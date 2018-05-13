package net.xidlims.service.teaching;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.xidlims.domain.TAssignment;
import net.xidlims.domain.TAssignmentGrading;

public interface TAssignmentForExamService {
	
	/*************************************************************************************
	 * @內容：保存 新建课程站点
	 * @作者： 魏誠
	 * @日期：2015-08-7
	 *************************************************************************************/
	public TAssignment saveTAssignmentForExam(HttpSession httpSession,TAssignment tAssignment,
			int flagID) ;

	/*************************************************************************************
	 * @param submitTime 
	 * @內容：保存学生答题
	 * @作者： 魏誠
	 * @日期：2015-08-20
	 *************************************************************************************/
	public TAssignmentGrading saveTAssignmentGradeForExam(HttpServletRequest request,int assignmentId, Integer submitTime);
	
	/*************************************************************************************
	 * @param submitTime 
	 * @內容：保存学生答题记录
	 * @作者： 魏誠
	 * @日期：2015-08-20
	 *************************************************************************************/
	public void saveTAssignmentItemMapping(HttpServletRequest request,int assignmentId, Integer submitTime);

	/*************************************************************************************
	 * @內容：删除学生保存未提交答题记录
	 * @作者： 魏誠
	 * @日期：2015-08-20
	 *************************************************************************************/
	public void deleteTAssignmentItemMapping(int assignmentId);
	
	/*************************************************************************************
	 * @內容：根据站点编号和测验内容保存测验
	 * @作者： 黄崔俊
	 * @日期：2015-8-27 14:38:23
	 *************************************************************************************/
	public TAssignment saveTAssignmentForExam(Integer cid,TAssignment tAssignment);
	
	/*************************************************************************************
	 * @內容：删除测验
	 * @作者： 黄崔俊
	 * @日期：2015-11-3 10:25:35
	 *************************************************************************************/
	public void deleteExamById(TAssignment exam);
	
	/*************************************************************************************
	 * @內容：引入试题到测验大项
	 * @作者： 黄崔俊
	 * @日期：2015-11-4 16:19:59
	 *************************************************************************************/
	public void importItemsInExam(Integer sectionId, Integer[] itemIdArray,Integer itemScore);
	
	/*************************************************************************************
	 * @內容：修改测验属性
	 * @作者：黄崔俊
	 * @日期：2016-1-14 16:49:18
	 *************************************************************************************/
	public TAssignment saveExamAttributes(TAssignment tAssignment);
}


