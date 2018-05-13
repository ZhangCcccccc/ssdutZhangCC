package net.xidlims.service.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContexts;

import jxl.StringFormulaCell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import flex.messaging.io.ArrayList;

import net.xidlims.domain.SystemTime;
import net.xidlims.dao.SchoolAcademyDAO;
import net.xidlims.dao.SystemBuildDAO;
import net.xidlims.dao.SystemCampusDAO;
import net.xidlims.dao.SystemMajor12DAO;
import net.xidlims.dao.SystemSubject12DAO;
import net.xidlims.dao.SystemTimeDAO;
import net.xidlims.dao.UserDAO;
import net.xidlims.domain.Authority;
import net.xidlims.domain.SchoolAcademy;
import net.xidlims.domain.SystemBuild;
import net.xidlims.domain.SystemCampus;
import net.xidlims.domain.SystemMajor12;
import net.xidlims.domain.SystemSubject12;
import net.xidlims.domain.User;

@Service("SystemService")
public class SystemServiceImpl implements SystemService {
	
	@Autowired
	private SchoolAcademyDAO schoolAcademyDAO;
	@Autowired
	private SystemCampusDAO systemCampusDAO;
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private SystemBuildDAO systemBuildDAO;
	@Autowired
	private SystemSubject12DAO systemSubject12DAO;
	@Autowired
	private SystemMajor12DAO systemMajor12DAO;
	@PersistenceContext
	private EntityManager entityManager;
	@Autowired
	private SystemTimeDAO systemTimeDAO;
	/**
	 * 获取所有的学院数据
	 * @author hly
	 * 2015.07.27
	 */
	@Override
	public List<SchoolAcademy> getAllSchoolAcademy(Integer currpage, Integer pageSize) {
		StringBuffer hql = new StringBuffer("select a from SchoolAcademy a");
		
		return schoolAcademyDAO.executeQuery(hql.toString(), (currpage-1)*pageSize, pageSize);
	}

	/**
	 * 获取所有的校区数据
	 * @author hly
	 * 2015.07.27
	 */
	@Override
	public List<SystemCampus> getAllSystemCampus(Integer currpage, Integer pageSize) {
		StringBuffer hql = new StringBuffer("select c from SystemCampus c");
		
		return systemCampusDAO.executeQuery(hql.toString(), (currpage-1)*pageSize, pageSize);
	}

	/**
	 * 根据查询条件获取所有的用户数据
	 * @author hly
	 * 2015.07.27
	 */
	@Override
	public List<User> getAllUser(Integer currpage, Integer pageSize, User user) {
		StringBuffer hql = new StringBuffer("select u from User u where 1=1 ");
		if(user.getUserRole()!=null && !"".equals(user.getUserRole()))
		{
			hql.append(" and u.userRole='"+user.getUserRole()+"'");  //用户角色
		}
		if(user.getSchoolAcademy()!=null && user.getSchoolAcademy().getAcademyNumber()!=null && !"".equals(user.getSchoolAcademy().getAcademyNumber()))
		{
			hql.append(" and u.schoolAcademy.academyNumber='"+user.getSchoolAcademy().getAcademyNumber()+"'");//用户所在学院
			
		}
		
		return userDAO.executeQuery(hql.toString(), (currpage-1)*pageSize, pageSize);
	}
	
	/**
	 * 根据查询条件获取所有的用户数据--获取相关权限的用户
	 * @author 贺子龙
	 * 2015-11-20
	 */
	public List<User> getUserByAuthority(User user,int authorityId){
		StringBuffer hql = new StringBuffer("select u from User u where 1=1 ");
		if(user.getSchoolAcademy()!=null && user.getSchoolAcademy().getAcademyNumber()!=null && !"".equals(user.getSchoolAcademy().getAcademyNumber()))
		{
			hql.append(" and u.schoolAcademy.academyNumber='"+user.getSchoolAcademy().getAcademyNumber()+"'");//用户所在学院
			
		}
		List<User> userList=userDAO.executeQuery(hql.toString(), 0,-1);
		//判断当前登陆人是否为指定权限
		List<User> departmentHeaders=new ArrayList();
		for (User user2 : userList) {
			String judge=",";
			for(Authority authority:user2.getAuthorities()){
				judge = judge + "," + authority.getId() + "," ;
			}
			if(judge.indexOf(","+authorityId+",")>-1){//18--系主任
				departmentHeaders.add(user2);
			}
		}
		return departmentHeaders;
	}

