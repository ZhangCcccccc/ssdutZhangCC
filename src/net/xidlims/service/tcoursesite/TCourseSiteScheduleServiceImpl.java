package net.xidlims.service.tcoursesite;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;

import net.xidlims.constant.CommonConstantInterface;
import net.xidlims.dao.AuthorityDAO;
import net.xidlims.dao.SchoolCourseDAO;
import net.xidlims.dao.TCourseSiteChannelDAO;
import net.xidlims.dao.TCourseSiteDAO;
import net.xidlims.dao.TCourseSiteScheduleDAO;
import net.xidlims.dao.TCourseSiteTagDAO;
import net.xidlims.dao.TCourseSiteUserDAO;
import net.xidlims.dao.TimetableSelfCourseDAO;
import net.xidlims.dao.UserDAO;
import net.xidlims.dao.WkCourseDAO;
import net.xidlims.dao.TAssignmentDAO;
import net.xidlims.domain.SchoolCourse;
import net.xidlims.domain.SchoolCourseDetail;
import net.xidlims.domain.SchoolCourseStudent;
import net.xidlims.domain.TAssignment;
import net.xidlims.domain.TAssignmentGrading;
import net.xidlims.domain.TCourseSite;
import net.xidlims.domain.TCourseSiteChannel;
import net.xidlims.domain.TCourseSiteSchedule;
import net.xidlims.domain.TCourseSiteTag;
import net.xidlims.domain.TCourseSiteUser;
import net.xidlims.domain.TDiscuss;
import net.xidlims.domain.TimetableCourseStudent;
import net.xidlims.domain.TimetableSelfCourse;
import net.xidlims.domain.User;
import net.xidlims.domain.WkCourse;
import net.xidlims.service.common.ShareService;
import net.xidlims.view.ViewTAssignment;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Service("TCourseSiteScheduleService")
public class TCourseSiteScheduleServiceImpl implements TCourseSiteScheduleService {
	@Autowired
	private TCourseSiteUserDAO tCourseSiteUserDAO;
	
	@Autowired
	private ShareService shareService;
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private TAssignmentDAO tAssignmentDAO;
	
	@Autowired
	private TCourseSiteDAO tCourseSiteDAO;
	
	@Autowired
	private TCourseSiteChannelDAO tCourseSiteChannelDAO;
	
	@Autowired
	private AuthorityDAO authorityDAO;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private WkCourseDAO wkCourseDAO;
	
	@Autowired
	private SchoolCourseDAO schoolCourseDAO;
	
	@Autowired
	private TimetableSelfCourseDAO timetableSelfCourseDAO;
	
	@Autowired
	private WkChapterService wkChapterService;
	
	@Autowired
	private WkLessonService wkLessonService;
	
	@Autowired
	private WkFolderService wkFolderService;
	
	@Autowired
	private TCourseSiteScheduleDAO tCourseSiteScheduleDAO;
	
	@Autowired
	private TCourseSiteService tCourseSiteService;
	@Autowired
	private TCourseSiteTagDAO tCourseSiteTagDAO;
	
	
	/****************************************************************************
	 * Description:课程信息-保存课表
	 * 
	 * @author：裴继超
	 * @date：2015-8-3
	 ****************************************************************************/
	@Override
	public void saveSchedule(TCourseSiteSchedule schedule) {
		//保存课表信息
		tCourseSiteScheduleDAO.store(schedule);
		tCourseSiteScheduleDAO.flush();
	}
	
	/****************************************************************************
	 * Description:课程信息-删除课表
	 * 
	 * @author：裴继超
	 * @date：2015-8-3
	 ****************************************************************************/
	@Override
	public void deleteSchedule(TCourseSiteSchedule schedule) {
		//删除课表信息
		schedule.setUser(userDAO.findUserByPrimaryKey(schedule.getUser().getUsername().trim())==null?shareService.getUser():userDAO.findUserByPrimaryKey(schedule.getUser().getUsername().trim()));
		tCourseSiteScheduleDAO.remove(schedule);
		tCourseSiteScheduleDAO.flush();
	}
	
	/****************************************************************************
	 * Description:课程信息-根据星期、课时获取课表信息
	 * 
	 * @author：裴继超
	 * @date：2016-8-3
	 ****************************************************************************/
	@Override
	public String findScheduleByDayAndSession(Integer tCourseSiteId,String username,
			String day,String session){
		String sql = "select s from TCourseSiteSchedule s where 1=1 ";
		sql = sql + " and s.TCourseSite.id = " + tCourseSiteId ;
		if(username!=null&&!username.equals("")){
			sql = sql + " and s.user.username like '" + username + "' " ;
		}
		sql = sql + " and s.day like '" + day + "' " +
				" and s.session like '" + session + "' ";
		List<TCourseSiteSchedule> schedules = tCourseSiteScheduleDAO.executeQuery(sql, 0,-1);
		String content = "";
		for(TCourseSiteSchedule s:schedules){
			content = content + s.getContent();
		}
		return content;
	}
	
	/****************************************************************************
	 * Description:课程信息-获取课表map
	 * 
	 * @author：裴继超
	 * @date：2016-8-3
	 ****************************************************************************/
	@Override
	public Map findSchedulesMap(Integer tCourseSiteId){
		//当前课程
		TCourseSite tCourseSite = tCourseSiteService.findCourseSiteById(tCourseSiteId);
		//当前登录人
		User user = shareService.getUser();
		Map<Integer, String> map = new LinkedHashMap<Integer, String>(0);
		for(Integer i=1;i<6;i++){
			for(Integer j=1;j<5;j++){
				String content = findScheduleByDayAndSession(tCourseSiteId,user.getUsername(),i.toString(),j.toString());
				map.put(i*10+j, content);
			}
		}
		return map;
		
	}
	
