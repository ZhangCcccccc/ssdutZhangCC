package net.xidlims.web.operation;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.xidlims.constant.CommonConstantInterface;
import net.xidlims.dao.AssetCabinetWarehouseAccessRecordDAO;
import net.xidlims.dao.MessageDAO;
import net.xidlims.dao.OperationItemDAO;
import net.xidlims.dao.SystemMajor12DAO;
import net.xidlims.domain.AssetCabinetWarehouseAccessRecord;
import net.xidlims.domain.LabRoom;
import net.xidlims.domain.LabRoomDevice;
import net.xidlims.domain.Message;
import net.xidlims.domain.OperationItem;
import net.xidlims.domain.OperationItemDevice;
import net.xidlims.domain.OperationItemMaterialRecord;
import net.xidlims.domain.SchoolCourseInfo;
import net.xidlims.domain.SchoolTerm;
import net.xidlims.domain.SystemMajor12;
import net.xidlims.domain.User;
import net.xidlims.service.common.ShareService;
import net.xidlims.service.device.LabRoomDeviceService;
import net.xidlims.service.lab.LabCenterService;
import net.xidlims.service.lab.LabRoomService;
import net.xidlims.service.operation.OperationService;
import net.xidlims.service.system.SystemLogService;
import net.xidlims.service.system.SystemService;
import net.xidlims.service.timetable.SchoolCourseInfoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller("OperationControllerRest")
@SessionAttributes("selected_labCenter")
public class OperationControllerRest<JsonResult> {
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
	private SchoolCourseInfoService schoolCourseInfoService;
	@Autowired
	private LabRoomDeviceService labRoomDeviceService;
	@Autowired
	private MessageDAO messageDAO;
	@Autowired
	private SystemMajor12DAO systemMajor12DAO;
	@Autowired
	private OperationItemDAO operationItemDAO;
	@Autowired
	private SystemLogService systemLogService;
	@Autowired
	private
	AssetCabinetWarehouseAccessRecordDAO assetCabinetWarehouseAccessRecordDAO;
	/**
	 * 查看实验项目信息
	 * @author hly
	 * 2015.08.06
	 */
	@RequestMapping(value = "/operationRest/viewOperationItemRest/{lp_name}/{term_id}/{course_number}/{lp_create_user}/{page}/{flag}/{status}/{id}", method = RequestMethod.GET)
	public ModelAndView viewOperationItemRest(HttpServletRequest request,@PathVariable String lp_name,@PathVariable int term_id,@PathVariable String course_number,
			@PathVariable String lp_create_user,@PathVariable int page,@PathVariable int flag,@PathVariable int status,
			@PathVariable int id,Model model){
		ModelAndView mav = new ModelAndView();
		StringBuffer majorStr = new StringBuffer();
		OperationItem operationItem = operationService.findOperationItemByPrimaryKey(id);
		
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
		mav.addObject("listItemDevice", operationService.getItemDeviceByItem(id, null, 1, -1));
		mav.addObject("listItemMaterialRecord", operationService.getItemMaterialRecordByItem(id));
		
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
		mav.setViewName("operation/viewOperationItem.jsp");
		
		//前台状态传参
		mav.addObject("lp_name", lp_name);
		mav.addObject("term_id", term_id);
		mav.addObject("course_number", course_number);
		mav.addObject("lp_create_user", lp_create_user);
		mav.addObject("page", page);
		String ip = shareService.getIpAddr(request);
		//保存日志信息 saveOperationItemLog(String userIp, int tag, int action, int id)
		//tag：子模块标志位  0-我的实验项目  1-实验项目管理  2-实验项目导入  id：项目卡的id 0-查看 -1--新建
		//action:  0 新建 1 编辑 2 查看 3 删除 4 提交 5 审核查看 6 保存 7 审核编辑后保存 8 导入 9 审核结果
		systemLogService.saveOperationItemLog(ip, flag, 5, id);
		
		//找到该项目下的所有入库的物资
				List<AssetCabinetWarehouseAccessRecord> assetRecords = assetCabinetWarehouseAccessRecordDAO.executeQuery("select a from AssetCabinetWarehouseAccessRecord" +
						" a where 1=1 and status = 1 and asset.category = 0 " +
						"and (assetCabinetWarehouseAccess.type = 0 " +
						"or assetCabinetWarehouseAccess.type != 0 and assetCabinetWarehouseAccess.operationItem.id="+id+")", 0, -1);
				mav.addObject("assetRecords", assetRecords);
		return mav;
	}
	
	
	/**
	 * 实验项目列表
	 * @author hly
	 * 2015.07.29
	 * 贺子龙修改于2015-09-02
	 */
	@RequestMapping(value = "/operationRest/listOperationItemRest/{lp_name}/{term_id}/{course_number}/{lp_create_user}/{page}/{flag}/{status}/{orderBy}", method = RequestMethod.GET)
	public ModelAndView listOperationItemRest(@PathVariable String lp_name,@PathVariable int term_id,@PathVariable String course_number,
			@PathVariable String lp_create_user,@PathVariable int page,@PathVariable int flag,@PathVariable int status,
			@PathVariable int orderBy,Model model, @ModelAttribute OperationItem operationItem, @ModelAttribute("selected_labCenter") Integer cid){
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
		//将状态赋值给operationItem
		//实验项目名称
		if(!"-1".equals(lp_name)){
			operationItem.setLpName(lp_name);
		}
		//学期
		SchoolTerm selectTerm=new SchoolTerm();
		if(!"-1".equals(term_id)){
			selectTerm.setId(term_id);
			operationItem.setSchoolTerm(selectTerm);
		}
		//所属课程
		SchoolCourseInfo selectCourse=new SchoolCourseInfo();
		if (!"-1".equals(course_number)) {
			selectCourse.setCourseNumber(course_number);
			operationItem.setSchoolCourseInfo(selectCourse);
		}
		//创建人
		User selectUser=new User();
		if (!"-1".equals(lp_create_user)) {
			selectUser.setUsername(lp_create_user);
			operationItem.setUserByLpCreateUser(selectUser);
		}
		
		if(cid!=null)
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
		mav.addObject("listOperationItem", operationService.findAllOperationItemExceptDraft(page, pageSize, operationItem, cid, orderBy));
		mav.addObject("pageModel", shareService.getPage(page, pageSize, totalRecords));
		mav.addObject("status", status);
		mav.addObject("orderBy", orderBy);
		mav.addObject("users",operationService.getsome());
		mav.addObject("schoolTerms", shareService.findAllSchoolTerm());  //所有学期
		mav.addObject("schoolCourseInfos",operationService.getCourse(cid));
		mav.addObject("currUser", currUser);  //当前登录用户
		mav.addObject("operationItem1", new OperationItem());  //用于设置项目编号
		mav.addObject("draft", shareService.getCDictionaryByCategory("status_operation_item_check", "1"));  // 草稿
		mav.addObject("toCheck", shareService.getCDictionaryByCategory("status_operation_item_check", "2"));  // 审核中
		mav.addObject("checkYes", shareService.getCDictionaryByCategory("status_operation_item_check", "3"));  // 审核通过
		mav.addObject("checkNo", shareService.getCDictionaryByCategory("status_operation_item_check", "4"));  //审核拒绝
		//获取当前学期
		SchoolTerm schoolTerm = shareService.getBelongsSchoolTerm(Calendar.getInstance());
		mav.addObject("schoolTerm", schoolTerm);
		//决定升序还是降序
		boolean asc=true;
		if (orderBy<10) {
			asc=false;
		}
		mav.addObject("asc", asc);
		
		mav.addObject("lp_name", lp_name);
		mav.addObject("term_id", term_id);
		mav.addObject("course_number", course_number);
		mav.addObject("lp_create_user", lp_create_user);
		mav.addObject("page", page);
		mav.setViewName("operation/listOperationItem.jsp");
		return mav;
	}
	
