package net.xidlims.web.asset;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import net.xidlims.domain.Asset;
import net.xidlims.domain.AssetAdjustRecord;
import net.xidlims.domain.AssetApp;
import net.xidlims.domain.AssetAppRecord;
import net.xidlims.domain.AssetCabinet;
import net.xidlims.domain.IotSharePowerOpentime;
import net.xidlims.domain.AssetCabinetWarehouse;
import net.xidlims.domain.AssetCabinetWarehouseAccess;
import net.xidlims.domain.AssetCabinetWarehouseAccessRecord;
import net.xidlims.domain.AssetReceive;
import net.xidlims.domain.AssetReceiveRecord;
import net.xidlims.domain.LabCenter;
import net.xidlims.domain.LabRoom;
import net.xidlims.domain.OperationItem;
import net.xidlims.domain.OperationOutline;
import net.xidlims.domain.User;
import net.xidlims.service.asset.AssetAppService;
import net.xidlims.service.asset.AssetCabinetWarehouseAccessService;
import net.xidlims.service.asset.AssetReceiveService;
import net.xidlims.service.asset.AssetService;
import net.xidlims.service.common.ShareService;
import net.xidlims.service.lab.LabRoomService;

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

@Controller("AssetCabinetWarehouseAccessController")
@SessionAttributes("selected_labCenter")
@RequestMapping("/asset")
public class AssetCabinetWarehouseAccessController<JsonResult> {

