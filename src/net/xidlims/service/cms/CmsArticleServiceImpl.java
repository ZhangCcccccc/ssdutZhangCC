package net.xidlims.service.cms;

import net.xidlims.dao.CmsArticleDAO;
import net.xidlims.dao.CmsChannelDAO;
import net.xidlims.dao.CmsResourceDAO;
import net.xidlims.dao.CmsTagDAO;

import net.xidlims.domain.CmsArticle;
import net.xidlims.domain.CmsChannel;
import net.xidlims.domain.CmsResource;
import net.xidlims.domain.CmsTag;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.Query;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

/**
 * Spring service that handles CRUD requests for Article entities
 * 
 */

@Service("CmsArticleService")
@Transactional
public class CmsArticleServiceImpl implements CmsArticleService {

	/**
	 * DAO injected by Spring that manages Article entities
	 * 
	 */
	@Autowired
	private CmsArticleDAO articleDAO;

	/**
	 * DAO injected by Spring that manages Channel entities
	 * 
	 */
	@Autowired
	private CmsChannelDAO channelDAO;

	/**
	 * DAO injected by Spring that manages Resource entities
	 * 
	 */
	@Autowired
	private CmsResourceDAO resourceDAO;

	/**
	 * DAO injected by Spring that manages Tag entities
	 * 
	 */
	@Autowired
	private CmsTagDAO tagDAO;

	/**
	 * Instantiates a new ArticleServiceImpl.
	 *
	 */
	public CmsArticleServiceImpl() {
	}

	/**
	 * Save an existing Tag entity
	 * 
	 */
	@Transactional
	public CmsArticle saveArticleTags(Integer id, CmsTag related_tags) {
		CmsArticle article = articleDAO.findCmsArticleByPrimaryKey(id, -1, -1);
		CmsTag existingtags = tagDAO.findCmsTagByPrimaryKey(related_tags.getId());

		// copy into the existing record to preserve existing relationships
		if (existingtags != null) {
			existingtags.setId(related_tags.getId());
			existingtags.setName(related_tags.getName());
			existingtags.setDescription(related_tags.getDescription());
			existingtags.setCategory(related_tags.getCategory());
			related_tags = existingtags;
		} else {
			related_tags = tagDAO.store(related_tags);
			tagDAO.flush();
		}

		article.getCmsTags().add(related_tags);
		related_tags.getCmsArticles().add(article);
		article = articleDAO.store(article);
		articleDAO.flush();

		related_tags = tagDAO.store(related_tags);
		tagDAO.flush();

		return article;
	}

	/**
	 * Return a count of all Article entity
	 * 
	 */
	@Transactional
	public Integer countArticles() {
		return ((Long) articleDAO.createQuerySingleResult("select count(o) from CmsArticle o").getSingleResult()).intValue();
	}

	/**
	 * Delete an existing Tag entity
	 * 
	 */
	@Transactional
	public CmsArticle deleteArticleTags(Integer article_id, Integer related_tags_id) {
		CmsArticle article = articleDAO.findCmsArticleByPrimaryKey(article_id, -1, -1);
		CmsTag related_tags = tagDAO.findCmsTagByPrimaryKey(related_tags_id, -1, -1);

		article.getCmsTags().remove(related_tags);
		related_tags.getCmsArticles().remove(article);
		article = articleDAO.store(article);
		articleDAO.flush();

		related_tags = tagDAO.store(related_tags);
		tagDAO.flush();

		tagDAO.remove(related_tags);
		tagDAO.flush();

		return article;
	}

	/**
	 */
	@Transactional
	public CmsArticle findArticleByPrimaryKey(Integer id) {
		return articleDAO.findCmsArticleByPrimaryKey(id);
	}

