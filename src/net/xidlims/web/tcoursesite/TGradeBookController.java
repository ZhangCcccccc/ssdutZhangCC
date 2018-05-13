/****************************************************************************
 * 命名规范：
 * 本控制层调整必须是按照如下的路径调整：@RequestMapping("/tCourseSite/xxxx")。
 * 列表信息listxxxx，獲取信息：getxxxx 编辑信息：editxxxx 删除信息：detelexxxx 新增信息 newxxxx
 * 导出信息exportxxxx 保存信息：savexxxx 
 ****************************************************************************/

package net.xidlims.web.tcoursesite;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import net.xidlims.dao.TCourseSiteUserDAO;
import net.xidlims.dao.TGradeObjectDAO;
import net.xidlims.dao.TGradeRecordDAO;
import net.xidlims.domain.TCourseSite;
import net.xidlims.domain.TCourseSiteUser;
import net.xidlims.domain.TExperimentSkill;
import net.xidlims.domain.TGradeObject;
import net.xidlims.domain.TGradeRecord;
import net.xidlims.domain.TGradebook;
import net.xidlims.domain.TWeightSetting;
import net.xidlims.domain.User;
import net.xidlims.service.common.ShareService;
import net.xidlims.service.lab.LabCenterService;
import net.xidlims.service.tcoursesite.TAssignmentQuestionPoolService;
import net.xidlims.service.tcoursesite.TCourseSiteArticalService;
import net.xidlims.service.tcoursesite.TCourseSiteChannelService;
import net.xidlims.service.tcoursesite.TCourseSiteService;
import net.xidlims.service.tcoursesite.TCourseSiteUserService;
import net.xidlims.service.tcoursesite.TExperimentSkillService;
import net.xidlims.service.tcoursesite.TGradebookService;
import net.xidlims.service.tcoursesite.WkChapterService;
import net.xidlims.service.tcoursesite.WkFolderService;
import net.xidlims.service.tcoursesite.WkLessonService;
import net.xidlims.service.tcoursesite.WkService;
import net.xidlims.service.tcoursesite.WkUploadService;
import net.xidlims.service.teaching.TAssignmentGradingService;
import net.xidlims.web.aop.SystemServiceLog;

/**************************************************************************
 * Description:系统后台管理模块
 * 
 * @author：魏诚
 * @date ：2014-07-14
 **************************************************************************/
@Controller("TGradeBookController")
public class TGradeBookController<JsonResult> {
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
	private TCourseSiteChannelService tCourseSiteChannelService;
	@Autowired
	private TCourseSiteArticalService tCourseSiteArticalService;
	@Autowired
	private TCourseSiteService tCourseSiteService;
	@Autowired
	private LabCenterService labCenterService;
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
	private TGradebookService tGradebookService;
	@Autowired
	private TCourseSiteUserService tCourseSiteUserService;
	@Autowired
	private TExperimentSkillService tExperimentSkillService;
	@Autowired
	private TAssignmentGradingService  tAssignmentGradingService;
	@Autowired
	private TCourseSiteUserDAO tCourseSiteUserDAO;
	@Autowired
	private TGradeRecordDAO tGradeRecordDAO;
	@Autowired
	private TGradeObjectDAO tGradeObjectDAO;
	
