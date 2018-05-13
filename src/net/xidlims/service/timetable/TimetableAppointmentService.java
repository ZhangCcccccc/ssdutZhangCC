package net.xidlims.service.timetable;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.xidlims.constant.MonthReport;
import net.xidlims.domain.SchoolCourseDetail;
import net.xidlims.domain.TimetableAppointment;
import net.xidlims.domain.TimetableLabRelated;
import net.xidlims.domain.TimetableTeacherRelated;
import net.xidlims.domain.User;

import org.springframework.transaction.annotation.Transactional;

public interface TimetableAppointmentService {

	/*************************************************************************************
	 * @內容：查看所有的时间列表安排
	 * @作者： 魏誠
	 * @日期：2014-07-24
	 *************************************************************************************/
	public List<TimetableAppointment> getTimetableAppointmentsByQuery(int termId,TimetableAppointment timetableAppointment,int status,int curr,
			int size,int iLabCenter);

	/*************************************************************************************
	 * @內容：查看计数的所有时间列表安排
	 * @作者： 魏誠
	 * @日期：2014-07-24
	 *************************************************************************************/
	public int getCountTimetableAppointmentsByQuery(int termId,TimetableAppointment timetableAppointment,int status,int iLabCenter);
	
	/*************************************************************************************
     * @內容：查看所有的时间列表安排
     * @作者：贺子龙
     * @日期：2016-04-09
     *************************************************************************************/
    public List<TimetableAppointment> getTimetableAppointmentsByQuery(int termId,String courseNumber,int status,int curr,
            int size,int iLabCenter);

    /*************************************************************************************
     * @內容：查看计数的所有时间列表安排
     * @作者：贺子龙
     * @日期：2016-04-09
     *************************************************************************************/
    public int getCountTimetableAppointmentsByQuery(int termId,String courseNumber,int status,int iLabCenter);
	
	public String[] getTimetableWeekClass(int[] intWeeks);
	/*************************************************************************************
	 * @內容：根据实验室和节次及星期列出所有二次排课时间列表安排
	 * @作者： 魏誠
	 * @日期：2014-07-24
	 *************************************************************************************/
	public List<TimetableLabRelated> getReListLabTimetableAppointments(HttpServletRequest request,int iLabCenter,int term);
	
	/*************************************************************************************
	 * @內容：根据实验室和节次及星期列出所有自建课程排课时间列表安排
	 * @作者： 魏誠
	 * @日期：2014-07-24
	 *************************************************************************************/
	public List<TimetableLabRelated> getSelfListLabTimetableAppointments(HttpServletRequest request,int iLabCenter,int term);

	/*************************************************************************************
	 * @內容：发布所选选课组所在的排课内容
	 * @作者： 魏誠
	 * @日期：2014-08-4
	 *************************************************************************************/
	@Transactional
	public void doReleaseTimetableAppointments(String courseCode,int flag) ;

	/*************************************************************************************
	 * @內容：保存调整排课的内容
	 * @作者： 魏誠
	 * @日期：2014-08-5
	 *************************************************************************************/
	public SchoolCourseDetail saveAdjustTimetable(HttpServletRequest request) throws ParseException;
	
	/*************************************************************************************
	 * @內容：保存二次排课的不分组排课的内容
	 * @作者： 魏誠
	 * @日期：2014-08-5
	 *************************************************************************************/
	public TimetableAppointment saveNoGroupReTimetable(HttpServletRequest request)  throws ParseException ;
	
	/*************************************************************************************
	 * @內容：保存二次排课的分组排课的内容
	 * @作者： 魏誠
	 * @日期：2014-08-5
	 *************************************************************************************/
	@Transactional
	public TimetableAppointment saveGroupReTimetable(HttpServletRequest request)  throws ParseException ;
	
	/*************************************************************************************
	 * @內容：排课管理中，保存修改的排课的内容
	 * @作者： 魏誠
	 * @日期：2014-08-5
     *************************************************************************************/
	@Transactional
	public void saveAdminTimetable(HttpServletRequest request);
	
	/*************************************************************************************
	 * @內容：排课管理中，删除排课信息的内容
	 * @作者： 魏誠
	 * @日期：2014-08-5
	 *************************************************************************************/
	@Transactional
	public void deleteAppointment(int id) ;
	
	/*************************************************************************************
	 * @內容：进行termid,获取教务选课组编号
	 * @作者： 魏誠
	 * @日期：2014-07-24
	 *************************************************************************************/
	public String getAdminCourseCodeList(int term,int iLabCenter);

	/*************************************************************************************
	 * @throws ParseException 
	 * @內容：根据学期和中心获取月报报表
	 * @作者： 贺子龙
	 * @日期：2015-12-27
	 *************************************************************************************/
	public List<MonthReport> getMonthReports(int term, int year, int month,
			int cid) throws ParseException;
	
	/*************************************************************************************
	 * @description：获取所有排课相关的教师
	 * @author： 郑昕茹
	 * @date：2016-11-30
	 *************************************************************************************/
	public  List<User>  getAllTimetableRelatedTeachers();
	
	/*************************************************************************************
	 * @description：根据当前三个时间维度和选课组编号查看是否有冲突的排课安排
	 * @author：罗璇
	 * @date：2017年3月14日
	 *************************************************************************************/
	public String checkStudentsByCourseCodeAndTime(HttpServletRequest request);
	
	/*************************************************************************************
	 * @description：根据学院获取排课
	 * @author： 张凯
	 * @date：2017-3-12
	 *************************************************************************************/
	/*public List<TimetableAppointment> getTimetableAppointmentByAcademyAndTeacher();*/
	/*************************************************************************************
     * @內容：查看所有的预约的列表安排
     * @作者：贺子龙
     * @日期：2016-04-09
     *************************************************************************************/
    public List<TimetableAppointment> getTimetableAppointmentsByQueryNew(int termId,
            String courseNumber, int status, int curr, int size, int iLabCenter, HttpServletRequest request);
    
    /*************************************************************************************
     * @內容：查看计数的所有时间列表安排
     * @作者： 贺子龙
     * @日期：2016-04-09
     *************************************************************************************/
    public int getCountTimetableAppointmentsByQueryNew(int termId, String courseNumber, int status,
            int iLabCenter, HttpServletRequest request);
}