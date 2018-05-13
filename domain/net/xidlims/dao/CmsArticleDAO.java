package net.xidlims.dao;

import net.xidlims.domain.CmsArticle;

import java.util.Calendar;
import java.util.Set;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage CmsArticle entities.
 * 
 */
public interface CmsArticleDAO extends JpaDao<CmsArticle> {

	/**
	 * JPQL Query - findAllCmsArticles
	 *
	 */
	public Set<CmsArticle> findAllCmsArticles() throws DataAccessException;

	/**
	 * JPQL Query - findAllCmsArticles
	 *
	 */
	public Set<CmsArticle> findAllCmsArticles(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCmsArticleByState
	 *
	 */
	public Set<CmsArticle> findCmsArticleByState(Integer state) throws DataAccessException;

	/**
	 * JPQL Query - findCmsArticleByState
	 *
	 */
	public Set<CmsArticle> findCmsArticleByState(Integer state, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCmsArticleByReadNum
	 *
	 */
	public Set<CmsArticle> findCmsArticleByReadNum(Integer readNum) throws DataAccessException;

	/**
	 * JPQL Query - findCmsArticleByReadNum
	 *
	 */
	public Set<CmsArticle> findCmsArticleByReadNum(Integer readNum, int startResult, int maxRows) throws DataAccessException;
	/**
	 * JPQL Query - findAllArticlesCreatetimeDesc
	 *
	 */
	public Set<CmsArticle> findAllCmsArticlesCreatetimeDesc() throws DataAccessException;
	
	/**
	 * JPQL Query - findAllArticlesCreatetimeDesc
	 *
	 */
	public Set<CmsArticle> findAllCmsArticlesCreatetimeDesc(int startResult, int maxRows) throws DataAccessException;
	/**
	 * JPQL Query - findCmsArticleByCreateTime
	 *
	 */
	public Set<CmsArticle> findCmsArticleByCreateTime(java.util.Calendar createTime) throws DataAccessException;

	/**
	 * JPQL Query - findCmsArticleByCreateTime
	 *
	 */
	public Set<CmsArticle> findCmsArticleByCreateTime(Calendar createTime, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCmsArticleByProfileContaining
	 *
	 */
	public Set<CmsArticle> findCmsArticleByProfileContaining(String profile) throws DataAccessException;

	/**
	 * JPQL Query - findCmsArticleByProfileContaining
	 *
	 */
	public Set<CmsArticle> findCmsArticleByProfileContaining(String profile, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCmsArticleByTitleContaining
	 *
	 */
	public Set<CmsArticle> findCmsArticleByTitleContaining(String title) throws DataAccessException;

	/**
	 * JPQL Query - findCmsArticleByTitleContaining
	 *
	 */
	public Set<CmsArticle> findCmsArticleByTitleContaining(String title, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCmsArticleByTitle
	 *
	 */
	public Set<CmsArticle> findCmsArticleByTitle(String title_1) throws DataAccessException;

	/**
	 * JPQL Query - findCmsArticleByTitle
	 *
	 */
	public Set<CmsArticle> findCmsArticleByTitle(String title_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCmsArticleById
	 *
	 */
	public CmsArticle findCmsArticleById(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findCmsArticleById
	 *
	 */
	public CmsArticle findCmsArticleById(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCmsArticleByPrimaryKey
	 *
	 */
	public CmsArticle findCmsArticleByPrimaryKey(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findCmsArticleByPrimaryKey
	 *
	 */
	public CmsArticle findCmsArticleByPrimaryKey(Integer id_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCmsArticleByNews
	 *
	 */
	public Set<CmsArticle> findCmsArticleByNews(String news) throws DataAccessException;

	/**
	 * JPQL Query - findCmsArticleByNews
	 *
	 */
	public Set<CmsArticle> findCmsArticleByNews(String news, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCmsArticleBySort
	 *
	 */
	public Set<CmsArticle> findCmsArticleBySort(Integer sort) throws DataAccessException;

	/**
	 * JPQL Query - findCmsArticleBySort
	 *
	 */
	public Set<CmsArticle> findCmsArticleBySort(Integer sort, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCmsArticleByProfile
	 *
	 */
	public Set<CmsArticle> findCmsArticleByProfile(String profile_1) throws DataAccessException;

	/**
	 * JPQL Query - findCmsArticleByProfile
	 *
	 */
	public Set<CmsArticle> findCmsArticleByProfile(String profile_1, int startResult, int maxRows) throws DataAccessException;

}