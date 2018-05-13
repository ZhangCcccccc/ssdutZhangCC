package net.xidlims.service.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.xidlims.service.common.ShareService;
import net.xidlims.dao.LabRoomDeviceDAO;
import net.xidlims.dao.SchoolAcademyDAO;
import net.xidlims.dao.SchoolCourseDAO;
import net.xidlims.dao.SchoolDeviceDAO;
import net.xidlims.dao.SystemBuildDAO;
import net.xidlims.dao.SystemRoomDAO;
import net.xidlims.dao.TimetableAppointmentDAO;
import net.xidlims.dao.UserDAO;
import net.xidlims.domain.LabRoomDevice;
import net.xidlims.domain.SchoolAcademy;
import net.xidlims.domain.SchoolDevice;
import net.xidlims.domain.SystemBuild;
import net.xidlims.domain.SystemRoom;
import net.xidlims.domain.TimetableAppointment;
import net.xidlims.domain.User;

@Service("ShareDataService")
public class ShareDataServiceImpl implements ShareDataService {

	@Autowired
	private SystemBuildDAO systemBuildDAO;
	
	@Autowired
	private SystemRoomDAO systemRoomDAO;
	
	@Autowired
	private SchoolDeviceDAO schoolDeviceDAO;
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private SchoolAcademyDAO schoolAcademyDAO;
	
	@Autowired
	private TimetableAppointmentDAO timetableAppointmentDAO;
	
	@Autowired
	private ShareService shareService;
	@Autowired
	private LabRoomDeviceDAO labRoomDeviceDAO;
	
	@Override
	public List<SystemBuild> findSystemBuildBySystemBuild(SystemBuild systemBuild) {
		// TODO Auto-generated method stub
		String sql="select l from SystemBuild l where 1=1";
		if(systemBuild!=null){
		if(systemBuild.getBuildName()!=null&&!systemBuild.getBuildName().equalsIgnoreCase("")){
		
			sql+=" and l.buildName like '%"+systemBuild.getBuildName()+"%'";
		}
		}
		/*sql+=" order by l.CConsumables desc";*/
		//给语句添加分页机制；
		List<SystemBuild> systemBuilds=systemBuildDAO.executeQuery(sql,0,-1);
		return systemBuilds;
	}
	
	@Override
	public int getCountSystemBuildBySystemBuild(SystemBuild systemBuild) {
		// TODO Auto-generated method stub
		String sql="select count(*) from SystemBuild l where 1=1";
		if(systemBuild!=null){
			if(systemBuild.getBuildName()!=null&&!systemBuild.getBuildName().equalsIgnoreCase("")){
				
				sql+=" and l.buildName like '%"+systemBuild.getBuildName()+"%'";
			}
		}
		/*sql+=" order by l.CConsumables desc";*/
		//给语句添加分页机制；
		//List<SystemBuild> systemBuilds=systemBuildDAO.executeQuery(sql,0,-1);
		return ((Long) systemBuildDAO.createQuerySingleResult(sql).getSingleResult()).intValue();
	}
	
	@Override
	public List<SystemBuild> findSystemBuildBySystemBuild(SystemBuild systemBuild, int page,
			int pageSize) {
		// TODO Auto-generated method stub
				String sql="select l from SystemBuild l where 1=1";
				if(systemBuild!=null){
				if(systemBuild.getBuildName()!=null&&!systemBuild.getBuildName().equalsIgnoreCase("")){
				
					sql+=" and l.buildName like '%"+systemBuild.getBuildName()+"%'";
				}
				}
		
		List<SystemBuild> systemBuilds=systemBuildDAO.executeQuery(sql,(page-1)*pageSize,pageSize);
		return systemBuilds;
	}
	
	@Override
	public List<SystemRoom> findSystemRoomBySystemRoom(SystemRoom systemRoom) {
		// TODO Auto-generated method stub
		String sql="select l from SystemRoom l where 1=1";
		if(systemRoom.getSystemBuild()!=null){
		if(systemRoom.getSystemBuild().getBuildName()!=null&&!systemRoom.getSystemBuild().getBuildName().equalsIgnoreCase("")){
		
			sql+=" and l.systemBuild.buildName like '%"+systemRoom.getSystemBuild().getBuildName()+"%'";
		}
		}
		/*sql+=" order by l.CConsumables desc";*/
		//给语句添加分页机制；
		List<SystemRoom> systemRooms=systemRoomDAO.executeQuery(sql,0,-1);
		return systemRooms;
	}
	
