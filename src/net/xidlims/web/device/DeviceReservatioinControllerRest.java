package net.xidlims.web.device;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import net.xidlims.dao.CStaticValueDAO;
import net.xidlims.dao.LabRoomDeviceReservationDAO;
import net.xidlims.dao.LabRoomDeviceReservationResultDAO;
import net.xidlims.dao.SchoolTermDAO;
import net.xidlims.domain.CDictionary;
import net.xidlims.domain.LabRoom;
import net.xidlims.domain.LabRoomDevice;
import net.xidlims.domain.LabRoomDeviceReservation;
import net.xidlims.domain.LabRoomDeviceReservationResult;
import net.xidlims.domain.SchoolDevice;
import net.xidlims.domain.SchoolTerm;
import net.xidlims.domain.User;
import net.xidlims.service.common.ShareService;
import net.xidlims.service.device.LabRoomDeviceReservationService;
import net.xidlims.service.device.LabRoomDeviceService;
import net.xidlims.service.lab.LabCenterService;
import net.xidlims.service.lab.LabRoomService;

import org.springframework.beans.factory.annotation.Autowired;
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

import edu.emory.mathcs.backport.java.util.Collections;

/****************************************************************************
 * @功能： 设备预约管理模块--表述性状态传递
 * @作者：魏诚
 * @时间：2014-07-14
 ****************************************************************************/
