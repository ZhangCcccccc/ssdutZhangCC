package net.xidlims.service.tcoursesite;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.xidlims.domain.TExerciseInfo;
import net.xidlims.domain.User;

public interface TExerciseItemRecordService {

	/*************************************************************************************
	 * @param type 
	 * @內容：保存学生答题信息
	 * @作者：黄崔俊
	 * @日期：2015-12-29 11:04:41
	 *************************************************************************************/
	Map<String, String> saveItemRecord(User user, Integer cid,
			Integer questionId, Integer itemId, Integer answerId,
			String type);

	/*************************************************************************************
	 * @param totalRecords 
	 * @param itemType 
	 * @內容：查询学生练习信息
	 * @作者：黄崔俊
	 * @日期：2015-12-29 11:04:41
	 *************************************************************************************/
	TExerciseInfo findStochasticStringByQuestionAndUser(Integer cid,
			Integer questionId, User user, int totalRecords, Integer itemType);

	/*************************************************************************************
	 * @內容：删除该生的练习答题答案记录
	 * @作者：黄崔俊
	 * @日期：2015-12-30 15:41:14
	 *************************************************************************************/
	int deleteTExerciseAnswerRecordsByUser(User nowUser);

	/****************************************************************************
	 * Description:课程-练习-多选题保存答案 
	 * @author：李军凯
	 * @date ：2016-08-25

	 ****************************************************************************/
	 Map<String, String> saveMultiItemRecord(User user, Integer cid,Integer questionId,Integer itemId,String answer,String type);

	
}


