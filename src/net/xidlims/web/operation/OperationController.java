package net.xidlims.web.operation;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.BindException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.xidlims.constant.CommonConstantInterface;
import net.xidlims.dao.AssetCabinetWarehouseAccessDAO;
import net.xidlims.dao.AssetCabinetWarehouseAccessRecordDAO;
import net.xidlims.dao.CDictionaryDAO;
import net.xidlims.dao.CommonDocumentDAO;
import net.xidlims.dao.MessageDAO;
import net.xidlims.dao.OperationItemDAO;
import net.xidlims.dao.OperationOutlineDAO;
import net.xidlims.dao.SystemMajor12DAO;
import net.xidlims.domain.AssetCabinetWarehouseAccessRecord;
import net.xidlims.domain.CDictionary;
import net.xidlims.domain.CommonDocument;
import net.xidlims.domain.LabCenter;
import net.xidlims.domain.LabRoom;
import net.xidlims.domain.LabRoomDevice;
import net.xidlims.domain.Message;
import net.xidlims.domain.OperationItem;
import net.xidlims.domain.OperationItemDevice;
import net.xidlims.domain.OperationItemMaterialRecord;
import net.xidlims.domain.OperationOutline;
import net.xidlims.domain.SchoolCourseInfo;
import net.xidlims.domain.SchoolTerm;
import net.xidlims.domain.SystemMajor12;
import net.xidlims.domain.User;
import net.xidlims.service.asset.AssetCabinetWarehouseAccessService;
import net.xidlims.service.common.ShareService;
import net.xidlims.service.device.LabRoomDeviceService;
import net.xidlims.service.dictionary.CDictionaryService;
import net.xidlims.service.lab.LabCenterService;
import net.xidlims.service.lab.LabRoomService;
import net.xidlims.service.operation.OperationService;
import net.xidlims.service.system.SystemLogService;
import net.xidlims.service.system.SystemService;
import net.xidlims.service.timetable.OuterApplicationService;
import net.xidlims.service.timetable.SchoolCourseInfoService;

import org.hibernate.Session;
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

@Controller("OperationController")
@SessionAttributes("selected_labCenter")
@RequestMapping("/operation")
public class OperationController<JsonResult> {

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
	private SystemService systemService;
	@Autowired
	private LabRoomService labRoomService;
	@Autowired
	private LabCenterService labCenterService;
	@Autowired
	private OperationService operationService;
	@Autowired
	private SystemLogService systemLogService;
	@Autowired
	private SchoolCourseInfoService schoolCourseInfoService;
	@Autowired
	private LabRoomDeviceService labRoomDeviceService;
	@Autowired
	private MessageDAO messageDAO;
	@Autowired
	private SystemMajor12DAO systemMajor12DAO;
	@Autowired
	private CommonDocumentDAO commonDocumentDAO;
	@Autowired
	private OperationItemDAO operationItemDAO;
	@Autowired
	private
	OuterApplicationService outerApplicationService;
	@Autowired
	private
	AssetCabinetWarehouseAccessDAO assetCabinetWarehouseAccessDAO;
	@Autowired
	private CDictionaryService cDictionaryService;
	@Autowired
	private
	AssetCabinetWarehouseAccessService assetCabinetWarehouseAccessService;
	@Autowired
	private
	AssetCabinetWarehouseAccessRecordDAO assetCabinetWarehouseAccessRecordDAO;
	@Autowired
	private OperationOutlineDAO operationOutlineDAO;
	/**
	 * 实验项目列表
	 * @author hly
	 * 2015.07.29
	 * 贺子龙修改于2015-09-02
	 */
	@RequestMapping("/listOperationItem")
	public ModelAndView listOperationItem(HttpServletRequest request,@RequestParam int currpage, int status, int orderBy, @ModelAttribute OperationItem operationItem, @ModelAttribute("selected_labCenter") Integer cid){
		ModelAndView mav = new ModelAndView();
		SchoolCourseInfo schoolCourseInfo = new SchoolCourseInfo();
		User user = new User();
		User currUser = shareService.getUserDetail();  //当前登录人
		/*if(!shareService.checkAuthority(currUser.getUsername(), "EXCENTERDIRECTOR"))
		{
			operationItem.setUserByLpCreateUser(currUser);  //显示当前登录人的实验项目
		}*/  //2015-11-18  按照建桥张老师的需求修改
		if (status==5) {
			if (!shareService.checkAuthority(currUser.getUsername(), "EXCENTERDIRECTOR")) {
				operationItem.setUserByLpCheckUser(currUser);//显示当前登录人需要审核的实验项目
			}
		}
		if(cid!=null&&cid!=-1)
		{
			mav.addObject("schoolAcademy", labCenterService.findLabCenterByPrimaryKey(cid).getSchoolAcademy());
		}
		
		switch (status) 
		{
		    case 1:
		    	operationItem.setCDictionaryByLpStatusCheck(shareService.getCDictionaryByCategory("status_operation_item_check", "1"));
			    break;
		    case 2:
		    	operationItem.setCDictionaryByLpStatusCheck(shareService.getCDictionaryByCategory("status_operation_item_check", "2"));
		    	break;
		    case 3:
		    	operationItem.setCDictionaryByLpStatusCheck(shareService.getCDictionaryByCategory("status_operation_item_check", "3"));
		    	break;
		    case 4:
		    	operationItem.setCDictionaryByLpStatusCheck(shareService.getCDictionaryByCategory("status_operation_item_check", "4"));
		    	break;
		    case 5://我的审核
		    	operationItem.setCDictionaryByLpStatusCheck(shareService.getCDictionaryByCategory("status_operation_item_check", "2"));
		    	break;	
		}
		
		int pageSize = CommonConstantInterface.INT_PAGESIZE;
		int totalRecords = operationService.findAllOperationItemExceptDraft(operationItem, cid);
		mav.addObject("listOperationItem", operationService.findAllOperationItemExceptDraft(currpage, pageSize, operationItem, cid, orderBy));
		mav.addObject("pageModel", shareService.getPage(currpage, pageSize, totalRecords));
//		if(status==0 || status==1)
//		{
//			mav.addObject("users", systemService.getAllUser(1, -1, user));  //教师数据
//		}
		mav.addObject("status", status);
		mav.addObject("orderBy", orderBy);
//		mav.addObject("users", systemService.getAllUser(1, -1, user));  //教师数据
		mav.addObject("users",operationService.getsome());
		mav.addObject("schoolTerms", shareService.findAllSchoolTerm());  //所有学期
//		mav.addObject("schoolCourseInfos", schoolCourseInfoService.getCourseInfoByQuery(schoolCourseInfo, -1, 1, -1));  //课程数据
		mav.addObject("schoolCourseInfos",operationService.getCourse(cid));
		mav.addObject("currUser", currUser);  //当前登录用户
		mav.addObject("operationItem1", new OperationItem());  //用于设置项目编号
		mav.addObject("draft", shareService.getCDictionaryByCategory("status_operation_item_check", "1"));  // 草稿
		mav.addObject("toCheck", shareService.getCDictionaryByCategory("status_operation_item_check", "2"));  // 审核中
		mav.addObject("checkYes", shareService.getCDictionaryByCategory("status_operation_item_check", "3"));  // 审核通过
		mav.addObject("checkNo", shareService.getCDictionaryByCategory("status_operation_item_check", "4"));  //审核拒绝
		//mav.addObject("departmentHeaders", systemService.getUserByAuthority( user, 18));//指定审核人--系主任
		//获取当前学期
		SchoolTerm schoolTerm = shareService.getBelongsSchoolTerm(Calendar.getInstance());
		mav.addObject("schoolTerm", schoolTerm);
		//决定升序还是降序
		boolean asc=true;
		if (orderBy<10) {
			asc=false;
		}
		mav.addObject("asc", asc);
		mav.addObject("page", currpage);
		mav.setViewName("operation/listOperationItem.jsp");
		
/*		String ip = shareService.getIpAddr(request);
		//保存日志信息 saveOperationItemLog(String userIp, int tag, int action, int id)
		//tag：子模块标志位  0-我的实验项目  1-实验项目管理  2-实验项目导入  id：项目卡的id 0-查看 -1--新建
		//action:  0 新建 1 编辑 2 查看 3 删除 4 提交 5 审核查看 6 保存 7 审核编辑后保存 8 导入 9 审核结果
		systemLogService.saveOperationItemLog(ip, 1, 2, 0);*/
		
		return mav;
	}
	
