/****************************************************************************
 * 命名规范：
 * 本控制层调整必须是按照如下的路径调整：@RequestMapping("/visualization/sample")，全部小写。
 * 列表信息listxxxx，獲取信息：getxxxx 编辑信息：editxxxx 删除信息：detelexxxx 新增信息 newxxxx
 * 导出信息exportxxxx 保存信息：savexxxx  
 ****************************************************************************/

package net.xidlims.web.tcoursesite;

import java.net.BindException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.xidlims.dao.CommonServerDAO;
import net.xidlims.dao.LabRoomDAO;
import net.xidlims.domain.CDictionary;
import net.xidlims.domain.CommonDocument;
import net.xidlims.domain.CommonServer;
import net.xidlims.domain.LabRoom;
import net.xidlims.domain.LabRoomAdmin;
import net.xidlims.domain.LabRoomAgent;
import net.xidlims.domain.LabRoomDevice;
import net.xidlims.domain.LabRoomFurniture;
import net.xidlims.domain.OperationItem;
import net.xidlims.domain.SchoolDevice;
import net.xidlims.domain.SystemBuild;
import net.xidlims.domain.SystemRoom;
import net.xidlims.domain.User;
import net.xidlims.domain.WkFolder;
import net.xidlims.service.common.CommonDocumentService;
import net.xidlims.service.common.CommonVideoService;
import net.xidlims.service.common.ShareService;
import net.xidlims.service.device.LabRoomDeviceService;
import net.xidlims.service.device.SchoolDeviceService;
import net.xidlims.service.dictionary.CDictionaryService;
import net.xidlims.service.lab.LabRoomFurnitureService;
import net.xidlims.service.lab.LabRoomService;
import net.xidlims.service.system.SystemBuildService;
import net.xidlims.service.tcoursesite.WkFolderService;
import net.xidlims.service.visualization.VisualizationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


/**************************************************************************
 * Description:可视化-可视化页面的显示
 * 
 * @author：于侃
 * @date ：2016-09-27
 **************************************************************************/

@Controller("TVisualizationController")
public class TVisualizationController<JsonResult> {
	
	@Autowired
	private ShareService shareService;
	@Autowired
	private LabRoomService labRoomService;
	@Autowired
	private SystemBuildService systemBuildService;
	@Autowired
	private LabRoomDeviceService labRoomDeviceService;
	@Autowired
	private VisualizationService visualizationService;
	
