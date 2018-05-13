package net.xidlims.service.lab;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.xidlims.domain.LabConstructionAcceptance;
import net.xidlims.domain.LabConstructionDevice;
import net.xidlims.domain.LabConstructionFunding;
import net.xidlims.domain.LabConstructionProject;
import net.xidlims.domain.LabConstructionProjectAudit;
import net.xidlims.domain.LabConstructionPurchase;
import net.xidlims.domain.OperationItem;
import net.xidlims.domain.SchoolAcademy;
import net.xidlims.domain.User;

/**
 * Spring service that handles CRUD requests for LabConstructionProject entities
 * 
 */
public interface LabConstructionProjectService {


	/***************************
	 * 功能：根据查询条件实验项目列表（分页）
	 * 作者： 贺子龙
	 * 日期：2015-10-01
	 **************************/
	public List<LabConstructionProject> findAllLabConstructionProjectsByQuery(Integer currpage, Integer pageSize, LabConstructionProject labConstructionProject);
	/***************************
	 * 功能：根据查询条件实验项目列表（不分页）
	 * 作者： 贺子龙
	 * 日期：2015-10-01
	 **************************/
	public List<LabConstructionProject> findAllLabConstructionProjectsByQuery(LabConstructionProject labConstructionProject);
	
	/***************************
	 * 功能：根据查询条件实验项目记录数量
	 * 作者： 贺子龙
	 * 日期：2015-10-01
	 **************************/
	public int findAllLabConstructionProjectsByQueryCount(LabConstructionProject labConstructionProject);
	
	/********************************************
	 * 功能：找出LabConstructionProject表中的项目负责人字段
	 * 作者：贺子龙
	 * 日期：2015-10-01
	 ********************************************/
	public Map<String, String> findAllProjectManagers();
	
	/********************************************
	 * 功能：保存项目立项的一条记录
	 * 作者：贺子龙
	 * 日期：2015-10-01
	 ********************************************/
	public LabConstructionProject saveLabConstructionProject(LabConstructionProject labConstructionProject);

	/********************************************
	 * 功能：通过姓名和工号查询用户
	 * 作者：贺子龙
	 * 日期：2015-10-03
	 ********************************************/
	public List<User> findUserByCnameAndUsername(User user,Integer labConstructionProjectId,Integer page,int pageSize);
	/********************************************
	 * 功能：通过姓名和工号查询用户的数量
	 * 作者：贺子龙
	 * 日期：2015-10-03
	 ********************************************/
	public int findUserByCnameAndUsernameCount(User user,Integer labConstructionProjectId);
	
	/**
	 * 根据主键获取实验室对象
	 * @author hly
	 * 2015.07.28
	 */
	/********************************************
	 * 功能：根据主键获取项目立项对象
	 * 作者：贺子龙
	 * 日期：2015-10-03
	 ********************************************/
	public LabConstructionProject findLabConstructionProjectByPrimaryKey(Integer labConstructionProjectId);
	/****************************************************************************
	 * 功能：删除项目立项
	 * 作者：贺子龙
	 * 时间：2015-10-03
	 ****************************************************************************/
	public void deleteLabConstructionProject(Integer labConstructionProjectId);
	
	/****************************************************************************
	 * 功能：项目立项上传附件
	 * 作者：贺子龙
	 * 时间：2015-10-14
	 ****************************************************************************/
	public void projectDocumentUpload(HttpServletRequest request,HttpServletResponse response, Integer id,int type);
	/***********************************************************************************************
	 * 根据文件id下载文件
	 * 作者：贺子龙
	 ***********************************************************************************************/
	public void downloadDocument(HttpServletRequest request,HttpServletResponse response, Integer id); 
	
}