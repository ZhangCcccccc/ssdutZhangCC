package net.xidlims.service.timetable;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import net.xidlims.constant.MonthReport;
import net.xidlims.dao.LabCenterDAO;
import net.xidlims.dao.LabRoomDAO;
import net.xidlims.dao.LabRoomDeviceReservationDAO;
import net.xidlims.dao.OperationItemDAO;
import net.xidlims.dao.SchoolCourseDAO;
import net.xidlims.dao.SchoolCourseDetailDAO;
import net.xidlims.dao.TimetableAppointmentDAO;
import net.xidlims.dao.TimetableAppointmentSameNumberDAO;
import net.xidlims.dao.TimetableBatchDAO;
import net.xidlims.dao.TimetableGroupDAO;
import net.xidlims.dao.TimetableGroupStudentsDAO;
import net.xidlims.dao.TimetableItemRelatedDAO;
import net.xidlims.dao.TimetableLabRelatedDAO;
import net.xidlims.dao.TimetableTeacherRelatedDAO;
import net.xidlims.dao.TimetableTutorRelatedDAO;
import net.xidlims.dao.UserDAO;
import net.xidlims.domain.Authority;
import net.xidlims.domain.LabCenter;
import net.xidlims.domain.LabRoom;
import net.xidlims.domain.LabRoomDeviceReservation;
import net.xidlims.domain.OperationItem;
import net.xidlims.domain.SchoolAcademy;
import net.xidlims.domain.SchoolCourseDetail;
import net.xidlims.domain.SchoolCourseStudent;
import net.xidlims.domain.TimetableAppointment;
import net.xidlims.domain.TimetableAppointmentSameNumber;
import net.xidlims.domain.TimetableBatch;
import net.xidlims.domain.TimetableCourseStudent;
import net.xidlims.domain.TimetableGroup;
import net.xidlims.domain.TimetableGroupStudents;
import net.xidlims.domain.TimetableItemRelated;
import net.xidlims.domain.TimetableLabRelated;
import net.xidlims.domain.TimetableLabRelatedDevice;
import net.xidlims.domain.TimetableTeacherRelated;
import net.xidlims.domain.TimetableTutorRelated;
import net.xidlims.domain.User;
import net.xidlims.service.common.MySQLService;
import net.xidlims.service.common.ShareService;
import net.xidlims.service.system.SchoolWeekService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("TimetableAppointmentService")
public class TimetableAppointmentServiceImpl implements TimetableAppointmentService {

	@Autowired
	private TimetableAppointmentDAO timetableAppointmentDAO;
	@Autowired
	private TimetableLabRelatedDAO timetableLabRelatedDAO;
	@Autowired
	private SchoolCourseDetailDAO schoolCourseDetailDAO;
	@Autowired
	private ShareService shareService;
	@Autowired
	private LabRoomDAO labRoomDAO;
	@Autowired
	private OperationItemDAO operationItemDAO;
	@Autowired
	private TimetableItemRelatedDAO timetableItemRelatedDAO;
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private TimetableTeacherRelatedDAO timetableTeacherRelatedDAO;
	@Autowired
	private TimetableTutorRelatedDAO timetableTutorRelatedDAO;
	@Autowired
	private TimetableGroupDAO timetableGroupDAO;
	@Autowired
	private TimetableGroupStudentsDAO timetableGroupStudentsDAO;
	@Autowired
	private TimetableBatchDAO timetableBatchDAO;
	@Autowired
	private TimetableAppointmentSameNumberDAO timetableAppointmentSameNumberDAO;
	@Autowired
	private LabCenterDAO labCenterDAO;
	@Autowired
	private LabRoomDeviceReservationDAO labRoomDeviceReservationDAO;
	@Autowired
	private TimetableCourseSchedulingService timetableCourseSchedulingService;
	@Autowired
	private MySQLService mysqlService;
	@Autowired
	private SchoolWeekService schoolWeekService;
	
	/*************************************************************************************
	 * @內容：查看所有的预约的列表安排
	 * @作者： 魏誠
	 * @日期：2014-07-24
	 *************************************************************************************/
	public List<TimetableAppointment> getTimetableAppointmentsByQuery(int termId,
			TimetableAppointment timetableAppointment, int status, int curr, int size, int iLabCenter) {
		String academyNumber="";
        // 如果没有获取有效的实验分室列表-根据登录用户的所属学院
        /*if (iLabCenter != -1) {
    		//获取选择的实验中心
        	LabCenter l=labCenterDAO.findLabCenterById(iLabCenter);
        	academyNumber = l.getSchoolAcademy().getAcademyNumber();
        }else{
        	academyNumber = shareService.getUserDetail().getSchoolAcademy().getAcademyNumber();
        }*/
		// 创建根据学期来查询课程；
		StringBuffer sql = new StringBuffer(
				"select distinct c from TimetableAppointment  c ,TimetableTeacherRelated d where 1=1 and d.timetableAppointment.id = c.id ");
		if (status != -1) {
			sql.append(" and c.status = " + status);
		}

		if (timetableAppointment!=null&&timetableAppointment.getId()!=null&&timetableAppointment.getId() != -1 ) {
			if(timetableAppointment.getSchoolCourseInfo() != null&& timetableAppointment.getSchoolCourseInfo().getCourseNumber() != null){
			sql.append(" and c.schoolCourseInfo.courseNumber like '"
					+ timetableAppointment.getSchoolCourseInfo().getCourseNumber() + "' ");
			}
			}
		if(timetableAppointment!=null&&timetableAppointment.getDetail() != null){
			sql.append(" and d.user.username like '"+ timetableAppointment.getDetail() + "'");
		}
		/**
		 * 判断是否只是老师角色
		 **/
		User user = shareService.getUserDetail();
		String judge = ",";
		for (Authority authority : user.getAuthorities()) {
			judge = judge + "," + authority.getId() + ",";
		}

		if (timetableAppointment!=null&&timetableAppointment.getSchoolCourseInfo() != null
				&& !timetableAppointment.getSchoolCourseInfo().getCourseNumber().equals("-1")) {
			sql.append(" and c.schoolCourseInfo.courseNumber like '%"
					+ timetableAppointment.getSchoolCourseInfo().getCourseNumber() + "%'");
		}

		// 按照课程排序
		sql.append(" and (c.schoolCourseInfo.academyNumber like '%" + academyNumber
				+ "%') ");

		// 自主排课
		String sqlSelf = sql.toString();
		// 如果权限仅为教师，不为实验中心主任或超级管理员，则增加过滤
		if (judge.indexOf(",2,") != -1 && judge.indexOf(",11,") == -1 && judge.indexOf(",4,") == -1) {
			sqlSelf = sqlSelf + " and (c.timetableSelfCourse.user.username like '" + user.getUsername() + "'"
					+" or d.user.username like '"+ user.getUsername() + "')";
		}
		sqlSelf = sqlSelf + " and (c.timetableStyle!=1 and c.timetableStyle!=2 and c.timetableStyle!=3 and c.timetableStyle!=4 and c.timetableSelfCourse.schoolTerm ="
				+ termId + ") order by c.courseCode ,c.weekday,c.timetableNumber desc";
		List<TimetableAppointment> timetableAppointmentSelfs = timetableAppointmentDAO.executeQuery(sqlSelf, curr
				* size, size);

		// 教务排课
		String sqlAcademy = sql.toString();
		// 如果权限仅为教师，不为实验中心主任或超级管理员，则增加过滤
		if (judge.indexOf(",2,") != -1 && judge.indexOf(",11,") == -1 && judge.indexOf(",4,") == -1) {
			sqlAcademy = sqlAcademy + " and (c.schoolCourseDetail.user.username like '" + user.getUsername() + "'"
					+" or d.user.username like '"+ user.getUsername() + "')";
		}
		sqlAcademy = sqlAcademy + " and (c.timetableStyle!=5 and c.timetableStyle!=6 and c.schoolCourse.schoolTerm ="
				+ termId + ") order by c.courseCode ,c.weekday,c.timetableNumber desc ";
		List<TimetableAppointment> timetableAppointmentAcademys = timetableAppointmentDAO.executeQuery(sqlAcademy, curr
				* size, size);
		
		// 合并教务排课及自主排课内容
		for (TimetableAppointment timetableAppointment1 : timetableAppointmentSelfs) {
			timetableAppointmentAcademys.add(timetableAppointment1);
		}

		Collections.sort(timetableAppointmentAcademys, new Comparator<TimetableAppointment>() {
			public int compare(TimetableAppointment arg0, TimetableAppointment arg1) {

				if (!arg0.getTimetableGroups().isEmpty() && !arg1.getTimetableGroups().isEmpty()) {
					return arg0.getTimetableGroups().iterator().next().getTimetableBatch().getId()
							.compareTo(arg1.getTimetableGroups().iterator().next().getTimetableBatch().getId());
				}  else {
					return 0;
				}

			}
		});
		return timetableAppointmentAcademys;
	}