	/**
	 * 教师个人的实验项目列表
	 * @author hly
	 * 2015.08.07
	 */
	@RequestMapping("/listMyOperationItem")
	public ModelAndView listMyOperationItem(HttpServletRequest request,@RequestParam int currpage, int status, int orderBy,@ModelAttribute OperationItem operationItem, @ModelAttribute("selected_labCenter") Integer cid){
		ModelAndView mav = new ModelAndView();
		operationItem.setUserByLpCreateUser(shareService.getUserDetail());  //当前登录人
		User user = new User();
		user.setUserRole("1");  //教师
		if(cid!=null&&cid!=-1)
		{
			user.setSchoolAcademy(labCenterService.findLabCenterByPrimaryKey(cid).getSchoolAcademy());  //显示本中心下的教师
		}
		
		switch (status) 
		{
		    case 1:
		    	operationItem.setCDictionaryByLpStatusCheck(shareService.getCDictionaryByCategory("status_operation_item_check", "1"));
			    break;
		    case 2:
		    	operationItem.setCDictionaryByLpStatusCheck(shareService.getCDictionaryByCategory("status_operation_item_check", "2"));
		    	break;
		    case 3:
		    	operationItem.setCDictionaryByLpStatusCheck(shareService.getCDictionaryByCategory("status_operation_item_check", "3"));
		    	break;
		    case 4:
		    	operationItem.setCDictionaryByLpStatusCheck(shareService.getCDictionaryByCategory("status_operation_item_check", "4"));
		    	break;
		}
		
		int pageSize = CommonConstantInterface.INT_PAGESIZE;
		int totalRecords = operationService.findAllOperationItemByQueryCount(operationItem, cid);
		mav.addObject("listOperationItem", operationService.findAllOperationItemByQuery(currpage, pageSize, operationItem, cid, orderBy));
		mav.addObject("pageModel", shareService.getPage(currpage, pageSize, totalRecords));
		/*if(status==0 || status==1)
		{
			mav.addObject("users", systemService.getAllUser(1, -1, user));  //教师数据
		}*/
		  
  		//List<User> departmentHeaders=systemService.getUserByAuthority(user, 18);
  		List<User> departmentHeaders=systemService.getUsersByAuthorityId(user, 7);
		mav.addObject("departmentHeaders", departmentHeaders);//指定审核人--系主任
		boolean thereIsAHeader=false;
		if (departmentHeaders.size()>0) {
			thereIsAHeader=true;
		}
		mav.addObject("thereIsAHeader", thereIsAHeader);
		mav.addObject("status", status);
		mav.addObject("orderBy", orderBy);
		mav.addObject("schoolCourseInfos",operationService.getCourse(cid,shareService.getUser().getUsername()));//课程数据
		mav.addObject("draft", shareService.getCDictionaryByCategory("status_operation_item_check", "1"));  // 草稿
		mav.addObject("toCheck", shareService.getCDictionaryByCategory("status_operation_item_check", "2"));  // 审核中
		mav.addObject("checkYes", shareService.getCDictionaryByCategory("status_operation_item_check", "3"));  // 审核通过
		mav.addObject("checkNo", shareService.getCDictionaryByCategory("status_operation_item_check", "4"));  //审核拒绝
		mav.addObject("schoolTerms", shareService.findAllSchoolTerm());  //所有学期
		//获取当前学期
		SchoolTerm schoolTerm = shareService.getBelongsSchoolTerm(Calendar.getInstance());
		mav.addObject("schoolTerm", schoolTerm);
		mav.setViewName("operation/listMyOperationItem.jsp");
		//决定升序还是降序
		boolean asc=true;
		if (orderBy<10) {
			asc=false;
		}
		mav.addObject("asc", asc);
		mav.addObject("page", currpage);
		
/*		String ip = shareService.getIpAddr(request);
		//保存日志信息 saveOperationItemLog(String userIp, int tag, int action, int id)
		//tag：子模块标志位  0-我的实验项目  1-实验项目管理  2-实验项目导入  id：项目卡的id 0-查看 -1--新建
		//action:  0 新建 1 编辑 2 查看 3 删除 4 提交 5 审核查看 6 保存 7 审核编辑后保存 8 导入 9 审核结果
		systemLogService.saveOperationItemLog(ip, 0, 2, 0);
		*/
		return mav;
	}
	
	/**
	 * 可以导入的实验项目列表
	 * @author hly
	 * 2015.08.07
	 */
	@RequestMapping("/listOperationItemImport")
	public ModelAndView listOperationItemImport(HttpServletRequest request,@RequestParam int currpage, int orderBy, @ModelAttribute OperationItem operationItem, @ModelAttribute("selected_labCenter") Integer cid){
		ModelAndView mav = new ModelAndView();
		//operationItem.setCDictionaryByLpStatusCheck(shareService.getCDictionaryByCategory("status_operation_item_check", "1"));
		
		int pageSize = CommonConstantInterface.INT_PAGESIZE;
		List<OperationItem> listOperationItem=operationService.findAllOperationItemByQueryImport(currpage, pageSize, operationItem, cid, orderBy);
		List<OperationItem> allOperationItem=operationService.findAllOperationItemByQueryImport(1, -1, operationItem, cid);
		int totalRecords = allOperationItem.size();
		
		mav.addObject("page", currpage);  
		mav.addObject("cid", cid);  //实验中心id
		mav.addObject("orderBy", orderBy);  //实验中心id
		mav.addObject("pageModel", shareService.getPage(currpage, pageSize, totalRecords));
		mav.addObject("schoolTerms", shareService.findAllSchoolTerm());  //所有学期
		mav.addObject("schoolCourseInfos",operationService.getCourse(cid));//课程数据
		mav.addObject("listOperationItem", listOperationItem);
		mav.addObject("allOperationItem", allOperationItem);
		mav.setViewName("operation/listOperationItemImport.jsp");
		//决定升序还是降序
		boolean asc=true;
		if (orderBy<10) {
			asc=false;
		}
		mav.addObject("asc", asc);
		
/*		String ip = shareService.getIpAddr(request);
		//保存日志信息 saveOperationItemLog(String userIp, int tag, int action, int id)
		//tag：子模块标志位  0-我的实验项目  1-实验项目管理  2-实验项目导入  id：项目卡的id 0-查看 -1--新建
		//action:  0 新建 1 编辑 2 查看 3 删除 4 提交 5 审核查看 6 保存 7 审核编辑后保存 8 导入 9 审核结果
		systemLogService.saveOperationItemLog(ip, 2, 2, 0);*/
		return mav;
	}
	
	/**
	 * 新建实验项目
	 * @author hly
	 * 2015.07.29
	 */
	@RequestMapping("/newOperationItem")
	public ModelAndView newOperationItem(HttpServletRequest request,@ModelAttribute("selected_labCenter") Integer cid,int isMine,int flagId){
		ModelAndView mav = new ModelAndView();
		LabRoom labRoom = new LabRoom();
		SchoolCourseInfo schoolCourseInfo = new SchoolCourseInfo();
		
		if(cid!=null && cid>0)
		{
			LabCenter labCenter = labCenterService.findLabCenterByPrimaryKey(cid);
			labRoom.setLabCenter(labCenter);
			mav.addObject("schoolAcademy", labCenter.getSchoolAcademy());
			schoolCourseInfo.setAcademyNumber(shareService.getUser().getSchoolAcademy().getAcademyNumber());
		}
//		mav.addObject("listItemMaterialRecord", operationService.getItemMaterialRecordByItem(itemId));
		mav.addObject("operationItem", new OperationItem());
		mav.addObject("schoolTerms", shareService.findAllSchoolTerm());  //学期
//        mav.addObject("users", systemService.getAllUser(1, -1, user));  //教师数据
        mav.addObject("labRooms", labRoomService.findAllLabRoomByQuery(1, -1, labRoom,-1));  //实验室数据
        mav.addObject("schoolCourseInfos", schoolCourseInfoService.getCourseInfoByQuery(schoolCourseInfo, -1, 1, -1));  //课程数据
        mav.addObject("subjects", systemService.getAllSystemSubject12(1, -1));  //学科数据
        mav.addObject("majors", systemService.getAllSystemMajor12(1, -1));  //专业数据
        //mav.addObject("users", systemService.getAllUser(1, -1, shareService.getUser()));  //教师数据
        mav.addObject("users", systemService.getAllJPAUser(1, -1, shareService.getUser()));  //教师数据
        mav.addObject("labProjectMain", shareService.getCDictionaryData("category_operation_item_main"));  //实验类别
        mav.addObject("labProjectApp", shareService.getCDictionaryData("category_operation_item_app"));  //实验类型
        mav.addObject("labProjectNature", shareService.getCDictionaryData("category_operation_item_nature"));  //实验性质
        mav.addObject("labProjectStudent", shareService.getCDictionaryData("category_operation_item_student"));  //实验者类型
        mav.addObject("labProjectChange", shareService.getCDictionaryData("status_operation_item_change"));  //变动状态
        mav.addObject("labProjectPublic", shareService.getCDictionaryData("category_operation_item_public"));  //开放实验
        mav.addObject("labProjectRewardLevel", shareService.getCDictionaryData("category_operation_item_reward_level"));  //获奖等级
        mav.addObject("labProjectRequire", shareService.getCDictionaryData("category_operation_item_require"));  //实验要求
        mav.addObject("labProjectGuideBook", shareService.getCDictionaryData("category_operation_item_guide_book"));  //实验指导书
        mav.addObject("currSchoolTerm", shareService.getBelongsSchoolTerm(Calendar.getInstance()));  //当前学期
        
        mav.addObject("flagId", flagId);
        mav.addObject("isEdit", false);
        mav.addObject("isMine", isMine);
        //当前类型下的实验大纲
        mav.addObject("outlines", operationOutlineDAO.executeQuery("select o from OperationOutline o where 1=1" +
        		" and labCenter.id="+cid, 0,-1));
		mav.setViewName("operation/editOperationItem.jsp");
		
		String ip = shareService.getIpAddr(request);
		//保存日志信息 saveOperationItemLog(String userIp, int tag, int action, int id)
		//tag：子模块标志位  0-我的实验项目  1-实验项目管理  2-实验项目导入  id：项目卡的id 0-查看 -1--新建
		//action:  0 新建 1 编辑 2 查看 3 删除 4 提交 5 审核查看 6 保存 7 审核编辑后保存 8 导入 9 审核结果
		systemLogService.saveOperationItemLog(ip, 0, 0, -1);
		return mav;
	}
	
