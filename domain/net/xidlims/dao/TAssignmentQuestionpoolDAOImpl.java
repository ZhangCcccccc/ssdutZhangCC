package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.TAssignmentQuestionpool;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage TAssignmentQuestionpool entities.
 * 
 */
@Repository("TAssignmentQuestionpoolDAO")
@Transactional
public class TAssignmentQuestionpoolDAOImpl extends AbstractJpaDao<TAssignmentQuestionpool>
		implements TAssignmentQuestionpoolDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { TAssignmentQuestionpool.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new TAssignmentQuestionpoolDAOImpl
	 *
	 */
	public TAssignmentQuestionpoolDAOImpl() {
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
	 * JPQL Query - findTAssignmentQuestionpoolByQuestionpoolId
	 *
	 */
	@Transactional
	public TAssignmentQuestionpool findTAssignmentQuestionpoolByQuestionpoolId(Integer questionpoolId) throws DataAccessException {

		return findTAssignmentQuestionpoolByQuestionpoolId(questionpoolId, -1, -1);
	}

	/**
	 * JPQL Query - findTAssignmentQuestionpoolByQuestionpoolId
	 *
	 */

	@Transactional
	public TAssignmentQuestionpool findTAssignmentQuestionpoolByQuestionpoolId(Integer questionpoolId, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findTAssignmentQuestionpoolByQuestionpoolId", startResult, maxRows, questionpoolId);
			return (net.xidlims.domain.TAssignmentQuestionpool) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findTAssignmentQuestionpoolByCreatedTime
	 *
	 */
	@Transactional
	public Set<TAssignmentQuestionpool> findTAssignmentQuestionpoolByCreatedTime(java.util.Calendar createdTime) throws DataAccessException {

		return findTAssignmentQuestionpoolByCreatedTime(createdTime, -1, -1);
	}

	/**
	 * JPQL Query - findTAssignmentQuestionpoolByCreatedTime
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TAssignmentQuestionpool> findTAssignmentQuestionpoolByCreatedTime(java.util.Calendar createdTime, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTAssignmentQuestionpoolByCreatedTime", startResult, maxRows, createdTime);
		return new LinkedHashSet<TAssignmentQuestionpool>(query.getResultList());
	}

	/**
	 * JPQL Query - findTAssignmentQuestionpoolByPrimaryKey
	 *
	 */
	@Transactional
	public TAssignmentQuestionpool findTAssignmentQuestionpoolByPrimaryKey(Integer questionpoolId) throws DataAccessException {

		return findTAssignmentQuestionpoolByPrimaryKey(questionpoolId, -1, -1);
	}

	/**
	 * JPQL Query - findTAssignmentQuestionpoolByPrimaryKey
	 *
	 */

	@Transactional
	public TAssignmentQuestionpool findTAssignmentQuestionpoolByPrimaryKey(Integer questionpoolId, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findTAssignmentQuestionpoolByPrimaryKey", startResult, maxRows, questionpoolId);
			return (net.xidlims.domain.TAssignmentQuestionpool) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findTAssignmentQuestionpoolByDescriptionContaining
	 *
	 */
	@Transactional
	public Set<TAssignmentQuestionpool> findTAssignmentQuestionpoolByDescriptionContaining(String description) throws DataAccessException {

		return findTAssignmentQuestionpoolByDescriptionContaining(description, -1, -1);
	}

	/**
	 * JPQL Query - findTAssignmentQuestionpoolByDescriptionContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TAssignmentQuestionpool> findTAssignmentQuestionpoolByDescriptionContaining(String description, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTAssignmentQuestionpoolByDescriptionContaining", startResult, maxRows, description);
		return new LinkedHashSet<TAssignmentQuestionpool>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllTAssignmentQuestionpools
	 *
	 */
	@Transactional
	public Set<TAssignmentQuestionpool> findAllTAssignmentQuestionpools() throws DataAccessException {

		return findAllTAssignmentQuestionpools(-1, -1);
	}

	/**
	 * JPQL Query - findAllTAssignmentQuestionpools
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TAssignmentQuestionpool> findAllTAssignmentQuestionpools(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllTAssignmentQuestionpools", startResult, maxRows);
		return new LinkedHashSet<TAssignmentQuestionpool>(query.getResultList());
	}

	/**
	 * JPQL Query - findTAssignmentQuestionpoolByTitle
	 *
	 */
	@Transactional
	public Set<TAssignmentQuestionpool> findTAssignmentQuestionpoolByTitle(String title) throws DataAccessException {

		return findTAssignmentQuestionpoolByTitle(title, -1, -1);
	}

	/**
	 * JPQL Query - findTAssignmentQuestionpoolByTitle
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TAssignmentQuestionpool> findTAssignmentQuestionpoolByTitle(String title, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTAssignmentQuestionpoolByTitle", startResult, maxRows, title);
		return new LinkedHashSet<TAssignmentQuestionpool>(query.getResultList());
	}

	/**
	 * JPQL Query - findTAssignmentQuestionpoolByDescription
	 *
	 */
	@Transactional
	public Set<TAssignmentQuestionpool> findTAssignmentQuestionpoolByDescription(String description) throws DataAccessException {

		return findTAssignmentQuestionpoolByDescription(description, -1, -1);
	}

	/**
	 * JPQL Query - findTAssignmentQuestionpoolByDescription
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TAssignmentQuestionpool> findTAssignmentQuestionpoolByDescription(String description, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTAssignmentQuestionpoolByDescription", startResult, maxRows, description);
		return new LinkedHashSet<TAssignmentQuestionpool>(query.getResultList());
	}

	/**
	 * JPQL Query - findTAssignmentQuestionpoolByTitleContaining
	 *
	 */
	@Transactional
	public Set<TAssignmentQuestionpool> findTAssignmentQuestionpoolByTitleContaining(String title) throws DataAccessException {

		return findTAssignmentQuestionpoolByTitleContaining(title, -1, -1);
	}

	/**
	 * JPQL Query - findTAssignmentQuestionpoolByTitleContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TAssignmentQuestionpool> findTAssignmentQuestionpoolByTitleContaining(String title, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTAssignmentQuestionpoolByTitleContaining", startResult, maxRows, title);
		return new LinkedHashSet<TAssignmentQuestionpool>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(TAssignmentQuestionpool entity) {
		return true;
	}
}
