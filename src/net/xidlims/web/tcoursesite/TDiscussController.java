package net.xidlims.web.tcoursesite;


import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.xidlims.domain.SchoolClasses;
import net.xidlims.domain.TCourseSite;
import net.xidlims.domain.TDiscuss;
import net.xidlims.domain.User;
import net.xidlims.service.common.ShareService;
import net.xidlims.service.tcoursesite.TCourseSiteService;
import net.xidlims.service.tcoursesite.TCourseSiteUserService;
import net.xidlims.service.tcoursesite.TDiscussService;
import net.xidlims.web.aop.SystemServiceLog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;


/**************************************************************************************
 * description：讨论模块 
 * 
 * @author：黄崔俊
 * @date：2015-8-5
 *************************************************************************************/
@Controller("TDiscussController")
public class TDiscussController<JsonResult> {
	/**************************************************************************************
	 * @初始化WebDataBinder，这个WebDataBinder用于填充被@InitBinder注释的处理 方法的command和form对象
	 * 
	 *************************************************************************************/
	@InitBinder
	public void initBinder(WebDataBinder binder, HttpServletRequest request) {
		binder.registerCustomEditor(java.util.Calendar.class,
				new org.skyway.spring.util.databinding.CustomCalendarEditor());
		binder.registerCustomEditor(byte[].class,
				new org.springframework.web.multipart.support.ByteArrayMultipartFileEditor());
		binder.registerCustomEditor(boolean.class, new org.skyway.spring.util.databinding.EnhancedBooleanEditor(false));
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
	private TDiscussService tDiscussService;
	@Autowired 
	private ShareService shareService;
	@Autowired 
	private TCourseSiteService tCourseSiteService;
	@Autowired 
	private TCourseSiteUserService tCourseSiteUserService;

	/**************************************************************************
	 * Description:查看讨论列表
	 *  
	 * @author：裴继超
	 * @date ：2016-06-20
	 **************************************************************************/
	@SystemServiceLog("查看讨论列表")
	@ResponseBody
	@RequestMapping("/tcoursesite/discuss/listDiscusses")
	public ModelAndView listDiscusses(HttpSession httpSession,@RequestParam Integer tCourseSiteId,
			@RequestParam Integer currpage) {
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
		mav.addObject("flag", flag);
		//获取该站点下的讨论数量
		int totalRecords = tDiscussService.getCountTDiscussList(tCourseSiteId.toString());
		mav.addObject("totalRecords", totalRecords);
		//设置分页大小
		int pageSize = 200;
		Map<String, Integer> pageModel = shareService.getPage(currpage, pageSize, totalRecords);
		mav.addObject("pageModel", pageModel);
		//将currpage映射到page，用来获取当前页的页码
		mav.addObject("page", currpage);
		List<TDiscuss> tDiscusses = tDiscussService.findTDiscussList(tCourseSiteId.toString(),currpage,pageSize);	
		
		mav.addObject("tDiscusses",tDiscusses);
		
		//新建讨论
		mav.addObject("tDiscuss",new TDiscuss());
		mav.setViewName("tcoursesite/discuss/listDiscusses.jsp");
		return mav;
	}
	

	/**************************************************************************
	 * Description:保存讨论
	 *  
	 * @author：裴继超
	 * @date ：2016-06-20
	 **************************************************************************/
	@SystemServiceLog("保存讨论")
	@ResponseBody
	@RequestMapping("/tcoursesite/discuss/saveTDiscuss")
	public ModelAndView saveTDiscuss(@RequestParam Integer tCourseSiteId,
			@ModelAttribute TDiscuss tDiscuss) {
		ModelAndView mav = new ModelAndView();
		tDiscuss.setTCourseSite(tCourseSiteService.findCourseSiteById(tCourseSiteId));
		tDiscuss.setDiscussTime(Calendar.getInstance());
		tDiscuss.setUser(shareService.getUser());
		tDiscussService.saveTDiscuss(tDiscuss);
		mav.setViewName("redirect:/tcoursesite/discuss/listDiscusses?tCourseSiteId="+tCourseSiteId+"&currpage=1");
		return mav;
	}

	/**************************************************************************
	 * Description:删除讨论
	 *  
	 * @author：裴继超
	 * @date ：2016-06-24
	 **************************************************************************/
	@SystemServiceLog("删除讨论")
	@ResponseBody
	@RequestMapping("/tcoursesite/discuss/deleteDiscuss")
	public ModelAndView deleteDiscuss(@RequestParam Integer discussId, Integer tCourseSiteId) {
		ModelAndView mav = new ModelAndView();
		//根据id查询讨论
		TDiscuss tDiscuss = tDiscussService.findTDiscussByPrimaryKey(discussId);
		//删除该讨论
		tDiscussService.deleteTDiscuss(tDiscuss);
		mav.setViewName("redirect:/tcoursesite/discuss/listDiscusses?tCourseSiteId="+tCourseSiteId
							+"&currpage=1");
		return mav;
	}

	/**************************************************************************
	 * Description:修改讨论
	 *  
	 * @author：裴继超
	 * @date ：2016-06-24
	 **************************************************************************/
	@SystemServiceLog("修改讨论")
	@RequestMapping("/tcoursesite/discuss/editDiscuss")
	@ResponseBody
	public Map<String, Object> editDiscuss(@RequestParam Integer discussId) {
		//根据id查询讨论
		TDiscuss tDiscuss = tDiscussService.findTDiscussByPrimaryKey(discussId);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("title", (tDiscuss.getTitle()==null)?"":tDiscuss.getTitle());// 章节id
		map.put("content", (tDiscuss.getContent()==null)?"":tDiscuss.getContent());
		return map;
	}

	/**************************************************************************
	 * Description:查看讨论及回复
	 *  
	 * @author：裴继超
	 * @date ：2016-06-27
	 **************************************************************************/
	@SystemServiceLog("查看讨论及回复")
	@ResponseBody
	@RequestMapping("/tcoursesite/discuss/tDiscuss")
	public ModelAndView tDiscuss(HttpSession httpSession,@RequestParam Integer tCourseSiteId,
			Integer discussId,@RequestParam Integer currpage) {
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
			}else if (tCourseSiteUserService.isUserBelongToTeacher(tCourseSiteId)){
				flag=1; //如果当前登陆人参与这门课，则是老师身份
			}else {
				flag = 0;//如果当前登陆人不是本人开的课程，则是学生身份
			}
			
		}else if(user!=null&&user.getAuthorities().toString().contains("STUDENT")){
			flag = 0;
		}
		mav.addObject("flag", flag);
		//获取讨论
		TDiscuss discuss = tDiscussService.findTDiscussByPrimaryKey(discussId);
		mav.addObject("discuss", discuss);
		
