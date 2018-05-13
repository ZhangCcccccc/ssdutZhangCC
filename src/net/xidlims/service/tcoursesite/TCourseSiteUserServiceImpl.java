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

import net.xidlims.dao.AuthorityDAO;
import net.xidlims.dao.SchoolClassesDAO;
import net.xidlims.dao.SchoolCourseDAO;
import net.xidlims.dao.SchoolCourseInfoDAO;
import net.xidlims.dao.SchoolMajorDAO;
import net.xidlims.dao.TAssignmentAnswerDAO;
import net.xidlims.dao.TAssignmentDAO;
import net.xidlims.dao.TAssignmentItemDAO;
import net.xidlims.dao.TAssignmentQuestionpoolDAO;
import net.xidlims.dao.TCourseSiteDAO;
import net.xidlims.dao.TCourseSiteGroupDAO;
import net.xidlims.dao.TCourseSiteUserDAO;
import net.xidlims.dao.UserDAO;
import net.xidlims.domain.Authority;
import net.xidlims.domain.SchoolClasses;
import net.xidlims.domain.SchoolCourse;
import net.xidlims.domain.SchoolCourseDetail;
import net.xidlims.domain.SchoolCourseInfo;
import net.xidlims.domain.SchoolMajor;
import net.xidlims.domain.SystemMajor12;
import net.xidlims.domain.TAssignment;
import net.xidlims.domain.TAssignmentAnswer;
import net.xidlims.domain.TAssignmentItem;
import net.xidlims.domain.TAssignmentQuestionpool;
import net.xidlims.domain.TAssignmentSection;
import net.xidlims.domain.TCourseSite;
import net.xidlims.domain.TCourseSiteGroup;
import net.xidlims.domain.TCourseSiteUser;
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


@Service("TCourseSiteUserService")
public class TCourseSiteUserServiceImpl implements TCourseSiteUserService {

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

	/*************************************************************************************
	 * Description:课程站点-查询课程下的学生
	 * 
	 * @author： 裴继超
	 * @date：2016-6-16
	 *************************************************************************************/
	@Override
	public List<TCourseSiteUser> findTCourseSiteUserBySiteId(Integer id, Integer currpage, 
			Integer pageSize) {
		// TODO Auto-generated method stub
		String sql = "from TCourseSiteUser u where u.TCourseSite.id = " + id;
		sql+=" and u.user.userRole=0";
		List<TCourseSiteUser> tCourseSiteUsers = tCourseSiteUserDAO.executeQuery(sql, (currpage-1)*pageSize, pageSize);
		return tCourseSiteUsers;
	}
	
