package net.xidlims.web.timetable;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import net.xidlims.constant.CommonConstantInterface;
import net.xidlims.service.common.ShareService;
import net.xidlims.service.timetable.OuterApplicationService;
import net.xidlims.service.timetable.SchoolCourseDetailService;
import net.xidlims.service.timetable.SchoolCourseInfoService;
import net.xidlims.service.timetable.TimetableAppointmentService;
import net.xidlims.service.timetable.TimetableReSchedulingService;
import net.xidlims.service.timetable.TimetableSelfCourseService;
import net.xidlims.service.timetable.TimetableSelfSchedulingService;
import net.xidlims.dao.LabCenterDAO;
import net.xidlims.dao.OperationItemDAO;
import net.xidlims.dao.SchoolCourseInfoDAO;
import net.xidlims.dao.SchoolTermDAO;
import net.xidlims.dao.TimetableAppointmentDAO;
import net.xidlims.dao.TimetableCourseStudentDAO;
import net.xidlims.dao.TimetableGroupDAO;
import net.xidlims.dao.TimetableSelfCourseDAO;
import net.xidlims.dao.UserDAO;
import net.xidlims.domain.AssetCabinetWarehouseAccessRecord;
import net.xidlims.domain.AssetReceive;
import net.xidlims.domain.AssetReceiveAllocation;
import net.xidlims.domain.AssetReceiveRecord;
import net.xidlims.domain.SchoolCourseDetail;
import net.xidlims.domain.SchoolCourseInfo;
import net.xidlims.domain.SchoolTerm;
import net.xidlims.domain.TimetableAppointment;
import net.xidlims.domain.TimetableCourseStudent;
import net.xidlims.domain.TimetableGroup;
import net.xidlims.domain.TimetableSelfCourse;

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
 * 功能：自主管理模块 作者：魏诚 时间：2014-07-14
 *****************************************************************************************/
@Controller("TimetableSelfSchedulingController")
@SessionAttributes("selected_labCenter")
public class TimetableSelfSchedulingController<JsonResult> {
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
	private OuterApplicationService outerApplicationService;
	@Autowired
	private TimetableAppointmentService timetableAppointmentService;
	@Autowired
	private SchoolTermDAO schoolTermDAO;
	@Autowired
	private SchoolCourseDetailService schoolCourseDetailService;
	@Autowired
	private ShareService shareService;
	@Autowired
	private OperationItemDAO operationItemDAO;
	@Autowired
	private TimetableGroupDAO timetableGroupDAO;
	@Autowired
	private TimetableAppointmentDAO timetableAppointmentDAO;
	@Autowired
	private TimetableReSchedulingService timetableReSchedulingService;
	@Autowired
	private TimetableSelfCourseDAO timetableSelfCourseDAO;
	@Autowired
	private TimetableSelfSchedulingService timetableSelfSchedulingService;
	@Autowired
	private SchoolCourseInfoService schoolCourseInfoService;
	@Autowired
	private SchoolCourseInfoDAO schoolCourseInfoDAO;
	@Autowired
	private TimetableSelfCourseService timetableSelfCourseService;
	@Autowired
	private TimetableCourseStudentDAO timetableCourseStudentDAO;
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private LabCenterDAO labCenterDAO;

	/**************************************************************************************
	 * @自主排课： 保存实现自主排课（不含分组）的内容
	 * @throws ParseException 
	 * @页面跳转：listSelfTimetable-listSelfTimetable-doIframeGroupSelfTimetable-saveGroupSelfTimetable
	 * @作者：魏诚
	 * @日期：2014-07-25
	 *************************************************************************************/
	@RequestMapping("/timetable/selfTimetable/saveNoGroupSelfTimetable")
	public ModelAndView saveNoGroupSelfTimetable(HttpServletRequest request) throws ParseException {
		ModelAndView mav = new ModelAndView();
		// 保存调整排课数据
		timetableSelfSchedulingService.saveNoGroupSelfTimetable(request);

		String rtUrl = "redirect:/timetable/selfTimetable/doIframeNoGroupSelfTimetable?term="
				+ request.getParameter("term") + "&courseCode=" + request.getParameter("courseCode") + "&classids="
				+ request.getParameter("classids") + "&weekday=" + request.getParameter("weekday") + "&labroom="
				+ request.getParameterValues("labRooms")[0]+"&tableAppId=0";
		mav.setViewName(rtUrl);
		return mav;

	}

	/**************************************************************************************
	 * @自主排课： 保存实现自主排课（分组）的内容
	 * @throws ParseException 
	 * @页面跳转：listSelfTimetable-listSelfTimetable-doIframeGroupSelfTimetable-saveGroupSelfTimetable
	 * @作者：魏诚
	 * @日期：2014-07-25
	 *************************************************************************************/
	@RequestMapping("/timetable/selfTimetable/saveGroupSelfTimetable")
	public ModelAndView saveGroupSelfTimetable(HttpServletRequest request) throws ParseException {
		ModelAndView mav = new ModelAndView();
		// TimetableGroup timetableGroup = new TimetableGroup();
		// 如果已保存排课记录，先删除，再保存修改。保存待删除的标记位
		TimetableAppointment deleteTimetableAppointment = null;
		if (!request.getParameter("appointment").isEmpty()) {
			deleteTimetableAppointment = timetableAppointmentDAO.findTimetableAppointmentById(Integer.parseInt(request
					.getParameter("appointment")));
		}
		// 保存调整排课数据
		TimetableAppointment timetableAppointment = timetableSelfSchedulingService.saveGroupReTimetable(request);
		// timetableGroup =
		// timetableGroupDAO.findTimetableGroupById(Integer.parseInt(request.getParameter("group")));

		// 获取选课组
		mav.addObject("courseCode", request.getParameter("courseCode"));
		mav.setViewName("redirect:/timetable/selfTimetable/doIframeGroupSelfTimetable?term="
				+ timetableAppointment.getTimetableSelfCourse().getSchoolTerm().getId() + "&courseCode="
				+ request.getParameter("courseCode"));
		// 删除修改前的记录
		if (deleteTimetableAppointment != null) {
			timetableAppointmentService.deleteAppointment(deleteTimetableAppointment.getId());
		}
		return mav;

	}

