package net.xidlims.web.cms;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.xidlims.dao.TCourseSiteChannelDAO;
import net.xidlims.domain.TCourseSite;
import net.xidlims.domain.TCourseSiteArtical;
import net.xidlims.domain.TCourseSiteChannel;
import net.xidlims.domain.User;
import net.xidlims.service.common.ShareService;
import net.xidlims.service.system.UserDetailService;
import net.xidlims.service.tcoursesite.WkService;
import net.xidlims.service.tcoursesite.TCourseSiteArticalService;
import net.xidlims.service.tcoursesite.TCourseSiteChannelService;
import net.xidlims.service.tcoursesite.TCourseSiteService;

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
 * 功能：课程介绍模块（栏目和文章） 作者：黄崔俊   时间：2015-7-31 
 *****************************************************************************************/
@Controller("ChannelAndArticalController")
@SessionAttributes("selected_labCenter")
public class ChannelAndArticalController<JsonResult> {
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
	private ShareService shareService;
	@Autowired
	private TCourseSiteChannelService tCourseSiteChannelService;
	@Autowired
	private TCourseSiteArticalService tCourseSiteArticalService;
	@Autowired
	private UserDetailService userDetailService;
	@Autowired
	private WkService wkService;
	@Autowired
	private TCourseSiteService tCourseSiteService;
 
	/**************************************************************************************
	 * @课程站点 查看课程介绍(栏目)
	 * @作者：黄崔俊
	 * @日期：2015-7-30 15:51:55
	 *************************************************************************************/
	@RequestMapping("/cms/channel/listCourseChannels")
	public ModelAndView listCourseChannels(HttpSession httpSession) {
		ModelAndView mav = new ModelAndView();
		//List<TCourseSiteChannel> tCourseSiteChannels = tCourseSiteChannelService.findTCourseSiteChannelList(httpSession);	
		List<TCourseSiteChannel> tCourseSiteChannels = new ArrayList<TCourseSiteChannel>();
		List<TCourseSiteChannel> tCourseSiteChannelsTempChannels = tCourseSiteChannelService.findTCourseSiteChannelList();
		for (TCourseSiteChannel tCourseSiteChannel : tCourseSiteChannelsTempChannels) {
			if (tCourseSiteChannel.getTCourseSiteChannelsForParentChannelId().size()==0) {
				tCourseSiteChannels.add(tCourseSiteChannel);
			}
		}
		mav.addObject("tCourseSiteChannels",tCourseSiteChannels);
		/*mav.addObject("siteId", httpSession.getAttribute("selected_courseSite"));
		int siteId=Integer.valueOf(httpSession.getAttribute("selected_courseSite").toString());
		TCourseSite site=tCourseSiteService.findCourseSiteById(siteId);
		mav.addObject("site", site);*/
		mav.setViewName("cms/channel/listCourseChannels.jsp");
		return mav;
	}
	
	/****************************************************************************
	 * 功能：新建课程栏目
	 * 作者：黄崔俊
	 * 时间：2015-7-31 10:00:29
	 ****************************************************************************/
	@RequestMapping("/teaching/channel/newCourseSiteChannel")
	public ModelAndView newCourseSiteChannel(HttpSession httpSession){
		//新建ModelAndView对象；
		ModelAndView mav=new ModelAndView();
		//页面表单对象
		mav.addObject("tCourseSiteChannel", new TCourseSiteChannel());
		//查询已有栏目
		//mav.addObject("tCourseSiteChannels", tCourseSiteChannelService.findTCourseSiteChannelList(httpSession));
		List<TCourseSiteChannel> tCourseSiteChannels = new ArrayList<TCourseSiteChannel>();
		List<TCourseSiteChannel> tCourseSiteChannelsTempChannels = tCourseSiteChannelService.findTCourseSiteChannelList(null);
		for (TCourseSiteChannel tCourseSiteChannel : tCourseSiteChannelsTempChannels) {
			if (tCourseSiteChannel.getTCourseSiteChannelsForParentChannelId().size()==0) {
				tCourseSiteChannels.add(tCourseSiteChannel);
			}
		}
		mav.addObject("tCourseSiteChannels", tCourseSiteChannels);
		//查询课程栏目状态列表
		mav.addObject("cTChannelStates", shareService.getCDictionaryData("c_tcoursesite_state"));
		//查询课程栏目标签列表
		mav.addObject("tCourseSiteTags", tCourseSiteChannelService.findTCourseSiteTagList("1"));

		mav.setViewName("cms/channel/editTCourseChannel.jsp");
		return mav;
	}
	
