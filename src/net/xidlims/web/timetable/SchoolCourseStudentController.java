package net.xidlims.web.timetable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import net.xidlims.common.ComparatorTimetableGroup;
import net.xidlims.dao.SchoolCourseDAO;
import net.xidlims.dao.SchoolCourseDetailDAO;
import net.xidlims.dao.SchoolCourseStudentDAO;
import net.xidlims.dao.TimetableGroupDAO;
import net.xidlims.dao.TimetableGroupStudentsDAO;
import net.xidlims.dao.TimetableSelfCourseDAO;
import net.xidlims.dao.UserDAO;
import net.xidlims.domain.LabRoomAdmin;
import net.xidlims.domain.SchoolCourse;
import net.xidlims.domain.SchoolCourseDetail;
import net.xidlims.domain.SchoolCourseStudent;
import net.xidlims.domain.TimetableAppointment;
import net.xidlims.domain.TimetableGroup;
import net.xidlims.domain.TimetableGroupStudents;
import net.xidlims.domain.TimetableSelfCourse;
import net.xidlims.domain.User;
import net.xidlims.service.timetable.SchoolCourseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller("SchoolCourseStudentController")
public class SchoolCourseStudentController<JsonResult> {
	/************************************************************
	 * @初始化WebDataBinder，这个WebDataBinder用于填充被@InitBinder注释的处理 方法的command和form对象
	 * 
	 ************************************************************/
	@InitBinder
	public void initBinder(WebDataBinder binder, HttpServletRequest request) { // Register
		binder.registerCustomEditor(java.util.Calendar.class,
				new org.skyway.spring.util.databinding.CustomCalendarEditor());
		binder.registerCustomEditor(
				byte[].class,
				new org.springframework.web.multipart.support.ByteArrayMultipartFileEditor());
		binder.registerCustomEditor(boolean.class,
				new org.skyway.spring.util.databinding.EnhancedBooleanEditor(
						false));
		binder.registerCustomEditor(Boolean.class,
				new org.skyway.spring.util.databinding.EnhancedBooleanEditor(
						true));
		binder.registerCustomEditor(java.math.BigDecimal.class,
				new org.skyway.spring.util.databinding.NaNHandlingNumberEditor(
						java.math.BigDecimal.class, true));
		binder.registerCustomEditor(Integer.class,
				new org.skyway.spring.util.databinding.NaNHandlingNumberEditor(
						Integer.class, true));
		binder.registerCustomEditor(java.util.Date.class,
				new org.skyway.spring.util.databinding.CustomDateEditor());
		binder.registerCustomEditor(String.class,
				new org.skyway.spring.util.databinding.StringEditor());
		binder.registerCustomEditor(Long.class,
				new org.skyway.spring.util.databinding.NaNHandlingNumberEditor(
						Long.class, true));
		binder.registerCustomEditor(Double.class,
				new org.skyway.spring.util.databinding.NaNHandlingNumberEditor(
						Double.class, true));
	}

