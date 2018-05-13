package net.xidlims.service.xidapp;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.xidlims.dao.AppClouddickDownloadFileDAO;
import net.xidlims.dao.AppClouddickDownloadFolderDAO;
import net.xidlims.dao.AppGroupDAO;
import net.xidlims.dao.AppPostImagesDAO;
import net.xidlims.dao.AppPostStatusDAO;
import net.xidlims.dao.AppPostlistDAO;
import net.xidlims.dao.AppPostReplyDAO;
import net.xidlims.dao.AppQuestionchooseDAO;
import net.xidlims.dao.AppQuestionnaireDAO;
import net.xidlims.dao.LabReservationDAO;
import net.xidlims.dao.LabReservationTimeTableDAO;
import net.xidlims.dao.LabRoomDAO;
import net.xidlims.dao.OperationItemDAO;
import net.xidlims.dao.SchoolCourseStudentDAO;
import net.xidlims.dao.SchoolWeekdayDAO;
import net.xidlims.dao.SystemTimeDAO;
import net.xidlims.dao.TAssignmentItemDAO;
import net.xidlims.dao.TAssignmentQuestionpoolDAO;
import net.xidlims.dao.TCourseSiteDAO;
import net.xidlims.dao.TCourseSiteUserDAO;
import net.xidlims.dao.TimetableAppointmentDAO;
import net.xidlims.dao.TimetableAppointmentSameNumberDAO;
import net.xidlims.dao.TimetableItemRelatedDAO;
import net.xidlims.dao.TimetableLabRelatedDAO;
import net.xidlims.dao.TimetableSelfCourseDAO;
import net.xidlims.dao.TimetableTeacherRelatedDAO;
import net.xidlims.dao.UserDAO;
import net.xidlims.dao.WkChapterDAO;
import net.xidlims.dao.WkUploadDAO;
import net.xidlims.domain.AppClouddickDownloadFile;
import net.xidlims.domain.AppClouddickDownloadFolder;
import net.xidlims.domain.AppGroup;
import net.xidlims.domain.AppPostImages;
import net.xidlims.domain.AppPostReply;
import net.xidlims.domain.AppPostStatus;
import net.xidlims.domain.AppPostlist;
import net.xidlims.domain.AppQuestionchoose;
import net.xidlims.domain.AppQuestionnaire;
import net.xidlims.domain.LabReservation;
import net.xidlims.domain.LabRoom;
import net.xidlims.domain.OperationItem;
import net.xidlims.domain.TAssignmentAnswer;
import net.xidlims.domain.TAssignmentItem;
import net.xidlims.domain.TAssignmentQuestionpool;
import net.xidlims.domain.TCourseSite;
import net.xidlims.domain.TCourseSiteUser;
import net.xidlims.domain.TimetableAppointment;
import net.xidlims.domain.TimetableAppointmentSameNumber;
import net.xidlims.domain.TimetableItemRelated;
import net.xidlims.domain.TimetableLabRelated;
import net.xidlims.domain.TimetableSelfCourse;
import net.xidlims.domain.TimetableTeacherRelated;
import net.xidlims.domain.User;
import net.xidlims.domain.WkChapter;
import net.xidlims.domain.WkUpload;
import net.xidlims.service.common.MySQLService;
import net.xidlims.service.common.ShareService;
import net.xidlims.service.lab.LabReservationService;
import net.xidlims.service.timetable.OuterApplicationService;
import net.xidlims.service.timetable.TimetableCourseSchedulingService;
import net.xidlims.service.timetable.TimetableSelfSchedulingService;

import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.RequestContext;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import app.xidlims.Album;
import app.xidlims.Annoucement;
import app.xidlims.AnnoucementList;
import app.xidlims.Appointment;
import app.xidlims.GroupBack;
import app.xidlims.LearningChapter;
import app.xidlims.LearningVideoNew;
import app.xidlims.NewAlbum;
import app.xidlims.NewFolder;
import app.xidlims.NewPostList;
import app.xidlims.PostDetail;
import app.xidlims.Question;
import app.xidlims.QuestionNaireDetail;
import app.xidlims.QuestionPool;
import app.xidlims.ReturnDownloadFile;
import app.xidlims.ReturnDownloadFolder;
import app.xidlims.ShareAppPostReplyList;
import app.xidlims.ShareDetail;
import app.xidlims.ShareList;

import com.google.gson.JsonObject;
import com.google.inject.persist.Transactional;
import com.lowagie.text.html.simpleparser.Img;

@Service("AppService")
public class AppServiceImpl implements AppService {
	@PersistenceContext
	EntityManager entityManager;
	@Autowired
	LabRoomDAO labRoomDAO;
	@Autowired
	SchoolCourseStudentDAO schoolCourseStudentDAO;
	@Autowired
	UserDAO userDAO;
	@Autowired
	TCourseSiteUserDAO tCourseSiteUserDAO;
	@Autowired
	ShareService shareService;
	@Autowired
	LabReservationTimeTableDAO labReservationTimeTableDAO;
	@Autowired
	SystemTimeDAO systemTimeDAO;
	@Autowired
	SchoolWeekdayDAO schoolWeekdayDAO;
	@Autowired
	TimetableSelfCourseDAO timetableSelfCourseDAO;
	@Autowired
	TimetableLabRelatedDAO timetableLabRelatedDAO;
	@Autowired
	TimetableAppointmentSameNumberDAO timetableAppointmentSameNumberDAO;
	@Autowired
	TimetableAppointmentDAO timetableAppointmentDAO;
	@Autowired
	TimetableTeacherRelatedDAO timetableTeacherRelatedDAO;
	@Autowired
	OperationItemDAO operationItemDAO;
	@Autowired
	TimetableItemRelatedDAO timetableItemRelatedDAO;
	@Autowired
	WkUploadDAO wkUploadDAO;
	@Autowired
	WkChapterDAO wkChapterDAO;
	@Autowired
	TAssignmentQuestionpoolDAO tAssignmentQuestionpoolDAO;
	@Autowired
	TAssignmentItemDAO tAssignmentItemDAO;
	@Autowired
	AppGroupDAO appGroupDAO;
	@Autowired
	AppPostlistDAO appPostlistDAO;
	@Autowired
	AppPostImagesDAO appPostImagesDAO;
	@Autowired
	AppPostReplyDAO appPostReplyDAO;
	@Autowired
	AppPostStatusDAO appPostStatusDAO;
	@Autowired
	AppQuestionnaireDAO appQuestionnaireDAO;
	@Autowired
	AppQuestionchooseDAO appQuestionchooseDAO;
	@Autowired
	OuterApplicationService outerApplicationService;
	@Autowired
	LabReservationService labReservationService;
	@Autowired
	TimetableSelfSchedulingService timetableSelfSchedulingService;
	@Autowired
	MySQLService mysqlService;
	@Autowired
	TimetableCourseSchedulingService timetableCourseSchedulingService;
	@Autowired
	LabReservationDAO labReservationDAO;
	@Autowired
	AppClouddickDownloadFolderDAO appClouddickDownloadFolderDAO;
	@Autowired
	AppClouddickDownloadFileDAO appClouddickDownloadFileDAO;
	@Autowired
	TCourseSiteDAO tCourseSiteDAO;

	/****************************************************************************
	 * 功能：查询出所有的实验室 作者：张凯 时间：2017-03-28
	 ****************************************************************************/
	@Override
	public String findAllLabRoom(String labRoomName) {
		String sql = "select l from LabRoom l where 1=1 and l.labRoomActive=1 and l.labRoomReservation=1 ";
		if (labRoomName != null && labRoomName.equals("")) {
			sql += " and l.labRoomName like '%" + labRoomName + "%'";
		}
		List<LabRoom> labrooms = labRoomDAO.executeQuery(sql, 0, -1);
		;
		String ids = "";
		for (LabRoom l : labrooms) {
			ids += l.getId() + ",";
		}
		if (!"".equals(ids)) {
			ids = ids.substring(0, ids.length() - 1);
		}
		String sqlLabRoom = " select id,reservation_number,lab_room_number,lab_room_name,lab_room_address "
				+ "from lab_room where lab_room.id in (" + ids + ")";
		return sqlLabRoom;
	}

	/****************************************************************************
	 * 功能：我的课程 作者：张凯 时间：2017-03-29
	 ****************************************************************************/
	@Override
	public String findCoursesByStudent(String username) {
		String sql = "select t from TCourseSiteUser t where t.user.username like '"
				+ username + "'";
		List<TCourseSiteUser> tCourseSiteUsers = tCourseSiteUserDAO
				.executeQuery(sql, 0, -1);
		String siteId = "";
		for (TCourseSiteUser tu : tCourseSiteUsers) {
			siteId += tu.getTCourseSite().getId() + ",";
		}
		String sqlCourse = "select t.id,t.title,t.course_number,user.cname,t.isOpen from t_course_site_user tu "
				+ "inner join t_course_site as t on tu.site_id=t.id "
				+ "inner join user on tu.username=user.username "
				+ "where tu.permission=2";
		if (!siteId.equals("")) {
			siteId = siteId.substring(0, siteId.length() - 1);
			sqlCourse += " and tu.site_id in (" + siteId + ")";
		}
		return sqlCourse;
	}

	/****************************************************************************
	 * 功能：查询学生的班级 作者：张凯 时间：2017-03-30
	 ****************************************************************************/
	@Override
	public String findClassByStudent(String username) {
		User user = userDAO.findUserByPrimaryKey(username);
		String sql = "select username,cname from user where user.classes_number like '"
				+ user.getSchoolClasses().getClassNumber() + "'";
		return sql;
	}

	/****************************************************************************
	 * 功能：学生个人的成绩 作者：张凯 时间：2017-03-31
	 ****************************************************************************/
	@Override
	public String getStudentGrades(String username) {
		String sql = "SELECT t_grade_record.points,school_course.course_name "
				+ "FROM t_grade_record INNER JOIN t_grade_object ON t_grade_record.object_id = t_grade_object.id "
				+ "INNER JOIN t_gradebook ON t_grade_object.grade_id = t_gradebook.id "
				+ "INNER JOIN t_course_site ON t_gradebook.site_id = t_course_site.id "
				+ "INNER JOIN school_course ON t_course_site.course_no = school_course.course_no "
				+ "WHERE t_grade_record.student_number like '" + username + "'";
		return sql;
	}

	/****************************************************************************
	 * 功能：获取可用的实验室周次 作者：张凯 时间：2017-04-26
	 ****************************************************************************/
	@Override
	public String getUnusedWeeks(String labroomID, String weekDay,
			String section) {
		Integer term = shareService
				.getBelongsSchoolTerm(Calendar.getInstance()).getId();
		String[] sections = section.split(",");
		String[] labroomId = labroomID.split(",");
		int[] classes = this.stringsToints(sections);
		int[] labrooms = this.stringsToints(labroomId);
		int day = Integer.parseInt(weekDay);
		Integer[] week = outerApplicationService.getValidLabWeeks(term,
				classes, labrooms, day);
		String weeks = "";
		for (Integer w : week) {
			weeks += w + ",";
		}
		return weeks.equals("") ? weeks : weeks
				.substring(0, weeks.length() - 1);
	}

