package net.xidlims.service.cms;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.xidlims.dao.CmsDocumentDAO;
import net.xidlims.domain.CmsDocument;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;


@Service("CmsDocumentService")
@Transactional
public class CmsDocumentServiceImpl implements CmsDocumentService {

	@Autowired CmsDocumentDAO documentDAO;
	@Override
	public List<CmsDocument> findAllCmsDocuments() {
		// TODO Auto-generated method stub
		String sql="select d from CmsDocument d where 1=1";
		sql+=" order by createTime desc";
		return documentDAO.executeQuery(sql, 0, -1);
	}

	@Override
	public List<CmsDocument> findAllCmsDocuments(
			int page, int pageSize) {
		// TODO Auto-generated method stub
		String sql="select d from CmsDocument d where 1=1";
		sql+=" order by createTime desc";
		return documentDAO.executeQuery(sql,(page-1)*pageSize,pageSize);
	}
	//查找新生成的附件
		@Override
		public int lastDocument() {
			// TODO Auto-generated method stub
			String sql = "select max(d.id) from CmsDocument d where 1=1";
			int result = (Integer)documentDAO.createQuerySingleResult(sql).getSingleResult();
			return result;
		}

		/****************************************************************************
		 * 功能：上传附件
		 * 作者：裴继超
		 * 时间：2015年12月18日13:01:51
		 ****************************************************************************/
		@Override
		public void uploadDocument(HttpServletRequest request,
				HttpServletResponse response, Integer id,Integer type) {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request; 
			 String sep = System.getProperty("file.separator"); 
			 Map files = multipartRequest.getFileMap(); 
			 Iterator fileNames = multipartRequest.getFileNames();
			 boolean flag =false; 
			 String fileDir = request.getSession().getServletContext().getRealPath( "/") +  "upload"+ sep+"document"+sep+id;
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
				  int endAddress = fileTrueName.lastIndexOf(".");
				  String ss = fileTrueName.substring(endAddress, fileTrueName.length());//后缀名
	              String fileNewName = "document" + id + ss;
				  //System.out.println("文件名称："+fileTrueName);
				  File uploadedFile = new File(fileDir + sep + fileNewName); 
				  //System.out.println("文件存放路径为："+fileDir + sep + fileNewName);
				  try {
					FileCopyUtils.copy(bytes,uploadedFile);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				  	CmsDocument documentlast = documentDAO.findCmsDocumentByPrimaryKey(id);
				    documentlast.setUrl( "/"+"upload"+"/"+"document" +"/"+id+"/"+ fileNewName);
				    documentlast.setProfile(fileTrueName);
				    documentDAO.store(documentlast);
			  } 
			}
		}
		/**********************************************************************************
		 * 下载附件
		 *
		 **********************************************************************************/
		@Override
		public void downloadFile(CmsDocument document, HttpServletRequest request,
				HttpServletResponse response) {
			try{		
				String oldFileName=document.getProfile();
				int endAddress = oldFileName.lastIndexOf(".");
				String ss = oldFileName.substring(endAddress, oldFileName.length());//后缀名
				String fileName = "document"+document.getId()+ss;
				String root = System.getProperty("gvsun.root");
				FileInputStream fis = new FileInputStream(request.getSession().getServletContext().getRealPath( "/")+document.getUrl());
				response.setCharacterEncoding("utf-8");
				//附件原名字
				String newName = document.getProfile();
				//解决上传中文文件时不能下载的问题
				
				response.setContentType("multipart/form-data;charset=UTF-8");
				if (request.getHeader("User-Agent").toLowerCase().indexOf("firefox") > 0) {
					newName = new String(newName.getBytes("UTF-8"),"ISO8859-1");// firefox浏览器
				} else if (request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0) {
					newName = URLEncoder.encode(newName, "UTF-8");// IE浏览器
				} else {
					newName = URLEncoder.encode(newName, "UTF-8");
				}

				response.setHeader("Content-Disposition", "attachment;fileName="+newName.replaceAll(" ", ""));
				
				OutputStream fos = response.getOutputStream();
				byte[] buffer = new byte[8192];
				int count = 0;
				while((count = fis.read(buffer))>0){
					fos.write(buffer,0,count);   
				}
				fis.close();
				fos.close();
			}catch(Exception e){
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
