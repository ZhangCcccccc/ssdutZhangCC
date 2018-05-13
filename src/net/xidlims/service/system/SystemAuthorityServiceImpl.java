package net.xidlims.service.system;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.xidlims.service.common.ShareService;
import net.xidlims.dao.AuthorityDAO;
import net.xidlims.dao.TimetableGroupStudentsDAO;
import net.xidlims.dao.UserDAO;
import net.xidlims.domain.Authority;
import net.xidlims.domain.SchoolAcademy;
import net.xidlims.domain.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("SystemAuthorityService")
public class SystemAuthorityServiceImpl implements SystemAuthorityService {
	/*@Autowired
	private AuthorityService authorityService;*/
	
	@Autowired
	private ShareService shareService;
	
	@Autowired
	private AuthorityDAO authorityDAO;
	
	@Autowired
	private UserDAO userDAO;
	

/*	@Transactional
	public List<showAcademyAuthority> getUserTotalRecords(String academyNumber){
		
		List<showAcademyAuthority> listNumber=new ArrayList<showAcademyAuthority>();
		User user=shareService.getUser();
		List<Authority> listAuthority=findAuthorityByUser(user);
		
		for (Authority authority : listAuthority){
			String hql = "select count(distinct u) from User u join u.authorities a where  a.id="+authority.getId();
			if (authority.getId()<=7||authority.getId()==13||authority.getId()==18) {//本学院
				if(academyNumber!=null&&!academyNumber.equals("")){
					hql+=" and u.schoolAcademy.academyNumber='"+academyNumber+"'";
				}
			}else {//校级
			}
			showAcademyAuthority newdate=new showAcademyAuthority();
			newdate.setAuthorityNumber(((Long)userDAO.createQuerySingleResult(hql).getSingleResult()).intValue());
			newdate.setAuthorityName(authority.getAuthorityName());
			newdate.setAuthorityCname(authority.getCname());
			newdate.setAuthorityId(authority.getId());
			listNumber.add(newdate);
		}
		
		return listNumber;
	}*/
	@Transactional
	public List<showAcademyAuthority> getUserTotalRecords(){
		
		List<showAcademyAuthority> listNumber=new ArrayList<showAcademyAuthority>();
		User user=shareService.getUser();
		List<Authority> listAuthority=findAuthorityByUser(user);
		
		for (Authority authority : listAuthority){
			String hql = "select count(distinct u) from User u join u.authorities a where  a.id="+authority.getId();
			showAcademyAuthority newdate=new showAcademyAuthority();
			newdate.setAuthorityNumber(((Long)userDAO.createQuerySingleResult(hql).getSingleResult()).intValue());
			newdate.setAuthorityName(authority.getAuthorityName());
			newdate.setAuthorityCname(authority.getCname());
			newdate.setAuthorityId(authority.getId());
			listNumber.add(newdate);
		}
		
		return listNumber;
	}
	@Transactional
	public List<User> getUserByAuthority(int id){
		
	    String hql = "select distinct u from User u join u.authorities a where u.schoolAcademy.academyNumber='"+shareService.getUser().getSchoolAcademy().getAcademyNumber()+"' and u.userStatus=1 and a.id="+id;

		return userDAO.executeQuery(hql,0,-1);
	}
	/***********************************************************************************************
	 * 根据user对象和权限id以及学院编号查询用户数量
	 * 作者：李小龙
	 ***********************************************************************************************/
/*	@Transactional
	public int findUserByUser(User user,int Id,String academyNumber) {
		// TODO Auto-generated method stub
		String sql="select count(distinct u) from User u join u.authorities a where a.id="+Id;
		if (Id<=7||Id==13||Id==18) {//本学院
			if(academyNumber!=null&&!academyNumber.equals("")){
				sql+=" and u.schoolAcademy.academyNumber='"+academyNumber+"'";
			}
		}else {//校级
		}
		
		if(user!=null){
			if(user.getCname()!=null&&!user.getCname().equalsIgnoreCase("")){
			
				sql+=" and u.cname like '%"+user.getCname()+"%'";
			}
		}
		return ((Long)userDAO.createQuerySingleResult(sql).getSingleResult()).intValue();
	}*/
	@Transactional
	public int findUserByUser(User user,int Id) {
		// TODO Auto-generated method stub
		String sql="select count(distinct u) from User u join u.authorities a where a.id="+Id;
		
		if(user!=null){
			if(user.getCname()!=null&&!user.getCname().equalsIgnoreCase("")){
			
				sql+=" and u.cname like '%"+user.getCname()+"%'";
			}
		}
		return ((Long)userDAO.createQuerySingleResult(sql).getSingleResult()).intValue();
	}
	/***********************************************************************************************
	 * 根据user对象和权限id以及学院编号查询用户数量
	 * 作者：李小龙
	 ***********************************************************************************************/
	/*@Transactional
	public int findUserByUser(User user,int Id,String academyNumber) {
		// TODO Auto-generated method stub
		String sql="select count(distinct u) from User u join u.authorities a where a.id="+Id;
		if (Id<=7||Id==13||Id==18) {//本学院
			if(academyNumber!=null&&!academyNumber.equals("")){
				sql+=" and u.schoolAcademy.academyNumber='"+academyNumber+"'";
			}
		}else {//校级
		}
		
		if(user!=null){
			if(user.getCname()!=null&&!user.getCname().equalsIgnoreCase("")){
			
				sql+=" and u.cname like '%"+user.getCname()+"%'";
			}
		}
		return ((Long)userDAO.createQuerySingleResult(sql).getSingleResult()).intValue();
	}*/
	/***********************************************************************************************
	 * 根据user对象和权限id以及学院编号查询用户 并分页
	 * 作者：李小龙
	 ***********************************************************************************************/
	@Transactional
	public List<User> findUserByUser(User user, int page,int pageSize,int Id) {
		// TODO Auto-generated method stub
				String sql="select distinct u from User u join u.authorities a where  a.id="+Id;
				
				if(user!=null){
					if(user.getCname()!=null&&!user.getCname().equalsIgnoreCase("")){
					
						sql+=" and u.cname like '%"+user.getCname()+"%'";
					}
				}
		
		List<User> users=userDAO.executeQuery(sql,(page-1)*pageSize,pageSize);
		return users;
	}
	
