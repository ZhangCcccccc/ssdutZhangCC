package net.xidlims.service.tcoursesite;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.xidlims.common.DiskFile;
import net.xidlims.domain.User;
import net.xidlims.domain.WkFolder;
import net.xidlims.domain.WkUpload;



public interface NetDiskService {
	
	/**************************************************************************
	 * Description:资源模块-根据路径和类别获取upload对象
	 * 
	 * @author：裴继超
	 * @date ：2016-8-30
	 **************************************************************************/
 	public WkUpload findUploadByUrlAndType(String url, int type); 	
 	
	/**************************************************************************
	 * Description:资源模块-获取对应文件夹下的资源列表
	 * 
	 * @author：裴继超
	 * @date ：2016-8-31
	 **************************************************************************/
 	public List<List<DiskFile>> findListFiles(String rootPath, String folder, 
			boolean onlyDirectory) throws FileNotFoundException, IOException;
 	
	/**************************************************************************
	 * Description:资源模块-文件排序（文件夹在前，文件在后）
	 * 
	 * @author：裴继超
	 * @date ：2016-8-31
	 **************************************************************************/
 	public List<File> sortFiles(File[] files); 
 	
 	
	/**************************************************************************
	 * Description:资源模块-把文件或文件夹转化为DiskFile对象
	 * 
	 * @author：裴继超
	 * @date ：2016-8-31
	 **************************************************************************/
 	public DiskFile changeToDiskFileByFlie(File file,String rootPath, String folder
			)throws FileNotFoundException, IOException ;
 	
	/**************************************************************************
	 * Description:资源模块-保存文件、文件夹、链接
	 * 
	 * @author：裴继超
	 * @date ：2016-9-1
	 **************************************************************************/
	public WkUpload saveUpload(WkUpload upload);
	
	/**************************************************************************
	 * Description:资源模块-把文件夹，文件列表转化成String
	 * 
	 * @author：裴继超
	 * @date ：2016-9-7
	 **************************************************************************/
	public String changeFilesListToString(String path,List<List<DiskFile>> listListFiles,
			Integer tCourseSiteId);
	
	/**************************************************************************
	 * Description:资源模块-批量删除文件
	 * 
	 * @author：裴继超
	 * @date ：2016-9-12
	 **************************************************************************/
	public String deleteFiles(String[] wkUploadIds,Integer tCourseSiteId,
			HttpServletRequest request);
	
	/**************************************************************************
	 * Description:资源模块-删除文件夹下所有文件
	 * 
	 * @author：裴继超
	 * @date ：2016-9-12
	 **************************************************************************/
	public void deleteFilesByFolder(String url,HttpServletRequest request);
	
	/**************************************************************************
	 * Description:资源模块-解压缩zip包 
	 * 
	 * @author：于侃
	 * @date ：2016-09-18
	 * @param zipFilePath zip文件的全路径 
     * @param unzipFilePath 解压后的文件保存的路径 
     * @param includeZipFileName 解压后的文件保存的路径是否包含压缩文件的文件名。true-包含；false-不包含
	 * @throws Exception 
	 **************************************************************************/
    public String unzip(String zipFilePath, String unzipFilePath, boolean includeZipFileName,String path,int tCourseSiteId) throws Exception;
    
    /**************************************************************************
	 * Description:资源模块程-搜索资源
	 * 
	 * @author：于侃
	 * @date ：2016年10月8日 10:11:46
	 **************************************************************************/
 	public List<WkUpload> searchWkUploads(String uploadName,Integer tCourseSiteId,int uploadType);
 	
 	/**************************************************************************
	 * Description:实验项目上传资源容器中对应也上传-原生方式上传
	 * 
	 * @author：马帅
	 * @date ：2017-8-22
	 **************************************************************************/
 	public void uploadResource(HttpServletRequest request, String path,
			Integer type, Integer id);
 	/**************************************************************************
	 * Description:实验项目-多文件上传
	 * 
	 * @author：马帅
	 * @date ：2017-8-21
	 **************************************************************************/
	public  Map<String,String> uploadMultiFile(HttpServletRequest request, String path,
	Integer type, Integer siteId);
}