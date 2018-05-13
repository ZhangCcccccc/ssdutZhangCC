/****************************************************************************
 * 命名规范：
 * 本控制层调整必须是按照如下的路径调整：@RequestMapping("/device/sample")，全部小写。
 * 列表信息listxxxx，獲取信息：getxxxx 编辑信息：editxxxx 删除信息：detelexxxx 新增信息 newxxxx
 * 导出信息exportxxxx 保存信息：savexxxx  
 ****************************************************************************/

package net.xidlims.web.device;

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
import javax.servlet.http.HttpServletResponse;

import net.xidlims.common.ReservationList;
import net.xidlims.dao.CStaticValueDAO;
import net.xidlims.dao.LabCenterDAO;
import net.xidlims.dao.LabRoomDeviceReservationDAO;
import net.xidlims.dao.LabRoomDeviceReservationResultDAO;
import net.xidlims.dao.LabRoomLimitTimeDAO;
import net.xidlims.dao.SchoolTermDAO;
import net.xidlims.domain.CDictionary;
import net.xidlims.domain.CStaticValue;
import net.xidlims.domain.LabRoom;
import net.xidlims.domain.LabRoomAdmin;
import net.xidlims.domain.LabRoomDevice;
import net.xidlims.domain.LabRoomDeviceReservation;
import net.xidlims.domain.LabRoomDeviceReservationResult;
import net.xidlims.domain.SchoolDevice;
import net.xidlims.domain.SchoolTerm;
import net.xidlims.domain.User;
import net.xidlims.service.common.ShareService;
import net.xidlims.service.device.LabRoomDeviceReservationService;
import net.xidlims.service.device.LabRoomDeviceService;
import net.xidlims.service.device.SchoolDeviceService;
import net.xidlims.service.lab.LabCenterService;
import net.xidlims.service.lab.LabRoomService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.google.inject.servlet.RequestParameters;

import edu.emory.mathcs.backport.java.util.Collections;

/****************************************************************************
 * 功能： 设备预约管理模块 作者：李小龙 时间：2014-08-08
 ****************************************************************************/
@Controller("DeviceReservationController")
@SessionAttributes("selected_labCenter")
public class DeviceReservationController<JsonResult> {

	@Autowired
	LabRoomDeviceService labRoomDeviceService;
	@Autowired
	LabRoomDeviceReservationDAO labRoomDeviceReservationDAO;
	@Autowired
	LabRoomDeviceReservationResultDAO labRoomDeviceReservationResultDAO;
	@Autowired
	ShareService shareService;
	@Autowired
	SchoolTermDAO schoolTermDAO;
	@Autowired
	LabCenterDAO labCenterDAO;
	@Autowired
	LabCenterService labCenterService;
	@Autowired
	LabRoomService labRoomService;
	@Autowired
	LabRoomDeviceReservationService labRoomDeviceReservationService;
	@Autowired
	CStaticValueDAO cStaticValueDAO;
	@Autowired
	LabRoomLimitTimeDAO labRoomLimitTimeDAO;
	@Autowired
	SchoolDeviceService schoolDeviceService;
	
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
	 * 功能：实验室设备管理---设备预约管理---全部 作者：李小龙
	 ****************************************************************************/
	@RequestMapping("/device/reservationList")
	public ModelAndView reservationList(@ModelAttribute LabRoomDeviceReservation reservation, @RequestParam Integer page, @ModelAttribute("selected_labCenter") Integer centerId) {
		// 新建ModelAndView对象；
		ModelAndView mav = new ModelAndView();
		// 获取当前登录人
		User user = shareService.getUser();
		String username = user.getUsername();
		username = "[" + username + "]";

		// 学期
		List<SchoolTerm> terms = shareService.findAllSchoolTerms();
		mav.addObject("terms", terms);
		// 中心所属实验室
		List<LabRoom> rooms = labRoomService.findLabRoomByLabCenterid(centerId,1);
		mav.addObject("rooms", rooms);
		mav.addObject("reservation", reservation);
		// 查询出所有的设备设备预约记录
		List<LabRoomDeviceReservation> allReservationList = labRoomDeviceService.findAllLabRoomDeviceReservation(reservation, 0, centerId, 1, -1);
		int totalRecords = allReservationList.size();
		int pageSize = 10;// 每页10条记录
		// 分页信息
		Map<String, Integer> pageModel = shareService.getPage(page, pageSize, totalRecords);
		// 根据分页信息查询出来的记录
		List<LabRoomDeviceReservation> reservationList = labRoomDeviceService.findAllLabRoomDeviceReservation(reservation, 0, centerId, page, pageSize);
		mav.addObject("reservationList", reservationList);
		// 找出设备预约对应的所有的申请人、指导老师、设备管理员、实验室
		Map<String, String> reserveUsers = new HashMap<String, String>();
		Map<String, String> teachers = new HashMap<String, String>();
		Map<String, String> manageUsers = new HashMap<String, String>();
		Map<Integer, String> labrooms = new HashMap<Integer, String>();
		for (LabRoomDeviceReservation labRoomDeviceReservation : reservationList) {
			boolean isUnderTeacherAudit = labRoomDeviceReservationService.isUnderTeacherAudit(labRoomDeviceReservation);
			boolean isUnderManagerAudit = labRoomDeviceReservationService.isUnderManagerAudit(labRoomDeviceReservation);
			boolean isUnderLabManagerAudit = labRoomDeviceReservationService.isUnderLabManagerAudit(labRoomDeviceReservation);
			mav.addObject("isUnderTeacherAudit", isUnderTeacherAudit);
			mav.addObject("isUnderManagerAudit", isUnderManagerAudit);
			mav.addObject("isUnderLabManagerAudit", isUnderLabManagerAudit);
			labRoomDeviceReservation.setTag(0);// 将tag初始化为一个不相关的值，防止后续查找到脏数据。
			// 当前登录人符合导师审核条件
			if (labRoomDeviceReservation.getUserByTeacher() != null && user.getUsername() == labRoomDeviceReservation.getUserByTeacher().getUsername() && isUnderTeacherAudit) {
				labRoomDeviceReservation.setTag(1);// 满足导师审核条件
				labRoomDeviceReservationDAO.store(labRoomDeviceReservation);
			}

			// 当前登录人符合实验室管理员审核条件
			if (labRoomDeviceReservation.getLabRoomDevice().getLabRoom().getLabRoomAdmins().toString().indexOf(username) != -1 && isUnderLabManagerAudit) {
				labRoomDeviceReservation.setTag(2);// 满足实验室管理员审核条件
				labRoomDeviceReservationDAO.store(labRoomDeviceReservation);
			}
			// 当前登录人符合设备管理员审核条件
			if (labRoomDeviceReservation.getLabRoomDevice().getUser() != null && user.getUsername() == labRoomDeviceReservation.getLabRoomDevice().getUser().getUsername() && isUnderManagerAudit) {
				labRoomDeviceReservation.setTag(3);// 满足设备管理员审核条件
				labRoomDeviceReservationDAO.store(labRoomDeviceReservation);
			}
			reserveUsers.put(labRoomDeviceReservation.getUserByReserveUser().getUsername(), labRoomDeviceReservation.getUserByReserveUser().getCname());
			if (labRoomDeviceReservation.getUserByTeacher() != null) {
				teachers.put(labRoomDeviceReservation.getUserByTeacher().getUsername(), labRoomDeviceReservation.getUserByTeacher().getCname());
			}
			if (labRoomDeviceReservation.getLabRoomDevice().getUser() != null) {
				manageUsers.put(labRoomDeviceReservation.getLabRoomDevice().getUser().getUsername(), labRoomDeviceReservation.getLabRoomDevice().getUser().getCname());
			}
			labrooms.put(labRoomDeviceReservation.getLabRoomDevice().getLabRoom().getId(), labRoomDeviceReservation.getLabRoomDevice().getLabRoom().getLabRoomName());
		}
		mav.addObject("reserveUsers", reserveUsers);
		mav.addObject("teachers", teachers);
		mav.addObject("manageUsers", manageUsers);
		mav.addObject("labrooms", labrooms);

		mav.addObject("pageModel", pageModel);
		mav.addObject("totalRecords", totalRecords);
		mav.addObject("page", page);
		mav.addObject("pageSize", pageSize);
		mav.addObject("cid", centerId);

		mav.setViewName("/device/lab_room_device_reservation/reservationList.jsp");
		return mav;
	}

	/****************************************************************************
	 * 功能：删除设备预约 作者：贺子龙
	 ****************************************************************************/
	@RequestMapping("/device/deleteReservation")
	public String deleteReservation(@RequestParameters Integer id) {
		LabRoomDeviceReservation labRoomDeviceReservation = labRoomDeviceReservationDAO.findLabRoomDeviceReservationByPrimaryKey(id);
		if (labRoomDeviceReservation != null) {

			labRoomDeviceReservationDAO.remove(labRoomDeviceReservation);
			labRoomDeviceReservationDAO.flush();
		}
		return "redirect:/device/reservationList?page=1";
	}

