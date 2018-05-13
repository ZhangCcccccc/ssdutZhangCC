/**
 * 
 */
package net.xidlims.web.newtimetable;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import net.luxunsh.util.EmptyUtil;
import net.xidlims.constant.ListPageUtil;
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
import net.xidlims.domain.LabRoomCourseCapacity;
import net.xidlims.domain.Message;
import net.xidlims.domain.OperationItem;
import net.xidlims.domain.OperationOutline;
import net.xidlims.domain.SchoolAcademy;
import net.xidlims.domain.SchoolClasses;
import net.xidlims.domain.SchoolCourse;
import net.xidlims.domain.SchoolCourseDetail;
import net.xidlims.domain.SchoolCourseMerge;
import net.xidlims.domain.SchoolCourseStudent;
import net.xidlims.domain.SchoolWeek;
import net.xidlims.domain.SystemTime;
import net.xidlims.domain.TimetableAppointment;
import net.xidlims.domain.TimetableAppointmentSameNumber;
import net.xidlims.domain.TimetableBatch;
import net.xidlims.domain.TimetableBatchStudent;
import net.xidlims.domain.TimetableGroup;
import net.xidlims.domain.TimetableGroupStudents;
import net.xidlims.domain.TimetableTeacherRelated;
import net.xidlims.domain.User;
import net.xidlims.service.ConvertUtil;
import net.xidlims.service.common.ShareService;
import net.xidlims.service.newoperation.NewOperationService;
import net.xidlims.service.newtimetable.NewTimetableCourseSchedulingService;
import net.xidlims.service.newtimetable.NewTimetableShareService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * @author 贺子龙
 * 
 */
@Controller("SchoolCourseAjaxController")
@SessionAttributes("selected_labCenter")
@RequestMapping("/newtimetable")
public class SchoolCourseAjaxController {

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
	@Autowired
	private NewTimetableShareService newTimetableShareService;

	/*********************************************************************************
	 * description： 批量设置排课教师 author：郑昕茹 date：2017-04-14
	 *********************************************************************************/
	@RequestMapping("/setSchedulePermission")
	public @ResponseBody
	String setSchedulePermission(String[] detailNos, String teacher) {
		Integer count = 0;
		User user = userDAO.findUserByPrimaryKey(teacher);
		for (String detailNo : detailNos) {
			SchoolCourseDetail courseDetail = schoolCourseDetailDAO
					.findSchoolCourseDetailByCourseDetailNo(detailNo);
			courseDetail.setUserByScheduleTeacher(user);
			schoolCourseDetailDAO.store(courseDetail);
			count++;
		}
		return "success";
	}

	/*********************************************************************************
	 * description： 批量设置排课教师 author：郑昕茹 date：2017-04-14
	 *********************************************************************************/
	@RequestMapping("/setSchedulePermissions")
	public @ResponseBody
	String setSchedulePermissions(String[] detailNos, String[] teacher) {
		Integer count = 0;
		Set<User> users = new HashSet<User>();
		for (String t : teacher) {
			User user = userDAO.findUserByPrimaryKey(t);
			users.add(user);
		}
		for (String detailNo : detailNos) {
			SchoolCourseDetail courseDetail = schoolCourseDetailDAO
					.findSchoolCourseDetailByCourseDetailNo(detailNo);
			courseDetail.setUserByScheduleTeachers(users);
			schoolCourseDetailDAO.store(courseDetail);
			count++;
		}
		return "success";
	}

	/*********************************************************************************
	 * description： 设置排课教师 author：郑昕茹 date：2017-04-19
	 *********************************************************************************/
	@RequestMapping("/changeScheduleTeacher")
	public @ResponseBody
	String changeScheduleTeacher(String detailNo, String teacher) {
		SchoolCourseDetail courseDetail = schoolCourseDetailDAO
				.findSchoolCourseDetailByCourseDetailNo(detailNo);
		User user = userDAO.findUserByPrimaryKey(teacher);
		courseDetail.setUserByScheduleTeacher(user);
		schoolCourseDetailDAO.store(courseDetail);
		return "success";
	}

