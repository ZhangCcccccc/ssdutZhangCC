package net.xidlims.web.device;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.xidlims.dao.LabRoomDeviceDAO;
import net.xidlims.dao.SchoolDeviceDAO;
import net.xidlims.domain.CDictionary;
import net.xidlims.domain.LabRoomDevice;
import net.xidlims.domain.LabRoomDeviceRepair;
import net.xidlims.domain.SchoolDevice;
import net.xidlims.domain.User;
import net.xidlims.service.common.ShareService;
import net.xidlims.service.device.LabRoomDeviceRepairService;
import net.xidlims.service.device.LabRoomDeviceService;

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
 * Spring MVC controller that handles CRUD requests for LabRoomDeviceRepair entities
 * 
 */

@Controller("LabRoomDeviceRepairController")
@SessionAttributes("selected_labCenter")
public class LabRoomDeviceRepairController {

	/**
	 * DAO injected by Spring that manages CLabRoomDeviceChoice entities
	 * 
	 */
	@Autowired
	private LabRoomDeviceDAO labRoomDeviceDAO;
	@Autowired
	private LabRoomDeviceService labRoomDeviceService;
	@Autowired
	private SchoolDeviceDAO schoolDeviceDAO;

	/**
	 * Service injected by Spring that provides CRUD operations for LabRoomDeviceRepair entities
	 * 
	 */
	@Autowired
	private LabRoomDeviceRepairService labRoomDeviceRepairService;
	
	@Autowired
	private ShareService shareService;

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

	
	/****************************************************************************
	 * 功能：实验室设备管理---设备维修
	 * 作者：方正
	 ****************************************************************************/
	@RequestMapping("/device/listLabRoomRDevice")
	public ModelAndView listLabRoomRDevice(@ModelAttribute LabRoomDevice labRoomDevice,@RequestParam Integer page,@ModelAttribute("selected_labCenter") Integer cid){
		ModelAndView mav=new ModelAndView();
		//登录者学院编号
		String academyNumber = shareService.getUser().getSchoolAcademy().getAcademyNumber();
		//本学院的实验室分室
		mav.addObject("rooms", labRoomDeviceRepairService.getLabRoomByAcademy(academyNumber));
		//本学院设备
		mav.addObject("schoolDevices",labRoomDeviceRepairService.getSchoolDevicesByAcademy(academyNumber));
		//查询和当前登录人同一学院的用户
		User user=shareService.getUser();
		List<User> users=shareService.findTheSameCollegeUser(user.getSchoolAcademy().getAcademyNumber());
		//System.out.println(users.size());
		mav.addObject("users",users);
		int pageSize=10;//每页20条记录
		//查询出来的总记录条数
		int totalRecords=labRoomDeviceService.findAllLabRoomDevice(labRoomDevice,cid,1);
		//分页信息
		Map<String,Integer> pageModel =shareService.getPage(page, pageSize,totalRecords);
		//根据分页信息查询出来的记录
		List<LabRoomDevice> listLabRoomDevice=labRoomDeviceService.findAllLabRoomDeviceNew(labRoomDevice,cid,page,pageSize,1);
		mav.addObject("listLabRoomDevice",listLabRoomDevice);
		
		mav.addObject("pageModel",pageModel);
		mav.addObject("totalRecords", totalRecords);
		mav.addObject("page", page);
		mav.addObject("pageSize", pageSize);		
		mav.setViewName("/device/listLabRoomDevices.jsp");
		return mav;
	}
	
	/****************************************************************************
	 * 功能：实验室设备管理---设备维修
	 * 作者：方正
	 ****************************************************************************/
	@RequestMapping("/device/labRoomDeviceRepair/listlabRoomDeviceRepair")
	public ModelAndView labRoomDeviceRepair(@ModelAttribute LabRoomDeviceRepair labRoomDeviceRepair,@RequestParam String td,@RequestParam int page){
		//新建ModelAndView对象；
		ModelAndView mav=new ModelAndView();
		//查询表单的对象
		mav.addObject("labRoomDeviceRepair", labRoomDeviceRepair);
		int pageSize=10;//每页10条记录
		
		//根据设备编号，找出该设备的相关信息
		List<LabRoomDevice> labRoomDevices = labRoomDeviceDAO.executeQuery("select l from LabRoomDevice l where l.schoolDevice.deviceNumber='"+td+"'");
		
		LabRoomDevice labRoomDevice = labRoomDevices.get(0);
		mav.addObject("labRoomDevice", labRoomDevice);
		
		//查询出来的总记录条数
		int totalRecords=labRoomDeviceRepairService.findLabRoomDeviceRepairByLabRoomDeviceRepair(td).size();
		//分页信息
		Map<String,Integer> pageModel =shareService.getPage(page, pageSize,totalRecords);
		//根据分页信息查询出来的记录
		List<LabRoomDeviceRepair> listLabRoomDeviceRepair=labRoomDeviceRepairService.findLabRoomDeviceRepairByLabRoomDeviceRepair(td,page,pageSize);
		mav.addObject("listLabRoomDeviceRepair",listLabRoomDeviceRepair);
		/*Map<Integer, String> map2=labRoomDeviceRepairService.getCLabRoomDeviceRepairStatusMap();
		mav.addObject("map2", map2);*/
		List<CDictionary> cDictionaries = shareService.getCDictionaryData("c_lab_room_device_repair_status");
		mav.addObject("cDictionaries", cDictionaries);
		mav.addObject("pageModel",pageModel);
		mav.addObject("totalRecords", totalRecords);
		mav.addObject("page", page);
		mav.addObject("pageSize", pageSize);
		mav.addObject("td", td);
		mav.setViewName("device/listlabRoomDeviceRepair.jsp");
	    mav.addObject("labRoomDeviceRepair", new LabRoomDeviceRepair());
		return mav;
	}
	
