package net.xidlims.service.system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.dao.UserDAO;
import net.xidlims.domain.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("UserDetailService")
public class UserDetailServiceImpl implements UserDetailService {
	@Autowired
	private UserDAO userDAO;
	@PersistenceContext
	private EntityManager entityManager;
	
	/*************************************************************************************
	 * @內容：获取用户的总记录数
	 * @作者： 叶明盾
	 * @日期：2014-08-14
	 *************************************************************************************/
	@Transactional
	public int getUserTotalRecords(String number){
		//得出用户数量（由于用户的数据量比较多，不能够使用userDAO.findAllUsers()方法查找用户）
		String sql="select count(u) from User u where  u.userStatus='1' and u.schoolAcademy.academyNumber like '"+number+"%'";
		return  ((Long) userDAO.createQuerySingleResult(sql).getSingleResult()).intValue();
	}
	
	/*************************************************************************************
	 * @內容：获取用户的总记录数
	 * @作者： 叶明盾
	 * @日期：2014-08-14
	 *************************************************************************************/
	@Transactional
	public int getUserTotalRecords(User user,String number){
		//得出用户数量（由于用户的数据量比较多，不能够使用userDAO.findAllUsers()方法查找用户）
		//String sql="select count(u) from User u where u.schoolAcademy.academyNumber like '"+number+"%'";
		String sql="select count(u) from User u where 1=1";
		if(user!=null&&user.getUsername() != null){
			sql+=" and u.username='"+user.getUsername()+"' ";
		}
		return  ((Long) userDAO.createQuerySingleResult(sql).getSingleResult()).intValue();
	}
	
	/*************************************************************************************
	 * @內容：查找所有的用户信息
	 * @作者： 叶明盾
	 * @日期：2014-08-14
	 *************************************************************************************/
	public List<User> findAllUsers(int curr, int size){
		//利用sql语句从用户表中查找出所有的用户，并赋给StringBuffer类型的sb变量
		StringBuffer sb= new StringBuffer("select u from User u where 1=1");
		//给语句添加分页机制
		List<User> users=userDAO.executeQuery(sb.toString(), curr*size, size);
		return users;
	}
	
	/*************************************************************************************
	 * @內容：查找指定学院所有的用户信息
	 * @作者： 何立友
	 * @日期：2014-09-24
	 *************************************************************************************/
	public List<User> findUsersByAcademy(String academyNumber){
		StringBuffer hql = new StringBuffer("select u from User u where u.userStatus='1' ");
		if(academyNumber != null && !"".equals(academyNumber))
		{
			//hql.append(" and u.schoolAcademy.academyNumber like '"+academyNumber+"%' ");
		}
		
		return userDAO.executeQuery(hql.toString(), 0, -1);
	}
	
	/*************************************************************************************
	 * @內容：获取在校用户user的分页列表信息
	 * @作者： 叶明盾
	 * @日期：2014-08-18
	 *************************************************************************************/
	public List<User> findUserByQuery(User user, int curr, int size,String number) {
		String query = "";
		//判断获取的用户的信息是否为空
		if (user.getUsername() != null) {
			query = query + " and u.username like '%" + user.getUsername() + "%'";
		}
		// 查询用户表；
		StringBuffer sql = new StringBuffer("select u from User u where u.userStatus='1' and u.schoolAcademy.academyNumber like '"+number+"%'");
		// 将query添加到sb1后
		sql.append(query);
		sql.append(" order by u.username");
	    // 执行sb语句
		List<User> users = userDAO.executeQuery(sql.toString(),curr*size,size);
		return users;
	}
	
	/*************************************************************************************
	 * @內容：获取查询的用户数
	 * @作者： 何立友
	 * @日期：2014-09-23
	 *************************************************************************************/
	public int getUserTotalRecordsByQuery(String queryStr)
	{
		StringBuffer hql = new StringBuffer("select count(u) from User u where 1=1 ");
		if(queryStr != null && !"".equals(queryStr))
		{
			hql.append(" and u.username like '%"+queryStr+"%'");
		}
		
		return ((Long) userDAO.createQuerySingleResult(hql.toString()).getSingleResult()).intValue();
	}
	
	/*************************************************************************************
	 * @內容：根据用户的Username查找出相应的用户信息
	 * @作者： 叶明盾
	 * @日期：2014-09-02
	 *************************************************************************************/
	public User findUserByNum(String num) {
		return userDAO.findUserByUsername(num);
	}
	
	/*************************************************************************************
	* @內容：根据学院编号和user对象查询User并分页
	* @作者： 李小龙
	*************************************************************************************/
	@Override
	public List<User> getUserTotalRecords(User user, String number,int currpage, int pageSize) {
		//得出用户数量（由于用户的数据量比较多，不能够使用userDAO.findAllUsers()方法查找用户）
		//String sql="select u from User u where u.schoolAcademy.academyNumber like '"+number+"%'";
		String sql="select u from User u where 1=1";
		if(user!=null&&user.getUsername() != null){
			sql+=" and u.username='"+user.getUsername()+"' ";
		}
		return  userDAO.executeQuery(sql, (currpage-1)*pageSize,pageSize);
	}

