package net.xidlims.web.tcoursesite;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.luxunsh.util.EmptyUtil;
import net.luxunsh.util.NetFileDiskUtils;
import net.luxunsh.util.UUIDUtil;
import net.xidlims.common.DiskFile;
import net.xidlims.dao.WkUploadDAO;
import net.xidlims.domain.TCourseSite;
import net.xidlims.domain.User;
import net.xidlims.domain.WkUpload;
import net.xidlims.service.common.ShareService;
import net.xidlims.service.tcoursesite.NetDiskService;
import net.xidlims.service.tcoursesite.TCourseSiteService;
import net.xidlims.service.tcoursesite.TCourseSiteUserService;
import net.xidlims.service.tcoursesite.WkFolderService;
import net.xidlims.service.tcoursesite.WkUploadService;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("tcoursesite/disk")
@Controller("NetDiskController")
public class NetDiskController {

	@Autowired
	private ShareService shareService;
	@Autowired
	private TCourseSiteService tCourseSiteService;
	@Autowired
	private WkUploadService wkUploadService;
	@Autowired
	private WkFolderService wkFolderService;
	@Autowired
	private NetDiskService netDiskService;
	@Autowired
	private WkUploadDAO wkUploadDAO;
	@Autowired
	private TCourseSiteUserService tCourseSiteUserService;
	

	/**************************************************************************
	 * Description:资源模块-文件列表
	 * 
	 * @author：裴继超
	 * @date ：2016-8-30
	 **************************************************************************/
	@RequestMapping("/listFiles")
	public ModelAndView listFiles(@RequestParam Integer tCourseSiteId,HttpServletRequest request ) {
		ModelAndView mav = new ModelAndView();
		//新建一个文件夹对象
		WkUpload wkUpload = new WkUpload();
		mav.addObject("wkUpload", wkUpload);
		// 选择的课程中心
		TCourseSite tCourseSite = tCourseSiteService.findCourseSiteById(tCourseSiteId);
		mav.addObject("tCourseSite", tCourseSite);
		//当前登录人
		User user = shareService.getUser();
		mav.addObject("user", user);
		//当前登录者
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		//根目录
		String rootPath = request.getSession().getServletContext().getRealPath("/") + "upload/tcoursesite/site_disk_" + tCourseSiteId;
		try {
			//获取根目录下所有文件夹和文件
			List<List<DiskFile>> lists = netDiskService.findListFiles(rootPath, "", false);
			mav.addObject("lists", lists);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//角色判断：如果具有老师权限且为该课程的创建者或者助教则默认为老师，如果没有教师权限则默认为学生
		Integer flag = 2;
		if(user!=null&&(user.getAuthorities().toString().contains("EXCENTERDIRECTOR")
		        ||user.getAuthorities().toString().contains("LABMANAGER")
		        ||user.getAuthorities().toString().contains("SUPERADMIN")
		        ||user.getAuthorities().toString().contains("DEAN")
		        ||user.getAuthorities().toString().contains("SCHOOLLEADER")
		        ||user.getAuthorities().toString().contains("ASSOCIATEDEAN")
		        ||user.getAuthorities().toString().contains("EDUCATIONADMIN")
		        ||user.getAuthorities().toString().contains("LABCENTERMANAGER"))){
			flag = 1;//实验中心主任   或者实验室管理员   或者超级管理员   或者教务处   或者校领导   或者教学副院长    或者教务管理员   或者实验中心管理员   为教师权限
		}else if(user!=null&&user.getAuthorities().toString().contains("TEACHER")){
			if (tCourseSite.getUserByCreatedBy().getUsername().equals(user.getUsername())) {
				flag = 1;//如果当前登陆人是本人开的课程，则是老师身份
			}else if (tCourseSiteUserService.isSTeacherBySiteId(tCourseSiteId,user.getUsername())){
				flag = 1;//如果当前登陆人是本课程的助教，则是老师身份
			}else if (tCourseSiteUserService.isUserBelongToTeacher(tCourseSiteId)){
				flag=1; //如果当前登陆人参与这门课，则是老师身份
			}else {
				flag = 0;//如果当前登陆人不是本人开的课程，则是学生身份
			}
			
		}else if(user!=null&&user.getAuthorities().toString().contains("STUDENT")){
			flag = 0;
		}
		mav.addObject("flag", flag);
		mav.setViewName("tcoursesite/disk/listFiles.jsp");
		return mav;
	}

	/**************************************************************************
	 * Description:资源模块-下载文件
	 * 
	 * @author：裴继超
	 * @date ：2016-9-13
	 **************************************************************************/
	@RequestMapping("download")
	public ResponseEntity<byte[]> download(@RequestParam Integer tCourseSiteId,
			HttpServletRequest request) throws IOException {
		//设置多个文件的地址
		List<String> strs = new LinkedList<String>();
		//文件的主键id
		String fid = request.getParameter("fid");
		//根据id获取文件或文件夹对象
		WkUpload wkUpload = wkUploadService.findUploadByPrimaryKey(Integer.parseInt(fid));
		//当前登录用户
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		//服务器地址
		String rootPath = request.getSession().getServletContext().getRealPath("/");
		
		//获取相应路径下所有文件
		//NetFileDiskUtils.getFilePath(rootPath,strs);
		
		String path = wkUpload.getUrl();//文件的相对路径
		File file = new File(rootPath + path);//设置文件的路径
		HttpHeaders headers = new HttpHeaders();//设置HttpHeaders对象
		String fname = wkUpload.getName();//设置文件的名字
		String fileName = new String(fname.getBytes("UTF-8"), "iso-8859-1");// 为了解决中文名称乱码问题
		//下载该文件
		headers.setContentDispositionFormData("WkUpload", fileName);
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);

	}

	
	/**************************************************************************
	 * Description:资源模块-删除文件夹和文件
	 * 
	 * @author：裴继超
	 * @date ：2016-9-13
	 **************************************************************************/
	@ResponseBody
	@RequestMapping("/removeFile")
	public String removeFile(@RequestParam Integer tCourseSiteId,
			HttpServletRequest request) throws UnsupportedEncodingException {
		
		//获取文件夹和文件的id集
		String fids = URLDecoder.decode(request.getParameter("fids"),"UTF-8");
		fids = fids.substring(0,fids.length()-1);//去最后一个逗号

		String[] wkUploadIds = fids.split(",");//id集转化为数组
		//当期那登录者
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		//服务器路径
		String rootPath = request.getSession().getServletContext().getRealPath("/");
		//删除文件夹及文件，返回id集
		String result = netDiskService.deleteFiles(wkUploadIds,tCourseSiteId,request);
		return result;
	}

