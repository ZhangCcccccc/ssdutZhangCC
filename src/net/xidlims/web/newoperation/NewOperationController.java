package net.xidlims.web.newoperation;

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
import net.xidlims.dao.SchoolCourseInfoDAO;
import net.xidlims.dao.SystemMajor12DAO;
import net.xidlims.dao.UserDAO;
import net.xidlims.domain.AssetCabinetWarehouseAccessRecord;
import net.xidlims.domain.CDeviceStatus;
import net.xidlims.domain.CDictionary;
import net.xidlims.domain.CommonDocument;
import net.xidlims.domain.IotSharePowerOpentime;
import net.xidlims.domain.LabCenter;
import net.xidlims.domain.LabRoom;
import net.xidlims.domain.LabRoomDevice;
import net.xidlims.domain.Message;
import net.xidlims.domain.OperationItem;
import net.xidlims.domain.OperationItemDevice;
import net.xidlims.domain.OperationItemMaterialRecord;
import net.xidlims.domain.OperationOutline;
import net.xidlims.domain.SchoolCourseDetail;
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
import net.xidlims.service.newoperation.NewOperationService;
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

@Controller("NewOperationController")
@SessionAttributes("selected_labCenter")
@RequestMapping("/newoperation")
public class NewOperationController<JsonResult> {

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
	
	@Autowired
	private NewOperationService newOperationService;
	@Autowired
	private  SchoolCourseInfoDAO schoolCourseInfoDAO;
	@Autowired
	private UserDAO userDAO;
	/*********************************************************************************
	 * description： 实验大纲管理-负责教师
	 * author：郑昕茹
	 * date：2017-04-10
	 *********************************************************************************/
	@RequestMapping("/experimentalmanagement")
	public ModelAndView experimentalmanagement(HttpServletRequest request, @ModelAttribute OperationOutline operationOutline  ,@RequestParam int currpage) {
		ModelAndView mav = new ModelAndView();
		
		//登陆人权限获得
		mav.addObject("authorities", shareService.getUser().getAuthorities());
		int pageSize=30;
		int totalRecords=operationService.getOutlinelistpage(request,operationOutline,1,-1,-1).size();
		List<OperationOutline> Outlinelist=operationService.getOutlinelistpage(request,operationOutline,currpage,pageSize,-1);
		Map<String,Integer> pageModel = shareService.getPage(currpage, pageSize,totalRecords);
		mav.addObject("outlineNames", newOperationService.getOutlineNames(request));
	    mav.addObject("newFlag", true);
	    mav.addObject("pageModel",pageModel);
		mav.addObject("totalRecords", totalRecords);
		mav.addObject("currpage", currpage);
		mav.addObject("pageSize", pageSize);
		mav.addObject("operationOutline", operationOutline);
		mav.addObject("user", shareService.getUser());
		
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
		mav.setViewName("newoperation/experimentalmanagement.jsp");
		return mav;
	}
	
	
	
	/*********************************************************************************
	 * @description:新建大纲内容
	 * @author：郑昕茹
	 * @date：2017-04-10
	 ********************************************************************************/
	@RequestMapping("/newoperationproject")
	public ModelAndView newoperationproject( HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		
		//登陆人权限获得
		mav.addObject("authorities", shareService.getUser().getAuthorities());
		OperationOutline OperationOutline= new OperationOutline();
		OperationOutline.setSchoolAcademy(shareService.getUser().getSchoolAcademy());
		mav.addObject("operationOutline",OperationOutline);
		Set<SystemMajor12> majorsEdit = OperationOutline.getSystemMajors();
		mav.addObject("majorsEdit",majorsEdit);
		Set<CDictionary> property = OperationOutline.getCDictionarys();
		mav.addObject("property",property);
		//查找登录人所在学院课程
		mav.addObject("schoolCourseInfoMap", newOperationService.getschoolcouresMapByAcademy(request));
		//获取课程先修课程
		mav.addObject("firstCourses",OperationOutline.getSchoolCourseInfoes());
		//查找登录人所在的学院专业
		mav.addObject("schoolmajer", operationService.getschoolmajerSet(-1));
		//查找学分
		mav.addObject("operationscareMap", operationService.getcoperationscareMap());
		//查找登录人所在学院的开课学院
		mav.addObject("operationstartschooleMap", operationService.getoperationstartschooleMap(-1));
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
		
		mav.addObject("user", shareService.getUser());
		mav.setViewName("newoperation/newoperationproject.jsp");
		//c_operation_outline_type
		return mav;
	}
	
	
	
