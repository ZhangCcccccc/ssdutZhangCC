package net.xidlims.service.newtimetable;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.luxunsh.util.EmptyUtil;
import net.xidlims.dao.LabRoomCourseCapacityDAO;
import net.xidlims.dao.LabRoomDAO;
import net.xidlims.dao.LabRoomDeviceDAO;
import net.xidlims.dao.LabRoomDeviceReservationDAO;
import net.xidlims.dao.OperationItemDAO;
import net.xidlims.dao.SchoolCourseDAO;
import net.xidlims.dao.SchoolCourseDetailDAO;
import net.xidlims.dao.SchoolCourseMergeDAO;
import net.xidlims.dao.SchoolCourseStudentDAO;
import net.xidlims.dao.SchoolTermDAO;
import net.xidlims.dao.SchoolWeekDAO;
import net.xidlims.dao.TimetableAppointmentDAO;
import net.xidlims.dao.TimetableAppointmentSameNumberDAO;
import net.xidlims.dao.TimetableBatchDAO;
import net.xidlims.dao.TimetableBatchStudentDAO;
import net.xidlims.dao.TimetableGroupDAO;
import net.xidlims.dao.TimetableGroupStudentsDAO;
import net.xidlims.dao.TimetableLabRelatedDAO;
import net.xidlims.dao.TimetableLabRelatedDeviceDAO;
import net.xidlims.dao.TimetableTeacherRelatedDAO;
import net.xidlims.dao.UserDAO;
import net.xidlims.domain.LabRoom;
import net.xidlims.domain.LabRoomCourseCapacity;
import net.xidlims.domain.OperationItem;
import net.xidlims.domain.SchoolCourse;
import net.xidlims.domain.SchoolCourseDetail;
import net.xidlims.domain.SchoolCourseInfo;
import net.xidlims.domain.SchoolCourseMerge;
import net.xidlims.domain.SchoolCourseStudent;
import net.xidlims.domain.SchoolTerm;
import net.xidlims.domain.SchoolWeek;
import net.xidlims.domain.TimetableAppointment;
import net.xidlims.domain.TimetableAppointmentSameNumber;
import net.xidlims.domain.TimetableBatch;
import net.xidlims.domain.TimetableBatchStudent;
import net.xidlims.domain.TimetableGroup;
import net.xidlims.domain.TimetableGroupStudents;
import net.xidlims.domain.TimetableLabRelated;
import net.xidlims.domain.TimetableTeacherRelated;
import net.xidlims.domain.User;
import net.xidlims.service.common.ShareService;
import net.xidlims.service.device.LabRoomDeviceReservationService;
import net.xidlims.service.device.LabRoomDeviceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("NewTimetableCourseSchedulingService")
public class NewTimetableCourseSchedulingServiceImpl implements NewTimetableCourseSchedulingService {
	
	@PersistenceContext private EntityManager entityManager;
	@Autowired
	private SchoolCourseDetailDAO schoolCourseDetailDAO;
	@Autowired
	private TimetableAppointmentDAO timetableAppointmentDAO;
	@Autowired
	private LabRoomDeviceDAO labRoomDeviceDAO;
	@Autowired
	private TimetableLabRelatedDeviceDAO timetableLabRelatedDeviceDAO;
	@Autowired
	private LabRoomDeviceReservationDAO labRoomDeviceReservationDAO;
	@Autowired
	private ShareService shareService;
	@Autowired
	private SchoolTermDAO schoolTermDAO;
	@Autowired
	private LabRoomDeviceReservationService labRoomDeviceReservationService;
	@Autowired
	private TimetableLabRelatedDAO timetableLabRelatedDAO;
	@Autowired
	private TimetableAppointmentSameNumberDAO timetableAppointmentSameNumberDAO;
	@Autowired
	private LabRoomDeviceService labRoomDeviceService;
	@Autowired
	private  TimetableBatchDAO timetableBatchDAO;
	@Autowired
	private  TimetableGroupStudentsDAO timetableGroupStudentsDAO;
	@Autowired
	private  SchoolCourseMergeDAO schoolCourseMergeDAO;
	@Autowired
	private TimetableGroupDAO timetableGroupDAO;
	@Autowired
	private TimetableBatchStudentDAO timetableBatchStudentDAO;
	@Autowired
	private SchoolCourseStudentDAO schoolCourseStudentDAO;
	@Autowired
	private LabRoomDAO labRoomDAO;
	@Autowired
	private LabRoomCourseCapacityDAO labRoomCourseCapacityDAO;
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private SchoolWeekDAO schoolWeekDAO;
	@Autowired
	private OperationItemDAO operationItemDAO;
	@Autowired
	private TimetableTeacherRelatedDAO timetableTeacherRelatedDAO;
	@Autowired
	private  SchoolCourseDAO schoolCourseDAO;
	/*************************************************************************************
	 * @description：找到所有当前课程教师管理下的schoolcoursedetail
	 * @author：郑昕茹
	 * @date：2017-04-14
	 *************************************************************************************/
	public List<SchoolCourseDetail> findSchoolCourseDetailUnderPermission(SchoolCourseDetail schoolCourseDetail, int currpage, int pageSize, HttpServletRequest request) {
		StringBuffer sql = new StringBuffer(
				"select c from SchoolCourseDetail c join c.users cu join c.schoolCourse s " +
				"join s.schoolCourseInfo info " +
				"join info.operationOutlinesForClassId oo" +
				"  where 1=1 and oo is not null and c.weekday is null");
		int termId = shareService.getBelongsSchoolTerm(Calendar.getInstance()).getId();
		if(schoolCourseDetail.getSchoolTerm() != null && schoolCourseDetail.getSchoolTerm().getId() != null 
				&& !schoolCourseDetail.getSchoolTerm().getId().equals("")){
			termId = schoolCourseDetail.getSchoolTerm().getId();
		}
		sql.append(" and c.schoolTerm.id ="+termId);
		
		if(schoolCourseDetail.getCourseName() != null && !schoolCourseDetail.getCourseName().equals("")){
			sql.append(" and c.courseName like '%"+schoolCourseDetail.getCourseName()+"%'");
		}
		if(schoolCourseDetail.getSchoolCourse() != null && schoolCourseDetail.getSchoolCourse().getCourseNo()!= null && !schoolCourseDetail.getSchoolCourse().getCourseNo().equals("")){
			sql.append(" and c.schoolCourse.courseNo like '%"+schoolCourseDetail.getSchoolCourse().getCourseNo()+"%'");
		}
		//已该教师为课程负责人
		if(!request.getSession().getAttribute("authorityName").equals("SUPERADMIN") && 
				!request.getSession().getAttribute("authorityName").equals("LABCENTERMANAGER")){
			sql.append(" and c.user.username ='"+shareService.getUser().getUsername()+"'");	
		}
		sql.append(" and cu is not null");
		sql.append(" group by c.courseDetailNo");
		// 执行sb语句
		List<SchoolCourseDetail> schoolCourses = schoolCourseDetailDAO.executeQuery(sql.toString(), (currpage-1)*pageSize,pageSize);
		return schoolCourses;
	}
	
	
	/*************************************************************************************
	 * @description：找到我负责的所有排课的课程（课程负责人和排课教师）
	 * @author：郑昕茹
	 * @date：2017-04-14
	 *************************************************************************************/
	public List<SchoolCourseDetail> findMyScheduleList(SchoolCourseDetail schoolCourseDetail, int currpage, int pageSize) {
		StringBuffer sql = new StringBuffer(
				"select c from SchoolCourseDetail c where 1=1");
		if(schoolCourseDetail.getSchoolTerm() != null && schoolCourseDetail.getSchoolTerm().getId() != null && !schoolCourseDetail.getSchoolTerm().getId().equals("")){
			sql.append(" and c.schoolTerm.id ="+schoolCourseDetail.getSchoolTerm().getId());
		}
		if(schoolCourseDetail.getCourseName() != null && !schoolCourseDetail.getCourseName().equals("")){
			sql.append(" and c.courseName like '%"+schoolCourseDetail.getCourseName()+"%'");
		}
		if(schoolCourseDetail.getCourseNumber() != null && !schoolCourseDetail.getCourseNumber().equals("")){
			sql.append(" and c.courseNumber like '%"+schoolCourseDetail.getCourseNumber()+"%'");
		}
		//已该教师为课程负责人或者授权教师
		sql.append(" and (c.user.username ='"+shareService.getUser().getUsername()+"' or c.userByScheduleTeacher.username ='"+shareService.getUser().getUsername()+"')");
		// 执行sb语句
		List<SchoolCourseDetail> schoolCourses = schoolCourseDetailDAO.executeQuery(sql.toString(), (currpage-1)*pageSize,pageSize);
		return schoolCourses;
	}
	
	
	/*************************************************************************************
	 * @description：找到我负责的所有排课的课程（课程负责人和排课教师）合班后的结果
	 * @author：郑昕茹
	 * @date：2017-04-26
	 *************************************************************************************/
	public List<Object[]> findMyScheduleListView(HttpSession session,SchoolCourseDetail schoolCourseDetail, int currpage, int pageSize) {
		StringBuffer sql = new StringBuffer(
				"select c.* from view_school_course c " +
				// 总课时！=理论学时  就是 scd.total_class_hour!=scd.theory_hour  也就是非理论课的  全部拿出来  c.is_schedule= 1  这个不要了
				" where 1=1 and  c.total_class_hour!=c.theory_hour");
		
		if(schoolCourseDetail.getSchoolCourse()!=null&&schoolCourseDetail.getSchoolCourse().getCourseNo() != null && !schoolCourseDetail.getSchoolCourse().getCourseNo().equals("")){
			sql.append(" and c.course_no like '%"+schoolCourseDetail.getSchoolCourse().getCourseNo()+"%'");
		}
		/*if(schoolCourseDetail.getCourseNumber() != null && !schoolCourseDetail.getCourseNumber().equals("")){
			sql.append(" and c.course_no like '%"+schoolCourseDetail.getCourseNumber()+"%'");
		}*/
		//已该教师为课程负责人或者授权教师
		if(!session.getAttribute("authorityName").equals("SUPERADMIN") && !session.getAttribute("authorityName").equals("LABCENTERMANAGER"))
		{
			sql.append(" and (c.teacher_number like'%"+shareService.getUser().getUsername()+"%' " +// 课程负责老师
					" or c.schedule_teacher like '%"+shareService.getUser().getUsername()+"%'" + // 排课老师
					" or (c.schedule_teachers is not null and c.schedule_teachers like'%"+shareService.getUser().getUsername()+"%')" +
					" or (c.course_teachers is not null and c.course_teachers like'%"+shareService.getUser().getUsername()+"%')" +
							")");// 
		}
		Calendar time = Calendar.getInstance();
		SchoolTerm term = shareService.getBelongsSchoolTerm(time);
		int termId = term.getId();
		/*if(schoolCourseDetail.getSchoolTerm() == null || schoolCourseDetail.getSchoolTerm().getId() == null || schoolCourseDetail.getSchoolTerm().getId().equals("")){
		}else{
			sql.append("and c.course_name like '%" + schoolCourseDetail.getCourseName() +"'");
		}*/
		sql.append(" and c.term_id like '%"+termId+"%'");
		sql.append(" group by c.course_detail_no");
		
		sql.append(" order by c.term_id DESC");
		// 执行sb语句
		Query query= entityManager.createNativeQuery(sql.toString());
		if(pageSize != -1)
		query.setMaxResults(pageSize);
		query.setFirstResult(pageSize*(currpage-1));
       // 获取list对象
        List<Object[]> list= query.getResultList();
        return list;
	}
	
