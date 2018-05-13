/****************************************************************************
 * 命名规范：
 * 本控制层调整必须是按照如下的路径调整：@RequestMapping("/tcoursesite/upload/xxxx")。
 * 列表信息listxxxx，獲取信息：getxxxx 编辑信息：editxxxx 删除信息：detelexxxx 新增信息 newxxxx
 * 导出信息exportxxxx 保存信息：savexxxx 
 ****************************************************************************/

package net.xidlims.web.tcoursesite;

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
import javax.servlet.http.HttpServletResponse;

import net.xidlims.dao.TAssignmentGradingDAO;
import net.xidlims.dao.TCourseSiteDAO;
import net.xidlims.domain.TAssignment;
import net.xidlims.domain.TAssignmentGrading;
import net.xidlims.domain.TAssignmentItemComponent;
import net.xidlims.domain.TCourseSite;
import net.xidlims.domain.TCourseSiteUser;
import net.xidlims.domain.User;
import net.xidlims.domain.WkFolder;
import net.xidlims.service.common.ShareService;
import net.xidlims.service.tcoursesite.TAssignmentService;
import net.xidlims.service.tcoursesite.TCourseSiteService;
import net.xidlims.service.tcoursesite.TCourseSiteUserService;
import net.xidlims.service.tcoursesite.TGradebookService;
import net.xidlims.service.tcoursesite.WkFolderService;
import net.xidlims.service.teaching.TAssignmentGradingService;
import net.xidlims.web.aop.SystemServiceLog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**************************************************************************
 * Description:系统后台管理模块
 * 
 * @author：魏诚
 * @date ：2014-07-14
 **************************************************************************/
