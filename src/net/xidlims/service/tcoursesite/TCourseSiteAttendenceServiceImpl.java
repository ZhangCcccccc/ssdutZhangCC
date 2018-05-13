package net.xidlims.service.tcoursesite;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.dao.TGradeObjectDAO;
import net.xidlims.dao.TGradeRecordDAO;
import net.xidlims.dao.TGradebookDAO;
import net.xidlims.dao.AuthorityDAO;
import net.xidlims.dao.SchoolClassesDAO;
import net.xidlims.dao.SchoolCourseDAO;
import net.xidlims.dao.SchoolCourseInfoDAO;
import net.xidlims.dao.SchoolMajorDAO;
import net.xidlims.dao.TAssignmentAnswerAssignDAO;
import net.xidlims.dao.TAssignmentAnswerDAO;
import net.xidlims.dao.TAssignmentDAO;
import net.xidlims.dao.TAssignmentGradingDAO;
import net.xidlims.dao.TAssignmentItemDAO;
import net.xidlims.dao.TAssignmentQuestionpoolDAO;
import net.xidlims.dao.TCourseSiteDAO;
import net.xidlims.dao.TCourseSiteGroupDAO;
import net.xidlims.dao.TCourseSiteUserDAO;
import net.xidlims.dao.UserDAO;
import net.xidlims.domain.Authority;
import net.xidlims.domain.SchoolClasses;
import net.xidlims.domain.SchoolCourse;
import net.xidlims.domain.SchoolCourseInfo;
import net.xidlims.domain.SchoolMajor;
import net.xidlims.domain.SystemMajor12;
import net.xidlims.domain.TAssignment;
import net.xidlims.domain.TAssignmentAnswer;
import net.xidlims.domain.TAssignmentAnswerAssign;
import net.xidlims.domain.TAssignmentControl;
import net.xidlims.domain.TAssignmentGrading;
import net.xidlims.domain.TAssignmentItem;
import net.xidlims.domain.TAssignmentQuestionpool;
import net.xidlims.domain.TAssignmentSection;
import net.xidlims.domain.TCourseSite;
import net.xidlims.domain.TCourseSiteGroup;
import net.xidlims.domain.TCourseSiteUser;
import net.xidlims.domain.TGradeObject;
import net.xidlims.domain.TGradeRecord;
import net.xidlims.domain.TGradebook;
import net.xidlims.domain.TimetableCourseStudent;
import net.xidlims.domain.TimetableSelfCourse;
import net.xidlims.domain.User;


import net.xidlims.service.common.ShareService;
import net.xidlims.web.aop.SystemServiceLog;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@Service(" TCourseSiteAttendenceService")
public class TCourseSiteAttendenceServiceImpl implements TCourseSiteAttendenceService {

	@Autowired
	private TAssignmentQuestionpoolDAO tAssignmentQuestionpoolDAO;
	@Autowired
	private TCourseSiteDAO tCourseSiteDAO;
	@Autowired
	private ShareService shareService;
	@Autowired
	private TAssignmentItemDAO tAssignmentItemDAO;
	@Autowired
	private TAssignmentAnswerDAO tAssignmentAnswerDAO;
	@Autowired
	private TAssignmentDAO tAssignmentDAO;
	@Autowired
	private SchoolCourseInfoDAO schoolCourseInfoDAO;
	@PersistenceContext
	private EntityManager entityManager;
	@Autowired
	private TCourseSiteUserDAO tCourseSiteUserDAO;
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private AuthorityDAO authorityDAO;
	@Autowired
	private SchoolCourseDAO schoolCourseDAO;
	@Autowired
	private SchoolMajorDAO schoolMajorDAO;
	@Autowired
	private SchoolClassesDAO schoolClassesDAO;
	@Autowired
	private TCourseSiteGroupDAO tCourseSiteGroupDAO;
	@Autowired
	private TAssignmentAnswerAssignDAO tAssignmentAnswerAssignDAO;
	@Autowired
	private TAssignmentGradingDAO tAssignmentGradingDAO;
	@Autowired
	private TGradebookDAO tGradebookDAO;
	@Autowired
	private TGradeObjectDAO tGradeObjectDAO;
	@Autowired
	private TGradeRecordDAO tGradeRecordDAO;

