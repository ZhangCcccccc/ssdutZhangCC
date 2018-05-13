package net.xidlims.service.system;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.xidlims.domain.SystemLog;
import net.xidlims.domain.User;


/**
 * Spring service that handles CRUD requests for SystemLog entities
 * 
 */
public interface SystemLogService {
	
	
	/***********************************************************************************************
	 * 功能：保存项目卡片操作的日志
	 * 作者：贺子龙
	 * 日期：2015-12-19
	 * userIp：ip地址   tag：子模块标志位  0-我的实验项目  1-实验项目管理  2-实验项目导入  id：项目卡的id  0-查看所有   "-1"-新建
	 ***********************************************************************************************/
	public void saveOperationItemLog(String userIp, int tag, int action, int id);
	/***********************************************************************************************
	 * 功能：查询系统日志
	 * 作者：贺子龙
	 * 日期：2015-12-19
	 ***********************************************************************************************/
	public List<SystemLog> findSystemLogs(SystemLog systemLog,int cid,int currpage,int pageSize,HttpServletRequest request);
	/**********************************
	 * 功能：查询systemLog表中的用户详细字段
	 * 作者：贺子龙
	 * 时间：2015-12-19
	 *********************************/
	public Map<String, String> getUserMap(int cid);
	/**********************************
	 * 功能：删除系统日志
	 * 作者：贺子龙
	 * 时间：2015-12-22
	 *********************************/
	public void deleteSystemLog(String logIds);
	/**********************************
	 * 功能：查找当前用户默认选择的实验中心
	 * 作者：罗璇
	 * 时间：2017年3月10日
	 *********************************/
	public SystemLog findDefaultCenterByUsernameAndDetail(String username, String detail);
	/**********************************
	 * 功能：新建当前用户默认选择的实验中心
	 * 作者：罗璇
	 * 时间：2017年3月10日
	 *********************************/
	public void saveDefaultCenterLog(User user, Integer centerId, SystemLog systemLog);
}