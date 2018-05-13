/****************************************************************************
 * 命名规范：
 * 本控制层调整必须是按照如下的路径调整：@RequestMapping("/visualization/sample")，全部小写。
 * 列表信息listxxxx，獲取信息：getxxxx 编辑信息：editxxxx 删除信息：detelexxxx 新增信息 newxxxx
 * 导出信息exportxxxx 保存信息：savexxxx  
 ****************************************************************************/

package net.xidlims.web.visualization;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.xidlims.service.system.SystemBuildService;
import net.xidlims.service.visualization.VisualizationService;
import net.xidlims.service.common.CommonDocumentService;
import net.xidlims.service.common.CommonVideoService;
import net.xidlims.service.common.ShareService;
import net.xidlims.service.device.LabRoomDeviceService;
import net.xidlims.service.lab.LabRoomFurnitureService;
import net.xidlims.service.lab.LabRoomService;
import net.xidlims.dao.LabRoomDAO;
import net.xidlims.dao.SystemBuildDAO;
import net.xidlims.domain.CommonDocument;
import net.xidlims.domain.LabRoomAdmin;
import net.xidlims.domain.LabRoomAgent;
import net.xidlims.domain.LabRoomDevice;
import net.xidlims.domain.LabRoomFurniture;
import net.xidlims.domain.SchoolAcademy;
import net.xidlims.domain.SchoolTerm;
import net.xidlims.domain.LabRoom;
import net.xidlims.domain.SystemBuild;
import net.xidlims.domain.SystemRoom;
import net.xidlims.domain.User;

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
@Controller("VisualizationShowController")
public class visualizationShowController<JsonResult> {
	
	@Autowired
	private ShareService shareService;
	@Autowired
	private LabRoomService labRoomService;
	@Autowired
	private LabRoomDAO labRoomDAO;
	@Autowired
	private LabRoomFurnitureService labRoomFurnitureService;
	@Autowired
	private LabRoomDeviceService labRoomDeviceService;
	@Autowired
	private VisualizationService visualizationService;
	
	@Autowired CommonDocumentService commonDocumentService;
	@Autowired CommonVideoService commonVideoService;
	@Autowired
	private SystemBuildService systemBuildService;
	
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
	 * 可视化首页展示
	 * 裴继超
	 * 2016年1月23日
	 ****************************************************************************/
	@RequestMapping("/visualization/show/index")
	public ModelAndView buildPlace(){
		ModelAndView mav = new ModelAndView();
		List<SystemBuild> buils = systemBuildService.finAllSystemBuilds();
		mav.addObject("buils", buils); 
		//当前登录人
		User user = shareService.getUser();
		mav.addObject("user", user);
		mav.setViewName("visualization/show/index.jsp");
		
		return mav;
	}
	
	/****************************************************************************
	 * 中医药实验室楼层展示
	 * 裴继超
	 * 2016年1月23日
	 ****************************************************************************/
	@RequestMapping("/visualization/show/floor")
	public ModelAndView floor(@RequestParam String buildNumber){
		ModelAndView mav = new ModelAndView();
		
		SchoolTerm schoolTerm = shareService.findNewTerm();  //当前学期
		List<LabRoom> labRooms = visualizationService.getLabRoomsByBuildAndFloor(buildNumber,"1",1,-1);
		mav.addObject("labRooms", labRooms); 
		if(labRooms.size()!=0){
			mav.addObject("labRoom", labRooms.get(0)); 
		}
		mav.addObject("buildNumber", buildNumber); 
		List<SystemBuild> systemBuilds = systemBuildService.findBuildingByXY();
		mav.addObject("systemBuilds", systemBuilds); 
		mav.addObject("records", systemBuildService.findBuildingbyBuildNumber(buildNumber).getFloorNum());
		//mav.addObject("buildings", buildings); 
		
		mav.setViewName("visualization/show/floor.jsp");
		
		return mav;
	}
	/****************************************************************************
	 * 中医药实验室楼层展示
	 * 裴继超
	 * 2016年1月23日
	 ****************************************************************************/
	@RequestMapping("/visualization/show/roomImage")
	public ModelAndView roomImage(@RequestParam int id){
		ModelAndView mav = new ModelAndView();
		LabRoom labRoom = visualizationService.findLabRoomByPrimaryKey(id);
		mav.addObject("labRoom", labRoom); 
		
		LabRoomDevice labRoomDevice = new LabRoomDevice();
		mav.addObject("labRoomDevice", labRoomDevice); 
		//此实验室已经标记位置的设备
		List<LabRoomDevice> labRoomDevices = visualizationService.findLabRoomDevicesByLabRoomIdAndXY(id);
		mav.addObject("labRoomDevices", labRoomDevices); 
		mav.setViewName("visualization/show/roomImage.jsp");
		
		return mav;
	}
	
