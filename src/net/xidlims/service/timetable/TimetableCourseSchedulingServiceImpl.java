package net.xidlims.service.timetable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import net.xidlims.dao.CDictionaryDAO;
import net.xidlims.dao.LabRoomDeviceDAO;
import net.xidlims.dao.LabRoomDeviceReservationDAO;
import net.xidlims.dao.SchoolCourseDetailDAO;
import net.xidlims.dao.SchoolTermDAO;
import net.xidlims.dao.TimetableAppointmentDAO;
import net.xidlims.dao.TimetableAppointmentSameNumberDAO;
import net.xidlims.dao.TimetableLabRelatedDAO;
import net.xidlims.dao.TimetableLabRelatedDeviceDAO;
import net.xidlims.domain.CDictionary;
import net.xidlims.domain.LabRoomDevice;
import net.xidlims.domain.LabRoomDeviceReservation;
import net.xidlims.domain.SchoolCourseDetail;
import net.xidlims.domain.TimetableAppointment;
import net.xidlims.domain.TimetableAppointmentSameNumber;
import net.xidlims.domain.TimetableLabRelated;
import net.xidlims.domain.TimetableLabRelatedDevice;
import net.xidlims.service.common.ShareService;
import net.xidlims.service.device.LabRoomDeviceReservationService;
import net.xidlims.service.device.LabRoomDeviceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("TimetableCourseSchedulingService")
public class TimetableCourseSchedulingServiceImpl implements TimetableCourseSchedulingService {
	@Autowired
	private SchoolCourseDetailDAO schoolCourseDetailDAO;
	@Autowired
	private TimetableAppointmentDAO timetableAppointmentDAO;
	@Autowired
	private LabRoomDeviceDAO labRoomDeviceDAO;
	@Autowired
	private TimetableLabRelatedDeviceDAO timetableLabRelatedDeviceDAO;
	@Autowired
	private LabRoomDeviceReservationDAO labRoomDeviceReservationDAO;
	@Autowired
	private ShareService shareService;
	@Autowired
	private SchoolTermDAO schoolTermDAO;
	@Autowired
	private LabRoomDeviceReservationService labRoomDeviceReservationService;
	@Autowired
	private TimetableLabRelatedDAO timetableLabRelatedDAO;
	@Autowired
	private TimetableAppointmentSameNumberDAO timetableAppointmentSameNumberDAO;
	@Autowired
	private LabRoomDeviceService labRoomDeviceService;
	
	
	/*************************************************************************************
	 * @內容：根据时间查找学期
	 * @作者： 魏誠
	 * @日期：2014-07-24
	 *************************************************************************************/
	public String getTimetableClassesMap(int term, String weekday, String courseCode) {
		// 根据课程及id获取课程排课列表
		String sql = "select c from SchoolCourseDetail c where c.schoolCourse.courseCode like '" + courseCode
				+ "' and c.weekday =" + Integer.parseInt(weekday);
		// 获取以排课内容
		String sql1 = "select c from TimetableAppointment c where c.schoolCourseDetail.schoolCourse.courseCode like '"
				+ courseCode + "' and c.weekday =" + Integer.parseInt(weekday)
				+ " and c.schoolCourseDetail.schoolTerm.id = " + term;
		List<TimetableAppointment> timetableAppointmentList = timetableAppointmentDAO.executeQuery(sql1);
		String commonClass = "";
		for (TimetableAppointment timetableAppointment1 : timetableAppointmentList) {
			for (int i = timetableAppointment1.getStartClass(); i <= timetableAppointment1.getEndClass(); i++) {
				commonClass = commonClass + i + ";";
			}
		}

		String jsonClass = "[";
		// 遍历实验分室
		for (SchoolCourseDetail schoolCourseDetail : schoolCourseDetailDAO.executeQuery(sql)) {
			for (int i = schoolCourseDetail.getStartClass(); i <= schoolCourseDetail.getEndClass(); i++) {

				if (i == schoolCourseDetail.getEndClass()) {
					jsonClass = jsonClass + "{\"id\":\"" + i + "\",\"value\":\"" + i + "\",\"weekday\":\""
							+ schoolCourseDetail.getWeekday() + "\"}";

				} else {
					jsonClass = jsonClass + "{\"id\":\"" + i + "\",\"value\":\"" + i + "\",\"weekday\":\""
							+ schoolCourseDetail.getWeekday() + "\"},";

				}
			}
			jsonClass = jsonClass + "]";
			break;
		}
		return jsonClass;
	}