	/*********************************************************************************
	 * @description:保存大纲内容
	 * @author：郑昕茹
	 * @date：2017-04-11
	 ********************************************************************************/
	@RequestMapping("/saveoperationoutline")
	public String  saveoperationoutline(@ModelAttribute OperationOutline operationOutline ,HttpServletRequest request) {
//		System.out.println(projectitrms.length);
		
		//String commencementnaturemap=request.getParameter("commencementnaturemap");
		//String projectitrms=request.getParameter("projectitrms");
		String docment=request.getParameter("docment");
		operationOutline =	operationService.saveoperationoutline(operationOutline,request);
		  if(docment!=null && docment!=""){
			 String[] str= docment.split(",");
			 for (String string : str) {
				 if(string!=null && string!=""){
					CommonDocument dd= commonDocumentDAO.findCommonDocumentByPrimaryKey(Integer.parseInt(string));
				     if(dd!=null){ 
					dd.setOperationOutline(operationOutline);
				      commonDocumentDAO.store(dd);
				 }
				 }
			}
			  
		  }
		return "redirect:/newoperation/editoutline?idkey="+operationOutline.getId();
	}
	
	
	/****************************************************************************
	 * description：编辑实验室大纲
	 * @author：徐郑昕茹
	 * @date：2017-04-11
	 ****************************************************************************/
	@RequestMapping("/editoutline")
	public ModelAndView newoperationproject(@RequestParam int idkey) {
		/**
		 * 
		 */
		ModelAndView mav = new ModelAndView();
		//登陆人权限获得
		mav.addObject("authorities", shareService.getUser().getAuthorities());
				
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
		mav.addObject("schoolCourseInfoMap", operationService.getschoolcouresMap(-1));
		//查找登录人所在的学院专业
		mav.addObject("schoolmajer", operationService.getschoolmajerSet(-1));
		//查找所有学分
		mav.addObject("operationscareMap", operationService.getcoperationscareMap());
		//获取大纲学分
		mav.addObject("outlineCredit",operationOutline.getCOperationOutlineCredit());
		//查找登录人所在学院的开课学院
		mav.addObject("operationstartschooleMap", operationService.getoperationstartschooleMap(-1));
		//获取开课学院
		mav.addObject("academies",operationOutline.getSchoolAcademies());
		//查找开课性质
		mav.addObject("commencementnaturemap", operationService.getcommencementnatureSet());
		//获取项目卡
		mav.addObject("operationItem", operationService.getoperationItemlist());
		mav.addObject("isNew", 0);
		
		mav.addObject("items", newOperationService.findOperationItemOutlineIdOrderByNumber(idkey));
		//添加实验项目
		mav.addObject("operationItem", new OperationItem());
		LabRoom labRoom = new LabRoom();
		mav.addObject("labRooms", labRoomService.findAllLabRoomByQuery(1, -1, labRoom,-1));  //实验室数据
		mav.addObject("itemCode", newOperationService.getItemCodeByOutline(operationOutline));
		mav.setViewName("newoperation/newoperationproject.jsp");
		return mav;
	}
	
	
	
