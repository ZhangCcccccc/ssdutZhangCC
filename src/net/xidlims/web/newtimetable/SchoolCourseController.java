/****************************************************************************
 * @功能：该controller为应对西电排课中的学生自选
 * @作者：贺子龙
 * @Date：2017-10-16
 * 
 *  基础课排课
    1.学生自选  21  2.教师强排  22 3.自主分批 28 4.公选 23
    
    合班排课
    2.学生自选  24  2.教师强排  25 3.自主分批 26 4.公选 23
 * 
 ****************************************************************************/

package net.xidlims.web.newtimetable;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
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
import net.xidlims.domain.OperationItem;
import net.xidlims.domain.SchoolCourseDetail;
import net.xidlims.domain.SchoolCourseInfo;
import net.xidlims.domain.SchoolCourseMerge;
import net.xidlims.domain.SchoolCourseStudent;
import net.xidlims.domain.SchoolTerm;
import net.xidlims.domain.SystemTime;
import net.xidlims.domain.TimetableAppointment;
import net.xidlims.domain.TimetableBatch;
import net.xidlims.domain.TimetableGroup;
import net.xidlims.domain.TimetableGroupStudents;
import net.xidlims.domain.TimetableLabRelated;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller("SchoolCourseController")
@SessionAttributes("selected_labCenter")
@RequestMapping("/newtimetable")
public class SchoolCourseController<JsonResult> {

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

	/*********************************************************************************
	 * description： 排课授权 author：郑昕茹 date：2017-04-14
	 *********************************************************************************/
	@RequestMapping("/listSchoolCourseDetailPermissions")
	public ModelAndView listSchoolCourseDetailPermissions(
			HttpServletRequest request,
			@ModelAttribute SchoolCourseDetail schoolCourseDetail,
			@RequestParam int currpage) {
		ModelAndView mav = new ModelAndView();

		// 登陆人权限获得
		mav.addObject("user", shareService.getUser());
		int pageSize = 30;
		List<SchoolCourseDetail> detailListAll = newTimetableCourseSchedulingService
		.findSchoolCourseDetailUnderPermission(schoolCourseDetail, 1,
				-1, request);
		int totalRecords = detailListAll.size();
		
		List<SchoolCourseDetail> courseDetailList = newTimetableCourseSchedulingService
				.findSchoolCourseDetailUnderPermission(schoolCourseDetail,
						currpage, pageSize, request);
		mav.addObject("detailListAll", detailListAll);
		Map<String, Integer> pageModel = shareService.getPage(currpage,
				pageSize, totalRecords);
		mav.addObject("pageModel", pageModel);
		mav.addObject("totalRecords", totalRecords);
		mav.addObject("currpage", currpage);
		mav.addObject("pageSize", pageSize);

		mav.addObject("schoolCourseDetail", schoolCourseDetail);
		// 查找所有的实验大纲
		mav.addObject("courseDetailList", courseDetailList);
		List<SchoolTerm> schoolTerms = outerApplicationService.getSchoolTermList();
		mav.addObject("schoolTerms", schoolTerms);
		mav.setViewName("newtimetable/listSchoolCourseDetailPermissions.jsp");
		return mav;
	}

	/*********************************************************************************
	 * description： 设置排课教师 author：郑昕茹 date：2017-04-19
	 *********************************************************************************/
	@RequestMapping("/changeScheduleTeachers")
	public String changeScheduleTeachers(String detailNo, String[] teacher) {
		SchoolCourseDetail courseDetail = schoolCourseDetailDAO
				.findSchoolCourseDetailByCourseDetailNo(detailNo);
		Set<User> users = courseDetail.getUserByScheduleTeachers();
		for (String t : teacher) {
			User user = userDAO.findUserByPrimaryKey(t);
			users.add(user);
		}
		courseDetail.setUserByScheduleTeachers(users);
		schoolCourseDetailDAO.store(courseDetail);
		return "redirect:/newtimetable/listLabSchoolCourseDetailPermissions?currpage=1";

	}

