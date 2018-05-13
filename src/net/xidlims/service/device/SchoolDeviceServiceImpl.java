package net.xidlims.service.device;

import java.util.List;

import java.util.Set;

import net.xidlims.domain.SchoolDeviceUse;
import net.xidlims.dao.LabCenterDAO;
import net.xidlims.dao.SchoolDeviceDAO;
import net.xidlims.dao.SchoolDeviceUseDAO;
import net.xidlims.domain.LabCenter;
import net.xidlims.domain.SchoolDevice;
import net.xidlims.service.common.ShareService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SchoolDeviceService")
public class SchoolDeviceServiceImpl implements SchoolDeviceService{
	
	@Autowired
	SchoolDeviceDAO schoolDeviceDAO;
	@Autowired
	private LabCenterDAO labCenterDAO;
	@Autowired
	private ShareService shareService;
	@Autowired
	private
	SchoolDeviceUseDAO schoolDeviceUseDAO; 
	/****************************************************************************
	 * 功能：根据学院编号和schoolDevice对象查询设备数量
	 * 作者：贺子龙
	 * 时间：2015-09-08
	 ****************************************************************************/
	@Override
	public int findSchoolDeviceByAcademyNumberAndSchoolDevice(
			String academyNumber, SchoolDevice schoolDevice) {
		// TODO Auto-generated method stub
		String sql="select count(*) from SchoolDevice d where d.deviceNumber not in(select ld.schoolDevice.deviceNumber from LabRoomDevice ld)";
		/*if(academyNumber!=null&&!academyNumber.equals("")){
			sql+=" and d.schoolAcademy.academyNumber like '%"+academyNumber+"%'";
		}*/
		if(schoolDevice!=null){
			if(schoolDevice.getDeviceName()!=null&&!schoolDevice.getDeviceName().equals("")){//设备名称
				sql+=" and d.deviceName like '%"+schoolDevice.getDeviceName()+"%'";
			}
			if(schoolDevice.getDeviceNumber()!=null&&!schoolDevice.getDeviceNumber().equals("")){//设备编号
				sql+=" and d.deviceNumber ="+schoolDevice.getDeviceNumber();
			}
			if(schoolDevice.getDeviceAddress()!=null&&!schoolDevice.getDeviceAddress().equals("")){//设备编号
				sql+=" and d.deviceAddress like '%"+schoolDevice.getDeviceAddress()+"%'";
			}
			/*//设备保管员
			if(schoolDevice.getUserByKeepUser()!=null&&schoolDevice.getUserByKeepUser().getCname()!=null&&!schoolDevice.getUserByKeepUser().getCname().equals("")){
				sql+=" and d.userByKeepUser.cname like '%"+schoolDevice.getUserByKeepUser().getCname()+"%'";
			}*/
		}
		/*if(maxDeviceNumber!=null&&!maxDeviceNumber.equals("")){
			sql+=" and d.deviceNumber <="+maxDeviceNumber;
		}*/
//		System.out.println(sql);
		return ((Long) schoolDeviceDAO.createQuerySingleResult(sql).getSingleResult()).intValue();
	}
	
