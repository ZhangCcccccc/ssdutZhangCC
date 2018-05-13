package net.xidlims.common;

import java.util.List;


public interface MySQLService {
	/**************************************************************************************
	 * 读取视图的数据并分页
	 * 李小龙
	 **************************************************************************************/
	public List getViewOfStudentCourse(int pageSize,int page);
	/**************************************************************************************
	 * 根据课程id读取微课表的章节
	 * 李小龙
	 **************************************************************************************/
	public List getWKCourseChapter(int id);
	
	/**************************************************************************************
	 * 根据课程id读取微课的课时
	 * 李小龙
	 **************************************************************************************/
	public List getWKCourseLesson(int id);
}