	/****************************************************************************
	 * 功能：审核预约、保存实验室预约 作者：张凯 时间：2017-04-27
	 * 
	 * @throws ParseException
	 ****************************************************************************/
	@Transactional
	public String auditAndSaveLabReservation(String labroomID, String weekDay,
			String section, String week, String act, String actName,
			String content, String ps, String students, String username)
			throws ParseException {
		LabRoom labroom = labRoomDAO.findLabRoomById(Integer
				.parseInt(labroomID));
		LabReservation labReservation = new LabReservation();
		labReservation.setLabRoom(labroom);
		// 获取学期
		String schoolTerm = shareService
				.getBelongsSchoolTerm(Calendar.getInstance()).getId()
				.toString();
		String[] schoolTermid = schoolTerm.split(",");
		// 获取周次
		String[] Week = week.split(",");
		// 获取节次
		String[] jieci = section.split(",");
		// 获取星期
		String[] systemTime = weekDay.split(",");
		// 获取学生
		// String student = students.split(",");
		String xueqi = shareService
				.getBelongsSchoolTerm(Calendar.getInstance()).getId()
				.toString();
		int sa = 0;
		if (username != null && !username.isEmpty()) {
			User user = userDAO.findUserByPrimaryKey(username);
			labReservation.setUser(user);
		}
		// 将排课时间存储到表timetableAppointment中
		TimetableAppointment timetableAppointment = this
				.saveNoGroupSelfTimetable(labroomID, weekDay, week, section,
						username);
		timetableAppointment.setTimetableStyle(7);
		labReservation.setTimetableAppointment(timetableAppointment);
		// 自主排课的序号为365
		int m = 365;
		// 保存选课组信息
		labReservation.setTimetableSelfCourse(timetableSelfCourseDAO
				.findTimetableSelfCourseById(m));
		if (labReservation.getCDictionaryByActivityCategory() != null
				&& schoolTerm != null && schoolTerm != "" && Week != null
				&& Week.length > 0 && systemTime != null
				&& systemTime.length > 0 && jieci != null && jieci.length > 0) {

			sa = labReservationService.savelabReservation(labReservation,
					Integer.parseInt(labroomID), schoolTerm, Week, systemTime,
					jieci);
			TimetableLabRelated timetableLabRelated = new TimetableLabRelated();
			if (labReservation.getLabRoom().getId() != null) {
				// 将matchLabs添加到matchLabs中
				timetableLabRelated.setLabRoom(labReservation.getLabRoom());
				timetableLabRelated.setTimetableAppointment(labReservation
						.getTimetableAppointment());
				timetableLabRelatedDAO.store(timetableLabRelated);
			}

		}

		if (xueqi != null && xueqi != "" && Week != null && Week.length > 0
				&& weekDay != null && weekDay != "" && jieci != null
				&& jieci.length > 0) {
			sa = labReservationService.savelabReservation1(labReservation,
					Integer.parseInt(labroomID), xueqi, Week, weekDay, jieci);

		}
		/*
		 * if (student != null && student != "") {
		 * this.savelabReservationstudent(sa, student); }
		 */
		if (students != null && students != "") {
			labReservationService.savelabReservationstudent(sa, students);
		}
		labReservation.setId(sa);
		// return labReservation;
		return "success";
	}

	/*************************************************************************************
	 * @throws ParseException
	 * @內容：保存自主排课的不分组排课的内容
	 * @作者： 张凯
	 * @日期：2017-04-28
	 *************************************************************************************/
	@Transactional
	public TimetableAppointment saveNoGroupSelfTimetable(String labroomID,
			String weekday, String week, String section, String username)
			throws ParseException {

		TimetableAppointment timetableAppointment = new TimetableAppointment();
		// 调整排课的实验室选择
		String[] labRooms = labroomID.split(",");
		// 实验设备设置为空，APP模块不牵涉设备预约
		String[] sLabRoomDevice = null;
		// 调整排课的实验项目选择
		String[] items = null;
		// 调整排课的授课教师选择
		String[] teachers = null;
		// String weekday = request.getParameter("weekday");
		// 调整排课的星期选择
		String[] weeks = week.split(",");
		int[] intWeeks = new int[weeks.length];
		char[] consumablesCosts = null;
		Integer groupCount = null;
		for (int i = 0; i < weeks.length; i++) {
			intWeeks[i] = Integer.parseInt(weeks[i]);
		}
		// 周次进行排序
		String[] sWeek = timetableSelfSchedulingService
				.getTimetableWeekClass(intWeeks);
		// 调整排课的节次选择
		String[] classes = section.split(",");
		int[] intClasses = new int[classes.length];
		for (int i = 0; i < classes.length; i++) {
			intClasses[i] = Integer.parseInt(classes[i]);
		}

		// 节次进行排序
		String[] sClasses = timetableSelfSchedulingService
				.getTimetableWeekClass(intClasses);

		// TimetableAppointment();
		// 自主排课的序号
		Integer m = 365;
		TimetableSelfCourse timetableSelfCourse = timetableSelfCourseDAO
				.findTimetableSelfCourseById(m);

		/**
		 * 如果一次排课，排课的周次或节次都是连续的，则保存主表记录
		 **/
		timetableAppointment.setAppointmentNo(timetableSelfCourse
				.getCourseCode() + "-" + timetableSelfCourse.getId());
		// timetableAppointment.setTimetableNumber(timetableAppointment.getTimetableNumber());
		timetableAppointment.setCreatedBy(username);
		timetableAppointment.setCreatedBy(username);
		timetableAppointment.setCreatedDate(Calendar.getInstance());
		timetableAppointment.setUpdatedDate(Calendar.getInstance());
		// 设置排课方式为二次排课的分组排课模式
		timetableAppointment.setTimetableStyle(5);
		timetableAppointment.setSchoolCourseInfo(timetableSelfCourse
				.getSchoolCourseInfo());
		// 设置排课状态为待发布
		timetableAppointment.setStatus(10);
		timetableAppointment.setTimetableSelfCourse(timetableSelfCourse);
		timetableAppointment.setCourseCode(timetableSelfCourse.getCourseCode());
		timetableAppointment.setWeekday(Integer.parseInt(weekday));

		timetableAppointment.setPreparation("");
		timetableAppointment.setGroups(-1);
		timetableAppointment.setLabhours(-1);

		if (groupCount != null) {
			timetableAppointment.setGroupCount(groupCount);
		} else {
			timetableAppointment.setGroupCount(-1);
		}
		if (consumablesCosts != null) {
			timetableAppointment.setConsumablesCosts(new BigDecimal(
					consumablesCosts));
		} else {
			timetableAppointment.setConsumablesCosts(new BigDecimal(-1));
		}
		// 设置调整排课的内容
		if (sWeek[0].indexOf(("-")) == -1) {
			timetableAppointment.setTotalWeeks("1");
			timetableAppointment.setStartWeek(Integer.parseInt(sWeek[0]));
			timetableAppointment.setEndWeek(Integer.parseInt(sWeek[0]));

		} else {
			timetableAppointment.setTotalWeeks(String.valueOf((Integer
					.parseInt(sWeek[0].split("-")[1]) - Integer
					.parseInt(sWeek[0].split("-")[0]))));
			timetableAppointment.setStartWeek(Integer.parseInt(sWeek[0]
					.split("-")[0]));
			timetableAppointment.setEndWeek(Integer.parseInt(sWeek[0]
					.split("-")[1]));
		}

		if (sClasses[0].indexOf(("-")) == -1) {
			timetableAppointment.setTotalClasses(Integer.parseInt(sClasses[0]));
			timetableAppointment.setStartClass(Integer.parseInt(sClasses[0]));
			timetableAppointment.setEndClass(Integer.parseInt(sClasses[0]));
		} else {
			timetableAppointment
					.setTotalClasses((Integer.parseInt(sClasses[0].split("-")[1]) - Integer
							.parseInt(sClasses[0].split("-")[0])));
			timetableAppointment.setStartClass(Integer.parseInt(sClasses[0]
					.split("-")[0]));
			timetableAppointment.setEndClass(Integer.parseInt(sClasses[0]
					.split("-")[1]));
		}
		TimetableAppointment timetableAppointmentNew = timetableAppointmentDAO
				.store(timetableAppointment);
		timetableAppointmentDAO.flush();
		TimetableLabRelated timetableLabRelated = new TimetableLabRelated();

		int countLabRoomDevice = 0; // 计算所选实验室总共有多少设备
		// 如果matchLabs不为空时
		if (labRooms != null && labRooms.length > 0) {
			for (String i1 : labRooms) {
				LabRoom labRoom = labRoomDAO.findLabRoomById(Integer
						.parseInt(i1));
				// 将matchLabs添加到matchLabs中
				timetableLabRelated.setLabRoom(labRoom);
				timetableLabRelated
						.setTimetableAppointment(timetableAppointmentNew);
				timetableLabRelatedDAO.store(timetableLabRelated);
				timetableLabRelatedDAO.flush();

				// 实验室设备数量累加
				if (labRoom.getLabRoomDevices() != null
						&& labRoom.getLabRoomDevices().size() > 0) {
					countLabRoomDevice += labRoom.getLabRoomDevices().size();
				}

			}
		}

		// 设置此次排课的针对对象（1 设备 2 实验室）

		timetableAppointmentNew.setDeviceOrLab(2);// 此次排课针对实验室

		timetableAppointmentNew = timetableAppointmentDAO
				.store(timetableAppointmentNew);

		/*
		 * 对排课预约选定的指导老师进行保存
		 */
		TimetableTeacherRelated timetableTeacherRelated = new TimetableTeacherRelated();
		// 获取选择的实验室列表
		List<User> matchTeachers = new ArrayList<User>();
		// 如果matchLabs不为空时
		if (teachers != null && teachers.length > 0) {
			for (int i1 = 0; i1 < teachers.length; i1++) {
				// 将matchLabs添加到matchLabs中
				matchTeachers.add(userDAO.findUserByUsername(teachers[i1]));
				timetableTeacherRelated.setUser(userDAO
						.findUserByUsername(teachers[i1]));
				timetableTeacherRelated
						.setTimetableAppointment(timetableAppointmentNew);
				timetableTeacherRelatedDAO.store(timetableTeacherRelated);
			}
		}

		/*
		 * 对排课预约选定的实验项目进行保存
		 */
		TimetableItemRelated timetableItemRelated = new TimetableItemRelated();
		// 获取选择的实验室列表
		List<OperationItem> matchItems = new ArrayList<OperationItem>();
		// 如果matchLabs不为空时
		if (items != null && items.length > 0) {
			for (int i1 = 0; i1 < items.length; i1++) {
				// 将matchLabs添加到matchLabs中
				matchItems.add(operationItemDAO.findOperationItemById(Integer
						.parseInt(items[i1])));
				timetableItemRelated.setOperationItem(operationItemDAO
						.findOperationItemById(Integer.parseInt(items[i1])));
				timetableItemRelated
						.setTimetableAppointment(timetableAppointmentNew);
				timetableItemRelatedDAO.store(timetableItemRelated);
			}
		}

		/**
		 * 如果一次排课，排课的周次或节次有不连续的，则保存主表记录
		 **/
		if (sWeek.length > 1 || sClasses.length > 1) {
			for (int i = 0; i < sWeek.length; i++) {
				for (int j = 0; j < sClasses.length; j++) {
					TimetableAppointmentSameNumber timetableAppointmentSameNumber = new TimetableAppointmentSameNumber();

					timetableAppointmentSameNumber.setCreatedBy(username);
					timetableAppointmentSameNumber.setCreatedBy(username);
					timetableAppointmentSameNumber.setCreatedDate(Calendar
							.getInstance());
					timetableAppointmentSameNumber.setUpdatedDate(Calendar
							.getInstance());

					// 设置调整排课的内容
					if (sWeek[i].indexOf(("-")) == -1) {
						timetableAppointmentSameNumber.setTotalWeeks("1");
						timetableAppointmentSameNumber.setStartWeek(Integer
								.parseInt(sWeek[i]));
						timetableAppointmentSameNumber.setEndWeek(Integer
								.parseInt(sWeek[i]));

					} else {
						timetableAppointmentSameNumber
								.setTotalWeeks(String.valueOf((Integer
										.parseInt(sWeek[i].split("-")[1]) - Integer
										.parseInt(sWeek[i].split("-")[0]))));
						timetableAppointmentSameNumber.setStartWeek(Integer
								.parseInt(sWeek[i].split("-")[0]));
						timetableAppointmentSameNumber.setEndWeek(Integer
								.parseInt(sWeek[i].split("-")[1]));
					}

					if (sClasses[j].indexOf(("-")) == -1) {
						timetableAppointmentSameNumber.setTotalClasses(Integer
								.parseInt(sClasses[j]));
						timetableAppointmentSameNumber.setStartClass(Integer
								.parseInt(sClasses[j]));
						timetableAppointmentSameNumber.setEndClass(Integer
								.parseInt(sClasses[j]));
					} else {
						timetableAppointmentSameNumber.setTotalClasses((Integer
								.parseInt(sClasses[j].split("-")[1]) - Integer
								.parseInt(sClasses[j].split("-")[0])));
						timetableAppointmentSameNumber.setStartClass(Integer
								.parseInt(sClasses[j].split("-")[0]));
						timetableAppointmentSameNumber.setEndClass(Integer
								.parseInt(sClasses[j].split("-")[1]));
					}
					timetableAppointmentSameNumber
							.setTimetableAppointment(timetableAppointmentNew);
					timetableAppointmentSameNumberDAO
							.store(timetableAppointmentSameNumber);
					timetableAppointmentSameNumberDAO.flush();

				}
			}
		}
		timetableLabRelatedDAO.flush();
		List<TimetableLabRelated> timetableLabRelateds = timetableLabRelatedDAO
				.executeQuery(
						"select c from TimetableLabRelated c where c.timetableAppointment.id="
								+ timetableAppointmentNew.getId(), 0, -1);
		if (timetableAppointmentNew.getDeviceOrLab().equals(1)) {
			for (TimetableLabRelated timetableLabRelatedTmp : timetableLabRelateds) {
				timetableCourseSchedulingService
						.saveTimetableLabroomDeviceReservation(
								timetableLabRelatedTmp, sLabRoomDevice,
								timetableSelfCourse.getSchoolTerm().getId());

			}
		} else {
			mysqlService.createLabLimitByAppointment(timetableAppointmentNew
					.getId());
		}
		return timetableAppointmentNew;
	}