	/**************************************************************************************
	 * @排课管理的删除排课条目
	 * @页面跳转：timetableAdmin-timetableAdminIframe-deleteAppointment
	 * @作者：魏诚
	 * @日期：2014-07-25
	 *************************************************************************************/
	@RequestMapping("/timetable/selfTimetable/deleteSelfGroupAppointment")
	public ModelAndView deleteSelfGroupAppointment(@RequestParam int id) {
		ModelAndView mav = new ModelAndView();
		TimetableAppointment timetableAppointment = timetableAppointmentDAO.findTimetableAppointmentById(id);
		// 排课管理的删除排课条目
		timetableAppointmentService.deleteAppointment(id);
		mav.setViewName("redirect:/timetable/selfTimetable/doIframeGroupSelfTimetable?term="
				+ timetableAppointment.getTimetableSelfCourse().getSchoolTerm().getId() + "&courseCode="
				+ timetableAppointment.getTimetableSelfCourse().getId());
		return mav;
	}

	/**************************************************************************************
	 * @自主排课： 实现自主排课（含分组）的功能入口
	 * @页面跳转：listSelfTimetable
	 * @作者：魏诚
	 * @日期：2014-07-25
	 *************************************************************************************/
	@RequestMapping("/timetable/selfTimetable/listSelfTimetable")
	public ModelAndView listSelfTimetable(HttpServletRequest request, @ModelAttribute("selected_labCenter") Integer cid) {
		ModelAndView mav = new ModelAndView();
		if (cid == null) {
			cid = -1;
		}
		mav.addObject("labroom", request.getParameter("labroom"));
		mav.addObject("courseCode", request.getParameter("courseCode"));
		// 获取登录用户信息
		mav.addObject("user", shareService.getUserDetail());
		// 获取学期列表
		List<SchoolTerm> schoolTerms = outerApplicationService.getSchoolTermList();
		mav.addObject("schoolTerm", schoolTerms);
		// 根据课程及id获取选课组列表
		//int term = shareService.findNewTerm().getId();获取当前时段所处学期或下个新学期
		int term = shareService.getBelongsSchoolTerm(Calendar.getInstance()).getId();
		if (request.getParameter("term") != null) {
			term = Integer.parseInt(request.getParameter("term"));
		}
		mav.addObject("term", term);
		mav.addObject("courseCodes", schoolCourseDetailService.getCourseCodeInSchoolCourse(term, cid));
		// 获取去重的实验分室室列表
		mav.addObject("labRoomMap", outerApplicationService.getLabRoomMap(cid));

		// 获取实验分室室排课记录：二次排课数据集
		mav.addObject("labReTimetable",
				timetableSelfSchedulingService.getListLabTimetableAppointments(request, cid, term));
		// 自建课程数据集
		mav.addObject("labSelfTimetable",
				timetableSelfSchedulingService.getSelfListLabTimetableAppointments(request, cid, term));
		mav.setViewName("timetable/selfTimetable/listSelfTimetable.jsp");
		return mav;
	}

	/**************************************************************************************
	 * @自主排课： 实现自主排课的iframe功能入口（含分组）
	 * @页面跳转：listSelfTimetable-listSelfTimetable-doIframeGroupSelfTimetable
	 * @作者：魏诚
	 * @日期：2014-07-25
	 *************************************************************************************/
	@RequestMapping("/timetable/selfTimetable/doIframeSelfTimetable")
	public ModelAndView doIframeSelfTimetable(HttpServletRequest request, @RequestParam int term, int weekday,
			String classids, @ModelAttribute("selected_labCenter") Integer cid) {
		ModelAndView mav = new ModelAndView();
		if (cid == null) {
			cid = -1;
		}
		/*
		 * 获取查询条件
		 */

		mav.addObject("term", request.getParameter("term"));
		mav.addObject("labroom", request.getParameter("labroom"));
		// mav.addObject("week", request.getParameter("week"));
		mav.addObject("courseCode", request.getParameter("courseCode"));
		// 获取登录用户信息
		mav.addObject("user", shareService.getUserDetail());
		// 获取学期列表
		mav.addObject("schoolTerm", schoolTermDAO.findAllSchoolTerms());
		// 获取星期
		mav.addObject("weekday", weekday);
		// 根据课程及id获取课程排课列表
		SchoolCourseDetail schoolCourseDetail = new SchoolCourseDetail();
		schoolCourseDetail.setId(-1);
		mav.addObject("schedulingCourseMap",
				schoolCourseDetailService.getCourseDetailsByQuery(term, schoolCourseDetail, 0, -1, cid));

		mav.addObject("courseCodes", schoolCourseDetailService.getCourseCodeInSchoolCourse(term, cid));
		// 获取星期
		mav.addObject("classids", classids);
		// 获取实验分室室排课记录
		mav.addObject("labTimetable",
				timetableAppointmentService.getSelfListLabTimetableAppointments(request, cid, term));
		mav.setViewName("timetable/selfTimetable/listIframeSelfTimetable.jsp");
		return mav;
	}

