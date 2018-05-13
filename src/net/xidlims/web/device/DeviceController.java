/****************************************************************************
 * 命名规范：
 * 本控制层调整必须是按照如下的路径调整：@RequestMapping("/device/sample")，全部小写。
 * 列表信息listxxxx，獲取信息：getxxxx 编辑信息：editxxxx 删除信息：detelexxxx 新增信息 newxxxx
 * 导出信息exportxxxx 保存信息：savexxxx  
 ****************************************************************************/

package net.xidlims.web.device;

import java.net.BindException;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.xidlims.service.common.CommonDocumentService;
import net.xidlims.service.common.CommonVideoService;
import net.xidlims.service.common.ShareService;
import net.xidlims.service.device.LabRoomDeviceReservationService;
import net.xidlims.service.device.LabRoomDeviceService;
import net.xidlims.service.device.SchoolDeviceService;
import net.xidlims.service.lab.LabRoomService;
import net.xidlims.constant.CommonConstantInterface;
import net.xidlims.dao.AuthorityDAO;
import net.xidlims.dao.CDictionaryDAO;
import net.xidlims.dao.CStaticValueDAO;
import net.xidlims.dao.LabCenterDAO;
import net.xidlims.dao.LabRoomAdminDAO;
import net.xidlims.dao.LabRoomDAO;
import net.xidlims.dao.LabRoomDeviceDAO;
import net.xidlims.dao.LabRoomDeviceLendingDAO;
import net.xidlims.dao.LabRoomDeviceLendingResultDAO;
import net.xidlims.dao.LabRoomDevicePermitUsersDAO;
import net.xidlims.dao.LabRoomDeviceRepairDAO;
import net.xidlims.dao.LabRoomDeviceReservationDAO;
import net.xidlims.dao.LabRoomDeviceTrainingDAO;
import net.xidlims.dao.LabRoomDeviceTrainingPeopleDAO;
import net.xidlims.dao.LabRoomLimitTimeDAO;
import net.xidlims.dao.MessageDAO;
import net.xidlims.dao.SchoolDeviceDAO;
import net.xidlims.dao.SchoolWeekDAO;
import net.xidlims.dao.SystemTimeDAO;
import net.xidlims.dao.UserDAO;
import net.xidlims.domain.Authority;
import net.xidlims.domain.CDictionary;
import net.xidlims.domain.CStaticValue;
import net.xidlims.domain.CommonDocument;
import net.xidlims.domain.CommonVideo;
import net.xidlims.domain.IotSharePowerOpentime;
import net.xidlims.domain.LabCenter;
import net.xidlims.domain.LabRoom;
import net.xidlims.domain.LabRoomAdmin;
import net.xidlims.domain.LabRoomDevice;
import net.xidlims.domain.LabRoomDeviceLending;
import net.xidlims.domain.LabRoomDeviceLendingResult;
import net.xidlims.domain.LabRoomDevicePermitUsers;
import net.xidlims.domain.LabRoomDeviceRepair;
import net.xidlims.domain.LabRoomDeviceReservation;
import net.xidlims.domain.LabRoomDeviceTraining;
import net.xidlims.domain.LabRoomDeviceTrainingPeople;
import net.xidlims.domain.LabRoomLimitTime;
import net.xidlims.domain.Message;
import net.xidlims.domain.ResearchProject;
import net.xidlims.domain.SchoolDevice;
import net.xidlims.domain.SchoolTerm;
import net.xidlims.domain.SystemTime;
import net.xidlims.domain.TimetableLabRelated;
import net.xidlims.domain.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

/****************************************************************************
 * @功能： 设备管理模块
 * 
 * @作者：魏诚 @时间：2014-07-14
 ****************************************************************************/
@Controller("DeviceController")
@SessionAttributes({"selected_labCenter", "is_reservation"})
public class DeviceController<JsonResult> {
	// 读取属性文件中specialAcademy对应的值（此方法需要在web-content.xml中增加配置）
	@Value("${specialAcademy}")
	private String specialAcademy;

	@Autowired
	LabRoomDeviceService labRoomDeviceService;
	@Autowired
	LabRoomDAO labRoomDAO;
	@Autowired
	ShareService shareService;
	@Autowired
	SchoolDeviceDAO schoolDeviceDAO;
	@Autowired
	CDictionaryDAO cDictionaryDAO;
	@Autowired
	LabRoomDeviceReservationDAO labRoomDeviceReservationDAO;
	@Autowired
	SystemTimeDAO systemTimeDAO;
	@Autowired
	LabRoomDeviceTrainingDAO labRoomDeviceTrainingDAO;
	@Autowired
	LabRoomDeviceTrainingPeopleDAO labRoomDeviceTrainingPeopleDAO;
	@Autowired
	UserDAO userDAO;
	@Autowired
	CommonDocumentService commonDocumentService;
	@Autowired
	CommonVideoService commonVideoService;
	@Autowired
	LabRoomService labRoomService;
	@Autowired
	LabRoomDeviceLendingDAO labRoomDeviceLendingDAO;
	@Autowired
	LabRoomDeviceLendingResultDAO labRoomDeviceLendingResultDAO;
	@Autowired
	LabRoomDeviceRepairDAO labRoomDeviceRepairDAO;
	@Autowired
	LabRoomDeviceDAO labRoomDeviceDAO;
	@Autowired
	LabCenterDAO labCenterDAO;
	@Autowired
	LabRoomAdminDAO labRoomAdminDAO;
	@Autowired
	AuthorityDAO authorityDAO;
	@Autowired
	CStaticValueDAO cStaticValueDAO;
	@Autowired
	LabRoomLimitTimeDAO labRoomLimitTimeDAO;
	@Autowired
	SchoolWeekDAO schoolWeekDAO;
	@Autowired
	LabRoomDevicePermitUsersDAO labRoomDevicePermitUsersDAO;
	@Autowired
	SchoolDeviceService schoolDeviceService;
	@Autowired
	LabRoomDeviceReservationService labRoomDeviceReservationService;
	@Autowired
	MessageDAO messageDAO;
	