	@Override
	public int getCountSystemRoomBySystemRoom(SystemRoom systemRoom) {
		// TODO Auto-generated method stub
		String sql="select count(*) from SystemRoom l where 1=1";
		if(systemRoom.getSystemBuild()!=null){
			if(systemRoom.getSystemBuild().getBuildName()!=null&&!systemRoom.getSystemBuild().getBuildName().equalsIgnoreCase("")){
				
				sql+=" and l.systemBuild.buildName like '%"+systemRoom.getSystemBuild().getBuildName()+"%'";
			}
		}
		/*sql+=" order by l.CConsumables desc";*/
		//给语句添加分页机制；
		//List<SystemRoom> systemRooms=systemRoomDAO.executeQuery(sql,0,-1);
		return ((Long) systemBuildDAO.createQuerySingleResult(sql).getSingleResult()).intValue();
	}
	
	@Override
	public List<SystemRoom> findSystemRoomBySystemRoom(SystemRoom systemRoom, int page,
			int pageSize) {
		// TODO Auto-generated method stub
				String sql="select l from SystemRoom l where 1=1";
				if(systemRoom.getSystemBuild()!=null){
				if(systemRoom.getSystemBuild().getBuildName()!=null&&!systemRoom.getSystemBuild().getBuildName().equalsIgnoreCase("")){
				
					sql+=" and l.systemBuild.buildName like '%"+systemRoom.getSystemBuild().getBuildName()+"%'";
				}
				}
		
		List<SystemRoom> systemRooms=systemRoomDAO.executeQuery(sql,(page-1)*pageSize,pageSize);
		return systemRooms;
	}
	
	@Override
	public List<SchoolDevice> findSchoolDeviceBySchoolDevice(SchoolDevice schoolDevice) {
		// TODO Auto-generated method stub
		String sql="select l from SchoolDevice l where 1=1";
		if(schoolDevice!=null){
		if(schoolDevice.getDeviceName()!=null&&!schoolDevice.getDeviceName().equalsIgnoreCase("")){
		
			sql+=" and l.deviceName like '%"+schoolDevice.getDeviceName()+"%'";
		}
		}
		/*sql+=" order by l.CConsumables desc";*/
		//给语句添加分页机制；
		List<SchoolDevice> schoolDevices=schoolDeviceDAO.executeQuery(sql,0,-1);
		return schoolDevices;
	}
	
	@Override
	public int getCountSchoolDeviceBySchoolDevice(SchoolDevice schoolDevice) {
		// TODO Auto-generated method stub
		String sql="select count(*) from SchoolDevice l where 1=1";
		if(schoolDevice!=null){
		if(schoolDevice.getDeviceName()!=null&&!schoolDevice.getDeviceName().equalsIgnoreCase("")){
		
			sql+=" and l.deviceName like '%"+schoolDevice.getDeviceName()+"%'";
		}
		if(schoolDevice.getDeviceNumber()!=null&&!schoolDevice.getDeviceNumber().equalsIgnoreCase("")){
			
			sql+=" and l.deviceNumber like '%"+schoolDevice.getDeviceNumber()+"%'";
		}
		}
		/*sql+=" order by l.CConsumables desc";*/
		//给语句添加分页机制；
		//List<SchoolDevice> schoolDevices=schoolDeviceDAO.executeQuery(sql,0,-1);
		return ((Long) schoolDeviceDAO.createQuerySingleResult(sql).getSingleResult()).intValue();
	}
	
	@Override
	public int getCountLabRoomDevice(LabRoomDevice labRoomDevice) {
		// TODO Auto-generated method stub
		String sql="select count(*) from LabRoomDevice l where 1=1";
		if(labRoomDevice!=null){
		if(labRoomDevice.getSchoolDevice() != null && labRoomDevice.getSchoolDevice().getDeviceName()!=null&&!labRoomDevice.getSchoolDevice().getDeviceName().equalsIgnoreCase("")){
		
			sql+=" and l.schoolDevice.deviceName like '%"+labRoomDevice.getSchoolDevice().getDeviceName()+"%'";
		}
		if(labRoomDevice.getSchoolDevice() != null && labRoomDevice.getSchoolDevice().getDeviceNumber()!=null&&!labRoomDevice.getSchoolDevice().getDeviceNumber().equalsIgnoreCase("")){
			
			sql+=" and l.schoolDevice.deviceNumber like '%"+labRoomDevice.getSchoolDevice().getDeviceNumber()+"%'";
		}
		if(labRoomDevice.getLabRoom() != null && labRoomDevice.getLabRoom().getId()!=null&&!labRoomDevice.getLabRoom().getId().equals("")){
			
			sql+=" and l.labRoom.id = "+labRoomDevice.getLabRoom().getId();
		}
		}
		/*sql+=" order by l.CConsumables desc";*/
		//给语句添加分页机制；
		//List<SchoolDevice> schoolDevices=schoolDeviceDAO.executeQuery(sql,0,-1);
		return ((Long) labRoomDeviceDAO.createQuerySingleResult(sql).getSingleResult()).intValue();
	}
	
