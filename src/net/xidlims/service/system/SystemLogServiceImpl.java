package net.xidlims.service.system;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.xidlims.dao.LabCenterDAO;
import net.xidlims.dao.OperationItemDAO;
import net.xidlims.dao.SystemLogDAO;
import net.xidlims.domain.OperationItem;
import net.xidlims.domain.SystemLog;
import net.xidlims.domain.User;
import net.xidlims.service.common.ShareService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SystemLogService")
public class SystemLogServiceImpl implements SystemLogService {

	@Autowired private ShareService shareService;
	
	
	
	
	@Autowired private OperationItemDAO operationItemDAO;
	@Autowired private SystemLogDAO systemLogDAO;
	@Autowired private LabCenterDAO labCenterDAO;
	public SystemLogServiceImpl() {
	}

	/***********************************************************************************************
	 * 功能：保存项目卡片操作的日志
	 * 作者：贺子龙
	 * 日期：2015-12-19
	 * userIp：ip地址   tag：子模块标志位  0-我的实验项目  1-实验项目管理  2-实验项目导入  id：项目卡的id
	 ***********************************************************************************************/
	@Override
	public void saveOperationItemLog(String userIp, int tag, int action, int id){
		//获取当前登录用户
		User user=shareService.getUser();
		//用户详情：姓名+工号
		String userDetail=user.getCname()+"["+user.getUsername()+"]";
		//用户所在学院
		String userAcademy="";
		if (user.getSchoolAcademy()!=null&&!user.getSchoolAcademy().getAcademyNumber().equals("")) {
			userAcademy=user.getSchoolAcademy().getAcademyNumber();
		}
		//所属子模块
		String childModule="";
		switch (tag) 
		{
		    case 0: childModule="我的实验项目";break;
		    case 1: childModule="实验项目管理";break;
		    case 2: childModule="实验项目导入";break;
		}
		//操作动作
		String operationAction="";
		switch (action)//动作: 0 新建 1 编辑 2 查看 3 删除 4 提交 5 审核查看 6 保存 7 审核编辑后保存 8 导入 9 审核结果
		{
		    case 0: operationAction="新建";break;
		    case 1: operationAction="编辑";break;
		    case 2: operationAction="查看";break;
		    case 3: operationAction="删除";break;
		    case 4: operationAction="提交";break;
		    case 5: operationAction="审核查看";break;
		    case 6: operationAction="保存";break;
		    case 7: operationAction="审核编辑后保存";break;
		    case 8: operationAction="导入";break;
		    case 9: operationAction="审核结果";break;
		}
		//操作对象详情
		String objectiveDetail="";
		switch (id) 
		{
		    case 0: objectiveDetail=operationAction+"《"+childModule+"》"+"列表";break;//id=0 代表是查看列表页面
		    case -1:objectiveDetail=operationAction+"《"+childModule+"》";break;//id=-1 代表新建
		    default: {
		    	OperationItem operationItem=operationItemDAO.findOperationItemByPrimaryKey(id);
		    	if (operationItem!=null&&!operationItem.getLpName().equals("")) {
		    		objectiveDetail=operationAction+"——名称：《"+operationItem.getLpName()+"》";
		    		if(operationItem.getUserByLpCreateUser()!=null&&!operationItem.getUserByLpCreateUser().equals("")){
		    			objectiveDetail+="(创建者："+operationItem.getUserByLpCreateUser().getCname()+")";
		    		}
				}
		    }
		}
		//新建一个系统日志
		SystemLog log=new SystemLog();
		log.setUserDetail(userDetail);
		log.setUserAcademy(userAcademy);
		log.setUserIp(userIp);
		log.setCalendarTime(Calendar.getInstance());
		log.setSuperModule("课程实验");
		log.setChildModule(childModule);
		log.setObjectiveDetail(objectiveDetail);
		log.setOperationAction(action);
		systemLogDAO.store(log);
		systemLogDAO.flush();
	}
	/***********************************************************************************************
	 * 功能：查询系统日志
	 * 作者：贺子龙
	 * 日期：2015-12-19
	 ***********************************************************************************************/
	public List<SystemLog> findSystemLogs(SystemLog systemLog, int cid,int currpage,int pageSize,HttpServletRequest request){
		
		String academyNumber="";
        // 如果没有获取有效的实验分室列表-根据登录用户的所属学院
        if (cid != -1) {
    		//获取选择的实验中心
        	academyNumber = labCenterDAO.findLabCenterById(cid).getSchoolAcademy().getAcademyNumber();
        }else{
        	academyNumber = shareService.getUserDetail().getSchoolAcademy().getAcademyNumber();
        }
        String starttime= request.getParameter("starttime");
    	String endtime=	request.getParameter("endtime");
    	
		String sql="select s from SystemLog s where 1=1";
		
		if(starttime!=null && starttime.length()>0 && endtime!=null&& endtime.length()>0){
			sql+=" and s.calendarTime between '"+starttime +"' and '"+endtime+"' "; 
		}
		if (!academyNumber.equals("")) {
			sql+=" and s.userAcademy like '%"+academyNumber+"%'";
		}
		if (systemLog.getSuperModule()!=null&&!systemLog.getSuperModule().equals("")) {
			sql+=" and s.superModule like '%"+systemLog.getSuperModule()+"%'";
		}
		if (systemLog.getUserDetail()!=null&&!systemLog.getUserDetail().equals("")) {
			sql+=" and s.userDetail like '%"+systemLog.getUserDetail()+"%'";
		}
		if (systemLog.getOperationAction()!=null&&!systemLog.getOperationAction().equals("")) {
			sql+=" and s.operationAction like '%"+systemLog.getOperationAction()+"%'";
		}
		sql+=" order by s.calendarTime desc";
		//System.out.println(sql+"'''");
		return systemLogDAO.executeQuery(sql, (currpage-1)*pageSize, pageSize);
		
	}
	/**********************************
	 * 功能：查询systemLog表中的用户详细字段
	 * 作者：贺子龙
	 * 时间：2015-12-19
	 *********************************/
	public Map<String, String> getUserMap(int cid){
		String academyNumber="";
        // 如果没有获取有效的实验分室列表-根据登录用户的所属学院
        if (cid != -1) {
    		//获取选择的实验中心
        	academyNumber = labCenterDAO.findLabCenterById(cid).getSchoolAcademy().getAcademyNumber();
        }else{
        	academyNumber = shareService.getUserDetail().getSchoolAcademy().getAcademyNumber();
        }
		Map<String, String> map=new HashMap<String, String>();
		String sql="select s from SystemLog s where 1=1";
		if (!academyNumber.equals("")) {
			sql+=" and s.userAcademy like '%"+academyNumber+"%'";
		}
		List<SystemLog>   list=systemLogDAO.executeQuery(sql,0,-1);
		if(list.size()>0){
			for(SystemLog sl:list){
				map.put(sl.getUserDetail(),sl.getUserDetail());
			}
		}
		return map;
	}
	/**********************************
	 * 功能：删除系统日志
	 * 作者：贺子龙
	 * 时间：2015-12-22
	 *********************************/
	public void deleteSystemLog(String logIds){
		String[] ids = logIds.split(",");
		for (String string : ids) {
			SystemLog log=systemLogDAO.findSystemLogByPrimaryKey(Integer.parseInt(string));
			if(log != null)
			{
				systemLogDAO.remove(log);
				systemLogDAO.flush();
			}
		}
	}
	
