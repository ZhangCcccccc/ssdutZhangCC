/****************************************************************************
 * 命名规范：
 * 本控制层调整必须是按照如下的路径调整：@RequestMapping("/report/sample")，全部小写。
 * 列表信息listxxxx，獲取信息：getxxxx 编辑信息：editxxxx 删除信息：detelexxxx 新增信息 newxxxx
 * 导出信息exportxxxx 保存信息：savexxxx  
 ****************************************************************************/

package net.xidlims.web.report;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.xidlims.constant.CommonConstantInterface; 
import net.xidlims.service.EmptyUtil; 
import net.xidlims.service.common.ShareService;
import net.xidlims.service.device.SchoolDeviceService;
import net.xidlims.service.lab.LabCenterService;
import net.xidlims.service.lab.LabRoomService;
import net.xidlims.service.report.ReportService;
import net.xidlims.dao.LabCenterDAO;
import net.xidlims.dao.LabReservationDAO;
import net.xidlims.dao.LabReservationTimeTableDAO;
import net.xidlims.dao.LabRoomAgentDAO;
import net.xidlims.dao.SchoolAcademyDAO;
import net.xidlims.dao.SchoolTermDAO;
import net.xidlims.dao.TimetableAppointmentDAO;
import net.xidlims.dao.TimetableGroupDAO;
import net.xidlims.domain.CmsSite; 
import net.xidlims.domain.LabCenter;
import net.xidlims.domain.LabReservation;
import net.xidlims.domain.LabReservationTimeTable;
import net.xidlims.domain.LabRoom;
import net.xidlims.domain.LabRoomAgent;
import net.xidlims.domain.LabRoomDeviceReservation;
import net.xidlims.domain.ReportParameter;
import net.xidlims.domain.ReportRate;
import net.xidlims.domain.SchoolAcademy;
import net.xidlims.domain.SchoolCourseStudent;
import net.xidlims.domain.SchoolDevice;
import net.xidlims.domain.SchoolTerm;
import net.xidlims.domain.TimetableAppointment;
import net.xidlims.domain.TimetableGroup;
import net.xidlims.domain.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


/****************************************************************************
 * 功能：绩效报表的展示
 * 绩效报表模块 作者：何立友 时间：2014-08-19
 ****************************************************************************/
@Controller("ReportController")
public class ReportController<JsonResult> {
	 
	@Autowired
	private LabRoomAgentDAO labRoomAgentDAO;
	@Autowired
	private ReportService reportService;
	@Autowired
	private SchoolAcademyDAO schoolAcademyDAO;
	@Autowired
	private ShareService shareService;
	@Autowired
	private SchoolTermDAO schoolTermDAO;
	@Autowired
	private SchoolDeviceService schoolDeviceService;
	@Autowired
	private TimetableGroupDAO timetableGroupDAO;
	@Autowired
	private  LabReservationDAO labReservationDAO;
	@Autowired
	private LabCenterService labCenterService;
	@Autowired
	private LabRoomService labRoomService;
	@Autowired
	private TimetableAppointmentDAO timetableAppointmentDAO;
	@Autowired
	private LabReservationTimeTableDAO labReservationTimeTableDAO;
	@Autowired
	private LabCenterDAO labCenterDAO;
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
	
	/****************************************************************************
	 * 综合报表页面
	 * @author hely
	 * 2014.08.20
	 ****************************************************************************/
	@RequestMapping("/report/reportMain")
	public ModelAndView reportMain(@ModelAttribute LabRoom labRoom){
		ModelAndView mav = new ModelAndView();
		List<SchoolTerm> schoolTerms = new ArrayList<SchoolTerm>();
		
		//查询条件
		if(labRoom.getLabRoomName() != null)
		{
			schoolTerms.addAll(reportService.getSchoolTermsByStr(labRoom.getLabRoomName())); //加入学期查询条件
			mav.addObject("selectTerms", reportService.getSelectTerms(labRoom.getLabRoomName(), labRoom.getLabRoomNumber(), true));
			mav.addObject("otherTerms", reportService.getSelectTerms(labRoom.getLabRoomName(), labRoom.getLabRoomNumber(), false));
		}
		else
		{
			SchoolTerm currentTerm = shareService.getBelongsSchoolTerm(Calendar.getInstance()); //获取当前学期
			schoolTerms.add(currentTerm);
			labRoom.setLabRoomNumber(currentTerm.getYearCode());
			mav.addObject("selectTerms", reportService.getSelectTerms(currentTerm.getId()+"", null, true));
			mav.addObject("otherTerms", reportService.getSelectTerms(currentTerm.getId()+"", currentTerm.getYearCode(), false));
		}
		
		mav.addObject("xAxis", reportService.getAcademyNames());
		mav.addObject("termsMap", reportService.getTermsMap());
		mav.addObject("rates", reportService.getReportRateByTerms(schoolTerms));
		mav.addObject("labRoom", labRoom);
		mav.setViewName("reports/reportMain.jsp");
		
		return mav;
	}
	
	/****************************************************************************
	 * 综合报表页面
	 * @author hely
	 * 2014.08.20
	 ****************************************************************************/
	/*@RequestMapping("/{acaUrl}/report/reportMainAca")
	public ModelAndView reportMainAca(@PathVariable String acaUrl,@ModelAttribute LabRoom labRoom){
		ModelAndView mav = new ModelAndView();
		
		CmsSite site = cmsService.findSiteByPrimaryKey(acaUrl);
		
		String academyNumber = site.getAcademyNum();
		
		SchoolAcademy academy = schoolAcademyDAO.findSchoolAcademyByAcademyNumber(academyNumber);
		
		
		List<SchoolTerm> schoolTerms = new ArrayList<SchoolTerm>();
		
		//查询条件
		if(labRoom.getLabRoomName() != null)
		{
			schoolTerms.addAll(reportService.getSchoolTermsByStr(labRoom.getLabRoomName())); //加入学期查询条件
			mav.addObject("selectTerms", reportService.getSelectTerms(labRoom.getLabRoomName(), labRoom.getLabRoomNumber(), true));
			mav.addObject("otherTerms", reportService.getSelectTerms(labRoom.getLabRoomName(), labRoom.getLabRoomNumber(), false));
		}
		else
		{
			SchoolTerm currentTerm = shareService.getBelongsSchoolTerm(Calendar.getInstance()); //获取当前学期
			schoolTerms.add(currentTerm);
			labRoom.setLabRoomNumber(currentTerm.getYearCode());
			mav.addObject("selectTerms", reportService.getSelectTerms(currentTerm.getId()+"", null, true));
			mav.addObject("otherTerms", reportService.getSelectTerms(currentTerm.getId()+"", currentTerm.getYearCode(), false));
		}
		
		mav.addObject("xAxis", "'"+academy.getAcademyName()+"'");
		mav.addObject("termsMap", reportService.getTermsMap());
		List<ReportRate> reportRates = new ArrayList<ReportRate>();
		reportRates.add(reportService.getReportRateByTermsAcademy(academyNumber,schoolTerms));
		mav.addObject("rates", reportRates);
		mav.addObject("labRoom", labRoom);
		mav.setViewName("reports/reportMainAca.jsp");
		
		return mav;
	}*/
	
	/**
	 * 学院绩效报表
	 * @author hely
	 * 2014.12.11
	 */
	@RequestMapping("/report/reportAcademyMain")
	public ModelAndView reportAcademyMain(@RequestParam String academyNumber, @ModelAttribute LabRoom labRoom){
		ModelAndView mav = new ModelAndView();
		List<SchoolTerm> schoolTerms = new ArrayList<SchoolTerm>();
		
		//查询条件
		if(labRoom.getLabRoomName() != null)
		{
			schoolTerms.addAll(reportService.getSchoolTermsByStr(labRoom.getLabRoomName())); //加入学期查询条件
			mav.addObject("selectTerms", reportService.getSelectTerms(labRoom.getLabRoomName(), labRoom.getLabRoomNumber(), true));
			mav.addObject("otherTerms", reportService.getSelectTerms(labRoom.getLabRoomName(), labRoom.getLabRoomNumber(), false));
		}
		else
		{
			SchoolTerm currentTerm = shareService.getBelongsSchoolTerm(Calendar.getInstance()); //获取当前学期
			schoolTerms.add(currentTerm);
			labRoom.setLabRoomNumber(currentTerm.getYearCode());
			mav.addObject("selectTerms", reportService.getSelectTerms(currentTerm.getId()+"", null, true));
			mav.addObject("otherTerms", reportService.getSelectTerms(currentTerm.getId()+"", currentTerm.getYearCode(), false));
		}
		
		mav.addObject("termsMap", reportService.getTermsMap());
		mav.addObject("labRoom", labRoom);
		mav.addObject("academyRate", reportService.getReportRateByTermsAcademy(academyNumber, schoolTerms));
		mav.addObject("academy", reportService.getAcademyByNumber(academyNumber));
		mav.setViewName("reports/reportAcademyMain.jsp");
		
		return mav;
	}
	
	/**
	 * 查看具体某学院的实验室利用率公式涉及信息
	 * @author hely
	 * 2014.12.11
	 */
	@RequestMapping("/report/reportAcademyLabRate")
	public ModelAndView reportAcademyLabRate(@RequestParam String academyNumber, @RequestParam String terms){
		ModelAndView mav = new ModelAndView();
		List<SchoolTerm> schoolTerms = new ArrayList<SchoolTerm>();
		
		if(terms != null && !"".equals(terms))
		{
			String[] termsArr = terms.split(",");
			for(int i=0;i<termsArr.length;i++)
			{
				schoolTerms.add(schoolTermDAO.findSchoolTermByPrimaryKey(Integer.parseInt(termsArr[i])));
			}
		}
		else
		{
			schoolTerms.add(shareService.getBelongsSchoolTerm(Calendar.getInstance()));  //默认学期
		}
		
		mav.addObject("labArea", reportService.getLabRoomAreaByAcademy(academyNumber));  //学院实验室面积
		mav.addObject("labAvgArea", reportService.getLabAvgArea(academyNumber));  //学院实验室生均面积
		
		int labRoomCapacity = reportService.getLabCapacityByAcademy(academyNumber);  //学院实验室容量
		mav.addObject("labRoomCapacity", labRoomCapacity);  //学院实验室容量
		
		int ratedCourseTime = reportService.getRatedCourseTime(academyNumber);  //实验室额定课时数 
		double ratedCourseTimeTerm = (schoolTerms.size() == 1)? ratedCourseTime/2.0:ratedCourseTime;  //如果是单个学期就乘以2
		mav.addObject("ratedCourseTimeTerm", ratedCourseTimeTerm);  //实验室额定课时数
		
		int studentTimeSum = reportService.getStudentTimeByAcademy(academyNumber, schoolTerms);  //实验室人时数
		mav.addObject("studentTimeSum", studentTimeSum);  //实验室人时数
		
		mav.addObject("labRate", reportService.getAcademyLabRateStr(studentTimeSum, ratedCourseTimeTerm, labRoomCapacity));  //实验室利用率
		mav.setViewName("reports/reportAcademyLabRate.jsp");
		
		return mav;
	}
	
