package net.xidlims.service.tcoursesite;

import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import net.xidlims.dao.TAssignmentAnswerDAO;
import net.xidlims.dao.TAssignmentItemDAO;
import net.xidlims.dao.TAssignmentQuestionpoolDAO;
import net.xidlims.dao.TCourseSiteDAO;
import net.xidlims.dao.TExerciseAnswerRecordDAO;
import net.xidlims.dao.TExerciseInfoDAO;
import net.xidlims.dao.TExerciseItemRecordDAO;
import net.xidlims.dao.TMistakeItemDAO;
import net.xidlims.domain.TAssignmentAnswer;
import net.xidlims.domain.TAssignmentItem;
import net.xidlims.domain.TCourseSite;
import net.xidlims.domain.TExerciseAnswerRecord;
import net.xidlims.domain.TExerciseInfo;
import net.xidlims.domain.TExerciseItemRecord;
import net.xidlims.domain.TMistakeItem;
import net.xidlims.domain.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("TExerciseItemRecordService")
public class TExerciseItemRecordServiceImpl implements TExerciseItemRecordService {

	@Autowired
	private TAssignmentItemDAO tAssignmentItemDAO;
	@Autowired
	private TAssignmentQuestionpoolDAO tAssignmentQuestionpoolDAO;
	@Autowired
	private TCourseSiteDAO tCourseSiteDAO;
	@Autowired
	private TAssignmentAnswerDAO tAssignmentAnswerDAO;
	@Autowired
	private TExerciseItemRecordDAO tExerciseItemRecordDAO;
	@Autowired
	private TExerciseAnswerRecordDAO tExerciseAnswerRecordDAO;
	@Autowired
	private TMistakeItemDAO tMistakeItemDAO;
	@Autowired
	private TExerciseInfoDAO tExerciseInfoDAO;
	@PersistenceContext
    private EntityManager entityManager;
	
