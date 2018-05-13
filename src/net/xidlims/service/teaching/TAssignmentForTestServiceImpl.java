package net.xidlims.service.teaching;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;

import net.xidlims.dao.TAssignmentAnswerAssignDAO;
import net.xidlims.dao.TAssignmentAnswerDAO;
import net.xidlims.dao.TAssignmentControlDAO;
import net.xidlims.dao.TAssignmentDAO;
import net.xidlims.dao.TAssignmentGradingDAO;
import net.xidlims.dao.TAssignmentItemComponentDAO;
import net.xidlims.dao.TAssignmentItemDAO;
import net.xidlims.dao.TAssignmentItemMappingDAO;
import net.xidlims.dao.TAssignmentQuestionpoolDAO;
import net.xidlims.dao.TAssignmentSectionDAO;
import net.xidlims.dao.TCourseSiteDAO;
import net.xidlims.dao.UserDAO;
import net.xidlims.domain.TAssignment;
import net.xidlims.domain.TAssignmentAnswer;
import net.xidlims.domain.TAssignmentAnswerAssign;
import net.xidlims.domain.TAssignmentControl;
import net.xidlims.domain.TAssignmentGrading;
import net.xidlims.domain.TAssignmentItem;
import net.xidlims.domain.TAssignmentItemComponent;
import net.xidlims.domain.TAssignmentItemMapping;
import net.xidlims.domain.TAssignmentQuestionpool;
import net.xidlims.domain.TAssignmentSection;
import net.xidlims.domain.TCourseSite;
import net.xidlims.domain.User;
import net.xidlims.service.common.ShareService;
import net.xidlims.view.ViewTAssignment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("TAssignmentForTestServiceImpl")
public class TAssignmentForTestServiceImpl implements TAssignmentForTestService {

	@Autowired
	private TAssignmentItemDAO tAssignmentItemDAO;
	@Autowired
	private TAssignmentDAO tAssignmentDAO;
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private TCourseSiteDAO tCourseSiteDAO;
	@Autowired
	private TAssignmentAnswerAssignDAO tAssignmentAnswerAssignDAO;
	@Autowired
	private TAssignmentControlDAO tAssignmentControlDAO;
	@Autowired
	private TAssignmentQuestionpoolDAO tAssignmentQuestionpoolDAO;
	@Autowired
	private TAssignmentItemComponentDAO tAssignmentItemComponentDAO;
	@Autowired
	private TAssignmentSectionDAO tAssignmentSectionDAO;
	@Autowired
	private TAssignmentAnswerDAO tAssignmentAnswerDAO;
	@Autowired
	private TAssignmentGradingDAO tAssignmentGradingDAO;
	@Autowired
	private TAssignmentItemMappingDAO tAssignmentItemMappingDAO;
	@Autowired
	private ShareService shareService;
	@PersistenceContext  
    private EntityManager entityManager;
	
	@Override
	public String checkItemQuantity(String[] questionIdArray,
			Integer[] typeArray, Integer[] quantityArray) {
		// TODO Auto-generated method stub
		String result = "";
		String questionIds = "";
		for (String questionId : questionIdArray) {
			questionIds += "'"+questionId+"',";
		}
		questionIds = questionIds.substring(0, questionIds.length()-1);
		String sql="select count(t) from TAssignmentQuestionpool c,TAssignmentItem t where t.id in elements(c.TAssignmentItems) ";
		sql += " and c.questionpoolId in ("+questionIds+") ";
		for (int i=0;i<typeArray.length;i++) {
			String querySql = sql +" and t.type = '"+typeArray[i]+"'";
			int count = ((Long)tAssignmentItemDAO.createQuerySingleResult(querySql).getSingleResult()).intValue();
			if (count<quantityArray[i]) {
				if (typeArray[i]==1) {
					result += "选中题库中多选题";
				}
				if (typeArray[i]==2) {
					result += "选中题库中对错题";
				}
				if (typeArray[i]==4) {
					result += "选中题库中单选题";
				}
				if (typeArray[i]==8) {
					result += "选中题库中填空题";
				}
				result +="仅"+count+"道，数量不足，请重新设定!\n";
				
			}
		}
		return result;
	}