	/*************************************************************************************
	 * @內容：查看计数的所有时间列表安排
	 * @作者： 魏誠
	 * @日期：2014-07-24
	 *************************************************************************************/
	public int getCountTimetableAppointmentsByQuery(int termId, TimetableAppointment timetableAppointment, int status,
			int iLabCenter) {
		String academyNumber="";
        // 如果没有获取有效的实验分室列表-根据登录用户的所属学院
       /* if (iLabCenter != -1) {
    		//获取选择的实验中心
        	academyNumber = shareService.getUserDetail().getSchoolAcademy().getAcademyNumber();
        }else{
        	academyNumber = shareService.getUserDetail().getSchoolAcademy().getAcademyNumber();
        }*/
		// 创建根据学期来查询课程(二次排课)；
		StringBuffer sql = new StringBuffer(
				"select count(distinct c)  from TimetableAppointment c ,TimetableTeacherRelated d where 1=1  and d.timetableAppointment.id = c.id  and c.schoolCourseInfo.academyNumber like '%"
						+ academyNumber + "%'");
		if (status != -1) {
			sql.append(" and c.status = " + status);
		}
		if(timetableAppointment!=null&&timetableAppointment.getDetail() != null){
			sql.append(" and d.user.username like '"+ timetableAppointment.getDetail() + "'");
		}

		/*	*//**
		 * 判断是否只是老师角色
		 **/
		User user = shareService.getUserDetail();
		String judge = ",";
		for (Authority authority : user.getAuthorities()) {
			judge = judge + "," + authority.getId() + ",";
		}
		
		if (timetableAppointment!=null&&timetableAppointment.getSchoolCourseInfo() != null
				&& !timetableAppointment.getSchoolCourseInfo().getCourseNumber().equals("-1")) {
			sql.append(" and c.schoolCourseInfo.courseNumber like '%"
					+ timetableAppointment.getSchoolCourseInfo().getCourseNumber() + "%'");

		}

		// 自主排课
		String sqlSelf = sql.toString();
		// 如果权限仅为教师，不为实验中心主任或超级管理员，则增加过滤
		if (judge.indexOf(",2,") != -1 && judge.indexOf(",11,") == -1 && judge.indexOf(",4,") == -1) {
			sqlSelf = sqlSelf + " and (c.timetableSelfCourse.user.username like '" + user.getUsername() + "'"
					+" or d.user.username like '"+ user.getUsername() + "')";
		}
		sqlSelf = sqlSelf + " and (c.timetableStyle!=1 and c.timetableStyle!=2 and c.timetableStyle!=3 and c.timetableStyle!=4 and c.timetableSelfCourse.schoolTerm ="
				+ termId + ") ";
		if (timetableAppointment!=null&&timetableAppointment.getSchoolCourseInfo() != null
				&& !timetableAppointment.getSchoolCourseInfo().getCourseNumber().equals("-1")) {
			sqlSelf += " and c.schoolCourseInfo.courseNumber like '%"
					+ timetableAppointment.getSchoolCourseInfo().getCourseNumber() + "%'";

		}
		int iSelf = ((Long) timetableAppointmentDAO.createQuerySingleResult(sqlSelf).getSingleResult()).intValue();
		// 教务排课
		String sqlAcademy = sql.toString();
		// 如果权限仅为教师，不为实验中心主任或超级管理员，则增加过滤
		if (judge.indexOf(",2,") != -1 && judge.indexOf(",11,") == -1 && judge.indexOf(",4,") == -1) {
			sqlAcademy = sqlAcademy + " and (c.schoolCourseDetail.user.username like '" + user.getUsername() + "'"
					+" or d.user.username like '"+ user.getUsername() + "')";
		}
		if (timetableAppointment!=null&&timetableAppointment.getSchoolCourseInfo() != null
				&& !timetableAppointment.getSchoolCourseInfo().getCourseNumber().equals("-1")) {
			sqlAcademy += " and c.schoolCourseInfo.courseNumber like '%"
					+ timetableAppointment.getSchoolCourseInfo().getCourseNumber() + "%'";

		}
		sqlAcademy = sqlAcademy + " and (c.timetableStyle!=5 and c.timetableStyle!=6 and c.schoolCourse.schoolTerm ="
				+ termId + ") ";
		int iAcademy = ((Long) timetableAppointmentDAO.createQuerySingleResult(sqlAcademy).getSingleResult())
				.intValue();

		return iSelf + iAcademy;
	}
	
	/*************************************************************************************
     * @內容：查看所有的预约的列表安排
     * @作者：贺子龙
     * @日期：2016-04-09
     *************************************************************************************/
    public List<TimetableAppointment> getTimetableAppointmentsByQuery(int termId,
            String courseNumber, int status, int curr, int size, int iLabCenter) {
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
                "select distinct c from TimetableAppointment  c ,TimetableTeacherRelated d where 1=1  ");
        if (status != -1) {
            sql.append(" and c.status = " + status);
        }

        if (!courseNumber.equals("")) {
            sql.append(" and c.schoolCourseInfo.courseNumber like '" + courseNumber + "' ");
        }

        /**
         * 判断是否只是老师角色
         **/
        User user = shareService.getUserDetail();
        String judge = ",";
        for (Authority authority : user.getAuthorities()) {
            judge = judge + "," + authority.getId() + ",";
        }

        // 按照课程排序
        sql.append(" and (c.schoolCourseInfo.academyNumber like '%" + academyNumber
                + "%') ");

        // 自主排课
        String sqlSelf = sql.toString();
        // 如果权限仅为教师，不为实验中心主任或超级管理员，则增加过滤
        if (judge.indexOf(",2,") != -1 && judge.indexOf(",7,") == -1 && judge.indexOf(",11,") == -1 && judge.indexOf(",4,") == -1) {
            sqlSelf = sqlSelf + " and (c.timetableSelfCourse.user.username like '" + user.getUsername() + "'"
            		+" or d.user.username like '"+ user.getUsername() + "')";
        }
        sqlSelf = sqlSelf + " and (c.timetableStyle!=1 and c.timetableStyle!=2 and c.timetableStyle!=3 and c.timetableStyle!=4 and c.timetableSelfCourse.schoolTerm ="
                + termId + ") order by c.courseCode ,c.weekday,c.timetableNumber desc";
        List<TimetableAppointment> timetableAppointmentSelfs = timetableAppointmentDAO.executeQuery(sqlSelf, curr*size, size);

        // 教务排课
        String sqlAcademy = sql.toString();
        // 如果权限仅为教师，不为实验中心主任或超级管理员，则增加过滤
        if (judge.indexOf(",2,") != -1 && judge.indexOf(",7,") == -1 && judge.indexOf(",11,") == -1 && judge.indexOf(",4,") == -1) {
            sqlAcademy = sqlAcademy + " and (c.schoolCourseDetail.user.username like '" + user.getUsername() + "'"
            		+" or d.user.username like '"+ user.getUsername() + "')";
        }
        sqlAcademy = sqlAcademy + " and ((c.timetableStyle is null or c.timetableStyle is not null and c.timetableStyle !=5 and c.timetableStyle!=6) and c.schoolCourse.schoolTerm ="
                + termId + ") order by c.courseCode ,c.weekday,c.timetableNumber desc ";
        List<TimetableAppointment> timetableAppointmentAcademys = timetableAppointmentDAO.executeQuery(sqlAcademy, curr*size, size);
        
        // 合并教务排课及自主排课内容
        for (TimetableAppointment timetableAppointment1 : timetableAppointmentSelfs) {
            timetableAppointmentAcademys.add(timetableAppointment1);
        }

