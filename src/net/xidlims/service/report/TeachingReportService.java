package net.xidlims.service.report;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.xidlims.domain.LabRoom;
import net.xidlims.domain.SchoolCourse;
import net.xidlims.domain.SchoolCourseDetail;
import net.xidlims.domain.SchoolTerm;
import net.xidlims.domain.TimetableAppointment;
import net.xidlims.domain.TimetableLabRelated;
import net.xidlims.domain.TimetableSelfCourse;



public interface TeachingReportService {


	/************************************************************
	 * @內容：获取周次，用于下拉框
	 * @作者：何立友
	 * @日期：2014-09-11
	 ************************************************************/
	public Map<Integer, String> getWeekMap();
	
	/*************************************************************************************
	 * @內容：根据实验室和节次及星期列出所有时间列表安排
	 * @作者： 何立友
	 * @日期：2014-09-12
	 *************************************************************************************/
	public List<TimetableLabRelated> getLabTimetableAppointments(int termId, int labCenterId, int week);
	
	/*************************************************************************************
	 * @內容：据实验室和节次及星期列出所有时间列表安排
	 * @作者： 何立友
	 * @日期：2015-05-12
	 *************************************************************************************/
	public List<TimetableLabRelated> getSelfLabTimetableAppointments(int termId, int labCenterId, int week);
	
	/*************************************************************************************
	 * @內容：获取指定学期、登录者所在学院、有课程的教师(ajax)
	 * @作者： 何立友
	 * @日期：2014-09-12
	 *************************************************************************************/
	public String getTeachersByTerm(int termId, int labCenterId);
	
	/*************************************************************************************
	 * @內容：获取指定学期、指定教师选课组(ajax)
	 * @作者： 何立友
	 * @日期：2014-09-17
	 *************************************************************************************/
	public String getCourseDetailsByTermTeacher(int termId, String username);
	
	/*************************************************************************************
	 * @內容：根据courseNo(主键)获取课程
	 * @作者： 何立友
	 * @日期：2014-09-12
	 *************************************************************************************/
	public SchoolCourse getCourseByCourseNo(String courseNo);
	
	/*************************************************************************************
	 * @內容：根据courseDetailNo(主键)获取选课组
	 * @作者： 何立友
	 * @日期：2014-09-17
	 *************************************************************************************/
	public SchoolCourseDetail getCourseDetailByCourseDetailNo(String courseDetailNo);
	
	/*************************************************************************************
	 * @內容：根据courseCode获取自主排课选课组
	 * @作者： 何立友
	 * @日期：2015-05-13
	 *************************************************************************************/
	public TimetableSelfCourse getTimetableSelfCourseByCourseCode(String courseCode);
	
	/*************************************************************************************
	 * @內容：获取指定学期、登录者所在学院、有课程的教师(Map)
	 * @作者： 何立友
	 * @日期：2014-09-12
	 *************************************************************************************/
	public Map<String, String> getTeachersMapByTerm(int termId);
	
	/*************************************************************************************
	 * @內容：获取指定学期、指定教师课程(Map)
	 * @作者： 何立友
	 * @日期：2014-09-12
	 *************************************************************************************/
	public Map<String, String> getCourseDetailsMapByTermTeacher(int termId, String username);
	
	/*************************************************************************************
	 * @內容：根据课程获取排课信息
	 * @作者： 何立友
	 * @日期：2014-09-12
	 *************************************************************************************/
	public List<TimetableAppointment> getTimetableAppointmentByCourseDetail(String courseDetailNo); 
	
	/*************************************************************************************
	 * @內容：根据学期获取已发布的排课信息
	 * @作者： 何立友
	 * @日期：2014-09-13
	 *************************************************************************************/
	public List<SchoolCourseDetail> getSchoolCourseDetailByTerm(int termId, int labCenterId);
	
	/**
	 * @內容：根据学期获取已发布的自主排课信息
	 * @作者： 何立友
	 * @日期：2015-05-13
	 */
	public List<TimetableSelfCourse> getTimetableSelfCourseByTerm(int termId, int labCenterId);
	
	/*************************************************************************************
	 * @內容：上机登记报表导出Excel
	 * @作者： 何立友
	 * @日期：2014-09-14
	 *************************************************************************************/
	public void exportExperimentRegister(SchoolCourseDetail schoolCourseDetail, HttpServletRequest request, HttpServletResponse response) throws Exception;

	/*************************************************************************************
	 * @內容：实验准备报表导出Excel
	 * @作者： 何立友
	 * @日期：2014-09-15
	 *************************************************************************************/
	public void exportExperimentPrepare(SchoolTerm schoolTerm, int labCenterId, HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	/*************************************************************************************
	 * @內容：实验准备报表导出Excel
	 * @作者： 何立友
	 * @日期：2014-10-30
	 *************************************************************************************/
	public void exportTermRegister(SchoolTerm schoolTerm, int labCenterId, HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	/*************************************************************************************
	 * @內容：获取登录者所在学院的实验室
	 * @作者： 何立友
	 * @日期：2014-10-30
	 *************************************************************************************/
	public List<LabRoom> getLabRooms();
	
	/*************************************************************************************
	 * @內容：根据参数条件获取排课信息
	 * @作者： 何立友
	 * @日期：2014-10-30
	 *************************************************************************************/
	public List<TimetableLabRelated> getLabTimetableAppointmenmt(int termId, int startClass, int endClass, int labId, int weekday, String academyNumber);
	/*************************************************************************************
	 * @throws Exception 
	 * @內容：导出周次登记
	 * @作者： 徐龙帅
	 * @日期：2015-01-14
	 *************************************************************************************/
	public void exportweekRegister(SchoolTerm schoolTerm, int labCenterId, HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	/*************************************************************************************
	 * @內容：查找指定中心的实验室分室
	 * @作者： 何立友
	 * @日期：2015-06-17
	 *************************************************************************************/
	public List<LabRoom> getLabRoomsByLabCenter(int labCenterId);
	/*************************************************************************************
	 * @內容：月报报表导出Excel
	 * @作者：贺子龙
	 * @日期：2015-11-16
	 *************************************************************************************/
	public void exportMonthRegister(SchoolTerm schoolTerm, int labCenterId, HttpServletRequest request, HttpServletResponse response) throws Exception;
	
}