package net.xidlims.service.device;

import java.util.List;

import net.xidlims.domain.LabRoomDeviceReservation;


/**
 * Spring service that handles CRUD requests for LabRoomDeviceReservation entities
 * 
 */
public interface LabRoomDeviceReservationService {

	
	/**
	 * Delete an existing LabRoomDeviceReparation entity
	 * 
	 */
	public void deleteLabRoomDeviceReservation(LabRoomDeviceReservation labRoomDeviceReservation);
	/************************************
	 *功能：判断当前设备预约是否在导师审核阶段
	 *作者：贺子龙
	 *时间：2015-10-31 
	 ************************************/
	public boolean isUnderTeacherAudit(LabRoomDeviceReservation labRoomDeviceReservation);
	
	/************************************
	 *功能：判断当前设备预约是否在实验室管理员审核阶段
	 *作者：贺子龙
	 *时间：2015-10-31 
	 ***********************************/
	public boolean isUnderLabManagerAudit(LabRoomDeviceReservation labRoomDeviceReservation);
	
	/************************************
	 *功能：判断当前设备预约是否在设备管理员审核阶段
	 *作者：贺子龙
	 *时间：2015-10-31 
	 ***********************************/
	public boolean isUnderManagerAudit(LabRoomDeviceReservation labRoomDeviceReservation);
	
	
	/************************************
	 *功能：找到innerSame相同的设备预约
	 *作者：贺子龙
	 *时间：2016-04-19
	 ***********************************/
	public List<LabRoomDeviceReservation> findInnerSame(int reservationId);
	
	/****************************************************************************
	 * @功能：根据设备id查询设备的预约记录
	 * @作者：贺子龙
	 * @日期：2016-05-05
	 ****************************************************************************/
	public void saveLabRoomDeviceReservation(LabRoomDeviceReservation labRoomDeviceReservation);
}