@Controller("TAssignmentController")
public class TAssignmentController<JsonResult> {
	@InitBinder
	public void initBinder(WebDataBinder binder, HttpServletRequest request) { 
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
	private TCourseSiteService tCourseSiteService;
	@Autowired
	private TCourseSiteDAO tCourseSiteDAO;
	@Autowired
	private WkFolderService wkFolderService;
	@Autowired
	private TAssignmentService tAssignmentService;
	@Autowired
	private TAssignmentGradingService tAssignmentGradingService;
	@Autowired
	private TGradebookService tGradebookService;
	@Autowired
	private TAssignmentGradingDAO tAssignmentGradingDAO;
	@Autowired
	private TCourseSiteUserService tCourseSiteUserService;
	

	/**************************************************************************
	 * Description:教师布置作业
	 *  
	 * @author：裴继超
	 * @date ：2016-05-31
	 **************************************************************************/
	@SystemServiceLog("布置作业")
	@ResponseBody
	@RequestMapping("/tcoursesite/saveAssignment")
	public ModelAndView saveAssignment(@ModelAttribute TAssignment tAssignment,
			@RequestParam Integer tCourseSiteId,Integer moduleType ,HttpServletRequest request,Integer selectId) throws ParseException{
		ModelAndView mav = new ModelAndView();
		tAssignment = tAssignmentService.saveTAssignment(tAssignment,request);
		//根据作业创建成绩册
		tGradebookService.createGradebook(tCourseSiteId, tAssignment);
		mav.setViewName("redirect:/tcoursesite/chaptersList?tCourseSiteId="+tCourseSiteId+"&moduleType="+moduleType+"&selectId="+selectId);
		return mav;
	}
	
	/**************************************************************************
	 * Description:保存考试
	 * 
	 * @author：裴继超
	 * @date ：2016-06-01
	 **************************************************************************/
	@SystemServiceLog("保存考试")
	@ResponseBody
	@RequestMapping("/tcoursesite/saveTest")
	public ModelAndView saveTest(@ModelAttribute TAssignment tAssignment,@RequestParam Integer tCourseSiteId,Integer moduleType ,HttpServletRequest request,Integer selectId) throws ParseException{
		ModelAndView mav = new ModelAndView();
		
		tAssignment = tAssignmentService.saveTAssignmentForTest(tCourseSiteId,tAssignment,request);
		
		//根据考试创建成绩册
		tGradebookService.createGradebook(tCourseSiteId, tAssignment);
		
		mav.setViewName("redirect:/tcoursesite/chaptersList?tCourseSiteId="+tCourseSiteId+"&moduleType="+moduleType+"&selectId="+selectId);
		return mav;
	}
	
	/**************************************************************************** 
	 * Description:保存测验
	 * 
	 * @author：魏诚
	 * @date ：2015-08-04
	 ****************************************************************************/
	@SystemServiceLog("保存测验")
	@ResponseBody
	@RequestMapping("/tcoursesite/saveExam")
	public ModelAndView saveExam(@ModelAttribute TAssignment tAssignment,@RequestParam Integer tCourseSiteId,Integer moduleType,HttpServletRequest request,Integer selectId) throws ParseException{
		ModelAndView mav = new ModelAndView();
		tAssignment = tAssignmentService.saveTAssignmentForExam(tCourseSiteId,tAssignment,request);
		
		//根据测验创建成绩册
		tGradebookService.createGradebook(tCourseSiteId, tAssignment);

		mav.setViewName("redirect:/teaching/exam/examInfo?tCourseSiteId="+tCourseSiteId+"&examId="+tAssignment.getId()+"&moduleType="+moduleType+"&selectId="+selectId);
		return mav;
	}
	
	/**********************************************************************************
	 * Description:知识技能体验-编辑作业
	 * 
	 * @author：裴继超
	 * @date ：2016-05-05
	 **********************************************************************************/
	@SystemServiceLog("编辑作业")
	@RequestMapping("/tcoursesite/editAssignmentFolder")
	@ResponseBody
	public Map<String, Object> editAssignmentFolder(@RequestParam Integer folderId) {
		WkFolder wkFolder = wkFolderService.findWkFolderByPrimaryKey(folderId);

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Map<String, Object> map = new HashMap<String, Object>();
		if (wkFolder.getTAssignments().size()!=0) {// 假如有视频
			TAssignment tAssignment = wkFolder.getTAssignments().iterator().next();// iterator()枚举
			map.put("tAssignmentTitle", (tAssignment.getTitle()==null)?"":tAssignment.getTitle());//
			map.put("tAssignmentId", (tAssignment.getId()==null)?"":tAssignment.getId());//
			map.put("tAssignmentAnswerAssignId", (tAssignment.getTAssignmentAnswerAssign()==null)?"":tAssignment.getTAssignmentAnswerAssign().getId());//
			map.put("tAssignmentControlId", (tAssignment.getTAssignmentControl().getId()==null)?"":tAssignment.getTAssignmentControl().getId());//
			map.put("tAssignmentWkChapterId", (tAssignment.getWkFolder().getWkChapter()==null)?"-1":tAssignment.getWkFolder().getWkChapter().getId());//
			map.put("tAssignmentWkLessonId", (tAssignment.getWkFolder().getWkLesson()==null)?"-1":tAssignment.getWkFolder().getWkLesson().getId());//
			map.put("TAssignmentControl_startdate", (tAssignment.getTAssignmentControl().getStartdate()==null)?"":df.format(tAssignment.getTAssignmentControl().getStartdate().getTime()));//
			map.put("TAssignmentControl_duedate", (tAssignment.getTAssignmentControl().getDuedate()==null)?"":df.format(tAssignment.getTAssignmentControl().getDuedate().getTime()));//
			map.put("tAssignmentUsername", (tAssignment.getTAssignmentAnswerAssign().getUser().getUsername()==null)?"":tAssignment.getTAssignmentAnswerAssign().getUser().getUsername());//
			map.put("assignmentContent", (tAssignment.getContent()==null)?"":tAssignment.getContent());//
			map.put("assignmentStatus", (tAssignment.getStatus()==null)?"":tAssignment.getStatus());//
			map.put("assignmentCreatedTime", (tAssignment.getCreatedTime()==null)?"":df.format(tAssignment.getCreatedTime().getTime()));//
			map.put("assignmentWkFolderId", (tAssignment.getWkFolder()==null)?"":tAssignment.getWkFolder().getId());//
			map.put("assignmentuserusername", (tAssignment.getUser().getUsername()==null)?"":tAssignment.getUser().getUsername());//
			map.put("timelimitselect", (tAssignment.getTAssignmentControl().getTimelimit()==null)?"0":tAssignment.getTAssignmentControl().getTimelimit());//
			map.put("TAssignmentControlSubmitType", (tAssignment.getTAssignmentControl().getSubmitType()==null)?"1":tAssignment.getTAssignmentControl().getSubmitType());//
			map.put("TAssignmentControlToGradebook", (tAssignment.getTAssignmentControl().getToGradebook()==null)?"yes":tAssignment.getTAssignmentControl().getToGradebook());//
			map.put("TAssignmentControlGradeToStudent", (tAssignment.getTAssignmentControl().getGradeToStudent()==null)?"yes":tAssignment.getTAssignmentControl().getGradeToStudent());//
			map.put("TAssignmentControlGradeToTotalGrade", (tAssignment.getTAssignmentControl().getGradeToTotalGrade()==null)?"yes":tAssignment.getTAssignmentControl().getGradeToTotalGrade());//
			 			
		}
		return map;
	}
	
	/**************************************************************************
	 * Description:知识技能体验-编辑测验
	 *  
	 * @author：裴继超
	 * @date ：2016-05-05
	 **************************************************************************/
	@SystemServiceLog("编辑测验")
	@RequestMapping("/tcoursesite/editExamFolder")
	@ResponseBody
	public Map<String, Object> editExamFolder(@RequestParam Integer folderId) {
		//根据主键查询文件夹
		WkFolder wkFolder = wkFolderService.findWkFolderByPrimaryKey(folderId);

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Map<String, Object> map = new HashMap<String, Object>();
		if (wkFolder.getTAssignments().size()!=0) {// 假如有视频
			TAssignment exam = wkFolder.getTAssignments().iterator().next();
			map.put("examTitle", (exam.getTitle()==null)?"":exam.getTitle());//
			map.put("examId", (exam.getId()==null)?"":exam.getId());//
			map.put("examAnswerAssignId", (exam.getTAssignmentAnswerAssign()==null)?"":exam.getTAssignmentAnswerAssign().getId());//
			map.put("examControlId", (exam.getTAssignmentControl().getId()==null)?"":exam.getTAssignmentControl().getId());//
			map.put("examWkChapterId", (exam.getWkFolder().getWkChapter()==null)?"-1":exam.getWkFolder().getWkChapter().getId());//
			map.put("examWkLessonId", (exam.getWkFolder().getWkLesson()==null)?"-1":exam.getWkFolder().getWkLesson().getId());//
			map.put("examTAssignmentControl_startdate", (exam.getTAssignmentControl().getStartdate()==null)?"":df.format(exam.getTAssignmentControl().getStartdate().getTime()));//
			map.put("examTAssignmentControl_duedate", (exam.getTAssignmentControl().getDuedate()==null)?"":df.format(exam.getTAssignmentControl().getDuedate().getTime()));//
			map.put("examUsername", (exam.getTAssignmentAnswerAssign().getUser().getUsername()==null)?"":exam.getTAssignmentAnswerAssign().getUser().getUsername());//
			map.put("examContent", (exam.getContent()==null)?"":exam.getContent());//
			map.put("examStatus", (exam.getStatus()==null)?"":exam.getStatus());//
			map.put("examCreatedTime", (exam.getCreatedTime()==null)?"":df.format(exam.getCreatedTime().getTime()));//
			map.put("examWkFolderId", (exam.getWkFolder()==null)?"":exam.getWkFolder().getId());//
			map.put("examUserUsername", (exam.getUser().getUsername()==null)?"":exam.getUser().getUsername());//
			map.put("examtimelimitselect", (exam.getTAssignmentControl().getTimelimit()==null)?"0":exam.getTAssignmentControl().getTimelimit());//
			map.put("examTAssignmentControlSubmitType", (exam.getTAssignmentControl().getSubmitType()==null)?"1":exam.getTAssignmentControl().getSubmitType());//
			map.put("examScoreExam", (exam.getTAssignmentAnswerAssign().getScore()==null)?"":exam.getTAssignmentAnswerAssign().getScore());//
			map.put("examTAssignmentControlToGradebook", (exam.getTAssignmentControl().getToGradebook()==null)?"yes":exam.getTAssignmentControl().getToGradebook());//
			map.put("examTAssignmentControlGradeToStudent", (exam.getTAssignmentControl().getGradeToStudent()==null)?"yes":exam.getTAssignmentControl().getGradeToStudent());//
			map.put("examTAssignmentControlGradeToTotalGrade", (exam.getTAssignmentControl().getGradeToTotalGrade()==null)?"yes":exam.getTAssignmentControl().getGradeToTotalGrade());//
		}
		return map;
	}
	
	/**************************************************************************
	 * Description:知识技能体验-编辑考试
	 *  
	 * @author：裴继超
	 * @date ：2016-05-05
	 **************************************************************************/
	@SystemServiceLog("编辑考试")
	@RequestMapping("/tcoursesite/editTestFolder")
	@ResponseBody
	public Map<String, Object> editTestFolder(@RequestParam Integer folderId) {
		WkFolder wkFolder = wkFolderService.findWkFolderByPrimaryKey(folderId);

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Map<String, Object> map = new HashMap<String, Object>();
		if (wkFolder.getTAssignments().size()!=0) {// 假如有视频
			TAssignment test = wkFolder.getTAssignments().iterator().next();
			map.put("testTitle", (test.getTitle()==null)?"":test.getTitle());//
			map.put("testId", (test.getId()==null)?"":test.getId());//
			map.put("testAnswerAssignId", (test.getTAssignmentAnswerAssign()==null)?"":test.getTAssignmentAnswerAssign().getId());//
			map.put("testControlId", (test.getTAssignmentControl().getId()==null)?"":test.getTAssignmentControl().getId());//
			map.put("testWkChapterId", (test.getWkFolder().getWkChapter()==null)?"-1":test.getWkFolder().getWkChapter().getId());//
			map.put("testWkLessonId", (test.getWkFolder().getWkLesson()==null)?"-1":test.getWkFolder().getWkLesson().getId());//
			map.put("testTAssignmentControl_startdate", (test.getTAssignmentControl().getStartdate()==null)?"":df.format(test.getTAssignmentControl().getStartdate().getTime()));//
			map.put("testTAssignmentControl_duedate", (test.getTAssignmentControl().getDuedate()==null)?"":df.format(test.getTAssignmentControl().getDuedate().getTime()));//
			map.put("testUsername", (test.getTAssignmentAnswerAssign().getUser().getUsername()==null)?"":test.getTAssignmentAnswerAssign().getUser().getUsername());//
			map.put("testContent", (test.getContent()==null)?"":test.getContent());//
			map.put("testStatus", (test.getStatus()==null)?"":test.getStatus());//
			map.put("testCreatedTime", (test.getCreatedTime()==null)?"":df.format(test.getCreatedTime().getTime()));//
			map.put("testWkFolderId", (test.getWkFolder()==null)?"":test.getWkFolder().getId());//
			map.put("testUserUsername", (test.getUser().getUsername()==null)?"":test.getUser().getUsername());//
			map.put("testtimelimitselect", (test.getTAssignmentControl().getTimelimit()==null)?"0":test.getTAssignmentControl().getTimelimit());//
			map.put("testScoreTest", (test.getTAssignmentAnswerAssign().getScore()==null)?"":test.getTAssignmentAnswerAssign().getScore());//
			
			List<TAssignmentItemComponent>  tAssignmentItemComponents = tAssignmentService.findTAssignmentItemComponent(test);
			String trString = "";
			for(TAssignmentItemComponent t:tAssignmentItemComponents){
				StringBuffer questionIdTestHtml=new StringBuffer();
				if(t.getItemType()==4){questionIdTestHtml.append("单选题");}
				if(t.getItemType()==1){questionIdTestHtml.append("多选题");}
				if(t.getItemType()==2){questionIdTestHtml.append("判断题");}
				if(t.getItemType()==8){questionIdTestHtml.append("单选题");}

				trString += "<tr>"+
						"<td><input type='text' style='width:80px;' name='sectionName' value='"+t.getSectionName()+"' />"+"</td>"+
						"<td><input type='hidden' name='itemTypeTest' value='"+t.getItemType()+"'/>"+questionIdTestHtml+"</td>"+
						"<td><input type='hidden' name='questionIdTest' value='"+t.getTAssignmentQuestionpool().getQuestionpoolId()+"'/>"+t.getTAssignmentQuestionpool().getTitle()+"</td>"+
						"<td><input type='text' style='width:50px;' name='itemQuantityTest' value='"+t.getItemQuantity()+"'  oninput='changeNumber(this)' /></td>"+
						"<td><input type='text' style='width:50px;' name='itemScoreTest' value='"+t.getItemScore()+"' oninput='changeNumber(this)' /></td>"+
						"<td><button type='button' class='btn' onclick='deleteThisTr(this,"+t.getTAssignmentQuestionpool()+")'>删除</button></td>"+
						"</tr>";
			}
			map.put("trString", trString);
		}
		return map;
	}
	
	/**************************************************************************
	 * Description:作业-下载教师附件
	 * 
	 * @author： 裴继超
	 * @date ：2016-08-05
	 **************************************************************************/
	@SystemServiceLog("下载教师附件")
	@ResponseBody
	@RequestMapping("/tcoursesite/assignment/downloadFile")
	public void downloadFile(HttpServletRequest request,@RequestParam Integer tCourseSiteId,
			HttpServletResponse response,@RequestParam Integer id) throws Exception {
		//id对应的文档
		TAssignment assignment= tAssignmentService.findTAssignmentById(id);
		tAssignmentService.downloadTeacherFile(assignment, request, response);
	}
	
	/**************************************************************************************
	 * @description：实验技能-实验作业-浏览提交内容
 	 * @author：陈乐为
	 * @date：2016-10-19
	 *************************************************************************************/
	@RequestMapping("/tcoursesite/assignment/showAssignmentGrading")
	public ModelAndView showAssignmentGrading(@RequestParam Integer tCourseSiteId,Integer assignmentId,Integer skillId, int flag){
		//flag用于判断是学生和教师
		ModelAndView mav = new ModelAndView();
		//全部:-1  视频:1  图片:2  参考文件:3  作业:4  测试:5 考试:6
		
		// 选择的课程中心
		TCourseSite tCourseSite = tCourseSiteService.findCourseSiteById(tCourseSiteId);
		mav.addObject("tCourseSite", tCourseSite);
		User user = shareService.getUserDetail();//查看当前登录用户
		TAssignment tAssignment = tAssignmentService.findTAssignmentById(assignmentId);
		List<TAssignmentGrading> tAssignmentGradings = null;
		//如果当前登入人权限为1且不为作业创建者，则设置查询用的"user"为作业创建者
		if(flag ==1 && user !=tAssignment.getUser()) {
			User userByCreate = tAssignment.getUser();
			tAssignmentGradings = tAssignmentGradingService.findTAssignmentGradingList(assignmentId, flag, userByCreate);
		}else {
			tAssignmentGradings = tAssignmentGradingService.findTAssignmentGradingList(assignmentId, flag, user);
		}
		
		//如果登入人是老师，也可以查看学生的历史提交记录
		if(flag !=0) {
			//查找当前作业下所有学生的提交记录
			Set<TAssignmentGrading> allTAssignmentGradings = tAssignment.getTAssignmentGradings();
			//存放当前课程下所有学生相对应提交的作业
			Map<String,List<TAssignmentGrading>> map =new HashMap<String, List<TAssignmentGrading>>();
			//每个学生提交的作业
			List<TAssignmentGrading> tAssignmentGradingListForStu = null;
			//获取课程站点下的所有学生
			List<TCourseSiteUser> userList = tCourseSiteUserService.findAlltCourseSiteUserBySiteId(tCourseSiteId);
			//循环每个学生并设置对应提交的作业
			for(TCourseSiteUser tCourseSiteUser : userList) {
				
				tAssignmentGradingListForStu = new ArrayList<TAssignmentGrading>();
				//循环提交的每个作业
				for(TAssignmentGrading tAssignmentGrading : allTAssignmentGradings) {
					//如果该作业的提交者为当前学生，则加入map中
					if(tAssignmentGrading.getUserByStudent().getUsername() == tCourseSiteUser.getUser().getUsername()) {
						
						tAssignmentGradingListForStu.add(tAssignmentGrading);
					}
				}
				map.put(tCourseSiteUser.getUser().getUsername(), tAssignmentGradingListForStu);
			}
			mav.addObject("map", map);
		}
		
		mav.addObject("tAssignmentGradings", tAssignmentGradings);
		mav.addObject("tAssignment", tAssignment);
		mav.addObject("user", user);
		mav.addObject("flag", flag);
		mav.addObject("skillId", skillId);
		
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
		mav.setViewName("tcoursesite/skill/showAssignmentGrading.jsp");
		return mav;
	}
	
	/****************************************************************************
	 * @description：实验技能-实验作业-浏览提交内容-教师修改作业
	 * @author：陈乐为
	 * @date：2016-10-19
	 ****************************************************************************/
	@RequestMapping("/tcoursesite/assignment/editAssignmentById")
	public ModelAndView editAssignmentById(@RequestParam Integer tCourseSiteId,Integer skillId,int assignId){
		//新建ModelAndView对象；
		ModelAndView mav=new ModelAndView();
		mav.addObject("skillId", skillId);
		//获取所选的站点
		TCourseSite tCourseSite = tCourseSiteDAO.findTCourseSiteByPrimaryKey(tCourseSiteId);
		mav.addObject("tCourseSite", tCourseSite);
		TAssignment  tAssignment = tAssignmentService.findTAssignmentById(assignId); 
		mav.addObject("tAssignment", tAssignment);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		mav.addObject("startdate",sdf.format(tAssignment.getTAssignmentControl().getStartdate().getTime()));
		mav.addObject("duedate",sdf.format(tAssignment.getTAssignmentControl().getDuedate().getTime()));
		mav.setViewName("tcoursesite/skill/editAssignment.jsp");
		return mav;
	}
	
	/****************************************************************************
	 * @description：实验技能-实验作业-浏览提交内容-教师发布作业
	 * @author：陈乐为
	 * @date：2016-10-19
	 ****************************************************************************/
	@RequestMapping("/tcoursesite/assignment/saveAssignmentGrading")
	public String saveAssignmentGrading(@ModelAttribute TAssignment tAssignment,Integer tCourseSiteId, 
			Integer skillId,HttpServletRequest request) throws ParseException{
		
		tAssignment = tAssignmentService.saveTAssignment(tAssignment,request);
		return "redirect:/tcoursesite/skill/workExperimentSkill?tCourseSiteId="+tCourseSiteId+"&skillId="+skillId;
	}
	
	
	/**************************************************************
	 * @description：学生提交作业
	 * @author：陈乐为
	 * @date：2016-10-20
	 **************************************************************/
	@RequestMapping("/tcoursesite/assignment/saveAssignmentGrade")
	public ModelAndView saveAssignmentGrade(@RequestParam Integer tCourseSiteId,int assignId, int flag,Integer skillId){
		//新建ModelAndView对象；
		ModelAndView mav=new ModelAndView();
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
		mav.addObject("flag", flag);
		// 选择的课程中心
		TCourseSite tCourseSite = tCourseSiteService.findCourseSiteById(tCourseSiteId);
		mav.addObject("tCourseSite", tCourseSite);
		
		mav.addObject("skillId", skillId);
		
		mav.setViewName("tcoursesite/skill/saveAssignmentGrade.jsp");
		return mav;
	}
	
	/****************************************************************************
	 * @descriptoin：学生提交作业 保存
	 * @author：陈乐为
	 * @date：2016-10-20
	 ****************************************************************************/
	@RequestMapping("/tcoursesite/assignment/saveSubmitTAssignmentGrade")
	public ModelAndView saveSubmitTAssignmentGrade(@ModelAttribute TAssignmentGrading tAssignmentGrade,
			Integer tCourseSiteId, Integer skillId,HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("tCourseSiteId", tCourseSiteId);
		mav.addObject("skillId", skillId);
		
		TAssignmentGrading tAssignmentGrading = tAssignmentGradingService.saveTAssignmentGrading(tAssignmentGrade,tCourseSiteId,request);
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
			mav.setViewName("tcoursesite/skill/editAssignmentGrade.jsp");
		}else {//提交后返回课程详情页面
			mav.setViewName("redirect:/tcoursesite/skill/workExperimentSkill");
		}
		return mav;
	}
	
