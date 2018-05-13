package net.xidlims.service.lab;

import java.util.List;
import java.util.Map;

import net.xidlims.domain.LabConstructionDevice;
import net.xidlims.domain.LabConstructionProject;
import net.xidlims.domain.LabConstructionPurchase;


/**
 * Spring service that handles CRUD requests for LabConstructionPurchase entities
 * 
 */
public interface LabConstructionPurchaseService {

	
	/********************************************
	 * 功能：保存项目申购的一条记录
	 * 作者：贺子龙
	 * 日期：2015-10-04
	 ********************************************/
	public LabConstructionPurchase saveLabConstructionPurchase(LabConstructionPurchase labConstructionPurchase);
	/********************************************
	 * 功能：删除项目申购的一条记录
	 * 作者：贺子龙
	 * 日期：2015-10-04
	 ********************************************/
	public void deleteLabConstructionPurchase(int labConstructionPurchaseId);
	
	/***************************
	 * 功能：根据查询条件实验项目建设数量
	 * 作者： 贺子龙
	 * 日期：2015-10-04
	 **************************/
	public int findAllLabConstructionPurchasesByQueryCount(LabConstructionPurchase labConstructionPurchase);
	/***************************
	 * 功能：根据查询条件实验项目建设列表(分页)
	 * 作者： 贺子龙
	 * 日期：2015-10-04
	 **************************/
	public List<LabConstructionPurchase> findAllLabConstructionPurchasesByQuery(Integer currpage, Integer pageSize, 
			LabConstructionPurchase labConstructionPurchase);
	
	/***************************
	 * 功能：根据查询条件实验项目建设列表(不分页)
	 * 作者： 贺子龙
	 * 日期：2015-10-04
	 **************************/
	public List<LabConstructionPurchase> findAllLabConstructionPurchasesByQuery(LabConstructionPurchase labConstructionPurchase);
	
	/********************************************
	 * 功能：找出LabConstructionProject表中的申请人字段
	 * 作者：贺子龙
	 * 日期：2015-10-04
	 ********************************************/
	public Map<String, String> findAllProjectApplicants();
	/********************************************
	 * 功能：找出LabConstructionProject表中的保管人字段
	 * 作者：贺子龙
	 * 日期：2015-10-04
	 ********************************************/
	public Map<String, String> findAllProjectKeepers();
	/*********************************
	 * 根据主键获取实验项目建设所申购物品记录
	 * 作者：贺子龙
	 * 日期：2015-10-04
	 *********************************/
	public LabConstructionDevice findDeviceByPrimaryKey(Integer id);
	/*********************************
	 * 保存实验项目建设所申购物品记录
	 * 作者：贺子龙
	 * 日期：2015-10-04
	 *********************************/
	public LabConstructionDevice saveDevice(LabConstructionDevice labConstructionDevice);
	/*********************************
	 * 删除实验项目建设所申购物品记录
	 * 作者：贺子龙
	 * 日期：2015-10-04
	 *********************************/
	public void deleteDevice(int id);
	/********************************************
	 * 功能：根据主键获取实验项目建设记录
	 * 作者：贺子龙
	 * 日期：2015-10-04
	 ********************************************/
	public LabConstructionPurchase findLabConstructionPurchaseByPrimaryKey(Integer labConstructionPurchaseId);
	/********************************************
	 * 功能：根据project和标志位获取实验项目建设所申购物品记录
	 * 作者：贺子龙
	 * 日期：2015-10-04
	 ********************************************/
	public List<LabConstructionDevice> findlabConstructionDevicesByProjectAndTag(LabConstructionProject labConstructionProject,int tag);
	
	
}