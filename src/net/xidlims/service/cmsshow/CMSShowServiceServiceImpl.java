package net.xidlims.service.cmsshow;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import net.xidlims.common.LabAttendance;
import net.xidlims.service.common.ShareService;
import net.xidlims.dao.CommonHdwlogDAO;
import net.xidlims.dao.LabCenterDAO;
import net.xidlims.dao.LabReservationDAO;
import net.xidlims.dao.LabRoomDAO;
import net.xidlims.dao.LabRoomDeviceDAO;
import net.xidlims.dao.LabRoomDeviceReservationDAO;
import net.xidlims.dao.SchoolAcademyDAO;
import net.xidlims.dao.UserDAO;
import net.xidlims.domain.CDictionary;
import net.xidlims.domain.CommonHdwlog;
import net.xidlims.domain.LabReservation;
import net.xidlims.domain.LabReservationTimeTable;
import net.xidlims.domain.LabRoom;
import net.xidlims.domain.LabRoomDevice;
import net.xidlims.domain.LabRoomDeviceReservation;
import net.xidlims.domain.SchoolAcademy;
import net.xidlims.domain.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import excelTools.Labreservationlist;



@Service("CMSShowService")
public class CMSShowServiceServiceImpl implements  CMSShowService {
	

	@Autowired 
	private LabRoomDAO labRoomDAO;
	@Autowired 
	private LabReservationDAO labReservationDAO;
	@Autowired
	private LabRoomDeviceReservationDAO labRoomDeviceReservationDAO;
	@Autowired
	private LabRoomDeviceDAO labRoomDeviceDAO;
	@Autowired
	private CommonHdwlogDAO commonHdwlogDAO;
	@Autowired
	private ShareService shareService;
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private SchoolAcademyDAO schoolAcademyDAO;
	@Autowired
	private LabCenterDAO labCenterDAO;
	
	
	/*************************************************************************************
	 * @內容：实验室开放的总记录数-校级页面
	 * @作者：叶明盾
	 * @日期：2014-09-10
	 *************************************************************************************/
	@Transactional
	public int getAllLabReservationTotalRecords(){
		//实验室开放的总记录数（由于数据量比较多，不能够使用findAll()方法查找）
		return  ((Long) labReservationDAO.createQuerySingleResult("select count(*) from LabReservation").getSingleResult()).intValue();
	}
	
	/*************************************************************************************
	 * @內容：查找所有的实验室开放-校级页面
	 * @作者：叶明盾
	 * @日期：2014-09-10
	 *************************************************************************************/
	public List<LabReservation> findAllLabReservations(int curr, int size){
		//利用sql语句从LabReservation表中查找出所有的数据，并赋给StringBuffer类型的sb变量
		StringBuffer sb= new StringBuffer("select l from LabReservation l where 1=1");
		//给语句添加分页机制
		sb.append(" order by l.id desc");
		List<LabReservation> labReservations=labReservationDAO.executeQuery(sb.toString(), curr*size, size);
		return labReservations;
	}
	
	/*************************************************************************************
	 * @內容：根据实验室开放的情况查找出相应的周次、星期、节次-校级页面
	 * @作者：叶明盾
	 * @日期：2014-09-10
	 *************************************************************************************/
	public  List<Labreservationlist> findLabReservationDate(int curr, int size){
		 List<LabReservation> LabReservations = this.findAllLabReservations(curr - 1, size);
		 List<Labreservationlist>    list = new ArrayList<Labreservationlist>();  
         for (LabReservation lab : LabReservations) {
        	 Labreservationlist   la=new Labreservationlist();
        	        Set<String>  week=new HashSet<String>(); 
        	        Set<String>  day=new HashSet<String>(); 
        	        Set<String>  time=new HashSet<String>(); 
        	for (LabReservationTimeTable labre : lab.getLabReservationTimeTables()) {
        		//week.add(labre.getCLabReservationWeek().getName());
        		week.add(labre.getCDictionary().getCName());
        		day.add(labre.getSchoolWeekday().getWeekdayName());
        	 	time.add(labre.getSystemTime().getSectionName());
			}
        	int dd=week.size();
        	String[] weeks=week.toArray(new String[dd]);
        	String[] days=day.toArray(new String[dd]);
        	String[] timea=time.toArray(new String[dd]);;
			la.setWeek(weeks);
			la.setTime(timea);
			la.setDay(days);
			list.add(la);		
		}
         return list;
	}
	
	
	
