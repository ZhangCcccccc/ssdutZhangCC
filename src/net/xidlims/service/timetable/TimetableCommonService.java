package net.xidlims.service.timetable;

import java.text.ParseException;

public interface TimetableCommonService {

	/*************************************************************************************
	 * @throws ParseException
	 * @內容：对SchoolCourseDetail所属的选课组进行实验室设备的判冲处理
	 * @作者： 魏誠
	 * @日期：2014-07-24
	 *************************************************************************************/
	public String getLabroomDeviceValid(String[] devices, String courseCode) throws ParseException;
}