	/*************************************************************************************
	 * @description：找到学期下所有实验室的排课记录
	 * @author：郑昕茹
	 * @date：2017-04-14
	 *************************************************************************************/
	public List<TimetableLabRelated> getListLabTimetableAppointments(int roomId, int term) {
		// 创建根据学期来查询课程；
		StringBuffer sql = new StringBuffer(
				"select c from TimetableLabRelated c where 1=1 and  (c.timetableAppointment.schoolCourseDetail.schoolTerm.id ="
						+ term+")");
		StringBuffer mergeSql = new StringBuffer(
				"select c from TimetableLabRelated c where 1=1 and  (c.timetableAppointment.schoolCourseMerge.termId ="
						+ term+")");
		// 实验室查询
		mergeSql.append(" and c.labRoom.id ="+roomId);
		sql.append(" and c.labRoom.id = "+roomId);
		
		List<TimetableLabRelated> timetableLabRelateds = timetableLabRelatedDAO.executeQuery(sql.toString(), 0, -1);
		List<TimetableLabRelated> mergeTimetableLabRelateds = timetableLabRelatedDAO.executeQuery(mergeSql.toString(), 0, -1);
		
		timetableLabRelateds.addAll(mergeTimetableLabRelateds);
		return timetableLabRelateds;
	}
	
	/*************************************************************************************
	 * @description：找到学期下指定课程的所有实验室的排课记录
	 * @author：郑昕茹
	 * @date：2017-04-19
	 *************************************************************************************/
	public List<TimetableLabRelated> getListLabTimetableAppointmentsByCourseDetailNo(HttpServletRequest request, 
			int term, String courseDetailNo) {
		// 创建根据学期来查询课程；
		StringBuffer sql = new StringBuffer(
				"select c from TimetableLabRelated c where 1=1 and  c.timetableAppointment.schoolCourseDetail.schoolTerm.id ="
						+ term);
		if (request.getParameter("labroom") != null && !request.getParameter("labroom").equals("-1")) {
			sql.append(" and c.labRoom.id = " + request.getParameter("labroom"));
		}else{
			sql.append(" and c.labRoom.id = -1");
		}
		sql.append( " and c.timetableAppointment.schoolCourseDetail.courseDetailNo ='"+courseDetailNo+"'");
		List<TimetableLabRelated> timetableLabRelateds = timetableLabRelatedDAO.executeQuery(sql.toString());
		return timetableLabRelateds;
	}
	
	/*************************************************************************************
	 * @description：找到学期下指定课程的所有实验室的排课记录（根据排课类型）
	 * @author：贺子龙
	 * @date：2017-10-17
	 *************************************************************************************/
	public List<TimetableLabRelated> getTimetableAppointmentsByCourseDetailNoAndType(HttpServletRequest request, 
			int term, String courseDetailNo, Integer type){
		// 创建根据学期来查询课程；
		StringBuffer sql = new StringBuffer(
				"select c from TimetableLabRelated c where 1=1 and  c.timetableAppointment.schoolCourseDetail.schoolTerm.id ="
						+ term);
		if (request.getParameter("labroom") != null && !request.getParameter("labroom").equals("-1")) {
			sql.append(" and c.labRoom.id = " + request.getParameter("labroom"));
		}else{
			sql.append(" and c.labRoom.id = -1");
		}
		sql.append( " and c.timetableAppointment.schoolCourseDetail.courseDetailNo ='"+courseDetailNo+"'");
		if (!EmptyUtil.isObjectEmpty(type)) {
			sql.append(" and c.timetableAppointment.timetableStyle = "+type);
		}
		List<TimetableLabRelated> timetableLabRelateds = timetableLabRelatedDAO.executeQuery(sql.toString());
		return timetableLabRelateds;
	}
	
	/*************************************************************************************
	 * @description：根据courseDetailNo和type,实验室找到排课批
	 * @author：郑昕茹
	 * @date：2017-04-21
	 *************************************************************************************/
	public List<TimetableBatch> findTimetableBatchByCourseDetailNoAndType(String courseDetailNo, Integer type){
		String sql = "select t from TimetableBatch t where 1=1";
		sql += " and t.courseCode ='"+courseDetailNo+"'";
		sql += " and t.type= " + type;
		List<TimetableBatch> timetableBatchs = timetableBatchDAO.executeQuery(sql, 0, -1);
		if (timetableBatchs==null || timetableBatchs.size()==0) {
			timetableBatchs.add(new TimetableBatch());// 这个目的是防止之前写的方法get(0)报错
		}
		return timetableBatchs;
	}
	
	/*************************************************************************************
	 * @description：找到同组下周次和星期相同的排课记录
	 * @author：郑昕茹
	 * @date：2017-04-24
	 *************************************************************************************/
	public TimetableAppointment findTimetableAppointmentByGroupIdAndSameWeekAndWeekday(Integer groupId, Integer week, Integer weekday){
		String sql = "select t from TimetableAppointment t left join t.timetableGroups tg where 1=1";
		sql += " and tg.id = "+groupId;
		sql += " and t.startWeek ="+week;
		sql += " and t.weekday ="+weekday;
		List<TimetableAppointment> appointments = timetableAppointmentDAO.executeQuery(sql, 0,-1);
		if(appointments.size() > 0)return appointments.get(0);
		return null;
	}
	
	/*************************************************************************************
	 * @description：根据组号分页找学生名单
	 * @author：郑昕茹
	 * @date：2017-04-24
	 *************************************************************************************/
	public List<TimetableGroupStudents> findTimetableGroupStudentsByGroupId(Integer groupId, int page, int pageSize, HttpServletRequest request){
		String sql = "select t from TimetableGroupStudents t where 1=1 and t.timetableGroup.id="+groupId;
		if(request.getParameter("username") != null && !request.getParameter("username").equals("")){
			sql += " and t.user.username ='"+request.getParameter("username")+"'";
		}
		if (pageSize!=-1) {// 因为前台要通过这种方式获取班级，所以全查询的时候，不要做班级筛选
			if(request.getParameter("selectClass") != null && !request.getParameter("selectClass").equals("")){
				sql += " and t.user.schoolClasses.classNumber ="+request.getParameter("selectClass")+"";
			}
		}
		if(request.getParameter("academy") != null && !request.getParameter("academy").equals("")){
			sql += " and t.user.schoolAcademy.academyNumber ='"+request.getParameter("academy")+"'";
		}
		sql+=" order by t.id";
		return timetableGroupStudentsDAO.executeQuery(sql, pageSize*(page-1), pageSize);
	}
	
	/*************************************************************************************
	 * @description：根据组号分页找学生名单
	 * @author：郑昕茹
	 * @date：2017-04-24
	 *************************************************************************************/
	public List<TimetableGroupStudents> findTimetableGroupStudentsByGroupId(Integer groupId, int page, int pageSize){
		String sql = "select t from TimetableGroupStudents t where 1=1 and t.timetableGroup.id="+groupId;
		return timetableGroupStudentsDAO.executeQuery(sql, pageSize*(page-1), pageSize);
	}
	
	
	/*************************************************************************************
	 * @description：根据当前三个时间维度和选课组编号查看是否有冲突的排课安排,学生
	 * @author：罗璇
	 * @date：2017年3月14日
	 *************************************************************************************/
	public boolean checkSingleStudentByCourseCodeAndTime(Integer week, Integer weekday, Integer classes, String courseDetailNo, String student){

		//查找所有符合条件的排课选课组
		String sql = "select ta from TimetableAppointment ta left join ta.timetableGroups tg left join tg.timetableGroupStudentses s where 1=1 ";
		
		//节次
		sql += " and (ta.startClass <= " + (classes*2-1)+ " and ta.endClass >= " + (classes*2) + ") ";
		
		sql += " and (ta.startWeek <= " + week + " and ta.endWeek >= " + week + ") ";
		//星期筛选条件
		sql += " and ta.weekday = " + weekday;
		
		sql += " and s.user.username ='"+student+"'";
		List<TimetableAppointment> timetableAppointments = timetableAppointmentDAO.executeQuery(sql, 0, -1);
		if(timetableAppointments.size() == 0)return false;
		else return true;
	}
	
	
	/*************************************************************************************
	 * @description：根据当前三个时间维度和选课组编号查看是否有冲突的排课安排,获取冲突学生数量
	 * @author：罗璇
	 * @date：2017年3月14日
	 *************************************************************************************/
	public Integer getConflictStudentsByCourseCodeAndTime(Integer week, Integer weekday, Integer classes, String courseDetailNo, Set<TimetableGroupStudents> students){
		Integer count = 0;
		for(TimetableGroupStudents t:students){
			if(this.checkSingleStudentByCourseCodeAndTime(week, weekday, classes, courseDetailNo, t.getUser().getUsername()) == true){
				count++;
			}
		}
		return count;
	}
	
	
	
