package net.xidlims.web.teaching;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.xidlims.dao.TCourseSiteUserDAO;
import net.xidlims.dao.TGradeObjectDAO;
import net.xidlims.dao.TGradeRecordDAO;
import net.xidlims.dao.UserDAO;
import net.xidlims.domain.LabCenter;
import net.xidlims.domain.SchoolTerm;
import net.xidlims.domain.TCourseSite;
import net.xidlims.domain.TCourseSiteUser;
import net.xidlims.domain.TGradeObject;
import net.xidlims.domain.TGradeRecord;
import net.xidlims.domain.TWeightSetting;
import net.xidlims.domain.User;
import net.xidlims.service.common.ShareService;
import net.xidlims.service.tcoursesite.TCourseSiteService;
import net.xidlims.service.tcoursesite.TGradebookService;
import net.xidlims.service.timetable.OuterApplicationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.authentication.UserServiceBeanDefinitionParser;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

/******************************************************************************************
 * @功能：成绩册模块 
 * @作者：魏诚 时间：2014-07-14
 *****************************************************************************************/
@Controller("TeachingGradeBookController")
@SessionAttributes("selected_labCenter")
public class TeachingGradeBookController<JsonResult> {
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
	private UserDAO userDAO;
	@Autowired
	private TGradeObjectDAO tGradeObjectDAO;
	@Autowired
	private TGradeRecordDAO tGradeRecordDAO;
	@Autowired
	private TCourseSiteUserDAO tCourseSiteUserDAO;
	@Autowired
	private TGradebookService tGradebookService;
	@Autowired
	private TCourseSiteService tCourseSiteService;
	
	