	@Autowired
	private SchoolCourseStudentDAO schoolCourseStudentDAO;
	@Autowired
	private TimetableGroupStudentsDAO timetableGroupStudentsDAO;
	@Autowired
	private TimetableGroupDAO timetableGroupDAO;
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private SchoolCourseDetailDAO schoolCourseDetailDAO;
	@Autowired
	private TimetableSelfCourseDAO timetableSelfCourseDAO;
	@Autowired
	private SchoolCourseService schoolCourseService;
	@Autowired
	private SchoolCourseDAO schoolCourseDAO;
	/************************************************************
	 * 教务排课-进行排课查看 作者：戴昊宇 日期：2017-10-25
	 ************************************************************/
	@RequestMapping("/timetable/openSearchStudent")
	public ModelAndView openSearchStudent(HttpServletRequest request,
			@RequestParam String courseDetailNo,Integer merge) {
		ModelAndView mav = new ModelAndView();
		// 根据课程及id获取课程排课列表
		
		String sql = "select c from SchoolCourseStudent c ";
		// 合班情况下
		if(merge==1){
			SchoolCourseDetail schoolCourseDetailByCourseDetailNo = schoolCourseDetailDAO
					.findSchoolCourseDetailByCourseDetailNo(courseDetailNo);
			Integer mergeId = 0;
			mergeId = schoolCourseDetailByCourseDetailNo.getSchoolCourseMerge()
					.getId();
			sql = sql + "where c.schoolCourseDetail.schoolCourseMerge.id="
				+ mergeId + " and c.state=1 ";
		}else{
			sql = sql + " where c.schoolCourseDetail.courseDetailNo='"
				+ courseDetailNo + "'  and c.state=1 ";
		}
		
		if(request.getParameter("schoolCourseClass")!=null&&request.getParameter("schoolCourseClass")!=""){
		    sql = sql + " and c.schoolClasses.classNumber like '%" +request.getParameter("schoolCourseClass") + "%'";
		}
		if(request.getParameter("schoolCourseStudent")!=null&request.getParameter("schoolCourseStudent")!=""){
		    sql = sql + " and c.userByStudentNumber.username like '%" +request.getParameter("schoolCourseStudent") + "%'";
		}
		List<SchoolCourseStudent> schoolCourseStudents = schoolCourseStudentDAO
				.executeQuery(sql, 0, -1);
		for(SchoolCourseStudent schoolCourseStudent :schoolCourseStudents){
			schoolCourseStudent.setIsSelect(1);
			schoolCourseStudentDAO.store(schoolCourseStudent);
			schoolCourseStudentDAO.flush();
		}
		mav.addObject("studentMap", schoolCourseStudents);
		mav.addObject("merge",merge);
		mav.addObject("courseDetailNo",courseDetailNo);
		mav.setViewName("timetable/schedulingcourse/listCourseStudent.jsp");
		return mav;

	}
	
	/************************************************************
	 * 功能：排课管理--二次排课分批--分批管理 
	 * 作者：贺子龙 
	 * 日期：2016-03-14
	 ************************************************************/
	@SuppressWarnings("unchecked")
	@RequestMapping("/timetable/manageBatch")
	public ModelAndView manageBatch(@RequestParam String courseDetailNo) {
		ModelAndView mav = new ModelAndView();
		SchoolCourseDetail schoolCourseDetail = schoolCourseDetailDAO.findSchoolCourseDetailByPrimaryKey(courseDetailNo);
		// 新建一个存放分组的list
		List<TimetableGroup> groups = new ArrayList<TimetableGroup>();
		// 找到该教务课程对应的所有排课
		Set<TimetableAppointment> timetableAppointments= schoolCourseDetail.getTimetableAppointments();
		for (TimetableAppointment timetableAppointment : timetableAppointments) {
			Set<TimetableGroup> groupSet = timetableAppointment.getTimetableGroups();
			if (groupSet.size()>0) {
				for (TimetableGroup timetableGroup : groupSet) {
					if (groups.contains(timetableGroup)) {
						//防止重复
					}else {
						groups.add(timetableGroup);
					}
				}
			}
		}
		ComparatorTimetableGroup comparator=new ComparatorTimetableGroup();
		Collections.sort(groups, comparator);//按批次排序
		mav.addObject("groups", groups);
		mav.setViewName("timetable/schedulingcourse/manageBatch.jsp");
		return mav;

	}
	
	/************************************************************
	 * 功能：排课管理--自主排课分批--分批管理 
	 * 作者：贺子龙 
	 * 日期：2016-03-14
	 ************************************************************/
	@SuppressWarnings("unchecked")
	@RequestMapping("/timetable/manageBatchSelf")
	public ModelAndView manageBatchSelf(@RequestParam int selfCourseId) {
		ModelAndView mav = new ModelAndView();
		TimetableSelfCourse selfCourse = timetableSelfCourseDAO.findTimetableSelfCourseByPrimaryKey(selfCourseId);
		// 新建一个存放分组的list
		List<TimetableGroup> groups = new ArrayList<TimetableGroup>();
		// 找到该教务课程对应的所有排课
		Set<TimetableAppointment> timetableAppointments= selfCourse.getTimetableAppointments();
		for (TimetableAppointment timetableAppointment : timetableAppointments) {
			Set<TimetableGroup> groupSet = timetableAppointment.getTimetableGroups();
			if (groupSet.size()>0) {
				for (TimetableGroup timetableGroup : groupSet) {
					if (groups.contains(timetableGroup)) {
						//防止重复
					}else {
						groups.add(timetableGroup);
					}
				}
			}
		}
		ComparatorTimetableGroup comparator=new ComparatorTimetableGroup();
		Collections.sort(groups, comparator);//按批次排序
		mav.addObject("groups", groups);
		mav.setViewName("timetable/selfTimetable/manageBatchSelf.jsp");
		return mav;

	}
	
