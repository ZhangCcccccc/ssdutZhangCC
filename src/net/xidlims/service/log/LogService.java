package net.xidlims.service.log;

import java.util.Date;
import java.util.List;

import net.xidlims.domain.Log;
import net.xidlims.domain.User;


public interface LogService {


	/**
	 * @功能：保存日志
	 * @作者：裴继超
	 * @时间：2016年7月9日14:27:22
	 */ 
	public void saveLog(Log log);

	/**
	 * @功能：根据主键查询日志
	 * @作者：裴继超
	 * @时间：2016年7月9日14:27:22
	 */
	public Log findLogByPrimaryKey(Integer id);

	/*************************************************************************************
	 * @內容：删除日志
	 * @作者：裴继超
	 * @时间：2016年7月9日14:27:22
	 *************************************************************************************/
	public void deleteLog(Log log);
	
	/**
	 * @功能：查询日志
	 * @作者：裴继超
	 * @时间：2016年7月9日14:27:22
	 */
	public List<Log> findLog(String userid,String module,String action,String data,
			String startTime, String endTime);

	/**
	 * @功能：查询日志数量
	 * @作者：裴继超
	 * @日期：2016年7月9日14:51:08
	 */
	public int findLogSize(String userid,String module,String action,String data,
			String startTime, String endTime);
	
	/**
	 * @功能：查询日志用户数量
	 * @作者：裴继超
	 * @日期：2016年7月9日14:51:08
	 */
	public int findLogUsersSize(String userid,String module,String action,String data,
			String startTime, String endTime);
	
	/**
	 * @功能：查询站点成员访问站点数量
	 * @作者：裴继超
	 * @日期：2016年7月9日14:51:08
	 */
	public int findSiteLogUsersSize(String userid,String module,String action,String data,
			String startTime, String endTime);
	
	/**
	 * @功能：查询访问最多的模块
	 * @作者：裴继超
	 * @日期：2016年7月9日14:51:08
	 */
	public List<Object> findMostSiteEventLogsSize(String userid,String module,String action,String data,
			String startTime, String endTime);
	
	/**
	 * @功能：学习行为-获取最近7天
	 * @作者：裴继超
	 * @日期：2016年8月4日15:12:52
	 */
    public List<Date> dateToWeek(Date mdate,int daySize) ;


}

