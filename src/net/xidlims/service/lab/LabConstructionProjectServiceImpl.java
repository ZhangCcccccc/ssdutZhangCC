package net.xidlims.service.lab;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.xidlims.dao.CommonDocumentDAO;
import net.xidlims.dao.LabConstructionProjectDAO;
import net.xidlims.dao.UserDAO;
import net.xidlims.domain.CommonDocument;
import net.xidlims.domain.LabConstructionProject;
import net.xidlims.domain.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * Spring service that handles CRUD requests for LabConstructionProject entities
 * 
 */

@Service("LabConstructionProjectService")
@Transactional
public class LabConstructionProjectServiceImpl implements
		LabConstructionProjectService {
	@Autowired LabConstructionProjectDAO labConstructionProjectDAO;
	@Autowired UserDAO userDAO;
	@Autowired CommonDocumentDAO documentDAO;
	
	
	/***************************
	 * 功能：根据查询条件实验项目列表（分页）
	 * 作者： 贺子龙
	 * 日期：2015-10-01
	 **************************/
	@Override
	public List<LabConstructionProject> findAllLabConstructionProjectsByQuery(Integer currpage, Integer pageSize, 
			LabConstructionProject labConstructionProject){//根据项目名称、项目编号、项目负责人、项目状态四个条件查询项目立项
		StringBuffer hql = new StringBuffer("select i from LabConstructionProject i  where 1=1");
		//项目名称条件
		if(labConstructionProject.getProjectName()!=null && !"".equals(labConstructionProject.getProjectName()))
		{
			hql.append(" and i.projectName like '%"+labConstructionProject.getProjectName()+"%'");
		}
		
		//项目编号条件
		if(labConstructionProject.getProjectNumber()!=null && !"".equals(labConstructionProject.getProjectNumber()))
		{
			hql.append(" and i.projectNumber like '%"+labConstructionProject.getProjectNumber()+"%'");
		}
		//项目状态
		if(labConstructionProject.getAuditResults()!=null && labConstructionProject.getAuditResults()!=0)
		{
			hql.append(" and i.auditResults ="+labConstructionProject.getAuditResults());
		}
		//项目负责人条件
		if(labConstructionProject.getUser()!=null && labConstructionProject.getUser().getUsername()!="")
		{
			hql.append(" and i.user.username ='"+labConstructionProject.getUser().getUsername()+"'");
		}
		
		hql.append(" order by i.createdAt desc");
		return labConstructionProjectDAO.executeQuery(hql.toString(), (currpage-1)*pageSize, pageSize);
	}
	
	
	/***************************
	 * 功能：根据查询条件实验项目列表（不分页）
	 * 作者： 贺子龙
	 * 日期：2015-10-01
	 **************************/
	@Override
	public List<LabConstructionProject> findAllLabConstructionProjectsByQuery(LabConstructionProject labConstructionProject){//根据项目名称、项目编号、项目负责人、项目状态四个条件查询项目立项
		StringBuffer hql = new StringBuffer("select i from LabConstructionProject i  where 1=1");
		//项目名称条件
		if(labConstructionProject.getProjectName()!=null && !"".equals(labConstructionProject.getProjectName()))
		{
			hql.append(" and i.projectName like '%"+labConstructionProject.getProjectName()+"%'");
		}
		
		//项目编号条件
		if(labConstructionProject.getProjectNumber()!=null && !"".equals(labConstructionProject.getProjectNumber()))
		{
			hql.append(" and i.projectNumber like '%"+labConstructionProject.getProjectNumber()+"%'");
		}
		//项目状态
		if(labConstructionProject.getAuditResults()!=null && labConstructionProject.getAuditResults()!=0)
		{
			hql.append(" and i.auditResults ="+labConstructionProject.getAuditResults());
		}
		//项目负责人条件
		if(labConstructionProject.getUser()!=null && labConstructionProject.getUser().getUsername()!="")
		{
			hql.append(" and i.user.username ='"+labConstructionProject.getUser().getUsername()+"'");
		}
		
		hql.append(" order by i.createdAt desc");
		return labConstructionProjectDAO.executeQuery(hql.toString(), 0, -1);
	}

	/***************************
	 * 功能：根据查询条件实验项目记录数量
	 * 作者： 贺子龙
	 * 日期：2015-10-01
	 **************************/
	public int findAllLabConstructionProjectsByQueryCount(LabConstructionProject labConstructionProject){
		StringBuffer hql = new StringBuffer("select  count(i) from LabConstructionProject i  where 1=1");
		//项目名称条件
		if(labConstructionProject.getProjectName()!=null && !"".equals(labConstructionProject.getProjectName()))
		{
			hql.append(" and i.projectName like '%"+labConstructionProject.getProjectName()+"%'");
		}
		
		//项目编号条件
		if(labConstructionProject.getProjectNumber()!=null && !"".equals(labConstructionProject.getProjectNumber()))
		{
			hql.append(" and i.projectNumber like '%"+labConstructionProject.getProjectNumber()+"%'");
		}		
		
		//项目负责人条件
		if(labConstructionProject.getUser()!=null && labConstructionProject.getUser().getUsername()!="")
		{
			hql.append(" and i.user.username ='"+labConstructionProject.getUser().getUsername()+"'");
		}
		
		//项目状态
		if(labConstructionProject.getAuditResults()!=null)
		{
			hql.append(" and i.auditResults ="+labConstructionProject.getAuditResults());
		}
		
		return ((Long) labConstructionProjectDAO.createQuerySingleResult(hql.toString()).getSingleResult()).intValue();
	}
	
	
	/********************************************
	 * 功能：找出LabConstructionProject表中的项目负责人字段
	 * 作者：贺子龙
	 * 日期：2015-10-01
	 ********************************************/
	@Override
	public Map<String, String> findAllProjectManagers() {
		Map<String, String> map=new HashMap<String, String>();
		
		String s="select u from LabConstructionProject u";
		List<LabConstructionProject>   list=labConstructionProjectDAO.executeQuery(s);
		if(list.size()>0){
			for(LabConstructionProject lc:list){
				if (lc.getUser()!=null ) {
					
					map.put(lc.getUser().getUsername(), lc.getUser().getCname());
				}
			}
		}
		return map;
		
	}
	
	/********************************************
	 * 功能：保存项目立项的一条记录
	 * 作者：贺子龙
	 * 日期：2015-10-01
	 ********************************************/
	@Override
	public LabConstructionProject saveLabConstructionProject(LabConstructionProject labConstructionProject) {
		if(labConstructionProject.getUser()==null || labConstructionProject.getUser().getUsername()==null)
		{
			labConstructionProject.setUser(null);  //项目负责人
		}
		if(labConstructionProject.getSchoolAcademy()==null || labConstructionProject.getSchoolAcademy().getAcademyNumber()==null)
		{
			labConstructionProject.setSchoolAcademy(null);  //所属院（系、部）
		}
		return labConstructionProjectDAO.store(labConstructionProject);
	}
	
	/********************************************
	 * 功能：通过姓名和工号查询用户
	 * 作者：贺子龙
	 * 日期：2015-10-03
	 ********************************************/
	@Override
	public List<User> findUserByCnameAndUsername(User user, Integer labConstructionProjectId,Integer page,int pageSize){
		String sql="select u from User u where 1=1";
		if(labConstructionProjectId!=null && labConstructionProjectId!=0){
			sql+=" and u.username not in(select a.user.username from MLabConstructionProjectUser a where a.labConstructionProject.id="+labConstructionProjectId+")";
		}
		if(user!=null){
			if(user.getCname()!=null&&!user.getCname().equals("")){
				sql+=" and u.cname like '%"+user.getCname()+"%'";
			}
			if(user.getUsername()!=null&&!user.getUsername().equals("")){
				sql+=" and u.username like '%"+user.getUsername()+"%'";
			}
		}
		
		return userDAO.executeQuery(sql,(page-1)*pageSize,pageSize);
	}
	/********************************************
	 * 功能：通过姓名和工号查询用户的数量
	 * 作者：贺子龙
	 * 日期：2015-10-03
	 ********************************************/
	@Override
	public int findUserByCnameAndUsernameCount(User user,Integer labConstructionProjectId){
		String sql="select count(*) from User u where 1=1";
		if(labConstructionProjectId!=null && labConstructionProjectId!=0){
			sql+=" and u.username not in(select a.user.username from MLabConstructionProjectUser a where a.labConstructionProject.id="+labConstructionProjectId+")";
		}
		if(user!=null){
			if(user.getCname()!=null&&!user.getCname().equals("")){
				sql+=" and u.cname like '%"+user.getCname()+"%'";
			}
			if(user.getUsername()!=null&&!user.getUsername().equals("")){
				sql+=" and u.username like '%"+user.getUsername()+"%'";
			}
		}
		
		return ((Long) userDAO.createQuerySingleResult(sql).getSingleResult()).intValue();
		
	}
	/********************************************
	 * 功能：根据主键获取项目立项对象
	 * 作者：贺子龙
	 * 日期：2015-10-03
	 ********************************************/
	public LabConstructionProject findLabConstructionProjectByPrimaryKey(Integer labConstructionProjectId){
		return labConstructionProjectDAO.findLabConstructionProjectByPrimaryKey(labConstructionProjectId);
	}
	
	/****************************************************************************
	 * 功能：删除项目立项
	 * 作者：贺子龙
	 * 时间：2015-10-03
	 ****************************************************************************/
	@Override
	public void deleteLabConstructionProject(Integer labConstructionProjectId) {
		LabConstructionProject labConstructionProject = findLabConstructionProjectByPrimaryKey(labConstructionProjectId);
		if(labConstructionProject != null)
		{
			labConstructionProjectDAO.remove(labConstructionProject);
			labConstructionProjectDAO.flush();
		}
	}
	
	/****************************************************************************
	 * 功能：项目立项上传附件
	 * 作者：贺子龙
	 * 时间：2015-10-14
	 ****************************************************************************/
	@Override
	public void projectDocumentUpload(HttpServletRequest request,HttpServletResponse response, Integer id,int type){
		// TODO Auto-generated method stub
				MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request; 
				 String sep = System.getProperty("file.separator"); 
				 Map files = multipartRequest.getFileMap(); 
				 Iterator fileNames = multipartRequest.getFileNames();
				 boolean flag =false; 
				 String fileDir = request.getSession().getServletContext().getRealPath( "/") +  "upload"+ sep+"labConstructionProject"+sep+id;
				//存放文件文件夹名称
				for(; fileNames.hasNext();){
				  String filename = (String) fileNames.next(); 
				  CommonsMultipartFile file = (CommonsMultipartFile) files.get(filename); 
				  byte[] bytes = file.getBytes(); 
				  if(bytes.length != 0) {
					  // 说明申请有附件
					  if(!flag) { 
						  File dirPath = new File(fileDir); 
						  if(!dirPath.exists()) { 
							  flag = dirPath.mkdirs();
				              } 
				      } 
					  String fileTrueName = file.getOriginalFilename(); 
					  //System.out.println("文件名称："+fileTrueName);
					  File uploadedFile = new File(fileDir + sep + fileTrueName); 
					  //System.out.println("文件存放路径为："+fileDir + sep + fileTrueName);
					  try {
						FileCopyUtils.copy(bytes,uploadedFile);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
					  saveProjectDocument(fileTrueName,id,type);
				  } 
				}
		
	}
	/****************************************************************************
	 * 功能：保存项目立项附件
	 * 作者：贺子龙
	 * 时间：2015-09-28 14:50:33
	 ****************************************************************************/
	public void saveProjectDocument(String fileTrueName, Integer id,int type) {
		// TODO Auto-generated method stub
		//id对应的设备
		LabConstructionProject labConstructionProject=labConstructionProjectDAO.findLabConstructionProjectByPrimaryKey(id);
		CommonDocument doc = new CommonDocument( );
		doc.setDocumentName(fileTrueName);
		String imageUrl="upload/labConstructionProject/"+id+"/"+fileTrueName;
		doc.setDocumentUrl(imageUrl);
		doc.setLabConstructionProject(labConstructionProject);
		doc.setType(type);//图片和文档
		
		documentDAO.store(doc);
	}
	
	/****************************************************************************
	 * 功能：根据文件id下载文件
	 * 作者：贺子龙
	 ****************************************************************************/
	@Override
	public void downloadDocument(HttpServletRequest request,
			HttpServletResponse response, Integer id) {
		try{
			//id对应的document
			CommonDocument doc=documentDAO.findCommonDocumentByPrimaryKey(id);
			//文件名称
			String fileName=doc.getDocumentName();
			String root = System.getProperty("xidlims.root");
			String sep = System.getProperty("file.separator"); 
			//File.separator windows是\，unix是/
			String url=root+ "upload"+ sep+"labConstructionProject"+sep+doc.getLabConstructionProject().getId();
			FileInputStream fis = new FileInputStream(url+sep+fileName);
			response.setCharacterEncoding("utf-8");
			//解决上传中文文件时不能下载的问题
			response.setContentType("multipart/form-data;charset=UTF-8");
			if (request.getHeader("User-Agent").toLowerCase().indexOf("firefox") > 0) {
				fileName = new String(fileName.getBytes("UTF-8"),"ISO8859-1");// firefox浏览器
			} else if (request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0) {
				fileName = URLEncoder.encode(fileName, "UTF-8");// IE浏览器
			} else {
				fileName = URLEncoder.encode(fileName, "UTF-8");
			}
			response.setHeader("Content-Disposition", "attachment;fileName="+fileName.replaceAll(" ", ""));
			
			OutputStream fos = response.getOutputStream();
			byte[] buffer = new byte[8192];
			int count = 0;
			while((count = fis.read(buffer))>0){
				fos.write(buffer,0,count);   
			}
			fis.close();
			fos.close();
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				response.getOutputStream().close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
}