@Controller("DeviceControllerReservationRest")
@SessionAttributes("selected_labCenter")
public class DeviceReservatioinControllerRest {
	
	
	@Autowired LabRoomDeviceService labRoomDeviceService;
	@Autowired LabRoomDeviceReservationDAO labRoomDeviceReservationDAO;
	@Autowired LabRoomDeviceReservationResultDAO labRoomDeviceReservationResultDAO;
	@Autowired ShareService shareService;
	@Autowired SchoolTermDAO schoolTermDAO;
	@Autowired LabCenterService labCenterService;
	@Autowired LabRoomService labRoomService;
	@Autowired LabRoomDeviceReservationService labRoomDeviceReservationService;
	@Autowired CStaticValueDAO cStaticValueDAO;
	
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

	
	/**
	 * 
	  *@comment：我的审核页面
	  *@param reservation、page、centerId
	  *@return：
	  *@author：叶明盾
	  *@date：2015-10-29 下午4:44:18
	 */
	@RequestMapping(value = "device/myReservationListRest/{labRoomId}/{schoolTermId}/{deviceName}/{page}", method = RequestMethod.GET)
	public ModelAndView myReservationListRest(@PathVariable int labRoomId, @PathVariable String schoolTermId,
			@PathVariable String deviceName, @PathVariable int page, Model model,
			@ModelAttribute("selected_labCenter") Integer centerId, @ModelAttribute LabRoomDeviceReservation reservation) {
		//新建ModelAndView对象；
		ModelAndView mav=new ModelAndView();
		//当前登陆人信息
		User user=shareService.getUser();
		String username="["+user.getUsername()+"]";
		//学期
		List<SchoolTerm> terms= shareService.findAllSchoolTerms(); 
		mav.addObject("terms", terms);
		//将前台的信息传递过来
		SchoolDevice schoolDevice  = new SchoolDevice();
		LabRoomDevice labRoomDevice=new LabRoomDevice();
		if(!"-1".equals(deviceName)){
			schoolDevice.setDeviceName(deviceName);
		}
		LabRoom labroom=new LabRoom();
		if(!"-1".equals(labRoomId)&&labRoomId!=-1){
			labroom.setId(labRoomId);
		}
		labRoomDevice.setSchoolDevice(schoolDevice);
		labRoomDevice.setLabRoom(labroom);
		reservation.setLabRoomDevice(labRoomDevice);
		SchoolTerm schoolTerm=new SchoolTerm();
		if(!"-1".equals(schoolTermId)){
			schoolTerm.setId(Integer.valueOf(schoolTermId));
		}
		reservation.setSchoolTerm(schoolTerm);
		
		//中心所属实验室
		Map<Integer, String> rooms = labRoomDeviceService.findAllLabrooms(centerId);
		mav.addObject("rooms", rooms);
		mav.addObject("reservation", reservation);
		int cId=1;//审核中
		//根据设备预约信息和审核状态查询设备预约
		List<LabRoomDeviceReservation> allReservationList=labRoomDeviceService.findAllLabRoomDeviceReservation(reservation,cId,centerId, 1, -1);
		int totalRecords=0;
		for (LabRoomDeviceReservation labRoomDeviceReservation : allReservationList) {
			boolean isUnderTeacherAudit=labRoomDeviceReservationService.isUnderTeacherAudit(labRoomDeviceReservation);
			boolean isUnderManagerAudit=labRoomDeviceReservationService.isUnderManagerAudit(labRoomDeviceReservation);
			boolean isUnderLabManagerAudit=labRoomDeviceReservationService.isUnderLabManagerAudit(labRoomDeviceReservation);
			mav.addObject("isUnderTeacherAudit", isUnderTeacherAudit);
			mav.addObject("isUnderManagerAudit", isUnderManagerAudit);
			mav.addObject("isUnderLabManagerAudit", isUnderLabManagerAudit);
			labRoomDeviceReservation.setTag(0);//将tag初始化为一个不相关的值，防止后续查找到脏数据。
			//当前登录人符合导师审核条件
			if (labRoomDeviceReservation.getUserByTeacher()!=null&&user.getUsername()==labRoomDeviceReservation.getUserByTeacher().getUsername()&&isUnderTeacherAudit) {
				labRoomDeviceReservation.setTag(1);//满足导师审核条件
				labRoomDeviceReservationDAO.store(labRoomDeviceReservation);
				totalRecords++;continue;
			}
			
			//当前登录人符合实验室管理员审核条件
			if (labRoomDeviceReservation.getLabRoomDevice().getLabRoom().getLabRoomAdmins().toString().indexOf(username)!=-1&&isUnderLabManagerAudit) {
				labRoomDeviceReservation.setTag(2);//满足实验室管理员审核条件
				labRoomDeviceReservationDAO.store(labRoomDeviceReservation);
				totalRecords++;continue;
			}
			//当前登录人符合设备管理员审核条件
			if (labRoomDeviceReservation.getLabRoomDevice().getUser()!=null&&user.getUsername()==labRoomDeviceReservation.getLabRoomDevice().getUser().getUsername()&&isUnderManagerAudit) {
				labRoomDeviceReservation.setTag(3);//满足设备管理员审核条件
				labRoomDeviceReservationDAO.store(labRoomDeviceReservation);
				totalRecords++;continue;
			}
		}
		int pageSize=10;//每页10条记录
		//分页信息
		Map<String,Integer> pageModel =shareService.getPage(page, pageSize,totalRecords);
		//根据分页信息查询出来的记录
		List<LabRoomDeviceReservation> reservationList=labRoomDeviceService.findAllLabRoomDeviceReservation(reservation,cId,centerId,page,pageSize,9);//这里9只是tag的传参，没有实际意义
		mav.addObject("reservationList", reservationList);
		
		mav.addObject("pageModel",pageModel);
		mav.addObject("totalRecords", totalRecords);
		mav.addObject("page", page);
		mav.addObject("pageSize", pageSize);	
		mav.addObject("cid", centerId);
		//结果
		//Set<CTrainingResult> results=cTrainingResultDAO.findAllCTrainingResults();
		List<CDictionary> results = shareService.getCDictionaryData("c_training_result");
		mav.addObject("results", results);
		
		//再将状态信息传递给新的页面
		mav.addObject("schoolTermId", schoolTermId);
		mav.addObject("labRoomId", labRoomId);
		mav.addObject("deviceName", deviceName);
		mav.addObject("isRest", 1);
		mav.setViewName("/device/lab_room_device_reservation/myReservationList.jsp");
		return mav;
	}
	