	@Override
	public List<LabRoomDevice> findLabRoomDevice(LabRoomDevice labRoomDevice, Integer page, Integer pageSize) {
		// TODO Auto-generated method stub
		String sql="select l from LabRoomDevice l  where 1=1";
		if(labRoomDevice!=null){
		if(labRoomDevice.getSchoolDevice() != null && labRoomDevice.getSchoolDevice().getDeviceName()!=null&&!labRoomDevice.getSchoolDevice().getDeviceName().equalsIgnoreCase("")){
		
			sql+=" and l.schoolDevice.deviceName like '%"+labRoomDevice.getSchoolDevice().getDeviceName()+"%'";
		}
		if(labRoomDevice.getSchoolDevice() != null && labRoomDevice.getSchoolDevice().getDeviceNumber()!=null&&!labRoomDevice.getSchoolDevice().getDeviceNumber().equalsIgnoreCase("")){
			
			sql+=" and l.schoolDevice.deviceNumber like '%"+labRoomDevice.getSchoolDevice().getDeviceNumber()+"%'";
		}
		if(labRoomDevice.getLabRoom() != null && labRoomDevice.getLabRoom().getId()!=null&&!labRoomDevice.getLabRoom().getId().equals("")){
			
			sql+=" and l.labRoom.id = "+labRoomDevice.getLabRoom().getId();
		}
		}
		/*sql+=" order by l.CConsumables desc";*/
		//给语句添加分页机制；
		List<LabRoomDevice> schoolDevices=labRoomDeviceDAO.executeQuery(sql,(page-1)*pageSize,pageSize);
		return schoolDevices;
	}
	
	@Override
	public List<SchoolDevice> findSchoolDeviceBySchoolDevice(SchoolDevice schoolDevice, int page,
			int pageSize) {
		// TODO Auto-generated method stub
				String sql="select l from SchoolDevice l where 1=1";
				if(schoolDevice!=null){
				if(schoolDevice.getDeviceName()!=null&&!schoolDevice.getDeviceName().equalsIgnoreCase("")){
				
					sql+=" and l.deviceName like '%"+schoolDevice.getDeviceName()+"%'";
				}
				if(schoolDevice.getDeviceNumber()!=null&&!schoolDevice.getDeviceNumber().equalsIgnoreCase("")){
					
					sql+=" and l.deviceNumber like '%"+schoolDevice.getDeviceNumber()+"%'";
				}
				}
		
		List<SchoolDevice> schoolDevices=schoolDeviceDAO.executeQuery(sql,(page-1)*pageSize,pageSize);
		return schoolDevices;
	}
	
	@Override
	public List<User> findUserByUser(User user) {
		// TODO Auto-generated method stub
		String sql="select l from User l where 1=1";
		if(user!=null){
		if(user.getCname()!=null&&!user.getCname().equalsIgnoreCase("")){
		
			sql+=" and l.cname like '%"+user.getCname()+"%'";
		}
		}
		/*sql+=" order by l.CConsumables desc";*/
		//给语句添加分页机制；
		List<User> users=userDAO.executeQuery(sql,0,-1);
		return users;
	}
	
	@Override
	public List<User> findUserByUser(User user, int page,
			int pageSize) {
		// TODO Auto-generated method stub
				String sql="select l from User l where 1=1";
				if(user!=null){
				if(user.getCname()!=null&&!user.getCname().equalsIgnoreCase("")){
				
					sql+=" and l.cname like '%"+user.getCname()+"%'";
				}
				}
		
		List<User> users=userDAO.executeQuery(sql,(page-1)*pageSize,pageSize);
		return users;
	}
	
