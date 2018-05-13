package net.xidlims.web.teaching;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.xidlims.constant.CommonConstantInterface;
import net.xidlims.dao.SchoolCourseInfoDAO;
import net.xidlims.dao.TCourseSiteDAO;
import net.xidlims.dao.TCourseSiteUserDAO;
import net.xidlims.dao.UserDAO;
import net.xidlims.domain.SchoolCourseInfo;
import net.xidlims.domain.SchoolTerm;
import net.xidlims.domain.TCourseSite;
import net.xidlims.domain.TCourseSiteUser;
import net.xidlims.domain.User;
import net.xidlims.service.common.ShareService;
import net.xidlims.service.tcoursesite.TCourseSiteService;
import net.xidlims.service.timetable.OuterApplicationService;
import net.xidlims.service.timetable.SchoolCourseInfoService;

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
 * 功能：课程站点管理模块 作者：魏诚 时间：2014-07-14
 *****************************************************************************************/
@Controller("TeachingCourseSiteController")
@SessionAttributes({"selected_courseSite","selected_labCenter"})
public class TeachingCourseSiteController<JsonResult> {
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
	private OuterApplicationService outerApplicationService;
	@Autowired
	private ShareService shareService;
	@Autowired
	private TCourseSiteDAO tCourseSiteDAO;
	@Autowired
	private SchoolCourseInfoService schoolCourseInfoService;
	@Autowired
	private SchoolCourseInfoDAO schoolCourseInfoDAO;
	@Autowired
	private TCourseSiteService tCourseSiteService;
	@Autowired
	private TCourseSiteUserDAO tCourseSiteUserDAO;
	@Autowired
	private UserDAO userDAO;
	

	/**************************************************************************************
	 * @课程站点 查看课程站点
	 * @作者：魏诚
	 * @日期：2014-07-25
	 *************************************************************************************/
	@RequestMapping("/teaching/coursesite/listCourseSites")
	public ModelAndView listCourseCodes(@ModelAttribute TCourseSite tCourseSite,
			@RequestParam int currpage,Integer termId,String type) {
		ModelAndView mav = new ModelAndView();

		if (termId == null) {
			SchoolTerm schoolTerm = shareService.getBelongsSchoolTerm(Calendar.getInstance());
			if (schoolTerm!=null) {
				termId = schoolTerm.getId();
			}
		}
		// 设置分页变量并赋值为20
		int pageSize = CommonConstantInterface.INT_PAGESIZE;
		// 设置用户的总记录数并赋值
		int totalRecords = tCourseSiteService.getTCourseSiteTotalRecordsByTerm(-1, termId,tCourseSite);
		Map<String, Integer> pageModel = shareService.getPage(currpage, pageSize, totalRecords);
		// 将pageModel映射到pageModel
		mav.addObject("pageModel", pageModel);
		// 将currpage映射到page，用来获取当前页的页码
		mav.addObject("page", currpage);
		// 将totalRecords映射到totalRecords，用来获取总记录数
		mav.addObject("totalRecords", totalRecords);

		mav.addObject("tCourseSite", tCourseSite);
		//选课组列表
		List<TCourseSite> tCourseSites = tCourseSiteService.findTCourseSitesByTerm(-1, termId, currpage, pageSize,tCourseSite);
		mav.addObject("tCourseSites",tCourseSites);
		
		mav.addObject("termId", termId);
		mav.addObject("terms", outerApplicationService.getSchoolTermList());
		mav.addObject("type", type);
		mav.setViewName("teaching/coursesite/listCourseSites.jsp");
		return mav;
	}

	/**************************************************************************************
	 * @自主排课 新建选课组页面
	 * @作者：魏诚
	 * @日期：2015-07-24
	 *************************************************************************************/
	@RequestMapping("/teaching/coursesite/newCourseSite")
	public ModelAndView newCourseSite(@RequestParam int id) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("flagId", id);

		TCourseSite tCourseSite = new TCourseSite();
		if (id != -1) {
			tCourseSite = tCourseSiteDAO.findTCourseSiteByPrimaryKey(id);
		}
		mav.addObject("tCourseSite", tCourseSite);
		// 获取学期列表
		List<SchoolTerm> schoolTerms = outerApplicationService.getSchoolTermList();
		mav.addObject("schoolTerms", schoolTerms);
		
