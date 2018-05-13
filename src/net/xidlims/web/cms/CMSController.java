package net.xidlims.web.cms;

import java.nio.channels.Channel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;




import net.xidlims.dao.CmsLinkDAO;
import net.xidlims.dao.CmsTagDAO;
import net.xidlims.domain.Authority;
import net.xidlims.domain.CmsChannel;
import net.xidlims.domain.CmsArticle;
import net.xidlims.domain.CmsLink;
import net.xidlims.domain.CmsResource;
import net.xidlims.domain.LabReservation;
import net.xidlims.domain.LabRoomDevice;
import net.xidlims.domain.LabRoomDeviceReservation;
import net.xidlims.domain.TAssignmentGrading;
import net.xidlims.domain.TCourseSite;
import net.xidlims.domain.TCourseSiteArtical;
import net.xidlims.domain.TCourseSiteChannel;
import net.xidlims.domain.TCourseSiteUser;
import net.xidlims.domain.TMessage;
import net.xidlims.domain.TimetableAppointmentSameNumber;
import net.xidlims.domain.User;
import net.xidlims.domain.CmsLink;
import net.xidlims.domain.CmsTag;
import net.xidlims.domain.WkCourse;
import net.xidlims.domain.WkFolder;
import net.xidlims.domain.WkLesson;
import net.xidlims.service.cms.CmsChannelService;
import net.xidlims.service.cms.CmsArticleService;
import net.xidlims.service.cms.CmsService;
import net.xidlims.service.cms.CmsSystemService;
import net.xidlims.service.common.ShareService;
import net.xidlims.service.device.LabRoomDeviceService;
import net.xidlims.service.lab.LabReservationService;
import net.xidlims.service.teaching.TAssignmentForTestService;
import net.xidlims.service.teaching.TAssignmentGradingService;
import net.xidlims.service.tcoursesite.TAssignmentService;
import net.xidlims.service.tcoursesite.TCourseSiteArticalService;
import net.xidlims.service.tcoursesite.TCourseSiteChannelService;
import net.xidlims.service.tcoursesite.TCourseSiteService;
import net.xidlims.service.tcoursesite.TMessageService;
import net.xidlims.service.tcoursesite.WkService;
import net.xidlims.view.ViewTAssignment;
import net.xidlims.web.PageModel;

import org.apache.commons.net.nntp.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import excelTools.Labreservationlist;



/******************************************************************************************
 * 功能：CMS（课程）模块 作者：李小龙 时间：2015-8-10 15:08:50
 *****************************************************************************************/
@Controller("CMSController")
public class CMSController<JsonResult> {
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
	private TCourseSiteService tCourseSiteService;
	@Autowired
	private WkService wkService;
	@Autowired
	private TAssignmentService tAssignmentService;
	@Autowired
	private TAssignmentGradingService tAssignmentGradingService;
	@Autowired
	private TMessageService tMessageService;
	@Autowired
	private TAssignmentForTestService tAssignmentForTestService;
	@Autowired
	private CmsSystemService cmsSystemService;
	@Autowired
	private LabReservationService labReservationService;
	@Autowired
	private LabRoomDeviceService labRoomDeviceService;
	@Autowired
	private CmsService cmsService;
	@Autowired
	private CmsChannelService cmschannelService;
	@Autowired
	private CmsArticleService cmsarticleService;
	@Autowired
	private CmsLinkDAO linkDAO;
	@Autowired
	private CmsTagDAO linktagDAO;

