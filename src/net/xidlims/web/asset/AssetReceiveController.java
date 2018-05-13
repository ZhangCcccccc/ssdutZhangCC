package net.xidlims.web.asset;

import java.math.BigDecimal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
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

import net.xidlims.constant.CommonConstantInterface;
import net.xidlims.dao.MessageDAO;
import net.xidlims.domain.Asset;
import net.xidlims.domain.AssetApp;
import net.xidlims.domain.AssetAppAudit;
import net.xidlims.domain.AssetAppRecord;
import net.xidlims.domain.AssetCabinetWarehouseAccessRecord;
import net.xidlims.domain.AssetReceive;
import net.xidlims.domain.AssetReceiveAllocation;
import net.xidlims.domain.AssetReceiveAudit;
import net.xidlims.domain.AssetReceiveRecord;
import net.xidlims.domain.Message;
import net.xidlims.domain.OperationItem;
import net.xidlims.domain.OperationOutline;
import net.xidlims.domain.User;
import net.xidlims.service.asset.AssetAppService;
import net.xidlims.service.asset.AssetCabinetWarehouseAccessService;
import net.xidlims.service.asset.AssetReceiveService;
import net.xidlims.service.asset.AssetService;
import net.xidlims.service.common.ShareService;
import net.xidlims.service.devicePurchase.DevicePurchaseService;

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

@Controller("AssetReceiveController")
@SessionAttributes("selected_labCenter")
@RequestMapping("/asset")
public class AssetReceiveController<JsonResult> {

