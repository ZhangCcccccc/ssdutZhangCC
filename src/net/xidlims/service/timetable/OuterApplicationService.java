package net.xidlims.service.timetable;

import java.util.List;
import java.util.Map;

import net.xidlims.domain.OperationItem;
import net.xidlims.domain.SchoolTerm;

public interface OuterApplicationService {

	/************************************************************ 
	 * 获取可排的实验室列表
	 * 作者：魏诚
	 * 日期：2014-07-14
	 ************************************************************/
	public Map<Integer, String> getLabRoomMap(int iLabCenter);
	
	/************************************************************ 
	 * 获取可选的教师列表
	 * 作者：魏诚
	 * 日期：2014-07-24
	 ************************************************************/
	public Map<String, String> getTimetableTearcherMap() ;
	
	/************************************************************ 
	 * 获取可选的教师列表(以当前选课组所在学院查询该学院下所有老师)
	 * 作者：罗璇
	 * 日期：2017年3月22日
	 ************************************************************/
	public Map<String, String> getTimetableTearcherMapByCourseAcademy(String courseCode) ;
	
	/************************************************************ 
	 * 获取可排的实验项目列表
	 * 作者：魏诚
	 * 日期：2014-07-24
	 ************************************************************/
	public Map<Integer, String> getTimetableItemMap(String courseNo);
	
	/************************************************************ 
	 * 获取可选的教学周
	 * 作者：魏诚
	 * 日期：2014-07-24
	 ************************************************************/
	public Integer[] getValidWeeks(int term,int classids,int labroom,int weekday) ;
	
	/************************************************************
	 * @获取可选的教学周
	 * @作者：魏诚
	 * @日期：2014-07-24
	 ************************************************************/
	public Integer[] getValidWeeks(int term, int[] classes, int[] labrooms,
			int weekday);
	/************************************************************
	 * @获取可选的教学周--编辑排课记录
	 * @作者：贺子龙
	 * @日期：2016-01-06
	 ************************************************************/
	public Integer[] getValidWeeks(int term, int[] classes, int[] labrooms,
			int weekday, int tableAppId);
	/************************************************************ 
	 * 根据选课组编号获取可排的实验项目列表
	 * 作者：魏诚
	 * 日期：2014-07-24
	 ************************************************************/
	public List<OperationItem> getCourseCodeItemList(String courseCode) ;
	
	/************************************************************ 
	 * @获取所有学期数据，按id倒序
	 * @作者：魏诚
	 * @日期：2014-08-17
	 ************************************************************/
	public List<SchoolTerm> getSchoolTermList();
	
	/*************************************************************************************
	 * @內容：判断选课资源是否冲突
	 * @作者： 魏誠
	 * @日期：2015-03-10
	 *************************************************************************************/
	public boolean isTimetableConflict(int term, int weekday, int[] labrooms, int[] classes,int[] weeks);

	/************************************************************
	 * @获取可选的教学周-针对实验室预约
	 * @作者：魏诚
	 * @日期：2015-07-09
	 ************************************************************/
	public Integer[] getValidLabWeeks(int term, int[] classes, int[] labrooms, int weekday);
}