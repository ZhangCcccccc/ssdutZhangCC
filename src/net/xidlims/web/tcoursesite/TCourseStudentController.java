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
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.xidlims.domain.TAssignmentQuestionpool;
import net.xidlims.dao.WkFolderDAO;
import net.xidlims.dao.TCourseSiteDAO;
import net.xidlims.dao.TCourseSiteUserDAO;
import net.xidlims.dao.WkCourseDAO;
import net.xidlims.dao.WkLessonDAO;
import net.xidlims.dao.WkUploadDAO;
import net.xidlims.domain.Authority;
import net.xidlims.domain.LabCenter;
import net.xidlims.domain.SchoolAcademy;
import net.xidlims.domain.SchoolClasses;
import net.xidlims.domain.SchoolMajor;
import net.xidlims.domain.TAssignment;
import net.xidlims.domain.TAssignmentAnswerAssign;
import net.xidlims.domain.TAssignmentControl;
import net.xidlims.domain.TCourseSite;
import net.xidlims.domain.TCourseSiteChannel;
import net.xidlims.domain.TCourseSiteArtical;
import net.xidlims.domain.TCourseSiteGroup;
import net.xidlims.domain.TCourseSiteUser;
import net.xidlims.domain.TDiscuss;
import net.xidlims.domain.User;
import net.xidlims.domain.WkChapter;
import net.xidlims.domain.WkFolder;
import net.xidlims.domain.WkLesson;
import net.xidlims.domain.WkUpload;
import net.xidlims.service.common.ShareService;
import net.xidlims.service.lab.LabCenterService;
import net.xidlims.service.system.SystemService;
import net.xidlims.service.tcoursesite.TCourseSiteUserService;
import net.xidlims.service.tcoursesite.WkChapterService;
import net.xidlims.service.tcoursesite.WkFolderService;
import net.xidlims.service.tcoursesite.WkLessonService;
import net.xidlims.service.tcoursesite.WkService;
import net.xidlims.service.tcoursesite.WkUploadService;
import net.xidlims.service.tcoursesite.TAssignmentQuestionPoolService;
import net.xidlims.service.tcoursesite.TCourseSiteService;
import net.xidlims.service.tcoursesite.TCourseSiteArticalService;
import net.xidlims.service.tcoursesite.TCourseSiteChannelService;
import net.xidlims.web.aop.SystemServiceLog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

/**************************************************************************
 * Description:系统后台管理模块
 * 
 * @author：魏诚
 * @date ：2014-07-14
 **************************************************************************/
