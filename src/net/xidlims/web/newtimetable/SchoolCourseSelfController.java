/****************************************************************************
 * @功能：该controller为应对西电排课中的自主分批
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
import java.util.ArrayList;
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
import net.xidlims.domain.SchoolCourseMerge;
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

@Controller("SchoolCourseSelfController")
@SessionAttributes("selected_labCenter")
@RequestMapping("/newtimetable")
public class SchoolCourseSelfController {

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
    private NewTimetableShareService newTimetableShareService;
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
    
    /************************************************************
     * @description：自主排课（非合班）
     * @author：贺子龙
     * @date：2017-10-16
     ************************************************************/
    @RequestMapping("/doSpecializedBasicCourseTimetableSelf")
    public ModelAndView doSpecializedBasicCourseTimetableSelf(
            @RequestParam String courseDetailNo, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();

        /* ----------- 左侧课表显示   -------------   */
        
        // 登陆人权限获得
        mav.addObject("user", shareService.getUser());
        SchoolTerm schoolTerm = shareService.getBelongsSchoolTerm(Calendar
                .getInstance());
        // 获取课表的展示框架
        List<Object[]> schoolTermWeeks = termDetailService
                .findViewSchoolTermWeek(schoolTerm.getId());
        mav.addObject("schoolTerm", schoolTerm);
        mav.addObject("schoolTermWeeks", schoolTermWeeks);
        mav.addObject("specialSchoolWeeks", termDetailService
                .findSpecialSchoolWeekByTerm(schoolTerm.getId()));
        // 获取所有节次
        List<SystemTime> times = timeDetailService.findAllTimes(0, -1, "S");
        Map<Object, Object> time = new HashMap<Object, Object>();
        Map<Object, Object> timeEnd = new HashMap<Object, Object>();
        for (SystemTime t : times) {
            time.put(t.getSection(), t.getStartDate());
            timeEnd.put(t.getSection(), t.getEndDate());
        }
        mav.addObject("timeMap", time);
        
        // 显示选择列的冲突
        Set<Integer> weeks = new HashSet<Integer>();
        if (request.getParameter("showChosen") != null
                && request.getParameter("showChosen").equals("1")) {
            String[] isChosens = request.getParameterValues("isChosen");
            Integer count = 0;
            for (String isChosen : isChosens) {
                count++;
                if (isChosen.equals("1")) {
                    weeks.add(count);
                }
            }
            mav.addObject("weeks", weeks);
            mav.addObject("showChosen", 1);
        }
        // 获取实验室
        mav.addObject("labRoomMap", newTimetableCourseSchedulingService.getLabRoomMap());
        
        /* ----------- 右侧信息展示   -------------   */
        
        // 获取实验室信息（id capacity）
        Integer roomId = -1;
        int capacity = 0;// 实验室or课程 容量
        LabRoom labRoom = new LabRoom();
        if (request.getParameter("labroom") != null) {
            roomId = Integer.parseInt(request.getParameter("labroom"));
            labRoom = labRoomDAO.findLabRoomById(Integer.parseInt(request
                    .getParameter("labroom")));
            if (labRoom!=null && labRoom.getLabRoomCapacity()!=null) {
                capacity = labRoom.getLabRoomCapacity();
            }
        }
        
        // 找到，课程对应的容量
        LabRoomCourseCapacity labRoomCourseCapacity = newTimetableCourseSchedulingService
                .findLabRoomCourseCapacityByCourseDetailNoAndLabId(courseDetailNo, roomId);
        if (labRoomCourseCapacity != null
                && labRoomCourseCapacity.getCapacity() != null)
            capacity = labRoomCourseCapacity.getCapacity();
        
        mav.addObject("labRoom", labRoom);
        mav.addObject("roomId", roomId);
        mav.addObject("capacity", capacity);
        
        // 根据实验室和学期获取排课结果
        if(roomId!=-1){
            mav.addObject("listLabTimetableAppointments", newTimetableCourseSchedulingService
                    .getListLabTimetableAppointments(roomId, schoolTerm.getId()));
        }

        mav.addObject("courseDetailNo", courseDetailNo);
        
        SchoolCourseDetail schoolCourseDetail = schoolCourseDetailDAO.findSchoolCourseDetailByPrimaryKey(courseDetailNo);
        mav.addObject("schoolCourseDetail", schoolCourseDetail);

        // 获取排课教师
        Set<User> teachers = schoolCourseDetail.getUsers();
        teachers.add(schoolCourseDetail.getUser());
        teachers.addAll(schoolCourseDetail.getUserByScheduleTeachers());
        mav.addObject("teachers", teachers);
        
        // 选课开始时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String courseStartTime = sdf.format(Calendar.getInstance().getTime());
        if (schoolCourseDetail.getCourseStartTime() != null) {
            courseStartTime = sdf.format(schoolCourseDetail.getCourseStartTime().getTime());
        }
        mav.addObject("courseStartTime", courseStartTime);
        
        // 获取课程下的实验项目
        List<OperationItem> items = newOperationService.findOperationItemsByCourseNumber(schoolCourseDetail.getCourseNumber());
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
		
        
        /**
         * 1、查找该种排课类型、该项目是否已经生成批次
         * 2、若没有生成，则去设置页面
         * 3、若已经生成，则继续进行
         */
        // 生成批次所需参数
        int type = 28;
        mav.addObject("type", type);
        
        Integer batchId = -1;
        // 找到公选课排课所在批次
        TimetableBatch timetableBatch = newTimetableCourseSchedulingService.findTimetableBatchByCourseDetailNoAndTypeAndItemId
                (courseDetailNo, type, itemId);
        
        // 默认新建一个分组的list，根据不同的情况给它赋值
        List<TimetableGroup> groups = new ArrayList<TimetableGroup>();
        
        if (timetableBatch!=null) {
            batchId = timetableBatch.getId();
            groups = newTimetableCourseSchedulingService.findTimetableGroupsByBacthId(timetableBatch.getId());
            mav.setViewName("newtimetable/doSpecializedBasicCourseTimetableSelf.jsp");
        }else{
        	mav.setViewName("newtimetable/setSpecializedBasicCourseTimetableSelfGroup.jsp");
        }
        
        // 后台传值
        // 批次
        mav.addObject("batchId", batchId);
        // 分组
        mav.addObject("groups", groups);
        // 当前分组
        if (!EmptyUtil.isStringEmpty(request.getParameter("groupId"))) {
            mav.addObject("groupId", request.getParameter("groupId"));
        }
        // 本门课程的排课完成情况
        int isPartComplete = 0;
        int isComplete = 0;
        
        if (schoolCourseDetail.getIsComplete() != null && schoolCourseDetail.getIsComplete() == 1){
        	isComplete = 1;// 本课程排课完成
        }
        if (timetableBatch!=null && timetableBatch.getIsComplete() != null && timetableBatch.getIsComplete() == 1) {
            isPartComplete = 1;// 本批次下的排课完成
        }
        // 此次分批下是否存在排课
        int has_timetable = 0;
        if (groups!=null && groups.size()>0) {
			for (TimetableGroup timetableGroup : groups) {
				if (timetableGroup.getTimetableAppointments()!=null 
						&& timetableGroup.getTimetableAppointments().size()>0) {
					has_timetable = 1; break;
				}
			}
		}
        
        mav.addObject("has_timetable", has_timetable);
        mav.addObject("isComplete", isComplete);
        mav.addObject("isPartComplete", isPartComplete);
        
        // 找到当前课程，当前type下的所有分批，为了批次复制
        List<TimetableBatch> batches = newTimetableCourseSchedulingService.findTimetableBatchByCourseDetailNoAndType
        		(courseDetailNo, type);
        if (!batches.contains(timetableBatch)) {
        	batches.add(timetableBatch);
		}
        mav.addObject("batches", batches);
        
        return mav;
    }
    /************************************************************
   	 * @description：自主排课（合班）
   	 * @author：戴昊宇
   	 * @date：2017-10-18
   	 ************************************************************/
	@RequestMapping("/doMergeCourseTimetableSelf")
	public ModelAndView doMergeCourseTimetableSelf(
		@RequestParam Integer mergeId, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		
        /* ----------- 左侧课表显示   -------------   */
		
		// 登陆人权限获得
		mav.addObject("authorities", shareService.getUser().getAuthorities());
		mav.addObject("user", shareService.getUser());
		SchoolTerm schoolTerm = shareService.getBelongsSchoolTerm(Calendar
				.getInstance());
		// 获取课表的展示框架
		List<Object[]> schoolTermWeeks = termDetailService
				.findViewSchoolTermWeek(schoolTerm.getId());
		mav.addObject("schoolTerm", schoolTerm);
		mav.addObject("schoolTermWeeks", schoolTermWeeks);
		mav.addObject("specialSchoolWeeks", termDetailService
				.findSpecialSchoolWeekByTerm(schoolTerm.getId()));
		// 获取所有节次
        List<SystemTime> times = timeDetailService.findAllTimes(0, -1, "S");
        Map<Object, Object> time = new HashMap<Object, Object>();
        Map<Object, Object> timeEnd = new HashMap<Object, Object>();
        for (SystemTime t : times) {
            time.put(t.getSection(), t.getStartDate());
            timeEnd.put(t.getSection(), t.getEndDate());
        }
        mav.addObject("timeMap", time);
        // 显示选择列的冲突
     	Set<Integer> weeks = new HashSet<Integer>();
     	if (request.getParameter("showChosen") != null&& request.getParameter("showChosen").equals("1")) {
     		String[] isChosens = request.getParameterValues("isChosen");
     		Integer count = 0;
     		if (isChosens != null && isChosens.length != 0) {
     			for (String isChosen : isChosens) {
     				count++;
     				if (isChosen.equals("1")) {
     					weeks.add(count);
     				}
     			}
     			mav.addObject("weeks", weeks);
     			mav.addObject("showChosen", 1);
     			}
     		}
		// 获取实验室
     	mav.addObject("labRoomMap",newTimetableCourseSchedulingService.getLabRoomMap());
     	
        /* ----------- 右侧信息展示   -------------   */
     	
    	// 合班编号
		mav.addObject("mergeId", mergeId);
		SchoolCourseMerge schoolCourseMerge = schoolCourseMergeDAO.findSchoolCourseMergeById(mergeId);
		mav.addObject("schoolCourseMerge", schoolCourseMerge);
		// 名单显示船只
		mav.addObject("courseDetailNo", schoolCourseMerge.getCourseNo());
		// 获得选课教师
		Set<User> teachers = new HashSet<User>();
		SchoolCourseDetail detail = null;
		if (schoolCourseMerge.getSchoolCourseDetails() != null) {
			for (SchoolCourseDetail d : schoolCourseMerge
					.getSchoolCourseDetails()) {
				if (d.getCourseDetailNo().equals(schoolCourseMerge.getCourseDetailNo())) {
					// 当合并两门不同课程编号的课程时，一切已人数多的那一组为准
					// 因为保存的时候就是以人数最多的那一组存的schoolCourseMerge的courseDetailNO
					detail = d;
				}
				teachers.addAll(d.getUsers());
				teachers.addAll(d.getUserByScheduleTeachers());
				teachers.add(d.getUser());
			}
		}
		mav.addObject("teachers", teachers);
		mav.addObject("courseTeacher", detail.getUser());
		
		// 获取实验室信息（id capacity）
		Integer roomId = -1;
		int capacity = 0;// 实验室or课程 容量
		LabRoom labRoom = new LabRoom();
		if (request.getParameter("labroom") != null) {
			roomId = Integer.parseInt(request.getParameter("labroom"));
			labRoom = labRoomDAO.findLabRoomById(Integer.parseInt(request
					.getParameter("labroom")));
			if (labRoom!=null && labRoom.getLabRoomCapacity()!=null) {
                capacity = labRoom.getLabRoomCapacity();
            }
		}
		
		mav.addObject("labRoom", labRoom);
		mav.addObject("roomId", roomId);
	    mav.addObject("capacity", capacity);
	    
	    // 根据实验室和学期获取排课结果
	    if(roomId!=-1){
		mav.addObject("listLabTimetableAppointments",newTimetableCourseSchedulingService.getListLabTimetableAppointments
				(roomId,schoolTerm.getId()));
	    }
	
		// 获取课程下的实验项目
		List<OperationItem> items = newOperationService.findOperationItemsByCourseNumber(detail.getSchoolCourse().getSchoolCourseInfo().getCourseNumber());
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
        
        /**
         * 1、查找该种排课类型、该项目是否已经生成批次
         * 2、若没有生成，则去设置页面
         * 3、若已经生成，则继续进行
         */
        int type = 26;
        mav.addObject("type", type);
        
        Integer batchId = -1;
        // 找到公选课排课所在批次
     	TimetableBatch timetableBatch = newTimetableCourseSchedulingService
     					.findTimetableBatchByCourseDetailNoAndTypeAndItemId(mergeId.toString(),type, itemId);
     			
        // 默认新建一个分组的list，根据不同的情况给它赋值
     	List<TimetableGroup> groups = new ArrayList<TimetableGroup>();		
     	if (timetableBatch!=null&&timetableBatch.getId()!=null) {
     	    batchId = timetableBatch.getId();
     	    groups = newTimetableCourseSchedulingService.findTimetableGroupsByBacthId(timetableBatch.getId());
     	    mav.setViewName("newtimetable/doMergeCourseTimetableSelf.jsp");
     	}else{
     	    mav.setViewName("newtimetable/setMergeCourseTimetableSelfGroup.jsp");
     	     } 
     	// 后台传值
     	// 批次
     	mav.addObject("batchId", batchId);
        // 分组
     	mav.addObject("groups", groups);
     	// 当前分组
     	if (!EmptyUtil.isStringEmpty(request.getParameter("groupId"))) {
     	     mav.addObject("groupId", request.getParameter("groupId"));
     	   }
     	// 本门课程的排课完成情况
        int isPartComplete = 0;
        int isComplete = 0;
           
        if (schoolCourseMerge.getIsComplete() != null && schoolCourseMerge.getIsComplete() == 1){
     	     isComplete = 1;// 本课程排课完成
     	     }
     	if (timetableBatch!=null && timetableBatch.getIsComplete() != null && timetableBatch.getIsComplete() == 1) {
     	      isPartComplete = 1;// 本批次下的排课完成
     	     }     	
		// 此次分批下是否存在排课
        int has_timetable = 0;
        if (groups!=null && groups.size()>0) {
            for (TimetableGroup timetableGroup : groups) {
                if (timetableGroup.getTimetableAppointments()!=null 
                        && timetableGroup.getTimetableAppointments().size()>0) {
                    has_timetable = 1; break;
                }
            }
        }
        
        mav.addObject("has_timetable", has_timetable);
        mav.addObject("isComplete", isComplete);
        mav.addObject("isPartComplete", isPartComplete);
        
        // 找到当前课程，当前type下的所有分批，为了批次复制
        List<TimetableBatch> batches = newTimetableCourseSchedulingService.findTimetableBatchByCourseDetailNoAndType
                (mergeId.toString(), type);
        batches.add(timetableBatch);
        if (!batches.contains(timetableBatch)) {
        	batches.add(timetableBatch);
        }
        mav.addObject("batches", batches);
        
		return mav;
	}

	

    /************************************************************
	 * @description：自主排课（合班）
	 * @author：戴昊宇
	 * @date：2017-10-18
	 ************************************************************/
	@RequestMapping("/setMergeCourseTimetableSelfGroup")
	public ModelAndView setMergeCourseTimetableSelfGroup(
			@RequestParam Integer mergeId, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		
		// 获得合班排课的所有学生
		List<String> allCoursesStudentUsernames = newTimetableCourseSchedulingService
				.findUsersNameByMergeId(mergeId.toString(), null);
		Integer numbers = allCoursesStudentUsernames.size();
		// 生成批次所需参数
        String batchName = "自主分批（合班）";
        int type = 26 ;
        // 找到项目id
        Integer itemId = -1;
        if (!EmptyUtil.isStringEmpty(request.getParameter("item"))) {
            itemId = Integer.parseInt(request.getParameter("item"));
        }
        // 自动分批
        int ifSelect = 0;
        mav.addObject("type", type);
        // 生成分批
        TimetableBatch timetableBatch = newTimetableShareService.createTimetableBatch(batchName, mergeId.toString(), type, null, ifSelect, itemId);
		// 获得组数
        Integer groupNumber = 1;
        if (!EmptyUtil.isStringEmpty(request.getParameter("groupNumbers"))) {
            groupNumber = Integer.parseInt(request.getParameter("groupNumbers"));
        }
        // 自动生成分组
        newTimetableShareService.createTimetableGroupsByNumber(timetableBatch.getId(), numbers, groupNumber, allCoursesStudentUsernames);
		mav.setViewName("redirect:/newtimetable/doMergeCourseTimetableSelf?mergeId=" + mergeId);
		return mav;
	}
    /************************************************************
	 * @description：自主排课--自动分批
	 * @author：贺子龙
	 * @date：2017-10-16
	 ************************************************************/
	@RequestMapping("/setSpecializedBasicCourseTimetableSelfGroup")
	public ModelAndView setSpecializedBasicCourseTimetableSelfGroup(
			@RequestParam String courseDetailNo, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		// 获取全部学生
		List<String> studentsAll = newTimetableCourseSchedulingService.findUsernamesByCourseDetailNo(courseDetailNo);
		Integer studentNumber = studentsAll.size();
		// 生成批次所需参数
        String batchName = "自主分批";
        int type = 28;
        // 自动分批
        int ifSelect = 0;
        mav.addObject("type", type);
        // 找到项目id
        Integer itemId = -1;
        if (!EmptyUtil.isStringEmpty(request.getParameter("item"))) {
            itemId = Integer.parseInt(request.getParameter("item"));
        }
        // 生成分批
		TimetableBatch timetableBatch = newTimetableShareService.createTimetableBatch(batchName, courseDetailNo, type, null, ifSelect, itemId);
		// 获取组数
		Integer groupNumber = 1;
		if (!EmptyUtil.isStringEmpty(request.getParameter("groupNumbers"))) {
			groupNumber = Integer.parseInt(request.getParameter("groupNumbers"));
		}
		// 自动生成分组
		newTimetableShareService.createTimetableGroupsByNumber(timetableBatch.getId(), studentNumber, groupNumber, studentsAll);
		mav.setViewName("redirect:/newtimetable/doSpecializedBasicCourseTimetableSelf?courseDetailNo=" + courseDetailNo);
		return mav;
	}
    
    /************************************************************
	 * @description：自主排课--手动分批
	 * @author：贺子龙
	 * @date：2017-10-16
	 ************************************************************/
	@RequestMapping("/inBatches")
	public ModelAndView inBatches(@RequestParam String courseDetailNo,
			int groupNumbers, int timetableBatchId, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		int batchId = 0;
		// 生成批次所需参数
        String batchName = "自主分批";
        int type = 28;
        // 自动分批
        int ifSelect = 1;
        mav.addObject("type", type);
        
        // 找到项目id
        Integer itemId = -1;
        if (!EmptyUtil.isStringEmpty(request.getParameter("item"))) {
            itemId = Integer.parseInt(request.getParameter("item"));
        }
        
        TimetableBatch batch = newTimetableCourseSchedulingService
				.findTimetableBatchByCourseDetailNoAndTypeAndItemId(courseDetailNo, 28, itemId);
        List<TimetableGroup> groups = new LinkedList<TimetableGroup>();
        if (batch!=null && batch.getId()!=null) {
        	batchId = batch.getId();
        	groups = newTimetableCourseSchedulingService.findTimetableGroupsByBacthId(batchId);
		}else {
    		TimetableBatch timetableBatch = newTimetableShareService.createTimetableBatch(batchName, courseDetailNo, type, null, ifSelect, itemId);
    		batchId = timetableBatch.getId();
    		// 获取全部学生
    		List<String> studentsAll = newTimetableCourseSchedulingService.findUsernamesByCourseDetailNo(courseDetailNo);
    		Integer studentNumber = studentsAll.size();
    		// 自动生成分组
    		groups = newTimetableShareService.createTimetableGroupsByNumber(batchId, studentNumber, groupNumbers, null);
    	}
        mav.addObject("groups", groups);
		mav.addObject("timetableBatchId", batchId);
		mav.addObject("courseDetailNo", courseDetailNo);
		mav.setViewName("timetable/selfTimetable/listHandTimetable.jsp");
		return mav;
	}
    
	
	/************************************************************
	 * @description：合班分批-手动排课
	 * @author：贺子龙
	 * @date：2017-10-11
	 ************************************************************/
	@RequestMapping("/inBatchesMerge")
	public ModelAndView inBatchesMerge(@RequestParam Integer mergeId,
			int groupNumbers, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		
		
		int batchId = 0;
		// 生成批次所需参数
        String batchName = "合班排课自主分批批次";
        int type = 26;
        // 自动分批
        int ifSelect = 1;
        mav.addObject("type", type);
		
		// 找到项目id
        Integer itemId = -1;
        if (!EmptyUtil.isStringEmpty(request.getParameter("item"))) {
            itemId = Integer.parseInt(request.getParameter("item"));
        }
		// 找到公选课排课所在批次
		TimetableBatch batch = newTimetableCourseSchedulingService
				.findTimetableBatchByCourseDetailNoAndTypeAndItemId(mergeId.toString(),
						type, itemId);
        List<TimetableGroup> groups = new LinkedList<TimetableGroup>();
        if (batch!=null && batch.getId()!=null) {
        	batchId = batch.getId();
        	groups = newTimetableCourseSchedulingService.findTimetableGroupsByBacthId(batchId);
		}else {
    		TimetableBatch timetableBatch = newTimetableShareService.createTimetableBatch(batchName, mergeId.toString(), type, null, ifSelect, itemId);
    		batchId = timetableBatch.getId();
    		// 获取全部学生
    		List<String> studentsAll = newTimetableCourseSchedulingService.findUsernamesByCourseDetailNo(mergeId.toString());
    		Integer studentNumber = studentsAll.size();
    		// 自动生成分组
    		groups = newTimetableShareService.createTimetableGroupsByNumber(batchId, studentNumber, groupNumbers, null);
    	}
        mav.addObject("groups", groups);
		mav.addObject("timetableBatchId", batchId);
		mav.addObject("mergeId", mergeId);
		mav.setViewName("timetable/selfTimetable/listHandTimetableMerge.jsp");
		return mav;
	}
	
}
