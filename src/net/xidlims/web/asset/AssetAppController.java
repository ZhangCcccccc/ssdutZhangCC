package net.xidlims.web.asset;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import net.xidlims.constant.CommonConstantInterface;
import net.xidlims.dao.MessageDAO;
import net.xidlims.domain.Asset;
import net.xidlims.domain.AssetApp;
import net.xidlims.domain.AssetAppAudit;
import net.xidlims.domain.AssetAppDate;
import net.xidlims.domain.AssetAppRecord;
import net.xidlims.domain.AssetCabinet;
import net.xidlims.domain.Message;
import net.xidlims.domain.OperationItem;
import net.xidlims.domain.OperationOutline;
import net.xidlims.domain.User;
import net.xidlims.service.asset.AssetAppService;
import net.xidlims.service.asset.AssetService;
import net.xidlims.service.common.ShareService;
import net.xidlims.service.devicePurchase.DevicePurchaseService;
import net.xidlims.service.operation.OperationService;

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

@Controller("AssetAppController") 
@SessionAttributes("selected_labCenter")
@RequestMapping("/asset")
public class AssetAppController<JsonResult> {

	@Autowired AssetService assetService;
	@Autowired AssetAppService assetAppService;
	@Autowired ShareService shareService;
	@Autowired DevicePurchaseService devicePurchaseService;
	@Autowired MessageDAO messageDAO;
	@Autowired OperationService operationService;
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
	
