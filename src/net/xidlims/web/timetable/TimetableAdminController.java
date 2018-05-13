package net.xidlims.web.timetable;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import net.luxunsh.util.EmptyUtil;
import net.xidlims.service.common.ShareService;
import net.xidlims.service.lab.LabRoomService;
import net.xidlims.service.timetable.OuterApplicationService;
import net.xidlims.service.timetable.SchoolCourseDetailService;
import net.xidlims.service.timetable.TimetableAppointmentService;
import net.xidlims.service.timetable.TimetableBatchService;
import net.xidlims.service.timetable.TimetableCourseSchedulingService;
import net.xidlims.service.timetable.TimetableReSchedulingService;
import net.xidlims.service.timetable.TimetableSelfSchedulingService;
import net.xidlims.dao.TimetableAppointmentDAO;
import net.xidlims.domain.LabRoom;
import net.xidlims.domain.LabRoomDevice;
import net.xidlims.domain.SchoolCourse;
import net.xidlims.domain.SchoolTerm;
import net.xidlims.domain.TimetableAppointment;
import net.xidlims.domain.TimetableBatch;
import net.xidlims.domain.TimetableLabRelated;
import net.xidlims.domain.TimetableLabRelatedDevice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;


/****************************************************************************
 * @功能说明： 排课管理 排课管理的所有页面跳转的功能在本文件中实现 作者：魏诚 时间：2014-08-16
 ****************************************************************************/
@Controller("TimetableAdminController")
@SessionAttributes("selected_labCenter")
public class TimetableAdminController<JsonResult> {

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
	private SchoolCourseDetailService schoolCourseDetailService;
	@Autowired
	private TimetableAppointmentService timetableAppointmentService;
	@Autowired
	private TimetableAppointmentDAO timetableAppointmentDAO;
	@Autowired
	private OuterApplicationService outerApplicationService;
	@Autowired
	private TimetableSelfSchedulingService timetableSelfSchedulingService;
	
	@Autowired
	private TimetableBatchService timetableBatchService;	
	@Autowired
	private TimetableReSchedulingService timetableReSchedulingService;	
	@Autowired
	private LabRoomService labRoomService;
	@Autowired
	private
	TimetableCourseSchedulingService timetableCourseSchedulingService;

	/**************************************************************************
	 * @排课管理  排课管理-页面列表
	 * @页面跳转：timetableAdmin-timetableAdminIframe
	 * @作者：魏诚
	 * @日期：2014-07-25
	 **************************************************************************/
	@RequestMapping("/timetable/timetableAdminIframe")
	public ModelAndView timetableAdminIframe(HttpServletRequest request,
			@ModelAttribute TimetableAppointment timetableAppointment,
			@RequestParam int currpage,@ModelAttribute("selected_labCenter")Integer cid) {
		ModelAndView mav = new ModelAndView();
		// 如果没有获取有效的实验分室列表-根据登录用户的所属学院
	    if(cid==null){
	    	cid =-1;
	    }
		// 获取学期列表
		List<SchoolTerm> schoolTerms = outerApplicationService.getSchoolTermList();
		mav.addObject("schoolTerms", schoolTerms);
		// 当前时间的学期
		int term = shareService.getBelongsSchoolTerm(Calendar.getInstance()).getId();
		if (request.getParameter("term")!=null&&!request.getParameter("term").equals("null")) {
			term = Integer.parseInt(request.getParameter("term"));
		}
		
		mav.addObject("term", term);
		
		// 获取登录用户信息
		mav.addObject("user", shareService.getUserDetail());
		// 当前查询条件中的课程编号
		String courseNumber = "";
		if (timetableAppointment.getSchoolCourseInfo() != null && timetableAppointment.getSchoolCourseInfo().getCourseNumber() != null) {
			courseNumber = timetableAppointment.getSchoolCourseInfo().getCourseNumber();
		}else {
			if (request.getParameter("courseNumber")!=null&&!request.getParameter("courseNumber").equals("")) {
				courseNumber = request.getParameter("courseNumber");
			}
		}
		mav.addObject("courseNumber", courseNumber);
		
				
		// 根据课程及id获取课程排课列表的计数
		int totalRecords = timetableAppointmentService
				.getCountTimetableAppointmentsByQueryNew(term,courseNumber,
						Integer.parseInt(request.getParameter("status")),cid,request);
		mav.addObject("totalRecords", totalRecords);
		// 设置分页变量并赋值为20；
		int pageSize = 20;//CommonConstantInterface.INT_PAGESIZE;
		Map<String, Integer> pageModel = shareService.getPage(currpage,
				pageSize, totalRecords);
		mav.addObject("pageModel", pageModel);
		// 将currpage映射到page，用来获取当前页的页码
		mav.addObject("page", currpage);
		// 状态 默认全部
		int status = -1;
		if (!EmptyUtil.isStringEmpty(request.getParameter("status"))) {
			status = Integer.parseInt(request.getParameter("status"));
		}
		mav.addObject("status", status);
		// 根据课程及id获取课程排课列表
		mav.addObject("timetableAppointments", timetableAppointmentService
				.getTimetableAppointmentsByQueryNew(term,courseNumber, status, currpage - 1, pageSize,cid,request));
		mav.addObject(
				"timetableAppointmentAll",
				timetableAppointmentService.getTimetableAppointmentsByQueryNew(term,courseNumber, status, 0, -1,cid,request));
		// 根据labClassWeekdays搜索排课记录
		mav.setViewName("timetable/timetableAdmin/listTimetableAdminIframeNew.jsp");
		return mav;
	}
	
