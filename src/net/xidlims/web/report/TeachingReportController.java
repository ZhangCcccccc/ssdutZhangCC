/****************************************************************************
 * 命名规范：
 * 本控制层调整必须是按照如下的路径调整：@RequestMapping("report/teachingReport/sample")，全部小写。
 * 列表信息listxxxx，獲取信息：getxxxx 编辑信息：editxxxx 删除信息：detelexxxx 新增信息 newxxxx
 * 导出信息exportxxxx 保存信息：savexxxx  
 ****************************************************************************/

package net.xidlims.web.report;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.luxunsh.util.EmptyUtil;
import net.xidlims.constant.CommonConstantInterface;
import net.xidlims.constant.MonthReport;
import net.xidlims.dao.LabCenterDAO;
import net.xidlims.dao.TimetableAppointmentDAO;
import net.xidlims.domain.LabCenter;
import net.xidlims.domain.SchoolTerm;
import net.xidlims.domain.TimetableAppointment;
import net.xidlims.service.common.ShareService;
import net.xidlims.service.report.TeachingReportService;
import net.xidlims.service.system.SchoolWeekService;
import net.xidlims.service.timetable.OuterApplicationService;
import net.xidlims.service.timetable.SchoolCourseDetailService;
import net.xidlims.service.timetable.TimetableAppointmentService;

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
 * 功能：教学报表
 * 绩效报表模块 作者：贺子龙 时间：2015-11-16
 ****************************************************************************/
@Controller("TeachingReportController")
@SessionAttributes("selected_labCenter")
public class TeachingReportController<JsonResult> {
	
	@InitBinder
	public void initBinder(WebDataBinder binder, HttpServletRequest request) { // Register static property editors.
		binder.registerCustomEditor(java.util.Calendar.class, new org.skyway.spring.util.databinding.CustomCalendarEditor());
		binder.registerCustomEditor(byte[].class, new org.springframework.web.multipart.support.ByteArrayMultipartFileEditor());
		binder.registerCustomEditor(boolean.class, new org.skyway.spring.util.databinding.EnhancedBooleanEditor(false));
		binder.registerCustomEditor(Boolean.class, new org.skyway.spring.util.databinding.EnhancedBooleanEditor(true));
		binder.registerCustomEditor(java.math.BigDecimal.class, new org.skyway.spring.util.databinding.NaNHandlingNumberEditor(java.math.BigDecimal.class, true));
		binder.registerCustomEditor(Integer.class, new org.skyway.spring.util.databinding.NaNHandlingNumberEditor(Integer.class, true));
		binder.registerCustomEditor(java.util.Date.class, new org.skyway.spring.util.databinding.CustomDateEditor());
		binder.registerCustomEditor(String.class, new org.skyway.spring.util.databinding.StringEditor());
		binder.registerCustomEditor(Long.class, new org.skyway.spring.util.databinding.NaNHandlingNumberEditor(Long.class, true));
		binder.registerCustomEditor(Double.class, new org.skyway.spring.util.databinding.NaNHandlingNumberEditor(Double.class, true));
	}
	
	@Autowired
	private OuterApplicationService outerApplicationService;
	@Autowired
	private ShareService shareService;
	@Autowired
	private SchoolWeekService schoolWeekService;
	@Autowired
	private SchoolCourseDetailService schoolCourseDetailService;
	@Autowired
	private TeachingReportService teachingReportService;
	@Autowired
	private TimetableAppointmentService timetableAppointmentService;
	@Autowired
	private LabCenterDAO labCenterDAO;
	@Autowired
	private TimetableAppointmentDAO timetableAppointmentDAO;
	
