package net.xidlims.service.report;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.luxunsh.util.EmptyUtil;
import net.xidlims.dao.LabCenterDAO;
import net.xidlims.dao.LabCenterDAOImpl;
import net.xidlims.dao.LabReservationDAO;
import net.xidlims.dao.LabRoomAdminDAO;
import net.xidlims.dao.LabRoomAgentDAO;
import net.xidlims.dao.LabRoomDAO;
import net.xidlims.dao.LabRoomDeviceDAO;
import net.xidlims.dao.LabRoomDeviceReservationDAO;
import net.xidlims.dao.OperationItemDAO;
import net.xidlims.dao.ReportParameterDAO;
import net.xidlims.dao.ReportRateDAO;
import net.xidlims.dao.SchoolAcademyDAO;
import net.xidlims.dao.SchoolCourseStudentDAO;
import net.xidlims.dao.SchoolDeviceDAO;
import net.xidlims.dao.SchoolMajorDAO;
import net.xidlims.dao.SchoolTermDAO;
import net.xidlims.dao.TimetableAppointmentDAO;
import net.xidlims.dao.TimetableCourseStudentDAO;
import net.xidlims.dao.UserDAO;
import net.xidlims.domain.LabCenter;
import net.xidlims.domain.LabReservation;
import net.xidlims.domain.LabRoom;
import net.xidlims.domain.LabRoomDevice;
import net.xidlims.domain.LabRoomDeviceReservation;
import net.xidlims.domain.OperationItem;
import net.xidlims.domain.ReportParameter;
import net.xidlims.domain.ReportRate;
import net.xidlims.domain.SchoolAcademy;
import net.xidlims.domain.SchoolDevice;
import net.xidlims.domain.SchoolTerm;
import net.xidlims.domain.TimetableAppointment;
import net.xidlims.domain.User;
import net.xidlims.service.common.ShareService;
import net.xidlims.service.device.LabRoomDeviceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import excelTools.ExcelUtils;
import excelTools.JsGridReportBase;
import excelTools.TableData;

@Service("ReportService")
public class ReportServiceImpl implements ReportService 
{
	@PersistenceContext private EntityManager entityManager;
	@Autowired
	private LabRoomDAO labRoomDAO;
	@Autowired
	private TimetableAppointmentDAO timetableAppointmentDAO;
	@Autowired
	private SchoolCourseStudentDAO schoolCourseStudentDAO;
	@Autowired
	private SchoolDeviceDAO schoolDeviceDAO;
	@Autowired
	private ReportParameterDAO reportParameterDAO;
	@Autowired
	private SchoolTermDAO schoolTermDAO;
	@Autowired
	private LabRoomDeviceReservationDAO labRoomDeviceReservationDAO;
	@Autowired
	private OperationItemDAO operationItemDAO;
	@Autowired
	private SchoolAcademyDAO schoolAcademyDAO;
	@Autowired
	private ReportRateDAO reportRateDAO;
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private ShareService shareService;
	@Autowired
	private TimetableCourseStudentDAO timetableCourseStudentDAO;
	@Autowired
	private SchoolMajorDAO schoolMajorDAO;
	@Autowired 
	private LabRoomAdminDAO labRoomAdminDAO;
	@Autowired
	private LabReservationDAO labReservationDAO;
	@Autowired
	private LabRoomDeviceService labRoomDeviceService;
	@Autowired
	private LabRoomDeviceDAO labRoomDeviceDAO;
	@Autowired
	private LabRoomAgentDAO labRoomAgentDAO; 
	@Autowired
	private LabCenterDAO labCenterDAO; 
	public ReportServiceImpl(){
	}

	/**
	 * 获取实验室容量
	 * @author hely
	 * 2014.08.06
	 */
	@Override
	public int getLabCapacity(int labRoomId) 
	{
		LabRoom labRoom = labRoomDAO.findLabRoomByPrimaryKey(labRoomId);
		
		if(labRoom != null && labRoom.getLabRoomCapacity() != null)
		{
			return labRoom.getLabRoomCapacity();  
		}
		return 0;  //数据库中没有数据则返回0
	}

	/**
	 * 实验室额定课时数（学校给的数据）
	 * @author hely
	 * 2014.08.06
	 */
	@Override
	public int getRatedCourseTime(String academyNumber) 
	{
		int ratedCourseTime = 0;
		
		StringBuffer hql = new StringBuffer("select r from ReportParameter r where r.schoolAcademy.academyNumber=?");
		List<ReportParameter> reportParameters = reportParameterDAO.executeQuery(hql.toString(), 0, -1, academyNumber);
		
		if(reportParameters.size() > 0)
		{
			if(reportParameters.get(0).getRatedCourseTime() != null)
				ratedCourseTime = reportParameters.get(0).getRatedCourseTime();
		}
		
		return ratedCourseTime;
	}

	/**
	 * 单个实验室实验人时数
	 * @author hely
	 * 2014.08.06
	 */
	@Override
	public int getStudentTime(int labRoomId, List<SchoolTerm> schoolTerms) 
	{
		int studentTime = 0;
		
		StringBuffer terms = new StringBuffer("(");
		for (int i=0;i<schoolTerms.size();i++) 
		{
			if(i == schoolTerms.size()-1)
			{
				terms.append(schoolTerms.get(i).getId()+")");
			}
			else
			{
				terms.append(schoolTerms.get(i).getId()+",");
			}
		}
		
		StringBuffer hql = new StringBuffer("select distinct ta from TimetableLabRelated tlr right join tlr.timetableAppointment ta where tlr.labRoom.id="+labRoomId+" and ta.status=1 and ta.schoolCourseDetail.schoolTerm.id in "+terms+" ");
		//自主排课
		StringBuffer hqlSelf = new StringBuffer("select distinct ta from TimetableLabRelated tlr right join tlr.timetableAppointment ta where tlr.labRoom.id="+labRoomId+" and ta.status=1 and ta.timetableSelfCourse.schoolTerm.id in "+terms+" ");
		List<TimetableAppointment> timetableAppointments = timetableAppointmentDAO.executeQuery(hql.toString(), 0, -1);
		timetableAppointments.addAll(timetableAppointmentDAO.executeQuery(hqlSelf.toString(), 0, -1));
		
		for (TimetableAppointment timetableAppointment : timetableAppointments) 
		{
			//公式=周次*每次上课的总节次*上课的学生数量
			if(timetableAppointment.getTimetableStyle() >=1 && timetableAppointment.getTimetableStyle()<=4)  //教务排课，学生数量从shcool_course_student发表中获取
			{
				studentTime += (timetableAppointment.getEndWeek()-timetableAppointment.getStartWeek()+1)*(timetableAppointment.getEndClass()-timetableAppointment.getStartClass()+1)*getStudentAmountByDetail(timetableAppointment.getAppointmentNo());
			}
			else if(timetableAppointment.getTimetableStyle()==5 || timetableAppointment.getTimetableStyle()==6)  //自主排课，学生数量从timetable_course_student表中获取
			{
				String[] selfCourse = timetableAppointment.getAppointmentNo().split("-");
				studentTime += (timetableAppointment.getEndWeek()-timetableAppointment.getStartWeek()+1)*(timetableAppointment.getEndClass()-timetableAppointment.getStartClass()+1)*getStudentAmountBySelfCourse(Integer.parseInt(selfCourse[selfCourse.length-1]));
			}
		}
		
		//实验室预约
		StringBuffer hqlLabReservation = new StringBuffer("select distinct r from LabReservationTimeTable tt join tt.labReservation r where r.auditResults=1 and tt.schoolTerm.id in "+terms+" and r.labRoom.id="+labRoomId);
		List<LabReservation> labReservations = labReservationDAO.executeQuery(hqlLabReservation.toString(), 0, -1);
		
		for (LabReservation labReservation : labReservations) 
		{
			studentTime += labReservation.getLabReservationTimeTables().size()*labReservation.getLabReservationTimeTableStudents().size();
		}
		
		//设备预约
		StringBuffer hqlDeviceReservation = new StringBuffer("select dr from LabRoomDeviceReservation dr where 1=1 ");
		hqlDeviceReservation.append(" and dr.CDictionaryByCAuditResult.CNumber=2");  //审核通过的
		hqlDeviceReservation.append(" and dr.schoolTerm in "+terms+" ");  //学期
		hqlDeviceReservation.append(" and dr.labRoomDevice.labRoom.id="+labRoomId);  //实验室id
		List<LabRoomDeviceReservation> labRoomDeviceReservations = labRoomDeviceReservationDAO.executeQuery(hqlDeviceReservation.toString(), 0, -1);
		
		for (LabRoomDeviceReservation labRoomDeviceReservation : labRoomDeviceReservations) 
		{
			studentTime += ((labRoomDeviceReservation.getEndtime().getTimeInMillis()-labRoomDeviceReservation.getBegintime().getTimeInMillis())/3600000)*60/45;
		}
		
		return studentTime;
	}
	
	/**
	 * 计算指定一些实验室的人时数之和
	 * @param labArr 实验室id数组
	 * @param schoolTerms 学期
	 * @author hely
	 * 2014.12.03
	 */
	@Override
	public int getStudentTimeLabs(int[] labArr, List<SchoolTerm> schoolTerms)
	{
		int studentTimeSum = 0;
		for(int i=0;i<labArr.length;i++)
		{
			studentTimeSum += getStudentTime(labArr[i], schoolTerms);
		}
		
		return studentTimeSum;
	}
	
	/**
	 * 获取指定SchoolCourseDetail的学生数量(教务排课)
	 * @author hely
	 * 2014.08.07
	 */
	public int getStudentAmountByDetail(String courseDetailNO)
	{
		StringBuffer hql = new StringBuffer("select count(distinct s) from SchoolCourseStudent s where s.schoolCourseDetail.courseDetailNo='"+courseDetailNO+"' ");
		
		return ((Long) (schoolCourseStudentDAO.createQuerySingleResult(hql.toString()).getSingleResult())).intValue();
	}

	/**
	 * 获取自主排课的学生数量
	 * @author hely
	 * 2014.11.05
	 */
	public int getStudentAmountBySelfCourse(int selfCourseId)
	{
		StringBuffer hql = new StringBuffer("select count(s) from TimetableCourseStudent s where s.timetableSelfCourse.id="+selfCourseId);
		
		return ((Long) (timetableCourseStudentDAO.createQuerySingleResult(hql.toString()).getSingleResult())).intValue();
	}
	
	/**
	 * 单个实验室利用率（绩效指标--单个实验室）
	 * @author hely
	 * 2014.08.06
	 */
	@Override
	public String getSingleLabRate(int labRoomId, int ratedCourseTime, List<SchoolTerm> schoolTerms) 
	{
		//如果计算的时间段为一个学期，则实验室额定课时数应该除以2
		double ratedCourseTimeTerm = (schoolTerms.size() == 1)? ratedCourseTime/2.0:ratedCourseTime;
		
		//实验室利用率=学年实验人时数/(实验室容量*实验室额定课时数)，分母中有一个为0，计算结果为0
		double rate = (getLabCapacity(labRoomId) == 0 || ratedCourseTime == 0) ? 0:getStudentTime(labRoomId, schoolTerms)/(getLabCapacity(labRoomId)*ratedCourseTimeTerm);
		
		DecimalFormat df = new DecimalFormat("0.00");  //保留两位小数
		
		return df.format(rate*100);
	}
	
	/**
	 * 计算一个实验室有多个实验室分室的实验室利用率
	 * @param labArr 实验分室id(labRoom的id)
	 * @param ratedCourseTime 额定课时数
	 * @param schoolTerms 学期
	 * @author hely
	 * 2014.12.03
	 */
	@Override
	public String getLabRateLabAnnex(int[] labArr, int ratedCourseTime, List<SchoolTerm> schoolTerms)
	{
		//如果计算的时间段为一个学期，则实验室额定课时数应该除以2
		double ratedCourseTimeTerm = (schoolTerms.size() == 1)? ratedCourseTime/2.0:ratedCourseTime;
		int labCapacity = 0;  //实验室容量
		
		for(int i=0;i<labArr.length;i++)
		{
			if(getLabCapacity(labArr[i]) > 0)
			{
				
				labCapacity = getLabCapacity(labArr[i]);
				break;  //实验室分室只是逻辑上的概念，是一个实验室在逻辑上的划分，所以容量是一致的
			}
		}
		
		//实验室利用率=学年实验人时数/(实验室容量*实验室额定课时数)，分母中有一个为0，计算结果为0
		double rate = (labCapacity == 0 || ratedCourseTime == 0) ? 0:getStudentTimeLabs(labArr, schoolTerms)/(labCapacity*ratedCourseTimeTerm);
				
		DecimalFormat df = new DecimalFormat("0.00");  //保留两位小数
				
		return df.format(rate*100);
	}

	/**
	 * 学院实验室利用率（绩效指标）
	 * @author hely
	 * 2014.08.06
	 */
	@Override
	public BigDecimal getAcademyLabRate(String academyNumber, List<SchoolTerm> schoolTerms) 
	{
		BigDecimal studentTimeSum = new BigDecimal(getStudentTimeByAcademy(academyNumber, schoolTerms)*100);  //该学院总实验人时数，乘以100得百分比
		int labRoomCapacitySum = getLabCapacityByAcademy(academyNumber);  //该学院实验室总容量
		int ratedCourseTime = getRatedCourseTime(academyNumber);  //实验室额定课时数
		//如果为一个学期的话，实验室额定课时数应该除以2，数据库存储的是整个学年的实验室额定课时数
		double ratedCourseTimeTerm = (schoolTerms.size() == 1)? ratedCourseTime/2.0:ratedCourseTime;
		BigDecimal result = new BigDecimal(labRoomCapacitySum*ratedCourseTimeTerm);
		
		//实验室利用率=学年实验人时数/(实验室容量*实验室额定课时数)，分母中有一个为0，计算结果为0
		try
		{
			return studentTimeSum.divide(result, 2, BigDecimal.ROUND_HALF_UP);  //两位小数
		}catch (Exception e) {
			return new BigDecimal("0");
		}
	}
	
	/**
	 * 根据公式中的变量计算学院实验室利用率
	 * @param studentTimeSum 实验室人时数
	 * @param ratedCourseTime 额定课时数
	 * @param labRoomCapacity 实验室容量
	 * @author hely
	 * 2014.08.26
	 */
	public String getAcademyLabRateStr(int studentTimeSum, double ratedCourseTime, int labRoomCapacity)
	{
		//实验室利用率=学年实验人时数/(实验室容量*实验室额定课时数)，分母中有一个为0，计算结果为0
		BigDecimal rate = null;
		BigDecimal studentTime = new BigDecimal(studentTimeSum*100);
		BigDecimal result = new BigDecimal(ratedCourseTime*labRoomCapacity);
		
		try
		{
			rate = studentTime.divide(result, 2, BigDecimal.ROUND_HALF_UP);
		}catch (Exception e) {
			rate = new BigDecimal("0");
		}
		
		return rate.toString();
	}

