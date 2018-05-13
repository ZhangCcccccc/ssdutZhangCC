package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.TMessageAttachment;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage TMessageAttachment entities.
 * 
 */
@Repository("TMessageAttachmentDAO")
@Transactional
public class TMessageAttachmentDAOImpl extends AbstractJpaDao<TMessageAttachment>
		implements TMessageAttachmentDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { TMessageAttachment.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new TMessageAttachmentDAOImpl
	 *
	 */
	public TMessageAttachmentDAOImpl() {
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
	 * JPQL Query - findAllTMessageAttachments
	 *
	 */
	@Transactional
	public Set<TMessageAttachment> findAllTMessageAttachments() throws DataAccessException {

		return findAllTMessageAttachments(-1, -1);
	}

	/**
	 * JPQL Query - findAllTMessageAttachments
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TMessageAttachment> findAllTMessageAttachments(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllTMessageAttachments", startResult, maxRows);
		return new LinkedHashSet<TMessageAttachment>(query.getResultList());
	}

	/**
	 * JPQL Query - findTMessageAttachmentByPrimaryKey
	 *
	 */
	@Transactional
	public TMessageAttachment findTMessageAttachmentByPrimaryKey(Integer id) throws DataAccessException {

		return findTMessageAttachmentByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findTMessageAttachmentByPrimaryKey
	 *
	 */

	@Transactional
	public TMessageAttachment findTMessageAttachmentByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findTMessageAttachmentByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.TMessageAttachment) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findTMessageAttachmentByPath
	 *
	 */
	@Transactional
	public Set<TMessageAttachment> findTMessageAttachmentByPath(String path) throws DataAccessException {

		return findTMessageAttachmentByPath(path, -1, -1);
	}

	/**
	 * JPQL Query - findTMessageAttachmentByPath
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TMessageAttachment> findTMessageAttachmentByPath(String path, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTMessageAttachmentByPath", startResult, maxRows, path);
		return new LinkedHashSet<TMessageAttachment>(query.getResultList());
	}

	/**
	 * JPQL Query - findTMessageAttachmentByPathContaining
	 *
	 */
	@Transactional
	public Set<TMessageAttachment> findTMessageAttachmentByPathContaining(String path) throws DataAccessException {

		return findTMessageAttachmentByPathContaining(path, -1, -1);
	}

	/**
	 * JPQL Query - findTMessageAttachmentByPathContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TMessageAttachment> findTMessageAttachmentByPathContaining(String path, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTMessageAttachmentByPathContaining", startResult, maxRows, path);
		return new LinkedHashSet<TMessageAttachment>(query.getResultList());
	}

	/**
	 * JPQL Query - findTMessageAttachmentById
	 *
	 */
	@Transactional
	public TMessageAttachment findTMessageAttachmentById(Integer id) throws DataAccessException {

		return findTMessageAttachmentById(id, -1, -1);
	}

	/**
	 * JPQL Query - findTMessageAttachmentById
	 *
	 */

	@Transactional
	public TMessageAttachment findTMessageAttachmentById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findTMessageAttachmentById", startResult, maxRows, id);
			return (net.xidlims.domain.TMessageAttachment) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findTMessageAttachmentByMessageId
	 *
	 */
	@Transactional
	public Set<TMessageAttachment> findTMessageAttachmentByMessageId(Integer messageId) throws DataAccessException {

		return findTMessageAttachmentByMessageId(messageId, -1, -1);
	}

	/**
	 * JPQL Query - findTMessageAttachmentByMessageId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TMessageAttachment> findTMessageAttachmentByMessageId(Integer messageId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTMessageAttachmentByMessageId", startResult, maxRows, messageId);
		return new LinkedHashSet<TMessageAttachment>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(TMessageAttachment entity) {
		return true;
	}
}