	/***********************************************************************************
	 * @description 药品溶液管理-药品申购｛显示所有的药品审核信息｝
	 * @author 徐文
	 * @date 2016-08-08
	 * **********************************************************************************/
	@RequestMapping("/listAssetApps")
	public ModelAndView listAssetApps(@RequestParam Integer currpage,Integer assetAuditStatus,@ModelAttribute AssetApp assetApp){
		ModelAndView mav = new ModelAndView();
		int pageSize = 30;//设置分页
		User user = shareService.getUser();//获得当前登录人
		//获取当前登录人申购的所有信息
		List<AssetApp> listAssetApps=assetAppService.findAssetApps(currpage,pageSize,assetAuditStatus,assetApp,user,null,null);
		/**
		 * 1.遍历每个申购申购记录，得出记录数，即物资种类
		 * 2.在记录不为空时，将单价和数量相乘
		 * 3.累加得到总价值
		 * */
		//存储每条申购对应的申购记录的种类的种类
		int num[] = new int[listAssetApps.size()];
		//存储每条申购对应的申购记录的总价
		float totalPrice[] = new float[listAssetApps.size()];
		//计数现在在第几条申购
		int count = 0;
		for(AssetApp a:listAssetApps){
			num[count] = 0;
			totalPrice[count] = 0;
			//获取当前遍历到的这条申购相应的申购记录
			Set<AssetAppRecord> listAssetAppRecords=a.getAssetAppRecords();
			for(Iterator<AssetAppRecord> it = listAssetAppRecords.iterator(); it.hasNext();){
				AssetAppRecord assetAppRecord = it.next();
				if (assetAppRecord!=null) {
					num[count]++;
				}
				if (assetAppRecord!=null&&assetAppRecord.getAppPrice()!=null&&assetAppRecord.getAppQuantity()!=null) {
					BigDecimal quantity =new BigDecimal(assetAppRecord.getAppQuantity());
					float unitPrice=quantity.multiply(assetAppRecord.getAppPrice()).floatValue();
					totalPrice[count]+=unitPrice;
				}
			}
			count++;
		}
		Calendar startDate = null;
		Calendar endDate = null;
		//申购时间
		if(assetAppService.findAssetAppDate().size() != 0){
			AssetAppDate assetAppDate = assetAppService.findAssetAppDate().get(0);
			startDate = assetAppDate.getStartDate();
			endDate = assetAppDate.getEndDate(); 
		}
		//获取总条数
		int totalRecords=assetAppService.findAssetApps(1,-1,assetAuditStatus,assetApp,user,null,null).size();
		//翻页相关
		Map<String, Integer> pageModel = shareService.getPage(currpage, pageSize, totalRecords);
		mav.addObject("listAssetApps", listAssetApps);//传值
		mav.addObject("pageModel", pageModel);
		mav.addObject("assetApp", assetApp);
		mav.addObject("totalRecords", totalRecords);
		mav.addObject("totalPrice", totalPrice);
		mav.addObject("num", num);
		mav.addObject("assetAuditStatus", assetAuditStatus);
		mav.addObject("currpage", currpage);
		mav.addObject("pageSize", pageSize);
		mav.addObject("startDate", startDate);
		mav.addObject("endDate", endDate);
		mav.setViewName("asset/listAssetApps.jsp");
		return mav;
	}
	/***********************************************************************************
	 * @description 药品溶液管理-药品申购｛新建｝
	 * @author 徐文
	 * @date 2016-08-08
	 * **********************************************************************************/
	@RequestMapping("/newAssetApp")
	public ModelAndView newAssetApp(@RequestParam Integer flag,@ModelAttribute("selected_labCenter") Integer cid){
		ModelAndView mav = new ModelAndView();
		AssetApp assetApp = new AssetApp();
		//获取随机数6位
		String randomCode = assetAppService.getNumber(assetAppService.findAppNo());
		//获取当前时间
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		String dateStr = sdf.format(calendar.getTime());
		//生成申购编号
		String appNo = dateStr + randomCode ;
		assetApp.setAppNo(appNo);
		//获取该实验中心下的所有实验室运行大纲
		List<OperationItem> operationItems = assetAppService.findOperationOutlineByLabCenter(cid);
		Set<OperationOutline> operationOutlines = new HashSet<OperationOutline>();
		if(operationItems != null){
			for(OperationItem operationItem:operationItems){
				if(operationItem.getOperationOutline() != null)
				{
					operationOutlines.add(operationItem.getOperationOutline());
				}
			}
		}
		mav.addObject("operationOutlines", operationOutlines);
		mav.addObject("assetApp", assetApp);//新增数据
		mav.addObject("isNew", 1);//标志位表示新增
		mav.addObject("flag", flag);
		mav.addObject("outLine", 0);
		mav.setViewName("asset/newAssetApp.jsp");
		return mav;
	}
	/***********************************************************************************
	 * @description 药品溶液管理-药品申购｛保存｝
	 * @author 徐文
	 * @date 2016-08-08
	 * **********************************************************************************/
	@RequestMapping("/saveAssetApp")
	public String saveAssetApp(@ModelAttribute AssetApp assetApp){
		ModelAndView mav = new ModelAndView();
		assetApp.setAssetAuditStatus(4);
		if(assetApp.getOperationItem().getId()==null){
			assetApp.setOperationItem(null);
		}
		//若为新建对应的设备申购保存，则将当前设备状态设置为设备状态字典表中顺序最小的设备状态
		//获取随机数6位
		String randomCode = assetAppService.getNumber(assetAppService.findAppNo());
		//获取当前时间
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		String dateStr = sdf.format(calendar.getTime());
		//生成申购编号
		String appNo = dateStr + randomCode ;
		assetApp.setAppNo(appNo);
		assetApp.setUser(shareService.getUser());//将登录人信息保存进去
		//判断当前日期是否超出申购时间
		Calendar currTime = Calendar.getInstance();
		if(assetAppService.findAssetAppDate().size() != 0){
			AssetAppDate assetAppDate = assetAppService.findAssetAppDate().get(0);
			Calendar startDate = assetAppDate.getStartDate();
			Calendar endDate = assetAppDate.getEndDate(); 
			if(currTime.after(startDate) && currTime.before(endDate)){ 
				assetApp.setAppDate(Calendar.getInstance()); 
				assetApp=assetAppService.saveAssetApp(assetApp);//保存修改或者新建的数据
				return "redirect:/asset/viewAssetApp?id="+assetApp.getId();
			}
		}
		if(assetAppService.findAssetAppDate().size() == 0){
			assetApp.setAppDate(Calendar.getInstance()); 
			assetApp=assetAppService.saveAssetApp(assetApp);//保存修改或者新建的数据
			return "redirect:/asset/viewAssetApp?id="+assetApp.getId();
		}
		return "redirect:/asset/newAssetApp?flag=1";
	}
	/***********************************************************************************
	 * @description 药品溶液管理-药品申购｛查看｝
	 * @author 徐文
	 * @date 2016-08-09
	 * **********************************************************************************/
	@RequestMapping("/viewAssetApp")
	public ModelAndView viewAssetApp(@RequestParam Integer id){
		ModelAndView mav = new ModelAndView();
        //通过主键找到所有的设备申购
		AssetApp assetApp = assetAppService.findAssetAppByPrimaryKey(id);
		mav.addObject("assetApp", assetApp);
		mav.addObject("assetAppRecords", assetApp.getAssetAppRecords());
		mav.addObject("assetAppRecord", new AssetAppRecord());
		mav.addObject("assets", assetService.findAllAssetNames());
		mav.addObject("id", id);
		mav.setViewName("asset/viewAssetApp.jsp");
		return mav;
	}
	/***********************************************************************************
	 * @description 药品溶液管理-药品申购｛保存｝
	 * @author 徐文
	 * @date 2016-08-09
	 * **********************************************************************************/
	@RequestMapping("/saveAssetAppRecord")
	public ModelAndView saveAssetAppRecord(HttpServletRequest request,@ModelAttribute AssetAppRecord assetAppRecord,@RequestParam Integer id){
		ModelAndView mav = new ModelAndView();
		//将申购记录中申购相关字段设置进去
		assetAppRecord.setId(null);
		assetAppRecord.setAssetApp(assetAppService.findAssetAppByPrimaryKey(id));
		if(assetAppRecord.getAppSpec().equals("是")){
			String[] numbers = request.getParameterValues("selectNumber");
			String number = "";
			String spec ="";
			if(!numbers[0].equals("")){
				spec += "20ul ,";
				number +=numbers[0]+" "+",";
			}else{
				spec += " ,";
				number +=" "+",";
			}
			if(!numbers[1].equals("")){
				spec += "200ul ,";
				number +=numbers[1]+" "+",";
			}else{
				spec += " ,";
				number +=" "+",";
			}
			if(!numbers[2].equals("")){
				spec += "1000ul ,";
				number +=numbers[2]+" "+",";
			}else{
				spec += " ";
				number +=" ";
			}
			assetAppRecord.setAppfinalSpec(spec);
			assetAppRecord.setSelectNumber(number);
		}
		assetAppService.saveAssetAppRecord(assetAppRecord);
		mav.setViewName("redirect:/asset/viewAssetApp?id="+id);
		return mav;
	}
	/***********************************************************************************
	 * @description 药品溶液管理-药品申购｛编辑｝ 
	 * @author 徐文
	 * @date 2016-08-08
	 * **********************************************************************************/
	@RequestMapping("/editAssetApp")
	public ModelAndView editAssetApp(@RequestParam Integer id){
		ModelAndView mav = new ModelAndView(); 
		//根据主键找到物资申购记录
		AssetApp assetApp = assetAppService.findAssetAppByPrimaryKey(id);
		mav.addObject("isNew", 0);//标志位表示编辑
		mav.addObject("assetApp", assetApp);
		//获取该实验中心下的所有实验室运行大纲
		List<OperationItem> operationItems = assetAppService.findOperationOutlineByLabCenter(-1);
		Set<OperationOutline> operationOutlines = new HashSet<OperationOutline>();
		if(operationItems != null){
			for(OperationItem operationItem:operationItems){
				operationOutlines.add(operationItem.getOperationOutline());
			}
		}
		mav.addObject("operationOutlines", operationOutlines);
		mav.addObject("outLine", assetApp.getType());
		if(assetApp.getOperationItem() != null){
			//找到该实验项目所属实验大纲下的全部实验项目
			mav.addObject("items",assetApp.getOperationItem().getOperationOutline().getOperationItems());
		}
		mav.setViewName("asset/newAssetApp.jsp");
		return mav;
	}
	/***********************************************************************************
	 * @description 药品溶液管理-药品申购｛删除｝
	 * @author 徐文
	 * @date 2016-08-08
	 * **********************************************************************************/
	@RequestMapping("/deleteAssetApp")
	public ModelAndView deleteAssetApp(@RequestParam Integer id){
		ModelAndView mav = new ModelAndView(); 
		//根据主键找到物资申购记录
		AssetApp assetApp = assetAppService.findAssetAppByPrimaryKey(id);
		assetAppService.deleteAssetApp(assetApp); 
		mav.setViewName("redirect:/asset/listAssetApps?currpage=1&assetAuditStatus=9");
		return mav;
	}
	/***********************************************************************************
	 * @description 药品溶液管理-药品申购｛提交｝
	 * @author 徐文
	 * @date 2016-08-09
	 * **********************************************************************************/
	@RequestMapping("/submitAssetApps")
	public String submitAssetApps(@RequestParam Integer id){
        //通过主键找到所有的设备申购
		AssetApp assetApp = assetAppService.findAssetAppByPrimaryKey(id);
		//点击提交后设备状态由未提交（4）变为未审核（3）
		assetApp.setAssetAuditStatus(3);
		assetApp.setMem(null);
		assetAppService.saveAssetApp(assetApp);
		List<User> superAdmins=shareService.findUsersByAuthorityId(11);//超级管理员
		for(User superAdmin:superAdmins){
			Message message= new Message();
			message.setSendUser(shareService.getUserDetail().getCname());//当前登录人
			message.setSendCparty(shareService.getUserDetail().getSchoolAcademy().getAcademyName());//当前登录人所在学院
			message.setTitle("药品溶液申购");
			String content="申请成功，等待审核";
			content+="<a  href='/xidlims/asset/auditAssetApp?id=";
			content+=id;
			content+="'>点击审核</a>";
			message.setContent(content);
			message.setMessageState(CommonConstantInterface.INT_Flag_ZERO);
			message.setCreateTime(Calendar.getInstance());
			message.setUsername(superAdmin.getUsername());
			message=messageDAO.store(message);
		}
		
		return "redirect:/asset/listAssetApps?currpage=1&assetAuditStatus=9";
	}
	
