/****************************************************************************
 * 命名规范：
 * 本控制层调整必须是按照如下的路径调整：@RequestMapping("/device/sample")，全部小写。
 * 列表信息listxxxx，獲取信息：getxxxx 编辑信息：editxxxx 删除信息：detelexxxx 新增信息 newxxxx
 * 导出信息exportxxxx 保存信息：savexxxx  
 ****************************************************************************/

package net.xidlims.web.device;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

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
import net.xidlims.dao.MessageDAO;
import net.xidlims.dao.SchoolDeviceDAO;
import net.xidlims.dao.UserDAO;
import net.xidlims.domain.Authority;
import net.xidlims.domain.CDictionary;
import net.xidlims.domain.CStaticValue;
import net.xidlims.domain.CommonDocument;
import net.xidlims.domain.CommonVideo;
import net.xidlims.domain.LabRoom;
import net.xidlims.domain.LabRoomDevice;
import net.xidlims.domain.LabRoomDevicePermitUsers;
import net.xidlims.domain.LabRoomDeviceReservation;
import net.xidlims.domain.LabRoomDeviceTraining;
import net.xidlims.domain.LabRoomDeviceTrainingPeople;
import net.xidlims.domain.Message;
import net.xidlims.domain.ResearchProject;
import net.xidlims.domain.SchoolDevice;
import net.xidlims.domain.SchoolTerm;
import net.xidlims.domain.User;
import net.xidlims.service.common.CommonDocumentService;
import net.xidlims.service.common.CommonVideoService;
import net.xidlims.service.common.ShareService;
import net.xidlims.service.device.LabRoomDeviceService;
import net.xidlims.service.device.SchoolDeviceService;
import net.xidlims.service.dictionary.CDictionaryService;
import net.xidlims.service.lab.LabRoomService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

/****************************************************************************
 * @功能： 设备管理模块
 * @作者：魏诚
 * @时间：2014-07-14
 ****************************************************************************/
@Controller("DeviceControllerRest")
@SessionAttributes("selected_labCenter")
public class DeviceControllerRest<JsonResult> {
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
	LabRoomDeviceReservationDAO labRoomDeviceReservationDAO;
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
	LabRoomDevicePermitUsersDAO labRoomDevicePermitUsersDAO;
	@Autowired
	SchoolDeviceService schoolDeviceService;
	@Autowired
	CDictionaryService cDictionaryService;
	@Autowired
	MessageDAO messageDAO;

	@InitBinder
	public void initBinder(WebDataBinder binder, HttpServletRequest request) { // Register
																				// static
																				// property
																				// editors.
		binder.registerCustomEditor(java.util.Calendar.class,
				new org.skyway.spring.util.databinding.CustomCalendarEditor());
		binder.registerCustomEditor(byte[].class,
				new org.springframework.web.multipart.support.ByteArrayMultipartFileEditor());
		binder.registerCustomEditor(boolean.class, new org.skyway.spring.util.databinding.EnhancedBooleanEditor(false));
		binder.registerCustomEditor(Boolean.class, new org.skyway.spring.util.databinding.EnhancedBooleanEditor(true));
		binder.registerCustomEditor(java.math.BigDecimal.class,
				new org.skyway.spring.util.databinding.NaNHandlingNumberEditor(java.math.BigDecimal.class, true));
		binder.registerCustomEditor(Integer.class, new org.skyway.spring.util.databinding.NaNHandlingNumberEditor(
				Integer.class, true));
		binder.registerCustomEditor(java.util.Date.class, new org.skyway.spring.util.databinding.CustomDateEditor());
		binder.registerCustomEditor(String.class, new org.skyway.spring.util.databinding.StringEditor());
		binder.registerCustomEditor(Long.class, new org.skyway.spring.util.databinding.NaNHandlingNumberEditor(
				Long.class, true));
		binder.registerCustomEditor(Double.class, new org.skyway.spring.util.databinding.NaNHandlingNumberEditor(
				Double.class, true));
	}

	/****************************************************************************
	 * @功能：实验室设备管理---设备管理--所有设备
	 * @作者：魏诚
	 * @时间：2015-10-11
	 ****************************************************************************/
	@RequestMapping(value = "device/listLabRoomDeviceRest/{labRoomId}/{deviceNumber}/{deviceName}/{username}/{page}/{schoolDevice_allowAppointment}", method = RequestMethod.GET)
	public ModelAndView listLabRoomDeviceRest(@PathVariable int labRoomId, @PathVariable String deviceNumber,
			@PathVariable String deviceName, @PathVariable String username,@PathVariable int page,@PathVariable String schoolDevice_allowAppointment, Model model,
			@ModelAttribute("selected_labCenter") Integer cid, @ModelAttribute LabRoomDevice labRoomDevice) {
		// 新建ModelAndView对象；
		ModelAndView mav = new ModelAndView();
		// 对对象进行rest赋值
		LabRoom labRoom = labRoomDAO.findLabRoomById(labRoomId);
		mav.addObject("roomId", labRoomId);
		SchoolDevice schoolDevice = new SchoolDevice();
		
		if(!"-1".equals(deviceName)){
			schoolDevice.setDeviceName(deviceName);
		}
		if(!"-1".equals(deviceNumber)){
			schoolDevice.setDeviceNumber(deviceNumber);
		}
		if(!"-1".equals(username)){
			User user = userDAO.findUserByPrimaryKey(username);
			labRoomDevice.setUser(user);
		}
		CDictionary cDictionary = new CDictionary();
		cDictionary.setCNumber(schoolDevice_allowAppointment);
		if(!"-1".equals(schoolDevice_allowAppointment)){
			labRoomDevice.setCDictionaryByAllowAppointment(cDictionary);
		}
		labRoomDevice.setLabRoom(labRoom);
		labRoomDevice.setSchoolDevice(schoolDevice);

		// 中心所属学院
		//String academy = labCenterDAO.findLabCenterByPrimaryKey(cid).getSchoolAcademy().getAcademyNumber();

		// 查询表单的对象
		mav.addObject("labRoomDevice", labRoomDevice);
		// 实验室
		List<LabRoom> rooms = labRoomService.findLabRoomByLabCenterid(cid,1);
		mav.addObject("rooms", rooms);
		// 设备管理员
		List<User> users = shareService.findUsersByAuthorityId(10);
		mav.addObject("users", users);
		int pageSize = 10;// 每页20条记录
		// 查询出来的总记录条数
		int totalRecords = labRoomDeviceService.findAllLabRoomDeviceNew(labRoomDevice, cid,1);
		// 分页信息
		Map<String, Integer> pageModel = shareService.getPage(page, pageSize, totalRecords);
		// 根据分页信息查询出来的记录
		List<LabRoomDevice> listLabRoomDevice = labRoomDeviceService.findAllLabRoomDeviceNew(labRoomDevice, cid, page,
				pageSize,1);
		mav.addObject("listLabRoomDevice", listLabRoomDevice);
		// 查询所有设备记录
		List<LabRoomDevice> listLabRoomDeviceAll = labRoomDeviceService.findAllLabRoomDeviceNew(labRoomDevice, cid, 1, -1,1);
		mav.addObject("listLabRoomDeviceAll", listLabRoomDeviceAll);
		mav.addObject("pageModel", pageModel);
		mav.addObject("totalRecords", totalRecords);
		mav.addObject("page", page);
		mav.addObject("pageSize", pageSize);
		//获取所有的设备管理员
		mav.addObject("userMap", labRoomDeviceService.findDeviceManageCnamerByCid(cid));
		
		//获取当前用户传递到前台
		User user = shareService.getUser();
		mav.addObject("user", user);
		mav.setViewName("/device/specialAcademy/specialAcademyForLabroom.jsp");
		// 根据学院编号判断是否为配置文件配置的特殊学院（化工学院）
		/*boolean flag = specialAcademy.contains(academy);
		if (flag) {
			if (academy.equals("0201")) {//纺织学院
				mav.setViewName("/device/specialAcademy/specialAcademyForLabroom.jsp");
				
			}else mav.setViewName("/device/specialAcademy/specialAcademy.jsp");
		} else {
			mav.setViewName("/device/lab_room_device/listLabRoomDevice.jsp");
		}
		*/
		return mav;
	}

	/****************************************************************************
	 * @功能：实验室设备管理---设备管理---编辑（化工学院）
	 * @作者：李小龙
	 ****************************************************************************/
	@RequestMapping(value = "/device/editDeviceInfoRest/{labRoomId}/{deviceNumber}/{deviceName}/{username}/{page}/{id}/{schoolDevice_allowAppointment}", method = RequestMethod.GET)
	public ModelAndView editDeviceInfoRest(@PathVariable int labRoomId,
			@PathVariable String deviceNumber, @PathVariable String deviceName,@PathVariable String username,
			@PathVariable int page,@PathVariable String schoolDevice_allowAppointment,@PathVariable int id, Model model,
			@ModelAttribute("selected_labCenter") Integer cid) {
		// 新建ModelAndView对象；
		ModelAndView mav = new ModelAndView();

		// 对对象进行rest赋值
		LabRoom labRoom = labRoomDAO.findLabRoomById(labRoomId);
		if(labRoom==null){
			labRoom = new LabRoom();
			labRoom.setId(-1);
		}
		LabRoomDevice labRoomDevice = new LabRoomDevice();
		SchoolDevice schoolDevice = new SchoolDevice();
		schoolDevice.setDeviceName(deviceName);
		schoolDevice.setDeviceNumber(deviceNumber);
		labRoomDevice.setLabRoom(labRoom);
		labRoomDevice.setSchoolDevice(schoolDevice);
		mav.addObject("labRoomDevice", labRoomDevice);

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
		List<LabRoomDeviceTraining> trainings = labRoomDeviceService
				.findLabRoomDeviceTrainingByTermId(term.getId(), id);
		mav.addObject("trainings", trainings);
		// 培训表单的对象
		mav.addObject("train", new LabRoomDeviceTraining());
		// 培训教师

		mav.setViewName("/device/specialAcademy/editDeviceInfo.jsp");
		
		//贺子龙新增2015-10-19
		mav.addObject("labRoomId", labRoomId);
		mav.addObject("deviceName", deviceName);
		mav.addObject("deviceNumber", deviceNumber);
		mav.addObject("username", username);
		mav.addObject("page", page);
		mav.addObject("schoolDevice_allowAppointment", schoolDevice_allowAppointment);
		return mav;
	}
	