	/**
	 * 实验项目材料使用记录列表
	 * @author hly
	 * 2015.08.10
	 */
	@RequestMapping(value = "/operationRest/listItemMaterialRecordRest/{lp_name}/{term_id}/{lp_create_user}/{page}/{isMine}/{status}/{orderBy}/{id}", method = RequestMethod.GET)
	public ModelAndView listItemMaterialRecordRest(HttpServletRequest request,@PathVariable String lp_name,@PathVariable int term_id,
			@PathVariable String lp_create_user,@PathVariable int page,@PathVariable int isMine,@PathVariable int status,
			@PathVariable int orderBy,@PathVariable int id,Model model){
		ModelAndView mav = new ModelAndView();
		
		
		mav.addObject("listItemMaterialRecord", operationService.getItemMaterialRecordByItem(id, 1, -1));
		mav.addObject("operationItemMaterialRecord", new OperationItemMaterialRecord());
		
		OperationItem operationItem=operationService.findOperationItemByPrimaryKey(id);
		mav.addObject("operationItem", operationItem);
		/*String majors=operationItem.getLpMajorFit();
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
		mav.addObject("majorStr", majorStr);*/
		
		mav.addObject("categoryMaterialRecordMain", shareService.getCDictionaryData("category_operation_item_material_record_main"));
		mav.addObject("isMine", isMine);
		mav.addObject("status", status);
		mav.addObject("orderBy", orderBy);
		mav.setViewName("operation/listItemMaterialRecord.jsp");
		
		mav.addObject("lp_name", lp_name);
		mav.addObject("term_id", term_id);
		//mav.addObject("course_number", course_number);
		mav.addObject("lp_create_user", lp_create_user);
		mav.addObject("page", page);
		mav.addObject("id", id);
		String ip = shareService.getIpAddr(request);
		int tag=1;
		if (isMine==1) {
			tag=0;
		}
		
		//找到该项目下的所有入库的物资
		List<AssetCabinetWarehouseAccessRecord> assetRecords = assetCabinetWarehouseAccessRecordDAO.executeQuery("select a from AssetCabinetWarehouseAccessRecord" +
				" a where 1=1 and status = 1 and asset.category = 0 " +
				"and (assetCabinetWarehouseAccess.type = 0 " +
				"or assetCabinetWarehouseAccess.type != 0 and assetCabinetWarehouseAccess.operationItem.id="+id+")", 0, -1);
		mav.addObject("assetRecords", assetRecords);
		//保存日志信息 saveOperationItemLog(String userIp, int tag, int action, int id)
		//tag：子模块标志位  0-我的实验项目  1-实验项目管理  2-实验项目导入  id：项目卡的id 0-查看 -1--新建
		//action: 0 新建 1 编辑 2 查看 3 删除 4 提交 5 审核 6 保存 7 审核保存 8 导入
		systemLogService.saveOperationItemLog(ip, tag, 2, id);
		return mav;
	}
	
