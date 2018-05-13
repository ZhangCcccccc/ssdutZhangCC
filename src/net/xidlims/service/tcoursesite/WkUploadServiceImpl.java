package net.xidlims.service.tcoursesite;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.xidlims.dao.WkUploadDAO;
import net.xidlims.domain.TCourseSite;
import net.xidlims.domain.User;
import net.xidlims.domain.WkFolder;
import net.xidlims.domain.WkUpload;
import net.xidlims.service.common.ShareService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;





@Service("WkUploadService")
public class WkUploadServiceImpl implements  WkUploadService {
	
	@Autowired
	private WkUploadDAO wkUploadDAO;
	@Autowired
	private WkFolderService wkFolderService;
	@Autowired
	private ShareService shareService;
	/**************************************************************************
	 * Description:保存文件 
	 * 
	 * @author：于侃
	 * @date ：2016-09-13
	 **************************************************************************/
	@Override
	public int saveUpload(WkUpload upload) {
		//设置上传时间为当前时间
		upload.setUpTime(Calendar.getInstance());
		//保存文件
		WkUpload up=wkUploadDAO.store(upload);
		//刷新数据库
		wkUploadDAO.flush();
		//返回上传文件的id
		return up.getId();
	}
	/**************************************************************************
	 * Description:根据主键查询上传的文件
	 * 
	 * @author：于侃
	 * @date ：2016-09-13
	 **************************************************************************/
	@Override
	public WkUpload findUploadByPrimaryKey(Integer valueOf) {
		//通过主键查找上传的文件
		return wkUploadDAO.findWkUploadByPrimaryKey(valueOf);
	}
	/**************************************************************************
	 * Description:删除文档
	 * 
	 * @author：于侃
	 * @date ：2016-09-13
	 **************************************************************************/
	@Override
	public void deleteWkUpload(WkUpload upload) {
		//删除上传文件
		wkUploadDAO.remove(upload);
	}
	
	/**************************************************************************
	 * Description:处理ajax上传过来文件，支持多文件 
	 * 
	 * @author：裴继超
	 * @date ：2016年5月5日14:51:48
	 **************************************************************************/
	@Override
	public int processUpload(Integer type,HttpServletRequest request) {
		//用于多文件上传的request
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		//分隔符/
		String sep = "/";
		//获取所有上传的文件
		Map<?, ?> files = multipartRequest.getFileMap();
		//多文件所有文件名称集合
		Iterator<?> fileNames = multipartRequest.getFileNames();
		//文件id列表，通过","分隔
		String resourceLis = "";
		//文件夹是否创建成功的标志位
		boolean flag = false;
		//设置时间格式
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyyMMdd");
		//文件类型
		String folderType = "";
		if(type == 0){
			folderType = "video";
		}else if(type == 1){
			folderType = "image";
		}else if(type == 2){
			folderType = "document";
		}else if(type == 3){
			folderType = "assignment";
		}else{
			folderType = "skill";
		}
		//获取课程号
		HttpSession httpSession = request.getSession();
		TCourseSite tCourseSite = (TCourseSite)httpSession.getAttribute("currsite");
		//拼接文件夹地址
//		String fileDir = request.getSession().getServletContext().getRealPath("/") + "upload" + sep
//				+ "folder" + sep + dateformat.format(new Date()) + sep;
		String fileDir = request.getSession().getServletContext().getRealPath("/") + "upload" + sep
				+ "tcoursesite" + sep + "site" + tCourseSite.getId() + sep + folderType + sep;
		// 存放文件文件夹名称
		for (; fileNames.hasNext();) {
			String filename = (String) fileNames.next();
			//将文件转换成用于上传
			CommonsMultipartFile file = (CommonsMultipartFile) files.get(filename);
			//获得文件数据
			byte[] bytes = file.getBytes();
			if (bytes.length != 0) {
				// 说明申请有附件
				if (!flag) {
					//判断该文件夹是否存在
					File dirPath = new File(fileDir);
					if (!dirPath.exists()) {
						//该文件夹不存在，创建该文件夹
						flag = dirPath.mkdirs();
					}
				}
				//取得上传时的文件名称
				String fileTrueName = file.getOriginalFilename();
				//取得上传文件后缀
				String suffix = fileTrueName.substring(fileTrueName.lastIndexOf("."));
				// 使用UUID生成文件名称
				String logImageName = UUID.randomUUID().toString() + suffix;// 构建文件名称
				//拼成完整的文件保存路径加文件
//				String fileName = request.getSession().getServletContext().getRealPath("/upload/folder/") + sep
//						+ dateformat.format(new Date()) + sep + logImageName;
				String fileName = request.getSession().getServletContext().getRealPath("/") + "upload" + sep
						+ "tcoursesite" + sep + "site" + tCourseSite.getId() + sep + folderType + sep + logImageName;
				//通过文件保存路径新建上传文件
				File uploadFile = new File(fileName);
				try {
					//拷贝文件内容，在相应路径创建该文件
					FileCopyUtils.copy(bytes, uploadFile);
				} catch (IOException e) {
					e.printStackTrace();
				}
				//新建上传文件
				WkUpload upload = new WkUpload();
				//保存图片大小
				long a=uploadFile.length()/1024/1024;
				//文件大小的格式
				DecimalFormat fmt=new DecimalFormat("#.##");
				//文件大小
				String size="";
				//根据文件大小来添加单位M/KB
				if (a!=0) {
					size=fmt.format(a)+"M";
				}else{
					a = uploadFile.length()/1024;
					size = fmt.format(a)+"KB";
				}
				//设置上传文件大小
				upload.setSize(size);
				//设置上传文件路径
//				upload.setUrl("/upload/folder/" + dateformat.format(new Date()) + "/" + logImageName);
				upload.setUrl(sep + "upload" + sep + "tcoursesite" + sep + 
						"site" + tCourseSite.getId() + sep + folderType + sep + logImageName);
				//设置上传文件名称
				upload.setName(fileTrueName);
				//设置上传文件类型 0：视频，1：图片，2：文件
				upload.setType(type);
				//设置上传人
				upload.setUser(shareService.getUserDetail());
				//保存上传文件 返回生成的文件id
				int id = saveUpload(upload);
				//资源列表
				resourceLis = resourceLis + String.valueOf(id) + ",";
				//返回生成的文件id
				return id;
			}
		}
		return 0;
	}
	
