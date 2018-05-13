package net.xidlims.service.common;

import java.util.List;

import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;


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
	
	/****************************************************
	 * 功能：调用存储过程--将针对实验室的排课映射到实验室禁用时间（直接排课专用）
	 * 作者： 贺子龙
	 * 日期：2016-05-28
	 *****************************************************/
	public void createLabLimitByDirectAppointment(Integer appointmentId);
	
	/****************************************************
	 * 功能：调用存储过程--将针对实验室的排课映射到实验室禁用时间
	 * 作者： 贺子龙
	 * 日期：2016-05-28
	 *****************************************************/
	public void createLabLimitByAppointment(Integer appointmentId);
}