	@Autowired CommonDocumentService commonDocumentService;
	@Autowired CommonVideoService commonVideoService;
	@Autowired
	CDictionaryService cDictionaryService;
	@Autowired
	LabRoomDAO labRoomDAO;
	@Autowired
	LabRoomFurnitureService labRoomFurnitureService;
	@Autowired
	CommonServerDAO commonServerDAO;
	@Autowired
	SchoolDeviceService schoolDeviceService;
	@Autowired
	private WkFolderService wkFolderService;
	
	
	@InitBinder
	public void initBinder(WebDataBinder binder, HttpServletRequest request) { // Register static property editors.
		binder.registerCustomEditor(java.util.Calendar.class, new org.skyway.spring.util.databinding.CustomCalendarEditor());
		binder.registerCustomEditor(byte[].class, new org.springframework.web.multipart.support.ByteArrayMultipartFileEditor());
		binder.registerCustomEditor(boolean.class, new org.skyway.spring.util.databinding.EnhancedBooleanEditor(false));
		binder.registerCustomEditor(Boolean.class, new org.skyway.spring.util.databinding.EnhancedBooleanEditor(true));
		binder.registerCustomEditor(java.math.BigDecimal.class, new org.skyway.spring.util.databinding.NaNHandlingNumberEditor(java.math.BigDecimal.class, true));
		binder.registerCustomEditor(Integer.class, new org.skyway.spring.util.databinding.NaNHandlingNumberEditor(Integer.class, true));
		binder.registerCustomEditor(java.util.Date.class, new org.skyway.spring.util.databinding.CustomDateEditor());
		binder.registerCustomEditor(String.class, new org.skyway.spring.util.databinding.StringEditor());
		binder.registerCustomEditor(Long.class, new org.skyway.spring.util.databinding.NaNHandlingNumberEditor(Long.class, true));
		binder.registerCustomEditor(Double.class, new org.skyway.spring.util.databinding.NaNHandlingNumberEditor(Double.class, true));
	}
	
	
	/**************************************************************************
	 * Description:可视化-实验室楼层展示
	 * 
	 * @author：于侃
	 * @date ：2016-09-27
	 **************************************************************************/
	@RequestMapping("/tcoursesite/visualization/roomList")
	public ModelAndView floor(@RequestParam int page,HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		//当前登陆人
		User user = shareService.getUser();
		mav.addObject("user", user);
		String buildNumber = "";
		String floor = "";
		if(request.getParameter("buildNumber")!=null&&request.getParameter("buildNumber")!=""){
			buildNumber = request.getParameter("buildNumber").toString();
		}
		if(request.getParameter("floor")!=null&&request.getParameter("floor")!=""){
			floor = request.getParameter("floor").toString();
		}
		//SchoolTerm schoolTerm = shareService.findNewTerm();  //当前学期
		int pageSize = 10;

		int totalRecords = visualizationService.getLabRoomsByBuildAndFloor(buildNumber,floor,1,-1).size();
		//分页信息
		Map<String,Integer> pageModel =shareService.getPage(page, pageSize,totalRecords);
		
		List<LabRoom> labRooms = visualizationService.getLabRoomsByBuildAndFloor(buildNumber,floor,page,pageSize);
		mav.addObject("labRooms", labRooms); 
		mav.addObject("pageModel",pageModel);
		mav.addObject("totalRecords", totalRecords);
		mav.addObject("page", page);
		mav.addObject("pageSize", pageSize);
		LabRoom labRoom = new LabRoom();
		mav.addObject("labRoom", labRoom); 
		
		List<SystemBuild> builds = systemBuildService.finAllSystemBuilds();
		mav.addObject("builds", builds); 
		mav.addObject("buildNumber", buildNumber); 
		if(buildNumber!=null){
			SystemBuild building = systemBuildService.finSystemBuildById(buildNumber);
			if(building!=null){
				String buildName = building.getBuildName();
				mav.addObject("buildName", buildName); 
			}
		}
		//左侧栏选中项
		mav.addObject("select", "visualization");
		mav.addObject("floor", floor); 
		mav.setViewName("tcoursesite/visualization/roomList.jsp");
		
		return mav;
	}
	/**************************************************************************
	 * Description:可视化-切换楼层
	 * 
	 * @author：于侃
	 * @date ：2016-09-27
	 **************************************************************************/
	@RequestMapping("/tcoursesite/visualization/changeFloor")
	public @ResponseBody
	String changeFloor(@RequestParam String buildNumber,String floor) {
		List<LabRoom> labRooms = visualizationService.getLabRoomsByBuildAndFloor(buildNumber,floor,0,-1);

		String str = "";
		for(LabRoom l:labRooms){
			str = str + " <li style='transform: rotate(50deg); transform-origin: 50% 50% 0px;'>" +
					"<a href='javascript:void(0)' onclick='changeRoom("+l.getId()+")'>" +
							"<span>"+l.getLabRoomNumber()+"</span>&nbsp;&nbsp;"+l.getLabRoomName()+"</a>" +
									"<p style='display:none;'>"+l.getId()+"</p></li>";
		}
		return shareService.htmlEncode(str);
	}
	
