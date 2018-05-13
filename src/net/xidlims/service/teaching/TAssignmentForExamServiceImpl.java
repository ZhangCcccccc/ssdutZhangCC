package net.xidlims.service.teaching;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.xidlims.dao.TAssignmentAnswerAssignDAO;
import net.xidlims.dao.TAssignmentAnswerDAO;
import net.xidlims.dao.TAssignmentControlDAO;
import net.xidlims.dao.TAssignmentDAO;
import net.xidlims.dao.TAssignmentGradingDAO;
import net.xidlims.dao.TAssignmentItemDAO;
import net.xidlims.dao.TAssignmentItemMappingDAO;
import net.xidlims.dao.TAssignmentSectionDAO;
import net.xidlims.dao.TCourseSiteDAO;
import net.xidlims.dao.UserDAO;
import net.xidlims.domain.TAssignment;
import net.xidlims.domain.TAssignmentAnswer;
import net.xidlims.domain.TAssignmentAnswerAssign;
import net.xidlims.domain.TAssignmentControl;
import net.xidlims.domain.TAssignmentGrading;
import net.xidlims.domain.TAssignmentItem;
import net.xidlims.domain.TAssignmentItemMapping;
import net.xidlims.domain.TAssignmentQuestionpool;
import net.xidlims.domain.TAssignmentSection;
import net.xidlims.service.common.ShareService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("TAssignmentForExamServiceImpl")
public class TAssignmentForExamServiceImpl implements TAssignmentForExamService {

	@Autowired
	private TAssignmentAnswerAssignDAO tAssignmentAnswerAssignDAO;
	@Autowired
	private TAssignmentDAO tAssignmentDAO;

	@Autowired
	private TCourseSiteDAO tCourseSiteDAO;
	@Autowired
	private TAssignmentControlDAO tAssignmentControlDAO;
	@Autowired
	private TAssignmentItemDAO tAssignmentItemDAO;
	@Autowired
	private ShareService shareService;
	@Autowired
	private TAssignmentGradingDAO tAssignmentGradingDAO;
	@Autowired
	private TAssignmentAnswerDAO tAssignmentAnswerDAO;
	@Autowired
	private TAssignmentItemMappingDAO tAssignmentItemMappingDAO;
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private TAssignmentSectionDAO tAssignmentSectionDAO;

	
	
	/*************************************************************************************
	 * @內容：保存测验
	 * @作者： 魏誠
	 * @日期：2015-07-31
	 *************************************************************************************/
	public TAssignment saveTAssignmentForExam(HttpSession httpSession, TAssignment tAssignment, int flagID) {
		// 获取课程站点id
		int tCourseSiteId = Integer.parseInt(httpSession.getAttribute("selected_courseSite").toString());

		if (flagID == -1) {
			/*
			 * 保存TAssignment需先保存一对一子表，然后设置为空，保存，然后再重新设置一对一子表，再保存。
			 */
			// 设置作业的课程站点
			//tAssignment.setTCourseSite(tCourseSiteDAO.findTCourseSiteById(tCourseSiteId));
			// 记录作业的控制表
			TAssignmentControl tAssignmentControl = tAssignment.getTAssignmentControl();

			tAssignment.setTAssignmentControl(null);
			tAssignment = tAssignmentDAO.store(tAssignment);

			// 保存作业的控制表
			tAssignmentControl.setTAssignment(tAssignment);
			tAssignmentControl = tAssignmentControlDAO.store(tAssignmentControl);

			tAssignment.setTAssignmentControl(tAssignmentControl);
			tAssignmentDAO.store(tAssignment);
			// tAssignmentControlDAO.flush();
			return tAssignment;
		} else {
			// 记录作业的控制表
			TAssignmentControl tAssignmentControl = tAssignment.getTAssignmentControl();
			tAssignment.setTAssignmentControl(null);
			tAssignment = tAssignmentDAO.store(tAssignment);
			tAssignmentDAO.flush();

			// 保存作业的控制表
			tAssignmentControl.setTAssignment(tAssignment);
			tAssignmentControl = tAssignmentControlDAO.store(tAssignmentControl);

			tAssignment.setTAssignmentControl(tAssignmentControl);
			tAssignmentDAO.store(tAssignment);

			return tAssignment;
		}
	}

