/****************************************************************************
 * 命名规范：
 * 本控制层调整必须是按照如下的路径调整：@RequestMapping("/appointment/sample")，全部小写。
 * 列表信息listxxxx，獲取信息：getxxxx 编辑信息：editxxxx 删除信息：detelexxxx 新增信息 newxxxx
 * 导出信息exportxxxx 保存信息：savexxxx 
 ****************************************************************************/

package net.xidlims.web.lab;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.xidlims.service.EmptyUtil;
import net.xidlims.common.LabAttendance;
import net.xidlims.dao.LabCenterDAO;
import net.xidlims.dao.LabReservationDAO;
import net.xidlims.dao.LabRoomAgentDAO;
import net.xidlims.dao.LabRoomDAO;
import net.xidlims.dao.LabRoomLimitTimeDAO;
import net.xidlims.dao.SchoolTermDAO;
import net.xidlims.dao.TimetableSelfCourseDAO;
import net.xidlims.dao.UserDAO;
import net.xidlims.domain.CDictionary;
import net.xidlims.domain.CommonHdwlog;
import net.xidlims.domain.LabAnnex;
import net.xidlims.domain.LabCenter;
import net.xidlims.domain.LabReservation;
import net.xidlims.domain.LabReservationAudit;
import net.xidlims.domain.LabReservationTimeTable;
import net.xidlims.domain.LabReservationTimeTableStudent;
import net.xidlims.domain.LabRoom;
import net.xidlims.domain.LabRoomAdmin;
import net.xidlims.domain.LabRoomAgent;
import net.xidlims.domain.LabRoomDevice;
import net.xidlims.domain.LabRoomLimitTime;
import net.xidlims.domain.SchoolDevice;
import net.xidlims.domain.SchoolTerm;
import net.xidlims.domain.SchoolWeekday;
import net.xidlims.domain.SystemTime;
import net.xidlims.domain.TimetableAppointmentSameNumber;
import net.xidlims.domain.User;
import net.xidlims.service.cmsshow.CMSShowService;
import net.xidlims.service.common.LabRoomLogService;
import net.xidlims.service.common.ShareService;
import net.xidlims.service.lab.LabAnnexService;
import net.xidlims.service.lab.LabReservationService;
import net.xidlims.service.lab.LabRoomService;
import net.xidlims.service.report.TeachingReportService;
import net.xidlims.service.system.SchoolWeekService;
import net.xidlims.service.system.SystemService;
import net.xidlims.service.system.TermDetailService;
import net.xidlims.web.PageModel;

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

import excelTools.ExcelUtils;
import excelTools.JsGridReportBase;
import excelTools.Labreservationlist;
import excelTools.TableData;

/****************************************************************************
 * 功能：实验分室模块 作者：魏诚 时间：2014-07-14
 ****************************************************************************/
@Controller("LabReservationController")
@SessionAttributes("selected_labCenter")
public class LabReservationController<JsonResult> {