	/**************************************************************************
	 * Description:可视化-切换楼层
	 * 
	 * @author：于侃
	 * @date ：2016-09-27
	 **************************************************************************/
	@RequestMapping("/tcoursesite/visualization/changeFloorFirstRoom")
	public @ResponseBody
	String changeFloorFirstRoom(@RequestParam String buildNumber,String floor,HttpServletRequest request) {
		List<LabRoom> labRooms = visualizationService.getLabRoomsByBuildAndFloor(buildNumber,floor,0,-1);
		LabRoom labRoom = new LabRoom();
		String str = "";
		if(labRooms.size()!=0){
			labRoom = labRooms.get(0);
			str = str + "<h2>" + labRoom.getLabRoomNumber()+"&nbsp;&nbsp;"+labRoom.getLabRoomName()+"</h2>";
			str = str + "<div class='banner_info'><p>容量：" + labRoom.getLabRoomCapacity() + "人</p>";
			str = str + "<p>面积：" + labRoom.getLabRoomArea() + "平方米</p><p>状态：";
			/*if(labRoom.getCActiveByLabRoomActive().getId()==1){
				str = str + "可用、";
			}else{
				str = str + "不可用、";
			}
			if(labRoom.getCActiveByLabRoomReservation().getId()==1){
				str = str + "可预约";
			}else{
				str = str + "不可预约";
			}*/
			str = str + "可用、可预约";
			str = str + "</p> <a class='room_info_btn' href='javascript:void(0)' onclick='roomDetails()'> 教室详情</a></div>";
			str = str + "</p> <a class='room_visualization' target='_blank' href='"+request.getContextPath()+"/tcoursesite/visualization/roomImage?id="+labRoom.getId()+"'> 房间可视化</a></div>";
		}

		
		return shareService.htmlEncode(str);
	}
	
	/**************************************************************************
	 * Description:可视化-切换实验室
	 * 
	 * @author：于侃
	 * @date ：2016-09-27
	 **************************************************************************/
	@RequestMapping("/tcoursesite/visualization/changeRoom")
	public @ResponseBody
	String changeRoom(@RequestParam int id,HttpServletRequest request) {
		LabRoom labRoom = visualizationService.findLabRoomByPrimaryKey(id);

		String str = "";
		str = str + "<h2>" + labRoom.getLabRoomNumber()+"&nbsp;&nbsp;"+labRoom.getLabRoomName()+"</h2>";
		str = str + "<div class='banner_info'><p>容量：" + labRoom.getLabRoomCapacity() + "人</p>";
		str = str + "<p>面积：" + labRoom.getLabRoomArea() + "平方米</p><p>状态：";
		/*if(labRoom.getCActiveByLabRoomActive().getId()==1){
			str = str + "可用、";
		}else{
			str = str + "不可用、";
		}
		if(labRoom.getCActiveByLabRoomReservation().getId()==1){
			str = str + "可预约";
		}else{
			str = str + "不可预约";
		}*/
		str = str + "可用、可预约";
		str = str + "</p> <a class='room_info_btn' href='javascript:void(0)' onclick='roomDetails()'> 教室详情</a></div>";
		str = str + "</p> <a class='room_visualization' target='_blank' href='"+request.getContextPath()+"/tcoursesite/visualization/roomImage?id="+labRoom.getId()+"'> 房间可视化</a></div>";
		return shareService.htmlEncode(str);
	}
	
	/**************************************************************************
	 * Description:可视化-切换实验室全景图
	 * 
	 * @author：于侃
	 * @date ：2016-09-27
	 **************************************************************************/
	@RequestMapping("/tcoursesite/visualization/changeRoomPanoramagram")
	public @ResponseBody
	String changeRoomPanoramagram(@RequestParam int id,HttpServletRequest request) {
		LabRoom labRoom = visualizationService.findLabRoomByPrimaryKey(id);

		CommonDocument commonDocument = commonDocumentService.findCommonDocumentByLabRoom(labRoom,3);
		String str = ""+commonDocument.getDocumentUrl()+"";
		return shareService.htmlEncode(str);
	}
	
