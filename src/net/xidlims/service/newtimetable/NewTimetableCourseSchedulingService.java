package net.xidlims.service.newtimetable;

import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.transaction.annotation.Transactional;

import net.xidlims.domain.LabRoomCourseCapacity;
import net.xidlims.domain.LabRoomDevice;
import net.xidlims.domain.OperationItem;
import net.xidlims.domain.SchoolCourseDetail;
import net.xidlims.domain.SchoolCourseStudent;
import net.xidlims.domain.SchoolWeek;
import net.xidlims.domain.TimetableAppointment;
import net.xidlims.domain.TimetableBatch;
import net.xidlims.domain.TimetableBatchStudent;
import net.xidlims.domain.TimetableGroup;
import net.xidlims.domain.TimetableGroupStudents;
import net.xidlims.domain.TimetableLabRelated;
import net.xidlims.domain.User;

public interface NewTimetableCourseSchedulingService {
	/*************************************************************************************
	 * @description：找到所有当前课程教师管理下的schoolcoursedetail
	 * @author：郑昕茹
	 * @date：2017-04-14
	 *************************************************************************************/
	public List<SchoolCourseDetail> findSchoolCourseDetailUnderPermission(SchoolCourseDetail schoolCourseDetail, int currpage, int pageSize, HttpServletRequest request);
	
	
	/*************************************************************************************
	 * @description：找到我负责的所有排课的课程（课程负责人和排课教师）
	 * @author：郑昕茹
	 * @date：2017-04-14
	 *************************************************************************************/
	public List<SchoolCourseDetail> findMyScheduleList(SchoolCourseDetail schoolCourseDetail, int currpage, int pageSize);
	
	/*************************************************************************************
	 * @description：根据找到学期下所有实验室的排课记录
	 * @author：郑昕茹
	 * @date：2017-04-14
	 *************************************************************************************/
	public List<TimetableLabRelated> getListLabTimetableAppointments(int roomId, int term);
	
	
	/*************************************************************************************
	 * @description：找到学期下指定课程的所有实验室的排课记录
	 * @author：郑昕茹
	 * @date：2017-04-19
	 *************************************************************************************/
	public List<TimetableLabRelated> getListLabTimetableAppointmentsByCourseDetailNo(HttpServletRequest request, 
			int term, String courseDetailNo);
	
	/*************************************************************************************
	 * @description：找到学期下指定课程的所有实验室的排课记录（根据排课类型）
	 * @author：贺子龙
	 * @date：2017-10-17
	 *************************************************************************************/
	public List<TimetableLabRelated> getTimetableAppointmentsByCourseDetailNoAndType(HttpServletRequest request, 
			int term, String courseDetailNo, Integer type);
	

	/*************************************************************************************
	 * @description：根据courseDetailNo和type,实验室找到排课批
	 * @author：郑昕茹
	 * @date：2017-04-21
	 *************************************************************************************/
	public List<TimetableBatch> findTimetableBatchByCourseDetailNoAndType(String courseDetailNo, Integer type);
	
	/*************************************************************************************
	 * @description：找到同组下周次和星期相同的排课记录
	 * @author：郑昕茹
	 * @date：2017-04-24
	 *************************************************************************************/
	public TimetableAppointment findTimetableAppointmentByGroupIdAndSameWeekAndWeekday(Integer groupId, Integer week, Integer weekday);
	

	/*************************************************************************************
	 * @description：根据组号分页找学生名单
	 * @author：郑昕茹
	 * @date：2017-04-24
	 *************************************************************************************/
	public List<TimetableGroupStudents> findTimetableGroupStudentsByGroupId(Integer groupId, int page, int pageSize, HttpServletRequest request);
	
	/*************************************************************************************
	 * @description：根据组号分页找学生名单
	 * @author：郑昕茹
	 * @date：2017-04-24
	 *************************************************************************************/
	public List<TimetableGroupStudents> findTimetableGroupStudentsByGroupId(Integer groupId, int page, int pageSize);
	
	/*************************************************************************************
	 * @description：根据当前三个时间维度和选课组编号查看是否有冲突的排课安排,学生
	 * @author：罗璇
	 * @date：2017年3月14日
	 *************************************************************************************/
	public boolean checkSingleStudentByCourseCodeAndTime(Integer week, Integer weekday, Integer classes, String courseDetailNo, String student);
	