	/***********************************************************************************
	 * @description 药品溶液管理-药品申购审核｛list｝
	 * @author 徐文
	 * @date 2016-08-10
	 * **********************************************************************************/
	@RequestMapping("/auditAssetApps")
	public ModelAndView auditAssetApps(@RequestParam int currpage,Integer assetAuditStatus, @ModelAttribute AssetApp assetApp,HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		int pageSize = 30;//设置分页
		User user = shareService.getUser();//获得当前登录人 
		//获取设备申购的所有信息（因为审核状态没有为9的值，没有进后台关于状态筛选的方法sql）
		//获得查询的开始时间
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");//获得查询的结束时间
		List<AssetApp> listAssetApps=assetAppService.findAssetApps(currpage,pageSize,assetAuditStatus,assetApp,null,startDate,endDate);
		List<AssetApp> users =  assetAppService.findAssetAppsGroupByUsers(currpage,pageSize,assetAuditStatus,assetApp,null,startDate,endDate);
		/**
		 * 1.遍历每个申购申购记录，得出记录数，即物资种类
		 * 2.在记录不为空时，将单价和数量相乘
		 * 3.累加得到总价值
		 * */
		//存储每条申购对应的申购记录的种类的种类
		int num[] = new int[listAssetApps.size()];
		//存储每条申购对应的申购记录的总价
		float totalPrice[] = new float[listAssetApps.size()];
		//计数现在在第几条申购
		int count = 0;
		for(AssetApp a:listAssetApps){
			num[count] = 0;
			totalPrice[count] = 0;
			//获取当前遍历到的这条申购相应的申购记录
			Set<AssetAppRecord> listAssetAppRecords=a.getAssetAppRecords();
			for(Iterator<AssetAppRecord> it = listAssetAppRecords.iterator(); it.hasNext();){
				AssetAppRecord assetAppRecord = it.next();
				if (assetAppRecord!=null) {
					num[count]++;
				}
				if (assetAppRecord!=null&&assetAppRecord.getAppPrice()!=null&&assetAppRecord.getAppQuantity()!=null) {
					BigDecimal quantity =new BigDecimal(assetAppRecord.getAppQuantity());
					float unitPrice=quantity.multiply(assetAppRecord.getAppPrice()).floatValue();
					totalPrice[count]+=unitPrice;
				}
			}
			count++;
		}
		//获取总条数
		int totalRecords = assetAppService.findAssetApps(currpage,pageSize,assetAuditStatus,assetApp,null,startDate,endDate).size();
		//翻页相关
		Map<String, Integer> pageModel = shareService.getPage(currpage, pageSize, totalRecords);
		mav.addObject("listAssetApps", listAssetApps);//传值
		mav.addObject("pageModel", pageModel);
		mav.addObject("num", num);
		mav.addObject("totalPrice", totalPrice);
		mav.addObject("assetApp", assetApp);
		mav.addObject("totalPrice", totalPrice);
		mav.addObject("assetAuditStatus", assetAuditStatus);
		mav.addObject("totalRecords", totalRecords);
		mav.addObject("currpage", currpage);
		mav.addObject("pageSize", pageSize);
		mav.addObject("users", users);
		mav.addObject("startDate",startDate);
		mav.addObject("endDate", endDate);
		mav.setViewName("asset/auditAssetApps.jsp");
		return mav;
	}
	/***********************************************************************************
	 * @description 药品溶液管理-药品申购审核｛audit｝
	 * @author 徐文
	 * @date 2016-08-10
	 * **********************************************************************************/
	@RequestMapping("/auditAssetApp")
	public ModelAndView auditAssetApp(@RequestParam Integer id){
		ModelAndView mav = new ModelAndView();
        //通过主键找到所有的申购
		AssetApp assetApp = assetAppService.findAssetAppByPrimaryKey(id);
		//获取相应的申购记录
		Set<AssetAppRecord> listAssetAppRecords=assetApp.getAssetAppRecords();
		//计数申购记录的种类，即总条数
		int Num = 0;
		float totalPrice = 0;//存储申购记录的总价
		/**
		 * 1.遍历所有的申购记录，得出记录数，即物资种类
		* 2.在记录不为空时，将单价和数量相乘
		* 3.累加得到总价值
		* */
		for(Iterator<AssetAppRecord> it = listAssetAppRecords.iterator(); it.hasNext();){
			AssetAppRecord assetAppRecord = it.next();
			if (assetAppRecord!=null) {
				Num++;
			}
			if (assetAppRecord!=null&&assetAppRecord.getAppPrice()!=null&&assetAppRecord.getAppQuantity()!=null) {
				BigDecimal quantity =new BigDecimal(assetAppRecord.getAppQuantity());
				float unitPrice=quantity.multiply(assetAppRecord.getAppPrice()).floatValue();
				totalPrice+=unitPrice;
			}
		}
		mav.addObject("assetApp", assetApp);
		mav.addObject("listAssetAppRecords",listAssetAppRecords);
		mav.addObject("Num",Num);
		mav.addObject("totalPrice",totalPrice);
 		mav.addObject("id",id);
		mav.setViewName("asset/auditAssetApp.jsp");
		return mav;
	}
	/***********************************************************************************
	 * @description 药品溶液管理-药品申购审核｛保存｝
	 * @author 徐文
	 * @date 2016-08-10
	 * **********************************************************************************/
	@RequestMapping("/saveAuditAssetApp")
	public ModelAndView saveAuditAssetApp(HttpServletRequest request, @RequestParam int id){
		ModelAndView mav = new ModelAndView();
		//通过主键找到所有的申购
		AssetApp assetApp = assetAppService.findAssetAppByPrimaryKey(id);
		if (request.getParameter("mem")!=null) {
			assetApp.setMem(request.getParameter("mem"));
		}
		if (request.getParameter("assetAuditStatus")!=null) {
			assetApp.setAssetAuditStatus(Integer.parseInt((request.getParameter("assetAuditStatus"))));
		}
		assetApp.setStockStatus(0);
		assetAppService.saveAssetApp(assetApp);
			//jilu表审核结果保存
			// 获取选中的明细表的id
	        String flag1[] = request.getParameterValues("flag1");
	        if(flag1 != null && flag1.length != 0){
	        	   // 首先对数组排序
		        Arrays.sort(flag1);
	        }
	        // 遍历设置审核结果
	        /**
	         * 1.先通过assetApp找到同id下所有的record的信息
	         * 2.对拿到的所有的record进行遍历
	         * 3.用二分法将flag1和遍历的每一个record进行比较
	         * 4.如果flag1得到的信息中有包含record，那么result就大于0
	         * 5.整体审核意见为通过时，将大于0的部分审核意见设置为1，没勾选的设置为0，并且下一级审核中不能再审核该record信息
	         * 6.整体意见不通过时，即便勾选了，也全部设置为0。
	         * */
	        Iterator<AssetAppRecord> assetAppRecords= assetApp.getAssetAppRecords().iterator();
	        while (assetAppRecords.hasNext()) {
	        	AssetAppRecord assetAppRecord = assetAppRecords.next();
	            int result = Arrays.binarySearch(flag1, assetAppRecord.getId()
	                    .toString());
	            if (assetApp.getAssetAuditStatus()==1) {
	            	//药品管理员审核完成后，给项目建设中的设备申购申请人审核成功的消息
	    			Message message= new Message();
	    			message.setSendUser(shareService.getUserDetail().getCname());//当前登录人
	    			message.setSendCparty(shareService.getUserDetail().getSchoolAcademy().getAcademyName());//当前登录人所在学院
	    			message.setTitle("药品溶液申购，药品管理员审核同意，审核通过");
	    			String content="药品管理员审核通过，请查看审核结果";
	    			content+="<a  href='/xidlims/asset/getAssetApp?id=";
	    			content+=id;
	    			content+="'>点击查看</a>";
	    			message.setContent(content);
	    			message.setMessageState(CommonConstantInterface.INT_Flag_ZERO);
	    			message.setCreateTime(Calendar.getInstance());
	    			message.setUsername(assetApp.getUser().getUsername());//设备申购申请人
	    			message=messageDAO.store(message);
	            	if (result >= 0) {
	            		// 選中
	            		assetAppRecord.setResult(1);
	            		assetAppRecord.setStockStatus(0);
	            	} else {
	            		// 反選
	            		assetAppRecord.setResult(0);
	            	}
	            }
	            	if (assetApp.getAssetAuditStatus()==2) {
	            		//药品管理员审核完成后，给项目建设中的设备申购申请人审核成功的消息
		    			Message message= new Message();
		    			message.setSendUser(shareService.getUserDetail().getCname());//当前登录人
		    			message.setSendCparty(shareService.getUserDetail().getSchoolAcademy().getAcademyName());//当前登录人所在学院
		    			message.setTitle("药品溶液申购，药品管理员审核不同意，审核不通过");
		    			String content="药品管理员审核未通过，请查看审核结果";
		    			content+="<a  href='/xidlims/asset/getAssetApp?id=";
		    			content+=id;
		    			content+="'>点击查看</a>";
		    			message.setContent(content);
		    			message.setMessageState(CommonConstantInterface.INT_Flag_ZERO);
		    			message.setCreateTime(Calendar.getInstance());
		    			message.setUsername(assetApp.getUser().getUsername());//设备申购申请人
		    			message=messageDAO.store(message);
		            	if (result >= 0) {
		            		// 選中
		            		assetAppRecord.setResult(0);
		            	} else {
		            		// 反選
		            		assetAppRecord.setResult(1);
		            		assetAppRecord.setStockStatus(0);
		            	}
	                 }
	            	assetAppService.saveAssetAppRecord(assetAppRecord);
	        }
		//将审核结果逐条保存到record表里
	            AssetAppAudit assetAppAudit = new AssetAppAudit();
        User user = shareService.getUser();
        //获取的登录人
        assetAppAudit.setUser(user);
        //审核结果是4（拒绝）的时候保存为0，是3，2通过保存为1
        if (assetApp.getAssetAuditStatus()==1) {
        	assetAppAudit.setStatus(1);
        }
        if (assetApp.getAssetAuditStatus()==2) {
        	assetAppAudit.setStatus(0);
        }
        assetAppAudit.setCreateDate(Calendar.getInstance());
        assetAppAudit.setResult(assetApp.getMem());
        //关联表保存
        assetAppAudit.setAssetApp(assetApp);
        assetAppAudit=assetAppService.saveAssetAppAudit(assetAppAudit);
		mav.setViewName("redirect:/asset/auditAssetApps?currpage=1&assetAuditStatus=4");
		return mav;
	}
	/***********************************************************************************
	 * @description 药品溶液管理-药品申购｛查看｝
	 * @author 徐文
	 * @date 2016-08-09
	 * **********************************************************************************/
	@RequestMapping("/getAssetApp")
	public ModelAndView getAssetApp(@RequestParam Integer id){
		ModelAndView mav = new ModelAndView();
        //通过主键找到所有的设备申购
		AssetApp assetApp = assetAppService.findAssetAppByPrimaryKey(id);
		Set<AssetAppAudit> assetAppAudits = assetApp.getAssetAppAudits();
		//找到申购对应的第一个审核记录
		AssetAppAudit assetAppAudit = null;
		for(Iterator<AssetAppAudit> it = assetAppAudits.iterator(); it.hasNext();){
			assetAppAudit = it.next();
			break;
		}
		//找到该申购对应的所有申购记录
		Set<AssetAppRecord> assetAppRecords = assetApp.getAssetAppRecords();
		//计数申购记录的种类，即总条数
		int num = 0;
		float totalPrice = 0;//存储申购记录的总价
		/**
		 * 1.遍历所有的申购记录，得出记录数，即物资种类
		 * 2.在记录不为空时，将单价和数量相乘
		 * 3.累加得到总价值
		 * */
		for(Iterator<AssetAppRecord> it = assetAppRecords.iterator(); it.hasNext();){
			AssetAppRecord assetAppRecord = it.next();
			if (assetAppRecord!=null) {
				num++;
			}
			if (assetAppRecord!=null&&assetAppRecord.getAppPrice()!=null&&assetAppRecord.getAppQuantity()!=null) {
				BigDecimal quantity =new BigDecimal(assetAppRecord.getAppQuantity());
				float unitPrice=quantity.multiply(assetAppRecord.getAppPrice()).floatValue();
				totalPrice+=unitPrice;
			}
		}
		mav.addObject("assetAppRecords", assetAppRecords);
		mav.addObject("assetApp", assetApp);
		mav.addObject("id", id);
		mav.addObject("num", num);
		mav.addObject("totalPrice", totalPrice);
		mav.addObject("assetAppAudit", assetAppAudit);
		mav.setViewName("asset/getAssetApp.jsp");
		return mav;
	}
	