	/************************************************************
	 * @功能-获取设备信息ajax
	 * @作者：裴继超
	 * @日期：2016年1月27日
	 ************************************************************/
	@RequestMapping("/visualization/show/lookDevice")
	public @ResponseBody
	Map<String, String> lookDevice(@RequestParam int id) {
		LabRoomDevice labRoomDevice = visualizationService.findLabRoomDeviceByPrimaryKey(id);
		//设备信息map
		Map<String, String> map = new HashMap<String, String>();
		map.put("labRoomDeviceId", labRoomDevice.getId().toString());
		map.put("xCoordinate", labRoomDevice.getxCoordinate().toString());
		map.put("yCoordinate", labRoomDevice.getyCoordinate().toString());
		map.put("deviceNumber", labRoomDevice.getSchoolDevice().getDeviceNumber());
		map.put("deviceName", labRoomDevice.getSchoolDevice().getDeviceName());
		map.put("deviceFormat", (labRoomDevice.getSchoolDevice().getDeviceFormat()==null)?"":labRoomDevice.getSchoolDevice().getDeviceFormat());
		map.put("deviceSupplier", (labRoomDevice.getSchoolDevice().getDeviceSupplier()==null)?"":labRoomDevice.getSchoolDevice().getDeviceSupplier());
		map.put("devicePattern", (labRoomDevice.getSchoolDevice().getDevicePattern()==null)?"":labRoomDevice.getSchoolDevice().getDevicePattern());
		map.put("qRCodeUrl", (labRoomDevice.getqRCodeUrl()==null)?"":labRoomDevice.getqRCodeUrl());
		map.put("dimensionalCode", (labRoomDevice.getDimensionalCode()==null)?"":labRoomDevice.getDimensionalCode());
		if(labRoomDevice.getSchoolDevice().getSchoolAcademy()!=null){
			map.put("academyName", labRoomDevice.getSchoolDevice().getSchoolAcademy().getAcademyName());
		}else{
			map.put("academyName", "");
		}
		
		//map.put("devicePattern", labRoomDevice.getSchoolDevice().getDevicePattern());
		return map;
	}
	
	/************************************************************
	 * @可视化-切换楼层
	 * @作者：裴继超
	 * @日期：2016年1月22日
	 ************************************************************/
	@RequestMapping("/visualization/show/changeFloor")
	public @ResponseBody
	String changeFloor(@RequestParam String buildNumber,String floor) {
		List<LabRoom> labRooms = visualizationService.getLabRoomsByBuildAndFloor(buildNumber,floor,1,-1);

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
	@RequestMapping("/visualization/show/changeFloorFirstRoom")
	public @ResponseBody
	String changeFloorFirstRoom(@RequestParam String buildNumber,String floor,HttpServletRequest request) {
		//服务器端口
		String baseAdress = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+request.getContextPath();
		List<LabRoom> labRooms = visualizationService.getLabRoomsByBuildAndFloor(buildNumber,floor,1,-1);
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
			str = str + "可用、不可预约";
			str = str + "</p> <a class='room_info_btn' href='http://www.baidu.com'> 实验室监控</a></div>";
			str = str + "</p> <a class='room_visualization' target='_blank' href='"+request.getContextPath()+"/visualization/show/roomImage?id="+labRoom.getId()+"'> 房间可视化</a>";
			String url = baseAdress + "/tms/courseList?currpage=1";
			if(labRoom.getLabRoomNumber().equals("4412")){
				url = "http://www.xidlims.net:3380/wk/course/140";
			}else if(labRoom.getLabRoomNumber().equals("4118")){
				url = "http://www.xidlims.net:3380/wk/course/82";
			}
			
			str = str + " <a class='room_visualization' target='_blank' href='"+url+"'>实验室资源</a> </div>";
		}

		
		return shareService.htmlEncode(str);
	}
	
	/************************************************************
	 * @可视化-切换楼层第一个房间
	 * @作者：裴继超
	 * @日期：2016年1月22日
	 ************************************************************/
	@RequestMapping("/visualization/show/changeFloorFirstRoomMap")
	public @ResponseBody
	Map changeFloorFirstRoomMap(@RequestParam String buildNumber,String floor,HttpServletRequest request) {
		List<LabRoom> labRooms = visualizationService.getLabRoomsByBuildAndFloor(buildNumber,floor,1,-1);
		Map<String, String> map = new HashMap<String, String>();
		map = visualizationService.findLabRoomMap(labRooms.get(0));
		return map;
	}
	
