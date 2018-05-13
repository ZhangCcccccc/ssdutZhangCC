package net.xidlims.service.report;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import net.xidlims.domain.LabRoom;
import net.xidlims.domain.OperationItem;
import net.xidlims.domain.ReportParameter;
import net.xidlims.domain.SchoolAcademy;
import net.xidlims.domain.SchoolDevice;
import net.xidlims.domain.SchoolTerm;
import net.xidlims.domain.TimetableAppointment;
import net.xidlims.domain.ReportRate;
import net.xidlims.domain.User;

public interface ReportService {
	
	/**
	 * 获取实验室容量
	 * @author hely
	 * 2014.08.06
	 */
	public int getLabCapacity(int labRoomId);
	
	/**
	 * 获得学院的生均面积
	 * @param academyNumber 学院编号
	 * @author hely
	 * 2014.08.26
	 */
	public int getLabAvgArea(String academyNumber);
	
	/**
	 * 获取学院实验室容量总和
	 * @param academyNumber 学院编号
	 * @author hely
	 * 2014.08.26
	 */
	public int getLabCapacityByAcademy(String academyNumber);
	
	/**
	 * 实验室额定课时数（学校给的数据）
	 * @author hely
	 * 2014.08.06
	 */
	public int getRatedCourseTime(String academyNumber);
	
	/**
	 * 单个实验室实验人时数
	 * @author hely
	 * 2014.08.06
	 */
	public int getStudentTime(int labRoomId, List<SchoolTerm> schoolTerms);
	
	/**
	 * 计算指定一些实验室的人时数之和
	 * @param labArr 实验室id数组
	 * @param schoolTerms 学期
	 * @author hely
	 * 2014.12.03
	 */
	public int getStudentTimeLabs(int[] labArr, List<SchoolTerm> schoolTerms);
	
	/**
	 * 指定学院的实验室人时数
	 * @param academyNumber 学院编号
	 * @param schoolTerms 时间段
	 * @author hely
	 * 2014.08.26
	 */
	public int getStudentTimeByAcademy(String academyNumber, List<SchoolTerm> schoolTerms);
	
	/**
	 * 获取制定SchoolCourseDetail大学生数量
	 * @author hely
	 * 2014.08.07
	 */
	public int getStudentAmountByDetail(String courseDetailNO);
	
	/**
	 * 单个实验室利用率
	 * @author hely
	 * 2014.08.06
	 */
	public String getSingleLabRate(int labRoomId, int ratedCourseTime, List<SchoolTerm> schoolTerms);
	
	/**
	 * 计算一个实验室有多个实验室分室的实验室利用率
	 * @param labArr 实验分室id(labRoom的id)
	 * @param ratedCourseTime 额定课时数
	 * @param schoolTerms 学期
	 * @author hely
	 * 2014.12.03
	 */
	public String getLabRateLabAnnex(int[] labArr, int ratedCourseTime, List<SchoolTerm> schoolTerms);
	
	/**
	 * 学院实验室利用率
	 * @author hely
	 * 2014.08.06
	 */
	public BigDecimal getAcademyLabRate(String academyNumber, List<SchoolTerm> schoolTerms);
	
	/**
	 * 根据公式中的变量计算学院实验室利用率
	 * @param studentTimeSum 实验室人时数
	 * @param ratedCourseTime 额定课时数
	 * @param labRoomCapacity 实验室容量
	 * @author hely
	 * 2014.08.26
	 */
	public String getAcademyLabRateStr(int studentTimeSum, double ratedCourseTime, int labRoomCapacity);
	
	/**
	 * 学校大型仪器设备年平均机时（学校给的数据）
	 * @author hely
	 * 2014.08.06
	 */
	public int getLargeDeviceAvgTime(String academyNumber);
	
	/**
	 * 获取某学院大型仪器设备（价格大于等于10万的设备）
	 * @author hely
	 * 2014.08.06
	 */
	public List<SchoolDevice> getLargeDeviceByAcademy(String academyNumber, String terms);
	
