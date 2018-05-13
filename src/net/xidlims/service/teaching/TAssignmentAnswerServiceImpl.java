package net.xidlims.service.teaching;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.xidlims.dao.TAssignmentAnswerDAO;
import net.xidlims.dao.TAssignmentItemDAO;
import net.xidlims.domain.TAssignmentAnswer;

@Service("TAssignmentAnswerService")
public class TAssignmentAnswerServiceImpl implements TAssignmentAnswerService {

	@Autowired
	private TAssignmentItemDAO tAssignmentItemDAO;
	@Autowired
	private TAssignmentAnswerDAO tAssignmentAnswerDAO;
	
	/*************************************************************************************
	 * @內容：保存测验小题答案选项
	 * @作者： 黄崔俊
	 * @日期：2015-8-31 14:44:56
	 *************************************************************************************/
	@Override
	public TAssignmentAnswer saveExamAnswer(TAssignmentAnswer tAssignmentAnswer) {
		// TODO Auto-generated method stub
		TAssignmentAnswer newTAssignmentAnswer = null;
		if (tAssignmentAnswer.getId()==null) {
			tAssignmentAnswer.setTAssignmentItem(tAssignmentItemDAO.findTAssignmentItemById(tAssignmentAnswer.getTAssignmentItem().getId()));
			newTAssignmentAnswer = tAssignmentAnswerDAO.store(tAssignmentAnswer);
		}
		return newTAssignmentAnswer;
	}

	/*************************************************************************************
	 * @內容：根据选项id查询选项
	 * @作者： 黄崔俊
	 * @日期：2015-8-31 15:07:09
	 *************************************************************************************/
	@Override
	public TAssignmentAnswer findTAssignmentAnswerById(Integer id) {
		// TODO Auto-generated method stub
		TAssignmentAnswer tAssignmentAnswer = tAssignmentAnswerDAO.findTAssignmentAnswerById(id);
		
		return tAssignmentAnswer;
	}

	/*************************************************************************************
	 * @內容：删除答案选项
	 * @作者： 黄崔俊
	 * @日期：2015-8-31 15:07:09
	 *************************************************************************************/
	@Override
	public void deleteExamAnswer(TAssignmentAnswer tAssignmentAnswer) {
		// TODO Auto-generated method stub
		tAssignmentAnswerDAO.remove(tAssignmentAnswer);
	}

}