	/****************************************************************************
	 * 功能：获取课程下视频列表 作者：张凯 时间：2017-05-03
	 ****************************************************************************/
	@Override
	public List<LearningVideoNew> getVideosList(String id) {
		// 获取视频
		String sql = "from WkUpload t where 1=1 and t.type = 0 and t.wkFolder.WkChapter.TCourseSite.id = "
				+ Integer.parseInt(id);
		List<WkUpload> videos = wkUploadDAO.executeQuery(sql);
		List<LearningVideoNew> list = new ArrayList<LearningVideoNew>();
		String chapterNameString = "";
		// 转换成所需格式
		for (WkUpload upload : videos) {
			if (upload.getWkFolder().getWkChapter().getType() == 1) {
				chapterNameString = "知识:"
						+ upload.getWkFolder().getWkChapter().getName();
			} else if (upload.getWkFolder().getWkChapter().getType() == 2) {
				chapterNameString = "技能:"
						+ upload.getWkFolder().getWkChapter().getName();
			} else if (upload.getWkFolder().getWkChapter().getType() == 3) {
				chapterNameString = "体验:"
						+ upload.getWkFolder().getWkChapter().getName();
			}
			LearningVideoNew video = new LearningVideoNew(upload.getId(),
					upload.getName(), upload.getWkFolder().getWkChapter()
							.getId(), chapterNameString);
			list.add(video);
		}
		return list;
	}

	/****************************************************************************
	 * 功能：获取课程下章节 作者：张凯 时间：2017-05-04
	 ****************************************************************************/
	@Override
	public List<LearningChapter> getChapters(String id) {
		// type为1的章节
		String sql2 = "from WkChapter w where w.TCourseSite.id = "
				+ Integer.parseInt(id) + " and w.type = 1 order by w.seq";
		List<WkChapter> chapters = wkChapterDAO.executeQuery(sql2);
		List<LearningChapter> list2 = new ArrayList<LearningChapter>();
		for (WkChapter c : chapters) {
			LearningChapter chapter = new LearningChapter(c.getId(), "知识:"
					+ c.getName());
			list2.add(chapter);
		}
		// type为2的章节
		sql2 = "from WkChapter w where w.TCourseSite.id = "
				+ Integer.parseInt(id) + " and w.type = 2 order by w.seq";
		chapters = wkChapterDAO.executeQuery(sql2);
		for (WkChapter c : chapters) {
			LearningChapter chapter = new LearningChapter(c.getId(), "技能:"
					+ c.getName());
			list2.add(chapter);
		}
		// type为3的章节
		sql2 = "from WkChapter w where w.TCourseSite.id = "
				+ Integer.parseInt(id) + " and w.type = 3 order by w.seq";
		chapters = wkChapterDAO.executeQuery(sql2);
		for (WkChapter c : chapters) {
			LearningChapter chapter = new LearningChapter(c.getId(), "体验:"
					+ c.getName());
			list2.add(chapter);
		}
		return list2;
	}


	/*********************************************************************************
	 * @description:获取试题列表
	 * @author:张凯 2017/05/20
	 ************************************************************************************/
	@Override
	public List<Question> getQuestionList(Integer id) {
		TAssignmentQuestionpool ta = tAssignmentQuestionpoolDAO
				.findTAssignmentQuestionpoolByPrimaryKey(id);
		String ids = "";
		for (TAssignmentItem it : ta.getTAssignmentItems()) {
			ids += it.getId() + ",";
		}
		if (!ids.equals("")) {
			ids = ids.substring(0, ids.length() - 1);
		}
		String sql = "select ta from TAssignmentItem ta where ta.id in (" + ids+ ")";
		// 获取题库下的所有试题
		List<TAssignmentItem> items = tAssignmentItemDAO.executeQuery(sql);
		// 获取试题信息
		List<Question> questions = new ArrayList<Question>();
		for (TAssignmentItem item : items) {
			List<String> text = new ArrayList<String>();
			List<Integer> answer = new ArrayList<Integer>();
			for (TAssignmentAnswer an : item.getTAssignmentAnswers()) {
				// 选项
				text.add(an.getText());
				// 答案显示
				answer.add(an.getIscorrect());

			}
			Question question = new Question(item.getId(),
					item.getDescription(), text, answer, item.getType());
			questions.add(question);
		}
		return questions;
	}
	

	/*********************************************************************************
	 * @description:保存新建讨论并将图片与讨论外键关联
	 * @author:张凯 时间：2017/06/29
	 ************************************************************************************/
	public AppPostlist savePost(String title, String sponsor, String content,
			String ids, String type, Integer groupID, Integer isStick) {
		AppPostlist post = new AppPostlist();
		User user = userDAO.findUserByPrimaryKey(sponsor);
		if (groupID == null) {
			post.setSchoolClasses(user.getSchoolClasses());
		} else {
			AppGroup group = appGroupDAO.findAppGroupById(groupID);
			post.setAppGroup(group);
		}
		post.setTitle(title);
		post.setTime(Calendar.getInstance());
		post.setContent(content);
		post.setUser(user);
		post.setIsstick(isStick);
		post.setState(0);
		post.setType(1);
		post = appPostlistDAO.store(post);
		if (ids != null && !ids.equals("")) {
			String[] imgsID = ids.split(",");
			if (imgsID.length > 0) {
				String hql = "select a from AppPostImages a where a.id= "
						+ imgsID[0];
				AppPostImages imglist1 = appPostImagesDAO
						.executeQueryByNameSingleResult(hql);

				post.setImagelist(imglist1.getImageurl());
			}
			for (String id : imgsID) {
				AppPostImages img = appPostImagesDAO
						.findAppPostImagesById(Integer.parseInt(id));
				img.setAppPostlist(post);
				appPostImagesDAO.store(img);
			}
		}
		if (post.getAppPostImageses().size() > 0) {
			for (AppPostImages img : post.getAppPostImageses()) {
				post.setImagelist(img.getImageurl());
				break;
			}
		}
		post = appPostlistDAO.store(post);
		appPostlistDAO.store(post);
		return post;
	}

	/*********************************************************************************
	 * @description:获取班级帖子列表
	 * @author:张凯 2017/06/29
	 ************************************************************************************/
	@Override
	public List<NewPostList> getPostlist(String username,
			HttpServletRequest request) {
		User user = userDAO.findUserByPrimaryKey(username);
		String sql = "select p from AppPostlist p where p.schoolClasses.classNumber like '"
				+ user.getSchoolClasses().getClassNumber() + "'and p.type=1";
		List<AppPostlist> posts = appPostlistDAO.executeQuery(sql, 0, -1);
		List<NewPostList> truePosts = new ArrayList<NewPostList>();
		// 获取当前服务器地址
		StringBuffer url = request.getRequestURL();
		String uri = request.getRequestURI();
		String servletUrl = url.substring(0, url.length() - uri.length())
				+ request.getContextPath();
		Integer state = 0;
		if (posts != null && posts.size() > 0) {
			for (AppPostlist p : posts) {
				String pudge = " select s from AppPostStatus s where s.appPostlist.id="
						+ p.getId()
						+ " and s.user.username like '"
						+ username
						+ "'and s.type=1";
				List<AppPostStatus> status = appPostStatusDAO
						.executeQuery(pudge);
				if (status != null && status.size() > 0) {
					state = status.get(0).getType();
				}
				NewPostList newPost = null;
				SimpleDateFormat sdf = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				String date = sdf.format(p.getTime().getTime());
				if (p.getIsstick() == 0) {
					newPost = new NewPostList(p.getId(), p.getTitle(), state,
							p.getIsstick(), p.getUser().getUsername(), date,
							servletUrl + "/" + p.getImagelist());
				} else {
					newPost = new NewPostList(p.getId(), p.getTitle(), state,
							p.getIsstick(), p.getUser().getUsername(), date,
							null);
				}
				truePosts.add(newPost);
			}
		}
		return truePosts;
	}

