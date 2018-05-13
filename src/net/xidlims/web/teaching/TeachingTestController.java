package net.xidlims.web.teaching;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import net.xidlims.dao.TAssignmentDAO;
import net.xidlims.domain.TAssignment;
import net.xidlims.domain.TAssignmentAnswerAssign;
import net.xidlims.domain.TAssignmentControl;
import net.xidlims.domain.TAssignmentGrading;
import net.xidlims.domain.TAssignmentItemComponent;
import net.xidlims.domain.TAssignmentItemMapping;
import net.xidlims.domain.TAssignmentQuestionpool;
import net.xidlims.domain.TCourseSite;
import net.xidlims.domain.TCourseSiteUser;
import net.xidlims.domain.User;
import net.xidlims.service.common.ShareService;
import net.xidlims.service.teaching.TAssignmentForExamService;
import net.xidlims.service.teaching.TAssignmentForTestService;
import net.xidlims.service.teaching.TAssignmentGradingService;
import net.xidlims.service.tcoursesite.TAssignmentQuestionPoolService;
import net.xidlims.service.tcoursesite.TAssignmentService;
import net.xidlims.service.tcoursesite.TCourseSiteService;
import net.xidlims.service.tcoursesite.TGradebookService;
import net.xidlims.view.ViewTAssignment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

/******************************************************************************************
 * @功能：考试模块
 * @作者：黄崔俊 时间：2015-11-30 14:22:20
 *****************************************************************************************/
@Controller("TeachingTestController")
@SessionAttributes("selected_courseSite")
public class TeachingTestController<JsonResult> {
	/**************************************************************************************
	 * @初始化WebDataBinder，这个WebDataBinder用于填充被
	 * @InitBinder注释的处理 方法的command和form对象
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
		binder.registerCustomEditor(Float.class, new org.skyway.spring.util.databinding.NaNHandlingNumberEditor(
				Float.class, true));
	}

	@Autowired
	private TAssignmentDAO tAssignmentDAO;
	@Autowired
	private ShareService shareService;
	@Autowired
	private TAssignmentService tAssignmentService;
	@Autowired
	private TAssignmentForExamService tAssignmentForExamService;
	@Autowired
	private TAssignmentForTestService tAssignmentForTestService;
	@Autowired
	private TAssignmentGradingService tAssignmentGradingService;
	@Autowired
	private TGradebookService tGradebookService;
	@Autowired
	private TAssignmentQuestionPoolService tAssignmentQuestionPoolService;
	@Autowired
	private TCourseSiteService tCourseSiteService;
	
	
	/**************************************************************************************
	 * @练习和测验 创建考试入口
	 * @作者：黄崔俊
	 * @日期：2015-12-3 13:45:24
	 *************************************************************************************/
	@RequestMapping("/teaching/test/testMain")
	public ModelAndView testMain(@ModelAttribute("selected_courseSite")Integer cid) {
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("teaching/test/testMain.jsp");
		return mav;
	}

	/**************************************************************************************
	 * @练习和测验 根据当前课程及状态查看考试列表
	 * @作者：黄崔俊
	 * @日期：2015-12-3 13:46:05
	 *************************************************************************************/
	@RequestMapping("/teaching/test/testList")
	public ModelAndView testList(@RequestParam Integer status,@ModelAttribute("selected_courseSite") Integer cid) {
		ModelAndView mav = new ModelAndView();
		User nowUser = shareService.getUser();
		
		String type = "test";
		List<ViewTAssignment> testList = tAssignmentForTestService.findTestList(nowUser, cid, type, status,""); 
		
		mav.addObject("testList",testList);
		mav.addObject("status", status);
		mav.setViewName("teaching/test/testList.jsp");
		return mav;
	}
	
