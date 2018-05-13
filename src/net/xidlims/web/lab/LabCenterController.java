package net.xidlims.web.lab;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.xidlims.constant.CommonConstantInterface;
import net.xidlims.domain.LabCenter;
import net.xidlims.domain.SchoolAcademy;
import net.xidlims.domain.SystemLog;
import net.xidlims.domain.User;
import net.xidlims.service.common.ShareService;
import net.xidlims.service.lab.LabCenterService;
import net.xidlims.service.system.SystemLogService;
import net.xidlims.service.system.SystemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller("LabCenterController")
@SessionAttributes("selected_labCenter")
@RequestMapping("/labCenter")
public class LabCenterController<JsonResult> {

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
	private LabCenterService labCenterService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private  SystemLogService systemLogService;
	/**
	 * 实验中心列表
	 * @author hly
	 * 2015.07.27
	 */
	
	@RequestMapping("/listLabCenter")
	public ModelAndView listLabCenter(@RequestParam int currpage, @ModelAttribute LabCenter labCenter, @ModelAttribute("selected_labCenter") Integer cid){
		ModelAndView mav = new ModelAndView();
		int pageSize = CommonConstantInterface.INT_PAGESIZE;  //分页记录数
		if(labCenter.getId()==null)
		{
			labCenter.setId(cid);
		}
		int totalRecords = labCenterService.findAllLabCenterByQuery(labCenter, 1, -1).size();

		mav.addObject("labCenter", labCenter);
		mav.addObject("pageModel", shareService.getPage(currpage, pageSize, totalRecords));
		mav.addObject("listLabCenter",labCenterService.findAllLabCenterByQuery(labCenter, currpage, pageSize));
		mav.setViewName("lab/lab_center/listLabCenter.jsp");
		return mav;
	}
	
	/**
	 * 新建实验中心
	 * @author hly
	 * 2015.07.27
	 */
	@RequestMapping("/newLabCenter")
	public ModelAndView newLabCenter(){
		ModelAndView mav = new ModelAndView();
		User user = new User();
		user.setUserRole("1");  //在老师中查找
		
		mav.addObject("labCenter", new LabCenter());
		mav.addObject("listSchoolAdademy", systemService.getAllSchoolAcademy(1, -1));
		mav.addObject("listSystemCampus", systemService.getAllSystemCampus(1, -1));
		mav.addObject("listUser", systemService.getAllUser(1, -1, user));
		mav.addObject("listSystemBuild", systemService.getAllSystemBuild(1, -1));
		mav.setViewName("lab/lab_center/editLabCenter.jsp");
		return mav;
	}
	
	/**
	 * 编辑实验中心
	 * @author hly
	 * 2015.07.27
	 */
	@RequestMapping("/editLabCenter")
	public ModelAndView editLabCenter(@RequestParam int labCenterId){
		ModelAndView mav = new ModelAndView();
		User user = new User();
		user.setUserRole("1");  //在老师中查找
		
		mav.addObject("labCenter", labCenterService.findLabCenterByPrimaryKey(labCenterId));
		mav.addObject("listSchoolAdademy", systemService.getAllSchoolAcademy(1, -1));
		mav.addObject("listSystemCampus", systemService.getAllSystemCampus(1, -1));
		mav.addObject("listUser", systemService.getAllUser(1, -1, user));
		mav.addObject("listSystemBuild", systemService.getAllSystemBuild(1, -1));
		mav.setViewName("lab/lab_center/editLabCenter.jsp");
		return mav;
	}
	
	/**
	 * 保存实验中心数据
	 * @author hly
	 * 2015.07.27
	 */
	@RequestMapping("saveLabCenter")
	public String saveLabCenter(@ModelAttribute LabCenter labCenter){
		labCenterService.saveLabCenter(labCenter);
		
		return "redirect:/labCenter/listLabCenter?currpage=1";
	}
	
	/**
	 * 删除实验中心
	 * @author hly
	 * 2015.07.27
	 */
	@RequestMapping("/deleteLabCenter")
	public String deleteLabCenter(@RequestParam int labCenterId){
		labCenterService.deleteLabCenter(labCenterId);
		
		return "redirect:/labCenter/listLabCenter?currpage=1";
	}
	
	/****************************************************************************
	 * 功能：选择前往的中心 作者：李小龙
	 ****************************************************************************/
	@RequestMapping("/selectCenter")
	public ModelAndView selectCenter(HttpServletRequest request,@ModelAttribute("selected_labCenter") Integer cid) {
		
		ModelAndView mav = new ModelAndView();
		User user = shareService.getUser();  // 当前登录人

		List<LabCenter> centers = new ArrayList<LabCenter>();

		
		//西电的逻辑是进系统不再手动选择lab_center，默认选择一个所在学院对应的实验中心
		if (SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString().indexOf("ROLE_SUPERADMIN") != -1){//当用户为超级管理员时，可以查看所有中心
			centers = labCenterService.findAllLabCenterByQuery(new LabCenter(), 1, -1);
		}else{
			SchoolAcademy academy = user.getSchoolAcademy();  // 所属学院
			if (academy != null) 
			{
				centers.addAll(labCenterService.findLabCenterByAcademy(academy.getAcademyNumber()));  // 所属学院下的中心
			}
		}
		
		mav.addObject("user", user);
		mav.addObject("centers", centers);
		/*mav.setViewName("system/checkCenter.jsp");*/
		if(cid != -1){//在已经自动选择lab_center的情况下可以进行手动选择
			mav.setViewName("lab/selectCenter.jsp");
		}else{
			SystemLog currLog = systemLogService.findDefaultCenterByUsernameAndDetail(user.getUsername(), "默认实验中心");
			if(currLog != null){
				mav.setViewName("redirect:/test?labCenterId=" + currLog.getOperationAction());
			}else{
				mav.setViewName("redirect:/test?labCenterId=" + centers.get(0).getId());
			}
		}
		return mav;
	}
}
