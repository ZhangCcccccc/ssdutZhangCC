package net.xidlims.web.tcoursesite;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.xidlims.constant.CommonConstantInterface;
import net.xidlims.dao.TCourseSiteDAO;
import net.xidlims.domain.Authority;
import net.xidlims.domain.SchoolCourse;
import net.xidlims.domain.SchoolCourseDetail;
import net.xidlims.domain.SchoolTerm;
import net.xidlims.domain.TCourseSite;
import net.xidlims.domain.TCourseSiteUser;
import net.xidlims.domain.User;
import net.xidlims.service.common.ShareService;
import net.xidlims.service.newtimetable.NewTimetableCourseSchedulingService;
import net.xidlims.service.system.UserDetailService;
import net.xidlims.service.tcoursesite.TAssignmentService;
import net.xidlims.service.tcoursesite.TCourseSiteArticalService;
import net.xidlims.service.tcoursesite.TCourseSiteChannelService;
import net.xidlims.service.tcoursesite.TCourseSiteService;
import net.xidlims.service.tcoursesite.TCourseSiteUserService;
import net.xidlims.service.tcoursesite.TMessageService;
import net.xidlims.service.tcoursesite.WkService;
import net.xidlims.service.teaching.TAssignmentGradingService;
import net.xidlims.service.timetable.OuterApplicationService;
import net.xidlims.service.timetable.SchoolCourseDetailService;
import net.xidlims.service.timetable.SchoolCourseInfoService;
import net.xidlims.service.timetable.SchoolCourseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;



/******************************************************************************************
 * 功能：CMS（课程）模块 作者：李小龙 时间：2015-8-10 15:08:50
 *****************************************************************************************/
@Controller("TMSController")
public class TMSController<JsonResult> {
	/**************************************************************************************
	 * @初始化WebDataBinder，这个WebDataBinder用于填充被@InitBinder注释的处理 方法的command和form对象
	 * 
	 *************************************************************************************/
	@InitBinder
	public void initBinder(WebDataBinder binder, HttpServletRequest request) {
		binder.registerCustomEditor(java.util.Calendar.class,
				new org.skyway.spring.util.databinding.CustomCalendarEditor());
		binder.registerCustomEditor(byte[].class,
				new org.springframework.web.multipart.support.ByteArrayMultipartFileEditor());
		binder.registerCustomEditor(boolean.class, new org.skyway.spring.util.databinding.EnhancedBooleanEditor(false));
		binder.registerCustomEditor(Boolean.class, new org.skyway.spring.util.databinding.EnhancedBooleanEditor(true));
		binder.registerCustomEditor(java.math.BigDecimal.class,
				new org.skyway.spring.util.databinding.NaNHandlingNumberEditor(java.math.BigDecimal.class, true));
		binder.registerCustomEditor(Integer.class, new org.skyway.spring.util.databinding.NaNHandlingNumberEditor(
				Integer.class, true));
		binder.registerCustomEditor(java.util.Date.class, new org.skyway.spring.util.databinding.CustomDateEditor());
		binder.registerCustomEditor(String.class, new org.skyway.spring.util.databinding.StringEditor());
		binder.registerCustomEditor(Long.class, new org.skyway.spring.util.databinding.NaNHandlingNumberEditor(
				Long.class, true));
		binder.registerCustomEditor(Double.class, new org.skyway.spring.util.databinding.NaNHandlingNumberEditor(
				Double.class, true));
	}

