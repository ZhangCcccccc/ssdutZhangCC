/****************************************************************************
 * 命名规范：
 * 本控制层调整必须是按照如下的路径调整：@RequestMapping("/device/system/sample")，全部小写。
 * 列表信息listxxxx，獲取信息：getxxxx 编辑信息：editxxxx 删除信息：detelexxxx 新增信息 newxxxx
 * 导出信息exportxxxx 保存信息：savexxxx 
 ****************************************************************************/

package net.xidlims.web.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.xidlims.dao.AuthorityDAO;
import net.xidlims.dao.SystemLogDAO;
import net.xidlims.dao.UserDAO;
import net.xidlims.domain.Authority;
import net.xidlims.domain.LabCenter;
import net.xidlims.domain.Message;
import net.xidlims.domain.SchoolAcademy;
import net.xidlims.domain.SystemLog;
import net.xidlims.domain.User;
import net.xidlims.service.common.ShareService;
import net.xidlims.service.lab.LabCenterService;
import net.xidlims.service.message.MessageService;
import net.xidlims.service.system.SystemLogService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

/****************************************************************************
 * 功能：系统后台管理模块 作者：魏诚 时间：2014-07-14
 ****************************************************************************/
@Controller("CommonController")
@SessionAttributes("selected_labCenter")
public class CommonController<JsonResult> {
	
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
	private MessageService messageService;
	@Autowired
	private SystemLogService systemLogService;
	@Autowired
	private AuthorityDAO authorityDAO;
	@Autowired
	private UserDAO userDAO;
	/*
	 * @RequestMapping("/test") public ModelAndView test(){ ModelAndView mav=new
	 * ModelAndView(); //当前登录人 User user=shareService.getUser();
	 * mav.addObject("user",user); //所属学院 SchoolAcademy
	 * academy=user.getSchoolAcademy(); //所属学院下的中心 Set<LabCenter> centers=new
	 * HashSet<LabCenter>(); if(academy!=null){ centers=academy.getLabCenters();
	 * }else{ centers=labCenterService.findAllLabCenter(); }
	 * mav.addObject("centers", centers); mav.setViewName("system/test.jsp");
	 * return mav; }
	 */

	/****************************************************************************
	 * 功能：系统默认的url 作者：李小龙
	 ****************************************************************************/
	@RequestMapping("/test")
	public ModelAndView test(HttpSession session,@RequestParam Integer labCenterId,ModelMap modelMap) {
		// 当前登录人
		User user = shareService.getUser();
		ModelAndView mav = new ModelAndView();
		String judge = ",";
		for (Authority authority : user.getAuthorities()) {
			judge = judge + "," + authority.getId() + ",";
	    }
		Set<Authority> authorities = user.getAuthorities();
		if(user.getSchoolCourseDetails() != null && user.getSchoolCourseDetails().size() != 0){
			if(judge.indexOf(",22,") == -1){
				Authority authority = new Authority();
				authority.setId(22);
				authorities = user.getAuthorities();
				authorities.add(authority);
				user.setAuthorities(authorities);
				userDAO.store(user);
			}
		}else{
			if(judge.indexOf(",22,") != -1){
				Authority authority = authorityDAO.findAuthorityById(22);
				authorities = user.getAuthorities();
				authorities.remove(authority);
				user.setAuthorities(authorities);
				userDAO.store(user);
			}
		}
		/*
		 * 角色判断：如果具有老师权限则默认为老师，如果没有教师权限则默认为学生
		*/
		if(user.getAuthorities().toString().contains("TEACHER")){
			session.setAttribute("selected_role", "ROLE_TEACHER");
		}else if(user.getAuthorities().toString().contains("STUDENT")){
			session.setAttribute("selected_role", "ROLE_STUDENT");
		}else if(user.getAuthorities().toString().contains("SUPERADMIN")){
			session.setAttribute("selected_role", "ROLE_SUPERADMIN");
		}

        judge = ",";
        for (Authority authority : user.getAuthorities()) {
            judge = judge + "," + authority.getId() + ",";
        }
        if(session.getAttribute("authorityName") == null){
        	  if(judge.indexOf(",11,") != -1){
              	session.setAttribute("authorityName", "SUPERADMIN");
              }else if(judge.indexOf(",21,") != -1){
                	session.setAttribute("authorityName", "EDUCATIONADMIN");
                }else if(judge.indexOf(",21,") != -1){
              	session.setAttribute("authorityName", "ASSOCIATEDEAN");
              }else if(judge.indexOf(",22,") != -1){
              	session.setAttribute("authorityName", "COURSETEACHER");
              }else if(judge.indexOf(",7,") != -1){
                	session.setAttribute("authorityName", "EXPERIMENTALTEACHING");
              }else if(judge.indexOf(",5,") != -1){
                  	session.setAttribute("authorityName", "LABMANAGER");
              }else if(judge.indexOf(",24,") != -1){
                	session.setAttribute("authorityName", "LABCENTERMANAGER");
              }else if(judge.indexOf(",2,") != -1){
              	session.setAttribute("authorityName", "TEACHER");
              }else if(judge.indexOf(",1,") != -1){
              	session.setAttribute("authorityName", "STUDENT");
              }else{
              	session.setAttribute("authorityName", "TEACHER");
              }
        }
        shareService.changeRole(session.getAttribute("authorityName").toString());
		//将当前登录人放到session中
		session.setAttribute("loginUser", user);
		session.setAttribute("messageNum", messageService.countmessage( ));
		if ("portal".equals(session.getAttribute("LOGINTYPE"))) {
			mav.setViewName("redirect:/cms/index");
			return mav;
		}
		if ("admin".equals(session.getAttribute("LOGINTYPE"))) {
			mav.setViewName("redirect:/admin/");
			return mav;
		}
		if ("tms".equals(session.getAttribute("SITELOGINTYPE"))) {
			mav.setViewName("redirect:/tms/index");
			return mav;
		}
		modelMap.addAttribute("selected_labCenter", labCenterId);
		mav.addObject("user", user);
		String authority = "";
		int i = 1;
		Set<Authority> auths = user.getAuthorities();
		for (Authority a : auths) {
			if (a.getType() >= i) {
				authority = a.getCname();
				i = a.getType();
			}
		} 
		mav.addObject("authority", authority);
		// 选择的中心
		LabCenter center = null;
		if(labCenterId!=-1){
			//记录老师选择的选择中心操作到system_log表
			SystemLog currLog = systemLogService.findDefaultCenterByUsernameAndDetail(user.getUsername(), "默认实验中心");
			systemLogService.saveDefaultCenterLog(user, labCenterId, currLog);
				
			center = labCenterService.findLabCenterByPrimaryKey(labCenterId);
		}
		if ("index".equals(session.getAttribute("LOGINTYPE"))&&labCenterId==-1) {
			mav.setViewName("system/test.jsp");
			return mav;
		}
		mav.addObject("center", center);
		mav.setViewName("system/test.jsp");
		return mav;
	}

