package net.xidlims.service.teaching;

import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.xidlims.domain.TAssignmentGrading;
import net.xidlims.domain.User;
import net.xidlims.view.ViewTAssignmentAllGrade;

public interface TAssignmentGradingService {

	/**
	 * @功能： 保存学生提交的作业
	 * @作者： 黄崔俊
	 * @时间： 2015-8-13 16:23:54
	 * @param request 
	 * @param cid 
	 * @return 
	 */
	public TAssignmentGrading saveTAssignmentGrading(TAssignmentGrading tAssignmentGrade, Integer cid, HttpServletRequest request);

	/**
	 * @功能：根据作业id和user查询学生的作业提交情况
	 * @作者： 黄崔俊
	 * @时间： 2015-8-14 14:48:27
	 */
	public List<TAssignmentGrading> findTAssignmentGradingList(Integer assignmentId, int flag, User user);

	
	/**
	 * @功能：根据id查询作业提交的详情
	 * @作者： 黄崔俊
	 * @时间： 2015-8-14 16:14:50
	 */
	public TAssignmentGrading findTAssignmentGradingById(Integer assignGradeId);

	/**
	 * @return 
	 * @功能：教师批改作业
	 * @作者： 黄崔俊
	 * @时间： 2015-8-17 09:30:25
	 */
	public TAssignmentGrading updateTAssignmentGrading(TAssignmentGrading tAssignmentGrade);

	/**
	 * @功能：教师批改作业
	 * @作者： 黄崔俊
	 * @时间：2015-8-20 14:38:31
	 */
	public TAssignmentGrading updateTAssignmentGrading(Integer assignGradeId,String comments, Float finalScore, User nowUser, Calendar calendar);

	/**
	 * @功能：教师下载作业
	 * @作者： 黄崔俊
	 * @时间：2015-8-24 15:41:59
	 */
	public String downloadAssignment(HttpServletRequest request,
			HttpServletResponse response, Integer cid, Integer assignId);

	/**
	 * @param nowUser 
	 * @功能：查询该课程下所有成绩情况
	 * @作者： 黄崔俊
	 * @时间：2015-8-25 14:05:06
	 */
	public List<ViewTAssignmentAllGrade> searchAllGrade(User nowUser, Integer cid);

	/**
	 * @功能：根据课程站点查询该站点下的学生
	 * @作者： 黄崔俊
	 * @时间：2015-8-25 15:10:28
	 */
	public List<User> findUsersByTCourseSiteId(Integer cid);
	
	/**
	 * @功能：根据当前登陆人和测验类型查询成绩
	 * @作者： 黄崔俊
	 * @时间：2015-9-1 10:13:32
	 */
	public List<TAssignmentGrading> findTAssignmentGradingList(User nowUser,String type);
	
	/**
	 * @功能：根据当前登陆人和测验类型以及当前课程查询成绩
	 * @作者： 黄崔俊
	 * @时间： 2015-8-14 14:48:27
	 */
	public List<TAssignmentGrading> findTAssignmentGradingList(Integer tCourseSiteId,User user, String string);
	
	/**********************************************************************************
	 * @功能：下载树形表的文件
	 * @作者： 裴继超
	 * @时间：2015-9-15
	 **********************************************************************************/
	public String downloadFile(TAssignmentGrading tAssignmentGrade, HttpServletRequest request,
			HttpServletResponse response,Integer cid);
	/**********************************************************************************
	 * @功能：下载一份作业
	 * @作者： 裴继超
	 * @时间：2015-9-28
	 **********************************************************************************/
	public String downloadFileForStudent(TAssignmentGrading tAssignmentGrade, HttpServletRequest request,
			HttpServletResponse response,Integer cid);
	/**********************************************************************************
	 * @功能：根据作业id和登录人获取分数及评语
	 * @作者： 裴继超
	 * @时间：2015-9-24
	 **********************************************************************************/
	public TAssignmentGrading findGrandingByAssignmentId(Integer assignmentId);
	/**********************************************************************************
	 * @功能：根据站点id和登录人获取分数及评语
	 * @作者： 裴继超
	 * @时间：2015-9-25
	 **********************************************************************************/
	public int findSubmitTimeByAssignmentId(Integer assignmentId);
	/**
	 * @功能：学生查看已提交次数
	 * @作者： 裴继超
	 * @时间： 2015-9-28
	 */

	public int findTAssignmentGradingTimes(Integer assignmentId);

	/**
	 * @功能：根据测验id和user查询学生的测验提交情况
	 * @作者： 黄崔俊
	 * @时间：2015-10-16 09:52:30
	 */
	public List<TAssignmentGrading> findExamGradingList(Integer examId,Integer flag, User user);

	/**************************************************************************
	 * Description:知识技能体验-测试-获取学生分数
	 * 
	 * @author：裴继超
	 * @date ：2016-9-3
	 **************************************************************************/
	public TAssignmentGrading findTAssignmentGradingByExamAndUser(
			Integer examId, User nowUser);
	
	/**
	 * @功能： 自定义成绩时保存学生提交的作业
	 * @作者： 张佳鸣
	 * @时间： 2017-9-29
	 */
	public void saveTAssignmentGrading1(TAssignmentGrading tAssignmentGrade);
	
	/**
	 * @功能： 老师批改试验项目的作业“驳回”功能设置更新实验成绩数据
	 * @作者： 张佳鸣
	 * @时间： 2017-11-28
	 */
	public void updateTAssignmentGrading(Integer assignGradeId,User nowUser,Calendar calendar);
	
}