	/**************************************************************************
	 * Description:复制文件 
	 * 
	 * @author：裴继超
	 * @date ：2016年6月15日15:01:13
	 **************************************************************************/
	@Override
	public int copyUpload(WkUpload upload,WkFolder newWkFolder) {
		
		//当前登陆用户
		User nowUser = shareService.getUserDetail();
		//新建上传文件
		WkUpload newWkUpload = new WkUpload();
		newWkUpload.setName(upload.getName());
		newWkUpload.setUrl(upload.getUrl());
		newWkUpload.setUpTime(Calendar.getInstance());
		newWkUpload.setType(upload.getType());
		newWkUpload.setDescription(upload.getDescription());
		newWkUpload.setSize(upload.getSize());
		newWkUpload.setUser(nowUser);
		newWkUpload.setWkFolder(newWkFolder);
		//保存文件
		newWkUpload = wkUploadDAO.store(newWkUpload);
		wkUploadDAO.flush();
		return newWkUpload.getId();
	}
	
	/**************************************************************************
	 * Description:知识技能体验-用户所属资源列表分页
	 * 
	 * @author：裴继超
	 * @date ：2016年7月25日9:28:59
	 **************************************************************************/
 	public List<WkUpload> findWkUploadsByUser(User user, int currpage, int pagesize, int uploadType){
 		//声明文件列表
 		List<WkUpload> list = new ArrayList<WkUpload>();
 		//根据当前用户和资源类型获取资源列表，（分页）
 		String hql = new String("select w from WkUpload w where w.user.username like '" + user.getUsername() + "'" + " and ( " + "w.type = " + uploadType + " or w.type = 101 ) group by w.url");
 		list = wkUploadDAO.executeQuery(hql, (currpage-1)*pagesize, pagesize);
 		return list;
 	}
 	
 	/**************************************************************************
	 * Description:知识技能体验-用户所属资源总数
	 * 
	 * @author：裴继超
	 * @date ：2016年7月25日9:29:03
	 **************************************************************************/
 	public int countWkUploadsByUser(User user, int uploadType){
 		//声明文件列表
 		List<WkUpload> list = new ArrayList<WkUpload>();
 		//根据当前用户和资源类型获取资源列表
 		String hql = new String("select w from WkUpload w where w.user = '" + user.getUsername() + "'" + " and " + "w.type = " + uploadType + " group by w.url");
 		list = wkUploadDAO.executeQuery(hql, 0, -1);
 		//获取资源总数
 		int count = list.size();
 		return count;
 	}
 	
 	/**************************************************************************
	 * Description:知识技能体验-查看图片
	 * 
	 * @author：裴继超
	 * @date ：2016年8月11日17:28:10
	 **************************************************************************/
 	@Override
	public Map lookImageMap(Integer folderId,int currpage, int pagesize){
 		//根据id获取文件
 		WkFolder wkFolder = wkFolderService.findWkFolderByPrimaryKey(folderId);
 		Map<String, Object> map = new HashMap<String, Object>();
 		//根据文件id查找对应的上传的文件列表
 		String sql = "select u from WkUpload u where u.wkFolder.id = " + folderId;
 		//文件列表
 		List<WkUpload> uploads = wkUploadDAO.executeQuery(sql,(currpage-1)*pagesize, pagesize);
 		// 查询的图片存在
		if (uploads.size()!=0) {
			//因为每页显示一个，所有获取第一个上传的图片
			WkUpload upload = uploads.get(0);
			// 图片路径
			map.put("imageUrlLook", upload.getUrl());
			// 图片名称
			map.put("imageNameLook", upload.getName());
		}
 		return map;
		
	}
 	