	/**************************************************************************
	 * @查看教学用设备
	 * @作者：贺子龙
	 * @日期：2016-05-16
	 ***************************************************************************/
	@RequestMapping("/timetable/listTeachingLabRoomDevice")
	public ModelAndView listTeachingLabRoomDevice(@RequestParam int timetableId,int labRoomId) {
		ModelAndView mav = new ModelAndView();
		/**
		 * 1、判断该实验室是否允许教学外的设备对外开放
		 * 2、若开放，显示排课用到的设备
		 * 3、若不开放，显示所有设备
		 */
		// 找到对应实验室
		LabRoom labRoom = labRoomService.findLabRoomByPrimaryKey(labRoomId);
		Set<LabRoomDevice> labRoomDeviceAll = labRoom.getLabRoomDevices();
		// 找到对应排课
		TimetableAppointment appointment = timetableAppointmentDAO.findTimetableAppointmentByPrimaryKey(timetableId);
		List<LabRoomDevice> deviceList = new ArrayList<LabRoomDevice>();
		if (appointment.getDeviceOrLab().equals(2)) {// 针对实验室
			deviceList.addAll(labRoom.getLabRoomDevices());
		}else {// 允许
			if (appointment!=null && appointment.getTimetableLabRelateds()!=null && appointment.getTimetableLabRelateds().size()>0) {
				for (TimetableLabRelated related : appointment.getTimetableLabRelateds()) {
					if(related.getLabRoom() != null && related.getLabRoom().getId() == labRoomId){
						if (related.getTimetableLabRelatedDevices()!=null && related.getTimetableLabRelatedDevices().size()>0) {
							for (TimetableLabRelatedDevice deviceRelated : related.getTimetableLabRelatedDevices()) {
								deviceList.add(deviceRelated.getLabRoomDevice());
								labRoomDeviceAll.remove(deviceRelated.getLabRoomDevice()) ;
							}
						}
					} 
				}
			}
		}
		mav.addObject("labRoomDeviceAll",labRoomDeviceAll);
		mav.addObject("timetableId",timetableId);
		mav.addObject("labRoomId",labRoomId);
		mav.addObject("deviceList", deviceList);
		mav.addObject("labRoom", labRoom);
		mav.addObject("appointment", appointment);
		mav.setViewName("timetable/timetableAdmin/listTeachingLabRoomDevice.jsp");
		return mav;
	}
	
