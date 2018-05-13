package net.xidlims.service.timetable;

import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import net.xidlims.domain.LabRoom;
import net.xidlims.domain.SchoolCourse;
import net.xidlims.domain.SchoolCourseDetail;
import net.xidlims.domain.SchoolTerm;
import net.xidlims.domain.SchoolWeek;

public interface SchoolCourseDetailService {

	/*************************************************************************************
	 * @內容：根据时间查找学期
	 * @作者： 魏誠
	 * @日期：2014-07-24
	 *************************************************************************************/
	public List<SchoolTerm> getSchoolTermsByNow();

	/*************************************************************************************
	 * @內容：创建一条通过周次和星期分组的语句
	 * @作者： 魏誠
	 * @日期：2014-07-24
	 *************************************************************************************/
	public List<SchoolWeek> getWeeksByNow(int termId);

	/*************************************************************************************
	 * @內容：获取可用的实验室分室
	 * @作者： 魏誠
	 * @日期：2014-07-24
	 *************************************************************************************/
	public Map<Integer, String> getLabRoomMap();

	/*************************************************************************************
	 * @內容：根据appointment_no获取实验安排表分组数
	 * @作者： 魏誠
	 * @日期：2014-07-24
	 *************************************************************************************/
	public int getGroupedCourseAppointmentsByAppointNo();

	/*************************************************************************************
	 * @內容：根据以选课组为单元的获取实验安排表分组
	 * @作者： 魏誠
	 * @日期：2014-07-24
	 *************************************************************************************/
	public List<SchoolCourseDetail> getCourseCodeInSchoolCourseDetail(int term);
	
	/*************************************************************************************
	 * @內容：根据以选课组为单元的获取实验安排表分组
	 * @作者： 魏誠
	 * @日期：2014-07-24
	 *************************************************************************************/
	public List<SchoolCourse> getCourseCodeInSchoolCourse(int term,int iLabCenter);

	/*************************************************************************************
	 * @內容：根据学期和状态获取所有的排课课程数量
	 * @作者： 魏誠
	 * @日期：2014-07-24
	 *************************************************************************************/
	public int getCountAllStatusTimetabledCourses(int termId, int statusId);

	/*************************************************************************************
	 * @內容：根据学期和状态获取已排课的课程列表
	 * @作者： 魏誠
	 * @日期：2014-07-24
	 *************************************************************************************/
	public List<SchoolCourse> getHavedTimetabledCourses(int termId,
			int statusId, int curr, int size);

	
	/*************************************************************************************
	 * @內容：根据coursedetail获取实验室
	 * @作者： 魏誠
	 * @日期：2014-07-24
	 *************************************************************************************/
	public Set<LabRoom> getLabsByCourse(SchoolCourseDetail courseDetail);

	/*************************************************************************************
	 * @內容：根据coursedetail获取实验室
	 * @作者： 魏誠
	 * @日期：2014-07-24
	 *************************************************************************************/
	public List<SchoolCourseDetail> getNeedTimetabledAppointments(
			List<SchoolCourseDetail> details);

	/*************************************************************************************
	 * @內容：进行教务排课，根据SchoolCourseDetail
	 * @作者： 魏誠
	 * @日期：2014-07-24
	 *************************************************************************************/
	public void doDirectTimetable(String courseCode,  String[] labs,String[] tutorRelateds,String[] teacherRelateds ,String[] sLabRoomDevice)  throws ParseException;

	/*************************************************************************************
	 * @內容：进行termid,获取教务CourseDetail的分页列表信息（教务排课的入口显示页面）
	 * @作者： 魏誠
	 * @日期：2014-07-24
	 *************************************************************************************/
	public List<SchoolCourseDetail> getCourseDetailsByQuery(int termId, SchoolCourseDetail schoolCourseDetail,
			int curr, int size,int iLabCenter);
	
	/*************************************************************************************
	 * @內容：实验室排课管理-排课-list(获取所有教务推送的排课和未安排时间的排课)
	 * @作者： 罗璇
	 * @日期：2017年3月8日
	 *************************************************************************************/
	public List<SchoolCourseDetail> getCourseDetailsAllByQuery(int termId, SchoolCourseDetail schoolCourseDetail,
			int curr, int size,int iLabCenter,HttpServletRequest request);

	/*************************************************************************************
	 * @內容：进行termid,获取计数获取教务CourseDetail的分页列表信息（教务排课的入口显示页面）
	 * @作者： 魏誠
	 * @日期：2014-07-24
	 *************************************************************************************/
	public int getCountCourseDetailsByQuery(int termId, SchoolCourseDetail schoolCourseDetail,int iLabCenter);
	
	/*************************************************************************************
	 * @內容：实验室排课管理-排课-list-count(获取所有教务推送的排课和未安排时间的排课)
	 * @作者： 罗璇
	 * @日期：2017年3月8日
	 *************************************************************************************/
	public int getCountCourseDetailsAllByQuery(int termId, SchoolCourseDetail schoolCourseDetail,int iLabCenter,HttpServletRequest request);
	
	/*************************************************************************************
	 * @內容：获取可用的周次教务排课信息json
	 * @作者： 魏誠
	 * @日期：2014-08-4
	 *************************************************************************************/
	public String getValidWeek(int weekday,String[] labrooms,String courseCode) ;

	/*************************************************************************************
	 * @內容：获取可用的周次教务排课信息map
	 * @作者： 魏誠
	 * @日期：2014-08-4
	 *************************************************************************************/
	public Map<Integer,Integer> getValidWeekMap(int weekday,String[] labrooms,String courseCode);
	
	/*************************************************************************************
	 * @內容：根据选课组编号获取教务排课信息
	 * @作者： 魏誠
	 * @日期：2014-08-4
	 *************************************************************************************/
	public List<SchoolCourseDetail> getSchoolCourseDetailByCourseCode(String courseCode);	
	
	/*************************************************************************************
	 * @ 根据课程编号联动获取选课组信息
	 * @作者： 张凯
	 * @日期：2017-03-16
	 *************************************************************************************/
	public Map<String,String> getSchoolCourseByCourseNumber(String courseNumber,Integer termId);
}