	/**
	 * 实验项目设备表
	 * @author hly
	 * 2015.08.19
	 */
	@RequestMapping(value = "/operationRest/listItemDeviceRest/{lp_name}/{term_id}/{course_number}/{lp_create_user}/{page}/{isMine}/{status}/{orderBy}/{id}", method = RequestMethod.GET)
	public ModelAndView listItemDeviceRest(@PathVariable String lp_name,@PathVariable int term_id,@PathVariable String course_number,
			@PathVariable String lp_create_user,@PathVariable int page,@PathVariable int isMine,@PathVariable int status,
			@PathVariable int orderBy,@PathVariable int id,Model model){
		ModelAndView mav = new ModelAndView();
		
		OperationItem operationItem = operationService.findOperationItemByPrimaryKey(id);
		
		mav.addObject("operationItem", operationItem);
		mav.addObject("isMine", isMine);
		mav.addObject("listItemDevice", operationService.getItemDeviceByItem(id, null, 1, -1));
		if(operationItem!=null && operationItem.getLabRoom()!=null)
		{
			List<LabRoomDevice> allLabRoomDevice = labRoomDeviceService.findLabRoomDeviceByRoomId(operationItem.getLabRoom().getId());
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
		
		mav.addObject("lp_name", lp_name);
		mav.addObject("term_id", term_id);
		mav.addObject("course_number", course_number);
		mav.addObject("lp_create_user", lp_create_user);
		mav.addObject("page", page);
		return mav;
	}
	
	
	/**
	 * 删除实验项目
	 * @author hly
	 * 2015.07.29
	 */
	@RequestMapping(value = "/operationRest/deleteOperationItemRest/{lp_name}/{term_id}/{course_number}/{lp_create_user}/{page}/{isMine}/{status}/{orderBy}/{id}", method = RequestMethod.GET)
	public ModelAndView deleteOperationItemRest(HttpServletRequest request,@PathVariable String lp_name,@PathVariable int term_id,@PathVariable String course_number,
			@PathVariable String lp_create_user,@PathVariable int page,@PathVariable int isMine,@PathVariable int status,
			@PathVariable int orderBy,@PathVariable int id,Model model, @ModelAttribute OperationItem operationItem, @ModelAttribute("selected_labCenter") Integer cid){
		ModelAndView mav = new ModelAndView();	
		String ip = shareService.getIpAddr(request);
		//保存日志信息 saveOperationItemLog(String userIp, int tag, int action, int id)
		//tag：子模块标志位  0-我的实验项目  1-实验项目管理  2-实验项目导入  id：项目卡的id 0-查看 -1--新建
		//action:  0 新建 1 编辑 2 查看 3 删除 4 提交 5 审核查看 6 保存 7 审核编辑后保存 8 导入 9 审核结果
		int tag=1;
		if (isMine==1) {
			tag=0;
		}
		systemLogService.saveOperationItemLog(ip, tag, 3, id);
		operationService.deleteOperationItem(id);
		if (isMine==1) {
			//System.out.println(lp_name+"[[[[");
			//TODO  lp_name 暂时写成-1  因为总是被转换成%20
			mav.setViewName("redirect:/operationRest/listMyOperationItemRest/" + "-1"
					+ "/"+ term_id + "/" + course_number + "/" + lp_create_user
					+ "/"+ page +"/0"+ "/"+status+ "/"+orderBy);
		}else
			mav.setViewName("redirect:/operationRest/listOperationItemRest/" + "-1"
					+ "/"+ term_id + "/" + course_number + "/" + lp_create_user
					+ "/"+ page +"/1"+ "/"+status+ "/"+orderBy);
		//page 后面的1 or 0--flag
		return mav;
	}
	
	
	/**
	 * 教师个人的实验项目列表
	 * @author hly
	 * 2015.08.07
	 */
		
	@RequestMapping(value = "/operationRest/listMyOperationItemRest/{lp_name}/{term_id}/{course_number}/{lp_create_user}/{page}/{flag}/{status}/{orderBy}", method = RequestMethod.GET)
	public ModelAndView listMyOperationItemRest(@PathVariable String lp_name,@PathVariable int term_id,@PathVariable String course_number,
			@PathVariable String lp_create_user,@PathVariable int page,@PathVariable int flag,@PathVariable int status,
			@PathVariable int orderBy,Model model, @ModelAttribute OperationItem operationItem, @ModelAttribute("selected_labCenter") Integer cid){
			
		ModelAndView mav = new ModelAndView();
		//将状态赋值给operationItem
		//实验项目名称
		if(!"-1".equals(lp_name)){
			operationItem.setLpName(lp_name);
		}
		//学期
		SchoolTerm selectTerm=new SchoolTerm();
		if(!"-1".equals(term_id)){
			selectTerm.setId(term_id);
			operationItem.setSchoolTerm(selectTerm);
		}
		//所属课程
		SchoolCourseInfo selectCourse=new SchoolCourseInfo();
		if (!"-1".equals(course_number)) {
			selectCourse.setCourseNumber(course_number);
			operationItem.setSchoolCourseInfo(selectCourse);
		}
		//创建人
		operationItem.setUserByLpCreateUser(shareService.getUserDetail());  //当前登录人
		User user = new User();
		user.setUserRole("1");  //教师
		if(cid!=null)
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
		mav.addObject("listOperationItem", operationService.findAllOperationItemByQuery(page, pageSize, operationItem, cid, orderBy));
		mav.addObject("pageModel", shareService.getPage(page, pageSize, totalRecords));
		if(status==0 || status==1)
		{
			mav.addObject("users", systemService.getAllUser(1, -1, user));  //教师数据
		}
		  
  		List<User> departmentHeaders=systemService.getUserByAuthority(user, 18);
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
		
		mav.addObject("lp_name", lp_name);
		mav.addObject("term_id", term_id);
		mav.addObject("course_number", course_number);
		mav.addObject("lp_create_user", lp_create_user);
		mav.addObject("page", page);
		return mav;
	}
	/**
	 * 提交实验项目，保存指定审核人
	 * @author hly
	 * 2015.08.06
	 */
		
	@RequestMapping(value = "/operationRest/submitOperationItemRest/{lp_name}/{term_id}/{course_number}/{lp_create_user}/{username_check}/{page}/{isMine}/{status}/{orderBy}/{id}", method = RequestMethod.GET)
	public ModelAndView submitOperationItemRest(HttpServletRequest request,@PathVariable String lp_name,@PathVariable int term_id,@PathVariable String course_number,
			@PathVariable String lp_create_user,@PathVariable String username_check,@PathVariable int page,@PathVariable int isMine,@PathVariable int status,
			@PathVariable int orderBy,@PathVariable int id,Model model,@ModelAttribute OperationItem operationItem, @ModelAttribute("selected_labCenter") Integer cid){
		ModelAndView mav=new ModelAndView();
		OperationItem oItem=new OperationItem();
		//审核人
		User checkUser=new User();
		if (!"-1".equals(username_check)) {
			checkUser.setUsername(username_check);
			operationItem.setUserByLpCheckUser(checkUser);
		}
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
		content+="&&flag=3&status=2'>点击查看</a>";//flag设置为3，防止返回报错
		message.setContent(content);
		message.setMessageState(CommonConstantInterface.INT_Flag_ZERO);
		message.setCreateTime(date);
		message.setUsername(oItem.getUserByLpCheckUser().getUsername());
		message=messageDAO.store(message);
		int tag=1;
		if (isMine==1) {
			//System.out.println(lp_name+"[[[[");
			//TODO  lp_name 暂时写成-1  因为总是被转换成%20
			mav.setViewName("redirect:/operationRest/listMyOperationItemRest/" + "-1"
					+ "/"+ term_id + "/" + course_number + "/" + lp_create_user
					+ "/"+ page +"/0"+ "/"+status+ "/"+orderBy);
			tag=0;
		}else
			mav.setViewName("redirect:/operationRest/listOperationItemRest/" + "-1"
					+ "/"+ term_id + "/" + course_number + "/" + lp_create_user
					+ "/"+ page +"/1"+ "/"+status+ "/"+orderBy);
		//page 后面的1 or 0--flag
		String ip = shareService.getIpAddr(request);
		//保存日志信息 saveOperationItemLog(String userIp, int tag, int action, int id)
		//tag：子模块标志位  0-我的实验项目  1-实验项目管理  2-实验项目导入  id：项目卡的id 0-查看 -1--新建
		//action:  0 新建 1 编辑 2 查看 3 删除 4 提交 5 审核查看 6 保存 7 审核编辑后保存 8 导入 9 审核结果
		systemLogService.saveOperationItemLog(ip, tag, 4, operationItem.getId());
		return mav;
	}
	
}