	/*********************************************************************
	 * @description：下载学生附件
	 * @param :id TAssignmentGrading主键
	 * @param：tCourseSiteId 课程主键
	 * @author：陈乐为
	 * @date：2016-10-20
	 *********************************************************************/
	@RequestMapping("/tcoursesite/assignment/downloadFileForTeacher")                                                     
	public String downloadFileForTeacher(HttpServletRequest request,HttpServletResponse response,@RequestParam Integer id,@RequestParam int tCourseSiteId) throws Exception {
		//id对应的文档
		TAssignmentGrading tAssignmentGrade=tAssignmentGradingDAO.findTAssignmentGradingByPrimaryKey(id);
		
		String result=tAssignmentGradingService.downloadFile(tAssignmentGrade, request, response,tCourseSiteId);
		if (result == null) {
			return "forward:/tcoursesite/skill/experimentSkillsList?tCourseSiteId="+tCourseSiteId;
		}
		return null;
		
	}
	
	/*********************************************************************
	 * @description：下载学生附件
	 * @param :id TAssignmentGrading主键
	 * @param：tCourseSiteId 课程主键
	 * @author：陈乐为
	 * @date：2016-10-20
	 *********************************************************************/
	@RequestMapping("/tcoursesite/assignment/downloadFileForStudent")                                                     
	public String downloadFileForStudent(HttpServletRequest request,HttpServletResponse response,@RequestParam Integer id,@RequestParam int tCourseSiteId) throws Exception {
		//id对应的文档
		TAssignmentGrading tAssignmentGrade=tAssignmentGradingDAO.findTAssignmentGradingByPrimaryKey(id);
	 		
		String result=tAssignmentGradingService.downloadFileForStudent(tAssignmentGrade, request, response,tCourseSiteId);
		if (result == null) {
			return "forward:/tcoursesite/skill/experimentSkillsList?tCourseSiteId="+tCourseSiteId;
		}
		return null;
	 }
}