	@Autowired AssetService assetService;
	@Autowired ShareService shareService;
	@Autowired AssetReceiveService assetReceiveService;
	@Autowired DevicePurchaseService devicePurchaseService;
	@Autowired AssetCabinetWarehouseAccessService assetCabinetWarehouseAccessService;
	@Autowired MessageDAO messageDAO;
	@Autowired AssetAppService assetAppService;
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
	 * @功能：药品溶液管理--药品申领（药品申领列表）
	 * @author 郑昕茹
	 * @日期：2016-08-03
	 * **********************************************************************************/
	@RequestMapping("/listAssetReceives")
	public ModelAndView listAssetReceives(@RequestParam int currpage,@ModelAttribute AssetReceive assetReceive, @RequestParam Integer status){
		//重新更新物资数量（减去申领并领走的数量）
		assetReceiveService.judgeAllocationWarehouseOpenAndReduce();
		ModelAndView mav = new ModelAndView();
		int pageSize = 30;
		//获得所有药品申购信息 
		List<AssetReceive> listAssetReceives = assetReceiveService.findAllAssetReceives(currpage, pageSize, assetReceive,status);
		Map users = shareService.getUsersMap();//获取所有用户 
		int totalRecords = assetReceiveService.findAllAssetReceives(1, -1, assetReceive,status).size();
		//翻页相关
		Map<String, Integer> pageModel = shareService.getPage(currpage, pageSize, totalRecords);
		mav.addObject("assetReceive",assetReceive);
		mav.addObject("pageSize",pageSize);
		mav.addObject("currpage",currpage);
		mav.addObject("totalRecords", totalRecords);
		mav.addObject("pageModel", pageModel);
		mav.addObject("listAssetReceives", listAssetReceives);
		mav.addObject("status", status);
		mav.addObject("users", users);
		mav.setViewName("asset/listAssetReceives.jsp");
		return mav;
	}
	/***********************************************************************************
	 * @功能：药品溶液管理--药品申领（新建药品申领）
	 * @author 郑昕茹
	 * @日期：2016-08-09
	 * **********************************************************************************/
	@RequestMapping("/newAssetReceive")
	public ModelAndView newAssetReceive(@ModelAttribute("selected_labCenter") Integer cid){
		ModelAndView mav = new ModelAndView();
		AssetReceive assetReceive = new AssetReceive(); 
		mav.addObject("assetReceive", assetReceive);
		//获取随机数6位
		String randomCode = assetReceiveService.getNumber(assetReceiveService.findReceiveNo());
		//获取当前时间
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		String dateStr = sdf.format(calendar.getTime());
		//生成申购编号
		String appNo = dateStr + randomCode ;
		assetReceive.setReceiveNo(appNo);
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
		mav.addObject("isEdit",0);
		mav.addObject("type", 0);
		mav.setViewName("asset/newAssetReceive.jsp");
		return mav;
	}
	/***********************************************************************************
	 * @功能：药品溶液管理--药品申领（编辑药品申领）
	 * @author 郑昕茹
	 * @日期：2016-08-09
	 * **********************************************************************************/
	@RequestMapping("/editAssetReceive")
	public ModelAndView editAssetReceive(@RequestParam Integer id,@ModelAttribute("selected_labCenter") Integer cid){
		ModelAndView mav = new ModelAndView();
		//通过主键找到需要编辑的药品申领信息
		AssetReceive assetReceive = assetReceiveService.findAssetReceiveByPrimaryKey(id);
		SimpleDateFormat sdf = new SimpleDateFormat( "HH:mm:ss" ); 
		SimpleDateFormat sdformat = new SimpleDateFormat( "yyyy-MM-dd" ); 
		String startDate = sdf.format(assetReceive.getStartData().getTime());
		String endDate = sdf.format(assetReceive.getEndDate().getTime());
		String date = sdformat.format(assetReceive.getEndDate().getTime());
		mav.addObject("assetReceive", assetReceive); 
		mav.addObject("isEdit", 1);
		mav.addObject("startDate", startDate);
		mav.addObject("endDate", endDate);
		mav.addObject("date", date);
		//获取该实验中心下的所有实验室运行大纲
		List<OperationItem> operationItems = assetAppService.findOperationOutlineByLabCenter(cid);
		Set<OperationOutline> operationOutlines = new HashSet<OperationOutline>();
		if(operationItems != null){
			for(OperationItem operationItem:operationItems){
				operationOutlines.add(operationItem.getOperationOutline());
			}
		}
		if(assetReceive.getType() == 0)
		mav.addObject("type", assetReceive.getType());
		else{
			mav.addObject("type",assetReceive.getOperationItem().getOperationOutline().getId());
			mav.addObject("items", assetReceive.getOperationItem().getOperationOutline().getOperationItems());
		}
		mav.addObject("operationOutlines", operationOutlines);
		mav.setViewName("asset/newAssetReceive.jsp");
		return mav;
	}
	/***********************************************************************************
	 * @throws ParseException 
	 * @功能：药品溶液管理--药品申领（保存药品申领）
	 * @author 郑昕茹
	 * @日期：2016-08-09
	 * **********************************************************************************/
	@RequestMapping("/saveAssetReceive")
	public  ModelAndView saveAssetReceive(@ModelAttribute AssetReceive assetReceive,HttpServletRequest request) throws ParseException{
		ModelAndView mav = new ModelAndView();
		assetReceive.setStatus(2);
		if(assetReceive.getOperationItem().getId()==null){
			assetReceive.setOperationItem(null);
		}
		String type = request.getParameter("operationOutline");
		//非实验项目
		if(type.equals("0")){
			assetReceive.setType(0);
			assetReceive.setOperationItem(null);
		}//实验项目
		else{
			assetReceive.setType(1);
		}
		//获取随机数6位
		String randomCode = assetReceiveService.getNumber(assetReceiveService.findReceiveNo());
		
		//获取当前时间
		Calendar calendar = Calendar.getInstance(); 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		String dateStr = sdf.format(calendar.getTime());
		//生成申购编号
		String appNo = dateStr + randomCode ; 
		SimpleDateFormat sdformat = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" ); 
		Date startDate = sdformat.parse(request.getParameter("date")+" "+request.getParameter("startData"));
		Date endDate = sdformat.parse(request.getParameter("date")+" "+request.getParameter("endDate"));
		Calendar start = Calendar.getInstance();
		start.setTime(startDate);
		assetReceive.setStartData(start);
		Calendar end = Calendar.getInstance();
		end.setTime(endDate);
		assetReceive.setEndDate(end);
		assetReceive.setReceiveNo(appNo);
		assetReceive.setUser(shareService.getUser());
		assetReceive.setReceiveDate(Calendar.getInstance());
		assetReceive=assetReceiveService.saveAssetReceive(assetReceive);
		mav.setViewName("redirect:/asset/viewAssetReceive?id="+assetReceive.getId());
		return mav;
	}
	/***********************************************************************************
	 * @description 药品溶液管理-药品申领｛查看｝
	 * @author 徐文
	 * @date 2016-08-12
	 * **********************************************************************************/
	@RequestMapping("/viewAssetReceive")
	public ModelAndView viewAssetReceive(@RequestParam Integer id){
		ModelAndView mav = new ModelAndView();
		AssetReceive assetReceive = assetReceiveService.findAssetReceiveByPrimaryKey(id);
		// 找到能申领的所有物资
		Set<Asset> assets = assetReceiveService.findAssetsCanReceive(assetReceive);
		Set<AssetReceiveRecord> assetReceiveRecords =  assetReceive.getAssetReceiveRecords();//获取申购单中的所有申购记录
        String unit[] = new String[assetReceiveRecords.size()];//存储单位
        Integer spec[] = new Integer[assetReceiveRecords.size()];
        int count = 0;
        //遍历所有的申购记录对应的物资，获取它们的单位
        Iterator<AssetReceiveRecord> it = assetReceiveRecords.iterator();
        while(it.hasNext()){
            Asset asset = it.next().getAsset();
            if(asset.getSpecifications() != null){
                unit[count] = asset.getSpecifications().replaceAll("[^a-z^A-Z]", "");
                spec[count] = Integer.parseInt(asset.getSpecifications().replaceAll("[^0-9]", ""));
            }
            else{
                unit[count] = asset.getUnit();
                spec[count] = 1;
            }
            count++;
        }

        mav.addObject("unit", unit);
        mav.addObject("spec", spec);
		mav.addObject("assetReceive", assetReceive);
		mav.addObject("assetReceiveRecords", assetReceive.getAssetReceiveRecords());
		mav.addObject("assetReceiveRecord", new AssetReceiveRecord());
		mav.addObject("assets", assets);
		mav.addObject("id", id);
		mav.setViewName("asset/viewAssetReceive.jsp");
		return mav;
	}
	/***********************************************************************************
	 * @description 药品溶液管理-药品申领｛保存｝
	 * @author 徐文
	 * @date 2016-08-12
	 * **********************************************************************************/
	@RequestMapping("/saveAssetReceiveRecord")
	public @ResponseBody String saveAssetReceiveRecord(HttpServletRequest request,@ModelAttribute AssetReceiveRecord assetReceiveRecord,@RequestParam Integer id){
		
		assetReceiveRecord.setId(null);
		//assetReceiveRecord=assetReceiveService.saveAssetReceiveRecord(assetReceiveRecord);
		Asset asset = assetService.findAssetByPrimaryKey(assetReceiveRecord.getAsset().getId());
		Double quantity = assetReceiveRecord.getQuantity().doubleValue();
		//找到所用符合申领人的物资数量
		Double number = assetReceiveService.findReceiveNumByAsset(asset, assetReceiveService.findAssetReceiveByPrimaryKey(id));
		if(asset.getSpecifications() != null){
			String spec = asset.getSpecifications().replaceAll("[^0-9]", "");//获得规格
			number = number*Double.parseDouble(spec);
		} 
		if(quantity <= number){
		//将申购记录中申购相关字段设置进去
			//assetAppRecord.setId(null);
			assetReceiveRecord.setAssetReceive(assetReceiveService.findAssetReceiveByPrimaryKey(id));
			assetReceiveRecord=assetReceiveService.saveAssetReceiveRecord(assetReceiveRecord);
			//将数量除以规格得到单位数量
			/*if(assetReceiveRecord.getAsset().getSpecifications() != null){
				String spec = assetReceiveRecord.getAsset().getSpecifications().replaceAll("[^0-9]", "");//获得规格
				assetReceiveRecord.setQuantity(assetReceiveRecord.getQuantity().divide(new BigDecimal(spec),11,BigDecimal.ROUND_HALF_UP));
			} */
			assetReceiveRecord=assetReceiveService.saveAssetReceiveRecord(assetReceiveRecord);
			return "match";
		}
		return "notMatch";
	}
	/***********************************************************************************
	 * @description 药品溶液管理-药品申领｛提交｝
	 * @author 徐文
	 * @date 2016-08-12
	 * **********************************************************************************/
	@RequestMapping("/submitAssetReceive")
	public String submitAssetReceive(@RequestParam Integer id){
        //通过主键找到所有的设备申购
		AssetReceive assetReceive = assetReceiveService.findAssetReceiveByPrimaryKey(id);
		//点击提交后设备状态由未提交（2）变为未审核（3）
		assetReceive.setStatus(3);
		assetReceiveService.saveAssetReceive(assetReceive);
		List<User> superAdmins=shareService.findUsersByAuthorityId(11);//超级管理员
		for(User superAdmin:superAdmins){
			Message message= new Message();
			message.setSendUser(shareService.getUserDetail().getCname());//当前登录人
			message.setSendCparty(shareService.getUserDetail().getSchoolAcademy().getAcademyName());//当前登录人所在学院
			message.setTitle("药品溶液申领");
			String content="申请成功，等待审核";
			content+="<a  href='/xidlims/asset/auditAssetReceive?id=";
			content+=id;
			content+="'>点击审核</a>";
			message.setContent(content);
			message.setMessageState(CommonConstantInterface.INT_Flag_ZERO);
			message.setCreateTime(Calendar.getInstance());
			message.setUsername(superAdmin.getUsername());
			message=messageDAO.store(message);
		}
		
		return "redirect:/asset/listAssetReceives?currpage=1&status=9";
	}
	/***********************************************************************************
	 * @功能：药品溶液管理--药品申领（删除药品申领记录）
	 * @author 郑昕茹
	 * @日期：2016-08-09
	 * **********************************************************************************/
	@RequestMapping("/deleteAssetReceive")
	public ModelAndView deleteAssetReceive(@RequestParam Integer id){
		ModelAndView mav = new ModelAndView();
		assetReceiveService.deleteAssetReceive(id);
		mav.setViewName("redirect:/asset/listAssetReceives?currpage=1&status=9");
		return mav;
	}
	/***********************************************************************************
	 * @功能：药品溶液管理--申领审核（药品申领列表）
	 * @author 郑昕茹
	 * @日期：2016-08-03
	 * **********************************************************************************/
	@RequestMapping("/listAssetReceiveAudits")
	public ModelAndView listAssetReceiveAudits(@RequestParam int currpage,@ModelAttribute AssetReceive assetReceive, @RequestParam Integer status){
		ModelAndView mav = new ModelAndView();
		int pageSize = 30;
		//获得所有药品申购信息 
		List<AssetReceive> listAssetReceives = assetReceiveService.findAllAssetReceives(currpage, pageSize, assetReceive,status);
		//获取所有药品名称
		List<Asset> listAssetChNames = assetService.findAllAssetNamesByCategory(0);
		Map users = shareService.getUsersMap();//获取所有用户
		int totalRecords = assetReceiveService.findAllAssetReceives(1, -1, assetReceive,status).size();
		//翻页相关
		Map<String, Integer> pageModel = shareService.getPage(currpage, pageSize, totalRecords);
		mav.addObject("assetReceive",assetReceive);
		mav.addObject("pageSize",pageSize);
		mav.addObject("currpage",currpage);
		mav.addObject("totalRecords", totalRecords);
		mav.addObject("pageModel", pageModel);
		mav.addObject("status", status);
		mav.addObject("users", users);
		mav.addObject("listAssetReceives", listAssetReceives);
		mav.addObject("listAssetChNames", listAssetChNames);
		mav.setViewName("asset/listAssetReceiveAudits.jsp");
		return mav;
	}
	/***********************************************************************************
	 * @description 药品溶液管理-药品申领审核｛audit｝
	 * @author 徐文
	 * @date 2016-08-12
	 * **********************************************************************************/
	@RequestMapping("/auditAssetReceive")
	public ModelAndView auditAssetReceive(@RequestParam Integer id){
		ModelAndView mav = new ModelAndView();
        //通过主键找到所有的申购
		AssetReceive assetReceive = assetReceiveService.findAssetReceiveByPrimaryKey(id);
		//获取相应的申购记录
		Set<AssetReceiveRecord> listAssetReceiveRecords=assetReceive.getAssetReceiveRecords();
		/*
		 * 获取申领单位
		 */
		String unit[] = new String[listAssetReceiveRecords.size()] ;
		int count = 0;
		
		Iterator<AssetReceiveRecord> it = listAssetReceiveRecords.iterator();
		while(it.hasNext()){
			AssetReceiveRecord assetReceiveRecord = it.next();
			if(assetReceiveRecord.getAsset().getSpecifications() != null){
				unit[count] = (assetReceiveRecord.getAsset().getSpecifications()).replaceAll("[^a-z^A-Z]", ""); //获取单位
			} 
			else{
				unit[count] = assetReceiveRecord.getAsset().getUnit();
			}
			count++;
		}
		//计数申购记录的种类，即总条数
		mav.addObject("assetReceive", assetReceive);
		mav.addObject("listAssetReceiveRecords",listAssetReceiveRecords);
		mav.addObject("unit", unit);
 		mav.addObject("id",id);
		mav.setViewName("asset/auditAssetReceive.jsp");
		return mav;
	}
	/***********************************************************************************
	 * @description 药品溶液管理-药品申购审核｛保存｝
	 * @author 徐文
	 * @date 2016-08-10
	 * **********************************************************************************/
	@RequestMapping("/saveAuditAssetReceive")
	public ModelAndView saveAuditAssetReceive(HttpServletRequest request, @RequestParam int id){
		ModelAndView mav = new ModelAndView();
		//通过主键找到所有的申购
		AssetReceive assetReceive = assetReceiveService.findAssetReceiveByPrimaryKey(id);
		if (request.getParameter("mem")!=null) {
			assetReceive.setMem(request.getParameter("mem"));
		}
		if (request.getParameter("status")!=null) {
			assetReceive.setStatus(Integer.valueOf(request.getParameter("status")));
		}
		assetReceive.setAllocationStatus(0);//设置仓库分配的状态为未分配
		assetReceiveService.saveAssetReceive(assetReceive);
			//jilu表审核结果保存
			// 获取选中的明细表的id
	        String flag1[] = request.getParameterValues("flag1");
	        // 首先对数组排序
	        Arrays.sort(flag1);
	        // 遍历设置审核结果
	        /**
	         * 1.先通过assetApp找到同id下所有的record的信息
	         * 2.对拿到的所有的record进行遍历
	         * 3.用二分法将flag1和遍历的每一个record进行比较
	         * 4.如果flag1得到的信息中有包含record，那么result就大于0
	         * 5.整体审核意见为通过时，将大于0的部分审核意见设置为1，没勾选的设置为0，并且下一级审核中不能再审核该record信息
	         * 6.整体意见不通过时，即便勾选了，也全部设置为0。
	         * */
	        Iterator<AssetReceiveRecord> assetReceiveRecords= assetReceive.getAssetReceiveRecords().iterator();
	        while (assetReceiveRecords.hasNext()) {
	        	AssetReceiveRecord assetReceiveRecord = assetReceiveRecords.next();
	            int result = Arrays.binarySearch(flag1, assetReceiveRecord.getId()
	                    .toString());
	            if (assetReceive.getStatus()==1) {
	            	//药品管理员审核完成后，给项目建设中的设备申购申请人审核成功的消息
	    			Message message= new Message();
	    			message.setSendUser(shareService.getUserDetail().getCname());//当前登录人
	    			message.setSendCparty(shareService.getUserDetail().getSchoolAcademy().getAcademyName());//当前登录人所在学院
	    			message.setTitle("药品溶液申领，药品管理员审核同意，审核通过");
	    			String content="药品管理员审核通过，请查看审核结果";
	    			content+="<a  href='/xidlims/asset/getAssetReceive?id=";
	    			content+=id;
	    			content+="'>点击查看</a>";
	    			message.setContent(content);
	    			message.setMessageState(CommonConstantInterface.INT_Flag_ZERO);
	    			message.setCreateTime(Calendar.getInstance());
	    			message.setUsername(assetReceive.getUser().getUsername());//设备申购申请人
	    			message=messageDAO.store(message);
	            	if (result >= 0) {
	            		// 選中
	            		assetReceiveRecord.setResult(1);
	            		assetReceiveRecord.setAllocationStatus(0);
	            	} else {
	            		// 反選
	            		assetReceiveRecord.setResult(0);
	            	}
	            }
	            	if (assetReceive.getStatus()==0) {
	            		//药品管理员审核完成后，给项目建设中的设备申购申请人审核成功的消息
		    			Message message= new Message();
		    			message.setSendUser(shareService.getUserDetail().getCname());//当前登录人
		    			message.setSendCparty(shareService.getUserDetail().getSchoolAcademy().getAcademyName());//当前登录人所在学院
		    			message.setTitle("药品溶液申领，药品管理员审核不同意，审核不通过");
		    			String content="药品管理员审核未通过，请查看审核结果";
		    			content+="<a  href='/xidlims/asset/getAssetReceive?id=";
		    			content+=id;
		    			content+="'>点击查看</a>";
		    			message.setContent(content);
		    			message.setMessageState(CommonConstantInterface.INT_Flag_ZERO);
		    			message.setCreateTime(Calendar.getInstance());
		    			message.setUsername(assetReceive.getUser().getUsername());//设备申购申请人
		    			message=messageDAO.store(message);
		            	if (result >= 0) {
		            		// 選中
		            		assetReceiveRecord.setResult(0);
		            	} else {
		            		// 反選
		            		assetReceiveRecord.setResult(1);
		            		assetReceiveRecord.setAllocationStatus(0);
		            	}
		            	assetReceiveService.saveAssetReceiveRecord(assetReceiveRecord);
	            }
	        }
		//将审核结果逐条保存到record表里
	    AssetReceiveAudit assetReceiveAudit = new AssetReceiveAudit();
        User user = shareService.getUser();
        //获取的登录人
        assetReceiveAudit.setUser(user);
        //审核结果是4（拒绝）的时候保存为0，是3，2通过保存为1
        if (assetReceive.getStatus()==1) {
        	assetReceiveAudit.setStatus(1);
        	assetReceiveAudit.setResult("通过");
        }
        if (assetReceive.getStatus()==0) {
        	assetReceiveAudit.setStatus(0);
        	assetReceiveAudit.setResult("拒绝");
        }
        assetReceiveAudit.setCreateDate(Calendar.getInstance());
        //关联表保存
        assetReceiveAudit.setAssetReceive(assetReceive);
        assetReceiveService.saveAuditAssetReceive(assetReceiveAudit);
		mav.setViewName("redirect:/asset/allocateReceive?id="+id);
		return mav;
	}
	/***********************************************************************************
	 * @description 药品溶液管理-药品申购｛查看｝
	 * @author 徐文
	 * @date 2016-08-09
	 * **********************************************************************************/
	@RequestMapping("/viewAuditAssetReceive")
	public ModelAndView viewAuditAssetReceive(@RequestParam Integer id){
		ModelAndView mav = new ModelAndView();
        //通过主键找到所有的设备申购
		AssetReceive assetReceive = assetReceiveService.findAssetReceiveByPrimaryKey(id);
		//找到该申购对应的所有申领记录
		Set<AssetReceiveRecord> listAssetReceiveRecords=assetReceive.getAssetReceiveRecords();
		Set<AssetReceiveAudit> listAssetReceiveAudits=assetReceive.getAssetReceiveAudits();
		/*
		 * 若审核通过并完成分配，显示分配信息
		 */
		Map<AssetReceiveAllocation,String> receiveAllocationsAndUnits = new HashMap<AssetReceiveAllocation,String>();//存储分配结果及其单位 
		String allocationUnit;
		Set<AssetReceiveRecord> receiveRecords = assetReceive.getAssetReceiveRecords();//获取所有的申领记录
		Iterator<AssetReceiveRecord> recordIt = receiveRecords.iterator();
		while(recordIt.hasNext()){
			AssetReceiveRecord a = recordIt.next();
			//若已分配
			if(a.getResult() != null && a.getResult() == 1 && a.getAllocationStatus() == 1){
				Set<AssetReceiveAllocation> allocations = a.getAssetReceiveAllocations(); 
				for(AssetReceiveAllocation allocation:allocations){
					if(allocation.getAsset().getSpecifications() != null){
						allocationUnit = (allocation.getAsset().getSpecifications()).replaceAll("[^a-z^A-Z]", ""); //获取单位
					} 
					else{
						allocationUnit = allocation.getAsset().getUnit();
					}
					receiveAllocationsAndUnits.put(allocation, allocationUnit);
				}
			} 
		}
		
		Set<AssetReceiveRecord> assetReceiveRecords = assetReceive.getAssetReceiveRecords(); 
		/*
		 * 获取申领单位
		 */
		String unit[] = new String[assetReceiveRecords.size()] ;
		int count = 0; 
		Iterator<AssetReceiveRecord> it = assetReceiveRecords.iterator();
		while(it.hasNext()){
			AssetReceiveRecord assetReceiveRecord = it.next();
			if(assetReceiveRecord.getAsset().getSpecifications() != null){
				unit[count] = (assetReceiveRecord.getAsset().getSpecifications()).replaceAll("[^a-z^A-Z]", ""); //获取单位
			} 
			else{
				unit[count] = assetReceiveRecord.getAsset().getUnit();
			}
			count++;
		}
		mav.addObject("listAssetReceiveRecords", listAssetReceiveRecords);
		mav.addObject("assetReceive", assetReceive);
		mav.addObject("id", id);
		mav.addObject("listAssetReceiveAudits", listAssetReceiveAudits);
		mav.addObject("assetReceive", assetReceive);
		mav.addObject("assetReceiveRecords", assetReceiveRecords);
		mav.addObject("unit", unit);
		mav.addObject("assets", assetService.findAllAssetNamesByCategory(0));
		mav.addObject("receiveAllocationsAndUnits", receiveAllocationsAndUnits);
		mav.setViewName("asset/viewAuditAssetReceive.jsp");
		return mav;
	}
	/***********************************************************************************
	 * @description 药品溶液管理-药品申领审核｛选择取药品的药品柜｝
	 * @author 徐文
	 * @date 2016-08-12
	 * **********************************************************************************/
	@RequestMapping("/selectWarehouse")
	public ModelAndView selectWarehouse(@RequestParam Integer id){
		ModelAndView mav = new ModelAndView();
        //通过主键找到所有的申购
		AssetReceive assetReceive = assetReceiveService.findAssetReceiveByPrimaryKey(id);
		//获取相应的申购记录
		Set<AssetReceiveRecord> listAssetReceiveRecords=assetReceive.getAssetReceiveRecords();
		//计数申购记录的种类，即总条数
		mav.addObject("assetReceive", assetReceive);
		mav.addObject("listAssetReceiveRecords",listAssetReceiveRecords);
 		mav.addObject("id",id);
		mav.setViewName("asset/auditAssetReceive.jsp");
		return mav;
	}
	
