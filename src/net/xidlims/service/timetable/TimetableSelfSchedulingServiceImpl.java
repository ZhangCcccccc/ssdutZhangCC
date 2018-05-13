package net.xidlims.service.timetable;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import net.xidlims.service.common.MySQLService;
import net.xidlims.service.common.ShareService;
import net.xidlims.dao.LabCenterDAO;
import net.xidlims.dao.LabRoomDAO;
import net.xidlims.dao.OperationItemDAO;
import net.xidlims.dao.TimetableAppointmentDAO;
import net.xidlims.dao.TimetableAppointmentSameNumberDAO;
import net.xidlims.dao.TimetableBatchDAO;
import net.xidlims.dao.TimetableBatchItemsDAO;
import net.xidlims.dao.TimetableCourseStudentDAO;
import net.xidlims.dao.TimetableGroupDAO;
import net.xidlims.dao.TimetableGroupStudentsDAO;
import net.xidlims.dao.TimetableItemRelatedDAO;
import net.xidlims.dao.TimetableLabRelatedDAO;
import net.xidlims.dao.TimetableSelfCourseDAO;
import net.xidlims.dao.TimetableTeacherRelatedDAO;
import net.xidlims.dao.UserDAO;
import net.xidlims.domain.LabRoom;
import net.xidlims.domain.OperationItem;
import net.xidlims.domain.TimetableAppointment;
import net.xidlims.domain.TimetableAppointmentSameNumber;
import net.xidlims.domain.TimetableBatch;
import net.xidlims.domain.TimetableBatchItems;
import net.xidlims.domain.TimetableCourseStudent;
import net.xidlims.domain.TimetableGroup;
import net.xidlims.domain.TimetableGroupStudents;
import net.xidlims.domain.TimetableItemRelated;
import net.xidlims.domain.TimetableLabRelated;
import net.xidlims.domain.TimetableSelfCourse;
import net.xidlims.domain.TimetableTeacherRelated;
import net.xidlims.domain.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("TimetableSelfSchedulingService")
public class TimetableSelfSchedulingServiceImpl implements TimetableSelfSchedulingService {
	@Autowired
	private OperationItemDAO operationItemDAO;
	@Autowired
	private TimetableGroupDAO timetableGroupDAO;
	@Autowired
	private TimetableBatchDAO timetableBatchDAO;
	@Autowired
	private TimetableBatchItemsDAO timetableBatchItemsDAO;
	@Autowired
	private ShareService shareService;
	@Autowired
	private TimetableAppointmentDAO timetableAppointmentDAO;
	@Autowired
	private TimetableTeacherRelatedDAO timetableTeacherRelatedDAO;
	@Autowired
	private TimetableLabRelatedDAO timetableLabRelatedDAO;
	@Autowired
	private TimetableItemRelatedDAO timetableItemRelatedDAO;
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private LabRoomDAO labRoomDAO;
	@Autowired
	private TimetableSelfCourseDAO timetableSelfCourseDAO;
	@Autowired
	private TimetableAppointmentSameNumberDAO timetableAppointmentSameNumberDAO;
	@Autowired
	private LabCenterDAO labCenterDAO;
	@Autowired
	private TimetableGroupStudentsDAO timetableGroupStudentsDAO;
	@Autowired
	private TimetableCourseSchedulingService timetableCourseSchedulingService;
	@Autowired
	private MySQLService mysqlService;
	@Autowired
	private TimetableCourseStudentDAO timetableCourseStudentDAO;
	
