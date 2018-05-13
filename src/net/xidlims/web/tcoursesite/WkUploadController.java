/****************************************************************************
 * 命名规范：
 * 本控制层调整必须是按照如下的路径调整：@RequestMapping("/tcoursesite/upload/xxxx")。
 * 列表信息listxxxx，獲取信息：getxxxx 编辑信息：editxxxx 删除信息：detelexxxx 新增信息 newxxxx
 * 导出信息exportxxxx 保存信息：savexxxx 
 ****************************************************************************/

package net.xidlims.web.tcoursesite;

import java.text.SimpleDateFormat;
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
import net.xidlims.domain.TCourseSite;
import net.xidlims.domain.TCourseSiteUser;
import net.xidlims.domain.User;
import net.xidlims.domain.WkChapter;
import net.xidlims.domain.WkFolder;
import net.xidlims.domain.WkLesson;
import net.xidlims.domain.WkUpload;
import net.xidlims.service.common.ShareService;
import net.xidlims.service.lab.LabCenterService;
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
@Controller("WkUploadController")
public class WkUploadController<JsonResult> {
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
	
	/****************************************************************************
	 * description:处理课时编辑页面ajax发来的课时视频
	 * 
	 * author:裴继超
	 * date:2016年5月5日11:40:54
	 ****************************************************************************/
	@RequestMapping("/tcoursesite/fileUpload")
	public @ResponseBody String fileUpload(@RequestParam Integer type,HttpServletRequest request) throws Exception {
		int id =  wkUploadService.processUpload(type,request);
		return String.valueOf(id);
	}
	
	
	
	/****************************************************************************
	 * description:保存课时图片
	 * 
	 * author:裴继超
	 * date:2016年5月5日11:40:54
	 ****************************************************************************/
	@RequestMapping("/tcoursesite/saveImageUpload")
	public String saveLessonPic(@ModelAttribute WkLesson lesson) {
		int lessonId = lesson.getId();
		WkLesson extLesson = wkLessonService.findWkLessonByPrimaryKey(lessonId);// 找出存在课时
		extLesson.setSourceList(lesson.getSourceList());// 修改图片

		wkLessonService.saveLesson(extLesson);// 保存课时
		int courseId = extLesson.getWkChapter().getTCourseSite().getId();
		return "redirect:wk/admin/course/" + courseId + "/lesson";// 返回课程编辑页面
	}
	
	/****************************************************************************
	 * description:课时学习 查看视频 输入:课时id
	 * 
	 * author:裴继超
	 * date:2016年6月3日11:29:56
	 ****************************************************************************/
	@SystemServiceLog("查看视频")
	@ResponseBody
	@RequestMapping("/tcoursesite/videoLook")
	public ModelAndView videoLook(HttpServletRequest request, @RequestParam Integer folderId,Integer moduleType) {
		String ip = shareService.getIpAddr(request);// ip
		ModelAndView mav = new ModelAndView();
		mav.setViewName("tcoursesite/video.jsp");
		WkFolder wkFolder = wkFolderService.findWkFolderByPrimaryKey(folderId);
		if (wkFolder.getUploads().size()!=0) {// 对课时的视频判空
			WkUpload upload = null;
			for(WkUpload u:wkFolder.getUploads()){
				upload = u;
			}
			mav.addObject("upload", upload);//上传视屏
			mav.addObject("videoType", "local");//标记为本地
		} else {
			mav.addObject("videoType", "online");//标记为在线
			String videoUrl = wkFolder.getVideoUrl();// 在线视频的网址

			System.out.println(videoUrl);
			// System.out.println(url.indexOf(".html"));
			if (videoUrl != null && videoUrl.length() > 0) {// 对在线网址判空
				int index = videoUrl.indexOf(".html");// 获取索引值
				String uuid = videoUrl.substring(index - 17, index-2);// 得到视频uuid
				// url="http://player.youku.com/player.php/sid/"+uuid+"/v.swf";//拼接真实flash网址
				System.out.println(uuid);
				// http://player.youku.com/player.php/Type/Folder/Fid/22233099/Ob/1/sid/XNzExMTU3NzQ4/v.swf
				mav.addObject("videoUrlPc", "http://player.youku.com/player.php/sid/" + uuid + "/v.swf");
				mav.addObject("videoUrlMobile", "http://player.youku.com/embed/" + uuid);
				// <iframe height=498 width=510
				// src="http://player.youku.com/embed/XNzExMTU4MTc2"
				// frameborder=0 allowfullscreen></iframe>
			}
		}
		return mav;
	}
	/****************************************************************************
	 * description:下载文件
	 * 
	 * author:裴继超
	 * date:2016年6月3日15:34:14
	 ****************************************************************************/