	/**************************************************************************
	 * Description:课程站点-查看成绩册
	 * 
	 * @author：裴继超
	 * @date ：2016年6月16日9:41:47
	 **************************************************************************/
	@SystemServiceLog("查看成绩册")
	@ResponseBody
	@RequestMapping("/tcoursesite/gradeBook")
	public ModelAndView gradeBook(@RequestParam Integer tCourseSiteId,String type,HttpSession httpSession) {
		ModelAndView mav = new ModelAndView();
		//类别：test考试，assignment作业
		mav.addObject("type", type);
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
		//课程所属学生列表
		List<TCourseSiteUser> tCourseSiteUsers = tCourseSiteService.findTCourseSiteStudents(tCourseSiteId) ;     
		mav.addObject("tCourseSiteUsers",tCourseSiteUsers);
		//作业科目列表
		List<TGradeObject> tAssignmentGradeObjects = tGradebookService.findTGradeObjectsByTCourseSiteAndType(tCourseSiteId,"assignment");      
		mav.addObject("tAssignmentGradeObjects",tAssignmentGradeObjects);
		//List<TCourseSite> tCourseSites = tCourseSiteService.findAllTCourseSitesByTCourseSiteUserAndStatus(tCourseSiteUsers.get(0),"1");
		//根据科目和学生查看成绩列表
		List<List<Object>> assignmentLists = tGradebookService.findStudentScoreRecords(tCourseSiteUsers,tAssignmentGradeObjects);
		mav.addObject("assignmentLists", assignmentLists);
		
		//测试科目列表
		List<TGradeObject> tExamGradeObjects = tGradebookService.findTGradeObjectsByTCourseSiteAndType(tCourseSiteId,"exam");      
		mav.addObject("tExamGradeObjects",tExamGradeObjects);
		
		//考勤列表
		List<TGradeObject> tAttendenceGradeObjects = tGradebookService.findTGradeObjectsByTCourseSiteAndType(tCourseSiteId,"attendence");      
		mav.addObject("tAttendenceGradeObjects",tAttendenceGradeObjects);
		
		//List<TCourseSite> tCourseSites = tCourseSiteService.findAllTCourseSitesByTCourseSiteUserAndStatus(tCourseSiteUsers.get(0),"1");
		//根据科目和学生查看成绩列表
		List<List<Object>> examLists = tGradebookService.findStudentScoreRecords(tCourseSiteUsers,tExamGradeObjects);
		mav.addObject("examLists", examLists);
		
		//考试科目列表
		List<TGradeObject> tTestGradeObjects = tGradebookService.findTGradeObjectsByTCourseSiteAndType(tCourseSiteId,"test");      
		mav.addObject("tTestGradeObjects",tTestGradeObjects);

		//List<TCourseSite> tCourseSites = tCourseSiteService.findAllTCourseSitesByTCourseSiteUserAndStatus(tCourseSiteUsers.get(0),"1");
		//根据科目和学生查看成绩列表
		List<List<Object>> testLists = tGradebookService.findStudentScoreRecords(tCourseSiteUsers,tTestGradeObjects);
		mav.addObject("testLists", testLists);
		
		//根据科目和学生查看成绩列表
		List<List<Object>> attendenceLists = tGradebookService.findStudentScoreRecords(tCourseSiteUsers,tAttendenceGradeObjects);
		mav.addObject("attendenceLists", attendenceLists);
		
		//总分科目列表
		List<TGradeObject> tSumGradeObjects = tGradebookService.findTGradeObjectsByTCourseSiteAndType(tCourseSiteId,null);      
		mav.addObject("tSumGradeObjects",tSumGradeObjects);
		
		//查询自定义成绩列表，首先判断本课程站点下是否存在自定义成绩再查询成绩列表
		List<TGradeObject> customTGradeObjects = tGradebookService.findCustomTGradeObjectsByTCourseSite(tCourseSiteId,"custom");
		if(customTGradeObjects !=null && customTGradeObjects.size() !=0){
			
			//参数customFlag用以判断是否存在自定义成绩
			mav.addObject("customFlag",true);
			mav.addObject("customTGradeObjects", customTGradeObjects);
			//根据自定义科目和学生查看成绩列表
			List<List<Object>> customLists = tGradebookService.findCustomStudentScoreRecords(tCourseSiteUsers,customTGradeObjects);
			mav.addObject("customLists", customLists);
			//自定义成绩模块个数
			int customTGradeObjectsSize = customTGradeObjects.size();
			mav.addObject("customTGradeObjectsSize", customTGradeObjectsSize);
			//查询对应课程的总评权重设置情况
			List<TWeightSetting> weightSettings = tGradebookService.findWeightSettingsWithCustom(tCourseSiteId,customTGradeObjectsSize,customTGradeObjects);
			mav.addObject("weightSettings", weightSettings);
			
			//查看总成绩列表(带自定义成绩)
			List<List<Object>> sumLists = tGradebookService.findTotalScoreInfoWithCustom(tCourseSiteUsers,weightSettings,tCourseSiteId,customTGradeObjectsSize,customLists);
			mav.addObject("sumLists", sumLists);
		}else{
			
			//查询对应课程的总评权重设置情况
			List<TWeightSetting> weightSettings = tGradebookService.findWeightSettings(tCourseSiteId);
			mav.addObject("weightSettings", weightSettings);
			//自定义成绩模块个数
			int customTGradeObjectsSize = 0;
			mav.addObject("customTGradeObjectsSize", customTGradeObjectsSize);
			//查看总成绩列表(不带自定义成绩)
			List<List<Object>> sumLists = tGradebookService.findTotalScoreInfo(tCourseSiteUsers,weightSettings,tCourseSiteId);
			mav.addObject("sumLists", sumLists);
		}
		
		//权重设置
		List<List<Object>> assignmentList = tGradebookService.findWeightSetting(tCourseSiteId,"assignment");
		mav.addObject("assignmentList", assignmentList);
		List<List<Object>> examList = tGradebookService.findWeightSetting(tCourseSiteId,"exam");
		mav.addObject("examList", examList);
		List<List<Object>> testList = tGradebookService.findWeightSetting(tCourseSiteId,"test");
		mav.addObject("testList", testList);
		List<List<Object>> attendenceList = tGradebookService.findWeightSetting(tCourseSiteId,"attendence");
		mav.addObject("attendenceList", attendenceList);
		//根据实验列表查看成绩
		List<List<Object>> skillLists = tExperimentSkillService.getGradingWithSkillForAll(tCourseSiteUsers,tCourseSiteId);
		mav.addObject("skillLists", skillLists);
		//实验项目权重设置
		List<List<Object>> weightWithSkill = tExperimentSkillService.getWeightWithSkill(tCourseSiteId);
		mav.addObject("weightWithSkill",weightWithSkill);
		//获取实验项目以及对应的权重
		List<List<Object>> weightAndNameWithSkill = tExperimentSkillService.getWeightAndNameWithSkill(tCourseSiteId);
		mav.addObject("tExperimentSkills",weightAndNameWithSkill);
		mav.setViewName("tcoursesite/gradeBook.jsp");
		return mav;
	}
	
	/**************************************************************************
	 * Description:权重设置
	 * 
	 * @author：裴继超
	 * @date ：2016年6月17日15:59:31
	 **************************************************************************/
	@SystemServiceLog("权重设置")
	@ResponseBody
	@RequestMapping("/tcoursesite/gradeBook/singleWeightSetting")
	public ModelAndView singleWeightSetting(HttpServletRequest request,@RequestParam Integer tCourseSiteId) {
		ModelAndView mav = new ModelAndView();
		//权重设置
		tGradebookService.singleWeightSetting(request,tCourseSiteId);
		mav.setViewName("redirect:/tcoursesite/weightAchievement?tCourseSiteId="+tCourseSiteId+"&type=weight");
		return mav;
	}
	
