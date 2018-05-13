package net.xidlims.service.lab;

import java.util.List;

import net.xidlims.domain.LabCenter;

public interface LabCenterService {

	/**
	 * 根据实验中心主键查找实验中心对象
	 * @author hly
	 * 2015.07.24
	 */
	public LabCenter findLabCenterByPrimaryKey(Integer labCenterId);
	
	/**
	 * 获取所有的实验中心
	 * @author hly
	 * 2015.07.24
	 */
	public List<LabCenter> findAllLabCenterByQuery(LabCenter labCenter, Integer currpage, Integer pageSize);
	
	/**
	 * 保存实验中心数据
	 * @author hly
	 * 2015.07.27
	 */
	public LabCenter saveLabCenter(LabCenter labCenter);
	
	/**
	 * 删除实验中心
	 * @author hly
	 * 2015.07.27
	 */
	public boolean deleteLabCenter(Integer labCenterId);

	/****************************************************************************
	 * 功能：根据实验中心信息查询实验中心
	 * 作者：李小龙
	 ****************************************************************************/
	public List<LabCenter> findAllLabCenterByLabCenter(LabCenter labCenter);

	/****************************************************************************
	 * 功能：根据实验中心信息查询实验中心并分页
	 * 作者：李小龙
	 ****************************************************************************/
	public List<LabCenter> findAllLabCenterByLabCenter(LabCenter labCenter,
			int page, int pageSize);
	
	/****************************************************************************
	 * 功能：根据学院查找中心
	 * 作者：贺子龙
	 ****************************************************************************/
	public List<LabCenter> findLabCenterByAcademy(String academyNumber);
	
}
