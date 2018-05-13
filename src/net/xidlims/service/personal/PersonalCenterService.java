package net.xidlims.service.personal;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import net.xidlims.domain.CActive;
//import net.xidlims.domain.CLabAnnexType;
//import net.xidlims.domain.CLabRoomType;
import net.xidlims.domain.LabReservation;
import net.xidlims.domain.LabRoom;
import net.xidlims.domain.LabRoomAgent;
//import net.xidlims.domain.LabRoomDeviceReservation;
import net.xidlims.dao.OperationItemDAO;
import net.xidlims.domain.OperationItem;



public interface PersonalCenterService {

	public List<LabReservation> findLabreservationmanage(String username) ;

	/****************************************************************************
	 * 功能：根据当前用户查找实验室设备预约 前4条的记录
	 * 作者：方正
	 ****************************************************************************/

	/*public List<LabRoomDeviceReservation> findAllLabRoomDeviceReservationByUsername(String username);
	
	public List<LabRoomDeviceReservation> getAllLabRoomDeviceReservationByUsername(String username);
	
	public List<LabRoomDeviceReservation> getAllLabRoomDeviceReservationByUsername(
			String username ,int page ,int pageSize);
	
	public List<LabRoomDeviceReservation> getAllLabRoomDeviceReservationByLabRoomDeviceReservation(
			LabRoomDeviceReservation labRoomDeviceReservation);
	
	public List<LabRoomDeviceReservation> getAllLabRoomDeviceReservationByLabRoomDeviceReservation(
			LabRoomDeviceReservation labRoomDeviceReservation , int page , int pageSize);*/
	
	public List<OperationItem> getoperationItemByUserId(String username,int page,int pageSize);
	
	public List<OperationItem> getoperationItemByUsername(String username);
	public List<OperationItem> getcoperationItemByUsername(String username);
	public List<OperationItem> getoperationItemByUserId(String username) ;
	
	public List<OperationItem> getoperationItemByOperationItem(OperationItem operationItem,int page,int pageSize);
	
	public List<OperationItem> getoperationItemByOperationItem(OperationItem operationItem);
	
	/*public List<LabRoomDeviceReservation> getAllLabRoomDeviceReservationBy(
			LabRoomDeviceReservation labRoomDeviceReservation);
	public List<LabRoomDeviceReservation> getAllLabRoomDeviceReservationBy(
			LabRoomDeviceReservation labRoomDeviceReservation ,int page ,int pageSize);*/
}