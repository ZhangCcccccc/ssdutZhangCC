package net.xidlims.web.system;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.xidlims.constant.CommonConstantInterface;
import net.xidlims.domain.SystemLog;
import net.xidlims.service.common.ShareService;
import net.xidlims.service.system.SystemLogService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

/**
 * Spring MVC controller that handles CRUD requests for SystemLog entities
 * 
 */

@Controller("SystemLogController")
@SessionAttributes("selected_labCenter")
public class SystemLogController {

	@Autowired private SystemLogService systemLogService;
	@Autowired private ShareService shareService;
	
	@InitBinder
	public void initBinder(WebDataBinder binder, HttpServletRequest request) { // Register static property editors.
		binder.registerCustomEditor(java.util.Calendar.class, new org.skyway.spring.util.databinding.CustomCalendarEditor());
		binder.registerCustomEditor(byte[].class, new org.springframework.web.multipart.support.ByteArrayMultipartFileEditor());
		binder.registerCustomEditor(boolean.class, new org.skyway.spring.util.databinding.EnhancedBooleanEditor(false));
		binder.registerCustomEditor(Boolean.class, new org.skyway.spring.util.databinding.EnhancedBooleanEditor(true));
		binder.registerCustomEditor(java.math.BigDecimal.class, new org.skyway.spring.util.databinding.NaNHandlingNumberEditor(java.math.BigDecimal.class, true));
		binder.registerCustomEditor(Integer.class, new org.skyway.spring.util.databinding.NaNHandlingNumberEditor(Integer.class, true));
		binder.registerCustomEditor(java.util.Date.class, new org.skyway.spring.util.databinding.CustomDateEditor());
		binder.registerCustomEditor(String.class, new org.skyway.spring.util.databinding.StringEditor());
		binder.registerCustomEditor(Long.class, new org.skyway.spring.util.databinding.NaNHandlingNumberEditor(Long.class, true));
		binder.registerCustomEditor(Double.class, new org.skyway.spring.util.databinding.NaNHandlingNumberEditor(Double.class, true));
	}
	
	
	/***********************************************
	 * 功能：列出系统日志
	 * 作者：贺子龙
	 * 日期：2015-12-21
	 **********************************************/
	@RequestMapping("/log/listOperationLog")
	public ModelAndView listOperationLog(@RequestParam int currpage,@ModelAttribute SystemLog systemLog,
			@ModelAttribute("selected_labCenter") Integer cid,HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		int pageSize = CommonConstantInterface.INT_PAGESIZE;
		int totalRecords=systemLogService.findSystemLogs(systemLog,cid,1,-1,request).size();
		List<SystemLog>  operationLogs=systemLogService.findSystemLogs(systemLog,cid,currpage,pageSize,request);
		Map<String, String> userDetailMap=systemLogService.getUserMap(cid);
		mav.addObject("userDetailMap", userDetailMap);
		mav.addObject("operationLogs", operationLogs);
		mav.addObject("pageModel", shareService.getPage(currpage, pageSize, totalRecords));
		mav.addObject("pageSize", pageSize);
		mav.addObject("currpage", currpage);
		mav.setViewName("reports/systemLog/listOperationLog.jsp");
		return mav;
	}
	
	/***********************************************
	 * 功能：删除系统日志
	 * 作者：贺子龙
	 * 日期：2015-12-22
	 **********************************************/
	@RequestMapping("/log/deleteOperationLog")
	public ModelAndView deleteOperationLog(@RequestParam String logIds){
		ModelAndView mav = new ModelAndView();
		systemLogService.deleteSystemLog(logIds);
		mav.setViewName("redirect:/log/listOperationLog?currpage=1");
		return mav;
	}

}