	/**
	 * 编辑实验项目
	 * @author hly
	 * 2015.07.29
	 */
	@RequestMapping("/editOperationItem")
	public ModelAndView editOperationItem(HttpServletRequest request,@RequestParam int operationItemId, @ModelAttribute("selected_labCenter") Integer cid,int isMine,int flagId){
		ModelAndView mav = new ModelAndView();
		LabRoom labRoom = new LabRoom();
		SchoolCourseInfo schoolCourseInfo = new SchoolCourseInfo();
		
		if(cid!=null && cid>0)
		{
			LabCenter labCenter = labCenterService.findLabCenterByPrimaryKey(cid);
			labRoom.setLabCenter(labCenter);
			mav.addObject("schoolAcademy", labCenter.getSchoolAcademy());
			schoolCourseInfo.setAcademyNumber(labCenter.getSchoolAcademy().getAcademyNumber());
		}
		
		//仪器设备新增
		OperationItem operationItem = operationService.findOperationItemByPrimaryKey(operationItemId);
		if(operationItem.getLpMajorFit() != null){
			//面向专业
			String majors=operationItem.getLpMajorFit();
			String[] majorArray=majors.split(",");
			List<SystemMajor12>  majorList=new ArrayList<SystemMajor12>();
			List<SystemMajor12> allMajorList=systemService.getAllSystemMajor12(1, -1);
			for (String string : majorArray) {
				SystemMajor12 major=systemMajor12DAO.findSystemMajor12ByPrimaryKey(string);
				if (major!=null) {
					majorList.add(major);
					allMajorList.remove(major);
				}
				
			}
			mav.addObject("majorList", majorList);	//已选专业
			mav.addObject("majors", allMajorList);  //所有专业（排除已选）
		}
		
		operationItem.getCommonDocuments();
		mav.addObject("operationItem", operationItem);
		mav.addObject("listItemDevice", operationService.getItemDeviceByItem(operationItemId, null, 1, -1));
		if(operationItem!=null && operationItem.getLabRoom()!=null)
		{
			List<LabRoomDevice> allLabRoomDevice = labRoomDeviceService.findLabRoomDeviceByRoomId(operationItem.getLabRoom().getId());

			List<LabRoomDevice> existLabRoomDevice = new ArrayList<LabRoomDevice>();
			
			for (OperationItemDevice operationItemDevice : operationItem.getOperationItemDevices()) 
			{
				existLabRoomDevice.add(operationItemDevice.getLabRoomDevice());
			}
			
			allLabRoomDevice.removeAll(existLabRoomDevice);  //去除已经添加的labRoomDevice
			mav.addObject("listLabRoomDevice", allLabRoomDevice);
		}
		
		
		mav.addObject("listItemMaterialRecord", operationService.getItemMaterialRecordByItem(operationItemId));//实验材料 贺子龙新增
		mav.addObject("categoryMaterialRecordMain", shareService.getCDictionaryData("category_operation_item_material_record_main"));
		mav.addObject("operationItemMaterialRecord", new OperationItemMaterialRecord());
		
		mav.addObject("operationItem", operationItem);
		mav.addObject("schoolTerms", shareService.findAllSchoolTerm());  //学期
        mav.addObject("users", systemService.getAllUser(1, -1, shareService.getUser()));  //教师数据
        mav.addObject("labRooms", labRoomService.findAllLabRoomByQuery(1, -1, labRoom,-1));  //实验室数据
        mav.addObject("schoolCourseInfos", schoolCourseInfoService.getCourseInfoByQuery(schoolCourseInfo, -1, 1, -1));  //课程数据
        mav.addObject("subjects", systemService.getAllSystemSubject12(1, -1));  //学科数据
        
        
        mav.addObject("labProjectMain", shareService.getCDictionaryData("category_operation_item_main"));  //实验类别
        mav.addObject("labProjectApp", shareService.getCDictionaryData("category_operation_item_app"));  //实验类型
        mav.addObject("labProjectNature", shareService.getCDictionaryData("category_operation_item_nature"));  //实验性质
        mav.addObject("labProjectStudent", shareService.getCDictionaryData("category_operation_item_student"));  //实验者类型
        mav.addObject("labProjectChange", shareService.getCDictionaryData("status_operation_item_change"));  //变动状态
        mav.addObject("labProjectPublic", shareService.getCDictionaryData("category_operation_item_public"));  //开放实验
        mav.addObject("labProjectRewardLevel", shareService.getCDictionaryData("category_operation_item_reward_level"));  //获奖等级
        mav.addObject("labProjectRequire", shareService.getCDictionaryData("category_operation_item_require"));  //实验要求
        mav.addObject("labProjectGuideBook", shareService.getCDictionaryData("category_operation_item_guide_book"));  //实验指导书
        
        mav.addObject("flagId", flagId);
        mav.addObject("isEdit", true);
        mav.addObject("isMine", isMine);
		mav.setViewName("operation/editOperationItem.jsp");
		
		String ip = shareService.getIpAddr(request);
		//保存日志信息 saveOperationItemLog(String userIp, int tag, int action, int id)
		//tag：子模块标志位  0-我的实验项目  1-实验项目管理  2-实验项目导入  id：项目卡的id 0-查看 -1--新建
		//action:  0 新建 1 编辑 2 查看 3 删除 4 提交 5 审核查看 6 保存 7 审核编辑后保存 8 导入 9 审核结果
		systemLogService.saveOperationItemLog(ip, 0, 1, operationItemId);
		 //当前类型下的实验大纲
        mav.addObject("outlines", operationOutlineDAO.executeQuery("select o from OperationOutline o where 1=1" +
        		" and labCenter.id="+cid, 0,-1));
		//找到该项目下的所有入库的物资
		List<AssetCabinetWarehouseAccessRecord> assetRecords = assetCabinetWarehouseAccessRecordDAO.executeQuery("select a from AssetCabinetWarehouseAccessRecord" +
				" a where 1=1 and status = 1 and asset.category = 0 " +
				"and (assetCabinetWarehouseAccess.type = 0 " +
				"or assetCabinetWarehouseAccess.type != 0 and assetCabinetWarehouseAccess.operationItem.id="+operationItemId+")", 0, -1);
		mav.addObject("assetRecords", assetRecords);
		return mav;
	}
	
	/**
	 * 保存实验项目
	 * @author hly
	 * 2015.07.29
	 */
	@RequestMapping("/saveOperationItem")
	public ModelAndView saveOperationItem(HttpServletRequest request,@ModelAttribute OperationItem operationItem,@RequestParam int toMyList,@ModelAttribute("selected_labCenter") Integer cid){
		ModelAndView mav=new ModelAndView();
		operationItem.setCDictionaryByLpStatusCheck(shareService.getCDictionaryByCategory("status_operation_item_check", "3"));  //无需审核，全部审核通过
		operationItem.setUserByLpCreateUser(shareService.getUserDetail());  //当前登录人
		operationItem.setCreatedAt(Calendar.getInstance());//设置为当前时间
		//保存当前所处的实验类型
		if(cid != -1)
		{
			operationItem.setLabCenter(labCenterService.findLabCenterByPrimaryKey(cid));
		}
		operationItem = operationService.saveOperationItem(operationItem);
		mav.setViewName("redirect:/operation/editOperationItem?operationItemId="+operationItem.getId()+"&&isMine="+toMyList+"&flagId=10");
		//flagId==10  保存成功标志
		
		String ip = shareService.getIpAddr(request);
		//保存日志信息 saveOperationItemLog(String userIp, int tag, int action, int id)
		//tag：子模块标志位  0-我的实验项目  1-实验项目管理  2-实验项目导入  id：项目卡的id 0-查看 -1--新建
		//action:  0 新建 1 编辑 2 查看 3 删除 4 提交 5 审核查看 6 保存 7 审核编辑后保存 8 导入 9 审核结果
		int tag=1;
		if (toMyList==1) {
			tag=0;
		}
		systemLogService.saveOperationItemLog(ip, tag, 6, operationItem.getId());
		
		String docment=request.getParameter("docment");
		  if(docment!=null && docment!=""){
			 String[] str= docment.split(",");
			 for (String string : str) {
				 if(string!=null && string!=""){
					CommonDocument dd= commonDocumentDAO.findCommonDocumentByPrimaryKey(Integer.parseInt(string));
				     if(dd!=null){ 
					dd.setOperationItem(operationItem);
				      commonDocumentDAO.store(dd);
				 }
				 }
			}
			  
		  }
		return mav;
		
	}
	
