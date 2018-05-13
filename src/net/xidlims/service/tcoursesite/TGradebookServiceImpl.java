package net.xidlims.service.tcoursesite;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.xidlims.dao.TAssignmentDAO;
import net.xidlims.dao.TCourseSiteDAO;
import net.xidlims.dao.TExperimentSkillDAO;
import net.xidlims.dao.TGradeObjectDAO;
import net.xidlims.dao.TGradeRecordDAO;
import net.xidlims.dao.TGradebookDAO;
import net.xidlims.dao.TWeightSettingDAO;
import net.xidlims.domain.TAssignment;
import net.xidlims.domain.TAssignmentControl;
import net.xidlims.domain.TAssignmentGrading;
import net.xidlims.domain.TCourseSite;
import net.xidlims.domain.TCourseSiteUser;
import net.xidlims.domain.TExperimentSkill;
import net.xidlims.domain.TGradeObject;
import net.xidlims.domain.TGradeRecord;
import net.xidlims.domain.TGradebook;
import net.xidlims.domain.TWeightSetting;
import net.xidlims.domain.User;
import net.xidlims.service.teaching.TAssignmentGradingService;

@Service("TGradebookService")
public class TGradebookServiceImpl implements TGradebookService {

	@Autowired
	private TAssignmentDAO tAssignmentDAO;
	@Autowired
	private TGradebookDAO tGradebookDAO;
	@Autowired
	private TGradeObjectDAO tGradeObjectDAO;
	@Autowired
	private TGradeRecordDAO tGradeRecordDAO;
	@Autowired
	private TCourseSiteDAO tCourseSiteDAO;
	@Autowired
	private TWeightSettingDAO tWeightSettingDAO;
	@Autowired
	private TExperimentSkillDAO tExperimentSkillDAO;
	@Autowired
	private TAssignmentGradingService tAssignmentGradingService;
	@Autowired
	private TExperimentSkillService tExperimentSkillService;
	@PersistenceContext
	private EntityManager entityManager;

	/**************************************************************************
	 * Description:根据测验id查询是否进成绩册，是则加入成绩册
	 * 
	 * @author：黄崔俊
	 * @date ：2015-9-2 09:34:59
	 **************************************************************************/
	@Override
	public void saveGradebook(Integer cid, int assignmentId, TAssignmentGrading tAssignmentGrade) {
		// 根据id获取测验
		TAssignment tAssignment = tAssignmentDAO.findTAssignmentById(assignmentId);
		// 查询作业（或测验）的控制项
		TAssignmentControl tAssignmentControl = tAssignment.getTAssignmentControl();
		// 如果成绩需要加入成绩册，则加入成绩册
		if ("yes".equals(tAssignmentControl.getToGradebook())) {
			/*
			 * String sql = "from TGradebook c where c.TCourseSite.id ='"+cid+"'";
			 * List<TGradebook> tGradebooks = tGradebookDAO.executeQuery(sql, 0, -1);
			 * TGradebook tGradebook = null; if (tGradebooks.size()>0 ) {//有成绩册则只用该成绩册
			 * tGradebook = tGradebooks.get(0);
			 * 
			 * }else {//该课程站点无成绩册则新建 tGradebook = new TGradebook();
			 * tGradebook.setTCourseSite (tCourseSiteDAO.findTCourseSiteById(cid));
			 * tGradebook = tGradebookDAO.store(tGradebook); } sql =
			 * "from TGradeObject c where c.TGradebook.id ='" +tGradebook.getId()+
			 * "' and c.TAssignment.id ='"+tAssignment.getId()+"'"; List<TGradeObject>
			 * tGradeObjects = tGradeObjectDAO.executeQuery(sql, 0, -1); TGradeObject
			 * tGradeObject = null; if (tGradeObjects.size()>0)
			 * {//查询是否有该作业（或测验）的成绩单，有成绩单则直接添加学生成绩记录 tGradeObject = tGradeObjects.get(0);
			 * 
			 * }else {//没有则新建成绩单 tGradeObject = new TGradeObject();
			 * tGradeObject.setTAssignment(tAssignment); tGradeObject.setMarked("yes"
			 * .equals(tAssignmentControl.getGradeToTotalGrade
			 * ())?1:0);//是否计入课程成绩，读取作业或测验控制项（是否计入总成绩） tGradeObject.setPointsPossible
			 * (tAssignment.getTAssignmentAnswerAssign().getScore());
			 * tGradeObject.setReleased(0); tGradeObject.setTGradebook(tGradebook);
			 * tGradeObject.setTitle(tAssignment.getTitle());
			 * tGradeObject.setType(tAssignment.getType()); tGradeObject.setWeight(new
			 * BigDecimal(1));//默认权重为1 tGradeObject = tGradeObjectDAO.store(tGradeObject); }
			 */
			// 查询该课程成绩簿
			String sql = "from TGradebook c where c.TCourseSite.id ='" + cid + "'";
			TGradebook tGradebook = tGradebookDAO.executeQuery(sql, 0, -1).get(0);
			// 查询测验对应成绩题目
			sql = "from TGradeObject c where c.TGradebook.id ='" + tGradebook.getId() + "' and c.TAssignment.id ='"
					+ tAssignment.getId() + "'";
			List<TGradeObject> tGradeObjects = tGradeObjectDAO.executeQuery(sql, 0, -1);
			
			TGradeObject tGradeObject = null;
			List<TGradeRecord> tGradeRecords = new ArrayList<TGradeRecord>();
			TGradeRecord tGradeRecord =  null;
			
			if(tGradeObjects.size() !=0 && tGradeObjects !=null) {
				
				tGradeObject = tGradeObjects.get(0);
				// 查询测验对应成绩记录
				sql = "from TGradeRecord c where c.TGradeObject.id = '" + tGradeObject.getId() + "' and c.user.username = '"
						+ tAssignmentGrade.getUserByStudent().getUsername() + "'";
				tGradeRecords = tGradeRecordDAO.executeQuery(sql, 0, -1);
			}

			// 如果该生该次作业（或测验）成绩已有
			if (tGradeRecords.size() > 0) {
				tGradeRecord = tGradeRecords.get(0);
				/*
				 * if (tAssignmentGrade.getFinalScore().compareTo(tGradeRecord. getPoints())==1)
				 * {//如果新成绩高，则覆盖 tGradeRecord.setPoints(tAssignmentGrade.getFinalScore()); }
				 */
				// 如果最终分数不为空则更新分数，否则删除该成绩
				if(tAssignmentGrade.getFinalScore()!=null) {
					tGradeRecord.setPoints(tAssignmentGrade.getFinalScore());
				}else {
					tGradeRecordDAO.remove(tGradeRecord);
					tGradeRecordDAO.flush();
				}
				
			} else {
				// 没有成绩则新建
				tGradeRecord = new TGradeRecord();
				tGradeRecord.setPoints(tAssignmentGrade.getFinalScore());
				tGradeRecord.setTGradeObject(tGradeObject);
				tGradeRecord.setUser(tAssignmentGrade.getUserByStudent());
			}
			tGradeRecord.setRecordTime(Calendar.getInstance());
			// 如果最终分数不为空则保存成绩，否则不保存
			if(tAssignmentGrade.getFinalScore()!=null && tGradeObject !=null) {
				tGradeRecordDAO.store(tGradeRecord);
			}
		}
	}

