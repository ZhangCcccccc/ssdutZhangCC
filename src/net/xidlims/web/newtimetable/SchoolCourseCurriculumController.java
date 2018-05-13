/**
 * 
 */
package net.xidlims.web.newtimetable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.xidlims.dao.LabRoomDAO;
import net.xidlims.dao.MessageDAO;
import net.xidlims.dao.OperationItemDAO;
import net.xidlims.dao.SchoolAcademyDAO;
import net.xidlims.dao.SchoolCourseDAO;
import net.xidlims.dao.SchoolCourseDetailDAO;
import net.xidlims.dao.SchoolCourseMergeDAO;
import net.xidlims.dao.SchoolCourseStudentDAO;
import net.xidlims.dao.TimetableAppointmentDAO;
import net.xidlims.dao.TimetableBatchDAO;
import net.xidlims.dao.TimetableBatchStudentDAO;
import net.xidlims.dao.TimetableGroupDAO;
import net.xidlims.dao.TimetableGroupStudentsDAO;
import net.xidlims.dao.TimetableLabRelatedDAO;
import net.xidlims.dao.TimetableTeacherRelatedDAO;
import net.xidlims.dao.UserDAO;
import net.xidlims.domain.LabRoom;
import net.xidlims.domain.OperationOutline;
import net.xidlims.domain.SchoolAcademy;
import net.xidlims.domain.SchoolClasses;
import net.xidlims.domain.SchoolCourse;
import net.xidlims.domain.SchoolCourseDetail;
import net.xidlims.domain.SchoolCourseInfo;
import net.xidlims.domain.SchoolCourseMerge;
import net.xidlims.domain.SchoolTerm;
import net.xidlims.domain.SchoolWeek;
import net.xidlims.domain.TimetableAppointment;
import net.xidlims.domain.TimetableAppointmentSameNumber;
import net.xidlims.domain.TimetableGroup;
import net.xidlims.domain.TimetableGroupStudents;
import net.xidlims.domain.TimetableItemRelated;
import net.xidlims.domain.TimetableLabRelated;
import net.xidlims.domain.TimetableTeacherRelated;
import net.xidlims.domain.User;
import net.xidlims.service.common.ShareService;
import net.xidlims.service.newoperation.NewOperationService;
import net.xidlims.service.newtimetable.NewTimetableCourseSchedulingService;
import net.xidlims.service.newtimetable.TimetableAppointmentSaveService;
import net.xidlims.service.system.TermDetailService;
import net.xidlims.service.system.TimeDetailService;
import net.xidlims.service.system.UserDetailService;
import net.xidlims.service.timetable.OuterApplicationService;
import net.xidlims.service.timetable.SchoolCourseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import excelTools.ExcelUtils;
import excelTools.JsGridReportBase;
import excelTools.TableData;

/**
 * @author 贺子龙
 * 
 */

@Controller("SchoolCourseCurriculumController")
@SessionAttributes("selected_labCenter")
@RequestMapping("/newtimetable")
public class SchoolCourseCurriculumController<JsonResult> {
	@Autowired
	private ShareService shareService;
	@Autowired
	private TimetableAppointmentDAO timetableAppointmentDAO;
	@Autowired
	private OuterApplicationService outerApplicationService;
	@Autowired
	private NewTimetableCourseSchedulingService newTimetableCourseSchedulingService;
	@Autowired
	private SchoolCourseDetailDAO schoolCourseDetailDAO;
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private TermDetailService termDetailService;
	@Autowired
	private LabRoomDAO labRoomDAO;
	@Autowired
	private TimetableAppointmentSaveService timetableAppointmentSaveService;
	@Autowired
	private TimetableBatchDAO timetableBatchDAO;
	@Autowired
	private TimetableGroupDAO timetableGroupDAO;
	@Autowired
	private TimetableGroupStudentsDAO timetableGroupStudentsDAO;
	@Autowired
	private TimetableTeacherRelatedDAO timetableTeacherRelatedDAO;
	@Autowired
	private SchoolCourseMergeDAO schoolCourseMergeDAO;
	@Autowired
	private OperationItemDAO operationItemDAO;
	@Autowired
	private TimetableBatchStudentDAO timetableBatchStudentDAO;
	@Autowired
	private SchoolCourseStudentDAO schoolCourseStudentDAO;
	@Autowired
	private SchoolAcademyDAO schoolAcademyDAO;
	@Autowired
	private TimetableLabRelatedDAO timetableLabRelatedDAO;
	@Autowired
	private TimeDetailService timeDetailService;
	@Autowired
	private NewOperationService newOperationService;
	@Autowired
	private SchoolCourseDAO schoolCourseDAO;
	@Autowired
	private MessageDAO messageDAO;
	@Autowired
	private UserDetailService userDetailService;
	@Autowired
	private SchoolCourseService schoolCourseService;

	/*************************************************************************
	 * @初始化WebDataBinder，这个WebDataBinder用于填充被@InitBinder注释的处理 方法的command和form对象
	 * 
	 *************************************************************************/
	@InitBinder
	public void initBinder(WebDataBinder binder, HttpServletRequest request) {
		binder.registerCustomEditor(java.util.Calendar.class,
				new org.skyway.spring.util.databinding.CustomCalendarEditor());
		binder.registerCustomEditor(
				byte[].class,
				new org.springframework.web.multipart.support.ByteArrayMultipartFileEditor());
		binder.registerCustomEditor(boolean.class,
				new org.skyway.spring.util.databinding.EnhancedBooleanEditor(
						false));
		binder.registerCustomEditor(Boolean.class,
				new org.skyway.spring.util.databinding.EnhancedBooleanEditor(
						true));
		binder.registerCustomEditor(java.math.BigDecimal.class,
				new org.skyway.spring.util.databinding.NaNHandlingNumberEditor(
						java.math.BigDecimal.class, true));
		binder.registerCustomEditor(Integer.class,
				new org.skyway.spring.util.databinding.NaNHandlingNumberEditor(
						Integer.class, true));
		binder.registerCustomEditor(java.util.Date.class,
				new org.skyway.spring.util.databinding.CustomDateEditor());
		binder.registerCustomEditor(String.class,
				new org.skyway.spring.util.databinding.StringEditor());
		binder.registerCustomEditor(Long.class,
				new org.skyway.spring.util.databinding.NaNHandlingNumberEditor(
						Long.class, true));
		binder.registerCustomEditor(Double.class,
				new org.skyway.spring.util.databinding.NaNHandlingNumberEditor(
						Double.class, true));
	}