	/*************************************************************************************
	 * @description：根据学生找到其已参与的排课
	 * @author：郑昕茹
	 * @date：2017-04-24
	 *************************************************************************************/
	public Map<String, Integer> getConflictTimetableAndNum(Set<TimetableGroupStudents> students, Integer[] chosenWeeks){
		int currterm = shareService.getBelongsSchoolTerm(Calendar.getInstance()).getId();
		String chosenWeek = ",";
		if(chosenWeeks!= null && chosenWeeks.length != 0){
			for(Integer week:chosenWeeks){
				chosenWeek += week+",";
			}
		}
		Map<String, Integer> map = new HashMap<String, Integer>();
		
		for(TimetableGroupStudents tgs:students){
			Set<String> set = new HashSet<String>();
			String sql = "select ta from TimetableAppointment ta , TimetableGroupStudents s left join ta.timetableGroups tg where 1=1 and s.timetableGroup.id = tg.id";
			sql += " and s.user.username ='"+tgs.getUser().getUsername()+"'";
			List<TimetableAppointment> appointments = timetableAppointmentDAO.executeQuery(sql, 0, -1);
			for(TimetableAppointment ta:appointments){
				// 判断当前学期
				if (ta.getSchoolCourseDetail()==null || 
						(ta.getSchoolCourseDetail()!=null && ta.getSchoolCourseDetail().getSchoolTerm().getId()!=currterm)) {
					continue;
				}
				if(ta.getTimetableAppointmentSameNumbers() == null || ta.getTimetableAppointmentSameNumbers() != null && ta.getTimetableAppointmentSameNumbers().size() == 0){
					for(int i = ta.getStartWeek(); i <= ta.getEndWeek(); i++){
						if(chosenWeek.equals(",")){
							for(int j = (ta.getStartClass() + 1)/2; j <= (ta.getEndClass()/2); j++){
								set.add((i+"-"+ta.getWeekday()+"-"+j).toString());
							}
						}
						else{
							if(chosenWeek.indexOf((","+Integer.toString(i)+",")) != -1){
								for(int j = (ta.getStartClass() + 1)/2; j <= (ta.getEndClass()/2); j++){
									set.add((i+"-"+ta.getWeekday()+"-"+j).toString());
								}
							}
						}
					}
				}
				else{
					for(TimetableAppointmentSameNumber tas:ta.getTimetableAppointmentSameNumbers()){
						for(int i = tas.getStartWeek(); i <= tas.getEndWeek(); i++){
							if(chosenWeek.equals(",")){
								for(int j = (tas.getStartClass() + 1)/2; j <= (tas.getEndClass()/2); j++){
									set.add((i+"-"+tas.getWeekday()+"-"+j).toString());
								}
							}
							else{
								if(chosenWeek.indexOf((","+Integer.toString(i)+",")) != -1){
									for(int j = (tas.getStartClass() + 1)/2; j <= (tas.getEndClass()/2); j++){
										set.add((i+"-"+tas.getWeekday()+"-"+j).toString());
									}
								}
							}
						}
					}
				}
			}
			
			String sqlAdmin = "select scd from SchoolCourseDetail scd, SchoolCourseStudent scs where 1=1 and scs.schoolCourseDetail.courseDetailNo = scd.courseDetailNo";

			sqlAdmin += " and scs.userByStudentNumber.username = '"+ tgs.getUser().getUsername()+"'";
			sqlAdmin += " and scd.schoolTerm.id ="+currterm;
			// 增加必修限制  or scd.courseCategory is null 此处先去掉，需要数据处理把所有的null给赋值
			sqlAdmin += " and (scd.courseCategory like '必修')";
			sqlAdmin += "  group by scd.courseDetailNo";
			List<SchoolCourseDetail> details = schoolCourseDetailDAO.executeQuery(sqlAdmin, 0, -1);
			for(SchoolCourseDetail scd:details){
					if(scd.getStartWeek() != null && scd.getEndWeek() != null && scd.getStartClass() != null && scd.getEndClass() != null && scd.getWeekday() != null)
					for(int i = scd.getStartWeek(); i <= scd.getEndWeek(); i++){
						if(chosenWeek.equals(",")){
							for(int j = (scd.getStartClass() + 1)/2; j <= (scd.getEndClass()/2); j++){
								set.add((i+"-"+scd.getWeekday()+"-"+j).toString());
							}
						}
						else{
							if(chosenWeek.indexOf((","+Integer.toString(i)+",")) != -1){
								for(int j = (scd.getStartClass() + 1)/2; j <= (scd.getEndClass()/2); j++){
									if(map.get((i+"-"+scd.getWeekday()+"-"+j).toString()) != null)
									{
										set.add((i+"-"+scd.getWeekday()+"-"+j).toString());
									}
									else{
										set.add((i+"-"+scd.getWeekday()+"-"+j).toString());
									}
								}
							}
						}
					}
			}
			for(String s:set){
				if(map.get(s) == null){
					map.put(s, 1);
				}
				else{
					map.put(s, map.get(s)+1);
				}
			}
		}
		return map;
	}
	

	
	
	/*************************************************************************************
	 * @description：找到学期下和班课程下所有课程的所有实验室的排课记录
	 * @author：郑昕茹
	 * @date：2017-04-26
	 *************************************************************************************/
	public List<TimetableLabRelated> getListLabTimetableAppointmentsByMergeId(HttpServletRequest request, 
			int term, Integer mergeId) {
		
		// 创建根据学期来查询课程；
		StringBuffer sql = new StringBuffer(
				"select c from TimetableLabRelated c where 1=1 and  c.timetableAppointment.schoolCourseMerge.termId ="
						+ term);
		if (request.getParameter("labroom") != null && !request.getParameter("labroom").equals("-1")) {
			sql.append(" and c.labRoom.id = " + request.getParameter("labroom"));
		}else{
			sql.append(" and c.labRoom.id = -1");
		}
		sql.append( " and c.timetableAppointment.schoolCourseMerge.id = "+mergeId);
		List<TimetableLabRelated> timetableLabRelateds = timetableLabRelatedDAO.executeQuery(sql.toString());
		return timetableLabRelateds;
	}
	
	/*************************************************************************************
	 * @description：找到学期下和班课程下所有课程的所有实验室的排课记录（根据排课类型）
	 * @author：郑昕茹
	 * @date：2017-04-26
	 *************************************************************************************/
	public List<TimetableLabRelated> getTimetableAppointmentsByMergeIdAndType(HttpServletRequest request, 
			int term, Integer mergeId, Integer type){
		// 创建根据学期来查询课程；
		StringBuffer sql = new StringBuffer(
				"select c from TimetableLabRelated c where 1=1 and  c.timetableAppointment.schoolCourseMerge.termId ="
						+ term);
		if (request.getParameter("labroom") != null && !request.getParameter("labroom").equals("-1")) {
			sql.append(" and c.labRoom.id = " + request.getParameter("labroom"));
		}else{
			sql.append(" and c.labRoom.id = -1");
		}
		sql.append( " and c.timetableAppointment.schoolCourseMerge.id = "+mergeId);
		if (!EmptyUtil.isObjectEmpty(type)) {
			sql.append(" and c.timetableAppointment.timetableStyle = "+type);
		}		
		List<TimetableLabRelated> timetableLabRelateds = timetableLabRelatedDAO.executeQuery(sql.toString());
		return timetableLabRelateds;
	}
	
	/*************************************************************************************
	 * @description：找到学期下当前登录人（学生）的所有排课,根据学期和课程有查询
	 * @author：郑昕茹
	 * @date：2017-04-27
	 *************************************************************************************/
	public List<TimetableAppointment> findMyTimetableStudentsByTermAndCourseName(Integer termId, String courseName){
		List<TimetableAppointment> appointments = new LinkedList<TimetableAppointment>();
		if(termId == -1){
			termId = shareService.getBelongsSchoolTerm(Calendar.getInstance()).getId();
		}
		String sql = "select t from TimetableGroupStudents t where  t.user.username like '"
				+ shareService.getUserDetail().getUsername() + "' ";
		List<TimetableGroupStudents> tass = timetableGroupStudentsDAO.executeQuery(sql, 0, -1);
		if (tass.size() > 0) {
			for (TimetableGroupStudents ta : tass) {
				for (TimetableAppointment te : ta.getTimetableGroup().getTimetableAppointments()) {
					// 不合班
					if ((te.getTimetableStyle() == 21 || te.getTimetableStyle() == 22 || te.getTimetableStyle() == 23 || te.getTimetableStyle() == 28) && te.getStatus() == 1
							&& te.getSchoolCourseDetail() != null && te.getSchoolCourseDetail().getSchoolTerm().getId() == termId) {
						if(!courseName.equals("")  && courseName.equals(te.getSchoolCourseDetail().getCourseName()))
						{
							appointments.add(te);
						}
						if(courseName.equals("") ){
							appointments.add(te);
						}
					}
					// 合班
					if ((te.getTimetableStyle() == 25  || te.getTimetableStyle() == 24  || te.getTimetableStyle() == 26) && te.getStatus() == 1 && te.getSchoolCourseMerge() != null
							&& te.getSchoolCourseMerge().getTermId() == termId) {
						if(!courseName.equals("")  && courseName.equals(te.getSchoolCourseDetail().getCourseName()))
						{
							appointments.add(te);
						}
						if(courseName.equals("") ){
							appointments.add(te);
						}	
					}
				}
			}
		}
		return appointments;
	}
	

	/*************************************************************************************
	 * @description：根据courseDetailNo和type和实验项目找到排课批
	 * @author：郑昕茹
	 * @date：2017-04-28
	 *************************************************************************************/
	public TimetableBatch findTimetableBatchByCourseDetailNoAndTypeAndItemId(String courseDetailNo, Integer type, Integer itemId){
		String sql = "select t from TimetableBatch t where 1=1";
		sql += " and t.courseCode ='"+courseDetailNo+"'";
		sql += " and t.type= " + type;
		// 考虑到有些批次没有项目
		if (itemId != -1) {
			sql += " and t.operationItem.id = "+itemId;
		}
		List<TimetableBatch> timetableBatchs = timetableBatchDAO.executeQuery(sql, 0, -1);
		if(timetableBatchs != null && timetableBatchs.size() > 0)return timetableBatchs.get(0);
		return null;
	}
	
	
	