	/*************************************************************************************
	 * @內容：保存学生答题
	 * @作者： 魏誠
	 * @日期：2015-08-20
	 *************************************************************************************/
	public TAssignmentGrading saveTAssignmentGradeForExam(HttpServletRequest request,int assignmentId, Integer submitTime) {
		
		TAssignment tAssignment = tAssignmentDAO.findTAssignmentById(assignmentId);
		if(submitTime != 0){//若是提交，查询以前的最大提交数并加一,没有则为第一次提交
			String sql  = "from TAssignmentGrading c where c.TAssignment.id = '"+tAssignment.getId()+"' and c.userByStudent.username = '"+shareService.getUser().getUsername()+"'";
			List<TAssignmentGrading> tAssignmentGradings = tAssignmentGradingDAO.executeQuery(sql, 0, -1);
			/*if(tAssignment.getTAssignmentGradings().iterator().hasNext()){
				submitTime = tAssignment.getTAssignmentGradings().iterator().next().getSubmitTime()+1;
			}*/
			if(tAssignmentGradings.size()>0){
				submitTime = tAssignmentGradings.get(0).getSubmitTime()+1;
			}
		}
		// 定义总成绩变量
		float totalGrading = 0;

		// 获取分数
		for (TAssignmentSection tAssignmentSection : tAssignment.getTAssignmentSections()) {
			for (TAssignmentItem tAssignmentItem : tAssignmentSection.getTAssignmentItems()) {
				String[] answers = request.getParameterValues("answers" + tAssignmentItem.getId());
				String[] answertexts = request.getParameterValues("answertexts" + tAssignmentItem.getId());
				
				String sql ="from TAssignmentItemMapping c where c.TAssignment.id = '"+tAssignment.getId()+"' and c.TAssignmentItem.id = '"+tAssignmentItem.getId()+"' and c.userByStudent.username = '"+shareService.getUser().getUsername()+"' and c.submitTime = '"+submitTime+"'";
				List<TAssignmentItemMapping> tAssignmentItemMappings = tAssignmentItemMappingDAO.executeQuery(sql, 0, -1);
				
				ArrayList<String> answersArray = new ArrayList<String>();
				for (TAssignmentAnswer tAssignmentAnswer : tAssignmentItem.getTAssignmentAnswers()) {
					// 多选题正确答案赋值到数组中
					if (tAssignmentItem.getType() == 1 && tAssignmentAnswer.getIscorrect() == 1) {
						if (answers!=null && answers.length > 0) {
							/*for (int i = 0; i < answers.length; i++) {
								if (tAssignmentAnswer.getId() == Integer.parseInt(answers[i])) {
									answersArray.add(answers[i]);
									break;
								}
							}*/
							answersArray.add(tAssignmentAnswer.getId().toString());
						}
						
					}

					// 对错题判断
					if (tAssignmentItem.getType() == 2 && tAssignmentAnswer.getIscorrect() == 1) {
						if (answers!=null && answers.length > 0 && tAssignmentAnswer.getId() == Integer.parseInt(answers[0])) {
							totalGrading = tAssignmentItem.getScore().floatValue() + totalGrading;
							for (TAssignmentItemMapping tAssignmentItemMapping : tAssignmentItemMappings) {
								tAssignmentItemMapping.setAutoscore(tAssignmentItem.getScore());
								tAssignmentItemMappingDAO.store(tAssignmentItemMapping);
							}
							break;
						}
					}
					// 单选题判断
					if (tAssignmentItem.getType() == 4 && tAssignmentAnswer.getIscorrect() == 1) {
						if (answers!=null && answers.length > 0 && tAssignmentAnswer.getId() == Integer.parseInt(answers[0])) {
							totalGrading = tAssignmentItem.getScore().floatValue() + totalGrading;
							for (TAssignmentItemMapping tAssignmentItemMapping : tAssignmentItemMappings) {
								tAssignmentItemMapping.setAutoscore(tAssignmentItem.getScore());
								tAssignmentItemMappingDAO.store(tAssignmentItemMapping);
							}
							break;
						}
					}

				}
				// 填空题题判断
				if (tAssignmentItem.getType() == 8 && answers!=null && answers.length > 0) {
					
					int count = 0;
					for(int i = 0; i<answers.length; i++){//统计答对的填空数量
						if (answertexts[i]!=null&&answertexts[i].equals(tAssignmentAnswerDAO.findTAssignmentAnswerById(Integer.valueOf(answers[i])).getText())) {
							count++;
						}
					}
					if (count!=0) {//如果有答对的题，计入得分
						BigDecimal itemScore = tAssignmentItem.getScore();
						//以小题得分除以总空格数并乘以答对的空格数计算得分
						totalGrading = itemScore.multiply(new BigDecimal(count)).divide(new BigDecimal(answers.length)).floatValue() + totalGrading;
					
						for (TAssignmentItemMapping tAssignmentItemMapping : tAssignmentItemMappings) {
							tAssignmentItemMapping.setAutoscore(itemScore.multiply(new BigDecimal(count)).divide(new BigDecimal(answers.length)));
							tAssignmentItemMappingDAO.store(tAssignmentItemMapping);
						}
					}
				}

				if (tAssignmentItem.getType() == 1) {
					// 判断多选题是否正确
					int flag = 1;
					if (answers!=null && answers.length == answersArray.size()) {
						for (String a : answers) {
							// 变量answers，判断是否数组相等,多选或少选都算错
							int flagPart = 0;
							for (String b : answersArray) {
								if (a.equals(b)) {
									flagPart = 1;
								}
	
							}
							if (flagPart == 0) {
								flag = 0;
							}
	
						}
	
					} else {
						flag = 0;
					}
					//
					if (flag == 1) {
						totalGrading = tAssignmentItem.getScore().floatValue() + totalGrading;
						for (TAssignmentItemMapping tAssignmentItemMapping : tAssignmentItemMappings) {
							tAssignmentItemMapping.setAutoscore(tAssignmentItem.getScore());
							tAssignmentItemMappingDAO.store(tAssignmentItemMapping);
						}
					}
				}
				
			}
		}

		//

		TAssignmentGrading tAssignmentGrade = new TAssignmentGrading();
		String sql  = "from TAssignmentGrading c where c.TAssignment.id = '"+tAssignment.getId()+"' and c.userByStudent.username = '"+shareService.getUser().getUsername()+"'";
		List<TAssignmentGrading> tAssignmentGradings = tAssignmentGradingDAO.executeQuery(sql, 0, -1);
		if (tAssignmentGradings.size()>0) {
			tAssignmentGrade = tAssignmentGradings.get(0);
			/*if (totalGrading > tAssignmentGrade.getFinalScore().floatValue()){//若新成绩高于原成绩，则覆盖，否则不覆盖
				tAssignmentGrade.setFinalScore(new BigDecimal(totalGrading));
			}*/
			tAssignmentGrade.setFinalScore(new BigDecimal(totalGrading));//取最新一次成绩
			
		}else {
			//考试成绩
			tAssignmentGrade.setFinalScore(new BigDecimal(totalGrading));
			
			//考试的学生
			tAssignmentGrade.setUserByStudent(shareService.getUserDetail());
			//考试的试题对象
			tAssignmentGrade.setTAssignment(tAssignmentDAO.findTAssignmentById(assignmentId));
			
		}
		//改变提交次数
		tAssignmentGrade.setSubmitTime(submitTime);
		//提交测验的学生
		tAssignmentGrade.setUserByStudent(shareService.getUserDetail());
		
		Integer islate = 0;//0表示正常提交
		Calendar submitDate = Calendar.getInstance();
		Calendar dueDate = tAssignment.getTAssignmentControl().getDuedate();
		if(submitDate.after(dueDate)){
			islate = 1;//1表示迟交
		}
		tAssignmentGrade.setIslate(islate);
		tAssignmentGrade.setSubmitdate(submitDate);
		// 提交后返回作业列表
		return tAssignmentGradingDAO.store(tAssignmentGrade);
		
	}
	
