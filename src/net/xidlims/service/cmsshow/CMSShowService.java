package net.xidlims.service.cmsshow;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.xidlims.common.LabAttendance;
import net.xidlims.domain.CommonHdwlog;
import net.xidlims.domain.LabReservation;
import net.xidlims.domain.LabRoom;
import net.xidlims.domain.LabRoomDevice;
import net.xidlims.domain.LabRoomDeviceReservation;

import org.springframework.transaction.annotation.Transactional;

import excelTools.Labreservationlist;



public interface CMSShowService {
	/*************************************************************************************
	 * @內容：实验教学的总记录数-校级页面
	 * @作者：叶明盾
	 * @日期：2014-09-10
	 *************************************************************************************/
	//@Transactional
	//public int getAllOutlineTotalRecords();
	
	/*************************************************************************************
	 * @內容：查找所有的实验教学信息-校级页面
	 * @作者：叶明盾
	 * @日期：2014-09-10
	 *************************************************************************************/
	//public List<OperationOutline> findAllOutlines(int curr, int size);
	
	/*************************************************************************************
	 * @內容：实验室开放的总记录数-校级页面
	 * @作者：叶明盾
	 * @日期：2014-09-10
	 *************************************************************************************/
	@Transactional
	public int getAllLabReservationTotalRecords();
	
	/*************************************************************************************
	 * @內容：查找所有的实验室开放-校级页面
	 * @作者：叶明盾
	 * @日期：2014-09-10
	 *************************************************************************************/
	public List<LabReservation> findAllLabReservations(int curr, int size);
	
	/*************************************************************************************
	 * @內容：根据实验室开放的情况查找出相应的周次、星期、节次-校级页面
	 * @作者：叶明盾
	 * @日期：2014-09-10
	 *************************************************************************************/
	public  List<Labreservationlist> findLabReservationDate(int curr, int size);
	
	/*************************************************************************************
	 * @內容：仪器共享的总记录数-校级页面
	 * @作者：叶明盾
	 * @日期：2014-09-10
	 *************************************************************************************/
	@Transactional
	public int getAllLabRoomDeviceReservationTotalRecords();
	
	/*************************************************************************************
	 * @內容：查找所有的仪器共享-校级页面
	 * @作者：叶明盾
	 * @日期：2014-09-10
	 *************************************************************************************/
	public List<LabRoomDeviceReservation> findAllLabRoomDeviceReservations(int curr, int size);
	
	/*************************************************************************************
	 * @內容：实验教学的总记录数-计算机学院
	 * @作者：叶明盾
	 * @日期：2014-09-23
	 *************************************************************************************/
	//@Transactional
	//public int getCompOutlineTotalRecords();
	
	/*************************************************************************************
	 * @內容：查找所有的实验教学信息-计算机学院
	 * @作者：叶明盾
	 * @日期：2014-09-23
	 *************************************************************************************/
	//public List<OperationOutline> findCompOutlines(int curr, int size);
	
	/*************************************************************************************
	 * @內容：实验室开放的总记录数-计算机学院
	 * @作者：叶明盾
	 * @日期：2014-09-23
	 *************************************************************************************/
	@Transactional
	public int getCompLabReservationTotalRecords();
	
	/*************************************************************************************
	 * @內容：查找所有的实验室开放-计算机学院
	 * @作者：叶明盾
	 * @日期：2014-09-23
	 *************************************************************************************/
	public List<LabReservation> findCompLabReservations(int curr, int size);
	
	/*************************************************************************************
	 * @內容：仪器共享的总记录数-计算机学院
	 * @作者：叶明盾
	 * @日期：2014-09-23
	 *************************************************************************************/
	@Transactional
	public int getCompLabRoomDeviceReservationTotalRecords();
	
	/*************************************************************************************
	 * @內容：查找所有的仪器共享-计算机学院
	 * @作者：叶明盾
	 * @日期：2014-09-23
	 *************************************************************************************/
	public List<LabRoomDeviceReservation> findCompLabRoomDeviceReservations(int curr, int size);
	
	/**
	 * 实验教学的总记录数-各个学院
	 * @param academyId 学院的ID
	 * @return
	 * @author 叶明盾
	 * @date 2014-11-11 上午12:35:23
	 */
	//public int getAcademyOutlineTotalRecords(String academyId);
	
	/**
	 * 查找所有的实验教学信息-各个学院
	 * @param curr
	 * @param size
	 * @param academyId 学院的ID
	 * @return
	 * @author 叶明盾
	 * @date 2014-11-11 上午12:45:47
	 */
	//public List<OperationOutline> findAcademyOutlines(int curr, int size,String academyId);
	
	/**
	 * 实验室开放的总记录数-各个学院
	 * @param academyId 学院Id
	 * @return
	 * @author 叶明盾
	 * @date 2014-11-11 上午12:47:07
	 */
	public int getAcademyLabReservationTotalRecords(String academyId);
	
	/**
	 * 查找所有的实验室开放-各个学院
	 * @param curr
	 * @param size
	 * @param academyId 学院Id
	 * @return
	 * @author 叶明盾
	 * @date 2014-11-11 上午12:48:15
	 */
	public List<LabReservation> findAcademyLabReservations(int curr, int size,String academyId);
	
