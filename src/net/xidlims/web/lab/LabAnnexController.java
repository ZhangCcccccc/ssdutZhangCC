package net.xidlims.web.lab;

import java.util.Calendar;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import net.xidlims.constant.CommonConstantInterface;
import net.xidlims.domain.LabAnnex;
import net.xidlims.domain.LabCenter;
import net.xidlims.domain.LabRoom;
import net.xidlims.service.common.ShareService;
import net.xidlims.service.lab.LabAnnexService;
import net.xidlims.service.lab.LabCenterService;
import net.xidlims.service.system.SystemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller("LabAnnexController")
@SessionAttributes("selected_labCenter")
@RequestMapping("/labAnnex")
public class LabAnnexController<JsonResult> {

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
	private LabAnnexService labAnnexService;
	@Autowired
	private LabCenterService labCenterService;
	@Autowired
	private SystemService systemService;
	
	/****************************************************************************
	 * 功能：获取labannex列表
	 * 作者：徐文
	 * 日期：2016-4-26
	 ****************************************************************************/
	@RequestMapping("/listLabAnnex")
	public ModelAndView listLabAnnex(@RequestParam int currpage, @ModelAttribute LabAnnex labAnnex,@ModelAttribute("selected_labCenter") Integer cid){
		ModelAndView mav = new ModelAndView();
		int pageSize = CommonConstantInterface.INT_PAGESIZE;
		
		
		int totalRecords = labAnnexService.findAllLabAnnexByQuery(1, -1, labAnnex,cid).size();
		List<LabAnnex> listLabAnnex=labAnnexService.findAllLabAnnexByQuery(currpage, pageSize, labAnnex,cid);
		
				for (LabAnnex labAnnex2 : listLabAnnex) {
					Set<LabRoom> labRooms=labAnnex2.getLabRooms();
					int labNumber=0;
					if (labRooms!=null&&labRooms.size()>0) {
					  for (LabRoom labRoom : labRooms) {
							labNumber=labNumber+1;
					  }
					}
					labAnnex2.setLabNumber(labNumber+"");
					labAnnexService.saveLabAnnex(labAnnex2);
				}
				
		mav.addObject("cid", cid);
		mav.addObject("listLabAnnex", listLabAnnex);
		mav.addObject("pageModel", shareService.getPage(currpage, pageSize, totalRecords));
		mav.setViewName("lab/lab_annex/listLabAnnex.jsp");
		return mav;
	}
	/**********************************************************************************************
	 * 功能：新建实验室
	 * 姓名：徐文
	 * 日期：2016-4-27
	 *************************************************************************************************/
	@RequestMapping("/newLabAnnex")
	public ModelAndView newLabAnnex(@RequestParam int labCenterId){
		ModelAndView mav = new ModelAndView();
		//实验室类别
		LabCenter labCenter=labCenterService.findLabCenterByPrimaryKey(labCenterId);

		LabAnnex labAnnex = new LabAnnex();
		labAnnex.setCreatedAt(Calendar.getInstance());
		
		mav.addObject("isNew", 1);
		mav.addObject("CDictionary", shareService.getCDictionaryData("c_lab_annex_type")); 
		//mav.addObject("labCenterName", labCenter.getCenterName());
		mav.addObject("labCenterId", labCenterId);
		mav.addObject("labAnnex", labAnnex);
		mav.addObject("subject12s", systemService.getAllSystemSubject12(1, -1));  //学科数据(12版)
		mav.addObject("listLabCenter", labCenterService.findAllLabCenterByQuery(new LabCenter(), 1, -1));
		mav.setViewName("lab/lab_annex/newLabAnnex.jsp");
		return mav;
	}
	/**************************************************************************************
	 * 功能：保存实验室数据
	 * 姓名：徐文
	 * 日期：2016-4-27
	 ***************************************************************************************/
	@RequestMapping("/saveLabAnnex")
	public String saveLabAnnex(@ModelAttribute LabAnnex labAnnex){
		labAnnex.setLabCenter(labCenterService.findLabCenterByPrimaryKey(12));
		labAnnexService.saveLabAnnex(labAnnex);
		
		return "redirect:/labAnnex/listLabAnnex?currpage=1";
	}
	/*******************************************************************************************
	 * 功能：删除实验室数据
	 * 姓名：徐文
	 * 日期：2016-4-27
	 ******************************************************************************************/
	@RequestMapping("/deleteLabAnnex")
	public String deleteLabAnnex(@RequestParam int labAnnexId){
		labAnnexService.deleteLabAnnex(labAnnexId);  
		
		return "redirect:/labAnnex/listLabAnnex?currpage=1";
	}
	/*****************************************************************************************
	 * 功能：编辑实验室
	 * 姓名：徐文
	 * 日期：2016-4-28
	 *****************************************************************************************/
	@RequestMapping("/editLabAnnex")
	public ModelAndView editLabAnnex(@RequestParam int labAnnexId){
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("isNew", 0);
		mav.addObject("CDictionary", shareService.getCDictionaryData("c_lab_annex_type"));
		mav.addObject("labAnnex", labAnnexService.findLabAnnexByPrimaryKey(labAnnexId));
		mav.addObject("subject12s", systemService.getAllSystemSubject12(1, -1));  //学科数据(12版)
		mav.addObject("listLabCenter", labCenterService.findAllLabCenterByQuery(new LabCenter(), 1, -1));
		mav.setViewName("lab/lab_annex/newLabAnnex.jsp");
		return mav;
	}
	/****************************************************************************
	 * 功能：查看实验室详细信息
	 * 作者：徐文
	 * 日期：2016-4-28
	 ****************************************************************************/
	@RequestMapping("/getLabAnnex")
	public ModelAndView getLabAnnex(@RequestParam int id){
		//新建ModelAndView对象；
		ModelAndView mav=new ModelAndView();
		//id对应的实验室
		LabAnnex labAnnex=labAnnexService.findLabAnnexByPrimaryKey(id);
		mav.addObject("labAnnex", labAnnex);
		
		mav.setViewName("lab/lab_annex/getLabAnnex.jsp");
		return mav;
	}
}