 	/**************************************************************************
	 * Description:知识技能体验-根据章节查询图片数量
	 * 
	 * @author：于侃
	 * @date ：2016年10月14日 15:33:00
	 **************************************************************************/
 	@Override
	public Integer countImageMapByChapter(Integer chapterId,Integer lessonId){
 		//根据章节查找对应的上传的图片数量
 		String sql = "";
 		if(lessonId == -1){
 			sql = "select count(u) from WkUpload u join u.wkFolder f where f.WkChapter.id = " + chapterId + " and f.WkLesson is null and u.type = 1";
 		}else{
 			sql = "select count(u) from WkUpload u join u.wkFolder f where f.WkChapter.id = " + chapterId + " and f.WkLesson.id = " + lessonId + " and u.type = 1 ";
 		}
 		int result = ((Long)wkUploadDAO.createQuerySingleResult(sql).getSingleResult()).intValue();
 		return result;
 	}
 	
 	/**************************************************************************
	 * Description:知识技能体验-根据章节查看图片
	 * 
	 * @author：于侃
	 * @date ：2016年10月14日 15:33:35
	 **************************************************************************/
 	@Override
	public Map lookImageMapByChapter(Integer chapterId,Integer lessonId,int currpage, int pagesize){
 		Map<String, Object> map = new HashMap<String, Object>();
 		//根据章节查找对应的上传的图片列表
 		String sql = "";
 		if(lessonId == -1){
	 		sql = "select u from WkUpload u join u.wkFolder f where f.WkChapter.id = " + chapterId + " and f.WkLesson is null and u.type = 1 order by f.id";
 		}else{
 			sql = "select u from WkUpload u join u.wkFolder f where f.WkChapter.id = " + chapterId + " and f.WkLesson.id = " + lessonId
	 				+ " and u.type = 1 order by f.id";
 		}
 		//图片列表
 		List<WkUpload> uploads = wkUploadDAO.executeQuery(sql,(currpage-1)*pagesize, pagesize);
 		// 查询的图片存在
		if (uploads.size()!=0) {
			//因为每页显示一个，所有获取第一个上传的图片
			WkUpload upload = uploads.get(0);
			// 图片路径
			map.put("imageUrlLook", upload.getUrl());
			// 图片名称
			map.put("imageNameLook", upload.getName());
		}
 		return map;
	}
 	
 	
 	/**************************************************************************
	 * Description:pdf转swf
	 * 
	 * @author：于侃
	 * @date ：2016年10月12日 16:46:52
	 **************************************************************************/
 	public void pdf2swf(String fileString,String rootPath) throws Exception
    {
 		//环境1：windows 2:linux(涉及pdf2swf路径问题)
 		int environment=2;
        String fileName=fileString.substring(0,fileString.lastIndexOf("."));
        File pdfFile=new File(fileName+".pdf");
        File swfFile=new File(fileName+".swf");
        Runtime r=Runtime.getRuntime();
        if(!swfFile.exists())
        {
            if(pdfFile.exists())
            {
                if(environment==1)//windows环境处理
                {
                    try {
                        Process p=r.exec("xidlims/SWFTools/pdf2swf.exe "+pdfFile.getPath()+" -o "+swfFile.getPath()+" -T 9");
                        System.out.print(loadStream(p.getInputStream()));
                        System.err.print(loadStream(p.getErrorStream()));
                        System.out.print(loadStream(p.getInputStream()));
                        //System.err.println("****swf转换成功，文件输出："+swfFile.getPath()+"****");
                    } catch (Exception e) {
                        e.printStackTrace();
                        throw e;
                    }
                }
                else if(environment==2)//linux环境处理
                {
                    try {
                        Process p=r.exec(rootPath+"SWFTools/pdf2swf.exe "+pdfFile.getPath()+" -o "+swfFile.getPath()+" -T 9");
//                        System.out.print(loadStream(p.getInputStream()));
//                        System.err.print(loadStream(p.getErrorStream()));
//                        System.err.println("****swf转换成功，文件输出："+swfFile.getPath()+"****");
                        loadStream(p.getInputStream());
                        loadStream(p.getErrorStream());
                    } catch (Exception e) {
                        e.printStackTrace();
                        throw e;
                    }
                }
            }
            else {
                System.out.println("****pdf不存在，无法转换****");
            }
        }
        else {
            System.out.println("****swf已存在不需要转换****");
        }
    }
 	
 	public String loadStream(InputStream in) throws IOException
    {
        int ptr=0;
        in=new BufferedInputStream(in);
        StringBuffer buffer=new StringBuffer();
        
        while((ptr=in.read())!=-1)
        {
            buffer.append((char)ptr);
        }
        return buffer.toString();
    }
 	
 	/**************************************************************************
	 * Description:微课-查找章节下的图片
	 * 
	 * @author：裴继超
	 * @date ：2016-11-24
	 **************************************************************************/
 	@Override
	public List<WkUpload> findImagesByChapterId(Integer chapterId){
		
		List<WkUpload> images = new ArrayList(); 
		String sql = "from WkUpload u where u.wkFolder.WkChapter.id = " + chapterId;
		sql += " and u.wkFolder.type = 2";
		images = wkUploadDAO.executeQuery(sql, 0,-1);
		return images;
		
	}
}