	/**************************************************************************
	 * Description:保存学生答题信息
	 * 
	 * @author：黄崔俊
	 * @date ：2015-12-29 11:04:41
	 **************************************************************************/
	@Override
	public Map<String, String> saveItemRecord(User user, Integer cid,
			Integer questionId, Integer itemId, Integer answerId,
			String type) {
		Map<String, String> map = new HashMap<String, String>();
		//根据答案id查询答案信息
		TAssignmentAnswer tAssignmentAnswer = tAssignmentAnswerDAO.findTAssignmentAnswerById(answerId);
		//根据课程id查询课程
		TCourseSite tCourseSite = tCourseSiteDAO.findTCourseSiteById(cid);
		//根据题目id查询作业题目
		TAssignmentItem tAssignmentItem = tAssignmentItemDAO.findTAssignmentItemById(itemId);
		//查询该生是否已有该题答题记录
		String hql = "from TExerciseItemRecord t where t.TCourseSite.id = '"+cid+"' and t.user.username = '"+user.getUsername()+"'" +
				" and t.TAssignmentItem.id = '"+itemId+"' and t.exerciseType = '"+type+"'";
		if (questionId>0) {
			//如果是章节答题记录，则加上题库条件
			hql+=" and t.TAssignmentQuestionpool.questionpoolId = '"+questionId+"'";
		}else {
			hql+=" and t.TAssignmentQuestionpool.questionpoolId is null";
		}
		List<TExerciseItemRecord> tExerciseItemRecords = tExerciseItemRecordDAO.executeQuery(hql);
		if (tExerciseItemRecords.size()==0) {
			//如果没有答题记录则新增
			TExerciseItemRecord tExerciseItemRecord = new TExerciseItemRecord();
			tExerciseItemRecord.setExerciseType(type);
			tExerciseItemRecord.setTAssignmentItem(tAssignmentItem);
			tExerciseItemRecord.setTAssignmentQuestionpool(tAssignmentQuestionpoolDAO.findTAssignmentQuestionpoolByPrimaryKey(questionId));
			tExerciseItemRecord.setTCourseSite(tCourseSite);
			tExerciseItemRecord.setUser(user);
			tExerciseItemRecord.setCreateDate(Calendar.getInstance());
			tExerciseItemRecord.setModifyDate(Calendar.getInstance());
			tExerciseItemRecord.setSubmitDate(Calendar.getInstance());
			
			tExerciseItemRecord.setIscorrect(tAssignmentAnswer.getIscorrect());
			tExerciseItemRecord = tExerciseItemRecordDAO.store(tExerciseItemRecord);
			
			TExerciseAnswerRecord tExerciseAnswerRecord = new TExerciseAnswerRecord();
			tExerciseAnswerRecord.setTAssignmentAnswer(tAssignmentAnswer);
			tExerciseAnswerRecord.setTExerciseItemRecord(tExerciseItemRecord);
			tExerciseAnswerRecordDAO.store(tExerciseAnswerRecord);
			
		}else {
			TExerciseItemRecord tExerciseItemRecord = tExerciseItemRecords.get(0);
			//删除原有答题记录的答案记录
			for (TExerciseAnswerRecord tExerciseAnswerRecord : tExerciseItemRecord.getTExerciseAnswerRecords()) {
				tExerciseAnswerRecordDAO.remove(tExerciseAnswerRecord);
			}
			tExerciseItemRecord.setModifyDate(Calendar.getInstance());
			tExerciseItemRecord.setSubmitDate(Calendar.getInstance());
			
			tExerciseItemRecord.setIscorrect(tAssignmentAnswer.getIscorrect());
			tExerciseItemRecord.setTExerciseAnswerRecords(new HashSet<TExerciseAnswerRecord>());//答案记录置空
			tExerciseItemRecord = tExerciseItemRecordDAO.store(tExerciseItemRecord);
			
			TExerciseAnswerRecord tExerciseAnswerRecord = new TExerciseAnswerRecord();
			tExerciseAnswerRecord.setTAssignmentAnswer(tAssignmentAnswer);
			tExerciseAnswerRecord.setTExerciseItemRecord(tExerciseItemRecord);
			tExerciseAnswerRecordDAO.store(tExerciseAnswerRecord);
		}
		
		//如果答错了，则计入错题
		if (tAssignmentAnswer.getIscorrect()==0) {
			hql = "from TMistakeItem t where t.TCourseSite.id = '"+cid+"' " +
					" and t.TAssignmentItem.id = '"+itemId+"' and t.user.username = '"+user.getUsername()+"'";
			if (questionId>0) {
				//如果是章节错题，则加上题库条件
				hql+=" and t.TAssignmentQuestionpool.questionpoolId = '"+questionId+"'";
			}else {
				hql+=" and t.TAssignmentQuestionpool.questionpoolId is null";
			}
			List<TMistakeItem> mistakeItems = tMistakeItemDAO.executeQuery(hql);
			TMistakeItem tMistakeItem = new TMistakeItem();
			if (mistakeItems.size()>0) {
				//如果错题已记录，则错误数加一
				tMistakeItem = mistakeItems.get(0);
				tMistakeItem.setErrorCount(tMistakeItem.getErrorCount()+1);
				
			}else {
				//否则新增错题记录
				tMistakeItem.setErrorCount(1);
				tMistakeItem.setTAssignmentItem(tAssignmentItem);
				tMistakeItem.setTAssignmentQuestionpool(tAssignmentQuestionpoolDAO.findTAssignmentQuestionpoolByPrimaryKey(questionId));
				tMistakeItem.setTCourseSite(tCourseSite);
				tMistakeItem.setUser(user);
			}
			tMistakeItemDAO.store(tMistakeItem);
			String result = "你答错了，正确答案为：";
			if (tAssignmentItem.getType()==1||tAssignmentItem.getType()==4) {
				//多选或单选
				for (TAssignmentAnswer answer : tAssignmentItem.getTAssignmentAnswers()) {
					if (answer.getIscorrect()==1) {
						result += answer.getLabel()+"、";
					}
				}
				result = result.substring(0, result.length()-1)+"。";
			}
			if (tAssignmentItem.getType()==2||tAssignmentItem.getType()==8) {
				//判断题或填空题
				for (TAssignmentAnswer answer : tAssignmentItem.getTAssignmentAnswers()) {
					if (answer.getIscorrect()==1) {
						result += answer.getLabel()+"、";
					}
				}
				result = result.substring(0, result.length()-1)+"。";
			}
			map.put("result", result);
		}else {
			map.put("result", "你答对了！");
		}
		return map;
	}