	/****************************************************************************
	 * 功能：新建考试
	 * 作者：黄崔俊
	 * 时间：2015-8-6 10:00:29
	 ****************************************************************************/
	@RequestMapping("/teaching/test/newTest")
	public ModelAndView newTest(@ModelAttribute("selected_courseSite")Integer cid){
		//新建ModelAndView对象；
		ModelAndView mav=new ModelAndView();
		// 登陆用户
		mav.addObject("user", shareService.getUser());
		//页面表单对象
		TAssignment  tAssignment = new TAssignment();
		TAssignmentControl  tAssignmentControl = new TAssignmentControl();
		//初始化默认时间
		Calendar duedate = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String nowDate =sdf.format(duedate.getTime());
		mav.addObject("startdate",nowDate);
		mav.addObject("duedate",nowDate);
		
		//初始化作业设置
		TAssignmentAnswerAssign tAssignmentAnswerAssign = new TAssignmentAnswerAssign();
		tAssignmentAnswerAssign.setScore(new BigDecimal(100));
		tAssignmentAnswerAssign.setUser(shareService.getUser());
		
		tAssignment.setCreatedTime(duedate);
		tAssignment.setUser(shareService.getUser());
		tAssignment.setTAssignmentControl(tAssignmentControl);
		tAssignment.setTAssignmentAnswerAssign(tAssignmentAnswerAssign);
				
		mav.addObject("tAssignment", tAssignment);
		
		List<TAssignmentQuestionpool> tAssignmentQuestionpools = tAssignmentQuestionPoolService.findQuestionList(cid);
		mav.addObject("tAssignmentQuestionpools", tAssignmentQuestionpools);
		
		mav.addObject("itemComponents", new ArrayList<TAssignmentItemComponent>());
		mav.setViewName("teaching/test/editTest.jsp");
		return mav;
	}
	
	
	
	/****************************************************************************
	 * 功能：查询要修改的考试信息
	 * 作者：黄崔俊
	 * 时间：2015-12-6 20:28:48
	 ****************************************************************************/
	@RequestMapping("/teaching/test/editTest")
	public ModelAndView editTest(@ModelAttribute("selected_courseSite") Integer cid,@RequestParam Integer testId){
		ModelAndView mav = new ModelAndView();
		TAssignment test = tAssignmentService.findTAssignmentById(testId);
		mav.addObject("tAssignment", test);
		
		List<TAssignmentQuestionpool> tAssignmentQuestionpools = tAssignmentQuestionPoolService.findQuestionList(cid);
		mav.addObject("tAssignmentQuestionpools", tAssignmentQuestionpools);
		
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		for (TAssignmentQuestionpool tAssignmentQuestionpool : test.getTAssignmentQuestionpools()) {
			map.put(tAssignmentQuestionpool.getQuestionpoolId(), tAssignmentQuestionpool);
		}
		mav.addObject("map", map);
		
		mav.addObject("itemComponents", test.getTAssignmentItemComponents());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		mav.addObject("startdate",sdf.format(test.getTAssignmentControl().getStartdate().getTime()));
		mav.addObject("duedate",sdf.format(test.getTAssignmentControl().getDuedate().getTime()));
		
		mav.setViewName("teaching/test/editTest.jsp");
		
		return mav;
	}
	/****************************************************************************
	 * 功能：保存考试设置
	 * 作者：黄崔俊
	 * 时间：2015-12-2 15:56:39
	 * @throws ParseException 
	 ****************************************************************************/
	@RequestMapping("/teaching/test/saveTest")
	public String saveTest(@ModelAttribute("selected_courseSite") Integer cid,@ModelAttribute TAssignment tAssignment,HttpServletRequest request) throws ParseException{
		boolean result = false;
		if (tAssignment.getId()==null) {
			result = true;
		}
		
		tAssignment = tAssignmentForTestService.saveTAssignmentForTest(cid,tAssignment,request);
		
		//根据考试创建成绩册
		tGradebookService.createGradebook(cid, tAssignment);
		
		if (result) {
			return "redirect:/teaching/test/testMain";
		}else {
			return "redirect:/teaching/test/testList?status=0";
		}
	}
	/****************************************************************************
	 * 功能：删除测验
	 * 作者：黄崔俊
	 * 时间：2015-10-21 16:19:05
	 ****************************************************************************/
	@RequestMapping("/teaching/test/deleteTestById")
	public String deleteExamById(@RequestParam Integer testId){
		TAssignment test = tAssignmentDAO.findTAssignmentById(testId);
		tAssignmentForTestService.deleteTestById(test);
		return "redirect:/teaching/test/testList?status=0";
	}
	
	
	/****************************************************************************
	 * 功能：发布考试
	 * 作者：黄崔俊
	 * 时间：2015-10-28 14:26:19
	 ****************************************************************************/
	@RequestMapping("/teaching/test/deployTest")
	public ModelAndView deployTest(@RequestParam Integer testId){
		ModelAndView mav = new ModelAndView();
		//获取当前作业
		TAssignment tAssignment = tAssignmentService.findTAssignmentById(testId);
		//修改作业发布状态
		tAssignmentService.changeAsignmentStatus(tAssignment);
		mav.addObject("status", 0);
		mav.setViewName("redirect:/teaching/test/testList");
		return mav;
	}
		