	/************************************************************
	 * @description：我的课表（学生）
	 * @author：郑昕茹
	 * @date：2017-04-17
	 ************************************************************/
	@RequestMapping("/myTimetableStudent")
	public ModelAndView myTimetableStudent(HttpServletRequest request,
			String courseNo) {
		ModelAndView mav = new ModelAndView();
		if (request.getSession().getAttribute("authorityName")
				.equals("SUPERADMIN")) {
			mav.setViewName("redirect:/newtimetable/myTimetableAdmin");
		}
		if (request.getSession().getAttribute("authorityName")
				.equals("TEACHER")) {
			mav.setViewName("redirect:/newtimetable/myTimetableTeacher");
		}
		if (request.getSession().getAttribute("authorityName")
				.equals("COURSETEACHER")) {
			mav.setViewName("redirect:/newtimetable/myTimetableCourseTeacher");
		}
		if (request.getSession().getAttribute("authorityName")
				.equals("STUDENT")) {
			// 登陆人权限获得
			mav.addObject("authorities", shareService.getUser()
					.getAuthorities());
			mav.addObject("user", shareService.getUser());

			Integer termId = -1;
			String courseId = "";
			//学期查询条件
			if (request.getParameter("term") != null) {
				termId = Integer.parseInt(request.getParameter("term"));
			}
			//当前学期
			if (termId == -1) {
				termId = shareService.getBelongsSchoolTerm(
						Calendar.getInstance()).getId();
			}
			//课程
			if (request.getParameter("courseId") != null) {
				courseId = request.getParameter("courseId");
			}
			Integer type = 1;
			mav.addObject("termId", termId);
			if (courseNo != null) {
				courseId = courseNo;
			}
			mav.addObject("courseId", courseId);
			// 登陆人权限获得
			mav.addObject("authorities", shareService.getUser()
					.getAuthorities());
			//学期所有的起止年、月、日、星期、时间
			List<Object[]> schoolTermWeeks = termDetailService
					.findViewSchoolTermWeek(termId);
			mav.addObject("schoolTermWeeks", schoolTermWeeks);
			//特殊的周次、星期（如节日、期中考等）
			mav.addObject("specialSchoolWeeks",
					termDetailService.findSpecialSchoolWeekByTerm(termId));

			List<SchoolTerm> schoolTerms = outerApplicationService
					.getSchoolTermList();
			mav.addObject("schoolTerms", schoolTerms);
			Set<TimetableAppointment> appointments = new HashSet<TimetableAppointment>();
			Map<String, String> courses = new HashMap<String, String>();
			//所在分组
			Set<SchoolCourseDetail> details = new HashSet<SchoolCourseDetail>();
			String sql = "select t from TimetableGroupStudents t where  t.user.username like '"
					+ shareService.getUserDetail().getUsername() + "' ";
			List<TimetableGroupStudents> tass = timetableGroupStudentsDAO
					.executeQuery(sql, 0, -1);
			Set<SchoolCourseInfo> courseInfos = new HashSet<SchoolCourseInfo>();
			//遍历所在分组的排课信息
			if (tass.size() > 0) {
				for (TimetableGroupStudents ta : tass) {
					for (TimetableAppointment te : ta.getTimetableGroup()
							.getTimetableAppointments()) {
						// 不合班
						if ((te.getTimetableStyle() != 25
								&& te.getTimetableStyle() != 24 && te
								.getTimetableStyle() != 26)
								&& te.getSchoolCourseDetail() != null
								&& te.getSchoolCourseDetail().getSchoolTerm()
										.getId().intValue() == termId
										.intValue() && te.getStatus() == 1) {
							courses.put(te.getSchoolCourseDetail()
									.getSchoolCourse().getCourseNo(), te
									.getSchoolCourseDetail().getCourseName()
									+ te.getSchoolCourseDetail()
											.getSchoolCourse().getCourseNo());
							if (!courseId.equals("") && courseId.equals(te.getSchoolCourse().getCourseNo())) {
								appointments.add(te);
								courseInfos.add(te.getSchoolCourseDetail()
										.getSchoolCourse()
										.getSchoolCourseInfo());
								type = 2;
							}
							if (courseId.equals("")) {
								appointments.add(te);
								courseInfos.add(te.getSchoolCourseDetail()
										.getSchoolCourse()
										.getSchoolCourseInfo());

							}
						}
						// 合班
						if ((te.getTimetableStyle() == 25
								|| te.getTimetableStyle() == 24 || te
								.getTimetableStyle() == 26)
								&& te.getSchoolCourseMerge() != null
								&& te.getSchoolCourseMerge().getTermId()
										.intValue() == termId.intValue()
								&& te.getStatus() == 1) {
							courses.put(
									te.getSchoolCourseMerge().getCourseNo(), te
											.getSchoolCourseMerge()
											.getCourseName()
											+ te.getSchoolCourseMerge()
													.getCourseNo());
							if (!courseId.equals("")//课程查询
									&& courseId.equals(te
											.getSchoolCourseMerge()
											.getCourseNo())) {
								type = 2;
								appointments.add(te);
								Set<SchoolCourseDetail> courseDetails = te
										.getSchoolCourseMerge()
										.getSchoolCourseDetails();
								for (SchoolCourseDetail s : courseDetails) {
									courseInfos.add(s.getSchoolCourse()
											.getSchoolCourseInfo());
									break;
								}
							}
							if (courseId.equals("")) {
								appointments.add(te);
								Set<SchoolCourseDetail> courseDetails = te
										.getSchoolCourseMerge()
										.getSchoolCourseDetails();
								for (SchoolCourseDetail s : courseDetails) {
									courseInfos.add(s.getSchoolCourse()
											.getSchoolCourseInfo());
									break;
								}
							}
						}
					}
				}
			}
			mav.addObject("type", type);//查询类型-页面没有用到这个参数
			mav.addObject("appointments", appointments);
			mav.addObject("courses", courses);
			mav.addObject("courseInfos", courseInfos);
			mav.addObject("details", details);
			mav.setViewName("newtimetable/myTimetableStudent.jsp");

		}
		return mav;
	}

	/************************************************************
	 * @description：我的课表（教师i）
	 * @author：郑昕茹
	 * @date：2017-04-17
	 ************************************************************/
	@RequestMapping("/myTimetableTeacher")
	public ModelAndView myTimetableTeacher(HttpServletRequest request) {

		ModelAndView mav = new ModelAndView();
		if (request.getSession().getAttribute("authorityName")
				.equals("SUPERADMIN")) {
			mav.setViewName("redirect:/newtimetable/myTimetableAdmin");
		}
		if (request.getSession().getAttribute("authorityName")
				.equals("TEACHER")) {
			// 登陆人权限获得
			mav.addObject("authorities", shareService.getUser()
					.getAuthorities());
			mav.addObject("user", shareService.getUser());
			Integer termId = -1;
			String courseId = "";
			String academy = "";
			if (request.getParameter("term") != null) {
				termId = Integer.parseInt(request.getParameter("term"));
			}
			if (termId == -1) {
				termId = shareService.getBelongsSchoolTerm(
						Calendar.getInstance()).getId();
			}
			if (request.getParameter("courseId") != null) {
				courseId = request.getParameter("courseId");
			}
			if (request.getParameter("academy") != null) {
				academy = request.getParameter("academy");
			}
			mav.addObject("termId", termId);
			mav.addObject("courseId", courseId);
			
			List<Object[]> schoolTermWeeks = termDetailService
					.findViewSchoolTermWeek(termId);
			mav.addObject("schoolTermWeeks", schoolTermWeeks);
			mav.addObject("specialSchoolWeeks",
					termDetailService.findSpecialSchoolWeekByTerm(termId));

			List<SchoolTerm> schoolTerms = outerApplicationService
					.getSchoolTermList();
			mav.addObject("schoolTerms", schoolTerms);
			Set<TimetableAppointment> appointments = new HashSet<TimetableAppointment>();
			Set<String> academys = new HashSet<String>();

			// 非教务
			String sql = "select t from TimetableTeacherRelated t where  t.user.username like '"
					+ shareService.getUserDetail().getUsername() + "' ";
			List<TimetableTeacherRelated> ttrs = timetableTeacherRelatedDAO
					.executeQuery(sql, 0, -1);

			Set<SchoolCourseInfo> courseInfos = new HashSet<SchoolCourseInfo>();
			if (ttrs.size() > 0) {
				for (TimetableTeacherRelated ta : ttrs) {
					TimetableAppointment te = ta.getTimetableAppointment();
					
					if(te.getStatus() == 1){//已发布
						//课程查询
						if(!courseId.equals("")){
							//合班的课程安排
							if(te.getSchoolCourseDetail() != null && te.getSchoolCourseDetail().getSchoolCourseMerge() != null
									 && te.getSchoolCourseDetail().getSchoolCourseMerge().getCourseNo().equals(courseId)){
								//同时-学院查询
								if(!academy.equals("") 
										&& te.getSchoolCourse().getSchoolCourseInfo().getAcademyNumber().equals(academy)){
									appointments.add(te);
									//课程列表-判断是否存在
									if(!courseInfos.contains(te.getSchoolCourse().getSchoolCourseInfo())){
										courseInfos.add(te.getSchoolCourse().getSchoolCourseInfo());
									}
									//学院
									if(!academys.contains(te.getSchoolCourse().getSchoolCourseInfo().getAcademyNumber())){
										academys.add(te.getSchoolCourse().getSchoolCourseInfo().getAcademyNumber());
									}
								}else{//无-学院查询
									appointments.add(te);
									//课程列表-判断是否存在
									if(te.getSchoolCourse() != null 
											&& !courseInfos.contains(te.getSchoolCourse().getSchoolCourseInfo())){
										courseInfos.add(te.getSchoolCourse().getSchoolCourseInfo());
									}
									//学院
									if(te.getSchoolCourse() != null
											&& !academys.contains(te.getSchoolCourse().getSchoolCourseInfo().getAcademyNumber())){
										academys.add(te.getSchoolCourse().getSchoolCourseInfo().getAcademyNumber());
									}
								}
							}else if(te.getSchoolCourse() != null 
									&& te.getSchoolCourse().getCourseNo().equals(courseId)){
								//不合班的课程安排
								//同时-学院查询
								if(!academy.equals("") 
										&& te.getSchoolCourse().getSchoolCourseInfo().getAcademyNumber().equals(academy)){
									appointments.add(te);
									//课程列表-判断是否存在
									if(!courseInfos.contains(te.getSchoolCourse().getSchoolCourseInfo())){
										courseInfos.add(te.getSchoolCourse().getSchoolCourseInfo());
									}
									//学院
									if(!academys.contains(te.getSchoolCourse().getSchoolCourseInfo().getAcademyNumber())){
										academys.add(te.getSchoolCourse().getSchoolCourseInfo().getAcademyNumber());
									}
								}else{//无-学院查询
									appointments.add(te);
									//课程列表-判断是否存在
									if(!courseInfos.contains(te.getSchoolCourse().getSchoolCourseInfo())){
										courseInfos.add(te.getSchoolCourse().getSchoolCourseInfo());
									}
									//学院
									if(!academys.contains(te.getSchoolCourse().getSchoolCourseInfo().getAcademyNumber())){
										academys.add(te.getSchoolCourse().getSchoolCourseInfo().getAcademyNumber());
									}
								}
							}
						}else if(courseId.equals("")){//无-课程查询
							//合班的课程安排
							if(te.getSchoolCourseDetail() != null && te.getSchoolCourseDetail().getSchoolCourseMerge() != null){
								//同时-学院查询
								if(!academy.equals("") 
										&& te.getSchoolCourse().getSchoolCourseInfo().getAcademyNumber().equals(academy)){
									appointments.add(te);
									//课程列表-判断是否存在
									if(!courseInfos.contains(te.getSchoolCourse().getSchoolCourseInfo())){
										courseInfos.add(te.getSchoolCourse().getSchoolCourseInfo());
									}
									//学院
									if(!academys.contains(te.getSchoolCourse().getSchoolCourseInfo().getAcademyNumber())){
										academys.add(te.getSchoolCourse().getSchoolCourseInfo().getAcademyNumber());
									}
								}else{//无-学院查询
									appointments.add(te);
									//课程列表-判断是否存在
									if(!courseInfos.contains(te.getSchoolCourse().getSchoolCourseInfo())){
										courseInfos.add(te.getSchoolCourse().getSchoolCourseInfo());
									}
									//学院
									if(!academys.contains(te.getSchoolCourse().getSchoolCourseInfo().getAcademyNumber())){
										academys.add(te.getSchoolCourse().getSchoolCourseInfo().getAcademyNumber());
									}
								}
							}else{
								//同时-学院查询
								if(!academy.equals("") 
										&& te.getSchoolCourse().getSchoolCourseInfo().getAcademyNumber().equals(academy)){
									appointments.add(te);
									//课程列表-判断是否存在
									if(!courseInfos.contains(te.getSchoolCourse().getSchoolCourseInfo())){
										courseInfos.add(te.getSchoolCourse().getSchoolCourseInfo());
									}
									//学院
									if(!academys.contains(te.getSchoolCourse().getSchoolCourseInfo().getAcademyNumber())){
										academys.add(te.getSchoolCourse().getSchoolCourseInfo().getAcademyNumber());
									}
								}else{//无-学院查询
									appointments.add(te);
									//课程列表-判断是否存在
									if(te.getSchoolCourse() != null && !courseInfos.contains(te.getSchoolCourse().getSchoolCourseInfo())){
										courseInfos.add(te.getSchoolCourse().getSchoolCourseInfo());
									}
									//学院
									if(te.getSchoolCourse() != null 
											&& !academys.contains(te.getSchoolCourse().getSchoolCourseInfo().getAcademyNumber())){
										academys.add(te.getSchoolCourse().getSchoolCourseInfo().getAcademyNumber());
									}
								}
							}
						}
					}
				}
			}
			String hql ="select u.schoolCourseDetails2 from User u where 1=1 and u.username =" +
					"'" + shareService.getUser().getUsername() +"'";
			List<SchoolCourseDetail> courses = schoolCourseDetailDAO.executeQuery(hql, 0, -1);
			mav.addObject("appointments", appointments);
			//课程查询选框-教务推送的教师的所有课程
			mav.addObject("courses", courses);
			//学院下拉选框列表
			Map<String, String> mapAcademy = new HashMap<String, String>();
			for (String academyNumber : academys) {
				SchoolAcademy schoolAcademy = schoolAcademyDAO.findSchoolAcademyByAcademyNumber(academyNumber);
				mapAcademy.put(schoolAcademy.getAcademyNumber(),schoolAcademy.getAcademyName());
			}
			mav.addObject("mapAcademy", mapAcademy);
			
			mav.addObject("user", shareService.getUser());

			mav.addObject("courseInfos", courseInfos);//下方课程列表
			mav.setViewName("newtimetable/myTimetableTeacher.jsp");
		}
		if (request.getSession().getAttribute("authorityName")
				.equals("STUDENT")) {
			mav.setViewName("redirect:/newtimetable/myTimetableStudent");
		}
		if (request.getSession().getAttribute("authorityName")
				.equals("COURSETEACHER")) {
			mav.setViewName("redirect:/newtimetable/myTimetableCourseTeacher");
		}

		return mav;
	}

