package net.xidlims.service.teaching;

import net.xidlims.domain.TAssignmentSection;

public interface TAssignmentSectionService {

	/*************************************************************************************
	 * @內容：根据id查询作业
	 * @作者： 黄崔俊
	 * @日期：2015-8-31 10:26:17
	 *************************************************************************************/
	TAssignmentSection findTAssignmentSectionById(Integer sectionId);

	/*************************************************************************************
	 * @內容：保存作业大题
	 * @作者： 黄崔俊
	 * @日期：2015-8-31 10:31:28
	 *************************************************************************************/
	TAssignmentSection saveExamSection(TAssignmentSection tAssignmentSection);
	
}


