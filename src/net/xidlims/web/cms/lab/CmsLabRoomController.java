package net.xidlims.web.cms.lab;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.xidlims.constant.LabAttendance;
import net.xidlims.domain.CommonHdwlog;
import net.xidlims.constant.CommonConstantInterface;
import net.xidlims.dao.AuthorityDAO;
import net.xidlims.dao.CDictionaryDAO;
import net.xidlims.dao.CommonServerDAO;
import net.xidlims.dao.LabRoomAdminDAO;
import net.xidlims.dao.LabRoomAgentDAO;
import net.xidlims.dao.LabRoomDAO;
import net.xidlims.dao.LabRoomDeviceDAO;
import net.xidlims.dao.LabRoomFurnitureDAO;
import net.xidlims.dao.MessageDAO;
import net.xidlims.dao.OperationItemDAO;
import net.xidlims.dao.TimetableSelfCourseDAO;
import net.xidlims.dao.UserDAO;
import net.xidlims.domain.Authority;
import net.xidlims.domain.CDictionary;
import net.xidlims.domain.CommonServer;
import net.xidlims.domain.LabCenter;
import net.xidlims.domain.LabReservation;
import net.xidlims.domain.LabRoom;
import net.xidlims.domain.LabRoomAdmin;
import net.xidlims.domain.LabRoomAgent;
import net.xidlims.domain.LabRoomDevice;
import net.xidlims.domain.LabRoomFurniture;
import net.xidlims.domain.LabWorker;
import net.xidlims.domain.Message;
import net.xidlims.domain.OperationItem;
import net.xidlims.domain.SchoolAcademy;
import net.xidlims.domain.SchoolDevice;
import net.xidlims.domain.TimetableAppointmentSameNumber;
import net.xidlims.domain.User;
import net.xidlims.service.common.ShareService;
import net.xidlims.service.device.LabRoomDeviceService;
import net.xidlims.service.device.SchoolDeviceService;
import net.xidlims.service.dictionary.CDictionaryService;
import net.xidlims.service.lab.LabCenterService;
import net.xidlims.service.lab.LabReservationService;
import net.xidlims.service.lab.LabRoomFurnitureService;
import net.xidlims.service.lab.LabRoomService;
import net.xidlims.service.system.SystemService;

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

import excelTools.Labreservationlist;

import java.net.HttpURLConnection;
import java.net.URL;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.BufferedReader;

@Controller("CmsLabRoomController")
@SessionAttributes("selected_labCenter")
@RequestMapping("/cms/labRoom")
public class CmsLabRoomController<JsonResult> {

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
	private LabRoomDeviceService labRoomDeviceService;
	@Autowired 
	private CommonServerDAO commonServerDAO;
	@Autowired
	private OperationItemDAO operationItemDAO;
	@Autowired
	private CDictionaryDAO cDictionaryDAO;
	@Autowired
	private LabRoomAgentDAO labRoomAgentDAO;
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private LabRoomAdminDAO labRoomAdminDAO;
	@Autowired
	private AuthorityDAO authorityDAO;
	@Autowired
	private SchoolDeviceService schoolDeviceService;
	@Autowired
	private LabRoomDeviceDAO labRoomDeviceDAO;
	@Autowired 
	private LabRoomDAO labRoomDAO;
	@Autowired 
	private LabRoomFurnitureDAO labRoomFurnitureDAO;
	@Autowired 
	private LabRoomFurnitureService labRoomFurnitureService;
	@Autowired 
	private LabReservationService labReservationService;
	@Autowired 
	private TimetableSelfCourseDAO timetableSelfCourseDAO;
	@Autowired 
	private MessageDAO messageDAO;
	