	/************************************************************
	 * @可视化-切换实验室全景图
	 * @作者：裴继超
	 * @日期：2016年3月14日
	 ************************************************************/
	@RequestMapping("/visualization/show/changeFloorFirstRoomImage")
	public @ResponseBody
	String changeFloorFirstRoomImage(@RequestParam String buildNumber,String floor,int type,HttpServletRequest request) {
		List<LabRoom> labRooms = visualizationService.getLabRoomsByBuildAndFloor(buildNumber,floor,1,-1);
		LabRoom labRoom = new LabRoom();
		String str = "";
		if(labRooms.size()!=0){
			labRoom = labRooms.get(0);
			CommonDocument commonDocument = commonDocumentService.findCommonDocumentByLabRoom(labRoom,type);
			str = ""+commonDocument.getDocumentUrl()+"";
		}else{
			return null;
		}
		return shareService.htmlEncode(str);
	}
	
	/************************************************************
	 * @可视化-切换实验室
	 * @作者：裴继超
	 * @日期：2016年1月23日
	 ************************************************************/
	@RequestMapping("/visualization/show/changeRoomMap")
	public @ResponseBody
	Map changeRoomMap(@RequestParam int id,HttpServletRequest request) {
		LabRoom labRoom = visualizationService.findLabRoomByPrimaryKey(id);
		Map<String, String> map = new HashMap<String, String>();
		map = visualizationService.findLabRoomMap(labRoom);
		return map;
	}
	
	/************************************************************
	 * @可视化-切换实验室MAP
	 * @作者：裴继超
	 * @日期：2016年1月23日
	 ************************************************************/
	@RequestMapping("/visualization/show/changeRoom")
	public @ResponseBody
	String changeRoom(@RequestParam int id,HttpServletRequest request) {
		LabRoom labRoom = visualizationService.findLabRoomByPrimaryKey(id);
		String baseAdress = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+request.getContextPath();
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
		str = str + "可用、不可预约";
		str = str + "</p> <a class='room_info_btn' href='http://www.baidu.com'> 实验室监控</a>";
		str = str + "</p> <a class='room_visualization' target='_blank' href='"+request.getContextPath()+"/visualization/show/roomImage?id="+labRoom.getId()+"'> 房间可视化</a>";
		String url = baseAdress + "/tms/courseList?currpage=1";
		if(labRoom.getLabRoomNumber().equals("4412")){
			url = "http://www.xidlims.net:3380/wk/course/140";
		}else if(labRoom.getLabRoomNumber().equals("4118")){
			url = "http://www.xidlims.net:3380/wk/course/82";
		}
		
		str = str + " <a class='room_visualization' target='_blank' href='"+url+"'>实验室资源</a> </div>";
		
		return shareService.htmlEncode(str);
	}

	/************************************************************
	 * @可视化-切换实验室全景图
	 * @作者：裴继超
	 * @日期：2016年3月14日
	 ************************************************************/
	@RequestMapping("/visualization/show/changeRoomImage")
	public @ResponseBody
	String changeRoomPanoramagram(@RequestParam int id,int type,HttpServletRequest request) {
		LabRoom labRoom = visualizationService.findLabRoomByPrimaryKey(id);

		CommonDocument commonDocument = commonDocumentService.findCommonDocumentByLabRoom(labRoom,type);
		String str = ""+commonDocument.getDocumentUrl()+"";
		return shareService.htmlEncode(str);
	}
	
	/****************************************************************************
	 * 功能：AJAX根据校区id查询楼宇
	 * 作者：李小龙
	 ****************************************************************************/
	@RequestMapping("/visualization/findBuildByCampusId")
	public @ResponseBody String findBuildByCampusId(@RequestParam String id, HttpServletResponse response) {
		
		response.setContentType("text/json; charset=utf-8");
		response.setCharacterEncoding("utf-8");
		//根据校区id查询楼宇
		List<SystemBuild> builds=systemBuildService.findBuildByCampusId(id);
		String a="请选择";
		String s="<option  value='" + "'>" +a+ "</option>";
		for (SystemBuild b : builds) {
			s+="<option  value='" +b.getBuildNumber() + "'>" +b.getBuildName()+ "</option>";
		}
		return shareService.htmlEncode(s);
	}

	/****************************************************************************
	 * 功能：AJAX根据楼宇查询房间
	 * 作者：李小龙
	 ****************************************************************************/
	@RequestMapping("/visualization/findRoomByBuildNumber")
	public @ResponseBody String findRoomByBuildNumber(@RequestParam String buildNumber, HttpServletResponse response) {
		
		response.setContentType("text/json; charset=utf-8");
		response.setCharacterEncoding("utf-8");
		//根据楼宇编号查询房间
		List<SystemRoom> roomList=systemBuildService.findRoomByBuildNumber(buildNumber);
		String a="请选择";
		String s="<option  value='" + "'>" +a+ "</option>";
		for (SystemRoom m : roomList) {
			s+="<option  value='" +m.getRoomNumber()+ "'>" +m.getRoomName()+"("+m.getRoomNo()+")"+ "</option>";
		}
		return shareService.htmlEncode(s);
	}

}
