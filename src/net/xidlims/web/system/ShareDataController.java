package net.xidlims.web.system;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.xidlims.constant.CommonConstantInterface;
import net.xidlims.service.common.ShareService;
import net.xidlims.service.device.LabRoomDeviceService;
import net.xidlims.service.lab.LabCenterService;
import net.xidlims.service.lab.LabRoomService;
import net.xidlims.service.system.ShareDataService;
import net.xidlims.service.system.UserDetailService;
import net.xidlims.service.timetable.OuterApplicationService;
import net.xidlims.service.timetable.SchoolCourseDetailService;
import net.xidlims.service.timetable.SchoolCourseService;
import net.xidlims.dao.LabRoomDAO;
import net.xidlims.dao.LabRoomDeviceDAO;
import net.xidlims.dao.SchoolAcademyDAO;
import net.xidlims.dao.SchoolCourseStudentDAO;
import net.xidlims.dao.SchoolDeviceDAO;
import net.xidlims.dao.SystemBuildDAO;
import net.xidlims.dao.SystemCampusDAO;
import net.xidlims.dao.SystemRoomDAO;
import net.xidlims.dao.TimetableAppointmentDAO;
import net.xidlims.dao.TimetableGroupDAO;
import net.xidlims.dao.TimetableGroupStudentsDAO;
import net.xidlims.domain.CDictionary;
import net.xidlims.domain.LabCenter;
import net.xidlims.domain.LabRoom;
import net.xidlims.domain.LabRoomDevice;
import net.xidlims.domain.LabRoomDeviceTraining;
import net.xidlims.domain.SchoolAcademy;
import net.xidlims.domain.SchoolCourseDetail;
import net.xidlims.domain.SchoolCourseStudent;
import net.xidlims.domain.SchoolDevice;
import net.xidlims.domain.SchoolTerm;
import net.xidlims.domain.SystemBuild;
import net.xidlims.domain.SystemRoom;
import net.xidlims.domain.TimetableAppointment;
import net.xidlims.domain.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import excelTools.ExcelUtils;
import excelTools.JsGridReportBase;
import excelTools.TableData;

@Controller("ShareDataController")
@SessionAttributes("selected_labCenter")
public class ShareDataController {

	@Autowired
	private ShareService shareService;
	
	@Autowired
	private SchoolCourseDetailService schoolCourseDetailService;
	
	@Autowired
	private SchoolCourseService schoolCourseService;
	
	@Autowired
	private TimetableAppointmentDAO timetableAppointmentDAO;
	
	@Autowired
	private SchoolCourseStudentDAO schoolCourseStudentDAO;
	
	@Autowired
	private TimetableGroupStudentsDAO timetableGroupStudentsDAO;
	
	@Autowired
	private TimetableGroupDAO timetableGroupDAO;
	
	@Autowired
	private SystemCampusDAO systemCampusDAO;
	
	@Autowired
	private ShareDataService shareDataService;
	
	@Autowired
	private SystemBuildDAO systemBuildDAO;
	
	@Autowired
	private OuterApplicationService outerApplicationService;
	
	@Autowired
	private SystemRoomDAO systemRoomDAO;
	
	@Autowired
	private SchoolDeviceDAO schoolDeviceDAO;
	