	/**************************************************************************
	 * Description:班级成员-查看课程下的学生分组(分页)
	 *  
	 * @author：李军凯
	 * @date ：2016-09-14
	 **************************************************************************/
	@Override
	public List<TCourseSiteGroup> findTCourseSiteGroupBySiteId(Integer id, Integer currpage, 
			Integer pageSize) {
		// TODO Auto-generated method stub
		String sql = "from TCourseSiteGroup u where u.TCourseSite.id = " + id;
		List<TCourseSiteGroup> tCourseSiteGroups = tCourseSiteGroupDAO.executeQuery(sql, (currpage-1)*pageSize, pageSize);
		return tCourseSiteGroups;
	}
	/**************************************************************************
	 * Description:班级成员-通过课程号查看课程下的学生分组（不分页）
	 *  
	 * @author：李军凯
	 * @date ：2016-09-27
	 **************************************************************************/
	@Override
	public List<TCourseSiteGroup> findTCourseSiteGroupsBySiteId(Integer id) {
		// TODO Auto-generated method stub
		String sql = "from TCourseSiteGroup u where u.TCourseSite.id = " + id;
		List<TCourseSiteGroup> tCourseSiteGroups = tCourseSiteGroupDAO.executeQuery(sql);
		return tCourseSiteGroups;
	}
	/**************************************************************************
	 * Description:班级成员-查寻学生分组
	 *  
	 * @author：李军凯
	 * @date ：2016-09-14
	 **************************************************************************/
	@Override
	public TCourseSiteGroup findTCourseSiteGroupById(Integer id) {
		// TODO Auto-generated method stub
		TCourseSiteGroup tCourseSiteGroup = tCourseSiteGroupDAO.findTCourseSiteGroupById(id);
		return tCourseSiteGroup;
	}
	/**************************************************************************
	 * Description:班级成员-保存学生分组
	 *  
	 * @author：李军凯
	 * @date ：2016-09-14
	 **************************************************************************/
	public TCourseSiteGroup saveTCourseSiteGroup(TCourseSiteGroup tCourseSiteGroup) {
		tCourseSiteGroup = tCourseSiteGroupDAO.store(tCourseSiteGroup);
		tCourseSiteGroupDAO.flush();
		return tCourseSiteGroup;		
	}
	/**************************************************************************
	 * Description:班级成员-删除学生分组
	 *  
	 * @author：李军凯
	 * @date ：2016-09-14
	 **************************************************************************/
	public void  deleteTCourseSiteGroup(TCourseSiteGroup tCourseSiteGroup) {
		//把该分组下的学生的GroupId置空
		String sql = "from TCourseSiteUser u where u.groupId = " + tCourseSiteGroup.getId();
		List<TCourseSiteUser> tCourseSiteUsers = tCourseSiteUserDAO.executeQuery(sql);
		for(TCourseSiteUser tCourseSiteUser : tCourseSiteUsers){
			tCourseSiteUser.setgroupId(null);
			saveTCourseSiteUsersForRole(tCourseSiteUser);
		}
		//删除分组
		tCourseSiteGroupDAO.remove(tCourseSiteGroup);
		tCourseSiteGroupDAO.flush();		
	}
	/**************************************************************************
	 * Description:班级成员-查寻分组下的学生总数
	 *  
	 * @author：李军凯
	 * @date ：2016-09-18
	 **************************************************************************/
	public int findTCourseSiteGroupTotalRecords(Integer id) {
		// TODO Auto-generated method stub
		String sql = "select count(u)from TCourseSiteUser u where u.groupId = " + id;
		int result = ((Long)tCourseSiteUserDAO.createQuerySingleResult(sql).getSingleResult()).intValue();
		return result;
	}
	/**************************************************************************
	 * Description:班级成员-查寻分组下的学生（分页）
	 *  
	 * @author：李军凯
	 * @date ：2016-09-18
	 **************************************************************************/
	public List<TCourseSiteUser> findTCourseSiteUserByTCourseSiteId(Integer id, Integer currpage, 
			Integer pageSize) {
		// TODO Auto-generated method stub
		String sql = "from TCourseSiteUser u where u.groupId = " + id;
		List<TCourseSiteUser> tCourseSiteUsers = tCourseSiteUserDAO.executeQuery(sql, (currpage-1)*pageSize, pageSize); 
		return tCourseSiteUsers;
	}
	/**************************************************************************
	 * Description:班级成员-查寻分组下的学生（不分页）
	 *  
	 * @author：李军凯
	 * @date ：2016-09-29
	 **************************************************************************/
	public List<TCourseSiteUser> findTCourseSiteUserByGroupId(Integer id) {
		// TODO Auto-generated method stub
		String sql = "from TCourseSiteUser u where u.groupId = " + id;
		List<TCourseSiteUser> tCourseSiteUsers = tCourseSiteUserDAO.executeQuery(sql); 
		return tCourseSiteUsers;
	}
	/**************************************************************************
	 * Description:班级成员-查寻课程学生总数
	 *  
	 * @author：李军凯
	 * @date ：2016-09-18
	 **************************************************************************/
	public int findTCourseSiteGroupTotalRecordsBySite(Integer tCourseSiteId,Integer tCourseSiteGroupId) {
		// TODO Auto-generated method stub
		String sql = "select count(u) from TCourseSiteUser u where u.TCourseSite.id = " + tCourseSiteId +" and u.groupId is null ";
		int result = ((Long)tCourseSiteUserDAO.createQuerySingleResult(sql).getSingleResult()).intValue();
		return result;
	}
	/**************************************************************************
	 * Description:班级成员-查寻课程未分组学生列表
	 *  
	 * @author：李军凯
	 * @date ：2016-09-18
	 **************************************************************************/
	public List<TCourseSiteUser> findtCourseSiteUsersList(Integer tCourseSiteId,Integer tCourseSiteGroupId,int curr, int size){
		List<TCourseSiteUser> tCourseSiteUsers=new ArrayList<TCourseSiteUser>();
		String sql = "from TCourseSiteUser u where u.TCourseSite.id = " + tCourseSiteId +" and u.groupId is null order by u.user.username";
		tCourseSiteUsers = tCourseSiteUserDAO.executeQuery(sql,(curr-1)*size,size);
		return tCourseSiteUsers;
	}
	/**************************************************************************
	 * Description:班级成员-保存学生的分组
	 *  
	 * @author：李军凯
	 * @date ：2016-09-18
	 **************************************************************************/
	public void saveTCourseSiteGroupUser(Integer id,Integer tCourseSiteGroupId) {
		// TODO Auto-generated method stub
		/*String sql = "from TCourseSiteUser u where u.TCourseSite.id = " + tCourseSiteId +" and u.user.username like '"+username+"'";
		List<TCourseSiteUser> tCourseSiteUsers = tCourseSiteUserDAO.executeQuery(sql);
		for(TCourseSiteUser tCourseSiteUser : tCourseSiteUsers){
			tCourseSiteUser.setgroupId(tCourseSiteGroupId);
			saveTCourseSiteUsersForRole(tCourseSiteUser);
		}*/	
		//查找学生
		TCourseSiteUser tCourseSiteUser = tCourseSiteUserDAO.findTCourseSiteUserByPrimaryKey(id);
		//添加分组
		tCourseSiteUser.setgroupId(tCourseSiteGroupId);
		saveTCourseSiteUsersForRole(tCourseSiteUser);
	}
	/**************************************************************************
	 * Description:班级成员-查寻课程下的所有学生
	 *  
	 * @author：李军凯
	 * @date ：2016-09-21
	 **************************************************************************/
	public List<TCourseSiteUser> findAlltCourseSiteUsers(Integer tCourseSiteId){
		List<TCourseSiteUser> tCourseSiteUsers=new ArrayList<TCourseSiteUser>();
		String sql = "from TCourseSiteUser u where u.TCourseSite.id = " + tCourseSiteId +" order by u.user.username ";
		tCourseSiteUsers = tCourseSiteUserDAO.executeQuery(sql,0,-1);
		return tCourseSiteUsers;
	}
	/**************************************************************************
	 * Description:班级成员-通过id查询课程下的某个学生
	 *  
	 * @author：李军凯
	 * @date ：2016-09-21
	 **************************************************************************/
	@Override
	public TCourseSiteUser findTCourseSiteUserById(Integer id) {
		// TODO Auto-generated method stub
		TCourseSiteUser tCourseSiteUser = tCourseSiteUserDAO.findTCourseSiteUserByPrimaryKey(id);
		return tCourseSiteUser;
	}
	/*************************************************************************************
	 * Description:课程站点-班级成员-获取在校用户user的分页列表信息
	 * 
	 * @author： 裴继超
	 * @date：2016-7-25
	 *************************************************************************************/
	@Override
	public List<User> findUsersList(User user,int tCourseSiteId, int curr, int size) {
		//获取当前课程站点
		TCourseSite tCourseSite = tCourseSiteDAO.findTCourseSiteById(tCourseSiteId);
		//新建一个list存放当前课程站点学生username
		List<String> list = new ArrayList<String>(); 
		String usernames = "";
//		//去掉老师用户
//		List<TCourseSiteUser> tCourseSiteUserList = tCourseSite.getTCourseSiteUsers();
		//循环当前站点学生，并把username存到list
		for(TCourseSiteUser t:tCourseSite.getTCourseSiteUsers()){
			list.add(t.getUser().getUsername());
			usernames = usernames + "'" + t.getUser().getUsername() + "',";
		}
		//如果没有进入for循环，则不必去除逗号
		if(usernames.length()!=0){
			//去掉字符串最后一个逗号
			usernames = usernames.substring(0,usernames.length()-1);
		}
		//list转化为数组
		
		String query = "";
		//判断获取的用户的信息是否为空
		if (user.getUsername() != null) {
			query = query + " and u.username like '%" + user.getUsername() + "%'";
		}
		if (user.getCname() != null && !user.getCname().equals("")) {
			query = query + " and u.cname like '%" + user.getCname() + "%'";
		}
		if (user.getSchoolAcademy()!=null&&user.getSchoolAcademy().getAcademyNumber()!=null&&!user.getSchoolAcademy().getAcademyNumber().equals("")){
			query = query + " and u.schoolAcademy.academyNumber like '%" + user.getSchoolAcademy().getAcademyNumber() + "%' ";
		}
		if (user.getMajorNumber()!=null&&!user.getMajorNumber().equals("")){
			query = query + " and u.majorNumber like '%" + user.getMajorNumber() + "%' ";
		}
		if (user.getSchoolClasses()!=null&&user.getSchoolClasses().getClassNumber()!=null&&!user.getSchoolClasses().getClassNumber().equals("")){
			query = query + " and u.schoolClasses.classNumber like '%" + user.getSchoolClasses().getClassNumber() + "%' ";
		}
		// 查询用户表；
		StringBuffer sql;
		//如果usernames为空则不必加后面半句
		if(usernames==""){
			sql = new StringBuffer("select u from User u where u.userStatus like '%在籍%'");
		}else{
			sql = new StringBuffer("select u from User u where u.userStatus like '%在籍%'" +
					" and u.username not in ("+ usernames +") ");
		}
		// 将query添加到sb1后
		sql.append(query);
		sql.append(" order by u.username");
	    // 执行sb语句
		System.out.print("---"+sql);
		List<User> users = userDAO.executeQuery(sql.toString(),(curr-1)*size,size);
		return users;
	}
	
