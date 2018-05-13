package net.xidlims.service.timetable;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.common.LabAttendance;
import net.xidlims.service.common.ShareService;
import net.xidlims.service.lab.LabCenterService;
import net.xidlims.web.timetable.AttendancetableByWeek;
import net.xidlims.web.timetable.CheckAttendancetable;
import net.xidlims.dao.CommonHdwlogDAO;
import net.xidlims.dao.LabCenterDAO;
import net.xidlims.dao.LabRoomAgentDAO;
import net.xidlims.dao.SchoolCourseStudentDAO;
import net.xidlims.dao.SchoolWeekDAO;
import net.xidlims.dao.SystemTimeDAO;
import net.xidlims.dao.TimetableAppointmentDAO;
import net.xidlims.dao.TimetableAttendanceDAO;
import net.xidlims.dao.TimetableCourseStudentDAO;
import net.xidlims.dao.TimetableLabRelatedDAO;
import net.xidlims.dao.UserDAO;
import net.xidlims.domain.CommonHdwlog;
import net.xidlims.domain.LabCenter;
import net.xidlims.domain.LabRoomAgent;
import net.xidlims.domain.SchoolCourse;
import net.xidlims.domain.SchoolCourseDetail;
import net.xidlims.domain.SchoolCourseStudent;
import net.xidlims.domain.SchoolWeek;
import net.xidlims.domain.SystemTime;
import net.xidlims.domain.TimetableAppointment;
import net.xidlims.domain.TimetableAttendance;
import net.xidlims.domain.TimetableCourseStudent;
import net.xidlims.domain.TimetableGroup;
import net.xidlims.domain.TimetableGroupStudents;
import net.xidlims.domain.TimetableLabRelated;
import net.xidlims.domain.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import excelTools.Attendance;

/**
 * Spring service that handles CRUD requests for TimetableAttendance entities
 * 
 */

@Service("TimetableAttendanceService")
@Transactional
public class TimetableAttendanceServiceImpl implements TimetableAttendanceService {

	/**
	 * DAO injected by Spring that manages TimetableAttendance entities
	 * 
	 */
	@Autowired
	private TimetableAttendanceDAO timetableAttendanceDAO;

	/**
	 * DAO injected by Spring that manages User entities
	 * 
	 */
	@Autowired
	private UserDAO userDAO;

	@Autowired
	private TimetableAppointmentDAO timetableAppointmentDAO;

	@Autowired
	private SchoolWeekDAO schoolWeekDAO;

	@Autowired
	private SystemTimeDAO systemTimeDAO;

	@Autowired
	private LabRoomAgentDAO labRoomAgentDAO;

	@Autowired
	private TimetableLabRelatedDAO timetableLabRelatedDAO;

	@Autowired
	private CommonHdwlogDAO commonHdwlogDAO;

	@Autowired
	private SchoolCourseStudentDAO schoolCourseStudentDAO;

	@Autowired
	private ShareService shareService;
	@Autowired
	private LabCenterService labCenterService;
	@Autowired
	private LabCenterDAO labCenterDAO;
	@Autowired
	private TimetableCourseStudentDAO timetableCourseStudentDAO;

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Instantiates a new TimetableAttendanceServiceImpl.
	 * 
	 */
	public TimetableAttendanceServiceImpl() {
	}

	/**
	 * Save an existing TimetableAttendance entity
	 * 
	 */
	@Transactional
	public void saveTimetableAttendance(TimetableAttendance timetableattendance) {

		TimetableAttendance existingTimetableAttendance = timetableAttendanceDAO
				.findTimetableAttendanceByPrimaryKey(timetableattendance.getId());

		if (existingTimetableAttendance != null) {
			if (existingTimetableAttendance != timetableattendance) {
				existingTimetableAttendance.setId(timetableattendance.getId());
				existingTimetableAttendance.setAttendDate(timetableattendance.getAttendDate());
				existingTimetableAttendance.setAttendanceMachine(timetableattendance.getAttendanceMachine());
				existingTimetableAttendance.setActualAttendance(timetableattendance.getActualAttendance());
				existingTimetableAttendance.setMemo(timetableattendance.getMemo());
				existingTimetableAttendance.setWeekday(timetableattendance.getWeekday());
				existingTimetableAttendance.setWeek(timetableattendance.getWeek());
				existingTimetableAttendance.setArrangeClass(timetableattendance.getArrangeClass());
				existingTimetableAttendance.setCreatedDate(timetableattendance.getCreatedDate());
				existingTimetableAttendance.setUpdatedDate(timetableattendance.getUpdatedDate());
				existingTimetableAttendance.setAttendanceStatus(timetableattendance.getAttendanceStatus());
			}
			timetableattendance = timetableAttendanceDAO.store(existingTimetableAttendance);
		} else {
			timetableattendance = timetableAttendanceDAO.store(timetableattendance);
		}
		timetableAttendanceDAO.flush();
	}

	@Transactional
	public void saveTimetableAttendance(TimetableAttendance timetableattendance, Integer id, Integer idKey) {
		TimetableAppointment s = timetableAppointmentDAO.findTimetableAppointmentById(id);

		timetableattendance.setAttendDate(Calendar.getInstance());
		timetableattendance.setCreatedDate(Calendar.getInstance());
		timetableattendance.setUpdatedDate(Calendar.getInstance());
		// 获取USER对象
		// timetableattendance.setUserByDetailId(s.getSchoolCourseDetail().getUser());
		timetableattendance.setWeekday(s.getWeekday());
		timetableattendance.setWeek(idKey);
		timetableattendance.setActualAttendance(1);
		// timetableattendance.setUserByUserNumber();
		// 获取USER对象
		// timetableattendance.setUserByCreatedBy(s.);
		timetableAttendanceDAO.store(timetableattendance);
		timetableAttendanceDAO.flush();
	}

	/**
	 */
	@Transactional
	public TimetableAttendance findTimetableAttendanceByPrimaryKey(Integer id) {
		return timetableAttendanceDAO.findTimetableAttendanceByPrimaryKey(id);
	}

	/**
	 * Delete an existing TimetableAttendance entity
	 * 
	 */
	@Transactional
	public void deleteTimetableAttendance(TimetableAttendance timetableattendance) {
		timetableAttendanceDAO.remove(timetableattendance);
		timetableAttendanceDAO.flush();
	}