	/*************************************************************************************
	 * @description：根据当前三个时间维度和选课组编号查看是否有冲突的排课安排,获取冲突学生数量
	 * @author：罗璇
	 * @date：2017年3月14日
	 *************************************************************************************/
	public Integer getConflictStudentsByCourseCodeAndTime(Integer week, Integer weekday, Integer classes, String courseDetailNo, Set<TimetableGroupStudents> students);
	
	
	/*************************************************************************************
	 * @description：根据学生找到其已参与的排课
	 * @author：郑昕茹
	 * @date：2017-04-24
	 *************************************************************************************/
	public Map<String, Integer> getConflictTimetableAndNum(Set<TimetableGroupStudents> students, Integer[] chosenWeeks);
	
	/*************************************************************************************
	 * @description：找到我负责的所有排课的课程（课程负责人和排课教师）合班后的结果
	 * @author：郑昕茹
	 * @date：2017-04-26
	 *************************************************************************************/
	public List<Object[]> findMyScheduleListView(HttpSession session,SchoolCourseDetail schoolCourseDetail, int currpage, int pageSize);
	
	/*************************************************************************************
	 * @description：找到学期下和班课程下所有课程的所有实验室的排课记录
	 * @author：郑昕茹
	 * @date：2017-04-26
	 *************************************************************************************/
	public List<TimetableLabRelated> getListLabTimetableAppointmentsByMergeId(HttpServletRequest request, 
			int term, Integer mergeId);
	
	/*************************************************************************************
	 * @description：找到学期下和班课程下所有课程的所有实验室的排课记录（根据排课类型）
	 * @author：郑昕茹
	 * @date：2017-04-26
	 *************************************************************************************/
	public List<TimetableLabRelated> getTimetableAppointmentsByMergeIdAndType(HttpServletRequest request, 
			int term, Integer mergeId, Integer type);

	/*************************************************************************************
	 * @description：找到学期下当前登录人（学生）的所有排课,根据学期和课程有查询
	 * @author：郑昕茹
	 * @date：2017-04-27
	 *************************************************************************************/
	public List<TimetableAppointment> findMyTimetableStudentsByTermAndCourseName(Integer termId, String courseName);
	
	/*************************************************************************************
	 * @description：根据courseDetailNo和type和实验项目找到排课批
	 * @author：郑昕茹
	 * @date：2017-04-28
	 *************************************************************************************/
	public TimetableBatch findTimetableBatchByCourseDetailNoAndTypeAndItemId(String courseDetailNo, Integer type, Integer itemId);
	
	
	
	/*************************************************************************************
	 * @description：找到学期下登录人可以选课的所有组(非合班)
	 * @author：郑昕茹
	 * @date：2017-04-28
	 *************************************************************************************/
	public List<TimetableGroup> findTimetableGroupsCanSelectByCurrUser();
	
	/*************************************************************************************
	 * @description：找到批次找到其下所有排课
	 * @author：郑昕茹
	 * @date：2017-04-30
	 *************************************************************************************/
	public List<TimetableAppointment> findTimetableAppointmentsByBatchId(Integer batchId);
	
	/*************************************************************************************
	 * @description：根据批次和当前学生找到批次学生记录
	 * @author：郑昕茹
	 * @date：2017-04-30
	 *************************************************************************************/
	public List<TimetableBatchStudent> findTimetableBatchStudentsByBatchId(Integer batchId);
	
	
	/*************************************************************************************
	 * @description：判断学生是否有冲突的选课
	 * @author：郑昕茹
	 * @date：2017-04-30
	 *************************************************************************************/
	public boolean checkSingleStudentTime(Integer week, Integer weekday, Integer startClass, Integer endClass, String student);
	
	
	/*************************************************************************************
	 * @description：根据组号和当前学生找到组号学生记录
	 * @author：郑昕茹
	 * @date：2017-04-30
	 *************************************************************************************/
	public List<TimetableGroupStudents> findTimetableGroupStudentsByGroupId(Integer groupId);
	
	
	/*************************************************************************************
	 * @description：找到学期下登录人可以选课的所有组(合班)
	 * @author：郑昕茹
	 * @date：2017-05-02
	 *************************************************************************************/
	public List<TimetableGroup> findTimetableGroupsCanSelectInMergeCourseByCurrUser();
	