	/****************************************************************************
	 * 功能：根据学院编号和schoolDevice对象查询设备并分页
	 * 作者：贺子龙
	 * 时间：2015-09-08
	 ****************************************************************************/
	@Override
	public List<SchoolDevice> findSchoolDeviceByAcademyNumberAndSchoolDevice(
			String academyNumber, SchoolDevice schoolDevice, Integer page,
			int pageSize) {
		// TODO Auto-generated method stub
		String sql="select d from SchoolDevice d where d.deviceNumber not in (select ld.schoolDevice.deviceNumber from LabRoomDevice ld)";
		/*if(academyNumber!=null&&!academyNumber.equals("")){
			sql+=" and d.schoolAcademy.academyNumber like '%"+academyNumber+"%'";
		}*/
		if(schoolDevice!=null){
			if(schoolDevice.getDeviceName()!=null&&!schoolDevice.getDeviceName().equals("")){//设备名称
				sql+=" and d.deviceName like '%"+schoolDevice.getDeviceName()+"%'";
			}
			if(schoolDevice.getDeviceNumber()!=null&&!schoolDevice.getDeviceNumber().equals("")){//设备编号
				sql+=" and d.deviceNumber ="+schoolDevice.getDeviceNumber();
			}
			if(schoolDevice.getDeviceAddress()!=null&&!schoolDevice.getDeviceAddress().equals("")){//设备编号
				sql+=" and d.deviceAddress like '%"+schoolDevice.getDeviceAddress()+"%'";
			}
			/*//设备保管员
			if(schoolDevice.getUserByKeepUser()!=null&&schoolDevice.getUserByKeepUser().getCname()!=null&&!schoolDevice.getUserByKeepUser().getCname().equals("")){
				sql+=" and d.userByKeepUser.cname like '%"+schoolDevice.getUserByKeepUser().getCname()+"%'";
			}*/
		}
		/*if(maxDeviceNumber!=null&&!maxDeviceNumber.equals("")){
			sql+=" and d.deviceNumber <="+maxDeviceNumber;
		}*/
//		System.out.println(sql);
		return schoolDeviceDAO.executeQuery(sql, (page-1)*pageSize,pageSize);
	}
	
	/****************************************************************************
	 * 功能：根据设备编号查询设备
	 * 作者：贺子龙
	 * 时间：2015-09-08
	 ****************************************************************************/
	@Override
	public SchoolDevice findSchoolDeviceByPrimaryKey(String deviceNumber) {
		// TODO Auto-generated method stub
		return schoolDeviceDAO.findSchoolDeviceByPrimaryKey(deviceNumber);
	}
	
	/****************************************************************************
	 * 功能：查询出所有的设备
	 * 作者：李小龙
	 * 时间：2014-07-30
	 ****************************************************************************/
	@Override
	public Set<SchoolDevice> findAllSchoolDevice() {
		// TODO Auto-generated method stub
		return schoolDeviceDAO.findAllSchoolDevices();
	}
	/****************************************************************************
	 * 功能：根据学院编号查询设备
	 * 作者：李小龙
	 ****************************************************************************/
	@Override
	public List<SchoolDevice> findSchoolDeviceByAcademyNumber(
			String academyNumber) {
		// TODO Auto-generated method stub
		String sql="select d from SchoolDevice d where d.schoolAcademy.academyNumber like '%"+academyNumber+"%'";
		return schoolDeviceDAO.executeQuery(sql, 0,-1);
	}
	/****************************************************************************
	 * 功能：根据学院编号和schoolDevice对象查询设备数量
	 * 作者：李小龙
	 ****************************************************************************/
	@Override
	public int findSchoolDeviceByAcademyNumberAndSchoolDevice(
			String academyNumber, SchoolDevice schoolDevice,String maxDeviceNumber) {
		// TODO Auto-generated method stub
		String sql="select count(*) from SchoolDevice d where d.deviceNumber not in(select ld.schoolDevice.deviceNumber from LabRoomDevice ld)";
		if(academyNumber!=null&&!academyNumber.equals("")){
			sql+=" and d.schoolAcademy.academyNumber like '%"+academyNumber+"%'";
		}
		if(schoolDevice!=null){
			if(schoolDevice.getDeviceName()!=null&&!schoolDevice.getDeviceName().equals("")){//设备名称
				sql+=" and d.deviceName like '%"+schoolDevice.getDeviceName()+"%'";
			}
			if(schoolDevice.getDeviceNumber()!=null&&!schoolDevice.getDeviceNumber().equals("")){//设备编号
				sql+=" and d.deviceNumber >='"+schoolDevice.getDeviceNumber()+"'";
			}
			//设备保管员
			if(schoolDevice.getUserByKeepUser()!=null&&schoolDevice.getUserByKeepUser().getCname()!=null&&!schoolDevice.getUserByKeepUser().getCname().equals("")){
				sql+=" and d.userByKeepUser.cname like '%"+schoolDevice.getUserByKeepUser().getCname()+"%'";
			}
		}
		if(maxDeviceNumber!=null&&!maxDeviceNumber.equals("")){
			sql+=" and d.deviceNumber <='"+maxDeviceNumber+"'";
		}
		return ((Long) schoolDeviceDAO.createQuerySingleResult(sql).getSingleResult()).intValue();
	}
	
	
	/****************************************************************************
	 * 功能：根据学院编号和schoolDevice对象查询已添加设备数量
	 * 作者：贺子龙
	 * 时间：2015-09-22 20:57:44
	 ****************************************************************************/
	@Override
	public int findSchoolDeviceByAcademyNumberAndSchoolDeviceNew(
			String academyNumber, SchoolDevice schoolDevice,String maxDeviceNumber) {
		// TODO Auto-generated method stub
		String sql="select count(*) from SchoolDevice d where d.deviceNumber  in(select ld.schoolDevice.deviceNumber from LabRoomDevice ld)";
		if(academyNumber!=null&&!academyNumber.equals("")){
			sql+=" and d.schoolAcademy.academyNumber like '%"+academyNumber+"%'";
		}
		if(schoolDevice!=null){
			if(schoolDevice.getDeviceName()!=null&&!schoolDevice.getDeviceName().equals("")){//设备名称
				sql+=" and d.deviceName like '%"+schoolDevice.getDeviceName()+"%'";
			}
			if(schoolDevice.getDeviceNumber()!=null&&!schoolDevice.getDeviceNumber().equals("")){//设备编号
				sql+=" and d.deviceNumber >='"+schoolDevice.getDeviceNumber()+"'";
			}
			//设备保管员
			if(schoolDevice.getUserByKeepUser()!=null&&schoolDevice.getUserByKeepUser().getCname()!=null&&!schoolDevice.getUserByKeepUser().getCname().equals("")){
				sql+=" and d.userByKeepUser.cname like '%"+schoolDevice.getUserByKeepUser().getCname()+"%'";
			}
		}
		if(maxDeviceNumber!=null&&!maxDeviceNumber.equals("")){
			sql+=" and d.deviceNumber <='"+maxDeviceNumber+"'";
		}
		return ((Long) schoolDeviceDAO.createQuerySingleResult(sql).getSingleResult()).intValue();
	}
	