	/*************************************************************************************
	 * @內容：查看计数的所有时间列表安排
	 * @作者： 魏誠
	 * @日期：2014-07-24
	 *************************************************************************************/
	public int getCountTimetableAppointmentsByQuery(int status) {
		// 创建根据学期来查询课程；
		StringBuffer sql = new StringBuffer("select count(*)  from TimetableAppointment c ");
		if (status != -1) {
			sql.append("where c.status = " + status);
		}
		// 将query添加到sb1后
		return ((Long) timetableAppointmentDAO.createQuerySingleResult(sql.toString()).getSingleResult()).intValue();
	}

	public String[] getTimetableWeekClass(int[] intWeeks) {
		String startWeek = "1";
		String endWeek = "1";
		String sWeek = "";
		Arrays.sort(intWeeks);
		// 创建根据学期来查询课程；
		for (int i = 0; i < intWeeks.length; i++) {

			if (i == 0) {
				startWeek = String.valueOf(intWeeks[i]);
				if (intWeeks.length == 1) {
					sWeek = startWeek + ";";
				}
			} else {
				if (intWeeks[i] - intWeeks[i - 1] == 1) {
					if (i == intWeeks.length - 1) {
						endWeek = String.valueOf(intWeeks[i]);
						sWeek = sWeek + startWeek + "-" + endWeek + ";";
					} else {
						continue;
					}
				} else if (intWeeks[i] - intWeeks[i - 1] > 1 && intWeeks.length > i + 1) {
					endWeek = String.valueOf(intWeeks[i - 1]);
					sWeek = sWeek + startWeek + "-" + endWeek + ";";
					startWeek = String.valueOf(intWeeks[i]);

				} else if (intWeeks[i] - intWeeks[i - 1] > 1 && intWeeks.length == i + 1) {
					endWeek = String.valueOf(intWeeks[i - 1]);
					sWeek = sWeek + startWeek + "-" + endWeek + ";";
					sWeek = sWeek + String.valueOf(intWeeks[i]) + "-" + String.valueOf(intWeeks[i]);
				}
			}
		}
		return sWeek.split(";");
	}

	/*************************************************************************************
	 * @內容：查看所有的预约的列表安排
	 * @作者： 魏誠
	 * @日期：2014-07-24
	 *************************************************************************************/
	public List<TimetableAppointment> getTimetableAppointmentsByQuery(TimetableAppointment timetableAppointment,
			int status, int iLabCenter, int curr, int size) {
		LabCenter labCenter = labCenterDAO.findLabCenterById(iLabCenter);
		// 创建根据学期来查询课程；
		StringBuffer sql = new StringBuffer("select c from TimetableAppointment c where 1=1");

		/*if (iLabCenter != -1) {
			sql.append(" and c.schoolCourse.schoolAcademy.academyNumber='"
					+ shareService.getUser().getSchoolAcademy().getAcademyNumber() + "'");
		} else {
			sql.append(" and c.schoolCourse.schoolAcademy.academyNumber='"
					+ shareService.getUser().getSchoolAcademy().getAcademyNumber() + "'");
		}*/

		if (status != -1) {
			sql.append(" and c.status = " + status);
		}
		if (timetableAppointment.getSchoolCourse() != null
				&& timetableAppointment.getSchoolCourse().getCourseNo() != null) {
			sql.append(" and c.schoolCourse.courseNo like '%" + timetableAppointment.getSchoolCourse().getCourseNo()
					+ "%'");
		}
		// 按照课程排序
		sql.append(" order by c.schoolCourse.courseNo,c.courseCode ,c.weekday desc ");
		List<TimetableAppointment> timetableAppointments = timetableAppointmentDAO.executeQuery(sql.toString(), curr
				* size, size);
		return timetableAppointments;
	}

	/*************************************************************************************
	 * @內容：查看计数的所有时间列表安排
	 * @作者： 魏誠
	 * @日期：2014-07-24
	 *************************************************************************************/
	public int getCountTimetableAppointmentsByQuery(TimetableAppointment timetableAppointment, int status,
			int iLabCenter) {
		LabCenter labCenter = labCenterDAO.findLabCenterById(iLabCenter);
		// 创建根据学期来查询课程；lll
		StringBuffer sql = new StringBuffer("select count(*) from TimetableAppointment c where 1=1");

		/*
		 * StringBuffer sql = new StringBuffer(
		 * "select count(*) from TimetableAppointment c where c.schoolCourse.schoolAcademy.academyNumber'"
		 * +shareService.getUser().getSchoolAcademy().getAcademyNumber()+"'");
		 */
		if (shareService.getUser().getSchoolAcademy() != null) {
			sql.append(" and c.schoolCourse.schoolAcademy.academyNumber='"
					+ shareService.getUser().getSchoolAcademy().getAcademyNumber() + "'");
		}

		if (status != -1) {
			sql.append(" and c.status = " + status);
		}
		if (timetableAppointment.getSchoolCourse() != null
				&& timetableAppointment.getSchoolCourse().getCourseNo() != null) {
			sql.append(" and c.schoolCourse.courseNo like '%" + timetableAppointment.getSchoolCourse().getCourseNo()
					+ "%'");
		}

		// 将query添加到sb1后
		return ((Long) timetableAppointmentDAO.createQuerySingleResult(sql.toString()).getSingleResult()).intValue();
	}

	/*************************************************************************************
	 * @內容：查看所有的预约的列表安排
	 * @作者： 魏誠
	 * @日期：2014-07-24
	 *************************************************************************************/
	public List<TimetableAppointment> getTimetableAppointmentsByQuery(int status,
			SchoolCourseDetail schoolCourseDetail, int curr, int size) {
		// 创建根据学期来查询课程；
		StringBuffer sql = new StringBuffer("select c from TimetableAppointment c where 1=1 ");
		if (status != -1) {
			sql.append(" and  c.status = " + status);
		}
		if (schoolCourseDetail != null && schoolCourseDetail.getId() != -1) {

			sql.append(" and c.id=" + schoolCourseDetail.getId());
		}
		// 按照课程排序
		sql.append(" order by c.schoolCourse.courseNo,c.courseCode ,c.weekday desc ");
		List<TimetableAppointment> timetableAppointments = timetableAppointmentDAO.executeQuery(sql.toString(), curr
				* size, size);
		return timetableAppointments;
	}