	/**************************************************************************
	 * Description:自定义成绩保存
	 * 
	 * @author：张佳鸣
	 * @date ：2017年11月15日
	 **************************************************************************/
	@RequestMapping("/tcoursesite/userinfo/saveGrading")
	public ModelAndView saveGrading(HttpServletRequest request,@RequestParam Integer tCourseSiteId,Integer currpage,String customScoreTitle){
			ModelAndView mav = new ModelAndView();

			//保存课程号对应的成绩册t_gradebook
			TGradebook tGradebook = tGradebookService.saveTGradebookByCustom(tCourseSiteService.findTCourseSiteById(tCourseSiteId.toString()));
			
			//保存成绩册中的自定义成绩名称,customScoreTitle为自定义模块名称
			TGradeObject tGradeObject = tGradebookService.saveTGradeObjectByCustom(tGradebook,customScoreTitle);
			
			//课程所属学生列表
			List<TCourseSiteUser> tCourseSiteUsers = tCourseSiteService.findTCourseSiteStudents(tCourseSiteId);
			
			//根据学生列表循环查找个人学生的各门实验成绩
			for(TCourseSiteUser u:tCourseSiteUsers){
				//获取学生和学生学号
				User uesr = u.getUser();
				String userName = u.getUser().getUsername();
				String grade = request.getParameter(userName+"_score");
				//自定义成绩分数,前台如果有数据则正常保存成绩，如果没有则保存为0
				int score = 0;
				if(grade != null) {
					//获取前台输入的自定义成绩
					score = Integer.parseInt(grade);
				}
//				//获取当前时间
//				Date date=new Date();
//				DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//				String time=format.format(date);
				//保存自定义成绩册相关具体成绩记录
				tGradebookService.saveTGradeRecordByCustom(uesr,score,tGradeObject);
				
	        }
		  //重定向到自定义成绩页面
		  mav.setViewName("redirect:/tcoursesite/customAchievement?tCourseSiteId="+tCourseSiteId+"&type=custom&currpage="+currpage);
	      return mav;	
	  }
	
	/**************************************************************************
	 * Description:保存教师修改后的成绩
	 * 
	 * @author：李军凯
	 * @date ：2016-11-12
	 **************************************************************************/
	@ResponseBody
	@RequestMapping("/tcoursesite/saveFinalGrade")
	public ModelAndView saveFinalGrade(HttpServletRequest request,@RequestParam Integer tCourseSiteId,Integer currpage){
		ModelAndView mav = new ModelAndView();
		List<TCourseSiteUser> tCourseSiteUsers = tCourseSiteService.findTCourseSiteStudents(tCourseSiteId);
		for(TCourseSiteUser t:tCourseSiteUsers){
			if(t.getUser().getUsername()!=null && t.getUser().getUsername()!=""){
				//消除页面分页导致的空数据报错
				String grade = request.getParameter(t.getUser().getUsername()+"_recruitment");
				if(grade == null) continue;
				t.setIncrement(Integer.parseInt(grade));
				tCourseSiteUserDAO.store(t);
				tCourseSiteUserDAO.flush();
			}
		}
		mav.setViewName("redirect:/tcoursesite/sumAchievement?tCourseSiteId="+tCourseSiteId+"&type=sum&currpage="+currpage);
		return mav;
	}
	
	/**************************************************************************
	 * Description:保存改动后的自定义成绩
	 * 
	 * @author：张佳鸣
	 * @date ：2017-11-16
	 **************************************************************************/
	@ResponseBody
	@RequestMapping("/tcoursesite/saveEditCustomScore")
	public ModelAndView saveEditCustomScore(HttpServletRequest request,@RequestParam Integer tCourseSiteId,Integer currpage){
		ModelAndView mav = new ModelAndView();
		//查找本站点下的自定义成绩册
		List<TGradeObject> customTGradeObjects = tGradebookService.findCustomTGradeObjectsByTCourseSite(tCourseSiteId,"custom");
		int i = 3;
		for(TGradeObject customTGradeObject : customTGradeObjects){
			
			for(TGradeRecord tGradeRecord : customTGradeObject.getTGradeRecords()){
					String grade = request.getParameter(tGradeRecord.getUser().getUsername()+"_"+i);
					if(grade ==null)	continue;
					//保存前台传过来自定义成绩的数据
					tGradeRecord.setPoints(new BigDecimal(Integer.parseInt(grade)));
					tGradeRecordDAO.store(tGradeRecord);
					tGradeRecordDAO.flush();
			}
			i++;
		}
		mav.setViewName("redirect:/tcoursesite/customAchievement?tCourseSiteId="+tCourseSiteId+"&type=custom&currpage="+currpage);
		
		return mav;
	}
	
	/**************************************************************************
	 * Description:删除自定义成绩
	 *  
	 * @author：张佳鸣
	 * @date ：2017-11-17
	 **************************************************************************/
	@ResponseBody
	@RequestMapping("/tcoursesite/deleteCustomScore")
	public ModelAndView deleteCustomScore(@RequestParam Integer tCourseSiteId,Integer customScoreId,Integer currpage) {
		ModelAndView mav = new ModelAndView();
		//根据主键查找自定义成绩册tGradeObject
		TGradeObject tGradeObject = tGradeObjectDAO.findTGradeObjectByPrimaryKey(customScoreId);
		//根据查找出来的tGradeObject删除对应的自定义成绩所有内容，包括tGradebook/tGradeObject/tGradeRecord/tWeightSetting四张表的内容
		tGradebookService.deleteCustomScore(tGradeObject,tCourseSiteId);
		//返回成绩首页
		mav.setViewName("redirect:/tcoursesite/customAchievement?tCourseSiteId="+tCourseSiteId+"&type=custom&currpage="+currpage);
		return mav;
	}
	