	@Autowired AssetService assetService;
	@Autowired ShareService shareService;
	@Autowired AssetCabinetWarehouseAccessService assetCabinetWarehouseAccessService;
	@Autowired LabRoomService labRoomService;
	@Autowired AssetAppService assetAppService;
	@Autowired AssetReceiveService assetReceiveService;
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
	 * @功能：药品溶液管理--物资记录（库存调整列表）
	 * @author 郑昕茹
	 * @日期：2016-08-07
	 * **********************************************************************************/
	@RequestMapping("/listStoreAdjustRecords")
	public ModelAndView listStoreAdjustRecords(@RequestParam Integer id){
		ModelAndView mav = new ModelAndView();
		//根据id找到同种类型的物资记录的其中一条
		AssetCabinetWarehouseAccess access = assetCabinetWarehouseAccessService.findAssetCabinetWarehouseAccessByPrimaryKey(id);
		//找到属于同一类型，同一asset的物资记录
		List<AssetCabinetWarehouseAccessRecord> listAccessRecords = assetCabinetWarehouseAccessService.findAllAccesssInSameAssetAndType(access);
		mav.addObject("listAccessRecords", listAccessRecords);
		mav.setViewName("asset/listStoreAdjustRecords.jsp");
		mav.addObject("id", id);
		return mav;
	}
	/***********************************************************************************
	 * @功能：药品溶液管理--物资记录（药品库存调整操作）
	 * @author 郑昕茹
	 * @日期：2016-08-07
	 * **********************************************************************************/
	@RequestMapping("/newAdjustStore")
	public ModelAndView newAdjustStore(@RequestParam Integer id, @RequestParam Integer adjustId){
		ModelAndView mav = new ModelAndView();
		//根据主键找到存储在药品柜的信息
		AssetCabinetWarehouseAccessRecord accessRecord = assetCabinetWarehouseAccessService.findAssetCabinetWarehouseAccessRecordByPrimaryKey(id);
		//获得当前登录人
		User currUser = shareService.getUser();
		//获得所有users
		Map users = shareService.getUsersMap();
		if(accessRecord.getAsset().getSpecifications()!= null){
			String flag = (accessRecord.getAsset().getSpecifications()).replaceAll("[^a-z^A-Z]", ""); 
			mav.addObject("flag", flag);
		}
		mav.addObject("assetAdjustRecord", new AssetAdjustRecord());
		mav.addObject("accessRecord",accessRecord);
		mav.addObject("currUser", currUser);
		mav.addObject("users", users);
		mav.addObject("adjustId", adjustId);
		mav.setViewName("asset/newAdjustStore.jsp");
		return mav;
	}
	/***********************************************************************************
	 * @功能：药品溶液管理--物资记录（保存药品库存调整操作结果）
	 * @author 郑昕茹
	 * @日期：2016-08-07
	 * **********************************************************************************/
	@RequestMapping("/saveAdjustStore")
	public ModelAndView saveAdjustStore(@RequestParam Integer id,HttpServletRequest request,@ModelAttribute AssetAdjustRecord assetAdjustRecord, @RequestParam Integer adjustId){
		ModelAndView mav = new ModelAndView();
		//根据主键找到存储在药品柜的信息
		AssetCabinetWarehouseAccessRecord accessRecord = assetCabinetWarehouseAccessService.findAssetCabinetWarehouseAccessRecordByPrimaryKey(id);
		//调整数量
		String s = assetAdjustRecord.getQuantity().toString();
		
		double adjustQuantity = Double.parseDouble(s);
		//负责人
		User user = shareService.findUserByUsername(request.getParameter("chargePerson"));
		//调整方式
		String adjustWay = assetAdjustRecord.getType().toString();
		
		double beforeQuantity = accessRecord.getCabinetQuantity().doubleValue();//调整前的数量
		double d;//存取调整后的double结果
		//若该药品有规格
		if(accessRecord.getAsset().getSpecifications()== null){
			//添加
			if(adjustWay.equals("1")){
				d = accessRecord.getCabinetQuantity().doubleValue()+adjustQuantity;
				//double转decimal
				BigDecimal bg = new BigDecimal(new Double(d).toString());
				//蛇者改变后的库存数量
				accessRecord.setCabinetQuantity(bg);
			}//减少
			else{
				d = accessRecord.getCabinetQuantity().doubleValue()-adjustQuantity;
				BigDecimal bg = new BigDecimal(new Double(d).toString());
				accessRecord.setCabinetQuantity(bg);
			}
		}//若该药品无规格
		else{
			//获取要增加或减少的数量，将数量/规格
			adjustQuantity = adjustQuantity/Double.parseDouble((accessRecord.getAsset().getSpecifications()).replaceAll("[^0-9]", ""));
			//添加
			if(adjustWay.equals("1")){
				d = accessRecord.getCabinetQuantity().doubleValue()+adjustQuantity;
				BigDecimal bg = new BigDecimal(new Double(d).toString());
				accessRecord.setCabinetQuantity(bg);
			}
			//减少
			else{
				d = accessRecord.getCabinetQuantity().doubleValue()-adjustQuantity;
				BigDecimal bg = new BigDecimal(new Double(d).toString());
				accessRecord.setCabinetQuantity(bg);
			}
		}
		accessRecord=assetCabinetWarehouseAccessService.saveLocationMessage(accessRecord);
		//找到位置信息对应的在用物资记录
		AssetCabinetWarehouseAccess assetCabinetWarehouseAccess = accessRecord.getAssetCabinetWarehouseAccess();
		assetCabinetWarehouseAccess.setUser(user);//负责人
		double changeQuantity = d - beforeQuantity;//调整前后的数量差
		assetCabinetWarehouseAccess.setCabinetQuantity(new BigDecimal(assetCabinetWarehouseAccess.getCabinetQuantity().doubleValue()+changeQuantity));//设置改变后的库存数量
		assetCabinetWarehouseAccessService.saveAssetCabinetWarehouseAccess(assetCabinetWarehouseAccess);
		assetAdjustRecord.setAssetCabinetWarehouseAccessRecord(accessRecord);
		assetAdjustRecord.setAsset(accessRecord.getAsset());
		assetAdjustRecord.setDate(Calendar.getInstance());
		if(accessRecord.getAsset().getSpecifications() != null){
			assetAdjustRecord.setUnit(accessRecord.getAsset().getSpecifications().replaceAll("[^0-9]", ""));
		}
		else{
			assetAdjustRecord.setUnit(accessRecord.getAsset().getUnit());
		}
		assetCabinetWarehouseAccessService.saveAssetAdjustRecord(assetAdjustRecord);
		mav.setViewName("redirect:/asset/listStoreAdjustRecords?id="+adjustId);
		return mav;
	}
	/***********************************************************************************
	 * @description 药品溶液管理--药品入库（申购入库列表）
	 * @author 郑昕茹
	 * @date 2016-08-10
	 * **********************************************************************************/
	@RequestMapping("/listAccessStocks")
	public ModelAndView listAccessStocks(@RequestParam Integer currpage,@ModelAttribute AssetApp assetApp,@RequestParam Integer stockStatus){
		ModelAndView mav = new ModelAndView();
		int pageSize = 30;//设置分页
		//获取所有通过审核的申购信息
		List<AssetApp> listAssetApps=assetAppService.findAssetAppsNeedStock(currpage, pageSize, assetApp, stockStatus);
		for(AssetApp app:listAssetApps){
			Set<AssetAppRecord> assetAppRecords = app.getAssetAppRecords();
			//判断该申购单对应的已通过审核的药品申购记录是否已全部成功入库
			boolean flag = true;
			//遍历所有的药品申购记录，判断是否有未入库的记录，若有说明该申购未入库
			Iterator<AssetAppRecord> assetAppRecordIterator = assetAppRecords.iterator();
			while(assetAppRecordIterator.hasNext()){
			AssetAppRecord a = assetAppRecordIterator.next();
			//只对通过审核的record进行判断
			if(a.getResult()!=null && a.getResult() == 1 &&a.getAsset().getCategory()!=null &&  a.getAsset().getCategory() == 0){
				//表示该记录未入库
				if(a.getStockStatus() == 0 || a.getStockStatus() == 2){
					flag = false;
					break;
				}
			}
			}
			//该药品申购全部入库
			if(flag == true){
				app.setStockStatus(1);
			}
			//该药品申购未全部入库
			else{
				app.setStockStatus(0);
			}
			assetAppService.saveAssetApp(app);
		}
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
		int totalRecords=assetAppService.findAssetApps(1,-1,1,assetApp,null,null,null).size();
		//翻页相关
		Map<String, Integer> pageModel = shareService.getPage(currpage, pageSize, totalRecords);
		mav.addObject("num", num);//传值
		mav.addObject("listAssetApps", listAssetApps);//传值
		mav.addObject("pageModel", pageModel);
		mav.addObject("assetApp", assetApp);
		mav.addObject("totalRecords", totalRecords);
		mav.addObject("totalPrice", totalPrice); 
		mav.addObject("currpage", currpage);
		mav.addObject("pageSize", pageSize);
		mav.addObject("stockStatus", stockStatus);
		mav.setViewName("asset/listAccessStocks.jsp");
		return mav;
	}
	/***********************************************************************************
	 * @description：药品溶液管理--药品入库（新建药品入库）
	 * @author 郑昕茹
	 * @date：2016-08-11
	 * **********************************************************************************/
	@RequestMapping("/newAccessStock")
	public ModelAndView newAccessStock(@ModelAttribute("selected_labCenter") Integer cid){
		ModelAndView mav = new ModelAndView();
		AssetCabinetWarehouseAccess assetCabinetWarehouseAccess = new AssetCabinetWarehouseAccess(); 
		//User currUser = shareService.getUser();
		//获得所有users
		//Map users = shareService.getUsersMap();
		//找到再有的药品字典记录
		List<Asset> listAssets = assetService.findAllReagentAssetNames();
		//获取该实验中心下的所有实验室运行大纲
		List<OperationItem> operationItems = assetAppService.findOperationOutlineByLabCenter(cid);
		Set<OperationOutline> operationOutlines = new HashSet<OperationOutline>();
		if(operationItems != null){
			for(OperationItem operationItem:operationItems){
				operationOutlines.add(operationItem.getOperationOutline());
			}
		}
		mav.addObject("operationOutlines", operationOutlines);
		mav.addObject("assetCabinetWarehouseAccess", assetCabinetWarehouseAccess); 
		mav.addObject("listAssets", listAssets);
		//mav.addObject("currUser", currUser);
		//mav.addObject("users", users);
		mav.setViewName("asset/newAccessStock.jsp");
		return mav;
	}
	/***********************************************************************************
	 * @description：药品溶液管理--药品入库（保存药品入库）
	 * @author 郑昕茹
	 * @date：2016-08-11
	 * **********************************************************************************/
	@RequestMapping("/saveAccessStock")
	public ModelAndView saveAccessStock(@ModelAttribute AssetCabinetWarehouseAccess assetCabinetWarehouseAccess){
		ModelAndView mav = new ModelAndView(); 
		if(assetCabinetWarehouseAccess.getOperationItem().getId()==null){
			assetCabinetWarehouseAccess.setOperationItem(null);
		}
		assetCabinetWarehouseAccess.setFlag(0);
		assetCabinetWarehouseAccess.setStatus(2);//入库中
		assetCabinetWarehouseAccess=assetCabinetWarehouseAccessService.saveAssetCabinetWarehouseAccess(assetCabinetWarehouseAccess); 
		mav.setViewName("redirect:/asset/setLocationMessage?id="+assetCabinetWarehouseAccess.getId()+"&isClickCompleteStock=0");
		return mav;
	}
	/***********************************************************************************
	 * @description：药品溶液管理--药品入库（设置药品位置具体信息）
	 * @author 郑昕茹
	 * @date：2016-08-11
	 * **********************************************************************************/
	@RequestMapping("/setLocationMessage")
	public ModelAndView setLocationMessage(@RequestParam Integer id,@ModelAttribute LabCenter labCenter, @ModelAttribute("selected_labCenter") Integer cid,@RequestParam Integer isClickCompleteStock){
		ModelAndView mav = new ModelAndView(); 
		//通过主键找到要设置药品位置具体信息的在用物资记录
		AssetCabinetWarehouseAccess assetCabinetWarehouseAccess = assetCabinetWarehouseAccessService.findAssetCabinetWarehouseAccessByPrimaryKey(id);
		//找到该物资对应的所有存放记录
		Set<AssetCabinetWarehouseAccessRecord> accessRecords = assetCabinetWarehouseAccess.getAssetCabinetWarehouseAccessRecords();
		//新建存放记录
		AssetCabinetWarehouseAccessRecord accessRecord = new AssetCabinetWarehouseAccessRecord();
		List<AssetCabinet> assetCabinets = assetCabinetWarehouseAccessService.findAllAssetCabinets();
		//找到所有的实验室
		Set<LabRoom> labRooms = new HashSet<LabRoom>();
		if(assetCabinets != null){
			for(AssetCabinet assetCabinet:assetCabinets){
				labRooms.add(assetCabinet.getLabRoom());
			}
		}
		//找到物资字典中的所有药品
		List<Asset> assets = assetService.findAllAssetNamesByCategory(0);
		mav.addObject("assetCabinetWarehouseAccess", assetCabinetWarehouseAccess); 
		mav.addObject("accessRecords", accessRecords);
		mav.addObject("accessRecord", accessRecord);
		mav.addObject("labRooms", labRooms);
		mav.addObject("assets", assets);
		mav.addObject("id", id);
		mav.addObject("isClickCompleteStock", isClickCompleteStock);
		mav.addObject("status", assetCabinetWarehouseAccess.getStatus());
		assetCabinetWarehouseAccessService.saveAssetCabinetWarehouseAccess(assetCabinetWarehouseAccess); 
		mav.setViewName("asset/setLocationMessage.jsp");
		return mav;
	}
	/***********************************************************************************
	 * @description：药品溶液管理--药品入库（查看药品位置具体信息）
	 * @author 郑昕茹
	 * @date：2016-08-18
	 * **********************************************************************************/
	@RequestMapping("/viewLocationMessage")
	public ModelAndView viewLocationMessage(@RequestParam Integer id,@ModelAttribute LabCenter labCenter, @ModelAttribute("selected_labCenter") Integer cid){
		ModelAndView mav = new ModelAndView(); 
		//通过主键找到要设置药品位置具体信息的在用物资记录
		AssetCabinetWarehouseAccess assetCabinetWarehouseAccess = assetCabinetWarehouseAccessService.findAssetCabinetWarehouseAccessByPrimaryKey(id);
		//找到该物资对应的所有存放记录
		Set<AssetCabinetWarehouseAccessRecord> accessRecords = assetCabinetWarehouseAccess.getAssetCabinetWarehouseAccessRecords();
		//新建存放记录
		AssetCabinetWarehouseAccessRecord accessRecord = new AssetCabinetWarehouseAccessRecord();
		//找到所有的实验室
		List<LabRoom> labRooms = labRoomService.findLabRoomByLabCenterid(null,1);
		//找到物资字典中的所有药品
		List<Asset> assets = assetService.findAllAssetNamesByCategory(0);
		mav.addObject("assetCabinetWarehouseAccess", assetCabinetWarehouseAccess); 
		mav.addObject("accessRecords", accessRecords);
		mav.addObject("accessRecord", accessRecord);
		mav.addObject("labRooms", labRooms);
		mav.addObject("assets", assets);
		mav.addObject("id", id);
		mav.addObject("status", assetCabinetWarehouseAccess.getStatus());
		assetCabinetWarehouseAccessService.saveAssetCabinetWarehouseAccess(assetCabinetWarehouseAccess); 
		mav.setViewName("asset/viewLocationMessage.jsp");
		return mav;
	}
	