	/**
	 * 保存实验项目--审核时
	 * @author 贺子龙
	 * 2015-12-14
	 */
	@RequestMapping("/saveOperationItemWhileAudit")
	public String saveOperationItemWhileAudit(HttpServletRequest request,@ModelAttribute OperationItem operationItem,@RequestParam int flag,int status){
		int operationItemId=operationItem.getId();
		OperationItem originalItem=operationService.findOperationItemByPrimaryKey(operationItemId);
		//保存页面上没有被编辑的字段，不然会被冲掉。
		operationItem.setCDictionaryByLpStatusCheck(originalItem.getCDictionaryByLpStatusCheck()); 
		operationItem.setUserByLpCreateUser(originalItem.getUserByLpCreateUser());
		operationItem.setCreatedAt(originalItem.getCreatedAt());
		operationItem.setUserByLpCheckUser(originalItem.getUserByLpCheckUser());
		operationItem.setLpCodeCustom(originalItem.getLpCodeCustom());
		operationItem.setLpCollege(originalItem.getLpCollege());
		operationItem.setLpPurposes(originalItem.getLpPurposes());
		operationItem.setLabCenter(originalItem.getLabCenter());
		operationItem = operationService.saveOperationItem(operationItem);
		
		String ip = shareService.getIpAddr(request);
		//保存日志信息 saveOperationItemLog(String userIp, int tag, int action, int id)
		//tag：子模块标志位  0-我的实验项目  1-实验项目管理  2-实验项目导入  id：项目卡的id 0-查看 -1--新建
		//action:  0 新建 1 编辑 2 查看 3 删除 4 提交 5 审核查看 6 保存 7 审核编辑后保存 8 导入 9 审核结果
		systemLogService.saveOperationItemLog(ip, 1, 7, operationItemId);
		return "redirect:/operation/viewOperationItem?operationItemId="+operationItemId+"&flag="+3+"&status="+status;//flag=3是防止保存后跳转的页面“返回”报错
		
	}
	
	/**
	 * 保存实验项目2-跳转页面
	 * @author 贺子龙
	 * 2015-09-23 19:40:46
	 */
	@RequestMapping("/saveOperationItemAll")
	public String saveOperationItemAll(@RequestParam int isMine){
		if (isMine==1) {
			return "redirect:/operation/listMyOperationItem?currpage=1&status=0"+"&orderBy=9";
		}else {
			return "redirect:/operation/listOperationItem?currpage=1&status=0"+"&orderBy=9";
		}
	}
	
	/**
	 * 删除实验项目
	 * @author hly
	 * 2015.07.29
	 */
	@RequestMapping("/deleteOperationItem")
	public String deleteOperationItem(HttpServletRequest request,@RequestParam int operationItemId, int isMine, int status){
		String ip = shareService.getIpAddr(request);
		//保存日志信息 saveOperationItemLog(String userIp, int tag, int action, int id)
		//tag：子模块标志位  0-我的实验项目  1-实验项目管理  2-实验项目导入  id：项目卡的id 0-查看 -1--新建
		//action:  0 新建 1 编辑 2 查看 3 删除 4 提交 5 审核查看 6 保存 7 审核编辑后保存 8 导入 9 审核结果
		int tag=1;
		if (isMine==1) {
			tag=0;
		}
		systemLogService.saveOperationItemLog(ip, tag, 3, operationItemId);
		operationService.deleteOperationItem(operationItemId);
		if (isMine==1) {
			return "redirect:/operation/listMyOperationItem?currpage=1&status="+status+"&orderBy=9";
		}else
		return "redirect:/operation/listOperationItem?currpage=1&status="+status+"&orderBy=9";
	}
	
	/**
	 * 提交实验项目，保存指定审核人
	 * @author hly
	 * 2015.08.06
	 */
	@RequestMapping("/submitOperationItem")
	public String submitOperationItem(HttpServletRequest request,@ModelAttribute OperationItem operationItem,int isMine){
		OperationItem oItem=new OperationItem();
		oItem=operationService.submitOperationItem(operationItem);
		//提交完成后向审核人发送消息
		Message message= new Message();
		Calendar date=Calendar.getInstance();
		message.setSendUser(shareService.getUserDetail().getCname());
		message.setSendCparty(shareService.getUserDetail().getSchoolAcademy().getAcademyName());
		message.setCond(0);
		message.setTitle(CommonConstantInterface.STR_OPERATIONITEM_TITLE);
		String content="申请成功，等待审核";
		content+="<a  href='/xidlims/operation/viewOperationItem?operationItemId=";
		content+=oItem.getId();
		content+="&&flag=1&status=2'>点击查看</a>";
//		message.setContent("申请成功，等待审核<a  href='/xidlims/operation/viewOperationItem?operationItemId=44956&&flag=1&status=2'>点击查看</a>");
		message.setContent(content);
		message.setMessageState(CommonConstantInterface.INT_Flag_ZERO);
		message.setCreateTime(date);
		message.setUsername(oItem.getUserByLpCheckUser().getUsername());
		message=messageDAO.store(message);
		String ip = shareService.getIpAddr(request);
		//保存日志信息 saveOperationItemLog(String userIp, int tag, int action, int id)
		//tag：子模块标志位  0-我的实验项目  1-实验项目管理  2-实验项目导入  id：项目卡的id 0-查看 -1--新建
		//action:  0 新建 1 编辑 2 查看 3 删除 4 提交 5 审核查看 6 保存 7 审核编辑后保存 8 导入 9 审核结果
		int tag=1;
		if (isMine==1) {
			tag=0;
		}
		systemLogService.saveOperationItemLog(ip, tag, 4, oItem.getId());
		if (isMine==0) {
			
			return "redirect:/operation/listOperationItem?currpage=1&status=2&orderBy=9";
		}else return "redirect:/operation/listMyOperationItem?currpage=1&status=2&orderBy=9";
	}
	
	/**
	 * 查看实验项目信息
	 * @author hly
	 * 2015.08.06
	 */
	@RequestMapping("/viewOperationItem")
	public ModelAndView viewOperationItem(@RequestParam int operationItemId,int flag,int status){
		ModelAndView mav = new ModelAndView();
		StringBuffer majorStr = new StringBuffer();
		OperationItem operationItem = operationService.findOperationItemByPrimaryKey(operationItemId);
		
		if(operationItem!=null&&operationItem.getLpMajorFit()!=null && !"".equals(operationItem.getLpMajorFit()))
		{
			String majorArr[] = operationItem.getLpMajorFit().split(",");
			
			for (String s : majorArr) 
			{
				if (systemService.findSystemMajor12ByNumber(s)!=null) {
					majorStr.append(systemService.findSystemMajor12ByNumber(s).getMName()+"["+s+"],");
				}
			}
			if(majorStr.length() > 0)
			{
				majorStr.deleteCharAt(majorStr.length()-1);  //去掉最后一个逗号
			}
		}
		mav.addObject("flag", flag);
		mav.addObject("status", status);
		
		mav.addObject("operationItem", operationItem);
		mav.addObject("currUser", shareService.getUserDetail());  //当前登录人
		mav.addObject("majorStr", majorStr);  //面向专业
		mav.addObject("toCheck", shareService.getCDictionaryByCategory("status_operation_item_check", "2"));  // 审核中
		mav.addObject("status", status); 
		//2015-09-24 10:10:45新增    仪器、材料
		mav.addObject("listItemDevice", operationService.getItemDeviceByItem(operationItemId, null, 1, -1));
		mav.addObject("listItemMaterialRecord", operationService.getItemMaterialRecordByItem(operationItemId));
		
		//审核人可以进行编辑
        mav.addObject("labProjectMain", shareService.getCDictionaryData("category_operation_item_main"));  //实验类别
        mav.addObject("labProjectApp", shareService.getCDictionaryData("category_operation_item_app"));  //实验类型
        mav.addObject("labProjectNature", shareService.getCDictionaryData("category_operation_item_nature"));  //实验性质
        mav.addObject("labProjectStudent", shareService.getCDictionaryData("category_operation_item_student"));  //实验者类型
        mav.addObject("labProjectChange", shareService.getCDictionaryData("status_operation_item_change"));  //变动状态
        mav.addObject("labProjectPublic", shareService.getCDictionaryData("category_operation_item_public"));  //开放实验
        mav.addObject("labProjectRewardLevel", shareService.getCDictionaryData("category_operation_item_reward_level"));  //获奖等级
        mav.addObject("labProjectRequire", shareService.getCDictionaryData("category_operation_item_require"));  //实验要求
        mav.addObject("labProjectGuideBook", shareService.getCDictionaryData("category_operation_item_guide_book"));  //实验指导书
        mav.addObject("schoolTerms", shareService.findAllSchoolTerm());  //学期
        mav.addObject("users", systemService.getAllUser(1, -1, shareService.getUser()));  //教师数据
        LabRoom labRoom = new LabRoom();
        mav.addObject("labRooms", labRoomService.findAllLabRoomByQuery(1, -1, labRoom,-1));  //实验室数据
        SchoolCourseInfo schoolCourseInfo = new SchoolCourseInfo();
        mav.addObject("schoolCourseInfos", schoolCourseInfoService.getCourseInfoByQuery(schoolCourseInfo, -1, 1, -1));  //课程数据
        mav.addObject("subjects", systemService.getAllSystemSubject12(1, -1));  //学科数据
        //面向专业
  		String majors=operationItem.getLpMajorFit();
  		String[] majorArray=majors.split(",");
  		List<SystemMajor12>  majorList=new ArrayList<SystemMajor12>();
  		List<SystemMajor12> allMajorList=systemService.getAllSystemMajor12(1, -1);
  		for (String string : majorArray) {
  			SystemMajor12 major=systemMajor12DAO.findSystemMajor12ByPrimaryKey(string);
  			if (major!=null) {
  				majorList.add(major);
  				allMajorList.remove(major);
  			}
  			
  		}
  		mav.addObject("majorList", majorList);	//已选专业
  		mav.addObject("majors", allMajorList);  //所有专业（排除已选）
        //添加材料
  		mav.addObject("operationItemMaterialRecord", new OperationItemMaterialRecord());
  		mav.addObject("categoryMaterialRecordMain", shareService.getCDictionaryData("category_operation_item_material_record_main"));
  		//添加设备
  		if(operationItem!=null && operationItem.getLabRoom()!=null)
		{
			List<LabRoomDevice> allLabRoomDevice = labRoomDeviceService.findLabRoomDeviceByRoomId(operationItem.getLabRoom().getId());

			List<LabRoomDevice> existLabRoomDevice = new ArrayList<LabRoomDevice>();
			
			for (OperationItemDevice operationItemDevice : operationItem.getOperationItemDevices()) 
			{
				existLabRoomDevice.add(operationItemDevice.getLabRoomDevice());
			}
			
			allLabRoomDevice.removeAll(existLabRoomDevice);  //去除已经添加的labRoomDevice
			mav.addObject("listLabRoomDevice", allLabRoomDevice);
		}
		mav.addObject("orderBy", 9);//仅传参用，防止我的消息--实验项目卡--审核--返回报404
		
		List<AssetCabinetWarehouseAccessRecord> assetRecords = assetCabinetWarehouseAccessRecordDAO.executeQuery("select a from AssetCabinetWarehouseAccessRecord" +
				" a where 1=1 and status = 1 and asset.category = 0 " +
				"and (assetCabinetWarehouseAccess.type = 0 " +
				"or assetCabinetWarehouseAccess.type != 0 and assetCabinetWarehouseAccess.operationItem.id="+operationItemId+")", 0, -1);
		mav.addObject("assetRecords", assetRecords);
		mav.setViewName("operation/viewOperationItem.jsp");
		return mav;
	}
	