	/**
	 * 获取某学院大型仪器设备（价格大于等于10万的设备）
	 * @author hely
	 * 2014.08.08
	 */
	public int getLargeDeviceAmountByAcademy(String academyNumber);
	
	/**
	 * 学院大型仪器设备使用机时汇总
	 * @author hely
	 * 2014.08.06
	 */
	public int getLargeDeviceSumTime(String academyNumber, List<SchoolTerm> schoolTerms);
	
	/**
	 * 大型实验仪器设备使用机时的平均利用率(报表2)
	 * @author hely
	 * 2014.08.06
	 */
	public BigDecimal getLargeDeviceTimeRate(String academyNumber, List<SchoolTerm> schoolTerms);
	
	/**
	 * 某学院使用的大型实验仪器台数
	 * @author hely
	 * 2014.08.06
	 */
	public int getLargeDeviceUsedAmount(String academyNumber, List<SchoolTerm> schoolTerms);
	
	/**
	 * 大型实验仪器设备台数使用率
	 * @author hely
	 * 2014.08.06
	 */
	public BigDecimal getLargeDeviceUsedRate(String academyNumber, List<SchoolTerm> schoolTerms);
	
	/**
	 * 获取给定学期的下一个学期
	 * @author hely
	 * 2014.08.08
	 */
	public SchoolTerm getNextSchoolTerm(SchoolTerm schoolTerm);
	
	/**
	 * 教学计划规定开设的实验数
	 * @author hely
	 * 2014.08.08
	 */
	public int getPlanItemsAmount(String academyNumber, List<SchoolTerm> schoolTerms);
	
	/**
	 * 实际开出实验数量
	 * @author hely
	 * 2014.08.08
	 */
	public int getRealItemsAmount(String academyNumber, List<SchoolTerm> schoolTerms);
	
	/**
	 * 实际开出实验项目列表
	 * @author hely
	 * 2014.09.17
	 */
	public List<OperationItem> getRealItems(String academyNumber, List<SchoolTerm> schoolTerms, int curr, int size);
	
	/**
	 * 实验项目开出率
	 * @author hely
	 * 2014.08.08
	 */
	public BigDecimal getItemsRate(String academyNumber, List<SchoolTerm> schoolTerms);
	
	/**
	 * 学院在职教师数
	 * @author hely
	 * 2014.08.08
	 */
	public int getTeacherAmountByAcademy(String academyNumber);
	
	/**
	 * 参加指导实验的教师人数
	 * @author hely
	 * 2014.08.08
	 */
	public int getTeacherItemAmountByAcademy(String academyNumber, List<SchoolTerm> schoolTerms);
	
	/**
	 * 教师参加指导实验占教师总人数的比例
	 * @author hely
	 * 2014.08.08
	 */
	public BigDecimal getTeacherItemRate(String academyNumber, List<SchoolTerm> schoolTerms);
	
	/**
	 * 学院实验室专职实验人数
	 * @author hely
	 * 2014.08.08
	 */
	public int getLabAdminAmount(String academyNumber);
	
	/**
	 * 实验室专职管理人员人均接待师生的人时数(报表6)
	 * @author hely
	 * 2014.08.08
	 */
	public BigDecimal getLabAdminRate(String academyNumber, List<SchoolTerm> schoolTerms);
	
	/**
	 * 实验室专职管理人员人均接待师生的人时数(通过公式中的数值直接计算，加快页面显示速度)
	 * @author hely
	 * 2014.09.17
	 */
	public BigDecimal getlabAdminRateSimple(BigDecimal studentTimeSum, BigDecimal labAdminAmount);
	
	/**
	 * 获取指定学院的大型综合性实验数量
	 * @param academyNumber  学院编号（如果为null或者""则返回全校所有的大型综合性实验数量）
	 * @author hely
	 * 2014.08.18
	 */
	public int getComplexItemAmountByAcademy(String academyNumber);
	
	/**
	 * 获取指定学院的专业数量（report_parameter表中的数据）
	 * @param academyNumber 学院编号（如果为null或者""则返回全校专业数量）
	 * @author hely
	 * 2014.08.18
	 */
	public int getMajorAmountByAcademy(String academyNumber);
	
