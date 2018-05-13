package net.xidlims.service.tcoursesite;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.xidlims.dao.TAssignmentAnswerAssignDAO;
import net.xidlims.dao.TAssignmentControlDAO;
import net.xidlims.dao.TAssignmentDAO;
import net.xidlims.dao.TAssignmentGradingDAO;
import net.xidlims.dao.TAssignmentItemComponentDAO;
import net.xidlims.dao.TAssignmentQuestionpoolDAO;
import net.xidlims.dao.TCourseSiteDAO;
import net.xidlims.dao.TCourseSiteUserDAO;
import net.xidlims.dao.UserDAO;
import net.xidlims.domain.TAssignment;
import net.xidlims.domain.TAssignmentAnswerAssign;
import net.xidlims.domain.TAssignmentControl;
import net.xidlims.domain.TAssignmentGrading;
import net.xidlims.domain.TAssignmentItemComponent;
import net.xidlims.domain.TAssignmentQuestionpool;
import net.xidlims.domain.TCourseSite;
import net.xidlims.domain.TCourseSiteUser;
import net.xidlims.domain.User;
import net.xidlims.domain.WkFolder;
import net.xidlims.service.common.ShareService;
import net.xidlims.view.ViewTAssignment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.inject.persist.Transactional;

@Service("WkTAssignmentService")
public class TAssignmentServiceImpl implements TAssignmentService {
	@Autowired
	private TAssignmentAnswerAssignDAO tAssignmentAnswerAssignDAO;
	@Autowired
	private TAssignmentDAO tAssignmentDAO;
	@Autowired
	private ShareService shareService;
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private TCourseSiteDAO tCourseSiteDAO;
	@Autowired
	private TCourseSiteUserDAO tCourseSiteUserDAO;
	@Autowired
	private TAssignmentControlDAO tAssignmentControlDAO;
	@Autowired
	private TAssignmentGradingDAO tAssignmentGradingDAO;
	@Autowired
	private WkFolderService wkFolderService;
	@Autowired
	private TAssignmentQuestionpoolDAO tAssignmentQuestionpoolDAO;
	@Autowired
	private TAssignmentItemComponentDAO tAssignmentItemComponentDAO;
	@Autowired
	private WkUploadService wkUploadService;

	
	/**************************************************************************
	 * Description:根据id查询作业
	 * 
	 * @author：黄崔俊
	 * @date ：2015-8-12
	 **************************************************************************/
	@Override
	public TAssignment findTAssignmentById(int assignId) {
		// TODO Auto-generated method stub
		TAssignment tAssignment = tAssignmentDAO.findTAssignmentById(assignId);
		return tAssignment;
	}
	
	/**************************************************************************
	 * Description:保存作业
	 * 
	 * @author： 裴继超
	 * @date ：2016-5-30
	 **************************************************************************/
	@Transactional
	public TAssignment saveTAssignment(TAssignment tAssignment,HttpServletRequest request) throws ParseException {
		// TODO Auto-generated method stub
		//当前登陆用户
		User nowUser = shareService.getUserDetail();
		TAssignment newTAssignment = null;
		if (tAssignment.getId()==null) {//没有id表示是新增
			//先保存作业所属文件夹
			WkFolder wkFolder = new WkFolder();
			wkFolder.setName(tAssignment.getTitle());
			wkFolder.setType(4);
			wkFolder.setUser(nowUser);
			String belongTo = "";
			if(tAssignment.getWkFolder().getWkLesson() != null && tAssignment.getWkFolder().getWkLesson().getId()!=-1){
				wkFolder.setWkChapter(null);
				wkFolder.setWkLesson(tAssignment.getWkFolder().getWkLesson());
				belongTo = "lesson"+tAssignment.getWkFolder().getWkLesson().getId();
			}else{
				wkFolder.setWkChapter(tAssignment.getWkFolder().getWkChapter());
				belongTo = "chapter"+tAssignment.getWkFolder().getWkChapter().getId();
			}
			wkFolder = wkFolderService.saveWkFolder(wkFolder,request);
			/*
			 * 保存TAssignment需先保存一对一子表，然后设置为空，保存，然后再重新设置一对一子表，再保存。
			*/
			//设置作业的课程站点
			//tAssignment.setTCourseSite(tCourseSiteDAO.findTCourseSiteById(cid));
			
			//查询当前
			//记录作业的控制表
			TAssignmentControl tAssignmentControl = tAssignment.getTAssignmentControl();
			//记录作业的设置表
			TAssignmentAnswerAssign tAssignmentAssign = tAssignment.getTAssignmentAnswerAssign();

			tAssignment.setTAssignmentControl(null);
			tAssignment.setTAssignmentAnswerAssign(null);
			tAssignment.setWkFolder(wkFolder);
			
			tAssignment = tAssignmentDAO.store(tAssignment);
            //设置开始时间和结束时间
			String startdate = request.getParameter("startdateAssignment");
			String duedate = request.getParameter("duedateAssignment");
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
			tAssignment.setSequence(tAssignment.getId());//给作业排序，初始值和id保持一致
			//tAssignmentControlDAO.flush();
			//保存附件
			String upload = request.getParameter("attachment");
			String resource = request.getParameter("radioname");
			String name = null;
			if(upload !=null && !upload.isEmpty()){
				name = wkUploadService.findUploadByPrimaryKey(Integer.parseInt(upload)).getUrl();
			}else if(resource != null && !resource.isEmpty()){
				name = wkUploadService.findUploadByPrimaryKey(Integer.parseInt(resource)).getUrl();
			}
			tAssignment.setTeacherFilePath(name);
			newTAssignment = tAssignmentDAO.store(tAssignment);
			
		}else{
			TAssignment oldTAssignment = tAssignmentDAO.findTAssignmentByPrimaryKey(tAssignment.getId());
			oldTAssignment.setTitle(tAssignment.getTitle());
			oldTAssignment.setContent(tAssignment.getContent());
			oldTAssignment.setStatus(tAssignment.getStatus());
			
			TAssignmentControl oldTAssignmentControl = tAssignment.getTAssignmentControl();
			//设置开始时间和结束时间
			String startdate = request.getParameter("startdateAssignment");
			String duedate = request.getParameter("duedateAssignment");
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
			
			//获取当前时间 
			Calendar now=Calendar.getInstance();
			WkFolder wkFolder = new WkFolder();
			wkFolder.setId(tAssignment.getWkFolder().getId());
			wkFolder.setName(tAssignment.getTitle());
			wkFolder.setType(4);
			wkFolder.setUser(nowUser);
			wkFolder.setCreateTime(oldTAssignment.getWkFolder().getCreateTime());
			wkFolder.setUpdateTime(now);
			String belongTo = "";
			if(tAssignment.getWkFolder() != null && tAssignment.getWkFolder().getWkLesson() != null &&
					tAssignment.getWkFolder().getWkLesson().getId()!=null&&tAssignment.getWkFolder().getWkLesson().getId()!=-1){
				wkFolder.setWkChapter(null);
				wkFolder.setWkLesson(tAssignment.getWkFolder().getWkLesson());
				belongTo = "lesson"+tAssignment.getWkFolder().getWkLesson().getId();
			}else{
				wkFolder.setWkLesson(null);
				wkFolder.setWkChapter(tAssignment.getWkFolder().getWkChapter());
				belongTo = "chapter"+tAssignment.getWkFolder().getWkChapter().getId();
			}
			
			//附件上传
			String upload = request.getParameter("attachment");
			String resource = request.getParameter("radioname");
			String name = null;
			if(upload !=null && !upload.isEmpty()){
				name = wkUploadService.findUploadByPrimaryKey(Integer.parseInt(upload)).getUrl();
			}else if(resource != null && !resource.isEmpty()){
				name = wkUploadService.findUploadByPrimaryKey(Integer.parseInt(resource)).getUrl();
			}
			//修改时如果没有上传附件，则沿用以前的附件路径
			if(name!=null){
				oldTAssignment.setTeacherFilePath(name);
			}
			newTAssignment = tAssignmentDAO.store(oldTAssignment);
			wkFolder = wkFolderService.saveWkFolder(wkFolder,request);
		}
		
		return newTAssignment;
	}

