package net.xidlims.service.tcoursesite;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.xidlims.domain.TAssignment;
import net.xidlims.domain.TAssignmentGrading;
import net.xidlims.domain.TCourseSite;
import net.xidlims.domain.TCourseSiteUser;
import net.xidlims.domain.TGradeObject;
import net.xidlims.domain.TGradeRecord;
import net.xidlims.domain.TGradebook;
import net.xidlims.domain.TWeightSetting;
import net.xidlims.domain.User;

public interface TGradebookService {

	/**
	 * @功能：根据测验id查询是否进成绩册，是则加入成绩册
	 * @作者：黄崔俊
	 * @时间：2015-9-2 09:34:59
	 */
	public void saveGradebook(Integer cid, int assignmentId,
			TAssignmentGrading tAssignmentGrade);

	/**
	 * @功能：根据作业测验或考试创建成绩册及成绩单
	 * @作者：黄崔俊
	 * @时间：2015-12-8 14:52:05
	 */
	public void createGradebook(Integer cid, TAssignment tAssignment);

	/**
	 * @功能：根据课程和类型查看成绩科目列表
	 * @作者：黄崔俊
	 * @时间：2015-12-8 14:52:05
	 */
	public List<TGradeObject> findTGradeObjectsByTCourseSiteAndType(
			Integer cid, String type);

	/**
	 * @功能：根据科目和学生查看成绩列表
	 * @作者：黄崔俊
	 * @时间：2016-1-8 12:25:07
	 */
	public List<List<Object>> findStudentScoreRecords(
			List<TCourseSiteUser> tCourseSiteUsers,
			List<TGradeObject> tGradeObjects);

	/**
	 * @功能：根据科目和学生查看成绩总分列表
	 * @作者：黄崔俊
	 * @时间：2016-1-8 15:29:58
	 */
	public Map<String, BigDecimal> findSumScoresByTCourseSiteAndType(
			Integer cid, String type);

	/**
	 * 查询权重设置情况
	 * 
	 * @param cid
	 *            课程id
	 * @param type
	 * @return
	 */
	public List<List<Object>> findWeightSetting(Integer cid, String type);

	/**
	 * 查询总评权重设置情况
	 * 
	 * @param cid
	 *            课程id
	 * @return
	 */
	public List<TWeightSetting> findWeightSettings(Integer cid);

	/**
	 * @功能：查看总成绩列表
	 * @作者：黄崔俊
	 * @时间：2016-1-11 08:52:43
	 */
	public List<List<Object>> findTotalScoreInfo(
			List<TCourseSiteUser> tCourseSiteUsers,
			List<TWeightSetting> weightSettings, Integer cid);

	/**
	 * @功能：权重设置
	 * @作者：黄崔俊
	 * @时间：2016-1-11 10:21:17
	 */
	public void singleWeightSetting(HttpServletRequest request, Integer cid);

	/**
	 * 根据objectId查询作业项下不同学生成绩列表
	 * 
	 * @param objectId
	 * @return
	 */
	public List<TGradeRecord> findTGradeRecordsByObjectId(Integer objectId);

	/**
	 * 根据学生学号查询学生不同作业项成绩列表
	 * 
	 * @param objectId
	 * @return
	 */
	public List<TGradeRecord> findTGradeRecordsByUser(String username);

	/**
	 * 根据课程id查询不同学生不同作业项成绩列表
	 * 
	 * @param objectId
	 * @return
	 */
	public List<TGradeRecord> findTGradeRecordsBySiteId(Integer cid);

	/**
	 * 根据课程id查询作业（测验）项列表
	 * 
	 * @param objectId
	 * @return
	 */
	public List<TGradeObject> findTGradeObjectsBySiteId(Integer cid);

	/**
	 * @功能：保存自定义成绩时课程号对应的成绩册t_gradebook
	 * @作者：张佳鸣
	 * @时间：2017-11-15
	 */
	public TGradebook saveTGradebookByCustom(TCourseSite tCourseSite);