	/************************************************************
	 * @学期登记报表
	 * @作者：何立友
	 * @日期：2014-09-11
	 ************************************************************/
	@RequestMapping("/report/teachingReport/termRegister")
	public ModelAndView termRegister(@ModelAttribute SchoolTerm schoolTerm,@ModelAttribute("selected_labCenter")Integer cid) {
		ModelAndView mav = new ModelAndView();
		// 获取去重的实验分室列表
		mav.addObject("labRoomMap", outerApplicationService.getLabRoomMap(cid));
		// 获取登录用户信息
		mav.addObject("user", shareService.getUserDetail());
		// 获取当前学期
		SchoolTerm currentTerm = new SchoolTerm();
		// 获取所有的学期
		List<SchoolTerm> terms = schoolCourseDetailService.getSchoolTermsByNow();
		
		mav.addObject("weeksMap", teachingReportService.getWeekMap());  //周次下拉框数据
		/**
		 * 如果学期获取为零则学期为空则返回显示页面
		 */
		if (terms.size() > CommonConstantInterface.INT_CURRPAGE_ZERO) {
			currentTerm = terms.get(CommonConstantInterface.INT_CURRPAGE_ZERO);
		} else {
			mav.setViewName("reports/teachingReport/termRegister.jsp");
			return mav;
		}
		if(schoolTerm.getId() == null) //默认页面显示当前学期
		{
			mav.addObject("schoolTerm", currentTerm);
			schoolTerm = currentTerm;
		}
		List<SchoolTerm> schoolTerms = outerApplicationService.getSchoolTermList();
		mav.addObject("schoolTerms", schoolTerms);
		
		int week = shareService.getBelongsSchoolWeek(Calendar.getInstance());
		if (!EmptyUtil.isObjectEmpty(schoolTerm.getTermCode())) {
			week = schoolTerm.getTermCode();
		}
		
		// 获取实验室分室排课记录
		mav.addObject("labTimetable", teachingReportService.getLabTimetableAppointments(schoolTerm.getId(), cid, week));
		// 获取二次排课实验分室室排课记录
		mav.addObject("labSelfTimetable", teachingReportService.getSelfLabTimetableAppointments(schoolTerm.getId(), cid, week));
		mav.setViewName("reports/teachingReport/termRegister.jsp");
		return mav;
	}
	
	/************************************************************
	 * @学期登记报表导出Excel
	 * @作者：何立友
	 * @日期：2014-09-11
	 ************************************************************/
	@RequestMapping("/report/teachingReport/exportTermRegister")
	public void exportTermRegister(@ModelAttribute SchoolTerm schoolTerm, HttpServletRequest request, HttpServletResponse response, @ModelAttribute("selected_labCenter") Integer cid)throws Exception{
		teachingReportService.exportTermRegister(schoolTerm, cid, request, response);
	}
	
	/************************************************************
	 * @周次登记报表
	 * @作者：何立友
	 * @日期：2014-09-11
	 ************************************************************/
	@RequestMapping("/report/teachingReport/weekRegister")
	public ModelAndView weekRegister(@ModelAttribute SchoolTerm schoolTerm,@ModelAttribute("selected_labCenter")Integer cid) {
		ModelAndView mav = new ModelAndView();
		// 获取去重的实验分室室列表
		mav.addObject("labRoomMap", outerApplicationService.getLabRoomMap(cid));
		// 获取登录用户信息
		mav.addObject("user", shareService.getUserDetail());
		//获取当前学期
		SchoolTerm currentTerm = new SchoolTerm();
		// 获取所有的学期
		List<SchoolTerm> terms = schoolCourseDetailService.getSchoolTermsByNow();
		/**
		 * 如果学期获取为零则学期为空则返回显示页面
		 */
		if (terms.size() > CommonConstantInterface.INT_CURRPAGE_ZERO) {
			currentTerm = terms.get(CommonConstantInterface.INT_CURRPAGE_ZERO);
		} else {
			mav.setViewName("reports/teachingReport/weekRegister.jsp");
			return mav;
		}
		if(schoolTerm.getId() == null) //默认页面显示当前学期,所有周次
		{
			mav.addObject("schoolTerm", currentTerm);
			schoolTerm = currentTerm;
			schoolTerm.setTermCode(0);
		}
		List<SchoolTerm> schoolTerms = outerApplicationService.getSchoolTermList();
		mav.addObject("schoolTerms", schoolTerms);  //学期下拉框数据
		mav.addObject("weeksMap", teachingReportService.getWeekMap());  //周次下拉框数据
		// 获取实验分室室排课记录
		mav.addObject("labTimetable", teachingReportService.getLabTimetableAppointments(schoolTerm.getId(), cid, schoolTerm.getTermCode()));
		mav.addObject("labSelfTimetable", teachingReportService.getSelfLabTimetableAppointments(schoolTerm.getId(), cid, schoolTerm.getTermCode()));
		mav.setViewName("reports/teachingReport/weekRegister.jsp");
		return mav;
	}
	
	
	