	/*************************************************************************************
	 * @內容：仪器共享的总记录数-校级页面
	 * @作者：叶明盾
	 * @日期：2014-09-10
	 *************************************************************************************/
	@Transactional
	public int getAllLabRoomDeviceReservationTotalRecords(){
		//得出仪器共享的总记录数（由于数据量比较多，不能够使用findAll()方法查找）
		String sql = "select count(l) from LabRoomDeviceReservation l where 1=1";
		// 非排课占用
		sql+=" and (l.timetableLabDevice is null or l.appointmentId is null)";
		sql+=" group by l.innerSame";
		return  ((Long) labRoomDeviceReservationDAO.createQuerySingleResult(sql).getSingleResult()).intValue();
	}
	
	/*************************************************************************************
	 * @內容：查找所有的仪器共享-校级页面
	 * @作者：叶明盾
	 * @日期：2014-09-10
	 *************************************************************************************/
	public List<LabRoomDeviceReservation> findAllLabRoomDeviceReservations(int curr, int size){
		//利用sql语句从仪器共享表中查找出所有的数据，并赋给StringBuffer类型的sb变量
		StringBuffer sb= new StringBuffer("select l from LabRoomDeviceReservation l where 1=1");
		// 非排课占用
		sb.append(" and (l.timetableLabDevice is null or l.appointmentId is null)");
		//给语句添加分页机制
		sb.append(" group by l.innerSame");
		List<LabRoomDeviceReservation> labRoomDeviceReservations = labRoomDeviceReservationDAO.executeQuery(sb.toString(), curr*size, size);
		return labRoomDeviceReservations;
	}
	
	
	/*************************************************************************************
	 * @內容：实验室开放的总记录数-计算机学院
	 * @作者：叶明盾
	 * @日期：2014-09-23
	 *************************************************************************************/
	@Transactional
	public int getCompLabReservationTotalRecords(){
		//实验室开放的总记录数（由于数据量比较多，不能够使用findAll()方法查找），0205为计算机学院的学院编号
		return  ((Long) labReservationDAO.createQuerySingleResult("select count(*) from LabReservation l where  l.labRoom.labAnnex.labCenter.schoolAcademy.academyNumber=0205 ").getSingleResult()).intValue();
	}
	
	/*************************************************************************************
	 * @內容：查找所有的实验室开放-计算机学院
	 * @作者：叶明盾
	 * @日期：2014-09-23
	 *************************************************************************************/
	public List<LabReservation> findCompLabReservations(int curr, int size){
		//利用sql语句从LabReservation表中查找出计算机学院的数据，并赋给StringBuffer类型的sb变量，0205为计算机学院的学院编号；
		StringBuffer sb= new StringBuffer("select l from LabReservation l where  l.labRoom.labAnnex.labCenter.schoolAcademy.academyNumber=0205 and 1=1");
		//给语句添加分页机制
		List<LabReservation> labReservations=labReservationDAO.executeQuery(sb.toString(), curr*size, size);
		return labReservations;
	}
	
	/*************************************************************************************
	 * @內容：仪器共享的总记录数-计算机学院
	 * @作者：叶明盾
	 * @日期：2014-09-23
	 *************************************************************************************/
	@Transactional
	public int getCompLabRoomDeviceReservationTotalRecords(){
		//得出计算机学院仪器共享的总记录数（由于数据量比较多，不能够使用findAll()方法查找），0205为计算机学院的学院编号
		return  ((Long) labRoomDeviceReservationDAO.createQuerySingleResult("select count(*) from LabRoomDeviceReservation l where l.labRoomDevice.labRoom.labAnnex.labCenter.schoolAcademy.academyNumber=0205 ").getSingleResult()).intValue();
	}
	