	/****************************************************************************
	 * 功能：实验室设备管理---设备预约管理---审核中 作者：李小龙
	 * 
	 * @throws ParseException
	 ****************************************************************************/
	@RequestMapping("/device/auditReservationList")
	public ModelAndView auditReservationList(@ModelAttribute LabRoomDeviceReservation reservation, @RequestParam Integer page, @ModelAttribute("selected_labCenter") Integer centerId) throws ParseException {
		// 新建ModelAndView对象；
		ModelAndView mav = new ModelAndView();
		// 当前登陆人信息
		User user = shareService.getUser();
		String username = "[" + user.getUsername() + "]";
		// 学期
		List<SchoolTerm> terms = shareService.findAllSchoolTerms();
		mav.addObject("terms", terms);
		// 中心所属实验室
		mav.addObject("reservation", reservation);
		int cId = 1;// 审核中
		// 根据设备预约信息和审核状态查询设备预约
		List<LabRoomDeviceReservation> allReservationList = labRoomDeviceService.findAllLabRoomDeviceReservation(reservation, cId, centerId, 1, -1);
		// 实验室
		Map<Integer, String> labrooms = new HashMap<Integer, String>();
		for (LabRoomDeviceReservation labRoomDeviceReservation : allReservationList) {
			// 更新审核结果
			int deviceId = labRoomDeviceReservation.getLabRoomDevice().getId();
			labRoomDeviceService.updateReservationResult(deviceId);

			boolean isUnderTeacherAudit = labRoomDeviceReservationService.isUnderTeacherAudit(labRoomDeviceReservation);
			boolean isUnderManagerAudit = labRoomDeviceReservationService.isUnderManagerAudit(labRoomDeviceReservation);
			boolean isUnderLabManagerAudit = labRoomDeviceReservationService.isUnderLabManagerAudit(labRoomDeviceReservation);
			mav.addObject("isUnderTeacherAudit", isUnderTeacherAudit);
			mav.addObject("isUnderManagerAudit", isUnderManagerAudit);
			mav.addObject("isUnderLabManagerAudit", isUnderLabManagerAudit);
			labRoomDeviceReservation.setTag(0);// 将tag初始化为一个不相关的值，防止后续查找到脏数据。
			// 当前登录人符合导师审核条件
			if (labRoomDeviceReservation.getUserByTeacher() != null && user.getUsername() == labRoomDeviceReservation.getUserByTeacher().getUsername() && isUnderTeacherAudit) {
				labRoomDeviceReservation.setTag(1);// 满足导师审核条件
				labRoomDeviceReservationDAO.store(labRoomDeviceReservation);
			}

			// 当前登录人符合实验室管理员审核条件
			if (labRoomDeviceReservation.getLabRoomDevice().getLabRoom().getLabRoomAdmins().toString().indexOf(username) != -1 && isUnderLabManagerAudit) {
				labRoomDeviceReservation.setTag(2);// 满足实验室管理员审核条件
				labRoomDeviceReservationDAO.store(labRoomDeviceReservation);
			}
			// 当前登录人符合设备管理员审核条件
			if (labRoomDeviceReservation.getLabRoomDevice().getUser() != null && user.getUsername() == labRoomDeviceReservation.getLabRoomDevice().getUser().getUsername() && isUnderManagerAudit) {
				labRoomDeviceReservation.setTag(3);// 满足设备管理员审核条件
				labRoomDeviceReservationDAO.store(labRoomDeviceReservation);
			}
			labrooms.put(labRoomDeviceReservation.getLabRoomDevice().getLabRoom().getId(), labRoomDeviceReservation.getLabRoomDevice().getLabRoom().getLabRoomName());
		}
		mav.addObject("rooms", labrooms);
		int totalRecords = allReservationList.size();
		int pageSize = 10;// 每页10条记录
		// 分页信息
		Map<String, Integer> pageModel = shareService.getPage(page, pageSize, totalRecords);
		// 根据分页信息查询出来的记录
		List<LabRoomDeviceReservation> reservationList = labRoomDeviceService.findAllLabRoomDeviceReservation(reservation, cId, centerId, page, pageSize);
		mav.addObject("reservationList", reservationList);

		mav.addObject("pageModel", pageModel);
		mav.addObject("totalRecords", totalRecords);
		mav.addObject("page", page);
		mav.addObject("pageSize", pageSize);
		mav.addObject("cid", centerId);

		mav.setViewName("/device/lab_room_device_reservation/auditReservationList.jsp");
		return mav;
	}

	/****************************************************************************
	 * 功能：实验室设备管理---设备预约管理---审核通过 作者：李小龙
	 ****************************************************************************/
	@RequestMapping("/device/passReservationList")
	public ModelAndView passReservationList(@ModelAttribute LabRoomDeviceReservation reservation, @RequestParam Integer page, @ModelAttribute("selected_labCenter") Integer centerId) {
		// 新建ModelAndView对象；
		ModelAndView mav = new ModelAndView();
		// 学期
		List<SchoolTerm> terms = shareService.findAllSchoolTerms();
		mav.addObject("terms", terms);
		// 中心所属实验室
		mav.addObject("reservation", reservation);
		int cId = 2;// 审核通过
		// 根据设备预约信息和审核状态查询设备预约
		List<LabRoomDeviceReservation> allReservationList = labRoomDeviceService.findAllLabRoomDeviceReservation(reservation, cId, centerId, 1, -1);
		int totalRecords = allReservationList.size();
		int pageSize = 10;// 每页10条记录
		// 分页信息
		Map<String, Integer> pageModel = shareService.getPage(page, pageSize, totalRecords);
		// 根据分页信息查询出来的记录
		List<LabRoomDeviceReservation> reservationList = labRoomDeviceService.findAllLabRoomDeviceReservation(reservation, cId, centerId, page, pageSize);
		mav.addObject("reservationList", reservationList);
		Map<Integer, String> labrooms = new HashMap<Integer, String>();
		for (LabRoomDeviceReservation labRoomDeviceReservation : allReservationList) {
			labrooms.put(labRoomDeviceReservation.getLabRoomDevice().getLabRoom().getId(), labRoomDeviceReservation.getLabRoomDevice().getLabRoom().getLabRoomName());
		}
		mav.addObject("rooms", labrooms);
		mav.addObject("pageModel", pageModel);
		mav.addObject("totalRecords", totalRecords);
		mav.addObject("page", page);
		mav.addObject("pageSize", pageSize);
		mav.addObject("cid", centerId);

		mav.setViewName("/device/lab_room_device_reservation/passReservationList.jsp");
		return mav;
	}

	/****************************************************************************
	 * 功能：实验室设备管理---设备预约管理---审核拒绝 作者：李小龙
	 ****************************************************************************/
	@RequestMapping("/device/rejectedReservationList")
	public ModelAndView rejectedReservationList(@ModelAttribute LabRoomDeviceReservation reservation, @RequestParam Integer page, @ModelAttribute("selected_labCenter") Integer centerId) {
		// 新建ModelAndView对象；
		ModelAndView mav = new ModelAndView();
		// 学期
		List<SchoolTerm> terms = shareService.findAllSchoolTerms();
		mav.addObject("terms", terms);
		// 中心所属实验室
		mav.addObject("reservation", reservation);
		int cId = 3;// 审核通过
		// 根据设备预约信息和审核状态查询设备预约
		List<LabRoomDeviceReservation> allReservationList = labRoomDeviceService.findAllLabRoomDeviceReservation(reservation, cId, centerId, 1, -1);
		int totalRecords = allReservationList.size();
		int pageSize = 10;// 每页10条记录
		// 分页信息
		Map<String, Integer> pageModel = shareService.getPage(page, pageSize, totalRecords);
		// 根据分页信息查询出来的记录
		List<LabRoomDeviceReservation> reservationList = labRoomDeviceService.findAllLabRoomDeviceReservation(reservation, cId, centerId, page, pageSize);
		mav.addObject("reservationList", reservationList);
		Map<Integer, String> labrooms = new HashMap<Integer, String>();
		for (LabRoomDeviceReservation labRoomDeviceReservation : allReservationList) {
			labrooms.put(labRoomDeviceReservation.getLabRoomDevice().getLabRoom().getId(), labRoomDeviceReservation.getLabRoomDevice().getLabRoom().getLabRoomName());
		}
		mav.addObject("rooms", labrooms);
		mav.addObject("pageModel", pageModel);
		mav.addObject("totalRecords", totalRecords);
		mav.addObject("page", page);
		mav.addObject("cid", centerId);
		mav.addObject("pageSize", pageSize);

		mav.setViewName("/device/lab_room_device_reservation/rejectedReservationList.jsp");
		return mav;
	}

	/****************************************************************************
	 * 设备预约审核--导师 作者：李小龙
	 ****************************************************************************/
	@RequestMapping("/device/teacherReservationAudit")
	public ModelAndView teacherReservationAudit(@RequestParam Integer id) {
		// 新建ModelAndView对象；
		ModelAndView mav = new ModelAndView();
		// id对应的预约
		LabRoomDeviceReservation reservation = labRoomDeviceReservationDAO.findLabRoomDeviceReservationByPrimaryKey(id);
		mav.addObject("reservation", reservation);
		// 是否处于导师审核阶段下
		boolean isUnderTeacherAudit = labRoomDeviceReservationService.isUnderTeacherAudit(reservation);
		mav.addObject("isUnderTeacherAudit", isUnderTeacherAudit);
		// 当前登录人
		User user = shareService.getUser();
		mav.addObject("user", user);
		// 教师审核结果
		// mav.addObject("audit",
		// labRoomDeviceService.findLabRoomDeviceReservationResult(id,reservation.getUserByTeacher().getUsername()));
		mav.addObject("audit", new LabRoomDeviceReservationResult());
		// 结果
		//Set<CTrainingResult> results = cTrainingResultDAO.findAllCTrainingResults();
		List<CDictionary> results = shareService.getCDictionaryData("c_training_result");
		mav.addObject("results", results);

		// 导师审核结果 贺子龙
		Set<LabRoomDeviceReservationResult> teacherAudits = reservation.getLabRoomDeviceReservationResults();// 该预约对应的所有审核结果
		// 新建一个整型list，用来存放所有的audits的Id
		if (teacherAudits.size() > 0) {

			List<Integer> auditsListId = new ArrayList<Integer>();
			for (LabRoomDeviceReservationResult labRoomDeviceReservationResult : teacherAudits) {
				if (labRoomDeviceReservationResult.getTag() == 1) {
					auditsListId.add(labRoomDeviceReservationResult.getId());// 将tag条件的id加入到auditsListId中
				}
			}
			Collections.sort(auditsListId);// 将auditsListId从小到大排序
			if (auditsListId.size() > 0) {
				int auditId = auditsListId.get(auditsListId.size() - 1);// 取最后一个，确保是最后一次导师审核（针对打回去的情况）
				LabRoomDeviceReservationResult teacherAudit = labRoomDeviceReservationResultDAO.findLabRoomDeviceReservationResultByPrimaryKey(auditId);
				mav.addObject("teacherAudit", teacherAudit);
			}
		}

		mav.addObject("isRest", 0);
		// 再将状态信息传递给新的页面
		mav.addObject("schoolTermId", -1);
		mav.addObject("labRoomId", -1);
		mav.addObject("deviceName", "-1");
		mav.addObject("page", 1);

		mav.setViewName("/device/lab_room_device_reservation/teacherReservationAudit.jsp");
		return mav;
	}