	/*************************************************************************************
	 * @description：找到学期下登录人可以选课的所有组(非合班)
	 * @author：郑昕茹
	 * @date：2017-04-28
	 *************************************************************************************/
	public List<TimetableGroup> findTimetableGroupsCanSelectByCurrUser() {
		String sql = "select t from TimetableGroup t left join t.timetableAppointments ta left join ta.schoolCourseDetail scd left join scd.schoolCourseStudents scs where 1=1";
		sql += " and ta.timetableStyle = 21";
		sql += " and t.timetableStyle = 21";
		sql += " and scs.userByStudentNumber ='"+ shareService.getUser().getUsername()+"'";
		sql += " and t.timetableBatch.operationItem is not null";
		sql += " group by t.id"; 
		sql += " order by scd.courseDetailNo, t.timetableBatch.id, t.timetableBatch.operationItem.id,t.id";
		return timetableGroupDAO.executeQuery(sql, 0, -1);
	}
	
	
	/*************************************************************************************
	 * @description：找到学期下登录人可以选课的所有组(合班)
	 * @author：郑昕茹
	 * @date：2017-05-02
	 *************************************************************************************/
	public List<TimetableGroup> findTimetableGroupsCanSelectInMergeCourseByCurrUser() {
		String sql = "select t from TimetableGroup t left join t.timetableAppointments ta left join ta.schoolCourseMerge scm left join scm.schoolCourseDetails scd left join scd.schoolCourseStudents scs where 1=1";
		sql += " and ta.timetableStyle = 24";
		sql += " and t.timetableStyle = 24";
		sql += " and scs.userByStudentNumber ='"+ shareService.getUser().getUsername()+"'";
		sql += " and t.timetableBatch.operationItem is not null";
		sql += " group by t.id"; 
		sql += " order by scm.id, t.timetableBatch.id, t.timetableBatch.operationItem.id,t.id";
		return timetableGroupDAO.executeQuery(sql, 0, -1);
	}
	
	
	/*************************************************************************************
	 * @description：找到批次找到其下所有排课
	 * @author：郑昕茹
	 * @date：2017-04-30
	 *************************************************************************************/
	public List<TimetableAppointment> findTimetableAppointmentsByBatchId(Integer batchId) {
		String sql = "select t from TimetableAppointment t left join t.timetableGroups tg where 1=1";
		sql += " and tg.timetableBatch.id ="+batchId;
		return timetableAppointmentDAO.executeQuery(sql, 0,-1);
	}
	
	
	/*************************************************************************************
	 * @description：根据批次和当前学生找到批次学生记录
	 * @author：郑昕茹
	 * @date：2017-04-30
	 *************************************************************************************/
	public List<TimetableBatchStudent> findTimetableBatchStudentsByBatchId(Integer batchId) {
		String sql = "select t from TimetableBatchStudent t where 1=1 ";
		sql += " and t.timetableBatch.id ="+ batchId;
		sql += " and t.user.username ='"+shareService.getUser().getUsername()+"'";
		return timetableBatchStudentDAO.executeQuery(sql, 0,-1);
	}
	
	/*************************************************************************************
	 * @description：判断学生是否有冲突的选课
	 * @author：郑昕茹
	 * @date：2017-04-30
	 *************************************************************************************/
	public boolean checkSingleStudentTime(Integer week, Integer weekday, Integer startClass, Integer endClass, String student){

		//查找所有符合条件的排课选课组
		String sql = "select ta from TimetableAppointment ta left join ta.timetableGroups tg left join tg.timetableGroupStudentses s where 1=1 ";
		
		//节次
		sql += " and (ta.startClass <= " + startClass+ " and ta.endClass >= " + endClass + ") ";
		
		sql += " and (ta.startWeek <= " + week + " and ta.endWeek >= " + week + ") ";
		//星期筛选条件
		sql += " and ta.weekday = " + weekday;
		
		sql += " and s.user.username ='"+student+"'";
		List<TimetableAppointment> timetableAppointments = timetableAppointmentDAO.executeQuery(sql, 0, -1);
		if(timetableAppointments.size() == 0)return false;
		else return true;
	}
	
	
	/*************************************************************************************
	 * @description：根据组号和当前学生找到组号学生记录
	 * @author：郑昕茹
	 * @date：2017-04-30
	 *************************************************************************************/
	public List<TimetableGroupStudents> findTimetableGroupStudentsByGroupId(Integer groupId) {
		String sql = "select t from TimetableGroupStudents t where 1=1 ";
		sql += " and t.timetableGroup.id ="+ groupId;
		sql += " and t.user.username ='"+shareService.getUser().getUsername()+"'";
		return timetableGroupStudentsDAO.executeQuery(sql, 0,-1);
	}
	
	
	/*************************************************************************************
	 * @description：找到批次下的所有组，并排序
	 * @author：郑昕茹
	 * @date：2017-05-02
	 *************************************************************************************/
	public List<TimetableGroup> findTimetableGroupsByBacthId(Integer batchId){
		String sql = "select t from TimetableGroup t where 1=1";
		sql += " and t.timetableBatch.id ="+batchId;
		sql += " order by t.id";
		return timetableGroupDAO.executeQuery(sql, 0,-1);
	}
	
	/*************************************************************************************
	 * @description：判断学生是否有冲突的选课,不包含本身
	 * @author：郑昕茹
	 * @date：2017-05-02
	 *************************************************************************************/
	public boolean checkSingleStudentTimeExceptSelfAppointment(String student, Integer appointmentId){
		TimetableAppointment timetableAppointment = timetableAppointmentDAO.findTimetableAppointmentById(appointmentId);
		List<TimetableAppointment> timetableAppointments =new LinkedList<TimetableAppointment>();
		int currterm = shareService.getBelongsSchoolTerm(Calendar.getInstance()).getId();
		if(timetableAppointment.getTimetableAppointmentSameNumbers() == null || timetableAppointment.getTimetableAppointmentSameNumbers() != null && timetableAppointment.getTimetableAppointmentSameNumbers().size() == 0)
		{
			//查找所有符合条件的排课选课组
			String sql = "select ta from TimetableAppointment ta left join ta.timetableGroups tg left join tg.timetableGroupStudentses s where 1=1 ";
			
			//节次
			sql += " and (ta.startClass <= " + timetableAppointment.getStartClass()+ " and ta.endClass >= " + timetableAppointment.getEndClass() + ") ";
			
			sql += " and (ta.startWeek <= " + timetableAppointment.getStartWeek() + " and ta.endWeek >= " + timetableAppointment.getEndWeek() + ") ";
			//星期筛选条件
			sql += " and ta.weekday = " + timetableAppointment.getWeekday();
			sql += " and ta.id != "+appointmentId;
			sql += " and s.user.username ='"+student+"'";
			sql += " group by ta.id";
			
			// 判断当前学期
			for(TimetableAppointment ta:timetableAppointmentDAO.executeQuery(sql, 0, -1)){
				if (ta.getSchoolCourseDetail()!=null && ta.getSchoolCourseDetail().getSchoolTerm().getId()==currterm) {
					return true;
				}
			}
			
			//查找所有符合条件的排课选课组
			sql = "select ta from TimetableAppointment ta left join ta.timetableGroups tg left join tg.timetableGroupStudentses s left join ta.timetableAppointmentSameNumbers tas where 1=1 ";
					
			//节次
			sql += " and (tas.startClass <= " + timetableAppointment.getStartClass()+ " and tas.endClass >= " + timetableAppointment.getEndClass() + ") ";
			
			sql += " and (tas.startWeek <= " + timetableAppointment.getStartWeek() + " and tas.endWeek >= " + timetableAppointment.getEndWeek() + ") ";
			//星期筛选条件
			sql += " and tas.weekday = " + timetableAppointment.getWeekday();
			sql += " and ta.id != "+appointmentId;
			sql += " and s.user.username ='"+student+"'";
			sql += " group by ta.id";
			
			// 判断当前学期
			for(TimetableAppointment ta:timetableAppointmentDAO.executeQuery(sql, 0, -1)){
				if (ta.getSchoolCourseDetail()!=null && ta.getSchoolCourseDetail().getSchoolTerm().getId()==currterm) {
					return true;
				}
			}
			
			//查找所有符合条件的排课选课组
			sql = "select scd from SchoolCourseDetail scd ,SchoolCourseStudent scs where 1=1 and scs.schoolCourseDetail.courseDetailNo = scd.courseDetailNo";
			
			//节次
			sql += " and (scd.startClass <= " + timetableAppointment.getStartClass()+ " and scd.endClass >= " +timetableAppointment.getEndClass() + ") ";
			
			sql += " and (scd.startWeek <= " + timetableAppointment.getStartWeek() + " and scd.endWeek >= " + timetableAppointment.getEndWeek() + ") ";
			//星期筛选条件
			sql += " and scd.weekday = " + timetableAppointment.getWeekday();
			sql += " and scs.userByStudentNumber.username ='"+student+"'";
			sql += " group by scd.courseDetailNo";
			System.out.print(schoolCourseDetailDAO.executeQuery(sql, 0, -1).size()+" ");
			if(schoolCourseDetailDAO.executeQuery(sql, 0, -1).size() != 0)return true;
		}
		else{
			for(TimetableAppointmentSameNumber tas:timetableAppointment.getTimetableAppointmentSameNumbers()){
				//查找所有符合条件的排课选课组
				String sql = "select ta from TimetableAppointment ta left join ta.timetableGroups tg left join tg.timetableGroupStudentses s where 1=1 ";
				
				//节次
				sql += " and (ta.startClass <= " + tas.getStartClass()+ " and ta.endClass >= " + tas.getEndClass() + ") ";
				
				sql += " and (ta.startWeek <= " + tas.getStartWeek() + " and ta.endWeek >= " + tas.getEndWeek() + ") ";
				//星期筛选条件
				sql += " and ta.weekday = " + tas.getWeekday();
				sql += " and ta.id != "+appointmentId;
				sql += " and s.user.username ='"+student+"'";
				sql += " group by ta.id";
				
				// 判断当前学期
				for(TimetableAppointment ta:timetableAppointmentDAO.executeQuery(sql, 0, -1)){
					if (ta.getSchoolCourseDetail()!=null && ta.getSchoolCourseDetail().getSchoolTerm().getId()==currterm) {
						return true;
					}
				}
				
				//查找所有符合条件的排课选课组
				sql = "select ta from TimetableAppointment ta left join ta.timetableGroups tg left join tg.timetableGroupStudentses s left join ta.timetableAppointmentSameNumbers tas where 1=1 ";
						
				//节次
				sql += " and (tas.startClass <= " + tas.getStartClass()+ " and tas.endClass >= " + tas.getEndClass() + ") ";
				
				sql += " and (tas.startWeek <= " + tas.getStartWeek() + " and tas.endWeek >= " + tas.getEndWeek() + ") ";
				//星期筛选条件
				sql += " and tas.weekday = " + tas.getWeekday();
				sql += " and ta.id != "+appointmentId;
				sql += " and s.user.username ='"+student+"'";
				sql += " group by ta.id";
				// 判断当前学期
				for(TimetableAppointment ta:timetableAppointmentDAO.executeQuery(sql, 0, -1)){
					if (ta.getSchoolCourseDetail()!=null && ta.getSchoolCourseDetail().getSchoolTerm().getId()==currterm) {
						return true;
					}
				}
				
				//查找所有符合条件的排课选课组
				sql = "select scd from SchoolCourseDetail scd ,SchoolCourseStudent scs where 1=1 and scs.schoolCourseDetail.courseDetailNo = scd.courseDetailNo";
				
				//节次
				sql += " and (scd.startClass <= " + tas.getStartClass()+ " and scd.endClass >= " +tas.getEndClass() + ") ";
				
				sql += " and (scd.startWeek <= " + tas.getStartWeek() + " and scd.endWeek >= " + tas.getEndWeek() + ") ";
				//星期筛选条件
				sql += " and scd.weekday = " + tas.getWeekday();
				sql += " and scs.userByStudentNumber.username ='"+student+"'";
				sql += " group by scd.courseDetailNo";
				if(schoolCourseDetailDAO.executeQuery(sql, 0, -1).size() != 0)return true;
			}
		}
		return false;
	}
	
	