	/****************************************************************************
	 * @功能：实验室设备管理---参数设置---编辑（化工学院）
	 * @作者：李小龙
	 ****************************************************************************/
	@RequestMapping(value = "/device/editDeviceSettingRest/{labRoomId}/{deviceNumber}/{deviceName}/{username}/{page}/{id}/{schoolDevice_allowAppointment}", method = RequestMethod.GET)
	public ModelAndView editDeviceSettingRest(@PathVariable int labRoomId,
			@PathVariable String deviceNumber, @PathVariable String deviceName,@PathVariable String username,
			@PathVariable int page,@PathVariable int id, @PathVariable String schoolDevice_allowAppointment,Model model,
			@ModelAttribute("selected_labCenter") Integer cid) {
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
		//培训形式
		/*Set<CTrainingType> CTrainingTypes=cTrainingTypeDAO.findAllCTrainingTypes();*/
		List<CDictionary> CTrainingTypes = shareService.getCDictionaryData("c_training_type");
		mav.addObject("CTrainingTypes", CTrainingTypes);
		
		mav.setViewName("/device/lab_room_device_access/editDeviceSetting.jsp");
		/*if (device.getCActiveByIsAudit()!=null&&device.getCActiveByIsAudit().getId()!=null) {
			
			mav.addObject("isAudit", device.getCActiveByIsAudit().getId());
		}
		if (device.getCActiveByAllowSecurityAccess()!=null&&device.getCActiveByAllowSecurityAccess().getId()!=null) {
			mav.addObject("allowSecurityAccess", device.getCActiveByAllowSecurityAccess().getId());
		}
		if (device.getCActiveByAllowAppointment()!=null&&device.getCActiveByAllowAppointment().getId()!=null) {
			mav.addObject("allowAppointment", device.getCActiveByAllowAppointment().getId());
		}
		if (device.getCActiveByTeacherAudit()!=null&&device.getCActiveByTeacherAudit().getId()!=null) {
			mav.addObject("needtutor", device.getCActiveByTeacherAudit().getId());
		}*/
		
		if (device.getCDictionaryByIsAudit()!=null&&device.getCDictionaryByIsAudit().getId()!=null) {
			
			mav.addObject("isAudit", device.getCDictionaryByIsAudit().getCNumber());
		}
		if (device.getCDictionaryByAllowSecurityAccess()!=null&&device.getCDictionaryByAllowSecurityAccess().getId()!=null) {
			mav.addObject("allowSecurityAccess", device.getCDictionaryByAllowSecurityAccess().getCNumber());
		}
		if (device.getCDictionaryByAllowAppointment()!=null&&device.getCDictionaryByAllowAppointment().getId()!=null) {
			mav.addObject("allowAppointment", device.getCDictionaryByAllowAppointment().getCNumber());
		}
		if (device.getCDictionaryByTeacherAudit()!=null&&device.getCDictionaryByTeacherAudit().getId()!=null) {
			mav.addObject("needtutor", device.getCDictionaryByTeacherAudit().getCNumber());
		}
		
		
		mav.addObject("labRoomId", labRoomId);
		mav.addObject("deviceName", deviceName);
		mav.addObject("deviceNumber", deviceNumber);
		mav.addObject("username", username);
		mav.addObject("page", page);
		mav.addObject("schoolDevice_allowAppointment", schoolDevice_allowAppointment);
		return mav;
	}
	
	/****************************************************************************
	 * @功能：设备图片（化工学院）
	 * @作者：李小龙
	 ****************************************************************************/
	@RequestMapping(value = "/device/editDeviceImageRest/{labRoomId}/{deviceNumber}/{deviceName}/{username}/{page}/{id}/{schoolDevice_allowAppointment}", method = RequestMethod.GET)
	public ModelAndView editDeviceImageRest(@PathVariable int labRoomId,
			@PathVariable String deviceNumber, @PathVariable String deviceName, @PathVariable String username, 
			@PathVariable int page,@PathVariable int id,@PathVariable String schoolDevice_allowAppointment, Model model,
			@ModelAttribute("selected_labCenter") Integer cid) {
		// 新建ModelAndView对象；
		ModelAndView mav = new ModelAndView();
		LabRoomDevice device = labRoomDeviceService.findLabRoomDeviceByPrimaryKey(id);
		mav.addObject("device", device);
		mav.setViewName("/device/specialAcademy/deviceImage.jsp");
		
		mav.addObject("labRoomId", labRoomId);
		mav.addObject("deviceName", deviceName);
		mav.addObject("deviceNumber", deviceNumber);
		mav.addObject("username", username);
		mav.addObject("page", page);
		mav.addObject("schoolDevice_allowAppointment", schoolDevice_allowAppointment);
		return mav;
	}
	
	/****************************************************************************
	 * @功能：设备视频（化工学院）
	 * @作者：李小龙
	 ****************************************************************************/
	@RequestMapping(value = "/device/editDeviceVideoRest/{labRoomId}/{deviceNumber}/{deviceName}/{username}/{page}/{id}/{schoolDevice_allowAppointment}", method = RequestMethod.GET)
	public ModelAndView editDeviceVideoRest(@PathVariable int labRoomId,
			@PathVariable String deviceNumber, @PathVariable String deviceName, @PathVariable String username, 
			@PathVariable int page,@PathVariable int id, @PathVariable String schoolDevice_allowAppointment,Model model,
			@ModelAttribute("selected_labCenter") Integer cid) {
		// 新建ModelAndView对象；
		ModelAndView mav = new ModelAndView();
		LabRoomDevice device = labRoomDeviceService.findLabRoomDeviceByPrimaryKey(id);
		mav.addObject("device", device);
		mav.setViewName("/device/specialAcademy/deviceVideo.jsp");
		
		mav.addObject("labRoomId", labRoomId);
		mav.addObject("deviceName", deviceName);
		mav.addObject("deviceNumber", deviceNumber);
		mav.addObject("username", username);
		mav.addObject("page", page);
		mav.addObject("schoolDevice_allowAppointment", schoolDevice_allowAppointment);
		return mav;
	}
	
	
	/****************************************************************************
	 * @功能：设备文档（化工学院）
	 * @作者：李小龙
	 ****************************************************************************/
	@RequestMapping(value = "/device/editDeviceDocumentRest/{labRoomId}/{deviceNumber}/{deviceName}/{username}/{page}/{id}/{schoolDevice_allowAppointment}", method = RequestMethod.GET)
	public ModelAndView editDeviceDocumentRest(@PathVariable int labRoomId,
			@PathVariable String deviceNumber, @PathVariable String deviceName, @PathVariable String username, 
			@PathVariable int page,@PathVariable int id, @PathVariable String schoolDevice_allowAppointment,Model model,
			@ModelAttribute("selected_labCenter") Integer cid) {
		// 新建ModelAndView对象；
		ModelAndView mav = new ModelAndView();
		LabRoomDevice device = labRoomDeviceService.findLabRoomDeviceByPrimaryKey(id);
		mav.addObject("device", device);
		mav.setViewName("/device/specialAcademy/deviceDocument.jsp");
		
		mav.addObject("labRoomId", labRoomId);
		mav.addObject("deviceName", deviceName);
		mav.addObject("deviceNumber", deviceNumber);
		mav.addObject("username", username);
		mav.addObject("page", page);
		mav.addObject("schoolDevice_allowAppointment", schoolDevice_allowAppointment);
		return mav;
	}
	
	/****************************************************************************
	 * @功能：设备二维码（化工学院）
	 * @作者：李小龙
	 ****************************************************************************/
	@RequestMapping(value = "/device/editDeviceDimensionalCodeRest/{labRoomId}/{deviceNumber}/{deviceName}/{username}/{page}/{id}/{schoolDevice_allowAppointment}", method = RequestMethod.GET)
	public ModelAndView editDeviceDimensionalCodeRest(@PathVariable int labRoomId,
			@PathVariable String deviceNumber, @PathVariable String deviceName, @PathVariable String username, 
			@PathVariable int page,@PathVariable int id,@PathVariable String schoolDevice_allowAppointment, Model model,
			@ModelAttribute("selected_labCenter") Integer cid) {
		// 新建ModelAndView对象；
		ModelAndView mav = new ModelAndView();
		LabRoomDevice device = labRoomDeviceService.findLabRoomDeviceByPrimaryKey(id);
		mav.addObject("device", device);
		mav.setViewName("/device/specialAcademy/dimensionalCode.jsp");
		
		mav.addObject("labRoomId", labRoomId);
		mav.addObject("deviceName", deviceName);
		mav.addObject("deviceNumber", deviceNumber);
		mav.addObject("username", username);
		mav.addObject("page", page);
		mav.addObject("schoolDevice_allowAppointment", schoolDevice_allowAppointment);
		return mav;
	}
	
	/****************************************************************************
	 * @功能：根据设备id查询培训
	 * @作者：李小龙
	 ****************************************************************************/
	@RequestMapping(value = "/device/editDeviceTrainingRest/{labRoomId}/{deviceNumber}/{deviceName}/{username}/{page}/{id}/{schoolDevice_allowAppointment}", method = RequestMethod.GET)
	public ModelAndView editDeviceTrainingRest(@PathVariable int labRoomId,
			@PathVariable String deviceNumber, @PathVariable String deviceName, @PathVariable String username, 
			@PathVariable int page,@PathVariable int id,@PathVariable String schoolDevice_allowAppointment, Model model,
			@ModelAttribute LabRoomDeviceTraining train,
			@ModelAttribute("selected_labCenter") Integer cid) {
		// 新建ModelAndView对象；
		ModelAndView mav = new ModelAndView();
		// 培训查询表单的对象
		mav.addObject("train", train);
		// 设备id对应的设备
		LabRoomDevice device = labRoomDeviceService.findLabRoomDeviceByPrimaryKey(id);
		mav.addObject("device", device);
		// 学期
		List<SchoolTerm> terms = shareService.findAllSchoolTerms();
		mav.addObject("terms", terms);
		// 当前登录人
		User user = shareService.getUser();
		mav.addObject("user", user);
		
		//判断当前登陆人是否为项目立项的审核人或者超级管理员
		String judge=",";
		for(Authority authority:user.getAuthorities()){
			judge = judge + "," + authority.getId() + "," ;
		}
		boolean isEXCENTERDIRECTOR;//是否为非本学院审核人或超级管理员
		if(judge.indexOf(",4,")>-1||judge.indexOf(",11,")>-1){
			isEXCENTERDIRECTOR = true;
		}else isEXCENTERDIRECTOR = false;
		mav.addObject("isEXCENTERDIRECTOR", isEXCENTERDIRECTOR);
		
		// 根据设备id和培训对象查询培训
		List<LabRoomDeviceTraining> trainList = labRoomDeviceService.findLabRoomDeviceTrainingByDeviceId(train,
				id);
		for (LabRoomDeviceTraining trainForTime : trainList) {
			Calendar traintime=trainForTime.getTime();
			Calendar systemtime=Calendar.getInstance();
			if (traintime!=null&&traintime.before(systemtime)) {
				//trainForTime.setCTrainingStatus(cTrainingStatusDAO.findCTrainingStatusByPrimaryKey(3));//如果培训时间早于系统时间，将培训状态设置为“已过期”
				trainForTime.setCTrainingStatus(shareService.getCDictionaryByCategory("c_training_status", "3"));
				labRoomDeviceTrainingDAO.store(trainForTime);
			}
		}

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
		//单独随时培训的学生
		LabRoomDevicePermitUsers student=new LabRoomDevicePermitUsers();
		mav.addObject("student", student);
		//单独培训学生名单
		Set<LabRoomDevicePermitUsers> students =device.getLabRoomDevicePermitUserses();
		List<User> studentsPass=new ArrayList<User>();
		for (LabRoomDevicePermitUsers ss : students) {
			studentsPass.add(ss.getUser());
		}
		mav.addObject("studentsPass", studentsPass);
		// 添加培训表单的培训教师
		List<User> users = shareService.findTheSameCollegeTeacher(user.getSchoolAcademy().getAcademyNumber());
		mav.addObject("users", users);
		
		mav.setViewName("/device/specialAcademy/deviceTraining.jsp");// 统一到集中培训页面
		//if ((device.getCTrainingType()!=null&&device.getCTrainingType().getId()==1)||device.getCTrainingType()==null) {
		//}else mav.setViewName("/device/specialAcademy/deviceTrainingForSingle.jsp");
		
		
		mav.addObject("labRoomId", labRoomId);
		mav.addObject("deviceName", deviceName);
		mav.addObject("deviceNumber", deviceNumber);
		mav.addObject("username", username);
		mav.addObject("schoolDevice_allowAppointment", schoolDevice_allowAppointment);
		mav.addObject("page", page);
		return mav;
	}

	
	/****************************************************************************
	 * @功能：实验室设备管理---设备管理---查看（化工学院）
	 * @作者：贺子龙
	 ****************************************************************************/
	@RequestMapping(value = "/device/viewDeviceInfoRest/{labRoomId}/{deviceNumber}/{deviceName}/{username}/{page}/{id}/{schoolDevice_allowAppointment}", method = RequestMethod.GET)
	public ModelAndView viewDeviceInfoRest(@PathVariable int labRoomId,
			@PathVariable String deviceNumber, @PathVariable String deviceName, @PathVariable String username, 
			@PathVariable int page,@PathVariable int id, @PathVariable String schoolDevice_allowAppointment,Model model,
			@ModelAttribute("selected_labCenter") Integer cid) {
		// 新建ModelAndView对象；
		ModelAndView mav = new ModelAndView();
		// id对应的实验分室设备
		LabRoomDevice device = labRoomDeviceService.findLabRoomDeviceByPrimaryKey(id);
		mav.addObject("device", device);
		mav.setViewName("/device/specialAcademy/viewDeviceInfo.jsp");

		mav.addObject("labRoomId", labRoomId);
		mav.addObject("deviceName", deviceName);
		mav.addObject("deviceNumber", deviceNumber);
		mav.addObject("username", username);
		mav.addObject("schoolDevice_allowAppointment", schoolDevice_allowAppointment);
		mav.addObject("page", page);
		return mav;
	}
	