	/****************************************************************************
	 * description：删除实验室大纲
	 * @author：郑昕茹
	 * @date：2017-04-11
	 ****************************************************************************/
	@RequestMapping("/delectuotline")
	public String  delectuotline(@RequestParam int idkey) {
		operationService.delectloutline(idkey);
		return "redirect:/newoperation/experimentalmanagement?currpage=1";
	}
	
	
	/***********************************************************************************
	 * @description：通过课程编号获得课程名称
	 * @author 郑昕茹
	 * @date：2017-04-11
	 * **********************************************************************************/
	@ResponseBody
	@RequestMapping("/getCourseNameByCourseNumber")
	public Map<String,String> getCourseNameByCourseNumber(@RequestParam String courseNumber){
		SchoolCourseInfo si= schoolCourseInfoDAO.findSchoolCourseInfoByCourseNumber(courseNumber);
		Map<String, String> map = new HashMap<String,String>();
		map.put("courseName", si.getCourseName());
		return map;
	}
	
	
	/***********************************************************************************
	 * @description：保存实验项目
	 * @author 郑昕茹
	 * @date：2017-04-11
	 * **********************************************************************************/
	@RequestMapping("/saveOperationItemforxidlims")
	public ModelAndView saveOperationItemforxidlims(HttpServletRequest request,@ModelAttribute OperationItem operationItem,@RequestParam int outlineId){
		ModelAndView mav=new ModelAndView();
		operationItem.setCDictionaryByLpStatusCheck(shareService.getCDictionaryByCategory("status_operation_item_check", "3"));  //无需审核，全部审核通过
		operationItem.setUserByLpCreateUser(shareService.getUserDetail());  //当前登录人
		operationItem.setCreatedAt(Calendar.getInstance());//设置为当前时间
		OperationOutline operationOutline = operationOutlineDAO.findOperationOutlineById(outlineId);
		operationItem.setOperationOutline(operationOutline);
		//获取实验大纲的课程id并保存到实验项目里
		SchoolCourseInfo schoolCourseInfo = operationOutline.getSchoolCourseInfoByClassId();
		operationItem.setSchoolCourseInfo(schoolCourseInfo);
		operationItem = operationService.saveOperationItem(operationItem);

		OperationItem maxOperationItem = newOperationService.findMaxOrderNumber(operationItem.getOperationOutline().getId());
		if(maxOperationItem  != null)
		{
			operationItem.setOrderNumber(maxOperationItem.getOrderNumber()+1);
		}
		else{
			operationItem.setOrderNumber(1);
		}
		operationItem = operationService.saveOperationItem(operationItem);
		mav.setViewName("redirect:/newoperation/editoutline?idkey="+operationOutline.getId());
		//flagId==10  保存成功标志
		
		String ip = shareService.getIpAddr(request);
		//保存日志信息 saveOperationItemLog(String userIp, int tag, int action, int id)
		//tag：子模块标志位  0-我的实验项目  1-实验项目管理  2-实验项目导入  id：项目卡的id 0-查看 -1--新建
		//action:  0 新建 1 编辑 2 查看 3 删除 4 提交 5 审核查看 6 保存 7 审核编辑后保存 8 导入 9 审核结果
		int tag=1;
		systemLogService.saveOperationItemLog(ip, tag, 6, operationItem.getId());
		
		return mav;
		
	}
	
	
	/***********************************************************************************
	 * @description：保存实验项目
	 * @author 郑昕茹
	 * @date：2017-04-11
	 * **********************************************************************************/
	@RequestMapping("/saveEditOperationItem")
	public ModelAndView saveEditOperationItem(HttpServletRequest request, @RequestParam Integer itemId,@ModelAttribute("selected_labCenter") Integer cid){
		ModelAndView mav=new ModelAndView();
		OperationItem operationItem = operationItemDAO.findOperationItemById(itemId);
		operationItem.setCDictionaryByLpStatusCheck(shareService.getCDictionaryByCategory("status_operation_item_check", "3"));  //无需审核，全部审核通过
		operationItem.setUserByLpCreateUser(shareService.getUserDetail());  //当前登录人
		operationItem.setCreatedAt(Calendar.getInstance());//设置为当前时间
		//保存当前所处的实验类型
		if(cid != -1)
		{
			operationItem.setLabCenter(labCenterService.findLabCenterByPrimaryKey(cid));
		}
		if(request.getParameter("lpName"+itemId) != null){
			operationItem.setLpName(request.getParameter("lpName"+itemId));
		}
		if(request.getParameter("lpDepartmentHours"+itemId) != null){
			operationItem.setLpDepartmentHours(Integer.parseInt(request.getParameter("lpDepartmentHours"+itemId)));
		}
		if(request.getParameter("labRoomId"+itemId) != null){
			Integer labRoomId = Integer.parseInt(request.getParameter("labRoomId"+itemId));
			LabRoom labRoom = labRoomService.findLabRoomByPrimaryKey(labRoomId);
			operationItem.setLabRoom(labRoom);
		}
		if(request.getParameter("basicRequired"+itemId) != null){
			operationItem.setBasicRequired(request.getParameter("basicRequired"+itemId));
		}
		if(request.getParameter("specification"+itemId) != null){
			operationItem.setSpecification(Integer.parseInt(request.getParameter("specification"+itemId)));
		}
		operationItem = operationService.saveOperationItem(operationItem);
		
		mav.setViewName("redirect:/newoperation/editoutline?idkey="+operationItem.getOperationOutline().getId());
		//flagId==10  保存成功标志
		
		String ip = shareService.getIpAddr(request);
		//保存日志信息 saveOperationItemLog(String userIp, int tag, int action, int id)
		//tag：子模块标志位  0-我的实验项目  1-实验项目管理  2-实验项目导入  id：项目卡的id 0-查看 -1--新建
		//action:  0 新建 1 编辑 2 查看 3 删除 4 提交 5 审核查看 6 保存 7 审核编辑后保存 8 导入 9 审核结果
		int tag=1;
		systemLogService.saveOperationItemLog(ip, tag, 6, operationItem.getId());
		
		return mav;
		
	}
	

