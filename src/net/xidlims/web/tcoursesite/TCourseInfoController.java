/****************************************************************************
 * 命名规范：
 * 本控制层调整必须是按照如下的路径调整：@RequestMapping("/tCourseSite/xxxx")。
 * 列表信息listxxxx，獲取信息：getxxxx 编辑信息：editxxxx 删除信息：detelexxxx 新增信息 newxxxx
 * 导出信息exportxxxx 保存信息：savexxxx 
 ****************************************************************************/

package net.xidlims.web.tcoursesite;

import java.math.BigDecimal;
import java.net.BindException;
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
import net.xidlims.domain.TAssignment;
import net.xidlims.domain.TAssignmentAnswerAssign;
import net.xidlims.domain.TAssignmentControl;
import net.xidlims.domain.TCourseSite;
import net.xidlims.domain.TCourseSiteChannel;
import net.xidlims.domain.TCourseSiteArtical;
import net.xidlims.domain.TCourseSiteSchedule;
import net.xidlims.domain.TCourseSiteTag;
import net.xidlims.domain.TCourseSiteUser;
import net.xidlims.domain.User;
import net.xidlims.domain.WkChapter;
import net.xidlims.domain.WkFolder;
import net.xidlims.domain.WkLesson;
import net.xidlims.domain.WkUpload;
import net.xidlims.service.common.ShareService;
import net.xidlims.service.lab.LabCenterService;
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
import net.xidlims.service.tcoursesite.TCourseSiteScheduleService;
import net.xidlims.view.ViewTAssignment;
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
@Controller("TCourseInfoController")
public class TCourseInfoController<JsonResult> {
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
	private TCourseSiteScheduleService tCourseSiteScheduleService;
	@Autowired
	private TCourseSiteUserService tCourseSiteUserService;
	
	/**************************************************************************
	 * Description:课程站点-查看课程信息
	 *  
	 * @author：李军凯
	 * @date ：2016-10-14
	 **************************************************************************/
	@SystemServiceLog("查看课程信息")
	@ResponseBody
	@RequestMapping("/tcoursesite/courseInfo/courseInfo")
	public ModelAndView courseInfo(@RequestParam Integer tCourseSiteId,Integer curWeek,HttpSession httpSession) {
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
			flag = 1;//实验中心主任   或者实验室管理员   或者超级管理员   或者教务处   或者校领导   或者教学副院长 或者教务管理员   或者实验中心管理员   为教师权限
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
		//课程下的所有栏目
		List<TCourseSiteChannel> tCourseSiteChannels = tCourseSiteChannelService.findTCourseSiteChannelsBySiteId(tCourseSiteId);
		mav.addObject("tCourseSiteChannels",tCourseSiteChannels);
		mav.addObject("tCourseSiteArtical", new TCourseSiteArtical());
		
		//课表map
		Map<Integer, String> map = tCourseSiteScheduleService.findSchedulesMap(tCourseSiteId);
		mav.addObject("map", map);
		//mav.addObject("schedule", new TCourseSiteSchedule());
		List<TCourseSiteTag> days = tCourseSiteScheduleService.findTCourseSiteTagsByDescription("day");
		//List<TCourseSiteTag> sessions = tCourseSiteScheduleService.findTCourseSiteTagsByDescription("session");
		List<TCourseSiteTag> sessions = tCourseSiteScheduleService.findTCourseSiteTagsByDescription("session");
		List<TCourseSiteTag> weeks = tCourseSiteScheduleService.findTCourseSiteTagsByDescription("week");
		mav.addObject("days", days);
		mav.addObject("sessions", sessions);
		mav.addObject("weeks", weeks);
		//初始化默认时间
		Calendar duedate = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String nowDate =sdf.format(duedate.getTime());
		mav.addObject("now",nowDate);
		//上课周
		mav.addObject("curWeek", curWeek);
		//查找课表
		List<TCourseSiteSchedule> tCourseSiteSchedule = tCourseSiteScheduleService.findTCourseSiteSchedulesBySiteIdAndWeek(tCourseSiteId, curWeek);
		mav.addObject("tCourseSiteSchedule", tCourseSiteSchedule);
		//查找当前周的文件
		List<Object[]> curFolders = tCourseSiteScheduleService.findCurFolder(curWeek, tCourseSiteId);
		mav.addObject("curFolders", curFolders);
		//查找当前周的测试
		List<Object[]> curExams = tCourseSiteScheduleService.findCurExams(curWeek, tCourseSiteId);
		mav.addObject("curExams", curExams);
		/*List<ViewTAssignment> chapterViewExams = tCourseSiteScheduleService.findViewExamList(curExams, tCourseSiteId);
		mav.addObject("chapterViewExams", chapterViewExams);*/
		//查找当前周的考试
		List<Object[]> curTests = tCourseSiteScheduleService.findCurTests(curWeek, tCourseSiteId);
		mav.addObject("curTests", curTests);
		/*List<ViewTAssignment> chapterViewTests = tCourseSiteScheduleService.findViewExamList(curTests, tCourseSiteId);
		mav.addObject("chapterViewTests", chapterViewTests);*/
		//查找有课程的节次
		List<TCourseSiteTag> curSessions= new ArrayList<TCourseSiteTag>();
		for(TCourseSiteTag s:sessions){
			int flagSe = 0;
			for(TCourseSiteSchedule t:tCourseSiteSchedule){
				if(t.getSession().equals(Integer.parseInt(s.getSiteTag()))){
					flagSe =1;
				}			
			}
			if(flagSe==1){
				curSessions.add(s);
			}
		}
		
		mav.setViewName("tcoursesite/courseInfo/courseInfo.jsp");
		mav.addObject("curSessions", curSessions);
		return mav;
	}
	