	/*********************************************************************************
	 * @description: 上传图片
	 * @author:张凯
	 * @date：2017/06/30
	 ************************************************************************************/
	public String uploadImageForLabRoom(HttpServletRequest request,
			HttpServletResponse response) {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		String sep = System.getProperty("file.separator");
		Map files = multipartRequest.getFileMap();
		Iterator fileNames = multipartRequest.getFileNames();
		boolean flag = false;
		String fileDir = multipartRequest.getSession().getServletContext()
				.getRealPath("/")
				+ "upload" + sep + "discuss";
		// 保存上传图片的id
		String ids = "";
		// 存放文件文件夹名称
		for (; fileNames.hasNext();) {
			String filename = (String) fileNames.next();
			CommonsMultipartFile file = (CommonsMultipartFile) files
					.get(filename);
			byte[] bytes = file.getBytes();
			if (bytes.length != 0) {
				// 说明申请有附件
				if (!flag) {
					File dirPath = new File(fileDir);
					if (!dirPath.exists()) {
						flag = dirPath.mkdirs();
					}
				}
				String fileTrueName = file.getOriginalFilename();
				// 文件重命名
				int endAddress = fileTrueName.lastIndexOf(".");
				String ss = fileTrueName.substring(endAddress,
						fileTrueName.length());// 后缀名
				String fileNewName = UUID.randomUUID().toString() + ss;
				File uploadedFile = new File(fileDir + sep + fileNewName);
				try {
					FileCopyUtils.copy(bytes, uploadedFile);
				} catch (IOException e) {
					e.printStackTrace();
				}
				String path = "/discuss";
				AppPostImages p = this.saveImage(path, fileNewName, -1);
				ids += p.getId();
			} else {
				ids += "failed";
			}
		}
		return ids;
	}

	/*********************************************************************************
	 * @description: 图片与文档的保存
	 * @author:张凯
	 * @date：2017/06/30
	 ************************************************************************************/
	@Override
	public AppPostImages saveImage(String path, String fileNewName, Integer id) {
		AppPostImages img = new AppPostImages();
		AppPostlist appPostList = appPostlistDAO.findAppPostlistById(id);
		img.setAppPostlist(appPostList);
		String imageUrl = "upload" + path + "/" + fileNewName;
		img.setImageurl(imageUrl);
		img.setUploadTime(Calendar.getInstance());
		img = appPostImagesDAO.store(img);
		appPostImagesDAO.flush();
		return img;
	}

	/*********************************************************************************
	 * @description: 图片的获取
	 * @author:张凯
	 * @date：2017/07/03
	 ************************************************************************************/
	public List<AppPostImages> getImageList(Integer id) {
		String sql = " select img from AppPostImages img where img.appPostlist.id = "
				+ id;
		sql += "order by img.uploadTime";
		List<AppPostImages> images = appPostImagesDAO.executeQuery(sql, 0, -1);
		return images;
	}

	/*********************************************************************************
	 * @description: 查看帖子
	 * @author:张凯
	 * @date：2017/07/03
	 ************************************************************************************/
	public PostDetail viewPostDetails(String username, Integer ID,
			HttpServletRequest request) {
		AppPostlist p = appPostlistDAO.findAppPostlistById(ID);
		// 判断用户是否已经收藏帖子、喜欢帖子
		String isLike = "select count(*) from app_userlikepost where id=" + ID
				+ " and username like '" + username + "'";
		String isCollect = "select count(*) from app_usercollectpost where id="
				+ ID + " and username like '" + username + "'";
		int count1 = ((BigInteger) entityManager.createNativeQuery(isLike)
				.getSingleResult()).intValue();
		int count2 = ((BigInteger) entityManager.createNativeQuery(isCollect)
				.getSingleResult()).intValue();
		int like = count1 > 0 ? 1 : 0;
		int collect = count2 > 0 ? 1 : 0;
		// 获取帖子下的所有图片路径
		List<String> imglist = new ArrayList<String>();
		String url = request.getRequestURL().toString();
		String servletUrl = url.substring(0, url.length()
				- request.getRequestURI().length())
				+ request.getContextPath();
		// for(AppPostImages img:p.getAppPostImageses()){
		// imglist.add(servletUrl+"/"+img.getImageurl());
		// }

		// author:赵昶 date：2017/07/20 图片添加不完全，添加所有图片
		// sql:查找改帖子的照片
		String sql = "select p from AppPostImages p where p.appPostlist.id="
				+ ID;
		List<AppPostImages> posts = appPostImagesDAO.executeQuery(sql, 0, -1);
		List<String> truePosts = new ArrayList<String>();
		// 添加第一章首页图片
		imglist.add(servletUrl + "/" + p.getImagelist());
		// 获取符合要求的所有图片
		for (AppPostImages post : posts) {
			truePosts.add(post.getImageurl());
		}

		for (String img : truePosts) {
			imglist.add(servletUrl + "/" + img);
		}
		// 结束：author:赵昶 date：2017/07/20 图片添加不完全，添加所有图片

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = sdf.format(p.getTime().getTime());
		// 返回帖子数据与用户是否收藏、点赞
		PostDetail detail = new PostDetail(p.getTitle(), p.getContent(), p
				.getUser().getUsername(), date, imglist, like, collect);
		// 保存或更新用户查看帖子的记录
		AppPostStatus status = null;
		if (p.getAppPostStatuses() != null || p.getAppPostStatuses().size() > 0) {
			for (AppPostStatus s : p.getAppPostStatuses()) {
				status = s;
				status.setType(2);
				appPostStatusDAO.store(status);
				appPostStatusDAO.flush();
				break;
			}
		} else {
			status = new AppPostStatus();
			// 帖子状态变为已读
			status.setType(2);
			status.setUser(userDAO.findUserByPrimaryKey(username));
			status.setAppPostlist(p);
			appPostStatusDAO.store(status);
			appPostStatusDAO.flush();
		}
		return detail;
	}

	/*********************************************************************************
	 * @description: 保存帖子回复内容
	 * @author:张凯
	 * @date：2017/07/03
	 ************************************************************************************/
	public void savePostReply(Integer ID, String Sponsor, String comment,
			Integer toResponseID) {
		AppPostReply reply = new AppPostReply();
		AppPostlist post = appPostlistDAO.findAppPostlistById(ID);
		reply.setAppPostlist(post);
		reply.setTime(Calendar.getInstance());
		reply.setUser(userDAO.findUserByPrimaryKey(Sponsor));
		reply.setComment(comment);
		if (toResponseID != null)
			reply.setAppPostReply(appPostReplyDAO
					.findAppPostReplyById(toResponseID));
		appPostReplyDAO.store(reply);
		if (post.getAppPostStatuses() != null
				&& post.getAppPostStatuses().size() > 0) {
			for (AppPostStatus s : post.getAppPostStatuses()) {
				s.setType(3);
				appPostStatusDAO.store(s);
				appPostStatusDAO.flush();
			}
		}
	}

	/*********************************************************************************
	 * @description: 帖子点赞
	 * @author:张凯
	 * @date：2017/07/04
	 ************************************************************************************/
	public Integer saveUpvoteUserForPost(Integer ID, String sponsor,
			Integer flag) {
		Integer sign = 0;
		String hql = "select count(*) from app_userlikepost where id=" + ID
				+ " and username='" + sponsor + "'";
		int count = ((BigInteger) entityManager.createNativeQuery(hql)
				.getSingleResult()).intValue();// 查询是否已经点赞，count=0表示未点赞
		if (flag == 0 && count > 0) {// 0表示取消点赞
			String sql = "DELETE FROM app_userlikepost WHERE id= " + ID
					+ " AND username= '" + sponsor + "'";
			entityManager.createNativeQuery(sql).executeUpdate();
			sign = 1;
		} else if (flag == 1 && count == 0) {// 1表示点赞
			String sql = " INSERT INTO app_userlikepost VALUES(" + ID + ",'"
					+ sponsor + "')";
			entityManager.createNativeQuery(sql).executeUpdate();
			sign = 1;
		}
		return sign;
	}

	/*********************************************************************************
	 * @description: 帖子收藏,flag=0时取消收藏,为1时收藏
	 * @author:张凯
	 * @date：2017/07/05
	 ************************************************************************************/
	@Override
	public Integer saveCollectUserForPost(Integer ID, String sponsor,
			Integer flag) {
		Integer sign = 0;
		String pudge = "select count(*) from app_usercollectpost where id="
				+ ID + " and username='" + sponsor + "'";
		int count = ((BigInteger) entityManager.createNativeQuery(pudge)
				.getSingleResult()).intValue();
		if (count > 0 && flag == 0) {// 取消收藏
			String sql = " delete from app_usercollectpost where id=" + ID
					+ " and username='" + sponsor + "'";
			entityManager.createNativeQuery(sql).executeUpdate();
			sign = 1;
		} else if (count == 0 && flag == 1) {// 收藏帖子
			String sql = " INSERT INTO app_usercollectpost VALUES(" + ID + ",'"
					+ sponsor + "')";
			entityManager.createNativeQuery(sql).executeUpdate();
			sign = 1;
		}
		return sign;
	}

	/*********************************************************************************
	 * @description: 获取公告列表
	 * @author:张凯
	 * @date：2017/07/04
	 ************************************************************************************/
	public List<AnnoucementList> getAnnouncements(String username) {
		User user = userDAO.findUserByPrimaryKey(username);
		String sql = "SELECT p FROM AppPostlist AS p WHERE p.type = 2 and p.schoolClasses.classNumber like '"
				+ user.getSchoolClasses().getClassNumber() + "'";
		List<AppPostlist> posts = appPostlistDAO.executeQuery(sql);
		List<AnnoucementList> annlist = new ArrayList<AnnoucementList>();
		if (posts != null && posts.size() > 0) {
			for (AppPostlist p : posts) {
				SimpleDateFormat sdf = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				String date = sdf.format(p.getTime().getTime());
				AnnoucementList a = new AnnoucementList(p.getId(),
						p.getTitle(), p.getContent(), date, p.getUser()
								.getCname());
				annlist.add(a);
			}
		}
		return annlist;
	}

	/*********************************************************************************
	 * @description: 获取公告详情
	 * @author:张凯
	 * @date：2017/07/04
	 ************************************************************************************/
	public Annoucement getAnnoucementDetails(Integer ID) {
		AppPostlist post = appPostlistDAO.findAppPostlistById(ID);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = sdf.format(post.getTime().getTime());
		Annoucement ann = new Annoucement(post.getTitle(), post.getContent(),
				date, post.getUser().getCname());
		return ann;
	}

	/*********************************************************************************
	 * @description: 保存新建公告
	 * @author:张凯
	 * @return
	 * @date：2017/07/04
	 ************************************************************************************/
	public AnnoucementList saveAnnoucement(String sponsor, String comment,
			String title) {
		AppPostlist ann = new AppPostlist();
		ann.setUser(userDAO.findUserByPrimaryKey(sponsor));
		ann.setType(2);// 公告类型为2
		ann.setContent(comment);
		ann.setTime(Calendar.getInstance());
		ann.setTitle(title);
		ann.setSchoolClasses(userDAO.findUserByPrimaryKey(sponsor)
				.getSchoolClasses());
		appPostlistDAO.store(ann);
		String sql = "select p from AppPostlist p where p.title='" + title
				+ "' order by p.id desc";
		List<AppPostlist> list = appPostlistDAO.executeQuery(sql, 0, -1);
		System.out.print(list.size());
		Set<AppPostlist> set = appPostlistDAO.findAppPostlistByTitle(title);
		JSONObject jsonObject = new JSONObject();
		AnnoucementList anoun = new AnnoucementList();
		if (list != null && list.size() > 0) {
			for (AppPostlist p : list) {
				SimpleDateFormat sdf = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				String date = sdf.format(p.getTime().getTime());
				anoun = new AnnoucementList(p.getId(), p.getTitle(),
						p.getContent(), date, p.getUser().getCname());
			}
		}
		return anoun;
	}

