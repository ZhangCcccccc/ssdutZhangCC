package net.xidlims.service.timetable;

import java.text.ParseException;
import java.util.List;
import java.util.Set;

import net.xidlims.common.LabAttendance;
import net.xidlims.web.timetable.AttendancetableByWeek;
import net.xidlims.web.timetable.CheckAttendancetable;
import net.xidlims.domain.SchoolCourse;
import net.xidlims.domain.SchoolCourseDetail;
import net.xidlims.domain.SchoolCourseStudent;
import net.xidlims.domain.TimetableAppointment;
import net.xidlims.domain.TimetableAttendance;
import net.xidlims.domain.TimetableGroupStudents;
import net.xidlims.domain.User;

import org.springframework.web.bind.annotation.RequestParam;

import excelTools.Attendance;

/**
 * Spring service that handles CRUD requests for TimetableAttendance entities
 * 
 */
public interface TimetableAttendanceService {

	/**
	 * Save an existing TimetableAttendance entity
	 * 
	 */
	public void saveTimetableAttendance(TimetableAttendance timetableattendance);

	/**
	 * Delete an existing TimetableAttendance entity
	 * 
	 */
	public void deleteTimetableAttendance(TimetableAttendance timetableattendance_1);
	
	
	public void saveTimetableAttendance(TimetableAttendance timetableattendance,Integer id,Integer idKey);
	
	/*public List<TimetableAppointment> getTimetableAppointmentsByQuery(int curr, int size);*/
	
	/*public int getCountTimetableAppointmentsByQuery();*/
	
	public int getCountTimetableAppointmentsByQuery(int status);
	
	public List<TimetableAppointment> getTimetableAppointmentsByQuery(
			int status,SchoolCourseDetail schoolCourseDetail, int curr, int size);
	
	/*public List<TimetableAppointment> getTimetableAppointmentsByQuery(TimetableAppointment timetableAppointment,
			int status, int curr, int size);*/
	
	public void saveMachineAttendance(List<TimetableAppointment> timetableAppointment) throws ParseException;
	/*************************************************************************************
	 * @內容：根据排课和周次查询考勤
	 * @作者： 李小龙
	 *************************************************************************************/
	public List<TimetableAttendance> showStudentAttendanceByWeek(TimetableAppointment t,Integer week);
	
	public void saveStudentAttendanceByWeek(String username,Integer id,Integer idKey,Integer flag);
	
	public List<CheckAttendancetable> checkTotalCourseAttendance(Set<TimetableAppointment> listtimeappoints,List<SchoolCourseStudent> schoolCourseStudent);
	
	public List<AttendancetableByWeek> showTimetableGroupStudentsByWeek(int studentNumber,TimetableAppointment t,
			List<TimetableGroupStudents> TimetableGroupStudents,@RequestParam Integer id,
			@RequestParam Integer idKey);
	
	
	/*************************************************************************************
	 * @內容：查看所有的预约的列表安排
	 * @作者： 魏誠
	 * @日期：2014-07-24
	 *************************************************************************************/
	public List<TimetableAppointment> getTimetableAppointmentsByQuery(TimetableAppointment timetableAppointment,
			int status,int iLabCenter, int curr, int size);
	
	public int getCountTimetableAppointmentsByQuery(TimetableAppointment timetableAppointment,int status,int iLabCenter);
	/*************************************************************************************
	 * @內容：查询考勤信息表 timetable_attendance
	 * @作者： 徐龙帅
	 * @日期：2014-12-4
	 *************************************************************************************/
public List<TimetableAppointment> findTimetableAttendance(TimetableAppointment timetableAppointment);
public List<TimetableAppointment> findTimetableAttendance(TimetableAppointment timetableAppointment,int page,int pageSize);


/*************************************************************************************
 * 根据选课组编号查询学生名单
 * 李小龙
 *************************************************************************************/
public List<CheckAttendancetable> findStudentsByCourseCode(TimetableAppointment t) ;
/*************************************************************************************
 * 根据排课记录查询学生名单
 * 李小龙
 *************************************************************************************/
public Set<User> findStudentsByTimetableAppointment(TimetableAppointment timeAppt);
/*************************************************************************************
 * 根据学生名单和周次获取学生成绩
 * 李小龙
 *************************************************************************************/
public List<CheckAttendancetable> getTearmScoreByStudents(Set<User> students,int weeks,TimetableAppointment t);
/*************************************************************************************
 * 根据成绩结果获取成绩次数（取最大值）
 * 李小龙
 *************************************************************************************/
public int getTearmScoreTime(List<CheckAttendancetable> checkList);
/*************************************************************************************
 * 查询出当前登录人可以查看的课程考勤
 * 李小龙
 *************************************************************************************/
public List<Attendance> findAttendanceBySchoolCourse(SchoolCourse course,int cid);
/*************************************************************************************
 * 查询出当前登录人可以查看的实验室考勤
 * 贺子龙
 * 2015-11-27
 *************************************************************************************/
public List<LabAttendance> findLabAttendance(String username, String cname,String labName,int cid,int currpage, int pageSize);
/*************************************************************************************
 * 查询出当前登录人可以查看的实验室考勤人数
 * 贺子龙
 * 2015-11-27
 *************************************************************************************/
public int findLabAttendance(String username, String cname,String labName,int cid);

}
