/****************************************************************************
 * 命名规范：
 * 本控制层调整必须是按照如下的路径调整：@RequestMapping("/tCourseSite/xxxx")。
 * 列表信息listxxxx，獲取信息：getxxxx 编辑信息：editxxxx 删除信息：detelexxxx 新增信息 newxxxx
 * 导出信息exportxxxx 保存信息：savexxxx 
 ****************************************************************************/

package net.xidlims.web.tcoursesite;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.xidlims.constant.CommonConstantInterface;
import net.xidlims.dao.TAssignmentAnswerAssignDAO;
import net.xidlims.dao.TAssignmentDAO;
import net.xidlims.dao.TAssignmentGradingDAO;
import net.xidlims.dao.TCourseSiteUserDAO;
import net.xidlims.domain.Authority;
import net.xidlims.domain.SchoolAcademy;
import net.xidlims.domain.SchoolClasses;
import net.xidlims.domain.SchoolMajor;
import net.xidlims.domain.SchoolTerm;
import net.xidlims.domain.TAssignment;
import net.xidlims.domain.TAssignmentAnswerAssign;
import net.xidlims.domain.TAssignmentControl;
import net.xidlims.domain.TAssignmentGrading;
import net.xidlims.domain.TAssignmentQuestionpool;
import net.xidlims.domain.TCourseSite;
import net.xidlims.domain.TCourseSiteGroup;
import net.xidlims.domain.TCourseSiteUser;
import net.xidlims.domain.TGradeObject;
import net.xidlims.domain.TGradeRecord;
import net.xidlims.domain.TGradebook;
import net.xidlims.domain.TMessage;
import net.xidlims.domain.User;
import net.xidlims.domain.WkChapter;
import net.xidlims.domain.WkFolder;
import net.xidlims.domain.WkLesson;
import net.xidlims.domain.WkUpload;
import net.xidlims.service.common.ShareService;
import net.xidlims.service.lab.LabCenterService;
import net.xidlims.service.system.SystemService;
import net.xidlims.service.system.UserDetailService;
import net.xidlims.service.tcoursesite.TAssignmentQuestionPoolService;
import net.xidlims.service.tcoursesite.TAssignmentService;
import net.xidlims.service.tcoursesite.TCourseSiteService;
import net.xidlims.service.tcoursesite.TCourseSiteUserService;
import net.xidlims.service.tcoursesite.TGradebookService;
import net.xidlims.service.tcoursesite.WkChapterService;
import net.xidlims.service.tcoursesite.WkFolderService;
import net.xidlims.service.tcoursesite.WkLessonService;
import net.xidlims.service.tcoursesite.WkService;
import net.xidlims.service.tcoursesite.WkUploadService;
import net.xidlims.service.teaching.TAssignmentForTestService;
import net.xidlims.service.teaching.TAssignmentGradingService;
import net.xidlims.service.timetable.OuterApplicationService;
import net.xidlims.service.timetable.SchoolCourseInfoService;
import net.xidlims.view.ViewTAssignment;
import net.xidlims.web.aop.SystemServiceLog;
import net.xidlims.service.tcoursesite.TMessageService;
import net.xidlims.service.tcoursesite.TCourseSiteAttendenceService;

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
/**************************************************************************
 * Description:系统后台管理模块
 * 
 * @author：魏诚
 * @date ：2014-07-14
 **************************************************************************/