	/*************************************************************************************
	 * @內容：保存 分组信息
	 * @作者： 魏誠
	 * @日期：2014-07-24
	 *************************************************************************************/
	public void saveTimetableGroup(HttpServletRequest request) {
		// 获取选课组
		String courseCode = request.getParameter("courseCode");
		String batchName=request.getParameter("batchName");
		// 分组计数
		int countGroup = Integer.parseInt(request.getParameter("countGroup"));
		/**
		 * 新建批次
		 **/

		TimetableBatch timetableBatch = new TimetableBatch();
		// 获取相同选课组的最大批次
		int maxBatch = timetableBatchDAO.executeQuery(
				"select c from TimetableBatch c where c.courseCode like '" + courseCode + "'").size();
		maxBatch = maxBatch + 1;
		timetableBatch.setCountGroup(countGroup);
		timetableBatch.setCourseCode(courseCode);
		timetableBatch.setBatchName("第" + maxBatch + "批");
		if(batchName!=null){
			timetableBatch.setBatchName(batchName);
		}
		timetableBatch.setIfselect(Integer.parseInt(request.getParameter("ifselect")));
		try {
			Calendar ccalendarStart = Calendar.getInstance();
			Calendar ccalendarEnd = Calendar.getInstance();
			SimpleDateFormat sstartDate = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat eendDate = new SimpleDateFormat("yyyy-MM-dd");
			Date ddate1 = sstartDate.parse(request.getParameter("startDate"));
			Date ddate2 = eendDate.parse(request.getParameter("endDate"));
			ccalendarStart.setTime(ddate1);
			ccalendarEnd.setTime(ddate2);
			timetableBatch.setStartDate(ccalendarStart);
			timetableBatch.setEndDate(ccalendarEnd);
			timetableBatch = timetableBatchDAO.store(timetableBatch);
		} catch (Exception e) {

		}

		// 保存批次相关的实验项目
		String[] items = request.getParameterValues("item");
		int length = items.length;
		if (items[length - 1].equals("")) {
			length = length - 1;
		}
		TimetableBatchItems timetableBatchItems = new TimetableBatchItems();
		try {
			if (items.length > 0) {
				for (int i = 0; i < length; i++) {
					// 将matchLabs添加到matchLabs中
					timetableBatchItems.setOperationItem((operationItemDAO.findOperationItemByPrimaryKey(Integer
							.parseInt(items[i]))));
					timetableBatchItems.setTimetableBatch(timetableBatch);
					timetableBatchItemsDAO.store(timetableBatchItems);
				}
			}
			/**
			 * 新建组
			 **/
			// 获取传入参数
			String[] numbers = request.getParameterValues("numbers");
			String[] groupNames = request.getParameterValues("groupName");
			/*
			 * String[] startDate = request.getParameterValues("startDate");
			 * String[] endDate = request.getParameterValues("endDate");
			 */
			

			for (int i = 0; i < countGroup; i++) {
				TimetableGroup timetableGroup = new TimetableGroup();
				// 日期处理
				/*
				 * Calendar calendarStart = Calendar.getInstance(); Calendar
				 * calendarEnd = Calendar.getInstance(); SimpleDateFormat
				 * dStartDate = new SimpleDateFormat("yyyy-MM-dd"); Date date1 =
				 * dStartDate.parse(startDate[i]);
				 * 
				 * SimpleDateFormat dEndDate = new
				 * SimpleDateFormat("yyyy-MM-dd"); Date date2 =
				 * dEndDate.parse(endDate[i]); calendarStart.setTime(date1);
				 * timetableGroup.setStartDate(calendarStart);
				 * calendarEnd.setTime(date2);
				 * timetableGroup.setEndDate(calendarEnd);
				 */
				timetableGroup.setGroupName("第" + String.valueOf(i + 1) + "组");
				if(groupNames!=null && groupNames[i]!=null){
					timetableGroup.setGroupName(groupNames[i]);
				}
				timetableGroup.setNumbers(Integer.parseInt(numbers[i]));
				timetableGroup.setTimetableBatch(timetableBatch);
				timetableGroup.setTimetableAppointment(null);
				// TimetableGroup timetableGroupSave = new TimetableGroup();
				timetableGroup=timetableGroupDAO.store(timetableGroup);
				Integer j = i+1;
				//录入
				if (timetableBatch.getIfselect() == 2) {
					String[] studentIds = request.getParameterValues("student_"+j.toString());
					for (int k = 1; k <= timetableGroup.getNumbers(); k++) {
						TimetableGroupStudents timetableGroupStudents = new TimetableGroupStudents();
						timetableGroupStudents.setUser(timetableCourseStudentDAO.findTimetableCourseStudentByPrimaryKey(Integer.parseInt(studentIds[k-1])).getUser());
						timetableGroupStudents.setTimetableGroup(timetableGroup);
						timetableGroupStudentsDAO.store(timetableGroupStudents);
						timetableGroupStudentsDAO.flush();
					}
				}
			}
		} catch (Exception e) {

		}
	}

	/*************************************************************************************
	 * @內容：根据选课组编号获取分组信息
	 * @作者： 魏誠
	 * @日期：2014-07-24
	 *************************************************************************************/
	public List<TimetableGroup> getTimetableGroupByCourseCode(String courseCode) {
		List<TimetableGroup> timetableGroups = timetableGroupDAO
				.executeQuery("select c from TimetableGroup c where c.timetableBatch.courseCode like '" + courseCode
						+ "' order by c.timetableBatch.courseCode,c.timetableBatch.id desc,c.id asc");
		return timetableGroups;
	}