	/**
	 * 学校大型仪器设备年平均机时（学校给的数据，存储在report_parameter表中）
	 * @author hely
	 * 2014.08.06
	 */
	@Override
	public int getLargeDeviceAvgTime(String academyNumber) 
	{
		int largeDeviceAvgTime = 0;
		
		StringBuffer hql = new StringBuffer("select r from ReportParameter r where r.schoolAcademy.academyNumber=?");
		List<ReportParameter> reportParameters = reportParameterDAO.executeQuery(hql.toString(), 0, -1, academyNumber);
		
		if(reportParameters.size() > 0)
		{
			if(reportParameters.get(0).getDeviceAvgTime() != null)
				largeDeviceAvgTime = reportParameters.get(0).getDeviceAvgTime();
		}
		
		return largeDeviceAvgTime;
	}

	/**
	 * 获取某学院大型仪器设备（价格大于等于10万的设备）
	 * @author hely
	 * 2014.08.06
	 */
	@Override
	public List<SchoolDevice> getLargeDeviceByAcademy(String academyNumber, String terms) 
	{
		StringBuffer hql = new StringBuffer("select distinct d.schoolDevice from LabRoomDevice d where d.schoolDevice.devicePrice >= 100000");
		if(academyNumber != null && !"".equals(academyNumber))
		{
			hql.append(" and d.labRoom.labAnnex.labCenter.schoolAcademy.academyNumber like '"+academyNumber+"%' ");
		}
		
		List<SchoolDevice> schoolDevices = schoolDeviceDAO.executeQuery(hql.toString(), 0, -1);
		
		if (schoolDevices!=null && schoolDevices.size()>0) {
			for (SchoolDevice schoolDevice : schoolDevices) {
				labRoomDeviceService.calculateHoursForSchoolDevice(schoolDevice.getDeviceNumber(), terms);
			}
		}
		
		return schoolDevices;
	}
	
	/**
	 * 获取某学院大型仪器设备（价格低于10万的设备）
	 * @author hely
	 * 2014.09.26
	 */
	@Override
	public List<SchoolDevice> getDeviceByAcademy(String academyNumber)
	{
		StringBuffer hql = new StringBuffer("select distinct d.schoolDevice from LabRoomDevice d where d.schoolDevice.devicePrice < 100000");
		if(academyNumber != null && !"".equals(academyNumber))
		{
			hql.append(" and d.labRoom.labAnnex.labCenter.schoolAcademy.academyNumber like '"+academyNumber+"%' ");
		}
		
		return schoolDeviceDAO.executeQuery(hql.toString(), 0, -1);
	}
	
	/**
	 * 获取某学院大型仪器设备台数（价格大于等于10万的设备）
	 * @author hely
	 * 2014.08.08
	 */
	public int getLargeDeviceAmountByAcademy(String academyNumber)
	{
		StringBuffer hql = new StringBuffer("select count(distinct d.schoolDevice) from LabRoomDevice d where d.schoolDevice.devicePrice >= 100000");
		hql.append(" and d.labRoom.labAnnex.labCenter.schoolAcademy.academyNumber like '"+academyNumber+"%' ");
		
		return ((Long)schoolDeviceDAO.createQuerySingleResult(hql.toString()).getSingleResult()).intValue();
	}

	/**
	 * 学院大型仪器设备使用机时汇总
	 * @author hely
	 * 2014.08.06
	 */
	@Override
	public int getLargeDeviceSumTime(String academyNumber, List<SchoolTerm> schoolTerms) 
	{
		int sumTime = 0;  //单位：小时
		
		//构造学期查询字符串，例如(2,3)或者(2)
		StringBuffer terms = new StringBuffer("(");
		for (int i=0;i<schoolTerms.size();i++) 
		{
			if(i == schoolTerms.size()-1)
			{
				terms.append(schoolTerms.get(i).getId()+")");
			}
			else
			{
				terms.append(schoolTerms.get(i).getId()+",");
			}
		}
		
		StringBuffer hql = new StringBuffer("select dr from LabRoomDeviceReservation dr where 1=1 ");
		hql.append(" and dr.CDictionaryByCAuditResult.id=2 ");  //审核通过
		hql.append(" and dr.labRoomDevice.schoolDevice.devicePrice >= 100000 ");  //大型设备（大于等于10w）
		hql.append(" and dr.labRoomDevice.schoolDevice.schoolAcademy.academyNumber='"+academyNumber+"' ");  //学院
		hql.append(" and dr.schoolTerm.id in "+terms+" ");  //学期
		
		List<LabRoomDeviceReservation> labRoomDeviceReservations = labRoomDeviceReservationDAO.executeQuery(hql.toString(), 0, -1);
		
		for (LabRoomDeviceReservation labRoomDeviceReservation : labRoomDeviceReservations) 
		{
			 sumTime += (labRoomDeviceReservation.getEndtime().getTimeInMillis() - labRoomDeviceReservation.getBegintime().getTimeInMillis())/3600000;
		}
		
		return sumTime;
	}

	/**
	 * 大型实验仪器设备使用机时的平均利用率(绩效报表)
	 * @author hely
	 * 2014.08.08
	 */
	@Override
	public BigDecimal getLargeDeviceTimeRate(String academyNumber, List<SchoolTerm> schoolTerms) 
	{
		int largeDeviceAvgTime = getLargeDeviceAvgTime(academyNumber);
		//如果限定时间为一个学期，则设备年平均机时应该除以2，因为数据库存储的是整个学年的时间
		double largeDeviceAvgTimeTerm = (schoolTerms.size() == 1)? largeDeviceAvgTime/2.0:largeDeviceAvgTime;
		
		//公式=∑学院单件大型仪器设备使用机时/(学校大型仪器设备年平均机时*该学院大型仪器设备数)
		BigDecimal LargeDeviceSumTime = new BigDecimal(getLargeDeviceSumTime(academyNumber, schoolTerms)*100);  //∑学院单件大型仪器设备使用机时，乘以100得百分数
		BigDecimal result = new BigDecimal(largeDeviceAvgTimeTerm*getLargeDeviceAmountByAcademy(academyNumber));  //学校大型仪器设备年平均机时*该学院大型仪器设备数
		
		try
		{
			return LargeDeviceSumTime.divide(result, 2, BigDecimal.ROUND_HALF_UP);
		}catch (Exception e) {
			return new BigDecimal("0");
		}
	}

	/**
	 * 某学院给定学期内使用的大型实验仪器台数
	 * @author hely
	 * 2014.08.06
	 */
	@Override
	public int getLargeDeviceUsedAmount(String academyNumber, List<SchoolTerm> schoolTerms) 
	{
		int largeDeviceUsedAmount = 0;
		
		//构造学期查询字符串，例如(2,3)或者(2)
		StringBuffer terms = new StringBuffer("(");
		for (int i=0;i<schoolTerms.size();i++) 
		{
			if(i == schoolTerms.size()-1)
			{
				terms.append(schoolTerms.get(i).getId()+")");
			}
			else
			{
				terms.append(schoolTerms.get(i).getId()+",");
			}
		}
		
		StringBuffer hql = new StringBuffer("select count(distinct dr.labRoomDevice.schoolDevice) from LabRoomDeviceReservation dr where 1=1 ");
		hql.append(" and dr.CDictionaryByCAuditResult.id=2 ");  //审核通过
		hql.append(" and dr.labRoomDevice.schoolDevice.devicePrice >= 100000 ");  //大型设备（大于等于10w）
		hql.append(" and dr.labRoomDevice.schoolDevice.schoolAcademy.academyNumber='"+academyNumber+"' ");  //学院
		hql.append(" and dr.schoolTerm.id in "+terms+" ");  //学期
		
		largeDeviceUsedAmount = ((Long)schoolDeviceDAO.createQuerySingleResult(hql.toString()).getSingleResult()).intValue();
		
		return largeDeviceUsedAmount;
	}
	
	/**
	 * 某学院给定学期内使用的大型实验仪器
	 * @param academyNumber 学院编号
	 * @param schoolTerms 学期
	 * @author hely
	 * 2014.09.10
	 */
	public List<SchoolDevice> getLargeDeviceUsed(String academyNumber, List<SchoolTerm> schoolTerms)
	{
		//构造学期查询字符串，例如(2,3)或者(2)
		StringBuffer terms = new StringBuffer("(");
		for (int i=0;i<schoolTerms.size();i++) 
		{
			if(i == schoolTerms.size()-1)
			{
				terms.append(schoolTerms.get(i).getId()+")");
			}
			else
			{
				terms.append(schoolTerms.get(i).getId()+",");
			}
		}
		
		StringBuffer hql = new StringBuffer("select distinct dr.labRoomDevice.schoolDevice from LabRoomDeviceReservation dr where 1=1 ");
		hql.append(" and dr.CDictionaryByCAuditResult.id=2 ");  //审核通过
		hql.append(" and dr.labRoomDevice.schoolDevice.devicePrice >= 100000 ");  //大型设备（大于等于10w）
		hql.append(" and dr.labRoomDevice.schoolDevice.schoolAcademy.academyNumber='"+academyNumber+"' ");  //学院
		hql.append(" and dr.schoolTerm.id in "+terms+" ");  //学期
		
		List<SchoolDevice> schoolDevices = schoolDeviceDAO.executeQuery(hql.toString(), 0, -1);
		
		if (schoolDevices!=null && schoolDevices.size()>0) {
			for (SchoolDevice schoolDevice : schoolDevices) {
				labRoomDeviceService.calculateHoursForSchoolDevice(schoolDevice.getDeviceNumber(), terms.toString());
			}
		}
		
		return schoolDevices;
	}

	/**
	 * 大型实验仪器设备台数使用率(绩效报表)
	 * @author hely
	 * 2014.08.06
	 */
	@Override
	public BigDecimal getLargeDeviceUsedRate(String academyNumber, List<SchoolTerm> schoolTerms) 
	{
		BigDecimal largeDeviceAmountByAcademy = new BigDecimal(getLargeDeviceAmountByAcademy(academyNumber)); //学院的大型设备数量
		BigDecimal largeDeviceUsedAmount = new BigDecimal(getLargeDeviceUsedAmount(academyNumber, schoolTerms)*100); //学院指定时间内使用的大型设备数量，乘以100是为了得到百分比
		try 
		{
			return largeDeviceUsedAmount.divide(largeDeviceAmountByAcademy, 2, BigDecimal.ROUND_HALF_UP);
		} catch (Exception e) {
			return new BigDecimal("0");
		}
	}

	/**
	 * 获取给定学期的下一个学期
	 * @author hely
	 * 2014.08.08
	 */
	@Override
	public SchoolTerm getNextSchoolTerm(SchoolTerm schoolTerm) 
	{
		SchoolTerm term = new SchoolTerm();
		
		StringBuffer hql = new StringBuffer("select s from SchoolTerm s order by s.termStart asc");
		List<SchoolTerm> schoolTerms = schoolTermDAO.executeQuery(hql.toString(), 0, -1);
		
		for (int i=0;i<schoolTerms.size();i++) 
		{
			if(schoolTerms.get(i).equals(schoolTerm))
			{
				if(i == schoolTerms.size()-1)
				{
					term = null;  //如果所给的学期是数据库最后一个学期，则没有下一个学期，返回null
				}
				else
				{
					term = schoolTerms.get(i+1);
				}
			}
		}
		
		return term;
	}

	/**
	 * 教学计划规定开设的实验数(report_parameter表中的数据)
	 * @author hely
	 * 2014.11.05
	 */
	@Override
	public int getPlanItemsAmount(String academyNumber, List<SchoolTerm> schoolTerms) 
	{
		int planItemsAmount = 0;
		
		return planItemsAmount;
	}

	/**
	 * 实际开出实验数量
	 * @author hely
	 * 2014.08.08
	 */
	@Override
	public int getRealItemsAmount(String academyNumber, List<SchoolTerm> schoolTerms) 
	{
		int realItemsAmount = 0;
		StringBuffer terms = new StringBuffer();
		for (SchoolTerm schoolTerm : schoolTerms) 
		{
			terms.append(schoolTerm.getId()+",");
		}
		if(terms.length() > 0)
		{
			terms.deleteCharAt(terms.length()-1);  //去掉最后的逗号
		}
		
		StringBuffer hql = new StringBuffer("select count(distinct oi) from TimetableItemRelated tir join tir.operationItem oi where 1=1 ");
		hql.append(" and oi.schoolAcademyByCollege.academyNumber='"+academyNumber+"' and tir.timetableAppointment.schoolCourseDetail.schoolTerm.id in ("+terms+") ");
		//自主排课
		StringBuffer hqlSelfCourse = new StringBuffer("select count(distinct tir.operationItem) from TimetableItemRelated tir where 1=1 ");
		hqlSelfCourse.append(" and tir.timetableAppointment.timetableSelfCourse.schoolTerm.id in ("+terms+") and tir.timetableAppointment.timetableSelfCourse.schoolAcademy='"+academyNumber+"'");
		
		realItemsAmount = ((Long)operationItemDAO.createQuerySingleResult(hql.toString()).getSingleResult()).intValue()+
				((Long)operationItemDAO.createQuerySingleResult(hqlSelfCourse.toString()).getSingleResult()).intValue();
		
		return realItemsAmount;
	}
	
	/**
	 * 实际开出实验项目列表
	 * @author hely
	 * 2014.09.17
	 */
	public List<OperationItem> getRealItems(String academyNumber, List<SchoolTerm> schoolTerms, int curr, int size)
	{
		StringBuffer terms = new StringBuffer();
		for (SchoolTerm schoolTerm : schoolTerms) 
		{
			terms.append(schoolTerm.getId()+",");
		}
		if(terms.length() > 0)
		{
			terms.deleteCharAt(terms.length()-1);  //去掉最后的逗号
		}
		
		StringBuffer hql = new StringBuffer("select distinct oi from TimetableItemRelated tir join tir.operationItem oi where 1=1 ");
		hql.append(" and oi.schoolAcademyByCollege.academyNumber=? and tir.timetableAppointment.schoolCourseDetail.schoolTerm.id in ("+terms.toString()+") ");
		
		return operationItemDAO.executeQuery(hql.toString(), curr*size, size, academyNumber);
	}

	/**
	 * 实验项目开出率(绩效报表)
	 * @author hely
	 * 2014.08.08
	 */
	@Override
	public BigDecimal getItemsRate(String academyNumber, List<SchoolTerm> schoolTerms) 
	{
		BigDecimal realItemsAmount = new BigDecimal(getRealItemsAmount(academyNumber, schoolTerms)*100);  //实际开出的实验数量，乘以100得百分比
		BigDecimal planItemsAmount = new BigDecimal(getRealItemsAmount(academyNumber, schoolTerms));  //教学计划规定开设的实验数
		
		try
		{
			return realItemsAmount.divide(planItemsAmount, 2, BigDecimal.ROUND_HALF_UP);  //两位小数
		}catch (Exception e) {
			return new BigDecimal("0");
		}
	}

