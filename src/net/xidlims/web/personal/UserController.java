package net.xidlims.web.personal;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.BindException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.xidlims.JsonDateValueProcessor;
import net.xidlims.constant.CommonConstantInterface;
import net.xidlims.dao.LabReservationDAO;
import net.xidlims.dao.MessageDAO;
import net.xidlims.dao.SchoolCourseStudentDAO;
import net.xidlims.dao.SchoolTermDAO;
import net.xidlims.dao.TimetableAppointmentDAO;
import net.xidlims.dao.TimetableCourseStudentDAO;
import net.xidlims.dao.TimetableGroupStudentsDAO;
import net.xidlims.dao.TimetableLabRelatedDAO;
import net.xidlims.dao.TimetableTeacherRelatedDAO;
import net.xidlims.dao.UserDAO;
import net.xidlims.domain.Authority;
import net.xidlims.domain.Message;
import net.xidlims.domain.SchoolCourseStudent;
import net.xidlims.domain.SchoolTerm;
import net.xidlims.domain.TimetableAppointment;
import net.xidlims.domain.TimetableCourseStudent;
import net.xidlims.domain.TimetableGroupStudents;
import net.xidlims.domain.TimetableLabRelated;
import net.xidlims.domain.TimetableTeacherRelated;
import net.xidlims.domain.User;
import net.xidlims.service.common.ShareService;
import net.xidlims.service.lab.LabReservationService;
import net.xidlims.service.message.MessageService;
import net.xidlims.service.personal.PersonalCenterService;
import net.xidlims.service.timetable.OuterApplicationService;
import net.xidlims.service.timetable.SchoolCourseDetailService;
import net.xidlims.service.timetable.TimetableAppointmentService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.commons.collections.functors.ConstantFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

@Controller("UserController")
public class UserController {

	@Autowired
	PersonalCenterService personalCenterService;
	@Autowired
	TimetableAppointmentService timetableAppointmentService;
	@Autowired
	ShareService shareService;
	@Autowired
	SchoolCourseDetailService schoolCourseDetailService;
	@Autowired
	TimetableLabRelatedDAO timetableLabRelatedDAO;
	@Autowired
	TimetableGroupStudentsDAO timetableGroupStudentsDAO;
	@Autowired
	SchoolCourseStudentDAO schoolCourseStudentDAO;
	@Autowired
	TimetableCourseStudentDAO timetableCourseStudentDAO;
	@Autowired
	private OuterApplicationService outerApplicationService;
	@Autowired
	LabReservationService labReservationService;
	@Autowired
	LabReservationDAO labReservationDAO;
	@Autowired
	SchoolTermDAO schoolTermDAO;
	@Autowired
	UserDAO userDAO;
	@Autowired
	TimetableTeacherRelatedDAO timetableTeacherRelatedDAO;
	@Autowired
	TimetableAppointmentDAO timetableAppointmentDAO;
	@Autowired
	MessageDAO messageDAO;
	@Autowired
	MessageService messageService;
//	@Autowired
//	LabRoomDeviceService labRoomDeviceService;
//	@Autowired CTrainingResultDAO cTrainingResultDAO;
	@PersistenceContext
	private EntityManager entityManager;

	@InitBinder
	public void initBinder(WebDataBinder binder, HttpServletRequest request) { // Register // static // property // editors.
		binder.registerCustomEditor(java.util.Calendar.class, new org.skyway.spring.util.databinding.CustomCalendarEditor());
		binder.registerCustomEditor(byte[].class, new org.springframework.web.multipart.support.ByteArrayMultipartFileEditor());
		binder.registerCustomEditor(boolean.class, new org.skyway.spring.util.databinding.EnhancedBooleanEditor(false));
		binder.registerCustomEditor(Boolean.class, new org.skyway.spring.util.databinding.EnhancedBooleanEditor(true));
		binder.registerCustomEditor(java.math.BigDecimal.class, new org.skyway.spring.util.databinding.NaNHandlingNumberEditor(java.math.BigDecimal.class, true));
		binder.registerCustomEditor(Integer.class, new org.skyway.spring.util.databinding.NaNHandlingNumberEditor(Integer.class, true));
		binder.registerCustomEditor(java.util.Date.class, new org.skyway.spring.util.databinding.CustomDateEditor());
		binder.registerCustomEditor(String.class, new org.skyway.spring.util.databinding.StringEditor());
		binder.registerCustomEditor(Long.class, new org.skyway.spring.util.databinding.NaNHandlingNumberEditor(Long.class, true));
		binder.registerCustomEditor(Double.class, new org.skyway.spring.util.databinding.NaNHandlingNumberEditor(Double.class, true));
	}
	