	@Override
	public TAssignment saveTAssignmentForTest(Integer cid,
			TAssignment tAssignment, HttpServletRequest request) throws ParseException {
		// TODO Auto-generated method stub
		String[] questionIds = request.getParameterValues("questionId");
		String[] itemTypes = request.getParameterValues("itemType");
		String[] itemQuantitys = request.getParameterValues("itemQuantity");
		String[] itemScores = request.getParameterValues("itemScore");
		
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
            
			String startdate = request.getParameter("startdate");
			String duedate = request.getParameter("duedate");
			//保存作业的控制表
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			Date beginDate = sdf.parse(startdate);
			Date endDate = sdf.parse(duedate);
			Calendar startCalendar = Calendar.getInstance();
			startCalendar.setTime(beginDate);
			tAssignmentControl.setStartdate(startCalendar);
			Calendar dueCalendar = Calendar.getInstance();
			dueCalendar.setTime(endDate);
			tAssignmentControl.setDuedate(dueCalendar);
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
			Set<TAssignmentQuestionpool> tAssignmentQuestionpools = new HashSet<TAssignmentQuestionpool>();
			for (String questionId : questionIds) {
				tAssignmentQuestionpools.add(tAssignmentQuestionpoolDAO.findTAssignmentQuestionpoolByPrimaryKey(Integer.valueOf(questionId)));
			}
			newTAssignment.setTAssignmentQuestionpools(tAssignmentQuestionpools);
			newTAssignment = tAssignmentDAO.store(newTAssignment);
			for (int i = 0; i < itemScores.length; i++) {
				TAssignmentItemComponent tAssignmentItemComponent = new TAssignmentItemComponent();
				tAssignmentItemComponent.setItemType(Integer.valueOf(itemTypes[i]));
				tAssignmentItemComponent.setItemQuantity(Integer.valueOf(itemQuantitys[i]));
				tAssignmentItemComponent.setItemScore(new BigDecimal(itemScores[i]));
				tAssignmentItemComponent.setTAssignment(newTAssignment);
				tAssignmentItemComponentDAO.store(tAssignmentItemComponent);
			}
		}else{
			TAssignment oldTAssignment = tAssignmentDAO.findTAssignmentById(tAssignment.getId());
			oldTAssignment.setTitle(tAssignment.getTitle());
			oldTAssignment.setContent(tAssignment.getContent());
			
			TAssignmentControl oldTAssignmentControl = tAssignment.getTAssignmentControl();
			
			String startdate = request.getParameter("startdate");
			String duedate = request.getParameter("duedate");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			Date beginDate = sdf.parse(startdate);
			Date endDate = sdf.parse(duedate);
			
			Calendar startCalendar = Calendar.getInstance();
			startCalendar.setTime(beginDate);
			oldTAssignmentControl.setStartdate(startCalendar);
			Calendar dueCalendar = Calendar.getInstance();
			dueCalendar.setTime(endDate);
			oldTAssignmentControl.setDuedate(dueCalendar);
			
			oldTAssignmentControl.setGradeToStudent(tAssignment.getTAssignmentControl().getGradeToStudent());
			oldTAssignmentControl.setGradeToTotalGrade(tAssignment.getTAssignmentControl().getGradeToTotalGrade());
			oldTAssignmentControl.setSubmitType(tAssignment.getTAssignmentControl().getSubmitType());
			oldTAssignmentControl.setTimelimit(tAssignment.getTAssignmentControl().getTimelimit());
			oldTAssignmentControl.setTAssignment(oldTAssignment);
			oldTAssignmentControl = tAssignmentControlDAO.store(oldTAssignmentControl);
			oldTAssignment.setTAssignmentControl(oldTAssignmentControl);
			
			
			//暂时作业设置表的这两个字段值是默认的
			TAssignmentAnswerAssign oldTAssignmentAnswerAssign = tAssignment.getTAssignmentAnswerAssign();
			oldTAssignmentAnswerAssign.setScore(tAssignment.getTAssignmentAnswerAssign().getScore());
			oldTAssignmentAnswerAssign.setUser(userDAO.findUserByPrimaryKey(tAssignment.getTAssignmentAnswerAssign().getUser().getUsername()));
			oldTAssignmentAnswerAssign.setTAssignment(oldTAssignment);
			oldTAssignmentAnswerAssign = tAssignmentAnswerAssignDAO.store(oldTAssignmentAnswerAssign);
			oldTAssignment.setTAssignmentAnswerAssign(oldTAssignmentAnswerAssign);
			
			newTAssignment = tAssignmentDAO.store(oldTAssignment);
			
			//解除试卷原有题型设置
			for (TAssignmentItemComponent tAssignmentItemComponent : newTAssignment.getTAssignmentItemComponents()) {
				tAssignmentItemComponentDAO.remove(tAssignmentItemComponent);
			}
			newTAssignment.setTAssignmentItemComponents(new HashSet<TAssignmentItemComponent>());
			newTAssignment = tAssignmentDAO.store(newTAssignment);
			Set<TAssignmentQuestionpool> tAssignmentQuestionpools = new HashSet<TAssignmentQuestionpool>();
			//解除试卷与已有题库的关联
			newTAssignment.setTAssignmentQuestionpools(tAssignmentQuestionpools);
			tAssignmentDAO.store(newTAssignment);
			newTAssignment = tAssignmentDAO.store(newTAssignment);
			for (String questionId : questionIds) {
				tAssignmentQuestionpools.add(tAssignmentQuestionpoolDAO.findTAssignmentQuestionpoolByPrimaryKey(Integer.valueOf(questionId)));
			}
			newTAssignment.setTAssignmentQuestionpools(tAssignmentQuestionpools);
			newTAssignment = tAssignmentDAO.store(newTAssignment);
			for (int i = 0; i < itemScores.length; i++) {
				TAssignmentItemComponent tAssignmentItemComponent = new TAssignmentItemComponent();
				tAssignmentItemComponent.setItemType(Integer.valueOf(itemTypes[i]));
				tAssignmentItemComponent.setItemQuantity(Integer.valueOf(itemQuantitys[i]));
				tAssignmentItemComponent.setItemScore(new BigDecimal(itemScores[i]));
				tAssignmentItemComponent.setTAssignment(newTAssignment);
				tAssignmentItemComponentDAO.store(tAssignmentItemComponent);
			}
		}
		return newTAssignment;
	}

	@Override
	public List<ViewTAssignment> findTestList(User nowUser, Integer cid, String type, 
			int status, String chapterOrLesson) {
		// TODO Auto-generated method stub
		List<ViewTAssignment> viewTAssignments = null;
		StringBuffer sql = new StringBuffer("from TAssignment c where 1=1 ");
		if(chapterOrLesson.equals("chapter")){
			sql.append(" and c.wkFolder.WkChapter.TCourseSite.id = '" + cid + "' and c.type like '" + type + "' ");
		}else if(chapterOrLesson.equals("lesson")){
			sql.append(" and c.wkFolder.WkLesson.wkChapter.TCourseSite.id = '" + cid + "' and c.type like '" + type + "' ");
		}
		
		
		
		int flag = 2;//判断当前登陆人员的权限
		TCourseSite tCourseSite = tCourseSiteDAO.findTCourseSiteById(cid);
		
		if(nowUser!=null&&(nowUser.getAuthorities().toString().contains("EXCENTERDIRECTOR")
		        ||nowUser.getAuthorities().toString().contains("LABMANAGER")
		        ||nowUser.getAuthorities().toString().contains("SUPERADMIN")
		        ||nowUser.getAuthorities().toString().contains("DEAN")
		        ||nowUser.getAuthorities().toString().contains("SCHOOLLEADER")
		        ||nowUser.getAuthorities().toString().contains("ASSOCIATEDEAN")
		        ||nowUser.getAuthorities().toString().contains("COURSETEACHER")
		        ||nowUser.getAuthorities().toString().contains("EDUCATIONADMIN")
		        ||nowUser.getAuthorities().toString().contains("LABCENTERMANAGER"))){
			flag = 1;//实验中心主任   或者实验室管理员   或者超级管理员   或者教务处   或者校领导   或者教学副院长   或者课程负责教师   或者教务管理员   或者实验中心管理员   为教师权限
		}else if (nowUser.getAuthorities().toString().contains("TEACHER")) {//教师权限
			if (tCourseSite.getUserByCreatedBy().getUsername().equals(nowUser.getUsername())) {
				flag = 1;//如果是当前登陆人开的课程，则是老师身份
			}else {
				flag = 0;//如果不是当前登陆人开的课程，则在该课程中是学生身份
			}
		}else {//学生身份
			flag = 0;
		}
		if(flag==1||tCourseSite.getTCourseSiteUsers().toString().contains(nowUser.getUsername())){//如果是老师或该门课下的学生包含当前登陆人，则进行查询
			if (flag == 1) {//如果是教师身份，则根据状态查询
				sql.append(" and c.user.username = '"+nowUser.getUsername()+"' ");
				if (status!=-1) {
					sql.append(" and c.status = '"+status+"'");
				}
			}
			if (flag == 0) {//学生身份则查看已发布的测试
				sql.append(" and c.TAssignmentControl.startdate < now() and c.status = '1'");
			}
			
			List<TAssignment> tests = tAssignmentDAO.executeQuery(sql.toString(), 0, -1);
			viewTAssignments = new ArrayList<ViewTAssignment>();
			for (TAssignment tAssignment : tests) {
				ViewTAssignment viewTAssignment = new ViewTAssignment();
				viewTAssignment.setId(tAssignment.getId());
				viewTAssignment.setCreatedTime(tAssignment.getCreatedTime());
				//viewTAssignment.setDescription(tAssignment.getDescription());
				viewTAssignment.setStatus(tAssignment.getStatus());
				viewTAssignment.setTAssignmentAnswerAssign(tAssignment.getTAssignmentAnswerAssign());
				viewTAssignment.setTAssignmentControl(tAssignment.getTAssignmentControl());
				viewTAssignment.setTAssignmentSections(tAssignment.getTAssignmentSections());
				//viewTAssignment.setTCourseSite(tAssignment.getTCourseSite());
				viewTAssignment.setTitle(tAssignment.getTitle());
				viewTAssignment.setType(tAssignment.getType());
				Set<TAssignmentGrading> assignmentGradings = tAssignment.getTAssignmentGradings();
				int count = 0;
				for (TAssignmentGrading tAssignmentGrading : assignmentGradings) {
					if (tAssignmentGrading.getUserByStudent().getAuthorities().toString().contains("STUDENT")&&
						!tAssignmentGrading.getUserByStudent().getAuthorities().toString().contains("TEACHER")&&
						tAssignmentGrading.getSubmitTime()>0) {//如果有提交的记录，则提交的学生数量加一
						count++;
					}
				}
				if (flag ==0 ) {
					String sql1="from TAssignmentGrading c where c.TAssignment.id='"+tAssignment.getId()+"' and c.userByStudent.username='"+nowUser.getUsername()+"' and c.submitTime>0 order by c.submitdate desc";
					List<TAssignmentGrading> Grandings=tAssignmentGradingDAO.executeQuery(sql1, 0,-1);
					if (Grandings.size()>0) {
						viewTAssignment.setSubmitTimeForStudent(Grandings.get(0).getSubmitTime());
						viewTAssignment.setScoreForStudent(Grandings.get(0).getFinalScore().toString());
					}else {
						viewTAssignment.setSubmitTimeForStudent(0);
					}
				}
				/*if (flag==0) {
					if (tAssignment.getTAssignmentGradings().size()!=0) {
						for (TAssignmentGrading tAssignmentGrading : tAssignment.getTAssignmentGradings()) {
							if (tAssignmentGrading.getUserByStudent().getUsername().equals(nowUser.getUsername())) {
								viewTAssignment.setSubmitTimeForStudent(tAssignmentGrading.getSubmitTime());
							}
						}
						
					}
					if (viewTAssignment.getSubmitTimeForStudent()==null) {
						viewTAssignment.setSubmitTimeForStudent(0);
					}
				}*/
				viewTAssignment.settAssignGradeSubmitCount(count);
				viewTAssignments.add(viewTAssignment);
			}
		}
		return viewTAssignments;
	}

	@Override
	public void deleteTestById(TAssignment test) {
		// TODO Auto-generated method stub
		tAssignmentDAO.remove(test);
	}

	@Override
	public TAssignment findTestByUserAndTest(User nowUser,
			TAssignment parentTest) {
		// TODO Auto-generated method stub
		TAssignment test = new TAssignment();
		if (nowUser.getAuthorities().toString().contains("TEACHER")&&parentTest.getUser().getUsername().equals(nowUser.getUsername())) {//教师身份
			test = parentTest;
		}
		if (!(nowUser.getAuthorities().toString().contains("TEACHER")&&parentTest.getUser().getUsername().equals(nowUser.getUsername()))){//学生身份
			String sql = "from TAssignment c where c.testParentId = '"+parentTest.getId()+"' and c.user.username = '"+nowUser.getUsername()+"'";
			List<TAssignment> tAssignments = tAssignmentDAO.executeQuery(sql, 0, -1);
			if (tAssignments.size()>0) {//如果已经存在则查询
				test = tAssignments.get(0);
			}else{
				test.setUser(nowUser);
				test.setTestParentId(parentTest.getId());
				test.setCreatedTime(Calendar.getInstance());
				test = tAssignmentDAO.store(test);
			}
		}
		return test;
	}
	
	@Override
	public TAssignment findTestByTeacherAndTest(User nowUser,
			TAssignment parentTest) {
		// TODO Auto-generated method stub
		TAssignment test = new TAssignment();
		if (!(nowUser.getAuthorities().toString().contains("TEACHER")&&parentTest.getUser().getUsername().equals(nowUser.getUsername()))) {//学生身份
			return test;
		}
		if (nowUser.getAuthorities().toString().contains("TEACHER")&&parentTest.getUser().getUsername().equals(nowUser.getUsername())){//教师身份
			String sql = "from TAssignment c where c.testParentId = '"+parentTest.getId()+"' and c.user.username = '"+nowUser.getUsername()+"'";
			List<TAssignment> tAssignments = tAssignmentDAO.executeQuery(sql, 0, -1);
			if (tAssignments.size()>0) {//如果已经存在则查询
				test = tAssignments.get(0);
			}else{
				test.setUser(nowUser);
				test.setTestParentId(parentTest.getId());
				test.setCreatedTime(Calendar.getInstance());
				test = tAssignmentDAO.store(test);
			}
		}
		return test;
	}

	@Transactional
	@Override
	public TAssignment createRandomTest(TAssignment test, TAssignment parentTest) {
		// TODO Auto-generated method stub
		if (test.getTAssignmentSections().size()>0&&test.getTAssignmentSections().iterator().next().getTAssignmentItems().size()>0) {//该试卷已出
			return test;
		}else {//若没有则新增
			String questionIds = "";
			for (TAssignmentQuestionpool questionpool : parentTest.getTAssignmentQuestionpools()) {
				questionIds += "'"+questionpool.getQuestionpoolId()+"',";
			}
			questionIds = questionIds.substring(0, questionIds.length()-1);
			String sql="select t from TAssignmentQuestionpool c,TAssignmentItem t where t.id in elements(c.TAssignmentItems) ";
			sql += " and c.questionpoolId in ("+questionIds+") ";
			Integer i = 1;
			//Set<TAssignmentSection> tAssignmentSections = new HashSet<TAssignmentSection>();
			for (TAssignmentItemComponent tAssignmentItemComponent : parentTest.getTAssignmentItemComponents()) {
				//根据试卷组成新增大项
				/*TAssignmentSection tAssignmentSection = new TAssignmentSection();
				tAssignmentSection.setCreatedTime(Calendar.getInstance());
				String description = null;
				if (tAssignmentItemComponent.getItemType()==1) {
					description = "多选题";
				}
				if (tAssignmentItemComponent.getItemType()==2) {
					description = "对错题";
				}
				if (tAssignmentItemComponent.getItemType()==4) {
					description = "单选题";
				}
				if (tAssignmentItemComponent.getItemType()==8) {
					description = "填空题";
				}
				tAssignmentSection.setDescription(description);
				tAssignmentSection.setTAssignment(test);
				tAssignmentSection.setSequence(i);
				tAssignmentSection = tAssignmentSectionDAO.store(tAssignmentSection);
				String queryString = sql + " and t.type = '"+tAssignmentItemComponent.getItemType()+"'";
				List<TAssignmentItem> tAssignmentItems = tAssignmentItemDAO.executeQuery(queryString, 0, -1);
				Set<Integer> set = getRandomList(tAssignmentItemComponent.getItemQuantity(), tAssignmentItems.size());
				//Set<TAssignmentItem> assignmentItems = new HashSet<TAssignmentItem>();
				for (Integer count : set) {
					copyTAssignmentItem(tAssignmentItems.get(count), tAssignmentSection, tAssignmentItemComponent.getItemScore());
				}*/
				TAssignmentSection tAssignmentSection = new TAssignmentSection();
				tAssignmentSection.setCreatedTime(Calendar.getInstance());
				String description = null;
				if (tAssignmentItemComponent.getItemType()==1) {
					description = "多选题";
				}
				if (tAssignmentItemComponent.getItemType()==2) {
					description = "对错题";
				}
				if (tAssignmentItemComponent.getItemType()==4) {
					description = "单选题";
				}
				if (tAssignmentItemComponent.getItemType()==8) {
					description = "填空题";
				}
				tAssignmentSection.setDescription(description);
				tAssignmentSection.setTAssignment(test);
				tAssignmentSection.setSequence(i);
				tAssignmentSection = tAssignmentSectionDAO.store(tAssignmentSection);
				String queryString = sql + " and t.type = '"+tAssignmentItemComponent.getItemType()+"'";
				List<TAssignmentItem> tAssignmentItems = tAssignmentItemDAO.executeQuery(queryString, 0, -1);
				Set<Integer> set = getRandomList(tAssignmentItemComponent.getItemQuantity(), tAssignmentItems.size());
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String currentTime = sdf.format(Calendar.getInstance().getTime());
				String username = shareService.getUser().getUsername();
				String score = tAssignmentItemComponent.getItemScore().toString();
				String insertsql = "insert into `t_assignment_item` (`section_id`, `sequence`, `description`, `descriptionTemp`, `score`, `created_by`, `created_time`, `type`, `item_parent`) ";
				/*for (Integer count : set) {
					TAssignmentItem tAssignmentItem = tAssignmentItems.get(count);
					insertsql +="('"+tAssignmentSection.getId()+"',"+tAssignmentItem.getSequence()+",'"+tAssignmentItem.getDescription()+"',"+(tAssignmentItem.getDescriptionTemp()==null?null:("'"+tAssignmentItem.getDescriptionTemp()+"'"))+",'"+score+"','"+username+"','"+currentTime+"','"+tAssignmentItem.getType()+"','"+tAssignmentItem.getId()+"'),";
					
				}*/
				String ids = "(";
				for (Integer count : set) {
					ids +="'"+tAssignmentItems.get(count).getId()+"',";
				}
				ids = ids.substring(0,ids.length()-1)+")";
				insertsql += "select '"+tAssignmentSection.getId()+"', `sequence`, `description`, `descriptionTemp`, '"+score+"', '"+username+"', '"+currentTime+"', `type`, `id` from t_assignment_item where id in "+ids;
				Query query = entityManager.createNativeQuery(insertsql);
				
				int result =  query.executeUpdate();
				queryString = "select id,item_parent from `t_assignment_item` where section_id = '"+tAssignmentSection.getId()+"' and created_by = '"+username+"' and item_parent in "+ids;
				insertsql = "insert into t_assignment_answer (`item_id`,`text`,`label`,`iscorrect`) ";
				insertsql +="select (select c.id from `t_assignment_item` c where c.section_id ='"+tAssignmentSection.getId()+"' and c.item_parent = item_id and c.created_by = '"+username+"'),`text`,`label`,`iscorrect` from `t_assignment_answer` where item_id in "+ids;
//				TAssignmentSection ttAssignmentSection = tAssignmentSectionDAO.findTAssignmentSectionById(tAssignmentSection.getId());
				entityManager.createNativeQuery(insertsql).executeUpdate();
				System.out.println(result);
			}
		}
		return test;
	}
	
	//根据一个大数和一个小数生成set集合
	private Set<Integer> getRandomList(Integer min,Integer max){
		Set<Integer> set = new HashSet<Integer>();
		String s = "";
		while(set.size()<min){
			Integer i = new Random().nextInt(max);
			if(!s.contains(i.toString())){
				set.add(i);
			}
			s += i.toString()+",";
		}
		
		return set;
	}
	/*************************************************************************************
	 * @內容：保存学生答题记录
	 * @作者： 魏誠
	 * @日期：2015-08-20
	 *************************************************************************************/
	@Transactional
	public BigDecimal saveTAssignmentItemMapping(HttpServletRequest request,int assignmentId,Integer submitTime) {
		//初始化当前时间
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar calendar = Calendar.getInstance();
		String currentTime = sdf.format(calendar.getTime());
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
		Integer tAssignmentId = tAssignment.getId();
		Integer itemId = 0;
		String username = shareService.getUser().getUsername();
		String answerText = "";
		String score;
		List<String> sqlList = null;
		BigDecimal totalScore = new BigDecimal(0);
		String basesql = "INSERT INTO `t_assignment_item_mapping` (`assignment_id`, `item_id`, `answer_id`, `answer_text`, `student`, `submit_date`, `submitTime`,`autoscore`) VALUES ";
		String sql = "";
		for (TAssignmentSection tAssignmentSection : tAssignment.getTAssignmentSections()) {
			for (TAssignmentItem tAssignmentItem : tAssignmentSection.getTAssignmentItems()) {
				itemId = tAssignmentItem.getId();
				score = "0";
				int count = 0;
				List<String> answersArray = new ArrayList<String>();
				sqlList = new ArrayList<String>();
				String[] answers = request.getParameterValues("answers" + tAssignmentItem.getId());
				String[] answertexts = request.getParameterValues("answertexts" + tAssignmentItem.getId());
				
				
				if(answers != null && answers.length>0){
					for(int i=0;i<answers.length;i++){
						
						// 对错题判断和单选题判断
						if (tAssignmentItem.getType() == 2 ||tAssignmentItem.getType() == 4) {
							TAssignmentAnswer tAssignmentAnswer = tAssignmentAnswerDAO.findTAssignmentAnswerById(Integer.valueOf(answers[i]));
							if (tAssignmentAnswer.getIscorrect()==1) {
								score = tAssignmentItem.getScore().toString();
								totalScore = totalScore.add(tAssignmentItem.getScore());
							}
						}
						
						// 填空题题判断
						if (tAssignmentItem.getType() == 8) {
							if (answertexts[i]!=null&&answertexts[i].trim().equals(tAssignmentAnswerDAO.findTAssignmentAnswerById(Integer.valueOf(answers[i])).getText())) {
								count++;
							}
						}
						
						if (answertexts!=null) {
							answerText = answertexts[i].trim();
						}
						//sql += "('"+tAssignmentId+"','"+itemId+"','"+answers[i]+"','"+answerText+"','"+username+"','"+currentTime+"','"+submitTime+"','"+score+"'),";
						sqlList.add("('"+tAssignmentId+"','"+itemId+"','"+answers[i]+"','"+answerText+"','"+username+"','"+currentTime+"','"+submitTime+"',");
					}
					
				}
				if (tAssignmentItem.getType() == 8) {
					if (count!=0) {//如果有答对的题，计入得分
						BigDecimal itemScore = tAssignmentItem.getScore();
						//以小题得分除以总空格数并乘以答对的空格数计算得分
						totalScore = totalScore.add(itemScore.multiply(new BigDecimal(count)).divide(new BigDecimal(answers.length)));
						
						score = itemScore.multiply(new BigDecimal(count)).divide(new BigDecimal(answers.length)).toString();
					}
				}
				
				if (tAssignmentItem.getType() == 1) {
					for (TAssignmentAnswer tAssignmentAnswer : tAssignmentItem.getTAssignmentAnswers()) {
						if (tAssignmentAnswer.getIscorrect()==1) {
							answersArray.add(tAssignmentAnswer.getId().toString());
						}
					}
					count = compare(answers, answersArray);
					if (count != 0) {
						score = new BigDecimal(count/2.0).multiply(tAssignmentItem.getScore()).toString();
						totalScore = totalScore.add(new BigDecimal(count/2.0).multiply(tAssignmentItem.getScore()));
					}	
					
				}
				for (String string : sqlList) {
					sql += string +"'"+score+"'),";
				}
				
			}
		}
		if (!"".equals(sql)) {
			sql = basesql + sql.substring(0, sql.length()-1);
			
			entityManager.createNativeQuery(sql).executeUpdate();
		}
		
		return totalScore;
	}
	/*************************************************************************************
	 * @內容：保存学生答题
	 * @作者：黄崔俊
	 * @日期：2015-12-3 23:04:22
	 *************************************************************************************/
	public TAssignmentGrading saveTAssignmentGradeForTest(BigDecimal totalScore,int assignmentId, Integer submitTime) {
		
		TAssignment tAssignment = tAssignmentDAO.findTAssignmentById(assignmentId);
		TAssignment parentTAssignment = tAssignmentDAO.findTAssignmentById(tAssignment.getTestParentId());
		TAssignmentGrading tAssignmentGrade = new TAssignmentGrading();
		if(submitTime != 0){//若是提交，查询以前的最大提交数并加一,没有则为第一次提交
			String sql  = "from TAssignmentGrading c where c.TAssignment.id = '"+parentTAssignment.getId()+"' and c.userByStudent.username = '"+shareService.getUser().getUsername()+"'";
			List<TAssignmentGrading> tAssignmentGradings = tAssignmentGradingDAO.executeQuery(sql, 0, -1);
			
			if(tAssignmentGradings.size()>0){
				submitTime = tAssignmentGradings.get(0).getSubmitTime()+1;
				tAssignmentGrade = tAssignmentGradings.get(0);
			}
		}
		tAssignmentGrade.setFinalScore(totalScore);
		
		//考试的试题对象
		tAssignmentGrade.setTAssignment(parentTAssignment);
		//改变提交次数
		tAssignmentGrade.setSubmitTime(submitTime);
		//考试的学生
		tAssignmentGrade.setUserByStudent(shareService.getUser());
		
		Integer islate = 0;//0表示正常提交
		Calendar submitDate = Calendar.getInstance();
		Calendar dueDate = parentTAssignment.getTAssignmentControl().getDuedate();
		if(submitDate.after(dueDate)){
			islate = 1;//1表示迟交
		}
		tAssignmentGrade.setIslate(islate);
		tAssignmentGrade.setSubmitdate(submitDate);
		// 提交后返回作业列表
		return tAssignmentGradingDAO.store(tAssignmentGrade);
		
	}
	
	private int compare(String[] array,List<String> list){
		int result = 2; 
		if (array==null||array.length==0||array.length>list.size()) {
			result = 0;
			
		}else {
			if (array.length==list.size()) {
				for (String string : array) {
					if (!list.contains(string)) {
						result = 0;
						break;
					}
				}
			}else {
				result = 1;
				for (String string : array) {
					if (!list.contains(string)) {
						result = 0;
						break;
					}
				}
			}
		}
		
		return result;
	}

	@Override
	public TAssignmentGrading findTAssignmentGradingByTestIdAndUser(
			Integer testId, User nowUser) {
		// TODO Auto-generated method stub
		String sql = "from TAssignmentGrading c where c.TAssignment.id = '"+testId+"' and c.userByStudent.username = '"+nowUser.getUsername()+"'";
		List<TAssignmentGrading> tAssignmentGradings = tAssignmentGradingDAO.executeQuery(sql, 0, -1);
		TAssignmentGrading tAssignmentGrading  = tAssignmentGradings.get(0);
		return tAssignmentGrading;
	}

	@Override
	public List<TAssignmentItemMapping> findTAssignmentItemMappingsByTAssignmentGrading(
			TAssignmentGrading tAssignmentGrading) {
		// TODO Auto-generated method stub
		TAssignment parentTAssignment = tAssignmentGrading.getTAssignment();
		String sql = "from TAssignment c where c.testParentId = '"+parentTAssignment.getId()+"' and c.user.username = '"+ tAssignmentGrading.getUserByStudent().getUsername() +"'";
		List<TAssignment> tAssignments = tAssignmentDAO.executeQuery(sql, 0, -1);
		TAssignment tAssignment = tAssignments.get(0);
		
		//tAssignmentGrading.getSubmitTime()获取最后一次的提交次数
		sql = "from TAssignmentItemMapping c where c.userByStudent.username like '" + tAssignmentGrading.getUserByStudent().getUsername() + "' and c.submitTime='"+tAssignmentGrading.getSubmitTime()+"' and c.TAssignment.id = '" + tAssignment.getId() +"'";
		List<TAssignmentItemMapping> itemMappings = tAssignmentItemMappingDAO.executeQuery(sql, 0, -1);
		return itemMappings;
	}

	/*@Transactional
	@Override
	public BigDecimal saveChangeTAssignmentItemMapping(Integer assignmentId) {
		// TODO Auto-generated method stub
		// 获取试卷对象
		TAssignment tAssignment = tAssignmentDAO.findTAssignmentById(assignmentId);
		
		TAssignment parentTAssignment = tAssignmentDAO.findTAssignmentById(tAssignment.getTestParentId());
		//初始化当前时间
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Calendar calendar = Calendar.getInstance();
				String currentTime = sdf.format(calendar.getTime());
				

				
				Integer tAssignmentId = tAssignment.getId();
				Integer itemId = 0;
				String username = tAssignment.getUser().getUsername();
				String answerText = null;
				String score;
				List<String> sqlList = null;
				BigDecimal totalScore = new BigDecimal(0);
				String basesql = "INSERT INTO `t_assignment_item_mapping` (`assignment_id`, `item_id`, `answer_id`, `answer_text`, `student`, `submit_date`, `submitTime`,`autoscore`) VALUES ";
				String sql = "";
				for (TAssignmentSection tAssignmentSection : tAssignment.getTAssignmentSections()) {
					for (TAssignmentItem tAssignmentItem : tAssignmentSection.getTAssignmentItems()) {
						itemId = tAssignmentItem.getId();
						score = "0";
						int count = 0;
						List<String> answersArray = new ArrayList<String>();
						sqlList = new ArrayList<String>();
						String maxCountSql = "select count(t) from TAssignmentGrading t where t.TAssignment.id = '"+parentTAssignment.getId()+"' and t.userByStudent.username = '"+username+"'";
						int maxCount = ((Long)tAssignmentGradingDAO.createQuerySingleResult(maxCountSql).getSingleResult()).intValue();
						String querySql = "from TAssignmentItemMapping c where c.TAssignment.id = '"+tAssignmentId+"' and c.TAssignmentItem.id = '"+tAssignmentItem.getId()+"' and c.userByStudent.username = '"+username+"' and c.submitTime = '1'";
						List<TAssignmentItemMapping> tAssignmentItemMappings = tAssignmentItemMappingDAO.executeQuery(querySql);
						String[] answers = null;
						if (tAssignmentItemMappings.size()>0) {
							answers = new String[tAssignmentItemMappings.size()];
							int arrayCount = 0;
							for (TAssignmentItemMapping tAssignmentItemMapping : tAssignmentItemMappings) {
								answers[arrayCount] = tAssignmentItemMapping.getTAssignmentAnswer().getId().toString();
								arrayCount++;
							}
						}
						
						
						
						if(answers != null && answers.length>0){
							for(int i=0;i<answers.length;i++){
								
								// 对错题判断和单选题判断
								if (tAssignmentItem.getType() == 2 ||tAssignmentItem.getType() == 4) {
									TAssignmentAnswer tAssignmentAnswer = tAssignmentAnswerDAO.findTAssignmentAnswerById(Integer.valueOf(answers[i]));
									if (tAssignmentAnswer.getIscorrect()==1) {
										score = tAssignmentItem.getScore().toString();
										totalScore = totalScore.add(tAssignmentItem.getScore());
									}
								}
							
								//sql += "('"+tAssignmentId+"','"+itemId+"','"+answers[i]+"','"+answerText+"','"+username+"','"+currentTime+"','"+submitTime+"','"+score+"'),";
								sqlList.add("('"+tAssignmentId+"','"+itemId+"','"+answers[i]+"',"+answerText+",'"+username+"','"+currentTime+"','400',");
							}
							
						}
						
						
						if (tAssignmentItem.getType() == 1) {
							for (TAssignmentAnswer tAssignmentAnswer : tAssignmentItem.getTAssignmentAnswers()) {
								if (tAssignmentAnswer.getIscorrect()==1) {
									answersArray.add(tAssignmentAnswer.getId().toString());
								}
							}
							count = compare(answers, answersArray);
							if (count != 0) {
								score = new BigDecimal(count/2.0).multiply(tAssignmentItem.getScore()).toString();
								totalScore = totalScore.add(new BigDecimal(count/2.0).multiply(tAssignmentItem.getScore()));
							}	
							
						}
						for (String string : sqlList) {
							sql += string +"'"+score+"'),";
						}
						
					}
				}
				if (!"".equals(sql)) {
					sql = basesql + sql.substring(0, sql.length()-1);
					
					entityManager.createNativeQuery(sql).executeUpdate();
				}
				
				return totalScore;
	}*/

	/*@Override
	public TAssignmentGrading saveChangeTAssignmentGradeForTest(
			BigDecimal totalScore, Integer assignmentId) {
		// TODO Auto-generated method stub
		TAssignment tAssignment = tAssignmentDAO.findTAssignmentById(assignmentId);
		TAssignment parentTAssignment = tAssignmentDAO.findTAssignmentById(tAssignment.getTestParentId());
		TAssignmentGrading tAssignmentGrade = new TAssignmentGrading();
			String sql  = "from TAssignmentGrading c where c.TAssignment.id = '"+parentTAssignment.getId()+"' and c.userByStudent.username = '"+tAssignment.getUser().getUsername()+"'";
			List<TAssignmentGrading> tAssignmentGradings = tAssignmentGradingDAO.executeQuery(sql, 0, -1);
			
			if(tAssignmentGradings.size()>0){
				tAssignmentGrade = tAssignmentGradings.get(0);
			}else {
				Calendar submitDate = Calendar.getInstance();
				tAssignmentGrade.setSubmitdate(submitDate);
			}
		tAssignmentGrade.setFinalScore(totalScore);
		
		//考试的试题对象
		tAssignmentGrade.setTAssignment(parentTAssignment);
		//改变提交次数
		tAssignmentGrade.setSubmitTime(400);
		//考试的学生
		tAssignmentGrade.setUserByStudent(tAssignment.getUser());
		
		Integer islate = 0;//0表示正常提交
		
		Calendar dueDate = parentTAssignment.getTAssignmentControl().getDuedate();
		if(submitDate.after(dueDate)){
			islate = 1;//1表示迟交
		}
		tAssignmentGrade.setIslate(islate);
		
		// 提交后返回作业列表
		return tAssignmentGradingDAO.store(tAssignmentGrade);
	}*/
}
