package net.xidlims.web.teaching;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.xidlims.dao.TAssignmentAnswerDAO;
import net.xidlims.dao.TAssignmentDAO;
import net.xidlims.dao.TAssignmentGradingDAO;
import net.xidlims.dao.TAssignmentItemDAO;
import net.xidlims.dao.TAssignmentItemMappingDAO;
import net.xidlims.dao.TAssignmentSectionDAO;
import net.xidlims.dao.TCourseSiteDAO;
import net.xidlims.domain.TAssignment;
import net.xidlims.domain.TAssignmentAnswer;
import net.xidlims.domain.TAssignmentAnswerAssign;
import net.xidlims.domain.TAssignmentControl;
import net.xidlims.domain.TAssignmentGrading;
import net.xidlims.domain.TAssignmentItem;
import net.xidlims.domain.TAssignmentItemMapping;
import net.xidlims.domain.TAssignmentSection;
import net.xidlims.domain.TCourseSite;
import net.xidlims.domain.TCourseSiteUser;
import net.xidlims.domain.User;
import net.xidlims.service.common.ShareService;
import net.xidlims.service.teaching.TAssignmentAnswerService;
import net.xidlims.service.teaching.TAssignmentForExamService;
import net.xidlims.service.teaching.TAssignmentGradingService;
import net.xidlims.service.tcoursesite.TAssignmentItemService;
import net.xidlims.service.teaching.TAssignmentSectionService;
import net.xidlims.service.tcoursesite.TAssignmentQuestionPoolService;
import net.xidlims.service.tcoursesite.TAssignmentService;
import net.xidlims.service.tcoursesite.TCourseSiteService;
import net.xidlims.service.tcoursesite.TCourseSiteUserService;
import net.xidlims.service.tcoursesite.TGradebookService;
import net.xidlims.view.ViewTAssignment;

import org.hibernate.criterion.LikeExpression;
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
 * @功能：自主管理模块 
 * @作者：魏诚 时间：2014-07-14
 *****************************************************************************************/
@Controller("TeachingExamController")
@SessionAttributes("selected_courseSite")
public class TeachingExamController<JsonResult> {
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
	private TAssignmentGradingDAO tAssignmentGradingDAO;
	@Autowired
	private TAssignmentGradingService tAssignmentGradingService;
	@Autowired
	private TAssignmentSectionService tAssignmentSectionService;
	@Autowired
	private TAssignmentItemService tAssignmentItemService;
	@Autowired
	private TAssignmentAnswerService tAssignmentAnswerService;
	@Autowired
	private TGradebookService tGradebookService;
	@Autowired
	private TCourseSiteService tCourseSiteService;
	@Autowired
	private TAssignmentQuestionPoolService tAssignmentQuestionPoolService;
	@Autowired
	private TCourseSiteUserService tCourseSiteUserService;
	
	/**************************************************************************************
	 * @练习和测验 练习测验入口
	 * @作者：魏诚
	 * @日期：2015-08-3
	 *************************************************************************************/
	@RequestMapping("/teaching/exam/examIframe")
	public ModelAndView examIframe(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		//如果为切换学生，则直接进入测试，设置当前的返回链接
		if(request.getSession().getAttribute("selected_role").equals("ROLE_TEACHER")){
			request.getSession().setAttribute("URL", "teaching/exam/examMain");
		}else{
			request.getSession().setAttribute("URL", "teaching/exam/examIframe");
		}

		mav.setViewName("teaching/exam/examIframe.jsp");
		return mav;
	}
	
	/**************************************************************************************
	 * @练习和测验 创建测验入口,如果是老师，创建测试，如果是学生开始测试
	 * @作者：魏诚
	 * @日期：2015-08-3
	 *************************************************************************************/
	@RequestMapping("/teaching/exam/examMain")
	public ModelAndView examMain(HttpSession httpSession,@ModelAttribute("selected_courseSite")Integer cid) {
		ModelAndView mav = new ModelAndView();
		//如果为切换学生，则直接进入测试，如果为老师，则进入iframe页面，可以查看题库。设置当前的返回链接
		if(httpSession.getAttribute("selected_role").equals("ROLE_TEACHER")){
			httpSession.setAttribute("URL", "teaching/exam/examMain");
		}else{
			httpSession.setAttribute("URL", "teaching/exam/examIframe");
		}
		User nowUser = shareService.getUserDetail();
		//已发布列表
		Integer status = 1;//1表示已发布
		String type = "exam";
		//List<TAssignment> tAssignmentsYes = tAssignmentService.findTAssignmentList(cid, type, status); 
		Integer flag = 2;
		if (nowUser.getAuthorities().toString().contains("TEACHER")) {
			flag = 1;
		}else {
			flag = 0;
		}
		if(nowUser!=null&&(nowUser.getAuthorities().toString().contains("EXCENTERDIRECTOR")
		        ||nowUser.getAuthorities().toString().contains("LABMANAGER")
		        ||nowUser.getAuthorities().toString().contains("SUPERADMIN")
		        ||nowUser.getAuthorities().toString().contains("DEAN")
		        ||nowUser.getAuthorities().toString().contains("SCHOOLLEADER")
		        ||nowUser.getAuthorities().toString().contains("ASSOCIATEDEAN")
		        ||nowUser.getAuthorities().toString().contains("EDUCATIONADMIN")
		        ||nowUser.getAuthorities().toString().contains("LABCENTERMANAGER"))){
			flag = 1;//实验中心主任   或者实验室管理员   或者超级管理员   或者教务处   或者校领导   或者教学副院长    或者教务管理员   或者实验中心管理员   为教师权限
		}
		List<ViewTAssignment> tAssignmentsYes = tAssignmentService.findViewExamList(nowUser, cid, flag,"lesson"); 
		mav.addObject("tAssignments",tAssignmentsYes);
		//已提交测试的成绩
		List<TAssignmentGrading> tAssignmentGradings = tAssignmentGradingService.findTAssignmentGradingList(nowUser,type);     
		mav.addObject("TAssignmentGrading",tAssignmentGradings);
		
		mav.setViewName("teaching/exam/examMain.jsp");
		return mav;
	}