	/**************************************************************************
	 * Description:可视化-实验室房间展示
	 * 
	 * @author：于侃
	 * @date ：2016-09-27
	 **************************************************************************/
	@RequestMapping("/tcoursesite/visualization/roomImage")
	public ModelAndView roomImage(@RequestParam int id){
		ModelAndView mav = new ModelAndView();
		//当前登陆人
		User user = shareService.getUser();
		mav.addObject("user", user);
		//SchoolTerm schoolTerm = shareService.findNewTerm();  //当前学期
		LabRoom labRoom = visualizationService.findLabRoomByPrimaryKey(id);
		mav.addObject("labRoom", labRoom); 
		
		LabRoomDevice labRoomDevice = new LabRoomDevice();
		mav.addObject("labRoomDevice", labRoomDevice); 
		//此实验室已经标记位置的设备
		List<LabRoomDevice> labRoomDevices = visualizationService.findLabRoomDevicesByLabRoomIdAndXY(id);
		mav.addObject("labRoomDevices", labRoomDevices); 
		//左侧栏选中项
		mav.addObject("select", "visualization");
		
		//所有视频
		List<WkFolder> wkFolders = wkFolderService.findFoldersByType(1);
		mav.addObject("wkFolders", wkFolders);
		
		mav.setViewName("tcoursesite/visualization/roomImage.jsp");
		
		return mav;
	}
	
	/**************************************************************************
	 * Description:可视化-保存实验室设备
	 * 
	 * @author：于侃
	 * @date ：2016-09-27
	 **************************************************************************/
	@RequestMapping("/tcoursesite/visualization/saveDevice")
	public ModelAndView saveDevice(@ModelAttribute LabRoomDevice labRoomDevice){
		ModelAndView mav = new ModelAndView();
		//当前登陆人
		User user = shareService.getUser();
		mav.addObject("user", user);
		//根据id获取实验室设备
		LabRoomDevice device = labRoomDeviceService.findLabRoomDeviceByPrimaryKey(labRoomDevice.getId());
		//保存坐标
		device.setxCoordinate(labRoomDevice.getxCoordinate());
		device.setyCoordinate(labRoomDevice.getyCoordinate());
		//保存设备视频
		device.setqRCodeUrl(labRoomDevice.getqRCodeUrl());
		//保存实验室设备
		visualizationService.saveLabRoomDevice(device);
		int id = labRoomDevice.getLabRoom().getId();
		//左侧栏选中项
		mav.addObject("select", "visualization");
		mav.setViewName("redirect:/tcoursesite/visualization/roomImage?id="+id);
		return mav;
	}
	
	/**************************************************************************
	 * Description:可视化-获取设备信息ajax
	 * 
	 * @author：于侃
	 * @date ：2016-09-27
	 **************************************************************************/
	@RequestMapping("/tcoursesite/visualization/editDevice")
	public @ResponseBody
	Map<String, String> getSchoolClassesUser(@RequestParam int id) {
		LabRoomDevice labRoomDevice = visualizationService.findLabRoomDeviceByPrimaryKey(id);
		//设备信息map
		Map<String, String> map = new HashMap<String, String>();
		map.put("labRoomDeviceId", labRoomDevice.getId().toString());
		map.put("xCoordinate", labRoomDevice.getxCoordinate().toString());
		map.put("yCoordinate", labRoomDevice.getyCoordinate().toString());
		//map.put("deviceNumber", labRoomDevice.getSchoolDevice().getDeviceNumber());
		//map.put("deviceName", labRoomDevice.getSchoolDevice().getDeviceName());
		return map;
	}
	
	/**************************************************************************
	 * Description:可视化-删除实验室设备
	 * 
	 * @author：于侃
	 * @date ：2016-09-27
	 **************************************************************************/
	@RequestMapping("/tcoursesite/visualization/deletDevice")
	public @ResponseBody
	boolean deletDevice(@RequestParam int labRoomDeviceId){
//		LabRoomDevice labRoomDevice = visualizationService.findLabRoomDeviceByPrimaryKey(labRoomDeviceId);
//		int roomId = labRoomDevice.getLabRoom().getId();
		visualizationService.deletLabRoomDeviceXY(labRoomDeviceId);
		boolean b = true;
		return b;
	}
	
