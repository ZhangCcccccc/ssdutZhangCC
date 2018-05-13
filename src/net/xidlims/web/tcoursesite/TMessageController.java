package net.xidlims.web.tcoursesite;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.xidlims.domain.SchoolClasses;
import net.xidlims.domain.TCourseSite;
import net.xidlims.domain.TMessage;
import net.xidlims.domain.User;
import net.xidlims.service.common.ShareService;
import net.xidlims.service.message.MessageService;
import net.xidlims.service.tcoursesite.TCourseSiteService;
import net.xidlims.service.tcoursesite.TCourseSiteUserService;
import net.xidlims.service.tcoursesite.TMessageService;
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


/**************************************************************************
 * Description:通知模块
 * 
 * @author：黄崔俊
 * @date ：2015-8-5 15:50:33
 **************************************************************************/
@Controller("TMessageController")
public class TMessageController<JsonResult> {
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
	@Autowired
	private MessageService messageService;
	

	/**************************************************************************
	 * Description:查看通知列表
	 * 
	 * @author：裴继超
	 * @date ：2016年6月20日14:44:42
	 **************************************************************************/
	@SystemServiceLog("查看通知列表")
	@ResponseBody
	@RequestMapping("/tcoursesite/message/listMessages")
	public ModelAndView listMessages(HttpSession httpSession,@RequestParam Integer tCourseSiteId,
			@RequestParam Integer currpage,
			@RequestParam Integer queryType,@RequestParam String titleQuery) {
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
				flag=1;
			}
			else {
				flag = 0;//如果当前登陆人不是本人开的课程，则是学生身份
			}
			
		}else if(user!=null&&user.getAuthorities().toString().contains("STUDENT")){
			flag = 0;
		}
		mav.addObject("flag", flag);
		//获取该站点下的通知数量
		int totalRecords = tMessageService.getCountTMessageList(tCourseSiteId.toString(),queryType,titleQuery);
		mav.addObject("totalRecords", totalRecords);
		//设置分页大小
		int pageSize = 200;
		//分页信息
		Map<String, Integer> pageModel = shareService.getPage(currpage, pageSize, totalRecords);
		mav.addObject("pageModel", pageModel);
		//将currpage映射到page，用来获取当前页的页码
		mav.addObject("page", currpage);
		//通知列表
		List<TMessage> tMessages = tMessageService.findTMessageList(tCourseSiteId.toString(),queryType,titleQuery,currpage,pageSize);	
		mav.addObject("tMessages",tMessages);
		mav.addObject("queryType", queryType);
		mav.addObject("titleQuery", titleQuery);
		//获取当前时间
		Calendar duedate = Calendar.getInstance();
		//设置时间格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		//格式转换
		String nowDate =sdf.format(duedate.getTime());
		mav.addObject("nowDate",nowDate);
		//新建通知
		mav.addObject("tMessage",new TMessage());
		if(flag==1)//老师身份
		{
			mav.setViewName("tcoursesite/message/listMessages.jsp");
		}else if(flag==0)//学生身份
		{
			mav.setViewName("redirect:/tcoursesite/discuss/listDiscusses?tCourseSiteId="+tCourseSiteId+"&currpage=1");
		}
		return mav;
	}
	
	
	/**************************************************************************
	 * Description:保存通知
	 * 
	 * @author：裴继超
	 * @date ：2016年6月20日14:44:42
	 **************************************************************************/
	@SystemServiceLog("保存通知")
	@ResponseBody
	@RequestMapping("/tcoursesite/message/saveTMessage")
	public ModelAndView saveTMessage(@RequestParam Integer tCourseSiteId,
			@ModelAttribute TMessage tMessage) {
		ModelAndView mav = new ModelAndView();
		// 选择的课程中心
		tMessage.setTCourseSite(tCourseSiteService.findCourseSiteById(tCourseSiteId));
		//如果发布时间为空则设置为当前时间
		if(tMessage.getReleaseTime()==null){
			tMessage.setReleaseTime(Calendar.getInstance());
		}
		//当前登陆人
		tMessage.setUser(shareService.getUser());

		//保存通知
		tMessageService.saveTMessage(tMessage);
		
		mav.setViewName("redirect:/tcoursesite/message/listMessages?tCourseSiteId="+tCourseSiteId+"&currpage=1&queryType=1&titleQuery=");
		return mav;
	}
	
	/**************************************************************************
	 * Description:删除通知
	 * 
	 * @author：裴继超
	 * @date ：2016年6月24日9:44:43
	 **************************************************************************/
	@SystemServiceLog("删除通知")
	@ResponseBody
	@RequestMapping("/tcoursesite/message/deleteMessage")
	public ModelAndView deleteMessage(@RequestParam Integer messageId, Integer tCourseSiteId) {
		ModelAndView mav = new ModelAndView();
		//根据id查询通知
		TMessage tMessage = tMessageService.findTMessageByPrimaryKey(messageId);
		//删除该通知
		tMessageService.deleteTMessage(tMessage);
		mav.setViewName("redirect:/tcoursesite/message/listMessages?tCourseSiteId="+tCourseSiteId
							+"&currpage=1&queryType=1&titleQuery=");
		return mav;
	}
	
	/**************************************************************************
	 * Description:修改通知
	 * 
	 * @author：裴继超
	 * @date ：2016年6月24日9:44:43
	 **************************************************************************/
	@SystemServiceLog("修改通知")
	@RequestMapping("/tcoursesite/message/editMessage")
	@ResponseBody
	public Map<String, Object> editMessage(@RequestParam Integer messageId) {
		//根据id查询通知
		TMessage tMessage = tMessageService.findTMessageByPrimaryKey(messageId);
		//发布时间
		Calendar duedate = tMessage.getReleaseTime();
		//设置时间格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		//格式转换
		String Date =sdf.format(duedate.getTime());
		Map<String, Object> map = new HashMap<String, Object>();
		//通知标题
		map.put("title", (tMessage.getTitle()==null)?"":tMessage.getTitle());
		//通知内容
		map.put("content", (tMessage.getContent()==null)?"":tMessage.getContent());
		//发布时间
		map.put("releaseTime",Date);
		return map;
	}
	/**************************************************************************
	 * Description:查看通知
	 * 
	 * @author：李军凯
	 * @date ：2016-08-30
	 **************************************************************************/
	@SystemServiceLog("查看通知")
	@RequestMapping("/tcoursesite/message/showMessage")
	@ResponseBody
	public Map<String, Object> showMessage(@RequestParam Integer messageId) {
		//根据id查询通知
		TMessage tMessage = tMessageService.findTMessageByPrimaryKey(messageId);
		//发布时间
		Calendar duedate = tMessage.getReleaseTime();
		//设置时间格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		//格式转换
		String Date =sdf.format(duedate.getTime());
		Map<String, Object> map = new HashMap<String, Object>();
		//通知标题
		map.put("showTitle", (tMessage.getTitle()==null)?"":tMessage.getTitle());
		//通知内容
		map.put("showContent", (tMessage.getContent()==null)?"":tMessage.getContent());
		//发布时间
		map.put("showReleaseTime",Date);
		//通知设置为已读
		tMessageService.setTMessageIsread(messageId);
		return map;
	}
	/**************************************************************************
	 * Description:根据通知信息和用户信息保存通知发布的记录
	 * 
	 * @author：裴继超
	 * @date ：2015-9-22
	 **************************************************************************/
	/*@RequestMapping("/tcoursesite/message/publishClassMessage")
	public @ResponseBody String publishClassMessage(@RequestParam String[] messageIds,String[] classnumbers) {
		
     
		List<User> users=tMessageService.findUserListByClassnumber(classnumbers);
		
		tMessageService.publishClassMessage(messageIds, users);
		return htmlEncode("通知发布成功！");
	}
*/
	/**************************************************************************
	 * Description:根据通知信息和用户信息保存通知发布的记录
	 * 
	 * @author：黄崔俊
	 * @date ：2015-8-10 14:18:19
	 **************************************************************************/
	/*@RequestMapping("/tcoursesite/message/saveUserMessage")
	public @ResponseBody String saveUserMessage(@RequestParam String[] messageIds,String[] usernames) {
		//根据通知信息和用户信息保存通知发布的记录
		tMessageService.saveUserMessage(messageIds, usernames);
		return htmlEncode("通知发布成功！");
	}*/
	/**************************************************************************
	 * Description:根据通知id查询通知详情
	 * 
	 * @author：黄崔俊
	 * @date ：2015-8-10 14:18:19
	 **************************************************************************/
	@RequestMapping("/tcoursesite/message/findMessageContentById")
	public @ResponseBody String findMessageContentById(@RequestParam Integer id) {
		//根据通知id查询通知详情
		TMessage tMessage = tMessageService.findTMessageByPrimaryKey(id);
		return htmlEncode(tMessage.getContent());
	}
	
	
	/**************************************************************************
	 * Description:处理ajax中文乱码
	 * 
	 * @author：李小龙
	 * @date ：
	 **************************************************************************/
	public static String htmlEncode(String str) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < str.length(); i++) {
			int c = (int) str.charAt(i);
			if (c > 127 && c != 160) {
				sb.append("&#").append(c).append(";");
			} else {
				sb.append((char) c);
			}
		}
		return sb.toString();
	}
	/**************************************************************************
	 * Description:上传文件 
	 * 
	 * @author：李军凯
	 * @date ：2016-09-27
	 **************************************************************************/
	@RequestMapping("/tcoursesite/message/fileUpload")
	public @ResponseBody String fileUpload(@RequestParam Integer type,HttpServletRequest request) throws Exception {
		int id =  tMessageService.processUpload(type,request);
		return String.valueOf(id);
	}
}