	/************************************************************
	 * @ajax获取本学期本学院的课程
	 * @作者：何立友
	 * @日期：2014-09-12
	 ************************************************************/
	@RequestMapping("/report/teachingReport/getTeachersByTerm")
	public @ResponseBody String getTeachersByTerm(@RequestParam int termId, @ModelAttribute("selected_labCenter") Integer cid){
		return shareService.htmlEncode(teachingReportService.getTeachersByTerm(termId, cid));
	}
	
	/*************************************************************************************
	 * @获取指定学期、指定教师课程(ajax)
	 * @作者： 何立友
	 * @日期：2014-09-12
	 *************************************************************************************/
	@RequestMapping("/report/teachingReport/getCoursesByTermTeacher")
	public @ResponseBody String getCoursesByTermTeacher(@RequestParam int termId, @RequestParam String username){
		return shareService.htmlEncode(teachingReportService.getCourseDetailsByTermTeacher(termId, username));
	}
	
	
	/************************************************************
	 * @throws Exception 
	 * @导出周次登记报表
	 * @作者：徐龙帅
	 * @日期：2015-01-14
	 ************************************************************/
	@RequestMapping("/report/teachingReport/exportWeekRegister")
	public void export(@ModelAttribute SchoolTerm schoolTerm,HttpServletRequest request, HttpServletResponse response,@ModelAttribute("selected_labCenter") Integer cid) throws Exception{
		teachingReportService.exportweekRegister(schoolTerm, cid, request, response);
	}
	
	/************************************************************
	 * @throws ParseException 
	 * @月报报表
	 * @作者：贺子龙
	 * @日期：2015-11-16
	 ************************************************************/
	//TODO 这里直接抄的学期报表，需要更改
	@RequestMapping("/report/teachingReport/monthRegister")
	public ModelAndView monthRegister(HttpServletRequest request,
			@ModelAttribute TimetableAppointment timetableAppointment,
			@ModelAttribute("selected_labCenter")Integer cid,@RequestParam int currpage) throws ParseException {
		ModelAndView mav = new ModelAndView();
		int pageSize = 20;
		int term = shareService.getBelongsSchoolTerm(Calendar.getInstance()).getId();
		if (request.getParameter("term") != null) {
			term = Integer.parseInt(request.getParameter("term"));
		}
		Calendar currTime=Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		String dateStr = sdf.format(currTime.getTime());
		
		int year=Integer.parseInt(dateStr.substring(0, 4));
		int month=Integer.parseInt(dateStr.substring(5, 7));
		
		if (request.getParameter("year")!=null) {
			year=Integer.parseInt(request.getParameter("year"));
		}
		if (request.getParameter("month")!=null) {
			month=Integer.parseInt(request.getParameter("month"));
		}
		if (year!=0&&month!=0) {
			term=shareService.getSchoolTermByMonth(year, month).getId();
		}
		mav.addObject("term", term);
		mav.addObject("year", year);
		mav.addObject("month", month);
		List<MonthReport> monthReports=timetableAppointmentService.getMonthReports(term, year,month,cid);
		mav.addObject("monthReports",monthReports);	
		//获取当前实验中心
		LabCenter labCenter=labCenterDAO.findLabCenterByPrimaryKey(cid);
		mav.addObject("labCenter", labCenter);
		// 获取登录用户信息
		mav.addObject("user", shareService.getUserDetail());
		// 获取所有的学期
		List<SchoolTerm> schoolTerms = outerApplicationService.getSchoolTermList();
		mav.addObject("schoolTerms", schoolTerms);
		
		
		
		mav.setViewName("reports/teachingReport/monthRegister.jsp");
		return mav;
	}
	
	/************************************************************
	 * @月报报表导出Excel
	 * @作者：贺子龙
	 * @日期：2015-11-16
	 ************************************************************/
	//TODO 这里直接抄的学期报表，需要更改
	@RequestMapping("/report/teachingReport/exportMonthRegister")
	public void exportMonthRegister(@ModelAttribute SchoolTerm schoolTerm, HttpServletRequest request, HttpServletResponse response, @ModelAttribute("selected_labCenter") Integer cid)throws Exception{
		teachingReportService.exportMonthRegister(schoolTerm, cid, request, response);
	}
	

}
