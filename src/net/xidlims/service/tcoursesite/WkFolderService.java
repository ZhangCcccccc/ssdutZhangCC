package net.xidlims.service.tcoursesite;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.xidlims.domain.TAssignment;
import net.xidlims.domain.WkChapter;
import net.xidlims.domain.WkFolder;

public interface WkFolderService {
	/**********************************************************************************
	 * 保存文件夹
	 * 裴继超
	 * 2016年4月29日9:50:57
	 **********************************************************************************/
	public WkFolder saveWkFolder(WkFolder wkFolder,HttpServletRequest request);
	/**********************************************************************************
	 * 根据主键查询文件夹 
	 * 裴继超
	 * 2016年4月29日9:50:57
	 **********************************************************************************/
	public WkFolder findWkFolderByPrimaryKey(Integer wkFolderId);
	/**********************************************************************************
	 * 删除文件夹 
	 * 裴继超
	 * 2016年4月29日9:50:57
	 **********************************************************************************/
	public void deleteWkFolder(WkFolder wkFolder);
	
	/**********************************************************************************
	 * 复制文件夹资源
	 * 裴继超
	 * 2016年6月15日9:59:03
	 **********************************************************************************/
	public int copyWkFolder(Integer tCourseSiteId,Integer wkFolderId,Integer wkChapterId,
			Integer wkLessonId,HttpServletRequest request) ;
	
	/**********************************************************************************
	 * 复制文件夹
	 * 裴继超
	 * 2016年6月15日15:31:46
	 **********************************************************************************/
	public WkFolder copyFolder(WkFolder folder,WkFolder newParentFolder,HttpServletRequest request) ;
	
	/**********************************************************************************
	 * 查找资源文件夹个数
	 * 裴继超
	 * 2016年7月9日20:26:34
	 **********************************************************************************/
	public int findFileFolderSize(Integer tCourseSiteId) ;
	
	/**************************************************************************
	 * Description:知识技能体验-ajax根据课时id和获取文件夹列表map
	 * 
	 * @author：裴继超
	 * @date ：2016年8月23日13:45:10
	 **************************************************************************/
	public Map findFolderMapByLessonId(Integer lessonId);
	
	/**************************************************************************
	 * Description:实验技能验-保存资源文件夹
	 * 
	 * @author：裴继超
	 * @date ：2016-9-28
	 **************************************************************************/
	public WkFolder saveTExperimentSkillWkFolder(WkChapter chapter,String name,Integer type);
	
	/**************************************************************************
	 * Description:实验技能验-保存资源文件夹
	 * 
	 * @author：裴继超
	 * @date ：2016-9-28
	 **************************************************************************/
	public String batchMadeQRCode(HttpServletRequest request);
	
	/**************************************************************************
	 * Description:可视化-根据类型获取文件夹
	 * 
	 * @author：裴继超
	 * @date ：2016-11-9
	 **************************************************************************/
	public List<WkFolder> findFoldersByType(Integer type);
	

	
}