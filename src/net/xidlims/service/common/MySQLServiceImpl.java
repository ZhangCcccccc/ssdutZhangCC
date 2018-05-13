package net.xidlims.service.common;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("MySQLService")
public class MySQLServiceImpl implements MySQLService {
	@PersistenceContext  
    private EntityManager entityManager;

	/**************************************************************************************
	 * 读取视图的数据并分页
	 * 李小龙
	 **************************************************************************************/
	@Override
	public List getViewOfStudentCourse(int pageSize,int page) {
		// TODO Auto-generated method stub
		Query query=entityManager.createNativeQuery("select student_number,student_name,course_number,course_name,course_code from view_lxl ");
		List list=query.setMaxResults(pageSize).setFirstResult((page-1)*pageSize).getResultList();
		return list;
	}
	/**************************************************************************************
	 * 根据课程id读取微课的章节
	 * 李小龙
	 **************************************************************************************/
	@Override
	public List getWKCourseChapter(int id) {
		String sql="select * from wk_chapter where course_id="+id;
		Query query=entityManager.createNativeQuery(sql);
		List sectionList=query.getResultList();
		
		return sectionList;
	}
	/**************************************************************************************
	 * 根据课程id读取微课的课时
	 * 李小龙
	 **************************************************************************************/
	@Override
	public List getWKCourseLesson(int id) {
		String sql="select * from wk_lesson where chapter_id in(select c.id from wk_chapter as c where c.course_id="+id+")";
		Query query=entityManager.createNativeQuery(sql);
		List lessonList=query.getResultList();
		
		return lessonList;
	} 
	
	/****************************************************
	 * 功能：调用存储过程--将针对实验室的排课映射到实验室禁用时间（直接排课专用）
	 * 作者： 贺子龙
	 * 日期：2016-05-28
	 *****************************************************/
	@Transactional
	public void createLabLimitByDirectAppointment(Integer appointmentId){
		Query query = entityManager.createNativeQuery("{call setLimitTimeFromDirectAppointment("+appointmentId+")}");
		query.executeUpdate();
	}
	
	/****************************************************
	 * 功能：调用存储过程--将针对实验室的排课映射到实验室禁用时间
	 * 作者： 贺子龙
	 * 日期：2016-05-28
	 *****************************************************/
	@Transactional
	public void createLabLimitByAppointment(Integer appointmentId){
		Query query = entityManager.createNativeQuery("{call setLimitTimeFromAppoint("+appointmentId+")}");
		query.executeUpdate();
	}
}
