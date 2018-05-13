package net.xidlims.service.timetable;

import java.util.List;
import java.util.Map;

import net.xidlims.domain.SchoolCourse;
import net.xidlims.domain.SchoolCourseStudent;
import net.xidlims.domain.TCourseSite;

public interface SchoolCourseService {
	
	/*************************************************************************************
	 * @內容：进行termid,获取教务选课组编号
	 * @作者： 魏誠
	 * @日期：2014-09-16
	 *************************************************************************************/
	public List<SchoolCourse> getCourseCodeListAll(int termId,int iLabCenter);
	
	/*************************************************************************************
	 * @內容：进行termid,获取教务选课组编号
	 * @作者： 贺子龙
	 * @日期：2017-01-02
	 *************************************************************************************/
	public Map<String, String> getCourseCodeListMap(int termId,int iLabCenter);
	
	/*************************************************************************************
	 * @內容：进行termid,获取教务选课组编号
	 * @作者： 魏誠
	 * @日期：2014-07-24
	 *************************************************************************************/
	public String getCourseCodeList(int term,int iLabCenter);

	/*************************************************************************************
	 * @內容：进行termid,获取教务选课组编号
	 * @作者： 魏誠
	 * @日期：2014-09-16
	 *************************************************************************************/
	public List<SchoolCourse> getCourseCodeList(int termId);
	/*************************************************************************************
     * @內容：根据username找到学生信息
     * @作者： 戴昊宇
     * @日期：2017-09-27
     *************************************************************************************/
    public List<SchoolCourseStudent> findSchoolCourseStudentByStudentNumber(String studentNumber);
    
    /*************************************************************************************
     * @內容：新建课程时保存相应的详细SchoolCourse课程信息
     * @作者： 张佳鸣
     * @日期：2017-10-18
     *************************************************************************************/
    public void saveSchoolCourseByTCourseSite(TCourseSite tCourseSite);
    
    /*************************************************************************************
     * @內容：根据CourseNumber查找所有的SchoolCourse
     * @作者： 张佳鸣
     * @日期：2017-10-18
     *************************************************************************************/
    public List<SchoolCourse> findSchoolCoursesByCourseNumber(String courseNumber);
    
    /*************************************************************************************
     * @內容：根据课程代码查找合班信息并返回课序号
     * @作者： 张佳鸣
     * @日期：2017-10-23
     *************************************************************************************/
    public String findSchoolCourseMergeCourseNumberByCourseCode(String courseCode);
    /*************************************************************************************
   	 * Description:学生查询的ajax调用
   	 *
   	 * @author：戴昊宇
   	 * @date：2017-10-25
   	 *************************************************************************************/
   	public List<Map<String,String>> coolSuggestTuser(String tUserName);
    /*************************************************************************************
 	 * @內容：根据courseNo找到学生
 	 * @作者： 戴昊宇
 	 * @日期：2017-09-27
 	 *************************************************************************************/
 	   public List<SchoolCourseStudent> findSchoolCourseStudentByCourseNo(String courseDetailNo);
	
}