	/*************************************************************************************
	 * @description：找到批次下的所有组，并排序
	 * @author：郑昕茹
	 * @date：2017-05-02
	 *************************************************************************************/
	public List<TimetableGroup> findTimetableGroupsByBacthId(Integer batchId);
	
	
	/*************************************************************************************
	 * @description：判断学生是否有冲突的选课,不包含本身
	 * @author：郑昕茹
	 * @date：2017-05-02
	 *************************************************************************************/
	public boolean checkSingleStudentTimeExceptSelfAppointment(String student, Integer appointmentId);
	
	

	/*************************************************************************************
	 * @description：根据组号分页找学生名单(未排入任何一组的学生)
	 * @author：郑昕茹
	 * @date：2017-05-03
	 *************************************************************************************/
	public List<SchoolCourseStudent> findSchoolCourseStudentsNotInBatchGroup(Integer batchId, int page, int pageSize,Integer isMerge, String courseCode,HttpServletRequest request);
	
	
	/*************************************************************************************
	 * @description：根据courseCode找到该课程下的所有批次
	 * @author：郑昕茹
	 * @date：2017-05-03
	 *************************************************************************************/
	public List<TimetableBatch> findTimetableBatchsByCourseCodeAndType(String courseCode);
	
	/*************************************************************************************
	 * @description：找到学期下登录人可以选课的所有组(全校公选课)
	 * @author：郑昕茹
	 * @date：2017-05-03
	 *************************************************************************************/
	public List<TimetableGroup> findTimetableGroupsCanSelectInPublicCourse();
	
	
	/*************************************************************************************
	 * @description：根据courseDetailNo找到其下所有公选课排课
	 * @author：郑昕茹
	 * @date：2017-05-03
	 *************************************************************************************/
	public List<TimetableAppointment> findTimetableAppointmentsByCourseDetailNo(String courseDetailNo);
	
	/************************************************************
	 * @ 获取可排的实验分室列表 @ 作者：魏诚 @ 日期：2014-07-24
	 ************************************************************/
	public Map<Integer, String> getLabRoomMap();
	
	/**
	 * Description 根据课程获取容量匹配的实验室
	 * 
	 * @author 陈乐为
	 * @date 2017-9-25
	 */
	public Map<Integer, String> getLabRoomMapByCourseDetailNo(String courseDetailNo);
	
	/*************************************************************************************
	 * @description：根据courseDetailNo和labId找到其分批的容量
	 * @author：郑昕茹
	 * @date：2017-05-05
	 *************************************************************************************/
	public LabRoomCourseCapacity findLabRoomCourseCapacityByCourseDetailNoAndLabId(String courseDetailNo, Integer labId);
	
	/*************************************************************************************
	 * @description：根据合班课程的id找到其下所有学生
	 * @author：郑昕茹
	 * @date：2017-05-07
	 *************************************************************************************/
	public List<SchoolCourseStudent> findSchoolCourseStudentsByMergeId(Integer mergeId);
	
	/*************************************************************************************
	 * @description：根据合班课程的id找到其下所有学生数量
	 * @author：贺子龙
	 * @date：2017-10-04
	 *************************************************************************************/
	public int countSchoolCourseStudentsByMergeId(Integer mergeId);
	
	/*************************************************************************************
	 * @description：根据courseDetailNo找到其下所有学生
	 * @author：郑昕茹
	 * @date：2017-05-07
	 *************************************************************************************/
	public List<User> findUsersByCourseDetailNo(String courseDetailNo);
	
	
	/*************************************************************************************
	 * @description：根据groupId找到其下所有学生
	 * @author：郑昕茹
	 * @date：2017-05-07
	 *************************************************************************************/
	public List<User> findUsersByGroupId(Integer groupId);
	
	/*************************************************************************************
	 * @description：根据周次，星期，学期找到对应的schoolWeek
	 * @author：郑昕茹
	 * @date：2017-05-09
	 *************************************************************************************/
	public SchoolWeek findSchoolWeekByWeekAndWeekdayAndTerm(Integer week, Integer weekday, Integer termId);
	

