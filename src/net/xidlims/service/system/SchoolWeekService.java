package net.xidlims.service.system;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import net.xidlims.domain.TimetableAppointment;

public interface SchoolWeekService {
	
	
	/************************************
	 * 功能：根据学期  周次 星期 查出对应日期
	 * 作者：贺子龙
	 * 日期：2015-12-27
	 *************************************/
	public Calendar getDate(int term, int week, int weekday);
	
	/************************************
	 * 功能：获取某一排课的所有上课日期
	 * 作者：贺子龙
	 * 日期：2015-12-27
	 *************************************/
	public List<Calendar> getTimetableAppointmentDate(TimetableAppointment timetableAppointment);
	/************************************
	 * 功能：获取所有（学期  周次 星期）和日期的键值对
	 * 作者：缪军
	 * 日期：2017-07-19
	 *************************************/
	public Map<String, Calendar> getMapDate();
}
