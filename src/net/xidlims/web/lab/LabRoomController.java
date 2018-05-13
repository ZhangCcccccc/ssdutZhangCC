package net.xidlims.web.lab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.xidlims.JsonDateValueProcessor;
import net.xidlims.constant.CommonConstantInterface;
import net.xidlims.constant.LabAttendance;
import net.xidlims.dao.AuthorityDAO;
import net.xidlims.dao.CDictionaryDAO;
import net.xidlims.dao.CommonServerDAO;
import net.xidlims.dao.LabRoomAdminDAO;
import net.xidlims.dao.LabRoomAgentDAO;
import net.xidlims.dao.LabRoomCourseCapacityDAO;
import net.xidlims.dao.LabRoomDAO;
import net.xidlims.dao.LabRoomDeviceDAO;
import net.xidlims.dao.LabRoomFurnitureDAO;
import net.xidlims.dao.LabRoomLimitTimeDAO;
import net.xidlims.dao.OperationItemDAO;
import net.xidlims.dao.SchoolCourseDetailDAO;
import net.xidlims.dao.UserDAO;
import net.xidlims.domain.Authority;
import net.xidlims.domain.CDictionary;
import net.xidlims.domain.CommonHdwlog;
import net.xidlims.domain.CommonServer;
import net.xidlims.domain.LabAnnex;
import net.xidlims.domain.LabCenter;
import net.xidlims.domain.LabRoom;
import net.xidlims.domain.LabRoomAdmin;
import net.xidlims.domain.LabRoomAgent;
import net.xidlims.domain.LabRoomCourseCapacity;
import net.xidlims.domain.LabRoomDevice;
import net.xidlims.domain.LabRoomFurniture;
import net.xidlims.domain.LabRoomLimitTime;
import net.xidlims.domain.LabWorker;
import net.xidlims.domain.OperationItem;
import net.xidlims.domain.SchoolAcademy;
import net.xidlims.domain.SchoolDevice;
import net.xidlims.domain.SchoolTerm;
import net.xidlims.domain.SystemLog;
import net.xidlims.domain.User;
import net.xidlims.service.common.ShareService;
import net.xidlims.service.device.LabRoomDeviceService;
import net.xidlims.service.device.SchoolDeviceService;
import net.xidlims.service.dictionary.CDictionaryService;
import net.xidlims.service.lab.LabAnnexService;
import net.xidlims.service.lab.LabCenterService;
import net.xidlims.service.lab.LabRoomFurnitureService;
import net.xidlims.service.lab.LabRoomService;
import net.xidlims.service.system.SystemLogService;
import net.xidlims.service.system.SystemService;
import net.xidlims.service.timetable.OuterApplicationService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller("LabRoomController")
@SessionAttributes("selected_labCenter")
@RequestMapping("/labRoom")
public class LabRoomController<JsonResult> {

	@Autowired LabRoomDAO labRoomDAO;
	@Autowired LabRoomFurnitureDAO labRoomFurnitureDAO;
	@Autowired LabRoomFurnitureService labRoomFurnitureService;
	@Autowired OuterApplicationService outerApplicationService;
	
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
	CDictionaryService cDictionaryService;
	@Autowired
	private LabCenterService labCenterService;
	@Autowired 
	LabRoomDeviceService labRoomDeviceService;
	@Autowired 
	CommonServerDAO commonServerDAO;
	@Autowired
	OperationItemDAO operationItemDAO;
	@Autowired
	CDictionaryDAO cDictionaryDAO;
	@Autowired
	LabRoomAgentDAO labRoomAgentDAO;
	@Autowired
	UserDAO userDAO;
	@Autowired
	LabRoomAdminDAO labRoomAdminDAO;
	@Autowired
	AuthorityDAO authorityDAO;
	@Autowired
	SchoolDeviceService schoolDeviceService;
	@Autowired
	LabRoomDeviceDAO labRoomDeviceDAO;
	@Autowired
	LabAnnexService labAnnexService;
	@Autowired
	private  SystemLogService systemLogService;
	@Autowired
	private  SchoolCourseDetailDAO schoolCourseDetailDAO;
	@Autowired LabRoomLimitTimeDAO labRoomLimitTimeDAO;
	@Autowired
	private LabRoomCourseCapacityDAO labRoomCourseCapacityDAO;
	/**
	 * 实验室列表
	 * @author hly
	 * 2015.07.28
	 */
	@RequestMapping("/listLabRoom")
	public ModelAndView listLabRoom(@RequestParam int currpage, int orderBy, @ModelAttribute LabRoom labRoom, @RequestParam Integer cid){
		ModelAndView mav = new ModelAndView();
		int pageSize = CommonConstantInterface.INT_PAGESIZE;
		if(cid!=null && cid>0)
		{
			LabCenter labCenter = new LabCenter();
			labCenter.setId(cid);
			labRoom.setLabCenter(labCenter);
		}
		int totalRecords = labRoomService.findAllLabRoomByQuery(1, -1, labRoom,9,cid).size();
		
		mav.addObject("cid", cid);
		mav.addObject("labRoom", labRoom);
		mav.addObject("pageModel", shareService.getPage(currpage, pageSize, totalRecords));
		mav.addObject("listLabRoom", labRoomService.findAllLabRoomByQuery(currpage, pageSize, labRoom,orderBy,cid));
		//决定升序还是降序
		boolean asc=true;
		if (orderBy<10) {
			asc=false;
		}
		mav.addObject("asc", asc);
		mav.addObject("orderBy", orderBy);
		mav.addObject("user", shareService.getUser());
		String labString = "";
		if(shareService.getUser().getLabRoomAdmins() != null){
			for(LabRoomAdmin lad:shareService.getUser().getLabRoomAdmins()){
				labString += ","+lad.getLabRoom().getId();
			}
		}
		mav.addObject("labString", labString);
		mav.setViewName("lab/lab_room/listLabRoom.jsp");
		return mav;
	}
	
	/**
	 * 新建实验室
	 * @author hly
	 * 2015.07.28
	 */
	@RequestMapping("/newLabRoom")
	public ModelAndView newLabRoom(@ModelAttribute LabAnnex labAnnex,@RequestParam int labCenterId,@ModelAttribute("selected_labCenter") Integer cid){
		ModelAndView mav = new ModelAndView();
		LabCenter labCenter=labCenterService.findLabCenterByPrimaryKey(labCenterId);
		//实验室类型
		List<CDictionary> labRoomTypes = shareService.getCDictionaryData("c_lab_room_type");
		LabRoom labRoom = new LabRoom();
		labRoom.setLabRoomTimeCreate(Calendar.getInstance());
		mav.addObject("labRoomTypes", labRoomTypes);
		//mav.addObject("labCenterName", labCenter.getCenterName());
		mav.addObject("labCenterId", labCenterId);
		mav.addObject("labRoom", labRoom);
		List<LabAnnex> listLabAnnex=labAnnexService.findAllLabAnnexByQuery(1, -1, labAnnex,cid);
		mav.addObject("listLabAnnex", listLabAnnex);
		mav.addObject("subject12s", systemService.getAllSystemSubject12(1, -1));  //学科数据(12版)
		mav.addObject("listLabCenter", labCenterService.findAllLabCenterByQuery(new LabCenter(), 1, -1));
		mav.setViewName("lab/lab_room/editLabRoom.jsp");
		return mav;
	}
	
	/**
	 * 编辑实验室
	 * @author hly
	 * 2015.07.28
	 */
	@RequestMapping("/editLabRoom")
	public ModelAndView editLabRoom(@ModelAttribute LabAnnex labAnnex,@RequestParam int labRoomId,@ModelAttribute("selected_labCenter") Integer cid){
		ModelAndView mav = new ModelAndView();
		//实验室类型
		List<CDictionary> labRoomTypes = shareService.getCDictionaryData("c_lab_room_type");
		mav.addObject("labRoomTypes", labRoomTypes);
		mav.addObject("labRoom", labRoomService.findLabRoomByPrimaryKey(labRoomId));
		mav.addObject("currType", labRoomService.findLabRoomByPrimaryKey(labRoomId).getCDictionaryByLabRoom());
		mav.addObject("subject12s", systemService.getAllSystemSubject12(1, -1));  //学科数据(12版)
		mav.addObject("listLabCenter", labCenterService.findAllLabCenterByQuery(new LabCenter(), 1, -1));
		List<LabAnnex> listLabAnnex=labAnnexService.findAllLabAnnexByQuery(1, -1, labAnnex,cid);
		mav.addObject("listLabAnnex", listLabAnnex);
		mav.setViewName("lab/lab_room/editLabRoom.jsp");
		return mav;
	}
	