	/**************************************************************************************
	 * @功能 查看课程站点
	 * @作者：李小龙
	 * @日期：2015-8-10
	 *************************************************************************************/
	@RequestMapping("cms/courseSite")
	public ModelAndView courseSite(HttpServletRequest request,@RequestParam Integer id) {
		ModelAndView mav = new ModelAndView();
		// id对应的课程
		TCourseSite course = tCourseSiteService.findCourseSiteById(id);
		mav.addObject("course", course);
		// 该课程正常活动的栏目
		List<TCourseSiteChannel> channels = tCourseSiteService.findChannelsBySiteIdAndState(id, 1);
		mav.addObject("channels", channels);

		// 获取登录用户
		User user = shareService.getUser();
		mav.addObject("user", user);
		
		/*
		 * 角色判断：如果具有老师权限则默认为老师，如果没有教师权限则默认为学生
		*/
		Integer flag = 2;
		if(user!=null&&user.getAuthorities().toString().contains("TEACHER")){
			if (course.getUserByCreatedBy().getUsername().equals(user.getUsername())) {
				flag = 1;//如果当前登陆人是本人开的课程，则是老师身份
			}else {
				flag = 0;//如果当前登陆人不是本人开的课程，则是学生身份
			}
			
		}else if(user!=null&&user.getAuthorities().toString().contains("STUDENT")){
			request.getSession().setAttribute("selected_role", "ROLE_STUDENT");
			flag = 0;
		}
		
		// 资源
		WkCourse wkCourse = wkService.findWkCourseByPrimaryKey(id);// 得到课程
		mav.addObject("wkCourse", wkCourse);// 返回课程
		if (wkCourse != null) {
			/*List<WkUpload> uploads = new ArrayList<WkUpload>();// 要返回的集合
			for (WkChapter chapter : wkCourse.getWkChapters()) {// 遍历课程每个章节
				uploads.addAll(wkService.getUpload(chapter.getFileList()));// 返回课程的文件
			}
			uploads.addAll(wkService.getUpload(wkCourse.getFilesList()));// 返回课程的文件
			mav.addObject("uploads", uploads);// 返回所有的文件*/
			
			//根据课程id查找一级目录
			List<WkFolder> folders=wkService.findFirstFolderBySiteId(id);
			mav.addObject("folders",folders);

			mav.addObject("lesson", new WkLesson());// 返回新课时存放数据
			mav.addObject("active", "files");
		}
		
//		if (user != null) {
//			List<ViewTAssignment> viewTAssignments = tAssignmentService.findTAssignmentList(user,id,flag);
//			mav.addObject("viewTAssignments", viewTAssignments);
//			
//			List<ViewTAssignment> viewExams = tAssignmentService.findViewExamList(user,id,flag);
//			mav.addObject("viewExams", viewExams);
//			
//			List<ViewTAssignment> viewTests = tAssignmentForTestService.findTestList(user, id, "test", 1);
//			mav.addObject("viewTests", viewTests);
//			
//			//已提交测试的成绩
//			List<TAssignmentGrading> tAssignmentGradings = tAssignmentGradingService.findTAssignmentGradingList(id,user,"exam");     
//			mav.addObject("TAssignmentGradings",tAssignmentGradings);
//			
//			//查询通知列表
//			List<TMessage> tMessages = tMessageService.findTMessageListByUserAndSite(id,user);
//			mav.addObject("tMessages", tMessages);
//		}	

		
		
		
		
	
		
		request.getSession().setAttribute("selected_courseSite", id);
		mav.setViewName("cms/courseSite/courseSite.jsp");
		return mav;
	}

	/**************************************************************************************
	 * @功能 查看教师详细信息
	 * @作者：李小龙
	 * @日期：2015-8-12
	 *************************************************************************************/
	@RequestMapping("/cms/teacher")
	public ModelAndView teacher(@RequestParam Integer id) {
		ModelAndView mav = new ModelAndView();
		// id对应的新闻
		TCourseSiteArtical artical = tCourseSiteArticalService.findTCourseSiteArticalByPrimaryKey(id);
		mav.addObject("artical", artical);
		// 课程站点
		TCourseSite course = artical.getTCourseSiteChannel().getTCourseSite();
		mav.addObject("course", course);
		mav.setViewName("cms/courseSite/teacher.jsp");
		return mav;
	}
	
	