	/**************************************************************************
	 * Description:根据作业测验或考试创建成绩册及成绩单
	 * 
	 * @author：黄崔俊
	 * @date ：2015-12-8 14:52:05
	 **************************************************************************/
	@Override
	public void createGradebook(Integer cid, TAssignment tAssignment) {
		// 查询作业（或测验）的控制项
		TAssignmentControl tAssignmentControl = tAssignment.getTAssignmentControl();
		// 如果成绩需要加入成绩册，则创建成绩册
		if ("yes".equals(tAssignmentControl.getToGradebook())) {
			// 查询课程对应成绩簿
			String sql = "from TGradebook c where c.TCourseSite.id ='" + cid + "'";
			List<TGradebook> tGradebooks = tGradebookDAO.executeQuery(sql, 0, -1);
			TGradebook tGradebook = null;
			// 有成绩册则使用该成绩册
			if (tGradebooks.size() > 0) {
				tGradebook = tGradebooks.get(0);
			} else {
				// 该课程站点无成绩册则新建
				tGradebook = new TGradebook();
				tGradebook.setTCourseSite(tCourseSiteDAO.findTCourseSiteById(cid));
				tGradebook = tGradebookDAO.store(tGradebook);
			}
			// 根据成绩册id和测验id查找试题
			sql = "from TGradeObject c where c.TGradebook.id ='" + tGradebook.getId() + "' and c.TAssignment.id ='"
					+ tAssignment.getId() + "'";
			List<TGradeObject> tGradeObjects = tGradeObjectDAO.executeQuery(sql, 0, -1);
			TGradeObject tGradeObject = null;
			// 查询是否有该作业（或测验）的成绩单，有成绩单则直接添加学生成绩记录
			if (tGradeObjects.size() > 0) {
				tGradeObject = tGradeObjects.get(0);
				tGradeObject.setPointsPossible(tAssignment.getTAssignmentAnswerAssign().getScore());
				tGradeObject = tGradeObjectDAO.store(tGradeObject);
			} else {
				// 没有则新建成绩单
				tGradeObject = new TGradeObject();
				tGradeObject.setTAssignment(tAssignment);
				tGradeObject.setMarked("yes".equals(tAssignmentControl.getGradeToTotalGrade()) ? 1 : 0);// 是否计入课程成绩，读取作业或测验控制项（是否计入总成绩）
				tGradeObject.setPointsPossible(tAssignment.getTAssignmentAnswerAssign().getScore());
				tGradeObject.setReleased(0);
				tGradeObject.setTGradebook(tGradebook);
				tGradeObject.setTitle(tAssignment.getTitle());
				tGradeObject.setType(tAssignment.getType());
				// 默认权重为1
				tGradeObject.setWeight(new BigDecimal(1));
				tGradeObject = tGradeObjectDAO.store(tGradeObject);
			}
		}

	}

	/**************************************************************************
	 * Description:根据课程和类型查看成绩科目列表
	 * 
	 * @author：黄崔俊
	 * @date ：2015-12-8 14:52:05
	 **************************************************************************/
	@Override
	public List<TGradeObject> findTGradeObjectsByTCourseSiteAndType(Integer cid, String type) {
		// 根据课程和类型查看成绩科目列表
		String hql = "select c from TGradebook t join t.TGradeObjects c where t.TCourseSite.id = '" + cid
				+ "' and c.TAssignment.status = '1'"
				+ " and c.TAssignment.TAssignmentControl.toGradebook = 'yes' and c.TAssignment.TAssignmentControl.gradeToTotalGrade = 'yes'";
		if (type != null) {
			hql += " and c.type = '" + type + "'";
		}
		hql += " order by c.TAssignment.id";
		List<TGradeObject> tGradeObjects = tGradeObjectDAO.executeQuery(hql, 0, -1);
		return tGradeObjects;
	}

	/**************************************************************************
	 * Description:根据科目和学生查看成绩列表
	 * 
	 * @author：黄崔俊
	 * @date ：2016-1-8 12:25:07
	 **************************************************************************/
	@Override
	public List<List<Object>> findStudentScoreRecords(List<TCourseSiteUser> tCourseSiteUsers,
			List<TGradeObject> tGradeObjects) {
		// 学生成绩列表的列表
		List<List<Object>> lists = new ArrayList<List<Object>>();
		// 学生成绩列表
		List<Object> list;
		// 遍历每个学生
		for (TCourseSiteUser tCourseSiteUser : tCourseSiteUsers) {
			// 学生成绩列表
			list = new ArrayList<Object>();
			// 获取学生编号
			list.add(tCourseSiteUser.getUser().getCname());
			// 获取学生姓名
			list.add(tCourseSiteUser.getUser().getUsername());
			// 成绩
			String score = "";
			// 总成绩
			BigDecimal totalScore = new BigDecimal(0);
			// 总权重
			BigDecimal totalWeight = new BigDecimal(0);
			if (tGradeObjects.size() != 0) {
				for (TGradeObject tGradeObject : tGradeObjects) {
					score = "-";
					for (TGradeRecord tGradeRecord : tCourseSiteUser.getUser().getTGradeRecords()) {
						if (tGradeRecord.getTGradeObject().getId().equals(tGradeObject.getId())) {
							score = tGradeRecord.getPoints().toString();
							if (score.contains(".")) {
								score = score.split("\\.")[0];
							}
							totalScore = totalScore.add(tGradeRecord.getPoints().multiply(tGradeObject.getWeight()));
						}

					}
					totalWeight = totalWeight.add(tGradeObject.getWeight());
					list.add(score);
				}
				list.add(totalScore.divide(totalWeight, 1, BigDecimal.ROUND_HALF_UP));
			} else {
				list.add(0);
			}
			lists.add(list);
		}
		return lists;
	}

	/**************************************************************************
	 * Description:根据科目和学生查看成绩总分列表
	 * 
	 * @author：黄崔俊
	 * @date ：2016-1-8 15:29:58
	 **************************************************************************/
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, BigDecimal> findSumScoresByTCourseSiteAndType(Integer cid, String type) {
		// 学生章节内容的成绩
		String sql = "select c.student_number,sum(c.points*t.weight) from `t_grade_record` c "
				+ "join t_grade_object t " + "on c.object_id = t.id " + "join t_assignment a "
				+ "on t.assignment_id = a.id " + "join t_assignment_control ac " + "on a.id = ac.assignment_id "
				+ "join wk_folder f " + "on a.folder_id = f.id " + "join wk_chapter ch " + "on f.chapter_id = ch.id "
				+ "join t_course_site s " + "on ch.site_id = s.id " + "where a.type = '" + type + "' "
				+ "and a.`status` = '1' " + "and s.id = '" + cid + "' " + "and ac.grade_to_totalGrade = 'yes'";
		if(type =="assignment") {
	    //由于实验报告的type也存的是assignment，因此姑且由名字差异（实验报告都有“实验报告”四个字）去除实验报告（因为数据已经推到学校了），但由此会导致知识-作业的名字不能设置为*某某实验报告等等*
			   sql += "and t.`title` not like '%实验报告'";
		}
		       sql += "group by c.student_number";
		List<Object> objects1 = entityManager.createNativeQuery(sql).getResultList();
		// 学生章节内学习单元的成绩
		String hql = "select c.student_number,sum(c.points*t.weight) from `t_grade_record` c "
				+ "join t_grade_object t " + "on c.object_id = t.id " + "join t_assignment a "
				+ "on t.assignment_id = a.id " + "join t_assignment_control ac " + "on a.id = ac.assignment_id "
				+ "join wk_folder f " + "on a.folder_id = f.id " + "join wk_lesson le " + "on f.lesson_id = le.id "
				+ "join wk_chapter ch " + "on f.chapter_id = ch.id " + "join t_course_site s " + "on ch.site_id = s.id "
				+ "where a.type = '" + type + "' " + "and a.`status` = '1' " + "and s.id = '" + cid + "' "
				+ "and ac.grade_to_totalGrade = 'yes'";
		if(type =="assignment") {
	    //由于实验报告的type也存的是assignment，因此姑且由名字差异（实验报告都有“实验报告”四个字）去除实验报告（因为数据已经推到学校了），但由此会导致知识-作业的名字不能设置为*某某实验报告等等*
			   hql += "and t.`title` not like '%实验报告'";
		}
		       hql += "group by c.student_number";
		List<Object> objects2 = entityManager.createNativeQuery(hql).getResultList();

		Map<String, BigDecimal> map = new HashMap<String, BigDecimal>();
		for (Object object : objects1) {
			Object[] objs = (Object[]) object;
			map.put((String) objs[0], (BigDecimal) objs[1]);
		}
		for (Object object : objects2) {
			Object[] objs = (Object[]) object;
			map.put((String) objs[0], (BigDecimal) objs[1]);
		}
		return map;
	}