	/**
	 * 独立实验课、大型综合性实验课占比(报表7)
	 * @param academyNumber 学院编号
	 * @author hely
	 * 2014.08.18
	 */
	public BigDecimal getComplexItemRate(List<SchoolTerm> schoolTerms, String academyNumber);
	
	/**
	 * 学院实际人才培养量（本科生+研究生）
	 * @param academyNumber 学院编号
	 * @author hely
	 * 2014.08.18
	 */
	public int getStudentAmountRealTrain(String academyNumber, List<SchoolTerm> schoolTerms);
	
	/**
	 * 学院实际人才培养量（本科生）
	 * @param academyNumber 学院编号
	 * @para schoolTerms 学期集合，可能是一个学期或者一个学年（两个学期）
	 * @author hely
	 * 2014.09.25
	 */
	public int getUndergraduateAmount(String academyNumber, List<SchoolTerm> schoolTerms);
	
	/**
	 * 学院实际人才培养量（研究生）
	 * @param academyNumber 学院编号
	 * @author hely
	 * 2014.09.25
	 */
	public int getGraduateAmount(String academyNumber, List<SchoolTerm> schoolTerms);
	
	/**
	 * 低于10w的设备的经费总额
	 * @param academyNumber 学院编号
	 * @author hely
	 * 2014.08.18
	 */
	public BigDecimal getDeviceCostByAcademy(String academyNumber);
	
	/**
	 * 大型设备的经费总额
	 * @param academyNumber 学院编号
	 * @author hely
	 * 2014.08.18
	 */
	public BigDecimal getBigDeviceCostByAcademy(String academyNumber);
	
	/**
	 * 学院额定人才培养量
	 * @param academyNumber 学院编号
	 * @author hely
	 * 2014.08.18
	 */
	public BigDecimal getStudentAmountPlanTrain(String academyNumber);
	
	/**
	 * 指定学院的学科系数
	 * @param academyNumber 学院代码
	 * @author hely
	 * 2014.08.18
	 */
	public BigDecimal getSubjectFactorByAcademy(String academyNumber);
	
	/**
	 * 学院实验室使用面积总和
	 * @param academyNumber 学院编号
	 * @author hely
	 * 2014.08.18
	 */
	public BigDecimal getLabRoomAreaByAcademy(String academyNumber);
	
	/**
	 * 人才培养率
	 * @param academyNumber 学院编号
	 * @author hely
	 * 2014.08.18
	 */
	public BigDecimal getStudentTrainRate(String academyNumber, List<SchoolTerm> schoolTerms);
	
	/**
	 * 根据学院查找实验室分室
	 * @param academyNumber 学院编号
	 * @author hely
	 * 2014.08.20
	 */
	public List<LabRoom> getLabRoomByAcademy(String academyNumber);
	
	/**
	 * 保存报表参数
	 * @param reportParameter 报表参数对象
	 * @author hely
	 * 2014.08.22
	 */
	public void saveReportParameter(ReportParameter reportParameter);
	
	/**
	 * 获取所有报表参数
	 * @author hely
	 * 2014.08.22
	 */
	public List<ReportParameter> getAllReportParameter();
	
	/**
	 * 根据id查找报表参数
	 * @author hely
	 * 2014.08.22
	 */
	public ReportParameter getReportParameterById(Integer id);
	
	/**
	 * 根据id删除报表参数
	 * @param id 报表参数id
	 * @author hely
	 * 2014.08.22
	 */
	public void deleteReportParameterById(Integer id);
	
	/**
	 * 根据学院和所给时间段获取实验室利用率公式中涉及到的数据
	 * @param academyNumber  学院编号
	 * @param schoolTerms  时间段
	 * @author hely
	 * 2014.08.26
	 */
	public List<LabRoom> getLabRateDetailInfo(List<SchoolTerm> schoolTerms);
	
	/**
	 * 根据实验室id获取实验室名称、容量、利用率等信息
	 * @param id 实验室id
	 * @author hely
	 * 2014.08.27
	 */
	public LabRoom getLabRoomInfoById(Integer id, int ratedCourseTime, List<SchoolTerm> schoolTerms);
	
