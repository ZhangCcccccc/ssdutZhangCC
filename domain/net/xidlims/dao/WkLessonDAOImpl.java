package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.WkLesson;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage WkLesson entities.
 * 
 */
@Repository("WkLessonDAO")
@Transactional
public class WkLessonDAOImpl extends AbstractJpaDao<WkLesson> implements
		WkLessonDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { WkLesson.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new WkLessonDAOImpl
	 *
	 */
	public WkLessonDAOImpl() {
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
	 * JPQL Query - findWkLessonByVideoUrlContaining
	 *
	 */
	@Transactional
	public Set<WkLesson> findWkLessonByVideoUrlContaining(String videoUrl) throws DataAccessException {

		return findWkLessonByVideoUrlContaining(videoUrl, -1, -1);
	}

	/**
	 * JPQL Query - findWkLessonByVideoUrlContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<WkLesson> findWkLessonByVideoUrlContaining(String videoUrl, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findWkLessonByVideoUrlContaining", startResult, maxRows, videoUrl);
		return new LinkedHashSet<WkLesson>(query.getResultList());
	}

	/**
	 * JPQL Query - findWkLessonByTestId
	 *
	 */
	@Transactional
	public Set<WkLesson> findWkLessonByTestId(Integer testId) throws DataAccessException {

		return findWkLessonByTestId(testId, -1, -1);
	}

	/**
	 * JPQL Query - findWkLessonByTestId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<WkLesson> findWkLessonByTestId(Integer testId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findWkLessonByTestId", startResult, maxRows, testId);
		return new LinkedHashSet<WkLesson>(query.getResultList());
	}

	/**
	 * JPQL Query - findWkLessonByCreateTime
	 *
	 */
	@Transactional
	public Set<WkLesson> findWkLessonByCreateTime(java.util.Calendar createTime) throws DataAccessException {

		return findWkLessonByCreateTime(createTime, -1, -1);
	}

	/**
	 * JPQL Query - findWkLessonByCreateTime
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<WkLesson> findWkLessonByCreateTime(java.util.Calendar createTime, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findWkLessonByCreateTime", startResult, maxRows, createTime);
		return new LinkedHashSet<WkLesson>(query.getResultList());
	}

	/**
	 * JPQL Query - findWkLessonByTitleContaining
	 *
	 */
	@Transactional
	public Set<WkLesson> findWkLessonByTitleContaining(String title) throws DataAccessException {

		return findWkLessonByTitleContaining(title, -1, -1);
	}

	/**
	 * JPQL Query - findWkLessonByTitleContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<WkLesson> findWkLessonByTitleContaining(String title, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findWkLessonByTitleContaining", startResult, maxRows, title);
		return new LinkedHashSet<WkLesson>(query.getResultList());
	}

	/**
	 * JPQL Query - findWkLessonByVideoUrl
	 *
	 */
	@Transactional
	public Set<WkLesson> findWkLessonByVideoUrl(String videoUrl) throws DataAccessException {

		return findWkLessonByVideoUrl(videoUrl, -1, -1);
	}

	/**
	 * JPQL Query - findWkLessonByVideoUrl
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<WkLesson> findWkLessonByVideoUrl(String videoUrl, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findWkLessonByVideoUrl", startResult, maxRows, videoUrl);
		return new LinkedHashSet<WkLesson>(query.getResultList());
	}

	/**
	 * JPQL Query - findWkLessonByTypeContaining
	 *
	 */
	@Transactional
	public Set<WkLesson> findWkLessonByTypeContaining(String type) throws DataAccessException {

		return findWkLessonByTypeContaining(type, -1, -1);
	}

	/**
	 * JPQL Query - findWkLessonByTypeContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<WkLesson> findWkLessonByTypeContaining(String type, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findWkLessonByTypeContaining", startResult, maxRows, type);
		return new LinkedHashSet<WkLesson>(query.getResultList());
	}

	/**
	 * JPQL Query - findWkLessonById
	 *
	 */
	@Transactional
	public WkLesson findWkLessonById(Integer id) throws DataAccessException {

		return findWkLessonById(id, -1, -1);
	}

	/**
	 * JPQL Query - findWkLessonById
	 *
	 */

	@Transactional
	public WkLesson findWkLessonById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findWkLessonById", startResult, maxRows, id);
			return (net.xidlims.domain.WkLesson) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findWkLessonByFileId
	 *
	 */
	@Transactional
	public Set<WkLesson> findWkLessonByFileId(Integer fileId) throws DataAccessException {

		return findWkLessonByFileId(fileId, -1, -1);
	}

	/**
	 * JPQL Query - findWkLessonByFileId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<WkLesson> findWkLessonByFileId(Integer fileId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findWkLessonByFileId", startResult, maxRows, fileId);
		return new LinkedHashSet<WkLesson>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllWkLessons
	 *
	 */
	@Transactional
	public Set<WkLesson> findAllWkLessons() throws DataAccessException {

		return findAllWkLessons(-1, -1);
	}

	/**
	 * JPQL Query - findAllWkLessons
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<WkLesson> findAllWkLessons(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllWkLessons", startResult, maxRows);
		return new LinkedHashSet<WkLesson>(query.getResultList());
	}

	/**
	 * JPQL Query - findWkLessonByContent
	 *
	 */
	@Transactional
	public Set<WkLesson> findWkLessonByContent(String content) throws DataAccessException {

		return findWkLessonByContent(content, -1, -1);
	}

	/**
	 * JPQL Query - findWkLessonByContent
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<WkLesson> findWkLessonByContent(String content, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findWkLessonByContent", startResult, maxRows, content);
		return new LinkedHashSet<WkLesson>(query.getResultList());
	}

	/**
	 * JPQL Query - findWkLessonByType
	 *
	 */
	@Transactional
	public Set<WkLesson> findWkLessonByType(String type) throws DataAccessException {

		return findWkLessonByType(type, -1, -1);
	}

	/**
	 * JPQL Query - findWkLessonByType
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<WkLesson> findWkLessonByType(String type, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findWkLessonByType", startResult, maxRows, type);
		return new LinkedHashSet<WkLesson>(query.getResultList());
	}

	/**
	 * JPQL Query - findWkLessonBySeq
	 *
	 */
	@Transactional
	public Set<WkLesson> findWkLessonBySeq(Integer seq) throws DataAccessException {

		return findWkLessonBySeq(seq, -1, -1);
	}

	/**
	 * JPQL Query - findWkLessonBySeq
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<WkLesson> findWkLessonBySeq(Integer seq, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findWkLessonBySeq", startResult, maxRows, seq);
		return new LinkedHashSet<WkLesson>(query.getResultList());
	}

	/**
	 * JPQL Query - findWkLessonByViewedNum
	 *
	 */
	@Transactional
	public Set<WkLesson> findWkLessonByViewedNum(Integer viewedNum) throws DataAccessException {

		return findWkLessonByViewedNum(viewedNum, -1, -1);
	}

	/**
	 * JPQL Query - findWkLessonByViewedNum
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<WkLesson> findWkLessonByViewedNum(Integer viewedNum, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findWkLessonByViewedNum", startResult, maxRows, viewedNum);
		return new LinkedHashSet<WkLesson>(query.getResultList());
	}

	/**
	 * JPQL Query - findWkLessonByPrimaryKey
	 *
	 */
	@Transactional
	public WkLesson findWkLessonByPrimaryKey(Integer id) throws DataAccessException {

		return findWkLessonByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findWkLessonByPrimaryKey
	 *
	 */

	@Transactional
	public WkLesson findWkLessonByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findWkLessonByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.WkLesson) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findWkLessonBySourceList
	 *
	 */
	@Transactional
	public Set<WkLesson> findWkLessonBySourceList(String sourceList) throws DataAccessException {

		return findWkLessonBySourceList(sourceList, -1, -1);
	}

	/**
	 * JPQL Query - findWkLessonBySourceList
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<WkLesson> findWkLessonBySourceList(String sourceList, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findWkLessonBySourceList", startResult, maxRows, sourceList);
		return new LinkedHashSet<WkLesson>(query.getResultList());
	}

	/**
	 * JPQL Query - findWkLessonBySourceListContaining
	 *
	 */
	@Transactional
	public Set<WkLesson> findWkLessonBySourceListContaining(String sourceList) throws DataAccessException {

		return findWkLessonBySourceListContaining(sourceList, -1, -1);
	}

	/**
	 * JPQL Query - findWkLessonBySourceListContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<WkLesson> findWkLessonBySourceListContaining(String sourceList, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findWkLessonBySourceListContaining", startResult, maxRows, sourceList);
		return new LinkedHashSet<WkLesson>(query.getResultList());
	}

	/**
	 * JPQL Query - findWkLessonByDeep
	 *
	 */
	@Transactional
	public Set<WkLesson> findWkLessonByDeep(String deep) throws DataAccessException {

		return findWkLessonByDeep(deep, -1, -1);
	}

	/**
	 * JPQL Query - findWkLessonByDeep
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<WkLesson> findWkLessonByDeep(String deep, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findWkLessonByDeep", startResult, maxRows, deep);
		return new LinkedHashSet<WkLesson>(query.getResultList());
	}

	/**
	 * JPQL Query - findWkLessonByTitle
	 *
	 */
	@Transactional
	public Set<WkLesson> findWkLessonByTitle(String title) throws DataAccessException {

		return findWkLessonByTitle(title, -1, -1);
	}

	/**
	 * JPQL Query - findWkLessonByTitle
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<WkLesson> findWkLessonByTitle(String title, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findWkLessonByTitle", startResult, maxRows, title);
		return new LinkedHashSet<WkLesson>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(WkLesson entity) {
		return true;
	}
}