	/**************************************************************************************
	 * @自主排课： 实现自主排课的不分组功能入口
	 * @页面跳转：listSelfTimetable-listSelfTimetable-doIframeGroupSelfTimetable
	 * @作者：魏诚
	 * @日期：2014-07-25
	 *************************************************************************************/
	@RequestMapping("/timetable/selfTimetable/doIframeNoGroupSelfTimetable")
	public ModelAndView doIframeNoGroupSelfTimetable(HttpServletRequest request, @RequestParam int term, int weekday,
			String classids, String courseCode, int labroom, Integer tableAppId, @ModelAttribute("selected_labCenter") Integer cid) {
		//tableAppId==0表示新建 tableAppId!=0表示编辑，编辑的时候，可选周次的判断方法就不能与自身判冲
		ModelAndView mav = new ModelAndView();
		if (cid == null) {
			cid = -1;
		}
		// 获取去重的实验分室室列表
		mav.addObject("labRoomMap", outerApplicationService.getLabRoomMap(cid));
		// 获取可选的实验项目列表列表
		mav.addObject(
				"timetableItemMap",
				outerApplicationService.getTimetableItemMap(timetableSelfCourseDAO
						.findTimetableSelfCourseById(Integer.parseInt(courseCode)).getSchoolCourseInfo()
						.getCourseNumber()));

		// 获取可选的教师列表列表
		mav.addObject("timetableTearcherMap", outerApplicationService.getTimetableTearcherMap());
		// 获取学期列表
		mav.addObject("schoolTerm", schoolTermDAO.findSchoolTermById(term));
		mav.addObject("term", term);
		// 根据选课组编号获取课程排课列表
		mav.addObject("timetableAppointmentMap", timetableAppointmentDAO
				.findTimetableAppointmentByCourseCode(timetableSelfCourseDAO.findTimetableSelfCourseById(
						Integer.parseInt(courseCode)).getCourseCode()));
		// 获取星期
		mav.addObject("weekday", weekday);
		// mav.addObject("week", week);
		// LabRoom labRoom = labRoomDAO.findLabRoomById(labroom);
		mav.addObject("labroom", labroom);
		// 根据所选节次获取可用的周次
		Integer[] weeks = outerApplicationService.getValidWeeks(term, Integer.parseInt(classids), labroom, weekday);
		mav.addObject("weeks", weeks);
		mav.addObject("courseCode", courseCode);
		mav.addObject("coursecode", courseCode);
		// 根据课程及id获取课程排课列表
		mav.addObject("courseCodes", timetableSelfCourseDAO.findTimetableSelfCourseById(Integer.parseInt(courseCode)));
		// 获取星期
		mav.addObject("classids", classids);
		// 获取登录用户信息
		mav.addObject("user", shareService.getUserDetail());
		// 获取实验分室室排课记录
		mav.addObject("labTimetable",
				timetableAppointmentService.getSelfListLabTimetableAppointments(request, cid, term));
		//将排课id映射给jsp
		mav.addObject("tableAppId", tableAppId);
		if (tableAppId!=0&&tableAppId!=-1) {
			mav.addObject("timetableAppointment", timetableAppointmentDAO.findTimetableAppointmentByPrimaryKey(tableAppId));
		}
		// 如果權限為true則，進行排課處理，否則返回列表
		if (timetableAppointmentDAO.findTimetableAppointmentByCourseCode(
				timetableSelfCourseDAO.findTimetableSelfCourseById(Integer.parseInt(courseCode)).getCourseCode())
				.size() == 0
				|| shareService.isOrNotAuthority(timetableAppointmentDAO
						.findTimetableAppointmentByCourseCode(
								timetableSelfCourseDAO.findTimetableSelfCourseById(Integer.parseInt(courseCode))
										.getCourseCode()).iterator().next())) {
			mav.setViewName("timetable/selfTimetable/listIframeNoGroupSelfTimetable.jsp");
			return mav;
		} else {
			mav.setViewName("timetable/reTimetable/warning.jsp");
			return mav;
		}
	}

	/**************************************************************************************
	 * @自主排课：实现自主排课的分组排课功能入口
	 * @页面跳转：listSelfTimetable-listSelfTimetable-doIframeGroupSelfTimetable
	 * @作者：魏诚
	 * @日期：2014-07-25
	 *************************************************************************************/
	@RequestMapping("/timetable/selfTimetable/doIframeGroupSelfTimetable")
	public ModelAndView doIframeGroupSelfTimetable(HttpServletRequest request,
			@ModelAttribute("selected_labCenter") Integer cid) {
		ModelAndView mav = new ModelAndView();
		if (cid == null) {
			cid = -1;
		}
		// 获取去重的实验分室室列表
		mav.addObject("labRoomMap", outerApplicationService.getLabRoomMap(cid));
		// 获取可选的教师列表列表
		mav.addObject("timetableTearcherMap", outerApplicationService.getTimetableTearcherMap());
		// 获取学期列表
		mav.addObject("schoolTerm", schoolTermDAO.findSchoolTermById(Integer.parseInt(request.getParameter("term"))));
		// 获取选课组
		mav.addObject("courseCode", request.getParameter("courseCode"));
		// 根据选课组获取课程排课列表
		mav.addObject("courseCodes", timetableSelfCourseDAO.findTimetableSelfCourseById(Integer.parseInt(request
				.getParameter("courseCode"))));
		// 获取登录用户信息
		mav.addObject("user", shareService.getUserDetail());
		// 获取批次列表
		mav.addObject("timetableGroups", timetableReSchedulingService
				.getTimetableGroupByCourseCode(timetableSelfCourseDAO.findTimetableSelfCourseById(
						Integer.parseInt(request.getParameter("courseCode"))).getCourseCode()));
		mav.addObject("countGroup", request.getParameter("countGroup"));
		if(request.getParameter("countGroup") != null){
			int countGroup = Integer.parseInt(request.getParameter("countGroup"));
			for(Integer i = 1; i <= countGroup; i++){
				mav.addObject("student_"+i.toString(),request.getParameterValues("student_"+i.toString()));
			}
		}
		// 如果權限為true則，進行排課處理，否則返回列表
		if (timetableAppointmentDAO.findTimetableAppointmentByCourseCode(
				timetableSelfCourseDAO
						.findTimetableSelfCourseById(Integer.parseInt(request.getParameter("courseCode")))
						.getCourseCode()).size() == 0
				|| shareService.isOrNotAuthority(timetableAppointmentDAO
						.findTimetableAppointmentByCourseCode(
								timetableSelfCourseDAO.findTimetableSelfCourseById(
										Integer.parseInt(request.getParameter("courseCode"))).getCourseCode())
						.iterator().next())) {
			mav.setViewName("timetable/selfTimetable/listIframeGroupSelfTimetable.jsp");
			return mav;
		} else {
			mav.setViewName("timetable/reTimetable/warning.jsp");
			return mav;
		}
	}

	/**************************************************************************************
	 * @自主排课：实现含分组自主排课的选择选课组后的iframe功能入口
	 * @页面跳转：listSelfTimetable-listSelfTimetable-doIframeGroupSelfTimetable
	 * @作者：魏诚
	 * @日期：2014-07-25
	 *************************************************************************************/
	@RequestMapping("/timetable/selfTimetable/doSelfTimetableIframe")
	public ModelAndView doSelfTimetableIframe(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("term", request.getParameter("term"));
		mav.addObject("labroom", request.getParameter("labroom"));
		mav.addObject("weekday", request.getParameter("weekday"));
		mav.addObject("classids", request.getParameter("classids"));
		mav.addObject("courseCode", request.getParameter("courseCode"));
		// 判断跳转分批、不分批或全部
		TimetableSelfCourse timetableSelfCourse = timetableSelfCourseDAO.findTimetableSelfCourseById(Integer
				.parseInt(request.getParameter("courseCode")));
		if (timetableSelfCourse != null
				&& timetableAppointmentDAO.findTimetableAppointmentByCourseCode(timetableSelfCourse.getCourseCode()) != null
				&& !timetableAppointmentDAO.findTimetableAppointmentByCourseCode(timetableSelfCourse.getCourseCode())
						.isEmpty()
				&& timetableAppointmentDAO.findTimetableAppointmentByCourseCode(timetableSelfCourse.getCourseCode())
						.iterator() != null) {
			mav.addObject(
					"groups",
					timetableAppointmentDAO
							.findTimetableAppointmentByCourseCode(
									timetableSelfCourseDAO.findTimetableSelfCourseById(
											Integer.parseInt(request.getParameter("courseCode"))).getCourseCode())
							.iterator().next().getTimetableGroups().size());
		} else {
			mav.addObject("groups", "-1");
		}
		mav.setViewName("timetable/selfTimetable/listSelfTimetableIframe.jsp");
		return mav;
	}

