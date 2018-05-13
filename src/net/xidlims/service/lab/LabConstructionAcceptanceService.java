package net.xidlims.service.lab;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.xidlims.domain.LabConstructionAcceptance;
import net.xidlims.domain.LabConstructionFunding;


/**
 * Spring service that handles CRUD requests for LabConstructionAcceptance entities
 * 
 */
public interface LabConstructionAcceptanceService {

	/***********************************
	 * 功能：满足查询条件的所有实验室建设项目验收数量
	 * 作者：贺子龙
	 * 日期：2015-10-14
	 ***********************************/
	public int findAllLabConstructionAcceptancesByQueryCount(LabConstructionAcceptance labConstructionAcceptance);
	/***************************
	 * 功能：根据查询条件分页实验项目验收记录
	 * 作者： 贺子龙
	 * 日期：2015-10-14
	 **************************/
	public List<LabConstructionAcceptance> findAllLabConstructionAcceptancesByQuery(Integer currpage, Integer pageSize,
			LabConstructionAcceptance labConstructionAcceptance);
	
	/***************************
	 * 功能：根据主键查询项目验收
	 * 作者： 贺子龙
	 * 日期：2015-10-14
	 **************************/
	public LabConstructionAcceptance findLabConstructionAcceptanceByPrimaryKey(Integer labConstructionAcceptanceId);
	/********************************
	 * 功能：保存项目验收
	 * 作者：贺子龙
	 * 日期：2015-10-14
	 *********************************/
	public LabConstructionAcceptance saveLabConstructionAcceptance(LabConstructionAcceptance labConstructionAcceptance);
	/********************************
	 * 功能：项目验收上传附件
	 * 作者：贺子龙
	 * 日期：2015-10-14
	 *********************************/
	public void acceptanceDocumentUpload(HttpServletRequest request,HttpServletResponse response, Integer id,int type,int flaggy);
	/****************************************************************************
	 * 功能：下载项目立项附件
	 * 作者：贺子龙
	 * 时间：2015-10-14
	 ****************************************************************************/
	public void downloadAcceptanceDocument(HttpServletRequest request,
			HttpServletResponse response, Integer id);
	/********************************
	 * 功能：删除项目验收
	 * 作者：贺子龙
	 * 日期：2015-10-14
	 *********************************/
	public void deleteLabConstructionAcceptance(Integer labConstructionAcceptanceId);
	
}