	/***********************************************************************************
	 * @description 药品溶液管理-药品申领｛查看｝
	 * @author 郑昕茹
	 * @date 2016-08-13
	 * **********************************************************************************/
	@RequestMapping("/getAssetReceive")
	public ModelAndView getAssetReceive(@RequestParam Integer id){
		ModelAndView mav = new ModelAndView();
        //通过主键找到所有的设备申购
		AssetReceive assetReceive = assetReceiveService.findAssetReceiveByPrimaryKey(id);
		//找到申领对应的第一个审核记录
		AssetReceiveAudit assetReceiveAudit = null;
		Set<AssetReceiveAudit> assetReceiveAudits = assetReceive.getAssetReceiveAudits();
		for(Iterator<AssetReceiveAudit> it = assetReceiveAudits.iterator(); it.hasNext();){
			assetReceiveAudit = it.next();
			break;
		}
		/*
		 * 若审核通过并完成分配，显示分配信息
		 */
		Map<AssetReceiveAllocation,String> receiveAllocationsAndUnits = new HashMap<AssetReceiveAllocation,String>();//存储分配结果及其单位 
		String allocationUnit;
		Set<AssetReceiveRecord> receiveRecords = assetReceive.getAssetReceiveRecords();//获取所有的申领记录
		Iterator<AssetReceiveRecord> recordIt = receiveRecords.iterator();
		while(recordIt.hasNext()){
			AssetReceiveRecord a = recordIt.next();
			//若已分配
			if(a.getResult() != null && a.getResult() == 1 && a.getAllocationStatus() == 1){
				Set<AssetReceiveAllocation> allocations = a.getAssetReceiveAllocations(); 
				for(AssetReceiveAllocation allocation:allocations){
					if(allocation.getAsset().getSpecifications() != null){
						allocationUnit = (allocation.getAsset().getSpecifications()).replaceAll("[^a-z^A-Z]", ""); //获取单位
					} 
					else{
						allocationUnit = allocation.getAsset().getUnit();
					}
					receiveAllocationsAndUnits.put(allocation, allocationUnit);
				}
			} 
		}
		
		Set<AssetReceiveRecord> assetReceiveRecords = assetReceive.getAssetReceiveRecords(); 
		/*
		 * 获取申领单位
		 */
		String unit[] = new String[assetReceiveRecords.size()] ;
		int count = 0; 
		Iterator<AssetReceiveRecord> it = assetReceiveRecords.iterator();
		while(it.hasNext()){
			AssetReceiveRecord assetReceiveRecord = it.next();
			if(assetReceiveRecord.getAsset().getSpecifications() != null){
				unit[count] = (assetReceiveRecord.getAsset().getSpecifications()).replaceAll("[^a-z^A-Z]", ""); //获取单位
			} 
			else{
				unit[count] = assetReceiveRecord.getAsset().getUnit();
			}
			count++;
		}
		mav.addObject("assetReceive", assetReceive);
		mav.addObject("assetReceiveRecords", assetReceiveRecords);
		mav.addObject("unit", unit);
		mav.addObject("assetReceiveRecord", new AssetReceiveRecord());
		mav.addObject("assets", assetService.findAllAssetNamesByCategory(0));
		mav.addObject("id", id);
		mav.addObject("assetReceiveAudit", assetReceiveAudit);
		mav.addObject("receiveAllocationsAndUnits", receiveAllocationsAndUnits);
		mav.setViewName("asset/getAssetReceive.jsp");
		return mav;
	}
	