	/**************************************************************************************
	 * @自主排课：实现含分组自主排课的选择选课组页面功能入口
	 * @页面跳转：listSelfTimetable-listSelfTimetable-doIframeGroupSelfTimetable-doSelectCourseCode
	 * @作者：魏诚
	 * @日期：2014-07-25
	 *************************************************************************************/
	@RequestMapping("/timetable/selfTimetable/doSelectCourseCode")
	public ModelAndView doSelectCourseCode(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("courseCodes", timetableSelfCourseService.getTimetableSelfCourseByTermAcademy(request));
		mav.addObject("term", request.getParameter("term"));
		mav.addObject("labroom", request.getParameter("labroom"));
		mav.addObject("weekday", request.getParameter("weekday"));
		mav.addObject("classids", request.getParameter("classids"));
		mav.addObject("courseCode", request.getParameter("courseCode"));

		mav.setViewName("timetable/selfTimetable/doSelectCourseCode.jsp");
		return mav;
	}

	/**************************************************************************************
	 * @自主排课：实现含分组自主排课的选择选课组页面功能入口
	 * @页面跳转：listSelfTimetable-listSelfTimetable-doIframeGroupSelfTimetable-newTimetableGroup
	 * @作者：魏诚
	 * @日期：2014-07-25
	 *************************************************************************************/
	@RequestMapping("/timetable/selfTimetable/newTimetableGroup")
	public ModelAndView newTimetableGroup(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("term", request.getParameter("term"));
		mav.addObject("labroom", request.getParameter("labroom"));
		mav.addObject("week", request.getParameter("week"));
		mav.addObject("weekday", request.getParameter("weekday"));
		mav.addObject("classids", request.getParameter("classids"));
		//mav.addObject("item", operationItemDAO.findOperationItemById(Integer.parseInt(request.getParameter("item"))));
		mav.addObject("courseCode", request.getParameter("courseCode"));
		mav.addObject("courseCodeId", request.getParameter("courseCodeId"));
		// 保存分组计数信息
		mav.addObject("countGroup", request.getParameter("countGroup"));
		// 保存批次名称
		mav.addObject("batchName", request.getParameter("batchName"));
		// 保存批次开始选课日期
		mav.addObject("startDate", request.getParameter("startDate"));
		// 保存批次结束选课日期
		mav.addObject("endDate", request.getParameter("endDate"));
		// 获取可选的实验项目列表列表
		mav.addObject("courseCodeItemList",
				outerApplicationService.getCourseCodeItemList(request.getParameter("courseCode")));
		// 根据课程及id获取课程排课列表
		// 保存学生选课信息
		mav.addObject("ifselect", request.getParameter("ifselect"));
		// 根据课程及id获取课程排课列表
		mav.addObject(
				"courseCodes",
				new ArrayList<TimetableSelfCourse>(timetableSelfCourseDAO.findTimetableSelfCourseByCourseCode(request
						.getParameter("courseCode"))).get(0));
		//获取该课程下的学生
		mav.addObject("students", timetableSelfCourseDAO.findTimetableSelfCourseById(Integer.parseInt(request
				.getParameter("courseCodeId"))).getTimetableCourseStudents());
		mav.setViewName("timetable/selfTimetable/newTimetableGroup.jsp");
		return mav;
	}
	
    /***********************************************************************************
	* @description 预约排课-自主排课｛去除已被选择的学生｝
	* @author 郑昕茹
	* @date 2016-09-01
	* **********************************************************************************/
	@RequestMapping("/timetable/selfTimetable/removeDuplicateStudents")
	public @ResponseBody Map<String, String> removeDuplicateStudents(HttpServletRequest request, @RequestParam int id,@RequestParam int countGroup){
		Set<TimetableCourseStudent> totalStudents = timetableSelfCourseDAO.findTimetableSelfCourseById(id).getTimetableCourseStudents();
		Map<String, String> map = new HashMap<String, String>();
		String s = "";
		//获取选中的物资对象
		//String studentIds[] = request.getParameterValues("students[]");
		Set<TimetableCourseStudent> removedStudents = new HashSet<TimetableCourseStudent>();
		//int currSelect = Integer.parseInt(request.getParameter("currSelect"));
		for(Integer i = 1; i <= countGroup; i++){
				String selectedStudentIds[] = request.getParameterValues("student_"+i.toString()+"[]");
				if(totalStudents != null){
					for(TimetableCourseStudent totalStudent:totalStudents){
						if(selectedStudentIds != null){
							for(String selectStudentId:selectedStudentIds){
								if(totalStudent.getId() == Integer.parseInt(selectStudentId)){
									removedStudents.add(timetableCourseStudentDAO.findTimetableCourseStudentByPrimaryKey(Integer.parseInt(selectStudentId)));
									break;
								}
							}
						}
					}
				}	
		}
		for(TimetableCourseStudent removedStudent:removedStudents){
			totalStudents.remove(removedStudent);
		}
		for(Integer i = 1; i <= countGroup; i++){
				String selectedStudentIds[] = request.getParameterValues("student_"+i.toString()+"[]");
				String ss="";
				if(selectedStudentIds != null){
					for(String studentId:selectedStudentIds){
						TimetableCourseStudent selectedStudent = timetableCourseStudentDAO.findTimetableCourseStudentByPrimaryKey(Integer.parseInt(studentId));
						ss+="<option selected=\"selected\" value=\""+selectedStudent.getId()+"\">"+selectedStudent.getUser().getCname()+"</option>";
					}
				}
				if(totalStudents != null){
					for(TimetableCourseStudent totalStudent:totalStudents){
						ss+="<option value=\""+totalStudent.getId()+"\">"+totalStudent.getUser().getCname()+"</option>";
					}
				}	
				map.put("student_"+i.toString(), shareService.htmlEncode(ss));
			
		}
		return map;
	}

