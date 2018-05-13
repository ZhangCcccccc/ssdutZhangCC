package net.xidlims.service.teaching;

import net.xidlims.domain.TAssignmentAnswer;

public interface TAssignmentAnswerService {

	/*************************************************************************************
	 * @內容：保存测验小题答案选项
	 * @作者： 黄崔俊
	 * @日期：2015-8-31 14:44:56
	 *************************************************************************************/
	public TAssignmentAnswer saveExamAnswer(TAssignmentAnswer tAssignmentAnswer);

	/*************************************************************************************
	 * @內容：根据选项id查询选项
	 * @作者： 黄崔俊
	 * @日期：2015-8-31 15:07:09
	 *************************************************************************************/
	public TAssignmentAnswer findTAssignmentAnswerById(Integer id);

	/*************************************************************************************
	 * @內容：删除答案选项
	 * @作者： 黄崔俊
	 * @日期：2015-8-31 15:07:09
	 *************************************************************************************/
	public void deleteExamAnswer(TAssignmentAnswer tAssignmentAnswer);

	
}


