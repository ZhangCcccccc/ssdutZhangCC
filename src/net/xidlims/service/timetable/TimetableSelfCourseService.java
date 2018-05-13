package net.xidlims.service.timetable;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.xidlims.domain.SchoolCourseInfo;
import net.xidlims.domain.TimetableSelfCourse;

import org.springframework.transaction.annotation.Transactional;

public interface TimetableSelfCourseService {
	/*************************************************************************************
	 * @內容：获取自建课程信息的分页列表信息
	 * @作者： 魏诚
	 * @日期：2014-08-25
	 *************************************************************************************/
	public List<TimetableSelfCourse> findAllTimetableSelfCourses(
			TimetableSelfCourse timetableSelfCourse, int curr, int size, Integer cid) ;
	
	/*************************************************************************************
	 * @內容：查找所有的自建课程信息
	 * @作者：魏诚
	 * @日期：2014-08-25
	 *************************************************************************************/
	public List<TimetableSelfCourse> findAllTimetableSelfCourses(int curr, int size);
	
	/*************************************************************************************
	 * @內容：获取查找自建课程信息记录数
	 * @作者： 魏诚
	 * @日期：2014-08-25
	 *************************************************************************************/
	@Transactional
	public int getTimetableSelfCourseTotalRecords();
	
	/*************************************************************************************
	 * @內容：获取最大自建课程信息id
	 * @作者： 魏诚
	 * @日期：2014-08-25
	 *************************************************************************************/
	@Transactional
	public int getMaxTimetableSelfCourseId();
	
	/*************************************************************************************
	 * @內容：根据以选课组为单元的获取实验安排表分组
	 * @作者： 魏誠
	 * @日期：2014-07-24
	 *************************************************************************************/
	public List<TimetableSelfCourse> getCourseCodeInTimetableSelfCourse(int term);
	
	/*************************************************************************************
	 * @內容：保存 新建自主排课
	 * @作者： 魏誠
	 * @日期：2014-07-24
	 *************************************************************************************/
	public void saveSelfSchoolCourseInfo(SchoolCourseInfo schoolCourseInfo,
			String flagID);
	
	/*************************************************************************************
	 * @內容：保存 新建自主排课的选课组
	 * @作者： 魏誠
	 * @日期：2014-07-24
	 *************************************************************************************/
	public String saveTimetableSelfCourse(HttpServletRequest request,TimetableSelfCourse timetableSelfCourse,
			int flagID);
	
	/**************************************************************************************
	 * @自主排课： 删除id对应的批次的所有记录
	 * @作者：魏诚
	 * @日期：2014-07-25
	 **************************************************************************************/
	public String deleteBatch(int id, int term, String courseCode);
	
	/**************************************************************************************
	 * @自主排课： 根据学期和学院获取自主排课列表
	 * @作者：魏诚
	 * @日期：2014-07-25
	 **************************************************************************************/
	public List<TimetableSelfCourse> getTimetableSelfCourseByTermAcademy(HttpServletRequest request);
	
}