	/****************************************************************************
	 * 设备预约审核--实验室管理员 作者：贺子龙
	 ****************************************************************************/
	@RequestMapping("/device/labManagerReservationAudit")
	public ModelAndView labManagerReservationAudit(@RequestParam Integer id) {
		// 新建ModelAndView对象；
		ModelAndView mav = new ModelAndView();
		// id对应的预约
		LabRoomDeviceReservation reservation = labRoomDeviceReservationDAO.findLabRoomDeviceReservationByPrimaryKey(id);
		mav.addObject("reservation", reservation);
		// 是否处于实验室管理员审核阶段下
		boolean isUnderLabManagerAudit = labRoomDeviceReservationService.isUnderLabManagerAudit(reservation);
		mav.addObject("isUnderLabManagerAudit", isUnderLabManagerAudit);
		// 当前登录人
		User user = shareService.getUser();
		String username = user.getUsername();
		username = "[" + username + "]";
		mav.addObject("user", user);
		mav.addObject("username", username);
		// 管理员审核结果
		// mav.addObject("audit",
		// labRoomDeviceService.findManageAudit(id,reservation.getUserByTeacher().getUsername()));
		mav.addObject("audit", new LabRoomDeviceReservationResult());
		// 结果
		//Set<CTrainingResult> results = cTrainingResultDAO.findAllCTrainingResults();
		List<CDictionary> results = shareService.getCDictionaryData("c_training_result");
		mav.addObject("results", results);

		// 管理员审核结果 贺子龙
		Set<LabRoomDeviceReservationResult> labManageAudits = reservation.getLabRoomDeviceReservationResults();// 该预约对应的所有审核结果
		// 新建一个整型list，用来存放所有的audits的Id
		if (labManageAudits.size() > 0) {

			List<Integer> auditsListId = new ArrayList<Integer>();
			for (LabRoomDeviceReservationResult labRoomDeviceReservationResult : labManageAudits) {
				if (labRoomDeviceReservationResult.getTag() == 2) {
					auditsListId.add(labRoomDeviceReservationResult.getId());// 将tag条件的id加入到auditsListId中
				}
			}
			Collections.sort(auditsListId);// 将auditsListId从小到大排序
			if (auditsListId.size() > 0) {

				int auditId = auditsListId.get(auditsListId.size() - 1);// 取最后一个，确保是最后一次设备管理员审核（针对打回去的情况）
				LabRoomDeviceReservationResult labManageAudit = labRoomDeviceReservationResultDAO.findLabRoomDeviceReservationResultByPrimaryKey(auditId);
				mav.addObject("labManageAudit", labManageAudit);
			}
		}

		// 再将状态信息传递给新的页面
		mav.addObject("schoolTermId", -1);
		mav.addObject("labRoomId", -1);
		mav.addObject("deviceName", "-1");
		mav.addObject("page", 1);
		mav.addObject("isRest", 0);

		mav.setViewName("/device/lab_room_device_reservation/labManagerReservationAudit.jsp");
		return mav;
	}

	/****************************************************************************
	 * 设备预约审核--设备管理员 作者：李小龙
	 ****************************************************************************/
	@RequestMapping("/device/managerReservationAudit")
	public ModelAndView managerReservationAudit(@RequestParam Integer id) {
		// 新建ModelAndView对象；
		ModelAndView mav = new ModelAndView();
		// id对应的预约
		LabRoomDeviceReservation reservation = labRoomDeviceReservationDAO.findLabRoomDeviceReservationByPrimaryKey(id);
		mav.addObject("reservation", reservation);
		// 是否处于设备管理员审核阶段下
		boolean isUnderManagerAudit = labRoomDeviceReservationService.isUnderManagerAudit(reservation);
		mav.addObject("isUnderManagerAudit", isUnderManagerAudit);
		// 当前登录人
		User user = shareService.getUser();
		mav.addObject("user", user);
		// 管理员审核结果
		// mav.addObject("audit",
		// labRoomDeviceService.findManageAudit(id,reservation.getUserByTeacher().getUsername()));
		mav.addObject("audit", new LabRoomDeviceReservationResult());
		// 结果
		//Set<CTrainingResult> results = cTrainingResultDAO.findAllCTrainingResults();
		List<CDictionary> results = shareService.getCDictionaryData("c_training_result");
		mav.addObject("results", results);

		// 管理员审核结果 贺子龙
		Set<LabRoomDeviceReservationResult> manageAudits = reservation.getLabRoomDeviceReservationResults();// 该预约对应的所有审核结果
		// 新建一个整型list，用来存放所有的audits的Id
		if (manageAudits.size() > 0) {

			List<Integer> auditsListId = new ArrayList<Integer>();
			for (LabRoomDeviceReservationResult labRoomDeviceReservationResult : manageAudits) {
				if (labRoomDeviceReservationResult.getTag() == 3) {
					auditsListId.add(labRoomDeviceReservationResult.getId());// 将tag条件的id加入到auditsListId中
				}
			}
			Collections.sort(auditsListId);// 将auditsListId从小到大排序
			if (auditsListId.size() > 0) {

				int auditId = auditsListId.get(auditsListId.size() - 1);// 取最后一个，确保是最后一次设备管理员审核（针对打回去的情况）
				LabRoomDeviceReservationResult manageAudit = labRoomDeviceReservationResultDAO.findLabRoomDeviceReservationResultByPrimaryKey(auditId);
				mav.addObject("manageAudit", manageAudit);
			}
		}

		// 再将状态信息传递给新的页面
		mav.addObject("schoolTermId", -1);
		mav.addObject("labRoomId", -1);
		mav.addObject("deviceName", "-1");
		mav.addObject("page", 1);
		mav.addObject("isRest", 0);

		mav.setViewName("/device/lab_room_device_reservation/managerReservationAudit.jsp");
		return mav;
	}

	/****************************************************************************
	 * 保存导师审核 作者：李小龙
	 * 
	 * @throws ParseException
	 ****************************************************************************/
	@SuppressWarnings("unused")
	@RequestMapping("/device/saveTeacherAudit")
	public ModelAndView saveTeacherAudit(@ModelAttribute LabRoomDeviceReservationResult audit, @RequestParam Integer id) throws ParseException {
		// 新建ModelAndView对象；
		ModelAndView mav = new ModelAndView();
		// id对应的设备预约
		LabRoomDeviceReservation reservation = labRoomDeviceReservationDAO.findLabRoomDeviceReservationByPrimaryKey(id);

		audit.setLabRoomDeviceReservation(reservation);

		// System.out.println(request.getParameter("begintime"));
		audit.setTag(1);// 1为导师审核
		// 当前登录人为审核人
		User user = shareService.getUser();
		audit.setUser(user);
		// 给审核意见之后加上审核人信息
		String remark = audit.getRemark();
		remark += "        审核人姓名：" + user.getCname() + "      审核人工号：" + user.getUsername();
		audit.setRemark(remark);
		// 审核结果
		int resultId = audit.getCTrainingResult().getId();
		if (resultId == 620) {
			labRoomDeviceService.alterTimeAfterRefused(reservation, 0);
		}
		if (resultId == 619) {
			reservation.setStage(reservation.getStage() + 1);
			// 审核通过,判断是否需要管理员审核
			LabRoomDevice device = reservation.getLabRoomDevice();

			if (device.getCActiveByLabManagerAudit() != null) {
				int lmId = Integer.parseInt(device.getCActiveByLabManagerAudit().getCNumber());
				if (lmId == 1) {// 需要实验室管理员审核
				}
				if (lmId == 2) {// 不需要实验室管理员审核---直接通过审核
					/*if (device.getCActiveByManagerAudit() != null) {
						int mId = device.getCActiveByManagerAudit().getId();
						if (mId == 1) {// 需要设备管理员审核
						}
						if (mId == 2) {// 不需要设备管理员审核---直接通过审核
							CAuditResult r = cAuditResultDAO.findCAuditResultByPrimaryKey(2);
							reservation.setCAuditResult(r);
						}
					}*/
					if(device.getCDictionaryByManagerAudit()!=null){
						//int mId=device.getCActiveByManagerAudit().getId();
						String mId = device.getCDictionaryByManagerAudit().getCNumber();
						if("1".equals(mId)){//需要设备管理员审核
						}
						if("2".equals(mId)){//不需要设备管理员审核---直接通过审核
							//CAuditResult r=cAuditResultDAO.findCAuditResultByPrimaryKey(2);
							CDictionary r = shareService.getCDictionaryByCategory("c_audit_result", "2");
							reservation.setCAuditResult(r);
						}
					}
				}
			}

		}
		LabRoomDeviceReservation reservationOri = labRoomDeviceReservationDAO.store(reservation);
		//CTrainingResult result = cTrainingResultDAO.findCTrainingResultByPrimaryKey(resultId);
		audit.setAuditTime(Calendar.getInstance());// 审核时间
		LabRoomDeviceReservationResult auditOri = labRoomDeviceReservationResultDAO.store(audit);
		// 将审核意见和预约状态同步到关联设备
        List<LabRoomDeviceReservation> reservations = labRoomDeviceReservationService.findInnerSame(id);
        if (reservations!=null&&reservations.size()>0) {
            for (LabRoomDeviceReservation labRoomDeviceReservation : reservations) {
                // 同步预约状态
                labRoomDeviceReservation.copy(reservationOri);
                // 同步审核意见
                LabRoomDeviceReservationResult auditSame = new LabRoomDeviceReservationResult();
                auditSame.copy(auditOri);
                auditSame.setLabRoomDeviceReservation(labRoomDeviceReservation);
                auditSame.setAuditTime(Calendar.getInstance());// 审核时间
                labRoomDeviceReservationResultDAO.store(auditSame);
            }
        }
		mav.setViewName("redirect:/device/reservationList?page=1");
		return mav;
	}