	/**************************************************************************
	 * Description:可视化-设置楼宇位置
	 * 
	 * @author：于侃
	 * @date ：2016-09-27
	 **************************************************************************/
	@RequestMapping("/tcoursesite/visualization/setBuildPlace")
	public ModelAndView setBuildPlace(){
		ModelAndView mav = new ModelAndView();
		//当前登陆人
		User user = shareService.getUser();
		mav.addObject("user", user);
		List<SystemBuild> buils = systemBuildService.finAllSystemBuilds();
		mav.addObject("buils", buils); 
		SystemBuild systemBuild = new SystemBuild();
		mav.addObject("systemBuild", systemBuild);
		//左侧栏选中项
		mav.addObject("select", "visualization");
		mav.setViewName("tcoursesite/visualization/setBuildPlace.jsp");
		
		return mav;
	}
	
	/**************************************************************************
	 * Description:可视化-保存楼宇位置
	 * 
	 * @author：于侃
	 * @date ：2016-09-27
	 **************************************************************************/
	@RequestMapping("/tcoursesite/visualization/saveBuild")
	public ModelAndView saveBuild(@ModelAttribute SystemBuild systemBuild){
		ModelAndView mav = new ModelAndView();
		//当前登陆人
		User user = shareService.getUser();
		mav.addObject("user", user);
		SystemBuild build = systemBuildService.finSystemBuildById(systemBuild.getBuildNumber());
		build.setxCoordinate(systemBuild.getxCoordinate());
		build.setyCoordinate(systemBuild.getyCoordinate());
		systemBuildService.saveSystemBuild(build);
		//左侧栏选中项
		mav.addObject("select", "visualization");
		mav.setViewName("redirect:/tcoursesite/visualization/setBuildPlace");
		return mav;
	}

	/**************************************************************************
	 * Description:可视化-获取楼栋ajax
	 * 
	 * @author：于侃
	 * @date ：2016-09-27
	 **************************************************************************/
	@RequestMapping("/tcoursesite/visualization/editBuild")
	public @ResponseBody
	Map<String, String> editBuild(@RequestParam String buildNumber) {
		SystemBuild systemBuild = systemBuildService.finSystemBuildById(buildNumber);
		//楼宇信息map
		Map<String, String> map = new HashMap<String, String>();
		map.put("buildNumber", systemBuild.getBuildNumber());
		map.put("xCoordinate", systemBuild.getxCoordinate().toString());
		map.put("yCoordinate", systemBuild.getyCoordinate().toString());
		return map;
	}
	
	/**************************************************************************
	 * Description:可视化-实验室上传图片
	 * 
	 * @author：于侃
	 * @date ：2016-09-27
	 **************************************************************************/
	@RequestMapping("/tcoursesite/visualization/addLabRoomImage")
	public ModelAndView updateLabRoom(@RequestParam Integer id) {
		ModelAndView mav=new ModelAndView();
		//当前登陆人
		User user = shareService.getUser();
		mav.addObject("user", user);
		//id对应的实验分室
		LabRoom labRoom=labRoomService.findLabRoomByPrimaryKey(id);
		mav.addObject("labRoom", labRoom);
		//左侧栏选中项
		mav.addObject("select", "visualization");
		mav.setViewName("tcoursesite/visualization/addLabRoomImage.jsp");
		return mav;
	}
	
	/**************************************************************************
	 * Description:可视化-保存实验分室
	 * 
	 * @author：于侃
	 * @date ：2016-09-27
	 **************************************************************************/
	@RequestMapping("/tcoursesite/visualization/saveLabRoom")
	public ModelAndView saveLabRoom(@ModelAttribute LabRoom labRoom){
		//新建ModelAndView对象；
		ModelAndView mav=new ModelAndView();
		//当前登陆人
		User user = shareService.getUser();
		mav.addObject("user", user);
		//实验室地点
		String number=labRoom.getSystemRoom().getRoomNumber();
		
		//房间号对应的房间
		SystemRoom sysRoom=visualizationService.findSystemRoomByPrimaryKey(number);
		labRoom.setSystemRoom(sysRoom);	
		//设置实验室地点字段
		labRoom.setLabRoomAddress(number);
		//将实验室保存到数据库
		LabRoom room=visualizationService.save(labRoom);
		mav.addObject("room", room);
		//左侧栏选中项
		mav.addObject("select", "visualization");
		
		mav.setViewName("redirect:/tcoursesite/visualization/roomList?page=1");
		return mav;
	}
	