	@InitBinder
	public void initBinder(WebDataBinder binder, HttpServletRequest request) { // Register
																				// static
																				// property
																				// editors.
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
	 * @功能：删除实验分室设备
	 * @作者：李小龙
	 ****************************************************************************/
	@RequestMapping("/device/deleteLabRoomDevice")
	public ModelAndView deleteLabRoomDevice(@RequestParam Integer id) {
		// 新建ModelAndView对象；
		ModelAndView mav = new ModelAndView();
		// id对应的实验室设备
		LabRoomDevice d = labRoomDeviceService.findLabRoomDeviceByPrimaryKey(id);
		// 设备所属实验室
		LabRoom room = d.getLabRoom();
		labRoomDeviceService.deleteLabRoomDevice(d);
		mav.setViewName("redirect:/appointment/getLabRoom?id=" + room.getId());
		return mav;
	}

	/****************************************************************************
	 * @功能：设置实验室设备的管理员，实验室预约的时候的设备管理员审核
	 * @作者：贺子龙 @时间：2015-09-22 14:14:17
	 ****************************************************************************/
	@RequestMapping("/device/setLabRoomDeviceManager")
	public ModelAndView setLabRoomDeviceManager(@RequestParam Integer id, String[] array) {
		// 新建ModelAndView对象；
		ModelAndView mav = new ModelAndView();
		// id对应的实验室设备
		LabRoomDevice d = labRoomDeviceService.findLabRoomDeviceByPrimaryKey(id);
		// 设备所属实验室
		LabRoom room = d.getLabRoom();
		for (String i : array) {
			User u = userDAO.findUserByPrimaryKey(i);
			d.setUser(u);
			labRoomDeviceDAO.store(d);
			// 给用户赋予权限
			Authority authority = authorityDAO.findAuthorityByPrimaryKey(10);// 设备管理员
			Set<Authority> ahths = u.getAuthorities();
			ahths.add(authority);
			u.setAuthorities(ahths);
			userDAO.store(u);
		}
		mav.setViewName("redirect:/appointment/getLabRoom?id=" + room.getId());
		return mav;
	}

	/****************************************************************************
	 * @功能：给设备上传图片
	 * @作者：李小龙
	 ****************************************************************************/
	@RequestMapping("/device/deviceImageUpload")
	public @ResponseBody String deviceImageUpload(HttpServletRequest request, HttpServletResponse response, BindException errors, Integer id) throws Exception {
		labRoomDeviceService.deviceImageUpload(request, response, id, 1);
		return "ok";
	}

	/****************************************************************************
	 * @功能：给设备上传视频
	 * @作者：李小龙
	 ****************************************************************************/
	@RequestMapping("/device/deviceVideoUpload")
	public @ResponseBody String deviceVideoUpload(HttpServletRequest request, HttpServletResponse response, BindException errors, Integer id) throws Exception {
		labRoomDeviceService.deviceVideoUpload(request, response, id);
		return "ok";
	}

	/****************************************************************************
	 * @功能：删除设备图片
	 * @作者：李小龙
	 ****************************************************************************/
	@RequestMapping("/device/deleteDeviceDocument")
	public ModelAndView deleteDeviceDocument(@RequestParam Integer id, @ModelAttribute("selected_labCenter") Integer cid) {
		// 新建ModelAndView对象；
		ModelAndView mav = new ModelAndView();
		// id对应的设备图片
		CommonDocument doc = commonDocumentService.findCommonDocumentByPrimaryKey(id);
		// 图片所属的设备
		LabRoomDevice device = doc.getLabRoomDevice();
		int idkey = device.getId();
		commonDocumentService.deleteCommonDocument(doc);
		// 删除服务器上的文件
		int type = doc.getType();
		// 中心所属学院
		String academy = labCenterDAO.findLabCenterByPrimaryKey(cid).getSchoolAcademy().getAcademyNumber();
		// 根据学院编号判断是否为配置文件配置的特殊学院（化工学院）
		boolean flag = specialAcademy.contains(academy);
		if (flag) {
			if (type == 1) {// 图片
				mav.setViewName("redirect:/device/deviceImage?deviceId=" + idkey);
			} else {// 文档
				mav.setViewName("redirect:/device/deviceDocument?deviceId=" + idkey);
			}

		} else {
			mav.setViewName("redirect:/device/editDeviceReservationInfo?id=" + idkey);
		}
		return mav;
	}

	/****************************************************************************
	 * @功能：删除设备视频
	 * @作者：李小龙
	 ****************************************************************************/
	@RequestMapping("/device/deleteLabRoomVideo")
	public ModelAndView deleteLabRoomVideo(@RequestParam Integer id, @ModelAttribute("selected_labCenter") Integer cid) {
		// 新建ModelAndView对象；
		ModelAndView mav = new ModelAndView();
		// id对应的设备视频
		CommonVideo video = commonVideoService.findVideoByPrimaryKey(id);
		// 图片所属的设备
		LabRoomDevice device = video.getLabRoomDevice();
		int idkey = device.getId();
		commonVideoService.deleteCommonVideo(video);
		// 删除服务器上的文件

		// 中心所属学院
		String academy = labCenterDAO.findLabCenterByPrimaryKey(cid).getSchoolAcademy().getAcademyNumber();
		// 根据学院编号判断是否为配置文件配置的特殊学院（化工学院）
		boolean flag = specialAcademy.contains(academy);
		if (flag) {
			mav.setViewName("redirect:/device/deviceVideo?deviceId=" + idkey);
		} else {
			mav.setViewName("redirect:/device/editDeviceReservationInfo?id=" + idkey);
		}

		return mav;
	}

	/****************************************************************************
	 * @功能：实验室设备管理---设备管理--所有设备
	 * @作者：李小龙
	 ****************************************************************************/
	@RequestMapping("/device/listLabRoomDevice")
	public ModelAndView listLabRoomDevice(@ModelAttribute LabRoomDevice labRoomDevice, @RequestParam Integer page, Integer isReservation,@ModelAttribute("selected_labCenter") Integer cid) {
		// 新建ModelAndView对象；
		ModelAndView mav = new ModelAndView();
		String academy="";
		if(cid != null & cid != -1)
		{
			// 中心所属学院
			academy = labCenterDAO.findLabCenterByPrimaryKey(cid).getSchoolAcademy().getAcademyNumber();
		}
		else{
			academy=shareService.getUser().getSchoolAcademy().getAcademyNumber();
		}
		// 查询表单的对象
		mav.addObject("labRoomDevice", labRoomDevice);
		// 实验室
		List<LabRoom> rooms = labRoomService.findLabRoomWithDevices(cid,isReservation);
		int totalRecordsLabroom = rooms.size();
		mav.addObject("rooms", rooms);
		int pageSize = 10;
		int pageSizeLabroom = 12;
		// 实验室分页
		List<LabRoom> roomsList = labRoomService.findLabRoomWithDevices(labRoomDevice, cid, page, pageSizeLabroom,isReservation);
		mav.addObject("roomsList", roomsList);

		// 设备管理员
		List<User> users = shareService.findUsersByAuthorityId(10);
		mav.addObject("users", users);
		// 查询出来的总记录条数
		int totalRecords = labRoomDeviceService.findAllLabRoomDeviceNew(labRoomDevice, cid,isReservation);
		// 分页信息
		Map<String, Integer> pageModel = shareService.getPage(page, pageSize, totalRecords);// listLabRoomDevice.jsp页面分页
		Map<String, Integer> pageModelLabroom = shareService.getPage(page, pageSizeLabroom, totalRecordsLabroom);// listLabRoom.jsp页面分页

		// 根据分页信息查询出来的记录
		List<LabRoomDevice> listLabRoomDevice = labRoomDeviceService.findAllLabRoomDeviceNew(labRoomDevice, cid, page, pageSize,isReservation);
		mav.addObject("listLabRoomDevice", listLabRoomDevice);
		// 获取所有的设备管理员
		mav.addObject("userMap", labRoomDeviceService.findDeviceManageCnamerByCid(cid));
		// 查询所有设备记录
		List<LabRoomDevice> listLabRoomDeviceAll = labRoomDeviceService.findAllLabRoomDeviceNew(labRoomDevice, cid, 1, -1,isReservation);
		mav.addObject("listLabRoomDeviceAll", listLabRoomDeviceAll);
		mav.addObject("pageModel", pageModel);
		mav.addObject("pageModelLabroom", pageModelLabroom);
		mav.addObject("totalRecords", totalRecords);
		mav.addObject("totalRecordsLabroom", totalRecordsLabroom);
		mav.addObject("page", page);
		mav.addObject("pageSize", pageSize);
		mav.addObject("pageSizeLabroom", pageSizeLabroom);
		// 获取所有的设备管理员
		//mav.addObject("userMap", labRoomDeviceService.findDeviceManagerByCid(cid));
		// 获取当前用户传递到前台
		User user = shareService.getUser();
		mav.addObject("user", user);
		// 加入全局变量来表征当前老师选择的是“设置”还是“预约”
				if (isReservation != null && isReservation.equals(1)) {
					mav.addObject("is_reservation", 1);
				}else {
					mav.addObject("is_reservation", 0);
				}
		// 根据学院编号判断是否为配置文件配置的特殊学院（化工学院）
		/*boolean flag = specialAcademy.contains(academy);
		if (flag) {
			if (academy.equals("0000001")) {// 纺织学院
				mav.setViewName("/device/specialAcademy/listLabRoom.jsp");

			} else
				mav.setViewName("/device/specialAcademy/specialAcademy.jsp");
		} else {
			mav.setViewName("/device/lab_room_device/listLabRoomDevice.jsp");
		}*/
		mav.setViewName("/device/specialAcademy/listLabRoom.jsp");
		return mav;
	}

	/****************************************************************************
	 * @功能：实验室设备管理---设备管理--所有设备
	 * @作者：李小龙
	 ****************************************************************************/
	@SuppressWarnings("unused")
	@RequestMapping("/device/listLabRoomDeviceForLabroom")
	public ModelAndView listLabRoomDeviceForLabroom(@ModelAttribute LabRoomDevice labRoomDevice, @RequestParam Integer page, @ModelAttribute("selected_labCenter") Integer cid, @ModelAttribute("is_reservation") Integer isReservation) {

		// 新建ModelAndView对象；
		ModelAndView mav = new ModelAndView();
		int roomId = -1;
		if (labRoomDevice.getLabRoom() != null && labRoomDevice.getLabRoom().getId() != null) {
			roomId = labRoomDevice.getLabRoom().getId();
		}
		mav.addObject("roomId", roomId);
		// 中心所属学院
		//String academy = labCenterDAO.findLabCenterByPrimaryKey(cid).getSchoolAcademy().getAcademyNumber();

		// 查询表单的对象
		mav.addObject("labRoomDevice", labRoomDevice);
		// 实验室
		List<LabRoom> rooms = labRoomService.findLabRoomWithDevices(cid,isReservation);
		int totalRecordsLabroom = labRoomService.findAllLabRoom(labRoomDevice, cid, isReservation);
		mav.addObject("rooms", rooms);
		int pageSize = 10;
		int pageSizeLabroom = 12;
		// 实验室分页
		List<LabRoom> roomsList = labRoomService.findLabRoomByLabCenterid(labRoomDevice, cid, page, pageSizeLabroom);
		mav.addObject("roomsList", roomsList);

		// 设备管理员
		List<User> users = shareService.findUsersByAuthorityId(10);
		mav.addObject("users", users);
		// 查询出来的总记录条数
		int totalRecords = labRoomDeviceService.findAllLabRoomDeviceNew(labRoomDevice, cid, isReservation);
		// 分页信息
		Map<String, Integer> pageModel = shareService.getPage(page, pageSize, totalRecords);// listLabRoomDevice.jsp页面分页
		Map<String, Integer> pageModelLabroom = shareService.getPage(page, pageSizeLabroom, totalRecordsLabroom);// listLabRoom.jsp页面分页
		// 获取所有的设备管理员
		mav.addObject("userMap", labRoomDeviceService.findDeviceManageCnamerByCid(cid));
		// 根据分页信息查询出来的记录
		List<LabRoomDevice> listLabRoomDevice = labRoomDeviceService.findAllLabRoomDeviceNew(labRoomDevice, cid, page, pageSize,1);
		mav.addObject("listLabRoomDevice", listLabRoomDevice);
		// 查询所有设备记录
		List<LabRoomDevice> listLabRoomDeviceAll = labRoomDeviceService.findAllLabRoomDeviceNew(labRoomDevice, cid, 1, -1,1);
		mav.addObject("listLabRoomDeviceAll", listLabRoomDeviceAll);
		mav.addObject("pageModel", pageModel);
		mav.addObject("pageModelLabroom", pageModelLabroom);
		mav.addObject("totalRecords", totalRecords);
		mav.addObject("totalRecordsLabroom", totalRecordsLabroom);
		mav.addObject("page", page);
		mav.addObject("pageSize", pageSize);
		mav.addObject("pageSizeLabroom", pageSizeLabroom);
		mav.addObject("tag", 1);// 后台区分查询取消的跳转页面
		// 当前用户
		User user = shareService.getUser();
		mav.addObject("user", user);
		mav.setViewName("/device/specialAcademy/specialAcademyForLabroom.jsp");

		return mav;
	}

	/****************************************************************************
	 * @功能：实验室设备管理---设备管理--所有设备
	 * @作者：贺子龙
	 ****************************************************************************/
	@RequestMapping("/device/listLabRoomDeviceNew")
	public ModelAndView listLabRoomDeviceNew(@ModelAttribute LabRoomDevice labRoomDevice, @RequestParam int roomId, Integer page, @ModelAttribute("selected_labCenter") Integer cid, @ModelAttribute("is_reservation") Integer isReservtaion) {
		// System.out.println("=-=-=-=-=-");
		// 新建ModelAndView对象；
		ModelAndView mav = new ModelAndView();

		// 查询表单的对象
		mav.addObject("labRoomDevice", labRoomDevice);
		// 实验室
		List<LabRoom> rooms = labRoomService.findLabRoomByLabCenterid(cid,isReservtaion);
		mav.addObject("rooms", rooms);
		int pageSize = 10;
		// roomId对应的实验室
		LabRoom labRoom = labRoomService.findLabRoomByPrimaryKey(roomId);
		mav.addObject("labRoom", labRoom);

		// 设备管理员
		List<User> users = shareService.findUsersByAuthorityId(10);
		mav.addObject("users", users);
		// 查询出来的总记录条数
		int totalRecords = labRoomDeviceService.findAllLabRoomDevice(labRoomDevice, cid, roomId);
		// 分页信息
		Map<String, Integer> pageModel = shareService.getPage(page, pageSize, totalRecords);// listLabRoomDevice.jsp页面分页
		// 获取所有的设备管理员
		mav.addObject("userMap", labRoomDeviceService.findDeviceManageCnamerByCid(cid));
		// 根据分页信息查询出来的记录
		List<LabRoomDevice> listLabRoomDevice = labRoomDeviceService.findAllLabRoomDevice(labRoomDevice, cid, page, pageSize, roomId);
		mav.addObject("listLabRoomDevice", listLabRoomDevice);
		// 查询所有设备记录
		List<LabRoomDevice> listLabRoomDeviceAll = labRoomDeviceService.findAllLabRoomDevice(labRoomDevice, cid, 1, -1, roomId);
		mav.addObject("listLabRoomDeviceAll", listLabRoomDeviceAll);
		mav.addObject("pageModel", pageModel);
		mav.addObject("totalRecords", totalRecords);
		mav.addObject("page", page);
		mav.addObject("pageSize", pageSize);
		mav.addObject("roomId", roomId);
		mav.addObject("tag", 0);// 后台区分查询取消的跳转页面
		// 当前用户
		User user = shareService.getUser();
		mav.addObject("user", user);

		mav.setViewName("/device/specialAcademy/specialAcademyForLabroom.jsp");
		return mav;
	}

	/****************************************************************************
	 * @功能：实验室设备管理---设备管理--关联设备
	 * @作者：李小龙
	 ****************************************************************************/
	@RequestMapping("/device/listInnerSameDevice")
	public ModelAndView listInnerSameDevice(@ModelAttribute("selected_labCenter") Integer cid, @RequestParam Integer deviceId) {
		// 新建ModelAndView对象；
		ModelAndView mav = new ModelAndView();
		LabRoomDevice device = labRoomDeviceService.findLabRoomDeviceByPrimaryKey(deviceId);
		String deviceNumber = device.getSchoolDevice().getDeviceNumber();
		List<SchoolDevice> schoolDevices = schoolDeviceService.findInnerSameDevice(deviceNumber);
		mav.addObject("schoolDevices", schoolDevices);
		mav.addObject("device", device);
		mav.addObject("labRooms", labRoomService.findLabRoomByLabCenterid(cid,1));  //实验室数据
		// 添加设备所需
		mav.addObject("schoolDevice", new SchoolDevice()); // 实验室设备

		mav.setViewName("/device/specialAcademy/listInnerSameDevice.jsp");
		return mav;
	}

	/****************************************************************************
	 * @throws ParseException
	 * @功能：安全准入验证
	 * @作者：李小龙
	 ****************************************************************************/
	@RequestMapping("/device/securityAccess")
	public @ResponseBody String securityAccess(@RequestParam Integer id) throws ParseException {
		// 更新预约结果
		labRoomDeviceService.updateReservationResult(id);
		User user = shareService.getUser();
		String data = labRoomDeviceService.securityAccess(user.getUsername(), id);
		return htmlEncode(data);
	}

	/****************************************************************************
	 * @功能：设备预约
	 * @作者：李小龙
	 ****************************************************************************/
	@SuppressWarnings("unused")
	@RequestMapping("/device/reservationDevice")
	public ModelAndView reservationLabRoomDevice(@RequestParam Integer id) {
		// 新建ModelAndView对象；
		ModelAndView mav = new ModelAndView();
		// id对应的实验室设备
		LabRoomDevice device = labRoomDeviceService.findLabRoomDeviceByPrimaryKey(id);
		mav.addObject("device", device);

		CStaticValue cStaticValue = new CStaticValue();
		// 获取类型为设备预约审核有效@时间device_valid_time的字典，并取出第一个值
		for (CStaticValue cStaticValueTmp : cStaticValueDAO.findCStaticValueByCode("device_valid_time")) {
			if (cStaticValueTmp.getId() != null) {
				cStaticValue = cStaticValueTmp;
			}
		}

		// 赋予变量cStaticValue到前端
		mav.addObject("cStaticValue", cStaticValue);
		mav.addObject("id", id);
		// 获取当前@时间
		Calendar now = Calendar.getInstance();
		if (cStaticValue.getStaticValue() != null) {// 判空，贺子龙 2015-10-20
			if (device.getCDictionaryByIsAudit() != null && "1".equals(device.getCDictionaryByIsAudit().getCNumber())) {// 如果不需要审核，则不用加一天或两天时间
																									// 贺子龙
																									// 2015-11-04
				String[] abc = cStaticValue.getStaticValue().split(":");
				if (now.get(Calendar.HOUR_OF_DAY) > Integer.parseInt(abc[0])) {
					now.add(Calendar.DAY_OF_YEAR, 2);
				} else if ((now.get(Calendar.HOUR_OF_DAY) == Integer.parseInt(abc[0]))) {
					if ((now.get(Calendar.MINUTE) > Integer.parseInt(abc[1]))) {
						now.add(Calendar.DAY_OF_YEAR, 2);
					} else {
						now.add(Calendar.DAY_OF_YEAR, 1);
					}
				} else {
					now.add(Calendar.DAY_OF_YEAR, 1);
				}
			}
			/*
			 * else {//但是，不能预约已经过去的时间 now.add(Calendar.HOUR_OF_DAY, 1);//加一个小时
			 * 
			 * }
			 */

		}

		// 获取当前@时间的年份映射给year
		mav.addObject("year", now.get(Calendar.YEAR));
		// 获取当前@时间的月份映射给month
		mav.addObject("month", now.get(Calendar.MONTH));
		// 获取当前@时间的天数映射给day；
		mav.addObject("day", now.get(Calendar.DAY_OF_MONTH));

		// 获取当前@时间的天数映射给小时；
		mav.addObject("hour", now.get(Calendar.HOUR_OF_DAY));

		// 获取当前@时间的天数映射给分钟；
		mav.addObject("minute", now.get(Calendar.MINUTE));

		// 获取当前@时间的天数映射给分钟；
		mav.addObject("second", now.get(Calendar.SECOND));

		if (device.getSchoolDevice() != null) {
			mav.addObject("schoolDeviceName", device.getSchoolDevice().getDeviceName());
		}

		// 设备所属实验室
		LabRoom room = device.getLabRoom();
		// 根据实验室查询实验室的排课
		List<TimetableLabRelated> relateds = labRoomDeviceService.findTimetableLabRelatedByRoomId(room.getId());

		// 导师集合
		User user = shareService.getUser();
		List<User> ts = shareService.findTheSameCollegeTeacher(user.getSchoolAcademy().getAcademyNumber());
		List<String> teachers = new ArrayList<String>();
		for (User u : ts) {
			/*
			 * if(u.getSchoolAcademy().getAcademyNumber().equals("0201")&&
			 * u.getUsername().toString().indexOf("1005")==-1){//
			 * 目前纺织学院正式帐号都是10055开头的，所以不包含10055的都要排除掉 continue; }
			 */
			// 预约插件只支持纯数字，这边过滤掉username包含字母的数据
			if (Pattern.compile("(?i)[a-z]").matcher(u.getUsername()).find() == false) {
				teachers.add("{key" + ":" + u.getUsername().trim() + ",label:'" + u.getCname().trim() + u.getUsername() + "'}");
			}

		}
		mav.addObject("teachers", teachers);
		// // 申请性质集合
		// Set<CReservationProperty> ps =
		// cReservationPropertyDAO.findAllCReservationPropertys();
		// List<String> propertys = new ArrayList<String>();
		// for (CReservationProperty p : ps) {
		// propertys.add("{key" + ":" + p.getId() + ",label:'" + p.getName() +
		// "'}");
		// }
		// mav.addObject("propertys", propertys);
		mav.setViewName("/device/lab_room_device/reservationDevice.jsp");
		return mav;
	}

	/****************************************************************************
	 * @功能：Ajax保存设备预约并返回是否保存成功 @作者：李小龙
	 ****************************************************************************/
	@RequestMapping("/device/saveDeviceReservation")
	public @ResponseBody String saveDeviceReservation(@RequestParam Integer equinemtid, String startDate, String endDate, String description, String phone, String teacher, Integer property, String research) throws Exception {

		// id对应的实验室设备
		LabRoomDevice device = labRoomDeviceService.findLabRoomDeviceByPrimaryKey(equinemtid);

		// 当前登录人
		User user = shareService.getUser();
		// 要保存进数据库的预约对象
		LabRoomDeviceReservation reservation = new LabRoomDeviceReservation();

		reservation.setLabRoomDevice(device);// 预约的设备
		reservation.setUserByReserveUser(user);// 预约人
		reservation.setContent(description);// 描述
		reservation.setPhone(phone);// 联系电话
		// 指导老师
		User u = userDAO.findUserByPrimaryKey(teacher);
		reservation.setUserByTeacher(u);
		// 申请性质--2016-03-22默认为预约
		//CReservationProperty p = cReservationPropertyDAO.findCReservationPropertyByPrimaryKey(1);
		CDictionary cReservationProperty = shareService.getCDictionaryByCategory("c_reservation_property", "1");
		reservation.setCReservationProperty(cReservationProperty);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// 开始@时间
		Date date = sdf.parse(startDate);
		Calendar calendar1 = Calendar.getInstance();
		calendar1.setTime(date);
		reservation.setBegintime(calendar1);
		// 结束@时间
		Date date2 = sdf.parse(endDate);
		Calendar calendar2 = Calendar.getInstance();
		calendar2.setTime(date2);
		reservation.setEndtime(calendar2);
		// 申请@时间
		reservation.setTime(Calendar.getInstance());
		// 申请所属的学期
		SchoolTerm term = shareService.getBelongsSchoolTerm(Calendar.getInstance());
		reservation.setSchoolTerm(term);
		if(research != null && !research.equals("") && !research.equals("null"))
		{
			ResearchProject researchProject = labRoomDeviceService.findResearchProjectByPrimaryKey(Integer.parseInt(research));
			reservation.setResearchProject(researchProject);
		}

		// 获取当前学期
		int termId = shareService.getBelongsSchoolTerm(Calendar.getInstance()).getId();
		// 实验室禁用时间段列表
		List<LabRoomLimitTime> labRoomLimitTimes = labRoomLimitTimeDAO.executeQuery("select c from LabRoomLimitTime c where c.labId= " + device.getLabRoom().getId() + " and c.schoolTerm.id=" + termId, 0, -1);
		// 如果是禁用时间匹配则返回limit
		if (!shareService.isLimitedByTime(labRoomLimitTimes, calendar1, calendar2)) {
			return "LIMIT";
		}
		// 状态
		// 是否需要审核
		//int isAudit = device.getCActiveByIsAudit().getId();
		String isAudit = device.getCDictionaryByIsAudit().getCNumber();
		
		if ("2".equals(isAudit) || (device.getUser() != null && user.getUsername().equals(device.getUser().getUsername()))) {// 不需要审核或者预约人为设备管理员本人
			//CAuditResult status = cAuditResultDAO.findCAuditResultByPrimaryKey(2);
			CDictionary status = shareService.getCDictionaryByCategory("c_audit_result", "2");
			reservation.setCAuditResult(status);
		}
		if ("1".equals(isAudit) && (device.getUser() != null && !user.getUsername().equals(device.getUser().getUsername()))) {// 需要审核并且预约人不是设备管理员本人
			// 默认先设为审核中
			//CAuditResult status = cAuditResultDAO.findCAuditResultByPrimaryKey(1);
			CDictionary status = shareService.getCDictionaryByCategory("c_audit_result", "1");
			reservation.setCAuditResult(status);

			if (device.getCDictionaryByTeacherAudit() != null) {// 贺子龙 2015-11-06
				//int CActiveByTeacherAuditId = device.getCActiveByTeacherAudit().getId();
				String CActiveByTeacherAuditId = device.getCDictionaryByTeacherAudit().getCNumber();
				if ("2".equals(CActiveByTeacherAuditId)) {// 不需要导师
					if (device.getCActiveByLabManagerAudit() != null) {
						//int CActiveByLabManagerAuditId = device.getCActiveByLabManagerAudit().getId();
						String CActiveByLabManagerAuditId = device.getCActiveByLabManagerAudit().getCNumber();
						if ("2".equals(CActiveByLabManagerAuditId)) {// 不需要实验室管理员
							if (device.getCDictionaryByManagerAudit() != null) {
								//int CActiveByManagerAuditId = device.getCActiveByManagerAudit().getId();
								String CActiveByManagerAuditId = device.getCDictionaryByManagerAudit().getCNumber();
								if ("2".equals(CActiveByManagerAuditId)) {// 不需要设备管理员
									// 审核通过
									//CAuditResult pass = cAuditResultDAO.findCAuditResultByPrimaryKey(2);
									CDictionary pass = shareService.getCDictionaryByCategory("c_audit_result", "2");
									reservation.setCAuditResult(pass);
								}
							}
						}
					}
				}
			}

		}

		// 根据设备id查询设备的预约记录
		List<LabRoomDeviceReservation> reservationList = labRoomDeviceService.findReservationListByDeviceId(equinemtid);
		int flag = 1;// 标记为0为失败，1为成功
		// 循环遍历预约记录，看是否和以前的预约有冲突
		for (LabRoomDeviceReservation r : reservationList) {
			Calendar start = r.getBegintime();
			Calendar end = r.getEndtime();
			if (end.after(calendar1) && start.before(calendar2)) {
				flag = 0;
			}

		}
		String dateStr = sdf.format(Calendar.getInstance().getTime());
		if (flag > 0) {
			reservation.setStage(0);
			if (device.getSchoolDevice().getInnerSame()!=null&&!device.getSchoolDevice().getInnerSame().equals("")) {
				reservation.setInnerSame(device.getSchoolDevice().getInnerSame()+"-"+dateStr);
				reservation.setInnerDeviceName(device.getSchoolDevice().getInnerDeviceName().replace("]", "]</br>"));
			}
			else {
				reservation.setInnerSame("device-"+device.getId()+"-"+dateStr);// 为了查询时候的group by InnerSame
			}
			labRoomDeviceReservationService.saveLabRoomDeviceReservation(reservation);
			
			return "success";
		} else {
			return "error";
		}

	}
	
	
	/****************************************************************************
	 * @功能：Ajax保存设备预约并返回是否保存成功 
	 * @作者：XL
	 ****************************************************************************/
	@RequestMapping("/device/saveModifyDeviceReservation")
	public @ResponseBody String saveDeviceReservation(@RequestParam Integer equinemtid, String startDate, String endDate, String description, String phone, String teacher, Integer property, @RequestParam Integer reservalId) throws Exception {
		// 找到对应的实验室预约
		LabRoomDeviceReservation reservation = labRoomDeviceReservationDAO.findLabRoomDeviceReservationByPrimaryKey(reservalId);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// 更新开始@时间
		Date date = sdf.parse(startDate);
		Calendar calendar1 = Calendar.getInstance();
		calendar1.setTime(date);
		reservation.setBegintime(calendar1);
		
		// 更新结束@时间
		Date date2 = sdf.parse(endDate);
		Calendar calendar2 = Calendar.getInstance();
		calendar2.setTime(date2);
		reservation.setEndtime(calendar2);

		// equinemtid对应的实验室设备
		LabRoomDevice device = labRoomDeviceService.findLabRoomDeviceByPrimaryKey(equinemtid);
		// 获取当前学期
		int termId = shareService.getBelongsSchoolTerm(Calendar.getInstance()).getId();
		
		// 实验室禁用时间段列表
		List<LabRoomLimitTime> labRoomLimitTimes = labRoomLimitTimeDAO.executeQuery("select c from LabRoomLimitTime c where c.labId= " + device.getLabRoom().getId() + " and c.schoolTerm.id=" + termId, 0, -1);
		// 如果是禁用时间匹配则返回limit
		if (!shareService.isLimitedByTime(labRoomLimitTimes, calendar1, calendar2)) {
			return "LIMIT";
		}

		// 根据设备id查询设备的预约记录
		List<LabRoomDeviceReservation> reservationList = labRoomDeviceService.findReservationListByDeviceId(equinemtid);
		int flag = 1;// 标记为0为失败，1为成功
		// 循环遍历预约记录，看是否和以前的预约有冲突
		for (LabRoomDeviceReservation r : reservationList) {
			if (r.getId().equals(reservalId)) {// 不与自己判冲
				continue;
			}
			Calendar start = r.getBegintime();
			Calendar end = r.getEndtime();
			if (end.after(calendar1) && start.before(calendar2)) {
				flag = 0;
			}
		}
		
		if (flag > 0) {
			labRoomDeviceReservationDAO.store(reservation);
			return "success";
		} else {
			return "error";
		}

	}


	/****************************************************************************
	 * @功能：查询以前所有的预约记录并显示在页面上
	 * @作者：李小龙
	 ****************************************************************************/
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/device/getLabReservation")
	public @ResponseBody List getLabReservation(@RequestParam Integer id,HttpServletRequest request) throws Exception {
		
		//,@RequestParam String 
		String reservalId = request.getParameter("reservalId");
		
		// 创建一个新的数组对象；
		List list = new ArrayList();
		// 根据设备id查询设备预约记录
		List<LabRoomDeviceReservation> reservations = labRoomDeviceService.findReservationListByDeviceId(id);
		for (LabRoomDeviceReservation r : reservations) {
			
			String cname = r.getUserByReserveUser().getCname();
			//String result = r.getCAuditResult().getName();
			String result = r.getCAuditResult().getCName();
			String content = r.getContent();

			// 创建一个新的map对象给变量appointmentMap；
			Map<String, Object> map = new HashMap<String, Object>();

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat sdfSimple = new SimpleDateFormat("yyyy-MM-dd");
			// 开始@时间
			Calendar startDate = r.getBegintime();
			// 结束@时间
			Calendar endDate = r.getEndtime();
			// 解决跨天问题

			// 获取两个日期相隔的天数
			int days = (int) ((endDate.getTime().getTime() - startDate.getTime().getTime()) / (1000 * 60 * 60 * 24));
			// 如果不跨天
			if (days == 0) {
				map.put("start_date", sdf.format(startDate.getTime()));
				map.put("end_date", sdf.format(endDate.getTime()));
				if ((r.getId().intValue()+"").equals(reservalId) ) {// 设备预约本身的颜色为绿色
					map.put("color", "green");
					map.put("text", "预约者：" + cname + "<br />审核状态：" + result + "<br />预约内容：" + content
							+ "<br /><br /><font color='red'>此色块为您正在修改的时间段</font>");
				}else {
					map.put("text", "预约者：" + cname + "<br />审核状态：" + result + "<br />预约内容：" + content);
				}
				list.add(map);
				
			} else {

				for (int i = 0; i <= days; i++) {
					Map<String, Object> mapTmp = new HashMap<String, Object>();
					if (i == 0) {
						mapTmp.put("start_date", sdf.format(startDate.getTime()));
						mapTmp.put("end_date", sdfSimple.format(startDate.getTime()) + " 23:59:59");
					} else if (i == days) {
						mapTmp.put("start_date", sdfSimple.format(endDate.getTime()) + " 00:00:01");
						mapTmp.put("end_date", sdf.format(endDate.getTime()));
					} else {
						startDate.add(Calendar.DATE, 1);//
						mapTmp.put("start_date", sdfSimple.format(startDate.getTime()) + " 00:00:01");
						mapTmp.put("end_date", sdfSimple.format(startDate.getTime()) + " 23:59:59");

					}
					if ((r.getId().intValue()+"").equals(reservalId) ) {// 设备预约本身的颜色为绿色
						mapTmp.put("text", "预约者：" + cname + "<br />审核状态：" + result + "<br />预约内容：" + content
								+ "<br /><br /><font color='red'>此色块为您正在修改的时间段</font>");
						mapTmp.put("color", "green");
					}else {
						mapTmp.put("text", "预约者：" + cname + "<br />审核状态：" + result + "<br />预约内容：" + content);
					}
					list.add(mapTmp);

				}

			}

		}

		System.out.println(list.size());
		return list;
	}

	/****************************************************************************
	 * @功能：获取设备禁止时间
	 * @作者：XL
	 ****************************************************************************/
	@SuppressWarnings("rawtypes")
	@RequestMapping("/device/getLimitLabReservation")
	public @ResponseBody List getLimitLabReservation(@RequestParam Integer id, @ModelAttribute("selected_labCenter") Integer cid) throws Exception {

		List<Map<String, String>> limits = new LinkedList<Map<String, String>>();
		Map<String, String> limit = null;

		// 获取room禁用时间
		LabRoomDevice labRoomDevice = labRoomDeviceService.findLabRoomDeviceByPrimaryKey(id);
		Integer roomID = labRoomDevice.getLabRoom().getId();
		Set<LabRoomLimitTime> limitTimes = labRoomLimitTimeDAO.findLabRoomLimitTimeByLabId(roomID);

		for (LabRoomLimitTime lrlt : limitTimes) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat hms = new SimpleDateFormat("HH:mm:ss");

			long sTime = lrlt.getSchoolTerm().getTermStart().getTime().getTime();

			Integer weekday = lrlt.getWeekday();

			Integer startweek = lrlt.getStartweek();
			Integer endweek = lrlt.getEndweek();

			Integer startclass = lrlt.getStartclass();
			Integer endclass = lrlt.getEndclass();

			for (int i = startweek; i <= endweek; i++) {

				long dlT = (i - 1) * 7 * 24 * 3600 * 1000l + (weekday - 1) * 24 * 3600 * 1000l;
				String date = sdf.format(new Date(sTime + dlT));

				SystemTime STime = systemTimeDAO.findSystemTimeById(startclass);
				SystemTime ETime = systemTimeDAO.findSystemTimeById(endclass);

				limit = new HashMap<String, String>();
				limit.put("start_date", date + " " + hms.format(STime.getStartDate().getTime()));
				limit.put("end_date", date + " " + hms.format(ETime.getEndDate().getTime()));
				limit.put("color", "orange");
				limit.put("text", "设备禁止时间段");

				limits.add(limit);
			}

		}

		/*
		 * Gson gson = new Gson(); System.out.println(gson.toJson(limits));
		 */

		return limits;
	}