	/**
	 * Description 学期课程联动（学生）
	 * 
	 * @author 陈乐为
	 * @param request
	 * @param termId
	 * @return
	 * @date 2017-9-22
	 */
	@RequestMapping("/getCourseByTermStudent")
	public @ResponseBody
	Map<String, String> getCourseByTermStudent(HttpServletRequest request,
			int termId) {
		Map<String, String> courses = new HashMap<String, String>();
		String courseNo = null;
		StringBuffer options = new StringBuffer();

		String sql = "select t from TimetableGroupStudents t where  t.user.username like '"
				+ shareService.getUserDetail().getUsername() + "' ";
		List<TimetableGroupStudents> tass = timetableGroupStudentsDAO
				.executeQuery(sql, 0, -1);
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
							&& te.getStatus() == 1) {
						if (te.getSchoolCourseDetail().getSchoolCourse()
								.getCourseNo() != courseNo) {
							options.append("<option value='"
									+ te.getSchoolCourseDetail()
											.getSchoolCourse().getCourseNo()
									+ "'>"
									+ te.getSchoolCourseDetail()
											.getCourseName()
									+ te.getSchoolCourseDetail()
											.getSchoolCourse().getCourseNo()
									+ "</option>");
						}
						courseNo = te.getSchoolCourseDetail().getSchoolCourse()
								.getCourseNo();
					}
					// 合班
					if ((te.getTimetableStyle() == 25
							|| te.getTimetableStyle() == 24 || te
							.getTimetableStyle() == 26)
							&& te.getSchoolCourseMerge() != null
							&& te.getSchoolCourseMerge().getTermId().intValue() == termId
							&& te.getStatus() == 1) {
						if (te.getSchoolCourseMerge().getCourseNo() != courseNo) {
							options.append("<option value='"
									+ te.getSchoolCourseMerge().getCourseNo()
									+ "'>"
									+ te.getSchoolCourseMerge().getCourseName()
									+ te.getSchoolCourseMerge().getCourseNo()
									+ "</option>");
						}
					}
				}
			}
		}

		courses.put("courses", options.toString());
		return courses;
	}

	/**
	 * Description 学期课程联动
	 * 
	 * @author 陈乐为
	 * @param request
	 * @param termId
	 * @return
	 * @date 2017-9-22
	 */
	@RequestMapping("/getCourseByTerm")
	public @ResponseBody
	Map<String, String> getCourseByTerm(HttpServletRequest request, int termId) {
		Map<String, String> courses = new HashMap<String, String>();
		StringBuffer options = new StringBuffer();
		/*// 非教务
		String sql = "select t from TimetableTeacherRelated t where  t.user.username like '"
				+ shareService.getUserDetail().getUsername() + "' ";
		List<TimetableTeacherRelated> ttrs = timetableTeacherRelatedDAO
				.executeQuery(sql, 0, -1);

		StringBuffer options = new StringBuffer();
		String courseNo = null;
		if (ttrs.size() > 0) {
			for (TimetableTeacherRelated ta : ttrs) {
				TimetableAppointment te = ta.getTimetableAppointment();

				if (te.getTimetableStyle() != 25
						&& te.getTimetableStyle() != 24
						&& te.getTimetableStyle() != 26) {
					if (te.getSchoolCourseDetail() != null 
							&& te.getSchoolCourseDetail().getSchoolTerm().getId() == termId) {
						if (te.getSchoolCourseDetail().getSchoolCourse()
								.getCourseNo() != courseNo) {// 去重
							options.append("<option value='"
									+ te.getSchoolCourseDetail()
											.getSchoolCourse().getCourseNo()
									+ "'>"
									+ te.getSchoolCourseDetail()
											.getCourseName()
									+ te.getSchoolCourseDetail()
											.getSchoolCourse().getCourseNo()
									+ "</option>");
						}
						courseNo = te.getSchoolCourseDetail().getSchoolCourse()
								.getCourseNo();
					}
				} else {
					if (te.getSchoolCourseMerge() != null
							&& te.getSchoolCourseMerge().getTermId() == termId) {
						if (te.getSchoolCourseMerge().getCourseNo() != courseNo) {// 去重
							options.append("<option value='"
									+ te.getSchoolCourseMerge().getCourseNo()
									+ "'>"
									+ te.getSchoolCourseMerge().getCourseName()
									+ te.getSchoolCourseMerge().getCourseNo()
									+ "</option>");
						}
						courseNo = te.getSchoolCourseMerge().getCourseNo();
					}
				}
			}
		}*/
		String hql ="select u.schoolCourseDetails3 from User u where 1=1 and u.username ='" + shareService.getUser().getUsername() +"'";
		List<SchoolCourseDetail> course = schoolCourseDetailDAO
				.executeQuery(hql, 0, -1);
		for(SchoolCourseDetail s : course){
			if(s.getSchoolTerm().getId() == termId){
				options.append("<option value='"
						+ s.getSchoolCourse().getCourseNo()
						+ "'>"
						+ s.getCourseName()
						+ s.getSchoolCourse().getCourseNo()
						+ "</option>");
			}
		}
		courses.put("courses", options.toString());
		return courses;
	}

	/**
	 * Description 学期课程联动（课程负责教师）
	 * 
	 * @author 陈乐为
	 * @param request
	 * @param termId
	 * @return
	 * @date 2017-9-22
	 */
	@RequestMapping("/getCourseByTermTeacher")
	public @ResponseBody
	Map<String, String> getCourseByTermTeacher(HttpServletRequest request,
			int termId) {
		Map<String, String> courses = new HashMap<String, String>();
		StringBuffer options = new StringBuffer();
		Set<SchoolCourseMerge> merges = new HashSet<SchoolCourseMerge>();
		String courseNo = null;

		/*for (SchoolCourseDetail s : shareService.getUser()
				.getSchoolCourseDetails()) {
			if (s.getSchoolCourseMerge() == null) {
				if (s.getSchoolCourse().getCourseNo() != courseNo && s.getSchoolCourse().getSchoolTerm().getId() == termId) {
					options.append("<option value='"
							+ s.getSchoolCourse().getCourseNo() + "'>"
							+ s.getCourseName()
							+ s.getSchoolCourse().getCourseNo() + "</option>");
				}
				courseNo = s.getSchoolCourse().getCourseNo();
			} else {
				merges.add(s.getSchoolCourseMerge());
			}
		}
		if(!merges.isEmpty()){
			for (SchoolCourseMerge s : merges) {
				if (s.getTermId() == termId && s.getCourseNo() != courseNo) {
					options.append("<option value='" + s.getCourseNo() + "'>"
							+ s.getCourseName() + s.getCourseNo() + "</option>");
				}
				courseNo = s.getCourseNo();
			}
		}*/
		String hql ="select u.schoolCourseDetails2 from User u where 1=1 and u.username ='" + shareService.getUser().getUsername() +"'";
		List<SchoolCourseDetail> course = schoolCourseDetailDAO
				.executeQuery(hql, 0, -1);
		String hql1 ="select u.schoolCourseDetails3 from User u where 1=1 and u.username ='" + shareService.getUser().getUsername() +"'";
		List<SchoolCourseDetail> course1 = schoolCourseDetailDAO
				.executeQuery(hql1, 0, -1);
		for(SchoolCourseDetail s1 : course1){
			course.remove(s1);
		}
		for(SchoolCourseDetail s : course){
			if(s.getSchoolTerm().getId() == termId){
				options.append("<option value='"
						+ s.getSchoolCourse().getCourseNo()
						+ "'>"
						+ s.getCourseName()
						+ s.getSchoolCourse().getCourseNo()
						+ "</option>");
			}
		}
		courses.put("courses", options.toString());
		return courses;
	}

	/********************************************************************************
	 * Description: 欧亚排课-排课一级页面{右侧：保存分组排课}
	 * 
	 * @param isAdmin
	 *            0-->教务分组 1-->自主分组
	 * @author: 贺子龙
	 * @date: 2016-08-31
	 *********************************************************************************/
	@ResponseBody
	@RequestMapping("saveGroupTimetable")
	public String saveGroupTimetable(@RequestParam int term, String classes,
			String labRooms, int weekday, String items, String weeks,
			String teachers, String courseNo, int groupId, Integer isAdmin) {
		// 将字符串转化为int型的数组
		int[] classArray = ConvertUtil.stringToIntArray(classes);
		int[] labRoomArray = ConvertUtil.stringToIntArray(labRooms);
		int[] itemArray = ConvertUtil.stringToIntArray(items);
		int[] weekArray = ConvertUtil.stringToIntArray(weeks);
		TimetableAppointment appointment = timetableAppointmentSaveService
				.saveGroupTimetable(term, classArray, labRoomArray, weekArray,
						weekday, itemArray, teachers, courseNo, groupId,
						isAdmin);
		if (appointment.getId() != null) {// 保存成功
			return "success";
		} else {
			return "error";
		}
	}

	/********************************************************************************
	 * Description: 保存公选课排课结果
	 * 
	 * @author: 郑昕茹
	 * @throws ParseException
	 * @date: 2017-04-19
	 *********************************************************************************/
	@ResponseBody
	@RequestMapping("/deleteAppointment")
	public String deleteAppointment(@RequestParam Integer appointmentId)
			throws ParseException {
		TimetableAppointment timetableAppointment = timetableAppointmentDAO
				.findTimetableAppointmentById(appointmentId);
		if (timetableAppointment.getTimetableGroups() != null) {
			for (TimetableGroup t : timetableAppointment.getTimetableGroups()) {
				timetableGroupDAO.remove(t);
			}
		}
		timetableAppointment.setTimetableGroups(null);
		timetableAppointmentDAO.remove(timetableAppointment);
		return "success";
	}

	/********************************************************************************
	 * Description: 删除基础课排课结果
	 * 
	 * @author: 郑昕茹
	 * @throws ParseException
	 * @date: 2017-04-19
	 *********************************************************************************/
	@ResponseBody
	@RequestMapping("/deleteSpecializedBasicCourseAppointment")
	public String deleteSpecializedBasicCourseAppointment(
			@RequestParam Integer appointmentId) throws ParseException {
		TimetableAppointment timetableAppointment = timetableAppointmentDAO
				.findTimetableAppointmentById(appointmentId);
		timetableAppointmentDAO.remove(timetableAppointment);
		return "success";
	}

	/********************************************************************************
	 * Description: 保存基础课排课结果
	 * 
	 * @author: 郑昕茹
	 * @throws ParseException
	 * @date: 2017-04-20
	 *********************************************************************************/
	@ResponseBody
	@RequestMapping("/saveSpecializedBasicCourseTimetable")
	public String saveSpecializedBasicCourseTimetable(@RequestParam int term,
			Integer[] weeks, Integer[] weekdays, Integer[] classes,
			Integer[] groups, Integer labRoom, String courseDetailNo,
			Integer item, String[] teachers, String basicCourseStart,
			String basicCourseEnd) throws ParseException {
		// 保存课程的选课信息
		SchoolCourseDetail schoolCourseDetail = schoolCourseDetailDAO
				.findSchoolCourseDetailByCourseDetailNo(courseDetailNo);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		schoolCourseDetailDAO.store(schoolCourseDetail);
		LabRoom lab = labRoomDAO.findLabRoomById(labRoom);
		Map<Integer, Integer> groupIds = new HashMap<Integer, Integer>();

		TimetableBatch timetableBatch = newTimetableCourseSchedulingService
				.findTimetableBatchByCourseDetailNoAndType(courseDetailNo, 21).get(0);
		if (basicCourseStart != null && !basicCourseStart.equals("")) {
			Calendar startTime = Calendar.getInstance();
			startTime.setTime(sdf.parse(basicCourseStart));
			timetableBatch.setStartDate(startTime);
		}
		if (basicCourseEnd != null && !basicCourseEnd.equals("")) {
			Calendar endTime = Calendar.getInstance();
			endTime.setTime(sdf.parse(basicCourseEnd));
			timetableBatch.setEndDate(endTime);
		}
		timetableBatch = timetableBatchDAO.store(timetableBatch);
		// 获得排课组数和每组人数
		return "-1";
	}

	/********************************************************************************
	 * Description: 保存基础课排课结果（单条）
	 * 
	 * @author: 郑昕茹
	 * @throws ParseException
	 * @date: 2017-04-24
	 *********************************************************************************/
	@ResponseBody
	@RequestMapping("/saveSingleSpecializedBasicCourseTimetable")
	public Map<String, Object> saveSingleSpecializedBasicCourseTimetable(
			@RequestParam int term, Integer week, Integer weekday,
			Integer selectClass, Integer group, Integer labRoom,
			String courseDetailNo, Integer item, String teacher)
			throws ParseException {
		Map<String, Object> mapResult = new HashMap<String, Object>();
		// 保存课程的选课信息
		SchoolCourseDetail schoolCourseDetail = schoolCourseDetailDAO
				.findSchoolCourseDetailByCourseDetailNo(courseDetailNo);
		Set<User> teachers = schoolCourseDetail.getUsers();
		teachers.addAll(schoolCourseDetail.getUserByScheduleTeachers());
		teachers.add(schoolCourseDetail.getUser());

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		schoolCourseDetailDAO.store(schoolCourseDetail);
		LabRoom lab = labRoomDAO.findLabRoomById(labRoom);
		Map<Integer, Integer> groupIds = new HashMap<Integer, Integer>();
		Integer count = 0;
		// 选择的一节相当于两节
		Integer startClass = selectClass * 2 - 1;
		Integer endClass = selectClass * 2;
		// 获取所有节次
		List<SystemTime> times = timeDetailService.findAllTimes(1, -1, "S");
		Map<String, Calendar> time = new HashMap<String, Calendar>();
		Map<String, Calendar> timeEnd = new HashMap<String, Calendar>();
		for (SystemTime t : times) {
			time.put(t.getSectionName(), t.getStartDate());
			timeEnd.put(t.getSectionName(), t.getEndDate());
		}
		Map<String, Object> map = timetableAppointmentSaveService
				.saveSpecializedBasicCourseTimetable(
						term,
						ConvertUtil.stringToIntArray(startClass.toString()
								+ "," + endClass.toString()),
						ConvertUtil.stringToIntArray(labRoom.toString()),
						ConvertUtil.stringToIntArray(week.toString()), weekday,
						courseDetailNo,
						ConvertUtil.stringToIntArray(item.toString()), teacher,
						group);
		TimetableAppointment appointment = (TimetableAppointment) map
				.get("appointment");
		appointment = timetableAppointmentDAO
				.findTimetableAppointmentById(appointment.getId());
		Integer oldAppointmentId = (Integer) map.get("oldAppointmentId");
		mapResult.put("oldAppointmentId", oldAppointmentId);

		if (appointment.getId() != null) {
			String classes = appointment.getStartClass() + "-"
					+ appointment.getEndClass();
			Integer start = appointment.getStartClass();
			Integer end = appointment.getEndClass();
			if (timetableAppointmentSaveService.findSameNumbersByAppointmentId(
					appointment.getId()).size() != 0) {
				classes = "";
				for (TimetableAppointmentSameNumber tas : timetableAppointmentSaveService
						.findSameNumbersByAppointmentId(appointment.getId())) {
					if (tas.getStartClass() < start) {
						start = tas.getStartClass();
					}
					if (tas.getEndClass() > end) {
						end = tas.getEndClass();
					}
					classes += tas.getStartClass() + "-" + tas.getEndClass()
							+ ",";
				}
			}
			Calendar calStart = Calendar.getInstance();
			Calendar calEnd = Calendar.getInstance();
			if (start >= 9) {
				calStart = time.get("晚");
			} else {
				calStart = time.get(start.toString());
			}
			if (end >= 9) {
				calEnd = timeEnd.get("晚");
			} else {
				calEnd = timeEnd.get(end.toString());
			}
			appointment.setActualStartDate(calStart);
			appointment.setActualEndDate(calEnd);
			timetableAppointmentDAO.store(appointment);

			String s = "<select multiple='true' class='chzn-select' id='teacher" + appointment.getId()
					+ "' onchange='saveTeacher(" + appointment.getId()
					+ ")'><option value=''>请选择</option>";
			for (User u : teachers) {
				if (u.getUsername().equals(
						schoolCourseDetail.getUser().getUsername())) {
					s += "<option value='" + u.getUsername() + "' selected>"
							+ u.getCname() + "</option>";
				} else {
					s += "<option value='" + u.getUsername() + "'>"
							+ u.getCname() + "</option>";
				}
			}
			s += "</select>";
			mapResult.put("teacher", shareService.htmlEncode(s));
			SimpleDateFormat sdfNew = new SimpleDateFormat("HH:mm");

			String actualStartDate = "<input style='width:42%;height:25px;float:left;margin:0 3%;' id='actualStartDate"
					+ appointment.getId()
					+ "'  class='Wdate datepicker' value='"
					+ sdfNew.format(calStart.getTime())
					+ "' onfocus=\"WdatePicker({dateFmt:'HH:mm',skin:'whyGreen'})\" type='text' name='date'  onchange='saveActualStartDate("
					+ group + "," + appointment.getId() + ")'  readonly />";
			String actualEndDate = "<input style='width:42%;height:25px;float:left;margin:0 3%;' id='actualEndDate"
					+ appointment.getId()
					+ "'  class='Wdate datepicker' value='"
					+ sdfNew.format(calEnd.getTime())
					+ "' onfocus=\"WdatePicker({dateFmt:'HH:mm',skin:'whyGreen'})\" type='text' name='date'  onchange='saveActualEndDate("
					+ group + "," + appointment.getId() + ")'  readonly />";
			mapResult.put("actualStartDate",
					shareService.htmlEncode(actualStartDate));
			mapResult.put("actualEndDate",
					shareService.htmlEncode(actualEndDate));
			String close = "<a class='fa fa-times r close' title='关闭' id='close"
					+ appointment.getId()
					+ "' onclick='deleteSpecializedBasicCourseAppointment("
					+ appointment.getId() + ")'></a>";
			mapResult.put("close", shareService.htmlEncode(close));
			mapResult.put("classes", classes);
		}
		if (appointment.getId() != null) {
			mapResult.put("result", appointment.getId());
		} else {
			mapResult.put("result", -1);
		}
		return mapResult;
	}

	/************************************************************
	 * @description：根据组号生成学生名单
	 * @author：郑昕茹
	 * @date：2017-04-24
	 ************************************************************/
	@RequestMapping("/setTimetableGroupStudentsByGroupId")
	public @ResponseBody
	String setTimetableGroupStudentsByGroupId(@RequestParam Integer groupId) {
		TimetableGroup timetableGroup = timetableGroupDAO
				.findTimetableGroupById(groupId);
		if (timetableGroup.getTimetableGroupStudentses() == null
				|| (timetableGroup.getTimetableGroupStudentses() != null && timetableGroup
						.getTimetableGroupStudentses().size() != timetableGroup
						.getNumbers())) {
			TimetableBatch timetableBatch = timetableGroup.getTimetableBatch();
			List<String> allCoursesStudentUsernames = new ArrayList<String>();
			if (timetableGroup.getTimetableStyle() == 25) {
				allCoursesStudentUsernames = newTimetableCourseSchedulingService
						.findUsersNameByMergeId(timetableBatch.getCourseCode()
								.toString(), null);
			} else {
				allCoursesStudentUsernames = newTimetableCourseSchedulingService
						.findUsernamesByCourseDetailNo(timetableBatch
								.getCourseCode().toString());
			}
			for (TimetableGroup t : newTimetableCourseSchedulingService
					.findTimetableGroupsByBacthId(timetableBatch.getId())) {
				allCoursesStudentUsernames
						.removeAll(newTimetableCourseSchedulingService
								.findUsernamesByGroupId(t.getId()));
			}

			// 找到学生信息
			List<SchoolCourseStudent> student = new ArrayList<SchoolCourseStudent>();
			for (String studentusername : allCoursesStudentUsernames) {
				student.addAll(schoolCourseService
						.findSchoolCourseStudentByStudentNumber(studentusername));
			}
			// 根据班级优先排序其次是学号
			Collections.sort(student, new Comparator<SchoolCourseStudent>() {
				/*
				 * @Override public int compare(Object) public int
				 * compare(String o1, String o2) { return o1.compareTo(o2); }
				 */
				@Override
				public int compare(SchoolCourseStudent o1,
						SchoolCourseStudent o2) {
					int flag = o1.getSchoolClasses().getClassNumber()
							.compareTo(o2.getSchoolClasses().getClassNumber());
					if (flag == 0) {
						return o1
								.getUserByStudentNumber()
								.getUsername()
								.compareTo(
										o2.getUserByStudentNumber()
												.getUsername());
					}
					return flag;
				}
			});

			Iterator<String> iterator = allCoursesStudentUsernames.iterator();
			for (int j = 1; j <= timetableGroup.getNumbers(); j++) {
				TimetableGroupStudents timetableGroupStudents = new TimetableGroupStudents();
				timetableGroupStudents.setUser(userDAO
						.findUserByPrimaryKey(iterator.next()));
				timetableGroupStudents.setTimetableGroup(timetableGroup);
				timetableGroupStudentsDAO.store(timetableGroupStudents);
				timetableGroupStudentsDAO.flush();
			}
		}
		return "success";
	}

	/************************************************************
	 * @description：根据组号找到学生名单
	 * @author：郑昕茹
	 * @date：2017-04-24
	 ************************************************************/
	@RequestMapping("/findTimetableGroupStudentsByGroupId")
	public @ResponseBody
	Map<String, Object> findTimetableGroupStudentsByGroupId(
			@RequestParam Integer groupId, int page, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		int pageSize = 20;

		TimetableGroup timetableGroup = timetableGroupDAO
				.findTimetableGroupById(groupId);
		List<TimetableGroupStudents> students = newTimetableCourseSchedulingService
				.findTimetableGroupStudentsByGroupId(groupId, page, pageSize,
						request);
		/*
		 * Collections.sort(students,new Comparator<TimetableGroupStudents>() {
		 * 
		 * @Override public int compare(TimetableGroupStudents o1,
		 * TimetableGroupStudents o2) { return
		 * o1.getUser().getUsername().compareTo(o2.getUser().getUsername()); }
		 * });
		 */
		// -1的时候不做班级筛选，所以这个地方pageSize用了-2
		int totalRecords = newTimetableCourseSchedulingService
				.findTimetableGroupStudentsByGroupId(groupId, 1, -2, request)
				.size();
		Map<String, Integer> pageModel = shareService.getPage(page, pageSize,
				totalRecords);
		String s = "";
		Integer count = 0;
		for (TimetableGroupStudents student : students) {
			count++;
			s += "<tr>" + "<td class='tc'>" + count + "</td>"
					+ "<td class='tc'>" + student.getUser().getUsername()
					+ "</td>" + "<td class='tc'>"
					+ student.getUser().getCname() + "</td>"
					+ "<td class='tc'>"
					+ student.getUser().getSchoolClasses().getClassName()
					+ "</td>" + "<td>"
					+ student.getUser().getSchoolAcademy().getAcademyName()
					+ "</td>";
			Integer isConflict = 0;
			for (TimetableAppointment ta : timetableGroup
					.getTimetableAppointments()) {
				if (newTimetableCourseSchedulingService
						.checkSingleStudentTimeExceptSelfAppointment(student
								.getUser().getUsername(), ta.getId()) == true) {
					isConflict = 1;
					break;
				}
			}
			if (isConflict == 1) {
				s += "<td class='tc red'>是</td>";
			} else {
				s += "<td class='tc'>否</td>";
			}
			s += "<td class='tc'><input type='checkbox' value='"
					+ student.getUser().getUsername()
					+ "' id='LType' name='LType'></td>" + "</tr>";
		}
		int previousPage;
		int nextPage;
		if (page == 1) {
			previousPage = page;
		} else {
			previousPage = page - 1;
		}
		int totalPage = (totalRecords + pageSize - 1) / pageSize;
		if (page == (totalRecords + pageSize - 1) / pageSize) {
			nextPage = page;
		} else {
			nextPage = page + 1;
		}
		map.put("content", shareService.htmlEncode(s));
		String p = "<a class='btn' onclick='getStudents("
				+ groupId
				+ ","
				+ totalPage
				+ ")'>末页</a><a class='btn' onclick='getStudents("
				+ groupId
				+ ","
				+ nextPage
				+ ")'>下一页</a><div class='page-select'>"
				+ "<div class='page-word'>页</div><form><select class='page-number'><option selected='1'>1</option>"
				+ "<option>2</option><option>3</option></select></form><div class='page-word'>第</div></div><a class='btn' onclick='getStudents("
				+ groupId + "," + previousPage + ")'>上一页</a>"
				+ "<a class='btn' onclick='getStudents(" + groupId
				+ ",1)'>首页</a><div class'p-pos'>" + totalRecords + "条记录 • 共"
				+ totalPage + "页</div>";
		map.put("p", shareService.htmlEncode(p));
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
		String username = "<option value=\"\">请选择</option>";
		for (User u : users) {
			if (request.getParameter("username") != null
					&& request.getParameter("username").equals(u.getUsername())) {
				username += "<option value=\"" + u.getUsername()
						+ "\" selected>" + u.getUsername() + "</option>";
			} else {
				username += "<option value=\"" + u.getUsername() + "\">"
						+ u.getUsername() + "</option>";
			}
		}
		map.put("username", shareService.htmlEncode(username));

		String academy = "<option value=\"\">请选择</option>";
		for (SchoolAcademy a : academys) {
			if (request.getParameter("academy") != null
					&& request.getParameter("academy").equals(
							a.getAcademyNumber())) {
				academy += "<option value=\"" + a.getAcademyNumber()
						+ "\" selected>" + a.getAcademyName() + "</option>";
			} else {
				academy += "<option value=\"" + a.getAcademyNumber() + "\">"
						+ a.getAcademyName() + "</option>";
			}
		}
		map.put("academy", shareService.htmlEncode(academy));

		String schoolClass = "<option value=\"\">请选择</option>";
		for (SchoolClasses sc : classes) {
			if (request.getParameter("selectClass") != null
					&& request.getParameter("selectClass").equals(
							sc.getClassNumber())) {
				schoolClass += "<option value=\"" + sc.getClassNumber()
						+ "\" selected>" + sc.getClassName() + "</option>";
			} else {
				schoolClass += "<option value=\"" + sc.getClassNumber() + "\">"
						+ sc.getClassName() + "</option>";
			}
		}
		map.put("schoolClass", shareService.htmlEncode(schoolClass));
		map.put("groupName", timetableGroupDAO.findTimetableGroupById(groupId)
				.getGroupName());
		return map;
	}

	/************************************************************
	 * @description：根据组号找到学生名单
	 * @author：郑昕茹
	 * @date：2017-04-24
	 ************************************************************/
	@RequestMapping("/findConflictTime")
	public @ResponseBody
	Map<String, Integer> findConflictTime(@RequestParam Integer groupId,
			Integer[] chosenWeeks) {
		TimetableGroup t = timetableGroupDAO.findTimetableGroupById(groupId);
		if (t.getTimetableGroupStudentses() != null) {
			return newTimetableCourseSchedulingService
					.getConflictTimetableAndNum(
							t.getTimetableGroupStudentses(), chosenWeeks);
		} else
			return null;
	}

	/************************************************************
	 * @description：保存排课教师
	 * @author：郑昕茹
	 * @date：2017-04-25
	 ************************************************************/
	@RequestMapping("/saveTimetableAppointmentTeacher")
	public @ResponseBody String saveTimetableAppointmentTeacher(@RequestParam Integer appointmentId, String[] teacher) {
		// 找到排课
		TimetableAppointment t = timetableAppointmentDAO.findTimetableAppointmentById(appointmentId);
		for (TimetableTeacherRelated r : t.getTimetableTeacherRelateds()) {
			timetableTeacherRelatedDAO.remove(r);
		}
		// 删除教师
		t.setTimetableTeacherRelateds(null);
		t = timetableAppointmentDAO.store(t);
		// 重新安排教师
		for(String teachers :teacher){
			User user = userDAO.findUserByPrimaryKey(teachers);
			TimetableTeacherRelated teacherRelated = new TimetableTeacherRelated();
			teacherRelated.setTimetableAppointment(t);
			teacherRelated.setUser(user);
			timetableTeacherRelatedDAO.store(teacherRelated);
		}
		return "success";
	}
	/*********************************************************************************
	 * description： 合班操作 author：郑昕茹 date：2017-04-26
	 *********************************************************************************/
	@RequestMapping("/mergeCourses")
	public @ResponseBody
	String mergeCourses(String[] detailNos) {
		// 初始化人数
		Integer count = 0;
		// 创建一条新的合班记录
		SchoolCourseMerge schoolCourseMerge = new SchoolCourseMerge();
		schoolCourseMerge = schoolCourseMergeDAO.store(schoolCourseMerge);
		String finalString = "";
		List<SchoolCourse> courses = new ArrayList<SchoolCourse>();
		String courseNumber = "";
		// 遍历选中的Detail
		boolean isSameCourse = true;// 针对不同课程编号的
		String courseNumberOri = "";// 初始化课程编号
		// 当合并两门不同课程编号的课程时，一切已人数多的那一组为准
		SchoolCourseDetail courseWithMoreStudents = new SchoolCourseDetail();
		int studentCount = 0;// 初始化学生数量
		
		for (String detailNo : detailNos) {
			// 获取排课计划
			SchoolCourseDetail courseDetail = schoolCourseDetailDAO
					.findSchoolCourseDetailByCourseDetailNo(detailNo);
			
			if (!EmptyUtil.isStringEmpty(courseNumberOri) && !courseNumberOri.equals(courseDetail.getCourseNumber())) {
				isSameCourse = false;
			}else{
				courseNumberOri = courseDetail.getCourseNumber();
			}
			// 获取教学班
			SchoolCourse schoolCourse = courseDetail.getSchoolCourse();
			// 把教学计划和合班教学班进行关联
			for (SchoolCourseDetail scd : schoolCourse.getSchoolCourseDetails()) {
				scd.setSchoolCourseMerge(schoolCourseMerge);
				schoolCourseDetailDAO.store(scd);
			}
			// 找到该detail下的学生数，目的是获取人数最多的那个选课组
			int studentInThisDetail = courseDetail.getSchoolCourseStudents().size();
			if (studentInThisDetail>studentCount) {
				studentCount = courseDetail.getSchoolCourseStudents().size();
				courseWithMoreStudents = courseDetail;
			}
			
			// 将课程库编号进行拼接
			int courseCodeBit = schoolCourse.getCourseNo().indexOf("-(");
			if (count == 0) {
				courseNumber += schoolCourse.getCourseNo().substring(0, courseCodeBit);
			} else {
				courseNumber += "<br>"
						+ schoolCourse.getCourseNo().substring(0, courseCodeBit);
			}
			courses.add(schoolCourse);
			count++;
		}
		
		// 课程按课程序号排序
		Collections.sort(courses, new Comparator<SchoolCourse>() {
			public int compare(SchoolCourse arg0, SchoolCourse arg1) {
				return arg0.getCourseNo().compareTo(arg1.getCourseNo());
			}
		});
		
		// 课程组编号
		int begin = 0;
		int end = 0;
		for (int i = 0; i < courses.size() - 1; i++) {
			String here = courses.get(i).getCourseNo();
			String here1 = here.substring(0, here.indexOf("-("));
			String here12 = here1.substring(here1.length() - 2, here1.length());
			begin = i;
			end = i;
			for (int j = i + 1; j < courses.size(); j++) {
				String now = courses.get(j - 1).getCourseNo();
				String now1 = now.substring(0, now.indexOf("-("));
				String now12 = now1.substring(now1.length() - 2, now1.length());

				String next = courses.get(j).getCourseNo();
				String next1 = next.substring(0, next.indexOf("-("));
				String next12 = next1.substring(next1.length() - 2,
						next1.length());
				int number1 = Integer.parseInt(now12);
				int number2 = Integer.parseInt(next12);
				if (number1 + 1 == number2) {
					end = j;
					if (j == courses.size() - 1) {
						if (i == 0) {
							finalString += here12 + "-" + next12;
						} else {
							finalString += "," + here12 + "-" + next12;
						}
						i = j - 1;
					}
				} else {
					if (begin == end) {
						if (i == 0) {
							finalString += here12;
						} else {
							finalString += "," + here12;
						}
						if (j == courses.size() - 1) {
							if (i == 0) {
								finalString += next12;
							} else {
								finalString += "," + next12;
							}
							i = j - 1;
						}
						break;
					} else {
						if (i == 0) {
							finalString += here12 + "-" + now12;
						} else {
							finalString += "," + here12 + "-" + now12;
						}
						i = j - 1;
						break;
					}
				}
			}
		}
		String now = courses.get(0).getCourseNo();
		String now1 = now.substring(0, now.indexOf("-("));
		String now2 = now.substring(now.indexOf("-("), now.length());
		String now11 = now1.substring(0, now1.length() - 2);
		// 由此可见，整个合班过程中，并没有对学生表进行更改
		schoolCourseMerge.setStudentNumbers(newTimetableCourseSchedulingService
				.countSchoolCourseStudentsByMergeId(schoolCourseMerge.getId()));
		schoolCourseMerge.setCourseNumber(courseNumber);
		schoolCourseMerge.setCourseDetailNo(courseWithMoreStudents
				.getCourseDetailNo());
		schoolCourseMerge.setCourseName(courseWithMoreStudents.getCourseName());
		schoolCourseMerge.setTermId(courseWithMoreStudents.getSchoolTerm().getId());

		if (isSameCourse) {
			schoolCourseMerge.setCourseNo(now11 + "(" + finalString + ")" + now2);
			schoolCourseMerge.setCourseCode(courses.get(0).getCourseCode());
		}else {
			schoolCourseMerge.setCourseNo(courseWithMoreStudents.getCourseDetailNo());
			schoolCourseMerge.setCourseCode(courseWithMoreStudents.getSchoolCourse().getCourseCode());
		}
		
		schoolCourseMergeDAO.store(schoolCourseMerge);
		return "success";
	}

	/********************************************************************************
	 * Description: 保存合班排课结果（单条）
	 * 
	 * @author: 郑昕茹
	 * @throws ParseException
	 * @date: 2017-04-24
	 *********************************************************************************/
	@ResponseBody
	@RequestMapping("/saveMergeCourseTimetable")
	public Map<String, Object> saveMergeCourseTimetableCourseTimetable(
			@RequestParam int term, Integer week, Integer weekday,
			Integer selectClass, Integer group, Integer labRoom,
			Integer mergeId, Integer item, String teacher)
			throws ParseException {
		Map<String, Object> mapResult = new HashMap<String, Object>();
		// 保存课程的选课信息
		SchoolCourseMerge schoolCourseMerge = schoolCourseMergeDAO
				.findSchoolCourseMergeById(mergeId);

		Set<User> teachers = new HashSet<User>();
		if (schoolCourseMerge.getSchoolCourseDetails() != null) {
			for (SchoolCourseDetail d : schoolCourseMerge
					.getSchoolCourseDetails()) {
				teachers.addAll(d.getUsers());
				teachers.addAll(d.getUserByScheduleTeachers());
				teachers.add(d.getUser());
			}
		}
		LabRoom lab = labRoomDAO.findLabRoomById(labRoom);
		Map<Integer, Integer> groupIds = new HashMap<Integer, Integer>();
		Integer count = 0;
		// 选择的一节相当于两节
		Integer startClass = selectClass * 2 - 1;
		Integer endClass = selectClass * 2;
		// 获取所有节次
		List<SystemTime> times = timeDetailService.findAllTimes(1, -1, "S");
		Map<String, Calendar> time = new HashMap<String, Calendar>();
		Map<String, Calendar> timeEnd = new HashMap<String, Calendar>();
		for (SystemTime t : times) {
			time.put(t.getSectionName(), t.getStartDate());
			timeEnd.put(t.getSectionName(), t.getEndDate());
		}
		Map<String, Object> map = timetableAppointmentSaveService
				.saveMergeCourseTimetableCourseTimetable(
						term,
						ConvertUtil.stringToIntArray(startClass.toString()
								+ "," + endClass.toString()),
						ConvertUtil.stringToIntArray(labRoom.toString()),
						ConvertUtil.stringToIntArray(week.toString()), weekday,
						mergeId, ConvertUtil.stringToIntArray(item.toString()),
						teacher, group);
		TimetableAppointment appointment = (TimetableAppointment) map
				.get("appointment");
		appointment = timetableAppointmentDAO
				.findTimetableAppointmentById(appointment.getId());
		Integer oldAppointmentId = (Integer) map.get("oldAppointmentId");
		mapResult.put("oldAppointmentId", oldAppointmentId);
		if (appointment != null && appointment.getId() != null) {
			String classes = appointment.getStartClass() + "-"
					+ appointment.getEndClass();
			Integer start = appointment.getStartClass();
			Integer end = appointment.getEndClass();
			if (timetableAppointmentSaveService.findSameNumbersByAppointmentId(
					appointment.getId()).size() != 0) {
				classes = "";
				for (TimetableAppointmentSameNumber tas : timetableAppointmentSaveService
						.findSameNumbersByAppointmentId(appointment.getId())) {
					if (tas.getStartClass() < start) {
						start = tas.getStartClass();
					}
					if (tas.getEndClass() > end) {
						end = tas.getEndClass();
					}
					classes += tas.getStartClass() + "-" + tas.getEndClass()
							+ ",";
				}
			}
			Calendar calStart = Calendar.getInstance();
			Calendar calEnd = Calendar.getInstance();
			if (start >= 9) {
				calStart = time.get("晚");
			} else {
				calStart = time.get(start.toString());
			}
			if (end >= 9) {
				calEnd = timeEnd.get("晚");
			} else {
				calEnd = timeEnd.get(end.toString());
			}
			appointment.setActualStartDate(calStart);
			appointment.setActualEndDate(calEnd);
			timetableAppointmentDAO.store(appointment);

			// 此处需要修改一下  select要能多选
			String s = "<select multiple='true' class='chzn-select' id='teacher" + appointment.getId()
					+ "' onchange='saveTeacher(" + appointment.getId()
					+ ")'><option value=''>课题组</option>";
			for (User u : teachers) {
				if (u.getUsername()
						.equals(shareService.getUser().getUsername())) {
					s += "<option value='" + u.getUsername() + "' selected>"
							+ u.getCname() + "</option>";
				} else {
					s += "<option value='" + u.getUsername() + "'>"
							+ u.getCname() + "</option>";
				}
			}
			s += "</select>";
			mapResult.put("teacher", shareService.htmlEncode(s));
			SimpleDateFormat sdfNew = new SimpleDateFormat("HH:mm");

			String actualStartDate = "<input style='width:42%;height:25px;float:left;margin:0 3%;' id='actualStartDate"
					+ appointment.getId()
					+ "'  class='Wdate datepicker' value='"
					+ sdfNew.format(calStart.getTime())
					+ "' onfocus=\"WdatePicker({dateFmt:'HH:mm',skin:'whyGreen'})\" type='text' name='date'  onchange='saveActualStartDate("
					+ group + "," + appointment.getId() + ")'  readonly />";
			String actualEndDate = "<input style='width:42%;height:25px;float:left;margin:0 3%;' id='actualEndDate"
					+ appointment.getId()
					+ "'  class='Wdate datepicker' value='"
					+ sdfNew.format(calEnd.getTime())
					+ "' onfocus=\"WdatePicker({dateFmt:'HH:mm',skin:'whyGreen'})\" type='text' name='date'  onchange='saveActualEndDate("
					+ group + "," + appointment.getId() + ")'  readonly />";
			mapResult.put("actualStartDate",
					shareService.htmlEncode(actualStartDate));
			mapResult.put("actualEndDate",
					shareService.htmlEncode(actualEndDate));
			String close = "<a class='fa fa-times r close' title='关闭' id='close"
					+ appointment.getId()
					+ "' onclick='deleteSpecializedBasicCourseAppointment("
					+ appointment.getId() + ")'></a>";
			mapResult.put("close", shareService.htmlEncode(close));
			mapResult.put("classes", classes);
		}
		if (appointment != null && appointment.getId() != null) {
			mapResult.put("result", appointment.getId());
		} else {
			mapResult.put("result", -1);
		}
		return mapResult;
	}

	/************************************************************
	 * @description：根据批次找到其下全部排课
	 * @author：郑昕茹
	 * @date：2017-04-30
	 ************************************************************/
	@RequestMapping("/findAppointmentsByBatchId")
	public @ResponseBody
	Map<String, String> findAppointmentsByBatchId(@RequestParam Integer batchId) {
		Map<String, String> map = new HashMap<String, String>();
		List<TimetableAppointment> appointments = newTimetableCourseSchedulingService
				.findTimetableAppointmentsByBatchId(batchId);
		for (TimetableAppointment ta : appointments) {
			if (ta.getTimetableAppointmentSameNumbers() == null
					|| ta.getTimetableAppointmentSameNumbers() != null
					&& ta.getTimetableAppointmentSameNumbers().size() == 0) {
				for (int i = ta.getStartWeek(); i <= ta.getEndWeek(); i++) {
					for (int j = (ta.getStartClass() + 1) / 2; j <= (ta
							.getEndClass() / 2); j++) {
						for (TimetableGroup tg : ta.getTimetableGroups()) {
							map.put(i + "-" + ta.getWeekday() + "-" + j,
									tg.getGroupName());
						}
					}
				}
			} else {
				for (TimetableAppointmentSameNumber tas : ta
						.getTimetableAppointmentSameNumbers()) {
					for (int i = tas.getStartWeek(); i <= tas.getEndWeek(); i++) {
						for (int j = (tas.getStartClass() + 1) / 2; j <= (tas
								.getEndClass() / 2); j++) {
							for (TimetableGroup tg : tas
									.getTimetableAppointment()
									.getTimetableGroups()) {
								map.put(i + "-" + ta.getWeekday() + "-" + j,
										tg.getGroupName());
							}
						}
					}
				}
			}
		}
		return map;
	}

	/************************************************************
	 * @description：学生选择组
	 * @author：郑昕茹
	 * @date：2017-04-30
	 ************************************************************/
	@RequestMapping("/selectGroup")
	public @ResponseBody
	Map<String, Object> selectGroup(@RequestParam Integer groupId) {
		Map<String, Object> map = new HashMap<String, Object>();
		User user = shareService.getUser();
		TimetableGroup timetableGroup = timetableGroupDAO
				.findTimetableGroupById(groupId);
		boolean isConflict = false;
		Map<String, String> mapTimetable = new HashMap<String, String>();
		for (TimetableAppointment ta : timetableGroup
				.getTimetableAppointments()) {
			if (ta.getTimetableAppointmentSameNumbers() == null
					|| ta.getTimetableAppointmentSameNumbers() != null
					&& ta.getTimetableAppointmentSameNumbers().size() == 0) {
				if (newTimetableCourseSchedulingService.checkSingleStudentTime(
						ta.getStartWeek(), ta.getWeekday(), ta.getStartClass(),
						ta.getEndClass(), user.getUsername()) == true) {
					isConflict = true;
					break;
				}
				for (int i = ta.getStartWeek(); i <= ta.getEndWeek(); i++) {
					for (int j = (ta.getStartClass() + 1) / 2; j <= (ta
							.getEndClass() / 2); j++) {
						for (TimetableGroup tg : ta.getTimetableGroups()) {
							mapTimetable.put(i + "-" + ta.getWeekday() + "-"
									+ j, tg.getGroupName());
						}
					}
				}
			} else {
				for (TimetableAppointmentSameNumber tas : ta
						.getTimetableAppointmentSameNumbers()) {
					if (newTimetableCourseSchedulingService
							.checkSingleStudentTime(tas.getStartWeek(),
									tas.getWeekday(), tas.getStartClass(),
									tas.getEndClass(), user.getUsername()) == true) {
						isConflict = true;
						break;
					}
					for (int i = tas.getStartWeek(); i <= tas.getEndWeek(); i++) {
						for (int j = (tas.getStartClass() + 1) / 2; j <= (tas
								.getEndClass() / 2); j++) {
							for (TimetableGroup tg : tas
									.getTimetableAppointment()
									.getTimetableGroups()) {
								mapTimetable.put(i + "-" + ta.getWeekday()
										+ "-" + j, tg.getGroupName());
							}
						}
					}
				}
			}
		}
		map.put("mapTimetable", mapTimetable);
		if (isConflict == true) {
			map.put("result", "timeFail");
		} else {
			if (newTimetableCourseSchedulingService
					.findTimetableBatchStudentsByBatchId(
							timetableGroup.getTimetableBatch().getId()).size() != 0) {
				Integer selectStudents = 0;
				if (timetableGroup.getTimetableGroupStudentses() != null
						&& timetableGroup.getTimetableGroupStudentses().size() != 0) {
					selectStudents = timetableGroup
							.getTimetableGroupStudentses().size();
				}
				if (selectStudents < timetableGroup.getNumbers()) {
					TimetableBatchStudent timetableBatchStudent = newTimetableCourseSchedulingService
							.findTimetableBatchStudentsByBatchId(
									timetableGroup.getTimetableBatch().getId())
							.get(0);
					timetableBatchStudent.setTimetableGroup(timetableGroup);
					timetableBatchStudentDAO.store(timetableBatchStudent);

					TimetableGroupStudents timetableGroupStudent = new TimetableGroupStudents();
					timetableGroupStudent.setTimetableGroup(timetableGroup);
					timetableGroupStudent.setUser(shareService.getUser());
					timetableGroupStudentsDAO.store(timetableGroupStudent);
					map.put("result", "success");
				} else {
					map.put("result", "sizeFail");
				}
			} else {
				Integer selectStudents = 0;
				if (timetableGroup.getTimetableGroupStudentses() != null
						&& timetableGroup.getTimetableGroupStudentses().size() != 0) {
					selectStudents = timetableGroup
							.getTimetableGroupStudentses().size();
				}
				if (selectStudents < timetableGroup.getNumbers()) {
					TimetableBatchStudent timetableBatchStudent = new TimetableBatchStudent();
					timetableBatchStudent.setTimetableGroup(timetableGroup);
					timetableBatchStudent.setTimetableBatch(timetableGroup
							.getTimetableBatch());
					timetableBatchStudent.setWithdrawTimes(0);
					timetableBatchStudent.setUser(shareService.getUser());
					timetableBatchStudentDAO.store(timetableBatchStudent);

					TimetableGroupStudents timetableGroupStudent = new TimetableGroupStudents();
					timetableGroupStudent.setTimetableGroup(timetableGroup);
					timetableGroupStudent.setUser(shareService.getUser());
					timetableGroupStudentsDAO.store(timetableGroupStudent);

					map.put("result", "success");
				} else {
					map.put("result", "sizeFail");
				}
			}
		}

		return map;
	}

	/************************************************************
	 * @description：学生退选
	 * @author：郑昕茹
	 * @date：2017-04-30
	 ************************************************************/
	@RequestMapping("/unSelectGroup")
	public @ResponseBody
	Map<String, Object> unSelectGroup(@RequestParam Integer batchId) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (newTimetableCourseSchedulingService
				.findTimetableBatchStudentsByBatchId(batchId).size() > 0) {
			TimetableBatchStudent timetableBatchStudent = newTimetableCourseSchedulingService
					.findTimetableBatchStudentsByBatchId(batchId).get(0);
			TimetableGroup timetableGroup = timetableBatchStudent
					.getTimetableGroup();
			timetableBatchStudent.setWithdrawTimes(timetableBatchStudent
					.getWithdrawTimes() + 1);
			timetableBatchStudent.setTimetableGroup(null);
			timetableBatchStudentDAO.store(timetableBatchStudent);

			Set<TimetableGroupStudents> students = timetableGroup
					.getTimetableGroupStudentses();
			if (newTimetableCourseSchedulingService
					.findTimetableGroupStudentsByGroupId(timetableGroup.getId())
					.size() != 0) {
				students.remove(newTimetableCourseSchedulingService
						.findTimetableGroupStudentsByGroupId(
								timetableGroup.getId()).get(0));
				timetableGroupStudentsDAO
						.remove(newTimetableCourseSchedulingService
								.findTimetableGroupStudentsByGroupId(
										timetableGroup.getId()).get(0));
			}
			timetableGroup.setTimetableGroupStudentses(students);
			timetableGroupDAO.store(timetableGroup);

			Map<String, String> mapTimetable = new HashMap<String, String>();
			for (TimetableAppointment ta : timetableGroup
					.getTimetableAppointments()) {
				if (ta.getTimetableAppointmentSameNumbers() == null
						|| ta.getTimetableAppointmentSameNumbers() != null
						&& ta.getTimetableAppointmentSameNumbers().size() == 0) {
					for (int i = ta.getStartWeek(); i <= ta.getEndWeek(); i++) {
						for (int j = (ta.getStartClass() + 1) / 2; j <= (ta
								.getEndClass() / 2); j++) {
							for (TimetableGroup tg : ta.getTimetableGroups()) {
								mapTimetable.put(i + "-" + ta.getWeekday()
										+ "-" + j, tg.getGroupName());
							}
						}
					}
				} else {
					for (TimetableAppointmentSameNumber tas : ta
							.getTimetableAppointmentSameNumbers()) {
						for (int i = tas.getStartWeek(); i <= tas.getEndWeek(); i++) {
							for (int j = (tas.getStartClass() + 1) / 2; j <= (tas
									.getEndClass() / 2); j++) {
								for (TimetableGroup tg : tas
										.getTimetableAppointment()
										.getTimetableGroups()) {
									mapTimetable.put(i + "-" + ta.getWeekday()
											+ "-" + j, tg.getGroupName());
								}
							}
						}
					}
				}
			}
			map.put("mapTimetable", mapTimetable);
			map.put("result", "success");
		}
		return map;
	}

	/************************************************************
	 * @description：删除组中的学生
	 * @author：郑昕茹
	 * @date：2017-05-02
	 ************************************************************/
	@RequestMapping("/deleteThisStudent")
	public @ResponseBody
	String deleteThisStudent(@RequestParam Integer studentId,
			HttpServletRequest request) {
		TimetableGroupStudents tgs = timetableGroupStudentsDAO
				.findTimetableGroupStudentsById(studentId);
		TimetableGroup tg = tgs.getTimetableGroup();
		Integer numbers = tg.getNumbers() - 1;
		tg.setNumbers(numbers);
		timetableGroupDAO.store(tg);
		timetableGroupStudentsDAO.remove(tgs);
		return tg.getId().toString();
	}

	/************************************************************
	 * @description：根据组号找到学生名单
	 * @author：郑昕茹
	 * @date：2017-04-24
	 ************************************************************/
	@RequestMapping("/addStudentsInMergeCourse")
	public @ResponseBody
	Map<String, Object> addStudentsInMergeCourse(@RequestParam Integer groupId,
			@RequestParam Integer isMerge, int page, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		TimetableGroup timetableGroup = timetableGroupDAO
				.findTimetableGroupById(groupId);
		int pageSize = 20;
		List<SchoolCourseStudent> students = newTimetableCourseSchedulingService
				.findSchoolCourseStudentsNotInBatchGroup(timetableGroup
						.getTimetableBatch().getId(), page, pageSize, isMerge,
						timetableGroup.getTimetableBatch().getCourseCode(),
						request);

		int totalRecords = newTimetableCourseSchedulingService
				.findSchoolCourseStudentsNotInBatchGroup(
						timetableGroup.getTimetableBatch().getId(), 1, -1,
						isMerge,
						timetableGroup.getTimetableBatch().getCourseCode(),
						request).size();

		Map<String, Integer> pageModel = shareService.getPage(page, pageSize,
				totalRecords);
		String s = "";
		Integer count = 0;
		for (SchoolCourseStudent student : students) {
			count++;
			s += "<tr>" + "<td class='tc'>" + count + "</td>"
					+ "<td class='tc'>"
					+ student.getUserByStudentNumber().getUsername() + "</td>"
					+ "<td class='tc'>"
					+ student.getUserByStudentNumber().getCname() + "</td>"
					+ "<td class='tc'>"
					+ student.getSchoolClasses().getClassName() + "</td>"
					+ "<td>" + student.getSchoolAcademy().getAcademyName()
					+ "</td>";
			Integer isConflict = 0;
			for (TimetableAppointment ta : timetableGroup
					.getTimetableAppointments()) {
				if (newTimetableCourseSchedulingService
						.checkSingleStudentTimeExceptSelfAppointment(student
								.getUserByStudentNumber().getUsername(), ta
								.getId()) == true) {
					isConflict = 1;
					break;
				}
			}
			if (isConflict == 1) {
				s += "<td class='tc red'>是</td>";
			} else {
				s += "<td class='tc'>否</td>";
			}
			s += "<td class='tc'><a onclick='addThisStudent("
					+ student.getUserByStudentNumber().getUsername()
					+ ")'>添加</a></td>" + "</tr>";
		}
		int previousPage;
		int nextPage;
		if (page == 1) {
			previousPage = page;
		} else {
			previousPage = page - 1;
		}
		int totalPage = (totalRecords + pageSize - 1) / pageSize;
		if (page == (totalRecords + pageSize - 1) / pageSize) {
			nextPage = page;
		} else {
			nextPage = page + 1;
		}
		map.put("content", shareService.htmlEncode(s));

		String p = "<a class='btn' onclick='getAddStudents("
				+ groupId
				+ ","
				+ totalPage
				+ ")'>末页</a><a class='btn' onclick='getAddStudents("
				+ groupId
				+ ","
				+ nextPage
				+ ")'>下一页</a><div class='page-select'>"
				+ "<div class='page-word'>页</div><form><select class='page-number'><option selected='1'>1</option>"
				+ "<option>2</option><option>3</option></select></form><div class='page-word'>第</div></div><a class='btn' onclick='getAddStudents("
				+ groupId + "," + previousPage + ")'>上一页</a>"
				+ "<a class='btn' onclick='getAddStudents(" + groupId
				+ ",1)'>首页</a><div class'p-pos'>" + totalRecords + "条记录 • 共"
				+ totalPage + "页</div>";
		map.put("p", shareService.htmlEncode(p));

		List<SchoolCourseStudent> allStudents = newTimetableCourseSchedulingService
				.findSchoolCourseStudentsNotInBatchGroup(timetableGroup
						.getTimetableBatch().getId(), 1, -1, isMerge,
						timetableGroup.getTimetableBatch().getCourseCode(),
						request);
		Set<SchoolAcademy> academys = new HashSet<SchoolAcademy>();
		Set<User> users = new HashSet<User>();
		Set<SchoolClasses> classes = new HashSet<SchoolClasses>();
		for (SchoolCourseStudent t : allStudents) {
			academys.add(t.getSchoolAcademy());
			users.add(t.getUserByStudentNumber());
			classes.add(t.getSchoolClasses());
		}
		String username = "<option value=\"\">请选择</option>";
		for (User u : users) {
			if (request.getParameter("username") != null
					&& request.getParameter("username").equals(u.getUsername())) {
				username += "<option value=\"" + u.getUsername()
						+ "\" selected>" + u.getUsername() + "</option>";
			} else {
				username += "<option value=\"" + u.getUsername() + "\">"
						+ u.getUsername() + "</option>";
			}
		}
		map.put("username", shareService.htmlEncode(username));

		String academy = "<option value=\"\">请选择</option>";
		for (SchoolAcademy a : academys) {
			if (request.getParameter("academy") != null
					&& request.getParameter("academy").equals(
							a.getAcademyNumber())) {
				academy += "<option value=\"" + a.getAcademyNumber()
						+ "\" selected>" + a.getAcademyName() + "</option>";
			} else {
				academy += "<option value=\"" + a.getAcademyNumber() + "\">"
						+ a.getAcademyName() + "</option>";
			}
		}
		map.put("academy", shareService.htmlEncode(academy));

		String schoolClass = "<option value=\"\">请选择</option>";
		for (SchoolClasses sc : classes) {
			if (request.getParameter("selectClass") != null
					&& request.getParameter("selectClass").equals(sc.getId())) {
				schoolClass += "<option value=\"" + sc.getId() + "\">"
						+ sc.getClassName() + "</option>";
			} else {
				schoolClass += "<option value=\"" + sc.getId() + "\">"
						+ sc.getClassName() + "</option>";
			}
		}
		map.put("schoolClass", shareService.htmlEncode(schoolClass));
		map.put("groupName", timetableGroupDAO.findTimetableGroupById(groupId)
				.getGroupName());
		return map;
	}

	/************************************************************
	 * @description：添加组中的学生
	 * @author：郑昕茹
	 * @date：2017-05-03
	 ************************************************************/
	@RequestMapping("/addThisStudent")
	public @ResponseBody
	String addThisStudent(@RequestParam String username,
			@RequestParam Integer groupId, HttpServletRequest request) {
		TimetableGroup timetableGroup = timetableGroupDAO
				.findTimetableGroupById(groupId);

		TimetableGroupStudents tgs = new TimetableGroupStudents();
		tgs.setTimetableGroup(timetableGroup);
		tgs.setUser(userDAO.findUserByPrimaryKey(username));
		timetableGroupStudentsDAO.store(tgs);
		return "success";

	}

	/************************************************************
	 * @description：根据批次找到其下全部排课
	 * @author：郑昕茹
	 * @date：2017-05-03
	 ************************************************************/
	@RequestMapping("/findAppointmentsByCourseDetailNo")
	public @ResponseBody
	Map<String, String> findAppointmentsByCourseDetailNo(
			@RequestParam String courseDetailNo) {
		Map<String, String> map = new HashMap<String, String>();
		List<TimetableAppointment> appointments = newTimetableCourseSchedulingService
				.findTimetableAppointmentsByCourseDetailNo(courseDetailNo);
		Integer count = 0;
		for (TimetableAppointment ta : appointments) {
			count++;
			if (ta.getTimetableAppointmentSameNumbers() == null
					|| ta.getTimetableAppointmentSameNumbers() != null
					&& ta.getTimetableAppointmentSameNumbers().size() == 0) {
				for (int i = ta.getStartWeek(); i <= ta.getEndWeek(); i++) {
					for (int j = (ta.getStartClass() + 1) / 2; j <= (ta
							.getEndClass() / 2); j++) {
						map.put(i + "-" + ta.getWeekday() + "-" + j,
								count.toString());
					}
				}
			} else {
				for (TimetableAppointmentSameNumber tas : ta
						.getTimetableAppointmentSameNumbers()) {
					for (int i = tas.getStartWeek(); i <= tas.getEndWeek(); i++) {
						for (int j = (tas.getStartClass() + 1) / 2; j <= (tas
								.getEndClass() / 2); j++) {
							map.put(i + "-" + tas.getWeekday() + "-" + j,
									count.toString());
						}
					}
				}
			}
		}
		return map;
	}

	/************************************************************
	 * @description：学生选择组(公选课）
	 * @author：郑昕茹
	 * @date：2017-05-03
	 ************************************************************/
	@RequestMapping("/selectPublicGroup")
	public @ResponseBody
	Map<String, Object> selectPublicGroup(@RequestParam Integer groupId) {
		Map<String, Object> map = new HashMap<String, Object>();
		User user = shareService.getUser();
		TimetableGroup timetableGroup = timetableGroupDAO
				.findTimetableGroupById(groupId);
		boolean isConflict = false;
		Map<String, String> mapTimetable = new HashMap<String, String>();
		for (TimetableAppointment ta : timetableGroup
				.getTimetableAppointments()) {
			if (ta.getTimetableAppointmentSameNumbers() == null
					|| ta.getTimetableAppointmentSameNumbers() != null
					&& ta.getTimetableAppointmentSameNumbers().size() == 0) {
				if (newTimetableCourseSchedulingService.checkSingleStudentTime(
						ta.getStartWeek(), ta.getWeekday(), ta.getStartClass(),
						ta.getEndClass(), user.getUsername()) == true) {
					isConflict = true;
					break;
				}
				for (int i = ta.getStartWeek(); i <= ta.getEndWeek(); i++) {
					for (int j = (ta.getStartClass() + 1) / 2; j <= (ta
							.getEndClass() / 2); j++) {
						for (TimetableGroup tg : ta.getTimetableGroups()) {
							mapTimetable.put(i + "-" + ta.getWeekday() + "-"
									+ j, tg.getGroupName());
						}
					}
				}
			} else {
				for (TimetableAppointmentSameNumber tas : ta
						.getTimetableAppointmentSameNumbers()) {
					if (newTimetableCourseSchedulingService
							.checkSingleStudentTime(tas.getStartWeek(),
									tas.getWeekday(), tas.getStartClass(),
									tas.getEndClass(), user.getUsername()) == true) {
						isConflict = true;
						break;
					}
					for (int i = tas.getStartWeek(); i <= tas.getEndWeek(); i++) {
						for (int j = (tas.getStartClass() + 1) / 2; j <= (tas
								.getEndClass() / 2); j++) {
							for (TimetableGroup tg : tas
									.getTimetableAppointment()
									.getTimetableGroups()) {
								mapTimetable.put(i + "-" + ta.getWeekday()
										+ "-" + j, tg.getGroupName());
							}
						}
					}
				}
			}
		}
		map.put("mapTimetable", mapTimetable);
		if (isConflict == true) {
			map.put("result", "timeFail");
		} else {
			Integer selectStudents = 0;
			if (timetableGroup.getTimetableGroupStudentses() != null
					&& timetableGroup.getTimetableGroupStudentses().size() != 0) {
				selectStudents = timetableGroup.getTimetableGroupStudentses()
						.size();
			}
			if (selectStudents < timetableGroup.getNumbers()) {

				TimetableGroupStudents timetableGroupStudent = new TimetableGroupStudents();
				timetableGroupStudent.setTimetableGroup(timetableGroup);
				timetableGroupStudent.setUser(shareService.getUser());
				timetableGroupStudentsDAO.store(timetableGroupStudent);
				map.put("result", "success");
			} else {
				map.put("result", "sizeFail");
			}
		}

		return map;
	}

	/************************************************************
	 * @throws ParseException
	 * @description：学生退选（公选课）
	 * @author：郑昕茹
	 * @date：2017-05-03
	 ************************************************************/
	@RequestMapping("/unSelectPublicGroup")
	public @ResponseBody
	Map<String, Object> unSelectPublicGroup(@RequestParam Integer groupId)
			throws ParseException {
		Map<String, Object> map = new HashMap<String, Object>();
		TimetableGroup timetableGroup = timetableGroupDAO
				.findTimetableGroupById(groupId);
		User user = shareService.getUser();
		if (timetableGroup.getTimetableStyle() == 23)// 公选
		{
			SchoolCourseDetail schoolCourseDetail = schoolCourseDetailDAO
					.findSchoolCourseDetailByCourseDetailNo(timetableGroup
							.getTimetableBatch().getCourseCode());
			if (schoolCourseDetail != null
					&& schoolCourseDetail.getWithdrawalTimes() != null) {
				if (user.getPublicWithdrawlTimes() != null
						&& user.getPublicWithdrawlTimes().intValue() >= schoolCourseDetail
								.getWithdrawalTimes().intValue()) {
					map.put("result", "chooseFailed");
					return map;
				}
			}
		}
		Set<TimetableGroupStudents> students = timetableGroup
				.getTimetableGroupStudentses();
		if (newTimetableCourseSchedulingService
				.findTimetableGroupStudentsByGroupId(timetableGroup.getId())
				.size() != 0) {
			students.remove(newTimetableCourseSchedulingService
					.findTimetableGroupStudentsByGroupId(timetableGroup.getId())
					.get(0));
			timetableGroupStudentsDAO
					.remove(newTimetableCourseSchedulingService
							.findTimetableGroupStudentsByGroupId(
									timetableGroup.getId()).get(0));
		}
		timetableGroup.setTimetableGroupStudentses(students);
		timetableGroupDAO.store(timetableGroup);
		List<SystemTime> times = timeDetailService.findAllTimes(0, -1, "S");
		Map<String, Calendar> time = new HashMap<String, Calendar>();
		for (SystemTime t : times) {
			time.put(t.getSectionName(), t.getStartDate());
		}
		Map<String, String> mapTimetable = new HashMap<String, String>();
		SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		for (TimetableAppointment ta : timetableGroup
				.getTimetableAppointments()) {
			if (ta.getTimetableAppointmentSameNumbers() == null
					|| ta.getTimetableAppointmentSameNumbers() != null
					&& ta.getTimetableAppointmentSameNumbers().size() == 0) {
				Integer startWeek = ta.getStartWeek();
				Integer weekday = ta.getWeekday();
				Integer startClass = ta.getStartClass();
				SchoolWeek schoolWeek = newTimetableCourseSchedulingService
						.findSchoolWeekByWeekAndWeekdayAndTerm(
								startWeek,
								weekday,
								shareService.getBelongsSchoolTerm(
										Calendar.getInstance()).getId());
				String date = sdfDate.format(schoolWeek.getDate().getTime());
				String classTime = "";
				if (startClass >= 9) {
					classTime = sdfTime.format(time.get("晚").getTime());
				} else {
					classTime = sdfTime.format(time.get(startClass.toString())
							.getTime());
				}
				Calendar startDate = Calendar.getInstance();
				startDate.setTime(sdf.parse(date + " " + classTime));
				if (timetableGroup.getTimetableStyle() == 23)// 公选
				{
					SchoolCourseDetail schoolCourseDetail = schoolCourseDetailDAO
							.findSchoolCourseDetailByCourseDetailNo(timetableGroup
									.getTimetableBatch().getCourseCode());
					if (schoolCourseDetail != null
							&& schoolCourseDetail.getWithdrawalMinute() != null) {
						startDate.add(Calendar.MINUTE,
								schoolCourseDetail.getWithdrawalMinute());
					}
				}
				if (startDate.before(Calendar.getInstance())) {
					map.put("result", "timeFailed");
					return map;
				}
				for (int i = ta.getStartWeek(); i <= ta.getEndWeek(); i++) {
					for (int j = (ta.getStartClass() + 1) / 2; j <= (ta
							.getEndClass() / 2); j++) {
						for (TimetableGroup tg : ta.getTimetableGroups()) {
							mapTimetable.put(i + "-" + ta.getWeekday() + "-"
									+ j, tg.getGroupName());
						}
					}
				}
			} else {
				for (TimetableAppointmentSameNumber tas : ta
						.getTimetableAppointmentSameNumbers()) {
					Integer startWeek = tas.getStartWeek();
					Integer weekday = tas.getWeekday();
					Integer startClass = tas.getStartClass();
					SchoolWeek schoolWeek = newTimetableCourseSchedulingService
							.findSchoolWeekByWeekAndWeekdayAndTerm(
									startWeek,
									weekday,
									shareService.getBelongsSchoolTerm(
											Calendar.getInstance()).getId());
					String date = sdfDate
							.format(schoolWeek.getDate().getTime());
					String classTime = "";
					if (startClass >= 9) {
						classTime = sdfTime.format(time.get("晚").getTime());
					} else {
						classTime = sdfTime.format(time.get(
								startClass.toString()).getTime());
					}
					Calendar startDate = Calendar.getInstance();
					startDate.setTime(sdf.parse(date + " " + classTime));
					if (timetableGroup.getTimetableStyle() == 23)// 公选
					{
						SchoolCourseDetail schoolCourseDetail = schoolCourseDetailDAO
								.findSchoolCourseDetailByCourseDetailNo(timetableGroup
										.getTimetableBatch().getCourseCode());
						if (schoolCourseDetail != null
								&& schoolCourseDetail.getWithdrawalMinute() != null) {
							startDate.add(Calendar.MINUTE,
									schoolCourseDetail.getWithdrawalMinute());
						}
					}
					if (startDate.before(Calendar.getInstance())) {
						map.put("result", "timeFailed");
						return map;
					}
					for (int i = tas.getStartWeek(); i <= tas.getEndWeek(); i++) {
						for (int j = (tas.getStartClass() + 1) / 2; j <= (tas
								.getEndClass() / 2); j++) {
							for (TimetableGroup tg : tas
									.getTimetableAppointment()
									.getTimetableGroups()) {
								mapTimetable.put(i + "-" + ta.getWeekday()
										+ "-" + j, tg.getGroupName());
							}
						}
					}
				}
			}
		}
		map.put("mapTimetable", mapTimetable);
		map.put("result", "success");
		map.put("courseDetailNo", timetableGroup.getTimetableBatch()
				.getCourseCode());
		if (user.getPublicWithdrawlTimes() == null) {
			user.setPublicWithdrawlTimes(1);
		} else {
			user.setPublicWithdrawlTimes(user.getPublicWithdrawlTimes() + 1);
		}
		userDAO.store(user);
		return map;
	}

	/********************************************************************************
	 * Description: 保存选课开始时间（基础课）
	 * 
	 * @author: 郑昕茹
	 * @throws ParseException
	 * @date: 2017-05-04
	 *********************************************************************************/
	@ResponseBody
	@RequestMapping("/saveStartTime")
	public String saveStartTime(@RequestParam String startTime,
			@RequestParam String courseDetailNo) throws ParseException {
		SchoolCourseDetail schoolCourseDetail = schoolCourseDetailDAO
				.findSchoolCourseDetailByCourseDetailNo(courseDetailNo);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar calendar = Calendar.getInstance();
		if (startTime != null && !startTime.equals("")) {
			Date startDate = sdf.parse(startTime);
			calendar.setTime(startDate);
			schoolCourseDetail.setCourseStart(calendar);
			schoolCourseDetailDAO.store(schoolCourseDetail);
		}
		return "success";
	}

	/********************************************************************************
	 * Description: 保存选课开始时间（基础课）
	 * 
	 * @author: 郑昕茹
	 * @throws ParseException
	 * @date: 2017-05-04
	 *********************************************************************************/
	@ResponseBody
	@RequestMapping("/saveEndTime")
	public String saveEndTime(@RequestParam String endTime,
			@RequestParam String courseDetailNo) throws ParseException {
		SchoolCourseDetail schoolCourseDetail = schoolCourseDetailDAO
				.findSchoolCourseDetailByCourseDetailNo(courseDetailNo);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar calendar = Calendar.getInstance();
		if (endTime != null && !endTime.equals("")) {
			Date startDate = sdf.parse(endTime);
			calendar.setTime(startDate);
			schoolCourseDetail.setCourseEnd(calendar);
			schoolCourseDetailDAO.store(schoolCourseDetail);
		}
		return "success";
	}

	/********************************************************************************
	 * Description: 保存选课开始时间（基础课）
	 * 
	 * @author: 郑昕茹
	 * @throws ParseException
	 * @date: 2017-05-04
	 *********************************************************************************/
	@ResponseBody
	@RequestMapping("/saveStartTimeInMerge")
	public String saveStartTimeInMerge(@RequestParam String startTime,
			@RequestParam Integer mergeId) throws ParseException {
		SchoolCourseMerge schoolCourseMerge = schoolCourseMergeDAO
				.findSchoolCourseMergeById(mergeId);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar calendar = Calendar.getInstance();
		if (startTime != null && !startTime.equals("")) {
			Date startDate = sdf.parse(startTime);
			calendar.setTime(startDate);
			schoolCourseMerge.setCourseStart(calendar);
			schoolCourseMergeDAO.store(schoolCourseMerge);
		}
		return "success";
	}

	/********************************************************************************
	 * Description: 保存选课开始时间（基础课）
	 * 
	 * @author: 郑昕茹
	 * @throws ParseException
	 * @date: 2017-05-04
	 *********************************************************************************/
	@ResponseBody
	@RequestMapping("/saveEndTimeInMerge")
	public String saveEndTimeInMerge(@RequestParam String endTime,
			@RequestParam Integer mergeId) throws ParseException {
		SchoolCourseMerge schoolCourseMerge = schoolCourseMergeDAO
				.findSchoolCourseMergeById(mergeId);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar calendar = Calendar.getInstance();
		if (endTime != null && !endTime.equals("")) {
			Date startDate = sdf.parse(endTime);
			calendar.setTime(startDate);
			schoolCourseMerge.setCourseEnd(calendar);
			schoolCourseMergeDAO.store(schoolCourseMerge);
		}
		return "success";
	}

	/************************************************************
	 * Description: 保存实际开课时间（针对排课记录）
	 * 
	 * @author: 郑昕茹
	 * @throws ParseException
	 * @date: 2017-05-05
	 *********************************************************************************/
	@ResponseBody
	@RequestMapping("/saveActualStartDate")
	public String saveActualStartDate(@RequestParam String actualStartDate,
			@RequestParam Integer groupId, @RequestParam Integer appointmentId)
			throws ParseException {
		TimetableGroup timetableGroup = timetableGroupDAO
				.findTimetableGroupById(groupId);
		TimetableAppointment timetableAppointment = timetableAppointmentDAO
				.findTimetableAppointmentById(appointmentId);
		SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
		if (timetableGroup.getTimetableBatch().getType() == 21
				|| timetableGroup.getTimetableBatch().getType() == 22
				|| timetableGroup.getTimetableBatch().getType() == 28) {
			SchoolCourseDetail detail = schoolCourseDetailDAO
					.findSchoolCourseDetailByCourseDetailNo(timetableGroup
							.getTimetableBatch().getCourseCode());
			if (detail != null && detail.getCourseStart() != null) {
				actualStartDate = sdfDate.format(detail.getCourseStart()
						.getTime()) + " " + actualStartDate;
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				Calendar calendar = Calendar.getInstance();
				if (actualStartDate != null && !actualStartDate.equals("")) {
					Date startDate = sdf.parse(actualStartDate);
					calendar.setTime(startDate);
					timetableAppointment.setActualStartDate(calendar);
					timetableAppointmentDAO.store(timetableAppointment);
				}
			} else {
				SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
				Calendar calendar = Calendar.getInstance();
				if (actualStartDate != null && !actualStartDate.equals("")) {
					Date startDate = sdf.parse(actualStartDate);
					calendar.setTime(startDate);
					timetableAppointment.setActualStartDate(calendar);
					timetableAppointmentDAO.store(timetableAppointment);
				}
			}
		} else {
			SchoolCourseMerge merge = schoolCourseMergeDAO
					.findSchoolCourseMergeById(Integer.parseInt(timetableGroup
							.getTimetableBatch().getCourseCode()));
			if (merge != null && merge.getCourseStart() != null) {
				actualStartDate = sdfDate.format(merge.getCourseStart()
						.getTime()) + " " + actualStartDate;
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				Calendar calendar = Calendar.getInstance();
				if (actualStartDate != null && !actualStartDate.equals("")) {
					Date startDate = sdf.parse(actualStartDate);
					calendar.setTime(startDate);
					timetableAppointment.setActualStartDate(calendar);
					timetableAppointmentDAO.store(timetableAppointment);
				}
			} else {
				SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
				Calendar calendar = Calendar.getInstance();
				if (actualStartDate != null && !actualStartDate.equals("")) {
					Date startDate = sdf.parse(actualStartDate);
					calendar.setTime(startDate);
					timetableAppointment.setActualStartDate(calendar);
					timetableAppointmentDAO.store(timetableAppointment);
				}
			}
		}

		return "success";
	}

	/************************************************************
	 * Description: 保存实际结束时间（针对排课记录）
	 * 
	 * @author: 郑昕茹
	 * @throws ParseException
	 * @date: 2017-05-05
	 *********************************************************************************/
	@ResponseBody
	@RequestMapping("/saveActualEndDate")
	public String saveActualEndDate(@RequestParam String actualEndDate,
			@RequestParam Integer groupId, @RequestParam Integer appointmentId)
			throws ParseException {
		TimetableGroup timetableGroup = timetableGroupDAO
				.findTimetableGroupById(groupId);
		TimetableAppointment timetableAppointment = timetableAppointmentDAO
				.findTimetableAppointmentById(appointmentId);
		SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
		if (timetableGroup.getTimetableBatch().getType() == 21
				|| timetableGroup.getTimetableBatch().getType() == 22
				|| timetableGroup.getTimetableBatch().getType() == 28) {
			SchoolCourseDetail detail = schoolCourseDetailDAO
					.findSchoolCourseDetailByCourseDetailNo(timetableGroup
							.getTimetableBatch().getCourseCode());
			if (detail != null && detail.getCourseStart() != null) {
				actualEndDate = sdfDate.format(detail.getCourseStart()
						.getTime()) + " " + actualEndDate;
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				Calendar calendar = Calendar.getInstance();
				if (actualEndDate != null && !actualEndDate.equals("")) {
					Date endDate = sdf.parse(actualEndDate);
					calendar.setTime(endDate);
					timetableAppointment.setActualEndDate(calendar);
					timetableAppointmentDAO.store(timetableAppointment);
				}
			} else {
				SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
				Calendar calendar = Calendar.getInstance();
				if (actualEndDate != null && !actualEndDate.equals("")) {
					Date endDate = sdf.parse(actualEndDate);
					calendar.setTime(endDate);
					timetableAppointment.setActualEndDate(calendar);
					timetableAppointmentDAO.store(timetableAppointment);
				}
			}
		} else {
			SchoolCourseMerge merge = schoolCourseMergeDAO
					.findSchoolCourseMergeById(Integer.parseInt(timetableGroup
							.getTimetableBatch().getCourseCode()));
			if (merge != null && merge.getCourseStart() != null) {
				actualEndDate = sdfDate
						.format(merge.getCourseStart().getTime())
						+ " "
						+ actualEndDate;
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				Calendar calendar = Calendar.getInstance();
				if (actualEndDate != null && !actualEndDate.equals("")) {
					Date endDate = sdf.parse(actualEndDate);
					calendar.setTime(endDate);
					timetableAppointment.setActualEndDate(calendar);
					timetableAppointmentDAO.store(timetableAppointment);
				}
			} else {
				SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
				Calendar calendar = Calendar.getInstance();
				if (actualEndDate != null && !actualEndDate.equals("")) {
					Date endDate = sdf.parse(actualEndDate);
					calendar.setTime(endDate);
					timetableAppointment.setActualEndDate(calendar);
					timetableAppointmentDAO.store(timetableAppointment);
				}
			}
		}

		return "success";
	}

	/************************************************************
	 * Description: 完成该批次排课
	 * 
	 * @author: 郑昕茹
	 * @date: 2017-05-08
	 *********************************************************************************/
	@ResponseBody
	@RequestMapping("/completeBatchTimetable")
	public String completeBatchTimetable(@RequestParam Integer batchId)
			throws ParseException {
		TimetableBatch timetableBatch = timetableBatchDAO
				.findTimetableBatchById(batchId);
		timetableBatch.setIsComplete(1);
		timetableBatch = timetableBatchDAO.store(timetableBatch);
		if (timetableBatch.getTimetableGroups() != null) {
			for (TimetableGroup tg : timetableBatch.getTimetableGroups()) {
				if (tg.getTimetableAppointments() != null) {
					for (TimetableAppointment ta : tg
							.getTimetableAppointments()) {
						ta.setStatus(1);
						timetableAppointmentDAO.store(ta);
					}
				}
			}
		}
		return "success";
	}

	/************************************************************
	 * Description: 修改该批次排课
	 * 
	 * @author: 郑昕茹
	 * @date: 2017-05-15
	 *********************************************************************************/
	@ResponseBody
	@RequestMapping("/modifyBatchTimetable")
	public String modifyBatchTimetable(@RequestParam Integer batchId)
			throws ParseException {
		TimetableBatch timetableBatch = timetableBatchDAO.findTimetableBatchById(batchId);
		timetableBatch.setIsComplete(0);
		timetableBatch = timetableBatchDAO.store(timetableBatch);
		return "success";
	}

	/************************************************************
	 * Description: 完成基础课排课
	 * @author: 郑昕茹
	 * @date: 2017-05-08
	 *********************************************************************************/
	@ResponseBody
	@RequestMapping("/completeDetailTimetable")
	public String completeDetailTimetable(@RequestParam String courseDetailNo){
		// 1、detail置为1  2、全部的timetables tatus置为1
		SchoolCourseDetail detail = schoolCourseDetailDAO.findSchoolCourseDetailByCourseDetailNo(courseDetailNo);
		detail.setIsComplete(1);
		detail = schoolCourseDetailDAO.store(detail);
		List<TimetableAppointment> appointments = newTimetableCourseSchedulingService
				.findTimetableAppointmentsByCourseDetailNo(courseDetailNo);
		for (TimetableAppointment ta : appointments) {
			ta.setStatus(1);
			timetableAppointmentDAO.store(ta);
		}
		return "success";
	}

	/************************************************************
	 * Description: 完成基础课排课
	 * @author: 郑昕茹
	 * @date: 2017-05-08
	 *********************************************************************************/
	@ResponseBody
	@RequestMapping("/modifyDetailTimetable")
	public String modifyDetailTimetable(@RequestParam String courseDetailNo)
			throws ParseException {
		SchoolCourseDetail detail = schoolCourseDetailDAO
				.findSchoolCourseDetailByCourseDetailNo(courseDetailNo);
		detail.setIsComplete(0);
		detail = schoolCourseDetailDAO.store(detail);
		return "success";
	}

	/************************************************************
     * Description: 完成合班排课
     * 
     * @author: 郑昕茹
     * @date: 2017-05-08
     *********************************************************************************/
    @ResponseBody
    @RequestMapping("/completeMergeTimetable")
    public String completeMergeTimetable(@RequestParam Integer mergeId){
        SchoolCourseMerge merge = schoolCourseMergeDAO
                .findSchoolCourseMergeById(mergeId);
        merge.setIsComplete(1);
        merge = schoolCourseMergeDAO.store(merge);
        List<TimetableAppointment> appointments = newTimetableCourseSchedulingService
                .findTimetableAppointmentsByMergeId(mergeId);
        for (TimetableAppointment ta : appointments) {
            ta.setStatus(1);
            timetableAppointmentDAO.store(ta);
        }
        return "success";
    }

	/************************************************************
	 * Description: 修改合班排课
	 * 
	 * @author: 郑昕茹
	 * @date: 2017-05-15
	 *********************************************************************************/
	@ResponseBody
	@RequestMapping("/modifyMergeTimetable")
	public String modifyeMergeTimetable(@RequestParam Integer mergeId)
			throws ParseException {
		SchoolCourseMerge merge = schoolCourseMergeDAO
				.findSchoolCourseMergeById(mergeId);
		merge.setIsComplete(0);
		merge = schoolCourseMergeDAO.store(merge);
		return "success";
	}

	/************************************************************
	 * @throws ParseException
	 * @description：判断当前时间是否在课程开始前
	 * @author：郑昕茹
	 * @date：2017-05-03
	 ************************************************************/
	@RequestMapping("/judgeSelectTimeIsOk")
	public @ResponseBody
	String judgeSelectTimeIsOk(@RequestParam Integer groupId)
			throws ParseException {
		// 获取所有节次
		List<SystemTime> times = timeDetailService.findAllTimes(0, -1, "S");
		Map<String, Calendar> time = new HashMap<String, Calendar>();
		for (SystemTime t : times) {
			time.put(t.getSectionName(), t.getStartDate());
		}

		SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		TimetableGroup timetableGroup = timetableGroupDAO
				.findTimetableGroupById(groupId);
		for (TimetableAppointment ta : timetableGroup
				.getTimetableAppointments()) {
			if (ta.getTimetableAppointmentSameNumbers() == null
					|| ta.getTimetableAppointmentSameNumbers() != null
					&& ta.getTimetableAppointmentSameNumbers().size() == 0) {
				Integer startWeek = ta.getStartWeek();
				Integer weekday = ta.getWeekday();
				Integer startClass = ta.getStartClass();
				SchoolWeek schoolWeek = newTimetableCourseSchedulingService
						.findSchoolWeekByWeekAndWeekdayAndTerm(
								startWeek,
								weekday,
								shareService.getBelongsSchoolTerm(
										Calendar.getInstance()).getId());
				String date = sdfDate.format(schoolWeek.getDate().getTime());
				String classTime = "";
				if (startClass >= 9) {
					classTime = sdfTime.format(time.get("晚").getTime());
				} else {
					classTime = sdfTime.format(time.get(startClass.toString())
							.getTime());
				}
				Calendar startDate = Calendar.getInstance();
				startDate.setTime(sdf.parse(date + " " + classTime));
				if (timetableGroup.getTimetableStyle() == 23)// 公选
				{
					SchoolCourseDetail schoolCourseDetail = schoolCourseDetailDAO
							.findSchoolCourseDetailByCourseDetailNo(timetableGroup
									.getTimetableBatch().getCourseCode());
					if (schoolCourseDetail != null
							&& schoolCourseDetail.getCourseEndMinute() != null) {
						startDate.add(Calendar.MINUTE,
								schoolCourseDetail.getCourseEndMinute());
					}
				}
				if (startDate.before(Calendar.getInstance())) {
					return "-1";
				}
			} else {
				for (TimetableAppointmentSameNumber tas : ta
						.getTimetableAppointmentSameNumbers()) {
					Integer startWeek = tas.getStartWeek();
					Integer weekday = tas.getWeekday();
					Integer startClass = tas.getStartClass();
					SchoolWeek schoolWeek = newTimetableCourseSchedulingService
							.findSchoolWeekByWeekAndWeekdayAndTerm(
									startWeek,
									weekday,
									shareService.getBelongsSchoolTerm(
											Calendar.getInstance()).getId());
					String date = sdfDate
							.format(schoolWeek.getDate().getTime());
					String classTime = "";
					if (startClass >= 9) {
						classTime = sdfTime.format(time.get("晚").getTime());
					} else {
						classTime = sdfTime.format(time.get(
								startClass.toString()).getTime());
					}
					Calendar startDate = Calendar.getInstance();
					startDate.setTime(sdf.parse(date + " " + classTime));
					if (timetableGroup.getTimetableStyle() == 23)// 公选
					{
						SchoolCourseDetail schoolCourseDetail = schoolCourseDetailDAO
								.findSchoolCourseDetailByCourseDetailNo(timetableGroup
										.getTimetableBatch().getCourseCode());
						if (schoolCourseDetail != null
								&& schoolCourseDetail.getCourseEndMinute() != null) {
							startDate.add(Calendar.MINUTE,
									schoolCourseDetail.getCourseEndMinute());
						}
					}
					if (startDate.before(Calendar.getInstance())) {
						return "-1";
					}
				}
			}
		}
		return "ok";
	}

	/************************************************************
	 * Description: 发送基础课发布消息给课程课程学生-学生选课
	 * 
	 * @author: 郑昕茹
	 * @date: 2017-06-04
	 *********************************************************************************/
	@ResponseBody
	@RequestMapping("/sendDetailMessageAll")
	public String sendDetailMessageAll(@RequestParam String courseDetailNo)
			throws ParseException {
		SchoolCourseDetail detail = schoolCourseDetailDAO
				.findSchoolCourseDetailByCourseDetailNo(courseDetailNo);
		// 获取全部学生
		List<String> studentsAll = newTimetableCourseSchedulingService
				.findUsernamesByCourseDetailNo(courseDetailNo);
		for (String student : studentsAll) {
			Message message = new Message();
			message.setSendUser(shareService.getUser().getCname());
			message.setSendCparty(shareService.getUser().getSchoolAcademy()
					.getAcademyName());
			message.setTitle(detail.getSchoolCourse().getCourseNo() + " "
					+ detail.getSchoolCourse().getCourseName() + "课程安排（选课）");
			message.setUsername(student);
			message.setCreateTime(Calendar.getInstance());
			message.setMessageState(0);
			message.setContent("/newtimetable/doStudentCourseSelect?courseDetailNo="
					+ detail.getCourseDetailNo());
			message.setType("timetableStudent");
			messageDAO.store(message);
		}
		User courseTeacher = detail.getUser();
		Message m = new Message();
		m.setSendUser(shareService.getUser().getCname());
		m.setSendCparty(shareService.getUser().getSchoolAcademy()
				.getAcademyName());
		m.setTitle(detail.getSchoolCourse().getCourseNo() + " "
				+ detail.getSchoolCourse().getCourseName() + "课程安排（选课）");
		m.setUsername(courseTeacher.getUsername());
		m.setContent("/timetable/timetableAdminIframeNew?currpage=1&id=-1&status=-1&selectCourseDetailNo="
				+ courseDetailNo);
		m.setType("timetable");
		m.setCreateTime(Calendar.getInstance());
		m.setMessageState(0);
		messageDAO.store(m);

		List<User> admins = shareService.findUsersByAuthority(11);
		for (User admin : admins) {
			Message message = new Message();
			message.setSendUser(shareService.getUser().getCname());
			message.setSendCparty(shareService.getUser().getSchoolAcademy()
					.getAcademyName());
			message.setTitle(detail.getSchoolCourse().getCourseNo() + " "
					+ detail.getSchoolCourse().getCourseName() + "课程安排（选课）");
			message.setUsername(admin.getUsername());
			message.setContent("/timetable/timetableAdminIframeNew?currpage=1&id=-1&status=-1&selectCourseDetailNo="
					+ courseDetailNo);
			message.setType("timetable");
			message.setCreateTime(Calendar.getInstance());
			message.setMessageState(0);
			messageDAO.store(message);
		}
		List<User> labCenterManagers = shareService.findUsersByAuthority(24);
		for (User manager : labCenterManagers) {
			Message message = new Message();
			message.setSendUser(shareService.getUser().getCname());
			message.setSendCparty(shareService.getUser().getSchoolAcademy()
					.getAcademyName());
			message.setTitle(detail.getSchoolCourse().getCourseNo() + " "
					+ detail.getSchoolCourse().getCourseName() + "课程安排（选课）");
			message.setUsername(manager.getUsername());
			message.setContent("/timetable/timetableAdminIframeNew?currpage=1&id=-1&status=-1&selectCourseDetailNo="
					+ courseDetailNo);
			message.setType("timetable");
			message.setCreateTime(Calendar.getInstance());
			message.setMessageState(0);
			messageDAO.store(message);
		}
		return "success";
	}

	/************************************************************
	 * Description: 发送基础课发布消息给课程课程学生-学生选课
	 * 
	 * @author: 郑昕茹
	 * @date: 2017-06-04
	 *********************************************************************************/
	@ResponseBody
	@RequestMapping("/sendDetailMessageByBatch")
	public String sendDetailMessageByBacth(@RequestParam String courseDetailNo,
			@RequestParam Integer batchId) throws ParseException {
		SchoolCourseDetail detail = schoolCourseDetailDAO
				.findSchoolCourseDetailByCourseDetailNo(courseDetailNo);
		TimetableBatch timetableBatch = timetableBatchDAO
				.findTimetableBatchById(batchId);
		// 获取全部学生
		List<String> studentsAll = newTimetableCourseSchedulingService
				.findUsernamesByCourseDetailNo(courseDetailNo);
		for (String student : studentsAll) {
			Message message = new Message();
			message.setSendUser(shareService.getUser().getCname());
			message.setSendCparty(shareService.getUser().getSchoolAcademy()
					.getAcademyName());
			message.setTitle(detail.getSchoolCourse().getCourseNo() + " "
					+ detail.getSchoolCourse().getCourseName() + "课程-"
					+ timetableBatch.getOperationItem().getLpName() + "安排（选课）");
			message.setUsername(student);
			message.setCreateTime(Calendar.getInstance());
			message.setMessageState(0);
			message.setContent("/newtimetable/doStudentCourseSelect?courseDetailNo="
					+ detail.getCourseDetailNo());
			message.setType("timetableStudent");
			messageDAO.store(message);
		}
		User courseTeacher = detail.getUser();
		Message m = new Message();
		m.setSendUser(shareService.getUser().getCname());
		m.setSendCparty(shareService.getUser().getSchoolAcademy()
				.getAcademyName());
		m.setTitle(detail.getSchoolCourse().getCourseNo() + " "
				+ detail.getSchoolCourse().getCourseName() + "课程-"
				+ timetableBatch.getOperationItem().getLpName() + "安排（选课）");
		m.setUsername(courseTeacher.getUsername());
		m.setContent("/timetable/timetableAdminIframeNew?currpage=1&id=-1&status=-1&selectCourseDetailNo="
				+ courseDetailNo);
		m.setType("timetable");
		m.setCreateTime(Calendar.getInstance());
		m.setMessageState(0);
		messageDAO.store(m);

		List<User> admins = shareService.findUsersByAuthority(11);
		for (User admin : admins) {
			Message message = new Message();
			message.setSendUser(shareService.getUser().getCname());
			message.setSendCparty(shareService.getUser().getSchoolAcademy()
					.getAcademyName());
			message.setTitle(detail.getSchoolCourse().getCourseNo() + " "
					+ detail.getSchoolCourse().getCourseName() + "课程-"
					+ timetableBatch.getOperationItem().getLpName() + "安排（选课）");
			message.setUsername(admin.getUsername());
			message.setContent("/timetable/timetableAdminIframeNew?currpage=1&id=-1&status=-1&selectCourseDetailNo="
					+ courseDetailNo);
			message.setType("timetable");
			message.setCreateTime(Calendar.getInstance());
			message.setMessageState(0);
			messageDAO.store(message);
		}
		List<User> labCenterManagers = shareService.findUsersByAuthority(24);
		for (User manager : labCenterManagers) {
			Message message = new Message();
			message.setSendUser(shareService.getUser().getCname());
			message.setSendCparty(shareService.getUser().getSchoolAcademy()
					.getAcademyName());
			message.setTitle(detail.getSchoolCourse().getCourseNo() + " "
					+ detail.getSchoolCourse().getCourseName() + "课程-"
					+ timetableBatch.getOperationItem().getLpName() + "安排（选课）");
			message.setUsername(manager.getUsername());
			message.setContent("/timetable/timetableAdminIframeNew?currpage=1&id=-1&status=-1&selectCourseDetailNo="
					+ courseDetailNo);
			message.setType("timetable");
			message.setCreateTime(Calendar.getInstance());
			message.setMessageState(0);
			messageDAO.store(message);
		}
		return "success";
	}

	/************************************************************
	 * Description: 发送基础课发布消息给课程课程学生-非学生选课
	 * 
	 * @author: 郑昕茹
	 * @date: 2017-06-04
	 *********************************************************************************/
	@ResponseBody
	@RequestMapping("/sendMessageAllNotSelfSelect")
	public String sendMessageAllNotSelfSelect(
			@RequestParam String courseDetailNo) throws ParseException {
		SchoolCourseDetail detail = schoolCourseDetailDAO
				.findSchoolCourseDetailByCourseDetailNo(courseDetailNo);
		// 获取全部学生
		List<String> studentsAll = newTimetableCourseSchedulingService
				.findUsernamesByCourseDetailNo(courseDetailNo);
		for (String student : studentsAll) {
			Message message = new Message();
			message.setSendUser(shareService.getUser().getCname());
			message.setSendCparty(shareService.getUser().getSchoolAcademy()
					.getAcademyName());
			message.setTitle(detail.getSchoolCourse().getCourseNo() + " "
					+ detail.getSchoolCourse().getCourseName() + "课程安排");
			message.setUsername(student);
			message.setCreateTime(Calendar.getInstance());
			message.setMessageState(0);
			message.setContent("/newtimetable/myTimetableStudent?courseNo="
					+ detail.getSchoolCourse().getCourseNo());
			message.setType("timetableStudent");
			messageDAO.store(message);
		}
		User courseTeacher = detail.getUser();
		Message m = new Message();
		m.setSendUser(shareService.getUser().getCname());
		m.setSendCparty(shareService.getUser().getSchoolAcademy()
				.getAcademyName());
		m.setTitle(detail.getSchoolCourse().getCourseNo() + " "
				+ detail.getSchoolCourse().getCourseName() + "课程安排");
		m.setUsername(courseTeacher.getUsername());
		m.setContent("/timetable/timetableAdminIframeNew?currpage=1&id=-1&status=-1&selectCourseDetailNo="
				+ courseDetailNo);
		m.setType("timetable");
		m.setCreateTime(Calendar.getInstance());
		m.setMessageState(0);
		messageDAO.store(m);

		List<User> admins = shareService.findUsersByAuthority(11);
		for (User admin : admins) {
			Message message = new Message();
			message.setSendUser(shareService.getUser().getCname());
			message.setSendCparty(shareService.getUser().getSchoolAcademy()
					.getAcademyName());
			message.setTitle(detail.getSchoolCourse().getCourseNo() + " "
					+ detail.getSchoolCourse().getCourseName() + "课程安排");
			message.setUsername(admin.getUsername());
			message.setContent("/timetable/timetableAdminIframeNew?currpage=1&id=-1&status=-1&selectCourseDetailNo="
					+ courseDetailNo);
			message.setType("timetable");
			message.setCreateTime(Calendar.getInstance());
			message.setMessageState(0);
			messageDAO.store(message);
		}
		List<User> labCenterManagers = shareService.findUsersByAuthority(24);
		for (User manager : labCenterManagers) {
			Message message = new Message();
			message.setSendUser(shareService.getUser().getCname());
			message.setSendCparty(shareService.getUser().getSchoolAcademy()
					.getAcademyName());
			message.setTitle(detail.getSchoolCourse().getCourseNo() + " "
					+ detail.getSchoolCourse().getCourseName() + "课程安排）");
			message.setUsername(manager.getUsername());
			message.setContent("/timetable/timetableAdminIframeNew?currpage=1&id=-1&status=-1&selectCourseDetailNo="
					+ courseDetailNo);
			message.setType("timetable");
			message.setCreateTime(Calendar.getInstance());
			message.setMessageState(0);
			messageDAO.store(message);
		}
		return "success";
	}

	/************************************************************
	 * Description: 发送基础课发布消息给课程课程学生-非学生选课
	 * 
	 * @author: 郑昕茹
	 * @date: 2017-06-04
	 *********************************************************************************/
	@ResponseBody
	@RequestMapping("/sendMessageByBacthNotSelfSelect")
	public String sendMessageByBacthNotSelfSelect(
			@RequestParam String courseDetailNo, @RequestParam Integer batchId)
			throws ParseException {
		TimetableBatch timetableBatch = timetableBatchDAO
				.findTimetableBatchById(batchId);
		SchoolCourseDetail detail = schoolCourseDetailDAO
				.findSchoolCourseDetailByCourseDetailNo(courseDetailNo);
		// 获取全部学生
		List<String> studentsAll = newTimetableCourseSchedulingService
				.findUsernamesByCourseDetailNo(courseDetailNo);
		for (String student : studentsAll) {
			Message message = new Message();
			message.setSendUser(shareService.getUser().getCname());
			message.setSendCparty(shareService.getUser().getSchoolAcademy()
					.getAcademyName());
			message.setTitle(detail.getSchoolCourse().getCourseNo() + " "
					+ detail.getSchoolCourse().getCourseName() + "课程-"
					+ timetableBatch.getOperationItem().getLpName() + "安排");
			message.setUsername(student);
			message.setCreateTime(Calendar.getInstance());
			message.setMessageState(0);
			message.setContent("/newtimetable/myTimetableStudent?courseNo="
					+ detail.getSchoolCourse().getCourseNo());
			message.setType("timetableStudent");
			messageDAO.store(message);
		}
		User courseTeacher = detail.getUser();
		Message m = new Message();
		m.setSendUser(shareService.getUser().getCname());
		m.setSendCparty(shareService.getUser().getSchoolAcademy()
				.getAcademyName());
		m.setTitle(detail.getSchoolCourse().getCourseNo() + " "
				+ detail.getSchoolCourse().getCourseName() + "课程-"
				+ timetableBatch.getOperationItem().getLpName() + "安排");
		m.setUsername(courseTeacher.getUsername());
		m.setContent("/timetable/timetableAdminIframeNew?currpage=1&id=-1&status=-1&selectCourseDetailNo="
				+ courseDetailNo);
		m.setType("timetable");
		m.setCreateTime(Calendar.getInstance());
		m.setMessageState(0);
		messageDAO.store(m);

		List<User> admins = shareService.findUsersByAuthority(11);
		for (User admin : admins) {
			Message message = new Message();
			message.setSendUser(shareService.getUser().getCname());
			message.setSendCparty(shareService.getUser().getSchoolAcademy()
					.getAcademyName());
			message.setTitle(detail.getSchoolCourse().getCourseNo() + " "
					+ detail.getSchoolCourse().getCourseName() + "课程-"
					+ timetableBatch.getOperationItem().getLpName() + "安排）");
			message.setUsername(admin.getUsername());
			message.setContent("/timetable/timetableAdminIframeNew?currpage=1&id=-1&status=-1&selectCourseDetailNo="
					+ courseDetailNo);
			message.setType("timetable");
			message.setCreateTime(Calendar.getInstance());
			message.setMessageState(0);
			messageDAO.store(message);
		}
		List<User> labCenterManagers = shareService.findUsersByAuthority(24);
		for (User manager : labCenterManagers) {
			Message message = new Message();
			message.setSendUser(shareService.getUser().getCname());
			message.setSendCparty(shareService.getUser().getSchoolAcademy()
					.getAcademyName());
			message.setTitle(detail.getSchoolCourse().getCourseNo() + " "
					+ detail.getSchoolCourse().getCourseName() + "课程-"
					+ timetableBatch.getOperationItem().getLpName() + "安排");
			message.setUsername(manager.getUsername());
			message.setContent("/timetable/timetableAdminIframeNew?currpage=1&id=-1&status=-1&selectCourseDetailNo="
					+ courseDetailNo);
			message.setType("timetable");
			message.setCreateTime(Calendar.getInstance());
			message.setMessageState(0);
			messageDAO.store(message);
		}
		return "success";
	}

	/************************************************************
	 * Description: 发送合班课发布消息给课程课程学生-学生选课
	 * 
	 * @author: 郑昕茹
	 * @date: 2017-06-04
	 *********************************************************************************/
	@ResponseBody
	@RequestMapping("/sendMergeMessageAll")
	public String sendMergeMessageAll(@RequestParam Integer mergeId)
			throws ParseException {
		SchoolCourseMerge schoolCourseMerge = schoolCourseMergeDAO
				.findSchoolCourseMergeById(mergeId);
		// 获取全部学生
		List<String> studentsAll = newTimetableCourseSchedulingService
				.findUsersNameByMergeId(mergeId.toString(), null);
		for (String student : studentsAll) {
			Message message = new Message();
			message.setSendUser(shareService.getUser().getCname());
			message.setSendCparty(shareService.getUser().getSchoolAcademy()
					.getAcademyName());
			message.setTitle(schoolCourseMerge.getCourseNo() + " "
					+ schoolCourseMerge.getCourseName() + "课程安排（选课）");
			message.setUsername(student);
			message.setCreateTime(Calendar.getInstance());
			message.setMessageState(0);
			message.setContent("/newtimetable/doStudentCourseSelect?mergeId="
					+ schoolCourseMerge.getId());
			message.setType("timetableStudent");
			messageDAO.store(message);
		}
		User courseTeacher = schoolCourseMerge.getSchoolCourseDetails()
				.iterator().next().getUser();
		Message m = new Message();
		m.setSendUser(shareService.getUser().getCname());
		m.setSendCparty(shareService.getUser().getSchoolAcademy()
				.getAcademyName());
		m.setTitle(schoolCourseMerge.getCourseNo() + " "
				+ schoolCourseMerge.getCourseName() + "课程安排（选课）");
		m.setUsername(courseTeacher.getUsername());
		m.setContent("/timetable/timetableAdminIframeNew?currpage=1&id=-1&status=-1&selectMergeId="
				+ mergeId);
		m.setType("timetable");
		m.setCreateTime(Calendar.getInstance());
		m.setMessageState(0);
		messageDAO.store(m);

		List<User> admins = shareService.findUsersByAuthority(11);
		for (User admin : admins) {
			Message message = new Message();
			message.setSendUser(shareService.getUser().getCname());
			message.setSendCparty(shareService.getUser().getSchoolAcademy()
					.getAcademyName());
			message.setTitle(schoolCourseMerge.getCourseNo() + " "
					+ schoolCourseMerge.getCourseName() + "课程安排（选课）");
			message.setUsername(admin.getUsername());
			message.setContent("/timetable/timetableAdminIframeNew?currpage=1&id=-1&status=-1&selectMergeId="
					+ mergeId);
			message.setType("timetable");
			message.setCreateTime(Calendar.getInstance());
			message.setMessageState(0);
			messageDAO.store(message);
		}
		List<User> labCenterManagers = shareService.findUsersByAuthority(24);
		for (User manager : labCenterManagers) {
			Message message = new Message();
			message.setSendUser(shareService.getUser().getCname());
			message.setSendCparty(shareService.getUser().getSchoolAcademy()
					.getAcademyName());
			message.setTitle(schoolCourseMerge.getCourseNo() + " "
					+ schoolCourseMerge.getCourseName() + "课程安排（选课）");
			message.setUsername(manager.getUsername());
			message.setContent("/timetable/timetableAdminIframeNew?currpage=1&id=-1&status=-1&selectMergeId="
					+ mergeId);
			message.setType("timetable");
			message.setCreateTime(Calendar.getInstance());
			message.setMessageState(0);
			messageDAO.store(message);
		}
		return "success";
	}

	/************************************************************
	 * Description: 发送合班课发布消息给课程课程学生-非学生选课
	 * 
	 * @author: 郑昕茹
	 * @date: 2017-06-04
	 *********************************************************************************/
	@ResponseBody
	@RequestMapping("/sendMergeMessageAllNotSelfSelect")
	public String sendMergeMessageAllNotSelfSelect(@RequestParam Integer mergeId)
			throws ParseException {
		SchoolCourseMerge schoolCourseMerge = schoolCourseMergeDAO
				.findSchoolCourseMergeById(mergeId);
		// 获取全部学生
		List<String> studentsAll = newTimetableCourseSchedulingService
				.findUsersNameByMergeId(mergeId.toString(), null);
		for (String student : studentsAll) {
			Message message = new Message();
			message.setSendUser(shareService.getUser().getCname());
			message.setSendCparty(shareService.getUser().getSchoolAcademy()
					.getAcademyName());
			message.setTitle(schoolCourseMerge.getCourseNo() + " "
					+ schoolCourseMerge.getCourseName() + "课程安排）");
			message.setUsername(student);
			message.setCreateTime(Calendar.getInstance());
			message.setMessageState(0);
			message.setContent("/newtimetable/myTimetableStudent?courseNo="
					+ schoolCourseMerge.getCourseNo());
			message.setType("timetableStudent");
			messageDAO.store(message);
		}
		User courseTeacher = schoolCourseMerge.getSchoolCourseDetails()
				.iterator().next().getUser();
		Message m = new Message();
		m.setSendUser(shareService.getUser().getCname());
		m.setSendCparty(shareService.getUser().getSchoolAcademy()
				.getAcademyName());
		m.setTitle(schoolCourseMerge.getCourseNo() + " "
				+ schoolCourseMerge.getCourseName() + "课程安排");
		m.setUsername(courseTeacher.getUsername());
		m.setContent("/timetable/timetableAdminIframeNew?currpage=1&id=-1&status=-1&selectMergeId="
				+ mergeId);
		m.setType("timetable");
		m.setCreateTime(Calendar.getInstance());
		m.setMessageState(0);
		messageDAO.store(m);

		List<User> admins = shareService.findUsersByAuthority(11);
		for (User admin : admins) {
			Message message = new Message();
			message.setSendUser(shareService.getUser().getCname());
			message.setSendCparty(shareService.getUser().getSchoolAcademy()
					.getAcademyName());
			message.setTitle(schoolCourseMerge.getCourseNo() + " "
					+ schoolCourseMerge.getCourseName() + "课程安排）");
			message.setUsername(admin.getUsername());
			message.setContent("/timetable/timetableAdminIframeNew?currpage=1&id=-1&status=-1&selectMergeId="
					+ mergeId);
			message.setType("timetable");
			message.setCreateTime(Calendar.getInstance());
			message.setMessageState(0);
			messageDAO.store(message);
		}
		List<User> labCenterManagers = shareService.findUsersByAuthority(24);
		for (User manager : labCenterManagers) {
			Message message = new Message();
			message.setSendUser(shareService.getUser().getCname());
			message.setSendCparty(shareService.getUser().getSchoolAcademy()
					.getAcademyName());
			message.setTitle(schoolCourseMerge.getCourseNo() + " "
					+ schoolCourseMerge.getCourseName() + "课程安排）");
			message.setUsername(manager.getUsername());
			message.setContent("/timetable/timetableAdminIframeNew?currpage=1&id=-1&status=-1&selectMergeId="
					+ mergeId);
			message.setType("timetable");
			message.setCreateTime(Calendar.getInstance());
			message.setMessageState(0);
			messageDAO.store(message);
		}
		return "success";
	}

	/************************************************************
	 * Description: 发送合班课发布消息给课程课程学生-学生选课
	 * 
	 * @author: 郑昕茹
	 * @date: 2017-06-04
	 *********************************************************************************/
	@ResponseBody
	@RequestMapping("/sendMergeByBatchMessage")
	public String sendMergeByBatchMessage(@RequestParam Integer mergeId,
			@RequestParam Integer batchId) throws ParseException {
		SchoolCourseMerge schoolCourseMerge = schoolCourseMergeDAO
				.findSchoolCourseMergeById(mergeId);
		TimetableBatch timetableBatch = timetableBatchDAO
				.findTimetableBatchById(batchId);
		// 获取全部学生
		List<String> studentsAll = newTimetableCourseSchedulingService
				.findUsersNameByMergeId(mergeId.toString(), null);
		for (String student : studentsAll) {
			Message message = new Message();
			message.setSendUser(shareService.getUser().getCname());
			message.setSendCparty(shareService.getUser().getSchoolAcademy()
					.getAcademyName());
			message.setTitle(schoolCourseMerge.getCourseNo() + " "
					+ schoolCourseMerge.getCourseName() + "课程-"
					+ timetableBatch.getOperationItem().getLpName() + "安排（选课）");
			message.setUsername(student);
			message.setCreateTime(Calendar.getInstance());
			message.setMessageState(0);
			message.setContent("/newtimetable/doStudentCourseSelect?mergeId="
					+ schoolCourseMerge.getId());
			message.setType("timetableStudent");
			messageDAO.store(message);
		}
		User courseTeacher = schoolCourseMerge.getSchoolCourseDetails()
				.iterator().next().getUser();
		Message m = new Message();
		m.setSendUser(shareService.getUser().getCname());
		m.setSendCparty(shareService.getUser().getSchoolAcademy()
				.getAcademyName());
		m.setTitle(schoolCourseMerge.getCourseNo() + " "
				+ schoolCourseMerge.getCourseName() + "课程-"
				+ timetableBatch.getOperationItem().getLpName() + "安排（选课）");
		m.setUsername(courseTeacher.getUsername());
		m.setContent("/timetable/timetableAdminIframeNew?currpage=1&id=-1&status=-1&selectMergeId="
				+ mergeId);
		m.setType("timetable");
		m.setCreateTime(Calendar.getInstance());
		m.setMessageState(0);
		messageDAO.store(m);

		List<User> admins = shareService.findUsersByAuthority(11);
		for (User admin : admins) {
			Message message = new Message();
			message.setSendUser(shareService.getUser().getCname());
			message.setSendCparty(shareService.getUser().getSchoolAcademy()
					.getAcademyName());
			message.setTitle(schoolCourseMerge.getCourseNo() + " "
					+ schoolCourseMerge.getCourseName() + "课程-"
					+ timetableBatch.getOperationItem().getLpName() + "安排（选课）");
			message.setUsername(admin.getUsername());
			message.setContent("/timetable/timetableAdminIframeNew?currpage=1&id=-1&status=-1&selectMergeId="
					+ mergeId);
			message.setType("timetable");
			message.setCreateTime(Calendar.getInstance());
			message.setMessageState(0);
			messageDAO.store(message);
		}
		List<User> labCenterManagers = shareService.findUsersByAuthority(24);
		for (User manager : labCenterManagers) {
			Message message = new Message();
			message.setSendUser(shareService.getUser().getCname());
			message.setSendCparty(shareService.getUser().getSchoolAcademy()
					.getAcademyName());
			message.setTitle(schoolCourseMerge.getCourseNo() + " "
					+ schoolCourseMerge.getCourseName() + "课程-"
					+ timetableBatch.getOperationItem().getLpName() + "安排（选课）");
			message.setUsername(manager.getUsername());
			message.setContent("/timetable/timetableAdminIframeNew?currpage=1&id=-1&status=-1&selectMergeId="
					+ mergeId);
			message.setType("timetable");
			message.setCreateTime(Calendar.getInstance());
			message.setMessageState(0);
			messageDAO.store(message);
		}
		return "success";
	}

	/************************************************************
	 * Description: 发送合班课发布消息给课程课程学生-非学生选课
	 * 
	 * @author: 郑昕茹
	 * @date: 2017-06-04
	 *********************************************************************************/
	@ResponseBody
	@RequestMapping("/sendMergeMessageByBatchNotSelfSelect")
	public String sendMergeMessageByBatchNotSelfSelect(
			@RequestParam Integer mergeId, @RequestParam Integer batchId)
			throws ParseException {
		SchoolCourseMerge schoolCourseMerge = schoolCourseMergeDAO
				.findSchoolCourseMergeById(mergeId);
		TimetableBatch timetableBatch = timetableBatchDAO
				.findTimetableBatchById(batchId);
		// 获取全部学生
		List<String> studentsAll = newTimetableCourseSchedulingService
				.findUsersNameByMergeId(mergeId.toString(), null);
		for (String student : studentsAll) {
			Message message = new Message();
			message.setSendUser(shareService.getUser().getCname());
			message.setSendCparty(shareService.getUser().getSchoolAcademy()
					.getAcademyName());
			message.setTitle(schoolCourseMerge.getCourseNo() + " "
					+ schoolCourseMerge.getCourseName() + "课程-"
					+ timetableBatch.getOperationItem().getLpName() + "安排");
			message.setUsername(student);
			message.setCreateTime(Calendar.getInstance());
			message.setMessageState(0);
			message.setContent("/newtimetable/myTimetableStudent?courseNo="
					+ schoolCourseMerge.getCourseNo());
			message.setType("timetableStudent");
			messageDAO.store(message);
		}
		User courseTeacher = schoolCourseMerge.getSchoolCourseDetails()
				.iterator().next().getUser();
		Message m = new Message();
		m.setSendUser(shareService.getUser().getCname());
		m.setSendCparty(shareService.getUser().getSchoolAcademy()
				.getAcademyName());
		m.setTitle(schoolCourseMerge.getCourseNo() + " "
				+ schoolCourseMerge.getCourseName() + "课程-"
				+ timetableBatch.getOperationItem().getLpName() + "安排");
		m.setUsername(courseTeacher.getUsername());
		m.setContent("/timetable/timetableAdminIframeNew?currpage=1&id=-1&status=-1&selectMergeId="
				+ mergeId);
		m.setType("timetable");
		m.setCreateTime(Calendar.getInstance());
		m.setMessageState(0);
		messageDAO.store(m);

		List<User> admins = shareService.findUsersByAuthority(11);
		for (User admin : admins) {
			Message message = new Message();
			message.setSendUser(shareService.getUser().getCname());
			message.setSendCparty(shareService.getUser().getSchoolAcademy()
					.getAcademyName());
			message.setTitle(schoolCourseMerge.getCourseNo() + " "
					+ schoolCourseMerge.getCourseName() + "课程-"
					+ timetableBatch.getOperationItem().getLpName() + "安排");
			message.setUsername(admin.getUsername());
			message.setContent("/timetable/timetableAdminIframeNew?currpage=1&id=-1&status=-1&selectMergeId="
					+ mergeId);
			message.setType("timetable");
			message.setCreateTime(Calendar.getInstance());
			message.setMessageState(0);
			messageDAO.store(message);
		}
		List<User> labCenterManagers = shareService.findUsersByAuthority(24);
		for (User manager : labCenterManagers) {
			Message message = new Message();
			message.setSendUser(shareService.getUser().getCname());
			message.setSendCparty(shareService.getUser().getSchoolAcademy()
					.getAcademyName());
			message.setTitle(schoolCourseMerge.getCourseNo() + " "
					+ schoolCourseMerge.getCourseName() + "课程-"
					+ timetableBatch.getOperationItem().getLpName() + "安排（选课）");
			message.setUsername(manager.getUsername());
			message.setContent("/timetable/timetableAdminIframeNew?currpage=1&id=-1&status=-1&selectMergeId="
					+ mergeId);
			message.setType("timetable");
			message.setCreateTime(Calendar.getInstance());
			message.setMessageState(0);
			messageDAO.store(message);
		}
		return "success";
	}

	/************************************************************
	 * Description: 发送基础课发布消息给课程课程学生-学生选课
	 * 
	 * @author: 郑昕茹
	 * @date: 2017-06-04
	 *********************************************************************************/
	@ResponseBody
	@RequestMapping("/sendDetailMessageByGroup")
	public String sendDetailMessageByGroup(@RequestParam String[] groups,
			@RequestParam String courseDetailNo) throws ParseException {
		SchoolCourseDetail detail = schoolCourseDetailDAO
				.findSchoolCourseDetailByCourseDetailNo(courseDetailNo);
		for (String groupId : groups) {
			TimetableGroup timetableGroup = timetableGroupDAO
					.findTimetableGroupById(Integer.parseInt(groupId));
			List<String> studentsAll = newTimetableCourseSchedulingService
					.findUsernamesByGroupId(Integer.parseInt(groupId));
			for (String student : studentsAll) {
				Message message = new Message();
				message.setSendUser(shareService.getUser().getCname());
				message.setSendCparty(shareService.getUser().getSchoolAcademy()
						.getAcademyName());
				message.setTitle(detail.getSchoolCourse().getCourseNo()
						+ " "
						+ detail.getSchoolCourse().getCourseName()
						+ "课程-"
						+ timetableGroup.getTimetableBatch().getOperationItem()
								.getLpName() + "-"
						+ timetableGroup.getGroupName() + "安排（选课）");
				message.setUsername(student);
				message.setCreateTime(Calendar.getInstance());
				message.setMessageState(0);
				message.setContent("/newtimetable/doStudentCourseSelect?courseDetailNo="
						+ detail.getCourseDetailNo());
				message.setType("timetableStudent");
				messageDAO.store(message);
			}
			User courseTeacher = detail.getUser();
			Message m = new Message();
			m.setSendUser(shareService.getUser().getCname());
			m.setSendCparty(shareService.getUser().getSchoolAcademy()
					.getAcademyName());
			m.setTitle(detail.getSchoolCourse().getCourseNo()
					+ " "
					+ detail.getSchoolCourse().getCourseName()
					+ "课程-"
					+ timetableGroup.getTimetableBatch().getOperationItem()
							.getLpName() + "-" + timetableGroup.getGroupName()
					+ "安排（选课）");
			m.setUsername(courseTeacher.getUsername());
			m.setContent("/timetable/timetableAdminIframeNew?currpage=1&id=-1&status=-1&selectCourseDetailNo="
					+ courseDetailNo);
			m.setType("timetable");
			m.setCreateTime(Calendar.getInstance());
			m.setMessageState(0);
			messageDAO.store(m);

			List<User> admins = shareService.findUsersByAuthority(11);
			for (User admin : admins) {
				Message message = new Message();
				message.setSendUser(shareService.getUser().getCname());
				message.setSendCparty(shareService.getUser().getSchoolAcademy()
						.getAcademyName());
				message.setTitle(detail.getSchoolCourse().getCourseNo()
						+ " "
						+ detail.getSchoolCourse().getCourseName()
						+ "课程-"
						+ timetableGroup.getTimetableBatch().getOperationItem()
								.getLpName() + "-"
						+ timetableGroup.getGroupName() + "安排（选课）");
				message.setUsername(admin.getUsername());
				message.setContent("/timetable/timetableAdminIframeNew?currpage=1&id=-1&status=-1&selectCourseDetailNo="
						+ courseDetailNo);
				message.setType("timetable");
				message.setCreateTime(Calendar.getInstance());
				message.setMessageState(0);
				messageDAO.store(message);
			}
			List<User> labCenterManagers = shareService
					.findUsersByAuthority(24);
			for (User manager : labCenterManagers) {
				Message message = new Message();
				message.setSendUser(shareService.getUser().getCname());
				message.setSendCparty(shareService.getUser().getSchoolAcademy()
						.getAcademyName());
				message.setTitle(detail.getSchoolCourse().getCourseNo()
						+ " "
						+ detail.getSchoolCourse().getCourseName()
						+ "课程-"
						+ timetableGroup.getTimetableBatch().getOperationItem()
								.getLpName() + "-"
						+ timetableGroup.getGroupName() + "安排（选课）");
				message.setUsername(manager.getUsername());
				message.setContent("/timetable/timetableAdminIframeNew?currpage=1&id=-1&status=-1&selectCourseDetailNo="
						+ courseDetailNo);
				message.setType("timetable");
				message.setCreateTime(Calendar.getInstance());
				message.setMessageState(0);
				messageDAO.store(message);
			}
		}
		return "success";
	}

	/************************************************************
	 * Description: 发送基础课发布消息给课程课程学生-学生选课
	 * 
	 * @author: 郑昕茹
	 * @date: 2017-06-04
	 *********************************************************************************/
	@ResponseBody
	@RequestMapping("/sendDetailMessageByGroupNotSelfSelect")
	public String sendDetailMessageByGroupNotSelfSelect(
			@RequestParam String[] groups, @RequestParam String courseDetailNo)
			throws ParseException {
		SchoolCourseDetail detail = schoolCourseDetailDAO
				.findSchoolCourseDetailByCourseDetailNo(courseDetailNo);
		for (String groupId : groups) {
			TimetableGroup timetableGroup = timetableGroupDAO
					.findTimetableGroupById(Integer.parseInt(groupId));
			List<String> studentsAll = newTimetableCourseSchedulingService
					.findUsernamesByGroupId(Integer.parseInt(groupId));
			for (String student : studentsAll) {
				Message message = new Message();
				message.setSendUser(shareService.getUser().getCname());
				message.setSendCparty(shareService.getUser().getSchoolAcademy()
						.getAcademyName());
				if (timetableGroup.getTimetableBatch().getOperationItem() != null) {
					message.setTitle(detail.getSchoolCourse().getCourseNo()
							+ " "
							+ detail.getSchoolCourse().getCourseName()
							+ "课程-"
							+ timetableGroup.getTimetableBatch()
									.getOperationItem().getLpName() + "-"
							+ timetableGroup.getGroupName() + "安排");
				} else {
					message.setTitle(detail.getSchoolCourse().getCourseNo()
							+ " " + detail.getSchoolCourse().getCourseName()
							+ "课程" + "-" + timetableGroup.getGroupName() + "安排");
				}
				message.setUsername(student);
				message.setCreateTime(Calendar.getInstance());
				message.setMessageState(0);
				message.setContent("/newtimetable/myTimetableStudent?courseNo="
						+ detail.getSchoolCourse().getCourseNo());
				message.setType("timetableStudent");
				messageDAO.store(message);
			}
			User courseTeacher = detail.getUser();
			Message m = new Message();
			m.setSendUser(shareService.getUser().getCname());
			m.setSendCparty(shareService.getUser().getSchoolAcademy()
					.getAcademyName());
			if (timetableGroup.getTimetableBatch().getOperationItem() != null) {
				m.setTitle(detail.getSchoolCourse().getCourseNo()
						+ " "
						+ detail.getSchoolCourse().getCourseName()
						+ "课程-"
						+ timetableGroup.getTimetableBatch().getOperationItem()
								.getLpName() + "-"
						+ timetableGroup.getGroupName() + "安排");
			} else {
				m.setTitle(detail.getSchoolCourse().getCourseNo() + " "
						+ detail.getSchoolCourse().getCourseName() + "课程" + "-"
						+ timetableGroup.getGroupName() + "安排");
			}
			m.setUsername(courseTeacher.getUsername());
			m.setContent("/timetable/timetableAdminIframeNew?currpage=1&id=-1&status=-1&selectCourseDetailNo="
					+ courseDetailNo);
			m.setType("timetable");
			m.setCreateTime(Calendar.getInstance());
			m.setMessageState(0);
			messageDAO.store(m);

			List<User> admins = shareService.findUsersByAuthority(11);
			for (User admin : admins) {
				Message message = new Message();
				message.setSendUser(shareService.getUser().getCname());
				message.setSendCparty(shareService.getUser().getSchoolAcademy()
						.getAcademyName());
				if (timetableGroup.getTimetableBatch().getOperationItem() != null) {
					message.setTitle(detail.getSchoolCourse().getCourseNo()
							+ " "
							+ detail.getSchoolCourse().getCourseName()
							+ "课程-"
							+ timetableGroup.getTimetableBatch()
									.getOperationItem().getLpName() + "-"
							+ timetableGroup.getGroupName() + "安排");
				} else {
					message.setTitle(detail.getSchoolCourse().getCourseNo()
							+ " " + detail.getSchoolCourse().getCourseName()
							+ "课程" + "-" + timetableGroup.getGroupName() + "安排");
				}
				message.setUsername(admin.getUsername());
				message.setContent("/timetable/timetableAdminIframeNew?currpage=1&id=-1&status=-1&selectCourseDetailNo="
						+ courseDetailNo);
				message.setType("timetable");
				message.setCreateTime(Calendar.getInstance());
				message.setMessageState(0);
				messageDAO.store(message);
			}
			List<User> labCenterManagers = shareService
					.findUsersByAuthority(24);
			for (User manager : labCenterManagers) {
				Message message = new Message();
				message.setSendUser(shareService.getUser().getCname());
				message.setSendCparty(shareService.getUser().getSchoolAcademy()
						.getAcademyName());
				if (timetableGroup.getTimetableBatch().getOperationItem() != null) {
					message.setTitle(detail.getSchoolCourse().getCourseNo()
							+ " "
							+ detail.getSchoolCourse().getCourseName()
							+ "课程-"
							+ timetableGroup.getTimetableBatch()
									.getOperationItem().getLpName() + "-"
							+ timetableGroup.getGroupName() + "安排");
				} else {
					message.setTitle(detail.getSchoolCourse().getCourseNo()
							+ " " + detail.getSchoolCourse().getCourseName()
							+ "课程" + "-" + timetableGroup.getGroupName() + "安排");
				}
				message.setUsername(manager.getUsername());
				message.setContent("/timetable/timetableAdminIframeNew?currpage=1&id=-1&status=-1&selectCourseDetailNo="
						+ courseDetailNo);
				message.setType("timetable");
				message.setCreateTime(Calendar.getInstance());
				message.setMessageState(0);
				messageDAO.store(message);
			}
		}
		return "success";
	}

	/************************************************************
	 * Description: 发送基础课发布消息给课程课程学生-学生选课
	 * 
	 * @author: 郑昕茹
	 * @date: 2017-06-04
	 *********************************************************************************/
	@ResponseBody
	@RequestMapping("/sendMergeMessageByGroup")
	public String sendMergeMessageByGroup(@RequestParam String[] groups,
			@RequestParam Integer mergeId) throws ParseException {
		SchoolCourseMerge schoolCourseMerge = schoolCourseMergeDAO
				.findSchoolCourseMergeById(mergeId);
		for (String groupId : groups) {
			TimetableGroup timetableGroup = timetableGroupDAO
					.findTimetableGroupById(Integer.parseInt(groupId));
			List<String> studentsAll = newTimetableCourseSchedulingService
					.findUsernamesByGroupId(Integer.parseInt(groupId));
			for (String student : studentsAll) {
				Message message = new Message();
				message.setSendUser(shareService.getUser().getCname());
				message.setSendCparty(shareService.getUser().getSchoolAcademy()
						.getAcademyName());
				message.setTitle(schoolCourseMerge.getCourseNo()
						+ " "
						+ schoolCourseMerge.getCourseName()
						+ "课程-"
						+ timetableGroup.getTimetableBatch().getOperationItem()
								.getLpName() + "-"
						+ timetableGroup.getGroupName() + "安排（选课）");
				message.setUsername(student);
				message.setCreateTime(Calendar.getInstance());
				message.setMessageState(0);
				message.setContent("/newtimetable/doStudentCourseSelect?mergeId="
						+ schoolCourseMerge.getId());
				message.setType("timetableStudent");
				messageDAO.store(message);
			}
			User courseTeacher = schoolCourseMerge.getSchoolCourseDetails()
					.iterator().next().getUser();
			Message m = new Message();
			m.setSendUser(shareService.getUser().getCname());
			m.setSendCparty(shareService.getUser().getSchoolAcademy()
					.getAcademyName());
			m.setTitle(schoolCourseMerge.getCourseNo()
					+ " "
					+ schoolCourseMerge.getCourseName()
					+ "课程-"
					+ timetableGroup.getTimetableBatch().getOperationItem()
							.getLpName() + "-" + timetableGroup.getGroupName()
					+ "安排（选课）");
			m.setUsername(courseTeacher.getUsername());
			m.setContent("/timetable/timetableAdminIframeNew?currpage=1&id=-1&status=-1&selectMergeId="
					+ mergeId);
			m.setType("timetable");
			m.setCreateTime(Calendar.getInstance());
			m.setMessageState(0);
			messageDAO.store(m);

			List<User> admins = shareService.findUsersByAuthority(11);
			for (User admin : admins) {
				Message message = new Message();
				message.setSendUser(shareService.getUser().getCname());
				message.setSendCparty(shareService.getUser().getSchoolAcademy()
						.getAcademyName());
				message.setTitle(schoolCourseMerge.getCourseNo()
						+ " "
						+ schoolCourseMerge.getCourseName()
						+ "课程-"
						+ timetableGroup.getTimetableBatch().getOperationItem()
								.getLpName() + "-"
						+ timetableGroup.getGroupName() + "安排（选课）");
				message.setUsername(admin.getUsername());
				message.setContent("/timetable/timetableAdminIframeNew?currpage=1&id=-1&status=-1&selectMergeId="
						+ mergeId);
				message.setType("timetable");
				message.setCreateTime(Calendar.getInstance());
				message.setMessageState(0);
				messageDAO.store(message);
			}
			List<User> labCenterManagers = shareService
					.findUsersByAuthority(24);
			for (User manager : labCenterManagers) {
				Message message = new Message();
				message.setSendUser(shareService.getUser().getCname());
				message.setSendCparty(shareService.getUser().getSchoolAcademy()
						.getAcademyName());
				message.setTitle(schoolCourseMerge.getCourseNo()
						+ " "
						+ schoolCourseMerge.getCourseName()
						+ "课程-"
						+ timetableGroup.getTimetableBatch().getOperationItem()
								.getLpName() + "-"
						+ timetableGroup.getGroupName() + "安排（选课）");
				message.setUsername(manager.getUsername());
				message.setContent("/timetable/timetableAdminIframeNew?currpage=1&id=-1&status=-1&selectMergeId="
						+ mergeId);
				message.setType("timetable");
				message.setCreateTime(Calendar.getInstance());
				message.setMessageState(0);
				messageDAO.store(message);
			}
		}
		return "success";
	}

	/************************************************************
	 * Description: 发送基础课发布消息给课程课程学生-学生选课
	 * 
	 * @author: 郑昕茹
	 * @date: 2017-06-04
	 *********************************************************************************/
	@ResponseBody
	@RequestMapping("/sendMergeMessageByGroupNotSelfSelect")
	public String sendMergeMessageByGroupNotSelfSelect(
			@RequestParam String[] groups, @RequestParam Integer mergeId)
			throws ParseException {
		SchoolCourseMerge schoolCourseMerge = schoolCourseMergeDAO
				.findSchoolCourseMergeById(mergeId);
		for (String groupId : groups) {
			TimetableGroup timetableGroup = timetableGroupDAO
					.findTimetableGroupById(Integer.parseInt(groupId));
			List<String> studentsAll = newTimetableCourseSchedulingService
					.findUsernamesByGroupId(Integer.parseInt(groupId));
			for (String student : studentsAll) {
				Message message = new Message();
				message.setSendUser(shareService.getUser().getCname());
				message.setSendCparty(shareService.getUser().getSchoolAcademy()
						.getAcademyName());
				message.setTitle(schoolCourseMerge.getCourseNo()
						+ " "
						+ schoolCourseMerge.getCourseName()
						+ "课程-"
						+ timetableGroup.getTimetableBatch().getOperationItem()
								.getLpName() + "-"
						+ timetableGroup.getGroupName() + "安排");
				message.setUsername(student);
				message.setCreateTime(Calendar.getInstance());
				message.setMessageState(0);
				message.setContent("/newtimetable/myTimetableStudent?courseNo="
						+ schoolCourseMerge.getCourseNo());
				message.setType("timetableStudent");
				messageDAO.store(message);
			}
			User courseTeacher = schoolCourseMerge.getSchoolCourseDetails()
					.iterator().next().getUser();
			Message m = new Message();
			m.setSendUser(shareService.getUser().getCname());
			m.setSendCparty(shareService.getUser().getSchoolAcademy()
					.getAcademyName());
			m.setTitle(schoolCourseMerge.getCourseNo()
					+ " "
					+ schoolCourseMerge.getCourseName()
					+ "课程-"
					+ timetableGroup.getTimetableBatch().getOperationItem()
							.getLpName() + "-" + timetableGroup.getGroupName()
					+ "安排");
			m.setUsername(courseTeacher.getUsername());
			m.setContent("/timetable/timetableAdminIframeNew?currpage=1&id=-1&status=-1&selectMergeId="
					+ mergeId);
			m.setType("timetable");
			m.setCreateTime(Calendar.getInstance());
			m.setMessageState(0);
			messageDAO.store(m);

			List<User> admins = shareService.findUsersByAuthority(11);
			for (User admin : admins) {
				Message message = new Message();
				message.setSendUser(shareService.getUser().getCname());
				message.setSendCparty(shareService.getUser().getSchoolAcademy()
						.getAcademyName());
				message.setTitle(schoolCourseMerge.getCourseNo()
						+ " "
						+ schoolCourseMerge.getCourseName()
						+ "课程-"
						+ timetableGroup.getTimetableBatch().getOperationItem()
								.getLpName() + "-"
						+ timetableGroup.getGroupName() + "安排");
				message.setUsername(admin.getUsername());
				message.setContent("/timetable/timetableAdminIframeNew?currpage=1&id=-1&status=-1&selectMergeId="
						+ mergeId);
				message.setType("timetable");
				message.setCreateTime(Calendar.getInstance());
				message.setMessageState(0);
				messageDAO.store(message);
			}
			List<User> labCenterManagers = shareService
					.findUsersByAuthority(24);
			for (User manager : labCenterManagers) {
				Message message = new Message();
				message.setSendUser(shareService.getUser().getCname());
				message.setSendCparty(shareService.getUser().getSchoolAcademy()
						.getAcademyName());
				message.setTitle(schoolCourseMerge.getCourseNo()
						+ " "
						+ schoolCourseMerge.getCourseName()
						+ "课程-"
						+ timetableGroup.getTimetableBatch().getOperationItem()
								.getLpName() + "-"
						+ timetableGroup.getGroupName() + "安排");
				message.setUsername(manager.getUsername());
				message.setContent("/timetable/timetableAdminIframeNew?currpage=1&id=-1&status=-1&selectMergeId="
						+ mergeId);
				message.setType("timetable");
				message.setCreateTime(Calendar.getInstance());
				message.setMessageState(0);
				messageDAO.store(message);
			}
		}
		return "success";
	}

	/************************************************************
	 * Description: 进行设备过滤的ajax调用
	 * 
	 * @作者：孙虎
	 * @日期：2017-08-14
	 ************************************************************/
	@RequestMapping("/coolSuggestDevice")
	public @ResponseBody
	List<Map<String, String>> coolSuggestDevice(@RequestParam String userName) {
		// 返回可用的人员信息
		return userDetailService.coolSuggestTuser(userName);
	}

	/*********************************************************************************
	 * description： 保存分组 author：戴昊宇 date：2017-09-16
	 *********************************************************************************/
	@RequestMapping("/savegroup")
	public @ResponseBody
	String savegroup(Integer groupId, String courseDetailNo, Integer number) {
		TimetableGroup group = timetableGroupDAO
				.findTimetableGroupById(groupId);
		group.setNumbers(number);
		timetableGroupDAO.store(group);
		return "success";
	}

	/********************************************************************************
	 * Description: 保存公选课排课结果
	 * 
	 * @author: 郑昕茹
	 * @throws ParseException
	 * @date: 2017-04-19
	 *********************************************************************************/
	@ResponseBody
	@RequestMapping("/savePublicElectiveCourseTimetable")
	public String savePublicElectiveCourseTimetable(@RequestParam int term,
			Integer[] weeks, Integer[] weekdays, Integer[] classes,
			Integer labRoom, String courseDetailNo, String courseStartTime,
			Integer courseEndMinute, Integer withdrawalMinute,
			Integer withdrawalTimes, String courseRequirement)
			throws ParseException {
		// 保存课程的选课信息
		SchoolCourseDetail schoolCourseDetail = schoolCourseDetailDAO
				.findSchoolCourseDetailByCourseDetailNo(courseDetailNo);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		schoolCourseDetail.setCourseEndMinute(courseEndMinute);
		schoolCourseDetail.setWithdrawalMinute(withdrawalMinute);
		schoolCourseDetail.setWithdrawalTimes(withdrawalTimes);
		schoolCourseDetail.setCourseRequirement(courseRequirement);
		if (courseStartTime != null && !courseStartTime.equals("")) {
			Calendar startTime = Calendar.getInstance();
			startTime.setTime(sdf.parse(courseStartTime));
			schoolCourseDetail.setCourseStartTime(startTime);
		}

		schoolCourseDetailDAO.store(schoolCourseDetail);

		return "-1";
	}

	/********************************************************************************
	 * Description: 保存公选课排课结果（合班）
	 * 
	 * @author: 郑昕茹
	 * @throws ParseException
	 * @date: 2017-04-19
	 *********************************************************************************/
	@ResponseBody
	@RequestMapping("/savePublicElectiveCourseTimetableInMerge")
	public String savePublicElectiveCourseTimetableInMerge(
			@RequestParam int term, Integer[] weeks, Integer[] weekdays,
			Integer[] classes, Integer labRoom, Integer mergeId,
			String courseStartTime, Integer courseEndMinute,
			Integer withdrawalMinute, Integer withdrawalTimes,
			String courseRequirement) throws ParseException {
		// 保存课程的选课信息
		SchoolCourseMerge schoolCourseMerge = schoolCourseMergeDAO
				.findSchoolCourseMergeById(mergeId);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		schoolCourseMerge.setCourseEndMinute(courseEndMinute);
		schoolCourseMerge.setWithdrawalMinute(withdrawalMinute);
		schoolCourseMerge.setWithdrawalTimes(withdrawalTimes);
		schoolCourseMerge.setCourseRequirement(courseRequirement);
		if (courseStartTime != null && !courseStartTime.equals("")) {
			Calendar startTime = Calendar.getInstance();
			startTime.setTime(sdf.parse(courseStartTime));
			schoolCourseMerge.setCourseStartTime(startTime);
		}
		schoolCourseMergeDAO.store(schoolCourseMerge);
		return "-1";
	}

	/********************************************************************************
	 * Description: 保存公选课单条排课结果
	 * 
	 * @author: 郑昕茹
	 * @throws ParseException
	 * @date: 2017-04-19
	 *********************************************************************************/
	@ResponseBody
	@RequestMapping("/saveSinglePublicElectiveCourseTimetable")
	public String saveSinglePublicElectiveCourseTimetable(
			@RequestParam int term, Integer week, Integer weekday,
			Integer selectClass, Integer labRoom, String courseDetailNo)
			throws ParseException {
		// 选择的一节相当于两节
		Integer startClass = selectClass * 2 - 1;
		Integer endClass = selectClass * 2;
		// 获取所处批次
		TimetableBatch timetableBatch = newTimetableCourseSchedulingService
				.findTimetableBatchByCourseDetailNoAndType(courseDetailNo, 23).get(0);
		int size = 0;
		if (timetableBatch!=null && timetableBatch.getId()!=null && timetableBatch.getTimetableGroups() != null) {
			size = timetableBatch.getTimetableGroups().size();
		}
		Integer capacity = 0 ;
		if(labRoom!=-1){
		 capacity = labRoomDAO.findLabRoomById(labRoom).getLabRoomCapacity();
		}

		// 找到，课程对应的容量
		LabRoomCourseCapacity labRoomCourseCapacity = newTimetableCourseSchedulingService
				.findLabRoomCourseCapacityByCourseDetailNoAndLabId(
						courseDetailNo, labRoom);
		if (labRoom != null && capacity != 0 && timetableBatch.getId()!=null){
		Integer orderNumber = size + 1;
		TimetableGroup timetableGroup = new TimetableGroup();
		timetableGroup.setGroupName("第" + orderNumber + "批");
		timetableGroup.setNumbers(capacity);
		timetableGroup.setTimetableBatch(timetableBatch);
		timetableGroup.setTimetableStyle(23);
		timetableGroup.setLabRoom(labRoomDAO.findLabRoomById(labRoom));
		timetableGroup = timetableGroupDAO.store(timetableGroup);
		TimetableAppointment appointment = timetableAppointmentSaveService
				.savePublicElectiveCourseTimetable(
						term,
						ConvertUtil.stringToIntArray(startClass.toString()
								+ "," + endClass.toString()),
						ConvertUtil.stringToIntArray(labRoom.toString()),
						ConvertUtil.stringToIntArray(week.toString()), weekday,
						courseDetailNo, timetableGroup.getId());
		if (appointment.getId() != null)
			return appointment.getId().toString();
		else
			return "-1";
	}
	return "0";
	}

	/********************************************************************************
	 * Description: 保存公选课单条排课结果（合班）
	 * 
	 * @author: 郑昕茹
	 * @throws ParseException
	 * @date: 2017-04-19
	 *********************************************************************************/
	@ResponseBody
	@RequestMapping("/saveSinglePublicElectiveCourseTimetableInMerge")
	public String saveSinglePublicElectiveCourseTimetableInMerge(
			@RequestParam int term, Integer week, Integer weekday,
			Integer selectClass, Integer labRoom, Integer mergeId)
			throws ParseException {
		// 选择的一节相当于两节
		Integer startClass = selectClass * 2 - 1;
		Integer endClass = selectClass * 2;
		// 获取所处批次
		TimetableBatch timetableBatch = newTimetableCourseSchedulingService
				.findTimetableBatchByCourseDetailNoAndType(mergeId.toString(),
						23).get(0);
		int size = 0;
		if (timetableBatch.getTimetableGroups() != null) {
			size = timetableBatch.getTimetableGroups().size();
		}
		Integer capacity = 0 ;
		if(labRoom!=-1){
		 capacity = labRoomDAO.findLabRoomById(labRoom).getLabRoomCapacity();
		}
		SchoolCourseMerge schoolCourseMerge = schoolCourseMergeDAO
				.findSchoolCourseMergeById(mergeId);
		String courseDetailNo = "";
		if (schoolCourseMerge.getSchoolCourseDetails() != null) {
			for (SchoolCourseDetail scd : schoolCourseMerge
					.getSchoolCourseDetails()) {
				courseDetailNo = scd.getCourseDetailNo();
				break;
			}
		}
		// 找到，课程对应的容量
		LabRoomCourseCapacity labRoomCourseCapacity = newTimetableCourseSchedulingService
				.findLabRoomCourseCapacityByCourseDetailNoAndLabId(
						courseDetailNo, labRoom);
		if (labRoom != null && capacity != 0 && timetableBatch.getId()!=null){
		Integer orderNumber = size + 1;
		TimetableGroup timetableGroup = new TimetableGroup();
		timetableGroup.setGroupName("第" + orderNumber + "批");
		timetableGroup.setNumbers(capacity);
		timetableGroup.setTimetableBatch(timetableBatch);
		timetableGroup.setTimetableStyle(23);
		timetableGroup.setLabRoom(labRoomDAO.findLabRoomById(labRoom));
		timetableGroup = timetableGroupDAO.store(timetableGroup);
		TimetableAppointment appointment = timetableAppointmentSaveService
				.savePublicElectiveCourseTimetableInMerge(
						term,
						ConvertUtil.stringToIntArray(startClass.toString()
								+ "," + endClass.toString()),
						ConvertUtil.stringToIntArray(labRoom.toString()),
						ConvertUtil.stringToIntArray(week.toString()), weekday,
						mergeId, timetableGroup.getId());
		if (appointment.getId() != null)
			return appointment.getId().toString();
		else
			return "-1";
		}
		return "0";
	}

	/****************************************************************************
	 * 作者：周志辉 时间：2017-09-26
	 * 
	 * @throws UnsupportedEncodingException
	 ****************************************************************************/
	@SuppressWarnings("deprecation")
	@RequestMapping("/findUserByDetailNoAndClassNumber")
	public @ResponseBody
	String findUserByDetailNoAndClassNumber(
			@RequestParam String courseDetailNo, String classNumber,
			int batchId, int currpage, int groupId, HttpServletRequest request)
			throws UnsupportedEncodingException {
		if (request.getParameter("classNumber") != null) {
			classNumber = request.getParameter("classNumber");
		}
		List<String> student1 = new ArrayList<String>() {
		};
		List<String> student2 = new ArrayList<String>() {
		};
		TimetableBatch batch = timetableBatchDAO
				.findTimetableBatchById(batchId);
		Set<TimetableGroup> groups = batch.getTimetableGroups();
		for (TimetableGroup timetableGroup : groups) {
			student1 = newTimetableCourseSchedulingService
					.findUserBygroupId(timetableGroup.getId());// 已分配学生
			for (String stu : student1) {
				student2.add(stu);
			}
		}

		List<String> studentsAll = newTimetableCourseSchedulingService
				.findUsernamesByCourseDetailNo(courseDetailNo, classNumber);
		List<String> list = getDiffrent(student2, studentsAll);
		List<User> students = new ArrayList<User>();
		for (String student : list) {
			User a = userDAO.findUserByPrimaryKey(student);
			students.add(a);
		}

		// 分页
		int pageSize = 20;
		ListPageUtil<User> studentList = new ListPageUtil<User>(students,
				currpage, pageSize);

		String s = "";
		for (User d : studentList.getPagedList()) {
			s += "<tr>" + "<td>" + d.getUsername() + "</td>" + "<td>"
					+ d.getCname() + "</td>" + "<td>"
					+ d.getSchoolClasses().getClassNumber() + "</td>"
					+ "<td><input type='checkbox' name='CK' value='"
					+ d.getUsername() + "'/></td>" + "</tr>";
		}

		s += "<tr><td colspan='7'>"
				+ "<a href='javascript:void(0)' onclick='queryUser("
				+ groupId
				+ ",1);'>"
				+ "首页"
				+ "</a>&nbsp;"
				+ "<a href='javascript:void(0)' onclick='queryUser("
				+ groupId
				+ ","
				+ studentList.getLastPage()
				+ ");'>"
				+ "上一页"
				+ "</a>&nbsp;"
				+ "<a href='javascript:void(0)' onclick='queryUser("
				+ groupId
				+ ","
				+ studentList.getNextPage()
				+ ");'>"
				+ "下一页"
				+ "</a>&nbsp;"
				+ "<a href='javascript:void(0)' onclick='queryUser("
				+ groupId
				+ ","
				+ studentList.getTotalPage()
				+ ");'>"
				+ "末页"
				+ "</a>&nbsp;"
				+ "当前第"
				+ currpage
				+ "页&nbsp; 共"
				+ studentList.getTotalPage()
				+ "页  "
				+ studentList.getTotalCount() + "条记录" + "</td></tr>";
		return shareService.htmlEncode(s);
	}

	/****************************************************************************
	 * 作者：贺子龙 时间：2017-10-11
	 * 
	 * @throws UnsupportedEncodingException
	 ****************************************************************************/
	@SuppressWarnings("deprecation")
	@RequestMapping("/findUserByMergeIdAndClassNumber")
	public @ResponseBody
	String findUserByMergeIdAndClassNumber(@RequestParam int mergeId,
			String classNumber, int batchId, int currpage, int groupId,
			HttpServletRequest request) throws UnsupportedEncodingException {
		if (request.getParameter("classNumber") != null) {
			classNumber = request.getParameter("classNumber");
		}
		List<String> student1 = new ArrayList<String>() {
		};
		List<String> student2 = new ArrayList<String>() {
		};
		TimetableBatch batch = timetableBatchDAO
				.findTimetableBatchById(batchId);
		Set<TimetableGroup> groups = batch.getTimetableGroups();
		for (TimetableGroup timetableGroup : groups) {
			student1 = newTimetableCourseSchedulingService
					.findUserBygroupId(timetableGroup.getId());// 已分配学生
			for (String stu : student1) {
				student2.add(stu);
			}
		}

		List<String> studentsAll = newTimetableCourseSchedulingService
				.findUsersNameByMergeId(mergeId + "", classNumber);
		List<String> list = getDiffrent(student2, studentsAll);
		List<User> students = new ArrayList<User>();
		for (String student : list) {
			User a = userDAO.findUserByPrimaryKey(student);
			students.add(a);
		}

		// 分页
		int pageSize = 20;
		ListPageUtil<User> studentList = new ListPageUtil<User>(students,
				currpage, pageSize);

		String s = "";
		for (User d : studentList.getPagedList()) {
			s += "<tr>" + "<td>" + d.getUsername() + "</td>" + "<td>"
					+ d.getCname() + "</td>" + "<td>"
					+ d.getSchoolClasses().getClassNumber() + "</td>"
					+ "<td><input type='checkbox' name='CK' value='"
					+ d.getUsername() + "'/></td>" + "</tr>";
		}

		s += "<tr><td colspan='7'>"
				+ "<a href='javascript:void(0)' onclick='queryUser("
				+ groupId
				+ ",1);'>"
				+ "首页"
				+ "</a>&nbsp;"
				+ "<a href='javascript:void(0)' onclick='queryUser("
				+ groupId
				+ ","
				+ studentList.getLastPage()
				+ ");'>"
				+ "上一页"
				+ "</a>&nbsp;"
				+ "<a href='javascript:void(0)' onclick='queryUser("
				+ groupId
				+ ","
				+ studentList.getNextPage()
				+ ");'>"
				+ "下一页"
				+ "</a>&nbsp;"
				+ "<a href='javascript:void(0)' onclick='queryUser("
				+ groupId
				+ ","
				+ studentList.getTotalPage()
				+ ");'>"
				+ "末页"
				+ "</a>&nbsp;"
				+ "当前第"
				+ currpage
				+ "页&nbsp; 共"
				+ studentList.getTotalPage()
				+ "页  "
				+ studentList.getTotalCount() + "条记录" + "</td></tr>";
		return shareService.htmlEncode(s);
	}

	/************************************************************
	 * @description：专业限选课排课-分批添加学生名单
	 * @author：贺子龙
	 * @date：2017-10-11
	 ************************************************************/
	@RequestMapping("/saveInBatches")
	public @ResponseBody
	String saveInBatches(@RequestParam int groupId, String[] array) {
		TimetableGroup timetableGroup = timetableGroupDAO
				.findTimetableGroupById(groupId);
		int origNumber = 0;
		if (timetableGroup.getNumbers() != null) {
			origNumber = timetableGroup.getNumbers();
		}
		timetableGroup.setNumbers(array.length + origNumber);
		timetableGroup = timetableGroupDAO.store(timetableGroup);
		TimetableGroupStudents timetableGroupStudents = new TimetableGroupStudents();
		for (String i : array) {
			timetableGroupStudents.setUser(userDAO.findUserByPrimaryKey(i));
			User abc = userDAO.findUserByPrimaryKey(i);
			timetableGroupStudents.setTimetableGroup(timetableGroup);
			TimetableGroupStudents aaa = timetableGroupStudentsDAO
					.store(timetableGroupStudents);
			timetableGroupStudentsDAO.flush();
		}
		return "success";
	}

	/************************************************************
	 * @description：删除分批-自主排课
	 * @author：戴昊宇
	 * @date：2017-05-13
	 ************************************************************/
	@RequestMapping("/delSpecializedBasicCourseTimetableSelfGroup")
	public @ResponseBody
	String delSpecializedBasicCourseTimetableSelfGroup(
			@RequestParam String courseDetailNo, String mergeId,Integer type, int item) {
		// 找到公选课排课所在批次
		TimetableBatch timetableBatch = newTimetableCourseSchedulingService
				.findTimetableBatchByCourseDetailNoAndTypeAndItemId(courseDetailNo, type, item);
		

		if (timetableBatch == null || timetableBatch!=null && timetableBatch.getId() == null) {
			// 找到公选课排课所在批次
			timetableBatch = newTimetableCourseSchedulingService
					.findTimetableBatchByCourseDetailNoAndTypeAndItemId(
							mergeId.toString(), type, item);
		}

		for (TimetableGroup group : timetableBatch.getTimetableGroups()) {
			for (TimetableGroupStudents groupstudent : group
					.getTimetableGroupStudentses()) {
				timetableGroupStudentsDAO.remove(groupstudent);
			}
			group.setTimetableGroupStudentses(null);
			timetableGroupDAO.store(group);
			timetableGroupDAO.remove(group);
		}
		timetableBatch.setTimetableGroups(null);
		timetableBatchDAO.remove(timetableBatch);
		return "success";
	}

	/**
	 * 获取两个List的不同元素
	 * 
	 * @param list1
	 * @param list2
	 * @return
	 */
	private static List<String> getDiffrent(List<String> list1,
			List<String> list2) {
		List<String> diff = new ArrayList<String>();
		for (String str : list2) {
			if (!list1.contains(str)) {
				diff.add(str);
			}
		}
		return diff;
	}
	
	/*********************************************************************************
     * Description: 判断当前项目、当前排课方式下是否已经建有分批
     * 
     * @author: 贺子龙
     * @date: 2017-10-14
     *********************************************************************************/
    @ResponseBody
    @RequestMapping("/ifBatchCopyReady")
    public String ifBatchCopyReady(@RequestParam Integer itemId, int type, String courseDetailNo){
    	// 判断当前课程，当前type，当前项目下，有无分批
        TimetableBatch timetableBatch = newTimetableCourseSchedulingService.findTimetableBatchByCourseDetailNoAndTypeAndItemId
                (courseDetailNo, type, itemId);
        // 找到当前课程，当前type下的所有分批，为了批次复制
        List<TimetableBatch> batches = newTimetableCourseSchedulingService.findTimetableBatchByCourseDetailNoAndType
        		(courseDetailNo, type);
        
        if(EmptyUtil.isObjectEmpty(timetableBatch) && batches.size()>0 && batches.get(0).getId()!=null){// 如果该项目下没有批次，并且其他项目下有批次则满足条件
            return "yes";
        }else{
            return "no";
        }        
    }
    
    /*********************************************************************************
     * Description: 判断当前项目、当前排课方式下是否已经建有分批
     * 
     * @author: 贺子龙
     * @date: 2017-10-18
     *********************************************************************************/
    @ResponseBody
    @RequestMapping("/ifBatchCopyReadyMerge")
    public String ifBatchCopyReadyMerge(@RequestParam Integer itemId, int type, Integer mergeId){
        // 找到公选课排课所在批次
        TimetableBatch timetableBatch = newTimetableCourseSchedulingService.findTimetableBatchByCourseDetailNoAndTypeAndItemId
                (mergeId.toString(), type, itemId);
        // 找到当前课程，当前type下的所有分批，为了批次复制
        List<TimetableBatch> batches = newTimetableCourseSchedulingService.findTimetableBatchByCourseDetailNoAndType
                (mergeId.toString(), type);
        
        if(EmptyUtil.isObjectEmpty(timetableBatch) && batches.size()>0 && batches.get(0).getId()!=null){// 如果该项目下没有批次，并且其他项目下有批次则满足条件
            return "yes";
        }else{
            return "no";
        }        
    }
    
    /*********************************************************************************
     * Description: 判断当前项目、当前排课方式下是否已经建有分批
     * 
     * @author: 贺子龙
     * @date: 2017-10-14
     *********************************************************************************/
    @ResponseBody
    @RequestMapping("/copyBatch")
    public String copyBatch(@RequestParam Integer itemId, int sourceBatchId){
    	newTimetableShareService.copyBatch(itemId, sourceBatchId);
    	return "success";
    }

}