	/**
	 * 保存实验室数据
	 * @author hly
	 * 2015.07.28
	 */
	@RequestMapping("/saveLabRoom")
	public String saveLabRoom(@ModelAttribute LabRoom labRoom, HttpServletRequest request){
		String type = request.getParameter("type");
		if(labRoom != null && labRoom.getId() != null && !labRoom.getId().equals("")){
			LabRoom l = labRoomService.findLabRoomByPrimaryKey(labRoom.getId());
			if(l != null){
				labRoom.setSystemRoom(l.getSystemRoom());
			}
		}
		
		CDictionary c = shareService.getCDictionaryByCategory("c_lab_room_type", type);
		labRoom.setCDictionaryByLabRoomType(c);
		labRoom.setIsUsed(labRoom.getLabRoomActive());
		//labRoom.setLabCenter(labCenterService.findLabCenterByPrimaryKey(12));
		labRoom.setIsUsed(1);
		if(labRoom.getReservationNumber() == null)
		{
			labRoom.setReservationNumber(1);
		}
		labRoomService.saveLabRoom(labRoom);
		
		return "redirect:/labRoom/listLabRoom?currpage=1&orderBy=9&cid="+labRoom.getLabCenter().getId();
	}
	
	/**
	 * 删除实验室数据
	 * @author hly
	 * 2015.07.28
	 */
	@RequestMapping("/deleteLabRoom")
	public String deleteLabRoom(@RequestParam int labRoomId, Integer cid){
		LabRoom room=labRoomService.findLabRoomByPrimaryKey(labRoomId);
		room.setIsUsed(0);//假删  
		labRoomService.saveLabRoom(room);
		//labRoomService.deleteLabRoom(labRoomId);  真删  由于和实验项目的外键关系，会报错删不掉
		
		return "redirect:/labRoom/listLabRoom?currpage=1&orderBy=9&cid="+cid;
	}
	
	/**
	 * 实验室工作人员列表
	 * @author hly
	 * 2015.07.29
	 */
	@RequestMapping("/listLabWorker")
	public ModelAndView listLabWorker(@RequestParam int currpage, @ModelAttribute LabWorker labWorker){
		ModelAndView mav = new ModelAndView();
		int pageSize = CommonConstantInterface.INT_PAGESIZE;
		int totalRecords = labRoomService.findAllLabWorkerByQuery(1, -1, labWorker).size();
		
		mav.addObject("listLabWorker", labRoomService.findAllLabWorkerByQuery(currpage, pageSize, labWorker));
		mav.addObject("pageModel", shareService.getPage(currpage, pageSize, totalRecords));
		mav.setViewName("lab/lab_worker/listLabWorker.jsp");
		return mav;
	}
	
	/**
	 * 新建实验室工作人员
	 * @author hly
	 * 2015.07.29
	 */
	@RequestMapping("/newLabWorker")
	public ModelAndView newLabWorker(){
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("labWorker", new LabWorker());
		mav.addObject("listLabCenter", labCenterService.findAllLabCenterByQuery(new LabCenter(), 1, -1));
		mav.addObject("listUser", outerApplicationService.getTimetableTearcherMap());
		mav.addObject("listAcademicDegree", shareService.getCDictionaryData("category_lab_worker_academic_degree"));  //文化程度
		mav.addObject("listSubject", shareService.getCDictionaryData("category_lab_worker_subject"));  //所属学科
		mav.addObject("listSpecialtyDuty", shareService.getCDictionaryData("category_lab_worker_specialty_duty"));  //专业职称
		mav.addObject("listCategoryStaff", shareService.getCDictionaryData("category_lab_worker_category_staff"));  //人员类别
		mav.addObject("listEmployment", shareService.getCDictionaryData("category_lab_worker_employment"));  //聘任情况
		mav.addObject("listReward", shareService.getCDictionaryData("category_lab_worker_reward"));  //成果奖励
		mav.addObject("listForeignLanguage", shareService.getCDictionaryData("category_lab_worker_foreign_language"));  //外语语种
		mav.addObject("listForeignLanguageLevel", shareService.getCDictionaryData("category_lab_worker_foreign_language_level"));  //外语水平
		mav.addObject("listDegree", shareService.getCDictionaryData("category_lab_worker_degree"));  //学位
		mav.addObject("listMainWork", shareService.getCDictionaryData("category_lab_worker_main_work"));  //主要工作
		mav.addObject("listPaperLevel", shareService.getCDictionaryData("category_lab_worker_paper_level"));  //论文级别
		mav.addObject("listBookLevel", shareService.getCDictionaryData("category_lab_worker_book_level"));  //著作级别
		mav.addObject("listCategoryExpert", shareService.getCDictionaryData("category_lab_worker_category_expert"));  //专家类别
		mav.setViewName("lab/lab_worker/editLabWorker.jsp");
		return mav;
	}
	
	/**
	 * 编辑实验室工作人员
	 * @author hly
	 * 2015.07.29
	 */
	@RequestMapping("/editLabWorker")
	public ModelAndView editLabWorker(@RequestParam int labWorkerId){
        ModelAndView mav = new ModelAndView();
		
        mav.addObject("labWorker", labRoomService.findLabWorkerByPrimaryKey(labWorkerId));
        mav.addObject("listLabCenter", labCenterService.findAllLabCenterByQuery(new LabCenter(), 1, -1));
        mav.addObject("listUser", outerApplicationService.getTimetableTearcherMap());
        mav.addObject("listAcademicDegree", shareService.getCDictionaryData("category_lab_worker_academic_degree"));  //文化程度
		mav.addObject("listSubject", shareService.getCDictionaryData("category_lab_worker_subject"));  //所属学科
		mav.addObject("listSpecialtyDuty", shareService.getCDictionaryData("category_lab_worker_specialty_duty"));  //专业职称
		mav.addObject("listCategoryStaff", shareService.getCDictionaryData("category_lab_worker_category_staff"));  //人员类别
		mav.addObject("listEmployment", shareService.getCDictionaryData("category_lab_worker_employment"));  //聘任情况
		mav.addObject("listReward", shareService.getCDictionaryData("category_lab_worker_reward"));  //成果奖励
		mav.addObject("listForeignLanguage", shareService.getCDictionaryData("category_lab_worker_foreign_language"));  //外语语种
		mav.addObject("listForeignLanguageLevel", shareService.getCDictionaryData("category_lab_worker_foreign_language_level"));  //外语水平
		mav.addObject("listDegree", shareService.getCDictionaryData("category_lab_worker_degree"));  //学位
		mav.addObject("listMainWork", shareService.getCDictionaryData("category_lab_worker_main_work"));  //主要工作
		mav.addObject("listPaperLevel", shareService.getCDictionaryData("category_lab_worker_paper_level"));  //论文级别
		mav.addObject("listBookLevel", shareService.getCDictionaryData("category_lab_worker_book_level"));  //著作级别
		mav.addObject("listCategoryExpert", shareService.getCDictionaryData("category_lab_worker_category_expert"));  //专家类别
		mav.setViewName("lab/lab_worker/editLabWorker.jsp");
		return mav;
	}
	
	/**
	 * 保存实验室工作人员数据
	 * @author hly
	 * 2015.07.29
	 */
	@RequestMapping("/saveLabWorker")
	public String saveLabWorker(@ModelAttribute LabWorker labWorker){
		labRoomService.saveLabWorker(labWorker);
		
		return "redirect:/labRoom/listLabWorker?currpage=1";
	}
	
	/**
	 * 删除实验室工作人员
	 * @author hly
	 * 2015.07.29
	 */
	@RequestMapping("/deleteLabWorker")
	public String deleteLabWorker(@RequestParam int labWorkerId){
		labRoomService.deleteLabWorker(labWorkerId);
		
		return "redirect:/labRoom/listLabWorker?currpage=1";
	}
	