	/**
	 * 查看具体某学院的大型设备机时利用率公式涉及信息
	 * @author hely
	 * 2014.12.11
	 */
	@RequestMapping("/report/reportAcademyLargeDeviceTimeRate")
	public ModelAndView reportAcademyLargeDeviceTimeRate(@RequestParam String academyNumber, @RequestParam String terms){
		ModelAndView mav = new ModelAndView();
		List<SchoolTerm> schoolTerms = new ArrayList<SchoolTerm>();
		
		if(terms != null && !"".equals(terms))
		{
			String[] termsArr = terms.split(",");
			for(int i=0;i<termsArr.length;i++)
			{
				schoolTerms.add(schoolTermDAO.findSchoolTermByPrimaryKey(Integer.parseInt(termsArr[i])));
			}
		}
		else
		{
			schoolTerms.add(shareService.getBelongsSchoolTerm(Calendar.getInstance()));  //默认学期
		}
		
		mav.addObject("largeDeviceSumTime", reportService.getLargeDeviceSumTime(academyNumber, schoolTerms));  //设备机时
		mav.addObject("largeDeviceAmount", reportService.getLargeDeviceAmountByAcademy(academyNumber));  //大型仪器设备数
		
		int largeDeviceAvgTime = reportService.getLargeDeviceAvgTime(academyNumber);
		//如果限定时间为一个学期，则设备年平均机时应该除以2，因为数据库存储的是整个学年的时间
		double largeDeviceAvgTimeTerm = (schoolTerms.size() == 1)? largeDeviceAvgTime/2.0:largeDeviceAvgTime;
		mav.addObject("largeDeviceAvgTimeTerm", largeDeviceAvgTimeTerm);  //设备年平均机时
		
		mav.addObject("largeDeviceTimeRate", reportService.getLargeDeviceTimeRate(academyNumber, schoolTerms));  //设备利用率
	    mav.setViewName("reports/reportAcademyLargeDeviceTimeRate.jsp");
		
		return mav;
	}
	
	/**
	 * 查看具体某学院的大型实验设备仪器台数使用率公式涉及信息
	 * @author hely
	 * 2014.12.11
	 */
	@RequestMapping("/report/reportAcademyLargeDeviceUsedRate")
	public ModelAndView reportAcademyLargeDeviceUsedRate(@RequestParam String academyNumber, @RequestParam String terms){
		ModelAndView mav = new ModelAndView();
		List<SchoolTerm> schoolTerms = new ArrayList<SchoolTerm>();
		
		if(terms != null && !"".equals(terms))
		{
			String[] termsArr = terms.split(",");
			for(int i=0;i<termsArr.length;i++)
			{
				schoolTerms.add(schoolTermDAO.findSchoolTermByPrimaryKey(Integer.parseInt(termsArr[i])));
			}
		}
		else
		{
			schoolTerms.add(shareService.getBelongsSchoolTerm(Calendar.getInstance()));  //默认学期
		}
		
		mav.addObject("largeDeviceAmount", reportService.getLargeDeviceAmountByAcademy(academyNumber));  //大型设备总数
		mav.addObject("largeDeviceUsedAmount", reportService.getLargeDeviceUsedAmount(academyNumber, schoolTerms));  //使用的大型设备数
		mav.addObject("largeDeviceUsedRate", reportService.getLargeDeviceUsedRate(academyNumber, schoolTerms));  //大型设备台数使用率
		mav.setViewName("reports/reportAcademyLargeDeviceUsedRate.jsp");
		
		return mav;
	}
	
	/**
	 * 查看具体某学院的实验室专职人员平均接待师生人时数公式涉及信息
	 * @author hely
	 * 2014.12.11
	 */
	@RequestMapping("/report/reportAcademyLabAdminRate")
	public ModelAndView reportAcademyLabAdminRate(@RequestParam String academyNumber, @RequestParam String terms){
		ModelAndView mav = new ModelAndView();
		List<SchoolTerm> schoolTerms = new ArrayList<SchoolTerm>();
		
		if(terms != null && !"".equals(terms))
		{
			String[] termsArr = terms.split(",");
			for(int i=0;i<termsArr.length;i++)
			{
				schoolTerms.add(schoolTermDAO.findSchoolTermByPrimaryKey(Integer.parseInt(termsArr[i])));
			}
		}
		else
		{
			schoolTerms.add(shareService.getBelongsSchoolTerm(Calendar.getInstance()));  //默认学期
		}
		
		int studentTimeSum = reportService.getStudentTimeByAcademy(academyNumber, schoolTerms); //实验人时数
		int labRoomCapacity = reportService.getLabCapacityByAcademy(academyNumber); //实验室容量
		int ratedCourseTime = reportService.getRatedCourseTime(academyNumber); //实验室额定课时
		int labAdminAmount = reportService.getLabAdminAmount(academyNumber);
		double ratedCourseTimeTerm = (schoolTerms.size() == 1)? ratedCourseTime/2.0:ratedCourseTime;
		
		mav.addObject("studentTimeSum", studentTimeSum);  //实验室人数时
		mav.addObject("labRoomCapacity", labRoomCapacity);  //实验室容量
		mav.addObject("ratedCourseTimeTerm", ratedCourseTimeTerm);  //实验室额定课时数
		mav.addObject("labAdminAmount", labAdminAmount);  //实验室专职管理人员数
		mav.addObject("labAdminRate", reportService.getlabAdminRateSimple(new BigDecimal(studentTimeSum), new BigDecimal(labAdminAmount)));  //专职管理人员人均接待人时数
		mav.setViewName("reports/reportAcademyLabAdminRate.jsp");
		
		return mav;
	}
	
	/**
	 * 查看具体某学院的项目开出率公式涉及信息
	 * @author hely
	 * 2014.12.11
	 */
	@RequestMapping("/report/reportAcademyItemsRate")
	public ModelAndView reportAcademyItemsRate(@RequestParam String academyNumber, @RequestParam String terms){
		ModelAndView mav = new ModelAndView();
		List<SchoolTerm> schoolTerms = new ArrayList<SchoolTerm>();
		
		if(terms != null && !"".equals(terms))
		{
			String[] termsArr = terms.split(",");
			for(int i=0;i<termsArr.length;i++)
			{
				schoolTerms.add(schoolTermDAO.findSchoolTermByPrimaryKey(Integer.parseInt(termsArr[i])));
			}
		}
		else
		{
			schoolTerms.add(shareService.getBelongsSchoolTerm(Calendar.getInstance()));  //默认学期
		}
		
		mav.addObject("realItemsAmount", reportService.getRealItemsAmount(academyNumber, schoolTerms));  //实际开出实验数
		mav.addObject("planItemsAmount", reportService.getPlanItemsAmount(academyNumber, schoolTerms));  //计划开设实验数
		mav.addObject("itemsRate", reportService.getItemsRate(academyNumber, schoolTerms));  //实验项目开出率
		mav.setViewName("reports/reportAcademyItemsRate.jsp");
		
		return mav;
	}
	
	/**
	 * 查看具体某学院的教师参与指导实验比例公式涉及信息
	 * @author hely
	 * 2014.12.12
	 */
	@RequestMapping("/report/reportAcademyTeacherItemRate")
	public ModelAndView reportAcademyTeacherItemRate(@RequestParam String academyNumber, @RequestParam String terms){
		ModelAndView mav = new ModelAndView();
		List<SchoolTerm> schoolTerms = new ArrayList<SchoolTerm>();
		
		if(terms != null && !"".equals(terms))
		{
			String[] termsArr = terms.split(",");
			for(int i=0;i<termsArr.length;i++)
			{
				schoolTerms.add(schoolTermDAO.findSchoolTermByPrimaryKey(Integer.parseInt(termsArr[i])));
			}
		}
		else
		{
			schoolTerms.add(shareService.getBelongsSchoolTerm(Calendar.getInstance()));  //默认学期
		}
		
		mav.addObject("teacherAmount", reportService.getTeacherAmountByAcademy(academyNumber));  //学院教师数量
		mav.addObject("teacherItemAmount", reportService.getTeacherItemAmountByAcademy(academyNumber, schoolTerms));  //参加指导的教师数量
		mav.addObject("teacherItemRate", reportService.getTeacherItemRate(academyNumber, schoolTerms));  //教师参加指导比例
		mav.setViewName("reports/reportAcademyTeacherItemRate.jsp");
		
		return mav;
	}
	
	/**
	 * 查看具体某学院的独立实验课、大型综合性实验课比例公式涉及信息
	 * @author hely
	 * 2014.12.12
	 */
	@RequestMapping("/report/reportAcademyComplexItemRate")
	public ModelAndView reportAcademyComplexItemRate(@RequestParam String academyNumber, @RequestParam String terms){
		ModelAndView mav = new ModelAndView();
		List<SchoolTerm> schoolTerms = new ArrayList<SchoolTerm>();
		
		if(terms != null && !"".equals(terms))
		{
			String[] termsArr = terms.split(",");
			for(int i=0;i<termsArr.length;i++)
			{
				schoolTerms.add(schoolTermDAO.findSchoolTermByPrimaryKey(Integer.parseInt(termsArr[i])));
			}
		}
		else
		{
			schoolTerms.add(shareService.getBelongsSchoolTerm(Calendar.getInstance()));  //默认学期
		}
		
		mav.addObject("complexItemAmount", reportService.getComplexItemAmountByTermAcademy(schoolTerms, academyNumber));  //大型综合性实验课之和
		mav.addObject("majorAmount", reportService.getMajorAmountByAcademy(academyNumber));  //专业数
		mav.addObject("complexItemAcademyAmount", reportService.getComplexItemAmountByTermAcademy(schoolTerms, null));  //全校大型综合性实验课之和
		mav.addObject("complexItemRate", reportService.getComplexItemRate(schoolTerms, academyNumber));  //独立实验课、大型综合性实验课比例
		mav.setViewName("reports/reportAcademyComplexItemRate.jsp");
		
		return mav;
	}
	
	/**
	 * 查看具体某学院的人才培养效率公式涉及信息
	 * @author hely
	 * 2014.12.12
	 */
	@RequestMapping("/report/reportAcademyStudentTrainRate")
	public ModelAndView reportAcademyStudentTrainRate(@RequestParam String academyNumber, @RequestParam String terms){
		ModelAndView mav = new ModelAndView();
		List<SchoolTerm> schoolTerms = new ArrayList<SchoolTerm>();
		
		if(terms != null && !"".equals(terms))
		{
			String[] termsArr = terms.split(",");
			for(int i=0;i<termsArr.length;i++)
			{
				schoolTerms.add(schoolTermDAO.findSchoolTermByPrimaryKey(Integer.parseInt(termsArr[i])));
			}
		}
		else
		{
			schoolTerms.add(shareService.getBelongsSchoolTerm(Calendar.getInstance()));  //默认学期
		}
		
		mav.addObject("undergraduateAmount", reportService.getUndergraduateAmount(academyNumber, schoolTerms));  //当前本科生数
		mav.addObject("graduateAmount", reportService.getGraduateAmount(academyNumber, schoolTerms));  //当前研究生数
		mav.addObject("deviceCost", reportService.getDeviceCostByAcademy(academyNumber));  //现有设备经费额（10万以下）
		mav.addObject("bigDeviceCost", reportService.getBigDeviceCostByAcademy(academyNumber));  //现有设备经费额（10万以上）
		mav.addObject("labRoomArea", reportService.getLabRoomAreaByAcademy(academyNumber));  //实验室使用面积
		mav.addObject("subjectFactor", reportService.getSubjectFactorByAcademy(academyNumber));  //学科系数
		mav.addObject("studentTrainRate", reportService.getStudentTrainRate(academyNumber, schoolTerms));  //人才培养效率
		mav.setViewName("reports/reportAcademyStudentTrainRate.jsp");
		
		return mav;
	}
	
