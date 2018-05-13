/****************************************************************************
 * 命名规范：
 * 本控制层调整必须是按照如下的路径调整：@RequestMapping("/cms/device/sample")，全部小写。
 * 列表信息listxxxx，獲取信息：getxxxx 编辑信息：editxxxx 删除信息：detelexxxx 新增信息 newxxxx
 * 导出信息exportxxxx 保存信息：savexxxx  
 ****************************************************************************/

package net.xidlims.web.cms.lab;

import java.net.BindException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.xidlims.dao.AuthorityDAO;
import net.xidlims.dao.CDictionaryDAO;
import net.xidlims.dao.CStaticValueDAO;
import net.xidlims.dao.LabCenterDAO;
import net.xidlims.dao.LabRoomAdminDAO;
import net.xidlims.dao.LabRoomDAO;
import net.xidlims.dao.LabRoomDeviceDAO;
import net.xidlims.dao.LabRoomDeviceReservationDAO;
import net.xidlims.dao.LabRoomDeviceTrainingDAO;
import net.xidlims.dao.LabRoomDeviceTrainingPeopleDAO;
import net.xidlims.dao.SchoolDeviceDAO;
import net.xidlims.dao.UserDAO;
import net.xidlims.domain.Authority;
import net.xidlims.domain.CDictionary;
import net.xidlims.domain.CStaticValue;
import net.xidlims.domain.CommonDocument;
import net.xidlims.domain.CommonVideo;
import net.xidlims.domain.LabCenter;
import net.xidlims.domain.LabRoom;
import net.xidlims.domain.LabRoomAdmin;
import net.xidlims.domain.LabRoomDevice;
import net.xidlims.domain.LabRoomDevicePermitUsers;
import net.xidlims.domain.LabRoomDeviceReservation;
import net.xidlims.domain.LabRoomDeviceTraining;
import net.xidlims.domain.LabRoomDeviceTrainingPeople;
import net.xidlims.domain.SchoolAcademy;
import net.xidlims.domain.SchoolDevice;
import net.xidlims.domain.SchoolTerm;
import net.xidlims.domain.TimetableLabRelated;
import net.xidlims.domain.User;
import net.xidlims.service.common.CommonDocumentService;
import net.xidlims.service.common.CommonVideoService;
import net.xidlims.service.common.ShareService;
import net.xidlims.service.device.LabRoomDeviceReservationService;
import net.xidlims.service.device.LabRoomDeviceService;
import net.xidlims.service.lab.LabCenterService;
import net.xidlims.service.lab.LabRoomService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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


/****************************************************************************
 * 功能：
 * 设备管理模块 作者：魏诚 时间：2014-07-14
 ****************************************************************************/
@Controller("CmsDeviceController")
@SessionAttributes("selected_labCenter")
public class CmsDeviceController<JsonResult> {
	//读取属性文件中specialAcademy对应的值（此方法需要在web-content.xml中增加配置）
	@Value("${specialAcademy}")
	private String specialAcademy;
	
	@Autowired LabRoomDeviceService labRoomDeviceService;
	@Autowired LabRoomDAO labRoomDAO;
	@Autowired ShareService shareService;
	@Autowired SchoolDeviceDAO schoolDeviceDAO;
	@Autowired UserDAO userDAO;
	@Autowired LabRoomService labRoomService;
	@Autowired LabRoomDeviceDAO labRoomDeviceDAO;
	@Autowired LabCenterDAO labCenterDAO;
	@Autowired LabRoomAdminDAO labRoomAdminDAO;
	@Autowired AuthorityDAO authorityDAO;
	@Autowired CDictionaryDAO cDictionaryDAO;
	@Autowired CommonDocumentService commonDocumentService;
	@Autowired CommonVideoService commonVideoService;
	@Autowired CStaticValueDAO cStaticValueDAO;
	@Autowired LabRoomDeviceReservationDAO labRoomDeviceReservationDAO;
	@Autowired
	CDictionaryDAO cTrainingStatusDAO;
	@Autowired
	LabRoomDeviceTrainingDAO labRoomDeviceTrainingDAO;
	@Autowired
	LabRoomDeviceTrainingPeopleDAO labRoomDeviceTrainingPeopleDAO;
	
	@Autowired
	CDictionaryDAO cTrainingResultDAO;
	@Autowired
	LabCenterService labCenterService;
	@Autowired
	LabRoomDeviceReservationService labRoomDeviceReservationService;
	
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
	 * 功能：删除实验分室设备
	 * 作者：李小龙
	 ****************************************************************************/
	@RequestMapping("/cms/device/deleteLabRoomDevice")
	public ModelAndView deleteLabRoomDevice(@RequestParam Integer id){
		//新建ModelAndView对象；
		ModelAndView mav=new ModelAndView();
		//id对应的实验室设备
		LabRoomDevice d=labRoomDeviceService.findLabRoomDeviceByPrimaryKey(id);
		//设备所属实验室
		LabRoom room=d.getLabRoom();
		labRoomDeviceService.deleteLabRoomDevice(d);
		mav.setViewName("redirect:/appointment/getLabRoom?id="+room.getId());
		return mav;
	}
	
	/****************************************************************************
	 * 功能：实验室设备管理---设备管理--所有设备
	 * 作者：李小龙
	 ****************************************************************************/
	/*@RequestMapping("/cms/device/listLabRoomDevice")
	public ModelAndView listLabRoomDevice(@ModelAttribute LabRoomDevice labRoomDevice,@RequestParam Integer currpage,@ModelAttribute("selected_labCenter") Integer cid){
		ModelAndView mav=new ModelAndView();
		//中心所属学院
		String academy=labCenterDAO.findLabCenterByPrimaryKey(cid).getSchoolAcademy().getAcademyNumber();
		//获取当前用户
		User user=shareService.getUser();
		String username="["+user.getUsername()+"]";
		mav.addObject("username", username);
		mav.addObject("user", user);
		//查询表单的对象
		mav.addObject("labRoomDevice", labRoomDevice);
		//实验室
		List<LabRoom> rooms=labRoomService.findLabRoomByLabCenterid(cid);
		mav.addObject("rooms", rooms);
		
		List<User> users = shareService.findUsersByAuthorityId(10);  //设备管理员
		mav.addObject("users", users);
		int pageSize=20;//每页20条记录
		//查询出来的总记录条数
		int totalRecords=labRoomDeviceService.findAllLabRoomDevice(labRoomDevice, cid);
		//分页信息
		Map<String,Integer> pageModel =shareService.getPage(currpage, pageSize,totalRecords);
		//根据分页信息查询出来的记录
		List<LabRoomDevice> listLabRoomDevice=labRoomDeviceService.findAllLabRoomDevice(labRoomDevice, cid, currpage, pageSize);
		mav.addObject("listLabRoomDevice",listLabRoomDevice);
		
		mav.addObject("pageModel",pageModel);
		mav.addObject("totalRecords", totalRecords);
		mav.addObject("page", currpage);
		mav.addObject("pageSize", pageSize);
		//根据学院编号判断是否为配置文件配置的特殊学院（化工学院）
		boolean flag=specialAcademy.contains(academy);
		if(flag){
			mav.setViewName("/device/specialAcademy/specialAcademy.jsp");
		}else{
			mav.setViewName("/device/lab_room_device/listLabRoomDevice.jsp");
		}
		
		return mav;
	}*/
	