	/*********************************************************************************
	 * @description: 新建分享
	 * @author:张凯
	 * @date：2017/07/07
	 ************************************************************************************/
	public int NewShare(Integer ID, String username, String title,String postList) {
		int flag = 0;
		AppPostlist share = new AppPostlist();
		User user = userDAO.findUserByPrimaryKey(username);
		share.setUser(user);
		share.setTitle(title);
		share.setTime(Calendar.getInstance());
		share.setType(3);// 公告类型为3
		share.setSchoolClasses(user.getSchoolClasses());
		share = appPostlistDAO.store(share);
		if (postList!= null && !postList.equals("")){
			String[] lisID = postList.split(",");
			if (lisID != null){
                AppPostImages list = appPostImagesDAO
                        .findAppPostImagesById(Integer.parseInt(lisID[0]));
                share.setImagelist(list.getImageurl());
            }
		for (String id : lisID) {
			AppPostImages list = appPostImagesDAO
						.findAppPostImagesById(Integer.parseInt(id));
				list.setAppPostlist(share);
				appPostImagesDAO.store(list);
			}
			
		}
		flag = 1;
		return flag;
	}
	/*********************************************************************************
	 * @description: 上传分享
	 * @author:唐钦邦
	 * @date：2017/06/30
	 ************************************************************************************/
	public String uploadShareForLabRoom(HttpServletRequest request,
			HttpServletResponse response) {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		String sep = System.getProperty("file.separator");
		Map files = multipartRequest.getFileMap();
		Iterator fileNames = multipartRequest.getFileNames();
		boolean flag = false;
		String fileDir = multipartRequest.getSession().getServletContext()
				.getRealPath("/")
				+ "upload" + sep + "document";
		// 保存上传图片的id
		String ids = "";
		// 存放文件文件夹名称
		for (; fileNames.hasNext();) {
			String filename = (String) fileNames.next();
			CommonsMultipartFile file = (CommonsMultipartFile) files
					.get(filename);
			byte[] bytes = file.getBytes();
			if (bytes.length != 0) {
				// 说明申请有附件
				if (!flag) {
					File dirPath = new File(fileDir);
					if (!dirPath.exists()) {
						flag = dirPath.mkdirs();
					}
				}
				String fileTrueName = file.getOriginalFilename();
				// 文件重命名
				int endAddress = fileTrueName.lastIndexOf(".");
				String ss = fileTrueName.substring(endAddress,
						fileTrueName.length());// 后缀名
				String fileNewName = UUID.randomUUID().toString() + ss;
				File uploadedFile = new File(fileDir + sep + fileNewName);
				try {
					FileCopyUtils.copy(bytes, uploadedFile);
				} catch (IOException e) {
					e.printStackTrace();
				}
				String path = "/document";
				AppPostImages p = this.saveImage(path, fileNewName, -1);
				ids += p.getId();
			} else {
				ids += "failed";
			}
		}
		return ids;
	}

	
	/*********************************************************************************
     * @description: 保存分享
     * @author:张凯
     * @date：2017/07/07
     ************************************************************************************/
    public AnnoucementList saveNewShare(Integer ID, String username, String title) {
        AppPostlist share = new AppPostlist();
        User user = userDAO.findUserByPrimaryKey(username);
        share.setUser(user);
        share.setTitle(title);
        share.setTime(Calendar.getInstance());
        share.setType(3);// 公告类型为3
        share.setSchoolClasses(user.getSchoolClasses());
        appPostlistDAO.store(share);
        String sql = "select p from AppPostlist p where p.title='" + title  + "' order by p.id desc";
        List<AppPostlist> lists = appPostlistDAO.executeQuery(sql,0,-1);
        System.out.print(lists.size());
        Set<AppPostlist> set = appPostlistDAO.findAppPostlistByTitle(title);
        JSONObject jsonObject = new JSONObject();
        AnnoucementList shar = new AnnoucementList();
        if (lists != null && lists.size() > 0) {
            for (AppPostlist p : lists) {
                SimpleDateFormat sdf = new SimpleDateFormat(
                        "yyyy-MM-dd HH:mm:ss");
                String date = sdf.format(p.getTime().getTime());
                shar = new AnnoucementList(p.getId(),p.getTitle(),
                        p.getContent(),date,p.getUser().getUsername());
            }
        }
        return shar;
    }

/*	*//*********************************************************************************
	 * @description: 上传文档
	 * @author:张凯
	 * @date：2017/07/07
	 ************************************************************************************//*
	public AppPostImages uploadDocument(HttpServletRequest request,
			HttpServletResponse response) {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		String sep = System.getProperty("file.separator");
		Map files = multipartRequest.getFileMap();
		Iterator fileNames = multipartRequest.getFileNames();
		boolean flag = false;
		String fileDir = request.getSession().getServletContext()
				.getRealPath("/")
				+ "upload" + sep + "document";
		AppPostImages img = null;
		// 存放文件文件夹名称
		for (; fileNames.hasNext();) {
			String filename = (String) fileNames.next();
			CommonsMultipartFile file = (CommonsMultipartFile) files
					.get(filename);
			byte[] bytes = file.getBytes();
			if (bytes.length != 0) {
				// 说明申请有附件
				if (!flag) {
					File dirPath = new File(fileDir);
					if (!dirPath.exists()) {
						flag = dirPath.mkdirs();
					}
				}
				String fileTrueName = file.getOriginalFilename();
				// 文件重命名
				int endAddress = fileTrueName.lastIndexOf(".");
				String ss = fileTrueName.substring(endAddress,
						fileTrueName.length());// 后缀名
				String fileNewName = "roomImageType" + ss;
				File uploadedFile = new File(fileDir + sep + fileTrueName);
				try {
					FileCopyUtils.copy(bytes, uploadedFile);
				} catch (IOException e) {
					e.printStackTrace();
				}
				String path = "/document";
				img = this.saveImage(path, fileTrueName, -1);
			} else {
			}
		}
		return img;
	}
*/
	/*********************************************************************************
	 * @description: 分享列表
	 * @author:张凯
	 * @date：2017/07/07
	 ************************************************************************************/
	public List<ShareList> getShareList(String username,
			HttpServletRequest request) {
		User user = userDAO.findUserByPrimaryKey(username);
		String sql = "SELECT p FROM AppPostlist AS p WHERE p.type = 3 and p.schoolClasses.classNumber like '"
				+ user.getSchoolClasses().getClassNumber() + "'";
		List<AppPostlist> posts = appPostlistDAO.executeQuery(sql);
		List<ShareList> sharelist = new ArrayList<ShareList>();
		List<String> enclosure = new ArrayList<String>();

		String servUrl = request.getRequestURL().toString();
		servUrl = servUrl.substring(0, servUrl.length()
				- request.getRequestURI().length())
				+ request.getContextPath();

		if (posts != null && posts.size() > 0) {
			for (AppPostlist p : posts) {
				SimpleDateFormat sdf = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				String date = sdf.format(p.getTime().getTime());

				String sql2 = "select p from AppPostImages p where p.appPostlist.id="
						+ p.getId();
				List<AppPostImages> posts2 = appPostImagesDAO.executeQuery(
						sql2, 0, -1);
				List<String> truePosts = new ArrayList<String>();
				// 获取符合要求的所有图片
				for (AppPostImages p2 : posts2) {
					truePosts.add(p2.getImageurl());
				}

				for (String enc : truePosts) {
					enclosure.add(servUrl + "/" + enc);
				}

				ShareList a = new ShareList(p.getId(), p.getTitle(), date, p
						.getUser().getCname(), enclosure);
				sharelist.add(a);
			}
		}
		return sharelist;
	}

	/*********************************************************************************
	 * @description: 分享详情
	 * @author:张凯
	 * @date：2017/07/07
	 ************************************************************************************/
	public ShareDetail shareDocument(Integer ID, HttpServletRequest request) {
		AppPostlist share = appPostlistDAO.findAppPostlistById(ID);
		Set<AppPostImages> docs = share.getAppPostImageses();
		String docName = "";
		AppPostImages doc = new AppPostImages();
		String sql = "select p from AppPostImages p where p.appPostlist.id="
				+ ID;
		List<AppPostImages> posts = appPostImagesDAO.executeQuery(sql, 0, -1);
		List<String> truePosts = new ArrayList<String>();
		String url = request.getRequestURL().toString();
		String servletUrl = url.substring(0, url.length()
				- request.getRequestURI().length())
				+ request.getContextPath();
		List<String> imglist = new ArrayList<String>();

		// 添加第一章首页图片
		imglist.add(servletUrl + "/" + share.getImagelist());
		// 获取符合要求的所有图片
		for (AppPostImages post : posts) {
			truePosts.add(post.getImageurl());
		}

		for (String img : truePosts) {
			// String name=servletUrl+"/"+img;
			// String tureName=name.substring(name.lastIndexOf("/") + 1)+name;
			// imglist.add(tureName);
			imglist.add(servletUrl + "/" + img);

			// imglist.add(servletUrl+"/"+img.substring(img.lastIndexOf("/") +
			// 1)+name);
		}
		List<String> docnameList = new ArrayList<String>();
		for (String i : imglist) {
			String tureName = i.substring(i.lastIndexOf("/") + 1);
			docnameList.add(tureName);
		}
		System.out.print(docnameList.size());
		if (docs != null && docs.size() > 0) {
			for (AppPostImages document : docs) {
				doc = document;
			}
		}
		int num = doc.getImageurl().lastIndexOf("/");
		docName += doc.getImageurl().substring(num, doc.getImageurl().length());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = sdf.format(share.getTime().getTime());
		ShareDetail s = new ShareDetail(doc.getId(), share.getTitle(), share
				.getUser().getCname(), date, docnameList, imglist);
		return s;
	}

	/*********************************************************************************
	 * @description: 下载分享附件
	 * @author:张凯
	 * @date：2017/07/07
	 ************************************************************************************/
	public String downloadDocument(Integer ID, HttpServletRequest request) {
		AppPostImages doc = appPostImagesDAO.findAppPostImagesById(ID);
		String servUrl = request.getRequestURL().toString();
		servUrl = servUrl.substring(0, servUrl.length()
				- request.getRequestURI().length())
				+ request.getContextPath();
		String url = servUrl + "/" + doc.getImageurl();
		return url;
	}

	/****************************************************************************
	 * 功能：查询学生的小组 作者：缪军 时间：2017-06-30
	 ****************************************************************************/
	public String getStudentGroup(String username) {
		String hql = " select g.id , g.name from app_group g "
				+ " inner join app_group_user gu on g.id = gu.id "
				+ " INNER JOIN user u on gu.username = u.username "
				+ " where u.username like '" + username + "'";
		return hql;
	}

