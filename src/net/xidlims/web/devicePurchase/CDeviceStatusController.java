package net.xidlims.web.devicePurchase;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import net.xidlims.dao.CDeviceStatusDAO;
import net.xidlims.dao.NDevicePurchaseDAO;

import net.xidlims.domain.CDeviceStatus;
import net.xidlims.domain.CDeviceSupplier;
import net.xidlims.domain.NDevicePurchase;
import net.xidlims.service.common.ShareService;
import net.xidlims.service.devicePurchase.CDeviceStatusService;
 

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
 * Spring MVC controller that handles CRUD requests for CDeviceStatus entities
 * 
 */
   
@Controller("CDeviceStatusController")
@SessionAttributes("selected_labCenter")
@RequestMapping("/devicePurchase")
public class CDeviceStatusController {

	 
	@Autowired
	private CDeviceStatusDAO cDeviceStatusDAO; 
	@Autowired
	private NDevicePurchaseDAO nDevicePurchaseDAO;
	@Autowired
	private CDeviceStatusService cDeviceStatusService;
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
	
	/***********************************************************************************
	 * @功能：设备申购管理--设备字典（设备状态字典）
	 * @author 郑昕茹
	 * @日期：2016-07-20
	 * **********************************************************************************/
	@RequestMapping("/listDeviceStatus")
	public ModelAndView listDeviceStatus(@RequestParam int currpage){
		ModelAndView mav = new ModelAndView();
		int pageSize = 30; //设置分页
		//获取所有设备状态信息
		List<CDeviceStatus> listCDeviceStatus = cDeviceStatusService.findCDeviceStatus(currpage, pageSize); 
		//获取总记录数
		int totalRecords =  cDeviceStatusService.findCDeviceStatus(1, -1).size();
		//获取正常状态的总记录数
		int totalNormalRecords = cDeviceStatusService.findCDeviceStatusByFlag(1,-1).size();
		//分页
		Map<String, Integer> pageModel = shareService.getPage(currpage, pageSize, totalRecords);
		//映射到jsp
		mav.addObject("listCDeviceStatus",listCDeviceStatus);
		mav.addObject("pageModel",pageModel);
		mav.addObject("totalRecords",totalRecords);
		mav.addObject("totalNormalRecords",totalNormalRecords);
		mav.addObject("currpage", currpage);
		mav.addObject("pageSize", pageSize);
		 
		mav.setViewName("devicePurchase/listCDeviceStatus.jsp");
		return mav;
	}
	/***********************************************************************************
	 * @功能：设备申购管理--设备字典（新建设备状态）
	 * @author 郑昕茹
	 * @日期：2016-07-20
	 * **********************************************************************************/
	@RequestMapping("/newDeviceStatus")
	public ModelAndView newDeviceStatus(){
		ModelAndView mav = new ModelAndView();
		CDeviceStatus cDeviceStatus = new CDeviceStatus();
		mav.addObject("cDeviceStatus", cDeviceStatus);
		//isNew标志是新建还是编辑
		mav.addObject("isNew", 1);
		//oldFlag表示原来的设备状态类型
		mav.addObject("oldFlag",1);
		mav.setViewName("devicePurchase/newCDeviceStatus.jsp");
		
		return mav;
	}
	/***********************************************************************************
	 * @功能：设备申购管理--设备字典（编辑设备状态）
	 * @author 郑昕茹
	 * @日期：2016-07-20
	 * **********************************************************************************/
	@RequestMapping("/editDeviceStatus")
	public ModelAndView editDeviceStatus(@RequestParam Integer id){
		ModelAndView mav = new ModelAndView();
		//根据主键查找到要修改的设备状态信息
		CDeviceStatus cDeviceStatus = cDeviceStatusService.findCDeviceStatusByPrimaryKey(id);
		//oldFlag表示编辑前原来的设备状态类型
		int oldFlag = cDeviceStatus.getFlag();
		
		//映射到jsp
		mav.addObject("cDeviceStatus", cDeviceStatus); 
		mav.addObject("isNew", 0);
		mav.addObject("oldFlag",oldFlag);
		mav.setViewName("devicePurchase/newCDeviceStatus.jsp");  
		return mav;
	}
	/***********************************************************************************
	 * @功能：设备申购管理--设备字典（保存设备状态）
	 * @author 郑昕茹
	 * @日期：2016-07-20
	 * **********************************************************************************/
	@RequestMapping("/saveDeviceStatus")
	public String saveDeviceStatus(@ModelAttribute CDeviceStatus cDeviceStatus,@RequestParam Integer isNew,@RequestParam Integer oldFlag){
		//分情况进行保存操作
		//若是新建,并且保存的设备状态信息的类型是正常状态时
		if(isNew == 1 && cDeviceStatus.getFlag() == 1){
			//找到类型为正常状态的设备信息的记录数
			int totalRecords =  cDeviceStatusService.findCDeviceStatusByFlag(1, -1).size();
			//将新建的设备状态信息的statusOrder值设为正常状态的总记录数加1
			cDeviceStatus.setStatusOrder(totalRecords+1);
			cDeviceStatusService.saveCDeviceStatus(cDeviceStatus);//保存修改或者新建的数据
			//将所有非正常状态的设备信息的记录的statusOrder整体后移一位
			cDeviceStatusService.MoveOrder();
		} 
		else if(isNew == 1 && cDeviceStatus.getFlag() == 0){ //若是新建,并且保存的设备状态信息的类型是特殊状态时
			//获取总记录数
			int totalRecords =  cDeviceStatusService.findCDeviceStatus(1, -1).size();
			//将新建的设备状态信息的statusOrder值设为总记录数加1
			cDeviceStatus.setStatusOrder(totalRecords+1);
			cDeviceStatusService.saveCDeviceStatus(cDeviceStatus);//保存修改或者新建的数据
		} //若是编辑并且设备状态信息的类型发生改变时（从正常变为特殊或从特殊变为正常）
		else if(isNew == 0 && oldFlag != cDeviceStatus.getFlag()){
			//将所有设备状态信息的statusOrder全部进行改变，使正常状态的信息的statusOrder始终小于特殊状态的信息的statusOrder
			cDeviceStatusService.reOrderAfterChangeFlag(cDeviceStatus.getFlag(),cDeviceStatus);
			
		}//其他情况直接保存
		else cDeviceStatusService.saveCDeviceStatus(cDeviceStatus);
		return "redirect:/devicePurchase/listDeviceStatus?currpage=1"; 
	}
	/***********************************************************************************
	 * @功能：设备申购管理--保存设备状态
	 * @author 郑昕茹
	 * @日期：2016-07-20
	 * **********************************************************************************/
	@RequestMapping("/deleteDeviceStatus")
	public String deleteDeviceStatus(@RequestParam Integer id){ 
		cDeviceStatusService.deleteCDeviceStatus(id);//根据id删除数据
		return "redirect:/devicePurchase/listDeviceStatus?currpage=1"; 
	}
	/***********************************************************************************
	 * @功能：设备申购管理--设备字典（上移设备状态顺序）
	 * @author 郑昕茹
	 * @日期：2016-07-22
	 * **********************************************************************************/
	@RequestMapping("/addStatusOrder")
	public String addStatusOrder(@RequestParam Integer id){ 
		//根据主键获取该条设备状态信息
		CDeviceStatus currentCDeviceStatus = cDeviceStatusService.findCDeviceStatusByPrimaryKey(id);
		//获取该条设备状态信息的当前顺序
		Integer currentOrder= currentCDeviceStatus.getStatusOrder();
		//若不是第一条，都可以进行上移操作
		if(currentOrder != 1){
			//获取要改变的顺序
			Integer changeOrder = currentOrder - 1;
			//根据顺序找到上一条设备状态信息
			CDeviceStatus lastCDeviceStatus = cDeviceStatusService.findCDeviceStatusByStatusOrder(currentOrder-1);
			//设置该条设备信息的顺序为changeOrder
			currentCDeviceStatus.setStatusOrder(changeOrder);
			//设置上一条设备状态信息的顺序为currentOrder
			lastCDeviceStatus.setStatusOrder(currentOrder);
			//保存两条设备状态信息
			cDeviceStatusService.saveCDeviceStatus(currentCDeviceStatus);
			cDeviceStatusService.saveCDeviceStatus(lastCDeviceStatus);
		}
		
		return "redirect:/devicePurchase/listDeviceStatus?currpage=1"; 
	} 
	/***********************************************************************************
	 * @功能：设备申购管理--（设备字典）下移设备状态顺序
	 * @author 郑昕茹
	 * @日期：2016-07-22
	 * **********************************************************************************/
	@RequestMapping("/subStatusOrder")
	public String subStatusOrder(@RequestParam Integer id){ 
		//根据主键获取该条设备状态信息
		CDeviceStatus cDeviceStatus1 = cDeviceStatusService.findCDeviceStatusByPrimaryKey(id);
		//获取该条设备状态信息的顺序
		Integer currentOrder = cDeviceStatus1.getStatusOrder();
		//下移后的顺序
		Integer changeOrder = currentOrder + 1;
		//找到最后一条，即statusOrder值最大的正常状态的设备状态信息
		CDeviceStatus maxCDeviceStatus = cDeviceStatusService.findMaxStatusOrder();
		//若当前设备状态信息不是最后一条可以进行下移操作
		if(currentOrder < maxCDeviceStatus.getStatusOrder()){
			//根据顺序找到下一条设备状态信息
			CDeviceStatus cDeviceStatus2 = cDeviceStatusService.findCDeviceStatusByStatusOrder(currentOrder+1);
			//设置该条设备信息的顺序为changeOrder
			cDeviceStatus1.setStatusOrder(changeOrder);
			//设置下一条设备状态信息的顺序为currentOrder
			cDeviceStatus2.setStatusOrder(currentOrder);
			//保存两条设备状态信息
			cDeviceStatusService.saveCDeviceStatus(cDeviceStatus1);
			cDeviceStatusService.saveCDeviceStatus(cDeviceStatus2);
		}
		
		return "redirect:/devicePurchase/listDeviceStatus?currpage=1"; 
	} 
	/***********************************************************************************
	 * @功能：设备申购管理--设备字典（设备供应商列表）
	 * @author 郑昕茹
	 * @日期：2016-07-26
	 * **********************************************************************************/
	@RequestMapping("/listCDeviceSupplier")
	public ModelAndView listCDeviceSupplier(@RequestParam int currpage,@ModelAttribute CDeviceSupplier cDeviceSupplier){
		ModelAndView mav = new ModelAndView();
		int pageSize = 30; //设置分页
		//获取所有设备状态信息
		List<CDeviceSupplier> listCDeviceSuppliers = cDeviceStatusService.findCDeviceSuppliers(currpage, pageSize, cDeviceSupplier);
		//获取总记录数
		int totalRecords =  cDeviceStatusService.findCDeviceSuppliers(1, -1, cDeviceSupplier).size();
		Map<String, Integer> pageModel = shareService.getPage(currpage, pageSize, totalRecords);
		//映射到jsp
		mav.addObject("listCDeviceSuppliers",listCDeviceSuppliers);
		mav.addObject("cDeviceSupplier",cDeviceSupplier);
		mav.addObject("pageModel",pageModel);
		mav.addObject("totalRecords",totalRecords);
		mav.addObject("currpage", currpage);
		mav.addObject("pageSize", pageSize);
		mav.setViewName("devicePurchase/listCDeviceSupplier.jsp");
		return mav;
	}
	/***********************************************************************************
	 * @功能：设备申购管理--（设备字典）新建设备供应商
	 * @author 郑昕茹
	 * @日期：2016-07-26
	 * **********************************************************************************/
	@RequestMapping("/newCDeviceSupplier")
	public ModelAndView newCDeviceSupplier(){
		ModelAndView mav = new ModelAndView();
		CDeviceSupplier cDeviceSupplier = new CDeviceSupplier();
		mav.addObject("cDeviceSupplier", cDeviceSupplier);
		//isNew标志新建状态
		mav.addObject("isNew", 1);
		mav.setViewName("devicePurchase/newCDeviceSupplier.jsp");
		
		return mav;
	}
	/***********************************************************************************
	 * @功能：设备申购管理--（设备字典）编辑设备供应商
	 * @author 郑昕茹
	 * @日期：2016-07-26
	 * **********************************************************************************/
	@RequestMapping("/editCDeviceSupplier")
	public ModelAndView editCDeviceSupplier(@RequestParam Integer id){
		ModelAndView mav = new ModelAndView();
		//根据主键查找到要修改的设备状态信息
		CDeviceSupplier cDeviceSupplier = cDeviceStatusService.findCDeviceSupplierByPrimaryKey(id);
		mav.addObject("cDeviceSupplier", cDeviceSupplier); 
		//标志为编辑状态
		mav.addObject("isNew", 0);
		mav.setViewName("devicePurchase/newCDeviceSupplier.jsp");  
		return mav;
	}
	/***********************************************************************************
	 * @功能：设备申购管理--（设备字典）保存设备供应商
	 * @author 郑昕茹
	 * @日期：2016-07-26
	 * **********************************************************************************/
	@RequestMapping("/saveCDeviceSupplier")
	public String saveCDeviceSupplier(@ModelAttribute CDeviceSupplier cDeviceSupplier){
		cDeviceStatusService.saveCDeviceSupplier(cDeviceSupplier);
		return "redirect:/devicePurchase/listCDeviceSupplier?currpage=1"; 
	}
	/***********************************************************************************
	 * @功能：设备申购管理--保存设备供应商
	 * @author 郑昕茹
	 * @日期：2016-07-26
	 * **********************************************************************************/
	@RequestMapping("/deleteCDeviceSupplier")
	public String deleteCDeviceSupplier(@RequestParam Integer id){ 
		cDeviceStatusService.deleteCDeviceSupplier(id);//删除 数据
		return "redirect:/devicePurchase/listCDeviceSupplier?currpage=1"; 
	}
}