	/****************************************************************************
	 * 功能：判断设备预约与现有的设备预约是否冲突 作者：贺子龙 时间：2015-10-28
	 * 
	 * @throws ParseException
	 ****************************************************************************/
	@RequestMapping("/device/judgeConfliction")
	public @ResponseBody String judgeConfliction(@RequestParam Integer id, HttpServletRequest request) throws ParseException {
		// System.out.println(request.getParameter("begintime"));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date date = sdf.parse(request.getParameter("begintime"));// 从前台将begintime取出来，然后转化成calendar格式
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date date1 = sdf1.parse(request.getParameter("endtime"));// 从前台将endtime取出来，然后转化成calendar格式
		Calendar calendar1 = Calendar.getInstance();
		calendar1.setTime(date1);
		// id对应的设备预约
		LabRoomDeviceReservation reservation = labRoomDeviceReservationDAO.findLabRoomDeviceReservationByPrimaryKey(id);
		// 根据设备id查询设备的预约记录
		List<LabRoomDeviceReservation> reservationList = labRoomDeviceService.findReservationListByDeviceId(reservation.getLabRoomDevice().getId());
		int flag = 1;// 标记为0为失败，1为成功
		// 循环遍历预约记录，看是否和以前的预约有冲突
		for (LabRoomDeviceReservation r : reservationList) {
			Calendar start = r.getBegintime();
			Calendar end = r.getEndtime();
			if (r.getId() != reservation.getId()) {// 不要跟自己判冲
				if (end.after(calendar) && start.before(calendar1)) {
					flag = 0;
					break;
				}
			}

		}
		if (flag == 1) {
			reservation.setBegintime(calendar);
			reservation.setEndtime(calendar1);
			labRoomDeviceReservationDAO.store(reservation);
			return "success";
		} else {
			return "error";
		}

	}

	/****************************************************************************
	 * 保存实验室管理员审核 作者：李小龙
	 * 
	 * @throws ParseException
	 ****************************************************************************/
	@SuppressWarnings("unused")
	@RequestMapping("/device/saveLabManagerAudit")
	public ModelAndView saveLabManagerAudit(@ModelAttribute LabRoomDeviceReservationResult audit, @RequestParam Integer id) throws ParseException {
		// 新建ModelAndView对象；
		ModelAndView mav = new ModelAndView();
		// id对应的设备预约
		LabRoomDeviceReservation reservation = labRoomDeviceReservationDAO.findLabRoomDeviceReservationByPrimaryKey(id);
		audit.setLabRoomDeviceReservation(reservation);
		audit.setTag(2);// 2为实验室管理员审核
		// 当前登录人为审核人
		User user = shareService.getUser();
		audit.setUser(user);
		// 给审核意见之后加上审核人信息
		String remark = audit.getRemark();
		remark += "        审核人姓名：" + user.getCname() + "      审核人工号：" + user.getUsername();
		audit.setRemark(remark);
		// 审核结果
		int resultId = audit.getCTrainingResult().getId();
		if (resultId == 620) {
			// 审核拒绝
			//CAuditResult r = cAuditResultDAO.findCAuditResultByPrimaryKey(3);
			CDictionary r = shareService.getCDictionaryByCategory("c_audit_result", "3");
			reservation.setCAuditResult(r);
			reservation.setStage(-1);
			reservation.setOriginalBegin(reservation.getBegintime());// 在被赋值为1900-01-01之前，把本身的值保存在original中
			reservation.setOriginalEnd(reservation.getEndtime());
			String str = "1900-01-01";
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = sdf.parse(str);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			reservation.setBegintime(calendar);
			reservation.setEndtime(calendar);
			labRoomDeviceReservationDAO.store(reservation);
		}
		if (resultId == 619) {
			reservation.setStage(reservation.getStage() + 1);
			// 审核通过,判断是否需要设备管理员审核
			LabRoomDevice device = reservation.getLabRoomDevice();
			/*if (device.getCActiveByManagerAudit() != null) {
				int lmId = device.getCActiveByManagerAudit().getId();
				if (lmId == 1) {// 需要设备管理员审核
				}
				if (lmId == 2) {// 不需要设备管理员审核
					// 审核通过
					CAuditResult r = cAuditResultDAO.findCAuditResultByPrimaryKey(2);
					reservation.setCAuditResult(r);

				}
			}*/
			if(device.getCDictionaryByManagerAudit()!=null){
				//int mId=device.getCActiveByManagerAudit().getId();
				String mId = device.getCDictionaryByManagerAudit().getCNumber();
				if("1".equals(mId)){//需要设备管理员审核
				}
				if("2".equals(mId)){//不需要设备管理员审核---直接通过审核
					//CAuditResult r=cAuditResultDAO.findCAuditResultByPrimaryKey(2);
					CDictionary r = shareService.getCDictionaryByCategory("c_audit_result", "2");
					reservation.setCAuditResult(r);
				}
			}
		}
		LabRoomDeviceReservation reservationOri = labRoomDeviceReservationDAO.store(reservation);
		//CTrainingResult result = cTrainingResultDAO.findCTrainingResultByPrimaryKey(resultId);
		audit.setAuditTime(Calendar.getInstance());// 审核时间
		LabRoomDeviceReservationResult auditOri = labRoomDeviceReservationResultDAO.store(audit);
		// 将审核意见和预约状态同步到关联设备
        List<LabRoomDeviceReservation> reservations = labRoomDeviceReservationService.findInnerSame(id);
        if (reservations!=null&&reservations.size()>0) {
            for (LabRoomDeviceReservation labRoomDeviceReservation : reservations) {
                // 同步预约状态
                labRoomDeviceReservation.copy(reservationOri);
                // 同步审核意见
                LabRoomDeviceReservationResult auditSame = new LabRoomDeviceReservationResult();
                auditSame.copy(auditOri);
                auditSame.setLabRoomDeviceReservation(labRoomDeviceReservation);
                auditSame.setAuditTime(Calendar.getInstance());// 审核时间
                labRoomDeviceReservationResultDAO.store(auditSame);
            }
        }
		mav.setViewName("redirect:/device/reservationList?page=1");
		return mav;
	}

	/****************************************************************************
	 * 保存设备管理员审核 作者：李小龙
	 * 
	 * @throws ParseException
	 ****************************************************************************/
	@SuppressWarnings("unused")
	@RequestMapping("/device/saveManagerAudit")
	public ModelAndView saveManagerAudit(@ModelAttribute LabRoomDeviceReservationResult audit, @RequestParam Integer id) throws ParseException {
		// 新建ModelAndView对象；
		ModelAndView mav = new ModelAndView();
		// id对应的设备预约
		LabRoomDeviceReservation reservation = labRoomDeviceReservationDAO.findLabRoomDeviceReservationByPrimaryKey(id);
		audit.setLabRoomDeviceReservation(reservation);
		audit.setTag(3);// 3为设备管理员审核
		// 当前登录人为审核人
		User user = shareService.getUser();
		audit.setUser(user);
		// 给审核意见之后加上审核人信息
		String remark = audit.getRemark();
		remark += "        审核人姓名：" + user.getCname() + "      审核人工号：" + user.getUsername();
		audit.setRemark(remark);
		// 审核结果
		int resultId = audit.getCTrainingResult().getId();
		if (resultId == 620) {
			// 审核拒绝
			//CAuditResult r = cAuditResultDAO.findCAuditResultByPrimaryKey(3);
			CDictionary r = shareService.getCDictionaryByCategory("c_audit_result", "3");
			reservation.setCAuditResult(r);
			reservation.setStage(-1);
			reservation.setOriginalBegin(reservation.getBegintime());// 在被赋值为1900-01-01之前，把本身的值保存在original中
			reservation.setOriginalEnd(reservation.getEndtime());
			String str = "1900-01-01";
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = sdf.parse(str);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			reservation.setBegintime(calendar);
			reservation.setEndtime(calendar);
			labRoomDeviceReservationDAO.store(reservation);
		}
		if (resultId == 619) {
			reservation.setStage(reservation.getStage() + 1);
			// 审核通过
			//CAuditResult r = cAuditResultDAO.findCAuditResultByPrimaryKey(2);
			CDictionary r = shareService.getCDictionaryByCategory("c_audit_result", "2");
			reservation.setCAuditResult(r);
		}
		LabRoomDeviceReservation reservationOri = labRoomDeviceReservationDAO.store(reservation);
		//CTrainingResult result = cTrainingResultDAO.findCTrainingResultByPrimaryKey(resultId);
		audit.setAuditTime(Calendar.getInstance());// 审核时间
		LabRoomDeviceReservationResult auditOri = labRoomDeviceReservationResultDAO.store(audit);
		// 将审核意见和预约状态同步到关联设备
        List<LabRoomDeviceReservation> reservations = labRoomDeviceReservationService.findInnerSame(id);
        if (reservations!=null&&reservations.size()>0) {
            for (LabRoomDeviceReservation labRoomDeviceReservation : reservations) {
                // 同步预约状态
                labRoomDeviceReservation.copy(reservationOri);
                // 同步审核意见
                LabRoomDeviceReservationResult auditSame = new LabRoomDeviceReservationResult();
                auditSame.copy(auditOri);
                auditSame.setLabRoomDeviceReservation(labRoomDeviceReservation);
                auditSame.setAuditTime(Calendar.getInstance());// 审核时间
                labRoomDeviceReservationResultDAO.store(auditSame);
            }
        }
		mav.setViewName("redirect:/device/reservationList?page=1");
		return mav;
	}

	/****************************************************************************
	 * @功能：保存管理员审核
	 * @作者：魏诚 @时间：2015-10-08
	 ****************************************************************************/
	@RequestMapping("/device/setAuditValidTime")
	public ModelAndView setAuditValidTime() {
		// 新建ModelAndView对象；
		ModelAndView mav = new ModelAndView();
		CStaticValue cStaticValue = new CStaticValue();
		// 获取类型为设备预约审核有效时间device_valid_time的字典，并取出第一个值
		for (CStaticValue cStaticValueTmp : cStaticValueDAO.findCStaticValueByCode("device_valid_time")) {
			if (cStaticValueTmp.getId() != null) {
				cStaticValue = cStaticValueTmp;
			}
		}
		// 赋予变量cStaticValue到前端
		mav.addObject("cStaticValue", cStaticValue);

		mav.setViewName("/device/setAuditValidTime.jsp");
		return mav;
	}