	/**************************************************************************
	 * Description:查询权重设置情况
	 * 
	 * @author：于侃
	 * @date ：2016-09-13
	 **************************************************************************/
	@SuppressWarnings("unchecked")
	@Override
	public List<List<Object>> findWeightSetting(Integer cid, String type) {
		List<List<Object>> list = new ArrayList<List<Object>>();
		if ("assignment".equals(type) || "exam".equals(type) || "test".equals(type) || "attendence".equals(type)) {
			// 如果是作业，开始或者测验，查询其题目权重设置情况
			String sql = "SELECT o.id,a.title,o.weight FROM t_grade_object o " + "JOIN t_assignment a "
					+ "ON a.id = o.assignment_id " + "JOIN t_assignment_control ac " + "ON a.id = ac.assignment_id "
					+ "JOIN t_gradebook g " + "ON o.grade_id = g.id " + "JOIN t_course_site t " + "ON g.site_id = t.id "
					+ "WHERE a.type = '" + type + "' " + "and ac.grade_to_totalGrade = 'yes' " + "AND t.id = '" + cid
					+ "'";
			List<Object> objects = entityManager.createNativeQuery(sql).getResultList();
			// 将查询到的值存入list在存入list列表
			for (Object object : objects) {
				List<Object> list2 = new ArrayList<Object>();
				Object[] objs = (Object[]) object;
				list2.add(objs[0]);
				list2.add(objs[1]);
				list2.add(objs[2]);
				list.add(list2);
			}
		}
		return list;
	}

	/**************************************************************************
	 * Description:查询总评权重设置情况
	 * 
	 * @author：于侃
	 * @date ：2016-09-13
	 **************************************************************************/
	@Override
	public List<TWeightSetting> findWeightSettings(Integer cid) {

		// 获取实验室权重
		String hql = "from TWeightSetting c where c.TCourseSite.id = '" + cid + "' and c.type = 'skill'";
		List<TWeightSetting> weightSettings = tWeightSettingDAO.executeQuery(hql, 0, -1);
		if (weightSettings.size() == 0) {
			// 如果不存在对应权重，则创建，权重为0.5
			TWeightSetting weightSetting = new TWeightSetting();
			weightSetting.setCreateDate(Calendar.getInstance());
			weightSetting.setModifyDate(Calendar.getInstance());
			weightSetting.setTCourseSite(tCourseSiteDAO.findTCourseSiteById(cid));
			weightSetting.setType("skill");
			weightSetting.setWeight(new BigDecimal(0.1));
			tWeightSettingDAO.store(weightSetting);
		}
		// 根据课程id查询作业类型的权重设置
		hql = "from TWeightSetting c where c.TCourseSite.id = '" + cid + "' and c.type = 'assignment'";
		weightSettings = tWeightSettingDAO.executeQuery(hql, 0, -1);
		if (weightSettings.size() == 0) {
			// 如果不存在对应权重，则创建，权重为0.2
			TWeightSetting weightSetting = new TWeightSetting();
			weightSetting.setCreateDate(Calendar.getInstance());
			weightSetting.setModifyDate(Calendar.getInstance());
			weightSetting.setTCourseSite(tCourseSiteDAO.findTCourseSiteById(cid));
			weightSetting.setType("assignment");
			weightSetting.setWeight(new BigDecimal(0.2));
			tWeightSettingDAO.store(weightSetting);
		}
		// 根据课程id查询测试类型的权重设置
		hql = "from TWeightSetting c where c.TCourseSite.id = '" + cid + "' and c.type = 'exam'";
		weightSettings = tWeightSettingDAO.executeQuery(hql, 0, -1);
		if (weightSettings.size() == 0) {
			// 如果不存在对应权重，则创建，权重为0.3
			TWeightSetting weightSetting = new TWeightSetting();
			weightSetting.setCreateDate(Calendar.getInstance());
			weightSetting.setModifyDate(Calendar.getInstance());
			weightSetting.setTCourseSite(tCourseSiteDAO.findTCourseSiteById(cid));
			weightSetting.setType("exam");
			weightSetting.setWeight(new BigDecimal(0.3));
			tWeightSettingDAO.store(weightSetting);
		}
		// 根据课程id查询考试类型的权重设置
		hql = "from TWeightSetting c where c.TCourseSite.id = '" + cid + "' and c.type = 'test'";
		weightSettings = tWeightSettingDAO.executeQuery(hql, 0, -1);
		if (weightSettings.size() == 0) {
			// 如果不存在对应权重，则创建，权重为0.5
			TWeightSetting weightSetting = new TWeightSetting();
			weightSetting.setCreateDate(Calendar.getInstance());
			weightSetting.setModifyDate(Calendar.getInstance());
			weightSetting.setTCourseSite(tCourseSiteDAO.findTCourseSiteById(cid));
			weightSetting.setType("test");
			weightSetting.setWeight(new BigDecimal(0.4));
			tWeightSettingDAO.store(weightSetting);
		}
		// 根据课程id查询考勤类型的权重设置
		hql = "from TWeightSetting c where c.TCourseSite.id = '" + cid + "' and c.type = 'attendence'";
		weightSettings = tWeightSettingDAO.executeQuery(hql, 0, -1);
		if (weightSettings.size() == 0) {
			// 如果不存在对应权重，则创建，权重为0.5
			TWeightSetting weightSetting = new TWeightSetting();
			weightSetting.setCreateDate(Calendar.getInstance());
			weightSetting.setModifyDate(Calendar.getInstance());
			weightSetting.setTCourseSite(tCourseSiteDAO.findTCourseSiteById(cid));
			weightSetting.setType("attendence");
			weightSetting.setWeight(new BigDecimal(0.1));
			tWeightSettingDAO.store(weightSetting);
		}
		hql = "from TWeightSetting c where c.TCourseSite.id = '" + cid + "' and c.type not in ('expreport','exptest','expwork')";
		weightSettings = tWeightSettingDAO.executeQuery(hql, 0, -1);
		return weightSettings;
	}