	/****************************************************************************
	 * @功能：实验室设备管理---设备管理---编辑
	 * @作者：李小龙
	 ****************************************************************************/
	@RequestMapping("/device/editDeviceReservationInfo")
	public ModelAndView editDeviceReservationInfo(@RequestParam Integer id) {
		// 新建ModelAndView对象；
		ModelAndView mav = new ModelAndView();
		// id对应的实验分室设备
		LabRoomDevice device = labRoomDeviceService.findLabRoomDeviceByPrimaryKey(id);
		mav.addObject("device", device);
		// 设备管理员
		User user = shareService.getUser();
		List<User> users = shareService.findTheSameCollegeTeacher(user.getSchoolAcademy().getAcademyNumber());
		mav.addObject("users", users);
		// 设备状态
		//Set<CDeviceStatus> stutus = cDeviceStatusDAO.findAllCDeviceStatuss();
		List<CDictionary> stutus = shareService.getCDictionaryData("c_lab_room_device_status");
		mav.addObject("stutus", stutus);
		// 所属类型
		//Set<CDeviceType> types = cDeviceTypeDAO.findAllCDeviceTypes();
		List<CDictionary> types = shareService.getCDictionaryData("c_lab_room_device_type");
		mav.addObject("types", types);
		// 收费标准
		//Set<CDeviceCharge> charges = cDeviceChargeDAO.findAllCDeviceCharges();
		List<CDictionary> charges = shareService.getCDictionaryData("c_lab_room_device_charge");
		mav.addObject("charges", charges);

		// 当前@时间所属的学期
		Calendar time = Calendar.getInstance();
		SchoolTerm term = shareService.getBelongsSchoolTerm(time);
		// 根据学期和设备查询培训
		List<LabRoomDeviceTraining> trainings = labRoomDeviceService.findLabRoomDeviceTrainingByTermId(term.getId(), id);
		mav.addObject("trainings", trainings);
		// 培训表单的对象
		mav.addObject("train", new LabRoomDeviceTraining());
		// 培训教师

		// 安全准入形式
		//CLabAccessType accessType = device.getCLabAccessType();
		CDictionary accessType = device.getCDictionaryBySecurityAccessType();
		if (accessType != null) {
			if (accessType.getCNumber() != null && "1".equals(accessType.getCNumber())) {// 现场培训
				mav.setViewName("/device/lab_room_device_access/deviceTraining.jsp");
			}
			if (accessType.getCNumber() != null && "2".equals(accessType.getCNumber())) {// 网上考试
				// 答题设置

				mav.setViewName("/device/lab_room_device_access/deviceExam.jsp");
			}
		} else {// 默认为现场培训
			mav.setViewName("/device/lab_room_device_access/deviceTraining.jsp");
		}

		return mav;
	}

	/****************************************************************************
	 * @功能：查看设备
	 * @作者：李小龙
	 ****************************************************************************/
	@RequestMapping("/device/showDeviceTraining")
	public ModelAndView showDeviceTraining(@RequestParam Integer id) {
		// 新建ModelAndView对象；
		ModelAndView mav = new ModelAndView();
		// id对应的实验分室设备
		LabRoomDevice device = labRoomDeviceService.findLabRoomDeviceByPrimaryKey(id);
		mav.addObject("device", device);

		mav.setViewName("/device/lab_room_device/showDeviceTraining.jsp");
		return mav;
	}

	/****************************************************************************
	 * @功能：保存实验设备培训
	 * @作者：李小龙
	 ****************************************************************************/
	@RequestMapping("/device/saveLabRoomDeviceTraining")
	public ModelAndView saveLabRoomDeviceTraining(@ModelAttribute LabRoomDeviceTraining train, @RequestParam Integer deviceId, @ModelAttribute("selected_labCenter") Integer cid) {
		// 新建ModelAndView对象；
		ModelAndView mav = new ModelAndView();
		// id对应的实验分室设备
		LabRoomDevice device = labRoomDeviceService.findLabRoomDeviceByPrimaryKey(deviceId);
		train.setLabRoomDevice(device);
		// 当前@时间所属学期为培训所属学期
		SchoolTerm term = shareService.getBelongsSchoolTerm(train.getTime());
		train.setSchoolTerm(term);
		// 状态设置为待培训
		//CTrainingStatus status = cTrainingStatusDAO.findCTrainingStatusByPrimaryKey(1);
		CDictionary status = shareService.getCDictionaryByCategory("c_training_status", "1");
		train.setCTrainingStatus(status);
		// 参加人数默认为0
		train.setJoinNumber(0);
		if (train.getUser() != null && train.getUser().getUsername() != null && !train.getUser().getUsername().equals("")) {
			train.getUser().setUsername(train.getUser().getUsername());
		} else {
			train.setUser(null);
		}
		labRoomDeviceService.saveLabRoomDeviceTraining(train);
		// 中心所属学院
		String academy = labCenterDAO.findLabCenterByPrimaryKey(cid).getSchoolAcademy().getAcademyNumber();
		boolean flag = specialAcademy.contains(academy);
		if (flag) {
			mav.setViewName("redirect:/device/deviceTraining?deviceId=" + deviceId);
		} else {
			mav.setViewName("redirect:/device/editDeviceReservationInfo?id=" + deviceId);
		}

		return mav;
	}
	
	 

	/****************************************************************************
	 * @功能：实验室设备管理---设备管理---保存实验设备信息
	 * @作者：李小龙
	 ****************************************************************************/
	@RequestMapping("/device/saveDeviceReservationInfo")
	public ModelAndView saveDeviceReservationInfo(@ModelAttribute LabRoomDevice device, @RequestParam Integer deviceId, @ModelAttribute("selected_labCenter") Integer cid) {
		// 新建ModelAndView对象；
		ModelAndView mav = new ModelAndView();
		// id对应的实验室设备
		LabRoomDevice d = labRoomDeviceService.findLabRoomDeviceByPrimaryKey(deviceId);
		User user = new User();
		if (device.getUser() != null) {
			user = userDAO.findUserByPrimaryKey(device.getUser().getUsername());
			d.setUser(user);
		}
		/*if (device.getCDeviceStatus() != null && device.getCDeviceStatus().getId() != null) {
			d.setCDeviceStatus(device.getCDeviceStatus());
		}
		if (device.getCDeviceType() != null && device.getCDeviceType().getId() != null) {
			d.setCDeviceType(device.getCDeviceType());
		}
		if (device.getCDeviceCharge() != null && device.getCDeviceCharge().getId() != null) {
			d.setCDeviceCharge(device.getCDeviceCharge());
		}*/
		if (device.getCDictionaryByDeviceStatus() != null && device.getCDictionaryByDeviceStatus().getId() != null) {
			d.setCDictionaryByDeviceStatus(device.getCDictionaryByDeviceStatus());
		}
		if (device.getCDictionaryByDeviceType() != null && device.getCDictionaryByDeviceType().getId() != null) {
			d.setCDictionaryByDeviceType(device.getCDictionaryByDeviceType());
		}
		if (device.getCDictionaryByDeviceCharge() != null && device.getCDictionaryByDeviceCharge().getId() != null) {
			d.setCDictionaryByDeviceCharge(device.getCDictionaryByDeviceCharge());
		}
		d.setIndicators(device.getIndicators());

		d.setPrice(device.getPrice());
		d.setFunction(device.getFunction());
		d.setFeatures(device.getFeatures());
		d.setApplications(device.getApplications());
		labRoomDeviceService.save(d);
		// 给用户赋予权限
		if (user != null) {

			Set<Authority> ahths = user.getAuthorities();

			Authority a = authorityDAO.findAuthorityByPrimaryKey(10);// 设备管理员
			ahths.add(a);
			user.setAuthorities(ahths);
			userDAO.store(user);
		}
		// 中心所属学院
		String academy = labCenterDAO.findLabCenterByPrimaryKey(cid).getSchoolAcademy().getAcademyNumber();
		if (academy.equals("0201")) {// 纺织学院
			mav.setViewName("redirect:/device/listLabRoomDeviceNew?roomId=" + d.getLabRoom().getId() + "&page=1");

		} else
			mav.setViewName("redirect:/device/listLabRoomDevice?page=1");
		// mav.setViewName("redirect:/device/listLabRoomDevice?page=1");
		return mav;
	}

	/****************************************************************************
	 * @功能：根据设备id查询培训
	 * @作者：李小龙
	 ****************************************************************************/
	@RequestMapping("/device/findAllTrainingByDeviceId")
	public ModelAndView findAllTrainingByDeviceId(@ModelAttribute LabRoomDeviceTraining train, @RequestParam Integer deviceId) {
		// 新建ModelAndView对象；
		ModelAndView mav = new ModelAndView();
		// 培训查询表单的对象
		mav.addObject("train", train);
		// 设备id
		mav.addObject("deviceId", deviceId);
		// 学期
		List<SchoolTerm> terms = shareService.findAllSchoolTerms();
		mav.addObject("terms", terms);
		// 当前登录人
		User user = shareService.getUser();
		mav.addObject("user", user);
		// 根据设备id和培训对象查询培训
		List<LabRoomDeviceTraining> trainList = labRoomDeviceService.findLabRoomDeviceTrainingByDeviceId(train, deviceId);

		mav.addObject("trainList", trainList);
		// 当前登录人是否参加过培训
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		// 第一个培训的培训名单
		if (trainList.size() > 0) {
			for (LabRoomDeviceTraining t : trainList) {
				int i = t.getId();
				List<LabRoomDeviceTrainingPeople> peoples = labRoomDeviceService.findTrainingPeoplesByTrainingId(i);
				mav.addObject("peoples", peoples);
				int flag = 1;// 默认为1：未参加，0为已参加
				for (LabRoomDeviceTrainingPeople p : peoples) {
					if (p.getUser().getUsername().equals(user.getUsername())) {
						flag = 0;
						break;
					} else {
						flag = 1;
					}
				}
				map.put(i, flag);
			}

		}
		mav.addObject("map", map);

		List<User> userList = shareService.findTheSameCollegeUser(user.getSchoolAcademy().getAcademyNumber());
		mav.addObject("userList", userList);
		// 培训结果
		//Set<CTrainingResult> results = cTrainingResultDAO.findAllCTrainingResults();
		List<CDictionary> results = shareService.getCDictionaryData("c_training_result"); 
		mav.addObject("results", results);

		mav.setViewName("/device/lab_room_device_access/trainList.jsp");
		return mav;
	}

	/****************************************************************************
	 * @功能：根据设备id查询培训和培训人名单
	 * @作者：李小龙
	 ****************************************************************************/
	@RequestMapping("/device/findTrainingPeopleByDeviceIdAndTrainId")
	public ModelAndView findTrainingPeopleByDeviceIdAndTrainId(@ModelAttribute LabRoomDeviceTraining train, @RequestParam Integer deviceId, Integer id) {
		// 新建ModelAndView对象；
		ModelAndView mav = new ModelAndView();
		// id对应的实验室设备培训
		// 培训查询表单的对象
		mav.addObject("train", train);
		// 设备id
		mav.addObject("deviceId", deviceId);
		// 学期
		List<SchoolTerm> terms = shareService.findAllSchoolTerms();
		mav.addObject("terms", terms);
		// 培训人
		User user = shareService.getUser();
		List<User> userList = shareService.findTheSameCollegeUser(user.getSchoolAcademy().getAcademyNumber());
		mav.addObject("userList", userList);
		// 培训结果
		//Set<CTrainingResult> results = cTrainingResultDAO.findAllCTrainingResults();
		List<CDictionary> results = shareService.getCDictionaryData("c_training_result");
		mav.addObject("results", results);

		// 根据设备id查询培训
		List<LabRoomDeviceTraining> trainList = labRoomDeviceService.findLabRoomDeviceTrainingByDeviceId(train, deviceId);
		mav.addObject("trainList", trainList);
		// 根据培训id查询培训名单
		List<LabRoomDeviceTrainingPeople> peoples = labRoomDeviceService.findTrainingPeoplesByTrainingId(id);

		mav.addObject("peoples", peoples);
		// 当前登录人是否参加过培训
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		// 第一个培训的培训名单
		if (trainList.size() > 0) {
			for (LabRoomDeviceTraining t : trainList) {
				int i = t.getId();
				List<LabRoomDeviceTrainingPeople> ps = labRoomDeviceService.findTrainingPeoplesByTrainingId(i);
				int flag = 1;
				for (LabRoomDeviceTrainingPeople p : ps) {
					if (p.getUser().getUsername().equals(user.getUsername())) {
						flag = 0;
						break;
					} else {
						flag = 1;
					}
				}
				map.put(i, flag);
			}

		}
		mav.addObject("map", map);
		mav.setViewName("/device/lab_room_device_access/trainingPeopleList.jsp");
		return mav;
	}

	/****************************************************************************
	 * @功能：保存实验设备培训人
	 * @作者：李小龙
	 ****************************************************************************/
	@RequestMapping("/device/saveLabRoomDeviceTrainingPeople")
	public ModelAndView saveLabRoomDeviceTrainingPeople(@ModelAttribute LabRoomDeviceTrainingPeople people) {
		// 新建ModelAndView对象；
		ModelAndView mav = new ModelAndView();
		int id = people.getLabRoomDeviceTraining().getId();
		// id对应的实验室设备培训
		LabRoomDeviceTraining train = labRoomDeviceTrainingDAO.findLabRoomDeviceTrainingByPrimaryKey(id);

		String usernames = people.getUser().getUsername();
		String name[] = usernames.split(",");
		for (int i = 0; i < name.length; i++) {
			// 培训对象
			LabRoomDeviceTrainingPeople p = new LabRoomDeviceTrainingPeople();
			p.setLabRoomDeviceTraining(train);
			// 培训人
			User u = userDAO.findUserByPrimaryKey(name[i]);
			p.setUser(u);
			// 培训结果
			//CTrainingResult result = cTrainingResultDAO.findCTrainingResultByPrimaryKey(2);
			CDictionary result = shareService.getCDictionaryByCategory("c_training_result", "2");
			
			p.setCDictionary(result);

			labRoomDeviceTrainingPeopleDAO.store(p);
		}

		mav.setViewName("redirect:/device/findAllTrainingByDeviceId?deviceId=" + train.getLabRoomDevice().getId());
		return mav;
	}