	/***********************************************************************************
	 * @description：药品溶液管理--药品入库（联动查询实验室中的药品柜）
	 * @author 郑昕茹
	 * @date：2016-08-12
	 * **********************************************************************************/
	@ResponseBody
	@RequestMapping("/findAssetCabinetByLabRoomId")
	public Map<String, String> findAssetCabinetByLabRoomId(@RequestParam String labRoomId){
		Map<String, String> map = new HashMap<String, String>();
		String assetCabinetValue = assetCabinetWarehouseAccessService.findAssetCabinetByLabRoomId(labRoomId);
		map.put("assetCabinet", assetCabinetValue);
		return map;
	}
	/***********************************************************************************
	 * @description：药品溶液管理--药品入库（联动查询药品柜中的格子）
	 * @author 郑昕茹
	 * @date：2016-08-12
	 * **********************************************************************************/
	@ResponseBody
	@RequestMapping("/findWarehouseByCabinetId")
	public Map<String, String> findWarehouseByCabinetId(@RequestParam String cabinetId){
		Map<String, String> map = new HashMap<String, String>();
		String warehouseValue = assetCabinetWarehouseAccessService.findWarehouseByCabinetId(cabinetId);
		map.put("warehouse", warehouseValue);
		return map;
	}
	/***********************************************************************************
	 * @description：药品溶液管理--药品入库（保存药品位置信息）
	 * @author 郑昕茹
	 * @date：2016-08-12
	 * **********************************************************************************/
	@RequestMapping("/saveLoctaionMessage")
	public ModelAndView saveLoctaionMessage(@ModelAttribute AssetCabinetWarehouseAccessRecord assetRecord,@RequestParam Integer id){
		ModelAndView mav = new ModelAndView(); 
		//根据主键找到在用物资
		AssetCabinetWarehouseAccess assetCabinetWarehouseAccess = assetCabinetWarehouseAccessService.findAssetCabinetWarehouseAccessByPrimaryKey(id);
		//设置位置信息对应的在用物资
		assetRecord.setAssetCabinetWarehouseAccess(assetCabinetWarehouseAccess);
		//没有真正完成入库
		assetRecord.setStatus(0);
		//表示该字典入库
		assetCabinetWarehouseAccess.getAsset().setStatus(1);
		assetService.saveAsset(assetCabinetWarehouseAccess.getAsset());
		assetRecord.setId(null);
		//设置位置信息对应的药品
		assetRecord.setAsset(assetCabinetWarehouseAccess.getAsset());
		if(assetRecord.getAssetCabinetWarehouse() != null && assetRecord.getAssetCabinetWarehouse().getAssetCabinet() != null 
				&& assetRecord.getAssetCabinetWarehouse().getAssetCabinet().getLabRoom() != null && assetRecord.getAssetCabinetWarehouse().getAssetCabinet().getLabRoom().getId() == 0){
			assetRecord.setAssetCabinetWarehouse(null);
		}
		else{
			assetRecord.setPosition(null);
		}
		//保存位置信息
		assetRecord = assetCabinetWarehouseAccessService.saveLocationMessage(assetRecord);
		
		mav.setViewName("redirect:/asset/setLocationMessage?id="+id.toString()+"&isClickCompleteStock=0");
		return mav;
	}
	/***********************************************************************************
	 * @description：药品溶液管理--药品入库（删除药品位置信息）
	 * @author 郑昕茹
	 * @date：2016-08-13
	 * **********************************************************************************/
	@RequestMapping("/deleteLocationMessage")
	public ModelAndView deleteLocationMessage(@RequestParam Integer id){
		ModelAndView mav = new ModelAndView(); 
		//通过主键找到该条位置信息
		AssetCabinetWarehouseAccessRecord assetRecord = assetCabinetWarehouseAccessService.findAssetCabinetWarehouseAccessRecordByPrimaryKey(id);
		//找到该条位置信息对应的在用物资
		AssetCabinetWarehouseAccess assetCabinetWarehouseAccess = assetRecord.getAssetCabinetWarehouseAccess();
		assetCabinetWarehouseAccess.setStatus(0);
		assetCabinetWarehouseAccessService.saveAssetCabinetWarehouseAccess(assetCabinetWarehouseAccess);
		//找到该在用物资对应的所有位置信息
		Set<AssetCabinetWarehouseAccessRecord> assetRecords = assetCabinetWarehouseAccess.getAssetCabinetWarehouseAccessRecords();
		//遍历位置信息，将status都设为0，因为要删除一条信息，入库的总数量可能发生变化
		Iterator<AssetCabinetWarehouseAccessRecord> it = assetRecords.iterator();
		while(it.hasNext()){
			AssetCabinetWarehouseAccessRecord a = it.next();
			a.setStatus(0);
			assetCabinetWarehouseAccessService.saveLocationMessage(a);
		}
		//通过主键删除药品位置信息
		assetCabinetWarehouseAccessService.deleteLocationMessage(id);
		
		mav.setViewName("redirect:/asset/setLocationMessage?id="+assetCabinetWarehouseAccess.getId().toString()+"&isClickCompleteStock=0");
		return mav;
	}
	