	/***********************************************************************************
	 * @description 药品溶液管理-药品申购｛设置提交时间｝
	 * @author 郑昕茹
	 * @date 2016-08-17
	 * **********************************************************************************/
	@RequestMapping("/setSubmitTime")
	public ModelAndView setSubmitTime( ){  
		ModelAndView mav = new ModelAndView();
		AssetAppDate assetAppDate=null;
		String startDate=null;
		String endDate=null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(assetAppService.findAssetAppDate().size()==0){
			assetAppDate = new AssetAppDate();
		}
		else
		{
			assetAppDate = assetAppService.findAssetAppDate().get(0);
			startDate = sdf.format(assetAppDate.getStartDate().getTime());
			endDate =	sdf.format(assetAppDate.getEndDate().getTime());
		}
		mav.addObject("assetAppDate", assetAppDate);
		mav.addObject("startDate", startDate);
		mav.addObject("endDate", endDate);
		mav.setViewName("asset/setSubmitTime.jsp");
		return mav;
	}
	
	/***********************************************************************************
	 * @description 药品溶液管理-药品申购｛保存提交时间｝
	 * @author 郑昕茹
	 * @date 2016-08-17
	 * **********************************************************************************/
	@RequestMapping("/saveSubmitTime")
	public ModelAndView saveSubmitTime( @ModelAttribute AssetAppDate assetAppDate){  
		ModelAndView mav = new ModelAndView(); 
		assetAppService.saveAssetAppDate(assetAppDate);
		mav.setViewName("redirect:/asset/auditAssetApps?currpage=1&assetAuditStatus=4");
		return mav;
	}
	