	/****************************************************************************
	 * description：提交实验室大纲
	 * @author：郑昕茹
	 * @date：2017-04-11
	 ****************************************************************************/
	@RequestMapping("/submitOutline")
	public String  submitOutline(@RequestParam Integer outlineId) {
		OperationOutline operationOutline = operationOutlineDAO.findOperationOutlineById(outlineId);
		operationOutline.setStatus(1);
		operationOutlineDAO.store(operationOutline);
		return "redirect:/newoperation/experimentalmanagement?currpage=1";
	}
	
	
	/****************************************************************************
	 * description：删除实验项目
	 * @author：郑昕茹
	 * @date：2017-04-11
	 ****************************************************************************/
	@RequestMapping("/deleteOperationItem")
	public String deleteOperationItem(HttpServletRequest request,@RequestParam int operationItemId){
		OperationItem operationItem = operationItemDAO.findOperationItemById(operationItemId);
		Integer outlineId = operationItem.getOperationOutline().getId();
		Integer currentOrder = operationItem.getOrderNumber();
		List<OperationItem> largerItems = newOperationService.findOperationItemsLargerOrderNumberByOutlineId(currentOrder, outlineId);
		for(OperationItem o:largerItems){
			o.setOrderNumber(o.getOrderNumber()-1);
			operationItemDAO.store(o);
		}
		operationService.deleteOperationItem(operationItemId);
		return "redirect:/newoperation/editoutline?idkey="+outlineId;
	}
	
	
	
