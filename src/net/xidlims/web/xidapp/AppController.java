package net.xidlims.web.xidapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.xidlims.JsonDateValueProcessor;
import net.xidlims.dao.SchoolCourseStudentDAO;
import net.xidlims.dao.TAssignmentQuestionpoolDAO;
import net.xidlims.dao.TimetableCourseStudentDAO;
import net.xidlims.dao.TimetableGroupStudentsDAO;
import net.xidlims.dao.TimetableLabRelatedDAO;
import net.xidlims.dao.TimetableTeacherRelatedDAO;
import net.xidlims.dao.UserDAO;
import net.xidlims.dao.WkUploadDAO;
import net.xidlims.domain.AppGroup;
import net.xidlims.domain.AppPostImages;
import net.xidlims.domain.AppPostReply;
import net.xidlims.domain.AppPostlist;
import net.xidlims.domain.AppQuestionnaire;
import net.xidlims.domain.SchoolCourseStudent;
import net.xidlims.domain.SchoolTerm;
import net.xidlims.domain.TAssignmentQuestionpool;
import net.xidlims.domain.TCourseSite;
import net.xidlims.domain.TimetableAppointment;
import net.xidlims.domain.TimetableCourseStudent;
import net.xidlims.domain.TimetableGroupStudents;
import net.xidlims.domain.TimetableLabRelated;
import net.xidlims.domain.TimetableTeacherRelated;
import net.xidlims.domain.User;
import net.xidlims.domain.WkUpload;
import net.xidlims.service.common.ShareService;
import net.xidlims.service.lab.LabReservationService;
import net.xidlims.service.tcoursesite.TAssignmentQuestionPoolService;
import net.xidlims.service.tcoursesite.TCourseSiteService;
import net.xidlims.service.xidapp.AppService;
import net.xidlims.web.aop.SystemServiceLog;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import app.xidlims.Album;
import app.xidlims.Annoucement;
import app.xidlims.AnnoucementList;
import app.xidlims.Appointment;
import app.xidlims.LearningChapter;
import app.xidlims.LearningVideoNew;
import app.xidlims.NewAlbum;
import app.xidlims.NewFolder;
import app.xidlims.NewPostList;
import app.xidlims.NewQuestionnaire;
import app.xidlims.ReturnDownloadFile;
import app.xidlims.ReturnDownloadFolder;
import app.xidlims.SaveQuestionnaire;
import app.xidlims.PostDetail;
import app.xidlims.Question;
import app.xidlims.QuestionNaireDetail;
import app.xidlims.QuestionPool;
import app.xidlims.ShareAppPostReplyList;
import app.xidlims.ShareDetail;
import app.xidlims.ShareList;
import app.xidlims.GroupBack;

@Controller("AppController")
public class AppController {
	//定义保存所有Socket的ArrayList
	public static ArrayList<Socket> socketList = new ArrayList<Socket>();
	
	@Autowired
	UserDAO userDAO;
	@Autowired
	TimetableCourseStudentDAO timetableCourseStudentDAO;
	@Autowired
	SchoolCourseStudentDAO schoolCourseStudentDAO;
	@Autowired
	TimetableGroupStudentsDAO timetableGroupStudentsDAO;
	@Autowired
	ShareService shareService;
	@Autowired
	LabReservationService labReservationService;
	@Autowired
	AppService appService;
	@Autowired
	TimetableTeacherRelatedDAO timetableTeacherRelatedDAO;
	@Autowired
	TimetableLabRelatedDAO timetableLabRelatedDAO;
	@Autowired
	WkUploadDAO wkUploadDAO;
	@Autowired
	TCourseSiteService tCourseSiteService;
	@Autowired
	TAssignmentQuestionPoolService tAssignmentQuestionPoolService;
	@Autowired
	TAssignmentQuestionpoolDAO tAssignmentQuestionpoolDAO;
	
	public static ArrayList<Socket> socketlist =
			new ArrayList<Socket>();
	/*********************************************************************************
	 * @description:APP登录
	 * @author:张凯	2017/03/06
	 ************************************************************************************/
	@RequestMapping("/xidapp/login")
	@Transactional
	public void loginApp(String username, String password, HttpServletResponse response) throws IOException {
		User user = userDAO.findUserByUsername(username);
		String flag = null;
		if(user!=null&&!user.equals("")){
			if(password.equals(user.getPassword())){
				flag = "succeed";
			}
			else{
				flag = "1";
			}
		}else{
			flag = "0";
		}
		if(flag.equals("succeed")){
			/*ServerSocket serverSocket = new ServerSocket(30000); 
			//SocketThread s = new SocketThread(serverSocket);
			Socket socket = serverSocket.accept();
			socketlist.add(socket);
            System.out.println(socketlist);
            if(user.getOnline()==1&&user.getPhoneIP()!=null&&user.getPhoneIP().equals("")){
            	for(Iterator<Socket> it = socketlist.iterator();it.hasNext();){
            		Socket s = it.next();
            		if(user.getPhoneIP().contains(s.getInetAddress().getHostAddress())){
            			OutputStream outputStream = socket.getOutputStream();
            			outputStream.write("账号已在其他地方登录！\n".getBytes("utf-8"));
            			outputStream.close();
            			break;
            		}
            	}
            }*/
            Integer online = 1;
			user.setOnline(online);
			//user.setPhoneIP(socket.getInetAddress().getHostAddress());
			userDAO.store(user);
			userDAO.flush();
		}
		response.setContentType("text/ html;charset=utf-8");
		response.setCharacterEncoding("utf-8"); 
		PrintWriter out = response.getWriter();
 	   	out.print(flag);
 	   	out.print("<br/>");
 	   	out.print(username);
 	   	out.flush();
		out.close();
	}
	
