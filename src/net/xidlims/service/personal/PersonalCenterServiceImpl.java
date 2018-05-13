package net.xidlims.service.personal;

import java.util.List;


import net.xidlims.dao.OperationItemDAO;
import net.xidlims.dao.LabReservationDAO;
import net.xidlims.domain.LabReservation;
//import net.xidlims.domain.LabRoomDeviceReservation;
import net.xidlims.domain.OperationItem;
//import net.xidlims.dao.LabRoomDeviceReservationDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("PersonalCenterService")
public class PersonalCenterServiceImpl implements  PersonalCenterService {

	@Autowired OperationItemDAO operationItemDAO;
	@Autowired LabReservationDAO labReservationDAO;
//	@Autowired LabRoomDeviceReservationDAO labRoomDeviceReservationDAO;

	
	//方正  根据当前用户查找改用户的实验室预约  获取前4行的数据
	@Override
	public List<LabReservation> findLabreservationmanage(String username) {
		String sql="select l from LabReservation l where 1=1 and l.user.username='"+username+"'";
		sql+=" order by l.id asc";
		return labReservationDAO.executeQuery(sql,0,5);
	}
	
	/****************************************************************************
	 * 功能：根据当前用户查找实验室设备预约 
	 * 作者：方正
	 ****************************************************************************/
	/*@Override
	public List<LabRoomDeviceReservation> findAllLabRoomDeviceReservationByUsername(
			String username) {
		//当前登录人为预约人或者老师
		String sql="select l from LabRoomDeviceReservation l where l.userByReserveUser.username='"+username+"' " +
				"or l.userByTeacher.username='"+username+"'";
		sql+=" order by l.time desc";
		List<LabRoomDeviceReservation> list=labRoomDeviceReservationDAO.executeQuery(sql,0,-1);
		//当前登录人为实验室
		String s="select l from LabRoomDeviceReservation l where l.labRoomDevice.id in (select d.id from LabRoomDevice d where d.labRoom.id in(select m.id from LabRoomAdmin m where m.user.username='"+username+"'))";
		List<LabRoomDeviceReservation> list2=labRoomDeviceReservationDAO.executeQuery(s,0,5);
		return list;
	}*/
	
	
	/****************************************************************************
	 * 功能：根据当前用户查找实验室设备预约   获取所有的记录  不包含分页信息
	 * 作者：方正
	 ****************************************************************************/
	/*@Override
	public List<LabRoomDeviceReservation> getAllLabRoomDeviceReservationByUsername(
			String username) {
		String sql="select l from LabRoomDeviceReservation l where 1=1 and l.userByReserveUser.username='"+username+"'";
		sql+=" order by l.time desc";
		return labRoomDeviceReservationDAO.executeQuery(sql);
	}*/
	
	/****************************************************************************
	 * 功能：根据当前用户查找实验室设备预约   获取所有的记录  包含分页信息
	 * 作者：方正
	 ****************************************************************************/
	/*@Override
	public List<LabRoomDeviceReservation> getAllLabRoomDeviceReservationByUsername(
			String username ,int page ,int pageSize) {
		// TODO Auto-generated method stub
		String sql="select l from LabRoomDeviceReservation l where 1=1 and l.userByReserveUser.username='"+username+"'";
		sql+=" order by l.time desc";
		return labRoomDeviceReservationDAO.executeQuery(sql,(page-1)*pageSize,pageSize);
	}*/
	
	
	/****************************************************************************
	 * 功能：根据当前LabRoomDeviceReservation对象查找实验室设备预约   获取所有的记录  不包含分页信息
	 * 作者：方正
	 ****************************************************************************/
	/*@Override
	public List<LabRoomDeviceReservation> getAllLabRoomDeviceReservationByLabRoomDeviceReservation(
			LabRoomDeviceReservation labRoomDeviceReservation) {
		// TODO Auto-generated method stub
		String sql="select l from LabRoomDeviceReservation l where 1=1 and ";
		if(labRoomDeviceReservation.getId()!=null && labRoomDeviceReservation.getId()!=0){
			sql+=" l.id like '"+labRoomDeviceReservation.getId()+"'";
		}
		sql+=" order by l.id desc";
		return labRoomDeviceReservationDAO.executeQuery(sql);
	}*/
	
