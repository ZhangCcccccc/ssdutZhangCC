/****************************************************************************
 * 命名规范：
 * 本控制层调整必须是按照如下的路径调整：@RequestMapping("/tcoursesite/upload/xxxx")。
 * 列表信息listxxxx，獲取信息：getxxxx 编辑信息：editxxxx 删除信息：detelexxxx 新增信息 newxxxx
 * 导出信息exportxxxx 保存信息：savexxxx 
 ****************************************************************************/

package net.xidlims.web.tcoursesite;

import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.xidlims.dao.WkFolderDAO;
import net.xidlims.dao.TCourseSiteDAO;
import net.xidlims.dao.TCourseSiteUserDAO;
import net.xidlims.dao.WkCourseDAO;
import net.xidlims.dao.WkLessonDAO;
import net.xidlims.dao.WkUploadDAO;
import net.xidlims.domain.Authority;
import net.xidlims.domain.LabCenter;
import net.xidlims.domain.SchoolAcademy;
import net.xidlims.domain.TAssignment;
import net.xidlims.domain.TCourseSite;
import net.xidlims.domain.TCourseSiteUser;
import net.xidlims.domain.User;
import net.xidlims.domain.WkChapter;
import net.xidlims.domain.WkFolder;
import net.xidlims.domain.WkLesson;
import net.xidlims.domain.WkUpload;
import net.xidlims.service.common.ShareService;
import net.xidlims.service.lab.LabCenterService;
import net.xidlims.service.tcoursesite.TAssignmentService;
import net.xidlims.service.tcoursesite.WkChapterService;
import net.xidlims.service.tcoursesite.WkFolderService;
import net.xidlims.service.tcoursesite.WkLessonService;
import net.xidlims.service.tcoursesite.WkService;
import net.xidlims.service.tcoursesite.WkUploadService;
import net.xidlims.service.tcoursesite.TCourseSiteService;
import net.xidlims.web.aop.SystemServiceLog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

/****************************************************************************
 * 功能：系统后台管理模块 作者：魏诚 时间：2014-07-14
 ****************************************************************************/
@Controller("WkFolderController")
public class WkFolderController<JsonResult> {
	@InitBinder
	public void initBinder(WebDataBinder binder, HttpServletRequest request) { // Register
																				// static
																				// property
																				// editors.
		binder.registerCustomEditor(java.util.Calendar.class,
				new org.skyway.spring.util.databinding.CustomCalendarEditor());
		binder.registerCustomEditor(byte[].class,
				new org.springframework.web.multipart.support.ByteArrayMultipartFileEditor());
		binder.registerCustomEditor(Boolean.class, new org.skyway.spring.util.databinding.EnhancedBooleanEditor(true));
		binder.registerCustomEditor(java.math.BigDecimal.class,
				new org.skyway.spring.util.databinding.NaNHandlingNumberEditor(java.math.BigDecimal.class, true));
		binder.registerCustomEditor(Integer.class, new org.skyway.spring.util.databinding.NaNHandlingNumberEditor(
				Integer.class, true));
		binder.registerCustomEditor(java.util.Date.class, new org.skyway.spring.util.databinding.CustomDateEditor());
		binder.registerCustomEditor(String.class, new org.skyway.spring.util.databinding.StringEditor());
		binder.registerCustomEditor(Long.class, new org.skyway.spring.util.databinding.NaNHandlingNumberEditor(
				Long.class, true));
		binder.registerCustomEditor(Double.class, new org.skyway.spring.util.databinding.NaNHandlingNumberEditor(
				Double.class, true));
	}

	@Autowired
	private ShareService shareService;
	@Autowired
	private LabCenterService labCenterService;
	@Autowired
	private TCourseSiteService tCourseSiteService;
	@Autowired
	private TCourseSiteUserDAO tCourseSiteUserDAO;
	@Autowired
	private TCourseSiteDAO tCourseSiteDAO;
	@Autowired
	private WkCourseDAO wkCourseDAO;
	@Autowired
	private WkLessonDAO wkLessonDAO;
	@Autowired
	private WkChapterService wkChapterService;
	@Autowired
	private WkLessonService wkLessonService;
	@Autowired
	private WkService wkService;
	@Autowired
	private WkUploadDAO wkUploadDAO;
	@Autowired
	private WkFolderDAO folderDAO;
	@Autowired
	private WkUploadService wkUploadService;
	@Autowired
	private WkFolderService wkFolderService;
	@Autowired
	private TAssignmentService tAssignmentService;;
	