	/****************************************************************************
	 * description：上移实验项目在实验大纲中的位置
	 * @author：郑昕茹
	 * @date：2017-04-11
	 ****************************************************************************/
	@RequestMapping("/upOrder")
	public String upOrder(@RequestParam Integer id){ 
		OperationItem operationItem = operationItemDAO.findOperationItemById(id);
		//获取该条实验项目当前顺序
		Integer currentOrder= operationItem.getOrderNumber();
		//若不是第一条，都可以进行上移操作
		if(currentOrder != 1){
			//获取要改变的顺序
			Integer changeOrder = currentOrder - 1;
			//根据顺序找到上一条实验项目
			OperationItem lastOperationItem = newOperationService.findOperationItemByOrderNumberAndOutlineId(changeOrder, operationItem.getOperationOutline().getId());
			//设置该条实验项目的顺序为changeOrder
			operationItem.setOrderNumber(changeOrder);
			//设置上一条实验项目信息的顺序为currentOrder
			lastOperationItem.setOrderNumber(currentOrder);
			//保存两条实验项目
			operationItemDAO.store(lastOperationItem);
			operationItemDAO.store(operationItem);
		}
		
		return "redirect:/newoperation/editoutline?idkey="+operationItem.getOperationOutline().getId();
	} 
	/***********************************************************************************
	 * @功能：下移实验项目在实验大纲中的位置
	 * @author 郑昕茹
	 * @日期：2017-04-11
	 * **********************************************************************************/
	@RequestMapping("/downOrder")
	public String downOrder(@RequestParam Integer id){ 
		//根据主键获取该条设备状态信息
		OperationItem operationItem = operationItemDAO.findOperationItemById(id);
		//获取该条设备状态信息的顺序
		Integer currentOrder = operationItem.getOrderNumber();
		//下移后的顺序
		Integer changeOrder = currentOrder + 1;
		//找到最后一条，即statusOrder值最大的正常状态的设备状态信息
		OperationItem maxOperationItem = newOperationService.findMaxOrderNumber(operationItem.getOperationOutline().getId());
		//若当前设备状态信息不是最后一条可以进行下移操作
		if(currentOrder < maxOperationItem.getOrderNumber()){
			//根据顺序找到下一条设备状态信息
			OperationItem operationItem2 = newOperationService.findOperationItemByOrderNumberAndOutlineId(currentOrder+1,operationItem.getOperationOutline().getId());
			//设置该条设备信息的顺序为changeOrder
			operationItem.setOrderNumber(changeOrder);
			//设置下一条设备状态信息的顺序为currentOrder
			operationItem2.setOrderNumber(currentOrder);
			//保存两条设备状态信息
			operationItemDAO.store(operationItem);
			operationItemDAO.store(operationItem2);
		}
		
		return "redirect:/newoperation/editoutline?idkey="+operationItem.getOperationOutline().getId();
	} 
	
	
	/*********************************************************************************
	 * description： 实验大纲管理-教务管理员-全部审核
	 * author：郑昕茹
	 * date：2017-04-12
	 *********************************************************************************/
	@RequestMapping("/experimentalAudit")
	public ModelAndView experimentalAudit(HttpServletRequest request, @ModelAttribute OperationOutline operationOutline  ,@RequestParam int currpage,@ModelAttribute("selected_labCenter") Integer sid) {
		ModelAndView mav = new ModelAndView();
		//登陆人权限获得
		mav.addObject("authorities", shareService.getUser().getAuthorities());
		int pageSize=30;
		int totalRecords=newOperationService.getAllOutlinelistpage(request,operationOutline,1,-1,sid).size();
		List<OperationOutline> Outlinelist=newOperationService.getAllOutlinelistpage(request,operationOutline,currpage,pageSize,sid);
		Map<String,Integer> pageModel = shareService.getPage(currpage, pageSize,totalRecords);
		mav.addObject("outlineNames", newOperationService.getOutlineNames(request));
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
		mav.setViewName("newoperation/experimentalAudit.jsp");
		return mav;
	}
	
	/*********************************************************************************
	 * description： 实验大纲管理-教务管理员-我的审核
	 * author：郑昕茹
	 * date：2017-04-12
	 *********************************************************************************/
	@RequestMapping("/experimentalMyAudit")
	public ModelAndView experimentalMyAudit(HttpServletRequest request, @ModelAttribute OperationOutline operationOutline  ,@RequestParam int currpage,@ModelAttribute("selected_labCenter") Integer sid) {
		ModelAndView mav = new ModelAndView();
		//登陆人权限获得
		mav.addObject("authorities", shareService.getUser().getAuthorities());
		int pageSize=30;
		int totalRecords=newOperationService.getAuditOutlinelistpage(request,operationOutline,1,-1,sid).size();
		List<OperationOutline> Outlinelist=newOperationService.getAuditOutlinelistpage(request,operationOutline,currpage,pageSize,sid);
		Map<String,Integer> pageModel = shareService.getPage(currpage, pageSize,totalRecords);
		mav.addObject("outlineNames", newOperationService.getOutlineNames(request));
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
		mav.setViewName("newoperation/experimentalMyAudit.jsp");
		return mav;
	}
	
	
	/****************************************************************************
	 * description：审核通过实验大纲
	 * @author：郑昕茹
	 * @date：2017-04-12
	 ****************************************************************************/
	@RequestMapping("/auditPassOutline")
	public String auditPassOutline(@RequestParam Integer outlineId) {
		OperationOutline operationOutline = operationOutlineDAO.findOperationOutlineById(outlineId);
		operationOutline.setStatus(2);
		operationOutline.setUserByAuditUser(shareService.getUser());
		operationOutlineDAO.store(operationOutline);
		return "redirect:/newoperation/experimentalAudit?currpage=1";
	}
	
