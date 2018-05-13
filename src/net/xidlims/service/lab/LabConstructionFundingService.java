package net.xidlims.service.lab;

import java.util.List;

import net.xidlims.domain.LabConstructionFunding;


/**
 * Spring service that handles CRUD requests for LabConstructionFunding entities
 * 
 */
public interface LabConstructionFundingService {
	
	/***************************
	 * 功能：根据查询条件实验项目经费数量
	 * 作者： 贺子龙
	 * 日期：2015-10-05
	 **************************/
	
	public int findAllLabConstructionFundingsByQueryCount(LabConstructionFunding labConstructionFunding);
	/***************************
	 * 功能：根据查询条件分页实验项目经费记录(分页)
	 * 作者： 贺子龙
	 * 日期：2015-10-05
	 **************************/
	public List<LabConstructionFunding> findAllLabConstructionFundingsByQuery(Integer currpage, Integer pageSize,LabConstructionFunding labConstructionFunding);

	/***************************
	 * 功能：根据查询条件分页实验项目经费记录(不分页)
	 * 作者： 贺子龙
	 * 日期：2015-10-05
	 **************************/
	public List<LabConstructionFunding> findAllLabConstructionFundingsByQuery(LabConstructionFunding labConstructionFunding);

	
	/********************************
	 * 功能：保存项目经费
	 * 作者：贺子龙
	 * 日期：2015-10-05
	 *********************************/
	public LabConstructionFunding saveLabConstructionFunding(LabConstructionFunding labConstructionFunding);
	/********************************
	 * 功能：根据主键查询实验项目经费记录
	 * 作者：贺子龙
	 * 日期：2015-10-05
	 *********************************/
	public LabConstructionFunding findLabConstructionFundingByPrimaryKey(Integer labConstructionFundingId); 
	/********************************
	 * 功能：删除主键查询实验项目经费记录
	 * 作者：贺子龙
	 * 日期：2015-10-05
	 *********************************/
	public void deleteLabConstructionFunding(Integer labConstructionFundingId); 
	
}