	/**************************************************************************************
	 * @自主排课：保存分组信息
	 * @页面跳转：listSelfTimetable-listSelfTimetable-doIframeGroupSelfTimetable-newTimetableGroup-saveTimetableGroup
	 * @作者：魏诚
	 * @日期：2014-07-25
	 *************************************************************************************/
	@RequestMapping("/timetable/selfTimetable/saveTimetableGroup")
	public ModelAndView saveTimetableGroup(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("term", request.getParameter("term"));
		mav.addObject("item", request.getParameter("item"));
		mav.addObject("courseCodeId", request.getParameter("courseCodeId"));
		mav.addObject("countGroup", request.getParameter("countGroup"));
		timetableSelfSchedulingService.saveTimetableGroup(request);
		// 获取学期列表
		mav.addObject("schoolTerm", schoolTermDAO.findSchoolTermById(Integer.parseInt(request.getParameter("term"))));
		// 获取选课组
		mav.addObject("courseCode",
				timetableSelfCourseDAO.findTimetableSelfCourseByCourseCode(request.getParameter("courseCode"))
						.iterator().next().getId());
		// 根据选课组获取课程排课列表
		mav.addObject("courseCodes",
				timetableSelfCourseDAO.findTimetableSelfCourseByCourseCode(request.getParameter("courseCode"))
						.iterator().next());
		mav.setViewName("redirect:/timetable/selfTimetable/doIframeGroupSelfTimetable");
		return mav;
	}

	/**************************************************************************************
	 * @自主排课：实现自主排课分组进行排课,appointment 排课timetableAppointment的id，-1为新建
	 * @页面跳转：listSelfTimetable-listSelfTimetable-doIframeGroupSelfTimetable-doGroupSelfTimetable
	 * @作者：魏诚
	 * @日期：2014-07-25
	 *************************************************************************************/
	@RequestMapping("/timetable/selfTimetable/doGroupSelfTimetable")
	public ModelAndView doGroupSelfTimetable(HttpServletRequest request, @RequestParam int term, String courseCode,
			int appointment, @ModelAttribute("selected_labCenter") Integer cid) {
		ModelAndView mav = new ModelAndView();
		if (cid == null) {
			cid = -1;
		}
		// 获取去重的实验分室室列表
		mav.addObject("labRoomMap", outerApplicationService.getLabRoomMap(cid));
		// 如果为新建排课
		if (appointment == -1) {
			mav.addObject("appointment", null);
		} else {
			mav.addObject("appointment", timetableAppointmentDAO.findTimetableAppointmentById(appointment));
		}


		// 获取分组对象
		if (request.getParameter("group")!=null&&!request.getParameter("group").equals("")) {
		    TimetableGroup timetableGroup = timetableGroupDAO.findTimetableGroupById(Integer.parseInt(request
		            .getParameter("group")));
		    mav.addObject("group", timetableGroup);
		}else {//针对编辑情况
		    if (appointment != -1) {
		        Set<TimetableGroup> groups = timetableAppointmentDAO.findTimetableAppointmentById(appointment).getTimetableGroups();
		        List<TimetableGroup> groupList = new ArrayList<TimetableGroup>(groups);
		        TimetableGroup timetableGroup = groupList.get(0);
		        mav.addObject("group", timetableGroup);
		    }
		}
		
		// 获取可选的教师列表列表
		mav.addObject("timetableTearcherMap", outerApplicationService.getTimetableTearcherMap());
		// 获取学期列表
		mav.addObject("schoolTerm", schoolTermDAO.findSchoolTermById(term));
		// mav.addObject("week", week);
		mav.addObject("courseCode", courseCode);
		// 根据课程及id获取课程排课列表
		mav.addObject("courseCodes", timetableSelfCourseDAO.findTimetableSelfCourseById(Integer.parseInt(courseCode)));
		// 获取可选的实验项目列表列表
		mav.addObject(
				"timetableItemMap",
				outerApplicationService.getTimetableItemMap(timetableSelfCourseDAO
						.findTimetableSelfCourseById(Integer.parseInt(courseCode)).getSchoolCourseInfo()
						.getCourseNumber()));
		// 获取登录用户信息
		mav.addObject("user", shareService.getUserDetail());
		// 获取实验分室室排课记录
		mav.addObject("labTimetable",
				timetableAppointmentService.getSelfListLabTimetableAppointments(request, cid, term));
		
		//将排课id映射给jsp
		mav.addObject("tableAppId", appointment);
		
		mav.setViewName("timetable/selfTimetable/doGroupSelfTimetable.jsp");
		return mav;
	}

	/**************************************************************************************
	 * @自主排课： 删除id对应的批次的所有记录
	 * @页面跳转：listSelfTimetable-listSelfTimetable-doIframeGroupSelfTimetable-deleteBatch
	 * @作者：魏诚
	 * @日期：2014-07-25
	 *************************************************************************************/
	@RequestMapping("/timetable/selfTimetable/deleteBatch")
	public ModelAndView deleteBatch(@RequestParam int id, int term, String courseCode) {
		ModelAndView mav = new ModelAndView();
		// 批量删除处理
		timetableSelfCourseService.deleteBatch(id, term, courseCode);
		mav.setViewName("../../timetable/selfTimetable/doIframeGroupSelfTimetable?term=" + term + "&courseCode="
				+ courseCode);
		return mav;
	}

	/**************************************************************************************
	 * @自主排课 确认自主分组排课是否完成
	 * @页面跳转：listSelfTimetable-listSelfTimetable-doIframeGroupSelfTimetable-doReNOGroupTimetableOk
	 * @作者：魏诚
	 * @日期：2014-07-25
	 *************************************************************************************/
	@RequestMapping("/timetable/selfTimetable/doReGroupTimetableOk")
	public ModelAndView doReGroupTimetableOk(@RequestParam String courseCode, int term) {
		ModelAndView mav = new ModelAndView();
		// 根据选课组编号，获取排课信息
		List<TimetableAppointment> timetableAppointments = new ArrayList<TimetableAppointment>(
				timetableAppointmentDAO.findTimetableAppointmentByCourseCode(courseCode));
		for (TimetableAppointment timetableAppointment : timetableAppointments) {
			timetableAppointment.setStatus(2);
			timetableAppointmentDAO.store(timetableAppointment);
		}
		mav.setViewName("../../timetable/selfTimetable/doIframeGroupSelfTimetable?term=" + term + "&courseCode="
				+ courseCode);
		return mav;
	}