	/****************************************************************************
	 * @功能：查看培训计划
	 * @作者：贺子龙
	 ****************************************************************************/
	@RequestMapping(value = "/device/viewDeviceTrainingRest/{labRoomId}/{deviceNumber}/{deviceName}/{username}/{page}/{id}/{schoolDevice_allowAppointment}", method = RequestMethod.GET)
	public ModelAndView viewDeviceTrainingRest(@PathVariable int labRoomId,
			@PathVariable String deviceNumber, @PathVariable String deviceName, @PathVariable String username, 
			@PathVariable int page,@PathVariable int id,@PathVariable String schoolDevice_allowAppointment, Model model,
			@ModelAttribute LabRoomDeviceTraining train,
			@ModelAttribute("selected_labCenter") Integer cid) {
		// 新建ModelAndView对象；
		ModelAndView mav = new ModelAndView();
		// 培训查询表单的对象
		mav.addObject("train", train);
		// 设备id对应的设备
		LabRoomDevice device = labRoomDeviceService.findLabRoomDeviceByPrimaryKey(id);
		mav.addObject("device", device);
		// 当前登录人
		User user = shareService.getUser();
		mav.addObject("user", user);
		//判断当前登陆人是否为项目立项的审核人或者超级管理员
		String judge=",";
		for(Authority authority:user.getAuthorities()){
			judge = judge + "," + authority.getId() + "," ;
		}
		boolean isEXCENTERDIRECTOR;//是否为非本学院审核人或超级管理员
		if(judge.indexOf(",4,")>-1||judge.indexOf(",11,")>-1){
			isEXCENTERDIRECTOR = true;
		}else isEXCENTERDIRECTOR = false;
		mav.addObject("isEXCENTERDIRECTOR", isEXCENTERDIRECTOR);
		
		// 根据设备id和培训对象查询培训
		List<LabRoomDeviceTraining> trainList = labRoomDeviceService.findLabRoomDeviceTrainingByDeviceId(train,
				id);
		for (LabRoomDeviceTraining trainForTime : trainList) {
			Calendar traintime=trainForTime.getTime();
			Calendar systemtime=Calendar.getInstance();
			if (traintime!=null&&traintime.before(systemtime)) {
				//trainForTime.setCTrainingStatus(cTrainingStatusDAO.findCTrainingStatusByPrimaryKey(3));//如果培训时间早于系统时间，将培训状态设置为“已过期”
				trainForTime.setCTrainingStatus(shareService.getCDictionaryByCategory("c_training_status", "3"));//如果培训时间早于系统时间，将培训状态设置为“已过期”
				labRoomDeviceTrainingDAO.store(trainForTime);
			}
		}
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
		
		mav.addObject("labRoomDeviceTrainingPeople", new LabRoomDeviceTrainingPeople());
		mav.setViewName("/device/specialAcademy/viewDeviceTraining.jsp");
		
		mav.addObject("labRoomId", labRoomId);
		mav.addObject("deviceName", deviceName);
		mav.addObject("deviceNumber", deviceNumber);
		mav.addObject("username", username);
		mav.addObject("schoolDevice_allowAppointment", schoolDevice_allowAppointment);
		mav.addObject("page", page);
		return mav;
	}
	
	/****************************************************************************
	 * @功能：实验室设备管理---设备管理---查看
	 * @作者：李小龙
	 ****************************************************************************/
	@RequestMapping(value = "/device/viewDeviceSettingRest/{labRoomId}/{deviceNumber}/{deviceName}/{username}/{page}/{id}/{schoolDevice_allowAppointment}", method = RequestMethod.GET)
	public ModelAndView viewDeviceSettingRest(@PathVariable int labRoomId,
			@PathVariable String deviceNumber, @PathVariable String deviceName, @PathVariable String username,
			@PathVariable int page,@PathVariable int id,@PathVariable String schoolDevice_allowAppointment, Model model,
			@ModelAttribute("selected_labCenter") Integer cid) {
		// 新建ModelAndView对象；
		ModelAndView mav = new ModelAndView();
		// id对应的实验分室设备
		LabRoomDevice device = labRoomDeviceService.findLabRoomDeviceByPrimaryKey(id);
		mav.addObject("device", device);
		mav.setViewName("/device/lab_room_device_access/viewDeviceSetting.jsp");
		
		mav.addObject("labRoomId", labRoomId);
		mav.addObject("deviceName", deviceName);
		mav.addObject("deviceNumber", deviceNumber);
		mav.addObject("username", username);
		mav.addObject("schoolDevice_allowAppointment",schoolDevice_allowAppointment);
		mav.addObject("page", page);
		return mav;
	}
	
	/****************************************************************************
	 * @功能：实验室设备管理---使用情况---查看
	 * @作者：贺子龙
	 ****************************************************************************/
	@RequestMapping(value = "/device/viewDeviceReservationRest/{labRoomId}/{deviceNumber}/{deviceName}/{username}/{page}/{id}/{currpage}/{schoolDevice_allowAppointment}", method = RequestMethod.GET)
	public ModelAndView viewDeviceReservationRest(@PathVariable int labRoomId,
			@PathVariable String deviceNumber, @PathVariable String deviceName, @PathVariable String username,
			@PathVariable int page,@PathVariable int id, @PathVariable int currpage, @PathVariable String schoolDevice_allowAppointment,Model model,
			HttpServletRequest request, @ModelAttribute LabRoomDeviceReservation reservation, @ModelAttribute("selected_labCenter") Integer cid) {
		// 新建ModelAndView对象；
		ModelAndView mav = new ModelAndView();
		// id对应的实验分室设备
		LabRoomDevice device = labRoomDeviceService.findLabRoomDeviceByPrimaryKey(id);
		mav.addObject("reservation", reservation);
		// 学期
		List<SchoolTerm> terms = shareService.findAllSchoolTerms();
		mav.addObject("terms", terms);
		// 查询出所有的设备设备预约记录
		int totalRecords = labRoomDeviceService.countLabRoomDeviceReservationByDeviceNumberAndCid(reservation, request, device.getSchoolDevice().getDeviceNumber(), 0, null);
		int pageSize = 20;
		// 分页信息
		Map<String, Integer> pageModel = shareService.getPage(currpage, pageSize, totalRecords);
		// 根据分页信息查询出来的记录
		List<LabRoomDeviceReservation> reservationList = labRoomDeviceService.findLabRoomDeviceReservationByDeviceNumberAndCid(reservation, request, device.getSchoolDevice().getDeviceNumber(), currpage, pageSize, 0, null);
		mav.addObject("reservationList", reservationList);
		
		// 找出设备预约对应的所有的申请人、指导老师、设备管理员、实验室
		Map<String, String> reserveUsers = new HashMap<String, String>();
		Map<String, String> teachers = new HashMap<String, String>();
		Map<String, String> manageUsers = new HashMap<String, String>();
		for (LabRoomDeviceReservation labRoomDeviceReservation : reservationList) {
			if (labRoomDeviceReservation.getUserByTeacher() != null) {
				reserveUsers.put(labRoomDeviceReservation.getUserByReserveUser().getUsername(), labRoomDeviceReservation.getUserByReserveUser().getCname());
			}
			if (labRoomDeviceReservation.getUserByTeacher() != null) {
				teachers.put(labRoomDeviceReservation.getUserByTeacher().getUsername(), labRoomDeviceReservation.getUserByTeacher().getCname());
			}
			if (labRoomDeviceReservation.getLabRoomDevice().getUser() != null) {
				manageUsers.put(labRoomDeviceReservation.getLabRoomDevice().getUser().getUsername(), labRoomDeviceReservation.getLabRoomDevice().getUser().getCname());
			}
		}
		mav.addObject("reserveUsers", reserveUsers);
		mav.addObject("teachers", teachers);
		mav.addObject("manageUsers", manageUsers);
		
		
		mav.addObject("pageModel", pageModel);
		mav.addObject("totalRecords", totalRecords);
		mav.addObject("currpage", currpage);
		mav.addObject("pageSize", pageSize);
		
		mav.addObject("device", device);
		mav.setViewName("/device/specialAcademy/viewDeviceReservation.jsp");
		
		mav.addObject("labRoomId", labRoomId);
		mav.addObject("deviceName", deviceName);
		mav.addObject("deviceNumber", deviceNumber);
		mav.addObject("username", username);
		mav.addObject("page", page);
		mav.addObject("schoolDevice_allowAppointment", schoolDevice_allowAppointment);
		return mav;
	}
	
