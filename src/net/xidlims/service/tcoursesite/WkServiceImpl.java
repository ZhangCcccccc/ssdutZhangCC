package net.xidlims.service.tcoursesite;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.xidlims.dao.WkFolderDAO;
import net.xidlims.dao.WkCourseDAO;
import net.xidlims.domain.WkFolder;
import net.xidlims.domain.User;
import net.xidlims.domain.WkCourse;
import net.xidlims.domain.WkUpload;
import net.xidlims.service.common.ShareService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;




@Service("WkService")
public class WkServiceImpl implements  WkService {
	
	@Autowired
	private WkUploadService wkUploadService;
	@Autowired
	private WkFolderDAO folderDAO;
	@Autowired
	private ShareService shareService;
	@Autowired
	private WkCourseDAO wkCourseDAO;
	
	/**************************************************************************
	 * Description:根据路径大小以及内容生成二维码
	 * 
	 * @author：于侃
	 * @date ：2016-09-13
	 **************************************************************************/
	@Override
	public void encode(String contents, int width, int height, String imgPath) {
		Map<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
		// 指定纠错等级
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
		// 指定编码格式
		hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
		try {
			BitMatrix bitMatrix = new MultiFormatWriter().encode(contents, BarcodeFormat.QR_CODE, width, height, hints);
			File file=new File(imgPath);
			if(!file.exists()){
				file.mkdirs();
			}
			//生成二维码
			MatrixToImageWriter.writeToFile(bitMatrix, "png", file);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**************************************************************************
	 * Description:处理ajax上传过来文件，支持多文件 
	 * 
	 * @author：
	 * @date ：
	 **************************************************************************/
	@Override
	public int processUpload(HttpServletRequest request) {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		String sep = System.getProperty("file.separator");
		Map<?, ?> files = multipartRequest.getFileMap();
		Iterator<?> fileNames = multipartRequest.getFileNames();
		String resourceLis = "";
		boolean flag = false;
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyyMMdd");
		String fileDir = request.getSession().getServletContext().getRealPath("/") + "upload" + sep
				+ dateformat.format(new Date()) + sep;

		// 存放文件文件夹名称
		for (; fileNames.hasNext();) {
			String filename = (String) fileNames.next();
			CommonsMultipartFile file = (CommonsMultipartFile) files.get(filename);
			byte[] bytes = file.getBytes();
			if (bytes.length != 0) {
				// 说明申请有附件
				if (!flag) {
					File dirPath = new File(fileDir);
					if (!dirPath.exists()) {
						flag = dirPath.mkdirs();
					}
				}

				String fileTrueName = file.getOriginalFilename();
				String suffix = fileTrueName.substring(fileTrueName.lastIndexOf("."));
				/** 使用UUID生成文件名称* */
				String logImageName = UUID.randomUUID().toString() + suffix;// 构建文件名称

				/** 拼成完整的文件保存路径加文件* */
				String fileName = request.getSession().getServletContext().getRealPath("/upload/") + sep
						+ dateformat.format(new Date()) + sep + logImageName;

				File uploadFile = new File(fileName);
				try {
					FileCopyUtils.copy(bytes, uploadFile);
				} catch (IOException e) {
					e.printStackTrace();
				}

				WkUpload upload = new WkUpload();

				upload.setUrl("/upload/" + dateformat.format(new Date()) + "/" + logImageName);
				upload.setName(fileTrueName);
				int id = wkUploadService.saveUpload(upload);
				resourceLis = resourceLis + String.valueOf(id) + ",";
				return id;
			}

		}

		return 0;
	}
	/**************************************************************************
	 * Description:处理资源列表的字符串 输入：包含资源id字符串，比如：,1632,1633,1634,1635
	 * 
	 * @author：于侃
	 * @date ：2016-09-13
	 **************************************************************************/
	@Override
	public List<WkUpload> getUpload(String list) {
		// 要返回的集合
		List<WkUpload> upload = new ArrayList<WkUpload>();
		// 字符串列表判空
		if (list != null && list.length() > 0 && list.indexOf(",") != -1) {
			//通过分隔符","将资源id读取到s中
			String[] s = list.split(",");
			//循环每个资源文件
			for (String id : s) {
				// 资源id判空
				if (id != null && id.length() > 0) {
					//通过id查询上传文件
					WkUpload u = wkUploadService.findUploadByPrimaryKey(Integer.valueOf(id));
					if (u != null) {
						// 添加资源
						upload.add(u);
					}
				}
			}
		}
		return upload;
	}

	/**************************************************************************
	 * Description:根据课程站点id查询该站点的一级目录
	 * 
	 * @author：
	 * @date ：
	 **************************************************************************/
	@Override
	public List<WkFolder> findFirstFolderBySiteId(Integer id) {
		StringBuffer sbf=new StringBuffer("select f from Folder f where f.wkCourse.id="+id+" and f.folder.id is null");
		return folderDAO.executeQuery(sbf.toString(), 0,-1);
	}

	/**************************************************************************
	 * Description:根据文件夹id构造该文件夹的子级目录 输入：文件夹id
	 * 
	 * @author：
	 * @date ：
	 **************************************************************************/
	@Override
	public String findFolderHTMLById(Integer id) {
		String str="";
		WkFolder folder=folderDAO.findWkFolderByPrimaryKey(id);
		//设置时间格式
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time="";
		for(WkFolder f : folder.getWkFolders()){
			if(f.getUpdateTime()!=null){
				time=sdf.format(f.getUpdateTime().getTime());
			}
			
			if(f.getWkFolders().size()>0){//有子级目录
				str+="<tr id='"+f.getId()+"' pId='"+id+"' hasChild='true'><td>"+f.getName()+"</td><td>"+f.getUser().getCname()+"</td><td>"+time+"</td><td>"+f.getWkFolders().size()+
						"项</td><td><a href='javascript:void(0);' onclick='add("+f.getId()+")'>增加 </a><a href='javascript:void(0);' onclick='edit("+f.getId()+")'>修改 </a><a href='javascript:void(0);' onclick='upload("+f.getId()+")'>上传 </a> </td></tr>";
			}else{//没有子级目录，显示当前文件夹和下面的文件
				str+="<tr id='"+f.getId()+"' pId='"+id+"'><td>"+f.getName()+"</td><td>"+f.getUser().getCname()+"</td><td>"+time+"</td><td>"+f.getUploads().size()+"个文件</td>" +
						"<td><a href='javascript:void(0);' onclick='add("+f.getId()+")'>增加 </a><a href='javascript:void(0);' onclick='edit("+f.getId()+")'>修改 </a>" +
								"<a onclick='deleteFolder("+f.getId()+",this)' >删除 </a>" +
										"<a href='javascript:void(0);' onclick='upload("+f.getId()+")'>上传 </a> </td></tr>";
				Set<WkUpload> uploads=f.getUploads();
				if(uploads.size()>0){
					User user = shareService.getUser();
					for (WkUpload wkUpload : uploads) {
						str+="<tr id='"+wkUpload.getId()+"' pId='"+f.getId()+"'><td>"+wkUpload.getName()+"</td>"+
	                    "<td>"+(wkUpload.getUser()==null?"":wkUpload.getUser().getCname())+"</td><td>"+time+"</td>"+
	                    "<td>"+(wkUpload.getSize()==null?"":wkUpload.getSize())+"</td><td>";
						if (user!=null) {
							str+="<a onclick='deleteFile("+wkUpload.getId()+",this)' >删除</a>";
							str+="<a href='javascript:void(0);' onclick='downloadFile("+wkUpload.getId()+")' >下载</a>";
						}
	                    str+="</td></tr>";
					}
				}
				
			}
			
		}
		if(folder.getUploads().size()>0){
			User user = shareService.getUser();
			for (WkUpload wkUpload : folder.getUploads()) {
				str+="<tr id='"+wkUpload.getId()+"' pId='"+folder.getId()+"'><td>"+wkUpload.getName()+"</td>"+
                "<td>"+(wkUpload.getUser()==null?"":wkUpload.getUser().getCname())+"</td><td>"+time+"</td>"+
                "<td>"+(wkUpload.getSize()==null?"":wkUpload.getSize())+"</td><td>";
				if (user!=null) {
					str+="<a onclick='deleteFile("+wkUpload.getId()+",this)' >删除</a>";
					str+="<a href='javascript:void(0);' onclick='downloadFile("+wkUpload.getId()+")' >下载 </a>";
				}
                str+="</td></tr>";
			}
		}
		return str;
	}
	/**************************************************************************
	 * Description:保存文件夹
	 * 
	 * @author：
	 * @date ：
	 **************************************************************************/
	@Override
	public WkFolder saveFolder(WkFolder folder) {
		
		return folderDAO.store(folder);
	}
	
	/**************************************************************************
	 * Description:给树形表上传文件,并关联文件所属的文件夹
	 * 
	 * @author：
	 * @date ：
	 **************************************************************************/
	@Override
	public void fileUpload(HttpServletRequest request, Integer id) {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		String sep = System.getProperty("file.separator");
		Map<?, ?> files = multipartRequest.getFileMap();
		Iterator<?> fileNames = multipartRequest.getFileNames();
		//String resourceLis = "";
		boolean flag = false;
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyyMMdd");
		String fileDir = request.getSession().getServletContext().getRealPath("/") + "upload" + sep
				+ dateformat.format(new Date()) + sep;

		// 存放文件文件夹名称
		for (; fileNames.hasNext();) {
			String filename = (String) fileNames.next();
			CommonsMultipartFile file = (CommonsMultipartFile) files.get(filename);
			byte[] bytes = file.getBytes();
			if (bytes.length != 0) {
				// 说明申请有附件
				if (!flag) {
					File dirPath = new File(fileDir);
					if (!dirPath.exists()) {
						flag = dirPath.mkdirs();
					}
				}

				String fileTrueName = file.getOriginalFilename();
				String suffix = fileTrueName.substring(fileTrueName.lastIndexOf("."));
				/** 使用UUID生成文件名称* */
				String logImageName = UUID.randomUUID().toString() + suffix;// 构建文件名称

				/** 拼成完整的文件保存路径加文件* */
				String fileName = request.getSession().getServletContext().getRealPath("/upload/") + sep
						+ dateformat.format(new Date()) + sep + logImageName;
System.out.println("即将保存的文件路径为："+fileName);
				File uploadFile = new File(fileName);
				try {
					FileCopyUtils.copy(bytes, uploadFile);
				} catch (IOException e) {
					e.printStackTrace();
				}

				WkUpload upload = new WkUpload();
				long a=uploadFile.length()/1024/1024;
				
				DecimalFormat fmt=new DecimalFormat("#.##");
				String size="";
				if (a!=0) {
					size=fmt.format(a)+"M";
				}else{
					a = uploadFile.length()/1024;
					size = fmt.format(a)+"KB";
				}
				
				upload.setSize(size);
				upload.setUser(shareService.getUser());
				upload.setUrl("/upload/" + dateformat.format(new Date()) + "/" + logImageName);
				upload.setName(fileTrueName);
				//文件所属的文件夹
				WkFolder folder=folderDAO.findWkFolderByPrimaryKey(id);
				upload.setWkFolder(folder);
				wkUploadService.saveUpload(upload);
				
			}

		}
		
	}
	/**************************************************************************
	 * Description:下载树形表的文件
	 * 
	 * @author：
	 * @date ：
	 **************************************************************************/
	@Override
	public void downloadFile(WkUpload upload, HttpServletRequest request,
			HttpServletResponse response) {
		try{			
			String fileName=upload.getName();
			String root = System.getProperty("xidlims.root");
			FileInputStream fis = new FileInputStream(root+upload.getUrl());
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
	/**************************************************************************
	 * Description:删除树形表中的文件夹（同时删除文件）
	 * 
	 * @author：
	 * @date ：
	 **************************************************************************/
	@Override
	public void deleteFolder(WkFolder folder) {
		String root=System.getProperty("xidlims.root");
		Set<WkUpload> uploads=folder.getUploads();
		//删除文件
		for (WkUpload wkUpload : uploads) {
			shareService.deleteFile(root+wkUpload.getUrl());
		}
		//删除文件夹
		folderDAO.remove(folder);
	}
	/**************************************************************************
	 * Description:根据文件夹id构造该文件夹的子级目录(CMS前端) 输入：文件夹id
	 * 
	 * @author：
	 * @date ：
	 **************************************************************************/
	@Override
	public String findCmsFolderHTMLById(HttpServletRequest request,Integer id) {
		String str="";
		WkFolder folder=folderDAO.findWkFolderByPrimaryKey(id);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time="";
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
		for(WkFolder f : folder.getWkFolders()){
			if(f.getUpdateTime()!=null){
				time=sdf.format(f.getUpdateTime().getTime());
			}
			
			if(f.getWkFolders().size()>0){//有子级目录
				str+="<tr id='"+f.getId()+"' pId='"+id+"' hasChild='true'><td>"+f.getName()+"</td><td>"+f.getUser().getCname()+"</td><td>"+time+"</td><td>"+f.getWkFolders().size()+
						"项</td><td></td></tr>";
			}else{//没有子级目录，显示当前文件夹和下面的文件
				str+="<tr id='"+f.getId()+"' pId='"+id+"'><td>"+f.getName()+"</td><td>"+f.getUser().getCname()+"</td><td>"+time+"</td><td>"+f.getUploads().size()+"个文件</td>" +
						"<td></td></tr>";
				Set<WkUpload> uploads=f.getUploads();
				if(uploads.size()>0){
					User user = shareService.getUser();
					for (WkUpload wkUpload : uploads) {
						str+="<tr id='"+wkUpload.getId()+"' pId='"+f.getId()+"'><td>"+wkUpload.getName()+"</td>"+
	                    "<td>"+(wkUpload.getUser()==null?"":wkUpload.getUser().getCname())+"</td><td>"+time+"</td>"+
	                    "<td>"+(wkUpload.getSize()==null?"":wkUpload.getSize())+"</td><td>";
						if (user!=null) {
							String url = basePath + wkUpload.getUrl();
							str+="<a href='"+url+"' onclick='return checkUrl(this)' target='blank'>预览</a>";
							str+="<a style='margin-left:5px;' href='javascript:void(0);' onclick='downloadFile("+wkUpload.getId()+")' >下载 </a>";
						}
	                    str+="</td></tr>";
					}
				}
				
			}
			
		}
		if(folder.getUploads().size()>0){
			User user = shareService.getUser();
			for (WkUpload wkUpload : folder.getUploads()) {
				str+="<tr id='"+wkUpload.getId()+"' pId='"+folder.getId()+"'><td>"+wkUpload.getName()+"</td>"+
                "<td>"+(wkUpload.getUser()==null?"":wkUpload.getUser().getCname())+"</td><td>"+time+"</td>"+
                "<td>"+(wkUpload.getSize()==null?"":wkUpload.getSize())+"</td><td>";
				if (user!=null) {
					String url = basePath + wkUpload.getUrl();
					str+="<a href='"+url+"' onclick='return checkUrl(this)' target='blank'>预览</a>";
					str+="<a style='margin-left:5px;' href='javascript:void(0);' onclick='downloadFile("+wkUpload.getId()+")' >下载 </a>";
				}
                str+="</td></tr>";
			}
		}
		return str;
	}

	/**************************************************************************
	 * Description:根据微课课程主键查询对象
	 * 
	 * @author：
	 * @date ：
	 **************************************************************************/
	@Override
	public WkCourse findWkCourseByPrimaryKey(Integer id) {
		WkCourse wkCourse = wkCourseDAO.findWkCourseByPrimaryKey(id);
		return wkCourse;
	}
}