	/**************************************************************************
	 * Description:视频文件夹更换视频
	 * 
	 * @author：裴继超
	 * @date ：2016年5月5日11:11:06
	 **************************************************************************/	
	@SystemServiceLog("编辑视频")
	@RequestMapping("/tcoursesite/editVideoFolder")
	@ResponseBody
	public Map<String, Object> editVideoFolder(@RequestParam Integer folderId) {
		WkFolder wkFolder = wkFolderService.findWkFolderByPrimaryKey(folderId);
		Map<String, Object> map = new HashMap<String, Object>();
		if (wkFolder.getUploads().size()!=0) {// 假如有视频
			WkUpload upload = wkFolder.getUploads().iterator().next();
			map.put("videoId", upload.getId());// 视频id
			map.put("videoName", upload.getName());// 视频名称
		}
		map.put("videoFolderId", (wkFolder.getId()==null)?"":wkFolder.getId()); // 视频文件夹i
		map.put("videoFolderName", (wkFolder.getName()==null)?"":wkFolder.getName()); // 视频文件夹名字
		map.put("videoUrl", (wkFolder.getVideoUrl()==null)?"":wkFolder.getVideoUrl()); // 在线视频
		return map;
	}
	

	/**************************************************************************
	 * Description:保存视频文件夹
	 * 
	 * @author：于侃
	 * @date ：2016-09-08
	 **************************************************************************/
	@SystemServiceLog("保存视频")
	@ResponseBody
	@RequestMapping("/tcoursesite/saveVideoFolder")
	public  ModelAndView saveVideoFolder(@ModelAttribute WkFolder wkFolder,@RequestParam Integer tCourseSiteId,Integer moduleType,HttpServletRequest request,Integer selectId) {
		ModelAndView mav = new ModelAndView();
		//当前登陆用户
		User nowUser = shareService.getUserDetail();
		wkFolder.setType(1);
		wkFolder.setUser(nowUser);
		if(wkFolder.getWkLesson().getId()!=-1){
			wkFolder.setWkChapter(null);
		}else{
			wkFolder.setWkLesson(null);
		}
		wkFolder = wkFolderService.saveWkFolder(wkFolder,request);
		mav.setViewName("redirect:/tcoursesite/chaptersList?tCourseSiteId=" + tCourseSiteId + "&moduleType=" + moduleType +"&selectId="+selectId);
		//文件夹下属视频添加文件夹外键
		String videoIdStrings = request.getParameter("videoId");
		String[] videoIdString = videoIdStrings.split(",");
		for(String s:videoIdString){
			int videoId = 0;
			if(s==null||s.equals("")){
				//网路视屏
				String videoUrl = wkFolder.getVideoUrl();
				WkUpload upload=new WkUpload();
				upload.setName(wkFolder.getName());
				upload.setUrl(videoUrl);
				upload.setType(0);
				upload.setUpTime(Calendar.getInstance());
				upload.setUser(nowUser);
				wkUploadDAO.flush();
				WkUpload up = wkUploadDAO.store(upload);
				//return mav;
				videoId=up.getId();
			}else{
				videoId = Integer.parseInt(s);
			}
			WkUpload video = wkUploadService.findUploadByPrimaryKey(videoId);
			video.setWkFolder(wkFolder);
			videoId = wkUploadService.saveUpload(video);
		}
		return mav;
	}
	

	/**************************************************************************
	 * Description:保存图片文件夹
	 * 
	 * @author：于侃
	 * @date ：2016-09-08
	 **************************************************************************/
	@SystemServiceLog("保存图片")
	@ResponseBody
	@RequestMapping("/tcoursesite/saveImageFolder")
	public  ModelAndView saveImageFolder(@ModelAttribute WkFolder wkFolder,@RequestParam Integer tCourseSiteId,Integer moduleType,HttpServletRequest request,Integer selectId) {
		ModelAndView mav = new ModelAndView();
		//当前登陆用户
		User nowUser = shareService.getUserDetail();
		wkFolder.setType(2);
		wkFolder.setUser(nowUser);
		if(wkFolder.getWkLesson().getId()!=-1){
			wkFolder.setWkChapter(null);
		}else{
			wkFolder.setWkLesson(null);
		}
		wkFolder = wkFolderService.saveWkFolder(wkFolder,request);
		mav.setViewName("redirect:/tcoursesite/chaptersList?tCourseSiteId=" + tCourseSiteId + "&moduleType=" + moduleType +"&selectId="+selectId);
		//文件夹下属视频添加文件夹外键
		String idString = request.getParameter("imagesList");
		if (idString != null && idString.length() > 0 && idString.indexOf(",") != -1) {// 字符串列表判空
			String[] s = idString.split(",");
			for (String id : s) {
				// System.out.println(string);
				if (id != null && id.length() > 0) {// 资源id判空
					WkUpload image = wkUploadService.findUploadByPrimaryKey(Integer.valueOf(id));
					image.setWkFolder(wkFolder);
					wkUploadService.saveUpload(image);
				}
			}
		}
		
				String[] idString2 = request.getParameterValues("checkname");
					if(idString2 != null && idString2.length > 0){
						for(String id : idString2){
								if (id != null && id.length() > 0) {// 资源id判空
									WkUpload image = wkUploadService.findUploadByPrimaryKey(Integer.valueOf(id));
									wkUploadService.copyUpload(image, wkFolder);
								}
							}
					}
		
		//mav.setViewName("redirect:tcoursesite?tCourseSiteId="+wkFolder.getWkChapter().getTCourseSite().getId());
		return mav;
	}
	