	/****************************************************************************
	 * 功能：实验室设备管理---设备预约管理---审核中
	 * 作者：李小龙
	 ****************************************************************************/
	@RequestMapping(value = "device/auditReservationListRest/{labRoomId}/{schoolTermId}/{deviceName}/{page}", method = RequestMethod.GET)
	public ModelAndView auditReservationListRest(@PathVariable int labRoomId, @PathVariable String schoolTermId,
			@PathVariable String deviceName, @PathVariable int page, Model model,
			@ModelAttribute("selected_labCenter") Integer centerId, @ModelAttribute LabRoomDeviceReservation reservation) {
		//新建ModelAndView对象；
		ModelAndView mav=new ModelAndView();
		//当前登陆人信息
		User user=shareService.getUser();
		String username="["+user.getUsername()+"]";
		//学期
		List<SchoolTerm> terms= shareService.findAllSchoolTerms(); 
		mav.addObject("terms", terms);
		//将前台的信息传递过来
		SchoolDevice schoolDevice  = new SchoolDevice();
		LabRoomDevice labRoomDevice=new LabRoomDevice();
		if(!"-1".equals(deviceName)){
			schoolDevice.setDeviceName(deviceName);
		}
		LabRoom labroom=new LabRoom();
		if(!"-1".equals(labRoomId)&&labRoomId!=-1){
			labroom.setId(labRoomId);
		}
		labRoomDevice.setSchoolDevice(schoolDevice);
		labRoomDevice.setLabRoom(labroom);
		reservation.setLabRoomDevice(labRoomDevice);
		SchoolTerm schoolTerm=new SchoolTerm();
		if(!"-1".equals(schoolTermId)){
			schoolTerm.setId(Integer.valueOf(schoolTermId));
		}
		reservation.setSchoolTerm(schoolTerm);
		
		//中心所属实验室
		Map<Integer, String> rooms=labRoomDeviceService.findAllLabrooms(centerId);
		mav.addObject("rooms", rooms);
		mav.addObject("reservation", reservation);
		int cId=1;//审核中
		//根据设备预约信息和审核状态查询设备预约
		List<LabRoomDeviceReservation> allReservationList=labRoomDeviceService.findAllLabRoomDeviceReservation(reservation,cId,centerId,1,-1);
		for (LabRoomDeviceReservation labRoomDeviceReservation : allReservationList) {
			boolean isUnderTeacherAudit=labRoomDeviceReservationService.isUnderTeacherAudit(labRoomDeviceReservation);
			boolean isUnderManagerAudit=labRoomDeviceReservationService.isUnderManagerAudit(labRoomDeviceReservation);
			boolean isUnderLabManagerAudit=labRoomDeviceReservationService.isUnderLabManagerAudit(labRoomDeviceReservation);
			mav.addObject("isUnderTeacherAudit", isUnderTeacherAudit);
			mav.addObject("isUnderManagerAudit", isUnderManagerAudit);
			mav.addObject("isUnderLabManagerAudit", isUnderLabManagerAudit);
			labRoomDeviceReservation.setTag(0);//将tag初始化为一个不相关的值，防止后续查找到脏数据。
			//当前登录人符合导师审核条件
			if (labRoomDeviceReservation.getUserByTeacher()!=null&&user.getUsername()==labRoomDeviceReservation.getUserByTeacher().getUsername()&&isUnderTeacherAudit) {
				labRoomDeviceReservation.setTag(1);//满足导师审核条件
				labRoomDeviceReservationDAO.store(labRoomDeviceReservation);
			}
			
			//当前登录人符合实验室管理员审核条件
			if (labRoomDeviceReservation.getLabRoomDevice().getLabRoom().getLabRoomAdmins().toString().indexOf(username)!=-1&&isUnderLabManagerAudit) {
				labRoomDeviceReservation.setTag(2);//满足实验室管理员审核条件
				labRoomDeviceReservationDAO.store(labRoomDeviceReservation);
			}
			//当前登录人符合设备管理员审核条件
			if (labRoomDeviceReservation.getLabRoomDevice().getUser()!=null&&user.getUsername()==labRoomDeviceReservation.getLabRoomDevice().getUser().getUsername()&&isUnderManagerAudit) {
				labRoomDeviceReservation.setTag(3);//满足设备管理员审核条件
				labRoomDeviceReservationDAO.store(labRoomDeviceReservation);
			}
		}
		int totalRecords=allReservationList.size();
		int pageSize=10;//每页10条记录
		//分页信息
		Map<String,Integer> pageModel =shareService.getPage(page, pageSize,totalRecords);
		//根据分页信息查询出来的记录
		List<LabRoomDeviceReservation> reservationList=labRoomDeviceService.findAllLabRoomDeviceReservation(reservation,cId,centerId,page,pageSize);
		mav.addObject("reservationList", reservationList);
		
		mav.addObject("pageModel",pageModel);
		mav.addObject("totalRecords", totalRecords);
		mav.addObject("page", page);
		mav.addObject("pageSize", pageSize);	
		mav.addObject("cid", centerId);
		//再将状态信息传递给新的页面
		mav.addObject("schoolTermId", schoolTermId);
		mav.addObject("labRoomId", labRoomId);
		mav.addObject("deviceName", deviceName);
		mav.setViewName("/device/lab_room_device_reservation/auditReservationList.jsp");
		return mav;
	}
	