	/**************************************************************************
	 * Description:查看总成绩列表
	 * 
	 * @author：黄崔俊
	 * @date ：2016-1-11 08:52:43
	 **************************************************************************/
	@Override
	public List<List<Object>> findTotalScoreInfo(List<TCourseSiteUser> tCourseSiteUsers,
			List<TWeightSetting> weightSettings, Integer cid) {
		// 查询作业总分
		Map<String, BigDecimal> assignmentScoreMap = findSumScoresByTCourseSiteAndType(cid, "assignment");
		// 查询测验总分
		Map<String, BigDecimal> examScoreMap = findSumScoresByTCourseSiteAndType(cid, "exam");
		// 查询考试总分
		Map<String, BigDecimal> testScoreMap = findSumScoresByTCourseSiteAndType(cid, "test");
		// 查询考勤总分
		Map<String, BigDecimal> attendenceScoreMap = findSumScoresByTCourseSiteAndType(cid, "attendence");
		// 查询实验总分
		Map<String, BigDecimal> experimentScoreMap = findExperimentScoresByTCourseSiteAndTCourseSiteUsers(cid, tCourseSiteUsers);
		// 设置权重
		BigDecimal assignmentTotalWeight = countTotalWeightByType(cid, "assignment");
		BigDecimal examTotalWeight = countTotalWeightByType(cid, "exam");
		BigDecimal testTotalWeight = countTotalWeightByType(cid, "test");
		BigDecimal attendenceTotalWeight = countTotalWeightByType(cid, "attendence");
		List<List<Object>> lists = new ArrayList<List<Object>>();
		// 循环每个学生
		for (TCourseSiteUser tCourseSiteUser : tCourseSiteUsers) {
			// 总成绩
			BigDecimal totalScore = new BigDecimal(0);
			// 总权重
			BigDecimal totalWeight = new BigDecimal(0);
			List<Object> objects = new ArrayList<Object>();
			// 姓名
			objects.add(tCourseSiteUser.getUser().getCname());
			// 学号
			objects.add(tCourseSiteUser.getUser().getUsername());
			if (assignmentScoreMap.get(tCourseSiteUser.getUser().getUsername()) != null) {
				// 如果有成绩
				BigDecimal assignmentScore = assignmentScoreMap.get(tCourseSiteUser.getUser().getUsername())
						.divide(assignmentTotalWeight, 1, BigDecimal.ROUND_HALF_UP);
				objects.add(assignmentScore);
				totalScore = totalScore.add(assignmentScore.multiply(weightSettings.get(0).getWeight()));
			} else {
				objects.add("-");
			}
			totalWeight = totalWeight.add(weightSettings.get(0).getWeight());

			if (examScoreMap.get(tCourseSiteUser.getUser().getUsername()) != null) {
				// 如果有成绩
				BigDecimal examScore = examScoreMap.get(tCourseSiteUser.getUser().getUsername()).divide(examTotalWeight,
						1, BigDecimal.ROUND_HALF_UP);
				objects.add(examScore);
				totalScore = totalScore.add(examScore.multiply(weightSettings.get(1).getWeight()));
			} else {
				objects.add("-");
			}
			totalWeight = totalWeight.add(weightSettings.get(1).getWeight());

			if (testScoreMap.get(tCourseSiteUser.getUser().getUsername()) != null) {
				// 如果有成绩
				BigDecimal testScore = testScoreMap.get(tCourseSiteUser.getUser().getUsername()).divide(testTotalWeight,
						1, BigDecimal.ROUND_HALF_UP);
				objects.add(testScore);
				totalScore = totalScore.add(testScore.multiply(weightSettings.get(2).getWeight()));
			} else {
				objects.add("-");
			}
			totalWeight = totalWeight.add(weightSettings.get(2).getWeight());
			if (attendenceScoreMap.get(tCourseSiteUser.getUser().getUsername()) != null) {
				// 如果有成绩
				BigDecimal attendenceScore = attendenceScoreMap.get(tCourseSiteUser.getUser().getUsername())
						.divide(attendenceTotalWeight, 1, BigDecimal.ROUND_HALF_UP);
				objects.add(attendenceScore);
				totalScore = totalScore.add(attendenceScore.multiply(weightSettings.get(3).getWeight()));
			} else {
				objects.add("-");
			}
			if (experimentScoreMap.get(tCourseSiteUser.getUser().getUsername()) != null) {
				// 如果有成绩
				BigDecimal experimentScore = experimentScoreMap.get(tCourseSiteUser.getUser().getUsername());
				objects.add(experimentScore);
				totalScore = totalScore.add(experimentScore.multiply(weightSettings.get(4).getWeight()));
			} else {
				objects.add("-");
			}
			// 总评
			objects.add(totalScore.intValue());
			totalWeight = totalWeight.add(weightSettings.get(3).getWeight());
			// 成绩改动
			objects.add(tCourseSiteUser.getIncrement());
			// 最终成绩
			objects.add(totalScore.intValue() + tCourseSiteUser.getIncrement());
			// objects.add(totalScore.divide(totalWeight,1,BigDecimal.ROUND_HALF_UP));
			lists.add(objects);
		}
		return lists;
	}

	/**************************************************************************
	 * Description:权重设置
	 * 
	 * @author：黄崔俊
	 * @date ：2016-1-11 10:21:17
	 **************************************************************************/
	public BigDecimal countTotalWeightByType(Integer cid, String type) {
		// 章节内容的权重
		String sql = "select sum(o.weight) from t_grade_object o " + "join t_assignment a "
				+ "on o.assignment_id = a.id " + "join t_assignment_control ac " + "on a.id = ac.assignment_id "
				+ "join wk_folder f " + "on a.folder_id = f.id " + "join wk_chapter ch " + "on f.chapter_id = ch.id "
				+ "join t_course_site s " + "on ch.site_id = s.id " + "where s.id = '" + cid + "' "
				+ "and ac.grade_to_totalGrade = 'yes' " + "and a.type = '" + type + "'";
		BigDecimal result1 = (BigDecimal) entityManager.createNativeQuery(sql).getSingleResult();
		// 章节学习单元的内容权重
		String hql = "select sum(o.weight) from t_grade_object o " + "join t_assignment a "
				+ "on o.assignment_id = a.id " + "join t_assignment_control ac " + "on a.id = ac.assignment_id "
				+ "join wk_folder f " + "on a.folder_id = f.id " + "join wk_lesson le " + "on f.lesson_id = le.id "
				+ "join wk_chapter ch " + "on le.chapter_id = ch.id " + "join t_course_site s "
				+ "on ch.site_id = s.id " + "where s.id = '" + cid + "' " + "and ac.grade_to_totalGrade = 'yes' "
				+ "and a.type = '" + type + "'";
		BigDecimal result2 = (BigDecimal) entityManager.createNativeQuery(hql).getSingleResult();
		if (result1 == null) {
			result1 = new BigDecimal(0);
		}
		if (result2 == null) {
			result2 = new BigDecimal(0);
		}
		BigDecimal result = result1.add(result2);
		// 如果result为0
		if (result.compareTo(new BigDecimal(0)) == 0) {
			result = new BigDecimal(1);
		}
		return result;
	}

	/**************************************************************************
	 * Description:权重设置
	 * 
	 * @author：黄崔俊
	 * @date ：2016-1-11 10:21:17
	 **************************************************************************/
	@Override
	public void singleWeightSetting(HttpServletRequest request, Integer cid) {
		String[] objectIds = request.getParameterValues("objectId");
		String[] weights = request.getParameterValues("weight");
		if (objectIds != null) {
			// 如果成绩簿题目不为空
			int i = 0;
			// 每个题目设置权重
			for (String objectId : objectIds) {
				TGradeObject tGradeObject = tGradeObjectDAO.findTGradeObjectById(Integer.valueOf(objectId));
				BigDecimal weight = new BigDecimal(Integer.valueOf(weights[i]) / 100.0);
				tGradeObject.setWeight(weight);
				tGradeObjectDAO.store(tGradeObject);
				i++;
			}
		} else {
			// 如果没有根据总评权重设置
			String[] weightSettingIds = request.getParameterValues("weightSettingId");
			int i = 0;
			for (String weightSettingId : weightSettingIds) {
				TWeightSetting tWeightSetting = tWeightSettingDAO
						.findTWeightSettingById(Integer.valueOf(weightSettingId));
				BigDecimal weight = new BigDecimal(Integer.valueOf(weights[i]) / 100.0);
				tWeightSetting.setWeight(weight);
				tWeightSetting.setModifyDate(Calendar.getInstance());
				tWeightSettingDAO.store(tWeightSetting);
				i++;
			}
		}
	}