	/*************************************************************************************
	 * Description:课程站点-班级成员-获取用户的总记录数（去掉当前站点学生）
	 * 
	 * @author： 裴继超
	 * @date：2016-7-25
	 *************************************************************************************/
	@Transactional
	public int getUsersRecords(User user,int tCourseSiteId){
		//获取当前课程站点
		TCourseSite tCourseSite = tCourseSiteDAO.findTCourseSiteById(tCourseSiteId);
		//新建一个list存放当前课程站点学生username
		String usernames = "";
		List<String> list = new ArrayList<String>(); 
		//循环当前站点学生，并把username存到list
		for(TCourseSiteUser t:tCourseSite.getTCourseSiteUsers()){
			list.add(t.getUser().getUsername());
			usernames = usernames + "'" + t.getUser().getUsername() + "',";
		}
		if(usernames.length()>0){
			usernames = usernames.substring(0,usernames.length()-1);
		}
		String sql;
		if(usernames==""){
			sql="select count(u) from User u where 1=1 and u.userStatus like '%在籍%'";
		}else{
			//得出用户数量（由于用户的数据量比较多，不能够使用userDAO.findAllUsers()方法查找用户）
			sql="select count(u) from User u where 1=1 and u.userStatus like '%在籍%'" +
					"and u.username not in ("+ usernames +") ";
		}
		if(user!=null&&user.getUsername() != null){
			sql+=" and u.username='"+user.getUsername()+"' ";
		}
		if (user.getCname() != null && !user.getCname().equals("")) {
			sql+=" and u.cname like '%" + user.getCname() + "%'";
		}
		if (user.getSchoolAcademy()!=null&&user.getSchoolAcademy().getAcademyNumber()!=null&&!user.getSchoolAcademy().getAcademyNumber().equals("")){
			sql+=" and u.schoolAcademy.academyNumber like '%" + user.getSchoolAcademy().getAcademyNumber() + "%' ";
		}
		if (user.getMajorNumber()!=null&&!user.getMajorNumber().equals("")){
			sql+=" and u.majorNumber like '%" + user.getMajorNumber() + "%' ";
		}
		if (user.getSchoolClasses()!=null&&user.getSchoolClasses().getClassNumber()!=null&&!user.getSchoolClasses().getClassNumber().equals("")){
			sql+=" and u.schoolClasses.classNumber like '%" + user.getSchoolClasses().getClassNumber() + "%' ";
		}
		System.out.print("---"+sql);
		return  ((Long) userDAO.createQuerySingleResult(sql).getSingleResult()).intValue();
	}
	