	/***********************************************************************************
	 * @description 药品溶液管理-药品审核｛查看｝
	 * @author 郑昕茹
	 * @date 2016-08-26
	 * **********************************************************************************/
	@RequestMapping("/getAssetAppInAudit")
	public ModelAndView getAssetAppInAudit(@RequestParam Integer id){
		ModelAndView mav = new ModelAndView();
        //通过主键找到所有的设备申购
		AssetApp assetApp = assetAppService.findAssetAppByPrimaryKey(id);
		Set<AssetAppAudit> assetAppAudits = assetApp.getAssetAppAudits();
		//找到申购对应的第一个审核记录
		AssetAppAudit assetAppAudit = null;
		for(Iterator<AssetAppAudit> it = assetAppAudits.iterator(); it.hasNext();){
			assetAppAudit = it.next();
			break;
		}
		//找到该申购对应的所有申购记录
		Set<AssetAppRecord> assetAppRecords = assetApp.getAssetAppRecords();
		//计数申购记录的种类，即总条数
		int num = 0;
		float totalPrice = 0;//存储申购记录的总价
		/**
		 * 1.遍历所有的申购记录，得出记录数，即物资种类
		 * 2.在记录不为空时，将单价和数量相乘
		 * 3.累加得到总价值
		 * */
		for(Iterator<AssetAppRecord> it = assetAppRecords.iterator(); it.hasNext();){
			AssetAppRecord assetAppRecord = it.next();
			if (assetAppRecord!=null) {
				num++;
			}
			if (assetAppRecord!=null&&assetAppRecord.getAppPrice()!=null&&assetAppRecord.getAppQuantity()!=null) {
				BigDecimal quantity =new BigDecimal(assetAppRecord.getAppQuantity());
				float unitPrice=quantity.multiply(assetAppRecord.getAppPrice()).floatValue();
				totalPrice+=unitPrice;
			}
		}
		mav.addObject("assetAppRecords", assetAppRecords);
		mav.addObject("assetApp", assetApp);
		mav.addObject("id", id);
		mav.addObject("num", num);
		mav.addObject("totalPrice", totalPrice);
		mav.addObject("assetAppAudit", assetAppAudit);
		mav.setViewName("asset/getAssetAppInAudit.jsp");
		return mav;
	}
	