	/*************************************************************************************
	 * @內容：根据时间查找学期
	 * @作者： 魏誠
	 * @日期：2014-07-24
	 *************************************************************************************/
	public String getTimetableClassesMap(int term, String weekday, String courseCode, String startClass) {
		// 根据课程及id获取课程排课列表
		String sql = "select c from SchoolCourseDetail c where c.schoolCourse.courseCode like '" + courseCode
				+ "' and c.weekday =" + Integer.parseInt(weekday) + " and c.startClass ="
				+ Integer.parseInt(startClass);

		String jsonClass = "[";
		// 遍历实验分室
		for (SchoolCourseDetail schoolCourseDetail : schoolCourseDetailDAO.executeQuery(sql)) {
			for (int i = schoolCourseDetail.getStartClass(); i <= schoolCourseDetail.getEndClass(); i++) {

				if (i == schoolCourseDetail.getEndClass()) {
					jsonClass = jsonClass + "{\"id\":\"" + i + "\",\"value\":\"" + i + "\",\"weekday\":\""
							+ schoolCourseDetail.getWeekday() + "\"}";

				} else {
					jsonClass = jsonClass + "{\"id\":\"" + i + "\",\"value\":\"" + i + "\",\"weekday\":\""
							+ schoolCourseDetail.getWeekday() + "\"},";

				}
			}
			jsonClass = jsonClass + "]";
			break;
		}
		return jsonClass;
	}

	/*************************************************************************************
	 * @內容：返回可用的星期信息--编辑排课记录
	 * @作者：魏诚
	 * @日期：2016-01-06
	 *************************************************************************************/
	public String getValidLabroomDevice(String[] labrooms) {

		//遍历所有选定实验室的实验设备
		//如果实验室具有实验设备，则增加到json中
		List<LabRoomDevice> labRoomDevices =new ArrayList<LabRoomDevice>();
		for(String labroom :labrooms){
			String sql = "select c from LabRoomDevice c where c.labRoom.id =" + labroom;
			//遍历当前实验室所对应的实验室设备，并加入到列表中
			for(LabRoomDevice labRoomDevice:labRoomDeviceDAO.executeQuery(sql, 0,-1)){
				labRoomDevices.add(labRoomDevice);
			}
		}
		String json = "[";
		//开始遍历实验室设备列表，并生成json
		for(LabRoomDevice labRoomDevice:labRoomDevices){
			json = json + "{\"id\":\"" + labRoomDevice.getId() + "\",\"value\":\""
						+ labRoomDevice.getSchoolDevice().getDeviceNumber() + labRoomDevice.getSchoolDevice().getDeviceName()+"("+labRoomDevice.getLabRoom().getLabRoomName()+")" + "\"},";

		}
		if(json.length()>2){
			json = json.substring(0,json.length()-1) + "]";
		}else{
			json = json + "]";
		}
		
		return json;
	}
	