	/****************************************************************************
	 * 功能：根据学院编号和schoolDevice对象查询设备并分页
	 * 作者：李小龙
	 ****************************************************************************/
	@Override
	public List<SchoolDevice> findSchoolDeviceByAcademyNumberAndSchoolDevice(
			String academyNumber, SchoolDevice schoolDevice,String maxDeviceNumber, Integer page,
			int pageSize) {
		// TODO Auto-generated method stub
		String sql="select d from SchoolDevice d where d.deviceNumber not in(select ld.schoolDevice.deviceNumber from LabRoomDevice ld)";
		if(academyNumber!=null&&!academyNumber.equals("")){
			sql+=" and d.schoolAcademy.academyNumber like '%"+academyNumber+"%'";
		}
		if(schoolDevice!=null){
			if(schoolDevice.getDeviceName()!=null&&!schoolDevice.getDeviceName().equals("")){//设备名称
				sql+=" and d.deviceName like '%"+schoolDevice.getDeviceName()+"%'";
			}
			if(schoolDevice.getDeviceNumber()!=null&&!schoolDevice.getDeviceNumber().equals("")){//设备编号
				sql+=" and d.deviceNumber >='"+schoolDevice.getDeviceNumber()+"'";
			}
			//设备保管员
			if(schoolDevice.getUserByKeepUser()!=null&&schoolDevice.getUserByKeepUser().getCname()!=null&&!schoolDevice.getUserByKeepUser().getCname().equals("")){
				sql+=" and d.userByKeepUser.cname like '%"+schoolDevice.getUserByKeepUser().getCname()+"%'";
			}
		}
		if(maxDeviceNumber!=null&&!maxDeviceNumber.equals("")){
			sql+=" and d.deviceNumber <='"+maxDeviceNumber+"'";
		}
		return schoolDeviceDAO.executeQuery(sql, (page-1)*pageSize,pageSize);
	}
	