	/****************************************************************************
	 * @功能：批量审核修改时间
	 * @作者：贺子龙 @时间：2016-01-13
	 ****************************************************************************/
	@RequestMapping("/device/alterTime")
	public ModelAndView alterTime(@RequestParam int id) {
		// 新建ModelAndView对象；
		ModelAndView mav = new ModelAndView();
		// id对应的设备预约
		LabRoomDeviceReservation reservation = labRoomDeviceReservationDAO.findLabRoomDeviceReservationByPrimaryKey(id);
		mav.addObject("reservation", reservation);
		mav.setViewName("/device/lab_room_device_reservation/alterReservationTime.jsp");
		return mav;
	}

	/****************************************************************************
	 * @功能：保存预约审核有效时间
	 * @作者：姜新剑 @时间：2015-10-12
	 ****************************************************************************/
	@RequestMapping("/device/saveCStaticValue")
	public ModelAndView saveCStaticValue(@ModelAttribute CStaticValue csv) {
		// 新建ModelAndView对象；
		ModelAndView mav = new ModelAndView();
		CStaticValue cStaticValue = new CStaticValue();
		// 获取类型为设备预约审核有效时间device_valid_time的字典，并取出第一个值
		for (CStaticValue cStaticValueTmp : cStaticValueDAO.findCStaticValueByCode("device_valid_time")) {
			if (cStaticValueTmp.getId() != null) {
				cStaticValue = cStaticValueTmp;
			}
		}
		cStaticValue.setStaticValue(csv.getStaticValue());
		cStaticValueDAO.store(cStaticValue);
		mav.addObject("cStaticValue", cStaticValue);
		mav.setViewName("/device/setAuditValidTime.jsp");
		return mav;
	}

	/**
	 * 
	 * @comment：我的审核页面
	 * @param reservation、page、centerId
	 * @throws ParseException
	 * @return：
	 * @author：叶明盾 @date：2015-10-29 下午4:44:18
	 */
	@RequestMapping("/device/myReservationList")
	public ModelAndView myReservationList(@ModelAttribute LabRoomDeviceReservation reservation, @RequestParam Integer page, @ModelAttribute("selected_labCenter") Integer centerId) throws ParseException {
		// 新建ModelAndView对象；
		ModelAndView mav = new ModelAndView();
		// 当前登陆人信息
		User user = shareService.getUser();
		String username = "[" + user.getUsername() + "]";
		// 学期
		List<SchoolTerm> terms = shareService.findAllSchoolTerms();
		mav.addObject("terms", terms);
		// 中心所属实验室
		Map<Integer, String> rooms = labRoomDeviceService.findAllLabrooms(centerId);
		mav.addObject("rooms", rooms);
		mav.addObject("reservation", reservation);
		int cId = 1;// 审核中
		// 根据设备预约信息和审核状态查询设备预约
		List<LabRoomDeviceReservation> allReservationList = labRoomDeviceService.findAllLabRoomDeviceReservation(reservation, cId, centerId, 1, -1);
		int totalRecords = 0;
		for (LabRoomDeviceReservation labRoomDeviceReservation : allReservationList) {
			// 更新审核结果
			int deviceId = labRoomDeviceReservation.getLabRoomDevice().getId();
			labRoomDeviceService.updateReservationResult(deviceId);

			boolean isUnderTeacherAudit = labRoomDeviceReservationService.isUnderTeacherAudit(labRoomDeviceReservation);
			boolean isUnderManagerAudit = labRoomDeviceReservationService.isUnderManagerAudit(labRoomDeviceReservation);
			boolean isUnderLabManagerAudit = labRoomDeviceReservationService.isUnderLabManagerAudit(labRoomDeviceReservation);
			mav.addObject("isUnderTeacherAudit", isUnderTeacherAudit);
			mav.addObject("isUnderManagerAudit", isUnderManagerAudit);
			mav.addObject("isUnderLabManagerAudit", isUnderLabManagerAudit);
			labRoomDeviceReservation.setTag(0);// 将tag初始化为一个不相关的值，防止后续查找到脏数据。
			// 当前登录人符合导师审核条件
			if (labRoomDeviceReservation.getUserByTeacher() != null && user.getUsername() == labRoomDeviceReservation.getUserByTeacher().getUsername() && isUnderTeacherAudit) {
				labRoomDeviceReservation.setTag(1);// 满足导师审核条件
				labRoomDeviceReservationDAO.store(labRoomDeviceReservation);
				totalRecords++;
				continue;
			}

			// 当前登录人符合实验室管理员审核条件
			if (labRoomDeviceReservation.getLabRoomDevice().getLabRoom().getLabRoomAdmins().toString().indexOf(username) != -1 && isUnderLabManagerAudit) {
				labRoomDeviceReservation.setTag(2);// 满足实验室管理员审核条件
				labRoomDeviceReservationDAO.store(labRoomDeviceReservation);
				totalRecords++;
				continue;
			}
			// 当前登录人符合设备管理员审核条件
			if (labRoomDeviceReservation.getLabRoomDevice().getUser() != null && user.getUsername() == labRoomDeviceReservation.getLabRoomDevice().getUser().getUsername() && isUnderManagerAudit) {
				labRoomDeviceReservation.setTag(3);// 满足设备管理员审核条件
				labRoomDeviceReservationDAO.store(labRoomDeviceReservation);
				totalRecords++;
				continue;
			}
		}
		// int totalRecords=allReservationList.size();
		int pageSize = 10;// 每页10条记录
		// 分页信息
		Map<String, Integer> pageModel = shareService.getPage(page, pageSize, totalRecords);
		// 根据分页信息查询出来的记录
		List<LabRoomDeviceReservation> reservationList = labRoomDeviceService.findAllLabRoomDeviceReservation(reservation, cId, centerId, page, pageSize, 9);// 这里9只是tag的传参，没有实际意义
		mav.addObject("reservationList", reservationList);

		mav.addObject("pageModel", pageModel);
		mav.addObject("totalRecords", totalRecords);
		mav.addObject("page", page);
		mav.addObject("pageSize", pageSize);
		mav.addObject("cid", centerId);
		// 结果
		//Set<CTrainingResult> results = cTrainingResultDAO.findAllCTrainingResults();
		List<CDictionary> results = shareService.getCDictionaryData("c_training_result");
		mav.addObject("results", results);
		mav.addObject("isRest", 0);
		mav.setViewName("/device/lab_room_device_reservation/myReservationList.jsp");
		return mav;
	}

	/****************************************************************************
	 * 保存导师审核--批量 作者：贺子龙
	 ****************************************************************************/
	@RequestMapping("/device/saveTeacherAuditBatch")
	public ModelAndView saveTeacherAuditBatch(@RequestParam Integer resultId, int[] array) {
		// 新建ModelAndView对象；
		ModelAndView mav = new ModelAndView();
		for (int reservationId : array) {

			// id对应的设备预约
			LabRoomDeviceReservation reservation = labRoomDeviceReservationDAO.findLabRoomDeviceReservationByPrimaryKey(reservationId);
			LabRoomDeviceReservationResult audit = new LabRoomDeviceReservationResult();
			audit.setLabRoomDeviceReservation(reservation);
			audit.setCTrainingResult(shareService.getCDictionaryByCategory("c_training_result", String.valueOf(resultId)));
			audit.setTag(1);// 1为导师审核
			// 当前登录人为审核人
			User user = shareService.getUser();
			audit.setUser(user);
			// 给审核意见之后加上审核人信息
			String remark = "指导教师批量审核";
			remark += "        审核人姓名：" + user.getCname() + "      审核人工号：" + user.getUsername();
			audit.setRemark(remark);
			if (resultId == 620) {
				// 审核拒绝
				//CAuditResult r = cAuditResultDAO.findCAuditResultByPrimaryKey(3);
				CDictionary r = shareService.getCDictionaryByCategory("c_audit_result", "3");
				reservation.setCAuditResult(r);
				reservation.setStage(-1);
				labRoomDeviceReservationDAO.store(reservation);
			}
			if (resultId == 619) {
				reservation.setStage(reservation.getStage() + 1);
				// 审核通过,判断是否需要管理员审核
				LabRoomDevice device = reservation.getLabRoomDevice();

				if (device.getCActiveByLabManagerAudit() != null) {
					int lmId = Integer.parseInt(device.getCActiveByLabManagerAudit().getCNumber());
					if (lmId == 1) {// 需要实验室管理员审核
					}
					if (lmId == 2) {// 不需要实验室管理员审核---直接通过审核
						/*if (device.getCActiveByManagerAudit() != null) {
							int mId = device.getCActiveByManagerAudit().getId();
							if (mId == 1) {// 需要设备管理员审核
							}
							if (mId == 2) {// 不需要设备管理员审核---直接通过审核
								CAuditResult r = cAuditResultDAO.findCAuditResultByPrimaryKey(2);
								reservation.setCAuditResult(r);
							}
						}*/
						if(device.getCDictionaryByManagerAudit()!=null){
							//int mId=device.getCActiveByManagerAudit().getId();
							String mId = device.getCDictionaryByManagerAudit().getCNumber();
							if("1".equals(mId)){//需要设备管理员审核
							}
							if("2".equals(mId)){//不需要设备管理员审核---直接通过审核
								//CAuditResult r=cAuditResultDAO.findCAuditResultByPrimaryKey(2);
								CDictionary r = shareService.getCDictionaryByCategory("c_audit_result", "2");
								reservation.setCAuditResult(r);
							}
						}
					}
				}

				labRoomDeviceReservationDAO.store(reservation);
			}
			audit.setAuditTime(Calendar.getInstance());// 审核时间
			labRoomDeviceReservationResultDAO.store(audit);
		}

		mav.setViewName("redirect:/device/myReservationList?page=1");
		return mav;
	}