	/**
	 * 实验室列表
	 * @author hly
	 * 2015.07.28
	 */
	@RequestMapping("/listLabRoom")
	public ModelAndView listLabRoom(@RequestParam int currpage, int orderBy, @ModelAttribute LabRoom labRoom, @ModelAttribute("selected_labCenter") Integer cid){
		ModelAndView mav = new ModelAndView();
		int pageSize = CommonConstantInterface.INT_PAGESIZE;
		if(cid!=null && cid>0)
		{
			LabCenter labCenter = new LabCenter();
			labCenter.setId(cid);
			labRoom.setLabCenter(labCenter);
		}
		int totalRecords = labRoomService.findAllLabRoomByQuery(1, -1, labRoom,9).size();
		
		mav.addObject("cid", cid);
		mav.addObject("labRoom", labRoom);
		mav.addObject("pageModel", shareService.getPage(currpage, pageSize, totalRecords));
		mav.addObject("listLabRoom", labRoomService.findAllLabRoomByQuery(currpage, pageSize, labRoom,orderBy));
		//决定升序还是降序
		boolean asc=true;
		if (orderBy<10) {
			asc=false;
		}
		mav.addObject("asc", asc);
		mav.addObject("orderBy", orderBy);
		mav.setViewName("lab/lab_room/listLabRoom.jsp");
		return mav;
	}
	