	/**************************************************************************
	 * Description:根据objectId查询作业项下不同学生成绩列表
	 * 
	 * @author：于侃
	 * @date ：2016-09-13
	 **************************************************************************/
	@Override
	public List<TGradeRecord> findTGradeRecordsByObjectId(Integer objectId) {
		// 根据objectId查询作业项下不同学生成绩列表
		String hql = "select c from TGradeRecord c where c.TGradeObject.id = '" + objectId + "'";
		List<TGradeRecord> tGradeRecords = tGradeRecordDAO.executeQuery(hql, 0, -1);
		return tGradeRecords;
	}

	/**************************************************************************
	 * Description:根据学生学号查询学生不同作业项成绩列表
	 * 
	 * @author：于侃
	 * @date ：2016-09-13
	 **************************************************************************/
	@Override
	public List<TGradeRecord> findTGradeRecordsByUser(String username) {
		// 根据学生学号查询学生不同作业项成绩列表
		String hql = "select c from TGradeRecord c where  c.user.username like '" + username + "'";
		List<TGradeRecord> tGradeRecords = tGradeRecordDAO.executeQuery(hql, 0, -1);
		return tGradeRecords;
	}

	/**************************************************************************
	 * Description:根据课程id查询不同学生不同作业项成绩列表
	 * 
	 * @author：于侃
	 * @date ：2016年9月13日 17:06:06
	 **************************************************************************/
	@Override
	public List<TGradeRecord> findTGradeRecordsBySiteId(Integer cid) {
		// 根据课程id查询不同学生不同作业项成绩列表
		String hql = "select c from TGradeRecord c where c.TGradeObject.TGradebook.TCourseSite.id = '" + cid + "'";
		List<TGradeRecord> tGradeRecords = tGradeRecordDAO.executeQuery(hql, 0, -1);
		return tGradeRecords;
	}

	/**************************************************************************
	 * Description:根据课程id查询作业（测验）项列表
	 * 
	 * @author：于侃
	 * @date ：2016年9月13日 17:06:10
	 **************************************************************************/
	@Override
	public List<TGradeObject> findTGradeObjectsBySiteId(Integer cid) {
		// 根据课程id查询作业（测验）项列表
		String hql = "select c from TGradeObject c where c.TGradebook.TCourseSite.id = '" + cid + "'";
		List<TGradeObject> tGradeObjects = tGradeObjectDAO.executeQuery(hql, 0, -1);
		return tGradeObjects;
	}

	/**
	 * @功能：保存自定义成绩时课程号对应的成绩册t_gradebook
	 * @作者：张佳鸣 @时间：2017-11-15
	 */
	public TGradebook saveTGradebookByCustom(TCourseSite tCourseSite) {

		// 新建成绩册对象
		TGradebook tGradebook = new TGradebook();
		// 根据tCourseSite设置成绩册对象外键和title为“custom（自定义）”
		tGradebook.setTCourseSite(tCourseSite);
		tGradebook.setTitle("custom");
		// 保存成绩册对象
		TGradebook tGradebook1 = tGradebookDAO.store(tGradebook);
		// 返回保存的成绩册对象
		return tGradebook1;
	}

	/**
	 * @功能：保存成绩册中的自定义成绩名称
	 * @作者：张佳鸣 @时间：2017-11-15
	 */
	public TGradeObject saveTGradeObjectByCustom(TGradebook tGradebook, String title) {

		// 新建自定义成绩TGradeObject对象
		TGradeObject tGradeObject = new TGradeObject();
		// 根据tGradebook设置自定义成绩对象外键和其他字段
		tGradeObject.setTGradebook(tGradebook);
		tGradeObject.setTitle(title);
		// 新建BigDecimal对象，用于存成绩值，满分100
		BigDecimal finalScore = new BigDecimal(100);
		tGradeObject.setPointsPossible(finalScore);
		tGradeObject.setType("custom");
		tGradeObject.setReleased(0);
		tGradeObject.setMarked(1);
		// 新建BigDecimal对象，用于存成绩权重，初始值为1
		BigDecimal totalScore = new BigDecimal(1);
		tGradeObject.setWeight(totalScore);
		// 保存自定义成绩对象
		TGradeObject tGradeObject1 = tGradeObjectDAO.store(tGradeObject);
		// 返回保存的自定义成绩对象
		return tGradeObject1;
	}

	/**
	 * @功能：保存自定义成绩册相关具体成绩记录
	 * @作者：张佳鸣 @时间：2017-11-15
	 */
	public void saveTGradeRecordByCustom(User user, Integer score, TGradeObject tGradeObject) {

		// 新建自定义成绩册相关具体成绩记录
		TGradeRecord tGradeRecord = new TGradeRecord();
		// 设置tGradeRecord各字段值
		tGradeRecord.setUser(user);
		// 新建BigDecimal对象，用于存学生成绩
		BigDecimal finalScore = new BigDecimal(score);
		tGradeRecord.setPoints(finalScore);
		tGradeRecord.setTGradeObject(tGradeObject);
		tGradeRecord.setRecordTime(Calendar.getInstance());
		tGradeRecordDAO.store(tGradeRecord);
		tGradeRecordDAO.flush();

	}

	/**
	 * @功能：根据课程id查看自定义科目列表
	 * @作者：张佳鸣 @时间：2017-11-16
	 */
	public List<TGradeObject> findCustomTGradeObjectsByTCourseSite(Integer cid, String type) {

		// 根据课程id查看自定义科目列表
		String hql = "select c from TGradebook t join t.TGradeObjects c where t.TCourseSite.id = '" + cid
				+ "' and c.type = '" + type + "' ";

		List<TGradeObject> tGradeObjects = tGradeObjectDAO.executeQuery(hql, 0, -1);

		return tGradeObjects;
	}

	/**
	 * @功能：根据自定义科目和学生查看成绩列表
	 * @作者：张佳鸣 @时间：2017-11-16
	 */
	public List<List<Object>> findCustomStudentScoreRecords(List<TCourseSiteUser> tCourseSiteUsers,
			List<TGradeObject> tGradeObjects) {

		// 学生成绩列表的列表
		List<List<Object>> lists = new ArrayList<List<Object>>();
		// 学生成绩列表
		List<Object> list;
		// 遍历每个学生
		for (TCourseSiteUser tCourseSiteUser : tCourseSiteUsers) {
			// 学生成绩列表
			list = new ArrayList<Object>();
			// 获取学生编号
			list.add(tCourseSiteUser.getUser().getCname());
			// 获取学生姓名
			list.add(tCourseSiteUser.getUser().getUsername());
			// 成绩
			String score = "";
			if (tGradeObjects.size() != 0) {
				for (TGradeObject tGradeObject : tGradeObjects) {
					score = "-";
					for (TGradeRecord tGradeRecord : tCourseSiteUser.getUser().getTGradeRecords()) {
						if (tGradeRecord.getTGradeObject().getId().equals(tGradeObject.getId())) {
							score = tGradeRecord.getPoints().toString();
							if (score.contains(".")) {
								score = score.split("\\.")[0];
							}
						}
					}
					list.add(score);
				}
			} else {
				list.add(0);
			}
			lists.add(list);
		}
		return lists;
	}

