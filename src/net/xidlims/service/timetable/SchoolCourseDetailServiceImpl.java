package net.xidlims.service.timetable;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import net.xidlims.dao.LabCenterDAO;
import net.xidlims.dao.LabRoomDAO;
import net.xidlims.dao.SchoolCourseDAO;
import net.xidlims.dao.SchoolCourseDetailDAO;
import net.xidlims.dao.SchoolTermDAO;
import net.xidlims.dao.SchoolWeekDAO;
import net.xidlims.dao.TimetableAppointmentDAO;
import net.xidlims.dao.TimetableLabRelatedDAO;
import net.xidlims.dao.TimetableTeacherRelatedDAO;
import net.xidlims.dao.TimetableTutorRelatedDAO;
import net.xidlims.dao.UserDAO;
import net.xidlims.domain.Authority;
import net.xidlims.domain.LabRoom;
import net.xidlims.domain.SchoolCourse;
import net.xidlims.domain.SchoolCourseDetail;
import net.xidlims.domain.SchoolTerm;
import net.xidlims.domain.SchoolWeek;
import net.xidlims.domain.TimetableAppointment;
import net.xidlims.domain.TimetableLabRelated;
import net.xidlims.domain.TimetableTeacherRelated;
import net.xidlims.domain.TimetableTutorRelated;
import net.xidlims.domain.User;
import net.xidlims.service.common.MySQLService;
import net.xidlims.service.common.ShareService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.inject.persist.Transactional;
 
@Service("SchoolCourseDetailService")
public class SchoolCourseDetailServiceImpl implements SchoolCourseDetailService {
	@Autowired
	private SchoolTermDAO schoolTermDAO;
	@Autowired
	private SchoolWeekDAO schoolWeekDAO;
	@Autowired
	private LabRoomDAO labRoomDAO;
	@Autowired
	private ShareService shareService;
	@Autowired
	private TimetableAppointmentDAO courseAppointmentDAO;
	@Autowired
	private SchoolCourseDetailDAO schoolCourseDetailDAO;
	@Autowired
	private SchoolCourseDAO schoolCourseDAO;
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private TimetableAppointmentDAO timetableAppointmentDAO;
	@Autowired
	private TimetableTeacherRelatedDAO timetableTeacherRelatedDAO;
	@Autowired
	private TimetableTutorRelatedDAO timetableTutorRelatedDAO;
	@Autowired
	private TimetableLabRelatedDAO timetableLabRelatedDAO;
	@Autowired
	private LabCenterDAO labCenterDAO;
	@Autowired
	private TimetableCourseSchedulingService timetableCourseSchedulingService;
	@Autowired
	private MySQLService mysqlService;
	
	/*************************************************************************************
	 * @內容：根据时间查找学期
	 * @作者： 魏誠
	 * @日期：2014-07-24
	 *************************************************************************************/
	public List<SchoolTerm> getSchoolTermsByNow() {
		StringBuffer sql = new StringBuffer(
				"select s from SchoolTerm s where 1=1 and (now() <s.termEnd or now() between s.termStart and s.termEnd) order by s.termEnd");
		// 执行sb语句
		List<SchoolTerm> terms = schoolTermDAO.executeQuery(sql.toString());
		return terms;
	}
   
	/*************************************************************************************
	 * @內容：创建一条通过周次和星期分组的语句
	 * @作者： 魏誠
	 * @日期：2014-07-24
	 *************************************************************************************/
	public List<SchoolWeek> getWeeksByNow(int termId) {
		// 创建一条通过周次和星期分组的语句；
		StringBuffer sql = new StringBuffer("select s from SchoolWeek s where 1=1 and s.schoolTerm.id='" + termId
				+ "'group by s.week order by s.week");
		// 执行sb1得到需要的周次；
		List<SchoolWeek> weeks = schoolWeekDAO.executeQuery(sql.toString());
		return weeks;
	}

	/*************************************************************************************
	 * @內容：获取可用的实验室分室
	 * @作者： 魏誠
	 * @日期：2014-07-24
	 ************************************************************************************/
	public Map<Integer, String> getLabRoomMap() {
		Map<Integer, String> labAnnexMap = new HashMap<Integer, String>();
		/*List<LabRoom> list = new ArrayList<LabRoom>(labRoomDAO.executeQuery("select c from LabRoom c "
				+ "where c.isUsed=1 and c.labAnnex.labCenter.schoolAcademy.id = "
				+ shareService.getUserDetail().getSchoolAcademy().getId()));*/
		List<LabRoom> list = new ArrayList<LabRoom>(labRoomDAO.executeQuery("select c from LabRoom c "
				+ "where c.isUsed=1 and c.labAnnex.labCenter.schoolAcademy.academyNumber = "
				+ shareService.getUserDetail().getSchoolAcademy().getAcademyNumber()));

		for (LabRoom labRoom : list) {
			labAnnexMap.put(labRoom.getId(), labRoom.getLabAnnex().getLabCenter() + ";" + labRoom.getLabRoomNumber()
					+ ";" + labRoom.getLabRoomName());
		}
		return labAnnexMap;
	}