	@RequestMapping("/device/labRoomDeviceRepair/updateLabRoomDeviceRepairRepairRecords")
	public String updateLabRoomDeviceRepairRepairRecords(@ModelAttribute LabRoomDeviceRepair labRoomDeviceRepair) {		
		//根据id查找labRoomDeviceRepair
		LabRoomDeviceRepair extLabRoomDeviceRepair = labRoomDeviceRepairService.findLabRoomDeviceRepairByPrimaryKey(labRoomDeviceRepair.getId());
		//更新RepairRecords
		String td = extLabRoomDeviceRepair.getLabRoomDevice().getSchoolDevice().getDeviceNumber();
		extLabRoomDeviceRepair.setRepairRecords(labRoomDeviceRepair.getRepairRecords());
		extLabRoomDeviceRepair.setRestoreTime(Calendar.getInstance());
		//保存
		labRoomDeviceRepairService.saveLabRoomDeviceRepair(extLabRoomDeviceRepair);		
		return "redirect:/device/labRoomDeviceRepair/listlabRoomDeviceRepair?page=1&td="+td;
	}
	
	@RequestMapping("/device/labRoomDeviceRepair/statusLabRoomDeviceRepairRepairstatus")
	public String statusLabRoomDeviceRepairRepairstatus(@ModelAttribute LabRoomDeviceRepair labRoomDeviceRepair) {		
		//根据id查找labRoomDeviceRepair
		LabRoomDeviceRepair extLabRoomDeviceRepair1 = labRoomDeviceRepairService.findLabRoomDeviceRepairByPrimaryKey(labRoomDeviceRepair.getId());
		//更新RepairRecords
		String td = extLabRoomDeviceRepair1.getLabRoomDevice().getSchoolDevice().getDeviceNumber();
		/*extLabRoomDeviceRepair1.setCLabRoomDeviceRepairStatus(labRoomDeviceRepair.getCLabRoomDeviceRepairStatus());*/
		extLabRoomDeviceRepair1.setCDictionaryByStatusId(labRoomDeviceRepair.getCDictionaryByStatusId());
		//保存
		labRoomDeviceRepairService.saveLabRoomDeviceRepair(extLabRoomDeviceRepair1);		
		return "redirect:/device/labRoomDeviceRepair/listlabRoomDeviceRepair?page=1&td="+td;
	}
	
	
	@RequestMapping("/device/newDeviceService")
	public ModelAndView newDeviceService(@RequestParam Integer td){
		//新建ModelAndView对象；
		ModelAndView mav=new ModelAndView();
		//将该Model映射到newDeviceService.jsp;
		mav.addObject("labRoomDeviceRepair", new LabRoomDeviceRepair());
		/*Map<Integer, String> map=labRoomDeviceRepairService.getCLabRoomDeviceChoiceMap();
		mav.addObject("map", map);*/
		List<CDictionary> choices = shareService.getCDictionaryData("c_lab_room_device_choice");
		mav.addObject("choices", choices);
		//mav.addObject("labRoomDeviceRepair1", new LabRoomDeviceRepair());
		/*Map<Integer, String> map1=labRoomDeviceRepairService.getCLabRoomDevicePartitionMap();
		mav.addObject("map1", map1);*/
		List<CDictionary> partitions = shareService.getCDictionaryData("c_lab_room_device_partition");
		mav.addObject("partitions", partitions);
		User user=shareService.getUser();
		mav.addObject("username", user.getUsername());	
		mav.addObject("td", td);
		mav.setViewName("/device/newDeviceService.jsp");
		return mav;
	}
	
	/*********************************************************************************
     * @description:保存设备
	 * @author:方正2014/07/07
	 ************************************************************************************/
	
	
	@RequestMapping("/device/labRoomDeviceRepair/saveLabRoomDeviceRepair")
	public String saveLabRoomDeviceRepair(@ModelAttribute LabRoomDeviceRepair labRoomDeviceRepair,@RequestParam String td) {		
		SchoolDevice i = schoolDeviceDAO.findSchoolDeviceByDeviceNumber(td);
		labRoomDeviceRepair.getLabRoomDevice().setSchoolDevice(i);
		labRoomDeviceRepairService.saveLabRoomDeviceRepair(labRoomDeviceRepair);			
		return "redirect:/device/labRoomDeviceRepair/listlabRoomDeviceRepair?page=1&td="+td;
	}
	
	@RequestMapping("/device/labRoomDeviceRepair/detailList")
	public String detailList(@ModelAttribute LabRoomDeviceRepair labRoomDeviceRepair) {		
		return "redirect:/device/labRoomDeviceRepair/listlabRoomDeviceRepair?page=1";
	}
	
}