	/****************************************************************************
	 * @功能：实验室设备管理---使用情况---查看
	 * @作者：贺子龙
	 ****************************************************************************/
	@RequestMapping(value = "/device/viewDeviceReservationRestAll/{labRoomId}/{deviceNumber}/{deviceName}/{username}/{page}/{id}/{currpage}/{schoolDevice_allowAppointment}", method = RequestMethod.GET)
	public ModelAndView viewDeviceReservationRestAll(@PathVariable int labRoomId,
			@PathVariable String deviceNumber, @PathVariable String deviceName, @PathVariable String username,
			@PathVariable int page,@PathVariable int id, @PathVariable int currpage, @PathVariable String schoolDevice_allowAppointment,Model model,
			HttpServletRequest request, @ModelAttribute LabRoomDeviceReservation reservation, @ModelAttribute("selected_labCenter") Integer cid) {
		// 新建ModelAndView对象；
		ModelAndView mav = new ModelAndView();
		// id对应的实验分室设备
		LabRoomDevice device = labRoomDeviceService.findLabRoomDeviceByPrimaryKey(id);
		mav.addObject("reservation", reservation);
		// 学期
		List<SchoolTerm> terms = shareService.findAllSchoolTerms();
		mav.addObject("terms", terms);
		// 查询出所有的设备设备预约记录
		List<LabRoomDeviceReservation> reservationLisAll = labRoomDeviceService.findLabRoomDeviceReservationByDeviceNumberAndCid(reservation, request, device.getSchoolDevice().getDeviceNumber(), 1, -1, 2, null);
		int totalRecords = labRoomDeviceService.countLabRoomDeviceReservationByDeviceNumberAndCid(reservation, request, device.getSchoolDevice().getDeviceNumber(), 2, null);
		double totalHours = 0.0;
		if (totalRecords>0) {
			for (LabRoomDeviceReservation labRoomDeviceReservation : reservationLisAll) {
				totalHours += labRoomDeviceReservation.getReserveHours().doubleValue();
			}
		}
		BigDecimal totalHourBD = new BigDecimal(totalHours);
		mav.addObject("totalHourBD", totalHourBD);
		int pageSize = 50;
		// 分页信息
		Map<String, Integer> pageModel = shareService.getPage(currpage, pageSize, totalRecords);
		// 根据分页信息查询出来的记录
		List<LabRoomDeviceReservation> reservationList = labRoomDeviceService.findLabRoomDeviceReservationByDeviceNumberAndCid(reservation, request, device.getSchoolDevice().getDeviceNumber(), currpage, pageSize, 2, null);
		mav.addObject("reservationList", reservationList);
		
		// 找出设备预约对应的所有的申请人、指导老师、设备管理员、实验室
		Map<String, String> reserveUsers = new HashMap<String, String>();
		Map<String, String> teachers = new HashMap<String, String>();
		Map<String, String> manageUsers = new HashMap<String, String>();
		for (LabRoomDeviceReservation labRoomDeviceReservation : reservationList) {
			if (labRoomDeviceReservation.getUserByTeacher() != null) {
				reserveUsers.put(labRoomDeviceReservation.getUserByReserveUser().getUsername(), labRoomDeviceReservation.getUserByReserveUser().getCname());
			}
			if (labRoomDeviceReservation.getUserByTeacher() != null) {
				teachers.put(labRoomDeviceReservation.getUserByTeacher().getUsername(), labRoomDeviceReservation.getUserByTeacher().getCname());
			}
			if (labRoomDeviceReservation.getLabRoomDevice().getUser() != null) {
				manageUsers.put(labRoomDeviceReservation.getLabRoomDevice().getUser().getUsername(), labRoomDeviceReservation.getLabRoomDevice().getUser().getCname());
			}
		}
		mav.addObject("reserveUsers", reserveUsers);
		mav.addObject("teachers", teachers);
		mav.addObject("manageUsers", manageUsers);
		
		
		mav.addObject("pageModel", pageModel);
		mav.addObject("totalRecords", totalRecords);
		mav.addObject("currpage", currpage);
		mav.addObject("pageSize", pageSize);
		
		mav.addObject("device", device);
		mav.setViewName("/device/specialAcademy/viewDeviceReservationRestAll.jsp");
		
		mav.addObject("labRoomId", labRoomId);
		mav.addObject("deviceName", deviceName);
		mav.addObject("deviceNumber", deviceNumber);
		mav.addObject("username", username);
		mav.addObject("page", page);
		mav.addObject("schoolDevice_allowAppointment", schoolDevice_allowAppointment);
		return mav;
	}
	
	/****************************************************************************
	 * @功能：设备图片（化工学院）--查看
	 * @作者：贺子龙
	 ****************************************************************************/
	@RequestMapping(value = "/device/viewDeviceImageRest/{labRoomId}/{deviceNumber}/{deviceName}/{username}/{page}/{id}/{schoolDevice_allowAppointment}", method = RequestMethod.GET)
	public ModelAndView viewDeviceImageRest(@PathVariable int labRoomId,
			@PathVariable String deviceNumber, @PathVariable String deviceName, @PathVariable String username, 
			@PathVariable int page,@PathVariable int id, @PathVariable String schoolDevice_allowAppointment,Model model,
			@ModelAttribute("selected_labCenter") Integer cid) {
		// 新建ModelAndView对象；
		ModelAndView mav = new ModelAndView();
		LabRoomDevice device = labRoomDeviceService.findLabRoomDeviceByPrimaryKey(id);
		mav.addObject("device", device);
		mav.setViewName("/device/specialAcademy/viewDeviceImage.jsp");
		
		mav.addObject("labRoomId", labRoomId);
		mav.addObject("deviceName", deviceName);
		mav.addObject("deviceNumber", deviceNumber);
		mav.addObject("username", username);
		mav.addObject("schoolDevice_allowAppointment", schoolDevice_allowAppointment);
		mav.addObject("page", page);
		return mav;
	}
	
	/****************************************************************************
	 * @功能：设备视频（化工学院）--查看
	 * @作者：贺子龙
	 ****************************************************************************/
	@RequestMapping(value = "/device/viewDeviceVideoRest/{labRoomId}/{deviceNumber}/{deviceName}/{username}/{page}/{id}/{schoolDevice_allowAppointment}", method = RequestMethod.GET)
	public ModelAndView viewDeviceVideoRest(@PathVariable int labRoomId,
			@PathVariable String deviceNumber, @PathVariable String deviceName, @PathVariable String username, 
			@PathVariable int page,@PathVariable int id, @PathVariable String schoolDevice_allowAppointment,Model model,
			@ModelAttribute("selected_labCenter") Integer cid) {
		// 新建ModelAndView对象；
		ModelAndView mav = new ModelAndView();
		LabRoomDevice device = labRoomDeviceService.findLabRoomDeviceByPrimaryKey(id);
		mav.addObject("device", device);
		mav.setViewName("/device/specialAcademy/viewDeviceVideo.jsp");
		
		mav.addObject("labRoomId", labRoomId);
		mav.addObject("deviceName", deviceName);
		mav.addObject("deviceNumber", deviceNumber);
		mav.addObject("username", username);
		mav.addObject("schoolDevice_allowAppointment", schoolDevice_allowAppointment);
		mav.addObject("page", page);
		return mav;
	}

	/****************************************************************************
	 * @功能：设备文档（化工学院）--查看
	 * @作者：李小龙
	 ****************************************************************************/
	@RequestMapping(value = "/device/viewDeviceDocumentRest/{labRoomId}/{deviceNumber}/{deviceName}/{username}/{page}/{id}/{schoolDevice_allowAppointment}", method = RequestMethod.GET)
	public ModelAndView viewDeviceDocumentRest(@PathVariable int labRoomId,
			@PathVariable String deviceNumber, @PathVariable String deviceName, @PathVariable String username, 
			@PathVariable int page,@PathVariable int id,@PathVariable String schoolDevice_allowAppointment, Model model,
			@ModelAttribute("selected_labCenter") Integer cid) {
		// 新建ModelAndView对象；
		ModelAndView mav = new ModelAndView();
		LabRoomDevice device = labRoomDeviceService.findLabRoomDeviceByPrimaryKey(id);
		mav.addObject("device", device);
		mav.setViewName("/device/specialAcademy/viewDeviceDocument.jsp");
		
		mav.addObject("labRoomId", labRoomId);
		mav.addObject("deviceName", deviceName);
		mav.addObject("deviceNumber", deviceNumber);
		mav.addObject("username", username);
		mav.addObject("schoolDevice_allowAppointment", schoolDevice_allowAppointment);
		mav.addObject("page", page);
		return mav;
	}
	
	/****************************************************************************
	 * @功能：设备二维码（化工学院）--查看
	 * @作者：李小龙
	 ****************************************************************************/
	@RequestMapping(value = "/device/viewDimensionalCodeRest/{labRoomId}/{deviceNumber}/{deviceName}/{username}/{page}/{id}/{schoolDevice_allowAppointment}", method = RequestMethod.GET)
	public ModelAndView viewDimensionalCodeRest(@PathVariable int labRoomId,
			@PathVariable String deviceNumber, @PathVariable String deviceName, @PathVariable String username,
			@PathVariable int page,@PathVariable int id, @PathVariable String schoolDevice_allowAppointment,Model model,
			@ModelAttribute("selected_labCenter") Integer cid) {
		// 新建ModelAndView对象；
		ModelAndView mav = new ModelAndView();
		LabRoomDevice device = labRoomDeviceService.findLabRoomDeviceByPrimaryKey(id);
		mav.addObject("device", device);
		mav.setViewName("/device/specialAcademy/viewDimensionalCode.jsp");
		
		mav.addObject("labRoomId", labRoomId);
		mav.addObject("deviceName", deviceName);
		mav.addObject("deviceNumber", deviceNumber);
		mav.addObject("username", username);
		mav.addObject("schoolDevice_allowAppointment", schoolDevice_allowAppointment);
		mav.addObject("page", page);
		return mav;
	}
	
	
	/****************************************************************************
	 * @throws ParseException 
	 * @throws UnsupportedEncodingException 
	 * @功能：保存实验设备培训
	 * @作者：李小龙
	 ****************************************************************************/
	@RequestMapping(value = "/device/saveLabRoomDeviceTrainingRest/{labRoomId}/{deviceNumber}/{deviceName}/{username}/{page}/{time}/{address}/{content}/{usernameTeacher}/{number}/{id}/{schoolDevice_allowAppointment}",
			method = RequestMethod.GET)
	public ModelAndView saveLabRoomDeviceTrainingRest(@PathVariable int labRoomId,
			@PathVariable String deviceNumber, @PathVariable String deviceName,@PathVariable String username, @PathVariable int page,
			@PathVariable String time, @PathVariable String address, @PathVariable String content, 
			@PathVariable String usernameTeacher, @PathVariable int number, @PathVariable int id,@PathVariable String schoolDevice_allowAppointment,
			Model model,@ModelAttribute("selected_labCenter") Integer cid) throws ParseException, UnsupportedEncodingException {
		// 新建ModelAndView对象；
		ModelAndView mav = new ModelAndView();
		LabRoomDeviceTraining train=new LabRoomDeviceTraining();
		// id对应的实验分室设备
		LabRoomDevice device = labRoomDeviceService.findLabRoomDeviceByPrimaryKey(id);
		train.setLabRoomDevice(device);
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date date =sdf.parse(time);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		// 当前@时间所属学期为培训所属学期
		SchoolTerm term = shareService.getBelongsSchoolTerm(calendar);
		train.setSchoolTerm(term);
		train.setTime(calendar);
		train.setAddress(address);
		train.setContent(content);
		train.setNumber(number);
		// 状态设置为待培训
		//CTrainingStatus status = cTrainingStatusDAO.findCTrainingStatusByPrimaryKey(1);
		CDictionary status = shareService.getCDictionaryByCategory("c_training_status", "1");
		train.setCTrainingStatus(status);
		// 参加人数默认为0
		train.setJoinNumber(0);
		User user=userDAO.findUserByPrimaryKey(usernameTeacher);
		if (user != null && user.getUsername() != null
				&& !user.getUsername().equals("")) {
			train.setUser(user);
		} else {
			train.setUser(null);
		}
		labRoomDeviceService.saveLabRoomDeviceTraining(train);
		// 中心所属学院
		/*String academy = labCenterDAO.findLabCenterByPrimaryKey(cid).getSchoolAcademy().getAcademyNumber();
		boolean flag = specialAcademy.contains(academy);
		if (flag) {
			mav.setViewName("redirect:/device/editDeviceTrainingRest/" + labRoomId+"/"+ deviceNumber + "/" + URLEncoder.encode(deviceName,"utf-8") +"/" + username + "/"+page+"/" + id);
			
		} else {
			mav.setViewName("redirect:/device/editDeviceReservationInfo?id=" + id);
		}*/
		mav.setViewName("redirect:/device/listLabRoomDevice");
		mav.addObject("labRoomId", labRoomId);
		mav.addObject("deviceName", deviceName);
		mav.addObject("deviceNumber", deviceNumber);
		mav.addObject("username", username);
		mav.addObject("schoolDevice_allowAppointment", schoolDevice_allowAppointment);
		mav.addObject("page", page);
		return mav;
	}
	
