package net.xidlims.web.devicePurchase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.xidlims.dao.CDeviceSupplierDAO;
import net.xidlims.dao.DevicePurchaseDetailCSupplierDAO;
import net.xidlims.dao.DeviceStatusRecordDAO;
import net.xidlims.domain.CDeviceStatus;
import net.xidlims.domain.DevicePurchaseDetailCSupplier;
import net.xidlims.domain.DeviceStatusRecord;
import net.xidlims.domain.NDeviceAuditRecord;
import net.xidlims.domain.NDevicePurchase;
import net.xidlims.domain.NDevicePurchaseDetail;
import net.xidlims.domain.User;
import net.xidlims.service.common.ShareService;
import net.xidlims.service.devicePurchase.CDeviceStatusService;
import net.xidlims.service.devicePurchase.DevicePurchaseDetailService;
import net.xidlims.service.devicePurchase.DevicePurchaseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller("DevicePurchaseController")
@SessionAttributes("selected_labCenter")
@RequestMapping("/devicePurchase")
public class DevicePurchaseController<JsonResult> {

	
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
	private DevicePurchaseService devicePurchaseService;
	@Autowired
	private DevicePurchaseDetailService devicePurchaseDetailService;
	@Autowired
	private ShareService shareService;
	@Autowired
	private CDeviceStatusService cDeviceStatusService;
	@Autowired
	private DeviceStatusRecordDAO deviceStatusRecordDAO;
	@Autowired
	private CDeviceSupplierDAO cDeviceSupplierDAO;
	@Autowired
	private DevicePurchaseDetailCSupplierDAO devicePurchaseDetailCSupplierDAO;
	
	/***********************************************************************************
	 * @功能：设备申购管理--设备申购
	 * @author 徐文
	 * @日期：2016-07-19
	 * **********************************************************************************/
	@RequestMapping("/listNDevicePurchase")
	public ModelAndView listNDevicePurchase(@RequestParam int currpage,@ModelAttribute NDevicePurchase nDevicePurchase){
		ModelAndView mav = new ModelAndView();
		int pageSize = 30;//设置分页
		//将当前设备状态的结束日期与当前系统时间进行比较，进行设备状态与结束时间的更新，并将状态改变信息存入设备状态记录表
		devicePurchaseService.changeDeviceStatusByCurrentTime(currpage, pageSize); 
		User user = shareService.getUser();//获得当前登录人
		//获取当前登录人设备申购的所有信息（因为审核状态没有为9的值，没有进后台关于状态筛选的方法sql 
		List<NDevicePurchase> listNDevicePurchase=devicePurchaseService.findNDevicePurchase(currpage,9, pageSize, nDevicePurchase,user);
		//获取所有不同申购编号的申购记录
		List<NDevicePurchase> listPurchaseNumbers = devicePurchaseService.findAllPurchaseNumber();
		//获取总条数
		int totalRecords = devicePurchaseService.findNDevicePurchase(1,9, -1, nDevicePurchase,user).size();
		//翻页相关
		Map<String, Integer> pageModel = shareService.getPage(currpage, pageSize, totalRecords);
		mav.addObject("listNDevicePurchase", listNDevicePurchase);//传值
		mav.addObject("listPurchaseNumbers", listPurchaseNumbers);
		mav.addObject("pageModel", pageModel);
		mav.addObject("nDevicePurchase", nDevicePurchase);
		mav.addObject("totalRecords", totalRecords);
		mav.addObject("currpage", currpage);
		mav.addObject("pageSize", pageSize);
		mav.setViewName("devicePurchase/listNDevicePurchase.jsp");
		return mav;
	}
	/***********************************************************************************
	 * @功能：设备申购管理--编辑设备申购
	 * @author 徐文
	 * @日期：2016-07-19
	 * **********************************************************************************/
	@RequestMapping("/editDevicePurchase")
	public ModelAndView editDevicePurchase(Integer id){
		ModelAndView mav = new ModelAndView();
		//通过主键找到申购信息
		mav.addObject("nDevicePurchase", devicePurchaseService.findDevicePurchaseByPrimaryKey(id));
		//获取字典表中经费类型c_n_device_purchase_type和申购原因c_n_device_purchase_reason
		mav.addObject("costType", shareService.getCDictionaryData("c_n_device_purchase_type"));
		mav.addObject("CostReason", shareService.getCDictionaryData("c_n_device_purchase_reason"));
		mav.addObject("isNew", 0);//标志位表示修改
		mav.setViewName("devicePurchase/newDevicePurchase.jsp");
		return mav;
	}
	