	/*************************************************************************************
	 * @內容：根据appointment_no获取实验安排表分组数
	 * @作者： 魏誠
	 * @日期：2014-07-24
	 *************************************************************************************/
	public int getGroupedCourseAppointmentsByAppointNo() {
		// 根据appointment_no分组的sql语句
		StringBuffer sb = new StringBuffer("select distinct c from TimetableAppointment c group by c.appointmentNo");
		// 执行sb1语句
		List<TimetableAppointment> appoints = courseAppointmentDAO.executeQuery(sb.toString());
		return appoints.size();
	}

	/*************************************************************************************
	 * @內容：根据以选课组为单元的获取实验安排表分组
	 * @作者： 魏誠
	 * @日期：2014-07-24
	 *************************************************************************************/
	public List<SchoolCourseDetail> getCourseCodeInSchoolCourseDetail(int term) {
		StringBuffer sql = new StringBuffer(
				"select c from SchoolCourseDetail c where 1=1 and c.schoolAcademy.academyNumber like '"
						+ shareService.getUserDetail().getSchoolAcademy().getAcademyNumber() + "' ");
		if (term != -1) {
			sql.append("and c.schoolTerm.id=" + term
					+ "  group by c.schoolCourse.courseCode,c.user.cname order by c.schoolCourse.courseCode ");
		}
		// 执行sb语句
		List<SchoolCourseDetail> schoolCourses = schoolCourseDetailDAO.executeQuery(sql.toString(), -1, -1);
		return schoolCourses;
	}

	/*************************************************************************************
	 * @內容：根据以选课组为单元的获取实验安排表分组
	 * @作者： 魏誠
	 * @日期：2014-07-24
	 *************************************************************************************/
	public List<SchoolCourse> getCourseCodeInSchoolCourse(int term, int iLabCenter) {

		String academyNumber="";
        // 如果没有获取有效的实验分室列表-根据登录用户的所属学院
        if (iLabCenter != -1) {
    		//获取选择的实验中心
        	academyNumber = shareService.getUserDetail().getSchoolAcademy().getAcademyNumber();
        }else{
        	academyNumber = shareService.getUserDetail().getSchoolAcademy().getAcademyNumber();
        }		StringBuffer sql = new StringBuffer(
				"select c from SchoolCourse c where 1=1 and c.schoolCourseDetails.size>0 and c.schoolAcademy.academyNumber like '"
						+ academyNumber + "' and c.state=1 ");

		if (term != -1) {
			sql.append("and c.schoolTerm.id=" + term + "  order by c.courseCode ");
		}
		// 执行sb语句
		List<SchoolCourse> schoolCourses = schoolCourseDAO.executeQuery(sql.toString(), 0, -1);
		return schoolCourses;
	}

	/*************************************************************************************
	 * @內容：根据学期和状态获取所有的排课课程数量
	 * @作者： 魏誠
	 * @日期：2014-07-24
	 *************************************************************************************/
	public int getCountAllStatusTimetabledCourses(int termId, int statusId) {
		/*return ((Long) schoolCourseDAO.createQuerySingleResult(
				"select count(*) from  SchoolCourse c where 1=1 and c.CTimetableStatus.id='" + statusId
						+ "' and c.schoolTerm.id='" + termId + "'").getSingleResult()).intValue();*/
		return ((Long) schoolCourseDAO.createQuerySingleResult(
				"select count(*) from  SchoolCourse c where 1=1 and c.CDictionaryByCourseStatus.CCategory='c_timetable_status' and c.CDictionaryByCourseStatus.CNumber = '" + statusId
				+ "' and c.schoolTerm.id='" + termId + "'").getSingleResult()).intValue();
	}

	/*************************************************************************************
	 * @內容：根据学期和状态获取已排课的课程列表
	 * @作者： 魏誠
	 * @日期：2014-07-24
	 *************************************************************************************/
	public List<SchoolCourse> getHavedTimetabledCourses(int termId, int statusId, int curr, int size) {
		// 获取等待发布排课的课程的sql语句
		/*StringBuffer sb1 = new StringBuffer("select c from SchoolCourse c where 1=1 and c.CTimetableStatus.id='"
				+ statusId + "' and c.schoolTerm.id='" + termId + "'");*/
		StringBuffer sb1 = new StringBuffer("select c from SchoolCourse c where 1=1 and c.CDictionaryByCourseStatus.CCategory='c_timetable_status' and c.CDictionaryByCourseStatus.CNumber = '"
				+ statusId + "' and c.schoolTerm.id='" + termId + "'");
		// 执行sb语句
		List<SchoolCourse> courses = schoolCourseDAO.executeQuery(sb1.toString(), curr * size, size);
		return courses;
	}