	/************************************************************
	 * 教务排课-进行排课查看 作者：魏诚 日期：2014-08-25
	 ************************************************************/
	@RequestMapping("/timetable/openSearchStudentGroup")
	public ModelAndView openSearchStudent(@RequestParam int id, String groupName) {
		ModelAndView mav = new ModelAndView();
		TimetableGroup currgroup=timetableGroupDAO.findTimetableGroupById(id);
		int batchId=currgroup.getTimetableBatch().getId();//获取批次id
		String sqlForGroup="select g from TimetableGroup g where g.timetableBatch.id ="+batchId;
		List<TimetableGroup> groups=timetableGroupDAO.executeQuery(sqlForGroup);
		mav.addObject("groups", groups);
		// 根据课程及id获取课程排课列表
		String sql = "select c from TimetableGroupStudents c where c.timetableGroup.id =" + id;
		List<TimetableGroupStudents> timetableGroupStudents = timetableGroupStudentsDAO.executeQuery(sql);
		mav.addObject("studentMap",timetableGroupStudents);
		mav.addObject("id",id);
		mav.addObject("admin",new LabRoomAdmin());//jsp modelAttribute
		if (groupName!=null&&groupName.length()>1&&groupName.indexOf(",")!=-1) {
			String arrayString[]=groupName.split(",");// 第一个元素为分组名称  第二个元素为排课类型
			mav.addObject("groupName",arrayString[0]);
			mav.addObject("timetableStyle",arrayString[1]);
		}
		mav.addObject("id",id);
		
		mav.setViewName("timetable/schedulingcourse/listCourseStudentGroup.jsp");
		return mav;

	}
	/************************************************************
	 *功能：批量调整学生分组
	 *作者：贺子龙
	 *时间：2016-01-06
	 ************************************************************/
	@RequestMapping("/timetable/batchAlterGroupStudents")
	public ModelAndView batchAlterGroupStudents(@RequestParam int targetGroupId, String[] array) {
		ModelAndView mav = new ModelAndView();
		TimetableGroup targetGroup=timetableGroupDAO.findTimetableGroupById(targetGroupId);
		int currGroupId=0;
		for (String string : array) {
			TimetableGroupStudents timetableGroupStudent = timetableGroupStudentsDAO.findTimetableGroupStudentsById(Integer.parseInt(string));
			currGroupId=timetableGroupStudent.getTimetableGroup().getId();
			timetableGroupStudent.setTimetableGroup(targetGroup);//将该学生所在分组至为目标分组
				timetableGroupStudentsDAO.store(timetableGroupStudent);
		}
		mav.setViewName("redirect:/timetable/openSearchStudentGroup?id="+currGroupId);
		return mav;
		
	}
	/************************************************************
	 *功能：批量添加分组学生
	 *作者：贺子龙
	 *时间：2016-01-06
	 ************************************************************/
	@RequestMapping("timetable/addGroupStudents")
	public ModelAndView addGroupStudents(@RequestParam int id, String[] array) {
		ModelAndView mav = new ModelAndView();
		TimetableGroup group=timetableGroupDAO.findTimetableGroupById(id);
		for (String username : array) {
			TimetableGroupStudents timetableGroupStudent = new TimetableGroupStudents();
			timetableGroupStudent.setTimetableGroup(group);
			User user=userDAO.findUserByPrimaryKey(username);
			if (user!=null) {
				timetableGroupStudent.setUser(user);
				timetableGroupStudentsDAO.store(timetableGroupStudent);
			}
		}
		mav.setViewName("redirect:/timetable/openSearchStudentGroup?id="+id);
		return mav;
		
	}
	/************************************************************
	 *功能：删除分组学生
	 *作者：贺子龙
	 *时间：2016-01-06
	 ************************************************************/
	@RequestMapping("timetable/deleteGroupStudent")
	public ModelAndView deleteGroupStudent(@RequestParam int id) {
		ModelAndView mav = new ModelAndView();
		TimetableGroupStudents timetableGroupStudent = timetableGroupStudentsDAO.findTimetableGroupStudentsById(id);
		int currGroupId=timetableGroupStudent.getTimetableGroup().getId();
		timetableGroupStudentsDAO.remove(timetableGroupStudent);
		timetableGroupStudentsDAO.flush();
		mav.setViewName("redirect:/timetable/openSearchStudentGroup?id="+currGroupId);
		return mav;
		
	}
	