	/****************************************************************************
	 * @功能：保存实验设备培训结果
	 * @作者：李小龙
	 ****************************************************************************/
	@RequestMapping("/device/saveTrainResult")
	public ModelAndView saveTrainResult(@RequestParam int idArray[], int valueArray[], @ModelAttribute("selected_labCenter") Integer cid) {
		// 新建ModelAndView对象；
		ModelAndView mav = new ModelAndView();
		for (int i = 0; i < idArray.length; i++) {
			// 培训人
			LabRoomDeviceTrainingPeople p = labRoomDeviceTrainingPeopleDAO.findLabRoomDeviceTrainingPeopleByPrimaryKey(idArray[i]);
			// 培训结果
			//CTrainingResult result = cTrainingResultDAO.findCTrainingResultByPrimaryKey(valueArray[i]);
			CDictionary result = shareService.getCDictionaryByCategory("c_training_result", String.valueOf(valueArray[i]));
			
			p.setCDictionary(result);
			// 执行保存
			labRoomDeviceTrainingPeopleDAO.store(p);

			// 将通过的学生添加到LabRoomDevicePermitUsers中，flag为2
			// 先将该用户从permitUser里面清除，防止重复添加
			if (p.getUser() != null && p.getLabRoomDeviceTraining() != null && p.getLabRoomDeviceTraining().getLabRoomDevice() != null) {
				String username1 = p.getUser().getUsername();
				int deviceId = p.getLabRoomDeviceTraining().getLabRoomDevice().getId();
				LabRoomDevicePermitUsers permitUser = labRoomDeviceService.findPermitUserByUsernameAndDeivce(username1, deviceId);
				if (permitUser != null) {
					labRoomDeviceService.deletePermitUser(permitUser);
				}
			}
			if (valueArray[i] == 1) {// 通过
				// username对应的用户
				LabRoomDevicePermitUsers student = new LabRoomDevicePermitUsers();
				student.setUser(p.getUser());
				if (p.getLabRoomDeviceTraining() != null && p.getLabRoomDeviceTraining().getLabRoomDevice() != null) {
					student.setLabRoomDevice(p.getLabRoomDeviceTraining().getLabRoomDevice());
				}
				student.setFlag(2);// 标记位（1 单独培训通过 2 集训通过 3 集训后门）
				labRoomDevicePermitUsersDAO.store(student);
			} else {// 不通过
					// do nothing
			}
		}
		// 培训人对应的培训
		LabRoomDeviceTrainingPeople people = labRoomDeviceTrainingPeopleDAO.findLabRoomDeviceTrainingPeopleByPrimaryKey(idArray[0]);
		LabRoomDeviceTraining train = people.getLabRoomDeviceTraining();
		// 该培训的培训人
		Set<LabRoomDeviceTrainingPeople> peoples = train.getLabRoomDeviceTrainingPeoples();
		// 根据培训id查询培训通过的人
		List<LabRoomDeviceTrainingPeople> passPeoples = labRoomDeviceService.findPassLabRoomDeviceTrainingPeopleByTrainId(train.getId());

		// 计算通过率
		double a = passPeoples.size();
		double b = peoples.size();
		double c = a / b;
		// 获取格式化对象
		NumberFormat nt = NumberFormat.getPercentInstance();
		// 设置百分数精确度2即保留两位小数
		nt.setMinimumFractionDigits(2);
		String s = nt.format(c);
		train.setPassRate(s);
		// 状态改为已完成
		//CTrainingStatus status = cTrainingStatusDAO.findCTrainingStatusByPrimaryKey(2);
		CDictionary status = shareService.getCDictionaryByCategory("c_training_status", "2");
		train.setCTrainingStatus(status);
		labRoomDeviceTrainingDAO.store(train);

		mav.setViewName("redirect:/device/listLabRoomDeviceTraining?currpage=1&isTeacher=1");

		return mav;
	}

	/****************************************************************************
	 * @功能：保存实验设备培训人
	 * @作者：李小龙
	 ****************************************************************************/
	@RequestMapping("/device/joinTraining")
	public ModelAndView joinTraining(@RequestParam Integer id, @ModelAttribute("selected_labCenter") Integer cid) {
		// 新建ModelAndView对象；
		ModelAndView mav = new ModelAndView();
		// id对应的培训
		LabRoomDeviceTraining train = labRoomDeviceTrainingDAO.findLabRoomDeviceTrainingByPrimaryKey(id);
		User user = shareService.getUser();
		LabRoomDeviceTrainingPeople people = new LabRoomDeviceTrainingPeople();
		people.setLabRoomDeviceTraining(train);
		people.setUser(user);
		labRoomDeviceTrainingPeopleDAO.store(people);
		Set<LabRoomDeviceTrainingPeople> peoples = train.getLabRoomDeviceTrainingPeoples();
		train.setJoinNumber(peoples.size());
		labRoomDeviceTrainingDAO.store(train);
		// 中心所属学院
		String academy = labCenterDAO.findLabCenterByPrimaryKey(cid).getSchoolAcademy().getAcademyNumber();
		boolean flag = specialAcademy.contains(academy);
		if (flag) {
			mav.setViewName("redirect:/device/deviceTraining?deviceId=" + train.getLabRoomDevice().getId());
		} else {
			mav.setViewName("redirect:/device/findTrainingPeopleByDeviceIdAndTrainId?deviceId=" + train.getLabRoomDevice().getId() + "&id=" + train.getId());
		}

		return mav;
	}

	/****************************************************************************
	 * @功能：删除培训
	 * @作者：李小龙
	 ****************************************************************************/
	@RequestMapping("/device/deleteLabRoomDeviceTrain")
	public ModelAndView deleteLabRoomDeviceTrain(@RequestParam Integer id) {
		// 新建ModelAndView对象；
		ModelAndView mav = new ModelAndView();
		// id对应的培训
		LabRoomDeviceTraining t = labRoomDeviceTrainingDAO.findLabRoomDeviceTrainingByPrimaryKey(id);
		int deviceId = t.getLabRoomDevice().getId();
		labRoomDeviceTrainingDAO.remove(t);
		mav.setViewName("redirect:/device/editDeviceReservationInfo?id=" + deviceId);
		return mav;
	}