	/*********************************************************************************
	 * @description:我的个人信息列表
	 * @author:方正2014/08/07
	 ************************************************************************************/
	@RequestMapping("/personal/listMyInfo")
	public ModelAndView listMyInfo() {
		ModelAndView mav = new ModelAndView();

		User user = shareService.getUser();  // 获取当前用户
		Set<Authority> as = user.getAuthorities();
		String str = "";
		if(as.size()==0){
			str = "暂无身份";
		}
		if(as.size()>0){
			for(Authority a : as){
				str+= a.getCname()+",";
			}
			str=str.substring(0,str.length()-1);
		}
		mav.addObject("str", str);
		mav.addObject("user", user);
		mav.setViewName("personal/message/listMyInfo.jsp");
		return mav;
	}

	/************************************************************
	 * @内容: 跳入个人中心模块 方正 2014/8/14
	 * 
	 ************************************************************/
	@RequestMapping("/personal/message/mySelfTimetable")
	@Transactional
	public ModelAndView mySelfTimetable() {
		ModelAndView mav = new ModelAndView();

		// 获取当前用户
		User user = shareService.getUser();
		String username = user.getUsername();
		String cname = user.getCname();
		mav.addObject("cname", cname);

		// 获取当前周次
		int week = shareService.findNewWeek();
		mav.addObject("week", week);

		// 获取当前学期
		SchoolTerm SchoolTerm = shareService.getBelongsSchoolTerm(Calendar.getInstance());
		String term = SchoolTerm.getTermName();
		mav.addObject("term", term);

		//定义操作视图和存储过程的service接口
		//EntityManager entityManager= objectDaoService.getEntityManager(); 
		
		/*//获取教师视图的返回list
		String teacherSql ="select course_name,course_code,teacher,start_week,end_week,start_class,end_class,weekday,cname,lab_id  from view_timetable_teacher_course where term_id = " + SchoolTerm.getId() + " and teacher like '"+ username + "'";
		Query query= entityManager.createNativeQuery(teacherSql );
		List<Object[]> teacherList= query.getResultList(); 
		
		//获取教师视图的返回list
		String studentSql ="select course_name,course_code,teacher,start_week,end_week,start_class,end_class,weekday,teacher_name,lab_id  from view_timetable_student_course where term_id = " + SchoolTerm.getId() + " and student_number like '"+ username + "' order by course_code,start_week ";
		//query = entityManager.createNativeQuery("call proc_get_school_course_student("+ username + ")");

		
		
		// 如果老師，則顯示老師的本人的排課記錄
		if (shareService.getUserDetail().getAuthorities().toString().indexOf("TEACHER")!=-1) {
			// 教务相关课程排课
			mav.addObject("timetableAppointmenta", teacherList);
		}else{
			Query execQuery = entityManager.createNativeQuery("call proc_get_school_course_student(?1)");
            
			execQuery.setParameter(1, username);
			execQuery.executeUpdate();
			
			Query studentQuery = entityManager.createNativeQuery(studentSql );
			List<Object[]> studentList= studentQuery.getResultList(); 
			
			// 教务相关课程排课
			mav.addObject("timetableAppointmenta", studentList);
		}
		*/
		
		// 根据当前用户去查找教师的预约课程的id的集合
		List<TimetableTeacherRelated> timetableTeacherRelated = timetableTeacherRelatedDAO
				.executeQuery("select t from TimetableTeacherRelated t where t.user.username='"
							+ username
							+ "' order by t.timetableAppointment.courseCode ,t.timetableAppointment.weekday,t.timetableAppointment.timetableNumber desc");

		// 根据当前学生去查找教师的预约课程的id的集合
		List<TimetableLabRelated> timetableLabRelated = timetableLabRelatedDAO.executeQuery(
				"select t from TimetableLabRelated t ", 0, -1);

		mav.addObject("timetableTeacherRelated", timetableTeacherRelated);
		String sql = "";
		// 根据获取的教师的集合id获取该老师所有的预约课程
		List<TimetableAppointment> timetableAppointment = new ArrayList<TimetableAppointment>();
		List<TimetableAppointment> selfTimetableAppointment = new ArrayList<TimetableAppointment>();
		if (shareService.getUserDetail().getUserRole().trim().equals("1")) {
			// 如果僅為老師，則顯示老師的本人的排課記錄
			
				for (TimetableTeacherRelated t : timetableTeacherRelated) {
					if (t.getTimetableAppointment().getStatus() == 1) {
						if (t.getTimetableAppointment().getTimetableStyle() == 5
								|| t.getTimetableAppointment().getTimetableStyle() == 6) {
							if (t.getTimetableAppointment().getTimetableSelfCourse().getSchoolTerm().getId() == SchoolTerm.getId() || t.getTimetableAppointment().getStatus() == 1) {
								selfTimetableAppointment.add(t.getTimetableAppointment());
							}
						} else {
							if (t.getTimetableAppointment().getSchoolCourse().getSchoolTerm().getId() == SchoolTerm
									.getId() || t.getTimetableAppointment().getStatus() == 1) {
								timetableAppointment.add(t.getTimetableAppointment());
							}
						}
					}
				}
		} else {
			// 自主排课分批排课(含二次分批及自主分批排课)
			sql = "select t from TimetableGroupStudents t where  t.user.username like '"
					+ shareService.getUserDetail().getUsername() + "' ";
			List<TimetableGroupStudents> tass = timetableGroupStudentsDAO.executeQuery(sql, 0, -1);
			if (tass.size() > 0) {
				for (TimetableGroupStudents ta : tass) {
					for (TimetableAppointment te : ta.getTimetableGroup().getTimetableAppointments()) {
						// 自主分批排课
						if (te.getTimetableStyle() == 6 && te.getStatus() == 1
								&& te.getTimetableSelfCourse().getSchoolTerm().getId() == SchoolTerm.getId()) {
							selfTimetableAppointment.add(te);
						}
						// 二次分批排课
						if (te.getTimetableStyle() == 4 && te.getStatus() == 1 && te.getSchoolCourseDetail() != null
								&& te.getSchoolCourseDetail().getSchoolTerm().getId() == SchoolTerm.getId()) {
							timetableAppointment.add(te);
						}
					}
				}
			}

			// 教务排课、二次不分批排课
			sql = "select  c from SchoolCourseStudent c where c.userByStudentNumber.username like '"
					+ shareService.getUserDetail().getUsername() + "' and c.state=1 ";
			List<SchoolCourseStudent> tas = schoolCourseStudentDAO.executeQuery(sql, 0, -1);
			sql = "select  c from TimetableCourseStudent c where c.user.username like '"
					+ shareService.getUserDetail().getUsername() + "'";
			List<TimetableCourseStudent> tase = timetableCourseStudentDAO.executeQuery(sql, 0, -1);
			mav.addObject("tas", tas);
			for (TimetableLabRelated t : timetableLabRelated) {
				// 如果是自主排课
				if (t.getTimetableAppointment().getTimetableStyle() == 5) {
					for (TimetableCourseStudent ta : tase) {
						if (ta.getTimetableSelfCourse() != null) {
							for (TimetableAppointment ltimetableAppointment : ta.getTimetableSelfCourse()
									.getTimetableAppointments()) {
								if (t.getTimetableAppointment().getId() == ltimetableAppointment.getId()&&
										t.getTimetableAppointment().getTimetableSelfCourse().getSchoolTerm().getId()==SchoolTerm.getId()) {
									if (t.getTimetableAppointment().getStatus() == 1) {
										selfTimetableAppointment.add(t.getTimetableAppointment());
									}

								}
							}
						}
					}
					// 教务课程的不分组排课
				} else if (t.getTimetableAppointment().getTimetableStyle() == 1
						|| t.getTimetableAppointment().getTimetableStyle() == 2
						|| t.getTimetableAppointment().getTimetableStyle() == 3) {
					for (SchoolCourseStudent ta : tas) {
						if (ta.getSchoolCourseDetail() != null) {
							for (TimetableAppointment ltimetableAppointment : ta.getSchoolCourseDetail()
									.getTimetableAppointments()) {
								if (t.getTimetableAppointment().getId() == ltimetableAppointment.getId()&&
										t.getTimetableAppointment().getSchoolCourseDetail().getSchoolTerm().getId()==SchoolTerm.getId()) {
									if (t.getTimetableAppointment().getStatus() == 1) {
										timetableAppointment.add(t.getTimetableAppointment());
									}
								}
							}
						}
					}
				}
			}
		}

		// 教务相关课程排课
		mav.addObject("timetableAppointment", timetableAppointment);
		// 自主课程相关排课
		mav.addObject("selfTimetableAppointment", selfTimetableAppointment);
		/*	if (shareService.getUserDetail().getAuthorities().toString().indexOf("TEACHER")!=-1) {
			// 如果老師，則顯示老師的本人的排課記錄
			if (shareService.getUserDetail().getAuthorities().toString().indexOf("EXCENTERDIRECTOR") == -1
					&& shareService.getUserDetail().getAuthorities().toString().indexOf("SUPERADMIN") == -1) {
				for (TimetableTeacherRelated t : timetableTeacherRelated) {
					if (t.getTimetableAppointment().getStatus() == 1) {
						if (t.getTimetableAppointment().getTimetableStyle() == 5
								|| t.getTimetableAppointment().getTimetableStyle() == 6) {
							if (t.getTimetableAppointment().getTimetableSelfCourse().getSchoolTerm().getId() == terms
									.get(0).getId() || t.getTimetableAppointment().getStatus() == 1) {
								selfTimetableAppointment.add(t.getTimetableAppointment());
							}
						} else {
							if (t.getTimetableAppointment().getSchoolCourse().getSchoolTerm().getId() == terms.get(0)
									.getId() || t.getTimetableAppointment().getStatus() == 1) {
								timetableAppointment.add(t.getTimetableAppointment());
							}
						}
					}
				}
			}
		} else {
			// 排课分批排课(含二次分批及自主分批排课)
			sql = "select t from TimetableGroupStudents t where  t.user.username like '"
					+ shareService.getUserDetail().getUsername() + "'";
			List<TimetableGroupStudents> tass = timetableGroupStudentsDAO.executeQuery(sql);
			if (tass.size() > 0) {
				for (TimetableGroupStudents ta : tass) {
					for (TimetableAppointment te : ta.getTimetableGroup().getTimetableAppointments()) {
						// 自主分批排课
						if (te.getTimetableStyle() == 6 && te.getStatus() == 1
								&& te.getTimetableSelfCourse().getSchoolTerm().getId() == terms.get(0).getId()) {
							selfTimetableAppointment.add(te);
						}
						// 二次分批排课
						if (te.getTimetableStyle() == 4 && te.getStatus() == 1 && te.getSchoolCourseDetail() != null
								&& te.getSchoolCourseDetail().getSchoolTerm().getId() == terms.get(0).getId()) {
							timetableAppointment.add(te);
						}
					}
				}
			}

			// 教务排课、二次不分批排课
			sql = "select  c from SchoolCourseStudent c where c.userByStudentNumber.username like '"
					+ shareService.getUserDetail().getUsername() + "'";
			List<SchoolCourseStudent> tas = schoolCourseStudentDAO.executeQuery(sql, -1, -1);
			sql = "select  c from TimetableCourseStudent c where c.user.username like '"
					+ shareService.getUserDetail().getUsername() + "'";
			List<TimetableCourseStudent> tase = timetableCourseStudentDAO.executeQuery(sql, -1, -1);
			mav.addObject("tas", tas);
			for (TimetableLabRelated t : timetableLabRelated) {
				// 如果是自主排课
				if (t.getTimetableAppointment().getTimetableStyle() == 5) {
					for (TimetableCourseStudent ta : tase) {
						if (ta.getTimetableSelfCourse() != null) {
							for (TimetableAppointment ltimetableAppointment : ta.getTimetableSelfCourse()
									.getTimetableAppointments()) {
								if (t.getTimetableAppointment().getId() == ltimetableAppointment.getId()) {
									if (t.getTimetableAppointment().getStatus() == 1) {
										selfTimetableAppointment.add(t.getTimetableAppointment());
									}

								}
							}
						}
					}
					// 教务课程的不分组排课
				} else if (t.getTimetableAppointment().getTimetableStyle() == 1
						|| t.getTimetableAppointment().getTimetableStyle() == 2
						|| t.getTimetableAppointment().getTimetableStyle() == 3) {
					for (SchoolCourseStudent ta : tas) {
						if (ta.getSchoolCourseDetail() != null) {
							for (TimetableAppointment ltimetableAppointment : ta.getSchoolCourseDetail()
									.getTimetableAppointments()) {
								if (t.getTimetableAppointment().getId() == ltimetableAppointment.getId()) {
									if (t.getTimetableAppointment().getStatus() == 1) {
										timetableAppointment.add(t.getTimetableAppointment());
									}
								}
							}
						}
					}
				}
			}
		}*/
		//获取我的课程返回list
		/*String myCourseSql ="select course_name,course_code,teacher,start_week,end_week,start_class,end_class,weekday,cname,lab_id  from view_timetable_teacher_course where term_id = " + SchoolTerm.getId() + " and teacher like '"+ username + "' group by course_code";
		Query myCourseQuery= entityManager.createNativeQuery(myCourseSql );
		List<Object[]> myCourseList= myCourseQuery.getResultList();  */

		// 我的课程
		//mav.addObject("myCourseList", myCourseList);

		
		
	/*	String jpql= String.format("select c from TimetableAppointment c");  		
		EntityManager entityManager= objectDaoService.getEntityManager();  
		        
		if (shareService.getUserDetail().getAuthorities().toString().indexOf("TEACHER")!=-1) {
			Query query= entityManager.createNativeQuery("select course_name,course_code,teacher,start_week,end_week,start_class,end_class,weekday,cname,lab_id from view_teacher_course where teacher like '" +shareService.getUserDetail().getUsername() +"' order by  course_code,weekday,start_class desc" );
			List<Object[]> teacherList= query.getResultList();  
	        mav.addObject("timetableList", teacherList);
		}else{
	       
			Query query= entityManager.createNativeQuery("select course_name,course_code,teacher,start_week,end_week,start_class,end_class,weekday,teacher_name,lab_id from view_student_course where student_number like '" +shareService.getUserDetail().getUsername() +"'  order by  course_code,weekday,start_class desc" );
		    List<Object[]> studentList= query.getResultList();  
		    mav.addObject("timetableList", studentList);
		}
		*/
        
        mav.setViewName("/personal/message/mySelfTimetable.jsp");
		
		return mav;
	}

