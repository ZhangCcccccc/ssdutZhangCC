package net.xidlims.service.tcoursesite;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.xidlims.dao.WkLessonDAO;
import net.xidlims.domain.WkChapter;
import net.xidlims.domain.WkLesson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("WkLessonService")
public class WkLessonServiceImpl implements  WkLessonService {
	@Autowired
	private WkLessonDAO wkLessonDAO;
	@Autowired
	private WkChapterService wkChapterService;
	
	
	/**************************************************************************
	 * Description:根据主键查询课时 
	 * 
	 * @author：于侃
	 * @date ：2016-09-13
	 **************************************************************************/
	@Override
	public WkLesson findWkLessonByPrimaryKey(int lessonId) {
		//根据主键查询课时
		return wkLessonDAO.findWkLessonByPrimaryKey(lessonId);
	}

	/**************************************************************************
	 * Description:保存课时 
	 * 
	 * @author：于侃
	 * @date ：2016-09-13
	 **************************************************************************/
	@Override
	public WkLesson saveLesson(WkLesson lesson) {
		//保存课时
		WkLesson l=wkLessonDAO.store(lesson);
//		wkLessonDAO.flush();
		return l;
	}
	
	/**************************************************************************
	 * Description:删除课时 
	 * 
	 * @author：于侃
	 * @date ：2016-09-13
	 **************************************************************************/
	@Override
	public void deleteLesson(WkLesson lesson) {
		//删除课时
		wkLessonDAO.remove(lesson);
	}
	
	/**************************************************************************
	 * Description:查询本课时所属章节下最大课时排序数值 
	 * 
	 * @author：于侃
	 * @date ：2016-09-13
	 **************************************************************************/
	@Override
	public int queryMaxLessonSeqByChapterId(WkChapter wkChapter) {
		//查询本课时所属章节下最大课时排序数值 
		String sql = "select max(c.seq) from WkLesson c where c.wkChapter.id = '"+wkChapter.getId()+"'";
		int result = (Integer)wkLessonDAO.createQuerySingleResult(sql).getSingleResult();
		return result;
	}
	
	/**************************************************************************
	 * Description:查询本课时所属章节下最小课时排序数值 
	 * 
	 * @author：于侃
	 * @date ：2016-09-13
	 **************************************************************************/
	@Override
	public int queryMinLessonSeqByChapterId(WkChapter wkChapter) {
		//查询本课时所属章节下最小课时排序数值 
		String sql = "select min(c.seq) from WkLesson c where c.wkChapter.id = '"+wkChapter.getId()+"'";
		int result = (Integer)wkLessonDAO.createQuerySingleResult(sql).getSingleResult();
		return result;
	}
	
	/**************************************************************************
	 * Description:查询比本课时小的课时排序数值
	 * 
	 * @author：于侃
	 * @date ：2016-09-13
	 **************************************************************************/
	@Override
	public int querysmallLessonSeqByChapterId(WkLesson wkLesson) {
		// TODO Auto-generated method stub
		String sql = "select max(c.seq) from WkLesson c where c.wkChapter.id = '"+wkLesson.getWkChapter().getId()+"' and c.seq < '"+wkLesson.getSeq()+"'";
		int result = (Integer)wkLessonDAO.createQuerySingleResult(sql).getSingleResult();
		return result;
	}
	
	/**************************************************************************
	 * Description:查询比本课时大的课时排序数值
	 * 
	 * @author：于侃
	 * @date ：2016-09-13
	 **************************************************************************/
	@Override
	public int querybigLessonSeqByChapterId(WkLesson wkLesson) {
		// TODO Auto-generated method stub
		String sql = "select min(c.seq) from WkLesson c where c.wkChapter.id = '"+wkLesson.getWkChapter().getId()+"' and c.seq > '"+wkLesson.getSeq()+"'";
		int result = (Integer)wkLessonDAO.createQuerySingleResult(sql).getSingleResult();
		return result;
		
	}
	
	/**************************************************************************
	 * Description:复制课时资源
	 * 
	 * @author：裴继超
	 * @date ：2016年6月15日9:59:03
	 **************************************************************************/
	@Override
	public int copyWkLesson(Integer tCourseSiteId,Integer wkLessonId,Integer chapterId) {
		//通过课时id获取课时
		WkLesson wkLesson = findWkLessonByPrimaryKey(wkLessonId);
		//通过章节id获取章节
		WkChapter wkChapter = wkChapterService.findChapterByPrimaryKey(chapterId);
		//新建课时
		WkLesson newWkLesson = new WkLesson();
		newWkLesson.setTitle(wkLesson.getTitle());
		newWkLesson.setSeq(wkLesson.getSeq());
		newWkLesson.setFileId(wkLesson.getFileId());
		newWkLesson.setContent(wkLesson.getContent());
		newWkLesson.setSourceList(wkLesson.getSourceList());
		newWkLesson.setCodeImgPath(wkLesson.getCodeImgPath());
		newWkLesson.setRemarks(wkLesson.getRemarks());
		newWkLesson.setType(wkLesson.getType());
		newWkLesson.setDeep(wkLesson.getDeep());
		newWkLesson.setTestId(wkLesson.getTestId());
		newWkLesson.setWkChapter(wkChapter);
		newWkLesson.setCreateTime(Calendar.getInstance());
		newWkLesson.setVideoUrl(wkLesson.getVideoUrl());
		//保存课时
		newWkLesson = saveLesson(newWkLesson);
		return newWkLesson.getId();
	}
	
	/**************************************************************************
	 * Description:知识技能体验-ajax根据章节id和获取课时列表map
	 * 
	 * @author：裴继超
	 * @date ：2016年8月23日13:45:10
	 **************************************************************************/
	@Override
	public Map findLessonMapByChapterId(Integer chapterId){
		Map<Integer, String> map = new HashMap<Integer, String>();
		//获取章节对应课时
		String sql = "select c from WkLesson c where c.wkChapter.id = " + chapterId;
		List<WkLesson> lessons = wkLessonDAO.executeQuery(sql, 0,-1);
		//获取所有对应课时的id和题目
		for(WkLesson c:lessons){
			map.put(c.getId(), c.getTitle());
		}
		return map;
	}
}