	/*************************************************************************************
	 * @description：根据mergeId找到其下所有学生
	 * @author：郑昕茹
	 * @date：2017-05-10
	 *************************************************************************************/
	public List<String> findUsersNameByMergeId(String mergeId, String classNumber);
	
	/*************************************************************************************
	 * @description：根据groupId找到其下所有学生(username)
	 * @author：郑昕茹
	 * @date：2017-05-10
	 *************************************************************************************/
	public List<String> findUsernamesByGroupId(Integer groupId);
	
	
	/*************************************************************************************
	 * @description：根据courseDetailNo找到其下所有学生(username)
	 * @author：郑昕茹
	 * @date：2017-05-10
	 *************************************************************************************/
	public List<String> findUsernamesByCourseDetailNo(String courseDetailNo,String classNumber);
	public List<String> findUsernamesByCourseDetailNo(String courseDetailNo);
	
	
	@Transactional(readOnly = false)  
    public void batchInsertAndUpdate(List list);
	
	
	/*************************************************************************************
	 * @description：找到同组下周次和星期，项目相同的排课记录
	 * @author：郑昕茹
	 * @date：2017-04-24
	 *************************************************************************************/
	public TimetableAppointment findTimetableAppointmentByGroupIdAndSameWeekAndWeekdayAndItem(Integer groupId, Integer week, Integer weekday, Integer itemId);

	/************************************************************ 
	 * @description：我的课表（超级管理员）
	 * @author：郑昕茹
	 * @date：2017-05-14
	 ************************************************************/
	public Map<String, Object> findTimetableAdmin(HttpServletRequest request);
	
	/************************************************************ 
	 * @description：我的课表（超级管理员）
	 * @author：郑昕茹
	 * @date：2017-05-14
	 ************************************************************/
	public Map<String, Object> findTimetableStudent(HttpServletRequest request);
	
	/*************************************************************************************
	 * @description：找到detail其下所有排课
	 * @author：郑昕茹
	 * @date：2017-04-30
	 *************************************************************************************/
	public List<TimetableAppointment> findTimetableAppointmentsByCourseDetailNoInSpecial(String courseDetailNo);
	
	/*************************************************************************************
	 * @description：找到detail其下基础课所有排课的项目
	 * @author：郑昕茹
	 * @date：2017-05-14
	 *************************************************************************************/
	public List<OperationItem> findOperationItemsByCourseDetailNoInSpecial(String courseDetailNo) ;
	
	/*************************************************************************************
	 * @description：找到mergeId其下所有排课
	 * @author：郑昕茹
	 * @date：2017-05-14
	 *************************************************************************************/
	public List<TimetableAppointment> findTimetableAppointmentsByMergeId(Integer mergeId);
	
	
	/*************************************************************************************
	 * @description：找到merge其下基础课所有排课的项目
	 * @author：郑昕茹
	 * @date：2017-05-14
	 *************************************************************************************/
	public List<OperationItem> findOperationItemsByMergeIdInSpecial(Integer mergeId);
	
	/************************************************************ 
	 * @description：我的课表（课程负责教师）
	 * @author：郑昕茹
	 * @date：2017-05-14
	 ************************************************************/
	public Map<String,Object> findTimetableCourseTeacher(HttpServletRequest request);
	
	/************************************************************ 
	 * @description：我的课表（普通教师）
	 * @author：郑昕茹
	 * @date：2017-05-14
	 ************************************************************/
	public Map<String, Object> findTimetableTeacher(HttpServletRequest request);
	
	/*************************************************************************************
	 * @description：找到所有当前实验中心管理员下的schoolcoursedetail
	 * @author：郑昕茹
	 * @date：2017-05-18
	 *************************************************************************************/
	public List<SchoolCourseDetail> findLabSchoolCourseDetailUnderPermission(SchoolCourseDetail schoolCourseDetail, int currpage, int pageSize, HttpServletRequest request);
	/*************************************************************************************
	 * @description：
	 * @author：周志辉
	 * @date：2017-09-27
	 *************************************************************************************/
	public List<String> findUserBygroupId(int groupid);
	
		
}