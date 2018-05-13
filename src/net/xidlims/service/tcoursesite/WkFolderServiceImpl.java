package net.xidlims.service.tcoursesite;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.xidlims.dao.WkChapterDAO;
import net.xidlims.dao.WkFolderDAO;
import net.xidlims.domain.TAssignment;
import net.xidlims.domain.TAssignmentAnswerAssign;
import net.xidlims.domain.TAssignmentControl;
import net.xidlims.domain.TCourseSite;
import net.xidlims.domain.User;
import net.xidlims.domain.WkChapter;
import net.xidlims.domain.WkFolder;
import net.xidlims.domain.WkLesson;
import net.xidlims.domain.WkUpload;
import net.xidlims.service.common.ShareService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.inject.persist.Transactional;

@Service("WkFolderService")
public class WkFolderServiceImpl implements  WkFolderService {
	@Autowired
	private WkChapterDAO wkChapterDAO;
	@Autowired
	private WkFolderDAO wkFolderDAO;
	@Autowired
	private WkChapterService wkChapterService;
	@Autowired
	private WkLessonService wkLessonService;
	@Autowired
	private ShareService shareService;
	@Autowired
	private TAssignmentService tAssignmentService;
	@Autowired
	private WkUploadService wkUploadService;
	@Autowired
	private WkService wkService;
	
	/**************************************************************************
	 * Description:保存文件夹
	 * 
	 * @author：裴继超
	 * @date ：2016年4月29日9:50:57
	 **************************************************************************/
	@Override
	public WkFolder saveWkFolder(WkFolder wkFolder,HttpServletRequest request) {
		//保存文件夹
		wkFolder = wkFolderDAO.store(wkFolder);
		//分隔符/
		String sep = "/";
		//获取课程号
		HttpSession httpSession = request.getSession();
		TCourseSite tCourseSite = (TCourseSite)httpSession.getAttribute("currsite");
		//获取章节类型
		Integer moduleType = 0;
		if(wkFolder.getWkChapter()!=null){
			moduleType = wkFolder.getWkChapter().getType();
		}else{
			moduleType = wkFolder.getWkLesson().getWkChapter().getType();
		}
		//文件类型
		String folderType = "";
		if(wkFolder.getType() == 1){
			folderType = "video";
		}else if(wkFolder.getType() == 2){
			folderType = "image";
		}else if(wkFolder.getType() == 3){
			folderType = "document";
		}else{
			folderType = "skill";
		}
		if(folderType.equals("video")){
			// 后台刷新二维码
			String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();// 得到网址
			String contents = url + request.getContextPath() + "/tcoursesite/videoLook?folderId=" + wkFolder.getId() + "&moduleType=" + moduleType; // 编辑二维码地址，手机端
			String logoRealPathDir = request.getSession().getServletContext().getRealPath("/");// 服务器文件路径
			String imgPath = logoRealPathDir + "upload" + sep + "tcoursesite" + sep + 
					"site" + tCourseSite.getId() + sep + folderType + sep + "folder" + wkFolder.getId() + ".png";
			
			
			int width = 300, height = 300; // 设置二维码尺寸
			wkService.encode(contents, width, height, imgPath);
			
			wkFolder.setqRCodeUrl("/upload" + sep + "tcoursesite" + sep + 
					"site" + tCourseSite.getId() + sep + folderType + sep + "folder" + wkFolder.getId() + ".png");
			wkFolderDAO.store(wkFolder);
		}
		return wkFolder;
	}

	/**************************************************************************
	 * Description:根据主键查询文件夹
	 * 
	 * @author：裴继超
	 * @date ：2016年4月29日9:50:57 
	 **************************************************************************/
	@Override
	public WkFolder findWkFolderByPrimaryKey(Integer wkFolderId) {
		//根据主键查询文件夹
		return wkFolderDAO.findWkFolderByPrimaryKey(wkFolderId);
	}
	/**************************************************************************
	 * Description:删除文件夹
	 * 
	 * @author：裴继超
	 * @date ：2016年4月29日9:50:57
	 **************************************************************************/
	@Override
	public void deleteWkFolder(WkFolder wkFolder) {
		//删除文件夹
		wkFolderDAO.remove(wkFolder);
		wkFolderDAO.flush();
	}
	
