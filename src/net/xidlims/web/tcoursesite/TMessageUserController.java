package net.xidlims.web.tcoursesite;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.xidlims.domain.SchoolClasses;
import net.xidlims.domain.TAssignmentQuestionpool;
import net.xidlims.domain.TCourseSite;
import net.xidlims.domain.TCourseSiteGroup;
import net.xidlims.domain.TCourseSiteUser;
import net.xidlims.domain.TMessage;
import net.xidlims.domain.TMessageAttachment;
import net.xidlims.domain.TMessageUser;
import net.xidlims.domain.User;
import net.xidlims.service.common.ShareService;
import net.xidlims.service.tcoursesite.TCourseSiteService;
import net.xidlims.service.tcoursesite.TCourseSiteUserService;
import net.xidlims.service.tcoursesite.TMessageService;
import net.xidlims.web.aop.SystemServiceLog;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

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

/**************************************************************************
 * Description:通知模块
 * 
 * @author：黄崔俊
 * @date ：2015-8-5 15:50:33
 **************************************************************************/
@Controller("TMessageUserController")
public class TMessageUserController<JsonResult> {
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
	private TMessageService tMessageService;
	@Autowired 
	private ShareService shareService;
	@Autowired 
	private TCourseSiteService tCourseSiteService;
	@Autowired 
	private TCourseSiteUserService tCourseSiteUserService;
	/**************************************************************************
	 * Description:消息-新建消息
	 *  
	 * @author：李军凯
	 * @date ：2016-09-21
	 **************************************************************************/
	@SystemServiceLog("新建消息")
	@ResponseBody
	@RequestMapping("/tcoursesite/message/newMessageUser")
	public ModelAndView newMessageUser(HttpSession httpSession,@RequestParam Integer tCourseSiteId) {
		ModelAndView mav = new ModelAndView();
		// 选择的课程中心
		TCourseSite tCourseSite = tCourseSiteService.findCourseSiteById(tCourseSiteId);
		mav.addObject("tCourseSite", tCourseSite);
		//当前登录人
		User user = shareService.getUser();
		mav.addObject("user", user);
		//角色判断：如果具有老师权限且为该课程的创建者或者助教则默认为老师，如果没有教师权限则默认为学生
		/*Integer flag = 2;
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
		mav.addObject("flag", flag);*/
		
		//学生列表
		List<TCourseSiteUser> tCourseSiteUsers = tCourseSiteUserService.findAlltCourseSiteUsers(tCourseSiteId);
		mav.addObject("tCourseSiteUsers",tCourseSiteUsers);
		//分组列表
		List<TCourseSiteGroup> tCourseSiteGroups = tCourseSiteUserService.findTCourseSiteGroupsBySiteId(tCourseSiteId);
		mav.addObject("tCourseSiteGroups",tCourseSiteGroups);
		//获取当前时间
		Calendar duedate = Calendar.getInstance();
		//设置时间格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		//格式转换
		String nowDate =sdf.format(duedate.getTime());
		mav.addObject("nowDate",nowDate);
		//新建通知
		mav.addObject("tMessage",new TMessage());
		mav.setViewName("tcoursesite/message/messageUser.jsp");
		return mav;
	}
	/**************************************************************************
	 * Description:消息-保存消息 
	 *  
	 * @author：李军凯
	 * @date ：2016-09-21
	 **************************************************************************/
	@SystemServiceLog("保存消息")
	@ResponseBody
	@RequestMapping("/tcoursesite/message/saveTMessageUser")
	public ModelAndView saveTMessageUser(@RequestParam Integer tCourseSiteId,String documentsList,String classPurview,HttpSession httpSession,HttpServletRequest request,
			@ModelAttribute TMessage tMessage) throws NumberFormatException, ParseException {
		ModelAndView mav = new ModelAndView();		
		// 选择的课程中心
		tMessage.setTCourseSite(tCourseSiteService.findCourseSiteById(tCourseSiteId));
		//当前登陆人
		tMessage.setUser(shareService.getUser());
		//时间
		String time = request.getParameter("releaseTime");
		if(time != null&& time !=""){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date RelaseDate = sdf.parse(time);
		Calendar timeCalendar = Calendar.getInstance();
		timeCalendar.setTime(RelaseDate);
		tMessage.setReleaseTime(timeCalendar);
		}
		//内容
		String content = tMessage.getContent();
		content = content.replaceAll("src=\"/ueditor/jsp/upload", "src=\"/xidlims/ueditor/jsp/upload");
		tMessage.setContent(content);
		
		//获取消息类型
		String[] type = request.getParameterValues("checkname");
		if(type.length==2){//邮件和消息
			tMessage.setType(201);
		}
		else{
			if(type[0].equals("email")){//邮件
				tMessage.setType(301);
			}
			if(type[0].equals("info")){//信息
				tMessage.setType(200);
			}
		}
		//保存消息
		tMessage = tMessageService.saveTMessageInfo(tMessage);
		//保存附件
		if(documentsList != null){
		String[] attachments= documentsList.split(",");
		for(String a:attachments){
			if(a != null && !a.equals(""))
			{
				TMessageAttachment t = tMessageService.findUpload(Integer.parseInt(a));
				t.setMessageId(tMessage.getId());
				tMessageService.saveUpload(t);
			}
		}
		}
		//获取学生id
		String[] ids = classPurview.split(",");
		//保存消息与发送对象
		if(ids != null){
			for(String id:ids){
				if(!id.contains("group") && id!= null && !id.equals("")){
				String username = tCourseSiteUserService.findTCourseSiteUserById(Integer.parseInt(id)).getUser().getUsername();
				tMessageService.saveTMessageuser(tMessage.getId(), username);
				}
			}
		  }		
		mav.setViewName("redirect:/tcoursesite/message/newMessageUser?tCourseSiteId="+tCourseSiteId);
		return mav;
	}
	/**************************************************************************
	 * Description:消息-立即发送消息
	 *  
	 * @author：李军凯
	 * @date ：2016-09-22
	 **************************************************************************/
	@SystemServiceLog("保存并立即发送的消息")
	@ResponseBody
	@RequestMapping("/tcoursesite/message/saveTMessageUserNow")
	public ModelAndView saveTMessageUserNow(@RequestParam Integer tCourseSiteId,String documentsList,String classPurviewNow,HttpSession httpSession,HttpServletRequest request,
			@ModelAttribute TMessage tMessage) throws NumberFormatException, ParseException {
		ModelAndView mav = new ModelAndView();		
		// 选择的课程中心
		tMessage.setTCourseSite(tCourseSiteService.findCourseSiteById(tCourseSiteId));
		//当前登陆人
		tMessage.setUser(shareService.getUser());
		//获取消息类型
		String[] type = request.getParameterValues("checkname");
		if(type.length==2){//邮件和消息
			tMessage.setType(202);
		}
		else{
			tMessage.setType(302);//只发送邮件		
		}
		//时间
		tMessage.setReleaseTime(Calendar.getInstance());
		//保存消息
		tMessage = tMessageService.saveTMessageInfo(tMessage);
		//保存附件
		String[] attachments= documentsList.split(",");
		for(String a:attachments){
			if(a != null && !a.equals(""))
			{
				TMessageAttachment t = tMessageService.findUpload(Integer.parseInt(a));
				t.setMessageId(tMessage.getId());
				tMessageService.saveUpload(t);
			}
		}
		//获取学生id
		String[] ids = classPurviewNow.split(",");
		//保存消息发送对象并发送消息
		if(ids != null){
			for(String id:ids){
				if(!id.contains("group") && id!= null && !id.equals("")){
				String username = tCourseSiteUserService.findTCourseSiteUserById(Integer.parseInt(id)).getUser().getUsername();
				tMessageService.saveTMessageuser(tMessage.getId(), username);
				tMessageService.sendMail(tMessage,
						tCourseSiteUserService.findTCourseSiteUserById(Integer.parseInt(id)).getUser().getEmail(), request);
				}
			}
		  }	
		mav.setViewName("redirect:/tcoursesite/message/newMessageUser?tCourseSiteId="+tCourseSiteId);
		return mav;
	}
	/**************************************************************************
	 * Description:消息-查看课程下的所有消息
	 *  
	 * @author：李军凯
	 * @date ：2016-09-22
	 **************************************************************************/
	@RequestMapping("/tcoursesite/message/findAllMessageUsers")
	public ModelAndView findAllMessageUsers(HttpSession httpSession,@RequestParam Integer tCourseSiteId) {
		ModelAndView mav = new ModelAndView();
		// 选择的课程中心
		TCourseSite tCourseSite = tCourseSiteService.findCourseSiteById(tCourseSiteId);
		mav.addObject("tCourseSite", tCourseSite);
		//已发邮件列表
		List<TMessage> tMessages = new ArrayList<TMessage>();
		tMessages =	tMessageService.findAllTMessageListBytCourseSiteId( Integer.toString(tCourseSiteId));
		mav.addObject("tMessages",tMessages);
		mav.setViewName("tcoursesite/message/sendMessageList.jsp");
		return mav;
	}
	/**************************************************************************
	 * Description:消息-查看消息的发送对象
	 * @author：李军凯
	 * @date ：2016-09-02
	 **************************************************************************/
	@RequestMapping("/tcoursesite/message/findAllMessageUsersBytMessageId")
	public @ResponseBody Map<Integer,String> findAllMessageUsersBytMessageId(@RequestParam Integer tMessageId) {		
		Map<Integer,String> map = new HashMap<Integer,String>();
		//发送对象List
		List<TMessageUser> tMessageUsers = new ArrayList<TMessageUser>();
		tMessageUsers = tMessageService.findAllTMessageUserListByMessageId(tMessageId);	
		for(TMessageUser t:tMessageUsers){
		map.put(t.getId(),t.getUsername());
		}		
		return map;
	}
	/**************************************************************************
	 * Description:消息-生成联系组的目录
	 * @author：李军凯
	 * @date ：2016-09-29
	 **************************************************************************/
	@RequestMapping("/tcoursesite/message/getcpartyMenu")
	public @ResponseBody
	JSONArray getcpartyMenu(@RequestParam Integer tCourseSiteId) {
		// Json数组（返回值，这个类型是由ztree决定的）
		JSONArray jsonArray =  tCourseSiteUserService.findtCourseSiteUserJSONArray(tCourseSiteId);		
		return jsonArray;
	}
	
}