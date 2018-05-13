package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.TAssignment;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage TAssignment entities.
 * 
 */
@Repository("TAssignmentDAO")
@Transactional
public class TAssignmentDAOImpl extends AbstractJpaDao<TAssignment> implements
		TAssignmentDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { TAssignment.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new TAssignmentDAOImpl
	 *
	 */
	public TAssignmentDAOImpl() {
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
	 * JPQL Query - findTAssignmentByStatus
	 *
	 */
	@Transactional
	public Set<TAssignment> findTAssignmentByStatus(Integer status) throws DataAccessException {

		return findTAssignmentByStatus(status, -1, -1);
	}

	/**
	 * JPQL Query - findTAssignmentByStatus
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TAssignment> findTAssignmentByStatus(Integer status, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTAssignmentByStatus", startResult, maxRows, status);
		return new LinkedHashSet<TAssignment>(query.getResultList());
	}

	/**
	 * JPQL Query - findTAssignmentById
	 *
	 */
	@Transactional
	public TAssignment findTAssignmentById(Integer id) throws DataAccessException {

		return findTAssignmentById(id, -1, -1);
	}

	/**
	 * JPQL Query - findTAssignmentById
	 *
	 */

	@Transactional
	public TAssignment findTAssignmentById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findTAssignmentById", startResult, maxRows, id);
			return (net.xidlims.domain.TAssignment) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findTAssignmentByTitleContaining
	 *
	 */
	@Transactional
	public Set<TAssignment> findTAssignmentByTitleContaining(String title) throws DataAccessException {

		return findTAssignmentByTitleContaining(title, -1, -1);
	}

	/**
	 * JPQL Query - findTAssignmentByTitleContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TAssignment> findTAssignmentByTitleContaining(String title, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTAssignmentByTitleContaining", startResult, maxRows, title);
		return new LinkedHashSet<TAssignment>(query.getResultList());
	}

	/**
	 * JPQL Query - findTAssignmentByDescriptionContaining
	 *
	 */
	@Transactional
	public Set<TAssignment> findTAssignmentByDescriptionContaining(String description) throws DataAccessException {

		return findTAssignmentByDescriptionContaining(description, -1, -1);
	}

	/**
	 * JPQL Query - findTAssignmentByDescriptionContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TAssignment> findTAssignmentByDescriptionContaining(String description, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTAssignmentByDescriptionContaining", startResult, maxRows, description);
		return new LinkedHashSet<TAssignment>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllTAssignments
	 *
	 */
	@Transactional
	public Set<TAssignment> findAllTAssignments() throws DataAccessException {

		return findAllTAssignments(-1, -1);
	}

	/**
	 * JPQL Query - findAllTAssignments
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TAssignment> findAllTAssignments(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllTAssignments", startResult, maxRows);
		return new LinkedHashSet<TAssignment>(query.getResultList());
	}

	/**
	 * JPQL Query - findTAssignmentByTitle
	 *
	 */
	@Transactional
	public Set<TAssignment> findTAssignmentByTitle(String title) throws DataAccessException {

		return findTAssignmentByTitle(title, -1, -1);
	}

	/**
	 * JPQL Query - findTAssignmentByTitle
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TAssignment> findTAssignmentByTitle(String title, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTAssignmentByTitle", startResult, maxRows, title);
		return new LinkedHashSet<TAssignment>(query.getResultList());
	}

	/**
	 * JPQL Query - findTAssignmentByPrimaryKey
	 *
	 */
	@Transactional
	public TAssignment findTAssignmentByPrimaryKey(Integer id) throws DataAccessException {

		return findTAssignmentByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findTAssignmentByPrimaryKey
	 *
	 */

	@Transactional
	public TAssignment findTAssignmentByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findTAssignmentByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.TAssignment) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findTAssignmentByDescription
	 *
	 */
	@Transactional
	public Set<TAssignment> findTAssignmentByDescription(String description) throws DataAccessException {

		return findTAssignmentByDescription(description, -1, -1);
	}

	/**
	 * JPQL Query - findTAssignmentByDescription
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TAssignment> findTAssignmentByDescription(String description, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTAssignmentByDescription", startResult, maxRows, description);
		return new LinkedHashSet<TAssignment>(query.getResultList());
	}

	/**
	 * JPQL Query - findTAssignmentByCreatedTime
	 *
	 */
	@Transactional
	public Set<TAssignment> findTAssignmentByCreatedTime(java.util.Calendar createdTime) throws DataAccessException {

		return findTAssignmentByCreatedTime(createdTime, -1, -1);
	}

	/**
	 * JPQL Query - findTAssignmentByCreatedTime
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TAssignment> findTAssignmentByCreatedTime(java.util.Calendar createdTime, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTAssignmentByCreatedTime", startResult, maxRows, createdTime);
		return new LinkedHashSet<TAssignment>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(TAssignment entity) {
		return true;
	}
}