	/****************************************************************************
	 * 功能：开始考试
	 * 作者：黄崔俊
	 * 时间：2015-12-3 16:26:11
	 ****************************************************************************/
	@RequestMapping("/teaching/test/beginTest")
	public ModelAndView beginTest(@RequestParam int testId,Integer simulation,Integer moduleType,Integer selectId){
		//新建ModelAndView对象；
		ModelAndView mav=new ModelAndView();
		//全部:-1  视频:1  图片:2  参考文件:3  作业:4  测试:5 考试:6
		mav.addObject("selectId", selectId);
		mav.addObject("moduleType", moduleType);
		//获取登录用户信息
		User nowUser = shareService.getUser();
		//根据id获取考试信息
		TAssignment parentTest = tAssignmentService.findTAssignmentById(testId);
		//根据当前登陆人获取考试信息
		TAssignment test = tAssignmentForTestService.findTestByUserAndTest(nowUser,parentTest);
		//组卷
		test = tAssignmentForTestService.createRandomTest(test,parentTest);
		mav.addObject("testId", test.getId());
		//判断是否是模拟测验(1表示模拟测验)
		mav.addObject("simulation", simulation);
		
		mav.setViewName("redirect:/teaching/test/newBeginTest");
		return mav;
	}
	
	/****************************************************************************
	 * 功能：开始考试
	 * 作者：黄崔俊
	 * 时间：2015-12-3 16:26:11
	 ****************************************************************************/
	@RequestMapping("/teaching/test/newBeginTest")
	public ModelAndView newBeginTest(@RequestParam int testId,Integer simulation,Integer moduleType,Integer selectId){
		//新建ModelAndView对象；
		ModelAndView mav=new ModelAndView();
		//全部:-1  视频:1  图片:2  参考文件:3  作业:4  测试:5 考试:6
		mav.addObject("selectId", selectId);
		mav.addObject("moduleType", moduleType);
		//获取登录用户信息
		User nowUser = shareService.getUser();
		mav.addObject("user", nowUser);
		//根据id获取考试信息
		TAssignment test = tAssignmentService.findTAssignmentById(testId);
		mav.addObject("test", test);
		TAssignment parentTest = tAssignmentService.findTAssignmentById(test.getTestParentId());
		mav.addObject("parentTest", parentTest);
		
		//判断是否是模拟测验(1表示模拟测验)
		mav.addObject("simulation", simulation);
		mav.setViewName("teaching/test/newBeginTest.jsp");
		return mav;
		
	}
	