	/*************************************************************************************
	 * @內容：根据coursedetail获取实验室
	 * @作者： 魏誠
	 * @日期：2014-07-24
	 *************************************************************************************/
	public Set<LabRoom> getLabsByCourse(SchoolCourseDetail courseDetail) {
		SchoolTerm schoolTerm = courseDetail.getSchoolTerm();
		// 获取schoolTerm的id
		int termId = schoolTerm.getId();
		// 新建一个LabAnnex的list集合
		Set<LabRoom> reflectSet = new LinkedHashSet<LabRoom>();

		// 获取courseDetail的courseAppointments
		Set<TimetableAppointment> courseAppointments = courseDetail.getTimetableAppointments();
		// 如果courseAppointments等于0时则执行以下代码
		if (courseAppointments.size() == 0) {
			// 获取courseAppointments的weekday
			int weekday = courseDetail.getWeekday();
			// 获取节次
			// int courseClass = courseDetail.getClass();
			// 获取courseArrange的week
			// int week = courseArrange.getArrangedWeek();
			// 创建一条按courseName模糊搜索的语句；
			String query = "and cp.SchoolCourseDetail.schoolTerm.id='" + termId + "' and cp.courseArrange.weekday='"
					+ weekday + "'";
			// + "'and cp.courseArrange.arrangeClass='" + courseClass
			// + "'and cp.courseArrange.arrangedWeek='" + week + "'";
			// 创建一条联合查询的语句；
			StringBuffer sb = new StringBuffer(
					"select distinct l from LabRoom l inner join l.TimetableAppointments cp where 1=1");
			// 将query追加到sb后面
			sb.append(query);
			// 执行sb1语句
			List<LabRoom> labRooms = labRoomDAO.executeQuery(sb.toString());
			// 如果labAnnexs不为空
			if (labRooms.size() > 0) {
				// 在reflectSet添加labAnnexs
				reflectSet.addAll(labRooms);
			}
		}
		return reflectSet;
	}

	/*************************************************************************************
	 * @內容：根据coursedetail获取实验室
	 * @作者： 魏誠
	 * @日期：2014-07-24
	 *************************************************************************************/
	public List<SchoolCourseDetail> getNeedTimetabledAppointments(List<SchoolCourseDetail> details) {
		// 新建CourseDetail的List对象
		List<SchoolCourseDetail> courseDetails = new ArrayList<SchoolCourseDetail>();
		// 循环details
		for (SchoolCourseDetail courseDetail : details) {
			// 获取courseDetail的id
			int id = courseDetail.getId();
			// 获取courseDetail的courseDetailNo
			String courseDetailNo = courseDetail.getCourseDetailNo();
			// 创建一条通过courseNumber查询的语句；
			StringBuffer sb = new StringBuffer(
					"select cp from TimetableAppointment cp where 1=1 and cp.courseDetail.id='" + id
							+ "'group by cp.appointmentNo");
			// 执行sb语句
			List<TimetableAppointment> courseAppointments1 = courseAppointmentDAO.executeQuery(sb.toString());
			// 循环courseAppointments1
			for (TimetableAppointment courseAppointment : courseAppointments1) {
				// 新建一个courseDetail的对象
				SchoolCourseDetail courseDetail1 = new SchoolCourseDetail();
				// 设置courseDetail1的courseDetailNo
				courseDetail1.setCourseDetailNo(courseDetailNo);
				// courseAppointment的appointmentNo
				String appointmentNo = courseAppointment.getAppointmentNo();
				// 获取courseAppointment的实验室名称
				String labName = "";
				// 创建一条通过appointmentNo查询的语句；
				StringBuffer appoint = new StringBuffer(
						"select distinct l from LabRoom l inner join l.courseAppointments cp where 1=1 and cp.appointmentNo='"
								+ appointmentNo + "'");
				// 执行sb语句
				List<LabRoom> labRooms = labRoomDAO.executeQuery(appoint.toString());
				// 循环labAnnexs
				for (LabRoom labRoom : labRooms) {
					// 获取labAnnex的labName
					String labName1 = labRoom.getLabRoomName();
					// 如果arrangeNumber不等于空
					if (labName != "") {
						labName = labName + "," + String.valueOf(labName1);
					}
					// 否则arrangeNumber赋值成arrangeId的string
					else {
						labName = String.valueOf(labName1);
					}
				}
				// 设置courseDetail的courseNumber
				courseDetail1.setCourseNumber(labName);
			}
		}
		return courseDetails;
	}