	/**************************************************************************
	 * Description:资源模块-创建文件夹
	 * 
	 * @author：裴继超
	 * @date ：2016-9-13
	 **************************************************************************/
	@ResponseBody
	@RequestMapping("/creatFolder")
	public String creatFolder(@RequestParam Integer tCourseSiteId,String name,
			String path,HttpServletRequest request) 
					throws UnsupportedEncodingException {

		//根目录
		String rootPath = request.getSession().getServletContext().getRealPath("/") + "upload/tcoursesite/site_disk_" + tCourseSiteId;
		//String name = URLDecoder.decode(request.getParameter("name"),"UTF-8");
		
		//查询要创建的文件夹是否存在
		WkUpload wkUpload = netDiskService.findUploadByUrlAndType("upload/tcoursesite/site_disk_" + tCourseSiteId + "/" + path + name, 100);
		if(wkUpload.getId()!=null){//如果存在则不能创建
			return "false";
		}
		//若path为空则直接在根目录下创建
		if (!EmptyUtil.isStringEmpty(path)) {
			NetFileDiskUtils.mkDirectory(rootPath + "/" + path + name);
		} else {
			NetFileDiskUtils.mkDirectory(rootPath + "/" + name);
		}
		
		
		wkUpload.setUpTime(Calendar.getInstance());//创建时间
		wkUpload.setUrl("upload/tcoursesite/site_disk_" + tCourseSiteId + "/" + path + name);//文件夹路径
		wkUpload.setName(name);//名字
		wkUpload.setType(100);//类型：100——文件夹
		wkUpload.setUser(shareService.getUser());//创建者
		wkUpload = netDiskService.saveUpload(wkUpload);//报春文件夹对象
		//设置页面上对应tr的class值
		String folderClass = "folder ";
		//父文件夹的class
		String parentClass = "folder";
		//都城父文件夹转数组
		String[] paths = path.split("/");
		//父文件夹路径
		String parentPath = "";
		//父文件夹id
		int parentFolderId = -1;
		for(String p:paths){//遍历父文件夹
			if(!p.equals("")){
				parentPath += "/"+p;
				//每层父文件夹的主键id
				Integer folderId = netDiskService.findUploadByUrlAndType("upload/tcoursesite/site_disk_" + tCourseSiteId + parentPath, 100).getId();
				parentClass = folderId.toString();
				folderClass += folderId + " ";
				parentFolderId = folderId;//设置父文件夹id
			}
		}
		//文件夹信息json
		String json = "{";
		json += "\"folderPath\":\""+path+"\",";
		json += "\"folderClass\":\""+folderClass+"\",";
		json += "\"parentClass\":\""+parentClass+"\",";
		json += "\"name\":\""+wkUpload.getName()+"\",";
		json += "\"type\":\""+wkUpload.getType()+"\",";
		json += "\"size\":\""+wkUpload.getSize()+"\",";
		json += "\"id\":\""+wkUpload.getId()+"\",";
		//创建时间转化格式
		Calendar upTime = wkUpload.getUpTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String upTimeDate =sdf.format(upTime.getTime());
		json += "\"upTime\":\""+upTimeDate+"\"";
		json += "} ";
		return shareService.htmlEncode(json);
	}
	
