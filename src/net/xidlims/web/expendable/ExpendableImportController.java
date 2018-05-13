package net.xidlims.web.expendable;

import javax.servlet.http.HttpServletRequest;

import net.xidlims.service.common.ShareService;
import net.xidlims.service.expendable.ExpendableImportService;
import net.xidlims.service.expendable.ExpendableService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller("ExpendableImportController")
@SessionAttributes("selected_labCenter")
@RequestMapping("/expendable")
public class ExpendableImportController<JsonResult> {

	
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
	private ExpendableService expendableService;
	@Autowired
	private ExpendableImportService expendableImportService;
	/***********************************************************************************
	 * @功能：耗材管理--在线耗材记录（导入耗材记录）
	 * @author 郑昕茹
	 * @日期：2016-07-27
	 * **********************************************************************************/
	@RequestMapping("/importExpendable")
	public ModelAndView importExpendable(HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		String fileName = expendableImportService.getUpdateFilePath(request);
		String logoRealPathDir = request.getSession().getServletContext().getRealPath("/");
		String filePath = logoRealPathDir + fileName;
		System.out.println(filePath);
		if(filePath.endsWith("xls")||filePath.endsWith("xlsx")){
			expendableImportService.importExpendable(filePath);
		}
		mav.setViewName("redirect:/expendable/listExpendableInUse?currpage=1");
		return mav;
	}
	
}