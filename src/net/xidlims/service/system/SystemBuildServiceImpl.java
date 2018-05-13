package net.xidlims.service.system;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import net.xidlims.dao.SchoolTermDAO;
import net.xidlims.dao.SchoolWeekDAO;
import net.xidlims.dao.SystemBuildDAO;
import net.xidlims.dao.SystemRoomDAO;
import net.xidlims.domain.LabRoomDevice;
import net.xidlims.domain.SchoolTerm;
import net.xidlims.domain.SchoolWeek;
import net.xidlims.domain.SystemBuild;
import net.xidlims.domain.SystemRoom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("SystemBuildService")
public class SystemBuildServiceImpl implements SystemBuildService {
	
	@Autowired SystemBuildDAO systemBuildDAO;
	@Autowired SystemRoomDAO systemRoomDAO;
	/*************************************************************************************
	 * @內容：根据校区id查询楼栋
	 * @作者：李小龙
	 *************************************************************************************/
	@Override
	public List<SystemBuild> findBuildByCampusId(String campusNumber) {
		// TODO Auto-generated method stub
		String sql="select b from SystemBuild b where b.systemCampus.campusNumber='"+campusNumber+"'";
		return systemBuildDAO.executeQuery(sql,0,-1);
	}
	/*************************************************************************************
	 * @內容：根据楼栋编号查询房间
	 * @作者：李小龙
	 *************************************************************************************/
	@Override
	public List<SystemRoom> findRoomByBuildNumber(String buildNumber) {
		// TODO Auto-generated method stub
		String sql="select m from SystemRoom m where m.systemBuild.buildNumber='"+buildNumber+"'";
		return systemRoomDAO.executeQuery(sql, 0,-1);
	}
	/*************************************************************************************
	 * @內容：根据学员编号查询楼栋
	 * @作者：李小龙
	 *************************************************************************************/
	@Override
	public List<SystemBuild> finSystemBuildByAcademy(String academyNumber) {
		if(academyNumber!=null&&!academyNumber.equals("")){
			String sql="select s from SystemBuild s where s.schoolAcademy.academyNumber="+academyNumber;
			return systemBuildDAO.executeQuery(sql, 0,-1);
		}else{
			return null;
		}
	}
	
	/*************************************************************************************
	 * @內容：查询所有楼栋
	 * @作者：裴继超
	 * @时间：2016年3月22日14:36:37
	 *************************************************************************************/
	@Override
	public List<SystemBuild> finAllSystemBuilds(){
		String sql = "select s from SystemBuild s where 1=1";
		return systemBuildDAO.executeQuery(sql, 0,-1);
	}
	
	
	/***************************************************************************************
	 * 保存楼栋
	 * 裴继超
	 * 2016年3月22日
	 *************************************************************************************/
	@Override
	public void saveSystemBuild(SystemBuild systemBuild){
		systemBuildDAO.store(systemBuild);
		systemBuildDAO.flush();
	}
	
	
	/*************************************************************************************
	 * @內容：根据id查询楼栋
	 * @作者：裴继超
	 * @时间：2016年3月22日14:36:37
	 *************************************************************************************/
	@Override
	public SystemBuild finSystemBuildById(String buildNumber){
		SystemBuild systemBuild = systemBuildDAO.findSystemBuildByBuildNumber(buildNumber);
		return systemBuild;
	}
	
	/**
	 * 根据是否存在坐标查楼宇
	 * 裴继超
	 * 2016年4月1日
	 */
	@Override
	public List<SystemBuild> findBuildingByXY(){
		String sql = "select b from SystemBuild b where  b.xCoordinate != null and b.yCoordinate != null ";
		List<SystemBuild> buildings = systemBuildDAO.executeQuery(sql.toString(), 0, -1);
		return buildings;
	}

	/**
	 * 根据id获取楼宇
	 * 裴继超
	 * 2016年4月5日
	 */
	@Override
	public SystemBuild findBuildingbyBuildNumber(String buildNumber){
		SystemBuild building = systemBuildDAO.findSystemBuildByPrimaryKey(buildNumber);
		return building;
	}
}