	/************************************************************
	 * @description：我的课表（课程负责教师）
	 * @author：郑昕茹
	 * @date：2017-04-17
	 ************************************************************/
	@RequestMapping("/myTimetableCourseTeacher")
	public ModelAndView myTimetableCourseTeacher(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		if (request.getSession().getAttribute("authorityName")
				.equals("SUPERADMIN")) {
			mav.setViewName("redirect:/newtimetable/myTimetableAdmin");
		}
		if (request.getSession().getAttribute("authorityName")
				.equals("TEACHER")) {
			mav.setViewName("redirect:/newtimetable/myTimetableTeacher");
		}
		if (request.getSession().getAttribute("authorityName")
				.equals("STUDENT")) {
			mav.setViewName("redirect:/newtimetable/myTimetableStudent");
		}
		if (request.getSession().getAttribute("authorityName")
				.equals("COURSETEACHER")) {
			// 登陆人权限获得
			mav.addObject("authorities", shareService.getUser()
					.getAuthorities());
			mav.addObject("user", shareService.getUser());
			Integer termId = -1;
			String courseId = "";
			Integer labId = -1;
			if (request.getParameter("term") != null) {
				termId = Integer.parseInt(request.getParameter("term"));
			}
			if (termId == -1) {
				termId = shareService.getBelongsSchoolTerm(
						Calendar.getInstance()).getId();
			}
			if (request.getParameter("courseId") != null) {
				courseId = request.getParameter("courseId");
			}
			if (request.getParameter("labId") != null
					&& !request.getParameter("labId").equals("")) {
				labId = Integer.parseInt(request.getParameter("labId"));
			}
			mav.addObject("termId", termId);
			mav.addObject("courseId", courseId);
			mav.addObject("labId", labId);
			// 登陆人权限获得
			mav.addObject("authorities", shareService.getUser()
					.getAuthorities());
			List<Object[]> schoolTermWeeks = termDetailService
					.findViewSchoolTermWeek(termId);
			mav.addObject("schoolTermWeeks", schoolTermWeeks);
			mav.addObject("specialSchoolWeeks",
					termDetailService.findSpecialSchoolWeekByTerm(termId));

			List<SchoolTerm> schoolTerms = outerApplicationService
					.getSchoolTermList();
			mav.addObject("schoolTerms", schoolTerms);
			
			Set<SchoolCourseInfo> courseInfos = new HashSet<SchoolCourseInfo>();
			Map<String, String> courseDetails = new HashMap<String, String>();
			
			String hql ="select u.schoolCourseDetails from User u where 1=1 and u.username ='" + shareService.getUser().getUsername() +"'";
			List<SchoolCourseDetail> courses = schoolCourseDetailDAO
					.executeQuery(hql, 0, -1);

			//负责教师所有相关的课程安排
			Set<TimetableAppointment> timetableAppointments = new HashSet<TimetableAppointment>();
			for(SchoolCourseDetail schoolCourseDetail : courses){
				//筛选同一学期的实验课程
				if(schoolCourseDetail.getSchoolTerm().getId().intValue() == termId.intValue() 
						&& schoolCourseDetail.getTimetableAppointments() != null
						&& schoolCourseDetail.getWeekday() == null){
					//课程查询
					if(!courseId.equals("")){
						//合班的课程安排
						if(schoolCourseDetail.getSchoolCourseMerge() != null
								 && schoolCourseDetail.getSchoolCourseMerge().getCourseNo().equals(courseId)){
							for(TimetableAppointment timetableAppointment : schoolCourseDetail.getSchoolCourseMerge().getTimetableAppointments()){
								if(timetableAppointment.getStatus() == 1){
									//同时-实验室查询
									if(labId != -1 && timetableAppointment.getTimetableLabRelateds() != null){
										for(TimetableLabRelated timetableLabRelated : timetableAppointment.getTimetableLabRelateds()){
											if(timetableLabRelated.getLabRoom().getId().equals(labId)){
												timetableAppointments.add(timetableAppointment);
												//课程列表-判断是否存在
												if(!courseInfos.contains(schoolCourseDetail.getSchoolCourse().getSchoolCourseInfo())){
													courseInfos.add(schoolCourseDetail.getSchoolCourse().getSchoolCourseInfo());
												}
											}
										}
									}else{//无-实验室查询
										timetableAppointments.add(timetableAppointment);
										//课程列表-判断是否存在
										if(!courseInfos.contains(schoolCourseDetail.getSchoolCourse().getSchoolCourseInfo())){
											courseInfos.add(schoolCourseDetail.getSchoolCourse().getSchoolCourseInfo());
										}
									}
								}
							}
						}else if(schoolCourseDetail.getSchoolCourse().getCourseNo().equals(courseId)){
							//不合班的课程安排
							for(TimetableAppointment timetableAppointment : schoolCourseDetail.getTimetableAppointments()){
								if(timetableAppointment.getStatus() == 1){
									//同时-实验室查询
									if(labId != -1 && timetableAppointment.getTimetableLabRelateds() != null){
										for(TimetableLabRelated timetableLabRelated : timetableAppointment.getTimetableLabRelateds()){
											if(timetableLabRelated.getLabRoom().getId().equals(labId)){
												timetableAppointments.add(timetableAppointment);
												//课程列表-判断是否存在
												if(!courseInfos.contains(schoolCourseDetail.getSchoolCourse().getSchoolCourseInfo())){
													courseInfos.add(schoolCourseDetail.getSchoolCourse().getSchoolCourseInfo());
												}
											}
										}
									}else{//无-实验室查询
										timetableAppointments.add(timetableAppointment);
										//课程列表-判断是否存在
										if(!courseInfos.contains(schoolCourseDetail.getSchoolCourse().getSchoolCourseInfo())){
											courseInfos.add(schoolCourseDetail.getSchoolCourse().getSchoolCourseInfo());
										}
									}
								}
							}
						}
					}else if(courseId.equals("")){//无-课程查询
						//合班的课程安排
						if(schoolCourseDetail.getSchoolCourseMerge() != null){
							for(TimetableAppointment timetableAppointment : schoolCourseDetail.getSchoolCourseMerge().getTimetableAppointments()){
								if(timetableAppointment.getStatus() == 1){
									//同时-实验室查询
									if(labId != -1 && timetableAppointment.getTimetableLabRelateds() != null){
										for(TimetableLabRelated timetableLabRelated : timetableAppointment.getTimetableLabRelateds()){
											if(timetableLabRelated.getLabRoom().getId() == labId){
												timetableAppointments.add(timetableAppointment);
												//课程列表-判断是否存在
												if(!courseInfos.contains(schoolCourseDetail.getSchoolCourse().getSchoolCourseInfo())){
													courseInfos.add(schoolCourseDetail.getSchoolCourse().getSchoolCourseInfo());
												}
											}
										}
									}else{//无-实验室查询
										timetableAppointments.add(timetableAppointment);
										//课程列表-判断是否存在
										if(!courseInfos.contains(schoolCourseDetail.getSchoolCourse().getSchoolCourseInfo())){
											courseInfos.add(schoolCourseDetail.getSchoolCourse().getSchoolCourseInfo());
										}
									}
								}
							}
						}else{
							//不合班的课程安排
							for(TimetableAppointment timetableAppointment : schoolCourseDetail.getTimetableAppointments()){
								if(timetableAppointment.getStatus() == 1){
									//同时-实验室查询
									if(labId != -1 && timetableAppointment.getTimetableLabRelateds() != null){
										for(TimetableLabRelated timetableLabRelated : timetableAppointment.getTimetableLabRelateds()){
											if(timetableLabRelated.getLabRoom().getId() == labId){
												timetableAppointments.add(timetableAppointment);
												//课程列表-判断是否存在
												if(!courseInfos.contains(schoolCourseDetail.getSchoolCourse().getSchoolCourseInfo())){
													courseInfos.add(schoolCourseDetail.getSchoolCourse().getSchoolCourseInfo());
												}
											}
										}
									}else{//无-实验室查询
										timetableAppointments.add(timetableAppointment);
										//课程列表-判断是否存在
										if(!courseInfos.contains(schoolCourseDetail.getSchoolCourse().getSchoolCourseInfo())){
											courseInfos.add(schoolCourseDetail.getSchoolCourse().getSchoolCourseInfo());
										}
									}
								}
							}
						}
					}
					//课程查询列表
					//合班
					if(schoolCourseDetail.getSchoolCourseMerge() != null){
						courseDetails.put(schoolCourseDetail.getSchoolCourseMerge().getCourseNo(), 
								schoolCourseDetail.getSchoolCourseMerge().getCourseName()
								+schoolCourseDetail.getSchoolCourseMerge().getCourseNo());
					}else{
						courseDetails.put(schoolCourseDetail.getSchoolCourse().getCourseNo(), 
								schoolCourseDetail.getSchoolCourse().getCourseName()
								+schoolCourseDetail.getSchoolCourse().getCourseNo());
					}
				}
			}
			mav.addObject("courses", courseDetails);
			mav.addObject("courseInfos", courseInfos);
			mav.addObject("user", shareService.getUser());
				
			if (courseId.equals("") && labId == -1) {//查询条件为空-默认
				mav.addObject("type", 1);
			} else{
				mav.addObject("type", 2);
//				mav.addObject("details", shareService.getUser().getSchoolCourseDetails());
			}
			
			mav.addObject("appointments", timetableAppointments);
			mav.addObject("labRoomMap", newTimetableCourseSchedulingService.getLabRoomMap());
			mav.setViewName("newtimetable/myTimetableCourseTeacher.jsp");
		}

		return mav;
	}

