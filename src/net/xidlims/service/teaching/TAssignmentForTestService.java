package net.xidlims.service.teaching;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.xidlims.domain.TAssignment;
import net.xidlims.domain.TAssignmentGrading;
import net.xidlims.domain.TAssignmentItemMapping;
import net.xidlims.domain.User;
import net.xidlims.view.ViewTAssignment;

public interface TAssignmentForTestService {
	
	/*************************************************************************************
	 * @內容：检查试题数量设定是否超过限制
	 * @作者：黄崔俊
	 * @日期：2015-12-2 14:11:07
	 *************************************************************************************/
	public String checkItemQuantity(String[] questionIdArray,
			Integer[] typeArray, Integer[] quantityArray);

	/*************************************************************************************
	 * @throws ParseException 
	 * @內容：保存考试设置
	 * @作者：黄崔俊
	 * @日期：2015-12-2 16:00:25
	 *************************************************************************************/
	public TAssignment saveTAssignmentForTest(Integer cid,
			TAssignment tAssignment, HttpServletRequest request) throws ParseException;

	/*************************************************************************************
	 * @param nowUser 
	 * @內容：查询考试列表
	 * @作者：黄崔俊
	 * @日期：2015-12-2 16:44:09
	 *************************************************************************************/
	public List<ViewTAssignment> findTestList(User nowUser, Integer cid, String type,
			int status, String chapterOrLesson);

	/*************************************************************************************
	 * @內容：删除未发布的考试
	 * @作者：黄崔俊
	 * @日期：2015-12-3 14:08:58
	 *************************************************************************************/
	public void deleteTestById(TAssignment test);

	/*************************************************************************************
	 * @內容：根据当前登陆人获取考试信息
	 * @作者：黄崔俊
	 * @日期：2015-12-3 16:34:30
	 *************************************************************************************/
	public TAssignment findTestByUserAndTest(User nowUser,
			TAssignment parentTest);
	
	/*************************************************************************************
	 * @內容：根据当前登陆人获取考试信息
	 * @作者：罗璇
	 * @日期：2016年4月1日14:52:29
	 *************************************************************************************/
	public TAssignment findTestByTeacherAndTest(User nowUser,
			TAssignment parentTest);

	/**
	 * @内容：根据试卷信息自动生成试卷
	 * @param test
	 * @param parentTest
	 * @作者：黄崔俊
	 * @日期：2015-12-3 16:57:20
	 * @return
	 */
	public TAssignment createRandomTest(TAssignment test, TAssignment parentTest);

	/**
	 * @内容：保存学生的答题记录
	 * @作者：黄崔俊
	 * @日期：2015-12-3 23:21:27
	 * @return 返回该次考试的得分
	 */
	public BigDecimal saveTAssignmentItemMapping(HttpServletRequest request,
			int assignmentId, Integer submitTime);

	/**
	 * @内容：保存学生答题
	 * @作者：黄崔俊
	 * @日期：2015-12-3 23:21:24
	 * @return
	 */
	public TAssignmentGrading saveTAssignmentGradeForTest(
			BigDecimal totalScore, int assignmentId, Integer submitTime);

	/**
	 * @内容：根据考试id和当前登陆人查询考试得分
	 * @作者：黄崔俊
	 * @日期：2015-12-6 14:19:53
	 * @param testId
	 * @param nowUser
	 * @return
	 */
	public TAssignmentGrading findTAssignmentGradingByTestIdAndUser(
			Integer testId, User nowUser);

	/**
	 * @内容：根据考试得分查询考试作答详情
	 * @作者：黄崔俊
	 * @日期：2015-12-6 14:19:53
	 * @param tAssignmentGrading
	 * @return
	 */
	public List<TAssignmentItemMapping> findTAssignmentItemMappingsByTAssignmentGrading(
			TAssignmentGrading tAssignmentGrading);
	/**
	 * @内容：测试JPA生成考试的效率
	 * @作者：黄崔俊
	 * @日期：2015-12-14 14:19:53
	 */
	/*public TAssignment testcreateRandomTest(TAssignment test, TAssignment parentTest);*/

	/**
	 * @内容：考试成绩有误修正数据临时方法
	 * @作者：黄崔俊
	 * @日期：2015-12-14 14:19:53
	 *//*
	public BigDecimal saveChangeTAssignmentItemMapping(Integer assignmentId);

	*//**
	 * @内容：考试成绩有误修正数据临时方法
	 * @作者：黄崔俊
	 * @日期：2015-12-14 14:19:53
	 *//*
	public TAssignmentGrading saveChangeTAssignmentGradeForTest(
			BigDecimal totalScore, Integer assignmentId);*/
}


