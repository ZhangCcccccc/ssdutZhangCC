/****************************************************************************
 * 命名规范：
 * 本控制层调整必须是按照如下的路径调整：@RequestMapping("/tCourseSite/xxxx")。
 * 列表信息listxxxx，獲取信息：getxxxx 编辑信息：editxxxx 删除信息：detelexxxx 新增信息 newxxxx
 * 导出信息exportxxxx 保存信息：savexxxx 
 ****************************************************************************/

package net.xidlims.web.tcoursesite;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

import net.xidlims.dao.TExperimentLabDeviceDAO;
import net.xidlims.dao.TExperimentLabRoomDAO;
import net.xidlims.dao.TGradeObjectDAO;
import net.xidlims.dao.TWeightSettingDAO;
import net.xidlims.dao.WkFolderDAO;
import net.xidlims.dao.WkUploadDAO;
import net.xidlims.domain.LabRoom;
import net.xidlims.domain.LabRoomDevice;
import net.xidlims.domain.TAssignment;
import net.xidlims.domain.TAssignmentAnswerAssign;
import net.xidlims.domain.TAssignmentControl;
import net.xidlims.domain.TAssignmentGrading;
import net.xidlims.domain.TAssignmentItem;
import net.xidlims.domain.TAssignmentItemMapping;
import net.xidlims.domain.TAssignmentSection;
import net.xidlims.domain.TCourseSite;
import net.xidlims.domain.TCourseSiteUser;
import net.xidlims.domain.TDiscuss;
import net.xidlims.domain.TExperimentLabDevice;
import net.xidlims.domain.TExperimentLabRoom;
import net.xidlims.domain.TExperimentSkill;
import net.xidlims.domain.TExperimentSkillUser;
import net.xidlims.domain.TGradeObject;
import net.xidlims.domain.TWeightSetting;
import net.xidlims.domain.User;
import net.xidlims.domain.WkChapter;
import net.xidlims.domain.WkFolder;
import net.xidlims.domain.WkUpload;
import net.xidlims.service.common.ShareService;
import net.xidlims.service.device.LabRoomDeviceService;
import net.xidlims.service.lab.LabCenterService;
import net.xidlims.service.lab.LabRoomService;
import net.xidlims.service.tcoursesite.NetDiskService;
import net.xidlims.service.tcoursesite.TAssignmentItemService;
import net.xidlims.service.tcoursesite.TAssignmentQuestionPoolService;
import net.xidlims.service.tcoursesite.TAssignmentService;
import net.xidlims.service.tcoursesite.TCourseSiteArticalService;
import net.xidlims.service.tcoursesite.TCourseSiteChannelService;
import net.xidlims.service.tcoursesite.TCourseSiteScheduleService;
import net.xidlims.service.tcoursesite.TCourseSiteService;
import net.xidlims.service.tcoursesite.TCourseSiteUserService;
import net.xidlims.service.tcoursesite.TDiscussService;
import net.xidlims.service.tcoursesite.TExperimentSkillService;
import net.xidlims.service.tcoursesite.TGradebookService;
import net.xidlims.service.tcoursesite.WkChapterService;
import net.xidlims.service.tcoursesite.WkFolderService;
import net.xidlims.service.tcoursesite.WkLessonService;
import net.xidlims.service.tcoursesite.WkService;
import net.xidlims.service.tcoursesite.WkUploadService;
import net.xidlims.service.teaching.TAssignmentForExamService;
import net.xidlims.service.teaching.TAssignmentGradingService;
import net.xidlims.service.teaching.TAssignmentSectionService;
import net.xidlims.view.ViewTAssignment;
import net.xidlims.web.aop.SystemServiceLog;

/**************************************************************************
 * Description:实验技能模块
 * 
 * @author：裴继超
 * @date ：2016-9-18
 **************************************************************************/