	/****************************************************************************
	 * 设备预约审核--导师
	 * 作者：李小龙
	 ****************************************************************************/
	@RequestMapping(value = "device/teacherReservationAuditRest/{labRoomId}/{schoolTermId}/{deviceName}/{page}/{id}", method = RequestMethod.GET)
	public ModelAndView teacherReservationAuditRest(@PathVariable int labRoomId, @PathVariable String schoolTermId,
			@PathVariable String deviceName, @PathVariable int page , @PathVariable int id, Model model,
			@ModelAttribute("selected_labCenter") Integer centerId) {
		//新建ModelAndView对象；
		ModelAndView mav=new ModelAndView();
		//id对应的预约
		LabRoomDeviceReservation reservation=labRoomDeviceReservationDAO.findLabRoomDeviceReservationByPrimaryKey(id);
		mav.addObject("reservation", reservation);
		//是否处于导师审核阶段下
		boolean isUnderTeacherAudit=labRoomDeviceReservationService.isUnderTeacherAudit(reservation);
		mav.addObject("isUnderTeacherAudit", isUnderTeacherAudit);
		//当前登录人
		User user=shareService.getUser();
		mav.addObject("user", user);
		//教师审核结果
//		mav.addObject("audit", labRoomDeviceService.findLabRoomDeviceReservationResult(id,reservation.getUserByTeacher().getUsername()));
		mav.addObject("audit", new LabRoomDeviceReservationResult());
		//结果
		//Set<CTrainingResult> results=cTrainingResultDAO.findAllCTrainingResults();
		List<CDictionary> results = shareService.getCDictionaryData("c_training_result");
		mav.addObject("results", results);
		
		//导师审核结果  贺子龙
		Set<LabRoomDeviceReservationResult> teacherAudits=reservation.getLabRoomDeviceReservationResults();//该预约对应的所有审核结果
		//新建一个整型list，用来存放所有的audits的Id
		if (teacherAudits.size()>0) {
			
			List<Integer> auditsListId = new ArrayList<Integer>();
			for (LabRoomDeviceReservationResult labRoomDeviceReservationResult : teacherAudits) {
				if (labRoomDeviceReservationResult.getTag()==1) {
					auditsListId.add(labRoomDeviceReservationResult.getId());//将tag条件的id加入到auditsListId中
				}
			}
			Collections.sort(auditsListId);//将auditsListId从小到大排序
			if (auditsListId.size()>0) {
			int auditId=auditsListId.get(auditsListId.size()-1);//取最后一个，确保是最后一次导师审核（针对打回去的情况）
			LabRoomDeviceReservationResult teacherAudit=labRoomDeviceReservationResultDAO.findLabRoomDeviceReservationResultByPrimaryKey(auditId);
			mav.addObject("teacherAudit", teacherAudit);
			}
		}
		
		//再将状态信息传递给新的页面
		mav.addObject("schoolTermId", schoolTermId);
		mav.addObject("labRoomId", labRoomId);
		mav.addObject("deviceName", deviceName);
		mav.addObject("page", page);
		mav.addObject("isRest", 1);
		mav.setViewName("/device/lab_room_device_reservation/teacherReservationAudit.jsp");
		return mav;		
	}
	/****************************************************************************
	 * 设备预约审核--实验室管理员
	 * 作者：贺子龙
	 ****************************************************************************/
	@RequestMapping(value = "device/labManagerReservationAuditRest/{labRoomId}/{schoolTermId}/{deviceName}/{page}/{id}", method = RequestMethod.GET)
	public ModelAndView labManagerReservationAuditRest(@PathVariable int labRoomId, @PathVariable String schoolTermId,
			@PathVariable String deviceName, @PathVariable int page , @PathVariable int id, Model model,
			@ModelAttribute("selected_labCenter") Integer centerId) {
		//新建ModelAndView对象；
		ModelAndView mav=new ModelAndView();
		//id对应的预约
		LabRoomDeviceReservation reservation=labRoomDeviceReservationDAO.findLabRoomDeviceReservationByPrimaryKey(id);
		mav.addObject("reservation", reservation);
		//是否处于实验室管理员审核阶段下
		boolean isUnderLabManagerAudit=labRoomDeviceReservationService.isUnderLabManagerAudit(reservation);
		mav.addObject("isUnderLabManagerAudit", isUnderLabManagerAudit);
		//当前登录人
		User user=shareService.getUser();
		String username=user.getUsername();
		username="["+username+"]";
		mav.addObject("user", user);
		mav.addObject("username", username);
		//管理员审核结果
//		mav.addObject("audit", labRoomDeviceService.findManageAudit(id,reservation.getUserByTeacher().getUsername()));
		mav.addObject("audit", new LabRoomDeviceReservationResult());
		//结果
		//Set<CTrainingResult> results=cTrainingResultDAO.findAllCTrainingResults();
		List<CDictionary> results = shareService.getCDictionaryData("c_training_result");
		mav.addObject("results", results);
		
		
		//管理员审核结果  贺子龙
		Set<LabRoomDeviceReservationResult> labManageAudits=reservation.getLabRoomDeviceReservationResults();//该预约对应的所有审核结果
		//新建一个整型list，用来存放所有的audits的Id
		if (labManageAudits.size()>0) {
			
			List<Integer> auditsListId = new ArrayList<Integer>();
			for (LabRoomDeviceReservationResult labRoomDeviceReservationResult : labManageAudits) {
				if (labRoomDeviceReservationResult.getTag()==2) {
					auditsListId.add(labRoomDeviceReservationResult.getId());//将tag条件的id加入到auditsListId中
				}
			}
			Collections.sort(auditsListId);//将auditsListId从小到大排序
			if (auditsListId.size()>0) {
				
				int auditId=auditsListId.get(auditsListId.size()-1);//取最后一个，确保是最后一次设备管理员审核（针对打回去的情况）
				LabRoomDeviceReservationResult labManageAudit=labRoomDeviceReservationResultDAO.findLabRoomDeviceReservationResultByPrimaryKey(auditId);
				mav.addObject("labManageAudit", labManageAudit);
			}
		}
		
		//再将状态信息传递给新的页面
		mav.addObject("schoolTermId", schoolTermId);
		mav.addObject("labRoomId", labRoomId);
		mav.addObject("deviceName", deviceName);
		mav.addObject("page", page);
		mav.addObject("isRest", 1);
		mav.setViewName("/device/lab_room_device_reservation/labManagerReservationAudit.jsp");
		return mav;		
	}
	