	/**
	 * 学院在职教师数
	 * @author hely
	 * 2014.08.08
	 */
	@Override
	public int getTeacherAmountByAcademy(String academyNumber) 
	{
		int teacherAmount = 0;
		
		StringBuffer hql = new StringBuffer("select count(u) from User u where u.userRole='1' and u.userStatus='1' and u.enabled=true and u.schoolAcademy.academyNumber=?");
		teacherAmount = ((Long) (userDAO.createQuerySingleResult(hql.toString(), academyNumber).getSingleResult())).intValue();
		
		return teacherAmount;
	}

	/**
	 * 参加指导实验的教师人数
	 * @author hely
	 * 2014.12.09
	 */
	@Override
	public int getTeacherItemAmountByAcademy(String academyNumber, List<SchoolTerm> schoolTerms) 
	{
		return getTeacherItemByAcademy(academyNumber, schoolTerms).size();
	}
	
	/**
	 * 参加指导实验的教师
	 * @author hely
	 * 2014.12.09
	 */
	@Override
	public Set<User> getTeacherItemByAcademy(String academyNumber, List<SchoolTerm> schoolTerms)
	{
		List<User> teachers = new ArrayList<User>();
		Set<User> teachersSet = new HashSet<User>();  //去除重复数据
		StringBuffer terms = new StringBuffer("(");
		for (int i=0;i<schoolTerms.size();i++) 
		{
			if(i == schoolTerms.size()-1)
			{
				terms.append(schoolTerms.get(i).getId()+")");
			}
			else
			{
				terms.append(schoolTerms.get(i).getId()+",");
			}
		}
		
		StringBuffer hql_te = new StringBuffer("select distinct t.user from TimetableTeacherRelated t where 1=1 ");
		hql_te.append(" and t.user.schoolAcademy.academyNumber='"+academyNumber+"' ");  //学院
		hql_te.append(" and t.timetableAppointment.schoolCourseDetail.schoolTerm.id in "+terms);  //学期
		teachers.addAll(userDAO.executeQuery(hql_te.toString(), 0, -1));
		
		StringBuffer hql_tu = new StringBuffer("select distinct t.user from TimetableTutorRelated t where 1=1 ");
		hql_tu.append(" and t.user.schoolAcademy.academyNumber='"+academyNumber+"' ");  //学院
		hql_tu.append(" and t.timetableAppointment.schoolCourseDetail.schoolTerm.id in "+terms);  //学期
		teachers.addAll(userDAO.executeQuery(hql_tu.toString(), 0, -1));
		
		teachersSet.addAll(teachers);  //去除重复数据
		
		return teachersSet;
	}

	/**
	 * 教师参加指导实验占教师总人数的比例(绩效报表)
	 * @author hely
	 * 2014.08.08
	 */
	@Override
	public BigDecimal getTeacherItemRate(String academyNumber, List<SchoolTerm> schoolTerms) 
	{
		BigDecimal TeacherAmount = new BigDecimal(getTeacherAmountByAcademy(academyNumber)); //学院的教师数量
		BigDecimal teacherItemAmount = new BigDecimal(getTeacherItemAmountByAcademy(academyNumber, schoolTerms)*100); //参与指导实验项目的教师数量，乘以100是为了得到百分比
		try 
		{
			return teacherItemAmount.divide(TeacherAmount, 2, BigDecimal.ROUND_HALF_UP);
		} catch (Exception e) {
			return new BigDecimal("0");
		}
	}

	/**
	 * 学院实验室专职实验人数（report_parameter表中的数据）
	 * @author hely
	 * 2014.08.08
	 */
	@Override
	public int getLabAdminAmount(String academyNumber) 
	{
		int labAdminAmount = 0;
		
		StringBuffer hql = new StringBuffer("select count(distinct a.user) from LabRoomAdmin a where 1=1 ");
		if(academyNumber != null && !"".equals(academyNumber))
		{
			hql.append(" and a.labRoom.labAnnex.labCenter.schoolAcademy.academyNumber='"+academyNumber+"' ");
		}
		labAdminAmount = ((Long) (labRoomAdminDAO.createQuerySingleResult(hql.toString()).getSingleResult())).intValue();
		
		return labAdminAmount;
	}

	/**
	 * 实验室专职管理人员人均接待师生的人时数(绩效指标)
	 * @author hely
	 * 2014.08.08
	 */
	@Override
	public BigDecimal getLabAdminRate(String academyNumber, List<SchoolTerm> schoolTerms)
	{
		BigDecimal studentTimeSum = new BigDecimal(getStudentTimeByAcademy(academyNumber, schoolTerms)); //实验人时数,这是人均人时数，不是百分比，不用乘以100
		BigDecimal labAdminAmount = new BigDecimal(getLabAdminAmount(academyNumber));  //实验室专职管理人员数量
		
		try {
			return studentTimeSum.divide(labAdminAmount, 2, BigDecimal.ROUND_HALF_UP);
		} catch (Exception e) {
			return new BigDecimal("0");
		}
		
	}
	
	/**
	 * 实验室专职管理人员人均接待师生的人时数(通过公式中的数值直接计算，加快页面显示速度)
	 * @author hely
	 * 2014.09.17
	 */
	public BigDecimal getlabAdminRateSimple(BigDecimal studentTimeSum, BigDecimal labAdminAmount)
	{
		try {
			return studentTimeSum.divide(labAdminAmount, 2, BigDecimal.ROUND_HALF_UP);  //保留两位小数
		} catch (Exception e) {
			return new BigDecimal("0");
		}
	}
	
	/**
	 * 获取指定学院的大型综合性实验数量
	 * @param academyNumber  学院编号（如果为null或者""则返回全校所有的大型综合性实验数量）
	 * @author hely
	 * 2014.08.18
	 */
	@Override
	public int getComplexItemAmountByAcademy(String academyNumber) 
	{
		int complexItemAmount = 0;  //大型综合性实验数量
		
		StringBuffer hql = new StringBuffer("select count(oi) from OperationItem oi where 1=1 and oi.complex=? ");
		if(academyNumber != null && !"".equals(academyNumber))
		{
			hql.append(" and oi.schoolAcademyByCollege.academyNumber='"+academyNumber+"' ");
		}
		complexItemAmount = ((Long) operationItemDAO.createQuerySingleResult(hql.toString(), 1).getSingleResult()).intValue();
		
		return complexItemAmount;
	}
	
	/**
	 * 获取指定学院、指定学期的大型综合性实验数量
	 * @param academyNumber  学院编号（如果为null或者""则返回全校所有的大型综合性实验数量）
	 * @author hely
	 * 2014.08.18
	 */
	@Override
	public int getComplexItemAmountByTermAcademy(List<SchoolTerm> schoolTerms, String academyNumber)
	{
		StringBuffer terms = new StringBuffer();  //学期字符串，有id组成，形式如1,2
		for (SchoolTerm schoolTerm : schoolTerms) 
		{
			terms.append(schoolTerm.getId()+",");
		}
		if(terms.length() > 0)
		{
			terms.deleteCharAt(terms.length()-1);  //去掉最后的逗号
		}
		
		StringBuffer hql = new StringBuffer("select count(distinct oi) from TimetableItemRelated tir join tir.operationItem oi where 1=1 ");
		hql.append(" and tir.timetableAppointment.status=1 ");  //已发布的排课
		hql.append(" and oi.complex=1 ");  //1为大型综合性实验
		hql.append(" and tir.timetableAppointment.schoolCourseDetail.schoolTerm.id in ("+terms+") ");  //查询指定学期的数据
		
		if(academyNumber != null && !"".equals(academyNumber))
		{
			hql.append(" and oi.schoolAcademyByCollege.academyNumber='"+academyNumber+"' ");  //查询指定学院的数据
		}
		
		return ((Long) operationItemDAO.createQuerySingleResult(hql.toString()).getSingleResult()).intValue();
	}
	
	/**
	 * 获取指定学院、指定学期的大型综合性实验
	 * @param academyNumber  学院编号（如果为null或者""则返回全校所有的大型综合性实验）
	 * @author hely
	 * 2014.09.25
	 */
	@Override
	public List<OperationItem> getComplexItemByTermAcademy(List<SchoolTerm> schoolTerms, String academyNumber, int curr, int size)
	{
		StringBuffer terms = new StringBuffer();  //学期字符串，有id组成，形式如1,2
		for (SchoolTerm schoolTerm : schoolTerms) 
		{
			terms.append(schoolTerm.getId()+",");
		}
		if(terms.length() > 0)
		{
			terms.deleteCharAt(terms.length()-1);  //去掉最后的逗号
		}
		
		StringBuffer hql = new StringBuffer("select distinct oi from TimetableItemRelated tir join tir.operationItem oi where 1=1 ");
		hql.append(" and tir.timetableAppointment.status=1 ");  //已发布的排课
		hql.append(" and oi.complex=1 ");  //1为大型综合性实验
		hql.append(" and tir.timetableAppointment.schoolCourseDetail.schoolTerm.id in ("+terms+") ");  //查询指定学期的数据
		
		if(academyNumber != null && !"".equals(academyNumber))
		{
			hql.append(" and oi.schoolAcademyByCollege.academyNumber='"+academyNumber+"' ");  //查询指定学院的数据
		}
		
		return operationItemDAO.executeQuery(hql.toString(), curr*size, size);
	}

	/**
	 * 获取指定学院的专业数量（report_parameter表中的数据）
	 * @param academyNumber 学院编号（如果为null或者""则返回全校专业数量）
	 * @author hely
	 * 2014.08.18
	 */
	@Override
	public int getMajorAmountByAcademy(String academyNumber) 
	{
		int majorAmount = 0;  //专业数量
		
		StringBuffer hql = new StringBuffer("select count(m) from SchoolMajor m where 1=1 ");
		if(academyNumber != null && !"".equals(academyNumber))
		{
			hql.append(" and m.schoolAcademy.academyNumber='"+academyNumber+"' ");
		}
		majorAmount = ((Long) (schoolMajorDAO.createQuerySingleResult(hql.toString()).getSingleResult())).intValue();
		
		return majorAmount;
	}

	/**
	 * 独立实验课、大型综合性实验课占比(绩效指标)
	 * @param academyNumber 学院编号
	 * @author hely
	 * 2014.08.18
	 */
	@Override
	public BigDecimal getComplexItemRate(List<SchoolTerm> schoolTerms, String academyNumber) 
	{
		BigDecimal complexItemAmount = new BigDecimal(getComplexItemAmountByTermAcademy(schoolTerms, academyNumber)*100);  //学院的大型综合性实验数量，乘以100得百分比
		int myjorAmountByAcedemy = getMajorAmountByAcademy(academyNumber);  //学院的专业数量
		int allComplexItem = getComplexItemAmountByTermAcademy(schoolTerms, null);  //全校所有的大型综合性实验数量
		BigDecimal result = new BigDecimal(myjorAmountByAcedemy*allComplexItem);
		
		try
		{
			return complexItemAmount.divide(result, 2, BigDecimal.ROUND_HALF_UP);
		}catch (Exception e) {
			return new BigDecimal("0");
		}
	}

	/**
	 * 学院实际人才培养量（本科生+研究生）
	 * @param academyNumber 学院编号
	 * @author hely
	 * 2014.08.18
	 */
	@Override
	public int getStudentAmountRealTrain(String academyNumber, List<SchoolTerm> schoolTerms) 
	{
		int studentAmountRealTrain = 0;  //学院实际人才培养量
		
		studentAmountRealTrain = getUndergraduateAmount(academyNumber, schoolTerms) + getGraduateAmount(academyNumber, schoolTerms);
		
		return studentAmountRealTrain;
	}

	/**
	 * 学院实际人才培养量（本科生）
	 * @param academyNumber 学院编号
	 * @para schoolTerms 学期集合，可能是一个学期或者一个学年（两个学期）
	 * @author hely
	 * 2014.09.25
	 */
	public int getUndergraduateAmount(String academyNumber, List<SchoolTerm> schoolTerms)
	{
		int studentAmountRealTrain = 0;  //本科生数量
		int termSize = schoolTerms.size();
		StringBuffer startGrade = new StringBuffer();
		StringBuffer endGrade = new StringBuffer();
		
		StringBuffer hql = new StringBuffer("select count(u) from User u where u.userRole='0' ");  //0代表学生
		if(academyNumber != null && !"".equals(academyNumber))
		{
			hql.append(" and u.schoolAcademy.academyNumber='"+academyNumber+"' ");
		}
		if(termSize > 0)
		{
			startGrade.append(Integer.parseInt(schoolTerms.get(0).getYearCode())-3);  //大四学生入学年份
			endGrade.append(schoolTerms.get(0).getYearCode());  //大一学生入学年份
			hql.append(" and u.grade >='"+startGrade+"' and u.grade<='"+endGrade+"'");
		}
		studentAmountRealTrain = ((Long) (userDAO.createQuerySingleResult(hql.toString()).getSingleResult())).intValue();
		
		return studentAmountRealTrain*termSize;  //一个学年两个学期的在校人数时相同的
	}
	
	/**
	 * 学院实际人才培养量（研究生）
	 * @param academyNumber 学院编号
	 * @author hely
	 * 2014.09.25
	 */
	public int getGraduateAmount(String academyNumber, List<SchoolTerm> schoolTerms)
	{
		int studentAmountRealTrain = 0;  //研究生数量
		
		int termSize = schoolTerms.size();
		StringBuffer startGrade = new StringBuffer();
		StringBuffer endGrade = new StringBuffer();
		
		StringBuffer hql = new StringBuffer("select count(u) from User u where u.userRole='2' ");  //0代表学生
		if(academyNumber != null && !"".equals(academyNumber))
		{
			hql.append(" and u.schoolAcademy.academyNumber='"+academyNumber+"' ");
		}
		if(termSize > 0)
		{
			startGrade.append(Integer.parseInt(schoolTerms.get(0).getYearCode())-2);  //研三学生入学年份
			endGrade.append(schoolTerms.get(0).getYearCode());  //研一学生入学年份
			hql.append(" and u.grade >='"+startGrade+"' and u.grade<='"+endGrade+"'");
		}
		studentAmountRealTrain = ((Long) (userDAO.createQuerySingleResult(hql.toString()).getSingleResult())).intValue();
		
		return studentAmountRealTrain*termSize;
	}
	
	/**
	 * 学院额定人才培养量
	 * @param academyNumber 学院编号
	 * @author hely
	 * 2014.08.18
	 */
	@Override
	public BigDecimal getStudentAmountPlanTrain(String academyNumber) 
	{
		BigDecimal deviceCost = getDeviceCostByAcademy(academyNumber);  //学院低于10w设备的总经费
		BigDecimal bigDeviceCost = getBigDeviceCostByAcademy(academyNumber);  //学院大型设备的总经费
		BigDecimal subjectFactor = getSubjectFactorByAcademy(academyNumber);  //学科系数
		BigDecimal labRoomArea = getLabRoomAreaByAcademy(academyNumber); //学院实验室面积总和
		
		if(deviceCost.add(bigDeviceCost).compareTo(BigDecimal.ZERO) == 0)
		{
			return new BigDecimal("0");
		}
		
		BigDecimal numerator = (deviceCost.add(bigDeviceCost.multiply(new BigDecimal("0.8")))).add(labRoomArea.multiply(new BigDecimal("5000")));  //公式中的分子
		BigDecimal denominator = subjectFactor.multiply(new BigDecimal("30000"));  //公式中的分母,numerator中都是以万元为单位，所以这里要乘以10000
		
		try 
		{
			return numerator.divide(denominator, 0, BigDecimal.ROUND_HALF_UP);
		} catch (Exception e) 
		{
			return new BigDecimal("0");
		}
	}