	/*********************************************************************************
	 * @description:保存我的个人信息
	 * @author:方正2014/08/07
	 ************************************************************************************/
	@RequestMapping("/personal/saveMyInfo")
	public String saveMyInfo(@ModelAttribute User user,HttpServletRequest request) {

		// 获取当前用户
		//String photo=request.getParameter("photo");
		String i = user.getUsername();
		User user1 = userDAO.findUserByPrimaryKey(i);

		// 直接设置用户的email telephone信息
		user1.setEmail(user.getEmail());
		user1.setTelephone(user.getTelephone());

		// 保存信息
		userDAO.store(user1);
		return "redirect:/personal/listMyInfo";
	}
	/*********************************************************************************
	 * 功能:显示我的消息列表
	 * 作者：贺子龙
	 * 时间：2015-09-15 20:13:40
	 ************************************************************************************/
	
	
	@RequestMapping("/personal/messageList")
	public ModelAndView messageList(@ModelAttribute Message message,@RequestParam int currpage,HttpServletRequest request){
		
		ModelAndView mav=new ModelAndView();
		String starttime=request.getParameter("starttime");
		String endtime=request.getParameter("endtime");
		mav.addObject("starttime", starttime);
		mav.addObject("endtime", endtime);
		int pageSize = 20;
		List<Message>  messages=messageService.findMessageBySome(message, currpage-1, pageSize,request);
		int totalRecords =messageService.countmessage(message,request);
		mav.addObject("messages",messages);
//		System.out.println(" totalRecords ="+totalRecords);
		Map<String, Integer> pageModel = shareService.getPage(
				currpage, pageSize, totalRecords);
		mav.addObject("pageModel", pageModel);
		mav.addObject("totalRecords", totalRecords);
		mav.addObject("page", currpage);
		mav.setViewName("personal/message/message.jsp");
		return mav;
		
	}
	
	
	/***************************************
	 * 功能：我的消息 查看，更新信息的状态，返回信息的内容；
	 * 作者：贺子龙
	 * 时间：2015-09-16 13:33:13
	 ****************************************/
	@RequestMapping("/setMsgStateNew")
	public @ResponseBody String[] setMsgState(@RequestParam Integer id) {
		Message message=messageService.findMessageByPrimaryKey(id);//查找消息
		message.setMessageState(1);//消息状态设置为已读
		messageDAO.store(message);
		Integer flag=message.getMessageState();
		String[] ss = new String[3];
		ss[0] = String.valueOf(flag);
		ss[1] = message.getContent();
		if(message.getType() != null)
		{
			ss[2] = message.getType();
		}
		else{
			ss[2] = "";
		}
		return ss;
	}
	
	
	//http://localhost:8080/xidlims/test/zhudianya?labroomID=123&weekDay=1&section=2
	