	/****************************************************************************
	 * 功能：根据学院编号和schoolDevice对象查询已添加设备
	 * 作者：贺子龙
	 * 时间：2015-09-22 20:52:08
	 ****************************************************************************/
	@Override
	public List<SchoolDevice> findSchoolDeviceByAcademyNumberAndSchoolDeviceNew(
			String academyNumber, SchoolDevice schoolDevice,String maxDeviceNumber, Integer page,
			int pageSize) {
		// TODO Auto-generated method stub
		String sql="select d from SchoolDevice d where d.deviceNumber  in(select ld.schoolDevice.deviceNumber from LabRoomDevice ld)";
		if(academyNumber!=null&&!academyNumber.equals("")){
			sql+=" and d.schoolAcademy.academyNumber like '%"+academyNumber+"%'";
		}
		if(schoolDevice!=null){
			if(schoolDevice.getDeviceName()!=null&&!schoolDevice.getDeviceName().equals("")){//设备名称
				sql+=" and d.deviceName like '%"+schoolDevice.getDeviceName()+"%'";
			}
			if(schoolDevice.getDeviceNumber()!=null&&!schoolDevice.getDeviceNumber().equals("")){//设备编号
				sql+=" and d.deviceNumber >='"+schoolDevice.getDeviceNumber()+"'";
			}
			//设备保管员
			if(schoolDevice.getUserByKeepUser()!=null&&schoolDevice.getUserByKeepUser().getCname()!=null&&!schoolDevice.getUserByKeepUser().getCname().equals("")){
				sql+=" and d.userByKeepUser.cname like '%"+schoolDevice.getUserByKeepUser().getCname()+"%'";
			}
		}
		if(maxDeviceNumber!=null&&!maxDeviceNumber.equals("")){
			sql+=" and d.deviceNumber <='"+maxDeviceNumber+"'";
		}
		return schoolDeviceDAO.executeQuery(sql, (page-1)*pageSize,pageSize);
	}
	
	/****************************************************************************
	 * 功能：找出当前设备的关联设备
	 * 作者：贺子龙
	 ****************************************************************************/
	public List<SchoolDevice> findInnerSameDevice(String deviceNumber){
		SchoolDevice device = findSchoolDeviceByPrimaryKey(deviceNumber);
		Integer innerSame = device.getInnerSame();
		if (innerSame==null||innerSame.equals("")) {
			return null;
		}else {
			String sql = "select d from SchoolDevice d where 1=1";
			sql+=" and d.deviceNumber <> '"+ deviceNumber+"'";
			sql+=" and d.innerSame = "+ innerSame;
			List<SchoolDevice> deviceList = schoolDeviceDAO.executeQuery(sql, 0, -1);
			return deviceList;
		}
	}
	
	/****************************************************************************
	 * 功能：根据设备名称和编号查询学校设备并分页
	 * 作者：贺子龙
	 ****************************************************************************/
	public List<SchoolDevice> findSchoolDeviceByNameAndNumber(Integer cid, int labRoomId, String deviceName, String deviceNumber, int page, int pageSize){
		String sql="select distinct d from SchoolDevice d, LabRoomDevice ld where 1=1";
		sql+=" and ld.schoolDevice.deviceNumber = d.deviceNumber";
		// 中心
		String academyNumber="";
		if (cid!=-1) {
			LabCenter center = labCenterDAO.findLabCenterByPrimaryKey(cid);
			academyNumber = center.getSchoolAcademy().getAcademyNumber();
		}else {
			academyNumber = shareService.getUser().getSchoolAcademy().getAcademyNumber();
		}
		if(academyNumber!=null&&!academyNumber.equals("")){
			sql+=" and d.schoolAcademy.academyNumber like '%"+academyNumber+"%'";
		}
		// 实验室
		if (labRoomId!=0) {
			sql+=" and ld.labRoom.id="+labRoomId;
		}
		// 设备名称
		if (deviceName!=null&&!deviceName.equals("")) {
			sql+=" and d.deviceName like '%"+deviceName+"%'";
		}
		// 设备编号
		if (deviceNumber!=null&&!deviceNumber.equals("")) {
			sql+=" and d.deviceNumber like '%"+deviceNumber+"%'";
		}
		//已经存在于某个组合中的设备不在备选设备中
		sql+=" and d.innerDeviceName is null";
		return schoolDeviceDAO.executeQuery(sql, (page-1)*pageSize,pageSize);
	}
	