	/***********************************************************************************
	 * @description 药品溶液管理-药品申购｛申领物品仓库分配｝
	 * @author 郑昕茹
	 * @date 2016-08-16
	 * **********************************************************************************/
	@RequestMapping("/allocateReceive")
	public ModelAndView allocateReceive(@RequestParam Integer id){
		ModelAndView mav = new ModelAndView();
		User user = shareService.getUser();//获取当前登录人
        //通过主键找到对应的设备申购
		AssetReceive assetReceive = assetReceiveService.findAssetReceiveByPrimaryKey(id);
		//找到该申购对应的所有申领记录
		Set<AssetReceiveRecord> listAssetReceiveRecords=assetReceive.getAssetReceiveRecords();
		String[] units = new String[listAssetReceiveRecords.size()];//存储物资的单位
		Integer[] spec = new Integer[listAssetReceiveRecords.size()];//存规格
		Integer[] nums = new Integer[listAssetReceiveRecords.size()];//对应的在用物资的条数
		int count = 0;//计数遍历到第几条记录
		Iterator<AssetReceiveRecord> it = listAssetReceiveRecords.iterator();
		//遍历所有的申领物资记录，获取它们的单位,规格，判断它们对应的在用物资的条数
		while(it.hasNext()){
			AssetReceiveRecord assetReceiveRecord = it.next();
			nums[count] = 0;
			//找到符合要求的物资记录
			List<AssetCabinetWarehouseAccessRecord> accessRecords =  assetReceiveService.findAssetCabinetWarehouseAccessRecordsByAsset(assetReceiveRecord.getAsset(), assetReceiveRecord.getAssetReceive());
			Iterator<AssetCabinetWarehouseAccessRecord> accessRecordIt =  accessRecords.iterator();
			while(accessRecordIt.hasNext()){
				AssetCabinetWarehouseAccessRecord accessRecord = accessRecordIt.next();
				if(accessRecord.getCabinetQuantity().compareTo(new BigDecimal(0))>0){
					nums[count]++;
				}
			}
			if(assetReceiveRecord.getAsset() != null && assetReceiveRecord.getAsset().getSpecifications() != null){
				units[count] = (assetReceiveRecord.getAsset().getSpecifications()).replaceAll("[^a-z^A-Z]", ""); //鑾峰彇鍗曚綅
				spec[count] = Integer.parseInt((assetReceiveRecord.getAsset().getSpecifications()).replaceAll("[^0-9]", "")); //鑾峰彇瑙勬牸
			}
			else{
				units[count] = assetReceiveRecord.getAsset().getUnit();
				spec[count] = 1;
			}
			//只有一条物资记录并且未分配
			if(nums[count] == 1 && assetReceiveRecord.getAssetReceive().getStatus() == 1 && assetReceiveRecord.getAllocationStatus() == 0){
		        Iterator<AssetCabinetWarehouseAccessRecord> accessRecordOnlyOneIt =  accessRecords.iterator();
		        while(accessRecordOnlyOneIt .hasNext()){
		          AssetCabinetWarehouseAccessRecord accessRecord = accessRecordOnlyOneIt .next();
		          if(accessRecord.getCabinetQuantity().compareTo(new BigDecimal(0))>0){
		        	  assetReceiveRecord.setAllocationStatus(1);//宸插垎閰
			          assetReceiveService.saveAssetReceiveRecord(assetReceiveRecord);
			          //新建分配
			          AssetReceiveAllocation allocation = new AssetReceiveAllocation();
			          allocation.setAsset(accessRecord.getAsset());
			          allocation.setAssetCabinetWarehouse(accessRecord.getAssetCabinetWarehouse());
			          allocation.setAssetReceiveRecord(assetReceiveRecord);
			          allocation.setQuantity(assetReceiveRecord.getQuantity());
			          allocation.setMem("0");//表示还未打开箱子取物资
			          allocation.setAssetCabinetWarehouseAccessRecord(accessRecord);
			          allocation = assetReceiveService.saveAssetReceiveAllocation(allocation);
			          break;
		          }
		        }
		      }
			count++;
		}

		mav.addObject("listAssetReceiveRecords", listAssetReceiveRecords);
		mav.addObject("assetReceive", assetReceive);
		mav.addObject("id", id);
		mav.addObject("units", units);
		mav.addObject("spec", spec);
		mav.addObject("nums", nums);
		mav.setViewName("asset/allocateReceive.jsp");
		return mav;
	}
	