	/*********************************************************************************
	 * description： 我的排课 author：郑昕茹 date：2017-04-14
	 *********************************************************************************/
	@RequestMapping("/listMySchedule")
	public ModelAndView listMySchedule(HttpServletRequest request,
			@ModelAttribute SchoolCourseDetail schoolCourseDetail,
			@RequestParam int currpage) {
		ModelAndView mav = new ModelAndView();

		// 登陆人权限获得
		mav.addObject("authorities", shareService.getUser().getAuthorities());
		int pageSize = 30;
		List<Object[]> courseDetailList = newTimetableCourseSchedulingService
				.findMyScheduleListView(request.getSession(),
						schoolCourseDetail, currpage, pageSize);
		List<Object[]> detailListAll = newTimetableCourseSchedulingService
				.findMyScheduleListView(request.getSession(),
						schoolCourseDetail, 1, -1);
		mav.addObject("detailListAll", detailListAll);
		int totalRecords = detailListAll.size();
		Map<String, Integer> pageModel = shareService.getPage(currpage,
				pageSize, totalRecords);
		mav.addObject("pageModel", pageModel);
		mav.addObject("totalRecords", totalRecords);
		mav.addObject("currpage", currpage);
		mav.addObject("pageSize", pageSize);
		mav.addObject("user", shareService.getUser());
		mav.addObject("schoolCourseDetail", schoolCourseDetail);
		// 查找所有的实验大纲
		mav.addObject("courseDetailList", courseDetailList);
		List<SchoolTerm> schoolTerms = outerApplicationService
				.getSchoolTermList();
		mav.addObject("schoolTerms", schoolTerms);
		String courseId = "";
		if (request.getParameter("schoolCourse.courseNo") != null) {
			courseId = request.getParameter("schoolCourse.courseNo");
		}
		mav.addObject("courseId", courseId);
		Map<String, String> courses = new HashMap<String, String>();
		Set<SchoolCourseInfo> courseInfos = new HashSet<SchoolCourseInfo>();
		Set<SchoolCourseMerge> merges = new HashSet<SchoolCourseMerge>();
		for (SchoolCourseDetail s : shareService.getUser()
				.getSchoolCourseDetails()) {
			if (s.getSchoolCourse().getSchoolCourseInfo()
					.getOperationOutlinesForClassId() != null
					&& s.getSchoolCourse().getSchoolCourseInfo()
							.getOperationOutlinesForClassId().size() != 0) {
				courseInfos.add(s.getSchoolCourse().getSchoolCourseInfo());
			}
			if (s.getSchoolCourseMerge() == null) {
				courses.put(s.getSchoolCourse().getCourseNo(),
						s.getCourseName()
								+ s.getSchoolCourse().getCourseNo());

			} else {
				merges.add(s.getSchoolCourseMerge());
			}
		}
		for (SchoolCourseMerge s : merges) {
			courses.put(s.getCourseNo(),
					s.getCourseName() + s.getCourseNo());
		}
		//Set<SchoolCourseDetail> courses = schoolCourseDetailDAO.findAllSchoolCourseDetails();
		mav.addObject("courses", courses);
		
		int termId = shareService.getBelongsSchoolTerm(Calendar.getInstance())
				.getId();
		if (schoolCourseDetail.getSchoolTerm() == null
				|| schoolCourseDetail.getSchoolTerm().getId() == null
				|| schoolCourseDetail.getSchoolTerm().getId().equals("")) {
		} else {
			termId = schoolCourseDetail.getSchoolTerm().getId();
		}
		mav.addObject("termId", termId);

		mav.setViewName("newtimetable/listMySchedules.jsp");
		return mav;
	}

