package net.xidlims.service.device;

import java.math.BigDecimal;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import net.xidlims.service.EmptyUtil;
import net.xidlims.domain.SchoolDeviceUse;
import net.xidlims.dao.LabRoomDeviceReservationDAO;
import net.xidlims.dao.SchoolDeviceDAO;
import net.xidlims.dao.SchoolDeviceUseDAO;
import net.xidlims.domain.LabRoomDevice;
import net.xidlims.domain.LabRoomDeviceReservation;
import net.xidlims.domain.SchoolDevice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Spring service that handles CRUD requests for LabRoomDeviceReservation entities
 * 
 */
@Service("LabRoomDeviceReservationService")
@Transactional
public class LabRoomDeviceReservationServiceImpl implements
LabRoomDeviceReservationService {
	
	@Autowired LabRoomDeviceReservationDAO labRoomDeviceReservationDAO;
	@Autowired LabRoomDeviceService labRoomDeviceService;
	@Autowired SchoolDeviceService schoolDeviceService;
	@Autowired SchoolDeviceDAO schoolDeviceDAO;
	@Autowired SchoolDeviceUseDAO schoolDeviceUseDAO;
	@Transactional
	public void deleteLabRoomDeviceReservation(LabRoomDeviceReservation labRoomDeviceReservation) {
		labRoomDeviceReservationDAO.remove(labRoomDeviceReservation);
		labRoomDeviceReservationDAO.flush();
	}

	/************************************
	 *功能：判断当前设备预约是否在导师审核阶段
	 *作者：贺子龙
	 *时间：2015-10-31 
	 ************************************/
	@Override
	public boolean isUnderTeacherAudit(LabRoomDeviceReservation labRoomDeviceReservation) {
		boolean isUnderTeacherAudit = false;
		if (labRoomDeviceReservation.getCAuditResult().getId()==614&&
				labRoomDeviceReservation.getLabRoomDevice().getCDictionaryByTeacherAudit()!=null&&
				labRoomDeviceReservation.getLabRoomDevice().getCDictionaryByTeacherAudit().getId()==621) {//审核中状态且需要导师审核
			
			if (labRoomDeviceReservation.getStage()==0 
					&&labRoomDeviceReservation.getLabRoomDevice().getCDictionaryByTeacherAudit()!=null &&labRoomDeviceReservation.getLabRoomDevice().getCDictionaryByManagerAudit().getId()==621) {
				isUnderTeacherAudit = true;
			}
		}
		return isUnderTeacherAudit;
	}

	/************************************
	 *功能：判断当前设备预约是否在实验室管理员审核阶段
	 *作者：贺子龙
	 *时间：2015-10-31 
	 ***********************************/
	@Override
	public boolean isUnderLabManagerAudit(LabRoomDeviceReservation labRoomDeviceReservation) {
		boolean isUnderLabManagerAudit = false;
		if (labRoomDeviceReservation.getCAuditResult().getId()==614
				&&labRoomDeviceReservation.getLabRoomDevice().getCActiveByLabManagerAudit()!=null
				&&labRoomDeviceReservation.getLabRoomDevice().getCActiveByLabManagerAudit().getId()==621) {//审核中状态且需要实验室管理员审核
			
			if ((labRoomDeviceReservation.getStage()==1 
					&& labRoomDeviceReservation.getLabRoomDevice().getCActiveByLabManagerAudit()!=null && labRoomDeviceReservation.getLabRoomDevice().getCActiveByLabManagerAudit().getId()==621 
					&& labRoomDeviceReservation.getLabRoomDevice().getCDictionaryByTeacherAudit()!=null && labRoomDeviceReservation.getLabRoomDevice().getCDictionaryByTeacherAudit().getId()==621) || 
					(labRoomDeviceReservation.getStage()==0 
					&& labRoomDeviceReservation.getLabRoomDevice().getCDictionaryByTeacherAudit()!=null && labRoomDeviceReservation.getLabRoomDevice().getCDictionaryByTeacherAudit().getId()==622)
					) {
				isUnderLabManagerAudit = true;
			}
		}
		return isUnderLabManagerAudit;
	}

	/************************************
	 *功能：判断当前设备预约是否在设备管理员审核阶段
	 *作者：贺子龙
	 *时间：2015-10-31 
	 ***********************************/
	@Override
	public boolean isUnderManagerAudit(LabRoomDeviceReservation labRoomDeviceReservation) {
		boolean isUnderManagerAudit = false;
		if (labRoomDeviceReservation.getCAuditResult().getId()==614
				&&labRoomDeviceReservation.getLabRoomDevice().getCDictionaryByManagerAudit()!=null
				&&labRoomDeviceReservation.getLabRoomDevice().getCDictionaryByManagerAudit().getId()==621) {//审核中状态且需要设备管理员审核
			
			if (	(labRoomDeviceReservation.getLabRoomDevice().getCDictionaryByManagerAudit()!=null && labRoomDeviceReservation.getLabRoomDevice().getCActiveByLabManagerAudit()!=null && labRoomDeviceReservation.getLabRoomDevice().getCDictionaryByTeacherAudit()!=null)&&
					((labRoomDeviceReservation.getStage()==2 && labRoomDeviceReservation.getLabRoomDevice().getCDictionaryByManagerAudit().getId()==621 && labRoomDeviceReservation.getLabRoomDevice().getCActiveByLabManagerAudit().getId()==621 && labRoomDeviceReservation.getLabRoomDevice().getCDictionaryByTeacherAudit().getId()==621) || 
							(labRoomDeviceReservation.getStage()==1 && (labRoomDeviceReservation.getLabRoomDevice().getCDictionaryByTeacherAudit().getId()==622||labRoomDeviceReservation.getLabRoomDevice().getCActiveByLabManagerAudit().getId()==622))||
							(labRoomDeviceReservation.getStage()==0 && labRoomDeviceReservation.getLabRoomDevice().getCDictionaryByTeacherAudit().getId()==622 && labRoomDeviceReservation.getLabRoomDevice().getCActiveByLabManagerAudit().getId()==622))) {
				isUnderManagerAudit = true;
			}
		}
		
		return isUnderManagerAudit;
	}
	
	
	/************************************
	 *功能：找到innerSame相同的设备预约
	 *作者：贺子龙
	 *时间：2016-04-19
	 ***********************************/
	public List<LabRoomDeviceReservation> findInnerSame(int reservationId){
		LabRoomDeviceReservation reservation = labRoomDeviceReservationDAO.findLabRoomDeviceReservationByPrimaryKey(reservationId);
		String innerSame = reservation.getInnerSame();
		String sql = "select l from LabRoomDeviceReservation l where 1=1";
		sql+=" and l.innerSame like '"+innerSame+"'";
		sql+=" and l.id <>"+reservationId;
		List<LabRoomDeviceReservation> reservations = labRoomDeviceReservationDAO.executeQuery(sql, 0, -1);
		if (reservations.size()>0) {
			return reservations;
		}else {
			return null;
		}
		
	}
	
	/****************************************************************************
	 * @功能：根据设备id查询设备的预约记录
	 * @作者：贺子龙
	 * @日期：2016-05-05
	 ****************************************************************************/
	public void saveLabRoomDeviceReservation(LabRoomDeviceReservation labRoomDeviceReservation){
		LabRoomDeviceReservation reservationOri= labRoomDeviceReservationDAO.store(labRoomDeviceReservation);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr = sdf.format(Calendar.getInstance().getTime());
		// 将设备预约信息同步到该设备的关联设备
		List<SchoolDevice> innerSameDevices = schoolDeviceService.findInnerSameDevice(labRoomDeviceReservation.getLabRoomDevice().getSchoolDevice().getDeviceNumber());
		if (innerSameDevices!=null&&innerSameDevices.size()>0) {
			for (SchoolDevice schoolDevice : innerSameDevices) {
				LabRoomDevice deviceSame = labRoomDeviceService.findLabRoomDeviceByDeviceNumber(schoolDevice.getDeviceNumber());
				if (deviceSame!=null) {
					LabRoomDeviceReservation reservationSame = new LabRoomDeviceReservation();
					reservationSame.copy(reservationOri);
					reservationSame.setLabRoomDevice(deviceSame);
					//reservationSame.setInnerSame(deviceSame.getSchoolDevice().getInnerSame()+"-"+dateStr);
					reservationSame.setInnerDeviceName(deviceSame.getSchoolDevice().getInnerDeviceName().replace("]", "]</br>"));
					reservationSame.setTimetableLabDevice(labRoomDeviceReservation.getTimetableLabDevice());
					//保存排课
					reservationSame.setAppointmentId(labRoomDeviceReservation.getAppointmentId());
					labRoomDeviceReservationDAO.store(reservationSame);
				}
			}
		}
		
	}

	/****************************************************************************
     * 得到设备的使用时间、使用次数、收取费用
     * @author 贺子龙
     * 2016-07-18
     ***************************************************************************/
    public void calculateHoursForSchoolDevice (String deviceNumber, String terms){
        
    	// LabRoomDeviceReservation和labRoomDevice联合查询，条件是设备编号和学期
        String sql = "select l from LabRoomDeviceReservation l where 1=1";		
        sql+=" and l.labRoomDevice.schoolDevice.deviceNumber like '"+deviceNumber+"'";
        sql+=" and l.CAuditResult.id = 2";
        
        
        if (!EmptyUtil.isStringEmpty(terms)) {
			sql+=" and l.schoolTerm.id in "+terms;
		}
        
        List<LabRoomDeviceReservation> reservations = labRoomDeviceReservationDAO.executeQuery(sql, 0 , -1);
        SchoolDevice schoolDevice = schoolDeviceDAO.findSchoolDeviceByPrimaryKey(deviceNumber);
        
        double useHours = 0.0;// 设备使用时长
        double price = 0.0;// 设备收取费用
        int useCount = 0;// 设备使用次数（预约成功的次数）
        if (reservations!=null && reservations.size()>0) {
            for (LabRoomDeviceReservation labRoomDeviceReservation : reservations) {
            	
            	if (labRoomDeviceReservation.getBegintime().after(Calendar.getInstance())) {
					continue;
				}
            	
            	// 累计使用时长
            	double useHour = this.getReserveHoursOfReservation(labRoomDeviceReservation).doubleValue();
                useHours += useHour;
                
                // 累计收取的费用
                // 判断收费类型(1计时 2计件 3计次 4记天)
                int chargeType = 0;
                if (!EmptyUtil.isObjectEmpty(labRoomDeviceReservation.getLabRoomDevice().getCDictionaryByDeviceCharge())) {
                	chargeType = labRoomDeviceReservation.getLabRoomDevice().getCDictionaryByDeviceCharge().getId();
				}
                // 如果没有计费类型，则无法统计收费情况
                
                if (chargeType!=0 && !EmptyUtil.isObjectEmpty(labRoomDeviceReservation.getLabRoomDevice().getPrice())) {
                	switch (chargeType) {
					case 1:// 计时收费
						price += useHour * labRoomDeviceReservation.getLabRoomDevice().getPrice().doubleValue();
						break;
					case 4:// 按天收费
						price += (useHour/24) * labRoomDeviceReservation.getLabRoomDevice().getPrice().doubleValue();
					default:
						price += labRoomDeviceReservation.getLabRoomDevice().getPrice().doubleValue();
						break;
					}
				}
                
                useCount++;
            }
        }
        
        // 使用时长
        BigDecimal userHourBD = new BigDecimal(useHours);
        // 收取费用
        BigDecimal priceBD = new BigDecimal(price);
        if (!EmptyUtil.isStringEmpty(terms)) {// 判空。
        	int term = Integer.parseInt(terms.substring(1, terms.length()-1));
        	SchoolDeviceUse deviceUse = schoolDeviceService.findSchoolDeviceUseByNumberAndTerm(deviceNumber, term);
        	if (EmptyUtil.isObjectEmpty(deviceUse)) {// 未有历史记录，则新建
        		deviceUse = new SchoolDeviceUse();
        		deviceUse.setSchoolDevice(schoolDevice);
        		deviceUse.setTerm(term);
        		deviceUse.setUseCount(useCount);
        		deviceUse.setUseHours(userHourBD);
        		deviceUse.setPrice(priceBD);
        		schoolDeviceUseDAO.store(deviceUse);
        	}else {// 有历史记录，则更新
        		deviceUse.setUseCount(useCount);
        		deviceUse.setUseHours(userHourBD);
        		deviceUse.setPrice(priceBD);
        		schoolDeviceUseDAO.store(deviceUse);
        	}
		}
    }
    
    
    
    /****************************************************************************
	 * 功能：获取一个设备预约的使用机时数
	 * 作者：贺子龙
	 ****************************************************************************/
	public BigDecimal getReserveHoursOfReservation(LabRoomDeviceReservation labRoomDeviceReservation){
		// 通过设备预约找到设备对应的实验中心id
		int centerId = labRoomDeviceReservation.getLabRoomDevice().getLabRoom().getLabAnnex().getLabCenter().getId();
		int reserveHours = 0;
		int reserveMinutes = 0;
		int startHour = labRoomDeviceReservation.getBegintime().get(Calendar.HOUR_OF_DAY);
		int endHour = labRoomDeviceReservation.getEndtime().get(Calendar.HOUR_OF_DAY);
		int startDay = labRoomDeviceReservation.getBegintime().get(Calendar.DAY_OF_YEAR);
		int endDay = labRoomDeviceReservation.getEndtime().get(Calendar.DAY_OF_YEAR);
		int startMinute = labRoomDeviceReservation.getBegintime().get(Calendar.MINUTE);
		int endMinute = labRoomDeviceReservation.getEndtime().get(Calendar.MINUTE);
		if (startDay == endDay) {// 不跨天
			reserveHours = endHour - startHour;
		}else {// 跨天
			if (centerId == 12) { // 纺织中心
				reserveHours += 24 - startHour;// 第一天
				reserveHours += (endDay-startDay-1)*(17-8);// 中间天
				reserveHours += endHour;// 最后一天
				
			}else { // 非纺织中心
				reserveHours += 24 - startHour;// 第一天
				reserveHours += (endDay-startDay-1)*(20-8);// 中间天
				reserveHours += endHour;// 最后一天
			}
		}
		reserveMinutes = endMinute - startMinute;
		double hourInMinute = reserveMinutes/60.0;
		double hour = hourInMinute + reserveHours;
		BigDecimal bg = new BigDecimal(hour);
		return bg;
	}
	
}