@Controller("TCourseSiteAttendenceController")
public class TCourseSiteAttendenceController<JsonResult> {
	@InitBinder
	public void initBinder(WebDataBinder binder, HttpServletRequest request) { // Register
																				// static
																				// property
																				// editors.
		binder.registerCustomEditor(java.util.Calendar.class,
				new org.skyway.spring.util.databinding.CustomCalendarEditor());
		binder.registerCustomEditor(byte[].class,
				new org.springframework.web.multipart.support.ByteArrayMultipartFileEditor());
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
	private TCourseSiteAttendenceService tCourseSiteAttendenceService;
	@Autowired
	private TCourseSiteService tCourseSiteService;
	@Autowired
	private WkChapterService wkChapterService;
	@Autowired
	private WkLessonService wkLessonService;
	@Autowired
	private WkService wkService;
	@Autowired
	private WkUploadService wkUploadService;
	@Autowired
	private WkFolderService wkFolderService;
	@Autowired
	private TAssignmentQuestionPoolService tAssignmentQuestionPoolService;
	@Autowired
	private TAssignmentService tAssignmentService;
	@Autowired
	private TAssignmentForTestService tAssignmentForTestService;
	@Autowired
	private TAssignmentGradingService tAssignmentGradingService;
	@Autowired
	private SchoolCourseInfoService schoolCourseInfoService;
	@Autowired
	private OuterApplicationService outerApplicationService;
	@Autowired
	private UserDetailService userDetailService;
	@Autowired
	private TCourseSiteUserService tCourseSiteUserService;
	@Autowired
	private 	SystemService 	systemService;
	@Autowired 
	private TMessageService tMessageService;
	@Autowired 
	private TGradebookService tGradebookService;
	@Autowired
	private TAssignmentGradingDAO tAssignmentGradingDAO;
	@Autowired
	private TAssignmentAnswerAssignDAO tAssignmentAnswerAssignDAO;
	@Autowired
	private TAssignmentDAO tAssignmentDAO;
	/*
	 * @RequestMapping("/test") public ModelAndView test(){ ModelAndView mav=new
	 * ModelAndView(); //当前登录人 User user=shareService.getUser();
	 * mav.addObject("user",user); //所属学院 SchoolAcademy
	 * academy=user.getSchoolAcademy(); //所属学院下的中心 Set<LabCenter> centers=new
	 * HashSet<LabCenter>(); if(academy!=null){ centers=academy.getLabCenters();
	 * }else{ centers=labCenterService.findAllLabCenter(); }
	 * mav.addObject("centers", centers); mav.setViewName("system/test.jsp");
	 * return mav; }
	 */
	/**************************************************************************
	 * Description:教师保存考勤
	 *  
	 * @author：李军凯
	 * @date ：2016-10-17
	 **************************************************************************/
	@SystemServiceLog("保存考勤")
	@ResponseBody
	@RequestMapping("/tcoursesite/saveAttendence")
	public ModelAndView saveAttendence(@ModelAttribute TAssignment tAssignment,
			@RequestParam Integer tCourseSiteId,Integer moduleType ,HttpServletRequest request,Integer selectId) throws ParseException{
		ModelAndView mav = new ModelAndView();
		tAssignment = tAssignmentService.saveAttendence(tAssignment,request);
		//根据作业创建成绩册
		tGradebookService.createGradebook(tCourseSiteId, tAssignment);
		mav.setViewName("redirect:/tcoursesite/chaptersList?tCourseSiteId="+tCourseSiteId+"&moduleType="+moduleType+"&selectId="+selectId);
		return mav;
	}
	/**************************************************************************
	 * Description:教师修改考勤
	 *  
	 * @author：李军凯
	 * @date ：2016-10-17
	 **************************************************************************/
	@SystemServiceLog("编辑考勤")
	@RequestMapping("/tcoursesite/editAttendence")
	@ResponseBody
	public Map<String, Object> editAttendence(@RequestParam Integer folderId) {
		WkFolder wkFolder = wkFolderService.findWkFolderByPrimaryKey(folderId);
		//TAssignment tAssignment = tAssignmentService.findTAssignmentById(attendenceId);
		Map<String, Object> map = new HashMap<String, Object>();
		if (wkFolder.getTAssignments().size()!=0) {// 假如有
			TAssignment tAssignment = wkFolder.getTAssignments().iterator().next();
			map.put("tAttendenceTitle", (tAssignment.getTitle()==null)?"":tAssignment.getTitle());
			map.put("tAttendenceId", (tAssignment.getId()==null)?"":tAssignment.getId());//
			map.put("tAttendenceAnswerAssignId", (tAssignment.getTAssignmentAnswerAssign()==null)?"":tAssignment.getTAssignmentAnswerAssign().getId());//
			map.put("tAttendenceControlId", (tAssignment.getTAssignmentControl().getId()==null)?"":tAssignment.getTAssignmentControl().getId());//
			map.put("tAttendenceWkChapterId", (tAssignment.getWkFolder().getWkChapter()==null)?"-1":tAssignment.getWkFolder().getWkChapter().getId());//
			map.put("tAttendenceWkLessonId", (tAssignment.getWkFolder().getWkLesson()==null)?"-1":tAssignment.getWkFolder().getWkLesson().getId());//
			//map.put("TAssignmentControl_startdate", (tAssignment.getTAssignmentControl().getStartdate()==null)?"":df.format(tAssignment.getTAssignmentControl().getStartdate().getTime()));//
			//map.put("TAssignmentControl_duedate", (tAssignment.getTAssignmentControl().getDuedate()==null)?"":df.format(tAssignment.getTAssignmentControl().getDuedate().getTime()));//
			map.put("tAttendenceUsername", (tAssignment.getTAssignmentAnswerAssign().getUser().getUsername()==null)?"":tAssignment.getTAssignmentAnswerAssign().getUser().getUsername());//
			//map.put("assignmentContent", (tAssignment.getContent()==null)?"":tAssignment.getContent());//
			//map.put("assignmentStatus", (tAssignment.getStatus()==null)?"":tAssignment.getStatus());//
			//map.put("assignmentCreatedTime", (tAssignment.getCreatedTime()==null)?"":df.format(tAssignment.getCreatedTime().getTime()));//
			map.put("tAttendenceWkFolderId", (tAssignment.getWkFolder()==null)?"":tAssignment.getWkFolder().getId());//
			map.put("tAttendenceuserusername", (tAssignment.getUser().getUsername()==null)?"":tAssignment.getUser().getUsername());//
			//map.put("timelimitselect", (tAssignment.getTAssignmentControl().getTimelimit()==null)?"0":tAssignment.getTAssignmentControl().getTimelimit());//
			//map.put("TAssignmentControlSubmitType", (tAssignment.getTAssignmentControl().getSubmitType()==null)?"1":tAssignment.getTAssignmentControl().getSubmitType());//
			map.put("tAttendenceControlToGradebook", (tAssignment.getTAssignmentControl().getToGradebook()==null)?"yes":tAssignment.getTAssignmentControl().getToGradebook());//
			map.put("tAttendenceControlGradeToStudent", (tAssignment.getTAssignmentControl().getGradeToStudent()==null)?"yes":tAssignment.getTAssignmentControl().getGradeToStudent());//
			map.put("tAttendenceControlGradeToTotalGrade", (tAssignment.getTAssignmentControl().getGradeToTotalGrade()==null)?"yes":tAssignment.getTAssignmentControl().getGradeToTotalGrade());//
			map.put("tAttendenceScore", (tAssignment.getTAssignmentAnswerAssign()==null)?"":tAssignment.getTAssignmentAnswerAssign().getScore());//tAttendenceScore	
		}
		return map;
	}
	/**************************************************************************
	 * Description:考勤-查看考勤
	 *  
	 * @author：李军凯
	 * @date ：2016-10-18
	 **************************************************************************/
	@SystemServiceLog("查看考勤")
	@ResponseBody
	@RequestMapping("/tcoursesite/attendence")
	public ModelAndView attendence(HttpSession httpSession,@RequestParam Integer tCourseSiteId,Integer attendenceId) {
		ModelAndView mav = new ModelAndView();
		// 选择的课程中心
		TCourseSite tCourseSite = tCourseSiteService.findCourseSiteById(tCourseSiteId);
		mav.addObject("tCourseSite", tCourseSite);
		//当前登录人
		User user = shareService.getUser();
		mav.addObject("user", user);
		//角色判断：如果具有老师权限且为该课程的创建者或者助教则默认为老师，如果没有教师权限则默认为学生
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
		}else if(user!=null&&user.getAuthorities().toString().contains("TEACHER")){
			if (tCourseSite.getUserByCreatedBy().getUsername().equals(user.getUsername())) {
				flag = 1;//如果当前登陆人是本人开的课程，则是老师身份
			}else if (tCourseSiteUserService.isSTeacherBySiteId(tCourseSiteId,user.getUsername())){
				flag = 1;//如果当前登陆人是本课程的助教，则是老师身份
			}else if (tCourseSiteUserService.isUserBelongToTeacher(tCourseSiteId)){
				flag=1; //如果当前登陆人参与这门课，则是老师身份
			}else {
				flag = 0;//如果当前登陆人不是本人开的课程，则是学生身份
			}
			
		}else if(user!=null&&user.getAuthorities().toString().contains("STUDENT")){
			flag = 0;
		}
		mav.addObject("flag", flag);
	
		//获取考勤列表
		List<TAssignment> tAssignments = tCourseSiteAttendenceService.findAttendenceBytCourseSiteId(tCourseSiteId);
		mav.addObject("tAssignments",tAssignments);
		
		
		//当前考勤的ID
		int curAttendenceId = attendenceId;
		if(curAttendenceId == -1){			
			if(tAssignments.size()>0){
			TAssignment t=tAssignments.get(0);
			curAttendenceId = t.getId();}			
		}
		mav.addObject("curAttendenceId",curAttendenceId);
		if(curAttendenceId != -1){
		//获取考勤列表		
		List<Object[]> attendenceList = tCourseSiteAttendenceService.findAttendenceListByAttendenceId(curAttendenceId);
		mav.addObject("attendenceList",attendenceList);		
		//扣分
		TAssignmentAnswerAssign tAssignmentAnswerAssign = tCourseSiteAttendenceService.findTAssignmentAnswerAssignByAssignmentId(curAttendenceId);
		mav.addObject("tAssignmentAnswerAssign",tAssignmentAnswerAssign);
		}
		mav.setViewName("tcoursesite/student/attendenceList.jsp");
		return mav;
	}
	/**************************************************************************
	 * Description:考勤-查看考勤
	 *  
	 * @author：李军凯
	 * @date ：2016-10-18
	 **************************************************************************/
	@SystemServiceLog("查看考勤")
	@ResponseBody
	@RequestMapping("/tcoursesite/findAttendence")
	public ModelAndView findAttendence(HttpSession httpSession,@RequestParam Integer tCourseSiteId,Integer folderId){
		ModelAndView mav = new ModelAndView();
		WkFolder wkFolder = wkFolderService.findWkFolderByPrimaryKey(folderId);
		Integer attendenceId = -1;
		if(wkFolder.getTAssignments().size()>0){
		TAssignment tAssignment = wkFolder.getTAssignments().iterator().next();
		attendenceId= tAssignment.getId();
		}
		mav.setViewName("redirect:/tcoursesite/attendence?tCourseSiteId="+tCourseSiteId+"&attendenceId="+attendenceId);
		return mav;		
	} 
	/**********************************************************************************
	 * Description:考勤-获取考勤列表
	 * 
	 * @author：李军凯
	 * @date ：2016-10-19
	 **********************************************************************************/
	@SystemServiceLog("获取考勤列表")
	@RequestMapping("/tcoursesite/findAttendenceById")
	@ResponseBody
	public List findAttendenceById(@RequestParam Integer id) {
		List<Object[]> attendenceList = tCourseSiteAttendenceService.findAttendenceListByAttendenceId(id);
		return attendenceList;
	}
	/**********************************************************************************
	 * Description:考勤-获取扣分
	 * 
	 * @author：李军凯
	 * @date ：2016-10-19
	 **********************************************************************************/
	@SystemServiceLog("获取扣分")
	@RequestMapping("/tcoursesite/findtAssignmentAnswerAssign")
	@ResponseBody
	public Map<String, Integer> findtAssignmentAnswerAssign(@RequestParam Integer id) {
		TAssignmentAnswerAssign tAssignmentAnswerAssign = tCourseSiteAttendenceService.findTAssignmentAnswerAssignByAssignmentId(id);
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("lateScore", tAssignmentAnswerAssign.getLateScore());
		map.put("earlyScore", tAssignmentAnswerAssign.getEarlyScore());
		map.put("truantScore", tAssignmentAnswerAssign.getTruantScore());
		map.put("leaveScore", tAssignmentAnswerAssign.getLeaveScore());
		return map;
	}
	/**************************************************************************
	 * Description:考勤-保存考勤成绩
	 *  
	 * @author：李军凯
	 * @date ：2016-10-20
	 **************************************************************************/
	@SystemServiceLog("保存考勤成绩")
	@ResponseBody
	@RequestMapping("/tcoursesite/saveAttendenceGrading")
	public ModelAndView saveAttendenceGrading(@RequestParam Integer tCourseSiteId,Integer attendenceId,HttpSession httpSession,
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		// 选择的课程中心
		//TCourseSite tCourseSite = tCourseSiteService.findCourseSiteById(tCourseSiteId);
		TAssignment tAssignment = tAssignmentDAO.findTAssignmentByPrimaryKey(attendenceId);
		tAssignment.setStatus(1);
		tAssignmentDAO.store(tAssignment);
		//当前登录人
		User user = shareService.getUser();
		//保存扣分项
		TAssignmentAnswerAssign tAssignmentAnswerAssign = tCourseSiteAttendenceService.findTAssignmentAnswerAssignByAssignmentId(attendenceId);
		tAssignmentAnswerAssign.setLateScore(Integer.parseInt(request.getParameter("lateScore")));//迟到
		tAssignmentAnswerAssign.setEarlyScore(Integer.parseInt(request.getParameter("earlyScore")));//早退
		tAssignmentAnswerAssign.setTruantScore(Integer.parseInt(request.getParameter("truantScore")));//旷课
		tAssignmentAnswerAssign.setLeaveScore(Integer.parseInt(request.getParameter("leaveScore")));//请假
		tAssignmentAnswerAssign=tAssignmentAnswerAssignDAO.store(tAssignmentAnswerAssign);
		//保存每个学生的成绩
		List<TCourseSiteUser> tCourseSiteUsers = tCourseSiteService.findCourseSiteUserBysiteId(tCourseSiteId);
		for(TCourseSiteUser t:tCourseSiteUsers){
			TAssignmentGrading tag = tCourseSiteAttendenceService.findTAssignmentGradingByTAssignmentIdAndtCourseSiteUser(attendenceId, t);
			if(tag == null){
				tag = new TAssignmentGrading();
			}
			tag.setTAssignment(tAssignment);
			tag.setUserByStudent(t.getUser());
			tag.setUserByGradeBy(user);
			tag.setGradeTime(Calendar.getInstance());
			int type = Integer.parseInt(request.getParameter(t.getUser().getUsername()));
			tag.setAttendenceType(type);
			int finalScore = tAssignmentAnswerAssign.getScore().intValue();
			if(type==1){
				finalScore = finalScore - Integer.parseInt(request.getParameter("lateScore"));				
			}			
			if(type==2){
				finalScore = finalScore - Integer.parseInt(request.getParameter("earlyScore"));
			}
			if(type==3){
				finalScore = finalScore - Integer.parseInt(request.getParameter("truantScore"));
			}
			if(type==4){
				finalScore = finalScore - Integer.parseInt(request.getParameter("leaveScore"));
			}
			tag.setFinalScore(new BigDecimal(finalScore));
			tAssignmentGradingDAO.store(tag);
			tAssignmentGradingDAO.flush();
			//根据作业查询成绩是否进成绩册
			tCourseSiteAttendenceService.saveGradebook(tCourseSiteId,tag.getTAssignment().getId(),tag);
		}
		mav.setViewName("redirect:/tcoursesite/attendence?tCourseSiteId="+tCourseSiteId+"&attendenceId="+attendenceId);
		return mav;
	}
	
}