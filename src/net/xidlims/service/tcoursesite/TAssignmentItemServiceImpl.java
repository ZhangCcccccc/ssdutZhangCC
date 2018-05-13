package net.xidlims.service.tcoursesite;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.xidlims.dao.TAssignmentAnswerDAO;
import net.xidlims.dao.TAssignmentItemDAO;
import net.xidlims.dao.TAssignmentItemMappingDAO;
import net.xidlims.dao.TAssignmentQuestionpoolDAO;
import net.xidlims.dao.TAssignmentSectionDAO;
import net.xidlims.dao.TCourseSiteDAO;
import net.xidlims.dao.TExerciseItemRecordDAO;
import net.xidlims.dao.TMistakeItemDAO;
import net.xidlims.dao.UserDAO;
import net.xidlims.domain.TAssignment;
import net.xidlims.domain.TAssignmentAnswer;
import net.xidlims.domain.TAssignmentGrading;
import net.xidlims.domain.TAssignmentItem;
import net.xidlims.domain.TAssignmentItemMapping;
import net.xidlims.domain.TAssignmentQuestionpool;
import net.xidlims.domain.TAssignmentSection;
import net.xidlims.domain.TCourseSite;
import net.xidlims.domain.TExerciseInfo;
import net.xidlims.domain.TExerciseItemRecord;
import net.xidlims.domain.TMistakeItem;
import net.xidlims.domain.User;
import net.xidlims.service.common.ShareService;

@Service("TAssignmentItemService")
public class TAssignmentItemServiceImpl implements TAssignmentItemService {

	@Autowired
	private TAssignmentItemDAO tAssignmentItemDAO;
	@Autowired
	private TAssignmentSectionDAO tAssignmentSectionDAO;
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private TAssignmentItemMappingDAO tAssignmentItemMappingDAO;
	@Autowired
	private TAssignmentAnswerDAO tAssignmentAnswerDAO;
	@Autowired
	private TAssignmentQuestionpoolDAO tAssignmentQuestionpoolDAO;
	@Autowired
	private TExerciseItemRecordDAO tExerciseItemRecordDAO;
	@Autowired
	private TMistakeItemDAO tMistakeItemDAO;
	@Autowired
	private TCourseSiteDAO tCourseSiteDAO;
	@Autowired
	private ShareService shareService;

