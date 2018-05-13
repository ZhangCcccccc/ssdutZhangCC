package net.xidlims.web.myCenter;

import java.net.BindException;
import java.util.List;
import java.util.Set;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.xidlims.dao.AuthorityDAO;
import net.xidlims.dao.MessageDAO;
import net.xidlims.dao.UserDAO;

import net.xidlims.domain.Authority;
import net.xidlims.domain.Message;
import net.xidlims.domain.User;

import net.xidlims.service.common.ShareService;
import net.xidlims.service.message.MessageService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.WebDataBinder;

import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.web.servlet.ModelAndView;

/**
 * Spring MVC controller that handles CRUD requests for Message entities
 * 
 */

@Controller("MyCenterController")
public class MyCenterController {

	/**
	 * DAO injected by Spring that manages Message entities
	 * 
	 */
	@Autowired
	private MessageDAO messageDAO;
	@Autowired
	private ShareService shareService;
	/**
	 * Service injected by Spring that provides CRUD operations for Message entities
	 * 
	 */
	@Autowired
	private MessageService messageService;
	@Autowired
	private AuthorityDAO authorityDAO;
	@Autowired
	private UserDAO userDAO;
	/**
	 * Register custom, context-specific property editors
	 * 
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder, HttpServletRequest request) { // Register static property editors.
		binder.registerCustomEditor(java.util.Calendar.class, new org.skyway.spring.util.databinding.CustomCalendarEditor());
		binder.registerCustomEditor(byte[].class, new org.springframework.web.multipart.support.ByteArrayMultipartFileEditor());
		binder.registerCustomEditor(boolean.class, new org.skyway.spring.util.databinding.EnhancedBooleanEditor(false));
		binder.registerCustomEditor(Boolean.class, new org.skyway.spring.util.databinding.EnhancedBooleanEditor(true));
		binder.registerCustomEditor(java.math.BigDecimal.class, new org.skyway.spring.util.databinding.NaNHandlingNumberEditor(java.math.BigDecimal.class, true));
		binder.registerCustomEditor(Integer.class, new org.skyway.spring.util.databinding.NaNHandlingNumberEditor(Integer.class, true));
		binder.registerCustomEditor(java.util.Date.class, new org.skyway.spring.util.databinding.CustomDateEditor());
		binder.registerCustomEditor(String.class, new org.skyway.spring.util.databinding.StringEditor());
		binder.registerCustomEditor(Long.class, new org.skyway.spring.util.databinding.NaNHandlingNumberEditor(Long.class, true));
		binder.registerCustomEditor(Double.class, new org.skyway.spring.util.databinding.NaNHandlingNumberEditor(Double.class, true));
	}

	/***********************************************************************************
	 * @description 个人中心
	 * @author 郑昕茹
	 * @date 2017-04-07
	 * **********************************************************************************/
	@RequestMapping("/self/myCenter")
	public ModelAndView myCenter(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		User user = shareService.getUser();
		//登陆人权限获得
		mav.addObject("authorities", shareService.getUser().getAuthorities());
		mav.addObject("user", user);
		int currpage = 1;
		//我的消息
		int pageSize = 3;
		List<Message>  messages=messageService.findMessageBySome(new Message(), 0, 3,request);
		mav.addObject("messages",messages);

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
			request.getSession().setAttribute("selected_role", "ROLE_TEACHER");
		}else if(user.getAuthorities().toString().contains("STUDENT")){
			request.getSession().setAttribute("selected_role", "ROLE_STUDENT");
		}else if(user.getAuthorities().toString().contains("SUPERADMIN")){
			request.getSession().setAttribute("selected_role", "ROLE_SUPERADMIN");
		}

        judge = ",";
        for (Authority authority : user.getAuthorities()) {
            judge = judge + "," + authority.getId() + ",";
        }
        if(request.getSession().getAttribute("authorityName") == null){
        	  if(judge.indexOf(",11,") != -1){
              	request.getSession().setAttribute("authorityName", "SUPERADMIN");
              	request.getSession().setAttribute("authorityCName", "超级管理员");
              }else if(judge.indexOf(",21,") != -1){
                	request.getSession().setAttribute("authorityName", "EDUCATIONADMIN");
                	request.getSession().setAttribute("authorityCName", "教务管理员");
              }else if(judge.indexOf(",21,") != -1){
              	request.getSession().setAttribute("authorityName", "ASSOCIATEDEAN");
              	request.getSession().setAttribute("authorityCName", "教学副院长");
              }else if(judge.indexOf(",22,") != -1){
              	request.getSession().setAttribute("authorityName", "COURSETEACHER");
              	request.getSession().setAttribute("authorityCName", "课程负责教师");
              }else if(judge.indexOf(",7,") != -1){
                	request.getSession().setAttribute("authorityName", "EXPERIMENTALTEACHING");
                	request.getSession().setAttribute("authorityCName", "实验教务");
              }else if(judge.indexOf(",5,") != -1){
                  	request.getSession().setAttribute("authorityName", "LABMANAGER");
                  	request.getSession().setAttribute("authorityCName", "实验室管理员");
              }else if(judge.indexOf(",24,") != -1){
                	request.getSession().setAttribute("authorityName", "LABCENTERMANAGER");
                	request.getSession().setAttribute("authorityCName", "实验中心管理员");
              }else if(judge.indexOf(",4,") != -1){
                	request.getSession().setAttribute("authorityName", "EXCENTERDIRECTOR");
                  	request.getSession().setAttribute("authorityCName", "实验中心主任");
               }else if(judge.indexOf(",2,") != -1){
              	request.getSession().setAttribute("authorityName", "TEACHER");
              	request.getSession().setAttribute("authorityCName", "教师");
              }else if(judge.indexOf(",1,") != -1){
              	request.getSession().setAttribute("authorityName", "STUDENT");
              	request.getSession().setAttribute("authorityCName", "学生");
              }
        }
        shareService.changeRole(request.getSession().getAttribute("authorityName").toString());
        
		if(request.getSession().getAttribute("authorityName") != null && (request.getSession().getAttribute("authorityName").equals("ASSOCIATEDEAN") || request.getSession().getAttribute("authorityName").equals("EXPERIMENTALTEACHING"))){
			mav.setViewName("myCenter/assistantDean.jsp");
		}
		else{
			mav.setViewName("myCenter/myCenter.jsp");
		}
		return mav;
	}

	
	@ResponseBody
	@RequestMapping("/self/changeUserRole")
	public void changeUserRole(@RequestParam String auth,HttpServletRequest request) {
		shareService.changeRole(auth);
		request.getSession().setAttribute("authorityName", auth);
		request.getSession().setAttribute("authorityCName", shareService.findAuthorityByAuthorityName(auth).getCname());
	}
	
	
	@ResponseBody
	@RequestMapping("/self/setTurnTypeSession")
	public String setTurnTypeSession(@RequestParam String sessionType,HttpServletRequest request) {
		System.out.print(request.getServletContext().getContextPath());
		request.getSession().setAttribute("turnType", sessionType);
		return request.getServletContext().getContextPath();
	}
	
	@RequestMapping("/system/downloadFile")
	public void downloadFile(String fileName, HttpServletRequest request,HttpServletResponse response) {
		shareService.downloadFile(fileName,request,response);
	}
	
}