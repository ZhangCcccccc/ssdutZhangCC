package net.xidlims.service.system;

import java.util.Calendar;
import java.util.List;
import java.util.Set;

import net.xidlims.dao.SystemCampusDAO;
import net.xidlims.dao.SystemTimeDAO;
import net.xidlims.domain.SystemCampus;
import net.xidlims.domain.SystemTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("TimeDetailService")
public class TimeDetailServiceImpl implements TimeDetailService {
	@Autowired
	private SystemTimeDAO systemTimeDAO;
	@Autowired
	private SystemCampusDAO systemCampusDAO;
	
	/*************************************************************************************
	 * @內容：获取节次的总记录数
	 * @作者： 叶明盾
	 * @日期：2014-07-28
	 *************************************************************************************/
	@Transactional
	public int getTimeTotalRecords(){
		Set<SystemTime> times = systemTimeDAO.findAllSystemTimes();
		return times.size();
	}
	
	/*************************************************************************************
	 * @內容：查找所有的节次信息
	 * @作者： 叶明盾
	 * @日期：2014-07-28
	 *************************************************************************************/
	public Set<SystemTime> findAllTimes(int curr, int size){
		return systemTimeDAO.findAllSystemTimes(curr*size, size);
	}
	
	/*************************************************************************************
	 * @內容：保存节次
	 * @作者： 叶明盾
	 * @日期：2014-07-29
	 *************************************************************************************/
	@Transactional
	public SystemTime saveTime(SystemTime systemTime){
		//判断节次创建时间是否为空，如果为空则填写当前时间
		if(systemTime.getCreatedDate() == null ){
			systemTime.setCreatedDate(Calendar.getInstance());
		}
		//判断节次更新时间是否为空，如果为空则填写当前时间
		if(systemTime.getUpdatedDate() == null ){
			systemTime.setUpdatedDate(Calendar.getInstance());
		}
		//保存节次
		return systemTimeDAO.store(systemTime);		
	}
	
	/*************************************************************************************
	 * @內容：根据节次的名称查找节次
	 * @作者： 叶明盾
	 * @日期：2014-07-29
	 *************************************************************************************/
	public Set<SystemTime> findTimesByTimeName(String timeName)
	{
		return systemTimeDAO.findSystemTimeByTimeName(timeName);
	}
	
	/*************************************************************************************
	 * @內容：根据节次对应的ID查找节次
	 * @作者： 叶明盾
	 * @日期：2014-07-31
	 *************************************************************************************/
	public SystemTime findTimeById(Integer idKey){
		return systemTimeDAO.findSystemTimeById(idKey);
	}

	/*************************************************************************************
	 * @內容：根据校区查询节次总记录数
	 * @作者： 李小龙
	 * @日期：2014-12-31
	 *************************************************************************************/
	@Override
	public int getTimeTotalRecords(String campusNumber) {
		String sql="select count(t) from SystemTime t where t.systemCampus.campusNumber='"+campusNumber+"'";
		return ((Long) systemTimeDAO.createQuerySingleResult(sql).getSingleResult()).intValue();
	}

	/*************************************************************************************
	 * @內容：根据校区编号查找所有的节次信息
	 * @作者： 李小龙
	 * @日期：2014-12-31
	 *************************************************************************************/
	@Override
	public List<SystemTime> findAllTimes(int page, int size, String campusNumber) {
		String sql="select t from SystemTime t where t.systemCampus.campusNumber='"+campusNumber+"'";
		sql+=" order by t.startDate";
		return systemTimeDAO.executeQuery(sql, (page-1)*size,size);
	}

	/*************************************************************************************
	 * @內容：查询出学校的两个校区（松江和延安路校区）
	 * @作者： 李小龙
	 * @日期：2014-12-31
	 *************************************************************************************/
	@Override
	public List<SystemCampus> findAllCampus() {
		String sql="select s from SystemCampus s where s.campusNumber<3";
		return systemCampusDAO.executeQuery(sql, 0,-1);
	}
	/*************************************************************************************
	 * @內容：根据节次名称和校区编号查询节次
	 * @作者： 李小龙
	 * @日期：2015-1-4
	 *************************************************************************************/
	@Override
	public List<SystemTime> findTimesByTimeNameAndCampusNumber(String timeName,
			String campusNumber) {
		String sql="select s from SystemTime s where s.systemCampus.campusNumber='"+campusNumber+"' and s.sectionName='"+timeName+"'";
		
		return systemTimeDAO.executeQuery(sql, 0,-1);
	}

	/*************************************************************************************
	 * @內容：根据校区编号查询校区
	 * @作者： 李小龙
	 * @日期：2015-1-4
	 *************************************************************************************/
	@Override
	public SystemCampus findSystemCampusByPromaryKey(String campusNumber) {
		
		return systemCampusDAO.findSystemCampusByPrimaryKey(campusNumber);
	}


}