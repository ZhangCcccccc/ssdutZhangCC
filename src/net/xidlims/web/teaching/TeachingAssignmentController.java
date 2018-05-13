package net.xidlims.web.teaching;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.xidlims.dao.TAssignmentDAO;
import net.xidlims.dao.TAssignmentGradingDAO;
import net.xidlims.dao.TCourseSiteDAO;
import net.xidlims.domain.TAssignment;
import net.xidlims.domain.TAssignmentAnswerAssign;
import net.xidlims.domain.TAssignmentControl;
import net.xidlims.domain.TAssignmentGrading;
import net.xidlims.domain.TCourseSite;
import net.xidlims.domain.TCourseSiteUser;
import net.xidlims.domain.User;
import net.xidlims.service.common.ShareService;
import net.xidlims.service.teaching.TAssignmentGradingService;
import net.xidlims.service.tcoursesite.TAssignmentService;
import net.xidlims.service.tcoursesite.TCourseSiteService;
import net.xidlims.service.tcoursesite.TGradebookService;
import net.xidlims.view.ViewTAssignment;
import net.xidlims.view.ViewTAssignmentAllGrade;

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
 * @功能：作业模块 
 * @作者：魏诚 时间：2014-07-14     黄崔俊 2015-8-12 10:39:37
 *****************************************************************************************/