	/****************************************************************************
	 * @功能：实验室设备管理---设备管理--所有设备
	 * @作者：李小龙
	 ****************************************************************************/
	@RequestMapping("/cms/device/listLabRoomDevice")
	public ModelAndView listLabRoomDevice(@ModelAttribute LabRoomDevice labRoomDevice, @RequestParam Integer page,
			@ModelAttribute("selected_labCenter") Integer cid) {
		// 新建ModelAndView对象；
		ModelAndView mav = new ModelAndView();
		// 中心所属学院
		String academy = labCenterDAO.findLabCenterByPrimaryKey(cid).getSchoolAcademy().getAcademyNumber();

		// 查询表单的对象
		mav.addObject("labRoomDevice", labRoomDevice);
		// 实验室
		List<LabRoom> rooms = labRoomService.findLabRoomByLabCenterid(cid,1);
		int totalRecordsLabroom=labRoomService.findAllLabRoom(labRoomDevice, cid,1);
		mav.addObject("rooms", rooms);
		int pageSize = 10;
		int pageSizeLabroom=12;
		//实验室分页
		List<LabRoom> roomsList = labRoomService.findLabRoomByLabCenterid(labRoomDevice, cid,page,pageSizeLabroom);
		mav.addObject("roomsList", roomsList);
		
		// 设备管理员
		List<User> users = shareService.findUsersByAuthorityId(10);
		mav.addObject("users", users);
		// 查询出来的总记录条数
		int totalRecords = labRoomDeviceService.findAllLabRoomDevice(labRoomDevice, cid,1);
		// 分页信息
		Map<String, Integer> pageModel = shareService.getPage(page, pageSize, totalRecords);//listLabRoomDevice.jsp页面分页
		Map<String, Integer> pageModelLabroom = shareService.getPage(page, pageSizeLabroom, totalRecordsLabroom);//listLabRoom.jsp页面分页
		
		// 根据分页信息查询出来的记录
		List<LabRoomDevice> listLabRoomDevice = labRoomDeviceService.findAllLabRoomDeviceNew(labRoomDevice, cid, page,
				pageSize,1);
		mav.addObject("listLabRoomDevice", listLabRoomDevice);
		// 查询所有设备记录
		List<LabRoomDevice> listLabRoomDeviceAll = labRoomDeviceService.findAllLabRoomDeviceNew(labRoomDevice, cid);
		mav.addObject("listLabRoomDeviceAll", listLabRoomDeviceAll);
		mav.addObject("pageModel", pageModel);
		mav.addObject("pageModelLabroom", pageModelLabroom);
		mav.addObject("totalRecords", totalRecords);
		mav.addObject("totalRecordsLabroom", totalRecordsLabroom);
		mav.addObject("page", page);
		mav.addObject("pageSize", pageSize);
		mav.addObject("pageSizeLabroom", pageSizeLabroom);
		// 根据学院编号判断是否为配置文件配置的特殊学院（化工学院）
		boolean flag = specialAcademy.contains(academy);
		if (flag) {
			if (academy.equals("0201")) {//纺织学院
				mav.setViewName("/device/specialAcademy/listLabRoom.jsp");
				
			}else mav.setViewName("/device/specialAcademy/specialAcademy.jsp");
		} else {
			mav.setViewName("/device/specialAcademy/specialAcademy.jsp");
		}

		return mav;
	}

	/****************************************************************************
	 * @功能：实验室设备管理---设备管理---设置
	 * @作者：李小龙
	 ****************************************************************************/
	@RequestMapping("/cms/device/editDeviceSetting")
	public ModelAndView editDeviceSetting(@RequestParam Integer id) {
		// 新建ModelAndView对象；
		ModelAndView mav = new ModelAndView();
		// id对应的实验分室设备
		LabRoomDevice device = labRoomDeviceService.findLabRoomDeviceByPrimaryKey(id);
		mav.addObject("device", device);
		
		// 获取所有单选的结果集（是/否）
		//Set<CDictionary> CActives = cDictionaryDAO.findAllCActive();
		Set<CDictionary> CActives=new HashSet<CDictionary>(cDictionaryDAO.executeQuery("select c from CDictionary c where c.CCategory like 'c_active' ", 0,-1));

		mav.addObject("CActives", CActives);
		// 预约形式
		//Set<CAppointmentType> CAppointmentTypes = cAppointmentTypeDAO.findAllCAppointmentTypes();
		Set<CDictionary> CAppointmentTypes=new HashSet<CDictionary>(cDictionaryDAO.executeQuery("select c from CDictionary c where c.CCategory like 'c_active' ", 0,-1));

		mav.addObject("CAppointmentTypes", CAppointmentTypes);
		
		Set<CDictionary> CLabAccessTypes=new HashSet<CDictionary>(cDictionaryDAO.executeQuery("select c from CDictionary c where c.CCategory like 'c_lab_access_type' ", 0,-1));

		//Set<CLabAccessType> CLabAccessTypes = cLabAccessTypeDAO.findAllCLabAccessTypes();
		mav.addObject("CLabAccessTypes", CLabAccessTypes);
		mav.setViewName("/device/lab_room_device_access/editDeviceSetting.jsp");
		return mav;
	}
	/****************************************************************************
	 * 实验室设备管理---设备管理---编辑
	 * 作者：贺子龙
	 * 时间：2015-09-28 11:05:36
	 ****************************************************************************/
	