	/**
	 * 低于10w的设备的经费总额
	 * @param academyNumber 学院编号
	 * @author hely
	 * 2014.08.18
	 */
	@Override
	public BigDecimal getDeviceCostByAcademy(String academyNumber) 
	{
		BigDecimal deviceCost = new BigDecimal("0");
		
		List<SchoolDevice> schoolDevices = getDeviceByAcademy(academyNumber);
		for (SchoolDevice schoolDevice : schoolDevices) 
		{
			deviceCost = deviceCost.add(schoolDevice.getDevicePrice());
		}
		
		return deviceCost;
	}

	/**
	 * 大型设备的经费总额(价格大于10w)
	 * @param academyNumber 学院编号
	 * @author hely
	 * 2014.08.18
	 */
	@Override
	public BigDecimal getBigDeviceCostByAcademy(String academyNumber) 
	{
		BigDecimal bigDeviceCost = new BigDecimal("0");

		List<SchoolDevice> schoolDevices = this.getLargeDeviceByAcademy(academyNumber, null);
		for (SchoolDevice schoolDevice : schoolDevices) 
		{
			bigDeviceCost = bigDeviceCost.add(schoolDevice.getDevicePrice());
		}
		
		return bigDeviceCost;
	}

	/**
	 * 指定学院的学科系数
	 * @param academyNumber 学院代码
	 * @author hely
	 * 2014.08.18
	 */
	@Override
	public BigDecimal getSubjectFactorByAcademy(String academyNumber) 
	{
		BigDecimal subjectFactor = new BigDecimal("0");
		
		StringBuffer hql = new StringBuffer("select rp from ReportParameter rp where rp.schoolAcademy.academyNumber=?");
		List<ReportParameter> reportParameters = reportParameterDAO.executeQuery(hql.toString(), 0, -1, academyNumber);
		if(reportParameters.size() > 0)
		{
			if(reportParameters.get(0).getSubjectFactor() != null)
			{
				subjectFactor = reportParameters.get(0).getSubjectFactor();  //根据学院查找里面只有一条数据
			}
		}
		
		return subjectFactor;
	}