	/**
	 * 根据学院查看所有的预约的列表安排
	 * @param academyNumber
	 * @param curr 当前页
	 * @param size 每页尺寸
	 * @author hely
	 * 2014.08.27
	 */
	public List<TimetableAppointment> getTimetableAppointmentsByAcademy(String academyNumber, List<SchoolTerm> schoolTerms, int curr, int size);
	
	/**
	 * 获取需要计算绩效报表的学院
	 * @author hely
	 * 2014.09.02
	 */
	public List<SchoolAcademy> getSchoolAcademyByQuery();
	
	/**
	 * 获取需要计算绩效报表学院的名称字符串
	 * @author hely
	 * 2014.09.04
	 */
	public String getAcademyNames();
	
	/**
	 * 根据指定学期查询绩效报表信息
	 * @param schoolTerms 学期
	 * @author hely
	 * 2014.09.04
	 */
	public List<ReportRate> getReportRateByTerms(List<SchoolTerm> schoolTerms);
	
	/**
	 * 根据学期查找大型设备台数使用率详细信息
	 * @param schoolTerms 学期
	 * @author hely
	 * 2014.09.05
	 */
	public List<LabRoom> getLargeDeviceUsedRateInfo(List<SchoolTerm> schoolTerms);
	
	/**
	 * 根据学期查找实验室专职管理人员接待人时数
	 * @param schoolTerms 学期
	 * @author hely
	 * 2014.09.17
	 */
	public List<LabRoom> getLabAdminRateInfo(List<SchoolTerm> schoolTerms);
	
	/**
	 * 根据学期查找教师参与指导实验项目的比例详细信息
	 * @param schoolTerms 学期
	 * @author hely
	 * 2014.09.16
	 */
	public List<LabRoom> getTeacherItemRateInfo(List<SchoolTerm> schoolTerms);
	
	/**
	 * 根据学期查找项目开出率详细信息
	 * @param schoolTerms 学期
	 * @author hely
	 * 2014.09.17
	 */
	public List<LabRoom> getItemsRateInfo(List<SchoolTerm> schoolTerms);

	/**
	 * 根据学院编号获取学院信息
	 * @param academyNumber 学院编号
	 * @author hely
	 * 2014.09.10
	 */
	public SchoolAcademy getAcademyByNumber(String academyNumber);
	
	/**
	 * 某学院给定学期内使用的大型实验仪器
	 * @param academyNumber 学院编号
	 * @param schoolTerms 学期
	 * @author hely
	 * 2014.09.10
	 */
	public List<SchoolDevice> getLargeDeviceUsed(String academyNumber, List<SchoolTerm> schoolTerms);
	
	/**
	 * 得到学年的数据，用于下拉框
	 * @author hely
	 * 2014.09.10
	 */
	public Map<String, String> getTermsMap();
	
	/**
	 * 根据学年编号(year_code)获取学期
	 * @param yearCode 学年编号
	 * @author hely
	 * 2014.09.10
	 */
	public String getTermsByYearCode(String yearCode);
	
	/**
	 * 根据字符串获取学期，用于下拉框
	 * @param queryStr 形式如 1,4
	 * @param flag 为true则查出字符串所包含的学期，否则查出不包含的学期
	 * @author hely
	 * 2014.09.10
	 */
	public Map<Integer, String> getSelectTerms(String queryStr, String yearCode, boolean flag);
	
	/**
	 * 根据字符串获取学期集合
	 * @param queryStr 形式如1,4
	 * @author hely
	 * 2014.09.10
	 */
	public List<SchoolTerm> getSchoolTermsByStr(String queryStr);
	
	/**
	 * 参加指导实验的教师
	 * @author hely
	 * 2014.09.16
	 */
	public Set<User> getTeacherItemByAcademy(String academyNumber, List<SchoolTerm> schoolTerms);
	
