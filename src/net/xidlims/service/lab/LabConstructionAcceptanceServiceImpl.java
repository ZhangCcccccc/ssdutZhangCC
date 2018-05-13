package net.xidlims.service.lab;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.xidlims.dao.CommonDocumentDAO;
import net.xidlims.dao.LabConstructionAcceptanceDAO;
import net.xidlims.domain.CommonDocument;
import net.xidlims.domain.LabConstructionAcceptance;
import net.xidlims.domain.User;
import net.xidlims.service.common.ShareService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * Spring service that handles CRUD requests for LabConstructionAcceptance entities
 * 
 */

@Service("LabConstructionAcceptanceService")
@Transactional
public class LabConstructionAcceptanceServiceImpl implements
		LabConstructionAcceptanceService {
	
	@Autowired private LabConstructionAcceptanceDAO labConstructionAcceptanceDAO;
	@Autowired private CommonDocumentDAO documentDAO;
	@Autowired private ShareService shareService;

	/***********************************
	 * 功能：满足查询条件的所有实验室建设项目验收数量
	 * 作者：贺子龙
	 * 日期：2015-10-14
	 ***********************************/
	@Override
	public int findAllLabConstructionAcceptancesByQueryCount(LabConstructionAcceptance labConstructionAcceptance){
		StringBuffer hql = new StringBuffer("select  count(i) from LabConstructionAcceptance i  where 1=1");
		//项目名称条件
		if(labConstructionAcceptance.getLabConstructionProject()!=null && !"".equals(labConstructionAcceptance.getLabConstructionProject().getProjectName()))
		{
			hql.append(" and i.labConstructionProject.projectName like '%"+labConstructionAcceptance.getLabConstructionProject().getProjectName()+"%'");
		}
		return ((Long) labConstructionAcceptanceDAO.createQuerySingleResult(hql.toString()).getSingleResult()).intValue();
	}
	
	
	/***************************
	 * 功能：根据查询条件分页实验项目验收记录
	 * 作者： 贺子龙
	 * 日期：2015-10-14
	 **************************/
	@Override
	public List<LabConstructionAcceptance> findAllLabConstructionAcceptancesByQuery(Integer currpage, Integer pageSize,
			LabConstructionAcceptance labConstructionAcceptance){
		StringBuffer hql = new StringBuffer("select  i from LabConstructionAcceptance i  where 1=1");
		//项目名称条件
		if(labConstructionAcceptance.getLabConstructionProject()!=null && !"".equals(labConstructionAcceptance.getLabConstructionProject().getProjectName()))
		{
			hql.append(" and i.labConstructionProject.projectName like '%"+labConstructionAcceptance.getLabConstructionProject().getProjectName()+"%'");
		}
		hql.append(" order by i.createdAt desc");
		return labConstructionAcceptanceDAO.executeQuery(hql.toString(), (currpage-1)*pageSize, pageSize);
		
	}
	
	/***************************
	 * 功能：根据主键查询项目验收
	 * 作者： 贺子龙
	 * 日期：2015-10-14
	 **************************/
	@Override
	public LabConstructionAcceptance findLabConstructionAcceptanceByPrimaryKey(Integer labConstructionAcceptanceId){
		return labConstructionAcceptanceDAO.findLabConstructionAcceptanceByPrimaryKey(labConstructionAcceptanceId);
	}
	
	/********************************
	 * 功能：保存项目验收
	 * 作者：贺子龙
	 * 日期：2015-10-14
	 *********************************/
	@Override
	public LabConstructionAcceptance saveLabConstructionAcceptance(LabConstructionAcceptance labConstructionAcceptance){
		if (labConstructionAcceptance.getLabConstructionProject()==null || labConstructionAcceptance.getLabConstructionProject().getId()==null) {
		
			labConstructionAcceptance.setLabConstructionProject(null);
		}
		return labConstructionAcceptanceDAO.store(labConstructionAcceptance);
	}
	
	/********************************
	 * 功能：项目验收上传附件
	 * 作者：贺子龙
	 * 日期：2015-10-14
	 *********************************/
	@Override
	public void acceptanceDocumentUpload(HttpServletRequest request,HttpServletResponse response, Integer id,int type,int flaggy){
		
		// TODO Auto-generated method stub
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request; 
		 String sep = System.getProperty("file.separator"); 
		 Map files = multipartRequest.getFileMap(); 
		 Iterator fileNames = multipartRequest.getFileNames();
		 boolean flag =false; 
		 String fileDir = request.getSession().getServletContext().getRealPath( "/") +  "upload"+ sep+"labConstructionAcceptance"+sep+id;
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
			  saveProjectDocument(fileTrueName,id,type,flaggy);
		  } 
		}
	}
	
	
	/****************************************************************************
	 * 功能：保存项目立项附件
	 * 作者：贺子龙
	 * 时间：2015-09-28 14:50:33
	 ****************************************************************************/
	public void saveProjectDocument(String fileTrueName, Integer id,int type,int flaggy) {
		// TODO Auto-generated method stub
		//id对应的设备
		LabConstructionAcceptance labConstructionAcceptance=labConstructionAcceptanceDAO.findLabConstructionAcceptanceByPrimaryKey(id);
		CommonDocument doc = new CommonDocument( );
		doc.setDocumentName(fileTrueName);
		String imageUrl="upload/labConstructionAcceptance/"+id+"/"+fileTrueName;
		doc.setDocumentUrl(imageUrl);
		doc.setLabConstructionAcceptance(labConstructionAcceptance);
		doc.setType(type);//图片和文档
		doc.setFlag(flaggy);//（ 1 项目建设阶段资料  2项目教学阶段资料  仪器设备资料   4 综合效益资料）
		doc.setCreatedAt(Calendar.getInstance());
		User user=new User();
		user=shareService.getUser();
		doc.setUser(user);//上传人为当前登录人
		documentDAO.store(doc);
	}
	/****************************************************************************
	 * 功能：下载项目立项附件
	 * 作者：贺子龙
	 * 时间：2015-10-14
	 ****************************************************************************/
	@Override
	public void downloadAcceptanceDocument(HttpServletRequest request,
			HttpServletResponse response, Integer id){
		
		try{
			//id对应的document
			CommonDocument doc=documentDAO.findCommonDocumentByPrimaryKey(id);
			//文件名称
			String fileName=doc.getDocumentName();
			String root = System.getProperty("xidlims.root");
			String sep = System.getProperty("file.separator"); 
			//File.separator windows是\，unix是/
			String url=root+ "upload"+ sep+"labConstructionAcceptance"+sep+doc.getLabConstructionAcceptance().getId();
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
	
	
	/********************************
	 * 功能：删除项目验收
	 * 作者：贺子龙
	 * 日期：2015-10-14
	 *********************************/
	@Override
	public void deleteLabConstructionAcceptance(Integer labConstructionAcceptanceId){
		
		LabConstructionAcceptance labConstructionAcceptance=findLabConstructionAcceptanceByPrimaryKey(labConstructionAcceptanceId);
		if (labConstructionAcceptance!=null) {
			labConstructionAcceptanceDAO.remove(labConstructionAcceptance);
			labConstructionAcceptanceDAO.flush();
		}
		
	}
}