	/*************************************************************************************
	 * @throws ParseException 
	 * @內容：进行教务排课，根据SchoolCourseDetail
	 * @作者： 魏誠
	 * @日期：2014-07-24
	 *************************************************************************************/
	@Transactional
	public void doDirectTimetable(String courseCode, String[] labs, String[] tutorRelateds, String[] teacherRelateds,String[] sLabRoomDevice) throws ParseException {
		// 初始化排课预约对象
		TimetableAppointment timetableAppointment = new TimetableAppointment();
		List<SchoolCourseDetail> schoolCourseDetails = schoolCourseDetailDAO
				.executeQuery("select c from SchoolCourseDetail c where c.schoolCourse.courseCode like '" + courseCode
						+ "'");
		/**
		 * 根据选课组courseCode遍历SchoolCourseDetail，将相同选课组的教务数据直接推送到排课表
		 **/
		for (SchoolCourseDetail schoolCourseDetail : schoolCourseDetails) {
			int appoints = this.getGroupedCourseAppointmentsByAppointNo() + 1;
			timetableAppointment.setAppointmentNo(schoolCourseDetail.getCourseDetailNo());
			timetableAppointment.setTimetableNumber(appoints);
			timetableAppointment.setCreatedBy(shareService.getUserDetail().getUsername());
			timetableAppointment.setCreatedBy(shareService.getUserDetail().getUsername());
			timetableAppointment.setCreatedDate(Calendar.getInstance());
			timetableAppointment.setUpdatedDate(Calendar.getInstance());
			timetableAppointment.setSchoolCourseDetail(schoolCourseDetail);
			// 设置发布状态为待发布
			timetableAppointment.setStatus(2);
			// 设置排课方式
			timetableAppointment.setTimetableStyle(1);
			// 设置选课组编号
			timetableAppointment.setCourseCode(schoolCourseDetail.getSchoolCourse().getCourseCode());
			// 设置调整排课的内容
			timetableAppointment.setTotalWeeks(schoolCourseDetail.getTotalWeeks());
			timetableAppointment.setWeekday(schoolCourseDetail.getWeekday());
			timetableAppointment.setStartWeek(schoolCourseDetail.getStartWeek());
			timetableAppointment.setEndWeek(schoolCourseDetail.getEndWeek());
			timetableAppointment.setTotalClasses(schoolCourseDetail.getTotalClasses());
			timetableAppointment.setStartClass(schoolCourseDetail.getStartClass());
			timetableAppointment.setEndClass(schoolCourseDetail.getEndClass());
			timetableAppointment.setSchoolCourse(schoolCourseDetail.getSchoolCourse());
			timetableAppointment.setSchoolCourseInfo((schoolCourseDetail.getSchoolCourse().getSchoolCourseInfo()));
			timetableAppointment.setPreparation("");
			timetableAppointment.setGroups(-1);
			timetableAppointment.setLabhours(-1);
			timetableAppointment.setGroupCount(-1);
			timetableAppointment.setConsumablesCosts(new BigDecimal(-1));

			// ttimetableAppointment.setStatus(status);
			// 保存排课预约表
			TimetableAppointment timetableAppointmentSaved = timetableAppointmentDAO.store(timetableAppointment);
			// 设置预约编号
			// String appointNo = String.valueOf(appoints);
			// 获取选择的实验室列表
			List<LabRoom> matchLabs = new ArrayList<LabRoom>();
			/*
			 * 对排课预约选定的实验分室进行保存
			 */
			TimetableLabRelated timetableLabRelated = new TimetableLabRelated();

			int countLabRoomDevice = 0; // 计算所选实验室总共有多少设备 
			// 如果matchLabs不为空时
			if (labs.length > 0) {
				for (int i = 0; i < labs.length; i++) {
					// 将matchLabs添加到matchLabs中
					LabRoom labRoom = labRoomDAO.findLabRoomById(Integer.parseInt(labs[i]));
					matchLabs.add(labRoom);
					timetableLabRelated.setLabRoom(labRoom);
					
					// 实验室设备数量累加
					if (labRoom.getLabRoomDevices()!=null && labRoom.getLabRoomDevices().size()>0) {
						countLabRoomDevice+=labRoom.getLabRoomDevices().size();
					}
					
					timetableLabRelated.setTimetableAppointment(timetableAppointmentSaved);
					timetableLabRelatedDAO.store(timetableLabRelated);
					timetableLabRelatedDAO.flush();
				}
			}
			
			// 设置此次排课的针对对象（1 设备  2 实验室）
			if ((sLabRoomDevice!=null && countLabRoomDevice == sLabRoomDevice.length)  
					|| sLabRoomDevice==null) {// i.全选情况--纺织学院   ii.其他学院sLabRoomDevice字段为空
				timetableAppointmentSaved.setDeviceOrLab(2);// 此次排课针对实验室
			}else {
				timetableAppointmentSaved.setDeviceOrLab(1);// 此次排课针对设备
			}
			timetableAppointmentSaved = timetableAppointmentDAO.store(timetableAppointmentSaved);
			
			/*
			 * 对排课预约选定的指导老师进行保存
			 */
			TimetableTeacherRelated timetableTeacherRelated = new TimetableTeacherRelated();
			// 获取选择的实验室列表
			List<User> matchTeachers = new ArrayList<User>();
			// 如果matchLabs不为空时
			if (teacherRelateds.length > 0) {
				for (int i = 0; i < teacherRelateds.length; i++) {
					// 将matchLabs添加到matchLabs中
					matchTeachers.add(userDAO.findUserByUsername(teacherRelateds[i]));
					timetableTeacherRelated.setUser(userDAO.findUserByUsername(teacherRelateds[i]));
					timetableTeacherRelated.setTimetableAppointment(timetableAppointmentSaved);
					timetableTeacherRelatedDAO.store(timetableTeacherRelated);
				}
			}

			/*
			 * 对排课预约选定的授课老师进行保存
			 */
			TimetableTutorRelated timetableTutorRelated = new TimetableTutorRelated();
			// 获取选择的实验室列表
			List<User> matchTutors = new ArrayList<User>();
			// 如果matchLabs不为空时，需要先判空，后判长度
			if (tutorRelateds != null && tutorRelateds.length > 0) {
				for (int i = 0; i < tutorRelateds.length; i++) {
					// 将matchLabs添加到matchLabs中
					matchTutors.add(userDAO.findUserByUsername(tutorRelateds[i]));
					timetableTutorRelated.setUser(userDAO.findUserByUsername(tutorRelateds[i]));
					timetableTutorRelated.setTimetableAppointment(timetableAppointmentSaved);
					timetableTutorRelatedDAO.store(timetableTutorRelated);
				}
			}
			List<TimetableLabRelated> timetableLabRelateds = timetableLabRelatedDAO.executeQuery("select c from TimetableLabRelated c where c.timetableAppointment.id="+timetableAppointmentSaved.getId(), 0,-1);
			if(timetableAppointmentSaved.getDeviceOrLab().equals(1)){
				for(TimetableLabRelated timetableLabRelatedTmp:timetableLabRelateds){
					timetableCourseSchedulingService.saveTimetableLabroomDeviceReservation(timetableLabRelatedTmp, sLabRoomDevice,schoolCourseDetail.getSchoolTerm().getId());
				}
			}else {
				mysqlService.createLabLimitByDirectAppointment(timetableAppointmentSaved.getId());
			}
			
		}

	}