	/**************************************************************************
	 * Description:课程站点-查看成绩册--作业成绩
	 * 
	 * @author：裴继超
	 * @date ：2016年6月16日9:41:47
	 **************************************************************************/
	@SystemServiceLog("查看成绩册--作业成绩")
	@ResponseBody
	@RequestMapping("/tcoursesite/assignmentAchievement")
	public ModelAndView assignmentAchievement(@RequestParam Integer tCourseSiteId,Integer currpage,String type,HttpSession httpSession) {
		ModelAndView mav = new ModelAndView();
		if(currpage ==null) {//若为空自动跳到第一页
			currpage =1;
		}
		//类别：assignment作业，exam测试，test考试，attendence考勤，custom自定义成绩，sum总成绩，weight成绩权重，customGrade自定义
		mav.addObject("type", type);
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
		//每页40条数据，总页数
		int pageSize =40;
		int totalRecords =tCourseSiteService.findTCourseSiteStudents(tCourseSiteId).size();
	    Map<String, Integer> pageModel = shareService.getPage(currpage, pageSize, totalRecords);
		//分页查询课程所属学生列表
		List<TCourseSiteUser> tCourseSiteUsers = tCourseSiteService.findTCourseSiteStudentsWithMore(tCourseSiteId,currpage,pageSize);   
		mav.addObject("pageModel", pageModel);
		mav.addObject("page", currpage);
		mav.addObject("totalRecords", totalRecords);
		mav.addObject("tCourseSiteUsers",tCourseSiteUsers);
		//查找是否存在自定义成绩
		List<TGradeObject> customTGradeObjects = tGradebookService.findCustomTGradeObjectsByTCourseSite(tCourseSiteId,"custom");
		if(customTGradeObjects !=null && customTGradeObjects.size() !=0) {
			//参数customFlag用以判断是否存在自定义成绩
			mav.addObject("customFlag",true);
		}
		//作业科目列表
		List<TGradeObject> tAssignmentGradeObjects = tGradebookService.findTGradeObjectsByTCourseSiteAndType(tCourseSiteId,"assignment");  
		//由于实验报告的type也存的是assignment，因此姑且由名字差异（实验报告都有“实验报告”四个字）去除实验报告（因为数据已经推到学校了），但由此会导致知识-作业的名字不能设置为*某某实验报告等等*
		List<TGradeObject> tAssignmentGradeObjectsWithoutReport = new ArrayList<TGradeObject>();
		for(TGradeObject tAssignmentGradeObject : tAssignmentGradeObjects) {
			String reportKey = "实验报告";
			if(!tAssignmentGradeObject.toString().contains(reportKey)) {
				tAssignmentGradeObjectsWithoutReport.add(tAssignmentGradeObject);
			}
		}
		mav.addObject("tAssignmentGradeObjects",tAssignmentGradeObjectsWithoutReport);
		//根据科目和学生查看成绩列表
		List<List<Object>> assignmentLists = tGradebookService.findStudentScoreRecords(tCourseSiteUsers,tAssignmentGradeObjectsWithoutReport);
		mav.addObject("assignmentLists", assignmentLists);
		//权重设置
		List<List<Object>> assignmentList = tGradebookService.findWeightSetting(tCourseSiteId,"assignment");
		mav.addObject("assignmentList", assignmentList);
		mav.setViewName("tcoursesite/assignmentAchievement.jsp");
		return mav;
	}
	
	/**************************************************************************
	 * Description:课程站点-查看成绩册--测试成绩
	 * 
	 * @author：裴继超
	 * @date ：2016年6月16日9:41:47
	 **************************************************************************/
	@SystemServiceLog("查看成绩册--测试成绩")
	@ResponseBody
	@RequestMapping("/tcoursesite/examAchievement")
	public ModelAndView examAchievement(@RequestParam Integer tCourseSiteId,Integer currpage,String type,HttpSession httpSession) {
		ModelAndView mav = new ModelAndView();
		if(currpage ==null) {//若为空自动跳到第一页
			currpage =1;
		}
		//类别：assignment作业，exam测试，test考试，attendence考勤，custom自定义成绩，sum总成绩，weight成绩权重，customGrade自定义
		mav.addObject("type", type);
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
		//每页40条数据，总页数
	    int pageSize =40;
	    int totalRecords =tCourseSiteService.findTCourseSiteStudents(tCourseSiteId).size();
	    Map<String, Integer> pageModel = shareService.getPage(currpage, pageSize, totalRecords);
		//分页查询课程所属学生列表
		List<TCourseSiteUser> tCourseSiteUsers = tCourseSiteService.findTCourseSiteStudentsWithMore(tCourseSiteId,currpage,pageSize);   
		mav.addObject("pageModel", pageModel);
		mav.addObject("page", currpage);
		mav.addObject("totalRecords", totalRecords);
		mav.addObject("tCourseSiteUsers",tCourseSiteUsers);
		List<TGradeObject> customTGradeObjects = tGradebookService.findCustomTGradeObjectsByTCourseSite(tCourseSiteId,"custom");
		if(customTGradeObjects !=null && customTGradeObjects.size() !=0) {
			//参数customFlag用以判断是否存在自定义成绩
			mav.addObject("customFlag",true);
		}
		//测试科目列表
		List<TGradeObject> tExamGradeObjects = tGradebookService.findTGradeObjectsByTCourseSiteAndType(tCourseSiteId,"exam");      
		mav.addObject("tExamGradeObjects",tExamGradeObjects);
		//根据科目和学生查看成绩列表
		List<List<Object>> examLists = tGradebookService.findStudentScoreRecords(tCourseSiteUsers,tExamGradeObjects);
		mav.addObject("examLists", examLists);
		//权重设置
		List<List<Object>> examList = tGradebookService.findWeightSetting(tCourseSiteId,"exam");
		mav.addObject("examList", examList);
		mav.setViewName("tcoursesite/examAchievement.jsp");
		return mav;
	}
	