	/***********************************************************************************
	 * @description 药品溶液管理-药品申购｛设置物品仓库信息｝
	 * @author 郑昕茹
	 * @date 2016-08-16
	 * **********************************************************************************/
	@RequestMapping("/setAllocation")
	public ModelAndView setAllocation(@RequestParam int id){
		ModelAndView mav=new ModelAndView();
		//获取当前登录人
		User user = shareService.getUser();//获取当前登录人
		//根据主键找到申领记录信息
		AssetReceiveRecord assetReceiveRecord = assetReceiveService.findAssetReceiveRecordByPrimaryKey(id);
		Asset asset = assetReceiveRecord.getAsset();//找到申领的在用物资记录 
		//用来存放符合条件的在用物资记录
		List<AssetCabinetWarehouseAccessRecord> records = new LinkedList<AssetCabinetWarehouseAccessRecord>();
		//找到符合要求的物资记录
		List<AssetCabinetWarehouseAccessRecord> accessRecords =  assetReceiveService.findAssetCabinetWarehouseAccessRecordsByAsset(assetReceiveRecord.getAsset(), assetReceiveRecord.getAssetReceive());
		Iterator<AssetCabinetWarehouseAccessRecord> accessRecordIt =  accessRecords.iterator();
		while(accessRecordIt.hasNext()){
			AssetCabinetWarehouseAccessRecord accessRecord = accessRecordIt.next();
			if(accessRecord.getCabinetQuantity().compareTo(new BigDecimal(0))>0){
			records.add(accessRecord);
			}
		}
		String[] unit = new String[records.size()];//存储物资的单位
		Integer[] spec = new Integer[records.size()];//存规格
		Integer[] intPart = new Integer[records.size()];//整数部分
		Double[] decimalPart = new Double[records.size()];//小数部分
		int count = 0;
        //遍历所有的申购记录对应的物资，获取它们的单位
        Iterator<AssetCabinetWarehouseAccessRecord> it = records.iterator();
        while(it.hasNext()){
        	AssetCabinetWarehouseAccessRecord record = it.next();
            Asset a = record.getAsset();
            if(a.getSpecifications() != null){
                unit[count] = a.getSpecifications().replaceAll("[^a-z^A-Z]", "");
                spec[count] = Integer.parseInt(a.getSpecifications().replaceAll("[^0-9]", ""));
            }
            else{
                unit[count] = asset.getUnit();
                spec[count] = 1;
            }
            double num = record.getCabinetQuantity().doubleValue();
            intPart[count] = (int)num;
			decimalPart[count] = num - intPart[count];
            count++;
          
        }
        mav.addObject("unit", unit);
        mav.addObject("spec", spec); 
		mav.addObject("assetReceiveRecord", assetReceiveRecord);
		mav.addObject("id", id);
		mav.addObject("records", records);	 
		mav.addObject("intPart", intPart);
		mav.addObject("decimalPart", decimalPart);
		mav.setViewName("asset/setAllocation.jsp");
		return mav;
	}
	/***********************************************************************************
	 * @description 药品溶液管理-药品申购｛查看物品仓库信息｝
	 * @author 郑昕茹
	 * @date 2016-08-16
	 * **********************************************************************************/
	@RequestMapping("/viewAllocation")
	public ModelAndView viewAllocation(@RequestParam int id){
		ModelAndView mav=new ModelAndView();
		//获取当前登录人
		User user = shareService.getUser();//获取当前登录人
		//根据主键找到申领记录信息
		AssetReceiveRecord assetReceiveRecord = assetReceiveService.findAssetReceiveRecordByPrimaryKey(id);
		Asset asset = assetReceiveRecord.getAsset();//找到申领的在用物资记录 
		//用来存放符合条件的在用物资记录
		List<AssetCabinetWarehouseAccessRecord> records = new LinkedList<AssetCabinetWarehouseAccessRecord>();
		//获取申领物资对应的在用物资
		Set<AssetCabinetWarehouseAccessRecord> accessRecords =  assetReceiveRecord.getAsset().getAssetCabinetWarehouseAccessRecords();
		Iterator<AssetCabinetWarehouseAccessRecord> accessRecordIt =  accessRecords.iterator();
		while(accessRecordIt.hasNext()){
			AssetCabinetWarehouseAccessRecord accessRecord = accessRecordIt.next();
			if(accessRecord.getStatus() == 1 && accessRecord.getCabinetQuantity().compareTo(new BigDecimal(0))>0){
					records.add(accessRecord);
			}
		}	
		//String[] units = new String[records.size()];//存储物资的单位
		//Integer[] spec = new Integer[records.size()];//存规格
		mav.addObject("records", records);		
		mav.setViewName("asset/viewAllocation.jsp");
		return mav;
	}
	