	/**
	 * 根据学期查找大型设备机时利用率详细信息
	 * @param schoolTerms 学期
	 * @author hely
	 * 2014.09.16
	 */
	public List<LabRoom> getLargeDeviceTimeRateInfo(List<SchoolTerm> schoolTerms);
	
	/**
	 * 根据学期查找独立、大型综合性实验课占比详细信息
	 * @param schoolTerms 学期
	 * @author hely
	 * 2014.09.25
	 */
	public List<LabRoom> getComplexItemRateInfo(List<SchoolTerm> schoolTerms);
	
	/**
	 * 获取指定学院、指定学期的大型综合性实验数量
	 * @param academyNumber  学院编号（如果为null或者""则返回全校所有的大型综合性实验数量）
	 * @author hely
	 * 2014.08.18
	 */
	public int getComplexItemAmountByTermAcademy(List<SchoolTerm> schoolTerms, String academyNumber);
	
	/**
	 * 获取指定学院、指定学期的大型综合性实验
	 * @param academyNumber  学院编号（如果为null或者""则返回全校所有的大型综合性实验）
	 * @author hely
	 * 2014.09.25
	 */
	public List<OperationItem> getComplexItemByTermAcademy(List<SchoolTerm> schoolTerms, String academyNumber, int curr, int size);
	
	/**
	 * 根据学期查找人才培养率详细信息
	 * @param schoolTerms 学期
	 * @author hely
	 * 2014.09.25
	 */
	public List<LabRoom> getStudentTrainRateInfo(List<SchoolTerm> schoolTerms);
	
	/**
	 * 获取某学院大型仪器设备（价格低于10万的设备）
	 * @author hely
	 * 2014.09.26
	 */
	public List<SchoolDevice> getDeviceByAcademy(String academyNumber);
	
	/**
	 * 获取指定学年的第一个学期
	 * @param yearCode 学年代码
	 * @author hely
	 * 2014.09.26
	 */
	public SchoolTerm getFirstTerm(String yearCode);
	
	/**
	 * 计算指定学期的综合指数
	 * @param schoolTerms 学期
	 * @author hely
	 * 2014.09.26
	 */
	public void computeRateResult();
	
	/**
	 * 将各个绩效指标存入report_rate表
	 * @author hely
	 * 2014.09.26
	 */
	public void storeRates();
	
	/**
	 * 将综合指数和绩效排名存入report_rate表
	 * @author hely
	 * 2014.11.10
	 */
	public void storeRank();
	
	/**
	 * 获取自主排课的学生数量
	 * @author hely
	 * 2014.11.05
	 */
	public int getStudentAmountBySelfCourse(int selfCourseId);
	
	/**
	 * 以时间倒叙的形式获取所有学期
	 * @author hely
	 * 2014.11.14
	 */
	public List<SchoolTerm> getAllSchoolTerms();
	
	/**
	 * 根据学院和学期获取绩效指标数据
	 * @param academyNumber 学院编号
	 * @param schoolTerm 学期
	 * @author hely
	 * 2014.12.02
	 */
	public ReportRate getReportRateByAcademyTerm(String academyNumber, SchoolTerm schoolTerm);
	
	/**
	 * 获取拥有分室的实验室信息
	 * @param labId 实验室id数组
	 * @param ratedCourseTime 额定课时数
	 * @param schoolTerms 学期
	 * @author hely
	 * 2014.12.03
	 */
	public LabRoom getLabRoomInfoByLabs(int[] labIdArr, int ratedCourseTime, List<SchoolTerm> schoolTerms); 
	
	/**
	 * 获取指定学期的学院绩效指标数据
	 * @param academyNumber 学院编号
	 * @param schoolTerms 学期
	 * @author hely
	 * 2014.12.11
	 */
	public ReportRate getReportRateByTermsAcademy(String academyNumber, List<SchoolTerm> schoolTerms);
	
	/**
	 * 根据学院编号获取学院名称
	 * @param academyNumber 学院编号
	 * @author hely
	 * 2015.03.19
	 */
	public String getAcademyNameByNumber(String academyNumber);
	