	/****************************************************************************
	 * 计算绩效指标和排名
	 * @author hely
	 * 2014.12.03
	 ****************************************************************************/
	@RequestMapping("/report/computeReport")
	public String computeReport(){
		reportService.storeRates();  //8个绩效指标
		reportService.storeRank();  //绩效排名
		
		return "redirect:/report/reportMain";
	}
	
/*	*//****************************************************************************
	 * 实验室利用率报表
	 * @author hely
	 * 2014.08.20
	 ****************************************************************************//*
	@RequestMapping("/report/reportLabRate")
	public ModelAndView reportLabRate(@ModelAttribute LabRoom labRoom){
		ModelAndView mav = new ModelAndView();
		List<SchoolTerm> schoolTerms = new ArrayList<SchoolTerm>();
		
		//查询条件
		if(labRoom.getLabRoomName() != null)
		{
			schoolTerms.addAll(reportService.getSchoolTermsByStr(labRoom.getLabRoomName())); //加入学期查询条件
			mav.addObject("selectTerms", reportService.getSelectTerms(labRoom.getLabRoomName(), labRoom.getLabRoomNumber(), true));
			mav.addObject("otherTerms", reportService.getSelectTerms(labRoom.getLabRoomName(), labRoom.getLabRoomNumber(), false));
		}
		else
		{
			SchoolTerm currentTerm = shareService.getBelongsSchoolTerm(Calendar.getInstance()); //获取当前学期
			schoolTerms.add(currentTerm);
			labRoom.setLabRoomNumber(currentTerm.getYearCode());
			mav.addObject("selectTerms", reportService.getSelectTerms(currentTerm.getId()+"", null, true));
			mav.addObject("otherTerms", reportService.getSelectTerms(currentTerm.getId()+"", currentTerm.getYearCode(), false));
		}
		
		mav.addObject("xAxis", reportService.getAcademyNames());
		mav.addObject("termsMap", reportService.getTermsMap());
		mav.addObject("labRates", reportService.getLabRateDetailInfo(schoolTerms));
		mav.setViewName("reports/reportLabRate.jsp");
		
		return mav;
	}*/
	
	 /****************************************************************************
		 * Description: 系统报表{实验室利用率报表--中心}
		 * @author 贺子龙
		 * @param termBack--从实验室利用率列表返回
		 * @date 2016-10-10
		 ****************************************************************************/
		@SuppressWarnings("unchecked")
		@RequestMapping("/dataReport/reportLabRate")
		public ModelAndView reportLabRate(@RequestParam Integer termBack, HttpServletRequest request){
			ModelAndView mav = new ModelAndView();
			// 当前学期
			int termId = shareService.getBelongsSchoolTerm(Calendar.getInstance()).getId();
			if (!EmptyUtil.isObjectEmpty(termBack)&&termBack!=-1) {
				termId = termBack;
			}
			// 选择
			if (!EmptyUtil.isStringEmpty(request.getParameter("term"))) {
				termId = Integer.parseInt(request.getParameter("term"));
			}
			List<SchoolTerm> schoolTerms = shareService.findAllSchoolTerms();
			mav.addObject("selectedTermId", termId);
			mav.addObject("selectTerms", schoolTerms);
	 		// 查询记录
	 		List<Object[]> labRates = reportService.getLabUseRate(request);
			String centerId = "";
			String rate ="";
			// 横坐标：中心名称
			String xAxis = "";
			if(labRates != null && labRates.size() != 0){
				for(Object[] o:labRates){
					centerId += o[0].toString()+",";
					rate += ""+o[5]+",";
					xAxis += "'"+o[1]+"',";
				}
			}
	 		// 所有中心
	 		List<LabCenter> centerList = labCenterService.findAllLabCenterByLabCenter(new LabCenter());

			List<LabCenter> centers = new LinkedList<LabCenter>();
			if(centerList != null && centerList.size() != 0){
				for(LabCenter l:centerList){
					if(centerId.contains(l.getId().toString()) == false){
						centers.add(l);
					}
				}
			}
			if (centers!=null && centers.size()>0) {
				for (LabCenter labCenter : centers) {
	 				xAxis+="'"+labCenter.getCenterName()+"',";
					rate += "0,";
					Object[] o = new Object[6];
					o[0] = labCenter.getId();
					o[1] = labCenter.getCenterName();
					o[3] = 0;	
					o[4] = 0;
					o[5] = 0;
					labRates.add(o);
	 			}
	 		}
	 		mav.addObject("xAxis", xAxis);
			mav.addObject("rate", rate);
	 		mav.addObject("labRates", labRates);
	 		mav.setViewName("reports/reportLabRateNew.jsp");
	 		return mav;

		}
	/****************************************************************************
	 * 实验室利用率--实验室面积信息
	 * @author hely
	 * 2014.08.20
	 ****************************************************************************/
	@RequestMapping("/report/reportLabArea")
	public ModelAndView reportLabArea(String academyNumber){
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("labRooms", reportService.getLabRoomByAcademy(academyNumber));
		mav.setViewName("reports/reportLabArea.jsp");
		
		return mav;
	}
	
	/****************************************************************************
	 * 实验室利用率--实验室容量信息
	 * @author hely
	 * 2014.08.20
	 ****************************************************************************/
	@RequestMapping("/report/reportLabCapacity")
	public ModelAndView reportLabCapacity(String academyNumber){
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("labRooms", reportService.getLabRoomByAcademy(academyNumber));
		mav.setViewName("reports/reportLabCapacity.jsp");
		
		return mav;
	}

	/****************************************************************************
	 * 实验室利用率--实验室人时数
	 * @author hely
	 * 2014.08.27
	 ****************************************************************************/
	@RequestMapping("/report/reportStudentTime")
	public ModelAndView reportStudentTime(@RequestParam int currpage, String academyNumber, String terms){
		ModelAndView mav = new ModelAndView();
		List<SchoolTerm> schoolTerms = new ArrayList<SchoolTerm>();
		
		if(terms != null && !"".equals(terms))
		{
			String[] termsArr = terms.split(",");
			for(int i=0;i<termsArr.length;i++)
			{
				schoolTerms.add(schoolTermDAO.findSchoolTermById(Integer.parseInt(termsArr[i])));
			}
		}
		else
		{
			schoolTerms.add(shareService.getBelongsSchoolTerm(Calendar.getInstance()));  //默认学期
		}
		
		int pageSize = CommonConstantInterface.INT_PAGESIZE; // 设置分页变量并赋值为20
		int totalRecords = reportService.getTimetableAppointmentsByAcademy(academyNumber, schoolTerms, 0, -1).size();
		Map<String,Integer> pageModel = shareService.getPage(pageSize, currpage,totalRecords);	
		
		mav.addObject("pageModel",pageModel);
		mav.addObject("page", currpage);
		mav.addObject("totalRecords", totalRecords);
		mav.addObject("academyNumber", academyNumber);
		mav.addObject("academyName", reportService.getAcademyNameByNumber(academyNumber));
		mav.addObject("studentTime", reportService.getStudentTimeByAcademy(academyNumber, schoolTerms));
		mav.addObject("timetableAppointments", reportService.getTimetableAppointmentsByAcademy(academyNumber, schoolTerms, currpage-1, pageSize));
		mav.addObject("timetableAppointmentAll", reportService.getTimetableAppointmentsByAcademy(academyNumber, schoolTerms, 0, -1));
		
		mav.setViewName("reports/reportStudentTime.jsp");
		return mav;
	}
	
	/****************************************************************************
	 * 大型设备平均利用率报表
	 * @author hely
	 * 2014.08.20
	 ****************************************************************************/
	@RequestMapping("/report/reportLargeDeviceTimeRate")
	public ModelAndView reportLargeDeviceTimeRate(@ModelAttribute LabRoom labRoom){
		ModelAndView mav = new ModelAndView();
		List<SchoolTerm> schoolTerms = new ArrayList<SchoolTerm>();
		
		//查询条件
		if(labRoom.getLabRoomName() != null)
		{
			schoolTerms.addAll(reportService.getSchoolTermsByStr(labRoom.getLabRoomName())); //加入学期查询条件
			mav.addObject("selectTerms", reportService.getSelectTerms(labRoom.getLabRoomName(), labRoom.getLabRoomNumber(), true));
			mav.addObject("otherTerms", reportService.getSelectTerms(labRoom.getLabRoomName(), labRoom.getLabRoomNumber(), false));
		}
		else
		{
			SchoolTerm currentTerm = shareService.getBelongsSchoolTerm(Calendar.getInstance()); //获取当前学期
			schoolTerms.add(currentTerm);
			labRoom.setLabRoomNumber(currentTerm.getYearCode());
			mav.addObject("selectTerms", reportService.getSelectTerms(currentTerm.getId()+"", null, true));
			mav.addObject("otherTerms", reportService.getSelectTerms(currentTerm.getId()+"", currentTerm.getYearCode(), false));
		}
		
		mav.addObject("xAxis", reportService.getAcademyNames());
		mav.addObject("termsMap", reportService.getTermsMap());
		mav.addObject("largeDeviceTimeRates", reportService.getLargeDeviceTimeRateInfo(schoolTerms));
		mav.setViewName("reports/reportLargeDeviceTimeRate.jsp");
		
		return mav;
	}
	
	/****************************************************************************
	 * 大型试验设备仪器台数使用率报表
	 * @author hely
	 * 2014.08.20
	 ****************************************************************************/
	@RequestMapping("/report/reportLargeDeviceUsedRate")
	public ModelAndView reportLargeDeviceUsedRate(@ModelAttribute LabRoom labRoom){
        ModelAndView mav = new ModelAndView();
		List<SchoolTerm> schoolTerms = new ArrayList<SchoolTerm>();
		
		if(labRoom.getLabRoomName() != null)
		{
			schoolTerms.addAll(reportService.getSchoolTermsByStr(labRoom.getLabRoomName())); //加入学期查询条件
			mav.addObject("selectTerms", reportService.getSelectTerms(labRoom.getLabRoomName(), labRoom.getLabRoomNumber(), true));
			mav.addObject("otherTerms", reportService.getSelectTerms(labRoom.getLabRoomName(), labRoom.getLabRoomNumber(), false));
		}
		else
		{
			SchoolTerm currentTerm = shareService.getBelongsSchoolTerm(Calendar.getInstance()); //获取当前学期
			schoolTerms.add(currentTerm);
			labRoom.setLabRoomNumber(currentTerm.getYearCode());
			mav.addObject("selectTerms", reportService.getSelectTerms(currentTerm.getId()+"", null, true));
			mav.addObject("otherTerms", reportService.getSelectTerms(currentTerm.getId()+"", currentTerm.getYearCode(), false));
		}
		mav.addObject("xAxis", reportService.getAcademyNames());
		mav.addObject("termsMap", reportService.getTermsMap());
        mav.addObject("largeDeviceUsedRate", reportService.getLargeDeviceUsedRateInfo(schoolTerms));
		mav.setViewName("reports/reportLargeDeviceUsedRate.jsp");
		
		return mav;
	}
	
	/****************************************************************************
	 * 指定学院的所有大型仪器设备
	 * @param academyNumber 学院编号
	 * @param request Servlet请求用于获取表单数据（term）
	 * @author hely
	 * 2014.09.10
	 ****************************************************************************/
	@RequestMapping("/report/reportLargeDeviceAcademy")
	public ModelAndView reportLargeDeviceAcademy(HttpServletRequest request, String academyNumber){
		ModelAndView mav = new ModelAndView();
		
		// 将学院记录映射到前台
		mav.addObject("academy", reportService.getAcademyByNumber(academyNumber));
		
		int term = shareService.getBelongsSchoolTerm(Calendar.getInstance()).getId();
		
		
		if (!EmptyUtil.isStringEmpty(request.getParameter("term"))) {
			term = Integer.parseInt(request.getParameter("term"));
		}
		
		String termString = "("+term+")";
		// 找到所有该学院下的设备呈现到jsp页面
		mav.addObject("largeDevices", reportService.getLargeDeviceByAcademy(academyNumber, termString));
		
		// 筛选条件
		// 学期
		List<SchoolTerm> schoolTerms = shareService.findAllSchoolTerms();
		mav.addObject("schoolTerms", schoolTerms);
		// 当前选定的学期
		mav.addObject("term", term);
		
		mav.setViewName("reports/reportLargeDeviceAcademy.jsp");
		
		return mav;
	}
	
