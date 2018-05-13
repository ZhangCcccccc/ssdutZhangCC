package net.xidlims.web.teaching;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.xidlims.dao.TCourseSiteChannelDAO;
import net.xidlims.domain.TCourseSite;
import net.xidlims.domain.TCourseSiteArtical;
import net.xidlims.domain.TCourseSiteChannel;
import net.xidlims.domain.TMessage;
import net.xidlims.domain.User;
import net.xidlims.service.common.ShareService;
import net.xidlims.service.system.UserDetailService;
import net.xidlims.service.tcoursesite.TCourseSiteArticalService;
import net.xidlims.service.tcoursesite.TCourseSiteChannelService;
import net.xidlims.service.tcoursesite.TCourseSiteService;
import net.xidlims.service.tcoursesite.TMessageService;

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


/******************************************************************************************
 * 功能：通知模块 作者：黄崔俊   时间：2015-8-5 15:50:33
 *****************************************************************************************/
@Controller("TeachingMessageController")
@SessionAttributes("selected_labCenter")
public class TeachingMessageController<JsonResult> {
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

	/**************************************************************************************
	 * @功能：查看通知列表
	 * @作者：黄崔俊
	 * @日期：2015-8-5 15:51:25
	 *************************************************************************************/
	@RequestMapping("/teaching/message/listMessages")
	public ModelAndView listMessages(HttpSession httpSession,@RequestParam Integer currpage,
			@RequestParam Integer queryType,@RequestParam String titleQuery) {
		ModelAndView mav = new ModelAndView();
		//获取该站点下的通知数量
		int totalRecords = tMessageService.getCountTMessageList(httpSession.getAttribute("selected_courseSite").toString(),queryType,titleQuery);
		mav.addObject("totalRecords", totalRecords);
		//设置分页大小
		int pageSize = 20;
		Map<String, Integer> pageModel = shareService.getPage(currpage, pageSize, totalRecords);
		mav.addObject("pageModel", pageModel);
		//将currpage映射到page，用来获取当前页的页码
		mav.addObject("page", currpage);
		List<TMessage> tMessages = tMessageService.findTMessageList(httpSession.getAttribute("selected_courseSite").toString(),queryType,titleQuery,currpage,pageSize);	
		mav.addObject("tMessages",tMessages);
		mav.addObject("queryType", queryType);
		mav.addObject("titleQuery", titleQuery);
		mav.setViewName("teaching/message/listMessages.jsp");
		return mav;
	}
	
	/**************************************************************************************
	 * @功能：新建通知
	 * @作者：黄崔俊
	 * @日期：2015-8-6 14:09:03
	 *************************************************************************************/
	@RequestMapping("/teaching/message/newTMessage")
	public ModelAndView newTMessage(HttpSession httpSession) {
		ModelAndView mav = new ModelAndView();
		TCourseSite tCourseSite = tCourseSiteService.findTCourseSiteById(httpSession.getAttribute("selected_courseSite").toString());
		TMessage tMessage = new TMessage();
		tMessage.setTCourseSite(tCourseSite);
		mav.addObject("tMessage",tMessage);
		mav.setViewName("teaching/message/editTMessage.jsp");
		return mav;
	}
	
	/**************************************************************************************
	 * @功能：保存通知
	 * @作者：黄崔俊
	 * @日期：2015-8-6 14:33:44
	 *************************************************************************************/
	@RequestMapping("/teaching/message/saveTMessage")
	public String saveTMessage(@ModelAttribute TMessage tMessage) {
		tMessageService.saveTMessage(tMessage);
		return "redirect:/teaching/message/listMessages?currpage=1&queryType=1&titleQuery=";
	}
	
	/**************************************************************************************
	 * @功能：删除通知
	 * @作者：黄崔俊
	 * @日期：2015-8-6 15:03:39
	 *************************************************************************************/
	@RequestMapping("/teaching/message/deleteTMessageById")
	public String deleteTMessageById(@RequestParam Integer id) {
		//根据id查询通知
		TMessage tMessage = tMessageService.findTMessageByPrimaryKey(id);
		//删除该通知
		tMessageService.deleteTMessage(tMessage);
		return "redirect:/teaching/message/listMessages?currpage=1&queryType=1&titleQuery=";
	}
	
	/**************************************************************************************
	 * @功能：修改通知
	 * @作者：黄崔俊
	 * @日期：2015-8-6 15:03:39
	 *************************************************************************************/
	@RequestMapping("/teaching/message/updateTMessageById")
	public ModelAndView updateTMessageById(@RequestParam Integer id) {
		ModelAndView mav  = new ModelAndView();
		//根据id查询通知
		TMessage tMessage = tMessageService.findTMessageByPrimaryKey(id);
		mav.addObject("tMessage", tMessage);
		mav.setViewName("teaching/message/editTMessage.jsp");
		return mav;
	}
	
	/**************************************************************************************
	 * @功能：根据班级和姓名查询用户列表
	 * @作者：黄崔俊
	 * @日期：2015-8-10 10:03:53
	 *************************************************************************************/
	@RequestMapping("/teaching/message/findUserByClassnameAndCname")
	public @ResponseBody String findUserByClassnameAndCname(@RequestParam String classname,String cname,Integer page) {
		int totalRecords = tMessageService.getCountUserListByClassnameAndCname(classname,cname); 
		int pageSize=10;
		Map<String,Integer> pageModel =shareService.getPage(page, pageSize,totalRecords);
		List<User> users = tMessageService.findUserListByClassnameAndCname(classname,cname,page,pageSize);
		//分页开始
	    String s="";
	    for (User d : users) {
			s+="<tr>"+
			"<td><input type='checkbox' name='CK_name' value='"+d.getUsername()+"' onchange='checkUserChecked(this)'/></td>"+
	    	"<td>"+d.getSchoolClasses().getClassName()+"</td>"+
			"<td>"+d.getUsername()+"</td>"+
			"<td>"+d.getCname()+"</td>"+
			
			"</tr>";			
		}
	    s+="<tr><td colspan='6'>"+
	    	    "<a href='javascript:void(0)' onclick='firstPage(1);'>"+"首页"+"</a>&nbsp;"+
	    	    "<a href='javascript:void(0)' onclick='previousPage("+page+");'>"+"上一页"+"</a>&nbsp;"+
	    	    "<a href='javascript:void(0)' onclick='nextPage("+page+","+pageModel.get("totalPage")+");'>"+"下一页"+"</a>&nbsp;"+
	    	    "<a href='javascript:void(0)' onclick='lastPage("+pageModel.get("totalPage")+");'>"+"末页"+"</a>&nbsp;"+
	    	    "当前第"+page+"页&nbsp; 共"+pageModel.get("totalPage")+"页  "+totalRecords+"条记录"+
	    	    		"</td></tr>";
				return htmlEncode(s);
	}
	
	/**************************************************************************************
	 * @功能：根据通知信息和用户信息保存通知发布的记录
	 * @作者：黄崔俊
	 * @日期：2015-8-10 14:18:19
	 *************************************************************************************/
	/*@RequestMapping("/teaching/message/saveUserMessage")
	public @ResponseBody String saveUserMessage(@RequestParam String[] messageIds,String[] usernames) {
		
		tMessageService.saveUserMessage(messageIds, usernames);
		return htmlEncode("通知发布成功！");
	}
	*/
	
	/****************************************************************************
	 * 功能：处理ajax中文乱码
	 * 作者：李小龙
	 ****************************************************************************/
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
}