	/*************************************************************************************
	 * @throws ParseException 
	 * @內容：保存排课实验室设备资源对应的设备预约记录
	 * @作者：魏诚
	 * @日期：2016-05-06
	 *************************************************************************************/
	public void saveTimetableLabroomDeviceReservation(TimetableLabRelated timetableLabRelated,String[] sLabRoomDevice,int term) throws ParseException {
		/*
		 * 对排课预约选定的实验设备进行保存
		 */
		//获取当前设备预约最大id值
		timetableLabRelated = timetableLabRelatedDAO.findTimetableLabRelatedById(timetableLabRelated.getId());
		TimetableLabRelatedDevice timetableLabRelatedDevice = new TimetableLabRelatedDevice();
		if (sLabRoomDevice!=null && sLabRoomDevice.length > 0) { 
			// 如果matchLabs不为空时
			for (int j = 0; j < sLabRoomDevice.length; j++) {
				// 如果遍历的实验室设备是属于当前实验室，则处理
				LabRoomDevice labRoomDevice= labRoomDeviceDAO.findLabRoomDeviceById(Integer.parseInt(sLabRoomDevice[j]));
				if(timetableLabRelated.getLabRoom().getId()==labRoomDevice.getLabRoom().getId()){
					timetableLabRelatedDevice.setLabRoomDevice(labRoomDevice);
					timetableLabRelatedDevice.setTimetableLabRelated(timetableLabRelated);
					TimetableLabRelatedDevice timetableLabRelatedDeviceTemp =timetableLabRelatedDeviceDAO.store(timetableLabRelatedDevice);
					//保存设备预约数据
					LabRoomDeviceReservation labRoomDeviceReservation = new LabRoomDeviceReservation();
					labRoomDeviceReservation.setLabRoomDevice(labRoomDevice);
					//保存相关的实验室关联设备id
					labRoomDeviceReservation.setTimetableLabDevice(timetableLabRelatedDeviceTemp.getId());
					//保存排课
					labRoomDeviceReservation.setAppointmentId(timetableLabRelated.getTimetableAppointment().getId());
					//判断不是连续周次节次
					List<TimetableAppointmentSameNumber> timetableAppointmentSameNumbers = timetableAppointmentSameNumberDAO.executeQuery("select c from TimetableAppointmentSameNumber c where c.timetableAppointment.id="+ timetableLabRelated.getTimetableAppointment().getId(), 0,-1);
					if(timetableAppointmentSameNumbers.size()>1){
						for(TimetableAppointmentSameNumber timetableAppointmentSameNumber:timetableAppointmentSameNumbers){
							
							for(int i= timetableAppointmentSameNumber.getStartWeek() ; i <= timetableAppointmentSameNumber.getEndWeek() ; i++) {  
								String beginTime = shareService.getDateByWeekdayClassWeek(timetableAppointmentSameNumber.getTimetableAppointment().getWeekday(),timetableAppointmentSameNumber.getStartClass(),i,term,0);
								String endTime = shareService.getDateByWeekdayClassWeek(timetableAppointmentSameNumber.getTimetableAppointment().getWeekday(),timetableAppointmentSameNumber.getEndClass(),i,term,1);
								SimpleDateFormat begin = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
								begin.parse(beginTime);
								//保存开始时间
								labRoomDeviceReservation.setBegintime( begin.getCalendar());
								SimpleDateFormat end = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
								end.parse(endTime);
								//保存结束时间
								labRoomDeviceReservation.setEndtime(end.getCalendar());
								//保存学期
								labRoomDeviceReservation.setSchoolTerm(schoolTermDAO.findSchoolTermById(term));
								//保存设备状态为通过
								//labRoomDeviceReservation.setCAuditResult(cAuditResultDAO.findCAuditResultById(2));
								CDictionary cDictionary = shareService.getCDictionaryByCategory("c_audit_result", "2"); 
								labRoomDeviceReservation.setCAuditResult(cDictionary);
								
								//判冲
								String sql = "select c from LabRoomDeviceReservation c where c.begintime >= '" + beginTime + "' and c.begintime <='" + endTime +"' and c.labRoomDevice ="+labRoomDevice.getId() ;
								String sql1 = "select c from LabRoomDeviceReservation c where c.endtime >= '" + beginTime + "' and c.endtime <='" + endTime +"' and c.labRoomDevice ="+labRoomDevice.getId();
                                if(labRoomDeviceReservationDAO.executeQuery(sql, 0,-1).size()>0 ){
                                	for(LabRoomDeviceReservation labRoomDeviceReservationTemp:labRoomDeviceReservationDAO.executeQuery(sql, 0,-1)){
                                		//设置为排课冲突
                                		labRoomDeviceService.alterTimeAfterRefused(labRoomDeviceReservationTemp, 3);
                                		List<LabRoomDeviceReservation> reservationSameList = labRoomDeviceReservationService.findInnerSame(labRoomDeviceReservationTemp.getId());
                                		if (reservationSameList!=null && reservationSameList.size()>0) {
											for (LabRoomDeviceReservation reservationSame : reservationSameList) {
												labRoomDeviceService.alterTimeAfterRefused(reservationSame, 3);
											}
										}
                                		// labRoomDeviceReservationTemp.setCAuditResult(cAuditResultDAO.findCAuditResultById(6));
                                		// labRoomDeviceReservationDAO.merge(labRoomDeviceReservationTemp);
                                	}
                                }
                                if(labRoomDeviceReservationDAO.executeQuery(sql1, 0,-1).size()>0){
                                	//设置为排课冲突
                                	for(LabRoomDeviceReservation labRoomDeviceReservationTemp:labRoomDeviceReservationDAO.executeQuery(sql1, 0,-1)){
                                		//设置为排课冲突
                                		labRoomDeviceService.alterTimeAfterRefused(labRoomDeviceReservationTemp, 3);
                                		List<LabRoomDeviceReservation> reservationSameList = labRoomDeviceReservationService.findInnerSame(labRoomDeviceReservationTemp.getId());
                                		if (reservationSameList!=null && reservationSameList.size()>0) {
											for (LabRoomDeviceReservation reservationSame : reservationSameList) {
												labRoomDeviceService.alterTimeAfterRefused(reservationSame, 3);
											}
										}
                                		// labRoomDeviceReservationTemp.setCAuditResult(cAuditResultDAO.findCAuditResultById(6));
                                		// labRoomDeviceReservationDAO.merge(labRoomDeviceReservationTemp);
                                	}
                                }
                                //保存预约记录
								labRoomDeviceReservationService.saveLabRoomDeviceReservation(labRoomDeviceReservation);
					        }  

						}
					}else{
						for(int i= timetableLabRelated.getTimetableAppointment().getStartWeek() ; i <= timetableLabRelated.getTimetableAppointment().getEndWeek() ; i++) {  
							String beginTime = shareService.getDateByWeekdayClassWeek(timetableLabRelated.getTimetableAppointment().getWeekday(),timetableLabRelated.getTimetableAppointment().getStartClass(),i,term,0);
							String endTime = shareService.getDateByWeekdayClassWeek(timetableLabRelated.getTimetableAppointment().getWeekday(),timetableLabRelated.getTimetableAppointment().getEndClass(),i,term,1);
							SimpleDateFormat begin = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							begin.parse(beginTime);
							//保存开始时间
							labRoomDeviceReservation.setBegintime( begin.getCalendar());
							SimpleDateFormat end = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							end.parse(endTime);
							//保存结束时间
							labRoomDeviceReservation.setEndtime(end.getCalendar());
							//保存学期
							labRoomDeviceReservation.setSchoolTerm(schoolTermDAO.findSchoolTermById(term));
							//保存设备状态为通过
							//labRoomDeviceReservation.setCAuditResult(cAuditResultDAO.findCAuditResultById(2));
							CDictionary cDictionary = shareService.getCDictionaryByCategory("c_audit_result", "2"); 
							labRoomDeviceReservation.setCAuditResult(cDictionary);
							//保存排课
							labRoomDeviceReservation.setAppointmentId(timetableLabRelated.getTimetableAppointment().getId());
							//保存预约者
							labRoomDeviceReservation.setUserByReserveUser(shareService.getUserDetail());
							labRoomDeviceReservation.setContent("排课占用设备资源");
							//判冲
							String sql = "select c from LabRoomDeviceReservation c where c.begintime >= '" + beginTime + "' and c.begintime <='" + endTime +"' and c.labRoomDevice ="+labRoomDevice.getId() ;
							String sql1 = "select c from LabRoomDeviceReservation c where c.endtime >= '" + beginTime + "' and c.endtime <='" + endTime +"' and c.labRoomDevice ="+labRoomDevice.getId() ;
                            if(labRoomDeviceReservationDAO.executeQuery(sql, 0,-1).size()>0||labRoomDeviceReservationDAO.executeQuery(sql1, 0,-1).size()>0){
                            	if(labRoomDeviceReservationDAO.executeQuery(sql, 0,-1).size()>0){
                            		for(LabRoomDeviceReservation labRoomDeviceReservationTemp:labRoomDeviceReservationDAO.executeQuery(sql, 0,-1)){
                                		//设置为排课冲突
                            			labRoomDeviceService.alterTimeAfterRefused(labRoomDeviceReservationTemp, 3);
                            			List<LabRoomDeviceReservation> reservationSameList = labRoomDeviceReservationService.findInnerSame(labRoomDeviceReservationTemp.getId());
                                		if (reservationSameList!=null && reservationSameList.size()>0) {
											for (LabRoomDeviceReservation reservationSame : reservationSameList) {
												labRoomDeviceService.alterTimeAfterRefused(reservationSame, 3);
											}
										}
                                		// labRoomDeviceReservationTemp.setCAuditResult(cAuditResultDAO.findCAuditResultById(6));
                                		// labRoomDeviceReservationDAO.merge(labRoomDeviceReservationTemp);
                                	}
                            	}
                            	
                            	if(labRoomDeviceReservationDAO.executeQuery(sql1, 0,-1).size()>0){
                                	//设置为排课冲突
                                 	for(LabRoomDeviceReservation labRoomDeviceReservationTemp:labRoomDeviceReservationDAO.executeQuery(sql1, 0,-1)){
                                 		//设置为排课冲突
                                 		labRoomDeviceService.alterTimeAfterRefused(labRoomDeviceReservationTemp, 3);
                                 		List<LabRoomDeviceReservation> reservationSameList = labRoomDeviceReservationService.findInnerSame(labRoomDeviceReservationTemp.getId());
                                		if (reservationSameList!=null && reservationSameList.size()>0) {
											for (LabRoomDeviceReservation reservationSame : reservationSameList) {
												labRoomDeviceService.alterTimeAfterRefused(reservationSame, 3);
											}
										}
                                 		// labRoomDeviceReservationTemp.setCAuditResult(cAuditResultDAO.findCAuditResultById(6));
                                 		// labRoomDeviceReservationDAO.merge(labRoomDeviceReservationTemp);
                                 	}
                                 }
                            }
                           
							labRoomDeviceReservationService.saveLabRoomDeviceReservation(labRoomDeviceReservation); 
				        }  
					}
				}
			}
		}
		/*//删除冗余数据
		String sql = "select c from LabRoomDeviceReservation c where c.CAuditResult.id ="+6+" and c.appointmentId=" +timetableLabRelated.getTimetableAppointment().getId();
        List<LabRoomDeviceReservation> labRoomDeviceReservations = labRoomDeviceReservationDAO.executeQuery(sql, 0,-1);
        for(LabRoomDeviceReservation labRoomDeviceReservation:labRoomDeviceReservations){
        	labRoomDeviceReservationDAO.remove(labRoomDeviceReservation);
        }*/
	}
	
	/*************************************************************************************
	 * @description：返回可添加的设备
	 * @author：郑昕茹
	 * @date：2016-10-13
	 *************************************************************************************/
	public List<LabRoomDevice> getValidLabroomDevices(String[] labrooms) {

		//遍历所有选定实验室的实验设备
		//如果实验室具有实验设备，则增加到json中
		List<LabRoomDevice> labRoomDevices =new ArrayList<LabRoomDevice>();
		for(String labroom :labrooms){
			String sql = "select c from LabRoomDevice c where c.labRoom.id =" + labroom;
			//遍历当前实验室所对应的实验室设备，并加入到列表中
			for(LabRoomDevice labRoomDevice:labRoomDeviceDAO.executeQuery(sql, 0,-1)){
				labRoomDevices.add(labRoomDevice);
			}
		}  
		return labRoomDevices;
	}
}