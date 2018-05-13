package net.xidlims.service.tcoursesite;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.xidlims.domain.TCourseSite;
import net.xidlims.dao.WkChapterDAO;
import net.xidlims.domain.WkChapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("WkChapterService")
public class WkChapterServiceImpl implements  WkChapterService {
	@Autowired
	private WkChapterDAO wkChapterDAO;

	@Autowired
	private TCourseSiteService tCourseSiteService;
		
	/**************************************************************************
	 * Description:保存章节 
	 * 
	 * @author：于侃
	 * @date ：2016-09-13
	 **************************************************************************/
	@Override
	public WkChapter saveChapter(WkChapter chapter) {
		//保存章节 
		chapter = wkChapterDAO.store(chapter);
		return chapter;
	}

	/**************************************************************************
	 * Description:根据主键查询章节
	 * 
	 * @author：于侃
	 * @date ：2016-09-13
	 **************************************************************************/
	@Override
	public WkChapter findChapterByPrimaryKey(Integer chapterId) {
		//根据主键查询章节
		return wkChapterDAO.findWkChapterByPrimaryKey(chapterId);
	}
	/**************************************************************************
	 * Description:删除章节
	 * 
	 * @author：于侃
	 * @date ：2016-09-13
	 **************************************************************************/
	@Override
	public void deleteChapter(WkChapter chapter) {
		//删除章节
		wkChapterDAO.remove(chapter);
	}
	
	/**************************************************************************
	 * Description:获取排序值最大的章节
	 * 
	 * @author：裴继超
	 * @date ：2016年5月31日15:29:23
	 **************************************************************************/
	@Override
	public int queryMaxChapterSeqBySiteId(TCourseSite tCourseSite) {
		//获取排序值最大的章节
		String sql = "select max(c.seq) from WkChapter c where c.TCourseSite.id = '"+tCourseSite.getId()+"'";
		Integer result = (Integer)wkChapterDAO.createQuerySingleResult(sql).getSingleResult();
		return result==null?0:result;
	}
	
	/**************************************************************************
	 * Description:获取排序值最小的章节
	 * 
	 * @author：裴继超
	 * @date ：2016年5月31日15:29:23
	 **************************************************************************/
	@Override
	public int queryMinChapterSeqBySiteId(TCourseSite tCourseSite) {
		//获取排序值最小的章节
		String sql = "select min(c.seq) from WkChapter c where c.TCourseSite.id = '"+tCourseSite.getId()+"'";
		Integer result = (Integer)wkChapterDAO.createQuerySingleResult(sql).getSingleResult();
		return result;
	}
	
	/**************************************************************************
	 * Description:获取排序值比选中章节小的章节
	 * 
	 * @author：裴继超
	 * @date ：2016年5月31日15:29:23
	 **************************************************************************/
	@Override
	public int querysmallChapterSeqBySiteId(WkChapter wkChapter) {
		//获取排序值比选中章节小的章节
		String sql = "select max(c.seq) from WkChapter c where c.TCourseSite.id = '"+wkChapter.getTCourseSite().getId()+"' and c.seq < '"+wkChapter.getSeq()+"'";
		Integer result = (Integer)wkChapterDAO.createQuerySingleResult(sql).getSingleResult();
		return result;
	}
	
	/**************************************************************************
	 * Description:获取排序值比选中章节大的章节
	 * 
	 * @author：裴继超
	 * @date ：2016年5月31日15:29:23
	 **************************************************************************/
	@Override
	public int querybigChapterSeqBySiteId(WkChapter wkChapter) {
		//获取排序值比选中章节大的章节
		String sql = "select min(c.seq) from WkChapter c where c.TCourseSite.id = '"+wkChapter.getTCourseSite().getId()+"' and c.seq > '"+wkChapter.getSeq()+"'";
		int result = (Integer)wkChapterDAO.createQuerySingleResult(sql).getSingleResult();
		return result;
		
	}
	
	/**************************************************************************
	 * Description:复制章节资源
	 * 
	 * @author：裴继超
	 * @date ：2016年6月15日9:59:03
	 **************************************************************************/
	@Override
	public int copyWkChapter(Integer tCourseSiteId,Integer wkChapterId) {
		//通过章节id获取章节
		WkChapter wkChapter = findChapterByPrimaryKey(wkChapterId);
		//通过课程id获取课程
		TCourseSite tCourseSite = tCourseSiteService.findCourseSiteById(tCourseSiteId);
		//新建章节
		WkChapter newWkChapter = new WkChapter();
		newWkChapter.setName(wkChapter.getName());
		newWkChapter.setFileList(wkChapter.getFileList());
		newWkChapter.setSeq(wkChapter.getSeq());
		newWkChapter.setType(wkChapter.getType());
		newWkChapter.setTCourseSite(tCourseSite);
		//保存章节
		newWkChapter = saveChapter(newWkChapter);
		return newWkChapter.getId();
		
	}
	
	/**************************************************************************
	 * Description:知识技能体验-ajax根据type和课程id获取章节
	 * 
	 * @author：裴继超
	 * @date ：2016年8月19日16:47:20
	 **************************************************************************/
	@Override
	public Map findChapterMapByModuleTypeAndSiteId(Integer tCourseSiteId,Integer moduleType){
		Map<Integer, String> map = new HashMap<Integer, String>();
		//根据type和课程id获取章节
		String sql = "select c from WkChapter c where c.TCourseSite.id = " + tCourseSiteId + 
				" and c.type = " + moduleType ;
		List<WkChapter> chapters = wkChapterDAO.executeQuery(sql, 0,-1);
		//获取章节id和名称
		for(WkChapter c:chapters){
			map.put(c.getId(), c.getName());
		}
		return map;
	}
	
	
	
}