	@RequestMapping("/tcoursesite/downloadFile")
	public void downloadFile(HttpServletRequest request,HttpServletResponse response,Integer id) throws Exception {
		//id对应的文档
		WkUpload upload=wkUploadDAO.findWkUploadByPrimaryKey(id);
		wkService.downloadFile(upload, request, response);
	}
	
	/****************************************************************************
	 * description:删除文件
	 * 
	 * author:于侃
	 * date:2016年9月7日
	 ****************************************************************************/
	@RequestMapping("/tcoursesite/deleteFile")
	public @ResponseBody String deleteFile(@RequestParam Integer id,@RequestParam Integer tCourseSiteId, Integer moduleType,HttpServletRequest request) throws Exception {
		//id对应的文档
		WkUpload wkUpload =wkUploadDAO.findWkUploadById(id);
		wkUpload.setWkFolder(null);
		wkUploadDAO.store(wkUpload);
		wkUploadDAO.flush();
		return "success";
	}
	
	/****************************************************************************
	 * description:预览pdf文件
	 * 
	 * author:于侃
	 * date:2016年10月12日 16:38:06
	 ****************************************************************************/
	@RequestMapping("/tcoursesite/showFile")
	public ModelAndView showFile(@RequestParam Integer id,HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();
		//id对应的文档
		WkUpload wkUpload =wkUploadDAO.findWkUploadById(id);
		String rootPath = request.getSession().getServletContext().getRealPath("/");
		wkUploadService.pdf2swf(rootPath+wkUpload.getUrl(),rootPath);
		mav.addObject("swfUrl", wkUpload.getUrl().substring(0,wkUpload.getUrl().lastIndexOf("."))+".swf");
		mav.setViewName("tcoursesite/showOnline.jsp");
		return mav;
	}
	