	/**
	 * 新建实验室
	 * @author hly
	 * 2015.07.28
	 */
	@RequestMapping("/newLabRoom")
	public ModelAndView newLabRoom(@RequestParam int labCenterId){
		ModelAndView mav = new ModelAndView();
		LabCenter labCenter=labCenterService.findLabCenterByPrimaryKey(labCenterId);
		
		LabRoom labRoom = new LabRoom();
		labRoom.setLabRoomTimeCreate(Calendar.getInstance());
		mav.addObject("labCenterName", labCenter.getCenterName());
		mav.addObject("labCenterId", labCenterId);
		mav.addObject("labRoom", labRoom);
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
	public ModelAndView editLabRoom(@RequestParam int labRoomId){
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("labRoom", labRoomService.findLabRoomByPrimaryKey(labRoomId));
		mav.addObject("subject12s", systemService.getAllSystemSubject12(1, -1));  //学科数据(12版)
		mav.addObject("listLabCenter", labCenterService.findAllLabCenterByQuery(new LabCenter(), 1, -1));
		mav.setViewName("lab/lab_room/editLabRoom.jsp");
		return mav;
	}
	
	/**
	 * 保存实验室数据
	 * @author hly
	 * 2015.07.28
	 */
	@RequestMapping("/saveLabRoom")
	public String saveLabRoom(@ModelAttribute LabRoom labRoom){
		labRoomService.saveLabRoom(labRoom);
		
		return "redirect:/labRoom/listLabRoom?currpage=1&orderBy=9";
	}
	
	/**
	 * 删除实验室数据
	 * @author hly
	 * 2015.07.28
	 */
	@RequestMapping("/deleteLabRoom")
	public String deleteLabRoom(@RequestParam int labRoomId){
		LabRoom room=labRoomService.findLabRoomByPrimaryKey(labRoomId);
		room.setIsUsed(0);//假删  
		labRoomService.saveLabRoom(room);
		//labRoomService.deleteLabRoom(labRoomId);  真删  由于和实验项目的外键关系，会报错删不掉
		
		return "redirect:/labRoom/listLabRoom?currpage=1&orderBy=9";
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
	@RequestMapping("/findLabRoomDetail")
	public ModelAndView findLabRoomDetail(@RequestParam Integer labroomId){
		//新建ModelAndView对象；
		ModelAndView mav=new ModelAndView();
		User user=shareService.getUser();
		mav.addObject("user", user);
		boolean flag=labRoomService.getLabRoomAdminReturn(labroomId,user);
		mav.addObject("flag", flag);
		//id对应的实验分室信息
		LabRoom labRoom=labRoomDAO.findLabRoomByPrimaryKey(labroomId);
		mav.addObject("labRoom", labRoom);
		if (user!=null) {
			
			//所有的实验项目卡
			List<OperationItem> items=labRoomService.findAllOperationItem(user.getSchoolAcademy().getAcademyNumber());
			mav.addObject("items", items);
		}
		//实验室家具
		List<LabRoomFurniture> labRoomFurniture = labRoomFurnitureService.findLabRoomFurnitureByRooId(labroomId);
		mav.addObject("labRoomFurniture",labRoomFurniture);
		mav.addObject("labRoomFurnitures", new LabRoomFurniture());
		//根据实验室查询实验室管理员
		List<LabRoomAdmin> adminList =labRoomDeviceService.findLabRoomAdminByRoomId(labroomId,1);
		mav.addObject("adminList", adminList);
		//实验室物联管理员
		List<LabRoomAdmin> agentAdmin =labRoomDeviceService.findLabRoomAdminByRoomId(labroomId,2);
		mav.addObject("agentAdmin", agentAdmin);
		//实验室管理员
		mav.addObject("admin",new LabRoomAdmin());
		//物联硬件
		mav.addObject("agent",new LabRoomAgent());
		List<LabRoomAgent> agentList =labRoomService.findLabRoomAgentByRoomId(labroomId);
		mav.addObject("agentList",agentList);
		//物联硬件类型
		List<CDictionary> types=cDictionaryService.findallCType();
		mav.addObject("types", types);
		//物联硬件服务器
		Set<CommonServer> serverList=commonServerDAO.findAllCommonServers();
		mav.addObject("serverList",serverList);
		//根据实验分室id查询实验室设备
		List<LabRoomDevice> labDeviceList= labRoomDeviceService.findLabRoomDeviceByRoomId(labroomId);
		mav.addObject("labDeviceList", labDeviceList);
		//设备信息设置表单对象
		mav.addObject("labRoomDevice", new LabRoomDevice());
		mav.addObject("schoolDevice", new SchoolDevice());
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
		mav.setViewName("cms/lab/lab_room/labRoomDetail.jsp");
		return mav;
	}
	
	/****************************************************************************
	 * 功能：保存实验室项目卡
	 * 作者：贺子龙
	 * 时间：2015-09-07
	 ****************************************************************************/
	@RequestMapping("/saveLabRoomOperationItem")
	public ModelAndView saveLabRoomOperationItem(@RequestParam Integer roomId,HttpServletRequest request) {
		ModelAndView mav=new ModelAndView();
		//roomId对应的实验室
		LabRoom room=labRoomService.findLabRoomByPrimaryKey(roomId);
		
		String s=request.getParameter("operationItem");
		String str[]=s.split(",");
		labRoomService.saveLabRoomOperationItem(room,str);
		mav.setViewName("redirect:/labRoom/getLabRoom?id="+roomId);
		return mav;
	}
	
	/****************************************************************************
	 * 功能：删除实验项目卡
	 * 作者：贺子龙
	 * 时间：2015-09-07
	 ****************************************************************************/
	@RequestMapping("/deleteLabRoomOperationItem")
	public ModelAndView deleteLabRoomOperationItem(@RequestParam Integer roomId,Integer id) {
		System.out.println("delete coming in");
		ModelAndView mav=new ModelAndView();
		//roomId对应的实验室
		LabRoom room=labRoomService.findLabRoomByPrimaryKey(roomId);
		//id对应的实验项目卡
		OperationItem m=operationItemDAO.findOperationItemByPrimaryKey(id);
		System.out.println("**********"+m.getLpName());
		labRoomService.deleteLabRoomOperationItem(room,m);
		mav.setViewName("redirect:/labRoom/getLabRoom?id="+roomId);
		return mav;
	}
	
	
	/****************************************************************************
	 * 功能：保存实验室物联硬件
	 * 作者：贺子龙
	 * 时间：2015-09-08
	 ****************************************************************************/
	@RequestMapping("/saveLabRoomAgent")
	public ModelAndView saveLabRoomAgent(@ModelAttribute LabRoomAgent agent,@RequestParam Integer roomId) {
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
		
		mav.setViewName("redirect:/labRoom/getLabRoom?id="+roomId);
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
	public ModelAndView saveLabRoomAdmin(@RequestParam Integer roomId,String[] array,Integer typeId) {
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
			//给用户赋予权限
			Set<Authority> ahths=u.getAuthorities();
			Authority a=authorityDAO.findAuthorityByPrimaryKey(10);//设备管理员
			ahths.add(a);
			u.setAuthorities(ahths);
			userDAO.store(u);
	  	}		
		mav.setViewName("redirect:/labRoom/getLabRoom?id="+roomId);
		return mav;
	}
	
	
	/****************************************************************************
	 * 功能：删除实验室管理员
	 * 作者：贺子龙
	 * 时间：2015-09-15
	 ****************************************************************************/
	@RequestMapping("/deleteLabRoomAdmin")
	public ModelAndView deleteLabRoomAdmin(@RequestParam Integer id){
		ModelAndView mav=new ModelAndView();
		//id对应的实验室物联管理员
		LabRoomAdmin admin=labRoomAdminDAO.findLabRoomAdminByPrimaryKey(id);
		labRoomAdminDAO.remove(admin);
		mav.setViewName("redirect:/labRoom/getLabRoom?id="+admin.getLabRoom().getId());
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
		
		String academyNumber=labCenterService.findLabCenterByPrimaryKey(cid).getSchoolAcademy().getAcademyNumber();
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
	public ModelAndView saveLabRoomDevice(@RequestParam Integer roomId,String[] array) throws Exception {
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
		mav.setViewName("redirect:/labRoom/getLabRoom?id="+roomId);
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
	public String deleteLabRoomFurniture(@RequestParam Integer i){
		LabRoomFurniture labRoomFurniture = labRoomFurnitureDAO.findLabRoomFurnitureByPrimaryKey(i);
		labRoomFurnitureService.deleteLabRoomFurniture(labRoomFurniture);
		int t = labRoomFurniture.getLabRoom().getId();
		return "redirect:/labRoom/getLabRoom?id="+t;
	}
	
	/****************************************************************************
	 * 功能：添加家具家具
	 * 作者：贺子龙
	 * 时间：2015-09-21 15:05:43
	 ****************************************************************************/
	@RequestMapping("/saveLabRoomFurniture")
	public String saveLabRoomFurniture(@ModelAttribute LabRoomFurniture labRoomFurniture) {
		int i=labRoomFurniture.getLabRoom().getId();
		labRoomFurnitureService.saveLabRoomFurniture(labRoomFurniture);
		return "redirect:/labRoom/getLabRoom?id="+i;
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
	/*************************************************************************************
	 * @內容：实验室管理
	 * @作者：黄崔俊
	 * @日期：2016-4-20 15:26:59
	 *************************************************************************************/
	@RequestMapping("/labRoomManagement")
	public ModelAndView labRoomManagement(@ModelAttribute LabRoom labRoom,@RequestParam Integer currpage,String username) {
		ModelAndView mav = new ModelAndView();

		//查询实验室预约列表
		List<LabReservation> labReservations= labReservationService.findLabreservationList(1,10);
		List<Labreservationlist> list = new ArrayList<Labreservationlist>();
		for (LabReservation lab : labReservations) {
			Labreservationlist la = new Labreservationlist();
			la.setId(lab.getId());
			if (lab.getCDictionaryByLabReservetYpe() != null) {
				la.setNametype(lab.getCDictionaryByLabReservetYpe().getCName());
			}
			if (lab.getEventName() != null) {
				la.setName(lab.getEventName());
			} else {
				la.setName(lab.getEnvironmentalRequirements());
			}
			Set<String> week = new HashSet<String>();
			Set<String> day = new HashSet<String>();
			Set<String> time = new HashSet<String>();
			if (lab.getTimetableAppointment().getTimetableAppointmentSameNumbers().size() > 0)
				for (TimetableAppointmentSameNumber timetableAppointmentSameNumber : lab.getTimetableAppointment()
						.getTimetableAppointmentSameNumbers()) {
					if (timetableAppointmentSameNumber.getStartWeek().intValue() != timetableAppointmentSameNumber.getEndWeek().intValue()) {
						week.add(timetableAppointmentSameNumber.getStartWeek().toString() + "-"
								+ timetableAppointmentSameNumber.getEndWeek().toString());
					} else {
						week.add(timetableAppointmentSameNumber.getStartWeek().toString());
						
					}
					day.add(lab.getTimetableAppointment().getWeekday().toString());
					if (timetableAppointmentSameNumber.getStartClass().intValue() != timetableAppointmentSameNumber.getEndClass().intValue()) {
						time.add(timetableAppointmentSameNumber.getStartClass().toString() + "-"
								+ timetableAppointmentSameNumber.getEndClass().toString());
					} else {
						time.add(timetableAppointmentSameNumber.getStartClass().toString());
					}
				}
			else {
				if (lab.getTimetableAppointment().getStartWeek().intValue() != lab.getTimetableAppointment().getEndWeek().intValue()) {
					week.add(lab.getTimetableAppointment().getStartWeek().toString() + "-"
							+ lab.getTimetableAppointment().getEndWeek().toString());
				}else{
					week.add(lab.getTimetableAppointment().getStartWeek().toString());
				}
				
				day.add(lab.getTimetableAppointment().getWeekday().toString());
				if (lab.getTimetableAppointment().getStartClass().intValue() != lab.getTimetableAppointment().getEndClass().intValue()) {
					time.add(lab.getTimetableAppointment().getStartClass().toString() + "-"
							+ lab.getTimetableAppointment().getEndClass().toString());
				}else{
					time.add(lab.getTimetableAppointment().getStartClass().toString());
					
				}
				
				
			}
			int dd = week.size();
			String[] weeks = week.toArray(new String[dd]);
			String[] days = day.toArray(new String[day.size()]);
			String[] timea = time.toArray(new String[time.size()]);
			;
			// 数组排序
			String[] weekt = insertSort(weeks);
			String[] timet = insertSort(timea);
			
			la.setWeek(weekt);
			la.setTime(timet);
			la.setDay(days);
			//设置实验室
			if (lab.getLabRoom() != null) {
				la.setLab(lab.getLabRoom().getLabRoomName());
				la.setLabRoom(lab.getLabRoom());
			}
			//设置申请者
			if (lab.getUser() != null) {
				la.setUser(lab.getUser());
			}
			la.setCont(lab.getAuditResults());
			la.setStart(lab.getReservations());
			la.setFabu(lab.getItemReleasese());
			list.add(la);
			
		}
		mav.addObject("labReservations", list);
		
		int pageSize = 8;
		//统计可预约实验室数量
		int totalRecords = labRoomService.countLabRoomListByQuery(labRoom, username);
		//查询可预约实验室列表
		List<LabRoom> labRooms= labRoomService.findLabRoomListByQuery(labRoom, username, currpage, pageSize);
		mav.addObject("labRoom", labRoom);
		mav.addObject("pageModel", shareService.getPage(currpage, pageSize, totalRecords));
		mav.addObject("labRooms", labRooms);
		
		//查询所有的实验室管理员
		List<User> users = labRoomService.findAllLabRoomAdmins(labRoom,1);
		mav.addObject("users", users);
		mav.addObject("username", username);
		User user = shareService.getUser();  // 当前登录人

		List<LabCenter> centers = new ArrayList<LabCenter>();
		if (user==null||SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString().indexOf("ROLE_SUPERADMIN") != -1 || 
				SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString().indexOf("ROLE_SCHOOLLEADER") != -1
				) //当用户为校领导、超级管理员时，可以查看所有中心
		{
			centers = labCenterService.findAllLabCenterByQuery(new LabCenter(), 1, -1);
		} 
		else 
		{
			SchoolAcademy academy = user.getSchoolAcademy();  // 所属学院
			if (academy != null) 
			{
				centers.addAll(academy.getLabCenters());  // 所属学院下的中心
			}
		}
		mav.addObject("centers", centers);
		if (labRoom.getLabCenter()!=null&&labRoom.getLabCenter().getId()!=null) {
			mav.addObject("searchLabRooms", labRoomService.findAllLabRoomByQuery(0, -1, labRoom,-1));
		}
		
		
		mav.addObject("selfCourseList", timetableSelfCourseDAO.executeQuery("select c from TimetableSelfCourse c where c.status=-1"));
		// 查找周次
		mav.addObject("week", labReservationService.getallweek());
		// 查找活动类别
		mav.addObject("activitycategory", labReservationService.getallactivitycategory());
		// 创建对象
		mav.addObject("labReservation", new LabReservation());
		// 学期
		mav.addObject("schoolterm", labReservationService.getschoolterm());
		// 当前学期
		mav.addObject("currTerm", shareService.getBelongsSchoolTerm(Calendar.getInstance()));
		mav.setViewName("cms/lab/lab_room/labRoomManagement.jsp");
		return mav;
	}
	/*************************************************************************************
	 * @內容:根据实验室中心查询实验室列表
	 * @作者：黄崔俊
	 * @日期：2016-5-4 14:40:03
	 *************************************************************************************/
	@RequestMapping("/findLabRoomByCenter")
	@ResponseBody
	public List<LabRoom> findLabRoomByCenter(@RequestParam Integer cid) {

		//查询实验室中心
		LabCenter labCenter = labCenterService.findLabCenterByPrimaryKey(cid);
		LabRoom labRoom = new LabRoom();
		labRoom.setLabCenter(labCenter);
		//查询该实验室中心下所有可用的实验室
		List<LabRoom> labRooms = labRoomService.findAllLabRoomByQuery(0, -1, labRoom,-1); 
		return labRooms;
	}
	/*************************************************************************************
	 * @內容：根据实验室查询实验室管理员列表
	 * @作者：黄崔俊
	 * @日期：2016-5-4 14:53:51
	 *************************************************************************************/
	@RequestMapping("/findLabRoomAdminsByLabRoom")
	@ResponseBody
	public Map<String, String> findLabRoomAdminsByLabRoom(@RequestParam Integer labroomId) {
		
		//查询实验室中心
		LabRoom labRoom = labRoomService.findLabRoomByPrimaryKey(labroomId);
		List<User> users = labRoomService.findAllLabRoomAdmins(labRoom, 1);
		Map<String, String> map = new HashMap<String, String>();
		for (User user : users) {
			map.put(user.getUsername(), user.getCname());
		}
		return map;
	}
	
	public static String[] insertSort(String[] weeks) {// 插入排序算法
		for (int i = 1; i < weeks.length; i++) {
			for (int j = i; j > 0; j--) {
                String start =weeks[j];
                String end =weeks[j-1];
                if(start.indexOf("-")!=-1){
                	start = start.substring(start.indexOf("-"));
                }
                if(end.indexOf("-")!=-1){
                	end = end.substring(end.indexOf("-"));
                }
                
				if (Integer.parseInt(start) < Integer.parseInt(end)) {
					String temp = weeks[j - 1];
					weeks[j - 1] = weeks[j];
					weeks[j] = temp;
				} else
					break;
			}
		}
		return weeks;
	}
	
	@RequestMapping("/savelabreservation")
	public String savelabreservation(@ModelAttribute LabReservation labReservation, HttpServletRequest request) throws ParseException {
		System.out.println("savelabreservation coming in");
		LabReservation labReservationII=labReservationService.saveLabReservationProc(labReservation, request);
		//预约完成后向审核人发送消息
		
		//预约的实验室有几个管理员就要生成几条消息(如果申请人本身也是管理员，则不发送给自己)
		Set<LabRoomAdmin> labRoomAdmins = labReservation.getLabRoom().getLabRoomAdmins();
		int messageCount = labRoomAdmins.size();
		
		
		for (LabRoomAdmin labRoomAdmin : labRoomAdmins) {
			if (labRoomAdmin.getUser().getUsername().equals(shareService.getUser().getUsername())) {
				
			}else {//如果自己也是管理员，不给自己发
				Message message= new Message();
				Calendar date=Calendar.getInstance();
				message.setSendUser(shareService.getUserDetail().getCname());
				message.setSendCparty(shareService.getUserDetail().getSchoolAcademy().getAcademyName());
				message.setCond(0);
				message.setTitle(CommonConstantInterface.STR_LABROOM_TITLE);
				String content="申请成功，等待审核";
				content+="<a  href='/xidlims/lab/checkButton?idkey=";
				content+=labReservationII.getId();
				content+="&tage=0'>点击查看</a>";
				message.setContent(content);
				message.setMessageState(CommonConstantInterface.INT_Flag_ZERO);
				message.setUsername(labRoomAdmin.getUser().getUsername());
				message.setCreateTime(date);
				message=messageDAO.store(message);
			}
		}
		
		return "redirect:/cms/labRoom/labRoomManagement?currpage=1";
	}
}