	/************************************************************
	 *功能：删除学生
	 *作者：戴昊宇
	 *时间：2017-10-25
	 ************************************************************/
	@RequestMapping("timetable/deleteStudent")
	public @ResponseBody String deleteStudent(@RequestParam String userName,String courseDetailNo,Integer merge) {
		// 通过courseDetailNo找到所有选课组下的课
		SchoolCourseDetail schoolCourseDetail = schoolCourseDetailDAO.findSchoolCourseDetailByCourseDetailNo(courseDetailNo);
		SchoolCourse schoolCourse = schoolCourseDAO.findSchoolCourseByCourseNo(	schoolCourseDetail.getSchoolCourse().getCourseNo());
		Set<SchoolCourseDetail> schoolCourseDetails = schoolCourse.getSchoolCourseDetails();
		// 遍历每一门课
		for(SchoolCourseDetail school:schoolCourseDetails){
		List<SchoolCourseStudent> findSchoolCourseStudentByStudentNumber = schoolCourseService.findSchoolCourseStudentByStudentNumber(userName);
		for(SchoolCourseStudent student :findSchoolCourseStudentByStudentNumber){
	    if(student.getSchoolCourseDetail().getCourseDetailNo().equals(school.getCourseDetailNo())){
			schoolCourseStudentDAO.remove(student);
			schoolCourseStudentDAO.flush();
	        }
		  }
		}
		return "success";
		
	}
	/************************************************************
	 *功能：添加学生
	 *作者：戴昊宇
	 *时间：2017-10-25
	 ************************************************************/
	@RequestMapping("timetable/addStudent")
	public @ResponseBody String addStudent(@RequestParam String userName,String courseDetailNo,Integer merge) {
		// 通过courseDetailNo找到所有选课组下的课
		SchoolCourseDetail schoolCourseDetail = schoolCourseDetailDAO.findSchoolCourseDetailByCourseDetailNo(courseDetailNo);
		SchoolCourse schoolCourse = schoolCourseDAO.findSchoolCourseByCourseNo(	schoolCourseDetail.getSchoolCourse().getCourseNo());
		Set<SchoolCourseDetail> schoolCourseDetails = schoolCourse.getSchoolCourseDetails();
		User user = userDAO.findUserByUsername(userName);
		// 遍历每一门课
		for(SchoolCourseDetail school:schoolCourseDetails){
		List<SchoolCourseStudent> studentByCourseNo = schoolCourseService.findSchoolCourseStudentByCourseNo(courseDetailNo);
		SchoolCourseStudent schoolCourseStudent = new SchoolCourseStudent();
		schoolCourseStudent.setUserByStudentNumber(user);
		schoolCourseStudent.setSchoolCourseDetail(school);
		schoolCourseStudent.setSchoolTerm(studentByCourseNo.get(0).getSchoolTerm());
		schoolCourseStudent.setSchoolClasses(user.getSchoolClasses());
		schoolCourseStudent.setSchoolAcademy(studentByCourseNo.get(0).getSchoolAcademy());
		schoolCourseStudent.setMajorName(studentByCourseNo.get(0).getMajorName());
		schoolCourseStudent.setMajorDirectionName(studentByCourseNo.get(0).getMajorDirectionName());
		schoolCourseStudent.setState(1);
		schoolCourseStudent.setIsSelect(1);
		schoolCourseStudentDAO.store(schoolCourseStudent);
		schoolCourseStudentDAO.flush();
		}
		return "success";
		
	}
	/************************************************************
	 *功能：插件查找学生
	 *作者：戴昊宇
	 *时间：2017-10-25
	 ************************************************************/
	@RequestMapping("timetable/findStudent")
	public @ResponseBody List<Map<String,String>> findStudent(@RequestParam String userName) {
		// 返回学生信息
		return schoolCourseService.coolSuggestTuser(userName);
	}
}