	/*************************************************************************************
	 * @內容：进行termid,获取教务CourseDetail的分页列表信息（教务排课的入口显示页面）
	 * @作者： 魏誠
	 * @日期：2014-07-24
	 *************************************************************************************/
	public List<SchoolCourseDetail> getCourseDetailsByQuery(int termId, SchoolCourseDetail schoolCourseDetail,
			int curr, int size, int iLabCenter) {
		String query = "";
		// 根据权限显示不同的内容：管理员查看所有，老师查看本人的 内容
		// if(shareService.getUserDetail().getAuthority().getAuthorityName().equals("ADMIN")){
		// query = " and c.user.schoolAcademy.academyNumber like '"
		// + shareService.getUserDetail().getSchoolAcademy().getAcademyNumber()
		// +
		// "'  order by c.schoolCourse.schoolCourseInfo.courseNumber,c.schoolCourse.courseCode  desc ";
		// }else{
		// query = " and c.user.username like '"
		// + shareService.getUserDetail().getUsername() +
		// "'  order by c.schoolCourse.courseCode desc";
		// }
		// + shareService.getUserDetail().getSchoolAcademy().getAcademyNumber()
		// +
		// "'  order by c.schoolCourse.schoolCourseInfo.courseNumber,c.schoolCourse.courseCode  desc ";
		if (schoolCourseDetail.getSchoolCourse() != null && schoolCourseDetail.getSchoolCourse().getCourseNo() != null) {
			query = query + " and c.schoolCourse.courseNo like '%" + schoolCourseDetail.getSchoolCourse().getCourseNo()
					+ "%'";
		}

		String academyNumber = "";
		// 如果没有获取有效的实验分室列表-根据登录用户的所属学院
		if (iLabCenter != -1) {
			// 获取选择的实验中心
			academyNumber = shareService.getUserDetail().getSchoolAcademy().getAcademyNumber();
		} else {
			academyNumber = shareService.getUserDetail().getSchoolAcademy().getAcademyNumber();
		}
		// 如果没有获取有效的实验分室列表-根据登录用户的所属学院
		StringBuffer sql = new StringBuffer("select c from SchoolCourseDetail c where  c.schoolTerm.id=" + termId
					+ "   and c.state=1");
		/**
         * 判断是否只是老师角色
        **/     
        User user = shareService.getUserDetail();
        String judge = ",";
        for(Authority authority:user.getAuthorities()){
            judge = judge + "," + authority.getId() + "," ;
        }
        boolean isSuper = false;
        //如果权限仅为教师，不为实验中心主任或超级管理员，则增加过滤
        if(judge.indexOf(",11,")>-1){
            isSuper = true;
        }
        
        // 如果没有获取有效的实验分室列表-根据登录用户的所属学院
        if (iLabCenter != -1) {
            //获取选择的实验中心
            academyNumber = shareService.getUserDetail().getSchoolAcademy().getAcademyNumber();
        }else{
            academyNumber = shareService.getUserDetail().getSchoolAcademy().getAcademyNumber();
        }
        
        if (!isSuper) {// 如果不是管理员，加学院筛选条件
            sql.append(" and c.schoolAcademy.academyNumber like '%" + academyNumber+"%'");
        }
		
		// 将query添加到sb1后
		sql.append(query);

		// 如果获取所有数据，则对选课组进行groupby
		if (curr == -1 && size == -1) {
			sql.append(" group by c.schoolCourse.courseCode,c.user.username");
		}

		sql.append(" order by c.schoolCourse.schoolCourseInfo.courseNumber,c.schoolCourse.courseCode desc");
		// 执行sb语句
		List<SchoolCourseDetail> courses = schoolCourseDetailDAO.executeQuery(sql.toString(), curr * size, size);
		return courses;
	}
	