	/****************************************************************************
	 * 功能：查看实验分室详情
	 * 作者：贺子龙
	 * 时间：2015-09-03
	 ****************************************************************************/
	@RequestMapping("/getLabRoom")
	public ModelAndView getLabRoom(@RequestParam Integer id,@ModelAttribute SchoolDevice schoolDevice, Integer cid){
		//新建ModelAndView对象；
		ModelAndView mav=new ModelAndView();
		User user=shareService.getUser();
		mav.addObject("user", user);
		boolean flag=labRoomService.getLabRoomAdminReturn(id,user);
		if(user.getLabCenter() != null && user.getLabCenter().getId().intValue() == cid.intValue()){
			flag = true;
		}
		mav.addObject("flag", flag);
//		System.out.println(flag);
		//id对应的实验分室信息
		LabRoom labRoom=labRoomDAO.findLabRoomByPrimaryKey(id);
		mav.addObject("labRoom", labRoom);
		Set<OperationItem> operationItems = labRoom.getOperationItems();
		mav.addObject("operationItems", operationItems);
		//所有的实验项目卡
		List<OperationItem> items=labRoomService.findAllOperationItem(user.getSchoolAcademy().getAcademyNumber());
		mav.addObject("items", items);
		//实验室家具
		List<LabRoomFurniture> labRoomFurniture = labRoomFurnitureService.findLabRoomFurnitureByRooId(id);
		mav.addObject("labRoomFurniture",labRoomFurniture);
		mav.addObject("labRoomFurnitures", new LabRoomFurniture());
		//根据实验室查询实验室管理员
				List<LabRoomAdmin> adminList =labRoomDeviceService.findLabRoomAdminByRoomId(id,1);
				mav.addObject("adminList", adminList);
				//实验室物联管理员
				List<LabRoomAdmin> agentAdmin =labRoomDeviceService.findLabRoomAdminByRoomId(id,2);
				mav.addObject("agentAdmin", agentAdmin);
				//实验室药品管理员
				List<LabRoomAdmin> cabinetAdmin =labRoomDeviceService.findLabRoomAdminByRoomId(id,3);
				mav.addObject("cabinetAdmin", cabinetAdmin);
				//实验室管理员
				mav.addObject("admin",new LabRoomAdmin());
				//物联硬件
				mav.addObject("agent",new LabRoomAgent());
				List<LabRoomAgent> agentList =labRoomService.findLabRoomAgentByRoomId(id);
				mav.addObject("agentList",agentList);
				//物联硬件类型
				List<CDictionary> types=cDictionaryService.findallCType();
				mav.addObject("types", types);
				//物联硬件服务器
				Set<CommonServer> serverList=commonServerDAO.findAllCommonServers();
				mav.addObject("serverList",serverList);
				//根据实验分室id查询实验室设备
				List<LabRoomDevice> labDeviceList= labRoomDeviceService.findLabRoomDeviceByRoomId(id);
				mav.addObject("labDeviceList", labDeviceList);
				//设备查询表单对象
				mav.addObject("schoolDevice", schoolDevice);
				//设备信息设置表单对象
				mav.addObject("labRoomDevice", new LabRoomDevice());
				/*//获取所有单选的结果集（是/否）
				Set<CActive> CActives=labRoomService.findAllCActive();
				mav.addObject("CActives", CActives);*/
				/*//预约形式
				Set<CAppointmentType> CAppointmentTypes=cAppointmentTypeDAO.findAllCAppointmentTypes();
				mav.addObject("CAppointmentTypes", CAppointmentTypes);*/
				/*//准入形式
				Set<CLabAccessType> CLabAccessTypes=cLabAccessTypeDAO.findAllCLabAccessTypes();
				mav.addObject("CLabAccessTypes", CLabAccessTypes);*/
				/*//实验室类别
				Set<CLabRoomType> labRoomTypes=cLabRoomTypeDAO.findAllCLabRoomTypes();
				mav.addObject("labRoomTypes", labRoomTypes);*/
				/*//是否可用
				mav.addObject("CActives", labRoomService.findAllCActive());*/
				/*//实验室id
				int annexId=labRoom.getLabAnnex().getId();
				mav.addObject("annexId", annexId);*/
				//门禁
				for (LabRoomAgent a : agentList) {
					if(a.getCDictionary().getId()==548){
						mav.addObject("Access", a);
					}
				}
				mav.addObject("user", user);
				mav.addObject("items", items);
				// 实验室id
				mav.addObject("id",id);
				//实验室禁用时间段列表
				mav.addObject("labRoomLimitTimes", labRoomLimitTimeDAO.executeQuery("select c from LabRoomLimitTime c where c.labId= " + id + " and flag = 0",0,-1));
		mav.addObject("cid", cid);
		mav.setViewName("lab/lab_room/labRoomDetail.jsp");
		return mav;
	}
	
	/****************************************************************************
	 * 功能：保存实验室项目卡
	 * 作者：贺子龙
	 * 时间：2015-09-07
	 ****************************************************************************/
	@RequestMapping("/saveLabRoomOperationItem")
	public ModelAndView saveLabRoomOperationItem(@RequestParam Integer roomId,Integer cid,HttpServletRequest request) {
		ModelAndView mav=new ModelAndView();
		//roomId对应的实验室
		LabRoom room=labRoomService.findLabRoomByPrimaryKey(roomId);
		
		String s=request.getParameter("operationItem");
		String str[]=s.split(",");
		labRoomService.saveLabRoomOperationItem(room,str);
		mav.setViewName("redirect:/labRoom/getLabRoom?id="+roomId+"&cid="+cid);
		return mav;
	}
	