	/**************************************************************************
	 * Description:（带自定义成绩）查看总成绩列表
	 * 
	 * @author：张佳鸣
	 * @date ：2017-11-16
	 **************************************************************************/
	@Override
	public List<List<Object>> findTotalScoreInfoWithCustom(List<TCourseSiteUser> tCourseSiteUsers,
			List<TWeightSetting> weightSettings, Integer cid, Integer customTGradeObjectsSize,
			List<List<Object>> customLists) {
		// 查询作业总分
		Map<String, BigDecimal> assignmentScoreMap = findSumScoresByTCourseSiteAndType(cid, "assignment");
		// 查询测验总分
		Map<String, BigDecimal> examScoreMap = findSumScoresByTCourseSiteAndType(cid, "exam");
		// 查询考试总分
		Map<String, BigDecimal> testScoreMap = findSumScoresByTCourseSiteAndType(cid, "test");
		// 查询考勤总分
		Map<String, BigDecimal> attendenceScoreMap = findSumScoresByTCourseSiteAndType(cid, "attendence");
		// 查询实验总分
		Map<String, BigDecimal> experimentScoreMap = findExperimentScoresByTCourseSiteAndTCourseSiteUsers(cid, tCourseSiteUsers);
		// 查询自定义成绩总分
		List<Map<String, BigDecimal>> customScoreLists = new ArrayList<Map<String, BigDecimal>>();
		for (int i = 0; i < customTGradeObjectsSize; i++) {

			Map<String, BigDecimal> customScoreMap = findCustomScoresByCustomLists(customLists, i);
			customScoreLists.add(customScoreMap);
		}

		// 设置权重
		BigDecimal assignmentTotalWeight = countTotalWeightByType(cid, "assignment");
		BigDecimal examTotalWeight = countTotalWeightByType(cid, "exam");
		BigDecimal testTotalWeight = countTotalWeightByType(cid, "test");
		BigDecimal attendenceTotalWeight = countTotalWeightByType(cid, "attendence");
		List<List<Object>> lists = new ArrayList<List<Object>>();
		// 循环每个学生
		for (TCourseSiteUser tCourseSiteUser : tCourseSiteUsers) {
			// 总成绩
			BigDecimal totalScore = new BigDecimal(0);
			// 总权重
			BigDecimal totalWeight = new BigDecimal(0);
			List<Object> objects = new ArrayList<Object>();
			// 姓名
			objects.add(tCourseSiteUser.getUser().getCname());
			// 学号
			objects.add(tCourseSiteUser.getUser().getUsername());
			if (assignmentScoreMap.get(tCourseSiteUser.getUser().getUsername()) != null) {
				// 如果有成绩
				BigDecimal assignmentScore = assignmentScoreMap.get(tCourseSiteUser.getUser().getUsername())
						.divide(assignmentTotalWeight, 1, BigDecimal.ROUND_HALF_UP);
				objects.add(assignmentScore);
				totalScore = totalScore.add(assignmentScore.multiply(weightSettings.get(0).getWeight()));
			} else {
				objects.add("-");
			}
			totalWeight = totalWeight.add(weightSettings.get(0).getWeight());

			if (examScoreMap.get(tCourseSiteUser.getUser().getUsername()) != null) {
				// 如果有成绩
				BigDecimal examScore = examScoreMap.get(tCourseSiteUser.getUser().getUsername()).divide(examTotalWeight,
						1, BigDecimal.ROUND_HALF_UP);
				objects.add(examScore);
				totalScore = totalScore.add(examScore.multiply(weightSettings.get(1).getWeight()));
			} else {
				objects.add("-");
			}
			totalWeight = totalWeight.add(weightSettings.get(1).getWeight());

			if (testScoreMap.get(tCourseSiteUser.getUser().getUsername()) != null) {
				// 如果有成绩
				BigDecimal testScore = testScoreMap.get(tCourseSiteUser.getUser().getUsername()).divide(testTotalWeight,
						1, BigDecimal.ROUND_HALF_UP);
				objects.add(testScore);
				totalScore = totalScore.add(testScore.multiply(weightSettings.get(2).getWeight()));
			} else {
				objects.add("-");
			}
			totalWeight = totalWeight.add(weightSettings.get(2).getWeight());
			if (attendenceScoreMap.get(tCourseSiteUser.getUser().getUsername()) != null) {
				// 如果有成绩
				BigDecimal attendenceScore = attendenceScoreMap.get(tCourseSiteUser.getUser().getUsername())
						.divide(attendenceTotalWeight, 1, BigDecimal.ROUND_HALF_UP);
				objects.add(attendenceScore);
				totalScore = totalScore.add(attendenceScore.multiply(weightSettings.get(3).getWeight()));
			} else {
				objects.add("-");
			}
			
			if (experimentScoreMap.get(tCourseSiteUser.getUser().getUsername()) != null) {
				// 如果有成绩
				BigDecimal experimentScore = experimentScoreMap.get(tCourseSiteUser.getUser().getUsername());
				objects.add(experimentScore);
				totalScore = totalScore.add(experimentScore.multiply(weightSettings.get(4).getWeight()));
			} else {
				objects.add("-");
			}
			
			for (int i = 0; i < customTGradeObjectsSize; i++) {

				if (customScoreLists.get(i).get(tCourseSiteUser.getUser().getUsername()) != null) {
					// 如果有成绩
					BigDecimal customScoreWeight = new BigDecimal(1);
					BigDecimal customScore = customScoreLists.get(i).get(tCourseSiteUser.getUser().getUsername())
							.divide(customScoreWeight, 1, BigDecimal.ROUND_HALF_UP);
					objects.add(customScore);
					totalScore = totalScore.add(customScore.multiply(weightSettings.get(i + 5).getWeight()));
				} else {
					objects.add("-");
				}
			}

			// 总评
			objects.add(totalScore.intValue());
			totalWeight = totalWeight.add(weightSettings.get(3).getWeight());
			// 成绩改动
			objects.add(tCourseSiteUser.getIncrement());
			// 最终成绩
			objects.add(totalScore.intValue() + tCourseSiteUser.getIncrement());
			// objects.add(totalScore.divide(totalWeight,1,BigDecimal.ROUND_HALF_UP));
			lists.add(objects);
		}
		return lists;
	}

	/**
	 * @功能：根据自定义类型和学生查看成绩列表
	 * @作者：张佳鸣 @时间：2017-11-16
	 */
	public Map<String, BigDecimal> findCustomScoresByCustomLists(List<List<Object>> customLists, Integer num) {

		Map<String, BigDecimal> map = new HashMap<String, BigDecimal>();
		for (List<Object> customList : customLists) {

			Object[] objs = (Object[]) customList.toArray();
			map.put((String) objs[1], new BigDecimal(Integer.parseInt(objs[num + 2].toString())));
		}
		return map;
	}