	/**************************************************************************
	 * Description:删除作业
	 * 
	 * @author：黄崔俊
	 * @date ：2015-8-12
	 **************************************************************************/
	@Override
	public void deleteTAssignment(TAssignment tAssignment) {
		// TODO Auto-generated method stub
		tAssignmentDAO.remove(tAssignment);
		
	}

	/**************************************************************************
	 * Description:查询作业列表
	 * 
	 * @author：黄崔俊
	 * @date ：2015-8-18
	 **************************************************************************/
	@Override
	public List<ViewTAssignment> findViewTAssignmentList(User nowUser,
			Integer cid, Integer flag) {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer("from TAssignment c where c.TCourseSite.id = '" + cid + "' and c.type like 'assignment'");
		if(flag == 0){//学生只能看到开始时间之后的作业
			sql.append(" and c.TAssignmentControl.startdate < now() and c.status='1'");
		}
		if (flag==1) { //老师只能看到自己布置的作业
			sql.append(" and c.user.username = '"+nowUser.getUsername()+"'");
		}
		sql.append(" order by c.sequence asc");
		List<TAssignment> tAssignments = tAssignmentDAO.executeQuery(sql.toString(), 0, -1);
		List<ViewTAssignment> viewTAssignments = new ArrayList<ViewTAssignment>();
		for (TAssignment tAssignment : tAssignments) {
			ViewTAssignment viewTAssignment = new ViewTAssignment();
			viewTAssignment.setId(tAssignment.getId());
			viewTAssignment.setContent(tAssignment.getContent());
			viewTAssignment.setCreatedTime(tAssignment.getCreatedTime());
			viewTAssignment.setDescription(tAssignment.getDescription());
			viewTAssignment.setStatus(tAssignment.getStatus());
			viewTAssignment.setTAssignmentAnswerAssign(tAssignment.getTAssignmentAnswerAssign());
			viewTAssignment.setTAssignmentControl(tAssignment.getTAssignmentControl());
			viewTAssignment.setTAssignmentSections(tAssignment.getTAssignmentSections());
			viewTAssignment.setWkFolder(tAssignment.getWkFolder());
			viewTAssignment.setTitle(tAssignment.getTitle());
			viewTAssignment.setType(tAssignment.getType());
			viewTAssignment.setUser(tAssignment.getUser());
			viewTAssignment.setType(tAssignment.getType());
			Set<TAssignmentGrading> assignmentGradings = tAssignment.getTAssignmentGradings();
			Map<String,TAssignmentGrading> tAssignmentGradingSumitTemp = new HashMap<String,TAssignmentGrading>();
			Map<String,TAssignmentGrading> tAssignmentGradingNotCorretTemp = new HashMap<String,TAssignmentGrading>();
			for (TAssignmentGrading tAssignmentGrading : assignmentGradings) {
				if (tAssignmentGrading.getSubmitTime()==1) {//已提交
					if (tAssignmentGradingSumitTemp.containsKey(tAssignmentGrading.getUserByStudent().getUsername())) {
						if(tAssignmentGrading.getAccessmentgradingId()>tAssignmentGradingSumitTemp.get(tAssignmentGrading.getUserByStudent().getUsername()).getAccessmentgradingId()){
							tAssignmentGradingSumitTemp.put(tAssignmentGrading.getUserByStudent().getUsername(), tAssignmentGrading);//若有历史提交记录，则替换掉
							if (tAssignmentGrading.getUserByGradeBy()==null) {//未批改
								tAssignmentGradingNotCorretTemp.put(tAssignmentGrading.getUserByStudent().getUsername(), tAssignmentGrading);
							}else{//若有过批改，则把集合中原未批改记录去除
								tAssignmentGradingNotCorretTemp.remove(tAssignmentGrading.getUserByStudent().getUsername());
							}
						}
					}else {
						tAssignmentGradingSumitTemp.put(tAssignmentGrading.getUserByStudent().getUsername(), tAssignmentGrading);
						if (tAssignmentGrading.getUserByGradeBy()==null) {//未批改
							tAssignmentGradingNotCorretTemp.put(tAssignmentGrading.getUserByStudent().getUsername(), tAssignmentGrading);
						}
					}
				}
			}
			viewTAssignment.settAssignGradeSubmitCount(tAssignmentGradingSumitTemp.size());
			viewTAssignment.settAssignGradeNotCorrectCount(tAssignmentGradingNotCorretTemp.size());
			viewTAssignments.add(viewTAssignment);
		}
		return viewTAssignments;
	}
	/**************************************************************************
	 * Description:修改作业发布状态
	 * 
	 * @author：黄崔俊
	 * @date ：2015-8-19
	 **************************************************************************/
	@Override
	public void changeAsignmentStatus(TAssignment tAssignment) {
		// TODO Auto-generated method stub
		tAssignment = tAssignmentDAO.findTAssignmentById(tAssignment.getId());
		tAssignment.setStatus(1);
		tAssignmentDAO.store(tAssignment);
	}
	/**************************************************************************
	 * Description:根据发布状态查询测验列表
	 * 
	 * @author：黄崔俊
	 * @date ：2015-8-28
	 **************************************************************************/
	@Override
	public List<TAssignment> findTAssignmentList(Integer cid,String type, Integer status) {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer("from TAssignment c where c.TCourseSite.id = '"+cid+"' and c.type = '"+ type +"' and c.status = '"+status+"' order by c.sequence asc");
		List<TAssignment> exams = tAssignmentDAO.executeQuery(sql.toString(), 0, -1);
		return exams;
	}
	/**************************************************************************
	 * Description:查询作业列表
	 * 
	 * @author： 裴继超
	 * @date ：2015-9-25
	 **************************************************************************/
	@Override
	public List<ViewTAssignment> findTAssignmentList(User nowUser,
			Integer cid, Integer flag, String chapterOrLesson) {
		// TODO Auto-generated method stub
		List<ViewTAssignment> viewTAssignments = null;
		TCourseSite tCourseSite = tCourseSiteDAO.findTCourseSiteById(cid);
		if(flag==1||tCourseSite.getTCourseSiteUsers().toString().contains(nowUser.getUsername())){//如果该门课下的学生包含当前登陆人，则进行查询
			
			StringBuffer sql = new StringBuffer("from TAssignment c where 1=1 ");
			//判断作业属于章节还是课时
			if(chapterOrLesson.equals("chapter")){
				sql.append(" and c.wkFolder.WkChapter.TCourseSite.id = '" + cid + "' and c.type like 'assignment'");
			}else if(chapterOrLesson.equals("lesson")){
				sql.append(" and c.wkFolder.WkLesson.wkChapter.TCourseSite.id = '" + cid + "' and c.type like 'assignment'");
			}
			if(flag == 0){//学生只能看到开始时间之后的作业
				sql.append(" and c.TAssignmentControl.startdate < now() and c.status='1'");
			}
			if (flag==1) { //老师只能看到自己布置的作业
				sql.append(" and c.user.username = '"+nowUser.getUsername()+"'");
			}
			sql.append(" order by c.sequence asc");
			List<TAssignment> tAssignments = tAssignmentDAO.executeQuery(sql.toString(), 0, -1);
			viewTAssignments = new ArrayList<ViewTAssignment>();
			for (TAssignment tAssignment : tAssignments) {
				ViewTAssignment viewTAssignment = new ViewTAssignment();
				
				String userId=shareService.getUser().getUsername();
				String sql1="from TAssignmentGrading c where c.TAssignment.id='"+tAssignment.getId()+"' and c.userByStudent.username='"+userId+"' and c.submitTime=1 order by c.submitdate desc";
				List<TAssignmentGrading> Grandings=tAssignmentGradingDAO.executeQuery(sql1, 0,-1);
				
				
				viewTAssignment.setSubmitTimeForStudent(Grandings.size());
				viewTAssignment.setId(tAssignment.getId());
				viewTAssignment.setContent(tAssignment.getContent());
				viewTAssignment.setCreatedTime(tAssignment.getCreatedTime());
				viewTAssignment.setDescription(tAssignment.getDescription());
				viewTAssignment.setStatus(tAssignment.getStatus());
				viewTAssignment.setTAssignmentAnswerAssign(tAssignment.getTAssignmentAnswerAssign());
				viewTAssignment.setTAssignmentControl(tAssignment.getTAssignmentControl());
				viewTAssignment.setTAssignmentSections(tAssignment.getTAssignmentSections());
				viewTAssignment.setWkFolder(tAssignment.getWkFolder());
				viewTAssignment.setTitle(tAssignment.getTitle());
				viewTAssignment.setType(tAssignment.getType());
				viewTAssignment.setUser(tAssignment.getUser());
				viewTAssignment.setType(tAssignment.getType());
				Set<TAssignmentGrading> assignmentGradings = tAssignment.getTAssignmentGradings();
				Map<String,TAssignmentGrading> tAssignmentGradingSumitTemp = new HashMap<String,TAssignmentGrading>();
				Map<String,TAssignmentGrading> tAssignmentGradingNotCorretTemp = new HashMap<String,TAssignmentGrading>();
				//定义学生的得分
				String scoreForStudent = null;
				for (TAssignmentGrading tAssignmentGrading : assignmentGradings) {
					if(tAssignmentGrading.getFinalScore() != null) {
						scoreForStudent = tAssignmentGrading.getFinalScore().toString();
					}
					if (tAssignmentGrading.getSubmitTime()==1) {//已提交
						if (tAssignmentGradingSumitTemp.containsKey(tAssignmentGrading.getUserByStudent().getUsername())) {
							if(tAssignmentGrading.getAccessmentgradingId()>tAssignmentGradingSumitTemp.get(tAssignmentGrading.getUserByStudent().getUsername()).getAccessmentgradingId()){
								tAssignmentGradingSumitTemp.put(tAssignmentGrading.getUserByStudent().getUsername(), tAssignmentGrading);//若有历史提交记录，则替换掉
								if (tAssignmentGrading.getUserByGradeBy()==null) {//未批改
									tAssignmentGradingNotCorretTemp.put(tAssignmentGrading.getUserByStudent().getUsername(), tAssignmentGrading);
								}else{//若有过批改，则把集合中原未批改记录去除
									tAssignmentGradingNotCorretTemp.remove(tAssignmentGrading.getUserByStudent().getUsername());
								}
							}
						}else {
							tAssignmentGradingSumitTemp.put(tAssignmentGrading.getUserByStudent().getUsername(), tAssignmentGrading);
							if (tAssignmentGrading.getUserByGradeBy()==null) {//未批改
								tAssignmentGradingNotCorretTemp.put(tAssignmentGrading.getUserByStudent().getUsername(), tAssignmentGrading);
							}
						}
					}
				}
				viewTAssignment.setScoreForStudent(scoreForStudent);
				viewTAssignment.setTAssignmentGradings(assignmentGradings);
				viewTAssignment.settAssignGradeSubmitCount(tAssignmentGradingSumitTemp.size());
				viewTAssignment.settAssignGradeNotCorrectCount(tAssignmentGradingNotCorretTemp.size());
				
				//站点下总学生数
				int allstudents=findCourseStudentsSize(cid)	;
				viewTAssignment.setNoSubmitStudents(allstudents - viewTAssignment.gettAssignGradeSubmitCount());
				viewTAssignments.add(viewTAssignment);
			}
		}
		return viewTAssignments;
	}
	/**************************************************************************
	 * Description:获取站点下的总学生人数
	 * 
	 * @author： 裴继超
	 * @date ：2015-9-28
	 **************************************************************************/
	@Override
	public int findCourseStudentsSize(Integer id){
		String sql="select count(c) from TCourseSiteUser c where c.TCourseSite.id='"+id+"' and c.authority.id = '1'";

		int result = ((Long)tCourseSiteUserDAO.createQuerySingleResult(sql).getSingleResult()).intValue();
		
		return result;
	}
	/**************************************************************************
	 * Description:获取提交作业人数
	 * 
	 * @author： 裴继超
	 * @date ：2015-9-28
	 **************************************************************************/
	@Override
	public List<TCourseSiteUser> findNotSubmitAssignmentStudents(Integer id){
		//已提交作业的学生
		String sql="from TAssignmentGrading c where c.TAssignment.id='"+id+"'  and c.submitTime=1 order by c.submitdate desc";
		List<TAssignmentGrading> Students=tAssignmentGradingDAO.executeQuery(sql, 0,-1);
		String ss = "";
		for (TAssignmentGrading SubmitStudent : Students) {
			ss+="'"+SubmitStudent.getUserByStudent().getUsername()+"',";
		}
		
		
		int siteId=-1;
	
		String str1 = ss.substring(0, ss.length() -1);
		String sql1 = "from TCourseSiteUser c where c.TCourseSite.id='"+siteId+"' and c.user.username not in("+str1+")";
		List<TCourseSiteUser> users = tCourseSiteUserDAO.executeQuery(sql1,0,-1);
		return users;	
	}
	/**************************************************************************
	 * Description:查询测试列表
	 * 
	 * @author：黄崔俊
	 * @date ：2015-10-15
	 **************************************************************************/
	@Override
	public List<ViewTAssignment> findViewExamList(User nowUser,
			Integer cid, Integer flag, String chapterOrLesson) {
		// TODO Auto-generated method stub
		List<ViewTAssignment> viewTAssignments = null;
		
		StringBuffer sql = new StringBuffer("from TAssignment c where 1=1 ");
		TCourseSite tCourseSite = tCourseSiteDAO.findTCourseSiteById(cid);
		if(chapterOrLesson.equals("chapter")){
			sql.append(" and c.wkFolder.WkChapter.TCourseSite.id = '" + cid + "' and c.type like 'exam'");
		}else if(chapterOrLesson.equals("lesson")){
			sql.append(" and c.wkFolder.WkLesson.wkChapter.TCourseSite.id = '" + cid + "' and c.type like 'exam'");
		}
		if(flag==1||tCourseSite.getTCourseSiteUsers().toString().contains(nowUser.getUsername())){//如果该门课下的学生包含当前登陆人，则进行查询
			if(flag == 0){//学生只能看到开始时间之后的已发布的测验
				sql.append(" and c.TAssignmentControl.startdate < now() and c.status='1'");
			}
			if (flag==1) { //老师只能看到自己布置的作业
				//sql.append(" and c.user.username = '"+nowUser.getUsername()+"'");
			}
			List<TAssignment> tAssignments = tAssignmentDAO.executeQuery(sql.toString(), 0, -1);
			viewTAssignments = new ArrayList<ViewTAssignment>();
			for (TAssignment tAssignment : tAssignments) {
				ViewTAssignment viewTAssignment = new ViewTAssignment();
				viewTAssignment.setId(tAssignment.getId());
				viewTAssignment.setContent(tAssignment.getContent());
				viewTAssignment.setCreatedTime(tAssignment.getCreatedTime());
				viewTAssignment.setDescription(tAssignment.getDescription());
				viewTAssignment.setStatus(tAssignment.getStatus());
				viewTAssignment.setTAssignmentAnswerAssign(tAssignment.getTAssignmentAnswerAssign());
				viewTAssignment.setTAssignmentControl(tAssignment.getTAssignmentControl());
				viewTAssignment.setTAssignmentSections(tAssignment.getTAssignmentSections());
				viewTAssignment.setWkFolder(tAssignment.getWkFolder());
				viewTAssignment.setTitle(tAssignment.getTitle());
				viewTAssignment.setType(tAssignment.getType());
				Set<TAssignmentGrading> assignmentGradings = tAssignment.getTAssignmentGradings();
				int count = 0;
				for (TAssignmentGrading tAssignmentGrading : assignmentGradings) {
					if(tAssignmentGrading.getSubmitTime()!=null) {//如果有提交的记录，则提交的学生数量加一
						if (tAssignmentGrading.getSubmitTime()>0) {
							count++;
						}
					}
				}
				if (flag==0) {
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
				}
				//查找该课程站点下学生用户的总个数
				String hql = "select t from TCourseSiteUser t where t.TCourseSite.id ="+ tCourseSite.getId() +" and t.authority.id =1";
				int studentUserSize = tCourseSiteUserDAO.executeQuery(hql, 0, -1).size();
				viewTAssignment.settAssignGradeSubmitCount(count);
				viewTAssignment.setNoSubmitStudents(studentUserSize-count);
				viewTAssignments.add(viewTAssignment);
			}
		}
		return viewTAssignments;
	}