	/***********************************************************************************
	 * @description：药品溶液管理--药品入库（完成入库，将所有位置的总数量与入库数量做判断，相同则入库成功，不同请核对）
	 * @author 郑昕茹
	 * @date：2016-08-13
	 * **********************************************************************************/
	@RequestMapping("/completeStock")
	public ModelAndView completeStock(@RequestParam Integer id){
		ModelAndView mav = new ModelAndView();
		//根据主键找到在用物资
		AssetCabinetWarehouseAccess assetCabinetWarehouseAccess = assetCabinetWarehouseAccessService.findAssetCabinetWarehouseAccessByPrimaryKey(id);
		boolean isMatch = assetCabinetWarehouseAccessService.judgeQuantitiesMatch(assetCabinetWarehouseAccess);
		//入库成功
		if(isMatch == true){
			//找到该要入库的在用物资对应的所有位置信息
			Set<AssetCabinetWarehouseAccessRecord> assetRecords = assetCabinetWarehouseAccess.getAssetCabinetWarehouseAccessRecords();	
			assetCabinetWarehouseAccess.setStatus(1);
			assetCabinetWarehouseAccessService.saveAssetCabinetWarehouseAccess(assetCabinetWarehouseAccess);
			Iterator<AssetCabinetWarehouseAccessRecord> it = assetRecords.iterator();
			while(it.hasNext()){
				AssetCabinetWarehouseAccessRecord assetRecord = it.next();
				assetRecord.setStatus(1);
				assetCabinetWarehouseAccessService.saveLocationMessage(assetRecord);
			}
			
		}
		else{
			//找到该要入库的在用物资对应的所有位置信息
			Set<AssetCabinetWarehouseAccessRecord> assetRecords = assetCabinetWarehouseAccess.getAssetCabinetWarehouseAccessRecords();
			assetCabinetWarehouseAccess.setStatus(2);
			assetCabinetWarehouseAccessService.saveAssetCabinetWarehouseAccess(assetCabinetWarehouseAccess);
			Iterator<AssetCabinetWarehouseAccessRecord> it = assetRecords.iterator();
			while(it.hasNext()){
				AssetCabinetWarehouseAccessRecord assetRecord = it.next();
				assetRecord.setStatus(2);
				assetCabinetWarehouseAccessService.saveLocationMessage(assetRecord);
			}
		}
		mav.setViewName("redirect:/asset/setLocationMessage?id="+id.toString()+"&isClickCompleteStock=1");
		return mav;
	}
	/***********************************************************************************
	 * @description：药品溶液管理--药品入库（查看申购后需要入库的药品）
	 * @author 郑昕茹
	 * @date：2016-08-15
	 * **********************************************************************************/
	@RequestMapping("/viewAppRecordNeedStocks")
	public ModelAndView viewAccessStocks(@RequestParam Integer id){
		ModelAndView mav = new ModelAndView();
		//根据主键找到药品申购记录
		AssetApp assetApp = assetAppService.findAssetAppByPrimaryKey(id);
		//找到该申购对应的所有申购记录
		Set<AssetAppRecord> assetAppRecords = assetApp.getAssetAppRecords();
		//计数申购记录的种类，即总条数
		int num = 0;
		float totalPrice = 0;//存储申购记录的总价
		List<AssetCabinetWarehouseAccess> accesss = new LinkedList<AssetCabinetWarehouseAccess>();
		//遍历所有的申购记录，获取其对应的在用物资记录
		Iterator<AssetAppRecord> it = assetAppRecords.iterator();
		while(it.hasNext()){
			AssetAppRecord assetAppRecord = it.next();
			if (assetAppRecord!=null) {
				num++;
			}
			if (assetAppRecord!=null&&assetAppRecord.getAppPrice()!=null&&assetAppRecord.getAppQuantity()!=null) {
				BigDecimal quantity =new BigDecimal(assetAppRecord.getAppQuantity());
				float unitPrice=quantity.multiply(assetAppRecord.getAppPrice()).floatValue();
				totalPrice+=unitPrice;
			}
			Set<AssetCabinetWarehouseAccess> warehouseAccesss = assetAppRecord.getAssetCabinetWarehouseAccesses();
			Iterator<AssetCabinetWarehouseAccess> accessIt = warehouseAccesss.iterator();
			Iterator<AssetCabinetWarehouseAccess> accessTest = warehouseAccesss.iterator();
			int count = 0;
			while(accessTest.hasNext()){
				accessTest.next();
				count++;
			}
			if(count == 0){
				accesss.add(new AssetCabinetWarehouseAccess());
			}else{
				//取第一条在用物资记录
				while(accessIt.hasNext()){
					AssetCabinetWarehouseAccess access = accessIt.next();
					accesss.add(access);
				}
			}
			
		}
		mav.addObject("assetApp", assetApp); 
		mav.addObject("accesss", accesss);
		mav.addObject("num", num);
		mav.addObject("totalPrice", totalPrice);
		mav.addObject("id", id);
		mav.addObject("assetAppRecords", assetAppRecords);
		mav.setViewName("asset/viewAppRecordNeedStocks.jsp");
		return mav;
	}
	/***********************************************************************************
	 * @description：药品溶液管理--药品入库（与申购相关的新建药品入库）
	 * @author 郑昕茹
	 * @date：2016-08-15
	 * **********************************************************************************/
	@RequestMapping("/newAccessStocks")
	public ModelAndView newAccessStocks(@RequestParam Integer id){
		ModelAndView mav = new ModelAndView();
		//根据主键找到物资申购记录
		AssetAppRecord assetAppRecord = assetAppService.findAssetAppRecordByPrimaryKey(id);
		AssetCabinetWarehouseAccess assetCabinetWarehouseAccess = new AssetCabinetWarehouseAccess();
		Calendar currTime = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		mav.addObject("currTime", sdf.format(currTime.getTime()));
		mav.addObject("assetAppRecord", assetAppRecord);
		mav.addObject("id", id);
		mav.addObject("assetCabinetWarehouseAccess", assetCabinetWarehouseAccess); 
		mav.setViewName("asset/newAccessStocks.jsp");
		return mav;
	}
	