	/****************************************************************************
	 * @功能：AJAX查询设备使用记录
	 * @作者：徐龙帅
	 ****************************************************************************/
	@RequestMapping("/device/findUsedDevice")
	public @ResponseBody String findUsedDevice(@RequestParam Integer deciceId) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// System.out.println("lll");
		List<LabRoomDeviceReservation> showRecord = labRoomDeviceReservationDAO.executeQuery("SELECT l FROM LabRoomDeviceReservation l  WHERE l.labRoomDevice.id=" + deciceId);
		// System.out.println("showRecord="+showRecord);
		String s = "";
		for (LabRoomDeviceReservation l : showRecord) {
			s += "<tr>" + "<td>" + l.getUserByReserveUser().getCname() + "</td>" + "<td>" + l.getLabRoomDevice().getSchoolDevice().getDeviceNumber() + "</td>" + "<td>" + l.getLabRoomDevice().getSchoolDevice().getDeviceName() + "</td>" + "<td>" + l.getLabRoomDevice().getSchoolDevice().getDevicePattern() + "</td>" + "<td>" + sdf.format(l.getBegintime().getTime()) + "</td>" + "<td>" + sdf.format(l.getEndtime().getTime()) + "</td>" + "<td>" + l.getUseTime() + "</td>" + "</tr>";
		}
		// System.out.println("s="+s);
		return htmlEncode(s);

	}

	/****************************************************************************
	 * @功能：处理ajax中文乱码
	 * @作者：李小龙
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
	 * @功能：设备出借申请单
	 * @作者：李鹏翔
	 ****************************************************************************/
	@RequestMapping("/device/deviceLendApply")
	public ModelAndView deviceLendApply(@RequestParam Integer idKey, @ModelAttribute("selected_labCenter") Integer cid) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("idKey", idKey);
		mav.addObject("date", shareService.getDate());
		mav.addObject("labRoomDeviceLend", new LabRoomDeviceLending());
		mav.addObject("labRoomDevice", labRoomDeviceService.findLabRoomDeviceByPrimaryKey(idKey));
		mav.addObject("user", shareService.getUser());
		// 查找当前中心所属学院
		String num = labCenterDAO.findLabCenterById(cid).getSchoolAcademy().getAcademyNumber();
		mav.addObject("users", shareService.findTheSameCollegeTeacher(num));
		mav.setViewName("/device/lab_room_device/deviceLendApply.jsp");
		return mav;
	}

	/****************************************************************************
	 * @功能：设备领用申请单
	 * @作者：李鹏翔
	 ****************************************************************************/
	@RequestMapping("/device/deviceKeepApply")
	public ModelAndView deviceKeepApply(@RequestParam Integer idKey, @ModelAttribute("selected_labCenter") Integer cid) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("idKey", idKey);
		mav.addObject("date", shareService.getDate());
		mav.addObject("labRoomDeviceLend", new LabRoomDeviceLending());
		mav.addObject("labRoomDevice", labRoomDeviceService.findLabRoomDeviceByPrimaryKey(idKey));
		mav.addObject("user", shareService.getUser());
		// 查找当前中心所属学院
		String num = labCenterDAO.findLabCenterById(cid).getSchoolAcademy().getAcademyNumber();
		mav.addObject("users", shareService.findTheSameCollegeTeacher(num));
		mav.setViewName("/device/lab_room_device/deviceKeepApply.jsp");
		return mav;
	}

	/****************************************************************************
	 * @功能：保存设备出借申请单
	 * @作者：李鹏翔
	 ****************************************************************************/
	@RequestMapping("/device/saveDeviceLendApply")
	public String saveDeviceLendApply(@ModelAttribute LabRoomDeviceLending lrdl) {
		labRoomDeviceService.saveDeviceLendApply(lrdl);
		CDictionary cDictionary=shareService.getCDictionaryByCategory("c_lab_room_device_status", "5");
		LabRoomDevice labRoomDevice = labRoomDeviceService.findLabRoomDeviceByPrimaryKey(lrdl.getLabRoomDevice().getId());
		labRoomDevice.setCDictionaryByDeviceStatus(cDictionary);//将设备状态设为借用中
		labRoomDeviceService.save(labRoomDevice);
		return "redirect:/device/deviceLendList?page=1";
	}

	/****************************************************************************
	 * @功能：保存设备领用申请单
	 * @作者：李鹏翔
	 ****************************************************************************/
	@RequestMapping("/device/saveDeviceKeepApply")
	public String saveDeviceKeepApply(@ModelAttribute LabRoomDeviceLending lrdl) {
		labRoomDeviceService.saveDeviceLendApply(lrdl);
		return "redirect:/device/deviceKeepList?page=1";
	}

	/****************************************************************************
	 * @功能：设备出借申请单列表
	 * @作者：李鹏翔
	 ****************************************************************************/
	@RequestMapping("/device/deviceLendList")
	public ModelAndView deviceLendList(@ModelAttribute LabRoomDeviceLending lrdl, @RequestParam Integer page, @ModelAttribute("selected_labCenter") Integer cid) {
		// 新建ModelAndView对象；
		ModelAndView mav = new ModelAndView();
		// 学期
		List<SchoolTerm> terms = shareService.findAllSchoolTerms();
		mav.addObject("terms", terms);
		mav.addObject("reservation", lrdl);
		// 查询出所有的设备设备预约记录
		int totalRecords = labRoomDeviceService.getLabRoomLendsTotals(lrdl);
		int pageSize = 10;// 每页10条记录
		// 分页信息
		Map<String, Integer> pageModel = shareService.getPage(page, pageSize, totalRecords);
		// 根据分页信息查询出来的记录
		List<LabRoomDeviceLending> deviceLendList = labRoomDeviceService.findAllLabRoomLends(lrdl, page, pageSize);
		mav.addObject("deviceLendList", deviceLendList);

		mav.addObject("cid", cid);
		mav.addObject("pageModel", pageModel);
		mav.addObject("totalRecords", totalRecords);
		mav.addObject("page", page);
		mav.addObject("pageSize", pageSize);

		// 当前登录人
		User user = shareService.getUser();
		// 判断当前登录人是否为实验教务或者超级管理员或者是本中心主任
		if (user.getAuthorities().size() > 0) {
			for (Authority a : user.getAuthorities()) {
				if (a.getId() == 7 || a.getId() == 11) {
					mav.addObject("admin", true);
				}
				if (a.getId() == 4) {
					mav.addObject("ca", true);
				}
			}
		}
		mav.addObject("user", user);
		mav.setViewName("/device/lab_room_device/deviceLendList.jsp");
		return mav;
	}

	/****************************************************************************
	 * @功能：设备领用申请单列表
	 * @作者：李鹏翔
	 ****************************************************************************/
	@RequestMapping("/device/deviceKeepList")
	public ModelAndView deviceKeepList(@ModelAttribute LabRoomDeviceLending lrdl, @RequestParam Integer page, @ModelAttribute("selected_labCenter") Integer cid) {
		// 新建ModelAndView对象；
		ModelAndView mav = new ModelAndView();
		// 学期
		List<SchoolTerm> terms = shareService.findAllSchoolTerms();
		mav.addObject("terms", terms);
		mav.addObject("reservation", lrdl);
		// 查询出所有的设备设备预约记录
		int totalRecords = labRoomDeviceService.getLabRoomKeepsTotals(lrdl);
		int pageSize = 10;// 每页10条记录
		// 分页信息
		Map<String, Integer> pageModel = shareService.getPage(page, pageSize, totalRecords);
		// 根据分页信息查询出来的记录
		List<LabRoomDeviceLending> deviceLendList = labRoomDeviceService.findAllLabRoomKeeps(lrdl, page, pageSize);
		mav.addObject("deviceLendList", deviceLendList);

		mav.addObject("cid", cid);
		mav.addObject("pageModel", pageModel);
		mav.addObject("totalRecords", totalRecords);
		mav.addObject("page", page);
		mav.addObject("pageSize", pageSize);

		// 当前登录人
		User user = shareService.getUser();
		// 判断当前登录人是否为实验教务或者超级管理员或者是本中心主任
		if (user.getAuthorities().size() > 0) {
			for (Authority a : user.getAuthorities()) {
				if (a.getId() == 7 || a.getId() == 11) {
					mav.addObject("admin", true);
				}
				if (a.getId() == 4) {
					mav.addObject("ca", true);
				}
			}
		}
		mav.addObject("user", user);
		mav.setViewName("/device/lab_room_device/deviceKeepList.jsp");
		return mav;
	}

	/****************************************************************************
	 * @功能：审核设备出借申请单
	 * @作者：李鹏翔
	 ****************************************************************************/
	@RequestMapping("/device/auditDeviceLending")
	public ModelAndView auditDeviceLending(@RequestParam Integer idKey) {
		ModelAndView mav = new ModelAndView();
		LabRoomDeviceLending lrdl = labRoomDeviceService.findDeviceLendingById(idKey);
		mav.addObject("lrdl", lrdl);
		mav.addObject("idKey", idKey);
		mav.addObject("user", shareService.getUser());
		mav.addObject("result", labRoomDeviceService.getAuditResultMap());
		mav.addObject("lrdlr", new LabRoomDeviceLendingResult());
		mav.setViewName("/device/lab_room_device/auditDeviceLending.jsp");
		return mav;
	}

	/****************************************************************************
	 * @功能：审核设备领出申请单
	 * @作者：李鹏翔
	 ****************************************************************************/
	@RequestMapping("/device/auditDeviceKeeping")
	public ModelAndView auditDeviceKeeping(@RequestParam Integer idKey) {
		ModelAndView mav = new ModelAndView();
		LabRoomDeviceLending lrdl = labRoomDeviceService.findDeviceLendingById(idKey);
		mav.addObject("lrdl", lrdl);
		mav.addObject("idKey", idKey);
		mav.addObject("user", shareService.getUser());
		mav.addObject("result", labRoomDeviceService.getAuditResultMap());
		mav.addObject("lrdlr", new LabRoomDeviceLendingResult());
		mav.setViewName("/device/lab_room_device/auditDeviceKeeping.jsp");
		return mav;
	}

	/****************************************************************************
	 * @功能：保存审核的申请单
	 * @作者：李鹏翔
	 ****************************************************************************/
	@RequestMapping("/device/saveAuditDeviceLending")
	public String saveAuditDeviceLending(@ModelAttribute LabRoomDeviceLendingResult lrdlr, Integer idKey) {
		LabRoomDeviceLendingResult l = labRoomDeviceLendingResultDAO.store(lrdlr);
		LabRoomDeviceLending lrdl = labRoomDeviceService.findDeviceLendingById(idKey);
		LabRoomDevice lrd = lrdl.getLabRoomDevice();
		/*CDeviceStatus cds = new CDeviceStatus();
		cds.setId(5);*/
		CDictionary cds = shareService.getCDictionaryByCategory("c_lab_room_device_status", "5");
		lrd.setCDictionaryByDeviceStatus(cds);
		labRoomDeviceDAO.store(lrd);
		if ("2".equals(l.getCDictionary().getCNumber())) {
			/*CLendingStatus cls = new CLendingStatus();
			cls.setId(1);*/
			CDictionary cls = shareService.getCDictionaryByCategory("c_lending_status", "1");
			lrdl.setCDictionary(cls);
			labRoomDeviceLendingDAO.store(lrdl);
		}
		return "redirect:/device/deviceLendList?page=1";
	}

	/****************************************************************************
	 * @功能：保存审核的申请单
	 * @作者：李鹏翔
	 ****************************************************************************/
	@RequestMapping("/device/saveAuditDeviceKeeping")
	public String saveAuditDeviceKeeping(@ModelAttribute LabRoomDeviceLendingResult lrdlr, Integer idKey) {
		LabRoomDeviceLendingResult l = labRoomDeviceLendingResultDAO.store(lrdlr);
		LabRoomDeviceLending lrdl = labRoomDeviceService.findDeviceLendingById(idKey);
		LabRoomDevice lrd = lrdl.getLabRoomDevice();
		CDictionary cds = shareService.getCDictionaryByCategory("c_lab_room_device_status", "6");
		lrd.setCDictionaryByDeviceStatus(cds);
		labRoomDeviceDAO.store(lrd);
		if ("2".equals(l.getCDictionary().getCNumber())) {
			/*CLendingStatus cls = new CLendingStatus();
			cls.setId(1);
			lrdl.setCLendingStatus(cls);*/
			CDictionary cls = shareService.getCDictionaryByCategory("c_lending_status", "1");
			lrdl.setCDictionary(cls);
			labRoomDeviceLendingDAO.store(lrdl);
		}
		
		return "redirect:/device/deviceKeepList?page=1";
	}

	/****************************************************************************
	 * @功能：审核通过的借用结果
	 * @作者：李鹏翔
	 ****************************************************************************/
	@RequestMapping("/device/passDeviceLendList")
	public ModelAndView passDeviceLendList(@ModelAttribute LabRoomDeviceLendingResult lrdlr, Integer page, @ModelAttribute("selected_labCenter") Integer cid) {
		ModelAndView mav = new ModelAndView();
		// 学期
		List<SchoolTerm> terms = shareService.findAllSchoolTerms();
		mav.addObject("terms", terms);
		mav.addObject("reservation", lrdlr);
		// 查询出所有的设备设备预约记录
		int totalRecords = labRoomDeviceService.getPassLendingTotals(lrdlr);
		int pageSize = 10;// 每页10条记录
		// 分页信息
		Map<String, Integer> pageModel = shareService.getPage(page, pageSize, totalRecords);
		// 根据分页信息查询出来的记录
		List<LabRoomDeviceLendingResult> deviceLendList = labRoomDeviceService.findAllPassLending(lrdlr, page, pageSize);
		mav.addObject("deviceLendList", deviceLendList);

		mav.addObject("cid", cid);
		mav.addObject("pageModel", pageModel);
		mav.addObject("totalRecords", totalRecords);
		mav.addObject("page", page);
		mav.addObject("pageSize", pageSize);

		// 当前登录人
		User user = shareService.getUser();
		// 判断当前登录人是否为实验教务或者超级管理员或者是本中心主任
		if (user.getAuthorities().size() > 0) {
			for (Authority a : user.getAuthorities()) {
				if (a.getId() == 7 || a.getId() == 4 || a.getId() == 11) {
					mav.addObject("ca", true);
				}
			}
		}
		mav.addObject("user", user);
		mav.setViewName("/device/lab_room_device/passDeviceLendList.jsp");
		return mav;
	}

	/****************************************************************************
	 * @功能：审核通过的领用结果
	 * @作者：李鹏翔
	 ****************************************************************************/
	@RequestMapping("/device/passDeviceKeepList")
	public ModelAndView passDeviceKeepList(@ModelAttribute LabRoomDeviceLendingResult lrdlr, Integer page, @ModelAttribute("selected_labCenter") Integer cid) {
		ModelAndView mav = new ModelAndView();
		// 学期
		List<SchoolTerm> terms = shareService.findAllSchoolTerms();
		mav.addObject("terms", terms);
		mav.addObject("reservation", lrdlr);
		// 查询出所有的设备设备预约记录
		int totalRecords = labRoomDeviceService.getPassKeepingTotals(lrdlr);
		int pageSize = 10;// 每页10条记录
		// 分页信息
		Map<String, Integer> pageModel = shareService.getPage(page, pageSize, totalRecords);
		// 根据分页信息查询出来的记录
		List<LabRoomDeviceLendingResult> deviceLendList = labRoomDeviceService.findAllPassKeeping(lrdlr, page, pageSize);
		mav.addObject("deviceLendList", deviceLendList);

		mav.addObject("cid", cid);
		mav.addObject("pageModel", pageModel);
		mav.addObject("totalRecords", totalRecords);
		mav.addObject("page", page);
		mav.addObject("pageSize", pageSize);

		// 当前登录人
		User user = shareService.getUser();
		// 判断当前登录人是否为实验教务或者超级管理员或者是本中心主任
		if (user.getAuthorities().size() > 0) {
			for (Authority a : user.getAuthorities()) {
				if (a.getId() == 7 || a.getId() == 4 || a.getId() == 11) {
					mav.addObject("ca", true);
				}
			}
		}
		mav.addObject("user", user);
		mav.setViewName("/device/lab_room_device/passDeviceKeepList.jsp");
		return mav;
	}

	/****************************************************************************
	 * @功能：审核被拒绝的借用结果
	 * @作者：李鹏翔
	 ****************************************************************************/
	@RequestMapping("/device/rejectedDeviceLendList")
	public ModelAndView rejectedDeviceLendList(@ModelAttribute LabRoomDeviceLendingResult lrdlr, Integer page, @ModelAttribute("selected_labCenter") Integer cid) {
		ModelAndView mav = new ModelAndView();
		// 学期
		List<SchoolTerm> terms = shareService.findAllSchoolTerms();
		mav.addObject("terms", terms);
		mav.addObject("reservation", lrdlr);
		// 查询出所有的设备设备预约记录
		int totalRecords = labRoomDeviceService.getRejectedLendingTotals(lrdlr);
		int pageSize = 10;// 每页10条记录
		// 分页信息
		Map<String, Integer> pageModel = shareService.getPage(page, pageSize, totalRecords);
		// 根据分页信息查询出来的记录
		List<LabRoomDeviceLendingResult> deviceLendList = labRoomDeviceService.findAllRejectedLending(lrdlr, page, pageSize);
		mav.addObject("deviceLendList", deviceLendList);

		mav.addObject("cid", cid);
		mav.addObject("pageModel", pageModel);
		mav.addObject("totalRecords", totalRecords);
		mav.addObject("page", page);
		mav.addObject("pageSize", pageSize);

		// 当前登录人
		User user = shareService.getUser();
		// 判断当前登录人是否为实验教务或者超级管理员或者是本中心主任
		if (user.getAuthorities().size() > 0) {
			for (Authority a : user.getAuthorities()) {
				if (a.getId() == 7 || a.getId() == 4 || a.getId() == 11) {
					mav.addObject("ca", true);
				}
			}
		}
		mav.addObject("user", user);
		mav.setViewName("/device/lab_room_device/rejectedDeviceLending.jsp");
		return mav;
	}

	/****************************************************************************
	 * @功能：审核被拒绝的领用结果
	 * @作者：李鹏翔
	 ****************************************************************************/
	@RequestMapping("/device/rejectedDeviceKeepList")
	public ModelAndView rejectedDeviceKeepList(@ModelAttribute LabRoomDeviceLendingResult lrdlr, Integer page, @ModelAttribute("selected_labCenter") Integer cid) {
		ModelAndView mav = new ModelAndView();
		// 学期
		List<SchoolTerm> terms = shareService.findAllSchoolTerms();
		mav.addObject("terms", terms);
		mav.addObject("reservation", lrdlr);
		// 查询出所有的设备设备预约记录
		int totalRecords = labRoomDeviceService.getRejectedKeepingTotals(lrdlr);
		int pageSize = 10;// 每页10条记录
		// 分页信息
		Map<String, Integer> pageModel = shareService.getPage(page, pageSize, totalRecords);
		// 根据分页信息查询出来的记录
		List<LabRoomDeviceLendingResult> deviceLendList = labRoomDeviceService.findAllRejectedKeeping(lrdlr, page, pageSize);
		mav.addObject("deviceLendList", deviceLendList);

		mav.addObject("cid", cid);
		mav.addObject("pageModel", pageModel);
		mav.addObject("totalRecords", totalRecords);
		mav.addObject("page", page);
		mav.addObject("pageSize", pageSize);

		// 当前登录人
		User user = shareService.getUser();
		// 判断当前登录人是否为实验教务或者超级管理员或者是本中心主任
		if (user.getAuthorities().size() > 0) {
			for (Authority a : user.getAuthorities()) {
				if (a.getId() == 7 || a.getId() == 4 || a.getId() == 11) {
					mav.addObject("ca", true);
				}
			}
		}
		mav.addObject("user", user);
		mav.setViewName("/device/lab_room_device/rejectedDeviceKeeping.jsp");
		return mav;
	}

	/****************************************************************************
	 * @功能：已归还的设备借用审核单
	 * @作者：李鹏翔
	 ****************************************************************************/
	@RequestMapping("/device/returnedDeviceLendList")
	public ModelAndView returnedDeviceLendList(@ModelAttribute LabRoomDeviceLendingResult lrdlr, Integer page, @ModelAttribute("selected_labCenter") Integer cid) {
		ModelAndView mav = new ModelAndView();
		// 学期
		List<SchoolTerm> terms = shareService.findAllSchoolTerms();
		mav.addObject("terms", terms);
		mav.addObject("reservation", lrdlr);
		// 查询出所有的设备设备预约记录
		int totalRecords = labRoomDeviceService.getReturnedLendingTotals(lrdlr);
		int pageSize = 10;// 每页10条记录
		// 分页信息
		Map<String, Integer> pageModel = shareService.getPage(page, pageSize, totalRecords);
		// 根据分页信息查询出来的记录
		List<LabRoomDeviceLendingResult> deviceLendList = labRoomDeviceService.findAllReturnedLending(lrdlr, page, pageSize);
		mav.addObject("deviceLendList", deviceLendList);

		mav.addObject("cid", cid);
		mav.addObject("pageModel", pageModel);
		mav.addObject("totalRecords", totalRecords);
		mav.addObject("page", page);
		mav.addObject("pageSize", pageSize);

		// 当前登录人
		User user = shareService.getUser();
		// 判断当前登录人是否为实验教务或者超级管理员或者是本中心主任
		if (user.getAuthorities().size() > 0) {
			for (Authority a : user.getAuthorities()) {
				if (a.getId() == 7 || a.getId() == 4 || a.getId() == 11) {
					mav.addObject("ca", true);
				}
			}
		}
		mav.addObject("user", user);
		mav.setViewName("/device/lab_room_device/returnedDeviceLendList.jsp");
		return mav;
	}

	/****************************************************************************
	 * @功能：所有设备借用
	 * @作者：李鹏翔
	 ****************************************************************************/
	@RequestMapping("/device/allDeviceLendList")
	public ModelAndView allDeviceLendList(@ModelAttribute LabRoomDeviceLendingResult lrdlr, Integer page, @ModelAttribute("selected_labCenter") Integer cid) {
		ModelAndView mav = new ModelAndView();
		// 学期
		List<SchoolTerm> terms = shareService.findAllSchoolTerms();
		mav.addObject("terms", terms);
		mav.addObject("reservation", lrdlr);
		// 查询出所有的设备设备预约记录
		int totalRecords = labRoomDeviceService.getAllLendTotals();
		int pageSize = 10;// 每页10条记录
		// 分页信息
		Map<String, Integer> pageModel = shareService.getPage(page, pageSize, totalRecords);
		// 根据分页信息查询出来的记录
		List<SchoolDevice> deviceLendList = labRoomDeviceService.findAllLendingList(page, pageSize);
		mav.addObject("deviceLendList", deviceLendList);

		mav.addObject("cid", cid);
		mav.addObject("pageModel", pageModel);
		mav.addObject("totalRecords", totalRecords);
		mav.addObject("page", page);
		mav.addObject("pageSize", pageSize);

		// 当前登录人
		User user = shareService.getUser();
		// 判断当前登录人是否为实验教务或者超级管理员或者是本中心主任
		if (user.getAuthorities().size() > 0) {
			for (Authority a : user.getAuthorities()) {
				if (a.getId() == 7 || a.getId() == 4 || a.getId() == 11) {
					mav.addObject("ca", true);
				}
			}
		}
		mav.addObject("user", user);
		mav.setViewName("/device/lab_room_device/allDeviceLendList.jsp");
		return mav;
	}

	/****************************************************************************
	 * @功能：已归还的设备领用审核单
	 * @作者：李鹏翔
	 ****************************************************************************/
	@RequestMapping("/device/returnedDeviceKeepList")
	public ModelAndView returnedDeviceKeepList(@ModelAttribute LabRoomDeviceLendingResult lrdlr, Integer page, @ModelAttribute("selected_labCenter") Integer cid) {
		ModelAndView mav = new ModelAndView();
		// 学期
		List<SchoolTerm> terms = shareService.findAllSchoolTerms();
		mav.addObject("terms", terms);
		mav.addObject("reservation", lrdlr);
		// 查询出所有的设备设备预约记录
		int totalRecords = labRoomDeviceService.getReturnedKeepingTotals(lrdlr);
		int pageSize = 10;// 每页10条记录
		// 分页信息
		Map<String, Integer> pageModel = shareService.getPage(page, pageSize, totalRecords);
		// 根据分页信息查询出来的记录
		List<LabRoomDeviceLendingResult> deviceLendList = labRoomDeviceService.findAllReturnedKeeping(lrdlr, page, pageSize);
		mav.addObject("deviceLendList", deviceLendList);

		mav.addObject("cid", cid);
		mav.addObject("pageModel", pageModel);
		mav.addObject("totalRecords", totalRecords);
		mav.addObject("page", page);
		mav.addObject("pageSize", pageSize);

		// 当前登录人
		User user = shareService.getUser();
		// 判断当前登录人是否为实验教务或者超级管理员或者是本中心主任
		if (user.getAuthorities().size() > 0) {
			for (Authority a : user.getAuthorities()) {
				if (a.getId() == 7 || a.getId() == 4 || a.getId() == 11) {
					mav.addObject("ca", true);
				}
			}
		}
		mav.addObject("user", user);
		mav.setViewName("/device/lab_room_device/returnedDeviceKeepList.jsp");
		return mav;
	}

	/****************************************************************************
	 * @功能：借用设备的归还
	 * @作者：李鹏翔
	 ****************************************************************************/
	@RequestMapping("/device/returnDeviceLending")
	public String returnDeviceLending(@RequestParam Integer idKey) {
		labRoomDeviceService.returnDeviceLending(idKey);
		return "redirect:/device/passDeviceLendList?page=1";
	}

	/****************************************************************************
	 * @功能：领用设备的归还
	 * @作者：李鹏翔
	 ****************************************************************************/
	@RequestMapping("/device/returnDeviceKeeping")
	public String returnDeviceKeeping(@RequestParam Integer idKey) {
		labRoomDeviceService.returnDeviceLending(idKey);
		return "redirect:/device/passDeviceKeepList?page=1";
	}

	/****************************************************************************
	 * @功能：设备维修管理
	 * @作者：李鹏翔
	 ****************************************************************************/
	@RequestMapping("/device/deviceRepairList")
	public ModelAndView deviceRepairList(@ModelAttribute LabRoomDeviceRepair lrdr, @RequestParam Integer page, @ModelAttribute("selected_labCenter") Integer cid) {
		ModelAndView mav = new ModelAndView();
		// 学期
		List<SchoolTerm> terms = shareService.findAllSchoolTerms();
		mav.addObject("terms", terms);
		mav.addObject("reservation", lrdr);
		// 查询出所有的设备设备预约记录
		int totalRecords = labRoomDeviceService.getAllRepairTotals(lrdr);
		int pageSize = 10;// 每页10条记录
		// 分页信息
		Map<String, Integer> pageModel = shareService.getPage(page, pageSize, totalRecords);
		// 根据分页信息查询出来的记录
		List<LabRoomDeviceRepair> deviceLendList = labRoomDeviceService.findAllRepairs(lrdr, page, pageSize);
		mav.addObject("deviceRepairList", deviceLendList);

		mav.addObject("pageModel", pageModel);
		mav.addObject("totalRecords", totalRecords);
		mav.addObject("page", page);
		mav.addObject("pageSize", pageSize);

		// 当前登录人
		User user = shareService.getUser();
		mav.addObject("user", user);
		// 获得当前设备所在的实验中心和实验室
		LabRoom lr = new LabRoom();
		if (lrdr.getId() != null) {
			lr = lrdr.getLabRoomDevice().getLabRoom();
		}
		LabCenter lc = labCenterDAO.findLabCenterByPrimaryKey(cid);
		mav.addObject("cid", cid);
		// 判断当前登录人是够有添加维修记录的权限
		List<User> us = labRoomDeviceService.findAdminByLrid(lr.getId());
		User us1 = lc.getUserByCenterManager();
		if (us1.getUsername().equals(user.getUsername())) {
			mav.addObject("admin", true);
		}
		if (us.size() > 0) {
			for (User u : us) {
				if (u.getUsername().equals(user.getUsername())) {
					mav.addObject("admin", true);
				}
			}
		}
		mav.setViewName("/device/lab_room_device/deviceRepairList.jsp");
		return mav;
	}

	/****************************************************************************
	 * @功能：保存设备报修
	 * @作者：李鹏翔
	 ****************************************************************************/
	@RequestMapping("/device/saveNewDeviceRepair")
	public String saveNewDeviceRepair(@ModelAttribute LabRoomDeviceRepair lrdr) {
		labRoomDeviceService.saveNewDeviceRepair(lrdr);
		return "redirect:/device/deviceRepairList?page=1";
	}

	/****************************************************************************
	 * @功能：报修状态设备列表
	 * @作者：李鹏翔
	 ****************************************************************************/
	@RequestMapping("/device/applyDeviceRepairList")
	public ModelAndView applyDeviceRepairList(@ModelAttribute LabRoomDeviceRepair lrdr, @RequestParam Integer page, @ModelAttribute("selected_labCenter") Integer cid) {
		ModelAndView mav = new ModelAndView();
		// 学期
		List<SchoolTerm> terms = shareService.findAllSchoolTerms();
		mav.addObject("terms", terms);
		mav.addObject("reservation", lrdr);
		// 查询出所有的设备设备预约记录
		int totalRecords = labRoomDeviceService.getApplyRepairTotals(lrdr);
		int pageSize = 10;// 每页10条记录
		// 分页信息
		Map<String, Integer> pageModel = shareService.getPage(page, pageSize, totalRecords);
		// 根据分页信息查询出来的记录
		List<LabRoomDeviceRepair> deviceLendList = labRoomDeviceService.findApplyRepairs(lrdr, page, pageSize);
		mav.addObject("deviceRepairList", deviceLendList);

		mav.addObject("pageModel", pageModel);
		mav.addObject("totalRecords", totalRecords);
		mav.addObject("page", page);
		mav.addObject("pageSize", pageSize);

		// 当前登录人
		User user = shareService.getUser();
		mav.addObject("user", user);
		// 获得当前设备所在的实验中心和实验室
		LabRoom lr = new LabRoom();
		if (lrdr.getId() != null) {
			lr = lrdr.getLabRoomDevice().getLabRoom();
		}
		LabCenter lc = labCenterDAO.findLabCenterByPrimaryKey(cid);
		mav.addObject("cid", cid);
		// 判断当前登录人是够有添加维修记录的权限
		List<User> us = labRoomDeviceService.findAdminByLrid(lr.getId());
		User us1 = lc.getUserByCenterManager();
		if (us1.getUsername().equals(user.getUsername())) {
			mav.addObject("admin", true);
		}
		if (us.size() > 0) {
			for (User u : us) {
				if (u.getUsername().equals(user.getUsername())) {
					mav.addObject("admin", true);
				}
			}
		}
		mav.setViewName("/device/lab_room_device/applyDeviceRepairList.jsp");
		return mav;
	}

	/****************************************************************************
	 * @功能：报修状态设备列表
	 * @作者：李鹏翔
	 ****************************************************************************/
	@RequestMapping("/device/passDeviceRepairList")
	public ModelAndView passDeviceRepairList(@ModelAttribute LabRoomDeviceRepair lrdr, @RequestParam Integer page) {
		ModelAndView mav = new ModelAndView();
		// 学期
		List<SchoolTerm> terms = shareService.findAllSchoolTerms();
		mav.addObject("terms", terms);
		mav.addObject("reservation", lrdr);
		// 查询出所有的设备设备预约记录
		int totalRecords = labRoomDeviceService.getPassRepairTotals(lrdr);
		int pageSize = 10;// 每页10条记录
		// 分页信息
		Map<String, Integer> pageModel = shareService.getPage(page, pageSize, totalRecords);
		// 根据分页信息查询出来的记录
		List<LabRoomDeviceRepair> deviceLendList = labRoomDeviceService.findPassRepairs(lrdr, page, pageSize);
		mav.addObject("deviceRepairList", deviceLendList);
		mav.addObject("pageModel", pageModel);
		mav.addObject("totalRecords", totalRecords);
		mav.addObject("page", page);
		mav.addObject("pageSize", pageSize);

		// 当前登录人
		User user = shareService.getUser();
		mav.addObject("user", user);
		mav.setViewName("/device/lab_room_device/passDeviceRepairList.jsp");
		return mav;
	}

	/****************************************************************************
	 * @功能：报修状态设备列表
	 * @作者：李鹏翔
	 ****************************************************************************/
	@RequestMapping("/device/rejectedDeviceRepairList")
	public ModelAndView rejectedDeviceRepairList(@ModelAttribute LabRoomDeviceRepair lrdr, @RequestParam Integer page) {
		ModelAndView mav = new ModelAndView();
		// 学期
		List<SchoolTerm> terms = shareService.findAllSchoolTerms();
		mav.addObject("terms", terms);
		mav.addObject("reservation", lrdr);
		// 查询出所有的设备设备预约记录
		int totalRecords = labRoomDeviceService.getRejectedRepairTotals(lrdr);
		int pageSize = 10;// 每页10条记录
		// 分页信息
		Map<String, Integer> pageModel = shareService.getPage(page, pageSize, totalRecords);
		// 根据分页信息查询出来的记录
		List<LabRoomDeviceRepair> deviceLendList = labRoomDeviceService.findRejectedRepairs(lrdr, page, pageSize);
		mav.addObject("deviceRepairList", deviceLendList);

		mav.addObject("pageModel", pageModel);
		mav.addObject("totalRecords", totalRecords);
		mav.addObject("page", page);
		mav.addObject("pageSize", pageSize);

		// 当前登录人
		User user = shareService.getUser();
		mav.addObject("user", user);
		mav.setViewName("/device/lab_room_device/rejectedDeviceRepairList.jsp");
		return mav;
	}

	/****************************************************************************
	 * @功能：设备维修
	 * @作者：李鹏翔
	 ****************************************************************************/
	@RequestMapping("/device/repairDevice")
	public ModelAndView repairDevice(@RequestParam Integer idKey) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("lrdr", labRoomDeviceRepairDAO.findLabRoomDeviceRepairByPrimaryKey(idKey));
		//mav.addObject("status", cLabRoomDeviceRepairStatusDAO.findAllCLabRoomDeviceRepairStatuss());
		mav.addObject("status", shareService.getCDictionaryData("c_lab_room_device_repair_status"));
		
		mav.setViewName("/device/lab_room_device/repairDevice.jsp");
		return mav;
	}

	/****************************************************************************
	 * @功能：保存设备维修记录
	 * @作者：李鹏翔
	 ****************************************************************************/
	@RequestMapping("/device/saveNewRepair")
	public String saveNewRepair(@ModelAttribute LabRoomDeviceRepair lrdr) {
		int id = lrdr.getId();
		LabRoomDeviceRepair repair = labRoomDeviceRepairDAO.findLabRoomDeviceRepairByPrimaryKey(id);
		repair.setCDictionaryByStatusId(lrdr.getCDictionaryByStatusId());
		repair.setRepairRecords(lrdr.getRepairRecords());
		repair.setRestoreTime(Calendar.getInstance());
		LabRoomDevice lrd = repair.getLabRoomDevice();
		if ("3".equals(lrdr.getCDictionaryByStatusId().getCNumber())) {
			/*CDeviceStatus cds = new CDeviceStatus();
			cds.setId(1);*/
			CDictionary cds = shareService.getCDictionaryByCategory("c_lab_room_device_status", "1");
			lrd.setCDictionaryByDeviceStatus(cds);
		} else {
			/*CDeviceStatus cds = new CDeviceStatus();
			cds.setId(4);
			lrd.setCDeviceStatus(cds);*/
			CDictionary cds = shareService.getCDictionaryByCategory("c_lab_room_device_status", "4");
			lrd.setCDictionaryByDeviceStatus(cds);
			
		}
		labRoomDeviceDAO.store(lrd);
		labRoomDeviceService.saveNewDeviceRepair(repair);
		return "redirect:/device/applyDeviceRepairList?page=1";
	}

	/****************************************************************************
	 * @功能：实验室设备管理---设备管理---查看
	 * @作者：李小龙
	 ****************************************************************************/
	@RequestMapping("/device/viewDeviceSetting")
	public ModelAndView viewDeviceSetting(@RequestParam Integer id) {
		// 新建ModelAndView对象；
		ModelAndView mav = new ModelAndView();
		// id对应的实验分室设备
		LabRoomDevice device = labRoomDeviceService.findLabRoomDeviceByPrimaryKey(id);
		mav.addObject("device", device);
		mav.setViewName("/device/lab_room_device_access/viewDeviceSetting.jsp");
		return mav;
	}

	/****************************************************************************
	 * @功能：确认维修
	 * @作者：李鹏翔
	 ****************************************************************************/
	@RequestMapping("/device/sureDeviceRepair")
	public String sureDeviceRepair(@RequestParam Integer idKey) {
		LabRoomDeviceRepair ldr = labRoomDeviceRepairDAO.findLabRoomDeviceRepairByPrimaryKey(idKey);
		/*CLabRoomDeviceRepairStatus status = new CLabRoomDeviceRepairStatus();
		status.setId(3);*/
		CDictionary status = shareService.getCDictionaryByCategory("c_lab_room_device_repair_status", "3");
//		ldr.setCLabRoomDeviceRepairStatus(status);
		ldr.setCDictionaryByStatusId(status);
		labRoomDeviceRepairDAO.store(ldr);
		return "redirect:/passDeviceRepairList?page=1";
	}

	/****************************************************************************
	 * @功能：实验室设备管理---设备管理---设置
	 * @作者：李小龙
	 ****************************************************************************/
	@RequestMapping("/device/editDeviceSetting")
	public ModelAndView editDeviceSetting(@RequestParam Integer id) {
		// 新建ModelAndView对象；
		ModelAndView mav = new ModelAndView();
		// id对应的实验分室设备
		LabRoomDevice device = labRoomDeviceService.findLabRoomDeviceByPrimaryKey(id);
		mav.addObject("device", device);

		// 获取所有单选的结果集（是/否）
		/*Set<CActive> CActives = labRoomService.findAllCActive();*/
		List<CDictionary> CActives = shareService.getCDictionaryData("c_active");
		mav.addObject("CActives", CActives);
		// 预约形式
		/*Set<CAppointmentType> CAppointmentTypes = cAppointmentTypeDAO.findAllCAppointmentTypes();*/
		List<CDictionary> CAppointmentTypes = shareService.getCDictionaryData("c_appointment_type");
		mav.addObject("CAppointmentTypes", CAppointmentTypes);
		/*Set<CLabAccessType> CLabAccessTypes = cLabAccessTypeDAO.findAllCLabAccessTypes();*/
		List<CDictionary> CLabAccessTypes = shareService.getCDictionaryData("c_lab_access_type");
		mav.addObject("CLabAccessTypes", CLabAccessTypes);
		mav.setViewName("/device/lab_room_device_access/editDeviceSetting.jsp");
		return mav;
	}

	/****************************************************************************
	 * @功能：实验室设备管理---设备管理---保存设置
	 * @作者：李小龙
	 ****************************************************************************/
	@SuppressWarnings("unused")
	@RequestMapping("/device/saveDeviceSetting")
	public ModelAndView saveDeviceSetting(@ModelAttribute LabRoomDevice device) {
		// 新建ModelAndView对象；
		ModelAndView mav = new ModelAndView();
		LabRoomDevice labDevice = labRoomDeviceService.findLabRoomDeviceByPrimaryKey(device.getId());
		if (device.getCDictionaryByAllowLending() != null) {
			labDevice.setCDictionaryByAllowLending(device.getCDictionaryByAllowLending());
		}
		if (device.getCDictionaryByAllowAppointment() != null) {
			labDevice.setCDictionaryByAllowAppointment(device.getCDictionaryByAllowAppointment());
		}
		if (device.getCDictionaryByAppointmentType() != null) {
			labDevice.setCDictionaryByAppointmentType(device.getCDictionaryByAppointmentType());
		}
		if (device.getCDictionaryByIsAudit() != null) {
			labDevice.setCDictionaryByIsAudit(device.getCDictionaryByIsAudit());
		}
		if (device.getCDictionaryByTeacherAudit() != null) {
			labDevice.setCDictionaryByTeacherAudit(device.getCDictionaryByTeacherAudit());
		}
		if (device.getCDictionaryByManagerAudit() != null) {
			labDevice.setCDictionaryByManagerAudit(device.getCDictionaryByManagerAudit());
		}
		if (device.getCActiveByLabManagerAudit() != null) {
			labDevice.setCActiveByLabManagerAudit(device.getCActiveByLabManagerAudit());
		}
		if (device.getCDictionaryByAllowSecurityAccess() != null) {
			labDevice.setCDictionaryByAllowSecurityAccess(device.getCDictionaryByAllowSecurityAccess());
		}
		if (device.getCDictionaryBySecurityAccessType() != null) {
			labDevice.setCDictionaryBySecurityAccessType(device.getCDictionaryBySecurityAccessType());
		}
		LabRoomDevice d = labRoomDeviceService.save(labDevice);
		mav.setViewName("redirect:/device/listLabRoomDevice?page=1");
		return mav;
	}

	/****************************************************************************
	 * @功能：实验室设备管理---设备管理---编辑（化工学院） @作者：李小龙
	 ****************************************************************************/
	@RequestMapping("/device/editDeviceInfo")
	public ModelAndView editDeviceInfo(@RequestParam Integer id) {
		// 新建ModelAndView对象；
		ModelAndView mav = new ModelAndView();
		// id对应的实验分室设备
		LabRoomDevice device = labRoomDeviceService.findLabRoomDeviceByPrimaryKey(id);
		mav.addObject("device", device);
		// 设备管理员
		User user = shareService.getUser();
		List<User> users = shareService.findTheSameCollegeTeacher(user.getSchoolAcademy().getAcademyNumber());
		mav.addObject("users", users);
		// 设备状态
		/*Set<CDeviceStatus> stutus = cDeviceStatusDAO.findAllCDeviceStatuss();*/
		List<CDictionary> stutus = shareService.getCDictionaryData("c_lab_room_device_status");
		mav.addObject("stutus", stutus);
		// 所属类型
		/*Set<CDeviceType> types = cDeviceTypeDAO.findAllCDeviceTypes();*/
		List<CDictionary> types = shareService.getCDictionaryData("c_lab_room_device_type");
		mav.addObject("types", types);
		// 收费标准
		/*Set<CDeviceCharge> charges = cDeviceChargeDAO.findAllCDeviceCharges();*/
		List<CDictionary> charges = shareService.getCDictionaryData("c_lab_room_device_charge");
		mav.addObject("charges", charges);

		// 当前@时间所属的学期
		Calendar time = Calendar.getInstance();
		SchoolTerm term = shareService.getBelongsSchoolTerm(time);
		// 根据学期和设备查询培训
		List<LabRoomDeviceTraining> trainings = labRoomDeviceService.findLabRoomDeviceTrainingByTermId(term.getId(), id);
		mav.addObject("trainings", trainings);
		// 培训表单的对象
		mav.addObject("train", new LabRoomDeviceTraining());
		// 培训教师

		mav.setViewName("/device/specialAcademy/editDeviceInfo.jsp");

		return mav;
	}

	/****************************************************************************
	 * @功能：实验室设备管理---设备管理---查看（化工学院） @作者：贺子龙
	 ****************************************************************************/
	@RequestMapping("/device/viewDeviceInfo")
	public ModelAndView viewDeviceInfo(@RequestParam Integer id) {
		// 新建ModelAndView对象；
		ModelAndView mav = new ModelAndView();
		// id对应的实验分室设备
		LabRoomDevice device = labRoomDeviceService.findLabRoomDeviceByPrimaryKey(id);
		mav.addObject("device", device);
		mav.setViewName("/device/specialAcademy/viewDeviceInfo.jsp");

		return mav;
	}

	/****************************************************************************
	 * @功能：设备图片（化工学院） @作者：李小龙
	 ****************************************************************************/
	@RequestMapping("/device/deviceImage")
	public ModelAndView deviceImage(@RequestParam Integer deviceId) {
		// 新建ModelAndView对象；
		ModelAndView mav = new ModelAndView();
		LabRoomDevice device = labRoomDeviceService.findLabRoomDeviceByPrimaryKey(deviceId);
		mav.addObject("device", device);
		mav.setViewName("/device/specialAcademy/deviceImage.jsp");
		return mav;
	}

	/****************************************************************************
	 * @功能：设备视频（化工学院） @作者：李小龙
	 ****************************************************************************/
	@RequestMapping("/device/deviceVideo")
	public ModelAndView deviceVideo(@RequestParam Integer deviceId) {
		// 新建ModelAndView对象；
		ModelAndView mav = new ModelAndView();
		LabRoomDevice device = labRoomDeviceService.findLabRoomDeviceByPrimaryKey(deviceId);
		mav.addObject("device", device);
		mav.setViewName("/device/specialAcademy/deviceVideo.jsp");
		return mav;
	}

	/****************************************************************************
	 * @功能：设备图片（化工学院）--查看
	 * @作者：贺子龙
	 ****************************************************************************/
	@RequestMapping("/device/viewDeviceImage")
	public ModelAndView viewDeviceImage(@RequestParam Integer deviceId) {
		// 新建ModelAndView对象；
		ModelAndView mav = new ModelAndView();
		LabRoomDevice device = labRoomDeviceService.findLabRoomDeviceByPrimaryKey(deviceId);
		mav.addObject("device", device);
		mav.setViewName("/device/specialAcademy/viewDeviceImage.jsp");
		return mav;
	}

	/****************************************************************************
	 * @功能：设备视频（化工学院）--查看
	 * @作者：贺子龙
	 ****************************************************************************/
	@RequestMapping("/device/viewDeviceVideo")
	public ModelAndView viewDeviceVideo(@RequestParam Integer deviceId) {
		// 新建ModelAndView对象；
		ModelAndView mav = new ModelAndView();
		LabRoomDevice device = labRoomDeviceService.findLabRoomDeviceByPrimaryKey(deviceId);
		mav.addObject("device", device);
		mav.setViewName("/device/specialAcademy/viewDeviceVideo.jsp");
		return mav;
	}

	/****************************************************************************
	 * @功能：设备二维码（化工学院）--查看
	 * @作者：李小龙
	 ****************************************************************************/
	@RequestMapping("/device/viewDimensionalCode")
	public ModelAndView viewDimensionalCode(@RequestParam Integer deviceId) {
		// 新建ModelAndView对象；
		ModelAndView mav = new ModelAndView();
		LabRoomDevice device = labRoomDeviceService.findLabRoomDeviceByPrimaryKey(deviceId);
		mav.addObject("device", device);
		mav.setViewName("/device/specialAcademy/viewDimensionalCode.jsp");
		return mav;
	}

	/****************************************************************************
	 * @功能：设备视频（化工学院） @作者：李小龙
	 ****************************************************************************/
	@RequestMapping("/device/dimensionalCode")
	public ModelAndView dimensionalCode(@RequestParam Integer deviceId) {
		// 新建ModelAndView对象；
		ModelAndView mav = new ModelAndView();
		LabRoomDevice device = labRoomDeviceService.findLabRoomDeviceByPrimaryKey(deviceId);
		mav.addObject("device", device);
		mav.setViewName("/device/specialAcademy/dimensionalCode.jsp");
		return mav;
	}

	/****************************************************************************
	 * @功能：根据设备id查询培训
	 * @作者：李小龙
	 ****************************************************************************/
	@RequestMapping("/device/deviceTraining")
	public ModelAndView deviceTraining(@ModelAttribute LabRoomDeviceTraining train, @RequestParam Integer deviceId) {
		// 新建ModelAndView对象；
		ModelAndView mav = new ModelAndView();
		// 培训查询表单的对象
		mav.addObject("train", train);
		// 设备id对应的设备
		LabRoomDevice device = labRoomDeviceService.findLabRoomDeviceByPrimaryKey(deviceId);
		mav.addObject("device", device);
		// 学期
		List<SchoolTerm> terms = shareService.findAllSchoolTerms();
		mav.addObject("terms", terms);
		// 当前登录人
		User user = shareService.getUser();
		mav.addObject("user", user);
		// 根据设备id和培训对象查询培训
		List<LabRoomDeviceTraining> trainList = labRoomDeviceService.findLabRoomDeviceTrainingByDeviceId(train, deviceId);

		mav.addObject("trainList", trainList);
		// 当前登录人是否参加过培训
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		// 第一个培训的培训名单
		if (trainList.size() > 0) {
			for (LabRoomDeviceTraining t : trainList) {
				int i = t.getId();
				List<LabRoomDeviceTrainingPeople> peoples = labRoomDeviceService.findTrainingPeoplesByTrainingId(i);
				mav.addObject("peoples", peoples);
				int flag = 1;// 默认为1：未参加，0为已参加
				for (LabRoomDeviceTrainingPeople p : peoples) {
					if (p.getUser().getUsername().equals(user.getUsername())) {
						flag = 0;
						break;
					} else {
						flag = 1;
					}
				}
				map.put(i, flag);
			}

		}
		mav.addObject("map", map);
		// 添加培训表单的培训教师
		List<User> users = shareService.findTheSameCollegeTeacher(user.getSchoolAcademy().getAcademyNumber());
		mav.addObject("users", users);

		mav.setViewName("/device/specialAcademy/deviceTraining.jsp");
		return mav;
	}

	/****************************************************************************
	 * @功能：查看培训计划
	 * @作者：贺子龙
	 ****************************************************************************/
	@RequestMapping("/device/viewDeviceTraining")
	public ModelAndView viewDeviceTraining(@ModelAttribute LabRoomDeviceTraining train, @RequestParam Integer deviceId) {
		// 新建ModelAndView对象；
		ModelAndView mav = new ModelAndView();
		// 培训查询表单的对象
		mav.addObject("train", train);
		// 设备id对应的设备
		LabRoomDevice device = labRoomDeviceService.findLabRoomDeviceByPrimaryKey(deviceId);
		mav.addObject("device", device);
		// 当前登录人
		User user = shareService.getUser();
		mav.addObject("user", user);
		// 根据设备id和培训对象查询培训
		List<LabRoomDeviceTraining> trainList = labRoomDeviceService.findLabRoomDeviceTrainingByDeviceId(train, deviceId);
		// 学期
		List<SchoolTerm> terms = shareService.findAllSchoolTerms();
		mav.addObject("terms", terms);
		mav.addObject("trainList", trainList);
		// 当前登录人是否参加过培训
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		// 第一个培训的培训名单
		if (trainList.size() > 0) {
			for (LabRoomDeviceTraining t : trainList) {
				int i = t.getId();
				List<LabRoomDeviceTrainingPeople> peoples = labRoomDeviceService.findTrainingPeoplesByTrainingId(i);
				mav.addObject("peoples", peoples);
				int flag = 1;// 默认为1：未参加，0为已参加
				for (LabRoomDeviceTrainingPeople p : peoples) {
					if (p.getUser().getUsername().equals(user.getUsername())) {
						flag = 0;
						break;
					} else {
						flag = 1;
					}
				}
				map.put(i, flag);
			}

		}
		mav.addObject("map", map);

		mav.setViewName("/device/specialAcademy/viewDeviceTraining.jsp");
		return mav;
	}

	/****************************************************************************
	 * @功能：培训预约管理主页面
	 * @作者：贺子龙
	 ****************************************************************************/
	@RequestMapping("/device/listLabRoomDeviceTraining")
	public ModelAndView listLabRoomDeviceTraining(@ModelAttribute LabRoomDeviceTraining labRoomDeviceTraining, @RequestParam Integer currpage, int isTeacher) {
		// 新建ModelAndView对象；
		ModelAndView mav = new ModelAndView();
		int pageSize = 20;
		// 培训预约
		List<LabRoomDeviceTraining> labRoomDeviceTrainings = labRoomDeviceService.findLabRoomDeviceTrainingByUser(null, null, currpage, pageSize);
		// 总记录数
		int totalRecords = labRoomDeviceService.findLabRoomDeviceTrainingByUser(null, null, 1, -1).size();
		mav.addObject("labRoomDeviceTrainings", labRoomDeviceTrainings);
		mav.addObject("pageModel", shareService.getPage(currpage, pageSize, totalRecords));
		mav.addObject("currpage", currpage);
		mav.addObject("pageSize", pageSize);
		mav.addObject("totalRecords", totalRecords);
		mav.addObject("isTeacher", isTeacher);
		mav.addObject("user", shareService.getUser());
		
		mav.setViewName("/device/lab_room_device_training/listLabRoomDeviceTraining.jsp");
		return mav;
	}

	/****************************************************************************
	 * @功能：取消培训预约
	 * @作者：贺子龙
	 ****************************************************************************/
	@RequestMapping("/device/cancleTraining")
	public ModelAndView cancleTraining(@RequestParam int id, int isTeacher, int flag) {
		// 新建ModelAndView对象；
		ModelAndView mav = new ModelAndView();
		// id对应的培训
		LabRoomDeviceTraining train = labRoomDeviceTrainingDAO.findLabRoomDeviceTrainingByPrimaryKey(id);
		// 取消培训预约
		labRoomDeviceService.cancleTraining(id);
		Set<LabRoomDeviceTrainingPeople> peoples = train.getLabRoomDeviceTrainingPeoples();
		train.setJoinNumber(peoples.size());
		labRoomDeviceTrainingDAO.store(train);
		String viewName = "";
		if (flag == 0) {
			viewName = "redirect:/personal/message/mySelfInfo";
		} else {
			viewName = "redirect:/device/listLabRoomDeviceTraining?currpage=1&isTeacher=" + isTeacher;
		}
		mav.setViewName(viewName);
		return mav;
	}

	/****************************************************************************
	 * @功能：根据设备id查询培训
	 * @作者：李小龙
	 ****************************************************************************/
	@RequestMapping("/device/findTrainingPeopleByTrainId")
	public ModelAndView findTrainingPeopleByTrainId(@RequestParam Integer id) {
		// 新建ModelAndView对象；
		ModelAndView mav = new ModelAndView();
		// id对应的培训
		LabRoomDeviceTraining train = labRoomDeviceTrainingDAO.findLabRoomDeviceTrainingByPrimaryKey(id);
		mav.addObject("train", train);
		// 培训所属的设备
		mav.addObject("device", train.getLabRoomDevice());
		List<LabRoomDeviceTrainingPeople> peoples = labRoomDeviceService.findTrainingPeoplesByTrainingId(id);
		mav.addObject("peoples", peoples);
		// 培训结果
		//Set<CTrainingResult> results = cTrainingResultDAO.findAllCTrainingResults();
		List<CDictionary> CActives = shareService.getCDictionaryData("c_training_result");
		mav.addObject("results", CActives);
		mav.setViewName("/device/lab_room_device_training/peopleList.jsp");
		return mav;
	}

	/****************************************************************************
	 * @功能：批量生成二维码
	 * @作者：李小龙
	 * @throws Exception
	 ****************************************************************************/
	@RequestMapping("/device/generateDimensionalCode")
	public @ResponseBody String generateDimensionalCode(HttpServletRequest request) {

		// System.out.println(request.getRequestURL());//http://localhost/xidlims/device/generateDimensionalCode
		// System.out.println(request.getContextPath());///xidlims
		// System.out.println(request.getServerName());//localhost
		String serverName = request.getServerName()+":"+request.getServerPort();
		String data = "success";
		Set<LabRoomDevice> deviceList = labRoomDeviceDAO.findAllLabRoomDevices();
		for (LabRoomDevice d : deviceList) {
			String url = "";
			try {
				url = shareService.getDimensionalCode(d, serverName);
			} catch (Exception e) {
				data = "error";
				break;
			}
			d.setDimensionalCode(url);
			labRoomDeviceDAO.store(d);
		}
		return data;
	}

	/****************************************************************************
	 * @功能：设备文档（化工学院） @作者：李小龙
	 ****************************************************************************/
	@RequestMapping("/device/deviceDocument")
	public ModelAndView deviceDocument(@RequestParam Integer deviceId) {
		// 新建ModelAndView对象；
		ModelAndView mav = new ModelAndView();
		LabRoomDevice device = labRoomDeviceService.findLabRoomDeviceByPrimaryKey(deviceId);
		mav.addObject("device", device);
		mav.setViewName("/device/specialAcademy/deviceDocument.jsp");
		return mav;
	}

	/****************************************************************************
	 * @功能：设备文档（化工学院）--查看
	 * @作者：李小龙
	 ****************************************************************************/
	@RequestMapping("/device/viewDeviceDocument")
	public ModelAndView viewDeviceDocument(@RequestParam Integer deviceId) {
		// 新建ModelAndView对象；
		ModelAndView mav = new ModelAndView();
		LabRoomDevice device = labRoomDeviceService.findLabRoomDeviceByPrimaryKey(deviceId);
		mav.addObject("device", device);
		mav.setViewName("/device/specialAcademy/viewDeviceDocument.jsp");
		return mav;
	}

	/****************************************************************************
	 * @功能：给设备上传文档
	 * @作者：李小龙
	 ****************************************************************************/
	@RequestMapping("/device/deviceDocumentUpload")
	public @ResponseBody String deviceDocumentUpload(HttpServletRequest request, HttpServletResponse response, BindException errors, Integer id) throws Exception {
		labRoomDeviceService.deviceDocumentUpload(request, response, id, 2);
		return "ok";
	}

	/****************************************************************************
	 * @功能：下载设备文档
	 * @作者：李小龙
	 ****************************************************************************/
	@RequestMapping("/device/downloadDocument")
	public void downloadDocument(HttpServletRequest request, HttpServletResponse response, int id) {
		// id对应的文档
		CommonDocument doc = commonDocumentService.findCommonDocumentByPrimaryKey(id);
		labRoomDeviceService.downloadFile(doc, request, response);
	}

	/**
	 * 
	 * @comment：设备管理员批量设置首页
	 * @param labRoomDevice、page、cid
	 * @return：
	 * @author：叶明盾 @date：2015-10-29 上午11:00:33
	 */
	@SuppressWarnings("unused")
	@RequestMapping("/device/batchDeviceAdmin")
	public ModelAndView batchDeviceAdmin(@ModelAttribute LabRoomDevice labRoomDevice, @RequestParam Integer page, @ModelAttribute("selected_labCenter") Integer cid) {
		// 新建ModelAndView对象；
		ModelAndView mav = new ModelAndView();
		// 中心所属学院
		String academy="";
		if(cid != null & cid != -1)
		{
			// 中心所属学院
			academy = labCenterDAO.findLabCenterByPrimaryKey(cid).getSchoolAcademy().getAcademyNumber();
		}
		else{
			academy=shareService.getUser().getSchoolAcademy().getAcademyNumber();
		}
		// 查询表单的对象
		mav.addObject("labRoomDevice", labRoomDevice);
		// 实验室
		List<LabRoom> rooms = labRoomService.findLabRoomByLabCenterid(cid,1);
		mav.addObject("rooms", rooms);
		// 设备管理员
		List<User> users = shareService.findUsersByAuthorityId(10);
		mav.addObject("users", users);
		int pageSize = 30;// 每页20条记录
		// 查询出来的总记录条数
		int totalRecords = labRoomDeviceService.findAllLabRoomDevice(labRoomDevice, cid,1);
		// 分页信息
		Map<String, Integer> pageModel = shareService.getPage(page, pageSize, totalRecords);
		// 根据分页信息查询出来的记录
		List<LabRoomDevice> listLabRoomDevice = labRoomDeviceService.findAllLabRoomDeviceNew(labRoomDevice, cid, page, pageSize,1);
		mav.addObject("listLabRoomDevice", listLabRoomDevice);
		// 查询所有设备记录
		List<LabRoomDevice> listLabRoomDeviceAll = labRoomDeviceService.findAllLabRoomDevice(labRoomDevice, cid, 1, -1,1);
		mav.addObject("listLabRoomDeviceAll", listLabRoomDeviceAll);
		// 实验室管理员
		mav.addObject("admin", new LabRoomAdmin());
		// 实验中心id
		mav.addObject("cid", cid);
		// 分页相关参数
		mav.addObject("pageModel", pageModel);
		mav.addObject("totalRecords", totalRecords);
		mav.addObject("page", page);
		mav.addObject("pageSize", pageSize);
		mav.setViewName("/device/batchDeviceAdmin.jsp");
		return mav;
	}

	/**
	 * 
	 * @comment：AJAX 根据姓名、工号查询当前登录人所在学院的用户
	 * @param cname
	 * @param username
	 * @param cid
	 * @param page
	 * @return：
	 * @author：叶明盾 @date：2015-10-28 下午10:23:45
	 */
	@RequestMapping("/device/findUserForDeviceAdmin")
	public @ResponseBody String findUserForDeviceAdmin(@RequestParam String cname, String username, Integer cid, Integer page) {

		User u = shareService.getUser();
		String academyNumber = "";
		if (u.getSchoolAcademy() != null) {
			academyNumber = u.getSchoolAcademy().getAcademyNumber();
		}
		User user = new User();
		user.setCname(cname);
		user.setUsername(username);

		// 分页开始
		int totalRecords = labRoomService.findUserByUserAndAcademy(user, cid, academyNumber);
		int pageSize = 20;
		Map<String, Integer> pageModel = shareService.getPage(page, pageSize, totalRecords);
		// 根据分页信息查询出来的记录
		List<User> userList = labRoomService.findUserByUserAndAcademy(user, cid, academyNumber, page, pageSize);
		String s = "";
		for (User d : userList) {
			String academy = "";
			if (d.getSchoolAcademy() != null) {
				academy = d.getSchoolAcademy().getAcademyName();
			}
			s += "<tr>" + "<td><input type='radio' name='CK_name' value='" + d.getUsername() + "'/></td>" + "<td>" + d.getCname() + "</td>" + "<td>" + d.getUsername() + "</td>" + "<td>" + academy + "</td>" + "</tr>";
		}
		s += "<tr><td colspan='6'>" + "<a href='javascript:void(0)' onclick='firstPage(1);'>" + "首页" + "</a>&nbsp;" + "<a href='javascript:void(0)' onclick='previousPage(" + page + ");'>" + "上一页" + "</a>&nbsp;" + "<a href='javascript:void(0)' onclick='nextPage(" + page + "," + pageModel.get("totalPage") + ");'>" + "下一页" + "</a>&nbsp;" + "<a href='javascript:void(0)' onclick='lastPage(" + pageModel.get("totalPage") + ");'>" + "末页" + "</a>&nbsp;" + "当前第" + page + "页&nbsp; 共" + pageModel.get("totalPage") + "页  " + totalRecords + "条记录" + "</td></tr>";
		return htmlEncode(s);
	}

	/**
	 * 
	 * @comment：批量保存实验室管理员
	 * @param roomId、array、typeId
	 * @return：
	 * @author：叶明盾 @date：2015-10-28 下午10:17:00
	 */
	@RequestMapping("/device/saveBatchDeviceAdmin")
	public ModelAndView saveBatchDeviceAdmin(@RequestParam String[] deviceArray, String[] array, Integer typeId) {
		ModelAndView mav = new ModelAndView();

		for (String a : deviceArray) {
			// devceId对应的设备
			LabRoomDevice device = labRoomDeviceService.findLabRoomDeviceByPrimaryKey(Integer.valueOf(a));

			for (String i : array) {
				// username对应的用户
				User u = userDAO.findUserByPrimaryKey(i);
				device.setUser(u);
				labRoomDeviceDAO.store(device);
				// LabRoomAdmin admin=new LabRoomAdmin();
				// admin.setLabRoom(room);
				// admin.setUser(u);
				// admin.setTypeId(typeId);
				// labRoomDeviceDAO.store(admin);
				// 给用户赋予权限
				// Set<Authority> ahths=u.getAuthorities();
				// Authority
				// at=authorityDAO.findAuthorityByPrimaryKey(10);//设备管理员
				// ahths.add(at);
				// u.setAuthorities(ahths);
				// userDAO.store(u);
			}
		}
		mav.setViewName("redirect:/device/batchDeviceAdmin?page=1");
		return mav;
	}

	/****************************************************************************
	 * 功能：AJAX 根据姓名、工号查询当前登录人所在学院的用户 作者：李小龙
	 ****************************************************************************/
	@RequestMapping("/device/findStudentByCnameAndUsername")
	public @ResponseBody String findStudentByCnameAndUsername(@RequestParam String cname, String username, Integer page, Integer deviceId) {

		User u = shareService.getUser();
		String academyNumber = "";
		if (u.getSchoolAcademy() != null) {
			academyNumber = u.getSchoolAcademy().getAcademyNumber();
		}
		User user = new User();
		user.setCname(cname);
		user.setUsername(username);

		// 分页开始
		int totalRecords = labRoomDeviceService.findStudentByCnameAndUsername(user, academyNumber, deviceId);
		int pageSize = 20;
		Map<String, Integer> pageModel = shareService.getPage(page, pageSize, totalRecords);
		// 根据分页信息查询出来的记录
		List<User> userList = labRoomDeviceService.findStudentByCnameAndUsername(user, academyNumber, deviceId, page, pageSize);
		String s = "";
		for (User d : userList) {
			String academy = "";
			if (d.getSchoolAcademy() != null) {
				academy = d.getSchoolAcademy().getAcademyName();
			}
			s += "<tr>" + "<td><input type='checkbox' name='CK_name' value='" + d.getUsername() + "'/></td>" + "<td>" + d.getCname() + "</td>" + "<td>" + d.getUsername() + "</td>" + "<td>" + academy + "</td>" +

					"</tr>";
		}

		int previousPage;
		int nextPage;
		if (page == 1) {
			previousPage = page;
		} else {
			previousPage = page - 1;
		}

		if (page == (totalRecords + pageSize - 1) / pageSize) {
			nextPage = page;
		} else {
			nextPage = page + 1;
		}
		s += "<tr><td colspan='6'>" + "<a href='javascript:void(0)' onclick='addStudent(1);'>" + "首页" + "</a>&nbsp;" + "<a href='javascript:void(0)' onclick='addStudent(" + previousPage + ");'>" + "上一页" + "</a>&nbsp;" + "<a href='javascript:void(0)' onclick='addStudent(" + nextPage + "," + pageModel.get("totalPage") + ");'>" + "下一页" + "</a>&nbsp;" + "<a href='javascript:void(0)' onclick='addStudent(" + (totalRecords + pageSize - 1) / pageSize + ");'>" + "末页" + "</a>&nbsp;" + "当前第" + page + "页&nbsp; 共" + pageModel.get("totalPage") + "页  " + totalRecords + "条记录" + "</td></tr>";
		return htmlEncode(s);
	}

	/****************************************
	 * 功能：设备管理--培训计划--已通过名单列表 作者：贺子龙 日期：2016-03-22
	 ***************************************/
	@RequestMapping("device/managePermitUser")
	public ModelAndView managePermitUser(@RequestParam int deviceId, int currpage) {
		ModelAndView mav = new ModelAndView();
		int pageSize = 20;
		int totalRecords = 0;
		if (labRoomDeviceService.findPermitUserByDeivceId(deviceId, 1, -1) != null) {
			totalRecords = labRoomDeviceService.findPermitUserByDeivceId(deviceId, 1, -1).size();
		}
		List<LabRoomDevicePermitUsers> students = labRoomDeviceService.findPermitUserByDeivceId(deviceId, currpage, pageSize);
		mav.addObject("students", students);
		mav.addObject("student", new LabRoomDevicePermitUsers());

		mav.addObject("deviceId", deviceId);
		mav.addObject("pageModel", shareService.getPage(currpage, pageSize, totalRecords));
		mav.addObject("currpage", currpage);
		mav.addObject("pageSize", pageSize);
		mav.addObject("totalRecords", totalRecords);
		mav.setViewName("/device/managePermitUser.jsp");
		return mav;
	}

	/****************************************
	 * 功能：设备管理--培训计划--已通过名单--删除 作者：贺子龙 日期：2016-03-22
	 ***************************************/
	@RequestMapping("device/deletePermitUser")
	public ModelAndView deletePermitUser(@RequestParam int id) {
		ModelAndView mav = new ModelAndView();
		LabRoomDevicePermitUsers permitUser = labRoomDeviceService.findLabRoomDevicePermitUsersByPrimaryKey(id);
		String username = permitUser.getUser().getUsername();
		int deviceId = permitUser.getLabRoomDevice().getId();
		if (permitUser.getFlag() == 2) {// 集训通过的人，如果删除的话需要将集训结果置为未通过并重新计算培训的通过率
			LabRoomDevice device = labRoomDeviceService.findLabRoomDeviceByPrimaryKey(deviceId);
			for (LabRoomDeviceTraining training : device.getLabRoomDeviceTrainings()) {
				int trainingId = training.getId();
				List<LabRoomDeviceTrainingPeople> peoples = labRoomDeviceService.findTrainingPeoplesByTrainingId(trainingId);
				for (LabRoomDeviceTrainingPeople labRoomDeviceTrainingPeople : peoples) {
					if (!labRoomDeviceTrainingPeople.getUser().getUsername().equals(username)) {// 不是当前用户，继续
						continue;
					} else {// 是当前用户，将培训结果设置为未通过
							// 将集训结果置为未通过
						//CTrainingResult result = cTrainingResultDAO.findCTrainingResultByPrimaryKey(2);
						CDictionary result = shareService.getCDictionaryByCategory("c_training_result", "2");
						labRoomDeviceTrainingPeople.setCDictionary(result);
						labRoomDeviceTrainingPeopleDAO.store(labRoomDeviceTrainingPeople);
					}
				}

				// 重新计算培训的通过率
				if (training.getCTrainingStatus().getId() == 2) {
					// 该培训的培训人
					Set<LabRoomDeviceTrainingPeople> trainingpeoples = training.getLabRoomDeviceTrainingPeoples();
					// 根据培训id查询培训通过的人
					List<LabRoomDeviceTrainingPeople> passPeoples = labRoomDeviceService.findPassLabRoomDeviceTrainingPeopleByTrainId(training.getId());
					// 计算通过率
					double a = passPeoples.size();
					double b = trainingpeoples.size();
					double c = a / b;
					// 获取格式化对象
					NumberFormat nt = NumberFormat.getPercentInstance();
					// 设置百分数精确度2即保留两位小数
					nt.setMinimumFractionDigits(2);
					String s = nt.format(c);
					training.setPassRate(s);
					labRoomDeviceTrainingDAO.store(training);
				}
			}
		}
		labRoomDeviceService.deletePermitUser(permitUser);
		mav.setViewName("redirect:/device/managePermitUser?deviceId=" + deviceId + "&currpage=1");
		return mav;
	}

	/****************************************************************************
	 * 功能：保存集中培训学生后门 作者：贺子龙 日期：2016-03-22
	 ****************************************************************************/
	@RequestMapping("device/savePermitUser")
	public ModelAndView savePermitUser(@RequestParam String usernameStr, @RequestParam int deviceId) {
		String[] array = usernameStr.split("S");
		ModelAndView mav = new ModelAndView();
		// id对应的培训
		LabRoomDevice device = labRoomDeviceService.findLabRoomDeviceByPrimaryKey(deviceId);
		for (String i : array) {
			// username对应的用户
			LabRoomDevicePermitUsers student = new LabRoomDevicePermitUsers();
			User u = userDAO.findUserByPrimaryKey(i);
			student.setUser(u);
			student.setLabRoomDevice(device);
			student.setFlag(3);// 标记位（1 单独培训通过 2 集训通过 3 集训后门）
			labRoomDevicePermitUsersDAO.store(student);
		}
		mav.setViewName("redirect:/device/managePermitUser?deviceId=" + deviceId + "&currpage=1");
		return mav;
	}

	/************************************************************
	 * 功能：判断该学生是否能进行该培训的预约 作者：贺子龙 日期：2016-03-22
	 ************************************************************/
	@RequestMapping("/device/ifPermitted")
	public @ResponseBody String ifPermitted(@RequestParam int deviceId) {
		// 获取当前用户
		String username = shareService.getUser().getUsername();
		// 找到当前培训所对应的设备
		LabRoomDevicePermitUsers permitUser = labRoomDeviceService.findPermitUserByUsernameAndDeivce(username, deviceId);
		if (permitUser != null) {
			return "permitted";
		} else {
			LabRoomDevice device = labRoomDeviceService.findLabRoomDeviceByPrimaryKey(deviceId);
			for (LabRoomDeviceTraining training : device.getLabRoomDeviceTrainings()) {
				if(training.getCTrainingStatus().getId().equals(4)){// 预约被取消
					continue;
				}
				int trainingId = training.getId();
				List<LabRoomDeviceTrainingPeople> peoples = labRoomDeviceService.findTrainingPeoplesByTrainingId(trainingId);
				for (LabRoomDeviceTrainingPeople labRoomDeviceTrainingPeople : peoples) {
					if (!labRoomDeviceTrainingPeople.getUser().getUsername().equals(username)) {// 不是当前用户，继续
						continue;
					} else {// 是当前用户，判断是否已经通过
						if (labRoomDeviceTrainingPeople.getCDictionary() == null) {// 未有结果
							return "wait";
						} else {
							if ("1".equals(labRoomDeviceTrainingPeople.getCDictionary().getCNumber())) {
								// 通过
								return "permitted";
							} else {
								// 未通过
								return "ok";
							}
						}
					}
				}
			}
		}
		return "ok";
	}

	/****************************************************************************
	 * 功能：ajax查询设备 作者：贺子龙 日期：2016-04-04
	 ****************************************************************************/
	@RequestMapping("/device/findSchoolDeviceByNameAndNumber")
	public @ResponseBody String findSchoolDeviceByNameAndNumber(@RequestParam int labRoomId, String deviceName, String deviceNumber, int page, @ModelAttribute("selected_labCenter") Integer cid) {

		int pageSize = 100;
		List<SchoolDevice> allSchoolDevice = schoolDeviceService.findSchoolDeviceByNameAndNumber(cid, labRoomId, deviceName, deviceNumber, page, pageSize);
		int totalRecords = schoolDeviceService.countSchoolDeviceByNameAndNumber(cid, labRoomId, deviceName, deviceNumber);

		Map<String, Integer> pageModel = shareService.getPage(page, pageSize, totalRecords);
		String s = "";
		for (SchoolDevice device : allSchoolDevice) {
			s += "<tr>" 
		+ "<td>" + device.getDeviceNumber() + "</td>" 
					+ "<td>" + device.getDeviceName() + "</td>" 
		+ "<td>" + device.getDevicePattern() + "</td>" 
					+ "<td>" + device.getDevicePrice() + "</td>" 
		+ "<td><input type='checkbox' name='CK' value='" + device.getDeviceNumber() + "'/></td>" 
					+ "</tr>";

		}
		int previousPage;
		int nextPage;
		if (page == 1) {
			previousPage = page;
		} else {
			previousPage = page - 1;
		}

		if (page == (totalRecords + pageSize - 1) / pageSize) {
			nextPage = page;
		} else {
			nextPage = page + 1;
		}

		s += "<tr><td colspan='7'>" 
		+ "<a href='javascript:void(0)' onclick='addDevices(1);'>" + "首页" + "</a>&nbsp;" 
				+ "<a href='javascript:void(0)' onclick='addDevices(" + previousPage + ");'>" + "上一页" 
		+ "</a>&nbsp;" + "<a href='javascript:void(0)' onclick='addDevices(" + nextPage + ");'>" + "下一页" 
				+ "</a>&nbsp;" 
		+ "<a href='javascript:void(0)' onclick='addDevices(" + (totalRecords + pageSize - 1) / pageSize + ");'>" + "末页" + "</a>&nbsp;" + "当前第" + page + "页&nbsp; 共" + pageModel.get("totalPage") + "页  " + totalRecords + "条记录" + "</td></tr>";
		return shareService.htmlEncode(s);
	}

	/****************************************************************************
	 * 功能：保存关联设备 作者：贺子龙 日期：2016-04-04
	 ****************************************************************************/
	@RequestMapping("/device/saveInnerSameDevice")
	public String saveInnerSameDevice(@RequestParam int deviceId, String[] array) {
		// 根据id找到该实验室设备
		LabRoomDevice labRoomDevice = labRoomDeviceService.findLabRoomDeviceByPrimaryKey(deviceId);
		SchoolDevice self = labRoomDevice.getSchoolDevice();
		// 判断该设备是否已经属于某一组合
		boolean isSeted = false;
		if (labRoomDevice.getSchoolDevice().getInnerSame() != null) {
			isSeted = true;
		}

		// 初始化innerSame,默认为0
		int innerSame = 0;
		// 关联设备关联后的名字
		String innerDeviceName = "";

		if (isSeted) {
			innerSame = labRoomDevice.getSchoolDevice().getInnerSame();
			innerDeviceName += labRoomDevice.getSchoolDevice().getInnerDeviceName()+"["+labRoomDevice.getSchoolDevice().getDeviceNumber()+"]" + " ";
		} else {
			innerSame = schoolDeviceService.maxSchoolDeviceSet() + 1;
			innerDeviceName += self.getDeviceName()+"["+self.getDeviceNumber()+"]" + " ";
		}

		for (String deviceNumber : array) {
			SchoolDevice schoolDevice = schoolDeviceService.findSchoolDeviceByPrimaryKey(deviceNumber);
			innerDeviceName += schoolDevice.getDeviceName()+"["+schoolDevice.getDeviceNumber()+"]" + " ";
		}

		// 将本设备设置成关联设备
		self.setInnerSame(innerSame);
		self.setInnerDeviceName(innerDeviceName);
		schoolDeviceService.saveSchoolDevice(self);

		// 将选择的设备设置成关联设备
		for (String deviceNumber : array) {
			SchoolDevice schoolDevice = schoolDeviceService.findSchoolDeviceByPrimaryKey(deviceNumber);
			schoolDevice.setInnerSame(innerSame);
			schoolDevice.setInnerDeviceName(innerDeviceName);
			schoolDeviceService.saveSchoolDevice(schoolDevice);
		}

		// 将该设备组合中原来的设备设置一下innerDeviceName
		if (isSeted) {
			List<SchoolDevice> devices = schoolDeviceService.findSchoolDeviceSet(innerSame);
			for (SchoolDevice schoolDevice : devices) {
				schoolDevice.setInnerSame(innerSame);
				schoolDevice.setInnerDeviceName(innerDeviceName);
				schoolDeviceService.saveSchoolDevice(schoolDevice);
			}
		}

		return "redirect:/device/listInnerSameDevice?deviceId=" + deviceId;
	}

	/****************************************************************************
	 * 功能：解除关联设备 作者：贺子龙 日期：2016-04-04
	 ****************************************************************************/
	@RequestMapping("/device/deleteInnerSameDevice")
	public String deleteInnerSameDevice(@RequestParam int deviceId, String deviceNumber) {
		// 根据id找到该实验室设备
		LabRoomDevice labRoomDevice = labRoomDeviceService.findLabRoomDeviceByPrimaryKey(deviceId);
		// 找到该套设备
		int innerSame = labRoomDevice.getSchoolDevice().getInnerSame();
		// 找到要解除关联的设备
		SchoolDevice targetDevice = schoolDeviceService.findSchoolDeviceByPrimaryKey(deviceNumber);
		targetDevice.setInnerSame(null);
		// 将解除的设备从innerDeviceName中去除
		String innerDeviceName = labRoomDevice.getSchoolDevice().getInnerDeviceName()
				.replaceFirst(targetDevice.getDeviceName(), "")
				.replaceFirst(targetDevice.getDeviceNumber(), "").replace("[]", "");

		List<SchoolDevice> devices = schoolDeviceService.findSchoolDeviceSet(innerSame);
		// 重新设置关联设备的innerSame和innerDeviceName
		if (devices.size() > 0) {
			for (SchoolDevice schoolDevice : devices) {
				schoolDevice.setInnerSame(innerSame);
				schoolDevice.setInnerDeviceName(innerDeviceName);
				schoolDeviceService.saveSchoolDevice(schoolDevice);
			}
		}
		return "redirect:/device/listInnerSameDevice?deviceId=" + deviceId;
	}
	
	/****************************************************************************
	 * @功能：取消培训
	 * @作者：贺子龙
	 ****************************************************************************/
	@RequestMapping("/device/cancelTraining")
	public @ResponseBody String cancelTraining(@RequestParam Integer id){
		// id对应的培训
		LabRoomDeviceTraining train = labRoomDeviceTrainingDAO.findLabRoomDeviceTrainingByPrimaryKey(id);
		//CTrainingStatus status = cTrainingStatusDAO.findCTrainingStatusByPrimaryKey(4);
		CDictionary status = shareService.getCDictionaryByCategory("c_training_status", "4");
		train.setCTrainingStatus(status);
		labRoomDeviceTrainingDAO.store(train);
		// 告知培训学生
		Set<LabRoomDeviceTrainingPeople> people = train.getLabRoomDeviceTrainingPeoples();
		if (people!=null && people.size()>0) {
			for (LabRoomDeviceTrainingPeople labRoomDeviceTrainingPeople : people) {
				labRoomDeviceTrainingPeople.setMessageFlag(2);
				labRoomDeviceTrainingPeopleDAO.store(labRoomDeviceTrainingPeople);
			}
		}
		
		return "success";
	}
	
	/****************************************************************************
	 * @功能：针对学生--培训预约时间变化，我知道了
	 * @作者：贺子龙
	 ****************************************************************************/
	@RequestMapping("/device/alreadyKnownMessege")
	public @ResponseBody String alreadyKnownMessege(@RequestParam int id){
		// 获取当前用户
		User user = shareService.getUser();
		List<LabRoomDeviceTrainingPeople> trainPeople = labRoomDeviceService.findTrainingPeoplesByTrainingId(id);
		if (trainPeople!=null && trainPeople.size()>0) {
			for (LabRoomDeviceTrainingPeople labRoomDeviceTrainingPeople : trainPeople) {
				if (labRoomDeviceTrainingPeople.getUser().getUsername() == user.getUsername()) {
					labRoomDeviceTrainingPeople.setMessageFlag(0);
					labRoomDeviceTrainingPeopleDAO.store(labRoomDeviceTrainingPeople);
				}else {
					continue;
				}
			}
		}
		return "success";
	}
	
	/************************************************************
	 * 功能：排课管理--分组管理--修改分批时间
	 * 作者：贺子龙
	 * 日期：2016-03-21
	 * @throws ParseException 
	 ************************************************************/
	@RequestMapping("/device/altTrainTime")
	public @ResponseBody
	String altTrainTime(@RequestParam int id,HttpServletRequest request) throws ParseException {
		// id对应的培训
		LabRoomDeviceTraining train = labRoomDeviceTrainingDAO.findLabRoomDeviceTrainingByPrimaryKey(id);
		if (train==null) {
			return "error";
		}
		if (request.getParameter("begintime")==null||request.getParameter("begintime").equals("")) {
			return "error";
		}
		//字符串转日期
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date start =sdf.parse(request.getParameter("begintime"));//从前台将begintime取出来，然后转化成calendar格式
		Calendar startDate = Calendar.getInstance();
		startDate.setTime(start);
		
		train.setTime(startDate);
		
		labRoomDeviceTrainingDAO.store(train);
		
		// 告知培训学生
		Set<LabRoomDeviceTrainingPeople> people = train.getLabRoomDeviceTrainingPeoples();
		if (people!=null && people.size()>0) {
			for (LabRoomDeviceTrainingPeople labRoomDeviceTrainingPeople : people) {
				labRoomDeviceTrainingPeople.setMessageFlag(1);
				labRoomDeviceTrainingPeopleDAO.store(labRoomDeviceTrainingPeople);
			}
		}
		
		return "success";
	}

	/****************************************************************************
	 * @description：课题组管理-列表
	 * @author：郑昕茹
	 * @date:2016-11-06
	 ****************************************************************************/
	@RequestMapping("/device/listAllResearchProjects")
	public ModelAndView listAllResearchProjects(@ModelAttribute ResearchProject researchProject, @RequestParam Integer page) {
		// 新建ModelAndView对象；
		ModelAndView mav = new ModelAndView(); 
		int pageSize = 20;
		//课题组分页
		List<ResearchProject> listResearchProjects = labRoomDeviceService.findAllResearchProjects(researchProject, page, pageSize);
		List<ResearchProject> listAllResearchProjects = labRoomDeviceService.findAllResearchProjects(new ResearchProject(), 1, -1);   
		// 查询出来的总记录条数
		int totalRecords = labRoomDeviceService.findAllResearchProjects(researchProject, 1, -1).size();
		// 分页信息
		Map<String, Integer> pageModel = shareService.getPage(page, pageSize, totalRecords); 
		mav.addObject("listResearchProjects", listResearchProjects);
		mav.addObject("listAllResearchProjects", listAllResearchProjects);
		mav.addObject("pageModel", pageModel); 
		mav.addObject("totalRecords", totalRecords); 
		mav.addObject("page", page);
		mav.addObject("pageSize", pageSize);
		mav.addObject("researchProject", researchProject);
		mav.setViewName("/device/listAllResearchProjects.jsp");
		return mav;
	}

	
	/****************************************************************************
	 * @description：课题组管理-新建课题组
	 * @author：郑昕茹
	 * @date:2016-11-06
	 ****************************************************************************/
	@RequestMapping("/device/newResearchProject")
	public ModelAndView newResearchProject( ){
		ModelAndView mav = new ModelAndView();
		mav.addObject("researchProject", new ResearchProject());
		mav.setViewName("/device/newResearchProject.jsp");
		return mav;
	}
	
	/****************************************************************************
	 * @description：课题组管理-编辑课题组
	 * @author：郑昕茹
	 * @date:2016-11-06
	 ****************************************************************************/
	@RequestMapping("/device/editResearchProject")
	public ModelAndView editResearchProject(@RequestParam Integer id){
		ModelAndView mav = new ModelAndView();
		mav.addObject("researchProject", labRoomDeviceService.findResearchProjectByPrimaryKey(id));
		mav.setViewName("/device/newResearchProject.jsp");
		return mav;
	}
	
	/****************************************************************************
	 * @description：课题组管理-保存课题组
	 * @author：郑昕茹
	 * @date:2016-11-06
	 ****************************************************************************/
	@RequestMapping("/device/saveResearchProject")
	public ModelAndView saveResearchProject(@ModelAttribute ResearchProject researchProject){
		ModelAndView mav = new ModelAndView();
		labRoomDeviceService.saveResearchProject(researchProject); 
		mav.setViewName("redirect:/device/listAllResearchProjects?page=1");
		return mav;
	}
	
	/****************************************************************************
	 * @description：课题组管理-删除课题组
	 * @author：郑昕茹
	 * @date:2016-11-06
	 ****************************************************************************/
	@RequestMapping("/device/deleteResearchProject")
	public ModelAndView deleteResearchProject(@RequestParam Integer id){
		ModelAndView mav = new ModelAndView();
		labRoomDeviceService.deleteResearchProject(id); 
		mav.setViewName("redirect:/device/listAllResearchProjects?page=1");
		return mav;
	}
	
	/************************************************************
	 * 功能：排课管理--分组管理--修改分批时间
	 * 作者：贺子龙
	 * 日期：2016-03-21
	 * @throws ParseException 
	 ************************************************************/
	@RequestMapping("/device/addTimeAndAddress")
	public @ResponseBody
	String addTimeAndAddress(@RequestParam int id,HttpServletRequest request) throws ParseException {
		// id对应的培训
		LabRoomDeviceTraining train = labRoomDeviceTrainingDAO.findLabRoomDeviceTrainingByPrimaryKey(id);
		if (train==null) {
			return "error";
		}
		if (request.getParameter("begintime")==null||request.getParameter("begintime").equals("")) {
			return "error";
		}
		//字符串转日期
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date start =sdf.parse(request.getParameter("begintime"));//从前台将begintime取出来，然后转化成calendar格式
		Calendar startDate = Calendar.getInstance();
		startDate.setTime(start);
		
		train.setTime(startDate);
		train.setSchoolTerm(shareService.getBelongsSchoolTerm(startDate));
		train.setAddress(request.getParameter("address"));
		labRoomDeviceTrainingDAO.store(train);
		
		// 告知培训学生
		Set<LabRoomDeviceTrainingPeople> people = train.getLabRoomDeviceTrainingPeoples();
		if (people!=null && people.size()>0) {
			for (LabRoomDeviceTrainingPeople labRoomDeviceTrainingPeople : people) {
				Message message= new Message();
				Calendar date=Calendar.getInstance();
				message.setSendUser(shareService.getUserDetail().getCname());
				message.setSendCparty(shareService.getUserDetail().getSchoolAcademy().getAcademyName());
				message.setCond(0);
				message.setTitle("培训申请确认");
				String content="培训时间和地点已设置";
				content+="<a  href='/xidlims/device/editDeviceTrainingRest/"+ train.getLabRoomDevice().getLabRoom().getId() + "/"+ "-1" + "/" + "-1" +"/-1/" +"1/"+ train.getLabRoomDevice().getId()+"/-1"+"'";
				 
				content+=" >点击查看</a>";
//				message.setContent("申请成功，等待审核<a  href='/xidlims/operation/viewOperationItem?operationItemId=44956&&flag=1&status=2'>点击查看</a>");
				message.setContent(content);
				message.setMessageState(CommonConstantInterface.INT_Flag_ZERO);
				message.setCreateTime(date);
				message.setUsername(labRoomDeviceTrainingPeople.getUser().getUsername());
				message=messageDAO.store(message);
			}
		}
		
		return "success";
	}

}