        Collections.sort(timetableAppointmentAcademys, new Comparator<TimetableAppointment>() {
            public int compare(TimetableAppointment arg0, TimetableAppointment arg1) {

                if (!arg0.getTimetableGroups().isEmpty() && !arg1.getTimetableGroups().isEmpty()) {
                    return arg0.getTimetableGroups().iterator().next().getTimetableBatch().getId()
                            .compareTo(arg1.getTimetableGroups().iterator().next().getTimetableBatch().getId());
                }  else {
                    return 0;
                }

            }
        });
        return timetableAppointmentAcademys;
    }

    /*************************************************************************************
     * @內容：查看计数的所有时间列表安排
     * @作者： 贺子龙
     * @日期：2016-04-09
     *************************************************************************************/
    public int getCountTimetableAppointmentsByQuery(int termId, String courseNumber, int status,
            int iLabCenter) {
        String academyNumber="";
        // 如果没有获取有效的实验分室列表-根据登录用户的所属学院
        if (iLabCenter != -1) {
            //获取选择的实验中心
            academyNumber = shareService.getUserDetail().getSchoolAcademy().getAcademyNumber();
        }else{
            academyNumber = shareService.getUserDetail().getSchoolAcademy().getAcademyNumber();
        }
        // 创建根据学期来查询课程(二次排课)；
        StringBuffer sql = new StringBuffer(
                "select count(distinct c)  from TimetableAppointment c ,TimetableTeacherRelated d where 1=1  and  c.schoolCourseInfo.academyNumber like '%"
                        + academyNumber + "%'");
        if (status != -1) {
            sql.append(" and c.status = " + status);
        }

        /*  *//**
         * 判断是否只是老师角色
         **/
        User user = shareService.getUserDetail();
        String judge = ",";
        for (Authority authority : user.getAuthorities()) {
            judge = judge + "," + authority.getId() + ",";
        }
        
        if (!courseNumber.equals("")) {
            sql.append(" and c.schoolCourseInfo.courseNumber like '%" + courseNumber + "%'");

        }

        // 自主排课
        String sqlSelf = sql.toString();
        // 如果权限仅为教师，不为实验中心主任或超级管理员，则增加过滤
        if (judge.indexOf(",2,") != -1 && judge.indexOf(",7,") == -1 && judge.indexOf(",11,") == -1 && judge.indexOf(",4,") == -1) {
            sqlSelf = sqlSelf + " and (c.timetableSelfCourse.user.username like '" + user.getUsername() + "'"
            		+" or d.user.username like '"+ user.getUsername() + "')";
        }
        sqlSelf = sqlSelf + " and (c.timetableStyle!=1 and c.timetableStyle!=2 and c.timetableStyle!=3 and c.timetableStyle!=4 and c.timetableSelfCourse.schoolTerm ="
                + termId + ") ";
        int iSelf = ((Long) timetableAppointmentDAO.createQuerySingleResult(sqlSelf).getSingleResult()).intValue();
        // 教务排课
        String sqlAcademy = sql.toString();
        // 如果权限仅为教师，不为实验中心主任或超级管理员，则增加过滤
        if (judge.indexOf(",2,") != -1 && judge.indexOf(",7,") == -1 && judge.indexOf(",11,") == -1 && judge.indexOf(",4,") == -1) {
            sqlAcademy = sqlAcademy + " and (c.schoolCourseDetail.user.username like '" + user.getUsername() + "'"
            +" or d.user.username like '"+ user.getUsername() + "')";
        }
        sqlAcademy = sqlAcademy + " and (c.timetableStyle is null or c.timetableStyle is not null and c.timetableStyle !=5 and c.timetableStyle!=6) and c.schoolCourse.schoolTerm ="
                + termId + ") ";
        int iAcademy = ((Long) timetableAppointmentDAO.createQuerySingleResult(sqlAcademy).getSingleResult())
                .intValue();

        return iSelf + iAcademy;
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
	 * @內容：根据实验室和节次及星期列出所有时间列表安排
	 * @作者： 魏誠
	 * @日期：2014-07-24
	 *************************************************************************************/
	public List<TimetableLabRelated> getReListLabTimetableAppointments(HttpServletRequest request, int iLabCenter,
			int term) {
		String academyNumber="";
        // 如果没有获取有效的实验分室列表-根据登录用户的所属学院
        if (iLabCenter != -1) {
    		//获取选择的实验中心
        	academyNumber = shareService.getUserDetail().getSchoolAcademy().getAcademyNumber();
        }else{
        	academyNumber = shareService.getUserDetail().getSchoolAcademy().getAcademyNumber();
        }		// 创建根据学期来查询课程；
		StringBuffer sql = new StringBuffer(
				"select c from TimetableLabRelated c where 1=1 and  c.timetableAppointment.schoolCourse.schoolTerm.id ="
						+ term + " and " + "c.timetableAppointment.schoolCourseInfo.academyNumber like '"
						+ academyNumber + "' ");
		if (request.getParameter("labroom") != null && !request.getParameter("labroom").equals("-1")) {
			sql.append(" and c.labRoom.id = " + request.getParameter("labroom"));
		}

		List<TimetableLabRelated> timetableLabRelateds = timetableLabRelatedDAO.executeQuery(sql.toString(), 0, -1);
		return timetableLabRelateds;
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
						+ academyNumber + "' ");
		if (request.getParameter("labroom") != null && !request.getParameter("labroom").equals("-1")) {
			sql.append(" and c.labRoom.id = " + request.getParameter("labroom"));
		}

		List<TimetableLabRelated> timetableLabRelateds = timetableLabRelatedDAO.executeQuery(sql.toString());
		return timetableLabRelateds;
	}

	/*************************************************************************************
	 * @內容：发布所选选课组所在的排课内容
	 * @作者： 魏誠
	 * @日期：2014-08-4
	 *************************************************************************************/
	@Transactional
	public void doReleaseTimetableAppointments(String courseCode, int flag) {
		// 判断是否为自主排课；
		String sqlstring = "";
		// flag==0为教务排课，flag=1为自建排课
		if (flag >= 1 && flag <= 4) {
			sqlstring = "select c from TimetableAppointment c where c.schoolCourseDetail.schoolCourse.courseCode like '"
					+ courseCode + "'";
		} else {
			sqlstring = "select c from TimetableAppointment c where c.timetableSelfCourse.courseCode like '"
					+ courseCode + "'";
		}
		StringBuffer sql = new StringBuffer(sqlstring);
		List<TimetableAppointment> timetableAppointments = timetableAppointmentDAO.executeQuery(sql.toString());
		for (TimetableAppointment timetableAppointment : timetableAppointments) {
			timetableAppointment.setStatus(1);
			timetableAppointmentDAO.store(timetableAppointment);
		}
	}

	/*************************************************************************************
	 * @throws ParseException 
	 * @內容：保存调整排课的内容
	 * @作者： 魏誠
	 * @日期：2014-08-5
	 *************************************************************************************/
	@SuppressWarnings("unused")
	@Transactional
	public SchoolCourseDetail saveAdjustTimetable(HttpServletRequest request) throws ParseException {
		// 调整排课的实验室选择
		String[] labRooms = request.getParameterValues("labRooms");
		//调整排课的实验室设备选择
		String[] devices = request.getParameterValues("devices");
		//调整排课的实验室设备选择
		String[]  sLabRoomDevice = request.getParameterValues("labRoomDevice_id");
		// 调整排课的授课教师选择
		String[] teachers = request.getParameterValues("teachers");

		// 调整排课的授课教师选择
		String[] items = request.getParameterValues("items");

		String weekDay = request.getParameter("weekday");
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
		SchoolCourseDetail schoolCourseDetail = schoolCourseDetailDAO.findSchoolCourseDetailByCourseDetailNo(request
				.getParameter("courseDetailNo"));

		/**
		 * 
		*/

		TimetableAppointment timetableAppointment1 = new TimetableAppointment();
		timetableAppointment1.setAppointmentNo(schoolCourseDetail.getCourseDetailNo());
		// timetableAppointment1.setTimetableNumber(timetableAppointment.getTimetableNumber());
		timetableAppointment1.setCreatedBy(shareService.getUserDetail().getUsername());
		timetableAppointment1.setCreatedBy(shareService.getUserDetail().getUsername());
		timetableAppointment1.setWeekday(Integer.parseInt(weekDay));
		timetableAppointment1.setCreatedDate(Calendar.getInstance());
		timetableAppointment1.setUpdatedDate(Calendar.getInstance());
		// timetableAppointment1.setTeacherRelated(timetableAppointment.getTeacherRelated());
		// timetableAppointment1.setTutorRelated(timetableAppointment.getTutorRelated());
		timetableAppointment1.setSchoolCourseDetail(schoolCourseDetail);
		timetableAppointment1.setSchoolCourse(schoolCourseDetail.getSchoolCourse());
		timetableAppointment1.setCreatedBy(shareService.getUserDetail().getUsername());
		timetableAppointment1.setSchoolCourseInfo(schoolCourseDetail.getSchoolCourse().getSchoolCourseInfo());
		// 设置排课方式
		timetableAppointment1.setTimetableStyle(2);
		// 设置排课状态
		timetableAppointment1.setStatus(10);
		// 设置选课组编号
		timetableAppointment1.setCourseCode(schoolCourseDetail.getSchoolCourse().getCourseCode());

		timetableAppointment1.setCreatedBy(shareService.getUserDetail().getUsername());
		timetableAppointment1.setCreatedDate(Calendar.getInstance());
		// timetableAppointment1.setWeekday(timetableAppointment.getWeekday());
		timetableAppointment1.setUpdatedDate(Calendar.getInstance());
		if (request.getParameter("preparation") != null) {
			timetableAppointment1.setPreparation(request.getParameter("preparation"));
		} else {
			timetableAppointment1.setPreparation("");
		}
		if (request.getParameter("groups") != null && !request.getParameter("groups").isEmpty()) {
			timetableAppointment1.setGroups(Integer.parseInt(request.getParameter("groups")));
		} else {
			timetableAppointment1.setGroups(-1);
		}
		if (request.getParameter("labhours") != null && !request.getParameter("labhours").isEmpty()) {
			timetableAppointment1.setLabhours(Integer.parseInt(request.getParameter("labhours")));
		} else {
			timetableAppointment1.setLabhours(-1);
		}
		if (request.getParameter("groupCount") != null && !request.getParameter("groupCount").isEmpty()) {
			timetableAppointment1.setGroupCount(Integer.parseInt(request.getParameter("groupCount")));
		} else {
			timetableAppointment1.setGroupCount(-1);
		}
		if (request.getParameter("consumablesCosts") != null) {
			timetableAppointment1.setConsumablesCosts(new BigDecimal((request.getParameter("consumablesCosts"))));
		} else {
			timetableAppointment1.setConsumablesCosts(new BigDecimal(-1));
		}
		// 设置调整排课的内容
		if (sWeek[0].indexOf(("-")) == -1) {
			timetableAppointment1.setTotalWeeks("1");
			timetableAppointment1.setStartWeek(Integer.parseInt(sWeek[0]));
			timetableAppointment1.setEndWeek(Integer.parseInt(sWeek[0]));

		} else {
			timetableAppointment1.setTotalWeeks(String.valueOf((Integer.parseInt(sWeek[0].split("-")[1]) - Integer
					.parseInt(sWeek[0].split("-")[0]))));
			timetableAppointment1.setStartWeek(Integer.parseInt(sWeek[0].split("-")[0]));
			timetableAppointment1.setEndWeek(Integer.parseInt(sWeek[0].split("-")[1]));
		}

		if (sClasses[0].indexOf(("-")) == -1) {
			timetableAppointment1.setTotalClasses(Integer.parseInt(sClasses[0]));
			timetableAppointment1.setStartClass(Integer.parseInt(sClasses[0]));
			timetableAppointment1.setEndClass(Integer.parseInt(sClasses[0]));
		} else {
			timetableAppointment1.setTotalClasses((Integer.parseInt(sClasses[0].split("-")[1]) - Integer
					.parseInt(sClasses[0].split("-")[0])));
			timetableAppointment1.setStartClass(Integer.parseInt(sClasses[0].split("-")[0]));
			timetableAppointment1.setEndClass(Integer.parseInt(sClasses[0].split("-")[1]));
		}

		timetableAppointment1 = timetableAppointmentDAO.store(timetableAppointment1);

		// * 对排课预约选定的实验分室进行保存

		TimetableLabRelated timetableLabRelated = new TimetableLabRelated();
		
		int countLabRoomDevice = 0; // 计算所选实验室总共有多少设备 
		// 如果matchLabs不为空时
		if (labRooms != null && labRooms.length > 0) {
			for (int i1 = 0; i1 < labRooms.length; i1++) {
				// 将matchLabs添加到matchLabs中
				LabRoom labRoom = labRoomDAO.findLabRoomById(Integer.parseInt(labRooms[i1]));
				timetableLabRelated.setLabRoom(labRoom);
				timetableLabRelated.setTimetableAppointment(timetableAppointment1);
				TimetableLabRelated timetableLabRelatedTmp = timetableLabRelatedDAO.store(timetableLabRelated);
				timetableLabRelatedDAO.flush();
				
				// 实验室设备数量累加
                if (labRoom.getLabRoomDevices()!=null && labRoom.getLabRoomDevices().size()>0) {
                    countLabRoomDevice+=labRoom.getLabRoomDevices().size();
                }
				
				/*if(sLabRoomDevice.length>0){
					timetableCourseSchedulingService.saveTimetableLabroomDeviceReservation(timetableLabRelatedTmp, sLabRoomDevice,schoolCourseDetail.getSchoolTerm().getId());
				}*/
			}
		}

		// 设置此次排课的针对对象（1 设备  2 实验室）
		if ((sLabRoomDevice!=null && countLabRoomDevice == sLabRoomDevice.length)  
				|| sLabRoomDevice==null) {// i.全选情况--纺织学院   ii.其他学院sLabRoomDevice字段为空
			timetableAppointment1.setDeviceOrLab(2);// 此次排课针对实验室
		}else {
			timetableAppointment1.setDeviceOrLab(1);// 此次排课针对设备
		}
		timetableAppointment1 = timetableAppointmentDAO.store(timetableAppointment1);
		
		// * 对排课预约选定的实验项目进行保存

		TimetableItemRelated timetableItemRelated = new TimetableItemRelated();
		// 如果matchItems不为空时
		if (items != null && items.length > 0) {
			for (int i1 = 0; i1 < items.length; i1++) {
				// 将matchItems添加到matchItems中
				timetableItemRelated.setOperationItem(operationItemDAO.findOperationItemById(Integer
						.parseInt(items[i1])));
				timetableItemRelated.setTimetableAppointment(timetableAppointment1);
				timetableItemRelatedDAO.store(timetableItemRelated);
			}
		}

		// * 对排课预约选定的指导老师进行保存

		TimetableTeacherRelated timetableTeacherRelated = new TimetableTeacherRelated();
		// 获取选择的实验室列表
		List<User> matchTeachers = new ArrayList<User>();
		// 如果matchLabs不为空时
		if (teachers != null && teachers.length > 0) {
			for (int i1 = 0; i1 < teachers.length; i1++) {
				// 将matchLabs添加到matchLabs中
				matchTeachers.add(userDAO.findUserByUsername(teachers[i1]));
				timetableTeacherRelated.setUser(userDAO.findUserByUsername(teachers[i1]));
				timetableTeacherRelated.setTimetableAppointment(timetableAppointment1);
				timetableTeacherRelatedDAO.store(timetableTeacherRelated);
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
				timetableAppointmentSameNumber.setTimetableAppointment(timetableAppointment1);
				timetableAppointmentSameNumberDAO.store(timetableAppointmentSameNumber);
				timetableAppointmentSameNumberDAO.flush();
			}
		}
		
		List<TimetableLabRelated> timetableLabRelateds = timetableLabRelatedDAO.executeQuery("select c from TimetableLabRelated c where c.timetableAppointment.id="+timetableAppointment1.getId(), 0,-1);
		if(timetableAppointment1.getDeviceOrLab().equals(1)){
			for(TimetableLabRelated timetableLabRelatedTmp:timetableLabRelateds){
				timetableCourseSchedulingService.saveTimetableLabroomDeviceReservation(timetableLabRelatedTmp, sLabRoomDevice,schoolCourseDetail.getSchoolTerm().getId());
			}
		}else {
			mysqlService.createLabLimitByAppointment(timetableAppointment1.getId());
		}
		return schoolCourseDetail;
	}

	/*************************************************************************************
	 * @內容：排课管理中，保存修改的排课的内容
	 * @作者： 魏誠
	 * @日期：2014-08-5
	 *************************************************************************************/
	@Transactional
	public void saveAdminTimetable(HttpServletRequest request) {
		TimetableAppointment timetableAppointment = new TimetableAppointment();
		// 调整排课的实验室选择
		String[] labRooms = request.getParameterValues("labRooms");
		// 调整排课的实验项目选择
		// String[] items = request.getParameterValues("items");
		// 调整排课的授课教师选择
		String[] teachers = request.getParameterValues("teachers");
		// 调整排课的授课教师选择
		String[] tutors = request.getParameterValues("tutors");

		// String weekday = request.getParameter("weekday");
		// 调整排课的星期选择
		String[] weeks = request.getParameterValues("weeks");
		int[] intWeeks = new int[weeks.length];

		for (int k = 0; k < weeks.length; k++) {
			intWeeks[k] = Integer.parseInt(weeks[k]);
		}
		// 周次进行排序
		String[] sWeek = this.getTimetableWeekClass(intWeeks);
		// 调整排课的节次选择
		String[] classes = request.getParameterValues("classes");
		int[] intClasses = new int[classes.length];
		for (int k = 0; k < classes.length; k++) {
			intClasses[k] = Integer.parseInt(classes[k]);
		}

		// 节次进行排序
		String[] sClasses = this.getTimetableWeekClass(intClasses);
		TimetableAppointment requestTimetableAppointment = timetableAppointmentDAO.findTimetableAppointmentById(Integer
				.parseInt(request.getParameter("id")));
		SchoolCourseDetail schoolCourseDetail = requestTimetableAppointment.getSchoolCourseDetail();

		for (int i = 0; i < sWeek.length; i++) {
			for (int j = 0; j < sClasses.length; j++) {
				timetableAppointment.setAppointmentNo(schoolCourseDetail.getCourseDetailNo());
				// timetableAppointment.setTimetableNumber(timetableAppointment.getTimetableNumber());
				timetableAppointment.setCreatedBy(shareService.getUserDetail().getUsername());
				timetableAppointment.setCreatedBy(shareService.getUserDetail().getUsername());
				timetableAppointment.setCreatedDate(Calendar.getInstance());
				timetableAppointment.setUpdatedDate(Calendar.getInstance());
				timetableAppointment.setSchoolCourseDetail(schoolCourseDetail);
				// 设置排课方式为二次排课的分组排课模式
				timetableAppointment.setTimetableStyle(requestTimetableAppointment.getTimetableStyle());
				// 设置排课状态为待发布
				timetableAppointment.setStatus(requestTimetableAppointment.getStatus());
				timetableAppointment.setCreatedBy(shareService.getUserDetail().getUsername());
				timetableAppointment.setCreatedBy(shareService.getUserDetail().getUsername());
				timetableAppointment.setCreatedDate(Calendar.getInstance());
				timetableAppointment.setSchoolCourseInfo(requestTimetableAppointment.getSchoolCourseInfo());
				// 保存选课组
				timetableAppointment.setCourseCode(requestTimetableAppointment.getCourseCode());
				// 保存课程
				timetableAppointment.setSchoolCourse(requestTimetableAppointment.getSchoolCourse());
				timetableAppointment.setWeekday(requestTimetableAppointment.getWeekday());
				timetableAppointment.setUpdatedDate(Calendar.getInstance());
				if (request.getParameter("preparation") != null) {
					timetableAppointment.setPreparation(request.getParameter("preparation"));
				} else {
					timetableAppointment.setPreparation("");
				}
				if (request.getParameter("groups") != null) {
					timetableAppointment.setGroups(Integer.parseInt(request.getParameter("groups")));
				} else {
					timetableAppointment.setGroups(-1);
				}
				if (request.getParameter("labhours") != null) {
					timetableAppointment.setLabhours(Integer.parseInt(request.getParameter("labhours")));
				} else {
					timetableAppointment.setLabhours(-1);
				}
				if (request.getParameter("groupCount") != null) {
					timetableAppointment.setGroupCount(Integer.parseInt(request.getParameter("groupCount")));
				} else {
					timetableAppointment.setGroupCount(-1);
				}
				if (request.getParameter("consumablesCosts") != null) {
					timetableAppointment
							.setConsumablesCosts(new BigDecimal((request.getParameter("consumablesCosts"))));
				} else {
					timetableAppointment.setConsumablesCosts(new BigDecimal(-1));
				}
				// 设置调整排课的内容
				if (sWeek[i].indexOf(("-")) == -1) {
					timetableAppointment.setTotalWeeks("1");
					timetableAppointment.setStartWeek(Integer.parseInt(sWeek[i]));
					timetableAppointment.setEndWeek(Integer.parseInt(sWeek[i]));

				} else {
					timetableAppointment
							.setTotalWeeks(String.valueOf((Integer.parseInt(sWeek[i].split("-")[1]) - Integer
									.parseInt(sWeek[i].split("-")[0]))));
					timetableAppointment.setStartWeek(Integer.parseInt(sWeek[i].split("-")[0]));
					timetableAppointment.setEndWeek(Integer.parseInt(sWeek[i].split("-")[1]));
				}

				if (sClasses[j].indexOf(("-")) == -1) {
					timetableAppointment.setTotalClasses(Integer.parseInt(sClasses[j]));
					timetableAppointment.setStartClass(Integer.parseInt(sClasses[j]));
					timetableAppointment.setEndClass(Integer.parseInt(sClasses[j]));
				} else {
					timetableAppointment.setTotalClasses((Integer.parseInt(sClasses[j].split("-")[1]) - Integer
							.parseInt(sClasses[j].split("-")[0])));
					timetableAppointment.setStartClass(Integer.parseInt(sClasses[j].split("-")[0]));
					timetableAppointment.setEndClass(Integer.parseInt(sClasses[j].split("-")[1]));
				}
				TimetableAppointment timetableAppointmentNew = timetableAppointmentDAO.store(timetableAppointment);
				// timetableAppointmentDAO.flush();
				TimetableLabRelated timetableLabRelated = new TimetableLabRelated();
				// 如果matchLabs不为空时
				if (labRooms != null && labRooms.length > 0) {
					for (int i1 = 0; i1 < labRooms.length; i1++) {
						// 将matchLabs添加到matchLabs中
						timetableLabRelated.setLabRoom(labRoomDAO.findLabRoomById(Integer.parseInt(labRooms[i1])));
						timetableLabRelated.setTimetableAppointment(timetableAppointmentNew);
						timetableLabRelatedDAO.store(timetableLabRelated);
						// timetableLabRelatedDAO.flush();
					}
				}
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
				 * 对排课预约选定的指导老师进行保存
				 */
				TimetableTutorRelated timetableTutorRelated = new TimetableTutorRelated();
				// 获取选择的实验室列表
				List<User> matchTutors = new ArrayList<User>();
				// 如果matchLabs不为空时
				if (tutors.length > 0) {
					for (int i1 = 0; i1 < tutors.length; i1++) {
						// 将matchLabs添加到matchLabs中
						matchTutors.add(userDAO.findUserByUsername(tutors[i1]));
						timetableTutorRelated.setUser(userDAO.findUserByUsername(tutors[i1]));
						timetableTutorRelated.setTimetableAppointment(timetableAppointmentNew);
						timetableTutorRelatedDAO.store(timetableTutorRelated);
						timetableTutorRelatedDAO.flush();
					}
				}

				/*
				 * 对排课预约选定的实验项目进行保存
				 */
				// TimetableItemRelated timetableItemRelated = new
				// TimetableItemRelated();
				// 获取选择的实验室列表
				// List<OperationItem> matchItems = new
				// ArrayList<OperationItem>();
				// 如果matchLabs不为空时
				/*
				 * if (items.length > 0) { for (int i1 = 0; i1 < items.length;
				 * i1++) { // 将matchLabs添加到matchLabs中
				 * matchItems.add(operationItemDAO
				 * .findOperationItemById(Integer .parseInt(items[i1])));
				 * timetableItemRelated.setOperationItem(operationItemDAO
				 * .findOperationItemById(Integer .parseInt(items[i1])));
				 * timetableItemRelated
				 * .setTimetableAppointment(timetableAppointmentNew);
				 * timetableItemRelatedDAO.store(timetableItemRelated);
				 * timetableItemRelatedDAO.flush(); } }
				 */
			}
		}

	}

	/*************************************************************************************
	 * @throws ParseException 
	 * @內容：保存二次排课的不分组排课的内容
	 * @作者： 魏誠
	 * @日期：2014-08-5
	 *************************************************************************************/
	@Transactional
	public TimetableAppointment saveNoGroupReTimetable(HttpServletRequest request) throws ParseException {

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

		for (int k = 0; k < weeks.length; k++) {
			intWeeks[k] = Integer.parseInt(weeks[k]);
		}
		// 周次进行排序
		String[] sWeek = this.getTimetableWeekClass(intWeeks);
		// 调整排课的节次选择
		String[] classes = request.getParameterValues("classes");
		int[] intClasses = new int[classes.length];
		for (int k = 0; k < classes.length; k++) {
			intClasses[k] = Integer.parseInt(classes[k]);
		}

		// 节次进行排序
		String[] sClasses = this.getTimetableWeekClass(intClasses);
		SchoolCourseDetail schoolCourseDetail = schoolCourseDetailDAO.findSchoolCourseDetailByCourseDetailNo(request
				.getParameter("courseDetailNo"));
		/**
         * 
		*/

		timetableAppointment.setAppointmentNo(schoolCourseDetail.getCourseDetailNo());
		// timetableAppointment.setTimetableNumber(timetableAppointment.getTimetableNumber());
		timetableAppointment.setCreatedBy(shareService.getUserDetail().getUsername());
		timetableAppointment.setCreatedBy(shareService.getUserDetail().getUsername());
		timetableAppointment.setCreatedDate(Calendar.getInstance());
		timetableAppointment.setUpdatedDate(Calendar.getInstance());
		timetableAppointment.setSchoolCourseDetail(schoolCourseDetail);
		// 设置排课方式为二次排课的分组排课模式
		timetableAppointment.setTimetableStyle(3);
		// 设置排课状态为待发布
		timetableAppointment.setStatus(10);
		timetableAppointment.setSchoolCourseInfo(schoolCourseDetail.getSchoolCourse().getSchoolCourseInfo());
		timetableAppointment.setCreatedBy(shareService.getUserDetail().getUsername());
		timetableAppointment.setCreatedBy(shareService.getUserDetail().getUsername());
		timetableAppointment.setCreatedDate(Calendar.getInstance());
		// 保存选课组
		timetableAppointment.setCourseCode(request.getParameter("courseCode").toString());
		// 保存课程
		timetableAppointment.setSchoolCourse(schoolCourseDetail.getSchoolCourse());
		timetableAppointment.setSchoolCourseInfo(schoolCourseDetail.getSchoolCourse().getSchoolCourseInfo());
		timetableAppointment.setWeekday(Integer.parseInt(weekday));
		timetableAppointment.setUpdatedDate(Calendar.getInstance());
		if (request.getParameter("preparation") != null) {
			timetableAppointment.setPreparation(request.getParameter("preparation"));
		} else {
			timetableAppointment.setPreparation("");
		}
		if (request.getParameter("preparation") != null && !request.getParameter("groups").isEmpty()) {
			timetableAppointment.setGroups(Integer.parseInt(request.getParameter("groups")));
		} else {
			timetableAppointment.setGroups(-1);
		}
		if (request.getParameter("preparation") != null && !request.getParameter("labhours").isEmpty()) {
			timetableAppointment.setLabhours(Integer.parseInt(request.getParameter("labhours")));
		} else {
			timetableAppointment.setLabhours(-1);
		}
		if (request.getParameter("preparation") != null && !request.getParameter("groupCount").isEmpty()) {
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
		// 获取选择的实验室列表
		List<OperationItem> matchItems = new ArrayList<OperationItem>();
		// 如果matchLabs不为空时
		if (items.length > 0) {
			for (int i1 = 0; i1 < items.length; i1++) {
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
				timetableCourseSchedulingService.saveTimetableLabroomDeviceReservation(timetableLabRelatedTmp, sLabRoomDevice,schoolCourseDetail.getSchoolTerm().getId());

			}
		}else {
			mysqlService.createLabLimitByAppointment(timetableAppointmentNew.getId());
		}
		return timetableAppointment;
	}

	/*************************************************************************************
	 * @throws ParseException 
	 * @內容：保存二次排课的分组排课的内容
	 * @作者： 魏誠
	 * @日期：2014-08-5
	 *************************************************************************************/
	@Transactional
	public TimetableAppointment saveGroupReTimetable(HttpServletRequest request) throws ParseException {
		TimetableAppointment timetableAppointment = new TimetableAppointment();
		TimetableAppointment timetableAppointmentReturn = new TimetableAppointment();
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
		SchoolCourseDetail schoolCourseDetail = schoolCourseDetailDAO.findSchoolCourseDetailByCourseDetailNo(request
				.getParameter("courseDetailNo"));

		/**
		 *
		*/
		timetableAppointment.setAppointmentNo(schoolCourseDetail.getCourseDetailNo());
		// timetableAppointment.setTimetableNumber(timetableAppointment.getTimetableNumber());
		timetableAppointment.setCreatedBy(shareService.getUserDetail().getUsername());
		timetableAppointment.setCreatedBy(shareService.getUserDetail().getUsername());
		timetableAppointment.setCreatedDate(Calendar.getInstance());
		timetableAppointment.setUpdatedDate(Calendar.getInstance());
		// 设置排课方式为二次排课的分组排课模式
		timetableAppointment.setTimetableStyle(4);
		// 设置排课状态为待发布
		timetableAppointment.setStatus(10);
		timetableAppointment.setSchoolCourseInfo(schoolCourseDetail.getSchoolCourse().getSchoolCourseInfo());
		// 设置排课的选课组编号
		timetableAppointment.setCourseCode(schoolCourseDetail.getSchoolCourse().getCourseCode());
		timetableAppointment.setSchoolCourseDetail(schoolCourseDetail);
		timetableAppointment.setSchoolCourse(schoolCourseDetail.getSchoolCourse());
		timetableAppointment.setCreatedBy(shareService.getUserDetail().getUsername());
		timetableAppointment.setCreatedBy(shareService.getUserDetail().getUsername());
		timetableAppointment.setCreatedDate(Calendar.getInstance());
		if (request.getParameter("preparation") != null) {
			timetableAppointment.setPreparation(request.getParameter("preparation"));
		} else {
			timetableAppointment.setPreparation("");
		}
		if (request.getParameter("groups") != null) {
			timetableAppointment.setGroups(Integer.parseInt(request.getParameter("groups")));
		} else {
			timetableAppointment.setGroups(-1);
		}
		if (request.getParameter("labhours") != null) {
			timetableAppointment.setLabhours(Integer.parseInt(request.getParameter("labhours")));
		} else {
			timetableAppointment.setLabhours(-1);
		}
		if (request.getParameter("groupCount") != null) {
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
		// 获取选择的实验室列表
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
				timetableCourseSchedulingService.saveTimetableLabroomDeviceReservation(timetableLabRelatedTmp, sLabRoomDevice,schoolCourseDetail.getSchoolTerm().getId());
			}
		}else {
			mysqlService.createLabLimitByAppointment(timetableAppointmentNew.getId());
		}
		return timetableAppointmentReturn;
	}

	/*************************************************************************************
	 * @內容：排课管理中，删除排课信息的内容
	 * @作者： 魏誠
	 * @日期：2014-08-5
	 *************************************************************************************/
	@Transactional
	public void deleteAppointment(int id) {
		// 删除相关的学生选课信息
		List<TimetableGroupStudents> timetableGroupStudentses = null;
		if (timetableAppointmentDAO.findTimetableAppointmentById(id).getTimetableGroups().size() > 0) {
			timetableGroupStudentses = timetableGroupStudentsDAO
					.executeQuery("select c from TimetableGroupStudents c where c.timetableGroup.timetableBatch.id = "
							+ timetableAppointmentDAO.findTimetableAppointmentById(id).getTimetableGroups().iterator()
									.next().getTimetableBatch().getId());

		}
		if (timetableAppointmentDAO.findTimetableAppointmentById(id).getTimetableGroups().size() > 0) {
			for (TimetableGroupStudents timetableGroupStudents : timetableGroupStudentses) {
				timetableGroupStudentsDAO.remove(timetableGroupStudents);
			}
		}
		TimetableBatch timetableBatch = null;
		// 设置排课批次标记为未调整完成
		if (timetableAppointmentDAO.findTimetableAppointmentById(id).getTimetableGroups().size() > 0) {
			timetableBatch = timetableAppointmentDAO.findTimetableAppointmentById(id).getTimetableGroups().iterator()
					.next().getTimetableBatch();
			timetableBatch.setFlag(null);
			timetableBatchDAO.store(timetableBatch);
			// 设置排课批次标记为待发布
			for (TimetableGroup timetableGroup : timetableBatch.getTimetableGroups()) {
				for (TimetableAppointment timetableAppointment : timetableGroup.getTimetableAppointments()) {
					// 设置为待发布
					timetableAppointment.setStatus(10);
					// 保存排课分组信息
					// Set<TimetableGroup> timetableGroupList=new
					// HashSet<TimetableGroup>();
					// timetableGroupDAO.executeQuery("SELECT t FROM TimetableGroup t join t.timetableAppointments s where t.id = ",0,-1);
					// timetableGroupList.addAll(timetableGroupDAO.executeQuery("SELECT t FROM TimetableAppointment t join t.timetableGroups s where t.id = "
					// +timetableAppointment.getId() ,0,-1));

					// timetableAppointment.setTimetableGroups(timetableGroupList);
					timetableAppointmentDAO.store(timetableAppointment);
				}
			}
		}

		// 删除相关实验分室
		List<TimetableLabRelated> timetableLabRelateds = timetableLabRelatedDAO
				.executeQuery("select c from TimetableLabRelated c where c.timetableAppointment.id =" + id);
		for (TimetableLabRelated timetableLabRelated : timetableLabRelateds) {
			//根据实验室相关设备遍历，相关实验分室下的实验室设备资源
			for(TimetableLabRelatedDevice timetableLabRelatedDevice:timetableLabRelated.getTimetableLabRelatedDevices()){
				List<LabRoomDeviceReservation> labRoomDeviceReservations = labRoomDeviceReservationDAO.executeQuery("select c from LabRoomDeviceReservation c where c.timetableLabDevice ="+timetableLabRelatedDevice.getId(), 0,-1);
			    //开始删除
				for(LabRoomDeviceReservation labRoomDeviceReservation:labRoomDeviceReservations){
					labRoomDeviceReservationDAO.remove(labRoomDeviceReservation);
				}
			}
			timetableLabRelatedDAO.remove(timetableLabRelated);
		}
		// 删除相关授课教师
		List<TimetableTeacherRelated> timetableTeacherRelateds = timetableTeacherRelatedDAO
				.executeQuery("select c from TimetableTeacherRelated c where c.timetableAppointment.id =" + id);
		for (TimetableTeacherRelated timetableTeacherRelated : timetableTeacherRelateds) {
			timetableTeacherRelatedDAO.remove(timetableTeacherRelated);
		}
		// 删除相关指导教师
		List<TimetableTutorRelated> timetableTutorRelateds = timetableTutorRelatedDAO
				.executeQuery("select c from TimetableTutorRelated c where c.timetableAppointment.id =" + id);
		for (TimetableTutorRelated timetableTutorRelated : timetableTutorRelateds) {
			timetableTutorRelatedDAO.remove(timetableTutorRelated);
		}

		// 删除相关指导教师
		List<TimetableItemRelated> timetableItemRelateds = timetableItemRelatedDAO
				.executeQuery("select c from TimetableItemRelated c where c.timetableAppointment.id =" + id);
		for (TimetableItemRelated timetableItemRelated : timetableItemRelateds) {
			timetableItemRelatedDAO.remove(timetableItemRelated);
		}

		// 移除分组选课数据

		timetableAppointmentDAO.remove(timetableAppointmentDAO.findTimetableAppointmentById(id));

	}

	/*************************************************************************************
	 * @內容：进行termid,获取教务选课组编号
	 * @作者： 魏誠
	 * @日期：2014-07-24
	 *************************************************************************************/
	public String getAdminCourseCodeList(int term, int iLabCenter) {
		String academyNumber="";
        // 如果没有获取有效的实验分室列表-根据登录用户的所属学院
        if (iLabCenter != -1) {
    		//获取选择的实验中心
        	academyNumber = shareService.getUserDetail().getSchoolAcademy().getAcademyNumber();
        }else{
        	academyNumber = shareService.getUserDetail().getSchoolAcademy().getAcademyNumber();
        }
		// 返回可用的星期信息
		String sql = "select c from TimetableAppointment c";
		List<TimetableAppointment> list = timetableAppointmentDAO.executeQuery(sql, 0, -1);
		String jsonWeek = "[";

		for (TimetableAppointment timetableAppointment : list) {
			// 教务排课
			if (timetableAppointment.getTimetableStyle() == 1 || timetableAppointment.getTimetableStyle() == 2
					|| timetableAppointment.getTimetableStyle() == 3 || timetableAppointment.getTimetableStyle() == 4) {
				if (timetableAppointment.getSchoolCourse().getSchoolTerm().getId() == term
						&& timetableAppointment.getSchoolCourse().getSchoolAcademy().getAcademyNumber()
								.equals(academyNumber)) {
					jsonWeek = jsonWeek + "{\"courseNumber\":\""
							+ timetableAppointment.getSchoolCourseInfo().getCourseNumber() + "\",\"value\":\""
							+ timetableAppointment.getSchoolCourse().getSchoolCourseInfo().getCourseNumber()
							+ timetableAppointment.getSchoolCourse().getUserByTeacher().getCname()
							+ timetableAppointment.getSchoolCourse().getCourseName() + "；选课组编号："
							+ timetableAppointment.getSchoolCourse().getCourseCode() + "\"},";
				}
				// 自主排课
			} else if (timetableAppointment.getTimetableStyle() == 5 || timetableAppointment.getTimetableStyle() == 6) {
				if (timetableAppointment.getTimetableSelfCourse() != null
						&& timetableAppointment.getTimetableSelfCourse().getSchoolTerm().getId() == term
						&& timetableAppointment.getTimetableSelfCourse().getSchoolAcademy().getAcademyNumber()
								.equals(academyNumber)) {
					jsonWeek = jsonWeek + "{\"courseNumber\":\""
							+ timetableAppointment.getSchoolCourseInfo().getCourseNumber() + "\",\"value\":\""
							+ timetableAppointment.getTimetableSelfCourse().getSchoolCourseInfo().getCourseNumber()
							+ timetableAppointment.getTimetableSelfCourse().getUser().getCname()
							+ timetableAppointment.getTimetableSelfCourse().getSchoolCourseInfo().getCourseName()
							+ "；选课组编号：" + timetableAppointment.getTimetableSelfCourse().getCourseCode() + "\"},";
				}
			}
		}

		jsonWeek.substring(0, jsonWeek.length() - 1);
		jsonWeek = jsonWeek + "]";
		return jsonWeek;

	}
	
	/*************************************************************************************
	 * @throws ParseException 
	 * @內容：根据学期和中心获取月报报表
	 * @作者： 贺子龙
	 * @日期：2015-12-27
	 *************************************************************************************/
	public List<MonthReport> getMonthReports(int term,int year, int month,int iLabCenter) throws ParseException{
		// 根据课程及id获取课程排课列表
		List<TimetableAppointment> timetableAppointments=
				getTimetableAppointmentsByMonth(term,1,year, month,iLabCenter);//里面的1  是status   1--已发布  -1--测试用
		//根据年月生成一个时间段
		Calendar[] timePeriod=shareService.getTimePeriod(year, month);
		List<MonthReport> monthReports=new ArrayList<MonthReport>();
		for (TimetableAppointment ta : timetableAppointments) {
			List<Calendar> dates=schoolWeekService.getTimetableAppointmentDate(ta);
			
			Set<TimetableLabRelated> labRooms=ta.getTimetableLabRelateds();//获取排课对应的实验室
			List<TimetableLabRelated> labRoomList=new ArrayList<TimetableLabRelated>(labRooms);
			for (Calendar date : dates) {
				if (date.before(timePeriod[1])&&date.after(timePeriod[0])) {//上课日期落在查询的月份中
					
					for (TimetableLabRelated timetableLab : labRoomList) {//两个实验室、一个老师，则显示两条记录
						MonthReport monthReport=creatMonthReport(iLabCenter);
						String laboratoryName="";//实验室名称
						String place="";//实验室地点
						if (timetableLab!=null && timetableLab.getLabRoom()!=null
								&& !timetableLab.getLabRoom().getLabRoomName().equals("")) {
							laboratoryName=timetableLab.getLabRoom().getLabRoomName();
						}
						monthReport.setLaboratoryName(laboratoryName);//实验室名称
						if (timetableLab!=null && timetableLab.getLabRoom()!=null
								&& !timetableLab.getLabRoom().getLabRoomAddress().equals("")) {
							place=timetableLab.getLabRoom().getLabRoomAddress();
						}
						monthReport.setPlace(place);//实验室地点
						monthReport.setDate(date);//上课日期
						String courseName="";//课程名称
						if (ta.getSchoolCourseInfo()!=null&&!ta.getSchoolCourseInfo().getCourseName().equals("")) {
							courseName=ta.getSchoolCourseInfo().getCourseName();
						}
						monthReport.setCourseName(courseName);
						
						String experimentName="";//项目卡名称
						String experimentType="";//项目卡类别
						String assessment="";//项目卡考核方式
						Set<TimetableItemRelated> items=ta.getTimetableItemRelateds();
						if (items.size()!=0) {
							int count=0;
							for (TimetableItemRelated item : items) {
								count++;
								if (item.getOperationItem()!=null
										&&item.getOperationItem().getLabRoom().getId()==timetableLab.getId()) {//属于当前实验室的项目卡
									experimentName+=item.getOperationItem().getLpName()+",";
								}
								if (count==1) {//节省时间 
									if (item.getOperationItem()!=null&&item.getOperationItem().getCDictionaryByLpCategoryMain()!=null
											&&!item.getOperationItem().getCDictionaryByLpCategoryMain().getCName().equals("")) {
										experimentType=item.getOperationItem().getCDictionaryByLpCategoryMain().getCName();
									}
									if (item.getOperationItem()!=null&&!item.getOperationItem().getLpAssessmentMethods().equals("")) {
										assessment=item.getOperationItem().getLpAssessmentMethods();
									}
								}
							}
						}
						if (experimentName.length()>1) {
							experimentName=experimentName.substring(0, experimentName.length()-1);
						}
						monthReport.setExperimentName(experimentName);
						monthReport.setExperimentType(experimentType);
						monthReport.setAssessment(assessment);
						//学生上课的人时数  1--机房  2--其他  3--本科  4--专科
						String[] classNumberString=getTimetableAppointmentType(ta);
						int[] classNumber=new int[classNumberString.length-1];
						for (int i = 0; i < classNumberString.length-1; i++) {//最后一位是班级名称，不用转化
							classNumber[i]=Integer.parseInt(classNumberString[i]);
						}
						int juniorNumber=0;
						int juniorClass=0;
						int undergraduateNumber=0;
						int undergraduateClass=0;
						int otherNumber=0;
						int otherClass=0;
						int roomNumber=0;
						int roomClass=0;
						
						switch (classNumber[0]) {
						case 1:
						{
							roomNumber=classNumber[1];
							roomClass=classNumber[2];
						}
						break;
						case 2:
						{
							otherNumber=classNumber[1];
							otherClass=classNumber[2];
						}
						break;
						case 4:
						{
							juniorNumber=classNumber[1];
							juniorClass=classNumber[2];
						}
						break;
						
						default://默认为本科
						{
							undergraduateNumber=classNumber[1];
							undergraduateClass=classNumber[2];
						}
						break;
						}
						monthReport.setJuniorNumber(juniorNumber);
						monthReport.setJuniorClass(juniorClass);
						monthReport.setJuniorTime(juniorClass*juniorNumber);
						monthReport.setUndergraduateNumber(undergraduateNumber);
						monthReport.setUndergraduateClass(undergraduateClass);
						monthReport.setUndergraduateTime(undergraduateClass*undergraduateNumber);
						monthReport.setOtherNumber(otherNumber);
						monthReport.setOtherClass(otherClass);
						monthReport.setOtherTime(otherClass*otherNumber);
						monthReport.setRoomNumber(roomNumber);
						monthReport.setRoomClass(roomClass);
						monthReport.setRoomTime(roomClass*roomNumber);
						String guidingTeacher="";//指导教师
						if (ta.getSchoolCourse()!=null&&ta.getSchoolCourse().getUserByTeacher()!=null
								&&!ta.getSchoolCourse().getUserByTeacher().getCname().equals("")) {
							guidingTeacher=ta.getSchoolCourse().getUserByTeacher().getCname()+"["+ta.getSchoolCourse().getUserByTeacher().getUsername()+"]";
						}
						monthReport.setGuidingTeacher(guidingTeacher);
						//上课班级
						String className="";
						if (!classNumberString[3].equals("")) {
							className=classNumberString[3];
						}
						monthReport.setClassName(className);
						String note="";//备注
						monthReport.setNote(note);
						monthReports.add(monthReport);
					}
				}
			}
			
			
		}
		return monthReports;
	}
	
	/*************************************************************************************
	 * @throws ParseException 
	 * @內容：查看所有的预约的列表安排--月报报表
	 * @作者：贺子龙
	 * @日期：2015-12-29
	 *************************************************************************************/
	public List<TimetableAppointment> getTimetableAppointmentsByMonth(int termId, int status, int year, int month, int iLabCenter) 
			throws ParseException {
		String academyNumber="";
        // 如果没有获取有效的实验分室列表-根据登录用户的所属学院
        if (iLabCenter != -1) {
    		//获取选择的实验中心
        	academyNumber = shareService.getUserDetail().getSchoolAcademy().getAcademyNumber();
        }else{
        	academyNumber = shareService.getUserDetail().getSchoolAcademy().getAcademyNumber();
        }
		// 创建根据学期来查询课程；
		String sql = "select c from TimetableAppointment  c ,TimetableLabRelated e where 1=1" +
							" and e.timetableAppointment.id=c.id";
		
		if (status != -1) {
			sql+=" and c.status = " + status;
		}
		if (year!=0&&month!=0) {//根据年月确定学期
			termId=shareService.getSchoolTermByMonth(year, month).getId();
		}
		int[] weekId=shareService.getSchoolWeekByMonth(year, month);
		if (weekId[0]!=0&&weekId[1]!=0) {//限制周次
			sql+= " and ((c.startWeek<="+weekId[1]+" and c.endWeek>="+weekId[1]+")";
			sql+= " or (c.startWeek<="+weekId[0]+" and c.endWeek>="+weekId[0]+"))";
		}
		//如果排课中的实验室在该学院下，则显示在排课管理中。
		sql+=" and e.labRoom.labCenter.schoolAcademy.academyNumber like '%" + academyNumber + "%'";
		sql+= " and c.timetableSelfCourse.schoolTerm.id ="+ termId; 
		sql+=" order by c.courseCode ,c.weekday,c.timetableNumber desc";
		List<TimetableAppointment> timetableAppointmentAcademys = timetableAppointmentDAO.executeQuery(sql, 0, -1);
		
		return timetableAppointmentAcademys;
	}
	
	/*************************************************************************************
	 * @內容：根据信息得到一条月报报表记录
	 * @作者： 贺子龙
	 * @日期：2015-12-27
	 *************************************************************************************/
	public MonthReport creatMonthReport(int iLabCenter){
		MonthReport monthReport=new MonthReport();
		//系别
		String department="";
		//获取当前实验中 
		department=shareService.getUser().getSchoolAcademy().getAcademyName(); 
		//中心主任
		String responsiblePerson="";
		/*if (labCenter.getUserByCenterManager()!=null&&!labCenter.getUserByCenterManager().getCname().equals("")) {
			responsiblePerson=labCenter.getUserByCenterManager().getCname()+"["+labCenter.getUserByCenterManager().getUsername()+"]";
		}*/
		monthReport.setDepartment(department);//系别
		//monthReport.setExperimentDepartment(labCenter.getCenterName());//实验部门
		monthReport.setResponsiblePerson(responsiblePerson);//中心主任
		
		return monthReport;
	}
	
	/*************************************************************************************
	 * @內容：判断排课的人时数类型、人数、课时数
	 * @作者： 贺子龙
	 * @日期：2015-12-28
	 *************************************************************************************/
	public String[] getTimetableAppointmentType(TimetableAppointment timetableAppointment){
		boolean haveItem=true;//有无实验室项目
		boolean isOther=true;//是否为其他学时
		boolean isUndergraduate=true;//是否为本科生
		String[] result={"0","0","0","0"};
		int totalNumber=0;
		Set<TimetableItemRelated> items=timetableAppointment.getTimetableItemRelateds();
		if (items.size()!=0) {//有实验室项目
			for (TimetableItemRelated item : items) {
				if (item.getOperationItem()!=null
						&&item.getOperationItem().getCDictionaryByLpCategoryMain()!=null) {//属于当前实验室的项目卡
					//实验项目卡类型
					int typeId=item.getOperationItem().getCDictionaryByLpCategoryMain().getId();
					if (typeId==454||typeId==455||typeId==456||typeId==460||typeId==461) {
						//判断实验类型是否为基础、专业基础、专业、毕业论文和毕业设计
						isOther=false;
					}
				}
			}
		}else {
			haveItem=false;
		}
		String username="";//该排课对应的其中一名学生的学号
		//timetableStyle排课方式：1直接排课 2调整排课 3二次不分组排课 4二次分组排课 5自主排课不分组 6自主排课分组
		int style=timetableAppointment.getTimetableStyle();
		if (style==1||style==2||style==3) {
			Set<SchoolCourseStudent> students=timetableAppointment.getSchoolCourseDetail().getSchoolCourseStudents();
			List<SchoolCourseStudent> studentList=new ArrayList<SchoolCourseStudent>(students);
			if (studentList.get(0).getUserByStudentNumber()!=null) {//将第一个学生取出来
				username=studentList.get(0).getUserByStudentNumber().getUsername();
			}
			totalNumber=students.size();//统计上课学生人数
		}else if (style==4||style==6) {//分组
			Set<TimetableGroup> groups=timetableAppointment.getTimetableGroups();
			List<TimetableGroup> groupList=new ArrayList<TimetableGroup>(groups);
			if (groupList.get(0)!=null) {
				for (TimetableGroup timetableGroup : groupList) {
					Set<TimetableGroupStudents> students=timetableGroup.getTimetableGroupStudentses();
					totalNumber+=students.size();//统计上课学生人数
				}
				Set<TimetableGroupStudents> studentsII=groupList.get(0).getTimetableGroupStudentses();
				List<TimetableGroupStudents> studentList=new ArrayList<TimetableGroupStudents>(studentsII);
				if (studentList.get(0).getUser()!=null) {//将第一个学生取出来
					username=studentList.get(0).getUser().getUsername();
				}
			}
		}else if (style==5) {
			Set<TimetableCourseStudent> students=timetableAppointment.getTimetableSelfCourse().getTimetableCourseStudents();
			totalNumber=students.size();//统计上课学生人数
			if (totalNumber>0) {
				List<TimetableCourseStudent> studentList=new ArrayList<TimetableCourseStudent>(students);
				if (studentList.get(0).getUser()!=null) {//将第一个学生取出来
					username=studentList.get(0).getUser().getUsername();
				}
				
			}
			
		}else if (style==6) {
			//跟style==4归为一种情况
			}
		
		isUndergraduate=shareService.isUndergraduate(username);//判断是否为本科生
		int type=4;//专科
		if (haveItem) {
			if (isOther) {
				type=2;//其他
			}else{
				if (isUndergraduate) {
					type=3;//本科
				}
			}
		}else{
			type=1;//机房
		}
		
		result[0]=type+"";//类型1--机房  2--其他  3--本科  4--专科
		result[1]=totalNumber+"";//人数
		result[2]=(timetableAppointment.getEndClass()-timetableAppointment.getStartClass()+1)+"";//时数
		String className="";
		if (!username.equals("")) {
			User user=userDAO.findUserByPrimaryKey(username);
			if (user!=null&&user.getSchoolClasses()!=null&&!user.getSchoolClasses().getClassName().equals("")) {
				className=user.getSchoolClasses().getClassName();
			}
		}
		
		result[3]=className;
		return result;
	}
	
	
	/*************************************************************************************
	 * @description：获取所有排课相关的教师
	 * @author： 郑昕茹
	 * @date：2016-11-30
	 *************************************************************************************/
	public List<User> getAllTimetableRelatedTeachers(){
		String sql ="select u from TimetableTeacherRelated t left join t.user u where 1=1 ";
		sql += " group by t.user.username";
		return userDAO.executeQuery(sql, 0, -1);
	}
	
	/*************************************************************************************
	 * @description：根据当前三个时间维度和选课组编号查看是否有冲突的排课安排
	 * @author：罗璇
	 * @date：2017年3月14日
	 *************************************************************************************/
	public String checkStudentsByCourseCodeAndTime(HttpServletRequest request){
		//获取当前选课组安排节次
		String[] classes = request.getParameterValues("classes");
		//获取当前选课组安排周次
		String[] weeks = request.getParameterValues("weeks");
		//获取当前选课组安排星期
		String weekDay = request.getParameter("weekday");
		//获取当前选课组编号
		String courseDetailNo = request.getParameter("courseDetailNo");
		
		// 节次进行排序
		int[] intClasses = new int[classes.length];
		for (int i = 0; i < classes.length; i++) {
			intClasses[i] = Integer.parseInt(classes[i]);
		}
		Arrays.sort(intClasses);
		
		// 周次进行排序
		int[] intWeeks = new int[weeks.length];
		for (int i = 0; i < weeks.length; i++) {
			intWeeks[i] = Integer.parseInt(weeks[i]);
		}
		Arrays.sort(intWeeks);
		
		//查找所有符合条件的排课选课组
		String sql = "select ta from TimetableAppointment ta where 1=1 ";
		//节次筛选条件
		if(intClasses.length == 1){
			sql += " and (ta.startClass <= " + intClasses[0] + " and ta.endClass >= " + intClasses[0] + ") ";
		}else{
			sql += " and (ta.startClass <= " + intClasses[1] + " and ta.endClass >= " + intClasses[0] + ") ";
		}
		//周次筛选条件
		if(intWeeks.length == 1){
			sql += " and (ta.startWeek <= " + intWeeks[0] + " and ta.endWeek >= " + intWeeks[0] + ") ";
		}else{
			sql += " and (ta.startWeek <= " + intWeeks[1] + " and ta.endWeek >= " + intWeeks[0] + ") ";
		}
		//星期筛选条件
		sql += " and ta.weekday = " + weekDay;
		
		List<TimetableAppointment> timetableAppointments = timetableAppointmentDAO.executeQuery(sql, 0, -1);
		//找到所有该时间段内有课的学生
		Set<String> usernameSet = new HashSet<String>();
		for(TimetableAppointment currAppointment : timetableAppointments){//遍历完成的排课结果
			for(SchoolCourseStudent currSCT : currAppointment.getSchoolCourseDetail().getSchoolCourseStudents()){//遍历学生
				usernameSet.add(currSCT.getUserByStudentNumber().getUsername());
			}
		}
		
		//找到当前正在排课的选课组下的所有学生
		Set<String> currUsernameSet = new HashSet<String>();
		for(SchoolCourseStudent currSCT : schoolCourseDetailDAO.findSchoolCourseDetailByCourseDetailNo(courseDetailNo).getSchoolCourseStudents()){
			currUsernameSet.add(currSCT.getUserByStudentNumber().getUsername());
		}
		
		//判断当前排课学生是否在已经排好的排课组中
		if(usernameSet.size() != 0){
			Integer count = 0;
			for(String currUsername : usernameSet){
				if(currUsernameSet.contains(currUsername)){
					count += 1;
				}
			}
			return count.toString();
		}else{
			return "0";//没有冲突
		}
	}
	
	/*************************************************************************************
     * @內容：查看所有的预约的列表安排
     * @作者：贺子龙
     * @日期：2016-04-09
     *************************************************************************************/
    public List<TimetableAppointment> getTimetableAppointmentsByQueryNew(int termId,
            String courseNumber, int status, int curr, int size, int iLabCenter, HttpServletRequest request) {
        // 创建根据学期来查询课程；
        StringBuffer sql = new StringBuffer(
                "select distinct c from TimetableAppointment  c " +
                "left join c.timetableItemRelateds tir " +
                "left join c.timetableGroups tg " +
                "left join c.timetableTeacherRelateds d " +
                "left join c.schoolCourseMerge scm " +
                "left join scm.schoolCourseDetails scd where 1=1  ");
        
        if (size==-1) {// 如果是全部，则不需要关联timetableItemRelateds和timetableGroups
        	// 重新初始化sql  为了提高代码执行效率
        	sql = new StringBuffer(
                    "select distinct c from TimetableAppointment  c " +
                    "left join c.timetableTeacherRelateds d " +
                    "left join c.schoolCourseMerge scm " +
                    "left join scm.schoolCourseDetails scd where 1=1  ");
		}
        
        if (status != -1) {
            sql.append(" and c.status = " + status);
        }
        // 学期
        sql.append("and c.schoolCourseDetail.schoolTerm.id =" + termId);
        if (!courseNumber.equals("")) {
            sql.append(" and c.schoolCourseInfo.courseNumber like '" + courseNumber + "' ");
        }
        if(request.getParameter("selectMergeId") != null && !request.getParameter("selectMergeId").equals("")){
        	sql.append(" and scm.id =" + request.getParameter("selectMergeId"));
        }
        if(request.getParameter("selectCourseDetailNo") != null && !request.getParameter("selectCourseDetailNo").equals("")){
        	sql.append(" and c.schoolCourseDetail.courseDetailNo ='" + request.getParameter("selectCourseDetailNo")+"'");
        }
        /**
         * 判断是否只是老师角色
         **/
        User user = shareService.getUserDetail();
        String judge = ",";
        for (Authority authority : user.getAuthorities()) {
            judge = judge + "," + authority.getId() + ",";
        }
        // 是否为特殊权限--默认是
        boolean isSpecial = true;
        if (judge.indexOf(",2,") != -1 && judge.indexOf(",7,") == -1 && judge.indexOf(",11,") == -1 && judge.indexOf(",4,") == -1) {
        	isSpecial = false;
        }

        // 非合班
        String sqlAcademy = sql.toString();
        // 如果权限仅为教师，不为实验中心主任或超级管理员，则增加过滤
        if (!isSpecial) {
            sqlAcademy = sqlAcademy + " and (scd.user.username = '" + user.getUsername() + "'"
            		+" or d.user.username = '"+ user.getUsername() + "')";
        }
        sqlAcademy = sqlAcademy + " and (c.timetableStyle is not null and c.timetableStyle != 27 and c.timetableStyle != 24 " +
        		"and c.timetableStyle != 25 and c.timetableStyle != 26) ";
        if (size!=-1) {// 全部的时候  不需要排序
        	sqlAcademy = sqlAcademy + " order by c.courseCode, tir.operationItem.id,tg.timetableBatch.id ,tg.id,c.weekday,c.timetableNumber, tg.id desc ";
		}
        List<TimetableAppointment> timetableAppointmentAcademys = timetableAppointmentDAO.executeQuery(sqlAcademy, curr*size, size);
        
        
        // 非合班
        String sqlMerge = sql.toString();
        // 如果权限仅为教师，不为实验中心主任或超级管理员，则增加过滤
        if (!isSpecial) {
        	sqlMerge = sqlMerge + " and (scd.user.username = '" + user.getUsername() + "'"
            		+" or d.user.username = '"+ user.getUsername() + "')";
        }
        sqlMerge = sqlMerge + " and (c.timetableStyle is not null and  (c.timetableStyle = 24 or c.timetableStyle = 25 " +
        		"or c.timetableStyle = 26))";
        if (size!=-1) {// 全部的时候  不需要排序
        	sqlMerge = sqlMerge + " order by scm.courseNumber,tir.operationItem.id,tg.timetableBatch.id ,tg.id, c.weekday,c.timetableNumber, tg.id desc ";
		}
        timetableAppointmentAcademys.addAll(timetableAppointmentDAO.executeQuery(sqlMerge, curr*size, size));
       
        return timetableAppointmentAcademys;
    }
    
    
    /*************************************************************************************
     * @內容：查看计数的所有时间列表安排
     * @作者： 贺子龙
     * @日期：2016-04-09
     *************************************************************************************/
    public int getCountTimetableAppointmentsByQueryNew(int termId, String courseNumber, int status,
            int iLabCenter, HttpServletRequest request) {
        // 创建根据学期来查询课程(二次排课)；
        StringBuffer sql = new StringBuffer(
                "select count(distinct c)  from TimetableAppointment c " +
                "left join c.timetableTeacherRelateds d " +
                "left join c.schoolCourseMerge scm " +
                "left join scm.schoolCourseDetails scd where 1=1 ");
        if (status != -1) {
            sql.append(" and c.status = " + status);
        }
        // 学期
        sql.append("and c.schoolCourseDetail.schoolTerm.id =" + termId);

        //判断是否只是老师角色
        User user = shareService.getUserDetail();
        String judge = ",";
        for (Authority authority : user.getAuthorities()) {
            judge = judge + "," + authority.getId() + ",";
        }
        // 是否为特殊权限--默认是
        boolean isSpecial = true;
        if (judge.indexOf(",2,") != -1 && judge.indexOf(",7,") == -1 && judge.indexOf(",11,") == -1 && judge.indexOf(",4,") == -1) {
        	isSpecial = false;
        }

        
        if (!courseNumber.equals("")) {
            sql.append(" and c.schoolCourseInfo.courseNumber like '%" + courseNumber + "%'");

        }
        if(request.getParameter("selectMergeId") != null && !request.getParameter("selectMergeId").equals("")){
        	sql.append(" and scm.id =" + request.getParameter("selectMergeId"));
        }
        if(request.getParameter("selectCourseDetailNo") != null && !request.getParameter("selectCourseDetailNo").equals("")){
        	sql.append(" and c.schoolCourseDetail.courseDetailNo ='" + request.getParameter("selectCourseDetailNo")+"'");
        }
        // 教务排课
        String sqlAcademy = sql.toString();
        // 如果权限仅为教师，不为实验中心主任或超级管理员，则增加过滤
        if (!isSpecial) {
            sqlAcademy = sqlAcademy + " and (c.schoolCourseDetail.user.username = '" + user.getUsername() + "'"
            +" or d.user.username = '"+ user.getUsername() + "')";
        }
        sqlAcademy = sqlAcademy + " and (c.timetableStyle is not null and c.timetableStyle != 27 and c.timetableStyle != 24 and c.timetableStyle != 25 " +
        		"and c.timetableStyle != 26) ";
        int iAcademy = ((Long) timetableAppointmentDAO.createQuerySingleResult(sqlAcademy).getSingleResult())
                .intValue();

        // 合班
        String sqlMerge = sql.toString();
        // 如果权限仅为教师，不为实验中心主任或超级管理员，则增加过滤
        if (!isSpecial) {
        	sqlMerge = sqlMerge + " and (scd.user.username = '" + user.getUsername() + "'"
            		+" or d.user.username = '"+ user.getUsername() + "')";
        }
        sqlMerge = sqlMerge + " and (c.timetableStyle is not null and  " +
        		"(c.timetableStyle = 24 or c.timetableStyle = 25 or c.timetableStyle = 26)) ";
        int iMerge = ((Long) timetableAppointmentDAO.createQuerySingleResult(sqlMerge).getSingleResult())
                .intValue();
        return iAcademy + iMerge;
    }

}