	/**************************************************************************
	 * Description:查询学生练习信息
	 * 
	 * @author：黄崔俊
	 * @date ：2015-12-29 11:04:41
	 **************************************************************************/
	@Override
	public TExerciseInfo findStochasticStringByQuestionAndUser(Integer cid,
			Integer questionId, User user, int totalRecords, Integer itemType) {
		//查询对应学生对应课程的练习信息
		String hql = "from TExerciseInfo t where t.user.username = '"+user.getUsername()+"' and t.TCourseSite.id = "+cid+" ";
		if (questionId>0) {
			//如果是章节记录，则加上题库条件
			hql+=" and t.TAssignmentQuestionpool.questionpoolId = '"+questionId+"'";
		}else {
			hql+=" and t.TAssignmentQuestionpool.questionpoolId is null";
		}
		List<TExerciseInfo> tExerciseInfos = tExerciseInfoDAO.executeQuery(hql);
		TExerciseInfo tExerciseInfo = null;
		if (tExerciseInfos.size()>0) {
			//有就获取第一个
			tExerciseInfo = tExerciseInfos.get(0);
		}else {
			//如果没有则新增
			tExerciseInfo = new TExerciseInfo();
			tExerciseInfo.setTAssignmentQuestionpool(tAssignmentQuestionpoolDAO.findTAssignmentQuestionpoolByPrimaryKey(questionId));
			tExerciseInfo.setTCourseSite(tCourseSiteDAO.findTCourseSiteById(cid));
			tExerciseInfo.setUser(user);
		}
		//多选
		if (itemType==1&&(tExerciseInfo.getMultipleStochasticString()==null||"".equals(tExerciseInfo.getMultipleStochasticString()))) {
			//根据整数n生成一个1-n以逗号隔开的字符串
			String stochasticString = getSequence(totalRecords);
			//给随机学习赋打乱的练习顺序
			tExerciseInfo.setMultipleStochasticString(stochasticString);
			tExerciseInfo = tExerciseInfoDAO.store(tExerciseInfo);
		}
		//单选
		if (itemType==4&&(tExerciseInfo.getSingleStochasticString()==null||"".equals(tExerciseInfo.getSingleStochasticString()))) {
			//根据整数n生成一个1-n以逗号隔开的字符串
			String stochasticString = getSequence(totalRecords);
			//给随机学习赋打乱的练习顺序
			tExerciseInfo.setSingleStochasticString(stochasticString);
			tExerciseInfo = tExerciseInfoDAO.store(tExerciseInfo);
		}
		//填空
		if (itemType==8&&(tExerciseInfo.getBlankStochasticString()==null||"".equals(tExerciseInfo.getBlankStochasticString()))) {
			//根据整数n生成一个1-n以逗号隔开的字符串
			String stochasticString = getSequence(totalRecords);
			//给随机学习赋打乱的练习顺序
			tExerciseInfo.setBlankStochasticString(stochasticString);
			tExerciseInfo = tExerciseInfoDAO.store(tExerciseInfo);
		}
		return tExerciseInfo;
	}
	
	/**************************************************************************
	 * Description:根据整数no生成一个1-n以逗号隔开的字符串
	 * 
	 * @author：于侃
	 * @date ：2016-09-14
	 **************************************************************************/
	public String getSequence(int no) {
		Integer[] sequence = new Integer[no];
		for(int i = 0; i < no; i++){
		sequence[i] = i;
		}
		Random random = new Random();
		for(int i = 0; i < no; i++){
			int p = random.nextInt(no);
			int tmp = sequence[i];
			sequence[i] = sequence[p];
			sequence[p] = tmp;
		}
		random = null;
		String result = "";
		for (int i = 0; i < sequence.length; i++) {
			result += sequence[i]+",";
		}
		if(result.length()!=0){
			result = result.substring(0, result.length()-1);
		}
		
		return result;
	}