	/****************************************************************************
	 * 功能：学生查看答题详情
	 * 作者：黄崔俊
	 * 时间：2015-12-6 14:04:03
	 ****************************************************************************/
	@RequestMapping("/teaching/test/findTestDetail")
	public ModelAndView findTestDetail(@RequestParam Integer testId,Integer tCourseSiteId,Integer moduleType,Integer selectId){
		//新建ModelAndView对象；
		ModelAndView mav=new ModelAndView();
		//获取登录用户信息
		User nowUser = shareService.getUserDetail();
		mav.addObject("tCourseSiteId", tCourseSiteId);
		mav.addObject("moduleType", moduleType);
		mav.addObject("selectId", selectId);
		mav.addObject("user", nowUser);
		TAssignmentGrading tAssignmentGrading = tAssignmentForTestService.findTAssignmentGradingByTestIdAndUser(testId,nowUser);
		mav.addObject("tAssignmentGrading", tAssignmentGrading);
		//获取测验信息
		TAssignment parentTest = tAssignmentGrading.getTAssignment();
		
		mav.addObject("parentTest", parentTest);
		//根据当前登陆人获取考试信息
		TAssignment test = tAssignmentForTestService.findTestByUserAndTest(nowUser,parentTest);
		mav.addObject("test", test);
		
		//查询学生最新一次的答题记录
		List<TAssignmentItemMapping> tAssignmentItemMappingList = tAssignmentForTestService.findTAssignmentItemMappingsByTAssignmentGrading(tAssignmentGrading); 
		Map<Integer, BigDecimal> scoreMap = new HashMap<Integer, BigDecimal>();
		Map<Integer, TAssignmentItemMapping> recordMap = new HashMap<Integer, TAssignmentItemMapping>();
		Integer itemId = 0;
		for (TAssignmentItemMapping tAssignmentItemMapping : tAssignmentItemMappingList) {
			itemId = tAssignmentItemMapping.getTAssignmentItem().getId();
			scoreMap.put(itemId, tAssignmentItemMapping.getAutoscore());
			recordMap.put(tAssignmentItemMapping.getTAssignmentAnswer().getId(), tAssignmentItemMapping);
			
		}
		mav.addObject("scoreMap", scoreMap);
		mav.addObject("recordMap", recordMap);
		mav.setViewName("teaching/test/testDetail.jsp");
		
		return mav;
	}
	