	/*************************************************************************************
	 * @description：根据组号分页找学生名单(未排入任何一组的学生)
	 * @author：郑昕茹
	 * @date：2017-05-03
	 *************************************************************************************/
	public List<SchoolCourseStudent> findSchoolCourseStudentsNotInBatchGroup(Integer batchId, int page, int pageSize,Integer isMerge, String courseCode,HttpServletRequest request){
		String sql = "select scs from SchoolCourseStudent scs where 1=1 ";
		if(isMerge == 1){
			sql += " and scs.schoolCourseDetail.schoolCourseMerge.id="+courseCode;
		}
		else{
			sql += " and scs.schoolCourseDetail.courseDetailNo ='"+courseCode+"'";
		}
		sql += " and scs.userByStudentNumber.username not in ";
		sql += " (select tgs.user.username from TimetableGroupStudents tgs where 1=1 and tgs.timetableGroup.timetableBatch.id="+batchId+")";
		if(request.getParameter("username") != null && !request.getParameter("username").equals("")){
			sql += " and scs.user.username ='"+request.getParameter("username")+"'";
		}
		if(request.getParameter("selectClass") != null && !request.getParameter("selectClass").equals("")){
			sql += " and scs.user.schoolClasses.id ="+request.getParameter("selectClass")+"";
		}
		if(request.getParameter("academy") != null && !request.getParameter("academy").equals("")){
			sql += " and scs.user.schoolAcademy.academyNumber ='"+request.getParameter("academy")+"'";
		}
		sql += " group by scs.userByStudentNumber.username";
		return schoolCourseStudentDAO.executeQuery(sql, pageSize*(page-1), pageSize);
	}
	
	
	/*************************************************************************************
	 * @description：根据courseCode找到该课程下的所有批次
	 * @author：郑昕茹
	 * @date：2017-05-03
	 *************************************************************************************/
	public List<TimetableBatch> findTimetableBatchsByCourseCodeAndType(String courseCode){
		String sql ="select t from TimetableBatch t where 1=1";
		sql += "and t.courseCode ='"+courseCode+"'";
		return timetableBatchDAO.executeQuery(sql, 0,-1);
	}
	
	/*************************************************************************************
	 * @description：找到学期下登录人可以选课的所有组(全校公选课)
	 * @author：郑昕茹
	 * @date：2017-05-03
	 *************************************************************************************/
	public List<TimetableGroup> findTimetableGroupsCanSelectInPublicCourse() {
		String sql = "select t from TimetableGroup t left join t.timetableAppointments ta left join ta.schoolCourseDetail scd left join scd.schoolCourseStudents scs where 1=1";
		sql += " and scs.userByStudentNumber.username ='"+shareService.getUser().getUsername()+"'";
		sql += " and ta.timetableStyle = 23";
		sql += " group by t.id"; 
		return timetableGroupDAO.executeQuery(sql, 0, -1);
	}
	
	/*************************************************************************************
	 * @description：根据courseDetailNo找到其下所有公选课排课
	 * @author：郑昕茹
	 * @date：2017-05-03
	 *************************************************************************************/
	public List<TimetableAppointment> findTimetableAppointmentsByCourseDetailNo(String courseDetailNo) {
		String sql = "select t from TimetableAppointment t left join t.timetableGroups tg  where 1=1";
		sql += " and t.schoolCourseDetail.courseDetailNo ='"+courseDetailNo+"'" ;
		sql += " and t.timetableStyle = 23";
		sql += " group by t.id";
		sql += " order by tg.id";
		return timetableAppointmentDAO.executeQuery(sql, 0,-1);
	}
	
	
	/************************************************************
	 * @ 获取可排的实验分室列表 @ 作者：魏诚 @ 日期：2014-07-24
	 ************************************************************/
	public Map<Integer, String> getLabRoomMap() {
		Map<Integer, String> labRoomMap = new LinkedHashMap<Integer, String>(0);
		// 如果没有获取有效的实验分室列表-根据登录用户的所属学院
		StringBuffer sql = new StringBuffer("select c from LabRoom c "
				//+ "where c.CDictionaryByLabRoom.CCategory='c_lab_room_type' and c.CDictionaryByLabRoom.CNumber = '1' and c.labAnnex.labCenter.schoolAcademy.academyNumber like '"
				+ "where c.CDictionaryByLabRoom.CCategory='c_lab_room_type' and c.CDictionaryByLabRoom.CNumber = '1'"
				+ " and (c.isUsed=1 or c.isUsed=null)");

		List<LabRoom> list = labRoomDAO.executeQuery(sql.toString());
		// 遍历实验分室
		for (LabRoom labRoom : list) {
			labRoomMap.put(labRoom.getId(),labRoom.getLabRoomName()+"("+labRoom.getLabRoomAddress()+")");
		}
		return labRoomMap;
	}
	
	/**
	 * Description 根据课程获取容量匹配的实验室
	 * 
	 * @param courseDetailNo
	 * @author 陈乐为
	 * @date 2017-9-25
	 */
	public Map<Integer, String> getLabRoomMapByCourseDetailNo(String courseDetailNo) {
		Map<Integer, String> labRoomMap = new LinkedHashMap<Integer, String>(0);
		StringBuffer hql = new StringBuffer("select c from LabRoomCourseCapacity c where c.schoolCourseDetail.courseDetailNo='"+courseDetailNo+"'");
		List<LabRoomCourseCapacity> labRoomCourseCapacities = labRoomCourseCapacityDAO.executeQuery(hql.toString());
		
		//遍历实验室
		for(LabRoomCourseCapacity labRoomCourseCapacity : labRoomCourseCapacities) {
			labRoomMap.put(labRoomCourseCapacity.getLabRoom().getId(), 
					labRoomCourseCapacity.getLabRoom().getLabRoomName()+"("+labRoomCourseCapacity.getLabRoom().getLabRoomAddress()+")");
		}
		
		return labRoomMap;
	}
	
	/*************************************************************************************
	 * @description：根据courseDetailNo和labId找到其分批的容量
	 * @author：郑昕茹
	 * @date：2017-05-05
	 *************************************************************************************/
	public LabRoomCourseCapacity findLabRoomCourseCapacityByCourseDetailNoAndLabId(String courseDetailNo, Integer labId) {
		String sql = "select l from LabRoomCourseCapacity l where 1=1";
		sql += " and l.labRoom.id ="+labId;
		sql += " and l.schoolCourseDetail.courseDetailNo ='"+courseDetailNo+"'";
		List<LabRoomCourseCapacity> capacities = labRoomCourseCapacityDAO.executeQuery(sql, 0,-1);
		if(capacities != null && capacities.size() != 0){
			return capacities.get(0);
		}
		return null;
	}
	
	/*************************************************************************************
	 * @description：根据合班课程的id找到其下所有学生
	 * @author：郑昕茹
	 * @date：2017-05-07
	 *************************************************************************************/
	public List<SchoolCourseStudent> findSchoolCourseStudentsByMergeId(Integer mergeId){
		String sql = "select s from SchoolCourseStudent s where 1=1";
		sql += " and s.schoolCourseDetail.schoolCourseMerge.id ="+mergeId;
		sql += " group by s.userByStudentNumber.username";
		return schoolCourseStudentDAO.executeQuery(sql, 0, -1);
	}

	/*************************************************************************************
	 * @description：根据合班课程的id找到其下所有学生数量
	 * @author：贺子龙
	 * @date：2017-10-04
	 *************************************************************************************/
	public int countSchoolCourseStudentsByMergeId(Integer mergeId){
		String sql = "select count(DISTINCT s.userByStudentNumber.username) from SchoolCourseStudent s where 1=1";
		sql += " and s.schoolCourseDetail.schoolCourseMerge.id ="+mergeId;
		return ((Long) schoolCourseStudentDAO.createQuerySingleResult(sql).getSingleResult()).intValue();
	}
	
	/*************************************************************************************
	 * @description：根据courseDetailNo找到其下所有学生
	 * @author：郑昕茹
	 * @date：2017-05-07
	 *************************************************************************************/
	public List<User> findUsersByCourseDetailNo(String courseDetailNo){
		String sql = "select u from User u, SchoolCourseStudent scs where 1=1 and scs.userByStudentNumber.username = u.username";
		sql += " and scs.schoolCourseDetail.courseDetailNo ='"+courseDetailNo+"'";
		sql += " group by u.username";
		return userDAO.executeQuery(sql, 0, -1);
	}
	
	/*************************************************************************************
	 * @description：根据groupId找到其下所有学生
	 * @author：郑昕茹
	 * @date：2017-05-07
	 *************************************************************************************/
	public List<User> findUsersByGroupId(Integer groupId){
		String sql = "select u from User u, TimetableGroupStudents tgs where 1=1 and tgs.user.username = u.username";
		sql += " and tgs.timetableGroup.id ="+groupId;
		sql += " group by u.username";
		return userDAO.executeQuery(sql, 0, -1);
	}
	