		//获取该站点下的讨论数量
		int totalRecords = discuss.getTDiscusses().size();
		mav.addObject("totalRecords", totalRecords);
		//设置分页大小
		int pageSize = 200;
		Map<String, Integer> pageModel = shareService.getPage(currpage, pageSize, totalRecords);
		mav.addObject("pageModel", pageModel);
		//将currpage映射到page，用来获取当前页的页码
		mav.addObject("page", currpage);
		List<TDiscuss> tDiscusses = tDiscussService.findTDiscussListByPartent(discussId,currpage,pageSize);	
		
		mav.addObject("tDiscusses",tDiscusses);
		
		//新建讨论
		mav.addObject("tDiscuss",new TDiscuss());
		mav.setViewName("tcoursesite/discuss/tDiscuss.jsp");
		return mav;
	}
	
	/**************************************************************************
	 * Description:保存回复
	 *  
	 * @author：裴继超
	 * @date ：2016-06-20
	 **************************************************************************/
	@SystemServiceLog("保存回复")
	@ResponseBody
	@RequestMapping("/tcoursesite/discuss/saveReply")
	public ModelAndView saveReply(@RequestParam Integer tCourseSiteId,
			Integer partentId,@ModelAttribute TDiscuss tDiscuss) {
		ModelAndView mav = new ModelAndView();
		TDiscuss parent = tDiscussService.findTDiscussByPrimaryKey(partentId);
		tDiscuss.setTDiscuss(parent);
		tDiscuss.setDiscussTime(Calendar.getInstance());
		tDiscuss.setUser(shareService.getUser());
		tDiscussService.saveTDiscuss(tDiscuss);
		mav.setViewName("redirect:/tcoursesite/discuss/tDiscuss?discussId="+partentId+
				"&tCourseSiteId="+tCourseSiteId+"&currpage=1");
		return mav;
	}

	/**************************************************************************
	 * Description:删除回复
	 *  
	 * @author：裴继超
	 * @date ：2016-06-24
	 **************************************************************************/
	@SystemServiceLog("删除回复")
	@ResponseBody
	@RequestMapping("/tcoursesite/discuss/deleteReply")
	public ModelAndView deleteReply(@RequestParam Integer discussId, Integer tCourseSiteId) {
		ModelAndView mav = new ModelAndView();
		//根据id查询讨论
		TDiscuss tDiscuss = tDiscussService.findTDiscussByPrimaryKey(discussId);
		int partentId = tDiscuss.getTDiscuss().getId();
		//删除该回复
		tDiscussService.deleteTDiscuss(tDiscuss);
		mav.setViewName("redirect:/tcoursesite/discuss/tDiscuss?discussId="+partentId+
							"&tCourseSiteId="+tCourseSiteId+"&currpage=1");
		return mav;
	}
	
}