	/*************************************************************************************
	 * @內容：实验室排课管理-排课-list(获取所有教务推送的排课和未安排时间的排课)
	 * @作者： 罗璇
	 * @日期：2017年3月8日
	 *************************************************************************************/
	public List<SchoolCourseDetail> getCourseDetailsAllByQuery(int termId, SchoolCourseDetail schoolCourseDetail,
			int curr, int size,int iLabCenter,HttpServletRequest request){

		/**
         * 判断是否只是老师角色
        **/     
        User user = shareService.getUserDetail();
        String judge = ",";
        for(Authority authority:user.getAuthorities()){
            judge = judge + "," + authority.getId() + "," ;
        }
        boolean isSuper = false;
        //如果权限仅为教师，不为实验中心主任或超级管理员，则增加过滤
        if(judge.indexOf(",11,")>-1){
            isSuper = true;
        }
        
		
		String query = "";
		if (schoolCourseDetail.getSchoolCourse() != null && !schoolCourseDetail.getSchoolCourse().getCourseNo().equals("-1")) {
			query = query + " and c.schoolCourse.courseNo like '%" + schoolCourseDetail.getSchoolCourse().getCourseNo()
					+ "%'";
		}

		StringBuffer sql = new StringBuffer();
		// 管理员可以看到所有排课
		if (isSuper){
			sql = new StringBuffer("select c from SchoolCourseDetail c where  c.schoolTerm.id=" + termId
					+ " and c.state=1 ");
		}else{
			sql = new StringBuffer("select c from SchoolCourseDetail c join c.users cu where  c.schoolTerm.id=" + termId
					+ " and c.state=1 ");
		}
		
		if(request.getParameter("courseNumber")!=null&&!request.getParameter("courseNumber").equals("")){
			sql.append(" and c.courseNumber like '"+request.getParameter("courseNumber")+"'");
		}

		if (!isSuper) {// 如果不是管理员，加老师筛选条件
            sql.append(" and cu.username = '" + shareService.getUser().getUsername() + "' ");
        }
		
		// 将query添加到sb1后
		sql.append(query);
		
		//已经有时间安排的课程不予呈现(以开始周次作为判断依据)
		sql.append(" and c.startWeek is null ");
		
		sql.append(" order by c.schoolCourse.schoolCourseInfo.courseNumber,c.schoolCourse.courseCode desc");
		// 执行sb语句
		List<SchoolCourseDetail> courses = schoolCourseDetailDAO.executeQuery(sql.toString(), curr * size, size);
		return courses;
	}

	/*************************************************************************************
	 * @內容：进行termid,获取计数获取教务CourseDetail的分页列表信息（教务排课的入口显示页面）
	 * @作者： 魏誠
	 * @日期：2014-07-24
	 *************************************************************************************/
	public int getCountCourseDetailsByQuery(int termId, SchoolCourseDetail schoolCourseDetail, int iLabCenter) {
		String query = "";
		// 根据权限显示不同的内容：管理员查看所有，老师查看本人的 内容
		// if(shareService.getUserDetail().getAuthority().getAuthorityName().equals("ADMIN")){
		// query = " and c.user.schoolAcademy.academyNumber like '"
		// + shareService.getUserDetail().getSchoolAcademy().getAcademyNumber()
		// + "'  order by c.schoolCourse.schoolCourseInfo.courseNumber desc ";
		// }else{
		// query = " and c.user.username like '"
		// + shareService.getUserDetail().getUsername() +
		// "'  order by c.schoolCourse.schoolCourseInfo.courseNumber desc";
		// }

		// 如果查询条件为所有，者id为-1，否则增加查询条件
		if (schoolCourseDetail.getSchoolCourse() != null && schoolCourseDetail.getSchoolCourse().getCourseNo() != null) {
			query = query + " and c.schoolCourse.courseNo like '%" + schoolCourseDetail.getSchoolCourse().getCourseNo()
					+ "%' ";
		}
		
		//如果没有选择实验中心，则按登录人所属学院确定中心
		StringBuffer sql = new StringBuffer();
		
		
		// 创建根据学期来查询课程；
		sql.append("select count(*)  from SchoolCourseDetail c where c.schoolTerm.id=" + termId
				+ " and c.endClass>0  and c.state=1");
		
		/**
         * 判断是否只是老师角色
        **/     
        User user = shareService.getUserDetail();
        String judge = ",";
        for(Authority authority:user.getAuthorities()){
            judge = judge + "," + authority.getId() + "," ;
        }
        boolean isSuper = false;
        //如果权限仅为教师，不为实验中心主任或超级管理员，则增加过滤
        if(judge.indexOf(",11,")>-1){
            isSuper = true;
        }
        
		if (!isSuper) {// 如果不是管理员，加学院筛选条件
			sql.append(" and c.schoolAcademy.academyNumber like '" + shareService.getUserDetail().getSchoolAcademy().getAcademyNumber()+"'");
		}
		
		// 将query添加到sb1后
		sql.append(query);
		return ((Long) schoolCourseDetailDAO.createQuerySingleResult(sql.toString()).getSingleResult()).intValue();
	}
	