	/************************************************************
	 * @description：专业限选课排课-学生自选
	 * @author：郑昕茹
	 * @date：2017-04-07
	 ************************************************************/
	@RequestMapping("/doSpecializedBasicCourseTimetable")
	public ModelAndView doSpecializedBasicCourseTimetable(
			@RequestParam String courseDetailNo, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		Calendar cal1 = Calendar.getInstance();
		String currpage = request.getParameter("currpage");
		mav.addObject("currpage", currpage);
		// 登陆人权限获得
		mav.addObject("authorities", shareService.getUser().getAuthorities());
		mav.addObject("user", shareService.getUser());
		SchoolTerm schoolTerm = shareService.getBelongsSchoolTerm(Calendar
				.getInstance());
		List<Object[]> schoolTermWeeks = termDetailService
				.findViewSchoolTermWeek(schoolTerm.getId());
		mav.addObject("schoolTerm", schoolTerm);
		mav.addObject("schoolTermWeeks", schoolTermWeeks);
		mav.addObject("specialSchoolWeeks", termDetailService
				.findSpecialSchoolWeekByTerm(schoolTerm.getId()));
		
		// 获取实验室信息
		Integer roomId = -1;
		LabRoom labRoom = labRoomDAO.findLabRoomById(roomId);
		if (request.getParameter("labroom") != null) {
			roomId = Integer.parseInt(request.getParameter("labroom"));
			labRoom = labRoomDAO.findLabRoomById(Integer.parseInt(request
					.getParameter("labroom")));
		}
		mav.addObject("roomId", roomId);
		mav.addObject("labRoom", labRoom);
		
		mav.addObject("listLabTimetableAppointments",
				newTimetableCourseSchedulingService
						.getListLabTimetableAppointments(roomId,
								schoolTerm.getId()));
		
		List<TimetableLabRelated> listThisCourseLabTimetableAppointments = newTimetableCourseSchedulingService
				.getListLabTimetableAppointmentsByCourseDetailNo(request,
						schoolTerm.getId(), courseDetailNo);
		mav.addObject("timetableLength",
				listThisCourseLabTimetableAppointments.size());
		mav.addObject("listThisCourseLabTimetableAppointments",
				listThisCourseLabTimetableAppointments);

		mav.addObject("labRoomMap",
				newTimetableCourseSchedulingService.getLabRoomMap());

		mav.addObject("courseDetailNo", courseDetailNo);
		
		SchoolCourseDetail schoolCourseDetail = schoolCourseDetailDAO
				.findSchoolCourseDetailByPrimaryKey(courseDetailNo);

		// 获取排课教师
		Set<User> teachers = schoolCourseDetail.getUsers();
		teachers.addAll(schoolCourseDetail.getUserByScheduleTeachers());
		teachers.add(schoolCourseDetail.getUser());
		mav.addObject("teachers", teachers);
		// 选课开始时间
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String courseStartTime = sdf.format(Calendar.getInstance().getTime());
		if (schoolCourseDetail.getCourseStartTime() != null) {
			courseStartTime = sdf.format(schoolCourseDetail
					.getCourseStartTime().getTime());
		}
		mav.addObject("courseStartTime", courseStartTime);
		mav.addObject("schoolCourseDetail", schoolCourseDetail);
		Integer batchId = -1;
		List<OperationItem> items = newOperationService
				.findOperationItemsByCourseNumber(schoolCourseDetail
						.getSchoolCourse().getSchoolCourseInfo()
						.getCourseNumber());
		mav.addObject("items", items);
		Integer itemId = -1;
		if (items != null && items.size() > 0) {
			itemId = items.get(0).getId();
		}
		if (request.getParameter("item") != null
				&& !request.getParameter("item").equals("")) {
			itemId = Integer.parseInt(request.getParameter("item"));
		}
		mav.addObject("item", itemId);
		if (labRoom != null) {

			Integer capacity = labRoom.getLabRoomCapacity();
			// 学生自选模式下，批次的数量由实验室容量和选课总人数的整除来定，每个批次的容量默认=实验室容量，除非实验室容量为0或为空才找课程容量
			if (EmptyUtil.isIntegerEmpty(capacity) || capacity.equals(0)) {
				// 找到，课程对应的容量
				LabRoomCourseCapacity labRoomCourseCapacity = newTimetableCourseSchedulingService
						.findLabRoomCourseCapacityByCourseDetailNoAndLabId(
								courseDetailNo, labRoom.getId());
				if (labRoomCourseCapacity != null
						&& labRoomCourseCapacity.getCapacity() != null)
					capacity = labRoomCourseCapacity.getCapacity();
			}

			// Set<OperationItem> items =
			// schoolCourseDetail.getSchoolCourse().getSchoolCourseInfo().getOperationItems();
			Integer numbers = schoolCourseDetail.getSchoolCourseStudents()
					.size();
			// 找到公选课排课所在批次
			TimetableBatch timetableBatch = newTimetableCourseSchedulingService
					.findTimetableBatchByCourseDetailNoAndTypeAndItemId(
							courseDetailNo, 21, itemId);
			if (timetableBatch == null) {
				timetableBatch = new TimetableBatch();
				timetableBatch.setBatchName("基础课排课批次");
				timetableBatch.setCourseCode(courseDetailNo);
				timetableBatch.setType(21);
				timetableBatch.setIfselect(1);
				timetableBatch.setOperationItem(operationItemDAO
						.findOperationItemById(itemId));
				timetableBatch = timetableBatchDAO.store(timetableBatch);
				// 获得排课组数和每组人数
				if (labRoom != null && capacity != null && capacity != 0
						&& schoolCourseDetail != null) {
					int studentNumber = schoolCourseDetail
							.getSchoolCourseStudents().size();
					int groupNumber = studentNumber / capacity;
					if (studentNumber % capacity != 0)
						groupNumber += 1;
					List<TimetableGroup> groups = new LinkedList<TimetableGroup>();
					for (int i = 1; i < groupNumber + 1; i++) {
						TimetableGroup timetableGroup = new TimetableGroup();
						timetableGroup.setTimetableBatch(timetableBatch);
						timetableGroup.setGroupName("第" + i + "批");
						if (i != groupNumber) {
							timetableGroup.setNumbers(capacity);
						} else {
							timetableGroup.setNumbers(studentNumber - capacity
									* (groupNumber - 1));
						}
						timetableGroup.setLabRoom(labRoom);
						timetableGroup.setTimetableStyle(21);
						timetableGroup = timetableGroupDAO
								.store(timetableGroup);
						groups.add(timetableGroup);
					}
					timetableBatch = timetableBatchDAO
							.findTimetableBatchById(timetableBatch.getId());
					mav.addObject("groups", groups);
					mav.addObject("isComplete", 0);
					batchId = timetableBatch.getId();
				}
			} else {
				if (schoolCourseDetail.getIsComplete() != null
						&& schoolCourseDetail.getIsComplete() == 1
						|| timetableBatch.getIsComplete() != null
						&& timetableBatch.getIsComplete() == 1) {
					mav.addObject("groups", newTimetableCourseSchedulingService
							.findTimetableGroupsByBacthId(timetableBatch
									.getId()));
					mav.addObject("isComplete", 1);
					if (schoolCourseDetail.getIsComplete() == null
							|| schoolCourseDetail.getIsComplete() != null
							&& schoolCourseDetail.getIsComplete() != 1) {
						mav.addObject("isPartComplete", 1);
					}
				} else {
					mav.addObject("isComplete", 0);
					List<TimetableGroup> groups = new LinkedList<TimetableGroup>();
					Integer count = 0;
					for (TimetableGroup t : newTimetableCourseSchedulingService
							.findTimetableGroupsByBacthId(timetableBatch
									.getId())) {
						// 已排课,保留分组，并在所有学生名单中除去这些学生
						if (t.getTimetableAppointments() != null
								&& t.getTimetableAppointments().size() != 0) {
							numbers -= t.getNumbers();
							count++;
							t.setGroupName("第" + count + "批");
							t = timetableGroupDAO.store(t);
							groups.add(t);
						}// 属于其他实验室，且未排课，删除这一分组
						else if (t.getLabRoom().getId().intValue() != labRoom
								.getId().intValue()
								&& (t.getTimetableAppointments() == null || t
										.getTimetableAppointments() != null
										&& t.getTimetableAppointments().size() == 0)) {
							timetableGroupDAO.remove(t);
						} else if (t.getLabRoom().getId().intValue() == labRoom
								.getId().intValue()
								&& t.getTimetableStyle() != 21
								&& (t.getTimetableAppointments() == null || t
										.getTimetableAppointments() != null
										&& t.getTimetableAppointments().size() == 0)) {
							timetableGroupDAO.remove(t);
						} else {
							numbers -= t.getNumbers();
							count++;
							t.setGroupName("第" + count + "批");
							t = timetableGroupDAO.store(t);
							groups.add(t);
						}
					}
					timetableBatch = timetableBatchDAO
							.findTimetableBatchById(timetableBatch.getId());
					int studentNumber = numbers;
					int groupNumber = studentNumber / capacity;
					if (studentNumber % capacity != 0)
						groupNumber += 1;
					for (int i = 1; i < groupNumber + 1; i++) {
						count++;
						TimetableGroup timetableGroup = new TimetableGroup();
						timetableGroup.setTimetableBatch(timetableBatch);
						timetableGroup.setGroupName("第" + count + "批");
						if (i != groupNumber) {
							timetableGroup.setNumbers(capacity);
						} else {
							timetableGroup.setNumbers(studentNumber - capacity
									* (groupNumber - 1));
						}
						timetableGroup.setLabRoom(labRoom);
						timetableGroup.setTimetableStyle(21);
						timetableGroup = timetableGroupDAO
								.store(timetableGroup);
						groups.add(timetableGroup);
					}
					timetableBatch = timetableBatchDAO
							.findTimetableBatchById(timetableBatch.getId());
					mav.addObject("groups", groups);
					if (schoolCourseDetail.getCourseStart() != null) {
						mav.addObject("startDate", sdf
								.format(schoolCourseDetail.getCourseStart()
										.getTime()));
					}
					if (schoolCourseDetail.getCourseEnd() != null) {
						mav.addObject("endDate", sdf.format(schoolCourseDetail
								.getCourseEnd().getTime()));
					}
				}

			}
			mav.addObject("timetableBatch", timetableBatch);
			batchId = timetableBatch.getId();
			mav.addObject("notSelectLab", 0);
		} else {
			TimetableBatch timetableBatch = newTimetableCourseSchedulingService
					.findTimetableBatchByCourseDetailNoAndTypeAndItemId(
							courseDetailNo, 21, itemId);
			if (timetableBatch == null) {
				mav.addObject("isComplete", 0);
			} else {
				batchId = timetableBatch.getId();
				if (schoolCourseDetail.getIsComplete() != null
						&& schoolCourseDetail.getIsComplete() == 1
						|| timetableBatch.getIsComplete() != null
						&& timetableBatch.getIsComplete() == 1) {
					mav.addObject("groups", newTimetableCourseSchedulingService
							.findTimetableGroupsByBacthId(timetableBatch
									.getId()));
					mav.addObject("isComplete", 1);
					if (schoolCourseDetail.getIsComplete() == null
							|| schoolCourseDetail.getIsComplete() != null
							&& schoolCourseDetail.getIsComplete() != 1) {
						mav.addObject("isPartComplete", 1);
					}
				} else {
					mav.addObject("isComplete", 0);
					mav.addObject("groups", newTimetableCourseSchedulingService
							.findTimetableGroupsByBacthId(timetableBatch
									.getId()));
				}
			}
			mav.addObject("notSelectLab", 1);
		}
		// 获取所有节次
		List<SystemTime> times = timeDetailService.findAllTimes(0, -1, "S");
		Map<Object, Object> time = new HashMap<Object, Object>();
		Map<Object, Object> timeEnd = new HashMap<Object, Object>();
		for (SystemTime t : times) {
			time.put(t.getSection(), t.getStartDate());
			timeEnd.put(t.getSection(), t.getEndDate());
		}
		mav.addObject("timeMap", time);
		mav.addObject("batchId", batchId);

		Calendar cal2 = Calendar.getInstance();
		System.out.println(cal2.getTimeInMillis() - cal1.getTimeInMillis());
		mav.setViewName("newtimetable/doSpecializedBasicCourseTimetable.jsp");
		return mav;
	}