	/**
	 * @功能：（带自定义成绩）查询总评权重设置情况
	 * @作者：张佳鸣 @时间：2017-11-16
	 */
	public List<TWeightSetting> findWeightSettingsWithCustom(Integer cid, Integer customTGradeObjectsSize,
			List<TGradeObject> customTGradeObjects) {
		
		// 获取实验室权重
		String hql = "from TWeightSetting c where c.TCourseSite.id = '" + cid + "' and c.type = 'skill'";
		List<TWeightSetting> weightSettings = tWeightSettingDAO.executeQuery(hql, 0, -1);
		if (weightSettings.size() == 0) {
			// 如果不存在对应权重，则创建，权重为0.5
		    TWeightSetting weightSetting = new TWeightSetting();
			weightSetting.setCreateDate(Calendar.getInstance());
			weightSetting.setModifyDate(Calendar.getInstance());
			weightSetting.setTCourseSite(tCourseSiteDAO.findTCourseSiteById(cid));
			weightSetting.setType("skill");
			weightSetting.setWeight(new BigDecimal(0.1));
			tWeightSettingDAO.store(weightSetting);
		}
		// 根据课程id查询作业类型的权重设置
		hql = "from TWeightSetting c where c.TCourseSite.id = '" + cid + "' and c.type = 'assignment'";
		weightSettings = tWeightSettingDAO.executeQuery(hql, 0, -1);
		if (weightSettings.size() == 0) {
			// 如果不存在对应权重，则创建，权重为0.2
			TWeightSetting weightSetting = new TWeightSetting();
			weightSetting.setCreateDate(Calendar.getInstance());
			weightSetting.setModifyDate(Calendar.getInstance());
			weightSetting.setTCourseSite(tCourseSiteDAO.findTCourseSiteById(cid));
			weightSetting.setType("assignment");
			weightSetting.setWeight(new BigDecimal(0.2));
			tWeightSettingDAO.store(weightSetting);
		}
		// 根据课程id查询考试类型的权重设置
		hql = "from TWeightSetting c where c.TCourseSite.id = '" + cid + "' and c.type = 'exam'";
		weightSettings = tWeightSettingDAO.executeQuery(hql, 0, -1);
		if (weightSettings.size() == 0) {
			// 如果不存在对应权重，则创建，权重为0.3
			TWeightSetting weightSetting = new TWeightSetting();
			weightSetting.setCreateDate(Calendar.getInstance());
			weightSetting.setModifyDate(Calendar.getInstance());
			weightSetting.setTCourseSite(tCourseSiteDAO.findTCourseSiteById(cid));
			weightSetting.setType("exam");
			weightSetting.setWeight(new BigDecimal(0.3));
			tWeightSettingDAO.store(weightSetting);
		}
		// 根据课程id查询测试类型的权重设置
		hql = "from TWeightSetting c where c.TCourseSite.id = '" + cid + "' and c.type = 'test'";
		weightSettings = tWeightSettingDAO.executeQuery(hql, 0, -1);
		if (weightSettings.size() == 0) {
			// 如果不存在对应权重，则创建，权重为0.5
			TWeightSetting weightSetting = new TWeightSetting();
			weightSetting.setCreateDate(Calendar.getInstance());
			weightSetting.setModifyDate(Calendar.getInstance());
			weightSetting.setTCourseSite(tCourseSiteDAO.findTCourseSiteById(cid));
			weightSetting.setType("test");
			weightSetting.setWeight(new BigDecimal(0.4));
			tWeightSettingDAO.store(weightSetting);
		}
		// 根据课程id查询考勤类型的权重设置
		hql = "from TWeightSetting c where c.TCourseSite.id = '" + cid + "' and c.type = 'attendence'";
		weightSettings = tWeightSettingDAO.executeQuery(hql, 0, -1);
		if (weightSettings.size() == 0) {
			// 如果不存在对应权重，则创建，权重为0.5
			TWeightSetting weightSetting = new TWeightSetting();
			weightSetting.setCreateDate(Calendar.getInstance());
			weightSetting.setModifyDate(Calendar.getInstance());
			weightSetting.setTCourseSite(tCourseSiteDAO.findTCourseSiteById(cid));
			weightSetting.setType("attendence");
			weightSetting.setWeight(new BigDecimal(0.1));
			tWeightSettingDAO.store(weightSetting);
		}
		// 获取自定义成绩权重
		for (int i = 0; i < customTGradeObjectsSize; i++) {

			// 自定义成绩权重设置时，类型选择TGradeObject的id
			String weightType = customTGradeObjects.get(i).getId().toString();
			hql = "from TWeightSetting c where c.TCourseSite.id = '" + cid + "' and c.type =" + weightType;
			weightSettings = tWeightSettingDAO.executeQuery(hql, 0, -1);
			if (weightSettings.size() == 0) {
				// 如果不存在对应权重，则创建，权重为0.1
				TWeightSetting weightSetting = new TWeightSetting();
				weightSetting.setCreateDate(Calendar.getInstance());
				weightSetting.setModifyDate(Calendar.getInstance());
				weightSetting.setTCourseSite(tCourseSiteDAO.findTCourseSiteById(cid));
				weightSetting.setType(weightType);
				weightSetting.setWeight(new BigDecimal(0.1));
				tWeightSettingDAO.store(weightSetting);
			}
		}

		hql = "from TWeightSetting c where c.TCourseSite.id = '" + cid + "' and c.type not in ('expreport','exptest','expwork')";
		weightSettings = tWeightSettingDAO.executeQuery(hql, 0, -1);
		return weightSettings;
	}

	/**
	 * @功能：删除自定义成绩
	 * @作者：张佳鸣 @时间：2017-11-17
	 */
	public void deleteCustomScore(TGradeObject tGradeObject, Integer tCourseSiteId) {

		// 根据tCourseSiteId和tGradeObjectId确定唯一的tWeightSetting并删除
		String hql = "select t from TWeightSetting t where t.TCourseSite.id ='" + tCourseSiteId + "' and t.type ='"
				+ tGradeObject.getId().toString() + "'";
		TWeightSetting tWeightSetting = tWeightSettingDAO.executeQuery(hql, 0, -1).get(0);
		tWeightSettingDAO.remove(tWeightSetting);
		tWeightSettingDAO.flush();

		// 根据tGradeObjectId确定所有学生成绩详情册tGradeRecords并删除
		hql = "select t from TGradeRecord t where t.TGradeObject.id =" + tGradeObject.getId();
		List<TGradeRecord> tGradeRecords = tGradeRecordDAO.executeQuery(hql, 0, -1);
		for (TGradeRecord tGradeRecord : tGradeRecords) {

			tGradeRecordDAO.remove(tGradeRecord);
			tGradeRecordDAO.flush();
		}

		// 删除tGradeObject，提前保存好tGradeObject的grade_id字段，用以删除tGradebook
		int gradeId = tGradeObject.getTGradebook().getId();
		tGradeObjectDAO.remove(tGradeObject);
		tGradeObjectDAO.flush();

		// 根据提前保存好的tGradeObject的grade_id字段查找tGradebook并删除
		TGradebook tGradebook = tGradebookDAO.findTGradebookByPrimaryKey(gradeId);
		tGradebookDAO.remove(tGradebook);
		tGradebookDAO.flush();
	}
	
	/*****************************************************
	 * Description:获取实验项目中的预习测试实验报告和作业
	 *
	 *@auther:张佳鸣
	 *@date：2017-12-04
	 *****************************************************/
	@Override
	public List<TWeightSetting> getWorkAndTestAndReport(Integer tCourseSiteId) {
		TCourseSite tCourseSite = tCourseSiteDAO.findTCourseSiteById(tCourseSiteId);
		String type="('expreport','exptest','expwork')";
		String sql="select w from TWeightSetting w where w.TCourseSite.id="+tCourseSiteId;
		sql+=" and w.type in "+type;
		List<TWeightSetting> list = tWeightSettingDAO.executeQuery(sql);
		if(list.size()<=0){
			//向数据库中添加相关数据
			  String typeList[]={"expreport","exptest","expwork"};
			for(int i=0;i<3;i++){
				TWeightSetting setting=new TWeightSetting();
				setting.setTCourseSite(tCourseSite);
				setting.setType(typeList[i]);
				setting.setWeight(new BigDecimal(1));
				setting.setCreateDate(Calendar.getInstance());
				setting.setModifyDate(Calendar.getInstance());
				tWeightSettingDAO.store(setting);
				tWeightSettingDAO.flush();
			}
		}
		List<TWeightSetting> list2 = tWeightSettingDAO.executeQuery(sql);
		return list2;
	}
	