	/****************************************************************************
	 * 功能：根据当前用户查找实验室设备预约   获取所有的记录  包含分页信息
	 * 作者：方正
	 ****************************************************************************/
	/*@Override
	public List<LabRoomDeviceReservation> getAllLabRoomDeviceReservationByLabRoomDeviceReservation(
			LabRoomDeviceReservation labRoomDeviceReservation , int page , int pageSize) {
		// TODO Auto-generated method stub
		String sql="select l from LabRoomDeviceReservation l where 1=1 and ";
		if(labRoomDeviceReservation.getId()!=null && labRoomDeviceReservation.getId()!=0){
			sql+=" l.id like '"+labRoomDeviceReservation.getId()+"'";
		}
		sql+=" order by l.id desc";
		return labRoomDeviceReservationDAO.executeQuery(sql,(page-1)*pageSize,pageSize);
	}*/
	
	
	/***
	 * 根据当前用户username获取该用户实验室项目信息 （控制了只选取前4条记录）
	 * 
	 * @param username
	 *            当前用户
	 * @return
	 */
	@Override
	public List<OperationItem> getoperationItemByUsername(String username) {
		
		String sql="select o from OperationItem o where 1=1 and o.user.username='"+username+"'";
		sql+=" order by o.id asc" ;
		return  operationItemDAO.executeQuery(sql ,0 ,5);
	}
	public List<OperationItem> getcoperationItemByUsername(String username){
		String sql="select o from OperationItem o where 1=1 and o.complex=1 and o.user.username='"+username+"'";
		sql+=" order by o.id asc" ;
		return  operationItemDAO.executeQuery(sql ,0 ,5);
	
	}
	
	/***
	 * 根据当前用户username获取该用户实验室项目信息  不包含分页 @方正
	 * 
	 * @param username
	 *            当前用户
	 * @return
	 */
	@Override
	public List<OperationItem> getoperationItemByUserId(String username) {
		String sql="select o from OperationItem o where 1=1 and o.user.username='"+username+"'";
		return  operationItemDAO.executeQuery(sql);
	}
	

	/***
	 * 根据当前用户username获取该用户实验室项目信息   包含分页信息   @方正 
	 * 
	 * @param username
	 *            当前用户
	 * @return
	 */
	@Override
	public List<OperationItem> getoperationItemByUserId(String username,int page,int pageSize) {
		String sql="select o from OperationItem o where 1=1 and o.user.username='"+username+"'";
		return  operationItemDAO.executeQuery(sql,(page-1)*pageSize,pageSize);
	}
	
	
	/***
	 * 根据当前用户对象 operationItem获取该用户实验室项目信息   包含分页信息   @方正 
	 * 
	 * @param operationItem
	 *            用户实验项目
	 * @return
	 */
	@Override
	public List<OperationItem> getoperationItemByOperationItem(OperationItem operationItem,int page,int pageSize) {
		String sql="select o from OperationItem o where 1=1 ";
		sql+="and o.id like '%"+operationItem.getId()+"%'";
		return  operationItemDAO.executeQuery(sql,(page-1)*pageSize,pageSize);
	}
	
	/***
	 * 根据当前用户对象 operationItem获取该用户实验室项目信息   不包含分页信息   @方正 
	 * 
	 * @param operationItem
	 *            用户实验项目
	 * @return
	 */
	@Override
	public List<OperationItem> getoperationItemByOperationItem(OperationItem operationItem) {
		String sql="select o from OperationItem o where 1=1 ";
		sql+="and o.id like '%"+operationItem.getId()+"%'";
		return  operationItemDAO.executeQuery(sql);
	}
	
	
	/*@Override
	public List<LabRoomDeviceReservation> getAllLabRoomDeviceReservationBy(
			LabRoomDeviceReservation labRoomDeviceReservation) {
		// TODO Auto-generated method stub
		String sql="select l from LabRoomDeviceReservation l where 1=1";
		return labRoomDeviceReservationDAO.executeQuery(sql);
	}*/
	
	
	/*@Override
	public List<LabRoomDeviceReservation> getAllLabRoomDeviceReservationBy(
			LabRoomDeviceReservation labRoomDeviceReservation ,int page ,int pageSize) {
		// TODO Auto-generated method stub
		String sql="select l from LabRoomDeviceReservation l where 1=1";
		return labRoomDeviceReservationDAO.executeQuery(sql,(page-1)*pageSize,pageSize);
	}*/
	
}