	/****************************************************************************
	 * 功能：查询全部小组，和是否关注 作者：缪军 时间：2017-06-30
	 ****************************************************************************/
	public String getGroupList(String username) {
		String hql = " select g.id , g.name , "
				+ " (select '"
				+ username
				+ "' in (select gu.username from app_group_user gu where gu.id=g.id))falg "
				+ " from app_group g ";
		return hql;
	}
	
	
	
	

	/****************************************************************************
	 * 功能：新建小组 作者：缪军 时间：2017-07-03
	 ****************************************************************************/
	public int newGroup(String username, String groupname) {
		int flag = 0;
		Set<AppGroup> set = appGroupDAO.findAppGroupByName(groupname);
		if (set != null && !set.isEmpty()) {
			flag = 2;
		} else {
			AppGroup newGroup = new AppGroup();
			newGroup.setName(groupname);
			newGroup = appGroupDAO.store(newGroup);
			appGroupDAO.flush();
			int follow = followStudentGroup(username, newGroup.getId(), 1);
			flag = follow;
		}
		return flag;// return 1表示成功。0表示失败，2表示已经有这个小组
	}

	/****************************************************************************
	 * 功能：查找小组 作者：缪军 时间：2017-07-19
	 ****************************************************************************/
	public List<AppGroup> findGroup(String username, String groupname) {
		String sql = "select l from AppGroup l where l.name='" + groupname
				+ "' order by l.id desc";
		List<AppGroup> list = appGroupDAO.executeQuery(sql, 0, -1);
		return list;
	}

	/****************************************************************************
	 * 功能：关注小组 作者：缪军 时间：2017-07-03
	 ****************************************************************************/
	public int followStudentGroup(String username, Integer groupId,
			Integer param) {// param 1表示关注。0表示取关
		int flag = 0;
		String sql = "select count(*) from app_group_user where id=" + groupId
				+ " and username=" + username;
		int count = ((BigInteger) entityManager.createNativeQuery(sql)
				.getSingleResult()).intValue();// 查询是否已经关注，count=0表示未关注
		if (param == 1 && count == 0) {// 关注小组
			String follow = "INSERT INTO app_group_user VALUES (" + groupId
					+ " ,'" + username + "')";
			entityManager.createNativeQuery(follow).executeUpdate();
			flag = 1;
		} else if (param == 0 && count > 0) {// 取关小组
			String unfollow = "delete from app_group_user where id=" + groupId
					+ " and username=" + username;
			entityManager.createNativeQuery(unfollow).executeUpdate();
			flag = 1;
		}
		return flag;// return 1表示成功。0表示失败
	}

	/*********************************************************************************
	 * @description: 查看相册
	 * @author:缪军
	 * @date：2017/07/04
	 ************************************************************************************/
	public Album viewAlbum(Integer ID, HttpServletRequest request) {
		// 获取服务器地址和项目名称
		String servUrl = request.getRequestURL().toString();
		servUrl = servUrl.substring(0, servUrl.length()
				- request.getRequestURI().length())
				+ request.getContextPath();
		// 相册采用app_postlist表，type为4
		String sql = "SELECT p FROM AppPostlist p WHERE p.id =" + ID
				+ " and p.type=4";
		Album album = new Album();
		List<AppPostlist> appPost = appPostlistDAO.executeQuery(sql);
		if (appPost != null && appPost.size() != 0) {
			album.setID(appPost.get(0).getId());
			album.setSponsor(appPost.get(0).getUser().getUsername());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String dateStr = sdf.format(appPost.get(0).getTime().getTime());
			album.setTime(dateStr);
			album.setTitle(appPost.get(0).getTitle());

		}
		List<String> imgList = new ArrayList<String>();
		String imgSql = "select i from AppPostImages i where i.appPostlist.id ="
				+ ID;
		List<AppPostImages> appImages = appPostImagesDAO.executeQuery(imgSql);
		if (appImages != null && appImages.size() != 0) {
			for (AppPostImages postImage : appImages) {
				imgList.add(servUrl + "/" + postImage.getImageurl());
			}
		}
		album.setImageList(imgList);
		album.setSum(imgList.size());

		return album;
	}
/*	*//*********************************************************************************
	 * @description: 保存图片
	 * @author:唐钦邦
	 * @date：2017/08/16
	 ************************************************************************************//*
	public String saveImg(HttpServletRequest request,
			HttpServletResponse response) {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		String sep = System.getProperty("file.separator");
		Map files = multipartRequest.getFileMap();
		Iterator fileNames = multipartRequest.getFileNames();
		boolean flag = false;
		String fileDir = multipartRequest.getSession().getServletContext()
				.getRealPath("/")
				+ "upload" + sep + "album";
		// 保存上传图片的id
		String ids = "";
		// 存放文件文件夹名称
		for (; fileNames.hasNext();) {
			String filename = (String) fileNames.next();
			CommonsMultipartFile file = (CommonsMultipartFile) files
					.get(filename);
			byte[] bytes = file.getBytes();
			if (bytes.length != 0) {
				// 说明申请有附件
				if (!flag) {
					File dirPath = new File(fileDir);
					if (!dirPath.exists()) {
						flag = dirPath.mkdirs();
					}
				}
				String fileTrueName = file.getOriginalFilename();
				// 文件重命名
				int endAddress = fileTrueName.lastIndexOf(".");
				String ss = fileTrueName.substring(endAddress,
						fileTrueName.length());// 后缀名
				String fileNewName = UUID.randomUUID().toString() + ss;
				File uploadedFile = new File(fileDir + sep + fileNewName);
				try {
					FileCopyUtils.copy(bytes, uploadedFile);
				} catch (IOException e) {
					e.printStackTrace();
				}
				String path = "/album";
				AppPostImages p = this.saveImage(path, fileNewName, -1);
				ids += p.getId();
			} else {
				ids += "failed";
			}
		}
		return ids;
	}
*/
	/*********************************************************************************
	 * @description:保存新建相册并将图片与postlist外键关联
	 * @author:缪军 时间：2017/07/04
	 * 改
	 ************************************************************************************/
	@Override
	public int newAlbum(String title, String sponsor, String imageList) {
		int flag = 0;
		AppPostlist album = new AppPostlist();
		User user = userDAO.findUserByPrimaryKey(sponsor);
		album.setTitle(title);
		album.setTime(Calendar.getInstance());
		album.setUser(user);
		album.setType(4);
		// 班级后添加
		album.setSchoolClasses(user.getSchoolClasses());
		album = appPostlistDAO.store(album);
		if (imageList != null && !imageList.equals("")) {
			String[] imgsID = imageList.split(",");
			if (imgsID != null){
				AppPostImages img = appPostImagesDAO
						.findAppPostImagesById(Integer.parseInt(imgsID[0]));
				album.setImagelist(img.getImageurl());
			}
			for (String id : imgsID) {
				AppPostImages img = appPostImagesDAO
						.findAppPostImagesById(Integer.parseInt(id));
				img.setAppPostlist(album);
				appPostImagesDAO.store(img);
			}
		}
		flag = 1;
		return flag;
	}
	