	/***********************************************************************************
	 * @description：药品溶液管理--药品申购（联动查询实验大纲下的实验项目）
	 * @author 郑昕茹
	 * @date：2016-08-30
	 * **********************************************************************************/
	@ResponseBody
	@RequestMapping("/findoperationItemsByoperationOutlineId")
	public Map<String, String> findAssetCabinetByLabRoomId(@RequestParam Integer operationOutlineId){
		Map<String, String> map = new HashMap<String, String>();
		String s = "<option value=\"\">请选择</option>";
		if(operationOutlineId != null){
			//通过主键找到实验大纲
			OperationOutline operationOutline = assetAppService.findOperationOutlineByPrimaryKey(operationOutlineId);
			Set<OperationItem> operationItems = operationOutline.getOperationItems();
			if(operationItems != null){
				for(OperationItem operationItem:operationItems){
					s+="<option value=\""+operationItem.getId()+"\">"+operationItem.getLpName()+"</option>";
				}
			}
		}
		map.put("operationItems", shareService.htmlEncode(s));
		return map;
	}
	
	/***********************************************************************************
	 * @description：药品溶液管理--药品申购（新建药品字典）
	 * @author 郑昕茹
	 * @date：2016-09-17
	 * **********************************************************************************/
	@RequestMapping("/newAssetInApp")
	public ModelAndView newAssetInApp(@RequestParam Integer id){
		ModelAndView mav = new ModelAndView();
		Asset asset = new Asset(); 
		mav.addObject("asset", asset); 
		mav.addObject("id",id);
		
		mav.setViewName("asset/newAssetInApp.jsp");
		return mav;
	}
	