	/**************************************************************************************
	 * @练习和测验 查看可发布测验
	 * @作者：魏诚                                    黄崔俊
	 * @日期：2015-08-3	   2015-8-28 09:38:52
	 *************************************************************************************/
	@RequestMapping("/teaching/exam/examList")
	public ModelAndView examList(@RequestParam Integer status,@ModelAttribute("selected_courseSite") Integer cid) {
		ModelAndView mav = new ModelAndView();
		User nowUser = shareService.getUser();
		Integer flag = 0;//学生
		if (nowUser.getAuthorities().toString().contains("TEACHER")) {
			flag = 1;//老师
		}
		if(nowUser!=null&&(nowUser.getAuthorities().toString().contains("EXCENTERDIRECTOR")
		        ||nowUser.getAuthorities().toString().contains("LABMANAGER")
		        ||nowUser.getAuthorities().toString().contains("SUPERADMIN")
		        ||nowUser.getAuthorities().toString().contains("DEAN")
		        ||nowUser.getAuthorities().toString().contains("SCHOOLLEADER")
		        ||nowUser.getAuthorities().toString().contains("ASSOCIATEDEAN")
		        ||nowUser.getAuthorities().toString().contains("EDUCATIONADMIN")
		        ||nowUser.getAuthorities().toString().contains("LABCENTERMANAGER"))){
			flag = 1;//实验中心主任   或者实验室管理员   或者超级管理员   或者教务处   或者校领导   或者教学副院长  或者教务管理员   或者实验中心管理员   为教师权限
		}
		String type = "exam";
		if (status==0) {
			List<TAssignment> tAssignments = tAssignmentService.findTAssignmentList(cid,type,status);
			mav.addObject("tAssignments",tAssignments);
		}
		if (status==1) {
			List<ViewTAssignment> viewTAssignments = tAssignmentService.findViewExamList(nowUser,cid,flag,"lesson");
			mav.addObject("tAssignments",viewTAssignments);
		}
		mav.addObject("status",status);
		mav.setViewName("teaching/exam/examList.jsp");
		return mav;
	}
	
	/****************************************************************************
	 * 功能：新建测验
	 * 作者：魏诚
	 * 时间：2015-8-6 10:00:29
	 ****************************************************************************/
	@RequestMapping("/teaching/exam/newExam")
	public ModelAndView newExam(HttpSession httpSession){
		//新建ModelAndView对象；
		ModelAndView mav=new ModelAndView();
		// 登陆用户
		mav.addObject("user", shareService.getUserDetail());
		//页面表单对象
		TAssignment  tAssignment = new TAssignment();
		TAssignmentControl  tAssignmentControl = new TAssignmentControl();
		//初始化默认时间
		Calendar duedate = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String nowDate =sdf.format(duedate.getTime());
		/*tAssignmentControl.setStartdate(duedate);
		tAssignmentControl.setDuedate(duedate);*/
		mav.addObject("startdate",nowDate);
		mav.addObject("duedate",nowDate);
		
		//初始化作业设置
		TAssignmentAnswerAssign tAssignmentAnswerAssign = new TAssignmentAnswerAssign();
		tAssignmentAnswerAssign.setScore(new BigDecimal(100));
		tAssignmentAnswerAssign.setUser(shareService.getUserDetail());
		
		tAssignment.setCreatedTime(duedate);
		tAssignment.setUser(shareService.getUserDetail());
		tAssignment.setTAssignmentControl(tAssignmentControl);
		tAssignment.setTAssignmentAnswerAssign(tAssignmentAnswerAssign);
				
		mav.addObject("tAssignment", tAssignment);
			
		mav.setViewName("teaching/exam/newExam.jsp");
		return mav;
	}
	

	/****************************************************************************
	 * 功能：保存测验
	 * 作者：魏诚
	 * 时间：2015-08-04
	 ****************************************************************************/
	@RequestMapping("/teaching/exam/saveExam")
	public String saveExam(@ModelAttribute("selected_courseSite") Integer cid,@ModelAttribute TAssignment tAssignment,@RequestParam int tCourseSiteId,Integer moduleType,Integer selectId){

		tAssignment = tAssignmentForExamService.saveTAssignmentForExam(cid,tAssignment);
		return "redirect:/teaching/exam/examInfo?examId="+tAssignment.getId()+"&tCourseSiteId="+tCourseSiteId+"&moduleType="+moduleType+"&selectId="+selectId;
	}
	
	/****************************************************************************
	 * 功能：查询测验信息以供编辑测验题目
	 * 作者：魏诚
	 * 时间：2015-08-04
	 ****************************************************************************/
	@RequestMapping("/teaching/exam/examInfo")
	public ModelAndView examInfo(@RequestParam int tCourseSiteId,int examId,Integer moduleType,Integer selectId){
		//新建ModelAndView对象；
		ModelAndView mav=new ModelAndView();
		//全部:-1  视频:1  图片:2  参考文件:3  作业:4  测试:5 考试:6
		mav.addObject("selectId", selectId);
		// 选择的课程中心
		TCourseSite tCourseSite = tCourseSiteService.findCourseSiteById(tCourseSiteId);
		mav.addObject("tCourseSite", tCourseSite);
		mav.addObject("moduleType", moduleType);
		mav.addObject("tCourseSiteId",tCourseSiteId);
		mav.addObject("examId",examId);
		//获取登录用户信息
		mav.addObject("user", shareService.getUserDetail());
		//获取测验信息
		mav.addObject("examInfo", tAssignmentService.findTAssignmentById(examId));
		/*List<TAssignmentItem> tAssignmentItems =  tAssignmentItemService.findTAssignmentItemListByExamId(examId);
		//获取题目详细信息
		mav.addObject("itemList", tAssignmentItems);*/
		mav.setViewName("teaching/exam/examInfo.jsp");
		return mav;
	}
	
	
	/****************************************************************************
	 * 功能：查询测验信息以供修改测验的基本信息
	 * 作者：魏诚
	 * 时间：2015-08-04
	 ****************************************************************************/
	@RequestMapping("/teaching/exam/findExamById")
	public ModelAndView findExamById(@RequestParam int examId,@RequestParam int tCourseSiteId,Integer moduleType,Integer selectId){
		//新建ModelAndView对象；
		ModelAndView mav=new ModelAndView();
		//全部:-1  视频:1  图片:2  参考文件:3  作业:4  测试:5 考试:6
		mav.addObject("selectId", selectId);
		// 选择的课程中心
		TCourseSite tCourseSite = tCourseSiteService.findCourseSiteById(tCourseSiteId);
		mav.addObject("tCourseSite", tCourseSite);
		mav.addObject("moduleType", moduleType);
		//获取登录用户信息
		mav.addObject("user", shareService.getUserDetail());
		//获取测验信息
		TAssignment examInfo = tAssignmentService.findTAssignmentById(examId);
		mav.addObject("tAssignment", examInfo);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		mav.addObject("startdate", sdf.format(examInfo.getTAssignmentControl().getStartdate().getTime()));
		mav.addObject("duedate", sdf.format(examInfo.getTAssignmentControl().getDuedate().getTime()));
		
		mav.setViewName("teaching/exam/newExam.jsp");
		return mav;
	}
	
