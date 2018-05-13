package net.xidlims.web.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;
import net.xidlims.service.timetable.OuterApplicationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;


@Controller("ApiController")
@SessionAttributes({"selected_labCenter"})
@RequestMapping("/api")
public class ApiController<JsonResult> {
	
	@InitBinder
	public void initBinder(WebDataBinder binder, HttpServletRequest request) { // Register
																				// static
																				// property
																				// editors.
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
	@Autowired
	private OuterApplicationService outerApplicationService;
	
	@RequestMapping(value="/getApi"/*\,produces="text/html;charset=UTF-8"*/,
			produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
	public @ResponseBody String getApi(@RequestParam int term){
		JSONObject aapJson = new JSONObject();  
		List<Map<String, String>> itemList = new ArrayList<Map<String, String>>();
		Map<String, String> map = new HashMap<String, String>();
		map.put("result", "ok");
		itemList.add(map);
    	aapJson.put("callback", itemList);
    	String item = "successCallback("+aapJson.toString()+")";
		return item;
	}
	
	@RequestMapping(value="/getVailLabroom"/*,produces="text/html;charset=UTF-8"*/,
			produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
	public @ResponseBody String getVailLabroom(@RequestParam int term,int weekday,int[] labrooms,int startclass,int endclass,int[] weeks){
		int[] classes = new int[endclass-startclass+1];
		int a= endclass-startclass+1;
		for(int i =0;i<a;i++){
			classes[i]=startclass;
			startclass++;
		}
		boolean timetableConflict = outerApplicationService.isTimetableConflict(term, weekday, labrooms, classes, weeks);
		if(timetableConflict==true){
			return "ok";
		}
		return "no";
	}
	
}
