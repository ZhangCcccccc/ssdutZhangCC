package net.xidlims.service.tcoursesite;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.xidlims.domain.WkFolder;
import net.xidlims.domain.WkCourse;
import net.xidlims.domain.WkUpload;



public interface WkService {
	/**********************************************************************************
	 * 根据路径大小以及内容生成二维码
	 * 
	 **********************************************************************************/
	public void encode(String contents, int width, int height, String imgPath);
	/**********************************************************************************
	 * 处理ajax上传过来文件，支持多文件 
	 * 返回：文件保存的记录id
	 * 
	 **********************************************************************************/
	public int processUpload(HttpServletRequest request);
	/**********************************************************************************
	 * 处理资源列表的字符串 
	 * 输入：包含资源id字符串，比如：,1632,1633,1634,1635
	 * 
	 **********************************************************************************/
	public List<WkUpload> getUpload(String sourceList);
	/**********************************************************************************
	 * 根据课程站点id查询该站点的一级目录
	 * 
	 * 
	 **********************************************************************************/
	public List<WkFolder> findFirstFolderBySiteId(Integer id);
	/**********************************************************************************
	 * 根据文件夹id构造该文件夹的子级目录
	 * 输入：文件夹id
	 * 
	 **********************************************************************************/
	public String findFolderHTMLById(Integer id);
	/**********************************************************************************
	 * 保存文件夹
	 *
	 **********************************************************************************/
	public WkFolder saveFolder(WkFolder folder);
	/**********************************************************************************
	 * 给树形表上传文件,并关联文件所属的文件夹
	 *
	 **********************************************************************************/
	public void fileUpload(HttpServletRequest request, Integer id);
	/**********************************************************************************
	 * 下载树形表的文件
	 *
	 **********************************************************************************/
	public void downloadFile(WkUpload upload, HttpServletRequest request,
			HttpServletResponse response);
	/**********************************************************************************
	 * 删除树形表中的文件夹（同时删除文件）
	 *
	 **********************************************************************************/
	public void deleteFolder(WkFolder folder);
	/**********************************************************************************
	 * 根据文件夹id构造该文件夹的子级目录(CMS前端)
	 * 输入：文件夹id
	 * @param request 
	 * 
	 **********************************************************************************/
	public String findCmsFolderHTMLById(HttpServletRequest request, Integer id);
	
	/**
	 * 根据微课课程主键查询对象
	 * @param id
	 * @return
	 */
	public WkCourse findWkCourseByPrimaryKey(Integer id);
}