	/****************************************************************************
	 * 功能：根据设备名称和编号查询学校设备数量
	 * 作者：贺子龙
	 ****************************************************************************/
	public int countSchoolDeviceByNameAndNumber(Integer cid, int labRoomId, String deviceName, String deviceNumber){
		String sql="select distinct count(d) from SchoolDevice d, LabRoomDevice ld where 1=1";
		sql+=" and ld.schoolDevice.deviceNumber = d.deviceNumber";
		// 中心
		String academyNumber="";
		if (cid!=-1) {
			LabCenter center = labCenterDAO.findLabCenterByPrimaryKey(cid);
			academyNumber = center.getSchoolAcademy().getAcademyNumber();
		}else {
			academyNumber = shareService.getUser().getSchoolAcademy().getAcademyNumber();
		}
		if(academyNumber!=null&&!academyNumber.equals("")){
			sql+=" and d.schoolAcademy.academyNumber like '%"+academyNumber+"%'";
		}
		// 实验室
		if (labRoomId!=0) {
			sql+=" and ld.labRoom.id="+labRoomId;
		}
		// 设备名称
		if (deviceName!=null&&!deviceName.equals("")) {
			sql+=" and d.deviceName like '%"+deviceName+"%'";
		}
		// 设备编号
		if (deviceNumber!=null&&!deviceNumber.equals("")) {
			sql+=" and d.deviceNumber like '%"+deviceNumber+"%'";
		}
		//已经存在于某个组合中的设备不在备选设备中
		sql+=" and d.innerDeviceName is null";
		return ((Long) schoolDeviceDAO.createQuerySingleResult(sql).getSingleResult()).intValue();
	}
	
	/****************************************************************************
	 * 功能：查询设备套数
	 * 作者：贺子龙
	 ****************************************************************************/
	public int maxSchoolDeviceSet(){
		String sql="select max(innerSame) from SchoolDevice d where 1=1";
		sql+=" and d.innerSame is not null";
		int maxInnerSame=0;
		if (schoolDeviceDAO.createQuerySingleResult(sql).getSingleResult()!=null) {
			maxInnerSame = ((Integer) schoolDeviceDAO.createQuerySingleResult(sql).getSingleResult()).intValue();
		}
		return maxInnerSame;
	}
	
	/****************************************************************************
	 * 功能：保存设备
	 * 作者：贺子龙
	 ****************************************************************************/
	public void saveSchoolDevice(SchoolDevice device){
		schoolDeviceDAO.store(device);
		schoolDeviceDAO.flush();
	}
	
	/****************************************************************************
	 * 功能：根据关联设备编号查询的设备组合
	 * 作者：贺子龙
	 ****************************************************************************/
	public List<SchoolDevice> findSchoolDeviceSet(int innerSame){
		String sql="select d from SchoolDevice d where 1=1";
		sql+=" and d.innerSame ="+innerSame;
		return schoolDeviceDAO.executeQuery(sql, 0, -1);
	}
	
	/****************************************************************************
	 * 设备管理--设备使用情况报表--根据设备编号和学期唯一确定一条设备使用情况记录
	 * @author 贺子龙
	 * 2016-07-21
	 ****************************************************************************/
	public SchoolDeviceUse findSchoolDeviceUseByNumberAndTerm(String deviceNumber, int term){
		String sql = "select s from SchoolDeviceUse s where 1=1";
		sql+=" and s.term="+term;
		sql+=" and s.schoolDevice.deviceNumber like '"+deviceNumber+"'";
		List<SchoolDeviceUse> devices = schoolDeviceUseDAO.executeQuery(sql, 0, -1);
		if (devices!=null && devices.size()>0) {
			return devices.get(0);
		}else {
			return null;
		}
	}
}