	/**
	 * 审核实验项目
	 * @author hly
	 * 2015.08.06
	 */
	@RequestMapping("/checkOperationItem")
	public String checkOperationItem(HttpServletRequest request,@RequestParam int operationItemId, int result){
		OperationItem operationItem = operationService.findOperationItemByPrimaryKey(operationItemId);
		String ip = shareService.getIpAddr(request);
		//保存日志信息 saveOperationItemLog(String userIp, int tag, int action, int id)
		//tag：子模块标志位  0-我的实验项目  1-实验项目管理  2-实验项目导入  id：项目卡的id 0-查看 -1--新建
		//action:  0 新建 1 编辑 2 查看 3 删除 4 提交 5 审核查看 6 保存 7 审核编辑后保存 8 导入 9 审核结果
		systemLogService.saveOperationItemLog(ip, 1, 9, operationItemId);
		int direct=0;
		if(operationItem!=null)
		{
			//审核完成后给申请人发送消息
			Message message= new Message();
			Calendar date=Calendar.getInstance();
			message.setSendUser(shareService.getUserDetail().getCname());
			message.setSendCparty(shareService.getUserDetail().getSchoolAcademy().getAcademyName());
			message.setCond(0);
			String content="";
			if(result==0)  //审核拒绝
			{
				operationItem.setCDictionaryByLpStatusCheck(shareService.getCDictionaryByCategory("status_operation_item_check", "4"));
				message.setTitle(CommonConstantInterface.STR_FLAG_DISAGREE);
				content="您的审核未通过";
				direct=4;
			}
			else if(result==1)  //审核通过
			{
				operationItem.setCDictionaryByLpStatusCheck(shareService.getCDictionaryByCategory("status_operation_item_check", "3"));
				message.setTitle(CommonConstantInterface.STR_FLAG_AGREE);
				content="您的审核已通过";
				direct=3;
			}
			content+="<a  href='/xidlims/operation/listItemMaterialRecord?itemId=";
			
			content+=operationItemId;
			content+="&currpage=1&isMine=1&status=0&orderBy=9"+
					"'>点击查看</a>";
			message.setContent(content);
			message.setMessageState(CommonConstantInterface.INT_Flag_ZERO);
			message.setCreateTime(date);
			if (operationItem.getUserByLpCreateUser()!=null) {
				message.setUsername(operationItem.getUserByLpCreateUser().getUsername());//把消息发给项目卡的申请人
			}
			message=messageDAO.store(message);
			
			operationService.saveOperationItem(operationItem);
		}
		return "redirect:/operation/listOperationItem?currpage=1&&status="+direct+"&orderBy=9";
	}
	
	/**
	 * 设置项目编号
	 * @author hly
	 * 2015.08.07
	 */
	@RequestMapping("/saveCodeCustom")
	public String saveCodeCustom(@ModelAttribute OperationItem operationItem1, Integer status){
		operationService.saveCodeCustom(operationItem1);
		
		return "redirect:/operation/listOperationItem?currpage=1&status="+status+"&orderBy=9";
	}
	
	/**
	 * 导入整个学期的实验项目
	 * @author hly
	 * 2015.08.10
	 */
	@RequestMapping("/importTermOperationItem")
	public String importTermOperationItem(@RequestParam int sourceId, int targetId, int cid){
		
		if(sourceId != targetId && cid>0)
		{
			operationService.importTermOperationItem(sourceId, targetId, cid);
		}
		return "redirect:/operation/listMyOperationItem?currpage=1&status=1&orderBy=9";
	}
	
	/**
	 * 导入选中的实验项目
	 * @author hly
	 * 2015.08.10
	 */
	@RequestMapping("/importOperationItem")
	public String importOperationItem(HttpServletRequest request,@RequestParam int termId, String itemIds){
		operationService.importOperationItem(request,termId, itemIds);
		
		return "redirect:/operation/listMyOperationItem?currpage=1&status=0&orderBy=9";//导入后
	}
	
	/**
	 * 实验项目材料使用记录列表
	 * @author hly
	 * 2015.08.10
	 */
	@RequestMapping("/listItemMaterialRecord")
	public ModelAndView listItemMaterialRecord(@RequestParam int itemId, int currpage, int isMine,int status, int orderBy){
		ModelAndView mav = new ModelAndView();
		
		int pageSize = CommonConstantInterface.INT_PAGESIZE;
		int totalRecords = operationService.getItemMaterialRecordByItemCount(itemId);
		
		mav.addObject("pageModel", shareService.getPage(currpage, pageSize, totalRecords));
		mav.addObject("listItemMaterialRecord", operationService.getItemMaterialRecordByItem(itemId, currpage, pageSize));
		mav.addObject("operationItemMaterialRecord", new OperationItemMaterialRecord());
		
		OperationItem operationItem=operationService.findOperationItemByPrimaryKey(itemId);
		mav.addObject("operationItem", operationItem);
		String majors=operationItem.getLpMajorFit();
		String[] majorArray=majors.split(",");
		String majorStr="";
		
		for (String string : majorArray) {
			SystemMajor12 major=systemMajor12DAO.findSystemMajor12ByPrimaryKey(string);
			if (major!=null&&!major.getMName().equals("")) {
				majorStr+=major.getMName()+"，";
			}
			if (majorStr.length()>1) {
				majorStr=majorStr.substring(0,majorStr.length()-1);
			}
		}
		mav.addObject("majorStr", majorStr);
		
		mav.addObject("categoryMaterialRecordMain", shareService.getCDictionaryData("category_operation_item_material_record_main"));
		mav.addObject("isMine", isMine);
		mav.addObject("status", status);
		mav.addObject("orderBy", orderBy);
		mav.setViewName("operation/listItemMaterialRecord.jsp");
		return mav;
	}
	
	/**
	 * 保存实验项目材料使用记录
	 * @author hly
	 * 2015.08.10
	 */
	@RequestMapping("/saveItemMaterialRecord")
	public String saveItemMaterialRecord(@ModelAttribute OperationItemMaterialRecord operationItemMaterialRecord,@RequestParam int flag,int status,int id){
		operationService.saveItemMaterialRecord(operationItemMaterialRecord);
		
		return "redirect:/operation/viewOperationItem?operationItemId="+id+"&flag="+3+"&status="+status;//flag=3  保存完成以后，跳转页面不记录状态信息
	}
	
	/**
	 * 新建项目时保存实验项目材料使用记录
	 * @author hly
	 * 2015.08.10
	 */
	@RequestMapping("/saveItemMaterialRecordNew")
	public String saveItemMaterialRecordNew(@ModelAttribute OperationItemMaterialRecord operationItemMaterialRecord){
		operationService.saveItemMaterialRecord(operationItemMaterialRecord);
		return "redirect:/operation/editOperationItem?operationItemId="+operationItemMaterialRecord.getOperationItem().getId()+"&&isMine=1&flagId=1";
	}
	