	@Override
	public List<SchoolAcademy> findSchoolAcademyBySchoolAcademy(SchoolAcademy schoolAcademy) {
		// TODO Auto-generated method stub
		String sql="select sa from SchoolAcademy sa where 1=1 ";
		if(schoolAcademy!=null){
		if(schoolAcademy.getAcademyName()!=null&&!schoolAcademy.getAcademyName().equalsIgnoreCase("")){
		
			sql+=" and sa.academyName like '%"+schoolAcademy.getAcademyName()+"%'";
		}
		}
		/*sql+=" order by l.CConsumables desc";*/
		//给语句添加分页机制；
		List<SchoolAcademy> schoolAcademys=schoolAcademyDAO.executeQuery(sql,0,-1);
		return schoolAcademys;
	}
	
	@Override
	public int getCountSchoolAcademyBySchoolAcademy(SchoolAcademy schoolAcademy) {
		// TODO Auto-generated method stub
		String sql="select count(*) from SchoolAcademy sa where 1=1 ";
		if(schoolAcademy!=null){
			if(schoolAcademy.getAcademyName()!=null&&!schoolAcademy.getAcademyName().equalsIgnoreCase("")){
				
				sql+=" and sa.academyName like '%"+schoolAcademy.getAcademyName()+"%'";
			}
		}
		/*sql+=" order by l.CConsumables desc";*/
		//给语句添加分页机制；
		//List<SchoolAcademy> schoolAcademys=schoolAcademyDAO.executeQuery(sql,0,-1);
		return ((Long) schoolAcademyDAO.createQuerySingleResult(sql).getSingleResult()).intValue();
	}
	
	@Override
	public List<SchoolAcademy> findSchoolAcademyBySchoolAcademy(SchoolAcademy schoolAcademy, int page,
			int pageSize) {
		// TODO Auto-generated method stub
				String sql="select sa from SchoolAcademy sa where 1=1 ";
				if(schoolAcademy!=null){
				if(schoolAcademy.getAcademyName()!=null&&!schoolAcademy.getAcademyName().equalsIgnoreCase("")){
				
					sql+=" and sa.academyName like '%"+schoolAcademy.getAcademyName()+"%'";
				}
				}
		
		List<SchoolAcademy> schoolAcademys=schoolAcademyDAO.executeQuery(sql,(page-1)*pageSize,pageSize);
		return schoolAcademys;
	}
	
	@Override
	public List<SchoolAcademy> findSchoolAcademyBySchoolAcademy1(SchoolAcademy schoolAcademy) {
		// TODO Auto-generated method stub
		String sql="select sa from SchoolAcademy sa where 1=1";
		if(schoolAcademy!=null){
		if(schoolAcademy.getAcademyName()!=null&&!schoolAcademy.getAcademyName().equalsIgnoreCase("")){
		
			sql+=" and sa.academyName like '%"+schoolAcademy.getAcademyName()+"%'";
		}
		}
		String sql1="select sa from SchoolAcademy sa where sa.academyNumber like '02__' and sa.academyNumber between '0201' and '0213'";
		List<SchoolAcademy> findAcademys=schoolAcademyDAO.executeQuery(sql1,0,-1);
		/*sql+=" order by l.CConsumables desc";*/
		//给语句添加分页机制；
		List<SchoolAcademy> schoolAcademys=schoolAcademyDAO.executeQuery(sql,0,-1);
		schoolAcademys.removeAll(findAcademys);
		return schoolAcademys;
	}
	
	@Override
	public int getCountSchoolAcademyBySchoolAcademy1(SchoolAcademy schoolAcademy) {
		// TODO Auto-generated method stub
		String sql="select count(*) from SchoolAcademy sa where 1=1";
		if(schoolAcademy!=null){
			if(schoolAcademy.getAcademyName()!=null&&!schoolAcademy.getAcademyName().equalsIgnoreCase("")){
				
				sql+=" and sa.academyName like '%"+schoolAcademy.getAcademyName()+"%'";
			}
		}
		String sql1="select count(*) from SchoolAcademy sa where sa.academyNumber like '02__' and sa.academyNumber between '0201' and '0213'";
		//List<SchoolAcademy> findAcademys=schoolAcademyDAO.executeQuery(sql1,0,-1);
		/*sql+=" order by l.CConsumables desc";*/
		//给语句添加分页机制；
		//List<SchoolAcademy> schoolAcademys=schoolAcademyDAO.executeQuery(sql,0,-1);
		//schoolAcademys.removeAll(findAcademys);
		int schoolAcademysCount = ((Long) schoolAcademyDAO.createQuerySingleResult(sql).getSingleResult()).intValue();
		int findAcademysCount = ((Long) schoolAcademyDAO.createQuerySingleResult(sql1).getSingleResult()).intValue();
		return (schoolAcademysCount-findAcademysCount);
	}
	