	/**
	 * 学院实验室使用面积总和
	 * @param academyNumber 学院编号
	 * @author hely
	 * 2014.08.18
	 */
	@Override
	public BigDecimal getLabRoomAreaByAcademy(String academyNumber) 
	{
		BigDecimal labRoomArea = new BigDecimal("0");
		
		StringBuffer hql = new StringBuffer("select sum(lr.labRoomArea) from LabRoom lr where lr.labAnnex.labCenter.schoolAcademy.academyNumber='"+academyNumber+"'");
		try 
		{
			labRoomArea = new BigDecimal(labRoomDAO.createQuerySingleResult(hql.toString()).getSingleResult().toString());
		} catch (Exception e) {
			return new BigDecimal("0");
		}
		
		return labRoomArea.setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * 人才培养率(绩效指标)
	 * @param academyNumber 学院编号
	 * @author hely
	 * 2014.08.18
	 */
	@Override
	public BigDecimal getStudentTrainRate(String academyNumber, List<SchoolTerm> schoolTerms) 
	{
		BigDecimal studentAmountRealTrain = new BigDecimal(getStudentAmountRealTrain(academyNumber, schoolTerms)*100);  //学院实际人才培养量,返回的是百分比，所以要乘以100
		BigDecimal studentAmountPlanTrain = getStudentAmountPlanTrain(academyNumber);  //学院额定人才培养量
		
		try 
		{
			return studentAmountRealTrain.divide(studentAmountPlanTrain, 2, BigDecimal.ROUND_HALF_UP);
		} catch (Exception e) 
		{
			return new BigDecimal("0");
		}
	}

	/**
	 * 根据学院查找实验室分室
	 * @param academyNumber 学院编号(academyNumber为null或者空时，返回所有实验室分室)
	 * @author hely
	 * 2014.08.20
	 */
	@Override
	public List<LabRoom> getLabRoomByAcademy(String academyNumber) 
	{
		StringBuffer hql = new StringBuffer("select lr from LabRoom lr where 1=1 ");
		
		if(academyNumber != null && !"".equals(academyNumber))
		{
			hql.append(" and lr.labAnnex.labCenter.schoolAcademy.academyNumber='"+academyNumber+"' ");
		}
		hql.append(" order by lr.labRoomName");
		
		List<LabRoom> labRooms = labRoomDAO.executeQuery(hql.toString(), 0, -1);
		
		return labRooms;
	}

	/**
	 * 保存报表参数
	 * @param reportParameter 报表参数对象
	 * @author hely
	 * 2014.08.22
	 */
	@Override
	public void saveReportParameter(ReportParameter reportParameter) 
	{
		reportParameterDAO.store(reportParameter);
		reportParameterDAO.flush();
	}

	/**
	 * 获取所有报表参数
	 * @author hely
	 * 2014.08.22
	 */
	@Override
	public List<ReportParameter> getAllReportParameter() 
	{
		StringBuffer hql = new StringBuffer("select rp from ReportParameter rp order by rp.schoolAcademy.academyNumber ");
		return reportParameterDAO.executeQuery(hql.toString(), 0, -1);
	}

	/**
	 * 根据id查找报表参数
	 * @author hely
	 * 2014.08.22
	 */
	@Override
	public ReportParameter getReportParameterById(Integer id) 
	{
		return reportParameterDAO.findReportParameterById(id);
	}

	/**
	 * 根据id删除报表参数
	 * @param id 报表参数id
	 * @author hely
	 * 2014.08.22
	 */
	@Override
	public void deleteReportParameterById(Integer id) 
	{
		ReportParameter reportParameter = reportParameterDAO.findReportParameterById(id);
		if(reportParameter != null)
		{
			reportParameterDAO.remove(reportParameter);
			reportParameterDAO.flush();
		}
		
	}

	/**
	 * 根据学院和所给时间段获取实验室利用率公式中涉及到的数据
	 * @param academyNumber  学院编号
	 * @param schoolTerms  时间段
	 * @author hely
	 * 2014.08.26
	 */
	@Override
	public List<LabRoom> getLabRateDetailInfo(List<SchoolTerm> schoolTerms) 
	{
		List<LabRoom> labRooms = new ArrayList<LabRoom>(); //用于JSP页面遍历
		List<SchoolAcademy> schoolAcademies = getSchoolAcademyByQuery(); //找出需要计算绩效报表的学院
		
		for (SchoolAcademy schoolAcademy : schoolAcademies) 
		{
			LabRoom labRoom = new LabRoom();
			String academyNumber = schoolAcademy.getAcademyNumber(); //学院编号
			ReportRate reportRate =  getReportRateByTermsAcademy(academyNumber, schoolTerms);
			int studentTimeSum = 0; //实验人时数
			if(reportRate!=null && reportRate.getStudentTime() != null)
			{
				studentTimeSum = reportRate.getStudentTime();
			}
			int labRoomCapacity = getLabCapacityByAcademy(academyNumber); //实验室容量
			int ratedCourseTime = getRatedCourseTime(academyNumber); //实验室额定课时
			double ratedCourseTimeTerm = (schoolTerms.size() == 1)? ratedCourseTime/2.0:ratedCourseTime;
			
			labRoom.setLabRoomPhone(academyNumber); //学院编号 
			labRoom.setLabRoomName(schoolAcademy.getAcademyName()); //学院名称
			labRoom.setLabRoomArea(getLabRoomAreaByAcademy(academyNumber)); //实验室使用面积
			labRoom.setLabRoomNumber(getLabAvgArea(academyNumber)+""); //生均面积
			labRoom.setLabRoomCapacity(labRoomCapacity); //实验室容量
			labRoom.setLabRoomEnName(ratedCourseTimeTerm+""); //实验室额定课时数
			labRoom.setLabRoomAddress(studentTimeSum+""); //实验室人时数
			labRoom.setLabRoomPrizeInformation(reportRate==null?"0":reportRate.getLabRate().toString()); //学院实验室利用率
			
			labRooms.add(labRoom);
		}
		
		return labRooms;
	}

	/**
	 * 获得学院的生均面积
	 * @param academyNumber 学院编号
	 * @author hely
	 * 2014.08.26
	 */
	@Override
	public int getLabAvgArea(String academyNumber) 
	{
		int labAvgArea = 0;
		
        StringBuffer hql = new StringBuffer("select rp from ReportParameter rp where rp.schoolAcademy.academyNumber=?");
		
		List<ReportParameter> reportParameters = reportParameterDAO.executeQuery(hql.toString(), 0, -1, academyNumber);
		
		if(reportParameters.size() > 0)
		{
			labAvgArea = reportParameters.get(0).getLabAvgArea(); //根据学院查找数据只有一条
		}
		
		return labAvgArea;
	}

	/**
	 * 获取学院实验室容量总和
	 * @param academyNumber 学院编号
	 * @author hely
	 * 2014.08.26
	 */
	@Override
	public int getLabCapacityByAcademy(String academyNumber) 
	{
		int LabCapacitySum = 0;
		
		StringBuffer hql = new StringBuffer("select sum(lr.labRoomCapacity) from LabRoom lr where lr.labAnnex.labCenter.schoolAcademy.academyNumber='"+academyNumber+"'");
		try 
		{
			LabCapacitySum =  ((Long) labRoomDAO.createQuerySingleResult(hql.toString()).getSingleResult()).intValue();
		} catch (Exception e) {}
				
		return LabCapacitySum;
	}

	/**
	 * 指定学院的实验室人时数
	 * @param academyNumber 学院编号
	 * @param schoolTerms 时间段
	 * @author hely
	 * 2014.08.26
	 */
	@Override
	public int getStudentTimeByAcademy(String academyNumber, List<SchoolTerm> schoolTerms) 
	{
		int studentTimeSum = 0;  //该学院总实验人时数
		
		List<LabRoom> labRooms = getLabRoomByAcademy(academyNumber);
		for (LabRoom labRoom : labRooms) 
		{
			studentTimeSum += getStudentTime(labRoom.getId(), schoolTerms);  //实验人时数加总
		}
		
		return studentTimeSum;
	}

	/**
	 * 根据实验室id获取实验室名称、容量、利用率等信息
	 * @param id 实验室id
	 * @author hely
	 * 2014.08.27
	 */
	@Override
	public LabRoom getLabRoomInfoById(Integer id, int ratedCourseTime, List<SchoolTerm> schoolTerms) 
	{
		LabRoom labRoom = labRoomDAO.findLabRoomById(id);
		if(labRoom == null)
		{
			labRoom = new LabRoom();
			labRoom.setLabRoomPhone("0");
			return labRoom;
		}
		labRoom.setLabRoomPhone(getSingleLabRate(id, ratedCourseTime, schoolTerms));
		
		return labRoom;
	}
	
	/**
	 * 获取拥有分室的实验室信息
	 * @param labId 实验室id数组
	 * @param ratedCourseTime 额定课时数
	 * @param schoolTerms 学期
	 * @author hely
	 * 2014.12.03
	 */
	@Override
	public LabRoom getLabRoomInfoByLabs(int[] labIdArr, int ratedCourseTime, List<SchoolTerm> schoolTerms) 
	{
		LabRoom labRoom = null;
		for(int i=0;i<labIdArr.length;i++)
		{
			labRoom = labRoomDAO.findLabRoomByPrimaryKey(labIdArr[i]);
			
			if(labRoom != null)
			{
				break;
			}
		}
		
		if(labRoom != null)
		{
			labRoom.setLabRoomPhone(getLabRateLabAnnex(labIdArr, ratedCourseTime, schoolTerms));
		}
		
		return labRoom;
	}

	/**
	 * 根据学院查看所有的预约的列表安排
	 * @param academyNumber
	 * @param curr 当前页
	 * @param size 每页尺寸
	 * @author hely
	 * 2014.08.27
	 */
	@Override
	public List<TimetableAppointment> getTimetableAppointmentsByAcademy(String academyNumber, List<SchoolTerm> schoolTerms, int curr, int size) 
	{
		StringBuffer hql = new StringBuffer("select distinct ta from TimetableLabRelated tlr join tlr.timetableAppointment ta where 1=1 ");
		//hql.append(" and tlr.labRoom.labAnnex.labCenter.schoolAcademy.academyNumber=?1 ");
		hql.append(" and ta.status=?1 ");
		if(schoolTerms.size() > 0)
		{
			StringBuffer hqlTerm = new StringBuffer();
			
			for (SchoolTerm schoolTerm : schoolTerms) 
			{
				hqlTerm.append(schoolTerm.getId()+",");
			}
			hql.append(" and ta.schoolCourseDetail.schoolTerm.id in ("+hqlTerm.substring(0, hqlTerm.length()-1)+")");
		}
		// 按照课程排序
		hql.append(" order by ta.schoolCourse.courseNo,ta.courseCode ,ta.weekday desc ");
		List<TimetableAppointment> timetableAppointments = timetableAppointmentDAO.executeQuery(hql.toString(), curr * size, size,  1);
		
		return timetableAppointments;
	}

	/**
	 * 获取需要计算绩效报表的学院
	 * @author hely
	 * 2014.09.02
	 */
	@Override
	public List<SchoolAcademy> getSchoolAcademyByQuery() 
	{
		//StringBuffer hql = new StringBuffer("select sa from SchoolAcademy sa where (sa.academyNumber like '02__' and sa.academyNumber <> '0208' and sa.academyNumber between '0201' and '0213') or (sa.academyNumber='0225') ");
		StringBuffer hql = new StringBuffer("select sa from SchoolAcademy sa where 1=1");
		hql.append(" order by sa.academyNumber asc ");
		List<SchoolAcademy> schoolAcademies = schoolAcademyDAO.executeQuery(hql.toString(), 0, -1);
		
		return schoolAcademies;
	}

	/**
	 * 获取需要计算绩效报表学院的名称字符串
	 * @author hely
	 * 2014.09.04
	 */
	@Override
	public String getAcademyNames() 
	{
		StringBuffer academyNames = new StringBuffer();
		
		/*List<SchoolAcademy> schoolAcademies = getSchoolAcademyByQuery(); //获取需要计算绩效报表的学院
		for (SchoolAcademy schoolAcademy : schoolAcademies) 
		{
			academyNames.append("'"+schoolAcademy.getAcademyName()+"',");
		}*/
		
		List<LabCenter> labCenters = findAllLabCenter();
		for(LabCenter l:labCenters){
			academyNames.append("'"+l.getCenterName()+"',");
		}
		
		return academyNames.deleteCharAt(academyNames.length()-1).toString();
	}

	/**
	 * 根据指定学期查询绩效报表信息
	 * @param schoolTerms 学期
	 * @author hely
	 * 2014.09.04
	 */
	@Override
	public List<ReportRate> getReportRateByTerms(List<SchoolTerm> schoolTerms) 
	{
		StringBuffer terms = new StringBuffer();
		for (SchoolTerm schoolTerm : schoolTerms) 
		{
			terms.append(schoolTerm.getId()+",");
		}
		if(terms.length() > 0)
		{
			terms.deleteCharAt(terms.length()-1); //去掉最后一个逗号
		}
		
		//StringBuffer hql = new StringBuffer("select rr from ReportRate rr where rr.schoolAcademy.academyNumber like '0201' and rr.terms = '"+terms+"' order by rr.schoolAcademy.academyNumber ");
		StringBuffer hql = new StringBuffer("select rr from ReportRate rr where rr.terms = '"+terms+"'");
		List<LabCenter> labCenteres = findAllLabCenter();
		String academyStr = "";
		for(LabCenter l:labCenteres){
			academyStr += "'" + l.getSchoolAcademy().getAcademyNumber() + "',";
		}
		if(!academyStr.equals("")){
			academyStr = academyStr.substring(0, academyStr.length()-1);
		}
		hql.append("and rr.schoolAcademy.academyNumber in (" + academyStr + ")");
		hql.append(" order by rr.schoolAcademy.academyNumber ");
		List<ReportRate> reportRates = reportRateDAO.executeQuery(hql.toString(), 0, -1);
		
		return reportRates;
	}
	
	/**
	 * 获取指定学期的学院绩效指标数据
	 * @param academyNumber 学院编号
	 * @param schoolTerms 学期（学年或者单个学期）
	 * @author hely
	 * 2014.12.11
	 */
	@Override
	public ReportRate getReportRateByTermsAcademy(String academyNumber, List<SchoolTerm> schoolTerms)
	{
		StringBuffer terms = new StringBuffer();
		for (SchoolTerm schoolTerm : schoolTerms) 
		{
			terms.append(schoolTerm.getId()+",");
		}
		if(terms.length() > 0)
		{
			terms.deleteCharAt(terms.length()-1); //去掉最后一个逗号
		}
		
		StringBuffer hql = new StringBuffer("select rr from ReportRate rr where rr.terms = '"+terms+"' and rr.schoolAcademy.academyNumber='"+academyNumber+"' ");
		List<ReportRate> reportRates = reportRateDAO.executeQuery(hql.toString(), 0, -1);
		
		if(reportRates.size() > 0)
		{
			return reportRates.get(0);  //指定学院、指定学期的数据只有一条
		}
		return null;
	}

	/**
	 * 根据学期查找大型设备台数使用率详细信息
	 * @param schoolTerms 学期
	 * @author hely
	 * 2014.09.05
	 */
	@Override
	public List<LabRoom> getLargeDeviceUsedRateInfo(List<SchoolTerm> schoolTerms) 
	{
		List<LabRoom> labRooms = new ArrayList<LabRoom>(); //用于JSP页面遍历
		List<SchoolAcademy> schoolAcademies = getSchoolAcademyByQuery(); //找出需要计算绩效报表的学院
		
		for (SchoolAcademy schoolAcademy : schoolAcademies) 
		{
			LabRoom labRoom = new LabRoom();
			String academyNumber = schoolAcademy.getAcademyNumber();
			
			labRoom.setLabRoomPhone(academyNumber);
			labRoom.setLabRoomAddress(schoolAcademy.getAcademyName()); //学院名称
			labRoom.setLabRoomNumber(getLargeDeviceAmountByAcademy(academyNumber)+""); //大型设备总数
			labRoom.setLabRoomName(getLargeDeviceUsedAmount(academyNumber, schoolTerms)+""); //使用的大型设备数
			labRoom.setLabRoomEnName(getLargeDeviceUsedRate(academyNumber, schoolTerms).toString()); //大型设备台数利用率
			
			labRooms.add(labRoom);
		}
		
		return labRooms;
	}

	/**
	 * 根据学院编号获取学院信息
	 * @param academyNumber 学院编号
	 * @author hely
	 * 2014.09.10
	 */
	@Override
	public SchoolAcademy getAcademyByNumber(String academyNumber) 
	{
		SchoolAcademy schoolAcademy = schoolAcademyDAO.findSchoolAcademyByAcademyNumber(academyNumber);
		if(schoolAcademy != null)
		{
			return schoolAcademy;
		}
		
		return null;
	}

	/**
	 * 得到学年的数据，用于下拉框
	 * @author hely
	 * 2014.09.10
	 */
	@Override
	public Map<String, String> getTermsMap() 
	{
		StringBuffer hql = new StringBuffer("select t from SchoolTerm t group by t.yearCode order by t.termStart asc");
		List<SchoolTerm> schoolTerms = schoolTermDAO.executeQuery(hql.toString(), 0, -1);
		
		Map<String, String> termsMap = new LinkedHashMap<String, String>(); //使用LinkedHashMap是为了保证顺序和插入顺序一致
		termsMap.put("0", "---请选择---");
		for (SchoolTerm schoolTerm : schoolTerms) 
		{
			 //int Yearcode = Integer.parseInt(schoolTerm.getYearCode())+1;
			 termsMap.put(schoolTerm.getYearCode(), schoolTerm.getYearCode()+"学年");
			//termsMap.put(schoolTerm.getYearCode(), schoolTerm.getYearCode()+ "学年");
		}
		
		return termsMap;
	}
	
	/**
	 * 根据学年编号(year_code)获取学期
	 * @param yearCode 学年编号
	 * @author hely
	 * 2014.09.10
	 */
	public String getTermsByYearCode(String yearCode)
	{
		StringBuffer hql = new StringBuffer("select t from SchoolTerm t where t.yearCode='"+yearCode+"' order by t.termStart asc");
		List<SchoolTerm> shoolTerms = schoolTermDAO.executeQuery(hql.toString(), 0, -1);
		
		StringBuffer options = new StringBuffer();
		for (SchoolTerm schoolTerm : shoolTerms) 
		{
			options.append("<option value='"+schoolTerm.getId()+"'>"+schoolTerm.getTermName()+"</option>");
		}
		
		return options.toString();
	}

	/**
	 * 根据字符串获取学期，用于下拉框
	 * @param queryStr 形式如 1,4
	 * @param flag 为true则查出字符串所包含的学期，否则查出不包含的学期
	 * @author hely
	 * 2014.09.10
	 */
	@Override
	public Map<Integer, String> getSelectTerms(String queryStr, String yearCode, boolean flag) 
	{
		StringBuffer hql = new StringBuffer("select t from SchoolTerm t where 1=1 ");
		if(yearCode != null && !"".equals(yearCode))
		{
			hql.append(" and t.yearCode = '"+yearCode+"' ");
		}
		if(queryStr != null && !"".equals(queryStr))
		{
			if(flag)
			{
				hql.append(" and t.id in ("+queryStr+")");
			}
			else
			{
				hql.append(" and t.id not in ("+queryStr+")");
			}
		}
		List<SchoolTerm> schoolTerms = schoolTermDAO.executeQuery(hql.toString(), 0, -1);
		
		Map<Integer, String> termsMap = new LinkedHashMap<Integer, String>();
		for (SchoolTerm schoolTerm : schoolTerms) 
		{
			termsMap.put(schoolTerm.getId(), schoolTerm.getTermName());
		}
		
		return termsMap;
	}

	/**
	 * 根据字符串获取学期集合
	 * @param queryStr 形式如1,4
	 * @author hely
	 * 2014.09.10
	 */
	@Override
	public List<SchoolTerm> getSchoolTermsByStr(String queryStr) 
	{
		StringBuffer hql = new StringBuffer("select t from SchoolTerm t where 1=1 ");
		if(queryStr != null && !"".equals(queryStr))
		{
			hql.append(" and t.id in ("+queryStr+") ");
		}
		
	    return schoolTermDAO.executeQuery(hql.toString(), 0, -1);
	}

	/**
	 * 根据学期查找教师参与指导实验项目的比例详细信息
	 * @param schoolTerms 学期
	 * @author hely
	 * 2014.09.16
	 */
	@Override
	public List<LabRoom> getTeacherItemRateInfo(List<SchoolTerm> schoolTerms) 
	{
		List<LabRoom> labRooms = new ArrayList<LabRoom>(); //用于JSP页面遍历
		List<SchoolAcademy> schoolAcademies = getSchoolAcademyByQuery(); //找出需要计算绩效报表的学院
		
		for (SchoolAcademy schoolAcademy : schoolAcademies) 
		{
			LabRoom labRoom = new LabRoom();
			String academyNumber = schoolAcademy.getAcademyNumber();
			
			labRoom.setLabRoomPhone(academyNumber);
			labRoom.setLabRoomAddress(schoolAcademy.getAcademyName()); //学院名称
			labRoom.setLabRoomNumber(getTeacherAmountByAcademy(academyNumber)+""); //学院教师数量
			labRoom.setLabRoomName(getTeacherItemAmountByAcademy(academyNumber, schoolTerms)+""); //参与指导教师数量
			labRoom.setLabRoomEnName(getTeacherItemRate(academyNumber, schoolTerms).toString()); //比例
			
			labRooms.add(labRoom);
		}
		
		return labRooms;
	}

	/**
	 * 根据学期查找大型设备机时利用率详细信息
	 * @param schoolTerms 学期
	 * @author hely
	 * 2014.09.16
	 */
	@Override
	public List<LabRoom> getLargeDeviceTimeRateInfo(List<SchoolTerm> schoolTerms) 
	{
		List<LabRoom> labRooms = new ArrayList<LabRoom>(); //用于JSP页面遍历
		List<SchoolAcademy> schoolAcademies = getSchoolAcademyByQuery(); //找出需要计算绩效报表的学院
		
		for (SchoolAcademy schoolAcademy : schoolAcademies) 
		{
			LabRoom labRoom = new LabRoom();
			String academyNumber = schoolAcademy.getAcademyNumber();
			
			labRoom.setLabRoomPhone(academyNumber);
			labRoom.setLabRoomAddress(schoolAcademy.getAcademyName()); //学院名称
			labRoom.setLabRoomNumber(getLargeDeviceSumTime(academyNumber, schoolTerms)+""); //设备机时
			labRoom.setLabRoomName(getLargeDeviceAmountByAcademy(academyNumber)+""); //大型仪器设备数
			int largeDeviceAvgTime = getLargeDeviceAvgTime(academyNumber);
			//如果限定时间为一个学期，则设备年平均机时应该除以2，因为数据库存储的是整个学年的时间
			double largeDeviceAvgTimeTerm = (schoolTerms.size() == 1)? largeDeviceAvgTime/2.0:largeDeviceAvgTime;
			labRoom.setLabRoomPrizeInformation(largeDeviceAvgTimeTerm+"");  //平均机时
			labRoom.setLabRoomEnName(getLargeDeviceTimeRate(academyNumber, schoolTerms)+""); //设备平均机时利用率
			
			labRooms.add(labRoom);
		}
		
		return labRooms;
	}

	/**
	 * 根据学期查找实验室专职管理人员接待人时数
	 * @param schoolTerms 学期
	 * @author hely
	 * 2014.09.17
	 */
	@Override
	public List<LabRoom> getLabAdminRateInfo(List<SchoolTerm> schoolTerms) 
	{
		List<LabRoom> labRooms = new ArrayList<LabRoom>(); //用于JSP页面遍历
		List<SchoolAcademy> schoolAcademies = getSchoolAcademyByQuery(); //找出需要计算绩效报表的学院
		
		for (SchoolAcademy schoolAcademy : schoolAcademies) 
		{
			LabRoom labRoom = new LabRoom();
			String academyNumber = schoolAcademy.getAcademyNumber(); //学院编号
			int studentTimeSum = getStudentTimeByAcademy(academyNumber, schoolTerms); //实验人时数
			int labRoomCapacity = getLabCapacityByAcademy(academyNumber); //实验室容量
			int ratedCourseTime = getRatedCourseTime(academyNumber); //实验室额定课时
			double ratedCourseTimeTerm = (schoolTerms.size() == 1)? ratedCourseTime/2.0:ratedCourseTime;
			
			labRoom.setLabRoomPhone(academyNumber); //学院编号 
			labRoom.setLabRoomName(schoolAcademy.getAcademyName()); //学院名称
			labRoom.setLabRoomAddress(studentTimeSum+""); //实验室人时数
			labRoom.setLabRoomCapacity(labRoomCapacity); //实验室容量
			labRoom.setLabRoomEnName(ratedCourseTimeTerm+""); //实验室额定课时数
			labRoom.setLabRoomNumber(getLabAdminAmount(academyNumber)+""); //实验室专职管理人员数量
			labRoom.setLabRoomPrizeInformation(""+getlabAdminRateSimple(new BigDecimal(studentTimeSum), new BigDecimal(getLabAdminAmount(academyNumber)))); //实验室专职管理人员接待人时数
			
			labRooms.add(labRoom);
		}
		
		return labRooms;
	}

	/**
	 * 根据学期查找项目开出率详细信息
	 * @param schoolTerms 学期
	 * @author hely
	 * 2014.09.17
	 */
	@Override
	public List<LabRoom> getItemsRateInfo(List<SchoolTerm> schoolTerms) 
	{
		List<LabRoom> labRooms = new ArrayList<LabRoom>(); //用于JSP页面遍历
		List<SchoolAcademy> schoolAcademies = getSchoolAcademyByQuery(); //找出需要计算绩效报表的学院
		
		for (SchoolAcademy schoolAcademy : schoolAcademies) 
		{
			LabRoom labRoom = new LabRoom();
			String academyNumber = schoolAcademy.getAcademyNumber(); //学院编号
			
			labRoom.setLabRoomPhone(academyNumber); //学院编号 
			labRoom.setLabRoomName(schoolAcademy.getAcademyName()); //学院名称
			labRoom.setLabRoomAddress(getRealItemsAmount(academyNumber, schoolTerms)+""); //实际开出实验项目数量
			labRoom.setLabRoomCapacity(getPlanItemsAmount(academyNumber, schoolTerms)); //计划开设实验项目数量
			labRoom.setLabRoomEnName(getItemsRate(academyNumber, schoolTerms).toString()); //实验项目开出率
			
			labRooms.add(labRoom);
		}
		
		return labRooms;
	}

	/**
	 * 根据学期查找独立、大型综合性实验课占比详细信息
	 * @param schoolTerms 学期
	 * @author hely
	 * 2014.09.25
	 */
	@Override
	public List<LabRoom> getComplexItemRateInfo(List<SchoolTerm> schoolTerms) 
	{
		List<LabRoom> labRooms = new ArrayList<LabRoom>(); //用于JSP页面遍历
		List<SchoolAcademy> schoolAcademies = getSchoolAcademyByQuery(); //找出需要计算绩效报表的学院
		
		for (SchoolAcademy schoolAcademy : schoolAcademies) 
		{
			LabRoom labRoom = new LabRoom();
			String academyNumber = schoolAcademy.getAcademyNumber(); //学院编号
			
			labRoom.setLabRoomPhone(academyNumber); //学院编号 
			labRoom.setLabRoomName(schoolAcademy.getAcademyName()); //学院名称
			labRoom.setLabRoomAddress(getComplexItemAmountByTermAcademy(schoolTerms, academyNumber)+""); //指定学院大型综合实验项目
			labRoom.setLabRoomNumber(getMajorAmountByAcademy(academyNumber)+"");  //指定学院专业数量
			labRoom.setLabRoomCapacity(getComplexItemAmountByTermAcademy(schoolTerms, null)); //全校大型综合实验项目
			labRoom.setLabRoomEnName(getComplexItemRate(schoolTerms, academyNumber).toString()); //独立实验课、大型综合性实验课比例
			
			labRooms.add(labRoom);
		}
		
		return labRooms;
	}
	
	/**
	 * 根据学期查找人才培养率详细信息
	 * @param schoolTerms 学期
	 * @author hely
	 * 2014.09.25
	 */
	@Override
	public List<LabRoom> getStudentTrainRateInfo(List<SchoolTerm> schoolTerms)
	{
		List<LabRoom> labRooms = new ArrayList<LabRoom>(); //用于JSP页面遍历
		List<SchoolAcademy> schoolAcademies = getSchoolAcademyByQuery(); //找出需要计算绩效报表的学院
		
		for (SchoolAcademy schoolAcademy : schoolAcademies) 
		{
			LabRoom labRoom = new LabRoom();
			String academyNumber = schoolAcademy.getAcademyNumber(); //学院编号
			
			labRoom.setLabRoomPhone(academyNumber); //学院编号 
			labRoom.setLabRoomName(schoolAcademy.getAcademyName()); //学院名称
			labRoom.setLabRoomAddress(getUndergraduateAmount(academyNumber, schoolTerms)+""); //当前本科生数量
			labRoom.setLabRoomCapacity(getGraduateAmount(academyNumber, schoolTerms)); //当前研究生数量
			labRoom.setLabRoomNumber(getDeviceCostByAcademy(academyNumber)+"");  //现有设备经费额（10万以下）
			labRoom.setLabRoomEnName(getBigDeviceCostByAcademy(academyNumber)+""); //现有设备经费额（10万以上）
			labRoom.setLabRoonAbbreviation(getLabRoomAreaByAcademy(academyNumber)+""); //实验室使用面积
			labRoom.setLabRoomPrizeInformation(getSubjectFactorByAcademy(academyNumber)+""); //学科系数
			labRoom.setLabRoomManagerAgencies(getStudentTrainRate(academyNumber, schoolTerms)+""); //人才培养效率
			
			labRooms.add(labRoom);
		}
		
		return labRooms;
	}
	
	/**
	 * 获取指定学年的第一个学期
	 * @param yearCode 学年代码
	 * @author hely
	 * 2014.09.26
	 */
	@Override
	public SchoolTerm getFirstTerm(String yearCode)
	{
		StringBuffer hql = new StringBuffer("select t from SchoolTerm t where t.yearCode='"+yearCode+"' and t.termCode='1'");
		List<SchoolTerm> schoolTerms = schoolTermDAO.executeQuery(hql.toString(), 0, -1);
		if(schoolTerms.size() > 0)
		{
			return schoolTerms.get(0);  //schoolTerms里只有一个结果
		}
		else
		{
			return null;
		}
	}

	/**
	 * 计算指定学期的综合指数存入report_rate表
	 * @param schoolTerms 学期
	 * @author hely
	 * 2014.09.26
	 */
	@Override
	public void computeRateResult() 
	{
		List<SchoolTerm> schoolTerms = new ArrayList<SchoolTerm>();
		SchoolTerm currTerm = shareService.getBelongsSchoolTerm(Calendar.getInstance());  //当前学期
		schoolTerms.add(currTerm);
		double labRateSum=0, largeDeviceTimeRateSum=0, largeDeviceUsedRateSum=0, labAdminRateSum=0;
		double itemsRateSum=0, teacherItemRateSum=0, complexItemRateSum=0, studentTrainRateSum=0;
		
		StringBuffer hql = new StringBuffer("select rr from ReportRate rr where rr.terms='"+currTerm.getId()+"' ");
		List<ReportRate> reportRates = reportRateDAO.executeQuery(hql.toString(), 0, -1);
		
		if(reportRates.size() > 0)
		{
			//实验室利用率汇总
			StringBuffer hqlLabRate = new StringBuffer("select sum(rr.labRate) from ReportRate rr where rr.terms='"+currTerm.getId()+"' ");
			labRateSum = (Double) reportRateDAO.createQuerySingleResult(hqlLabRate.toString()).getSingleResult();
			//大型设备平均机时利用率汇总
			StringBuffer hqlLargeDeviceTimeRate = new StringBuffer("select sum(rr.largeDeviceTimeRate) from ReportRate rr where rr.terms='"+currTerm.getId()+"' ");
			largeDeviceTimeRateSum = (Double) reportRateDAO.createQuerySingleResult(hqlLargeDeviceTimeRate.toString()).getSingleResult();
			//大型设备台数利用率汇总
			StringBuffer hqlLargeDeviceUsedRate = new StringBuffer("select sum(rr.largeDeviceUsedRate) from ReportRate rr where rr.terms='"+currTerm.getId()+"' ");
			largeDeviceUsedRateSum = (Double) reportRateDAO.createQuerySingleResult(hqlLargeDeviceUsedRate.toString()).getSingleResult();
			//实验室专职管理人员人均人时数汇总
			StringBuffer hqlLabAdminRate = new StringBuffer("select sum(rr.labAdminRate) from ReportRate rr where rr.terms='"+currTerm.getId()+"' ");
			labAdminRateSum = (Double) reportRateDAO.createQuerySingleResult(hqlLabAdminRate.toString()).getSingleResult();
			//项目开出率汇总
			StringBuffer hqlItemsRate = new StringBuffer("select sum(rr.itemsRate) from ReportRate rr where rr.terms='"+currTerm.getId()+"' ");
			itemsRateSum = (Double) reportRateDAO.createQuerySingleResult(hqlItemsRate.toString()).getSingleResult();
			//教师参与指导实验比例汇总
			StringBuffer hqlTeacherItemRate = new StringBuffer("select sum(rr.teacherItemRate) from ReportRate rr where rr.terms='"+currTerm.getId()+"' ");
			teacherItemRateSum = (Double) reportRateDAO.createQuerySingleResult(hqlTeacherItemRate.toString()).getSingleResult();
			//大型综合性实验比例汇总
			StringBuffer hqlComplexItemRate = new StringBuffer("select sum(rr.complexItemRate) from ReportRate rr where rr.terms='"+currTerm.getId()+"' ");
			complexItemRateSum = (Double) reportRateDAO.createQuerySingleResult(hqlComplexItemRate.toString()).getSingleResult();
			//人才培养率汇总
			StringBuffer hqlStudentTrainRate = new StringBuffer("select sum(rr.studentTrainRate) from ReportRate rr where rr.terms='"+currTerm.getId()+"' ");
			studentTrainRateSum = (Double) reportRateDAO.createQuerySingleResult(hqlStudentTrainRate.toString()).getSingleResult();
			
			for (ReportRate reportRate : reportRates) 
			{
				double score = reportRate.getLabRate().doubleValue()/labRateSum+
						       reportRate.getLargeDeviceTimeRate().doubleValue()/largeDeviceTimeRateSum+
						       reportRate.getLargeDeviceUsedRate().doubleValue()/largeDeviceUsedRateSum+
						       reportRate.getLabAdminRate().doubleValue()/labAdminRateSum+
						       reportRate.getItemsRate().doubleValue()/itemsRateSum+
						       reportRate.getTeacherItemRate().doubleValue()/teacherItemRateSum+
						       reportRate.getComplexItemRate().doubleValue()/complexItemRateSum+
						       reportRate.getStudentTrainRate().doubleValue()/studentTrainRateSum;
				
				reportRate.setScore(new BigDecimal(score));
				reportRateDAO.store(reportRate);
				reportRateDAO.flush();
			}
			
			StringBuffer hqlRank = new StringBuffer("select rr from ReportRate rr where rr.terms='"+currTerm.getId()+"' order by rr.score desc");
			List<ReportRate> reportRateRanks = reportRateDAO.executeQuery(hqlRank.toString(), 0, -1);
			for(int i=0;i<reportRateRanks.size();i++)
			{
				reportRateRanks.get(i).setRank(i+1);
				
				reportRateDAO.store(reportRateRanks.get(i));
				reportRateDAO.flush();
			}
		}
		
		if(currTerm.getTermCode() == 2)
		{
			SchoolTerm firstTerm = getFirstTerm(currTerm.getYearCode());  //本学年的第一个学期
			schoolTerms.add(firstTerm);  
			String termStr = firstTerm.getId()+","+currTerm.getId();
			
			double labRate=0, largeDeviceTimeRate=0, largeDeviceUsedRate=0, labAdminRate=0;
			double itemsRate=0, teacherItemRate=0, complexItemRate=0, studentTrainRate=0;
			
			StringBuffer hqlTerms = new StringBuffer("select rr from ReportRate rr where rr.terms='"+termStr+"' ");
			List<ReportRate> reportRateTerm = reportRateDAO.executeQuery(hqlTerms.toString(), 0, -1);
			
			if(reportRateTerm.size() > 0)
			{
				//实验室利用率汇总
				StringBuffer hqlLabRate = new StringBuffer("select sum(rr.labRate) from ReportRate rr where rr.terms='"+termStr+"' ");
				labRate = (Double) reportRateDAO.createQuerySingleResult(hqlLabRate.toString()).getSingleResult();
				//大型设备平均机时利用率汇总
				StringBuffer hqlLargeDeviceTimeRate = new StringBuffer("select sum(rr.largeDeviceTimeRate) from ReportRate rr where rr.terms='"+termStr+"' ");
				largeDeviceTimeRate = (Double) reportRateDAO.createQuerySingleResult(hqlLargeDeviceTimeRate.toString()).getSingleResult();
				//大型设备台数利用率汇总
				StringBuffer hqlLargeDeviceUsedRate = new StringBuffer("select sum(rr.largeDeviceUsedRate) from ReportRate rr where rr.terms='"+termStr+"' ");
				largeDeviceUsedRate = (Double) reportRateDAO.createQuerySingleResult(hqlLargeDeviceUsedRate.toString()).getSingleResult();
				//实验室专职管理人员人均人时数汇总
				StringBuffer hqlLabAdminRate = new StringBuffer("select sum(rr.labAdminRate) from ReportRate rr where rr.terms='"+termStr+"' ");
				labAdminRate = (Double) reportRateDAO.createQuerySingleResult(hqlLabAdminRate.toString()).getSingleResult();
				//项目开出率汇总
				StringBuffer hqlItemsRate = new StringBuffer("select sum(rr.itemsRate) from ReportRate rr where rr.terms='"+termStr+"' ");
				itemsRate = (Double) reportRateDAO.createQuerySingleResult(hqlItemsRate.toString()).getSingleResult();
				//教师参与指导实验比例汇总
				StringBuffer hqlTeacherItemRate = new StringBuffer("select sum(rr.teacherItemRate) from ReportRate rr where rr.terms='"+termStr+"' ");
				teacherItemRate = (Double) reportRateDAO.createQuerySingleResult(hqlTeacherItemRate.toString()).getSingleResult();
				//大型综合性实验比例汇总
				StringBuffer hqlComplexItemRate = new StringBuffer("select sum(rr.complexItemRate) from ReportRate rr where rr.terms='"+termStr+"' ");
				complexItemRate = (Double) reportRateDAO.createQuerySingleResult(hqlComplexItemRate.toString()).getSingleResult();
				//人才培养率汇总
				StringBuffer hqlStudentTrainRate = new StringBuffer("select sum(rr.studentTrainRate) from ReportRate rr where rr.terms='"+termStr+"' ");
				studentTrainRate = (Double) reportRateDAO.createQuerySingleResult(hqlStudentTrainRate.toString()).getSingleResult();
				
				for (ReportRate reportRate : reportRateTerm) 
				{
					double score = reportRate.getLabRate().doubleValue()/labRate+
							       reportRate.getLargeDeviceTimeRate().doubleValue()/largeDeviceTimeRate+
							       reportRate.getLargeDeviceUsedRate().doubleValue()/largeDeviceUsedRate+
							       reportRate.getLabAdminRate().doubleValue()/labAdminRate+
							       reportRate.getItemsRate().doubleValue()/itemsRate+
							       reportRate.getTeacherItemRate().doubleValue()/teacherItemRate+
							       reportRate.getComplexItemRate().doubleValue()/complexItemRate+
							       reportRate.getStudentTrainRate().doubleValue()/studentTrainRate;
					
					reportRate.setScore(new BigDecimal(score));
					reportRateDAO.store(reportRate);
					reportRateDAO.flush();
				}
				
				StringBuffer hqlRank = new StringBuffer("select rr from ReportRate rr where rr.terms='"+termStr+"' order by rr.score desc");
				List<ReportRate> reportRateRanks = reportRateDAO.executeQuery(hqlRank.toString(), 0, -1);
				for(int i=0;i<reportRateRanks.size();i++)
				{
					reportRateRanks.get(i).setRank(i+1);
					
					reportRateDAO.store(reportRateRanks.get(i));
					reportRateDAO.flush();
				}
			}
		}
	}

	/**
	 * 将各个绩效指标存入report_rate表
	 * @author hely
	 * 2014.09.26
	 */
	@Override
	public void storeRates() 
	{
		List<SchoolTerm> schoolTerms = new ArrayList<SchoolTerm>();
		SchoolTerm currTerm = shareService.getBelongsSchoolTerm(Calendar.getInstance());  //获取当前学期
		schoolTerms.add(currTerm);
		
		StringBuffer hql = new StringBuffer("select rr from ReportRate rr where rr.terms='"+currTerm.getId()+"' ");
		List<ReportRate> reportRates = reportRateDAO.executeQuery(hql.toString(), 0, -1);
		
		//report_rate表中已经存在相应记录，则对记录进行更新，否则新建记录
		if(reportRates.size() > 0)
		{
			for (ReportRate reportRate : reportRates) 
			{
				String academyNumber = reportRate.getSchoolAcademy().getAcademyNumber();  //学院编号
				
				reportRate.setLabRate(getAcademyLabRate(academyNumber, schoolTerms));  //实验室利用率
				reportRate.setStudentTime(getStudentTimeByAcademy(academyNumber, schoolTerms));  //实验室人时数
				reportRate.setLargeDeviceTimeRate(getLargeDeviceTimeRate(academyNumber, schoolTerms));  //大型设备平均机时利用率
				reportRate.setLargeDeviceUsedRate(getLargeDeviceUsedRate(academyNumber, schoolTerms));  //大型设备台数使用利用率
				reportRate.setLabAdminRate(getLabAdminRate(academyNumber, schoolTerms));  //实验室专职人员平均接待人时数
				reportRate.setItemsRate(getItemsRate(academyNumber, schoolTerms));  //实验项目开出率
				reportRate.setTeacherItemRate(getTeacherItemRate(academyNumber, schoolTerms));  //教师参与指导实验比例
				reportRate.setComplexItemRate(getComplexItemRate(schoolTerms, academyNumber));  //大型综合性实验比例
				reportRate.setStudentTrainRate(getStudentTrainRate(academyNumber, schoolTerms));  //人才培养率
				//实验人时数
				int studentTimeSum = getStudentTimeByAcademy(academyNumber, schoolTerms);
				reportRate.setStudentTime(studentTimeSum);
				reportRateDAO.store(reportRate);
				reportRateDAO.flush();
			}
		}
		else
		{
			List<SchoolAcademy> schoolAcademies = getSchoolAcademyByQuery();
			for (SchoolAcademy schoolAcademy : schoolAcademies) 
			{
				ReportRate reportRate = new ReportRate();
				String academyNumber = schoolAcademy.getAcademyNumber();
				
				reportRate.setSchoolAcademy(schoolAcademy);
				reportRate.setTerms(String.valueOf(currTerm.getId()));  //学期id组成的字符串，逗号隔开
				reportRate.setLabRate(getAcademyLabRate(academyNumber, schoolTerms));  //实验室利用率
				reportRate.setLargeDeviceTimeRate(getLargeDeviceTimeRate(academyNumber, schoolTerms));  //大型设备平均机时利用率
				reportRate.setLargeDeviceUsedRate(getLargeDeviceUsedRate(academyNumber, schoolTerms));  //大型设备台数使用利用率
				reportRate.setLabAdminRate(getLabAdminRate(academyNumber, schoolTerms));  //实验室专职人员平均接待人时数
				reportRate.setItemsRate(getItemsRate(academyNumber, schoolTerms));  //实验项目开出率
				reportRate.setTeacherItemRate(getTeacherItemRate(academyNumber, schoolTerms));  //教师参与指导实验比例
				reportRate.setComplexItemRate(getComplexItemRate(schoolTerms, academyNumber));  //大型综合性实验比例
				reportRate.setStudentTrainRate(getStudentTrainRate(academyNumber, schoolTerms));  //人才培养率
                //实验人时数
				int studentTimeSum = getStudentTimeByAcademy(academyNumber, schoolTerms);
				reportRate.setStudentTime(studentTimeSum);
				reportRateDAO.store(reportRate);
				reportRateDAO.flush();
			}
			
		}
		
		//如果当前学期为该学年的第二个学期，则还需要计算本学年的各种绩效指标
		if(currTerm.getTermCode() == 2)
		{
			SchoolTerm firstTerm = getFirstTerm(currTerm.getYearCode());  //本学年的第一个学期
			schoolTerms.add(firstTerm);  
			String termStr = firstTerm.getId()+","+currTerm.getId();  //构造学期查询条件
			
			StringBuffer hqls = new StringBuffer("select rr from ReportRate rr where rr.terms='"+termStr+"' ");
			List<ReportRate> reportRateTerms = reportRateDAO.executeQuery(hqls.toString(), 0, -1);
			
			if(reportRateTerms.size() > 0)
			{
				for (ReportRate reportRate : reportRateTerms) 
				{
					String academyNumber = reportRate.getSchoolAcademy().getAcademyNumber();  //学院编号
					
					reportRate.setLabRate(getAcademyLabRate(academyNumber, schoolTerms));  //实验室利用率
					reportRate.setLargeDeviceTimeRate(getLargeDeviceTimeRate(academyNumber, schoolTerms));  //大型设备平均机时利用率
					reportRate.setLargeDeviceUsedRate(getLargeDeviceUsedRate(academyNumber, schoolTerms));  //大型设备台数使用利用率
					reportRate.setLabAdminRate(getLabAdminRate(academyNumber, schoolTerms));  //实验室专职人员平均接待人时数
					reportRate.setItemsRate(getItemsRate(academyNumber, schoolTerms));  //实验项目开出率
					reportRate.setTeacherItemRate(getTeacherItemRate(academyNumber, schoolTerms));  //教师参与指导实验比例
					reportRate.setComplexItemRate(getComplexItemRate(schoolTerms, academyNumber));  //大型综合性实验比例
					reportRate.setStudentTrainRate(getStudentTrainRate(academyNumber, schoolTerms));  //人才培养率
					
					reportRateDAO.store(reportRate);
					reportRateDAO.flush();
				}
			}
			else
			{
				List<SchoolAcademy> schoolAcademies = getSchoolAcademyByQuery();
				for (SchoolAcademy schoolAcademy : schoolAcademies) 
				{
					ReportRate reportRate = new ReportRate();
					String academyNumber = schoolAcademy.getAcademyNumber();
					
					reportRate.setSchoolAcademy(schoolAcademy);
					reportRate.setTerms(termStr);  //学期id组成的字符串，逗号隔开
					reportRate.setLabRate(getAcademyLabRate(academyNumber, schoolTerms));  //实验室利用率
					reportRate.setLargeDeviceTimeRate(getLargeDeviceTimeRate(academyNumber, schoolTerms));  //大型设备平均机时利用率
					reportRate.setLargeDeviceUsedRate(getLargeDeviceUsedRate(academyNumber, schoolTerms));  //大型设备台数使用利用率
					reportRate.setLabAdminRate(getLabAdminRate(academyNumber, schoolTerms));  //实验室专职人员平均接待人时数
					reportRate.setItemsRate(getItemsRate(academyNumber, schoolTerms));  //实验项目开出率
					reportRate.setTeacherItemRate(getTeacherItemRate(academyNumber, schoolTerms));  //教师参与指导实验比例
					reportRate.setComplexItemRate(getComplexItemRate(schoolTerms, academyNumber));  //大型综合性实验比例
					reportRate.setStudentTrainRate(getStudentTrainRate(academyNumber, schoolTerms));  //人才培养率
					
					reportRateDAO.store(reportRate);
					reportRateDAO.flush();
				}
			}
		}
		
	}
	
	/**
	 * 将综合指数和绩效排名存入report_rate表
	 * @author hely
	 * 2014.11.10
	 */
	public void storeRank()
	{
		//找出还没有综合指标和绩效排名的记录，主要为了获取terms，所以使用group by
		StringBuffer hql = new StringBuffer("select rr from ReportRate rr group by rr.terms");
		List<ReportRate> rates = reportRateDAO.executeQuery(hql.toString(), 0, -1);
		
		for (ReportRate currRate : rates) 
		{
			String terms = currRate.getTerms(); //学期
			//获取需要计算的、指定学期或者学年的记录
			StringBuffer rateHql = new StringBuffer("select rr from ReportRate rr where rr.terms='"+terms+"'");
			List<ReportRate> reportRates = reportRateDAO.executeQuery(rateHql.toString(), 0, -1);
			double labRate = 0;  //实验室利用率
			double largeDeviceTimeRate = 0;  //大型设备平均机时利用率
			double largeDeviceUsedRate = 0;  //大型设备台数使用率
			double itemsRate = 0;  //实验项目开出率
			double teacherItemRate = 0;  //教师参与指导实验比例
			double labAdminRate = 0;  //实验室专职管理员平均人时数
			double complexItemRate = 0;  //大型综合性实验比例
			double studentTrainRate = 0;  //人才培养率
			
			//将每个绩效指标进行汇总
			for (ReportRate reportRate : reportRates) 
			{
				labRate += reportRate.getLabRate().doubleValue();
				largeDeviceTimeRate += reportRate.getLargeDeviceTimeRate().doubleValue();
				largeDeviceUsedRate += reportRate.getLargeDeviceUsedRate().doubleValue();
				itemsRate += reportRate.getItemsRate().doubleValue();
				teacherItemRate += reportRate.getTeacherItemRate().doubleValue();
				labAdminRate += reportRate.getLabAdminRate().doubleValue();
				complexItemRate += reportRate.getComplexItemRate().doubleValue();
				studentTrainRate += reportRate.getStudentTrainRate().doubleValue();
			}
			
			//计算综合指数
			for (ReportRate reportRate : reportRates) 
			{
				double result = 0;
				
				if(!(labRate>-0.000001 && labRate< 0.000001))
				{
					result += reportRate.getLabRate().doubleValue()/labRate;
				}
				if(!(largeDeviceTimeRate>-0.000001 && largeDeviceTimeRate< 0.000001))
				{
					result += reportRate.getLargeDeviceTimeRate().doubleValue()/largeDeviceTimeRate;
				}
				if(!(largeDeviceUsedRate>-0.000001 && largeDeviceUsedRate< 0.000001))
				{
					result += reportRate.getLargeDeviceUsedRate().doubleValue()/largeDeviceUsedRate;
				}
				if(!(itemsRate>-0.000001 && itemsRate< 0.000001))
				{
					result += reportRate.getItemsRate().doubleValue()/itemsRate;
				}
				if(!(teacherItemRate>-0.000001 && teacherItemRate< 0.000001))
				{
					result += reportRate.getTeacherItemRate().doubleValue()/teacherItemRate;
				}
				if(!(labAdminRate>-0.000001 && labAdminRate< 0.000001))
				{
					result += reportRate.getLabAdminRate().doubleValue()/labAdminRate;
				}
				if(!(complexItemRate>-0.000001 && complexItemRate< 0.000001))
				{
					result += reportRate.getComplexItemRate().doubleValue()/complexItemRate;
				}
				if(!(studentTrainRate>-0.000001 && studentTrainRate< 0.000001))
				{
					result += reportRate.getStudentTrainRate().doubleValue()/studentTrainRate;
				}
				
				reportRate.setScore(new BigDecimal(result));
				reportRateDAO.store(reportRate);
				reportRateDAO.flush();
			}
			
			//根据综合指标倒序排列
			StringBuffer resultHql = new StringBuffer("select rr from ReportRate rr where rr.terms='"+terms+"' order by rr.score desc");
			List<ReportRate> resultRates = reportRateDAO.executeQuery(resultHql.toString(), 0, -1);
			//保存绩效排名
			for (int i = 0; i < resultRates.size(); i++) 
			{
				resultRates.get(i).setRank(i+1);
				reportRateDAO.store(resultRates.get(i));
				reportRateDAO.flush();
			}
		}
	}

	/**
	 * 以时间倒叙的形式获取所有学期
	 * @author hely
	 * 2014.11.14
	 */
	@Override
	public List<SchoolTerm> getAllSchoolTerms() 
	{
		StringBuffer hql = new StringBuffer("select t from SchoolTerm t order by t.termStart desc");
		return schoolTermDAO.executeQuery(hql.toString(), 0, -1);
	}

	/**
	 * 根据学院和学期获取绩效指标数据
	 * @param academyNumber 学院编号
	 * @param schoolTerm 学期
	 * @author hely
	 * 2014.12.02
	 */
	@Override
	public ReportRate getReportRateByAcademyTerm(String academyNumber, SchoolTerm schoolTerm) 
	{
		StringBuffer hql = new StringBuffer("select rr from ReportRate rr where 1=1 ");
		hql.append(" and rr.schoolAcademy.academyNumber='"+academyNumber+"' ");  //学院条件
		if(schoolTerm!=null && schoolTerm.getId()!=null)
		{
			hql.append(" and rr.terms='"+schoolTerm.getId()+"' ");  //学期条件
		}
		List<ReportRate> reportRates = reportRateDAO.executeQuery(hql.toString());
		if(reportRates.size() > 0)
		{
			return reportRates.get(0);  //某学院指定学期只有一条数据
		}
		
		return null;
	}

	/**
	 * 根据学院编号获取学院名称
	 * @param academyNumber 学院编号
	 * @author hely
	 * 2015.03.19
	 */
	@Override
	public String getAcademyNameByNumber(String academyNumber) 
	{
		SchoolAcademy schoolAcademy = schoolAcademyDAO.findSchoolAcademyByAcademyNumber(academyNumber);
		return schoolAcademy== null ? "":schoolAcademy.getAcademyName();
	}

	/**
	 * 返回当前学期，如果放假则返回上一个学期
	 * @author hly
	 * 2015.07.21
	 */
	@Override
	public SchoolTerm getPreviousSchoolTerm() {
		SchoolTerm schoolTerm = new SchoolTerm();
		// 创建一条按创建时间倒序排序的语句；
		StringBuffer sb = new StringBuffer(
				"select t from SchoolTerm t where now() between t.termStart and t.termEnd or now() > t.termEnd order by t.termEnd desc");
		List<SchoolTerm> terms = schoolTermDAO.executeQuery(sb.toString());
		if (terms.size() > 0) {
			schoolTerm = terms.get(0);
		}
		return schoolTerm;
	}

	/************************************************
	 * 设备学期使用情况表（计划任务）
	 * @author 贺子龙
	 * 2016-07-21
	 *************************************************/
	public void createSchoolDeviceUse(){
		
		String sqlTerm = "select distinct lr.schoolTerm from LabRoomDeviceReservation lr where 1=1";
		// 限制条件：审核通过的设备预约
		sqlTerm+=" and lr.CDictionaryByCAuditResult.id = 2";
		// 获取所有学期
		List<SchoolTerm> termList = schoolTermDAO.executeQuery(sqlTerm);
		
		for (SchoolTerm schoolTerm : termList) {// 遍历学期
		String sqlDevice = "select distinct l from LabRoomDevice l, LabRoomDeviceReservation lr where 1=1";
		// 建立两个表的连接关系
		sqlDevice+=" and lr.labRoomDevice.id = l.id";
		// 限制条件：审核通过的设备预约
		sqlDevice+=" and lr.CDictionaryByCAuditResult.id = 2";
		// 限制条件：当前学期
		sqlDevice+=" and lr.schoolTerm.id="+schoolTerm.getId();
		List<LabRoomDevice> labRoomDevices = labRoomDeviceDAO.executeQuery(sqlDevice, 0, -1);
		if (labRoomDevices!=null && labRoomDevices.size()>0) {// 遍历设备
			for (LabRoomDevice device : labRoomDevices) {
					String terms = "("+schoolTerm.getId()+")";
					String deviceNumber = device.getSchoolDevice().getDeviceNumber();
					labRoomDeviceService.calculateHoursForSchoolDevice(deviceNumber, terms);
				}
			}
		}
	} 
	
	/************************************************
	 * 获取所有实验中心
	 * @author 张凯
	 * 2017-3-12
	 *************************************************/
	public List<LabCenter> findAllLabCenter(){
		String sql = " select lc from LabCenter lc where 1=1 ";
		
		return labCenterDAO.executeQuery(sql, 0,-1);
	}
	
	
	/****************************************************************************
	 * description：获得实验室使用情况列表
	 * @author：郑昕茹
	 * @date: 2017-05-10
	 ****************************************************************************/
	public List getListLabRoomUses(HttpServletRequest request, int page, int pageSize){
			String sql = "select * from view_lab_room_user_inorder v where 1=1";
			if(request.getParameter("termId") != null && !request.getParameter("termId").equals("")){
				sql += " and (termId1 ="+request.getParameter("termId")+" or termId2 ="+request.getParameter("termId")+")";
			}
			if(request.getParameter("centerId") != null && !request.getParameter("centerId").equals("")){
				sql += " and centerId ="+request.getParameter("centerId");
			}
			if(request.getParameter("labId") != null && !request.getParameter("labId").equals("")){
				sql += " and labId ="+request.getParameter("labId");
			}
			if(request.getParameter("useType") != null && !request.getParameter("useType").equals("")){
				if(request.getParameter("useType").equals("1"))
				{
					sql += " and useType ='排课'";
				}else{
					sql += " and useType ='预约'";
				}
			}
			if(!request.getSession().getAttribute("authorityName").equals("SUPERADMIN") && !request.getSession().getAttribute("authorityName").equals("LABCENTERMANAGER")){
				sql += " and teacherUsername ='"+shareService.getUser().getUsername()+"'";	
			}
			//获取当前时间，根据当前时间判断所属学期
			Calendar time = Calendar.getInstance();
			SchoolTerm term = shareService.getBelongsSchoolTerm(time);
			Calendar termStart = term.getTermStart();
			Calendar termEnd = term.getTermEnd();
			SimpleDateFormat start = new SimpleDateFormat("yyyy-MM-dd");
			String terms = start.format(termStart.getTime());
			SimpleDateFormat end = new SimpleDateFormat("yyyy-MM-dd");
			String terme = end.format(termEnd.getTime());
			if(request.getParameter("termId") == null || request.getParameter("termId").equals("")){
				sql += " and v.date1 between '"+ terms +"' and '"+ terme +"' ";
			}
			Query query= entityManager.createNativeQuery(sql);
			if(pageSize != -1)
			query.setMaxResults(pageSize);
			query.setFirstResult(pageSize*(page-1));
	       // 获取list对象
	        List<Object[]> list= query.getResultList();
			return list;
	}
	
	
	
	/*************************************************************************************
	 * @description：实验室日志报表导出
	 * @author：郑昕茹
	 * @date：2017-05-10
	 *************************************************************************************/
	@Override
	public void exportLabRoomUses(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		List<Map> list = new ArrayList<Map>();
		List<Object[]> listLabRoomDeviceUsageInAppointments = this.getListLabRoomUses(request,1,-1);
		//实验中心所在学院的实验室项目
		int i=1;
			for (Object[] curr : listLabRoomDeviceUsageInAppointments) 
			{
				Map map = new HashMap();
				
				map.put("serial number",i);//序号
				i++;
				map.put("deviceName",curr[1]);//设备名称
				map.put("courseName",curr[2]);//课程名称
				String s = "";
				if(curr[19] != null)s=curr[19].toString();
				if(curr[21] != null)s += curr[21].toString();
				if(curr[22] != null)s += "-"+curr[22].toString();
				map.put("itemName",s);//项目
				map.put("weekday", curr[10]);//星期
				s = "";
				if(curr[10].equals("1")){
					if(curr[11] != null){
						s += curr[11];
					}
					if(curr[12] != null){
						s += curr[12];
					}
					if(curr[23] != null){
						s += curr[23];
					}
					if(curr[24] != null){
						s += "-"+curr[24];
					}
				}
				else{
					if(curr[11] != null){
						s += curr[11];
					}
					if(curr[12] != null){
						s += curr[12];
					}
				}
				map.put("classes",s);//节次
				map.put("weeks",curr[13]);//周次
				map.put("teachers",curr[14]);//上课教师
				map.put("useCondition", curr[28]);
				list.add(map);
			}  
		String title = "实验室日志表";
        String[] hearders = new String[] {"序号","实验室名称","实验室地点","时间","使用类型","使用详情",
        		"任课或值班教师","学生人数","使用情况"};//表头数组
        String[] fields = new String[] {"serial number","deviceName", "courseName", "itemName",    "weekday",
        		"classes", "weeks", "teachers","useCondition"};
        TableData td = ExcelUtils.createTableData(list, ExcelUtils.createTableHeader(hearders),fields);
        JsGridReportBase report = new JsGridReportBase(request, response);
        report.exportExcel(title,  shareService.getUserDetail().getCname(), "",td);	
	}
	
	/****************************************************
	 * 功能：系统报表-实验室利用率
	 * 作者： 贺子龙
	 * 日期：2016-10-14
	 *****************************************************/
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List getLabUseRate(HttpServletRequest request){
		String sql = "select * from view_lab_hour_center v where 1=1";
		// 当前学期
		int termId = shareService.getBelongsSchoolTerm(Calendar.getInstance()).getId();
		// 选择
		if (!EmptyUtil.isStringEmpty(request.getParameter("term"))) {
			termId = Integer.parseInt(request.getParameter("term"));
		}
		sql+=" and v.termId = "+termId;
		
		Query query= entityManager.createNativeQuery(sql);
        // 获取list对象
        List<Object[]> list= query.getResultList();
		return list;
	}
	
	
	/****************************************************
	 * 功能：系统报表-实验室利用率--实验室
	 * 作者： 贺子龙
	 * 日期：2016-10-14
	 *****************************************************/
	@SuppressWarnings("rawtypes")
	public List getLabUseRateRoom(Integer centerId, Integer termId){
		// 建立查询
		String sql = "select * from view_lab_hour_room v where 1=1";
		// 学期条件
		sql+=" and v.termId = "+termId;
		// 中心条件
		sql+=" and v.centerId = "+centerId;
		// 执行查询
		Query query= entityManager.createNativeQuery(sql);
        // 获取list对象
        List<Object[]> list= query.getResultList();
		return list;
	}
	
	/**************************************************
	 * @description：實驗室使用率報表導出
	 * @author：陳樂為
	 * @date：2016-10-24
	 **************************************************/
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void exportReportLabRate(HttpServletRequest request, HttpServletResponse response, List<Object[]> labRates,int flag) throws Exception 
	  	{
	    List<Map> list = new ArrayList<Map>();
	    //实验中心所在学院的实验室项目
	    for (Object[] labRate : labRates) 
	      {
	        Map map = new HashMap();
	        if(flag == 1) {
	        	map.put("center",labRate[1]);//实验部门
	        	map.put("centerRealHour",labRate[3]);//负责人
	        	map.put("centerPlanHour", labRate[4]);//
	        	map.put("centerRate",labRate[5]);//
	        }else if (flag == 2) {
	        	map.put("center",labRate[2]);//实验部门
	        	map.put("centerRealHour",labRate[4]);//负责人
	        	map.put("centerPlanHour", labRate[5]);//
	        	map.put("centerRate",labRate[6]);//
	        }
	        list.add(map);
	      }  //实验室遍历
	    if(flag == 1) {
	    	String title = "实验室使用率报表-中心";
	    	String[] hearders = new String[] {"中心名称","本中心实验室学时数（实际）","本中心实验室学时数（额定）","实验室利用率（%）"};//表头数组
	    	String[] fields = new String[] {"center","centerRealHour", "centerPlanHour", "centerRate"};
	    	TableData td = ExcelUtils.createTableData(list, ExcelUtils.createTableHeader(hearders),fields);
	    	JsGridReportBase report = new JsGridReportBase(request, response);
	    	report.exportExcel(title,  shareService.getUserDetail().getCname(), "", td);  
	    }else if (flag == 2) {
	    	String title = "实验室使用率报表-实验室";
	    	String[] hearders = new String[] {"实验室名称","实验室学时数（实际）","实验室学时数（额定）","实验室利用率（%）"};//表头数组
	    	String[] fields = new String[] {"center","centerRealHour", "centerPlanHour", "centerRate"};
	    	TableData td = ExcelUtils.createTableData(list, ExcelUtils.createTableHeader(hearders),fields);
	    	JsGridReportBase report = new JsGridReportBase(request, response);
	    	report.exportExcel(title,  shareService.getUserDetail().getCname(), "", td);  
	    }
	  }
	/****************************************************************************
	 * description：获得实验人时列表
	 * @author：戴昊宇
	 * @date: 2017-05-10
	 ****************************************************************************/
	public List getListLabItemHourNumber(HttpServletRequest request, int page, int pageSize){
			String sql = "select * from view_lab_item_hour_number v where 1=1";
			if(request.getParameter("termId") != null && !request.getParameter("termId").equals("")){
				sql += " and termId ="+request.getParameter("termId");
			}
			
			if(request.getParameter("roomId") != null && !request.getParameter("roomId").equals("")){
				sql += " and roomId ="+request.getParameter("roomId");
			}
			//获取当前时间，根据当前时间判断所属学期
			Calendar time = Calendar.getInstance();
			SchoolTerm term = shareService.getBelongsSchoolTerm(time);
			String termName = term.getTermName();
			if(request.getParameter("termId") == null || request.getParameter("termId").equals("")){
			
			sql += " and termName ='" + termName +"'";
			}
			Query query= entityManager.createNativeQuery(sql);
			if(pageSize != -1)
			query.setMaxResults(pageSize);
			query.setFirstResult(pageSize*(page-1));
	       // 获取list对象
	        List<Object[]> list= query.getResultList();
			return list;
	}
	
	/****************************************************
	 * 功能：系统报表-实验室数量所占比例
	 * 作者： 王昊
	 * 日期：2017-10-26
	 *****************************************************/
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List getLabRoomRate(HttpServletRequest request){
		String sql = "select * from view_lab_room_number v where 1=1";
	
		Query query= entityManager.createNativeQuery(sql);
        // 获取list对象
        List<Object[]> list= query.getResultList();
		return list;
	}
	
	/****************************************************
	 * 功能：系统报表-大综合报表
	 * 作者： 王昊
	 * 日期：2017-10-26
	 *****************************************************/
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List getCountRate(HttpServletRequest request){
		String sql = "select * from view_integrated_report v where 1=1";
	
		Query query= entityManager.createNativeQuery(sql);
        // 获取list对象
        List<Object[]> list1= query.getResultList();
		return list1;
	}
	
	/****************************************************
	 * 功能：系统报表-大综合报表
	 * 作者： 王昊
	 * 日期：2017-10-26
	 *****************************************************/
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List getTeacherPercent(HttpServletRequest request){
		String sql = "select * from view_integrated_report v where 1=1";
	
		Query query= entityManager.createNativeQuery(sql);
        // 获取list对象
        List<Object[]> list= query.getResultList();
		return list;
	}

	/****************************************************
	 * 功能：系统报表-实验室教师人数
	 * 作者： 王昊
	 * 日期：2017-10-29
	 *****************************************************/
	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List getTecherPercent(HttpServletRequest request) {
		String sql = "select * from view_lab_teacher_percent v where 1=1";
		
		if(request.getParameter("centerNumber") != null && !request.getParameter("centerNumber").equals("")){
			sql += " and centerNumber ="+request.getParameter("centerNumber");
		}
		
		Query query= entityManager.createNativeQuery(sql);
        // 获取list对象
        List<Object[]> list= query.getResultList();
		return list;
	}
	/****************************************************
	 * 功能：系统报表-实验室教师学历结构
	 * 作者： 王昊
	 * 日期：2017-10-29
	 *****************************************************/
	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List getTecherEducation(HttpServletRequest request) {
		String sql = "select * from view_lab_teacher_education v where 1=1";
		
		if(request.getParameter("centerNumber") != null && !request.getParameter("centerNumber").equals("")){
			sql += " and centerNumber ="+request.getParameter("centerNumber");
		}

		Query query= entityManager.createNativeQuery(sql);
        // 获取list对象
        List<Object[]> list= query.getResultList();
		return list;
	}
	/****************************************************
	 * 功能：系统报表-实验室利用率
	 * 作者： 王昊
	 * 日期：2017-10-29
	 *****************************************************/
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List getLabUseRate1(HttpServletRequest request){
		String sql = "select * from view_lab_hour_center v where 1=1";
		// 当前学期
		int termId = shareService.getBelongsSchoolTerm1(Calendar.getInstance()).getId();
		// 选择
		if (!EmptyUtil.isStringEmpty(request.getParameter("term"))) {
			termId = Integer.parseInt(request.getParameter("term"));
		}
		sql+=" and v.termId = "+termId;
		
		Query query= entityManager.createNativeQuery(sql);
        // 获取list对象
        List<Object[]> list= query.getResultList();
		return list;
	}
	/****************************************************
	 * 功能：系统报表-实验室教师年龄结构
	 * 作者： 王昊
	 * 日期：2017-10-29
	 *****************************************************/
	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List getTecherAge(HttpServletRequest request) {
		String sql = "select * from view_lab_teacher_age v where 1=1";
		
		if(request.getParameter("centerNumber") != null && !request.getParameter("centerNumber").equals("")){
			sql += " and centerNumber ="+request.getParameter("centerNumber");
		}

		Query query= entityManager.createNativeQuery(sql);
        // 获取list对象
        List<Object[]> list= query.getResultList();
		return list;
	}
	/****************************************************
	 * 功能：系统报表-实验室教师职称结构
	 * 作者： 王昊
	 * 日期：2017-10-29
	 *****************************************************/
	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List getTecherLevel(HttpServletRequest request) {
		String sql = "select * from view_lab_teacher_level v where 1=1";
		
		if(request.getParameter("centerNumber") != null && !request.getParameter("centerNumber").equals("")){
			sql += " and centerNumber ="+request.getParameter("centerNumber");
		}

		Query query= entityManager.createNativeQuery(sql);
        // 获取list对象
        List<Object[]> list= query.getResultList();
		return list;
	}
	
	/*************************************************************************************
	 * @description：实验学时统计报表导出
	 * @author：陈乐为	
	 * @date：2018-1-3
	 *************************************************************************************/
	@SuppressWarnings("unchecked")
	@Override
	public void exportLabItemHourNumber(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		List<Map> list = new ArrayList<Map>();
		// 查询记录
		List<Object[]> listItemHourNumber = this.getListLabItemHourNumber(request, 1, -1);
		//实验中心所在学院的实验室项目
		int i=1;
			for (Object[] curr : listItemHourNumber) 
			{
				Map map = new HashMap();
				
				map.put("serial number",i);//序号
				i++;
				
                map.put("centerName",curr[0]);//实验中心
                map.put("labName",curr[1]);//实验室名称及房号
                map.put("itemName",curr[2]);//开设实验名称/开放实验室开展项目
                map.put("teacher",curr[3]);//开课教师
                map.put("hours",curr[4]);//实验学时
                map.put("classes",curr[5]);//学生班级及人数
                map.put("itemTime",curr[6]);//开设实验时段
                map.put("perHours",curr[7]);//人时数
                map.put("totalPerHours","");//总人时
                map.put("memo","");//备注
                
				list.add(map);
			}  
		String title = "实验学时统计表";
        String[] hearders = new String[] {"序号","实验中心","实验室名称及房号","开设实验名称/开放实验室开展项目","开课教师","实验学时",
        		"学生班级及人数","开设实验时段","人时数", "总人时", "备注"};//表头数组
        String[] fields = new String[] {"serial number","centerName", "labName", "itemName",    "teacher",
        		"hours", "classes", "itemTime","perHours", "totalPerHours", "memo"};
        TableData td = ExcelUtils.createTableData(list, ExcelUtils.createTableHeader(hearders),fields);
        JsGridReportBase report = new JsGridReportBase(request, response);
        report.exportExcel(title,  shareService.getUserDetail().getCname(), "",td);	
	}
}