	@Transactional
	public void deleteUserAuthority(User user,Authority authority){
		user.getAuthorities().remove(authority);
		authorityDAO.store(authority);
		
		userDAO.store(user);	
	}
	
	@Transactional
	public void saveUserAuthority(User user,Authority authority){
		user.getAuthorities().add(authority);
		authorityDAO.store(authority);
		
		userDAO.store(user);	
		
	}
	
	@Transactional
	public List<User> findAllAcademyMap(int Id){
		String sql="select u from User u where u.schoolAcademy.academyNumber='"+shareService.getUser().getSchoolAcademy().getAcademyNumber()+"' and u.userStatus=1";

		return userDAO.executeQuery(sql,0,-1);
	}
	
	@Transactional
	public void saveUserAuth(String userId, int authorities) {
		
		Set<Authority> currentAuthorities=new HashSet<Authority>();
		
		User user=userDAO.findUserByPrimaryKey(userId);
		//用户已有的权限
		Set<Authority> historyAuthorities=user.getAuthorities();
					
		Authority authority=new Authority();
		if (authorities!=0)
		{
				authority=authorityDAO.findAuthorityById(authorities);
				currentAuthorities.add(authority);	
		}
		if(historyAuthorities.contains(authority)){//已经有的权限包含要增加的权限
			
		}else{
			historyAuthorities.add(authority);
			
		}
		
		user.setAuthorities(historyAuthorities);
		userDAO.store(user);
		
	}
	/***********************************************************************************************
	 * 根据用户判断用户可管理的权限
	 * 作者：李小龙
	 ***********************************************************************************************/
	@Override
	public List<Authority> findAuthorityByUser(User user) {
		int type=1;
		Set<Authority> auths=user.getAuthorities();
		for (Authority a : auths) {
			if(a.getType()>type){
				type=a.getType();
			}
		}
		String sql="select a from Authority a where 1=1";//a.type<="+type;  贺子龙
		return authorityDAO.executeQuery(sql, 0,-1);
	}
	/***********************************************************************************************
	 * 根据权限id和用户名、工号查询可添加权限的用户数量
	 * 作者：李小龙
	 ***********************************************************************************************/
	@Override
	public int findUserByCnameAndUsername(String cname, String username,
			Integer authorityId) {
		// TODO Auto-generated method stub
		String sql="select count(u) from User u where 1=1";
		if(cname!=null&&!cname.equals("")){
			sql+=" and u.cname like '%"+cname+"%'";
		}
		 if(username!=null&&!username.equals("")){
			sql+=" and u.username like '%"+username+"%'";
		}
		
		 if(authorityId!=null&&authorityId==1){
			sql+=" and u.username in(select u.username from User u join u.authorities a where a.id="+authorityId+")";
			
		}
		 else if(authorityId!=null&&authorityId==2){
			sql+=" and u.username in(select u.username from User u join u.authorities a where a.id="+authorityId+")";
			
		}
		 else
			sql+="and u.userRole=1";
		
		
		//非12个学院的用户	
		sql+=" or u.schoolAcademy.academyNumber  in(select s.academyNumber from  SchoolAcademy s where s.academyNumber like '02__')";
	
		return ((Long) userDAO.createQuerySingleResult(sql).getSingleResult()).intValue();
	}
	/***********************************************************************************************
	 * 根据权限id和用户名、工号查询，权限进行查询并分页
	 * @date ：2017-08-25
	 * 作者：郭明杰
	 ***********************************************************************************************/
	@Override
	public List<User> findUserByUserAndSchoolAcademy(String cname,
			String username, Integer authorityId,
			Integer page, int pageSize) {
		// TODO Auto-generated method stub
		
		String sql="select u from User u where 1=1";

		if(cname!=null&&!cname.equals("")){
			sql+=" and u.cname like '%"+cname+"%'";
		}
		if(username!=null&&!username.equals("")){
			sql+=" and u.username like '%"+username+"%'";
		}
		if(authorityId!=null&&authorityId==1){
			sql+=" and u.username in(select u.username from User u join u.authorities a where a.id="+authorityId+")";
			
		}
		else if(authorityId!=null&&authorityId==2){
			sql+=" and u.username in(select u.username from User u join u.authorities a where a.id="+authorityId+")";			
		}
		else
			sql+="and u.userRole=1";
		
		
		//非12个学院的用户
		sql+=" or u.schoolAcademy.academyNumber in(select s.academyNumber from  SchoolAcademy s where s.academyNumber like '02__')";
		//System.out.println(sql);
		return userDAO.executeQuery(sql, (page-1)*pageSize,pageSize);
	}
	