	/****************************************************************************
	 * 功能：保存学生提交测验记录
	 * 作者：魏诚	
	 * 时间：2015-8-19 16:04:23
	 ****************************************************************************/
	@RequestMapping("/teaching/test/saveTAssignmentItemMapping")
	public ModelAndView saveTAssignmentItemMapping(HttpServletRequest request,@RequestParam int assignmentId,Integer submitTime,Integer simulation,@ModelAttribute("selected_courseSite") Integer cid,Integer moduleType,Integer selectId){
		ModelAndView mav = new ModelAndView();
		//全部:-1  视频:1  图片:2  参考文件:3  作业:4  测试:5 考试:6
		mav.addObject("selectId", selectId);
		mav.addObject("moduleType", moduleType);
		//如果已经保存数据，则重新清空复写
		tAssignmentForExamService.deleteTAssignmentItemMapping(assignmentId);

		//保存学生答题的过程记录
		BigDecimal totalScore = tAssignmentForTestService.saveTAssignmentItemMapping(request, assignmentId, submitTime);
		//如果为提交,则计入打分表
		if(submitTime>0){
			 TAssignmentGrading tAssignmentGrade = tAssignmentForTestService.saveTAssignmentGradeForTest(totalScore,assignmentId,submitTime);
			 if (simulation==null ||1!=simulation) {
				
				 //如果不是模拟，则根据测验查询成绩是否进成绩册
				 TAssignment tAssignment = tAssignmentService.findTAssignmentById(assignmentId);
				 tAssignment = tAssignmentService.findTAssignmentById(tAssignment.getTestParentId());
				 
				 tGradebookService.saveGradebook(cid,tAssignment.getId(),tAssignmentGrade);
			 }
		}
		//返回(由于前台未设置保存，则此处提交考试，故暂且如下处置跳转) tCourseSiteId,Integer moduleType,Integer selectId
		mav.setViewName("redirect:/teaching/test/findTestDetail?testId="+tAssignmentService.findTAssignmentById(assignmentId).getTestParentId()+"&tCourseSiteId="+cid);
		return mav;
	}
	/**************************************************************************************
	 * @功能：查看已提交测验的成绩列表
	 * @作者：裴继超
	 * @日期：2015/09/23
	 *************************************************************************************/
	@RequestMapping("/teaching/test/examGradingList")
	public ModelAndView assignmentGradingList(@RequestParam Integer examId){
		ModelAndView mav = new ModelAndView();
		
		User user = shareService.getUser();//查看当前登录用户
		//flag用于判断是学生和教师
		Integer flag = 0;
		if (user.getAuthorities().toString().contains("TEACHER")) {
			flag = 1;//老师
		}
		if(user!=null&&(user.getAuthorities().toString().contains("EXCENTERDIRECTOR")
		        ||user.getAuthorities().toString().contains("LABMANAGER")
		        ||user.getAuthorities().toString().contains("SUPERADMIN")
		        ||user.getAuthorities().toString().contains("DEAN")
		        ||user.getAuthorities().toString().contains("SCHOOLLEADER")
		        ||user.getAuthorities().toString().contains("ASSOCIATEDEAN")
		        ||user.getAuthorities().toString().contains("EDUCATIONADMIN")
		        ||user.getAuthorities().toString().contains("LABCENTERMANAGER"))){
			flag = 1;//实验中心主任   或者实验室管理员   或者超级管理员   或者教务处   或者校领导   或者教学副院长   或者教务管理员   或者实验中心管理员   为教师权限
		}
		List<TAssignmentGrading> tAssignmentGradings = tAssignmentGradingService.findExamGradingList(examId, flag, user);
		
		
		mav.addObject("tAssignmentGradings", tAssignmentGradings);
		mav.addObject("user", user);
		mav.setViewName("teaching/test/examGradingList.jsp");
		return mav;
	}
	/****************************************************************************
	 * 功能：进入测试详情页面以便打分
	 * 作者：裴继超	
	 * 时间：2015-9-23
	 ****************************************************************************/
	@RequestMapping("/teaching/test/findTExamGradeToMark")
	public ModelAndView findTAssignmentGradeToMark(@RequestParam Integer examId, int flag){
		ModelAndView mav = new ModelAndView();
		User nowUser = shareService.getUser();
		TAssignmentGrading tAssignmentGrading = tAssignmentGradingService.findTAssignmentGradingById(examId);
		tAssignmentGrading.setUserByGradeBy(nowUser);
		tAssignmentGrading.setGradeTime(Calendar.getInstance());
		mav.addObject("tAssignmentGrade",tAssignmentGrading);
		mav.addObject("tAssignment", tAssignmentGrading.getTAssignment());
		mav.addObject("flag", flag);
		mav.addObject("user", nowUser);
		mav.setViewName("teaching/test/editExamGradeForTeacher.jsp");
		return mav;
	}
	