	/***********************************************************************************
	 * @功能：设备申购管理--新建设备申购
	 * @author 徐文
	 * @日期：2016-07-19
	 * **********************************************************************************/
	@RequestMapping("/newDevicePurchase")
	public ModelAndView newDevicePurchase(){
		ModelAndView mav = new ModelAndView();
		//获取字典表中经费类型和申购原因
		mav.addObject("costType", shareService.getCDictionaryData("c_n_device_purchase_type"));
		mav.addObject("CostReason", shareService.getCDictionaryData("c_n_device_purchase_reason"));
		mav.addObject("nDevicePurchase", new NDevicePurchase());//新增数据
		mav.addObject("isNew", 1);//标志位表示新增
		mav.setViewName("devicePurchase/newDevicePurchase.jsp");
		return mav;
	}
	/***********************************************************************************
	 * @功能：设备申购管理--保存设备申购
	 * @author 徐文
	 * @日期：2016-07-19
	 * **********************************************************************************/
	@RequestMapping("/saveDevicePurchase")
	public String saveDevicePurchase(@ModelAttribute NDevicePurchase nDevicePurchase, @RequestParam int isNew){
		nDevicePurchase = devicePurchaseService.saveDevicePurchase(nDevicePurchase);//保存修改或者新建的数据
		nDevicePurchase.setAuditStatus(0);
		//若为新建对应的设备申购保存，则将当前设备状态设置为设备状态字典表中顺序最小的设备状态
		if(isNew == 1){
			//找到设备状态字典表中statusOrder最小的设备状态信息
			CDeviceStatus cDeviceStatus = cDeviceStatusService.findMinStatusOrder(); 
			nDevicePurchase.setCDeviceStatus(cDeviceStatus);
		}
		//获取随机数6位
		String randomCode = devicePurchaseService.getRandomNumber(6);
		//获取当前时间
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String dateStr = sdf.format(calendar.getTime());
		//生成申购编号
		String purchaseNumber = dateStr + randomCode ;
		nDevicePurchase.setPurchaseNumber(purchaseNumber);
		devicePurchaseService.saveDevicePurchase(nDevicePurchase);
		return "redirect:/devicePurchase/viewNDevicePurchase?id="+nDevicePurchase.getId();
	}
	/***********************************************************************************
	 * @功能：设备申购管理--删除设备申购
	 * @author 徐文
	 * @日期：2016-07-19
	 * **********************************************************************************/
	@RequestMapping("/deleteDevicePurchase")
	public String deleteDevicePurchase(@RequestParam Integer id){
		devicePurchaseService.deleteDevicePurchase(id);  
		return "redirect:/devicePurchase/listNDevicePurchase?currpage=1";
	}
	/***********************************************************************************
	 * @功能：设备申购管理--查看&&detail的新建
	 * @author 徐文
	 * @日期：2016-07-20
	 * **********************************************************************************/
	@RequestMapping("/viewNDevicePurchase")
	public ModelAndView viewNDevicePurchase(@RequestParam Integer id){
		ModelAndView mav = new ModelAndView();
        //通过主键找到所有的设备申购
		NDevicePurchase nDevicePurchase = devicePurchaseService.findDevicePurchaseByPrimaryKey(id);
		mav.addObject("nDevicePurchase", nDevicePurchase);
		mav.addObject("nDevicePurchaseDetails", nDevicePurchase.getNDevicePurchaseDetails());
		//新增设备申购detail的数据
		mav.addObject("nDevicePurchaseDetail", new NDevicePurchaseDetail());
		//找到AuthorityId为2即老师的所有用户
		mav.addObject("cDeviceSuppliers", devicePurchaseDetailService.getCDeviceSuppliers());
		mav.addObject("users",shareService.findUsersByAuthorityId(2));
		mav.addObject("id",id);
		mav.addObject("isNew",1);
		mav.setViewName("devicePurchase/viewNDevicePurchase.jsp");
		return mav;
	}
	/***********************************************************************************
	 * @功能：设备申购管理--保存设备申购detail
	 * @author 徐文
	 * @日期：2016-07-20
	 * **********************************************************************************/
	@RequestMapping("/savedevicePurchaseDetail")
	public String savedevicePurchaseDetail(HttpServletRequest request,@ModelAttribute NDevicePurchaseDetail nDevicePurchaseDetail,@RequestParam Integer id){
		//将设备申购detail中设备申购相关字段设置进去
		nDevicePurchaseDetail.setNDevicePurchase(devicePurchaseService.findDevicePurchaseByPrimaryKey(id));
		//保存修改或者新建的数据
		nDevicePurchaseDetail=devicePurchaseDetailService.saveNDevicePurchaseDetail(nDevicePurchaseDetail);
		/***
		 * 1.获取到设备供应商表的id[]
		 * 2.将供应商id设置set进“中间表”
		 * 3.将设备id也设置进去
		 * 4.保存
		 * */
		String[] suppliers = request.getParameterValues("supplier");
		for (String supplier : suppliers) {
			DevicePurchaseDetailCSupplier devicePurchaseDetailCSupplier = new DevicePurchaseDetailCSupplier();
			devicePurchaseDetailCSupplier.setCDeviceSupplier(cDeviceSupplierDAO.findCDeviceSupplierByPrimaryKey(Integer.valueOf(supplier)));
			devicePurchaseDetailCSupplier.setNDevicePurchaseDetail(devicePurchaseDetailService.findDevicePurchaseDetailByPrimaryKey(nDevicePurchaseDetail.getId()));
			devicePurchaseDetailCSupplierDAO.store(devicePurchaseDetailCSupplier);
		}
		//通过主键找到所以的设备申购信息
		NDevicePurchase nDevicePurchase = devicePurchaseService.findDevicePurchaseByPrimaryKey(id);
		//保存后审核状态变为0
		nDevicePurchase.setAuditStatus(0);
		devicePurchaseService.saveDevicePurchase(nDevicePurchase);//保存修改或者新建的数据
		return "redirect:/devicePurchase/viewNDevicePurchase?id="+id;
	}
	/***********************************************************************************
	 * @功能：设备申购管理--提交新增设备
	 * @author 徐文
	 * @日期：2016-07-21
	 * **********************************************************************************/
	@RequestMapping("/submitDevicePurchase")
	public String submitDevicePurchase(@RequestParam Integer id){
        //通过主键找到所有的设备申购
		NDevicePurchase nDevicePurchase = devicePurchaseService.findDevicePurchaseByPrimaryKey(id);
		//点击提交后设备状态由未提交（0）变为未审核（1）
		nDevicePurchase.setAuditStatus(1);
		devicePurchaseService.saveDevicePurchase(nDevicePurchase);//保存修改或者新建的数据
		return "redirect:/devicePurchase/listNDevicePurchase?currpage=1";
	}
	/***********************************************************************************
	 * @功能：设备申购管理--查看设备申购的detail
	 * @author 徐文
	 * @日期：2016-07-21
	 * **********************************************************************************/
	@RequestMapping("/getNDevicePurchase")
	public ModelAndView getNDevicePurchase(@RequestParam Integer id){
		ModelAndView mav = new ModelAndView();
        //通过主键找到所有的设备申购
		NDevicePurchase nDevicePurchase = devicePurchaseService.findDevicePurchaseByPrimaryKey(id);
		mav.addObject("nDevicePurchase", nDevicePurchase);
		mav.addObject("nDevicePurchaseDetails", nDevicePurchase.getNDevicePurchaseDetails());
		//新增设备申购detail的数据
		mav.addObject("nDevicePurchaseDetail", new NDevicePurchaseDetail());
		//找到AuthorityId为2即老师的所有用户
		mav.addObject("users",shareService.findUsersByAuthorityId(2));
		mav.addObject("cDeviceSuppliers", devicePurchaseDetailService.getCDeviceSuppliers());
		mav.addObject("id",id);
		mav.addObject("isNew",0);
		mav.setViewName("devicePurchase/viewNDevicePurchase.jsp");
		return mav;
	}
	/***********************************************************************************
	 * @功能：设备申购管理--设备申购在为未提交和已提交状态时
	 * @author 徐文
	 * @日期：2016-07-19
	 * **********************************************************************************/
	@RequestMapping("/listNDevicePurchases")
	public ModelAndView listNDevicePurchases(@RequestParam int currpage,int auditStatus,@ModelAttribute NDevicePurchase nDevicePurchase){
		ModelAndView mav = new ModelAndView();
		int pageSize = 30;//设置分页
		User user = shareService.getUser();//获得当前登录人
		//获取当前登录人设备申购的所有信息
		List<NDevicePurchase> listNDevicePurchase=devicePurchaseService.findNDevicePurchases(currpage,auditStatus, pageSize, nDevicePurchase,user);
		//获取总条数
		int totalRecords = devicePurchaseService.findNDevicePurchases(1,auditStatus, -1, nDevicePurchase,user).size();
		//翻页相关
		Map<String, Integer> pageModel = shareService.getPage(currpage, pageSize, totalRecords);
		mav.addObject("listNDevicePurchase", listNDevicePurchase);//传值
		mav.addObject("pageModel", pageModel);
		mav.addObject("totalRecords", totalRecords);
		mav.addObject("nDevicePurchase", nDevicePurchase);
		mav.addObject("currpage", currpage);
		mav.addObject("pageSize", pageSize);
		mav.addObject("auditStatus", auditStatus);
		mav.setViewName("devicePurchase/listNDevicePurchase.jsp");
		return mav;
	}
	/***********************************************************************************
	 * @功能：设备申购管理--设备申购审核
	 * @author 徐文
	 * @日期：2016-07-22
	 * **********************************************************************************/
	@RequestMapping("/listNDevicePurchaseAll")
	public ModelAndView listNDevicePurchaseAll(@RequestParam int currpage,@ModelAttribute NDevicePurchase nDevicePurchase){
		ModelAndView mav = new ModelAndView();
		int pageSize = 30;//设置分页
		devicePurchaseService.changeDeviceStatusByCurrentTime(currpage, pageSize);
		User user = new User();//让现在的user为空
		//获取设备申购的所有信息（因为审核状态没有为9的值，没有进后台关于状态筛选的方法sql）
		List<NDevicePurchase> listNDevicePurchase=devicePurchaseService.findNDevicePurchase(currpage,6, pageSize, nDevicePurchase,user);
		//获取总条数
		int totalRecords = devicePurchaseService.findNDevicePurchase(1,6, -1, nDevicePurchase,user).size();
		//翻页相关
		Map<String, Integer> pageModel = shareService.getPage(currpage, pageSize, totalRecords);
		mav.addObject("listNDevicePurchase", listNDevicePurchase);//传值
		mav.addObject("pageModel", pageModel);
		mav.addObject("nDevicePurchase", nDevicePurchase);
		mav.addObject("totalRecords", totalRecords);
		mav.addObject("currpage", currpage);
		mav.addObject("pageSize", pageSize);
		mav.setViewName("devicePurchase/listNDevicePurchaseAll.jsp");
		return mav;
	}
	/***********************************************************************************
	 * @功能：设备申购管理--审核
	 * @author 徐文
	 * @日期：2016-07-22
	 * **********************************************************************************/
	@RequestMapping("/auditDevicePurchase")
	public ModelAndView auditDevicePurchase(@RequestParam Integer id){
		ModelAndView mav = new ModelAndView();
        //通过主键找到所有的设备申购
		NDevicePurchase nDevicePurchase = devicePurchaseService.findDevicePurchaseByPrimaryKey(id);
		mav.addObject("nDevicePurchase", nDevicePurchase);
		mav.addObject("nDevicePurchaseDetails", nDevicePurchase.getNDevicePurchaseDetails());
		//新增设备申购detail的数据
		mav.addObject("nDevicePurchaseDetail", new NDevicePurchaseDetail());
		//找到AuthorityId为2即老师的所有用户
		mav.addObject("users",shareService.findUsersByAuthorityId(2));
		mav.addObject("id",id);
		mav.addObject("isNew",0);
		mav.setViewName("devicePurchase/auditDevicePurchase.jsp");
		return mav;
	}
	/***********************************************************************************
	 * @功能：设备申购管理--单条审核detail（分中心主任）
	 * @author 徐文
	 * @日期：2016-07-22
	 * **********************************************************************************/
	@RequestMapping("/auditDevicePurchaseDetail")
	public ModelAndView auditDevicePurchaseDetail(@RequestParam Integer id){
		ModelAndView mav = new ModelAndView();
        //通过主键找到所有的设备申购detail
		NDevicePurchaseDetail nDevicePurchaseDetail = devicePurchaseDetailService.findDevicePurchaseDetailByPrimaryKey(id);
		mav.addObject("id",id);
		mav.addObject("nDevicePurchaseDetail",nDevicePurchaseDetail);
		mav.setViewName("devicePurchase/auditDevicePurchaseDetail.jsp");
		return mav;
	}
	/***********************************************************************************
	 * @功能：设备申购管理--保存设备申购detail（分中心主任）
	 * @author 徐文
	 * @日期：2016-07-22
	 * **********************************************************************************/
	@RequestMapping("/saveAuditDevicePurchaseDetail")
	public ModelAndView saveAuditDevicePurchaseDetail(HttpServletRequest request, @RequestParam int id){
		ModelAndView mav = new ModelAndView();
		//通过主键找到所有的设备申购detail
		NDevicePurchaseDetail nDevicePurchaseDetail = devicePurchaseDetailService.findDevicePurchaseDetailByPrimaryKey(id);
		//获取jsp里面的数据保存进表里
		nDevicePurchaseDetail.setIfPassed(Integer.valueOf(request.getParameter("ifPassed")));
		nDevicePurchaseDetail.setAuditAdvice(request.getParameter("auditAdvice"));
		NDevicePurchase nDevicePurchase =nDevicePurchaseDetail.getNDevicePurchase();
		devicePurchaseDetailService.saveNDevicePurchaseDetail(nDevicePurchaseDetail);
		mav.setViewName("redirect:/devicePurchase/auditDevicePurchase?id="+nDevicePurchase.getId());
		return mav;
	}
	/***********************************************************************************
	 * @功能：设备申购管理--单条审核detail（中心主任）
	 * @author 徐文
	 * @日期：2016-07-23
	 * **********************************************************************************/
	@RequestMapping("/auditDevicePurchaseDetail2")
	public ModelAndView auditDevicePurchaseDetail2(@RequestParam Integer id){
		ModelAndView mav = new ModelAndView();
        //通过主键找到所有的设备申购detail
		NDevicePurchaseDetail nDevicePurchaseDetail = devicePurchaseDetailService.findDevicePurchaseDetailByPrimaryKey(id);
		mav.addObject("id",id);
		mav.addObject("nDevicePurchaseDetail",nDevicePurchaseDetail);
		mav.setViewName("devicePurchase/auditDevicePurchaseDetailC.jsp");
		return mav;
	}
	/***********************************************************************************
	 * @功能：设备申购管理--保存设备申购detail（中心主任）
	 * @author 徐文
	 * @日期：2016-07-23
	 * **********************************************************************************/
	@RequestMapping("/saveAuditDevicePurchaseDetailC")
	public ModelAndView saveAuditDevicePurchaseDetailC(HttpServletRequest request, @RequestParam int id){
		ModelAndView mav = new ModelAndView();
		//通过主键找到审核detail信息
		NDevicePurchaseDetail nDevicePurchaseDetail = devicePurchaseDetailService.findDevicePurchaseDetailByPrimaryKey(id);
		//获取jsp里面的数据保存进表里
		nDevicePurchaseDetail.setIfPassedCenter(Integer.valueOf(request.getParameter("ifPassedCenter")));
		nDevicePurchaseDetail.setAuditAdviceCenter(request.getParameter("auditAdviceCenter"));
		//获取detail对应的表单信息
		NDevicePurchase nDevicePurchase =nDevicePurchaseDetail.getNDevicePurchase();
		//保存
		devicePurchaseDetailService.saveNDevicePurchaseDetail(nDevicePurchaseDetail);
		mav.setViewName("redirect:/devicePurchase/auditDevicePurchase?id="+nDevicePurchase.getId());
		return mav;
	}
	/***********************************************************************************
	 * @功能：设备申购管理--设备申购在为未提交和已提交状态时
	 * @author 徐文
	 * @日期：2016-07-23
	 * **********************************************************************************/
	@RequestMapping("/listNDevicePurchasesAll")
	public ModelAndView listNDevicePurchasesAll(@RequestParam int currpage,int auditStatus,@ModelAttribute NDevicePurchase nDevicePurchase){
		ModelAndView mav = new ModelAndView();
		int pageSize = 30;//设置分页
		User user = shareService.getUser();//获得当前登录人
		//获取当前登录人设备申购的所有信息
		devicePurchaseService.changeDeviceStatusByCurrentTime(currpage, pageSize);
		List<NDevicePurchase> listNDevicePurchase=devicePurchaseService.findNDevicePurchase(currpage,auditStatus, pageSize, nDevicePurchase,user);
		//获取所有不同申购编号的申购记录
		List<NDevicePurchase> listPurchaseNumbers = devicePurchaseService.findAllPurchaseNumber();
		//获取总条数
		int totalRecords = devicePurchaseService.findNDevicePurchase(1,auditStatus, -1, nDevicePurchase,user).size();
		//翻页相关
		Map<String, Integer> pageModel = shareService.getPage(currpage, pageSize, totalRecords);
		mav.addObject("listNDevicePurchase", listNDevicePurchase);//传值
		mav.addObject("listPurchaseNumbers", listPurchaseNumbers);
		mav.addObject("pageModel", pageModel);
		mav.addObject("totalRecords", totalRecords);
		mav.addObject("nDevicePurchase", nDevicePurchase);
		mav.addObject("currpage", currpage);
		mav.addObject("pageSize", pageSize);
		mav.addObject("auditStatus",auditStatus);
		mav.setViewName("devicePurchase/listNDevicePurchaseAll.jsp");
		return mav;
	}
	/***********************************************************************************
	 * @功能：设备申购管理--审核
	 * @author 徐文
	 * @日期：2016-07-22
	 * **********************************************************************************/
	@RequestMapping("/auditDevicePurchaseCenter")
	public ModelAndView auditDevicePurchaseCenter(@RequestParam Integer id){
		ModelAndView mav = new ModelAndView();
        //通过主键找到所有的设备申购
		NDevicePurchase nDevicePurchase = devicePurchaseService.findDevicePurchaseByPrimaryKey(id);
		mav.addObject("nDevicePurchase", nDevicePurchase);
		mav.addObject("nDevicePurchaseDetails", nDevicePurchase.getNDevicePurchaseDetails());
		//新增设备申购detail的数据
		mav.addObject("nDevicePurchaseDetail", new NDevicePurchaseDetail());
		//找到AuthorityId为2即老师的所有用户
		mav.addObject("users",shareService.findUsersByAuthorityId(2));
		mav.addObject("id",id);
		mav.addObject("isNew",0);
		mav.setViewName("devicePurchase/auditDevicePurchaseCenter.jsp");
		return mav;
	}
	/***********************************************************************************
	 * @功能：设备申购管理--保存设备申购结果（fen中心主任）
	 * @author 徐文
	 * @日期：2016-07-23
	 * **********************************************************************************/
	@RequestMapping("/saveAuditDevicePurchaseR")
	public ModelAndView saveAuditDevicePurchaseR(HttpServletRequest request, @RequestParam int id){
		ModelAndView mav = new ModelAndView();
		//通过主键找到所有的设备申购
		NDevicePurchase nDevicePurchase = devicePurchaseService.findDevicePurchaseByPrimaryKey(id);
		nDevicePurchase.setAuditStatus(Integer.valueOf(request.getParameter("auditStatus")));
		if (request.getParameter("auditAdvice")!=null) {
			nDevicePurchase.setAuditAdvice(request.getParameter("auditAdvice"));
		}
		if (request.getParameter("auditAdviceCenter")!=null) {
			nDevicePurchase.setAuditAdvice(request.getParameter("auditAdviceCenter"));
		}
		//审核通过后设置该设备申购信息的当前设备状态的结束时间，并在设备状态表中插入第一条设备状态记录  
		if(nDevicePurchase.getAuditStatus() == 3){
			//获取当前的系统时间 
			Calendar newEndDate = Calendar.getInstance(); 
			//将当前系统时间加上当前状态的延迟天数得到新的结束时间
			newEndDate.add(Calendar.DATE, nDevicePurchase.getCDeviceStatus().getIntervalDate());
			//设置设备申购的当前设备状态的结束日期
			nDevicePurchase.setEndDate(newEndDate);
			devicePurchaseService.saveDevicePurchase(nDevicePurchase);//保存修改的数据
			//新建设备状态记录
			DeviceStatusRecord deviceStatusRecord = new DeviceStatusRecord(); 
			//设置该设备状态记录的开始时间为当前的系统时间
			deviceStatusRecord.setEndDate(Calendar.getInstance());
			//设置该设备状态记录对应的设备申购信息
			deviceStatusRecord.setNDevicePurchase(nDevicePurchase);
			//设置该设备状态记录的设备状态名称为涉笔申购信息中的设备状态名称
			deviceStatusRecord.setStatusName(nDevicePurchase.getCDeviceStatus().getStatusName());
			//保存修改的数据
			devicePurchaseService.saveDeviceStatusRecord(deviceStatusRecord);
		}
		//将审核结果逐条保存到record表里
		NDeviceAuditRecord nDeviceAuditRecord = new NDeviceAuditRecord();
        User user = shareService.getUser();
        //获取的登录人
        nDeviceAuditRecord.setUser(user);
        //审核结果是4（拒绝）的时候保存为0，是3，2通过保存为1
        if (nDevicePurchase.getAuditStatus()==4) {
            nDeviceAuditRecord.setAuditResult(0);
        }
        if (nDevicePurchase.getAuditStatus()==2) {
            nDeviceAuditRecord.setAuditResult(1);
        }
        
        if (nDevicePurchase.getAuditStatus()==3) {
            nDeviceAuditRecord.setAuditResult(1);
        }
        nDeviceAuditRecord.setRemark(nDevicePurchase.getAuditAdvice());
        nDeviceAuditRecord.setAuditDate(Calendar.getInstance());
        //关联表保存
        nDeviceAuditRecord.setNDevicePurchase(nDevicePurchase);
        devicePurchaseService.saveNDeviceAuditRecord(nDeviceAuditRecord);
		mav.setViewName("redirect:/devicePurchase/listNDevicePurchaseAll?currpage=1");
		return mav;
	}
	/***********************************************************************************
	 * @功能：查看该设备申购信息的所有设备状态记录
	 * @author 郑昕茹
	 * @日期：2016-07-21
	 * **********************************************************************************/
	@RequestMapping("/updateDeviceStatus")
	public ModelAndView updateDeviceStatus(@RequestParam Integer id){
		ModelAndView mav = new ModelAndView();
        //通过主键找到设备申购状态记录
		NDevicePurchase nDevicePurchase = devicePurchaseService.findDevicePurchaseByPrimaryKey(id);
		//根据deviceId找到该设备申购就的所有设备状态记录
		List<DeviceStatusRecord> listDeviceStatusRecords = devicePurchaseService.findDeviceStatusRecordByDeviceId(id);
		//获取设备状态记录的总条数
		int totalRecords = listDeviceStatusRecords.size(); 
		mav.addObject("listDeviceStatusRecords", listDeviceStatusRecords);
		mav.addObject("nDevicePurchase", nDevicePurchase);
		mav.addObject("id",id); 
		
		mav.setViewName("devicePurchase/updateDeviceStatus.jsp");
		return mav;
	}
	/***********************************************************************************
	 * @功能：延迟当前设备状态的结束日期
	 * @author 郑昕茹
	 * @日期：2016-07-25
	 * **********************************************************************************/
	@RequestMapping("/delayDeviceStatus")
	public ModelAndView delayDeviceStatus(@RequestParam Integer id){
		ModelAndView mav = new ModelAndView();
        //通过主键找到设备申购状态
		NDevicePurchase nDevicePurchase = devicePurchaseService.findDevicePurchaseByPrimaryKey(id);
		
		mav.addObject("nDevicePurchase",nDevicePurchase);
		mav.addObject("id",id);
		
		mav.setViewName("devicePurchase/delayDeviceStatus.jsp");
		return mav;
	}
	/***********************************************************************************
	 * @功能：保存延迟后的结束日期
	 * @author 郑昕茹
	 * @日期：2016-07-25
	 * **********************************************************************************/
	@RequestMapping("/saveDelayDate")
	public ModelAndView saveDelayDate(@RequestParam Integer id,@ModelAttribute NDevicePurchase nDevicePurchase){
		ModelAndView mav = new ModelAndView();
        //通过主键找到设备申购状态
		NDevicePurchase nOldDevicePurchase = devicePurchaseService.findDevicePurchaseByPrimaryKey(id);
		//获取延迟天数
		int delayDate = nDevicePurchase.getDelayDate();
		//获取延迟前的结束日期
		Calendar EndDate = nOldDevicePurchase.getEndDate();
		//得到延迟后的结束日期
		EndDate.add(Calendar.DATE, delayDate);
		//对原始的设备申购状态设置新的结束日期
		nOldDevicePurchase.setEndDate(EndDate);
		//保存修改后的信息
		devicePurchaseService.saveDevicePurchaseEndDate(nOldDevicePurchase);
		mav.setViewName("redirect:/devicePurchase/listNDevicePurchase?currpage=1");
		return mav;
	} 
	/***********************************************************************************
	 * @功能：修改当前设备状态
	 * @author 郑昕茹
	 * @日期：2016-07-25
	 * **********************************************************************************/
	@RequestMapping("/modifyDeviceStatus")
	public ModelAndView modifyDeviceStatus(@RequestParam Integer id){
		ModelAndView mav = new ModelAndView();
        //通过主键找到设备申购状态
		NDevicePurchase nDevicePurchase = devicePurchaseService.findDevicePurchaseByPrimaryKey(id);
		List<CDeviceStatus> listCDeviceStatus = cDeviceStatusService.findCDeviceStatus(1, -1);
		mav.addObject("nDevicePurchase",nDevicePurchase);
		mav.addObject("listCDeviceStatus",listCDeviceStatus);
		mav.addObject("id",id);
		
		mav.setViewName("devicePurchase/modifyDeviceStatus.jsp");
		return mav;
	}
	/***********************************************************************************
	 * @功能：保存延迟后的结束日期
	 * @author 郑昕茹
	 * @日期：2016-07-25
	 * **********************************************************************************/ 
	@RequestMapping("/saveCurrentDeviceStatus")
	public ModelAndView saveCurrentDeviceStatus(@RequestParam Integer id,@ModelAttribute NDevicePurchase nDevicePurchase){
		ModelAndView mav = new ModelAndView();
        //通过主键找到设备申购状态
		NDevicePurchase nOldDevicePurchase = devicePurchaseService.findDevicePurchaseByPrimaryKey(id);
		//获取设备状态
		CDeviceStatus cDeviceStatus = nDevicePurchase.getCDeviceStatus(); 
		//对原始的设备申购状态设置新的设备状态
		nOldDevicePurchase.setCDeviceStatus(cDeviceStatus);
		//保存修改后的信息
		devicePurchaseService.saveDevicePurchaseEndDate(nOldDevicePurchase);
		cDeviceStatus=cDeviceStatusService.findCDeviceStatusByPrimaryKey(cDeviceStatus.getId());
		//若设置的新的状态是正常状态，则改变其结束时间
		if(cDeviceStatus.getFlag()==1){
			Calendar newEndDate = Calendar.getInstance();
			newEndDate.add(Calendar.DATE, cDeviceStatus.getIntervalDate());
			nOldDevicePurchase.setEndDate(newEndDate);
			devicePurchaseService.saveDevicePurchaseEndDate(nOldDevicePurchase);
		}
		//将设置的新状态存入设备状态记录表中
		DeviceStatusRecord deviceStatusRecord = new DeviceStatusRecord();
		deviceStatusRecord.setEndDate(Calendar.getInstance());
		deviceStatusRecord.setNDevicePurchase(nOldDevicePurchase);
		deviceStatusRecord.setStatusName(cDeviceStatus.getStatusName());
		devicePurchaseService.saveDeviceStatusRecord(deviceStatusRecord); 
		mav.setViewName("redirect:/devicePurchase/listNDevicePurchase?currpage=1");
		return mav;
	} 
}