	/****************************************************************************
	 * 功能：发布测验题目
	 * 作者：魏诚
	 * 时间：2015-08-21
	 ****************************************************************************/
	@RequestMapping("/teaching/exam/deployExam")
	public String deployExam(@RequestParam int examId){
		//获取当前作业
		TAssignment tAssignment = tAssignmentService.findTAssignmentById(examId);
		//修改作业发布状态
		tAssignmentService.changeAsignmentStatus(tAssignment);
		return "redirect:/teaching/exam/examList?flag=1";
	}
		
	/****************************************************************************
	 * 功能：新建测验题目
	 * 作者：魏诚
	 * 时间：2015-08-04
	 ****************************************************************************/
	@RequestMapping("/teaching/exam/newExamItem")
	public ModelAndView newExamDetail(@RequestParam Integer sectionId,Integer tCourseSiteId,Integer moduleType,Integer selectId,Integer examId) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("tCourseSiteId", tCourseSiteId);
		mav.addObject("moduleType", moduleType);
		mav.addObject("selectId", selectId);
		mav.addObject("examId", examId);
		TAssignmentItem tAssignmentItem = new TAssignmentItem();
		//初始化默认时间
		//初始化默认时间
		Calendar createdTime = Calendar.getInstance();
		tAssignmentItem.setCreatedTime(createdTime);
		tAssignmentItem.setUser(shareService.getUserDetail());
		TAssignmentSection tAssignmentSection = tAssignmentSectionService.findTAssignmentSectionById(sectionId);
		tAssignmentItem.setTAssignmentSection(tAssignmentSection);
		tAssignmentItem.setStatus(0);
		mav.addObject("tAssignmentItem", tAssignmentItem);
		mav.addObject("examInfo", tAssignmentSectionService.findTAssignmentSectionById(sectionId).getTAssignment());
		mav.setViewName("teaching/exam/newExamItem.jsp");
			
		/*}else{
			TAssignmentItem tAssignmentItem = tAssignmentItemDAO.findTAssignmentItemById(id);
			mav.addObject("tAssignmentItem", tAssignmentItem);
			mav.setViewName("teaching/exam/examInfo.jsp");
		}*/
	