	/*************************************************************************************
	 * @description：根据周次，星期，学期找到对应的schoolWeek
	 * @author：郑昕茹
	 * @date：2017-05-09
	 *************************************************************************************/
	public SchoolWeek findSchoolWeekByWeekAndWeekdayAndTerm(Integer week, Integer weekday, Integer termId){
		String sql = "select s from SchoolWeek s where 1=1";
		sql += " and s.week ="+week;
		sql += " and s.weekday ="+weekday;
		sql += " and s.schoolTerm.id="+termId;
		List<SchoolWeek> weeks = schoolWeekDAO.executeQuery(sql,0,-1);
		if(weeks != null && weeks.size() != 0)return weeks.get(0);
		return null;
	}
	
	
	/*************************************************************************************
	 * @description：根据mergeId找到其下所有学生
	 * @author：郑昕茹
	 * @date：2017-05-10
	 *************************************************************************************/
	public List<String> findUsersNameByMergeId(String mergeId, String classNumber){
		String sql = "select scs.student_number from  school_course_merge scm left join school_course_detail scd on scd.merge_id = scm.id and scm.id ="+ mergeId;
		sql += " left join school_course_student scs on scs.course_detail_no = scd.course_detail_no";
		sql += " where scm.id = " +mergeId;
		
		if(classNumber!=null && !classNumber.trim().equals("")){
			classNumber = classNumber.trim();
			sql+=" And scs.classes_number="+classNumber;
		}
		sql+=" group by scs.student_number";
		
		Query query= entityManager.createNativeQuery(sql.toString());
        List<String> list= query.getResultList();
        return list;
	}
	
	
	/*************************************************************************************
	 * @description：根据groupId找到其下所有学生(username)
	 * @author：郑昕茹
	 * @date：2017-05-10
	 *************************************************************************************/
	public List<String> findUsernamesByGroupId(Integer groupId){
		String sql = "select tgs.username from timetable_group tg left join timetable_group_students tgs on tgs.group_id = tg.id";
		sql += " where tg.id = "+ groupId;
		sql += " group by tgs.username";
		Query query= entityManager.createNativeQuery(sql.toString());
        List<String> list= query.getResultList();
        return list;
	}
	
	
	/*************************************************************************************
	 * @description：根据courseDetailNo找到其下所有学生(username)
	 * @author：郑昕茹
	 * @date：2017-05-10
	 *************************************************************************************/
	public List<String> findUsernamesByCourseDetailNo(String courseDetailNo,String classNumber){
		String sql = "select scs.student_number from school_course_detail scd " +
				"left join school_course_student scs on scs.course_detail_no = scd.course_detail_no " +
				"and scd.course_detail_no ='"+courseDetailNo +"'";
		sql += " where scd.course_detail_no ='"+courseDetailNo+"'";
		classNumber = classNumber.trim();
		if(classNumber!=null && !classNumber.equals("")){
			sql+=" And scs.classes_number="+classNumber;
		}
		sql+=" group by scs.student_number";
		Query query= entityManager.createNativeQuery(sql.toString());
        List<String> list= query.getResultList();
        return list;
	}
	
	@Transactional(readOnly = false)  
    public void batchInsertAndUpdate(List list) {  
            int size =  list.size();  
        for (int i = 0; i < size; i++) {  
        	entityManager.merge(list.get(i));
            if (i % 1000 == 0 || i==(size-1)) { // 每1000条数据执行一次，或者最后不足1000条时执行  
            	entityManager.flush();  
            	entityManager.clear();  
            }  
        }  
    }
	/*************************************************************************************
	 * @description：根据courseDetailNo找到其下所有学生(username)
	 * @author：郑昕茹
	 * @date：2017-05-10
	 *************************************************************************************/
	public List<String> findUsernamesByCourseDetailNo(String courseDetailNo){
		String sql = "select scs.student_number from school_course_student scs";
		sql += " where scs.course_detail_no ='"+courseDetailNo+"'";
		sql+=" group by scs.student_number";
		sql+=" order by scs.classes_number, scs.student_number";
		Query query= entityManager.createNativeQuery(sql.toString());
        List<String> list= query.getResultList();
        return list;
	}
	
	/*************************************************************************************
	 * @description：找到同组下周次和星期，项目相同的排课记录
	 * @author：郑昕茹
	 * @date：2017-04-24
	 *************************************************************************************/
	public TimetableAppointment findTimetableAppointmentByGroupIdAndSameWeekAndWeekdayAndItem(Integer groupId, Integer week, Integer weekday, Integer itemId){
		String sql = "select t from TimetableAppointment t left join t.timetableGroups tg  left join t.timetableItemRelateds tir where 1=1";
		sql += " and tg.id = "+groupId;
		sql += " and t.startWeek ="+week;
		sql += " and t.weekday ="+weekday;
		sql += " and tir.operationItem.id ="+itemId;
		List<TimetableAppointment> appointments = timetableAppointmentDAO.executeQuery(sql, 0,-1);
		if(appointments.size() > 0)return appointments.get(0);
		return null;
	}
	
	/************************************************************ 
	 * @description：我的课表（超级管理员）
	 * @author：郑昕茹
	 * @date：2017-05-14
	 ************************************************************/
	public Map<String, Object> findTimetableAdmin(HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		Integer termId = -1;
		String courseId = "";
		Integer labId =-1;
		if(request.getParameter("term") != null){
			termId = Integer.parseInt(request.getParameter("term"));
		}
		if(termId == -1){
			termId = shareService.getBelongsSchoolTerm(Calendar.getInstance()).getId();
		}
		if(request.getParameter("courseId") != null){
			courseId = request.getParameter("courseId");
		}
		if(request.getParameter("labId") != null && !request.getParameter("labId").equals("")){
			labId = Integer.parseInt(request.getParameter("labId"));
		};
		List<TimetableAppointment> appointments = new ArrayList<TimetableAppointment>();
		Set<SchoolCourseInfo> courseInfos = new HashSet<SchoolCourseInfo>();
		if(labId != -1){
			String sql = "select t from TimetableLabRelated t where  t.labRoom.id ="+labId;
			List<TimetableLabRelated> tlrs = timetableLabRelatedDAO.executeQuery(sql, 0, -1);
			
			Set<SchoolCourseDetail> details = new HashSet<SchoolCourseDetail>();
			Integer type = 3;
			if (tlrs.size() > 0) {
				
				for (TimetableLabRelated ta : tlrs) {
						TimetableAppointment te = ta.getTimetableAppointment();
						
						
						if(te.getTimetableStyle() != 25 && te.getTimetableStyle() != 24 && te.getTimetableStyle() != 26){
							details.add(te.getSchoolCourseDetail());
						}
						else{
							if(te.getSchoolCourseMerge() != null)
							{
								details.addAll(te.getSchoolCourseMerge().getSchoolCourseDetails());
							}
						}
						// 不合班
						if ((te.getTimetableStyle() != 25 && te.getTimetableStyle() != 24 && te.getTimetableStyle() != 26) 
								&& te.getSchoolCourseDetail() != null && te.getSchoolCourseDetail().getSchoolTerm().getId().intValue() == termId.intValue()&& te.getStatus() == 1) {
							if(courseId.equals("")){
								appointments.add(te);
								courseInfos.add(te.getSchoolCourseDetail().getSchoolCourse().getSchoolCourseInfo());
							}
							else if(!courseId.equals("") && te.getSchoolCourseDetail().getSchoolCourse().getCourseNo().equals(courseId)){
								appointments.add(te);
								courseInfos.add(te.getSchoolCourseDetail().getSchoolCourse().getSchoolCourseInfo());
							}
						}
						// 合班
						if ((te.getTimetableStyle() == 25 || te.getTimetableStyle() == 24 || te.getTimetableStyle() == 26) && te.getSchoolCourseMerge() != null
								&& te.getSchoolCourseMerge().getTermId().intValue() == termId.intValue()&& te.getStatus() == 1) {
							if(courseId.equals("")){
								appointments.add(te);
								Set<SchoolCourseDetail> courseDetails = te.getSchoolCourseMerge().getSchoolCourseDetails();
								for(SchoolCourseDetail s:courseDetails){
									courseInfos.add(s.getSchoolCourse().getSchoolCourseInfo());
									break;
								}
							}
							else if(!courseId.equals("") && te.getSchoolCourseMerge().getCourseNo().equals(courseId)){
								appointments.add(te);
								Set<SchoolCourseDetail> courseDetails = te.getSchoolCourseMerge().getSchoolCourseDetails();
								for(SchoolCourseDetail s:courseDetails){
									courseInfos.add(s.getSchoolCourse().getSchoolCourseInfo());
									break;
								}
							}
						}
				}
				
			}
			
		}else if(!courseId.equals("")){
			SchoolCourse schoolCourse = schoolCourseDAO.findSchoolCourseByCourseNo(courseId);
			for(SchoolCourseDetail schoolCourseDetail:schoolCourse.getSchoolCourseDetails()){
				if(schoolCourseDetail != null && schoolCourseDetail.getTimetableAppointments() != null){
					courseInfos.add(schoolCourseDetail.getSchoolCourse().getSchoolCourseInfo());
					for(TimetableAppointment te:schoolCourseDetail.getTimetableAppointments()){
						// 不合班
						if ( te.getSchoolCourseDetail().getSchoolTerm().getId().intValue() == termId.intValue()&& te.getStatus() == 1) {
							appointments.add(te);
						}
					}
				}
			}
			String sql = "select s from SchoolCourseMerge s where 1=1 and s.courseNo ='"+courseId+"'";
			List<SchoolCourseMerge> merges = schoolCourseMergeDAO.executeQuery(sql, 0, -1);
			SchoolCourseMerge schoolCourseMerge = null;
			if(merges != null && merges.size() != 0){
				schoolCourseMerge = merges.get(0);
			}
			if(schoolCourseMerge != null){
				for(SchoolCourseDetail s:schoolCourseMerge.getSchoolCourseDetails()){
					courseInfos.add(s.getSchoolCourse().getSchoolCourseInfo());
					break;
				}
			}
			if(schoolCourseMerge != null && schoolCourseMerge.getTimetableAppointments() != null){
					
				for(TimetableAppointment te:schoolCourseMerge.getTimetableAppointments()){
						// 不合班
					if ( te.getSchoolCourseMerge().getTermId().intValue() == termId.intValue()&& te.getStatus() == 1) {
						appointments.add(te);
					}
				}
			}
		}
		map.put("appointments", appointments);
		map.put("courseInfos", courseInfos);
		return map;
	}
	