	/*************************************************************************************
	 * @throws ParseException
	 * @內容：机考考勤录入
	 * @作者： 喻泉声
	 * @日期：2014-08-27
	 *************************************************************************************/
	public void saveMachineAttendance(List<TimetableAppointment> timetableAppointment) throws ParseException {

		// 考勤匹配
		String startTime = "";
		String endTime = "";
		String userdate1 = "";
		String userdate2 = "";
		String userdate3 = "";

		// 把分页的排课信息付给t
		List<TimetableAppointment> t = timetableAppointment;

		for (int i = 0; i <= t.size() - 1; i++) {

			// 按照排课周次进行循环
			for (int y = t.get(i).getStartWeek(); y <= t.get(i).getEndWeek(); y++) {

				String sql4 = "select c from SchoolWeek c where c.week=" + y + " order by weekday asc";
				List<SchoolWeek> schoolWeek = schoolWeekDAO.executeQuery(sql4);

				// 年月日时间
				if (t.get(i).getStartClass() != 0 && t.get(i).getEndClass() != 0 && schoolWeek.size() == 7) {

					if (t.get(i).getStartClass() != 1 && t.get(i).getEndClass() != 11) {
						String sql5 = "select c from SystemTime c where c.id=" + (t.get(i).getStartClass() - 1) + "";
						String sql6 = "select c from SystemTime c where c.id=" + (t.get(i).getEndClass() + 1) + "";
						List<SystemTime> systemTime1 = systemTimeDAO.executeQuery(sql5);
						List<SystemTime> systemTime2 = systemTimeDAO.executeQuery(sql6);

						// 三个时间数据转换为String
						// 读取相应的年月日
						SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
						userdate1 = sdf1.format(schoolWeek.get(t.get(i).getWeekday() - 1).getDate().getTime());
						// 读取相应的开始时分秒
						SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm:ss");
						userdate2 = sdf2.format(systemTime1.get(0).getEndDate().getTime());
						// 读取相应的结束时分秒
						SimpleDateFormat sdf3 = new SimpleDateFormat("HH:mm:ss");
						userdate3 = sdf3.format(systemTime2.get(0).getStartDate().getTime());
					}

					if (t.get(i).getStartClass() == 1 && t.get(i).getEndClass() != 11) {
						String sql5 = "select c from SystemTime c where c.id=" + (t.get(i).getStartClass()) + "";
						String sql6 = "select c from SystemTime c where c.id=" + (t.get(i).getEndClass() + 1) + "";
						List<SystemTime> systemTime1 = systemTimeDAO.executeQuery(sql5);
						List<SystemTime> systemTime2 = systemTimeDAO.executeQuery(sql6);

						// 三个时间数据转换为String
						// 读取相应的年月日
						SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
						userdate1 = sdf1.format(schoolWeek.get(t.get(i).getWeekday() - 1).getDate().getTime());
						// 读取相应的开始时分秒
						SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm:ss");
						userdate2 = sdf2.format(systemTime1.get(0).getStartDate().getTime());
						// 读取相应的结束时分秒
						SimpleDateFormat sdf3 = new SimpleDateFormat("HH:mm:ss");
						userdate3 = sdf3.format(systemTime2.get(0).getStartDate().getTime());
					}

					if (t.get(i).getStartClass() != 1 && t.get(i).getEndClass() == 11) {
						String sql5 = "select c from SystemTime c where c.id=" + (t.get(i).getStartClass() - 1) + "";
						String sql6 = "select c from SystemTime c where c.id=" + (t.get(i).getEndClass()) + "";
						List<SystemTime> systemTime1 = systemTimeDAO.executeQuery(sql5);
						List<SystemTime> systemTime2 = systemTimeDAO.executeQuery(sql6);

						// 三个时间数据转换为String
						// 读取相应的年月日
						SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
						userdate1 = sdf1.format(schoolWeek.get(t.get(i).getWeekday() - 1).getDate().getTime());
						// 读取相应的开始时分秒
						SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm:ss");
						userdate2 = sdf2.format(systemTime1.get(0).getEndDate().getTime());
						// 读取相应的结束时分秒
						SimpleDateFormat sdf3 = new SimpleDateFormat("HH:mm:ss");
						userdate3 = sdf3.format(systemTime2.get(0).getEndDate().getTime());

					}

					// 年月日时分秒整合
					startTime = userdate1 + " " + userdate2;
					endTime = userdate1 + " " + userdate3;

					// 需要把labRoomId连接外键

					// 查询到具体排课预约的实验室编号
					String sql10 = "select c from TimetableLabRelated c where c.timetableAppointment.id='"
							+ t.get(i).getId() + "'";
					List<TimetableLabRelated> findLabRoomId = timetableLabRelatedDAO.executeQuery(sql10);
					int theLabRoomId = 0;
					List<LabRoomAgent> labRoomAgent = new ArrayList<LabRoomAgent>();
					if (findLabRoomId.size() > 0) {
						theLabRoomId = findLabRoomId.get(0).getLabRoom().getId();

						// 根据实验室编号获得考勤机IP的集合
						String sql11 = "select c from LabRoomAgent c where c.labRoom.id='" + theLabRoomId + "'";
						labRoomAgent = labRoomAgentDAO.executeQuery(sql11);
					}

					for (int z = 0; z <= labRoomAgent.size() - 1; z++) {

						// 根据年月日时分秒起始和结束时间和考勤机ip查询考勤表
						String sql7 = "select c from CommonHdwlog c where c.datetime between '" + startTime + "' and '"
								+ endTime + "'" + "and c.hardwareid='" + labRoomAgent.get(z).getHardwareIp() + "'";
						List<CommonHdwlog> commonHdwlog = commonHdwlogDAO.executeQuery(sql7);

						if (commonHdwlog.size() != 0) {
							// 在user表中找到卡号对应的人
							String sql9 = "select c from User c where c.cardno='" + commonHdwlog.get(0).getCardnumber()
									+ "'";
							List<User> cardNumberUser = userDAO.executeQuery(sql9);

							if (cardNumberUser.size() != 0) {
								// 在考勤表中查看该姓名、课程号和对应周次有无数据
								String sql8 = "select c from TimetableAttendance c where c.userByUserNumber.username='"
										+ cardNumberUser.get(0).getUsername() + "' and c.timetableAppointment.id="
										+ t.get(i).getId() + " and c.week=" + y + "";

								List<TimetableAttendance> s = timetableAttendanceDAO.executeQuery(sql8);

								if (s.size() == 0) {

									TimetableAttendance timetableAttendance = new TimetableAttendance();

									SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

									Date date = sdf.parse(commonHdwlog.get(0).getDatetime());
									Calendar calendar = Calendar.getInstance();

									calendar.setTime(date);

									// 保存
									timetableAttendance.setAttendDate(calendar);
									timetableAttendance.setUserByUserNumber(cardNumberUser.get(0));
									timetableAttendance.setAttendanceMachine(1);
									timetableAttendance.setActualAttendance(0);
									timetableAttendance.setTimetableAppointment(t.get(i));
									timetableAttendance.setWeekday(t.get(i).getWeekday());
									timetableAttendance.setWeek(y);

									timetableAttendance.setCreatedDate(Calendar.getInstance());
									timetableAttendance.setUpdatedDate(Calendar.getInstance());

									timetableAttendanceDAO.store(timetableAttendance);
									timetableAttendanceDAO.flush();
								}

								else {
									SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

									Date date = sdf.parse(commonHdwlog.get(0).getDatetime());
									Calendar calendar = Calendar.getInstance();

									calendar.setTime(date);

									s.get(0).setAttendDate(calendar);
									s.get(0).setAttendanceMachine(1);

									timetableAttendanceDAO.store(s.get(0));
									timetableAttendanceDAO.flush();
								}
							}
						}
					}
				}
			}
		}
	}