	/****************************************************************************
	 * 功能：保存课程栏目
	 * 作者：黄崔俊
	 * 时间：2015-7-31 15:37:48
	 ****************************************************************************/
	@RequestMapping("/teaching/channel/saveCourseSiteChannel")
	public String saveCourseSiteChannel(HttpSession httpSession,@ModelAttribute TCourseSiteChannel tCourseSiteChannel,HttpServletRequest request){
		String tCourseSiteId = httpSession.getAttribute("selected_courseSite").toString();
		tCourseSiteChannelService.saveCourseSiteChannel(tCourseSiteId,tCourseSiteChannel,request);
		return "redirect:/cms/channel/listCourseChannels";
	}
	
	/****************************************************************************
	 * 功能：删除课程栏目
	 * 作者：黄崔俊
	 * 时间：2015-8-3 14:19:40
	 ****************************************************************************/
	@RequestMapping("/teaching/channel/deleteCourseSiteChannelById")
	public String deleteCourseSiteChannelById(@RequestParam Integer id){
		//id对应的课程栏目
		TCourseSiteChannel tCourseSiteChannel=tCourseSiteChannelService.findTCourseSiteChannelByPrimaryKey(id);
		//删除栏目图片
		String imageUrl=tCourseSiteChannel.getImageUrl();
		if (imageUrl != null) {
			shareService.deleteFile(imageUrl);
		}
		//删除栏目下文章的图片
		Set<TCourseSiteArtical> articals = tCourseSiteChannel.getTCourseSiteArticals();
		for (TCourseSiteArtical tCourseSiteArtical : articals) {
			if (tCourseSiteArtical.getImageUrl() != null) {
				shareService.deleteFile(tCourseSiteArtical.getImageUrl());
			}
		}
		//删除该课程栏目
		tCourseSiteChannelService.deleteTCourseSiteChannel(tCourseSiteChannel);
		return "redirect:/cms/channel/listCourseChannels";
	}
	
	/****************************************************************************
	 * 功能：修改课程栏目
	 * 作者：黄崔俊
	 * 时间：2015-8-3 14:41:19
	 ****************************************************************************/
	@RequestMapping("/teaching/channel/updateCourseSiteChannelById")
	public ModelAndView updateCourseSiteChannelById(HttpSession httpSession,@RequestParam Integer id){
		//新建ModelAndView对象；
		ModelAndView mav=new ModelAndView();
		TCourseSiteChannel tCourseSiteChannel = tCourseSiteChannelService.findTCourseSiteChannelByPrimaryKey(id);
		//页面表单对象
		mav.addObject("tCourseSiteChannel", tCourseSiteChannel);
		
		//根据已有栏目查询课程为父栏目的栏目
//		mav.addObject("tCourseSiteChannels", tCourseSiteChannelService.findTCourseSiteChannelsTobeParent(tCourseSiteChannel,httpSession));
		mav.addObject("tCourseSiteChannels", tCourseSiteChannelService.findTCourseSiteChannelsTobeParent(tCourseSiteChannel,null));
		//查询课程栏目状态列表
		mav.addObject("cTChannelStates", shareService.getCDictionaryData("c_tcoursesite_state"));
		//查询课程栏目标签列表
		mav.addObject("tCourseSiteTags", tCourseSiteChannelService.findTCourseSiteTagList( "1"));

		mav.setViewName("cms/channel/editTCourseChannel.jsp");
		return mav;
	}
	