	@Autowired
	private ShareService shareService;
	@Autowired
	private TCourseSiteChannelService tCourseSiteChannelService;
	@Autowired
	private TCourseSiteArticalService tCourseSiteArticalService;
	@Autowired
	private TCourseSiteService tCourseSiteService;
	@Autowired
	private WkService wkService;
	@Autowired
	private TAssignmentService tAssignmentService;
	@Autowired
	private TAssignmentGradingService tAssignmentGradingService;
	@Autowired
	private TMessageService tMessageService;
	@Autowired
	private SchoolCourseInfoService schoolCourseInfoService;
	@Autowired
	private OuterApplicationService outerApplicationService;
	@Autowired
	private UserDetailService userDetailService;
	@Autowired
	private TCourseSiteDAO tCourseSiteDAO;
	@Autowired
	private TCourseSiteUserService tCourseSiteUserService;
	@Autowired
	private SchoolCourseDetailService schoolCourseDetailService;
	@Autowired
	private NewTimetableCourseSchedulingService newTimetableCourseSchedulingService;
	@Autowired
	private SchoolCourseService schoolCourseService;
	
	

	/**************************************************************************************
	 * @功能 查看首页
	 * @作者：李小龙
	 * @日期：2015-8-13
	 *************************************************************************************/
	@RequestMapping("/tms/index")
	public String index(HttpSession session) {
		ModelAndView mav = new ModelAndView();
		// 获取登录用户
		User user = shareService.getUser();
		mav.addObject("user", user);
		if(user!=null){
			if(user.getAuthorities().toString().contains("TEACHER")){
				session.setAttribute("selected_role", "ROLE_TEACHER");
			}else if(user.getAuthorities().toString().contains("STUDENT")){
				session.setAttribute("selected_role", "ROLE_STUDENT");
			}
			// 当前登录人
			session.setAttribute("loginUser", user);
		}
		
		// 查询所有站点
		/*List<TCourseSite> allsites = tCourseSiteService.findAllTCourseSite();
		mav.addObject("allsites", allsites);*/
		List<TCourseSite> allsites = tCourseSiteService.findAllTCourseSiteByUser(user);
		mav.addObject("allsites", allsites);
		// 查询出公开站点
		List<TCourseSite> sites = tCourseSiteService.findOpenTCourseSite(1);
		mav.addObject("sites", sites);
		

		mav.setViewName("tms/courseSite/index.jsp");
		return "redirect:/tms/courseList?currpage=1";
	}