	/**************************************************************************
	 * Description:课程站点-查看成绩册--考试成绩
	 * 
	 * @author：裴继超
	 * @date ：2016年6月16日9:41:47
	 **************************************************************************/
	@SystemServiceLog("查看成绩册--考试成绩")
	@ResponseBody
	@RequestMapping("/tcoursesite/testAchievement")
	public ModelAndView testAchievement(@RequestParam Integer tCourseSiteId,Integer currpage,String type,HttpSession httpSession) {
		ModelAndView mav = new ModelAndView();
		if(currpage ==null) {//若为空自动跳到第一页
			currpage =1;
		}
		//类别：assignment作业，exam测试，test考试，attendence考勤，custom自定义成绩，sum总成绩，weight成绩权重，customGrade自定义
		mav.addObject("type", type);
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
		//每页40条数据，总页数
	    int pageSize =40;
	    int totalRecords =tCourseSiteService.findTCourseSiteStudents(tCourseSiteId).size();
	    Map<String, Integer> pageModel = shareService.getPage(currpage, pageSize, totalRecords);
		//分页查询课程所属学生列表
		List<TCourseSiteUser> tCourseSiteUsers = tCourseSiteService.findTCourseSiteStudentsWithMore(tCourseSiteId,currpage,pageSize);   
		mav.addObject("pageModel", pageModel);
		mav.addObject("page", currpage);
		mav.addObject("totalRecords", totalRecords);
		mav.addObject("tCourseSiteUsers",tCourseSiteUsers);
		List<TGradeObject> customTGradeObjects = tGradebookService.findCustomTGradeObjectsByTCourseSite(tCourseSiteId,"custom");
		if(customTGradeObjects !=null && customTGradeObjects.size() !=0) {
			//参数customFlag用以判断是否存在自定义成绩
			mav.addObject("customFlag",true);
		}
		//考试科目列表
		List<TGradeObject> testGradeObjects = tGradebookService.findTGradeObjectsByTCourseSiteAndType(tCourseSiteId,"test");      
		mav.addObject("testGradeObjects",testGradeObjects);
		//根据科目和学生查看成绩列表
		List<List<Object>> testLists = tGradebookService.findStudentScoreRecords(tCourseSiteUsers,testGradeObjects);
		mav.addObject("testLists", testLists);
		//权重设置
		List<List<Object>> testList = tGradebookService.findWeightSetting(tCourseSiteId,"test");
		mav.addObject("testList", testList);
		mav.setViewName("tcoursesite/testAchievement.jsp");
		return mav;
	}
	
	/**************************************************************************
	 * Description:课程站点-查看成绩册-考勤成绩
	 * 
	 * @author：裴继超
	 * @date ：2016年6月16日9:41:47
	 **************************************************************************/
	@SystemServiceLog("查看成绩册-考勤成绩")
	@ResponseBody
	@RequestMapping("/tcoursesite/attendenceAchievement")
	public ModelAndView attendenceAchievement(@RequestParam Integer tCourseSiteId,Integer currpage,String type,HttpSession httpSession) {
		ModelAndView mav = new ModelAndView();
		if(currpage ==null) {//若为空自动跳到第一页
			currpage =1;
		}
		//类别：assignment作业，exam测试，test考试，attendence考勤，custom自定义成绩，sum总成绩，weight成绩权重，customGrade自定义
		mav.addObject("type", type);
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
		//每页40条数据，总页数
	    int pageSize =40;
	    int totalRecords =tCourseSiteService.findTCourseSiteStudents(tCourseSiteId).size();
	    Map<String, Integer> pageModel = shareService.getPage(currpage, pageSize, totalRecords);
		//分页查询课程所属学生列表
		List<TCourseSiteUser> tCourseSiteUsers = tCourseSiteService.findTCourseSiteStudentsWithMore(tCourseSiteId,currpage,pageSize);   
		mav.addObject("pageModel", pageModel);
		mav.addObject("page", currpage);
		mav.addObject("totalRecords", totalRecords);
		mav.addObject("tCourseSiteUsers",tCourseSiteUsers);
		List<TGradeObject> customTGradeObjects = tGradebookService.findCustomTGradeObjectsByTCourseSite(tCourseSiteId,"custom");
		if(customTGradeObjects !=null && customTGradeObjects.size() !=0) {
			//参数customFlag用以判断是否存在自定义成绩
			mav.addObject("customFlag",true);
		}
		//考勤列表
		List<TGradeObject> attendenceGradeObjects = tGradebookService.findTGradeObjectsByTCourseSiteAndType(tCourseSiteId,"attendence");      
		mav.addObject("attendenceGradeObjects",attendenceGradeObjects);
		//根据科目和学生查看成绩列表
		List<List<Object>> attendenceLists = tGradebookService.findStudentScoreRecords(tCourseSiteUsers,attendenceGradeObjects);
		mav.addObject("attendenceLists", attendenceLists);
		//权重设置
		List<List<Object>> attendenceList = tGradebookService.findWeightSetting(tCourseSiteId,"attendence");
		mav.addObject("attendenceList", attendenceList);
		mav.setViewName("tcoursesite/attendenceAchievement.jsp");
		return mav;
	}
	
