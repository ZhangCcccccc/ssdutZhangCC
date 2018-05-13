/****************************************************************************
 * 命名规范：
 * 本控制层调整必须是按照如下的路径调整：@RequestMapping("/tCourseSite/xxxx")。
 * 列表信息listxxxx，獲取信息：getxxxx 编辑信息：editxxxx 删除信息：detelexxxx 新增信息 newxxxx
 * 导出信息exportxxxx 保存信息：savexxxx 
 ****************************************************************************/

package net.xidlims.web.tcoursesite;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.xidlims.domain.Authority;
import net.xidlims.domain.SchoolAcademy;
import net.xidlims.domain.SchoolClasses;
import net.xidlims.domain.SchoolMajor;
import net.xidlims.domain.SchoolTerm;
import net.xidlims.domain.TAssignment;
import net.xidlims.domain.TAssignmentAnswerAssign;
import net.xidlims.domain.TAssignmentControl;
import net.xidlims.domain.TAssignmentQuestionpool;
import net.xidlims.domain.TCourseSite;
import net.xidlims.domain.TCourseSiteUser;
import net.xidlims.domain.TMessage;
import net.xidlims.domain.User;
import net.xidlims.domain.WkChapter;
import net.xidlims.domain.WkFolder;
import net.xidlims.domain.WkLesson;
import net.xidlims.domain.WkUpload;
import net.xidlims.service.common.ShareService;
import net.xidlims.service.system.SystemService;
import net.xidlims.service.system.UserDetailService;
import net.xidlims.service.tcoursesite.TAssignmentQuestionPoolService;
import net.xidlims.service.tcoursesite.TAssignmentService;
import net.xidlims.service.tcoursesite.TCourseSiteService;
import net.xidlims.service.tcoursesite.TCourseSiteUserService;
import net.xidlims.service.tcoursesite.TMessageService;
import net.xidlims.service.tcoursesite.TWeiKeService;
import net.xidlims.service.tcoursesite.WkChapterService;
import net.xidlims.service.tcoursesite.WkFolderService;
import net.xidlims.service.tcoursesite.WkLessonService;
import net.xidlims.service.tcoursesite.WkUploadService;
import net.xidlims.service.teaching.TAssignmentForTestService;
import net.xidlims.service.timetable.OuterApplicationService;
import net.xidlims.service.timetable.SchoolCourseInfoService;
import net.xidlims.view.ViewTAssignment;
import net.xidlims.web.aop.SystemServiceLog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
/**************************************************************************
 * Description:系统后台管理模块
 * 
 * @author：魏诚
 * @date ：2014-07-14
 **************************************************************************/
@Controller("TWeiKeController")
public class TWeiKeController<JsonResult> {
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
	private TCourseSiteService tCourseSiteService;
	@Autowired
	private WkChapterService wkChapterService;
	@Autowired
	private WkLessonService wkLessonService;
	@Autowired
	private WkUploadService wkUploadService;
	@Autowired
	private TAssignmentQuestionPoolService tAssignmentQuestionPoolService;
	@Autowired
	private TAssignmentService tAssignmentService;
	@Autowired
	private TAssignmentForTestService tAssignmentForTestService;
	@Autowired
	private SchoolCourseInfoService schoolCourseInfoService;
	@Autowired
	private OuterApplicationService outerApplicationService;
	@Autowired
	private UserDetailService userDetailService;
	@Autowired
	private TCourseSiteUserService tCourseSiteUserService;
	@Autowired
	private 	SystemService 	systemService;
	@Autowired 
	private TMessageService tMessageService;
	@Autowired
	private WkFolderService wkFolderService;
	@Autowired
	private TWeiKeService tWeiKeService;
	
	/**************************************************************************
	 * Description:微课-进入站点
	 *  
	 * @author：裴继超
	 * @date ：2016-11-23
	 **************************************************************************/
	@RequestMapping("/tcoursesite/weike/index")
	public ModelAndView weikeIndex(HttpSession session) {
		ModelAndView mav = new ModelAndView();
		// 当前登录人
		User user = shareService.getUser();
		mav.addObject("user", user);
		//设置权限
		if(user!=null){
			if(user.getAuthorities().toString().contains("TEACHER")){
				session.setAttribute("selected_role", "ROLE_TEACHER");
			}else if(user.getAuthorities().toString().contains("STUDENT")){
				session.setAttribute("selected_role", "ROLE_STUDENT");
			}
		}
		mav.setViewName("tcoursesite/weike/index.jsp");
		return mav;
	}
	