	/************************************************************
	 * @description：我的课表（超级管理员）
	 * @author：郑昕茹
	 * @date：2017-04-17
	 ************************************************************/
	@RequestMapping("/myTimetableAdmin")
	public ModelAndView myTimetableAdmin(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		if (request.getSession().getAttribute("authorityName")
				.equals("SUPERADMIN")
				|| request.getSession().getAttribute("authorityName")
						.equals("LABCENTERMANAGER")) {//实验中心管理员&超管
			// 登陆人权限获得
			mav.addObject("authorities", shareService.getUser()
					.getAuthorities());
			mav.addObject("user", shareService.getUser());
			Integer termId = -1;
			String courseId = "";
			Integer labId = -1;
			//学期查询
			if (request.getParameter("term") != null) {
				termId = Integer.parseInt(request.getParameter("term"));
			}
			if (termId == -1) {//当前学期
				termId = shareService.getBelongsSchoolTerm(
						Calendar.getInstance()).getId();
			}
			//课程查询
			if (request.getParameter("courseId") != null) {
				courseId = request.getParameter("courseId");
			}
			//实验室查询
			if (request.getParameter("labId") != null
					&& !request.getParameter("labId").equals("")) {
				labId = Integer.parseInt(request.getParameter("labId"));
			}
			//页面传参
			mav.addObject("termId", termId);
			mav.addObject("courseId", courseId);
			mav.addObject("labId", labId);
			// 登陆人权限获得
			mav.addObject("authorities", shareService.getUser()
					.getAuthorities());
			//根据学期找到该学期的最大最小周次，最大最小年份，最大最小月份
			List<Object[]> schoolTermWeeks = termDetailService
					.findViewSchoolTermWeek(termId);
			mav.addObject("schoolTermWeeks", schoolTermWeeks);
			//根据学期找到其下的所有特殊的schoolWeek
			mav.addObject("specialSchoolWeeks",
					termDetailService.findSpecialSchoolWeekByTerm(termId));
			//所有学期
			List<SchoolTerm> schoolTerms = outerApplicationService
					.getSchoolTermList();
			mav.addObject("schoolTerms", schoolTerms);
			Set<TimetableAppointment> appointments = new HashSet<TimetableAppointment>();
			mav.addObject("courses", newTimetableCourseSchedulingService
					.findMyScheduleListView(request.getSession(),
							new SchoolCourseDetail(), 1, -1));
			Set<SchoolCourseInfo> courseInfos = new HashSet<SchoolCourseInfo>();
			
			/*if (courseId.equals("") && labId == -1) {
				//查询条件为空-默认
				String sql = "select t from TimetableAppointment t where 1=1";
				List<TimetableAppointment> ttrs = timetableAppointmentDAO.executeQuery(sql, 0, -1);

				Set<SchoolCourseDetail> details = new HashSet<SchoolCourseDetail>();

				for(TimetableAppointment te : ttrs){
				
						if(te.getSchoolCourseDetail() != null 
								&& te.getSchoolCourseDetail().getSchoolTerm().getId().intValue() == termId.intValue() 
								&& te.getStatus() == 1){
							appointments.add(te);
							courseInfos.add(te.getSchoolCourseDetail()
									.getSchoolCourse().getSchoolCourseInfo());
							break;
						}
			}
			}*/
				if (labId != -1) {//实验室查询
				String sql = "select t from TimetableLabRelated t where  t.labRoom.id ="
						+ labId;
				List<TimetableLabRelated> tlrs = timetableLabRelatedDAO
						.executeQuery(sql, 0, -1);

				//排课生成后的课程详情（周次、教师等）
				if (tlrs.size() > 0) {

					for (TimetableLabRelated ta : tlrs) {
						TimetableAppointment te = ta.getTimetableAppointment();
						// 不合班
						//24合班排课批次学生自选，25没用，26分组排课？？？
						if ((te.getTimetableStyle() != 25
								&& te.getTimetableStyle() != 24 && te
								.getTimetableStyle() != 26)
								&& te.getSchoolCourseDetail() != null
								&& te.getSchoolCourseDetail().getSchoolTerm()
										.getId().intValue() == termId//相同学期
										.intValue() && te.getStatus() == 1) {//status=1已发布
							if (courseId.equals("")) {//无课程查询条件
								appointments.add(te);
								courseInfos.add(te.getSchoolCourseDetail()
										.getSchoolCourse()
										.getSchoolCourseInfo());
							} else if (!courseId.equals("")
									&& te.getSchoolCourseDetail()
											.getSchoolCourse().getCourseNo()
											.equals(courseId)) {//相同课程
								appointments.add(te);
								courseInfos.add(te.getSchoolCourseDetail()
										.getSchoolCourse()
										.getSchoolCourseInfo());
							}
						}
						// 合班
						if ((te.getTimetableStyle() == 25
								|| te.getTimetableStyle() == 24 || te
								.getTimetableStyle() == 26)
								&& te.getSchoolCourseMerge() != null
								&& te.getSchoolCourseMerge().getTermId()
										.intValue() == termId.intValue()//同一学期
								&& te.getStatus() == 1) {//已发布的排课信息
							if (courseId.equals("")) {//
								appointments.add(te);
								Set<SchoolCourseDetail> courseDetails = te
										.getSchoolCourseMerge()
										.getSchoolCourseDetails();
								for (SchoolCourseDetail s : courseDetails) {
									courseInfos.add(s.getSchoolCourse()
											.getSchoolCourseInfo());
									break;
								}
							} else if (!courseId.equals("")
									&& te.getSchoolCourseMerge().getCourseNo()
											.equals(courseId)) {
								appointments.add(te);
								Set<SchoolCourseDetail> courseDetails = te
										.getSchoolCourseMerge()
										.getSchoolCourseDetails();
								for (SchoolCourseDetail s : courseDetails) {
									courseInfos.add(s.getSchoolCourse()
											.getSchoolCourseInfo());
									break;
								}
							}
						}
					}
				}
				mav.addObject("type", 3);

			} else if (!courseId.equals("")) {
				SchoolCourse schoolCourse = schoolCourseDAO
						.findSchoolCourseByCourseNo(courseId);//课程编号
				if(schoolCourse != null){//查询不合班课程
					for (SchoolCourseDetail schoolCourseDetail : schoolCourse
							.getSchoolCourseDetails()) {
						if (schoolCourseDetail != null
								&& schoolCourseDetail.getTimetableAppointments() != null) {
							courseInfos.add(schoolCourseDetail.getSchoolCourse()
									.getSchoolCourseInfo());
							for (TimetableAppointment te : schoolCourseDetail
									.getTimetableAppointments()) {
								
								if (te.getSchoolCourseDetail().getSchoolTerm()
										.getId().intValue() == termId.intValue()
										&& te.getStatus() == 1) {
									appointments.add(te);
								}
							}
						}
					}
				}
				else{//查询合班课程
					String sql = "select s from SchoolCourseMerge s where 1=1 and s.courseNo ='"
							+ courseId + "'";
					List<SchoolCourseMerge> merges = schoolCourseMergeDAO
							.executeQuery(sql, 0, -1);
					SchoolCourseMerge schoolCourseMerge = null;
					if (merges != null && merges.size() != 0) {
						//school_course&school_course_merge一对一
						schoolCourseMerge = merges.get(0);
					}
					//课程列表信息
					if (schoolCourseMerge != null) {
						for (SchoolCourseDetail s : schoolCourseMerge
								.getSchoolCourseDetails()) {
							courseInfos.add(s.getSchoolCourse()
									.getSchoolCourseInfo());
							break;
						}
					}
					
					if (schoolCourseMerge != null
							&& schoolCourseMerge.getTimetableAppointments() != null) {
	
						for (TimetableAppointment te : schoolCourseMerge
								.getTimetableAppointments()) {
							// 不合班
							if (te.getSchoolCourseMerge().getTermId().intValue() == termId
									.intValue() && te.getStatus() == 1) {
								appointments.add(te);
							}
						}
					}
				}
				mav.addObject("type", 2);
			}
			mav.addObject("appointments", appointments);
			mav.addObject("labRoomMap",
					newTimetableCourseSchedulingService.getLabRoomMap());
			mav.addObject("courseInfos", courseInfos);
			mav.setViewName("newtimetable/myTimetableAdmin.jsp");
		}
		if (request.getSession().getAttribute("authorityName")
				.equals("TEACHER")) {
			mav.setViewName("redirect:/newtimetable/myTimetableTeacher");
		}
		if (request.getSession().getAttribute("authorityName")
				.equals("STUDENT")) {
			mav.setViewName("redirect:/newtimetable/myTimetableStudent");
		}
		if (request.getSession().getAttribute("authorityName")
				.equals("COURSETEACHER")) {
			mav.setViewName("redirect:/newtimetable/myTimetableCourseTeacher");
		}
		return mav;
	}

	
	/************************************************************
	 * @description：我的课表（实验中心主任）
	 * @author：郑昕茹
	 * @date：2017-04-17
	 ************************************************************/
	@RequestMapping("/myTimetableLabDirector")
	public ModelAndView myTimetableLabDirector(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		if (request.getSession().getAttribute("authorityName").equals("EXCENTERDIRECTOR")) {//实验中心主任
			// 登陆人权限获得
			mav.addObject("authorities", shareService.getUser()
					.getAuthorities());
			mav.addObject("user", shareService.getUser());
			Integer termId = -1;
			String courseId = "";
			Integer labId = -1;
			//学期查询
			if (request.getParameter("term") != null) {
				termId = Integer.parseInt(request.getParameter("term"));
			}
			if (termId == -1) {//当前学期
				termId = shareService.getBelongsSchoolTerm(
						Calendar.getInstance()).getId();
			}
			//课程查询
			if (request.getParameter("courseId") != null) {
				courseId = request.getParameter("courseId");
			}
			//实验室查询
			if (request.getParameter("labId") != null
					&& !request.getParameter("labId").equals("")) {
				labId = Integer.parseInt(request.getParameter("labId"));
			}
			//页面传参
			mav.addObject("termId", termId);
			mav.addObject("courseId", courseId);
			mav.addObject("labId", labId);
			// 登陆人权限获得
			mav.addObject("authorities", shareService.getUser()
					.getAuthorities());
			//根据学期找到该学期的最大最小周次，最大最小年份，最大最小月份
			List<Object[]> schoolTermWeeks = termDetailService
					.findViewSchoolTermWeek(termId);
			mav.addObject("schoolTermWeeks", schoolTermWeeks);
			//根据学期找到其下的所有特殊的schoolWeek
			mav.addObject("specialSchoolWeeks",
					termDetailService.findSpecialSchoolWeekByTerm(termId));
			//所有学期
			List<SchoolTerm> schoolTerms = outerApplicationService
					.getSchoolTermList();
			mav.addObject("schoolTerms", schoolTerms);
			Set<TimetableAppointment> appointments = new HashSet<TimetableAppointment>();
			mav.addObject("courses", newTimetableCourseSchedulingService
					.findMyScheduleListView(request.getSession(),
							new SchoolCourseDetail(), 1, -1));
			Set<SchoolCourseInfo> courseInfos = new HashSet<SchoolCourseInfo>();
			//
			Map<Integer, String> labRoomMap = new LinkedHashMap<Integer, String>(0);
			// 遍历实验分室
			User user=shareService.getUser();
			if(user.getLabCenter()!=null){
			Set<LabRoom> labRooms=user.getLabCenter().getLabRooms();
			for (LabRoom labRoom : labRooms) {
				labRoomMap.put(labRoom.getId(),labRoom.getLabRoomName()+"("+labRoom.getLabRoomAddress()+")");
			}}
		/*	if (courseId.equals("") && labId == -1) {
				//查询条件为空-默认
				if(user.getLabCenter()!=null){
				Set<LabRoom> labRooms=user.getLabCenter().getLabRooms();
				if(labRooms!=null){
				for(LabRoom l:labRooms){
					String sql = "select t from TimetableLabRelated t where 1=1";
					sql+=" and t.labRoom.id="+l.getId();
					List<TimetableLabRelated> tls = timetableLabRelatedDAO.executeQuery(sql, 0, -1);
					if(tls!=null){
					for(TimetableLabRelated tl:tls){
						
						if(tl.getTimetableAppointment().getSchoolCourseDetail() != null 
								&& tl.getTimetableAppointment().getSchoolCourseDetail().getSchoolTerm().getId().intValue() == termId.intValue() 
								&& tl.getTimetableAppointment().getStatus() == 1){
							appointments.add(tl.getTimetableAppointment());
							courseInfos.add(tl.getTimetableAppointment().getSchoolCourseDetail()
									.getSchoolCourse().getSchoolCourseInfo());
						}
					}}
				}
				}
				}
			}*/
				if (labId != -1) {//实验室查询
				String sql = "select t from TimetableLabRelated t where  t.labRoom.id ="
						+ labId;
				List<TimetableLabRelated> tlrs = timetableLabRelatedDAO
						.executeQuery(sql, 0, -1);

				//排课生成后的课程详情（周次、教师等）
				if (tlrs.size() > 0) {

					for (TimetableLabRelated ta : tlrs) {
						TimetableAppointment te = ta.getTimetableAppointment();
						// 不合班
						//24合班排课批次学生自选，25没用，26分组排课？？？
						if ((te.getTimetableStyle() != 25
								&& te.getTimetableStyle() != 24 && te
								.getTimetableStyle() != 26)
								&& te.getSchoolCourseDetail() != null
								&& te.getSchoolCourseDetail().getSchoolTerm()
										.getId().intValue() == termId//相同学期
										.intValue() && te.getStatus() == 1) {//status=1已发布
							if (courseId.equals("")) {//无课程查询条件
								appointments.add(te);
								courseInfos.add(te.getSchoolCourseDetail()
										.getSchoolCourse()
										.getSchoolCourseInfo());
							} else if (!courseId.equals("")
									&& te.getSchoolCourseDetail()
											.getSchoolCourse().getCourseNo()
											.equals(courseId)) {//相同课程
								appointments.add(te);
								courseInfos.add(te.getSchoolCourseDetail()
										.getSchoolCourse()
										.getSchoolCourseInfo());
							}
						}
						// 合班
						if ((te.getTimetableStyle() == 25
								|| te.getTimetableStyle() == 24 || te
								.getTimetableStyle() == 26)
								&& te.getSchoolCourseMerge() != null
								&& te.getSchoolCourseMerge().getTermId()
										.intValue() == termId.intValue()//同一学期
								&& te.getStatus() == 1) {//已发布的排课信息
							if (courseId.equals("")) {//
								appointments.add(te);
								Set<SchoolCourseDetail> courseDetails = te
										.getSchoolCourseMerge()
										.getSchoolCourseDetails();
								for (SchoolCourseDetail s : courseDetails) {
									courseInfos.add(s.getSchoolCourse()
											.getSchoolCourseInfo());
									break;
								}
							} else if (!courseId.equals("")
									&& te.getSchoolCourseMerge().getCourseNo()
											.equals(courseId)) {
								appointments.add(te);
								Set<SchoolCourseDetail> courseDetails = te
										.getSchoolCourseMerge()
										.getSchoolCourseDetails();
								for (SchoolCourseDetail s : courseDetails) {
									courseInfos.add(s.getSchoolCourse()
											.getSchoolCourseInfo());
									break;
								}
							}
						}
					}
				}
				mav.addObject("type", 3);

			} else if (!courseId.equals("")) {
				SchoolCourse schoolCourse = schoolCourseDAO
						.findSchoolCourseByCourseNo(courseId);//课程编号
				if(schoolCourse != null){//查询不合班课程
					for (SchoolCourseDetail schoolCourseDetail : schoolCourse
							.getSchoolCourseDetails()) {
						if (schoolCourseDetail != null
								&& schoolCourseDetail.getTimetableAppointments() != null) {
							courseInfos.add(schoolCourseDetail.getSchoolCourse()
									.getSchoolCourseInfo());
							for (TimetableAppointment te : schoolCourseDetail
									.getTimetableAppointments()) {
								
								if (te.getSchoolCourseDetail().getSchoolTerm()
										.getId().intValue() == termId.intValue()
										&& te.getStatus() == 1) {
									appointments.add(te);
								}
							}
						}
					}
				}
				else{//查询合班课程
					String sql = "select s from SchoolCourseMerge s where 1=1 and s.courseNo ='"
							+ courseId + "'";
					List<SchoolCourseMerge> merges = schoolCourseMergeDAO
							.executeQuery(sql, 0, -1);
					SchoolCourseMerge schoolCourseMerge = null;
					if (merges != null && merges.size() != 0) {
						//school_course&school_course_merge一对一
						schoolCourseMerge = merges.get(0);
					}
					//课程列表信息
					if (schoolCourseMerge != null) {
						for (SchoolCourseDetail s : schoolCourseMerge
								.getSchoolCourseDetails()) {
							courseInfos.add(s.getSchoolCourse()
									.getSchoolCourseInfo());
							break;
						}
					}
					
					if (schoolCourseMerge != null
							&& schoolCourseMerge.getTimetableAppointments() != null) {
	
						for (TimetableAppointment te : schoolCourseMerge
								.getTimetableAppointments()) {
							// 不合班
							if (te.getSchoolCourseMerge().getTermId().intValue() == termId
									.intValue() && te.getStatus() == 1) {
								appointments.add(te);
							}
						}
					}
				}
				mav.addObject("type", 2);
			}
			mav.addObject("appointments", appointments);
			mav.addObject("labRoomMap",labRoomMap);
			mav.addObject("courseInfos", courseInfos);
			mav.setViewName("newtimetable/myTimetableLabDirector.jsp");
		}
		if(request.getSession().getAttribute("authorityName")
				.equals("SUPERADMIN")){
			mav.setViewName("newtimetable/myTimetableAdmin.jsp");
		}
		if (request.getSession().getAttribute("authorityName")
				.equals("TEACHER")) {
			mav.setViewName("redirect:/newtimetable/myTimetableTeacher");
		}
		if (request.getSession().getAttribute("authorityName")
				.equals("STUDENT")) {
			mav.setViewName("redirect:/newtimetable/myTimetableStudent");
		}
		if (request.getSession().getAttribute("authorityName")
				.equals("COURSETEACHER")) {
			mav.setViewName("redirect:/newtimetable/myTimetableCourseTeacher");
		}
		return mav;
	}

	
	/************************************************************
	 * @description：我的课表 - 查看批次下的学生
	 * @author：郑昕茹
	 * @date：2017-04-17
	 ************************************************************/
	@RequestMapping("/viewTimetableGroupStudents")
	public ModelAndView viewTimetableGroupStudents(HttpServletRequest request,
			Integer groupId, String courseId, Integer termId, Integer labId,
			Integer currpage) {
		ModelAndView mav = new ModelAndView();

		// 登陆人权限获得
		mav.addObject("authorities", shareService.getUser().getAuthorities());
		mav.addObject("user", shareService.getUser());
		int pageSize = 20;
		List<TimetableGroupStudents> students = newTimetableCourseSchedulingService
				.findTimetableGroupStudentsByGroupId(groupId, currpage,
						pageSize, request);
		int totalRecords = newTimetableCourseSchedulingService
				.findTimetableGroupStudentsByGroupId(groupId, 1, -1, request)
				.size();
		TimetableGroup timetableGroup = timetableGroupDAO
				.findTimetableGroupById(groupId);
		Map<String, Integer> pageModel = shareService.getPage(currpage,
				pageSize, totalRecords);
		mav.addObject("students", students);
		mav.addObject("pageModel", pageModel);
		mav.addObject("currpage", currpage);
		mav.addObject("pageSize", pageSize);
		mav.addObject("totalRecords", totalRecords);

		List<TimetableGroupStudents> allStudents = newTimetableCourseSchedulingService
				.findTimetableGroupStudentsByGroupId(groupId, 1, -1, request);
		Set<SchoolAcademy> academys = new HashSet<SchoolAcademy>();
		Set<User> users = new HashSet<User>();
		Set<SchoolClasses> classes = new HashSet<SchoolClasses>();
		for (TimetableGroupStudents t : allStudents) {
			academys.add(t.getUser().getSchoolAcademy());
			users.add(t.getUser());
			classes.add(t.getUser().getSchoolClasses());
		}
		mav.addObject("academys", academys);
		mav.addObject("users", users);
		mav.addObject("classes", classes);

		mav.addObject("groupId", groupId);
		mav.addObject("courseId", courseId);
		mav.addObject("termId", termId);
		mav.addObject("labId", labId);
		mav.setViewName("newtimetable/viewTimetableGroupStudents.jsp");
		return mav;
	}