	/**
	 * 删除实验项目材料使用记录--自己删除
	 * @author hly
	 * 2015.08.10
	 */
	@RequestMapping("/deleteItemMaterialRecord")
	public String deleteItemMaterialRecord(@RequestParam int mrId, int itemId){
		operationService.deleteItemMaterialRecord(mrId);
		return "redirect:/operation/editOperationItem?operationItemId="+itemId+"&&isMine=1&flagId=1";
	}
	
	
	/**
	 * 删除实验项目材料使用记录--审核人删除
	 * @author hly
	 * 2015.08.10
	 */
	@RequestMapping("/deleteItemMaterialRecordNew")
	public String deleteItemMaterialRecordNew(@RequestParam int mrId, int itemId,int flag,int status){
		operationService.deleteItemMaterialRecord(mrId);
		return "redirect:/operation/viewOperationItem?operationItemId="+itemId+"&flag="+3+"&status="+status;//flag=3  不记录页面状态
	}
	
	/**
	 * ajax获取材料使用记录信息
	 * @author hly
	 * 2015.08.10
	 */
	@RequestMapping(value="/ajaxGetMaterialRecord", produces="application/json;charset=UTF-8")
	public @ResponseBody String ajaxGetMaterialRecord(@RequestParam int mrId){
		OperationItemMaterialRecord mr = operationService.findItemMaterialRecordByPrimaryKey(mrId);
		StringBuffer result = new StringBuffer();
		result.append("{");
		result.append("\"lpmrId\":"+mr.getLpmrId()+",");
		result.append("\"lpmrName\":\""+mr.getLpmrName()+"\",");
		result.append("\"lpmrModel\":\""+mr.getLpmrModel()+"\",");
		result.append("\"lpmrCategory\":"+mr.getCDictionary().getId()+",");
		result.append("\"lpmrUnit\":\""+mr.getLpmrUnit()+"\",");
		result.append("\"lpmrQuantity\":\""+mr.getLpmrQuantity()+"\",");
		result.append("\"lpmrAmount\":\""+mr.getLpmrAmount()+"\",");
		result.append("\"lpmrRemark\":\""+mr.getLpmrRemark()+"\"");
		result.append("}");
		
		return result.toString();
	}
	
	/**
	 * 实验项目设备表
	 * @author hly
	 * 2015.08.19
	 */
	@RequestMapping("/listItemDevice")
	public ModelAndView listItemDevice(@RequestParam int itemId, int currpage, int isMine, int status, int orderBy){
		ModelAndView mav = new ModelAndView();
		
		int pageSize = CommonConstantInterface.INT_PAGESIZE;
		int totalRecords = operationService.getItemDeviceByItemCount(itemId, null);
		OperationItem operationItem = operationService.findOperationItemByPrimaryKey(itemId);
		
		mav.addObject("pageModel", shareService.getPage(currpage, pageSize, totalRecords));
		mav.addObject("operationItem", operationItem);
		mav.addObject("isMine", isMine);
		mav.addObject("listItemDevice", operationService.getItemDeviceByItem(itemId, null, 1, -1));
		if(operationItem!=null && operationItem.getLabRoom()!=null)
		{
			List<LabRoomDevice> allLabRoomDevice = labRoomDeviceService.findLabRoomDeviceByRoomId(operationItem.getLabRoom().getId());
//			System.out.println(operationItem.getLabRoom().getId()+"090909090909");
			List<LabRoomDevice> existLabRoomDevice = new ArrayList<LabRoomDevice>();
			
			for (OperationItemDevice operationItemDevice : operationItem.getOperationItemDevices()) 
			{
				existLabRoomDevice.add(operationItemDevice.getLabRoomDevice());
			}
			
			allLabRoomDevice.removeAll(existLabRoomDevice);  //去除已经添加的labRoomDevice
			mav.addObject("orderBy", orderBy);
			mav.addObject("listLabRoomDevice", allLabRoomDevice);
		}
		
		mav.addObject("status", status);
		mav.setViewName("operation/listItemDevice.jsp");
		return mav;
	}
	
	/**
	 * 保存实验项目设备
	 * @author hly
	 * 2015.08.19
	 */
	@RequestMapping("/saveItemDevice")
	public String saveItemDevice(@RequestParam int itemId, String category, String ids){
		
		operationService.saveItemDevice(itemId, category, ids);
		
//		return "redirect:/operation/listItemDevice?itemId="+itemId+"&currpage=1";
		return "redirect:/operation/editOperationItem?operationItemId="+itemId+"&&isMine=1&flagId=1";//2015-09-23 16:41:46   贺子龙  修改跳转页面
	}
	
	/**
	 * 保存实验项目设备
	 * @author hly
	 * 2015.08.19
	 */
	@RequestMapping("/saveItemDeviceNew")
	public String saveItemDeviceNew(@RequestParam int itemId, String category, String ids,int flag,int status){
		
		operationService.saveItemDevice(itemId, category, ids);
		return "redirect:/operation/viewOperationItem?operationItemId="+itemId+"&flag="+3+"&status="+status;//flag=3  跳转页面不记录状态信息
	}
	
	/**
	 * 删除实验项目设备--自己删除
	 * @author hly
	 * 2015.08.20
	 */
	@RequestMapping("/deleteItemDevice")
	public String deleteItemDevice(@RequestParam int itemDeviceId, int itemId){
		operationService.deleteItemDevice(itemDeviceId);
		
		return "redirect:/operation/editOperationItem?operationItemId="+itemId+"&&isMine=1&flagId=1";
	}
	
	/**
	 * 删除实验项目设备--审核人删除
	 * @author hly
	 * 2015.08.20
	 */
	@RequestMapping("/deleteItemDeviceNew")
	public String deleteItemDeviceNew(@RequestParam int itemDeviceId, int itemId, int flag,int status){
		operationService.deleteItemDevice(itemDeviceId);
		return "redirect:/operation/viewOperationItem?operationItemId="+itemId+"&flag="+3+"&status="+status;//flag=3  不记录状态信息
	}
	
	/**
	 * ajax获取指定学院、指定角色的用户数据
	 * @author hly
	 * 2015.08.28
	 */
	@RequestMapping("/ajaxGetUser")
	public @ResponseBody List<Map<String, String>> ajaxGetUser(@RequestParam String academyNumber, String role){
		List<Map<String, String>> result=operationService.getUserByAcademyRole(academyNumber, role, 18);
		if (result.size()>0) {
			return operationService.getUserByAcademyRole(academyNumber, role, 18);//18--系主任
		}else {
			return null;
		}
	}
	
