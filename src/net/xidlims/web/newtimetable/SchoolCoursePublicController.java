/****************************************************************************
 * @功能：该controller为应对西电排课中的开放排课
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
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import net.xidlims.domain.SchoolCourseDetail;
import net.xidlims.domain.SchoolCourseMerge;
import net.xidlims.domain.SchoolTerm;
import net.xidlims.domain.TimetableBatch;
import net.xidlims.domain.TimetableLabRelated;
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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller("SchoolCoursePublicController")
@SessionAttributes("selected_labCenter")
@RequestMapping("/newtimetable")
public class SchoolCoursePublicController<JsonResult> {


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
	
	
	/************************************************************
	 * @description：公选课排课(非合班)
	 * @author：戴昊宇
	 * @date：2017-10-19
	 ************************************************************/
	@RequestMapping("/doSpecializedBasicCourseTimetablePublic")
	public ModelAndView doSpecializedBasicCourseTimetablePublic(
			@RequestParam String courseDetailNo, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		
		  /* ----------- 左侧课表显示   -------------   */
		
		//页数
		String currpage = request.getParameter("currpage");
		mav.addObject("currpage", currpage);
		// 登陆人权限获得
		mav.addObject("authorities", shareService.getUser().getAuthorities());
		mav.addObject("user", shareService.getUser());
		// 获取课表的展示框架
		SchoolTerm schoolTerm = shareService.getBelongsSchoolTerm(Calendar.getInstance());
		List<Object[]> schoolTermWeeks = termDetailService.findViewSchoolTermWeek(schoolTerm.getId());
		mav.addObject("schoolTerm", schoolTerm);
		mav.addObject("schoolTermWeeks", schoolTermWeeks);
		mav.addObject("specialSchoolWeeks", termDetailService
				.findSpecialSchoolWeekByTerm(schoolTerm.getId()));
		 
		/* ----------- 右侧信息展示   -------------   */
		
		// 获取实验室信息
		Integer roomId = -1;
		LabRoom labRoom = new LabRoom();
		if (request.getParameter("labroom") != null) {
			roomId = Integer.parseInt(request.getParameter("labroom"));
			labRoom = labRoomDAO.findLabRoomById(Integer.parseInt(request.getParameter("labroom")));		
		}
		
		mav.addObject("labRoom", labRoom);
		mav.addObject("roomId", roomId);
		
		SchoolCourseDetail schoolCourseDetail = schoolCourseDetailDAO.findSchoolCourseDetailByPrimaryKey(courseDetailNo);
		// 根据实验室和学期获取排课结果
		mav.addObject("listLabTimetableAppointments",newTimetableCourseSchedulingService.getListLabTimetableAppointments
				(roomId,schoolTerm.getId()));
		
		int type = 23;
		// 找到学期下指定课程的所有实验室的排课记录
		List<TimetableLabRelated> listThisCourseLabTimetableAppointments = newTimetableCourseSchedulingService
				.getTimetableAppointmentsByCourseDetailNoAndType(request,
						schoolTerm.getId(), courseDetailNo, type);
		mav.addObject("timetableLength",listThisCourseLabTimetableAppointments.size());
		mav.addObject("listThisCourseLabTimetableAppointments",listThisCourseLabTimetableAppointments);
		mav.addObject("labRoomMap",newTimetableCourseSchedulingService.getLabRoomMap());
		mav.addObject("courseDetailNo", courseDetailNo);
		
		// 选课开始时间
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String courseStartTime = sdf.format(Calendar.getInstance().getTime());
		if (schoolCourseDetail.getCourseStartTime() != null) {
			courseStartTime = sdf.format(schoolCourseDetail
					.getCourseStartTime().getTime());
		}
		mav.addObject("courseStartTime", courseStartTime);
		mav.addObject("schoolCourseDetail", schoolCourseDetail);
		
		/**
        * 1、查找该种排课类型、该项目是否已经生成批次
        * 2、若没有生成，则去设置页面
        * 3、若已经生成，则继续进行
        */
       // 生成批次所需参数
		String batchName= "公选课排课批次";
		Integer batchId = -1;
		mav.addObject("type", type);
		int ifSelect = 1;
		// 找到公选课排课所在批次
		TimetableBatch timetableBatch = newTimetableCourseSchedulingService.findTimetableBatchByCourseDetailNoAndType(courseDetailNo, type).get(0);
		 if (timetableBatch.getId()!=null) {
	            batchId = timetableBatch.getId();
	        }else{
	            // 获得排课组数和每组人数
	            if (labRoom != null  && schoolCourseDetail != null) {
	                timetableBatch = newTimetableShareService.createTimetableBatch(batchName, courseDetailNo, type, null, ifSelect, null);
	                batchId = timetableBatch.getId();
	            }
	        }
		// 本门课程的排课完成情况
		int isPartComplete = 0;
		int isComplete = 0;
		
		if (schoolCourseDetail.getIsComplete() != null&& schoolCourseDetail.getIsComplete() == 1) {
			isComplete = 1;// 本课程排课完成
		}
		if (timetableBatch != null && timetableBatch.getIsComplete() != null&& timetableBatch.getIsComplete() == 1) {
			isPartComplete = 1;// 本批次下的排课完成
		}
		mav.addObject("isComplete", isComplete);
		mav.addObject("isPartComplete", isPartComplete);
		// 批次
		mav.addObject("batchId", batchId);
		mav.setViewName("newtimetable/doSpecializedBasicCourseTimetablePublic.jsp");
		return mav;
	}

	/************************************************************
	 * @description：公选课排课(合班)
	 * @author：郑昕茹
	 * @date：2017-05-17
	 ************************************************************/
	@RequestMapping("/doMergeCourseTimetablePublic")
	public ModelAndView doMergeCourseTimetablePublic(
			@RequestParam Integer mergeId, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		
		  /* ----------- 左侧课表显示   -------------   */
		
		// 登陆人权限获得
		mav.addObject("authorities", shareService.getUser().getAuthorities());
		mav.addObject("user", shareService.getUser());
		// 获取课表的展示框架
		SchoolTerm schoolTerm = shareService.getBelongsSchoolTerm(Calendar.getInstance());
		List<Object[]> schoolTermWeeks = termDetailService.findViewSchoolTermWeek(schoolTerm.getId());
		mav.addObject("schoolTerm", schoolTerm);
		mav.addObject("schoolTermWeeks", schoolTermWeeks);
		mav.addObject("specialSchoolWeeks", termDetailService.findSpecialSchoolWeekByTerm(schoolTerm.getId()));
		
		 // 获取实验室信息
        Integer roomId = -1;
        LabRoom labRoom = new LabRoom();
        if (request.getParameter("labroom") != null) {
            roomId = Integer.parseInt(request.getParameter("labroom"));
            labRoom = labRoomDAO.findLabRoomById(Integer.parseInt(request.getParameter("labroom")));        
        }
        
        mav.addObject("labRoom", labRoom);
        mav.addObject("roomId", roomId);
        
        // 根据实验室和学期获取排课结果
		mav.addObject("listLabTimetableAppointments",newTimetableCourseSchedulingService.getListLabTimetableAppointments
				(roomId,schoolTerm.getId()));
		// 找到学期下指定课程的所有实验室的排课记录
		List<TimetableLabRelated> listThisCourseLabTimetableAppointments = newTimetableCourseSchedulingService
				.getListLabTimetableAppointmentsByMergeId(request,schoolTerm.getId(), mergeId);
		mav.addObject("timetableLength",listThisCourseLabTimetableAppointments.size());
		mav.addObject("listThisCourseLabTimetableAppointments",listThisCourseLabTimetableAppointments);
		mav.addObject("labRoomMap",newTimetableCourseSchedulingService.getLabRoomMap());
		mav.addObject("mergeId", mergeId);
		
		// 获得合班信息
		SchoolCourseMerge schoolCourseMerge = schoolCourseMergeDAO.findSchoolCourseMergeById(mergeId);
		// 选课开始时间
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String courseStartTime = sdf.format(Calendar.getInstance().getTime());
		if (schoolCourseMerge.getCourseStartTime() != null) {
			courseStartTime = sdf.format(schoolCourseMerge.getCourseStartTime()
					.getTime());
		}
		mav.addObject("courseStartTime", courseStartTime);
		mav.addObject("schoolCourseMerge", schoolCourseMerge);
		// 名单传值
		mav.addObject("courseDetailNo", schoolCourseMerge.getCourseNo());
		 /**
	        * 1、查找该种排课类型、该项目是否已经生成批次
	        * 2、若没有生成，则去设置页面
	        * 3、若已经生成，则继续进行
	        */
		String batchName= "公选课排课批次";
		Integer batchId = -1;
		Integer type =23 ;
		mav.addObject("type", type);
	    int ifSelect = 1;
		// 找到公选课排课所在批次
		TimetableBatch timetableBatch = newTimetableCourseSchedulingService.findTimetableBatchByCourseDetailNoAndType
				(mergeId.toString(), 23).get(0);
		 if (timetableBatch.getId()!=null) {
             batchId = timetableBatch.getId();
         }else{
             // 获得排课组数和每组人数
             if (labRoom != null  && schoolCourseMerge != null) {
                 timetableBatch = newTimetableShareService.createTimetableBatch(batchName, mergeId.toString(), type, null, ifSelect, null);
                 batchId = timetableBatch.getId();
             }
         }
		// 本门课程的排课完成情况
		int isPartComplete = 0;
		int isComplete = 0;

		if (schoolCourseMerge.getIsComplete() != null&& schoolCourseMerge.getIsComplete() == 1) {
			isComplete = 1;// 本课程排课完成
		}
		if (timetableBatch != null && timetableBatch.getIsComplete() != null
				&& timetableBatch.getIsComplete() == 1) {
			isPartComplete = 1;// 本批次下的排课完成
		}
		mav.addObject("isComplete", isComplete);
		mav.addObject("isPartComplete", isPartComplete);
		// 批次
		mav.addObject("batchId", batchId);
		mav.setViewName("newtimetable/doMergeCourseTimetablePublic.jsp");
		return mav;
	}
	
	/************************************************************
	 * @description：公选课排课
	 * @author：郑昕茹
	 * @date：2017-04-07
	 ************************************************************/
	@RequestMapping("/doPublicElectiveCourseTimetable")
	public ModelAndView doPublicElectiveCourseTimetable(
			@RequestParam String courseDetailNo, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
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
		if (request.getParameter("labroom") != null) {
			roomId = Integer.parseInt(request.getParameter("labroom"));
		}
		LabRoom labRoom = labRoomDAO.findLabRoomById(roomId);
		mav.addObject("labRoom", labRoomDAO.findLabRoomById(roomId));
		
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
		mav.addObject("isComplete", 0);
		// 建立公选课分批
		if (labRoom != null) {
			// 找到公选课排课所在批次
			TimetableBatch timetableBatch = newTimetableCourseSchedulingService
					.findTimetableBatchByCourseDetailNoAndType(courseDetailNo,
							23).get(0);
			if (timetableBatch == null || timetableBatch!=null && timetableBatch.getId()==null) {
				timetableBatch = new TimetableBatch();
				timetableBatch.setBatchName("公选课排课批次");
				timetableBatch.setCourseCode(courseDetailNo);
				timetableBatch.setType(23);
				timetableBatch.setIfselect(1);
				timetableBatchDAO.store(timetableBatch);
				mav.addObject("isComplete", 0);
			} else if (timetableBatch != null
					&& timetableBatch.getIsComplete() != null
					&& timetableBatch.getIsComplete() == 1) {
				mav.addObject("isComplete", 1);

			} else {
				mav.addObject("isComplete", 0);
			}
			mav.addObject("timetableBatch", timetableBatch);
			batchId = timetableBatch.getId();
		}
		mav.addObject("batchId", batchId);
		mav.setViewName("newtimetable/doPublicElectiveCourseTimetable.jsp");
		return mav;
	}
	
	
}