	/****************************************************************************
	 * 指定学院的实验设备列表(低于10万)
	 * @param academyNumber 学院编号
	 * @author hely
	 * 2014.09.26
	 ****************************************************************************/
	@RequestMapping("/report/reportDeviceAcademy")
	public ModelAndView reportDeviceAcademy(String academyNumber){
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("academy", reportService.getAcademyByNumber(academyNumber));
		mav.addObject("largeDevices", reportService.getDeviceByAcademy(academyNumber));
		mav.setViewName("reports/reportLargeDeviceAcademy.jsp");
		
		return mav;
	}
	
	/****************************************************************************
	 * 某学院在某一时间段内使用的大型设备
	 * @param academyNumber 学院编号
	 * @author hely
	 * 2014.09.10
	 ****************************************************************************/
	@RequestMapping("/report/reportLargeDeviceUsedAcademy")
	public ModelAndView reportLargeDeviceUsedAcademy(String academyNumber){
		ModelAndView mav = new ModelAndView();
		
		SchoolTerm currentTerms = shareService.getBelongsSchoolTerm(Calendar.getInstance());
		List<SchoolTerm> schoolTerms = new ArrayList<SchoolTerm>();
		schoolTerms.add(currentTerms);
		
		mav.addObject("academy", reportService.getAcademyByNumber(academyNumber));
		mav.addObject("largeDevices", reportService.getLargeDeviceUsed(academyNumber, schoolTerms));
		mav.setViewName("reports/reportLargeDeviceAcademy.jsp");
		
		return mav;
	}
	
	/****************************************************************************
	 * 某学院在某一时间段内未使用的大型设备
	 * @param academyNumber 学院编号
	 * @author hely
	 * 2014.09.10
	 ****************************************************************************/
	@RequestMapping("/report/reportLargeDeviceNoUsedAcademy")
	public ModelAndView reportLargeDeviceNoUsedAcademy(String academyNumber){
		ModelAndView mav = new ModelAndView();
		
		SchoolTerm currentTerms = shareService.getBelongsSchoolTerm(Calendar.getInstance());
		List<SchoolTerm> schoolTerms = new ArrayList<SchoolTerm>();
		schoolTerms.add(currentTerms);
		
		List<SchoolDevice> allSchoolDevice = reportService.getLargeDeviceByAcademy(academyNumber, null); //该学院所有大型设备
		List<SchoolDevice> usedSchoolDevice = reportService.getLargeDeviceUsed(academyNumber, schoolTerms); //该学院所选学期内使用的大型设备
		allSchoolDevice.removeAll(usedSchoolDevice);
		
		mav.addObject("academy", reportService.getAcademyByNumber(academyNumber));
		mav.addObject("largeDevices", allSchoolDevice);
		mav.setViewName("reports/reportLargeDeviceAcademy.jsp");
		
		return mav;
	}
	
	/****************************************************************************
	 * 实验室专职人员平均接待
	 * @author hely
	 * 2014.08.20
	 ****************************************************************************/
	@RequestMapping("/report/reportLabAdminRate")
	public ModelAndView reporLabAdminRate(@ModelAttribute LabRoom labRoom){
        ModelAndView mav = new ModelAndView();
        List<SchoolTerm> schoolTerms = new ArrayList<SchoolTerm>();
		
		if(labRoom.getLabRoomName() != null)
		{
			schoolTerms.addAll(reportService.getSchoolTermsByStr(labRoom.getLabRoomName())); //加入学期查询条件
			mav.addObject("selectTerms", reportService.getSelectTerms(labRoom.getLabRoomName(), labRoom.getLabRoomNumber(), true));
			mav.addObject("otherTerms", reportService.getSelectTerms(labRoom.getLabRoomName(), labRoom.getLabRoomNumber(), false));
		}
		else
		{
			SchoolTerm currentTerm = shareService.getBelongsSchoolTerm(Calendar.getInstance()); //获取当前学期
			schoolTerms.add(currentTerm);
			labRoom.setLabRoomNumber(currentTerm.getYearCode());
			mav.addObject("selectTerms", reportService.getSelectTerms(currentTerm.getId()+"", null, true));
			mav.addObject("otherTerms", reportService.getSelectTerms(currentTerm.getId()+"", currentTerm.getYearCode(), false));
		}
		
		mav.addObject("xAxis", reportService.getAcademyNames());
		mav.addObject("termsMap", reportService.getTermsMap());
		mav.addObject("labAdminRates", reportService.getLabAdminRateInfo(schoolTerms));
		mav.setViewName("reports/reportLabAdminRate.jsp");
		
		return mav;
	}
	
	/****************************************************************************
	 *  实验项目开出率报表
	 * @author hely
	 * 2014.08.20
	 ****************************************************************************/
	@RequestMapping("/report/reportItemsRate")
	public ModelAndView reporItemsRate(@ModelAttribute LabRoom labRoom){
        ModelAndView mav = new ModelAndView();
        List<SchoolTerm> schoolTerms = new ArrayList<SchoolTerm>();
		
		if(labRoom.getLabRoomName() != null)
		{
			schoolTerms.addAll(reportService.getSchoolTermsByStr(labRoom.getLabRoomName())); //加入学期查询条件
			mav.addObject("selectTerms", reportService.getSelectTerms(labRoom.getLabRoomName(), labRoom.getLabRoomNumber(), true));
			mav.addObject("otherTerms", reportService.getSelectTerms(labRoom.getLabRoomName(), labRoom.getLabRoomNumber(), false));
		}
		else
		{
			SchoolTerm currentTerm = shareService.getBelongsSchoolTerm(Calendar.getInstance()); //获取当前学期
			schoolTerms.add(currentTerm);
			labRoom.setLabRoomNumber(currentTerm.getYearCode());
			mav.addObject("selectTerms", reportService.getSelectTerms(currentTerm.getId()+"", null, true));
			mav.addObject("otherTerms", reportService.getSelectTerms(currentTerm.getId()+"", currentTerm.getYearCode(), false));
		}
		
		mav.addObject("xAxis", reportService.getAcademyNames());
		mav.addObject("termsMap", reportService.getTermsMap());
		mav.addObject("itemsRates", reportService.getItemsRateInfo(schoolTerms));
		mav.setViewName("reports/reportItemsRate.jsp");
		
		return mav;
	}
	
	@RequestMapping("/report/reportItems")
	public ModelAndView reportItems(@RequestParam int currpage, String academyNumber, String terms){
		ModelAndView mav = new ModelAndView();
		List<SchoolTerm> schoolTerms = new ArrayList<SchoolTerm>();
		
		if(terms !=null && !"".equals(terms))
		{
			String[] termsArr = terms.split(",");
			for(int i=0;i<termsArr.length;i++)
			{
				schoolTerms.add(schoolTermDAO.findSchoolTermById(Integer.parseInt(termsArr[i])));
			}
		}
		else
		{
			schoolTerms.add(shareService.getBelongsSchoolTerm(Calendar.getInstance()));  //当前学期
		}
		int pageSize = CommonConstantInterface.INT_PAGESIZE;
		int totalRecords = reportService.getRealItemsAmount(academyNumber, schoolTerms);
		Map<String, Integer> pageModel = shareService.getPage(pageSize, currpage, totalRecords);
		
		mav.addObject("pageModel", pageModel);
		mav.addObject("totalRecords", totalRecords);
		mav.addObject("items", reportService.getRealItems(academyNumber, schoolTerms, currpage-1, pageSize));
		mav.setViewName("/reports/reportItems.jsp");
		return mav;
	}
	
	/****************************************************************************
	 *  教师参加指导实验比例报表
	 * @author hely
	 * 2014.08.20
	 ****************************************************************************/
	@RequestMapping("/report/reportTeacherItemRate")
	public ModelAndView reportTeacherItemRate(@ModelAttribute LabRoom labRoom){
        ModelAndView mav = new ModelAndView();
        List<SchoolTerm> schoolTerms = new ArrayList<SchoolTerm>();
		
		//查询条件 
		if(labRoom.getLabRoomName() != null)
		{
			schoolTerms.addAll(reportService.getSchoolTermsByStr(labRoom.getLabRoomName())); //加入学期查询条件
			mav.addObject("selectTerms", reportService.getSelectTerms(labRoom.getLabRoomName(), labRoom.getLabRoomNumber(), true));
			mav.addObject("otherTerms", reportService.getSelectTerms(labRoom.getLabRoomName(), labRoom.getLabRoomNumber(), false));
		}
		else
		{
			SchoolTerm currentTerm = shareService.getBelongsSchoolTerm(Calendar.getInstance()); //获取当前学期
			schoolTerms.add(currentTerm);
			labRoom.setLabRoomNumber(currentTerm.getYearCode());
			mav.addObject("selectTerms", reportService.getSelectTerms(currentTerm.getId()+"", null, true));
			mav.addObject("otherTerms", reportService.getSelectTerms(currentTerm.getId()+"", currentTerm.getYearCode(), false));
		}
		
		mav.addObject("xAxis", reportService.getAcademyNames());  //柱状图x轴
		mav.addObject("termsMap", reportService.getTermsMap());
		mav.addObject("teacherItemRates", reportService.getTeacherItemRateInfo(schoolTerms));  //报表详细数据信息
		mav.setViewName("reports/reportTeacherItemRate.jsp");
		
		return mav;
	}
	
	/****************************************************************************
	 *参与指导的教师列表
	 * @author hely
	 * 2014.08.20
	 ****************************************************************************/
	@RequestMapping("/report/reportTeacherItemList")
	public ModelAndView reportTeacherItemList(@RequestParam int currpage, String academyNumber, String terms){
		ModelAndView mav = new ModelAndView();
		List<SchoolTerm> schoolTerms = new ArrayList<SchoolTerm>();
		
		if(terms != null && !"".equals(terms))  //terms由学期id组成，形式如 1,2
		{
			String[] termIds = terms.split(",");
			for(int i=0;i<termIds.length;i++)
			{
				schoolTerms.add(schoolTermDAO.findSchoolTermById(Integer.parseInt(termIds[i])));
			}
		}
		else  //默认学期的数据
		{
			schoolTerms.add(shareService.getBelongsSchoolTerm(Calendar.getInstance()));  //默认学期
		}
		
		//int pageSize = CommonConstantInterface.INT_PAGESIZE;  // 设置分页变量并赋值为20
		//int totalRecords = reportService.getTeacherItemAmountByAcademy(academyNumber, schoolTerms);  //记录总数
		//Map<String, Integer> pageModel = shareService.getPage(pageSize, currpage, totalRecords);
		mav.addObject("teachers", reportService.getTeacherItemByAcademy(academyNumber, schoolTerms));
		//mav.addObject("totalRecords", totalRecords);
		//mav.addObject("pageModel", pageModel);
		mav.addObject("page", currpage);
		mav.setViewName("reports/reportTeacherItemList.jsp");
		return mav;
	}
	
	/****************************************************************************
	 *  大型综合性实验比例报表
	 * @author hely
	 * 2014.08.20
	 ****************************************************************************/
	@RequestMapping("/report/reportComplexItemRate")
	public ModelAndView reportComplexItemRate(@ModelAttribute LabRoom labRoom){
        ModelAndView mav = new ModelAndView();
        List<SchoolTerm> schoolTerms = new ArrayList<SchoolTerm>();
        
        //查询条件 
  		if(labRoom.getLabRoomName() != null)
  		{
  			schoolTerms.addAll(reportService.getSchoolTermsByStr(labRoom.getLabRoomName())); //加入学期查询条件
  			mav.addObject("selectTerms", reportService.getSelectTerms(labRoom.getLabRoomName(), labRoom.getLabRoomNumber(), true));
  			mav.addObject("otherTerms", reportService.getSelectTerms(labRoom.getLabRoomName(), labRoom.getLabRoomNumber(), false));
  		}
  		else
  		{
  			SchoolTerm currentTerm = shareService.getBelongsSchoolTerm(Calendar.getInstance()); //获取当前学期
  			schoolTerms.add(currentTerm);
  			labRoom.setLabRoomNumber(currentTerm.getYearCode());
			mav.addObject("selectTerms", reportService.getSelectTerms(currentTerm.getId()+"", null, true));
			mav.addObject("otherTerms", reportService.getSelectTerms(currentTerm.getId()+"", currentTerm.getYearCode(), false));
  		}
		
        mav.addObject("xAxis", reportService.getAcademyNames());  //柱状图x轴
		mav.addObject("termsMap", reportService.getTermsMap());
		mav.addObject("complexItemRates", reportService.getComplexItemRateInfo(schoolTerms));
		mav.setViewName("reports/reportComplexItemRate.jsp");
		
		return mav;
	}
	
