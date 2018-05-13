/****************************************************************************
 * 命名规范：
 * 本控制层调整必须是按照如下的路径调整：@RequestMapping("/device/system/sample")，全部小写。
 * 列表信息listxxxx，獲取信息：getxxxx 编辑信息：editxxxx 删除信息：detelexxxx 新增信息 newxxxx
 * 导出信息exportxxxx 保存信息：savexxxx 
 ****************************************************************************/

package net.xidlims.web.system;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.xidlims.constant.CommonConstantInterface;
import net.xidlims.service.common.ShareService;
import net.xidlims.service.system.TimeDetailService;
//import net.xidlims.domain.LabRoomDeviceReservation;
import net.xidlims.domain.SystemCampus;
import net.xidlims.domain.SystemTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/****************************************************************************
 * 功能：系统后台管理模块
 * 作者：魏诚
 * 时间：2014-07-14
 ****************************************************************************/
@Controller("TimeController")
public class TimeController<JsonResult>{
	/************************************************************ 
	 * @初始化WebDataBinder，这个WebDataBinder用于填充被@InitBinder注释的处理
	 * 方法的command和form对象
	 *
	 ************************************************************/
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
	
	@Autowired
	TimeDetailService timeDetailService;
	@Autowired
	ShareService shareService;
	/*********************************************************************************
	 * 功能:节次管理
	 * 作者:李小龙
	 ************************************************************************************/
	@RequestMapping("/system/timeManage")
	public ModelAndView timeManage() {
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("system/time/systemTimeIframe.jsp");
		return mav;
	}
	
	/************************************************************ 
	 * @内容：延安路校区节次列表
	 * @作者：叶明盾
	 * @日期：2014-07-30
	 ************************************************************/
	@RequestMapping("/system/listTime")
	public ModelAndView listTime(@RequestParam int currpage,String campusNumber) {
		ModelAndView mav = new ModelAndView();		
		// 设置分页变量并赋值为20；
		int pageSize = CommonConstantInterface.INT_PAGESIZE;
		//设置节次表的总记录数并赋值
		int totalRecords = timeDetailService.getTimeTotalRecords(campusNumber);
		Map<String, Integer> pageModel = shareService.getPage(currpage, pageSize, totalRecords);
		mav.addObject("newFlag", true);
		mav.addObject("pageModel", pageModel);
		mav.addObject("page", currpage);
		mav.addObject("totalRecords", totalRecords);
		mav.addObject("user", new SystemTime());
		mav.addObject("times", timeDetailService.findAllTimes(currpage, pageSize,campusNumber));
		// 将该Model映射到listTerm.jsp;
		mav.addObject("campusNumber", campusNumber);
		mav.setViewName("system/time/listTime.jsp");
		return mav;
	}
	
	/************************************************************ 
	 * @内容：新增节次
	 * @作者：叶明盾
	 * @日期：2014-07-28
	 ************************************************************/
	@RequestMapping("/system/newTime")
	public ModelAndView newTime(HttpServletRequest request,@RequestParam String campusNumber)
	{
		ModelAndView mav = new ModelAndView();
		//获取系统的当前时间
		Calendar now = Calendar.getInstance();
		//格式化获取到的date，获取yyyy-MM-dd		
		mav.addObject("newFlag", true);
		SimpleDateFormat date=new SimpleDateFormat("HH:mm:ss");
		mav.addObject("date",date.format(now.getTime()));
//		mav.addObject("page", shareService.getCurrpage(request));
		mav.addObject("time", new SystemTime());
		//校区
		SystemCampus cmp=timeDetailService.findSystemCampusByPromaryKey(campusNumber);
		mav.addObject("cmp", cmp);
		mav.addObject("campusNumber", campusNumber);
		mav.setViewName("system/time/newTime.jsp");
		return mav;
	}
	
	/************************************************************ 
	 * @内容：保存新增节次
	 * @作者：叶明盾
	 * @日期：2014-07-28
	 ************************************************************/
	@RequestMapping("/system/saveNewTime")
	public String saveNewTime(@ModelAttribute SystemTime systemTime)
	{
		SystemTime t=timeDetailService.saveTime(systemTime);
		return "redirect:/system/listTime?currpage=1&campusNumber="+t.getSystemCampus().getCampusNumber();		
	}
	
	/************************************************************ 
	 * @内容：判断节次名称是否重复
	 * @作者：叶明盾
	 * @日期：2014-07-29
	 ************************************************************/
	@RequestMapping("/system/checkTime")
	public @ResponseBody	String checkTime(@RequestParam String timeName,String campusNumber)
	{
		List<SystemTime> teimes = timeDetailService.findTimesByTimeNameAndCampusNumber(timeName,campusNumber);
		
		if (teimes.size() == 0)
		{
			return "no";
		}
		else
		{
			return "ok";
		}
	}
	
	/************************************************************ 
	 * @内容：查看所选的节次信息
	 * @作者：叶明盾
	 * @日期：2014-07-32
	 ************************************************************/
	@RequestMapping("/system/editTime")
	public ModelAndView  editTime(@RequestParam Integer idKey, String campusNumber)
	{
		ModelAndView mav = new ModelAndView();
		mav.addObject("time",timeDetailService.findTimeById(idKey));
		//校区
		SystemCampus cmp=timeDetailService.findSystemCampusByPromaryKey(campusNumber);
		mav.addObject("cmp", cmp);
		mav.addObject("campusNumber", campusNumber);
		mav.setViewName("system/time/newTime.jsp");
		return mav;
	}
	
	/************************************************************ 
	 * @内容：判断学期名称是否重复
	 * @作者：叶明盾
	 * @日期：2014-07-29
	 ************************************************************/
	@RequestMapping("/system/checkDate")
	public @ResponseBody	String checkDate(@RequestParam String startDate, String endDate)  throws Exception
	{
		//转换时间格式
		DateFormat fmt =new SimpleDateFormat("HH:mm:ss");
		//新建一个Date的变量sDate赋值为null 
		Date sDate = null;
		//新建一个Date的变量eDate赋值为null
        Date eDate = null;
        //将startDate转换成date型
        sDate= fmt.parse(startDate);
        //将endDate转换成date型
        eDate= fmt.parse(endDate);
		//判断节次的开始时间是否大于结束时间
		if (sDate.getTime() < eDate.getTime())			
		{
			return "no";
		}
		else
		{
			return "ok";
		}
	}
}