	@Override
	public List<SchoolAcademy> findSchoolAcademyBySchoolAcademy1(SchoolAcademy schoolAcademy, int page,
			int pageSize) {
		// TODO Auto-generated method stub
				String sql="select sa from SchoolAcademy sa where 1=1";
				if(schoolAcademy!=null){
				if(schoolAcademy.getAcademyName()!=null&&!schoolAcademy.getAcademyName().equalsIgnoreCase("")){
				
					sql+=" and sa.academyName like '%"+schoolAcademy.getAcademyName()+"%'";
				}
				}
				String sql1="select sa from SchoolAcademy sa where sa.academyNumber like '02__' and sa.academyNumber between '0201' and '0213'";
				List<SchoolAcademy> findAcademys=schoolAcademyDAO.executeQuery(sql1,0,-1);	
		
		List<SchoolAcademy> schoolAcademys=schoolAcademyDAO.executeQuery(sql,(page-1)*pageSize,pageSize);
		schoolAcademys.removeAll(findAcademys);
		return schoolAcademys;
	}
	
	/*************************************************************************************
	 * @內容：查看所有的预约的列表安排
	 * @作者： 魏誠
	 * @日期：2014-07-24
	 *************************************************************************************/
	public List<TimetableAppointment> getTimetableAppointmentsByQuery(TimetableAppointment timetableAppointment,
			int status, int curr, int size) {
		// 创建根据学期来查询课程；
		StringBuffer sql = new StringBuffer(
				"select c from TimetableAppointment c where 1=1 and c.schoolCourse.schoolAcademy.academyNumber like '%"	+ shareService.getUserDetail().getSchoolAcademy().getAcademyNumber() + "%'");
		if (status != -1) {
			sql.append(" and c.status = " + status);
		}
		if (timetableAppointment.getSchoolCourse()!=null&&timetableAppointment.getSchoolCourse().getCourseNo() != null) {
			sql.append(" and c.schoolCourse.courseNo like '%" + timetableAppointment.getSchoolCourse().getCourseNo() + "%'");
		}
		// 按照课程排序
		sql.append(" order by c.schoolCourse.courseNo,c.courseCode ,c.weekday desc ");
		List<TimetableAppointment> timetableAppointments = timetableAppointmentDAO
				.executeQuery(sql.toString(), curr * size, size);
		return timetableAppointments;
	}

	/*************************************************************************************
	 * @內容：查看计数的所有时间列表安排
	 * @作者： 魏誠
	 * @日期：2014-07-24
	 *************************************************************************************/
	public int getCountTimetableAppointmentsByQuery(TimetableAppointment timetableAppointment,int status) {
		// 创建根据学期来查询课程；
		StringBuffer sql = new StringBuffer(
				"select count(*)  from TimetableAppointment c where 1=1 and c.schoolCourse.schoolAcademy.academyNumber like '%"	+ shareService.getUserDetail().getSchoolAcademy().getAcademyNumber() + "%'");
		if (status != -1) {
			sql.append(" and c.status = " + status);
		}
		if (timetableAppointment.getSchoolCourse()!=null&&timetableAppointment.getSchoolCourse().getCourseNo() != null) {
			sql.append(" and c.schoolCourse.courseNo like '%" + timetableAppointment.getSchoolCourse().getCourseNo() + "%'");
		}
		// 将query添加到sb1后
		return ((Long) timetableAppointmentDAO.createQuerySingleResult(
				sql.toString()).getSingleResult()).intValue();
	}

	@Override
	public List<SchoolDevice> findSchoolDeviceNotInLabRoomDevice() {
		// TODO Auto-generated method stub
		List<LabRoomDevice> devices = this.findLabRoomDevice(new LabRoomDevice(),1,-1);
		String sql="select l from SchoolDevice l where 1=1";
		if(devices != null && devices.size() != 0){
			int count = 0;
			sql += " and deviceNumber not in (";
			for(LabRoomDevice l:devices){
				if(count == 0)
				{
					sql += "'"+l.getSchoolDevice().getDeviceNumber()+"'";
					count++;
				}
				else{
					sql += " ,'"+l.getSchoolDevice().getDeviceNumber()+"'";
				}
			}
			sql+=" )";
		}
		List<SchoolDevice> schoolDevices=schoolDeviceDAO.executeQuery(sql,0,-1);
		return schoolDevices;
	}
	
}