	/*************************************************************************************
	 * @內容：查找所有的仪器共享-计算机学院
	 * @作者：叶明盾
	 * @日期：2014-09-23
	 *************************************************************************************/
	public List<LabRoomDeviceReservation> findCompLabRoomDeviceReservations(int curr, int size){
		//利用sql语句从仪器共享表中查找出计算机学院的数据，并赋给StringBuffer类型的sb变量，0205为计算机学院的学院编号
		StringBuffer sb= new StringBuffer("select l from LabRoomDeviceReservation l where l.labRoomDevice.labRoom.labAnnex.labCenter.schoolAcademy.academyNumber=0205 and 1=1");
		//给语句添加分页机制
		List<LabRoomDeviceReservation> labRoomDeviceReservations = labRoomDeviceReservationDAO.executeQuery(sb.toString(), curr*size, size);
		return labRoomDeviceReservations;
	}
	
	
	/**
	 * 实验室开放的总记录数-各个学院
	 * @param academyId 学院Id
	 * @return
	 * @author 叶明盾
	 * @date 2014-11-11 上午12:47:07
	 */
	@Transactional
	public int getAcademyLabReservationTotalRecords(String academyId){
		//实验室开放的总记录数（由于数据量比较多，不能够使用findAll()方法查找），academyId为学院的Id
		return  ((Long) labReservationDAO.createQuerySingleResult("select count(*) from LabReservation l where  l.labRoom.labAnnex.labCenter.schoolAcademy.academyNumber like '"+academyId+"'").getSingleResult()).intValue();
	}
	
	/**
	 * 查找所有的实验室开放-各个学院
	 * @param curr
	 * @param size
	 * @param academyId 学院Id
	 * @return
	 * @author 叶明盾
	 * @date 2014-11-11 上午12:48:15
	 */
	public List<LabReservation> findAcademyLabReservations(int curr, int size,String academyId){
		//利用sql语句从LabReservation表中查找出计算机学院的数据，并赋给StringBuffer类型的sb变量，academyId为学院编号；
		StringBuffer sb= new StringBuffer("select l from LabReservation l where  l.labRoom.labAnnex.labCenter.schoolAcademy.academyNumber like '"+academyId+"'");
		//给语句添加分页机制
		List<LabReservation> labReservations=labReservationDAO.executeQuery(sb.toString(), curr*size, size);
		return labReservations;
	}
	
	/**
	 * 仪器共享的总记录数-各个学院
	 * @param academyId 学院Id
	 * @return
	 * @author 叶明盾
	 * @date 2014-11-11 上午12:49:03
	 */
	@Transactional
	public int getAcademyLabRoomDeviceReservationTotalRecords(String academyId){
		//得出计算机学院仪器共享的总记录数（由于数据量比较多，不能够使用findAll()方法查找），academyId为学院编号
		return  ((Long) labRoomDeviceReservationDAO.createQuerySingleResult("select count(*) from LabRoomDeviceReservation l where l.labRoomDevice.labRoom.labAnnex.labCenter.schoolAcademy.academyNumber like '"+academyId+"'").getSingleResult()).intValue();
	}
	
