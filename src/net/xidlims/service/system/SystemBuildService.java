package net.xidlims.service.system;

import java.util.List;
import java.util.Map;
import java.util.Set;

import net.xidlims.domain.SchoolTerm;
import net.xidlims.domain.SystemBuild;
import net.xidlims.domain.SystemRoom;

public interface SystemBuildService {
	/*************************************************************************************
	 * @內容：根据校区id查询楼栋
	 * @作者：李小龙
	 *************************************************************************************/
	public List<SystemBuild> findBuildByCampusId(String campusNumber);
	/*************************************************************************************
	 * @內容：根据楼栋编号查询房间
	 * @作者：李小龙
	 *************************************************************************************/
	public List<SystemRoom> findRoomByBuildNumber(String buildNumber);
	/*************************************************************************************
	 * @內容：根据学员编号查询楼栋
	 * @作者：李小龙
	 *************************************************************************************/
	public List<SystemBuild> finSystemBuildByAcademy(String academyNumber);
	
	/*************************************************************************************
	 * @內容：查询所有楼栋
	 * @作者：裴继超
	 * @时间：2016年3月22日14:36:37
	 *************************************************************************************/
	public List<SystemBuild> finAllSystemBuilds();
	
	/*************************************************************************************
	 * 保存楼栋
	 * 裴继超
	 * 2016年3月22日
	 **************************************************************************************/
	public void saveSystemBuild(SystemBuild systemBuild);
	
	
	/*************************************************************************************
	 * @內容：根据id查询楼栋
	 * @作者：裴继超
	 * @时间：2016年3月22日14:36:37
	 *************************************************************************************/
	public SystemBuild finSystemBuildById(String buildNumber);
	
	/**
	 * 根据是否存在坐标查楼宇
	 * 裴继超
	 * 2016年4月1日
	 */
	public List<SystemBuild> findBuildingByXY();

	/**
	 * 根据id获取楼宇
	 * 裴继超
	 * 2016年4月5日
	 */
	public SystemBuild findBuildingbyBuildNumber(String buildNumber);
	
	

}