	/**************************************************************************
	 * Description:保存测验小题
	 * 
	 * @author：黄崔俊
	 * @date ：2015-8-31
	 **************************************************************************/
	@Override
	public TAssignmentItem saveExamItem(TAssignmentItem tAssignmentItem,HttpServletRequest request) {
		// TODO Auto-generated method stub
		TAssignmentItem newTAssignmentItem = null;
		if (tAssignmentItem.getId()==null) {
			tAssignmentItem.setTAssignmentSection(tAssignmentSectionDAO.findTAssignmentSectionById(tAssignmentItem.getTAssignmentSection().getId()));
			tAssignmentItem.setUser(userDAO.findUserByPrimaryKey(tAssignmentItem.getUser().getUsername()));
			String[] answerLabelChoices =request.getParameterValues("answerLabelChoice");//所有选项 
			String[] answerLabels =request.getParameterValues("answerLabel");//正确选项
			String[] answerTexts =request.getParameterValues("answerText");//答案内容
			newTAssignmentItem = tAssignmentItemDAO.store(tAssignmentItem);
			if (tAssignmentItem.getType()==1||tAssignmentItem.getType()==4) {//多选或单选
				for (int i = 0;i<answerLabelChoices.length; i++) {
					TAssignmentAnswer tAssignmentAnswer = new TAssignmentAnswer();
					tAssignmentAnswer.setLabel(answerLabelChoices[i]);
					tAssignmentAnswer.setText(answerTexts[i]);
					int iscorrect = 0;
					for (String string : answerLabels) {
						if(string.equals(answerLabelChoices[i])){
							iscorrect = 1;
						}
					}
					tAssignmentAnswer.setIscorrect(iscorrect);
					tAssignmentAnswer.setTAssignmentItem(newTAssignmentItem);
					tAssignmentAnswerDAO.store(tAssignmentAnswer);
				}
			}
			if (tAssignmentItem.getType()==2) {//是非题
				for (int i = 0;i<answerLabelChoices.length; i++) {
					TAssignmentAnswer tAssignmentAnswer = new TAssignmentAnswer();
					tAssignmentAnswer.setLabel(answerLabelChoices[i]);
					tAssignmentAnswer.setText(answerTexts[i]);
					int iscorrect = 0;
					for (String string : answerLabels) {
						if(string.equals(answerLabelChoices[i])){
							iscorrect = 1;
						}
					}
					tAssignmentAnswer.setIscorrect(iscorrect);
					tAssignmentAnswer.setTAssignmentItem(newTAssignmentItem);
					tAssignmentAnswerDAO.store(tAssignmentAnswer);
				}
				/*for (int i = 0;i<answerLabels.length; i++) {
					TAssignmentAnswer tAssignmentAnswer = new TAssignmentAnswer();
					tAssignmentAnswer.setIscorrect(Integer.valueOf(answerLabels[i]));
					tAssignmentAnswer.setText(answerTexts[i]);
					
					tAssignmentAnswer.setTAssignmentItem(newTAssignmentItem);
					tAssignmentAnswerDAO.store(tAssignmentAnswer);
				}*/
			}
			
		}else {
			TAssignmentItem oldTAssignmentItem = tAssignmentItemDAO.findTAssignmentItemById(tAssignmentItem.getId());
			if(oldTAssignmentItem.getTAssignmentQuestionpools().size()==0){//如果该试题不与题库关联，则直接修改
				oldTAssignmentItem.setUser(userDAO.findUserByPrimaryKey(tAssignmentItem.getUser().getUsername()));
				oldTAssignmentItem.setCreatedTime(tAssignmentItem.getCreatedTime());
				oldTAssignmentItem.setDescription(tAssignmentItem.getDescription());
				oldTAssignmentItem.setScore(tAssignmentItem.getScore());
				oldTAssignmentItem.setSequence(tAssignmentItem.getSequence());
				oldTAssignmentItem.setType(tAssignmentItem.getType());
				newTAssignmentItem = tAssignmentItemDAO.store(oldTAssignmentItem);
				
				for (TAssignmentAnswer tAssignmentAnswer : newTAssignmentItem.getTAssignmentAnswers()) {
					tAssignmentAnswerDAO.remove(tAssignmentAnswer);
				}//把原答案清掉
				
				String[] answerLabelChoices =request.getParameterValues("answerLabelChoice");//所有选项 
				String[] answerLabels =request.getParameterValues("answerLabel");//正确选项
				String[] answerTexts =request.getParameterValues("answerText");//答案内容
				newTAssignmentItem = tAssignmentItemDAO.store(tAssignmentItem);
				if (tAssignmentItem.getType()==1||tAssignmentItem.getType()==4) {//多选或单选
					for (int i = 0;i<answerLabelChoices.length; i++) {
						TAssignmentAnswer tAssignmentAnswer = new TAssignmentAnswer();
						tAssignmentAnswer.setLabel(answerLabelChoices[i]);
						tAssignmentAnswer.setText(answerTexts[i]);
						int iscorrect = 0;
						for (String string : answerLabels) {
							if(string.equals(answerLabelChoices[i])){
								iscorrect = 1;
							}
						}
						tAssignmentAnswer.setIscorrect(iscorrect);
						tAssignmentAnswer.setTAssignmentItem(newTAssignmentItem);
						tAssignmentAnswerDAO.store(tAssignmentAnswer);
					}
				}
				if (tAssignmentItem.getType()==2) {//是非题
					for (int i = 0;i<answerLabelChoices.length; i++) {
						TAssignmentAnswer tAssignmentAnswer = new TAssignmentAnswer();
						tAssignmentAnswer.setLabel(answerLabelChoices[i]);
						tAssignmentAnswer.setText(answerTexts[i]);
						int iscorrect = 0;
						for (String string : answerLabels) {
							if(string.equals(answerLabelChoices[i])){
								iscorrect = 1;
							}
						}
						tAssignmentAnswer.setIscorrect(iscorrect);
						tAssignmentAnswer.setTAssignmentItem(newTAssignmentItem);
						tAssignmentAnswerDAO.store(tAssignmentAnswer);
					}
					/*for (int i = 0;i<answerLabels.length; i++) {
					TAssignmentAnswer tAssignmentAnswer = new TAssignmentAnswer();
					tAssignmentAnswer.setIscorrect(Integer.valueOf(answerLabels[i]));
					tAssignmentAnswer.setText(answerTexts[i]);
					
					tAssignmentAnswer.setTAssignmentItem(newTAssignmentItem);
					tAssignmentAnswerDAO.store(tAssignmentAnswer);
				}*/
				}
				if (tAssignmentItem.getType()==8) {//填空题
					String[] ss = tAssignmentItem.getDescription().split("\\{");//将题目用‘{’分割
					String description = tAssignmentItem.getDescription();//获取题干
					for (int i = 1;i<ss.length; i++) {
						TAssignmentAnswer tAssignmentAnswer = new TAssignmentAnswer();
						tAssignmentAnswer.setText(ss[i].substring(0, ss[i].indexOf("}")));//获取答案
						description = description.replace("{"+tAssignmentAnswer.getText()+"}", "_________");//将题干中的答案用"_________"代替
						tAssignmentAnswer.setIscorrect(1);
						tAssignmentAnswer.setTAssignmentItem(newTAssignmentItem);
						tAssignmentAnswerDAO.store(tAssignmentAnswer);
					}
					newTAssignmentItem.setDescriptionTemp(description);
					tAssignmentItemDAO.store(newTAssignmentItem);
				}
			}else {//与题库有关联则与题库解除关联，并复制该题重新与题库建立关联
				TAssignmentItem copyTAssignmentItem = new TAssignmentItem();
				copyTAssignmentItem.copy(oldTAssignmentItem);
				copyTAssignmentItem.setId(null);
				copyTAssignmentItem.setTAssignmentSection(null);
				copyTAssignmentItem.setTAssignmentQuestionpools(new HashSet<TAssignmentQuestionpool>());
				copyTAssignmentItem.setTAssignmentAnswers(new HashSet<TAssignmentAnswer>());
				copyTAssignmentItem.setItemParent(oldTAssignmentItem.getId());
				copyTAssignmentItem.setUser(userDAO.findUserByPrimaryKey(tAssignmentItem.getUser().getUsername()));
				copyTAssignmentItem.setCreatedTime(Calendar.getInstance());
				copyTAssignmentItem.setDescription(tAssignmentItem.getDescription());
				copyTAssignmentItem.setScore(tAssignmentItem.getScore());
				copyTAssignmentItem.setSequence(tAssignmentItem.getSequence());
				copyTAssignmentItem.setType(tAssignmentItem.getType());
				newTAssignmentItem = tAssignmentItemDAO.store(copyTAssignmentItem);
				
				for (TAssignmentAnswer tAssignmentAnswer : newTAssignmentItem.getTAssignmentAnswers()) {
					tAssignmentAnswerDAO.remove(tAssignmentAnswer);
				}//把原答案清掉
				
				String[] answerLabelChoices =request.getParameterValues("answerLabelChoice");//所有选项 
				String[] answerLabels =request.getParameterValues("answerLabel");//正确选项
				String[] answerTexts =request.getParameterValues("answerText");
				if (tAssignmentItem.getType()==1||tAssignmentItem.getType()==4) {//多选或单选
					for (int i = 0;i<answerLabelChoices.length; i++) {
						TAssignmentAnswer tAssignmentAnswer = new TAssignmentAnswer();
						tAssignmentAnswer.setLabel(answerLabelChoices[i]);
						tAssignmentAnswer.setText(answerTexts[i]);
						int iscorrect = 0;
						for (String string : answerLabels) {
							if(string.equals(answerLabelChoices[i])){
								iscorrect = 1;
							}
						}
						tAssignmentAnswer.setIscorrect(iscorrect);
						tAssignmentAnswer.setTAssignmentItem(newTAssignmentItem);
						tAssignmentAnswerDAO.store(tAssignmentAnswer);
					}
				}
				if (tAssignmentItem.getType()==2) {//是非题
					for (int i = 0;i<answerLabelChoices.length; i++) {
						TAssignmentAnswer tAssignmentAnswer = new TAssignmentAnswer();
						tAssignmentAnswer.setLabel(answerLabelChoices[i]);
						tAssignmentAnswer.setText(answerTexts[i]);
						int iscorrect = 0;
						for (String string : answerLabels) {
							if(string.equals(answerLabelChoices[i])){
								iscorrect = 1;
							}
						}
						tAssignmentAnswer.setIscorrect(iscorrect);
						tAssignmentAnswer.setTAssignmentItem(newTAssignmentItem);
						tAssignmentAnswerDAO.store(tAssignmentAnswer);
					}
					/*for (int i = 0;i<answerLabels.length; i++) {
					TAssignmentAnswer tAssignmentAnswer = new TAssignmentAnswer();
					tAssignmentAnswer.setIscorrect(Integer.valueOf(answerLabels[i]));
					tAssignmentAnswer.setText(answerTexts[i]);
					
					tAssignmentAnswer.setTAssignmentItem(newTAssignmentItem);
					tAssignmentAnswerDAO.store(tAssignmentAnswer);
				}*/
				}
				if (tAssignmentItem.getType()==8) {//填空题
					String[] ss = tAssignmentItem.getDescription().split("\\{");
					String description = tAssignmentItem.getDescription();
					for (int i = 1;i<ss.length; i++) {
						TAssignmentAnswer tAssignmentAnswer = new TAssignmentAnswer();
						tAssignmentAnswer.setText(ss[i].substring(0, ss[i].indexOf("}")));
						description = description.replace("{"+tAssignmentAnswer.getText()+"}", "_________");
						tAssignmentAnswer.setIscorrect(1);
						tAssignmentAnswer.setTAssignmentItem(newTAssignmentItem);
						tAssignmentAnswerDAO.store(tAssignmentAnswer);
					}
					newTAssignmentItem.setDescriptionTemp(description);
					tAssignmentItemDAO.store(newTAssignmentItem);
				}
				Set<TAssignmentQuestionpool> tAssignmentQuestionpools = oldTAssignmentItem.getTAssignmentQuestionpools();
				for (TAssignmentQuestionpool tAssignmentQuestionpool : tAssignmentQuestionpools) {
					tAssignmentQuestionpool.getTAssignmentItems().remove(oldTAssignmentItem);
					tAssignmentQuestionpool.getTAssignmentItems().add(newTAssignmentItem);
					tAssignmentQuestionpoolDAO.store(tAssignmentQuestionpool);
				}
			}
		}
		return newTAssignmentItem;
	}
	/**************************************************************************
	 * Description:根据测验小题id查询测验小题
	 * 
	 * @author：黄崔俊
	 * @date ：2015-8-31
	 **************************************************************************/
	@Override
	public TAssignmentItem findTAssignmentItemById(Integer id) {
		// TODO Auto-generated method stub
		TAssignmentItem tAssignmentItem = tAssignmentItemDAO.findTAssignmentItemById(id);		
		return tAssignmentItem;
	}
	/**************************************************************************
	 * Description:删除作业小题
	 * 
	 * @author：黄崔俊
	 * @date ：2015-8-31
	 **************************************************************************/
	@Override
	public void deleteTAssignmentItem(TAssignmentItem tAssignmentItem) {
		// TODO Auto-generated method stub
		Set<TAssignmentQuestionpool> tAssignmentQuestionpools = tAssignmentItem.getTAssignmentQuestionpools();
		if (tAssignmentQuestionpools.size()>0) {//如果该试题被题库调用，则仅删除与TAssignmentSection大项之间的关联
			tAssignmentItem.setTAssignmentSection(null);
			tAssignmentItem.setSequence(null);
			tAssignmentItemDAO.store(tAssignmentItem);
		}else {//否则就直接删除
			tAssignmentItemDAO.remove(tAssignmentItem);
		}
	}
	/**************************************************************************
	 * Description:根据测验id查询测验小题列表
	 * 
	 * @author：黄崔俊
	 * @date ：2015-8-31
	 **************************************************************************/
	@Override
	public List<TAssignmentItem> findTAssignmentItemListByExamId(int examId) {
		// TODO Auto-generated method stub
		String sql = "select c from TAssignmentItem c where c.TAssignmentSection.TAssignment.id = '" + examId +"' order by c.sequence";
		List<TAssignmentItem> items = tAssignmentItemDAO.executeQuery(sql, 0, -1);
		return items;
	}
	/**************************************************************************
	 * Description:根据当前登陆人和测验查询测验答题情况
	 * 
	 * @author：黄崔俊
	 * @date ：2015-9-1
	 **************************************************************************/
	@Override
	public List<TAssignmentItemMapping> findTAssignmentItemMappingsByUserAndExamId(
			User nowUser, TAssignment examInfo) {
		// TODO Auto-generated method stub
		String sql = "from TAssignmentItemMapping c where c.userByStudent.username like '" + nowUser.getUsername() + "' and c.submitTime=0 and c.TAssignment.id = '" + examInfo.getId() +"'";
		List<TAssignmentItemMapping> itemMappings = tAssignmentItemMappingDAO.executeQuery(sql, 0, -1);
		return itemMappings;
	}
	/**************************************************************************
	 * Description:根据用户名，是否提交和测验查询测验答题情况
	 * 
	 * @author：黄崔俊
	 * @date ：2015-9-1
	 **************************************************************************/
	@Override
	public List<TAssignmentItemMapping> findTAssignmentItemMappingsByUserAndTestAndSubmit(
			User user, TAssignment examInfo,Integer isSubmit) {
		// TODO Auto-generated method stub
		String sql = "from TAssignmentItemMapping c where c.userByStudent.username = '" + user.getUsername() + "' and c.submitTime='"+isSubmit+"' and c.TAssignment.id = '" + examInfo.getId() +"'";
		List<TAssignmentItemMapping> itemMappings = tAssignmentItemMappingDAO.executeQuery(sql, 0, -1);
		return itemMappings;
	}
	/**************************************************************************
	 * Description:根据成绩记录查看测验答题情况
	 * 
	 * @author：黄崔俊
	 * @date ：2015-10-28
	 **************************************************************************/
	@Override
	public List<TAssignmentItemMapping> findTAssignmentItemMappingsByTAssignmentGrading(
			TAssignmentGrading tAssignmentGrading) {
		// TODO Auto-generated method stub
		//tAssignmentGrading.getSubmitTime()获取最后一次的提交次数
		String sql = "from TAssignmentItemMapping c where c.userByStudent.username like '" + tAssignmentGrading.getUserByStudent().getUsername() + "' and c.submitTime='"+tAssignmentGrading.getSubmitTime()+"' and c.TAssignment.id = '" + tAssignmentGrading.getTAssignment().getId() +"'";
		List<TAssignmentItemMapping> itemMappings = tAssignmentItemMappingDAO.executeQuery(sql, 0, -1);
		return itemMappings;
	}
	/*************************************************************************************
	 * Description:保存测验
	 * 
	 * @author：黄崔俊
	 * @date：2015-11-26 
	 *************************************************************************************/
	@Override
	public TAssignmentItem saveExamItemForQuestion(
			TAssignmentItem tAssignmentItem, HttpServletRequest request) {
		// TODO Auto-generated method stub
		TAssignmentItem newTAssignmentItem = null;
		TAssignmentQuestionpool tAssignmentQuestionpool = tAssignmentQuestionpoolDAO.findTAssignmentQuestionpoolByPrimaryKey(Integer.valueOf(request.getParameter("questionId")));
		if (tAssignmentItem.getId()==null) {
			tAssignmentItem.setUser(userDAO.findUserByPrimaryKey(tAssignmentItem.getUser().getUsername()));
			tAssignmentItem.setCreatedTime(Calendar.getInstance());
			String[] answerLabelChoices =request.getParameterValues("answerLabelChoice");//所有选项 
			String[] answerLabels =request.getParameterValues("answerLabel");//正确选项
			String[] answerTexts =request.getParameterValues("answerText");
			newTAssignmentItem = tAssignmentItemDAO.store(tAssignmentItem);
			if (tAssignmentItem.getType()==1||tAssignmentItem.getType()==4) {//多选或单选
				for (int i = 0;i<answerLabelChoices.length; i++) {
					TAssignmentAnswer tAssignmentAnswer = new TAssignmentAnswer();
					tAssignmentAnswer.setLabel(answerLabelChoices[i]);
					tAssignmentAnswer.setText(answerTexts[i]);
					int iscorrect = 0;
					for (String string : answerLabels) {
						if(string.equals(answerLabelChoices[i])){
							iscorrect = 1;
						}
					}
					tAssignmentAnswer.setIscorrect(iscorrect);
					tAssignmentAnswer.setTAssignmentItem(newTAssignmentItem);
					tAssignmentAnswerDAO.store(tAssignmentAnswer);
				}
			}
			if (tAssignmentItem.getType()==2) {//是非题
				for (int i = 0;i<answerLabelChoices.length; i++) {
					TAssignmentAnswer tAssignmentAnswer = new TAssignmentAnswer();
					tAssignmentAnswer.setLabel(answerLabelChoices[i]);
					tAssignmentAnswer.setText(answerTexts[i]);
					int iscorrect = 0;
					for (String string : answerLabels) {
						if(string.equals(answerLabelChoices[i])){
							iscorrect = 1;
						}
					}
					tAssignmentAnswer.setIscorrect(iscorrect);
					tAssignmentAnswer.setTAssignmentItem(newTAssignmentItem);
					tAssignmentAnswerDAO.store(tAssignmentAnswer);
				}
				/*for (int i = 0;i<answerLabels.length; i++) {
					TAssignmentAnswer tAssignmentAnswer = new TAssignmentAnswer();
					tAssignmentAnswer.setIscorrect(Integer.valueOf(answerLabels[i]));
					tAssignmentAnswer.setText(answerTexts[i]);
					
					tAssignmentAnswer.setTAssignmentItem(newTAssignmentItem);
					tAssignmentAnswerDAO.store(tAssignmentAnswer);
				}*/
			}
			
			tAssignmentQuestionpool.getTAssignmentItems().add(newTAssignmentItem);
			tAssignmentQuestionpoolDAO.store(tAssignmentQuestionpool);
		}else {
			TAssignmentItem oldTAssignmentItem = tAssignmentItemDAO.findTAssignmentItemById(tAssignmentItem.getId());
			if (oldTAssignmentItem.getTAssignmentSection()==null) {//如果试题未被测验引用，则直接修改
				
				oldTAssignmentItem.setUser(userDAO.findUserByPrimaryKey(tAssignmentItem.getUser().getUsername()));
				oldTAssignmentItem.setDescription(tAssignmentItem.getDescription());
				oldTAssignmentItem.setScore(tAssignmentItem.getScore());
				oldTAssignmentItem.setType(tAssignmentItem.getType());
				newTAssignmentItem = tAssignmentItemDAO.store(oldTAssignmentItem);
				for (TAssignmentAnswer tAssignmentAnswer : newTAssignmentItem.getTAssignmentAnswers()) {
					tAssignmentAnswerDAO.remove(tAssignmentAnswer);
				}//把原答案清掉
				newTAssignmentItem.setTAssignmentAnswers(new HashSet<TAssignmentAnswer>());
				newTAssignmentItem = tAssignmentItemDAO.store(newTAssignmentItem);
				String[] answerLabelChoices =request.getParameterValues("answerLabelChoice");//所有选项 
				String[] answerLabels =request.getParameterValues("answerLabel");//正确选项
				String[] answerTexts =request.getParameterValues("answerText");
				if (tAssignmentItem.getType()==1||tAssignmentItem.getType()==4) {//多选或单选
					for (int i = 0;i<answerLabelChoices.length; i++) {
						TAssignmentAnswer tAssignmentAnswer = new TAssignmentAnswer();
						tAssignmentAnswer.setLabel(answerLabelChoices[i]);
						tAssignmentAnswer.setText(answerTexts[i]);
						int iscorrect = 0;
						for (String string : answerLabels) {
							if(string.equals(answerLabelChoices[i])){
								iscorrect = 1;
							}
						}
						tAssignmentAnswer.setIscorrect(iscorrect);
						tAssignmentAnswer.setTAssignmentItem(newTAssignmentItem);
						tAssignmentAnswerDAO.store(tAssignmentAnswer);
					}
				}
				if (tAssignmentItem.getType()==2) {//是非题
					for (int i = 0;i<answerLabelChoices.length; i++) {
						TAssignmentAnswer tAssignmentAnswer = new TAssignmentAnswer();
						tAssignmentAnswer.setLabel(answerLabelChoices[i]);
						tAssignmentAnswer.setText(answerTexts[i]);
						int iscorrect = 0;
						for (String string : answerLabels) {
							if(string.equals(answerLabelChoices[i])){
								iscorrect = 1;
							}
						}
						tAssignmentAnswer.setIscorrect(iscorrect);
						tAssignmentAnswer.setTAssignmentItem(newTAssignmentItem);
						tAssignmentAnswerDAO.store(tAssignmentAnswer);
					}
					/*for (int i = 0;i<answerLabels.length; i++) {
						TAssignmentAnswer tAssignmentAnswer = new TAssignmentAnswer();
						tAssignmentAnswer.setIscorrect(Integer.valueOf(answerLabels[i]));
						tAssignmentAnswer.setText(answerTexts[i]);
						
						tAssignmentAnswer.setTAssignmentItem(newTAssignmentItem);
						tAssignmentAnswerDAO.store(tAssignmentAnswer);
					}*/
				}
				if (tAssignmentItem.getType()==8) {//填空题
					String[] ss = tAssignmentItem.getDescription().split("\\{");
					String description = tAssignmentItem.getDescription();
					for (int i = 1;i<ss.length; i++) {
						TAssignmentAnswer tAssignmentAnswer = new TAssignmentAnswer();
						tAssignmentAnswer.setText(ss[i].substring(0, ss[i].indexOf("}")));
						description = description.replace("{"+tAssignmentAnswer.getText()+"}", "_________");
						tAssignmentAnswer.setIscorrect(1);
						tAssignmentAnswer.setTAssignmentItem(newTAssignmentItem);
						tAssignmentAnswerDAO.store(tAssignmentAnswer);
					}
					newTAssignmentItem.setDescriptionTemp(description);
					tAssignmentItemDAO.store(newTAssignmentItem);
				}
				tAssignmentQuestionpool.getTAssignmentItems().add(newTAssignmentItem);
				tAssignmentQuestionpoolDAO.store(tAssignmentQuestionpool);
			}else{//否则题库与原试题解除关联，然后复制该题，并与题库建立关联
				TAssignmentItem copyTAssignmentItem = new TAssignmentItem();
				copyTAssignmentItem.copy(oldTAssignmentItem);
				copyTAssignmentItem.setId(null);
				copyTAssignmentItem.setTAssignmentSection(null);
				copyTAssignmentItem.setTAssignmentQuestionpools(new HashSet<TAssignmentQuestionpool>());
				copyTAssignmentItem.setTAssignmentAnswers(new HashSet<TAssignmentAnswer>());
				copyTAssignmentItem.setItemParent(oldTAssignmentItem.getId());
				copyTAssignmentItem.setDescription(tAssignmentItem.getDescription());
				copyTAssignmentItem.setScore(tAssignmentItem.getScore());
				copyTAssignmentItem.setType(tAssignmentItem.getType());
				newTAssignmentItem = tAssignmentItemDAO.store(copyTAssignmentItem);
				
				String[] answerLabelChoices =request.getParameterValues("answerLabelChoice");//所有选项 
				String[] answerLabels =request.getParameterValues("answerLabel");//正确选项
				String[] answerTexts =request.getParameterValues("answerText");
				if (tAssignmentItem.getType()==1||tAssignmentItem.getType()==4) {//多选或单选
					for (int i = 0;i<answerLabelChoices.length; i++) {
						TAssignmentAnswer tAssignmentAnswer = new TAssignmentAnswer();
						tAssignmentAnswer.setLabel(answerLabelChoices[i]);
						tAssignmentAnswer.setText(answerTexts[i]);
						int iscorrect = 0;
						for (String string : answerLabels) {
							if(string.equals(answerLabelChoices[i])){
								iscorrect = 1;
							}
						}
						tAssignmentAnswer.setIscorrect(iscorrect);
						tAssignmentAnswer.setTAssignmentItem(newTAssignmentItem);
						tAssignmentAnswerDAO.store(tAssignmentAnswer);
					}
				}
				if (tAssignmentItem.getType()==2) {//是非题
					for (int i = 0;i<answerLabelChoices.length; i++) {
						TAssignmentAnswer tAssignmentAnswer = new TAssignmentAnswer();
						tAssignmentAnswer.setLabel(answerLabelChoices[i]);
						tAssignmentAnswer.setText(answerTexts[i]);
						int iscorrect = 0;
						for (String string : answerLabels) {
							if(string.equals(answerLabelChoices[i])){
								iscorrect = 1;
							}
						}
						tAssignmentAnswer.setIscorrect(iscorrect);
						tAssignmentAnswer.setTAssignmentItem(newTAssignmentItem);
						tAssignmentAnswerDAO.store(tAssignmentAnswer);
					}
					/*for (int i = 0;i<answerLabels.length; i++) {
						TAssignmentAnswer tAssignmentAnswer = new TAssignmentAnswer();
						tAssignmentAnswer.setIscorrect(Integer.valueOf(answerLabels[i]));
						tAssignmentAnswer.setText(answerTexts[i]);
						
						tAssignmentAnswer.setTAssignmentItem(newTAssignmentItem);
						tAssignmentAnswerDAO.store(tAssignmentAnswer);
					}*/
				}
				if (tAssignmentItem.getType()==8) {//填空题
					String[] ss = tAssignmentItem.getDescription().split("\\{");
					String description = tAssignmentItem.getDescription();
					for (int i = 1;i<ss.length; i++) {
						TAssignmentAnswer tAssignmentAnswer = new TAssignmentAnswer();
						tAssignmentAnswer.setText(ss[i].substring(0, ss[i].indexOf("}")));
						description = description.replace("{"+tAssignmentAnswer.getText()+"}", "_________");
						tAssignmentAnswer.setIscorrect(1);
						tAssignmentAnswer.setTAssignmentItem(newTAssignmentItem);
						tAssignmentAnswerDAO.store(tAssignmentAnswer);
					}
					newTAssignmentItem.setDescriptionTemp(description);
					tAssignmentItemDAO.store(newTAssignmentItem);
				}
				tAssignmentQuestionpool.getTAssignmentItems().remove(oldTAssignmentItem);
				tAssignmentQuestionpool.getTAssignmentItems().add(newTAssignmentItem);
				tAssignmentQuestionpoolDAO.store(tAssignmentQuestionpool);
			}
		}
		
		return newTAssignmentItem;
	}
	/*************************************************************************************
	 * Description:根据题库id分页查询试题
	 * 
	 * @author：黄崔俊
	 * @date：2015-12-7 
	 *************************************************************************************/
	@Override
	public List<TAssignmentItem> findTAssignmentItemListByQuestionId(
			TAssignmentQuestionpool tAssignmentQuestionpool, Integer currpage,
			int pageSize) {
		// TODO Auto-generated method stub
		List<TAssignmentItem> tAssignmentItems = null;
		StringBuffer sbf = new StringBuffer("select t from TAssignmentQuestionpool c,TAssignmentItem t where t.id in elements(c.TAssignmentItems) ");
		sbf.append(" and c.questionpoolId = '"+tAssignmentQuestionpool.getQuestionpoolId()+"'");
		if (currpage == -1) {
			tAssignmentItems = tAssignmentItemDAO.executeQuery(sbf.toString(), 0, -1);
		}else {
			tAssignmentItems = tAssignmentItemDAO.executeQuery(sbf.toString(), (currpage-1)*pageSize, pageSize);
		}
		return tAssignmentItems;
	}
	/*************************************************************************************
	 * Description:统计某课程各题库试题总数
	 * 
	 * @author：黄崔俊
	 * @date：2015-12-22
	 *************************************************************************************/
	@Override
	public int countOrderItemListBySiteId(Integer cid, Integer questionId, Integer itemType) {
		// TODO Auto-generated method stub
		String hql = "select count(distinct i) from TCourseSite t join t.TAssignmentQuestionpools q join q.TAssignmentItems i where t.id = "+cid+" and i.type = '"+itemType+"'";
		if (questionId>0) {//如果分章节练习，则加上题库id条件
			hql += " and q.questionpoolId = '"+questionId+"'";
		}
		int result = ((Long)tAssignmentItemDAO.createQuerySingleResult(hql).getSingleResult()).intValue();
		return result;
	}
	/*************************************************************************************
	 * Description:分页查询课程下题库中的试题（顺序学习）
	 * 
	 * @author：黄崔俊
	 * @date：2015-12-22
	 *************************************************************************************/
	@Override
	public List<TAssignmentItem> findOrderItemListBySiteId(Integer cid,
			Integer currpage, int pageSize, Integer questionId, Integer itemType) {
		// TODO Auto-generated method stub
		String hql = "select distinct i from TCourseSite t join t.TAssignmentQuestionpools q join q.TAssignmentItems i where t.id = "+cid+" and i.type = '"+itemType+"'";
		if (questionId>0) {//如果分章节练习，则加上题库id条件
			hql += " and q.questionpoolId = '"+questionId+"'";
		}
		List<TAssignmentItem> items = tAssignmentItemDAO.executeQuery(hql, (currpage-1)*pageSize, pageSize);
		return items;
	}
	/*************************************************************************************
	 * Description:根据课程，题库，类型,登陆人及是否正确统计数目
	 * 
	 * @author：黄崔俊
	 * @date：2015-12-28
	 *************************************************************************************/
	@Override
	public Integer countItemByQuestionAndUser(Integer cid, Integer questionId,
			User user, String type, Integer iscorrect,Integer itemType) {
		// TODO Auto-generated method stub
		String hql = "select count(c) from TExerciseItemRecord c where c.exerciseType = '"+type+"' " +
				"and c.TCourseSite.id = "+cid+" and c.user.username = '"+user.getUsername()+"' and c.iscorrect = '"+iscorrect+"' and c.TAssignmentItem.type = '"+itemType+"'"; 
		if (questionId>0) {//如果分章节练习，则加上题库id条件
			hql += " and c.TAssignmentQuestionpool.questionpoolId = '"+questionId+"'";
		}else{
			hql += " and c.TAssignmentQuestionpool.questionpoolId is null";
		}
		int result = ((Long)tExerciseItemRecordDAO.createQuerySingleResult(hql).getSingleResult()).intValue();
		return result;
	}
	/*************************************************************************************
	 * Description:分页查询课程下题库中的试题（随机学习）
	 * 
	 * @author：黄崔俊
	 * @date：2015-12-29
	 *************************************************************************************/
	@Override
	public List<TAssignmentItem> findStochasticItemListBySiteIdAndItemType(
			TExerciseInfo tExerciseInfo, Integer currpage, int pageSize,Integer itemType) {
		// TODO Auto-generated method stub
		List<TAssignmentItem> tAssignmentItems = new ArrayList<TAssignmentItem>();
		String[] pages = null;
		if (itemType==1) {
			pages = tExerciseInfo.getMultipleStochasticString().split(",");
		}
		if (itemType==4) {
			pages = tExerciseInfo.getSingleStochasticString().split(",");
		}
		if (itemType==8) {
			pages = tExerciseInfo.getBlankStochasticString().split(",");
		}
		List<Integer> ids = new ArrayList<Integer>();
		
		for(int i =(currpage-1)*pageSize;i<currpage*pageSize;i++){
			ids.add(Integer.valueOf(pages[i]));
		}
		TCourseSite tCoureSite = tCourseSiteDAO.findTCourseSiteById(tExerciseInfo.getTCourseSite().getId());
		String hql;
		for (Integer integer : ids) {
			hql = "select distinct i from TCourseSite t join t.TAssignmentQuestionpools q join q.TAssignmentItems i where t.id = "+tCoureSite.getId()+" and i.type = '"+itemType+"'";
			if (tExerciseInfo.getTAssignmentQuestionpool()!=null) {//如果分章节练习，则加上题库id条件
				hql += " and q.questionpoolId = '"+tExerciseInfo.getTAssignmentQuestionpool().getQuestionpoolId()+"'";
			}
			List<TAssignmentItem> items = tAssignmentItemDAO.executeQuery(hql, (integer-1)*pageSize, 1);
			tAssignmentItems.addAll(items);
		}
		return tAssignmentItems;
	}
	/*************************************************************************************
	 * Description:分页查询课程下题库中的试题（错题学习）
	 * 
	 * @author：黄崔俊
	 * @date：2015-12-29
	 *************************************************************************************/
	@Override
	public List<TMistakeItem> findMistakeItemListBySiteId(Integer cid,
			Integer currpage, int pageSize, Integer questionId, String orderType, Integer itemType) {
		// TODO Auto-generated method stub
		String hql = "select distinct m from TAssignmentItem i join i.TMistakeItems m " +
				"where m.TCourseSite.id = " + cid + " and i.type = '" + itemType +
				"' and m.user.username like '" + shareService.getUser().getUsername() + "' ";
		if (questionId>0) {//如果分章节练习，则加上题库id条件
			hql += " and m.TAssignmentQuestionpool.questionpoolId = '"+questionId+"'";
		}
		if("errorCount".equals(orderType)){
			hql += " order by m.errorCount desc";
		}else {
			hql += " order by m.id asc";
		}
		List<TMistakeItem> items = tMistakeItemDAO.executeQuery(hql, (currpage-1)*pageSize, pageSize);
		return items;
	}
	/*************************************************************************************
	 * Description:统计某课程各题库错题总数
	 * 
	 * @author：黄崔俊
	 * @date：2015-12-29
	 *************************************************************************************/
	@Override
	public int countMistakeItemListBySiteId(Integer cid, Integer questionId, Integer itemType) {
		// TODO Auto-generated method stub
		String hql = "select count(distinct i) from TAssignmentItem i join i.TMistakeItems m " +
				"where m.TCourseSite.id = " + cid + " and i.type = '" + itemType + 
				"' and m.user.username like '" + shareService.getUser().getUsername() +"' ";
		if (questionId>0) {//如果分章节练习，则加上题库id条件
			hql += " and m.TAssignmentQuestionpool.questionpoolId = '"+questionId+"'";
		}
		int result = ((Long)tAssignmentItemDAO.createQuerySingleResult(hql).getSingleResult()).intValue();
		return result;
	}

}