	/**************************************************************************
	 * Description:课程站点-查看成绩册-实验成绩
	 * 
	 * @author：裴继超
	 * @date ：2016年6月16日9:41:47
	 **************************************************************************/
	@SystemServiceLog("查看成绩册-实验成绩")
	@ResponseBody
	@RequestMapping("/tcoursesite/experimentAchievement")
	public ModelAndView experimentAchievement(@RequestParam Integer tCourseSiteId,Integer currpage,String type,HttpSession httpSession) {
		ModelAndView mav = new ModelAndView();
		if(currpage ==null) {//若为空自动跳到第一页
			currpage =1;
		}
		//选择的课程中心
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
		
		//当前进入的url
		mav.addObject("url", "gradeAllExperimentSkill");
		//查看站点下的所有实验技能
		List<TExperimentSkill> tExperimentSkills = tExperimentSkillService.findTExperimentSkillListBySiteId(tCourseSiteId, 1,-1,"skill");
		mav.addObject("tExperimentSkills",tExperimentSkills);
		//实验技能完成情况列表
		List<Integer> skillDoList = tExperimentSkillService.findTExperimentSkillStringList(tCourseSiteId);
		mav.addObject("skillDoList",skillDoList);
		//每页40条数据，总页数
	    int pageSize =40;
	    int totalRecords =tCourseSiteService.findTCourseSiteStudents(tCourseSiteId).size();
	    Map<String, Integer> pageModel = shareService.getPage(currpage, pageSize, totalRecords);
		//分页查询课程所属学生列表
		List<TCourseSiteUser> tCourseSiteUsers = tCourseSiteService.findTCourseSiteStudentsWithMore(tCourseSiteId,currpage,pageSize);   
		mav.addObject("pageModel", pageModel);
		mav.addObject("page", currpage);
		mav.addObject("totalRecords", totalRecords);
		mav.addObject("tCourseSiteUsers",tCourseSiteUsers);
		List<TGradeObject> customTGradeObjects = tGradebookService.findCustomTGradeObjectsByTCourseSite(tCourseSiteId,"custom");
		if(customTGradeObjects !=null && customTGradeObjects.size() !=0) {
			//参数customFlag用以判断是否存在自定义成绩
			mav.addObject("customFlag",true);
		}
		//查看实验总成绩列表
		List<List<Object>> sumLists = tExperimentSkillService.gradeAllExperimentSkill(tCourseSiteUsers,tCourseSiteId);
		mav.addObject("sumLists", sumLists);
		
		mav.setViewName("tcoursesite/experimentAchievement.jsp");
		return mav;
	}
	
	/**************************************************************************
	 * Description:课程站点-查看成绩册-自定义成绩
	 * 
	 * @author：裴继超
	 * @date ：2016年6月16日9:41:47
	 **************************************************************************/
	@SystemServiceLog("查看成绩册-自定义成绩")
	@ResponseBody
	@RequestMapping("/tcoursesite/customAchievement")
	public ModelAndView customAchievement(@RequestParam Integer tCourseSiteId,Integer currpage,String type,HttpSession httpSession) {
		ModelAndView mav = new ModelAndView();
		if(currpage ==null) {//若为空自动跳到第一页
			currpage =1;
		}
		//类别：assignment作业，exam测试，test考试，attendence考勤，custom自定义成绩，sum总成绩，weight成绩权重，customGrade自定义
		mav.addObject("type", type);
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
		//每页40条数据，总页数
	    int pageSize =40;
	    int totalRecords =tCourseSiteService.findTCourseSiteStudents(tCourseSiteId).size();
	    Map<String, Integer> pageModel = shareService.getPage(currpage, pageSize, totalRecords);
		//分页查询课程所属学生列表
		List<TCourseSiteUser> tCourseSiteUsers = tCourseSiteService.findTCourseSiteStudentsWithMore(tCourseSiteId,currpage,pageSize);   
		mav.addObject("pageModel", pageModel);
		mav.addObject("page", currpage);
		mav.addObject("totalRecords", totalRecords);
		mav.addObject("tCourseSiteUsers",tCourseSiteUsers);
		//查询自定义成绩列表，首先判断本课程站点下是否存在自定义成绩再查询成绩列表
		List<TGradeObject> customTGradeObjects = tGradebookService.findCustomTGradeObjectsByTCourseSite(tCourseSiteId,"custom");
		if(customTGradeObjects !=null && customTGradeObjects.size() !=0){
			
			//参数customFlag用以判断是否存在自定义成绩
			mav.addObject("customFlag",true);
			mav.addObject("customTGradeObjects", customTGradeObjects);
			//根据自定义科目和学生查看成绩列表
			List<List<Object>> customLists = tGradebookService.findCustomStudentScoreRecords(tCourseSiteUsers,customTGradeObjects);
			mav.addObject("customLists", customLists);
			//自定义成绩模块个数
			int customTGradeObjectsSize = customTGradeObjects.size();
			mav.addObject("customTGradeObjectsSize", customTGradeObjectsSize);
			//查询对应课程的总评权重设置情况
			List<TWeightSetting> weightSettings = tGradebookService.findWeightSettingsWithCustom(tCourseSiteId,customTGradeObjectsSize,customTGradeObjects);
			mav.addObject("weightSettings", weightSettings);
			
		}else{
			//自定义成绩模块个数
			int customTGradeObjectsSize = 0;
			mav.addObject("customTGradeObjectsSize", customTGradeObjectsSize);
			//查询对应课程的总评权重设置情况
			List<TWeightSetting> weightSettings = tGradebookService.findWeightSettings(tCourseSiteId);
			mav.addObject("weightSettings", weightSettings);
			//若自定义成绩为空则返回作业成绩首页
			mav.setViewName("redirect:/tcoursesite/assignmentAchievement?tCourseSiteId="+tCourseSiteId+"&type=assignment&currpage=1");
			return mav;
		}
		mav.setViewName("tcoursesite/customAchievement.jsp");
		return mav;
	}
	