	/**
	 * 查找所有的仪器共享-各个学院
	 * @param curr
	 * @param size
	 * @param academyId 学院Id
	 * @return
	 * @author 叶明盾
	 * @date 2014-11-11 上午12:49:57
	 */
	public List<LabRoomDeviceReservation> findAcademyLabRoomDeviceReservations(int curr, int size,String academyId){
		//利用sql语句从仪器共享表中查找出计算机学院的数据，并赋给StringBuffer类型的sb变量，academyId为学院编号
		StringBuffer sb= new StringBuffer("select l from LabRoomDeviceReservation l where l.labRoomDevice.labRoom.labAnnex.labCenter.schoolAcademy.academyNumber like '"+academyId+"'");
		//给语句添加分页机制
		List<LabRoomDeviceReservation> labRoomDeviceReservations = labRoomDeviceReservationDAO.executeQuery(sb.toString(), curr*size, size);
		return labRoomDeviceReservations;
	}
	/*************************************************************************************
	 * @功能：根据学院查询实验室
	 * @作者： 李小龙
	 *************************************************************************************/
	@Override
	public List<LabRoom> findLabRoomBySchoolAcademy(LabRoom labRoom) {
		String sql="select r from LabRoom r where 1=1 ";
		if(labRoom.getLabAnnex()!=null&&labRoom.getLabAnnex().getLabCenter()!=null&&labRoom.getLabAnnex().getLabCenter().getSchoolAcademy()!=null){
			if(labRoom.getLabAnnex().getId()!=null&&!labRoom.getLabAnnex().getId().equals(0)){
				sql+=" and r.labAnnex.id="+labRoom.getLabAnnex().getId();
			}
			if(!labRoom.getLabAnnex().getLabCenter().getSchoolAcademy().getAcademyNumber().equals("")){
				sql+=" and r.labAnnex.labCenter.schoolAcademy.academyNumber='"+labRoom.getLabAnnex().getLabCenter().getSchoolAcademy().getAcademyNumber()+"' ";
			}
			
		}
		if(labRoom.getSystemRoom()!=null&&labRoom.getSystemRoom().getSystemBuild()!=null){
			if(!labRoom.getSystemRoom().getSystemBuild().getBuildNumber().equals("")){
				sql+=" and r.systemRoom.systemBuild.buildNumber='"+labRoom.getSystemRoom().getSystemBuild().getBuildNumber()+"' ";
			}
		}
		return labRoomDAO.executeQuery(sql, 0,-1);
	}
	/*************************************************************************************
	 * @功能：根据学院查询实验室并分页
	 * @作者： 李小龙
	 *************************************************************************************/
	@Override
	public List<LabRoom> findLabRoomBySchoolAcademy(LabRoom labRoom, int page, int pageSize) {
		String sql="select r from LabRoom r where 1=1 ";
		
		if(labRoom.getLabAnnex()!=null&&labRoom.getLabAnnex().getLabCenter()!=null&&labRoom.getLabAnnex().getLabCenter().getSchoolAcademy()!=null){
			if(labRoom.getLabAnnex().getId()!=null&&!labRoom.getLabAnnex().getId().equals(0)){
				sql+=" and r.labAnnex.id="+labRoom.getLabAnnex().getId();
			}
			if(!labRoom.getLabAnnex().getLabCenter().getSchoolAcademy().getAcademyNumber().equals("")){
				sql+=" and r.labAnnex.labCenter.schoolAcademy.academyNumber='"+labRoom.getLabAnnex().getLabCenter().getSchoolAcademy().getAcademyNumber()+"' ";
			}
			
		}
		if(labRoom.getSystemRoom()!=null&&labRoom.getSystemRoom().getSystemBuild()!=null){
			if(!labRoom.getSystemRoom().getSystemBuild().getBuildNumber().equals("")){
				sql+=" and r.systemRoom.systemBuild.buildNumber='"+labRoom.getSystemRoom().getSystemBuild().getBuildNumber()+" '";
			}
		}
		return labRoomDAO.executeQuery(sql, (page-1)*pageSize,pageSize);
	}
	
