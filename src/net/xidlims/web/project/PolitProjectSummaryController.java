package net.xidlims.web.project;

import java.text.ParseException;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.xidlims.dao.MessageDAO;
import net.xidlims.dao.SystemMajor12DAO;
import net.xidlims.domain.OperationItem;
import net.xidlims.domain.SchoolTerm;
import net.xidlims.service.common.ShareService;
import net.xidlims.service.device.LabRoomDeviceService;
import net.xidlims.service.lab.LabCenterService;
import net.xidlims.service.lab.LabRoomService;
import net.xidlims.service.operation.OperationService;
import net.xidlims.service.project.ProjectSummaryService;
import net.xidlims.service.system.SystemLogService;
import net.xidlims.service.system.SystemService;
import net.xidlims.service.timetable.OuterApplicationService;
import net.xidlims.service.timetable.SchoolCourseInfoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller("PolitProjectSummaryController")
@SessionAttributes("selected_labCenter")
@RequestMapping("/operation")
public class PolitProjectSummaryController<JsonResult> {

	@InitBinder
	public void initBinder(WebDataBinder binder, HttpServletRequest request) { // Register // static // property // editors.
		binder.registerCustomEditor(java.util.Calendar.class,new org.skyway.spring.util.databinding.CustomCalendarEditor());
		binder.registerCustomEditor(byte[].class,new org.springframework.web.multipart.support.ByteArrayMultipartFileEditor());
		binder.registerCustomEditor(Boolean.class, new org.skyway.spring.util.databinding.EnhancedBooleanEditor(true));
		binder.registerCustomEditor(java.math.BigDecimal.class,new org.skyway.spring.util.databinding.NaNHandlingNumberEditor(java.math.BigDecimal.class, true));
		binder.registerCustomEditor(Integer.class, new org.skyway.spring.util.databinding.NaNHandlingNumberEditor(Integer.class, true));
		binder.registerCustomEditor(java.util.Date.class, new org.skyway.spring.util.databinding.CustomDateEditor());
		binder.registerCustomEditor(String.class, new org.skyway.spring.util.databinding.StringEditor());
		binder.registerCustomEditor(Long.class, new org.skyway.spring.util.databinding.NaNHandlingNumberEditor(Long.class, true));
		binder.registerCustomEditor(Double.class, new org.skyway.spring.util.databinding.NaNHandlingNumberEditor(Double.class, true));
	}
	
	@Autowired
	private ShareService shareService;
	@Autowired
	private OuterApplicationService outerApplicationService;
	@Autowired
	private ProjectSummaryService projectSummaryService;
	@Autowired
	private LabCenterService labCenterService;

/*****************************************************************
 * @实验项目列表
 * @作者：陈乐为
 * @日期：2016-01-04
 *****************************************************************/
@RequestMapping("/project/projectsummary")
public ModelAndView projectsummary(HttpServletRequest request, 
		@ModelAttribute OperationItem operationItem,
		@RequestParam int currpage,@ModelAttribute("selected_labCenter") Integer sid)throws ParseException{
	ModelAndView mav = new ModelAndView();
	
	int totalRecords = projectSummaryService.Count(sid);
	int pageSize = 20;
	Map<String, Integer> pageModel = shareService.getPage(currpage, pageSize,totalRecords);
	mav.addObject("pageModel", pageModel);
	//获取当前页的页码
	mav.addObject("page", currpage);
	mav.addObject("totalRecords", totalRecords);
	int term = shareService.getBelongsSchoolTerm(Calendar.getInstance()).getId();
	if (request.getParameter("term") != null) {
		term = Integer.parseInt(request.getParameter("term"));
	}
	operationItem.setLabCenter(labCenterService.findLabCenterByPrimaryKey(sid));
	mav.addObject("ProjectSummaries", projectSummaryService.findAllOperationItem(currpage, pageSize, operationItem,sid));
	mav.addObject("term", term);
	// 获取登录用户信息
	mav.addObject("user", shareService.getUserDetail());
	// 获取所有的学期
	List<SchoolTerm> schoolTerms = outerApplicationService.getSchoolTermList();
	mav.addObject("schoolTerms", schoolTerms);
	
	mav.setViewName("project/projectSummary.jsp");
	return mav;
}

/*****************************************************************
 * @内容：根据条件查询
 * @作者：陈乐为
 * @日期：2016-01-05
 *****************************************************************/
@RequestMapping("/project/searchProjectSummary")
public ModelAndView searchProjectSummary(HttpServletRequest request, 
		@ModelAttribute OperationItem operationItem,
		@RequestParam int currpage,Integer roomid, @ModelAttribute("selected_labCenter") Integer sid){
	ModelAndView mav = new ModelAndView();
	int pageSize = 20;
	int totalRecords = projectSummaryService.findAllOperationItemByQueryCount(operationItem, roomid, sid);
	Map<String, Integer> pageModel = shareService.getPage(currpage, pageSize,totalRecords);
	mav.addObject("pageModel", pageModel);
	//获取当前页的页码
	mav.addObject("page", currpage);
	mav.addObject("totalRecords", totalRecords);
	
	mav.addObject("ProjectSummaries", projectSummaryService.findOperationItemsByQuery(currpage, pageSize, roomid, operationItem,sid));
	mav.addObject("pageModel", shareService.getPage(currpage, pageSize, totalRecords));
	mav.addObject("schoolTerms", shareService.findAllSchoolTerm());  //所有学期
	//获取当前学期
	SchoolTerm schoolTerm = shareService.getBelongsSchoolTerm(Calendar.getInstance());
	mav.addObject("schoolTerm", schoolTerm);
	mav.addObject("page", currpage);
	mav.setViewName("project/searchProjectSummary.jsp");
	
	return mav;
}

}