	@Autowired
	LabReservationService labReservationService;
	@Autowired
	ShareService shareService;
	@Autowired
	private LabRoomLogService labRoomLogService;
	@Autowired
	private CMSShowService cmsShowService;
	@Autowired
	private LabAnnexService labAnnexService;
	@Autowired
	private SchoolTermDAO schoolTermDAO;
	@Autowired
	private LabReservationDAO labReservationDAO;
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private TimetableSelfCourseDAO timetableSelfCourseDAO;
	@Autowired
	private LabCenterDAO labCenterDAO;
	@Autowired
	private LabRoomAgentDAO labRoomAgentDAO;
	@Autowired
	private LabRoomService labRoomService;
	@Autowired
	private LabRoomLimitTimeDAO labRoomLimitTimeDAO;
	@Autowired
	private LabRoomDAO labRoomDAO;
	@Autowired
	private TeachingReportService teachingReportService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private SchoolWeekService schoolWeekService;
	@Autowired
	private TermDetailService termDetailService;
	@InitBinder
	public void initBinder(WebDataBinder binder, HttpServletRequest request) { // Register
																				// static
																				// property
																				// editors.
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

	/****************************************************************************
	 * 功能：查询实验室 作者：薛帅 时间：2014-08-6
	 ****************************************************************************/
	@RequestMapping("/lab/labAnnexList")
	public ModelAndView labAnnexList(@ModelAttribute LabRoom labRoom, @RequestParam Integer currpage,
			@ModelAttribute("selected_labCenter") Integer sid) {
		// 新建ModelAndView对象；
		ModelAndView mav = new ModelAndView();
		// 查询表单的对象
		mav.addObject("labRoom", labRoom);
		int pageSize = 30;// 每页20条记录
		// 查询出来的总记录条数
		int totalRecords = labReservationService.findLabRoom(labRoom, sid).size();
		// 分页信息
		Map<String, Integer> pageModel = shareService.getPage(currpage, pageSize, totalRecords);
		// 根据分页信息查询出来的记录
		List<LabRoom> listLabRoom = labReservationService.findLabRoompage(labRoom, currpage, pageSize, sid);
		// 获取可用选课组
		mav.addObject("selfCourseList",
				timetableSelfCourseDAO.executeQuery("select c from TimetableSelfCourse c where c.status=-1"));
		mav.addObject("listLabRoom", listLabRoom);
		mav.addObject("pageModel", pageModel);
		mav.addObject("totalRecords", totalRecords);
		mav.addObject("currpage", currpage);
		mav.addObject("pageSize", pageSize);
		// 查找预约类型
		mav.addObject("appointmenttype", labReservationService.appointmenttype());
		// 查找周次
		mav.addObject("week", labReservationService.getallweek());
		// 查找星期
		mav.addObject("date", labReservationService.getalldate());
		// 查找节次
		mav.addObject("festivaltimes", labReservationService.getallfestivaltimes());
		// 查找活动类别
		mav.addObject("activitycategory", labReservationService.getallactivitycategory());
		// 查找联系人
		mav.addObject("user", labReservationService.getUsersMap());
		// 查找课程组编号
		mav.addObject("classgruop", labReservationService.getallclassgruop());
		// 查找课程组编号
		mav.addObject("courseMap", labReservationService.getallclass());
		// 创建对象
		mav.addObject("labReservation", new LabReservation());
		// 学期
		mav.addObject("schoolterm", labReservationService.getschoolterm());
		// 当前学期
		mav.addObject("currTerm", shareService.getBelongsSchoolTerm(Calendar.getInstance()));
		// 获取当前学院下的年级
		if(sid!= null && sid!=-1)
		{
			mav.addObject("grade",userDAO.executeQuery("select c from User c where c.schoolAcademy.academyNumber like '"
					+ labCenterDAO.findLabCenterById(sid).getSchoolAcademy().getAcademyNumber() + "' group by c.grade"));
		}
		else{
			mav.addObject("grade",userDAO.executeQuery("select c from User c where c.schoolAcademy.academyNumber like '"
					+ shareService.getUser().getSchoolAcademy().getAcademyNumber() + "' group by c.grade"));
		}
		//实验室禁用时间段列表
		//mav.addObject("labRoomLimitTimes", labRoomLimitTimeDAO.executeQuery("select c from LabRoomLimitTime c where c.labId= " + id,0,-1));
		mav.setViewName("lab/lab_reservation/labAppointment.jsp");
		return mav;
	}

	/**
	 * 功能 根据周次获取节次 ajax
	 * 
	 * @param
	 * @param idkey
	 *            周id
	 * @return
	 */
	@RequestMapping("labreservation/screeningtake")
	public @ResponseBody
	String screeningtake(@RequestParam Integer idkey, Integer labid, Integer time) {

		String str = "";
		for (SystemTime iter : labReservationService.getscreeningtake(idkey, labid, time)) {
			str += "<option value=" + iter.getId() + ">" + iter.getSectionName()+ "</option>";
		}
		;
		return shareService.htmlEncode(str);
	}

	/**
	 * 功能 根据周次获取星期 ajax
	 * 
	 * @param
	 * @param idkey
	 *            周id
	 * @return
	 */
	@RequestMapping("labreservation/screeningtaketime")
	public @ResponseBody
	String screeningtaketime(@RequestParam Integer idkey) {
		String str = "";
		for (SchoolWeekday iter : labReservationService.getscreeningtaketime(idkey)) {
			str += "<option value=" + iter.getId() + ">" + iter.getWeekdayName() + "</option>";
		}
		;
		return shareService.htmlEncode(str);
	}

	/**
	 * 功能 根据星期获取周次
	 * 
	 * @param
	 * @param idkey
	 *            周id
	 * @return
	 */
	@RequestMapping("labreservation/screeningtakexingqi")
	public @ResponseBody
	String screeningtakexingqi(@RequestParam Integer idkey) {
		String str = "";
		/*for (CLabReservationWeek iter : labReservationService.getscreeningtakexingqi(idkey)) {
			str += "<option value=" + iter.getId() + ">" + iter.getName() + "</option>";
		}*/
		for (CDictionary iter : labReservationService.getscreeningtakexingqi(idkey)) {
			str += "<option value=" + iter.getId() + ">" + iter.getCName() + "</option>";
		}
		;
		return shareService.htmlEncode(str);
	}

	/**
	 * 功能 根据星期获取节次
	 * 
	 * @param
	 * @param idkey
	 *            周id
	 * @return
	 */
	@RequestMapping("labreservation/screeningtakejicebyxingqi")
	public @ResponseBody
	String screeningtakejicebyxingqi(@RequestParam Integer idkey) {
		String str = "";
		for (SystemTime iter : labReservationService.getscreeningtakejicebyxingqi(idkey)) {
			str += "<option value=" + iter.getId() + ">" + iter.getSectionName() + "</option>";
		}
		;
		return shareService.htmlEncode(str);
	}

	@RequestMapping("/labreservation/savelabreservation")
	public String savelabreservation(@ModelAttribute LabReservation labReservation, HttpServletRequest request) throws ParseException {

		labReservationService.saveLabReservationProc(labReservation, request);
		/*
		 * // 获取学期 String xueqi = request.getParameter("xueqi"); // 获取周次
		 * String[] zhouci = request.getParameterValues("zhouci"); // 获取星期
		 * String xingqi = request.getParameter("xingwq"); // 获取节次 String[] time
		 * = request.getParameterValues("jiexi"); // 获取学生 String students =
		 * request.getParameter("student1");
		 * 
		 * if (xueqi != null && xueqi != "" && zhouci != null && zhouci.length >
		 * 0 && xingqi != null && xingqi != "" && time != null && time.length >
		 * 0) { sa = labReservationService.savelabReservation1(labReservation,
		 * Integer.parseInt(idkey), xueqi, zhouci, xingqi, time);
		 * 
		 * } if (student != null && student != "") {
		 * labReservationService.savelabReservationstudent(sa, student); } if
		 * (students != null && students != "") {
		 * labReservationService.savelabReservationstudent(sa, students); }
		 */
		// return "redirect:/Lab/labAnnexList?currpage=1";
		return "redirect:/labreservation/Labreservationmanage?tage=0&currpage=1";
	}

	/**
	 * 功能 ： 导出大纲
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @param tage
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unused", "unchecked" })
	@RequestMapping("/lab/labAnnexListexportall")
	public void labAnnexListexportall(@ModelAttribute LabRoom labRoom, HttpServletRequest request,
			HttpServletResponse response, @RequestParam int page, @ModelAttribute("selected_labCenter") Integer sid)
			throws Exception {

		int pageSize = 30;

		List<LabRoom> finList = labReservationService.findLabRoompage(labRoom, page, pageSize, sid);
		List<Map> list = new ArrayList<Map>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		for (LabRoom f : finList) {
			Map map = new HashMap();
			map.put("operationoutlineName", f.getLabRoomName());
			if (f.getUser() != null) {
				map.put("user", f.getUser().getCname());
			} else {
				map.put("user", "");
			}
			map.put("classno", f.getLabRoomNumber());
			map.put("classname", f.getLabRoomAddress());

			list.add(map);
		}
		String title = "项目列表";
		String[] hearders = new String[] { "实验室名称", "创建者", "实验室编号", "实验室地址" };// 表头数组
		String[] fields = new String[] { "operationoutlineName", "user", "classno", "classname" };// Financialresources对象属性数组
		TableData td = ExcelUtils.createTableData(list, ExcelUtils.createTableHeader(hearders), fields);
		JsGridReportBase report = new JsGridReportBase(request, response);
		report.exportToExcel(title, shareService.getUser().getCname(), td);

	}

	/****************************************************************************
	 * 功能：查询实验室 作者：薛帅 时间：2014-08-6
	 ****************************************************************************/
	@RequestMapping("/labreservation/Labreservationmanage")
	public ModelAndView Labreservationmanage(@ModelAttribute LabReservation labReservation,
			@RequestParam Integer currpage, int tage, @ModelAttribute("selected_labCenter") Integer sid) {
		// 新建ModelAndView对象；
		ModelAndView mav = new ModelAndView();
		// 查询表单的对象d
		mav.addObject("labReservation", labReservation);
		int pageSize = 30;// 每页20条记录
		// 查询出来的总记录条数
		int totalRecords = labReservationService.findLabreservationmanage(labReservation, tage, 1, -1, sid).size();
		// 分页信息
		Map<String, Integer> pageModel = shareService.getPage(currpage, pageSize, totalRecords);
		// 根据分页信息查询出来的记录
		Set<LabReservation> LabReservations = labReservationService.findLabreservationmanage(labReservation, tage,
				currpage, pageSize, sid);

		List<Labreservationlist> list = new ArrayList<Labreservationlist>();
		for (LabReservation lab : LabReservations) {
			Labreservationlist la = new Labreservationlist();
			la.setId(lab.getId());
			/*if (lab.getCLabReservationType() != null) {
				la.setNametype(lab.getCLabReservationType().getName());
			}*/
			if (lab.getCDictionaryByLabReservetYpe() != null) {
				la.setNametype(lab.getCDictionaryByLabReservetYpe().getCName());
			}
			if (lab.getEventName() != null) {
				la.setName(lab.getEventName());
			} else {
				la.setName(lab.getEnvironmentalRequirements());
			}
			Set<String> week = new HashSet<String>();
			Set<String> day = new HashSet<String>();
			Set<String> time = new HashSet<String>();
			if (lab.getTimetableAppointment().getTimetableAppointmentSameNumbers().size() > 0)
				for (TimetableAppointmentSameNumber timetableAppointmentSameNumber : lab.getTimetableAppointment()
						.getTimetableAppointmentSameNumbers()) {
					if (timetableAppointmentSameNumber.getStartWeek().intValue() != timetableAppointmentSameNumber.getEndWeek().intValue()) {
						week.add(timetableAppointmentSameNumber.getStartWeek().toString() + "-"
								+ timetableAppointmentSameNumber.getEndWeek().toString());
					} else {
						week.add(timetableAppointmentSameNumber.getStartWeek().toString());

					}
					day.add(lab.getTimetableAppointment().getWeekday().toString());
					if (timetableAppointmentSameNumber.getStartClass().intValue() != timetableAppointmentSameNumber.getEndClass().intValue()) {
						time.add(timetableAppointmentSameNumber.getStartClass().toString() + "-"
								+ timetableAppointmentSameNumber.getEndClass().toString());
					} else {
						time.add(timetableAppointmentSameNumber.getStartClass().toString());
					}
				}
			else {
				if (lab.getTimetableAppointment().getStartWeek().intValue() != lab.getTimetableAppointment().getEndWeek().intValue()) {
					week.add(lab.getTimetableAppointment().getStartWeek().toString() + "-"
							+ lab.getTimetableAppointment().getEndWeek().toString());
				}else{
					week.add(lab.getTimetableAppointment().getStartWeek().toString());
				}
				
				day.add(lab.getTimetableAppointment().getWeekday().toString());
				if (lab.getTimetableAppointment().getStartClass().intValue() != lab.getTimetableAppointment().getEndClass().intValue()) {
					time.add(lab.getTimetableAppointment().getStartClass().toString() + "-"
							+ lab.getTimetableAppointment().getEndClass().toString());
				}else{
					time.add(lab.getTimetableAppointment().getStartClass().toString());

				}
				

			}
			int dd = week.size();
			String[] weeks = week.toArray(new String[dd]);
			String[] days = day.toArray(new String[day.size()]);
			String[] timea = time.toArray(new String[time.size()]);
			;
			// 数组排序
			String[] weekt = insertSort(weeks);
			String[] timet = insertSort(timea);

			la.setWeek(weekt);
			la.setTime(timet);
			la.setDay(days);
			//设置实验室
			if (lab.getLabRoom() != null) {
				la.setLab(lab.getLabRoom().getLabRoomName());
				la.setLabRoom(lab.getLabRoom());
			}
			//设置申请者
			if (lab.getUser() != null) {
				la.setUser(lab.getUser());
			}
			la.setCont(lab.getAuditResults());
			la.setStart(lab.getReservations());
			la.setFabu(lab.getItemReleasese());
			la.setUser(lab.getUser());//申请人
			la.setLabRoom(lab.getLabRoom());//实验室
			String auditUser="";
			Set<LabReservationAudit> audits=lab.getLabReservationAudits();
			if (audits.size()>0) {
				for (LabReservationAudit labReservationAudit : audits) {
					auditUser=labReservationAudit.getUser().getCname()+",";
				}
			}
			if (auditUser.length()>0) {
				auditUser=auditUser.substring(0, auditUser.length()-1);
			}
			la.setAuditUser(auditUser);
			//当前用户是否能审核
			int isAudit=0;
			Set<LabRoomAdmin> admins=lab.getLabRoom().getLabRoomAdmins();
			if (admins.size()>0) {
				for (LabRoomAdmin labRoomAdmin : admins) {
					if (labRoomAdmin.getTypeId()==1&&labRoomAdmin.getUser().getUsername()==shareService.getUser().getUsername()) {
						isAudit=1;break;
					}
				}
			}
			la.setIsAudit(isAudit);
			list.add(la);

		}

		mav.addObject("labReservations", list);
		mav.addObject("pageModel", pageModel);
		mav.addObject("totalRecords", totalRecords);
		mav.addObject("currpage", currpage);
		mav.addObject("pageSize", pageSize);
		mav.addObject("tage", tage);
		SchoolTerm shoolTerm = new SchoolTerm();
		List<SchoolTerm> set = new ArrayList<SchoolTerm>();
		set.addAll(labReservationService.timemap());
		if (labReservation.getRemarks() != null && labReservation.getRemarks().trim() != "") {
			shoolTerm = schoolTermDAO.findSchoolTermById(Integer.parseInt(labReservation.getRemarks()));
			mav.addObject("shoolTerm", shoolTerm);
			set.remove(shoolTerm);
		}

		mav.addObject("timemap", set);
        //登录用户
		mav.addObject("user", shareService.getUserDetail());
		mav.setViewName("lab/lab_reservation/labreaervationmanageList.jsp");
		return mav;
	}

	public static String[] insertSort(String[] weeks) {// 插入排序算法
		for (int i = 1; i < weeks.length; i++) {
			for (int j = i; j > 0; j--) {
                String start =weeks[j];
                String end =weeks[j-1];
                if(start.indexOf("-")!=-1){
                	start = start.substring(start.indexOf("-"));
                }
                if(end.indexOf("-")!=-1){
                	end = end.substring(end.indexOf("-"));
                }
                
				if (Integer.parseInt(start) < Integer.parseInt(end)) {
					String temp = weeks[j - 1];
					weeks[j - 1] = weeks[j];
					weeks[j] = temp;
				} else
					break;
			}
		}
		return weeks;
	}

	/**
	 * 查看实验室预约课程的学生 作者：薛帅 参数 预约课程id
	 */
	@RequestMapping("/lab/findstudentforlabreation")
	public ModelAndView findstudentforlabreation(
			@ModelAttribute LabReservationTimeTableStudent labReservationTimeTableStudent, @RequestParam int idkey,
			int currpage) {

		// 新建ModelAndView对象；
		ModelAndView mav = new ModelAndView();
		// 查询表单的对象
		mav.addObject("labReservationTimeTableStudent", labReservationTimeTableStudent);
		int pageSize = 30;// 每页20条记录
		// 查询出来的总记录条数
		int totalRecords = labReservationService.getstudentforlabreation(labReservationTimeTableStudent, idkey).size();
		// 分页信息
		Map<String, Integer> pageModel = shareService.getPage(currpage, pageSize, totalRecords);
		// 根据分页信息查询出来的记录
		List<LabReservationTimeTableStudent> TableStudent = labReservationService.getstudentforlabreationpage(
				labReservationTimeTableStudent, idkey, currpage, pageSize);
		mav.addObject("TableStudent", TableStudent);
		mav.addObject("pageModel", pageModel);
		mav.addObject("totalRecords", totalRecords);
		mav.addObject("currpage", currpage);
		mav.addObject("pageSize", pageSize);
		mav.addObject("idkey", idkey);
		mav.setViewName("lab/lab_reservation/labStudentforLabreation.jsp");// 设置jsp页面
		return mav;

	}

	/**
	 * 实验室日志显示 作者：何莉莉 时间：
	 */
	@RequestMapping("/lab/listLabRoomLog")
	public ModelAndView listLabRoomLog(@RequestParam int currpage) {
		ModelAndView mav = new ModelAndView();
		// 要返回的分页模型
		int pageSize = 15;
		PageModel pageModel = new PageModel(currpage, labRoomLogService.countLabRoomLogs(), pageSize);
		mav.addObject("pageModel", pageModel);// 返回分页模型
		mav.addObject("logs", labRoomLogService.findAllLabRoomLogs((currpage - 1) * pageSize, pageSize));// 返回数据
		mav.setViewName("lab/listLabRoomLog.jsp");// 设置jsp页面
		return mav;

	}

	/**
	 * 实验室日志显示 作者：何莉莉 时间：
	 */
	@RequestMapping("/lab/checkButton")
	public ModelAndView checkButton(@RequestParam int idkey, int tage) {
		ModelAndView mav = new ModelAndView();
		LabReservation sd = labReservationService.getlabReservationinfor(idkey);
		mav.addObject("infor", sd);
		mav.addObject("tage", tage);
		mav.setViewName("lab/lab_reservation/labreaervationCentrolManagerView.jsp");
		return mav;

	}

	@RequestMapping("/labreservation/labteachershow")
	public ModelAndView labteachershow(@RequestParam int idkey, int tage) {
		ModelAndView mav = new ModelAndView();
		LabReservation lab = labReservationService.getlabReservationinfor(idkey);
		Labreservationlist la = new Labreservationlist();
		la.setId(lab.getId());
		/*if (lab.getCLabReservationType() != null) {
			la.setNametype(lab.getCLabReservationType().getName());
		}*/
		if (lab.getCDictionaryByLabReservetYpe() != null) {
			la.setNametype(lab.getCDictionaryByLabReservetYpe().getCName());
		}
		if (lab.getEventName() != null) {
			la.setName(lab.getEventName());
		} else {
			la.setName(lab.getEnvironmentalRequirements());
		}
		Set<String> week = new HashSet<String>();
		Set<String> day = new HashSet<String>();
		Set<String> time = new HashSet<String>();
		for (LabReservationTimeTable labre : lab.getLabReservationTimeTables()) {
			//week.add(labre.getCLabReservationWeek().getId().toString());
			week.add(labre.getCDictionary().getCName().toString());
			day.add(labre.getSchoolWeekday().getId().toString());
			time.add(labre.getSystemTime().getId().toString());
		}
		int dd = week.size();
		String[] weeks = week.toArray(new String[dd]);
		String[] days = day.toArray(new String[day.size()]);
		String[] timea = time.toArray(new String[time.size()]);
		;
		// 数组排序
		String[] weekt = insertSort(weeks);
		String[] timet = insertSort(timea);

		la.setWeek(weekt);
		la.setTime(timet);
		la.setDay(days);
		if (lab.getLabRoom() != null) {
			la.setLab(lab.getLabRoom().getLabRoomName());
		}
		la.setCont(lab.getAuditResults());
		la.setStart(lab.getReservations());
		la.setFabu(lab.getItemReleasese());
		mav.addObject("infor", la);
		mav.addObject("tage", tage);
		mav.setViewName("lab/lab_reservation/labteachershow.jsp");
		return mav;

	}

	/**
	 * 实验室预约的详细信息 老师审核 作者：何莉莉 时间：
	 */
	@RequestMapping("/labreservation/teacherLabreservationaudt")
	public ModelAndView teacherLabreservationaudt(@RequestParam int idkey, int tage) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("labReservationAudit", new LabReservationAudit());
		mav.addObject("tage", tage);
		// 要返回的分页模型
		LabReservation lab = labReservationService.getlabReservationinfor(idkey);
		Labreservationlist la = new Labreservationlist();
		la.setId(lab.getId());
		/*if (lab.getCLabReservationType() != null) {
			la.setNametype(lab.getCLabReservationType().getName());
		}*/
		if (lab.getCDictionaryByLabReservetYpe() != null) {
			la.setNametype(lab.getCDictionaryByLabReservetYpe().getCName());
		}
		if (lab.getEventName() != null) {
			la.setName(lab.getEventName());
		} else {
			la.setName(lab.getEnvironmentalRequirements());
		}
		Set<String> week = new HashSet<String>();
		Set<String> day = new HashSet<String>();
		Set<String> time = new HashSet<String>();
		for (LabReservationTimeTable labre : lab.getLabReservationTimeTables()) {
			//week.add(labre.getCLabReservationWeek().getId().toString());
			week.add(labre.getCDictionary().getCName().toString());
			day.add(labre.getSchoolWeekday().getId().toString());
			time.add(labre.getSystemTime().getId().toString());
		}
		int dd = week.size();
		String[] weeks = week.toArray(new String[dd]);
		String[] days = day.toArray(new String[day.size()]);
		String[] timea = time.toArray(new String[time.size()]);
		;
		// 数组排序
		String[] weekt = insertSort(weeks);
		String[] timet = insertSort(timea);

		la.setWeek(weekt);
		la.setTime(timet);
		la.setDay(days);
		if (lab.getLabRoom() != null) {
			la.setLab(lab.getLabRoom().getLabRoomName());
		}
		
		la.setCont(lab.getAuditResults());
		la.setStart(lab.getReservations());
		la.setFabu(lab.getItemReleasese());

		mav.addObject("l", la);// 返回数据
		mav.addObject("lab", lab);// 返回数据
		int hh = 0;
		Set<LabRoomAdmin> s = lab.getLabRoom().getLabRoomAdmins();
		for (LabRoomAdmin labRoomAdmin : s) {
			if (labRoomAdmin.getUser().getUsername().equals(shareService.getUser().getUsername())) {
				hh++;
			}
		}
		mav.addObject("man", hh);
		mav.addObject("admins", s);
		//设置登录用户
		mav.addObject("user",shareService.getUserDetail());
		mav.setViewName("lab/lab_reservation/teacherLabreservationaudt.jsp");// 设置jsp页面
		return mav;

	}

