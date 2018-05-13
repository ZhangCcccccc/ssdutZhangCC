package net.xidlims.service.system;

import java.util.List;
import java.util.Map;

import net.xidlims.domain.SchoolAcademy;
import net.xidlims.domain.SystemCampus;
import net.xidlims.domain.SystemMajor12;
import net.xidlims.domain.SystemSubject12;
import net.xidlims.domain.SystemTime;
import net.xidlims.domain.User;
import net.xidlims.domain.SystemBuild;

public interface SystemService {

	/**
	 * 获取所有的学院数据
	 * @author hly
	 * 2015.07.27
	 */
	public List<SchoolAcademy> getAllSchoolAcademy(Integer currpage, Integer pageSize);
	
	/**
	 * 获取所有的校区数据
	 * @author hly
	 * 2015.07.27
	 */
	public List<SystemCampus> getAllSystemCampus(Integer currpage, Integer pageSize);
	
	/**
	 * 根据查询条件获取所有的用户数据
	 * @author hly
	 * 2015.07.27
	 */
	public List<User> getAllUser(Integer currpage, Integer pageSize, User user);
	
	/**
	 * 根据查询条件获取所有的用户数据--获取相关权限的用户
	 * @author 贺子龙
	 * 2015-11-20
	 */
	public List<User> getUserByAuthority(User user,int authorityId);
	
	/**
	 * 获取所有的楼宇信息
	 * @author hly
	 * 2015.07.27
	 */
	public List<SystemBuild> getAllSystemBuild(Integer currpage, Integer pageSize);
	
	/**
	 * 获取12版学科数据
	 * @author hly
	 * 2015.08.04
	 */
	public List<SystemSubject12> getAllSystemSubject12(Integer currpage, Integer pageSize);
	
	/**
	 * 获取12版专业数据
	 * @author hly
	 * 2015.08.04
	 */
	public List<SystemMajor12> getAllSystemMajor12(Integer currpage, Integer pageSize);
	
	/**
	 * 根据专业编号获取专业信息
	 * @author hly
	 * 2015.08.06
	 */
	public SystemMajor12 findSystemMajor12ByNumber(String MNumber);

	public List<User> getUsersByAuthorityId(User user, Integer AuthorityId);

	public List<User> getAllJPAUser(Integer currpage, Integer pageSize, User user);
	/**********************************
	 * 功能：获取节次对应时间
	 * 作者：缪军
	 * 日期：2017-07-18
	 **********************************/
	public Map<String, SystemTime> getAllTimebyjieci();
}