	/****************************************************************************
	 * @功能：根据设备id查询培训
	 * @作者：李小龙
	 ****************************************************************************/
	@RequestMapping(value = "/device/findTrainingPeopleByTrainIdRest/{labRoomId}/{deviceNumber}/{deviceName}/{username}/{page}/{id}/{tag}/{toChangeAudit}/{schoolDevice_allowAppointment}", method = RequestMethod.GET)
	public ModelAndView findTrainingPeopleByTrainIdRest(@PathVariable int labRoomId,
			@PathVariable String deviceNumber, @PathVariable String deviceName, @PathVariable String username, 
			@PathVariable int page,@PathVariable int id, @PathVariable int tag,@PathVariable int toChangeAudit,@PathVariable String schoolDevice_allowAppointment,Model model,
			@ModelAttribute("selected_labCenter") Integer cid) {
		// 新建ModelAndView对象；
		ModelAndView mav = new ModelAndView();
		// id对应的培训
		LabRoomDeviceTraining train = labRoomDeviceTrainingDAO.findLabRoomDeviceTrainingByPrimaryKey(id);
		mav.addObject("train", train);
		// 培训所属的设备
		mav.addObject("device", train.getLabRoomDevice());
		List<LabRoomDeviceTrainingPeople> peoples = labRoomDeviceService.findTrainingPeoplesByTrainingId(id);
		mav.addObject("peoples", peoples);
		List<LabRoomDeviceTrainingPeople> passPeoples = labRoomDeviceService.findTrainingPassPeoplesByTrainingId(id);
		mav.addObject("passPeoples", passPeoples);
		// 培训结果
		//Set<CTrainingResult> results = cTrainingResultDAO.findAllCTrainingResults();
		List<CDictionary> results = shareService.getCDictionaryData("c_training_result");
		mav.addObject("results", results);
		mav.setViewName("/device/specialAcademy/peopleList.jsp");
		mav.addObject("tag", tag);//1  edit  2  view
		mav.addObject("toChangeAudit", toChangeAudit);//1  edit  2  view
		
		
		mav.addObject("labRoomId", labRoomId);
		mav.addObject("deviceName", deviceName);
		mav.addObject("deviceNumber", deviceNumber);
		mav.addObject("username", username);
		mav.addObject("page", page);
		mav.addObject("schoolDevice_allowAppointment", schoolDevice_allowAppointment);
		return mav;
	}
	
	
	/****************************************************************************
	 * @throws UnsupportedEncodingException 
	 * @功能：保存实验设备培训人
	 * @作者：李小龙
	 ****************************************************************************/
	@RequestMapping(value = "/device/joinTrainingRest/{labRoomId}/{deviceNumber}/{deviceName}/{username}/{page}/{trainingId}/{telephone}/{eMail}/{schoolDevice_allowAppointment}", method = RequestMethod.GET)
	public ModelAndView joinTrainingRest(@PathVariable int labRoomId,
			@PathVariable String deviceNumber, @PathVariable String deviceName, @PathVariable String username, 
			@PathVariable int page,@PathVariable int trainingId,
			@PathVariable String telephone, @PathVariable String eMail,@PathVariable String schoolDevice_allowAppointment, Model model, @ModelAttribute("selected_labCenter") Integer cid) throws UnsupportedEncodingException  {
		// 替换邮箱中的特殊字符
		eMail=shareService.replaceString(eMail);
		// 新建ModelAndView对象；
		ModelAndView mav = new ModelAndView();
		// id对应的培训
		LabRoomDeviceTraining train = labRoomDeviceTrainingDAO.findLabRoomDeviceTrainingByPrimaryKey(trainingId);
		User user = shareService.getUser();
		LabRoomDeviceTrainingPeople people = new LabRoomDeviceTrainingPeople();
		people.setLabRoomDeviceTraining(train);
		people.setUser(user);
		people.setTelephone(telephone);
		people.seteMail(eMail);
		labRoomDeviceTrainingPeopleDAO.store(people);
		Set<LabRoomDeviceTrainingPeople> peoples = train.getLabRoomDeviceTrainingPeoples();
		train.setJoinNumber(peoples.size());
		labRoomDeviceTrainingDAO.store(train);
		// 中心所属学院
		//String academy = labCenterDAO.findLabCenterByPrimaryKey(cid).getSchoolAcademy().getAcademyNumber();
		/*boolean flag = specialAcademy.contains(academy);
		if (flag) {
			mav.setViewName("redirect:/device/viewDeviceTrainingRest/" + labRoomId+"/"+ deviceNumber + "/" + URLEncoder.encode(deviceName,"utf-8") +"/" + username + "/"+page+"/" + train.getLabRoomDevice().getId());
		} else {
			mav.setViewName("redirect:/device/findTrainingPeopleByDeviceIdAndTrainId?deviceId="
					+ train.getLabRoomDevice().getId() + "&id=" + train.getId());
		}*/
		Message message= new Message();
		Calendar date=Calendar.getInstance();
		message.setSendUser(shareService.getUserDetail().getCname());
		message.setSendCparty(shareService.getUserDetail().getSchoolAcademy().getAcademyName());
		message.setCond(0);
		message.setTitle("培训人员增加");
		String content="培训人员增加";
		content+="<a  href='/xidlims/device/editDeviceTrainingRest/"+ train.getLabRoomDevice().getLabRoom().getId() + "/"+ "-1" + "/" + "-1" +"/-1/" +"1/"+ train.getLabRoomDevice().getId()+"/-1"+"'";
		 
		content+=" >点击查看</a>";
//		message.setContent("申请成功，等待审核<a  href='/xidlims/operation/viewOperationItem?operationItemId=44956&&flag=1&status=2'>点击查看</a>");
		message.setContent(content);
		message.setMessageState(CommonConstantInterface.INT_Flag_ZERO);
		message.setCreateTime(date);
		message.setUsername(train.getUser().getUsername());
		message=messageDAO.store(message);
		mav.setViewName("redirect:/device/viewDeviceTrainingRest/" + labRoomId+"/"+ deviceNumber + "/" + URLEncoder.encode(deviceName,"utf-8") +"/" + username + "/"+page+"/" + train.getLabRoomDevice().getId()+"/-1");
		mav.addObject("labRoomId", labRoomId);
		mav.addObject("deviceName", deviceName);
		mav.addObject("deviceNumber", deviceNumber);
		mav.addObject("username", username);
		mav.addObject("page", page);
		mav.addObject("schoolDevice_allowAppointment", schoolDevice_allowAppointment);
		return mav;
	}
	
	/****************************************************************************
	 * @throws UnsupportedEncodingException 
	 * @功能：删除培训
	 * @作者：贺子龙
	 ****************************************************************************/
	@RequestMapping(value = "/device/deleteTrainingRest/{labRoomId}/{deviceNumber}/{deviceName}/{username}/{page}/{id}/{schoolDevice_allowAppointment}", method = RequestMethod.GET)
	public ModelAndView deleteTrainingRest(@PathVariable int labRoomId,
			@PathVariable String deviceNumber, @PathVariable String deviceName, @PathVariable String username, 
			@PathVariable int page,@PathVariable int id,@PathVariable String schoolDevice_allowAppointment,
			Model model, @ModelAttribute("selected_labCenter") Integer cid) throws UnsupportedEncodingException  {
		// 新建ModelAndView对象；
		ModelAndView mav = new ModelAndView();
		// id对应的培训
		LabRoomDeviceTraining train = labRoomDeviceTrainingDAO.findLabRoomDeviceTrainingByPrimaryKey(id);
		labRoomDeviceTrainingDAO.remove(train);
		// 中心所属学院
		//String academy = labCenterDAO.findLabCenterByPrimaryKey(cid).getSchoolAcademy().getAcademyNumber();
		//boolean flag = specialAcademy.contains(academy);
		//if (flag) {
			mav.setViewName("redirect:/device/editDeviceTrainingRest/" + labRoomId+"/"+ deviceNumber + "/" + URLEncoder.encode(deviceName,"utf-8") +"/" + username + "/"+page+"/" + train.getLabRoomDevice().getId()+"/"+schoolDevice_allowAppointment);
		//} else {
			//mav.setViewName("redirect:/device/findTrainingPeopleByDeviceIdAndTrainId?deviceId="
			//		+ train.getLabRoomDevice().getId() + "&id=" + train.getId());
		//}
		
		mav.addObject("labRoomId", labRoomId);
		mav.addObject("deviceName", deviceName);
		mav.addObject("deviceNumber", deviceNumber);
		mav.addObject("username", username);
		mav.addObject("page", page);
		mav.addObject("schoolDevice_allowAppointment", schoolDevice_allowAppointment);
		return mav;
	}
	
	/****************************************************************************
	 * @throws UnsupportedEncodingException 
	 * @功能：取消培训预约
	 * @作者：贺子龙
	 ****************************************************************************/
	@RequestMapping(value = "/device/cancleTrainingRest/{labRoomId}/{deviceNumber}/{deviceName}/{username}/{page}/{id}/{schoolDevice_allowAppointment}", method = RequestMethod.GET)
	public ModelAndView cancleTrainingRest(@PathVariable int labRoomId,
			@PathVariable String deviceNumber, @PathVariable String deviceName, @PathVariable String username,
			@PathVariable int page,@PathVariable int id,@PathVariable String schoolDevice_allowAppointment,
			Model model, @ModelAttribute("selected_labCenter") Integer cid) throws UnsupportedEncodingException  {
		// 新建ModelAndView对象；
		ModelAndView mav = new ModelAndView();
		// id对应的培训
		LabRoomDeviceTraining train = labRoomDeviceTrainingDAO.findLabRoomDeviceTrainingByPrimaryKey(id);
		// 取消培训预约
		labRoomDeviceService.cancleTraining(id);
		Set<LabRoomDeviceTrainingPeople> peoples = train.getLabRoomDeviceTrainingPeoples();
		train.setJoinNumber(peoples.size());
		labRoomDeviceTrainingDAO.store(train);
		// 中心所属学院
		/*String academy = labCenterDAO.findLabCenterByPrimaryKey(cid).getSchoolAcademy().getAcademyNumber();
		boolean flag = specialAcademy.contains(academy);
		if (flag) {
			mav.setViewName("redirect:/device/viewDeviceTrainingRest/" + labRoomId+"/"+ deviceNumber + "/" + URLEncoder.encode(deviceName,"utf-8") +"/" + username + "/"+page+"/" + train.getLabRoomDevice().getId());
		} else {
			mav.setViewName("redirect:/device/findTrainingPeopleByDeviceIdAndTrainId?deviceId="
					+ train.getLabRoomDevice().getId() + "&id=" + train.getId());
		}*/
		mav.setViewName("redirect:/device/viewDeviceTrainingRest/" + labRoomId+"/"+ deviceNumber + "/" + URLEncoder.encode(deviceName,"utf-8") +"/" + username + "/"+page+"/" + train.getLabRoomDevice().getId()+"/"+schoolDevice_allowAppointment);
		
		mav.addObject("labRoomId", labRoomId);
		mav.addObject("deviceName", deviceName);
		mav.addObject("deviceNumber", deviceNumber);
		mav.addObject("username", username);
		mav.addObject("page", page);
		mav.addObject("schoolDevice_allowAppointment", schoolDevice_allowAppointment);
		return mav;
	}
	