	@Override
	public List<User> findAllUsers(String userRole) {
		// TODO Auto-generated method stub
		String sql = "select username,cname from `user` where enabled = '1'";
		if (userRole!=null) {
			sql += " and user_role = '"+userRole+"'";
		}
		List<Object> objects = entityManager.createNativeQuery(sql).getResultList();
		List<User> users = new ArrayList<User>();
		for (Object object : objects) {
			Object[] objs = (Object[])object;
			User user = new User();
			user.setUsername((String)objs[0]);
			user.setCname((String)objs[1]);
			users.add(user);
		}
		return users;
	}
	
	/**
	 * 获取当前学年下的本学院学生
	 * 裴继超
	 * 2016年7月12日17:18:32
	 */
	@Override
	public List<User> findUsersByAcademyAndGrade(String academyNumber, int year) {
		// TODO Auto-generated method stub
		String sql = "select c from User c where c.schoolAcademy.academyNumber like '" + academyNumber + "' and grade like '"+ year + "'";
		List<User> users = userDAO.executeQuery(sql, 0, -1);
		return users;
	}

	
	/**
	 * 获取当前学院下的学年列表
	 * 裴继超
	 * 2016年7月12日17:18:32
	 */
	@Override
	public List<User> findGradesByAcademy(String academyNumber) {
		// TODO Auto-generated method stub
		String sql = "select c from User c where c.schoolAcademy.academyNumber like '"+ academyNumber + "' group by c.grade";
		List<User> grades = userDAO.executeQuery(sql, 0, -1);
		return grades;
	}
	
	/**************************************************************************
	 * Description:系统管理-用户管理-获取用户的总记录数
	 * 
	 * @author：于侃 
	 * @date ：2016-08-24
	 **************************************************************************/
	@Transactional
	public int getUserTotalRecords(User user){
		String sql="select count(u) from User u where 1=1 and u.userStatus = 1 ";
		if(user!=null&&user.getUsername() != null){
			sql+=" and u.username like '%"+user.getUsername()+"%'";
		}
		if(user!=null&&user.getCname() != null){
			sql+=" and u.cname like '%"+user.getCname()+"%'";
		}
		if(user!=null&&user.getSchoolAcademy()!= null&&user.getSchoolAcademy().getAcademyNumber()!="-1"){
			sql+=" and u.schoolAcademy.academyNumber like '%"+user.getSchoolAcademy().getAcademyNumber()+"%'";
		}
		return  ((Long) userDAO.createQuerySingleResult(sql).getSingleResult()).intValue();
	}

	/**************************************************************************
	 * Description:系统管理-用户管理-查找所有的用户信息
	 * 
	 * @author：于侃 
	 * @date ：2016-08-24
	 **************************************************************************/
	public List<User> findUsers(){
		StringBuffer hql = new StringBuffer("select u from User u where u.userStatus='1' ");
		return userDAO.executeQuery(hql.toString(), 0, -1);
	}
	
	/**************************************************************************
	 * Description:系统管理-用户管理-根据user对象查询User并分页
	 * 
	 * @author：于侃 
	 * @date ：2016-08-24
	 **************************************************************************/
	public List<User> getUserTotalRecords(User user,int currpage, int pageSize) {
		String sql="select u from User u where 1=1 and u.userStatus = 1";
		if(user!=null&&user.getUsername() != null){
			sql+=" and u.username like '%"+user.getUsername()+"%'";
		}
		if(user!=null&&user.getCname() != null){
			sql+=" and u.cname like '%"+user.getCname()+"%'";
		}
		if(user!=null&&user.getSchoolAcademy()!= null&&!user.getSchoolAcademy().getAcademyNumber().equals("-1")){
			sql+=" and u.schoolAcademy.academyNumber like '%"+user.getSchoolAcademy().getAcademyNumber()+"%'";
		}
		sql+="order by u.createdAt desc";
		return  userDAO.executeQuery(sql, (currpage-1)*pageSize,pageSize);
	}
	
	/**************************************************************************
	 * Description:系统管理-用户管理-新建User对象
	 * 
	 * @author：于侃 
	 * @date ：2016-08-24
	 **************************************************************************/
	public void saveUser(User user){
		userDAO.store(user);
		userDAO.flush();
	}
	/*************************************************************************************
	 * Description:进行人员过滤的ajax调用
	 *
	 * @author：戴昊宇
	 * @date：2017-08-21
	 *************************************************************************************/
	@SuppressWarnings("unchecked")
	public List<Map<String,String>> coolSuggestTuser(String tUserName) {
		//获取批次列表
		
		String sql= "select sd.username,sd.cname from user sd "
				+ "where username like '%" + tUserName + "%' or cname like '%"+ tUserName + "%' limit 20";

		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> list =query.getResultList();
		// 返回可用的信息
		List<Map<String,String>> returnList = new ArrayList<Map<String,String>>();
		// 遍历实验工种
		for (Object[] object : list) {
			Map<String,String> map = new HashMap<String,String>();
			map.put("id", (String) object[0]);
			map.put("data",  (String) object[1]+(String) object[0]);
			returnList.add(map);
		}
		return returnList;
	}
}