package net.xidlims.service.tcoursesite;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import net.xidlims.domain.SchoolClasses;
import net.xidlims.domain.SchoolMajor;
import net.xidlims.domain.SystemMajor12;
import net.xidlims.domain.TAssignment;
import net.xidlims.domain.TAssignmentAnswerAssign;
import net.xidlims.domain.TAssignmentGrading;
import net.xidlims.domain.TAssignmentItem;
import net.xidlims.domain.TAssignmentQuestionpool;
import net.xidlims.domain.TCourseSite;
import net.xidlims.domain.TCourseSiteGroup;
import net.xidlims.domain.TCourseSiteUser;
import net.xidlims.domain.User;
import net.sf.json.JSONArray;


public interface TCourseSiteAttendenceService {

	/**************************************************************************
	 * Description:班级成员-查寻课程下的所有学生
	 *  
	 * @author：李军凯
	 * @date ：2016-10-17
	 **************************************************************************/
	public List findAttendenceListByAttendenceId(Integer attendenceId);
	/**************************************************************************
	 * Description:考勤-查寻课程下的所有考勤
	 *  
	 * @author：李军凯
	 * @date ：2016-10-18
	 **************************************************************************/
	public List<TAssignment> findAttendenceBytCourseSiteId(Integer tCourseSiteId);
	/**************************************************************************
	 * Description:考勤-查寻考勤扣分设置
	 *  
	 * @author：李军凯
	 * @date ：2016-10-19
	 **************************************************************************/
	public TAssignmentAnswerAssign findTAssignmentAnswerAssignByAssignmentId(Integer assignmentId);
	/**************************************************************************
	 * Description:考勤-查寻课程下某学生的某次考勤状况
	 *  
	 * @author：李军凯
	 * @date ：2016-10-20
	 **************************************************************************/
	public TAssignmentGrading findTAssignmentGradingByTAssignmentIdAndtCourseSiteUser(Integer TAssignmentId,
			TCourseSiteUser tCourseSiteUser);
	/**************************************************************************
	 * Description:根据测验id查询是否进成绩册，是则加入成绩册
	 * 
	 * @author：李军凯
	 * @date ：2016-10-20
	 **************************************************************************/
	public void saveGradebook(Integer cid,int assignmentId,TAssignmentGrading tAssignmentGrade);
}