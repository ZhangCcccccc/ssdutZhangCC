package net.xidlims.web.asset;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.xidlims.domain.Asset;
import net.xidlims.domain.AssetApp;
import net.xidlims.domain.AssetAppRecord;
import net.xidlims.domain.AssetCabinetWarehouseAccess;
import net.xidlims.domain.AssetCabinetWarehouseAccessRecord;
import net.xidlims.domain.AssetReceive;
import net.xidlims.domain.Authority;
import net.xidlims.domain.Expendable;
import net.xidlims.domain.OperationItem;
import net.xidlims.domain.OperationOutline;
import net.xidlims.domain.User;
import net.xidlims.service.asset.AssetAppService;
import net.xidlims.service.asset.AssetCabinetWarehouseAccessService;
import net.xidlims.service.asset.AssetReceiveService;
import net.xidlims.service.asset.AssetService;
import net.xidlims.service.common.ShareService;

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

import excelTools.ExcelUtils;
import excelTools.JsGridReportBase;
import excelTools.TableData;

@Controller("AssetController")
@SessionAttributes("selected_labCenter")
@RequestMapping("/asset")
public class AssetController<JsonResult> {

	@Autowired AssetService assetService;
	@Autowired ShareService shareService;
	@Autowired AssetAppService assetAppService;
	@Autowired AssetReceiveService assetReceiveService;
	@Autowired AssetCabinetWarehouseAccessService assetCabinetWarehouseAccessService;
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
	 * @功能：药品溶液管理--药品字典（药品字典列表）
	 * @author 郑昕茹
	 * @日期：2016-08-03
	 * **********************************************************************************/
	@RequestMapping("/listAssets")
	public ModelAndView listAssets(@RequestParam int currpage,@RequestParam int category,@ModelAttribute Asset asset){
		ModelAndView mav = new ModelAndView();
		int pageSize = 20;
		//获得所有药品信息 
		List<Asset> listAssets = assetService.findAllAssetsByCategory(currpage, pageSize, asset,category);
		//获取所有药品名称
		List<Asset> listAssetChNames = assetService.findAllAssetNamesByCategory(category);
		int totalRecords = assetService.findAllAssetsByCategory(1, -1, asset,category).size();
		//翻页相关
		Map<String, Integer> pageModel = shareService.getPage(currpage, pageSize, totalRecords);
		mav.addObject("pageSize",pageSize);
		mav.addObject("currpage",currpage);
		mav.addObject("totalRecords", totalRecords);
		mav.addObject("pageModel", pageModel);
		mav.addObject("listAssets", listAssets);
		mav.addObject("listAssetChNames", listAssetChNames);
		//药品类型
		mav.addObject("category",category);
		mav.setViewName("asset/listAssets.jsp");
		return mav;
	}
	/***********************************************************************************
	 * @功能：药品溶液管理--药品字典（新建药品字典）
	 * @author 郑昕茹
	 * @日期：2016-08-04
	 * **********************************************************************************/
	@RequestMapping("/newAsset")
	public ModelAndView newAsset(@RequestParam int category){
		ModelAndView mav = new ModelAndView();
		Asset asset = new Asset(); 
		mav.addObject("asset", asset);
		mav.addObject("flag", 0);
		mav.addObject("isEdit",0);
		//药品类型
		mav.addObject("category",category);
		mav.setViewName("asset/newAsset.jsp");
		return mav;
	}
	/***********************************************************************************
	 * @功能：药品溶液管理--药品字典（编辑药品字典）
	 * @author 郑昕茹
	 * @日期：2016-08-04
	 * **********************************************************************************/
	@RequestMapping("/editAsset")
	public ModelAndView editAsset(@RequestParam Integer id,@RequestParam int category){
		ModelAndView mav = new ModelAndView();
		//通过主键找到需要编辑的药品字典信息
		Asset asset = assetService.findAssetByPrimaryKey(id);
		mav.addObject("flag", asset.getFlag());
		mav.addObject("asset", asset); 
		mav.addObject("isEdit", 1);
		//药品类型
		mav.addObject("category",category);
		mav.setViewName("asset/newAsset.jsp");
		return mav;
	}
	/***********************************************************************************
	 * @功能：药品溶液管理--药品字典（保存药品字典）
	 * @author 郑昕茹
	 * @日期：2016-08-04
	 * **********************************************************************************/
	@RequestMapping("/saveAsset")
	public ModelAndView saveAsset(@ModelAttribute Asset asset,@RequestParam Integer category){
		ModelAndView mav = new ModelAndView();
		asset.setStatus(0);
		assetService.saveAsset(asset);  
		mav.setViewName("redirect:/asset/listAssets?currpage=1&category="+category.toString());
		return mav;
	}
	/***********************************************************************************
	 * @功能：药品溶液管理--药品字典（删除药品字典记录）
	 * @author 郑昕茹
	 * @日期：2016-08-04
	 * **********************************************************************************/
	@RequestMapping("/deleteAsset")
	public ModelAndView deleteAsset(@RequestParam Integer id,@RequestParam Integer category){
		ModelAndView mav = new ModelAndView();
		assetService.deleteAsset(id);
		mav.setViewName("redirect:/asset/listAssets?currpage=1&category="+category.toString());
		return mav;
	}
	/***********************************************************************************
	 * @功能：药品溶液管理--药品字典（导入药品字典记录）
	 * @author 郑昕茹
	 * @日期：2016-08-04
	 * **********************************************************************************/
	@RequestMapping("/importAsset")
	public ModelAndView importAsset(HttpServletRequest request,@RequestParam Integer category){
		ModelAndView mav = new ModelAndView();
		String fileName = shareService.getUpdateFilePath(request);
		String logoRealPathDir = request.getSession().getServletContext().getRealPath("/");
		String filePath = logoRealPathDir + fileName;
		System.out.println(filePath);
		if(filePath.endsWith("xls")||filePath.endsWith("xlsx")){
			assetService.importAsset(filePath,category);
		}
		mav.setViewName("redirect:/asset/listAssets?currpage=1&category="+category.toString());
		return mav;
	}
	/***********************************************************************************
	 * @功能：药品溶液管理--药品字典（导入药品字典记录格式检查）
	 * @author 郑昕茹
	 * @日期：2016-08-04
	 * **********************************************************************************/
	@RequestMapping("/checkRegex")
	public @ResponseBody
	String checkRegex(HttpServletRequest request) throws ParseException {
		//获取文件地址
		String fileName=shareService.getUpdateFilePath(request);
		//获取服务器地址
		String logoRealPathDir = request.getSession().getServletContext().getRealPath("/");
		//获取文件全部地址
		String filePath=logoRealPathDir+fileName;
		return assetService.checkRegex(filePath);
	}
	/***********************************************************************************
	 * @功能：药品溶液管理--物资记录（物资记录列表）
	 * @author 郑昕茹
	 * @日期：2016-08-05
	 * **********************************************************************************/
	/*@RequestMapping("/listAssetRecords")
	public ModelAndView listAssetRecords(@RequestParam int currpage,@ModelAttribute Asset asset){
		//重新更新物资数量（减去申领并领走的数量）
		assetReceiveService.judgeAllocationWarehouseOpenAndReduce();
		ModelAndView mav = new ModelAndView();
		int pageSize = 30;
		//获取所有药品字典
		List<Asset> listAssets = assetService.findAllAssetsInStock(currpage, pageSize/2, asset);
		int totalRecords = assetService.findAllAssetsInStock(1, -1, asset).size() * 2;
		//获取当前登录人
		User user = shareService.getUser();
		//判断当前用户是否为管理员
		boolean isAdmin = false;
		Set<Authority> authorities = user.getAuthorities();
		Iterator<Authority> it = authorities.iterator();
		while(it.hasNext()){
			Authority authority = it.next();
			if(authority.getId() == 11){
				isAdmin = true;
				break;
			}
		}
		//判断该物资是公用还是私用
		int[] ifPublic = new int[pageSize];
		String[] unit = new String[pageSize];//单位
		Integer[] spec = new Integer[pageSize];
		Double[] nums = new Double[pageSize];//总数量
		Integer[] intPart = new Integer[pageSize];//整数部分
		Double[] decimalPart = new Double[pageSize];//小数部分
		int count = 0;//计数遍历到第几条
		int publicNum;//公用的数量
		/*
		 * 公用
		 */
		//遍历药品字典的每条记录，获得其在物资表中的对应的所有记录，并求得总数量
		/*for(Asset a:listAssets){
			//获得物资表中的多条记录
			Set<AssetCabinetWarehouseAccessRecord> accessRecords = a.getAssetCabinetWarehouseAccessRecords();
			//统计每种药品的数量
			double num = 0;
			for(AssetCabinetWarehouseAccessRecord accessRecord:accessRecords){
				//已入库并且为公用
				if(accessRecord.getStatus() == 1 && accessRecord.getAssetCabinetWarehouseAccess().getIfPublic()==1)
				num += accessRecord.getCabinetQuantity().doubleValue();	
			}
			intPart[count] = (int)num;
			decimalPart[count] = num - intPart[count];
			nums[count]= num;
			ifPublic[count] = 1;
			if(a.getSpecifications() != null){
				unit[count] = (a.getSpecifications()).replaceAll("[^a-z^A-Z]", "");
				spec[count]=Integer.parseInt(a.getSpecifications().replaceAll("[^0-9]", ""));
			}
			else{
				unit[count] = a.getUnit();
				spec[count] = 1;
			}
			count++;
		}
		publicNum = count;
		/*
		 * 私用
		 */
		//遍历药品字典的每条记录，获得其在物资表中的对应的所有记录，并求得总数量
		/*for(Asset a:listAssets){
			//统计每种药品的数量
			double num = 0;
			//获得物资表中的多条记录
			Set<AssetCabinetWarehouseAccessRecord> accessRecords = a.getAssetCabinetWarehouseAccessRecords();
			//管理员
			if(isAdmin == true){
				for(AssetCabinetWarehouseAccessRecord accessRecord:accessRecords){
					if(accessRecord.getStatus() == 1 && accessRecord.getAssetCabinetWarehouseAccess().getIfPublic()==0)
					num += accessRecord.getCabinetQuantity().doubleValue();	
				}
				intPart[count] = (int)num;
				decimalPart[count] = num - intPart[count];
				nums[count]= num;
			}
			//普通教师
			else{
				for(AssetCabinetWarehouseAccessRecord accessRecord:accessRecords){
					if(accessRecord.getStatus() == 1 && accessRecord.getAssetCabinetWarehouseAccess().getIfPublic()==0
							&& accessRecord.getAssetCabinetWarehouseAccess() != null
							&& accessRecord.getAssetCabinetWarehouseAccess().getAssetAppRecord()!= null
							&& accessRecord.getAssetCabinetWarehouseAccess().getAssetAppRecord()!= null
							&& accessRecord.getAssetCabinetWarehouseAccess().getAssetAppRecord().getAssetApp() != null
							&& accessRecord.getAssetCabinetWarehouseAccess().getAssetAppRecord().getAssetApp().getUser() != null
							&& accessRecord.getAssetCabinetWarehouseAccess().getAssetAppRecord().getAssetApp().getUser().getUsername().equals(user.getUsername())){
						num += accessRecord.getCabinetQuantity().doubleValue();	
					}
					if(accessRecord.getStatus() == 1 && accessRecord.getAssetCabinetWarehouseAccess().getIfPublic()==0&& accessRecord.getAssetCabinetWarehouseAccess().getUser() != null
							&& accessRecord.getAssetCabinetWarehouseAccess().getUser().getUsername().equals(user.getUsername())){
						num += accessRecord.getCabinetQuantity().doubleValue();	
					}
					
				}
				intPart[count] = (int)num;
				decimalPart[count] = num - intPart[count];
				nums[count]= num;
			}
			ifPublic[count] = 0;
			if(a.getSpecifications() != null){
				unit[count] = (a.getSpecifications()).replaceAll("[^a-z^A-Z]", "");
				spec[count]=Integer.parseInt(a.getSpecifications().replaceAll("[^0-9]", ""));
			}
			else{
				unit[count] = a.getUnit();
				spec[count] = 1;
			}
			count++;		
		}
		//翻页相关
		Map<String, Integer> pageModel = shareService.getPage(currpage, pageSize, totalRecords);
		mav.addObject("pageSize",pageSize);
		mav.addObject("currpage",currpage);
		mav.addObject("totalRecords", totalRecords);
		mav.addObject("pageModel", pageModel);
		mav.addObject("asset", asset);
		mav.addObject("ifPublic", ifPublic);
		mav.addObject("publicNum", publicNum);
		mav.addObject("unit", unit);
		mav.addObject("intPart", intPart);
		mav.addObject("decimalPart", decimalPart);
		mav.addObject("spec", spec);
		mav.addObject("nums", nums);
		mav.addObject("listAssets", listAssets);
		mav.setViewName("asset/listAssetRecords.jsp");
		return mav;
	}*/
	/***********************************************************************************
	 * @功能：药品溶液管理--物资记录（物资记录列表）
	 * @author 郑昕茹
	 * @日期：2016-08-05
	 * **********************************************************************************/
	@RequestMapping("/listAssetRecords")
	public ModelAndView listAssetRecords(@RequestParam int currpage,@ModelAttribute AssetReceive assetReceive,@ModelAttribute("selected_labCenter") Integer cid){
		ModelAndView mav = new ModelAndView();
		//重新更新物资数量（减去申领并领走的数量）
		assetReceiveService.judgeAllocationWarehouseOpenAndReduce();
		int pageSize = 30;
		List<Asset> listAssets = assetService.findAllAssetNamesByCategory(0);
		List<AssetCabinetWarehouseAccess> accesss = assetCabinetWarehouseAccessService.findAllAccesss(currpage, pageSize, assetReceive);
		int totalRecords = assetCabinetWarehouseAccessService.findAllAccesss(1, -1, assetReceive).size();
		String[] unit = new String[pageSize];//单位
		Integer[] spec = new Integer[pageSize];
		Double[] nums = new Double[pageSize];//总数量
		Integer[] intPart = new Integer[pageSize];//整数部分
		Double[] decimalPart = new Double[pageSize];//小数部分
		int count = 0;//计数遍历到第几条
		for(AssetCabinetWarehouseAccess a:accesss){
			if(a.getAsset().getSpecifications() != null){
				unit[count] = (a.getAsset().getSpecifications()).replaceAll("[^a-z^A-Z]", "");
				spec[count]=Integer.parseInt(a.getAsset().getSpecifications().replaceAll("[^0-9]", ""));
			}
			else{
				unit[count] = a.getAsset().getUnit();
				spec[count] = 1;
			}
			//找到属于同一类型，同一asset的物资记录
			List<AssetCabinetWarehouseAccessRecord> sameAccessRecords = assetCabinetWarehouseAccessService.findAllAccesssInSameAssetAndType(a);
			//统计每种药品的数量
			double num = 0;	
			if(sameAccessRecords != null){ 
				 for(AssetCabinetWarehouseAccessRecord sameAccessRecord:sameAccessRecords){
					 num += sameAccessRecord.getCabinetQuantity().doubleValue();	
				 }
			}
			intPart[count] = (int)num;
			decimalPart[count] = num - intPart[count];
			nums[count]= num;
			count++;
		}
		//获取该实验中心下的所有实验室运行大纲
		List<OperationItem> operationItems = assetAppService.findOperationOutlineByLabCenter(cid);
		Set<OperationOutline> operationOutlines = new HashSet<OperationOutline>();
		if(operationItems != null){
			for(OperationItem operationItem:operationItems){
				operationOutlines.add(operationItem.getOperationOutline());
			}
		}
		
		if(assetReceive != null && assetReceive.getType() != null && assetReceive.getType() != 0 && !assetReceive.getType().equals("")){
				//通过主键找到实验大纲
			OperationOutline operationOutline = assetAppService.findOperationOutlineByPrimaryKey(assetReceive.getType());
			Set<OperationItem> operationItemList = operationOutline.getOperationItems();
			mav.addObject("operationItemList", operationItemList);
		}
		mav.addObject("operationOutlines", operationOutlines);
		mav.addObject("assetReceive",assetReceive);
		mav.addObject("accesss", accesss);
		mav.addObject("unit", unit); 
		mav.addObject("intPart", intPart);
		mav.addObject("decimalPart", decimalPart);
		mav.addObject("spec", spec);
		mav.addObject("nums", nums);
		Map<String, Integer> pageModel = shareService.getPage(currpage, pageSize, totalRecords);
		mav.addObject("pageSize",pageSize);
		mav.addObject("currpage",currpage);
		mav.addObject("totalRecords", totalRecords);
		mav.addObject("pageModel", pageModel);
		mav.addObject("listAssets",listAssets);
		mav.setViewName("asset/listAssetRecords.jsp");
		return mav; 
	}
	/***********************************************************************************
	 * @功能：药品溶液管理--物资记录（导出物资记录列表）
	 * @author 郑昕茹
	 * @日期：2016-08-05
	 * **********************************************************************************/
	@RequestMapping("/exportAssetApp")
	public void exportAssetApp(HttpServletRequest request,HttpServletResponse response,@RequestParam Integer isAudit, @RequestParam Integer currpage,Integer assetAuditStatus,@ModelAttribute AssetApp assetApp) throws Exception{
		int pageSize = 30;
		//获得当前登录人
		User user = shareService.getUser();
		Set<Authority> authorities = user.getAuthorities();
		//药品柜管理员或超级管理员
		boolean isAdmin = false;
		for(Authority a:authorities){
			if(a.getId() == 11 || a.getId() == 19){
				isAdmin = true;
				break;
			}
		}
		//获取设备申购的所有信息（因为审核状态没有为9的值，没有进后台关于状态筛选的方法sql）
		//获得查询的开始时间
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");//获得查询的结束时间
		//获取所有药品申购信息
		List<AssetApp> assetApps = new LinkedList<AssetApp>();
		if(isAudit == 1)
		{
			assetApps=assetAppService.findAssetApps(currpage,pageSize,assetAuditStatus,assetApp,null,startDate,endDate);
		}
		else{
			assetApps=assetAppService.findAssetApps(currpage,pageSize,assetAuditStatus,assetApp,user,startDate,endDate);
		}
		List<AssetAppRecord> assetAppRecords = new LinkedList<AssetAppRecord>();
		//获得所有申购信息的申购记录
		for(AssetApp a:assetApps){
			Set<AssetAppRecord> records = a.getAssetAppRecords();
			Iterator<AssetAppRecord> it = records.iterator();
			while(it.hasNext()){
				assetAppRecords.add(it.next());
			}
		}
		//存储打印的内容
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		//计数序号
		Integer count = 1;
		for(AssetAppRecord assetAppRecord:assetAppRecords ){
			Map<String,String> map = new HashMap<String,String>();
			map.put("number", count.toString());//序号
			if(assetAppRecord.getAssetApp() != null){
				//申购编号
				map.put("appNo", assetAppRecord.getAssetApp().getAppNo());
				if(assetAppRecord.getAssetApp().getType() != null && assetAppRecord.getAssetApp().getType() != 0){
					//实验项目名称
					map.put("projectName", assetAppRecord.getAssetApp().getOperationItem().getLpName());
				}
				else{
					map.put("projectName", "");
				}
			}
			else{
				map.put("appNo", "");
				map.put("projectName", "");
				
			}
			//实验教师
			if(assetAppRecord.getAssetApp()!=null && assetAppRecord.getAssetApp().getUser()!=null){
				map.put("appUser", assetAppRecord.getAssetApp().getUser().getCname());
			}
			else{
				map.put("appUser", "");
			}
			if(assetAppRecord.getAsset()!=null){
				map.put("chName", assetAppRecord.getAsset().getChName());//试剂/耗材/小型仪器名称/
				map.put("specifications", assetAppRecord.getAsset().getSpecifications());//规格/型号
				map.put("styleNumber", assetAppRecord.getStyleNumber());//货号
				map.put("cas", assetAppRecord.getAsset().getCas());//CAS号
			}
			else{
				map.put("chName", "");
				map.put("specifications","");
				map.put("styleNumber", "");
				map.put("cas", "");
			}
			map.put("appSupplier",assetAppRecord.getAppSupplier());//经销商
			map.put("appQuantity", assetAppRecord.getAppQuantity().toString());//数量
			map.put("appPrice", assetAppRecord.getAppPrice().toString());//单价
			Double totalPrice = assetAppRecord.getAppQuantity() * assetAppRecord.getAppPrice().doubleValue();
			map.put("totalPrice",totalPrice.toString());//数量
			map.put("appUsage",assetAppRecord.getAppUsage());//是否可在实验材料系统中直接购买
			map.put("appSpec", assetAppRecord.getAppSpec());//是否需要领取移液枪，请写明数量即规格
			map.put("mem", assetAppRecord.getMem());//备注
			list.add(map);
			count++;
		}
		String title = "2016年秋学期专业实验-综合生物实验试剂-耗材-小型仪器预算表";//表头数组
		//属性
		String[] headers = new String[]{"序号","申购编号","实验教师","实验项目名称","试剂/耗材/小型仪器名称","规格/型号","货号","CAS号","经销商","数量","单价","总价","是否可在实验材料系统中直接购买","是否需要领取移液枪,请写明数量及规格","备注"};
		String[] fields = new String[]{"number","appNo","appUser","projectName","chName","specifications","styleNumber","cas","appSupplier","appQuantity","appPrice","totalPrice","appUsage","appSpec","mem"};
		TableData td = ExcelUtils.createTableData(list, ExcelUtils.createTableHeader(headers), fields);	
		JsGridReportBase report = new JsGridReportBase(request,response);
		report.exportToExcel(title, user.getCname(), td);
	}
}