	/****************************************************************************
	 * @throws UnsupportedEncodingException 
	 * @功能：删除设备图片
	 * @作者：李小龙
	 ****************************************************************************/
	@RequestMapping(value = "/device/deleteDeviceDocumentRest/{labRoomId}/{deviceNumber}/{deviceName}/{username}/{page}/{id}/{schoolDevice_allowAppointment}", method = RequestMethod.GET)
	public ModelAndView deleteDeviceDocumentRest(@PathVariable int labRoomId,
			@PathVariable String deviceNumber, @PathVariable String deviceName, @PathVariable String username, 
			@PathVariable int page,@PathVariable int id, @PathVariable String schoolDevice_allowAppointment,Model model,
			@ModelAttribute("selected_labCenter") Integer cid) throws UnsupportedEncodingException  {
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
		//String academy = labCenterDAO.findLabCenterByPrimaryKey(cid).getSchoolAcademy().getAcademyNumber();
	
			if (type == 1) {// 图片
				mav.setViewName("redirect:/device/editDeviceImageRest/" + labRoomId+"/"+ deviceNumber + "/" + URLEncoder.encode(deviceName,"utf-8") +"/" + username + "/"+page+"/" +idkey+"/"+schoolDevice_allowAppointment);
			} else {// 文档
				//mav.setViewName("redirect:/device/deviceDocument?deviceId=" + idkey);
				mav.setViewName("redirect:/device/editDeviceDocumentRest/" + labRoomId+"/"+ deviceNumber + "/" + URLEncoder.encode(deviceName,"utf-8") +"/" + username + "/"+page+"/" +idkey+"/"+schoolDevice_allowAppointment);
			}
		
		
		
		mav.addObject("labRoomId", labRoomId);
		mav.addObject("deviceName", deviceName);
		mav.addObject("deviceNumber", deviceNumber);
		mav.addObject("username", username);
		mav.addObject("page", page);
		mav.addObject("schoolDevice_allowAppointment", schoolDevice_allowAppointment);
		return mav;
	}
	
	
	/****************************************************************************
	 * @throws UnsupportedEncodingException 
	 * @功能：删除设备视频
	 * @作者：李小龙
	 ****************************************************************************/
	@RequestMapping(value = "/device/deleteLabRoomVideoRest/{labRoomId}/{deviceNumber}/{deviceName}/{username}/{page}/{id}/{schoolDevice_allowAppointment}", method = RequestMethod.GET)
	public ModelAndView deleteLabRoomVideoRest(@PathVariable int labRoomId,
			@PathVariable String deviceNumber, @PathVariable String deviceName, @PathVariable String username, 
			@PathVariable int page,@PathVariable int id, @PathVariable String schoolDevice_allowAppointment,Model model,
			@ModelAttribute("selected_labCenter") Integer cid) throws UnsupportedEncodingException  {
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
		//String academy = labCenterDAO.findLabCenterByPrimaryKey(cid).getSchoolAcademy().getAcademyNumber();
		// 根据学院编号判断是否为配置文件配置的特殊学院（化工学院）
		//boolean flag = specialAcademy.contains(academy);
		/*if (flag) {
			
		} else {
			mav.setViewName("redirect:/device/editDeviceReservationInfo?id=" + idkey);
		}*/
		mav.setViewName("redirect:/device/editDeviceVideoRest/" + labRoomId+"/"+ deviceNumber + "/" + URLEncoder.encode(deviceName,"utf-8") +"/" + username + "/"+page+"/" +idkey+"/"+schoolDevice_allowAppointment);

		mav.addObject("labRoomId", labRoomId);
		mav.addObject("deviceName", deviceName);
		mav.addObject("deviceNumber", deviceNumber);
		mav.addObject("username", username);
		mav.addObject("page", page);
		mav.addObject("schoolDevice_allowAppointment", schoolDevice_allowAppointment);
		return mav;
	}
	/****************************************************************************
	 * @throws UnsupportedEncodingException 
	 * @功能：实验室设备管理---设备管理---保存实验设备信息
	 * @作者：李小龙
	 ****************************************************************************/
	@RequestMapping(value = "/device/saveDeviceInfoRest/{labRoomId}/{deviceNumber}/{deviceName}/{username}/{page}/" +
			"{usernameManager}/{status}/{type}/{charge}/{price}/{functionOfDevice}/{indicators}/{managerTelephone}/{managerMail}" +
			"/{managerOffice}/{auditTimeLimit}/{id}/{schoolDevice_allowAppointment}",
			method = RequestMethod.GET)
	public ModelAndView saveDeviceInfoRest(@PathVariable int labRoomId,
			@PathVariable String deviceNumber, @PathVariable String deviceName, 
			@PathVariable String username,@PathVariable int page,
			@PathVariable String usernameManager, @PathVariable int status,@PathVariable int type, @PathVariable int charge, 
			@PathVariable Double price, @PathVariable String functionOfDevice, @PathVariable String indicators,
			@PathVariable String managerTelephone,@PathVariable String managerMail, @PathVariable String managerOffice, @PathVariable int auditTimeLimit,@PathVariable int id,@PathVariable String schoolDevice_allowAppointment,
			Model model,@ModelAttribute("selected_labCenter") Integer cid) throws UnsupportedEncodingException {
		//将传来的特殊字符还原
		//1. +  URL 中+号表示空格 %2B
		//2. 空格 URL中的空格可以用+号或者编码 %20
		//3. /  分隔目录和子目录 %2F 
		//4. ?  分隔实际的 URL 和参数 %3F 
		//5. % 指定特殊字符 %25 
		//6. # 表示书签 %23 
		//7. & URL 中指定的参数间的分隔符 %26 
		//8. = URL 中指定参数的值 %3D 
		
		functionOfDevice=shareService.replaceString(functionOfDevice);
		indicators=shareService.replaceString(indicators);
		
		
		// 新建ModelAndView对象；
		ModelAndView mav = new ModelAndView();
		// id对应的实验室设备
		LabRoomDevice d = labRoomDeviceService.findLabRoomDeviceByPrimaryKey(id);
		// 设备管理员
		User user = new User();
		if (usernameManager!="-1") {
			
			user = userDAO.findUserByPrimaryKey(usernameManager);
			d.setUser(user);
		}
		// 管理员电话
		if (!managerTelephone.equals("-1")) {
			d.setManagerTelephone(managerTelephone);
		}else {
			d.setManagerTelephone(null);
		}
		// 管理员邮箱
		if (!managerMail.equals("-1")) {
			d.setManagerMail(managerMail);
		}else {
			d.setManagerMail(null);
		}
		// 管理员办公室
		if (!managerOffice.equals("-1")) {
			d.setManagerOffice(managerOffice);
		}else {
			d.setManagerOffice(null);
		}
		// 设备状态
		//d.setCDeviceStatus(cDeviceStatusDAO.findCDeviceStatusByPrimaryKey(status));
		d.setCDictionaryByDeviceStatus(cDictionaryService.finCDictionaryByPrimarykey(status));
		// 预约审核时间
		if (auditTimeLimit!=-1) {
			d.setAuditTimeLimit(auditTimeLimit);
		}else {
			d.setAuditTimeLimit(null);
		}
		// 所属类型
		//d.setCDeviceType(cDeviceTypeDAO.findCDeviceTypeByPrimaryKey(type));
		d.setCDictionaryByDeviceType(cDictionaryService.finCDictionaryByPrimarykey(type));
		// 收费标准
		//d.setCDeviceCharge(cDeviceChargeDAO.findCDeviceChargeByPrimaryKey(charge));
		d.setCDictionaryByDeviceCharge(cDictionaryService.finCDictionaryByPrimarykey(charge));
		// 技术指标
		if (!indicators.equals("-1")) {
			d.setIndicators(indicators);
		}else {
			d.setIndicators(null);
		}
		// 价格
		if (price!=null) {
			
			java.math.BigDecimal bdprice = new java.math.BigDecimal( price );
			d.setPrice(bdprice);
		}
		// 功能应用范围
		if (!functionOfDevice.equals("-1")) {
			
			d.setFunction(functionOfDevice);
		}else {
			d.setFunction(null);
		}
		labRoomDeviceService.save(d);
		
		// 将设备管理员相关信息、设备状态和预约审核时间同步到该设备的关联设备
		List<SchoolDevice> innerSameDevices = schoolDeviceService.findInnerSameDevice(d.getSchoolDevice().getDeviceNumber());
		if (innerSameDevices!=null&&innerSameDevices.size()>0) {
		for (SchoolDevice schoolDevice : innerSameDevices) {
			LabRoomDevice device = labRoomDeviceService.findLabRoomDeviceByDeviceNumber(schoolDevice.getDeviceNumber());
			if (device!=null) {
				if (usernameManager!="-1") {
		            
		            user = userDAO.findUserByPrimaryKey(usernameManager);
		            device.setUser(user);
		        }
		        // 管理员电话
		        if (!managerTelephone.equals("-1")) {
		            device.setManagerTelephone(managerTelephone);
		        }else {
		            device.setManagerTelephone(null);
		        }
		        // 管理员邮箱
		        if (!managerMail.equals("-1")) {
		            device.setManagerMail(managerMail);
		        }else {
		            device.setManagerMail(null);
		        }
		        // 管理员办公室
		        if (!managerOffice.equals("-1")) {
		            device.setManagerOffice(managerOffice);
		        }else {
		            device.setManagerOffice(null);
		        }
		        // 设备状态
		        //device.setCDeviceStatus(cDeviceStatusDAO.findCDeviceStatusByPrimaryKey(status));
		        device.setCDictionaryByDeviceStatus(shareService.getCDictionaryByCategory("c_lab_room_device_status", String.valueOf(status)));
				labRoomDeviceService.save(device);
			}
		}
		}
		// 给用户赋予权限
		if (user != null){
			
			Set<Authority> ahths = user.getAuthorities();
				
				Authority a = authorityDAO.findAuthorityByPrimaryKey(10);// 设备管理员
				ahths.add(a);
				user.setAuthorities(ahths);
				userDAO.store(user);
		}
		mav.setViewName("redirect:/device/editDeviceInfoRest/" + labRoomId+"/"+ deviceNumber + "/" + URLEncoder.encode(deviceName,"utf-8") +"/" + username + "/"+page+"/" +id+"/"+schoolDevice_allowAppointment);
		//mav.setViewName("redirect:/device/listLabRoomDevice");
		
		mav.addObject("labRoomId", labRoomId);
		mav.addObject("deviceName", deviceName);
		mav.addObject("deviceNumber", deviceNumber);
		mav.addObject("username", username);
		mav.addObject("page", page);
		mav.addObject("schoolDevice_allowAppointment", schoolDevice_allowAppointment);
		return mav;
	}
	
	
	/****************************************************************************
	 * @throws UnsupportedEncodingException 
	 * @功能：实验室设备管理---设备管理---保存设置
	 * @作者：李小龙
	 ****************************************************************************/
	@RequestMapping(value = "/device/saveDeviceSettingRest/{labRoomId}/{deviceNumber}/{deviceName}/{username}/{page}/" +
			"{needLoan1}/{appointment1}/{needAudit1}/{needtutor1}/{needlabadministrator1}/{needadministrator1}" +
			"/{needAllowSecurityAccess1}/{trainType1}/{isAuditTimeLimit1}/{id}/{schoolDevice_allowAppointment}",
			method = RequestMethod.GET)
	public ModelAndView saveDeviceSettingRest(@PathVariable int labRoomId,
			@PathVariable String deviceNumber, @PathVariable String deviceName, @PathVariable String username,@PathVariable int page,
			@PathVariable int needLoan1, @PathVariable int appointment1,@PathVariable int needAudit1, @PathVariable int needtutor1, 
			@PathVariable int needlabadministrator1, @PathVariable int needadministrator1, @PathVariable int needAllowSecurityAccess1, 
			@PathVariable int trainType1,@PathVariable int isAuditTimeLimit1,@PathVariable int id,@PathVariable String schoolDevice_allowAppointment,
			Model model,@ModelAttribute("selected_labCenter") Integer cid) throws UnsupportedEncodingException {
		
		// 新建ModelAndView对象；
		ModelAndView mav = new ModelAndView();
		LabRoomDevice labDevice = labRoomDeviceService.findLabRoomDeviceByPrimaryKey(id);
		/*labDevice.setCActiveByAllowLending(cActiveDAO.findCActiveByPrimaryKey(needLoan1));
		labDevice.setCActiveByAllowAppointment(cActiveDAO.findCActiveByPrimaryKey(appointment1));
		labDevice.setCActiveByIsAudit(cActiveDAO.findCActiveByPrimaryKey(needAudit1));
		labDevice.setCActiveByTeacherAudit(cActiveDAO.findCActiveByPrimaryKey(needtutor1));
		labDevice.setCActiveByLabManagerAudit(cActiveDAO.findCActiveByPrimaryKey(needlabadministrator1));
		labDevice.setCActiveByManagerAudit(cActiveDAO.findCActiveByPrimaryKey(needadministrator1));
		labDevice.setCActiveByAllowSecurityAccess(cActiveDAO.findCActiveByPrimaryKey(needAllowSecurityAccess1));
		labDevice.setCTrainingType(cTrainingTypeDAO.findCTrainingTypeByPrimaryKey(trainType1));*/
		
		labDevice.setCDictionaryByAllowLending(cDictionaryService.finCDictionaryByPrimarykey(needLoan1));
		labDevice.setCDictionaryByAllowAppointment(cDictionaryService.finCDictionaryByPrimarykey(appointment1));
		labDevice.setCDictionaryByIsAudit(cDictionaryService.finCDictionaryByPrimarykey(needAudit1));
		//不需要导师审核
		//labDevice.setCDictionaryByTeacherAudit(cDictionaryService.finCDictionaryByPrimarykey(needtutor1));
		labDevice.setCDictionaryByTeacherAudit(cDictionaryService.finCDictionaryByPrimarykey(622));
		labDevice.setCActiveByLabManagerAudit(cDictionaryService.finCDictionaryByPrimarykey(needlabadministrator1));
		labDevice.setCDictionaryByManagerAudit(cDictionaryService.finCDictionaryByPrimarykey(needadministrator1));
		labDevice.setCDictionaryByAllowSecurityAccess(cDictionaryService.finCDictionaryByPrimarykey(needAllowSecurityAccess1));
		labDevice.setCDictionaryByTrainType(cDictionaryService.finCDictionaryByPrimarykey(trainType1));
		labDevice.setIsAuditTimeLimit(isAuditTimeLimit1);
		if (isAuditTimeLimit1==1&&needtutor1==1) {//将限制时间默认设置为48小时
			labDevice.setAuditTimeLimit(48);
		}
		LabRoomDevice d = labRoomDeviceService.save(labDevice);
		
		// 找出当前设备的关联设备并将该参数设置同步给关联设备
		List<SchoolDevice> innerSameDevices = schoolDeviceService.findInnerSameDevice(d.getSchoolDevice().getDeviceNumber());
		if (innerSameDevices!=null&&innerSameDevices.size()>0) {
		for (SchoolDevice schoolDevice : innerSameDevices) {
			LabRoomDevice device = labRoomDeviceService.findLabRoomDeviceByDeviceNumber(schoolDevice.getDeviceNumber());
			if (device!=null) {
				/*device.setCActiveByAllowLending(cActiveDAO.findCActiveByPrimaryKey(needLoan1));
				device.setCActiveByAllowAppointment(cActiveDAO.findCActiveByPrimaryKey(appointment1));
				device.setCActiveByIsAudit(cActiveDAO.findCActiveByPrimaryKey(needAudit1));
				device.setCActiveByTeacherAudit(cActiveDAO.findCActiveByPrimaryKey(needtutor1));
				device.setCActiveByLabManagerAudit(cActiveDAO.findCActiveByPrimaryKey(needlabadministrator1));
				device.setCActiveByManagerAudit(cActiveDAO.findCActiveByPrimaryKey(needadministrator1));
				device.setCActiveByAllowSecurityAccess(cActiveDAO.findCActiveByPrimaryKey(needAllowSecurityAccess1));
				device.setCTrainingType(cTrainingTypeDAO.findCTrainingTypeByPrimaryKey(trainType1));*/
				labDevice.setCDictionaryByAllowLending(cDictionaryService.finCDictionaryByPrimarykey(needLoan1));
				labDevice.setCDictionaryByAllowAppointment(cDictionaryService.finCDictionaryByPrimarykey(appointment1));
				labDevice.setCDictionaryByIsAudit(cDictionaryService.finCDictionaryByPrimarykey(needAudit1));
				labDevice.setCDictionaryByTeacherAudit(cDictionaryService.finCDictionaryByPrimarykey(needtutor1));
				labDevice.setCActiveByLabManagerAudit(cDictionaryService.finCDictionaryByPrimarykey(needlabadministrator1));
				labDevice.setCDictionaryByManagerAudit(cDictionaryService.finCDictionaryByPrimarykey(needadministrator1));
				labDevice.setCDictionaryByAllowSecurityAccess(cDictionaryService.finCDictionaryByPrimarykey(needAllowSecurityAccess1));
				labDevice.setCDictionaryByTrainType(cDictionaryService.finCDictionaryByPrimarykey(trainType1));
				device.setIsAuditTimeLimit(isAuditTimeLimit1);
				if (isAuditTimeLimit1==1&&needtutor1==1) {//将限制时间默认设置为48小时
					device.setAuditTimeLimit(48);
				}
				labRoomDeviceService.save(device);
			}
		}
		}
		mav.setViewName("redirect:/device/editDeviceSettingRest/" + labRoomId+"/"+ deviceNumber + "/" + URLEncoder.encode(deviceName,"utf-8") +"/" + username + "/"+page+"/" +id+"/"+schoolDevice_allowAppointment);
		//mav.setViewName("redirect:/device/listLabRoomDevice");
		
		mav.addObject("labRoomId", labRoomId);
		mav.addObject("deviceName", deviceName);
		mav.addObject("deviceNumber", deviceNumber);
		mav.addObject("username", username);
		mav.addObject("page", page);
		mav.addObject("schoolDevice_allowAppointment", schoolDevice_allowAppointment);
		return mav;
	}
	
	
	/****************************************************************************
	 * @throws UnsupportedEncodingException 
	 * @功能：保存实验设备培训结果
	 * @作者：李小龙
	 ****************************************************************************/
	@RequestMapping(value = "/device/saveTrainResultRest/{labRoomId}/{deviceNumber}/{deviceName}/{username}/{page}/{idStr}/{valueStr}/{schoolDevice_allowAppointment}",
			method = RequestMethod.GET)
	public ModelAndView saveTrainResultRest(@PathVariable int labRoomId,
			@PathVariable String deviceNumber, @PathVariable String deviceName,@PathVariable String username, @PathVariable int page,
			@PathVariable String idStr, @PathVariable String valueStr,@PathVariable String schoolDevice_allowAppointment,
			Model model,@ModelAttribute("selected_labCenter") Integer cid) throws UnsupportedEncodingException  {
		String[] idArray = idStr.split("S");
		String[] valueArray = valueStr.split("S");
		
		// 新建ModelAndView对象；
		ModelAndView mav = new ModelAndView();
		for (int i = 0; i < idArray.length; i++) {
			// 培训人
			LabRoomDeviceTrainingPeople p = labRoomDeviceTrainingPeopleDAO
					.findLabRoomDeviceTrainingPeopleByPrimaryKey(Integer.parseInt(idArray[i]));
			// 培训结果
			//CTrainingResult result = cTrainingResultDAO.findCTrainingResultByPrimaryKey(Integer.parseInt(valueArray[i]));
			CDictionary result = shareService.getCDictionaryByCategory("c_training_result", valueArray[i]);
			//p.setCTrainingResult(result);
			p.setCDictionary(result);
			// 执行保存
			labRoomDeviceTrainingPeopleDAO.store(p);
			
			//将通过的学生添加到LabRoomDevicePermitUsers中，flag为2
			//先将该用户从permitUser里面清除，防止重复添加
			if (p.getUser()!=null&&p.getLabRoomDeviceTraining()!=null
					&&p.getLabRoomDeviceTraining().getLabRoomDevice()!=null) {
				String username1 = p.getUser().getUsername();
				int deviceId = p.getLabRoomDeviceTraining().getLabRoomDevice().getId();
				LabRoomDevicePermitUsers permitUser = labRoomDeviceService.findPermitUserByUsernameAndDeivce(username1,deviceId);
				if (permitUser!=null) {
					labRoomDeviceService.deletePermitUser(permitUser);
				}
			}
			if (Integer.parseInt(valueArray[i])==1) {//通过
				//username对应的用户
				LabRoomDevicePermitUsers student=new LabRoomDevicePermitUsers();
				student.setUser(p.getUser());
				if (p.getLabRoomDeviceTraining()!=null&&p.getLabRoomDeviceTraining().getLabRoomDevice()!=null) {
					student.setLabRoomDevice(p.getLabRoomDeviceTraining().getLabRoomDevice());
				}
				student.setFlag(2);//标记位（1 单独培训通过  2 集训通过  3 集训后门）
				labRoomDevicePermitUsersDAO.store(student);
			}else {//不通过
				//do nothing
			}
		}
		// 培训人对应的培训
		LabRoomDeviceTrainingPeople people = labRoomDeviceTrainingPeopleDAO
				.findLabRoomDeviceTrainingPeopleByPrimaryKey(Integer.parseInt(idArray[0]));
		LabRoomDeviceTraining train = people.getLabRoomDeviceTraining();
		// 该培训的培训人
		Set<LabRoomDeviceTrainingPeople> peoples = train.getLabRoomDeviceTrainingPeoples();
		// 根据培训id查询培训通过的人
		List<LabRoomDeviceTrainingPeople> passPeoples = labRoomDeviceService
				.findPassLabRoomDeviceTrainingPeopleByTrainId(train.getId());

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
		// 中心所属学院
		/*String academy = labCenterDAO.findLabCenterByPrimaryKey(cid).getSchoolAcademy().getAcademyNumber();
		boolean flag = specialAcademy.contains(academy);
		if (flag) {
			mav.setViewName("redirect:/device/editDeviceTrainingRest/" + labRoomId+"/"+ deviceNumber + "/" + URLEncoder.encode(deviceName,"utf-8") +"/" + username + "/"+page+"/" + train.getLabRoomDevice().getId());
		} else {
			mav.setViewName("redirect:/device/findTrainingPeopleByDeviceIdAndTrainId?deviceId="
					+ train.getLabRoomDevice().getId() + "&id=" + train.getId());
		}*/
		mav.setViewName("redirect:/device/listLabRoomDevice");
		
		mav.addObject("labRoomId", labRoomId);
		mav.addObject("deviceName", deviceName);
		mav.addObject("deviceNumber", deviceNumber);
		mav.addObject("username", username);
		mav.addObject("page", page);
		mav.addObject("schoolDevice_allowAppointment", schoolDevice_allowAppointment);
		return mav;
	}

	
	/****************************************************************************
	 * 功能：保存单独随时培训学生
	 * 作者：李小龙
	 * @throws UnsupportedEncodingException 
	 ****************************************************************************/
	@RequestMapping(value = "/device/saveTrainSigleResultRest/{labRoomId}/{deviceNumber}/{deviceName}/{username}/{page}/{usernameStr}/{flag}/{id}/{schoolDevice_allowAppointment}",
			method = RequestMethod.GET)
	public ModelAndView saveTrainSigleResultRest(@PathVariable int labRoomId,
			@PathVariable String deviceNumber, @PathVariable String deviceName, @PathVariable String username,@PathVariable int page,
			@PathVariable String usernameStr,@PathVariable int flag,@PathVariable int id,@PathVariable String schoolDevice_allowAppointment,
			Model model,@ModelAttribute("selected_labCenter") Integer cid) throws UnsupportedEncodingException  {
		String[] array = usernameStr.split("S");
		ModelAndView mav=new ModelAndView();
		// id对应的培训
		LabRoomDevice device=labRoomDeviceService.findLabRoomDeviceByPrimaryKey(id);
		for (String i : array) {
	  		//username对应的用户
			LabRoomDevicePermitUsers student=new LabRoomDevicePermitUsers();
			User u=userDAO.findUserByPrimaryKey(i);
			student.setUser(u);
			student.setLabRoomDevice(device);
			student.setFlag(flag);//标记位（1 单独培训通过  2 集训通过  3 集训后门）
			labRoomDevicePermitUsersDAO.store(student);
	  	}		
		
//		mav.setViewName("redirect:/device/editDeviceTrainingRest/" + labRoomId+"/"+ deviceNumber + "/" + URLEncoder.encode(deviceName,"utf-8") +"/" + username + "/"+page+"/" + id);
		mav.setViewName("redirect:/device/listLabRoomDevice");
		
		mav.addObject("labRoomId", labRoomId);
		mav.addObject("deviceName", deviceName);
		mav.addObject("deviceNumber", deviceNumber);
		mav.addObject("username", username);
		mav.addObject("page", page);
		mav.addObject("schoolDevice_allowAppointment", schoolDevice_allowAppointment);
		return mav;
	}
	
