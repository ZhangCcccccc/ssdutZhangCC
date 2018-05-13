package net.xidlims.common;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;


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
	
}