	/****************************************************************************
	 * description:知识技能体验-资源库列表（分页
	 * 
	 * author:裴继超
	 * date:2016年7月25日9:28:59
	 ****************************************************************************/
 	@RequestMapping("/tcoursesite/findUploadsByUser")
 	@ResponseBody
 	public String paging(HttpServletRequest request,
 			@RequestParam Integer uploadType, Integer currpage){
 		User user = shareService.getUser();
 		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
 		int pagesize = 20;
 		StringBuffer success = new StringBuffer();
 		if(uploadType == 1){
 		//图片资源库
 			List<WkUpload> wkImagesUploadList = wkUploadService.findWkUploadsByUser(user, currpage, pagesize, uploadType);
 			int totalRecords = wkUploadService.countWkUploadsByUser(user, uploadType);
 			Map<String, Integer> pageModel1 = shareService.getPage(currpage, pagesize, totalRecords);
 			for(WkUpload pic : wkImagesUploadList){
 				success.append("<li class=" + '"' + "pic_list hg9 lh35 ptb5 rel ovh" + '"' + ">");
 				success.append("<div class=" + '"' + "cf rel zx1 z c_category" + '"' + ">");
 				success.append("<input class=" + '"' + "l check_box"+ '"' + " type='checkbox'" + " id='upload" + pic.getId() + "'" + " name='checkname'" + " value='" + pic.getId() + "'" + " />");
 				success.append("<label class=" + '"' + "l mt10" + '"' + " for=" + '"' + "upload" + pic.getId() + '"' + "></label>");
 				success.append("<i class=" + '"' + "fa  fa-file-picture-o mt5 w24 h24 brh lh24 tc l wh bgb" + '"' + "></i>");
 				success.append("<div class=" + '"' + "l mlr15 cc1 c_tool poi" + '"' + ">" + pic.getName() + "</div>");
 				success.append("<div class=" + '"' + "accessory_info l f12 g9" + '"' + ">" + sdf.format(pic.getUpTime().getTime()) + " 大小：" + pic.getSize() + "</div></div></li>");
 			}
 			success.append(
 					"<a href='javascript:void(0)' onclick='firstPage(" + uploadType + ",1);'>"+"首页"+"</a>&nbsp;"+
 		    	    "<a href='javascript:void(0)' onclick='previousPage(" + uploadType+ "," +currpage+");'>"+"上一页"+"</a>&nbsp;"+
 		    	    "<a href='javascript:void(0)' onclick='nextPage(" + uploadType+ "," +currpage+","+pageModel1.get("totalPage")+");'>"+"下一页"+"</a>&nbsp;"+
 		    	    "<a href='javascript:void(0)' onclick='lastPage(" + uploadType+ "," +pageModel1.get("totalPage")+");'>"+"末页"+"</a>&nbsp;"+
 		    	    "当前第"+currpage+"页&nbsp; 共"+pageModel1.get("totalPage")+"页  "+totalRecords+"条记录"
 			);
 		}
 		if(uploadType == 2){
 			//附件资源库
 			List<WkUpload> wkFilesUploadList = wkUploadService.findWkUploadsByUser(user, currpage, pagesize, uploadType);
 			int totalRecords = wkUploadService.countWkUploadsByUser(user, uploadType);
 			Map<String, Integer> pageModel2 = shareService.getPage(currpage, pagesize, totalRecords);
 			for(WkUpload file : wkFilesUploadList){
 				success.append("<li class=" + '"' + "pic_list hg9 lh35 ptb5 rel ovh" + '"' + ">");
 				success.append("<div class=" + '"' + "cf rel zx1 z c_category" + '"' + ">");
 				success.append("<input class=" + '"' + "l check_box"+ '"' + " type='checkbox'" + " id='upload" + file.getId() + "'" + " name='checkname'" + " value='" + file.getId() + "'" + " />");
 				success.append("<label class=" + '"' + "l mt10" + '"' + " for=" + '"' + "upload" + file.getId() + '"' + "></label>");
 				success.append("<i class=" + '"' + "fa  fa-file-picture-o mt5 w24 h24 brh lh24 tc l wh bgb" + '"' + "></i>");
 				success.append("<div class=" + '"' + "l mlr15 cc1 c_tool poi" + '"' + ">" + file.getName() + "</div>");
 				success.append("<div class=" + '"' + "accessory_info l f12 g9" + '"' + ">" + sdf.format(file.getUpTime().getTime()) + " 大小：" + file.getSize() + "</div></div></li>");
 			}
 			success.append(
 					"<a href='javascript:void(0)' onclick='firstPage(" + uploadType + ",1);'>"+"首页"+"</a>&nbsp;"+
 		    	    "<a href='javascript:void(0)' onclick='previousPage(" + uploadType+ "," +currpage+");'>"+"上一页"+"</a>&nbsp;"+
 		    	    "<a href='javascript:void(0)' onclick='nextPage(" + uploadType+ "," +currpage+","+pageModel2.get("totalPage")+");'>"+"下一页"+"</a>&nbsp;"+
 		    	    "<a href='javascript:void(0)' onclick='lastPage(" + uploadType+ "," +pageModel2.get("totalPage")+");'>"+"末页"+"</a>&nbsp;"+
 		    	    "当前第"+currpage+"页&nbsp; 共"+pageModel2.get("totalPage")+"页  "+totalRecords+"条记录"
 			);
 		}
 		System.out.println(success.toString());
 		return shareService.htmlEncode(success.toString());
 	}
 	
