package net.xidlims.service.timetable;

import java.text.ParseException;
import java.util.List;

import net.xidlims.domain.LabRoomDevice;
import net.xidlims.domain.TimetableLabRelated;

public interface TimetableCourseSchedulingService {
	/*************************************************************************************
	 * @內容：根据时间查找学期
	 * @作者： 魏誠
	 * @日期：2014-07-24
	 *************************************************************************************/
	public String getTimetableClassesMap(int term,String weekday, String courseCode) ;
	/*************************************************************************************
	 * @內容：根据时间查找学期
	 * @作者： 魏誠
	 * @日期：2014-07-24
	 *************************************************************************************/
	public String getTimetableClassesMap(int term,String weekday, String courseCode,String startClass) ;
	
	/*************************************************************************************
	 * @內容：返回可用的星期信息--编辑排课记录
	 * @作者：魏诚
	 * @日期：2016-01-06
	 *************************************************************************************/
	public String getValidLabroomDevice(String[] labrooms);
	
	/*************************************************************************************
	 * @內容：保存排课实验室设备资源对应的设备预约记录
	 * @作者：魏诚
	 * @日期：2016-05-06
	 *************************************************************************************/
	public void saveTimetableLabroomDeviceReservation(TimetableLabRelated timetableLabRelated,String[] sLabRoomDevice,int term)  throws ParseException ;
	
	
	/*************************************************************************************
	 * @description：返回可添加的设备
	 * @author：郑昕茹
	 * @date：2016-10-13
	 *************************************************************************************/
	public List<LabRoomDevice> getValidLabroomDevices(String[] labrooms);
}