	/**
	 * 批量删除实验项目
	 * @author 贺子龙
	 * 2015-11-30
	 */
	@RequestMapping("/batchDeleteOperationItem")
	public String batchDeleteOperationItem(@RequestParam int[] array, int status){
		for (int operationItemId : array) {
			operationService.deleteOperationItem(operationItemId);
		}
		return "redirect:/operation/listOperationItem?currpage=1&status="+status+"&orderBy=9";
	}
	/*********************************************************************************
	 * 功能： 实验大纲管理
	 * 作者：徐文
	 * 日期：2016-05-27
	 *********************************************************************************/
	@RequestMapping("/experimentalmanagement")
	public ModelAndView experimentalmanagement(HttpServletRequest request, @ModelAttribute OperationOutline operationOutline  ,@RequestParam int currpage,@ModelAttribute("selected_labCenter") Integer sid) {
		ModelAndView mav = new ModelAndView();
		int pageSize=30;
		int totalRecords=operationService.getOutlinelistpage(request,operationOutline,1,-1,sid).size();
		List<OperationOutline> Outlinelist=operationService.getOutlinelistpage(request,operationOutline,currpage,pageSize,sid);
		Map<String,Integer> pageModel = shareService.getPage(currpage, pageSize,totalRecords);
		
	    mav.addObject("newFlag", true);
	    mav.addObject("pageModel",pageModel);
		mav.addObject("totalRecords", totalRecords);
		mav.addObject("currpage", currpage);
		mav.addObject("pageSize", pageSize);
		mav.addObject("operationOutline", operationOutline);
		mav.addObject("user", shareService.getUser());
		 int yes=0;//yes等于0不是所选择实验室的主任 1是
		  for (LabCenter labCenter : shareService.getUser().getLabCentersForCenterManager()) {
			  if(labCenter.getId().equals(sid)){
			    yes=1;
			   }
		  }
		 mav.addObject("yes", yes);
		//查找所有的实验大纲
		mav.addObject("Outlinelist",Outlinelist );
		Integer dictionaryTermId = null;
		if(request.getParameter("searchterm")!=null&&!request.getParameter("searchterm").equals("")){
			dictionaryTermId = Integer.valueOf(request.getParameter("searchterm"));
		}
		CDictionary cDictionary = cDictionaryService.finCDictionaryByPrimarykey(dictionaryTermId);
		mav.addObject("cDictionary",cDictionary);
		/*int term = shareService.getBelongsSchoolTerm(Calendar.getInstance()).getId(); 
		mav.addObject("term", term);*/
		// 获取学期列表
		//List<SchoolTerm> schoolTerms = outerApplicationService.getSchoolTermList();
		List<CDictionary> schoolTerms = shareService.getCDictionaryData("c_student_status");
		mav.addObject("schoolTerm", schoolTerms);
		mav.setViewName("operation/experimentalmanagement.jsp");
		return mav;
	}
	/*********************************************************************************
	 * 功能:查看大纲内容
	 * 作者：徐文
	 * 日期：2016-05-27
	 ********************************************************************************/
	@RequestMapping("/checkout")
	public ModelAndView checkout(@RequestParam int idkey,@ModelAttribute("selected_labCenter") Integer sid) {
		ModelAndView mav = new ModelAndView();
        mav.addObject("infor", operationService.getoperationoutlineinfor(idkey));  
		mav.setViewName("operation/checout.jsp");
		return mav;
	}	
	/*********************************************************************************
	 * 功能:新建大纲内容
	 * 作者：徐文
	 * 日期：2016-05-30
	 ********************************************************************************/
	@RequestMapping("/newoperationproject")
	public ModelAndView newoperationproject(@ModelAttribute("selected_labCenter") Integer sid) {
		ModelAndView mav = new ModelAndView();
		OperationOutline OperationOutline= new OperationOutline();
		OperationOutline.setSchoolAcademy(shareService.getUser().getSchoolAcademy());
		mav.addObject("operationOutline",OperationOutline);
		Set<SystemMajor12> majorsEdit = OperationOutline.getSystemMajors();
		mav.addObject("majorsEdit",majorsEdit);
		Set<CDictionary> property = OperationOutline.getCDictionarys();
		mav.addObject("property",property);
		//查找登录人所在学院课程
		mav.addObject("schoolCourseInfoMap", operationService.getschoolcouresMap(sid));
		//获取课程先修课程
		mav.addObject("firstCourses",OperationOutline.getSchoolCourseInfoes());
		//查找登录人所在的学院专业
		mav.addObject("schoolmajer", operationService.getschoolmajerSet(sid));
		//查找学分
		mav.addObject("operationscareMap", operationService.getcoperationscareMap());
		//查找登录人所在学院的开课学院
		mav.addObject("operationstartschooleMap", operationService.getoperationstartschooleMap(sid));
		//获取开课学院
		mav.addObject("academies",OperationOutline.getSchoolAcademies());
		//查找开课性质
		mav.addObject("commencementnaturemap", operationService.getcommencementnatureSet());
		//获取项目卡
		mav.addObject("operationItem", operationService.getoperationItemlist()); 
		//获取课程类型
		mav.addObject("coursetypemap",operationService.getCourseType());
		mav.addObject("isNew", 1);
		//所有学期
		mav.addObject("cDictionaryTerms",shareService.getCDictionaryData("c_student_status"));
		//获取开课学期
		mav.addObject("studyStages",OperationOutline.getStudyStages());
		mav.setViewName("operation/newoperationproject.jsp");
		return mav;
	}
	/*********************************************************************************
	 * 功能:保存item
	 * 作者：徐文
	 * 日期：2016-05-31
	 ********************************************************************************/
	@RequestMapping("/getuserprojectitems")
	public @ResponseBody String  getuserprojectitems(@RequestParam String projectitems){
		//System.out.println(projectitems);
		String stsr="";
		Integer ss=0;
		Integer aa=0;
		   if(projectitems.length()>0){
			      String[] str=projectitems.split(",");
			   
			   for (String string : str) { 
				   if(string!=null && string!=""){
				OperationItem s=  operationItemDAO.findOperationItemById(Integer.parseInt(string));
				if(s!=null){
				  String cop=null;
					if(s.getCDictionaryByLpCategoryMain()==null){
						cop="无";
				}else{
					cop=s.getCDictionaryByLpCategoryMain().getCName();
				}
					String sa=null;
					if(s.getLabRooms()!=null){
						for (LabRoom sts : s.getLabRooms()) {
							sa+=sts.getLabRoomName()+",";
						}
					
					}else{
					sa="无";
					}
					aa=s.getLpDepartmentHours();
					stsr+="<tr id='"+s.getId()+"' ><td >"+s.getLpCodeCustom()+"</td><td >"+s.getLpName()+"</td><td >"+cop+"</td><td >"+aa+"</td><td >"+ss+"</td><td >"+sa+"</td><td ><input class='savebutton'    id='"+s.getId()+"'  onclick='del(this.id)'   type='button' value='删除' /></td></tr>"; 
			}}}
		   }
		
		return shareService.htmlEncode(stsr);
	}
	/*********************************************************************************
	 * 功能:保存大纲内容
	 * 作者：徐文
	 * 日期：2016-05-31
	 ********************************************************************************/
	@RequestMapping("/saveoperationoutline")
	public String  saveoperationoutline(@ModelAttribute OperationOutline operationOutline ,HttpServletRequest request,@ModelAttribute("selected_labCenter") Integer sid) {
		if(sid != -1)
		{
			operationOutline.setLabCenter(labCenterService.findLabCenterByPrimaryKey(sid));
		}
		
//		System.out.println(projectitrms.length);
		
		//String commencementnaturemap=request.getParameter("commencementnaturemap");
		//String projectitrms=request.getParameter("projectitrms");
		String docment=request.getParameter("docment");
		 OperationOutline op=	operationService.saveoperationoutline(operationOutline,request);
		  if(docment!=null && docment!=""){
			 String[] str= docment.split(",");
			 for (String string : str) {
				 if(string!=null && string!=""){
					CommonDocument dd= commonDocumentDAO.findCommonDocumentByPrimaryKey(Integer.parseInt(string));
				     if(dd!=null){ 
					dd.setOperationOutline(op);
				      commonDocumentDAO.store(dd);
				 }
				 }
			}
			  
		  }
		return "redirect:/operation/experimentalmanagement?currpage=1";
	}
	
	/*********************************************************************************
	 * 功能:多文件上传
	 * 作者：徐文
	 * 日期：2016-06-01
	 ********************************************************************************/
	@RequestMapping("/uploaddnolinedocment")
	public @ResponseBody String uploaddnolinedocment(HttpServletRequest request, HttpServletResponse response, BindException errors,Integer id) throws Exception {
		String ss=operationService.uploaddnolinedocment(request, response,id);
		return shareService.htmlEncode(ss);
	}
	/****************************************************************************
	 * 功能：删除大纲文件
	 * 作者：徐文
	 * 日期：2016-06-01
	 ****************************************************************************/
	@RequestMapping("/delectdnolinedocment")
	public @ResponseBody String delectdnolinedocment(@RequestParam Integer idkey) throws Exception {
		CommonDocument d=commonDocumentDAO.findCommonDocumentById(idkey);
		commonDocumentDAO.remove(d);
		return "ok";
	}
	/****************************************************************************
	 * 功能：下载大纲文件
	 * 作者：徐文
	 * 日期：2016-06-01
	 ****************************************************************************/
	@RequestMapping("/downloadfile ")
	public void donloudfujian(HttpServletRequest request, HttpServletResponse response,@RequestParam int idkey) throws Exception {
		CommonDocument d=commonDocumentDAO.findCommonDocumentById(idkey);
		String filename = d.getDocumentName();
		
		String path=d.getDocumentUrl();
		try{			
		File sendPath = new File(path);
        FileInputStream fis = new FileInputStream(sendPath);
		response.setCharacterEncoding("utf-8");
		//解决上传中文文件时不能下载的问题
		response.setContentType("multipart/form-data;charset=UTF-8");
		if (request.getHeader("User-Agent").toLowerCase().indexOf("firefox") > 0) {
			filename = new String(filename.getBytes("UTF-8"),"ISO8859-1");// firefox浏览器
		} else if (request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0) {
			filename = URLEncoder.encode(filename, "UTF-8");// IE浏览器
		} else {
			filename = URLEncoder.encode(filename, "UTF-8");
		}
		response.setHeader("Content-Disposition", "attachment;fileName="+filename);		
        OutputStream fos = response.getOutputStream();
	    byte[] buffer = new byte[8192];
	    int count = 0;
	    while((count = fis.read(buffer))>0){
	    	fos.write(buffer,0,count);   
	    }
	    fis.close();
	    fos.close();
	}catch(Exception e){
		   e.printStackTrace();
		}
	}
	
	/***********************************************************************************
	 * @功能：实验大纲管理-导入实验大纲
	 * @author 罗璇
	 * @日期：2017年3月5日
	 * **********************************************************************************/
	@RequestMapping("/importOperationOutlines")
	public ModelAndView importAsset(HttpServletRequest request,@ModelAttribute("selected_labCenter") Integer sid){
		ModelAndView mav = new ModelAndView();
		String fileName = shareService.getUpdateFilePath(request);
		String logoRealPathDir = request.getSession().getServletContext().getRealPath("/");
		String filePath = logoRealPathDir + fileName;
		System.out.println(filePath);
		if(filePath.endsWith("xls")||filePath.endsWith("xlsx")){
			operationService.importOperationOutlines(filePath,sid);
		}
		mav.setViewName("redirect:/operation/experimentalmanagement?currpage=1");
		return mav;
	}
	