	@Autowired
	private UserDetailService userDetailService;
	@Autowired
	private LabCenterService labCenterService;
	@Autowired
	private SchoolAcademyDAO schoolAcademyDAO;
	@Autowired
	private LabRoomDeviceDAO labRoomDeviceDAO;
	@Autowired
	private LabRoomDeviceService labRoomDeviceService;
	@Autowired
	private LabRoomDAO labRoomDAO;
	@Autowired
	private LabRoomService labRoomService;
	/************************************************************
	 * @throws ParseException 
	 * @排课组列表
	 * @作者：喻泉声
	 * @日期：2014-09-1
	 ************************************************************/
	@RequestMapping("/sharedData/findAllCourseSchedule")
	public ModelAndView findAllCourseSchedule(HttpServletRequest request, @ModelAttribute SchoolCourseDetail schoolCourseDetail, @RequestParam int currpage,@ModelAttribute("selected_labCenter")Integer cid)
	{
		ModelAndView mav = new ModelAndView();
		// 获取terms的第一个
		SchoolTerm schoolTerm = new SchoolTerm();
		// 获取所有的学期
		List<SchoolTerm> terms = schoolCourseDetailService.getSchoolTermsByNow();
		// 如果学期获取为零则学期为空则返回显示页面
		if (terms.size() > CommonConstantInterface.INT_CURRPAGE_ZERO)
		{
			schoolTerm = terms.get(CommonConstantInterface.INT_CURRPAGE_ZERO);
		}
		else
		{
			mav.setViewName("timetable/schedulingcourse/listTimetableTerm.jsp");
			return mav;
		}
		// 设置分页变量并赋值为20；
		int pageSize = CommonConstantInterface.INT_PAGESIZE;
		//判断是否标记位为空，如果为空，则清空schoolCourseDetail
		if(request.getParameter("id") != null&&request.getParameter("id").equals("-1")){
			schoolCourseDetail.setCourseDetailNo(null) ;
		}
		// 根据课程及id获取课程排课列表的计数
		int totalRecords = schoolCourseDetailService.getCountCourseDetailsByQuery(schoolTerm.getId(), schoolCourseDetail,cid);
		mav.addObject("totalRecords", totalRecords);
		//获取登陆用户信息
		mav.addObject("user", shareService.getUserDetail() );
		Map<String, Integer> pageModel = shareService.getPage( currpage,pageSize, totalRecords);
		mav.addObject("pageModel", pageModel);
		//将currpage映射到page，用来获取当前页的页码
		mav.addObject("page", currpage);
		// 将TimetableAppointment映射到timetableAppointment；（教务调整排课入口属性）
		mav.addObject("timetableAppointment", new TimetableAppointment());
		// 获取当前周次
		int week = shareService.findNewWeek();
		// 映射week
		mav.addObject("week", String.valueOf(week));
		// 获取去重的实验分室室列表
		mav.addObject("labRoomMap", outerApplicationService.getLabRoomMap(cid));
		// 获取可选的教师列表列表
		mav.addObject("timetableTearcherMap", outerApplicationService.getTimetableTearcherMap());
		mav.addObject("schoolTerm", schoolTerm);
		// 映射schoolTerm的id
		mav.addObject("termId", schoolTerm.getId());
		// 根据课程及id获取课程排课列表
		List<SchoolCourseDetail> schoolCourseDetails = schoolCourseDetailService.getCourseDetailsByQuery(schoolTerm.getId(), schoolCourseDetail, currpage-1, pageSize,cid);
		// 根据课程及id获取课程排课列表
		mav.addObject("schedulingCourseMap", schoolCourseDetails);
		//mav.addObject("schedulingCourseAllMap", schoolCourseDetailService.getCourseDetailsByQuery(schoolTerm.getId(), schoolCourseDetail, -1, -1));
		mav.addObject("schedulingCourseAllMap",schoolCourseService.getCourseCodeList(schoolTerm.getId()) );
		// 获取该学期的所有周次
		mav.addObject("weeks", schoolCourseDetailService.getWeeksByNow(schoolTerm.getId()));
		mav.setViewName("system/shared_data/findAllCourseSchedule.jsp");
		return mav;
	}
	/*@RequestMapping("/sharedData/findAllCourseSchedule")
	public ModelAndView attendanceManageIframe(HttpServletRequest request,
			@ModelAttribute TimetableAppointment timetableAppointment,@RequestParam int currpage,@RequestParam int id,@ModelAttribute("selected_labCenter")Integer cid) throws ParseException {
		
		ModelAndView mav = new ModelAndView();
		//判断是否标记位为空，如果为空，则清空schoolCourseDetail
		if(request.getParameter("id") != null&&request.getParameter("id").equals("-1")){
			//timetableAppointment.setId(-1) ;
		}
		// 根据课程及id获取课程排课列表的计数
		int totalRecords =shareDataService
						.getCountTimetableAppointmentsByQuery(timetableAppointment,Integer.parseInt(request.getParameter("status")));
		mav.addObject("totalRecords", totalRecords);
		// 设置分页变量并赋值为20；
		int pageSize = 30;
		Map<String,Integer> pageModel = shareService.getPage(currpage,pageSize, totalRecords);	
		mav.addObject("pageModel",pageModel);
		//将currpage映射到page，用来获取当前页的页码
		mav.addObject("page", currpage);
		// 获取所有的学期
		List<SchoolTerm> terms = schoolCourseDetailService
				.getSchoolTermsByNow();
		// 映射terms给terms
		mav.addObject("terms", terms);
		
		//映射总条数
		mav.addObject("totalRecords", totalRecords);
		// 将course映射到course；
		mav.addObject("course", new SchoolCourse());
		// 根据课程及id获取课程排课列表
		mav.addObject("timetableAppointments", shareDataService
				.getTimetableAppointmentsByQuery(timetableAppointment,Integer.parseInt(request.getParameter("status")),currpage-1, pageSize));
		mav.addObject("timetableAppointmentAll", shareDataService
				.getTimetableAppointmentsByQuery(timetableAppointment,Integer.parseInt(request.getParameter("status")),-1, -1));
		
		
		//考勤机考勤遍历添加
		timetableAttendanceService.saveMachineAttendance(timetableAppointmentService
				.getTimetableAppointmentsByQuery(timetableAppointment,Integer.parseInt(request.getParameter("status")),currpage-1, pageSize,cid));
		
				// 根据labClassWeekdays搜索排课记录
				mav.setViewName("system/shared_data/findAllCourseSchedule.jsp");
				return mav;
	}*/
	