@Controller("TExperimentSkillController")
@SessionAttributes("selected_courseSite")
public class TExperimentSkillController<JsonResult> {
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
	private TCourseSiteScheduleService tCourseSiteScheduleService;
	@Autowired
	private TCourseSiteUserService tCourseSiteUserService;
	@Autowired
	private TExperimentSkillService tExperimentSkillService;
	@Autowired
	private LabRoomService labRoomService;
	@Autowired
	private TDiscussService tDiscussService;
	@Autowired
	private TGradebookService tGradebookService;
	@Autowired
	private TAssignmentGradingService tAssignmentGradingService;
	@Autowired
	private TAssignmentService tAssignmentService;
	@Autowired
	private NetDiskService netDiskService;
	@Autowired
	private TGradeObjectDAO tGradeObjectDAO;
	@Autowired
	private TExperimentLabRoomDAO tExperimentLabRoomDAO;
	@Autowired
	private TExperimentLabDeviceDAO tExperimentLabDeviceDAO;
	@Autowired
	private WkFolderDAO wkFolderDAO;
	@Autowired
	private WkUploadDAO wkUploadDAO;
	@Autowired
	private LabRoomDeviceService labRoomDeviceService;
	@Autowired
	private TAssignmentSectionService tAssignmentSectionService;
	@Autowired
	private TAssignmentItemService tAssignmentItemService;
	@Autowired
	private TWeightSettingDAO tWeightSettingDAO;
	@Autowired
	private TAssignmentForExamService tAssignmentForExamService;
	
	
	/**************************************************************************
	 * Description:实验技能-查看实验技能列表
	 *  
	 * @author：裴继超
	 * @date ：2016-06-16
	 **************************************************************************/
	@SystemServiceLog("查看实验技能列表")
	@ResponseBody
	@RequestMapping("/tcoursesite/skill/experimentSkillsList")
	public ModelAndView experimentSkillsList(@RequestParam Integer tCourseSiteId,HttpSession httpSession) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("selected_courseSite", tCourseSiteId);
		// 选择的课程中心
		TCourseSite tCourseSite = tCourseSiteService.findCourseSiteById(tCourseSiteId);
		mav.addObject("tCourseSite", tCourseSite);
		mav.addObject("moduleType", 2);
		mav.addObject("selectId", -1);
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
				flag=1; //如果当前登陆人参与这门课，则是老师身份
			}else {
				flag = 0;//如果当前登陆人不是本人开的课程，则是学生身份
			}
			
		}else if(user!=null&&user.getAuthorities().toString().contains("STUDENT")){
			flag = 0;
		}
		mav.addObject("flag", flag);
		//查看站点下的所有实验技能
		List<TExperimentSkill> tExperimentSkills = tExperimentSkillService.findTExperimentSkillListBySiteId(tCourseSiteId, 1,-1,"skill");
		mav.addObject("tExperimentSkills",tExperimentSkills);
		//实验技能完成情况列表
		List<Integer> skillDoList = tExperimentSkillService.findTExperimentSkillStringList(tCourseSiteId);
		mav.addObject("skillDoList",skillDoList);
		
		mav.setViewName("tcoursesite/skill/experimentSkillsList.jsp");
		return mav;
	}
	
	/**************************************************************************
	 * Description:实验技能-新建实验技能
	 *  
	 * @author：裴继超
	 * @date ：2016-06-16
	 **************************************************************************/
	@SystemServiceLog("新建实验技能")
	@ResponseBody
	@RequestMapping("/tcoursesite/skill/newExperimentSkill")
	public ModelAndView newExperimentSkill(@RequestParam Integer tCourseSiteId,Integer id,HttpSession httpSession,HttpServletRequest request) {
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
		mav.addObject("flag", flag);
		//查看站点下的所有实验技能
		List<TExperimentSkill> tExperimentSkills = tExperimentSkillService.findTExperimentSkillListBySiteId(tCourseSiteId, 1,-1,"skill");
		mav.addObject("tExperimentSkills",tExperimentSkills);
		//new一个空对象
		TExperimentSkill tExperimentSkill = new TExperimentSkill();
		if(id!=-1){
			tExperimentSkill = tExperimentSkillService.findTExperimentSkillByPrimaryKey(id);
			//时间格式设置
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			mav.addObject("startdate",sdf.format(tExperimentSkill.getStartdate().getTime()));
			mav.addObject("duedate",sdf.format(tExperimentSkill.getDuedate().getTime()));
			TAssignment tAssignment = tExperimentSkillService.findReportTAssignmentBySkill(tExperimentSkill);
			mav.addObject("toGradebook",tAssignment.getTAssignmentControl().getToGradebook());
			mav.addObject("editFlag",true);
		}else{
			//初始化默认时间
			Calendar duedate = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			mav.addObject("startdate",sdf.format(duedate.getTime()));
			mav.addObject("duedate",sdf.format(duedate.getTime()));
			mav.addObject("toGradebook","yes");
		}
		mav.addObject("tExperimentSkill",tExperimentSkill);
		
		Integer skillId = tExperimentSkill.getId();
		if(skillId!=null){
			//实验指导书
			List<WkUpload> quideList = tExperimentSkillService.findWkUploadBySkillIdAndType(skillId, 201);
			String quides = "";
			//id list
			String quideIdList = "";
			for (WkUpload quide : quideList) {
				quideIdList += quide.getId()+",";
				quides += "<li class='pic_list hg9 lh35 ptb5 rel ovh'  id='document"+quide.getId()+"'>" +
				"<div class='cf rel zx1 z c_category'>" +
				"<div class=''l mlr15 cc1 c_tool poi'>" + quide.getName() + 
				"<i class='r fa fa-trash-o g9 f14 mr5 poi' onclick=\"deleteThisDocument('experimentalQuide',"+quide.getId()+")\"></i>"+
//				"<a class=\"r fa fa-download g9 f14 mr5 poi\" href=\""+request.getContextPath()+"/tcoursesite/downloadFile?id="+quide.getId()+"\"></a>"+
//				"<a class=\"r fa fa-eye g9 f14 mr5 poi\" href=\""+request.getContextPath()+"/tcoursesite/showFile?id="+quide.getId()+"\" target=\"_blank\"></a>"+
				"</div></div></li>";
			}
			mav.addObject("quides", quides);
			mav.addObject("quideIdList", quideIdList);
			//实验图片
			List<WkUpload> imageList = tExperimentSkillService.findWkUploadBySkillIdAndType(skillId, 202);
			String images = "";
			//id list
			String imageIdList = "";
			for (WkUpload image : imageList) {
				imageIdList += image.getId()+",";
				images += "<li class='pic_list hg9 lh35 ptb5 rel ovh'  id='document"+image.getId()+"'>" +
				"<div class='cf rel zx1 z c_category'>" +
				"<div class=''l mlr15 cc1 c_tool poi'>" + image.getName() + 
				"<i class='r fa fa-trash-o g9 f14 mr5 poi' onclick=\"deleteThisDocument('experimentalImage',"+image.getId()+")\"></i>"+
//				"<a class=\"r fa fa-download g9 f14 mr5 poi\" href=\""+request.getContextPath()+"/tcoursesite/downloadFile?id="+image.getId()+"\"></a>"+
//				"<i class=\"r fa fa-eye g9 f14 mr5 poi\" onclick=\"imageLook("+image.getId()+")\"></i>"+
				"</div></div></li>";
			}
			mav.addObject("images",images);
			mav.addObject("imageIdList",imageIdList);
			//实验视频
			List<WkUpload> videoList = tExperimentSkillService.findWkUploadBySkillIdAndType(skillId, 203);
			String videos = "";
			//id list
			String videoIdList = "";
			for (WkUpload video : videoList) {
				videoIdList += video.getId()+",";
				videos += "<li class='pic_list hg9 lh35 ptb5 rel ovh'  id='document"+video.getId()+"'>" +
				"<div class='cf rel zx1 z c_category'>" +
				"<div class=''l mlr15 cc1 c_tool poi'>" + video.getName() + 
				"<i class='r fa fa-trash-o g9 f14 mr5 poi' onclick=\"deleteThisDocument('experimentalVideo',"+video.getId()+")\"></i>"+
//				"<a class=\"r fa fa-download g9 f14 mr5 poi\" href=\""+request.getContextPath()+"/tcoursesite/downloadFile?id="+video.getId()+"\"></a>"+
//				"<i class=\"r fa fa-eye g9 f14 mr5 poi\" onclick=\"videoLook("+video.getId()+")\"></i>"+
				"</div></div></li>";
			}
			mav.addObject("videos",videos);
			mav.addObject("videoIdList",videoIdList);
			//实验工具
			List<WkUpload> toolList = tExperimentSkillService.findWkUploadBySkillIdAndType(skillId, 204);
			String tools = "";
			//id list
			String toolIdList = "";
			for (WkUpload tool : toolList) {
				toolIdList += tool.getId()+",";
				tools += "<li class='pic_list hg9 lh35 ptb5 rel ovh'  id='document"+tool.getId()+"'>" +
				"<div class='cf rel zx1 z c_category'>" +
				"<div class=''l mlr15 cc1 c_tool poi'>" + tool.getName() + 
				"<i class='r fa fa-trash-o g9 f14 mr5 poi' onclick=\"deleteThisDocument('experimentalTool',"+tool.getId()+")\"></i>"+
				"</div></div></li>";
			}
			mav.addObject("tools",tools);
			mav.addObject("toolIdList",toolIdList);
			//获取所属实验室
			List<TExperimentLabRoom> tExperimentLabRooms = tExperimentSkillService.getLabRoomList(skillId);
			//id list
			String labRoomIdList = "";
			for (TExperimentLabRoom tExperimentLabRoom : tExperimentLabRooms) {
				labRoomIdList += tExperimentLabRoom.getLabRoomId()+",";
			}
			mav.addObject("labRoomIdList",labRoomIdList);
			//获取所属实验室下所属设备
			List<LabRoomDevice> labDevices = new ArrayList<LabRoomDevice>();
			for (TExperimentLabDevice tExperimentLabDevice : tExperimentSkillService.getLabDeviceList(skillId)) {
				labDevices.add(labRoomDeviceService.findLabRoomDeviceByPrimaryKey(tExperimentLabDevice.getLabDeviceId()));
			}
			mav.addObject("labDevices",labDevices);
		}
		//实验技能完成情况列表
		List<Integer> skillDoList = tExperimentSkillService.findTExperimentSkillStringList(tCourseSiteId);
		mav.addObject("skillDoList",skillDoList);
		
		//获取所有实验室
		List<LabRoom> labRooms = labRoomService.findLabRoomByLabCenterid(null,1);
		mav.addObject("labRooms",labRooms);

		mav.setViewName("tcoursesite/skill/newExperimentSkill.jsp");
		return mav;
	}
	
	/**************************************************************************
	 * Description:实验技能-保存实验技能
	 * @throws ParseException 
	 *  
	 * @author：裴继超
	 * @date ：2016-10-9
	 **************************************************************************/
	@SystemServiceLog("保存实验技能")
	@ResponseBody
	@RequestMapping("/tcoursesite/skill/saveExperimentSkill")
	public ModelAndView saveExperimentSkill(@RequestParam Integer tCourseSiteId,
			@ModelAttribute TExperimentSkill tExperimentSkill,HttpSession httpSession,
			HttpServletRequest request) throws ParseException {
		ModelAndView mav = new ModelAndView();
		//当前登录人
		User user = shareService.getUser();
		mav.addObject("user", user);
		tExperimentSkill = tExperimentSkillService.saveTExperimentSkill(tExperimentSkill,request);
		
		//保存实验技能所属实验室
		tExperimentSkillService.saveExperimentLabRooms(tExperimentSkill.getId(), request);
		//保存实验技能的实验工具
		tExperimentSkillService.saveExperimentDevices(tExperimentSkill.getId(), request);
		//保存一个实验报告
		TAssignment tAssignment = tExperimentSkillService.saveReportTAssignment(tExperimentSkill, request);
		//根据实验报告创建成绩册
		tGradebookService.createGradebook(tCourseSiteId, tAssignment);
		//保存一个实验数据
		TAssignment tAssignmentData = tExperimentSkillService.saveDataTAssignment(tExperimentSkill, request);
		//根据实验数据创建成绩册
		tGradebookService.createGradebook(tCourseSiteId, tAssignmentData);
		//保存一个预习测试
		TAssignment tAssignmentPrepareExam = tExperimentSkillService.savePrepareExam(tExperimentSkill, request);
		//根据预习测试创建成绩册
		tGradebookService.createGradebook(tCourseSiteId, tAssignmentPrepareExam);
				
		
		mav.setViewName("redirect:/tcoursesite/skill/experimentSkillsList?tCourseSiteId="+tCourseSiteId);
		return mav;
	}
	
	/**************************************************************************
	 * Description:实验技能-保存实验技能概要
	 * @throws ParseException 
	 *  
	 * @author：裴继超
	 * @date ：2016-9-23
	 **************************************************************************/
	@SystemServiceLog("保存实验技能概要")
	@ResponseBody
	@RequestMapping("/tcoursesite/skill/saveExperimentSkillProfile")
	public ModelAndView saveExperimentSkillProfile(@RequestParam Integer tCourseSiteId,
			HttpSession httpSession,
			HttpServletRequest request) throws ParseException {
		ModelAndView mav = new ModelAndView();
		tExperimentSkillService.saveExperimentSkillProfile(tCourseSiteId,request);
		mav.setViewName("redirect:/tcoursesite/skill/experimentSkillsList?tCourseSiteId="+tCourseSiteId);
		return mav;
	}
	
	/**************************************************************************
	 * Description:实验技能-根据实验室获取实验工具json
	 * 
	 * @author：裴继超
	 * @date ：2016-9-13
	 **************************************************************************/
	@ResponseBody
	@RequestMapping("/tcoursesite/skill/findToolsJsonByLabRooms")
	public String findToolsJsonByLabRooms(@RequestParam String labRoomIds,
			HttpServletRequest request) throws UnsupportedEncodingException {
		System.out.println(labRoomIds);
		//根据实验室获取实验工具json
		if(labRoomIds!=null&&labRoomIds!=""){
			String result = tExperimentSkillService.findToolsJsonByLabRooms(labRoomIds);
			System.out.println(result);
			return shareService.htmlEncode(result);
		}else{
			System.out.println("false");
			return "false";
		}
	}
	
	/**************************************************************************
	 * Description:实验技能-查看实验内容
	 *  
	 * @author：裴继超
	 * @date ：2016-06-16
	 **************************************************************************/
	@SystemServiceLog("查看实验内容")
	@ResponseBody
	@RequestMapping("/tcoursesite/skill/viewExperimentSkill")
	public ModelAndView viewExperimentSkill(@RequestParam Integer tCourseSiteId,Integer skillId,
			HttpSession httpSession) {
		ModelAndView mav = new ModelAndView();
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
		//当前进入的url
		mav.addObject("url", "viewExperimentSkill");
		//查看站点下的所有实验技能
		List<TExperimentSkill> tExperimentSkills = tExperimentSkillService.findTExperimentSkillListBySiteId(tCourseSiteId, 1,-1,"skill");
		mav.addObject("tExperimentSkills",tExperimentSkills);
		//查看实验技能
		TExperimentSkill tExperimentSkill = tExperimentSkillService.findTExperimentSkillByPrimaryKey(skillId);
		mav.addObject("tExperimentSkill",tExperimentSkill);
		//实验技能完成情况列表
		List<Integer> skillDoList = tExperimentSkillService.findTExperimentSkillStringList(tCourseSiteId);
		mav.addObject("skillDoList",skillDoList);
		//实验技能所属实验室
		String labRoomNames =tExperimentSkillService.findExperimentLabRoomsString(skillId);
		mav.addObject("labRoomNames",labRoomNames);
		
		mav.setViewName("tcoursesite/skill/viewExperimentSkill.jsp");
		return mav;
	}
	
	/**************************************************************************
	 * Description:实验技能-查看实验指导
	 *  
	 * @author：裴继超
	 * @date ：2016-10-9
	 **************************************************************************/
	@SystemServiceLog("查看实验指导")
	@ResponseBody
	@RequestMapping("/tcoursesite/skill/coachExperimentSkill")
	public ModelAndView coachExperimentSkill(@RequestParam Integer tCourseSiteId,Integer skillId,
			HttpSession httpSession) {
		ModelAndView mav = new ModelAndView();
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
			}else if (tCourseSiteUserService.isUserBelongToTeacher(tCourseSiteId)){
				flag=1; //如果当前登陆人参与这门课，则是老师身份
			}else {
				flag = 0;//如果当前登陆人不是本人开的课程，则是学生身份
			}
			
		}else if(user!=null&&user.getAuthorities().toString().contains("STUDENT")){
			flag = 0;
		}
		mav.addObject("flag", flag);
		//当前进入的url
		mav.addObject("url", "coachExperimentSkill");
		//查看站点下的所有实验技能
		List<TExperimentSkill> tExperimentSkills = tExperimentSkillService.findTExperimentSkillListBySiteId(tCourseSiteId, 1,-1,"skill");
		mav.addObject("tExperimentSkills",tExperimentSkills);
		//查看实验技能
		TExperimentSkill tExperimentSkill = tExperimentSkillService.findTExperimentSkillByPrimaryKey(skillId);
		mav.addObject("tExperimentSkill",tExperimentSkill);
		//实验技能完成情况列表
		List<Integer> skillDoList = tExperimentSkillService.findTExperimentSkillStringList(tCourseSiteId);
		mav.addObject("skillDoList",skillDoList);
		//实验技能所属实验室
		String labRoomNames =tExperimentSkillService.findExperimentLabRoomsString(skillId);
		mav.addObject("labRoomNames",labRoomNames);
		
		//实验指导书
		List<WkUpload> quides = tExperimentSkillService.findWkUploadBySkillIdAndType(skillId, 201);
		mav.addObject("quides",quides);
		//实验图片
		List<WkUpload> images = tExperimentSkillService.findWkUploadBySkillIdAndType(skillId, 202);
		mav.addObject("images",images);
		//实验视频
		List<WkUpload> videos = tExperimentSkillService.findWkUploadBySkillIdAndType(skillId, 203);
		mav.addObject("videos",videos);
		//实验工具
		List<WkUpload> tools = tExperimentSkillService.findWkUploadBySkillIdAndType(skillId, 204);
		mav.addObject("tools",tools);
		//实验设备
		List<LabRoomDevice> devices = tExperimentSkillService.findDeviceBySkillId(skillId);
		mav.addObject("devices",devices);
		
		mav.setViewName("tcoursesite/skill/coachExperimentSkill.jsp");
		return mav;
	}
	
	/**************************************************************************
	 * Description:实验技能-查看实验报告
	 *  
	 * @author：裴继超
	 * @date ：2016-10-11
	 **************************************************************************/
	@SystemServiceLog("查看实验报告")
	@ResponseBody
	@RequestMapping("/tcoursesite/skill/reportExperimentSkill")
	public ModelAndView reportExperimentSkill(@RequestParam Integer tCourseSiteId,Integer skillId,
			HttpSession httpSession) {
		ModelAndView mav = new ModelAndView();
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
		//当前进入的url
		mav.addObject("url", "reportExperimentSkill");
		//查看站点下的所有实验技能
		List<TExperimentSkill> tExperimentSkills = tExperimentSkillService.
				findTExperimentSkillListBySiteId(tCourseSiteId, 1,-1,"skill");
		mav.addObject("tExperimentSkills",tExperimentSkills);
		//查看实验技能
		TExperimentSkill tExperimentSkill = tExperimentSkillService.findTExperimentSkillByPrimaryKey(skillId);
		mav.addObject("tExperimentSkill",tExperimentSkill);
		//实验技能完成情况列表
		List<Integer> skillDoList = tExperimentSkillService.findTExperimentSkillStringList(tCourseSiteId);
		mav.addObject("skillDoList",skillDoList);
		//实验技能下的实验报告
		TAssignment tAssignment = tExperimentSkillService.findReportTAssignmentBySkill(tExperimentSkill);
		mav.addObject("tAssignment",tAssignment);
		
		List<TAssignmentGrading> tAssignmentGradings =null;
		//学生权限进入自己的实验报告
		if(flag==0){
			tAssignmentGradings = tExperimentSkillService.findReportTAssignmentGradingBySkillAndStudent(tExperimentSkill);
			if(tAssignmentGradings.size()==0){
				mav.addObject("tAssignmentGrading",new TAssignmentGrading());
				mav.setViewName("tcoursesite/skill/reportExperimentSkillForStudent.jsp");
				return mav;
			}
		}
		//教师权限查看学生提交列表(或学生已经提交过报告)
		tAssignmentGradings = tAssignmentGradingService.findTAssignmentGradingList(tAssignment.getId(), flag, user);
		
		if(flag >0 && user !=tAssignment.getUser()){
			
			User userByCreate = tAssignment.getUser();
			tAssignmentGradings = tAssignmentGradingService.findTAssignmentGradingList(tAssignment.getId(), flag, userByCreate);
		}
		mav.addObject("tAssignmentGradings",tAssignmentGradings);
		
		//mav.addObject("skillDoList",skillDoList);
		mav.setViewName("tcoursesite/skill/reportExperimentSkill.jsp");
		return mav;
	}
	
	/**************************************************************************
	 * Description:实验技能-提交实验报告
	 *  
	 * @author：裴继超
	 * @date ：2016-10-13
	 **************************************************************************/
	@RequestMapping("/tcoursesite/skill/saveReportByStudent")
	public ModelAndView saveReportByStudent(@ModelAttribute TAssignmentGrading tAssignmentGrade,
			Integer skillId,Integer tCourseSiteId,HttpServletRequest request,Integer selectId){
		ModelAndView mav = new ModelAndView();
		TAssignmentGrading tAssignmentGrading = tExperimentSkillService.
				saveReportByStudent(tAssignmentGrade,tCourseSiteId,request);
		mav.setViewName("redirect:/tcoursesite/skill/reportExperimentSkill?tCourseSiteId="+tCourseSiteId+"&skillId="+skillId);
		return mav;
	}
	
	/**************************************************************************
	 * Description:实验技能-查看实验作业
	 *  
	 * @author：裴继超
	 * @date ：2016-10-14
	 **************************************************************************/
	@SystemServiceLog("查看实验作业")
	@ResponseBody
	@RequestMapping("/tcoursesite/skill/workExperimentSkill")
	public ModelAndView workExperimentSkill(@RequestParam Integer tCourseSiteId,Integer skillId,
			HttpSession httpSession) {
		ModelAndView mav = new ModelAndView();
		//选择的课程中心
		TCourseSite tCourseSite = tCourseSiteService.findCourseSiteById(tCourseSiteId);
		mav.addObject("tCourseSite", tCourseSite);
		mav.addObject("skillId",skillId);
		mav.addObject("moduleType", 2);
		mav.addObject("selectId", -1);
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
			flag = 1;//实验中心主任   或者实验室管理员   或者超级管理员   或者教务处   或者校领导   或者教学副院长    或者教务管理员   或者实验中心管理员   为教师权限
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
		//当前进入的url
		mav.addObject("url", "workExperimentSkill");
		//查看站点下的所有实验技能
		List<TExperimentSkill> tExperimentSkills = tExperimentSkillService.findTExperimentSkillListBySiteId(tCourseSiteId, 1,-1,"skill");
		mav.addObject("tExperimentSkills",tExperimentSkills);
		//查看实验技能
		TExperimentSkill tExperimentSkill = tExperimentSkillService.findTExperimentSkillByPrimaryKey(skillId);
		mav.addObject("tExperimentSkill",tExperimentSkill);
		//查看实验技能对应的章节
		WkChapter wkChapter = wkChapterService.findChapterByPrimaryKey(tExperimentSkill.getChapterId());
		mav.addObject("wkChapter",wkChapter);
		
		//移除不属于当前登陆人（学生）提交的作业
		Set<WkFolder> wkFolders = wkChapter.getWkFolders();
		for(WkFolder wkFolder : wkFolders) {
			
			Set<TAssignment> tAssignments = wkFolder.getTAssignments();
			for(TAssignment tAssignment : tAssignments) {
			
				//获取每个作业或测试题表下的所有成绩
				Set<TAssignmentGrading> tAssignmentGradings = tAssignment.getTAssignmentGradings();
				Set<TAssignmentGrading> tAssignmentGradingsTemp = new HashSet<TAssignmentGrading>();
				for (TAssignmentGrading assignmentGrading : tAssignmentGradings) {
					if (!user.getUsername().equals(assignmentGrading.getUserByStudent().getUsername())) {
						tAssignmentGradingsTemp.add(assignmentGrading);//移除非当前登陆学生所提交的作业
					}else if (assignmentGrading.getSubmitTime()==0) {//查询学生所保存的作业
						tAssignmentGradingsTemp.add(assignmentGrading);//移除保存但未提交的作业
					}
				}
				tAssignmentGradings.removeAll(tAssignmentGradingsTemp);//当前统计登录人的历史提交记录
			}
		}
		
		//实验技能完成情况列表
		List<Integer> skillDoList = tExperimentSkillService.findTExperimentSkillStringList(tCourseSiteId);
		mav.addObject("skillDoList",skillDoList);
		
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
		tAssignmentAnswerAssign.setUser(user);
		
		tAssignment.setCreatedTime(duedate);
		tAssignment.setUser(user);
		tAssignment.setTAssignmentControl(tAssignmentControl);
		tAssignment.setTAssignmentAnswerAssign(tAssignmentAnswerAssign);
		mav.addObject("tAssignment", tAssignment);
//		if (flag == 0){
//			TAssignment stuTAssignment = tAssignmentService.findTAssignmentById(tAssignment.getId());
//			mav.addObject("stuTAssignmentt", stuTAssignment);
//		}
		if (user != null) {
			List<ViewTAssignment> chapterViewTAssignments = tAssignmentService.findTAssignmentList(user,tCourseSiteId,flag,"chapter");
			mav.addObject("chapterViewTAssignments", chapterViewTAssignments);
			
			List<ViewTAssignment> lessonViewTAssignments = tAssignmentService.findTAssignmentList(user,tCourseSiteId,flag,"lesson");
			mav.addObject("lessonViewTAssignments", lessonViewTAssignments);
		}
		
		
		mav.setViewName("tcoursesite/skill/workExperimentSkill.jsp");
		return mav;
	}
	
	
	/**************************************************************************
	 * Description:实验技能-查看实验问答
	 *  
	 * @author：裴继超
	 * @date ：2016-10-10
	 **************************************************************************/
	@SystemServiceLog("查看实验问答")
	@ResponseBody
	@RequestMapping("/tcoursesite/skill/discussExperimentSkill")
	public ModelAndView discussExperimentSkill(@RequestParam Integer tCourseSiteId,Integer skillId,
			@RequestParam Integer currpage,HttpSession httpSession) {
		ModelAndView mav = new ModelAndView();
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
			flag = 1;//实验中心主任   或者实验室管理员   或者超级管理员   或者教务处   或者校领导   或者教学副院长    或者教务管理员   或者实验中心管理员   为教师权限
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
		//当前进入的url
		mav.addObject("url", "discussExperimentSkill");
		//查看站点下的所有实验技能
		List<TExperimentSkill> tExperimentSkills = tExperimentSkillService.findTExperimentSkillListBySiteId(tCourseSiteId, 1,-1,"skill");
		mav.addObject("tExperimentSkills",tExperimentSkills);
		//查看实验技能
		TExperimentSkill tExperimentSkill = tExperimentSkillService.findTExperimentSkillByPrimaryKey(skillId);
		mav.addObject("tExperimentSkill",tExperimentSkill);
		//实验技能完成情况列表
		List<Integer> skillDoList = tExperimentSkillService.findTExperimentSkillStringList(tCourseSiteId);
		mav.addObject("skillDoList",skillDoList);
		//获取该站点下的讨论数量
		int totalRecords = tExperimentSkillService.getCountSkillTDiscussList(tCourseSiteId.toString());
		mav.addObject("totalRecords", totalRecords);
		//设置分页大小
		int pageSize = 200;
		Map<String, Integer> pageModel = shareService.getPage(currpage, pageSize, totalRecords);
		mav.addObject("pageModel", pageModel);
		//将currpage映射到page，用来获取当前页的页码
		mav.addObject("page", currpage);
		List<TDiscuss> tDiscusses = tExperimentSkillService.findSkillTDiscussList(tCourseSiteId.toString(),currpage,pageSize);	
		
		mav.addObject("tDiscusses",tDiscusses);
		
		//新建讨论
		mav.addObject("tDiscuss",new TDiscuss());
		
		
		mav.setViewName("tcoursesite/skill/discussExperimentSkill.jsp");
		return mav;
	}
	
	/**************************************************************************
	 * Description:实验技能-保存实验问答
	 *  
	 * @author：裴继超
	 * @date ：2016-10-10
	 **************************************************************************/
	@SystemServiceLog("保存实验问答")
	@ResponseBody
	@RequestMapping("/tcoursesite/skill/saveSkillDiscuss")
	public ModelAndView saveSkillDiscuss(@RequestParam Integer tCourseSiteId,Integer skillId,
			@ModelAttribute TDiscuss tDiscuss,HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		tDiscuss.setTCourseSite(tCourseSiteService.findCourseSiteById(tCourseSiteId));
		tDiscuss.setDiscussTime(Calendar.getInstance());
		tDiscuss.setUser(shareService.getUser());
		tDiscuss.setSkillId(skillId);
		tDiscuss.setIp(request.getRemoteAddr());
		tExperimentSkillService.saveSkillTDiscuss(tDiscuss);
		mav.setViewName("redirect:/tcoursesite/skill/discussExperimentSkill?tCourseSiteId="+
				tCourseSiteId+"&skillId="+skillId+"&currpage=1");
		return mav;
	}

	/**************************************************************************
	 * Description:实验技能-删除实验问答
	 *  
	 * @author：裴继超
	 * @date ：2016-06-24
	 **************************************************************************/
	@SystemServiceLog("删除实验问答")
	@ResponseBody
	@RequestMapping("/tcoursesite/skill/deleteSkillDiscuss")
	public ModelAndView deleteSkillDiscuss(@RequestParam Integer discussId,Integer skillId,
			Integer tCourseSiteId) {
		ModelAndView mav = new ModelAndView();
		//根据id查询实验问答
		TDiscuss tDiscuss = tExperimentSkillService.findSkillTDiscussByPrimaryKey(discussId);
		//删除该实验问答
		tExperimentSkillService.deleteSkillTDiscuss(tDiscuss);
		mav.setViewName("redirect:/tcoursesite/skill/discussExperimentSkill?tCourseSiteId="+
				tCourseSiteId+"&skillId="+skillId+"&currpage=1");
		return mav;
	}

	/**************************************************************************
	 * Description:实验技能-修改实验问答
	 *  
	 * @author：裴继超
	 * @date ：2016-06-24
	 **************************************************************************/
	@SystemServiceLog("修改实验问答")
	@RequestMapping("/tcoursesite/skill/editSkillDiscuss")
	@ResponseBody
	public Map<String, Object> editSkillDiscuss(@RequestParam Integer discussId) {
		//根据id查询实验问答
		TDiscuss tDiscuss = tExperimentSkillService.findSkillTDiscussByPrimaryKey(discussId);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("title", (tDiscuss.getTitle()==null)?"":tDiscuss.getTitle());// 章节id
		map.put("content", (tDiscuss.getContent()==null)?"":tDiscuss.getContent());
		return map;
	}

	/**************************************************************************
	 * Description:实验技能-查看实验问答及回复
	 *  
	 * @author：裴继超
	 * @date ：2016-06-27
	 **************************************************************************/
	@SystemServiceLog("查看实验问答及回复")
	@ResponseBody
	@RequestMapping("/tcoursesite/skill/skillDiscussReplies")
	public ModelAndView skillDiscussReplies(HttpSession httpSession,@RequestParam Integer tCourseSiteId,
			Integer skillId,Integer discussId,@RequestParam Integer currpage) {
		ModelAndView mav = new ModelAndView();
		// 选择的课程中心
		TCourseSite tCourseSite = tCourseSiteService.findCourseSiteById(tCourseSiteId);
		mav.addObject("tCourseSite", tCourseSite);
		//初始化默认时间
		/*Calendar duedate = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String nowDate =sdf.format(duedate.getTime());
		mav.addObject("now",nowDate);*/
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
			}else if (tCourseSiteUserService.isUserBelongToTeacher(tCourseSiteId)){
				flag=1; //如果当前登陆人参与这门课，则是老师身份
			}else {
				flag = 0;//如果当前登陆人不是本人开的课程，则是学生身份
			}
			
		}else if(user!=null&&user.getAuthorities().toString().contains("STUDENT")){
			flag = 0;
		}
		mav.addObject("flag", flag);
		//当前进入的url
		mav.addObject("url", "discussExperimentSkill");
		//查看站点下的所有实验技能
		List<TExperimentSkill> tExperimentSkills = tExperimentSkillService.findTExperimentSkillListBySiteId(tCourseSiteId, 1,-1,"skill");
		mav.addObject("tExperimentSkills",tExperimentSkills);
		//查看实验技能
		TExperimentSkill tExperimentSkill = tExperimentSkillService.findTExperimentSkillByPrimaryKey(skillId);
		mav.addObject("tExperimentSkill",tExperimentSkill);
		//实验技能完成情况列表
		List<Integer> skillDoList = tExperimentSkillService.findTExperimentSkillStringList(tCourseSiteId);
		mav.addObject("skillDoList",skillDoList);
		//获取实验问答
		TDiscuss discuss = tExperimentSkillService.findSkillTDiscussByPrimaryKey(discussId);
		mav.addObject("discuss", discuss);
		
		//获取该站点下的实验问答数量
		int totalRecords = discuss.getTDiscusses().size();
		mav.addObject("totalRecords", totalRecords);
		//设置分页大小
		int pageSize = 200;
		Map<String, Integer> pageModel = shareService.getPage(currpage, pageSize, totalRecords);
		mav.addObject("pageModel", pageModel);
		//将currpage映射到page，用来获取当前页的页码
		mav.addObject("page", currpage);
		List<TDiscuss> tDiscusses = tExperimentSkillService.findSkillTDiscussListByPartent(discussId,currpage,pageSize);	
		
		mav.addObject("tDiscusses",tDiscusses);
		
		//新建实验问答
		mav.addObject("tDiscuss",new TDiscuss());
		mav.setViewName("tcoursesite/skill/skillDiscussReplies.jsp");
		return mav;
	}
	
	/**************************************************************************
	 * Description:实验技能-保存回复
	 *  
	 * @author：裴继超
	 * @date ：2016-06-20
	 **************************************************************************/
	@SystemServiceLog("保存回复")
	@ResponseBody
	@RequestMapping("/tcoursesite/skill/saveSkillDiscussReply")
	public ModelAndView saveSkillDiscussReply(@RequestParam Integer tCourseSiteId,
			Integer partentId,Integer skillId,@ModelAttribute TDiscuss tDiscuss,
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		TDiscuss parent = tExperimentSkillService.findSkillTDiscussByPrimaryKey(partentId);
		tDiscuss.setTDiscuss(parent);
		tDiscuss.setDiscussTime(Calendar.getInstance());
		tDiscuss.setUser(shareService.getUser());
		tDiscuss.setIp(request.getRemoteAddr());
		tExperimentSkillService.saveSkillTDiscuss(tDiscuss);
		mav.setViewName("redirect:/tcoursesite/skill/skillDiscussReplies?discussId="+partentId+
				"&skillId="+skillId+"&tCourseSiteId="+tCourseSiteId+"&currpage=1");
		return mav;
	}

	/**************************************************************************
	 * Description:实验技能-删除回复
	 *  
	 * @author：裴继超
	 * @date ：2016-10-13
	 **************************************************************************/
	@SystemServiceLog("删除回复")
	@ResponseBody
	@RequestMapping("/tcoursesite/skill/deleteSkillDiscussReply")
	public ModelAndView deleteSkillDiscussReply(@RequestParam Integer discussId, Integer skillId,
			Integer tCourseSiteId) {
		ModelAndView mav = new ModelAndView();
		//根据id查询实验问答
		TDiscuss tDiscuss = tExperimentSkillService.findSkillTDiscussByPrimaryKey(discussId);
		int partentId = tDiscuss.getTDiscuss().getId();
		//删除该回复
		tExperimentSkillService.deleteSkillTDiscuss(tDiscuss);
		mav.setViewName("redirect:/tcoursesite/skill/skillDiscussReplies?discussId="+partentId+
				"&skillId="+skillId+"&tCourseSiteId="+tCourseSiteId+"&currpage=1");
		return mav;
	}
	
	
	/**************************************************************************
	 * Description:实验技能-给学生打分
	 *  
	 * @author：裴继超
	 * @date ：2016-10-13
	 **************************************************************************/
	@RequestMapping("/tcoursesite/skill/gradeReport")
	public @ResponseBody String gradeReport(@RequestParam Integer assignGradeId, String comments,
			Float finalScore,Integer tCourseSiteId){
		//flag用于判断是学生和教师
		User nowUser = shareService.getUserDetail();
		Calendar calendar = Calendar.getInstance();
		TAssignmentGrading tAssignmentGrade = tAssignmentGradingService.
				updateTAssignmentGrading(assignGradeId,comments,finalScore,nowUser,calendar);
		//根据作业查询成绩是否进成绩册
		tGradebookService.saveGradebook(tCourseSiteId,tAssignmentGrade.getTAssignment().getId(),
				tAssignmentGrade);
		return null;
	}
	
	/**************************************************************************
	 * Description:实验技能-下载一个学生的附件
	 *  
	 * @author：裴继超
	 * @date ：2016-10-13
	 **************************************************************************/
	@RequestMapping("/tcoursesite/skill/downloadReportFile")                                                     
	public String downloadReportFile(HttpServletRequest request,HttpServletResponse response,
			@RequestParam Integer id,@RequestParam Integer tCourseSiteId,
			Integer skillId) throws Exception {
		//id对应的文档
		TAssignmentGrading tAssignmentGrade=tAssignmentGradingService.findTAssignmentGradingById(id);
		
		String result=tAssignmentGradingService.downloadFile(tAssignmentGrade, request, response,tCourseSiteId);
		if (result == null) {
			return "forward:/tcoursesite/skill/reportExperimentSkill?tCourseSiteId="+tCourseSiteId+"&skillId=="+skillId;
		}
		return null;
		
	}
	
	/**************************************************************************
	 * Description:实验技能-查看实验报告文本
	 *  
	 * @author：裴继超
	 * @date ：2016-10-13
	 **************************************************************************/
	@SystemServiceLog("查看实验报告文本")
	@RequestMapping("/tcoursesite/skill/lookReport")
	@ResponseBody
	public Map<String, Object> lookReport(@RequestParam Integer id) {
		//设置返回的集合
		Map<String, Object> map = new HashMap<String, Object>();
		//根据id查询实验报告
		TAssignmentGrading tAssignmentGrade=tAssignmentGradingService.findTAssignmentGradingById(id);
		map.put("content", (tAssignmentGrade.getContent()==null)?"":tAssignmentGrade.getContent());// 章节id
		return map;
	}
	
	/**************************************************************************
	 * Description:实验技能-查看实验微课
	 *  
	 * @author：裴继超
	 * @date ：2016-11-17
	 **************************************************************************/
	@SystemServiceLog("查看实验微课")
	@ResponseBody
	@RequestMapping("/tcoursesite/skill/weikeExperimentSkill")
	public ModelAndView weikeExperimentSkill(@RequestParam Integer tCourseSiteId,Integer skillId,
			HttpSession httpSession) {
		ModelAndView mav = new ModelAndView();
		//选择的课程中心
		TCourseSite tCourseSite = tCourseSiteService.findCourseSiteById(tCourseSiteId);
		mav.addObject("tCourseSite", tCourseSite);
		//当前登录人
		User user = shareService.getUser();
		mav.addObject("user", user);
		//角色判断：如果具有老师权限且为该课程的创建者或者助教则默认为老师，如果没有教师权限则默认为学生
		Integer flag = tCourseSiteService.getFlagByUserAndSite(user, tCourseSite, httpSession);
		mav.addObject("flag", flag);
		//当前进入的url
		mav.addObject("url", "weikeExperimentSkill");
		//查看站点下的所有实验技能
		List<TExperimentSkill> tExperimentSkills = tExperimentSkillService.findTExperimentSkillListBySiteId(tCourseSiteId, 1,-1,"skill");
		mav.addObject("tExperimentSkills",tExperimentSkills);
		//查看实验技能
		TExperimentSkill tExperimentSkill = tExperimentSkillService.findTExperimentSkillByPrimaryKey(skillId);
		mav.addObject("tExperimentSkill",tExperimentSkill);
		//实验技能完成情况列表
		List<Integer> skillDoList = tExperimentSkillService.findTExperimentSkillStringList(tCourseSiteId);
		mav.addObject("skillDoList",skillDoList);
		//实验技能所属实验室
		String labRoomNames =tExperimentSkillService.findExperimentLabRoomsString(skillId);
		mav.addObject("labRoomNames",labRoomNames);
		
		//实验视频
		List<WkUpload> videos = tExperimentSkillService.findWkUploadBySkillIdAndType(skillId, 203);
		mav.addObject("videos",videos);
		
		//如果是学生设置预习测试可答题时间
		if(flag==0){
			tExperimentSkillService.newPrepareExam(flag, skillId);
		}
		mav.setViewName("tcoursesite/skill/weikeExperimentSkill.jsp");
		return mav;
	}
	
	/**************************************************************************
	 * Description:实验技能-预习测试-查看预习测试
	 *  
	 * @author：裴继超
	 * @date ：2016-11-17
	 **************************************************************************/
	@SystemServiceLog("查看预习测试")
	@ResponseBody
	@RequestMapping("/tcoursesite/skill/prepareExam")
	public ModelAndView prepareExam(@RequestParam Integer tCourseSiteId,Integer skillId,
			HttpSession httpSession) {
		ModelAndView mav = new ModelAndView();
		//选择的课程中心
		TCourseSite tCourseSite = tCourseSiteService.findCourseSiteById(tCourseSiteId);
		mav.addObject("tCourseSite", tCourseSite);
		//当前登录人
		User user = shareService.getUser();
		mav.addObject("user", user);
		//角色判断：如果具有老师权限且为该课程的创建者或者助教则默认为老师，如果没有教师权限则默认为学生
		Integer flag = tCourseSiteService.getFlagByUserAndSite(user, tCourseSite, httpSession);
		mav.addObject("flag", flag);
		//当前进入的url
		mav.addObject("url", "prepareExam");
		//查看站点下的所有实验技能
		List<TExperimentSkill> tExperimentSkills = tExperimentSkillService.findTExperimentSkillListBySiteId(tCourseSiteId, 1,-1,"skill");
		mav.addObject("tExperimentSkills",tExperimentSkills);
		//查看实验技能
		TExperimentSkill tExperimentSkill = tExperimentSkillService.findTExperimentSkillByPrimaryKey(skillId);
		mav.addObject("tExperimentSkill",tExperimentSkill);
		//实验技能完成情况列表
		List<Integer> skillDoList = tExperimentSkillService.findTExperimentSkillStringList(tCourseSiteId);
		mav.addObject("skillDoList",skillDoList);
		
		//实验技能下的预习测试
		TAssignment exam = tExperimentSkillService.findTAssignmentBySkillAndType(tExperimentSkill,205);
		mav.addObject("exam",exam);
		//教师权限查看学生提交列表(或学生已经提交过预习测试)
		List<TAssignmentGrading> tAssignmentGradings = tAssignmentGradingService.
				findExamGradingList(exam.getId(), flag, user);
		mav.addObject("tAssignmentGradings",tAssignmentGradings);
		//计算是否到答题时间
		//1为可以答题，0和-1为已查看视频但未到时间不可以答题，2为已答过题
		Integer examCan = tExperimentSkillService.getExamCan(flag, skillId);
	    mav.addObject("examCan",examCan);
		
		mav.setViewName("tcoursesite/skill/prepareExam.jsp");
		return mav;
	}
	
	/**************************************************************************
	 * Description:实验技能-查看成绩管理
	 *  
	 * @author：裴继超
	 * @date ：2016-11-29
	 **************************************************************************/
	@SystemServiceLog("查看成绩管理")
	@ResponseBody
	@RequestMapping("/tcoursesite/skill/gradeExperimentSkill")
	public ModelAndView gradeExperimentSkill(@RequestParam Integer tCourseSiteId,Integer skillId,Integer currpage,
			HttpSession httpSession) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("skillId", skillId);
		//选择的课程中心
		TCourseSite tCourseSite = tCourseSiteService.findCourseSiteById(tCourseSiteId);
		mav.addObject("tCourseSite", tCourseSite);
		//当前登录人
		User user = shareService.getUser();
		mav.addObject("user", user);
		//角色判断：如果具有老师权限且为该课程的创建者或者助教则默认为老师，如果没有教师权限则默认为学生
		Integer flag = tCourseSiteService.getFlagByUserAndSite(user, tCourseSite, httpSession);
		mav.addObject("flag", flag);
		//当前进入的url
		mav.addObject("url", "gradeExperimentSkill");
		
		//查看站点下的所有实验技能
		List<TExperimentSkill> tExperimentSkills = tExperimentSkillService.findTExperimentSkillListBySiteId(tCourseSiteId, 1,-1,"skill");
		mav.addObject("tExperimentSkills",tExperimentSkills);
		//查看实验技能
		TExperimentSkill tExperimentSkill = tExperimentSkillService.findTExperimentSkillByPrimaryKey(skillId);
		mav.addObject("tExperimentSkill",tExperimentSkill);
		//实验技能完成情况列表
		List<Integer> skillDoList = tExperimentSkillService.findTExperimentSkillStringList(tCourseSiteId);
		mav.addObject("skillDoList",skillDoList);
		//实验技能所属实验室
		String labRoomNames =tExperimentSkillService.findExperimentLabRoomsString(skillId);
		mav.addObject("labRoomNames",labRoomNames);
		
		List<TAssignment> workList=new ArrayList<TAssignment>();
		if(tExperimentSkill != null && tExperimentSkill.getChapterId() != null) {
			WkChapter wkChapter = wkChapterService.findChapterByPrimaryKey(tExperimentSkill.getChapterId());
			//mav.addObject("wkChapter",wkChapter);
			//根据实验项目的id获取该实验项目下的所有的作业
			Set<WkFolder> wkFolders = wkChapter.getWkFolders();
			for(WkFolder folder:wkFolders){
				if(folder.getType()==4){
					//当前实验项目下的作业
					Set<TAssignment> tAssignments = folder.getTAssignments();
					workList.addAll(tAssignments);
					mav.addObject("tAsslignmentList",tAssignments);
				}
			}
		}
		mav.addObject("workList",workList);
		//实验报告科目列表
		TAssignment tAssignmentForReport = tExperimentSkillService.findReportTAssignmentBySkill(tExperimentSkill);
		List<TGradeObject> reportGradeObjects = new ArrayList<TGradeObject>();
		reportGradeObjects.addAll(tAssignmentForReport.getTGradeObjects());
		//预习测试科目列表
		List<TGradeObject> prepareExamGradeObjects = tExperimentSkillService.findTGradeObjectsByType(tCourseSiteId,skillId,"prepareExam");      
		//获取实验作业，实验测试，实验报告及对应的权重
		List<TWeightSetting> workAndTestAndReport = tGradebookService.getWorkAndTestAndReport(tCourseSiteId);
		mav.addObject("workAndTestAndReport",workAndTestAndReport);

		if(currpage ==null) {//若没有分页数，则设置为第一页
			currpage =1;
		}
		//每页40条数据，总页数
		int pageSize =40;
		int totalRecords =tCourseSiteService.findTCourseSiteStudents(tCourseSiteId).size();
		//分页信息
		Map<String, Integer> pageModel = shareService.getPage(currpage, pageSize, totalRecords);
		//分页查询课程所属学生列表
		List<TCourseSiteUser> tCourseSiteUsers = tCourseSiteService.findTCourseSiteStudentsWithMore(tCourseSiteId,currpage,pageSize);   
		mav.addObject("pageModel", pageModel);
		mav.addObject("page", currpage);
		mav.addObject("totalRecords", totalRecords);
		mav.addObject("tCourseSiteUsers",tCourseSiteUsers);
		//根据科目和学生查看成绩列表
		//List<List<Object>> gradeLists = tGradebookService.findStudentScoreRecords(tCourseSiteUsers,skillGradeObjects);
		List<List<Object>> gradeLists = tGradebookService.findStudentScoreRecordsWithAll(tCourseSiteUsers, prepareExamGradeObjects, reportGradeObjects, workList, tCourseSiteId);
		mav.addObject("gradeLists", gradeLists);
		
		//实验科目列表
		List<TGradeObject> skillGradeObjects = prepareExamGradeObjects;
		skillGradeObjects.addAll(reportGradeObjects);
		mav.addObject("skillGradeObjects",skillGradeObjects);
		
		//学生的最终成绩列表
		Map<String, Integer> finalGradeLists = new LinkedHashMap<String, Integer>(0);
		for(List<Object> l:gradeLists){
			if(l !=null) {
			TExperimentSkillUser tExperimentSkillUser = tExperimentSkillService.findSkillUser(skillId, l.get(1).toString());
			
			String skillScore = tExperimentSkillUser.getFinalGrade().toString();
			if (skillScore.contains(".")) {
				skillScore = skillScore.split("\\.")[0];
			}
			if(Integer.parseInt(skillScore)==0){
				skillScore="0";
			}
			finalGradeLists.put(l.get(1).toString(), Integer.parseInt(skillScore));
			}
		}
		mav.addObject("finalGradeLists",finalGradeLists);
		mav.setViewName("tcoursesite/skill/gradeExperimentSkill.jsp");
		return mav;
	}
	/**************************************************************************
	 * Description:实验技能-预习测试-编辑测验题目
	 *  
	 * @author：裴继超
	 * @date ：2016-11-18
	 **************************************************************************/
	@RequestMapping("/tcoursesite/skill/prepareExamInfo")
	public ModelAndView prepareExamInfo(@RequestParam int tCourseSiteId,int skillId,
			HttpSession httpSession){
		//新建ModelAndView对象；
		ModelAndView mav = new ModelAndView();
		//选择的课程中心
		TCourseSite tCourseSite = tCourseSiteService.findCourseSiteById(tCourseSiteId);
		mav.addObject("tCourseSite", tCourseSite);
		//当前登录人
		User user = shareService.getUser();
		mav.addObject("user", user);
		//角色判断：如果具有老师权限且为该课程的创建者或者助教则默认为老师，如果没有教师权限则默认为学生
		Integer flag = tCourseSiteService.getFlagByUserAndSite(user, tCourseSite, httpSession);
		mav.addObject("flag", flag);
		//当前进入的url
		mav.addObject("url", "prepareExam");
		//查看站点下的所有实验技能
		List<TExperimentSkill> tExperimentSkills = tExperimentSkillService.findTExperimentSkillListBySiteId(tCourseSiteId, 1,-1,"skill");
		mav.addObject("tExperimentSkills",tExperimentSkills);
		//查看实验技能
		TExperimentSkill tExperimentSkill = tExperimentSkillService.findTExperimentSkillByPrimaryKey(skillId);
		mav.addObject("tExperimentSkill",tExperimentSkill);
		//实验技能完成情况列表
		List<Integer> skillDoList = tExperimentSkillService.findTExperimentSkillStringList(tCourseSiteId);
		mav.addObject("skillDoList",skillDoList);
		
		//实验技能下的预习测试
		TAssignment exam = tExperimentSkillService.findTAssignmentBySkillAndType(tExperimentSkill,205);
		mav.addObject("examId",exam.getId());
		//获取登录用户信息
		mav.addObject("user", user);
		//获取测验信息
		mav.addObject("examInfo", exam);
		mav.setViewName("tcoursesite/skill/prepareExamInfo.jsp");
		return mav;
	}
	/**************************************************************************
	 * Description:实验技能-所有实验项目成绩
	 *  
	 * @author：裴继超
	 * @date ：2016-11-29
	 **************************************************************************/
	@SystemServiceLog("所有实验项目成绩")
	@ResponseBody
	@RequestMapping("/tcoursesite/skill/gradeAllExperimentSkill")
	public ModelAndView gradeAllExperimentSkill(@RequestParam Integer tCourseSiteId,Integer currpage,
			HttpSession httpSession) {
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
		Integer flag = tCourseSiteService.getFlagByUserAndSite(user, tCourseSite, httpSession);
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
		//查看总成绩列表
		List<List<Object>> sumLists = tExperimentSkillService.gradeAllExperimentSkill(tCourseSiteUsers,tCourseSiteId);
		mav.addObject("sumLists", sumLists);
		
		mav.setViewName("tcoursesite/skill/gradeAllExperimentSkill.jsp");
		return mav;
	}
	
	/*************************************************************************************
	 * description:实验项目-删除实验项目
	 * 
	 * @author:于侃
	 * @date：2016年11月4日 14:56:56
	 *************************************************************************************/
	@SystemServiceLog("删除实验项目")
	@ResponseBody
	@RequestMapping("/tcoursesite/skill/deleteExperimentSkill")
	public ModelAndView deleteExperimentSkill(@RequestParam Integer tCourseSiteId,Integer skillId){
		ModelAndView mav = new ModelAndView();
		//通过id查询实验项目
		TExperimentSkill skill = tExperimentSkillService.findTExperimentSkillByPrimaryKey(skillId);
		//删除实验项目下对应所有实验室关联
		for (TExperimentLabRoom labRoom : tExperimentSkillService.getLabRoomList(skillId)) {
			tExperimentLabRoomDAO.remove(labRoom);
		}
		//删除实验项目下对应所有实验设备关联
		for (TExperimentLabDevice labDevice : tExperimentSkillService.getLabDeviceList(skillId)) {
			tExperimentLabDeviceDAO.remove(labDevice);
		}
		//删除对应作业，实验相关资料
		for (WkFolder folder : wkChapterService.findChapterByPrimaryKey(skill.getChapterId()).getWkFolders()) {
			wkFolderDAO.remove(folder);
		}
		//删除实验项目
		tExperimentSkillService.deleteTExperimentSkill(skill);
		mav.setViewName("redirect:/tcoursesite/skill/experimentSkillsList?tCourseSiteId="+tCourseSiteId);
		return mav;
	}
	/*************************************************************************************
	 * description:实验项目-判断是否已经生成教学对应课程
	 * 
	 * @author:李雪腾
	 * @throws IOException 
	 * @date：2017.7.11
	 *************************************************************************************/
	@RequestMapping("/tcoursesite/skill/isTcourseSiteExit")
	public void isTcourseSiteExit(@RequestParam String courseNo,String groupNumber, String courseDetailNo, HttpServletResponse res) throws IOException{
		boolean flag = tExperimentSkillService.isExitTcourseSiteWithCourseNo(courseNo,groupNumber,courseDetailNo);
		if(flag){
			res.getWriter().write("true");
		}else{
			res.getWriter().write("false");
		}
	}
	
	/**************************************************************************
	 * Description:实验技能-预习测试-新建测验大题
	 *  
	 * @author：张佳鸣
	 * @date ：2017-10-20
	 **************************************************************************/
	@RequestMapping("tcoursesite/skill/prepareExamNewSection")
	public ModelAndView prepareExamNewSection(@RequestParam int tCourseSiteId,int skillId,
			HttpSession httpSession) {
		//新建ModelAndView对象；
		ModelAndView mav = new ModelAndView();
		//选择的课程中心
		TCourseSite tCourseSite = tCourseSiteService.findCourseSiteById(tCourseSiteId);
		mav.addObject("tCourseSite", tCourseSite);
		//当前登录人
		User user = shareService.getUser();
		mav.addObject("user", user);
		//角色判断：如果具有老师权限且为该课程的创建者或者助教则默认为老师，如果没有教师权限则默认为学生
		Integer flag = tCourseSiteService.getFlagByUserAndSite(user, tCourseSite, httpSession);
		mav.addObject("flag", flag);
		//当前进入的url
		mav.addObject("url", "prepareExam");
		//查看站点下的所有实验技能
		List<TExperimentSkill> tExperimentSkills = tExperimentSkillService.findTExperimentSkillListBySiteId(tCourseSiteId, 1,-1);
		mav.addObject("tExperimentSkills",tExperimentSkills);
		//查看实验技能
		TExperimentSkill tExperimentSkill = tExperimentSkillService.findTExperimentSkillByPrimaryKey(skillId);
		mav.addObject("tExperimentSkill",tExperimentSkill);
		//实验技能完成情况列表
		List<Integer> skillDoList = tExperimentSkillService.findTExperimentSkillStringList(tCourseSiteId);
		mav.addObject("skillDoList",skillDoList);
		
		//实验技能下的预习测试
		TAssignment exam = tExperimentSkillService.findTAssignmentBySkillAndType(tExperimentSkill,205);
		mav.addObject("examId",exam.getId());
		
		TAssignmentSection tAssignmentSection = new TAssignmentSection();
		//初始化默认时间
		Calendar createdTime = Calendar.getInstance();
		tAssignmentSection.setTAssignment(exam);
		tAssignmentSection.setUser(user);
		tAssignmentSection.setCreatedTime(createdTime);
		tAssignmentSection.setStatus(0);
		mav.addObject("tAssignmentSection", tAssignmentSection);
		mav.setViewName("tcoursesite/skill/prepareExamNewSection.jsp");
			
		return mav;
	}
	
	/**************************************************************************
	 * Description:实验技能-预习测试-保存测验大题
	 * 
	 * @author：张佳鸣
	 * @date ：2017-10-20
	 **************************************************************************/
	@RequestMapping("/tcoursesite/skill/SavePrepareExamSection")
	public ModelAndView SavePrepareExamSection(@RequestParam int tCourseSiteId,Integer skillId,
			@ModelAttribute TAssignmentSection tAssignmentSection){
		//新建ModelAndView对象；
		ModelAndView mav=new ModelAndView();
		tAssignmentSection = tAssignmentSectionService.saveExamSection(tAssignmentSection); 
		mav.setViewName("redirect:/tcoursesite/skill/prepareExamInfo?tCourseSiteId="+tCourseSiteId+"&skillId="+ skillId);
		return mav;
	}
	
	/**************************************************************************
	 * Description:实验技能-预习测试-保存测验题目
	 * 
	 * @author：张佳鸣
	 * @date ：2017-10-20
	 **************************************************************************/
	@RequestMapping("/tcoursesite/skill/SavePrepareExamItem")
	public ModelAndView SavePrepareExamItem(@RequestParam int tCourseSiteId,Integer skillId,
			@ModelAttribute TAssignmentItem tAssignmentItem,HttpServletRequest request){
		//新建ModelAndView对象；
		ModelAndView mav=new ModelAndView();
		tAssignmentItem = tAssignmentItemService.saveExamItem(tAssignmentItem,request);
		mav.setViewName("redirect:/tcoursesite/skill/prepareExamInfo?tCourseSiteId="+tCourseSiteId+"&skillId="+ skillId);
		return mav;
	}
	
	/**************************************************************************
	 * Description:实验技能-预习测试-编辑测验题目
	 * 
	 * @author：张佳鸣
	 * @date ：2017-10-20
	 **************************************************************************/
	@RequestMapping("/tcoursesite/skill/prepareExamEditItem")
	public ModelAndView prepareExamEditItem(@RequestParam int tCourseSiteId,Integer skillId,
			@RequestParam Integer examItemId,HttpSession httpSession){
		//新建ModelAndView对象；
		ModelAndView mav = new ModelAndView();
		//选择的课程中心
		TCourseSite tCourseSite = tCourseSiteService.findCourseSiteById(tCourseSiteId);
		mav.addObject("tCourseSite", tCourseSite);
		//当前登录人
		User user = shareService.getUser();
		mav.addObject("user", user);
		//角色判断：如果具有老师权限且为该课程的创建者或者助教则默认为老师，如果没有教师权限则默认为学生
		Integer flag = tCourseSiteService.getFlagByUserAndSite(user, tCourseSite, httpSession);
		mav.addObject("flag", flag);
		//当前进入的url
		mav.addObject("url", "prepareExam");
		//查看站点下的所有实验技能
		List<TExperimentSkill> tExperimentSkills = tExperimentSkillService.findTExperimentSkillListBySiteId(tCourseSiteId, 1,-1);
		mav.addObject("tExperimentSkills",tExperimentSkills);
		//查看实验技能
		TExperimentSkill tExperimentSkill = tExperimentSkillService.findTExperimentSkillByPrimaryKey(skillId);
		mav.addObject("tExperimentSkill",tExperimentSkill);
		//实验技能完成情况列表
		List<Integer> skillDoList = tExperimentSkillService.findTExperimentSkillStringList(tCourseSiteId);
		mav.addObject("skillDoList",skillDoList);
		
		//实验技能下的预习测试
		TAssignment exam = tExperimentSkillService.findTAssignmentBySkillAndType(tExperimentSkill,205);
		mav.addObject("examId",exam.getId());
		//获取测验信息
		mav.addObject("tAssignmentItem", tAssignmentItemService.findTAssignmentItemById(examItemId));

		
		mav.setViewName("/tcoursesite/skill/prepareExamEditItem.jsp");
		return mav;
	}
	
	/**************************************************************************
	 * Description:实验技能-预习测试-删除小题
	 * 
	 * @author：张佳鸣
	 * @date ：2017-10-20
	 **************************************************************************/
	@RequestMapping("/tcoursesite/skill/prepareExamDeleteItem")
	public ModelAndView prepareExamDeleteItem(@RequestParam Integer examItemId,
			Integer tCourseSiteId, Integer skillId){
		//新建ModelAndView对象；
		ModelAndView mav=new ModelAndView();
		TAssignmentItem tAssignmentItem = tAssignmentItemService.findTAssignmentItemById(examItemId);
		tAssignmentItemService.deleteTAssignmentItem(tAssignmentItem);
		mav.setViewName("redirect:/tcoursesite/skill/prepareExamInfo?tCourseSiteId="+tCourseSiteId+"&skillId="+ skillId);
		return mav;
	}
	
	/**************************************************************************
	 * Description:实验技能-预习测试-发布
	 * 
	 * @author：裴继超
	 * @date ：2016-12-8
	 **************************************************************************/
	@RequestMapping("/tcoursesite/skill/prepareExamRelease")
	public ModelAndView prepareExamRelease(@RequestParam int tCourseSiteId,
			@RequestParam Integer assignmentId, Integer skillId) {
		//新建ModelAndView对象；
		ModelAndView mav=new ModelAndView();
		// 选择的课程中心
		TCourseSite tCourseSite = tCourseSiteService.findCourseSiteById(tCourseSiteId);
		mav.addObject("tCourseSite", tCourseSite);
		//获取登录用户信息
		mav.addObject("user", shareService.getUserDetail());
		//获取测验信息
		mav.addObject("examInfo", tAssignmentService.findTAssignmentById(assignmentId));
		/*List<TAssignmentItem> tAssignmentItems =  tAssignmentItemService.findTAssignmentItemListByExamId(examId);
		//获取题目详细信息
		mav.addObject("itemList", tAssignmentItems);*/
		tAssignmentService.releaseExam(assignmentId);

		mav.setViewName("redirect:/tcoursesite/skill/prepareExamInfo?tCourseSiteId="+tCourseSiteId+"&skillId="+ skillId);
		return mav;
	}
	
	/**************************************************************************
	 * Description:实验技能-保存实验测试，实验报告，实验作业的权重
	 *  
	 * @author：李雪腾
	 * @date ：2017-8-24
	 **************************************************************************/
	@SystemServiceLog("设置实验项目成绩权重")
	@ResponseBody
	@RequestMapping("/tcoursesite/skill/saveWorkAndReportAndTestWeight")
	public ModelAndView saveExperimentWeight(@RequestParam Integer tCourseSiteId,Integer skillId,
			HttpSession httpSession,HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		String[] ids = request.getParameterValues("wid");
		String[] weightList = request.getParameterValues("weight");
		int i=0;
		for(String id:ids){
			TWeightSetting tWeight = tWeightSettingDAO.findTWeightSettingByPrimaryKey(Integer.parseInt(id));
			BigDecimal weight = new BigDecimal(Integer.parseInt(weightList[i])/100.0);
			tWeight.setWeight(weight);
			Calendar duedate = Calendar.getInstance();
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			tWeight.setModifyDate(duedate);
			tWeightSettingDAO.store(tWeight);
			tWeightSettingDAO.flush();
			i++;
		}
		mav.setViewName("redirect:/tcoursesite/skill/gradeExperimentSkill?tCourseSiteId="+tCourseSiteId+"&skillId="+skillId+"&currpage=1");
		return mav;
	}
	
	/**************************************************************************
	 * Description:实验技能-预习测试-开始答题
	 * 
	 * @author：裴继超
	 * @date ：2016-11-21
	 **************************************************************************/
	@RequestMapping("/tcoursesite/skill/prepareBeginExam")
	public ModelAndView prepareBeginExam(@RequestParam Integer examId,
			Integer tCourseSiteId, Integer skillId){
		//新建ModelAndView对象；
		ModelAndView mav=new ModelAndView();
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
		//必须的参数传递
		mav.addObject("tCourseSiteId", tCourseSiteId);
		mav.addObject("skillId", skillId);
		mav.setViewName("tcoursesite/skill/prepareBeginExam.jsp");
		return mav;
	}
	
	/**************************************************************************
	 * Description:实验技能-预习测试-保存学生提交测验记录
	 * 
	 * @author：裴继超
	 * @date ：2016-11-21
	 **************************************************************************/
	@RequestMapping("/tcoursesite/skill/prepareSaveItemMapping")
	public ModelAndView prepareSaveItemMapping(HttpServletRequest request,
			@RequestParam int examId,Integer tCourseSiteId, Integer skillId){
		ModelAndView mav = new ModelAndView();
		//当前登录人
		User user = shareService.getUser();
		//保存学生答题的过程记录
		tAssignmentForExamService.saveTAssignmentItemMapping(request, examId, 1);
		//提交,则计入打分表
		TAssignmentGrading tAssignmentGrade = tAssignmentForExamService.saveTAssignmentGradeForExam(request,examId,1);
		//计入成绩册
		tGradebookService.saveGradebook(tCourseSiteId,examId,tAssignmentGrade);
		//保存实验项目分数
		tExperimentSkillService.saveSkillGrade(tCourseSiteId, skillId, user.getUsername());
		//返回
		mav.setViewName("redirect:/tcoursesite/skill/prepareExam?tCourseSiteId="+tCourseSiteId+"&skillId="+skillId);
		return mav;
	}
	
	/**************************************************************************
	 * Description:实验技能-预习测试-答题详情
	 * 
	 * @author：裴继超
	 * @date ：2016-11-21
	 * @搬迁标记：已搬迁至Pro_ExperimentSkillController
	 **************************************************************************/
	@RequestMapping("/tcoursesite/skill/prepareExamDetail")
	public ModelAndView prepareExamDetail(@RequestParam Integer gradingId,
			Integer tCourseSiteId, Integer skillId,HttpServletRequest request){
		//新建ModelAndView对象；
		ModelAndView mav=new ModelAndView();
		// 选择的课程中心
		TCourseSite tCourseSite = tCourseSiteService.findCourseSiteById(tCourseSiteId);
		mav.addObject("tCourseSite", tCourseSite);
		//获取登录用户信息
		User nowUser = shareService.getUserDetail();
		mav.addObject("user", nowUser);
		TAssignmentGrading tAssignmentGrading = tAssignmentGradingService.findTAssignmentGradingById(gradingId);
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
		Integer itemId = 0;
		for (TAssignmentItemMapping tAssignmentItemMapping : tAssignmentItemMappingList) {
			itemId = tAssignmentItemMapping.getTAssignmentItem().getId();
			scoreMap.put(itemId, tAssignmentItemMapping.getAutoscore());
			recordMap.put(tAssignmentItemMapping.getTAssignmentAnswer().getId(), tAssignmentItemMapping);
			
		}
		//获取httpSession
		HttpSession httpSession = request.getSession();
		//角色判断：如果具有老师权限且为该课程的创建者或者助教则默认为老师，如果没有教师权限则默认为学生
		Integer flag = tCourseSiteService.getFlagByUserAndSite(nowUser, tCourseSite, httpSession);
		mav.addObject("flag", flag);
		mav.addObject("scoreMap", scoreMap);
		mav.addObject("recordMap", recordMap);
		
		//必须的参数传递
		mav.addObject("tCourseSiteId", tCourseSiteId);
		mav.addObject("skillId", skillId);
		mav.setViewName("tcoursesite/skill/prepareExamDetail.jsp");
		return mav;
	}
	
	/**************************************************************************
	 * Description: 实验项目模块-原生方式多文件上传
	 * @throws IOException 
	 * 
	 * @author：马帅
	 * @date ：2017-8-21
	 **************************************************************************/
	@RequestMapping("/tcoursesite/multipleFileUpload")
	public void multipleFileUpload(HttpServletRequest request, HttpServletResponse response,Integer type,Integer siteId) throws IOException{
		String seps = System.getProperty("file.separator");
		/*
		 * 实验项目资源上传
		 */
		String fileName = "skill";
		String path = "upload" + seps + "tcoursesite" + seps + "site" + siteId + seps + fileName;
		Map<String,String> fileUploadMap = netDiskService.uploadMultiFile(request, path, type, siteId);
		String html = "";
		 for (Map.Entry<String, String> entry : fileUploadMap.entrySet()){
			html+="<li class='pic_list hg9 lh35 ptb5 rel ovh'  id='document"+entry.getKey()+"'>";
			html+="<input type='hidden' value="+entry.getKey()+" id='fileid' name='fileid' />";
			html+="<div class='cf rel zx1 z c_category'>";
			html+="<div class=''l mlr15 cc1 c_tool poi'>"+entry.getValue();
			if(type==201){
				//实验指导书
				html+="<i class='r fa fa-trash-o g9 f14 mr5 poi' onclick=\"deleteThisDocument('experimentalQuide',"+entry.getKey()+")\"></i>";
				html+="<a class=\"r fa fa-download g9 f14 mr5 poi\" href=\""+request.getContextPath()+"/product/tcoursesite/downloadFile?id="+entry.getKey()+"\"></a>";
				//html+="<a class=\"r fa fa-eye g9 f14 mr5 poi\" href=\""+request.getContextPath()+"/tcoursesite/showFile?id="+uploadWithOri.get(0)+"\" target=\"_blank\"></a>";
				html+="</div></div></li>";
			}else if(type==202){
				//实验图片
				html+="<i class='r fa fa-trash-o g9 f14 mr5 poi' onclick=\"deleteThisDocument('experimentalImage',"+entry.getKey()+")\"></i>";
				html+="<a class=\"r fa fa-download g9 f14 mr5 poi\" href=\""+request.getContextPath()+"/product/tcoursesite/downloadFile?id="+entry.getKey()+"\"></a>";
				html+="<i class=\"r fa fa-eye g9 f14 mr5 poi\" onclick=\"imageLook("+entry.getKey()+")\"></i>";
			}else if(type==203){
				//实验视频
				html+="<i class='r fa fa-trash-o g9 f14 mr5 poi' onclick=\"deleteThisDocument('experimentalVideo',"+entry.getKey()+")\"></i>";
				html+="<a class=\"r fa fa-download g9 f14 mr5 poi\" href=\""+request.getContextPath()+"/product/tcoursesite/downloadFile?id="+entry.getKey()+"\"></a>";
				html+="<i class=\"r fa fa-eye g9 f14 mr5 poi\" onclick=\"videoLook("+entry.getKey()+")\"></i>";
			}else{
				//其他
				html+="<i class='r fa fa-trash-o g9 f14 mr5 poi' onclick=\"deleteThisDocument('experimentalTool',"+entry.getKey()+")\"></i>";
			}
			html+="</div></div></li>";
		 }
		
		 response.getWriter().write(html);
	}
}