	/************************************************************ 
	 * @description：我的课表（学生）
	 * @author：郑昕茹
	 * @date：2017-05-14
	 ************************************************************/
	public Map<String, Object> findTimetableStudent(HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		Integer termId = -1;
		String courseId = "";
		if(request.getParameter("term") != null){
			termId = Integer.parseInt(request.getParameter("term"));
		}
		if(termId == -1){
			termId = shareService.getBelongsSchoolTerm(Calendar.getInstance()).getId();
		}
		if(request.getParameter("courseId") != null){
			courseId = request.getParameter("courseId");
		}
		List<TimetableAppointment> appointments = new ArrayList<TimetableAppointment>();
		Map<String, String> courses = new HashMap<String, String>();
		
		Set<SchoolCourseDetail> details = new HashSet<SchoolCourseDetail>();
		String sql = "select t from TimetableGroupStudents t where  t.user.username like '"
				+ shareService.getUserDetail().getUsername() + "' ";
		List<TimetableGroupStudents> tass = timetableGroupStudentsDAO.executeQuery(sql, 0, -1);
		Set<SchoolCourseInfo> courseInfos = new HashSet<SchoolCourseInfo>();
		if (tass.size() > 0) {
			for (TimetableGroupStudents ta : tass) {
				for (TimetableAppointment te : ta.getTimetableGroup().getTimetableAppointments()) {
					if(te.getTimetableStyle() != 25 && te.getTimetableStyle() != 24 && te.getTimetableStyle() != 26){
						courses.put(te.getSchoolCourseDetail().getCourseDetailNo(), te.getSchoolCourseDetail().getCourseName());
						details.add(te.getSchoolCourseDetail());
					}
					else{
						courses.put(te.getSchoolCourseMerge().getId().toString(), te.getSchoolCourseMerge().getCourseName());
						details.addAll(te.getSchoolCourseMerge().getSchoolCourseDetails());
					}
					// 不合班
					if ((te.getTimetableStyle() != 25 && te.getTimetableStyle() != 24 && te.getTimetableStyle() != 26) 
							&& te.getSchoolCourseDetail() != null && te.getSchoolCourseDetail().getSchoolTerm().getId().intValue() == termId.intValue() && te.getStatus() == 1) {
						if(!courseId.equals("")  && courseId.equals(te.getSchoolCourseDetail().getCourseDetailNo()))
						{
							appointments.add(te);
							courseInfos.add(te.getSchoolCourseDetail().getSchoolCourse().getSchoolCourseInfo());
						}
						if(courseId.equals("") ){
							appointments.add(te);
							courseInfos.add(te.getSchoolCourseDetail().getSchoolCourse().getSchoolCourseInfo());
						}
					}
					// 合班
					if ((te.getTimetableStyle() == 25 || te.getTimetableStyle() == 24 || te.getTimetableStyle() == 26) && te.getSchoolCourseMerge() != null
							&& te.getSchoolCourseMerge().getTermId().intValue() == termId.intValue()&& te.getStatus() == 1) {
						if(!courseId.equals("")  && courseId.equals(te.getSchoolCourseMerge().getId().toString()))
						{
							appointments.add(te);
							Set<SchoolCourseDetail> courseDetails = te.getSchoolCourseMerge().getSchoolCourseDetails();
							for(SchoolCourseDetail s:courseDetails){
								courseInfos.add(s.getSchoolCourse().getSchoolCourseInfo());
								break;
							}
						}
						if(courseId.equals("") ){
							appointments.add(te);
							Set<SchoolCourseDetail> courseDetails = te.getSchoolCourseMerge().getSchoolCourseDetails();
							for(SchoolCourseDetail s:courseDetails){
								courseInfos.add(s.getSchoolCourse().getSchoolCourseInfo());
								break;
							}
						}	
					}
				}
			}
		}
		map.put("appointments", appointments);
		map.put("courseInfos", courseInfos);
		return map;
	}
	
	
	/************************************************************ 
	 * @description：我的课表（普通教师）
	 * @author：郑昕茹
	 * @date：2017-05-14
	 ************************************************************/
	public Map<String, Object> findTimetableTeacher(HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		Integer termId = -1;
		String courseId = "";
		String academy ="";
		if(request.getParameter("term") != null){
			termId = Integer.parseInt(request.getParameter("term"));
		}
		if(termId == -1){
			termId = shareService.getBelongsSchoolTerm(Calendar.getInstance()).getId();
		}
		if(request.getParameter("courseId") != null){
			courseId = request.getParameter("courseId");
		}
		if(request.getParameter("academy") != null){
			academy = request.getParameter("academy");
		}
		List<TimetableAppointment> appointments = new ArrayList<TimetableAppointment>();
		Map<String, String> courses = new HashMap<String,String>();
		Set<String> academys = new HashSet<String>();
		
		//非教务
		String sql = "select t from TimetableTeacherRelated t where  t.user.username like '"
				+ shareService.getUserDetail().getUsername() + "' ";
		List<TimetableTeacherRelated> ttrs = timetableTeacherRelatedDAO.executeQuery(sql, 0, -1);
		
		Set<SchoolCourseDetail> details = new HashSet<SchoolCourseDetail>();
		Set<SchoolCourseInfo> courseInfos = new HashSet<SchoolCourseInfo>();
		Integer timetableStyle = 0;
		String courseCode = "0";
		if (ttrs.size() > 0) {
			for (TimetableTeacherRelated ta : ttrs) {
					TimetableAppointment te = ta.getTimetableAppointment();
					timetableStyle = te.getTimetableStyle();
					
					if(te.getTimetableStyle() != 25 && te.getTimetableStyle() != 24 && te.getTimetableStyle() != 26){
						courses.put(te.getSchoolCourseDetail().getSchoolCourse().getCourseNo(), te.getSchoolCourseDetail().getCourseName()+te.getSchoolCourseDetail().getSchoolCourse().getCourseNo());
						academys.add(te.getSchoolCourseDetail().getSchoolCourse().getSchoolCourseInfo().getAcademyNumber());
						details.add(te.getSchoolCourseDetail());
						if(te.getSchoolCourseDetail().getSchoolCourse().getSchoolCourseInfo().getOperationOutlinesForClassId() != null
							&& te.getSchoolCourseDetail().getSchoolCourse().getSchoolCourseInfo().getOperationOutlinesForClassId().size() != 0)
						courseInfos.add(te.getSchoolCourseDetail().getSchoolCourse().getSchoolCourseInfo());
						courseCode = te.getSchoolCourseDetail().getCourseDetailNo();
					}
					else{
						if(te.getSchoolCourseMerge() != null)
						{
							courses.put(te.getSchoolCourseMerge().getCourseNo(), te.getSchoolCourseMerge().getCourseName()+te.getSchoolCourseMerge().getCourseNo());
							details.addAll(te.getSchoolCourseMerge().getSchoolCourseDetails());
							for(SchoolCourseDetail sc:te.getSchoolCourseMerge().getSchoolCourseDetails()){
								academys.add(sc.getSchoolCourse().getSchoolCourseInfo().getAcademyNumber());
								if(sc.getSchoolCourse().getSchoolCourseInfo().getOperationOutlinesForClassId() != null
										&& sc.getSchoolCourse().getSchoolCourseInfo().getOperationOutlinesForClassId().size() != 0)
								courseInfos.add(sc.getSchoolCourse().getSchoolCourseInfo());
								break;
							}
							courseCode = te.getSchoolCourseMerge().getId().toString();
						}
					}
					// 不合班
					if ((te.getTimetableStyle() != 25 && te.getTimetableStyle() != 24 && te.getTimetableStyle() != 26) 
							&& te.getSchoolCourseDetail() != null && te.getSchoolCourseDetail().getSchoolTerm().getId().intValue() == termId.intValue()&& te.getStatus() == 1) {
						if(!courseId.equals("")  && courseId.equals(te.getSchoolCourseDetail().getSchoolCourse().getCourseNo()))
						{
							if(academy.equals(""))
							{
								appointments.add(te);
							}
							else if(te.getSchoolCourseDetail().getSchoolCourse().getSchoolCourseInfo().getAcademyNumber().equals(academy)){
								appointments.add(te);
							}
						}
						if(courseId.equals("") ){
							if(academy.equals(""))
							{
								appointments.add(te);
							}
							else if(te.getSchoolCourseDetail().getSchoolCourse().getSchoolCourseInfo().getAcademyNumber().equals(academy)){
								appointments.add(te);
							}
						}
					}
					// 合班
					if ((te.getTimetableStyle() == 25 || te.getTimetableStyle() == 24 || te.getTimetableStyle() == 26) && te.getSchoolCourseMerge() != null
							&& te.getSchoolCourseMerge().getTermId().intValue() == termId.intValue()&& te.getStatus() == 1) {
						if(!courseId.equals("")  && courseId.equals(te.getSchoolCourseMerge().getCourseNo()))
						{
							if(academy.equals(""))
							{
								appointments.add(te);
							}
							else {
								boolean flag = false;
								for(SchoolCourseDetail sc:te.getSchoolCourseMerge().getSchoolCourseDetails()){
									if(sc.getSchoolCourse().getSchoolCourseInfo().getAcademyNumber().equals(academy)){
										flag = true;
										break;
									}
								}
								if(flag == true)
								{
									appointments.add(te);
								}
							}
						}
						if(courseId.equals("") ){
							if(academy.equals(""))
							{
								appointments.add(te);
							}
							else{
								boolean flag = false;
								for(SchoolCourseDetail sc:te.getSchoolCourseMerge().getSchoolCourseDetails()){
									if(sc.getSchoolCourse().getSchoolCourseInfo().getAcademyNumber().equals(academy)){
										flag = true;
										break;
									}
								}
								if(flag == true)
								{
									appointments.add(te);
								}
							}
						}	
					}
			}
		}
		map.put("appointments", appointments);
		map.put("courseInfos", courseInfos);
		return map;
	}
	