	/****************************************************************************
	 * @功能：设备预约
	 * @作者：李小龙
	 ****************************************************************************/
	@RequestMapping(value = "/device/reservationDeviceRest/{labRoomId}/{deviceNumber}/{deviceName}/{username}/{page}/{id}/{schoolDevice_allowAppointment}",
	method = RequestMethod.GET)
	public ModelAndView reservationLabRoomDevice(@PathVariable int labRoomId,
			@PathVariable String deviceNumber, @PathVariable String deviceName, @PathVariable String username,@PathVariable int page,
			@PathVariable int id,@PathVariable String schoolDevice_allowAppointment,@ModelAttribute("selected_labCenter") Integer cid) {
		// 新建ModelAndView对象；
		ModelAndView mav = new ModelAndView();
		// id对应的实验室设备
		LabRoomDevice device = labRoomDeviceService.findLabRoomDeviceByPrimaryKey(id);
		String academy = labCenterDAO.findLabCenterByPrimaryKey(cid).getSchoolAcademy().getAcademyNumber();
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
		// 导师集合
		User user = shareService.getUser();
		List<User> ts = shareService.findTheSameCollegeTeacher(user.getSchoolAcademy().getAcademyNumber());
		List<String> teachers = new ArrayList<String>();
		for (User u : ts) {
			// 预约插件只支持纯数字，这边过滤掉username包含字母的数据
			if (Pattern.compile("(?i)[a-z]").matcher(u.getUsername()).find() == false) {
				teachers.add("{key" + ":" + u.getUsername().trim() + ",label:'" + u.getCname().trim() + u.getUsername() + "'}");
			}

		}
		mav.addObject("teachers", teachers);
		
		
		// 将设备管理员相关信息、设备状态和预约审核时间同步到该设备的关联设备
		List<SchoolDevice> innerSameDevices = schoolDeviceService.findInnerSameDevice(device.getSchoolDevice().getDeviceNumber());
		if (innerSameDevices!=null&&innerSameDevices.size()>0) {
			mav.addObject("innerSameDevices", innerSameDevices);
		}
		
		if (academy.equals("0201")) {// 纺织学院
			
			mav.addObject("isFZ", true);
			mav.addObject("first_hour", 8);
			mav.addObject("last_hour", 17);
			
		} else{
			
			mav.addObject("isFZ", false);
			mav.addObject("first_hour", 8);
			mav.addObject("last_hour", 21);
			
		}
		
		
		mav.setViewName("/device/lab_room_device/reservationDevice.jsp");
		mav.addObject("labRoomId", labRoomId);
		mav.addObject("deviceName", deviceName);
		mav.addObject("deviceNumber", deviceNumber);
		mav.addObject("username", username);
		mav.addObject("page", page);
		mav.addObject("schoolDevice_allowAppointment", schoolDevice_allowAppointment);
		return mav;
	}
	