	/****************************************************************************
	 * 指定学院、学期的大型实验项目列表
	 * @author hely
	 * 2014.09.25
	 ****************************************************************************/
	@RequestMapping("/report/reportComplexItems")
	public ModelAndView reportComplexItems(@RequestParam int currpage, String academyNumber, String terms){
		ModelAndView mav = new ModelAndView();
		List<SchoolTerm> schoolTerms = new ArrayList<SchoolTerm>();
		
		if(terms !=null && !"".equals(terms))
		{
			String[] termsArr = terms.split(",");
			for(int i=0;i<termsArr.length;i++)
			{
				schoolTerms.add(schoolTermDAO.findSchoolTermById(Integer.parseInt(termsArr[i])));
			}
		}
		else
		{
			schoolTerms.add(shareService.getBelongsSchoolTerm(Calendar.getInstance()));  //当前学期
		}
		int pageSize = CommonConstantInterface.INT_PAGESIZE;  //每页记录数
		int totalRecords = reportService.getComplexItemAmountByTermAcademy(schoolTerms, academyNumber);  //总记录数
		Map<String, Integer> pageModel = shareService.getPage(pageSize, currpage, totalRecords);
		
		mav.addObject("pageModel", pageModel);
		mav.addObject("totalRecords", totalRecords);
		mav.addObject("items", reportService.getComplexItemByTermAcademy(schoolTerms, academyNumber, currpage-1, pageSize));
		mav.setViewName("reports/reportComplexItems.jsp");
		return mav;
	}
	
	/****************************************************************************
	 *  人才培养率报表
	 * @author hely
	 * 2014.08.20
	 ****************************************************************************/
	@RequestMapping("/report/reportStudentTrainRate")
	public ModelAndView reportStudentTrainRate(@ModelAttribute LabRoom labRoom){
        ModelAndView mav = new ModelAndView();
        List<SchoolTerm> schoolTerms = new ArrayList<SchoolTerm>();
        
        //查询条件 
  		if(labRoom.getLabRoomName() != null)
  		{
  			schoolTerms.addAll(reportService.getSchoolTermsByStr(labRoom.getLabRoomName())); //加入学期查询条件
  			mav.addObject("selectTerms", reportService.getSelectTerms(labRoom.getLabRoomName(), labRoom.getLabRoomNumber(), true));
  			mav.addObject("otherTerms", reportService.getSelectTerms(labRoom.getLabRoomName(), labRoom.getLabRoomNumber(), false));
  		}
  		else
  		{
  			SchoolTerm currentTerm = shareService.getBelongsSchoolTerm(Calendar.getInstance()); //获取当前学期
  			schoolTerms.add(currentTerm);
  			labRoom.setLabRoomNumber(currentTerm.getYearCode());
			mav.addObject("selectTerms", reportService.getSelectTerms(currentTerm.getId()+"", null, true));
			mav.addObject("otherTerms", reportService.getSelectTerms(currentTerm.getId()+"", currentTerm.getYearCode(), false));
  		}
		
        mav.addObject("xAxis", reportService.getAcademyNames());  //柱状图x轴
		mav.addObject("termsMap", reportService.getTermsMap());
		mav.addObject("studentTrainRates", reportService.getStudentTrainRateInfo(schoolTerms));
		mav.setViewName("reports/reportStudentTrainRate.jsp");
		
		return mav;
	}
	
	/****************************************************************************
	 * 报表参数列表
	 * @author hely
	 * 2014.08.21
	 ****************************************************************************/
	@RequestMapping("/report/reportParameterList")
	public ModelAndView reportParameterList(){
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("reportParameters", reportService.getAllReportParameter());
		mav.setViewName("reports/reportParameterList.jsp");
		
		return mav;
	}
	
	/****************************************************************************
	 * 新建报表参数
	 * @author hely
	 * 2014.08.20
	 ****************************************************************************/
	@RequestMapping("/report/newReportParameter")
	public ModelAndView newReportParameter(){
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("reportParameter", new ReportParameter());
		Set<SchoolAcademy> academies = schoolAcademyDAO.findAllSchoolAcademys();  //学院
		List<SchoolTerm> schoolTerms = reportService.getAllSchoolTerms();  //学期
		mav.addObject("academies", academies);
		mav.addObject("schoolTerms", schoolTerms);
		mav.setViewName("reports/editReportParameter.jsp");
		
		return mav;
	}
	
	/****************************************************************************
	 * 修改报表参数
	 * @author hely
	 * 2014.08.22
	 ****************************************************************************/
	@RequestMapping("/report/editReportParameter")
	public ModelAndView editReportParameter(@RequestParam Integer id){
		ModelAndView mav = new ModelAndView();
		
		ReportParameter reportParameter = reportService.getReportParameterById(id);
		Set<SchoolAcademy> academies=schoolAcademyDAO.findAllSchoolAcademys();  //学院
		List<SchoolTerm> schoolTerms = reportService.getAllSchoolTerms();  //学期
		
		mav.addObject("reportParameter", reportParameter);
		mav.addObject("academies", academies);
		mav.addObject("schoolTerms", schoolTerms);
		mav.setViewName("reports/editReportParameter.jsp");
		
		return mav;
	}
	
	/****************************************************************************
	 *  保存报表参数
	 * @author hely
	 * 2014.08.22
	 ****************************************************************************/
	@RequestMapping("/report/saveReportParameter")
	public ModelAndView saveReportParameter(@ModelAttribute ReportParameter reportParameter){
		ModelAndView mav = new ModelAndView();
		
		reportService.saveReportParameter(reportParameter);
		
		mav.setViewName("redirect:/report/reportParameterList");
		return mav;
	}
	
	/**
	 * 根据id删除报表参数
	 * @param id  报表参数
	 * @author hely
	 * 2014.08.22
	 */
	@RequestMapping("/report/deleteReportParameter")
	public ModelAndView deleteReportParameter(@RequestParam Integer id){
		ModelAndView mav = new ModelAndView();
		
		reportService.deleteReportParameterById(id);
		
		mav.setViewName("redirect:/report/reportParameterList");
		return mav;
	}
	
	/**
	 * 根据year_code获取学期
	 * @param yearCode
	 * @author hely
	 * 2014.09.10
	 */
	@RequestMapping("/report/getTermsByYearCode")
	public @ResponseBody Map<String, String> getTermsByYearCode(@RequestParam String yearCode){
		Map<String, String> termsMap = new LinkedHashMap<String, String>();
		termsMap.put("terms", reportService.getTermsByYearCode(yearCode));
		
		return termsMap;
	}
	
	
	/****************************************************************************
	 * description:实验室使用情况报表
	 * @author:郑昕茹
	 * @date:2017-05-10
	 ****************************************************************************/
	@RequestMapping("/report/listLabRoomUsage")
	public ModelAndView listLabRoomUsage(HttpServletRequest request, @RequestParam int page) {
		ModelAndView mav = new ModelAndView();
		int pageSize = 30;
		// 学期
		List<SchoolTerm> terms = shareService.findAllSchoolTerms();
		mav.addObject("terms", terms);
		// 查询记录
		List<Object[]> listLabRoomUses = reportService.getListLabRoomUses(request, page, pageSize);
		mav.addObject("listLabRoomUses",listLabRoomUses);
		
		int totalRecords = reportService.getListLabRoomUses(request, 1, -1).size();
	
		//mav.addObject("courses", labRoomDeviceService.getAllCoursesInAppointment(request));
		
		if(request.getParameter("termId") != null && !request.getParameter("termId").equals("")){
			mav.addObject("termId", request.getParameter("termId"));
		}
		if(request.getParameter("centerId") != null && !request.getParameter("centerId").equals("")){
			mav.addObject("centerId", request.getParameter("centerId"));
		}
		if(request.getParameter("labId") != null && !request.getParameter("labId").equals("")){
			mav.addObject("labId", request.getParameter("labId"));
		}
		if(request.getParameter("useType") != null && !request.getParameter("useType").equals("")){
			mav.addObject("useType", request.getParameter("useType"));
		}
		mav.addObject("centers",labCenterService.findAllLabCenterByQuery(new LabCenter(), 1, -1));
		mav.addObject("rooms", labRoomService.findAllLabRoomByQuery(1, -1, new LabRoom(),9,-1));
		
		// 分页信息
		Map<String, Integer> pageModel = shareService.getPage(page, pageSize, totalRecords);
		mav.addObject("pageModel", pageModel);
		mav.addObject("pageSize",pageSize);
		mav.addObject("page", page);
		mav.addObject("totalRecords",totalRecords);
		mav.setViewName("/reports/listLabRoomUses.jsp");
		return mav;
	}
	
	
	/****************************************************************************
	 * @throws Exception 
	 * @description：实验室日志报表导出
	 * @author：郑昕茹
	 * @date：2017-05-10
	 ****************************************************************************/
	@RequestMapping("/report/exportLabRoomUses")
	public void exportLabRoomUses(HttpServletRequest request,HttpServletResponse response) throws Exception {
		reportService.exportLabRoomUses(request, response);
	
	}
	
	
	/****************************************************************************
	 * description:查看学生名单
	 * @author:郑昕茹
	 * @date:2017-05-10
	 ****************************************************************************/
	@RequestMapping("/report/openSearchStudent")
	public ModelAndView openSearchStudent(HttpServletRequest request,
			@RequestParam Integer id, @RequestParam Integer type) {
		ModelAndView mav = new ModelAndView();
		if(type == 1){
			TimetableGroup timetableGroup = timetableGroupDAO.findTimetableGroupById(id);
			mav.addObject("studentMap",timetableGroup.getTimetableGroupStudentses());
			mav.addObject("type", 1);
		}
		else{
			LabReservation reservation = labReservationDAO.findLabReservationById(id);
			mav.addObject("studentMap", reservation.getLabReservationTimeTableStudents());
			mav.addObject("type", 2);
		}
		mav.setViewName("reports/listGroupStudent.jsp");
		return mav;

	}
	

	
	/*********************************************************************************
	 * description： 批量设置排课教师
	 * author：郑昕茹
	 * date：2017-04-14
	 *********************************************************************************/
	@RequestMapping("/setUseCondition")
	public @ResponseBody
	String setUseCondition(Integer type, String useCondition, Integer id) {
		if(type == 1){
			TimetableAppointment timeAppointment = timetableAppointmentDAO.findTimetableAppointmentById(id);
			timeAppointment.setUseCondition(useCondition);
			timetableAppointmentDAO.store(timeAppointment);
		}
		else{
			LabReservationTimeTable timetable = labReservationTimeTableDAO.findLabReservationTimeTableById(id);
			timetable.setUseCondition(useCondition);
			labReservationTimeTableDAO.store(timetable);
		}
		return "success";
	}
	
	/****************************************************************************
	 * Description: 系统报表{实验室利用率报表--实验室}
	 * @author 贺子龙
	 * @date 2016-10-10
	 ****************************************************************************/
	@SuppressWarnings("unchecked")
	@RequestMapping("/dataReport/reportLabRateRoom")
	public ModelAndView reportLabRateRoom(@RequestParam Integer centerId, Integer termId){
		ModelAndView mav = new ModelAndView();
		// 查询记录
		List<Object[]> labRates = reportService.getLabUseRateRoom(centerId, termId);
		mav.addObject("labRates", labRates);
		// 中心傳遞
		mav.addObject("centerId", centerId);
		// 学期传递
		mav.addObject("termId", termId);
		mav.setViewName("reports/reportLabRateRoom.jsp");
		return mav;
	}
	