	/****************************************************************************
	 * 功能：选择前往的中心 作者：李小龙
	 ****************************************************************************/
	@RequestMapping("/checkCenter")
	public ModelAndView checkCenter(HttpServletRequest request,@ModelAttribute("selected_labCenter") Integer cid) {
		
		ModelAndView mav = new ModelAndView();
		User user = shareService.getUser();  // 当前登录人

		List<LabCenter> centers = new ArrayList<LabCenter>();
/*		if (SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString().indexOf("ROLE_SUPERADMIN") != -1 || 
				SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString().indexOf("ROLE_SCHOOLLEADER") != -1
				) //当用户为校领导、超级管理员时，可以查看所有中心
		{
			centers = labCenterService.findAllLabCenterByQuery(new LabCenter(), 1, -1);
		} 
		else 
		{
			SchoolAcademy academy = user.getSchoolAcademy();  // 所属学院
			if (academy != null) 
			{
				centers.addAll(labCenterService.findLabCenterByAcademy(academy.getAcademyNumber()));  // 所属学院下的中心
				LabCenter specialCenter = labCenterService.findLabCenterByPrimaryKey(15); // 综合性工程训练中心 都要看到
				if (!centers.contains(specialCenter)) {
					centers.add(specialCenter);
				}
			}
		}*/
		
		//西电的逻辑是进系统不再手动选择lab_center，默认选择一个所在学院对应的实验中心
		if (SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString().indexOf("ROLE_SUPERADMIN") != -1){//当用户为超级管理员时，可以查看所有中心
			centers = labCenterService.findAllLabCenterByQuery(new LabCenter(), 1, -1);
		}else{
			SchoolAcademy academy = user.getSchoolAcademy();  // 所属学院
			if (academy != null) 
			{
				centers.addAll(labCenterService.findLabCenterByAcademy(academy.getAcademyNumber()));  // 所属学院下的中心
			}
		}
		
		mav.addObject("user", user);
		mav.addObject("centers", centers);
		/*mav.setViewName("system/checkCenter.jsp");*/
		if(cid != -1){//在已经自动选择lab_center的情况下可以进行手动选择
			mav.setViewName("system/checkCenter.jsp");
		}else{
			SystemLog currLog = systemLogService.findDefaultCenterByUsernameAndDetail(user.getUsername(), "默认实验中心");
			if(currLog != null){
				mav.setViewName("redirect:/test?labCenterId=" + currLog.getOperationAction());
			}else{
				mav.setViewName("redirect:/test?labCenterId=" + centers.get(0).getId());
			}
		}
		return mav;
	}

	/****************************************************************************
	 * 功能：开发人员选择前往的登录页面 作者：魏诚
	 ****************************************************************************/
	@RequestMapping("/system/developer")
	public ModelAndView developer() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("system/developer.jsp");
		return mav;
	}
	
	/****************************************************************************
	 * @功能：登陆成功默认的url 
	 * @作者：黄崔俊
	 * @时间：2015-11-12 14:29:16
	 ****************************************************************************/
	@RequestMapping("/login")
	public ModelAndView test(HttpServletRequest request,HttpSession httpSession) {
		ModelAndView mav = new ModelAndView();
		
		// 当前登录人
		User user = shareService.getUser();
		/*
		 * 角色判断：如果具有老师权限则默认为老师，如果没有教师权限则默认为学生
		*/
		if(user.getAuthorities().toString().contains("SUPERADMIN")){
			request.getSession().setAttribute("selected_role", "ROLE_SUPERADMIN");
		}else if(user.getAuthorities().toString().contains("TEACHER")){
			request.getSession().setAttribute("selected_role", "ROLE_TEACHER");
		}else if(user.getAuthorities().toString().contains("STUDENT")){
			request.getSession().setAttribute("selected_role", "ROLE_STUDENT");
		}else if(user.getAuthorities().toString().contains("FAMILYMEMBER")){
			request.getSession().setAttribute("selected_role", "ROLE_FAMILYMEMBER");
		}
		httpSession.setAttribute("LOGINTYPE","back");
		httpSession.setAttribute("loginUser", user);
		mav.addObject("user", user);
		mav.setViewName("redirect:/checkCenter");
		return mav;
	}
}