	/**
	 * Save an existing Resource entity
	 * 
	 */
	@Transactional
	public CmsArticle saveArticleResource(Integer id, CmsResource related_resource) {
		CmsArticle article = articleDAO.findCmsArticleByPrimaryKey(id, -1, -1);
		CmsResource existingresource = resourceDAO.findCmsResourceByPrimaryKey(related_resource.getId());

		// copy into the existing record to preserve existing relationships
		if (existingresource != null) {
			existingresource.setId(related_resource.getId());
			existingresource.setName(related_resource.getName());
			existingresource.setUrl(related_resource.getUrl());
			existingresource.setProfile(related_resource.getProfile());
			existingresource.setCreateTime(related_resource.getCreateTime());
			related_resource = existingresource;
		} else {
			related_resource = resourceDAO.store(related_resource);
			resourceDAO.flush();
		}

		article.setCmsResource(related_resource);
		related_resource.getCmsArticles().add(article);
		article = articleDAO.store(article);
		articleDAO.flush();

		related_resource = resourceDAO.store(related_resource);
		resourceDAO.flush();

		return article;
	}

	/**
	 * Delete an existing Article entity
	 * 
	 */
	@Transactional
	public void deleteArticle(CmsArticle article) {
		articleDAO.remove(article);
		articleDAO.flush();
	}

	/**
	 * Load an existing Article entity
	 * 
	 */
	@Transactional
	public Set<CmsArticle> loadArticles() {
		return articleDAO.findAllCmsArticles();
		//return articleDAO.findAllArticlesCreatetimeDesc();
	}

	/**
	 * Return all Article entity
	 * 
	 */
	@Transactional
	public List<CmsArticle> findAllArticles(Integer startResult, Integer maxRows) {
		return new java.util.ArrayList<CmsArticle>(articleDAO.findAllCmsArticles(startResult, maxRows));
	}
	
	/**
	 * Return all Article entity
	 * 
	 */
	@Transactional
	public List<CmsArticle> findAllArticlesCreatetimeDesc(Integer startResult, Integer maxRows) {
		return new java.util.ArrayList<CmsArticle>(articleDAO.findAllCmsArticlesCreatetimeDesc(startResult, maxRows));
	}

	/**
	 * Save an existing Article entity
	 * 
	 */
/*	@Transactional
	public void saveArticle(Article article) {
		Article existingArticle = articleDAO.findArticleByPrimaryKey(article.getId());

		if (existingArticle != null) {
			if (existingArticle != article) {
				existingArticle.setId(article.getId());
				existingArticle.setTitle(article.getTitle());
				existingArticle.setNews(article.getNews());
				existingArticle.setProfile(article.getProfile());
				existingArticle.setState(article.getState());
				existingArticle.setSort(article.getSort());
				existingArticle.setReadNum(article.getReadNum());
				existingArticle.setCreateUser(article.getCreateUser());
				existingArticle.setCreateTime(article.getCreateTime());
			}
			article = articleDAO.store(existingArticle);
		} else {
			article = articleDAO.store(article);
		}
		articleDAO.flush();
	}*/
	@Transactional
	public void saveArticle(CmsArticle article) {
		CmsArticle existingArticle = articleDAO.findCmsArticleByPrimaryKey(article.getId());

		if (existingArticle != null) {
			if (existingArticle != article) {
				existingArticle.setId(article.getId());
				existingArticle.setTitle(article.getTitle());
				existingArticle.setNews(article.getNews());
				existingArticle.setProfile(article.getProfile());
				existingArticle.setState(article.getState());
				existingArticle.setSort(article.getSort());
				existingArticle.setReadNum(article.getReadNum());
				existingArticle.setCreateTime(article.getCreateTime());
			}
			article = articleDAO.store(existingArticle);
		} else {
			article = articleDAO.store(article);
		}
		articleDAO.flush();
	}