	/*************************************************************************************
	 * @內容：根据排课和周次查询考勤
	 * @作者： 李小龙
	 *************************************************************************************/
	public List<TimetableAttendance> showStudentAttendanceByWeek(TimetableAppointment t, Integer week) {
		String sql = "select t from TimetableAttendance t where t.week=" + week;
		if (t != null && !t.getId().equals("")) {
			sql += " and t.timetableAppointment.id=" + t.getId();
		}
		System.out.println(sql);
		return timetableAttendanceDAO.executeQuery(sql, 0, -1);
	}

	/*************************************************************************************
	 * @throws ParseException
	 * @內容：按周次的考情遍历显示列表2
	 * @作者： 喻泉声
	 * @日期：2014-08-27
	 *************************************************************************************/

	public List<AttendancetableByWeek> showTimetableGroupStudentsByWeek(int studentNumber, TimetableAppointment t,
			List<TimetableGroupStudents> TimetableGroupStudents, @RequestParam Integer id, @RequestParam Integer idKey) {

		List<AttendancetableByWeek> attendancetableByWeek = new ArrayList<AttendancetableByWeek>();
		// 按照学生人数来遍历考勤记录
		for (int i = 0; i < studentNumber; i++) {
			AttendancetableByWeek checkAtt = new AttendancetableByWeek();
			checkAtt.setStudentname(TimetableGroupStudents.get(i).getUser().getCname());
			checkAtt.setStudentnumber(TimetableGroupStudents.get(i).getUser().getUsername());

			String sql2 = "select c from TimetableAttendance c where c.timetableAppointment.id=" + id
					+ " and c.userByUserNumber.username='" + TimetableGroupStudents.get(i).getUser().getUsername()
					+ "' and c.week=" + idKey + "";

			List<TimetableAttendance> everyweek = timetableAttendanceDAO.executeQuery(sql2);

			if (everyweek.size() == 0) {

				// 新建一条timetable数据
				TimetableAttendance timetableAttendance = new TimetableAttendance();
				timetableAttendance.setUserByUserNumber(TimetableGroupStudents.get(i).getUser());
				timetableAttendance.setUserByCreatedBy(TimetableGroupStudents.get(i).getUser());
				timetableAttendance.setActualAttendance(0);
				timetableAttendance.setTimetableAppointment(t);
				timetableAttendance.setWeekday(t.getWeekday());
				timetableAttendance.setWeek(idKey);
				timetableAttendance.setAttendanceMachine(0);
				TimetableAttendance D = timetableAttendanceDAO.store(timetableAttendance);

				// 保存

				checkAtt.setAttendancetimeid(D.getId());
				checkAtt.setActual_attendance(D.getActualAttendance());
				checkAtt.setMemo(D.getMemo());
				checkAtt.setAttendancetime("");
				checkAtt.setAttendance_machine(D.getAttendanceMachine());

				timetableAttendanceDAO.flush();
			}

			else {

				everyweek.get(0).setUserByCreatedBy(TimetableGroupStudents.get(i).getUser());
				timetableAttendanceDAO.store(everyweek.get(0));
				timetableAttendanceDAO.flush();

				// 往CHECKAtt中添加信息
				checkAtt.setAttendancetimeid(everyweek.get(0).getId());
				checkAtt.setActual_attendance(everyweek.get(0).getActualAttendance());
				checkAtt.setMemo(everyweek.get(0).getMemo());
				checkAtt.setAttendance_machine(everyweek.get(0).getAttendanceMachine());
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

				if (everyweek.get(0).getAttendDate() != null) {
					checkAtt.setAttendancetime(dateFormat.format(everyweek.get(0).getAttendDate().getTime()));
				}

			}

			attendancetableByWeek.add(checkAtt);

		}

		return attendancetableByWeek;
	}

	/*************************************************************************************
	 * @throws ParseException
	 * @內容：按周次保存学生考勤
	 * @作者： 喻泉声
	 * @日期：2014-08-27
	 *************************************************************************************/
	public void saveStudentAttendanceByWeek(String username, Integer id, Integer idKey, Integer flag) {

		TimetableAppointment t = timetableAppointmentDAO.findTimetableAppointmentByPrimaryKey(id);
		String sql = null;
		if (1 == flag) {
			sql = "select c from SchoolCourseStudent c where c.userByStudentNumber.username='" + username + "'";
		}
		if (2 == flag) {
			sql = "select c from TimetableCourseStudent c where c.user.username =" + username;
		}
		// System.out.println("33333="+t);
		// 在考情中判断指定人是否考勤然后进行相关操作
		String sql1 = "select c from TimetableAttendance c where c.timetableAppointment.id='" + t.getId()
				+ "' and c.week=" + idKey + " and c.userByUserNumber.username='" + username + "'";

		List<TimetableAttendance> timetableAttendance1 = timetableAttendanceDAO.executeQuery(sql1);

		if (timetableAttendance1.size() == 0) {

			SchoolCourseStudent schoolCourseStudent = new SchoolCourseStudent();
			TimetableCourseStudent timetableCourseStudent = new TimetableCourseStudent();
			if (1 == flag) {
				schoolCourseStudent = schoolCourseStudentDAO.executeQuery(sql).get(0);
			}
			if (2 == flag) {
				timetableCourseStudent = timetableCourseStudentDAO.executeQuery(sql).get(0);
			}
			TimetableAppointment timetableAppointment = timetableAppointmentDAO
					.findTimetableAppointmentByPrimaryKey(id);

			TimetableAttendance timetableAttendance = new TimetableAttendance();
			if (1 == flag) {
				timetableAttendance.setUserByUserNumber(schoolCourseStudent.getUserByStudentNumber());
				timetableAttendance.setUserByCreatedBy(schoolCourseStudent.getUserByTeacherNumber());
			}
			if (2 == flag) {
				timetableAttendance.setUserByUserNumber(timetableCourseStudent.getUser());
				timetableAttendance.setUserByCreatedBy(timetableCourseStudent.getTimetableSelfCourse().getUser());
			}
			timetableAttendance.setTimetableAppointment(timetableAppointment);
			/*
			 * timetableAttendanceService.saveTimetableAttendance(
			 * timetableAttendance,id,idKey);
			 */

			//
			TimetableAppointment s = timetableAppointmentDAO.findTimetableAppointmentById(id);

			timetableAttendance.setAttendDate(Calendar.getInstance());
			timetableAttendance.setCreatedDate(Calendar.getInstance());
			timetableAttendance.setUpdatedDate(Calendar.getInstance());
			// 获取USER对象
			// timetableattendance.setUserByDetailId(s.getSchoolCourseDetail().getUser());
			timetableAttendance.setWeekday(s.getWeekday());
			timetableAttendance.setWeek(idKey);
			timetableAttendance.setActualAttendance(1);
			// timetableattendance.setUserByUserNumber();
			// 获取USER对象
			// timetableattendance.setUserByCreatedBy(s.);
			timetableAttendanceDAO.store(timetableAttendance);
			timetableAttendanceDAO.flush();
		}

		else {
			timetableAttendance1.get(0).setAttendDate(Calendar.getInstance());
			timetableAttendance1.get(0).setActualAttendance(1);
			timetableAttendanceDAO.store(timetableAttendance1.get(0));
			timetableAttendanceDAO.flush();
		}

	}