	    /***********************************************************************************
	* @description 药品溶液管理-药品申购｛判断并保存仓库分配结果｝
	* @author 郑昕茹
	* @date 2016-08-16
	* **********************************************************************************/
	@RequestMapping("/saveAllocation")
	public @ResponseBody String saveAllocation(HttpServletRequest request, @RequestParam int id){
	ModelAndView mav = new ModelAndView();
	AssetReceiveRecord receiveRecord = assetReceiveService.findAssetReceiveRecordByPrimaryKey(id);
	
	//获取选中的物资对象
	String flag[] = request.getParameterValues("flag[]");
	String quantity[] = request.getParameterValues("quantity[]");
	boolean isMatch = true;//判断单个数量和总数量是否满足条件，满足条件则分配
	String error = "";
	Double totalQuantity = new Double(0); 
	if(flag != null){
		int count = 0;
		for(String f:flag){
			int accessRecordId = Integer.parseInt(f);//获得选中的物资的id
			String spec = "1";
			//根据主键找到对应的在用物资
			AssetCabinetWarehouseAccessRecord accessRecord = assetCabinetWarehouseAccessService.findAssetCabinetWarehouseAccessRecordByPrimaryKey(accessRecordId);
			//找到药品的规格
			if(accessRecord.getAsset().getSpecifications() != null){
				spec = accessRecord.getAsset().getSpecifications().replaceAll("[^0-9]", "");
			}
			else{
				spec = "1";
			}
			if(accessRecord.getCabinetQuantity().multiply(new BigDecimal(spec)).compareTo((new BigDecimal(quantity[count]))) < 0) {
				isMatch = false;
				error = "everyError";
				break;		
			}
			totalQuantity += new Double(quantity[count]) ; 
			count++;
		}
		if(isMatch == true && !totalQuantity.equals(receiveRecord.getQuantity().doubleValue())){
			isMatch = false;
			error = "totalError";
		}
		if(isMatch == true){
			count=0;
			for(String f:flag){
				int accessRecordId = Integer.parseInt(f);//获得选中的物资的id
				String spec = "1";
				//根据主键找到对应的在用物资
				AssetCabinetWarehouseAccessRecord accessRecord = assetCabinetWarehouseAccessService.findAssetCabinetWarehouseAccessRecordByPrimaryKey(accessRecordId);
				AssetReceiveAllocation allocation = new AssetReceiveAllocation();
				allocation.setAsset(accessRecord.getAsset());
				allocation.setAssetReceiveRecord(receiveRecord);
				allocation.setQuantity(new BigDecimal(quantity[count]));
				if(accessRecord.getAssetCabinetWarehouse() != null){
					allocation.setAssetCabinetWarehouse(accessRecord.getAssetCabinetWarehouse());
				}
				else{
					allocation.setPosition(accessRecord.getPosition());
				}
				allocation.setMem("0");//表示还未打开箱子取物资
				allocation.setAssetCabinetWarehouseAccessRecord(accessRecord);
				//allocation.setQuantity((new BigDecimal(quantity[count])).divide(new BigDecimal(spec),11,BigDecimal.ROUND_HALF_UP));
				assetReceiveService.saveAssetReceiveAllocation(allocation);
				count++;
				}
			receiveRecord.setAllocationStatus(1);//当前记录分配完成 
			assetReceiveService.saveAssetReceiveRecord(receiveRecord);
			AssetReceive assetReceive = receiveRecord.getAssetReceive();//获取当前申领记录对应申领单
			assetReceiveService.judgeAllocationStatus(assetReceive);//判断当前申领单中的申领记录是否都已完成分配
		}
		
	}
	if(isMatch == false)return error; 
	else return receiveRecord.getAssetReceive().getId().toString();
	}
	/***********************************************************************************
	 * @description 药品溶液管理-药品申购｛查看物品仓库信息｝
	 * @author 郑昕茹
	 * @date 2016-08-20
	 * **********************************************************************************/
	@RequestMapping("/showViewAllocation")
	public ModelAndView showViewAllocation(@RequestParam int id){
		ModelAndView mav=new ModelAndView();
		//获取当前登录人
		User user = shareService.getUser();//获取当前登录人
		//根据主键找到申领记录信息
		AssetReceiveRecord assetReceiveRecord = assetReceiveService.findAssetReceiveRecordByPrimaryKey(id);
		//Asset asset = assetReceiveRecord.getAsset();//找到申领的在用物资记录 
		//找到分配结果
		Set<AssetReceiveAllocation> allocations =  assetReceiveRecord.getAssetReceiveAllocations();
		/*
		 * 获取物资单位
		 */
		String unit[] = new String[allocations.size()];
		int count = 0;
		for(AssetReceiveAllocation a:allocations){
			Asset asset = a.getAsset();
			if(asset.getSpecifications() != null){
                unit[count] = asset.getSpecifications().replaceAll("[^a-z^A-Z]", ""); 
            }
            else{
                unit[count] = asset.getUnit(); 
            }
            count++;
		}
		//String[] units = new String[records.size()];//存储物资的单位
		//Integer[] spec = new Integer[records.size()];//存规格
		mav.addObject("allocations", allocations);
		mav.addObject("unit", unit);
		mav.setViewName("asset/showViewAllocation.jsp");
		return mav;
	}
	