	/**************************************************************************
	 * Description:资源模块-上传文件
	 * 
	 * @author：裴继超
	 * @date ：2016-9-13
	 **************************************************************************/
	@ResponseBody
	@RequestMapping("/file/upload")
	public String testUpload(@RequestParam Integer tCourseSiteId,
			HttpServletRequest request) throws Exception{
		//当前登录者
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		//根目录
		String rootPath = request.getSession().getServletContext().getRealPath("/") + "upload/tcoursesite/site_disk_" + tCourseSiteId;
		//当前路径
		String path = request.getParameter("currentPath");
		//如果path为空设置为""
		if (EmptyUtil.isStringEmpty(path)) {
			path = "";
		}

		//判断根目录文件夹是否存在
		File root = new File (request.getSession().getServletContext().getRealPath("/") + "upload/tcoursesite/site_disk_" + tCourseSiteId);
		//不存在则创建
		if  (!root .exists()  && !root .isDirectory())      
		{       
			NetFileDiskUtils.mkDirectory(rootPath + "/" );
		}
		
		String filePath = "";//初始化文件路径
		String extName = "";//初始化后缀名
		String fileName = "";//初始化文件新名字
		String oriFileName = "";//初始文件原名字
		String uuid = UUIDUtil.getUUID();//随机生成文件新名字
		String folderPath = rootPath + "/" + path;//文件保存路径
		String size = "";//文件大小

		try {
			//创建一个通用的多部分解析器
			CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
			if (multipartResolver.isMultipart(request)) { //判断 request 是否有文件上传,即多部分请求
				MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;//转换成多部分request
				Iterator<String> iter = multiRequest.getFileNames();//取得request中的所有文件名
				while (iter.hasNext()) {
					MultipartFile file = multiRequest.getFile(iter.next());//取得上传文件
					if (file != null) {//如果file不为空
						oriFileName = file.getOriginalFilename();//获取文件原名字
						if (oriFileName.trim() != "") {//如果原名不为空
							extName = FilenameUtils.getExtension(oriFileName);//获取后缀名
							fileName = uuid + "." + extName;//设置文件新名字
							filePath = folderPath + "/" + fileName;//设置文件保存路径
							file.transferTo(new File(filePath));//上传文件
							//计算文件大小
							long length = file.getSize();
							if (length > 1024 * 1024) {
								int a = (int) ((length / 1000000f) * 100);
								size = a / 100f + " MB";
							} else if (length > 1024) {
								int a = (int) ((length / 1000f) * 100);
								size = a / 100f + " KB";
							} else {
								size = size + " bytes";
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "false";
		}
		//新建数据库文件对象
		WkUpload attach = new WkUpload();
		attach.setId(0);
		
		attach.setUpTime(Calendar.getInstance());//上传时间
		attach.setUrl("upload/tcoursesite/site_disk_" + tCourseSiteId + "/" + path + fileName);//上传路径
		attach.setName(oriFileName);//文件原名字
		attach.setType(101);//类型：101——文件
		attach.setSize(size);//大小
		//当前登陆人
		User user = shareService.getUser();
		attach.setUser(user);//创建者
		int attachId = wkUploadService.saveUpload(attach);//保存文件对象入数据库
		//设置页面上对应tr的class值
		String fileClass = "file ";
		//父文件夹的class
		String parentClass = "file";
		String[] paths = path.split("/");//多层父文件夹转数组
		String parentPath = "";//父文件夹路径
		
		
		for(String p:paths){//遍历多层的父文件夹
			if(!p.equals("")){
				parentPath += "/"+p;
				//每层的父文件夹id
				Integer folderId = netDiskService.findUploadByUrlAndType("upload/tcoursesite/site_disk_" + tCourseSiteId + parentPath, 100).getId();
				parentClass = "file" + folderId.toString();
				fileClass += "file" + folderId + " ";
			
			}
		}
		fileClass += "file" + attachId + " ";
		//新建的文件返回页面json
		String json = "{";
		json += "\"folderPath\":\""+path+"\",";
		json += "\"fileClass\":\""+fileClass+"\",";
		json += "\"parentClass\":\""+parentClass+"\",";
		json += "\"name\":\""+attach.getName()+"\",";
		json += "\"type\":\""+attach.getType()+"\",";
		json += "\"size\":\""+attach.getSize()+"\",";
		json += "\"id\":\""+attachId+"\",";
		//上传时间转化格式
		Calendar upTime = attach.getUpTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String upTimeDate =sdf.format(upTime.getTime());
		json += "\"upTime\":\""+upTimeDate+"\"";
		json += "} ";
		return shareService.htmlEncode(json);
	}

	
	/**************************************************************************
	 * Description:资源模块-文件列表
	 * 
	 * @author：裴继超
	 * @date ：2016-8-30
	 **************************************************************************/
	@RequestMapping("/findFilesJson")
	public @ResponseBody Map<String, Object> findFilesJson(@RequestParam Integer tCourseSiteId,
			String path,HttpServletRequest request ) {
		//新建一个空的map
		Map<String, Object> map = new HashMap<String, Object>();
		// 选择的课程中心
		TCourseSite tCourseSite = tCourseSiteService.findCourseSiteById(tCourseSiteId);
		//当前登录人
		User user = shareService.getUser();
		//根目录
		String rootPath = request.getSession().getServletContext().getRealPath("/") + "upload/tcoursesite/site_disk_" + tCourseSiteId;
		try {
			//获取该目录下的文件夹和文件
			List<List<DiskFile>> listListFiles = netDiskService.findListFiles(rootPath, path, false);
			//把文件夹，文件列表转化成String
			String trs = netDiskService.changeFilesListToString(path,listListFiles,tCourseSiteId);
			//把文件夹和文件tr传回页面
			map.put("folders", (trs==null)?"":trs);
			return map;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	
	/**************************************************************************
	 * Description:资源模块-上传压缩包自动解压
	 * 
	 * @author：于侃
	 * @date ：2016-09-18
	 **************************************************************************/
	@ResponseBody
	@RequestMapping("/uploadZip")
	public String uploadZip(@RequestParam Integer tCourseSiteId,
			HttpServletRequest request) throws Exception{
		//当前登录者
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		//根目录
		String rootPath = request.getSession().getServletContext().getRealPath("/") + "upload/tcoursesite/site_disk_" + tCourseSiteId;
		//当前路径
		String path = request.getParameter("currentPath");
		//如果path为空设置为""
		if (EmptyUtil.isStringEmpty(path)) {
			path = "";
		}

		//判断根目录文件夹是否存在
		File root = new File (request.getSession().getServletContext().getRealPath("/") + "upload/tcoursesite/site_disk_" + tCourseSiteId);
		//不存在则创建
		if  (!root .exists()  && !root .isDirectory())      
		{       
			NetFileDiskUtils.mkDirectory(rootPath + "/" );
		}
				
		String filePath = "";//初始化文件路径
		String extName = "";//初始化后缀名
		String fileName = "";//初始化文件新名字
		String oriFileName = "";//初始文件原名字
		String uuid = UUIDUtil.getUUID();//随机生成文件新名字
		String folderPath = rootPath + "/" + path;//文件保存路径
//		String size = "";//文件大小
		String json = "";//返回json
		try {
			//创建一个通用的多部分解析器
			CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
			if (multipartResolver.isMultipart(request)) { //判断 request 是否有文件上传,即多部分请求
				MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;//转换成多部分request
				Iterator<String> iter = multiRequest.getFileNames();//取得request中的所有文件名
				while (iter.hasNext()) {
					MultipartFile file = multiRequest.getFile(iter.next());//取得上传文件
					if (file != null) {//如果file不为空
						oriFileName = file.getOriginalFilename();//获取文件原名字
						if (oriFileName.trim() != "") {//如果原名不为空
							extName = FilenameUtils.getExtension(oriFileName);//获取后缀名
							if(extName.equals("zip")){
								filePath = folderPath + oriFileName;//设置文件保存路径
								file.transferTo(new File(filePath));//上传文件
								folderPath = folderPath + oriFileName.substring(0,oriFileName.lastIndexOf("."));
								json = netDiskService.unzip(filePath,folderPath,false,path,tCourseSiteId);
							}else{
								json = "{";
						  		json += "\"result\":\""+"notZip"+"\"";
						  		json += "}";
								return shareService.htmlEncode(json);
							}
//							//计算文件大小
//							long length = file.getSize();
//							if (length > 1024 * 1024) {
//								int a = (int) ((length / 1000000f) * 100);
//								size = a / 100f + " MB";
//							} else if (length > 1024) {
//								int a = (int) ((length / 1000f) * 100);
//								size = a / 100f + " KB";
//							} else {
//								size = size + " bytes";
//							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			json = "{";
	  		json += "\"result\":\""+"false"+"\"";
	  		json += "}";
			return shareService.htmlEncode(json);
		}
		return shareService.htmlEncode(json);
	}
	
	/**************************************************************************
	 * Description:资源模块-查询资源
	 * 
	 * @author：于侃
	 * @date ：2016年10月8日 13:57:37
	 **************************************************************************/
	@ResponseBody
	@RequestMapping("/searchFiles")
	public String searchFiles(String uploadName,Integer tCourseSiteId,HttpServletRequest request) throws Exception{
		// 选择的课程中心
		TCourseSite tCourseSite = tCourseSiteService.findCourseSiteById(tCourseSiteId);
		//当前登录人
		User user = shareService.getUser();
		//角色判断：如果具有老师权限且为该课程的创建者或者助教则默认为老师，如果没有教师权限则默认为学生
		Integer flag = 2;
		if(user!=null&&(user.getAuthorities().toString().contains("EXCENTERDIRECTOR")
		        ||user.getAuthorities().toString().contains("LABMANAGER")
		        ||user.getAuthorities().toString().contains("SUPERADMIN")
		        ||user.getAuthorities().toString().contains("DEAN")
		        ||user.getAuthorities().toString().contains("SCHOOLLEADER")
		        ||user.getAuthorities().toString().contains("ASSOCIATEDEAN")
		        ||user.getAuthorities().toString().contains("EDUCATIONADMIN")
		        ||user.getAuthorities().toString().contains("LABCENTERMANAGER"))){
			flag = 1;//实验中心主任   或者实验室管理员   或者超级管理员   或者教务处   或者校领导   或者教学副院长     或者教务管理员   或者实验中心管理员   为教师权限
		}else if(user!=null&&user.getAuthorities().toString().contains("TEACHER")){
			if (tCourseSite.getUserByCreatedBy().getUsername().equals(user.getUsername())) {
				flag = 1;//如果当前登陆人是本人开的课程，则是老师身份
			}else if (tCourseSiteUserService.isSTeacherBySiteId(tCourseSiteId,user.getUsername())){
				flag = 1;//如果当前登陆人是本课程的助教，则是老师身份
			}else {
				flag = 0;//如果当前登陆人不是本人开的课程，则是学生身份
			}
			
		}else if(user!=null&&user.getAuthorities().toString().contains("STUDENT")){
			flag = 0;
		}
		int tCourseSiteIdLength = String.valueOf(tCourseSiteId).length();
		String folderClass = "";
		String fileClass = "";
		//判断文件夹在第几层
		String paddingLeft = "";
		//拼接jsp字符串
		String trs = "";
		//路径
		String path = "";
		//根目录
		String rootPath = request.getSession().getServletContext().getRealPath("/");
		//搜索文件夹
		List<WkUpload> wkUploads = netDiskService.searchWkUploads(uploadName,tCourseSiteId,100);
		for(WkUpload f:wkUploads){
			folderClass = "folder ";
			fileClass = "file ";
			path = f.getUrl().substring(30+tCourseSiteIdLength);
			String[] paths = path.split("/");
			//设置父文件夹路径
			String parentPath = "";
			//遍历路径，查找路径中每个文件夹的id
			for(String p:paths){
				if(!p.equals("")){
					parentPath += "/"+p;
					Integer folderId = netDiskService.findUploadByUrlAndType("tcoursesite/site_disk_"+tCourseSiteId+parentPath, 100).getId();
					folderClass += folderId + " ";
					fileClass += "file" + folderId + " ";
				}
			}
			trs += "<tr id='"+f.getId()+"' class='"+folderClass+f.getId()+"'>";
			trs += "<input type='hidden'  id='path"+f.getId()+"' value='"+path+f.getName()+"/'/>";
			trs += "<td class='parent "+"' id='row_8279' "+paddingLeft+" onclick='openFolders("+ f.getId() +")'>";
			trs += "<input class='l check_box' type='checkbox' id='checkbox" + f.getId() + "' name='checkname' value='" + f.getId() + "' />";
			trs += "<label class='l mt10' for='checkbox" + f.getId() + "'></label>";
			trs += "<div class='resource'><span><i class='fa fa-folder-o bc mlr5 f18'></i>";
			trs += "<span> "+f.getName()+"</span>";
			trs += "</span></div></td>";
			trs += "<td>整个站点</td>";
			trs += "<td>admin</td>";//创建者
			//创建时间转化格式
			Calendar upTime = f.getUpTime();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String upTimeDate =sdf.format(upTime.getTime());
			trs += "<td>"+upTimeDate+"</td>";//时间
			File[] child = new File(rootPath+ "/" + f.getUrl()).listFiles();
			trs += "<td  id='size" + f.getId() + "'>"+child.length+"项</td>";//大小
			trs += "<td >";
			if(flag == 1){
				//上传压缩包按钮
				trs += "<i class=\"fa fa-list mr5 poi\"  title=\"上传压缩文件\"  onclick=\"uploadZip(";
				trs += "'"+path+"/',"+f.getId();
				trs += ")\"></i>&nbsp";
				//上传文件按钮
				trs += "<i class=\"fa fa-upload mr5 poi\" title=\"上传文件\" onclick=\"uploadFile(";
				trs += "'"+path+"/',"+f.getId();
				trs += ")\"></i>&nbsp";
				//添加文件夹按钮
				trs += "<i class=\"fa fa-folder-o mr5 poi\" title=\"添加文件夹\" onclick=\"addFolder(";
				trs += "'"+path+"/'";
				trs += ',';
				trs += "'',"+f.getId();
				trs += ")\">";
				trs += "</i>&nbsp";
				trs += "<i class=\"fa fa-trash-o mr5 poi\" title=\"删除\"  onclick='removeFiles(" + f.getId() + ");return false;'></i>";
			}
			trs += "</td>";
			trs += "</tr>";
			//放置一个隐藏的tr，用来给此文件夹下的文件定位
			trs += "<tr id='file"+f.getId()+"' class='file"+f.getId()+"' type='hidden'></tr>";
		}
		wkUploads = netDiskService.searchWkUploads(uploadName,tCourseSiteId,101);
		for(WkUpload f:wkUploads){
			folderClass = "folder ";
			fileClass = "file ";
			path = f.getUrl().substring(30+tCourseSiteIdLength);
			String[] paths = path.split("/");
			//设置父文件夹路径
			String parentPath = "";
			//遍历路径，查找路径中每个文件夹的id
			for(String p:paths){
				if(!p.equals("")){
					parentPath += "/"+p;
					Integer folderId = netDiskService.findUploadByUrlAndType("tcoursesite/site_disk_"+tCourseSiteId+parentPath, 100).getId();
					folderClass += folderId + " ";
					fileClass += "file" + folderId + " ";
				}
			}
			trs +=  "<tr id='file"+f.getId()+"' class='"+fileClass+"file"+f.getId()+"' >";
			trs += "<td class='parent' "+paddingLeft+" >";
			trs += "<input class='l check_box' type='checkbox' id='checkbox" + f.getId() + "' name='checkname' value='" + f.getId() + "' />";
			trs += "<label class='l mt10' for='checkbox" + f.getId() + "'></label>";
			trs += "<div class=''><span><i class='fa fa-file-o bc mlr5 f18'></i>";
			trs += "<span> "+f.getName()+"</span>";
			trs += "</span></div></td>";
			trs += "<td>整个站点</td>";
			trs += "<td>admin</td>";
			//创建时间转化格式
			Calendar upTime = f.getUpTime();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String upTimeDate =sdf.format(upTime.getTime());
			trs += "<td>"+upTimeDate+"</td>";
			trs += "<td>"+f.getSize()+"</td>";
			trs += "<td><i class=\"fa fa-download mr5 poi\" title=\"下载\"  onclick='download_(" + f.getId() + ");return false;'>";
			
			trs += "</i>&nbsp";
			if(flag == 1){
				trs += "<i class=\"fa fa-trash-o mr5 poi\" title=\"删除\"  onclick='removeFiles(" + f.getId() + ");return false;'></i>";
			}
			trs += "</td>";
			trs += "</tr>";
		}
		return shareService.htmlEncode(trs);
	}
	
	/**************************************************************************
	 * Description:资源模块-查询资源打开文件夹
	 * 
	 * @author：于侃
	 * @date ：2016年10月8日 13:57:37
	 **************************************************************************/
	@ResponseBody
	@RequestMapping("/openFolder")
	public Map<String, String> openFolder(Integer tCourseSiteId,Integer id,HttpServletRequest request) throws Exception{
		// 选择的课程中心
		TCourseSite tCourseSite = tCourseSiteService.findCourseSiteById(tCourseSiteId);
		//当前登录人
		User user = shareService.getUser();
		//角色判断：如果具有老师权限且为该课程的创建者或者助教则默认为老师，如果没有教师权限则默认为学生
		Integer flag = 2;
		if(user!=null&&(user.getAuthorities().toString().contains("EXCENTERDIRECTOR")
		        ||user.getAuthorities().toString().contains("LABMANAGER")
		        ||user.getAuthorities().toString().contains("SUPERADMIN")
		        ||user.getAuthorities().toString().contains("DEAN")
		        ||user.getAuthorities().toString().contains("SCHOOLLEADER")
		        ||user.getAuthorities().toString().contains("ASSOCIATEDEAN")
		        ||user.getAuthorities().toString().contains("EDUCATIONADMIN")
		        ||user.getAuthorities().toString().contains("LABCENTERMANAGER"))){
			flag = 1;//实验中心主任   或者实验室管理员   或者超级管理员   或者教务处   或者校领导   或者教学副院长   或者教务管理员   或者实验中心管理员   为教师权限
		}else if(user!=null&&user.getAuthorities().toString().contains("TEACHER")){
			if (tCourseSite.getUserByCreatedBy().getUsername().equals(user.getUsername())) {
				flag = 1;//如果当前登陆人是本人开的课程，则是老师身份
			}else if (tCourseSiteUserService.isSTeacherBySiteId(tCourseSiteId,user.getUsername())){
				flag = 1;//如果当前登陆人是本课程的助教，则是老师身份
			}else {
				flag = 0;//如果当前登陆人不是本人开的课程，则是学生身份
			}
			
		}else if(user!=null&&user.getAuthorities().toString().contains("STUDENT")){
			flag = 0;
		}
		Map<String, String> map = new HashMap<String, String>();
		int tCourseSiteIdLength = String.valueOf(tCourseSiteId).length();
		String folderClass = "";
		String fileClass = "";
		//判断文件夹在第几层
		String paddingLeft = "";
		//拼接jsp字符串
		String trs = "";
		//路径
		String path = "";
		//根目录
		String rootPath = request.getSession().getServletContext().getRealPath("/");
		//即将打开的文件夹
		WkUpload wkUpload = wkUploadDAO.findWkUploadById(id);
		String folderPath = wkUpload.getUrl();
		//文件夹下所有文件		
		File[] childs = new File(rootPath+ "/" + wkUpload.getUrl()).listFiles();
		for (File file : childs) {
			if(!file.isFile()){
				WkUpload f = netDiskService.findUploadByUrlAndType(folderPath + "/" + file.getName(), 100);
				folderClass = "folder ";
				fileClass = "file ";
				path = f.getUrl().substring(30+tCourseSiteIdLength);
				String[] paths = path.split("/");
				//设置父文件夹路径
				String parentPath = "";
				//遍历路径，查找路径中每个文件夹的id
				for(String p:paths){
					if(!p.equals("")){
						parentPath += "/"+p;
						Integer folderId = netDiskService.findUploadByUrlAndType("tcoursesite/site_disk_"+tCourseSiteId+parentPath, 100).getId();
						folderClass += folderId + " ";
						fileClass += "file" + folderId + " ";
					}
				}
				trs += "<tr id='"+f.getId()+"' class='"+folderClass+f.getId()+"'>";
				trs += "<input type='hidden'  id='path"+f.getId()+"' value='"+path+f.getName()+"/'/>";
				trs += "<td class='parent "+"' id='row_8279' "+paddingLeft+" onclick='openFolders("+ f.getId() +")'>";
				trs += "<input class='l check_box' type='checkbox' id='checkbox" + f.getId() + "' name='checkname' value='" + f.getId() + "' />";
				trs += "<label class='l mt10' for='checkbox" + f.getId() + "'></label>";
				trs += "<div class='resource'><span><i class='fa fa-folder-o bc mlr5 f18'></i>";
				trs += "<span> "+f.getName()+"</span>";
				trs += "</span></div></td>";
				trs += "<td>整个站点</td>";
				trs += "<td>admin</td>";//创建者
				//创建时间转化格式
				Calendar upTime = f.getUpTime();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String upTimeDate =sdf.format(upTime.getTime());
				trs += "<td>"+upTimeDate+"</td>";//时间
				File[] child = new File(rootPath+ "/" + f.getUrl()).listFiles();
				trs += "<td  id='size" + f.getId() + "'>"+child.length+"项</td>";//大小
				trs += "<td >";
				if(flag == 1){
					//上传压缩包按钮
					trs += "<i class=\"fa fa-list mr5 poi\"  title=\"上传压缩文件\"  onclick=\"uploadZip(";
					trs += "'"+path+"/',"+f.getId();
					trs += ")\"></i>&nbsp";
					//上传文件按钮
					trs += "<i class=\"fa fa-upload mr5 poi\" title=\"上传文件\" onclick=\"uploadFile(";
					trs += "'"+path+"/',"+f.getId();
					trs += ")\"></i>&nbsp";
					//添加文件夹按钮
					trs += "<i class=\"fa fa-folder-o mr5 poi\" title=\"添加文件夹\" onclick=\"addFolder(";
					trs += "'"+path+"/'";
					trs += ',';
					trs += "'',"+f.getId();
					trs += ")\">";
					trs += "</i>&nbsp";
					trs += "<i class=\"fa fa-trash-o mr5 poi\" title=\"删除\"  onclick='removeFiles(" + f.getId() + ");return false;'></i>";
				}
				trs += "</td>";
				trs += "</tr>";
				//放置一个隐藏的tr，用来给此文件夹下的文件定位
				trs += "<tr id='file"+f.getId()+"' class='file"+f.getId()+"' type='hidden'></tr>";
			}else{
				WkUpload f = netDiskService.findUploadByUrlAndType(folderPath + "/" + file.getName(), 101);
				folderClass = "folder ";
				fileClass = "file ";
				path = f.getUrl().substring(30+tCourseSiteIdLength);
				String[] paths = path.split("/");
				//设置父文件夹路径
				String parentPath = "";
				//遍历路径，查找路径中每个文件夹的id
				for(String p:paths){
					if(!p.equals("")){
						parentPath += "/"+p;
						Integer folderId = netDiskService.findUploadByUrlAndType("tcoursesite/site_disk_"+tCourseSiteId+parentPath, 100).getId();
						folderClass += folderId + " ";
						fileClass += "file" + folderId + " ";
					}
				}
				trs +=  "<tr id='file"+f.getId()+"' class='"+fileClass+"file"+f.getId()+"' >";
				trs += "<td class='parent' "+paddingLeft+" >";
				trs += "<input class='l check_box' type='checkbox' id='checkbox" + f.getId() + "' name='checkname' value='" + f.getId() + "' />";
				trs += "<label class='l mt10' for='checkbox" + f.getId() + "'></label>";
				trs += "<div class=''><span><i class='fa fa-file-o bc mlr5 f18'></i>";
				trs += "<span> "+f.getName()+"</span>";
				trs += "</span></div></td>";
				trs += "<td>整个站点</td>";
				trs += "<td>admin</td>";
				//创建时间转化格式
				Calendar upTime = f.getUpTime();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String upTimeDate =sdf.format(upTime.getTime());
				trs += "<td>"+upTimeDate+"</td>";
				trs += "<td>"+f.getSize()+"</td>";
				trs += "<td><i class=\"fa fa-download mr5 poi\" title=\"下载\"  onclick='download_(" + f.getId() + ");return false;'>";
				
				trs += "</i>&nbsp";
				if(flag == 1){
					trs += "<i class=\"fa fa-trash-o mr5 poi\" title=\"删除\"  onclick='removeFiles(" + f.getId() + ");return false;'></i>";
				}
				trs += "</td>";
				trs += "</tr>";
			}
		}
		map.put("tbody", trs);
		path = wkUpload.getUrl().substring(30+tCourseSiteIdLength);
		String[] paths = path.split("/");
		//设置打开文件夹后上方路径显示
		String ht = "...&nbsp;";
		//设置父文件夹路径
		String parentPath = "";
		for (String p : paths) {
			if(!p.equals("")){
				parentPath += "/"+p;
				Integer folderId = netDiskService.findUploadByUrlAndType("tcoursesite/site_disk_"+tCourseSiteId+parentPath, 100).getId();
				ht += "&nbsp;/&nbsp;<a href='javascript:void(0)' onclick='openFolders("+ folderId +")'>"+ p +"</a>";
			}
		}
		map.put("searchPath", ht);
		return map;
	}
	
/*	@RequestMapping("/xidapp/imageTest")
	public ModelAndView imageTest(HttpServletRequest request){
			ModelAndView mav = new ModelAndView();
			mav.setViewName("xidapp/imageTest.jsp");
			return mav;
	}*/
}