	/***********************************************************************************
	 * @description：药品溶液管理--药品入库（与申购相关的保存药品入库）
	 * @author 郑昕茹
	 * @date：2016-08-15
	 * **********************************************************************************/
	@RequestMapping("/saveAccessStocks")
	public ModelAndView saveAccessStocks(@ModelAttribute AssetCabinetWarehouseAccess assetCabinetWarehouseAccess,@RequestParam Integer id){
		ModelAndView mav = new ModelAndView(); 
		//根据主键找到物资申购记录信息
		AssetAppRecord assetAppRecord = assetAppService.findAssetAppRecordByPrimaryKey(id);
		assetAppRecord.setStockStatus(2);//入库中
		assetAppService.saveAssetAppRecord(assetAppRecord);
		//设置在用物资记录对应的药品申购记录
		assetCabinetWarehouseAccess.setAssetAppRecord(assetAppRecord);
		assetCabinetWarehouseAccess.setAsset(assetAppRecord.getAsset());
		assetCabinetWarehouseAccess.setStatus(2);//入库中
		assetCabinetWarehouseAccess.setType(assetAppRecord.getAssetApp().getType());//
		assetCabinetWarehouseAccess.setFlag(1);//申购相关
		assetCabinetWarehouseAccess.setOperationItem(assetAppRecord.getAssetApp().getOperationItem());
		assetCabinetWarehouseAccess=assetCabinetWarehouseAccessService.saveAssetCabinetWarehouseAccess(assetCabinetWarehouseAccess); 
		mav.setViewName("redirect:/asset/setLocationMessages?id="+assetCabinetWarehouseAccess.getId()+"&isClickCompleteStock=0"+"&appId="+assetAppRecord.getAssetApp().getId());
		return mav;
	}
	
	/***********************************************************************************
	 * @description：药品溶液管理--药品入库（设置药品位置具体信息）
	 * @author 郑昕茹
	 * @date：2016-08-15
	 * **********************************************************************************/
	@RequestMapping("/setLocationMessages")
	public ModelAndView setLocationMessages(@RequestParam Integer id,@RequestParam Integer appId,@ModelAttribute LabCenter labCenter, @ModelAttribute("selected_labCenter") Integer cid,@RequestParam Integer isClickCompleteStock){
		ModelAndView mav = new ModelAndView(); 
		AssetAppRecord appRecord = assetAppService.findAssetAppRecordByPrimaryKey(appId);
		//通过主键找到要设置药品位置具体信息的在用物资记录
		AssetCabinetWarehouseAccess assetCabinetWarehouseAccess = assetCabinetWarehouseAccessService.findAssetCabinetWarehouseAccessByPrimaryKey(id);
		//找到该物资对应的所有存放记录
		if(assetCabinetWarehouseAccess != null)
		{
			Set<AssetCabinetWarehouseAccessRecord> accessRecords = assetCabinetWarehouseAccess.getAssetCabinetWarehouseAccessRecords();
			mav.addObject("accessRecords", accessRecords);
		}
		//新建存放记录
		AssetCabinetWarehouseAccessRecord accessRecord = new AssetCabinetWarehouseAccessRecord(); 
		List<AssetCabinet> assetCabinets = assetCabinetWarehouseAccessService.findAllAssetCabinets();
		//找到所有的实验室
				Set<LabRoom> labRooms = new HashSet<LabRoom>();
				if(assetCabinets != null){
					for(AssetCabinet assetCabinet:assetCabinets){
						labRooms.add(assetCabinet.getLabRoom());
					}
				}
		//找到物资字典中的所有药品
		List<Asset> assets = assetService.findAllAssetNamesByCategory(0);
		mav.addObject("assetCabinetWarehouseAccess", assetCabinetWarehouseAccess); 
		
		mav.addObject("accessRecord", accessRecord);
		mav.addObject("labRooms", labRooms);
		mav.addObject("assets", assets);
		mav.addObject("id", id);
		mav.addObject("appRecordId", appId);
		mav.addObject("isClickCompleteStock", isClickCompleteStock);
		mav.addObject("status", assetCabinetWarehouseAccess.getStatus());
		assetCabinetWarehouseAccessService.saveAssetCabinetWarehouseAccess(assetCabinetWarehouseAccess); 
		mav.setViewName("asset/setLocationMessages.jsp");
		return mav;
	}
	
	/***********************************************************************************
	 * @description：药品溶液管理--药品入库（查看药品位置具体信息）
	 * @author 郑昕茹
	 * @date：2016-08-15
	 * **********************************************************************************/
	@RequestMapping("/viewLocationMessages")
	public ModelAndView viewLocationMessages(@RequestParam Integer id,@RequestParam Integer appId,@ModelAttribute LabCenter labCenter, @ModelAttribute("selected_labCenter") Integer cid){
		ModelAndView mav = new ModelAndView(); 
		//通过主键找到要设置药品位置具体信息的在用物资记录
		AssetCabinetWarehouseAccess assetCabinetWarehouseAccess = assetCabinetWarehouseAccessService.findAssetCabinetWarehouseAccessByPrimaryKey(id);
		//找到该物资对应的所有存放记录
		Set<AssetCabinetWarehouseAccessRecord> accessRecords = assetCabinetWarehouseAccess.getAssetCabinetWarehouseAccessRecords();
		//新建存放记录
		AssetCabinetWarehouseAccessRecord accessRecord = new AssetCabinetWarehouseAccessRecord();
		//找到所有的实验室
		List<LabRoom> labRooms = labRoomService.findLabRoomByLabCenterid(null,1);
		//找到物资字典中的所有药品
		List<Asset> assets = assetService.findAllAssetNamesByCategory(0);
		mav.addObject("assetCabinetWarehouseAccess", assetCabinetWarehouseAccess); 
		mav.addObject("accessRecords", accessRecords);
		mav.addObject("accessRecord", accessRecord);
		mav.addObject("labRooms", labRooms);
		mav.addObject("assets", assets);
		mav.addObject("id", id);
		mav.addObject("appRecordId", appId);
		mav.addObject("status", assetCabinetWarehouseAccess.getStatus());
		assetCabinetWarehouseAccessService.saveAssetCabinetWarehouseAccess(assetCabinetWarehouseAccess); 
		mav.setViewName("asset/viewLocationMessages.jsp");
		return mav;
	}
	 