	/***********************************************************************************
	 * @description：药品溶液管理--药品申购（检查日期间隔与当前时间是否超过两周）
	 * @author 郑昕茹
	 * @date：2016-09-02
	 * **********************************************************************************/
	@ResponseBody
	@RequestMapping("/checkDate")
	public String checkDate(@RequestParam String date,@RequestParam String startDate, @RequestParam String endDate) throws ParseException{
		if(date != null && startDate != null && endDate != null){
			//获取当前时间
			Calendar calendar = Calendar.getInstance(); 
			SimpleDateFormat sdformat = new SimpleDateFormat( "yyyy-MM-dd" ); 
			Date d = sdformat.parse(date);
			Calendar c = Calendar.getInstance();
			c.setTime(d);
			calendar.add(Calendar.DATE, 14);
			if(c.after(calendar)==true){
				return "dateilegal";
			}
			
			SimpleDateFormat sdf = new SimpleDateFormat( "HH:mm:ss" ); 
			Date startdate = sdf.parse(startDate);
			Date enddate = sdf.parse(endDate);
			Calendar start = Calendar.getInstance();
			start.setTime(startdate);
			Calendar end = Calendar.getInstance();
			end.setTime(enddate);
			if(start.after(end)){
				return "ilegal";
			}
			start.add(Calendar.HOUR, 2);
			if(start.before(end)==true){
				return "timeilegal";
			}
			
		
	}
		return "legal";
		}
}