	/*************************************************************************************
	 * Description:课程站点-班级成员-根据username获取学生
	 * 
	 * @author： 裴继超
	 * @date：2016-7-25
	 *************************************************************************************/
	@Override
	public User findUserByUsername(String username) {
		User user = userDAO.findUserByPrimaryKey(username);
		return user;
	}
	
	/*************************************************************************************
	 * Description:课程站点-班级成员-保存班级成员
	 * 
	 * @author： 裴继超
	 * @date：2016-7-28
	 *************************************************************************************/
	@Override
	public TCourseSiteUser saveTCourseSiteUser(TCourseSiteUser tCourseSiteUser){
		tCourseSiteUser = tCourseSiteUserDAO.store(tCourseSiteUser);
		tCourseSiteUserDAO.flush();
		return tCourseSiteUser;
	}
	
	/*************************************************************************************
	 * Description:课程站点-班级成员-保存班级成员列表
	 * 
	 * @author： 裴继超
	 * @date：2016-7-28
	 *************************************************************************************/
	@Override
	public void saveTCourseSiteUsers(TCourseSite tCourseSite,String[] usernames){
		//循环遍历选择的学生主键
		for(String u:usernames){
			//根据主键获取学生对象
			User user = this.findUserByUsername(u);
			//获取学生权限
			Authority authority = authorityDAO.findAuthorityById(1);
			//新建站点学生对象，并赋值
			TCourseSiteUser tCourseSiteUser = new TCourseSiteUser();
			tCourseSiteUser.setTCourseSite(tCourseSite);
			tCourseSiteUser.setUser(user);
			tCourseSiteUser.setAuthority(authority);
			tCourseSiteUser.setRole(0);
			tCourseSiteUser.setIncrement(0);
			//保存站点学生
			tCourseSiteUser = this.saveTCourseSiteUser(tCourseSiteUser);
		}
	}
	