	/*************************************************************************************
	 * @throws ParseException
	 * @內容：看到一门课所有学生的考勤表
	 * @作者： 喻泉声
	 * @日期：2014-08-27
	 *************************************************************************************/
	public List<CheckAttendancetable> checkTotalCourseAttendance(Set<TimetableAppointment> listtimeappoints,
			List<SchoolCourseStudent> schoolCourseStudent) {
		List<CheckAttendancetable> checkList = new ArrayList<CheckAttendancetable>();
		for (SchoolCourseStudent s : schoolCourseStudent) {
			CheckAttendancetable check = new CheckAttendancetable();
			check.setStudentname(s.getUserByStudentNumber().getCname());
			check.setStudentnumber(s.getUserByStudentNumber().getUsername());
			check.setClassgroup(s.getSchoolCourseDetail().getSchoolCourse().getCourseCode());
			Map<Integer, String> map = new HashMap<Integer, String>();
			for (TimetableAppointment t : listtimeappoints) {
				for (int i = 1; i < t.getStartWeek(); i++) {
					map.put(i, "--");
				}
				for (int i = t.getStartWeek(); i <= t.getEndWeek(); i++) {
					map.put(i, "应到次数/实到次数");
				}
				int totalWeek = 22;
				for (int i = t.getEndWeek() + 1; i <= totalWeek; i++) {
					map.put(i, "--");
				}
			}
			checkList.add(check);
		}
		return checkList;
	}

	/*************************************************************************************
	 * @內容：查询考勤信息表 timetable_attendance
	 * @作者： 徐龙帅
	 * @日期：2014-12-4
	 *************************************************************************************/
	public List<TimetableAppointment> findTimetableAttendance(TimetableAppointment timetableAppointment) {

		List<TimetableAppointment> timetableAppointments = null;
		if (timetableAppointment.getSchoolCourse() != null) {

			String sql = "select t from TimetableAppointment t,SchoolCourseStudent s where t.schoolCourseDetail.courseDetailNo = s.schoolCourseDetail.courseDetailNo"
					+ " and s.userByStudentNumber.cname like  '"
					+ timetableAppointment.getSchoolCourse().getUserByCreatedBy().getUsername() + "'";
			if (timetableAppointment.getSchoolCourse() != null
					&& timetableAppointment.getSchoolCourse().getSchoolTerm() != null) {
				sql += "and t.schoolCourse.schoolTerm.id="
						+ timetableAppointment.getSchoolCourse().getSchoolTerm().getId();
			}
			// 根据查询条件得到TimetableAttendance对象集合
			List<TimetableAppointment> timetableAppointments1 = timetableAppointmentDAO.executeQuery(sql, 0, -1);
			// System.out.println(timetableAppointments1.size());
			String s = "select t from TimetableAppointment t,TimetableCourseStudent s where t.timetableSelfCourse.id=s.timetableSelfCourse.id and s.user.cname like'"
					+ timetableAppointment.getSchoolCourse().getUserByCreatedBy().getUsername()
					+ ""
					+ "'and t.schoolCourse.schoolTerm.termName"
					+ " like '%"
					+ timetableAppointment.getSchoolCourse().getSchoolTerm().getTermName() + "%'";

			// 根据查询条件得到TimetableAttendance对象集合
			timetableAppointments = timetableAppointmentDAO.executeQuery(s, 0, -1);
			// System.out.println("2222"+timetableAppointments.size());
			for (TimetableAppointment l : timetableAppointments1) {

				timetableAppointments.add(l);
			}
		}
		// System.out.println("33333="+timetableAppointments.size());
		return timetableAppointments;

	}

	public List<TimetableAppointment> findTimetableAttendance(TimetableAppointment timetableAppointment, int page,
			int pageSize) {

		List<TimetableAppointment> timetableAppointments = null;
		if (timetableAppointment.getSchoolCourse() != null) {
			/*
			 * String sql=
			 * "SELECT TimetableAppointment FROM TimetableAppointment, SchoolCourseDetail,SchoolCourseStudent"
			 * +
			 * " WHERE TimetableAppointment.schoolCourseDetail.courseDetailNo=SchoolCourseDetail.courseDetailNo"
			 * +
			 * "and SchoolCourseDetail.courseDetailNo=SchoolCourseStudent.schoolCourseDetail.courseDetailNo and"
			 * + "SchoolCourseStudent.userByStudentNumber.username like '%"+
			 * timetableAppointment
			 * .getSchoolCourse().getUserByCreatedBy().getUsername()+
			 * "%'and TimetableAppointment.schoolCourse.schoolTerm.termName like '%"
			 * +
			 * timetableAppointment.getSchoolCourse().getSchoolTerm().getTermName
			 * ()+"%'";
			 */
			// 根据查询条件得到TimetableAttendance对象集合

			String sql = "select t from TimetableAppointment t,SchoolCourseStudent s where t.schoolCourseDetail.courseDetailNo = s.schoolCourseDetail.courseDetailNo"
					+ " and s.userByStudentNumber.cname like  '"
					+ timetableAppointment.getSchoolCourse().getUserByCreatedBy().getUsername() + "'";
			if (timetableAppointment.getSchoolCourse() != null
					&& timetableAppointment.getSchoolCourse().getSchoolTerm() != null) {
				sql += "and t.schoolCourse.schoolTerm.id="
						+ timetableAppointment.getSchoolCourse().getSchoolTerm().getId();
			}
			// 根据查询条件得到TimetableAttendance对象集合
			List<TimetableAppointment> timetableAppointments1 = timetableAppointmentDAO.executeQuery(sql, 0, -1);
			// System.out.println(timetableAppointments1.size());
			String s = "select t from TimetableAppointment t,TimetableCourseStudent s where t.timetableSelfCourse.id=s.timetableSelfCourse.id and s.user.cname like'"
					+ timetableAppointment.getSchoolCourse().getUserByCreatedBy().getUsername()
					+ ""
					+ "'and t.schoolCourse.schoolTerm.termName"
					+ " like '%"
					+ timetableAppointment.getSchoolCourse().getSchoolTerm().getTermName() + "%'";

			// 根据查询条件得到TimetableAttendance对象集合
			timetableAppointments = timetableAppointmentDAO.executeQuery(s, 0, -1);
			// System.out.println("2222"+timetableAppointments.size());
			for (TimetableAppointment l : timetableAppointments1) {

				timetableAppointments.add(l);
			}
		}
		return timetableAppointments;

	}