	/*****************************************************************************************
	 * @description：實驗室使用率報表導出--中心
	 * @param：termId 學期主鍵
	 * @author：陳樂為
	 * @date：2016-10-24
	 *****************************************************************************************/
	@SuppressWarnings("unchecked")
	@RequestMapping("/dataReport/exportReportLabRate")
	public void exportReportLabRate(HttpServletRequest request, HttpServletResponse response)throws Exception{
		ModelAndView mav = new ModelAndView();
		// 查询记录
		List<Object[]> labRates = reportService.getLabUseRate(request);
		mav.addObject("labRates", labRates);
		
		reportService.exportReportLabRate(request, response, labRates, 1);
	}
	/****************************************************************************
	 * description:实验室使用情况报表
	 * @author:戴昊宇
	 * @date:2017-05-10
	 ****************************************************************************/
	@RequestMapping("/report/listLabItemHourNumber")
	public ModelAndView listLabItemHourNumber(HttpServletRequest request, @RequestParam int page) {
		ModelAndView mav = new ModelAndView();
		int pageSize = 30;
		// 学期
		List<SchoolTerm> terms = shareService.findAllSchoolTerms();
		mav.addObject("terms", terms);
		// 查询记录
		List<Object[]> listItemHourNumber = reportService.getListLabItemHourNumber(request, page, pageSize);
		mav.addObject("listItemHourNumber",listItemHourNumber);
		int totalRecords = reportService.getListLabItemHourNumber(request, 1, -1).size();
		if(request.getParameter("termId") != null && !request.getParameter("termId").equals("")){
			mav.addObject("termId", request.getParameter("termId"));
		}
		
		if(request.getParameter("roomId") != null && !request.getParameter("roomId").equals("")){
			mav.addObject("roomId", request.getParameter("roomId"));
		}
		// 分页信息
		Map<String, Integer> pageModel = shareService.getPage(page, pageSize, totalRecords);
		mav.addObject("pageModel", pageModel);
		mav.addObject("pageSize",pageSize);
		mav.addObject("page", page);
		mav.addObject("totalRecords",totalRecords);
		mav.addObject("rooms", labRoomService.findAllLabRoomByQuery(1, -1, new LabRoom(),9,-1));
		mav.setViewName("/reports/listLabItemHourNumber.jsp");
		return mav;
	}
	
 		
 		/****************************************************************************
 		 * Description: 系统报表{实验室数量统计}
 		 * @author 王昊
 		 * @date 2017-10-26
 		 ****************************************************************************/
		@SuppressWarnings("unchecked")
		@RequestMapping("/report/comprehensiveReport")
		public ModelAndView comprehensiveReport(@RequestParam Integer termBack,HttpServletRequest request){
			 ModelAndView mav = new ModelAndView();
		    //1.实验室数量报表
		    // 查询记录
	 		List<Object[]> labRates = reportService.getLabRoomRate(request);
			String centerNumber = "";
			String rate ="";
			String p="";
			// 横坐标：中心名称
			String xAxis = "";
			StringBuilder str=new StringBuilder();
			StringBuilder str1=new StringBuilder();
			StringBuilder str2=new StringBuilder();
			StringBuilder str3=new StringBuilder();
			if(labRates != null && labRates.size() != 0){
				for(Object[] o:labRates){
					//centerNumber += o[0].toString()+",";
					rate += ""+o[2]+",";//实验室数量
					p +=""+o[4]+",";//实验室数用率
					xAxis += "'"+o[1]+"',";//中心名称
					
					String data = "{value:"+o[2]+",name:'"+o[1]+"'}";
					str.append(data+",");
					
				    String datan="'"+o[1]+"'";
				    str1.append(datan+',');
				    String datav=""+o[2]+"";
				    str2.append(datav+',');
				    String datap=""+o[4]+"";
				    str3.append(datap+',');
				    				
				}
				str.deleteCharAt(str.length() - 1);
				str1.deleteCharAt(str1.length() - 1);
				str2.deleteCharAt(str2.length() - 1);	
				str3.deleteCharAt(str3.length() - 1);
			}
			
			
	 		// 所有中心
	 		List<LabCenter> centerList1 = labCenterService.findAllLabCenterByLabCenter(new LabCenter());

			List<LabCenter> centers = new LinkedList<LabCenter>();
			if(centerList1 != null && centerList1.size() != 0){
				for(LabCenter l:centerList1){
					if(centerNumber.contains(l.getCenterNumber().toString()) == false){
						centers.add(l);
					}
				}
			}
			if (centers!=null && centers.size()>0) {
				for (LabCenter labCenter : centers) {
	 				xAxis+="'"+labCenter.getCenterName()+"',";
					rate += "0,";
					Object[] o = new Object[6];
					o[0] = labCenter.getCenterNumber();
					o[1] = labCenter.getCenterName();
					o[2] = 0;
					o[4]=0;
				
					labRates.add(o);
	 			}
				
	 		}
			
			//实验室数量统计+各中心实验室利用率
	 		mav.addObject("xAxis", xAxis);
			mav.addObject("rate", rate);
			mav.addObject("p", p);
			mav.addObject("str",str);
			mav.addObject("str1",str1);
			mav.addObject("str2",str2);
			mav.addObject("str3",str3);
	  		mav.addObject("labRates", labRates);
	  		
	  		//2.大综合报表
	  	    // 查询记录
			List<Object[]> intergrateRates = reportService.getCountRate(request);
			String centerNumberc = "";
			String rate2 ="";
	 		String rate3 ="";
			String rate4 ="";
			String rate5 ="";
	 		String rate6 ="";
	 		String rate7 ="";
			// 横坐标：中心名称
			String xAxis1 = "";
			StringBuilder str4=new StringBuilder();
	 		StringBuilder s1=new StringBuilder();
			StringBuilder s2=new StringBuilder();
			StringBuilder s3=new StringBuilder();
			StringBuilder s4=new StringBuilder();
			StringBuilder s5=new StringBuilder();
			StringBuilder s6=new StringBuilder();
			if(intergrateRates != null && intergrateRates.size() != 0){
				for(Object[] o1:intergrateRates){
					//centerNumber += o[0].toString()+",";
					xAxis1 +="'"+o1[1]+"',";
					rate2 += ""+o1[2]+",";
	 				rate3 += ""+o1[3]+",";
	 				rate4 += ""+o1[4]+",";
	 				rate5 += ""+o1[5]+",";
	 				rate6 += ""+o1[6]+",";
	 				rate7 += ""+o1[7]+",";

	 				
	 				String datab="'"+o1[1]+"'";
	  			    str4.append(datab+',');
	 				
	 			    String data1=""+o1[2]+"";
	 			    s1.append(data1+',');
	 			    
	 			    String data2=""+o1[3]+"";
				    s2.append(data2+',');
				    
				    String data3=""+o1[4]+"";
	 			    s3.append(data3+',');
	 			    
	 			    String data4=""+o1[5]+"";
				    s4.append(data4+',');
				    
				    String data5=""+o1[6]+"";
	 			    s5.append(data5+',');
	 			    
	 			    String data6=""+o1[7]+"";
				    s6.append(data6+',');
				   
	 			}
				str4.deleteCharAt(str4.length() - 1);
	 			s1.deleteCharAt(s1.length() - 1);
				s2.deleteCharAt(s2.length() - 1);
				s3.deleteCharAt(s3.length() - 1);	
				s4.deleteCharAt(s4.length() - 1);
				s5.deleteCharAt(s5.length() - 1);
				s6.deleteCharAt(s6.length() - 1);	
	 			
	 		
			}
			
			
	 		// 所有中心
	 		List<LabCenter> centerList = labCenterService.findAllLabCenterByLabCenter(new LabCenter());

			List<LabCenter> centersc = new LinkedList<LabCenter>();
			if(centerList != null && centerList.size() != 0){
				for(LabCenter l:centerList){
					if(centerNumberc.contains(l.getCenterNumber().toString()) == false){
						centersc.add(l);
					}
				}
			}
			if (centersc!=null && centersc.size()>0) {
				for (LabCenter labCenter : centersc) {
	 				xAxis1+="'"+labCenter.getCenterName()+"',";
	 				rate2 += "0,";
	 				rate3 += "0,";
	 				rate4 += "0,";
	 				rate5 += "0,";
	 				rate6 += "0,";
	 				rate7 += "0,";
	 				Object[] o1 = new Object[8];
	 				//o1[0] = labCenter.getCenterNumber();
	 				o1[1] = labCenter.getCenterName();
	 				o1[2] = 0;	
	 				o1[3] = 0;
	 				o1[4] = 0;
	 				o1[5] = 0;
	 				o1[6] = 0;
	 				o1[7] = 0;
	 					
	 				intergrateRates.add(o1);
	 			}
				
	 		}
			
			//实验室数量统计+各中心实验室利用率
		     	mav.addObject("xAxis1", xAxis1);
		     	mav.addObject("str4", str4);
			    mav.addObject("s1", s1);
			    mav.addObject("s2", s2);
			    mav.addObject("s3", s3);
			    mav.addObject("s4", s4);
			    mav.addObject("s5", s5);
			    mav.addObject("s6", s6);
			    mav.addObject("rate2", rate2);
			    mav.addObject("rate3", rate3);
			    mav.addObject("rate4", rate4);
			    mav.addObject("rate5", rate5);
			    mav.addObject("rate6", rate6);
			    mav.addObject("rate7", rate7);
			    mav.addObject("intergrateRates", intergrateRates);
			    
			    //3.实验室利用率
			   // 当前学期
				int termId = shareService.getBelongsSchoolTerm1(Calendar.getInstance()).getId();
				if (!EmptyUtil.isObjectEmpty(termBack)&&termBack!=-1) {
					termId = termBack;
				}
				// 选择
				if (!EmptyUtil.isStringEmpty(request.getParameter("term"))) {
					termId = Integer.parseInt(request.getParameter("term"));
				}
				List<SchoolTerm> schoolTerms = shareService.findAllSchoolTerms1();
				mav.addObject("selectedTermId", termId);
				mav.addObject("selectTerms", schoolTerms);
		 		// 查询记录
		 		List<Object[]> labRatesc = reportService.getLabUseRate1(request);
				String centerId = "";
				String ratec ="";
				// 横坐标：中心名称
				String xAxisc = "";
				if(labRatesc != null && labRatesc.size() != 0){
					for(Object[] o:labRatesc){
						centerId += o[0].toString()+",";
						ratec += ""+o[5]+",";
						xAxisc += "'"+o[1]+"',";
					}
				}
		 		// 所有中心
		 		List<LabCenter> centerListc = labCenterService.findAllLabCenterByLabCenter(new LabCenter());

				List<LabCenter> centersd = new LinkedList<LabCenter>();
				if(centerListc != null && centerListc.size() != 0){
					for(LabCenter l:centerListc){
						if(centerId.contains(l.getId().toString()) == false){
							centersd.add(l);
						}
					}
				}
				if (centersd!=null && centersd.size()>0) {
					for (LabCenter labCenter : centersd) {
		 				xAxisc+="'"+labCenter.getCenterName()+"',";
						ratec += "0,";
						Object[] o = new Object[6];
						o[0] = labCenter.getId();
						o[1] = labCenter.getCenterName();
						o[3] = 0;	
						o[4] = 0;
						o[5] = 0;
						labRates.add(o);
		 			}
		 		}
		 		mav.addObject("xAxisc", xAxisc);
		 		System.out.println(xAxisc);
				mav.addObject("ratec", ratec);
				System.out.println(ratec);
		 		mav.addObject("labRatesc", labRatesc);
		 		
		 		
		 		//4.专职、兼职实验老师分布
		 	    // 查询记录
		 		List<Object[]> techerPercent = reportService.getTecherPercent(request);	
		 		
		 		if(request.getParameter("centerNumber") != null && !request.getParameter("centerNumber").equals("")){
					mav.addObject("centerNumber", request.getParameter("centerNumber"));
				}
		 				 		
			     
		        String centerNumbertp = "";
				String tp ="";
		 		String tp1 ="";
		 		String xAxistp = "";
				StringBuilder stp=new StringBuilder();
				//StringBuilder sstp=new StringBuilder();
		 		StringBuilder stp1=new StringBuilder();
				StringBuilder stp2=new StringBuilder();
				StringBuilder stp3=new StringBuilder();
				int kk=0;
				int ky=0;
		     if(techerPercent != null && techerPercent.size() != 0){
		    	
					for(Object[] otp:techerPercent){
						//centerNumber += o[0].toString()+",";
						xAxistp +="'"+otp[1]+"',";
						tp += ""+otp[2]+",";
		 				tp1 += ""+otp[3]+",";
		 			
		 			   kk=kk+Integer.parseInt(otp[2].toString());
		 			   ky=ky+Integer.parseInt(otp[3].toString());
		 				
					//	String tpvn1 = "{value:"+otp[3]+",name:'专职'}";
					//	sstp.append(tpvn1+",");
			
		 				String datap="'"+otp[1]+"'";
		  			    stp3.append(datap+',');
		 				
		 			    String tp11=""+otp[2]+"";
		 			    stp1.append(tp11+',');
		 			    
		 			    String tp12=""+otp[3]+"";
					    stp2.append(tp12+',');				   
		 			}
					String tpvn = "{value:"+kk+",name:'兼职实验教师'},{value:"+ky+",name:'专职实验教师'}";
					stp.append(tpvn+",");
					System.out.println(stp);
				//	stp.deleteCharAt(stp.length() - 1);
				//	sstp.deleteCharAt(sstp.length() - 1);
		 			stp3.deleteCharAt(stp3.length() - 1);
		 			stp1.deleteCharAt(stp1.length() - 1);
		 			stp2.deleteCharAt(stp2.length() - 1);	
		 		
				}
			    
			 // 所有中心
		 		List<LabCenter> centerListp = labCenterService.findAllLabCenterByLabCenter(new LabCenter());				 		
		 		
				List<LabCenter> centersp = new LinkedList<LabCenter>();
				if(centerListp != null && centerListp.size() != 0){
					for(LabCenter l:centerListp){
						if(centerNumbertp.contains(l.getCenterNumber().toString()) == false){
							centersp.add(l);
						}
					}
				} 
				mav.addObject("centersp",centersp);
				
				if (centersp!=null && centersp.size()>0) {
					for (LabCenter labCenter : centersp) {
		 				xAxistp+="'"+labCenter.getCenterName()+"',";
						tp += "0,";
						Object[] otp = new Object[6];
						otp[0] = labCenter.getCenterNumber();
						otp[1] = labCenter.getCenterName();
						otp[2] = 0;
						otp[3]=0;
					
						techerPercent.add(otp);
		 			}				
		 		}		    
				mav.addObject("xAxistp", xAxistp);
				mav.addObject("tp",tp);
				mav.addObject("tp1",tp1);
				mav.addObject("stp",stp);
			//	System.out.println(stp);
			//	mav.addObject("sstp",sstp);
			//	System.out.println(sstp);
				mav.addObject("stp1",stp1);
			//	System.out.println(stp1);
				mav.addObject("stp2",stp2);
			//	System.out.println(stp2);
				mav.addObject("stp3",stp3);
			//	System.out.println(stp3);
		 		mav.addObject("techerPercent", techerPercent);			
				mav.setViewName("reports/comprehensiveReport.jsp");
			    return mav;
}	
		
		
		/****************************************************************************
 		 * Description: 系统报表{实验室人员现状}
 		 * @author 王昊
 		 * @date 2017-10-31
 		 ****************************************************************************/
		@SuppressWarnings("unchecked")
		@RequestMapping("/report/sciPerstatic")
		public ModelAndView sciPerstatic(HttpServletRequest request){
			     ModelAndView mav = new ModelAndView();
		//1.实验室教师学历结构	
		// 查询记录
	     List<Object[]> techerEducation = reportService.getTecherEducation(request);
	     
	     if(request.getParameter("centerNumber") != null && !request.getParameter("centerNumber").equals("")){
				mav.addObject("centerNumber", request.getParameter("centerNumber"));
			}
	     
	        String centerNumberte = "";
			String te ="";
	 		String te1 ="";
	 		String te2 ="";
	 		String te3 ="";
	 		String te4 ="";
	 		String te5 ="";
	 		String te6 ="";
	 		String te7 ="";
	 		String te8 ="";
	 		String xAxiste = "";
			StringBuilder ste=new StringBuilder();
			StringBuilder sste=new StringBuilder();
	 		StringBuilder ste1=new StringBuilder();
			StringBuilder ste2=new StringBuilder();
			StringBuilder ste3=new StringBuilder();
			StringBuilder ste4=new StringBuilder();
			StringBuilder ste5=new StringBuilder();
			StringBuilder ste6=new StringBuilder();
			StringBuilder ste7=new StringBuilder();
			StringBuilder ste8=new StringBuilder();
			StringBuilder ste9=new StringBuilder();

			int ke=0;
			int kf=0;
			int kg=0;
			int kh=0;
			int ki=0;
			int kj=0;
			int kl=0;
			int km=0;
	     if(techerEducation != null && techerEducation.size() != 0){
	    	
	    		
				for(Object[] ote:techerEducation){
					//centerNumber += o[0].toString()+",";
					xAxiste +="'"+ote[1]+"',";
					te += ""+ote[2]+",";
	 				te1 += ""+ote[3]+",";
	 				te2 += ""+ote[4]+",";
	 				te3 += ""+ote[5]+",";
	 				te4 += ""+ote[6]+",";
	 				te5 += ""+ote[7]+",";
	 				te6 += ""+ote[8]+",";
	 				te7 += ""+ote[9]+",";
	 				
	 				
	 				ke=ke+Integer.parseInt(ote[2].toString());
	 				kf=kf+Integer.parseInt(ote[3].toString());
	 				kg=kg+Integer.parseInt(ote[4].toString());
	 				kh=kh+Integer.parseInt(ote[5].toString());
	 				ki=ki+Integer.parseInt(ote[6].toString());
	 				kj=kj+Integer.parseInt(ote[7].toString());
	 				kl=kl+Integer.parseInt(ote[8].toString());
	 				km=km+Integer.parseInt(ote[9].toString());
	 				String datab="'"+ote[1]+"'";
	  			    ste3.append(datab+',');
	 				
	 			    String te11=""+ote[2]+"";
	 			    ste1.append(te11+',');
	 			    
	 			    String te12=""+ote[3]+"";
				    ste2.append(te12+',');	
				    
				    String te15=""+ote[4]+"";
	 			    ste5.append(te15+',');
	 			    
	 			    String te14=""+ote[5]+"";
				    ste4.append(te14+',');	
				    
				    String te16="'"+ote[6]+"'";
	  			    ste6.append(te16+',');
	 				
	 			    String te17=""+ote[7]+"";
	 			    ste7.append(te17+',');
	 			    
	 			    String te18=""+ote[8]+"";
				    ste8.append(te18+',');	
				    
				    String te19=""+ote[9]+"";
	 			    ste9.append(te19+',');
	 			    
	 			   
	 			}
				//专职
	 			String tpvn = "{value:"+ke+",name:'博士'},{value:"+kf+",name:'硕士'},{value:"+kg+",name:'大学'},{value:"+kh+",name:'大专'}";
				sste.append(tpvn+",");
				//兼职	 			
				String tevn1 = "{value:"+ki+",name:'博士'},{value:"+kj+",name:'硕士'},{value:"+kl+",name:'大学'},{value:"+km+",name:'大专'}";
				ste.append(tevn1+",");
				//ste.deleteCharAt(ste.length() - 1);
				//sste.deleteCharAt(sste.length() - 1);
	 			//ste3.deleteCharAt(ste3.length() - 1);
	 			//ste1.deleteCharAt(ste1.length() - 1);
	 			//ste2.deleteCharAt(ste2.length() - 1);	
	 			//ste5.deleteCharAt(ste5.length() - 1);
	 			//ste4.deleteCharAt(ste4.length() - 1);
	 			//ste6.deleteCharAt(ste6.length() - 1);
	 			//ste7.deleteCharAt(ste7.length() - 1);	
	 			//ste8.deleteCharAt(ste8.length() - 1);
	 			//ste9.deleteCharAt(ste9.length() - 1);
	 		
			}
		    
		 // 所有中心
	 		List<LabCenter> centerLists = labCenterService.findAllLabCenterByLabCenter(new LabCenter());
	
			List<LabCenter> centerss = new LinkedList<LabCenter>();
			if(centerLists != null && centerLists.size() != 0){
				for(LabCenter l:centerLists){
					if(centerNumberte.contains(l.getCenterNumber().toString()) == false){
						centerss.add(l);
					}
				}
			} 
			mav.addObject("centerss",centerss);
			if (centerss!=null && centerss.size()>0) {
				for (LabCenter labCenter : centerss) {
	 				xAxiste+="'"+labCenter.getCenterName()+"',";
					te += "0,";
					Object[] ote = new Object[18];
					ote[0]=labCenter.getCenterNumber();
					ote[1]=labCenter.getCenterName();
					ote[2]=0;
					ote[3]=0;
					ote[4]=0;
					ote[5]=0;
					ote[6]=0;
					ote[7]=0;
					ote[8]=0;
					ote[9]=0;
				
					techerEducation.add(ote);
	 			}				
	 		}		    
			mav.addObject("xAxiste", xAxiste);
			mav.addObject("te",te);
			mav.addObject("te1",te1);
			mav.addObject("ste",ste);
			System.out.println(ste);
			mav.addObject("sste",sste);
			System.out.println(sste);
			mav.addObject("ste1",ste1);
			mav.addObject("ste2",ste2);
			mav.addObject("ste3",ste3);
			mav.addObject("ste4",ste4);
			mav.addObject("ste5",ste5);
			mav.addObject("ste6",ste6);
			mav.addObject("ste7",ste7);
			mav.addObject("ste8",ste8);
			mav.addObject("ste9",ste9);
			mav.addObject("te2",te2);
			mav.addObject("te3",te3);
			mav.addObject("te4",te4);
			mav.addObject("te5",te5);
			mav.addObject("te6",te6);
			mav.addObject("te7",te7);
			mav.addObject("te8",te8);
	 		mav.addObject("techerEducation", techerEducation);			
	 		
	 		//2.实验室教师年龄结构
	 	   // 查询记录
		     List<Object[]> techerAge = reportService.getTecherAge(request);	 
		     if(request.getParameter("centerNumber") != null && !request.getParameter("centerNumber").equals("")){
					mav.addObject("centerNumber", request.getParameter("centerNumber"));
				}
		     
		        String centerNumberta = "";
				String ta ="";
		 		String ta1 ="";
		 		String ta2 ="";
		 		String ta3 ="";
		 		String ta4 ="";
		 		String ta5 ="";
		 		String ta6 ="";
		 		String ta7 ="";
		 		String ta8 ="";
		 		String xAxista = "";
				StringBuilder sta=new StringBuilder();
				StringBuilder ssta=new StringBuilder();
		 		StringBuilder sta1=new StringBuilder();
				StringBuilder sta2=new StringBuilder();
				StringBuilder sta3=new StringBuilder();
				StringBuilder sta4=new StringBuilder();
				StringBuilder sta5=new StringBuilder();
				StringBuilder sta6=new StringBuilder();
				StringBuilder sta7=new StringBuilder();
				StringBuilder sta8=new StringBuilder();
				StringBuilder sta9=new StringBuilder();
				
				int ko=0;
				int kp=0;
				int kq=0;
				int kr=0;
				int ks=0;
				int kt=0;
				int ku=0;
				int kv=0;
			
		     if(techerAge != null && techerAge.size() != 0){
		    	
					for(Object[] ota:techerAge){
						//centerNumber += o[0].toString()+",";
						xAxista +="'"+ota[1]+"',";
						ta += ""+ota[2]+",";
		 				ta1 += ""+ota[3]+",";
		 				ta2 += ""+ota[4]+",";
		 				ta3 += ""+ota[5]+",";
		 				ta4 += ""+ota[6]+",";
		 				ta5 += ""+ota[7]+",";
		 				ta6 += ""+ota[8]+",";
		 				ta7 += ""+ota[9]+",";
		 				
		 				ko=ko+Integer.parseInt(ota[2].toString());
		 				kp=kp+Integer.parseInt(ota[3].toString());
		 				kq=kq+Integer.parseInt(ota[4].toString());
		 				kr=kr+Integer.parseInt(ota[5].toString());
		 				ks=ks+Integer.parseInt(ota[6].toString());
		 				kt=kt+Integer.parseInt(ota[7].toString());
		 				ku=ku+Integer.parseInt(ota[8].toString());
		 				kv=kv+Integer.parseInt(ota[9].toString());
		 			 
		 				String datac="'"+ota[1]+"'";
		  			    sta3.append(datac+',');
		 				
		 			    String ta11=""+ota[2]+"";
		 			    sta1.append(ta11+',');
		 			    
		 			    String ta12=""+ota[3]+"";
					    sta2.append(ta12+',');	
					    
					    String ta15=""+ota[4]+"";
		 			    sta5.append(ta15+',');
		 			    
		 			    String ta14=""+ota[5]+"";
					    sta4.append(ta14+',');	
					    
					    String ta16="'"+ota[6]+"'";
		  			    sta6.append(ta16+',');
		 				
		 			    String ta17=""+ota[7]+"";
		 			    sta7.append(ta17+',');
		 			    
		 			    String ta18=""+ota[8]+"";
					    sta8.append(ta18+',');	
					    
					    String ta19=""+ota[9]+"";
		 			    sta9.append(ta19+',');

		 			}
					 
				
		 				//专职
		 				String tavn = "{value:"+ko+",name:'35岁以下'},{value:"+kp+",name:'36-50岁'},{value:"+kq+",name:'51-60岁'},{value:"+kr+",name:'60岁以上'}";
						ssta.append(tavn+",");
						//兼职	 			
						String tavn1 = "{value:"+ks+",name:'35岁以下'},{value:"+kt+",name:'36-50岁'},{value:"+ku+",name:'51-60岁'},{value:"+kv+",name:'60岁以上'}";
						sta.append(tavn1+",");
			            
				}
			    
			 // 所有中心
		 		List<LabCenter> centerLista = labCenterService.findAllLabCenterByLabCenter(new LabCenter());
		
				List<LabCenter> centersa = new LinkedList<LabCenter>();
				if(centerLista != null && centerLista.size() != 0){
					for(LabCenter l:centerLista){
						if(centerNumberta.contains(l.getCenterNumber().toString()) == false){
							centersa.add(l);
						}
					}
				} 
				mav.addObject("centersa",centersa);
				if (centersa!=null && centersa.size()>0) {
					for (LabCenter labCenter : centersa) {
		 				xAxista+="'"+labCenter.getCenterName()+"',";
						ta += "0,";
						Object[] ota = new Object[18];
						ota[0]=labCenter.getCenterNumber();
						ota[1]=labCenter.getCenterName();
						ota[2]=0;
						ota[3]=0;
						ota[4]=0;
						ota[5]=0;
						ota[6]=0;
						ota[7]=0;
						ota[8]=0;
						ota[9]=0;
					
						techerAge.add(ota);
		 			}				
		 		}		    
				mav.addObject("xAxista", xAxista);
				mav.addObject("ta",ta);
				mav.addObject("ta1",ta1);
				mav.addObject("sta",sta);
				System.out.println(sta);
				mav.addObject("ssta",ssta);
				System.out.println(ssta);
				mav.addObject("sta1",sta1);
				mav.addObject("sta2",sta2);
				mav.addObject("sta3",sta3);
				mav.addObject("sta4",sta4);
				mav.addObject("sta5",sta5);
				mav.addObject("sta6",sta6);
				mav.addObject("sta7",sta7);
				mav.addObject("sta8",sta8);
				mav.addObject("sta9",sta9);
				mav.addObject("ta2",ta2);
				mav.addObject("ta3",ta3);
				mav.addObject("ta4",ta4);
				mav.addObject("ta5",ta5);
				mav.addObject("ta6",ta6);
				mav.addObject("ta7",ta7);
				mav.addObject("ta8",ta8);
		 		mav.addObject("techerAge", techerAge);
	 		
		 		//3.实验室教师职称结构
			 	// 查询记录
			     List<Object[]> techerLevel = reportService.getTecherLevel(request);
			     if(request.getParameter("centerNumber") != null && !request.getParameter("centerNumber").equals("")){
						mav.addObject("centerNumber", request.getParameter("centerNumber"));
					}
			     
			        String centerNumbertl = "";
					String tl ="";
			 		String tl1 ="";
			 		String tl2 ="";
			 		String tl3 ="";
			 		String tl4 ="";
			 		String tl5 ="";
			 		String tl6 ="";
			 		String tl7 ="";
			 		String tl8 ="";
			 		String xAxistl = "";
					StringBuilder stl=new StringBuilder();
					StringBuilder sstl=new StringBuilder();
			 		StringBuilder stl1=new StringBuilder();
					StringBuilder stl2=new StringBuilder();
					StringBuilder stl3=new StringBuilder();
					StringBuilder stl4=new StringBuilder();
					StringBuilder stl5=new StringBuilder();
					StringBuilder stl6=new StringBuilder();
					StringBuilder stl7=new StringBuilder();
					StringBuilder stl8=new StringBuilder();
					StringBuilder stl9=new StringBuilder();
					StringBuilder stl10=new StringBuilder();
					int a=0;
					int b=0;
					int c=0;
					int d=0;
					int e=0;
					int f=0;
					int g=0;
					int h=0;
					int i=0;
			     if(techerLevel != null && techerLevel.size() != 0){
			   
						for(Object[] otl:techerLevel){
							//centerNumber += o[0].toString()+",";
							xAxistl +="'"+otl[1]+"',";
							tl += ""+otl[2]+",";
			 				tl1 += ""+otl[3]+",";
			 				tl2 += ""+otl[4]+",";
			 				tl3 += ""+otl[5]+",";
			 				tl4 += ""+otl[6]+",";
			 				tl5 += ""+otl[7]+",";
			 				tl6 += ""+otl[8]+",";
			 				tl7 += ""+otl[9]+",";
			 				tl8 += ""+otl[10]+",";
			 				
			 				a=a+Integer.parseInt(otl[2].toString());
			 				b=b+Integer.parseInt(otl[3].toString());
			 				c=c+Integer.parseInt(otl[4].toString());
			 				d=d+Integer.parseInt(otl[5].toString());
			 				e=e+Integer.parseInt(otl[6].toString());
			 				f=f+Integer.parseInt(otl[7].toString());
			 				g=g+Integer.parseInt(otl[8].toString());
			 				h=h+Integer.parseInt(otl[9].toString());
			 				i=i+Integer.parseInt(otl[10].toString());
			 		
			 				String datad="'"+otl[1]+"'";
			  			    stl3.append(datad+',');
			 				
			 			    String tl11=""+otl[2]+"";
			 			    stl1.append(tl11+',');
			 			    
			 			    String tl12=""+otl[3]+"";
						    stl2.append(tl12+',');	
						    
						    String tl15=""+otl[4]+"";
			 			    stl5.append(tl15+',');
			 			    
			 			    String tl14=""+otl[5]+"";
						    stl4.append(tl14+',');	
						    
						    String tl16=""+otl[6]+"";
			  			    stl6.append(tl16+',');
			 				
			 			    String tl17=""+otl[7]+"";
			 			    stl7.append(tl17+',');
			 			    
			 			    String tl18=""+otl[8]+"";
						    stl8.append(tl18+',');	
						    
						    String tl19=""+otl[9]+"";
			 			    stl9.append(tl19+',');
			 			     
			 			    String tl20=""+otl[10]+"";
			 			    stl10.append(tl20+',');

			    			}
			        //专职
		 			String tlvn = "{value:"+a+",name:'教授'},{value:"+b+",name:'副教授(高工)'},{value:"+c+",name:'讲师(工程师)'},{value:"+d+",name:'助工'},{value:"+i+",name:'工人'}";
					sstl.append(tlvn+",");
					//兼职	 			
					String tlvn1 = "{value:"+e+",name:'教授'},{value:"+f+",name:'副教授(高工)'},{value:"+g+",name:'讲师(工程师)'},{value:"+h+",name:'助工'}";
					stl.append(tlvn1+",");
					
					}
				    
				 // 所有中心
			 		List<LabCenter> centerListl = labCenterService.findAllLabCenterByLabCenter(new LabCenter());
			
					List<LabCenter> centersl = new LinkedList<LabCenter>();
					if(centerListl != null && centerListl.size() != 0){
						for(LabCenter l:centerListl){
							if(centerNumbertl.contains(l.getCenterNumber().toString()) == false){
								centersl.add(l);
							}
						}
					} 
					mav.addObject("centersl",centersl);
					if (centersl!=null && centersl.size()>0) {
						for (LabCenter labCenter : centersl) {
			 				xAxistl+="'"+labCenter.getCenterName()+"',";
							tl += "0,";
							Object[] otl = new Object[18];
							otl[0]=labCenter.getCenterNumber();
							otl[1]=labCenter.getCenterName();
							otl[2]=0;
							otl[3]=0;
							otl[4]=0;
							otl[5]=0;
							otl[6]=0;
							otl[7]=0;
							otl[8]=0;
							otl[9]=0;
							otl[10]=0;
						
							techerLevel.add(otl);
			 			}				
			 		}		    
					mav.addObject("xAxistl", xAxistl);
					mav.addObject("tl",tl);
					mav.addObject("tl1",tl1);
					mav.addObject("stl",stl);
					System.out.println(stl);
					mav.addObject("sstl",sstl);
					System.out.println(sstl);
					mav.addObject("stl1",stl1);
					mav.addObject("stl2",stl2);
					mav.addObject("stl3",stl3);
					mav.addObject("stl4",stl4);
					mav.addObject("stl5",stl5);
					mav.addObject("stl6",stl6);
					mav.addObject("stl7",stl7);
					mav.addObject("stl8",stl8);
					mav.addObject("stl9",stl9);
					mav.addObject("stl10",stl10);
					mav.addObject("tl2",tl2);
					mav.addObject("tl3",tl3);
					mav.addObject("tl4",tl4);
					mav.addObject("tl5",tl5);
					mav.addObject("tl6",tl6);
					mav.addObject("tl7",tl7);
					mav.addObject("tl8",tl8);
			 		mav.addObject("techerLevel", techerLevel);
		 		    mav.setViewName("reports/sciPerstatic.jsp");
		 		    return mav;
	}		
		
	/****************************************************************************
	 * @throws Exception 
	 * @description：实验室使用情况报表导出
	 * @author：陈乐为
	 * @date：2018-1-3
	 ****************************************************************************/
	@RequestMapping("/report/exportLabItemHourNumber")
	public void exportLabItemHourNumber(HttpServletRequest request,HttpServletResponse response) throws Exception {
		reportService.exportLabItemHourNumber(request, response);
	
	}
}