	/*************************************************************************************
	 * Description:课程站点-班级成员-通过school_course生成班级成员
	 * 
	 * @author： 裴继超
	 * @date：2016-7-28
	 *************************************************************************************/
	@Override
	public void saveTCourseSiteUsersByCourseNo(TCourseSite tCourseSite,String courseNo){
		//通过no获取对应的教务课程
		SchoolCourse schoolCourse = schoolCourseDAO.findSchoolCourseByPrimaryKey(courseNo);
		//筛选出该教务课程下属的学生username，并去重
		String usernames = "select distinct s.userByStudentNumber.username from SchoolCourseStudent s where s.schoolCourseDetail.schoolCourse.courseNo like '"
					+ courseNo +"' ";
		//通过username列表获取对应的学生
		String sql = "select u from User u where u.username in ("+usernames+") ";
		List<User> users = userDAO.executeQuery(sql.toString(),0, -1);
		//System.out.print(users.size());
		//循环遍历选择的学生
		for(User u:users){
			//获取学生权限
			Authority authority = authorityDAO.findAuthorityById(1);
			//新建站点学生对象，并赋值
			TCourseSiteUser tCourseSiteUser = new TCourseSiteUser();
			tCourseSiteUser.setTCourseSite(tCourseSite);
			tCourseSiteUser.setUser(u);
			tCourseSiteUser.setAuthority(authority);
			tCourseSiteUser.setRole(0);
			tCourseSiteUser.setIncrement(0);
			//保存站点学生
			tCourseSiteUser = this.saveTCourseSiteUser(tCourseSiteUser);
		}
	}
	
	/*************************************************************************************
	 * Description:课程站点-班级成员-删除班级成员
	 * 
	 * @author： 裴继超
	 * @date：2016-7-28
	 *************************************************************************************/
	@Override
	public void deleteTCourseSiteUser(Integer id){
		TCourseSiteUser tCourseSiteUser = tCourseSiteUserDAO.findTCourseSiteUserByPrimaryKey(id);
		tCourseSiteUserDAO.remove(tCourseSiteUser);
		//循环遍历选择的学生主键
		tCourseSiteUserDAO.flush();
	}
	
