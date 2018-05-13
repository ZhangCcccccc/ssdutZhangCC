package net.xidlims.service.lab;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.xidlims.dao.LabConstructionDeviceDAO;
import net.xidlims.dao.LabConstructionPurchaseDAO;
import net.xidlims.domain.LabConstructionDevice;
import net.xidlims.domain.LabConstructionProject;
import net.xidlims.domain.LabConstructionPurchase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Spring service that handles CRUD requests for LabConstructionPurchase entities
 * 
 */

@Service("LabConstructionPurchaseService")
@Transactional
public class LabConstructionPurchaseServiceImpl implements
		LabConstructionPurchaseService {
	
	@Autowired LabConstructionPurchaseDAO labConstructionPurchaseDAO;
	@Autowired LabConstructionDeviceDAO labConstructionDeviceDAO;
	/********************************************
	 * 功能：保存项目申购的一条记录
	 * 作者：贺子龙
	 * 日期：2015-10-04
	 ********************************************/
	@Override
	public LabConstructionPurchase saveLabConstructionPurchase(LabConstructionPurchase labConstructionPurchase) {
		if(labConstructionPurchase.getUserByKeeper()==null || labConstructionPurchase.getUserByKeeper().getUsername()==null)
		{
			labConstructionPurchase.setUserByKeeper(null);  //保管人
		}
		if(labConstructionPurchase.getUserByApplicant()==null || labConstructionPurchase.getUserByApplicant().getUsername()==null)
		{
			labConstructionPurchase.setUserByApplicant(null);  //申请人
		}
		if(labConstructionPurchase.getSchoolAcademy()==null || labConstructionPurchase.getSchoolAcademy().getAcademyNumber()==null)
		{
			labConstructionPurchase.setSchoolAcademy(null);  //所属院（系、部）
		}
		if(labConstructionPurchase.getCDictionary()==null || labConstructionPurchase.getCDictionary().getId()==null)
		{
			labConstructionPurchase.setCDictionary(null);  //申购类型
		}
		if(labConstructionPurchase.getLabConstructionProject()==null || labConstructionPurchase.getLabConstructionProject().getId()==null)
		{
			labConstructionPurchase.setLabConstructionProject(null);  //所对应的项目立项
		}
		return labConstructionPurchaseDAO.store(labConstructionPurchase);
	}
	
	/***************************
	 * 功能：根据查询条件实验项目建设数量
	 * 作者： 贺子龙
	 * 日期：2015-10-04
	 **************************/
	@Override
	public int findAllLabConstructionPurchasesByQueryCount(LabConstructionPurchase labConstructionPurchase){
		StringBuffer hql = new StringBuffer("select  count(i) from LabConstructionPurchase i  where 1=1");
		//项目名称条件
		if(labConstructionPurchase.getLabConstructionProject()!=null && !"".equals(labConstructionPurchase.getLabConstructionProject().getProjectName()))
		{
			hql.append(" and i.labConstructionProject.projectName like '%"+labConstructionPurchase.getLabConstructionProject().getProjectName()+"%'");
		}
		
		
		//申请人
		if(labConstructionPurchase.getUserByApplicant()!=null && labConstructionPurchase.getUserByApplicant().getUsername()!="")
		{
			hql.append(" and i.userByApplicant.username ='"+labConstructionPurchase.getUserByApplicant().getUsername()+"'");
		}
		//保管人
		if(labConstructionPurchase.getUserByKeeper()!=null && labConstructionPurchase.getUserByKeeper().getUsername()!="")
		{
			hql.append(" and i.userByKeeper.username ='"+labConstructionPurchase.getUserByKeeper().getUsername()+"'");
		}
		
		
		//项目状态
		if(labConstructionPurchase.getAuditResults()!=null)
		{
			hql.append(" and i.auditResults ="+labConstructionPurchase.getAuditResults());
		}
		return ((Long) labConstructionPurchaseDAO.createQuerySingleResult(hql.toString()).getSingleResult()).intValue();
	}
	/***************************
	 * 功能：根据查询条件实验项目建设列表（分页）
	 * 作者： 贺子龙
	 * 日期：2015-10-04
	 **************************/
	@Override
	public List<LabConstructionPurchase> findAllLabConstructionPurchasesByQuery(Integer currpage, Integer pageSize, 
			LabConstructionPurchase labConstructionPurchase){
		StringBuffer hql = new StringBuffer("select  i from LabConstructionPurchase i  where 1=1");
		//项目名称条件
		if(labConstructionPurchase.getLabConstructionProject()!=null && !"".equals(labConstructionPurchase.getLabConstructionProject().getProjectName()))
		{
			hql.append(" and i.labConstructionProject.projectName like '%"+labConstructionPurchase.getLabConstructionProject().getProjectName()+"%'");
		}
		//申请人
		if(labConstructionPurchase.getUserByApplicant()!=null && labConstructionPurchase.getUserByApplicant().getUsername()!="")
		{
			hql.append(" and i.userByApplicant.username ='"+labConstructionPurchase.getUserByApplicant().getUsername()+"'");
		}
		//保管人
		if(labConstructionPurchase.getUserByKeeper()!=null && labConstructionPurchase.getUserByKeeper().getUsername()!="")
		{
			hql.append(" and i.userByKeeper.username ='"+labConstructionPurchase.getUserByKeeper().getUsername()+"'");
		}
		//项目状态
		if(labConstructionPurchase.getAuditResults()!=null)
		{
			hql.append(" and i.auditResults ="+labConstructionPurchase.getAuditResults());
		}
		hql.append(" order by i.purchaseTime desc");
		return labConstructionPurchaseDAO.executeQuery(hql.toString(), (currpage-1)*pageSize, pageSize);
	}
	
	
	/***************************
	 * 功能：根据查询条件实验项目建设列表（不分页）
	 * 作者： 贺子龙
	 * 日期：2015-10-04
	 **************************/
	@Override
	public List<LabConstructionPurchase> findAllLabConstructionPurchasesByQuery(LabConstructionPurchase labConstructionPurchase){
		StringBuffer hql = new StringBuffer("select  i from LabConstructionPurchase i  where 1=1");
		//项目名称条件
		if(labConstructionPurchase.getLabConstructionProject()!=null && !"".equals(labConstructionPurchase.getLabConstructionProject().getProjectName()))
		{
			hql.append(" and i.labConstructionProject.projectName like '%"+labConstructionPurchase.getLabConstructionProject().getProjectName()+"%'");
		}
		//申请人
		if(labConstructionPurchase.getUserByApplicant()!=null && labConstructionPurchase.getUserByApplicant().getUsername()!="")
		{
			hql.append(" and i.userByApplicant.username ='"+labConstructionPurchase.getUserByApplicant().getUsername()+"'");
		}
		//保管人
		if(labConstructionPurchase.getUserByKeeper()!=null && labConstructionPurchase.getUserByKeeper().getUsername()!="")
		{
			hql.append(" and i.userByKeeper.username ='"+labConstructionPurchase.getUserByKeeper().getUsername()+"'");
		}
		//项目状态
		if(labConstructionPurchase.getAuditResults()!=null)
		{
			hql.append(" and i.auditResults ="+labConstructionPurchase.getAuditResults());
		}
		hql.append(" order by i.purchaseTime desc");
		return labConstructionPurchaseDAO.executeQuery(hql.toString(), 0, -1);
	}
	
	/********************************************
	 * 功能：找出LabConstructionProject表中的申请人字段
	 * 作者：贺子龙
	 * 日期：2015-10-04
	 ********************************************/
	@Override
	public Map<String, String> findAllProjectApplicants(){
		Map<String, String> map=new HashMap<String, String>();
		
		String s="select u from LabConstructionPurchase u";
		List<LabConstructionPurchase>   list=labConstructionPurchaseDAO.executeQuery(s);
		if(list.size()>0){
			for(LabConstructionPurchase lc:list){
				if (lc.getUserByApplicant()!=null ) {
					
					map.put(lc.getUserByApplicant().getUsername(), lc.getUserByApplicant().getCname());
				}
			}
		}
		return map;
	}
	/********************************************
	 * 功能：找出LabConstructionProject表中的保管人字段
	 * 作者：贺子龙
	 * 日期：2015-10-04
	 ********************************************/
	@Override
	public Map<String, String> findAllProjectKeepers(){
		Map<String, String> map=new HashMap<String, String>();
		
		String s="select u from LabConstructionPurchase u";
		List<LabConstructionPurchase>   list=labConstructionPurchaseDAO.executeQuery(s);
		if(list.size()>0){
			for(LabConstructionPurchase lc:list){
				if (lc.getUserByKeeper()!=null ) {
					
					map.put(lc.getUserByKeeper().getUsername(), lc.getUserByKeeper().getCname());
				}
			}
		}
		return map;
	}
	/*********************************
	 * 根据主键获取实验项目建设所申购物品记录
	 * 作者：贺子龙
	 * 日期：2015-10-04
	 *********************************/
	@Override
	public LabConstructionDevice findDeviceByPrimaryKey(Integer id){
		return labConstructionDeviceDAO.findLabConstructionDeviceByPrimaryKey(id);
	}
	/*********************************
	 * 保存实验项目建设所申购物品记录
	 * 作者：贺子龙
	 * 日期：2015-10-04
	 *********************************/
	@Override
	public LabConstructionDevice saveDevice(LabConstructionDevice labConstructionDevice){
		return labConstructionDeviceDAO.store(labConstructionDevice);
	}
	/*********************************
	 * 删除实验项目建设所申购物品记录
	 * 作者：贺子龙
	 * 日期：2015-10-04
	 *********************************/
	public void deleteDevice(int id){
		LabConstructionDevice labConstructionDevice=labConstructionDeviceDAO.findLabConstructionDeviceByPrimaryKey(id);
		if (labConstructionDevice!=null) {
			labConstructionDeviceDAO.remove(labConstructionDevice);
			labConstructionDeviceDAO.flush();
		}
	}
	
	/********************************************
	 * 功能：根据主键获取实验项目建设记录
	 * 作者：贺子龙
	 * 日期：2015-10-04
	 ********************************************/
	@Override
	public LabConstructionPurchase findLabConstructionPurchaseByPrimaryKey(Integer labConstructionPurchaseId){
		return labConstructionPurchaseDAO.findLabConstructionPurchaseByPrimaryKey(labConstructionPurchaseId);
	}
	/********************************************
	 * 功能：根据project和标志位获取实验项目建设所申购物品记录
	 * 作者：贺子龙
	 * 日期：2015-10-04
	 ********************************************/
	@Override
	public List<LabConstructionDevice> findlabConstructionDevicesByProjectAndTag(LabConstructionProject labConstructionProject,int tag){
		String s="select d from LabConstructionDevice d where d.labConstructionProject.id="+labConstructionProject.getId();
		s+="and d.tag="+tag;
		return labConstructionDeviceDAO.executeQuery(s);
	}
	
	/********************************************
	 * 功能：删除项目申购的一条记录
	 * 作者：贺子龙
	 * 日期：2015-10-04
	 ********************************************/
	@Override
	public void deleteLabConstructionPurchase(int labConstructionPurchaseId){
		LabConstructionPurchase labConstructionPurchase=labConstructionPurchaseDAO.findLabConstructionPurchaseByPrimaryKey(labConstructionPurchaseId);
		if (labConstructionPurchase!=null) {
			labConstructionPurchaseDAO.remove(labConstructionPurchase);
			labConstructionPurchaseDAO.flush();
		}
	}
}