	/************************************************************ 
	 * @description：我的课表（课程负责教师）
	 * @author：郑昕茹
	 * @date：2017-05-14
	 ************************************************************/
	public Map<String,Object> findTimetableCourseTeacher(HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		Integer termId = -1;
		String courseId = "";
		Integer labId =-1;
		if(request.getParameter("term") != null){
			termId = Integer.parseInt(request.getParameter("term"));
		}
		if(termId == -1){
			termId = shareService.getBelongsSchoolTerm(Calendar.getInstance()).getId();
		}
		if(request.getParameter("courseId") != null){
			courseId = request.getParameter("courseId");
		}
		if(request.getParameter("labId") != null && !request.getParameter("labId").equals("")){
			labId = Integer.parseInt(request.getParameter("labId"));
		}
		Map<String, String> courses = new HashMap<String, String>();
		List<TimetableAppointment> appointments = new ArrayList<TimetableAppointment>();
		Set<SchoolCourseInfo> courseInfos = new HashSet<SchoolCourseInfo>();
		Set<SchoolCourseMerge> merges = new HashSet<SchoolCourseMerge>();
		for(SchoolCourseDetail s:shareService.getUser().getSchoolCourseDetails()){
			if(s.getSchoolCourse().getSchoolCourseInfo().getOperationOutlinesForClassId() != null && s.getSchoolCourse().getSchoolCourseInfo().getOperationOutlinesForClassId().size() != 0)
			{
				courseInfos.add(s.getSchoolCourse().getSchoolCourseInfo());
			}
			if(s.getSchoolCourseMerge() == null){
				courses.put(s.getSchoolCourse().getCourseNo(), s.getCourseName()+s.getSchoolCourse().getCourseNo());
				
			}
			else{
				merges.add(s.getSchoolCourseMerge());
			}
		}
		for(SchoolCourseMerge s:merges){
			courses.put(s.getCourseNo(), s.getCourseName()+s.getCourseNo());
		}
		Integer timetableStyle = 0;
		String courseCode = "0";
		if(courseId.equals("") && labId == -1){
			String sql = "select t from TimetableTeacherRelated t where  t.user.username like '"
					+ shareService.getUserDetail().getUsername() + "' ";
			List<TimetableTeacherRelated> ttrs = timetableTeacherRelatedDAO.executeQuery(sql, 0, -1);
			
			Set<SchoolCourseDetail> details = new HashSet<SchoolCourseDetail>();
			
			if (ttrs.size() > 0) {
				for (TimetableTeacherRelated ta : ttrs) {
						TimetableAppointment te = ta.getTimetableAppointment();
						timetableStyle = te.getTimetableStyle();
						
						if(te.getTimetableStyle() != 25 && te.getTimetableStyle() != 24 && te.getTimetableStyle() != 26){
							details.add(te.getSchoolCourseDetail());
							courseInfos.add(te.getSchoolCourseDetail().getSchoolCourse().getSchoolCourseInfo());
							courseCode = te.getSchoolCourseDetail().getCourseDetailNo();
						}
						else{
							if(te.getSchoolCourseMerge() != null)
							{
								details.addAll(te.getSchoolCourseMerge().getSchoolCourseDetails());
								for(SchoolCourseDetail sc:te.getSchoolCourseMerge().getSchoolCourseDetails()){
									courseInfos.add(sc.getSchoolCourse().getSchoolCourseInfo());
									break;
								}
								courseCode = te.getSchoolCourseMerge().getId().toString();
							}
						}
						// 不合班
						if ((te.getTimetableStyle() != 25 && te.getTimetableStyle() != 24 && te.getTimetableStyle() != 26) 
								&& te.getSchoolCourseDetail() != null && te.getSchoolCourseDetail().getSchoolTerm().getId().intValue() == termId.intValue() && te.getStatus() == 1) {
							appointments.add(te);
						}
						// 合班
						if ((te.getTimetableStyle() == 25 || te.getTimetableStyle() == 24 || te.getTimetableStyle() == 26) && te.getSchoolCourseMerge() != null
								&& te.getSchoolCourseMerge().getTermId().intValue() == termId.intValue() && te.getStatus() == 1) {
							appointments.add(te);
						}
				}
			}
		}
		else if(labId != -1){
			String sql = "select t from TimetableLabRelated t where  t.labRoom.id ="+labId;
			List<TimetableLabRelated> tlrs = timetableLabRelatedDAO.executeQuery(sql, 0, -1);
			
			Set<SchoolCourseDetail> details = new HashSet<SchoolCourseDetail>();
			Integer type = 3;
			if (tlrs.size() > 0) {
				
				for (TimetableLabRelated ta : tlrs) {
						TimetableAppointment te = ta.getTimetableAppointment();
						
						
						if(te.getTimetableStyle() != 25 && te.getTimetableStyle() != 24 && te.getTimetableStyle() != 26 ){
							details.add(te.getSchoolCourseDetail());
							courseCode = te.getSchoolCourseDetail().getCourseDetailNo();
						}
						else{
							if(te.getSchoolCourseMerge() != null)
							{
								details.addAll(te.getSchoolCourseMerge().getSchoolCourseDetails());
								courseCode = te.getSchoolCourseMerge().getId().toString();
							}
						}
						// 不合班
						if ((te.getTimetableStyle() != 25 && te.getTimetableStyle() != 24 && te.getTimetableStyle() != 26) 
								&& te.getSchoolCourseDetail() != null && te.getSchoolCourseDetail().getSchoolTerm().getId().intValue() == termId.intValue() && te.getStatus() == 1) {
							if(courseId.equals("")){
								appointments.add(te);
							
							}
							else if(!courseId.equals("") && te.getSchoolCourseDetail().getSchoolCourse().getCourseNo().equals(courseId)){
								appointments.add(te);
								type = 2;
							}
						}
						// 合班
						if ((te.getTimetableStyle() == 25 || te.getTimetableStyle() == 24 || te.getTimetableStyle() == 26) && te.getSchoolCourseMerge() != null
								&& te.getSchoolCourseMerge().getTermId().intValue() == termId.intValue() && te.getStatus() == 1) {
							if(courseId.equals("")){
								appointments.add(te);
							}
							else if(!courseId.equals("") && te.getSchoolCourseMerge().getCourseNo().equals(courseId)){
								appointments.add(te);
							}
						}
				}
				
			}
		}else{
			String sql = "select s from SchoolCourseMerge s where 1=1 and s.courseNo ='"+courseId+"'";
			List<SchoolCourseMerge> courseMerges = schoolCourseMergeDAO.executeQuery(sql, 0, -1);
			SchoolCourseMerge schoolCourseMerge = null;
			if(merges != null && courseMerges.size() != 0){
				schoolCourseMerge = courseMerges.get(0);
			}
			if(schoolCourseMerge != null){
				for(SchoolCourseDetail s:schoolCourseMerge.getSchoolCourseDetails()){
					courseInfos.add(s.getSchoolCourse().getSchoolCourseInfo());
					break;
				}
			}
			if(schoolCourseMerge != null && schoolCourseMerge.getTimetableAppointments() != null){
					
				for(TimetableAppointment te:schoolCourseMerge.getTimetableAppointments()){
						// 不合班
					if ( te.getSchoolCourseMerge().getTermId().intValue() == termId.intValue()&& te.getStatus() == 1) {
						appointments.add(te);
					}
				}
			}
		}
		map.put("appointments", appointments);
		map.put("courseInfos", courseInfos);
		return map;
	}
	/*************************************************************************************
	 * @description：找到detail其下基础课所有排课
	 * @author：郑昕茹
	 * @date：2017-05-14
	 *************************************************************************************/
	public List<TimetableAppointment> findTimetableAppointmentsByCourseDetailNoInSpecial(String courseDetailNo) {
		String sql = "select t from TimetableAppointment t where 1=1";
		sql += " and t.timetableStyle is not null and (t.timetableStyle = 21 or t.timetableStyle = 22 or t.timetableStyle = 28)";
		sql += " and t.schoolCourseDetail.courseDetailNo ='"+courseDetailNo+"'";
		return timetableAppointmentDAO.executeQuery(sql, 0,-1);
	}
	
	
	/*************************************************************************************
	 * @description：找到detail其下基础课所有排课的项目
	 * @author：郑昕茹
	 * @date：2017-05-14
	 *************************************************************************************/
	public List<OperationItem> findOperationItemsByCourseDetailNoInSpecial(String courseDetailNo) {
		String sql = "select oi from TimetableAppointment t left join t.timetableItemRelateds tir left join tir.operationItem oi where 1=1";
		sql += " and t.timetableStyle is not null and (t.timetableStyle = 21 or t.timetableStyle = 22 or t.timetableStyle = 28)";
		sql += " and t.schoolCourseDetail.courseDetailNo ='"+courseDetailNo+"'";
		sql += " group by oi.id";
		return operationItemDAO.executeQuery(sql, 0,-1);
	}
	
	/*************************************************************************************
	 * @description：找到mergeId其下所有排课
	 * @author：郑昕茹
	 * @date：2017-05-14
	 *************************************************************************************/
	public List<TimetableAppointment> findTimetableAppointmentsByMergeId(Integer mergeId) {
		String sql = "select t from TimetableAppointment t where 1=1";
		sql += " and t.timetableStyle is not null and (t.timetableStyle = 24 or t.timetableStyle = 25 or t.timetableStyle = 26)";
		sql += " and t.schoolCourseMerge.mergeId ="+ mergeId;
		return timetableAppointmentDAO.executeQuery(sql, 0,-1);
	}
	
	

	/*************************************************************************************
	 * @description：找到merge其下基础课所有排课的项目
	 * @author：郑昕茹
	 * @date：2017-05-14
	 *************************************************************************************/
	public List<OperationItem> findOperationItemsByMergeIdInSpecial(Integer mergeId) {
		String sql = "select oi from TimetableAppointment t left join t.timetableItemRelateds tir left join tir.operationItem oi where 1=1";
		sql += " and t.timetableStyle is not null and (t.timetableStyle = 24 or t.timetableStyle = 25 or t.timetableStyle = 26)";
		sql += " and t.schoolCourseMerge.id ="+mergeId;
		sql += " group by oi.id";
		return operationItemDAO.executeQuery(sql, 0,-1);
	}
	
	
	/*************************************************************************************
	 * @description：找到所有当前实验中心管理员下的schoolcoursedetail
	 * @author：郑昕茹
	 * @date：2017-05-18
	 *************************************************************************************/
	public List<SchoolCourseDetail> findLabSchoolCourseDetailUnderPermission(SchoolCourseDetail schoolCourseDetail, int currpage, int pageSize, HttpServletRequest request) {	
		StringBuffer sql = new StringBuffer(
				"select c from SchoolCourseDetail c  join c.schoolCourse s " +
						"join s.schoolCourseInfo info join info.operationOutlinesForClassId oo " +
						"where 1=1 and oo is not null "+
						"and c.totalClassHour <> c.theoryHour and c.weekday is null");
		
		int termId = shareService.getBelongsSchoolTerm(Calendar.getInstance()).getId();
		if(schoolCourseDetail.getSchoolTerm() != null && schoolCourseDetail.getSchoolTerm().getId() != null 
				&& !schoolCourseDetail.getSchoolTerm().getId().equals("")){
			termId = schoolCourseDetail.getSchoolTerm().getId();
		}
		sql.append(" and c.schoolTerm.id ="+termId);
		
		if(schoolCourseDetail.getCourseName() != null && !schoolCourseDetail.getCourseName().equals("")){
			sql.append(" and c.courseName like '%"+schoolCourseDetail.getCourseName()+"%'");
		}
		if(schoolCourseDetail.getSchoolCourse() != null && schoolCourseDetail.getSchoolCourse().getCourseNo() != null && !schoolCourseDetail.getSchoolCourse().getCourseNo().equals("")){
			sql.append(" and c.schoolCourse.courseNo like '%"+schoolCourseDetail.getSchoolCourse().getCourseNo()+"%'");
		}
		//已该教师为课程负责人
		if(!request.getSession().getAttribute("authorityName").equals("SUPERADMIN") && !request.getSession().getAttribute("authorityName").equals("LABCENTERMANAGER")){
			sql.append(" and c.user.username ='"+shareService.getUser().getUsername()+"'");	
		}
		// 执行sb语句
		List<SchoolCourseDetail> schoolCourses = schoolCourseDetailDAO.executeQuery(sql.toString(), (currpage-1)*pageSize,pageSize);
		return schoolCourses;
	}

	/*************************************************************************************
	 * @description：
	 * @author：周志辉
	 * @date：2017-09-27
	 *************************************************************************************/
	@Override
	public List<String> findUserBygroupId(int groupid) {
		String sql = "select username from timetable_group_students where group_id="+groupid;
		//sql+=" group by scs.student_number";
		Query query= entityManager.createNativeQuery(sql.toString());
        List<String> list= query.getResultList();
        return list;
	}


}