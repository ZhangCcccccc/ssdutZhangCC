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
@Controller("TCourseSiteGroupController")
public class TCourseSiteGroupController<JsonResult> {
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
	 * Description:学生分组-查看学生分组
	 *  
	 * @author：李军凯
	 * @date ：2016-09-14
	 **************************************************************************/
	@SystemServiceLog("查看学生分组")
	@ResponseBody
	@RequestMapping("/tcoursesite/student/courseStudentsGroupList")
	public ModelAndView courseStudentsGroupList(@RequestParam Integer tCourseSiteId,Integer currpage,HttpSession httpSession) {
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
		mav.addObject("flag", flag);
		//获取该课程下学生分组数
		int totalRecords=tCourseSite.getTCourseSiteGroups().size();
		mav.addObject("totalRecords", totalRecords);
		//设置分页大小
		int pageSize = 20;
		Map<String, Integer> pageModel = shareService.getPage(currpage,pageSize, totalRecords);
		mav.addObject("pageModel", pageModel);
		//将currpage映射到page，用来获取当前页的页码
		mav.addObject("page", currpage);
		//新建学生分组
		mav.addObject("tCourseSiteGroup",new TCourseSiteGroup());
		
		List<TCourseSiteGroup> tCourseSiteGroups = tCourseSiteUserService.findTCourseSiteGroupBySiteId(tCourseSiteId, currpage, pageSize);		
		mav.addObject("tCourseSiteGroups",tCourseSiteGroups);		
		mav.setViewName("tcoursesite/student/courseStudentsGroupList.jsp");
		return mav;
	}
	/**************************************************************************
	 * Description:学生分组-查询学生分组
	 *  
	 * @author：李军凯
	 * @date ：2016-09-14
	 **************************************************************************/
	@SystemServiceLog("查询学生分组信息")
	@RequestMapping("/tcoursesite/student/findTCourseSiteGroupById")
	public @ResponseBody String[] findTCourseSiteGroupById(@RequestParam Integer tCourseSiteId,@RequestParam Integer id) {
		String[] ss = new String[2];
		//根据id查询学生分组信息
		TCourseSiteGroup tCourseSiteGroup = tCourseSiteUserService.findTCourseSiteGroupById(id);
		ss[0] = tCourseSiteGroup.getGroupTitle();
		ss[1] = tCourseSiteGroup.getDescription();
		return ss;
	}
	/**************************************************************************
	 * Description:学生分组-保存学生分组
	 *  
	 * @author：李军凯
	 * @date ：2016-09-14
	 **************************************************************************/
	@SystemServiceLog("保存学生分组")
	@ResponseBody
	@RequestMapping("/tcoursesite/student/savetCourseSiteGroup")
	public ModelAndView savetCourseSiteGroup(@RequestParam Integer tCourseSiteId,@ModelAttribute TCourseSiteGroup tCourseSiteGroup) {		
		ModelAndView mav = new ModelAndView();		
		//保存学生分组
		tCourseSiteUserService.saveTCourseSiteGroup(tCourseSiteGroup);
		mav.setViewName("redirect:/tcoursesite/student/courseStudentsGroupList?tCourseSiteId="+tCourseSiteId+"&currpage=1");
		return mav;
	}
	/**************************************************************************
	 * Description:学生分组-删除学生分组
	 *  
	 * @author：李军凯
	 * @date ：2016-09-14
	 **************************************************************************/
	@SystemServiceLog("删除学生分组")
	@ResponseBody
	@RequestMapping("/tcoursesite/student/deletetCourseSiteGroup")
	public ModelAndView deletetCourseSiteGroup(@RequestParam Integer tCourseSiteId,Integer id,HttpSession httpSession,HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		TCourseSiteGroup tCourseSiteGroup = tCourseSiteUserService.findTCourseSiteGroupById(id);
		//删除学生分组
		tCourseSiteUserService.deleteTCourseSiteGroup(tCourseSiteGroup);
		mav.setViewName("redirect:/tcoursesite/student/courseStudentsGroupList?tCourseSiteId="+tCourseSiteId+"&currpage=1");
		return mav;
	}
	/**************************************************************************
	 * Description:学生分组-批量删除学生分组
	 *  
	 * @author：李军凯
	 * @date ：2016-09-18
	 **************************************************************************/
	@SystemServiceLog("批量删除学生分组")
	@ResponseBody
	@RequestMapping("/tcoursesite/student/deletetCourseSiteGroups")
	public ModelAndView deletetCourseSiteGroups(@RequestParam Integer tCourseSiteId,HttpSession httpSession,HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		String[] ids = request.getParameterValues("checkname");
		//删除班级学生
		for(String id:ids){
			TCourseSiteGroup tCourseSiteGroup = tCourseSiteUserService.findTCourseSiteGroupById(Integer.parseInt(id));
			tCourseSiteUserService.deleteTCourseSiteGroup(tCourseSiteGroup);
		}
		mav.setViewName("redirect:/tcoursesite/student/courseStudentsGroupList?tCourseSiteId="+tCourseSiteId+"&currpage=1");
		return mav;
	}
	/**************************************************************************
	 * Description:学生分组-查看学生分组详情
	 *  
	 * @author：李军凯
	 * @date ：2016-09-18
	 **************************************************************************/
	@SystemServiceLog("查看学生分组详情")
	@ResponseBody
	@RequestMapping("/tcoursesite/student/tCourseSiteGroupDetail")
	public ModelAndView tCourseSiteGroupDetail(@RequestParam Integer tCourseSiteId,Integer tCourseSiteGroupId,Integer currpage,HttpSession httpSession) {
		ModelAndView mav = new ModelAndView();
		// 选择的课程中心
		TCourseSite tCourseSite = tCourseSiteService.findCourseSiteById(tCourseSiteId);
		mav.addObject("tCourseSite", tCourseSite);
		//当前登录人
		User user = shareService.getUser();
		mav.addObject("user", user);
		//获取该分组下的学生数
		int totalRecords= tCourseSiteUserService.findTCourseSiteGroupTotalRecords(tCourseSiteGroupId);
		mav.addObject("totalRecords", totalRecords);
		//设置分页大小
		int pageSize = 20;
		Map<String, Integer> pageModel = shareService.getPage(currpage,pageSize, totalRecords);
		mav.addObject("pageModel", pageModel);
		//将currpage映射到page，用来获取当前页的页码
		mav.addObject("page", currpage);
		//新建学生分组
		//mav.addObject("tCourseSiteGroup",new TCourseSiteGroup());
		List<TCourseSiteUser> tCourseSiteUsers = tCourseSiteUserService.findTCourseSiteUserByTCourseSiteId(tCourseSiteGroupId, currpage, pageSize);		
		mav.addObject("tCourseSiteUsers",tCourseSiteUsers);
		mav.addObject("tCourseSiteGroupId", tCourseSiteGroupId);
		mav.setViewName("tcoursesite/student/courseStudentsGroupDetailList.jsp");
		return mav;
	}
	/**************************************************************************
	 * Description:学生分组-查看学生分组详情
	 *  
	 * @author：李军凯
	 * @date ：2016-09-18
	 **************************************************************************/
	@SystemServiceLog("切换分组内成员角色")
	@ResponseBody
	@RequestMapping("/tcoursesite/student/saveTCourseSiteGroupUsersForRole")
	public ModelAndView saveTCourseSiteGroupUsersForRole(@RequestParam Integer id,HttpSession httpSession,HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		//根据学生分组id查询学生
		TCourseSiteUser tCourseSiteUser = tCourseSiteUserDAO.findTCourseSiteUserByPrimaryKey(id);
		if(tCourseSiteUser != null && tCourseSiteUser.getRole() == 0) {
			tCourseSiteUser.setRole(2);
		} else if(tCourseSiteUser != null && tCourseSiteUser.getRole() == 2) {
			tCourseSiteUser.setRole(0);
		}		
		tCourseSiteUserService.saveTCourseSiteUsersForRole(tCourseSiteUser);
		mav.setViewName("redirect:/tcoursesite/student/tCourseSiteGroupDetail?tCourseSiteId="+tCourseSiteUser.getTCourseSite().getId()+"&tCourseSiteGroupId="+tCourseSiteUser.getgroupId()+"&currpage=1");
		return mav;
	}
	/**************************************************************************
	 * Description:学生分组-删除分组内的学生
	 *  
	 * @author：李军凯
	 * @date ：2016-09-18
	 **************************************************************************/
	@SystemServiceLog("删除分组内的学生")
	@ResponseBody
	@RequestMapping("/tcoursesite/student/deleteTCourseSiteGroupUser")
	public ModelAndView deleteTCourseSiteGroupUser(@RequestParam Integer tCourseSiteId,Integer id,HttpSession httpSession,HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		//根据班级成员id查询分组内的学生
		TCourseSiteUser tCourseSiteUser = tCourseSiteUserDAO.findTCourseSiteUserByPrimaryKey(id);
		Integer groupId = tCourseSiteUser.getgroupId();
		//删除分组内的学生
		tCourseSiteUser.setgroupId(null);
		tCourseSiteUserService.saveTCourseSiteUsersForRole(tCourseSiteUser);
		mav.setViewName("redirect:/tcoursesite/student/tCourseSiteGroupDetail?tCourseSiteId="+tCourseSiteId+"&tCourseSiteGroupId="+groupId+"&currpage=1");
		return mav;
	}
	/**************************************************************************
	 * Description:学生分组-批量删除分组内的学生
	 *  
	 * @author：李军凯
	 * @date ：2016-09-18
	 **************************************************************************/
	@SystemServiceLog("批量删除分组内的学生")
	@ResponseBody
	@RequestMapping("/tcoursesite/student/deleteTCourseSiteGroupUsers")
	public ModelAndView deleteTCourseSiteGroupUsers(@RequestParam Integer tCourseSiteId,Integer tCourseSiteGroupId,HttpSession httpSession,HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		String[] ids = request.getParameterValues("checkname");
		//删除班级学生
		for(String id:ids){
			//根据班级成员id查询分组内的学生
			TCourseSiteUser tCourseSiteUser = tCourseSiteUserDAO.findTCourseSiteUserByPrimaryKey(Integer.parseInt(id));
			//删除分组内的学生
			tCourseSiteUser.setgroupId(null);
			tCourseSiteUserService.saveTCourseSiteUsersForRole(tCourseSiteUser);
		}
		mav.setViewName("redirect:/tcoursesite/student/tCourseSiteGroupDetail?tCourseSiteId="+tCourseSiteId+"&tCourseSiteGroupId="+tCourseSiteGroupId+"&currpage=1");
		return mav;
	}
	/**************************************************************************
	 * Description:学生分组-查询学生列表
	 *  
	 * @author：李军凯
	 * @date ：2016-09-18
	 **************************************************************************/
	@SystemServiceLog("查询学生列表")
	@ResponseBody
	@RequestMapping("/tcoursesite/student/groupStudentsList")
	public ModelAndView groupStudentsList(@RequestParam Integer tCourseSiteId,Integer tCourseSiteGroupId,Integer currpage,
	HttpSession httpSession) {
		ModelAndView mav = new ModelAndView();
		// 选择的课程中心
		TCourseSite tCourseSite = tCourseSiteService.findCourseSiteById(tCourseSiteId);
		mav.addObject("tCourseSite", tCourseSite);
		//当前登录人
		User loginUser = shareService.getUser();
		mav.addObject("user", loginUser);
		//获取查询user数
		int totalRecords = tCourseSiteUserService.findTCourseSiteGroupTotalRecordsBySite(tCourseSiteId, tCourseSiteGroupId);
		mav.addObject("totalRecords", totalRecords);
		//设置分页大小
		int pageSize = 30;
		Map<String, Integer> pageModel = shareService.getPage(currpage,pageSize, totalRecords);
		mav.addObject("pageModel", pageModel);
		//将currpage映射到page，用来获取当前页的页码
		mav.addObject("page", currpage);
		//获取学生（分页）
		List<TCourseSiteUser> tCourseSiteUsers = tCourseSiteUserService.findtCourseSiteUsersList(tCourseSiteId, tCourseSiteGroupId, currpage, pageSize);
		mav.addObject("tCourseSiteUsers",tCourseSiteUsers);
		mav.addObject("tCourseSiteGroupId", tCourseSiteGroupId);
		mav.setViewName("tcoursesite/student/groupStudentsList.jsp");
		return mav;
	}
	/**************************************************************************
	 * Description:学生分组-保存分组添加的学生
	 *  
	 * @author：李军凯
	 * @date ：2016-09-18
	 **************************************************************************/
	@SystemServiceLog("保存分组添加的学生")
	@ResponseBody
	@RequestMapping("/tcoursesite/student/saveTCourseSiteGroupUsers")
	public ModelAndView saveTCourseSiteGroupUsers(@RequestParam Integer tCourseSiteId,Integer tCourseSiteGroupId,HttpSession httpSession,HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		//TCourseSite tCourseSite = tCourseSiteService.findCourseSiteById(tCourseSiteId);
		//获取选择的学生
		String[] ids =request.getParameterValues("checkname");
		//批量添加班级学生
		for(String id: ids ){
		tCourseSiteUserService.saveTCourseSiteGroupUser(Integer.parseInt(id), tCourseSiteGroupId);
		}
		mav.setViewName("redirect:/tcoursesite/student/groupStudentsList?tCourseSiteId="+tCourseSiteId+"&tCourseSiteGroupId="+tCourseSiteGroupId+"&currpage=1");
		return mav;
	}
}