	/*************************************************************************************
	 * 根据排课记录查询学生名单 李小龙
	 *************************************************************************************/
	@Override
	public Set<User> findStudentsByTimetableAppointment(TimetableAppointment timeAppt) {
		int style = timeAppt.getTimetableStyle();
		Set<User> userSet = new HashSet<User>();
		if (style != 4 && style != 5 && style != 6) {
			Set<SchoolCourseStudent> courseStudents = timeAppt.getSchoolCourseDetail().getSchoolCourseStudents();
			for (SchoolCourseStudent cs : courseStudents) {
				userSet.add(cs.getUserByStudentNumber());
			}
		} else if (style == 4) {// 二次分组排课
			Set<TimetableGroup> groups = timeAppt.getTimetableGroups();
			for (TimetableGroup gs : groups) {
				Set<TimetableGroupStudents> groupStudents = gs.getTimetableGroupStudentses();
				for (TimetableGroupStudents s : groupStudents) {
					userSet.add(s.getUser());
				}
			}
		} else if (style == 5) {// 自主排课
			Set<TimetableCourseStudent> couresStudents = timeAppt.getTimetableSelfCourse().getTimetableCourseStudents();
			for (TimetableCourseStudent cs : couresStudents) {
				userSet.add(cs.getUser());
			}
		} else if (style == 6) {// 自主分组排课(和二次分组排课一致)
			Set<TimetableGroup> groups = timeAppt.getTimetableGroups();
			for (TimetableGroup gs : groups) {
				Set<TimetableGroupStudents> groupStudents = gs.getTimetableGroupStudentses();
				for (TimetableGroupStudents s : groupStudents) {
					userSet.add(s.getUser());
				}
			}
		} else {
			userSet = null;
		}
		return userSet;
	}

	/*************************************************************************************
	 * 根据选课组编号查询学生名单 李小龙
	 *************************************************************************************/
	@Override
	public List<CheckAttendancetable> findStudentsByCourseCode(TimetableAppointment t) {
		// 要返回的集合对象
		List<CheckAttendancetable> checkList = new ArrayList<CheckAttendancetable>();
		// 该学期的周次
		int weeks = shareService.getTermNumber(t);
		if (t.getTimetableStyle() != 5 && t.getTimetableStyle() != 6) {
			System.out.println("进入timetableStyle<5的学生名单··········");
			// 选课组编号
			String courseDetailNo = t.getSchoolCourseDetail().getCourseDetailNo();
			String sql = "select c from SchoolCourseStudent c where c.schoolCourseDetail.courseDetailNo like '"
					+ courseDetailNo + "'";
			List<SchoolCourseStudent> schoolCourseStudents = schoolCourseStudentDAO.executeQuery(sql, 0, -1);
			System.out.println(t.getId() + "-------学生人数：" + schoolCourseStudents.size());
			if (schoolCourseStudents.size() > 0) {

				for (SchoolCourseStudent s : schoolCourseStudents) {
					CheckAttendancetable check = new CheckAttendancetable();
					check.setStudentname(s.getUserByStudentNumber().getCname());
					check.setStudentnumber(s.getUserByStudentNumber().getUsername());
					// 每一周的考勤
					Map<Integer, String> map = new HashMap<Integer, String>();
					for (int i = 1; i <= weeks; i++) {
						/*
						 * String str=
						 * "select a from TimetableAttendance a where a.userByUserNumber.username="
						 * +s.getUserByStudentNumber().getUsername()+
						 * " and a.timetableAppointment.id="
						 * +t.getId()+" and a.week="+i+"";
						 * List<TimetableAttendance>
						 * attList=timetableAttendanceDAO.executeQuery(str,
						 * 0,-1); String str2=
						 * "select a from TimetableAttendance a where a.userByUserNumber.username="
						 * +s.getUserByStudentNumber().getUsername()+
						 * " and a.timetableAppointment.id="
						 * +t.getId()+" and a.week=" +i+
						 * " and (a.actualAttendance=1 or a.attendanceMachine=1)"
						 * ; List<TimetableAttendance>
						 * attList2=timetableAttendanceDAO .executeQuery(str2,
						 * 0,-1); //应到次数 int count=attList.size(); //实到次数 int
						 * count2=attList2.size();
						 */
						Query query = null;
						String str = "select count(*) from timetable_attendance a where a.user_number='"
								+ s.getUserByStudentNumber().getUsername() + "' and a.course_no like '"
								+ t.getSchoolCourse().getCourseNo() + "' and a.week=" + i + "";
						String str2 = "select count(*) from timetable_attendance a where a.user_number='"
								+ s.getUserByStudentNumber().getUsername() + "' and a.course_no like '"
								+ t.getSchoolCourse().getCourseNo() + "' and a.week=" + i
								+ " and (a.actual_attendance=1 or a.attendance_machine=1)";
						query = entityManager.createNativeQuery(str);
						// 应到次数
						BigInteger count = (BigInteger) query.getSingleResult();
						query = entityManager.createNativeQuery(str2);
						// 实到次数
						BigInteger count2 = (BigInteger) query.getSingleResult();
						map.put(i, count2 + "/" + count);
					}
					check.setMap(map);

					checkList.add(check);
				}

			}
		} else {
			System.out.println("进入timetableStyle>=5的学生名单··········");
			Integer timetableSelfCourseID = t.getTimetableSelfCourse().getId();
			String sql = "select c from TimetableCourseStudent c where c.timetableSelfCourse.id ="
					+ timetableSelfCourseID;
			List<TimetableCourseStudent> timetableCourseStudents = timetableCourseStudentDAO.executeQuery(sql, 0, -1);
			if (timetableCourseStudents.size() > 0) {

				for (TimetableCourseStudent s : timetableCourseStudents) {
					CheckAttendancetable check = new CheckAttendancetable();
					check.setStudentname(s.getUser().getCname());
					check.setStudentnumber(s.getUser().getUsername());

					// 每一周的考勤
					Map<Integer, String> map = new HashMap<Integer, String>();
					for (int i = 1; i <= weeks; i++) {
						/*
						 * String str=
						 * "select a from TimetableAttendance a where a.userByUserNumber.username="
						 * +s.getUser().getUsername()+
						 * " and a.timetableAppointment.id="
						 * +t.getId()+" and a.week="+i+"";
						 * List<TimetableAttendance>
						 * attList=timetableAttendanceDAO.executeQuery(str,
						 * 0,-1); String str2=
						 * "select a from TimetableAttendance a where a.userByUserNumber.username="
						 * +s.getUser().getUsername()+
						 * " and a.timetableAppointment.id="
						 * +t.getId()+" and a.week=" +i+
						 * " and (a.actualAttendance=1 or a.attendanceMachine=1)"
						 * ; List<TimetableAttendance>
						 * attList2=timetableAttendanceDAO .executeQuery(str2,
						 * 0,-1); //应到次数 int count=attList.size(); //实到次数 int
						 * count2=attList2.size();
						 */
						Query query = null;
						String str = "";
						String str2 = "";

						str = "select count(*) from timetable_attendance a where a.user_number='"
								+ s.getUser().getUsername() + "' and a.course_no=" + t.getTimetableSelfCourse().getId()
								+ " and a.week=" + i + "";
						str2 = "select count(*) from timetable_attendance a where a.user_number='"
								+ s.getUser().getUsername() + "' and a.course_no = "
								+ t.getTimetableSelfCourse().getId() + " and a.week=" + i
								+ " and (a.actual_attendance=1 or a.attendance_machine=1)";

						query = entityManager.createNativeQuery(str);
						// 应到次数
						BigInteger count = (BigInteger) query.getSingleResult();

						query = entityManager.createNativeQuery(str2);
						// 实到次数
						BigInteger count2 = (BigInteger) query.getSingleResult();
						map.put(i, count2 + "/" + count);
					}
					check.setMap(map);

					checkList.add(check);
				}
			}
		}
		return checkList;
	}

