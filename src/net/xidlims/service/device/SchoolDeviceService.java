package net.xidlims.service.device;

import java.util.List;
import java.util.Set;

import net.xidlims.domain.SchoolDeviceUse;
import net.xidlims.domain.SchoolDevice;

public interface SchoolDeviceService {
	
	/****************************************************************************
	 * 功能：根据学院编号和schoolDevice对象查询设备数量
	 * 作者：贺子龙
	 * 时间：2015-09-08
	 ****************************************************************************/
	public int findSchoolDeviceByAcademyNumberAndSchoolDevice(
			String academyNumber, SchoolDevice schoolDevice);

	/****************************************************************************
	 * 功能：根据学院编号和schoolDevice对象查询设备并分页
	 * 作者：贺子龙
	 * 时间：2015-09-08
	 ****************************************************************************/
	public List<SchoolDevice> findSchoolDeviceByAcademyNumberAndSchoolDevice(
			String academyNumber, SchoolDevice schoolDevice, Integer page,
			int pageSize);
	/****************************************************************************
	 * 功能：根据设备编号查询设备
	 * 作者：贺子龙
	 * 时间：2015-09-08
	 ****************************************************************************/
	public SchoolDevice findSchoolDeviceByPrimaryKey(String deviceNumber);
	
	/****************************************************************************
	 * 功能：查询出所有的设备
	 * 作者：李小龙
	 * 时间：2014-07-30
	 ****************************************************************************/
	public Set<SchoolDevice> findAllSchoolDevice();
	/****************************************************************************
	 * 功能：根据学院编号查询设备
	 * 作者：李小龙
	 ****************************************************************************/
	public List<SchoolDevice> findSchoolDeviceByAcademyNumber(
			String academyNumber);
	/****************************************************************************
	 * 功能：根据学院编号和schoolDevice对象查询设备数量
	 * 作者：李小龙
	 ****************************************************************************/
	public int findSchoolDeviceByAcademyNumberAndSchoolDevice(
			String academyNumber, SchoolDevice schoolDevice,String maxDeviceNumber);
	
	/****************************************************************************
	 * 功能：根据学院编号和schoolDevice对象查询已添加设备数量
	 * 作者：贺子龙
	 * 时间：2015-09-22 20:57:44
	 ****************************************************************************/
	public int findSchoolDeviceByAcademyNumberAndSchoolDeviceNew(
			String academyNumber, SchoolDevice schoolDevice,String maxDeviceNumber);
	
	/****************************************************************************
	 * 功能：根据学院编号和schoolDevice对象查询设备并分页
	 * 作者：李小龙
	 ****************************************************************************/
	public List<SchoolDevice> findSchoolDeviceByAcademyNumberAndSchoolDevice(
			String academyNumber, SchoolDevice schoolDevice,String maxDeviceNumber, Integer page,
			int pageSize);
	/****************************************************************************
	 * 功能：根据学院编号和schoolDevice对象查询已添加设备
	 * 作者：贺子龙
	 * 时间：2015-09-22 20:52:08
	 ****************************************************************************/
	public List<SchoolDevice> findSchoolDeviceByAcademyNumberAndSchoolDeviceNew(
			String academyNumber, SchoolDevice schoolDevice,String maxDeviceNumber, Integer page,
			int pageSize);
	
	/****************************************************************************
	 * 功能：找出当前设备的关联设备
	 * 作者：贺子龙
	 ****************************************************************************/
	public List<SchoolDevice> findInnerSameDevice(String deviceNumber);
	
	/****************************************************************************
	 * 功能：根据设备名称和编号查询学校设备并分页
	 * 作者：贺子龙
	 ****************************************************************************/
	public List<SchoolDevice> findSchoolDeviceByNameAndNumber(Integer cid, int labRoomId, String deviceName, String deviceNumber, int page, int pageSize);
	
	/****************************************************************************
	 * 功能：根据设备名称和编号查询学校设备数量
	 * 作者：贺子龙
	 ****************************************************************************/
	public int countSchoolDeviceByNameAndNumber(Integer cid, int labRoomId, String deviceName, String deviceNumber);
	
	/****************************************************************************
	 * 功能：查询设备套数
	 * 作者：贺子龙
	 ****************************************************************************/
	public int maxSchoolDeviceSet();
	
	/****************************************************************************
	 * 功能：保存设备
	 * 作者：贺子龙
	 ****************************************************************************/
	public void saveSchoolDevice(SchoolDevice device);
	
	/****************************************************************************
	 * 功能：根据关联设备编号查询的设备组合
	 * 作者：贺子龙
	 ****************************************************************************/
	public List<SchoolDevice> findSchoolDeviceSet(int innerSame);
	
	/****************************************************************************
	 * 设备管理--设备使用情况报表--根据设备编号和学期唯一确定一条设备使用情况记录
	 * @author 贺子龙
	 * 2016-07-21
	 ****************************************************************************/
	public SchoolDeviceUse findSchoolDeviceUseByNumberAndTerm(String deviceNumber, int term);
}