	/**************************************************************************
	 * Description:课程信息保存文章
	 *  
	 * @author：裴继超
	 * @date ：2016-06-16
	 **************************************************************************/
	@SystemServiceLog("保存文章")
	@ResponseBody
	@RequestMapping("/tcoursesite/courseInfo/saveArtical")
	public ModelAndView saveArtical(@ModelAttribute TCourseSiteArtical tCourseSiteArtical,@RequestParam Integer tCourseSiteId,Integer curWeek, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		//当前课程站点
		TCourseSite tCourseSite = tCourseSiteService.findCourseSiteById(tCourseSiteId);
		// 当前登录人
		User user = shareService.getUser();
		if (tCourseSiteArtical.getId() == -1){
			TCourseSiteChannel tCourseSiteChannel = new TCourseSiteChannel();
			tCourseSiteChannel.setTCourseSite(tCourseSite);
			tCourseSiteChannel.setChannelName(tCourseSiteArtical.getName());
			tCourseSiteChannel.setCreateUser(user);
			tCourseSiteChannel.setCreateDate(Calendar.getInstance());
			tCourseSiteChannel = tCourseSiteChannelService.saveChannel(tCourseSiteChannel);
			
			tCourseSiteArtical.setTCourseSiteChannel(tCourseSiteChannel);
			tCourseSiteArtical.setCreateUser(user);
			tCourseSiteArtical.setCreateDate(Calendar.getInstance());
			tCourseSiteArtical = tCourseSiteArticalService.saveArtical(tCourseSiteArtical);
		}else{
			TCourseSiteArtical newTCourseSiteArtical = tCourseSiteArticalService.findTCourseSiteArticalByPrimaryKey(tCourseSiteArtical.getId());
			newTCourseSiteArtical.setName(tCourseSiteArtical.getName());
			newTCourseSiteArtical.setContent(tCourseSiteArtical.getContent());
			newTCourseSiteArtical = tCourseSiteArticalService.saveArtical(newTCourseSiteArtical);
			
			TCourseSiteChannel tCourseSiteChannel = newTCourseSiteArtical.getTCourseSiteChannel();
			tCourseSiteChannel.setChannelName(newTCourseSiteArtical.getName());
			tCourseSiteChannel = tCourseSiteChannelService.saveChannel(tCourseSiteChannel);
			
		}
		
		mav.setViewName( "redirect:/tcoursesite/courseInfo/courseInfo?tCourseSiteId=" + tCourseSiteId+"&curWeek="+curWeek);
		return mav;
	}
	