		return mav;
	}
	
	/****************************************************************************
	 * 功能：新建测验题目
	 * 作者：魏诚
	 * 时间：2015-08-04
	 ****************************************************************************/
	@RequestMapping("/teaching/exam/newExamSection")
	public ModelAndView newExamSection(@RequestParam int tCourseSiteId,@RequestParam Integer assignmentId,Integer moduleType,Integer selectId) {
		ModelAndView mav = new ModelAndView();
		//全部:-1  视频:1  图片:2  参考文件:3  作业:4  测试:5 考试:6
		mav.addObject("selectId", selectId);
		// 选择的课程中心
		TCourseSite tCourseSite = tCourseSiteService.findCourseSiteById(tCourseSiteId);
		mav.addObject("tCourseSite", tCourseSite);
		mav.addObject("moduleType", moduleType);
		TAssignmentSection tAssignmentSection = new TAssignmentSection();
		//初始化默认时间
		Calendar createdTime = Calendar.getInstance();
		tAssignmentSection.setTAssignment(tAssignmentDAO.findTAssignmentById(assignmentId));
		tAssignmentSection.setUser(shareService.getUserDetail());
		tAssignmentSection.setCreatedTime(createdTime);
		tAssignmentSection.setStatus(0);
		mav.addObject("tAssignmentSection", tAssignmentSection);
		mav.setViewName("teaching/exam/newExamSection.jsp");
			
		/*}else{
			TAssignmentSection tAssignmentSection = tAssignmentSectionDAO.findTAssignmentSectionById(id);
			mav.addObject("tAssignmentSection", tAssignmentSection);
			mav.setViewName("teaching/exam/newExamSection.jsp");
		}*/
	
		return mav;
	}

	/****************************************************************************
	 * 功能：保存测验
	 * 作者：魏诚
	 * 时间：2015-08-04
	 ****************************************************************************/
	@RequestMapping("/teaching/exam/saveExamItem")
	public ModelAndView saveExamItem(@ModelAttribute TAssignmentItem tAssignmentItem,HttpServletRequest request){
		//新建ModelAndView对象；
		ModelAndView mav=new ModelAndView();
		tAssignmentItem = tAssignmentItemService.saveExamItem(tAssignmentItem,request);
		mav.setViewName("redirect:/teaching/exam/examInfo?examId="+tAssignmentItem.getTAssignmentSection().getTAssignment().getId());
		return mav;
	}
	
	/****************************************************************************
	 * 功能：保存测验大题
	 * 作者：魏诚
	 * 时间：2015-08-04
	 ****************************************************************************/
	/**************************************************************************
	 * Description:测试-添加题目-保存测验大题
	 * 
	 * @author：于侃
	 * @date ：2016-09-06
	 **************************************************************************/
	@RequestMapping("/teaching/exam/saveExamSection")
	public ModelAndView saveExamSection(@RequestParam int tCourseSiteId,@ModelAttribute TAssignmentSection tAssignmentSection,Integer moduleType,Integer selectId){
		//新建ModelAndView对象；
		ModelAndView mav=new ModelAndView();
		tAssignmentSection = tAssignmentSectionService.saveExamSection(tAssignmentSection); 
		mav.setViewName("redirect:/teaching/exam/examInfo?tCourseSiteId="+tCourseSiteId+"&examId="+ tAssignmentSection.getTAssignment().getId()+"&moduleType="+moduleType+"&selectId="+selectId);
		return mav;
	}
	
	/****************************************************************************
	 * 功能：查询小题
	 * 作者：魏诚
	 * 时间：2015-08-04
	 ****************************************************************************/
	@RequestMapping("/teaching/exam/examItemInfo")
	public ModelAndView examItemInfo(@RequestParam Integer examItemId){
		//新建ModelAndView对象；
		ModelAndView mav=new ModelAndView();
		//获取登录用户信息
		mav.addObject("user", shareService.getUserDetail());
		//获取测验信息
		mav.addObject("tAssignmentItem", tAssignmentItemService.findTAssignmentItemById(examItemId));

		mav.setViewName("teaching/exam/examItemInfo.jsp");
		return mav;
	}
	/****************************************************************************
	 * 功能：查询小题
	 * 作者：黄崔俊
	 * 时间：2015-8-31 15:02:15
	 ****************************************************************************/
	@RequestMapping("/teaching/exam/findExamItemById")
	public ModelAndView findExamItemById(@RequestParam Integer id){
		//新建ModelAndView对象；
		ModelAndView mav=new ModelAndView();
		//获取登录用户信息
		mav.addObject("user", shareService.getUserDetail());
		//获取测验信息
		mav.addObject("tAssignmentItem", tAssignmentItemService.findTAssignmentItemById(id));
		mav.setViewName("teaching/exam/newExamItem.jsp");
		return mav;
	}
	
	/****************************************************************************
	 * 功能：新建测验题目的答案选项
	 * 作者：魏诚
	 * 时间：2015-08-19
	 ****************************************************************************/
	@RequestMapping("/teaching/exam/newExamAnswer")
	public ModelAndView newExamAnswer(@RequestParam Integer itemId) {
		ModelAndView mav = new ModelAndView();
		TAssignmentAnswer tAssignmentAnswer = new TAssignmentAnswer();
		tAssignmentAnswer.setTAssignmentItem(tAssignmentItemService.findTAssignmentItemById(itemId));
		mav.addObject("tAssignmentAnswer", tAssignmentAnswer);
		mav.setViewName("teaching/exam/newExamAnswer.jsp");
			
		/*}else{
			TAssignmentAnswer tAssignmentAnswer = tAssignmentAnswerDAO.findTAssignmentAnswerById(id);
			mav.addObject("tAssignmentAnswer", tAssignmentAnswer);
			mav.setViewName("teaching/exam/newExamAnswer.jsp");
		}*/
	
		return mav;
	}
	
	/****************************************************************************
	 * 功能：保存测验题目的答案选项
	 * 作者：魏诚
	 * 时间：2015-08-19
	 ****************************************************************************/
	@RequestMapping("/teaching/exam/saveExamAnswer")
	public ModelAndView saveExamAnswer(@ModelAttribute TAssignmentAnswer tAssignmentAnswer){
		//新建ModelAndView对象；
		ModelAndView mav=new ModelAndView();
		tAssignmentAnswer = tAssignmentAnswerService.saveExamAnswer(tAssignmentAnswer);
		mav.setViewName("redirect:/teaching/exam/examItemInfo?examItemId="+tAssignmentAnswer.getTAssignmentItem().getId());
		return mav;
	}
	/****************************************************************************
	 * 功能：根据选项id查询选项
	 * 作者：魏诚
	 * 时间：2015-08-19
	 ****************************************************************************/
	@RequestMapping("/teaching/exam/findExamAnswerById")
	public ModelAndView findExamAnswerById(@RequestParam Integer id){
		//新建ModelAndView对象；
		ModelAndView mav=new ModelAndView();
		TAssignmentAnswer tAssignmentAnswer = tAssignmentAnswerService.findTAssignmentAnswerById(id);
		mav.addObject("tAssignmentAnswer", tAssignmentAnswer);
		mav.setViewName("teaching/exam/newExamAnswer.jsp");
		return mav;
	}
	
	/****************************************************************************
	 * 功能：删除测验题目的答案选项
	 * 作者：魏诚
	 * 时间：2015-08-19
	 ****************************************************************************/
	@RequestMapping("/teaching/exam/deleteExamAnswerById")
	public ModelAndView deleteExamAnswerById(@RequestParam Integer id){
		//新建ModelAndView对象；
		ModelAndView mav=new ModelAndView();
		TAssignmentAnswer tAssignmentAnswer = tAssignmentAnswerService.findTAssignmentAnswerById(id);
		tAssignmentAnswerService.deleteExamAnswer(tAssignmentAnswer);
		mav.setViewName("redirect:/teaching/exam/examItemInfo?examItemId="+tAssignmentAnswer.getTAssignmentItem().getId());
		return mav;
	}

	/****************************************************************************
	 * 功能：保存测验题目的答案选项
	 * 作者：魏诚
	 * 时间：2015-08-19
	 ****************************************************************************/
	@RequestMapping("/teaching/exam/deleteTAssignmentItem")
	public ModelAndView deleteTAssignmentItem(@RequestParam Integer id){
		//新建ModelAndView对象；
		ModelAndView mav=new ModelAndView();
		TAssignmentItem tAssignmentItem = tAssignmentItemService.findTAssignmentItemById(id);
		tAssignmentItemService.deleteTAssignmentItem(tAssignmentItem);
		String returnBack = "redirect:/teaching/exam/examInfo?examId="+tAssignmentItem.getTAssignmentSection().getTAssignment().getId();
		mav.setViewName(returnBack);
		return mav;
	}
	
	/****************************************************************************
	 * 功能：保存测验
	 * 作者：魏诚
	 * 时间：2015-08-04
	 ****************************************************************************/
	@RequestMapping("/teaching/exam/beginExam")
	public ModelAndView beginExam(@RequestParam int examId,Integer simulation,Integer moduleType,Integer selectId){
		//新建ModelAndView对象；
		ModelAndView mav=new ModelAndView();
		//全部:-1  视频:1  图片:2  参考文件:3  作业:4  测试:5 考试:6
		mav.addObject("selectId", selectId);
		mav.addObject("moduleType", moduleType);
		//获取登录用户信息
		User nowUser = shareService.getUserDetail();
		mav.addObject("user", nowUser);
		//获取测验信息
		TAssignment examInfo = tAssignmentService.findTAssignmentById(examId);
		ViewTAssignment viewTAssignment = new ViewTAssignment();
		viewTAssignment.setTAssignmentSections(examInfo.getTAssignmentSections());
		
		mav.addObject("examInfo", examInfo);
		//获取题目详细信息
		List<TAssignmentItem> items = tAssignmentItemService.findTAssignmentItemListByExamId(examId);
		mav.addObject("itemList", items);
		//判断是否是模拟测验(1表示模拟测验)
		mav.addObject("simulation", simulation);
		//判断该学生是否有保存未提交的测验，如果有，则读取记录；
		List<TAssignmentItemMapping> tAssignmentItemMappingList = tAssignmentItemService.findTAssignmentItemMappingsByUserAndExamId(nowUser,examInfo); 
		if(tAssignmentItemMappingList.size()>0){
			mav.addObject("tAssignmentItemMappingList", tAssignmentItemMappingList);
			mav.setViewName("teaching/exam/editBeginExam.jsp");
		}else{
			mav.setViewName("teaching/exam/newBeginExam.jsp");
		}
		
		
		return mav;
	}
	
	/****************************************************************************
	 * 功能：学生提交测验
	 * 作者：魏诚	
	 * 时间：2015-8-19 16:04:23
	 ****************************************************************************/
	/*@RequestMapping("/teaching/exam/saveTAssignmentGradeForExam")
	public ModelAndView saveTAssignmentGradeForExam(HttpServletRequest request,@RequestParam int status){
		ModelAndView mav = new ModelAndView();
		//保存学生提交测验
		tAssignmentForExamService.saveTAssignmentGradeForExam(request,status);
		//返回
		mav.setViewName("redirect:/teaching/assignment/assignmentList?flag=0");
		return mav;
	}*/
	
	/****************************************************************************
	 * 功能：保存学生提交测验记录
	 * 作者：魏诚	
	 * 时间：2015-8-19 16:04:23
	 ****************************************************************************/
	@RequestMapping("/teaching/exam/saveTAssignmentItemMapping")
	public ModelAndView newTAssignmentItemMapping(HttpServletRequest request,@RequestParam int assignmentId,Integer submitTime,Integer simulation,@ModelAttribute("selected_courseSite") Integer cid,Integer moduleType,Integer selectId){
		ModelAndView mav = new ModelAndView();
		//如果已经保存数据，则重新清空复写
		tAssignmentForExamService.deleteTAssignmentItemMapping(assignmentId);

		//保存学生答题的过程记录
		tAssignmentForExamService.saveTAssignmentItemMapping(request, assignmentId, submitTime);
		
		//如果为提交,则计入打分表
		if(submitTime>0){
			 TAssignmentGrading tAssignmentGrade = tAssignmentForExamService.saveTAssignmentGradeForExam(request,assignmentId,submitTime);
			 
			 if (simulation==null ||1!=simulation) {
				
				 //如果不是模拟，则根据测验查询成绩是否进成绩册
				 tGradebookService.saveGradebook(cid,assignmentId,tAssignmentGrade);
			 }
		}
		//返回
		mav.setViewName("redirect:/teaching/exam/findExamDetailForStudent?tCourseSiteId="+cid+"&examId="+assignmentId+"&moduleType="+moduleType+"&selectId="+selectId);
		//mav.setViewName("redirect:/teaching/exam/examMain");
		return mav;
	}
	/**************************************************************************************
	 * @功能：查看已提交测验的成绩列表
	 * @作者：裴继超
	 * @日期：2015/09/23
	 *************************************************************************************/
	@RequestMapping("/teaching/exam/examGradingList")
	public ModelAndView assignmentGradingList(@RequestParam Integer tCourseSiteId,Integer examId,Integer moduleType,Integer selectId){
		ModelAndView mav = new ModelAndView();
		//全部:-1  视频:1  图片:2  参考文件:3  作业:4  测试:5 考试:6
		mav.addObject("selectId", selectId);
		mav.addObject("moduleType", moduleType);
		// 选择的课程中心
		TCourseSite tCourseSite = tCourseSiteService.findCourseSiteById(tCourseSiteId);
		mav.addObject("tCourseSite", tCourseSite);
		//当前选择的测试
		TAssignment tAssignment = tAssignmentService.findTAssignmentById(examId);
		mav.addObject("tAssignment", tAssignment);
		User user = shareService.getUserDetail();//查看当前登录用户
		//flag用于判断是学生和教师
		Integer flag = 0;
		if (user.getAuthorities().toString().contains("TEACHER")&&user.getUsername().equals(tCourseSite.getUserByCreatedBy().getUsername())) {
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
			flag = 1;//实验中心主任   或者实验室管理员   或者超级管理员   或者教务处   或者校领导   或者教学副院长  或者教务管理员   或者实验中心管理员   为教师权限
		}
		
		List<TAssignmentGrading> tAssignmentGradings = tAssignmentGradingService.findExamGradingList(examId, flag, user);
		
		
		mav.addObject("tAssignmentGradings", tAssignmentGradings);
		mav.addObject("user", user);
		mav.addObject("flag", flag);
		
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
		mav.setViewName("teaching/exam/examGradingList.jsp");
		return mav;
	}
	/****************************************************************************
	 * 功能：进入测试详情页面以便打分
	 * 作者：裴继超	
	 * 时间：2015-9-23
	 ****************************************************************************/
	@RequestMapping("/teaching/exam/findTExamGradeToMark")
	public ModelAndView findTAssignmentGradeToMark(@RequestParam Integer examId, int flag){
		ModelAndView mav = new ModelAndView();
		User nowUser = shareService.getUserDetail();
		TAssignmentGrading tAssignmentGrading = tAssignmentGradingService.findTAssignmentGradingById(examId);
		tAssignmentGrading.setUserByGradeBy(nowUser);
		tAssignmentGrading.setGradeTime(Calendar.getInstance());
		mav.addObject("tAssignmentGrade",tAssignmentGrading);
		mav.addObject("tAssignment", tAssignmentGrading.getTAssignment());
		mav.addObject("flag", flag);
		mav.addObject("user", nowUser);
		mav.setViewName("teaching/exam/editExamGradeForTeacher.jsp");
		return mav;
	}
	/****************************************************************************
	 * 功能：从题库中引入试题
	 * 作者：黄崔俊
	 * 时间：2015-11-4 16:10:26
	 ****************************************************************************/
	@RequestMapping("/teaching/exam/importInExam")
	public @ResponseBody String importInExam(@RequestParam Integer[] itemIdArray,Integer sectionId,Integer itemScore){

		tAssignmentForExamService.importItemsInExam(sectionId, itemIdArray,itemScore);
		
		return "";
	}
	
	/**************************************************************************
	 * Description:测试-添加题目-选择导入题目数量随机从题库中引入试题
	 * 
	 * @author：于侃
	 * @date ：2016-09-0
	 **************************************************************************/
	@RequestMapping("/teaching/exam/randomImportInExam")
	public @ResponseBody String randomImportInExam(@RequestParam Integer sectionId,@RequestParam Integer selectNum,Integer totalRecords,Integer questionId,Integer itemScore){
		//产生随机数组
		Integer[] values = new Integer[selectNum];
		Random random = new Random();
		int count = 0;
		while(count < selectNum) {  
			int number = random.nextInt(totalRecords);
			boolean flag = true;  
			for (int j = 0; j < count; j++) {  
				if(number == values[j]){  
					flag = false;  
					break;  
				}  
			}  
			if(flag){  
				values[count] = number;  
				count++;  
			}  
		} 
		
		List<TAssignmentItem> items = tAssignmentQuestionPoolService.findTAssignmentItemsByQuestionId(questionId, 1, -1);
		//通过随机数组值来获取题库中题目
		for(int i = 0;i < selectNum;i++){
			values[i]=items.get(values[i]).getId();
		}
		tAssignmentForExamService.importItemsInExam(sectionId,values,itemScore);
		return "";
	}
	
	/**************************************************************************
	 * Description:知识技能体验-测试-查看学生答题详情
	 * 
	 * @author：裴继超
	 * @date ：2016-9-3
	 **************************************************************************/
	@RequestMapping("/teaching/exam/findExamDetail")
	public ModelAndView findExamDetail(@RequestParam int tCourseSiteId,Integer assignGradingId,Integer moduleType,Integer selectId){
		//新建ModelAndView对象；
		ModelAndView mav=new ModelAndView();
		//全部:-1  视频:1  图片:2  参考文件:3  作业:4  测试:5 考试:6
		mav.addObject("selectId", selectId);
		mav.addObject("moduleType", moduleType);
		// 选择的课程中心
		TCourseSite tCourseSite = tCourseSiteService.findCourseSiteById(tCourseSiteId);
		mav.addObject("tCourseSite", tCourseSite);
		//获取登录用户信息
		User nowUser = shareService.getUserDetail();
		mav.addObject("user", nowUser);
		TAssignmentGrading tAssignmentGrading = tAssignmentGradingService.findTAssignmentGradingById(assignGradingId);
		//获取测验信息
		TAssignment examInfo = tAssignmentGrading.getTAssignment();
		ViewTAssignment viewTAssignment = new ViewTAssignment();
		viewTAssignment.setTAssignmentSections(examInfo.getTAssignmentSections());
		
		mav.addObject("examInfo", examInfo);
		//获取题目详细信息
		List<TAssignmentItem> items = tAssignmentItemService.findTAssignmentItemListByExamId(examInfo.getId());
		mav.addObject("itemList", items);
		//查询学生最新一次的答题记录
		List<TAssignmentItemMapping> tAssignmentItemMappingList = tAssignmentItemService.findTAssignmentItemMappingsByTAssignmentGrading(tAssignmentGrading); 
		Map<Integer, BigDecimal> scoreMap = new HashMap<Integer, BigDecimal>();
		Map<Integer, TAssignmentItemMapping> recordMap = new HashMap<Integer, TAssignmentItemMapping>();
		/*if(tAssignmentItemMappingList.size()>0){
			mav.addObject("tAssignmentItemMappingList", tAssignmentItemMappingList);
		}*/
		Integer itemId = 0;
		for (TAssignmentItemMapping tAssignmentItemMapping : tAssignmentItemMappingList) {
			itemId = tAssignmentItemMapping.getTAssignmentItem().getId();
			scoreMap.put(itemId, tAssignmentItemMapping.getAutoscore());
			recordMap.put(tAssignmentItemMapping.getTAssignmentAnswer().getId(), tAssignmentItemMapping);
			
		}
		//角色判断：如果具有老师权限且为该课程的创建者或者助教则默认为老师，如果没有教师权限则默认为学生
		Integer flag = 2;
		if(nowUser!=null&&(nowUser.getAuthorities().toString().contains("EXCENTERDIRECTOR")
		        ||nowUser.getAuthorities().toString().contains("LABMANAGER")
		        ||nowUser.getAuthorities().toString().contains("SUPERADMIN")
		        ||nowUser.getAuthorities().toString().contains("DEAN")
		        ||nowUser.getAuthorities().toString().contains("SCHOOLLEADER")
		        ||nowUser.getAuthorities().toString().contains("ASSOCIATEDEAN")
		        ||nowUser.getAuthorities().toString().contains("EDUCATIONADMIN")
		        ||nowUser.getAuthorities().toString().contains("LABCENTERMANAGER"))){
			flag = 1;//实验中心主任   或者实验室管理员   或者超级管理员   或者教务处   或者校领导   或者教学副院长   或者教务管理员   或者实验中心管理员   为教师权限
		}else if(nowUser!=null&&nowUser.getAuthorities().toString().contains("TEACHER")){
			if (tCourseSite.getUserByCreatedBy().getUsername().equals(nowUser.getUsername())) {
				flag = 1;//如果当前登陆人是本人开的课程，则是老师身份
			}else if (tCourseSiteUserService.isSTeacherBySiteId(tCourseSiteId,nowUser.getUsername())){
				flag = 1;//如果当前登陆人是本课程的助教，则是老师身份
			}else {
				flag = 0;//如果当前登陆人不是本人开的课程，则是学生身份
			}
			
		}else if(nowUser!=null&&nowUser.getAuthorities().toString().contains("STUDENT")){
			flag = 0;
		}
		mav.addObject("flag", flag);
		mav.addObject("scoreMap", scoreMap);
		mav.addObject("recordMap", recordMap);
		mav.setViewName("teaching/exam/examDetail.jsp");
		
		return mav;
	}
	
	/**************************************************************************
	 * Description:知识技能体验-测试-查看学生答题详情
	 * 
	 * @author：裴继超
	 * @date ：2016-9-3
	 **************************************************************************/
	@RequestMapping("/teaching/exam/findExamDetailForStudent")
	public ModelAndView findExamDetailForStudent(@RequestParam int tCourseSiteId,Integer examId,Integer moduleType,Integer selectId){
		//新建ModelAndView对象；
		ModelAndView mav=new ModelAndView();
		//全部:-1  视频:1  图片:2  参考文件:3  作业:4  测试:5 考试:6
		mav.addObject("selectId", selectId);
		mav.addObject("moduleType", moduleType);
		// 选择的课程中心
		TCourseSite tCourseSite = tCourseSiteService.findCourseSiteById(tCourseSiteId);
		mav.addObject("tCourseSite", tCourseSite);
		//获取登录用户信息
		User nowUser = shareService.getUserDetail();
		mav.addObject("user", nowUser);
		TAssignmentGrading tAssignmentGrading = tAssignmentGradingService.findTAssignmentGradingByExamAndUser(examId,nowUser);
		
		/*TAssignmentGrading tAssignmentGrading = tAssignmentGradingService.findTAssignmentGradingById(assignGradingId);*/
		//获取测验信息
		TAssignment examInfo = tAssignmentGrading.getTAssignment();
		ViewTAssignment viewTAssignment = new ViewTAssignment();
		viewTAssignment.setTAssignmentSections(examInfo.getTAssignmentSections());
		
		mav.addObject("examInfo", examInfo);
		//获取题目详细信息
		List<TAssignmentItem> items = tAssignmentItemService.findTAssignmentItemListByExamId(examInfo.getId());
		mav.addObject("itemList", items);
		//查询学生最新一次的答题记录
		List<TAssignmentItemMapping> tAssignmentItemMappingList = tAssignmentItemService.findTAssignmentItemMappingsByTAssignmentGrading(tAssignmentGrading); 
		Map<Integer, BigDecimal> scoreMap = new HashMap<Integer, BigDecimal>();
		Map<Integer, TAssignmentItemMapping> recordMap = new HashMap<Integer, TAssignmentItemMapping>();
		/*if(tAssignmentItemMappingList.size()>0){
			mav.addObject("tAssignmentItemMappingList", tAssignmentItemMappingList);
		}*/
		Integer itemId = 0;
		for (TAssignmentItemMapping tAssignmentItemMapping : tAssignmentItemMappingList) {
			itemId = tAssignmentItemMapping.getTAssignmentItem().getId();
			scoreMap.put(itemId, tAssignmentItemMapping.getAutoscore());
			recordMap.put(tAssignmentItemMapping.getTAssignmentAnswer().getId(), tAssignmentItemMapping);
			
		}
		//角色判断：如果具有老师权限且为该课程的创建者或者助教则默认为老师，如果没有教师权限则默认为学生
		Integer flag = 2;
		if(nowUser!=null&&(nowUser.getAuthorities().toString().contains("EXCENTERDIRECTOR")
		        ||nowUser.getAuthorities().toString().contains("LABMANAGER")
		        ||nowUser.getAuthorities().toString().contains("SUPERADMIN")
		        ||nowUser.getAuthorities().toString().contains("DEAN")
		        ||nowUser.getAuthorities().toString().contains("SCHOOLLEADER")
		        ||nowUser.getAuthorities().toString().contains("ASSOCIATEDEAN")
		        ||nowUser.getAuthorities().toString().contains("EDUCATIONADMIN")
		        ||nowUser.getAuthorities().toString().contains("LABCENTERMANAGER"))){
			flag = 1;//实验中心主任   或者实验室管理员   或者超级管理员   或者教务处   或者校领导   或者教学副院长  或者教务管理员   或者实验中心管理员   为教师权限
		}else if(nowUser!=null&&nowUser.getAuthorities().toString().contains("TEACHER")){
			if (tCourseSite.getUserByCreatedBy().getUsername().equals(nowUser.getUsername())) {
				flag = 1;//如果当前登陆人是本人开的课程，则是老师身份
			}else if (tCourseSiteUserService.isSTeacherBySiteId(tCourseSiteId,nowUser.getUsername())){
				flag = 1;//如果当前登陆人是本课程的助教，则是老师身份
			}else {
				flag = 0;//如果当前登陆人不是本人开的课程，则是学生身份
			}
			
		}else if(nowUser!=null&&nowUser.getAuthorities().toString().contains("STUDENT")){
			flag = 0;
		}
		mav.addObject("flag", flag);
		mav.addObject("scoreMap", scoreMap);
		mav.addObject("recordMap", recordMap);
		mav.setViewName("teaching/exam/ExamDetail.jsp");
		
		return mav;
	}
	
	/**************************************************************************
	 * Description:测试-发布
	 * 
	 * @author：于侃
	 * @date ：2016-09-06
	 **************************************************************************/
	@RequestMapping("/teaching/exam/releaseSection")
	public ModelAndView releaseSection(@RequestParam int tCourseSiteId,@RequestParam Integer assignmentId,Integer moduleType,Integer selectId) {
		//新建ModelAndView对象；
		ModelAndView mav=new ModelAndView();
		//全部:-1  视频:1  图片:2  参考文件:3  作业:4  测试:5 考试:6
		mav.addObject("selectId", selectId);
		// 选择的课程中心
		TCourseSite tCourseSite = tCourseSiteService.findCourseSiteById(tCourseSiteId);
		mav.addObject("tCourseSite", tCourseSite);
		mav.addObject("moduleType", moduleType);
		//获取登录用户信息
		mav.addObject("user", shareService.getUserDetail());
		//获取测验信息
		mav.addObject("examInfo", tAssignmentService.findTAssignmentById(assignmentId));
		/*List<TAssignmentItem> tAssignmentItems =  tAssignmentItemService.findTAssignmentItemListByExamId(examId);
		//获取题目详细信息
		mav.addObject("itemList", tAssignmentItems);*/
		tAssignmentService.releaseExam(assignmentId);

		mav.setViewName("teaching/exam/examInfo.jsp");
		return mav;
	}

	
	/*
	 * 以下陈乐为写的所有方法，都是为避免影响其他页面调用，复制过来的
	 */
	
	/****************************************************************************
	 * 功能：查询小题
	 * 作者：陈乐为
	 * 时间：2016-9-8
	 ****************************************************************************/
	@RequestMapping("/teaching/exam/examItemInfoSelect")
	public ModelAndView examItemInfoSelect(@RequestParam Integer examItemId,Integer tCourseSiteId, Integer examId,Integer moduleType,Integer selectId){
		//新建ModelAndView对象；
		ModelAndView mav=new ModelAndView();
		//全部:-1  视频:1  图片:2  参考文件:3  作业:4  测试:5 考试:6
		mav.addObject("selectId", selectId);
		//获取登录用户信息
		mav.addObject("user", shareService.getUserDetail());
		//获取测验信息
		mav.addObject("tAssignmentItem", tAssignmentItemService.findTAssignmentItemById(examItemId));

		mav.addObject("examId",examId);
		mav.addObject("tCourseSiteId",tCourseSiteId);
		mav.addObject("moduleType",moduleType);
		
		mav.setViewName("teaching/exam/examItemInfo.jsp");
		return mav;
	}
	
	/****************************************************************************
	 * 功能：新建测验题目(为避免影响其他页面的调用，修改时拷贝此方法)
	 * 作者：陈乐为
	 * 时间：2016-9-8
	 ****************************************************************************/
	@RequestMapping("/teaching/exam/newExamItemForOne")
	public ModelAndView newExamItemForOne(@RequestParam Integer sectionId, Integer tCourseSiteId,Integer moduleType,Integer selectId) {
		ModelAndView mav = new ModelAndView();
		//全部:-1  视频:1  图片:2  参考文件:3  作业:4  测试:5 考试:6
		mav.addObject("selectId", selectId);
		mav.addObject("moduleType", moduleType);
		TAssignmentItem tAssignmentItem = new TAssignmentItem();
		//初始化默认时间
		//初始化默认时间
		Calendar createdTime = Calendar.getInstance();
		tAssignmentItem.setCreatedTime(createdTime);
		tAssignmentItem.setUser(shareService.getUserDetail());
		TAssignmentSection tAssignmentSection = tAssignmentSectionService.findTAssignmentSectionById(sectionId);
		tAssignmentItem.setTAssignmentSection(tAssignmentSection);
		tAssignmentItem.setStatus(0);
		mav.addObject("tAssignmentItem", tAssignmentItem);
		mav.addObject("tCourseSiteId", tCourseSiteId);
		mav.addObject("examInfo", tAssignmentSectionService.findTAssignmentSectionById(sectionId).getTAssignment());
		mav.setViewName("teaching/exam/newExamItem.jsp");
			
		return mav;
	}
	
	/****************************************************************************
	 * 功能：保存测验
	 * 作者：陈乐为
	 * 时间：2016-9-8
	 ****************************************************************************/
	@RequestMapping("/teaching/exam/saveExamItemForOne")
	public ModelAndView saveExamItemForOne(@ModelAttribute TAssignmentItem tAssignmentItem,HttpServletRequest request, @RequestParam Integer tCourseSiteId,Integer moduleType,Integer selectId){
		//新建ModelAndView对象；
		ModelAndView mav=new ModelAndView();
		tAssignmentItem = tAssignmentItemService.saveExamItem(tAssignmentItem,request);
		mav.setViewName("redirect:/teaching/exam/examInfo?examId="+tAssignmentItem.getTAssignmentSection().getTAssignment().getId()
				+"&moduleType="+moduleType+"&tCourseSiteId="+tCourseSiteId+"&selectId="+selectId);
				//+"&tCourseSiteId="+tAssignmentItem.getTAssignmentSection().getTAssignment().tCourseSiteId+"&moduleType="+moduleType);
		return mav;
	}
	
	/**************************************************************************
	 * Description:删除小题
	 * 
	 * @author：于侃
	 * @date ：2016-09-12
	 **************************************************************************/
	@RequestMapping("/teaching/exam/deleteTAssignmentItemById")
	public ModelAndView deleteTAssignmentItemById(@RequestParam Integer examItemId,Integer tCourseSiteId, Integer examId,Integer moduleType,Integer selectId){
		//新建ModelAndView对象；
		ModelAndView mav=new ModelAndView();
		TAssignmentItem tAssignmentItem = tAssignmentItemService.findTAssignmentItemById(examItemId);
		tAssignmentItemService.deleteTAssignmentItem(tAssignmentItem);
		mav.setViewName("redirect:/teaching/exam/examInfo?examId="+examId
				+"&moduleType="+moduleType+"&tCourseSiteId="+tCourseSiteId+"&selectId="+selectId);
		return mav;
	}
}