	/**************************************************************************
	 * Description:根据登陆身份从作业当前位置上查找在本课程下的下一条作业
	 * 
	 * @author：黄崔俊
	 * @date ：2015-10-19
	 **************************************************************************/
	@Override
	public TAssignment querybigSeqByTAssignment(TAssignment tAssignment,
			User user) {
		// TODO Auto-generated method stub
		
		StringBuffer sql = new StringBuffer("from TAssignment c where c.TCourseSite.id = '"+0+"'");
		if (user.getAuthorities().toString().contains("TEACHER")) {
			sql.append(" and c.user.username = '"+user.getUsername()+"'");
		}
		sql.append(" and c.sequence > '"+tAssignment.getSequence()+"' order by c.sequence asc");
		//只查比当前排序大的第一条记录
		List<TAssignment> tAssignments = tAssignmentDAO.executeQuery(sql.toString(), 0, 1);
		
		if (tAssignments.size() == 0) {
			tAssignment = null;//如果没有则返回空
		}else {
			tAssignment = tAssignments.get(0);
		}
		return tAssignment;
	}

	/**************************************************************************
	 * Description:保存作业
	 * 
	 * @author：黄崔俊
	 * @date ：2015-10-19
	 **************************************************************************/
	@Override
	public void saveTAssignment(TAssignment tAssignment) {
		// TODO Auto-generated method stub
		tAssignmentDAO.store(tAssignment);
	}