	/****************************************************************************
	 * 功能：搜索项目卡
	 * 作者：徐文
	 * 日期：2016-06-01
	 ****************************************************************************/
	@SuppressWarnings("unused")
	@RequestMapping("/getitem")
	public @ResponseBody String getitem(String nameop) {
	
		List<OperationItem> d= operationService.getitem(nameop);
		String str="";
		if(d.size()>0){
		  for (OperationItem itm : d) {
				str+="<tr align='center'><td align='center'><input type='checkbox' value='"+itm.getId()+"'></td><td align='center'>"+itm.getLpCodeCustom()+"</td><td align='center'>"+itm.getLpName()+"</td><td align='center'></td></tr>";
		  }
		}
    return shareService.htmlEncode(str);
	}
	/****************************************************************************
	 * 功能：导出实验室大纲
	 * 作者：徐文
	 * 日期：2016-06-01
	 ****************************************************************************/
	@SuppressWarnings({ "rawtypes", "unused", "unchecked" })
	@RequestMapping("/outline/exportOutline")
	public void export(@ModelAttribute OperationOutline operationOutline ,HttpServletRequest request, HttpServletResponse response,@RequestParam int page,@ModelAttribute("selected_labCenter") Integer sid) throws Exception {
		
		int pageSize=30;
		
		List<OperationOutline> findList=operationService.getOutlinelistpage(request,operationOutline,page,pageSize,sid);
		List<Map> list=new ArrayList<Map>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		for (OperationOutline f : findList) {
			Map map=new HashMap();
			map.put("1", f.getLabOutlineName());//dagangmingcheng
			if(f.getUser().getCname() != null&&f.getUser().getCname() != null ){
			map.put("2", f.getUser().getCname());//jiaoshi
			}
			if(f.getSchoolCourseInfoByClassId()!=null&& f.getSchoolCourseInfoByClassId().getCourseNumber()!=null){
			map.put("3",   f.getSchoolCourseInfoByClassId().getCourseNumber());//kechengbiaohao
			}
			if(f.getSchoolCourseInfoByClassId()!=null && f.getSchoolCourseInfoByClassId().getCourseName()!=null){
			map.put("4",  f.getSchoolCourseInfoByClassId().getCourseName());//kechengmingcheng
			}
			if(f.getSchoolAcademy()!=null&&f.getSchoolAcademy().getAcademyName()!=null){
			map.put("5", f.getSchoolAcademy().getAcademyName());//xueyuan
			}
			list.add(map);
		 }
		 String title = "实验大纲列表";
	        String[] hearders = new String[] {"大纲名称","教师", "课程编号","课程名称", "学院"};//表头数组
	        String[] fields = new String[] {"1", "2","3","4","5"};//Financialresources对象属性数组
	        TableData td = ExcelUtils.createTableData(list, ExcelUtils.createTableHeader(hearders),fields);
	        JsGridReportBase report = new JsGridReportBase(request, response);
	        report.exportToExcel(title,shareService.getUser().getCname(), td);
		
	}
	/****************************************************************************
	 * 功能：删除实验室大纲
	 * 作者：徐文
	 * 日期：2016-06-01
	 ****************************************************************************/
	@RequestMapping("/delectuotline")
	public String  delectuotline(@RequestParam int idkey) {
		operationService.delectloutline(idkey);
		return "redirect:/operation/experimentalmanagement?currpage=1";
	}
	/****************************************************************************
	 * 功能：编辑实验室大纲
	 * 作者：徐文
	 * 日期：2016-06-01
	 ****************************************************************************/
	@RequestMapping("/editoutline")
	public ModelAndView newoperationproject(@RequestParam int idkey,@ModelAttribute("selected_labCenter") Integer sid) {
		/**
		 * 
		 */
		ModelAndView mav = new ModelAndView();
		// 查找被编辑的大纲对象
		OperationOutline operationOutline=operationService.getoperationoutlineinfor(idkey);
		mav.addObject("operationOutline",operationOutline);
		// 被编辑对象的多选框内容
		Set<SystemMajor12> majorsEdit = operationOutline.getSystemMajors();
		mav.addObject("majorsEdit",majorsEdit);
		//获取实验大纲课程性质
		Set<CDictionary> property = operationOutline.getCDictionarys();
		mav.addObject("property",property);
		//获取实验大纲的开课学期
		Set<CDictionary> studyStages = operationOutline.getStudyStages();
		mav.addObject("studyStages",studyStages);
		Set<OperationItem> item = operationOutline.getOperationItems();
		mav.addObject("item",item);
		//获取所有课程类型
		mav.addObject("coursetypemap",operationService.getCourseType());
		//获取实验大纲课程类型
		mav.addObject("courseType",operationOutline.getCourseDescription());
		//所有学期
		mav.addObject("cDictionaryTerms",shareService.getCDictionaryData("c_student_status"));		
		mav.addObject("firstCourses",operationOutline.getSchoolCourseInfoes());
		//...
		//查找登录人所在学院课程
		mav.addObject("schoolCourseInfoMap", operationService.getschoolcouresMap(sid));
		//查找登录人所在的学院专业
		mav.addObject("schoolmajer", operationService.getschoolmajerSet(sid));
		//查找所有学分
		mav.addObject("operationscareMap", operationService.getcoperationscareMap());
		//获取大纲学分
		mav.addObject("outlineCredit",operationOutline.getCOperationOutlineCredit());
		//查找登录人所在学院的开课学院
		mav.addObject("operationstartschooleMap", operationService.getoperationstartschooleMap(sid));
		//获取开课学院
		mav.addObject("academies",operationOutline.getSchoolAcademies());
		//查找开课性质
		mav.addObject("commencementnaturemap", operationService.getcommencementnatureSet());
		//获取项目卡
		mav.addObject("operationItem", operationService.getoperationItemlist());
		mav.addObject("isNew", 0);
		mav.setViewName("operation/newoperationproject.jsp");
		return mav;
	}
	
	/**
	 * 新建项目时保存实验项目材料使用记录
	 * @author hly
	 * 2015.08.10
	 */
	@RequestMapping("/saveItemMaterialRecordNewAfterAudit")
	public String saveItemMaterialRecordNewAfterAudit(@ModelAttribute OperationItemMaterialRecord operationItemMaterialRecord,@RequestParam String lp_name,@RequestParam int term_id,@RequestParam String course_number,
			@RequestParam String lp_create_user,@RequestParam int page,@RequestParam int isMine,@RequestParam int status,
			@RequestParam int orderBy,@RequestParam int id){
		operationService.saveItemMaterialRecord(operationItemMaterialRecord);
		return "redirect:/operationRest/listItemMaterialRecordRest/" + lp_name + "/"+ term_id + "/" + course_number + "/" + lp_create_user + "/"+page+ "/1/"+status+"/"+orderBy+"/" +id+"/";
	}
	
	/*********************************************************************************
	 * @description:实验项目多文件上传
	 * @author：郑昕茹
	 * @date：2016-11-09
	 ********************************************************************************/
	@RequestMapping("/uploadItemdocument")
	public @ResponseBody String uploadItemdocument(HttpServletRequest request, HttpServletResponse response, BindException errors,Integer id) throws Exception {
		String ss=operationService.uploadItemdocument(request, response, id);
		return shareService.htmlEncode(ss);
	}
	
	/**
	 * 保存实验项目(单独为西电使用)
	 * @author 张凯
	 * 2017.03.14
	 */
	@RequestMapping("/saveOperationItemforxidlims")
	public ModelAndView saveOperationItemforxidlims(HttpServletRequest request,@ModelAttribute OperationItem operationItem,@RequestParam int toMyList,@ModelAttribute("selected_labCenter") Integer cid){
		ModelAndView mav=new ModelAndView();
		operationItem.setCDictionaryByLpStatusCheck(shareService.getCDictionaryByCategory("status_operation_item_check", "3"));  //无需审核，全部审核通过
		operationItem.setUserByLpCreateUser(shareService.getUserDetail());  //当前登录人
		operationItem.setCreatedAt(Calendar.getInstance());//设置为当前时间
		//保存当前所处的实验类型
		if(cid != -1)
		{
			operationItem.setLabCenter(labCenterService.findLabCenterByPrimaryKey(cid));
		}
		if(operationItem.getOperationOutline()!=null&&operationItem.getOperationOutline().getId()!=null){
			operationItem.setOperationOutline(operationOutlineDAO.findOperationOutlineById(operationItem.getOperationOutline().getId()));
		}
		//获取实验大纲的课程id并保存到实验项目里
		SchoolCourseInfo schoolCourseInfo = operationItem.getOperationOutline().getSchoolCourseInfoByClassId();
		operationItem.setSchoolCourseInfo(schoolCourseInfo);
		operationItem = operationService.saveOperationItem(operationItem);
		mav.setViewName("redirect:/operation/listMyOperationItem?currpage=1&status=0&orderBy=9");
		//flagId==10  保存成功标志
		
		String ip = shareService.getIpAddr(request);
		//保存日志信息 saveOperationItemLog(String userIp, int tag, int action, int id)
		//tag：子模块标志位  0-我的实验项目  1-实验项目管理  2-实验项目导入  id：项目卡的id 0-查看 -1--新建
		//action:  0 新建 1 编辑 2 查看 3 删除 4 提交 5 审核查看 6 保存 7 审核编辑后保存 8 导入 9 审核结果
		int tag=1;
		if (toMyList==1) {
			tag=0;
		}
		systemLogService.saveOperationItemLog(ip, tag, 6, operationItem.getId());
		
		return mav;
		
	}
	
}
	
	
