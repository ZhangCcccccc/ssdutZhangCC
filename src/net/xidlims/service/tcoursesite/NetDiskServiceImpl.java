package net.xidlims.service.tcoursesite;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import net.xidlims.common.DiskFile;
import net.xidlims.dao.WkFolderDAO;
import net.xidlims.dao.WkUploadDAO;
import net.xidlims.domain.TCourseSite;
import net.xidlims.domain.User;
import net.xidlims.domain.WkFolder;
import net.xidlims.domain.WkUpload;
import net.xidlims.service.common.ShareService;
import net.luxunsh.util.DateUtil;
import net.luxunsh.util.EmptyUtil;
import net.luxunsh.util.NetFileDiskUtils;
import net.luxunsh.util.UUIDUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import java.lang.Object;
import java.io.BufferedInputStream;  
import java.io.BufferedOutputStream;  
import java.io.File;  
import java.io.FileInputStream;  
import java.io.FileOutputStream;  
import java.util.Enumeration;  
import java.util.zip.CRC32;  
import java.util.zip.CheckedOutputStream;  
//import java.util.zip.ZipEntry;  
//import java.util.zip.ZipFile;  
import java.util.zip.ZipOutputStream;  
  
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;  
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;  


@Service("NetDiskService")
public class NetDiskServiceImpl implements  NetDiskService {
	
	@Autowired
	private WkUploadDAO wkUploadDAO;
	@Autowired
	private WkFolderDAO wkFolderDAO;
	@Autowired
	private WkFolderService wkFolderService;
	@Autowired
	private ShareService shareService;
	@Autowired
	private WkUploadService wkUploadService;
	@Autowired
	private TCourseSiteService tCourseSiteService;
	@Autowired
	private TCourseSiteUserService tCourseSiteUserService;
 	
	
	/**************************************************************************
	 * Description:资源模块-根据路径和类别获取upload对象
	 * 
	 * @author：裴继超
	 * @date ：2016-8-30
	 **************************************************************************/
 	@Override
 	public WkUpload findUploadByUrlAndType(String url, int type){
 		List<WkUpload> list = new ArrayList<WkUpload>();
 		//根据当前用户和资源类型获取资源列表，
 		String hql = new String("select w from WkUpload w where w.url like '%" + url + "'" );
 		if(type!=-1){
 			hql += " and " + "w.type = " + type ;
 		}
 		//System.out.println("---------------------"+hql);
 		list = wkUploadDAO.executeQuery(hql, 0, -1);
 		//获取资源总数
 		if(list.size()==0){
 			return new WkUpload();
 		}
 		return list.get(0);
 	}
 	
	/**************************************************************************
	 * Description:资源模块-获取对应文件夹下的资源列表
	 * 
	 * @author：裴继超
	 * @date ：2016-8-31
	 **************************************************************************/
 	public List<List<DiskFile>> findListFiles(String rootPath, String folder, 
			boolean onlyDirectory) throws FileNotFoundException, IOException {

 		//判断是否为根目录
		if (EmptyUtil.isStringEmpty(folder)) {
			folder = "";
		}
		//新建文件list,文件夹list，url list
		List<DiskFile> fileList = new ArrayList<DiskFile>();
		List<DiskFile> folderList = new ArrayList<DiskFile>();
		List<DiskFile> urlList = new ArrayList<DiskFile>();
		//File[] arrFiles = new File(rootPath + "/" + folder).listFiles();
		//获取当前文件夹下的所有文件，并排序
		List<File> arrFiles = this.sortFiles(new File(rootPath + "/" + folder).listFiles());
		DiskFile dFile = null;
		if (arrFiles != null) {
			for (File f : arrFiles) {
				dFile = this.changeToDiskFileByFlie(f, rootPath,folder);
				
				if (onlyDirectory && !f.isDirectory()) {
					continue;
				}
				if(dFile.getType()==1){
					fileList.add(dFile);
				}else if(dFile.getType()==0){
					folderList.add(dFile);
				}else if(dFile.getType()==2){
					urlList.add(dFile);
				}
				
			}
		}
		List<List<DiskFile>> lists = new ArrayList<List<DiskFile>>();
		lists.add(folderList);
		lists.add(fileList);
		lists.add(urlList);
		this.bubbleSort(folderList);
		this.bubbleSort(fileList);
		this.bubbleSort(urlList);
		
		return lists;
	}
 	