	/**************************************************************************
	 * Description:删除该生的练习答题答案记录
	 * 
	 * @author：黄崔俊
	 * @date ：2015-12-29 11:04:41
	 **************************************************************************/
	@Transactional
	@Override
	public int deleteTExerciseAnswerRecordsByUser(User nowUser) {
		//删除该生的练习答题答案记录
		String sql = "delete from t_exercise_answer_record where item_record_id in(select id from t_exercise_item_record where username = '"+nowUser.getUsername()+"')";
		int result = entityManager.createNativeQuery(sql).executeUpdate();
		return result;
	}
	
	/****************************************************************************
	 * Description:课程-练习-多选题保存答案 
	 * 
	 * @author：李军凯
	 * @date ：2016-08-25
	 ****************************************************************************/
	 public Map<String, String> saveMultiItemRecord(User user, Integer cid,Integer questionId,Integer itemId,String answer,String type)
	 {
		// TODO Auto-generated method stub
			Map<String, String> map = new HashMap<String, String>();
			//根据题号找到对应的题目
			TAssignmentItem tAssignmentItem = tAssignmentItemDAO.findTAssignmentItemById(itemId);
			//得到题目的所有答案
			Set<TAssignmentAnswer> tAssignmentAnswers = tAssignmentItem.getTAssignmentAnswers();
			//定义正确答案
			String rightAnswers = "";	
			//遍历答案集得到正确答案
			for(TAssignmentAnswer tAssignmentAnswer:tAssignmentAnswers)
			{
				if(tAssignmentAnswer.getIscorrect()==1)
				{rightAnswers += tAssignmentAnswer.getId()+"," ;}
			}		
			String result = "你答错了，正确答案为：";
			TCourseSite tCourseSite = tCourseSiteDAO.findTCourseSiteById(cid);
			//查询该生是否已有该题答题记录
			String hql = "from TExerciseItemRecord t where t.TCourseSite.id = '"+cid+"' and t.user.username = '"+user.getUsername()+"'" +
					" and t.TAssignmentItem.id = '"+itemId+"' and t.exerciseType = '"+type+"'";
			if (questionId>0) {
				//如果是章节答题记录，则加上题库条件
				hql+=" and t.TAssignmentQuestionpool.questionpoolId = '"+questionId+"'";
			}else {
				hql+=" and t.TAssignmentQuestionpool.questionpoolId is null";
			}
			List<TExerciseItemRecord> tExerciseItemRecords = tExerciseItemRecordDAO.executeQuery(hql);
			if (tExerciseItemRecords.size()==0) {
				//如果没有答题记录则新增
				TExerciseItemRecord tExerciseItemRecord = new TExerciseItemRecord();
				tExerciseItemRecord.setExerciseType(type);
				tExerciseItemRecord.setTAssignmentItem(tAssignmentItem);
				tExerciseItemRecord.setTAssignmentQuestionpool(tAssignmentQuestionpoolDAO.findTAssignmentQuestionpoolByPrimaryKey(questionId));
				tExerciseItemRecord.setTCourseSite(tCourseSite);
				tExerciseItemRecord.setUser(user);
				tExerciseItemRecord.setCreateDate(Calendar.getInstance());
				tExerciseItemRecord.setModifyDate(Calendar.getInstance());
				tExerciseItemRecord.setSubmitDate(Calendar.getInstance());				
					if(answer.equals(rightAnswers)){
						tExerciseItemRecord.setIscorrect(1);}
					else{
						tExerciseItemRecord.setIscorrect(0);}
				
				tExerciseItemRecord = tExerciseItemRecordDAO.store(tExerciseItemRecord);
				
				String[] answers = answer.split(",");
				for(String a:answers){
					TExerciseAnswerRecord tExerciseAnswerRecord = new TExerciseAnswerRecord();
					TAssignmentAnswer tAssignmentAnswer = tAssignmentAnswerDAO.findTAssignmentAnswerByPrimaryKey(Integer.parseInt(a));
					tExerciseAnswerRecord.setTAssignmentAnswer(tAssignmentAnswer);
					tExerciseAnswerRecord.setTExerciseItemRecord(tExerciseItemRecord);
					tExerciseAnswerRecordDAO.store(tExerciseAnswerRecord);
				}
			}else {
				TExerciseItemRecord tExerciseItemRecord = tExerciseItemRecords.get(0);
				//删除原有答题记录的答案记录
				for (TExerciseAnswerRecord tExerciseAnswerRecord : tExerciseItemRecord.getTExerciseAnswerRecords()) {
					tExerciseAnswerRecordDAO.remove(tExerciseAnswerRecord);
				}
				tExerciseItemRecord.setModifyDate(Calendar.getInstance());
				tExerciseItemRecord.setSubmitDate(Calendar.getInstance());
				if(answer.equals(rightAnswers)){
					tExerciseItemRecord.setIscorrect(1);}
				else{
					tExerciseItemRecord.setIscorrect(0);}
				//答案记录置空
				tExerciseItemRecord.setTExerciseAnswerRecords(new HashSet<TExerciseAnswerRecord>());
				tExerciseItemRecord = tExerciseItemRecordDAO.store(tExerciseItemRecord);
				String[] answers = answer.split(",");
				for(String a:answers){
					TExerciseAnswerRecord tExerciseAnswerRecord = new TExerciseAnswerRecord();
					TAssignmentAnswer tAssignmentAnswer = tAssignmentAnswerDAO.findTAssignmentAnswerByPrimaryKey(Integer.parseInt(a));
					tExerciseAnswerRecord.setTAssignmentAnswer(tAssignmentAnswer);
					tExerciseAnswerRecord.setTExerciseItemRecord(tExerciseItemRecord);
					tExerciseAnswerRecordDAO.store(tExerciseAnswerRecord);
				}
			}
			//与正确答案比较
			if(!answer.equals(rightAnswers)){
				hql = "from TMistakeItem t where t.TCourseSite.id = '"+cid+"' " +
						" and t.TAssignmentItem.id = '"+itemId+"' and t.user.username = '"+user.getUsername()+"'";
				if (questionId>0) {
					//如果是章节错题，则加上题库条件
					hql+=" and t.TAssignmentQuestionpool.questionpoolId = '"+questionId+"'";
				}else {
					hql+=" and t.TAssignmentQuestionpool.questionpoolId is null";
				}
				List<TMistakeItem> mistakeItems = tMistakeItemDAO.executeQuery(hql);
				TMistakeItem tMistakeItem = new TMistakeItem();
				if (mistakeItems.size()>0) {
					//如果错题已记录，则错误数加一
					tMistakeItem = mistakeItems.get(0);
					tMistakeItem.setErrorCount(tMistakeItem.getErrorCount()+1);
					
				}else {
					//否则新增错题记录
					tMistakeItem.setErrorCount(1);
					tMistakeItem.setTAssignmentItem(tAssignmentItem);
					tMistakeItem.setTAssignmentQuestionpool(tAssignmentQuestionpoolDAO.findTAssignmentQuestionpoolByPrimaryKey(questionId));
					tMistakeItem.setTCourseSite(tCourseSite);
					tMistakeItem.setUser(user);
				}
				tMistakeItemDAO.store(tMistakeItem);
				for (TAssignmentAnswer a : tAssignmentAnswers) {
					if (a.getIscorrect()==1) {
						result += a.getLabel()+"、";
					}					
				}
				result = result.substring(0, result.length()-1)+"。";
				map.put("result", result);
			}
									
			else {
					map.put("result", "你答对了！");
			}
			
			return map;
	 }
}