	/*************************************************************************************
	 * @throws ParseException 
	 * @內容：保存自主排课的不分组排课的内容
	 * @作者： 魏誠
	 * @日期：2014-08-5
	 *************************************************************************************/
	@Transactional
	public TimetableAppointment saveNoGroupSelfTimetable(HttpServletRequest request) throws ParseException {

		TimetableAppointment timetableAppointment = new TimetableAppointment();
		// 调整排课的实验室选择
		String[] labRooms = request.getParameterValues("labRooms");
		//调整排课的实验室设备选择
		String[]  sLabRoomDevice = request.getParameterValues("labRoomDevice_id");
		// 调整排课的实验项目选择
		String[] items = request.getParameterValues("items");
		// 调整排课的授课教师选择
		String[] teachers = request.getParameterValues("teachers");
		String weekday = request.getParameter("weekday");
		// 调整排课的星期选择
		String[] weeks = request.getParameterValues("weeks");
		int[] intWeeks = new int[weeks.length];

		for (int i = 0; i < weeks.length; i++) {
			intWeeks[i] = Integer.parseInt(weeks[i]);
		}
		// 周次进行排序
		String[] sWeek = this.getTimetableWeekClass(intWeeks);
		// 调整排课的节次选择
		String[] classes = request.getParameterValues("classes");
		int[] intClasses = new int[classes.length];
		for (int i = 0; i < classes.length; i++) {
			intClasses[i] = Integer.parseInt(classes[i]);
		}

		// 节次进行排序
		String[] sClasses = this.getTimetableWeekClass(intClasses);

		// TimetableAppointment();
		TimetableSelfCourse timetableSelfCourse = timetableSelfCourseDAO.findTimetableSelfCourseById(Integer
				.parseInt(request.getParameter("courseCode")));

		/**
		 * 如果一次排课，排课的周次或节次都是连续的，则保存主表记录
		 **/
		timetableAppointment.setAppointmentNo(timetableSelfCourse.getCourseCode() + "-" + timetableSelfCourse.getId());
		// timetableAppointment.setTimetableNumber(timetableAppointment.getTimetableNumber());
		timetableAppointment.setCreatedBy(shareService.getUserDetail().getUsername());
		timetableAppointment.setCreatedBy(shareService.getUserDetail().getUsername());
		timetableAppointment.setCreatedDate(Calendar.getInstance());
		timetableAppointment.setUpdatedDate(Calendar.getInstance());
		// 设置排课方式为二次排课的分组排课模式
		timetableAppointment.setTimetableStyle(5);
		timetableAppointment.setSchoolCourseInfo(timetableSelfCourse.getSchoolCourseInfo());
		// 设置排课状态为待发布
		timetableAppointment.setStatus(10);
		timetableAppointment.setTimetableSelfCourse(timetableSelfCourse);
		timetableAppointment.setCourseCode(timetableSelfCourse.getCourseCode());
		timetableAppointment.setWeekday(Integer.parseInt(weekday));
		if (request.getParameter("preparation") != null) {
			timetableAppointment.setPreparation(request.getParameter("preparation"));
		} else {
			timetableAppointment.setPreparation("");
		}
		if (request.getParameter("groups") != null && !request.getParameter("groups").isEmpty()) {
			timetableAppointment.setGroups(Integer.parseInt(request.getParameter("groups")));
		} else {
			timetableAppointment.setGroups(-1);
		}
		if (request.getParameter("labhours") != null && !request.getParameter("labhours").isEmpty()) {
			timetableAppointment.setLabhours(Integer.parseInt(request.getParameter("labhours")));
		} else {
			timetableAppointment.setLabhours(-1);
		}
		if (request.getParameter("groupCount") != null && !request.getParameter("groupCount").isEmpty()) {
			timetableAppointment.setGroupCount(Integer.parseInt(request.getParameter("groupCount")));
		} else {
			timetableAppointment.setGroupCount(-1);
		}
		if (request.getParameter("consumablesCosts") != null) {
			timetableAppointment.setConsumablesCosts(new BigDecimal((request.getParameter("consumablesCosts"))));
		} else {
			timetableAppointment.setConsumablesCosts(new BigDecimal(-1));
		}
		// 设置调整排课的内容
		if (sWeek[0].indexOf(("-")) == -1) {
			timetableAppointment.setTotalWeeks("1");
			timetableAppointment.setStartWeek(Integer.parseInt(sWeek[0]));
			timetableAppointment.setEndWeek(Integer.parseInt(sWeek[0]));

		} else {
			timetableAppointment.setTotalWeeks(String.valueOf((Integer.parseInt(sWeek[0].split("-")[1]) - Integer
					.parseInt(sWeek[0].split("-")[0]))));
			timetableAppointment.setStartWeek(Integer.parseInt(sWeek[0].split("-")[0]));
			timetableAppointment.setEndWeek(Integer.parseInt(sWeek[0].split("-")[1]));
		}

		if (sClasses[0].indexOf(("-")) == -1) {
			timetableAppointment.setTotalClasses(Integer.parseInt(sClasses[0]));
			timetableAppointment.setStartClass(Integer.parseInt(sClasses[0]));
			timetableAppointment.setEndClass(Integer.parseInt(sClasses[0]));
		} else {
			timetableAppointment.setTotalClasses((Integer.parseInt(sClasses[0].split("-")[1]) - Integer
					.parseInt(sClasses[0].split("-")[0])));
			timetableAppointment.setStartClass(Integer.parseInt(sClasses[0].split("-")[0]));
			timetableAppointment.setEndClass(Integer.parseInt(sClasses[0].split("-")[1]));
		}
		TimetableAppointment timetableAppointmentNew = timetableAppointmentDAO.store(timetableAppointment);
		timetableAppointmentDAO.flush();
		TimetableLabRelated timetableLabRelated = new TimetableLabRelated();
		
		int countLabRoomDevice = 0; // 计算所选实验室总共有多少设备 
		// 如果matchLabs不为空时
		if (labRooms != null && labRooms.length > 0) {
			for (String i1 : labRooms) {
				LabRoom labRoom = labRoomDAO.findLabRoomById(Integer.parseInt(i1));
				// 将matchLabs添加到matchLabs中
				timetableLabRelated.setLabRoom(labRoom);
				timetableLabRelated.setTimetableAppointment(timetableAppointmentNew);
				timetableLabRelatedDAO.store(timetableLabRelated);
				timetableLabRelatedDAO.flush();
				
				// 实验室设备数量累加
                if (labRoom.getLabRoomDevices()!=null && labRoom.getLabRoomDevices().size()>0) {
                    countLabRoomDevice+=labRoom.getLabRoomDevices().size();
                }
				
			}
		}
		
		// 设置此次排课的针对对象（1 设备  2 实验室）
		if ((sLabRoomDevice!=null && countLabRoomDevice == sLabRoomDevice.length)  
				|| sLabRoomDevice==null) {// i.全选情况--纺织学院   ii.其他学院sLabRoomDevice字段为空
			timetableAppointmentNew.setDeviceOrLab(2);// 此次排课针对实验室
		}else {
			timetableAppointmentNew.setDeviceOrLab(1);// 此次排课针对设备
		}
		timetableAppointmentNew = timetableAppointmentDAO.store(timetableAppointmentNew);

		/*
		 * 对排课预约选定的指导老师进行保存
		 */
		TimetableTeacherRelated timetableTeacherRelated = new TimetableTeacherRelated();
		// 获取选择的实验室列表
		List<User> matchTeachers = new ArrayList<User>();
		// 如果matchLabs不为空时
		if (teachers!=null&&teachers.length > 0) {
			for (int i1 = 0; i1 < teachers.length; i1++) {
				// 将matchLabs添加到matchLabs中
				matchTeachers.add(userDAO.findUserByUsername(teachers[i1]));
				timetableTeacherRelated.setUser(userDAO.findUserByUsername(teachers[i1]));
				timetableTeacherRelated.setTimetableAppointment(timetableAppointmentNew);
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
		if (items!=null&&items.length > 0) {
			for (int i1 = 0; i1 < items.length; i1++) {
				// 将matchLabs添加到matchLabs中
				matchItems.add(operationItemDAO.findOperationItemById(Integer.parseInt(items[i1])));
				timetableItemRelated.setOperationItem(operationItemDAO.findOperationItemById(Integer
						.parseInt(items[i1])));
				timetableItemRelated.setTimetableAppointment(timetableAppointmentNew);
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

					timetableAppointmentSameNumber.setCreatedBy(shareService.getUserDetail().getUsername());
					timetableAppointmentSameNumber.setCreatedBy(shareService.getUserDetail().getUsername());
					timetableAppointmentSameNumber.setCreatedDate(Calendar.getInstance());
					timetableAppointmentSameNumber.setUpdatedDate(Calendar.getInstance());

					// 设置调整排课的内容
					if (sWeek[i].indexOf(("-")) == -1) {
						timetableAppointmentSameNumber.setTotalWeeks("1");
						timetableAppointmentSameNumber.setStartWeek(Integer.parseInt(sWeek[i]));
						timetableAppointmentSameNumber.setEndWeek(Integer.parseInt(sWeek[i]));

					} else {
						timetableAppointmentSameNumber.setTotalWeeks(String.valueOf((Integer.parseInt(sWeek[i]
								.split("-")[1]) - Integer.parseInt(sWeek[i].split("-")[0]))));
						timetableAppointmentSameNumber.setStartWeek(Integer.parseInt(sWeek[i].split("-")[0]));
						timetableAppointmentSameNumber.setEndWeek(Integer.parseInt(sWeek[i].split("-")[1]));
					}

					if (sClasses[j].indexOf(("-")) == -1) {
						timetableAppointmentSameNumber.setTotalClasses(Integer.parseInt(sClasses[j]));
						timetableAppointmentSameNumber.setStartClass(Integer.parseInt(sClasses[j]));
						timetableAppointmentSameNumber.setEndClass(Integer.parseInt(sClasses[j]));
					} else {
						timetableAppointmentSameNumber
								.setTotalClasses((Integer.parseInt(sClasses[j].split("-")[1]) - Integer
										.parseInt(sClasses[j].split("-")[0])));
						timetableAppointmentSameNumber.setStartClass(Integer.parseInt(sClasses[j].split("-")[0]));
						timetableAppointmentSameNumber.setEndClass(Integer.parseInt(sClasses[j].split("-")[1]));
					}
					timetableAppointmentSameNumber.setTimetableAppointment(timetableAppointmentNew);
					timetableAppointmentSameNumberDAO.store(timetableAppointmentSameNumber);
					timetableAppointmentSameNumberDAO.flush();

				}
			}
		}
		timetableLabRelatedDAO.flush();
		List<TimetableLabRelated> timetableLabRelateds = timetableLabRelatedDAO.executeQuery("select c from TimetableLabRelated c where c.timetableAppointment.id="+timetableAppointmentNew.getId(), 0,-1);
		if(timetableAppointmentNew.getDeviceOrLab().equals(1)){
			for(TimetableLabRelated timetableLabRelatedTmp:timetableLabRelateds){
				timetableCourseSchedulingService.saveTimetableLabroomDeviceReservation(timetableLabRelatedTmp, sLabRoomDevice,timetableSelfCourse.getSchoolTerm().getId());

			}
		}else {
			mysqlService.createLabLimitByAppointment(timetableAppointmentNew.getId());
		}
		return timetableAppointmentNew;
	}

	/*************************************************************************************
	 * @內容：返回排课节次或者周次
	 * @作者： 魏誠
	 * @日期：2014-08-5
	 *************************************************************************************/
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
	 * @內容：根据实验室和节次及星期列出所有时间列表安排
	 * @作者： 魏誠
	 * @日期：2014-07-24
	 *************************************************************************************/
	public List<TimetableLabRelated> getSelfListLabTimetableAppointments(HttpServletRequest request, int iLabCenter,
			int term) {
		String academyNumber="";
        // 如果没有获取有效的实验分室列表-根据登录用户的所属学院
        if (iLabCenter != -1) {
    		//获取选择的实验中心
        	academyNumber = shareService.getUserDetail().getSchoolAcademy().getAcademyNumber();
        }else{
        	academyNumber = shareService.getUserDetail().getSchoolAcademy().getAcademyNumber();
        }
		// 创建根据学期来查询课程；
		StringBuffer sql = new StringBuffer(
				"select c from TimetableLabRelated c where 1=1 and  c.timetableAppointment.timetableSelfCourse.schoolTerm.id ="
						+ term + " and " + "c.timetableAppointment.schoolCourseInfo.academyNumber like '"
						+ academyNumber + "' "
		/* + request.getParameter("term") */);
		if (request.getParameter("labroom") != null && !request.getParameter("labroom").equals("-1")) {
			sql.append(" and c.labRoom.id = " + request.getParameter("labroom"));
		}
		/*
		 * if(request.getParameter("week")!=null&&!request.getParameter("week").
		 * equals("-1")){ sql.append(" and c.timetableAppointment.startWeek <= "
		 * + request.getParameter("week") +
		 * " and c.timetableAppointment.endWeek >= " +
		 * request.getParameter("week")); }
		 */
		List<TimetableLabRelated> timetableLabRelateds = timetableLabRelatedDAO.executeQuery(sql.toString());
		return timetableLabRelateds;
	}

	/*************************************************************************************
	 * @內容：根据实验室和节次及星期列出所有时间列表安排
	 * @作者： 魏誠
	 * @日期：2014-07-24
	 *************************************************************************************/
	public List<TimetableLabRelated> getListLabTimetableAppointments(HttpServletRequest request, int iLabCenter,
			int term) {
		String academyNumber="";
        // 如果没有获取有效的实验分室列表-根据登录用户的所属学院
        if (iLabCenter != -1) {
    		//获取选择的实验中心
        	academyNumber = shareService.getUserDetail().getSchoolAcademy().getAcademyNumber();
        }else{
        	academyNumber = shareService.getUserDetail().getSchoolAcademy().getAcademyNumber();
        }
		// 创建根据学期来查询课程；
		StringBuffer sql = new StringBuffer(
				"select c from TimetableLabRelated c where 1=1 and  c.timetableAppointment.schoolCourse.schoolTerm.id = "
						+ term + " and " + "c.timetableAppointment.schoolCourseInfo.academyNumber like '"
						+ academyNumber + "' "
		/* + request.getParameter("term") */);
		if (request.getParameter("labroom") != null && !request.getParameter("labroom").equals("-1")) {
			sql.append(" and c.labRoom.id = " + request.getParameter("labroom"));
		}
		/*
		 * if(request.getParameter("week")!=null&&!request.getParameter("week").
		 * equals("-1")){ sql.append(" and c.timetableAppointment.startWeek <= "
		 * + request.getParameter("week") +
		 * " and c.timetableAppointment.endWeek >= " +
		 * request.getParameter("week")); }
		 */
		List<TimetableLabRelated> timetableLabRelateds = timetableLabRelatedDAO.executeQuery(sql.toString());
		return timetableLabRelateds;
	}

	/*************************************************************************************
	 * @throws ParseException 
	 * @內容：保存自主排课的分组排课的内容
	 * @作者： 魏誠
	 * @日期：2014-08-5
	 *************************************************************************************/
	@Transactional
	public TimetableAppointment saveGroupReTimetable(HttpServletRequest request) throws ParseException {
		TimetableAppointment timetableAppointment = new TimetableAppointment();
		TimetableAppointment timetableAppointmentReturn = new TimetableAppointment();
		// 获取本分组下有多少个timetableappointment-group的记录

		// 调整排课的实验室选择
		String[] labRooms = request.getParameterValues("labRooms");
		//调整排课的实验室设备选择
		String[]  sLabRoomDevice = request.getParameterValues("labRoomDevice_id");
		// 调整排课的实验项目选择
		String[] items = request.getParameterValues("items");
		// 调整排课的授课教师选择
		String[] teachers = request.getParameterValues("teachers");
		String weekday = request.getParameter("weekday");
		// 调整排课的星期选择
		String[] weeks = request.getParameterValues("weeks");
		int[] intWeeks = new int[weeks.length];

		for (int i = 0; i < weeks.length; i++) {
			intWeeks[i] = Integer.parseInt(weeks[i]);
		}
		// 周次进行排序
		String[] sWeek = this.getTimetableWeekClass(intWeeks);
		// 调整排课的节次选择
		String[] classes = request.getParameterValues("classes");
		int[] intClasses = new int[classes.length];
		for (int i = 0; i < classes.length; i++) {
			intClasses[i] = Integer.parseInt(classes[i]);
		}

		// 节次进行排序
		String[] sClasses = this.getTimetableWeekClass(intClasses);
		/*
		 * SchoolCourseDetail schoolCourseDetail = schoolCourseDetailDAO
		 * .findSchoolCourseDetailByCourseDetailNo(request
		 * .getParameter("courseDetailNo"));
		 */
		TimetableSelfCourse timetableSelfCourse = timetableSelfCourseDAO.findTimetableSelfCourseById(Integer
				.parseInt(request.getParameter("courseCode")));

		/**
		 * 如果一次排课，排课的周次或节次都是连续的，则保存主表记录
		 **/
		timetableAppointment.setAppointmentNo(timetableSelfCourse.getCourseCode() + "-" + timetableSelfCourse.getId());
		// timetableAppointment.setTimetableNumber(timetableAppointment.getTimetableNumber());
		timetableAppointment.setCreatedBy(shareService.getUserDetail().getUsername());
		timetableAppointment.setCreatedBy(shareService.getUserDetail().getUsername());
		timetableAppointment.setCreatedDate(Calendar.getInstance());
		timetableAppointment.setUpdatedDate(Calendar.getInstance());
		// 设置排课方式为自主排课的分组排课模式
		timetableAppointment.setTimetableStyle(6);
		// 设置排课状态为待发布
		timetableAppointment.setStatus(10);
		timetableAppointment.setSchoolCourseInfo(timetableSelfCourse.getSchoolCourseInfo());

		// 设置排课的选课组编号
		timetableAppointment.setCourseCode(timetableSelfCourse.getCourseCode());
		timetableAppointment.setTimetableSelfCourse(timetableSelfCourse);
		timetableAppointment.setCourseCode(timetableSelfCourse.getCourseCode());
		timetableAppointment.setCreatedBy(shareService.getUserDetail().getUsername());
		timetableAppointment.setCreatedBy(shareService.getUserDetail().getUsername());
		timetableAppointment.setCreatedDate(Calendar.getInstance());
		if (request.getParameter("preparation") != null) {
			timetableAppointment.setPreparation(request.getParameter("preparation"));
		} else {
			timetableAppointment.setPreparation("");
		}
		if (request.getParameter("groups") != null && !request.getParameter("groups").isEmpty()) {
			timetableAppointment.setGroups(Integer.parseInt(request.getParameter("groups")));
		} else {
			timetableAppointment.setGroups(-1);
		}
		if (request.getParameter("groups") != null && !request.getParameter("labhours").isEmpty()) {
			timetableAppointment.setLabhours(Integer.parseInt(request.getParameter("labhours")));
		} else {
			timetableAppointment.setLabhours(-1);
		}
		if (request.getParameter("groups") != null && !request.getParameter("groupCount").isEmpty()) {
			timetableAppointment.setGroupCount(Integer.parseInt(request.getParameter("groupCount")));
		} else {
			timetableAppointment.setGroupCount(-1);
		}
		if (request.getParameter("consumablesCosts") != null) {
			timetableAppointment.setConsumablesCosts(new BigDecimal((request.getParameter("consumablesCosts"))));
		} else {
			timetableAppointment.setConsumablesCosts(new BigDecimal(-1));
		}
		timetableAppointment.setWeekday(Integer.parseInt(weekday));
		timetableAppointment.setUpdatedDate(Calendar.getInstance());
		// 设置调整排课的内容
		if (sWeek[0].indexOf(("-")) == -1) {
			timetableAppointment.setTotalWeeks("1");
			timetableAppointment.setStartWeek(Integer.parseInt(sWeek[0]));
			timetableAppointment.setEndWeek(Integer.parseInt(sWeek[0]));

		} else {
			timetableAppointment.setTotalWeeks(String.valueOf((Integer.parseInt(sWeek[0].split("-")[1]) - Integer
					.parseInt(sWeek[0].split("-")[0]))));
			timetableAppointment.setStartWeek(Integer.parseInt(sWeek[0].split("-")[0]));
			timetableAppointment.setEndWeek(Integer.parseInt(sWeek[0].split("-")[1]));
		}

		if (sClasses[0].indexOf(("-")) == -1) {
			timetableAppointment.setTotalClasses(Integer.parseInt(sClasses[0]));
			timetableAppointment.setStartClass(Integer.parseInt(sClasses[0]));
			timetableAppointment.setEndClass(Integer.parseInt(sClasses[0]));
		} else {
			timetableAppointment.setTotalClasses((Integer.parseInt(sClasses[0].split("-")[1]) - Integer
					.parseInt(sClasses[0].split("-")[0])));
			timetableAppointment.setStartClass(Integer.parseInt(sClasses[0].split("-")[0]));
			timetableAppointment.setEndClass(Integer.parseInt(sClasses[0].split("-")[1]));
		}

		// 保存排课分组信息
		Set<TimetableGroup> timetableGroupList = new HashSet<TimetableGroup>();
		// timetableGroupDAO.executeQuery("SELECT t FROM TimetableGroup t join t.timetableAppointments s where t.id = ",0,-1);
		timetableGroupList.addAll(timetableGroupDAO.executeQuery(
				"SELECT t FROM TimetableGroup t join t.timetableAppointments s where t.id = "
						+ Integer.parseInt(request.getParameter("group")), 0, -1));
		timetableGroupList
				.add(timetableGroupDAO.findTimetableGroupById(Integer.parseInt(request.getParameter("group"))));
		timetableAppointment.setTimetableGroups(timetableGroupList);

		// 保存排课记录
		TimetableAppointment timetableAppointmentNew = timetableAppointmentDAO.store(timetableAppointment);
		timetableAppointmentDAO.flush();

		timetableAppointmentReturn = timetableAppointmentNew;
		TimetableLabRelated timetableLabRelated = new TimetableLabRelated();
		
		int countLabRoomDevice = 0; // 计算所选实验室总共有多少设备 
		// 如果matchLabs不为空时
		if (labRooms != null && labRooms.length > 0) {
			for (int i1 = 0; i1 < labRooms.length; i1++) {
				LabRoom labRoom = labRoomDAO.findLabRoomById(Integer.parseInt(labRooms[i1]));
				// 将matchLabs添加到matchLabs中
				timetableLabRelated.setLabRoom(labRoom);
				timetableLabRelated.setTimetableAppointment(timetableAppointmentNew);
				timetableLabRelatedDAO.store(timetableLabRelated);
				timetableLabRelatedDAO.flush();
				
				// 实验室设备数量累加
                if (labRoom.getLabRoomDevices()!=null && labRoom.getLabRoomDevices().size()>0) {
                    countLabRoomDevice+=labRoom.getLabRoomDevices().size();
                }
				
			}
		}
		
		// 设置此次排课的针对对象（1 设备  2 实验室）
		if ((sLabRoomDevice!=null && countLabRoomDevice == sLabRoomDevice.length)  
				|| sLabRoomDevice==null) {// i.全选情况--纺织学院   ii.其他学院sLabRoomDevice字段为空
			timetableAppointmentNew.setDeviceOrLab(2);// 此次排课针对实验室
		}else {
			timetableAppointmentNew.setDeviceOrLab(1);// 此次排课针对设备
		}
		timetableAppointmentNew = timetableAppointmentDAO.store(timetableAppointmentNew);
		/*
		 * 对排课预约选定的指导老师进行保存
		 */
		TimetableTeacherRelated timetableTeacherRelated = new TimetableTeacherRelated();
		// 获取选择的实验室列表
		List<User> matchTeachers = new ArrayList<User>();
		// 如果matchLabs不为空时
		if (teachers.length > 0) {
			for (int i1 = 0; i1 < teachers.length; i1++) {
				// 将matchLabs添加到matchLabs中
				matchTeachers.add(userDAO.findUserByUsername(teachers[i1]));
				timetableTeacherRelated.setUser(userDAO.findUserByUsername(teachers[i1]));
				timetableTeacherRelated.setTimetableAppointment(timetableAppointmentNew);
				timetableTeacherRelatedDAO.store(timetableTeacherRelated);
				timetableTeacherRelatedDAO.flush();
			}
		}

		/*
		 * 对排课预约选定的实验项目进行保存
		 */
		TimetableItemRelated timetableItemRelated = new TimetableItemRelated();
		// 获取选择的实验项目列表
		List<OperationItem> matchItems = new ArrayList<OperationItem>();
		// 如果matchLabs不为空时
		if (items.length > 0) {
			for (int i1 = 0; i1 < items.length; i1++) {
				// 如果item为空则跳出循环
				if (items[i1] == null || items[i1].equals("")) {
					continue;
				}
				// 去重
				if (i1 > 0 && items[i1].equals(items[i1 - 1])) {
					continue;

				}
				// 将matchLabs添加到matchLabs中
				matchItems.add(operationItemDAO.findOperationItemById(Integer.parseInt(items[i1])));
				timetableItemRelated.setOperationItem(operationItemDAO.findOperationItemById(Integer
						.parseInt(items[i1])));
				timetableItemRelated.setTimetableAppointment(timetableAppointmentNew);
				timetableItemRelatedDAO.store(timetableItemRelated);
				timetableItemRelatedDAO.flush();
			}
		}

		for (int i = 0; i < sWeek.length; i++) {
			for (int j = 0; j < sClasses.length; j++) {
				TimetableAppointmentSameNumber timetableAppointmentSameNumber = new TimetableAppointmentSameNumber();
				timetableAppointmentSameNumber.setCreatedBy(shareService.getUserDetail().getUsername());
				timetableAppointmentSameNumber.setCreatedBy(shareService.getUserDetail().getUsername());
				timetableAppointmentSameNumber.setCreatedDate(Calendar.getInstance());
				timetableAppointmentSameNumber.setUpdatedDate(Calendar.getInstance());

				// 设置调整排课的内容
				if (sWeek[i].indexOf(("-")) == -1) {
					timetableAppointmentSameNumber.setTotalWeeks("1");
					timetableAppointmentSameNumber.setStartWeek(Integer.parseInt(sWeek[i]));
					timetableAppointmentSameNumber.setEndWeek(Integer.parseInt(sWeek[i]));

				} else {
					timetableAppointmentSameNumber
							.setTotalWeeks(String.valueOf((Integer.parseInt(sWeek[i].split("-")[1]) - Integer
									.parseInt(sWeek[i].split("-")[0]))));
					timetableAppointmentSameNumber.setStartWeek(Integer.parseInt(sWeek[i].split("-")[0]));
					timetableAppointmentSameNumber.setEndWeek(Integer.parseInt(sWeek[i].split("-")[1]));
				}

				if (sClasses[j].indexOf(("-")) == -1) {
					timetableAppointmentSameNumber.setTotalClasses(Integer.parseInt(sClasses[j]));
					timetableAppointmentSameNumber.setStartClass(Integer.parseInt(sClasses[j]));
					timetableAppointmentSameNumber.setEndClass(Integer.parseInt(sClasses[j]));
				} else {
					timetableAppointmentSameNumber
							.setTotalClasses((Integer.parseInt(sClasses[j].split("-")[1]) - Integer
									.parseInt(sClasses[j].split("-")[0])));
					timetableAppointmentSameNumber.setStartClass(Integer.parseInt(sClasses[j].split("-")[0]));
					timetableAppointmentSameNumber.setEndClass(Integer.parseInt(sClasses[j].split("-")[1]));
				}
				timetableAppointmentSameNumber.setTimetableAppointment(timetableAppointmentNew);
				timetableAppointmentSameNumberDAO.store(timetableAppointmentSameNumber);
				timetableAppointmentSameNumberDAO.flush();

			}
		}
		timetableLabRelatedDAO.flush();
		List<TimetableLabRelated> timetableLabRelateds = timetableLabRelatedDAO.executeQuery("select c from TimetableLabRelated c where c.timetableAppointment.id="+timetableAppointmentNew.getId(), 0,-1);
		if(timetableAppointmentNew.getDeviceOrLab().equals(1)){
			for(TimetableLabRelated timetableLabRelatedTmp:timetableLabRelateds){
				timetableCourseSchedulingService.saveTimetableLabroomDeviceReservation(timetableLabRelatedTmp, sLabRoomDevice,timetableSelfCourse.getSchoolTerm().getId());

			}
		}else {
			mysqlService.createLabLimitByAppointment(timetableAppointmentNew.getId());
		}
		return timetableAppointmentReturn;
	}

	/*************************************************************************************
	 * @內容：自主分组排课排课完成确定
	 * @作者： 魏誠
	 * @日期：2014-08-5
	 *************************************************************************************/
	public void doSelfGroupTimetableOk(String courseCode, int batchId, int term) {
		// 根据选课组编号，获取排课信息
		List<TimetableAppointment> timetableAppointments = new ArrayList<TimetableAppointment>(
				timetableAppointmentDAO.findTimetableAppointmentByCourseCode(courseCode));
		for (TimetableAppointment timetableAppointment : timetableAppointments) {
			// 设置代发布状态：2
			timetableAppointment.setStatus(2);
			timetableAppointmentDAO.store(timetableAppointment);
		}

		// 如果分组为自动选课，保存学生选课记录
		TimetableBatch timetableBatch = timetableBatchDAO.findTimetableBatchById(batchId);
		timetableBatch.setFlag(1);
		timetableBatchDAO.store(timetableBatch);
		timetableBatchDAO.flush();

		// 自动选课
		if (timetableBatch.getIfselect() == 0) {
			Iterator<TimetableCourseStudent> iterator = null;

			for (TimetableGroup timetableGroup : timetableBatch.getTimetableGroups()) {
				if (timetableGroup.getTimetableAppointments().size() == 0) {
					continue;
				}
				if (iterator == null) {
					iterator = timetableGroup.getTimetableAppointments().iterator().next().getTimetableSelfCourse()
							.getTimetableCourseStudents().iterator();
				}
				for (int i = 1; i <= timetableGroup.getNumbers(); i++) {
					TimetableGroupStudents timetableGroupStudents = new TimetableGroupStudents();
					timetableGroupStudents.setUser(iterator.next().getUser());
					timetableGroupStudents.setTimetableGroup(timetableGroup);
					timetableGroupStudentsDAO.store(timetableGroupStudents);
					timetableGroupStudentsDAO.flush();
				}
			}
		}
	}

}