	/**************************************************************************
	 * Description:复制文件夹资源
	 * 
	 * @author：裴继超
	 * @date ：2016年6月15日9:59:03
	 **************************************************************************/
	@Override
	public int copyWkFolder(Integer tCourseSiteId,Integer wkFolderId,Integer wkChapterId,
			Integer wkLessonId,HttpServletRequest request) {
		// 当前登录人
		User user = shareService.getUser();
		//通过id查找文件夹
		WkFolder wkFolder = findWkFolderByPrimaryKey(wkFolderId);
		//根据章节id查找章节
		WkChapter wkChapter = wkChapterService.findChapterByPrimaryKey(wkChapterId);
		//根据课时id查找课时
		WkLesson wkLesson = wkLessonService.findWkLessonByPrimaryKey(wkLessonId);
		//新建文件夹
		WkFolder newWkFolder = new WkFolder();
		newWkFolder.setName(wkFolder.getName());
		newWkFolder.setUser(user);
		newWkFolder.setCreateTime(Calendar.getInstance());
		newWkFolder.setType(wkFolder.getType());
		newWkFolder.setVideoUrl(wkFolder.getVideoUrl());
		
		if(wkFolder.getWkChapter()!=null){
			newWkFolder.setWkChapter(wkChapter);
		}
		if(wkFolder.getWkLesson()!=null){
			newWkFolder.setWkLesson(wkLesson);
		}
		//保存文件夹
		newWkFolder = saveWkFolder(newWkFolder,request);
		//复制作业，考试，测试
		if(wkFolder.getType()==4||wkFolder.getType()==5||wkFolder.getType()==6){
			tAssignmentService.copyTAssignment(wkFolder, newWkFolder,tCourseSiteId);
		}
		//复制视频，图片，文件
		if(wkFolder.getType()==1||wkFolder.getType()==2||wkFolder.getType()==3){
			if(wkFolder.getUploads().size()!=0){
				for(WkUpload u1:wkFolder.getUploads()){
					wkUploadService.copyUpload(u1, newWkFolder);
				}
			}
			//复制第一层子文件夹
			if(wkFolder.getWkFolders().size()!=0){
				for(WkFolder f1:wkFolder.getWkFolders()){
					WkFolder newF1 = copyFolder(f1,newWkFolder,request);
					if(f1.getUploads().size()!=0){
						for(WkUpload u2:f1.getUploads()){
							wkUploadService.copyUpload(u2, newF1);
						}
					}
					//复制第二层子文件夹
					if(f1.getWkFolders().size()!=0){
						for(WkFolder f2:f1.getWkFolders()){
							WkFolder newF2 = copyFolder(f2,newF1,request);
							if(f2.getUploads().size()!=0){
								for(WkUpload u3:f2.getUploads()){
									wkUploadService.copyUpload(u3, newF2);
								}
							}
						}
					}
				}
			}
		}
		return newWkFolder.getId();
	}
	
	/**************************************************************************
	 * Description:复制文件夹
	 * 
	 * @author：裴继超
	 * @date ：2016年6月15日15:31:46
	 **************************************************************************/
	@Override
	public WkFolder copyFolder(WkFolder folder,WkFolder newParentFolder,HttpServletRequest request) {
		//当前登陆人
		User user = shareService.getUser();
		//新建文件夹
		WkFolder newFolder = new WkFolder();
		newFolder.setName(folder.getName());
		newFolder.setUser(user);
		newFolder.setCreateTime(Calendar.getInstance());
		newFolder.setType(folder.getType());
		newFolder.setVideoUrl(folder.getVideoUrl());
		newFolder.setWkFolder(newParentFolder);
		//保存文件夹
		newFolder = saveWkFolder(newFolder,request);
		return newFolder;
	}
	
	/**************************************************************************
	 * Description:查找资源文件夹个数
	 * 
	 * @author：裴继超
	 * @date ：2016年7月9日20:26:34
	 **************************************************************************/
	@Override
	public int findFileFolderSize(Integer tCourseSiteId) {
		//章节下文件夹个数
		String sql1 = "select count(*) from WkFolder f where f.WkLesson.wkChapter.TCourseSite.id = " + tCourseSiteId  ;
		int size1 =  ((Long) wkFolderDAO.createQuerySingleResult(sql1.toString()).getSingleResult()).intValue();
		//学习单元下文件夹个数
		String sql2 = "select count(*) from WkFolder f where f.WkChapter.TCourseSite.id = " + tCourseSiteId ;
		int size2 = ((Long) wkFolderDAO.createQuerySingleResult(sql2.toString()).getSingleResult()).intValue();
		return size1 + size2;
	}
	