	/*************************************************************************************
	 * 根据学生名单和周次获取学生成绩 李小龙
	 *************************************************************************************/
	@Override
	public List<CheckAttendancetable> getTearmScoreByStudents(Set<User> students, int weeks, TimetableAppointment t) {
		// 要返回的集合对象
		List<CheckAttendancetable> checkList = new ArrayList<CheckAttendancetable>();
		for (User user : students) {
			CheckAttendancetable check = new CheckAttendancetable();
			check.setStudentname(user.getCname());
			check.setStudentnumber(user.getUsername());
			// 成绩数量
			int count = 1;
			// 每一周的成绩
			Map<Integer, String> map = new HashMap<Integer, String>();
			for (int i = 1; i <= weeks; i++) {
				String sql = "select a from TimetableAttendance a where a.userByUserNumber.username="
						+ user.getUsername() + " and a.timetableAppointment.id=" + t.getId() + " and a.week=" + i + " ";
				List<TimetableAttendance> attList = timetableAttendanceDAO.executeQuery(sql, 0, -1);
				for (TimetableAttendance att : attList) {
					// 成绩
					map.put(count, att.getScore() + "");
					count++;
				}
			}
			check.setMap(map);

			checkList.add(check);
		}

		return checkList;
	}

	/*************************************************************************************
	 * 根据成绩结果获取成绩次数（取最大值） 李小龙
	 *************************************************************************************/
	@Override
	public int getTearmScoreTime(List<CheckAttendancetable> checkList) {
		List<Integer> list = new ArrayList<Integer>();
		for (CheckAttendancetable c : checkList) {
			list.add(c.getMap().size());
		}
		// 返回集合的最大值
		return Collections.max(list);
	}