	/****************************************************************************
	 * 保存实验室管理员审核--批量 作者：李小龙
	 ****************************************************************************/
	@SuppressWarnings("unused")
	@RequestMapping("/device/saveLabManagerAuditBatch")
	public ModelAndView saveLabManagerAuditBatch(@RequestParam Integer resultId, int[] array) {
		// 新建ModelAndView对象；
		ModelAndView mav = new ModelAndView();
		for (int reservationId : array) {
			// id对应的设备预约
			LabRoomDeviceReservation reservation = labRoomDeviceReservationDAO.findLabRoomDeviceReservationByPrimaryKey(reservationId);
			LabRoomDeviceReservationResult audit = new LabRoomDeviceReservationResult();
			audit.setLabRoomDeviceReservation(reservation);
			audit.setTag(2);// 2为实验室管理员审核
			// 当前登录人为审核人
			User user = shareService.getUser();
			audit.setUser(user);
			// 给审核意见之后加上审核人信息
			String remark = "实验室管理员批量审核";
			remark += "        审核人姓名：" + user.getCname() + "      审核人工号：" + user.getUsername();
			audit.setRemark(remark);
			// 审核结果
			if (resultId == 620) {
				// 审核拒绝
				//CAuditResult r = cAuditResultDAO.findCAuditResultByPrimaryKey(3);
				CDictionary r = shareService.getCDictionaryByCategory("c_audit_result", "3");
				reservation.setCAuditResult(r);
				reservation.setStage(-1);
				labRoomDeviceReservationDAO.store(reservation);
			}
			if (resultId == 619) {
				reservation.setStage(reservation.getStage() + 1);
				// 审核通过,判断是否需要设备管理员审核
				LabRoomDevice device = reservation.getLabRoomDevice();
				if (device.getCActiveByLabManagerAudit() != null) {
					int lmId = Integer.parseInt(device.getCActiveByLabManagerAudit().getCNumber());
					if (lmId == 1) {// 需要设备管理员审核
					}
					if (lmId == 2) {// 不需要身边管理员审核
						// 审核通过
						//CAuditResult r = cAuditResultDAO.findCAuditResultByPrimaryKey(2);
						CDictionary r = shareService.getCDictionaryByCategory("c_audit_result", "2");
						reservation.setCAuditResult(r);

					}
				}
				labRoomDeviceReservationDAO.store(reservation);
			}
			//CTrainingResult result = cTrainingResultDAO.findCTrainingResultByPrimaryKey(resultId);
			audit.setAuditTime(Calendar.getInstance());// 审核时间
			labRoomDeviceReservationResultDAO.store(audit);
		}
		mav.setViewName("redirect:/device/myReservationList?page=1");
		return mav;
	}

	/****************************************************************************
	 * 保存设备管理员审核 作者：李小龙
	 ****************************************************************************/
	@SuppressWarnings("unused")
	@RequestMapping("/device/saveManagerAuditBatch")
	public ModelAndView saveManagerAuditBatch(@RequestParam Integer resultId, int[] array) {
		// 新建ModelAndView对象；
		ModelAndView mav = new ModelAndView();
		for (int reservationId : array) {

			// id对应的设备预约
			LabRoomDeviceReservation reservation = labRoomDeviceReservationDAO.findLabRoomDeviceReservationByPrimaryKey(reservationId);
			LabRoomDeviceReservationResult audit = new LabRoomDeviceReservationResult();
			audit.setLabRoomDeviceReservation(reservation);
			audit.setTag(3);// 3为设备管理员审核
			// 当前登录人为审核人
			User user = shareService.getUser();
			audit.setUser(user);
			// 给审核意见之后加上审核人信息
			String remark = "设备管理员批量审核";
			remark += "        审核人姓名：" + user.getCname() + "      审核人工号：" + user.getUsername();
			audit.setRemark(remark);
			// 审核结果
			if (resultId == 620) {
				// 审核拒绝
				//CAuditResult r = cAuditResultDAO.findCAuditResultByPrimaryKey(3);
				CDictionary r = shareService.getCDictionaryByCategory("c_audit_result", "3");
				reservation.setCAuditResult(r);
				reservation.setStage(-1);
				labRoomDeviceReservationDAO.store(reservation);
			}
			if (resultId == 619) {
				reservation.setStage(reservation.getStage() + 1);
				// 审核通过
				//CAuditResult r = cAuditResultDAO.findCAuditResultByPrimaryKey(2);
				CDictionary r = shareService.getCDictionaryByCategory("c_audit_result", "2");
				reservation.setCAuditResult(r);
				labRoomDeviceReservationDAO.store(reservation);
			}
			//CTrainingResult result = cTrainingResultDAO.findCTrainingResultByPrimaryKey(resultId);
			audit.setAuditTime(Calendar.getInstance());// 审核时间
			labRoomDeviceReservationResultDAO.store(audit);
		}
		mav.setViewName("redirect:/device/myReservationList?page=1");
		return mav;
	}

	/********************************
	 * 功能：批量修改设备--查询所选的设备 作者：贺子龙 日期：2015-11-02
	 *******************************/

	@RequestMapping("/device/findSelectedReservation")
	public @ResponseBody Map<String, Object> findSelectedReservation(@RequestParam int[] array) {
		List<LabRoomDeviceReservation> reservations = new ArrayList<LabRoomDeviceReservation>();
		for (int reservationId : array) {
			LabRoomDeviceReservation reservation = labRoomDeviceReservationDAO.findLabRoomDeviceReservationByPrimaryKey(reservationId);
			reservations.add(reservation);

		}
		Map<String, Object> map = new HashMap<String, Object>();
		List<ReservationList> list = new ArrayList<ReservationList>();
		for (LabRoomDeviceReservation r : reservations) {
			ReservationList rl = new ReservationList();
			// 预约id
			rl.setReservationId(r.getId());
			// 审核阶段
			rl.setReservationTag(r.getTag());
			// 设备名称
			rl.setDeviceName(r.getLabRoomDevice().getSchoolDevice().getDeviceName());
			// 申请人
			rl.setReserveUserName(r.getUserByReserveUser().getCname());
			// 导师
			String teacherName = "";
			if (r.getUserByTeacher() != null) {
				teacherName = r.getUserByTeacher().getCname();
			}
			rl.setTeacherName(teacherName);
			// 设备管理员
			String managerName = "";
			if (r.getLabRoomDevice().getUser() != null) {
				managerName = r.getLabRoomDevice().getUser().getCname();
			}
			rl.setDeviceManagerName(managerName);
			// 申请内容
			rl.setReservationContent(r.getContent());
			// 预约开始时间
			String beginTime = "";
			Calendar calendar = r.getBegintime();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			beginTime = sdf.format(calendar.getTime());
			rl.setBeginTime(beginTime);
			// 结束时间
			String endTime = "";
			Calendar calendarEnd = r.getEndtime();
			endTime = sdf.format(calendarEnd.getTime());
			rl.setEndTime(endTime);
			list.add(rl);
		}
		map.put("list", list);
		return map;
	}

	/****************************************************************************
	 * 保存批量审核--resTag 1 导师 2 实验室管理员 3设备管理员 作者：贺子龙
	 * 
	 * @throws ParseException
	 ****************************************************************************/
	@RequestMapping("/device/saveAuditResultDiff")
	public ModelAndView saveAuditResultDiff(@RequestParam Integer resId, String remark, Integer resultId, Integer resTag) throws ParseException {
		// 新建ModelAndView对象；
		ModelAndView mav = new ModelAndView();
		// id对应的设备预约
		LabRoomDeviceReservation reservation = labRoomDeviceReservationDAO.findLabRoomDeviceReservationByPrimaryKey(resId);
		LabRoomDeviceReservationResult audit = new LabRoomDeviceReservationResult();
		audit.setLabRoomDeviceReservation(reservation);
		audit.setTag(resTag);
		// 当前登录人为审核人
		User user = shareService.getUser();
		audit.setUser(user);
		// 给审核意见之后加上审核人信息
		remark += "        (我的审核)审核人姓名：" + user.getCname() + "      审核人工号：" + user.getUsername();
		audit.setRemark(remark);
		// 审核结果
		if (resultId == 620) {
			labRoomDeviceService.alterTimeAfterRefused(reservation, 0);
		}
		if (resultId == 619) {
			reservation.setStage(reservation.getStage() + 1);
			// 审核通过,判断是否需要管理员审核
			LabRoomDevice device = reservation.getLabRoomDevice();
			if (resTag == 1) {// 当前导师审核阶段

				if (device.getCActiveByLabManagerAudit() != null) {
					int lmId = Integer.parseInt(device.getCActiveByLabManagerAudit().getCNumber());
					if (lmId == 1) {// 需要实验室管理员审核
					}
					if (lmId == 2) {// 不需要实验室管理员审核---直接通过审核
						/*if (device.getCActiveByManagerAudit() != null) {
							int mId = device.getCActiveByManagerAudit().getId();
							if (mId == 1) {// 需要设备管理员审核
							}
							if (mId == 2) {// 不需要设备管理员审核---直接通过审核
								CAuditResult r = cAuditResultDAO.findCAuditResultByPrimaryKey(2);
								reservation.setCAuditResult(r);
							}
						}*/
						if(device.getCDictionaryByManagerAudit()!=null){
							//int mId=device.getCActiveByManagerAudit().getId();
							String mId = device.getCDictionaryByManagerAudit().getCNumber();
							if("1".equals(mId)){//需要设备管理员审核
							}
							if("2".equals(mId)){//不需要设备管理员审核---直接通过审核
								//CAuditResult r=cAuditResultDAO.findCAuditResultByPrimaryKey(2);
								CDictionary r = shareService.getCDictionaryByCategory("c_audit_result", "2");
								reservation.setCAuditResult(r);
							}
						}
					}
				}
			}
			if (resTag == 2) {// 当前实验室管理员审核阶段
				/*if (device.getCActiveByManagerAudit() != null) {
					int lmId = device.getCActiveByManagerAudit().getId();
					if (lmId == 1) {// 需要设备管理员审核
					}
					if (lmId == 2) {// 不需要设备管理员审核
						// 审核通过
						CAuditResult r = cAuditResultDAO.findCAuditResultByPrimaryKey(2);
						reservation.setCAuditResult(r);
					}
				}*/
				if(device.getCDictionaryByManagerAudit()!=null){
					//int mId=device.getCActiveByManagerAudit().getId();
					String mId = device.getCDictionaryByManagerAudit().getCNumber();
					if("1".equals(mId)){//需要设备管理员审核
					}
					if("2".equals(mId)){//不需要设备管理员审核---直接通过审核
						//CAuditResult r=cAuditResultDAO.findCAuditResultByPrimaryKey(2);
						CDictionary r = shareService.getCDictionaryByCategory("c_audit_result", "2");
						reservation.setCAuditResult(r);
					}
				}
			}

			if (resTag == 3) {// 当前设备管理员审核阶段
				// 审核通过
				//CAuditResult r = cAuditResultDAO.findCAuditResultByPrimaryKey(2);
				CDictionary r = shareService.getCDictionaryByCategory("c_audit_result", "2");
				reservation.setCAuditResult(r);
			}

		}
		LabRoomDeviceReservation reservationOri = labRoomDeviceReservationDAO.store(reservation);
		//audit.setCTrainingResult(cTrainingResultDAO.findCTrainingResultByPrimaryKey(resultId));
		audit.setCTrainingResult(shareService.getCDictionaryByCategory("c_training_result", String.valueOf(resultId)));
		audit.setAuditTime(Calendar.getInstance());// 审核时间
		LabRoomDeviceReservationResult auditOri = labRoomDeviceReservationResultDAO.store(audit);
		
		// 将审核意见和预约状态同步到关联设备
		List<LabRoomDeviceReservation> reservations = labRoomDeviceReservationService.findInnerSame(resId);
		if (reservations!=null&&reservations.size()>0) {
			for (LabRoomDeviceReservation labRoomDeviceReservation : reservations) {
				// 同步预约状态
				labRoomDeviceReservation.copy(reservationOri);
				// 同步审核意见
				LabRoomDeviceReservationResult auditSame = new LabRoomDeviceReservationResult();
				auditSame.copy(auditOri);
				auditSame.setLabRoomDeviceReservation(labRoomDeviceReservation);
				auditSame.setAuditTime(Calendar.getInstance());// 审核时间
				labRoomDeviceReservationResultDAO.store(auditSame);
			}
		}
		
		
		
		mav.setViewName("redirect:/device/myReservationList?page=1");
		return mav;
	}

