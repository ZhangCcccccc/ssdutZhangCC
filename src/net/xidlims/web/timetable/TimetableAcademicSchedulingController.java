package net.xidlims.web.timetable;

import java.io.IOException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.xidlims.constant.CommonConstantInterface;
import net.xidlims.service.common.CStaticValueService;
import net.xidlims.service.common.ShareService;
import net.xidlims.service.timetable.OuterApplicationService;
import net.xidlims.service.timetable.SchoolCourseDetailService;
import net.xidlims.service.timetable.SchoolCourseInfoService;
import net.xidlims.service.timetable.SchoolCourseService;
import net.xidlims.service.timetable.TimetableAppointmentService;
import net.xidlims.dao.SchoolCourseDAO;
import net.xidlims.dao.SchoolTermDAO;
import net.xidlims.dao.TimetableAppointmentDAO;
import net.xidlims.domain.CStaticValue;
import net.xidlims.domain.SchoolCourseDetail;
import net.xidlims.domain.SchoolTerm;
import net.xidlims.domain.TimetableAppointment;

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

/****************************************************************************
 * @功能：教务排课管理模块
 * @作者：魏诚 时间：2014-07-14
 ****************************************************************************/
@Controller("TimetableCourseSchedulingController")
@SessionAttributes("selected_labCenter")
public class TimetableAcademicSchedulingController<JsonResult> {