	/**************************************************************************************
	 * @课程站点 查看课程介绍(文章)
	 * @作者：黄崔俊
	 * @日期：2015-8-4 10:32:18
	 *************************************************************************************/
	@RequestMapping("/teaching/artical/listCourseArticals")
	public ModelAndView listCourseArticals(@RequestParam Integer id,@RequestParam Integer currpage) {
		ModelAndView mav = new ModelAndView();
		//id对应的课程栏目
		TCourseSiteChannel tCourseSiteChannel=tCourseSiteChannelService.findTCourseSiteChannelByPrimaryKey(id);
		//获取该栏目下总的文章数
		int totalRecords = tCourseSiteChannel.getTCourseSiteArticals().size();
		mav.addObject("totalRecords", totalRecords);
		//设置分页大小
		int pageSize = 20;
		Map<String, Integer> pageModel = shareService.getPage(currpage, pageSize, totalRecords);
		mav.addObject("pageModel", pageModel);
		//将currpage映射到page，用来获取当前页的页码
		mav.addObject("page", currpage);
		//将所属栏目传回去，分页查询需要用到栏目id
		mav.addObject("tCourseSiteChannel",tCourseSiteChannel);
		mav.addObject("listCourseArticals",tCourseSiteArticalService.findTCourseSiteArticalsByChannel(tCourseSiteChannel, currpage, pageSize));
		mav.setViewName("cms/artical/listCourseArticals.jsp");
		return mav;
	}
	
	/****************************************************************************
	 * 功能：新建课程文章
	 * 作者：黄崔俊
	 * 时间：2015-8-4 14:00:47
	 ****************************************************************************/
	@RequestMapping("/teaching/artical/newCourseSiteArtical")
	public ModelAndView newCourseSiteArtical(HttpSession httpSession,@RequestParam int channelId){
		//新建ModelAndView对象；
		ModelAndView mav=new ModelAndView();
		// 获取登录用户
		User user = shareService.getUser();
		mav.addObject("user", user);
		//获取当前时间
		String date =shareService.getDate();
		mav.addObject("date", date);
		
		TCourseSiteChannel channel=tCourseSiteChannelService.findTCourseSiteChannelByPrimaryKey(channelId);
		mav.addObject("channel", channel);
		TCourseSiteArtical tCourseSiteArtical=new TCourseSiteArtical();
		//创建时间默认为当前时间
		tCourseSiteArtical.setCreateDate(Calendar.getInstance());
		//创建人默认为当前登录人
		tCourseSiteArtical.setCreateUser(user);
		//默认为当前所在的栏目
		tCourseSiteArtical.setTCourseSiteChannel(channel);
		//页面表单对象
		mav.addObject("tCourseSiteArtical",tCourseSiteArtical);
		//查询已有项目
		mav.addObject("tCourseSiteChannels", tCourseSiteChannelService.findTCourseSiteChannelList(null));
		//查询课程文章状态列表
		mav.addObject("cTChannelStates", shareService.getCDictionaryData("c_tcoursesite_state"));
		//查询课程文章标签列表
		mav.addObject("tCourseSiteTags", tCourseSiteChannelService.findTCourseSiteTagList("2"));
		
		int result = 0;
		if (channel.getTCourseSiteChannelsForParentChannelId().size()>0
			&&channel.getTCourseSiteChannelsForParentChannelId().iterator().next().getTCourseSiteTag().getId()==21) {//如果文章所属栏目属于课件栏目
			result = 1;
		}
		mav.addObject("result", result);
		mav.setViewName("cms/artical/editTCourseArtical.jsp");
		return mav;
	}
	
	/****************************************************************************
	 * 功能：保存课程文章
	 * 作者：黄崔俊
	 * 时间：2015-8-4 16:22:11
	 ****************************************************************************/
	@RequestMapping("teaching/artical/saveCourseSiteArtical")
	public String saveCourseSiteArtical(@ModelAttribute TCourseSiteArtical tCourseSiteArtical,HttpServletRequest request){
		
		tCourseSiteArticalService.saveCourseSiteArtical(tCourseSiteArtical,request);
		Integer tCourseSiteChannelId = tCourseSiteArtical.getTCourseSiteChannel().getId();
		return "redirect:/teaching/artical/listCourseArticals?id="+tCourseSiteChannelId+"&currpage=1";
	}
	
	/****************************************************************************
	 * 功能：删除课程文章
	 * 作者：黄崔俊
	 * 时间：2015-8-5 09:25:57
	 ****************************************************************************/
	@RequestMapping("/teaching/artical/deleteCourseSiteArticalById")
	public String deleteCourseSiteArticalById(@RequestParam Integer id){
		//id对应的课程文章
		TCourseSiteArtical tCourseSiteArtical = tCourseSiteArticalService.findTCourseSiteArticalByPrimaryKey(id);
		//删除文章图片
		String imageUrl=tCourseSiteArtical.getImageUrl();
		if(imageUrl != null){
			shareService.deleteFile(imageUrl);
		}
		//所属栏目id
		int pid=tCourseSiteArtical.getTCourseSiteChannel().getId();
		//删除该课程文章
		tCourseSiteArticalService.deleteTCourseSiteArtical(tCourseSiteArtical);
		return "redirect:/teaching/artical/listCourseArticals?id="+pid+"&currpage=1";
	}
	