	/**************************************************************************************
	 * @自主排课 确认自主不分组排课是否完成--从排课页面进入
	 * @页面跳转：listSelfTimetable-listSelfTimetable-doIframeNoGroupSelfTimetable-doReNOGroupTimetableOk
	 * @作者：魏诚
	 * @日期：2014-07-25
	 *************************************************************************************/
	@RequestMapping("/timetable/selfTimetable/doSelfNOGroupTimetableOk")
	public ModelAndView doSelfNOGroupTimetableOk(@RequestParam String courseCode, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		// 根据选课组编号，获取排课信息
		List<TimetableAppointment> timetableAppointments = new ArrayList<TimetableAppointment>(
				timetableAppointmentDAO.findTimetableAppointmentByCourseCode(courseCode));
		for (TimetableAppointment timetableAppointment : timetableAppointments) {
			// 设置调整完成标记
			timetableAppointment.setStatus(2);
			timetableAppointmentDAO.store(timetableAppointment);
		}
		Integer term = -1, weekday = -1, labroom=-1, tableAppId=0;
		String classids ="1", coursecode="-1";
		if(request.getParameter("term") != null && !request.getParameter("term").equals("")){
			term = Integer.parseInt(request.getParameter("term"));
		}
		if(request.getParameter("weekday") != null && !request.getParameter("weekday").equals("")){
			weekday = Integer.parseInt(request.getParameter("weekday"));
		}
		if(request.getParameter("labroom") != null && !request.getParameter("labroom").equals("")){
			labroom = Integer.parseInt(request.getParameter("labroom"));
		}
		if(request.getParameter("tableAppId") != null && !request.getParameter("tableAppId").equals("")){
			tableAppId = Integer.parseInt(request.getParameter("tableAppId"));
		}
		if(request.getParameter("classids") != null && !request.getParameter("classids").equals("")){
			classids = request.getParameter("classids");
		}
		if(request.getParameter("classids") != null && !request.getParameter("classids").equals("")){
			classids = request.getParameter("classids");
		}
		if(request.getParameter("coursecode") != null && !request.getParameter("coursecode").equals("")){
			coursecode = request.getParameter("coursecode");
		}
		mav.setViewName("redirect:/timetable/selfTimetable/doIframeNoGroupSelfTimetable?term="+term+"&weekday="+weekday+"&classids="+classids+"&courseCode="+timetableSelfCourseDAO.findTimetableSelfCourseByCourseCode(courseCode).iterator().next().getId()+"&labroom="+labroom+"&tableAppId="+tableAppId);
		return mav;
	}
	
	/**************************************************************************************
	 * @自主排课 确认自主不分组排课是否完成--从排课管理页面进入
	 * @作者：贺子龙
	 * @日期：2016-06-05
	 *************************************************************************************/
	@RequestMapping("/timetable/selfTimetable/doSelfNOGroupTimetableOkFromAdmin")
	public ModelAndView doSelfNOGroupTimetableOkFromAdmin(@RequestParam String courseCode) {
		ModelAndView mav = new ModelAndView();
		// 根据选课组编号，获取排课信息
		List<TimetableAppointment> timetableAppointments = new ArrayList<TimetableAppointment>(
				timetableAppointmentDAO.findTimetableAppointmentByCourseCode(courseCode));
		for (TimetableAppointment timetableAppointment : timetableAppointments) {
			// 设置调整完成标记
			timetableAppointment.setStatus(2);
			timetableAppointmentDAO.store(timetableAppointment);
		}
		mav.setViewName("redirect:/timetable/timetableAdminIframe?currpage=1&id=-1&status=-1");
		return mav;
	}

	/**************************************************************************************
	 * @自主排课 新建自主排课的选课组iframe页面
	 * @页面跳转：listSelfTimetable-newCourseCodeIframeMain
	 * @作者：魏诚
	 * @日期：2014-07-25
	 *************************************************************************************/
	@RequestMapping("/timetable/selfTimetable/newCourseCodeIframeMain")
	public ModelAndView newCourseCodeIframeMain() {
		ModelAndView mav = new ModelAndView();
		// 根据选课组编号，获取排课信息
		mav.setViewName("timetable/selfTimetable/newCourseCodeIframeMain.jsp");
		return mav;
	}

	/**************************************************************************************
	 * @自主排课 新建自主排课的选课组iframe课程查看页面
	 * @页面跳转：listSelfTimetable-newCourseCodeIframeMain-listNewCourseCodeIframe
	 * @作者：魏诚
	 * @日期：2014-07-25
	 *************************************************************************************/
	@RequestMapping("/timetable/selfTimetable/listNewCourseCodeIframe")
	public ModelAndView listNewCourseCodeIframe() {
		ModelAndView mav = new ModelAndView();
		List<SchoolTerm> schoolTerms = outerApplicationService.getSchoolTermList();
		mav.addObject("schoolTerms", schoolTerms);
		// 根据选课组编号，获取排课信息
		mav.setViewName("timetable/selfTimetable/listNewCourseCodeIframe.jsp");
		return mav;
	}

	/**************************************************************************************
	 * @自主排课 新建自主排课的选课组iframe选课组新建页面
	 * @页面跳转：listSelfTimetable-newCourseCodeIframeMain-newCourseCodeIframeDetail
	 * @作者：魏诚
	 * @日期：2014-07-25
	 *************************************************************************************/
	@RequestMapping("/timetable/selfTimetable/newCourseCodeIframeDetail")
	public ModelAndView newCourseCodeIframeDetail(@RequestParam int id,
			@ModelAttribute("selected_labCenter") Integer cid) {
		ModelAndView mav = new ModelAndView();
		// 登陆用户
		mav.addObject("user", shareService.getUserDetail());

		// 获取最大的id
		int maxId = timetableSelfCourseService.getTimetableSelfCourseTotalRecords();
		SimpleDateFormat sdf = new SimpleDateFormat("mmss");
		// 当前时间
		Calendar currTime = Calendar.getInstance();
		String dateStr = sdf.format(currTime.getTime());
		mav.addObject("maxId", maxId+"-"+dateStr);
		// id为-1时，新增，否则为修改
		mav.addObject("flagId", id);
		//
		TimetableSelfCourse timetableSelfCourse = new TimetableSelfCourse();
		if (id != -1) {
			timetableSelfCourse = timetableSelfCourseDAO.findTimetableSelfCourseById(id);
		}
		mav.addObject("timetableSelfCourse", timetableSelfCourse);
		// 获取学期列表
		List<SchoolTerm> schoolTerms = outerApplicationService.getSchoolTermList();
		mav.addObject("schoolTerms", schoolTerms);
		// 获取所有课程列表
		mav.addObject("schoolCourses", schoolCourseInfoService.getCourseInfoByQuery(null,cid, -1, 1, -1));
		// 获取可选的教师列表列表
		mav.addObject("timetableTearcherMap", outerApplicationService.getTimetableTearcherMap());
		// mav.addObject("timetableSelfCourse", new TimetableSelfCourse());
		// 获取当前学院下的学生
		/*mav.addObject(
				"userList",
				userDAO.executeQuery("select c from User c where c.schoolAcademy.academyNumber like '"
						+ labCenterDAO.findLabCenterById(cid).getSchoolAcademy().getAcademyNumber()
						+ "' and grade like '2014'"));*/
		mav.addObject(
				"userList",
				userDAO.executeQuery("select c from User c where grade like '2014'"));
		// 获取当前学院下的年级
		mav.addObject(
				"grade",
				userDAO.executeQuery("select c from User c where 1=1 group by c.grade"));

		mav.setViewName("/timetable/selfTimetable/newCourseCodeIframeDetail.jsp");
		return mav;
	}