	/****************************************************************************
	 * description：审核拒绝实验大纲
	 * @author：郑昕茹
	 * @date：2017-04-12
	 ****************************************************************************/
	@RequestMapping("/auditRefuseOutline")
	public String auditRefuseOutline(@RequestParam Integer outlineId) {
		OperationOutline operationOutline = operationOutlineDAO.findOperationOutlineById(outlineId);
		operationOutline.setStatus(3);
		operationOutline.setUserByAuditUser(shareService.getUser());
		operationOutlineDAO.store(operationOutline);
		return "redirect:/newoperation/experimentalAudit?currpage=1";
	}
	
	
	
	/****************************************************************************
	 * description：审核通过实验大纲
	 * @author：郑昕茹
	 * @date：2017-04-12
	 ****************************************************************************/
	@RequestMapping("/myAuditPassOutline")
	public String myAuditPassOutline(@RequestParam Integer outlineId) {
		OperationOutline operationOutline = operationOutlineDAO.findOperationOutlineById(outlineId);
		operationOutline.setStatus(2);
		operationOutline.setUserByAuditUser(shareService.getUser());
		operationOutlineDAO.store(operationOutline);
		return "redirect:/newoperation/experimentalAudit?currpage=1";
	}
	
	/****************************************************************************
	 * description：审核拒绝实验大纲
	 * @author：郑昕茹
	 * @date：2017-04-12
	 ****************************************************************************/
	@RequestMapping("/myAuditRefuseOutline")
	public String myAuditRefuseOutline(@RequestParam Integer outlineId) {
		OperationOutline operationOutline = operationOutlineDAO.findOperationOutlineById(outlineId);
		operationOutline.setStatus(3);
		operationOutline.setUserByAuditUser(shareService.getUser());
		operationOutlineDAO.store(operationOutline);
		return "redirect:/newoperation/experimentalAudit?currpage=1";
	}
	
	
	
	/*********************************************************************************
	 * @description:查看大纲内容
	 * @author：郑昕茹
	 * @date：2017-04-10
	 ********************************************************************************/
	@RequestMapping("/viewOperationOutline")
	public ModelAndView viewOperationOutline(@RequestParam Integer idkey, Integer from, Integer page) {
		ModelAndView mav = new ModelAndView();
		//登陆人权限获得
		mav.addObject("authorities", shareService.getUser().getAuthorities());
        mav.addObject("operationOutline", operationService.getoperationoutlineinfor(idkey));  
    	mav.addObject("items", newOperationService.findOperationItemOutlineIdOrderByNumber(idkey));
		mav.setViewName("newoperation/viewOperationOutline.jsp");
		if(from != null){
			mav.addObject("from",from);
		}
		else{
			mav.addObject("from",0);
		}
		mav.addObject("page", page);
		return mav;
	}
	
	
	
