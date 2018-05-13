/****************************************************************************
 * 命名规范：
 * 本控制层调整必须是按照如下的路径调整：@RequestMapping("/visualization/sample")，全部小写。
 * 列表信息listxxxx，獲取信息：getxxxx 编辑信息：editxxxx 删除信息：detelexxxx 新增信息 newxxxx
 * 导出信息exportxxxx 保存信息：savexxxx  
 ****************************************************************************/

package net.xidlims.web.visualization;

import java.io.File;
import java.net.BindException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.xidlims.service.visualization.VisualizationService;
import net.xidlims.service.common.CommonDocumentService;
import net.xidlims.service.common.CommonVideoService;
import net.xidlims.service.common.ShareService;
import net.xidlims.service.device.LabRoomDeviceService;
import net.xidlims.service.lab.LabRoomFurnitureService;
import net.xidlims.service.lab.LabRoomService;
import net.xidlims.service.system.SystemBuildService;
import net.xidlims.service.system.SystemService;
import net.xidlims.service.tcoursesite.WkFolderService;
import net.xidlims.dao.LabRoomDAO;
import net.xidlims.domain.CDictionary;
import net.xidlims.domain.CommonDocument;
import net.xidlims.domain.CommonServer;
import net.xidlims.domain.LabRoomAdmin;
import net.xidlims.domain.LabRoomAgent;
import net.xidlims.domain.LabRoomDevice;
import net.xidlims.domain.LabRoomFurniture;
import net.xidlims.domain.OperationItem;
import net.xidlims.domain.SchoolAcademy;
import net.xidlims.domain.SchoolDevice;
import net.xidlims.domain.SchoolTerm;
import net.xidlims.domain.LabRoom;
import net.xidlims.domain.SystemBuild;
import net.xidlims.domain.SystemCampus;
import net.xidlims.domain.SystemRoom;
import net.xidlims.domain.User;
import net.xidlims.domain.WkFolder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;



/****************************************************************************
 * 功能：可视化页面的显示
 * 可视化模块 作者：何立友 时间：2014-08-21
 ****************************************************************************/
@Controller("VisualizationController")
public class visualizationController<JsonResult> {
	
	@Autowired
	private ShareService shareService;
	@Autowired
	private LabRoomService labRoomService;
	@Autowired
	private LabRoomDAO labRoomDAO;
	@Autowired
	private SystemBuildService systemBuildService;
	@Autowired
	private LabRoomFurnitureService labRoomFurnitureService;
	@Autowired
	private LabRoomDeviceService labRoomDeviceService;
	@Autowired
	private VisualizationService visualizationService;
	@Autowired
	private SystemService systemService;
	@Autowired CommonDocumentService commonDocumentService;
	@Autowired CommonVideoService commonVideoService;
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
	
	
	/****************************************************************************
	 * 中医药实验室楼层展示
	 * 裴继超
	 * 2016年1月23日
	 ****************************************************************************/
	@RequestMapping("/visualization/roomList")
	public ModelAndView floor(@RequestParam int page,HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		
		String buildNumber = "";
		String floor = "";
		if(request.getParameter("buildNumber")!=null&&request.getParameter("buildNumber")!=""){
			buildNumber = request.getParameter("buildNumber").toString();
		}
		if(request.getParameter("floor")!=null&&request.getParameter("floor")!=""){
			floor = request.getParameter("floor").toString();
		}
		SchoolTerm schoolTerm = shareService.findNewTerm();  //当前学期
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
		mav.addObject("floor", floor); 
		mav.setViewName("visualization/roomList.jsp");
		
		return mav;
	}
	/************************************************************
	 * @可视化-切换楼层
	 * @作者：裴继超
	 * @日期：2016年1月22日
	 ************************************************************/
	@RequestMapping("/visualization/changeFloor")
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
	