	/************************************************************
	 * @throws ParseException 
	 * @排课组管理--查看学生名单列表
	 * @作者：喻泉声
	 * @日期：2014-09-1
	 ************************************************************/
	@RequestMapping("/showCourseScheduleById")
	public ModelAndView checkStudentAttendance(@RequestParam Integer id) {
		ModelAndView mav = new ModelAndView();
		
		//查出对应课程的学生和总人数
		TimetableAppointment t=timetableAppointmentDAO.findTimetableAppointmentByPrimaryKey(id);
		if(t.getTimetableStyle()!=4){
		mav.addObject("flag", 1);
		String courseDetailNo=t.getSchoolCourseDetail().getCourseDetailNo();
		//查出课程号
		String sql = "select c from SchoolCourseStudent c where c.schoolCourseDetail.courseDetailNo like '"
				+ courseDetailNo + "'";
		List<SchoolCourseStudent> schoolCourseStudent=schoolCourseStudentDAO.executeQuery(sql);
		
		int studentNumber=t.getSchoolCourseDetail().getSchoolCourseStudents().size();
		
        List<TimetableAppointment> TimetableAppointments = new ArrayList<TimetableAppointment>();
		TimetableAppointments.add(t);
		mav.addObject("t", t);
		
		mav.addObject("checkTotalAttendance",schoolCourseStudent);
		mav.addObject("studentNumber", studentNumber);
		}
		if(t.getTimetableStyle()==4){
			mav.addObject("flag", 2);
			String sql1 = "select c from TimetableGroup c where c.timetableAppointment='"
					+id+ "'";
			
			String sql2 = "select c from TimetableGroupStudents c where c.timetableGroup='"
					+timetableGroupDAO.executeQuery(sql1).get(0).getId()+ "'";
			
			
			mav.addObject("checkTotalAttendance",timetableGroupStudentsDAO.executeQuery(sql2));
		}
		
		mav.setViewName("system/shared_data/showCourseScheduleById.jsp");
		return mav;
	}
	
	/************************************************************
	 * @throws ParseException 
	 * @排课组管理--查看校区列表
	 * @作者：喻泉声
	 * @日期：2014-09-1
	 ************************************************************/
	@RequestMapping("/findAllSystemCampus")
	public ModelAndView findAllSystemCampus() {
		ModelAndView mav = new ModelAndView();
		
		String sql="select c from SystemCampus c where 1=1";
		
		mav.addObject("systemCampus", systemCampusDAO.executeQuery(sql, 0, -1));
		
		mav.setViewName("system/shared_data/findAllSystemCampus.jsp");
		return mav;
	}
	
	/************************************************************
	 * @throws ParseException 
	 * @排课组管理--查看学院列表
	 * @作者：喻泉声
	 * @日期：2014-09-2
	 ************************************************************/
	@RequestMapping("/findAllSchoolAcademy")
		public ModelAndView findAllSchoolAcademy(@ModelAttribute SchoolAcademy schoolAcademy,@RequestParam int page) {
			
			//新建ModelAndView对象；
			ModelAndView mav=new ModelAndView();
			
			//下拉框
			/*mav.addObject("labRoomDeviceReparationsMap0",labRoomDeviceReparationService.getLabRoomDeviceReparationMap0());*/
		
			//查询表单的对象
			mav.addObject("schoolAcademy",schoolAcademy);
			int pageSize=30;//每页20条记录
			//查询出来的总记录条数
			int totalRecords=shareDataService.findSchoolAcademyBySchoolAcademy(schoolAcademy).size();
			//分页信息
			Map<String,Integer> pageModel =shareService.getPage( page,pageSize,totalRecords);
			//根据分页信息查询出来的记录
			List<SchoolAcademy> listSchoolAcademy=shareDataService.findSchoolAcademyBySchoolAcademy(schoolAcademy, page, pageSize);
			mav.addObject("listSchoolAcademy",listSchoolAcademy);
			mav.addObject("pageModel",pageModel);
			mav.addObject("totalRecords", totalRecords);
			mav.addObject("page", page);
			mav.addObject("pageSize", pageSize);
			
			mav.setViewName("system/shared_data/findAllSchoolAcademy.jsp");

		   	return mav;
			
		}
	
	/************************************************************
	 * @throws ParseException 
	 * @排课组管理--查看一条学院列表
	 * @作者：喻泉声
	 * @日期：2014-09-2
	 ************************************************************/
	@RequestMapping("/showSchoolAcademyByNumber")
	public ModelAndView findSchoolAcademyById(@RequestParam String number) {
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("schoolAcademy",schoolAcademyDAO.findSchoolAcademyByPrimaryKey(number));
		mav.setViewName("system/shared_data/showSchoolAcademyById.jsp");
		return mav;
	}
	