	/**************************************************************************
	 * Description:系统管理-权限管理-读取对应权限人员列表
	 * 
	 * @author：于侃
	 * @date ：2016-08-24
	 **************************************************************************/
	public List<showAcademyAuthority> getUserRecords(){
		List<showAcademyAuthority> listNumber=new ArrayList<showAcademyAuthority>();
		User user=shareService.getUser();
		List<Authority> listAuthority=findAuthorityByUser(user);
		
		for (Authority authority : listAuthority){
			String hql = "select count(distinct u) from User u join u.authorities a where  a.id="+authority.getId();
			showAcademyAuthority newdate=new showAcademyAuthority();
			newdate.setAuthorityNumber(((Long)userDAO.createQuerySingleResult(hql).getSingleResult()).intValue());
			newdate.setAuthorityName(authority.getAuthorityName());
			newdate.setAuthorityCname(authority.getCname());
			newdate.setAuthorityId(authority.getId());
			listNumber.add(newdate);
		}
		
		return listNumber;
	}
	
	/**************************************************************************
	 * Description:系统管理-权限管理-根据user对象和权限id查询用户数量
	 * 
	 * @author：于侃
	 * @date ：2016-08-24
	 **************************************************************************/
	public int findUserByIdAndUser(User user,int Id) {
		// TODO Auto-generated method stub
		String sql="select count(distinct u) from User u join u.authorities a where a.id="+Id;
		if(user!=null){
			if(user.getCname()!=null&&!user.getCname().equalsIgnoreCase("")){
			
				sql+=" and u.cname like '%"+user.getCname()+"%'";
			}
		}
		return ((Long)userDAO.createQuerySingleResult(sql).getSingleResult()).intValue();
	}
	