	/**************************************************************************
	 * Description:根据登陆身份从作业当前位置上查找在本课程下的上一条作业
	 * 
	 * @author：黄崔俊
	 * @date ：2015-10-19
	 **************************************************************************/
	@Override
	public TAssignment queryminByTAssignment(TAssignment tAssignment, User user) {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer("from TAssignment c where c.TCourseSite.id = '"+0+"'");
		if (user.getAuthorities().toString().contains("TEACHER")) {
			sql.append(" and c.user.username = '"+user.getUsername()+"'");
		}
		sql.append(" and c.sequence < '"+tAssignment.getSequence()+"' order by c.sequence desc");
		//只查比当前排序小的第一条记录
		List<TAssignment> tAssignments = tAssignmentDAO.executeQuery(sql.toString(), 0, 1);
		if (tAssignments.size() == 0) {
			tAssignment = null;//如果没有则返回空
		}else {
			tAssignment = tAssignments.get(0);
		}
		return tAssignment;
	}
	/**************************************************************************
	 * Description:根据登陆身份上移作业顺序
	 * 
	 * @author：黄崔俊
	 * @date ：2016-1-26
	 **************************************************************************/
	@Override
	public TAssignment moveupTAssignment(TAssignment tAssignment, User user) {
		// TODO Auto-generated method stub
		//从位置上查找在本课程下的上一条作业
		TAssignment preTAssignment = queryminByTAssignment(tAssignment,user);
		if (preTAssignment != null) {//如果有上一条记录，则进行交换
			Integer tempSequence = tAssignment.getSequence();
			tAssignment.setSequence(preTAssignment.getSequence());
			tAssignment = tAssignmentDAO.store(tAssignment);
			
			preTAssignment.setSequence(tempSequence);
			tAssignmentDAO.store(preTAssignment);
		}
		return tAssignment;
	}
	/**************************************************************************
	 * Description:根据登陆身份下移作业顺序
	 * 
	 * @author：黄崔俊
	 * @date ：2016-1-26
	 **************************************************************************/
	@Override
	public TAssignment movedownTAssignment(TAssignment tAssignment, User user) {
		// TODO Auto-generated method stub
		//从位置上查找在本课程下的下一条作业
		TAssignment nextTAssignment = querybigSeqByTAssignment(tAssignment,user);
		if (nextTAssignment != null) {//如果有下一条记录，则进行交换
			Integer tempSequence = tAssignment.getSequence();
			tAssignment.setSequence(nextTAssignment.getSequence());
			tAssignment = tAssignmentDAO.store(tAssignment);
			
			nextTAssignment.setSequence(tempSequence);
			tAssignmentDAO.store(nextTAssignment);
		}
		return tAssignment;
	}