	/****************************************************************************
	 * 功能：下载相册(打包zip部分) 作者：缪军 时间：2017-07-05
	 ****************************************************************************/
	public void downloadAlbum(Integer id, HttpServletRequest request,
			HttpServletResponse response) {
		String sep = System.getProperty("file.separator");
		// 根路径
		String rootPath = request.getSession().getServletContext()
				.getRealPath("/");
		// 获取要下载的文件
		List<AppPostImages> postImages = getImageList(id);

		if (postImages == null || postImages.size() == 0) {
			// do nothing
		} else {
			File[] files = new File[postImages.size()];
			for (int i = 0; i < files.length; i++) {
				files[i] = new File(rootPath + postImages.get(i).getImageurl());
			}
			String tmpFileName = rootPath + "upload" + sep + "discuss" + sep
					+ id + appPostlistDAO.findAppPostlistById(id).getTitle()
					+ ".zip";
			byte[] buffer = new byte[1024];
			try {
				ZipOutputStream out = new ZipOutputStream(new FileOutputStream(
						tmpFileName));
				for (File file : files) {
					FileInputStream fis = new FileInputStream(file);
					out.putNextEntry(new ZipEntry(file.getName()));
					// 设置压缩文件内的字符编码，不然会变成乱码
					out.setEncoding("GBK");
					int len;
					// 读入需要下载的文件的内容，打包到zip文件
					while ((len = fis.read(buffer)) > 0) {
						out.write(buffer, 0, len);
					}
					out.closeEntry();
					fis.close();
				}
				out.close();
				downloadFile(request, response, tmpFileName);
				File file = new File(tmpFileName);
				file.delete();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/****************************************************************************
	 * 功能：根据路径下载文件 作者：缪军 时间：2017-07-05
	 ****************************************************************************/
	public void downloadFile(HttpServletRequest request,
			HttpServletResponse response, String filePath) {
		OutputStream fos = null;
		try {
			FileInputStream fis = new FileInputStream(filePath);
			response.setCharacterEncoding("utf-8");
			// 解决上传中文文件时不能下载的问题
			response.setContentType("multipart/form-data;charset=UTF-8");
			String fileName = filePath
					.substring(filePath.lastIndexOf("\\") + 1);
			if (request.getHeader("User-Agent").toLowerCase()
					.indexOf("firefox") > 0) {
				fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");// firefox浏览器
			} else if (request.getHeader("User-Agent").toUpperCase()
					.indexOf("MSIE") > 0) {
				fileName = URLEncoder.encode(fileName, "UTF-8");// IE浏览器
			} else {
				fileName = URLEncoder.encode(fileName, "UTF-8");
			}

			response.setHeader("Content-Disposition", "attachment;fileName="
					+ fileName.replaceAll("\\+", "%20")
							.replaceAll("%28", "\\(").replaceAll("%29", "\\)")
							.replaceAll("%3B", ";").replaceAll("%40", "@")
							.replaceAll("%23", "\\#").replaceAll("%26", "\\&"));

			fos = response.getOutputStream();
			byte[] buffer = new byte[8192];
			int count = 0;
			while ((count = fis.read(buffer)) > 0) {
				fos.write(buffer, 0, count);
			}
			fis.close();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/*********************************************************************************
	 * 功能：获取小组帖子列表 作者：缪军 时间：2017-07-06
	 ************************************************************************************/
	public List<NewPostList> getGroupPostlist(String username, Integer groupId,
			HttpServletRequest request) {
		String sql = "select p from AppPostlist p where p.appGroup.id="
				+ groupId + " order by time desc";
		List<AppPostlist> posts = appPostlistDAO.executeQuery(sql, 0, -1);
		List<NewPostList> truePosts = new ArrayList<NewPostList>();
		Integer state = 0;
		// 获取服务端地址以项目名称
		String servUrl = request.getRequestURL().substring(
				0,
				request.getRequestURL().length()
						- request.getRequestURI().length())
				+ request.getContextPath();
		if (posts != null && posts.size() > 0) {
			for (AppPostlist p : posts) {
				String pudge = " select s from AppPostStatus s where s.appPostlist.id="
						+ p.getId()
						+ " and s.user.username like '"
						+ username
						+ "'";
				List<AppPostStatus> appPostStatus = appPostStatusDAO
						.executeQuery(pudge);
				if (appPostStatus != null && appPostStatus.size() > 0) {
					state = appPostStatus.get(0).getType();
				}
				SimpleDateFormat sdf = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				String dateStr = sdf.format(p.getTime().getTime());
				NewPostList newPost = new NewPostList(p.getId(), p.getTitle(),
						state, p.getIsstick(), p.getUser().getUsername(),
						dateStr, servUrl

						+ "/" + p.getImagelist());
				truePosts.add(newPost);
			}
		}
		return truePosts;
	}

	/****************************************************************************
	 * 功能：查询已经关注的小组 作者：缪军 时间：2017-07-07
	 ****************************************************************************/
	public List<AppGroup> getFollowedGroupList(String username) {
		String hql = "select a from AppGroup a inner join fetch a.users s where s.username="
				+ username;
		List<AppGroup> appgroup = appGroupDAO.executeQuery(hql, 0, -1);
		// 解决json格式转换时，由hibernate级联导致的引用循环问题
		List<AppGroup> result = new ArrayList<AppGroup>();
		if (appgroup != null && appgroup.size() != 0) {
			for (AppGroup g : appgroup) {
				AppGroup a = new AppGroup();
				a.setId(g.getId());
				a.setName(g.getName());
				result.add(a);
			}
		}
		return result;
	}

	/****************************************************************************
	 * 功能：分组分别查询帖子列表 作者：缪军 时间：2017-07-07
	 ****************************************************************************/
	public List<NewPostList> getGroupListByGroup(String username,
			List<AppGroup> groups, HttpServletRequest request) {
		List<NewPostList> list = new ArrayList<NewPostList>();
		for (AppGroup appGroup : groups) {
			List<NewPostList> truePosts = getGroupPostlist(username,
					appGroup.getId(), request);
			if (truePosts != null && truePosts.size() > 0) {
				list.add(truePosts.get(0));
			}
		}
		return list;
	}

	/*********************************************************************************
	 * @description:获取班级帖子列表（随机）
	 * @author:缪军 2017/07/10
	 ************************************************************************************/
	@Override
	public List<NewPostList> getPostlistRandom(Integer limit, String username,
			HttpServletRequest request) {
		User user = userDAO.findUserByPrimaryKey(username);
		String sql = "select p from AppPostlist p where p.schoolClasses.classNumber like '"
				+ user.getSchoolClasses().getClassNumber() + "'and p.type=1";
		List<AppPostlist> posts = appPostlistDAO.executeQuery(sql, 0, -1);
		// 打乱取前limit个
		Collections.shuffle(posts);
		if (posts.size() > limit) {
			posts.subList(0, limit - 1);
		}

		String servUrl = request.getRequestURL().toString();
		servUrl = servUrl.substring(0, servUrl.length()
				- request.getRequestURI().length())
				+ request.getContextPath();
		String url;

		List<NewPostList> truePosts = new ArrayList<NewPostList>();
		Integer state = 0;
		if (posts != null && posts.size() > 0) {
			for (AppPostlist p : posts) {
				String pudge = " select s from AppPostStatus s where s.appPostlist.id="
						+ p.getId()
						+ " and s.user.username like '"
						+ username
						+ "'and s.type=1";
				List<AppPostStatus> status = appPostStatusDAO
						.executeQuery(pudge);
				if (status != null && status.size() > 0) {
					state = status.get(0).getType();
				}
				SimpleDateFormat sdf = new SimpleDateFormat(
						"yyyy-MM-dd HH-mm-ss");
				String date = sdf.format(p.getTime().getTime());
				url = servUrl + "/" + p.getImagelist();
				NewPostList newPost = new NewPostList(p.getId(), p.getTitle(),
						state, p.getIsstick(), p.getUser().getUsername(), date,
						url);
				truePosts.add(newPost);
				
			}
		}
		return truePosts;
	}

	/*********************************************************************************
	 * @description: 保存新建问卷
	 * @author:张凯
	 * @date：2017/07/13
	 ************************************************************************************/
	public Integer saveNewQuestionnaire(String title, String description,
			List<String> text, Integer type) {
		int flag = 0;
		AppQuestionnaire naire = new AppQuestionnaire();
		naire.setDescription(description);
		naire.setTitle(title);
		naire.setType(type);
		naire.setStat(0);
		naire = appQuestionnaireDAO.store(naire);
		for (String t : text) {
			AppQuestionchoose option = new AppQuestionchoose();
			option.setText(t);
			option.setAppQuestionnaire(naire);
			option.setNum(0);
			appQuestionchooseDAO.store(option);
		}
		flag = 1;
		return flag;
	}

	/*********************************************************************************
	 * @description: 保存问卷答案
	 * @author:唐钦邦
	 * @date：2017/08/09
	 ************************************************************************************/
	public Integer saveQuestionnaireAnswer(Integer ID, List<Integer> mAnswer) {
		int flag = 0;
		AppQuestionnaire naire = new AppQuestionnaire();
		naire = appQuestionnaireDAO.findAppQuestionnaireById(ID);
		naire.setStat(naire.getStat() + 1);
		appQuestionnaireDAO.store(naire);
		List<AppQuestionchoose> option = new ArrayList<AppQuestionchoose>();
		Set a = naire.getAppQuestionchooses();
		System.out.println(a == null);
		for (AppQuestionchoose aqc : naire.getAppQuestionchooses()) {
			option.add(aqc);
		}
		if (naire.getAppQuestionchooses() != null
				&& naire.getAppQuestionchooses().size() > 0) {

			for (int i = 0; i < mAnswer.size(); i++) {
				if (mAnswer.get(i) == 0 ) {
					AppQuestionchoose c = new AppQuestionchoose();
					c = option.get(0);
					c.setNum(c.getNum() + 1);
					appQuestionchooseDAO.store(c);
					flag = 1;
				}
				if (mAnswer.get(i) == 1 && option.size() > 1) {
					AppQuestionchoose c = new AppQuestionchoose();
					c = option.get(1);
					c.setNum(c.getNum() + 1);
					appQuestionchooseDAO.store(c);
					flag = 1;
				}
				if (mAnswer.get(i) == 2 && option.size() > 2) {
					AppQuestionchoose c = new AppQuestionchoose();
					c = option.get(2);
					c.setNum(c.getNum() + 1);
					appQuestionchooseDAO.store(c);
					flag = 1;
				}
				if (mAnswer.get(i) == 3 && option.size() > 3) {
					AppQuestionchoose c = new AppQuestionchoose();
					c = option.get(3);
					c.setNum(c.getNum() + 1);
					appQuestionchooseDAO.store(c);
					flag = 1;
				}
				if (mAnswer.get(i) == 4 && option.size() > 4) {
					AppQuestionchoose c = new AppQuestionchoose();
					c = option.get(4);
					c.setNum(c.getNum() + 1);
					appQuestionchooseDAO.store(c);
					flag = 1;
				}
			}

		}
		return flag;
	}

	/*********************************************************************************
	 * @description: 获取问卷调查列表
	 * @author:张凯
	 * @date：2017/07/13
	 ************************************************************************************/
	public String appQuestionNaireList() {
		String sql = " SELECT q.id,q.title FROM app_questionnaire AS q WHERE 1 = 1";
		return sql;
	}

	/*********************************************************************************
	 * @description: 获取问卷调查列表
	 * @author:张凯
	 * @date：2017/07/13
	 ************************************************************************************/
	public Integer authorityPudge(String username) {
		int flag = 0;
		User user = userDAO.findUserByPrimaryKey(username);
		if (user.getUserRole().equals("1")) {
			flag = 1;
		}
		return flag;
	}

	/*********************************************************************************
	 * @description: 获取问卷调查详情
	 * @author:张凯
	 * @date：2017/07/14
	 * 改
	 ************************************************************************************/
	public QuestionNaireDetail getQuestionNaireDetail(Integer ID) {
		AppQuestionnaire naire = appQuestionnaireDAO
				.findAppQuestionnaireById(ID);
		List<String> text = new ArrayList<String>();
		String sql = "select p from AppQuestionchoose p where p.appQuestionnaire.id='" +ID+ "' order by p.id asc";
		List<AppQuestionchoose> texts = appQuestionchooseDAO.executeQuery(sql,0,-1);
		System.out.println(texts.size());
		if (texts != null&& texts.size() > 0) {
			for (AppQuestionchoose c : texts) {
				text.add(c.getText());
			}
		}
		QuestionNaireDetail detail = new QuestionNaireDetail(
				naire.getDescription(), text, naire.getType());
		return detail;
	}
	public int[] stringsToints(String[] chars) {
		int[] a = new int[chars.length];
		for (int i = 0; i < chars.length; i++) {
			a[i] = Integer.parseInt(chars[i]);
		}
		return a;
	}

	/*********************************************************************************
	 * @description: 查看帖子回复内容(原)
	 * @author:张凯
	 * @date：2017/07/03
	 ************************************************************************************/
	public String viewPostReplies(Integer ID) {
		String sql = "SELECT r.id,r.to_discussion_in_id,r.sponsor,r.time,r.`comment`,r.to_response_id "
				+ "FROM app_post_reply AS r WHERE r.to_discussion_in_id = "
				+ ID;
		return sql;
	}

	/*********************************************************************************
	 * @description: 查看帖子回复内容(改)
	 * @author:赵昶
	 * @date：2017/07/19
	 ************************************************************************************/
	@Override
	public List<ShareAppPostReplyList> getAppPostReplyList(Integer ID) {
		String sql = "SELECT r "
				+ "FROM AppPostReply AS r WHERE r.appPostlist.id = " + ID;
		List<AppPostReply> posts = appPostReplyDAO.executeQuery(sql);
		List<ShareAppPostReplyList> appPostReplyList = new ArrayList<ShareAppPostReplyList>();
		if (posts != null && posts.size() > 0) {
			for (AppPostReply p : posts) {
				SimpleDateFormat sdf = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				String date = sdf.format(p.getTime().getTime());
				ShareAppPostReplyList a = new ShareAppPostReplyList(p.getId(),
						p.getAppPostlist().getId(), p.getUser().getUsername(),
						date, p.getComment(), p.getAppPostReply().getId());
				appPostReplyList.add(a);
			}
		}
		return appPostReplyList;

	}

	/*********************************************************************************
	 * @description: 获取图片列表
	 * @author:赵昶
	 * @date：2017/07/19
	 ************************************************************************************/
	@Override
	public List<NewAlbum> getAlbumlist(String username,
			HttpServletRequest request) {
		User user = userDAO.findUserByPrimaryKey(username);
		// sql语句：在AppPostlist中找到是图片的(type=4),和用户是同一个班级的(classnumber)
		String sql = "select p from AppPostlist p "
				+ " where p.schoolClasses.classNumber like '"
				+ user.getSchoolClasses().getClassNumber() + "' and p.type=4";
		// 取出所有符合条件的记录放入posts中
		List<AppPostlist> posts = appPostlistDAO.executeQuery(sql, 0, -1);
		// truePosts用于存放返回真正要用的数据
		List<NewAlbum> truePosts = new ArrayList<NewAlbum>();
		// 判断有没有取出记录，无符合条件的直接返回
		if (posts != null && posts.size() > 0) {
			// 有符合条件的记录，遍历取出的记录
			for (AppPostlist p : posts) {
				// 前端无法识别时间格式，转换成string在存放
				NewAlbum newPost = null;
				SimpleDateFormat sdf = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				String date = sdf.format(p.getTime().getTime());
				// 从整条记录中，筛选需要的数据
				newPost = new NewAlbum(p.getId(), p.getTitle(),
						p.getImagelist(), p.getUser().getUsername(), date);
				truePosts.add(newPost);
			}
		}
		return truePosts;
	}

	@Transactional
	public void saveAppQuestionchoose(AppQuestionchoose appquestionchoose) {
		AppQuestionchoose existingAppQuestionchoose = appQuestionchooseDAO
				.findAppQuestionchooseByPrimaryKey(appquestionchoose.getId());
		if (existingAppQuestionchoose != null) {
			if (existingAppQuestionchoose != appquestionchoose) {
				existingAppQuestionchoose.setId(appquestionchoose.getId());
				existingAppQuestionchoose.setText(appquestionchoose.getText());
				existingAppQuestionchoose.setChoose(appquestionchoose.getChoose());
				existingAppQuestionchoose.setNum(appquestionchoose.getNum());
		}
			appquestionchoose = appQuestionchooseDAO
					.store(existingAppQuestionchoose);
		} else {
			appquestionchoose = appQuestionchooseDAO.store(appquestionchoose);
		}
		appQuestionchooseDAO.flush();
	}
	
	/*********************************************************************************
	 * @description:获取实验室预约信息
	 * @author:孙广志	
	 * @date：2017/08/10
	 ************************************************************************************/
	@Override
	public List<Appointment> getAppointmentList(String username,
			HttpServletRequest request) {
		User user = userDAO.findUserByPrimaryKey(username);
		//sql语句:根据username查询
		String sql = "select l from LabReservation l"+" where l.user.username like'"+user.getUsername()+"'";
		List<LabReservation> lists =labReservationDAO.executeQuery(sql, 0,-1);		
		List<Appointment> needLists = new ArrayList<Appointment>();
		//判断有没有取出记录
		if(lists!=null&&lists.size()>0){
			//符合条件,遍历数据
			for(LabReservation l:lists){
			Appointment newList = null;
			//从整条记录中，筛选需要的数据
			newList = new Appointment(l.getLabRoom().getLabRoomName(),l.getAuditResults());
			needLists.add(newList);
		}
		}
		return needLists;
	}
	
	
	/*********************************************************************************
	 * @description:推送云盘文件夹信息
	 * @author:孙广志	
	 * @date：2017/08/11
	 ************************************************************************************/
	@Override
	public List<ReturnDownloadFolder> getDownloadFolderList(String username, HttpServletRequest request) {
		User user = userDAO.findUserByPrimaryKey(username);
		//sql语句:根据username,和id查询 文件夹
		String sql = "select d from AppClouddickDownloadFolder d"+" where d.user.username like'"+user.getUsername()+"'";
		List<AppClouddickDownloadFolder> lists =appClouddickDownloadFolderDAO.executeQuery(sql, 0,-1);		
		List<ReturnDownloadFolder> needLists = new ArrayList<ReturnDownloadFolder>();
		//判断有没有取出记录
		if(lists!=null&&lists.size()>0){
			//符合条件,遍历数据
			for(AppClouddickDownloadFolder d:lists){
			ReturnDownloadFolder newList = null;
			//从整条记录中，筛选需要的数据
			StringBuffer url = request.getRequestURL();
			String uri = request.getRequestURI();
			String servletUrl = url.substring(0, url.length()-uri.length())+request.getContextPath();
			newList = new ReturnDownloadFolder(d.getFoldername(),d.getId(),servletUrl+"/"+d.getLocation().toString());
			needLists.add(newList);
		}
		}
		return needLists;
	}
	/*********************************************************************************
	 * @description:推送云盘文件信息
	 * @author:孙广志	
	 * @date：2017/08/11
	 ************************************************************************************/
	@Override
	public List<ReturnDownloadFile> getDownloadFileList(String username,int id, HttpServletRequest request) {
		User user = userDAO.findUserByPrimaryKey(username);
		AppClouddickDownloadFolder appClouddickDownloadFolder = appClouddickDownloadFolderDAO.findAppClouddickDownloadFolderByPrimaryKey(id);
		//sql语句:根据username,和id查询 文件夹
		String sql = "select f from AppClouddickDownloadFile f"+" where f.user.username like '"+user.getUsername()+"' and f.appClouddickDownloadFolder.id="+appClouddickDownloadFolder.getId();
		List<AppClouddickDownloadFile> lists =appClouddickDownloadFileDAO.executeQuery(sql, 0,-1);		
		List<ReturnDownloadFile> needLists = new ArrayList<ReturnDownloadFile>();
		//判断有没有取出记录
		if(lists!=null&&lists.size()>0){
			//符合条件,遍历数据
			for(AppClouddickDownloadFile f:lists){
			ReturnDownloadFile newList = null;
			//从整条记录中，筛选需要的数据
			StringBuffer url = request.getRequestURL();//获取服务器地址
			String uri = request.getRequestURI();
			String servletUrl = url.substring(0, url.length()-uri.length())+request.getContextPath();
			newList = new ReturnDownloadFile(f.getFilename(),servletUrl+"/"+f.getLocation().toString());
			needLists.add(newList);
		}
		}
		return needLists;
	}
	
	/*********************************************************************************
	 * @description:获取新建文件夹信息
	 * @author:孙广志	
	 * @date：2017/08/14
	 ************************************************************************************/
	@Override
	public List<NewFolder> getNewFolderList(String username, String folderName,
			HttpServletRequest request) {
		User user = userDAO.findUserByPrimaryKey(username);
		String realLocation=UUID.randomUUID().toString().replace("-", "");
		//创建一个新的文件夹
		String sep = System.getProperty("file.separator"); 
		String path = request.getSession().getServletContext().getRealPath( "/") + sep + realLocation;
		//System.out.println(path);
		File sendPath = new File(path);
	    if(!sendPath.exists()){
	        sendPath.mkdirs();
	    }
	    
	  //添加一条新文件夹信息
	   
	    AppClouddickDownloadFolder a=new AppClouddickDownloadFolder();
	    a.setUser(user);
	    a.setFoldername(folderName);
	    a.setLocation(realLocation);
	    //判断数据库中是否有地址相等的数据
	    String sql ="select c from AppClouddickDownloadFolder c where c.foldername like  '"+folderName+"' and c.user.username like '" +username+"'";
	    List<AppClouddickDownloadFolder> list =appClouddickDownloadFolderDAO.executeQuery(sql);
	    
	    if(list.isEmpty()){
	    	
	    appClouddickDownloadFolderDAO.store(a);    
	    }else{
	    	System.out.println("创建失败,文件夹名称重复,'"+folderName+"'");	
	    boolean flag = false;
	   // System.out.println(flag);
	    }
	    //取数据
	    String sql2 = "select f from AppClouddickDownloadFolder f "+" where f.user.username like '"+user.getUsername()+"' and f.foldername like '"+folderName+"'";
	    List<AppClouddickDownloadFolder> listss =appClouddickDownloadFolderDAO.executeQuery(sql2, 0,-1);
	    List<NewFolder> needLists = new ArrayList<NewFolder>();
	  //判断有没有取出记录
	  		if(listss!=null&&listss.size()>0){
	  			//符合条件,遍历数据
	  			for(AppClouddickDownloadFolder f:listss){
	  			NewFolder newList = null;
	  			//从整条记录中，筛选需要的数据
	  			StringBuffer url = request.getRequestURL();//获取服务器地址
	  			String uri = request.getRequestURI();
	  			String servletUrl = url.substring(0, url.length()-uri.length())+request.getContextPath();
	  			//System.out.println(servletUrl);
	  			newList = new NewFolder(f.getId(),f.getFoldername(),servletUrl+"/"+f.getLocation().toString());
	  			needLists.add(newList);
	  		}
	  		}
	  		return needLists;
	}
	
	
	/*********************************************************************************
	 * @description: 新建文件保存 
	 * @author:孙广志	
	 * @date：2017/08/16
	 ************************************************************************************/
	@Override
	public AppClouddickDownloadFile saveNewFile(String username,String path,int fromFolder, String fileNewName, int id){
		AppClouddickDownloadFile file = new AppClouddickDownloadFile();
		AppClouddickDownloadFile appClouddickDownloadFile = appClouddickDownloadFileDAO.findAppClouddickDownloadFileById(id);
		User user = userDAO.findUserByPrimaryKey(username);
		AppClouddickDownloadFolder appClouddickDownloadFolder = appClouddickDownloadFolderDAO.findAppClouddickDownloadFolderByPrimaryKey(id);
		file.setUser(user);
		String fileUrl = "upload"+path+"/"+fileNewName;
		file.setLocation(fileUrl);
		file.setAppClouddickDownloadFolder(appClouddickDownloadFolder);
		file = appClouddickDownloadFileDAO.store(file);
		appClouddickDownloadFileDAO.flush();
		return file;
	}
	/*********************************************************************************
	 * @description:保存上传文件
	 * @author:孙广志	
	 * @date：2017/08/16
	 ************************************************************************************/
	@Override
	public String getSaveUploadFile(String username,int id,HttpServletRequest request,
			HttpServletResponse response) {
		MultipartHttpServletRequest  multipartRequest = (MultipartHttpServletRequest)request; 
		String sep = System.getProperty("file.separator"); 
		Map files = multipartRequest.getFileMap(); 
		Iterator fileNames = multipartRequest.getFileNames();
		boolean flag =false; 
		User user = userDAO.findUserByPrimaryKey(username);
		String userName = user.getUsername();
		String fileDir = multipartRequest.getSession().getServletContext().getRealPath("/")+ "upload"+sep;
		System.out.println(fileDir);
		//保存上传文件的id
		String ids = "";
		//存放文件文件夹名称
		for(; fileNames.hasNext();){
		  String filename = (String) fileNames.next(); 
		  CommonsMultipartFile file = (CommonsMultipartFile) files.get(filename); 
		  byte[] bytes = file.getBytes(); 
		  if(bytes.length!=0){
			  // 说明申请有附件
			  if(!flag) { 
				  File dirPath = new File(fileDir); 
				  if(!dirPath.exists()){ 
					  flag = dirPath.mkdirs();
		              }
		      } 
			  String fileTrueName = file.getOriginalFilename(); 
			  //文件重命名
			  int endAddress = fileTrueName.lastIndexOf(".");
			  String ss = fileTrueName.substring(endAddress, fileTrueName.length());//后缀名
			  String fileNewName = UUID.randomUUID().toString()+ss; 
			  File uploadedFile = new File(fileDir + sep + fileNewName); 
			  try {
				FileCopyUtils.copy(bytes,uploadedFile);
			} catch (IOException e) {
				e.printStackTrace();
			}

			  String path = "/"+UUID.randomUUID().toString()+ss;
			  AppClouddickDownloadFile p = this.saveNewFile(userName, path, id, fileNewName, id);
			  ids+= p.getId();
		  }else {
			  ids+="failed";
		}
		}
		return ids;
	}


}