	/**************************************************************************
	 * Description:课程列表-所有课程列表
	 * 
	 * @author：裴继超
	 * @date ：2016-8-24
	 **************************************************************************/
	@RequestMapping("/tms/courseList")
	public ModelAndView courseList(@RequestParam Integer currpage,Integer allCourseFlag,@ModelAttribute TCourseSite tCourseSite) {
		ModelAndView mav = new ModelAndView();
		//获取当前学期
		Calendar duedate = Calendar.getInstance();
		SchoolTerm term = shareService.getBelongsSchoolTerm(duedate);
		mav.addObject("term", term);
		//如果查询条件里面没有学期，默认查询所有
		if(tCourseSite.getSchoolTerm()!=null&&tCourseSite.getSchoolTerm().getId()==-1){
			tCourseSite.setSchoolTerm(null);
		}
		// 获取登录用户
		User user = shareService.getUser();
		mav.addObject("user", user);
		
		//分页，每页20条数据
		int pageSize = 20;
		//查询课程总数
		int totalRecords = tCourseSiteService.getTCourseSiteTotalRecordsByTCourseSiteWithMore(tCourseSite, currpage, pageSize, allCourseFlag, user.getUsername());
		//根据分页信息查询所有的课程
		List<TCourseSite> tCourseSites = tCourseSiteService.findTCourseSitesByTCourseSiteWithMore(tCourseSite, currpage, pageSize, allCourseFlag, user.getUsername());
		//分页信息
		Map<String,Integer> pageModel =shareService.getPage(currpage,pageSize,totalRecords);
		
//		List<Object[]> courseDetailList = newTimetableCourseSchedulingService.findMyScheduleListView(request.getSession(),null, 1, 30);
//		mav.addObject("courseDetailList", courseDetailList);
		
		Map<Integer,List<User>> map=new HashMap<Integer,List<User>>();
		
		for(TCourseSite site:tCourseSites){
			
			SchoolCourse schoolCourse = site.getSchoolCourse();
		
			Set<SchoolCourseDetail> schoolCourseDetails = schoolCourse.getSchoolCourseDetails();
			//存储课程所有老师
			Set<User> userList=new HashSet<User>();
			//有序集合，课程负责老师放在首位
			List<User> userOrderList= new ArrayList<User>();
			//根据schoolCourse确定课程负责老师并加入到list集合中
			User teacherInCharge = schoolCourse.getUserByTeacher();
			//将课程负责老师放在首位
			userOrderList.add(0, teacherInCharge);
		
			for(SchoolCourseDetail detail:schoolCourseDetails){
				//根据schoolCourseDetail获取相对应的负责老师schoolCourseDetailScheduleTeacher和课程老师schoolCourseDetailTeacher
				Set<User> user2List = detail.getUserByScheduleTeachers();
					for(User user22:user2List){
						userList.add(user22);
					}
				
				Set<User> user3List = detail.getUsers();
					for(User user33:user3List){
						userList.add(user33);
				}
			
				//遍历set集合，赋值给list集合
				for(User u : userList){
				
					if(u!=teacherInCharge){
				   
						userOrderList.add(u);
					}
				}
			
			}
			map.put(site.getId(), userOrderList);
		}
		
		 //对合班的课程进行课序号设置
		 for(TCourseSite t:tCourseSites){
			 
			 //取每门课程的课程代码对照合班表查看是否存在合班信息
			 //原课程代码
			 String courseCode = t.getSchoolCourse().getCourseCode();
			 //若有合班，取课程代码即课序号
			 String courseCode1 = schoolCourseService.findSchoolCourseMergeCourseNumberByCourseCode(courseCode);
			 //倘若courseCode1有数据，重新设置课序号
			 if(courseCode1!=null){
				 t.getSchoolCourse().setCourseCode(courseCode1);
			 }
		}
		mav.addObject("allCourseFlag", allCourseFlag);
		mav.addObject("teacherMap",map);
		mav.addObject("sites", tCourseSites);
		mav.addObject("currpage",currpage);
		mav.addObject("totalRecords",totalRecords);
		mav.addObject("pageModel", pageModel);
		mav.addObject("tCourseSite", tCourseSite);
		
        //课程列表还是我的课程
		mav.addObject("type", "courseList");
		//查询所有学期
		List<SchoolTerm> schoolTerms = shareService.findAllSchoolTerm();
		mav.addObject("schoolTerms", schoolTerms);
		
		mav.addObject("url", "/tms/courseList");
		mav.setViewName("tms/courseSite/newCourseList.jsp");
		return mav;
	}
	
	/**************************************************************************************
	 * @自主排课 新建选课组页面
	 * @作者：魏诚
	 * @日期：2015-07-24
	 *************************************************************************************/
	@SuppressWarnings("rawtypes")
	@RequestMapping("/tms/coursesite/newCourseSite")
	public ModelAndView newCourseSite() {
		ModelAndView mav = new ModelAndView();
		// 登陆用户
		mav.addObject("user", shareService.getUserDetail());
		mav.addObject("type", "myCourseList");

		// 获取最大的id
		int maxId = tCourseSiteService.getTCourseSiteTotalRecords();
		mav.addObject("maxId", maxId);
		// id为-1时，新增，否则为修改
		mav.addObject("flagId", -1);
		//
		TCourseSite tCourseSite = new TCourseSite();
		tCourseSite.setUserByCreatedBy(shareService.getUser());
		/*if (id != -1) {
			tCourseSite = tCourseSiteService.findCourseSiteById(id);
		}*/
		mav.addObject("tCourseSite", tCourseSite);
		// 获取学期列表
		List<SchoolTerm> schoolTerms = outerApplicationService.getSchoolTermList();
		mav.addObject("schoolTerms", schoolTerms);
		
		// 获取专业列表
//		Map schoolMajors = shareService.getMajorsMap();
//		mav.addObject("schoolMajors", schoolMajors);
		// 获取所有课程列表
		mav.addObject("schoolCourses", schoolCourseInfoService.getCourseInfoByQuery(null, -1, 1, -1));
		// 获取可选的教师列表列表
		mav.addObject("timetableTearcherMap", outerApplicationService.getTimetableTearcherMap());
		// mav.addtCourseSitelfCourse", new TimetableSelfCourse());
		// 获取当前学院下的学生
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		
		/*List<TCourseSiteUser> size=tCourseSiteService.findCourseSiteUserBysiteId(id);
		mav.addObject("tCourseUser",size);*/
		
		List<User> userList = userDetailService.findUsersByAcademyAndGrade(shareService.getUser().getSchoolAcademy().getAcademyName(),year);
		mav.addObject("userList", userList);
		// 获取当前学院下的年级
		List<User> grades = userDetailService.findGradesByAcademy(shareService.getUser().getSchoolAcademy().getAcademyName());
		mav.addObject("grade",grades);
		
		mav.setViewName("/tms/courseSite/newCourseSite.jsp");
		return mav;
	}
	
