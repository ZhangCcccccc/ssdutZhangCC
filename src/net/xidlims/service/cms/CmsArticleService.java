package net.xidlims.service.cms;

import net.xidlims.domain.CmsArticle;
import net.xidlims.domain.CmsChannel;
import net.xidlims.domain.CmsResource;
import net.xidlims.domain.CmsTag;

import java.util.List;
import java.util.Set;

/**
 * Spring service that handles CRUD requests for CmsArticle entities
 * 
 */
public interface CmsArticleService {

	/**
	 * Save an existing Tag entity
	 * 
	 */
	public CmsArticle saveArticleTags(Integer id, CmsTag related_tags);

	/**
	 * Return a count of all CmsArticle entity
	 * 
	 */
	public Integer countArticles();

	/**
	 * Delete an existing Tag entity
	 * 
	 */
	public CmsArticle deleteArticleTags(Integer CmsArticle_id, Integer related_tags_id);

	/**
	 */
	public CmsArticle findArticleByPrimaryKey(Integer id_1);

	/**
	 * Save an existing Resource entity
	 * 
	 */
	public CmsArticle saveArticleResource(Integer id_2, CmsResource related_resource);

	/**
	 * Delete an existing CmsArticle entity
	 * 
	 */
	public void deleteArticle(CmsArticle CmsArticle);

	/**
	 * Load an existing CmsArticle entity
	 * 
	 */
	public Set<CmsArticle> loadArticles();

	/**
	 * Return all CmsArticle entity
	 * 
	 */
	public List<CmsArticle> findAllArticles(Integer startResult, Integer maxRows);

	/**
	 * Save an existing CmsArticle entity
	 * 
	 */
	public void saveArticle(CmsArticle CmsArticle_1);

	/**
	 * Delete an existing Resource entity
	 * 
	 */
	public CmsArticle deleteArticleResource(Integer CmsArticle_id_1, Integer related_resource_id);

	/**
	 * Delete an existing Channel entity
	 * 
	 */
	public CmsArticle deleteArticleChannel(Integer CmsArticle_id_2, Integer related_channel_id);

	/**
	 * Save an existing Channel entity
	 * 
	 */
	public CmsArticle saveArticleChannel(Integer id_3, CmsChannel related_channel);

	/**
	 * 按照sort和创建时间的顺序，查找每个栏目下面的新闻
	 * 
	 */
	public List<CmsArticle> findArticlesByChannelIdOrderBySortAndCreateTimeDesc(Integer channelId,Integer page,Integer max);
	
	/**
	 * 按照sort和创建时间的顺序，查找每个栏目下面的新闻
	 * 
	 */
	public List<CmsArticle> findArticlesByChannelIdOrderBySortAndCreateTimeDesc(Integer channelId);
	
	
	/**
	 * 查找每个栏目下面有多少条articles.
	 * 
	 */
	public Integer countChannelArticles(Integer channelId);
	//根据附件查找文章
	public List<CmsArticle> findArticlesByDocumentId(Integer id);
}