@Controller("TeachingAssignmentController")
@SessionAttributes("selected_courseSite")
public class TeachingAssignmentController<JsonResult> {
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
		binder.registerCustomEditor(Float.class, new org.skyway.spring.util.databinding.NaNHandlingNumberEditor(
				Float.class, true));
	}

	@Autowired
	private TAssignmentDAO tAssignmentDAO;
	@Autowired
	private TAssignmentGradingDAO tAssignmentGradingDAO;
	@Autowired
	private ShareService shareService;
	@Autowired
	private TCourseSiteDAO tCourseSiteDAO;
	@Autowired
	private TAssignmentService tAssignmentService;
	@Autowired
	private TAssignmentGradingService tAssignmentGradingService;
	@Autowired
	private TGradebookService tGradebookService;
	@Autowired
	private TCourseSiteService tCourseSiteService;

	
	
	/**************************************************************************************
	 * @作业 查看可发布作业       
	 * @作者：魏诚
	 * @日期：2015-08-3
	 *************************************************************************************/
	@RequestMapping("/teaching/assignment/assignmentList")
	public ModelAndView assignmentList(HttpSession httpSession,@RequestParam Integer flag,@ModelAttribute("selected_courseSite")Integer cid) {
		ModelAndView mav = new ModelAndView();
		/**
		 * flag暂作为权限判断所用，1表示教师，布置作业，批改作业，0表示学生，提交作业
		 */
		//当前登陆用户
		User nowUser = shareService.getUserDetail();
		//如果为切换学生，则直接进入答题列表，如果为老师，则进入作业页面。设置当前的返回链接
		if(httpSession.getAttribute("selected_role").equals("ROLE_TEACHER")){
			httpSession.setAttribute("URL", "teaching/assignment/assignmentList?flag=0");
		}else{
			httpSession.setAttribute("URL", "teaching/assignment/assignmentIframe");
			
		}
		
		List<ViewTAssignment> viewTAssignments = tAssignmentService.findViewTAssignmentList(nowUser,cid,flag);
		//用户变量
		mav.addObject("user",nowUser);
		mav.addObject("viewTAssignments",viewTAssignments);
		mav.addObject("flag",flag);
		
		
		mav.setViewName("teaching/assignment/assignmentList.jsp");
		
		return mav;
	}
	
	/**************************************************************************************
	 * @作业 查看已提交作业的成绩列表
	 * @作者：魏诚                             黄崔俊
	 * @日期：2015-08-3	2015-8-14 14:26:59
	 *************************************************************************************/
	@RequestMapping("/teaching/assignment/assignmentGradingList")
	public ModelAndView assignmentGradingList(@RequestParam Integer tCourseSiteId,
			Integer assignmentId, int flag,Integer moduleType,Integer selectId){
		//flag用于判断是学生和教师
		ModelAndView mav = new ModelAndView();
		//全部:-1  视频:1  图片:2  参考文件:3  作业:4  测试:5 考试:6
		mav.addObject("selectId", selectId);
		mav.addObject("moduleType", moduleType);
		// 选择的课程中心
		TCourseSite tCourseSite = tCourseSiteService.findCourseSiteById(tCourseSiteId);
		mav.addObject("tCourseSite", tCourseSite);
		User user = shareService.getUserDetail();//查看当前登录用户
		TAssignment tAssignment = tAssignmentService.findTAssignmentById(assignmentId);
		List<TAssignmentGrading> tAssignmentGradings = tAssignmentGradingService.findTAssignmentGradingList(assignmentId, flag, user);
		
		
		mav.addObject("tAssignmentGradings", tAssignmentGradings);
		mav.addObject("tAssignment", tAssignment);
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
		mav.setViewName("teaching/assignment/assignmentGradingList.jsp");
		return mav;
	}
	/**************************************************************************************
	 * @作业 给学生打分
	 * @作者： 黄崔俊
	 * @日期：2015-8-20 14:25:55
	 *************************************************************************************/
	@RequestMapping("/teaching/assignmentGrading/grade")
	public @ResponseBody String grade(@RequestParam Integer assignGradeId, String comments,Float finalScore,@ModelAttribute("selected_courseSite")Integer cid){
		//flag用于判断是学生和教师
		User nowUser = shareService.getUserDetail();
		Calendar calendar = Calendar.getInstance();
		TAssignmentGrading tAssignmentGrade = tAssignmentGradingService.updateTAssignmentGrading(assignGradeId,comments,finalScore,nowUser,calendar);
		//根据作业查询成绩是否进成绩册
		tGradebookService.saveGradebook(cid,tAssignmentGrade.getTAssignment().getId(),tAssignmentGrade);
		return null;
	}
	
	/**************************************************************************************
	 * @作业 创建作业
	 * @作者：魏诚
	 * @日期：2015-08-3
	 *************************************************************************************/
	@RequestMapping("/teaching/assignment/assignmentMain")
	public ModelAndView assignmentMain() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("teaching/assignment/assignmentMain.jsp");
		return mav;
	}
	
	/**************************************************************************************
	 * @作业 作业入口
	 * @作者：魏诚
	 * @日期：2015-08-3
	 *************************************************************************************/
	@RequestMapping("/teaching/assignment/assignmentIframe")
	public ModelAndView assignmentIframe(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		//如果为切换学生，则直接进入答题列表，设置当前的返回链接
		if(request.getSession().getAttribute("selected_role").equals("ROLE_TEACHER")){
			
			request.getSession().setAttribute("URL", "teaching/assignment/assignmentList?flag=0");
		}else{
			request.getSession().setAttribute("URL", "teaching/assignment/assignmentIframe");
		}
		mav.setViewName("teaching/assignment/assignmentIframe.jsp");
		return mav;
	}
	
	/****************************************************************************
	 * 功能：新建作业
	 * 作者：魏诚                                                              黄崔俊
	 * 时间：2015-8-6 10:00:29	  2015-8-13 14:12:37
	 ****************************************************************************/
	@RequestMapping("/teaching/assignment/newAssignment")
	public ModelAndView newAssignment(){
		//新建ModelAndView对象；
		ModelAndView mav=new ModelAndView();
		// 登陆用户
		mav.addObject("user", shareService.getUserDetail());
		//页面表单对象
		TAssignment  tAssignment = new TAssignment();
		TAssignmentControl  tAssignmentControl = new TAssignmentControl();
		//初始化默认时间
		Calendar duedate = Calendar.getInstance();
		/*tAssignmentControl.setStartdate(duedate);
		tAssignmentControl.setDuedate(duedate);*/
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String nowDate =sdf.format(duedate.getTime());
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
		
		mav.setViewName("teaching/assignment/newAssignment.jsp");
		return mav;
	}
	
	/****************************************************************************
	 * 功能：学生提交作业
	 * 作者：黄崔俊	
	 * 时间：2015-8-13 15:24:24
	 ****************************************************************************/
	@RequestMapping("/teaching/assignment/newAssignmentGrade")
	public ModelAndView newAssignmentGrade(Integer moduleType,@RequestParam Integer tCourseSiteId,int assignId, int flag,Integer selectId,Integer skillId){
		//新建ModelAndView对象；
		ModelAndView mav=new ModelAndView();
		//全部:-1  视频:1  图片:2  参考文件:3  作业:4  测试:5 考试:6
		// 当前课程中心
		mav.addObject("skillId", skillId);
	    mav.addObject("selected_courseSite", tCourseSiteId);
		mav.addObject("selectId", selectId);
		TAssignment tAssignment = tAssignmentService.findTAssignmentById(assignId);
		Set<TAssignmentGrading> tAssignmentGradings = tAssignment.getTAssignmentGradings();
		User student = shareService.getUserDetail();//查询当前登陆账户
		Set<TAssignmentGrading> tAssignmentGradingsTemp = new HashSet<TAssignmentGrading>();
		TAssignmentGrading tAssignmentGrading = null;
		String isGraded = null;
		for (TAssignmentGrading assignmentGrading : tAssignmentGradings) {
			if (!student.getUsername().equals(assignmentGrading.getUserByStudent().getUsername())) {
				tAssignmentGradingsTemp.add(assignmentGrading);//移除非当前登陆学生所提交的作业
			}else if (assignmentGrading.getSubmitTime()==0) {//查询学生所保存的作业
				tAssignmentGrading = assignmentGrading;
				tAssignmentGradingsTemp.add(assignmentGrading);//移除保存但未提交的作业
			}else if (assignmentGrading.getFinalScore()!=null) {
				isGraded = "isGraded";//已被批改
			}
		}
		tAssignmentGradings.removeAll(tAssignmentGradingsTemp);//当前统计登录人的历史提交记录
		mav.addObject("tAssignment", tAssignment);

		if (tAssignmentGrading == null) {
			tAssignmentGrading = new TAssignmentGrading();
			tAssignmentGrading.setSubmitTime(0);
			tAssignmentGrading.setSubmitdate(Calendar.getInstance());
			tAssignmentGrading.setUserByStudent(student);
			tAssignmentGrading.setTAssignment(tAssignment);
		}else {
			tAssignmentGrading.setSubmitdate(Calendar.getInstance());//将提交的时间更改为当前时间
		}
		mav.addObject("isGraded", isGraded);
		mav.addObject("tAssignmentGrade", tAssignmentGrading);
		mav.addObject("moduleType", moduleType);
		
		User user = shareService.getUserDetail();//查看当前登录用户
		List<TAssignmentGrading> list = tAssignmentGradingService.findTAssignmentGradingList(assignId, flag, user);
		
		
		mav.addObject("tAssignmentGradings", list);
		mav.addObject("flag", flag);
		mav.addObject("assignId",assignId);
		// 选择的课程中心
		TCourseSite tCourseSite = tCourseSiteService.findCourseSiteById(tCourseSiteId);
		mav.addObject("tCourseSite", tCourseSite);
		mav.setViewName("teaching/assignment/editAssignmentGrade.jsp");
		return mav;
	}
	
	/****************************************************************************
	 * 功能：学生提交作业
	 * 作者：黄崔俊	
	 * 时间：2015-8-13 16:04:23
	 ****************************************************************************/
	@RequestMapping("/teaching/assignment/saveTAssignmentGrade")
	public ModelAndView saveTAssignmentGrade(@ModelAttribute TAssignmentGrading tAssignmentGrade,
			Integer moduleType,@ModelAttribute("selected_courseSite")Integer cid,HttpServletRequest request,Integer selectId,Integer skillId,Integer tCourseSiteId,Integer assignId,Integer flag){
		ModelAndView mav = new ModelAndView();
		TAssignmentGrading tAssignmentGrading = tAssignmentGradingService.saveTAssignmentGrading(tAssignmentGrade,cid,request);
		if (tAssignmentGrade.getSubmitTime()==0) {//保存后回到详情页面
			
			User nowUser = shareService.getUser();//获取当前登录人
			TAssignment tAssignment = tAssignmentGrading.getTAssignment();
			Set<TAssignmentGrading> tAssignmentGradings = tAssignment.getTAssignmentGradings();
			Set<TAssignmentGrading> tAssignmentGradingsTemp = new HashSet<TAssignmentGrading>();
			
			for (TAssignmentGrading assignmentGrading : tAssignmentGradings) {
				if (assignmentGrading.getSubmitTime()==0||!nowUser.getUsername().equals(assignmentGrading.getUserByStudent().getUsername())) {//移除学生所保存的作业（保存但未提交）和非当前登陆人的作业
					tAssignmentGradingsTemp.add(assignmentGrading);
				}
			}
			
			tAssignmentGradings.removeAll(tAssignmentGradingsTemp);
			mav.addObject("tAssignment", tAssignment);
			mav.addObject("tAssignmentGrade", tAssignmentGrading);
			mav.addObject("tip", "作业已保存，请及时提交以便修改！");
			mav.addObject("moduleType", moduleType);
			mav.addObject("selectId", selectId);
			mav.setViewName("teaching/assignment/editAssignmentGrade.jsp");
		}else {//提交后返回课程详情页面
			//判断skillId是否为空(知识的作业，skillId为空)，为空的话返回详情页面
			if(skillId == null) {
				mav.setViewName("redirect:/teaching/assignment/newAssignmentGrade?moduleType="+moduleType+"&tCourseSiteId="+tCourseSiteId+"&assignId="+assignId+"&flag="+flag+"&selectId="+selectId);
			}else {
				mav.setViewName("redirect:/tcoursesite/skill/workExperimentSkill?tCourseSiteId="+cid+"&moduleType="+moduleType +"&selectId="+selectId +"&skillId="+skillId);
			}
		}
		return mav;
	}
	
	/****************************************************************************
	 * 功能：学生保存作业
	 * 作者：黄崔俊	
	 * 时间：2015-8-13 16:04:23
	 ****************************************************************************//*
	@RequestMapping("/teaching/assignment/keepTAssignmentGrade")
	public @ResponseBody TAssignmentGrading keepTAssignmentGrade(@ModelAttribute TAssignmentGrading tAssignmentGrade){
		TAssignmentGrading newTAssignmentGrading = tAssignmentGradingService.saveTAssignmentGrading(tAssignmentGrade);
		return newTAssignmentGrading;
	}*/
	
	/****************************************************************************
	 * 功能：老师批改作业
	 * 作者：黄崔俊	
	 * 时间：2015-8-17 09:28:46
	 ****************************************************************************/
	@RequestMapping("/teaching/assignment/saveTAssignmentGradeByTeacher")
	public String saveTAssignmentGradeByTeacher(@ModelAttribute TAssignmentGrading tAssignmentGrade,@ModelAttribute("selected_courseSite") Integer cid){
		
		tAssignmentGrade = tAssignmentGradingService.updateTAssignmentGrading(tAssignmentGrade);
		//根据作业查询成绩是否进成绩册
		tGradebookService.saveGradebook(cid,tAssignmentGrade.getTAssignment().getId(),tAssignmentGrade);
		return "redirect:/teaching/assignment/assignmentGradingList?assignmentId="+tAssignmentGrade.getTAssignment().getId()+"&flag=1";
	}
	
	/****************************************************************************
	 * 功能：进入作业详情页面以便打分
	 * 作者：黄崔俊	
	 * 时间：2015-8-13 16:04:23
	 ****************************************************************************/
	@RequestMapping("/teaching/assignment/findTAssignmentGradeToMark")
	public ModelAndView findTAssignmentGradeToMark(@RequestParam Integer assignGradeId, int flag){
		ModelAndView mav = new ModelAndView();
		User nowUser = shareService.getUserDetail();
		TAssignmentGrading tAssignmentGrading = tAssignmentGradingService.findTAssignmentGradingById(assignGradeId);
		tAssignmentGrading.setUserByGradeBy(nowUser);
		tAssignmentGrading.setGradeTime(Calendar.getInstance());
		mav.addObject("tAssignmentGrade",tAssignmentGrading);
		mav.addObject("tAssignment", tAssignmentGrading.getTAssignment());
		mav.addObject("flag", flag);
		mav.addObject("user", nowUser);
		mav.setViewName("teaching/assignment/editAssignmentGradeForTeacher.jsp");
		return mav;
	}
	
	/****************************************************************************
	 * 功能：查看作业详情
	 * 作者：黄崔俊	
	 * 时间：2015-8-13 16:04:23
	 ****************************************************************************/
	@RequestMapping("/teaching/assignment/showAssignmentGrade")
	public ModelAndView showAssignmentGrade(@RequestParam Integer assignGradeId,Integer flag){
		ModelAndView mav = new ModelAndView();
		TAssignmentGrading tAssignmentGrading = tAssignmentGradingService.findTAssignmentGradingById(assignGradeId);
		tAssignmentGrading.setGradeTime(Calendar.getInstance());
		mav.addObject("tAssignmentGrade",tAssignmentGrading);
		mav.addObject("tAssignment", tAssignmentGrading.getTAssignment());
		mav.addObject("user",shareService.getUserDetail());
		mav.addObject("flag", flag);
		mav.setViewName("teaching/assignment/showAssignmentGrade.jsp");
		return mav;
	}
	
	/****************************************************************************
	 * 功能：新建作业
	 * 作者：魏诚
	 * 时间：2015-8-6 10:00:29
	 ****************************************************************************/
	@RequestMapping("/teaching/assignment/newAssignmentAssign")
	public ModelAndView newAssignmentAssign(HttpSession httpSession,@RequestParam int flagId,int assignId){
		//新建ModelAndView对象；
		ModelAndView mav=new ModelAndView();
		// 登陆用户
		mav.addObject("user", shareService.getUserDetail());
		// id为-1时，新增，否则为修改
		mav.addObject("flagId", flagId);
		//页面表单对象
		TAssignment  tAssignment = tAssignmentDAO.findTAssignmentById(assignId);
		//TAssignmentAnswerAssign  tAssignmentAnswerAssign = new TAssignmentAnswerAssign();
		//初始化默认时间
		//Calendar duedate = Calendar.getInstance();
		//tAssignmentAnswerAssign.setScoreDate(duedate);
		tAssignment.setUser(shareService.getUserDetail());
		//tAssignment.setTAssignmentAnswerAssign(tAssignmentAnswerAssign);
		mav.addObject("tAssignment", tAssignment);
		
		//课程信息
		TCourseSite tCourseSite = new TCourseSite();
		if (flagId != -1) {
			tCourseSite = tCourseSiteDAO.findTCourseSiteByPrimaryKey(flagId);
		}
		mav.addObject("tCourseSite", tCourseSite);
		
		mav.setViewName("teaching/assignment/editAssignmentGrade.jsp");
		return mav;
	}
	
	/****************************************************************************
	 * 功能：教师布置作业
	 * 作者：黄崔俊
	 * 时间：2015-8-12 15:44:11
	 * @throws ParseException 
	 ****************************************************************************/
	@RequestMapping("/teaching/assignment/saveAssignment")
	public String saveAssignment(@ModelAttribute TAssignment tAssignment,Integer tCourseSiteId,HttpServletRequest request,Integer moduleType,Integer selectId) throws ParseException{
		
		tAssignment = tAssignmentService.saveTAssignment(tAssignment,request);
		return "redirect:/teaching/assignment/assignmentGradingList?tCourseSiteId="+tCourseSiteId+"&assignmentId="+tAssignment.getId()+"&flag=1&moduleType="+moduleType+"&selectId="+selectId;
	}
	/****************************************************************************
	 * 功能：教师修改作业
	 * 作者：黄崔俊
	 * 时间：2015-8-12 16:56:15
	 ****************************************************************************/
	@RequestMapping("/teaching/assignment/updateAssignmentById")
	public ModelAndView updateAssignmentById(@RequestParam Integer tCourseSiteId,int assignId,Integer moduleType,Integer selectId){
		//新建ModelAndView对象；
		ModelAndView mav=new ModelAndView();
		//全部:-1  视频:1  图片:2  参考文件:3  作业:4  测试:5 考试:6
		mav.addObject("selectId", selectId);
		mav.addObject("moduleType", moduleType);
		//获取所选的站点
		TCourseSite tCourseSite = tCourseSiteDAO.findTCourseSiteByPrimaryKey(tCourseSiteId);
		mav.addObject("tCourseSite", tCourseSite);
		TAssignment  tAssignment = tAssignmentService.findTAssignmentById(assignId); 
		mav.addObject("tAssignment", tAssignment);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		mav.addObject("startdate",sdf.format(tAssignment.getTAssignmentControl().getStartdate().getTime()));
		mav.addObject("duedate",sdf.format(tAssignment.getTAssignmentControl().getDuedate().getTime()));
		mav.setViewName("teaching/assignment/newAssignment.jsp");
		return mav;
	}
	/****************************************************************************
	 * 功能：教师发布作业
	 * 作者：黄崔俊
	 * 时间：2015-8-19 15:27:03
	 ****************************************************************************/
	@RequestMapping("/teaching/assignment/changeAsignmentStatusById")
	public String changeAsignmentStatusById(@RequestParam int assignId){
		TAssignment tAssignment = tAssignmentService.findTAssignmentById(assignId);
		tAssignmentService.changeAsignmentStatus(tAssignment);
		return "redirect:/teaching/assignment/assignmentList?flag=1";
	}
	/****************************************************************************
	 * 功能：教师删除作业
	 * 作者：黄崔俊
	 * 时间：2015-8-13 09:41:39
	 ****************************************************************************/
	@RequestMapping("/teaching/assignment/deleteAssignmentById")
	public String deleteAssignmentById(@RequestParam int assignId){
		TAssignment tAssignment = tAssignmentService.findTAssignmentById(assignId);
		tAssignmentService.deleteTAssignment(tAssignment);
		return "redirect:/teaching/assignment/assignmentList?flag=1";
	}
	
	/****************************************************************************
	 * 功能：教师下载学生的作业
	 * 作者：黄崔俊
	 * 时间：2015-8-13 09:41:39
	 ****************************************************************************/
	@RequestMapping("/teaching/assignment/downloadAssignment")
	public String downloadAssignment(@RequestParam Integer assignId,Integer tCourseSiteId,HttpServletRequest request,
			HttpServletResponse response){
		String result = tAssignmentGradingService.downloadAssignment(request, response, tCourseSiteId, assignId);
		if (result == null) {
			return "redirect:/teaching/assignment/assignmentGradingList?tCourseSiteId="+tCourseSiteId+"&assignmentId="+assignId+"&flag=1";
		}
		return null;
	}
	/****************************************************************************
	 * 功能：查看本课程下所有学生的成绩
	 * 作者：黄崔俊
	 * 时间：2015-8-13 09:41:39
	 ****************************************************************************/
	@RequestMapping("/teaching/assignment/searchAllGrade")
	public ModelAndView searchAllGrade(@ModelAttribute("selected_courseSite")Integer cid){
		ModelAndView mav = new ModelAndView();
		//List<User> users = tAssignmentGradingService.findUsersByTCourseSiteId(cid);
		User nowUser = shareService.getUserDetail();
		//查询本课程作业暂时只对老师开放，故flag写死为1
		//List<ViewTAssignment> tAssignments = tAssignmentService.findViewTAssignmentList(nowUser, cid, 1);
		List<ViewTAssignmentAllGrade> viewTAssignmentAllGrades = tAssignmentGradingService.searchAllGrade(nowUser,cid);
		
		mav.addObject("viewTAssignmentAllGrades", viewTAssignmentAllGrades);
		mav.setViewName("/teaching/assignment/assignmentAllGrade.jsp");
		return mav;
	}
	/****************************************************************************
	 * 功能：下载一个学生的附件
	 * 作者：裴继超
	 * 时间：2015-9-15
	 ****************************************************************************/
	@RequestMapping("/teaching/assignment/downloadFile")                                                     
	public String downloadFile(HttpServletRequest request,HttpServletResponse response,@RequestParam Integer id,@ModelAttribute("selected_courseSite")Integer cid,@RequestParam int tCourseSiteId,Integer moduleType,Integer selectId) throws Exception {
		//id对应的文档
		TAssignmentGrading tAssignmentGrade=tAssignmentGradingDAO.findTAssignmentGradingByPrimaryKey(id);
		
		String result=tAssignmentGradingService.downloadFile(tAssignmentGrade, request, response,cid);
		if (result == null) {
			System.out.println("teaching/assignment/assignmentGradingList?assignmentId="+tAssignmentGrade.getTAssignment().getId()+"&flag=1&tCourseSiteId="+tCourseSiteId+"&moduleType="+moduleType+"&selectId="+selectId);
			return "forward:/teaching/assignment/assignmentGradingList?assignmentId="+tAssignmentGrade.getTAssignment().getId()+"&flag=1&tCourseSiteId="+tCourseSiteId+"&moduleType="+moduleType+"&selectId="+selectId;
		}
		return null;
		
	}
	/****************************************************************************
	 * 功能：下载一个学生的附件
	 * 作者：裴继超
	 * 时间：2015-9-28
	 ****************************************************************************/
	@RequestMapping("/teaching/assignment/downloadFileForStudent")                                                     
	public String downloadFileForStudent(HttpServletRequest request,HttpServletResponse response,@RequestParam Integer id,@ModelAttribute("selected_courseSite")Integer cid,@RequestParam int tCourseSiteId,Integer moduleType,Integer selectId) throws Exception {
		//id对应的文档
		TAssignmentGrading tAssignmentGrade=tAssignmentGradingDAO.findTAssignmentGradingByPrimaryKey(id);
		
		String result=tAssignmentGradingService.downloadFileForStudent(tAssignmentGrade, request, response,cid);
		if (result == null) {
			return "redirect:/teaching/assignment/assignmentGradingList?assignmentId="+tAssignmentGrade.getTAssignment().getId()+"&flag=1&tCourseSiteId="+tCourseSiteId+"&moduleType="+moduleType+"&selectId="+selectId;
		}
		return null;
		
	}
	/****************************************************************************
	 * 功能：学生查看作业
	 * 作者：裴继超
	 * 时间：2015-9-25
	 ****************************************************************************/
	@RequestMapping("/teaching/assignment/lookAssignmentGrade")
	public ModelAndView lookAssignmentGrade(@ModelAttribute("selected_courseSite")Integer cid,@RequestParam int assignId, int flag,Integer moduleType,Integer selectId){
		//新建ModelAndView对象；
		ModelAndView mav=new ModelAndView();
		//全部:-1  视频:1  图片:2  参考文件:3  作业:4  测试:5 考试:6
		mav.addObject("selectId", selectId);
		mav.addObject("moduleType", moduleType);
		// 选择的课程中心
		TCourseSite tCourseSite = tCourseSiteService.findCourseSiteById(cid);
		mav.addObject("tCourseSite", tCourseSite);
		TAssignment tAssignment = tAssignmentService.findTAssignmentById(assignId);
		Set<TAssignmentGrading> tAssignmentGradings = tAssignment.getTAssignmentGradings();
		User student = shareService.getUserDetail();//查询当前登陆账户
		Set<TAssignmentGrading> tAssignmentGradingsTemp = new HashSet<TAssignmentGrading>();
		TAssignmentGrading tAssignmentGrading = null;
		String isGraded = null;
		for (TAssignmentGrading assignmentGrading : tAssignmentGradings) {
			if (!student.getUsername().equals(assignmentGrading.getUserByStudent().getUsername())) {
				tAssignmentGradingsTemp.add(assignmentGrading);//移除非当前登陆学生所提交的作业
			}else if (assignmentGrading.getSubmitTime()==0) {//查询学生所保存的作业
				tAssignmentGrading = assignmentGrading;
				tAssignmentGradingsTemp.add(assignmentGrading);//移除保存但未提交的作业
			}else if (assignmentGrading.getFinalScore()!=null) {
				isGraded = "isGraded";//已被批改
			}
		}
		tAssignmentGradings.removeAll(tAssignmentGradingsTemp);//当前统计登录人的历史提交记录
		mav.addObject("tAssignment", tAssignment);

		if (tAssignmentGrading == null) {
			tAssignmentGrading = new TAssignmentGrading();
			tAssignmentGrading.setSubmitTime(0);
			tAssignmentGrading.setSubmitdate(Calendar.getInstance());
			tAssignmentGrading.setUserByStudent(student);
			tAssignmentGrading.setTAssignment(tAssignment);
		}else {
			tAssignmentGrading.setSubmitdate(Calendar.getInstance());//将提交的时间更改为当前时间
		}
		mav.addObject("isGraded", isGraded);
		mav.addObject("tAssignmentGrade", tAssignmentGrading);
		
		User user = shareService.getUserDetail();//查看当前登录用户
		List<TAssignmentGrading> list = tAssignmentGradingService.findTAssignmentGradingList(assignId, flag, user);
		
		mav.addObject("tAssignmentGradings", list);
		mav.addObject("user", user);
		mav.addObject("flag", flag);
		mav.setViewName("teaching/assignment/lookAssignmentGrade.jsp");
		return mav;
	}
	
	/****************************************************************************
	 * 功能：上移课时
	 * 作者：裴继超
	 * 时间：2015.09.17
	 ****************************************************************************/
	@RequestMapping("/teaching/assignment/moveup")
	public ModelAndView moveUp(@RequestParam Integer id) {
		ModelAndView mav = new ModelAndView();
		//获取作业信息
		TAssignment tAssignment = tAssignmentDAO.findTAssignmentById(id);
		User user = shareService.getUser();
		
		//从位置上查找在本课程下的上一条作业
		TAssignment preTAssignment = tAssignmentService.queryminByTAssignment(tAssignment,user);
		if (preTAssignment != null) {//如果有上一条记录，则进行交换
			Integer tempSequence = tAssignment.getSequence();
			tAssignment.setSequence(preTAssignment.getSequence());
			tAssignmentService.saveTAssignment(tAssignment);
			
			preTAssignment.setSequence(tempSequence);
			tAssignmentService.saveTAssignment(tAssignment);
		}
	
		Integer flag = 2;
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
		if (user.getAuthorities().toString().contains("TEACHER")) {
			flag = 1;//老师
		}
		if (user.getAuthorities().toString().contains("STUDENT")){
			flag = 0;//学生
		}
		
		mav.addObject("flag", flag);
		mav.setViewName("redirect:/teaching/assignment/assignmentList");
		return mav;

	}
	/****************************************************************************
	 * 功能：下移作业
	 * 作者：黄崔俊
	 * 时间：2015-10-19 16:00:44
	 ****************************************************************************/
	@RequestMapping("/teaching/assignment/movedown")
	public ModelAndView moveDown(@RequestParam Integer id) {
		ModelAndView mav = new ModelAndView();
		//获取作业信息
		TAssignment tAssignment = tAssignmentDAO.findTAssignmentById(id);
		User user = shareService.getUser();
		
		//从位置上查找在本课程下的下一条作业
		TAssignment nextTAssignment = tAssignmentService.querybigSeqByTAssignment(tAssignment,user);
		if (nextTAssignment != null) {//如果有下一条记录，则进行交换
			Integer tempSequence = tAssignment.getSequence();
			tAssignment.setSequence(nextTAssignment.getSequence());
			tAssignmentService.saveTAssignment(tAssignment);
			
			nextTAssignment.setSequence(tempSequence);
			tAssignmentService.saveTAssignment(tAssignment);
		}
		
		Integer flag = 2;
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
		if (user.getAuthorities().toString().contains("TEACHER")) {
			flag = 1;//老师
		}
		if (user.getAuthorities().toString().contains("STUDENT")){
			flag = 0;//学生
		}
		
		mav.addObject("flag", flag);
		mav.setViewName("redirect:/teaching/assignment/assignmentList");
		return mav;
	}
	
	/****************************************************************************
	 * 功能：教师“驳回”学生提交的作业
	 * 作者：张佳鸣
	 * 时间：2017-11-28
	 ****************************************************************************/
	@RequestMapping("/teaching/assignmentGrading/rejectGrade")
	public @ResponseBody String rejectGrade(@RequestParam Integer assignGradeId,@ModelAttribute("selected_courseSite")Integer cid){
		//确定当前登入人和当前时间
		User nowUser = shareService.getUserDetail();
		Calendar calendar = Calendar.getInstance();
		//更新学生提交的实验作业数据
		tAssignmentGradingService.updateTAssignmentGrading(assignGradeId,nowUser,calendar);
		return null;
	}
	
}