	@RequestMapping("/cms/device/editDeviceReservationInfo")
	public ModelAndView  editDeviceReservationInfo(@RequestParam Integer id) {
		//新建ModelAndView对象；
		ModelAndView mav=new ModelAndView();
		//id对应的实验分室设备
		LabRoomDevice device= labRoomDeviceService.findLabRoomDeviceByPrimaryKey(id);
		mav.addObject("device", device);
		//设备管理员
		User user=shareService.getUser();
		List<User> users=shareService.findTheSameCollegeTeacher(user.getSchoolAcademy().getAcademyNumber());
		mav.addObject("users", users);
		//设备状态
		Set<CDictionary> stutus=cDictionaryDAO.findCDictionaryByCCategoryContaining("c_lab_room_device_status");
		mav.addObject("stutus", stutus);
		//所属类型
		Set<CDictionary> types=cDictionaryDAO.findCDictionaryByCCategoryContaining("c_lab_room_device_type");	
		mav.addObject("types", types);
		//收费标准
		Set<CDictionary> charges=cDictionaryDAO.findCDictionaryByCCategoryContaining("c_lab_room_device_charge");
		mav.addObject("charges", charges);
		
		//当前时间所属的学期
		Calendar time=Calendar.getInstance();
		SchoolTerm term=shareService.getBelongsSchoolTerm(time);
		/*//根据学期和设备查询培训
		List<LabRoomDeviceTraining> trainings= labRoomDeviceService.findLabRoomDeviceTrainingByTermId(term.getId(),id);
		mav.addObject("trainings", trainings);*/
		/*//培训表单的对象
		mav.addObject("train", new LabRoomDeviceTraining());*/
		/*//培训教师
		
		//安全准入形式
		CLabAccessType accessType=device.getCLabAccessType();
		if(accessType!=null){
			if(accessType.getId()!=null&&accessType.getId()==1){//现场培训
				mav.setViewName("/device/lab_room_device_access/deviceTraining.jsp");
			}
			if(accessType.getId()!=null&&accessType.getId()==2){//网上考试
				//答题设置
				
				mav.setViewName("/device/lab_room_device_access/deviceExam.jsp");
			}
		}else{//默认为现场培训
			mav.setViewName("/device/lab_room_device_access/deviceTraining.jsp");
		}
		*/
		mav.setViewName("/device/lab_room_device_access/deviceTraining.jsp");
		
		return mav;		
	}
	/****************************************************************************
	 * 查看设备
	 * 作者：李小龙
	 ****************************************************************************/
	@RequestMapping("/cms/device/showDeviceTraining")
	public ModelAndView  showDeviceTraining(@RequestParam Integer id) {
		//新建ModelAndView对象；
		ModelAndView mav=new ModelAndView();
		//id对应的实验分室设备
		LabRoomDevice device=labRoomDeviceService.findLabRoomDeviceByPrimaryKey(id);
		mav.addObject("device", device);
		
		mav.setViewName("/device/lab_room_device/showDeviceTraining.jsp");
		return mav;		
	}
	

	/****************************************************************************
	 * 实验室设备管理---设备管理---保存实验设备信息
	 * 作者：李小龙
	 ****************************************************************************/
	@RequestMapping("/cms/device/saveDeviceReservationInfo")
	public ModelAndView  saveDeviceReservationInfo(@ModelAttribute LabRoomDevice device,@RequestParam Integer deviceId) {
		//新建ModelAndView对象；
		ModelAndView mav=new ModelAndView();
		//id对应的实验室设备
		LabRoomDevice d=labRoomDeviceService.findLabRoomDeviceByPrimaryKey(deviceId);
		User user=new User();
		if(device.getUser()!=null){
			user=userDAO.findUserByPrimaryKey(device.getUser().getUsername());
			d.setUser(user);
		}
		if(device.getCDictionaryByDeviceStatus()!=null&&device.getCDictionaryByDeviceStatus().getId()!=null){
			d.setCDictionaryByDeviceStatus(device.getCDictionaryByDeviceStatus());
		}
		if(device.getCDictionaryByDeviceType()!=null&&device.getCDictionaryByDeviceType().getId()!=null){
			d.setCDictionaryByDeviceType(device.getCDictionaryByDeviceType());
		}
		if(device.getCDictionaryByDeviceCharge()!=null&&device.getCDictionaryByDeviceCharge().getId()!=null){
			d.setCDictionaryByDeviceCharge(device.getCDictionaryByDeviceCharge());
		}
		d.setIndicators(device.getIndicators());
		
		d.setPrice(device.getPrice());
		d.setFunction(device.getFunction());
		d.setFeatures(device.getFeatures());
		d.setApplications(device.getApplications());
		labRoomDeviceService.save(d);
		//给用户赋予权限
		Set<Authority> ahths=user.getAuthorities();
		Authority a=authorityDAO.findAuthorityByPrimaryKey(10);//设备管理员
		ahths.add(a);
		user.setAuthorities(ahths);
		userDAO.store(user);
		//mav.setViewName("redirect:/device/listLabRoomDevice?currpage=1");
		mav.setViewName("redirect:/device/listLabRoomDeviceNew?roomId="+d.getLabRoom().getId()+"&page=1");
		return mav;		
	}
	
	/****************************************************************************
	 * 功能：处理ajax中文乱码
	 * 作者：李小龙
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
	 * 实验室设备管理---设备管理---设置
	 * 作者：李小龙
	 ****************************************************************************/
	/*@RequestMapping("/cms/device/editDeviceSetting")
	public ModelAndView  editDeviceSetting(@RequestParam Integer id) {
		//新建ModelAndView对象；
		ModelAndView mav=new ModelAndView();
		//id对应的实验分室设备
		LabRoomDevice device= labRoomDeviceService.findLabRoomDeviceByPrimaryKey(id);
		mav.addObject("device", device);
		//获取所有单选的结果集（是/否）
		Set<CActive> CActives=labRoomService.findAllCActive();
		mav.addObject("CActives", CActives);
		//预约形式
		Set<CAppointmentType> CAppointmentTypes=cAppointmentTypeDAO.findAllCAppointmentTypes();
		mav.addObject("CAppointmentTypes", CAppointmentTypes);
		Set<CLabAccessType> CLabAccessTypes=cLabAccessTypeDAO.findAllCLabAccessTypes();
		mav.addObject("CLabAccessTypes", CLabAccessTypes);
		mav.setViewName("/device/lab_room_device_access/editDeviceSetting.jsp");
		return mav;		
	}*/
	