	/*************************************************************************************
	 * @內容：保存学生答题记录
	 * @作者： 魏誠
	 * @日期：2015-08-20
	 *************************************************************************************/
	public void saveTAssignmentItemMapping(HttpServletRequest request,int assignmentId,Integer submitTime) {
		//初始化当前时间
		Calendar currentTime = Calendar.getInstance();
		// 获取试卷对象
		TAssignment tAssignment = tAssignmentDAO.findTAssignmentById(assignmentId);

		if(submitTime != 0){//若是提交，查询以前的最大提交数并加一,没有则为第一次提交
			String sql  = "from TAssignmentGrading c where c.TAssignment.id = '"+tAssignment.getId()+"' and c.userByStudent.username = '"+shareService.getUser().getUsername()+"'";
			List<TAssignmentGrading> tAssignmentGradings = tAssignmentGradingDAO.executeQuery(sql, 0, -1);
			/*if(tAssignment.getTAssignmentGradings().iterator().hasNext()){
				submitTime = tAssignment.getTAssignmentGradings().iterator().next().getSubmitTime()+1;
			}*/
			if(tAssignmentGradings.size()>0){
				submitTime = tAssignmentGradings.get(0).getSubmitTime()+1;
			}
		}
		for (TAssignmentSection tAssignmentSection : tAssignment.getTAssignmentSections()) {
			for (TAssignmentItem tAssignmentItem : tAssignmentSection.getTAssignmentItems()) {
				String[] answers = request.getParameterValues("answers" + tAssignmentItem.getId());
				String[] answertexts = request.getParameterValues("answertexts" + tAssignmentItem.getId());
				if(answers != null && answers.length>0){
					for(int i=0;i<answers.length;i++){
						TAssignmentItemMapping tAssignmentItemMapping = new TAssignmentItemMapping();
						//设置学生试卷相关的题目记录
						tAssignmentItemMapping.setTAssignmentItem(tAssignmentItem);
						//设置学生试卷对象记录
						tAssignmentItemMapping.setTAssignment(tAssignment);
						//设置学生试卷相关的考生对象
						tAssignmentItemMapping.setUserByStudent(shareService.getUserDetail());
						//设置学生试卷相关的答案记录
						tAssignmentItemMapping.setTAssignmentAnswer(tAssignmentAnswerDAO.findTAssignmentAnswerById(Integer.parseInt(answers[i])));
						if (answertexts!=null) {
							//设置学生试卷相关的答案记录
							tAssignmentItemMapping.setAnswerText(answertexts[i]);
						}
						//设置学生试卷相关的提交时间
						tAssignmentItemMapping.setSubmitDate(currentTime);
						//记录学生试卷是保存还是提交
						tAssignmentItemMapping.setSubmitTime(submitTime);
						//默认成绩为0
						tAssignmentItemMapping.setAutoscore(new BigDecimal(0));
                        //保存过程记录
						tAssignmentItemMappingDAO.store(tAssignmentItemMapping);
					}
					
				}
				
			}
		}
	}
	
