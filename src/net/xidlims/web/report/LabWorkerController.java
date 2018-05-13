package net.xidlims.web.report;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.xidlims.domain.LabWorker;
import net.xidlims.domain.OperationItem;
import net.xidlims.service.common.ShareService;
import net.xidlims.service.report.LabWorkerService;

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

@Controller("LabWorkerController")
@SessionAttributes("selected_labCenter")
@RequestMapping("/report")
public class LabWorkerController<JsonResult>{
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
	private LabWorkerService labWorkerService;
	
	/*****************************************************************
	 * @专任实验室人员列表
	 * @作者：陈乐为
	 * @日期：2016-01-06
	 *****************************************************************/
	@RequestMapping("/listLabWorker")
	public ModelAndView projectsummary(HttpServletRequest request, 
			@ModelAttribute LabWorker labWorker, 
			@RequestParam int currpage)throws ParseException{
		ModelAndView mav = new ModelAndView();
		int pageSize = 20;
		int totalRecords = labWorkerService.Count();
		
		Map<String, Integer> pageModel = shareService.getPage(currpage, pageSize,totalRecords);
		mav.addObject("pageModel", pageModel);
		//获取当前页的页码
		mav.addObject("page", currpage);
		mav.addObject("totalRecords", totalRecords);
		mav.addObject("labWorkers", labWorkerService.findAllLabWorker(currpage, pageSize, labWorker));
		// 获取登录用户信息
		mav.addObject("user", shareService.getUserDetail());
		
		mav.setViewName("reports/labWorker.jsp");
		
		return mav;
	}
	
	
	
}
