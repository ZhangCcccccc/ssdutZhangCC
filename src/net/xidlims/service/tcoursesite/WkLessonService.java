package net.xidlims.service.tcoursesite;

import java.util.Map;

import net.xidlims.domain.WkChapter;
import net.xidlims.domain.WkLesson;



public interface WkLessonService {
	/**********************************************************************************
	 * 根据主键查询课时 
	 * 
	 **********************************************************************************/
	public WkLesson findWkLessonByPrimaryKey(int lessonId);
	/**********************************************************************************
	 * 保存课时 
	 * 
	 **********************************************************************************/
	public WkLesson saveLesson(WkLesson lesson);
	/**********************************************************************************
	 * 删除课时 
	 * 
	 **********************************************************************************/
	public void deleteLesson(WkLesson lesson);
	
	/**********************************************************************************
	 * 查询本课时所属章节下最大课时排序数值 
	 * 
	 **********************************************************************************/
	public int queryMaxLessonSeqByChapterId(WkChapter wkChapter);
	/**********************************************************************************
	 * 查询本课时所属章节下最小课时排序数值 
	 * 
	 **********************************************************************************/
	public int queryMinLessonSeqByChapterId(WkChapter wkChapter);
	/**********************************************************************************
	 * 查询比本课时小的课时排序数值 
	 * 
	 **********************************************************************************/
	public int querysmallLessonSeqByChapterId(WkLesson wkLesson);
	/**********************************************************************************
	 * 查询比本课时小的课时排序数值 
	 * 
	 **********************************************************************************/
	public int querybigLessonSeqByChapterId(WkLesson wkLesson);
	
	/**********************************************************************************
	 * 复制课时资源
	 * 裴继超
	 * 2016年6月15日9:59:03
	 **********************************************************************************/
	public int copyWkLesson(Integer tCourseSiteId,Integer wkLessonId,Integer chapterId);
	
	/**************************************************************************
	 * Description:知识技能体验-ajax根据章节id和获取课时列表map
	 * 
	 * @author：裴继超
	 * @date ：2016年8月23日13:45:10
	 **************************************************************************/
	public Map findLessonMapByChapterId(Integer chapterId);
	
}