	/*************************************************************************************
	 * @內容：实验室排课管理-排课-list-count(获取所有教务推送的排课和未安排时间的排课)
	 * @作者： 罗璇
	 * @日期：2017年3月8日
	 *************************************************************************************/
	public int getCountCourseDetailsAllByQuery(int termId, SchoolCourseDetail schoolCourseDetail,int iLabCenter,HttpServletRequest request){

		/**
         * 判断是否只是老师角色
        **/     
        User user = shareService.getUserDetail();
        String judge = ",";
        for(Authority authority:user.getAuthorities()){
            judge = judge + "," + authority.getId() + "," ;
        }
        boolean isSuper = false;
        //如果权限仅为教师，不为实验中心主任或超级管理员，则增加过滤
        if(judge.indexOf(",11,")>-1){
            isSuper = true;
        }
        
		
		String query = "";

		// 如果查询条件为所有，者id为-1，否则增加查询条件
		if (schoolCourseDetail.getSchoolCourse() != null && !schoolCourseDetail.getSchoolCourse().getCourseNo().equals("-1")) {
			query = query + " and c.schoolCourse.courseNo like '%" + schoolCourseDetail.getSchoolCourse().getCourseNo()
					+ "%' ";
		}
		
		//如果没有选择实验中心，则按登录人所属学院确定中心
		StringBuffer sql = new StringBuffer();
		
		
		// 创建根据学期来查询课程；管理员可以看到所有排课
		if(isSuper){
			sql.append("select count(*)  from SchoolCourseDetail c  where c.schoolTerm.id=" + termId
					+ " and c.state=1 ");
		}else{
			sql.append("select count(*)  from SchoolCourseDetail c join c.users cu where c.schoolTerm.id=" + termId
					+ " and c.state=1 ");
		}
		
		if(request.getParameter("courseNumber")!=null&&!request.getParameter("courseNumber").equals("")){
			sql.append(" and c.courseNumber like '"+request.getParameter("courseNumber")+"'");
		}
		
		if (!isSuper) {// 如果不是管理员，加老师筛选条件
			sql.append(" and cu.username = '" + shareService.getUser().getUsername() + "' ");
		}
		
		// 将query添加到sb1后
		sql.append(query);
		
		//已经有时间安排的课程不予呈现(以开始周次作为判断依据)
		sql.append(" and c.startWeek is null ");
		
		return ((Long) schoolCourseDetailDAO.createQuerySingleResult(sql.toString()).getSingleResult()).intValue();
	
	}

	public int getValidWeek(int weekday, int week, String courseCode) {
		String sql = "select c from SchoolCourseDetail c where c.schoolCourse.courseCode like '" + courseCode
				+ "' and c.weekday =" + weekday;

		String sql1 = "select c from TimetableAppointment c where c.schoolCourseDetail.schoolCourse.courseCode like '"
				+ courseCode + "' and c.weekday =" + weekday;
		List<TimetableAppointment> timetableAppointmentList = timetableAppointmentDAO.executeQuery(sql1);
		String[] commonWeek;

		String tempClass = "";
		for (TimetableAppointment timetableAppointment : timetableAppointmentList) {
			if (timetableAppointment.getStartWeek() <= week && timetableAppointment.getEndWeek() >= week) {
				for (int i = timetableAppointment.getStartClass(); i <= timetableAppointment.getEndClass(); i++) {
					tempClass = tempClass + i + ";";

				}
			}
		}
		commonWeek = tempClass.split(";");
		// 如果可用的周次为0则返回
		if (commonWeek[0].equals("")) {
			return -1;
		}

		Arrays.sort(commonWeek);
		String sTempClass = "";
		String[] sCommonWeek;
		// 遍历实验分室
		for (SchoolCourseDetail schoolCourseDetail : schoolCourseDetailDAO.executeQuery(sql)) {
			if (schoolCourseDetail.getStartWeek() <= week && schoolCourseDetail.getEndWeek() >= week) {
				for (int i = schoolCourseDetail.getStartClass(); i <= schoolCourseDetail.getEndClass(); i++) {
					sTempClass = sTempClass + i + ";";

				}
			}
		}
		sCommonWeek = sTempClass.split(";");
		Arrays.sort(sCommonWeek);

		if (sCommonWeek.length == commonWeek.length
				&& Integer.parseInt(sCommonWeek[0]) == Integer.parseInt(commonWeek[0])
				&& Integer.parseInt(sCommonWeek[commonWeek.length - 1]) == Integer
						.parseInt(commonWeek[commonWeek.length - 1])) {
			return 1;
		} else {
			return -1;
		}
	}