	/****************************************************************************
	 * @功能：设备预约--查看界面的sheet
	 * @作者：李小龙
	 ****************************************************************************/
	@RequestMapping(value = "/device/doDeviceReservation/{labRoomId}/{deviceNumber}/{deviceName}/{username}/{page}/{id}/{currpage}/{schoolDevice_allowAppointment}",
	method = RequestMethod.GET)
	public ModelAndView doDeviceReservation(@PathVariable int labRoomId,
			@PathVariable String deviceNumber, @PathVariable String deviceName, @PathVariable String username,@PathVariable int page,
			@PathVariable int id, @PathVariable int currpage,@PathVariable String schoolDevice_allowAppointment,HttpServletRequest request, @ModelAttribute LabRoomDeviceReservation reservation,
			@ModelAttribute("selected_labCenter") Integer cid) {
		// 新建ModelAndView对象；
		ModelAndView mav = new ModelAndView();
		// id对应的实验室设备
		LabRoomDevice device = labRoomDeviceService.findLabRoomDeviceByPrimaryKey(id);
		String academy="";
		if(cid != null & cid != -1)
		{
			// 中心所属学院
			academy = labCenterDAO.findLabCenterByPrimaryKey(cid).getSchoolAcademy().getAcademyNumber();
		}
		else{
			academy=shareService.getUser().getSchoolAcademy().getAcademyNumber();
		}
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
		// 导师集合
		User user = shareService.getUser();
		List<User> ts = shareService.findTheSameCollegeTeacher(user.getSchoolAcademy().getAcademyNumber());
		List<String> teachers = new ArrayList<String>();
		for (User u : ts) {
			// 预约插件只支持纯数字，这边过滤掉username包含字母的数据
			if (Pattern.compile("(?i)[a-z]").matcher(u.getUsername()).find() == false) {
				teachers.add("{key" + ":" + u.getUsername().trim() + ",label:'" + u.getCname().trim() + u.getUsername() + "'}");
			}

		}
		 
		mav.addObject("teachers", teachers);
		
		List<ResearchProject> researchProjects = labRoomDeviceService.findAllResearchProjects(new ResearchProject(), 1, -1);
		List<String> researchs = new ArrayList<String>();
		for (ResearchProject r : researchProjects) { 
				researchs.add("{key" + ":" + r.getId() + ",label:'" +  r.getName().trim()  + "'}"); 

		}
		mav.addObject("researchs", researchs);
		// 将设备管理员相关信息、设备状态和预约审核时间同步到该设备的关联设备
		List<SchoolDevice> innerSameDevices = schoolDeviceService.findInnerSameDevice(device.getSchoolDevice().getDeviceNumber());
		if (innerSameDevices!=null&&innerSameDevices.size()>0) {
			mav.addObject("innerSameDevices", innerSameDevices);
		}
		
		if (academy.equals("0201")) {// 纺织学院
			mav.addObject("isFZ", true);
		} else{
			mav.addObject("isFZ", false);
		}
		
	     // 查询出所有的设备设备预约记录
        int totalRecords = labRoomDeviceService.countLabRoomDeviceReservationByDeviceNumberAndCid(reservation, request, device.getSchoolDevice().getDeviceNumber(), 0, null);
        int pageSize = 10;
        // 分页信息
        Map<String, Integer> pageModel = shareService.getPage(currpage, pageSize, totalRecords);
        // 根据分页信息查询出来的记录
        List<LabRoomDeviceReservation> reservationList = labRoomDeviceService.findLabRoomDeviceReservationByDeviceNumberAndCid(reservation, request, device.getSchoolDevice().getDeviceNumber(), currpage, pageSize, 0, null);
        mav.addObject("reservationList", reservationList);
        
        mav.addObject("pageModel", pageModel);
        mav.addObject("totalRecords", totalRecords);
        mav.addObject("currpage", currpage);
        mav.addObject("pageSize", pageSize);
		
        mav.addObject("isView", 1);
		
		mav.setViewName("/device/lab_room_device/reservationDevice.jsp");
		mav.addObject("labRoomId", labRoomId);
		mav.addObject("deviceName", deviceName);
		mav.addObject("deviceNumber", deviceNumber);
		mav.addObject("username", username);
		mav.addObject("page", page);
		mav.addObject("schoolDevice_allowAppointment",schoolDevice_allowAppointment);
		return mav;
	}
	
	/****************************************************************************
	 * @throws ParseException 
	 * @throws UnsupportedEncodingException 
	 * @功能：保存实验设备培训
	 * @作者：李小龙
	 ****************************************************************************/
	@RequestMapping(value = "/device/saveLabRoomDeviceTrainingRestNew/{labRoomId}/{deviceNumber}/{deviceName}/{username}/{page}/{content}/{usernameTeacher}/{number}/{id}/{schoolDevice_allowAppointment}",
			method = RequestMethod.GET)
	public ModelAndView saveLabRoomDeviceTrainingRestNew(@PathVariable int labRoomId,
			@PathVariable String deviceNumber, @PathVariable String deviceName,@PathVariable String username, @PathVariable int page,
			@PathVariable String content, 
			@PathVariable String usernameTeacher, @PathVariable int number, @PathVariable int id,@PathVariable String schoolDevice_allowAppointment,
			Model model,@ModelAttribute("selected_labCenter") Integer cid) throws ParseException, UnsupportedEncodingException {
		// 新建ModelAndView对象；
		ModelAndView mav = new ModelAndView();
		LabRoomDeviceTraining train=new LabRoomDeviceTraining();
		// id对应的实验分室设备
		LabRoomDevice device = labRoomDeviceService.findLabRoomDeviceByPrimaryKey(id);
		train.setLabRoomDevice(device);
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
		train.setContent(content);
		train.setNumber(number);
		// 状态设置为待培训
		//CTrainingStatus status = cTrainingStatusDAO.findCTrainingStatusByPrimaryKey(1);
		CDictionary status = shareService.getCDictionaryByCategory("c_training_status", "1");
		train.setCTrainingStatus(status);
		// 参加人数默认为0
		train.setJoinNumber(0);
		User user=userDAO.findUserByPrimaryKey(usernameTeacher);
		if (user != null && user.getUsername() != null
				&& !user.getUsername().equals("")) {
			train.setUser(user);
		} else {
			train.setUser(null);
		}
		labRoomDeviceService.saveLabRoomDeviceTraining(train);
		// 中心所属学院
		/*String academy = labCenterDAO.findLabCenterByPrimaryKey(cid).getSchoolAcademy().getAcademyNumber();
		boolean flag = specialAcademy.contains(academy);
		if (flag) {
			mav.setViewName("redirect:/device/editDeviceTrainingRest/" + labRoomId+"/"+ deviceNumber + "/" + URLEncoder.encode(deviceName,"utf-8") +"/" + username + "/"+page+"/" + id);
			
		} else {
			mav.setViewName("redirect:/device/editDeviceReservationInfo?id=" + id);
		}*/
		mav.setViewName("redirect:/device/listLabRoomDevice");
		mav.addObject("labRoomId", labRoomId);
		mav.addObject("deviceName", deviceName);
		mav.addObject("deviceNumber", deviceNumber);
		mav.addObject("username", username);
		mav.addObject("page", page);
		mav.addObject("schoolDevice_allowAppointment", schoolDevice_allowAppointment);
		return mav;
	}
	
}