	/****************************************************************************
	 * @功能：更改审核结果
	 * @作者：贺子龙 @时间：2015-11-10
	 ****************************************************************************/
	@RequestMapping("/device/alterAudit")
	public ModelAndView alterAudit(@RequestParam Integer id, @RequestParam int tag) {// tag
																						// 1从审核通过页面更改审核结果
																						// 2从审核拒绝页面更改审核结果
		// 新建ModelAndView对象；
		ModelAndView mav = new ModelAndView();
		// id对应的预约
		LabRoomDeviceReservation reservation = labRoomDeviceReservationDAO.findLabRoomDeviceReservationByPrimaryKey(id);
		mav.addObject("reservation", reservation);
		if (tag == 2) {// 如果是当前预约为审核拒绝状态，则将保存在original之中的开始和结束时间在重新赋值回来，并保存
			reservation.setBegintime(reservation.getOriginalBegin());
			reservation.setEndtime(reservation.getOriginalEnd());
			labRoomDeviceReservationDAO.store(reservation);
		}
		// 当前登录人
		User user = shareService.getUser();
		mav.addObject("user", user);
		// 更改后的审核结果
		mav.addObject("audit", new LabRoomDeviceReservationResult());
		// 结果
		//Set<CTrainingResult> results = cTrainingResultDAO.findAllCTrainingResults();
		List<CDictionary> results = shareService.getCDictionaryData("c_training_result");
		mav.addObject("results", results);
		mav.addObject("tag", tag);

		mav.setViewName("/device/lab_room_device_reservation/alterReservationAudit.jsp");
		return mav;
	}

	/****************************************************************************
	 * 保存更改后的意见
	 * 作者：贺子龙
	 * @throws ParseException 
	 ****************************************************************************/
	@SuppressWarnings("unused")
	@RequestMapping("/device/saveAlterAudit")
	public ModelAndView  saveAlterAudit(@ModelAttribute LabRoomDeviceReservationResult audit,@RequestParam Integer id) throws ParseException {
		//新建ModelAndView对象；
		ModelAndView mav=new ModelAndView();
		//id对应的设备预约
		LabRoomDeviceReservation reservation=labRoomDeviceReservationDAO.findLabRoomDeviceReservationByPrimaryKey(id);
		audit.setLabRoomDeviceReservation(reservation);
		//当前登录人为审核人
		User user=shareService.getUser();
		audit.setUser(user);
		//判断当前登录人是导师、实验室管理员、设备管理员哪个身份
		int tag=0;//tag 1 导师  2 实验室管理员  3 设备管理员
		boolean isTeacher=reservation.getUserByTeacher()!=null&&reservation.getUserByTeacher().getUsername()==user.getUsername();
		boolean isManager=reservation.getLabRoomDevice().getUser()!=null&&reservation.getLabRoomDevice().getUser().getUsername()==user.getUsername();
		boolean isLabManager=false;
		Set<LabRoomAdmin> admins=reservation.getLabRoomDevice().getLabRoom().getLabRoomAdmins();
		if (admins.size()>0) {
			for (LabRoomAdmin labRoomAdmin : admins) {
				if (labRoomAdmin.getUser().getUsername()==user.getUsername()) {
					isLabManager=true;break;
				}
			}
		}
		if (isTeacher) {
			tag=1;
		}else if (isLabManager) {
			tag=2;
		}else if (isManager) {
			tag=3;
		}
		
		audit.setTag(tag);
		//给审核意见之后加上审核人信息
		String remark=audit.getRemark();
		remark+="      (更改审核结果)  审核人姓名："+user.getCname()+"      审核人工号："+user.getUsername();
		audit.setRemark(remark);
		//审核结果
		int resultId=audit.getCTrainingResult().getId();
		if(resultId==620){
			labRoomDeviceService.alterTimeAfterRefused(reservation,0);
		}
		if(resultId==619){
			//审核通过
			reservation.setStage(reservation.getStage()+1);
			//CAuditResult r=cAuditResultDAO.findCAuditResultByPrimaryKey(2);
			CDictionary r = shareService.getCDictionaryByCategory("c_audit_result", "2");
			reservation.setCAuditResult(r);
		}
		LabRoomDeviceReservation reservationOri = labRoomDeviceReservationDAO.store(reservation);
		//CTrainingResult result=cTrainingResultDAO.findCTrainingResultByPrimaryKey(resultId);
		audit.setAuditTime(Calendar.getInstance());// 审核时间
		LabRoomDeviceReservationResult auditOri = labRoomDeviceReservationResultDAO.store(audit);
		
		// 将审核意见和预约状态同步到关联设备
        List<LabRoomDeviceReservation> reservations = labRoomDeviceReservationService.findInnerSame(id);
        if (reservations!=null&&reservations.size()>0) {
            for (LabRoomDeviceReservation labRoomDeviceReservation : reservations) {
                // 同步预约状态
                labRoomDeviceReservation.copy(reservationOri);
                // 同步审核意见
                LabRoomDeviceReservationResult auditSame = new LabRoomDeviceReservationResult();
                auditSame.copy(auditOri);
                auditSame.setLabRoomDeviceReservation(labRoomDeviceReservation);
                auditSame.setAuditTime(Calendar.getInstance());// 审核时间
                labRoomDeviceReservationResultDAO.store(auditSame);
            }
        }
		
		mav.addObject("audit", audit);
		mav.addObject("reservation", reservation);
		//结果
		//Set<CTrainingResult> results=cTrainingResultDAO.findAllCTrainingResults();
		List<CDictionary> results = shareService.getCDictionaryData("c_training_result");
		mav.addObject("results", results);
		
		if(resultId == 619)
		{
			mav.addObject("refresh", 1);
		}else{
			mav.addObject("refresh", 2);
		}
		
		mav.setViewName("/device/lab_room_device_reservation/alterReservationAudit.jsp");
		return mav;		
	}

