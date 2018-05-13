package net.xidlims.service.timetable;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.transaction.annotation.Transactional;

import net.xidlims.domain.TimetableAppointment;
import net.xidlims.domain.TimetableGroup;
import net.xidlims.domain.TimetableLabRelated;

public interface TimetableSelfSchedulingService {
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
	 * @內容：保存自主排课的不分组排课的内容
	 * @作者： 魏誠
	 * @日期：2014-08-5
	 *************************************************************************************/
	@Transactional
	public TimetableAppointment saveNoGroupSelfTimetable(
			HttpServletRequest request)  throws ParseException;
	/*************************************************************************************
	 * @內容：根据实验室和节次及星期列出所有时间列表安排
	 * @作者： 魏誠
	 * @日期：2014-07-24
	 *************************************************************************************/
	public List<TimetableLabRelated> getListLabTimetableAppointments(
			HttpServletRequest request,int iLabCenter,int term) ;
	
	/*************************************************************************************
	 * @內容：根据实验室和节次及星期列出所有自建课程时间列表安排
	 * @作者： 魏誠
	 * @日期：2014-07-24
	 *************************************************************************************/
	public List<TimetableLabRelated> getSelfListLabTimetableAppointments(
			HttpServletRequest request,int iLabCenter,int term) ;
	
	/*************************************************************************************
	 * @內容：保存自主排课的分组排课的内容
	 * @作者： 魏誠
	 * @日期：2014-08-5
	 *************************************************************************************/
	@Transactional
	public TimetableAppointment saveGroupReTimetable(HttpServletRequest request) throws ParseException;
	/*************************************************************************************
	 * @內容：自主分组排课排课完成确定
	 * @作者： 魏誠
	 * @日期：2014-08-5
	 *************************************************************************************/
	public void doSelfGroupTimetableOk(String courseCode, int batchId, int term);
	
	/*************************************************************************************
	 * @內容：返回排课节次或者周次
	 * @作者： 魏誠
	 * @日期：2014-08-5
	 *************************************************************************************/
	public String[] getTimetableWeekClass(int[] intWeeks);


}