	/************************************************************
	 * @初始化WebDataBinder，这个WebDataBinder用于填充被@InitBinder注释的处理 方法的command和form对象
	 * 
	 ************************************************************/
	@InitBinder
	public void initBinder(WebDataBinder binder, HttpServletRequest request) { // Register
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
	private OuterApplicationService outerApplicationService;
	@Autowired
	private SchoolCourseDetailService schoolCourseDetailService;
	@Autowired
	private TimetableAppointmentDAO timetableAppointmentDAO;
	@Autowired
	private TimetableAppointmentService timetableAppointmentService;
	@Autowired
	private SchoolCourseDAO schoolCourseDAO;
	@Autowired
	private SchoolCourseService schoolCourseService;
	@Autowired
	private SchoolCourseInfoService schoolCourseInfoService;
	@Autowired
	private ShareService shareService;
	@Autowired
	private SchoolTermDAO schoolTermDAO;
	@Autowired
	private CStaticValueService cStaticValueService;
	
	/************************************************************
	 * @throws IOException 
	 * @throws ParseException 
	 * @功能：教务排课-进行直接排课
	 * @作者：魏诚
	 * @日期：2014-07-25
	 ************************************************************/
	@RequestMapping("/timetable/doDirectTimetable")
	public ModelAndView doDirectTimetable(HttpServletRequest request,HttpServletResponse response, @RequestParam String courseCode) throws IOException, ParseException {
		ModelAndView mav = new ModelAndView();
		//进行实验设备判冲
		
		/*//判断是否实验设备冲突
		if(devices.length>0&&!timetableCommonService.getLabroomDeviceValid(devices, courseCode)){
			mav.setViewName("../../timetable/listTimetableTerm?currpage=" + request.getParameter("currpage") + "&id=-1");
			return mav;
		}*/
		String[] sLabRoom_id = request.getParameterValues("labRoom_id");
		String[] sLabRoomDevice_id = request.getParameterValues("labRoomDevice_id");
		String[] sTutorRelated = request.getParameterValues("tutorRelated");
		String[] sTeacherRelated = request.getParameterValues("teacherRelated");
		// 记录选课组编号
		mav.addObject("courseCode", courseCode);
		schoolCourseDetailService.doDirectTimetable(courseCode, sLabRoom_id, sTutorRelated, sTeacherRelated,sLabRoomDevice_id);
		//mav.setViewName("redirect:/timetable/listTimetableTerm?currpage=" + request.getParameter("currpage") + "&id=-1");
		mav.setViewName("redirect:/timetable/listTimetableAll?currpage=" + request.getParameter("currpage") + "&id=-1");
		return mav;
	}

	/************************************************************
	 * @教务排课-打开调整排课页面
	 * @参数：courseCode选课组编号，currpage调用的当前页数以便返回定位，flag提示是否处理调课完成0不提示，1提示
	 * @作者：魏诚
	 * @日期：2014-07-25
	 ************************************************************/
	@RequestMapping("/timetable/openAdjustTimetable")
	public ModelAndView openAdjustTimetable(@RequestParam String courseCode, int currpage, int flag, Integer tableAppId,
			@ModelAttribute("selected_labCenter") Integer cid) {
		//tableAppId==0表示新建 tableAppId!=0表示编辑，编辑的时候，可选周次的判断方法就不能与自身判冲
		if(cid==null){
			cid=-1;
		}
		ModelAndView mav = new ModelAndView();
		TimetableAppointment timetableAppointment = new TimetableAppointment();
		mav.addObject("timetableAppointment", timetableAppointment);
		// 获取去重的实验分室室列表
		mav.addObject("labRoomMap", outerApplicationService.getLabRoomMap(cid));
		// flag提示是否处理调课完成0不提示，1提示
		mav.addObject("flag", flag);
		// 获取可选的教师列表列表
		mav.addObject("timetableTearcherMap", outerApplicationService.getTimetableTearcherMap());
		// 获取登陆用户信息
		mav.addObject("user", shareService.getUserDetail());
		// 获取可选的实验项目列表列表
		mav.addObject(
				"timetableItemMap",
				outerApplicationService.getTimetableItemMap(schoolCourseDAO.findSchoolCourseByCourseCode(courseCode)
						.iterator().next().getSchoolCourseInfo().getCourseNumber()));
		// 获取可选的教师列表列表
		mav.addObject("courseCode", courseCode);
		// 根据选课组编号获取教务排课信息
		List<SchoolCourseDetail> schoolCourseDetailLists = schoolCourseDetailService
				.getSchoolCourseDetailByCourseCode(courseCode);
		mav.addObject("schoolCourseDetailMap", schoolCourseDetailLists);
		// 根据选课组获取课程排课列表
		mav.addObject("timetableAppointmentMap",
				timetableAppointmentDAO.findTimetableAppointmentByCourseCode(courseCode));
		// 获取可选的周列表列表
		mav.addObject("currpage", currpage);
		mav.addObject("validWeekMap", schoolCourseDetailService.getValidWeekMap(schoolCourseDetailLists.get(0)
				.getWeekday(), null, courseCode));
		mav.addObject("tableAppId", tableAppId);
		mav.setViewName("timetable/schedulingcourse/listAdjustTimetableTerm.jsp");
		return mav;
	}

	/************************************************************
	 * @教务排课-进行排课查看:查看排课信息期数据
	 * @页面跳转：listTimetableTerm-getTimetableWeeksMap
	 * @作者：魏诚
	 * @日期：2014-07-25
	 ************************************************************/
	@RequestMapping("/timetable/openSearchTimetable")
	public ModelAndView openSearchTimetable(@RequestParam String courseCode,
			@ModelAttribute("selected_labCenter") Integer cid) {
		ModelAndView mav = new ModelAndView();
		if(cid==null){
			cid=-1;
		}
		TimetableAppointment timetableAppointment = new TimetableAppointment();
		mav.addObject("timetableAppointment", timetableAppointment);
		// 获取去重的实验分室室列表
		mav.addObject("labRoomMap", outerApplicationService.getLabRoomMap(cid));
		// 获取可选的教师列表列表
		mav.addObject("timetableTearcherMap", outerApplicationService.getTimetableTearcherMap());
		// 根据选课组获取课程排课列表
		mav.addObject("timetableAppointmentMap",
				timetableAppointmentDAO.findTimetableAppointmentByCourseCode(courseCode));
		mav.setViewName("timetable/schedulingcourse/listCourseTimetable.jsp");
		return mav;
	}

	/************************************************************
	 * @throws ParseException 
	 * @教务排课-进行调整排课保存
	 * @页面跳转：listTimetableTerm-openAdjustTimetable-saveAdjustTimetable
	 * @作者：魏诚
	 * @日期：2014-07-25
	 ************************************************************/
	@RequestMapping("/timetable/saveAdjustTimetable")
	public ModelAndView saveAdjustTimetable(HttpServletRequest request) throws ParseException {
		ModelAndView mav = new ModelAndView();
		// 保存调整排课数据
		timetableAppointmentService.saveAdjustTimetable(request);
		// mav.setViewName("../../timetable/listTimetableTerm?currpage="+request.getParameter("currpage")
		// + "&id=-1");
		mav.setViewName("redirect:/timetable/openAdjustTimetable?courseCode=" + request.getParameter("courseCode")
				+ "&currpage=" + request.getParameter("currpage") + "&flag=1&tableAppId=0");
		return mav;
	}

	/************************************************************
	 * @教务排课-确认调整排课是否完成--从排课页面进入
	 * @页面跳转：listTimetableTerm-openAdjustTimetable-doJudgeTimetableOk
	 * @作者：魏诚
	 * @日期：2014-07-25
	 ************************************************************/
	@RequestMapping("/timetable/doJudgeTimetableOk")
	public ModelAndView doJudgeTimetableOk(HttpServletRequest request, @RequestParam String courseCode) {
		ModelAndView mav = new ModelAndView();
		// 发布排课
		List<TimetableAppointment> timetableAppointments = timetableAppointmentDAO
				.executeQuery("select c from TimetableAppointment c where c.courseCode like '" + courseCode + "'");
		for (TimetableAppointment timetableAppointment : timetableAppointments) {
			timetableAppointment.setStatus(2);
			timetableAppointmentDAO.store(timetableAppointment);
		}
		mav.setViewName("redirect:/timetable/listTimetableTerm?currpage=" + request.getParameter("currpage") + "&id=-1");
		return mav;
	}
	
	/************************************************************
	 * @教务排课-确认调整排课是否完成--从排课管理页面进入
	 * @作者：贺子龙
	 * @日期：2016-06-05
	 ************************************************************/
	@RequestMapping("/timetable/doJudgeTimetableOkFromAdmin")
	public ModelAndView doJudgeTimetableOkFromAdmin(HttpServletRequest request, @RequestParam String courseCode) {
		ModelAndView mav = new ModelAndView();
		// 发布排课
		List<TimetableAppointment> timetableAppointments = timetableAppointmentDAO
				.executeQuery("select c from TimetableAppointment c where c.courseCode like '" + courseCode + "'");
		for (TimetableAppointment timetableAppointment : timetableAppointments) {
			timetableAppointment.setStatus(2);
			timetableAppointmentDAO.store(timetableAppointment);
		}
		mav.setViewName("redirect:/timetable/timetableAdminIframe?currpage=1&id=-1&status=-1");
		return mav;
	}

	/************************************************************
	 * @显示教务排课的主显示页面
	 * @作者：魏诚
	 * @日期：2014-07-14
	 ************************************************************/
	@RequestMapping("/timetable/listTimetableTerm")
	public ModelAndView listTimetableTerm(HttpServletRequest request,
			@ModelAttribute SchoolCourseDetail schoolCourseDetail, @RequestParam int currpage,
			@ModelAttribute("selected_labCenter") Integer cid) {
		ModelAndView mav = new ModelAndView();
		/*
		 * // 获取terms的第一个 SchoolTerm schoolTerm = new SchoolTerm(); // 获取所有的学期
		 * List<SchoolTerm> terms =
		 * schoolCourseDetailService.getSchoolTermsByNow(); //
		 * 如果学期获取为零则学期为空则返回显示页面 if (terms.size() >
		 * CommonConstantInterface.INT_CURRPAGE_ZERO) { schoolTerm =
		 * terms.get(CommonConstantInterface.INT_CURRPAGE_ZERO); } else {
		 * mav.setViewName("timetable/schedulingcourse/listTimetableTerm.jsp");
		 * return mav; }
		 */
		if(cid==null){
			cid=-1;
		}
        // 获取学期列表
		List<SchoolTerm> schoolTerms = outerApplicationService.getSchoolTermList();
		mav.addObject("schoolTerms", schoolTerms);
		/*mav.addObject("iLabCenter", cid);*/
		// 根据课程及id获取选课组列表
		//int term = shareService.findNewTerm().getId();取当前所处学期或下个学期
		int term = shareService.getBelongsSchoolTerm(Calendar.getInstance()).getId();
		if (request.getParameter("term") != null) {
			term = Integer.parseInt(request.getParameter("term"));
		}
		mav.addObject("term", term);
		SchoolTerm schoolTerm = schoolTermDAO.findSchoolTermById(term);
		// 设置分页变量并赋值为20；
		int pageSize = CommonConstantInterface.INT_PAGESIZE;
		// 判断是否标记位为空，如果为空，则清空schoolCourseDetail
		if (request.getParameter("id") != null && request.getParameter("id").equals("-1")) {
			schoolCourseDetail.setCourseDetailNo(null);
		}
		// 根据课程及id获取课程排课列表的计数
		int totalRecords = schoolCourseDetailService.getCountCourseDetailsByQuery(term,
				schoolCourseDetail, cid);
		mav.addObject("totalRecords", totalRecords);
		// 获取登陆用户信息
		mav.addObject("user", shareService.getUserDetail());
		Map<String, Integer> pageModel = shareService.getPage(currpage, pageSize, totalRecords);
		mav.addObject("pageModel", pageModel);
		// 将currpage映射到page，用来获取当前页的页码
		mav.addObject("page", currpage);
		// 将TimetableAppointment映射到timetableAppointment；（教务调整排课入口属性）
		mav.addObject("timetableAppointment", new TimetableAppointment());
		// 获取当前周次
		int week = shareService.findNewWeek();
		// 映射week
		mav.addObject("week", String.valueOf(week));
		mav.addObject("labRoomMap", outerApplicationService.getLabRoomMap(cid));
		
		// 获取实验室排课的通用配置对象；
		CStaticValue cStaticValue = cStaticValueService.getCStaticValueByTimetableLabDevice(cid);
		mav.addObject("cStaticValue", cStaticValue);
		
		// 获取可选的教师列表列表
		mav.addObject("timetableTearcherMap", outerApplicationService.getTimetableTearcherMap());
		mav.addObject("schoolTerm", schoolTerm);
		// 映射schoolTerm的id
		mav.addObject("termId", schoolTerm.getId());
		// 根据课程及id获取课程排课列表
		List<SchoolCourseDetail> schoolCourseDetails = schoolCourseDetailService.getCourseDetailsByQuery(
				schoolTerm.getId(), schoolCourseDetail, currpage - 1, pageSize, cid);
		// 根据课程及id获取课程排课列表
		mav.addObject("schedulingCourseMap", schoolCourseDetails);
		// mav.addObject("schedulingCourseAllMap",
		// schoolCourseDetailService.getCourseDetailsByQuery(schoolTerm.getId(),
		// schoolCourseDetail, -1, -1));
		mav.addObject("schedulingCourseAllMap", schoolCourseService.getCourseCodeListMap(schoolTerm.getId(),cid));
		// 获取该学期的所有周次
		mav.addObject("weeks", schoolCourseDetailService.getWeeksByNow(schoolTerm.getId()));
		mav.setViewName("timetable/schedulingcourse/listTimetableTerm.jsp");
		return mav;
	}
	
	/************************************************************
	 * @ 实验室排课管理-排课-iframe入口
	 * @作者：罗璇
	 * @日期：2017年3月8日
	 ************************************************************/
	@RequestMapping("/timetable/listTimetableIframe")
	public ModelAndView listTimetableIframe(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/timetable/schedulingcourse/listTimetableIframe.jsp");
		return mav;
	}
	
	/************************************************************
	 * @ 实验室排课管理-排课-list
	 * @作者：罗璇
	 * @日期：2017年3月8日
	 ************************************************************/
	@RequestMapping("/timetable/listTimetableAll")
	public ModelAndView listTimetableAll(HttpServletRequest request,
			@ModelAttribute SchoolCourseDetail schoolCourseDetail, @RequestParam int currpage){
		ModelAndView mav = new ModelAndView();
		int cid = -1;
        // 获取学期列表
		List<SchoolTerm> schoolTerms = outerApplicationService.getSchoolTermList();
		mav.addObject("schoolTerms", schoolTerms);
		// 根据课程及id获取选课组列表
		int term = shareService.getBelongsSchoolTerm(Calendar.getInstance()).getId();
		if (request.getParameter("term") != null) {
			term = Integer.parseInt(request.getParameter("term"));
		}
		mav.addObject("term", term);
		SchoolTerm schoolTerm = schoolTermDAO.findSchoolTermById(term);
		// 设置分页变量并赋值为20；
		int pageSize = CommonConstantInterface.INT_PAGESIZE;
		// 判断是否标记位为空，如果为空，则清空schoolCourseDetail
		if (request.getParameter("id") != null && request.getParameter("id").equals("-1")) {
			schoolCourseDetail.setCourseDetailNo(null);
		}
		// 根据课程及id获取课程排课列表的计数
		int totalRecords = schoolCourseDetailService.getCountCourseDetailsAllByQuery(term,
				schoolCourseDetail, cid,request);
		mav.addObject("totalRecords", totalRecords);
		// 获取登陆用户信息
		mav.addObject("user", shareService.getUserDetail());
		Map<String, Integer> pageModel = shareService.getPage(currpage, pageSize, totalRecords);
		mav.addObject("pageModel", pageModel);
		// 将currpage映射到page，用来获取当前页的页码
		mav.addObject("page", currpage);
		// 将TimetableAppointment映射到timetableAppointment；（教务调整排课入口属性）
		mav.addObject("timetableAppointment", new TimetableAppointment());
		// 获取当前周次
		int week = shareService.findNewWeek();
		// 映射week
		mav.addObject("week", String.valueOf(week));
		mav.addObject("labRoomMap", outerApplicationService.getLabRoomMap(cid));
		
		// 获取实验室排课的通用配置对象；
		CStaticValue cStaticValue = cStaticValueService.getCStaticValueByTimetableLabDevice(cid);
		mav.addObject("cStaticValue", cStaticValue);
		
		// 获取可选的教师列表列表
		mav.addObject("timetableTearcherMap", outerApplicationService.getTimetableTearcherMap());
		mav.addObject("schoolTerm", schoolTerm);
		// 映射schoolTerm的id
		mav.addObject("termId", schoolTerm.getId());
		// 根据课程及id获取课程排课列表
		List<SchoolCourseDetail> schoolCourseDetails = schoolCourseDetailService.getCourseDetailsAllByQuery(
				schoolTerm.getId(), schoolCourseDetail, currpage - 1, pageSize, cid,request);
		// 根据课程及id获取课程排课列表
		mav.addObject("schedulingCourseMap", schoolCourseDetails);
		// mav.addObject("schedulingCourseAllMap",
		// schoolCourseDetailService.getCourseDetailsByQuery(schoolTerm.getId(),
		// schoolCourseDetail, -1, -1));
		mav.addObject("schedulingCourseAllMap", schoolCourseService.getCourseCodeListMap(schoolTerm.getId(),cid));
		// 获取该学期的所有周次
		mav.addObject("weeks", schoolCourseDetailService.getWeeksByNow(schoolTerm.getId()));
		//获取所有课程信息
		Map<String,String> schoolCourseInfoMap = schoolCourseInfoService.findAllSchoolCourseInfoMap();
		mav.addObject("schoolCourseInfoMap",schoolCourseInfoMap);
		//获取课程编号
		String courseNumber = "";
		if(request.getParameter("courseNumber")!=null&&!"".equals(request.getParameter("courseNumber"))){
			courseNumber = request.getParameter("courseNumber");
		}
		mav.addObject("courseNumber",courseNumber);
		mav.setViewName("timetable/schedulingcourse/listTimetableAll.jsp");
		return mav;
	}
	
	/************************************************************
	 * @ 实验室排课管理-排课课表-iframe入口
	 * @作者：罗璇
	 * @日期：2017年3月8日
	 ************************************************************/
	@RequestMapping("/timetable/listTCurriculumSchedule")
	public ModelAndView listTCurriculumSchedule(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/timetable/schedulingcourse/listTCurriculumSchedule.jsp");
		return mav;
	}
	
	/************************************************************
	 * @ 教务排课-调整排课-排课时判断当前时间端
	 * @作者：罗璇
	 * @日期：2017年3月14日
	 ************************************************************/
	@ResponseBody
	@RequestMapping("/timetable/checkStudents")
	public String checkStudents(HttpServletRequest request){

		
		//根据当前三个时间维度和选课组编号查看是否有冲突的排课安排
		String result = timetableAppointmentService.checkStudentsByCourseCodeAndTime(request);
		
		return result;
	}
	
	/************************************************************
	 * @ 实验室排课管理-排课课表-教师课表
	 * @作者：张凯
	 * @日期：2017年3月12日
	 ************************************************************/
	/*@RequestMapping("/timetable/listTimetableAppointmentTeacher")
	public ModelAndView lilistTimetableAppointmentTeacherst(){
		ModelAndView mav = new ModelAndView();
		
		return mav;
		
	}*/
	
}