	/*************************************************************************************
	 * @內容：获取可用的周次教务排课信息json
	 * @作者： 魏誠
	 * @日期：2014-08-4
	 *************************************************************************************/
	public String getValidWeek(int weekday, String[] labrooms, String courseCode) {
		String sql = "select c from SchoolCourseDetail c where c.schoolCourse.courseCode like '" + courseCode
				+ "' and c.weekday =" + weekday;

		String jsonWeek = "[";
		// 遍历实验分室
		for (SchoolCourseDetail schoolCourseDetail : schoolCourseDetailDAO.executeQuery(sql)) {
			for (int i = schoolCourseDetail.getStartWeek(); i <= schoolCourseDetail.getEndWeek(); i++) {
				if (this.getValidWeek(weekday, i, courseCode) == 1) {
					continue;
				}
				if (i == schoolCourseDetail.getEndWeek()) {
					jsonWeek = jsonWeek + "{\"id\":\"" + i + "\",\"value\":\"" + i + "\"}";

				} else {
					jsonWeek = jsonWeek + "{\"id\":\"" + i + "\",\"value\":\"" + i + "\"},";

				}
			}
			jsonWeek = jsonWeek + "]";
			break;
		}
		return jsonWeek;

	}

	/*************************************************************************************
	 * @內容：获取可用的周次教务排课信息map
	 * @作者： 魏誠
	 * @日期：2014-08-4
	 *************************************************************************************/
	public Map<Integer, Integer> getValidWeekMap(int weekday, String[] labrooms, String courseCode) {
		Map<Integer, Integer> validWeekMap = new HashMap<Integer, Integer>();
		String sql = "select c from SchoolCourseDetail c where c.schoolCourse.courseCode like '" + courseCode
				+ "' and c.weekday =" + weekday;
		// 遍历实验分室
		for (SchoolCourseDetail schoolCourseDetail : schoolCourseDetailDAO.executeQuery(sql)) {
			for (int i = schoolCourseDetail.getStartWeek(); i <= schoolCourseDetail.getEndWeek(); i++) {
				if (this.getValidWeek(weekday, i, courseCode) == 1) {
					continue;
				}
				validWeekMap.put(i, i);
			}
		}
		return validWeekMap;

	}

	/*************************************************************************************
	 * @內容：根据选课组编号获取教务排课信息
	 * @作者： 魏誠
	 * @日期：2014-08-4
	 *************************************************************************************/
	public List<SchoolCourseDetail> getSchoolCourseDetailByCourseCode(String courseCode) {
		// 根据选课组编号获取教务排课信息
		String sql = "select c from SchoolCourseDetail c where c.schoolCourse.courseCode like '" + courseCode
				+ "' and c.state=1 " ;
		return schoolCourseDetailDAO.executeQuery(sql.toString());
	}

	/*************************************************************************************
	 * @ 根据课程编号联动获取选课组信息
	 * @作者： 张凯
	 * @日期：2017-03-16
	 *************************************************************************************/
	@Override
	public Map<String, String> getSchoolCourseByCourseNumber(String courseNumber,Integer termId) {
		String sql = " select c from SchoolCourse c where c.schoolTerm.id ="+termId;
		if(courseNumber!=null&&!courseNumber.equals("")){
			sql += "and c.schoolCourseInfo.courseNumber like '"+courseNumber+"'";
		}
		List<SchoolCourse> schoolCourses = schoolCourseDAO.executeQuery(sql,0,-1);
		Map<String,String> map = new HashMap<String, String>();
		String SchoolCourseJson = "<option value= >---所有选课组---</option>";
		if(schoolCourses!=null&&schoolCourses.size()>0){
			for(SchoolCourse s:schoolCourses){
				if(s.getUserByTeacher()!=null&&s.getUserByTeacher().getCname()!=null){
					SchoolCourseJson += "<option value='"+s.getCourseNo()+"'>"+s.getSchoolCourseInfo().getCourseNumber()+","+s.getUserByTeacher().getCname()+"</option>";
				}else{
					SchoolCourseJson += "<option value='"+s.getCourseNo()+"'>"+s.getSchoolCourseInfo().getCourseNumber()+"</option>";
				}
			}
		}
		SchoolCourseJson = shareService.htmlEncode(SchoolCourseJson);
		map.put("SchoolCourseJson", SchoolCourseJson);
		return map;
	}
}