	/*************************************************************************************
	 * Description:课程站点-班级成员-查询所有专业
	 * 
	 * @author： 裴继超
	 * @date：2016-8-5
	 *************************************************************************************/
	@Override
	public List<SchoolMajor> getAllSchoolMajor(Integer currpage, Integer pageSize) {
		StringBuffer hql = new StringBuffer("select m from SchoolMajor m where 1=1");
		
		return schoolMajorDAO.executeQuery(hql.toString(), (currpage-1)*pageSize, pageSize);
	}
	
	/*************************************************************************************
	 * Description:课程站点-班级成员-查询所有班级
	 * 
	 * @author： 裴继超
	 * @date：2016-8-5
	 *************************************************************************************/
	@Override
	public List<SchoolClasses> getAllSchoolClass(Integer currpage, Integer pageSize) {
		StringBuffer hql = new StringBuffer("select c from SchoolClasses c where 1=1");
		
		return schoolClassesDAO.executeQuery(hql.toString(), (currpage-1)*pageSize, pageSize);
	}
	
	/*************************************************************************************
	 * description：班级成员-切换角色{保存成员}
	 * 
	 * @author：陈乐为
	 * @date：2016-8-26
	 *************************************************************************************/
	@Override
	public void saveTCourseSiteUsersForRole(TCourseSiteUser tCourseSiteUser) {
		tCourseSiteUserDAO.store(tCourseSiteUser);
	}
	
	/**************************************************************************************
	 * description:查询课程下的所有助教
	 * 
	 * @author:于侃
	 * @date:2016年8月31日
	 *************************************************************************************/
	public List<TCourseSiteUser> findTCourseSiteSTeacherBySiteId(Integer id) {
		String sql = "select u from TCourseSiteUser u where u.TCourseSite.id = " + id + " and u.role = '1'";
		List<TCourseSiteUser> tCourseSiteUsers = tCourseSiteUserDAO.executeQuery(sql);
		return tCourseSiteUsers;
	}
	/*************************************************************************************
	 * Description:课程站点-班级成员-根据课程id和username判断其是否是该课程的助教
	 * 
	 * @author：于侃
	 * @date：2016-09-14
	 *************************************************************************************/
	@Override
	public boolean isSTeacherBySiteId(Integer id,String username) {
		//是否是助教标志位
		boolean isSTeacher = false;
		//根据课程id和username判断其是否是该课程的助教
		String sql = "select u from TCourseSiteUser u where u.TCourseSite.id = " + id + " and u.user.username = '" + username + "' and u.role = '1' ";
		List<TCourseSiteUser> tCourseSiteUsers = tCourseSiteUserDAO.executeQuery(sql);
		if(tCourseSiteUsers.size()!=0){
			isSTeacher=true;
		}
		return isSTeacher;
	}
	/**************************************************************************
	 * Description:消息-生成联系组的目录
	 * @author：李军凯
	 * @date ：2016-09-29
	 **************************************************************************/
	public JSONArray findtCourseSiteUserJSONArray(int tCourseSiteId){
		// Json数组（返回值，这个类型是由ztree决定的）
				JSONArray jsonArray = new JSONArray();
				// Json对象，用来存到Json数组里面（格式：{id:5, pId:0, name:"广东省", open:true}）
				JSONObject obj = new JSONObject();
				// 查询一级目录
				//分组
				List<TCourseSiteGroup> tCourseSiteGroupss = findTCourseSiteGroupsBySiteId(tCourseSiteId);
				//学生
				List<TCourseSiteUser> tCourseSiteUsers = findAlltCourseSiteUsers(tCourseSiteId);
				// jsonArray的下标
				int count = 0;
				// 循环学生分组
				for (TCourseSiteGroup cparty1 : tCourseSiteGroupss) {
					// 给JSONObject对象赋值
					obj.put("id", "group"+cparty1.getId());
					obj.put("pId",0);
					// System.out.println(cparty.getPartyCode());
					obj.put("name", cparty1.getGroupTitle());
					obj.put("open", true);
					jsonArray.add(count, obj);
					count++;
				}
				// 循环学生
				for (TCourseSiteUser cparty3 : tCourseSiteUsers) {
		 			// 给JSONObject对象赋值
		 			obj.put("id", cparty3.getId());
		 			if(cparty3.getgroupId()==null){
		 			obj.put("pId",0);//父节点id	
		 			}
		 			else{
		 				obj.put("pId","group"+cparty3.getgroupId());//父节点id	
		 			}
		 			obj.put("name", cparty3.getUser().getCname());		
		 			jsonArray.add(count, obj);
		 			count++;
		 		}		
				return jsonArray;
	}
	