	/************************************************************
	 * @throws IOException 
	 * @内容: 校历模块  张凯  2017/4/24
	 * 
	 ************************************************************/
	@RequestMapping("/xidapp/schoolWeekApp")
	@Transactional
	public void schoolWeekApp(String username,HttpServletResponse response) throws IOException {
			
			// 获取当前周次
			int week = shareService.findNewWeek();
			JSONObject jsonObject = new JSONObject();
			Configuration config = new Configuration().configure();
			SessionFactory sf = config.buildSessionFactory();
			Session session = sf.openSession();
			response.setContentType("text/ html；charset=utf-8");
			response.setCharacterEncoding("utf-8");
			String sqlWeek = " select term_id, week, weekday, UNIX_TIMESTAMP(date) weekDate from school_week";
			SQLQuery queryList = session.createSQLQuery(sqlWeek); //返回对象
	        queryList.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
	        @SuppressWarnings("unchecked")
			List<Map<String, Object>> results = queryList.list();
	        JsonConfig jsonConfig = new JsonConfig();
	        // 设置javabean中日期转换时的格式
	        jsonConfig.registerJsonValueProcessor(Date.class,new JsonDateValueProcessor());
	 	    JSONArray jsonArray = JSONArray.fromObject(results,jsonConfig);
	 	    PrintWriter out = response.getWriter();
	 	    jsonObject.put("schoolWeeks", jsonArray);
	 	    out.print(jsonObject);
	 	   	out.flush();
			out.close();
		}
	