	/****************************************************************************
	 * 功能：删除实验项目卡
	 * 作者：贺子龙
	 * 时间：2015-09-07
	 ****************************************************************************/
	@RequestMapping("/deleteLabRoomOperationItem")
	public ModelAndView deleteLabRoomOperationItem(@RequestParam Integer roomId,Integer id, Integer cid) {
		System.out.println("delete coming in");
		ModelAndView mav=new ModelAndView();
		//roomId对应的实验室
		LabRoom room=labRoomService.findLabRoomByPrimaryKey(roomId);
		//id对应的实验项目卡
		OperationItem m=operationItemDAO.findOperationItemByPrimaryKey(id);
		System.out.println("**********"+m.getLpName());
		labRoomService.deleteLabRoomOperationItem(room,m);
		mav.setViewName("redirect:/labRoom/getLabRoom?id="+roomId+"&cid="+cid);
		return mav;
	}
	
	
	/****************************************************************************
	 * 功能：保存实验室物联硬件
	 * 作者：贺子龙
	 * 时间：2015-09-08
	 ****************************************************************************/
	@RequestMapping("/saveLabRoomAgent")
	public ModelAndView saveLabRoomAgent(@ModelAttribute LabRoomAgent agent,@RequestParam Integer roomId, Integer cid) {
		System.out.println("saveLabRoomAgent coming in");
		ModelAndView mav=new ModelAndView();
		//id对应的实验室
		LabRoom room=labRoomDAO.findLabRoomByPrimaryKey(roomId);
//		System.out.println("实验室号"+roomId);
		agent.setLabRoom(room);
		//物联服务器
		System.out.println(agent.getCommonServer().getId());
		int serverId=agent.getCommonServer().getId();
		System.out.println("物联服务器号"+serverId);
		CommonServer server=commonServerDAO.findCommonServerByPrimaryKey(serverId);
		System.out.println(server.getServerName());
		agent.setCommonServer(server);
//		agent.setId(50);
		labRoomAgentDAO.store(agent);
//		System.out.println(agent+"00000");
		
		mav.setViewName("redirect:/labRoom/getLabRoom?id="+roomId+"&cid="+cid);
		return mav;
	}
	/****************************************************************************
	 * 功能：删除实验室物联硬件
	 * 作者：贺子龙
	 * 时间：2015-09-08
	 ****************************************************************************/
	@RequestMapping("/deleteLabRoomAgent")
	public ModelAndView deleteLabRoomAgent(@RequestParam Integer id) {
		ModelAndView mav=new ModelAndView();
		//id对应的实验室物联硬件
		LabRoomAgent agent=labRoomAgentDAO.findLabRoomAgentByPrimaryKey(id);
		labRoomAgentDAO.remove(agent);
		
		mav.setViewName("redirect:/labRoom/getLabRoom?id="+agent.getLabRoom().getId());
		return mav;
	}
	/****************************************************************************
	 * 功能：修改实验室物联硬件
	 * 作者：贺子龙
	 * 时间：2015-09-08
	 ****************************************************************************/
	@RequestMapping("/updateLabRoomAgent")
	public ModelAndView updateLabRoomAgent(@RequestParam Integer id) {
		ModelAndView mav=new ModelAndView();
		//id对应的实验室物联硬件
		LabRoomAgent agent=labRoomAgentDAO.findLabRoomAgentByPrimaryKey(id);
		mav.addObject("agent", agent);
		//物联硬件类型
		List<CDictionary> types=cDictionaryService.findallCType();
		mav.addObject("types", types);
		//物联硬件服务器
		Set<CommonServer> serverList=commonServerDAO.findAllCommonServers();
		mav.addObject("serverList",serverList);
		
		mav.setViewName("lab/lab_room/updateLabRoomAgent.jsp");
		return mav;
	}
	
	
	/****************************************************************************
	 * 功能：处理ajax中文乱码
	 * 作者：贺子龙
	 * 时间：2015-09-08
	 ****************************************************************************/
	public static String htmlEncode(String str) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < str.length(); i++) {
			int c = (int) str.charAt(i);
			if (c > 127 && c != 160) {
				sb.append("&#").append(c).append(";");
			} else {
				sb.append((char) c);
			}
		}
		return sb.toString();
	}
	
	/****************************************************************************
	 * 功能：AJAX 根据姓名、工号查询当前登录人所在学院的用户
	 * 作者：贺子龙
	 * 时间：2015-09-08
	 ****************************************************************************/
	@RequestMapping("/findUserByCnameAndUsername")
	public @ResponseBody String findUserByCnameAndUsername(@RequestParam String cname,String username,Integer roomId,Integer page) {
		if (cname!=null) {
			
//			cname = java.net.URLDecoder.decode(cname, "UTF-8");// 转成utf-8；
			
		}
		User u=shareService.getUser();
		String academyNumber="";
		if(u.getSchoolAcademy()!=null){
			academyNumber=u.getSchoolAcademy().getAcademyNumber();
		}
		User user=new User();
		user.setCname(cname);
		user.setUsername(username);
		
		//分页开始
		int totalRecords=labRoomService.findUserByUserAndSchoolAcademy(user,roomId,academyNumber);
		int pageSize=20;
		Map<String,Integer> pageModel =shareService.getPage( page,pageSize,totalRecords);
		//根据分页信息查询出来的记录
		List<User> userList=labRoomService.findUserByUserAndSchoolAcademy(user,roomId,academyNumber,page,pageSize);
	    String s="";
	    for (User d : userList) {
	    	String academy="";
	    	if(d.getSchoolAcademy()!=null){
	    		academy=d.getSchoolAcademy().getAcademyName();
	    	}
			s+="<tr>"+
			"<td><input type='checkbox' name='CK_name' value='"+d.getUsername()+"'/></td>"+
	    	"<td>"+d.getCname()+"</td>"+
			"<td>"+d.getUsername()+"</td>"+
			"<td>"+academy+"</td>"+
			
			"</tr>";			
		}
	    s+="<tr><td colspan='6'>"+
	    	    "<a href='javascript:void(0)' onclick='firstPage(1);'>"+"首页"+"</a>&nbsp;"+
	    	    "<a href='javascript:void(0)' onclick='previousPage("+page+");'>"+"上一页"+"</a>&nbsp;"+
	    	    "<a href='javascript:void(0)' onclick='nextPage("+page+","+pageModel.get("totalPage")+");'>"+"下一页"+"</a>&nbsp;"+
	    	    "<a href='javascript:void(0)' onclick='lastPage("+pageModel.get("totalPage")+");'>"+"末页"+"</a>&nbsp;"+
	    	    "当前第"+page+"页&nbsp; 共"+pageModel.get("totalPage")+"页  "+totalRecords+"条记录"+
	    	    		"</td></tr>";
		return htmlEncode(s);
	}
	
	/****************************************************************************
	 * 功能：保存实验室管理员
	 * 作者：贺子龙
	 * 时间：2015-09-08
	 ****************************************************************************/
	@RequestMapping("/saveLabRoomAdmin")
	public ModelAndView saveLabRoomAdmin(@RequestParam Integer roomId,String[] array,Integer typeId, Integer cid) {
		ModelAndView mav=new ModelAndView();
		//roomId对应的实验分室
		LabRoom room=labRoomService.findLabRoomByPrimaryKey(roomId);
		for (String i : array) {
	  		//username对应的用户
			User u=userDAO.findUserByPrimaryKey(i);
			LabRoomAdmin admin=new LabRoomAdmin();
			admin.setLabRoom(room);
			admin.setUser(u);
			admin.setTypeId(typeId);
			labRoomAdminDAO.store(admin);
			if(typeId==3){
				//给用户赋予权限
				Set<Authority> ahths=u.getAuthorities();
				Authority a=authorityDAO.findAuthorityByPrimaryKey(19);//药品柜管理员
				ahths.add(a);
				u.setAuthorities(ahths);
				userDAO.store(u);
			}
			else{
				//给用户赋予权限
				Set<Authority> ahths=u.getAuthorities();
				Authority a=authorityDAO.findAuthorityByPrimaryKey(5);//实验室管理员
				ahths.add(a);
				u.setAuthorities(ahths);
				userDAO.store(u);
			}
	  	}		
		mav.setViewName("redirect:/labRoom/getLabRoom?id="+roomId+"&cid="+cid);
		return mav;
	}
	
	
	/****************************************************************************
	 * 功能：删除实验室管理员
	 * 作者：贺子龙
	 * 时间：2015-09-15
	 ****************************************************************************/
	@RequestMapping("/deleteLabRoomAdmin")
	public ModelAndView deleteLabRoomAdmin(@RequestParam Integer id, Integer cid){
		ModelAndView mav=new ModelAndView();
		//id对应的实验室物联管理员
		LabRoomAdmin admin=labRoomAdminDAO.findLabRoomAdminByPrimaryKey(id);
		labRoomAdminDAO.remove(admin);
		mav.setViewName("redirect:/labRoom/getLabRoom?id="+admin.getLabRoom().getId()+"&cid="+cid);
		return mav;
	}
	
	
	

	
	/****************************************************************************
	 * 功能：AJAX 根据设备名称、设备编号查询当前登录人所在学院的设备
	 * 作者：贺子龙
	 * 时间：2015-09-08
	 ****************************************************************************/
	@RequestMapping("/findSchoolDeviceByNameAndNumber")
	
	public @ResponseBody String findSchoolDeviceByNameAndNumber(@RequestParam String name,String number,String deviceAddress,Integer page,@ModelAttribute("selected_labCenter") Integer cid) {
		System.out.println("findSchoolDeviceByNameAndNumber coming in ");
		
		String academyNumber=shareService.getUser().getSchoolAcademy().getAcademyNumber();
		SchoolDevice schoolDevice=new SchoolDevice();
		schoolDevice.setDeviceName(name);
		schoolDevice.setDeviceNumber(number);
		schoolDevice.setDeviceAddress(deviceAddress);
		/*//设备保管员
		if(keepUser!=null&&!keepUser.equals("")){
			User u=new User();
			u.setCname(keepUser);
			schoolDevice.setUserByKeepUser(u);
		}*/
		//分页开始
		int totalRecords=schoolDeviceService.findSchoolDeviceByAcademyNumberAndSchoolDevice(academyNumber,schoolDevice);
		int pageSize=100;
		Map<String,Integer> pageModel =shareService.getPage( page,pageSize,totalRecords);
		//根据分页信息查询出来的记录
		List<SchoolDevice> deviceList=schoolDeviceService.findSchoolDeviceByAcademyNumberAndSchoolDevice(academyNumber,schoolDevice,page,pageSize);
	    
	    String s="";
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    for (SchoolDevice d : deviceList) {
	    	String time="";//购买日期
	    	if(d.getDeviceBuyDate()!=null){
	    		time=sdf.format(d.getDeviceBuyDate().getTime());
	    	}
	    	String academy="";//部门
	    	if(d.getSchoolAcademy()!=null){
	    		academy=d.getSchoolAcademy().getAcademyName();
	    	}
	    	String cname="";//保管员
	    	if(d.getUserByKeepUser()!=null){
	    		cname=d.getUserByKeepUser().getCname();
	    	}
			s+="<tr>"+
	    	"<td>"+d.getDeviceNumber()+"</td>"+
			"<td>"+d.getDeviceName()+"</td>"+
			"<td>"+d.getDevicePattern()+"</td>"+
			"<td>"+d.getDeviceFormat()+"</td>"+
			"<td>"+d.getDevicePrice()+"</td>"+
			"<td>"+d.getDeviceAddress()+"</td>"+
			"<td><input type='checkbox' name='CK' value='"+d.getDeviceNumber()+"'/></td>"+
			"</tr>";			
		}
	    s+="<tr><td colspan='7'>"+
	    	    "<a href='javascript:void(0)' onclick='first(1);'>"+"首页"+"</a>&nbsp;"+
	    	    "<a href='javascript:void(0)' onclick='previous("+page+");'>"+"上一页"+"</a>&nbsp;"+
	    	    "<a href='javascript:void(0)' onclick='next("+page+","+pageModel.get("totalPage")+");'>"+"下一页"+"</a>&nbsp;"+
	    	    "<a href='javascript:void(0)' onclick='last("+pageModel.get("totalPage")+");'>"+"末页"+"</a>&nbsp;"+
	    	    "当前第"+page+"页&nbsp; 共"+pageModel.get("totalPage")+"页  "+totalRecords+"条记录"+
	    	    		"</td></tr>";
		return htmlEncode(s);
	}
	/****************************************************************************
	 * 功能：保存实验室设备
	 * 作者：贺子龙
	 * 时间：2015-09-08 
	 ****************************************************************************/
	@RequestMapping("/saveLabRoomDevice")
	public ModelAndView saveLabRoomDevice(@RequestParam Integer roomId,String[] array, Integer cid) throws Exception {
		ModelAndView mav=new ModelAndView();
		//roomId对应的实验分室
		LabRoom room=labRoomService.findLabRoomByPrimaryKey(roomId);
		for (String i : array) {
	  		//设备编号对应的设备
			SchoolDevice d=schoolDeviceService.findSchoolDeviceByPrimaryKey(i);
			LabRoomDevice device=new LabRoomDevice();
			device.setLabRoom(room);
			device.setSchoolDevice(d);
			/*//默认为现场培训
			CLabAccessType accessType=cLabAccessTypeDAO.findCLabAccessTypeById(1);
			device.setCLabAccessType(accessType);
			//默认为日历形式
			CAppointmentType type=cAppointmentTypeDAO.findCAppointmentTypeById(2);
			device.setCAppointmentType(type);*/
			device=labRoomDeviceService.save(device);
			//设备二维码
			String url=shareService.getDimensionalCode(device);
			device.setDimensionalCode(url);
			labRoomDeviceService.save(device);
	  	}
		mav.setViewName("redirect:/labRoom/getLabRoom?id="+roomId+"&cid="+cid);
		return mav;
	}
	
	
	/****************************************************************************
	 * 功能：删除实验室设备
	 * 作者：贺子龙
	 * 时间：2015-09-15 
	 ****************************************************************************/
	@RequestMapping("/deleteLabRoomDeviceNew")
	public ModelAndView deleteLabRoomDeviceNew(@RequestParam Integer labDeviceId)  {
		ModelAndView mav=new ModelAndView();
		//id对应的实验室物联管理员
		LabRoomDevice device=labRoomDeviceDAO.findLabRoomDeviceByPrimaryKey(labDeviceId);
		labRoomDeviceDAO.remove(device);
		mav.setViewName("redirect:/labRoom/getLabRoom?id="+device.getLabRoom().getId());
		return mav;
	}
	
	/****************************************************************************
	 * 功能：删除家具
	 * 作者：方正
	 ****************************************************************************/
	@RequestMapping("/deleteLabRoomFurniture")
	public String deleteLabRoomFurniture(@RequestParam Integer i, Integer cid){
		LabRoomFurniture labRoomFurniture = labRoomFurnitureDAO.findLabRoomFurnitureByPrimaryKey(i);
		labRoomFurnitureService.deleteLabRoomFurniture(labRoomFurniture);
		int t = labRoomFurniture.getLabRoom().getId();
		return "redirect:/labRoom/getLabRoom?id="+t+"&cid="+cid;
	}
	
	/****************************************************************************
	 * 功能：添加家具家具
	 * 作者：贺子龙
	 * 时间：2015-09-21 15:05:43
	 ****************************************************************************/
	@RequestMapping("/saveLabRoomFurniture")
	public String saveLabRoomFurniture(@ModelAttribute LabRoomFurniture labRoomFurniture, Integer cid) {
		int i=labRoomFurniture.getLabRoom().getId();
		labRoomFurnitureService.saveLabRoomFurniture(labRoomFurniture);
		return "redirect:/labRoom/getLabRoom?id="+i+"&cid="+cid;
	}
	
	
	/****************************************************************************
	 * 功能：AJAX 根据设备名称、设备编号查询当前登录人所在学院的设备
	 * 作者：李小龙
	 ****************************************************************************/
	@RequestMapping("/openVideo")
	public @ResponseBody String openVideo(@RequestParam Integer roomId,Integer id) {
		//id对应的实验分室
		LabRoom labRoom=labRoomDAO.findLabRoomByPrimaryKey(roomId);
		//物联设备
		LabRoomAgent agent=labRoomAgentDAO.findLabRoomAgentByPrimaryKey(id);
		//根据实验室判断实验室所属学院的视频为一期或者二期
		int time=shareService.getLabRoomBelongsTime(labRoom);
		String url="";
/*		if(time==2){//二期
			System.out.println("-----");
			url="http://"+agent.getHardwareIp()+":"+agent.getHardwarePort()+"/PageCam"+agent.getHardwareRemark();
		}else{//一期
			System.out.println("-++++");
			url="http://"+agent.getCommonServer().getServerIp()+"/webcu/index2.php?id="+agent.getHardwarePort()+"&ip="+agent.getHardwareIp();
		}*/	
		url="http://"+agent.getCommonServer().getServerIp()+"/webcu/index2.htm?ip="+agent.getHardwareIp()+"&id="+agent.getHardwarePort();
		return htmlEncode(url);
	}
	
	
	/****************************************************************************
	 * 功能：AJAX返回门禁的结果
	 * 作者：李小龙
	 * @throws IOException 
	 ****************************************************************************/
	@RequestMapping("/openDoor")
	public @ResponseBody String openDoor(@RequestParam Integer roomId, HttpServletResponse response) throws IOException {
		
		//根据roomId查询该实验室的门禁
		List<LabRoomAgent> agentList=labRoomService.findLabRoomAgentAccessByRoomId(roomId);
		LabRoomAgent a=new LabRoomAgent();
		if(agentList.size()>0){
			a=agentList.get(0);
		}
		String ip=a.getHardwareIp();
		String sn=a.getHardwarePort();
		
		String serverUrl="";//服务器地址
		if(a.getCommonServer()!=null){
			//格式------http://192.168.10.252:8080/services/ofthings/acldoor.asp?cmd=open&ip=
			if(a.getCommonServer().getServerSn()!=null&&!a.getCommonServer().getServerSn().equals("")){
				serverUrl="http://"+a.getCommonServer().getServerIp()+":"+a.getCommonServer().getServerSn()+"/services/ofthings/acldoor.asp?cmd=open&ip="+ip+"&sn="+sn;
			}else{//端口为空
				serverUrl="http://"+a.getCommonServer().getServerIp()+"/services/ofthings/acldoor.asp?cmd=open&ip="+ip+"&sn="+sn;
			}
			
		}
System.out.println("学院物联服务器的地址：" + serverUrl);
		URL url=new URL(serverUrl);
		HttpURLConnection urlConn = (HttpURLConnection) url.openConnection(); 
		// 设置是否向httpUrlConnection输出，因为这个是post请求，参数要放在http正文内，因此需要设为true, 默认情况下是false; 
		urlConn.setDoOutput(true);
		// 设置是否从httpUrlConnection读入，默认情况下是true;
		urlConn.setDoInput(true);
		// Post 请求不能使用缓存
		urlConn.setUseCaches(false);
		// 设定传送的内容类型是可序列化的java对象  
	    // (如果不设此项,在传送序列化对象时,当WEB服务默认的不是这种类型时可能抛java.io.EOFException)
		urlConn.setRequestProperty("Content-type","application/x-java-serialized-object");
		// 设定请求的方法为"POST"，默认是GET
		urlConn.setRequestMethod("POST"); 
		// 连接，上面对urlConn的所有配置必须要在connect之前完成
		try{
			urlConn.connect();
			
		}catch(IOException e){
			return "error";
		}
		
		// 此处getOutputStream会隐含的进行connect (即：如同调用上面的connect()方法，  
	    // 所以在开发中不调用上述的connect()也可以)。
		OutputStream outStrm = urlConn.getOutputStream();
		
	    // 调用HttpURLConnection连接对象的getInputStream()函数,  
	    // 将内存缓冲区中封装好的完整的HTTP请求电文发送到服务端。  
	    InputStream inStrm = urlConn.getInputStream(); // <===注意，实际发送请求的代码段就在这里
	    
	    InputStreamReader  inStream = new InputStreamReader(inStrm,"UTF-8");
	    String inputline="";
	    String info="";//返回的参数
	    BufferedReader buffer=new BufferedReader(inStream);
	    
        while((inputline=buffer.readLine())!=null){
            info+=inputline;
        }
		//System.out.println("返回的参数为："+info);
	    //设置超时时间  
	    HttpURLConnection urlCon = (HttpURLConnection)url.openConnection();  
	    urlCon.setConnectTimeout(30000);  
	    urlCon.setReadTimeout(30000);  
	    if(info.contains("ok")){
	    	return "sucess";
	    }else{
	    	return "error";
	    }
	    
	}
	
	/****************************************************************************
	 * 功能：AJAX返回刷新权限的结果
	 * 作者：李小龙
	 * @throws IOException 
	 ****************************************************************************/
	@RequestMapping("/refreshPermissions")
	public @ResponseBody String refreshPermissions(@RequestParam Integer roomId, HttpServletResponse response) throws IOException {
		
		//根据roomId查询该实验室的门禁
		List<LabRoomAgent> agentList=labRoomService.findLabRoomAgentAccessByRoomId(roomId);
		LabRoomAgent a=new LabRoomAgent();
		if(agentList.size()>0){
			a=agentList.get(0);
		}
		
		String serverUrl="";//服务器地址
		if(a.getCommonServer()!=null){
			//格式------http://192.168.10.252:8080/services/ofthings/acldoor.asp?cmd=registrcard&roomnumber=
			if(a.getCommonServer().getServerSn()!=null&&!a.getCommonServer().getServerSn().equals("")){
				serverUrl="http://"+a.getCommonServer().getServerIp()+":"+a.getCommonServer().getServerSn()+"/services/ofthings/acldoor.asp?cmd=registrcard&roomnumber="+roomId;
			}else{
				serverUrl="http://"+a.getCommonServer().getServerIp()+"/services/ofthings/acldoor.asp?cmd=registrcard&roomnumber="+roomId;
			}
		}		
		System.out.println(serverUrl+"刷新权限");
		URL url=new URL(serverUrl);
		HttpURLConnection urlConn = (HttpURLConnection) url.openConnection(); 
		// 设置是否向httpUrlConnection输出，因为这个是post请求，参数要放在http正文内，因此需要设为true, 默认情况下是false; 
		urlConn.setDoOutput(true);
		// 设置是否从httpUrlConnection读入，默认情况下是true;
		urlConn.setDoInput(true);
		// Post 请求不能使用缓存
		urlConn.setUseCaches(false);
		// 设定传送的内容类型是可序列化的java对象  
	    // (如果不设此项,在传送序列化对象时,当WEB服务默认的不是这种类型时可能抛java.io.EOFException)
		urlConn.setRequestProperty("Content-type","application/x-java-serialized-object");
		// 设定请求的方法为"POST"，默认是GET
		urlConn.setRequestMethod("POST"); 
		// 连接，上面对urlConn的所有配置必须要在connect之前完成
		try{
			urlConn.connect();
		}catch(IOException e){
			return "error";
		}
		
		// 此处getOutputStream会隐含的进行connect (即：如同调用上面的connect()方法，  
	    // 所以在开发中不调用上述的connect()也可以)。
		OutputStream outStrm = urlConn.getOutputStream();
		
	    // 调用HttpURLConnection连接对象的getInputStream()函数,  
	    // 将内存缓冲区中封装好的完整的HTTP请求电文发送到服务端。  
	    InputStream inStrm = urlConn.getInputStream(); // <===注意，实际发送请求的代码段就在这里
	    
	    InputStreamReader  inStream = new InputStreamReader(inStrm,"UTF-8");
	    String inputline="";
	    String info="";//返回的参数
	    BufferedReader buffer=new BufferedReader(inStream);
	    
        while((inputline=buffer.readLine())!=null){
            info+=inputline;
        }
		//System.out.println("返回的参数为："+info);
	    //设置超时时间  
	    HttpURLConnection urlCon = (HttpURLConnection)url.openConnection();  
	    urlCon.setConnectTimeout(30000);  
	    urlCon.setReadTimeout(30000);  
	    if(info.contains("okok")){
	    	return "sucess";
	    }else{
	    	return "error";
	    }
	    
	}
	
	/****************************************************************************
	 * 功能：判断所填写的编号是否与数据库中已有的可用状态实验室编号重复
	 * 作者：贺子龙
	 * 日期：2015-12-23
	 ****************************************************************************/
	@RequestMapping("/testDuplicated")
	public @ResponseBody String testDuplicated(@RequestParam String labRoomNumber, HttpServletResponse response) {
		Set<LabRoom> labRoomNumberDup=labRoomDAO.findLabRoomByLabRoomNumber(labRoomNumber);//根据所填写的labRoomNumber查找数据库中是否有重名
		boolean isDuplicated=false;
		if (labRoomNumberDup.size()==0) {
			//do nothing
		}else {
			for (LabRoom labRoom : labRoomNumberDup) {
				if (labRoom.getIsUsed()==null||
						(labRoom.getIsUsed()!=null&&labRoom.getIsUsed().equals(1))) {//正常使用状态
					isDuplicated=true;break;
				}
			}
		}
		 
	    if(isDuplicated){
	    	return "isDuplicated";
	    }else{
	    	return "isNotDuplicated";
	    }
	    
	}
	/****************************************************************************
	 * 功能：门禁进出记录
	 * 作者：贺子龙
	 * 时间：2015-11-30
	 ****************************************************************************/
	@RequestMapping("/entranceManageForLab")
	public ModelAndView entranceManageForLab(@ModelAttribute LabRoom labRoom, @RequestParam Integer page,@ModelAttribute("selected_labCenter")Integer cid) {
		ModelAndView mav = new ModelAndView();
		//获取当前用户
		User user=shareService.getUser();
		mav.addObject("user", user);
		//查询表单的对象
		mav.addObject("labRoom", labRoom);
		// 设置分页变量并赋值为20
		int pageSize = 15;
		//查询出来的总记录条数
		int totalRecords = labRoomService.findLabRoomBySchoolAcademyDefault(labRoom,1,-1,548,cid).size();
		Map<String, Integer> pageModel = shareService.getPage(page, pageSize, totalRecords);
		//查询框中的实验室
		List<LabRoom> labRoomListAll=labRoomService.findLabRoomBySchoolAcademyDefault(labRoom,1,-1,548,cid);//门禁--548
		mav.addObject("labRoomListAll",labRoomListAll);
		//页面显示的实验室
		List<LabRoom> labRoomList=labRoomService.findLabRoomBySchoolAcademyDefault(labRoom,page,pageSize,548,cid);//门禁--548
		mav.addObject("labRoomList",labRoomList);
		mav.addObject("pageModel",pageModel);
		mav.addObject("totalRecords", totalRecords);
		mav.addObject("page", page);
		mav.addObject("pageSize", pageSize);
		mav.setViewName("lab/lab_record/listEntranceManageForLab.jsp");
		return mav;
	}
	
	
	/*************************************************************************************
	 * @內容：开放实验室资源--门禁
	 * @作者：贺子龙
	 * @日期：2015-12-01
	 *************************************************************************************/
	@RequestMapping("/entranceList")
	public ModelAndView entranceList(@RequestParam Integer id,Integer page,@ModelAttribute CommonHdwlog commonHdwlog,HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		String starttime=request.getParameter("starttime");
		String endtime=request.getParameter("endtime");
		mav.addObject("starttime", starttime);
		mav.addObject("endtime", endtime);
		mav.addObject("commonHdwlog", commonHdwlog);
		mav.addObject("id", id);
		//id对应的物联设备
		LabRoomAgent agent=labRoomAgentDAO.findLabRoomAgentByPrimaryKey(id);
		String ip=agent.getHardwareIp();
		String port=agent.getHardwarePort();
		// 设置分页变量并赋值为20
		//int pageSize = CommonConstantInterface.INT_PAGESIZE;
		int pageSize = 30;
		//查询出来的总记录条数
		int totalRecords = labRoomService.findLabRoomAccessByIpCount(commonHdwlog,ip,port,request);
		Map<String, Integer> pageModel = shareService.getPage(page, pageSize, totalRecords);
		//页面显示的实验室
		List<LabAttendance> accessList=labRoomService.findLabRoomAccessByIp(commonHdwlog,ip,port,page,pageSize,request);
		
		mav.addObject("accessList",accessList);
		mav.addObject("pageModel",pageModel);
		mav.addObject("totalRecords", totalRecords);
		mav.addObject("page", page);
		mav.addObject("pageSize", pageSize);
		mav.setViewName("lab/lab_record/listLabRoomEntrance.jsp");
		return mav;
	}
	
	
	/****************************************************************************
	 * 功能：AJAX 根据姓名、工号查询当前登录人所在学院的用户
	 * 作者：贺子龙
	 * 时间：2015-09-08
	 ****************************************************************************/
	@RequestMapping("/getLabRoomDetail")
	public @ResponseBody String getLabRoomDetail(@RequestParam Integer roomId) {
		//id对应的实验分室信息
		LabRoom labRoom=labRoomDAO.findLabRoomByPrimaryKey(roomId);
		//根据分页信息查询出来的记录
		String s="";
		s+="<tr>"+
				"<td>实验室名称："+labRoom.getLabRoomName()+"</td>"+
				"<tr><td>实验室描述："+labRoom.getLabRoomIntroduction()+"</td><tr>"+
				"<tr><td>实验室注意事项："+labRoom.getLabRoomAttentions()+"</td><tr>"+
				"<tr><td>实验室规章制度："+labRoom.getLabRoomRegulations()+"</td><tr>"+
				"<tr><td>实验室获奖信息："+labRoom.getLabRoomPrizeInformation()+"</td><tr>"+
			"</tr>";			
		return htmlEncode(s);
	}

	/****************************************************************************
	 * 功能：修改实验分室
	 * 作者：李小龙
	 ****************************************************************************/
	@RequestMapping("/appointment/showLabRoom")
	public ModelAndView showLabRoom(@RequestParam Integer id) {
		ModelAndView mav=new ModelAndView();
		//id对应的实验分室
		LabRoom labRoom=labRoomService.findLabRoomByPrimaryKey(id);
		mav.addObject("labRoom", labRoom);	
		mav.setViewName("lab/lab_room/showLabRoom.jsp");
		return mav;
	}
	
	/***********************************************************************************
	 * @description 药品溶液管理-药品申购｛设置物品仓库信息｝
	 * @author 郑昕茹
	 * @date 2016-08-16
	 * **********************************************************************************/
	@RequestMapping("/addAgent")
	public ModelAndView addAgent(@RequestParam int id){
		ModelAndView mav = new ModelAndView();
		LabRoomDevice labRoomDevice = labRoomDeviceService.findLabRoomDeviceByPrimaryKey(id);
		mav.addObject("labRoomDevice", labRoomDevice);
		if(labRoomDevice.getLabRoomAgent() == null){
			mav.addObject("flag", 1);
		}else{
			mav.addObject("flag", 0);
		}
		mav.addObject("listAgents",labRoomAgentDAO.executeQuery("select l from LabRoomAgent l where" +
				" 1=1 and l.labRoom.id="+labRoomDevice.getLabRoom().getId()+" and CDictionary.CNumber = 4", 0, -1));
		mav.addObject("id",id);
		mav.setViewName("lab/lab_room/editLabRoomDeviceAgent.jsp");
		return mav;
		
	}
	
	/***********************************************************************************
	 * @description 药品溶液管理-药品申购｛设置物品仓库信息｝
	 * @author 郑昕茹
	 * @date 2016-08-16
	 * **********************************************************************************/
	@RequestMapping("/saveLabRoomDeviceAgent")
	public ModelAndView saveLabRoomDeviceAgent(@RequestParam int deviceId, HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		LabRoomDevice labRoomDevice = labRoomDeviceService.findLabRoomDeviceByPrimaryKey(deviceId);
		String agentId = request.getParameter("selectAgent");
		LabRoomAgent agent = labRoomAgentDAO.findLabRoomAgentById(Integer.parseInt(agentId));
		labRoomDevice.setLabRoomAgent(agent);
		labRoomDeviceDAO.store(labRoomDevice);
		mav.setViewName("redirect:/labRoom/addAgent?id="+deviceId);
		return mav;
		
	}
	
	/***********************************************************************************
	 * @description 药品溶液管理-药品申购｛设置物品仓库信息｝
	 * @author 郑昕茹
	 * @date 2016-08-16
	 * **********************************************************************************/
	@RequestMapping("/deleteLabRoomDeviceAgent")
	public ModelAndView deleteLabRoomDeviceAgent(@RequestParam int deviceId){
		ModelAndView mav = new ModelAndView();
		LabRoomDevice labRoomDevice = labRoomDeviceService.findLabRoomDeviceByPrimaryKey(deviceId);
		labRoomDevice.setLabRoomAgent(null);
		labRoomDeviceDAO.store(labRoomDevice);
		mav.setViewName("redirect:/labRoom/addAgent?id="+deviceId);
		return mav;
		
	}
	
	
	/**
	 * 实验室列表(App用)
	 * @author hly
	 * 2015.07.28
	 * @throws IOException 
	 */
	@RequestMapping("/listLabRoomApp")
	public void listLabRoomApp(HttpServletResponse response) throws IOException{
		JSONObject jsonObject = new  JSONObject();
		 Configuration config = new Configuration().configure();
        SessionFactory sf     = config.buildSessionFactory();
        Session session = sf.openSession();
        Transaction ts = session.beginTransaction();
        response.setContentType("text/ html；charset=utf-8");
        response.setCharacterEncoding("utf-8"); 
 	   PrintWriter out = response.getWriter();
		String sql = " select id,lab_room_name, lab_room_number, lab_room_address, reservation_number from lab_room"
				+" where is_used = 1 and lab_room_reservation = 1";
		SQLQuery queryList = session.createSQLQuery(sql); //返回对象
        queryList.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        @SuppressWarnings("unchecked")
		List<Map<String, Object>> results = queryList.list();
        JsonConfig jsonConfig = new JsonConfig();
     // 设置javabean中日期转换时的格式
        jsonConfig.registerJsonValueProcessor(Date.class,new JsonDateValueProcessor());
 	    JSONArray jsonArray = JSONArray.fromObject(results,jsonConfig);
 	    //out.println(jsonArray); 
 	    jsonObject.put("labRooms", jsonArray);
 	    out.print(jsonObject);
	}
	
	
	/****************************************************************************
	 * 功能：选择前往的中心 作者：李小龙
	 ****************************************************************************/
	@RequestMapping("/selectCenter")
	public ModelAndView selectCenter(HttpServletRequest request) {
		
		ModelAndView mav = new ModelAndView();
		User user = shareService.getUser();  // 当前登录人

		List<LabCenter> centers = new ArrayList<LabCenter>();
		//已该教师为课程负责人
		/*if(request.getSession().getAttribute("authorityName").equals("SUPERADMIN")){
			centers = labCenterService.findAllLabCenterByQuery(new LabCenter(), 1, -1);
			mav.addObject("user", user);
			mav.addObject("centers", centers);
			mav.setViewName("system/checkCenter.jsp");
			mav.setViewName("lab/selectCenter.jsp");
		}
		else if(request.getSession().getAttribute("authorityName").equals("LABCENTERMANAGER") || request.getSession().getAttribute("authorityName").equals("TEACHER")){
			if(user.getLabCenter() != null)
			{
				mav.setViewName("redirect:/labRoom/listLabRoom?currpage=1&orderBy=9&cid="+user.getLabCenter().getId());
			}
			else{
				mav.setViewName("redirect:/labRoom/listLabRoom?currpage=1&orderBy=9&cid=0");
			}
		}*/
		centers = labCenterService.findAllLabCenterByQuery(new LabCenter(), 1, -1);
		mav.addObject("user", user);
		mav.addObject("centers", centers);
		/*mav.setViewName("system/checkCenter.jsp");*/
		mav.setViewName("lab/selectCenter.jsp");
		/*//西电的逻辑是进系统不再手动选择lab_center，默认选择一个所在学院对应的实验中心
		if (SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString().indexOf("ROLE_SUPERADMIN") != -1){//当用户为超级管理员时，可以查看所有中心
			centers = labCenterService.findAllLabCenterByQuery(new LabCenter(), 1, -1);
		}else{
			SchoolAcademy academy = user.getSchoolAcademy();  // 所属学院
			if (academy != null) 
			{
				centers.addAll(labCenterService.findLabCenterByAcademy(academy.getAcademyNumber()));  // 所属学院下的中心
			}
		}*/
		return mav;
	}
	
	
	
	/****************************************************************************
	 * 功能：删除实验室禁用时间段
	 * 作者：魏诚
	 ****************************************************************************/
	@RequestMapping("/appointment/deleteLabRoomLimitTime")
	public ModelAndView deleteLabRoomLimitTime(@RequestParam Integer id, Integer cid) {
		ModelAndView mav=new ModelAndView();
		//id对应的实验室管理员
		LabRoomLimitTime labRoomLimitTime=labRoomLimitTimeDAO.findLabRoomLimitTimeByPrimaryKey(id);
		
		labRoomLimitTimeDAO.remove(labRoomLimitTime);
		
		mav.setViewName("redirect:/labRoom/getLabRoom?id="+labRoomLimitTime.getLabId()+"&cid="+cid);
		return mav;
	}
	
	
	/****************************************************************************
	 * @功能：保存实验室禁用时间段
	 * @作者：魏诚
	 * @时间：2016-03-05
	 ****************************************************************************/
	@RequestMapping("/appointment/saveLabRoomLimitTime")
	public ModelAndView saveLabRoomLimitTime(HttpServletRequest request, @ModelAttribute LabRoomLimitTime labRoomLimitTime, 
			 Integer cid) throws Exception {
		ModelAndView mav=new ModelAndView();
		
		// 星期可多选
		String[] weekdays = request.getParameterValues("weekday1");
		
		boolean allSelected = false;
		// 如果选择的是全部
		if (weekdays!=null && weekdays.length>0) {
			for (String weekday : weekdays) {
				if(weekday.equals("0")){// 选择的是全部
					allSelected = true;
					break;
				}
			}
		}
		if (allSelected) {
			String[] allWeekdays = {"1","2","3","4","5","6","7"};
			weekdays = allWeekdays;
		}
		
		// 起始和结束周
		String startDate  = request.getParameter("startDate");
		int startweek = shareService.getSchoolWeekByString(startDate).getWeek();
		int startWeekday = shareService.getSchoolWeekByString(startDate).getWeekday();
		String endDate  = request.getParameter("endDate");
		int endweek = shareService.getSchoolWeekByString(endDate).getWeek();
		int endWeekday = shareService.getSchoolWeekByString(startDate).getWeekday();
		// 起始和结束节
		String startTime = request.getParameter("startTime");
		int startclass = shareService.getSystemTimeByString(startTime, cid);
		String endTime = request.getParameter("endTime");
		int endclass = shareService.getSystemTimeByString(endTime, cid);
		
		// 学期
		SchoolTerm term = shareService.getSchoolWeekByString(endDate).getSchoolTerm();
		
		if (weekdays!=null && weekdays.length>0) {
			
			if (endweek - startweek == 0){// 在同一周
				for (String weekday : weekdays){
					for (int i = startWeekday; i <= 7; i++) {
						if (weekday.equals(""+i)) {
							// 设置星期
							labRoomLimitTime.setWeekday(Integer.parseInt(weekday));
							// 设置起始节次
							labRoomLimitTime.setStartclass(startclass);
							// 设置结束节次
							labRoomLimitTime.setEndclass(endclass);
							// 设置起始周次
							labRoomLimitTime.setStartweek(startweek);
							// 设置结束周次							labRoomLimitTime.setEndweek(startweek);
							// 设置学期
							labRoomLimitTime.setSchoolTerm(term);
							// 设置标志位
							labRoomLimitTime.setFlag(0);// 0-手动添加
							labRoomLimitTimeDAO.store(labRoomLimitTime);
						}
					}
				}
			}
			
			if (endweek - startweek>=1) {// 有隔周情况
				
				// 起始周
				for (String weekday : weekdays){
					for (int i = startWeekday; i <= 7; i++) {
						if (weekday.equals(""+i)) {
							// 设置星期
							labRoomLimitTime.setWeekday(Integer.parseInt(weekday));
							// 设置起始节次
							labRoomLimitTime.setStartclass(startclass);
							// 设置结束节次
							labRoomLimitTime.setEndclass(endclass);
							// 设置起始周次
							labRoomLimitTime.setStartweek(startweek);
							// 设置结束周次
							labRoomLimitTime.setEndweek(startweek);
							// 设置学期
							labRoomLimitTime.setSchoolTerm(term);
							// 设置标志位
							labRoomLimitTime.setFlag(0);// 0-手动添加
							labRoomLimitTimeDAO.store(labRoomLimitTime);
						}
					}
				}
				
				// 终止周
				for (String weekday : weekdays){
					for (int i = 1; i < endWeekday; i++) {
						if (weekday.equals(""+i)) {
							// 设置星期
							labRoomLimitTime.setWeekday(Integer.parseInt(weekday));
							// 设置起始节次
							labRoomLimitTime.setStartclass(startclass);
							// 设置结束节次
							labRoomLimitTime.setEndclass(endclass);
							// 设置起始周次
							labRoomLimitTime.setStartweek(endweek);
							// 设置结束周次
							labRoomLimitTime.setEndweek(endweek);
							// 设置学期
							labRoomLimitTime.setSchoolTerm(term);
							// 设置标志位
							labRoomLimitTime.setFlag(0);// 0-手动添加
							labRoomLimitTimeDAO.store(labRoomLimitTime);
						}
					}
				}
				
				if (endweek - startweek>=2) {
					for (String weekday : weekdays) {
						// 设置星期
						labRoomLimitTime.setWeekday(Integer.parseInt(weekday));
						// 设置起始节次
						labRoomLimitTime.setStartclass(startclass);
						// 设置结束节次
						labRoomLimitTime.setEndclass(endclass);
						// 设置起始周次
						labRoomLimitTime.setStartweek(startweek+1);
						// 设置结束周次
						labRoomLimitTime.setEndweek(endweek-1);
						// 设置学期
						labRoomLimitTime.setSchoolTerm(term);
						// 设置标志位
						labRoomLimitTime.setFlag(0);// 0-手动添加
						labRoomLimitTimeDAO.store(labRoomLimitTime);
					}
				}
			}
		}
		mav.setViewName("redirect:/labRoom/getLabRoom?id="+labRoomLimitTime.getLabId()+"&cid="+cid);
		return mav;
	}
	
	
	/**************************************************************
	 *@description:实验室容量与课程的关系
	 * @author：郑昕茹
	 * 2017-05-28
	 ********************************************************************/
	@RequestMapping("/listLabRoomCourseCapacity")
	public ModelAndView listLabRoomCourseCapacity(@RequestParam int currpage, @ModelAttribute LabRoomCourseCapacity labRoomCourseCapacity){
		ModelAndView mav = new ModelAndView();
		int pageSize = CommonConstantInterface.INT_PAGESIZE;
		int totalRecords = labRoomService.findAllLabRoomCourseCapacityByQuery(1, -1, labRoomCourseCapacity).size();
		mav.addObject("listLabRooms",labRoomService.findAllLabRoomByQuery(1, -1, new LabRoom(),9,-1));
		mav.addObject("listSchoolCourseDetails", schoolCourseDetailDAO.executeQuery("select s from SchoolCourseDetail s where 1=1", 0,-1));
		mav.addObject("listLabRoomCourseCapacity", labRoomService.findAllLabRoomCourseCapacityByQuery(currpage, pageSize, labRoomCourseCapacity));
		mav.addObject("pageModel", shareService.getPage(currpage, pageSize, totalRecords));
		mav.addObject("labRoomCourseCapacity", labRoomCourseCapacity);
		mav.setViewName("lab/lab_room/listLabRoomCourseCapacity.jsp");
		return mav;
	}
	
	
	/**************************************************************
	 *@description:新建实验室与课程容量关系
	 * @author：郑昕茹
	 * 2017-05-28
	 ********************************************************************/
	@RequestMapping("/newLabRoomCourseCapacity")
	public ModelAndView newLabRoomCourseCapacity(){
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("labRoomCourseCapacity", new LabRoomCourseCapacity());
		mav.addObject("listLabRooms",labRoomService.findAllLabRoomByQuery(1, -1, new LabRoom(),9,-1));
		mav.addObject("listSchoolCourseDetails", schoolCourseDetailDAO.executeQuery("select s from SchoolCourseDetail s where 1=1", 0,-1));
		mav.setViewName("lab/lab_room/editLabRoomCourseCapacity.jsp");
		return mav;
	}
	
	/**************************************************************
	 *@description:编辑实验室与课程容量关系
	 * @author：郑昕茹
	 * 2017-05-28
	 ********************************************************************/
	@RequestMapping("/editLabRoomCourseCapacity")
	public ModelAndView editLabRoomCourseCapacity(@RequestParam Integer id){
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("labRoomCourseCapacity", labRoomCourseCapacityDAO.findLabRoomCourseCapacityByPrimaryKey(id));
		mav.addObject("listLabRooms",labRoomService.findAllLabRoomByQuery(1, -1, new LabRoom(),9,-1));
		mav.addObject("listSchoolCourseDetails", schoolCourseDetailDAO.executeQuery("select s from SchoolCourseDetail s where 1=1", 0,-1));
		mav.setViewName("lab/lab_room/editLabRoomCourseCapacity.jsp");
		return mav;
	}
	
	
	/**************************************************************
	 *@description:保存实验室与课程容量关系
	 * @author：郑昕茹
	 * 2017-05-28
	 ********************************************************************/
	@RequestMapping("/saveLabRoomCourseCapacity")
	public String saveLabRoomCourseCapacity(@ModelAttribute LabRoomCourseCapacity labRoomCourseCapacity){
		labRoomCourseCapacityDAO.store(labRoomCourseCapacity);
		
		return "redirect:/labRoom/listLabRoomCourseCapacity?currpage=1";
	}
	
	/**************************************************************
	 *@description:删除实验室与课程容量关系
	 * @author：郑昕茹
	 * 2017-05-28
	 ********************************************************************/
	@RequestMapping("/deleteLabRoomCourseCapacity")
	public ModelAndView deleteLabRoomCourseCapacity(@RequestParam Integer id){
		ModelAndView mav = new ModelAndView();
		LabRoomCourseCapacity labRoomCourseCapacity = labRoomCourseCapacityDAO.findLabRoomCourseCapacityByPrimaryKey(id);
		labRoomCourseCapacityDAO.remove(labRoomCourseCapacity);
		labRoomCourseCapacityDAO.flush();
		mav.setViewName("redirect:/labRoom/listLabRoomCourseCapacity?currpage=1");
		return mav;
	}
}
