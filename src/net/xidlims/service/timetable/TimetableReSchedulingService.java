package net.xidlims.service.timetable;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.xidlims.domain.TimetableGroup;

public interface TimetableReSchedulingService {
	/*************************************************************************************
	 * @內容：保存分组信息
	 * @作者： 魏誠
	 * @日期：2014-07-24
	 *************************************************************************************/
	public void saveTimetableGroup(HttpServletRequest request) ;
	
	/*************************************************************************************
	 * @內容：根据选课组编号获取分组信息
	 * @作者： 魏誠
	 * @日期：2014-07-24
	 *************************************************************************************/
	public List<TimetableGroup> getTimetableGroupByCourseCode(String courseCode) ;
	
	/*************************************************************************************
	 * @內容：获取学生选课的列表
	 * @作者： 魏誠
	 * @日期：2014-07-24
	 *************************************************************************************/
	public List<TimetableGroup> getTimetableStudentSelect();
	
	/*************************************************************************************
	 * @內容：返回实验项目相关的可用星期信息
	 * @作者： 魏誠
	 * @日期：2014-07-24
	 *************************************************************************************/
	public String getValidItemMap(int itemid);
	
	/*************************************************************************************
	 * @內容：返回可用的星期信息
	 * @作者： 魏誠
	 * @日期：2014-07-24
	 *************************************************************************************/
	public String getValidWeeksMap( int term, int weekday,
			int[] labrooms, int[] classes);
	/*************************************************************************************
	 * @內容：返回可用的星期信息--编辑排课记录
	 * @作者： 贺子龙
	 * @日期：2016-01-06
	 *************************************************************************************/
	public String getValidWeeksMap( int term, int weekday,
			int[] labrooms, int[] classes, int tableAppId);
	
	/*************************************************************************************
	 * @內容：删除id对应的批次的所有记录
	 * @作者： 魏誠
	 * @日期：2014-07-24
	 *************************************************************************************/
	public void deleteBatch(  int id, int term,
			String courseCode);
	
	/*************************************************************************************
	 * @內容：确认二次分组排课是否完成
	 * @作者： 魏誠
	 * @日期：2014-07-24
	 *************************************************************************************/
	public void doReGroupTimetableOk(String courseCode,int batchId, int term);
	/*************************************************************************************
	 * @內容：返回可用的实验室设备
	 * @作者： 贺子龙
	 * @日期：2015-10-27
	 *************************************************************************************/
	public String getLabroomDeviceMap(int[] labrooms);

}