	/**************************************************************************
	 * Description:资源模块-文件排序（文件夹在前，文件在后）
	 * 
	 * @author：裴继超
	 * @date ：2016-8-31
	 **************************************************************************/
 	@Override
 	public List<File> sortFiles(File[] files){
		  List<File> list1 = new ArrayList<File>();
		  List<File> list2 = new ArrayList<File>();
		  List<File> list3 = new ArrayList<File>();
		  if(files != null){
			  for (File f : files) {
				   if (f.isDirectory()) {
				    list1.add(f);
				   }
				   if (f.isFile()) {
				    list2.add(f);
				   }
				  }
		  }
		Collections.sort(list1);
		  Collections.sort(list2);
		  list3.addAll(list1);
		  list3.addAll(list2);
		  
		  
		  return list3;
 	}
 	
 	
	/**************************************************************************
	 * Description:资源模块-把文件或文件夹转化为DiskFile对象
	 * 
	 * @author：裴继超
	 * @date ：2016-8-31
	 **************************************************************************/
 	@Override
 	public DiskFile changeToDiskFileByFlie(File file,String rootPath, String folder
 			)throws FileNotFoundException, IOException {
 		DiskFile dFile = null;
		WkUpload wkUpload = null;
		dFile = new DiskFile();
		dFile.setPath((file.getPath().substring(rootPath.length() + 1)).replace("\\", "/"));
		//获取根目录
		int endAddress = rootPath.lastIndexOf("tcoursesite");
		String ss = rootPath.substring(endAddress, rootPath.length());
		
		
		wkUpload = this.findUploadByUrlAndType(ss+"/"+dFile.getPath(), -1);
		if (file.isFile()) {
			//String fid = FilenameUtils.getBaseName(upload.getName());
			dFile.setName(wkUpload.getName());
			dFile.setId(wkUpload.getId());
			dFile.setType(1);
			//dFile.setName(wkUploadDAO.findWkUploadByPrimaryKey(0).getName());
			@SuppressWarnings("resource")
			FileInputStream fileInputStream = new FileInputStream(file);
			int size = fileInputStream.available();
			fileInputStream.close();
			long length = file.length();
			if (length > 1024 * 1024) {
				int a = (int) ((length / 1000000f) * 100);
				dFile.setSize(a / 100f + " MB");
			} else if (length > 1024) {
				int a = (int) ((length / 1000f) * 100);
				dFile.setSize(a / 100f + " KB");
			} else {
				dFile.setSize(size + " bytes");
			}

		} else {
			File[] child = new File(rootPath+ "/" +folder+ "/" + file.getName()).listFiles();
			dFile.setId(wkUpload.getId());
			dFile.setName(file.getName());
			dFile.setType(0);
			//创建时间转化格式
			Calendar upTime = wkUpload.getUpTime();
			//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			//String upTimeDate =sdf.format(upTime.getTime());
			dFile.setDate(upTime.toString());
			dFile.setSize(child.length+"项");
		}

		dFile.setDate(DateUtil.dateToStr(new Date(file.lastModified())));
		
		return dFile;
 	}
	
 	public static void bubbleSort(List<DiskFile> files) {   
 		Comparator<DiskFile> comparator = new Comparator<DiskFile>(){
 		public int compare(DiskFile s1, DiskFile s2) {
 	     return s1.getId()-s2.getId();
 		}
 		};
 		 Collections.sort(files,comparator);
 		  //display(files);
 		DiskFile tempFile; // 记录临时中间值   
 	    int size = files.size(); // 数组大小   
 	} 
 	
 	static void display(List<DiskFile> lst){
 		  for(DiskFile s:lst)
 		   System.out.println(s.getId());
 		 }
 	
