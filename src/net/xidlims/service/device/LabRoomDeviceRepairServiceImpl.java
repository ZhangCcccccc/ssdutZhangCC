package net.xidlims.service.device;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

import net.xidlims.dao.CDictionaryDAO;
import net.xidlims.dao.LabRoomDAO;
import net.xidlims.dao.LabRoomDeviceRepairDAO;
import net.xidlims.dao.SchoolDeviceDAO;
import net.xidlims.dao.UserDAO;
import net.xidlims.domain.LabRoom;
import net.xidlims.domain.LabRoomDeviceRepair;
import net.xidlims.domain.SchoolDevice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Spring service that handles CRUD requests for LabRoomDeviceRepair entities
 * 
 */

@Service("LabRoomDeviceRepairService")
@Transactional
public class LabRoomDeviceRepairServiceImpl implements
		LabRoomDeviceRepairService {

	/**
	 * DAO injected by Spring that manages LabRoomDeviceRepair entities
	 * 
	 */
	@Autowired
	private LabRoomDeviceRepairDAO labRoomDeviceRepairDAO;

	/**
	 * DAO injected by Spring that manages SchoolDevice entities
	 * 
	 */
	@Autowired
	private SchoolDeviceDAO schoolDeviceDAO;

	@Autowired
	private LabRoomDAO labRoomDAO;

	/**
	 * Instantiates a new LabRoomDeviceRepairServiceImpl.
	 *
	 */
	public LabRoomDeviceRepairServiceImpl() {
	}

	/**
	 * Delete an existing LabRoomDeviceRepair entity
	 * 
	 */
	@Transactional
	public void deleteLabRoomDeviceRepair(LabRoomDeviceRepair labroomdevicerepair) {
		labRoomDeviceRepairDAO.remove(labroomdevicerepair);
		labRoomDeviceRepairDAO.flush();
	}

	/**
	 */
	@Transactional
	public LabRoomDeviceRepair findLabRoomDeviceRepairByPrimaryKey(Integer id) {
		return labRoomDeviceRepairDAO.findLabRoomDeviceRepairByPrimaryKey(id);
	}

	/**
	 * Save an existing LabRoomDeviceRepair entity
	 * 
	 */
	@Transactional
	public void saveLabRoomDeviceRepair(LabRoomDeviceRepair labRoomDeviceRepair) {
//		LabRoomDeviceRepair exitingLabRoomDeviceRepair = labRoomDeviceRepairDAO.
//				findLabRoomDeviceRepairByPrimaryKey(labRoomDeviceRepair.getId());
//		if(exitingLabRoomDeviceRepair != null){
//			if(exitingLabRoomDeviceRepair != labRoomDeviceRepair){
//				exitingLabRoomDeviceRepair.setId(labRoomDeviceRepair.getId());
//				exitingLabRoomDeviceRepair.setCLabRoomDeviceChoice(exitingLabRoomDeviceRepair.getCLabRoomDeviceChoice());
//				exitingLabRoomDeviceRepair.setCLabRoomDevicePartition(exitingLabRoomDeviceRepair.getCLabRoomDevicePartition());
//				exitingLabRoomDeviceRepair.setCLabRoomDeviceRepairStatus(exitingLabRoomDeviceRepair.getCLabRoomDeviceRepairStatus());
//				exitingLabRoomDeviceRepair.setDescription(exitingLabRoomDeviceRepair.getDescription());
//				exitingLabRoomDeviceRepair.setHardwareFailure(exitingLabRoomDeviceRepair.getHardwareFailure());
//				exitingLabRoomDeviceRepair.setRepairRecords(exitingLabRoomDeviceRepair.getRepairRecords());
//				exitingLabRoomDeviceRepair.setSchoolDevice(exitingLabRoomDeviceRepair.getSchoolDevice());
//				exitingLabRoomDeviceRepair.setSoftwareFailure(exitingLabRoomDeviceRepair.getSoftwareFailure());
//				exitingLabRoomDeviceRepair.setUser(exitingLabRoomDeviceRepair.getUser());
//			}
			labRoomDeviceRepair.setRepairTime(Calendar.getInstance());
			labRoomDeviceRepair.setCreateTime(Calendar.getInstance());
			labRoomDeviceRepair=labRoomDeviceRepairDAO.store(labRoomDeviceRepair);
		//}
		//labRoomDeviceRepair=labRoomDeviceRepairDAO.store(labRoomDeviceRepair);
	}

	/**
	 * Load an existing LabRoomDeviceRepair entity
	 * 
	 */
	@Transactional
	public Set<LabRoomDeviceRepair> loadLabRoomDeviceRepairs() {
		return labRoomDeviceRepairDAO.findAllLabRoomDeviceRepairs();
	}

	/**
	 * Save an existing SchoolDevice entity
	 * 
	 */
	@Transactional
	public LabRoomDeviceRepair saveLabRoomDeviceRepairSchoolDevice(Integer id, SchoolDevice related_schooldevice) {
		LabRoomDeviceRepair labroomdevicerepair = labRoomDeviceRepairDAO.findLabRoomDeviceRepairByPrimaryKey(id, -1, -1);
		SchoolDevice existingschoolDevice = schoolDeviceDAO.findSchoolDeviceByPrimaryKey(related_schooldevice.getDeviceNumber());

		// copy into the existing record to preserve existing relationships
		if (existingschoolDevice != null) {
			existingschoolDevice.setDeviceNumber(related_schooldevice.getDeviceNumber());
			existingschoolDevice.setDeviceName(related_schooldevice.getDeviceName());
			existingschoolDevice.setDeviceEnName(related_schooldevice.getDeviceEnName());
			existingschoolDevice.setDevicePattern(related_schooldevice.getDevicePattern());
			existingschoolDevice.setDeviceFormat(related_schooldevice.getDeviceFormat());
			existingschoolDevice.setDeviceUseDirection(related_schooldevice.getDeviceUseDirection());
			existingschoolDevice.setDeviceBuyDate(related_schooldevice.getDeviceBuyDate());
			existingschoolDevice.setDeviceAddress(related_schooldevice.getDeviceAddress());
			existingschoolDevice.setDeviceCountry(related_schooldevice.getDeviceCountry());
			existingschoolDevice.setDevicePrice(related_schooldevice.getDevicePrice());
			existingschoolDevice.setDeviceAccountedDate(related_schooldevice.getDeviceAccountedDate());
			existingschoolDevice.setDeviceSupplier(related_schooldevice.getDeviceSupplier());
			existingschoolDevice.setCreatedAt(related_schooldevice.getCreatedAt());
			existingschoolDevice.setUpdatedAt(related_schooldevice.getUpdatedAt());
			existingschoolDevice.setId(related_schooldevice.getId());
			existingschoolDevice.setProjectSource(related_schooldevice.getProjectSource());
			existingschoolDevice.setManufacturer(related_schooldevice.getManufacturer());
			existingschoolDevice.setAcademyMemo(related_schooldevice.getAcademyMemo());
			existingschoolDevice.setDeviceStatus(related_schooldevice.getDeviceStatus());
			related_schooldevice = existingschoolDevice;
		} else {
			related_schooldevice = schoolDeviceDAO.store(related_schooldevice);
			schoolDeviceDAO.flush();
		}

		/*labroomdevicerepair.setLabRoomDevice(related_schooldevice);
		related_schooldevice.getLabRoomDeviceRepairs().add(labroomdevicerepair);*/
		labroomdevicerepair = labRoomDeviceRepairDAO.store(labroomdevicerepair);
		labRoomDeviceRepairDAO.flush();

		related_schooldevice = schoolDeviceDAO.store(related_schooldevice);
		schoolDeviceDAO.flush();

		return labroomdevicerepair;
	}

	/**
	 * Delete an existing SchoolDevice entity
	 * 
	 */
	@Transactional
	public LabRoomDeviceRepair deleteLabRoomDeviceRepairSchoolDevice(Integer labroomdevicerepair_id, String related_schooldevice_deviceNumber) {
		LabRoomDeviceRepair labroomdevicerepair = labRoomDeviceRepairDAO.findLabRoomDeviceRepairByPrimaryKey(labroomdevicerepair_id, -1, -1);
		SchoolDevice related_schooldevice = schoolDeviceDAO.findSchoolDeviceByPrimaryKey(related_schooldevice_deviceNumber, -1, -1);

		/*labroomdevicerepair.setSchoolDevice(null);
		related_schooldevice.getLabRoomDeviceRepairs().remove(labroomdevicerepair);*/
		labroomdevicerepair = labRoomDeviceRepairDAO.store(labroomdevicerepair);
		labRoomDeviceRepairDAO.flush();

		related_schooldevice = schoolDeviceDAO.store(related_schooldevice);
		schoolDeviceDAO.flush();

		schoolDeviceDAO.remove(related_schooldevice);
		schoolDeviceDAO.flush();

		return labroomdevicerepair;
	}

	/**
	 * Return all LabRoomDeviceRepair entity
	 * 
	 */
	@Transactional
	public List<LabRoomDeviceRepair> findAllLabRoomDeviceRepairs(Integer startResult, Integer maxRows) {
		return new java.util.ArrayList<LabRoomDeviceRepair>(labRoomDeviceRepairDAO.findAllLabRoomDeviceRepairs(startResult, maxRows));
	}

	/**
	 * Return a count of all LabRoomDeviceRepair entity
	 * 
	 */
	@Transactional
	public Integer countLabRoomDeviceRepairs() {
		return ((Long) labRoomDeviceRepairDAO.createQuerySingleResult("select count(o) from LabRoomDeviceRepair o").getSingleResult()).intValue();
	}
	
	
	
	
	
	@Override
	public List<LabRoomDeviceRepair> loadLabRoomDeviceRepair(LabRoomDeviceRepair labRoomDeviceRepair,
			int flag,int currpage, int pageSize){
		List<LabRoomDeviceRepair> labRoomDeviceRepairs = new ArrayList<LabRoomDeviceRepair>();
		String sql = "select c from LabRoomDeviceRepair c where 1=1 ";	
		if(labRoomDeviceRepair.getId() != null && labRoomDeviceRepair.getId()!=0){
			sql+=" and c.id="+labRoomDeviceRepair.getId()+"order by id desc";
		}
		if(flag != 0){
			sql+="and c.id="+flag+"order by id desc";
		}
		labRoomDeviceRepairs=labRoomDeviceRepairDAO.executeQuery(sql, (currpage-1)*pageSize, pageSize);
		return labRoomDeviceRepairs;
	}
	
	@Override
	public List<LabRoomDeviceRepair> findLabRoomDeviceRepairByLabRoomDeviceRepair(String td,int page,int pageSize) {
		// TODO Auto-generated method stub
		String sql="select l from LabRoomDeviceRepair l where 1=1 and l.schoolDevice.deviceNumber='"+td+"'";
	/*	if(labRoomDeviceRepair.getHardwareFailure()!=null&&!labRoomDeviceRepair.getHardwareFailure().equalsIgnoreCase("")){
			sql+=" and l.hardwareFailure like '%"+labRoomDeviceRepair.getHardwareFailure()+"%'";
		}*/
		sql+=" order by l.id desc";
		//给语句添加分页机制；
		List<LabRoomDeviceRepair> labRoomDeviceRepair1=labRoomDeviceRepairDAO.executeQuery(sql,(page-1)*pageSize,pageSize);
		return labRoomDeviceRepair1;
	}
	
	@Override
	public List<LabRoomDeviceRepair> findLabRoomDeviceRepairByLabRoomDeviceRepair(String td) {
		// TODO Auto-generated method stub
		String sql="select l from LabRoomDeviceRepair l where 1=1 and l.schoolDevice.deviceNumber='"+td+"'";
/*		if(labRoomDeviceRepair.getHardwareFailure()!=null&&!labRoomDeviceRepair.getHardwareFailure().equalsIgnoreCase("")){
			sql+=" and l.hardwareFailure like '%"+labRoomDeviceRepair.getHardwareFailure()+"%'";
		}*/
		sql+=" order by l.id desc";
		//给语句添加分页机制；
		List<LabRoomDeviceRepair> labRoomDeviceRepair1=labRoomDeviceRepairDAO.executeQuery(sql);
		return labRoomDeviceRepair1;
	}

	/**
	 * 查找指定学院的设备
	 * @param academyNumber 学院编号
	 * @author 何立友
	 * 2014-09-17
	 */
	@Override
	public List<SchoolDevice> getSchoolDevicesByAcademy(String academyNumber) 
	{
		StringBuffer hql = new StringBuffer("select d from SchoolDevice d where 1=1");
		if(academyNumber != null && !"".equals(academyNumber))
		{
			hql.append(" and d.schoolAcademy.academyNumber = '"+academyNumber+"' ");
		}
		return schoolDeviceDAO.executeQuery(hql.toString(), 0, -1);
	}

	/**
	 * 根据学院查找实验室分室
	 * @param academyNumber 学院编号(academyNumber为null或者空时，返回所有实验室分室)
	 * @author hely
	 * 2014.08.20
	 */
	@Override
	public List<LabRoom> getLabRoomByAcademy(String academyNumber) 
	{
		StringBuffer hql = new StringBuffer("select lr from LabRoom lr where 1=1 ");
		
		if(academyNumber != null && !"".equals(academyNumber))
		{
			hql.append(" and lr.labAnnex.labCenter.schoolAcademy.academyNumber='"+academyNumber+"' ");
		}
		hql.append(" order by lr.labRoomName");
		
		List<LabRoom> labRooms = labRoomDAO.executeQuery(hql.toString(), 0, -1);
		
		return labRooms;
	}
}