	@RequestMapping("/test/zhudianya")
	@Transactional
	public void test(String id, String labroomID,String weekDay,String section,HttpServletResponse response) throws IOException {
		String ID=labroomID;
		//String ID = new String(labroomID.getBytes("ISO8859-1"),"UTF-8");
		String Day=weekDay;
		String Section=section;
		System.out.println("ID -> "+ID); 
		System.out.println("Day -> "+Day); 
		System.out.println("Section -> "+Section); 
		
		SchoolTerm schoolTerm = shareService.getBelongsSchoolTerm(Calendar.getInstance());
		String[] classes = section.split(",");
		int[] c = new int[classes.length];
		for(int i=0; i<classes.length; i++){
			c[i] = Integer.parseInt(classes[i]);
		}
		int[] labrooms = new int[1];
		labrooms[0] = Integer.parseInt(labroomID);
		
		Integer[] intWeeks = outerApplicationService.getValidLabWeeks(schoolTerm.getId(), c, labrooms, Integer.parseInt(weekDay));
		response.setContentType("text/html；charset=utf-8");
        response.setCharacterEncoding("utf-8"); 
 	   	PrintWriter out = response.getWriter();
 	   	String weeks ="";
 	   	if(intWeeks.length > 0){
 	   		weeks +=intWeeks[0].toString();
 	   		for(int i = 1; i < intWeeks.length; i++){
 	   			weeks +=","+intWeeks[i].toString();
 	   		}
 	   	}
 	   	out.print(weeks);
		
	 }
	@RequestMapping("/test/zhudianyaAll")
	@Transactional
	public void testAll(String labroomID,String weekDay,String section,String week,
			String act,String actName,String content,String ps,String students,
			HttpServletResponse response) throws IOException {
		String ID=labroomID;
		//String ID = new String(labroomID.getBytes("ISO8859-1"),"UTF-8");
		String Day=weekDay;
		String Section=section;
		String Week=week;
		String Act=act;
		String ActName=new String(actName.getBytes("ISO8859-1"),"UTF-8");
		String Content=new String(content.getBytes("ISO8859-1"),"UTF-8");
		String Ps=new String(ps.getBytes("ISO8859-1"),"UTF-8");
		String Students=new String(students.getBytes("ISO8859-1"),"UTF-8");
		System.out.println("ID -> "+ID); 
		System.out.println("Day -> "+Day); 
		System.out.println("Section -> "+Section); 
		System.out.println("Week -> "+Week); 
		System.out.println("Act -> "+Act); 
		System.out.println("ActName -> "+ActName); 
		System.out.println("Content -> "+Content); 
		System.out.println("Ps -> "+Ps); 
		System.out.println("Students -> "+Students); 
		response.setContentType("text/ html；charset=utf-8");
        response.setCharacterEncoding("utf-8"); 
 	   	PrintWriter out = response.getWriter();
 	   	out.print("success");
		
	 }
	
	
	
}