	/*********************************************************************************
	 * description： 实验大纲管理-教学副院长大纲修订权限
	 * author：郑昕茹
	 * date：2017-04-17
	 *********************************************************************************/
	@RequestMapping("/listOperationOutlinePermissions")
	public ModelAndView listOperationOutlinePermissions(HttpServletRequest request, @ModelAttribute OperationOutline operationOutline  ,@RequestParam int currpage,@ModelAttribute("selected_labCenter") Integer sid) {
		ModelAndView mav = new ModelAndView();
		
		//登陆人权限获得
		mav.addObject("authorities", shareService.getUser().getAuthorities());
		int pageSize=30;
		int totalRecords=operationService.getOutlinelistpage(request,operationOutline,1,-1,sid).size();
		List<OperationOutline> Outlinelist=operationService.getOutlinelistpage(request,operationOutline,currpage,pageSize,sid);
		Map<String,Integer> pageModel = shareService.getPage(currpage, pageSize,totalRecords);
		mav.addObject("outlineNames", newOperationService.getOutlineNames(request));
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
		mav.setViewName("newoperation/listOperationOutlinePermissions.jsp");
		mav.addObject("teachers", shareService.findAllTeacheresByAcademyNumber(shareService.getUser().getSchoolAcademy().getAcademyNumber()));
		return mav;
	}
	
	
	/*********************************************************************************
	 * description： 实验大纲教师授权
	 * author：郑昕茹
	 * date：2017-04-17
	 *********************************************************************************/
	@RequestMapping("/setOperationOutlinePermission")
	public @ResponseBody
	String setOperationOutlinePermission(Integer[] outlineids, String teacher) {
		Integer count = 0;
		User user = userDAO.findUserByPrimaryKey(teacher);
		for(Integer id:outlineids){
			OperationOutline operationOutline = operationOutlineDAO.findOperationOutlineById(id);
			operationOutline.setUserByPermittedTeacher(user);
			operationOutlineDAO.store(operationOutline);
			count++;
		}
		return "success";
	}
	
	
	/*********************************************************************************
	 * description： 实验大纲查看-学生
	 * author：郑昕茹
	 * date：2017-05-02
	 *********************************************************************************/
	@RequestMapping("/listStudentsOperationOutlines")
	public ModelAndView listStudentsOperationOutlines(HttpServletRequest request, @ModelAttribute OperationOutline operationOutline, @RequestParam Integer currpage) {
		ModelAndView mav = new ModelAndView();
		
		//登陆人权限获得
		mav.addObject("authorities", shareService.getUser().getAuthorities());
		int pageSize=30;
		int totalRecords=newOperationService.getListStudentsOperationOutlines(request, operationOutline, 1,-1).size();
		List<OperationOutline> Outlinelist=newOperationService.getListStudentsOperationOutlines(request, operationOutline, currpage, pageSize);
		Map<String,Integer> pageModel = shareService.getPage(currpage, pageSize,totalRecords);
		mav.addObject("outlineNames", newOperationService.getListStudentsOperationOutlines(request, new OperationOutline(), 1,-1));
	    mav.addObject("newFlag", true);
	    mav.addObject("pageModel",pageModel);
		mav.addObject("totalRecords", totalRecords);
		mav.addObject("currpage", currpage);
		mav.addObject("pageSize", pageSize);
		mav.addObject("operationOutline", operationOutline);
		mav.addObject("user", shareService.getUser());
		//查找所有的实验大纲
		mav.addObject("Outlinelist",Outlinelist );
		List<CDictionary> schoolTerms = shareService.getCDictionaryData("c_student_status");
		mav.addObject("schoolTerm", schoolTerms);
		mav.setViewName("newoperation/listStudentsOperationOutlines.jsp");
		return mav;
	}
	
	/*********************************************************************************
	 * description： 实验大纲查看-查看某课程下的全部实验大纲
	 * author：郑昕茹
	 * date：2017-05-02
	 *********************************************************************************/
	@RequestMapping("/viewCourseOperationOutlines")
	public ModelAndView viewCourseOperationOutlines(HttpServletRequest request, @ModelAttribute OperationOutline operationOutline, @RequestParam Integer currpage, @RequestParam Integer type, @RequestParam String courseCode) {
		ModelAndView mav = new ModelAndView();
		
		//登陆人权限获得
		mav.addObject("authorities", shareService.getUser().getAuthorities());
		int pageSize=30;
		int totalRecords=newOperationService.findOperationOutlinesByCourseCodeAndType(operationOutline, 1, -1, courseCode, type).size();
		List<OperationOutline> Outlinelist=newOperationService.findOperationOutlinesByCourseCodeAndType(operationOutline, currpage, pageSize, courseCode, type);
		Map<String,Integer> pageModel = shareService.getPage(currpage, pageSize,totalRecords);
		mav.addObject("outlineNames", newOperationService.findOperationOutlinesByCourseCodeAndType(new OperationOutline(), 1,-1, courseCode, type));
	    mav.addObject("newFlag", true);
	    mav.addObject("pageModel",pageModel);
		mav.addObject("totalRecords", totalRecords);
		mav.addObject("currpage", currpage);
		mav.addObject("pageSize", pageSize);
		mav.addObject("operationOutline", operationOutline);
		mav.addObject("user", shareService.getUser());
		//查找所有的实验大纲
		mav.addObject("Outlinelist",Outlinelist );
		List<CDictionary> schoolTerms = shareService.getCDictionaryData("c_student_status");
		mav.addObject("schoolTerm", schoolTerms);
		mav.setViewName("newoperation/viewCourseOperationOutlines.jsp");
		return mav;
	}
	