	/***********************************************************************************
	 * @description：药品溶液管理--药品入库（申购相关保存药品位置信息）
	 * @author 郑昕茹
	 * @date：2016-08-15
	 * **********************************************************************************/
	@RequestMapping("/saveLoctaionMessages")
	public ModelAndView saveLoctaionMessages(@ModelAttribute AssetCabinetWarehouseAccessRecord assetRecord,@RequestParam Integer id){
		ModelAndView mav = new ModelAndView(); 
		//根据主键找到在用物资
		AssetCabinetWarehouseAccess assetCabinetWarehouseAccess = assetCabinetWarehouseAccessService.findAssetCabinetWarehouseAccessByPrimaryKey(id);
		//找到对应的申购记录
		AssetAppRecord assetAppRecord = assetCabinetWarehouseAccess.getAssetAppRecord();
		assetAppRecord.setStockStatus(2);//入库中
		assetAppService.saveAssetAppRecord(assetAppRecord);
		//设置位置信息对应的在用物资
		assetRecord.setAssetCabinetWarehouseAccess(assetCabinetWarehouseAccess);
		//没有真正完成入库，入库中
		assetRecord.setStatus(2);
		assetCabinetWarehouseAccess.getAsset().setStatus(1);
		assetService.saveAsset(assetCabinetWarehouseAccess.getAsset());
		//设置位置信息对应的药品
		assetRecord.setAsset(assetCabinetWarehouseAccess.getAsset());
		assetRecord.setId(null);
		if(assetRecord.getAssetCabinetWarehouse() != null && assetRecord.getAssetCabinetWarehouse().getAssetCabinet() != null 
				&& assetRecord.getAssetCabinetWarehouse().getAssetCabinet().getLabRoom() != null && assetRecord.getAssetCabinetWarehouse().getAssetCabinet().getLabRoom().getId() == 0){
			assetRecord.setAssetCabinetWarehouse(null);
		}
		else{
			assetRecord.setPosition(null);
		}
		assetRecord = assetCabinetWarehouseAccessService.saveLocationMessage(assetRecord);
		mav.setViewName("redirect:/asset/setLocationMessages?id="+id.toString()+"&isClickCompleteStock=0"+"&appId="+id);
		return mav;
	}
	/***********************************************************************************
	 * @description：药品溶液管理--药品入库（完成入库，将所有位置的总数量与入库数量做判断，相同则入库成功，不同请核对）
	 * @author 郑昕茹
	 * @date：2016-08-15
	 * **********************************************************************************/
	@ResponseBody
	@RequestMapping("/checkQuantity")
	public String checkQuantity(@RequestParam Integer id){
		//根据主键找到在用物资
		AssetCabinetWarehouseAccess assetCabinetWarehouseAccess = assetCabinetWarehouseAccessService.findAssetCabinetWarehouseAccessByPrimaryKey(id);
		boolean isMatch = assetCabinetWarehouseAccessService.judgeQuantitiesMatch(assetCabinetWarehouseAccess);// 判断数量是否匹配
		//入库成功
		if(isMatch == true){
			//找到该要入库的在用物资对应的所有位置信息
			Set<AssetCabinetWarehouseAccessRecord> assetRecords = assetCabinetWarehouseAccess.getAssetCabinetWarehouseAccessRecords();	
			assetCabinetWarehouseAccess.setStatus(1);
			assetCabinetWarehouseAccess=assetCabinetWarehouseAccessService.saveAssetCabinetWarehouseAccess(assetCabinetWarehouseAccess);
			Iterator<AssetCabinetWarehouseAccessRecord> it = assetRecords.iterator();
			while(it.hasNext()){
				AssetCabinetWarehouseAccessRecord assetRecord = it.next();
				assetRecord.setStatus(1);
				assetCabinetWarehouseAccessService.saveLocationMessage(assetRecord);
			}
			//找到该在用物资对应的申购记录信息
			AssetAppRecord assetAppRecord = assetCabinetWarehouseAccess.getAssetAppRecord();
			assetAppRecord.setStockStatus(1);
			assetAppService.saveAssetAppRecord(assetAppRecord);
			//找到申购记录信息对应的申购信息
			AssetApp assetApp = assetAppRecord.getAssetApp();
			//找到该申购信息对应的所有申购记录
			Set<AssetAppRecord> assetAppRecords = assetApp.getAssetAppRecords();
			//判断该申购单对应的已通过审核的药品申购记录是否已全部成功入库
			boolean flag = true;
			//遍历所有的药品申购记录，判断是否有未入库的记录，若有说明该申购未入库
			Iterator<AssetAppRecord> assetAppRecordIterator = assetAppRecords.iterator();
			while(assetAppRecordIterator.hasNext()){
			AssetAppRecord a = assetAppRecordIterator.next();
			//只对通过审核的record进行判断
			if(a.getResult() == 1 &&a.getAsset().getCategory() != 1){
				//表示该记录未入库
				if(a.getStockStatus() == 0 || a.getStockStatus() == 2){
					flag = false;
					break;
				}
			}
			}
			//该药品申购全部入库
			if(flag == true){
				assetApp.setStockStatus(1);
			}
			//该药品申购未全部入库
			else{
				assetApp.setStockStatus(0);
			}
			assetAppService.saveAssetApp(assetApp);
			}
			else{
					//找到该要入库的在用物资对应的所有位置信息
					Set<AssetCabinetWarehouseAccessRecord> assetRecords = assetCabinetWarehouseAccess.getAssetCabinetWarehouseAccessRecords();
					assetCabinetWarehouseAccess.setStatus(2);//入库中
					assetCabinetWarehouseAccessService.saveAssetCabinetWarehouseAccess(assetCabinetWarehouseAccess);
					Iterator<AssetCabinetWarehouseAccessRecord> it = assetRecords.iterator();
					while(it.hasNext()){
					AssetCabinetWarehouseAccessRecord assetRecord = it.next();
					assetRecord.setStatus(2);
					assetCabinetWarehouseAccessService.saveLocationMessage(assetRecord);
				}
			}
		if(isMatch == true)return "match";
		else return "notMatch";
	}
	/***********************************************************************************
	 * @description：药品溶液管理--药品入库（删除申购对应的药品位置信息）
	 * @author 郑昕茹
	 * @date：2016-08-13
	 * **********************************************************************************/
	@RequestMapping("/deleteLocationMessages")
	public ModelAndView deleteLocationMessages(@RequestParam Integer id){
		ModelAndView mav = new ModelAndView(); 
		//通过主键找到该条位置信息
		AssetCabinetWarehouseAccessRecord assetRecord = assetCabinetWarehouseAccessService.findAssetCabinetWarehouseAccessRecordByPrimaryKey(id);
		//找到该条位置信息对应的在用物资
		AssetCabinetWarehouseAccess assetCabinetWarehouseAccess = assetRecord.getAssetCabinetWarehouseAccess();
		assetCabinetWarehouseAccess.setStatus(2);
		assetCabinetWarehouseAccessService.saveAssetCabinetWarehouseAccess(assetCabinetWarehouseAccess);
		//找到该在用物资对应的所有位置信息
		Set<AssetCabinetWarehouseAccessRecord> assetRecords = assetCabinetWarehouseAccess.getAssetCabinetWarehouseAccessRecords();
		//遍历位置信息，将status都设为0，因为要删除一条信息，入库的总数量可能发生变化
		Iterator<AssetCabinetWarehouseAccessRecord> it = assetRecords.iterator();
		while(it.hasNext()){
			AssetCabinetWarehouseAccessRecord a = it.next();
			a.setStatus(2);
			assetCabinetWarehouseAccessService.saveLocationMessage(a);
		}
		//通过主键删除药品位置信息
		assetCabinetWarehouseAccessService.deleteLocationMessage(id);
		
		mav.setViewName("redirect:/asset/setLocationMessages?id="+assetCabinetWarehouseAccess.getId().toString()+"&isClickCompleteStock=0"+"&appId="+id);
		return mav;
	}
	/***********************************************************************************
	 * @description：药品溶液管理--药品入库（完成入库，将所有位置的总数量与入库数量做判断，相同则入库成功，不同请核对）
	 * @author 郑昕茹
	 * @date：2016-08-15
	 * **********************************************************************************/
	@ResponseBody
	@RequestMapping("/checkNewStockQuantity")
	public String checkNewStockQuantity(@RequestParam Integer id){
		//根据主键找到在用物资
		AssetCabinetWarehouseAccess assetCabinetWarehouseAccess = assetCabinetWarehouseAccessService.findAssetCabinetWarehouseAccessByPrimaryKey(id);
		boolean isMatch = assetCabinetWarehouseAccessService.judgeQuantitiesMatch(assetCabinetWarehouseAccess);// 判断数量是否匹配
		//入库成功
		if(isMatch == true){
			//找到该要入库的在用物资对应的所有位置信息
			Set<AssetCabinetWarehouseAccessRecord> assetRecords = assetCabinetWarehouseAccess.getAssetCabinetWarehouseAccessRecords();	
			assetCabinetWarehouseAccess.setStatus(1);
			assetCabinetWarehouseAccess=assetCabinetWarehouseAccessService.saveAssetCabinetWarehouseAccess(assetCabinetWarehouseAccess);
			Iterator<AssetCabinetWarehouseAccessRecord> it = assetRecords.iterator();
			while(it.hasNext()){
				AssetCabinetWarehouseAccessRecord assetRecord = it.next();
				assetRecord.setStatus(1);
				assetCabinetWarehouseAccessService.saveLocationMessage(assetRecord);
			}   
			}
			else{
					//找到该要入库的在用物资对应的所有位置信息
					Set<AssetCabinetWarehouseAccessRecord> assetRecords = assetCabinetWarehouseAccess.getAssetCabinetWarehouseAccessRecords();
					assetCabinetWarehouseAccess.setStatus(2);//入库中
					assetCabinetWarehouseAccessService.saveAssetCabinetWarehouseAccess(assetCabinetWarehouseAccess);
					Iterator<AssetCabinetWarehouseAccessRecord> it = assetRecords.iterator();
					while(it.hasNext()){
					AssetCabinetWarehouseAccessRecord assetRecord = it.next();
					assetRecord.setStatus(2);
					assetCabinetWarehouseAccessService.saveLocationMessage(assetRecord);
				}
			}
		if(isMatch == true)return "match";
		else return "notMatch";
	}
	/***********************************************************************************
	 * @description 药品溶液管理--药品入库（新建的入库列表）
	 * @author 郑昕茹
	 * @date 2016-08-10
	 * **********************************************************************************/
	@RequestMapping("/listAccessStock")
	public ModelAndView listAccessStock(@RequestParam Integer currpage){
		ModelAndView mav = new ModelAndView();
		int pageSize = 30;//设置分页
		List<AssetCabinetWarehouseAccess>  assetCabinetWarehouseAccesss = assetCabinetWarehouseAccessService.findAllNewAccessStock(currpage, pageSize);
		//获取总条数
		int totalRecords= assetCabinetWarehouseAccessService.findAllNewAccessStock(1, -1).size();
		//翻页相关
		Map<String, Integer> pageModel = shareService.getPage(currpage, pageSize, totalRecords); 
		mav.addObject("assetCabinetWarehouseAccesss", assetCabinetWarehouseAccesss);//传值
		mav.addObject("pageModel", pageModel); 
		mav.addObject("totalRecords", totalRecords); 
		mav.addObject("currpage", currpage);
		mav.addObject("pageSize", pageSize); 
		mav.setViewName("asset/listAccessStock.jsp");
		return mav;
	}
	