	/************************************************************
	 * @throws ParseException 
	 * @排课组管理--查看建筑列表
	 * @作者：喻泉声
	 * @日期：2014-09-2
	 ************************************************************/
	@RequestMapping("/findAllSchoolBuild")
	public ModelAndView findAllSchoolBuild(@ModelAttribute SystemBuild systemBuild,@RequestParam int page) {
		
		//新建ModelAndView对象；
		ModelAndView mav=new ModelAndView();
		
		//下拉框
		/*mav.addObject("labRoomDeviceReparationsMap0",labRoomDeviceReparationService.getLabRoomDeviceReparationMap0());*/
	
		//查询表单的对象
		mav.addObject("systemBuild",systemBuild);
		int pageSize=30;//每页20条记录
		//查询出来的总记录条数
		int totalRecords=shareDataService.getCountSystemBuildBySystemBuild(systemBuild);
		//分页信息
		Map<String,Integer> pageModel =shareService.getPage( page,pageSize,totalRecords);
		//根据分页信息查询出来的记录
		List<SystemBuild> listSystemBuild=shareDataService.findSystemBuildBySystemBuild(systemBuild,page,pageSize);
		mav.addObject("listSystemBuild",listSystemBuild);
		mav.addObject("pageModel",pageModel);
		mav.addObject("totalRecords", totalRecords);
		mav.addObject("page", page);
		mav.addObject("pageSize", pageSize);
		
		mav.setViewName("system/shared_data/findAllSchoolBuild.jsp");

	   	return mav;
		
	}
	
	/************************************************************
	 * @throws ParseException 
	 * @--查看一条建筑信息
	 * @作者：喻泉声
	 * @日期：2014-09-2
	 ************************************************************/
	@RequestMapping("/showSchoolBuildById")
	public ModelAndView showSchoolBuildById(@RequestParam String buildNumber) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("current",systemBuildDAO.findSystemBuildByPrimaryKey(buildNumber));
		