	/**************************************************************************
	 * Description:班级成员-通过学生username和课程site_id获得小组
	 *  
	 * @author：于侃
	 * @date ：2016年10月18日 17:06:51
	 **************************************************************************/
	public TCourseSiteGroup findGroupByUser(String username,Integer id) {
		String sql = "from TCourseSiteUser u where u.user.username = '" + username + "' and u.TCourseSite.id = " + id;
		List<TCourseSiteUser> tCourseSiteUsers = tCourseSiteUserDAO.executeQuery(sql); 
		TCourseSiteGroup group = new TCourseSiteGroup();
		if(tCourseSiteUsers.size() > 0){
			group = tCourseSiteGroupDAO.findTCourseSiteGroupById(tCourseSiteUsers.get(0).getgroupId());
		}
		return group;
	}
	/**************************************************************************
	 * Description:判断这个老师是否参与这门可
	 *  
	 * @author：李雪腾
	 * @date ：2017.7.10
	 **************************************************************************/
	@Override
	public boolean isUserBelongToTeacher(Integer cid) {
		//获取当前登录人
		User user = shareService.getUser();
		//获取当前站点
		TCourseSite tCourseSite = tCourseSiteDAO.findTCourseSiteById(cid);
		//获取这个站点下面有哪些老师
		SchoolCourse schoolCourse = tCourseSite.getSchoolCourse();
		Set<SchoolCourseDetail> schoolCourseDetails = schoolCourse.getSchoolCourseDetails();
		Set<User> userList=new HashSet<User>();
		for(SchoolCourseDetail detail:schoolCourseDetails){
			
			User user2 = detail.getUser();
			if(user2.getUsername().equals(user.getUsername())){
				return true;
			}
			//补充教师身份认证
			Set<User> user3List = detail.getUsers();
			for(User user3:user3List){
				if(user3.getUsername().equals(user.getUsername())){
					return true;
				}
			}
			Set<User> user4List = detail.getUserByScheduleTeachers();
			for(User user4:user4List){
				if(user4.getUsername().equals(user.getUsername())){
					return true;
				}
			}
			
		}
		return false;
	}
	
	/**************************************************************************
	 * Description:根据课程号和username查找tCourseSiteUser
	 *  
	 * @author：张佳鸣
	 * @date ：2017-10-19
	 **************************************************************************/
	public TCourseSiteUser findTCourseSiteUserBySiteIdAndUsername(int tCourseSiteId,String username){
		
		String sql = "from TCourseSiteUser u where u.user.username = '" + username + "' and u.TCourseSite.id = " + tCourseSiteId;
		
		List<TCourseSiteUser> tCourseSiteUsers = tCourseSiteUserDAO.executeQuery(sql);
		
		if(tCourseSiteUsers !=null && !tCourseSiteUsers.isEmpty()){
			
			return tCourseSiteUsers.get(0);
		}else{
			
			return null;
		}
		
		
	}
	
	/*************************************************************************************
	 * Description:课程站点-查询课程下的学生，不分页
	 * 
	 * @author： 张佳鸣
	 * @date：2017-11-30
	 *************************************************************************************/
	public List<TCourseSiteUser> findAlltCourseSiteUserBySiteId(Integer id){
		
		String sql = "from TCourseSiteUser u where u.TCourseSite.id = " + id;
		sql+=" and u.user.userRole=0";
		List<TCourseSiteUser> tCourseSiteUsers = tCourseSiteUserDAO.executeQuery(sql, 0, -1);
		return tCourseSiteUsers;
		
	}
}
