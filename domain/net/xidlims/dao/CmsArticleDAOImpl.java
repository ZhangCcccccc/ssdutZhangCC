package net.xidlims.dao;

import net.xidlims.domain.CmsArticle;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage CmsArticle entities.
 * 
 */
@Repository("CmsArticleDAO")
@Transactional
public class CmsArticleDAOImpl extends AbstractJpaDao<CmsArticle> implements
		CmsArticleDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { CmsArticle.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlims
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new CmsArticleDAOImpl
	 *
	 */
	public CmsArticleDAOImpl() {
		super();
	}

	/**
	 * Get the entity manager that manages persistence unit 
	 *
	 */
	public EntityManager getEntityManager() {
		return entityManager;
	}

	/**
	 * Returns the set of entity classes managed by this DAO.
	 *
	 */
	public Set<Class<?>> getTypes() {
		return dataTypes;
	}

	/**
	 * JPQL Query - findAllCmsArticles
	 *
	 */
	@Transactional
	public Set<CmsArticle> findAllCmsArticles() throws DataAccessException {

		return findAllCmsArticles(-1, -1);
	}

	/**
	 * JPQL Query - findAllCmsArticles
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CmsArticle> findAllCmsArticles(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllCmsArticles", startResult, maxRows);
		return new LinkedHashSet<CmsArticle>(query.getResultList());
	}

	/**
	 * JPQL Query - findCmsArticleByState
	 *
	 */
	@Transactional
	public Set<CmsArticle> findCmsArticleByState(Integer state) throws DataAccessException {

		return findCmsArticleByState(state, -1, -1);
	}

	/**
	 * JPQL Query - findCmsArticleByState
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CmsArticle> findCmsArticleByState(Integer state, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCmsArticleByState", startResult, maxRows, state);
		return new LinkedHashSet<CmsArticle>(query.getResultList());
	}

	/**
	 * JPQL Query - findCmsArticleByReadNum
	 *
	 */
	@Transactional
	public Set<CmsArticle> findCmsArticleByReadNum(Integer readNum) throws DataAccessException {

		return findCmsArticleByReadNum(readNum, -1, -1);
	}

	/**
	 * JPQL Query - findCmsArticleByReadNum
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CmsArticle> findCmsArticleByReadNum(Integer readNum, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCmsArticleByReadNum", startResult, maxRows, readNum);
		return new LinkedHashSet<CmsArticle>(query.getResultList());
	}
	/**
	 * JPQL Query - findAllArticles
	 *
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CmsArticle> findAllCmsArticlesCreatetimeDesc() throws DataAccessException {
		Query query = createNamedQuery("findAllCmsArticlesCreatetimeDesc", -1, -1);
		return new LinkedHashSet<CmsArticle>(query.getResultList());
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CmsArticle> findAllCmsArticlesCreatetimeDesc(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllCmsArticlesCreatetimeDesc", startResult, maxRows);
		return new LinkedHashSet<CmsArticle>(query.getResultList());
	}

	/**
	 * JPQL Query - findCmsArticleByCreateTime
	 *
	 */
	@Transactional
	public Set<CmsArticle> findCmsArticleByCreateTime(java.util.Calendar createTime) throws DataAccessException {

		return findCmsArticleByCreateTime(createTime, -1, -1);
	}

	/**
	 * JPQL Query - findCmsArticleByCreateTime
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CmsArticle> findCmsArticleByCreateTime(java.util.Calendar createTime, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCmsArticleByCreateTime", startResult, maxRows, createTime);
		return new LinkedHashSet<CmsArticle>(query.getResultList());
	}

	/**
	 * JPQL Query - findCmsArticleByProfileContaining
	 *
	 */
	@Transactional
	public Set<CmsArticle> findCmsArticleByProfileContaining(String profile) throws DataAccessException {

		return findCmsArticleByProfileContaining(profile, -1, -1);
	}

	/**
	 * JPQL Query - findCmsArticleByProfileContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CmsArticle> findCmsArticleByProfileContaining(String profile, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCmsArticleByProfileContaining", startResult, maxRows, profile);
		return new LinkedHashSet<CmsArticle>(query.getResultList());
	}

	/**
	 * JPQL Query - findCmsArticleByTitleContaining
	 *
	 */
	@Transactional
	public Set<CmsArticle> findCmsArticleByTitleContaining(String title) throws DataAccessException {

		return findCmsArticleByTitleContaining(title, -1, -1);
	}

	/**
	 * JPQL Query - findCmsArticleByTitleContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CmsArticle> findCmsArticleByTitleContaining(String title, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCmsArticleByTitleContaining", startResult, maxRows, title);
		return new LinkedHashSet<CmsArticle>(query.getResultList());
	}

	/**
	 * JPQL Query - findCmsArticleByTitle
	 *
	 */
	@Transactional
	public Set<CmsArticle> findCmsArticleByTitle(String title) throws DataAccessException {

		return findCmsArticleByTitle(title, -1, -1);
	}

	/**
	 * JPQL Query - findCmsArticleByTitle
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CmsArticle> findCmsArticleByTitle(String title, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCmsArticleByTitle", startResult, maxRows, title);
		return new LinkedHashSet<CmsArticle>(query.getResultList());
	}

	/**
	 * JPQL Query - findCmsArticleById
	 *
	 */
	@Transactional
	public CmsArticle findCmsArticleById(Integer id) throws DataAccessException {

		return findCmsArticleById(id, -1, -1);
	}

	/**
	 * JPQL Query - findCmsArticleById
	 *
	 */

	@Transactional
	public CmsArticle findCmsArticleById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findCmsArticleById", startResult, maxRows, id);
			return (net.xidlims.domain.CmsArticle) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findCmsArticleByPrimaryKey
	 *
	 */
	@Transactional
	public CmsArticle findCmsArticleByPrimaryKey(Integer id) throws DataAccessException {

		return findCmsArticleByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findCmsArticleByPrimaryKey
	 *
	 */

	@Transactional
	public CmsArticle findCmsArticleByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findCmsArticleByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.CmsArticle) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findCmsArticleByNews
	 *
	 */
	@Transactional
	public Set<CmsArticle> findCmsArticleByNews(String news) throws DataAccessException {

		return findCmsArticleByNews(news, -1, -1);
	}

	/**
	 * JPQL Query - findCmsArticleByNews
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CmsArticle> findCmsArticleByNews(String news, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCmsArticleByNews", startResult, maxRows, news);
		return new LinkedHashSet<CmsArticle>(query.getResultList());
	}

	/**
	 * JPQL Query - findCmsArticleBySort
	 *
	 */
	@Transactional
	public Set<CmsArticle> findCmsArticleBySort(Integer sort) throws DataAccessException {

		return findCmsArticleBySort(sort, -1, -1);
	}

	/**
	 * JPQL Query - findCmsArticleBySort
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CmsArticle> findCmsArticleBySort(Integer sort, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCmsArticleBySort", startResult, maxRows, sort);
		return new LinkedHashSet<CmsArticle>(query.getResultList());
	}

	/**
	 * JPQL Query - findCmsArticleByProfile
	 *
	 */
	@Transactional
	public Set<CmsArticle> findCmsArticleByProfile(String profile) throws DataAccessException {

		return findCmsArticleByProfile(profile, -1, -1);
	}

	/**
	 * JPQL Query - findCmsArticleByProfile
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CmsArticle> findCmsArticleByProfile(String profile, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCmsArticleByProfile", startResult, maxRows, profile);
		return new LinkedHashSet<CmsArticle>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(CmsArticle entity) {
		return true;
	}
}
