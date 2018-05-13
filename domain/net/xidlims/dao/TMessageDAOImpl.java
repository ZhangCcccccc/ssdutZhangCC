package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.TMessage;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage TMessage entities.
 * 
 */
@Repository("TMessageDAO")
@Transactional
public class TMessageDAOImpl extends AbstractJpaDao<TMessage> implements
		TMessageDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { TMessage.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new TMessageDAOImpl
	 *
	 */
	public TMessageDAOImpl() {
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
	 * JPQL Query - findTMessageBySummary
	 *
	 */
	@Transactional
	public Set<TMessage> findTMessageBySummary(String summary) throws DataAccessException {

		return findTMessageBySummary(summary, -1, -1);
	}

	/**
	 * JPQL Query - findTMessageBySummary
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TMessage> findTMessageBySummary(String summary, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTMessageBySummary", startResult, maxRows, summary);
		return new LinkedHashSet<TMessage>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllTMessages
	 *
	 */
	@Transactional
	public Set<TMessage> findAllTMessages() throws DataAccessException {

		return findAllTMessages(-1, -1);
	}

	/**
	 * JPQL Query - findAllTMessages
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TMessage> findAllTMessages(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllTMessages", startResult, maxRows);
		return new LinkedHashSet<TMessage>(query.getResultList());
	}

	/**
	 * JPQL Query - findTMessageBySummaryContaining
	 *
	 */
	@Transactional
	public Set<TMessage> findTMessageBySummaryContaining(String summary) throws DataAccessException {

		return findTMessageBySummaryContaining(summary, -1, -1);
	}

	/**
	 * JPQL Query - findTMessageBySummaryContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TMessage> findTMessageBySummaryContaining(String summary, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTMessageBySummaryContaining", startResult, maxRows, summary);
		return new LinkedHashSet<TMessage>(query.getResultList());
	}

	/**
	 * JPQL Query - findTMessageByContent
	 *
	 */
	@Transactional
	public Set<TMessage> findTMessageByContent(String content) throws DataAccessException {

		return findTMessageByContent(content, -1, -1);
	}

	/**
	 * JPQL Query - findTMessageByContent
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TMessage> findTMessageByContent(String content, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTMessageByContent", startResult, maxRows, content);
		return new LinkedHashSet<TMessage>(query.getResultList());
	}

	/**
	 * JPQL Query - findTMessageByTitleContaining
	 *
	 */
	@Transactional
	public Set<TMessage> findTMessageByTitleContaining(String title) throws DataAccessException {

		return findTMessageByTitleContaining(title, -1, -1);
	}

	/**
	 * JPQL Query - findTMessageByTitleContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TMessage> findTMessageByTitleContaining(String title, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTMessageByTitleContaining", startResult, maxRows, title);
		return new LinkedHashSet<TMessage>(query.getResultList());
	}

	/**
	 * JPQL Query - findTMessageByReleaseTime
	 *
	 */
	@Transactional
	public Set<TMessage> findTMessageByReleaseTime(java.util.Calendar releaseTime) throws DataAccessException {

		return findTMessageByReleaseTime(releaseTime, -1, -1);
	}

	/**
	 * JPQL Query - findTMessageByReleaseTime
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TMessage> findTMessageByReleaseTime(java.util.Calendar releaseTime, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTMessageByReleaseTime", startResult, maxRows, releaseTime);
		return new LinkedHashSet<TMessage>(query.getResultList());
	}

	/**
	 * JPQL Query - findTMessageByPrimaryKey
	 *
	 */
	@Transactional
	public TMessage findTMessageByPrimaryKey(Integer id) throws DataAccessException {

		return findTMessageByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findTMessageByPrimaryKey
	 *
	 */

	@Transactional
	public TMessage findTMessageByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findTMessageByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.TMessage) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findTMessageById
	 *
	 */
	@Transactional
	public TMessage findTMessageById(Integer id) throws DataAccessException {

		return findTMessageById(id, -1, -1);
	}

	/**
	 * JPQL Query - findTMessageById
	 *
	 */

	@Transactional
	public TMessage findTMessageById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findTMessageById", startResult, maxRows, id);
			return (net.xidlims.domain.TMessage) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findTMessageByPublish
	 *
	 */
	@Transactional
	public Set<TMessage> findTMessageByPublish(Integer publish) throws DataAccessException {

		return findTMessageByPublish(publish, -1, -1);
	}

	/**
	 * JPQL Query - findTMessageByPublish
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TMessage> findTMessageByPublish(Integer publish, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTMessageByPublish", startResult, maxRows, publish);
		return new LinkedHashSet<TMessage>(query.getResultList());
	}

	/**
	 * JPQL Query - findTMessageByTitle
	 *
	 */
	@Transactional
	public Set<TMessage> findTMessageByTitle(String title) throws DataAccessException {

		return findTMessageByTitle(title, -1, -1);
	}

	/**
	 * JPQL Query - findTMessageByTitle
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TMessage> findTMessageByTitle(String title, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTMessageByTitle", startResult, maxRows, title);
		return new LinkedHashSet<TMessage>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(TMessage entity) {
		return true;
	}
}