	/**************************************************************************
	 * Description:可视化-给实验室上传图片
	 * 
	 * @author：于侃
	 * @date ：2016-09-27
	 **************************************************************************/
	@RequestMapping("/tcoursesite/visualization/uploadImageForLabRoom")
	public @ResponseBody String uploadImageForLabRoom(HttpServletRequest request, HttpServletResponse response, BindException errors,Integer id,Integer type) throws Exception {
		visualizationService.uploadImageForLabRoom(request, response,id,type);
		return "ok";
	}
	
	/****************************************************************************
	 * description:删除图片
	 * 
	 * author:于侃
	 * date:2016年10月25日 10:35:32
	 ****************************************************************************/
	@RequestMapping("/tcoursesite/visualization/deleteImageForLabRoom")
	public ModelAndView deleteImageForLabRoom(Integer id,Integer type,HttpServletRequest request){
		visualizationService.deleteImageForLabRoom(id,type,request);
		ModelAndView mav=new ModelAndView();
		//当前登陆人
		User user = shareService.getUser();
		mav.addObject("user", user);
		//id对应的实验分室
		LabRoom labRoom=labRoomService.findLabRoomByPrimaryKey(id);
		mav.addObject("labRoom", labRoom);
		//左侧栏选中项
		mav.addObject("select", "visualization");
		mav.setViewName("tcoursesite/visualization/addLabRoomImage.jsp");
		return mav;
	}
	
	/**************************************************************************
	 * Description:可视化-给实验室添加设备
	 * 
	 * @author：于侃
	 * @date ：2016-09-27
	 **************************************************************************/
	@RequestMapping("/tcoursesite/visualization/addLabRoomDevice")
	public ModelAndView addLabRoomDevice(@RequestParam Integer id,@ModelAttribute SchoolDevice schoolDevice){
		//新建ModelAndView对象；
		ModelAndView mav=new ModelAndView();
		User user=shareService.getUser();
		mav.addObject("user", user);
		boolean flag=labRoomService.getLabRoomAdminReturn(id,user);
		mav.addObject("flag", flag);
		//根据实验分室id查询实验室设备
		List<LabRoomDevice> labDeviceList= labRoomDeviceService.findLabRoomDeviceByRoomId(id);
		mav.addObject("labDeviceList", labDeviceList);
		//设备查询表单对象
		mav.addObject("schoolDevice", schoolDevice);
		//设备信息设置表单对象
		mav.addObject("labRoomDevice", new LabRoomDevice());
		//左侧栏选中项
		mav.addObject("select", "visualization");
		mav.setViewName("tcoursesite/visualization/addLabRoomDevice.jsp");
		return mav;
	}

	/**************************************************************************
	 * Description:可视化-查看实验分室详情
	 * 
	 * @author：于侃
	 * @date ：2016-09-27
	 **************************************************************************/
	@RequestMapping("/tcoursesite/visualization/getLabRoom")
	public ModelAndView getLabRoom(@RequestParam Integer id,@ModelAttribute SchoolDevice schoolDevice){
		//新建ModelAndView对象；
		ModelAndView mav=new ModelAndView();
		User user=shareService.getUser();
		mav.addObject("user", user);
		boolean flag=labRoomService.getLabRoomAdminReturn(id,user);
		mav.addObject("flag", flag);
//		System.out.println(flag);
		//id对应的实验分室信息
		LabRoom labRoom=labRoomDAO.findLabRoomByPrimaryKey(id);
		mav.addObject("labRoom", labRoom);
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
		//左侧栏选中项
		mav.addObject("select", "visualization");
		mav.setViewName("tcoursesite/visualization/labRoomDetail.jsp");
		return mav;
	}

	/**************************************************************************
	 * Description:可视化-保存实验室设备
	 * 
	 * @author：于侃
	 * @date ：2016-09-27
	 **************************************************************************/
	@RequestMapping("/tcoursesite/visualization/saveLabRoomDevice")
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
		mav.setViewName("redirect:/tcoursesite/visualization/getLabRoom?id="+roomId);
		return mav;
	}
}