	/************************************************************
	 * @可视化-切换楼层
	 * @作者：裴继超
	 * @日期：2016年1月22日
	 ************************************************************/
	@RequestMapping("/visualization/changeFloorFirstRoom")
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
			str = str + "</p> <a class='room_visualization' target='_blank' href='"+request.getContextPath()+"/visualization/roomImage?id="+labRoom.getId()+"'> 房间可视化</a></div>";
		}

		
		return shareService.htmlEncode(str);
	}
	
	/************************************************************
	 * @可视化-切换实验室
	 * @作者：裴继超
	 * @日期：2016年1月23日
	 ************************************************************/
	@RequestMapping("/visualization/changeRoom")
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
		str = str + "</p> <a class='room_visualization' target='_blank' href='"+request.getContextPath()+"/visualization/roomImage?id="+labRoom.getId()+"'> 房间可视化</a></div>";
		return shareService.htmlEncode(str);
	}
	
	/************************************************************
	 * @可视化-切换实验室全景图
	 * @作者：裴继超
	 * @日期：2016年3月14日
	 ************************************************************/
	@RequestMapping("/visualization/changeRoomPanoramagram")
	public @ResponseBody
	String changeRoomPanoramagram(@RequestParam int id,HttpServletRequest request) {
		LabRoom labRoom = visualizationService.findLabRoomByPrimaryKey(id);

		CommonDocument commonDocument = commonDocumentService.findCommonDocumentByLabRoom(labRoom,3);
		String str = ""+commonDocument.getDocumentUrl()+"";
		return shareService.htmlEncode(str);
	}
	
	/****************************************************************************
	 * 中医药实验室房间展示
	 * 裴继超
	 * 2016年1月23日
	 ****************************************************************************/
	@RequestMapping("/visualization/roomImage")
	public ModelAndView roomImage(@RequestParam int id){
		ModelAndView mav = new ModelAndView();
		SchoolTerm schoolTerm = shareService.findNewTerm();  //当前学期
		LabRoom labRoom = visualizationService.findLabRoomByPrimaryKey(id);
		mav.addObject("labRoom", labRoom); 
		
		LabRoomDevice labRoomDevice = new LabRoomDevice();
		mav.addObject("labRoomDevice", labRoomDevice); 
		//此实验室已经标记位置的设备
		List<LabRoomDevice> labRoomDevices = visualizationService.findLabRoomDevicesByLabRoomIdAndXY(id);
		mav.addObject("labRoomDevices", labRoomDevices);
		
		//所有视频
		List<WkFolder> wkFolders = wkFolderService.findFoldersByType(1);
		mav.addObject("wkFolders", wkFolders);
		
		mav.setViewName("visualization/roomImage.jsp");
		
		return mav;
	}
	
	/****************************************************************************
	 * 保存实验室设备
	 * 裴继超
	 * 2016年1月27日
	 ****************************************************************************/
	@RequestMapping("/visualization/saveDevice")
	public ModelAndView saveDevice(@ModelAttribute LabRoomDevice labRoomDevice){
		ModelAndView mav = new ModelAndView();
		//根据id获取实验室设备
		LabRoomDevice device = labRoomDeviceService.findLabRoomDeviceByPrimaryKey(labRoomDevice.getId());
		//保存坐标
		device.setxCoordinate(labRoomDevice.getxCoordinate());
		device.setyCoordinate(labRoomDevice.getyCoordinate());
		//保存视频二维码路径
		device.setqRCodeUrl(labRoomDevice.getqRCodeUrl());
		//保存实验室设备
		visualizationService.saveLabRoomDevice(device);
		int id = labRoomDevice.getLabRoom().getId();
		mav.setViewName("redirect:/visualization/roomImage?id="+id);
		return mav;
	}
	
	/************************************************************
	 * @功能-获取设备信息ajax
	 * @作者：裴继超
	 * @日期：2016年1月27日
	 ************************************************************/
	@RequestMapping("/visualization/editDevice")
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
	
	/****************************************************************************
	 * 删除实验室设备
	 * 裴继超
	 * 2016年1月28日
	 ****************************************************************************/
	@RequestMapping("/visualization/deletDevice")
	public @ResponseBody
	boolean deletDevice(@RequestParam int labRoomDeviceId){
		LabRoomDevice labRoomDevice = visualizationService.findLabRoomDeviceByPrimaryKey(labRoomDeviceId);
		int roomId = labRoomDevice.getLabRoom().getId();
		visualizationService.deletLabRoomDeviceXY(labRoomDeviceId);
		boolean b = true;
		return b;
	}
	
	/****************************************************************************
	 * 设置楼宇位置
	 * 裴继超
	 * 2016年3月22日
	 ****************************************************************************/
	@RequestMapping("/visualization/setBuildPlace")
	public ModelAndView setBuildPlace(){
		ModelAndView mav = new ModelAndView();
		List<SystemBuild> buils = systemBuildService.finAllSystemBuilds();
		mav.addObject("buils", buils); 
		SystemBuild systemBuild = new SystemBuild();
		mav.addObject("systemBuild", systemBuild); 
		mav.setViewName("visualization/setBuildPlace.jsp");
		
		return mav;
	}
	
	/****************************************************************************
	 * 保存楼宇位置
	 * 裴继超
	 * 2016年3月22日
	 ****************************************************************************/
	@RequestMapping("/visualization/saveBuild")
	public ModelAndView saveBuild(@ModelAttribute SystemBuild systemBuild){
		ModelAndView mav = new ModelAndView();
		SystemBuild build = systemBuildService.finSystemBuildById(systemBuild.getBuildNumber());
		build.setxCoordinate(systemBuild.getxCoordinate());
		build.setyCoordinate(systemBuild.getyCoordinate());
		systemBuildService.saveSystemBuild(build);
		mav.setViewName("redirect:/visualization/setBuildPlace");
		return mav;
	}

	/************************************************************
	 * @功能-获取楼栋ajax
	 * @作者：裴继超
	 * @日期：2016年1月27日
	 ************************************************************/
	@RequestMapping("/visualization/editBuild")
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
	
	/****************************************************************************
	 * 功能：实验室上传图片
	 * 作者：裴继超
	 * 时间：2016年5月27日10:53:11
	 ****************************************************************************/
	@RequestMapping("/visualization/addLabRoomImage")
	public ModelAndView updateLabRoom(@RequestParam Integer id) {
		ModelAndView mav=new ModelAndView();
		//id对应的实验分室
		LabRoom labRoom=labRoomService.findLabRoomByPrimaryKey(id);
		mav.addObject("labRoom", labRoom);
		List<SystemCampus> campusList= systemService.getAllSystemCampus(1,-1);
		mav.addObject("campusList", campusList);
		mav.setViewName("visualization/addLabRoomImage.jsp");
		return mav;
	}
	
	/****************************************************************************
	 * 功能：保存实验分室
	 * 作者：李小龙
	 * 时间：2014-07-29
	 ****************************************************************************/
	@RequestMapping("/visualization/saveLabRoom")
	public ModelAndView saveLabRoom(@ModelAttribute LabRoom labRoom){
		//新建ModelAndView对象；
		ModelAndView mav=new ModelAndView();
		int labRoomId = labRoom.getId();
		//获取之前的实验室
		LabRoom oldLabRoom = labRoomService.findLabRoomByPrimaryKey(labRoomId);
		//实验室地点
		String number=labRoom.getSystemRoom().getRoomNumber();
		 
		//房间号对应的房间
		SystemRoom sysRoom=visualizationService.findSystemRoomByPrimaryKey(number);
		oldLabRoom.setSystemRoom(sysRoom);	
		//设置实验室地点字段
		oldLabRoom.setLabRoomAddress(number);
		//将实验室保存到数据库
		LabRoom room=visualizationService.save(oldLabRoom);
		mav.addObject("room", room);
		
		
		mav.setViewName("redirect:/visualization/roomList?page=1");
		return mav;
	}
	
	/****************************************************************************
	 * 功能：给实验室上传图片
	 * 作者：裴继超
	 * 时间：2016年5月27日
	 ****************************************************************************/
	@RequestMapping("/visualization/uploadImageForLabRoom")
	public @ResponseBody String uploadImageForLabRoom(HttpServletRequest request, HttpServletResponse response, BindException errors,Integer id,Integer type) throws Exception {
		visualizationService.uploadImageForLabRoom(request, response,id,type);
		return "ok";
	}
	
	/****************************************************************************
	 * 功能：给实验室添加设备
	 * 作者：裴继超
	 * 时间：2016年5月27日
	 ****************************************************************************/
	@RequestMapping("visualization/addLabRoomDevice")
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
		mav.setViewName("visualization/addLabRoomDevice.jsp");
		return mav;
	}

	/****************************************************************************
	 * description:删除图片
	 * 
	 * author:于侃
	 * date:2016年09月21日
	 ****************************************************************************/
	@RequestMapping("/visualization/deleteImageForLabRoom")
	public ModelAndView deleteImageForLabRoom(Integer id,Integer type,HttpServletRequest request){
		visualizationService.deleteImageForLabRoom(id,type,request);
		ModelAndView mav=new ModelAndView();
		//id对应的实验分室
		LabRoom labRoom=labRoomService.findLabRoomByPrimaryKey(id);
		mav.addObject("labRoom", labRoom);
		mav.setViewName("visualization/addLabRoomImage.jsp");
		return mav;
	}
	
	/****************************************************************************
	 * description:下载图片
	 * 
	 * author:于侃
	 * date:2016年09月21日
	 ****************************************************************************/
	@RequestMapping("/visualization/downloadImageForLabRoom")
	public String downloadImageForLabRoom(Integer id,HttpServletRequest request,HttpServletResponse response){
		visualizationService.downloadImageForLabRoom(id,request,response);
		return null;
	}
}
