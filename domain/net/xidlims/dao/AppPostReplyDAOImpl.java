package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.AppPostReply;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage AppPostReply entities.
 * 
 */
@Repository("AppPostReplyDAO")
@Transactional
public class AppPostReplyDAOImpl extends AbstractJpaDao<AppPostReply> implements
		AppPostReplyDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { AppPostReply.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new AppPostReplyDAOImpl
	 *
	 */
	public AppPostReplyDAOImpl() {
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
	 * JPQL Query - findAppPostReplyByTime
	 *
	 */
	@Transactional
	public Set<AppPostReply> findAppPostReplyByTime(java.util.Calendar time) throws DataAccessException {

		return findAppPostReplyByTime(time, -1, -1);
	}

	/**
	 * JPQL Query - findAppPostReplyByTime
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AppPostReply> findAppPostReplyByTime(java.util.Calendar time, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAppPostReplyByTime", startResult, maxRows, time);
		return new LinkedHashSet<AppPostReply>(query.getResultList());
	}

	/**
	 * JPQL Query - findAppPostReplyByCommentContaining
	 *
	 */
	@Transactional
	public Set<AppPostReply> findAppPostReplyByCommentContaining(String comment) throws DataAccessException {

		return findAppPostReplyByCommentContaining(comment, -1, -1);
	}

	/**
	 * JPQL Query - findAppPostReplyByCommentContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AppPostReply> findAppPostReplyByCommentContaining(String comment, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAppPostReplyByCommentContaining", startResult, maxRows, comment);
		return new LinkedHashSet<AppPostReply>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllAppPostReplys
	 *
	 */
	@Transactional
	public Set<AppPostReply> findAllAppPostReplys() throws DataAccessException {

		return findAllAppPostReplys(-1, -1);
	}

	/**
	 * JPQL Query - findAllAppPostReplys
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AppPostReply> findAllAppPostReplys(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllAppPostReplys", startResult, maxRows);
		return new LinkedHashSet<AppPostReply>(query.getResultList());
	}

	/**
	 * JPQL Query - findAppPostReplyByCollection
	 *
	 */
	@Transactional
	public Set<AppPostReply> findAppPostReplyByCollection(Integer collection) throws DataAccessException {

		return findAppPostReplyByCollection(collection, -1, -1);
	}

	/**
	 * JPQL Query - findAppPostReplyByCollection
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AppPostReply> findAppPostReplyByCollection(Integer collection, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAppPostReplyByCollection", startResult, maxRows, collection);
		return new LinkedHashSet<AppPostReply>(query.getResultList());
	}

	/**
	 * JPQL Query - findAppPostReplyByPrimaryKey
	 *
	 */
	@Transactional
	public AppPostReply findAppPostReplyByPrimaryKey(Integer id) throws DataAccessException {

		return findAppPostReplyByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findAppPostReplyByPrimaryKey
	 *
	 */

	@Transactional
	public AppPostReply findAppPostReplyByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findAppPostReplyByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.AppPostReply) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findAppPostReplyById
	 *
	 */
	@Transactional
	public AppPostReply findAppPostReplyById(Integer id) throws DataAccessException {

		return findAppPostReplyById(id, -1, -1);
	}

	/**
	 * JPQL Query - findAppPostReplyById
	 *
	 */

	@Transactional
	public AppPostReply findAppPostReplyById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findAppPostReplyById", startResult, maxRows, id);
			return (net.xidlims.domain.AppPostReply) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findAppPostReplyByComment
	 *
	 */
	@Transactional
	public Set<AppPostReply> findAppPostReplyByComment(String comment) throws DataAccessException {

		return findAppPostReplyByComment(comment, -1, -1);
	}

	/**
	 * JPQL Query - findAppPostReplyByComment
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AppPostReply> findAppPostReplyByComment(String comment, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAppPostReplyByComment", startResult, maxRows, comment);
		return new LinkedHashSet<AppPostReply>(query.getResultList());
	}

	/**
	 * JPQL Query - findAppPostReplyByUpvote
	 *
	 */
	@Transactional
	public Set<AppPostReply> findAppPostReplyByUpvote(Integer upvote) throws DataAccessException {

		return findAppPostReplyByUpvote(upvote, -1, -1);
	}

	/**
	 * JPQL Query - findAppPostReplyByUpvote
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AppPostReply> findAppPostReplyByUpvote(Integer upvote, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAppPostReplyByUpvote", startResult, maxRows, upvote);
		return new LinkedHashSet<AppPostReply>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(AppPostReply entity) {
		return true;
	}
}
