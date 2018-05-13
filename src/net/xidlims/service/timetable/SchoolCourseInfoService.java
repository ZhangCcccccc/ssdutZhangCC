package net.xidlims.service.timetable;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import net.xidlims.domain.SchoolCourseInfo;

public interface SchoolCourseInfoService {
	/*************************************************************************************
	 * @內容：进行获取计数获取课程信息CourseInfo的分页列表信息
	 * @作者： 魏誠
	 * @日期：2014-07-24
	 *************************************************************************************/
	public int getCountCourseInfoByQuery(SchoolCourseInfo schoolCourseInfo) ;
	
	/*************************************************************************************
	 * @內容：获取CourseInfo的分页列表信息
	 * @作者： 魏誠
	 * @日期：2014-07-24
	 *************************************************************************************/
	public List<SchoolCourseInfo> getCourseInfoByQuery(SchoolCourseInfo schoolCourseInfo,int cid, int flag,int curr, int size);
	
	/*************************************************************************************
	 * @內容：获取查找自建课程信息记录数
	 * @作者： 魏诚
	 * @日期：2014-08-25
	 *************************************************************************************/
	@Transactional
	public int getSelfSchoolCourseInfoTotalRecords();

	/*************************************************************************************
	 * @內容：获取CourseInfo的分页列表信息,flag标记位-1为所有
	 * @作者： 魏誠
	 * @日期：2014-07-24
	 *************************************************************************************/
	public Object getCourseInfoByQuery(SchoolCourseInfo schoolCourseInfo,
			int flag, int curr, int pageSize);

	/**
	 * 查询所有课程
	 * @return
	 */
	public List<Object> findAllSchoolCourseInfo();
	
	/**
	 * 查询所有课程(map)
	 * @return
	 */
	public Map<String,String> findAllSchoolCourseInfoMap();
}