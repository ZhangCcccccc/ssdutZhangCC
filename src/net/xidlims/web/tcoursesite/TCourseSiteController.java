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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import net.xidlims.domain.Authority;
import net.xidlims.domain.SchoolAcademy;
import net.xidlims.domain.SchoolClasses;
import net.xidlims.domain.SchoolMajor;
import net.xidlims.domain.SchoolTerm;
import net.xidlims.domain.TAssignment;
import net.xidlims.domain.TAssignmentAnswerAssign;
import net.xidlims.domain.TAssignmentControl;
import net.xidlims.domain.TAssignmentQuestionpool;
import net.xidlims.domain.TCourseSite;
import net.xidlims.domain.TCourseSiteUser;
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
import net.xidlims.service.tcoursesite.TMessageService;
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
/**************************************************************************
 * Description:系统后台管理模块
 * 
 * @author：魏诚
 * @date ：2014-07-14
 **************************************************************************/
@Controller("TCourseSiteController")
public class TCourseSiteController<JsonResult> {
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
	private LabCenterService labCenterService;
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
	 * Description:进入课程平台
	 *  
	 * @author：裴继超
	 * @date ：2016-04-29
	 **************************************************************************/
	@SystemServiceLog("进入课程平台")
	@ResponseBody
	@RequestMapping("/tcoursesite")
	public ModelAndView tCourseSite(@RequestParam Integer tCourseSiteId,HttpSession httpSession) {
		ModelAndView mav = new ModelAndView();
		
		// 选择的课程中心
		TCourseSite tCourseSite = tCourseSiteService.findCourseSiteById(tCourseSiteId);
		// 当前登录人
		User user = shareService.getUser();
		httpSession.setAttribute("currsite", tCourseSite);
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
			flag = 1;//实验中心主任   或者实验室管理员   或者超级管理员   或者教务处   或者校领导   或者教学副院长  或者教务管理员   或者实验中心管理员   为教师权限
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
		httpSession.setAttribute("currflag", flag);
		mav.setViewName( "redirect:/tcoursesite/skill/experimentSkillsList?tCourseSiteId=" + tCourseSiteId + "&moduleType=2&selectId=-1");
		return mav;
	}
		

	/**************************************************************************
	 * Description:进入章节列表 
	 *  
	 * @author：裴继超
	 * @date ：2016-04-29
	 **************************************************************************/
	@SystemServiceLog("进入章节列表")
	@ResponseBody
	@RequestMapping("/tcoursesite/chaptersList")
	public ModelAndView chaptersList(HttpServletRequest request,@RequestParam Integer tCourseSiteId,
			Integer moduleType,HttpSession httpSession,Integer selectId) {
		String jspViewFlag = request.getParameter("viewFlag");
		String viewFlag = (String) httpSession.getAttribute("viewFlag");
		if(jspViewFlag!=null){
			viewFlag = jspViewFlag;
		}
		if(viewFlag==null||viewFlag.equals("")){
			httpSession.setAttribute("viewFlag", "0");
		}else{
			httpSession.setAttribute("viewFlag", viewFlag);
		}
		viewFlag = (String) httpSession.getAttribute("viewFlag");
		ModelAndView mav = new ModelAndView();
		//全部:-1  视频:1  图片:2  参考文件:3  作业:4  测试:5 考试:6
		mav.addObject("selectId", selectId);
		mav.addObject("selected_courseSite", tCourseSiteId);
		// 当前登录人
		User user = shareService.getUser();
		mav.addObject("user", user);
		String authority = "";
		int i = 1;
		Set<Authority> auths = user.getAuthorities();
		for (Authority a : auths) {
			if (a.getType() >= i) {
				authority = a.getCname();
				i = a.getType();
			}
		}
		mav.addObject("authority", authority);
		// 选择的课程中心
		TCourseSite tCourseSite = tCourseSiteService.findCourseSiteById(tCourseSiteId);
		mav.addObject("tCourseSite", tCourseSite);
		
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
			flag = 1;//实验中心主任   或者实验室管理员   或者超级管理员   或者教务处   或者校领导   或者教学副院长  或者教务管理员   或者实验中心管理员   为教师权限
		}else if(user!=null&&user.getAuthorities().toString().contains("TEACHER")){
			if (tCourseSite.getUserByCreatedBy().getUsername().equals(user.getUsername())) {
				flag = 1;//如果当前登陆人是本人开的课程，则是老师身份
			}else if (tCourseSiteUserService.isSTeacherBySiteId(tCourseSiteId,user.getUsername())){
				flag = 1;//如果当前登陆人是本课程的助教，则是老师身份
			}else if(tCourseSiteUserService.isUserBelongToTeacher(tCourseSiteId)){
				flag=1;
			}
			else {
				flag = 0;//如果当前登陆人不是本人开的课程，则是学生身份
			}
			
		}else if(user!=null&&user.getAuthorities().toString().contains("STUDENT")){
			flag = 0;
		}
		mav.addObject("flag", flag);
		
		//知识技能体验分类
		mav.addObject("moduleType", moduleType);
		