	/***********************************************************************************
	 * @description：药品溶液管理--药品申购（保存药品字典）
	 * @author 郑昕茹
	 * @date：2016-09-17
	 * **********************************************************************************/
	@RequestMapping("/saveAssetInApp")
	public @ResponseBody String saveAssetInApp(@ModelAttribute Asset asset){ 
		asset.setStatus(0);
		assetService.saveAsset(asset);  
		return "success";
	}
	
	/***********************************************************************************
	 * @description 药品溶液管理-药品申购｛删除药品申购记录｝
	 * @author 郑昕茹
	 * @date 2016-10-11
	 * **********************************************************************************/
	@RequestMapping("/deleteAssetAppRecord")
	public ModelAndView deleteAssetAppRecord(@RequestParam Integer id,@RequestParam Integer recordId){
		ModelAndView mav = new ModelAndView();  
		assetAppService.deleteAssetAppRecord(recordId);
		mav.setViewName("redirect:/asset/viewAssetApp?id="+id);
		return mav;
	}
	
	/***********************************************************************************
	 * @description：药品溶液管理--药品申购（新建药品字典）
	 * @author 郑昕茹
	 * @date：2016-09-17
	 * **********************************************************************************/
	@RequestMapping("/editAssetAppRecord")
	public ModelAndView editAssetAppRecord(@RequestParam Integer id, @RequestParam Integer appId){
		ModelAndView mav = new ModelAndView();
		AssetAppRecord assetAppRecord = assetAppService.findAssetAppRecordByPrimaryKey(id);
		mav.addObject("assetAppRecord", assetAppRecord); 
		if(assetAppRecord.getAppSpec().equals("是")){
			mav.addObject("isSpec", 1);
			String[] selectNumber = assetAppRecord.getSelectNumber().split(",");
			if(!selectNumber[0].equals(" ")){
				mav.addObject("flag1", 1);
				mav.addObject("number1", Integer.parseInt(selectNumber[0].trim()));
			}
			else{
				mav.addObject("flag1", 0);
				
			}
			if(!selectNumber[1].equals(" ")){
				mav.addObject("flag2", 1);
				mav.addObject("number2", Integer.parseInt(selectNumber[1].trim()));
			}
			else{
				mav.addObject("flag2", 0);
			}
			if(!selectNumber[2].equals(" ")){
				mav.addObject("flag3", 1);
				mav.addObject("number3", Integer.parseInt(selectNumber[2].trim()));
			}
			else{
				mav.addObject("flag3", 0);
			}
		}else{
			mav.addObject("isSpec", 0);
			mav.addObject("flag1", 0);
			mav.addObject("flag2", 0);
			mav.addObject("flag3", 0);
		}
		if(assetAppRecord.getAppUsage().equals("是")){
			mav.addObject("isUse", 1);
		}else{
			mav.addObject("isUse", 2);
		}
		mav.addObject("assets", assetService.findAllAssetNames());
		mav.addObject("id",id);
		mav.addObject("appId", appId);
		mav.setViewName("asset/editAssetAppRecord.jsp");
		return mav;
	}
	