	@RequestMapping("/labreservation/labreationdelectitem")
	public String administratorreview(@RequestParam int idkey) {

		labReservationService.labreationdelectitem(idkey);
		return "redirect:/labreservation/Labreservationmanage?tage=0&currpage=1";

	}

	/**
	 * 实验室日志显示 作者：薛帅 时间：8-12 参数 idkey 预约记录id tage 标记位区分审核状态
	 */
	@RequestMapping("/labreservation/administratorreview")
	public ModelAndView administratorreview(@RequestParam int idkey, int tage) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("labReservationAudit", new LabReservationAudit());
		// 要返回的分页模型
		mav.addObject("l", labReservationService.getlabReservationinfor(idkey));// 返回数据
		mav.setViewName("lab/lab_reservation/labreservationadministratorreview.jsp");// 设置jsp页面
		return mav;

	}

	/**
	 * 功能：保存老师审核结果 作者 薛帅
	 * 
	 * @param idkey
	 *            预约列表的id
	 * @param tage
	 *            标记为
	 * @return
	 */
	@RequestMapping("/labReservation/auditsavelabreservtion")
	public String auditsavelabreservtion(@ModelAttribute LabReservationAudit labReservationAudit,
			@RequestParam int idkey, int tage) {

		labReservationAudit.setUser(shareService.getUser());
		labReservationService.auditsavelabreservtion(labReservationAudit, idkey);

		return "redirect:/labreservation/Labreservationmanage?tage=" + tage + "&currpage=1";
	}

	/**
	 * 功能：发布课程 作者 薛帅
	 * 
	 * @param idkey
	 *            预约列表的id
	 * @param tage
	 *            标记为
	 * @return
	 */
	@RequestMapping("/lab/publishedcourses")
	public String publishedcourses(@RequestParam int idkey, int tage) {

		LabReservation d = labReservationService.getlabReservationinfor(idkey);
		d.setItemReleasese(1);
		d.setSelecteNumber(0);
		labReservationDAO.store(d);
		return "redirect:/labreservation/Labreservationmanage?tage=" + tage + "&currpage=1";
	}

	
	/************************************************************
	 * @实验室预约-预约时间判冲
	 * @作者：魏诚
	 * @日期：2015-07-09
	 ************************************************************/
	@RequestMapping("/lab/getValidWeeksMap")
	public @ResponseBody
	String getTimetableWeeksMap(@RequestParam int term, int weekday, int[] labrooms, int[] classes) {
		// 返回可用的星期信息
		return labReservationService.getValidWeeksMap(term, weekday, labrooms, classes);
	}
	
	/****************************************************************************
	 * 功能：门禁进出记录
	 * 作者：贺子龙
	 * 时间：2015-11-30
	 ****************************************************************************/
	@RequestMapping("/lab/entranceManageForLab")
	public ModelAndView entranceManageForLab(@ModelAttribute LabRoom labRoom, @RequestParam Integer page,@ModelAttribute("selected_labCenter")Integer cid) {
		ModelAndView mav = new ModelAndView();
		//获取当前用户
		User user=shareService.getUser();
		mav.addObject("user", user);
		//查询表单的对象
		mav.addObject("labRoom", labRoom);
		// 设置分页变量并赋值为20
		int pageSize = 15;
		//查询出来的总记录条数
		int totalRecords = cmsShowService.findLabRoomBySchoolAcademyDefault(labRoom,1,-1,2,cid).size();
		Map<String, Integer> pageModel = shareService.getPage(page, pageSize, totalRecords);
		//当前中心下的实验室
		List<LabAnnex> labAnnexs=labAnnexService.findAllLabAnnexBySchoolAcademy(cid);
		mav.addObject("labAnnexs", labAnnexs);
		//页面显示的实验室
		List<LabRoom> labRoomList=cmsShowService.findLabRoomBySchoolAcademyDefault(labRoom,page,pageSize,2,cid);//门禁--2
		
		mav.addObject("labRoomList",labRoomList);
		mav.addObject("pageModel",pageModel);
		mav.addObject("totalRecords", totalRecords);
		mav.addObject("page", page);
		mav.addObject("pageSize", pageSize);
		mav.setViewName("lab/lab_record/listEntranceManageForLab.jsp");
		return mav;
	}
	
	
	/*************************************************************************************
	 * @內容：开放实验室资源--门禁
	 * @作者：贺子龙
	 * @日期：2015-12-01
	 *************************************************************************************/
	@RequestMapping("/lab/entranceList")
	public ModelAndView entranceList(@RequestParam Integer id,Integer page,@ModelAttribute CommonHdwlog commonHdwlog,HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		String starttime=request.getParameter("starttime");
		String endtime=request.getParameter("endtime");
		mav.addObject("starttime", starttime);
		mav.addObject("endtime", endtime);
		mav.addObject("commonHdwlog", commonHdwlog);
		mav.addObject("id", id);
		//id对应的物联设备
		LabRoomAgent agent=labRoomAgentDAO.findLabRoomAgentByPrimaryKey(id);
		String ip=agent.getHardwareIp();
		String port=agent.getHardwarePort();
		// 设置分页变量并赋值为20
		//int pageSize = CommonConstantInterface.INT_PAGESIZE;
		int pageSize = 30;
		//查询出来的总记录条数
		int totalRecords = cmsShowService.findLabRoomAccessByIpCount(commonHdwlog,ip,port,request);
		Map<String, Integer> pageModel = shareService.getPage(page, pageSize, totalRecords);
		//页面显示的实验室
		List<LabAttendance> accessList=cmsShowService.findLabRoomAccessByIp(commonHdwlog,ip,port,page,pageSize,request);
		
		mav.addObject("accessList",accessList);
		mav.addObject("pageModel",pageModel);
		mav.addObject("totalRecords", totalRecords);
		mav.addObject("page", page);
		mav.addObject("pageSize", pageSize);
		mav.setViewName("lab/lab_record/listLabRoomEntrance.jsp");
		return mav;
	}
	
	/************************************************************
	 * @description:实验室预约-预约时间判冲
	 * @author：郑昕茹
	 * @date：2016-11-12
	 ************************************************************/
	@RequestMapping("/lab/getValidWeeksMapApp")
	public void getTimetableWeeksMapApp(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException  {
		response.setContentType("text / html；charset=utf-8");
        response.setCharacterEncoding("utf-8");       
        PrintWriter out = response.getWriter();
	    String labroomID = request.getParameter("labroomID");
	    String  weekDay= request.getParameter("weekDay");
	    String  section= request.getParameter("section");
	    System.out.println("labroomID -> "+labroomID);
	    System.out.println("weekDay -> "+weekDay);
	    System.out.println("section -> "+section);
	    
	    
	    out.println("1,2,3,6,8");
		out.flush();
		out.close();
		// 返回可用的星期信息
		//return labReservationService.getValidWeeksMap(term, weekday, labrooms, classes);
	}
	
	
	/****************************************************************************
	 * description:根据实验室找到实验室禁用时间
	 *@author 郑昕茹
	 * @date:2017-5-11
	 ****************************************************************************/
	@RequestMapping("/findLabRoomLimitTimesByLabId")
	public @ResponseBody String findSchoolDeviceByNameAndNumber(@RequestParam Integer labId) {
		List<LabRoomLimitTime> times = labRoomLimitTimeDAO.executeQuery("select c from LabRoomLimitTime c where c.labId= " +labId +" and flag = 0",0,-1);
	    String s="";
	    for(LabRoomLimitTime t:times){
	    	s+="<tr>"+
	    	"<td>"+t.getSchoolTerm().getTermName()+"</td>"+
	    	"<td>"+t.getStartweek()+"-"+t.getEndweek()+"周</td>"+
	    	"<td>"+t.getStartclass()+"-"+t.getEndclass()+"节</td>"+
	    	"<td>周"+t.getWeekday()+"</td>";
	    	if(t.getFlag() == 0){
	    		s += "<td>手动添加</td></tr>";
	    	}
	    	else{
	    		s += "<td>排课生成</td></tr>";
	    	}
	    }
		return shareService.htmlEncode(s);
	}
	
	
	/************************************************************
	 * description:实验室预约-判断实验室预约是否在禁用时间中
	 * @author:郑昕茹
	 * @date:2017-05-11
	 ************************************************************/
	@RequestMapping("/lab/judgeTimeIsInLimitTime")
	public @ResponseBody
	Map<String, Object> judgeTimeIsInLimitTime(@RequestParam int term, int weekday, int labroom, int[] classes, int[] weeks) {
		Map<String, Object> map = new HashMap<String, Object>();
		String s="";
		boolean isIn = false;
		for(int i = 0; i < weeks.length; i++){
			for(int j = 0; j < classes.length; j++){
				String sql = "select l from LabRoomLimitTime l where 1 = 1 and startweek <=" + weeks[i] + " and endweek >=" + weeks[i];
				sql += " and startclass <=" +classes[j]+" and endclass >="+classes[j];
				sql += " and schoolTerm.id = "+term;
				sql += " and lab_id ="+labroom;
				sql += " and weekday = "+weekday;
				List<LabRoomLimitTime> times = labRoomLimitTimeDAO.executeQuery(sql, 0,-1);
				if(times.size() != 0){
					isIn = true;
					 for(LabRoomLimitTime t:times){
						 s += t.getSchoolTerm().getTermName()+" "+t.getStartweek()+"-"+t.getEndweek()+"周 "+t.getStartclass()+"-"+t.getEndclass()+"节 "+"周"+t.getWeekday();
					 }
				}
			}
		}
		map.put("hint", s);
		if(isIn == true)map.put("isIn", 1);
		else map.put("isIn", 0);
		
		return map;
	}
	/************************************************************
	 * @实验室课表
	 * @作者：戴昊宇
	 * @日期：2017-08-15
	 ************************************************************/
	@RequestMapping("/lab/labReservationCalendar")
	public ModelAndView labReservationCalendar(HttpServletRequest request,@ModelAttribute("selected_labCenter")Integer cid,int roomId) {
		ModelAndView mav = new ModelAndView();
		/*-------用作查询------------*/
		mav.addObject("courseCode", request.getParameter("courseCode"));
		// 获取登录用户信息
		mav.addObject("user", shareService.getUserDetail());
		// 获得学期
		int term = shareService.getBelongsSchoolTerm(Calendar.getInstance()).getId();
		if (request.getParameter("term") != null) {
			term = Integer.parseInt(request.getParameter("term"));
		}
		// 获取学期列表用于查询
		List<SchoolTerm> schoolTerms = shareService.findAllSchoolTerms1();
		mav.addObject("schoolTerm", schoolTerms);
		mav.addObject("term", term);
		mav.addObject("labRoom", labRoomDAO.findLabRoomById(roomId));
		mav.addObject("roomId", roomId);
		// 实验中心
		if (!EmptyUtil.isObjectEmpty(labRoomDAO.findLabRoomById(roomId))) {
			cid = labRoomDAO.findLabRoomById(roomId).getLabCenter().getId();
		}
		mav.addObject("cid", cid);
		//保存的实验中心cid
		// 获取周
		int week = shareService.getBelongsSchoolWeek(Calendar.getInstance()); 
		if(!EmptyUtil.isStringEmpty(request.getParameter("week"))){
			week = Integer.parseInt(request.getParameter("week"));
		}
		mav.addObject("week", week);
		//获取当前学期
		int termId = shareService.getBelongsSchoolTerm(Calendar.getInstance()).getId();
		// 获取所有的学期
		mav.addObject("termId", termId);  //当前学期
		mav.addObject("weeksMap", teachingReportService.getWeekMap());  //周次下拉框数据
		// 获取实验分室室排课记录
		List<Object[]> listLabcalendar = labReservationService.getListLabCalendar(roomId);
		// 拼接班级编号并去重
		String classNumber="";
		Set<String> labSet=new HashSet<String>();
        for(Object[] labcalendar:listLabcalendar){
        	if(labcalendar[2]!=null){
        		String[] split = labcalendar[2].toString().split(",");
        		for(int i=0;i<split.length;i++){
        			labSet.add(split[i]);
        		}
        	}
        }
        for(String s:labSet){
        	classNumber+=s+",";
        }
        List<Object[]> allCalendar = labReservationService.getAllCalendar(listLabcalendar.size()>0&&!classNumber.equals("")?classNumber.substring(0, classNumber.length()-1):classNumber);
        mav.addObject("centerCalendar", allCalendar);
		//获取所有借此对应的时间
		mav.addObject("systemtime", systemService.getAllTimebyjieci());
		mav.addObject("schoolweeek", schoolWeekService.getMapDate());
		List<Object[]> schoolTermWeeks = termDetailService
				.findViewSchoolTermWeek(term);
		mav.addObject("schoolTermWeeks", schoolTermWeeks);
		mav.addObject("specialSchoolWeeks", termDetailService
				.findSpecialSchoolWeekByTerm(term));
		mav.setViewName("lab/lab_reservation/labReservationCalendar.jsp");
		return mav;
	}
}