	/**************************************************************************
	 * Description:资源模块-保存文件、文件夹、链接
	 * 
	 * @author：裴继超
	 * @date ：2016-9-1
	 **************************************************************************/
	@Override
	public WkUpload saveUpload(WkUpload upload) {
		
		upload=wkUploadDAO.store(upload);
		wkUploadDAO.flush();
		return upload;
	}
	
	/**************************************************************************
	 * Description:资源模块-把文件夹，文件列表转化成String
	 * 
	 * @author：裴继超
	 * @date ：2016-9-7
	 **************************************************************************/
	@Override
	public String changeFilesListToString(String path,List<List<DiskFile>> listListFiles,
			Integer tCourseSiteId) {
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
		        ||user.getAuthorities().toString().contains("COURSETEACHER")
		        ||user.getAuthorities().toString().contains("EDUCATIONADMIN")
		        ||user.getAuthorities().toString().contains("LABCENTERMANAGER"))){
	           flag = 1;//实验中心主任   或者实验室管理员   或者超级管理员   或者教务处   或者校领导   或者教学副院长   或者课程负责教师   或者教务管理员   或者实验中心管理员   为教师权限
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
		//设置要返回的String
		String trs = "";
		//设置页面上对应tr的class值
		String folderClass = "folder ";
		String fileClass = "file ";
		String[] paths = path.split("/");
		//设置父文件夹路径
		String parentPath = "";
		//父文件夹的id
		int folderParentFolderId = -1;
		//遍历路径，查找路径中每个文件夹的id
		for(String p:paths){
			if(!p.equals("")){
				parentPath += "/"+p;
				Integer folderId = this.findUploadByUrlAndType("tcoursesite/site_disk_"+tCourseSiteId+parentPath, 100).getId();
				folderClass += folderId + " ";
				fileClass += "file" + folderId + " ";
				folderParentFolderId = folderId;
			}
		}

		
		//判断文件夹在第几层
		String paddingLeft = "";
		if(!path.equals("")){
			paddingLeft = "style='padding-left: "+20*(paths.length)+"px;'";
		}
		int type = 0;
		for(List<DiskFile> listFiles:listListFiles){
			if(type==0){
				for(DiskFile f:listFiles){

					
					trs += "<tr id='"+f.getId()+"' class='"+folderClass+f.getId()+"'>";
					trs += "<input type='hidden'  id='path"+f.getId()+"' value='"+path+f.getName()+"/'/>";
					trs += "<td class='parent "+"' id='row_8279' "+paddingLeft+" onclick='lookFolders(this)'>";
					trs += "<input class='l check_box' type='checkbox' id='checkbox" + f.getId() + "' name='checkname' value='" + f.getId() + "' />";
					trs += "<label class='l mt10' for='checkbox" + f.getId() + "'></label>";
					trs += "<div class='resource'><span><i class='fa fa-folder-o bc mlr5 f18'></i>";
					trs += "<span> "+f.getName()+"</span>";
					trs += "</span></div></td>";
					trs += "<td>整个站点</td>";
					trs += "<td>admin</td>";//创建者
					trs += "<td>"+f.getDate()+"</td>";//时间
					trs += "<td  id='size" + f.getId() + "'>"+f.getSize()+"</td>";//大小
					trs += "<td >";
					if(flag == 1){
						//上传压缩包按钮
						trs += "<i class=\"fa fa-list mr5 poi\"  title=\"上传压缩文件\"  onclick=\"uploadZip(";
						trs += "'"+path+f.getName()+"/',"+f.getId();
						trs += ")\"></i>&nbsp";
						//上传文件按钮
						trs += "<i class=\"fa fa-upload mr5 poi\" title=\"上传文件\" onclick=\"uploadFile(";
						trs += "'"+path+f.getName()+"/',"+f.getId();
						trs += ")\"></i>&nbsp";
						//添加文件夹按钮
						trs += "<i class=\"fa fa-folder-o mr5 poi\" title=\"添加文件夹\" onclick=\"addFolder(";
						trs += "'"+path+f.getName()+"/'";
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
			}
			if(type==1){
				for(DiskFile f:listFiles){
					trs +=  "<tr id='file"+f.getId()+"' class='"+fileClass+"file"+f.getId()+"' >";
					trs += "<td class='parent' "+paddingLeft+" >";
					trs += "<input class='l check_box' type='checkbox' id='checkbox" + f.getId() + "' name='checkname' value='" + f.getId() + "' />";
					trs += "<label class='l mt10' for='checkbox" + f.getId() + "'></label>";
					trs += "<div class=''><span><i class='fa fa-file-o bc mlr5 f18'></i>";
					trs += "<span> "+f.getName()+"</span>";
					trs += "</span></div></td>";
					trs += "<td>整个站点</td>";
					trs += "<td>admin</td>";
					trs += "<td>"+f.getDate()+"</td>";
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
			if(type==2){
				for(DiskFile f:listFiles){
					
				}
			}
			type = type + 1;
		}
		return trs;
	}
	
	/**************************************************************************
	 * Description:资源模块-批量删除文件
	 * 
	 * @author：裴继超
	 * @date ：2016-9-12
	 **************************************************************************/
	@Override
	public String deleteFiles(String[] wkUploadIds,Integer tCourseSiteId,
			HttpServletRequest request) {
		String result = "";
		String rootPath = request.getSession().getServletContext().getRealPath("/");
		//设置文件夹下的所有文件的链接
		List<String> strs = new LinkedList<String>();
		for(String s:wkUploadIds){
			//根据id获取文件或文件夹对象
			WkUpload wkUpload = wkUploadService.findUploadByPrimaryKey(Integer.parseInt(s));
			if (wkUpload!=null&&wkUpload.getType() == 101) {
				
				String path = rootPath+wkUpload.getUrl();
				
				File file = new File(path);
				file.delete();
				wkUploadService.deleteWkUpload(wkUpload);
			}else if(wkUpload!=null&&wkUpload.getType() == 100){
				//获取文件夹下所有文件的路径
				NetFileDiskUtils.getFilePath(rootPath+wkUpload.getUrl(),strs);
				//循环文件夹下的所有文件
				for(String st:strs){
					String path =rootPath + (st.substring(rootPath.length())).replace("\\", "/");
					//删除对应的文件
					File file = new File(path);
					System.gc();
					file.delete();
				}
				String folder = rootPath+wkUpload.getUrl();
				
				NetFileDiskUtils.delFiles(new File(folder));
				wkUploadService.deleteWkUpload(wkUpload);
				this.deleteFilesByFolder(wkUpload.getUrl(), request);
			}
			result += s + ",";
		}
		
		return result;
	}
	
	/**************************************************************************
	 * Description:资源模块-删除文件夹下所有文件
	 * 
	 * @author：裴继超
	 * @date ：2016-9-12
	 **************************************************************************/
	@Override
	public void deleteFilesByFolder(String url,HttpServletRequest request){
		//new文件夹下所有文件列表
 		List<WkUpload> list = new ArrayList<WkUpload>();
 		//根据当前文件夹获取文件夹下文件类表sql
 		String hql = new String("select w from WkUpload w where w.url like '%" + url + "%'" );
 		list = wkUploadDAO.executeQuery(hql, 0, -1);
 		for(WkUpload w:list){
 			wkUploadDAO.remove(w);
 		}
	}
	
	/**************************************************************************
	 * Description:资源模块-解压缩zip包 
	 * 
	 * @author：于侃
	 * @date ：2016-09-18
	 * @param zipFilePath zip文件的全路径 
     * @param unzipFilePath 解压后的文件保存的路径 
     * @param includeZipFileName 解压后的文件保存的路径是否包含压缩文件的文件名。true-包含；false-不包含
	 **************************************************************************/
    @SuppressWarnings("unchecked")  
    public String unzip(String zipFilePath, String unzipFilePath, boolean includeZipFileName,String path,int tCourseSiteId) throws Exception  
    {  
    	zipFilePath = zipFilePath.replaceAll("/", "\\\\");
    	unzipFilePath = unzipFilePath.replaceAll("/", "\\\\");
    	if(unzipFilePath.lastIndexOf("\\")==(unzipFilePath.length()-1)){
    		unzipFilePath = unzipFilePath.substring(0,unzipFilePath.length()-1);
    	}
        if (StringUtils.isEmpty(zipFilePath) || StringUtils.isEmpty(unzipFilePath))  
        {  
            //throw new ParameterException(ICommonResultCode.PARAMETER_IS_NULL);            
        }  
        WkUpload checkUpload = findUploadByUrlAndType(unzipFilePath.substring(unzipFilePath.indexOf("upload\\tcoursesite\\site_disk_")).replaceAll("\\\\", "/"), 100);
		if(checkUpload.getId()!=null){//如果存在则不能创建
			//删除压缩包文件
	        File delFile = new File(zipFilePath); 
	        if(delFile.exists()){
	        	delFile.delete(); 
	          }
	          else{
	            System.out.println("文件不存在");
	          }
	        String json = "{";
	  		json += "\"result\":\"false\"";
	  		json += "}";
			return json;
		}
        File zipFile = new File(zipFilePath);  
        //如果解压后的文件保存路径包含压缩文件的文件名，则追加该文件名到解压路径  
        if (includeZipFileName)  
        {  
            String fileName = zipFile.getName();  
            if (StringUtils.isNotEmpty(fileName))  
            {  
                fileName = fileName.substring(0, fileName.lastIndexOf("."));  
            }  
            unzipFilePath = unzipFilePath + File.separator + fileName;  
        }  
        //创建解压缩文件保存的路径  
        File unzipFileDir = new File(unzipFilePath);  
        if (!unzipFileDir.exists() || !unzipFileDir.isDirectory())  
        {  
        	NetFileDiskUtils.mkDirectory(unzipFilePath.replaceAll("\\\\", "/"));
//        	boolean test = NetFileDiskUtils.mkDirectory("\\opt\\java\\xidlims\\upload\\tcoursesite\\site_disk_93\\test1");
//        	boolean test2 = NetFileDiskUtils.mkDirectory("\\opt\\java\\xidlims\\upload/tcoursesite/site_disk_93/test2");
//        	boolean test3 = NetFileDiskUtils.mkDirectory("/opt/java/xidlims/upload/tcoursesite/site_disk_93/test3");
//        	boolean test4 = NetFileDiskUtils.mkDirectory(unzipFilePath.substring(0,unzipFilePath.indexOf("upload\\tcoursesite\\site_disk_"))
//        			+unzipFilePath.substring(unzipFilePath.indexOf("upload\\tcoursesite\\site_disk_")).replaceAll("\\\\", "/"));
            //unzipFileDir.mkdirs();
            //保存文件夹进数据库
            WkUpload upload = new WkUpload();
            upload.setUpTime(Calendar.getInstance());//创建时间
            upload.setUrl(unzipFilePath.substring(unzipFilePath.indexOf("upload\\tcoursesite\\site_disk_")).replaceAll("\\\\", "/"));//文件夹路径
            upload.setName(unzipFilePath.substring(unzipFilePath.lastIndexOf("\\")+1));//名字
            upload.setType(100);//类型：100——文件夹
    		upload.setUser(shareService.getUser());//创建者
    		upload = saveUpload(upload);//报存文件夹对象
        }  
        
        //开始解压  
        ZipEntry entry = null;  
        String entryFilePath = null, entryDirPath = null;  
        File entryFile = null, entryDir = null;  
        int index = 0, count = 0, bufferSize = 1024;  
        byte[] buffer = new byte[bufferSize];  
        BufferedInputStream bis = null;  
        BufferedOutputStream bos = null;  
        //ZipFile zip = new ZipFile(zipFile);  
        //Enumeration<ZipEntry> entries = (Enumeration<ZipEntry>)zip.entries();
        System.out.println(zipFilePath);
        ZipFile zip = new ZipFile(zipFilePath.replaceAll("\\\\", "/"),"utf-8"); //解决中文乱码问题  
        Enumeration<ZipEntry> entries = zip.getEntries();  
        //循环对压缩包里的每一个文件进行解压       
        while(entries.hasMoreElements())  
        {  
            entry = entries.nextElement();  
            //构建压缩包中一个文件解压后保存的文件全路径  
            entryFilePath = unzipFilePath.replaceAll("\\\\", "/") + "/" + entry.getName();
            entryFilePath = entryFilePath.replaceAll("/", "\\\\");
            System.out.println(entryFilePath);
            //构建解压后保存的文件夹路径  
            index = entryFilePath.lastIndexOf("\\");  
            System.out.println("index======="+index);
            if (index != -1)  
            {  
                entryDirPath = entryFilePath.substring(0, index);  
                System.out.println(entryDirPath+"------"+index);
            }  
            else  
            {  
                entryDirPath = "";  
            }             
            entryDir = new File(entryDirPath);  
            //如果文件夹路径不存在，则创建文件夹  
            if (!entryDir.exists() || !entryDir.isDirectory())  
            {  
            	NetFileDiskUtils.mkDirectory(entryDirPath.replaceAll("\\\\", "/"));
                //entryDir.mkdirs();  
                //保存文件夹进数据库
                WkUpload wkUpload = new WkUpload();
                wkUpload.setUpTime(Calendar.getInstance());//创建时间
        		wkUpload.setUrl(entryDirPath.substring(entryDirPath.indexOf("upload\\tcoursesite\\site_disk_")).replaceAll("\\\\", "/"));//文件夹路径
        		wkUpload.setName(entryDirPath.substring(entryDirPath.lastIndexOf("\\")+1, entryDirPath.length()));//名字
        		wkUpload.setType(100);//类型：100——文件夹
        		wkUpload.setUser(shareService.getUser());//创建者
        		wkUpload = saveUpload(wkUpload);//报存文件夹对象
            }  
            
            String uuid = UUIDUtil.getUUID();//随机生成文件新名字
            String extName = "";//初始化后缀名
            String oriFileName = "";//初始文件原名字
            String size = "";//文件大小
            
            if(entryFilePath.length() != entryDirPath.length()+1){
            	oriFileName = entryFilePath.substring(index+1, entryFilePath.length());
            	extName = FilenameUtils.getExtension(oriFileName);//获取后缀名
            	entryFilePath = entryDirPath+"\\"+uuid + "." + extName;
            	//创建解压文件  
                entryFile = new File(entryFilePath.replaceAll("\\\\", "/"));
                if (entryFile.exists())  
                {  
                    //检测文件是否允许删除，如果不允许删除，将会抛出SecurityException  
                    SecurityManager securityManager = new SecurityManager();  
                    securityManager.checkDelete(entryFilePath);  
                    //删除已存在的目标文件  
                    entryFile.delete();   
                }  
                  
                //写入文件  
                bos = new BufferedOutputStream(new FileOutputStream(entryFile));  
                bis = new BufferedInputStream(zip.getInputStream(entry));  
                while ((count = bis.read(buffer, 0, bufferSize)) != -1)  
                {  
                    bos.write(buffer, 0, count);  
                }  
                bos.flush();  
                bos.close();    
                
                //新建数据库文件对象
        		WkUpload attach = new WkUpload();
        		attach.setId(0);
        		
        		attach.setUpTime(Calendar.getInstance());//上传时间
        		attach.setUrl(entryFilePath.substring(entryFilePath.indexOf("upload\\tcoursesite\\site_disk_")).replaceAll("\\\\", "/"));//上传路径
        		attach.setName(oriFileName);//文件原名字
        		attach.setType(101);//类型：101——文件
        		//计算文件大小
				long length = entryFile.length();
				if (length > 1024 * 1024) {
					int a = (int) ((length / 1000000f) * 100);
					size = a / 100f + " MB";
				} else if (length > 1024) {
					int a = (int) ((length / 1000f) * 100);
					size = a / 100f + " KB";
				} else {
					size = size + " bytes";
				}
        		attach.setSize(size);//大小
        		//当前登陆人
        		User user = shareService.getUser();
        		attach.setUser(user);//创建者
        		int attachId = wkUploadService.saveUpload(attach);//保存文件对象入数据库
            }
                      
        }  
        zip.close();
        //删除压缩包文件
        File delFile = new File(zipFilePath.replaceAll("\\\\", "/")); 
        if(delFile.exists()){
        	delFile.delete(); 
          }
          else{
            System.out.println("文件不存在");
          }
        //查询要创建的文件夹
        WkUpload wkUpload = findUploadByUrlAndType(unzipFilePath.substring(unzipFilePath.indexOf("upload\\tcoursesite\\site_disk_")).replaceAll("\\\\", "/"), 100);
        
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
				Integer folderId = findUploadByUrlAndType("upload/tcoursesite/site_disk_" + tCourseSiteId + parentPath, 100).getId();
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
        return json;
    }  
    
	/**************************************************************************
	 * Description:资源模块程-搜索资源
	 * 
	 * @author：于侃
	 * @date ：2016年10月8日 10:11:46
	 **************************************************************************/
 	public List<WkUpload> searchWkUploads(String uploadName,Integer tCourseSiteId,int uploadType){
 		//声明文件列表
 		List<WkUpload> list = new ArrayList<WkUpload>();
 		//根据课程id、资源名和资源类型获取资源列表
 		String url = "upload/tcoursesite/site_disk_"+tCourseSiteId;
 		String hql = new String("select w from WkUpload w where w.name like '%" + uploadName + "%' and w.url like '%"+ url +"%' and w.type = " + uploadType);
 		//list = wkUploadDAO.executeQuery(hql, (currpage-1)*pagesize, pagesize);
 		list = wkUploadDAO.executeQuery(hql, 0, -1);
 		return list;
 	}
 	
 	/**************************************************************************
	 * Description:实验项目上传资源容器中对应也上传-原生方式上传
	 * 
	 * @author：马帅
	 * @date ：2017-6-30
	 **************************************************************************/
	@Override
	public void uploadResource(HttpServletRequest request, String path,
			Integer type, Integer id) {
		// TODO Auto-generated method stub
				String sep = System.getProperty("file.separator");
				MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
				/** 构建文件保存的目录* */
				// String PathDir = "/upload/"+ dateformat.format(new Date());
				/** 得到文件保存目录的真实路径* */
				String RealPathDir = "";
				RealPathDir = request.getSession().getServletContext().getRealPath("/") + path;
				// //System.out.println("文件保存目录的真实路径:"+logoRealPathDir);
				/** 根据真实路径创建目录* */
				File SaveFile = new File(RealPathDir);
				if (!SaveFile.exists()) {
					SaveFile.mkdirs();
				}		
				Iterator fileNames = multipartRequest.getFileNames();
				for (; fileNames.hasNext();) {
					String filename = (String) fileNames.next();
					
					List<MultipartFile> files = multipartRequest.getFiles(filename);
					for (MultipartFile file : files) {
						String fileTrueName = file.getOriginalFilename();			
						//取得上传文件后缀
						String suffix = fileTrueName.substring(fileTrueName.lastIndexOf("."));
						// 使用UUID生成文件名称
						String logImageName = UUID.randomUUID().toString() + suffix;// 构建文件名称	
						if (!file.isEmpty()) {
								try {
									file.transferTo(new File(RealPathDir + sep + logImageName));
								} catch (IllegalStateException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							
								WkUpload wkupload = new WkUpload();
								/*if(wkUploadService.findImageBySiteIdAndType(id, type).size()>0){
									wkupload = wkUploadService.findImageBySiteIdAndType(id, type).get(0);
								}*/
								//计算文件大小
								String size = "";
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
								//设置上传文件大小
								//System.out.println(name);
								wkupload.setSize(size);
								String pattern = Pattern.quote(System.getProperty("file.separator"));
								String[] names = path.split(pattern);
								//String[] names =path.split("\\\\");
								String name = names[names.length-1];
								String folder = names[names.length-2];
								wkupload.setUrl("upload/tcoursesite/site_disk_"+id+"/"+folder+"/"+name+"/"+logImageName);
								wkupload.setName(fileTrueName);
								wkupload.setUpTime(Calendar. getInstance());
								wkupload.setUser(shareService.getUserDetail());
								wkupload.setType(101);
								wkUploadDAO.store(wkupload);
						}
					}
					}
					
				MultipartFile multipartFile = multipartRequest.getFile("file");

				/** 判断文件不为空 */
				// 存放文件文件夹名称
				for (; fileNames.hasNext();) {
					String filename = (String) fileNames.next();
					MultipartFile file = multipartRequest.getFile(filename);
					String suffix = multipartFile.getOriginalFilename().substring(
							multipartFile.getOriginalFilename().lastIndexOf("."));
					/** 使用UUID生成文件名称* */
					String logImageName = UUID.randomUUID().toString() + suffix;// 构建文件名称
					/** 拼成完整的文件保存路径加文件* */
					String fileName = RealPathDir + "/" + logImageName;
					File thisFile = new File(RealPathDir);
					if (!thisFile.exists()) {
						thisFile.mkdirs();
					} else {
						try {
							file.transferTo(new File(fileName));
						} catch (IllegalStateException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}

				}
	}
	
	/*************************************************************************************
	 * Description:实验项目模块多文件上传
	 * 
	 * @author： 马帅
	 * @date：2017-8-21
	 *************************************************************************************/
	@Override
	public Map<String, String> uploadMultiFile(HttpServletRequest request,
			String path, Integer type, Integer siteId) {
		Map<String,String> map = new HashMap<String, String>();
		String sep = System.getProperty("file.separator");
		//上传文件的类型
		String name = "";
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		/** 构建文件保存的目录* */
		// String PathDir = "/upload/"+ dateformat.format(new Date());
		/** 得到文件保存目录的真实路径* */
		String RealPathDir = request.getSession().getServletContext().getRealPath("/") + path;
		// //System.out.println("文件保存目录的真实路径:"+logoRealPathDir);
		/** 根据真实路径创建目录* */
		File SaveFile = new File(RealPathDir);
		if (!SaveFile.exists()) {
			SaveFile.mkdirs();
		}		
		Iterator fileNames = multipartRequest.getFileNames();
		for (; fileNames.hasNext();) {
			String filename = (String) fileNames.next();
			List<MultipartFile> files = multipartRequest.getFiles(filename);
			for (MultipartFile file : files){
				String fileTrueName = file.getOriginalFilename();			
				//取得上传文件后缀
				String suffix = fileTrueName.substring(fileTrueName.lastIndexOf("."));
				// 使用UUID生成文件名称
				String logImageName = UUID.randomUUID().toString() + suffix;// 构建文件名称	
				if (!file.isEmpty()) {
					      try{
						       file.transferTo(new File(RealPathDir + sep + logImageName));
					         } catch (IllegalStateException e) {
						     // TODO Auto-generated catch block
						     e.printStackTrace();
					         } catch (IOException e) {
						     // TODO Auto-generated catch block
						     e.printStackTrace();
					      }
						name = "skill";
						WkUpload wkupload = new WkUpload();
						String size = "";
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
						wkupload.setSize(size);
						wkupload.setUrl("/upload/tcoursesite/site"+siteId+"/"+name+"/"+logImageName);
						wkupload.setName(fileTrueName);
						wkupload.setUpTime(Calendar. getInstance());
						wkupload.setUser(shareService.getUserDetail());
						wkupload.setType(type);
						//list.add(wkupload.getName());
						int id = wkUploadService.saveUpload(wkupload);
						map.put(id+"", wkupload.getName());
				}
			}
		}
		MultipartFile multipartFile = multipartRequest.getFile("file");

		/** 判断文件不为空 */
		// 存放文件文件夹名称
		for (; fileNames.hasNext();) {
			String filename = (String) fileNames.next();
			MultipartFile file = multipartRequest.getFile(filename);
			String suffix = multipartFile.getOriginalFilename().substring(
					multipartFile.getOriginalFilename().lastIndexOf("."));
			/** 使用UUID生成文件名称* */
			String logImageName = UUID.randomUUID().toString() + suffix;// 构建文件名称
			/** 拼成完整的文件保存路径加文件* */
			String fileName = RealPathDir + "/" + logImageName;
			File thisFile = new File(RealPathDir);
			if (!thisFile.exists()) {
				thisFile.mkdirs();
			} else {
				try {
					file.transferTo(new File(fileName));
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
		return map;
	}
}