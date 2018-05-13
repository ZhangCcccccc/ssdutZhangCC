package net.xidlims.service.tcoursesite;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.xidlims.domain.TAssignment;
import net.xidlims.domain.TAssignmentGrading;
import net.xidlims.domain.TAssignmentItem;
import net.xidlims.domain.TAssignmentItemMapping;
import net.xidlims.domain.TAssignmentQuestionpool;
import net.xidlims.domain.TExerciseInfo;
import net.xidlims.domain.TMistakeItem;
import net.xidlims.domain.User;

public interface TAssignmentItemService {

	/**************************************************************************
	 * Description:保存测验小题
	 * 
	 * @author：黄崔俊
	 * @date ：2015-8-31
	 **************************************************************************/
	public TAssignmentItem saveExamItem(TAssignmentItem tAssignmentItem, HttpServletRequest request);

	/**************************************************************************
	 * Description:根据测验小题id查询测验小题
	 * 
	 * @author：黄崔俊
	 * @date ：2015-8-31
	 **************************************************************************/
	public TAssignmentItem findTAssignmentItemById(Integer id);

	/**************************************************************************
	 * Description:删除作业小题
	 * 
	 * @author：黄崔俊
	 * @date ：2015-8-31
	 **************************************************************************/
	public void deleteTAssignmentItem(TAssignmentItem tAssignmentItem);

	/**************************************************************************
	 * Description:根据测验id查询测验小题列表
	 * 
	 * @author：黄崔俊
	 * @date ：2015-8-31
	 **************************************************************************/
	public List<TAssignmentItem> findTAssignmentItemListByExamId(int examId);
	
	/**************************************************************************
	 * Description:根据当前登陆人和测验查询测验答题情况
	 * 
	 * @author：黄崔俊
	 * @date ：2015-9-1
	 **************************************************************************/
	public List<TAssignmentItemMapping> findTAssignmentItemMappingsByUserAndExamId(User nowUser, TAssignment examInfo);

	/**************************************************************************
	 * Description:根据成绩记录查看测验答题情况
	 * 
	 * @author：黄崔俊
	 * @date ：2015-10-28
	 **************************************************************************/
	public List<TAssignmentItemMapping> findTAssignmentItemMappingsByTAssignmentGrading(
			TAssignmentGrading tAssignmentGrading);

	/*************************************************************************************
	 * Description:保存测验
	 * 
	 * @author：黄崔俊
	 * @date：2015-11-26 
	 *************************************************************************************/
	public TAssignmentItem saveExamItemForQuestion(TAssignmentItem tAssignmentItem, HttpServletRequest request);

	/*************************************************************************************
	 * Description:根据题库id分页查询试题
	 * 
	 * @author：黄崔俊
	 * @date：2015-12-7 
	 *************************************************************************************/
	public List<TAssignmentItem> findTAssignmentItemListByQuestionId(
			TAssignmentQuestionpool tAssignmentQuestionpool, Integer currpage,
			int pageSize);

	/*************************************************************************************
	 * Description:统计某课程各题库试题总数
	 * 
	 * @author：黄崔俊
	 * @date：2015-12-22
	 *************************************************************************************/
	public int countOrderItemListBySiteId(Integer cid, Integer questionId, Integer itemType);

	/*************************************************************************************
	 * Description:分页查询课程下题库中的试题（顺序学习）
	 * 
	 * @author：黄崔俊
	 * @date：2015-12-22
	 *************************************************************************************/
	public List<TAssignmentItem> findOrderItemListBySiteId(Integer cid,Integer currpage, int pageSize, Integer questionId, Integer itemType);

	/*************************************************************************************
	 * Description:根据课程，题库，类型,登陆人及是否正确统计数目
	 * 
	 * @author：黄崔俊
	 * @date：2015-12-28
	 *************************************************************************************/
	public Integer countItemByQuestionAndUser(Integer cid, Integer questionId,
			User user, String type, Integer iscorrect, Integer itemType);

	/*************************************************************************************
	 * Description:分页查询课程下题库中的试题（随机学习）
	 * 
	 * @author：黄崔俊
	 * @date：2015-12-29
	 *************************************************************************************/
	public List<TAssignmentItem> findStochasticItemListBySiteIdAndItemType(
			TExerciseInfo tExerciseInfo, Integer currpage, int pageSize, Integer itemType);

	/*************************************************************************************
	 * Description:分页查询课程下题库中的试题（错题学习）
	 * 
	 * @author：黄崔俊
	 * @date：2015-12-29
	 *************************************************************************************/
	public List<TMistakeItem> findMistakeItemListBySiteId(Integer cid,
			Integer currpage, int pageSize, Integer questionId, String orderType, Integer itemType);

	/*************************************************************************************
	 * Description:统计某课程各题库错题总数
	 * 
	 * @author：黄崔俊
	 * @date：2015-12-29
	 *************************************************************************************/
	public int countMistakeItemListBySiteId(Integer cid, Integer questionId, Integer itemType);
	/**************************************************************************
	 * Description:根据用户名，是否提交和测验查询测验答题情况
	 * 
	 * @author：黄崔俊
	 * @date ：2015-9-1
	 **************************************************************************/
	public List<TAssignmentItemMapping> findTAssignmentItemMappingsByUserAndTestAndSubmit(
			User nowUser, TAssignment test, Integer isSubmit);
	
}