	/****************************************************************************
	 * 功能：查看学生答题详情
	 * 作者：黄崔俊
	 * 时间：2015-10-28 22:57:45
	 ****************************************************************************/
	@RequestMapping("/teaching/test/findTestDetailByGradingId")
	public ModelAndView findTestDetailByGradingId(@RequestParam Integer assignGradingId){
		//新建ModelAndView对象；
		ModelAndView mav=new ModelAndView();
		//获取登录用户信息
		User nowUser = shareService.getUserDetail();
		mav.addObject("user", nowUser);
		TAssignmentGrading tAssignmentGrading = tAssignmentGradingService.findTAssignmentGradingById(assignGradingId);
		//获取测验信息
		TAssignment parentTest = tAssignmentGrading.getTAssignment();
		
		mav.addObject("parentTest", parentTest);
		//根据得分获取考试信息
		TAssignment test = tAssignmentForTestService.findTestByUserAndTest(tAssignmentGrading.getUserByStudent(),parentTest);
		mav.addObject("test", test);
		
		//查询学生最新一次的答题记录
		List<TAssignmentItemMapping> tAssignmentItemMappingList = tAssignmentForTestService.findTAssignmentItemMappingsByTAssignmentGrading(tAssignmentGrading); 
		Map<Integer, BigDecimal> scoreMap = new HashMap<Integer, BigDecimal>();
		Map<Integer, TAssignmentItemMapping> recordMap = new HashMap<Integer, TAssignmentItemMapping>();
		Integer itemId = 0;
		for (TAssignmentItemMapping tAssignmentItemMapping : tAssignmentItemMappingList) {
			itemId = tAssignmentItemMapping.getTAssignmentItem().getId();
			scoreMap.put(itemId, tAssignmentItemMapping.getAutoscore());
			recordMap.put(tAssignmentItemMapping.getTAssignmentAnswer().getId(), tAssignmentItemMapping);
			
		}
		mav.addObject("scoreMap", scoreMap);
		mav.addObject("recordMap", recordMap);
		mav.setViewName("teaching/test/testDetail.jsp");
		
		return mav;
	}
	/****************************************************************************
	 * 功能：检查试题数量设定是否超过限制
	 * 作者：黄崔俊
	 * 时间：2015-12-2 11:16:01
	 ****************************************************************************/
	@RequestMapping("/teaching/test/checkItemQuantity")
	public @ResponseBody Map<String, String> checkItemQuantity(@RequestParam String[] questionIdArray,Integer[] typeArray,Integer[] quantityArray){
		
		String result = tAssignmentForTestService.checkItemQuantity(questionIdArray,typeArray,quantityArray);
		Map<String, String> map = new HashMap<String, String>();
		map.put("result", result);
		return map;
	}
	/**************************************************************************************
	 * @功能：查看已提交考试的成绩列表
	 * @作者：黄崔俊
	 * @日期：2015-12-6 15:20:36
	 *************************************************************************************/
	@RequestMapping("/teaching/test/testGradingList")
	public ModelAndView testGradingList(@RequestParam Integer tCourseSiteId,Integer testId,Integer moduleType,Integer selectId){
		ModelAndView mav = new ModelAndView();
		//全部:-1  视频:1  图片:2  参考文件:3  作业:4  测试:5 考试:6
		mav.addObject("selectId", selectId);
		mav.addObject("moduleType", moduleType);
		// 选择的课程中心
		TCourseSite tCourseSite = tCourseSiteService.findCourseSiteById(tCourseSiteId);
		mav.addObject("tCourseSite", tCourseSite);
		User user = shareService.getUser();//查看当前登录用户
		//flag用于判断是学生和教师
		Integer flag = 0;
		if (user.getAuthorities().toString().contains("TEACHER")) {
			flag = 1;//老师
		}
		if(user!=null&&(user.getAuthorities().toString().contains("EXCENTERDIRECTOR")
		        ||user.getAuthorities().toString().contains("LABMANAGER")
		        ||user.getAuthorities().toString().contains("SUPERADMIN")
		        ||user.getAuthorities().toString().contains("DEAN")
		        ||user.getAuthorities().toString().contains("SCHOOLLEADER")
		        ||user.getAuthorities().toString().contains("ASSOCIATEDEAN")
		        ||user.getAuthorities().toString().contains("EDUCATIONADMIN")
		        ||user.getAuthorities().toString().contains("LABCENTERMANAGER"))){
			flag = 1;//实验中心主任   或者实验室管理员   或者超级管理员   或者教务处   或者校领导   或者教学副院长   或者教务管理员   或者实验中心管理员   为教师权限
		} 
		
		//复用测试的方法，查看考试答题情况
		List<TAssignmentGrading> tAssignmentGradings = tAssignmentGradingService.findExamGradingList(testId, flag, user);
		//考试详细信息
		TAssignment tAssignment = tAssignmentService.findTAssignmentById(testId);
		mav.addObject("tAssignment", tAssignment);
		mav.addObject("tAssignmentGradings", tAssignmentGradings);
		mav.addObject("user", user);
		
		//未提交作业的学生列表
		Set<TCourseSiteUser> allStudents = tCourseSite.getTCourseSiteUsers();
		Set<TCourseSiteUser> notCommitStudents = new HashSet<TCourseSiteUser>();
		if(tAssignmentGradings.size()!=0){
			for(TCourseSiteUser t:allStudents){
				for(TAssignmentGrading g:tAssignmentGradings){
					if(!t.getUser().getUsername().equals(g.getUserByStudent().getUsername())){
						notCommitStudents.add(t);
					}
				}
			}
		}else{
			notCommitStudents.addAll(allStudents);
		}
		mav.addObject("notCommitStudents", notCommitStudents);
		mav.setViewName("teaching/test/testGradingList.jsp");
		return mav;
	}
	