	/*********************************************************************************
	 * description： 实验大纲查看-教师
	 * author：郑昕茹
	 * date：2017-05-02
	 *********************************************************************************/
	@RequestMapping("/listTeachersOperationOutlines")
	public ModelAndView listTeachersOperationOutlines(HttpServletRequest request, @ModelAttribute OperationOutline operationOutline, @RequestParam Integer currpage) {
		ModelAndView mav = new ModelAndView();
		
		//登陆人权限获得
		mav.addObject("authorities", shareService.getUser().getAuthorities());
		int pageSize=30;
		int totalRecords=newOperationService.getListTeachersOperationOutlines(request, operationOutline, 1,-1).size();
		List<OperationOutline> Outlinelist=newOperationService.getListTeachersOperationOutlines(request, operationOutline, currpage, pageSize);
		Map<String,Integer> pageModel = shareService.getPage(currpage, pageSize,totalRecords);
		mav.addObject("outlineNames", newOperationService.getListTeachersOperationOutlines(request, new OperationOutline(), 1,-1));
	    mav.addObject("newFlag", true);
	    mav.addObject("pageModel",pageModel);
		mav.addObject("totalRecords", totalRecords);
		mav.addObject("currpage", currpage);
		mav.addObject("pageSize", pageSize);
		mav.addObject("operationOutline", operationOutline);
		mav.addObject("user", shareService.getUser());
		//查找所有的实验大纲
		mav.addObject("Outlinelist",Outlinelist );
		List<CDictionary> schoolTerms = shareService.getCDictionaryData("c_student_status");
		mav.addObject("schoolTerm", schoolTerms);
		mav.setViewName("newoperation/listTeachersOperationOutlines.jsp");
		return mav;
	}
	
	
	/*********************************************************************************
	 * description： 实验大纲查看-全部实验大纲
	 * author：郑昕茹
	 * date：2017-05-02
	 *********************************************************************************/
	@RequestMapping("/listViewAllOperationOutlines")
	public ModelAndView listViewAllOperationOutlines(HttpServletRequest request, @ModelAttribute OperationOutline operationOutline, @RequestParam Integer currpage) {
		ModelAndView mav = new ModelAndView();
		
		//登陆人权限获得
		mav.addObject("authorities", shareService.getUser().getAuthorities());
		int pageSize=30;
		int totalRecords=newOperationService.getListAllOperationOutlines(request, operationOutline, 1,-1).size();
		List<OperationOutline> Outlinelist=newOperationService.getListAllOperationOutlines(request, operationOutline, currpage, pageSize);
		Map<String,Integer> pageModel = shareService.getPage(currpage, pageSize,totalRecords);
		mav.addObject("outlineNames", newOperationService.getListAllOperationOutlines(request, new OperationOutline(), 1,-1));
	    mav.addObject("newFlag", true);
	    mav.addObject("pageModel",pageModel);
		mav.addObject("totalRecords", totalRecords);
		mav.addObject("currpage", currpage);
		mav.addObject("pageSize", pageSize);
		mav.addObject("operationOutline", operationOutline);
		mav.addObject("user", shareService.getUser());
		//查找所有的实验大纲
		mav.addObject("Outlinelist",Outlinelist );
		List<CDictionary> schoolTerms = shareService.getCDictionaryData("c_student_status");
		mav.addObject("schoolTerm", schoolTerms);
		mav.setViewName("newoperation/listAllOperationOutlines.jsp");
		return mav;
	}
	/*********************************************************************************
	 * 功能:个人头像上传
	 * 作者：戴昊宇
	 * 日期：2017-08-30
	 ********************************************************************************/
		@RequestMapping("/uploadphoto")
		public @ResponseBody String uploaddnolinedocment(HttpServletRequest request, HttpServletResponse response, BindException errors,Integer id) throws Exception {
			String ss=operationService.uploadphoto(request, response,id);
			return shareService.htmlEncode(ss);
	}
}
	
	