	/**************************************************************************
	 * Description:知识技能体验-ajax根据课时id和获取文件夹列表map
	 * 
	 * @author：裴继超
	 * @date ：2016年8月23日13:45:10
	 **************************************************************************/
	@Override
	public Map findFolderMapByLessonId(Integer lessonId){
		Map<Integer, String> map = new HashMap<Integer, String>();
		//根据课时id获取对应文件夹列表
		String sql = "select c from WkFolder c where c.wkLesson.id = " + lessonId +
				" or c.wkFolder.wkLesson.id = " + lessonId +
				" or c.wkFolder.wkFolder.wkLesson.id = " + lessonId ;
		List<WkFolder> folders = wkFolderDAO.executeQuery(sql, 0,-1);
		//获取每个文件夹的id和名称
		for(WkFolder c:folders){
			map.put(c.getId(), c.getName());
		}
		return map;
	}
	
	/**************************************************************************
	 * Description:实验技能-保存资源文件夹
	 * 
	 * @author：裴继超
	 * @date ：2016-9-28
	 **************************************************************************/
	@Override
	public WkFolder saveTExperimentSkillWkFolder(WkChapter chapter,String name,Integer type) {
		WkFolder wkFolder = new WkFolder();//新建文件夹
		String sql = "select f from WkFolder f where f.type = " + type +" and f.WkChapter.id = " + chapter.getId() ;
		List<WkFolder> folderList = wkFolderDAO.executeQuery(sql, 0,-1);
		if(folderList.size()!=0){//判断是否该文件夹已经存在
			wkFolder = folderList.get(0);
		}
		wkFolder.setName(name);//设置名字
		wkFolder.setCreateTime(Calendar.getInstance());//设置时间
		wkFolder.setType(type);//设置类型
		wkFolder.setUser(shareService.getUserDetail());//设置创建者
		wkFolder.setWkChapter(chapter);//设置所属chapter
		//保存文件夹
		return wkFolderDAO.store(wkFolder);
	}
	
	/**************************************************************************
	 * Description:保存文件夹
	 * 
	 * @author：裴继超
	 * @date ：2016年4月29日9:50:57
	 **************************************************************************/
	@Override
	public String batchMadeQRCode(HttpServletRequest request) {
		Set<WkFolder> wkFolders = wkFolderDAO.findAllWkFolders();
		//保存文件夹
		for(WkFolder f:wkFolders){
			//设置课程号
			Integer siteId = 0;
			Integer moduleType = 0;
			//设置章节类型
			if(f.getWkChapter()!=null){
				siteId = f.getWkChapter().getTCourseSite().getId();
				moduleType = f.getWkChapter().getType();
			}else{
				siteId = f.getWkLesson().getWkChapter().getTCourseSite().getId();
				moduleType = f.getWkLesson().getWkChapter().getType();
			}
			
			//文件类型
			String folderType = "";
			if(f.getType() == 1){
				folderType = "video";
			}else if(f.getType() == 2){
				folderType = "image";
			}else if(f.getType() == 3){
				folderType = "document";
			}else{
				folderType = "skill";
			}
			if(folderType.equals("video")){
				//分隔符/
				String sep = "/";
				//拼接文件夹地址
				String fileDir = request.getSession().getServletContext().getRealPath("/") + "upload" + sep
						+ "tcoursesite" + sep + "site" + siteId + sep + folderType + sep;
				// 后台刷新二维码
				String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();// 得到网址
				String contents = url + request.getContextPath() + "/tcoursesite/videoLook?folderId=" + f.getId() + "&moduleType=" + moduleType; // 编辑二维码地址，手机端
				String logoRealPathDir = request.getSession().getServletContext().getRealPath("/");// 服务器文件路径
				String imgPath = logoRealPathDir + "upload" + sep + "tcoursesite" + sep + 
						"site" + siteId + sep + folderType + sep + "folder" + f.getId() + ".png";
				int width = 300, height = 300; // 设置二维码尺寸
				wkService.encode(contents, width, height, imgPath);
				f.setqRCodeUrl("/upload" + sep + "tcoursesite" + sep + 
						"site" + siteId + sep + folderType + sep + "folder" + f.getId() + ".png");
				wkFolderDAO.store(f);
			}
		}
		return "ok";
	}
	
	/**************************************************************************
	 * Description:可视化-根据类型获取文件夹
	 * 
	 * @author：裴继超
	 * @date ：2016-11-9
	 **************************************************************************/
	@Override
	public List<WkFolder> findFoldersByType(Integer type) {
		String sql = "from WkFolder f where f.type = " + type;
		List<WkFolder> wkFolders = wkFolderDAO.executeQuery(sql, 0,-1);
		return wkFolders;
	}
	
	
	
	
}