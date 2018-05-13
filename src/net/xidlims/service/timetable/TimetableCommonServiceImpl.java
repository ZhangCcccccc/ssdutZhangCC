package net.xidlims.service.timetable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import net.xidlims.dao.LabRoomDeviceDAO;
import net.xidlims.dao.LabRoomDeviceReservationDAO;
import net.xidlims.dao.SchoolCourseDetailDAO;
import net.xidlims.dao.SchoolWeekDAO;
import net.xidlims.dao.SystemTimeDAO;
import net.xidlims.domain.LabRoomDevice;
import net.xidlims.domain.LabRoomDeviceReservation;
import net.xidlims.domain.SchoolCourseDetail;
import net.xidlims.domain.SchoolWeek;
import net.xidlims.domain.SystemTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("TimetableCommonService")
public class TimetableCommonServiceImpl implements TimetableCommonService {

	@Autowired
	private LabRoomDeviceDAO labRoomDeviceDAO;
	@Autowired
	private SystemTimeDAO systemTimeDAO;
	@Autowired
	private SchoolCourseDetailDAO schoolCourseDetailDAO;
	@Autowired
	private SchoolWeekDAO schoolWeekDAO;
	@Autowired
	private LabRoomDeviceReservationDAO labRoomDeviceReservationDAO;

	/*************************************************************************************
	 * @throws ParseException
	 * @內容：对SchoolCourseDetail所属的选课组进行实验室设备的判冲处理
	 * @作者： 魏誠
	 * @日期：2014-07-24
	 *************************************************************************************/
	@SuppressWarnings("unused")
	public String getLabroomDeviceValid(String[] devices, String courseCode) throws ParseException {
		List<SchoolCourseDetail> schoolCourseDetails = schoolCourseDetailDAO
				.executeQuery("select c from SchoolCourseDetail c where c.schoolCourse.courseCode like '" + courseCode
						+ "'");

		// 如果选择了实验设备
		if (devices.length != 0) {
			for (String id : devices) {
				LabRoomDevice labroomDevice = labRoomDeviceDAO.findLabRoomDeviceById(Integer.parseInt(id));
				for (SchoolCourseDetail schoolCourseDetail : schoolCourseDetails) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					for (int i = 0; i < schoolCourseDetail.getEndClass() - schoolCourseDetail.getStartClass(); i++) {
						// 根据排课，获取相关的节次
						SystemTime systemTime = systemTimeDAO.findSystemTimeById(i+schoolCourseDetail.getStartClass());
						// 当前节次开始时间的HH:mm:ss格式
						String startHms = sdf.format(systemTime.getStartDate().getTime()).split(" ")[1];

						// 当前节次结束时间的hh:mm:ss格式
						String endHms = sdf.format(systemTime.getEndDate().getTime()).split(" ")[1];

						for (int j = 0; j < schoolCourseDetail.getEndWeek() - schoolCourseDetail.getStartWeek(); j++) {
							// 根据周次和星期，获取相关的时间
							List<SchoolWeek> schoolWeeks = schoolWeekDAO
									.executeQuery("select c from SchoolWeek c where c.weekday="
											+ schoolCourseDetail.getWeekday() + " and c.week =" + (j+schoolCourseDetail.getStartWeek())
											+ " and c.schoolTerm.id =" + schoolCourseDetail.getSchoolTerm().getId());
							// 当前周次的时间yyyy-mm-dd格式
							String weekYmd = sdf.format(schoolWeeks.get(0).getDate().getTime()).split(" ")[0];
							
							Date startdate = sdf.parse(weekYmd + " " + startHms);
							Date enddate = sdf.parse(weekYmd + " " + endHms);
							// 循环遍历设备预约表lab_room_device_reservation判断是否有冲突，如果有，则提示返回
							List<LabRoomDeviceReservation> labRoomDeviceReservations = labRoomDeviceReservationDAO
									.executeQuery("select c from LabRoomDeviceReservation c where c.labRoomDevice.id ="
											+ labroomDevice.getId() + " and c.begintime> '" + sdf.format(new Date()) + "'", 0, -1);
							for (LabRoomDeviceReservation labRoomDeviceReservation :labRoomDeviceReservations ) {
								
								if (enddate.compareTo(labRoomDeviceReservation.getBegintime().getTime()) >= 0&&startdate.compareTo(labRoomDeviceReservation.getBegintime().getTime())<= 0
										|| enddate.compareTo(labRoomDeviceReservation.getEndtime().getTime()) >= 0&&startdate.compareTo(labRoomDeviceReservation.getEndtime().getTime()) <= 0
										|| startdate.compareTo(labRoomDeviceReservation.getBegintime().getTime()) >= 0&&enddate.compareTo(labRoomDeviceReservation.getEndtime().getTime()) <= 0) {
									String a = sdf.format(labRoomDeviceReservation.getEndtime().getTime());
									return "no";
								}
							}
						}
					}
				}
			}

		}
		return "yes";

	}

}