	/**************************************************************************
	 * Description:编辑课程信息文章
	 *  
	 * @author：裴继超
	 * @date ：2016-06-21
	 **************************************************************************/
	@SystemServiceLog("编辑文章")
	@RequestMapping("/tcoursesite/courseInfo/editArtical")
	@ResponseBody
	public Map<String, Object> editArtical(@RequestParam Integer articalId) {
		WkChapter chapter = wkChapterService.findChapterByPrimaryKey(articalId);
		Map<String, Object> map = new HashMap<String, Object>();
		TCourseSiteArtical artical = tCourseSiteArticalService.findTCourseSiteArticalByPrimaryKey(articalId); // 章节title
		map.put("name", (artical.getName()==null)?"":artical.getName());// 章节id
		map.put("content", (artical.getContent()==null)?"":artical.getContent());
		return map;
	}
	
	/**************************************************************************
	 * Description:课程信息删除文章
	 *  
	 * @author：裴继超
	 * @date ：2016-06-16
	 **************************************************************************/
	@SystemServiceLog("删除文章")
	@ResponseBody
	@RequestMapping("/tcoursesite/courseInfo/deleteArtical")
	public ModelAndView deleteArtical(@RequestParam Integer tCourseSiteId,Integer articalId,Integer curWeek, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		TCourseSiteArtical tCourseSiteArtical = tCourseSiteArticalService.findTCourseSiteArticalByPrimaryKey(articalId);
		TCourseSiteChannel tCourseSiteChannel = tCourseSiteArtical.getTCourseSiteChannel();
		tCourseSiteArticalService.deleteTCourseSiteArtical(tCourseSiteArtical);
		tCourseSiteChannelService.deleteTCourseSiteChannel(tCourseSiteChannel);
		
		mav.setViewName( "redirect:/tcoursesite/courseInfo/courseInfo?tCourseSiteId=" + tCourseSiteId+"&curWeek="+curWeek);
		return mav;
	}
	
	/**************************************************************************
	 * Description:给实验分室上传图片
	 *  
	 * @author：李小龙
	 * @date ：2016-07-29
	 **************************************************************************/
	@RequestMapping("/tcoursesite/uploadImageForSite")
	public @ResponseBody String uploadImageForLabRoom(HttpServletRequest request, 
			HttpServletResponse response, BindException errors,Integer siteId,
			Integer type) throws Exception {
		String sep = System.getProperty("file.separator");
		//String path = sep + "upload" + sep + "site" + sep + siteId;
		String path = sep + "upload" + sep + "tcoursesite" + sep + "site" + siteId + sep + "courseInfo";
		tCourseSiteService.uploadImageForSite(request, path,type,siteId);
		return "ok";
	}
	
	/**************************************************************************
	 * Description:课程信息-保存课表
	 *  
	 * @author： 李军凯
	 * @date ：2016-10-13
	 **************************************************************************/
	@SystemServiceLog("保存课表")
	@ResponseBody
	@RequestMapping("/tcoursesite/courseInfo/saveSchedule")
	public ModelAndView saveSchedule(@RequestParam Integer tCourseSiteId,String place,Integer curWeek, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		// 选择的课程中心
		TCourseSite tCourseSite = tCourseSiteService.findCourseSiteById(tCourseSiteId);
		// 当前登录人
		User user = shareService.getUser();
		//初始化默认时间
		Calendar duedate = Calendar.getInstance();
		//获取星期
		String[] days = request.getParameterValues("sDay");
		//获取介词
		String[] sessions = request.getParameterValues("sSession");
		//获取周次
		String[] weeks = request.getParameterValues("sWeek"); 
		//String[] place = request.getParameterValues("place");
		for(String week:weeks){
			for(String day:days){
				for(String session:sessions){
					TCourseSiteSchedule t = new TCourseSiteSchedule();
					t.setCreateTime(duedate);
					t.setTCourseSite(tCourseSite);
					t.setUser(user);
					t.setWeek(Integer.parseInt(week));
					t.setDay(Integer.parseInt(day));
					t.setSession(Integer.parseInt(session));
					t.setPlace(place);
					tCourseSiteScheduleService.saveSchedule(t);
				}
			}
		}
		mav.setViewName( "redirect:/tcoursesite/courseInfo/courseInfo?tCourseSiteId=" + tCourseSiteId+"&curWeek="+curWeek);
		return mav;
	}
	
	

}