	/****************************************************
	 * Description:获取实验项目成绩
	 *
	 *@auther:张佳鸣
	 *@date：2017-12-04
	 *****************************************************/
	public List<List<Object>> findStudentScoreRecordsWithAll(
			List<TCourseSiteUser> tCourseSiteUsers,List<TGradeObject> testObjects,
			List<TGradeObject> reportObjects,List<TAssignment> workList,Integer tCourseSiteId){
		
		//获取实验作业，测试，报告的权重
				BigDecimal reportWeight=new BigDecimal(0);
				BigDecimal testWeight=new BigDecimal(0);
				BigDecimal worktWeight=new BigDecimal(0);
				List<TWeightSetting> workAndTestAndReport = this.getWorkAndTestAndReport(tCourseSiteId);
				for(TWeightSetting weight:workAndTestAndReport){
					if(weight.getType().equals("expreport")){
						reportWeight=reportWeight.add(weight.getWeight());
					}else if(weight.getType().equals("exptest")){
						testWeight=testWeight.add(weight.getWeight());
					}else if(weight.getType().equals("expwork")){
						worktWeight=worktWeight.add(weight.getWeight());
					}
				}
				
				//学生成绩列表的列表
				List<List<Object>> lists = new ArrayList<List<Object>>();
				//学生成绩列表
				List<Object> list = null;
				//遍历每个学生
				for (TCourseSiteUser tCourseSiteUser : tCourseSiteUsers) {
						//学生成绩列表
						list = new ArrayList<Object>();
						//获取学生编号
						list.add(tCourseSiteUser.getUser().getCname());
						//获取学生姓名
						list.add(tCourseSiteUser.getUser().getUsername());
						//成绩
						String score = "";
						//总成绩
						BigDecimal totalScore = new BigDecimal(0);
						//测试的总成绩
						BigDecimal testScore = new BigDecimal(0);
						//总权重
						BigDecimal totalWeight = new BigDecimal(0);
						
						//获取实验项目的成绩
						if(reportObjects.size()!=0){
							for (TGradeObject tGradeObject : reportObjects) {
								score = "0";
								for (TGradeRecord tGradeRecord : tCourseSiteUser.getUser().getTGradeRecords()) {
									if (tGradeRecord.getTGradeObject().getId().equals(tGradeObject.getId())) {
										score = tGradeRecord.getPoints().toString();
										if (score.contains(".")) {
											score = score.split("\\.")[0];
										}
										if(Integer.parseInt(score)==0){
											score="0";
										}
										totalScore = totalScore.add(tGradeRecord.getPoints().multiply(reportWeight));
									}
									
								}
								totalWeight = totalWeight.add(reportWeight);
								list.add(score);
							}
						}else{
							list.add("0");
						}
						if(testObjects.size()!=0){
							for (TGradeObject tGradeObject : testObjects) {
								score = "0";
								for (TGradeRecord tGradeRecord : tCourseSiteUser.getUser().getTGradeRecords()) {
									if (tGradeRecord.getTGradeObject().getId().equals(tGradeObject.getId())) {
										score = tGradeRecord.getPoints().toString();
										if (score.contains(".")) {
											score = score.split("\\.")[0];
										}
										if(Integer.parseInt(score)==0){
											score="0";
										}
										totalScore = totalScore.add(tGradeRecord.getPoints().multiply(testWeight));
									}
									
								}
								totalWeight = totalWeight.add(testWeight);
								list.add(score);
							}
						}else{
							list.add("0");
						}
						
//						//获取作业的分数
						BigDecimal newWorkScore = this.findStudentAllScoreRecords(tCourseSiteUser, tCourseSiteId, workList);
//						BigDecimal newWorkScore = workScore.divide(worktWeight);
						
						
						//BigDecimal newWorkScore = workScore.divide(worktWeight,1,BigDecimal.ROUND_HALF_UP);
						list.add(newWorkScore);
						totalScore = totalScore.add(newWorkScore.multiply(worktWeight));
//						totalScore = totalScore.add(newWorkScore);
						totalWeight = totalWeight.add(worktWeight);
						//获取总分
						list.add(totalScore.intValue());
//						list.add(totalScore.divide(totalWeight,1,BigDecimal.ROUND_HALF_UP));
						
					lists.add(list);
				}
				return lists;
	}
	
	/*****************************************************
	 * Description:获取实验项目中的预习测试，作业，实验报告的每个学生的成绩
	 *
	 *@auther:李雪腾
	 *@date：2017-8-25
	 *****************************************************/
	@Override
	public BigDecimal findStudentAllScoreRecords(TCourseSiteUser user,Integer tCourseSiteId,List<TAssignment> workList) {
		
		//获取作业，实验报告，实验测试的报告所对应的权重
		//获取传递过来的user 的作业的平均分
		BigDecimal score=new BigDecimal(0);
		for(TAssignment tAssignment:workList){
			List<TAssignmentGrading> gradingList = tAssignmentGradingService.findTAssignmentGradingList(tAssignment.getId(),0, user.getUser());
			for(TAssignmentGrading grading:gradingList){
				if(grading.getUserByStudent()!=null&&grading.getUserByStudent().getUsername().equals(user.getUser().getUsername())){
					BigDecimal finalScore = grading.getFinalScore();
					if(finalScore==null){
						score=score.add(new BigDecimal(0));	
					}else{
						score=score.add(new BigDecimal(finalScore.doubleValue()));	
					}
				}
			}
		}
		if(score.equals(new BigDecimal(0))){
			return score;
		}else{
			BigDecimal divide = score.divide(new BigDecimal(workList.size()),1,BigDecimal.ROUND_HALF_UP);
			return divide;
		}
	}
	
	/**
	 * @功能：实验项目权重设置
	 * @作者：张佳鸣
	 * @时间：2017-12-08
	 */
	public void experimentWeightSetting(HttpServletRequest request, Integer cid){
		String[] tExperimentSkillIds = request.getParameterValues("tExperimentSkillId");
		String[] weights = request.getParameterValues("tExperimentSkillWeight");
		int i = 0;
		// 每个题目设置权重
		for (String tExperimentSkillId : tExperimentSkillIds) {
			TExperimentSkill tExperimentSkill = tExperimentSkillDAO.findTExperimentSkillById(Integer.valueOf(tExperimentSkillId));
			BigDecimal weight = new BigDecimal(Integer.valueOf(weights[i]) / 100.0);
			tExperimentSkill.setWeight(weight);
			tExperimentSkillDAO.store(tExperimentSkill);
			i++;
		}
	}
	/**
	 * @功能：根据课程号和学生查看实验成绩总分列表
	 * @作者：张佳鸣
	 * @时间：2017-12-11
	 */
	public Map<String, BigDecimal> findExperimentScoresByTCourseSiteAndTCourseSiteUsers(
			Integer cid,List<TCourseSiteUser> tCourseSiteUsers){
		
		Map<String, BigDecimal> map= new HashMap<String, BigDecimal>();
		//查看实验总成绩列表
		List<List<Object>> sumLists = tExperimentSkillService.gradeAllExperimentSkill(tCourseSiteUsers,cid);
		for(List<Object> sumList : sumLists) {
			
			String username = sumList.get(1).toString();
			BigDecimal experimentScore = new BigDecimal(sumList.get(sumList.size()-1).toString());
			map.put(username, experimentScore);
		}
		return map;
	}
}