	/*************************************************************************************
	 * @功能：根据学院查询实验室并分页--默认显示当前学院的
	 * @作者： 贺子龙
	 *************************************************************************************/
	@Override
	public List<LabRoom> findLabRoomBySchoolAcademyDefault(
			LabRoom labRoom, int page, int pageSize,int type, int iLabCenter){
		String academyNumber="";
        // 如果没有获取有效的实验分室列表-根据登录用户的所属学院
        if (iLabCenter != -1) {
    		//获取选择的实验中心
        	academyNumber = shareService.getUserDetail().getSchoolAcademy().getAcademyNumber();
        }else{
        	academyNumber = shareService.getUserDetail().getSchoolAcademy().getAcademyNumber();
        }	
		String sql="select r from LabRoom r,LabRoomAgent la where 1=1 ";
		sql+=" and la.labRoom.id=r.id";
		//sql+=" and la.CAgentType.id="+type;
		sql+=" and la.CDictionary.CCategory = 'c_agent_type' and la.CDictionary.CNumber = '"+type+"'";
		if(shareService.getUser().getSchoolAcademy()!=null
				&&!shareService.getUser().getSchoolAcademy().getAcademyNumber().equals("")){
			sql+=" and r.labAnnex.labCenter.schoolAcademy.academyNumber='"+academyNumber+"' ";
		}
		if(labRoom.getLabAnnex()!=null&&labRoom.getLabAnnex().getId()!=null&&!labRoom.getLabAnnex().getId().equals(0)){
			sql+=" and r.labAnnex.id="+labRoom.getLabAnnex().getId();
		}
		if(labRoom != null && labRoom.getId() != null && !labRoom.getId().equals("")){
			sql += " and r.id ="+labRoom.getId();
		}
		return labRoomDAO.executeQuery(sql, (page-1)*pageSize,pageSize);
	}
	
	/*************************************************************************************
	 * @功能：根据学院查询设备
	 * @作者： 李小龙
	 *************************************************************************************/
	@Override
	public List<LabRoomDevice> findLabRoomDeviceBySchoolAcademy(
			LabRoomDevice labRoomDevice) {
		String sql="select d from LabRoomDevice d where 1=1";
		if(labRoomDevice.getLabRoom()!=null&&labRoomDevice.getLabRoom().getLabAnnex()!=null&&labRoomDevice.getLabRoom().getLabAnnex().getLabCenter()!=null&&labRoomDevice.getLabRoom().getLabAnnex().getLabCenter().getSchoolAcademy()!=null){
			if(labRoomDevice.getLabRoom().getLabAnnex().getId()!=null&&!labRoomDevice.getLabRoom().getLabAnnex().getId().equals(0)){
				sql+=" and d.labRoom.labAnnex.id="+labRoomDevice.getLabRoom().getLabAnnex().getId();
			}
			if(labRoomDevice.getLabRoom().getLabAnnex().getLabCenter().getSchoolAcademy().getAcademyNumber()!=null&&!labRoomDevice.getLabRoom().getLabAnnex().getLabCenter().getSchoolAcademy().getAcademyNumber().equals("")){
				sql+=" and d.labRoom.labAnnex.labCenter.schoolAcademy.academyNumber like'"+labRoomDevice.getLabRoom().getLabAnnex().getLabCenter().getSchoolAcademy().getAcademyNumber()+"'";
			}
		}
		
		return labRoomDeviceDAO.executeQuery(sql, 0,-1);
	}
	/*************************************************************************************
	 * @功能：根据学院查询设备并分页
	 * @作者： 李小龙
	 *************************************************************************************/
	@Override
	public List<LabRoomDevice> findLabRoomDeviceBySchoolAcademy(
			LabRoomDevice labRoomDevice, int page, int pageSize) {
		String sql="select d from LabRoomDevice d where 1=1";
		if(labRoomDevice.getLabRoom()!=null&&labRoomDevice.getLabRoom().getLabAnnex()!=null&&labRoomDevice.getLabRoom().getLabAnnex().getLabCenter()!=null&&labRoomDevice.getLabRoom().getLabAnnex().getLabCenter().getSchoolAcademy()!=null){
			if(labRoomDevice.getLabRoom().getLabAnnex().getId()!=null&&!labRoomDevice.getLabRoom().getLabAnnex().getId().equals(0)){
				sql+=" and d.labRoom.labAnnex.id="+labRoomDevice.getLabRoom().getLabAnnex().getId();
			}
			if(labRoomDevice.getLabRoom().getLabAnnex().getLabCenter().getSchoolAcademy().getAcademyNumber()!=null&&!labRoomDevice.getLabRoom().getLabAnnex().getLabCenter().getSchoolAcademy().getAcademyNumber().equals("")){
				sql+=" and d.labRoom.labAnnex.labCenter.schoolAcademy.academyNumber like '"+labRoomDevice.getLabRoom().getLabAnnex().getLabCenter().getSchoolAcademy().getAcademyNumber()+"'";
			}
		}
		return labRoomDeviceDAO.executeQuery(sql, (page-1)*pageSize,pageSize);
	}
	/*************************************************************************************
	 * @功能：根据ip查询刷卡记录
	 * @作者： 李小龙
	 *************************************************************************************/
	@Override
	public List<CommonHdwlog> findLabRoomAccessByIp(String ip,String port) {
		String sql="select c from CommonHdwlog c where 1=1";
		if(ip!=null&&!port.equals("")){
			sql+=" and c.deviceno='"+port+"' ";
		}
		sql+=" order by c.id desc";
		return commonHdwlogDAO.executeQuery(sql, 0,-1);
	}
	/*************************************************************************************
	 * @功能：根据ip查询刷卡记录并分页
	 * @作者： 李小龙
	 *************************************************************************************/
	@Override
	public List<CommonHdwlog> findLabRoomAccessByIp(String ip,String port, Integer page,
			int pageSize) {
		String sql="select c from CommonHdwlog c where 1=1";
		if(ip!=null&&!port.equals("")){
			sql+=" and c.deviceno='"+port+"' ";
		}
		sql+=" order by c.id desc";
		return commonHdwlogDAO.executeQuery(sql, (page-1)*pageSize,pageSize);
	}
	