@Controller("TCourseStudentController")
public class TCourseStudentController<JsonResult> {
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
	private TCourseSiteChannelService tCourseSiteChannelService;
	@Autowired
	private TCourseSiteArticalService tCourseSiteArticalService;
	@Autowired
	private TCourseSiteService tCourseSiteService;
	@Autowired
	private LabCenterService labCenterService;
	@Autowired
	private WkChapterService wkChapterService;
	@Autowired
	private WkLessonService wkLessonService;
	@Autowired
	private WkService wkService;
	@Autowired
	private WkUploadService wkUploadService;
	@Autowired
	private WkFolderService wkFolderService;
	@Autowired
	private TAssignmentQuestionPoolService tAssignmentQuestionPoolService;
	@Autowired
	private TCourseSiteUserService tCourseSiteUserService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private TCourseSiteUserDAO tCourseSiteUserDAO;
	
	
	/**************************************************************************
	 * Description:班级成员-查看班级学生
	 *  
	 * @author：裴继超
	 * @date ：2016-06-16
	 **************************************************************************/
	@SystemServiceLog("查看班级学生")
	@ResponseBody
	@RequestMapping("/tcoursesite/student/courseStudentsList")
	public ModelAndView courseStudent(@RequestParam Integer tCourseSiteId,Integer currpage,HttpSession httpSession) {
		ModelAndView mav = new ModelAndView();
		// 选择的课程中心
		TCourseSite tCourseSite = tCourseSiteService.findCourseSiteById(tCourseSiteId);
		mav.addObject("tCourseSite", tCourseSite);
		//当前登录人
		User user = shareService.getUser();
		mav.addObject("user", user);
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
			}else if(tCourseSiteUserService.isUserBelongToTeacher(tCourseSiteId)){
				flag=1;//如果当前登录人是本课程的老师
			}else {
				flag = 0;//如果当前登陆人不是本人开的课程，则是学生身份
			}
			
		}else if(user!=null&&user.getAuthorities().toString().contains("STUDENT")){
			flag = 0;
		}
		mav.addObject("flag", flag);
		//获取该课程下学生数
		 Set<TCourseSiteUser> courseSiteUsers = tCourseSite.getTCourseSiteUsers();
		 Set<TCourseSiteUser> newCourseSiteUsers =new HashSet<TCourseSiteUser>();
		 for(TCourseSiteUser t:courseSiteUsers){
			 if(t.getUser().getUserRole().equals("0")){
				 newCourseSiteUsers.add(t);
			 }
		 }
		int totalRecords=newCourseSiteUsers.size();
		mav.addObject("totalRecords", totalRecords);
		//设置分页大小
		int pageSize = 20;
		Map<String, Integer> pageModel = shareService.getPage(currpage,pageSize, totalRecords);
		mav.addObject("pageModel", pageModel);
		//将currpage映射到page，用来获取当前页的页码
		mav.addObject("page", currpage);
		List<TCourseSiteUser> tCourseSiteUsers = tCourseSiteUserService.findTCourseSiteUserBySiteId(tCourseSiteId, currpage, pageSize);
		mav.addObject("tCourseSiteUsers",tCourseSiteUsers);
		
		mav.setViewName("tcoursesite/student/courseStudentsList.jsp");
		return mav;
	}
	
	/**************************************************************************
	 * Description:班级成员-查询学生列表
	 *  
	 * @author：裴继超
	 * @date ：2016-06-16
	 **************************************************************************/
	@SystemServiceLog("查询学生列表")
	@ResponseBody
	@RequestMapping("/tcoursesite/student/usersList")
	public ModelAndView usersList(@RequestParam Integer tCourseSiteId,Integer currpage,
			@ModelAttribute User newUser,HttpSession httpSession) {
		ModelAndView mav = new ModelAndView();
		// 选择的课程中心
		TCourseSite tCourseSite = tCourseSiteService.findCourseSiteById(tCourseSiteId);
		mav.addObject("tCourseSite", tCourseSite);
		//当前登录人
		User loginUser = shareService.getUser();
		mav.addObject("user", loginUser);
		mav.addObject("newUser", newUser);
		//获取查询user数
		int totalRecords = tCourseSiteUserService.getUsersRecords(newUser,tCourseSiteId);
		mav.addObject("totalRecords", totalRecords);
		//设置分页大小
		int pageSize = 20;
		Map<String, Integer> pageModel = shareService.getPage(currpage,pageSize, totalRecords);
		mav.addObject("pageModel", pageModel);
		//将currpage映射到page，用来获取当前页的页码
		mav.addObject("page", currpage);
		
		//获取所有学院
		List<SchoolAcademy> academys = systemService.getAllSchoolAcademy(1,-1);
		mav.addObject("academys",academys);
		//获取所有专业
		List<SchoolMajor> majors = tCourseSiteUserService.getAllSchoolMajor(1,-1);
		mav.addObject("majors",majors);
		//获取所有专业
		List<SchoolClasses> classes = tCourseSiteUserService.getAllSchoolClass(1,-1);
		mav.addObject("classes",classes);
		//获取学生（分页）
		List<User> users = tCourseSiteUserService.findUsersList(newUser, tCourseSiteId,currpage, pageSize);
		mav.addObject("users",users);
		
		mav.setViewName("tcoursesite/student/usersList.jsp");
		return mav;
	}
	
	/**************************************************************************
	 * Description:班级成员-保存添加的站点学生
	 *  
	 * @author：裴继超
	 * @date ：2016-07-28
	 **************************************************************************/
	@SystemServiceLog("保存站点学生")
	@ResponseBody
	@RequestMapping("/tcoursesite/student/saveTCourseSiteUsers")
	public ModelAndView saveTCourseSiteUsers(@RequestParam Integer tCourseSiteId,HttpSession httpSession,HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		TCourseSite tCourseSite = tCourseSiteService.findCourseSiteById(tCourseSiteId);
		//获取选择的学生
		String[] usernames =request.getParameterValues("checkname");
		//批量添加班级学生
		tCourseSiteUserService.saveTCourseSiteUsers(tCourseSite, usernames);
		mav.setViewName("redirect:/tcoursesite/student/courseStudentsList?tCourseSiteId="+tCourseSiteId+"&currpage=1");
		return mav;
	}

	/**************************************************************************
	 * Description:班级成员-通过school_course生成的站点学生
	 *  
	 * @author：裴继超
	 * @date ：2016-07-28
	 **************************************************************************/
	@SystemServiceLog("生成站点学生")
	@ResponseBody
	@RequestMapping("/tcoursesite/student/saveTCourseSiteUsersByCourseNo")
	public ModelAndView saveTCourseSiteUsersByCourseNo(@RequestParam Integer tCourseSiteId,HttpSession httpSession,HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		TCourseSite tCourseSite = tCourseSiteService.findCourseSiteById(tCourseSiteId);
		mav.addObject("tCourseSite", tCourseSite);
		if(tCourseSite.getSchoolCourse()!=null){
			//批量添加班级学生
			tCourseSiteUserService.saveTCourseSiteUsersByCourseNo(tCourseSite, tCourseSite.getSchoolCourse().getCourseNo());
		}
		mav.setViewName("redirect:/tcoursesite/student/courseStudentsList?tCourseSiteId="+tCourseSiteId+"&currpage=1");
		return mav;
	}
	
	/**************************************************************************
	 * Description:班级成员-删除班级成员
	 *  
	 * @author：裴继超
	 * @date ：2016-07-28
	 **************************************************************************/
	@SystemServiceLog("删除班级成员")
	@ResponseBody
	@RequestMapping("/tcoursesite/student/deleteTCourseSiteUser")
	public ModelAndView deleteTCourseSiteUser(@RequestParam Integer tCourseSiteId,Integer id,HttpSession httpSession,HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		TCourseSite tCourseSite = tCourseSiteService.findCourseSiteById(tCourseSiteId);
		//删除班级学生
		tCourseSiteUserService.deleteTCourseSiteUser(id);
		mav.setViewName("redirect:/tcoursesite/student/courseStudentsList?tCourseSiteId="+tCourseSiteId+"&currpage=1");
		return mav;
	}
	
	/**************************************************************************
	 * Description:班级成员-批量删除班级成员
	 *  
	 * @author：裴继超
	 * @date ：2016-07-28
	 **************************************************************************/
	@SystemServiceLog("批量删除班级成员")
	@ResponseBody
	@RequestMapping("/tcoursesite/student/deleteTCourseSiteUsers")
	public ModelAndView deleteTCourseSiteUsers(@RequestParam Integer tCourseSiteId,HttpSession httpSession,HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		TCourseSite tCourseSite = tCourseSiteService.findCourseSiteById(tCourseSiteId);
		String[] ids = request.getParameterValues("checkname");
		//删除班级学生
		for(String i:ids){
			tCourseSiteUserService.deleteTCourseSiteUser(Integer.parseInt(i));
		}
		
		mav.setViewName("redirect:/tcoursesite/student/courseStudentsList?tCourseSiteId="+tCourseSiteId+"&currpage=1");
		return mav;
	}
	
	/**************************************************************************
	 * @description：班级成员-切换角色-保存班级成员
	 * 
	 * @author：陈乐为
	 * @date：2016-8-26
	 **************************************************************************/
	@SystemServiceLog("切换成员角色")
	@ResponseBody
	@RequestMapping("/tcoursesite/student/saveTCourseSiteUsersForRole")
	public ModelAndView saveTCourseSiteUsersForRole(@RequestParam Integer id,HttpSession httpSession,HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		//根据班级成员id查询班级成员
		TCourseSiteUser tCourseSiteUser = tCourseSiteUserDAO.findTCourseSiteUserByPrimaryKey(id);
		if(tCourseSiteUser != null && tCourseSiteUser.getRole() == 0) {
			tCourseSiteUser.setRole(1);
		} else if(tCourseSiteUser != null && tCourseSiteUser.getRole() == 1) {
			tCourseSiteUser.setRole(0);
		}
		
		tCourseSiteUserService.saveTCourseSiteUsersForRole(tCourseSiteUser);
		mav.setViewName("redirect:/tcoursesite/student/courseStudentsList?tCourseSiteId="+tCourseSiteUser.getTCourseSite().getId()+"&currpage=1");
		return mav;
	}
	
	/**************************************************************************************
	 * description：系统管理-选课组管理-学生名单-查询学生列表
	 * 
	 * @author：陈乐为
	 * @date：2016-9-2
	 *************************************************************************************/
	@SystemServiceLog("查询学生列表")
	@ResponseBody
	@RequestMapping("/tCourseSite/selectCourse/listUser")
	public ModelAndView listUser(@RequestParam Integer tCourseSiteId,Integer currpage,
			@ModelAttribute User newUser,HttpSession httpSession) {
		ModelAndView mav = new ModelAndView();
		// 选择的课程中心
		TCourseSite tCourseSite = tCourseSiteService.findCourseSiteById(tCourseSiteId);
		mav.addObject("tCourseSite", tCourseSite);
		//当前登录人
		User loginUser = shareService.getUser();
		mav.addObject("user", loginUser);
		mav.addObject("newUser", newUser);
		//获取查询user数
		int totalRecords = tCourseSiteUserService.getUsersRecords(newUser,tCourseSiteId);
		mav.addObject("totalRecords", totalRecords);
		//设置分页大小
		int pageSize = 20;
		Map<String, Integer> pageModel = shareService.getPage(currpage,pageSize, totalRecords);
		mav.addObject("pageModel", pageModel);
		//将currpage映射到page，用来获取当前页的页码
		mav.addObject("page", currpage);
		
		//获取所有学院
		List<SchoolAcademy> academys = systemService.getAllSchoolAcademy(1,-1);
		mav.addObject("academys",academys);
		//获取所有专业
		List<SchoolMajor> majors = tCourseSiteUserService.getAllSchoolMajor(1,-1);
		mav.addObject("majors",majors);
		//获取所有专业
		List<SchoolClasses> classes = tCourseSiteUserService.getAllSchoolClass(1,-1);
		mav.addObject("classes",classes);
		//获取学生（分页）
		List<User> users = tCourseSiteUserService.findUsersList(newUser, tCourseSiteId,currpage, pageSize);
		mav.addObject("users",users);
		
		mav.setViewName("tcoursesite/selectCourse/listUser.jsp");
		return mav;
	}
	
	/**************************************************************************************
	 * description：系统管理-选课组管理-学生名单-保存添加的选课组学生
	 * 
	 * @author：陈乐为
	 * @date：2016-9-2
	 *************************************************************************************/
	@SystemServiceLog("保存站点学生")
	@ResponseBody
	@RequestMapping("/tCourseSite/selectCourse/saveTCourseSiteUserList")
	public ModelAndView saveTCourseSiteUserList(@RequestParam Integer tCourseSiteId,HttpSession httpSession,HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		TCourseSite tCourseSite = tCourseSiteService.findCourseSiteById(tCourseSiteId);
		//获取选择的学生
		String[] usernames =request.getParameterValues("checkname");
		//批量添加班级学生
		tCourseSiteUserService.saveTCourseSiteUsers(tCourseSite, usernames);
		mav.setViewName("redirect:/tcoursesite/SelectCourse/courseStudentsList?tCourseSiteId="+tCourseSiteId+"&currpage=1");
		return mav;
	}
	
	/**************************************************************************************
	 * description：系统管理-选课组管理-学生名单-通过school_course生成的站点学生
	 * 
	 * @author：陈乐为
	 * @date：2016-9-2
	 *************************************************************************************/
	@SystemServiceLog("生成站点学生")
	@ResponseBody
	@RequestMapping("/tCourseSite/selectCourse/saveTCourseSiteUsersByCourseNum")
	public ModelAndView saveTCourseSiteUsersByCourseNum(@RequestParam Integer tCourseSiteId,HttpSession httpSession,HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		TCourseSite tCourseSite = tCourseSiteService.findCourseSiteById(tCourseSiteId);
		mav.addObject("tCourseSite", tCourseSite);
		if(tCourseSite.getSchoolCourse()!=null){
			//批量添加班级学生
			tCourseSiteUserService.saveTCourseSiteUsersByCourseNo(tCourseSite, tCourseSite.getSchoolCourse().getCourseNo());
		}
		mav.setViewName("redirect:/tcoursesite/SelectCourse/courseStudentsList?tCourseSiteId="+tCourseSiteId+"&currpage=1");
		return mav;
	}
	
	/**************************************************************************************
	 * description：系统管理-选课组管理-学生名单-批量删除班级成员
	 * 
	 * @author：陈乐为
	 * @date：2016-9-2
	 *************************************************************************************/
	@SystemServiceLog("批量删除班级成员")
	@ResponseBody
	@RequestMapping("/tCourseSite/selectCourse/deleteTCourseSiteUserList")
	public ModelAndView deleteTCourseSiteUserList(@RequestParam Integer tCourseSiteId,HttpSession httpSession,HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		TCourseSite tCourseSite = tCourseSiteService.findCourseSiteById(tCourseSiteId);
		String[] ids = request.getParameterValues("checkname");
		//删除班级学生
		for(String i:ids){
			tCourseSiteUserService.deleteTCourseSiteUser(Integer.parseInt(i));
		}
		
		mav.setViewName("redirect:/tcoursesite/SelectCourse/courseStudentsList?tCourseSiteId="+tCourseSiteId+"&currpage=1");
		return mav;
	}
	
	/**************************************************************************************
	 * description：系统管理-选课组管理-学生名单-删除班级成员
	 * 
	 * @author：陈乐为
	 * @date：2016-9-2
	 *************************************************************************************/
	@SystemServiceLog("删除班级成员")
	@ResponseBody
	@RequestMapping("/tCourseSite/selectCourse/deleteTCourseSiteUserByOne")
	public ModelAndView deleteTCourseSiteUserByOne(@RequestParam Integer tCourseSiteId,Integer id,HttpSession httpSession,HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		TCourseSite tCourseSite = tCourseSiteService.findCourseSiteById(tCourseSiteId);
		//删除班级学生
		tCourseSiteUserService.deleteTCourseSiteUser(id);
		mav.setViewName("redirect:/tcoursesite/SelectCourse/courseStudentsList?tCourseSiteId="+tCourseSiteId+"&currpage=1");
		return mav;
	}
	
	/**************************************************************************
	 * description：系统管理-选课组管理-学生名单-切换角色{保存班级成员}
	 * 
	 * @author：陈乐为
	 * @date：2016-9-2
	 **************************************************************************/
	@SystemServiceLog("切换成员角色")
	@ResponseBody
	@RequestMapping("/tCourseSite/selectCourse/saveTCourseSiteUserForRole")
	public ModelAndView saveTCourseSiteUserForRole(@RequestParam Integer id,HttpSession httpSession,HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		TCourseSiteUser tCourseSiteUser = tCourseSiteUserDAO.findTCourseSiteUserByPrimaryKey(id);
		if(tCourseSiteUser != null && tCourseSiteUser.getRole() == 0) {
			tCourseSiteUser.setRole(1);
		} else if(tCourseSiteUser != null && tCourseSiteUser.getRole() == 1) {
			tCourseSiteUser.setRole(0);
		}
		
		tCourseSiteUserService.saveTCourseSiteUsersForRole(tCourseSiteUser);
		mav.setViewName("redirect:/tcoursesite/SelectCourse/courseStudentsList?tCourseSiteId="+tCourseSiteUser.getTCourseSite().getId()+"&currpage=1");
		return mav;
	}
	
	/**************************************************************************
	 * description：系统管理-选课组管理-学生名单{导入学生名单}
	 * 
	 * @author：陈乐为
	 * @date：2016-9-2
	 **************************************************************************/
	@RequestMapping("/tCourseSite/selectCourse/importStudents")
	public ModelAndView importStudents(HttpServletRequest request,@RequestParam Integer cid) {
		ModelAndView mav = new ModelAndView();
		// 文件名称
		String fileName = shareService.getUpdateFileSavePath(request);
		// 服务器地址
		String logoRealPathDir = request.getSession().getServletContext().getRealPath("/");
		// 文件的全部地址
		String filePath = logoRealPathDir + fileName;

		if (fileName.endsWith("xls") || fileName.endsWith("xlsx")) {
			try{
				tCourseSiteService.importStudentsXls(filePath, cid);
			}catch(Exception e){
				e.printStackTrace();
			}			
		}
		
		mav.setViewName("redirect:/tcoursesite/SelectCourse/courseStudentsList?tCourseSiteId="+cid+"&currpage=1");
		return mav;
	}
	
	


}