	/*************************************************************************************
	 * @內容：删除学生保存未提交答题记录
	 * @作者： 魏誠
	 * @日期：2015-08-20
	 *************************************************************************************/
	public void deleteTAssignmentItemMapping(int assignmentId) {
		//获取当前学生保存未提交记录的列表
		List<TAssignmentItemMapping> tAssignmentItemMappingList = tAssignmentItemMappingDAO.executeQuery("select c from TAssignmentItemMapping c where c.TAssignment.id =" + assignmentId + " and c.userByStudent.username like '" + shareService.getUser().getUsername() + "' and c.submitTime=0", 0,-1);
		for(TAssignmentItemMapping tAssignmentItemMapping:tAssignmentItemMappingList){
			tAssignmentItemMappingDAO.remove(tAssignmentItemMapping);
		}
	}
	
	
	/*************************************************************************************
	 * @內容：根据站点编号和测验内容保存测验
	 * @作者： 黄崔俊
	 * @日期：2015-8-27 14:38:23
	 *************************************************************************************/
	@Override
	public TAssignment saveTAssignmentForExam(Integer cid,TAssignment tAssignment) {
		// TODO Auto-generated method stub
		TAssignment newTAssignment = null;
		if (tAssignment.getId()==null) {//没有id表示是新增
			/*
			 * 保存TAssignment需先保存一对一子表，然后设置为空，保存，然后再重新设置一对一子表，再保存。
			*/
			//设置作业的课程站点
			//tAssignment.setTCourseSite(tCourseSiteDAO.findTCourseSiteById(cid));
			//记录作业的控制表
			TAssignmentControl tAssignmentControl = tAssignment.getTAssignmentControl();
			//记录作业的设置表
			TAssignmentAnswerAssign tAssignmentAssign = tAssignment.getTAssignmentAnswerAssign();

			tAssignment.setTAssignmentControl(null);
			tAssignment.setTAssignmentAnswerAssign(null);
			
			tAssignment = tAssignmentDAO.store(tAssignment);
            
			//保存作业的控制表
			Calendar calendar = tAssignmentControl.getDuedate();
			calendar.set(Calendar.HOUR_OF_DAY, 23);
			calendar.set(Calendar.MINUTE, 59);
			calendar.set(Calendar.SECOND, 59);//处理结束时间，默认为当天的最后一秒
			tAssignmentControl.setDuedate(calendar);
			tAssignmentControl.setTAssignment(tAssignment);
			tAssignmentControl =tAssignmentControlDAO.store(tAssignmentControl);
			
			//保存作业的设置表
			tAssignmentAssign.setUser(userDAO.findUserByPrimaryKey(tAssignmentAssign.getUser().getUsername()));
			tAssignmentAssign.setTAssignment(tAssignment);
			tAssignmentAssign =tAssignmentAnswerAssignDAO.store(tAssignmentAssign);
			
			
			tAssignment.setTAssignmentControl(tAssignmentControl);
			tAssignment.setTAssignmentAnswerAssign(tAssignmentAssign);
			newTAssignment = tAssignmentDAO.store(tAssignment);
			//tAssignmentControlDAO.flush();
			
		}else{
			TAssignment oldTAssignment = tAssignmentDAO.findTAssignmentById(tAssignment.getId());
			oldTAssignment.setTitle(tAssignment.getTitle());
			oldTAssignment.setContent(tAssignment.getContent());
			
			TAssignmentControl oldTAssignmentControl = oldTAssignment.getTAssignmentControl();
			Calendar calendar = tAssignment.getTAssignmentControl().getDuedate();
			calendar.set(Calendar.HOUR_OF_DAY, 23);
			calendar.set(Calendar.MINUTE, 59);
			calendar.set(Calendar.SECOND, 59);//处理结束时间，默认为当天的最后一秒
			oldTAssignmentControl.setStartdate(tAssignment.getTAssignmentControl().getStartdate());
			oldTAssignmentControl.setDuedate(calendar);
			oldTAssignmentControl.setGradeToStudent(tAssignment.getTAssignmentControl().getGradeToStudent());
			oldTAssignmentControl.setGradeToTotalGrade(tAssignment.getTAssignmentControl().getGradeToTotalGrade());
			oldTAssignmentControl.setSubmitType(tAssignment.getTAssignmentControl().getSubmitType());
			oldTAssignmentControl.setTimelimit(tAssignment.getTAssignmentControl().getTimelimit());
			oldTAssignmentControl.setTAssignment(oldTAssignment);
			oldTAssignmentControl = tAssignmentControlDAO.store(oldTAssignmentControl);
			oldTAssignment.setTAssignmentControl(oldTAssignmentControl);
			
			
			//暂时作业设置表的这两个字段值是默认的
			TAssignmentAnswerAssign oldTAssignmentAnswerAssign = oldTAssignment.getTAssignmentAnswerAssign();
			oldTAssignmentAnswerAssign.setScore(tAssignment.getTAssignmentAnswerAssign().getScore());
			oldTAssignmentAnswerAssign.setUser(userDAO.findUserByPrimaryKey(tAssignment.getTAssignmentAnswerAssign().getUser().getUsername()));
			oldTAssignmentAnswerAssign.setTAssignment(oldTAssignment);
			oldTAssignmentAnswerAssign = tAssignmentAnswerAssignDAO.store(oldTAssignmentAnswerAssign);
			oldTAssignment.setTAssignmentAnswerAssign(oldTAssignmentAnswerAssign);
			
			newTAssignment = tAssignmentDAO.store(oldTAssignment);
			
		}
		return newTAssignment;
	}