	/****************************************************************************
	 * @功能：预约实验室禁用时间设置
	 * @作者：魏诚 @时间：2016-03-05
	 ****************************************************************************/
	@RequestMapping("/device/listLabRoomLimitTime")
	public ModelAndView listLabRoomLimitTime(@RequestParam Integer labId) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("labRoomLimitTimes", labRoomLimitTimeDAO.executeQuery("select c from LabRoomLimitTime c where c.labId= " + labId, 0, -1));
		mav.setViewName("/device/lab_room_device_reservation/listLabRoomLimitTime");
		return mav;

	}

	/****************************************************************************
	 * @功能：修改实验室预约时间
	 * @作者：XL
	 * @时间：2016-03-05
	 ****************************************************************************/
	@RequestMapping(value="/device/modifyReserva/{deviceId}/{reservalId}/{flag}",method = RequestMethod.GET)
	public ModelAndView modifyReserva(@PathVariable Integer deviceId,@PathVariable Integer reservalId,@PathVariable int flag, @ModelAttribute("selected_labCenter") Integer cid) {
		ModelAndView mav = new ModelAndView();
		// id对应的实验室设备
		LabRoomDevice device = labRoomDeviceService.findLabRoomDeviceByPrimaryKey(deviceId);
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
		mav.addObject("id", deviceId);
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
		mav.addObject("reservalId", reservalId);

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

		if (academy.equals("0201")) {// 纺织学院
			
			mav.addObject("isFZ", true);
			mav.addObject("first_hour", 8);
			mav.addObject("last_hour", 17);
			
		} else{
			
			mav.addObject("isFZ", false);
			mav.addObject("first_hour", 8);
			mav.addObject("last_hour", 21);
			
		}
		
		mav.addObject("flag", flag);
		
		mav.setViewName("/device/lab_room_device/reservationDeviceModify.jsp");
		return mav;
	}
	
	/****************************************************************************
	 * @功能：某一学校设备的预约情况统计
	 * @作者：贺子龙
	 * @时间：2016-05-18
	 ****************************************************************************/
	@RequestMapping("/device/listReservationByDevice")
	public ModelAndView listReservationByDevice(HttpServletRequest request, @ModelAttribute LabRoomDeviceReservation reservation, 
			@RequestParam String deviceNumber, int page) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("reservation", reservation);
		
		SchoolDevice device = schoolDeviceService.findSchoolDeviceByPrimaryKey(deviceNumber);
		mav.addObject("academyNumber", device.getSchoolAcademy().getAcademyNumber());
		mav.addObject("deviceName", device.getDeviceName());
		
		
		// 获取当前登录人
		User user = shareService.getUser();
		String username = user.getUsername();
		username = "[" + username + "]";

		// 学期
		List<SchoolTerm> terms = shareService.findAllSchoolTerms();
		mav.addObject("terms", terms);
		// 查询出所有的设备设备预约记录
		List<LabRoomDeviceReservation> allReservationList = labRoomDeviceService.findLabRoomDeviceReservationByDeviceNumberAndCid(reservation, request, deviceNumber, 1, -1, 2, null);
		int totalRecords = allReservationList.size();
		int pageSize = 10;// 每页10条记录
		// 分页信息
		Map<String, Integer> pageModel = shareService.getPage(page, pageSize, totalRecords);
		// 根据分页信息查询出来的记录
		List<LabRoomDeviceReservation> reservationList = labRoomDeviceService.findLabRoomDeviceReservationByDeviceNumberAndCid(reservation, request, deviceNumber, page, pageSize, 2, null);
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
		mav.addObject("page", page);
		mav.addObject("pageSize", pageSize);
		
		// 起止时间
        String begintime= request.getParameter("begintime");
    	String endtime=	request.getParameter("endtime");
		
    	mav.addObject("begintime", begintime);
    	mav.addObject("endtime", endtime);
		mav.addObject("deviceNumber", deviceNumber);
		mav.setViewName("/device/lab_room_device_reservation/listReservationByDevice.jsp");
		return mav;
	}
	
	
	/****************************************************************************
	 * @功能：中心下设备使用情况报表
	 * @作者：贺子龙
	 * @时间：2016-05-19
	 ****************************************************************************/
	@RequestMapping("/device/listLabRoomDeviceUsage")
	public ModelAndView listLabRoomDeviceUsage(HttpServletRequest request, @ModelAttribute LabRoomDeviceReservation reservation, 
			@RequestParam int page, @ModelAttribute("selected_labCenter") Integer cid) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("reservation", reservation);
		
		
		// 获取当前登录人
		User user = shareService.getUser();
		String username = user.getUsername();
		username = "[" + username + "]";

		// 学期
		List<SchoolTerm> terms = shareService.findAllSchoolTerms();
		mav.addObject("terms", terms);
		// 查询出所有的设备设备预约记录
		int totalRecords = labRoomDeviceService.findLabRoomDeviceReservationByDeviceNumberAndCid(reservation, request, null,1, -1, 2, cid).size();
		int pageSize = 20;// 每页10条记录
		// 分页信息
		Map<String, Integer> pageModel = shareService.getPage(page, pageSize, totalRecords);
		// 根据分页信息查询出来的记录
		List<LabRoomDeviceReservation> reservationList = labRoomDeviceService.findLabRoomDeviceReservationByDeviceNumberAndCid(reservation, request, null, page, pageSize, 2, cid);
		mav.addObject("reservationList", reservationList);
		List<LabRoomDeviceReservation> reservationLists = labRoomDeviceService.findLabRoomDeviceReservationByDeviceNumberAndCid(reservation, request, null, 1, -1, 2, cid);
		// 找出设备预约对应的所有的申请人、指导老师、设备管理员、实验室
		Map<String, String> reserveUsers = new HashMap<String, String>();
		Map<String, String> teachers = new HashMap<String, String>();
		Map<String, String> manageUsers = new HashMap<String, String>();
		Map<Integer, String> labrooms = new HashMap<Integer, String>();
		Map<Integer, String> researchs = new HashMap<Integer, String>();
		for (LabRoomDeviceReservation labRoomDeviceReservation : reservationLists) {
			if (labRoomDeviceReservation.getUserByTeacher() != null) {
				reserveUsers.put(labRoomDeviceReservation.getUserByReserveUser().getUsername(), labRoomDeviceReservation.getUserByReserveUser().getCname());
			}
			if (labRoomDeviceReservation.getUserByTeacher() != null) {
				teachers.put(labRoomDeviceReservation.getUserByTeacher().getUsername(), labRoomDeviceReservation.getUserByTeacher().getCname());
			}
			if (labRoomDeviceReservation.getLabRoomDevice().getUser() != null) {
				manageUsers.put(labRoomDeviceReservation.getLabRoomDevice().getUser().getUsername(), labRoomDeviceReservation.getLabRoomDevice().getUser().getCname());
			}
			if (labRoomDeviceReservation.getResearchProject() != null) {
				researchs.put(labRoomDeviceReservation.getResearchProject().getId(), labRoomDeviceReservation.getResearchProject().getName());
			}
			labrooms.put(labRoomDeviceReservation.getLabRoomDevice().getLabRoom().getId(), labRoomDeviceReservation.getLabRoomDevice().getLabRoom().getLabRoomName());
		}
		mav.addObject("reserveUsers", reserveUsers);
		mav.addObject("teachers", teachers);
		mav.addObject("manageUsers", manageUsers); 
		mav.addObject("researchs", researchs);
		
		
		mav.addObject("pageModel", pageModel);
		mav.addObject("totalRecords", totalRecords);
		mav.addObject("page", page);
		mav.addObject("pageSize", pageSize);
		
		// 起止时间
        String begintime= request.getParameter("begintime");
    	String endtime=	request.getParameter("endtime");
		
    	mav.addObject("begintime", begintime);
    	mav.addObject("endtime", endtime);
    	
		mav.setViewName("/device/lab_room_device_reservation/listLabRoomDeviceUsage.jsp");
		return mav;
	}
	
	

	/****************************************************************************
	 * @功能：中心下设备使用情况报表
	 * @作者：贺子龙
	 * @时间：2016-05-19
	 ****************************************************************************/
	@RequestMapping("/device/listLabRoomDeviceUsageInAppointment")
	public ModelAndView listLabRoomDeviceUsageInAppointment(HttpServletRequest request,
			@RequestParam int page) {
		ModelAndView mav = new ModelAndView();
		int pageSize = 30;
		// 查询记录
		List<Object[]> listLabRoomDeviceUsageInAppointments = labRoomDeviceService.getListLabRoomDeviceUsageInAppointment(request,page,pageSize);
		mav.addObject("listLabRoomDeviceUsageInAppointments",listLabRoomDeviceUsageInAppointments);
		
		int totalRecords = labRoomDeviceService.getListLabRoomDeviceUsageInAppointment(request,1,-1).size();
		mav.addObject("devices",labRoomDeviceService.getAllLabRoomDeviceUsageInAppointment());
		//所有排课相关项目 
		mav.addObject("items",labRoomDeviceService.getAllTimetableRelatedItems()); 
		//上课教师
		mav.addObject("teachers",labRoomDeviceService.getAllTimetableRelatedTeachers());
		
		//所有课程
		mav.addObject("courses", labRoomDeviceService.getAllCoursesInAppointment(request));
		
		if(request.getParameter("deviceName") != null && !request.getParameter("deviceName").equals("")){
			mav.addObject("deviceName", request.getParameter("deviceName"));
		}
		if(request.getParameter("deviceNumber") != null && !request.getParameter("deviceNumber").equals("")){
			mav.addObject("deviceNumber", request.getParameter("deviceNumber"));
		}
		if(request.getParameter("courseName") != null && !request.getParameter("courseName").equals("")){
			mav.addObject("courseName", request.getParameter("courseName"));
		}
		if(request.getParameter("itemName") != null && !request.getParameter("itemName").equals("")){
			mav.addObject("itemName", request.getParameter("itemName"));
		}
		if(request.getParameter("teacherName") != null && !request.getParameter("teacherName").equals("")){
			mav.addObject("teacherName", request.getParameter("teacherName"));
		}
		// 分页信息
		Map<String, Integer> pageModel = shareService.getPage(page, pageSize, totalRecords);
		mav.addObject("pageModel", pageModel);
		mav.addObject("pageSize",pageSize);
		mav.addObject("page", page);
		mav.addObject("totalRecords",totalRecords);
		mav.setViewName("/device/lab_room_device_reservation/listLabRoomDeviceUsageInAppointment.jsp");
		return mav;
	}
	
	/****************************************************************************
	 * @throws Exception 
	 * @description：设备教学报表
	 * @author：郑昕茹
	 * @date：2016-10-25
	 ****************************************************************************/
	@RequestMapping("/device/exportLabRoomDeviceUsageInAppointment")
	public void exportLabRoomDeviceUsageInAppointment(HttpServletRequest request,HttpServletResponse response) throws Exception {
		labRoomDeviceService.exportLabRoomDeviceUsageInAppointment(request, response);
	
	}
	
	/****************************************************************************
	 * @throws Exception 
	 * @description：设备使用情况报表
	 * @author：郑昕茹
	 * @date：2016-10-25
	 ****************************************************************************/
	@RequestMapping("/device/exportLabRoomDeviceUsage")
	public void exportLabRoomDeviceUsage(HttpServletRequest request,HttpServletResponse response, @ModelAttribute LabRoomDeviceReservation reservation, 
			 @ModelAttribute("selected_labCenter") Integer cid) throws Exception {
		labRoomDeviceService.exportLabRoomDeviceUsage(request, response, reservation, cid);
	
	}
}