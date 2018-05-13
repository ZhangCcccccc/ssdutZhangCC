package net.xidlims.service.log;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import net.xidlims.dao.TCourseSiteDAO;
import net.xidlims.dao.SchoolClassesDAO;
import net.xidlims.dao.LogDAO;
import net.xidlims.dao.UserDAO;
import net.xidlims.domain.SchoolClasses;
import net.xidlims.domain.Log;
import net.xidlims.domain.User;
import net.xidlims.service.common.ShareService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("LogService")
public class LogServiceImpl implements LogService {

	@Autowired
	private LogDAO logDAO;
	@Autowired
	private SchoolClassesDAO schoolClassesDAO;
	@Autowired
	private TCourseSiteDAO tCourseSiteDAO;
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private ShareService shareService;
	@PersistenceContext  
    private EntityManager entityManager;
	

	/**
	 * @功能：保存日志
	 * @作者：裴继超
	 * @日期：2016年7月9日14:51:08
	 */
	@Override
	public void saveLog(Log log) {
		// TODO Auto-generated method stub
		
		logDAO.store(log);
	}

	/**
	 * @功能：根据主键查询日志
	 * @作者：裴继超
	 * @日期：2016年7月9日14:51:08
	 */
	@Override
	public Log findLogByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return logDAO.findLogByPrimaryKey(id);
	}

	/*************************************************************************************
	 * @內容：删除日志
	 * @作者：裴继超
	 * @日期：2016年7月9日14:51:08
	 *************************************************************************************/
	@Override
	public void deleteLog(Log log) {
		// TODO Auto-generated method stub
		logDAO.remove(log);
	}
	
	/**
	 * @功能：查询日志
	 * @作者：裴继超
	 * @日期：2016年7月9日14:51:08
	 */
	@Override
	public List<Log> findLog(String userid,String module,String action,String data,
			String startTime, String endTime) {
		// TODO Auto-generated method stub
		String sql = "select l from Log l where ";
		if(userid!=null&&userid!=""){
			sql = sql + "l.userid like '" + userid + "' ";
		}
		if(module!=null&&module!=""){
			sql = sql + "l.module like '" + module + "' ";
		}
		if(action!=null&&action!=""){
			sql = sql + "l.action like '" + action + "' ";
		}
		if(data!=null&&data!=""){
			sql = sql + "l.data like '%tCourseSiteId=" + data + "%' ";
		}
		sql = sql + " l.createTime between '"+startTime+"' and '"+endTime+"' ";
		return logDAO.executeQuery(sql.toString(), 0, -1);
	}
	
	/**
	 * @功能：查询日志数量
	 * @作者：裴继超
	 * @日期：2016年7月9日14:51:08
	 */
	@Override
	public int findLogSize(String userid,String module,String action,String data,
			String startTime, String endTime) {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer("select count(*) from Log l where 1=1 ");
		if(userid!=null&&userid!=""){
			sql.append("and l.userid like '" + userid + "' ");
		}
		if(module!=null&&module!=""){
			sql.append("and l.module like '" + module + "' ");
		}
		if(action!=null&&action!=""){
			sql.append("and l.action like '" + action + "' ");
		}
		if(data!=null&&data!=""){
			sql.append("and l.data like '%tCourseSiteId=" + data + "%' ");
		}
		//sql.append("and  l.createTime between '"+startTime+"' and '"+endTime+"' ");
		return ((Long) logDAO.createQuerySingleResult(sql.toString()).getSingleResult()).intValue();
	}
	
	/**
	 * @功能：查询日志用户数量
	 * @作者：裴继超
	 * @日期：2016年7月9日14:51:08
	 */
	@Override
	public int findLogUsersSize(String userid,String module,String action,String data,
			String startTime, String endTime){
		StringBuffer sql = new StringBuffer("select count(DISTINCT userid) as NumberOfUserids from Log l where 1=1 ");
		if(module!=null&&module!=""){
			sql.append("and l.module like '" + module + "' ");
		}
		if(action!=null&&action!=""){
			sql.append("and l.action like '" + action + "' ");
		}
		if(data!=null&&data!=""){
			sql.append("and l.data like '%tCourseSiteId=" + data + "%' ");
		}
		return ((Long) logDAO.createQuerySingleResult(sql.toString()).getSingleResult()).intValue();
	}
	
	/**
	 * @功能：查询日志用户数量
	 * @作者：裴继超
	 * @日期：2016年7月9日14:51:08
	 */
	@Override
	public int findSiteLogUsersSize(String userid,String module,String action,String data,
			String startTime, String endTime){
		StringBuffer sql = new StringBuffer("select count(DISTINCT userid) as NumberOfUserids from Log l join TCourseSiteUser u where 1=1 ");
		if(module!=null&&module!=""){
			sql.append("and l.module like '" + module + "' ");
		}
		if(action!=null&&action!=""){
			sql.append("and l.action like '" + action + "' ");
		}
		if(data!=null&&data!=""){
			sql.append("and l.data like '%tCourseSiteId=" + data + "%' ");
		}
		return ((Long) logDAO.createQuerySingleResult(sql.toString()).getSingleResult()).intValue();
	}
	
	/**
	 * @功能：查询访问最多的模块，用户
	 * @作者：裴继超
	 * @日期：2016年7月9日14:51:08
	 */
	public List<Object> findMostSiteEventLogsSize(String userid,String module,String action,String data,
			String startTime, String endTime){
		String sql = "";
		if(userid!=null&&userid!=""){
			sql = "SELECT max(userid),COUNT(*) AS count FROM log GROUP BY userid ORDER BY count DESC";
		}
		if(module!=null&&module!=""){
			sql = "SELECT max(module),COUNT(*) AS count FROM log GROUP BY module ORDER BY count DESC";
		}
		List<Object> objects = entityManager.createNativeQuery(sql).getResultList();
		return objects;
	}
	
	/**
	 * @功能：学习行为-获取最近7天
	 * @作者：裴继超
	 * @日期：2016年8月4日15:12:52
	 */
	@Override
    public List<Date> dateToWeek(Date mdate,int daySize) {  
        Date fdate;  
        List<Date> list = new ArrayList<Date>();  
        Long fTime = mdate.getTime() - daySize * 24 * 3600000;  
        for (int a = 1; a <= 30; a++) {  
            fdate = new Date();  
            fdate.setTime(fTime + (a * 24 * 3600000));  
            list.add(a-1, fdate);  
        }  
        System.out.print(list);
        return list;  
    }  


}