	/****************************************************************************
	 * 功能：开始考试(测试JPA效率)
	 * 作者：黄崔俊
	 * 时间：2015-12-3 16:26:11
	 ****************************************************************************/
	/*@RequestMapping("/teaching/test/testbeginTest")
	public void testbeginTest(@RequestParam int testId,Integer simulation){
		//新建ModelAndView对象；
		long begin = System.currentTimeMillis();
		long end = System.currentTimeMillis();
		String usernames = "201210733082,201210734004,201210734011,201310733034,201310733035,201310733040,201310733041,201310733045,201310733046,201310733048,201310733050,201310733051,201310733059,201310733064,201310734001,201310734002,201310734003,201310734004,201310734005,201310734007,201310734008,201310734009,201310734010,201310734011,201310734013,201310734014,201310734016,201310734017,201310734018,201310734019,201310734020";
		//String usernames = "201310734063";
		String[] usernameStrings = usernames.split(",");
		System.out.println(System.currentTimeMillis()-begin);
		begin = System.currentTimeMillis();
		List<TAssignment> tAssignments = new ArrayList<TAssignment>();
		for (String string : usernameStrings) {
			User user = userDAO.findUserByPrimaryKey(string);
			//根据id获取考试信息
			TAssignment parentTest = tAssignmentService.findTAssignmentById(testId);
			//根据当前登陆人获取考试信息
			TAssignment test = tAssignmentForTestService.findTestByUserAndTest(user,parentTest);
			System.out.println(System.currentTimeMillis()-begin);
			begin = System.currentTimeMillis();
			//组卷
			test = tAssignmentForTestService.testcreateRandomTest(test,parentTest);
			
			System.out.println(test.getId()+"---------");
			System.out.println(System.currentTimeMillis()-begin);
			begin = System.currentTimeMillis();
		}
		System.out.println(tAssignments);
		List<Integer> list = new ArrayList<Integer>();
		for (TAssignment tAssignment : tAssignments) {
			list.add(tAssignment.getId());
		}
		System.out.println(list);
		
		System.out.println(System.currentTimeMillis()-begin);
		System.out.println(System.currentTimeMillis()-end);
		begin = System.currentTimeMillis();
		
	}*/
	