	/**************************************************************************
	 * Description:保存文件文件夹
	 * 
	 * @author：于侃
	 * @date ：2016-09-08
	 **************************************************************************/
	@SystemServiceLog("保存文件")
	@ResponseBody
	@RequestMapping("/tcoursesite/saveDocumentFolder")
	public  ModelAndView saveDocumentFolder(@ModelAttribute WkFolder wkFolder,@RequestParam Integer tCourseSiteId,Integer moduleType,HttpServletRequest request,Integer selectId) {
		ModelAndView mav = new ModelAndView();
		//当前登陆用户
		User nowUser = shareService.getUserDetail();
		wkFolder.setType(3);
		wkFolder.setUser(nowUser);
		if(wkFolder.getWkLesson().getId()==-1){
			wkFolder.setWkLesson(null);
		}
		wkFolder = wkFolderService.saveWkFolder(wkFolder,request);
		mav.setViewName("redirect:/tcoursesite/chaptersList?tCourseSiteId=" + tCourseSiteId + "&moduleType=" + moduleType +"&selectId="+selectId);
		//文件夹下属文件添加文件夹外键
		String idString = request.getParameter("documentsList");
		if (idString != null && idString.length() > 0 && idString.indexOf(",") != -1) {// 字符串列表判空
			String[] s = idString.split(",");
			for (String id : s) {
				// System.out.println(string);
				if (id != null && id.length() > 0) {// 资源id判空
					WkUpload document = wkUploadService.findUploadByPrimaryKey(Integer.valueOf(id));
					document.setWkFolder(wkFolder);
					wkUploadService.saveUpload(document);
				}
			}
		}
		String[] idString2 = request.getParameterValues("checkname");
				if(idString2 != null && idString2.length > 0){
					for(String id : idString2){
							if (id != null && id.length() > 0) {// 资源id判空
								WkUpload document = wkUploadService.findUploadByPrimaryKey(Integer.valueOf(id));
								wkUploadService.copyUpload(document, wkFolder);
							}
						}
				}
		//mav.setViewName("redirect:tcoursesite?tCourseSiteId="+wkFolder.getWkChapter().getTCourseSite().getId());
		return mav;
	}
	/**************************************************************************
	 * Description:删除文件夹
	 * 
	 * @author：裴继超
	 * @date ：2016年4月29日14:40:07
	 **************************************************************************/
	@SystemServiceLog("删除文件夹")
	@RequestMapping("/tcoursesite/deleteFolder")
	public @ResponseBody String deleteFolder(@RequestParam Integer folderId,@RequestParam Integer tCourseSiteId, Integer moduleType,HttpServletRequest request) {
		WkFolder folder = wkFolderService.findWkFolderByPrimaryKey(folderId);
		wkFolderService.deleteWkFolder(folder);
		
		return "success";
	}
	/**************************************************************************
	 * Description:编辑文件
	 * 
	 * @author：裴继超
	 * @date ： 2016年5月5日11:11:06
	 **************************************************************************/