	/************************************************************
	 * description：根据选择，调整学生批次
	 * 
	 * @author：魏诚
	 * @date：2017-09-28
	 ************************************************************/
	@RequestMapping("/changeStudents")
	public ModelAndView changeStudents(HttpServletRequest request, Integer type) {
		ModelAndView mav = new ModelAndView();
		// 获取选择的学生名单
		String[] checkbox = request.getParameterValues("LType");
		// 获取当前的组
		int groupId = Integer.parseInt(request.getParameter("groupId"));
		// 获取迁移的组
		int toGroup = Integer.parseInt(request.getParameter("selectGroup"));
		TimetableGroup from = timetableGroupDAO.findTimetableGroupById(groupId);
		TimetableGroup to = timetableGroupDAO.findTimetableGroupById(toGroup);
		// 开始遍历选择的复选框进行学生迁移
		for (String username : checkbox) {
			TimetableGroupStudents timetableGroupStudents = timetableGroupStudentsDAO
					.executeQuery(
							"select c from TimetableGroupStudents c where c.timetableGroup.id="
									+ groupId + " and c.user.username ='"
									+ username + "'").get(0);
			timetableGroupStudents.setTimetableGroup(to);
			timetableGroupStudentsDAO.store(timetableGroupStudents);
			from.setNumbers(from.getNumbers() - 1);
			to.setNumbers(to.getNumbers() + 1);
			// 更新分组的名单数
			timetableGroupDAO.store(from);
			timetableGroupDAO.store(to);
		}
		// 获取课程编号以便返回
		String courseDetailNo = from.getTimetableBatch().getCourseCode();
		if (type.equals(0)) {
			mav.setViewName("redirect:doSpecializedBasicCourseTimetableTeacher?courseDetailNo="
					+ courseDetailNo + "&currpage=3");
		} else {
			mav.setViewName("redirect:doSpecializedBasicCourseTimetableSelf?courseDetailNo="
					+ courseDetailNo + "&currpage=3");
		}
		return mav;
	}