	/***********************************************************************************
	 * @description 药品溶液管理--物资记录（获得物资的库存调整列表）
	 * @author 郑昕茹
	 * @date 2016-08-17
	 * **********************************************************************************/
	@RequestMapping("/getAssetAdjustRecords")
	public ModelAndView getAssetAdjustRecords(@RequestParam Integer currpage, @RequestParam Integer id,@ModelAttribute  AssetAdjustRecord assetAdjustRecord){
		ModelAndView mav = new ModelAndView();
		int pageSize = 20;//设置分页
		AssetCabinetWarehouseAccess assetCabinetWarehouseAccess = assetCabinetWarehouseAccessService.findAssetCabinetWarehouseAccessByPrimaryKey(id);
		List<AssetAdjustRecord> listAssetAdjustRecords = assetCabinetWarehouseAccessService.findAllAdjustRecordsInSameAssetAndType(assetCabinetWarehouseAccess, currpage, pageSize);
		//获取总条数
		int totalRecords= assetCabinetWarehouseAccessService.findAllAdjustRecordsInSameAssetAndType(assetCabinetWarehouseAccess, currpage, pageSize).size();
		//翻页相关
		Map<String, Integer> pageModel = shareService.getPage(currpage, pageSize, totalRecords); 
		mav.addObject("listAssetAdjustRecords", listAssetAdjustRecords);//传值 
		mav.addObject("pageModel", pageModel); 
		mav.addObject("totalRecords", totalRecords); 
		mav.addObject("currpage", currpage);
		mav.addObject("pageSize", pageSize); 
		mav.addObject("id", id);
		mav.addObject("assetAdjustRecord",assetAdjustRecord);
		mav.setViewName("asset/getAssetAdjustRecords.jsp");
		return mav;
	}
	