	/**************************************************************************
	 * Description:课程站点-查看成绩册-总成绩
	 * 
	 * @author：裴继超
	 * @date ：2016年6月16日9:41:47
	 **************************************************************************/
	@SystemServiceLog("查看成绩册-总成绩")
	@ResponseBody
	@RequestMapping("/tcoursesite/sumAchievement")
	public ModelAndView sumAchievement(@RequestParam Integer tCourseSiteId,Integer currpage,String type,HttpSession httpSession) {
		ModelAndView mav = new ModelAndView();
		if(currpage ==null) {//若为空自动跳到第一页
			currpage =1;
		}
		//类别：assignment作业，exam测试，test考试，attendence考勤，custom自定义成绩，sum总成绩，weight成绩权重，customGrade自定义
		mav.addObject("type", type);
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
		//每页40条数据，总页数
	    int pageSize =40;
	    int totalRecords =tCourseSiteService.findTCourseSiteStudents(tCourseSiteId).size();
	    Map<String, Integer> pageModel = shareService.getPage(currpage, pageSize, totalRecords);
		//分页查询课程所属学生列表
		List<TCourseSiteUser> tCourseSiteUsers = tCourseSiteService.findTCourseSiteStudentsWithMore(tCourseSiteId,currpage,pageSize);   
		mav.addObject("pageModel", pageModel);
		mav.addObject("page", currpage);
		mav.addObject("totalRecords", totalRecords);
		mav.addObject("tCourseSiteUsers",tCourseSiteUsers);
		//查询自定义成绩列表，首先判断本课程站点下是否存在自定义成绩再查询成绩列表
		List<TGradeObject> customTGradeObjects = tGradebookService.findCustomTGradeObjectsByTCourseSite(tCourseSiteId,"custom");
		if(customTGradeObjects !=null && customTGradeObjects.size() !=0){
			
			//参数customFlag用以判断是否存在自定义成绩
			mav.addObject("customFlag",true);
			mav.addObject("customTGradeObjects", customTGradeObjects);
			//根据自定义科目和学生查看成绩列表
			List<List<Object>> customLists = tGradebookService.findCustomStudentScoreRecords(tCourseSiteUsers,customTGradeObjects);
			mav.addObject("customLists", customLists);
			//自定义成绩模块个数
			int customTGradeObjectsSize = customTGradeObjects.size();
			mav.addObject("customTGradeObjectsSize", customTGradeObjectsSize);
			//查询对应课程的总评权重设置情况
			List<TWeightSetting> weightSettings = tGradebookService.findWeightSettingsWithCustom(tCourseSiteId,customTGradeObjectsSize,customTGradeObjects);
			mav.addObject("weightSettings", weightSettings);
			//查看总成绩列表(带自定义成绩)
			List<List<Object>> sumLists = tGradebookService.findTotalScoreInfoWithCustom(tCourseSiteUsers,weightSettings,tCourseSiteId,customTGradeObjectsSize,customLists);
			mav.addObject("sumLists", sumLists);
		}else{
			
			//查询对应课程的总评权重设置情况
			List<TWeightSetting> weightSettings = tGradebookService.findWeightSettings(tCourseSiteId);
			mav.addObject("weightSettings", weightSettings);
			//自定义成绩模块个数
			int customTGradeObjectsSize = 0;
			mav.addObject("customTGradeObjectsSize", customTGradeObjectsSize);
			//查看总成绩列表(不带自定义成绩)
			List<List<Object>> sumLists = tGradebookService.findTotalScoreInfo(tCourseSiteUsers,weightSettings,tCourseSiteId);
			mav.addObject("sumLists", sumLists);
		}
		mav.setViewName("tcoursesite/sumAchievement.jsp");
		return mav;
	}
	