	/****************************************************************************
	 * @功能：实验室设备管理---设备管理---保存设置
	 * @作者：李小龙
	 ****************************************************************************/
	@SuppressWarnings("unused")
	@RequestMapping("/cms/device/saveDeviceSetting")
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
	 * 实验室设备管理---设备管理---编辑（化工学院）
	 * 作者：李小龙
	 ****************************************************************************/
	/*@RequestMapping("/cms/device/editDeviceInfo")
	public ModelAndView  editDeviceInfo(@RequestParam Integer id) {
		//新建ModelAndView对象；
		ModelAndView mav=new ModelAndView();
		//id对应的实验分室设备
		LabRoomDevice device= labRoomDeviceService.findLabRoomDeviceByPrimaryKey(id);
		mav.addObject("device", device);
		//设备管理员
		User user=shareService.getUser();
		List<User> users=shareService.findTheSameCollegeTeacher(user.getSchoolAcademy().getAcademyNumber());
		mav.addObject("users", users);
		//设备状态
		Set<CDeviceStatus> stutus=cDeviceStatusDAO.findAllCDeviceStatuss();
		mav.addObject("stutus", stutus);
		//所属类型
		Set<CDeviceType> types=cDeviceTypeDAO.findAllCDeviceTypes();	
		mav.addObject("types", types);
		//收费标准
		Set<CDeviceCharge> charges=cDeviceChargeDAO.findAllCDeviceCharges();
		mav.addObject("charges", charges);
		
		//当前时间所属的学期
		Calendar time=Calendar.getInstance();
		SchoolTerm term=shareService.getBelongsSchoolTerm(time);
		//根据学期和设备查询培训
		List<LabRoomDeviceTraining> trainings= labRoomDeviceService.findLabRoomDeviceTrainingByTermId(term.getId(),id);
		mav.addObject("trainings", trainings);
		//培训表单的对象
		mav.addObject("train", new LabRoomDeviceTraining());
		//培训教师
		
		mav.setViewName("/device/specialAcademy/editDeviceInfo.jsp");
		
		return mav;		
	}*/
	/****************************************************************************
	 * 设备图片（化工学院）
	 * 作者：李小龙
	 ****************************************************************************/
	@RequestMapping("/cms/device/deviceImage")
	public ModelAndView  deviceImage(@RequestParam  Integer deviceId) {
		//新建ModelAndView对象；
		ModelAndView mav=new ModelAndView();
		LabRoomDevice device=labRoomDeviceService.findLabRoomDeviceByPrimaryKey(deviceId);
		mav.addObject("device", device);
		mav.setViewName("/device/specialAcademy/deviceImage.jsp");
		return mav;		
	}
	/****************************************************************************
	 * 设备视频（化工学院）
	 * 作者：李小龙
	 ****************************************************************************/
	@RequestMapping("/cms/device/deviceVideo")
	public ModelAndView  deviceVideo(@RequestParam  Integer deviceId) {
		//新建ModelAndView对象；
		ModelAndView mav=new ModelAndView();
		LabRoomDevice device=labRoomDeviceService.findLabRoomDeviceByPrimaryKey(deviceId);
		mav.addObject("device", device);
		mav.setViewName("/device/specialAcademy/deviceVideo.jsp");
		return mav;		
	}
	/****************************************************************************
	 * 设备视频（化工学院）
	 * 作者：李小龙
	 ****************************************************************************/
	@RequestMapping("/cms/device/dimensionalCode")
	public ModelAndView  dimensionalCode(@RequestParam  Integer deviceId) {
		//新建ModelAndView对象；
		ModelAndView mav=new ModelAndView();
		LabRoomDevice device=labRoomDeviceService.findLabRoomDeviceByPrimaryKey(deviceId);
		mav.addObject("device", device);
		mav.setViewName("/device/specialAcademy/dimensionalCode.jsp");
		return mav;		
	}
	
	
	/****************************************************************************
	 * 功能：给设备上传图片
	 * 作者：贺子龙
	 * 时间：2015-09-28 14:51:43
	 ****************************************************************************/
	@RequestMapping("/cms/device/deviceImageUpload")
	public @ResponseBody String deviceImageUpload(HttpServletRequest request, HttpServletResponse response, 
			BindException errors,Integer id) throws Exception {
		System.out.println("deviceImageUpload coming in");
		labRoomDeviceService.deviceImageUpload(request, response,id,1);
		return "ok";
	}
	/****************************************************************************
	 * 功能：给设备上传视频
	 * 作者：贺子龙
	 * 时间：2015-09-28 14:51:43
	 ****************************************************************************/
	@RequestMapping("/cms/device/deviceVideoUpload")
	public @ResponseBody String deviceVideoUpload(HttpServletRequest request, HttpServletResponse response, 
			BindException errors,Integer id) throws Exception {
		labRoomDeviceService.deviceVideoUpload(request, response,id);
		return "ok";
	}
	
	/****************************************************************************
	 * 功能：删除设备图片
	 * 作者：贺子龙
	 * 时间：2015-09-28 14:51:43
	 ****************************************************************************/
	@RequestMapping("/cms/device/deleteDeviceDocument")
	public ModelAndView deleteDeviceDocument(@RequestParam Integer id,@ModelAttribute("selected_labCenter") Integer cid){
		//新建ModelAndView对象；
		ModelAndView mav=new ModelAndView();
		//id对应的设备图片
		CommonDocument doc=commonDocumentService.findCommonDocumentByPrimaryKey(id);
		//图片所属的设备
		LabRoomDevice device=doc.getLabRoomDevice();
		int idkey=device.getId();
		commonDocumentService.deleteCommonDocument(doc);
		//删除服务器上的文件
		int type=doc.getType();
		//中心所属学院
		String academy=labCenterDAO.findLabCenterByPrimaryKey(cid).getSchoolAcademy().getAcademyNumber();
		//根据学院编号判断是否为配置文件配置的特殊学院（化工学院）
		boolean flag=specialAcademy.contains(academy);
		if(flag){
			if(type==1){//图片
				mav.setViewName("redirect:/device/deviceImage?deviceId="+idkey);
			}else{//文档
				mav.setViewName("redirect:/device/deviceDocument?deviceId="+idkey);
			}
			
		}else{
			mav.setViewName("redirect:/device/editDeviceReservationInfo?id="+idkey);
		}
		return mav;
	}
	/****************************************************************************
	 * 功能：删除设备视频
	 * 作者：贺子龙
	 * 时间：2015-09-28 14:51:43
	 ****************************************************************************/
	@RequestMapping("/cms/device/deleteLabRoomVideo")
	public ModelAndView deleteLabRoomVideo(@RequestParam Integer id,@ModelAttribute("selected_labCenter") Integer cid){
		//新建ModelAndView对象；
		ModelAndView mav=new ModelAndView();
		//id对应的设备视频
		CommonVideo video=commonVideoService.findVideoByPrimaryKey(id);
		//图片所属的设备
		LabRoomDevice device=video.getLabRoomDevice();
		int idkey=device.getId();
		commonVideoService.deleteCommonVideo(video);
		//删除服务器上的文件
		
		//中心所属学院
		String academy=labCenterDAO.findLabCenterByPrimaryKey(cid).getSchoolAcademy().getAcademyNumber();
		//根据学院编号判断是否为配置文件配置的特殊学院（化工学院）
		boolean flag=specialAcademy.contains(academy);
		if(flag){
			mav.setViewName("redirect:/device/deviceVideo?deviceId="+idkey);
		}else{
			mav.setViewName("redirect:/device/editDeviceReservationInfo?id="+idkey);
		}
		
		return mav;
	}
	