	@Override
	public void deleteExamById(TAssignment exam) {
		// TODO Auto-generated method stub
		for (TAssignmentSection tAssignmentSection : exam.getTAssignmentSections()) {
			Set<TAssignmentItem> tAssignmentItemsTemp = new HashSet<TAssignmentItem>();
			for (TAssignmentItem tAssignmentItem : tAssignmentSection.getTAssignmentItems()) {
				if (tAssignmentItem.getTAssignmentQuestionpools().size()>0) {//如果该试题已加入题库，则仅解除与tAssignmentSection的关联
					tAssignmentItemsTemp.add(tAssignmentItem);
					tAssignmentItem.setTAssignmentSection(null);
					tAssignmentItemDAO.store(tAssignmentItem);
				}
			}
			tAssignmentSection.getTAssignmentItems().removeAll(tAssignmentItemsTemp);
			tAssignmentSectionDAO.store(tAssignmentSection);
		}
		
		tAssignmentDAO.remove(exam);
	}

	@Override
	public void importItemsInExam(Integer sectionId, Integer[] itemIdArray,Integer itemScore) {
		// TODO Auto-generated method stub
		TAssignmentSection tAssignmentSection = tAssignmentSectionDAO.findTAssignmentSectionById(sectionId);
		for (Integer itemId : itemIdArray) {
			TAssignmentItem tAssignmentItem = tAssignmentItemDAO.findTAssignmentItemById(itemId);
//			//如果该题在本大项中引用过或未被测验大项引用过，则直接建立连接
//			if (tAssignmentItem.getTAssignmentSection()==null||sectionId.equals(tAssignmentItem.getTAssignmentSection().getId())||Arrays.asList(findSectionIdsByItemParent(tAssignmentItem.getId())).contains(sectionId)) {
//				tAssignmentItem.setTAssignmentSection(tAssignmentSection);
//				tAssignmentItemDAO.store(tAssignmentItem);
//			}else{//否则的话就复制该题，并建立连接
			TAssignmentItem newTAssignmentItem = new TAssignmentItem();
			newTAssignmentItem.copy(tAssignmentItem);
			newTAssignmentItem.setId(null);
			newTAssignmentItem.setScore(new BigDecimal(itemScore));
			newTAssignmentItem.setTAssignmentSection(tAssignmentSection);
			newTAssignmentItem.setTAssignmentQuestionpools(new HashSet<TAssignmentQuestionpool>());
			newTAssignmentItem.setTAssignmentAnswers(new HashSet<TAssignmentAnswer>());
			newTAssignmentItem.setItemParent(tAssignmentItem.getId());
			newTAssignmentItem.setCreatedTime(Calendar.getInstance());
			newTAssignmentItem = tAssignmentItemDAO.store(newTAssignmentItem);
			Set<TAssignmentAnswer> answers = tAssignmentItem.getTAssignmentAnswers();
			for (TAssignmentAnswer tAssignmentAnswer : answers) {
				TAssignmentAnswer answer = new TAssignmentAnswer();
				answer.copy(tAssignmentAnswer);
				answer.setId(null);
				answer.setTAssignmentItem(newTAssignmentItem);
				tAssignmentAnswerDAO.store(answer);
			}
//			}
		}
		
	}