	/**
	 * 返回当前学期，如果放假则返回上一个学期
	 * @author hly
	 * 2015.07.21
	 */
	public SchoolTerm getPreviousSchoolTerm();
	
	/************************************************
	 * 设备学期使用情况表（计划任务）
	 * @author 贺子龙
	 * 2016-07-21
	 *************************************************/
	public void createSchoolDeviceUse(); 
	
	/****************************************************************************
	 * description：获得实验室使用情况列表
	 * @author：郑昕茹
	 * @date: 2017-05-10
	 ****************************************************************************/
	public List getListLabRoomUses(HttpServletRequest request, int page, int pageSize);
	
	/*************************************************************************************
	 * @description：实验室日志报表导出
	 * @author：郑昕茹
	 * @date：2017-05-10
	 *************************************************************************************/
	public void exportLabRoomUses(HttpServletRequest request, HttpServletResponse response) throws Exception ;
	
	/****************************************************
	 * 功能：系统报表-实验室利用率
	 * 作者： 贺子龙
	 * 日期：2016-10-14
	 *****************************************************/
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List getLabUseRate(HttpServletRequest request);
	
	/**************************************************
	 * @description：實驗室使用率報表導出
	 * @author：陳樂為
	 * @date：2016-10-24
	 **************************************************/
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void exportReportLabRate(HttpServletRequest request, HttpServletResponse response, List<Object[]> labRates,int flag) throws Exception;

	/****************************************************
	 * 功能：系统报表-实验室利用率--实验室
	 * 作者： 贺子龙
	 * 日期：2016-10-14
	 *****************************************************/
	@SuppressWarnings("rawtypes")
	public List getLabUseRateRoom(Integer centerId, Integer termId);
	/****************************************************************************
	 * description：获得实验室开设实验人数列表
	 * @author：戴昊宇
	 * @date: 2017-05-10
	 ****************************************************************************/
	public List getListLabItemHourNumber(HttpServletRequest request, int page, int pageSize);
	
	/****************************************************
	 * 功能：综合报表-实验室数量所占比例
	 * 作者： 王昊
	 * 日期：2017-10-26
	 *****************************************************/
	@SuppressWarnings({ "rawtypes"})
	public List getLabRoomRate(HttpServletRequest request);
	
	/****************************************************
	 * 功能：综合报表-大综合报表
	 * 作者： 王昊
	 * 日期：2017-10-26
	 *****************************************************/
	@SuppressWarnings({ "rawtypes"})
	public List getCountRate(HttpServletRequest request);
	
	/****************************************************
	 * 功能：综合报表-教师兼职、兼职所占比例
	 * 作者： 王昊
	 * 日期：2017-10-29
	 *****************************************************/
	@SuppressWarnings({ "rawtypes"})
	public List getTecherPercent(HttpServletRequest request);
	
	/****************************************************
	 * 功能：实验室人员现状-实验室教师学历结构
	 * 作者： 王昊
	 * 日期：2017-10-29
	 *****************************************************/
	@SuppressWarnings({ "rawtypes"})
	public List getTecherEducation(HttpServletRequest request);
	/****************************************************
	 * 功能：综合报表-实验室利用率
	 * 作者： 王昊
	 * 日期：2017-10-29
	 *****************************************************/
	@SuppressWarnings({ "rawtypes"})
	public List getLabUseRate1(HttpServletRequest request);
	/****************************************************
	 * 功能：实验室人员现状-实验室教师年龄结构
	 * 作者： 王昊
	 * 日期：2017-10-31
	 *****************************************************/
	@SuppressWarnings({ "rawtypes"})
	public List getTecherAge(HttpServletRequest request);
	/****************************************************
	 * 功能：实验室人员现状-实验室教师职称结构
	 * 作者： 王昊
	 * 日期：2017-10-31
	 *****************************************************/
	@SuppressWarnings({ "rawtypes"})
	public List getTecherLevel(HttpServletRequest request);
	
	/*************************************************************************************
	 * @description：实验学时统计报表导出
	 * @author：陈乐为	
	 * @date：2018-1-3
	 *************************************************************************************/
	public void exportLabItemHourNumber(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
