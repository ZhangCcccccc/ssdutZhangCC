package net.xidlims.service.tcoursesite;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestParam;

import net.xidlims.domain.TAssignmentItem;
import net.xidlims.domain.TAssignmentQuestionpool;
import net.xidlims.domain.TCourseSite;
import net.xidlims.domain.User;


public interface TAssignmentQuestionPoolService {

	/**************************************************************************
	 * Description:查询题库列表
	 * 
	 * @author：黄崔俊
	 * @date ：2015-10-23
	 **************************************************************************/
	List<TAssignmentQuestionpool> findQuestionList(Integer cid);

	/**************************************************************************
	 * Description:当前课程及登陆用户查询题库列表
	 * 
	 * @author：黄崔俊
	 * @date ：2015-10-23
	 **************************************************************************/
	Map<Integer, String> findQuestionListByTypeAndUser(Integer type,Integer cid, User user);

	/**************************************************************************
	 * Description:保存题库类别
	 * 
	 * @author：黄崔俊
	 * @date ：2015-10-23
	 **************************************************************************/
	TAssignmentQuestionpool saveTAssignmentQuestionPool(Integer cid,TAssignmentQuestionpool tAssignmentQuestionpool);

	/**************************************************************************
	 * Description:根据id查询题库类别
	 * 
	 * @author：黄崔俊
	 * @date ：2015-10-26
	 **************************************************************************/
	TAssignmentQuestionpool findTAssignmentQuestionpoolById(Integer id);

	/**************************************************************************
	 * Description:根据题库id查询题库下题目
	 * 
	 * @author：黄崔俊
	 * @date ：2015-10-27
	 **************************************************************************/
	List<TAssignmentItem> findTAssignmentItemsByQuestionId(Integer id, Integer currpage, Integer pageSize);

	/**************************************************************************
	 * Description:根据题库id导入测验题目（xls格式）
	 * 
	 * @author：黄崔俊
	 * @date ：2015-10-27
	 **************************************************************************/
	int importTAssignmentItemsXls(String filePath, Integer questionId);

	/**************************************************************************
	 * Description:根据题库id删除题库
	 * 
	 * @author：黄崔俊
	 * @date ：2015-10-29
	 **************************************************************************/
	void deleteTAssignmentQuestionPoolById(Integer questionId);

	/**************************************************************************
	 * Description:根据试题id删除试题
	 * 
	 * @author：黄崔俊
	 * @date ：2015-10-30
	 **************************************************************************/
	void deleteTAssignmentItemById(Integer itemId, Integer questionId);

	/**************************************************************************
	 * Description:根据题库id将试题导入题库
	 * 
	 * @author：黄崔俊
	 * @date ：2015-11-2
	 **************************************************************************/
	void importTAssignmentItemsByQuestionId(Integer questionId, Integer examId);

	/**************************************************************************
	 * Description:根据课程id查询可导入的题库
	 * 
	 * @author：黄崔俊
	 * @date ：2015-11-25
	 **************************************************************************/
	List<TAssignmentQuestionpool> findCheckQuestionList(Integer cid);

	/*********************************************************************************
	 * description 题库-课程题库题库{导入txt文档格式的试题}
	 * 
	 * @author 李军凯
	 * @date 2016-08-23
	 ************************************************************************************/
	String importTAssignmentItemsTxt(String filePath, Integer questionId);

	/**************************************************************************
	 * Description:分页查询课程下的题库列表
	 * 
	 * @author：黄崔俊
	 * @date ：2015-12-25
	 **************************************************************************/
	List<TAssignmentQuestionpool> findQuestionListBySiteId(Integer cid,
			Integer currpage, int pageSize);

	/**************************************************************************
	 * Description:查询题库id，title，数量
	 * 
	 * @author：黄崔俊
	 * @date ：2016-3-20
	 **************************************************************************/
	List<Object[]> findQuestionInfoListBySiteId(Integer cid, Integer itemType);

	/**************************************************************************
	 * Description:根据课程编号查询题库列表
	 * 
	 * @author：黄崔俊
	 * @date ：2016-3-20
	 **************************************************************************/
	List<TAssignmentQuestionpool> findSchoolCourseQuestionList(String courseNumber);

	/**************************************************************************
	 * Description:根据站点号查询题库数量
	 * 
	 * @author：黄崔俊
	 * @date ：2016-3-20
	 **************************************************************************/
	int countQuestionBySiteId(Integer cid);
	/**************************************************************************
	 * Description:课程-题库-题库复制
	 * 
	 * @author：李军凯
	 * @date ：2016-09-05
	 **************************************************************************/
	 List<TAssignmentQuestionpool> findQuestionListByUser(String username,Integer tCourseSiteId);
	/**************************************************************************
		* Description:题库-题库复制-获取题库列表
		* 
		* @author：李军凯
		* @date ：2016-09-05
	 **************************************************************************/
	 Set<TAssignmentQuestionpool> findCopyQuestionListByUser(String[] poolIds);
	 /**************************************************************************
	  * Description:题库-题库复制-保存复制的题库
	  * 
	  * @author：李军凯
	  * @date ：2016-09-05
	  **************************************************************************/
	 void saveCopyQuestionList(TAssignmentQuestionpool tAssignmentQuestionpool);
	 /**************************************************************************************
		 * Description:课程-题库-题库导出
		 * 
		 * @author：李军凯
		 * @date ：2016-09-07
		 *************************************************************************************/
	 void exportTAssignmentItemById(Integer questionId,HttpServletRequest request,HttpServletResponse response);
	 /**************************************************************************************
		 * Description:课程-题库-题库导出、
		 * 
		 * @author：李军凯
		 * @date ：2016-09-07
		 *************************************************************************************/
	 void exportExcelQuestionPoolById(Integer questionId,HttpServletRequest request,HttpServletResponse response)throws Exception;
	 
	 /**************************************************************************
	 * Description:根据题库id，题目数量和题目类型来判断是否超出题库该类题目总数
	 * 
	 * @author：于侃
	 * @date ：2016年10月27日 14:32:19
	 **************************************************************************/
	public String checkTestItemCount(Integer questionpoolId,Integer quantity,Integer type);
	
	/**************************************************************************
	 * Description:根据题库id，题目类型来获取题库该类题目总数
	 * 
	 * @author：于侃
	 * @date ：2016年11月20日 13:51:14
	 **************************************************************************/
	public int getItemCount(Integer questionpoolId,Integer type);
}