	/************************************************************
	 * @description：我的课表 - 查看批次下的学生
	 * @author：郑昕茹
	 * @date：2017-04-17
	 ************************************************************/
	@RequestMapping("/returnToTimetable")
	public ModelAndView returnToTimetable(Integer groupId, String courseId,
			Integer termId, Integer labId, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("courseId", courseId);
		mav.addObject("termId", termId);
		mav.addObject("labId", labId);
		mav.setViewName("redirect:/newtimetable/myTimetableAdmin");
		if (request.getSession().getAttribute("authorityName")
				.equals("SUPERADMIN")) {
			mav.setViewName("redirect:/newtimetable/myTimetableAdmin");
		}
		if (request.getSession().getAttribute("authorityName")
				.equals("TEACHER")) {
			mav.setViewName("redirect:/newtimetable/myTimetableTeacher");
		}
		if (request.getSession().getAttribute("authorityName")
				.equals("STUDENT")) {
			mav.setViewName("redirect:/newtimetable/myTimetableStudent");
		}
		if (request.getSession().getAttribute("authorityName")
				.equals("COURSETEACHER")) {
			mav.setViewName("redirect:/newtimetable/myTimetableCourseTeacher");
		}
		return mav;
	}

	/************************************************************
	 * @throws Exception
	 * @description：我的课表 - 批次下的学生
	 * @author：郑昕茹
	 * @date：2017-05-09
	 ************************************************************/
	@RequestMapping("/exportGroupStudents")
	public void exportGroupStudents(HttpServletRequest request,
			HttpServletResponse response, Integer groupId) throws Exception {
		int pageSize = 20;
		List<TimetableGroupStudents> students = newTimetableCourseSchedulingService
				.findTimetableGroupStudentsByGroupId(groupId, 1, -1, request);
		// 存储打印的内容
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		// 计数序号
		Integer count = 1;
		for (TimetableGroupStudents tgs : students) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("number", count.toString());// 序号
			map.put("username", tgs.getUser().getUsername());
			map.put("cname", tgs.getUser().getCname());
			map.put("className", tgs.getUser().getSchoolClasses()
					.getClassName());
			map.put("academy", tgs.getUser().getSchoolAcademy()
					.getAcademyName());
			list.add(map);
			count++;
		}
		String title = "学生名单";// 表头数组
		// 属性
		String[] headers = new String[] { "序号", "学号", "姓名", "班级", "学院" };
		String[] fields = new String[] { "number", "username", "cname",
				"className", "academy" };
		TableData td = ExcelUtils.createTableData(list,
				ExcelUtils.createTableHeader(headers), fields);
		JsGridReportBase report = new JsGridReportBase(request, response);
		report.exportToExcel(title, shareService.getUser().getCname(), td);
	}

	/**********************************************
	 * @throws Exception
	 * @description：word保存方法
	 * @author：郑昕茹
	 * @date: 2017-05-09
	 ***********************************************/
	@RequestMapping("/exportTimetable")
	public void exportTimetable(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		List<TimetableAppointment> appointments = new ArrayList<TimetableAppointment>();
		Set<SchoolCourseInfo> courseInfos = new HashSet<SchoolCourseInfo>();
		if (request.getSession().getAttribute("authorityName")
				.equals("SUPERADMIN")) {
			appointments = (List<TimetableAppointment>) newTimetableCourseSchedulingService
					.findTimetableAdmin(request).get("appointments");
			courseInfos = (Set<SchoolCourseInfo>) newTimetableCourseSchedulingService
					.findTimetableAdmin(request).get("courseInfos");
		}
		if (request.getSession().getAttribute("authorityName")
				.equals("TEACHER")) {
			appointments = (List<TimetableAppointment>) newTimetableCourseSchedulingService
					.findTimetableTeacher(request).get("appointments");
			courseInfos = (Set<SchoolCourseInfo>) newTimetableCourseSchedulingService
					.findTimetableTeacher(request).get("courseInfos");
		}
		if (request.getSession().getAttribute("authorityName")
				.equals("COURSETEACHER")) {
			appointments = (List<TimetableAppointment>) newTimetableCourseSchedulingService
					.findTimetableCourseTeacher(request).get("appointments");
			courseInfos = (Set<SchoolCourseInfo>) newTimetableCourseSchedulingService
					.findTimetableCourseTeacher(request).get("courseInfos");
		}
		if (request.getSession().getAttribute("authorityName")
				.equals("STUDENT")) {
			appointments = (List<TimetableAppointment>) newTimetableCourseSchedulingService
					.findTimetableStudent(request).get("appointments");
			courseInfos = (Set<SchoolCourseInfo>) newTimetableCourseSchedulingService
					.findTimetableStudent(request).get("courseInfos");
		}

		Map<String, Object> map = new HashMap<String, Object>();
		SchoolTerm schoolTerm = shareService.getBelongsSchoolTerm(Calendar
				.getInstance());
		List<Object[]> schoolTermWeeks = termDetailService
				.findViewSchoolTermWeek(schoolTerm.getId());
		List<SchoolWeek> specialSchoolWeeks = termDetailService
				.findSpecialSchoolWeekByTerm(schoolTerm.getId());
		// 存储打印的内容
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();

		List<Map<String, String>> list2 = new ArrayList<Map<String, String>>();// 课程信息
		for (SchoolCourseInfo s : courseInfos) {
			Map<String, String> mapp = new HashMap<String, String>();
			mapp.put("weekday", s.getCourseName());
			mapp.put("classes", s.getCourseNumber());
			if (s.getOperationOutlinesForClassId() != null
					&& s.getOperationOutlinesForClassId().size() != 0) {
				for (OperationOutline o : s.getOperationOutlinesForClassId()) {
					mapp.put("week1", o.getPeriod().toString());
					if (o.getCOperationOutlineCredit() != null) {
						mapp.put("week2", o.getCOperationOutlineCredit()
								.getCredit());
					} else {
						mapp.put("week2", "");
					}
					mapp.put("week3", o.getCourseDescription());
					mapp.put("week4", o.getAssResultsPerEvaluation());
					mapp.put("week5", "");
				}
			} else {
				mapp.put("week1", "");
				mapp.put("week2", "");
				mapp.put("week3", "");
				mapp.put("week4", "");
				mapp.put("week5", "");
			}
			for (int i = 0; i < schoolTermWeeks.size(); i++) {
				Integer week = (Integer) (schoolTermWeeks.get(i))[2];
				if (week > 5) {
					mapp.put("week" + week, "");
				}
			}
			list2.add(mapp);
		}
		// 计数序号
		String[] fields = new String[schoolTermWeeks.size() + 2];
		// 属性
		String[] headers = new String[schoolTermWeeks.size() + 2];
		for (TimetableAppointment ta : appointments) {
			String item = "";
			if (ta.getTimetableItemRelateds() != null) {
				for (TimetableItemRelated tir : ta.getTimetableItemRelateds()) {
					// item += tir.getOperationItem().getLpName();
					item += tir.getOperationItem().getLpCodeCustom();
					break;
				}
			}
			String group = "";
			for (TimetableGroup tg : ta.getTimetableGroups()) {
				group += tg.getGroupName();
			}
			String lab = "";
			if (ta.getTimetableLabRelateds() != null) {
				for (TimetableLabRelated tlr : ta.getTimetableLabRelateds()) {
					lab += tlr.getLabRoom().getLabRoomAddress();
					break;
				}
			}
			String changeTime = "";
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
			if (ta.getActualStartDate() != null) {
				changeTime += sdf.format(ta.getActualStartDate().getTime());
			}
			if (ta.getActualEndDate() != null) {
				changeTime += "-" + sdf.format(ta.getActualEndDate().getTime());
			}
			if (ta.getTimetableAppointmentSameNumbers() != null
					&& ta.getTimetableAppointmentSameNumbers().size() != 0) {
				for (TimetableAppointmentSameNumber tas : ta
						.getTimetableAppointmentSameNumbers()) {
					for (int i = tas.getStartWeek(); i <= tas.getEndWeek(); i++) {
						for (int j = (tas.getStartClass() + 1) / 2; j <= tas
								.getEndClass() / 2; j++) {
							if (map.get(i + "-" + ta.getWeekday() + "-" + j) != null) {
								map.put(i + "-" + ta.getWeekday() + "-" + j,
										map.get(i + "-" + ta.getWeekday() + "-"
												+ j)
												+ " "
												+ item
												+ "-"
												+ group
												+ lab + " " + changeTime);
							} else {
								map.put(i + "-" + ta.getWeekday() + "-" + j,
										item + "-" + group + lab + " "
												+ changeTime);
							}
						}
					}
				}
			} else {
				for (int i = ta.getStartWeek(); i <= ta.getEndWeek(); i++) {
					for (int j = (ta.getStartClass() + 1) / 2; j <= ta
							.getEndClass() / 2; j++) {
						if (map.get(i + "-" + ta.getWeekday() + "-" + j) != null) {
							map.put(i + "-" + ta.getWeekday() + "-" + j,
									map.get(i + "-" + ta.getWeekday() + "-" + j)
											+ " "
											+ item
											+ "-"
											+ group
											+ lab
											+ " " + changeTime);
						} else {
							map.put(i + "-" + ta.getWeekday() + "-" + j, item
									+ "-" + group + lab + " " + changeTime);
						}
					}
				}
			}
		}
		Map<String, String> map1 = new HashMap<String, String>();
		Map<String, String> map2 = new HashMap<String, String>();

		Map<String, String> map3 = new HashMap<String, String>();
		Map<String, String> map4 = new HashMap<String, String>();

		map1.put("weekday", "星期");
		map1.put("classes", "日期");
		map2.put("weekday", "课次");
		map2.put("classes", "周");

		map3.put("weekday", "");
		map3.put("classes", "");
		map4.put("weekday", "课程");
		map4.put("classes", "代号");
		map4.put("week1", "学时");
		map4.put("week2", "学分");
		map4.put("week3", "性质");
		map4.put("week4", "考核方式");
		map4.put("week5", "上课教室");
		for (int i = 0; i < schoolTermWeeks.size(); i++) {
			Integer week = (Integer) (schoolTermWeeks.get(i))[2];
			if (schoolTermWeeks.get(i)[6].equals((schoolTermWeeks.get(i)[7]))) {
				map1.put("week" + week, schoolTermWeeks.get(i)[8].toString()
						+ "|" + schoolTermWeeks.get(i)[9].toString());
			} else {
				map1.put("week" + week, schoolTermWeeks.get(i)[6].toString()
						+ "/" + schoolTermWeeks.get(i)[8].toString() + "|"
						+ schoolTermWeeks.get(i)[7].toString() + "/"
						+ schoolTermWeeks.get(i)[9].toString());
			}
			// <c:if
			// test="${currWeek1[6]==currWeek1[7]}">${currWeek1[8]}<br>|<br>${currWeek1[9]}</c:if>
			// <c:if
			// test="${currWeek1[6]!=currWeek1[7]}">${currWeek1[6]}/${currWeek1[8]}<br>|<br>${currWeek1[7]}/${currWeek1[9]}</c:if>
			map2.put("week" + week, week.toString());
			if (week > 5) {
				map4.put("week" + week, "");
			}
			map3.put("week" + week, "");
		}
		list.add(map2);
		list.add(map1);
		for (int j = 1; j <= 7; j++) {
			for (int k = 1; k <= 5; k++) {
				Map<String, String> mapp = new HashMap<String, String>();
				mapp.put("weekday", String.valueOf(j));
				mapp.put("classes", (2 * k - 1) + "-" + (2 * k));
				fields[0] = "weekday";
				fields[1] = "classes";
				headers[0] = "    ";
				headers[1] = "月";
				for (int i = 0; i < schoolTermWeeks.size(); i++) {

					Integer week = (Integer) (schoolTermWeeks.get(i))[2];
					Integer month = (Integer) (schoolTermWeeks.get(i))[6];
					fields[i + 2] = "week" + week.toString();
					headers[i + 2] = month.toString() + "月    ";
					if (map.get(week + "-" + j + "-" + k) != null) {
						mapp.put("week" + week,
								map.get(week + "-" + j + "-" + k).toString());
					} else {
						mapp.put("week" + week, "");
					}
				}
				list.add(mapp);
			}
		}
		list.add(map3);
		list.add(map4);

		list.addAll(list2);
		String title = "课表";// 表头数组

		TableData td = ExcelUtils.createTableData(list,
				ExcelUtils.createTableHeader(headers), fields);
		JsGridReportBase report = new JsGridReportBase(request, response);
		report.exportToExcel(title, shareService.getUser().getCname(), td);
	}
	
	/************************************************************
	 * @description：我的课表（教师查看全部）
	 * @author：陈乐为
	 * @date：2017-10-26
	 ************************************************************/
	@RequestMapping("/myTimetableTeacherAll")
	public ModelAndView myTimetableTeacherAll(HttpServletRequest request) {

		ModelAndView mav = new ModelAndView();
		// 登陆人权限获得
		mav.addObject("authorities", shareService.getUser()
				.getAuthorities());
		mav.addObject("user", shareService.getUser());
		
		Integer termId = -1;
		String courseId = "";
		String academy = "";
		if (request.getParameter("term") != null) {
			termId = Integer.parseInt(request.getParameter("term"));
		}
		if (termId == -1) {
			termId = shareService.getBelongsSchoolTerm(
					Calendar.getInstance()).getId();
		}
		if (request.getParameter("courseId") != null) {
			courseId = request.getParameter("courseId");
		}
		if (request.getParameter("academy") != null) {
			academy = request.getParameter("academy");
		}
		mav.addObject("termId", termId);
		mav.addObject("courseId", courseId);
		List<Object[]> schoolTermWeeks = termDetailService
				.findViewSchoolTermWeek(termId);
		mav.addObject("schoolTermWeeks", schoolTermWeeks);
		mav.addObject("specialSchoolWeeks",
				termDetailService.findSpecialSchoolWeekByTerm(termId));
		
		List<SchoolTerm> schoolTerms = outerApplicationService.getSchoolTermList();
		mav.addObject("schoolTerms", schoolTerms);
		Set<TimetableAppointment> appointments = new HashSet<TimetableAppointment>();
		Map<String, String> courses1 = new HashMap<String, String>();
		Set<String> academys = new HashSet<String>();
		Set<SchoolCourseInfo> courseInfos = new HashSet<SchoolCourseInfo>();
		
		SchoolCourse schoolCourse = schoolCourseDAO.findSchoolCourseByCourseNo(courseId);//课程编号
		if(schoolCourse != null){//不合班
			Set<SchoolCourseDetail> schoolCourseDetails = schoolCourse.getSchoolCourseDetails();
			for(SchoolCourseDetail schoolCourseDetail : schoolCourseDetails){
				//筛选同一学期的实验课程
				if(schoolCourseDetail.getSchoolTerm().getId().intValue() == termId.intValue() 
						&& schoolCourseDetail.getTimetableAppointments() != null
						&& schoolCourseDetail.getWeekday() == null){
					//不合班的课程安排
					for(TimetableAppointment te : schoolCourseDetail.getTimetableAppointments()){
						if(te.getStatus() == 1){
							//同时-学院查询
							if(!academy.equals("") 
									&& te.getSchoolCourse().getSchoolCourseInfo().getAcademyNumber().equals(academy)){
								appointments.add(te);
								//课程列表-判断是否存在
								if(!courseInfos.contains(te.getSchoolCourse().getSchoolCourseInfo())){
									courseInfos.add(te.getSchoolCourse().getSchoolCourseInfo());
								}
								//学院
								if(!academys.contains(te.getSchoolCourse().getSchoolCourseInfo().getAcademyNumber())){
									academys.add(te.getSchoolCourse().getSchoolCourseInfo().getAcademyNumber());
								}
							}else{//无-学院查询
								appointments.add(te);
								//课程列表-判断是否存在
								if(te.getSchoolCourse() != null 
										&& !courseInfos.contains(te.getSchoolCourse().getSchoolCourseInfo())){
									courseInfos.add(te.getSchoolCourse().getSchoolCourseInfo());
								}
								//学院
								if(te.getSchoolCourse() != null
										&& !academys.contains(te.getSchoolCourse().getSchoolCourseInfo().getAcademyNumber())){
									academys.add(te.getSchoolCourse().getSchoolCourseInfo().getAcademyNumber());
								}
							}
						}
					}
				}
			}
		}else{
			SchoolCourseMerge schoolCourseMergeStr = null;
			Set<SchoolCourseMerge> schoolCourseMerges = schoolCourseMergeDAO.findSchoolCourseMergeByCourseNo(courseId);
			if(schoolCourseMerges != null){
				for(SchoolCourseMerge schoolCourseMerge : schoolCourseMerges){
					schoolCourseMergeStr = schoolCourseMerge;
					break;
				}
			}
			if(schoolCourseMergeStr != null){
				Set<SchoolCourseDetail> schoolCourseDetails = schoolCourseMergeStr.getSchoolCourseDetails();
				if(schoolCourseDetails != null){
					for(SchoolCourseDetail schoolCourseDetail : schoolCourseDetails){
						if(schoolCourseDetail.getWeekday() == null){
							for(TimetableAppointment te : schoolCourseDetail.getTimetableAppointments()){
								if(te.getStatus() == 1){
									//同时-学院查询
									if(!academy.equals("") 
											&& te.getSchoolCourse().getSchoolCourseInfo().getAcademyNumber().equals(academy)){
										appointments.add(te);
										//课程列表-判断是否存在
										if(!courseInfos.contains(te.getSchoolCourse().getSchoolCourseInfo())){
											courseInfos.add(te.getSchoolCourse().getSchoolCourseInfo());
										}
										//学院
										if(!academys.contains(te.getSchoolCourse().getSchoolCourseInfo().getAcademyNumber())){
											academys.add(te.getSchoolCourse().getSchoolCourseInfo().getAcademyNumber());
										}
									}else{//无-学院查询
										appointments.add(te);
										//课程列表-判断是否存在
										if(te.getSchoolCourse() != null 
												&& !courseInfos.contains(te.getSchoolCourse().getSchoolCourseInfo())){
											courseInfos.add(te.getSchoolCourse().getSchoolCourseInfo());
										}
										//学院
										if(te.getSchoolCourse() != null
												&& !academys.contains(te.getSchoolCourse().getSchoolCourseInfo().getAcademyNumber())){
											academys.add(te.getSchoolCourse().getSchoolCourseInfo().getAcademyNumber());
										}
									}
								}
							}
						}
					}
				}
			}
		}
		
		String hql ="select u.schoolCourseDetails2 from User u where 1=1 and u.username ='" + shareService.getUser().getUsername() +"'";
		List<SchoolCourseDetail> courses = schoolCourseDetailDAO
				.executeQuery(hql, 0, -1);
		mav.addObject("appointments", appointments);
		//课程查询选框
		mav.addObject("courses", courses);
		Map<String, String> mapAcademy = new HashMap<String, String>();
		for (String academyNumber : academys) {
			SchoolAcademy schoolAcademy = schoolAcademyDAO
					.findSchoolAcademyByAcademyNumber(academyNumber);
			mapAcademy.put(schoolAcademy.getAcademyNumber(),
					schoolAcademy.getAcademyName());
		}
		mav.addObject("academys", academys);
		mav.addObject("mapAcademy", mapAcademy);
		mav.addObject("user", shareService.getUser());
		
		mav.addObject("courseInfos", courseInfos);//下方课程列表
		mav.setViewName("newtimetable/myTimetableTeacherAll.jsp");

		return mav;
	}

}