	/**********************************
	 * 功能：查找当前用户默认选择的实验中心
	 * 作者：罗璇
	 * 时间：2017年3月10日
	 *********************************/
	public SystemLog findDefaultCenterByUsernameAndDetail(String username, String detail){
		String sql = "select s from SystemLog s where 1=1 ";
		//username
		sql += " and s.userDetail = '" + username + "' ";
		//操作详情
		sql += " and s.objectiveDetail = '" + detail + "' ";
		List<SystemLog> list = systemLogDAO.executeQuery(sql, 0, -1);
		if(!list.isEmpty()){
			return list.get(0);
		}else{
			return null;
		}
	}
	
	/**********************************
	 * 功能：新建当前用户默认选择的实验中心
	 * 作者：罗璇
	 * 时间：2017年3月10日
	 *********************************/
	public void saveDefaultCenterLog(User user, Integer centerId, SystemLog systemLog){
		if(systemLog == null){//若没有表里没有数据则新建
			systemLog = new SystemLog();
			//设置操作内容
			systemLog.setObjectiveDetail("默认实验中心");
			//设置操作用户
			systemLog.setUserDetail(user.getUsername());
			//设置用户用户学院
			systemLog.setUserAcademy(user.getSchoolAcademy().getAcademyNumber());
			//设置操作时间
			systemLog.setCalendarTime(Calendar.getInstance());
			//设置当前centerId
			systemLog.setOperationAction(centerId);
			systemLogDAO.store(systemLog);
		}else{//若表里有记录则更新
			//设置操作时间
			systemLog.setCalendarTime(Calendar.getInstance());
			//设置当前centerId
			systemLog.setOperationAction(centerId);
			systemLogDAO.store(systemLog);
		}
		
	}
	
}