	/****************************************************************************
	 * description:知识技能体验-查看图片
	 * 
	 * author:裴继超
	 * date:2016年5月5日11:11:06
	 ****************************************************************************/
	@SystemServiceLog("查看图片")
	@RequestMapping("/tcoursesite/lookImage")
	@ResponseBody
	public Map<String, Object> lookImage(@RequestParam Integer folderId,Integer currpage) {
		//查询图片所属的文件夹
		WkFolder wkFolder = wkFolderService.findWkFolderByPrimaryKey(folderId);
		//该文件夹下图片的数量
		int totalRecords = wkFolder.getUploads().size();
		//每页图片数量
		int pageSize = 1;
		//分页设置
		Map<String, Integer> pageModel = shareService.getPage(currpage,pageSize, totalRecords);
		//查询图片信息map
		Map<String, Object> map = wkUploadService.lookImageMap(folderId, currpage,pageSize);
		if(currpage==1){
			// 第一页
			map.put("upImage", 0);
		}else{
			// 上一页
			map.put("upImage", pageModel.get("previousPage"));
		}
		if(currpage==pageModel.get("lastPage")){
			// 最后一页
			map.put("downImage", 0);
		}else{
			// 下一页
			map.put("downImage", pageModel.get("nextPage"));
		}
		

		return map;
	}
	
	/****************************************************************************
	 * description:知识技能体验-查看图片
	 * 
	 * author:于侃
	 * date :2016年10月14日 15:34:46
	 ****************************************************************************/
	@SystemServiceLog("查看图片")
	@RequestMapping("/tcoursesite/lookImageByChapter")
	@ResponseBody
	public Map<String, Object> lookImageByChapter(@RequestParam Integer chapterId,@RequestParam Integer lessonId,Integer currpage) {
		//该章节下图片的数量
		int totalRecords = wkUploadService.countImageMapByChapter(chapterId, lessonId);
		//每页图片数量
		int pageSize = 1;
		//分页设置
		Map<String, Integer> pageModel = shareService.getPage(currpage,pageSize, totalRecords);
		//查询图片信息map
		Map<String, Object> map = wkUploadService.lookImageMapByChapter(chapterId, lessonId, currpage, pageSize);
		if(currpage==1){
			// 第一页
			map.put("upImage", 0);
		}else{
			// 上一页
			map.put("upImage", pageModel.get("previousPage"));
		}
		if(currpage==pageModel.get("lastPage")){
			// 最后一页
			map.put("downImage", 0);
		}else{
			// 下一页
			map.put("downImage", pageModel.get("nextPage"));
		}
		return map;
	}
	
	/**************************************************************************
	 * Description:知识模块-原生方式多文件上传
	 * 
	 * @author：马帅
	 * @date ：2017-8-11
	 * @搬迁标记：已搬迁至Pro_ExperimentSkillController
	 **************************************************************************/
	@SystemServiceLog("知识多文件上传")
	@RequestMapping("/tcoursesite/fileImageUpload")
	public void fileImageUpload(HttpServletRequest request, HttpServletResponse response,Integer type,Integer siteId,Integer moduleType) throws Exception {
		/**
		 * 知识,体验多文件上传
		 */
		String sep = System.getProperty("file.separator");
		String fileName = "";
		if(type==1){
			fileName = "image";
		}
		if(type==2){
			fileName = "document";
		}
		if(type==0){
			fileName = "voide";
		}
		String path = "upload" + sep + "tcoursesite" + sep + "site" + siteId + sep + fileName;
		Map<String,String> fileUploadMap = tCourseSiteService.uploadMultiFile(request, path,type,siteId);
		String html = "";
		if(type==0){
			for(String k:fileUploadMap.keySet()){
				html += k+",";
			}
			response.getWriter().write(html);
		}else{
			for(String k:fileUploadMap.keySet()){
				html += "<input type='hidden' value="+k+" name='fileid' />";
			}
			for(String o: fileUploadMap.values()){
				html += "<li class='pic_list hg9 lh35 ptb5 rel ovh'>" +
	  	      				"<div class='cf rel zx1 z c_category'>" +
	  	      				"<div class=''l mlr15 cc1 c_tool poi'>" +
	  	      				o +
	  	      				"</div></div></li><br/>";
			}
			response.getWriter().write(html);
		}
	}
}