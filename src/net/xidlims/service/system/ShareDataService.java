package net.xidlims.service.system;

import java.util.List;

import net.xidlims.domain.LabRoomDevice;
import net.xidlims.domain.SchoolAcademy;
import net.xidlims.domain.SchoolDevice;
import net.xidlims.domain.SystemBuild;
import net.xidlims.domain.SystemRoom;
import net.xidlims.domain.TimetableAppointment;
import net.xidlims.domain.User;

public interface ShareDataService {

	public List<SystemBuild> findSystemBuildBySystemBuild(SystemBuild systemBuild, int page,
			int pageSize);
	
	public List<SystemBuild> findSystemBuildBySystemBuild(SystemBuild systemBuild);
	
	public List<SystemRoom> findSystemRoomBySystemRoom(SystemRoom systemRoom);
	
	public List<SystemRoom> findSystemRoomBySystemRoom(SystemRoom systemRoom, int page,
			int pageSize);
	
	public List<SchoolDevice> findSchoolDeviceBySchoolDevice(SchoolDevice schoolDevice);
	
	public List<SchoolDevice> findSchoolDeviceBySchoolDevice(SchoolDevice schoolDevice, int page,
			int pageSize);
	
	public List<User> findUserByUser(User user);
	
	public List<User> findUserByUser(User user, int page,
			int pageSize);
	
	public List<SchoolAcademy> findSchoolAcademyBySchoolAcademy(SchoolAcademy schoolAcademy);
	
	public List<SchoolAcademy> findSchoolAcademyBySchoolAcademy(SchoolAcademy schoolAcademy, int page,
			int pageSize);
	
	public List<SchoolAcademy> findSchoolAcademyBySchoolAcademy1(SchoolAcademy schoolAcademy);
	
	public List<SchoolAcademy> findSchoolAcademyBySchoolAcademy1(SchoolAcademy schoolAcademy, int page,
			int pageSize);
	
	public List<TimetableAppointment> getTimetableAppointmentsByQuery(TimetableAppointment timetableAppointment,
			int status, int curr, int size);
	
	public int getCountTimetableAppointmentsByQuery(TimetableAppointment timetableAppointment,int status);

	public int getCountSchoolDeviceBySchoolDevice(SchoolDevice schoolDevice);

	public int getCountSchoolAcademyBySchoolAcademy1(SchoolAcademy schoolAcademy);

	public int getCountSchoolAcademyBySchoolAcademy(SchoolAcademy schoolAcademy);

	public int getCountSystemBuildBySystemBuild(SystemBuild systemBuild);

	public int getCountSystemRoomBySystemRoom(SystemRoom systemRoom); 
	
	public int getCountLabRoomDevice(LabRoomDevice labRoomDevice); 
	
	public List<LabRoomDevice> findLabRoomDevice(LabRoomDevice labRoomDevice, Integer page, Integer pageSize);
	
	public List<SchoolDevice> findSchoolDeviceNotInLabRoomDevice();
}