	/**
	 * @内容：考试成绩有误修正数据临时方法
	 * @作者：黄崔俊
	 * @日期：2015-12-14 14:19:53
	 */
	/*@RequestMapping("/teaching/test/changeTAssignmentItemMapping")
	public ModelAndView changeTAssignmentItemMapping(HttpServletRequest request,@ModelAttribute("selected_courseSite") Integer cid){
		ModelAndView mav = new ModelAndView();
		Integer[] integers = new Integer[]{270,271,272,273,274,275,276,277,278};
		for (Integer assignmentId : integers) {
			//保存学生答题的过程记录
			BigDecimal totalScore = tAssignmentForTestService.saveChangeTAssignmentItemMapping(assignmentId);
			//如果为提交,则计入打分表
			 TAssignmentGrading tAssignmentGrade = tAssignmentForTestService.saveChangeTAssignmentGradeForTest(totalScore,assignmentId);
				
			 //如果不是模拟，则根据测验查询成绩是否进成绩册
			 TAssignment tAssignment = tAssignmentService.findTAssignmentById(assignmentId);
			 tAssignment = tAssignmentService.findTAssignmentById(tAssignment.getTestParentId());
			 
			 tGradebookService.saveGradebook(cid,tAssignment.getId(),tAssignmentGrade);
		}

		
		//返回
		mav.setViewName("redirect:/cms/courseSite?id="+cid);
		return mav;
	}*/
	
	/****************************************************************************
	 * 功能：开始考试
	 * 作者：黄崔俊
	 * 时间：2015-12-3 16:26:11
	 ****************************************************************************/
	@RequestMapping("/teaching/test/creatNewText")
	public ModelAndView creatNewText(@RequestParam int testId){
		//新建ModelAndView对象；
		ModelAndView mav=new ModelAndView();
		//获取登录用户信息
		User nowUser = shareService.getUser();
		//根据id获取考试信息
		TAssignment parentTest = tAssignmentService.findTAssignmentById(testId);
		//根据当前登陆人获取考试信息
		TAssignment test = tAssignmentForTestService.findTestByTeacherAndTest(nowUser,parentTest);
		//组卷
		test = tAssignmentForTestService.createRandomTest(test,parentTest);
		mav.addObject("testId", test.getId());
		
		mav.setViewName("redirect:/teaching/test/newCreatNewText");
		return mav;
	}
	
	/****************************************************************************
	 * 功能：开始考试
	 * 作者：黄崔俊
	 * 时间：2015-12-3 16:26:11
	 ****************************************************************************/
	@RequestMapping("/teaching/test/newCreatNewText")
	public ModelAndView newCreatNewText(@RequestParam int testId){
		//新建ModelAndView对象；
		ModelAndView mav=new ModelAndView();
		//获取登录用户信息
		User nowUser = shareService.getUser();
		mav.addObject("user", nowUser);
		//根据id获取考试信息
		TAssignment test = tAssignmentService.findTAssignmentById(testId);
		mav.addObject("test", test);
		TAssignment parentTest = tAssignmentService.findTAssignmentById(test.getTestParentId());
		mav.addObject("parentTest", parentTest);
				
		mav.setViewName("teaching/test/newCreatNewText.jsp");
		return mav;		
		
	}
	
	/****************************************************************************
	 * 功能：查看答案
	 * 作者：罗璇
	 * 时间：2016-04-05 16:11:24
	 ****************************************************************************/
	@RequestMapping("/teaching/test/newViewAnswers")
	public ModelAndView newViewAnswers(@RequestParam int testId){
		//新建ModelAndView对象；
		ModelAndView mav=new ModelAndView();
		//获取登录用户信息
		User nowUser = shareService.getUser();
		mav.addObject("user", nowUser);
		//根据id获取考试信息
		TAssignment test = tAssignmentService.findTAssignmentById(testId);
		mav.addObject("test", test);
		TAssignment parentTest = tAssignmentService.findTAssignmentById(test.getTestParentId());
		mav.addObject("parentTest", parentTest);
				
		mav.setViewName("teaching/test/newViewAnswers.jsp");
		return mav;		
		
	}
	
	
}