	/**************************************************************************************
	 * @成绩册 成绩册入口
	 * @作者：魏诚
	 * @日期：2015-08-3
	 *************************************************************************************/
	@RequestMapping("/teaching/gradebook/gradebookIframe")
	public ModelAndView gradebookIframe() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("teaching/gradebook/gradebookIframe.jsp");
		return mav;
	}
	
	/**************************************************************************************
	 * @成绩册 创建成绩册入口
	 * @作者：魏诚
	 * @日期：2015-08-3
	 *************************************************************************************/
	@RequestMapping("/teaching/gradebook/gradebookMain")
	public ModelAndView gradebookMain() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("teaching/gradebook/gradebookMain.jsp");
		return mav;
	}

	/**************************************************************************************
	 * @成绩册 查看可发布成绩册
	 * @作者：魏诚
	 * @日期：2015-08-3
	 *************************************************************************************/
	@RequestMapping("/teaching/gradebook/gradebookList")
	public ModelAndView gradebookList(int flag,HttpSession httpSession) {
		ModelAndView mav = new ModelAndView();
		int a =  Integer.parseInt(httpSession.getAttribute("selected_courseSite").toString());
		//科目列表
		List<TGradeObject> tGradeObjects = tGradeObjectDAO.executeQuery("select c from TGradeObject c where c.TGradebook.TCourseSite.id = " + a + " ", 0,-1) ;     
		mav.addObject("tGradeObjects",tGradeObjects);
		
		mav.addObject("flag",flag);
		mav.setViewName("teaching/gradebook/gradebookList.jsp");
		return mav;
	}
	
	/**************************************************************************************
	 * @成绩册 查看可发布成绩册的详细学生成绩清单
	 * @作者：魏诚
	 * @日期：2015-08-3
	 *************************************************************************************/
	@RequestMapping("/teaching/gradebook/gradebookListDetal")
	public ModelAndView gradebookListDetal(@RequestParam int objectId) {
		ModelAndView mav = new ModelAndView();
		
		//科目列表
		List<TGradeRecord> tGradeRecords = tGradeRecordDAO.executeQuery("select c from TGradeRecord c where c.TGradeObject.id = " + objectId + " ", 0,-1) ;     
		mav.addObject("tGradeRecords",tGradeRecords);

		mav.setViewName("teaching/gradebook/gradebookListDetail.jsp");
		return mav;
	}
	
	/**************************************************************************************
	 * @成绩册 查看可发布成绩册的学生所有详细成绩清单
	 * @作者：魏诚
	 * @日期：2015-08-3
	 *************************************************************************************/
	@RequestMapping("/teaching/gradebook/gradebookStudentDetal")
	public ModelAndView gradebookStudentDetal(@RequestParam int objectId,String username) {
		ModelAndView mav = new ModelAndView();
		
		//科目列表
		List<TGradeRecord> tGradeRecords = tGradeRecordDAO.executeQuery("select c from TGradeRecord c where  c.user.username like '" + username  +  "'", 0,-1) ;     
		mav.addObject("tGradeRecords",tGradeRecords);
		mav.addObject("user",userDAO.findUserByPrimaryKey(username));
		mav.setViewName("teaching/gradebook/gradebookStudentDetail.jsp");
		return mav;
	}
	
	/**************************************************************************************
	 * @成绩册 查看可发布学生成绩册
	 * @作者：魏诚
	 * @日期：2015-08-3
	 *************************************************************************************/
	@RequestMapping("/teaching/gradebook/gradebookStudentList")
	public ModelAndView gradebookStudentList(HttpSession httpSession) {
		ModelAndView mav = new ModelAndView();
		int a =  Integer.parseInt(httpSession.getAttribute("selected_courseSite").toString());
		
		//课程所属学生列表
		List<TCourseSiteUser> tCourseSiteUsers = tCourseSiteUserDAO.executeQuery("select c from TCourseSiteUser c where c.TCourseSite.id = " + a + " ", 0,-1) ;     
		mav.addObject("tCourseSiteUsers",tCourseSiteUsers);
		
		//学生成绩列表
		List<TGradeRecord> tGradeRecords = tGradeRecordDAO.executeQuery("select c from TGradeRecord c where c.TGradeObject.TGradebook.TCourseSite.id = " + a + " ", 0,-1) ;     
		mav.addObject("tGradeRecords",tGradeRecords);
		
		//科目列表
		List<TGradeObject> tGradeObjects = tGradeObjectDAO.executeQuery("select c from TGradeObject c where c.TGradebook.TCourseSite.id = " + a + " ", 0,-1) ;     
		mav.addObject("tGradeObjects",tGradeObjects);

		mav.setViewName("teaching/gradebook/gradebookStudentList.jsp");
		return mav;
	}
	
	
	/**************************************************************************************
	 * @成绩册 成绩明细入口
	 * @作者：黄崔俊
	 * @日期：2016-2-29 10:46:58
	 *************************************************************************************/
	@RequestMapping("/teaching/gradebook/gradebookManageIframe")
	public ModelAndView gradebookManageIframe(@RequestParam Integer cid) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("cid", cid);
		mav.setViewName("teaching/gradebook/gradebookManageIframe.jsp");
		return mav;
	}
	
	/**************************************************************************************
	 * @功能：查看学生作业成绩
	 * @作者：黄崔俊
	 * @日期：2016-1-7 16:30:07
	 *************************************************************************************/
	@RequestMapping("/teaching/gradebook/gradebookScoreListByType")
	public ModelAndView gradebookScoreListByType(@RequestParam String type,Integer cid) {
		ModelAndView mav = new ModelAndView();
		
		//课程所属学生列表
		List<TCourseSiteUser> tCourseSiteUsers = tCourseSiteService.findTCourseSiteStudents(cid) ;     
		mav.addObject("tCourseSiteUsers",tCourseSiteUsers);
		//科目列表
		List<TGradeObject> tGradeObjects = tGradebookService.findTGradeObjectsByTCourseSiteAndType(cid,type);      
		mav.addObject("tGradeObjects",tGradeObjects);

		//List<TCourseSite> tCourseSites = tCourseSiteService.findAllTCourseSitesByTCourseSiteUserAndStatus(tCourseSiteUsers.get(0),"1");
		List<List<Object>> lists = tGradebookService.findStudentScoreRecords(tCourseSiteUsers,tGradeObjects);
		mav.addObject("lists", lists);
		mav.addObject("type", type);
		mav.setViewName("teaching/gradebook/gradebookScoreList.jsp");
		return mav;
	}
	
	/**************************************************************************************
	 * @功能：查看学生作业总评
	 * @作者：黄崔俊
	 * @日期：2016-1-8 14:52:47
	 *************************************************************************************/
	@RequestMapping("/teaching/gradebook/gradebookTotalScoreList")
	public ModelAndView gradebookTotalScoreList(@RequestParam Integer cid) {
		ModelAndView mav = new ModelAndView();
		
		//课程所属学生列表
		List<TCourseSiteUser> tCourseSiteUsers = tCourseSiteService.findTCourseSiteStudents(cid);
		mav.addObject("tCourseSiteUsers",tCourseSiteUsers);
		//科目列表
		List<TGradeObject> tGradeObjects = tGradebookService.findTGradeObjectsByTCourseSiteAndType(cid,null);      
		mav.addObject("tGradeObjects",tGradeObjects);
		
		//List<TCourseSite> tCourseSites = tCourseSiteService.findAllTCourseSitesByTCourseSiteUserAndStatus(tCourseSiteUsers.get(0),"1");
		
		List<TWeightSetting> weightSettings = tGradebookService.findWeightSettings(cid);
		mav.addObject("weightSettings", weightSettings);
		List<List<Object>> lists = tGradebookService.findTotalScoreInfo(tCourseSiteUsers,weightSettings,cid);;
		mav.addObject("lists", lists);
		mav.setViewName("teaching/gradebook/gradebookTotalScoreList.jsp");
		return mav;
	}
	
	
	/**************************************************************************************
	 * @功能：权重设置
	 * @作者：黄崔俊
	 * @日期：2016-1-8 16:27:35
	 *************************************************************************************/
	@RequestMapping("/teaching/gradebook/weightSetting")
	public ModelAndView weightSetting(@RequestParam Integer cid) {
		ModelAndView mav = new ModelAndView();
		List<List<Object>> assignmentList = tGradebookService.findWeightSetting(cid,"assignment");
		mav.addObject("assignmentList", assignmentList);
		List<List<Object>> examList = tGradebookService.findWeightSetting(cid,"exam");
		mav.addObject("examList", examList);
		List<TWeightSetting> weightSettings = tGradebookService.findWeightSettings(cid);
		mav.addObject("weightSettings", weightSettings);
		mav.addObject("cid", cid);
		mav.setViewName("teaching/gradebook/weightSetting.jsp");
		return mav;
	}
	
	/**************************************************************************************
	 * @功能：权重设置
	 * @作者：黄崔俊
	 * @日期：2016-1-8 16:27:35
	 *************************************************************************************/
	@RequestMapping("/teaching/gradebook/singleWeightSetting")
	public ModelAndView singleWeightSetting(HttpServletRequest request,@RequestParam Integer cid) {
		ModelAndView mav = new ModelAndView();
		tGradebookService.singleWeightSetting(request,cid);
		mav.setViewName("redirect:/teaching/gradebook/weightSetting?cid="+cid);
		return mav;
	}
	
}