	/**************************************************************************
	 * Description:查询作业记录数
	 * 
	 * @author：黄崔俊
	 * @date ：2015-11-10 
	 **************************************************************************/
	@Override
	public int countViewTAssignmentList(User nowUser, Integer cid, int flag) {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer("select count(c) from TAssignment c where c.TCourseSite.id = '" + cid + "' and c.type like 'assignment'");
		if(flag == 0){//学生只能看到开始时间之后的作业
			sql.append(" and c.TAssignmentControl.startdate < now() and c.status='1'");
		}
		if (flag==1) { //老师只能看到自己布置的作业
			sql.append(" and c.user.username = '"+nowUser.getUsername()+"'");
		}
		int count = ((Long)tAssignmentDAO.createQuerySingleResult(sql.toString()).getSingleResult()).intValue();
		return count;
	}
	/**************************************************************************
	 * Description:查询作业记录数
	 * 
	 * @author：黄崔俊
	 * @date ：2015-11-10 
	 **************************************************************************/
	@Override
	public List<ViewTAssignment> findViewTAssignmentList(User nowUser,
			Integer cid, int flag, Integer currpage, int pageSize) {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer("from TAssignment c where c.TCourseSite.id = '" + cid + "' and c.type like 'assignment'");
		if(flag == 0){//学生只能看到开始时间之后的作业
			sql.append(" and c.TAssignmentControl.startdate < now() and c.status='1'");
		}
		if (flag==1) { //老师只能看到自己布置的作业
			sql.append(" and c.user.username = '"+nowUser.getUsername()+"'");
		}
		sql.append("order by c.sequence asc");
		List<TAssignment> tAssignments = tAssignmentDAO.executeQuery(sql.toString(), (currpage-1)*pageSize, pageSize);
		List<ViewTAssignment> viewTAssignments = new ArrayList<ViewTAssignment>();
		for (TAssignment tAssignment : tAssignments) {
			ViewTAssignment viewTAssignment = new ViewTAssignment();
			if(flag == 0){//学生则查看提交次数
				String userId=shareService.getUser().getUsername();
				String sql1="from TAssignmentGrading c where c.TAssignment.id='"+tAssignment.getId()+"' and c.userByStudent.username='"+userId+"' and c.submitTime=1 order by c.submitdate desc";
				List<TAssignmentGrading> Grandings=tAssignmentGradingDAO.executeQuery(sql1, 0,-1);
				viewTAssignment.setSubmitTimeForStudent(Grandings.size());
			}
			
			
			viewTAssignment.setId(tAssignment.getId());
			viewTAssignment.setContent(tAssignment.getContent());
			viewTAssignment.setCreatedTime(tAssignment.getCreatedTime());
			viewTAssignment.setDescription(tAssignment.getDescription());
			viewTAssignment.setStatus(tAssignment.getStatus());
			viewTAssignment.setTAssignmentAnswerAssign(tAssignment.getTAssignmentAnswerAssign());
			viewTAssignment.setTAssignmentControl(tAssignment.getTAssignmentControl());
			viewTAssignment.setTAssignmentSections(tAssignment.getTAssignmentSections());
			//viewTAssignment.setTCourseSite(tAssignment.getTCourseSite());
			viewTAssignment.setTitle(tAssignment.getTitle());
			viewTAssignment.setType(tAssignment.getType());
			viewTAssignment.setUser(tAssignment.getUser());
			viewTAssignment.setType(tAssignment.getType());
			Set<TAssignmentGrading> assignmentGradings = tAssignment.getTAssignmentGradings();
			Map<String,TAssignmentGrading> tAssignmentGradingSumitTemp = new HashMap<String,TAssignmentGrading>();
			Map<String,TAssignmentGrading> tAssignmentGradingNotCorretTemp = new HashMap<String,TAssignmentGrading>();
			for (TAssignmentGrading tAssignmentGrading : assignmentGradings) {
				if (tAssignmentGrading.getSubmitTime()==1) {//已提交
					if (tAssignmentGradingSumitTemp.containsKey(tAssignmentGrading.getUserByStudent().getUsername())) {
						if(tAssignmentGrading.getAccessmentgradingId()>tAssignmentGradingSumitTemp.get(tAssignmentGrading.getUserByStudent().getUsername()).getAccessmentgradingId()){
							tAssignmentGradingSumitTemp.put(tAssignmentGrading.getUserByStudent().getUsername(), tAssignmentGrading);//若有历史提交记录，则替换掉
							if (tAssignmentGrading.getUserByGradeBy()==null) {//未批改
								tAssignmentGradingNotCorretTemp.put(tAssignmentGrading.getUserByStudent().getUsername(), tAssignmentGrading);
							}else{//若有过批改，则把集合中原未批改记录去除
								tAssignmentGradingNotCorretTemp.remove(tAssignmentGrading.getUserByStudent().getUsername());
							}
						}
					}else {
						tAssignmentGradingSumitTemp.put(tAssignmentGrading.getUserByStudent().getUsername(), tAssignmentGrading);
						if (tAssignmentGrading.getUserByGradeBy()==null) {//未批改
							tAssignmentGradingNotCorretTemp.put(tAssignmentGrading.getUserByStudent().getUsername(), tAssignmentGrading);
						}
					}
				}
			}
			viewTAssignment.settAssignGradeSubmitCount(tAssignmentGradingSumitTemp.size());
			viewTAssignment.settAssignGradeNotCorrectCount(tAssignmentGradingNotCorretTemp.size());
			viewTAssignments.add(viewTAssignment);
		}
		return viewTAssignments;
	}
	/**************************************************************************
	 * Description:作业-下载教师附件
	 * 
	 * @author： 裴继超
	 * @date ：2016-8-5
	 **************************************************************************/
	@Override
	public void downloadTeacherFile(TAssignment tAssignment,
			HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try{	
			int endAddress = tAssignment.getTeacherFilePath().lastIndexOf(".");
			String ss = tAssignment.getTeacherFilePath().substring(endAddress, tAssignment.getTeacherFilePath().length());//后缀名
			String fileName = tAssignment.getTitle()+ss;
			
			String root = System.getProperty("xidlims.root");
			FileInputStream fis = new FileInputStream(root+tAssignment.getTeacherFilePath());
			response.setCharacterEncoding("utf-8");
			//解决上传中文文件时不能下载的问题
			response.setContentType("multipart/form-data;charset=UTF-8");
			if (request.getHeader("User-Agent").toLowerCase().indexOf("firefox") > 0) {
				fileName = new String(fileName.getBytes("UTF-8"),"ISO8859-1");// firefox浏览器
			} else if (request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0) {
				fileName = URLEncoder.encode(fileName, "UTF-8");// IE浏览器
			} else {
				fileName = URLEncoder.encode(fileName, "UTF-8");
			}
			response.setHeader("Content-Disposition", "attachment;fileName="+fileName.replaceAll(" ", ""));
			
			OutputStream fos = response.getOutputStream();
			byte[] buffer = new byte[8192];
			int count = 0;
			while((count = fis.read(buffer))>0){
				fos.write(buffer,0,count);   
			}
			fis.close();
			fos.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				response.getOutputStream().close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	/**************************************************************************
	 * Description:保存考试
	 * 
	 * @author： 裴继超
	 * @date ：2016-6-1
	 **************************************************************************/
	@Override
	public TAssignment saveTAssignmentForTest(Integer tCourseSiteId,
			TAssignment tAssignment, HttpServletRequest request) throws ParseException {
		// TODO Auto-generated method stub
		//当前登陆用户
		User nowUser = shareService.getUserDetail();
		String[] sectionNames = request.getParameterValues("sectionName");
		String[] questionIds = request.getParameterValues("questionIdTest");
		String[] itemTypes = request.getParameterValues("itemTypeTest");
		String[] itemQuantitys = request.getParameterValues("itemQuantityTest");
		String[] itemScores = request.getParameterValues("itemScoreTest");
		
		TAssignment newTAssignment = null;
		if (tAssignment.getId()==null) {//没有id表示是新增
			//先保存考试所属文件夹
			WkFolder wkFolder = new WkFolder();
			wkFolder.setName(tAssignment.getTitle());
			wkFolder.setType(6);
			wkFolder.setUser(nowUser);
			if(tAssignment.getWkFolder().getWkLesson().getId()!=-1){
				wkFolder.setWkChapter(null);
				wkFolder.setWkLesson(tAssignment.getWkFolder().getWkLesson());
			}else{
				wkFolder.setWkChapter(tAssignment.getWkFolder().getWkChapter());
			}
			wkFolder = wkFolderService.saveWkFolder(wkFolder,request);
			/*
			 * 保存TAssignment需先保存一对一子表，然后设置为空，保存，然后再重新设置一对一子表，再保存。
			*/
			//设置考试的课程站点
			//tAssignment.setTCourseSite(tCourseSiteDAO.findTCourseSiteById(cid));
			//保存所属文件夹
			tAssignment.setWkFolder(wkFolder);
			//记录考试的控制表
			TAssignmentControl tAssignmentControl = tAssignment.getTAssignmentControl();
			//记录考试的设置表
			TAssignmentAnswerAssign tAssignmentAssign = tAssignment.getTAssignmentAnswerAssign();

			tAssignment.setTAssignmentControl(null);
			tAssignment.setTAssignmentAnswerAssign(null);
			
			tAssignment = tAssignmentDAO.store(tAssignment);
            
			String startdate = request.getParameter("startdateTest");
			String duedate = request.getParameter("duedateTest");
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
			tAssignment.setSequence(tAssignment.getId());//给考试排序，初始值和id保持一致
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
				tAssignmentItemComponent.setSectionName(sectionNames[i]);
				tAssignmentItemComponent.setItemType(Integer.valueOf(itemTypes[i]));
				tAssignmentItemComponent.setItemQuantity(Integer.valueOf(itemQuantitys[i]));
				tAssignmentItemComponent.setTAssignmentQuestionpool(tAssignmentQuestionpoolDAO.findTAssignmentQuestionpoolByPrimaryKey(Integer.valueOf(questionIds[i])));
				tAssignmentItemComponent.setItemScore(new BigDecimal(itemScores[i]));
				tAssignmentItemComponent.setTAssignment(newTAssignment);
				tAssignmentItemComponentDAO.store(tAssignmentItemComponent);
			}
		}else{
			
			TAssignment oldTAssignment = tAssignmentDAO.findTAssignmentById(tAssignment.getId());
			//保存所属文件夹
			WkFolder wkFolder = oldTAssignment.getWkFolder();
			wkFolder.setName(tAssignment.getTitle());
			wkFolder.setUser(nowUser);
			if(tAssignment.getWkFolder().getWkLesson().getId()!=-1){
				wkFolder.setWkChapter(null);
				wkFolder.setWkLesson(tAssignment.getWkFolder().getWkLesson());
			}else{
				wkFolder.setWkChapter(tAssignment.getWkFolder().getWkChapter());
			}
			wkFolder = wkFolderService.saveWkFolder(wkFolder,request);
			
			oldTAssignment.setTitle(tAssignment.getTitle());
			oldTAssignment.setContent(tAssignment.getContent());
			oldTAssignment.setStatus(tAssignment.getStatus());
			
			TAssignmentControl oldTAssignmentControl = tAssignment.getTAssignmentControl();
			
			String startdate = request.getParameter("startdateTest");
			String duedate = request.getParameter("duedateTest");
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
				tAssignmentItemComponent.setSectionName(sectionNames[i]);
				tAssignmentItemComponent.setItemType(Integer.valueOf(itemTypes[i]));
				tAssignmentItemComponent.setItemQuantity(Integer.valueOf(itemQuantitys[i]));
				tAssignmentItemComponent.setTAssignmentQuestionpool(tAssignmentQuestionpoolDAO.findTAssignmentQuestionpoolByPrimaryKey(Integer.valueOf(questionIds[i])));
				tAssignmentItemComponent.setItemScore(new BigDecimal(itemScores[i]));
				tAssignmentItemComponent.setTAssignment(newTAssignment);
				tAssignmentItemComponentDAO.store(tAssignmentItemComponent);
			}
		}
		return newTAssignment;
	}
	