	/***********************************************************************************
	 * @功能：药品溶液管理--物资记录（每种物资的申领记录列表）
	 * @author 郑昕茹
	 * @日期：2016-08-03
	 * **********************************************************************************/
	@RequestMapping("/getAssetReceiveRecords")
	public ModelAndView getAssetReceiveRecords(@RequestParam int currpage,@RequestParam int id){
		ModelAndView mav = new ModelAndView();
		int pageSize = 20;
		AssetCabinetWarehouseAccess assetCabinetWarehouseAccess = assetCabinetWarehouseAccessService.findAssetCabinetWarehouseAccessByPrimaryKey(id);
		//获得所有药品申购信息 
		List<AssetReceiveRecord> listAssetReceiveRecords = assetCabinetWarehouseAccessService.findAllReceiveRecordsInSameAssetAndType(assetCabinetWarehouseAccess, currpage, pageSize);
		int totalRecords = assetCabinetWarehouseAccessService.findAllReceiveRecordsInSameAssetAndType(assetCabinetWarehouseAccess, 1, -1).size(); 
		//翻页相关
		Map<String, Integer> pageModel = shareService.getPage(currpage, pageSize, totalRecords); 
		mav.addObject("pageSize",pageSize);
		mav.addObject("currpage",currpage);
		mav.addObject("totalRecords", totalRecords);
		mav.addObject("pageModel", pageModel);
		mav.addObject("id", id);
		mav.addObject("listAssetReceiveRecords", listAssetReceiveRecords); 
		mav.setViewName("asset/getAssetReceiveRecords.jsp");
		return mav;
	}
	
	/***********************************************************************************
	 * @功能：药品溶液管理--设置自动开箱时间 
	 * @author 郑昕茹
	 * @日期：2016-09-06
	 * **********************************************************************************/
	@RequestMapping("/listOpenTimes")
	public ModelAndView listOpenTimes(@ModelAttribute IotSharePowerOpentime iotSharePowerOpentime){
		ModelAndView mav = new ModelAndView();  
		List<IotSharePowerOpentime> listOpenTimes = assetCabinetWarehouseAccessService.findAllOpenTimes(iotSharePowerOpentime);
		mav.addObject("listOpenTimes", listOpenTimes); 
		mav.setViewName("asset/listOpenTimes.jsp");
		mav.addObject("iotSharePowerOpentime", iotSharePowerOpentime);
		mav.addObject("schoolTerms", shareService.findAllSchoolTerms());
		return mav;
	}
	
	/***********************************************************************************
	 * @功能：药品溶液管理--新建开箱记录
	 * @author 郑昕茹
	 * @日期：2016-09-06
	 * **********************************************************************************/
	@RequestMapping("/newOpenTime")
	public ModelAndView newOpenTime( ){
		ModelAndView mav = new ModelAndView();   
		mav.addObject("iotSharePowerOpentime", new IotSharePowerOpentime()); 
		mav.addObject("schoolWeekdays", shareService.getWeekdays());
		mav.setViewName("asset/newOpenTime.jsp");
		mav.addObject("schoolTerms", shareService.findAllSchoolTerms());
		return mav;
	}
	
	/***********************************************************************************
	 * @功能：药品溶液管理--编辑开箱记录
	 * @author 郑昕茹
	 * @日期：2016-09-06
	 * **********************************************************************************/
	@RequestMapping("/editOpenTime")
	public ModelAndView editOpenTime(@RequestParam Integer id){
		ModelAndView mav = new ModelAndView();   
		mav.addObject("iotSharePowerOpentime", assetCabinetWarehouseAccessService.findIotSharePowerOpentimeByPrimaryKey(id)); 
		mav.addObject("schoolWeekdays", shareService.getWeekdays());
		mav.setViewName("asset/editOpenTime.jsp");
		mav.addObject("schoolTerms", shareService.findAllSchoolTerms());
		SimpleDateFormat sdf = new SimpleDateFormat( "HH:mm:ss" ); 
		String startTime = sdf.format(assetCabinetWarehouseAccessService.findIotSharePowerOpentimeByPrimaryKey(id).getStartTime().getTime());
		String endTime = sdf.format(assetCabinetWarehouseAccessService.findIotSharePowerOpentimeByPrimaryKey(id).getEndTime().getTime());
		mav.addObject("startTime", startTime);
		mav.addObject("endTime", endTime);
		return mav;
	}
	
	/***********************************************************************************
	 * @功能：药品溶液管理--新建开箱时间记录
	 * @author 郑昕茹
	 * @throws ParseException 
	 * @日期：2016-09-06
	 * **********************************************************************************/
	@RequestMapping("/saveOpenTime")
	public ModelAndView saveOpenTime(@ModelAttribute IotSharePowerOpentime iotSharePowerOpentime,HttpServletRequest request) throws ParseException{
		ModelAndView mav = new ModelAndView();  
		SimpleDateFormat sdformat = new SimpleDateFormat( "HH:mm:ss" ); 
		Date startDate = sdformat.parse(request.getParameter("startTime"));
		Date endDate = sdformat.parse(request.getParameter("endTime"));
		Calendar start = Calendar.getInstance();
		start.setTime(startDate);
		iotSharePowerOpentime.setStartTime(start);
		Calendar end = Calendar.getInstance();
		end.setTime(endDate);
		iotSharePowerOpentime.setEndTime(end);
		assetCabinetWarehouseAccessService.saveIotSharePowerOpentime(iotSharePowerOpentime);
		
		mav.setViewName("redirect:/asset/listOpenTimes"); 
		return mav;
	}
	
	/***********************************************************************************
	 * @功能：药品溶液管理--删除开箱记录
	 * @author 郑昕茹
	 * @日期：2016-09-07
	 * **********************************************************************************/
	@RequestMapping("/deleteOpenTime")
	public ModelAndView deleteOpenTime(@RequestParam Integer id){
		ModelAndView mav = new ModelAndView();   
		assetCabinetWarehouseAccessService.deleteIotSharePowerOpentime(id);
		mav.setViewName("redirect:/asset/listOpenTimes"); 
		return mav;
	}
	
	/***********************************************************************************
	 * @description：药品溶液管理--设置自动开箱时间（根据学期找到该学期下还需要设置的周几）
	 * @author 郑昕茹
	 * @date：2016-09-06
	 * **********************************************************************************/
	@ResponseBody
	@RequestMapping("/findWeekdaysByTermId")
	public Map<String, String> findWeekdaysByTermId(@RequestParam Integer termId){
		Map<String, String> map = new HashMap<String, String>();
		String s = "<option value=\"\">请选择</option>";
		if(termId != null){
			List<IotSharePowerOpentime> IotSharePowerOpentimes = assetCabinetWarehouseAccessService.findOpenTimesByTermId(termId);
			Map<Integer,String> weekdays = shareService.getWeekdays();
			if(IotSharePowerOpentimes != null){
				for(IotSharePowerOpentime IotSharePowerOpentime:IotSharePowerOpentimes){
					weekdays.remove(IotSharePowerOpentime.getSchoolWeekday().getId()); 
				}
			}
				for (Map.Entry entry : weekdays.entrySet()) {  
					  
					s+="<option value=\""+entry.getKey()+"\">"+entry.getValue()+"</option>";
				  
				} 
		}
		map.put("operationItems", shareService.htmlEncode(s));
		return map;
	}
}