		httpSession.setAttribute("selected_courseSite", tCourseSiteId);
		
		//空的章节
		WkChapter wkChapter = new WkChapter();
		mav.addObject("wkChapter", wkChapter);
		//空的课时
		WkLesson wkLesson = new WkLesson();
		mav.addObject("wkLesson", wkLesson);
		
		//空的视频文件夹
		WkFolder videoFolder = new WkFolder();
		mav.addObject("videoFolder", videoFolder);
		
		//空的图片文件夹
		WkFolder imageFolder = new WkFolder();
		mav.addObject("imageFolder", imageFolder);
		
		//空的文件文件夹
		WkFolder documentFolder = new WkFolder();
		mav.addObject("documentFolder", documentFolder);
		
 		//资源库分页
 		int pagesize = 20;
 		int currpage = 1;
 		//图片资源库
 		int uploadTypeImages = 1;//图片类型
 		int imagesTotalRecords = wkUploadService.countWkUploadsByUser(user, uploadTypeImages);
 		Map<String, Integer> pageModel1 = shareService.getPage(currpage, pagesize, imagesTotalRecords);
 		List<WkUpload> wkImagesUploadList = wkUploadService.findWkUploadsByUser(user, currpage, pagesize, uploadTypeImages);
 		mav.addObject("wkImagesUploadList", wkImagesUploadList);
 		mav.addObject("pageModel1", pageModel1);
 		//附件资源库
 		int uploadTypeFiles = 2;//文件类型
 		int filesTotalRecords = wkUploadService.countWkUploadsByUser(user, uploadTypeFiles);
 		Map<String, Integer> pageModel2 = shareService.getPage(currpage, pagesize, filesTotalRecords);
 		List<WkUpload> wkFilesUploadList = wkUploadService.findWkUploadsByUser(user, currpage, pagesize, uploadTypeFiles);
 		mav.addObject("wkFilesUploadList", wkFilesUploadList);
 		mav.addObject("pageModel2", pageModel2);
		
		
		
		//新建作业窗口
		//页面表单对象
		TAssignment  tAssignment = new TAssignment();
		TAssignmentControl  tAssignmentControl = new TAssignmentControl();
		//初始化默认时间
		Calendar duedate = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String nowDate =sdf.format(duedate.getTime());
		mav.addObject("now",nowDate);
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
		
		//题库
		List<TAssignmentQuestionpool> tAssignmentQuestionpools = tAssignmentQuestionPoolService.findQuestionList(tCourseSiteId);
		mav.addObject("tAssignmentQuestionpools", tAssignmentQuestionpools);
		