	@SystemServiceLog("编辑文件")
	@RequestMapping("/tcoursesite/editDocumentFolder")
	@ResponseBody
	public Map<String, Object> editDocumentVideo(@RequestParam Integer folderId) {
		WkFolder wkFolder = wkFolderService.findWkFolderByPrimaryKey(folderId);
		Map<String, Object> map = new HashMap<String, Object>();
		if (wkFolder.getUploads().size()!=0) {// 假如有视频
			String documentsList = "";
			String documentsNameList = "";
			for(WkUpload u:wkFolder.getUploads()){
				documentsList = documentsList + u.getId()+",";
				documentsNameList = documentsNameList + 
						"<li class='pic_list hg9 lh35 ptb5 rel ovh' id='document"+u.getId()+"'>" +
        				"<div class='cf rel zx1 z c_category'>" +
        				"<div class=''l mlr15 cc1 c_tool poi'>" +
        				u.getName() +
        				"<i class='fa fa-trash-o g9 f14 mr5 poi' onclick='deleteThisDocument("+u.getId()+")'></i>"+
        				"</div></div></li>";
			}
			WkUpload upload = wkFolder.getUploads().iterator().next();
			map.put("documentsList", documentsList);// 文件夹id
			map.put("documentsNameList", documentsNameList);// 文件夹名称
		}
		map.put("documentFolderId", (wkFolder.getId()==null)?"":wkFolder.getId()); // 文件夹id
		map.put("documentFolderName", (wkFolder.getName()==null)?"":wkFolder.getName()); // 文件夹名字
		return map;
	}
	/**************************************************************************
	 * Description:知识技能体验-编辑图片
	 * 
	 * @author：裴继超
	 * @date ： 2016年5月5日11:11:06
	 **************************************************************************/
	@SystemServiceLog("编辑图片")
	@RequestMapping("/tcoursesite/editImageFolder")
	@ResponseBody
	public Map<String, Object> editImageFolder(@RequestParam Integer folderId) {
		WkFolder wkFolder = wkFolderService.findWkFolderByPrimaryKey(folderId);
		Map<String, Object> map = new HashMap<String, Object>();
		if (wkFolder.getUploads().size()!=0) {// 假如有视频
			String imagesList = "";
			String imagesNameList = "";
			for(WkUpload u:wkFolder.getUploads()){
				imagesList = imagesList + u.getId()+",";
				imagesNameList = imagesNameList + 
						"<li class='pic_list hg9 lh35 ptb5 rel ovh'>" +
        				"<div class='cf rel zx1 z c_category'>" +
        				"<div class=''l mlr15 cc1 c_tool poi'>" +
        				u.getName() +
        				"</div></div></li>";
			}
			WkUpload upload = wkFolder.getUploads().iterator().next();
			map.put("imagesList", imagesList);// 图片id
			map.put("imagesNameList", imagesNameList);// 图片名称
			
		}
		map.put("imageFolderId", (wkFolder.getId()==null)?"":wkFolder.getId());//文件夹id
		map.put("imageFolderName", (wkFolder.getName()==null)?"":wkFolder.getName()); // 文件夹名字
		return map;
	}
	
	/****************************************************************************
	 * description:知识-批量删除文件夹
	 * 
	 * @author:于侃
	 * @date:2016年8月29日
	 ****************************************************************************/
	@SystemServiceLog("批量删除文件夹")
	@RequestMapping("/tcoursesite/deleteFolders")
	public ModelAndView deleteFolders(@RequestParam Integer tCourseSiteId,Integer moduleType,HttpServletRequest request,Integer selectId) {
		ModelAndView mav = new ModelAndView();
		String[] folders = request.getParameterValues("checknames");
		WkFolder folder = new WkFolder();
		if(folders!=null){
			//删除文件夹
			for(String i:folders){
				folder = wkFolderService.findWkFolderByPrimaryKey(Integer.parseInt(i));
				wkFolderService.deleteWkFolder(folder);
			}
		}
	//	return "success";
		mav.setViewName("redirect:/tcoursesite/chaptersList?tCourseSiteId=" + tCourseSiteId + "&moduleType=" + moduleType +"&selectId="+selectId);
		return mav;
	}
	
	/****************************************************************************
	 * description:知识-批量删除文件
	 * 
	 * @author:于侃
	 * @date:2016年8月29日
	 ****************************************************************************/
	@SystemServiceLog("批量删除文件")
	@RequestMapping("/tcoursesite/deleteUploads")
	public ModelAndView deleteUploads(@RequestParam Integer tCourseSiteId,Integer moduleType,HttpServletRequest request,Integer selectId) {
		ModelAndView mav = new ModelAndView();
		String[] files = request.getParameterValues("checknames");
		WkUpload upload = new WkUpload();
		TAssignment tAssignment = new TAssignment();
		if(files!=null){
			//删除文件
			for(String f:files){
				if(f.indexOf("upload") > -1){
					upload = wkUploadService.findUploadByPrimaryKey(Integer.parseInt(f.substring(f.indexOf("upload")+6)));
					wkUploadService.deleteWkUpload(upload);
				}else{
					tAssignment = tAssignmentService.findTAssignmentById(Integer.parseInt(f.substring(f.indexOf("assignment")+10)));
					tAssignmentService.deleteTAssignment(tAssignment);
				}
			}
		}
		mav.setViewName("redirect:/tcoursesite/chaptersList?tCourseSiteId=" + tCourseSiteId + "&moduleType=" + moduleType +"&selectId="+selectId);
		return mav;
	}
	
	/**************************************************************************
	 * Description:知识-批量生成视频二维码
	 * 
	 * @author：裴继超
	 * @date ：2016-11-9
	 **************************************************************************/	
	@RequestMapping("/tcoursesite/batchMadeQRCode")
	@ResponseBody
	public String batchMadeQRCode(HttpServletRequest request) {
		
		String result = wkFolderService.batchMadeQRCode(request);
		
		return result;
	}

}