	/************************************************************
	 * @description：学生选课
	 * @author：郑昕茹
	 * @date：2017-04-26
	 ************************************************************/
	@RequestMapping("/doStudentCourseSelect")
	public ModelAndView doStudentCourseSelect(HttpServletRequest request,
			String courseDetailNo, Integer mergeId) {
		ModelAndView mav = new ModelAndView();
		String courseRequire = "";
		if (courseDetailNo != null) {
			mav.addObject("selectCourseDetailNo", courseDetailNo);
			SchoolCourseDetail detail = schoolCourseDetailDAO
					.findSchoolCourseDetailByCourseDetailNo(courseDetailNo);
			courseRequire = detail.getCourseRequirement();
		} else {
			mav.addObject("selectCourseDetailNo", 0);
		}

		if (mergeId != null) {
			mav.addObject("selectMergeId", mergeId);
			SchoolCourseMerge merge = schoolCourseMergeDAO
					.findSchoolCourseMergeById(mergeId);
			courseRequire = merge.getCourseRequirement();
		} else {
			mav.addObject("selectMergeId", 0);
		}
		mav.addObject("courseRequire", courseRequire);

		// 登陆人权限获得
		mav.addObject("authorities", shareService.getUser().getAuthorities());
		mav.addObject("user", shareService.getUser());
		mav.addObject("currTime", Calendar.getInstance());
		Integer termId = -1;
		if (request.getParameter("term") != null) {
			termId = Integer.parseInt(request.getParameter("term"));
		}
		if (termId == -1) {
			termId = shareService.getBelongsSchoolTerm(Calendar.getInstance())
					.getId();
		}
		mav.addObject("termId", termId);
		// 登陆人权限获得
		mav.addObject("authorities", shareService.getUser().getAuthorities());
		SchoolTerm schoolTerm = shareService.getBelongsSchoolTerm(Calendar
				.getInstance());
		List<Object[]> schoolTermWeeks = termDetailService
				.findViewSchoolTermWeek(schoolTerm.getId());
		mav.addObject("schoolTerm", schoolTerm);
		mav.addObject("schoolTermWeeks", schoolTermWeeks);
		mav.addObject("specialSchoolWeeks", termDetailService
				.findSpecialSchoolWeekByTerm(schoolTerm.getId()));

		List<SchoolTerm> schoolTerms = outerApplicationService
				.getSchoolTermList();
		mav.addObject("schoolTerms", schoolTerms);

		List<TimetableAppointment> appointments = new LinkedList<TimetableAppointment>();
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
									.getId().intValue() == termId.intValue()
							&& te.getStatus() == 1) {
						appointments.add(te);
					}
					// 合班
					if ((te.getTimetableStyle() == 25
							|| te.getTimetableStyle() == 24 || te
							.getTimetableStyle() == 26)
							&& te.getSchoolCourseMerge() != null
							&& te.getSchoolCourseMerge().getTermId().intValue() == termId
									.intValue() && te.getStatus() == 1) {
						appointments.add(te);
					}
				}
			}
		}
		mav.addObject("appointments", appointments);
		// 非合班
		List<TimetableGroup> timetableGroups = newTimetableCourseSchedulingService
				.findTimetableGroupsCanSelectByCurrUser();
		// 合班
		List<TimetableGroup> timetableGroupMergeCourses = newTimetableCourseSchedulingService
				.findTimetableGroupsCanSelectInMergeCourseByCurrUser();

		// 全校公选课
		List<TimetableGroup> timetableGroupPublicCourses = newTimetableCourseSchedulingService
				.findTimetableGroupsCanSelectInPublicCourse();
		mav.addObject("timetableGroups", timetableGroups);
		mav.addObject("timetableGroupMergeCourses", timetableGroupMergeCourses);
		mav.addObject("timetableGroupPublicCourses",
				timetableGroupPublicCourses);

		// 获取所有节次
		List<SystemTime> times = timeDetailService.findAllTimes(0, -1, "S");
		Map<Object, Object> time = new HashMap<Object, Object>();
		for (SystemTime t : times) {
			time.put(t.getSection(), t.getStartDate());
		}
		mav.setViewName("newtimetable/doStudentCourseSelect.jsp");
		return mav;
	}

	/************************************************************
	 * @description：合班排课-学生自选
	 * @author：郑昕茹
	 * @date：2017-04-07
	 ************************************************************/
	@RequestMapping("/doMergeCourseTimetable")
	public ModelAndView doMergeCourseTimetable(@RequestParam Integer mergeId,
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		Calendar cal1 = Calendar.getInstance();
		// 登陆人权限获得
		mav.addObject("authorities", shareService.getUser().getAuthorities());
		mav.addObject("user", shareService.getUser());
		SchoolTerm schoolTerm = shareService.getBelongsSchoolTerm(Calendar
				.getInstance());
		List<Object[]> schoolTermWeeks = termDetailService
				.findViewSchoolTermWeek(schoolTerm.getId());
		mav.addObject("schoolTerm", schoolTerm);
		mav.addObject("schoolTermWeeks", schoolTermWeeks);
		mav.addObject("specialSchoolWeeks", termDetailService
				.findSpecialSchoolWeekByTerm(schoolTerm.getId()));
		
		// 获取实验室信息
		Integer roomId = -1;
		LabRoom labRoom = labRoomDAO.findLabRoomById(roomId);
		if (request.getParameter("labroom") != null) {
			roomId = Integer.parseInt(request.getParameter("labroom"));
			labRoom = labRoomDAO.findLabRoomById(Integer.parseInt(request
					.getParameter("labroom")));
		}
		mav.addObject("labRoom", labRoom);
		mav.addObject("roomId", roomId);
		
		mav.addObject("listLabTimetableAppointments",
				newTimetableCourseSchedulingService
						.getListLabTimetableAppointments(roomId,
								schoolTerm.getId()));
		List<TimetableLabRelated> listThisCourseLabTimetableAppointments = newTimetableCourseSchedulingService
				.getListLabTimetableAppointmentsByMergeId(request,
						schoolTerm.getId(), mergeId);
		mav.addObject("timetableLength",
				listThisCourseLabTimetableAppointments.size());
		mav.addObject("listThisCourseLabTimetableAppointments",
				listThisCourseLabTimetableAppointments);
		mav.addObject("labRoomMap",
				newTimetableCourseSchedulingService.getLabRoomMap());
		mav.addObject("mergeId", mergeId);
		
		SchoolCourseMerge schoolCourseMerge = schoolCourseMergeDAO
				.findSchoolCourseMergeById(mergeId);
		// 选课开始时间
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// 查看学生名单用传值
		mav.addObject("courseDetailNo", schoolCourseMerge.getCourseNo());
		mav.addObject("schoolCourseMerge", schoolCourseMerge);
		

		// Set<OperationItem> items = new HashSet<OperationItem>();
		Set<User> teachers = new HashSet<User>();

		Integer numbers = schoolCourseMerge.getStudentNumbers();
		SchoolCourseDetail detail = null;
		if (schoolCourseMerge.getSchoolCourseDetails() != null) {
			for (SchoolCourseDetail d : schoolCourseMerge
					.getSchoolCourseDetails()) {
				detail = d;
				teachers.addAll(d.getUsers());
				teachers.addAll(d.getUserByScheduleTeachers());
				teachers.add(d.getUser());
			}
		}

		mav.addObject("teachers", teachers);
		Integer batchId = -1;
		// mav.addObject("courseTeacher", detail.getUser());
		List<OperationItem> items = newOperationService
				.findOperationItemsByCourseNumber(detail.getSchoolCourse()
						.getSchoolCourseInfo().getCourseNumber());
		mav.addObject("items", items);
		Integer itemId = -1;
		if (items != null && items.size() > 0) {
			itemId = items.get(0).getId();
		}
		if (request.getParameter("item") != null
				&& !request.getParameter("item").equals("")) {
			itemId = Integer.parseInt(request.getParameter("item"));
		}
		mav.addObject("item", itemId);
		if (labRoom != null) {
			Integer capacity = labRoom.getLabRoomCapacity();
			// 找到，课程对应的容量
			LabRoomCourseCapacity labRoomCourseCapacity = newTimetableCourseSchedulingService
					.findLabRoomCourseCapacityByCourseDetailNoAndLabId(
							detail.getCourseDetailNo(), labRoom.getId());
			if (labRoomCourseCapacity != null
					&& labRoomCourseCapacity.getCapacity() != null)
				capacity = labRoomCourseCapacity.getCapacity();
			// 找到公选课排课所在批次
			TimetableBatch timetableBatch = newTimetableCourseSchedulingService
					.findTimetableBatchByCourseDetailNoAndTypeAndItemId(
							mergeId.toString(), 24, itemId);
			if (timetableBatch == null) {
				timetableBatch = new TimetableBatch();
				timetableBatch.setBatchName("合班排课批次学生自选");
				timetableBatch.setCourseCode(mergeId.toString());
				timetableBatch.setType(24);
				timetableBatch.setIfselect(1);
				timetableBatch.setOperationItem(operationItemDAO
						.findOperationItemById(itemId));
				timetableBatch = timetableBatchDAO.store(timetableBatch);
				// 获得排课组数和每组人数
				if (labRoom != null && capacity != null && capacity != 0
						&& schoolCourseMerge != null) {
					int studentNumber = schoolCourseMerge.getStudentNumbers();
					int groupNumber = studentNumber / capacity;
					if (studentNumber % capacity != 0)
						groupNumber += 1;
					List<TimetableGroup> groups = new LinkedList<TimetableGroup>();
					for (int i = 1; i < groupNumber + 1; i++) {
						TimetableGroup timetableGroup = new TimetableGroup();
						timetableGroup.setTimetableBatch(timetableBatch);
						timetableGroup.setGroupName("第" + i + "批");
						timetableGroup.setTimetableStyle(24);
						timetableGroup.setLabRoom(labRoom);
						if (i != groupNumber) {
							timetableGroup.setNumbers(capacity);
						} else {
							timetableGroup.setNumbers(studentNumber - capacity
									* (groupNumber - 1));
						}
						timetableGroup = timetableGroupDAO
								.store(timetableGroup);
						groups.add(timetableGroup);
					}

					timetableBatch = timetableBatchDAO
							.findTimetableBatchById(timetableBatch.getId());
					mav.addObject("groups", groups);
					batchId = timetableBatch.getId();
					mav.addObject("isComplete", 0);
				}
			} else {
				if (schoolCourseMerge.getIsComplete() != null
						&& schoolCourseMerge.getIsComplete() == 1
						|| timetableBatch.getIsComplete() != null
						&& timetableBatch.getIsComplete() == 1) {
					mav.addObject("groups", newTimetableCourseSchedulingService
							.findTimetableGroupsByBacthId(timetableBatch
									.getId()));
					mav.addObject("isComplete", 1);
					if (schoolCourseMerge.getIsComplete() == null
							|| schoolCourseMerge.getIsComplete() != null
							&& schoolCourseMerge.getIsComplete() != 1) {
						mav.addObject("isPartComplete", 1);
					}
				} else {
					mav.addObject("isComplete", 0);
					List<TimetableGroup> groups = new LinkedList<TimetableGroup>();
					Integer count = 0;
					for (TimetableGroup t : newTimetableCourseSchedulingService
							.findTimetableGroupsByBacthId(timetableBatch
									.getId())) {
						// 已排课,保留分组，并在所有学生名单中除去这些学生
						if (t.getTimetableAppointments() != null
								&& t.getTimetableAppointments().size() != 0) {
							numbers -= t.getNumbers();
							count++;
							t.setGroupName("第" + count + "批");
							t = timetableGroupDAO.store(t);
							groups.add(t);
						}// 属于其他实验室，且未排课，删除这一分组
						else if (t.getLabRoom().getId().intValue() != labRoom
								.getId().intValue()
								&& (t.getTimetableAppointments() == null || t
										.getTimetableAppointments() != null
										&& t.getTimetableAppointments().size() == 0)) {
							timetableGroupDAO.remove(t);
						} else if (t.getLabRoom().getId().intValue() == labRoom
								.getId().intValue()
								&& t.getTimetableStyle() != 24
								&& (t.getTimetableAppointments() == null || t
										.getTimetableAppointments() != null
										&& t.getTimetableAppointments().size() == 0)) {
							timetableGroupDAO.remove(t);
						} else {
							numbers -= t.getNumbers();
							count++;
							t.setGroupName("第" + count + "批");
							t = timetableGroupDAO.store(t);
							groups.add(t);
						}
					}
					timetableBatch = timetableBatchDAO
							.findTimetableBatchById(timetableBatch.getId());
					int studentNumber = numbers;
					int groupNumber = studentNumber / capacity;
					if (studentNumber % capacity != 0)
						groupNumber += 1;
					for (int i = 1; i < groupNumber + 1; i++) {
						count++;
						TimetableGroup timetableGroup = new TimetableGroup();
						timetableGroup.setTimetableBatch(timetableBatch);
						timetableGroup.setGroupName("第" + count + "批");
						if (i != groupNumber) {
							timetableGroup.setNumbers(capacity);
						} else {
							timetableGroup.setNumbers(studentNumber - capacity
									* (groupNumber - 1));
						}
						timetableGroup.setLabRoom(labRoom);
						timetableGroup.setTimetableStyle(24);
						timetableGroup = timetableGroupDAO
								.store(timetableGroup);
						groups.add(timetableGroup);
					}
					timetableBatch = timetableBatchDAO
							.findTimetableBatchById(timetableBatch.getId());
					mav.addObject("groups", groups);
					if (schoolCourseMerge.getCourseStart() != null) {
						mav.addObject("startDate", sdf.format(schoolCourseMerge
								.getCourseStart().getTime()));
					}
					if (schoolCourseMerge.getCourseEnd() != null) {
						mav.addObject("endDate", sdf.format(schoolCourseMerge
								.getCourseEnd().getTime()));
					}
				}
			}
			mav.addObject("timetableBatch", timetableBatch);
			batchId = timetableBatch.getId();
			mav.addObject("notSelectLab", 0);
		} else {
			TimetableBatch timetableBatch = newTimetableCourseSchedulingService
					.findTimetableBatchByCourseDetailNoAndTypeAndItemId(
							mergeId.toString(), 24, itemId);
			if (timetableBatch == null) {
				mav.addObject("isComplete", 0);
			} else {
				batchId = timetableBatch.getId();
				if (schoolCourseMerge.getIsComplete() != null
						&& schoolCourseMerge.getIsComplete() == 1
						|| timetableBatch.getIsComplete() != null
						&& timetableBatch.getIsComplete() == 1) {
					mav.addObject("groups", newTimetableCourseSchedulingService
							.findTimetableGroupsByBacthId(timetableBatch
									.getId()));
					mav.addObject("isComplete", 1);
					if (schoolCourseMerge.getIsComplete() == null
							|| schoolCourseMerge.getIsComplete() != null
							&& schoolCourseMerge.getIsComplete() != 1) {
						mav.addObject("isPartComplete", 1);
					}

				} else {
					mav.addObject("isComplete", 0);
					mav.addObject("groups", newTimetableCourseSchedulingService
							.findTimetableGroupsByBacthId(timetableBatch
									.getId()));
				}
			}

			mav.addObject("notSelectLab", 1);
		}
		mav.addObject("batchId", batchId);
		Calendar cal2 = Calendar.getInstance();
		System.out.println(cal2.getTimeInMillis() - cal1.getTimeInMillis());
		mav.setViewName("newtimetable/doMergeCourseTimetable.jsp");
		return mav;
	}

	/************************************************************
	 * @description：完成某门课程的选课（合班课程）
	 * @author：郑昕茹
	 * @date：2017-05-02
	 ************************************************************/
	@RequestMapping("/courseMergeSubmit")
	public ModelAndView courseMergeSubmit(@RequestParam Integer mergeId) {
		ModelAndView mav = new ModelAndView();
		SchoolCourseMerge schoolCourseMerge = schoolCourseMergeDAO
				.findSchoolCourseMergeById(mergeId);
		Set<SchoolCourseDetail> details = schoolCourseMerge
				.getSchoolCourseDetails();
		if (details != null && details.size() != 0) {
			for (SchoolCourseDetail schoolCourseDetail : details) {
				Set<SchoolCourseStudent> students = schoolCourseDetail
						.getSchoolCourseStudents();
				if (students != null && students.size() != 0) {
					for (SchoolCourseStudent s : students) {
						if (s.getUserByStudentNumber() != null
								&& s.getUserByStudentNumber()
										.getUsername()
										.equals(shareService.getUser()
												.getUsername())) {
							s.setIsSelect(1);
							schoolCourseStudentDAO.store(s);
						}
					}
				}
			}
		}
		mav.setViewName("redirect:/newtimetable/doStudentCourseSelect");
		return mav;
	}

	/************************************************************
	 * @description：完成某门课程的选课（）
	 * @author：郑昕茹
	 * @date：2017-04-26
	 ************************************************************/
	@RequestMapping("/courseSubmit")
	public ModelAndView courseSubmit(@RequestParam String courseDetailNo) {
		ModelAndView mav = new ModelAndView();
		SchoolCourseDetail schoolCourseDetail = schoolCourseDetailDAO
				.findSchoolCourseDetailByCourseDetailNo(courseDetailNo);
		Set<SchoolCourseStudent> students = schoolCourseDetail
				.getSchoolCourseStudents();
		if (students != null && students.size() != 0) {
			for (SchoolCourseStudent s : students) {
				if (s.getUserByStudentNumber() != null
						&& s.getUserByStudentNumber().getUsername()
								.equals(shareService.getUser().getUsername())) {
					s.setIsSelect(1);
					schoolCourseStudentDAO.store(s);
				}
			}
		}
		mav.setViewName("redirect:/newtimetable/doStudentCourseSelect");
		return mav;
	}

	/*********************************************************************************
	 * description： 课程安排（中心管理员） author：郑昕茹 date：2017-04-14
	 *********************************************************************************/
	@RequestMapping("/listLabSchoolCourseDetailPermissions")
	public ModelAndView listLabSchoolCourseDetailPermissions(
			HttpServletRequest request,
			@ModelAttribute SchoolCourseDetail schoolCourseDetail,
			@RequestParam int currpage) {
		ModelAndView mav = new ModelAndView();

		// 登陆人权限获得
		mav.addObject("user", shareService.getUser());
		int pageSize = 30;
		
		List<SchoolCourseDetail> detailListAll = newTimetableCourseSchedulingService
		.findLabSchoolCourseDetailUnderPermission(schoolCourseDetail,
				1, -1, request);
		
		int totalRecords = detailListAll.size();
		List<SchoolCourseDetail> courseDetailList = newTimetableCourseSchedulingService
				.findLabSchoolCourseDetailUnderPermission(schoolCourseDetail,
						currpage, pageSize, request);
		mav.addObject("detailListAll", detailListAll);
		Map<String, Integer> pageModel = shareService.getPage(currpage,
				pageSize, totalRecords);
		mav.addObject("pageModel", pageModel);
		mav.addObject("totalRecords", totalRecords);
		mav.addObject("currpage", currpage);
		mav.addObject("pageSize", pageSize);

		mav.addObject("schoolCourseDetail", schoolCourseDetail);
		// 查找所有的实验大纲
		mav.addObject("courseDetailList", courseDetailList);
		List<SchoolTerm> schoolTerms = outerApplicationService.getSchoolTermList();
		mav.addObject("schoolTerms", schoolTerms);
		mav.addObject("teachers",
				shareService.findAllTeacheresByAcademyNumber("-1"));
		mav.setViewName("newtimetable/listLabSchoolCourseDetailPermissions.jsp");
		return mav;
	}

	/*********************************************************************************
	 * description： 设置排课教师 author：戴昊宇 date：2017-08-22
	 *********************************************************************************/
	@RequestMapping("/deletescheduleTeacher")
	public String deletescheduleTeacher(String name, String detailNo) {
		SchoolCourseDetail courseDetail = schoolCourseDetailDAO
				.findSchoolCourseDetailByCourseDetailNo(detailNo);
		Set<User> users = courseDetail.getUserByScheduleTeachers();
		User user = userDAO.findUserByPrimaryKey(name);
		users.remove(user);
		courseDetail.setUserByScheduleTeachers(users);
		schoolCourseDetailDAO.store(courseDetail);
		return "redirect:/newtimetable/listLabSchoolCourseDetailPermissions?currpage=1";

	}

}