		mav.setViewName("system/shared_data/showSchoolBuildById.jsp");
		return mav;
	}
	
	
	/************************************************************
	 * @throws ParseException 
	 * @共享数据--查看房屋列表
	 * @作者：喻泉声
	 * @日期：2014-09-2
	 ************************************************************/
	@RequestMapping("/findAllSystemRoom")
	public ModelAndView findAllSystemRoom(@ModelAttribute SystemRoom systemRoom,@RequestParam int page) {
		
		//新建ModelAndView对象；
		ModelAndView mav=new ModelAndView();
		
		//下拉框
		/*mav.addObject("labRoomDeviceReparationsMap0",labRoomDeviceReparationService.getLabRoomDeviceReparationMap0());*/
	
		//查询表单的对象
		mav.addObject("systemRoom",systemRoom);
		int pageSize=30;//每页20条记录
		//查询出来的总记录条数
		int totalRecords=shareDataService.getCountSystemRoomBySystemRoom(systemRoom);
		//分页信息
		Map<String,Integer> pageModel =shareService.getPage( page,pageSize,totalRecords);
		//根据分页信息查询出来的记录
		List<SystemRoom> listSystemRoom=shareDataService.findSystemRoomBySystemRoom(systemRoom,page,pageSize);
		mav.addObject("listSystemRoom",listSystemRoom);
		mav.addObject("pageModel",pageModel);
		mav.addObject("totalRecords", totalRecords);
		mav.addObject("page", page);
		mav.addObject("pageSize", pageSize);
		
		mav.setViewName("system/shared_data/findAllSystemRoom.jsp");

	   	return mav;
		
	}
	
	/************************************************************
	 * @throws ParseException 
	 * @--查看一条房屋信息
	 * @作者：喻泉声
	 * @日期：2014-09-2
	 ************************************************************/
	@RequestMapping("/showSystemRoomById")
	public ModelAndView showSystemRoomById(@RequestParam String roomNumber) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("current",systemRoomDAO.findSystemRoomByPrimaryKey(roomNumber));
		
		mav.setViewName("system/shared_data/showSystemRoomById.jsp");
		return mav;
	}
	
	/************************************************************
	 * @throws ParseException 
	 * @共享数据--查看资产管理列表
	 * @作者：喻泉声
	 * @日期：2014-09-2
	 ************************************************************/
	@RequestMapping("/findAllSchoolDevice")
	public ModelAndView findAllSchoolDevice(@ModelAttribute SchoolDevice schoolDevice,@RequestParam int page) {
		
		//新建ModelAndView对象；
		ModelAndView mav=new ModelAndView();
		
		//下拉框
		/*mav.addObject("labRoomDeviceReparationsMap0",labRoomDeviceReparationService.getLabRoomDeviceReparationMap0());*/
	
		//查询表单的对象
		mav.addObject("schoolDevice",schoolDevice);
		int pageSize=30;//每页20条记录
		//查询出来的总记录条数
		//int totalRecords=shareDataService.findSchoolDeviceBySchoolDevice(schoolDevice).size();
		int totalRecords=shareDataService.getCountSchoolDeviceBySchoolDevice(schoolDevice);
		//分页信息
		Map<String,Integer> pageModel =shareService.getPage( page,pageSize,totalRecords);
		//根据分页信息查询出来的记录
		List<SchoolDevice> listSchoolDevice=shareDataService.findSchoolDeviceBySchoolDevice(schoolDevice,page,pageSize);
		mav.addObject("listSchoolDevice",listSchoolDevice);
		mav.addObject("pageModel",pageModel);
		mav.addObject("totalRecords", totalRecords);
		mav.addObject("page", page);
		mav.addObject("pageSize", pageSize);
		mav.addObject("listSchoolDeviceAll", shareDataService.findSchoolDeviceBySchoolDevice(new SchoolDevice(),1,-1));
		mav.setViewName("system/shared_data/findAllSchoolDevice.jsp");

	   	return mav;
		
	}
	
	/************************************************************
	 * @throws ParseException 
	 * @--查看一条设备信息
	 * @作者：喻泉声
	 * @日期：2014-09-2
	 ************************************************************/
	@RequestMapping("/showSchoolDeviceById")
	public ModelAndView showSchoolDeviceById(@RequestParam String deviceNumber) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("current",schoolDeviceDAO.findSchoolDeviceByPrimaryKey(deviceNumber));
		
		mav.setViewName("system/shared_data/showSchoolDeviceById.jsp");
		return mav;
	}
	
	/************************************************************
	 * @description
	 * @author:郑昕茹
	 * @date:2016-10-23
	 ************************************************************/
	@RequestMapping("/editSchoolDevice")
	public ModelAndView editSchoolDevice(@RequestParam String deviceNumber) {
		ModelAndView mav = new ModelAndView();
		SchoolDevice schoolDevice = schoolDeviceDAO.findSchoolDeviceByPrimaryKey(deviceNumber);
		mav.addObject("schoolDevice",schoolDeviceDAO.findSchoolDeviceByPrimaryKey(deviceNumber));
		Map users = shareService.getUsersMap();
		mav.addObject("users", users);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(schoolDevice.getDeviceBuyDate() != null)
		mav.addObject("date1", sdf.format(schoolDevice.getDeviceBuyDate().getTime()));
		if(schoolDevice.getDeviceAccountedDate() != null)
		mav.addObject("date2", sdf.format(schoolDevice.getDeviceAccountedDate().getTime()));
		mav.setViewName("system/shared_data/editSchoolDevice.jsp");
		return mav;
	}
	
	/************************************************************
	 * @description
	 * @author:郑昕茹
	 * @date:2016-10-23
	 ************************************************************/
	@RequestMapping("/newSchoolDevice")
	public ModelAndView newSchoolDevice( ) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("schoolDevice",new SchoolDevice());
		Map users = shareService.getUsersMap();
		mav.addObject("users", users);
		mav.setViewName("system/shared_data/editSchoolDevice.jsp");
		return mav;
	}
	
	/************************************************************
	 * @description
	 * @author:郑昕茹
	 * @throws ParseException 
	 * @date:2016-10-23 
	 ************************************************************/
	
	@RequestMapping("/saveSchoolDevice")
	@Transactional
	public ModelAndView saveSchoolDevice(@ModelAttribute SchoolDevice schoolDevice, HttpServletRequest request) throws ParseException {
		ModelAndView mav = new ModelAndView(); 
		
		
		SchoolDevice s = schoolDeviceDAO.findSchoolDeviceByPrimaryKey(schoolDevice.getDeviceNumber());
		String date1 = request.getParameter("date1");
		String date2 = request.getParameter("date2");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(s == null)s = new SchoolDevice();
		s.setId(0);	
		s.setDeviceNumber(schoolDevice.getDeviceNumber());
		s = schoolDeviceDAO.store(s);
		s.setDeviceName(schoolDevice.getDeviceName());
		s.setCategoryType(schoolDevice.getCategoryType());
		s.setCategoryMain(schoolDevice.getCategoryMain()); 
		s.setDeviceAddress(schoolDevice.getDeviceAddress());
		s.setDeviceFormat(schoolDevice.getDeviceFormat()); 
		s.setSchoolAcademy(shareService.getUser().getSchoolAcademy());
		s.setProjectSource(schoolDevice.getProjectSource());
		s.setDeviceCountry(schoolDevice.getDeviceCountry());
		s.setDevicePattern(schoolDevice.getDevicePattern());
		s.setDeviceUseDirection(schoolDevice.getDeviceUseDirection());
		s.setDeviceSupplier(schoolDevice.getDeviceSupplier());
		s.setDevicePrice(schoolDevice.getDevicePrice());
		s.setSn(schoolDevice.getSn());
		s.setManufacturer(schoolDevice.getManufacturer());
		if(!schoolDevice.getUserByUserNumber().getUsername().equals(""))
		s.setUserByUserNumber(schoolDevice.getUserByUserNumber());
		if(!schoolDevice.getUserByKeepUser().getUsername().equals(""))
		s.setUserByKeepUser(schoolDevice.getUserByKeepUser());
		if(date1 != null && !date1.equals("")){
			Date d1 = sdf.parse(date1);
			Calendar c1 = Calendar.getInstance();
			c1.setTime(d1);
			s.setDeviceBuyDate(c1);
		}
		if(date2 != null && !date2.equals("")){
			Date d2 = sdf.parse(date2);
			Calendar c2 = Calendar.getInstance();
			c2.setTime(d2);
			s.setDeviceAccountedDate(c2);
		}
		schoolDeviceDAO.store(s);
		
		mav.setViewName("redirect:/findAllSchoolDevice?page=1");
		return mav;
	}
	
	/************************************************************
	 * @description
	 * @author:郑昕茹
	 * @date:2016-10-23
	 ************************************************************/
	@RequestMapping("/deleteSchoolDevice")
	public ModelAndView deleteSchoolDevice(@RequestParam String deviceNumber) {
		ModelAndView mav = new ModelAndView(); 
		SchoolDevice schoolDevice = schoolDeviceDAO.findSchoolDeviceByPrimaryKey(deviceNumber);
		schoolDeviceDAO.remove(schoolDevice);
		schoolDeviceDAO.flush();
		mav.setViewName("redirect:/findAllSchoolDevice?page=1");
		return mav;
	}
	
	/************************************************************
	 * @description
	 * @author:郑昕茹
	 * @date:2016-10-23
	 ************************************************************/
	@RequestMapping("/judgeSchoolDeviceNumberUnique")
	public @ResponseBody String judgeSchoolDeviceNumberUnique(@RequestParam String deviceNumber) {
		SchoolDevice schoolDevice = schoolDeviceDAO.findSchoolDeviceByPrimaryKey(deviceNumber);
		if(schoolDevice == null)return "unique";
		return "not";
	}
	 
	
	/************************************************************
	 * @throws ParseException 
	 * @--查看一条专业方向信息
	 * @作者：喻泉声
	 * @日期：2014-09-2
	 ************************************************************/
	/*@RequestMapping("/showCMajorDirectionById")
	public ModelAndView showCMajorDirectionById(@RequestParam int Id) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("current",cMajorDirectionDAO.findCMajorDirectionByPrimaryKey(Id));
		
		mav.setViewName("system/shared_data/showCMajorDirectionById.jsp");
		return mav;
	}*/
	
	/************************************************************
	 * @throws ParseException 
	 * @共享数据--查看一卡通列表
	 * @作者：喻泉声
	 * @日期：2014-09-2
	 ************************************************************/
	@RequestMapping("/findAllUserCard")
	public ModelAndView findAllUserCard(HttpServletRequest request, @ModelAttribute User user,@RequestParam int currpage,@ModelAttribute("selected_labCenter") Integer cid)
	{
		ModelAndView mav = new ModelAndView();
		// 设置分页变量并赋值为20
		int pageSize = 30;
		//选择的中心
		LabCenter center=labCenterService.findLabCenterByPrimaryKey(cid);
		//中心所属学院编号
		String number=center.getSchoolAcademy().getAcademyNumber();		
		// 设置用户的总记录数并赋值
		int totalRecords = userDetailService.getUserTotalRecords(number);
		//
		Map<String, Integer> pageModel = shareService.getPage( currpage,pageSize, totalRecords);
		//
		mav.addObject("newFlag", true);
		//将pageModel映射到pageModel
		mav.addObject("pageModel", pageModel);
		//将currpage映射到page，用来获取当前页的页码
		mav.addObject("page", currpage);
		//将totalRecords映射到totalRecords，用来获取总记录数
		mav.addObject("totalRecords", totalRecords);
		//将User映射到user
		mav.addObject("user", new User());
		//根据页面的页码，查找出20条记录，将找到的用户映射给users
		//findAllUserCard.jsp未发现调用，注释掉        黄崔俊
		//mav.addObject("users", userDetailService.findAllUsers(currpage - 1, pageSize));
		//获取用户列表
		mav.addObject("userMap", userDetailService.findUserByQuery(user, currpage-1, pageSize,number));
		// 将该Model映射到listUser.jsp
		mav.setViewName("system/shared_data/findAllUserCard.jsp");
		return mav;
	}
	
	/************************************************************
	 * @内容：显示用户详细信息
	 * @作者：喻泉声
	 * @日期：2014-09-02
	 ************************************************************/
	@RequestMapping("/findUserCardById")
	public ModelAndView findUserCardById(@RequestParam String num)
	{
		ModelAndView mav=new ModelAndView();
		
		mav.addObject("num", num);
		
		mav.addObject("users", userDetailService.findUserByNum(num));
		//将该Model映射将该Model映射到userDetailInfo.jsp
		mav.setViewName("system/shared_data/showUserCardById.jsp");
		return mav;
	}
	
	/*********************************************************************************
	 * @description: 导出用户信息列表
	 * @author:黄崔俊        2015-7-29 14:29:25
	 ************************************************************************************/
	@RequestMapping("/system/exportUserList")
	public void exportUserList(@ModelAttribute User user, HttpServletRequest request,
			HttpServletResponse response, @RequestParam int page,@ModelAttribute("selected_labCenter") Integer cid) throws Exception {
		
		// 设置分页大小
		int pageSize = 30;
		
		//选择的中心
		LabCenter center=labCenterService.findLabCenterByPrimaryKey(cid);
		//中心所属学院编号
		String number=center.getSchoolAcademy().getAcademyNumber();
		List<User> users = userDetailService.findUserByQuery(new User(), page-1, pageSize,number);
		List<Map> list = new ArrayList<Map>();
		
		// 设置打印的信息
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		for (User u : users) {
			Map map = new HashMap();
			map.put("username", u.getUsername());
			map.put("cname", u.getCname());
			map.put("userSexy", u.getUserSexy());
			map.put("cardno", u.getCardno());
			list.add(map);
			
		}
		String title = "用户信息列表";
		String[] hearders = new String[] { "用户工号", "用户姓名", "性别", "卡号"};// 表头数组
		String[] fields = new String[] { "username", "cname", "userSexy", "cardno" };// Financialresources对象属性数组
		TableData td = ExcelUtils.createTableData(list, ExcelUtils.createTableHeader(hearders), fields);
		JsGridReportBase report = new JsGridReportBase(request, response);
		report.exportToExcel(title, shareService.getUser().getCname(), td);
	}
	
	/************************************************************
	 * @throws ParseException 
	 * @--查看单位列表
	 * @作者：喻泉声
	 * @日期：2014-09-2
	 ************************************************************/
	@RequestMapping("/findAllDepartment")
	public ModelAndView findAllDepartment(@ModelAttribute SchoolAcademy schoolAcademy,@RequestParam int page) {
			
			//新建ModelAndView对象；
			ModelAndView mav=new ModelAndView();
			
			//下拉框
			/*mav.addObject("labRoomDeviceReparationsMap0",labRoomDeviceReparationService.getLabRoomDeviceReparationMap0());*/
		
			//查询表单的对象
			mav.addObject("schoolAcademy",schoolAcademy);
			int pageSize=30;//每页20条记录
			//查询出来的总记录条数
			int totalRecords=shareDataService.getCountSchoolAcademyBySchoolAcademy1(schoolAcademy);
			//分页信息
			Map<String,Integer> pageModel =shareService.getPage( page,pageSize,totalRecords);
			//根据分页信息查询出来的记录
			List<SchoolAcademy> listSchoolAcademy=shareDataService.findSchoolAcademyBySchoolAcademy1(schoolAcademy,page,pageSize);
			mav.addObject("listSchoolAcademy",listSchoolAcademy);
			mav.addObject("pageModel",pageModel);
			mav.addObject("totalRecords", totalRecords);
			mav.addObject("page", page);
			mav.addObject("pageSize", pageSize);
			
			mav.setViewName("system/shared_data/findAllDepartment.jsp");

		   	return mav;
	}
	
	
	/************************************************************
	 * @throws ParseException 
	 * @共享数据--查看资产管理列表
	 * @作者：喻泉声
	 * @日期：2014-09-2
	 ************************************************************/
	@RequestMapping("/findAllLabRoomDevice")
	public ModelAndView findAllLabRoomDevice(@ModelAttribute LabRoomDevice labRoomDevice,@RequestParam int page) {
		
		//新建ModelAndView对象；
		ModelAndView mav=new ModelAndView();
		
		//下拉框
		/*mav.addObject("labRoomDeviceReparationsMap0",labRoomDeviceReparationService.getLabRoomDeviceReparationMap0());*/
	
		//查询表单的对象
		mav.addObject("labRoomDevice",labRoomDevice);
		int pageSize=30;//每页20条记录
		//查询出来的总记录条数
		//int totalRecords=shareDataService.findSchoolDeviceBySchoolDevice(schoolDevice).size();
		int totalRecords=shareDataService.getCountLabRoomDevice(labRoomDevice);
		//分页信息
		Map<String,Integer> pageModel =shareService.getPage( page,pageSize,totalRecords);
		//根据分页信息查询出来的记录
		List<LabRoomDevice> listLabRoomDevice=shareDataService.findLabRoomDevice(labRoomDevice, page, pageSize);
		mav.addObject("listLabRoomDevice",listLabRoomDevice);
		mav.addObject("pageModel",pageModel);
		mav.addObject("totalRecords", totalRecords);
		mav.addObject("page", page);
		mav.addObject("pageSize", pageSize);
		mav.addObject("listLabRoomDeviceAll", shareDataService.findLabRoomDevice(new LabRoomDevice(), 1, -1));
		//找到所有的实验室
		mav.addObject("labRooms", labRoomService.findAllLabRoomByQuery(1, -1, new LabRoom(),-1));
		mav.setViewName("system/shared_data/findAllLabRoomDevice.jsp");

	   	return mav;
		
	}
	
	/************************************************************
	 * @description
	 * @author:郑昕茹
	 * @date:2016-10-23
	 ************************************************************/
	@RequestMapping("/deleteLabRoomDevice")
	public ModelAndView deleteLabRoomDevice(@RequestParam Integer id) {
		ModelAndView mav = new ModelAndView(); 
		LabRoomDevice labRoomDevice = labRoomDeviceDAO.findLabRoomDeviceById(id);
		labRoomDeviceDAO.remove(labRoomDevice);
		labRoomDeviceDAO.flush();
		mav.setViewName("redirect:/findAllLabRoomDevice?page=1");
		return mav;
	}
	
	/************************************************************
	 * @description
	 * @author:郑昕茹
	 * @date:2016-10-23
	 ************************************************************/
	@RequestMapping("/editLabRoomDevice")
	public ModelAndView editLabRoomDevice(@RequestParam Integer id) {
		ModelAndView mav = new ModelAndView();
		LabRoomDevice labRoomDevice = labRoomDeviceDAO.findLabRoomDeviceById(id);
		mav.addObject("labRoomDevice",labRoomDevice);
		Map users = shareService.getUsersMap();
		mav.addObject("users", users);   
		// 设备管理员
		User user = shareService.getUser(); 
		// 设备状态
		//Set<CDeviceStatus> stutus = cDeviceStatusDAO.findAllCDeviceStatuss();
		List<CDictionary> stutus = shareService.getCDictionaryData("c_lab_room_device_status");
		mav.addObject("stutus", stutus);
		// 所属类型
		//Set<CDeviceType> types = cDeviceTypeDAO.findAllCDeviceTypes();
		List<CDictionary> types = shareService.getCDictionaryData("c_lab_room_device_type");
		mav.addObject("types", types);
		// 收费标准
		//Set<CDeviceCharge> charges = cDeviceChargeDAO.findAllCDeviceCharges();
		List<CDictionary> charges = shareService.getCDictionaryData("c_lab_room_device_charge");
		mav.addObject("charges", charges);
		// 当前@时间所属的学期
		Calendar time = Calendar.getInstance();
		SchoolTerm term = shareService.getBelongsSchoolTerm(time);
		// 根据学期和设备查询培训
				List<LabRoomDeviceTraining> trainings = labRoomDeviceService
						.findLabRoomDeviceTrainingByTermId(term.getId(), id);
				mav.addObject("trainings", trainings);
				// 培训表单的对象
		mav.addObject("train", new LabRoomDeviceTraining());
		
		//找到所有的学校设备
		mav.addObject("schoolDevices", shareDataService.findSchoolDeviceBySchoolDevice(new SchoolDevice()));
		//找到所有的实验室
		mav.addObject("labRooms", labRoomService.findAllLabRoomByQuery(1, -1, new LabRoom(),-1));
		mav.setViewName("system/shared_data/editLabRoomDevice.jsp");
		return mav;
	}
	
	/************************************************************
	 * @description
	 * @author:郑昕茹
	 * @date:2016-10-23
	 ************************************************************/
	@RequestMapping("/newLabRoomDevice")
	public ModelAndView newLabRoomDevice( ) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("labRoomDevice",new LabRoomDevice());
		Map users = shareService.getUsersMap();
		mav.addObject("users", users);
		mav.addObject("labRoomDevice",new LabRoomDevice()); 
		// 设备管理员
		User user = shareService.getUser(); 
		// 设备状态
		//Set<CDeviceStatus> stutus = cDeviceStatusDAO.findAllCDeviceStatuss();
		List<CDictionary> stutus = shareService.getCDictionaryData("c_lab_room_device_status");
		mav.addObject("stutus", stutus);
		// 所属类型
		//Set<CDeviceType> types = cDeviceTypeDAO.findAllCDeviceTypes();
		List<CDictionary> types = shareService.getCDictionaryData("c_lab_room_device_type");
		mav.addObject("types", types);
		// 收费标准
		//Set<CDeviceCharge> charges = cDeviceChargeDAO.findAllCDeviceCharges();
		List<CDictionary> charges = shareService.getCDictionaryData("c_lab_room_device_charge");
		mav.addObject("charges", charges);
		// 当前@时间所属的学期
		Calendar time = Calendar.getInstance();
		SchoolTerm term = shareService.getBelongsSchoolTerm(time);
				// 培训表单的对象
		mav.addObject("train", new LabRoomDeviceTraining());
		//找到所有的学校设备
		mav.addObject("schoolDevices", shareDataService.findSchoolDeviceNotInLabRoomDevice());
		
		//找到所有的实验室
		mav.addObject("labRooms", labRoomService.findAllLabRoomByQuery(1, -1, new LabRoom(),-1));
		mav.setViewName("system/shared_data/editLabRoomDevice.jsp");
		return mav;
	}
	
	/************************************************************
	 * @description
	 * @author:郑昕茹
	 * @throws ParseException 
	 * @date:2016-10-23 
	 ************************************************************/
	
	@RequestMapping("/saveLabRoomDevice")
	@Transactional
	public ModelAndView saveLabRoomDevice(@ModelAttribute LabRoomDevice labRoomDevice, HttpServletRequest request) throws ParseException {
		ModelAndView mav = new ModelAndView(); 
		if(labRoomDevice.getCDictionaryByDeviceStatus() != null && labRoomDevice.getCDictionaryByDeviceStatus().getId() == null){
			labRoomDevice.setCDictionaryByDeviceStatus(null);
		}
		if(labRoomDevice.getCDictionaryByDeviceType() != null && labRoomDevice.getCDictionaryByDeviceType().getId() == null){
			labRoomDevice.setCDictionaryByDeviceType(null);
		}
		if(labRoomDevice.getCDictionaryByDeviceCharge() != null && labRoomDevice.getCDictionaryByDeviceCharge().getId() == null){
			labRoomDevice.setCDictionaryByDeviceCharge(null);
		} 
		labRoomDeviceDAO.store(labRoomDevice);
		mav.setViewName("redirect:/findAllLabRoomDevice?page=1");
		return mav;
	}
}