	/**************************************************************************
	 * Description:课程站点-查看成绩册-成绩权重设置
	 * 
	 * @author：裴继超
	 * @date ：2016年6月16日9:41:47
	 **************************************************************************/
	@SystemServiceLog("查看成绩册-成绩权重设置")
	@ResponseBody
	@RequestMapping("/tcoursesite/weightAchievement")
	public ModelAndView weightAchievement(@RequestParam Integer tCourseSiteId,String type,HttpSession httpSession) {
		ModelAndView mav = new ModelAndView();
		//类别：assignment作业，exam测试，test考试，attendence考勤，custom自定义成绩，sum总成绩，weight成绩权重，customGrade自定义
		mav.addObject("type", type);
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
		//查询自定义成绩列表，首先判断本课程站点下是否存在自定义成绩再查询成绩列表
		List<TGradeObject> customTGradeObjects = tGradebookService.findCustomTGradeObjectsByTCourseSite(tCourseSiteId,"custom");
		if(customTGradeObjects !=null && customTGradeObjects.size() !=0){
			
			//参数customFlag用以判断是否存在自定义成绩
			mav.addObject("customFlag",true);
			mav.addObject("customTGradeObjects", customTGradeObjects);
			//自定义成绩模块个数
			int customTGradeObjectsSize = customTGradeObjects.size();
			mav.addObject("customTGradeObjectsSize", customTGradeObjectsSize);
			//查询对应课程的总评权重设置情况
			List<TWeightSetting> weightSettings = tGradebookService.findWeightSettingsWithCustom(tCourseSiteId,customTGradeObjectsSize,customTGradeObjects);
			mav.addObject("weightSettings", weightSettings);
		}else{
			
			//查询对应课程的总评权重设置情况
			List<TWeightSetting> weightSettings = tGradebookService.findWeightSettings(tCourseSiteId);
			mav.addObject("weightSettings", weightSettings);
		}
		
		//权重设置
		List<List<Object>> assignmentList = tGradebookService.findWeightSetting(tCourseSiteId,"assignment");
		
		//由于实验报告的type也存的是assignment，因此姑且由名字差异（实验报告都有“实验报告”四个字）去除实验报告（因为数据已经推到学校了），但由此会导致知识-作业的名字不能设置为*某某实验报告等等*
		List<List<Object>> assignmentListWithoutReport = new ArrayList<List<Object>>();
		for(List<Object> assignmentObject : assignmentList) {
			String reportKey = "实验报告";
			if(!assignmentObject.toString().contains(reportKey)) {
				assignmentListWithoutReport.add(assignmentObject);
			}
		}
		mav.addObject("assignmentList", assignmentListWithoutReport);
		List<List<Object>> examList = tGradebookService.findWeightSetting(tCourseSiteId,"exam");
		mav.addObject("examList", examList);
		List<List<Object>> testList = tGradebookService.findWeightSetting(tCourseSiteId,"test");
		mav.addObject("testList", testList);
		List<List<Object>> attendenceList = tGradebookService.findWeightSetting(tCourseSiteId,"attendence");
		mav.addObject("attendenceList", attendenceList);
		//实验项目权重设置
		List<List<Object>> weightWithSkill = tExperimentSkillService.getWeightWithSkill(tCourseSiteId);
		mav.addObject("weightWithSkill",weightWithSkill);
		mav.setViewName("tcoursesite/weightAchievement.jsp");
		return mav;
	}
	
	/**************************************************************************
	 * Description:课程站点-自定义成绩
	 * 
	 * @author：裴继超
	 * @date ：2016年6月16日9:41:47
	 **************************************************************************/
	@SystemServiceLog("自定义成绩")
	@ResponseBody
	@RequestMapping("/tcoursesite/customGrade")
	public ModelAndView customGrade(@RequestParam Integer tCourseSiteId,String type,Integer currpage,HttpSession httpSession) {
		ModelAndView mav = new ModelAndView();
		if(currpage ==null) {//若为空自动跳到第一页
			currpage =1;
		}
		//类别：assignment作业，exam测试，test考试，attendence考勤，custom自定义成绩，sum总成绩，weight成绩权重，customGrade自定义
		mav.addObject("type", type);
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
		//每页40条数据，总页数
	    int pageSize =40;
	    int totalRecords =tCourseSiteService.findTCourseSiteStudents(tCourseSiteId).size();
	    Map<String, Integer> pageModel = shareService.getPage(currpage, pageSize, totalRecords);
		//分页查询课程所属学生列表
		List<TCourseSiteUser> tCourseSiteUsers = tCourseSiteService.findTCourseSiteStudentsWithMore(tCourseSiteId,currpage,pageSize);   
		mav.addObject("pageModel", pageModel);
		mav.addObject("page", currpage);
		mav.addObject("totalRecords", totalRecords);
		mav.addObject("tCourseSiteUsers",tCourseSiteUsers);
		List<TGradeObject> customTGradeObjects = tGradebookService.findCustomTGradeObjectsByTCourseSite(tCourseSiteId,"custom");
		if(customTGradeObjects !=null && customTGradeObjects.size() !=0) {
			//参数customFlag用以判断是否存在自定义成绩
			mav.addObject("customFlag",true);
		}
		mav.setViewName("tcoursesite/customGrade.jsp");
		return mav;
	}
	
	/**************************************************************************
	 * Description:实验项目权重设置
	 * 
	 * @author：张佳鸣
	 * @date ：2017-12-08
	 **************************************************************************/
	@SystemServiceLog("权重设置")
	@ResponseBody
	@RequestMapping("/tcoursesite/gradeBook/experimentWeightSetting")
	public ModelAndView experimentWeightSetting(HttpServletRequest request,@RequestParam Integer tCourseSiteId) {
		ModelAndView mav = new ModelAndView();
		//权重设置
		tGradebookService.experimentWeightSetting(request,tCourseSiteId);
		mav.setViewName("redirect:/tcoursesite/weightAchievement?tCourseSiteId="+tCourseSiteId+"&type=weight");
		return mav;
	}
}