	/****************************************************************************
	 * @功能：安全准入验证
	 * @作者：李小龙
	 ****************************************************************************/
	@RequestMapping("/cms/device/securityAccess")
	public @ResponseBody
	String securityAccess(@RequestParam Integer id) {
		System.out.println("进入安全准入的AJAX");
		User user = shareService.getUser();
		String data = labRoomDeviceService.securityAccess(user.getUsername(), id);
		return htmlEncode(data);
	}
	
	/****************************************************************************
	 * @功能：设备预约
	 * @作者：李小龙
	 ****************************************************************************/
	@SuppressWarnings("unused")
	@RequestMapping("/cms/device/reservationDevice")
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
		if (cStaticValue.getStaticValue()!=null) {//判空，贺子龙 2015-10-20
			if (device.getCDictionaryByIsAudit()!=null&&device.getCDictionaryByIsAudit().getId()==621) {//  如果不需要审核，则不用加一天或两天时间  贺子龙  2015-11-04
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
			/*else {//但是，不能预约已经过去的时间
				now.add(Calendar.HOUR_OF_DAY, 1);//加一个小时
				
			}*/
			
			
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
			/*if(u.getSchoolAcademy().getAcademyNumber().equals("0201")&&
					u.getUsername().toString().indexOf("1005")==-1){//目前纺织学院正式帐号都是10055开头的，所以不包含10055的都要排除掉
				continue;
			}*/
			// 预约插件只支持纯数字，这边过滤掉username包含字母的数据
			if (Pattern.compile("(?i)[a-z]").matcher(u.getUsername()).find() == false) {
				teachers.add("{key" + ":" + u.getUsername().trim() + ",label:'" + u.getCname().trim() + u.getUsername() + "'}");
			}

		}
		mav.addObject("teachers", teachers);
		// 申请性质集合
		//Set<CReservationProperty> ps = cReservationPropertyDAO.findAllCReservationPropertys();
		
		Set<CDictionary> ps=new HashSet<CDictionary>(cDictionaryDAO.executeQuery("select c from CDictionary c where c.CCategory like 'c_reservation_property' ", 0,-1));

		List<String> propertys = new ArrayList<String>();
		for (CDictionary p : ps) {
			propertys.add("{key" + ":" + p.getId() + ",label:'" + p.getCName() + "'}");
		}
		mav.addObject("propertys", propertys);
		mav.setViewName("/device/lab_room_device/reservationDevice.jsp");
		return mav;
	}
	