	/**
	 * Delete an existing Resource entity
	 * 
	 */
	@Transactional
	public CmsArticle deleteArticleResource(Integer article_id, Integer related_resource_id) {
		CmsArticle article = articleDAO.findCmsArticleByPrimaryKey(article_id, -1, -1);
		CmsResource related_resource = resourceDAO.findCmsResourceByPrimaryKey(related_resource_id, -1, -1);

		article.setCmsResource(null);
		related_resource.getCmsArticles().remove(article);
		article = articleDAO.store(article);
		articleDAO.flush();

		related_resource = resourceDAO.store(related_resource);
		resourceDAO.flush();

		resourceDAO.remove(related_resource);
		resourceDAO.flush();

		return article;
	}

	/**
	 * Delete an existing Channel entity
	 * 
	 */
	@Transactional
	public CmsArticle deleteArticleChannel(Integer article_id, Integer related_channel_id) {
		CmsArticle article = articleDAO.findCmsArticleByPrimaryKey(article_id, -1, -1);
		CmsChannel related_channel = channelDAO.findCmsChannelByPrimaryKey(related_channel_id, -1, -1);

		article.setCmsChannel(null);
		related_channel.getCmsArticles().remove(article);
		article = articleDAO.store(article);
		articleDAO.flush();

		related_channel = channelDAO.store(related_channel);
		channelDAO.flush();

		channelDAO.remove(related_channel);
		channelDAO.flush();

		return article;
	}

	/**
	 * Save an existing Channel entity
	 * 
	 */
	@Transactional
	public CmsArticle saveArticleChannel(Integer id, CmsChannel related_channel) {
		CmsArticle article = articleDAO.findCmsArticleByPrimaryKey(id, -1, -1);
		CmsChannel existingchannel = channelDAO.findCmsChannelByPrimaryKey(related_channel.getId());

		// copy into the existing record to preserve existing relationships
		if (existingchannel != null) {
			existingchannel.setId(related_channel.getId());
			existingchannel.setTitle(related_channel.getTitle());
			existingchannel.setSort(related_channel.getSort());
			existingchannel.setHyperlink(related_channel.getHyperlink());
			existingchannel.setProfile(related_channel.getProfile());
			existingchannel.setState(related_channel.getState());
			existingchannel.setReadNum(related_channel.getReadNum());
			existingchannel.setCreateTime(related_channel.getCreateTime());
			related_channel = existingchannel;
		}
		article.setCmsChannel(related_channel);
		related_channel.getCmsArticles().add(article);
		article = articleDAO.store(article);
		articleDAO.flush();
		related_channel = channelDAO.store(related_channel);
		channelDAO.flush();
		return article;
	}
	
	@SuppressWarnings("unchecked")
	public List<CmsArticle> findArticlesByChannelIdOrderBySortAndCreateTimeDesc(Integer channelId,Integer page,Integer max){
		List<CmsArticle>  articles = new ArrayList<CmsArticle>();
		String sql = "select a from CmsArticle a where a.cmsChannel.id = "+channelId+" order by a.sort ,a.createTime desc";
		Query q = articleDAO.createQuery(sql, (page-1)*max, max);
		articles = new ArrayList<CmsArticle>(q.getResultList());
		return articles;
	}
	

	public List<CmsArticle> findArticlesByChannelIdOrderBySortAndCreateTimeDesc(Integer channelId){
		List<CmsArticle>  articles = new ArrayList<CmsArticle>();
		String sql = "select a from CmsArticle a where a.cmsChannel.id = "+channelId+" order by a.sort ,a.createTime desc";
		articles = articleDAO.executeQuery(sql);
		return articles;
	}
	
	public Integer countChannelArticles(Integer channelId){
		String sql = "select count(a) from CmsArticle a where a.cmsChannel.id = "+channelId;
		return ((Long) articleDAO.createQuerySingleResult(sql).getSingleResult()).intValue();
	}
	//根据附件查找文章
	@Override
	public List<CmsArticle> findArticlesByDocumentId(Integer id){
		List<CmsArticle>  articles = new ArrayList<CmsArticle>();
		String sql = "select a from CmsArticle a where a.cmsDocument.id = "+id+" order by a.sort , a.createTime desc";
		articles = articleDAO.executeQuery(sql);
		return articles;
	}
}