	/*************************************************************************************
	 * @功能：根据ip查询刷卡记录并分页--增加查询功能
	 * @作者： 贺子龙
	 *************************************************************************************/
	@Override
	public List<LabAttendance> findLabRoomAccessByIp(CommonHdwlog commonHdwlog,String ip,String port, Integer page,
			int pageSize,HttpServletRequest request) {
		String sql="select c from CommonHdwlog c where 1=1";
		
		if(port!=null&&!port.equals("")){
			sql+=" and c.deviceno='"+port+"' ";
		}
		
		if(ip!=null&&!ip.equals("")){
			sql+=" and c.hardwareid='"+ip+"' ";
		}
		
		if (commonHdwlog.getCardname()!=null&&!commonHdwlog.getCardname().equals("")) {
			sql+=" and c.cardname like '%"+commonHdwlog.getCardname()+"%' ";
			
		}
		if (commonHdwlog.getUsername()!=null&&!commonHdwlog.getUsername().equals("")) {
			sql+=" and c.username like '%"+commonHdwlog.getUsername()+"%' ";
			
		}
		String starttime= request.getParameter("starttime");
		String endtime=	request.getParameter("endtime");
		
		  if(starttime!=null && starttime.length()>0 && endtime!=null&& endtime.length()>0){
			  sql += " and c.datetime between '"+starttime +"' and '"+endtime+"' "; 	
	        }
		
		sql+=" order by c.id desc";
		List<CommonHdwlog> commonHdwlogs=commonHdwlogDAO.executeQuery(sql, (page-1)*pageSize,pageSize);
		//将查出来的日志数据导入labAttendanceList中
		List<LabAttendance> labAttendanceList=new ArrayList<LabAttendance>();
		for (CommonHdwlog commonHdwlog2 : commonHdwlogs) {
			LabAttendance labAttendance=new LabAttendance();
			//姓名
			labAttendance.setCname(commonHdwlog2.getCardname());
			//考勤时间
			String attendanceTime=commonHdwlog2.getDatetime();
			labAttendance.setAttendanceTime(attendanceTime.substring(0, attendanceTime.length()-2));
			//default数据
			labAttendance.setClassName("暂无数据");
			labAttendance.setMajor("暂无数据");
			labAttendance.setAcademyName("暂无数据");
			//所属学院
			if (commonHdwlog2.getAcademyNumber()!=null&&!commonHdwlog2.getAcademyNumber().equals("")) {
				String academyName="";
				SchoolAcademy schoolAcademy=schoolAcademyDAO.findSchoolAcademyByAcademyNumber(commonHdwlog2.getAcademyNumber());
				if (schoolAcademy!=null&&!schoolAcademy.getAcademyName().equals("")) {
					academyName=schoolAcademy.getAcademyName();
					labAttendance.setAcademyName(academyName);
				}
				
			}
			//学号
			String username="";
			if (commonHdwlog2.getUsername()!=null&&!commonHdwlog2.getUsername().equals("")) {
				username=commonHdwlog2.getUsername();
			}
			if (!username.equals("")) {
				labAttendance.setUsername(username);
				User user=userDAO.findUserByPrimaryKey(username);
				//班级
				String className="";
				if (user.getSchoolClasses()!=null&&user.getSchoolClasses().getClassNumber()!=null
						&&!user.getSchoolClasses().getClassNumber().equals("")
						&&!user.getSchoolClasses().getClassName().equals("")) {
					className=user.getSchoolClasses().getClassName();
					labAttendance.setClassName(className);
				}
				//所属专业方向
				String major="";//暂无专业外键，暂删除
				/*if (user.getSchoolMajor()!=null&&!user.getSchoolMajor().getMajorName().equals("")) {
					major=user.getSchoolMajor().getMajorName();
					labAttendance.setMajor(major);
				}*/
				
			}
			labAttendanceList.add(labAttendance);
			
		}
		return labAttendanceList;
	}
	/*************************************************************************************
	 * @功能：根据ip查询刷卡记录数量--增加查询功能
	 * @作者： 贺子龙
	 *************************************************************************************/
	@Override
	public int findLabRoomAccessByIpCount(CommonHdwlog commonHdwlog,String ip,String port,HttpServletRequest request) {
		String sql="select count(*) from CommonHdwlog c where 1=1";
		
		if(port!=null&&!port.equals("")){
			sql+=" and c.deviceno='"+port+"' ";
		}
		
		if(ip!=null&&!ip.equals("")){
			sql+=" and c.hardwareid='"+ip+"' ";
		}
		
		
		if (commonHdwlog.getCardname()!=null&&!commonHdwlog.getCardname().equals("")) {
			sql+=" and c.cardname like '%"+commonHdwlog.getCardname()+"%' ";
			
		}
		if (commonHdwlog.getUsername()!=null&&!commonHdwlog.getUsername().equals("")) {
			sql+=" and c.username like '%"+commonHdwlog.getUsername()+"%' ";
			
		}
		String starttime= request.getParameter("starttime");
		String endtime=	request.getParameter("endtime");
		
		  if(starttime!=null && starttime.length()>0 && endtime!=null&& endtime.length()>0){
			  sql += " and c.datetime between '"+starttime +"' and '"+endtime+"' "; 	
	        }
		
		int count=((Long)commonHdwlogDAO.createQuerySingleResult(sql).getSingleResult()).intValue();
		return count;
	}
	