	/****************************************************************************
	 * @功能：Ajax保存设备预约并返回是否保存成功 @作者：李小龙
	 ****************************************************************************/
	@RequestMapping("/cms/device/saveDeviceReservation") 
	public @ResponseBody
	String saveDeviceReservation(@RequestParam Integer equinemtid, String startDate, String endDate,
			String description, String phone, String teacher, Integer property) throws Exception {

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
		// 申请性质
		CDictionary p = cDictionaryDAO.findCDictionaryByPrimaryKey(property);
		reservation.setCReservationProperty(p);

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
		

		//获取当前学期
		int termId = shareService.getBelongsSchoolTerm(Calendar.getInstance()).getId();
		//实验室禁用时间段列表
		/*List<LabRoomLimitTime>  labRoomLimitTimes = labRoomLimitTimeDAO.executeQuery("select c from LabRoomLimitTime c where c.labId= " + device.getLabRoom().getId() + " and c.schoolTerm.id=" + termId,0,-1);
		//如果是禁用时间匹配则返回limit
		if(!shareService.isLimitedByTime(labRoomLimitTimes,calendar1,calendar2)){
			return "LIMIT";
		}*/
		/*for(LabRoomLimitTime labRoomLimitTime:labRoomLimitTimes ){
			//如果是禁用时间则返回limit
			String sListDate = shareService.getDateByWeekdayClassWeek(labRoomLimitTime.getWeekday(), labRoomLimitTime.getStartclass(), labRoomLimitTime.getStartweek(), termId,0);
			String eListDate = shareService.getDateByWeekdayClassWeek(labRoomLimitTime.getWeekday(), labRoomLimitTime.getEndclass(), labRoomLimitTime.getEndweek(), termId,1);
			
			Date startLimitDate = sdf.parse(sListDate);
			Date endLimitDate = sdf.parse(eListDate);
			int cStartWeek = shareService.getBelongsSchoolWeek(calendar1);
			int cEndWeek = shareService.getBelongsSchoolWeek(calendar2);
            System.out.println("start:" + sListDate +"end:" +eListDate);
            System.out.println("startC:" + cStartWeek +"endC:" +cEndWeek);

			if(startLimitDate.getTime()<date.getTime()&&endLimitDate.getTime()>date.getTime()||startLimitDate.getTime()<date2.getTime()&&endLimitDate.getTime()>date2.getTime()){
				return "LIMIT";
			}
	
			String limitTime = shareService.getDateByWeekdayClassWeek(labRoomLimitTime.getWeekday(), labRoomLimitTime.getStartclass(), labRoomLimitTime.getStartweek(), termId,0) + ";" +shareService.getDateByWeekdayClassWeek(labRoomLimitTime.getWeekday(), labRoomLimitTime.getEndclass(), labRoomLimitTime.getEndweek(), termId,1);
			map.put(labRoomLimitTime.getId().toString(),limitTime );
		}*/
		
		// 状态
		// 是否需要审核
		int isAudit = device.getCDictionaryByIsAudit().getId();
				
		if (isAudit == 622) {// 不需要审核
			CDictionary status = cDictionaryDAO.findCDictionaryByPrimaryKey(622);
			reservation.setCAuditResult(status);
		}
		if (isAudit == 621) {// 需要审核
			// 默认先设为审核中
			CDictionary status = cDictionaryDAO.findCDictionaryByPrimaryKey(621);
			reservation.setCAuditResult(status);
			
			if (device.getCDictionaryByTeacherAudit() != null) {//贺子龙  2015-11-06
				int CActiveByTeacherAuditId = device.getCDictionaryByTeacherAudit().getId();
				if (CActiveByTeacherAuditId==2) {//不需要导师
					if (device.getCActiveByLabManagerAudit() != null){
						int CActiveByLabManagerAuditId = device.getCActiveByLabManagerAudit().getId();
						if (CActiveByLabManagerAuditId==2) {//不需要实验室管理员
							if (device.getCDictionaryByManagerAudit() != null) {
								int CActiveByManagerAuditId = device.getCDictionaryByManagerAudit().getId();
								if (CActiveByManagerAuditId==2) {//不需要设备管理员
									// 审核通过
									CDictionary pass = cDictionaryDAO.findCDictionaryByPrimaryKey(622);
									reservation.setCAuditResult(pass);
								}
							}
						}
					}
				}
			}
			
			
			/*// 是否需要导师审核
			if (device.getCActiveByTeacherAudit() != null) {
				int CActiveByTeacherAuditId = device.getCActiveByTeacherAudit().getId();
				if (device.getCActiveByManagerAudit() != null) {
					// 是否需要管理员审核
					int CActiveByManagerAuditId = device.getCActiveByManagerAudit().getId();
					if (CActiveByTeacherAuditId == 2) {// 不需要导师审核
						if (CActiveByManagerAuditId == 2) {// 不需要管理员审核
							// 审核通过
							CAuditResult pass = cAuditResultDAO.findCAuditResultByPrimaryKey(2);
							reservation.setCAuditResult(pass);
						}
					}
				}
			}*/
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
		if (flag > 0) {
			reservation.setStage(0);
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
	@RequestMapping("/cms/device/getLabReservation")
	public @ResponseBody
	List getLabReservation(@RequestParam Integer id) throws Exception {
		// 创建一个新的数组对象；
		List list = new ArrayList();
		// 根据设备id查询设备预约记录
		List<LabRoomDeviceReservation> reservations = labRoomDeviceService.findReservationListByDeviceId(id);
		for (LabRoomDeviceReservation r : reservations) {
			// 创建一个新的map对象给变量appointmentMap；
			Map<String, Object> map = new HashMap<String, Object>();

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat sdfSimple = new SimpleDateFormat("yyyy-MM-dd");
			// 开始@时间
			Calendar startDate = r.getBegintime();
			// 结束@时间
			Calendar endDate = r.getEndtime();
			//解决跨天问题
			
			//获取两个日期相隔的天数
			int days = (int) ((endDate.getTime().getTime() - startDate.getTime().getTime()) / (1000 * 60 * 60 * 24));
		    //如果不跨天
			if(days==0){
		    	map.put("start_date", sdf.format(startDate.getTime()));
				map.put("end_date", sdf.format(endDate.getTime()));
				list.add(map);
				
		    }else{
		    	
		    	for(int i=0;i<=days;i++){
		    		Map<String, Object> mapTmp = new HashMap<String, Object>();
		    		if(i==0){
		    			mapTmp.put("start_date", sdf.format(startDate.getTime()));
		    			mapTmp.put("end_date", sdfSimple.format(startDate.getTime())+" 23:59:59");
		    		}else if(i==days){
		    			mapTmp.put("start_date", sdfSimple.format(endDate.getTime())+" 00:00:01");
		    			mapTmp.put("end_date", sdf.format(endDate.getTime()));
		    		}else{
		    			startDate.add(Calendar.DATE, 1);//
		    			mapTmp.put("start_date", sdfSimple.format(startDate.getTime())+" 00:00:01");
		    			mapTmp.put("end_date", sdfSimple.format(startDate.getTime())+" 23:59:59");
						
		    		}
		    		list.add(mapTmp);
		    		
			    }
		    	
		    	
		    }
			
		}

		return list;
	}

	/****************************************************************************
	 * @功能：批量生成二维码
	 * @作者：李小龙
	 * @throws Exception
	 ****************************************************************************/
	@RequestMapping("/cms/device/generateDimensionalCode")
	public @ResponseBody
	String generateDimensionalCode(HttpServletRequest request) {
		
//		System.out.println(request.getRequestURL());//http://localhost/dhulims/device/generateDimensionalCode
//		System.out.println(request.getContextPath());///dhulims
//		System.out.println(request.getServerName());//localhost
		String serverName = request.getServerName();
		String data = "success";
		Set<LabRoomDevice> deviceList = labRoomDeviceDAO.findAllLabRoomDevices();
		for (LabRoomDevice d : deviceList) {
			String url = "";
			try {
				url = shareService.getDimensionalCode(d,serverName);
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
	 * @功能：实验室设备管理---设备管理--所有设备
	 * @作者：贺子龙
	 ****************************************************************************/
	@RequestMapping("/cms/device/listLabRoomDeviceNew")
	public ModelAndView listLabRoomDeviceNew(@ModelAttribute LabRoomDevice labRoomDevice, @RequestParam int roomId, Integer page,
			@ModelAttribute("selected_labCenter") Integer cid) {
		//System.out.println("=-=-=-=-=-");
		// 新建ModelAndView对象；
		ModelAndView mav = new ModelAndView();

		// 查询表单的对象
		mav.addObject("labRoomDevice", labRoomDevice);
		// 实验室
		List<LabRoom> rooms = labRoomService.findLabRoomByLabCenterid(cid,1);
		mav.addObject("rooms", rooms);
		int pageSize = 10;
		//roomId对应的实验室
		LabRoom labRoom=labRoomService.findLabRoomByPrimaryKey(roomId);
		mav.addObject("labRoom", labRoom);
		
		// 设备管理员
		List<User> users = shareService.findUsersByAuthorityId(10);
		mav.addObject("users", users);
		// 查询出来的总记录条数
		int totalRecords = labRoomDeviceService.findAllLabRoomDevice(labRoomDevice, cid, roomId);
		// 分页信息
		Map<String, Integer> pageModel = shareService.getPage(page, pageSize, totalRecords);//listLabRoomDevice.jsp页面分页
		
		// 根据分页信息查询出来的记录
		List<LabRoomDevice> listLabRoomDevice = labRoomDeviceService.findAllLabRoomDeviceNew(labRoomDevice, cid, page,
				pageSize,1);
		mav.addObject("listLabRoomDevice", listLabRoomDevice);
		// 查询所有设备记录
		List<LabRoomDevice> listLabRoomDeviceAll = labRoomDeviceService.findAllLabRoomDeviceNew(labRoomDevice, cid);
		mav.addObject("listLabRoomDeviceAll", listLabRoomDeviceAll);
		mav.addObject("pageModel", pageModel);
		mav.addObject("totalRecords", totalRecords);
		mav.addObject("page", page);
		mav.addObject("pageSize", pageSize);
		mav.addObject("roomId", roomId);
		mav.addObject("tag",0);//后台区分查询取消的跳转页面
		mav.setViewName("/device/specialAcademy/specialAcademyForLabroom.jsp");
		return mav;
	}
	
	/****************************************************************************
	 * @功能：根据设备id查询培训和培训人名单
	 * @作者：李小龙
	 ****************************************************************************/
	@RequestMapping("/cms/device/findTrainingPeopleByDeviceIdAndTrainId")
	public ModelAndView findTrainingPeopleByDeviceIdAndTrainId(@ModelAttribute LabRoomDeviceTraining train,
			@RequestParam Integer deviceId, Integer id) {
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
		Set<CDictionary> results=new HashSet<CDictionary>(cDictionaryDAO.executeQuery("select c from CDictionary c where c.CCategory like 'c_training_result' ", 0,-1));

		mav.addObject("results", results);

		// 根据设备id查询培训
		List<LabRoomDeviceTraining> trainList = labRoomDeviceService.findLabRoomDeviceTrainingByDeviceId(train,
				deviceId);
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
	 * @功能：保存实验设备培训结果
	 * @作者：李小龙
	 ****************************************************************************/
	@RequestMapping("/cms/device/saveTrainResult")
	public ModelAndView saveTrainResult(@RequestParam int idArray[], int valueArray[],
			@ModelAttribute("selected_labCenter") Integer cid) {
		// 新建ModelAndView对象；
		ModelAndView mav = new ModelAndView();
		for (int i = 0; i < idArray.length; i++) {
			// 培训人
			LabRoomDeviceTrainingPeople p = labRoomDeviceTrainingPeopleDAO
					.findLabRoomDeviceTrainingPeopleByPrimaryKey(idArray[i]);
			// 培训结果
			CDictionary result = cTrainingResultDAO.findCDictionaryByPrimaryKey(valueArray[i]);
			p.setCDictionary(result);
			// 执行保存
			labRoomDeviceTrainingPeopleDAO.store(p);
		}
		// 培训人对应的培训
		LabRoomDeviceTrainingPeople people = labRoomDeviceTrainingPeopleDAO
				.findLabRoomDeviceTrainingPeopleByPrimaryKey(idArray[0]);
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
		CDictionary status = cTrainingStatusDAO.findCDictionaryByPrimaryKey(630);
		train.setCTrainingStatus(status);
		labRoomDeviceTrainingDAO.store(train);
		// 中心所属学院
		String academy = labCenterDAO.findLabCenterByPrimaryKey(cid).getSchoolAcademy().getAcademyNumber();
		boolean flag = specialAcademy.contains(academy);
		if (flag) {
			mav.setViewName("redirect:/device/deviceTraining?deviceId=" + train.getLabRoomDevice().getId());
		} else {
			mav.setViewName("redirect:/device/findTrainingPeopleByDeviceIdAndTrainId?deviceId="
					+ train.getLabRoomDevice().getId() + "&id=" + train.getId());
		}

		return mav;
	}
	
	/****************************************************************************
	 * @功能：设备管理
	 * @作者：黄崔俊
	 * @时间： 2016-4-22 09:45:14
	 ****************************************************************************/
	@RequestMapping("/cms/device/deviceManagement")
	public ModelAndView deviceManagement(@ModelAttribute LabRoomDevice labRoomDevice, 
			@RequestParam Integer currpage,Integer cid) {
		// 新建ModelAndView对象；
		ModelAndView mav = new ModelAndView();
		// 查询表单的对象
		mav.addObject("labRoomDevice", labRoomDevice);
		mav.addObject("cid", cid);
		
		List<LabRoomDeviceReservation> labRoomDeviceReservations = labRoomDeviceService.findAllLabRoomDeviceReservation(new LabRoomDeviceReservation(), null, null, 1, 10, 1);
		mav.addObject("labRoomDeviceReservations", labRoomDeviceReservations);
		
		// 实验室
		List<LabRoom> rooms = labRoomService.findLabRoomByLabCenterid(cid,1);
		mav.addObject("rooms", rooms);
		int pageSize = 8;
		// 查询出来的总记录条数
		int totalRecords = labRoomDeviceService.findAllLabRoomDevice(labRoomDevice, cid,1);
		// 分页信息
		Map<String, Integer> pageModel = shareService.getPage(currpage, pageSize, totalRecords);//listLabRoomDevice.jsp页面分页
		
		// 根据分页信息查询出来的记录
		List<LabRoomDevice> listLabRoomDevice = labRoomDeviceService.findAllLabRoomDeviceNew(labRoomDevice, cid, currpage,
				pageSize,1);
		mav.addObject("listLabRoomDevice", listLabRoomDevice);
		// 查询所有设备记录
		List<LabRoomDevice> labRoomDeviceNumbers = labRoomDeviceService.findAllLabRoomDeviceNumbers(labRoomDevice, cid);
		mav.addObject("labRoomDeviceNumbers", labRoomDeviceNumbers);
		mav.addObject("pageModel", pageModel);
		mav.addObject("page", currpage);
	
		User user = shareService.getUser();
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
		
		mav.setViewName("cms/device/lab_room_device/labRoomDeviceManagement.jsp");
		return mav;
	}
	
	/*************************************************************************************
	 * @內容：根据实验室查询实验室设备列表
	 * @作者：黄崔俊
	 * @日期：2016-5-4 15:07:51
	 *************************************************************************************/
	@RequestMapping("/cms/device/findSchoolDeviceByLabRoom")
	@ResponseBody
	public Map<String, String> findSchoolDeviceByLabRoom(@RequestParam Integer labroomId) {
		
		//查询实验室中心
		LabRoom labRoom = labRoomService.findLabRoomByPrimaryKey(labroomId);
		LabRoomDevice labRoomDevice = new LabRoomDevice();
		labRoomDevice.setLabRoom(labRoom);
		List<LabRoomDevice> listLabRoomDevice = labRoomDeviceService.findAllLabRoomDeviceNew(labRoomDevice, labRoom.getLabCenter().getId(), 0,-1,1);
		Map<String, String> map = new HashMap<String, String>();
		for (LabRoomDevice labRoomDevice2 : listLabRoomDevice) {
			map.put(labRoomDevice2.getSchoolDevice().getDeviceNumber(), labRoomDevice2.getSchoolDevice().getDeviceName());
		}
		
		return map;
	}
	
	/*************************************************************************************
	 * @內容：查看实验室设备详情
	 * @作者：黄崔俊
	 * @日期：2016-5-12 10:50:51
	 *************************************************************************************/
	@RequestMapping("/cms/device/findLabRoomDeviceDetail")
	public ModelAndView findLabRoomDeviceDetail(@RequestParam Integer labRoomDeviceId) {
		ModelAndView mav = new ModelAndView();
		LabRoomDevice labRoomDevice = labRoomDeviceService.findLabRoomDeviceByPrimaryKey(labRoomDeviceId);
		mav.addObject("device",labRoomDevice);
		mav.setViewName("cms/device/lab_room_device/labRoomDeviceInfo.jsp");
		return mav;
	}
	
	/****************************************************************************
	 * 功能：实验室设备预约记录
	 * 作者：黄崔俊
	 ****************************************************************************/
	@RequestMapping("/cms/device/reservationList")
	public ModelAndView reservationList(@RequestParam Integer currpage,Integer labRoomDeviceId){
		//新建ModelAndView对象；
		ModelAndView mav=new ModelAndView();
		
		//查询出所有的设备设备预约记录
		int totalRecords = labRoomDeviceService.countLabRoomDeviceReservationInfoByDeviceId(labRoomDeviceId);
		int pageSize=2;//每页20条记录
		//分页信息
		Map<String,Integer> pageModel =shareService.getPage(currpage,pageSize,totalRecords);
		//根据分页信息查询出来的记录
		List<LabRoomDeviceReservation> reservationList=labRoomDeviceService.findLabRoomDeviceReservationInfoByDeviceId(labRoomDeviceId,currpage,pageSize);
		mav.addObject("reservationList", reservationList);
		mav.addObject("labRoomDeviceId", labRoomDeviceId);
		mav.addObject("pageModel",pageModel);
		mav.setViewName("cms/device/lab_room_device/labRoomDeviceReservationList.jsp");
		return mav;
	}
	
	/****************************************************************************
	 * 功能：实验室设备培训记录
	 * 作者：黄崔俊
	 * 时间：2016-5-18 17:00:43
	 ****************************************************************************/
	@RequestMapping("/cms/device/labRoomDeviceTrainingList")
	@ResponseBody
	public Map<String, Object> labRoomDeviceTrainingList(@RequestParam Integer labRoomDeviceId,Integer currpage){
		Map<String, Object> map = new HashMap<String, Object>();
		int pageSize=1;
		List<LabRoomDeviceTraining> deviceTrainings = labRoomDeviceService.findLabRoomDeviceTrainingListByDeviceId(labRoomDeviceId,currpage,pageSize);
		List<String[]> trainingList =  new ArrayList<String[]>();
		for (LabRoomDeviceTraining labRoomDeviceTraining : deviceTrainings) {
			String[] s = new String[8];
			s[0] = labRoomDeviceTraining.getContent();
			s[1] = labRoomDeviceTraining.getAddress();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			s[2] = sdf.format(labRoomDeviceTraining.getTime().getTime());
			s[3] = labRoomDeviceTraining.getUser().getCname();
			s[4] = labRoomDeviceTraining.getJoinNumber()+"/"+labRoomDeviceTraining.getNumber();
			s[5] = labRoomDeviceTraining.getSchoolTerm().getTermName();
			s[6] = labRoomDeviceTraining.getCTrainingStatus().getCName();
			if (labRoomDeviceTraining.getPassRate()==null||"".equals(labRoomDeviceTraining.getPassRate())) {
				s[7] = "未有结果";
			}else {
				s[7] = labRoomDeviceTraining.getPassRate();
			}
			trainingList.add(s);
		}
		int totalRecords = labRoomDeviceService.countLabRoomDeviceTrainingListByDeviceId(labRoomDeviceId);
		//分页信息
		Map<String,Integer> pageModel =shareService.getPage(currpage,pageSize,totalRecords);
		map.put("trainingList", trainingList);
		map.put("labRoomDeviceId", labRoomDeviceId);
		map.put("pageModel",pageModel);
		return map;
	}
	
	/****************************************************************************
	 * 功能：实验室设备培训通过名单
	 * 作者：黄崔俊
	 * 时间：2016-5-18 17:00:43
	 ****************************************************************************/
	@RequestMapping("/cms/device/labRoomDevicePermitUserList")
	@ResponseBody
	public Map<String, Object> managePermitUser(@RequestParam Integer labRoomDeviceId, int currpage) {
		Map<String, Object> map = new HashMap<String, Object>(); 
		int pageSize = 2;
		
		List<LabRoomDevicePermitUsers> students = labRoomDeviceService.findPermitUserByDeivceId(labRoomDeviceId, currpage, pageSize);
		List<String[]> userList = new ArrayList<String[]>();
		for (LabRoomDevicePermitUsers permitUser : students) {
			String[] s = new String[4];
			s[0] = permitUser.getUser().getUsername();
			s[1] = permitUser.getUser().getCname();
			if ("0".equals(permitUser.getUser().getUserRole())) {
				s[2] = "学生";
			}
			if ("2".equals(permitUser.getUser().getUserRole())) {
				s[2] = "研究生";
			}
			s[3] = permitUser.getUser().getSchoolAcademy().getAcademyName();
			userList.add(s);
		}
		int totalRecords = labRoomDeviceService.findPermitUserByDeivceId(labRoomDeviceId, 1, -1).size();
		
		map.put("userList", userList);
		map.put("labRoomDeviceId", labRoomDeviceId);
		map.put("pageModel", shareService.getPage(currpage, pageSize, totalRecords));
		map.put("pageSize", pageSize);
		return map;
	}
}