	/**************************************************************************
	 * @排课管理的删除排课条目
	 * @页面跳转：timetableAdmin-timetableAdminIframe-deleteAppointment
	 * @作者：魏诚
	 * @日期：2014-07-25
	 ***************************************************************************/
	@RequestMapping("/timetable/deleteAppointment")
	public ModelAndView deleteAppointment(@RequestParam int id,int term, int currpage,String courseNumber) {

		// 排课管理的删除排课条目
		timetableAppointmentService.deleteAppointment(id);
		
		// 删除排课相关的实验室禁用记录
		labRoomService.deleteLabRoomLimitTimeByAppointment(id);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/timetable/timetableAdminIframe?status=-1&term=" + term + "&currpage="
				+ currpage +"&courseNumber=" + courseNumber);
		return mav;
	}

	/****************************************************************************
	 * @排课管理-发布教务直接排课
	 * @页面跳转：timetableAdmin-timetableAdminIframe-doReleaseTimetable
	 * @作者：魏诚 日期：2014-08-4
	 ****************************************************************************/
	@RequestMapping("/timetable/doReleaseTimetable")
	public ModelAndView doReleaseTimetable(@RequestParam String courseCode,int flag,int term, String courseNumber) {
		ModelAndView mav = new ModelAndView();
		TimetableAppointment timetableAppointment = new TimetableAppointment();
		mav.addObject("timetableAppointment", timetableAppointment);
		// 发布排课,0为教务相关排课，1为自主排课
	    timetableAppointmentService.doReleaseTimetableAppointments(courseCode,flag);

		mav.setViewName("../../timetable/timetableAdminIframe?currpage=1&id=-1&status=-1&term=" +term+"&courseNumber="+courseNumber);
		return mav;
	}

	/****************************************************************************
	 * @throws ParseException 
	 * @排课管理 排课管理-保存排课修改
	 * @页面跳转：timetableAdmin-timetableAdminIframe-doAdminTimetable-saveAdminTimetable 教务排课进行调整排课 
	 * @作者:魏诚 	 日期：2014-07-27
	 ****************************************************************************/
	@RequestMapping("/timetable/saveAdminTimetable")
	public ModelAndView saveAdminTimetable(HttpServletRequest request) throws ParseException {
		ModelAndView mav = new ModelAndView();
		// 获取排课记录
		TimetableAppointment requestTimetableAppointment = timetableAppointmentDAO
				.findTimetableAppointmentById(Integer.parseInt(request
						.getParameter("id")));
		//TimetableAppointment timetableAppointment = new TimetableAppointment();
		// 处理教务排课及二次不分组排课
		if (requestTimetableAppointment.getTimetableStyle() == 2) {
			timetableAppointmentService.saveAdjustTimetable(request);;
		}
		if (requestTimetableAppointment.getTimetableStyle() == 3) {
			timetableAppointmentService.saveNoGroupReTimetable(request);
		}
		// 处理二次分组排课
		if (requestTimetableAppointment.getTimetableStyle() == 4) {

			// 保存调整排课数据
			timetableAppointmentService.saveGroupReTimetable(request);
		}
		// 处理自主排课不分组排课
		if (requestTimetableAppointment.getTimetableStyle() == 5) {
			
			timetableSelfSchedulingService.saveNoGroupSelfTimetable(request);
		}
		// 处理自主排课分组排课
		if (requestTimetableAppointment.getTimetableStyle() == 6) {
			// 保存调整排课数据
			timetableSelfSchedulingService.saveGroupReTimetable(request);
		}
		// 删除修改前的记录
		timetableAppointmentService.deleteAppointment(requestTimetableAppointment.getId());
		mav.setViewName("redirect:/timetable/timetableAdminIframe?currpage=1&term=" + request.getParameter("term") + "&id=-1&status=10");
		return mav;
	}

	/****************************************************************************
	 * @排课管理 教务排课-进行调整排课
	 * @页面跳转：timetableAdmin-timetableAdminIframe-doReleaseTimetable 
	 * @作者：魏诚 日期：2014-07-27
	 ****************************************************************************/
	@RequestMapping("/timetable/doAdminTimetable")
	public ModelAndView doAdminTimetable(HttpServletRequest request,
			@RequestParam int id,@ModelAttribute("selected_labCenter")Integer cid) {
		ModelAndView mav = new ModelAndView();
		if(cid==null){
			cid=-1;
		}
		// 获取去重的实验分室室列表
		mav.addObject("labRoomMap", outerApplicationService.getLabRoomMap(cid));
		// 获取可选的教师列表列表
		mav.addObject("timetableTearcherMap",
				outerApplicationService.getTimetableTearcherMap());
		TimetableAppointment timetableAppointment = new TimetableAppointment();
		mav.addObject("timetableAppointment", timetableAppointment);
		// 获取去重的实验分室室列表
		mav.addObject("labRoomMap", outerApplicationService.getLabRoomMap(cid));
		// 获取可选的教师列表列表
		mav.addObject("timetableTearcherMap",
				outerApplicationService.getTimetableTearcherMap());
     	
		// 根据课程及id获取课程排课列表
		TimetableAppointment timetableAppointmentMap = timetableAppointmentDAO.findTimetableAppointmentById(id);
		mav.addObject("timetableAppointmentMap",timetableAppointmentMap);
		//记录时间
		if(timetableAppointmentMap.getTimetableStyle()==5||timetableAppointmentMap.getTimetableStyle()==6){
			mav.addObject("term",timetableAppointmentMap.getTimetableSelfCourse().getSchoolTerm().getId());
		}else{
			mav.addObject("term",timetableAppointmentMap.getSchoolCourse().getSchoolTerm().getId());
		}
		// 获取可选的实验项目列表列表
		mav.addObject("timetableItemMap", outerApplicationService
				.getTimetableItemMap(timetableAppointmentDAO
						.findTimetableAppointmentById(id).getSchoolCourseInfo()
						.getCourseNumber()));
		mav.setViewName("timetable/timetableAdmin/listAdminTimetableTerm.jsp");
		
		mav.addObject("tableAppId",id);
		return mav;
	}

	
	
	/****************************************************************************
	 * @分组管理-对分组排课的信息进行管理和维护
	 * @作者：魏诚 日期：2015-05-10
	 ****************************************************************************/
	@RequestMapping("/timetable/groupAdmin")
	public ModelAndView groupAdmin(HttpServletRequest request,
				@ModelAttribute TimetableBatch timetableBatch,
				@RequestParam int currpage,@ModelAttribute("selected_labCenter")Integer cid) {
			ModelAndView mav = new ModelAndView();
			// 如果没有获取有效的实验分室列表-根据登录用户的所属学院
		    if(cid==null){
		    	cid =-1;
		    }
			// 获取学期列表
			List<SchoolTerm> schoolTerms = outerApplicationService
						.getSchoolTermList();
			mav.addObject("schoolTerms", schoolTerms);
			// 当前时间的学期
			int term = shareService.findNewTerm().getId();
			if (request.getParameter("term") != null) {
				term = Integer.parseInt(request.getParameter("term"));
			}
			
			//SchoolTerm schoolTerm = schoolTermDAO.findSchoolTermById(term);
			mav.addObject("term", term);
			
			// 获取登录用户信息
			mav.addObject("user", shareService.getUserDetail());
			// 判断是否标记位为空，如果为空，则清空schoolCourseDetail
			if (request.getParameter("id") != null
					&& request.getParameter("id").equals("-1")) {
				// timetableAppointment.setId(-1) ;
			}
			// 根据课程及id获取课程排课列表的计数
			int totalRecords = timetableBatchService
					.getCountTimetableBatchByQuery(term,Integer.parseInt(request.getParameter("status")),cid);
			mav.addObject("totalRecords", totalRecords);
			// 设置分页变量并赋值为20；
			int pageSize = 50;//CommonConstantInterface.INT_PAGESIZE;
			Map<String, Integer> pageModel = shareService.getPage(currpage,
					pageSize, totalRecords);
			mav.addObject("pageModel", pageModel);
			// 将currpage映射到page，用来获取当前页的页码
			mav.addObject("page", currpage);
			// 获取所有的学期
			List<SchoolTerm> terms = schoolCourseDetailService
					.getSchoolTermsByNow();
			// 映射terms给terms
			mav.addObject("terms", terms);

			// 根据课程及id获取课程排课列表
			mav.addObject("timetableBatchs", timetableBatchService
					.getTimetableBatchByQuery(term,Integer.parseInt(request.getParameter("status")),
							currpage - 1, pageSize,cid));
			mav.addObject(
					"timetableBatchAll",
					timetableBatchService.getTimetableBatchByQuery(term,Integer.parseInt(request.getParameter("status")), 0, -1,cid));
			// 根据labClassWeekdays搜索排课记录
			mav.setViewName("timetable/timetableAdmin/listTimetableBatch.jsp");
			return mav;
	}
	
	/************************************************************
	 * @二次排课： 删除id对应的批次的所有记录
	 * @页面跳转：listReTimetable-listReTimetable-doIframeGroupReTimetable-deleteBatch
	 * @作者：魏诚
	 * @日期：2014-07-25
	 ************************************************************/
	@RequestMapping("/timetable/deleteBatchAdmin")
	public ModelAndView deleteBatch(@RequestParam int id, int term, String courseCode) {
		ModelAndView mav = new ModelAndView();
		// 删除id对应的批次的所有记录
		timetableReSchedulingService.deleteBatch(id, term, courseCode);
		mav.setViewName("../../timetable/groupAdmin?currpage=1&status=-1" );
		return mav;
	}

	/**************************************************************************************
	 * @description排课管理： 保存添加的教学设备  
	 * @author：郑昕茹
	 * @date：2016-10-13
	 *************************************************************************************/
	@RequestMapping("/timetable/saveLabRoomDeviceRelated")
	public ModelAndView saveLabRoomDeviceRelated(@RequestParam String[] devices,@RequestParam Integer timetableId, @RequestParam Integer labRoomId) throws ParseException {
		ModelAndView mav = new ModelAndView(); 
		TimetableAppointment appointment = timetableAppointmentDAO.findTimetableAppointmentById(timetableId);
		if (appointment!=null && appointment.getTimetableLabRelateds()!=null && appointment.getTimetableLabRelateds().size()>0) {
			for (TimetableLabRelated related : appointment.getTimetableLabRelateds()) {
				if(related.getLabRoom() != null && related.getLabRoom().getId().equals(labRoomId) ){ 
						if(appointment.getTimetableStyle() == 1 || appointment.getTimetableStyle() == 2
								|| appointment.getTimetableStyle() == 3 || appointment.getTimetableStyle() == 4)
						{
							timetableCourseSchedulingService.saveTimetableLabroomDeviceReservation(related, devices, appointment.getSchoolCourse().getSchoolTerm().getId());
						}
						else{
							timetableCourseSchedulingService.saveTimetableLabroomDeviceReservation(related, devices, appointment.getTimetableSelfCourse().getSchoolTerm().getId());
						} 
				} 
			}
		}
		mav.setViewName("redirect:/timetable/listTeachingLabRoomDevice?timetableId="+timetableId+"&labRoomId="+labRoomId);
		return mav;

	}
	
	
	/**************************************************************************
	 * @排课管理  排课管理-页面列表
	 * @页面跳转：timetableAdmin-timetableAdminIframe
	 * @作者：魏诚
	 * @日期：2014-07-25
	 **************************************************************************/
	@RequestMapping("/timetable/timetableAdminIframeNew")
	public ModelAndView timetableAdminIframeNew(HttpServletRequest request,
			@ModelAttribute TimetableAppointment timetableAppointment,
			@RequestParam int currpage,@ModelAttribute("selected_labCenter")Integer cid) {
		ModelAndView mav = new ModelAndView();
		// 如果没有获取有效的实验分室列表-根据登录用户的所属学院

		// 获取学期列表
		List<SchoolTerm> schoolTerms = outerApplicationService
					.getSchoolTermList();
		mav.addObject("schoolTerms", schoolTerms);
		// 当前时间的学期
		int term = shareService.getBelongsSchoolTerm(Calendar.getInstance()).getId();
		if (request.getParameter("term")!=null&&!request.getParameter("term").equals("null")) {
			term = Integer.parseInt(request.getParameter("term"));
		}
		mav.addObject("term", term);
		
		// 获取登录用户信息
		mav.addObject("user", shareService.getUserDetail());
		// 判断是否标记位为空，如果为空，则清空schoolCourseDetail
		if (request.getParameter("id") != null
				&& request.getParameter("id").equals("-1")) {
			// timetableAppointment.setId(-1) ;
		}
		
		// 当前查询条件中的课程编号
		String courseNumber = "";
		if (timetableAppointment.getSchoolCourseInfo() != null && timetableAppointment.getSchoolCourseInfo().getCourseNumber() != null) {
			courseNumber = timetableAppointment.getSchoolCourseInfo().getCourseNumber();
		}else {
			if (request.getParameter("courseNumber")!=null&&!request.getParameter("courseNumber").equals("")) {
				courseNumber = request.getParameter("courseNumber");
			}
		}
		mav.addObject("courseNumber", courseNumber);
		// 根据课程及id获取课程排课列表的计数
		int totalRecords = timetableAppointmentService
				.getCountTimetableAppointmentsByQueryNew(term,courseNumber,
						Integer.parseInt(request.getParameter("status")),-1,request);
		mav.addObject("totalRecords", totalRecords);
		// 设置分页变量并赋值为20；
		int pageSize = 50;//CommonConstantInterface.INT_PAGESIZE;
		Map<String, Integer> pageModel = shareService.getPage(currpage,
				pageSize, totalRecords);
		mav.addObject("pageModel", pageModel);
		// 将currpage映射到page，用来获取当前页的页码
		mav.addObject("page", currpage);
		// 状态 默认全部
		int status = -1;
		if (!EmptyUtil.isStringEmpty(request.getParameter("status"))) {
			status = Integer.parseInt(request.getParameter("status"));
		}
		mav.addObject("status", status);
		// 根据课程及id获取课程排课列表
		mav.addObject("timetableAppointments", timetableAppointmentService
				.getTimetableAppointmentsByQueryNew(term,courseNumber,status,
						currpage - 1, pageSize,-1,request));
		mav.addObject(
				"timetableAppointmentAll",
				timetableAppointmentService.getTimetableAppointmentsByQueryNew(term,courseNumber,
						status, 0, -1,-1,request));
		// 根据labClassWeekdays搜索排课记录
		mav.setViewName("timetable/timetableAdmin/listTimetableAdminIframeNew.jsp");
		return mav;
	}
}