	/**************************************************************************
	 * Description:考勤-查寻课程下的所有学生的考勤情况
	 *  
	 * @author：李军凯
	 * @date ：2016-10-17
	 **************************************************************************/
	@Override
	public List findAttendenceListByAttendenceId(Integer attendenceId){
		String sql = "select * from view_t_assignment_attendence u where u.id = " + attendenceId + " order by u.username ";
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> list = query.getResultList();
		return list;
	}
	/**************************************************************************
	 * Description:考勤-查寻课程下的所有考勤
	 *  
	 * @author：李军凯
	 * @date ：2016-10-18
	 **************************************************************************/
	@Override
	public List<TAssignment> findAttendenceBytCourseSiteId(Integer tCourseSiteId){
		List<TAssignment> tAssignments=new ArrayList<TAssignment>();
		String sql = " from TAssignment u where u.type like 'attendence' and u.siteId = " + tCourseSiteId;
		tAssignments = tAssignmentDAO.executeQuery(sql);
		return tAssignments;
	}
	/**************************************************************************
	 * Description:考勤-查寻考勤扣分设置
	 *  
	 * @author：李军凯
	 * @date ：2016-10-19
	 **************************************************************************/
	@Override
	public TAssignmentAnswerAssign findTAssignmentAnswerAssignByAssignmentId(Integer assignmentId){
		TAssignment tAssignment = tAssignmentDAO.findTAssignmentById(assignmentId);
		TAssignmentAnswerAssign tAssignmentAnswerAssign = tAssignment.getTAssignmentAnswerAssign();
		return tAssignmentAnswerAssign;
	}
	/**************************************************************************
	 * Description:考勤-查寻课程下某学生的某次考勤状况
	 *  
	 * @author：李军凯
	 * @date ：2016-10-20
	 **************************************************************************/
	@Override
	public TAssignmentGrading findTAssignmentGradingByTAssignmentIdAndtCourseSiteUser(Integer TAssignmentId,
			TCourseSiteUser tCourseSiteUser){
		String username = tCourseSiteUser.getUser().getUsername();
		String sql = " from TAssignmentGrading u where u.TAssignment.id = " + TAssignmentId + " and u.userByStudent.username like '"+username+"'"  ;
		List<TAssignmentGrading> tAssignmentGradings = tAssignmentGradingDAO.executeQuery(sql);
		if(tAssignmentGradings.size()>0){
			return  tAssignmentGradings.get(0);
		}
		else{
			return null;
		}
	}
	/**************************************************************************
	 * Description:根据测验id查询是否进成绩册，是则加入成绩册
	 * 
	 * @author：李军凯
	 * @date ：2016-10-20
	 **************************************************************************/
	@Override
	public void saveGradebook(Integer cid,int assignmentId,TAssignmentGrading tAssignmentGrade) {
		//根据id获取测验
		TAssignment tAssignment = tAssignmentDAO.findTAssignmentById(assignmentId);
		//查询作业（或测验）的控制项
		TAssignmentControl tAssignmentControl = tAssignment.getTAssignmentControl();
		//如果成绩需要加入成绩册，则加入成绩册
		if("yes".equals(tAssignmentControl.getToGradebook())){
			//查询该课程成绩簿
			String sql = "from TGradebook c where c.TCourseSite.id ='"+cid+"'";
			TGradebook tGradebook = tGradebookDAO.executeQuery(sql, 0, -1).get(0);
			//查询测验对应成绩题目
			sql = "from TGradeObject c where c.TGradebook.id ='"+tGradebook.getId()+"' and c.TAssignment.id ='"+tAssignment.getId()+"'";
			TGradeObject tGradeObject = tGradeObjectDAO.executeQuery(sql, 0, -1).get(0);
			//查询测验对应成绩记录
			sql = "from TGradeRecord c where c.TGradeObject.id = '"+tGradeObject.getId()+"' and c.user.username = '"+tAssignmentGrade.getUserByStudent().getUsername()+"'";
			List<TGradeRecord> tGradeRecords = tGradeRecordDAO.executeQuery(sql, 0, -1);
			TGradeRecord tGradeRecord = null;
			//如果该生该次作业（或测验）成绩已有
			if (tGradeRecords.size()>0) {
				tGradeRecord = tGradeRecords.get(0);
				/*if (tAssignmentGrade.getFinalScore().compareTo(tGradeRecord.getPoints())==1) {//如果新成绩高，则覆盖
					tGradeRecord.setPoints(tAssignmentGrade.getFinalScore());
				}*/
				tGradeRecord.setPoints(tAssignmentGrade.getFinalScore());
				
			}else {
				//没有成绩则新建
				tGradeRecord = new TGradeRecord();
				tGradeRecord.setPoints(tAssignmentGrade.getFinalScore());
				tGradeRecord.setTGradeObject(tGradeObject);
				tGradeRecord.setUser(tAssignmentGrade.getUserByStudent());
			}
			tGradeRecord.setRecordTime(Calendar.getInstance());
			//保存成绩
			tGradeRecordDAO.store(tGradeRecord);
		}
	}
}