	/**
	 * 获取所有的楼宇信息
	 * @author hly
	 * 2015.07.27
	 */
	@Override
	public List<SystemBuild> getAllSystemBuild(Integer currpage, Integer pageSize) {
		StringBuffer hql = new StringBuffer("select b from SystemBuild b ");
		
		return systemBuildDAO.executeQuery(hql.toString(), (currpage-1)*pageSize, pageSize);
	}

	/**
	 * 获取12版学科数据
	 * @author hly
	 * 2015.08.04
	 */
	@Override
	public List<SystemSubject12> getAllSystemSubject12(Integer currpage, Integer pageSize) {
		StringBuffer hql = new StringBuffer("select s from SystemSubject12 s");
		
		return systemSubject12DAO.executeQuery(hql.toString(), (currpage-1)*pageSize, pageSize);
	}

	/**
	 * 获取12版专业数据
	 * @author hly
	 * 2015.08.04
	 */
	@Override
	public List<SystemMajor12> getAllSystemMajor12(Integer currpage, Integer pageSize) {
		StringBuffer hql = new StringBuffer("select m from SystemMajor12 m");
		
		return systemMajor12DAO.executeQuery(hql.toString(), (currpage-1)*pageSize, pageSize);
	}

	/**
	 * 根据专业编号获取专业信息
	 * @author hly
	 * 2015.08.06
	 */
	@Override
	public SystemMajor12 findSystemMajor12ByNumber(String MNumber) {
		return systemMajor12DAO.findSystemMajor12ByPrimaryKey(MNumber);
	}

	@Override
	public List<User> getUsersByAuthorityId(User user, Integer AuthorityId) {
		// TODO Auto-generated method stub
		StringBuffer hql = new StringBuffer("select u from User u join u.authorities a where a.id = '"+AuthorityId+"' ");
		if(user.getSchoolAcademy()!=null && user.getSchoolAcademy().getAcademyNumber()!=null && !"".equals(user.getSchoolAcademy().getAcademyNumber()))
		{
			hql.append(" and u.schoolAcademy.academyNumber='"+user.getSchoolAcademy().getAcademyNumber()+"'");//用户所在学院
			
		}
		List<User> users = userDAO.executeQuery(hql.toString(), 0, -1);
		return users;
	}
	
	@Override
	public List<User> getAllJPAUser(Integer currpage, Integer pageSize, User user) {
		String sql = "select u.username,u.cname from user u where 1=1 ";
		if(user.getUserRole()!=null && !"".equals(user.getUserRole()))
		{
			sql+=" and u.user_role='"+user.getUserRole()+"'";  //用户角色
		}
		if(user.getSchoolAcademy()!=null && user.getSchoolAcademy().getAcademyNumber()!=null && !"".equals(user.getSchoolAcademy().getAcademyNumber()))
		{
			sql+=" and u.academy_number='"+user.getSchoolAcademy().getAcademyNumber()+"'";//用户所在学院
			
		}
		List<Object> objects = entityManager.createNativeQuery(sql).getResultList();
		List<User> users = new ArrayList();
		for (Object object : objects) {
			Object[] objs = (Object[])object;
			User u = new User();
			u.setUsername((String)objs[0]);
			u.setCname((String)objs[1]);
			users.add(u);
		}
		
		return users;
	}
	
	/**********************************
	 * 功能：获取节次对应时间
	 * 作者：缪军
	 * 日期：2017-07-18
	 **********************************/
	public Map<String, SystemTime> getAllTimebyjieci(){
		Set<SystemTime> systemTimeSet = systemTimeDAO.findAllSystemTimes();
		Map<String, SystemTime> map=new HashMap<String, SystemTime>();
		for (SystemTime systemTime : systemTimeSet) {
			map.put(systemTime.getSectionName(), systemTime);
		}
		return map;
	}

}
