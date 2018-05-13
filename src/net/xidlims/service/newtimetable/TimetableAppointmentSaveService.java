package net.xidlims.service.newtimetable;

import java.util.List;
import java.util.Map;

import net.xidlims.domain.TimetableAppointment;
import net.xidlims.domain.TimetableAppointmentSameNumber;



/**********************************************
 * Description: 排课模块{各种形式排课的保存接口}
 * 
 * @author 贺子龙
 * @date 2016-08-31
 ***********************************************/
public interface TimetableAppointmentSaveService {
	/***********************************************************************************************
	 * Description：保存公选课排课结果
	 * 
	 * @author：郑昕茹
	 * @Date：2017-04-20
	 ***********************************************************************************************/
	public TimetableAppointment savePublicElectiveCourseTimetable(int term, int[] classes, int[] labrooms, int[] weekArray, 
			int weekday, String courseNo, int groupId);
	/***********************************************************************************************
	 * Description：排课模块{保存分组排课}
	 * 
	 * @author：贺子龙
	 * @Date：2016-08-31
	 ***********************************************************************************************/
	public TimetableAppointment saveGroupTimetable(int term, int[] classes, int[] labrooms, int[] weekArray, 
			int weekday, int[] items, String teachers, String courseNo, int groupId, Integer isAdmin);
	

	/***********************************************************************************************
	 * Description：保存基础课排课结果
	 * 
	 * @author：郑昕茹
	 * @Date：2017-04-20
	 ***********************************************************************************************/
	public Map<String, Object> saveSpecializedBasicCourseTimetable(int term, int[] classes, int[] labrooms, int[] weekArray, 
			int weekday, String courseNo ,int[] item, String teacher, Integer groupId);
	
	/***********************************************************************************************
	 * Description：保存合班排课结果
	 * 
	 * @author：郑昕茹
	 * @Date：2017-04-26
	 ***********************************************************************************************/
	public Map<String, Object> saveMergeCourseTimetableCourseTimetable(int term, int[] classes, int[] labrooms, int[] weekArray, 
			int weekday, Integer mergeId ,int[] item, String teacher, Integer groupId);
	
	

	/*
	 * description:根据appointId找到其下所有的跨节排课
	 * @author:郑昕茹
	 * 
	 */
	public List<TimetableAppointmentSameNumber> findSameNumbersByAppointmentId(Integer appointmentId);
	
	/***********************************************************************************************
	 * Description：保存公选课排课结果（合班）
	 * 
	 * @author：郑昕茹
	 * @Date：2017-04-20
	 ***********************************************************************************************/
	public TimetableAppointment savePublicElectiveCourseTimetableInMerge(int term, int[] classes, int[] labrooms, int[] weekArray, 
			int weekday, Integer mergeId, int groupId);
}