	/**************************************************************************************
	 * @自主排课 查看选课组学生选课名单清单页面
	 * @页面跳转：listSelfTimetable-newCourseCodeIframeMain-newCourseCodeIframeDetail
	 * @作者：魏诚
	 * @日期：2014-07-25
	 *************************************************************************************/
	@RequestMapping("/timetable/selfTimetable/listTimetableCourseStudents")
	public ModelAndView listTimetableCourseStudents(@RequestParam int id) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("id", id);
		// 获取可选的教师列表列表
		mav.addObject("timetableCourseStudents", timetableCourseStudentDAO.executeQuery(
				"select c from TimetableCourseStudent c where c.timetableSelfCourse.id =" + id, 0, -1));
		// mav.addObject("timetableSelfCourse", new TimetableSelfCourse());

		mav.setViewName("timetable/selfTimetable/listTimetableCourseStudents.jsp");
		return mav;
	}

	/**************************************************************************************
	 * @自主排课 批量删除学生名单
	 * @页面跳转：listSelfTimetable-listTimetableCourseStudents-batchDeleteCourseStudents
	 * @作者：魏诚
	 * @日期：2014-07-25
	 *************************************************************************************/
	@RequestMapping("/timetable/selfTimetable/batchDeleteCourseStudents")
	public ModelAndView batchDeleteCourseStudents(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		String[] ids = request.getParameterValues("VoteOption1");
		if (ids!=null && !ids.equals("") && ids.length>0) {
			for (String id : ids) {
				timetableCourseStudentDAO.remove(timetableCourseStudentDAO.findTimetableCourseStudentById(Integer
						.parseInt(id)));
			}
 		}

		mav.setViewName("redirect:/timetable/selfTimetable/listTimetableCourseStudents?id="
				+ request.getParameter("id"));
		return mav;
	}

	/**************************************************************************************
	 * @自主排课 保存 新建自主排课的选课组
	 * @页面跳转：listSelfTimetable-newCourseCodeIframeDetail-saveCourseCodeIframeDetail
	 * @作者：魏诚
	 * @日期：2014-07-25
	 *************************************************************************************/
	@RequestMapping("/timetable/selfTimetable/saveTimetableSelfCourse")
	public ModelAndView saveTimetableSelfCourse(HttpServletRequest request,
			@ModelAttribute TimetableSelfCourse timetableSelfCourse, @RequestParam int flagID) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName(timetableSelfCourseService.saveTimetableSelfCourse(request, timetableSelfCourse, flagID));
		return mav;
	}

	/**************************************************************************************
	 * @自主排课 保存 新建自主排课的选课组
	 * @页面跳转：listSelfTimetable-newCourseCodeIframeDetail-saveCourseCodeIframeDetail
	 * @作者：魏诚
	 * @日期：2014-07-25
	 *************************************************************************************/
	@RequestMapping("/timetable/selfTimetable/deleteTimetableSelfCourse")
	public ModelAndView deleteTimetableSelfCourse(@RequestParam int id) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("../../timetable/selfTimetable/listCourseCodes?currpage=1");
		// 删除指定id的TimetableSelfCourse
		timetableSelfCourseDAO.remove(timetableSelfCourseDAO.findTimetableSelfCourseById(id));
		return mav;
	}

	/**************************************************************************************
	 * @自主排课 查看自主排课的选课组
	 * @页面跳转：listSelfTimetable-newCourseCodeIframeDetail-saveCourseCodeIframeDetail
	 * @作者：魏诚
	 * @日期：2014-07-25
	 *************************************************************************************/
	@RequestMapping("/timetable/selfTimetable/listCourseCodes")
	public ModelAndView listCourseCodes(@ModelAttribute TimetableSelfCourse timetableSelfCourse,
			@RequestParam int currpage, @ModelAttribute("selected_labCenter") Integer cid) {
		ModelAndView mav = new ModelAndView();
		// 学期
		int termId = shareService.getBelongsSchoolTerm(Calendar.getInstance()).getId();
		if (timetableSelfCourse!=null&&timetableSelfCourse.getSchoolTerm()!=null) {
			termId = timetableSelfCourse.getSchoolTerm().getId();
		}
		mav.addObject("termId", termId);
		// 设置分页变量并赋值为20
		int pageSize = CommonConstantInterface.INT_PAGESIZE;
		// 设置用户的总记录数并赋值
		int totalRecords = timetableSelfCourseService.findAllTimetableSelfCourses(timetableSelfCourse, 0, -1,cid).size();
		Map<String, Integer> pageModel = shareService.getPage(currpage, pageSize, totalRecords);
		mav.addObject("newFlag", true);
		// 将pageModel映射到pageModel
		mav.addObject("pageModel", pageModel);
		// 将currpage映射到page，用来获取当前页的页码
		mav.addObject("page", currpage);
		// 将totalRecords映射到totalRecords，用来获取总记录数
		mav.addObject("totalRecords", totalRecords);
		// 将User映射到user
		mav.addObject("timetableSelfCourse", new TimetableSelfCourse());
		// 根据页面的页码，查找出20条记录，将找到的用户映射给users
		List<SchoolTerm> schoolTerms = outerApplicationService.getSchoolTermList();
		mav.addObject("schoolTerms", schoolTerms);
		// 获取用户列表
		mav.addObject("timetableSelfCourseMap",
				timetableSelfCourseService.findAllTimetableSelfCourses(timetableSelfCourse, currpage - 1, pageSize,cid));
		mav.setViewName("timetable/selfTimetable/listNewCourseCodeIframe.jsp");
		return mav;
	}

	/**************************************************************************************
	 * @自主排课 查看自主排课的选课组
	 * @页面跳转：listSelfTimetable-newCourseCodeIframeDetail-saveCourseCodeIframeDetail
	 * @作者：魏诚
	 * @日期：2014-07-25
	 **************************************************************************************/
	@RequestMapping("/timetable/selfTimetable/listCourseCodeIframeMain")
	public ModelAndView listCourseCodeIframeMain() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("timetable/selfTimetable/listCourseCodeIframeMain.jsp");
		return mav;
	}

	/**************************************************************************************
	 * @显示课程信息的主显示页面
	 * @作者：魏诚
	 * @日期：2014-07-14
	 *************************************************************************************/
	@RequestMapping("/timetable/selfTimetable/listSchoolCourseInfo")
	public ModelAndView listSchoolCourseInfo(@ModelAttribute SchoolCourseInfo schoolCourseInfo,
			@RequestParam int currpage,@ModelAttribute("selected_labCenter") Integer cid) {
		ModelAndView mav = new ModelAndView();
		// 设置分页变量并赋值为20；
		int pageSize = CommonConstantInterface.INT_PAGESIZE;
		// 根据课程及id获取课程排课列表的计数
		int totalRecords = schoolCourseInfoService.getCountCourseInfoByQuery(schoolCourseInfo);
		mav.addObject("totalRecords", totalRecords);
		mav.addObject("schoolCourseInfo", schoolCourseInfo);
		// 获取登陆用户信息
		mav.addObject("user", shareService.getUserDetail());
		Map<String, Integer> pageModel = shareService.getPage(currpage, pageSize, totalRecords);
		mav.addObject("pageModel", pageModel);
		// 将currpage映射到page，用来获取当前页的页码
		mav.addObject("page", currpage);
		// 维护自建课程
		mav.addObject("schoolCourseInfoList",
				schoolCourseInfoService.getCourseInfoByQuery(schoolCourseInfo, cid, 0, currpage, pageSize));
		mav.setViewName("timetable/selfTimetable/listSelfSchoolCourseInfo.jsp");
		return mav;
	}

	/**************************************************************************************
	 * @自主排课 新建自主排课的新建课程页面
	 * @页面跳转：listSelfTimetable-newCourseCodeIframeMain-newCourseCodeIframeDetail
	 * @作者：魏诚
	 * @日期：2014-07-25
	 *************************************************************************************/
	@RequestMapping("/timetable/selfTimetable/newSelfSchoolCourseInfo")
	public ModelAndView newSelfSchoolCourseInfo(@RequestParam String courseNumber) {
		ModelAndView mav = new ModelAndView();
		// 登陆用户
		mav.addObject("user", shareService.getUserDetail());
		// 获取最大的id
		int maxId = schoolCourseInfoService.getSelfSchoolCourseInfoTotalRecords();
		mav.addObject("maxId", maxId + 1);
		// id为-1时，新增，否则为修改
		mav.addObject("flagId", courseNumber);
		//
		SchoolCourseInfo schoolCourseInfo = new SchoolCourseInfo();
		if (!courseNumber.equals("-1")) {
			schoolCourseInfo = schoolCourseInfoDAO.findSchoolCourseInfoByCourseNumber(courseNumber);

		}
		mav.addObject("schoolCourseInfo", schoolCourseInfo);
		mav.setViewName("/timetable/selfTimetable/newSelfSchoolCourseInfo.jsp");
		return mav;
	}

	/*************************************************************************************
	 * @自主排课 保存 新建自主排课
	 * @页面跳转：listSelfTimetable-newCourseCodeIframeDetail-saveCourseCodeIframeDetail
	 * @作者：魏诚
	 * @日期：2014-07-25
	 *************************************************************************************/
	@RequestMapping("/timetable/selfTimetable/saveSelfSchoolCourseInfo")
	public ModelAndView saveSelfSchoolCourseInfo(@ModelAttribute SchoolCourseInfo schoolCourseInfo,
			@RequestParam String flagID) {
		ModelAndView mav = new ModelAndView();
		// 保存 新建自主排课
		timetableSelfCourseService.saveSelfSchoolCourseInfo(schoolCourseInfo, flagID);
		mav.setViewName("redirect:/timetable/selfTimetable/listSchoolCourseInfo?currpage=1");
		return mav;
	}

	/**************************************************************************************
	 * @自主排课 批量删除自建
	 * @页面跳转：listSelfTimetable-listTimetableCourseStudents-batchDeleteCourseStudents
	 * @作者：魏诚
	 * @日期：2014-07-25
	 *************************************************************************************/
	@RequestMapping("/timetable/selfTimetable/batchDeleteSelfSchoolCourseInfo")
	public ModelAndView batchDeleteSelfSchoolCourseInfo(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		String[] ids = request.getParameterValues("VoteOption1");
		for (String id : ids) {
			schoolCourseInfoDAO.remove(schoolCourseInfoDAO.findSchoolCourseInfoByCourseNumber(id));
		}
		mav.setViewName("redirect:/timetable/selfTimetable/listSchoolCourseInfo?currpage=1");
		return mav;
	}

	/**************************************************************************************
	 * @二次排课 确认自主分组排课是否完成--从排课页面进入
	 * @页面跳转：listReTimetable-listReTimetable-doIframeGroupReTimetable-doReNOGroupTimetableOk
	 * @作者：魏诚
	 * @日期：2014-07-25
	 *************************************************************************************/
	@RequestMapping("/timetable/selfTimetable/doSelfGroupTimetableOk")
	public ModelAndView doSelfGroupTimetableOk(@RequestParam String courseCode, int batchId, int term,HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("countGroup", request.getParameter("countGroup"));
		if(request.getParameter("countGroup") != null){
			int countGroup = Integer.parseInt(request.getParameter("countGroup"));
			for(Integer i = 1; i <= countGroup; i++){
				mav.addObject("student_"+i.toString(),request.getParameterValues("student_"+i.toString()));
			}
		}
		timetableSelfSchedulingService.doSelfGroupTimetableOk(courseCode, batchId, term);
		mav.setViewName("redirect:/timetable/selfTimetable/doIframeGroupSelfTimetable?term=" + term + "&courseCode="
				+ timetableSelfCourseDAO.findTimetableSelfCourseByCourseCode(courseCode).iterator().next().getId());
		return mav;
	}
	
	/**************************************************************************************
	 * @二次排课 确认自主分组排课是否完成--从排课管理页面进入
	 * @作者：魏诚
	 * @日期：2014-07-25
	 *************************************************************************************/
	@RequestMapping("/timetable/selfTimetable/doSelfGroupTimetableOkFromAdmin")
	public ModelAndView doSelfGroupTimetableOkFromAdmin(@RequestParam String courseCode, int batchId, int term) {
		ModelAndView mav = new ModelAndView();
		timetableSelfSchedulingService.doSelfGroupTimetableOk(courseCode, batchId, term);
		mav.setViewName("redirect:/timetable/timetableAdminIframe?currpage=1&id=-1&status=-1");
		return mav;
	}
}