	/**************************************************************************************
	 * @自主排课 保存 新建自主排课的选课组
	 * @页面跳转：listSelfTimetable-newCourseCodeIframeDetail-saveCourseCodeIframeDetail
	 * @作者：裴继超
	 * @日期：2016年7月13日11:49:27
	 *************************************************************************************/
	@RequestMapping("/tms/coursesite/saveTCourseSite")
	public ModelAndView saveTCourseSite(HttpServletRequest request,
			@ModelAttribute TCourseSite tCourseSite, @RequestParam int flagID) {
		ModelAndView mav = new ModelAndView();
		
		String returnUrl = tCourseSiteService.saveTCourseSite(request, tCourseSite, flagID);
		
		mav.setViewName("redirect:/tms/myCourseList?currpage=1"); 
		
		return mav;
	}

	/**************************************************************************************
	 * @自主排课 保存 删除自主排课的选课组
	 * @作者：裴继超
	 * @日期：2016年7月13日11:49:27
	 *************************************************************************************/
	@RequestMapping("/tms/coursesite/deleteTCourseSite")
	public ModelAndView deleteTCourseSite(@RequestParam int id) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/teaching/coursesite/listCourseSites?currpage=1");
		// 删除指定id的TCourseSite
		//tCourseSiteDAO.remove(tCourseSiteDAO.findTCourseSiteById(id));
		TCourseSite oldTCourseSite = tCourseSiteService.findCourseSiteById(id);
		oldTCourseSite.setStatus(0);//改状态数值假删除
		tCourseSiteDAO.store(oldTCourseSite);
		return mav;
	}
	
	/**************************************************************************************
	 * @功能 修改密码
	 * @作者：黄崔俊
	 * @日期：2015-9-15 14:33:20
	 *************************************************************************************/
	@RequestMapping("/tms/changePassword")
	public ModelAndView changePassword(@RequestParam String password,String type) {
		ModelAndView mav = new ModelAndView();
		// 获取登录用户
		User user = shareService.getUser();
		if (password!=null&&!"".equals(password)) {//传入的密码不为空则修改密码
			user = shareService.changeUserPassword(user,password);
		}
		mav.addObject("user", user);
        //课程列表还是我的课程
		if ("courseList".equals(type)) {
			if (password==null||"".equals(password)) {
				mav.setViewName("tms/courseSite/courseList.jsp");
			}else {
				mav.setViewName("redirect:/tms/courseList");
			}
		}
		if ("myCourseList".equals(type)) {
			if (password==null||"".equals(password)) {
				mav.setViewName("tms/courseSite/courseList.jsp");
			}else {
				mav.setViewName("redirect:/tms/myCourseList");
			}
		}
		mav.addObject("type", type);
		
		return mav;
	}
	
	/**************************************************************************************
	 * @功能 查看个人信息
	 * @作者：黄崔俊
	 * @日期：2015-9-16 10:22:22
	 *************************************************************************************/
	@RequestMapping("/tms/listMyInfo")
	public ModelAndView listMyInfo(@RequestParam String type) {
		ModelAndView mav = new ModelAndView();

		// 获取当前用户
		User user = shareService.getUser();
		Set<Authority> as = user.getAuthorities();
		String str = "";
		if(as.size()==0){
			str = "暂无身份";
		}
		if(as.size()>0){
			for(Authority a : as){
				str = a.getCname()+" ";
			}
		}
		str = str.substring(0, str.length()-1);
		mav.addObject("str", str);
		mav.addObject("user", user);
		
		//课程列表还是我的课程
		mav.addObject("type", type);
		mav.setViewName("tms/courseSite/courseList.jsp");
		return mav;
	}
	
	/**************************************************************************************
	 * @功能：保存个人信息
	 * @作者：黄崔俊
	 * @日期：2015-9-16 10:22:22
	 *************************************************************************************/
	@RequestMapping("/tms/saveMyInfo")
	public ModelAndView saveMyInfo(@ModelAttribute User user,@RequestParam String type) {
		ModelAndView mav = new ModelAndView();
		// 获取当前用户
		User user1 = shareService.getUser();

		// 直接设置用户的email telephone QQ信息
		user1.setEmail(user.getEmail());
		user1.setTelephone(user.getTelephone());
		user1.setQq(user.getQq());
		// 保存信息
		shareService.saveUser(user1);
		mav.addObject("type", type);
		mav.setViewName("redirect:/tms/listMyInfo");
		return mav;
	}
	
	/**************************************************************************
	 * Description:课程列表-我的选课课程列表
	 * 
	 * @author：裴继超
	 * @date ：2016-8-24
	 **************************************************************************/
	@RequestMapping("/tms/myCourseList")
	public ModelAndView myCourseList(@RequestParam Integer currpage,@ModelAttribute TCourseSite tCourseSite) {
		ModelAndView mav = new ModelAndView();
		//获取当前学期
		Calendar duedate = Calendar.getInstance();
		SchoolTerm term = shareService.getBelongsSchoolTerm(duedate);
		mav.addObject("term", term);
		//如果查询条件里面没有学期，默认查询所有
		if(tCourseSite.getSchoolTerm()!=null&&tCourseSite.getSchoolTerm().getId()==-1){
			tCourseSite.setSchoolTerm(null);
		}
		User user = shareService.getUser();
		// 查询出登录用户公开站点
		int pageSize = 24;
		int totalRecords = tCourseSiteService.getMyTCourseSiteTotalRecordsByTCourseSite(tCourseSite, currpage, pageSize);
		List<TCourseSite> tCourseSites = tCourseSiteService.findMyTCourseSitesByTCourseSite(tCourseSite, currpage, pageSize);
		Map<String,Integer> pageModel =shareService.getPage(currpage,pageSize,totalRecords);
		mav.addObject("sites", tCourseSites);
		mav.addObject("pageModel", pageModel);
		mav.addObject("tCourseSite", tCourseSite);
		
		
		 //课程列表还是我的课程
		mav.addObject("type", "myCourseList");
		mav.addObject("url", "/tms/myCourseList");
		
		//查询所有学期
		List<SchoolTerm> schoolTerms = shareService.findAllSchoolTerm();
		mav.addObject("schoolTerms", schoolTerms);

		
		// 获取登录用户
		mav.addObject("user", user);

		mav.setViewName("tms/courseSite/courseList.jsp");
		return mav;
	}
	
	
	public static String[] insertSort(String[] weeks) {// 插入排序算法
		for (int i = 1; i < weeks.length; i++) {
			for (int j = i; j > 0; j--) {
                String start =weeks[j];
                String end =weeks[j-1];
                if(start.indexOf("-")!=-1){
                	start = start.substring(start.indexOf("-"));
                }
                if(end.indexOf("-")!=-1){
                	end = end.substring(end.indexOf("-"));
                }
                
				if (Integer.parseInt(start) < Integer.parseInt(end)) {
					String temp = weeks[j - 1];
					weeks[j - 1] = weeks[j];
					weeks[j] = temp;
				} else
					break;
			}
		}
		return weeks;
	}
	
	
	/**************************************************************************************
	 * @功能 门户首页
	 * @作者：黄崔俊
	 * @日期：2016-4-18 09:29:59
	 *************************************************************************************/
	@RequestMapping("/tms/tcoursesite/listTCourseSite")
	public ModelAndView listTCourseSite(@RequestParam Integer currpage) {
		ModelAndView mav = new ModelAndView();
		int totalRecords = tCourseSiteService.countOpenTCourseSite(1);
		int pageSize = 12;
		List<TCourseSite> sites = tCourseSiteService.findOpenTCourseSite(1,currpage,pageSize);
		mav.addObject("sites", sites);
		mav.addObject("pageModel", shareService.getPage(currpage, pageSize, totalRecords));
		
		mav.setViewName("/tms/tcoursesite/listTCourseSite.jsp");
		return mav;
	}
	
	/*************************************************************************************
	 * @description:新建课程-添加助教{根据姓名查找，并赋值}
	 * @param:cName 姓名
	 * @author:陈乐为
	 * @date：2016-8-29
	 *************************************************************************************/
	@RequestMapping("/tms/tcoursesite/searchUser")
	public @ResponseBody Map<String,List> searchUser(@RequestParam int currpage,@RequestParam String cName)throws Exception{
	    String result=java.net.URLDecoder.decode(cName, "UTF-8");//转成utf-8；
	 // 设置分页变量并赋值为20
	 	 int pageSize = CommonConstantInterface.INT_PAGESIZE;
	    return tCourseSiteService.getCEduTypeDataSetMap(result,currpage,pageSize);
	 }
	
	/*************************************************************************************
	 * description:新建课程-添加助教-分页功能
	 * @param:cName 助教名
	 * @author:于侃
	 * @date：2016-8-31
	 *************************************************************************************/
	@RequestMapping("/tms/tcoursesite/searchUserPage")
	public @ResponseBody Map<String, Integer> searchUserPage(@RequestParam int currpage,@RequestParam String cName)throws Exception{
		String result=java.net.URLDecoder.decode(cName, "UTF-8");//转成utf-8；
		// 设置分页变量并赋值为20
		int pageSize = CommonConstantInterface.INT_PAGESIZE;
		int totalRecords = tCourseSiteService.countUsersByQuery(result);
		return shareService.getPage(currpage, pageSize, totalRecords);
	}
	
	/*************************************************************************************
	 * description:新建课程-添加课程{根据课程名称查找，并赋值}
	 * @param:courseName 课程名
	 * @author:于侃
	 * @date：2016-8-31
	 *************************************************************************************/
	@RequestMapping("/tms/tcoursesite/searchCourseNumber")
	public @ResponseBody Map<String,List> searchCourseNumber(@RequestParam int currpage,@RequestParam String courseName)throws Exception{
	    String result=java.net.URLDecoder.decode(courseName, "UTF-8");//转成utf-8；
	 // 设置分页变量并赋值为20
	 	int pageSize = CommonConstantInterface.INT_PAGESIZE;
	    return tCourseSiteService.getCourseNumberSetMap(result,currpage,pageSize);
	 }
	
	/*************************************************************************************
	 * description:新建课程-添加课程-分页功能
	 * @param:courseName 课程名
	 * @author:于侃
	 * @date：2016-8-31
	 *************************************************************************************/
	@RequestMapping("/tms/tcoursesite/searchCourseNumberPage")
	public @ResponseBody Map<String, Integer> searchCourseNumberPage(@RequestParam int currpage,@RequestParam String courseName)throws Exception{
		String result=java.net.URLDecoder.decode(courseName, "UTF-8");//转成utf-8；
		// 设置分页变量并赋值为20
		int pageSize = CommonConstantInterface.INT_PAGESIZE;
		int totalRecords = tCourseSiteService.countCourseNumberByQuery(result);
		return shareService.getPage(currpage, pageSize, totalRecords);
	}
}