	/**************************************************************************************
	 * @功能 查看教材详细信息
	 * @作者：黄崔俊
	 * @日期：2015-9-9 15:17:51
	 *************************************************************************************/
	@RequestMapping("/cms/book")
	public ModelAndView book(@RequestParam Integer id) {
		ModelAndView mav = new ModelAndView();
		// id对应的新闻
		TCourseSiteArtical artical = tCourseSiteArticalService.findTCourseSiteArticalByPrimaryKey(id);
		mav.addObject("artical", artical);
		// 课程站点
		TCourseSite course = artical.getTCourseSiteChannel().getTCourseSite();
		mav.addObject("course", course);
		mav.setViewName("cms/courseSite/book.jsp");
		return mav;
	}

	/**************************************************************************************
	 * @功能 查看教学介绍
	 * @作者：李小龙
	 * @日期：2015-8-12
	 *************************************************************************************/
	@RequestMapping("/cms/teachingIntroduction")
	public ModelAndView teachingIntroduction(@RequestParam Integer id) {
		ModelAndView mav = new ModelAndView();
		// id对应的栏目
		TCourseSiteChannel channel = tCourseSiteChannelService.findTCourseSiteChannelByPrimaryKey(id);
		mav.addObject("channel", channel);
		// 课程站点
		TCourseSite course = channel.getTCourseSite();
		mav.addObject("course", course);
		mav.setViewName("cms/courseSite/teacheringIntroduction.jsp");
		return mav;
	}

	/**************************************************************************************
	 * @功能 查看新闻列表
	 * @作者：李小龙
	 * @日期：2015-8-13
	 *************************************************************************************/
	@RequestMapping("/cms/articalList")
	public ModelAndView articalList(@RequestParam Integer id) {
		ModelAndView mav = new ModelAndView();
		// id对应的栏目
		TCourseSiteChannel channel = tCourseSiteChannelService.findTCourseSiteChannelByPrimaryKey(id);
		mav.addObject("channel", channel);
		// 课程站点
		TCourseSite course = channel.getTCourseSite();
		mav.addObject("course", course);
		mav.setViewName("cms/courseSite/articalList.jsp");
		return mav;
	}

	/**************************************************************************************
	 * @功能 查看新闻
	 * @作者：李小龙
	 * @日期：2015-8-13
	 *************************************************************************************/
	@RequestMapping("/cms/artical")
	public ModelAndView artical(@RequestParam Integer id) {
		ModelAndView mav = new ModelAndView();
		// id对应的文章
		TCourseSiteArtical artical = tCourseSiteArticalService.findTCourseSiteArticalByPrimaryKey(id);
		mav.addObject("artical", artical);
		// 文章所属栏目
		TCourseSiteChannel channel = artical.getTCourseSiteChannel();
		mav.addObject("channel", channel);
		// 课程站点
		TCourseSite course = channel.getTCourseSite();
		mav.addObject("course", course);
		mav.setViewName("cms/courseSite/artical.jsp");
		return mav;
	}