	/**************************************************************************
	 * Description:系统管理-权限管理-根据user对象和权限id查询用户 并分页
	 * 
	 * @author：于侃
	 * @date ：2016-08-24
	 **************************************************************************/
	public List<User> findUserByIdAndUser(User user, int page,int pageSize,int Id) {
		// TODO Auto-generated method stub
				String sql="select distinct u from User u join u.authorities a where  a.id="+Id;
				if(user!=null){
					if(user.getCname()!=null&&!user.getCname().equalsIgnoreCase("")){
					
						sql+=" and u.cname like '%"+user.getCname()+"%'";
					}
				}
		List<User> users=userDAO.executeQuery(sql,(page-1)*pageSize,pageSize);
		return users;
	}

	/**************************************************************************
	 * Description:系统管理-权限管理-根据权限id和用户名、工号查询可添加权限的用户数量
	 * 
	 * @author：于侃
	 * @date ：2016-08-24
	 **************************************************************************/
	public int findUserByCnameAndUname(String cname, String username,Integer authorityId
			, String academyNumber) {
			String sql="select count(u) from User u where 1=1";
			if (authorityId<=7||authorityId==13||authorityId==18) {//本学院
				if(academyNumber!=null&&!academyNumber.equals("")){
					sql+=" and u.schoolAcademy.academyNumber='"+academyNumber+"'";
				}
			}else {//校级
			}
			if(cname!=null&&!cname.equals("")){
				sql+=" and u.cname like '%"+cname+"%'";
			}
			if(username!=null&&!username.equals("")){
				sql+=" and u.username like '%"+username+"%'";
			}
			if(authorityId!=null&&authorityId!=0){
				sql+=" and u.username not in(select u.username from User u join u.authorities a where a.id="+authorityId+")";
			}
			sql+=" and u.userRole=1";
			//非12个学院的用户
			sql+=" or u.schoolAcademy.academyNumber not in(select s.academyNumber from  SchoolAcademy s where s.academyNumber like '02__')";
			return ((Long) userDAO.createQuerySingleResult(sql).getSingleResult()).intValue();
		}
	
	/**************************************************************************
	 * Description:系统管理-权限管理-根据权限id和用户名、工号查询可添加权限的用户并分页 
	 * @author：于侃
	 * @date ：2016-08-24
	 **************************************************************************/
	public List<User> findUserByUserAndId(String cname,
			String username, Integer authorityId, String academyNumber,
			Integer page, int pageSize) {
		// TODO Auto-generated method stub
		
		String sql="select u from User u where 1=1";
		if (authorityId<=7||authorityId==13||authorityId==18) {//本学院
			if(academyNumber!=null&&!academyNumber.equals("")){
				sql+=" and u.schoolAcademy.academyNumber='"+academyNumber+"'";
			}
		}else {//校级
		}
		if(cname!=null&&!cname.equals("")){
			sql+=" and u.cname like '%"+cname+"%'";
		}
		if(username!=null&&!username.equals("")){
			sql+=" and u.username like '%"+username+"%'";
		}
		if(authorityId!=null&&authorityId!=0){
			sql+=" and u.username not in(select u.username from User u join u.authorities a where a.id="+authorityId+")";
		}
		sql+=" and u.userRole=1";
		//非12个学院的用户
		sql+=" or u.schoolAcademy.academyNumber not in(select s.academyNumber from  SchoolAcademy s where s.academyNumber like '02__')";
		//System.out.println(sql);
		return userDAO.executeQuery(sql, (page-1)*pageSize,pageSize);
	}
}