	/************************************************************
	 * @throws IOException 
	 * @内容: 跳入个人中心模块 方正 2014/8/14
	 * 
	 ************************************************************/
	@RequestMapping("/xidapp/mySelfTimetable")
	@Transactional
	public void mySelfTimetableApp(String username,HttpServletResponse response) throws IOException {
		
		// 获取当前用户
		User user = userDAO.findUserByUsername(username);
		String cname = user.getCname();
		// 获取当前周次
		int week = shareService.findNewWeek();

		// 获取当前学期
		SchoolTerm SchoolTerm = shareService.getBelongsSchoolTerm(Calendar.getInstance());
		String term = SchoolTerm.getTermName();
		// 根据当前用户去查找教师的预约课程的id的集合
		List<TimetableTeacherRelated> timetableTeacherRelated = timetableTeacherRelatedDAO
				.executeQuery("select t from TimetableTeacherRelated t where t.user.username='"
							+ username
							+ "' order by t.timetableAppointment.courseCode ,t.timetableAppointment.weekday,t.timetableAppointment.timetableNumber desc");

		// 根据当前学生去查找教师的预约课程的id的集合
		List<TimetableLabRelated> timetableLabRelated = timetableLabRelatedDAO.executeQuery(
				"select t from TimetableLabRelated t ", 0, -1);
		String sql = "";
		// 根据获取的教师的集合id获取该老师所有的预约课程
		List<TimetableAppointment> timetableAppointment = new ArrayList<TimetableAppointment>();
		List<TimetableAppointment> selfTimetableAppointment = new ArrayList<TimetableAppointment>();
		if (user.getUserRole().trim().equals("1")) {
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
					+ user.getUsername() + "' ";
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
					+ user.getUsername() + "' and c.state=1 ";
			List<SchoolCourseStudent> tas = schoolCourseStudentDAO.executeQuery(sql, 0, -1);
			sql = "select  c from TimetableCourseStudent c where c.user.username like '"
					+ user.getUsername() + "'";
			List<TimetableCourseStudent> tase = timetableCourseStudentDAO.executeQuery(sql, 0, -1);
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

	
		JSONObject jsonObject = new  JSONObject();
		 Configuration config = new Configuration().configure();
         SessionFactory sf     = config.buildSessionFactory();
         Session session = sf.openSession();
         Transaction ts = session.beginTransaction();
         response.setContentType("text/ html；charset=utf-8");
         response.setCharacterEncoding("utf-8"); 
  	   PrintWriter out = response.getWriter();
		if(selfTimetableAppointment != null && selfTimetableAppointment.size() != 0){
	    	   String finalSql = "select * from view_final_timetable_appointment where";
	    	   int count = 0;
	    	   for(TimetableAppointment t:selfTimetableAppointment){
	    		   if(count == 0){
	    			   finalSql += " timetable_appointmentId ="+t.getId();
	    			   count++;
	    		   }
	    		   else{
	    			   finalSql += " or timetable_appointmentId ="+t.getId();
	    		   }
	    	   }
	           SQLQuery queryList = session.createSQLQuery(finalSql); //返回对象
	           queryList.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
	           @SuppressWarnings("unchecked")
	           List<Map<String, Object>> results = queryList.list();
	    	   JSONArray jsonArray = JSONArray.fromObject(results);
	    	   jsonObject.put("appointments", jsonArray);
	    	   //out.println(jsonArray);
	       }
		
 	    out.print(jsonObject);
 	   	out.flush();
		out.close();
	}
	
	/*********************************************************************************
	 * @description:实验室预约
	 * @author:张凯	2017/03/28
	 ************************************************************************************/
	@RequestMapping("/xidapp/labAnnexList")
	@Transactional
	public void labAnnexListApp(String labRoomName, HttpServletResponse response) throws IOException{
		//获取sql语句
		String sqlLabRoom = appService.findAllLabRoom(labRoomName);
		JSONObject jsonObject = new  JSONObject();
		Configuration config = new Configuration().configure();
        SessionFactory sf = config.buildSessionFactory();
        Session session = sf.openSession();
        //Transaction ts = session.beginTransaction();
        response.setContentType("text/ html；charset=utf-8");
        response.setCharacterEncoding("utf-8"); 
		SQLQuery queryList = session.createSQLQuery(sqlLabRoom); //返回对象
        queryList.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        @SuppressWarnings("unchecked")
        //获取APP所需数据里的字段
		List<Map<String, Object>> results = queryList.list();
 	   	PrintWriter out = response.getWriter();
 	   	JSONArray jsonArray = JSONArray.fromObject(results);
 	   	jsonObject.put("labrooms", jsonArray);
 	   	out.print(jsonObject);
 	   	out.flush();
		out.close();
	}
	
	/*********************************************************************************
	 * @description:我的课程
	 * @author:张凯	2017/03/29
	 * @throws IOException 
	 ************************************************************************************/
	@RequestMapping("/xidapp/studentCourse")
	@Transactional
	//学生课程这里还有问题得在修改；
	public void studentCourseApp(String username, HttpServletResponse response) throws IOException{
		//获取数据库语句
		String sqlStuCourse = appService.findCoursesByStudent(username);
		JSONObject jsonObject = new  JSONObject();
		Configuration config = new Configuration().configure();
        SessionFactory sf = config.buildSessionFactory();
        Session session = sf.openSession();
        //Transaction ts = session.beginTransaction();
        response.setContentType("text/ html；charset=utf-8");
        response.setCharacterEncoding("utf-8"); 
		SQLQuery queryList = session.createSQLQuery(sqlStuCourse); //返回对象
        queryList.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        @SuppressWarnings("unchecked")
        //获取APP所需数据里的字段
		List<Map<String, Object>> results = queryList.list();
 	   	PrintWriter out = response.getWriter();
 	   	JSONArray jsonArray = JSONArray.fromObject(results);
 	   	jsonObject.put("studentCourses", jsonArray);
 	   	out.print(jsonObject);
 	   	out.flush();
		out.close();
	}
	
	/*********************************************************************************
	 * @description:我的班级
	 * @author:张凯	2017/03/29
	 * @throws IOException 
	 ************************************************************************************/
	@RequestMapping("/xidapp/studentClass")
	@Transactional
	public void studentClassApp(String username, HttpServletResponse response) throws IOException{
		//获取数据库语句
		String sqlStuClass = appService.findClassByStudent(username);
		JSONObject jsonObject = new  JSONObject();
		Configuration config = new Configuration().configure();
        SessionFactory sf = config.buildSessionFactory();
        Session session = sf.openSession();
        //Transaction ts = session.beginTransaction();
        response.setContentType("text/ html；charset=utf-8");
        response.setCharacterEncoding("utf-8"); 
		SQLQuery queryList = session.createSQLQuery(sqlStuClass); //返回对象
        queryList.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        @SuppressWarnings("unchecked")
        //获取APP所需数据里的字段
		List<Map<String, Object>> results = queryList.list();
 	   	PrintWriter out = response.getWriter();
 	   	JSONArray jsonArray = JSONArray.fromObject(results);
 	   	jsonObject.put("classStudent", jsonArray);
 	   	out.print(jsonObject);
 	   	out.flush();
		out.close();
	}
	
	/*********************************************************************************
	 * @description:我的成绩
	 * @author:张凯	2017/03/29
	 * @throws IOException 
	 ************************************************************************************/
	@RequestMapping("/xidapp/studentGrade")
	@Transactional
	public void studentGradeApp(String username, HttpServletResponse response) throws IOException{
		String sql = appService.getStudentGrades(username);
		JSONObject jsonObject = new JSONObject();
		Configuration config = new Configuration().configure();
		SessionFactory sf = config.buildSessionFactory();
		Session session = sf.openSession();
		response.setContentType("text/ html；charset=utf-8");
		response.setCharacterEncoding("utf-8");
		SQLQuery lists = session.createSQLQuery(sql);
		lists.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
	    @SuppressWarnings("unchecked")
		List<Map<String,Object>> results = lists.list();
		PrintWriter out = response.getWriter();
		JSONArray jsonArray = JSONArray.fromObject(results);
		jsonObject.put("StudentGrades", jsonArray);
		out.print(jsonObject);
 	   	out.flush();
		out.close();
	}
	
	/*********************************************************************************
	 * @description:实验室预约判冲
	 * @author:张凯	2017/04/25
	 * @throws IOException 
	 ************************************************************************************/
	@RequestMapping("/xidapp/labAnnexJudgement")
	@Transactional
	public void labAnnexJudgementApp(String labroomID, String weekDay, String section, HttpServletResponse response) throws IOException{
		//获取可用的周次信息
		String week = appService.getUnusedWeeks(labroomID, weekDay, section);
		response.setContentType("text/ html;charset=utf-8");
		response.setCharacterEncoding("utf-8"); 
		PrintWriter out = response.getWriter();
 	   	out.print(week);
 	   	out.flush();
		out.close();
	}
	
	/*********************************************************************************
	 * @description:实验室预约
	 * @author:张凯	2017/04/27
	 * @throws IOException 
	 * @throws ParseException 
	 ************************************************************************************/
	@RequestMapping("/xidapp/labAnnexAppointment")
	@Transactional
	public void labAnnexAppointmentApp(String labroomID,String weekDay,String section,String week,String act, 
			String actName,String content,String ps,String students, String username, HttpServletResponse response) throws IOException, ParseException{
		//审核实验预约并保存通过的实验室预约
		String result = appService.auditAndSaveLabReservation(labroomID, weekDay, section, week, act, actName, content, ps, students, username);
		response.setContentType("text/ html;charset=utf-8");
		response.setCharacterEncoding("utf-8"); 
		PrintWriter out = response.getWriter();
 	   	out.print(result);
 	   	out.flush();
		out.close();
	}
	
	/*********************************************************************************
	 * @description:获取视频列表和课程章节
	 * @author:张凯	2017/05/3
	 * @throws IOException 
	 ************************************************************************************/
	@RequestMapping("/xidapp/videoListApp")
	@Transactional
	public void videoListApp(String id, HttpServletResponse response) throws IOException{
		PrintWriter out = response.getWriter();
		//设置输出
		response.setContentType("text/ html；charset=utf-8");
		response.setCharacterEncoding("utf-8");
		//获取课程下的视频
		List<LearningVideoNew> list = appService.getVideosList(id);
		//创建jsonObject
		JSONObject jsonObject = new  JSONObject();
		JSONArray jsonArray = JSONArray.fromObject(list);
		jsonObject.put("videos", jsonArray);
 	   	//获取课程下所有章节
 	   	List<LearningChapter> list2 = appService.getChapters(id);
 		JSONArray jsonArray2 = JSONArray.fromObject(list2);
 	   	jsonObject.put("chapters", jsonArray2);
 	   	// 打包成JSON格式
 	   	out.print(jsonObject);
		out.flush();
		out.close();
	}
	
	/*********************************************************************************
	 * @description:获取视频列表和课程章节
	 * @author:张凯	2017/05/3
	 * @throws IOException 
	 ************************************************************************************/
	@RequestMapping("/xidapp/watchVideoApp")
	@Transactional
	public void watchVideoApp(String id , HttpServletResponse response, HttpServletRequest request) throws IOException{
		WkUpload video = wkUploadDAO.findWkUploadById(Integer.parseInt(id));
		String path = video.getUrl();
		String servUrl = request.getRequestURL().toString();
		servUrl = servUrl.substring(0,servUrl.length()-request.getRequestURI().length())+request.getContextPath();
		response.setContentType("text/ html;charset=utf-8");
		response.setCharacterEncoding("utf-8"); 
		PrintWriter out = response.getWriter();
		path += servUrl+"/"+path;
 	   	out.print(path);
 	   	out.flush();
		out.close();
	}
	
	/*********************************************************************************
	 * @description:获取题库列表
	 * @author:唐钦邦 2017/08/18
	 * @throws IOException 
	 ************************************************************************************/
	@RequestMapping("/xidapp/find")
	@Transactional
	public void findQuestionList(Integer tCourseSiteId,Integer type,HttpServletResponse response) throws IOException {
		List<QuestionPool> questionPoolList = new ArrayList<QuestionPool>();
		PrintWriter out = response.getWriter();
		response.setContentType("text/ html；charset=utf-8");
		response.setCharacterEncoding("utf-8");
		String sql = " select t from TAssignmentQuestionpool t where t.type = "
				+ type;
		List<TAssignmentQuestionpool> questionPoolsPublic = tAssignmentQuestionpoolDAO
				.executeQuery(sql);
		TAssignmentQuestionpool t ;
		t=questionPoolsPublic.get(0);
		if(t.getTAssignmentQuestionpool().getType()== 1){
			 String sql2 = " select t from TAssignmentQuestionpool t where t.type = "
							+ type;
			 List<TAssignmentQuestionpool> questionPools = tAssignmentQuestionpoolDAO
					.executeQuery(sql2);
			 for (TAssignmentQuestionpool c : questionPools) {
				 	QuestionPool q = new QuestionPool(c.getQuestionpoolId(),
						c.getTitle());
					questionPoolList.add(q);
				}
		}else if(t.getTAssignmentQuestionpool().getType()== 2){
			// 选择的课程中心
			TCourseSite tCourseSite = tCourseSiteService.findCourseSiteById(tCourseSiteId);
			//课程对应题库                                              
			List<TAssignmentQuestionpool> tAssignmentQuestionpools = tAssignmentQuestionPoolService
					.findQuestionList(tCourseSiteId);
			for (TAssignmentQuestionpool a : tAssignmentQuestionpools) {
				if(a.getTAssignmentQuestionpool().getType()!=1){
				QuestionPool q = new QuestionPool(a.getQuestionpoolId(),
					a.getTitle());
				questionPoolList.add(q);
				}
			}
		}
		JSONObject jsonobject = new JSONObject();
		JSONArray jsonArray = JSONArray.fromObject(questionPoolList);
		jsonobject.put("questionPoolList", jsonArray);
		out.print(jsonobject);
		out.flush();
		out.close();
	}

	/*********************************************************************************
	 * @description:获取试题列表
	 * @author:张凯	2017/05/20
	 * @throws IOException 
	 ************************************************************************************/
	@RequestMapping("/xidapp/getQuestionItemApp")
	@Transactional
	public void getQuestionItemApp(Integer ID, HttpServletResponse response) throws IOException{
		List<Question> questions = appService.getQuestionList(ID);
		PrintWriter out = response.getWriter();
		//设置输出
		response.setContentType("text/ html；charset=utf-8");
		response.setCharacterEncoding("utf-8");
		JSONObject jsonobject = new JSONObject();
		JSONArray jsonArray = JSONArray.fromObject(questions);
		jsonobject.put("question", jsonArray);
		out.print(jsonobject);
		out.flush();
		out.close();
	}
		
	/*********************************************************************************
	 * @description:保存客服端发过来的帖子信息
	 * @author:张凯	2017/06/29
	 * @throws IOException 
	 ************************************************************************************/
	@RequestMapping("/xidapp/newClassPost")
	@Transactional
	public void newClassPost(String title, String sponsor, String content, String ids,Integer groupID,
			HttpServletRequest request, HttpServletResponse response, Integer isStick) throws IOException{
		AppPostlist post = appService.savePost(title, sponsor, content,ids, "1", groupID, isStick);
		response.setContentType("text/html；charset=utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		if(post!=null&&!post.equals("")){
			out.print("succees");
		}else{
			out.print(-1);
		}
		out.flush();
		out.close();
	}
	
	/*********************************************************************************
	 * @description:获取帖子列表
	 * @author:张凯	2017/07/05(TQB)
	 * @throws IOException 
	 ************************************************************************************/
	@RequestMapping("/xidapp/classPostListApp")
 	@Transactional
 	public void classPostList(String username, HttpServletResponse response, HttpServletRequest request) throws IOException{
		List<NewPostList> postList = appService.getPostlist(username,request);
		response.setContentType("text/ html；charset=utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		JSONObject jsonObject = new JSONObject();
		JSONArray jsonArray = JSONArray.fromObject(postList);
		jsonObject.put("postList", jsonArray);
		out.print(jsonObject);
		out.flush();
		out.close();
	}
	

	/*********************************************************************************
	 * @description:查看讨论部分
	 * @author:张凯	2017/07/03
	 * @throws IOException 
	 ************************************************************************************/
	@RequestMapping("/xidapp/classPostViewApp")
 	@Transactional
	public void classPostView(String username, Integer ID, HttpServletResponse response, HttpServletRequest request) throws Exception {
 		//获取帖子内容			
		PostDetail postdetail = appService.viewPostDetails(username,ID,request);
 		JSONObject jsonObject = new JSONObject();
		response.setContentType("text/ html；charset=utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		JSONArray jsonArray1 = JSONArray.fromObject(postdetail);
		jsonObject.put("postDetails", jsonArray1);
		//获取帖子回复内容		
 		List<ShareAppPostReplyList> share = appService.getAppPostReplyList(ID);
 	//	JSONObject jsonObject2 = new  JSONObject();
 		response.setContentType("text/ html；charset=utf-8");
 	    response.setCharacterEncoding("utf-8"); 
 		PrintWriter out2 = response.getWriter();
 		JSONArray jsonArray = JSONArray.fromObject(share);
 		jsonObject.put("shareAppPostReplyList", jsonArray);
 		out.print(jsonObject);		
 		out.flush();
 		out.close();
	}
			
	/*********************************************************************************
	 * @description:帖子回复部分保存
	 * @author:张凯	2017/07/03
	 * @throws IOException 
	 ************************************************************************************/
	@RequestMapping("/xidapp/postReply")
	@Transactional
	public void postReply(Integer ID, String Sponsor, String comment, Integer toResponseID, 
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		appService.savePostReply(ID, Sponsor, comment, toResponseID);
		response.setContentType("text/ html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		out.print(1);
 	   	out.flush();
		out.close();
	}
		
	/*********************************************************************************
	 * @description:帖子点赞
	 * @author:张凯	2017/07/04
	 * @throws IOException 
	 ************************************************************************************/
	@RequestMapping("/xidapp/upvotePost")
	@Transactional
	public void upvotePostApp(Integer ID, String sponsor, Integer flag, HttpServletResponse response) throws IOException{
		PrintWriter out = response.getWriter();
		out.print(appService.saveUpvoteUserForPost(ID, sponsor, flag));
		out.flush();
		out.close();
	}
	
	/*********************************************************************************
	 * @description:帖子收藏
	 * @author:张凯	2017/07/05
	 * @throws IOException 
	 ************************************************************************************/
	@RequestMapping("/xidapp/collectPost")
	@Transactional
	public void collectPostAPP(Integer ID, String sponsor, Integer flag, HttpServletResponse response) throws IOException{
		PrintWriter out = response.getWriter();
		out.print(appService.saveCollectUserForPost(ID, sponsor, flag));
		out.flush();
		out.close();
	}
	
	/*********************************************************************************
	 * @description:图片上传部分
	 * @author:张凯	2017/6/29
	 * @throws IOException 
	 ************************************************************************************/
	@RequestMapping("/xidapp/imageUploadTest")
	@Transactional
	public void uploadImageForLabRoom(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String img = appService.uploadImageForLabRoom(request, response);
		response.setContentType("text/ html;charset=utf-8");
		response.setCharacterEncoding("utf-8"); 
		PrintWriter out = response.getWriter();
		out.print(img);
 	   	out.flush();
		out.close();
	}
	
	/*********************************************************************************
	 * @description:获取公告列表
	 * @author:张凯	2017/7/4
	 * @throws IOException 
	 ************************************************************************************/
	@RequestMapping("/xidapp/announcementList")
	@Transactional
	public void announcementAPP(HttpServletResponse response, String username) throws IOException{
		List<AnnoucementList> anns = appService.getAnnouncements(username);
		JSONObject jsonObject = new  JSONObject();
        response.setContentType("text/ html；charset=utf-8");
        response.setCharacterEncoding("utf-8"); 
 	   	PrintWriter out = response.getWriter();
 	   	JSONArray jsonArray = JSONArray.fromObject(anns);
 	   	jsonObject.put("announcementList", jsonArray);
 	   	out.print(jsonObject);
 	    out.flush();
		out.close();
	}
	
	/*********************************************************************************
	 * @description:公告详情
	 * @author:张凯	2017/7/4
	 * @throws IOException 
	 ************************************************************************************/
	/*@RequestMapping("/xidapp/announcementDetails")
	@Transactional
	public @ResponseBody Annoucement announcementDetailsApp(Integer ID, HttpServletResponse response) throws IOException{
		Annoucement ann = appService.getAnnoucementDetails(ID);
		return ann;
 	   	
	}*/
	
	/*********************************************************************************
	 * @description:保存新建公告
	 * @author:张凯	2017/7/4
	 * @throws IOException 
	 * 修改：唐钦邦
	 ************************************************************************************/
	@RequestMapping("/xidapp/saveNewAnnouncement")
	@Transactional
	public void newAnnouncementApp(String sponsor, String comment, String title, HttpServletResponse response) throws IOException{
		AnnoucementList anoun=appService.saveAnnoucement(sponsor, comment, title);
		JSONObject jsonObject = new JSONObject();
		response.setContentType("text/ html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
        JSONArray jsonArray = JSONArray.fromObject(anoun);
        jsonObject.put("announcementBack", jsonArray);
        out.print(jsonObject);
		out.flush();
		out.close();
	}
	
	/*********************************************************************************
	 * @description:新建分享
	 * @author:唐钦邦2017/08/16	
	 * @throws IOException 
	 ************************************************************************************/
    @RequestMapping("/xidapp/NewShare")
    public void NewShareApp(Integer ID, String username, String title,String postList, HttpServletResponse response) throws IOException{
    	username="00101117";
    	title="测试";
    	postList="6,7,8,9,10";
    	response.setContentType("text/ html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        out.print(appService.NewShare(ID, username, title,postList));
        out.flush();
        out.close();
    }
    
    /*********************************************************************************
	 * @description:上传分享
	 * @author:唐钦邦2017/08/16	
	 * @throws IOException 
	 ************************************************************************************/
    @RequestMapping("/xidapp/shareUploadTest")
	@Transactional
	public void uploadShareForLabRoom(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String img = appService.uploadShareForLabRoom(request, response);
		response.setContentType("text/ html;charset=utf-8");
		response.setCharacterEncoding("utf-8"); 
		PrintWriter out = response.getWriter();
		out.print(img);
 	   	out.flush();
		out.close();
	}
	
    
    /*********************************************************************************
     * @description:保存分享
     * @author:张凯  2017/7/7
     * @throws IOException 
     ************************************************************************************/
    @RequestMapping("/xidapp/saveNewShare")
    public void saveNewShareApp(Integer ID, String username, String title, HttpServletResponse response) throws IOException{
        AnnoucementList shar=appService.saveNewShare(ID, username, title);
        JSONObject jsonObject = new JSONObject();
        response.setContentType("text/ html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        JSONArray jsonArray = JSONArray.fromObject(shar);
        jsonObject.put("shareBack", jsonArray);
        out.print(jsonObject);
        out.flush();
        out.close();
    }
/*
	*//*********************************************************************************
	 * @description:上传分享文档
	 * @author:张凯	2017/7/7
	 * @throws IOException 
	 ************************************************************************************//*
	@RequestMapping("/xidapp/uploadShareDocument")
	@Transactional
	public void uploadShareDocumentAPP(HttpServletResponse response, HttpServletRequest request) throws IOException{
		AppPostImages docu = appService.uploadDocument(request, response);
		PrintWriter out = response.getWriter();
		out.print((docu==null||docu.equals(""))?0:1);
		out.flush();
		out.close();
	}
	*/
	/*********************************************************************************
	 * @description:查看分享列表
	 * @author:张凯	2017/7/7
	 * @throws IOException 
	 ************************************************************************************/
	@RequestMapping("/xidapp/shareList")
	@Transactional
	public void shareListApp(String username, HttpServletResponse response,HttpServletRequest request) throws IOException{
		List<ShareList> share = appService.getShareList(username,request);
		JSONObject jsonObject = new  JSONObject();
	    response.setContentType("text/ html；charset=utf-8");
	    response.setCharacterEncoding("utf-8"); 
		PrintWriter out = response.getWriter();
		JSONArray jsonArray = JSONArray.fromObject(share);
		jsonObject.put("shareList", jsonArray);
		out.print(jsonObject);
		out.flush();
		out.close();
	}
	
	/*********************************************************************************
	 * @description:查看分享详情
	 * @author:张凯	2017/7/7
	 * @throws IOException 
	 ************************************************************************************/
	@RequestMapping("/xidapp/shareDetail")
	@Transactional
	public void shareDetailApp(Integer ID, HttpServletResponse response,HttpServletRequest request) throws IOException{
		ShareDetail share = appService.shareDocument(ID,request);
		JSONObject jsonObject = new JSONObject();
		JSONArray jsonArray = JSONArray.fromObject(share);
		jsonObject.put("shareDetail", jsonArray);
		PrintWriter out = response.getWriter();
		out.print(jsonObject);
		out.flush();
		out.close();
	}
	
	/*********************************************************************************
	 * @description:下载分享附件
	 * @author:张凯	2017/7/7
	 * @throws IOException 
	 ************************************************************************************/
	@RequestMapping("/xidapp/downloadDocument")
	@Transactional
	public void downloadDocumentApp(Integer ID, HttpServletResponse response, HttpServletRequest request) throws IOException{
		String docUrl = appService.downloadDocument(ID, request);
		PrintWriter out = response.getWriter();
		out.print(docUrl);
		out.flush();
		out.close();
	}
	
	/*********************************************************************************
	 * @description:我的小组
	 * @author:缪军 	2017/06/30
	 * @throws IOException 
	 ************************************************************************************/
	@RequestMapping("/xidapp/studentGroup")
	@Transactional
	public void studentGroupApp(String username, HttpServletResponse response) throws IOException{
		//获取数据库语句
		String sqlStuGroup = appService.getStudentGroup(username);
		JSONObject jsonObject = new  JSONObject();
		Configuration config = new Configuration().configure();
        SessionFactory sf = config.buildSessionFactory();
        Session session = sf.openSession();
        response.setContentType("text/ html；charset=utf-8");
        response.setCharacterEncoding("utf-8"); 
		SQLQuery queryList = session.createSQLQuery(sqlStuGroup); //返回对象
        queryList.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        @SuppressWarnings("unchecked")
        //获取APP所需数据里的字段
		List<Map<String, Object>> results = queryList.list();
 	   	PrintWriter out = response.getWriter();
 	   	JSONArray jsonArray = JSONArray.fromObject(results);
 	   	jsonObject.put("studentGroup", jsonArray);
 	   	out.print(jsonObject);
 	    out.flush();
		out.close();
	}
	
	/*********************************************************************************
	 * @description:获取全部小组，并判断是否关注
	 * @author:缪军	2017/06/30
	 * @throws IOException 
	 ************************************************************************************/
	@RequestMapping("/xidapp/getGroupList")
	@Transactional
	public void getGroupList(String username, HttpServletResponse response) throws IOException{
		//获取数据库语句
		String sqlGroupList = appService.getGroupList(username);
		JSONObject jsonObject = new  JSONObject();
		Configuration config = new Configuration().configure();
        SessionFactory sf = config.buildSessionFactory();
        Session session = sf.openSession();
        response.setContentType("text/ html；charset=utf-8");
        response.setCharacterEncoding("utf-8"); 
		SQLQuery queryList = session.createSQLQuery(sqlGroupList); //返回对象
        queryList.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        @SuppressWarnings("unchecked")
        //获取APP所需数据里的字段
		List<Map<String, Object>> results = queryList.list();
 	   	PrintWriter out = response.getWriter();
 	   	JSONArray jsonArray = JSONArray.fromObject(results);
 	   	jsonObject.put("groupList", jsonArray);
 	   	out.print(jsonObject);
 	    out.flush();
		out.close();
	}
		/*********************************************************************************
		 * @description:新建小组
		 * @author:缪军 	2017/07/03
		 * @throws IOException 
		 ************************************************************************************/
		@RequestMapping("/xidapp/newStudentGroup")
		@Transactional
		public void newStudentGroup(String username, String groupname, HttpServletResponse response) throws IOException{
			response.setContentType("text/ html;charset=utf-8");
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
		 	appService.newGroup(username, groupname);
		 	List<AppGroup> list =appService.findGroup(username, groupname);
		 	if (list!=null&&list.size()>0) {
		 		JSONObject jsonobject = new JSONObject();
		 		AppGroup app=new AppGroup();
		 		app.setId(list.get(0).getId());
		 		app.setName(list.get(0).getName());
		 		GroupBack groupback=new GroupBack();
		 		groupback.setId(list.get(0).getId());
		 		groupback.setName(list.get(0).getName());
				JSONArray jsonarray1 = JSONArray.fromObject(groupback);
				jsonobject.put("group", jsonarray1);
				out.print(jsonobject);
				
			}else {
				out.print("error");
			}
		 	out.flush();
		 	out.close();
		}
		
		/*********************************************************************************
			 * @description:关注小组
			 * @author:缪军 	2017/07/03
			 * @throws IOException 
		************************************************************************************/
		@RequestMapping("/xidapp/followStudentGroup")
		@Transactional
		public void followStudentGroup(String username , Integer groupId , Integer flag , HttpServletResponse response) throws IOException{
			response.setContentType("text/ html;charset=utf-8");
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			out.print(appService.followStudentGroup(username, groupId, flag));//return1表示成功,0表示失败.参数1表示关注，0表示取关
			out.flush();
			out.close();
		}
		
		
		
 		/*********************************************************************************
		 * @description:我的首页（小组讨论）页面
		 * @author:缪军 	2017/07/07
		 * @throws IOException 
		 ************************************************************************************/
		@RequestMapping("/xidapp/getMyGroupPostlist")
		@Transactional
		public void getMyGroupPostlist(String username ,HttpServletResponse response, HttpServletRequest request) throws IOException{
			//获取我关注的小组
			List<AppGroup> groups=appService.getFollowedGroupList(username);
			//获取各个小组对应的最新的帖子
			List<NewPostList> groupPost=appService.getGroupListByGroup(username, groups,request);
			response.setContentType("text/ html；charset=utf-8");
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			JSONObject jsonobject = new JSONObject();
			JSONArray jsonarray2 = JSONArray.fromObject(groupPost);
			jsonobject.put("groupPost", jsonarray2);
			out.print(jsonobject);
			out.flush();
			out.close();
		}
		
		/*********************************************************************************
		 * @description:推荐首页（小组讨论）页面
		 * @author:缪军 	2017/07/10
		 * @throws IOException 
		 ************************************************************************************/
		@RequestMapping("/xidapp/getCommendGroupPostlist")
		@Transactional
		public void getCommendGroupPostlist(String username ,HttpServletResponse response,HttpServletRequest request) throws IOException{
			//推荐首页限定条数
			Integer limit= 20;
			//获取最新回复的n个帖子
			List<NewPostList> postList = appService.getPostlistRandom(limit,username,request);
			response.setContentType("text/ html；charset=utf-8");
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			JSONObject jsonObject = new JSONObject();
			JSONArray jsonArray = JSONArray.fromObject(postList);
			jsonObject.put("postList", jsonArray);
			out.print(jsonObject);
			out.flush();
			out.close();
		}
		
		/*********************************************************************************
		 * @description:查看相册
		 * @author:缪军 	2017/07/04
		 * @throws IOException 
		 ************************************************************************************/
		@RequestMapping("/xidapp/albumView")
		@Transactional
		public void albumView(Integer ID, HttpServletResponse response, HttpServletRequest request) throws IOException{
			//获取相册信息
			Album album = appService.viewAlbum(ID,request);
			PrintWriter out = response.getWriter();
			response.setContentType("text/ html；charset=utf-8");
			response.setCharacterEncoding("utf-8");
			JSONObject jsonobject = new JSONObject();
			JSONArray jsonarray = JSONArray.fromObject(album);
			jsonobject.put("album", jsonarray);
			out.print(jsonobject);
			out.flush();
			out.close();
		}
	/*	
		*//*********************************************************************************
		 * @description:图片保存部分
		 * @author:唐钦邦	2017/8/16
		 * @throws IOException 
		 ************************************************************************************//*
		@RequestMapping("/xidapp/saveImg")
		@Transactional
		public void saveImg(HttpServletRequest request, HttpServletResponse response) throws Exception {
			String img = appService.saveImg(request, response);
			response.setContentType("text/ html;charset=utf-8");
			response.setCharacterEncoding("utf-8"); 
			PrintWriter out = response.getWriter();
			out.print(img);
	 	   	out.flush();
			out.close();
		}*/
	    /*********************************************************************************
		 * @description:新建相册
		 * @author:缪军 	2017/07/04
		 * @throws IOException 
		 ************************************************************************************/
			@RequestMapping("/xidapp/newAlbum")
			@Transactional
			public void newAlbum(String title, String sponsor,String imageIds, HttpServletResponse response) throws IOException{
				response.setContentType("text/ html;charset=utf-8");
				response.setCharacterEncoding("utf-8");
				PrintWriter out = response.getWriter();
		 	   	out.print(appService.newAlbum(title, sponsor, imageIds));//1表示成功。0表示失败
		 	    out.flush();
				out.close();
			}
		/*********************************************************************************
		 * @description:相册下载(打包zip)
		 * @author:缪军	2017/07/05
		 * @throws IOException 
		 ************************************************************************************/
		@RequestMapping("/xidapp/downloadAlbum")
		@Transactional
		public void downloadAlbum(Integer ID, HttpServletRequest request, HttpServletResponse response) throws Exception {
			appService.downloadAlbum(ID, request, response);
		}
			
		/*********************************************************************************
		 * @description:获取小组帖子列表
		 * @author:缪军 	2017/07/06
		 * @throws IOException 
		************************************************************************************/
		@RequestMapping("/xidapp/getGroupPostlist")
		@Transactional
		public void getGroupPostlist(String username , Integer groupId , HttpServletResponse response, HttpServletRequest request) throws IOException{
			//获取帖子信息
			List<NewPostList> groupList = appService.getGroupPostlist(username, groupId,request);
			response.setContentType("text/ html；charset=utf-8");
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			JSONObject jsonobject = new JSONObject();
			JSONArray jsonarray = JSONArray.fromObject(groupList);
			jsonobject.put("groupList", jsonarray);
			out.print(jsonobject);
			out.flush();
			out.close();		
		}
		 
		/*********************************************************************************
		 * @description:新增问卷调查
		 * @author:张凯     	2017/07/13
		 * 修改章新洁
		************************************************************************************/
		@RequestMapping("/xidapp/newQuestionNaire")
		@Transactional
		public void newQuestionNaireApp(HttpServletResponse response,String JsonForQuestionnaire) throws IOException{
			Gson gson=new Gson();
			NewQuestionnaire res = gson.fromJson(JsonForQuestionnaire, NewQuestionnaire.class);
			response.setContentType("text/ html；charset=utf-8");
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			out.print(appService.saveNewQuestionnaire(res.getTitle(), res.getDescription(), res.getText(), res.getType()));
			out.flush();
			out.close();
		}
		/*********************************************************************************
		 * @description:保存问卷答案
		 * @author:唐钦邦     	2017/08/09
		 * @throws IOException  
		************************************************************************************/
		@RequestMapping("/xidapp/saveQuestionAnswer")
		public void saveNewQuestionNaireApp(HttpServletResponse response,String JsonSaveQuestionnaire) throws IOException{
			Gson gson=new Gson();
			SaveQuestionnaire sav = gson.fromJson(JsonSaveQuestionnaire,SaveQuestionnaire.class);
			response.setContentType("text/ html；charset=utf-8");
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			out.print(appService.saveQuestionnaireAnswer(sav.getID(),sav.getMAnswer()));
		}


		/*********************************************************************************
		 * @description:问卷调查列表
		 * @author:张凯     	2017/07/13
		 * @throws IOException 
		************************************************************************************/
		@RequestMapping("/xidapp/questionNaireList")
		@Transactional
		public void questionNaireListApp(HttpServletResponse response, String username) throws IOException{
			String sql = appService.appQuestionNaireList();
			JSONObject jsonObject = new JSONObject();
			Configuration config = new Configuration().configure();
			SessionFactory sf = config.buildSessionFactory();
			Session session = sf.openSession();
			response.setContentType("text/ html；charset=utf-8");
			response.setCharacterEncoding("utf-8");
			SQLQuery lists = session.createSQLQuery(sql);
			lists.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		    @SuppressWarnings("unchecked")
			List<Map<String,Object>> results = lists.list();
			PrintWriter out = response.getWriter();
			JSONArray jsonArray = JSONArray.fromObject(results);
			//判断当前用户的角色
			Integer flag = appService.authorityPudge(username);
			jsonObject.put("StudentGrades", jsonArray);
			jsonObject.put("flag", flag);
			out.print(jsonObject);
	 	   	out.flush();
			out.close();
		}
		
		/*********************************************************************************
		 * @description:问卷调查详情
		 * @author:张凯     	2017/07/14
		 * @throws IOException 
		************************************************************************************/
 		@RequestMapping("/xidapp/questionNaireDetail")
		@Transactional
		public @ResponseBody void questionNaireDetailApp(HttpServletResponse response, Integer ID) throws IOException{
			QuestionNaireDetail detail = appService.getQuestionNaireDetail(ID);
			response.setContentType("text/ html；charset=utf-8");
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			JSONObject jsonobject = new JSONObject();
			JSONArray jsonarray = JSONArray.fromObject(detail);
			jsonobject.put("questionNaireDetail", jsonarray);
			out.print(jsonobject);
			out.flush();
			out.close();		
			/*PrintWriter out = response.getWriter();
			out.print(detail);
			out.flush();
			out.close();*/
//			return detail;
		}

		/*********************************************************************************
		 * @description:获取班级相册
		 * @author:赵昶	2017/07/19
		 * @throws IOException 
		 ************************************************************************************/
		@RequestMapping("/xidapp/classAlbumPostListApp")
	 	@Transactional
	 	public void classAlbumPostListApp(String username, HttpServletResponse response, HttpServletRequest request) throws IOException{
			//获取用户(username)同班级的相册信息，包括:相册id;title相册标题; imageList首张相册图片;sponsor相册分享者;time相册建立时间
			List<NewAlbum> postList = appService.getAlbumlist(username,request);
			response.setContentType("text/ html；charset=utf-8");
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			JSONObject jsonObject = new JSONObject();
			JSONArray jsonArray = JSONArray.fromObject(postList);
			//键值对的形式，存入取出的信息
			jsonObject.put("postList", jsonArray);
			out.print(jsonObject);
			out.flush();
			out.close();
		}
		
		/*********************************************************************************
		 * @description:获取实验室预约信息
		 * @author:孙广志	
		 * @throws IOException 
		 ************************************************************************************/
		@RequestMapping("/xidapp/getAppointmentList")
		@Transactional
		public void  getAppointmentRoomList(String username, HttpServletResponse response,HttpServletRequest request) throws IOException{
			List<Appointment> appointmentLists = appService.getAppointmentList(username,request);
			PrintWriter out = response.getWriter();
			//设置输出
			response.setContentType("text/ html；charset=utf-8");
			response.setCharacterEncoding("utf-8");
			JSONObject jsonobject = new JSONObject();
			JSONArray jsonArray = JSONArray.fromObject(appointmentLists);
			jsonobject.put("AppointmentList", jsonArray);
			out.print(jsonobject);
			out.flush();
			out.close();
		}
		/*********************************************************************************
		 * @description:推送云盘文件夹信息
		 * @author:孙广志	
		 * @throws IOException 
		 ************************************************************************************/
		@RequestMapping("/xidapp/getDowmloadFolderList")
		@Transactional
		public void  getDownloadFolderList(String username, HttpServletResponse response,HttpServletRequest request) throws IOException{
			List<ReturnDownloadFolder> downloadFolderLists = appService.getDownloadFolderList(username,request);
			PrintWriter out = response.getWriter();
			//设置输出
			response.setContentType("text/ html；charset=utf-8");
			response.setCharacterEncoding("utf-8");
			JSONObject jsonobject = new JSONObject();
			JSONArray jsonArray = JSONArray.fromObject(downloadFolderLists);
			jsonobject.put("DownloadFolderList", jsonArray);
			out.print(jsonobject);
			out.flush();
			out.close();
		}
		/*********************************************************************************
		 * @description:推送云盘文件信息
		 * @author:孙广志	
		 * @throws IOException 
		 ************************************************************************************/
		@RequestMapping("/xidapp/getDowmloadFileList")
		@Transactional
		public void  getDownloadFileList(String username, int id,HttpServletResponse response,HttpServletRequest request) throws IOException{
			List<ReturnDownloadFile> downloadFileLists = appService.getDownloadFileList(username,id,request);
			PrintWriter out = response.getWriter();
			//设置输出
			response.setContentType("text/ html；charset=utf-8");
			response.setCharacterEncoding("utf-8");
			JSONObject jsonobject = new JSONObject();
			JSONArray jsonArray = JSONArray.fromObject(downloadFileLists);
			jsonobject.put("DownloadFileList", jsonArray);
			out.print(jsonobject);
			out.flush();
			out.close();
		}
		/*********************************************************************************
		 * @description:获取新建文件夹信息,保存到表中
		 * @author:孙广志	
		 * @throws IOException 
		 ************************************************************************************/
		@RequestMapping("/xidapp/getNewFolderList")
		@Transactional
		public void getNewFolderList(String username, String folderName, HttpServletResponse response, HttpServletRequest request) throws IOException{
			List<NewFolder> addFolders = appService.getNewFolderList(username,folderName,request);
			PrintWriter out = response.getWriter();
			//设置输出
			response.setContentType("text/html；charset=utf-8");
			response.setCharacterEncoding("utf-8");
			JSONObject jsonobject = new JSONObject();
			JSONArray jsonArray = JSONArray.fromObject(addFolders);
			jsonobject.put("AddFolder", jsonArray);
			out.print(jsonobject);
			out.flush();
			out.close();
		}

		/*********************************************************************************
		 * @description:保存上传文件
		 * @author:孙广志	2017/8/16
		 * @throws IOException 
		 ************************************************************************************/
		@RequestMapping("/xidapp/saveUploadFile")
		@Transactional
		public void saveUploadFile(String username ,int id, HttpServletRequest request, HttpServletResponse response) throws Exception {
			String suf = appService.getSaveUploadFile (username, id, request, response);
			response.setContentType("text/ html;charset=utf-8");
			response.setCharacterEncoding("utf-8"); 
			PrintWriter out = response.getWriter();
			out.print(suf);
	 	   	out.flush();
			out.close();
		}
}