	/****************************************************************************
	 * 设备预约审核--设备管理员
	 * 作者：李小龙
	 ****************************************************************************/
	@RequestMapping(value = "device/managerReservationAuditRest/{labRoomId}/{schoolTermId}/{deviceName}/{page}/{id}", method = RequestMethod.GET)
	public ModelAndView managerReservationAuditRest(@PathVariable int labRoomId, @PathVariable String schoolTermId,
			@PathVariable String deviceName, @PathVariable int page , @PathVariable int id, Model model,
			@ModelAttribute("selected_labCenter") Integer centerId) {
		//新建ModelAndView对象；
		ModelAndView mav=new ModelAndView();
		//id对应的预约
		LabRoomDeviceReservation reservation=labRoomDeviceReservationDAO.findLabRoomDeviceReservationByPrimaryKey(id);
		mav.addObject("reservation", reservation);
		//是否处于设备管理员审核阶段下
		boolean isUnderManagerAudit=labRoomDeviceReservationService.isUnderManagerAudit(reservation);
		mav.addObject("isUnderManagerAudit", isUnderManagerAudit);
		//当前登录人
		User user=shareService.getUser();
		mav.addObject("user", user);
		//管理员审核结果
//		mav.addObject("audit", labRoomDeviceService.findManageAudit(id,reservation.getUserByTeacher().getUsername()));
		mav.addObject("audit", new LabRoomDeviceReservationResult());
		//结果
		//Set<CTrainingResult> results=cTrainingResultDAO.findAllCTrainingResults();
		List<CDictionary> results = shareService.getCDictionaryData("c_training_result");
		mav.addObject("results", results);
		
		
		//管理员审核结果  贺子龙
		Set<LabRoomDeviceReservationResult> manageAudits=reservation.getLabRoomDeviceReservationResults();//该预约对应的所有审核结果
		//新建一个整型list，用来存放所有的audits的Id
		if (manageAudits.size()>0) {
			
			List<Integer> auditsListId = new ArrayList<Integer>();
			for (LabRoomDeviceReservationResult labRoomDeviceReservationResult : manageAudits) {
				if (labRoomDeviceReservationResult.getTag()==3) {
					auditsListId.add(labRoomDeviceReservationResult.getId());//将tag条件的id加入到auditsListId中
				}
			}
			Collections.sort(auditsListId);//将auditsListId从小到大排序
			if (auditsListId.size()>0) {
				
				int auditId=auditsListId.get(auditsListId.size()-1);//取最后一个，确保是最后一次设备管理员审核（针对打回去的情况）
				LabRoomDeviceReservationResult manageAudit=labRoomDeviceReservationResultDAO.findLabRoomDeviceReservationResultByPrimaryKey(auditId);
				mav.addObject("manageAudit", manageAudit);
			}
		}
		//再将状态信息传递给新的页面
		mav.addObject("schoolTermId", schoolTermId);
		mav.addObject("labRoomId", labRoomId);
		mav.addObject("deviceName", deviceName);
		mav.addObject("page", page);
		mav.addObject("isRest", 1);
		mav.setViewName("/device/lab_room_device_reservation/managerReservationAudit.jsp");
		return mav;		
	}
	
	
	/****************************************************************************
	 * 保存导师审核
	 * 作者：李小龙
	 * @throws ParseException 
	 ****************************************************************************/
	@RequestMapping(value = "device/saveTeacherAuditRest/{labRoomId}/{schoolTermId}/{deviceName}/{page}/{resultId}/{remark}/{id}", method = RequestMethod.GET)
	public ModelAndView saveTeacherAuditRest(@PathVariable int labRoomId, @PathVariable String schoolTermId,
			@PathVariable String deviceName, @PathVariable int page ,@PathVariable int resultId ,@PathVariable String remark , @PathVariable int id, Model model,
			@ModelAttribute LabRoomDeviceReservationResult audit,@ModelAttribute("selected_labCenter") Integer centerId) throws ParseException{
		//新建ModelAndView对象；
		ModelAndView mav=new ModelAndView();
		//id对应的设备预约
		LabRoomDeviceReservation reservation=labRoomDeviceReservationDAO.findLabRoomDeviceReservationByPrimaryKey(id);
		
		audit.setLabRoomDeviceReservation(reservation);
		
		audit.setTag(1);//1为导师审核
		//当前登录人为审核人
		User user=shareService.getUser();
		audit.setUser(user);
		//给审核意见之后加上审核人信息
		remark+="        审核人姓名："+user.getCname()+"      审核人工号："+user.getUsername();
		audit.setRemark(remark);
		//审核结果
		if(resultId==2){
			labRoomDeviceService.alterTimeAfterRefused(reservation,0);
		}
		if(resultId==1){
			reservation.setStage(reservation.getStage()+1);
			//审核通过,判断是否需要管理员审核
			LabRoomDevice device=reservation.getLabRoomDevice();
			
			if(device.getCActiveByLabManagerAudit()!=null){
				int lmId=device.getCActiveByLabManagerAudit().getId();
				if(lmId==1){//需要实验室管理员审核
				}
				if(lmId==2){//不需要实验室管理员审核---直接通过审核
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
		//CTrainingResult result=cTrainingResultDAO.findCTrainingResultByPrimaryKey(resultId);
		CDictionary result = shareService.getCDictionaryByCategory("c_training_result", String.valueOf(resultId));
		audit.setCTrainingResult(result);
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
		mav.setViewName("redirect:/device/auditReservationListRest/" + labRoomId+"/"+ schoolTermId + "/" + "-1" + "/"+page);
		return mav;		
	}
	
	/****************************************************************************
	 * 保存实验室管理员审核
	 * 作者：李小龙
	 * @throws ParseException 
	 ****************************************************************************/
	@RequestMapping(value = "device/saveLabManagerAuditRest/{labRoomId}/{schoolTermId}/{deviceName}/{page}/{resultId}/{remark}/{id}", method = RequestMethod.GET)
	public ModelAndView saveLabManagerAuditRest(@PathVariable int labRoomId, @PathVariable String schoolTermId,
			@PathVariable String deviceName, @PathVariable int page ,@PathVariable int resultId ,@PathVariable String remark , @PathVariable int id, Model model,
			@ModelAttribute LabRoomDeviceReservationResult audit,@ModelAttribute("selected_labCenter") Integer centerId) throws ParseException{
					
		//新建ModelAndView对象；
		ModelAndView mav=new ModelAndView();
		LabRoomDeviceReservationResult auditNew=new LabRoomDeviceReservationResult();
		//id对应的设备预约
		LabRoomDeviceReservation reservation=labRoomDeviceReservationDAO.findLabRoomDeviceReservationByPrimaryKey(id);
		auditNew.setLabRoomDeviceReservation(reservation);
		auditNew.setTag(2);//2为实验室管理员审核
		//当前登录人为审核人
		User user=shareService.getUser();
		auditNew.setUser(user);
		//给审核意见之后加上审核人信息
		remark+="        审核人姓名："+user.getCname()+"      审核人工号："+user.getUsername();
		auditNew.setRemark(remark);
		//审核结果
		if(resultId==2){
			labRoomDeviceService.alterTimeAfterRefused(reservation,0);
		}
		if(resultId==1){
			reservation.setStage(reservation.getStage()+1);
			//审核通过,判断是否需要设备管理员审核
			LabRoomDevice device=reservation.getLabRoomDevice();
			if(device.getCDictionaryByManagerAudit()!=null){
				//int lmId=device.getCActiveByManagerAudit().getId();
				String lmId = device.getCDictionaryByManagerAudit().getCNumber();
				if ("1".equals(lmId)) {//需要设备管理员审核
				}
				if ("2".equals(lmId)) {//不需要设备管理员审核
					//审核通过
					//CAuditResult r=cAuditResultDAO.findCAuditResultByPrimaryKey(2);
					CDictionary r = shareService.getCDictionaryByCategory("c_audit_result", "2");
					reservation.setCAuditResult(r);
					
				}
			}
			
		}
		LabRoomDeviceReservation reservationOri = labRoomDeviceReservationDAO.store(reservation);
		//CTrainingResult result=cTrainingResultDAO.findCTrainingResultByPrimaryKey(resultId);
		CDictionary result = shareService.getCDictionaryByCategory("c_training_result", String.valueOf(resultId));
		auditNew.setCTrainingResult(result);
		auditNew.setAuditTime(Calendar.getInstance());// 审核时间
		LabRoomDeviceReservationResult auditOri = labRoomDeviceReservationResultDAO.store(auditNew);
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
		mav.setViewName("redirect:/device/auditReservationListRest/" + labRoomId+"/"+ schoolTermId + "/" + "-1" + "/"+page);
		return mav;		
	}
	
	/****************************************************************************
	 * 保存设备管理员审核
	 * 作者：李小龙
	 * @throws ParseException 
	 ****************************************************************************/
	@RequestMapping(value = "device/saveManagerAuditRest/{labRoomId}/{schoolTermId}/{deviceName}/{page}/{resultId}/{remark}/{id}", method = RequestMethod.GET)
	public ModelAndView saveManagerAuditRest(@PathVariable int labRoomId, @PathVariable String schoolTermId,
			@PathVariable String deviceName, @PathVariable int page ,@PathVariable int resultId ,@PathVariable String remark , @PathVariable int id, Model model,
			@ModelAttribute LabRoomDeviceReservationResult audit,@ModelAttribute("selected_labCenter") Integer centerId) throws ParseException{
			
		//新建ModelAndView对象；
		ModelAndView mav=new ModelAndView();
		//id对应的设备预约
		LabRoomDeviceReservation reservation=labRoomDeviceReservationDAO.findLabRoomDeviceReservationByPrimaryKey(id);
		audit.setLabRoomDeviceReservation(reservation);
		audit.setTag(3);//3为设备管理员审核
		//当前登录人为审核人
		User user=shareService.getUser();
		audit.setUser(user);
		//给审核意见之后加上审核人信息
		remark+="        审核人姓名："+user.getCname()+"      审核人工号："+user.getUsername();
		audit.setRemark(remark);
		//审核结果
		if(resultId==2){
			//审核拒绝
			//CAuditResult r=cAuditResultDAO.findCAuditResultByPrimaryKey(3);
			CDictionary r = shareService.getCDictionaryByCategory("c_audit_result", "3");
			
			reservation.setCAuditResult(r);
			reservation.setStage(-1);
			reservation.setOriginalBegin(reservation.getBegintime());//在被赋值为1900-01-01之前，把本身的值保存在original中
			reservation.setOriginalEnd(reservation.getEndtime());
			String str="1900-01-01";
			SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
			Date date =sdf.parse(str);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			reservation.setBegintime(calendar);
			reservation.setEndtime(calendar);
			labRoomDeviceReservationDAO.store(reservation);
		}
		if(resultId==1){
			reservation.setStage(reservation.getStage()+1);
			//审核通过
			//CAuditResult r=cAuditResultDAO.findCAuditResultByPrimaryKey(2);
			CDictionary r = shareService.getCDictionaryByCategory("c_audit_result", "2");
			reservation.setCAuditResult(r);
		}
		LabRoomDeviceReservation reservationOri = labRoomDeviceReservationDAO.store(reservation);
		//CTrainingResult result=cTrainingResultDAO.findCTrainingResultByPrimaryKey(resultId);
		CDictionary result = shareService.getCDictionaryByCategory("c_training_result", String.valueOf(resultId));
		audit.setCTrainingResult(result);
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
		mav.setViewName("redirect:/device/auditReservationListRest/" + labRoomId+"/"+ schoolTermId + "/" + "-1" + "/"+page);
		return mav;		
	}
}
