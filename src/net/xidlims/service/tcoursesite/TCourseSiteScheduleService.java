package net.xidlims.service.tcoursesite;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.transaction.annotation.Transactional;

import net.xidlims.domain.TCourseSite;
import net.xidlims.domain.TCourseSiteArtical;
import net.xidlims.domain.TCourseSiteChannel;
import net.xidlims.domain.TCourseSiteSchedule;
import net.xidlims.domain.TCourseSiteTag;
import net.xidlims.domain.TCourseSiteUser;
import net.xidlims.domain.User;
import net.xidlims.view.ViewTAssignment;

public interface TCourseSiteScheduleService {
	

	/****************************************************************************
	 * Description:课程信息-保存课表
	 * 
	 * @author：裴继超
	 * @date：2016-8-3
	 ****************************************************************************/
	public void saveSchedule(TCourseSiteSchedule schedule);
	
	/****************************************************************************
	 * Description:课程信息-删除课表
	 * 
	 * @author：裴继超
	 * @date：2016-8-3
	 ****************************************************************************/
	public void deleteSchedule(TCourseSiteSchedule schedule);

	/****************************************************************************
	 * Description:课程信息-根据星期、课时获取课表信息
	 * 
	 * @author：裴继超
	 * @date：2016-8-3
	 ****************************************************************************/
	public String findScheduleByDayAndSession(Integer tCourseSiteId,String username,
			String day,String session);
	
	/****************************************************************************
	 * Description:课程信息-获取课表map
	 * 
	 * @author：裴继超
	 * @date：2016-8-3
	 ****************************************************************************/
	public Map findSchedulesMap(Integer tCourseSiteId);

	/****************************************************************************
	 * Description:课程信息-获取课表星期或课时列表
	 * 
	 * @author：裴继超
	 * @date：2016-8-3
	 ****************************************************************************/
	public List<TCourseSiteTag> findTCourseSiteTagsByDescription(String description);

	/****************************************************************************
	 * Description:课程信息-根据主键获取课表星期或课时
	 * 
	 * @author：裴继超
	 * @date：2016-8-3
	 ****************************************************************************/
	public TCourseSiteTag findTCourseSiteTagById(Integer id);

	/****************************************************************************
	 * Description:课程信息-获取指定课表
	 * 
	 * @author：裴继超
	 * @date：2016-8-3
	 ****************************************************************************/
	public List<TCourseSiteSchedule> findTCourseSiteSchedulesBySiteIdAndUsernameAndDayAndSession(
			Integer tCourseSiteId,String username,String day,String session);
	/****************************************************************************
	 * Description:课程信息-获取每周课表
	 * 
	 * @author：李军凯
	 * @date：2016-10-13
	 ****************************************************************************/
	public List<TCourseSiteSchedule> findTCourseSiteSchedulesBySiteIdAndWeek(Integer tCourseSiteId,Integer week);
	/*******************************************************
	 * Description:课程信息-获取每周参考文件
	 * 
	 * @author：李军凯
	 * @date ：2016-10-14
	 *********************************************************/
	public List findCurFolder(int week,int tCourseSiteId);
	/*******************************************************
	 * Description:课程信息-获取每周测试
	 * 
	 * @author：李军凯
	 * @date ：2016-10-14
	 *********************************************************/
	public List findCurExams(int week,int tCourseSit);
	/*******************************************************
	 * Description:课程信息-获取每周考试
	 * 
	 * @author：李军凯
	 * @date ：2016-10-14
	 *********************************************************/
	public List findCurTests(int week,int tCourseSiteId);
	/**************************************************************************
	 * Description:课程信息-获取测试列表
	 * 
	 * @author：李军凯
	 * @date ：2016-10-14
	 **************************************************************************/
	public List<ViewTAssignment> findViewExamList(List<Object[]> curExamsOrTests,Integer cid);
}