	/*************************************************************************************
	 * 查询出当前登录人可以查看的课程考勤 李小龙
	 *************************************************************************************/
	@Override
	public List<Attendance> findAttendanceBySchoolCourse(SchoolCourse course, int cid) {
		// 要返回的对象
		List<Attendance> attList = new ArrayList<Attendance>();

		String sql = "select t from TimetableAppointment t where t.status=1 ";
		// 中心所属学院
		String academy = "";
		// 当前登录人
		User user = shareService.getUser();
		if (cid != -1) {
			academy = labCenterDAO.findLabCenterByPrimaryKey(cid).getSchoolAcademy().getAcademyNumber();
		} else {
			academy = user.getSchoolAcademy().getAcademyNumber();
		}

		// 角色身份过滤
		// 实验中心主任以及以上：可以看到本中心所有课程
		if (SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString().indexOf("ROLE_SUPERADMIN") != -1
				|| SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString()
						.indexOf("ROLE_EXPERIMENTALTEACHING") != -1
				|| SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString()
						.indexOf("ROLE_PREEXTEACHING") != -1
				|| SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString()
						.indexOf("ROLE_EXCENTERDIRECTOR") != -1) {

			if (academy != null && !academy.equals("")) {
				sql += " and t.schoolCourse.schoolAcademy.academyNumber='" + academy + "' ";
			}
		} else if (SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString()
				.indexOf("ROLE_TEACHER") != -1) {
			// 教师：可以看到他负责上的课程；
			sql += " and t.schoolCourse.userByTeacher.username='" + user.getUsername() + "' ";
		} else {
			// 学生：可以看到他应该上的课程；
			sql = "select t from TimetableAppointment t join SchoolCourse c,SchoolCourseDetail d,SchoolCourseStudent sd on t.schoolCourse.courseNo=c.courseNo and c.courseNo=d.schoolCourse.courseNo and d.courseDetailNo=sd.schoolCourseDetail.courseDetailNo where sd.userByStudentNumber.username='"
					+ user.getUsername() + "' ";
		}

		// 接下来是查询条件的过滤
		if (course != null) {
			if (course.getSchoolTerm() != null && course.getSchoolTerm().getId() != null) {
				sql += " and t.schoolCourse.schoolTerm.id=" + course.getSchoolTerm().getId();
			}
			if (course.getCourseNo() != null && !course.getCourseNo().equals("")) {
				sql += " and t.schoolCourse.courseNo='" + course.getCourseNo() + "' ";
			}
			if (course.getUserByTeacher() != null && course.getUserByTeacher().getUsername() != null
					&& !course.getUserByTeacher().getUsername().equals("")) {
				sql += " and t.schoolCourse.userByTeacher.username='" + course.getUserByTeacher().getUsername() + "' ";
			}
		}
		System.out.println(sql);
		// 排课记录
		List<TimetableAppointment> appList = timetableAppointmentDAO.executeQuery(sql, 0, -1);

		// 课程
		Set<SchoolCourse> courseList = new HashSet<SchoolCourse>();
		for (TimetableAppointment t : appList) {
			if (t.getSchoolCourse() != null) {
				courseList.add(t.getSchoolCourse());
			}
		}
		// 查询的周次
		String week = course.getMemo();
		System.out.println("-------week----------" + week);
		// 接下来遍历课程 统计考勤数据
		for (SchoolCourse schoolCourse : courseList) {
			Attendance att = new Attendance();
			// 选课组编号
			String courseNo = schoolCourse.getCourseNo();
			// 班级人数
			int classesNumber = 0;
			Set<SchoolCourseDetail> courseDetails = schoolCourse.getSchoolCourseDetails();
			if (courseDetails.size() > 0) {
				SchoolCourseDetail d = courseDetails.iterator().next();
				classesNumber = d.getSchoolCourseStudents().size();
			}
			// 考勤次数
			int attendanceTime = 0;
			String attendanceSql = "select t from TimetableAttendance t where t.courseNo='" + courseNo + "' ";
			if (week != null && !week.equals("0") && !week.equals("")) {// 不等于0即查询的周次不是全部
				attendanceSql += " and t.week=" + week;
			}
			System.out.println("-------attendanceSql-------------" + attendanceSql);
			List<TimetableAttendance> attendanceList = timetableAttendanceDAO.executeQuery(attendanceSql, 0, -1);
			if (classesNumber == 0) {
				attendanceTime = 0;
			} else {
				attendanceTime = attendanceList.size() / classesNumber;
			}

			// 统计实到人数
			String peopleSql = "select t from TimetableAttendance t where t.courseNo='" + courseNo + "' ";
			if (week != null && !week.equals("0") && !week.equals("")) {// 不等于0即查询的周次不是全部
				peopleSql += " and t.week=" + week;
			}
			peopleSql += " and t.attendanceMachine=1 or t.actualAttendance=1";
			System.out.println("-------peopleSql-------------" + peopleSql);
			List<TimetableAttendance> atds = timetableAttendanceDAO.executeQuery(peopleSql, 0, -1);
			int people = atds.size();// 实到人数

			// 考勤对象赋值
			att.setCourseNo(courseNo);
			att.setCollege(schoolCourse.getSchoolAcademy().getAcademyName());
			att.setCourseName(schoolCourse.getCourseName());
			att.setTeacher(schoolCourse.getUserByTeacher().getCname());
			att.setTerm(schoolCourse.getSchoolTerm().getTermName());
			att.setClassesNumber(classesNumber + "");
			att.setAttendanceTime(attendanceTime + "");
			att.setPeople(people + "");
			int absence = classesNumber * attendanceTime - people;
			att.setAbsence(absence + "");

			attList.add(att);

		}
		return attList;
	}

	/*************************************************************************************
	 * 查询出当前登录人可以查看的实验室考勤 贺子龙 2015-11-27
	 *************************************************************************************/
	@SuppressWarnings({ "unused", "unchecked" })
	@Override
	public List<LabAttendance> findLabAttendance(String username, String cname, String labName, int cid, int currpage,
			int pageSize) {

		// 中心所属学院
		String academyNumber = labCenterService.findLabCenterByPrimaryKey(cid).getSchoolAcademy().getAcademyNumber();
		String sql = "select academy_number,room_number,username,cname,lab_room_name,datetime from view_lab_attendance where academy_number='"
				+ academyNumber + "'";
		if (username != null && !username.equals("")) {
			sql += " and username like '%" + username + "%'";
		}
		if (cname != null && !cname.equals("")) {
			sql += " and cname like '%" + cname + "%'";
		}
		if (labName != null && !labName.equals("")) {
			sql += " and lab_room_name like '%" + labName + "%'";
		}
		Query query = entityManager.createNativeQuery(sql);

		List<Object[]> labAttendanceList = query.getResultList();

		List<LabAttendance> labRoomAttendList = new ArrayList<LabAttendance>();
		int count = (currpage - 1) * pageSize;
		int counta = 0;
		for (Object[] labRoomAttendance : labAttendanceList) {
			counta++;
			if (counta > (currpage - 1) * pageSize) {
				LabAttendance labAttendance = new LabAttendance();
				labAttendance.setAcademyNumber(labRoomAttendance[0].toString());
				labAttendance.setLabRoomId(Integer.parseInt(labRoomAttendance[1].toString()));
				labAttendance.setUsername(labRoomAttendance[2].toString());
				labAttendance.setCname(labRoomAttendance[3].toString());
				labAttendance.setLabRoomName(labRoomAttendance[4].toString());
				String str = labRoomAttendance[5].toString();
				labAttendance.setAttendanceTime(str.substring(0, str.length() - 2));
				labRoomAttendList.add(labAttendance);
			}
			if (counta > currpage * pageSize) {
				break;
			}
		}
		return labRoomAttendList;
	}

	/*************************************************************************************
	 * 查询出当前登录人可以查看的实验室考勤人数 贺子龙 2015-11-27
	 *************************************************************************************/
	@Override
	public int findLabAttendance(String username, String cname, String labName, int cid) {
		// 中心所属学院
		String academyNumber = labCenterService.findLabCenterByPrimaryKey(cid).getSchoolAcademy().getAcademyNumber();
		String sql = "select count(*) from view_lab_attendance where academy_number='" + academyNumber + "'";
		if (username != null && !username.equals("")) {
			sql += " and username like '%" + username + "%'";
		}
		if (cname != null && !cname.equals("")) {
			sql += " and cname like '%" + cname + "%'";
		}
		if (labName != null && !labName.equals("")) {
			sql += " and lab_room_name like '%" + labName + "%'";
		}
		Query query = entityManager.createNativeQuery(sql);
		BigInteger count = (BigInteger) query.getSingleResult();
		return count.intValue();
	}
}