	/**
	 * 仪器共享的总记录数-各个学院
	 * @param academyId 学院Id
	 * @return
	 * @author 叶明盾
	 * @date 2014-11-11 上午12:49:03
	 */
	public int getAcademyLabRoomDeviceReservationTotalRecords(String academyId);
	
	/**
	 * 查找所有的仪器共享-各个学院
	 * @param curr
	 * @param size
	 * @param academyId 学院Id
	 * @return
	 * @author 叶明盾
	 * @date 2014-11-11 上午12:49:57
	 */
	public List<LabRoomDeviceReservation> findAcademyLabRoomDeviceReservations(int curr, int size,String academyId);
	/*************************************************************************************
	 * @功能：根据学院查询实验室
	 * @作者： 李小龙
	 *************************************************************************************/
	public List<LabRoom> findLabRoomBySchoolAcademy(LabRoom labRoom);
	/*************************************************************************************
	 * @功能：根据学院查询实验室并分页
	 * @作者： 李小龙
	 *************************************************************************************/
	public List<LabRoom> findLabRoomBySchoolAcademy(
			LabRoom labRoom, int page, int pageSize);
	/*************************************************************************************
	 * @功能：根据学院查询实验室并分页--默认显示当前学院的
	 * @作者： 贺子龙 
	 *************************************************************************************/
	public List<LabRoom> findLabRoomBySchoolAcademyDefault(
			LabRoom labRoom, int page, int pageSize,int type, int iLabCenter);
	/*************************************************************************************
	 * @功能：根据学院查询设备
	 * @作者： 李小龙
	 *************************************************************************************/
	public List<LabRoomDevice> findLabRoomDeviceBySchoolAcademy(
			LabRoomDevice labRoomDevice);
	/*************************************************************************************
	 * @功能：根据学院查询设备并分页
	 * @作者： 李小龙
	 *************************************************************************************/
	public List<LabRoomDevice> findLabRoomDeviceBySchoolAcademy(
			LabRoomDevice labRoomDevice, int page, int pageSize);
	/*************************************************************************************
	 * @功能：根据ip查询刷卡记录
	 * @作者： 李小龙
	 *************************************************************************************/
	public List<CommonHdwlog> findLabRoomAccessByIp(String ip,String port);
	/*************************************************************************************
	 * @功能：根据ip查询刷卡记录并分页
	 * @作者： 李小龙
	 *************************************************************************************/
	public List<CommonHdwlog> findLabRoomAccessByIp(String ip,String port, Integer page,
			int pageSize);
	
	/*************************************************************************************
	 * @功能：根据ip查询刷卡记录并分页--增加查询功能
	 * @作者：贺子龙
	 *************************************************************************************/
	public List<LabAttendance> findLabRoomAccessByIp(CommonHdwlog commonHdwlog,String ip,String port, Integer page,
			int pageSize,HttpServletRequest request);
	/*************************************************************************************
	 * @功能：根据ip查询刷卡记录数量--增加查询功能
	 * @作者：贺子龙
	 *************************************************************************************/
	public int findLabRoomAccessByIpCount(CommonHdwlog commonHdwlog,String ip,String port,HttpServletRequest request);
	
	/*************************************************************************************
	 * @功能：根据实验室id查询实验室使用记录
	 * @作者： 李小龙
	 *************************************************************************************/
	public List<LabReservation> findLabRoomUseRecord(Integer roomId);
	/*************************************************************************************
	 * @功能：根据实验室id查询实验室使用记录并分页
	 * @作者： 李小龙
	 *************************************************************************************/
	public List<LabReservation> findLabRoomUseRecord(Integer roomId, int page,
			int pageSize);
	/*************************************************************************************
	 * @功能：根据设备id查询设备使用记录
	 * @作者： 李小龙
	 *************************************************************************************/
	public List<LabRoomDeviceReservation> findLabRoomDeviceUseRecord(Integer deviceId);
	/*************************************************************************************
	 * @功能：根据设备id查询设备使用记录并分页
	 * @作者： 李小龙
	 *************************************************************************************/
	public List<LabRoomDeviceReservation> findLabRoomDeviceUseRecord(
			Integer deviceId, int page, int pageSize);
	/*************************************************************************************
	 * @功能：根据楼栋编号查询实验室
	 * @作者： 李小龙
	 *************************************************************************************/
	public List<LabRoom> findLabRoomByBuildNumber(String buildNumber);
	/*************************************************************************************
	 * @功能：根据楼栋编号查询实验室并分页
	 * @作者： 李小龙
	 *************************************************************************************/
	public List<LabRoom> findLabRoomByBuildNumber(String buildNumber, int page,
			int pageSize);
	/*************************************************************************************
	 * @功能：根据学院编号查询实验室设备
	 * @作者： 李小龙
	 *************************************************************************************/
	public List<LabRoomDevice> findLabRoomDeviceByAcademy(String academyNumber);
	/*************************************************************************************
	 * @功能：根据学院编号查询实验室设备并分页
	 * @作者： 李小龙
	 *************************************************************************************/
	public List<LabRoomDevice> findLabRoomDeviceByAcademy(String academyNumber,
			int page, int pageSize);
}