	/****************************************************************************
	 * 功能：批量删除课程文章
	 * 作者：黄崔俊
	 * 时间：2015-8-6 17:08:09
	 ****************************************************************************/
	@RequestMapping("/teaching/artical/deleteCourseSiteArticalByIds")
	public String deleteCourseSiteArticalByIds(@RequestParam Integer id,String ids){
		//删除选中的课程文章
		tCourseSiteArticalService.deleteCourseSiteArticalByIds(ids);
		return "redirect:/teaching/artical/listCourseArticals?id="+id+"&currpage=1";
	}
	
	/****************************************************************************
	 * 功能：修改课程文章
	 * 作者：黄崔俊
	 * 时间：2015-8-5 09:25:57
	 ****************************************************************************/
	@RequestMapping("/teaching/artical/updateCourseSiteArticalById")
	public ModelAndView updateCourseSiteArticalById(HttpSession httpSession,@RequestParam Integer id){
		//新建ModelAndView对象；
		ModelAndView mav=new ModelAndView();
		//id对应的课程文章
		TCourseSiteArtical tCourseSiteArtical = tCourseSiteArticalService.findTCourseSiteArticalByPrimaryKey(id);
		//页面表单对象
		mav.addObject("tCourseSiteArtical", tCourseSiteArtical);
		//查询已有栏目
		mav.addObject("tCourseSiteChannels", tCourseSiteChannelService.findTCourseSiteChannelList(null));
		//查询课程文章状态列表
		mav.addObject("cTChannelStates", shareService.getCDictionaryData("c_tcoursesite_state"));
		//查询课程文章标签列表
		mav.addObject("tCourseSiteTags", tCourseSiteChannelService.findTCourseSiteTagList("2"));
		
		int result = 0;
		if (tCourseSiteArtical.getTCourseSiteChannel().getTCourseSiteChannelsForParentChannelId().size()>0
			&&tCourseSiteArtical.getTCourseSiteChannel().getTCourseSiteChannelsForParentChannelId().iterator().next().getTCourseSiteTag().getId()==21) {//如果文章所属栏目属于课件栏目
			result = 1;
		}
		mav.addObject("result", result);
		mav.setViewName("cms/artical/editTCourseArtical.jsp");
		return mav;
	}
	
	/****************************************************************************
	 * 功能：查询输入的工号/学号是否存在
	 * 作者：黄崔俊
	 * 时间：2015-8-5 11:03:01
	 ****************************************************************************/
	@RequestMapping("/teaching/artical/checkUser")
	public @ResponseBody boolean checkUser(@RequestParam String username){
		boolean b = true;
		User user = userDetailService.findUserByNum(username);
		if (user==null) {
			b = false;//false表示该工号/学号不存在
		}
		return b;
	}
	/**********************************************************************************
	 *  课程的文件管理模块(树形图)
	 *  
	 **********************************************************************************/
	@RequestMapping("/cms/getContentsList")
	public @ResponseBody  String  getContentsList(HttpServletRequest request,@RequestParam Integer id){
		String str=wkService.findCmsFolderHTMLById(request,id);
		
		return shareService.htmlEncode(str);
	}
	
	/****************************************************************************
	 * 功能：查询当前栏目的父栏目是否存在以及其父栏目的标签是否为教学课件
	 * 作者：黄崔俊
	 * 时间：2015-12-14 09:17:59
	 ****************************************************************************/
	@RequestMapping("/cms/channel/judgeIsCourseware")
	public @ResponseBody boolean judgeIsCourseware(@RequestParam Integer channelId){
		boolean b = false;
		TCourseSiteChannel channel = tCourseSiteChannelService.findTCourseSiteChannelByPrimaryKey(channelId);
		if (channel.getTCourseSiteChannelsForParentChannelId().size()>0
			&&channel.getTCourseSiteChannelsForParentChannelId().iterator().next().getTCourseSiteTag().getId()==21) {//如果文章所属栏目属于课件栏目
			b = true;
		}
		return b;
	}
}