	/***********************************************************************************
	 * @description：药品溶液管理--药品申购（新建药品字典）
	 * @author 郑昕茹
	 * @date：2016-09-17
	 * **********************************************************************************/
	@RequestMapping("/saveEditAssetAppRecord")
	public @ResponseBody String saveEditAssetAppRecord(HttpServletRequest request,@ModelAttribute AssetAppRecord assetAppRecord, @RequestParam Integer appId){
		assetAppRecord.setAssetApp(assetAppService.findAssetAppByPrimaryKey(appId));
		if(assetAppRecord.getAppSpec().equals("是")){
			String[] numbers = request.getParameterValues("selectNumber");
			String number = "";
			String spec ="";
			if(!numbers[0].equals("")){
				spec += "20ul ,";
				number +=numbers[0]+" "+",";
			}else{
				spec += " ,";
				number +=" "+",";
			}
			if(!numbers[1].equals("")){
				spec += "200ul ,";
				number +=numbers[1]+" "+",";
			}else{
				spec += " ,";
				number +=" "+",";
			}
			if(!numbers[2].equals("")){
				spec += "1000ul ,";
				number +=numbers[2]+" "+",";
			}else{
				spec += " ";
				number +=" ";
			}
			assetAppRecord.setAppfinalSpec(spec);
			assetAppRecord.setSelectNumber(number);
		}
		assetAppService.saveAssetAppRecord(assetAppRecord);
		return "success";
	}
}