		if (user != null) {
			List<ViewTAssignment> chapterViewTAssignments = tAssignmentService.findTAssignmentList(user,tCourseSiteId,flag,"chapter");
			mav.addObject("chapterViewTAssignments", chapterViewTAssignments);
			
			List<ViewTAssignment> lessonViewTAssignments = tAssignmentService.findTAssignmentList(user,tCourseSiteId,flag,"lesson");
			mav.addObject("lessonViewTAssignments", lessonViewTAssignments);
			
			List<ViewTAssignment> chapterViewExams = tAssignmentService.findViewExamList(user,tCourseSiteId,flag,"chapter");
			mav.addObject("chapterViewExams", chapterViewExams);
			
			List<ViewTAssignment> lessonViewExams = tAssignmentService.findViewExamList(user,tCourseSiteId,flag,"lesson");
			mav.addObject("lessonViewExams", lessonViewExams);
			
			List<ViewTAssignment> chapterViewTests = tAssignmentForTestService.findTestList(user, tCourseSiteId, "test", 1,"chapter");
			mav.addObject("chapterViewTests", chapterViewTests);
			
			List<ViewTAssignment> lessonViewTests = tAssignmentForTestService.findTestList(user, tCourseSiteId, "test", 1,"lesson");
			mav.addObject("lessonViewTests", lessonViewTests);
			
			//已提交测试的成绩
			//List<TAssignmentGrading> tAssignmentGradings = tAssignmentGradingService.findTAssignmentGradingList(tCourseSiteId,user,"exam");     
			//mav.addObject("TAssignmentGradings",tAssignmentGradings);
			
		}
		//查询题库及题库下单选题数量
		//List<Object[]> list = tAssignmentQuestionPoolService.findQuestionInfoListBySiteId(tCourseSite.getId(),4);
		//mav.addObject("questionList", list);
		//通知模块
		int infoPagesize = 5;
 		int infoCurrpage = 1;
 		//获取该站点下的通知数量
		int totalRecords = tMessageService.getCountTMessageListBytCourseSiteId(tCourseSiteId.toString());
		mav.addObject("totalRecords", totalRecords);
		Map<String, Integer> pageModel = shareService.getPage(infoCurrpage, infoPagesize, totalRecords);
		mav.addObject("pageModel", pageModel);
		//将currpage映射到page，用来获取当前页的页码
		mav.addObject("page", infoCurrpage);
		//获取通知
		//List<TMessage> tMessages = tMessageService.findTMessageListBytCourseSiteId(tCourseSiteId.toString(),infoCurrpage,infoPagesize);
		List<TMessage> tMessages = tMessageService.findTMessageListByUsername(user.getUsername(),tCourseSiteId);
		mav.addObject("tMessages",tMessages);
		List<Object[]> tMessageShowViewList = tMessageService.findTMessageShowViewList(user.getUsername(), tCourseSiteId);
		mav.addObject("tMessageShowViewList",tMessageShowViewList);
		if(viewFlag!=null&&viewFlag.equals("0")){
			mav.setViewName("tcoursesite/chaptersList.jsp");
//			mav.setViewName( "redirect:/tcoursesite/question/findQuestionList?tCourseSiteId=" + tCourseSiteId);
		}else{
			mav.setViewName("tcoursesite/chaptersView.jsp");
//			mav.setViewName( "redirect:/tcoursesite/question/findQuestionList?tCourseSiteId=" + tCourseSiteId);
		}
//		//判断资源存储根目录文件夹是否存在
//		File root = new File (request.getSession().getServletContext().getRealPath("/") + "upload/tcoursesite/site_disk_" + tCourseSiteId);
//		//不存在则创建
//		if  (!root .exists()  && !root .isDirectory())      
//		{       
//			NetFileDiskUtils.mkDirectory(root + "/" );
//		}
		return mav;
	}
	
	/**************************************************************************
	 * Description:保存章节
	 *  
	 * @author：裴继超
	 * @date ：2016-04-28
	 **************************************************************************/
	@SystemServiceLog("保存章节")
	@ResponseBody
	@RequestMapping("/tcoursesite/saveChapter")
	public ModelAndView saveChapter(@ModelAttribute WkChapter chapter,@RequestParam Integer tCourseSiteId, Integer moduleType,HttpServletRequest request,Integer selectId) {
		ModelAndView mav = new ModelAndView();
		
		if (chapter.getId() == null){
			TCourseSite tCourseSite = tCourseSiteService.findCourseSiteById(tCourseSiteId);
			int maxseq = wkChapterService.queryMaxChapterSeqBySiteId(tCourseSite);
			chapter.setSeq(maxseq+1);
		}
		chapter.setType(moduleType);
		chapter = wkChapterService.saveChapter(chapter);
		
		mav.setViewName( "redirect:/tcoursesite/chaptersList?tCourseSiteId=" + tCourseSiteId + "&moduleType=" + moduleType +"&selectId="+selectId);
		return mav;
	}
	
	/**************************************************************************
	 * Description:编辑章节
	 *  
	 * @author：裴继超
	 * @date ：2016-04-28
	 **************************************************************************/
	@SystemServiceLog("编辑章节")
	@RequestMapping("/tcoursesite/editChapter")
	@ResponseBody
	public Map<String, Object> editChapter(@RequestParam Integer chapterId) {
		WkChapter chapter = wkChapterService.findChapterByPrimaryKey(chapterId);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("chapterName", (chapter.getName()==null)?"":chapter.getName()); // 章节title
		map.put("chapterId", (chapter.getId()==null)?"":chapter.getId());// 章节id
		map.put("chapterSeq", (chapter.getSeq()==null)?"":chapter.getSeq());// 章节Seq
		return map;
	}
	
	/**************************************************************************
	 * Description:删除章节
	 *  
	 * @author：裴继超
	 * @date ：2016-04-28
	 **************************************************************************/
	@SystemServiceLog("删除章节")
	@ResponseBody
	@RequestMapping("/tcoursesite/deleteChapter")
	public String deleteChapter(@RequestParam Integer chapterId,@RequestParam Integer tCourseSiteId, Integer moduleType,HttpServletRequest request) {
		WkChapter chapter = wkChapterService.findChapterByPrimaryKey(chapterId);
		wkChapterService.deleteChapter(chapter);
		
		return "ok";
	}
	
	/**************************************************************************
	 * Description:保存课时
	 *  
	 * @author：裴继超
	 * @date ：2016-04-28
	 **************************************************************************/
	@SystemServiceLog("保存课时")
	@ResponseBody
	@RequestMapping("/tcoursesite/saveLesson")
	public ModelAndView saveLesson(@ModelAttribute WkLesson lesson,@RequestParam Integer tCourseSiteId, Integer moduleType,HttpServletRequest request,Integer selectId) {
		ModelAndView mav = new ModelAndView();
		if (lesson.getId() == null){
			WkChapter chapter=wkChapterService.findChapterByPrimaryKey(lesson.getWkChapter().getId());
				if(chapter.getWkLessons().size()!=0){
					int nextseq=wkLessonService.queryMaxLessonSeqByChapterId(chapter);
					lesson.setSeq(nextseq+1);
				}else{
					lesson.setSeq(1);
				}
		}
		wkLessonService.saveLesson(lesson);
		
		mav.setViewName( "redirect:/tcoursesite/chaptersList?tCourseSiteId=" + tCourseSiteId + "&moduleType=" + moduleType +"&selectId="+selectId);
		return mav;
	}
	
	/**************************************************************************
	 * Description:删除课时
	 *  
	 * @author：裴继超
	 * @date ：2016-04-28
	 **************************************************************************/
	@SystemServiceLog("删除课时")
	@ResponseBody
	@RequestMapping("/tcoursesite/deleteLesson")
	public String deleteLesson(@RequestParam Integer lessonId,@RequestParam Integer tCourseSiteId, Integer moduleType,HttpServletRequest request) {
		WkLesson lesson = wkLessonService.findWkLessonByPrimaryKey(lessonId);
		wkLessonService.deleteLesson(lesson);
		
		return "ok";
	}
	
	/**************************************************************************
	 * Description:编辑课时
	 *  
	 * @author：裴继超
	 * @date ：2016-04-28
	 **************************************************************************/
	@SystemServiceLog("编辑课时")
	@RequestMapping("/tcoursesite/editLesson")
	@ResponseBody
	public Map<String, Object> editLesson(@RequestParam Integer lessonId) {
		WkLesson lesson = wkLessonService.findWkLessonByPrimaryKey(lessonId);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("lessonIdTitle", (lesson.getTitle()==null)?"":lesson.getTitle()); // 课时title
		map.put("lessonLearningTarget", (lesson.getLearningTarget()==null)?"":lesson.getLearningTarget()); // 课时学习目标
		map.put("lessonRemarks", (lesson.getRemarks()==null)?"":lesson.getRemarks());// 课时备注
		map.put("lessonId", (lesson.getId()==null)?"":lesson.getId());// 课时id
		map.put("lessonChapterId", (lesson.getWkChapter().getId()==null)?"":lesson.getWkChapter().getId());// 课时章节id
		map.put("lessonSeq", (lesson.getSeq()==null)?"":lesson.getSeq());// 课时seq
		return map;
	}
	
	/**************************************************************************
	 * Description:根据教务排课或自主排课创建课程
	 *  
	 * @author：黄崔俊
	 * @date ：2016-6-3
	 **************************************************************************/
	@RequestMapping("/tcoursesite/createTCourseSite")
	public ModelAndView createTCourseSite(@RequestParam String type, String courseNo, Integer timetableSelfCourseId) {
		
		ModelAndView mav = new ModelAndView();
		//根据排课来源及相应主键创建教学课程
		TCourseSite tCourseSite = tCourseSiteService.createTCourseSiteForXD(type,courseNo,timetableSelfCourseId);
		//根据创建课程添加相应学生
		tCourseSite = tCourseSiteService.addTCourseSiteUsersForXD(tCourseSite); 
		/*if ("1".equals(moduleType)) {
			mav.setViewName("redirect:/timetable/listTimetableTerm?currpage=1&id=-1");
		}
		if ("2".equals(moduleType)) {
			mav.setViewName("redirect:/timetable/selfTimetable/listCourseCodes?currpage=1");
		}*/
		mav.setViewName("redirect:/tcoursesite/listTCourseSite?currpage=1");
		return mav;
	}
	
	/**************************************************************************
	 * Description:查询教学课程列表
	 *  
	 * @author：黄崔俊
	 * @date ：2016-6-21
	 **************************************************************************/
	@RequestMapping("/tcoursesite/listTCourseSite")
	public ModelAndView listTCourseSite(@RequestParam Integer currpage,@ModelAttribute TCourseSite tCourseSite) {
		
		ModelAndView mav = new ModelAndView();
		int pageSize = 20;
		int totalRecords = tCourseSiteService.getTCourseSiteTotalRecordsByTCourseSite(tCourseSite, currpage, pageSize);
		List<TCourseSite> tCourseSites = tCourseSiteService.findTCourseSitesByTCourseSite(tCourseSite, currpage, pageSize);
		Map<String,Integer> pageModel =shareService.getPage(currpage,pageSize,totalRecords);
		mav.addObject("tCourseSites", tCourseSites);
		mav.addObject("pageModel", pageModel);
		mav.addObject("tCourseSite", tCourseSite);
		//当前登录人
		User user = shareService.getUser();
		mav.addObject("user", user);
		// 学期
		List<SchoolTerm> terms = shareService.findAllSchoolTerms();
		mav.addObject("terms", terms);
		mav.setViewName("/tcoursesite/listTCourseSite.jsp");
		return mav;
	}
	
	/**************************************************************************
	 * Description:复制课程时跳转选择资源页面
	 *  
	 * @author：裴继超
	 * @date ：2016-06-14
	 **************************************************************************/
	@RequestMapping("/tcoursesite/copy/foldersList")
	public ModelAndView foldersList(@RequestParam Integer tCourseSiteId,HttpSession httpSession) {
		ModelAndView mav = new ModelAndView();
		// 当前登录人
		User user = shareService.getUser();
		mav.addObject("user", user);
		String authority = "";
		int i = 1;
		Set<Authority> auths = user.getAuthorities();
		for (Authority a : auths) {
			if (a.getType() >= i) {
				authority = a.getCname();
				i = a.getType();
			}
		}
		mav.addObject("authority", authority);
		// 选择的课程中心
		TCourseSite tCourseSite = tCourseSiteService.findCourseSiteById(tCourseSiteId);
		mav.addObject("tCourseSite", tCourseSite);
		
		mav.setViewName("tcoursesite/copy/foldersList.jsp");
		return mav;
	}
	
	/**************************************************************************
	 * Description:复制课程时跳转选择目标课程页面
	 *  
	 * @author：裴继超
	 * @date ：2016-06-14
	 **************************************************************************/
	@RequestMapping("/tcoursesite/copy/tCourseSitesList")
	public ModelAndView tCourseSitesList(@RequestParam Integer tCourseSiteId,String allIdsString,HttpSession httpSession) {
		ModelAndView mav = new ModelAndView();
		TCourseSite tCourseSite = tCourseSiteService.findCourseSiteById(tCourseSiteId);
		mav.addObject("tCourseSite", tCourseSite);
		// 当前登录人
		User user = shareService.getUser();
		mav.addObject("user", user);
		String authority = "";
		int i = 1;
		Set<Authority> auths = user.getAuthorities();
		for (Authority a : auths) {
			if (a.getType() >= i) {
				authority = a.getCname();
				i = a.getType();
			}
		}
		mav.addObject("authority", authority);
		// 选择的课程中心
		List<TCourseSite> tCourseSites = tCourseSiteService.findTCourseSiteByTerm();
		mav.addObject("tCourseSites", tCourseSites);
		mav.addObject("allIdsString", allIdsString);
		
		mav.setViewName("tcoursesite/copy/tCourseSitesList.jsp");
		return mav;
	}
	
	/**************************************************************************
	 * Description:复制课程保存复制资源
	 *  
	 * @author：裴继超
	 * @date ：2016-06-14
	 **************************************************************************/
	@RequestMapping("/tcoursesite/copy/saveTCourseSites")
	public ModelAndView saveTCourseSites(@RequestParam Integer tCourseSiteId,String allIdsString,HttpSession httpSession,HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		TCourseSite tCourseSite = tCourseSiteService.findCourseSiteById(tCourseSiteId);
		mav.addObject("tCourseSite", tCourseSite);
		// 当前登录人
		User user = shareService.getUser();
		mav.addObject("user", user);
		
		List<TCourseSite> tCourseSites = new ArrayList();
				
		String[] courseIds =request.getParameterValues("checkname");
		String tCourseSiteIdsString = "";
		for(String c:courseIds){
			TCourseSite newtCourseSite = tCourseSiteService.copyTCourseSite(allIdsString, Integer.parseInt(c),request);
			tCourseSites.add(newtCourseSite);
			tCourseSiteIdsString = tCourseSiteIdsString + c + ",";
		}
		mav.addObject("tCourseSites", tCourseSites);
		mav.setViewName("redirect:/tcoursesite/copy/copySuccess?tCourseSiteIdsString="+tCourseSiteIdsString+"&tCourseSiteId="+tCourseSiteId);
		return mav;
	}
	
	/**************************************************************************
	 * Description:复制课程成功后跳转到成功页面
	 *  
	 * @author：裴继超
	 * @date ：2016-06-14
	 **************************************************************************/
	@SystemServiceLog("复制课程")
	@ResponseBody
	@RequestMapping("/tcoursesite/copy/copySuccess")
	public ModelAndView copySuccess(@RequestParam Integer tCourseSiteId,String tCourseSiteIdsString,HttpSession httpSession,HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		TCourseSite tCourseSite = tCourseSiteService.findCourseSiteById(tCourseSiteId);
		mav.addObject("tCourseSite", tCourseSite);
		// 当前登录人
		User user = shareService.getUser();
		mav.addObject("user", user);
		
		List<TCourseSite> tCourseSites = new ArrayList();
				
		String[] tCourseSiteIds = tCourseSiteIdsString.split(",");
		
		for(String i:tCourseSiteIds){
			TCourseSite copyTCourseSite = tCourseSiteService.findCourseSiteById(Integer.parseInt(i));
			tCourseSites.add(copyTCourseSite);
		}
		mav.addObject("tCourseSites", tCourseSites);
		mav.setViewName("tcoursesite/copy/copySuccess.jsp");
		return mav;
	}
	
	/**************************************************************************
	 * Description:知识技能体验-ajax根据type获取章节
	 * 
	 * @author：裴继超
	 * @date ：2016年8月19日16:47:20
	 **************************************************************************/
	@RequestMapping("/tcoursesite/findChapterMap")
	@ResponseBody
	public Map<Integer, String> findChapterMap(@RequestParam Integer tCourseSiteId,Integer moduleType) {
		Map<Integer, String> map = new HashMap<Integer, String>();
		map = wkChapterService.findChapterMapByModuleTypeAndSiteId(tCourseSiteId, moduleType);
		return map;
	}
	
	/**************************************************************************
	 * Description:知识技能体验-ajax根据type获取章节
	 * 
	 * @author：裴继超
	 * @date ：2016年8月19日16:47:20
	 **************************************************************************/
	@RequestMapping("/tcoursesite/findLessonMap")
	@ResponseBody
	public Map<Integer, String> findLessonMap(@RequestParam Integer chapterId) {
		Map<Integer, String> map = new HashMap<Integer, String>();
		map = wkLessonService.findLessonMapByChapterId(chapterId);
		return map;
	}
	
	/**************************************************************************
	 * Description:列出选课组所有课程
	 * 
	 * @author：储俊
	 * @date ：2016年8月23日
	 **************************************************************************/
	@RequestMapping("/tcoursesite/listSelectCourse")
	public ModelAndView listSelectCourse(@RequestParam int currpage,@ModelAttribute TCourseSite tCourseSite) {
		ModelAndView mav = new ModelAndView();	
		//获取当前学期
		Calendar duedate = Calendar.getInstance();
		SchoolTerm term = shareService.getBelongsSchoolTerm(duedate);
		mav.addObject("term", term);
		//如果查询条件里面没有学期，默认查询当前学期
		if(tCourseSite.getSchoolTerm()!=null&&tCourseSite.getSchoolTerm().getId()==-1){
			tCourseSite.setSchoolTerm(null);
		}
		User user = shareService.getUser();
		mav.addObject("user", user);
		String authorities = user.getAuthorities().toString();
		if(!authorities.contains("SUPERADMIN")){
			tCourseSite.setUserByCreatedBy(user);
		}
		// 设置分页变量并赋值为20；
		int pageSize = 24;
		int totalRecords = tCourseSiteService.getAllTCourseSiteTotalRecordsByTCourseSite(tCourseSite, currpage, pageSize);

		List<TCourseSite> tCourseSites = tCourseSiteService.findAllTCourseSitesByTCourseSite(tCourseSite, currpage, pageSize);
		Map<String, Integer> pageModel = shareService.getPage(currpage, pageSize, totalRecords);
		mav.addObject("newFlag", true);
		mav.addObject("pageModel", pageModel);
		mav.addObject("page", currpage);
		mav.addObject("totalRecords", totalRecords);
		mav.addObject("TCourseSites", tCourseSites);
		mav.addObject("tCourseSite", tCourseSite);
		//查询所有学期
		List<SchoolTerm> schoolTerms = shareService.findAllSchoolTerm();
		//左侧栏选中项
		mav.addObject("select", "tcourse");
		mav.addObject("schoolTerms", schoolTerms);
		// 将该Model映射到listSelectCourse.jsp;
		mav.setViewName("/tcoursesite/selectCourse/listSelectCourse.jsp");
		return mav;
	}
	/**************************************************************************
	 * Description:编辑课程
	 * 
	 * @author：储俊
	 * @date ：2016年8月24日
	 **************************************************************************/
	@RequestMapping("/tcoursesite/editSelectCourse")
	public ModelAndView editSelectCourse(@RequestParam Integer tCourseSiteId)
	{
		ModelAndView mav = new ModelAndView();
		User user = shareService.getUser();
		mav.addObject("user", user);
		// 获取最大的id
		int maxId = tCourseSiteService.getSelectCourseTotalRecords();
		mav.addObject("maxId", maxId);
		mav.addObject("flagId", -1);
		TCourseSite tCourseSite = tCourseSiteService.findCourseSiteById(tCourseSiteId);
		mav.addObject("tCourseSite",tCourseSite);
		List<SchoolTerm> schoolTerms = outerApplicationService.getSchoolTermList();
		mav.addObject("schoolTerms", schoolTerms);
		
		// 获取专业列表
		//Map schoolMajors = shareService.getMajorsMap();
		//mav.addObject("schoolMajors", schoolMajors);
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
		//获取当前课程下助教
		List<TCourseSiteUser> sTeachers = tCourseSiteUserService.findTCourseSiteSTeacherBySiteId(tCourseSiteId);
		String sTeachersInfo = "";
		for(TCourseSiteUser s:sTeachers){
			sTeachersInfo += (","+s.getUser().getUsername());
		}
		mav.addObject("steachers", sTeachersInfo);
		//左侧栏选中项
		mav.addObject("select", "tcourse");
		mav.setViewName("/tcoursesite/selectCourse/editSelectCourse.jsp");
		return mav;
	}
	/**************************************************************************
	 * Description:删除课程
	 * 
	 * @author：储俊
	 * @date ：2016年8月24日
	 **************************************************************************/
	@RequestMapping("/tcoursesite/deleteSelectCourse")
	public ModelAndView deleteSelectCourse(@RequestParam Integer tCourseSiteId) {
		ModelAndView mav = new ModelAndView();
		TCourseSite tCourseSite = tCourseSiteService.findCourseSiteById(tCourseSiteId);
		 tCourseSiteService.delete(tCourseSite);
		tCourseSite.setStatus(0);
		mav.setViewName("redirect:/tcoursesite/listSelectCourse?currpage=1");
		return mav;
	}
	/**************************************************************************
	 * Description:保存课程
	 * 
	 * @author：储俊
	 * @date ：2016年8月24日
	 **************************************************************************/
	@RequestMapping("/tcoursesite/saveTCourseSite")
	public ModelAndView saveTCourseSite(HttpServletRequest request,
			@ModelAttribute TCourseSite tCourseSite, @RequestParam int flagID) {
		ModelAndView mav = new ModelAndView();
		String returnUrl = tCourseSiteService.saveTCourseSite(request, tCourseSite, flagID);
		mav.setViewName("redirect:/tcoursesite/listSelectCourse?currpage=1"); 
		
		return mav;
	}

	/**************************************************************************
	 * Description:课程站点 班级成员-查看班级学生
	 *  
	 * @author：裴继超
	 * @date ：2016-06-16
	 **************************************************************************/
	@SystemServiceLog("查看班级学生")
	@ResponseBody
	@RequestMapping("/tcoursesite/SelectCourse/courseStudentsList")
	public ModelAndView courseStudent(@RequestParam Integer tCourseSiteId,Integer currpage,HttpSession httpSession) {
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
		//获取该课程下学生数
		int totalRecords = tCourseSite.getTCourseSiteUsers().size();
		mav.addObject("totalRecords", totalRecords);
		//设置分页大小
		int pageSize = 20;
		Map<String, Integer> pageModel = shareService.getPage(currpage,pageSize, totalRecords);
		mav.addObject("pageModel", pageModel);
		//将currpage映射到page，用来获取当前页的页码
		mav.addObject("page", currpage);
		List<TCourseSiteUser> tCourseSiteUsers = tCourseSiteUserService.findTCourseSiteUserBySiteId(tCourseSiteId, currpage, pageSize);
		
		
		mav.addObject("tCourseSiteUsers",tCourseSiteUsers);
		
		mav.setViewName("tcoursesite/selectCourse/listCourseStudents.jsp");
		return mav;
	}
	
	/**************************************************************************
	 * Description:课程站点 班级成员-查询学生列表
	 *  
	 * @author：裴继超
	 * @date ：2016-06-16
	 **************************************************************************/
	@SystemServiceLog("查询学生列表")
	@ResponseBody
	@RequestMapping("/tcoursesite/SelectCourse/usersList")
	public ModelAndView usersList(@RequestParam Integer tCourseSiteId,Integer currpage,
			@ModelAttribute User newUser,HttpSession httpSession) {
		ModelAndView mav = new ModelAndView();
		// 选择的课程中心
		TCourseSite tCourseSite = tCourseSiteService.findCourseSiteById(tCourseSiteId);
		mav.addObject("tCourseSite", tCourseSite);
		//当前登录人
		User loginUser = shareService.getUser();
		mav.addObject("user", loginUser);
		mav.addObject("newUser", newUser);
		//获取查询user数
		int totalRecords = tCourseSiteUserService.getUsersRecords(newUser,tCourseSiteId);
		mav.addObject("totalRecords", totalRecords);
		//设置分页大小
		int pageSize = 20;
		Map<String, Integer> pageModel = shareService.getPage(currpage,pageSize, totalRecords);
		mav.addObject("pageModel", pageModel);
		//将currpage映射到page，用来获取当前页的页码
		mav.addObject("page", currpage);
		
		//获取所有学院
		List<SchoolAcademy> academys = systemService.getAllSchoolAcademy(1,-1);
		mav.addObject("academys",academys);
		//获取所有专业
		List<SchoolMajor> majors = tCourseSiteUserService.getAllSchoolMajor(1,-1);
		mav.addObject("majors",majors);
		//获取所有专业
		List<SchoolClasses> classes = tCourseSiteUserService.getAllSchoolClass(1,-1);
		mav.addObject("classes",classes);
		//获取学生（分页）
		List<User> users = tCourseSiteUserService.findUsersList(newUser, tCourseSiteId,currpage, pageSize);
		mav.addObject("users",users);
		
		mav.setViewName("tcoursesite/selectCourse/usersList.jsp");
		return mav;
	}
	
	/**************************************************************************
	 * Description:班级成员-保存添加的站点学生
	 *  
	 * @author：裴继超
	 * @date ：2016-07-28
	 **************************************************************************/
	@SystemServiceLog("保存站点学生")
	@ResponseBody
	@RequestMapping("/tcoursesite/SelectCourse/saveTCourseSiteUsers")
	public ModelAndView saveTCourseSiteUsers(@RequestParam Integer tCourseSiteId,HttpSession httpSession,HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		TCourseSite tCourseSite = tCourseSiteService.findCourseSiteById(tCourseSiteId);
		//获取选择的学生
		String[] usernames =request.getParameterValues("checkname");
		//批量添加班级学生
		tCourseSiteUserService.saveTCourseSiteUsers(tCourseSite, usernames);
		mav.setViewName("redirect:/tcoursesite/SelectCourse/courseStudentsList?tCourseSiteId="+tCourseSiteId+"&currpage=1");
		return mav;
	}
	
	/**************************************************************************
	 * Description:班级成员-通过school_course生成的站点学生
	 *  
	 * @author：裴继超
	 * @date ：2016-07-28
	 **************************************************************************/
	@SystemServiceLog("生成站点学生")
	@ResponseBody
	@RequestMapping("/tcoursesite/SelectCourse/saveTCourseSiteUsersByCourseNo")
	public ModelAndView saveTCourseSiteUsersByCourseNo(@RequestParam Integer tCourseSiteId,HttpSession httpSession,HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		TCourseSite tCourseSite = tCourseSiteService.findCourseSiteById(tCourseSiteId);
		mav.addObject("tCourseSite", tCourseSite);
		if(tCourseSite.getSchoolCourse()!=null){
			//批量添加班级学生
			tCourseSiteUserService.saveTCourseSiteUsersByCourseNo(tCourseSite, tCourseSite.getSchoolCourse().getCourseNo());
		}
		mav.setViewName("redirect:/tcoursesite/SelectCourse/courseStudentsList?tCourseSiteId="+tCourseSiteId+"&currpage=1");
		return mav;
	}
	
	/**************************************************************************
	 * Description:班级成员-删除班级成员
	 *  
	 * @author：裴继超
	 * @date ：2016-07-28
	 **************************************************************************/
	@SystemServiceLog("删除班级成员")
	@ResponseBody
	@RequestMapping("/tcoursesite/SelectCourse/deleteTCourseSiteUser")
	public ModelAndView deleteTCourseSiteUser(@RequestParam Integer tCourseSiteId,Integer id,HttpSession httpSession,HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		TCourseSite tCourseSite = tCourseSiteService.findCourseSiteById(tCourseSiteId);
		//删除班级学生
		tCourseSiteUserService.deleteTCourseSiteUser(id);
		mav.setViewName("redirect:/tcoursesite/SelectCourse/courseStudentsList?tCourseSiteId="+tCourseSiteId+"&currpage=1");
		return mav;
	}
	

	/**************************************************************************
	 * Description:班级成员-批量删除班级成员
	 *  
	 * @author：裴继超
	 * @date ：2016-07-28
	 **************************************************************************/
	@SystemServiceLog("批量删除班级成员")
	@ResponseBody
	@RequestMapping("/tcoursesite/SelectCourse/deleteTCourseSiteUsers")
	public ModelAndView deleteTCourseSiteUsers(@RequestParam Integer tCourseSiteId,HttpSession httpSession,HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		TCourseSite tCourseSite = tCourseSiteService.findCourseSiteById(tCourseSiteId);
		String[] ids = request.getParameterValues("checkname");
		//删除班级学生
		for(String i:ids){
			tCourseSiteUserService.deleteTCourseSiteUser(Integer.parseInt(i));
		}
		
		mav.setViewName("redirect:/tcoursesite/SelectCourse/courseStudentsList?tCourseSiteId="+tCourseSiteId+"&currpage=1");
		return mav;
	}
	
	
	/**************************************************************************
	 * Description:根据教务排课或自主排课创建课程
	 * @throws ParseException 
	 *  
	 * @author：黄崔俊
	 * @date ：2016-6-3
	 **************************************************************************/
	@RequestMapping("/tcoursesite/createTCourseSiteNew")
	public ModelAndView createTCourseSiteNew(@RequestParam String type, String courseNo, Integer timetableSelfCourseId,HttpServletRequest request) throws ParseException {
		
		ModelAndView mav = new ModelAndView();
		//根据排课来源及相应主键创建教学课程
		TCourseSite tCourseSite = tCourseSiteService.createTCourseSiteForXD(type,courseNo,timetableSelfCourseId);
		//根据创建课程添加相应学生
		tCourseSite = tCourseSiteService.addTCourseSiteUsersForXD(tCourseSite); 
		tCourseSite = tCourseSiteService.addOperationForXD(type, courseNo, timetableSelfCourseId,request,tCourseSite);
		/*if ("1".equals(moduleType)) {
			mav.setViewName("redirect:/timetable/listTimetableTerm?currpage=1&id=-1");
		}
		if ("2".equals(moduleType)) {
			mav.setViewName("redirect:/timetable/selfTimetable/listCourseCodes?currpage=1");
		}*/
	
		mav.setViewName("redirect:/newtimetable/listMySchedule?currpage=1");
		return mav;
	}
	
	/**************************************************************************
	 * Description:根据题库id，题目类型来获取题库该类题目总数
	 * 
	 * @author：于侃
	 * @date ：2016年10月27日 14:18:05
	 **************************************************************************/
	@SystemServiceLog("根据题库id，题目类型来获取题库该类题目总数")
	@ResponseBody
	@RequestMapping("/tcoursesite/question/getItemCount")
	public Map<String, String> getItemCount(Integer questionpoolId,String type){
		Map<String, String> map = new LinkedHashMap<String, String>();
		String poolNameString = tAssignmentQuestionPoolService.findTAssignmentQuestionpoolById(questionpoolId).getTitle();
		map.put("poolNameString",(poolNameString + "(" + tAssignmentQuestionPoolService.getItemCount(questionpoolId, Integer.parseInt(type)) + ")"));
		return map;
	}
}