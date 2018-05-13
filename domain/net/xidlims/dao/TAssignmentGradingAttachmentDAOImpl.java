package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.TAssignmentGradingAttachment;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage TAssignmentGradingAttachment entities.
 * 
 */
@Repository("TAssignmentGradingAttachmentDAO")
@Transactional
public class TAssignmentGradingAttachmentDAOImpl extends AbstractJpaDao<TAssignmentGradingAttachment>
		implements TAssignmentGradingAttachmentDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { TAssignmentGradingAttachment.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new TAssignmentGradingAttachmentDAOImpl
	 *
	 */
	public TAssignmentGradingAttachmentDAOImpl() {
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
	 * JPQL Query - findTAssignmentGradingAttachmentByTAssignmentGradingAttachmentId
	 *
	 */
	@Transactional
	public TAssignmentGradingAttachment findTAssignmentGradingAttachmentByTAssignmentGradingAttachmentId(Integer TAssignmentGradingAttachmentId) throws DataAccessException {

		return findTAssignmentGradingAttachmentByTAssignmentGradingAttachmentId(TAssignmentGradingAttachmentId, -1, -1);
	}

	/**
	 * JPQL Query - findTAssignmentGradingAttachmentByTAssignmentGradingAttachmentId
	 *
	 */

	@Transactional
	public TAssignmentGradingAttachment findTAssignmentGradingAttachmentByTAssignmentGradingAttachmentId(Integer TAssignmentGradingAttachmentId, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findTAssignmentGradingAttachmentByTAssignmentGradingAttachmentId", startResult, maxRows, TAssignmentGradingAttachmentId);
			return (net.xidlims.domain.TAssignmentGradingAttachment) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findTAssignmentGradingAttachmentByGradeUrlContaining
	 *
	 */
	@Transactional
	public Set<TAssignmentGradingAttachment> findTAssignmentGradingAttachmentByGradeUrlContaining(String gradeUrl) throws DataAccessException {

		return findTAssignmentGradingAttachmentByGradeUrlContaining(gradeUrl, -1, -1);
	}

	/**
	 * JPQL Query - findTAssignmentGradingAttachmentByGradeUrlContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TAssignmentGradingAttachment> findTAssignmentGradingAttachmentByGradeUrlContaining(String gradeUrl, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTAssignmentGradingAttachmentByGradeUrlContaining", startResult, maxRows, gradeUrl);
		return new LinkedHashSet<TAssignmentGradingAttachment>(query.getResultList());
	}

	/**
	 * JPQL Query - findTAssignmentGradingAttachmentByTAssignmentGradingId
	 *
	 */
	@Transactional
	public Set<TAssignmentGradingAttachment> findTAssignmentGradingAttachmentByTAssignmentGradingId(Integer TAssignmentGradingId) throws DataAccessException {

		return findTAssignmentGradingAttachmentByTAssignmentGradingId(TAssignmentGradingId, -1, -1);
	}

	/**
	 * JPQL Query - findTAssignmentGradingAttachmentByTAssignmentGradingId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TAssignmentGradingAttachment> findTAssignmentGradingAttachmentByTAssignmentGradingId(Integer TAssignmentGradingId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTAssignmentGradingAttachmentByTAssignmentGradingId", startResult, maxRows, TAssignmentGradingId);
		return new LinkedHashSet<TAssignmentGradingAttachment>(query.getResultList());
	}

	/**
	 * JPQL Query - findTAssignmentGradingAttachmentByCreatedByContaining
	 *
	 */
	@Transactional
	public Set<TAssignmentGradingAttachment> findTAssignmentGradingAttachmentByCreatedByContaining(String createdBy) throws DataAccessException {

		return findTAssignmentGradingAttachmentByCreatedByContaining(createdBy, -1, -1);
	}

	/**
	 * JPQL Query - findTAssignmentGradingAttachmentByCreatedByContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TAssignmentGradingAttachment> findTAssignmentGradingAttachmentByCreatedByContaining(String createdBy, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTAssignmentGradingAttachmentByCreatedByContaining", startResult, maxRows, createdBy);
		return new LinkedHashSet<TAssignmentGradingAttachment>(query.getResultList());
	}

	/**
	 * JPQL Query - findTAssignmentGradingAttachmentByCreatedBy
	 *
	 */
	@Transactional
	public Set<TAssignmentGradingAttachment> findTAssignmentGradingAttachmentByCreatedBy(String createdBy) throws DataAccessException {

		return findTAssignmentGradingAttachmentByCreatedBy(createdBy, -1, -1);
	}

	/**
	 * JPQL Query - findTAssignmentGradingAttachmentByCreatedBy
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TAssignmentGradingAttachment> findTAssignmentGradingAttachmentByCreatedBy(String createdBy, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTAssignmentGradingAttachmentByCreatedBy", startResult, maxRows, createdBy);
		return new LinkedHashSet<TAssignmentGradingAttachment>(query.getResultList());
	}

	/**
	 * JPQL Query - findTAssignmentGradingAttachmentByGroupId
	 *
	 */
	@Transactional
	public Set<TAssignmentGradingAttachment> findTAssignmentGradingAttachmentByGroupId(Integer groupId) throws DataAccessException {

		return findTAssignmentGradingAttachmentByGroupId(groupId, -1, -1);
	}

	/**
	 * JPQL Query - findTAssignmentGradingAttachmentByGroupId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TAssignmentGradingAttachment> findTAssignmentGradingAttachmentByGroupId(Integer groupId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTAssignmentGradingAttachmentByGroupId", startResult, maxRows, groupId);
		return new LinkedHashSet<TAssignmentGradingAttachment>(query.getResultList());
	}

	/**
	 * JPQL Query - findTAssignmentGradingAttachmentByCreatedTime
	 *
	 */
	@Transactional
	public Set<TAssignmentGradingAttachment> findTAssignmentGradingAttachmentByCreatedTime(java.util.Calendar createdTime) throws DataAccessException {

		return findTAssignmentGradingAttachmentByCreatedTime(createdTime, -1, -1);
	}

	/**
	 * JPQL Query - findTAssignmentGradingAttachmentByCreatedTime
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TAssignmentGradingAttachment> findTAssignmentGradingAttachmentByCreatedTime(java.util.Calendar createdTime, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTAssignmentGradingAttachmentByCreatedTime", startResult, maxRows, createdTime);
		return new LinkedHashSet<TAssignmentGradingAttachment>(query.getResultList());
	}

	/**
	 * JPQL Query - findTAssignmentGradingAttachmentByPrimaryKey
	 *
	 */
	@Transactional
	public TAssignmentGradingAttachment findTAssignmentGradingAttachmentByPrimaryKey(Integer TAssignmentGradingAttachmentId) throws DataAccessException {

		return findTAssignmentGradingAttachmentByPrimaryKey(TAssignmentGradingAttachmentId, -1, -1);
	}

	/**
	 * JPQL Query - findTAssignmentGradingAttachmentByPrimaryKey
	 *
	 */

	@Transactional
	public TAssignmentGradingAttachment findTAssignmentGradingAttachmentByPrimaryKey(Integer TAssignmentGradingAttachmentId, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findTAssignmentGradingAttachmentByPrimaryKey", startResult, maxRows, TAssignmentGradingAttachmentId);
			return (net.xidlims.domain.TAssignmentGradingAttachment) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findTAssignmentGradingAttachmentByGradeUrl
	 *
	 */
	@Transactional
	public Set<TAssignmentGradingAttachment> findTAssignmentGradingAttachmentByGradeUrl(String gradeUrl) throws DataAccessException {

		return findTAssignmentGradingAttachmentByGradeUrl(gradeUrl, -1, -1);
	}

	/**
	 * JPQL Query - findTAssignmentGradingAttachmentByGradeUrl
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TAssignmentGradingAttachment> findTAssignmentGradingAttachmentByGradeUrl(String gradeUrl, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTAssignmentGradingAttachmentByGradeUrl", startResult, maxRows, gradeUrl);
		return new LinkedHashSet<TAssignmentGradingAttachment>(query.getResultList());
	}

	/**
	 * JPQL Query - findTAssignmentGradingAttachmentByType
	 *
	 */
	@Transactional
	public Set<TAssignmentGradingAttachment> findTAssignmentGradingAttachmentByType(Integer type) throws DataAccessException {

		return findTAssignmentGradingAttachmentByType(type, -1, -1);
	}

	/**
	 * JPQL Query - findTAssignmentGradingAttachmentByType
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TAssignmentGradingAttachment> findTAssignmentGradingAttachmentByType(Integer type, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTAssignmentGradingAttachmentByType", startResult, maxRows, type);
		return new LinkedHashSet<TAssignmentGradingAttachment>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllTAssignmentGradingAttachments
	 *
	 */
	@Transactional
	public Set<TAssignmentGradingAttachment> findAllTAssignmentGradingAttachments() throws DataAccessException {

		return findAllTAssignmentGradingAttachments(-1, -1);
	}

	/**
	 * JPQL Query - findAllTAssignmentGradingAttachments
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TAssignmentGradingAttachment> findAllTAssignmentGradingAttachments(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllTAssignmentGradingAttachments", startResult, maxRows);
		return new LinkedHashSet<TAssignmentGradingAttachment>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(TAssignmentGradingAttachment entity) {
		return true;
	}
}