	/**************************************************************************
	 * Description:微课-站点列表
	 *  
	 * @author：裴继超
	 * @date ：2016-11-23
	 **************************************************************************/
	@RequestMapping("/tcoursesite/weike/courses")
	public ModelAndView courses(String search) {
		ModelAndView mav = new ModelAndView();
		TCourseSite tCourseSite = new TCourseSite();
		tCourseSite.setTitle(search);
		tCourseSite.setIsOpen(1);
		//当前用户可看的站点
		List<TCourseSite> courses = tWeiKeService.getSiteByUserAndIsOpen(tCourseSite, 1, -1);
		mav.addObject("courses", courses);
		mav.setViewName("tcoursesite/weike/courses.jsp");
		return mav;
	}
	
	
	/**************************************************************************
	 * Description:微课-进入站点
	 *  
	 * @author：裴继超
	 * @date ：2016-04-29
	 **************************************************************************/
	@SystemServiceLog("进入站点")
	@ResponseBody
	@RequestMapping("/tcoursesite/weike/site")
	public ModelAndView weikeSite(@RequestParam Integer tCourseSiteId,
			HttpSession httpSession) {
		ModelAndView mav = new ModelAndView();
		// 选择的课程中心
		TCourseSite tCourseSite = tCourseSiteService.findCourseSiteById(tCourseSiteId);
		mav.addObject("tCourseSite", tCourseSite);
		// 当前登录人
		User user = shareService.getUser();
		httpSession.setAttribute("currsite", tCourseSite);
		httpSession.setAttribute("selected_courseSite", tCourseSiteId);
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
		httpSession.setAttribute("currflag", flag);
		//页面必须的参数
		httpSession.setAttribute("moduleType", 1);
		mav.setViewName("tcoursesite/weike/course.jsp");
		return mav;
	}
	
	/**************************************************************************
	 * Description:微课-查看微课视频
	 *  
	 * @author：裴继超
	 * @date ：2016-04-29
	 **************************************************************************/
	@SystemServiceLog("查看微课视频")
	@ResponseBody
	@RequestMapping("/tcoursesite/weike/video")
	public ModelAndView weikeVideo(@RequestParam Integer tCourseSiteId,Integer folderId,
			HttpSession httpSession) {
		ModelAndView mav = new ModelAndView();
		// 选择的课程中心
		TCourseSite tCourseSite = tCourseSiteService.findCourseSiteById(tCourseSiteId);
		mav.addObject("tCourseSite", tCourseSite);
		// 当前登录人
		User user = shareService.getUser();
		httpSession.setAttribute("currsite", tCourseSite);
		httpSession.setAttribute("selected_courseSite", tCourseSiteId);
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
			flag = 1;//实验中心主任   或者实验室管理员   或者超级管理员   或者教务处   或者校领导   或者教学副院长  或者教务管理员   或者实验中心管理员   为教师权限
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
		httpSession.setAttribute("currflag", flag);
		//页面必须的参数
		httpSession.setAttribute("moduleType", 1);
		
		//获取视频
		WkFolder wkFolder = wkFolderService.findWkFolderByPrimaryKey(folderId);
		mav.addObject("wkFolder", wkFolder);
		mav.setViewName("tcoursesite/weike/video.jsp");
		//mav.setViewName( "redirect:/tcoursesite/courseInfo/courseInfo?tCourseSiteId=" + tCourseSiteId + "&curWeek=1");
		return mav;
	}
	
	/**************************************************************************
	 * Description:微课-查看微课图片
	 *  
	 * @author：裴继超
	 * @date ：2016-04-29
	 **************************************************************************/
	@SystemServiceLog("查看微课图片")
	@ResponseBody
	@RequestMapping("/tcoursesite/weike/image")
	public ModelAndView weikeImage(@RequestParam Integer tCourseSiteId,Integer folderId,
			HttpSession httpSession) {
		ModelAndView mav = new ModelAndView();
		// 选择的课程中心
		TCourseSite tCourseSite = tCourseSiteService.findCourseSiteById(tCourseSiteId);
		mav.addObject("tCourseSite", tCourseSite);
		// 当前登录人
		User user = shareService.getUser();
		httpSession.setAttribute("currsite", tCourseSite);
		httpSession.setAttribute("selected_courseSite", tCourseSiteId);
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
			}else if (tCourseSiteUserService.isUserBelongToTeacher(tCourseSiteId)){
				flag=1; //如果当前登陆人参与这门课，则是老师身份
			}else {
				flag = 0;//如果当前登陆人不是本人开的课程，则是学生身份
			}
			
		}else if(user!=null&&user.getAuthorities().toString().contains("STUDENT")){
			flag = 0;
		}
		httpSession.setAttribute("currflag", flag);
		
		//获取视频
		WkFolder wkFolder = wkFolderService.findWkFolderByPrimaryKey(folderId);
		mav.addObject("wkFolder", wkFolder);
		List<WkUpload> images = wkUploadService.findImagesByChapterId(wkFolder.getWkChapter().getId());
		mav.addObject("images", images);
		mav.setViewName("tcoursesite/weike/image.jsp");
		return mav;
	}
	

}