		// 获取所有课程列表
		List<Object> objects = schoolCourseInfoService.findAllSchoolCourseInfo();
		List<SchoolCourseInfo> schoolCourseInfos = new ArrayList<SchoolCourseInfo>();
		for (Object object : objects) {
			SchoolCourseInfo schoolCourseInfo = new SchoolCourseInfo();
			Object[] obj = (Object[])object;
			schoolCourseInfo.setCourseNumber((String)obj[0]);
			schoolCourseInfo.setCourseName((String)obj[1]);
			schoolCourseInfos.add(schoolCourseInfo);
		}
		mav.addObject("schoolCourses", schoolCourseInfos);
		// 获取可选的教师列表列表
		User user = shareService.getUser();
		Map<Object, Object> teacherMap = tCourseSiteService.getCourseSiteTearcherMap(user);
		mav.addObject("timetableTearcherMap", teacherMap);

		
		mav.setViewName("/teaching/coursesite/newCourseSite.jsp");
		return mav;
	}

	/**************************************************************************************
	 * @自主排课 查看选课组学生选课名单清单页面
	 * @页面跳转：listSelfTimetable-newCourseCodeIframeMain-newCourseCodeIframeDetail
	 * @作者：魏诚
	 * @日期：2014-07-25
	 *************************************************************************************/
	@RequestMapping("/teaching/coursesite/listTimetableCourseStudents")
	public ModelAndView listTimetableCourseStudents(@RequestParam int id) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("id", id);
		// 获取可选的教师列表列表
		mav.addObject("tCourseSiteUsers", tCourseSiteUserDAO.executeQuery(
				"select c from TimetableCourseStudent c where c.tCourseSite.id =" + id, 0, -1));
		// mav.addObject("tCourseSite", new TimetableSelfCourse());

		mav.setViewName("teaching/coursesite/listTimetableCourseStudents.jsp");
		return mav;
	}

	/**************************************************************************************
	 * @自主排课 批量删除学生名单
	 * @作者：魏诚
	 * @日期：2014-07-25
	 *************************************************************************************/
	@RequestMapping("/teaching/coursesite/batchDeleteCourseStudents")
	public ModelAndView batchDeleteCourseStudents(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		String[] ids = request.getParameterValues("VoteOption1");
		for (String id : ids) {
			tCourseSiteUserDAO.remove(tCourseSiteUserDAO.findTCourseSiteUserById(Integer
					.parseInt(id)));
		}

		mav.setViewName("redirect:/teaching/coursesite/listTimetableCourseStudents?id="
				+ request.getParameter("id"));
		return mav;
	}

	/**************************************************************************************
	 * @自主排课 保存 新建自主排课的选课组
	 * @页面跳转：listSelfTimetable-newCourseCodeIframeDetail-saveCourseCodeIframeDetail
	 * @作者：魏诚
	 * @日期：2014-07-25
	 *************************************************************************************/
	@RequestMapping("/teaching/coursesite/saveTCourseSite")
	public ModelAndView saveTCourseSite(HttpServletRequest request,
			@ModelAttribute TCourseSite tCourseSite, @RequestParam int flagID) {
		ModelAndView mav = new ModelAndView();
		String returnUrl = tCourseSiteService.saveTCourseSite(request, tCourseSite, flagID);
		mav.setViewName(returnUrl); 
		
		return mav;
	}

	/**************************************************************************************
	 * @自主排课 保存 删除自主排课的选课组
	 * @作者：魏诚
	 * @日期：2014-07-25
	 *************************************************************************************/
	@RequestMapping("/teaching/coursesite/deleteTCourseSite")
	public ModelAndView deleteTCourseSite(@RequestParam int id) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/teaching/coursesite/listCourseSites?currpage=1");
		// 删除指定id的TCourseSite
		//tCourseSiteDAO.remove(tCourseSiteDAO.findTCourseSiteById(id));
		TCourseSite oldTCourseSite = tCourseSiteDAO.findTCourseSiteById(id);
		oldTCourseSite.setStatus(0);//改状态数值假删除
		tCourseSiteDAO.store(oldTCourseSite);
		return mav;
	}

	/**************************************************************************************
	 * @显示课程信息的主显示页面
	 * @作者：魏诚
	 * @日期：2014-07-14
	 *************************************************************************************/
	@RequestMapping("/teaching/coursesite/listSchoolCourseInfo")
	public ModelAndView listSchoolCourseInfo(@ModelAttribute SchoolCourseInfo schoolCourseInfo,
			@RequestParam int currpage) {
		ModelAndView mav = new ModelAndView();
		// 设置分页变量并赋值为20；
		int pageSize = CommonConstantInterface.INT_PAGESIZE;
		// 根据课程及id获取课程排课列表的计数
		int totalRecords = schoolCourseInfoService.getCountCourseInfoByQuery(schoolCourseInfo);
		mav.addObject("totalRecords", totalRecords);
		mav.addObject("schoolCourseInfo", schoolCourseInfo);
		// 获取登陆用户信息
		mav.addObject("user", shareService.getUserDetail());
		Map<String, Integer> pageModel = shareService.getPage(currpage, pageSize, totalRecords);
		mav.addObject("pageModel", pageModel);
		// 将currpage映射到page，用来获取当前页的页码
		mav.addObject("page", currpage);
		// 维护自建课程
		mav.addObject("schoolCourseInfoList",
				schoolCourseInfoService.getCourseInfoByQuery(schoolCourseInfo, 0, currpage, pageSize));
		mav.setViewName("teaching/coursesite/listSelfSchoolCourseInfo.jsp");
		return mav;
	}

	/**************************************************************************************
	 * @自主排课 新建自主排课的新建课程页面
	 * @页面跳转：listSelfTimetable-newCourseCodeIframeMain-newCourseCodeIframeDetail
	 * @作者：魏诚
	 * @日期：2014-07-25
	 *************************************************************************************/
	@RequestMapping("/teaching/coursesite/newSelfSchoolCourseInfo")
	public ModelAndView newSelfSchoolCourseInfo(@RequestParam String courseNumber) {
		ModelAndView mav = new ModelAndView();
		// 登陆用户
		mav.addObject("user", shareService.getUserDetail());
		// 获取最大的id
		int maxId = schoolCourseInfoService.getSelfSchoolCourseInfoTotalRecords();
		mav.addObject("maxId", maxId + 1);
		// id为-1时，新增，否则为修改
		mav.addObject("flagId", courseNumber);
		//
		SchoolCourseInfo schoolCourseInfo = new SchoolCourseInfo();
		if (!courseNumber.equals("-1")) {
			schoolCourseInfo = schoolCourseInfoDAO.findSchoolCourseInfoByCourseNumber(courseNumber);

		}
		mav.addObject("schoolCourseInfo", schoolCourseInfo);
		mav.setViewName("/teaching/coursesite/newSelfSchoolCourseInfo.jsp");
		return mav;
	}
	
	/************************************************************
	 * @自建选课组-获取班级信息
	 * @作者：魏诚
	 * @日期：2015-07-09
	 ************************************************************/
	@RequestMapping("/teaching/coursesite/getSchoolClasses")
	public @ResponseBody
	Map<String, String> getSchoolClasses(@RequestParam String grade, @ModelAttribute("selected_labCenter") Integer cid) {
		// 返回可用的星期信息
		Map<String, String> map = new HashMap<String, String>();
		for (User user : userDAO.executeQuery("select c from User c where c.schoolAcademy.academyNumber like '"
				+ shareService.getUserDetail().getSchoolAcademy().getAcademyNumber()/*labCenterDAO.findLabCenterById(cid).getSchoolAcademy().getAcademyNumber()*/
				+ "' and grade like '" + grade + "' group by c.schoolClasses.classNumber order by c.schoolClasses.classNumber ")) {
			if(user.getSchoolClasses()!=null){
				map.put(user.getSchoolClasses().getClassNumber(), user.getSchoolClasses().getClassName());

			}
		}
		return map;
	}
	
	/************************************************************
	 * @自建选课组-获取班级学生信息
	 * @作者：魏诚
	 * @日期：2015-07-09
	 ************************************************************/
	@RequestMapping("/teaching/coursesite/getSchoolClassesUser")
	public @ResponseBody
	Map<String, String> getSchoolClassesUser(@RequestParam String classNumber, @ModelAttribute("selected_labCenter") Integer cid) {
		// 返回可用的星期信息
		Map<String, String> map = new HashMap<String, String>();
		String sql = "select c from User c where c.schoolAcademy.academyNumber like '"
				+ shareService.getUserDetail().getSchoolAcademy().getAcademyNumber()/*labCenterDAO.findLabCenterById(cid).getSchoolAcademy().getAcademyNumber()*/
				+ "' and c.schoolClasses.classNumber like '" + classNumber + "' order by c.username ";
		List<User> users = userDAO.executeQuery(sql,0,-1);
		for (User user : users) {
			if(user.getSchoolClasses()!=null){
				map.put(user.getUsername(), user.getCname());
			}
		}
		return map;
	}
	/**************************************************************************************
	 * @功能： 查看站点下所有学生列表
	 * @作者：裴继超
	 * @日期：2014-09-25
	 *************************************************************************************/
	@RequestMapping("/teaching/coursesite/ListCourseStudents")
	public ModelAndView ListCourseStudents(@RequestParam int id) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("teaching/coursesite/listStudents.jsp");
		
		List<TCourseSiteUser> Liststudents=tCourseSiteService.findCourseSiteUserBysiteId(id);

		mav.addObject("liststudents", Liststudents);

		
		return mav;
	}
	/**************************************************************************************
	 * @功能： 查看站点下所有学生列表
	 * @作者：裴继超
	 * @日期：2014-09-25
	 *************************************************************************************/
	@RequestMapping("/teaching/coursesite/manageStudents")
	public ModelAndView manageStudents(@RequestParam Integer id) {
		ModelAndView mav = new ModelAndView();
		
		List<TCourseSiteUser> tCourseSiteUsers=tCourseSiteService.findCourseSiteUserBysiteId(id);
		
		mav.addObject("tCourseSiteUsers", tCourseSiteUsers);
		mav.addObject("tCourseSite", tCourseSiteService.findCourseSiteById(id));
		mav.setViewName("teaching/coursesite/manageStudents.jsp");
		
		return mav;
	}
	
	/**************************************************************************************
	 * @功能：导入学生
	 * @作者：黄崔俊
	 * @日期：2016-3-7 14:48:18
	 *************************************************************************************/
	@RequestMapping("/teaching/coursesite/importStudents")
	public ModelAndView importStudents(HttpServletRequest request,@RequestParam Integer cid) {
		ModelAndView mav = new ModelAndView();
		// 文件名称
		String fileName = shareService.getUpdateFileSavePath(request);
		// 服务器地址
		String logoRealPathDir = request.getSession().getServletContext().getRealPath("/");
		// 文件的全部地址
		String filePath = logoRealPathDir + fileName;

		if (fileName.endsWith("xls") || fileName.endsWith("xlsx")) {
			try{
				tCourseSiteService.importStudentsXls(filePath, cid);
			}catch(Exception e){
				e.printStackTrace();
			}			
		}
		
		mav.setViewName("redirect:/tcoursesite/student/courseStudentsList?tCourseSiteId="+cid+"&currpage=1");
		return mav;
	}
	
	/**************************************************************************************
	 * @功能： 查看站点下所有学生列表
	 * @作者：裴继超
	 * @日期：2014-09-25
	 *************************************************************************************/
	@RequestMapping("/teaching/coursesite/deleteTCourseSiteUserById")
	public ModelAndView deleteTCourseSiteUserById(@RequestParam Integer id) {
		ModelAndView mav = new ModelAndView();
		TCourseSiteUser tCourseSiteUser = tCourseSiteService.findCourseSiteUserById(id);
		Integer cid = tCourseSiteUser.getTCourseSite().getId();
		tCourseSiteService.deleteCourseSiteUser(tCourseSiteUser);
		mav.setViewName("redirect:/teaching/coursesite/manageStudents?id="+cid);
		
		return mav;
	}
	
	/**************************************************************************************
	 * @功能： 查看站点下所有学生列表
	 * @作者：裴继超
	 * @日期：2014-09-25
	 *************************************************************************************/
	@RequestMapping("/teaching/coursesite/deleteTCourseSiteUserByIds")
	public ModelAndView deleteTCourseSiteUserByIds(@RequestParam Integer id,String ids) {
		ModelAndView mav = new ModelAndView();
		
		tCourseSiteService.deleteCourseSiteUserByIds(ids);
		
		mav.setViewName("redirect:/teaching/coursesite/manageStudents?id="+id);
		
		return mav;
	}
}