	/****************************************************************************
	 * Description:课程信息-获取课表星期或课时列表
	 * 
	 * @author：裴继超
	 * @date：2016-8-3
	 ****************************************************************************/
	@Override
	public List<TCourseSiteTag> findTCourseSiteTagsByDescription(String description){
		String sql = "select t from TCourseSiteTag t where 1=1 ";
		sql = sql + " and t.description like '" + description + "' ";
		List<TCourseSiteTag> tCourseSiteTags = tCourseSiteTagDAO.executeQuery(sql, 0,-1);
		return tCourseSiteTags;
	}
	
	/****************************************************************************
	 * Description:课程信息-根据主键获取课表星期或课时
	 * 
	 * @author：裴继超
	 * @date：2016-8-3
	 ****************************************************************************/
	@Override
	public TCourseSiteTag findTCourseSiteTagById(Integer id){
		TCourseSiteTag tCourseSiteTag = tCourseSiteTagDAO.findTCourseSiteTagByPrimaryKey(id);
		return tCourseSiteTag;
	}
		
	/****************************************************************************
	 * Description:课程信息-获取指定课表
	 * 
	 * @author：裴继超
	 * @date：2016-8-3
	 ****************************************************************************/
	@Override
	public List<TCourseSiteSchedule> findTCourseSiteSchedulesBySiteIdAndUsernameAndDayAndSession(
			Integer tCourseSiteId,String username,String day,String session){
		String sql = "select s from TCourseSiteSchedule s where 1=1 ";
		sql = sql + " and s.TCourseSite.id = " + tCourseSiteId ;
		if(username!=null&&!username.equals("")){
			sql = sql + " and s.user.username like '" + username + "' " ;
		}
		if(day!=null&&!day.equals("")){
			sql = sql + " and s.TCourseSiteTagByDay.siteTag like '" + day + "' ";
		}
		if(session!=null&&!session.equals("")){
			sql = sql + " and s.TCourseSiteTagBySession.siteTag like '" + session + "' ";
		}
		List<TCourseSiteSchedule> schedules = tCourseSiteScheduleDAO.executeQuery(sql, 0,-1);
		return schedules;
	}
	/****************************************************************************
	 * Description:课程信息-获取每周课表
	 * 
	 * @author：李军凯
	 * @date：2016-10-13
	 ****************************************************************************/
	@Override
	public List<TCourseSiteSchedule> findTCourseSiteSchedulesBySiteIdAndWeek(
			Integer tCourseSiteId,Integer week){
		String sql = "select s from TCourseSiteSchedule s where s.TCourseSite.id = " + tCourseSiteId + " and s.week = " + week;
		List<TCourseSiteSchedule> schedules = tCourseSiteScheduleDAO.executeQuery(sql, 0,-1);
		return schedules;
	}
	/*******************************************************
	 * Description:课程信息-获取每周参考文件
	 * 
	 * @author：李军凯
	 * @date ：2016-10-14
	 *********************************************************/
	@Override
	public List findCurFolder(int week,int tCourseSiteId) {
		//获取通知与用户名的对应关系
		String sql ="select * from view_wk_upload_wk_folder_wk_chapter_list c where c.site_id = "+tCourseSiteId+" and c.seq = "+ week;
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> list = query.getResultList();
		return list;
		
	}
	/*******************************************************
	 * Description:课程信息-获取每周测试
	 * 
	 * @author：李军凯
	 * @date ：2016-10-14
	 *********************************************************/
	@Override
	public List findCurExams(int week,int tCourseSiteId) {
		//获取通知与用户名的对应关系
		String sql ="select * from view_wk_chapter_wk_folder_t_assignment c where c.site_id = "+tCourseSiteId+" and c.seq = "+ week + " and c.type like 'exam' ";
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> list = query.getResultList();
		return list;
		
	}
	/*******************************************************
	 * Description:课程信息-获取每周考试
	 * 
	 * @author：李军凯
	 * @date ：2016-10-14
	 *********************************************************/
	@Override
	public List findCurTests(int week,int tCourseSiteId) {
		//获取通知与用户名的对应关系
		String sql ="select * from view_wk_chapter_wk_folder_t_assignment c where c.site_id = "+tCourseSiteId+" and c.seq = "+ week +" and c.type like 'test' ";		
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> list = query.getResultList();
		return list;
		
	}
	/**************************************************************************
	 * Description:课程信息-获取测试列表
	 * 
	 * @author：李军凯
	 * @date ：2016-10-14
	 **************************************************************************/
	@Override
	public List<ViewTAssignment> findViewExamList(List<Object[]> curExamsOrTests,Integer cid) {
		// TODO Auto-generated method stub
		List<ViewTAssignment> viewTAssignments = null;
		TCourseSite tCourseSite = tCourseSiteDAO.findTCourseSiteById(cid);
		List<TAssignment> tAssignments = null;
		for(Object t:curExamsOrTests){
			String[] a = (String[]) t;
			int ids = Integer.parseInt(a[0]) ;
			TAssignment ta= tAssignmentDAO.findTAssignmentById(ids);
			tAssignments.add(ta);
		}
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
				if (tAssignmentGrading.getSubmitTime()>0) {//如果有提交的记录，则提交的学生数量加一
					count++;
				}
			}
			
			viewTAssignment.settAssignGradeSubmitCount(count);
			viewTAssignment.setNoSubmitStudents(tCourseSite.getTCourseSiteUsers().size()-count);
			viewTAssignments.add(viewTAssignment);
		}
	
		return viewTAssignments;
	}
}