	/**
	 * @功能：保存成绩册中的自定义成绩名称
	 * @作者：张佳鸣
	 * @时间：2017-11-15
	 */
	public TGradeObject saveTGradeObjectByCustom(TGradebook tGradebook,
			String title);

	/**
	 * @功能：保存自定义成绩册相关具体成绩记录
	 * @作者：张佳鸣
	 * @时间：2017-11-15
	 */
	public void saveTGradeRecordByCustom(User user, Integer score,
			TGradeObject tGradeObject);

	/**
	 * @功能：根据课程id查看自定义科目列表
	 * @作者：张佳鸣
	 * @时间：2017-11-16
	 */
	public List<TGradeObject> findCustomTGradeObjectsByTCourseSite(Integer cid,
			String type);

	/**
	 * @功能：根据自定义科目和学生查看成绩列表
	 * @作者：张佳鸣
	 * @时间：2017-11-16
	 */
	public List<List<Object>> findCustomStudentScoreRecords(
			List<TCourseSiteUser> tCourseSiteUsers,
			List<TGradeObject> tGradeObjects);

	/**************************************************************************
	 * Description:（带自定义成绩）查看总成绩列表
	 * 
	 * @author：张佳鸣
	 * @date ：2017-11-16
	 **************************************************************************/
	public List<List<Object>> findTotalScoreInfoWithCustom(
			List<TCourseSiteUser> tCourseSiteUsers,
			List<TWeightSetting> weightSettings, Integer cid,
			Integer customTGradeObjectsSize, List<List<Object>> customLists);

	/**
	 * @功能：根据自定义类型和学生查看成绩列表
	 * @作者：张佳鸣
	 * @时间：2017-11-16
	 */
	public Map<String, BigDecimal> findCustomScoresByCustomLists(
			List<List<Object>> customLists, Integer num);

	/**
	 * @功能：（带自定义成绩）查询总评权重设置情况
	 * @作者：张佳鸣
	 * @时间：2017-11-16
	 */
	public List<TWeightSetting> findWeightSettingsWithCustom(Integer cid,
			Integer customTGradeObjectsSize,
			List<TGradeObject> customTGradeObjects);
	
	/**
	 * @功能：删除自定义成绩
	 * @作者：张佳鸣
	 * @时间：2017-11-17
	 */
	public void deleteCustomScore(TGradeObject tGradeObject,Integer tCourseSiteId);
	
	/****************************************************
	 * Description:获取实验项目中的预习测试实验报告和作业
	 *
	 *@auther:张佳鸣
	 *@date：2017-12-04
	 *****************************************************/
	public List<TWeightSetting> getWorkAndTestAndReport(Integer tCourseSiteId);
	
	/****************************************************
	 * Description:获取实验项目成绩
	 *
	 *@auther:张佳鸣
	 *@date：2017-12-04
	 *****************************************************/
	public List<List<Object>> findStudentScoreRecordsWithAll(
			List<TCourseSiteUser> tCourseSiteUsers,List<TGradeObject> testObjects,
			List<TGradeObject> reportObjects,List<TAssignment> workList,Integer tCourseSiteId);
	
	/*****************************************************
	 * Description:获取实验项目中的预习测试，作业，实验报告的每个学生的成绩
	 *
	 *@auther:李雪腾
	 *@date：2017-8-25
	 *****************************************************/
	public BigDecimal findStudentAllScoreRecords(TCourseSiteUser user,Integer tCourseSiteId,List<TAssignment> workList);
	
	/**
	 * @功能：实验项目权重设置
	 * @作者：张佳鸣
	 * @时间：2017-12-08
	 */
	public void experimentWeightSetting(HttpServletRequest request, Integer cid);
	
	/**
	 * @功能：根据课程号和学生查看实验成绩总分列表
	 * @作者：张佳鸣
	 * @时间：2017-12-11
	 */
	public Map<String, BigDecimal> findExperimentScoresByTCourseSiteAndTCourseSiteUsers(
			Integer cid,List<TCourseSiteUser> tCourseSiteUsers);
}