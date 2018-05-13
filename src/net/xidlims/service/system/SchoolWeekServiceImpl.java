package net.xidlims.service.system;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.xidlims.dao.SchoolWeekDAO;
import net.xidlims.domain.SchoolTerm;
import net.xidlims.domain.SchoolWeek;
import net.xidlims.domain.TimetableAppointment;
import net.xidlims.domain.TimetableAppointmentSameNumber;
import net.xidlims.service.common.ShareService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import flex.messaging.io.ArrayList;

@Service("SchoolWeekService")
public class SchoolWeekServiceImpl implements SchoolWeekService{
	
	
	@Autowired
	private SchoolWeekDAO shcoolweekDAO;
	@Autowired
	private ShareService shareService;
	/************************************
	 * 功能：根据学期  周次 星期 查出对应日期
	 * 作者：贺子龙
	 * 日期：2015-12-27
	 *************************************/
	public Calendar getDate(int term, int week, int weekday){
		String sql="select s from SchoolWeek s where 1=1";
		sql+=" and s.schoolTerm.id="+term;
		sql+=" and s.week="+week;
		sql+=" and s.weekday="+weekday;
		List<SchoolWeek> schoolWeek = shcoolweekDAO.executeQuery(sql, 0, -1);
		Calendar date=schoolWeek.get(0).getDate();
		return date;
	}
	
	/************************************
	 * 功能：获取某一排课的所有上课日期
	 * 作者：贺子龙
	 * 日期：2015-12-27
	 *************************************/
	public List<Calendar> getTimetableAppointmentDate(TimetableAppointment timetableAppointment){
		
		SchoolTerm s = shareService.getBelongsSchoolTerm(Calendar.getInstance());
		int term=s.getId();//学期
		if (timetableAppointment.getTimetableStyle().equals(5)
				||timetableAppointment.getTimetableStyle().equals(6)) {//自主排课
//			System.out.println("-------自主排课"+timetableAppointment.getTimetableStyle());
			if (timetableAppointment.getTimetableSelfCourse()!=null
					&&timetableAppointment.getTimetableSelfCourse().getSchoolTerm()!=null) {
				term=timetableAppointment.getTimetableSelfCourse().getSchoolTerm().getId();//学期
			}
		}else {//非自主排课
			if (timetableAppointment.getSchoolCourseDetail()!=null
					&&timetableAppointment.getSchoolCourseDetail().getSchoolTerm()!=null) {
				term=timetableAppointment.getSchoolCourseDetail().getSchoolTerm().getId();//学期
			}	
		}
		
		int weekday=timetableAppointment.getWeekday();//星期几
		List<Calendar> dates=new ArrayList();
		Set<TimetableAppointmentSameNumber> sameNumbers= timetableAppointment.getTimetableAppointmentSameNumbers();
		if (sameNumbers.size()==0) {//周次连续
			int startWeek=timetableAppointment.getStartWeek();//开始周
			int endWeek=timetableAppointment.getEndWeek();//结束周
			
			for(int week=startWeek; week<=endWeek;week++){
				Calendar date=getDate(term,week,weekday);
//				System.out.println("[[["+date.getTime());
				dates.add(date);
			}
		}else{//周次不连续
			for (TimetableAppointmentSameNumber tas : sameNumbers) {
				int startWeek=tas.getStartWeek();//开始周
				int endWeek=tas.getEndWeek();//结束周
				if (startWeek==endWeek) {
					Calendar date=getDate(term,startWeek,weekday);
//					System.out.println("---"+date.getTime());
					dates.add(date);
				}else{
					for(int week=startWeek; week<=endWeek;week++){
						Calendar date=getDate(term,week,weekday);
//						System.out.println("==="+date.getTime());
						dates.add(date);
					}
				}
				
			}
		}
		return dates;
	}
	/************************************
	 * 功能：获取所有（学期  周次 星期）和日期的键值对
	 * 作者：缪军
	 * 日期：2017-07-19
	 *************************************/
	public Map<String, Calendar> getMapDate(){
		Set<SchoolWeek> set=shcoolweekDAO.findAllSchoolWeeks();
		Map<String, Calendar> mapDate = new HashMap<String, Calendar>();
		for (SchoolWeek schoolWeek : set) {
			mapDate.put(schoolWeek.getSchoolTerm().getId()+"-"+schoolWeek.getWeek()+"-"+schoolWeek.getWeekday(), schoolWeek.getDate());
		}//拼接termid-week-weekday作为key查找相应值
		return mapDate;
	}
}