	/*************************************************************************************
	 * Description：根据站点编号和测验内容保存测验
	 * 
	 * @author： 裴继超
	 * @date ：2016-6-2
	 *************************************************************************************/
		@Override
		public TAssignment saveTAssignmentForExam(Integer tCourseSiteId,TAssignment tAssignment, HttpServletRequest request) throws ParseException {
			// TODO Auto-generated method stub
			//当前登陆用户
			User nowUser = shareService.getUserDetail();
			TAssignment newTAssignment = null;
			if (tAssignment.getId()==null) {//没有id表示是新增
				//先保存作业所属文件夹
				WkFolder wkFolder = new WkFolder();
				wkFolder.setName(tAssignment.getTitle());
				wkFolder.setType(5);
				wkFolder.setUser(nowUser);
				if(tAssignment.getWkFolder().getWkLesson().getId()!=-1){
					wkFolder.setWkChapter(null);
					wkFolder.setWkLesson(tAssignment.getWkFolder().getWkLesson());
				}else{
					wkFolder.setWkChapter(tAssignment.getWkFolder().getWkChapter());
				}
				wkFolder = wkFolderService.saveWkFolder(wkFolder,request);
				/*
			 * 保存TAssignment需先保存一对一子表，然后设置为空，保存，然后再重新设置一对一子表，再保存。
				*/
				//设置作业的课程站点
				//tAssignment.setTCourseSite(tCourseSiteDAO.findTCourseSiteById(cid));
				//保存测验所属文件夹
				tAssignment.setWkFolder(wkFolder);
				//记录作业的控制表
				TAssignmentControl tAssignmentControl = tAssignment.getTAssignmentControl();
				//记录作业的设置表
				TAssignmentAnswerAssign tAssignmentAssign = tAssignment.getTAssignmentAnswerAssign();
	
				tAssignment.setTAssignmentControl(null);
				tAssignment.setTAssignmentAnswerAssign(null);
				tAssignment.setSequence(tAssignment.getId());//给测试排序，初始值和id保持一致
				
				tAssignment = tAssignmentDAO.store(tAssignment);
	            
				//设置作业开始时间和结束时间
				String startdate = request.getParameter("startdateExam");
				String duedate = request.getParameter("duedateExam");
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
				
			}else{
				TAssignment oldTAssignment = tAssignmentDAO.findTAssignmentById(tAssignment.getId());
				
				//保存所属文件夹
				WkFolder wkFolder = oldTAssignment.getWkFolder();
				wkFolder.setName(tAssignment.getTitle());
				wkFolder.setUser(nowUser);
				if(tAssignment.getWkFolder().getWkLesson().getId()!=-1){
					wkFolder.setWkChapter(null);
					wkFolder.setWkLesson(tAssignment.getWkFolder().getWkLesson());
				}else{
					wkFolder.setWkChapter(tAssignment.getWkFolder().getWkChapter());
				}
				wkFolder = wkFolderService.saveWkFolder(wkFolder,request);
				
				oldTAssignment.setTitle(tAssignment.getTitle());
				oldTAssignment.setContent(tAssignment.getContent());
				oldTAssignment.setStatus(tAssignment.getStatus());
				
				TAssignmentControl oldTAssignmentControl = oldTAssignment.getTAssignmentControl();
				//设置开始时间和结束时间
				String startdate = request.getParameter("startdateExam");
				String duedate = request.getParameter("duedateExam");
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				Date beginDate = sdf.parse(startdate);
				Date endDate = sdf.parse(duedate);
				Calendar startCalendar = Calendar.getInstance();
				startCalendar.setTime(beginDate);
				oldTAssignmentControl.setStartdate(startCalendar);
				Calendar dueCalendar = Calendar.getInstance();
				dueCalendar.setTime(endDate);
				oldTAssignmentControl.setDuedate(dueCalendar);
				oldTAssignmentControl.setToGradebook(tAssignment.getTAssignmentControl().getToGradebook());
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
		
		/*************************************************************************************
		 * Description：复制作业，考试，测试
		 * 
		 * @author： 裴继超
		 * @date ：2016-6-15
		 *************************************************************************************/
		@Override
		public TAssignment copyTAssignment(WkFolder wkFolder,WkFolder newWkFolder,Integer tCourseSiteId) {
			// TODO Auto-generated method stub
			//当前登陆用户
			User nowUser = shareService.getUserDetail();
			TAssignment tAssignment = null;
			TAssignment newTAssignment = new TAssignment();
			Set<TAssignment> tAssignments = wkFolder.getTAssignments();
			if(tAssignments.size()!=0){
				tAssignment = tAssignments.iterator().next();
				//查询当前
				//记录作业的控制表
				TAssignmentControl tAssignmentControl = tAssignment.getTAssignmentControl();
				//记录作业的设置表
				TAssignmentAnswerAssign tAssignmentAssign = tAssignment.getTAssignmentAnswerAssign();
				
				TAssignmentControl newTAssignmentControl = new TAssignmentControl();
				TAssignmentAnswerAssign newTAssignmentAnswerAssign = new TAssignmentAnswerAssign();
	
				newTAssignment.setSiteId(tCourseSiteId);
				newTAssignment.setTAssignmentControl(null);
				newTAssignment.setTAssignmentAnswerAssign(null);
				newTAssignment.setWkFolder(newWkFolder);
				newTAssignment.setTitle(tAssignment.getTitle());
				newTAssignment.setDescription(tAssignment.getDescription());
				newTAssignment.setUser(nowUser);
				newTAssignment.setStatus(0);
				newTAssignment.setType(tAssignment.getType());
				newTAssignment.setContent(tAssignment.getContent());
				newTAssignment.setSequence(tAssignment.getSequence());
				newTAssignment.setTestParentId(tAssignment.getTestParentId());
				newTAssignment.setTeacherFilePath(tAssignment.getTeacherFilePath());
				newTAssignment.setIsSubmit(tAssignment.getIsSubmit());
				newTAssignment.setCreatedTime(Calendar.getInstance());
				
				newTAssignment = tAssignmentDAO.store(newTAssignment);
	            
				//保存作业的控制表
				newTAssignmentControl.setDuedate(tAssignmentControl.getDuedate());
				newTAssignmentControl.setTimelimit(tAssignmentControl.getTimelimit());
				newTAssignmentControl.setStartdate(tAssignmentControl.getStartdate());
				newTAssignmentControl.setSubmessage(tAssignmentControl.getSubmessage());
				newTAssignmentControl.setToGradebook(tAssignmentControl.getToGradebook());
				newTAssignmentControl.setSubmitType(tAssignmentControl.getSubmitType());
				newTAssignmentControl.setGradeToStudent(tAssignmentControl.getGradeToStudent());
				newTAssignmentControl.setGradeToTotalGrade(tAssignmentControl.getGradeToTotalGrade());
				newTAssignmentControl.setTAssignment(newTAssignment);
				newTAssignmentControl = tAssignmentControlDAO.store(newTAssignmentControl);
				
				//保存作业的设置表
				newTAssignmentAnswerAssign.setUser(nowUser);
				newTAssignmentAnswerAssign.setTAssignment(newTAssignment);
				newTAssignmentAnswerAssign.setScore(tAssignmentAssign.getScore());
				newTAssignmentAnswerAssign.setGrade(tAssignmentAssign.getGrade());
				newTAssignmentAnswerAssign.setContent(tAssignmentAssign.getContent());
				newTAssignmentAnswerAssign.setReference(tAssignmentAssign.getReference());
				newTAssignmentAnswerAssign.setScoreDate(tAssignmentAssign.getScoreDate());
				
				newTAssignmentAnswerAssign =tAssignmentAnswerAssignDAO.store(newTAssignmentAnswerAssign);
				
				
				newTAssignment.setTAssignmentControl(newTAssignmentControl);
				newTAssignment.setTAssignmentAnswerAssign(newTAssignmentAnswerAssign);
				newTAssignment = tAssignmentDAO.store(newTAssignment);
			}
				
			
			return newTAssignment;
		}

	
		/**************************************************************************
		 * Description:测试-发布-设置status为1
		 * 
		 * @author：于侃
		 * @date ：2016-09-06
		 **************************************************************************/
		public void releaseExam(int assignmentId){
			TAssignment tAssignment = new TAssignment();
			tAssignment = findTAssignmentById(assignmentId);
			tAssignment.setStatus(1);
			tAssignmentDAO.store(tAssignment);
			tAssignmentDAO.flush();
		}
		/*************************************************************************************
		 * Description：传递考试
		 * 
		 * @author： 储俊
		 * @date：2016-09-07
		 *************************************************************************************/
		@Override
		public List<TAssignmentItemComponent> findTAssignmentItemComponent(TAssignment test){
			StringBuffer sql = new StringBuffer("from TAssignmentItemComponent a  where a.TAssignment.id=" + test.getId());
			List<TAssignmentItemComponent> tAssignmentItemComponents=tAssignmentItemComponentDAO.executeQuery(sql.toString() ,0, -1);
			return tAssignmentItemComponents;
		}
		
		/**************************************************************************
		 * Description:保存考勤
		 * 
		 * @author： 李军凯
		 * @date ：2016-10-17
		 **************************************************************************/
		@Transactional
		public TAssignment saveAttendence(TAssignment tAssignment,HttpServletRequest request) throws ParseException {
			// TODO Auto-generated method stub
			//当前登陆用户
			User nowUser = shareService.getUserDetail();
			TAssignment newTAssignment = null;
			if (tAssignment.getId()==null) {//没有id表示是新增
				//先保存考勤所属文件夹
				WkFolder wkFolder = new WkFolder();
				wkFolder.setName(tAssignment.getTitle());
				wkFolder.setType(9);//考勤
				wkFolder.setUser(nowUser);
				String belongTo = "";
				if(tAssignment.getWkFolder().getWkLesson().getId()!=-1){
					wkFolder.setWkChapter(null);
					wkFolder.setWkLesson(tAssignment.getWkFolder().getWkLesson());
					belongTo = "lesson"+tAssignment.getWkFolder().getWkLesson().getId();
				}else{
					wkFolder.setWkChapter(tAssignment.getWkFolder().getWkChapter());
					belongTo = "chapter"+tAssignment.getWkFolder().getWkChapter().getId();
				}
				wkFolder = wkFolderService.saveWkFolder(wkFolder,request);
				/*
				 * 保存TAssignment需先保存一对一子表，然后设置为空，保存，然后再重新设置一对一子表，再保存。
				*/
				//设置考勤的课程站点
				//tAssignment.setTCourseSite(tCourseSiteDAO.findTCourseSiteById(cid));
				
				//查询当前
				//记录考勤的控制表
				TAssignmentControl tAssignmentControl = tAssignment.getTAssignmentControl();
				//记录考勤的设置表
				TAssignmentAnswerAssign tAssignmentAssign = tAssignment.getTAssignmentAnswerAssign();

				tAssignment.setTAssignmentControl(null);
				tAssignment.setTAssignmentAnswerAssign(null);
				tAssignment.setWkFolder(wkFolder);
				tAssignment.setCreatedTime(Calendar.getInstance());
				tAssignment.setStatus(0);
				tAssignment = tAssignmentDAO.store(tAssignment);
	            
				//保存考勤的控制表
				tAssignmentControl.setTAssignment(tAssignment);
				tAssignmentControl =tAssignmentControlDAO.store(tAssignmentControl);
				
				//保存考勤的设置表
				tAssignmentAssign.setUser(userDAO.findUserByPrimaryKey(tAssignmentAssign.getUser().getUsername()));
				tAssignmentAssign.setTAssignment(tAssignment);
				tAssignmentAssign =tAssignmentAnswerAssignDAO.store(tAssignmentAssign);
				
				
				tAssignment.setTAssignmentControl(tAssignmentControl);
				tAssignment.setTAssignmentAnswerAssign(tAssignmentAssign);
				tAssignment.setSequence(tAssignment.getId());//给考勤排序，初始值和id保持一致
				//tAssignmentControlDAO.flush();
				
				newTAssignment = tAssignmentDAO.store(tAssignment);
				
			}else{
				TAssignment oldTAssignment = tAssignmentDAO.findTAssignmentByPrimaryKey(tAssignment.getId());				
				TAssignmentControl oldTAssignmentControl = tAssignment.getTAssignmentControl();
				
				oldTAssignmentControl.setGradeToStudent(tAssignment.getTAssignmentControl().getGradeToStudent());
				oldTAssignmentControl.setGradeToTotalGrade(tAssignment.getTAssignmentControl().getGradeToTotalGrade());
				oldTAssignmentControl.setSubmitType(tAssignment.getTAssignmentControl().getSubmitType());
				oldTAssignmentControl.setTAssignment(oldTAssignment);
				oldTAssignmentControl = tAssignmentControlDAO.store(oldTAssignmentControl);
				oldTAssignment.setTAssignmentControl(oldTAssignmentControl);
				
				//暂时考勤设置表的这两个字段值是默认的
				TAssignmentAnswerAssign oldTAssignmentAnswerAssign = tAssignment.getTAssignmentAnswerAssign();
				oldTAssignmentAnswerAssign.setScore(tAssignment.getTAssignmentAnswerAssign().getScore());
				//oldTAssignmentAnswerAssign.setUser(userDAO.findUserByPrimaryKey(tAssignment.getTAssignmentAnswerAssign().getUser().getUsername()));
				oldTAssignmentAnswerAssign.setTAssignment(oldTAssignment);
				oldTAssignmentAnswerAssign = tAssignmentAnswerAssignDAO.store(oldTAssignmentAnswerAssign);
				oldTAssignment.setTAssignmentAnswerAssign(oldTAssignmentAnswerAssign);
			 
				
				WkFolder wkFolder = new WkFolder();	
				wkFolder.setId(tAssignment.getWkFolder().getId());
				wkFolder.setName(tAssignment.getTitle());
				wkFolder.setType(9);
				wkFolder.setUser(nowUser);			
				String belongTo = "";
				if(tAssignment.getWkFolder().getWkLesson().getId()!=null&&tAssignment.getWkFolder().getWkLesson().getId()!=-1){
					wkFolder.setWkChapter(null);
					wkFolder.setWkLesson(tAssignment.getWkFolder().getWkLesson());
					belongTo = "lesson"+tAssignment.getWkFolder().getWkLesson().getId();
				}else{
					wkFolder.setWkLesson(null);
					wkFolder.setWkChapter(tAssignment.getWkFolder().getWkChapter());
					belongTo = "chapter"+tAssignment.getWkFolder().getWkChapter().getId();
				}
				newTAssignment = tAssignmentDAO.store(oldTAssignment);
				wkFolder = wkFolderService.saveWkFolder(wkFolder,request);
			}
			
			return newTAssignment;
		}





}