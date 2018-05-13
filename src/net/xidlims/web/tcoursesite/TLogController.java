/****************************************************************************
 * 命名规范：
 * 本控制层调整必须是按照如下的路径调整：@RequestMapping("/tcoursesite/upload/xxxx")。
 * 列表信息listxxxx，獲取信息：getxxxx 编辑信息：editxxxx 删除信息：detelexxxx 新增信息 newxxxx
 * 导出信息exportxxxx 保存信息：savexxxx 
 ****************************************************************************/

package net.xidlims.web.tcoursesite;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.xidlims.dao.WkFolderDAO;
import net.xidlims.dao.TCourseSiteDAO;
import net.xidlims.dao.TCourseSiteUserDAO;
import net.xidlims.dao.WkCourseDAO;
import net.xidlims.dao.WkLessonDAO;
import net.xidlims.dao.WkUploadDAO;
import net.xidlims.domain.Authority;
import net.xidlims.domain.LabCenter;
import net.xidlims.domain.SchoolAcademy;
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
import net.xidlims.service.log.LogService;
import net.xidlims.service.tcoursesite.WkChapterService;
import net.xidlims.service.tcoursesite.WkFolderService;
import net.xidlims.service.tcoursesite.WkLessonService;
import net.xidlims.service.tcoursesite.WkService;
import net.xidlims.service.tcoursesite.WkUploadService;
import net.xidlims.service.tcoursesite.TCourseSiteService;
import net.xidlims.web.aop.SystemServiceLog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
@Controller("TLogController")
public class TLogController<JsonResult> {
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
	private TCourseSiteUserDAO tCourseSiteUserDAO;
	@Autowired
	private TCourseSiteDAO tCourseSiteDAO;
	@Autowired
	private WkCourseDAO wkCourseDAO;
	@Autowired
	private WkLessonDAO wkLessonDAO;
	@Autowired
	private WkChapterService wkChapterService;
	@Autowired
	private WkLessonService wkLessonService;
	@Autowired
	private WkService wkService;
	@Autowired
	private WkUploadDAO wkUploadDAO;
	@Autowired
	private WkFolderDAO folderDAO;
	@Autowired
	private WkUploadService wkUploadService;
	@Autowired
	private WkFolderService wkFolderService;
	@Autowired
	private LogService logService;
	
	/**************************************************************************
	 * Description:学习行为-学习行为列表
	 * 
	 * @author：裴继超
	 * @date ：2016年7月8日16:51:41
	 **************************************************************************/
	@SystemServiceLog("学习行为")
	@ResponseBody
	@RequestMapping("/tcoursesite/log")
	public ModelAndView listMessages(@RequestParam Integer tCourseSiteId) {
		ModelAndView mav = new ModelAndView();
		// 选择的课程中心
		TCourseSite tCourseSite = tCourseSiteService.findCourseSiteById(tCourseSiteId);
		mav.addObject("tCourseSite", tCourseSite);
		//当前登录人
		User user = shareService.getUser();
		mav.addObject("user", user);
		String data = "" + tCourseSiteId;
		//访问量
		int siteLogSize = logService.findLogSize("","","进入课程平台",data,"","");
		mav.addObject("siteLogSize", siteLogSize);
		//访问用户数
		int logUsersSize = logService.findLogUsersSize("","","",data,"","");
		mav.addObject("logUsersSize", logUsersSize);
		//站点成员人数
		int siteUsersSize = tCourseSite.getTCourseSiteUsers().size();
		mav.addObject("siteUsersSize", siteUsersSize);
		//int siteLogUsersSize = logService.findSiteLogUsersSize("","","",data,"","");
		//mav.addObject("siteLogUsersSize", siteLogUsersSize);
		//事件
		int siteEventLogSize = logService.findLogSize("","","",data,"","");
		mav.addObject("siteEventLogSize", siteEventLogSize);
		//访问最多的模块
		List<Object> moduleObjects = logService.findMostSiteEventLogsSize("", "module", "", "", "", "");
		mav.addObject("mostSiteModuleLogSize", moduleObjects.get(0));
		//访问最多的用户
		List<Object> userObjects = logService.findMostSiteEventLogsSize("userid", "", "", "", "", "");
		mav.addObject("mostSiteUserLogSize", userObjects.get(0));
		//文件数量
		int fileFolderSize = wkFolderService.findFileFolderSize(tCourseSiteId);
		mav.addObject("fileFolderSize", fileFolderSize);
		mav.setViewName("tcoursesite/log/log.jsp");
		return mav;
	}
	
	
	/**************************************************************************
	 * Description:学习行为-获取最近7天
	 * 
	 * @author：裴继超
	 * @date ：2016年7月8日16:51:41
	 **************************************************************************/
	@ResponseBody
	@RequestMapping("/tcoursesite/nearSevenDays")
	public List<String> nearSevenDays(@RequestParam String dayOrMoon ,Integer daySize) {
		//当前时间
		Date date = Calendar.getInstance().getTime();
		//获取最近七天,格式date
		List<Date> list = logService.dateToWeek(date,daySize);
		//新建列表
		List<String> strings = new ArrayList();
		//设置时间格式
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
		//循环时间列表，并添加到string列表里
		for(Date d:list){
			String dateString =sdf.format(d.getTime());
			strings.add(dateString);
		}
		return strings;
	}

}