	@Override
	public TAssignment saveExamAttributes(TAssignment tAssignment) {
		// TODO Auto-generated method stub
		TAssignment oldTAssignment = tAssignmentDAO.findTAssignmentById(tAssignment.getId());
		
		TAssignmentControl oldTAssignmentControl = oldTAssignment.getTAssignmentControl();
		Calendar calendar = tAssignment.getTAssignmentControl().getDuedate();
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);//处理结束时间，默认为当天的最后一秒
		oldTAssignmentControl.setStartdate(tAssignment.getTAssignmentControl().getStartdate());
		oldTAssignmentControl.setDuedate(calendar);
		oldTAssignmentControl.setGradeToStudent(tAssignment.getTAssignmentControl().getGradeToStudent());
		oldTAssignmentControl.setGradeToTotalGrade(tAssignment.getTAssignmentControl().getGradeToTotalGrade());
		oldTAssignmentControl.setSubmitType(tAssignment.getTAssignmentControl().getSubmitType());
		oldTAssignmentControl.setTimelimit(tAssignment.getTAssignmentControl().getTimelimit());
		oldTAssignmentControl.setTAssignment(oldTAssignment);
		oldTAssignmentControl = tAssignmentControlDAO.store(oldTAssignmentControl);
		oldTAssignment.setTAssignmentControl(oldTAssignmentControl);
		
		TAssignment newTAssignment = tAssignmentDAO.store(oldTAssignment);
		
		return newTAssignment;
	}
	
	
	public Integer[] findSectionIdsByItemParent(Integer itemParent) {
		String sql ="from TAssignmentItem c where c.itemParent = '"+itemParent+"'";
		List<TAssignmentItem> tAssignmentItems = tAssignmentItemDAO.executeQuery(sql, 0, -1);
		Integer[] sectionIds = new Integer[tAssignmentItems.size()];
		int index = 0;
		for (TAssignmentItem tAssignmentItem : tAssignmentItems) {
			sectionIds[index] = tAssignmentItem.getTAssignmentSection().getId();
			index++;
		}
		return sectionIds;
	}
}