package net.xidlims.service.lab;

import java.util.List;

import net.xidlims.dao.LabConstructionFundingDAO;
import net.xidlims.domain.LabConstructionFunding;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Spring service that handles CRUD requests for LabConstructionFunding entities
 * 
 */

@Service("LabConstructionFundingService")
@Transactional
public class LabConstructionFundingServiceImpl implements
		LabConstructionFundingService {
	
	@Autowired LabConstructionFundingDAO labConstructionFundingDAO;
	
	
	/***************************
	 * 功能：根据查询条件实验项目经费数量
	 * 作者： 贺子龙
	 * 日期：2015-10-05
	 **************************/
	@Override
	public int findAllLabConstructionFundingsByQueryCount(LabConstructionFunding labConstructionFunding){
		StringBuffer hql = new StringBuffer("select  count(i) from LabConstructionFunding i  where 1=1");
		//项目名称条件
		if(labConstructionFunding.getLabConstructionProject()!=null && !"".equals(labConstructionFunding.getLabConstructionProject().getProjectName()))
		{
			hql.append(" and i.labConstructionProject.projectName like '%"+labConstructionFunding.getLabConstructionProject().getProjectName()+"%'");
		}
		//经费编号条件
		if(labConstructionFunding.getFundingNumber()!=null && !"".equals(labConstructionFunding.getFundingNumber()))
		{
			hql.append(" and i.fundingNumber like '%"+labConstructionFunding.getFundingNumber()+"%'");
		}
		
		//项目状态
		if(labConstructionFunding.getAuditResults()!=null)
		{
			hql.append(" and i.auditResults ="+labConstructionFunding.getAuditResults());
		}
		return ((Long) labConstructionFundingDAO.createQuerySingleResult(hql.toString()).getSingleResult()).intValue();
	}
	
	/***************************
	 * 功能：根据查询条件分页实验项目经费记录(分页)
	 * 作者： 贺子龙
	 * 日期：2015-10-05
	 **************************/
	@Override
	public List<LabConstructionFunding> findAllLabConstructionFundingsByQuery(Integer currpage, Integer pageSize,
			LabConstructionFunding labConstructionFunding){
		StringBuffer hql = new StringBuffer("select  i from LabConstructionFunding i  where 1=1");
		//项目名称条件
		if(labConstructionFunding.getLabConstructionProject()!=null && !"".equals(labConstructionFunding.getLabConstructionProject().getProjectName()))
		{
			hql.append(" and i.labConstructionProject.projectName like '%"+labConstructionFunding.getLabConstructionProject().getProjectName()+"%'");
		}
		//经费编号条件
		if(labConstructionFunding.getFundingNumber()!=null && !"".equals(labConstructionFunding.getFundingNumber()))
		{
			hql.append(" and i.fundingNumber like '%"+labConstructionFunding.getFundingNumber()+"%'");
		}
		
		//项目状态
		if(labConstructionFunding.getAuditResults()!=null)
		{
			hql.append(" and i.auditResults ="+labConstructionFunding.getAuditResults());
		}
		hql.append(" order by i.createdAt desc");
		return labConstructionFundingDAO.executeQuery(hql.toString(), (currpage-1)*pageSize, pageSize);
	}
	
	
	/***************************
	 * 功能：根据查询条件分页实验项目经费记录(不分页)
	 * 作者： 贺子龙
	 * 日期：2015-10-05
	 **************************/
	@Override
	public List<LabConstructionFunding> findAllLabConstructionFundingsByQuery(LabConstructionFunding labConstructionFunding){
		StringBuffer hql = new StringBuffer("select  i from LabConstructionFunding i  where 1=1");
		//项目名称条件
		if(labConstructionFunding.getLabConstructionProject()!=null && !"".equals(labConstructionFunding.getLabConstructionProject().getProjectName()))
		{
			hql.append(" and i.labConstructionProject.projectName like '%"+labConstructionFunding.getLabConstructionProject().getProjectName()+"%'");
		}
		//经费编号条件
		if(labConstructionFunding.getFundingNumber()!=null && !"".equals(labConstructionFunding.getFundingNumber()))
		{
			hql.append(" and i.fundingNumber like '%"+labConstructionFunding.getFundingNumber()+"%'");
		}
		
		//项目状态
		if(labConstructionFunding.getAuditResults()!=null)
		{
			hql.append(" and i.auditResults ="+labConstructionFunding.getAuditResults());
		}
		hql.append(" order by i.createdAt desc");
		return labConstructionFundingDAO.executeQuery(hql.toString(), 0, -1);
	}
	
	/********************************
	 * 功能：保存项目经费
	 * 作者：贺子龙
	 * 日期：2015-10-05
	 *********************************/
	@Override
	public LabConstructionFunding saveLabConstructionFunding(LabConstructionFunding labConstructionFunding){
		if (labConstructionFunding.getLabConstructionProject()==null || labConstructionFunding.getLabConstructionProject().getId()==null) {
			labConstructionFunding.setLabConstructionProject(null);
		}
		return labConstructionFundingDAO.store(labConstructionFunding);
	}
	
	/********************************
	 * 功能：根据主键查询实验项目经费记录
	 * 作者：贺子龙
	 * 日期：2015-10-05
	 *********************************/
	@Override
	public LabConstructionFunding findLabConstructionFundingByPrimaryKey(Integer labConstructionFundingId){
		return labConstructionFundingDAO.findLabConstructionFundingByPrimaryKey(labConstructionFundingId);
	}
	
	/********************************
	 * 功能：删除主键查询实验项目经费记录
	 * 作者：贺子龙
	 * 日期：2015-10-05
	 *********************************/
	@Override
	public void deleteLabConstructionFunding(Integer labConstructionFundingId){
		LabConstructionFunding labConstructionFunding=labConstructionFundingDAO.findLabConstructionFundingByPrimaryKey(labConstructionFundingId);
		if (labConstructionFunding!=null) {
			labConstructionFundingDAO.remove(labConstructionFunding);
			labConstructionFundingDAO.flush();
		}
	
	}
	
	
}