	/*************************************************************************************
	 * @功能：根据实验室id查询实验室使用记录
	 * @作者： 李小龙
	 *************************************************************************************/
	@Override
	public List<LabReservation> findLabRoomUseRecord(Integer roomId) {
		String sql="select l from LabReservation l where l.labRoom.id="+roomId;
		return labReservationDAO.executeQuery(sql, 0,-1);
	}

	@Override
	public List<LabReservation> findLabRoomUseRecord(Integer roomId, int page,
			int pageSize) {
		String sql="select l from LabReservation l where l.labRoom.id="+roomId;
		return labReservationDAO.executeQuery(sql, (page-1)*pageSize,pageSize);
	}
	/*************************************************************************************
	 * @功能：根据设备id查询设备使用记录
	 * @作者： 李小龙
	 *************************************************************************************/
	@Override
	public List<LabRoomDeviceReservation> findLabRoomDeviceUseRecord(
			Integer deviceId) {
		//String sql="select l from LabRoomDeviceReservation l where l.labRoomDevice.id="+deviceId+" and l.CAuditResult.id=2";
		String sql="select l from LabRoomDeviceReservation l where l.labRoomDevice.id="+deviceId+" and l.CDictionary.CCategory = 'c_audit_result' and l.CDictionary.CNumber = '2'";
		return labRoomDeviceReservationDAO.executeQuery(sql, 0,-1);
	}
	/*************************************************************************************
	 * @功能：根据设备id查询设备使用记录并分页
	 * @作者： 李小龙
	 *************************************************************************************/
	@Override
	public List<LabRoomDeviceReservation> findLabRoomDeviceUseRecord(
			Integer deviceId, int page, int pageSize) {
		//String sql="select l from LabRoomDeviceReservation l where l.labRoomDevice.id="+deviceId+" and l.CAuditResult.id=2";
		String sql="select l from LabRoomDeviceReservation l where l.labRoomDevice.id="+deviceId+" and l.CDictionary.CCategory = 'c_audit_result' and l.CDictionary.CNumber = '2'";
		return labRoomDeviceReservationDAO.executeQuery(sql, (page-1)*pageSize,pageSize);
	}
	/*************************************************************************************
	 * @功能：根据楼栋编号查询实验室
	 * @作者： 李小龙
	 *************************************************************************************/
	@Override
	public List<LabRoom> findLabRoomByBuildNumber(String buildNumber) {
		String sql="select m from LabRoom m where m.systemRoom.systemBuild.buildNumber="+buildNumber;
		return labRoomDAO.executeQuery(sql, 0,-1);
	}
	/*************************************************************************************
	 * @功能：根据楼栋编号查询实验室并分页
	 * @作者： 李小龙
	 *************************************************************************************/
	@Override
	public List<LabRoom> findLabRoomByBuildNumber(String buildNumber, int page,
			int pageSize) {
		String sql="select m from LabRoom m where m.systemRoom.systemBuild.buildNumber="+buildNumber;
		return labRoomDAO.executeQuery(sql, (page-1)*pageSize,pageSize);
	}
	/*************************************************************************************
	 * @功能：根据学院编号查询实验室设备
	 * @作者： 李小龙
	 *************************************************************************************/
	@Override
	public List<LabRoomDevice> findLabRoomDeviceByAcademy(String academyNumber) {
		
		String sql="select d from LabRoomDevice d where 1=1";
		if(academyNumber!=null&&!academyNumber.equals("")){
			sql+=" and d.labRoom.labAnnex.labCenter.schoolAcademy.academyNumber="+academyNumber;
		}
		return labRoomDeviceDAO.executeQuery(sql, 0,-1);
	}
	/*************************************************************************************
	 * @功能：根据学院编号查询实验室设备并分页
	 * @作者： 李小龙
	 *************************************************************************************/
	@Override
	public List<LabRoomDevice> findLabRoomDeviceByAcademy(String academyNumber,
			int page, int pageSize) {
		String sql="select d from LabRoomDevice d where 1=1";
		if(academyNumber!=null&&!academyNumber.equals("")){
			sql+=" and d.labRoom.labAnnex.labCenter.schoolAcademy.academyNumber="+academyNumber;
		}
		return labRoomDeviceDAO.executeQuery(sql, (page-1)*pageSize,pageSize);
	}

}