	/**************************************************************************************
	 * @功能 查看首页
	 * @作者：李小龙
	 * @日期：2015-8-13
	 *************************************************************************************/
	@RequestMapping("/cms/index")
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView();
		User user = shareService.getUser();
		if (user==null) {
			// 查询出公开站点
			int currpage = 1;
			int pageSize = 8;
			List<TCourseSite> sites = tCourseSiteService.findOpenTCourseSite(1,currpage,pageSize);
			mav.addObject("sites", sites);
		}
		int currpage = 1;
		int pageSize = 5;
		List<LabReservation> labReservations= labReservationService.findLabreservationList(currpage,pageSize);
		List<Labreservationlist> list = new ArrayList<Labreservationlist>();
		for (LabReservation lab : labReservations) {
			Labreservationlist la = new Labreservationlist();
			la.setId(lab.getId());
			if (lab.getCDictionaryByLabReservetYpe() != null) {
				la.setNametype(lab.getCDictionaryByLabReservetYpe().getCName());
			}
			if (lab.getEventName() != null) {
				la.setName(lab.getEventName());
			} else {
				la.setName(lab.getEnvironmentalRequirements());
			}
			Set<String> week = new HashSet<String>();
			Set<String> day = new HashSet<String>();
			Set<String> time = new HashSet<String>();
			if (lab.getTimetableAppointment().getTimetableAppointmentSameNumbers().size() > 0)
				for (TimetableAppointmentSameNumber timetableAppointmentSameNumber : lab.getTimetableAppointment()
						.getTimetableAppointmentSameNumbers()) {
					if (timetableAppointmentSameNumber.getStartWeek().intValue() != timetableAppointmentSameNumber.getEndWeek().intValue()) {
						week.add(timetableAppointmentSameNumber.getStartWeek().toString() + "-"
								+ timetableAppointmentSameNumber.getEndWeek().toString());
					} else {
						week.add(timetableAppointmentSameNumber.getStartWeek().toString());
						
					}
					day.add(lab.getTimetableAppointment().getWeekday().toString());
					if (timetableAppointmentSameNumber.getStartClass().intValue() != timetableAppointmentSameNumber.getEndClass().intValue()) {
						time.add(timetableAppointmentSameNumber.getStartClass().toString() + "-"
								+ timetableAppointmentSameNumber.getEndClass().toString());
					} else {
						time.add(timetableAppointmentSameNumber.getStartClass().toString());
					}
				}
			else {
				if (lab.getTimetableAppointment().getStartWeek().intValue() != lab.getTimetableAppointment().getEndWeek().intValue()) {
					week.add(lab.getTimetableAppointment().getStartWeek().toString() + "-"
							+ lab.getTimetableAppointment().getEndWeek().toString());
				}else{
					week.add(lab.getTimetableAppointment().getStartWeek().toString());
				}
				
				day.add(lab.getTimetableAppointment().getWeekday().toString());
				if (lab.getTimetableAppointment().getStartClass().intValue() != lab.getTimetableAppointment().getEndClass().intValue()) {
					time.add(lab.getTimetableAppointment().getStartClass().toString() + "-"
							+ lab.getTimetableAppointment().getEndClass().toString());
				}else{
					time.add(lab.getTimetableAppointment().getStartClass().toString());
					
				}
				
				
			}
			int dd = week.size();
			String[] weeks = week.toArray(new String[dd]);
			String[] days = day.toArray(new String[day.size()]);
			String[] timea = time.toArray(new String[time.size()]);
			;
			// 数组排序
			String[] weekt = insertSort(weeks);
			String[] timet = insertSort(timea);
			
			la.setWeek(weekt);
			la.setTime(timet);
			la.setDay(days);
			//设置实验室
			if (lab.getLabRoom() != null) {
				la.setLab(lab.getLabRoom().getLabRoomName());
				la.setLabRoom(lab.getLabRoom());
			}
			//设置申请者
			if (lab.getUser() != null) {
				la.setUser(lab.getUser());
			}
			la.setCont(lab.getAuditResults());
			la.setStart(lab.getReservations());
			la.setFabu(lab.getItemReleasese());
			list.add(la);
			
		}
		mav.addObject("labReservations", list);
		List<LabRoomDeviceReservation> labRoomDeviceReservations = labRoomDeviceService.findAllLabRoomDeviceReservation(new LabRoomDeviceReservation(), null, null, currpage, pageSize, 1);
		mav.addObject("labRoomDeviceReservations", labRoomDeviceReservations);
		mav.setViewName("cms/index.jsp");
		return mav;
	}

	/**************************************************************************************
	 * @功能 查看课程列表
	 * @作者：魏诚
	 * @日期：2015-8-30
	 *************************************************************************************/
	@RequestMapping("/cms/courseList")
	public ModelAndView courseList() {
		ModelAndView mav = new ModelAndView();
		// 获取登录用户
		User user = shareService.getUser();
		mav.addObject("user", user);
		// 查询所有站点
		List<TCourseSite> allsites = tCourseSiteService.findAllTCourseSiteByUser(user);
		mav.addObject("sites", allsites);
		
        //课程列表还是我的课程
		mav.addObject("type", "课程列表");
		mav.setViewName("cms/courseSite/courseList.jsp");
		return mav;
	}
	
	/**************************************************************************************
	 * @功能 修改密码
	 * @作者：黄崔俊
	 * @日期：2015-9-15 14:33:20
	 *************************************************************************************/
	@RequestMapping("/cms/changePassword")
	public ModelAndView changePassword(@RequestParam String password,String type) {
		ModelAndView mav = new ModelAndView();
		// 获取登录用户
		User user = shareService.getUser();
		if (password!=null&&!"".equals(password)) {//传入的密码不为空则修改密码
			user = shareService.changeUserPassword(user,password);
		}
		mav.addObject("user", user);
        //课程列表还是我的课程
		if ("课程列表".equals(type)) {
			if (password==null||"".equals(password)) {
				mav.setViewName("cms/courseSite/courseList.jsp");
			}else {
				mav.setViewName("redirect:/cms/courseList");
			}
		}
		if ("我的课程".equals(type)) {
			if (password==null||"".equals(password)) {
				mav.setViewName("cms/courseSite/courseList.jsp");
			}else {
				mav.setViewName("redirect:/cms/myCourseList");
			}
		}
		mav.addObject("type", type);
		
		return mav;
	}
	
	/**************************************************************************************
	 * @功能 查看个人信息
	 * @作者：黄崔俊
	 * @日期：2015-9-16 10:22:22
	 *************************************************************************************/
	@RequestMapping("/cms/listMyInfo")
	public ModelAndView listMyInfo(@RequestParam String type) {
		ModelAndView mav = new ModelAndView();

		// 获取当前用户
		User user = shareService.getUser();
		Set<Authority> as = user.getAuthorities();
		String str = "";
		if(as.size()==0){
			str = "暂无身份";
		}
		if(as.size()>0){
			for(Authority a : as){
				str = a.getCname()+" ";
			}
		}
		str = str.substring(0, str.length()-1);
		mav.addObject("str", str);
		mav.addObject("user", user);
		
		//课程列表还是我的课程
		mav.addObject("type", type);
		mav.setViewName("cms/courseSite/courseList.jsp");
		return mav;
	}
	
	/**************************************************************************************
	 * @功能：保存个人信息
	 * @作者：黄崔俊
	 * @日期：2015-9-16 10:22:22
	 *************************************************************************************/
	@RequestMapping("/cms/saveMyInfo")
	public ModelAndView saveMyInfo(@ModelAttribute User user,@RequestParam String type) {
		ModelAndView mav = new ModelAndView();
		// 获取当前用户
		User user1 = shareService.getUser();

		// 直接设置用户的email telephone信息
		user1.setEmail(user.getEmail());
		user1.setTelephone(user.getTelephone());

		// 保存信息
		shareService.saveUser(user1);
		mav.addObject("type", type);
		mav.setViewName("redirect:/cms/listMyInfo");
		return mav;
	}
	
	/**************************************************************************************
	 * @功能 查看我的选课课程列表
	 * @作者：魏诚
	 * @日期：2015-8-30
	 *************************************************************************************/
	@RequestMapping("/cms/myCourseList")
	public ModelAndView myCourseList() {
		ModelAndView mav = new ModelAndView();
		// 查询出登录用户公开站点
		List<TCourseSite> sites = tCourseSiteService.findMyCourseList(shareService.getUser());
		 //课程列表还是我的课程
		mav.addObject("type", "我的课程");
		mav.addObject("sites", sites);
		// 获取登录用户
		mav.addObject("user", shareService.getUser());

		mav.setViewName("cms/courseSite/courseList.jsp");
		return mav;
	}
	/****************************************************************************
	 * 功能：获取学生的单个作业分数和评语
	 * 作者：裴继超
	 * 时间：2015-9-24
	 ****************************************************************************/
	@RequestMapping("/teaching/assignment/grading")                                                     
	public @ResponseBody String[] findGranding(@RequestParam Integer id) throws Exception {
		//
		TAssignmentGrading Granding=tAssignmentGradingService.findGrandingByAssignmentId(id);
		if(Granding==null){
			String score="未评分";
			String comments="无评语";
			String content="未提交";
			String[] ss = new String[]{score,comments,content};
			return ss;
		}else{
		String score=Granding.getUserByGradeBy()==null?"未评分":Granding.getFinalScore().toString();
		String comments=Granding.getUserByGradeBy()==null?"无评语":Granding.getComments();
		String content=Granding.getContent();
		String[] ss = new String[]{score,comments,content};
		return ss;
		}
		
		
	}
	/****************************************************************************
	 * 功能：获取未提交作业学生名单
	 * 作者：裴继超
	 * 时间：2015-9-24
	 ****************************************************************************/
	@RequestMapping("/teaching/assignment/notSubmitStudents")                                                     
	public @ResponseBody String findNotSubmitStudents(@RequestParam Integer id) throws Exception {
		//
		
		String s = "";
		List<TCourseSiteUser> notSubmitStudents=tAssignmentService.findNotSubmitAssignmentStudents(id);
		for (TCourseSiteUser user : notSubmitStudents) {
			s += "<tr>" +
					"<td>"+user.getUser().getSchoolClasses().getClassName()+"</td>" +
					"<td>"+user.getUser().getUsername()+"</td>" +
					"<td>"+user.getUser().getCname()+"</td>" +
							"</tr>";
		}
		
		String ss = "<thead><tr>" +
				"<th>班级</th>" +
				"<th>学号</th>" +
				"<th>姓名</th>" +
				"</tr></thead>" +
		        "<tbody>" +
				s +
				"</tbody>";
		return shareService.htmlEncode(ss);
		
		
	}
	
	/**************************************************************************************
	 * @功能 门户首页
	 * @作者：黄崔俊
	 * @日期：2016-4-18 09:29:59
	 *************************************************************************************/
	@RequestMapping("/cms/portal")
	public ModelAndView portal() {
		ModelAndView mav = new ModelAndView();

		List<CmsChannel> cmsChannels = cmsSystemService.findTopCmsChannels();
		mav.addObject("cmsChannels", cmsChannels);
		mav.setViewName("portal/index.jsp");
		return mav;
	}
	
	/**************************************************************************************
	 * @功能：查看平级栏目
	 * @作者：黄崔俊
	 * @日期：2016-4-29 11:13:07
	 *************************************************************************************/
	@RequestMapping("/cms/channel/findBrotherCmsChannelList")
	public ModelAndView findBrotherCmsChannelList(@RequestParam Integer id) {
		ModelAndView mav = new ModelAndView();
		//查询当前栏目
		CmsChannel cmsChannel = cmsService.findCmsChannelByPrimaryKey(id);
		mav.addObject("cmsChannel", cmsChannel);
		List<CmsChannel> cmsChannels = cmsSystemService.findBrotherCmsChannelList(cmsChannel);
		mav.addObject("cmsChannels", cmsChannels);
		mav.setViewName("portal/channel/secondChannelList.jsp");
		return mav;
	}
	
	public static String[] insertSort(String[] weeks) {// 插入排序算法
		for (int i = 1; i < weeks.length; i++) {
			for (int j = i; j > 0; j--) {
                String start =weeks[j];
                String end =weeks[j-1];
                if(start.indexOf("-")!=-1){
                	start = start.substring(start.indexOf("-"));
                }
                if(end.indexOf("-")!=-1){
                	end = end.substring(end.indexOf("-"));
                }
                
				if (Integer.parseInt(start) < Integer.parseInt(end)) {
					String temp = weeks[j - 1];
					weeks[j - 1] = weeks[j];
					weeks[j] = temp;
				} else
					break;
			}
		}
		return weeks;
	}
	
	
	/**************************************************************************************
	 * @功能 门户首页
	 * @作者：黄崔俊
	 * @日期：2016-4-18 09:29:59
	 *************************************************************************************/
	@RequestMapping("/cms/tcoursesite/listTCourseSite")
	public ModelAndView listTCourseSite(@RequestParam Integer currpage) {
		ModelAndView mav = new ModelAndView();
		int totalRecords = tCourseSiteService.countOpenTCourseSite(1);
		int pageSize = 12;
		List<TCourseSite> sites = tCourseSiteService.findOpenTCourseSite(1,currpage,pageSize);
		mav.addObject("sites", sites);
		mav.addObject("pageModel", shareService.getPage(currpage, pageSize, totalRecords));
		
		mav.setViewName("/cms/tcoursesite/listTCourseSite.jsp");
		return mav;
	}
	
	
	
	
	
	/**************************************************************************************
	 * @功能：二级页面
	 * @作者：储俊
	 * @日期：2016-9-18 
	 *************************************************************************************/
	@RequestMapping("/cms/cindex")
	public ModelAndView cindex() {
		ModelAndView mav = new ModelAndView();
		Set<CmsChannel> channels=cmschannelService.loadChannels();
		mav.addObject("channels", channels);
		int id=26;
		List<CmsLink> link =cmschannelService.findCmstagId(id);//查找所有的链接信息	
		mav.addObject("links", link);
		Set<CmsArticle> artcles=cmsarticleService.loadArticles();
		mav.addObject("artcles", artcles);
		mav.addObject("loginUser",shareService.getUser());
		List<CmsResource> cmsResources=cmsService.findCmsResourceByVideo();
		CmsResource CmsResource=null;
		if(cmsResources.size()!=0){
			CmsResource=cmsResources.get(0);
		}
		List<CmsLink> cmsLinks=cmsService.findCmsTag(28);
		mav.addObject("cmsLinks", cmsLinks);
		mav.addObject("CmsResource", CmsResource);
		Calendar duedate = Calendar.getInstance();
		//设置时间格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日    EEE");
		//格式转换
		String nowDate =sdf.format(duedate.getTime());
		mav.addObject("nowDate",nowDate);
		mav.setViewName("cms/cindex.jsp");
		return mav;
	}
	

	
	
	/**************************************************************************************
	 * @功能：一级页面
	 * @作者：储俊
	 * @日期：2016-9-18 
	 *************************************************************************************/
	@RequestMapping("/cms/winindex")
	public ModelAndView winindex() {
		ModelAndView mav = new ModelAndView();
		Set<CmsChannel> channels=cmschannelService.loadChannels();
		mav.addObject("channels", channels);
		//一级页面滚动tagid
		List<CmsChannel> topchannels=cmsService.getAlltopChannels();
		mav.addObject("topchannels", topchannels);
		int id=27;
		List<CmsLink> link =cmschannelService.findCmstagId(id);//查找所有的链接信息	
		mav.addObject("links", link);
		mav.setViewName("cms/winindex.jsp");
		return mav;
	}
	
	/**************************************************************************************
	 * @功能：文章
	 * @作者：储俊
	 * @日期：2016-9-18 
	 *************************************************************************************/
	@RequestMapping(value ="/cms/article/{articleId}")
	public ModelAndView artilce(@PathVariable Integer articleId) {
		ModelAndView mav = new ModelAndView();
		Set<CmsChannel> channels=cmschannelService.loadChannels();
		mav.addObject("channels", channels);
		//一级页面滚动tagid
		int id=27; 
	    int articleIdi= articleId.intValue();  
		List<CmsLink> link =cmschannelService.findCmstagId(id);//查找所有的链接信息	
		mav.addObject("links", link);
		CmsArticle article = cmsService.findCmsArticleByPrimaryKey(articleIdi);
		CmsChannel channel = article.getCmsChannel();
		CmsChannel topchannel= cmsService.gettopchannel(channel);
		int topid =topchannel.getId();
		List<CmsChannel> childchannel=cmsService.getchildchannel(topid);
		List<CmsArticle> hotarticles=cmsService.getArticleByreadNum();
		mav.addObject("hotarticles", hotarticles);
		mav.addObject("article", article);
		mav.addObject("topchannel", topchannel);
		mav.addObject("childchannel", childchannel);
		Calendar duedate = Calendar.getInstance();
		//设置时间格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日    EEE");
		//格式转换
		String nowDate =sdf.format(duedate.getTime());
		mav.addObject("nowDate",nowDate);
		mav.setViewName("cms/article/article.jsp");
		return mav;
	}
	/**************************************************************************************
	 * @功能：栏目
	 * @作者：储俊
	 * @日期：2016-10-11 
	 *************************************************************************************/
	@RequestMapping(value ="/cms/channel/{channelId}")
	public ModelAndView channel(@PathVariable Integer channelId,@RequestParam Integer currpage) {
		ModelAndView mav = new ModelAndView();
        //获取所有channel
		Set<CmsChannel> channels=cmschannelService.loadChannels();
		mav.addObject("channels", channels);
		CmsChannel channel = cmsService.findCmsChannelByPrimaryKey(channelId);
		//获取当前channel
		mav.addObject("channel", channel);
		//获取当前channel的topchannel
		CmsChannel topchannel= cmsService.gettopchannel(channel);
		int topid =topchannel.getId();
        //获取topchannel的子栏目
		List<CmsChannel> childchannel=cmsService.getchildchannel(topid);
		mav.addObject("topchannel", topchannel);
		mav.addObject("childchannel", childchannel);
		//获取当前channel的文章
		List<CmsArticle> articles=cmsService.getCmsArticleByChannelId(channelId);
		mav.addObject("articles", articles);
		//获取点击量最高的三篇文章作为今日热点
		List<CmsArticle> hotarticles=cmsService.getArticleByreadNum();
		mav.addObject("hotarticles", hotarticles);
		int articleSize=articles.size();
		mav.addObject("totalRecords", articleSize);
		int pagesize=10;
		PageModel pageModel = new PageModel(currpage, articleSize, pagesize);
		mav.addObject("pageModel", pageModel);
		List<CmsArticle> currarticles = cmsService.getassetpagelist(channelId,currpage, pagesize);
		mav.addObject("currarticles", currarticles);
		Calendar duedate = Calendar.getInstance();
		//设置时间格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日    EEE");
		//格式转换
		String nowDate =sdf.format(duedate.getTime());
		mav.addObject("nowDate",nowDate);
		//若文章为1篇   跳转文章页面
		if(articleSize==1){
			CmsArticle article=articles.get(0);
			int articleId=article.getId();
			mav.setViewName("redirect:/cms/article/"+articleId);
		}//若文章为空，子栏目不为空  跳转第一个子栏目页面
		else if(articleSize==0 & childchannel.size()!=0 & channel.getCmsChannel()==null){
			int firstchildId=childchannel.get(0).getId();
			mav.setViewName("redirect:/cms/channel/"+firstchildId+"?currpage=1");
			}
		else{
			mav.setViewName("cms/channel/channel.jsp");
		}
		return mav;
	}
	@RequestMapping(value ="/cms/search")
	public ModelAndView search( HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		String searchcontent=request.getParameter("j_username");
		List<CmsArticle> searcharticle=cmsService.getArticleBysearchcontent(searchcontent);
		if(searcharticle.size()!=0){
			mav.setViewName("redirect:/cms/article/"+searcharticle.get(0).getId());
		}
		else{
			mav.setViewName("redirect:/cms/cindex");
			}
		return mav;
		
	}
	/****************************************************************************
	 * 功能：手机端设备信息页面
	 * 作者：李小龙
	 ****************************************************************************/
	@RequestMapping("/cmsshow/showDevice")
	public ModelAndView showDevice(@RequestParam Integer id) {
		ModelAndView mav=new ModelAndView();
		//id对应的实验室设备
		LabRoomDevice device=labRoomDeviceService.findLabRoomDeviceByPrimaryKey(id);
		mav.addObject("device", device);	
		mav.setViewName("cmsshow/showDevice.jsp");
		return mav;
	}
    
	/****************************************************************************
	 * 功能：手机端设备详情页面
	 * 作者：李小龙
	 ****************************************************************************/
	@RequestMapping("/cmsshow/showDeviceInfo")
	public ModelAndView showDeviceInfo(@RequestParam Integer id) {
		ModelAndView mav=new ModelAndView();
		//id对应的实验室设备
		LabRoomDevice device=labRoomDeviceService.findLabRoomDeviceByPrimaryKey(id);
		mav.addObject("device", device);	
		System.out.println(device);
		mav.setViewName("cmsshow/showDeviceInfo.jsp");
		return mav;
	}
	
}