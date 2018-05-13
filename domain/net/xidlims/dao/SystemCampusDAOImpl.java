package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.SystemCampus;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage SystemCampus entities.
 * 
 */
@Repository("SystemCampusDAO")
@Transactional
public class SystemCampusDAOImpl extends AbstractJpaDao<SystemCampus> implements
		SystemCampusDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { SystemCampus.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new SystemCampusDAOImpl
	 *
	 */
	public SystemCampusDAOImpl() {
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
	 * JPQL Query - findSystemCampusByCampusDefault
	 *
	 */
	@Transactional
	public Set<SystemCampus> findSystemCampusByCampusDefault(Boolean campusDefault) throws DataAccessException {

		return findSystemCampusByCampusDefault(campusDefault, -1, -1);
	}

	/**
	 * JPQL Query - findSystemCampusByCampusDefault
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SystemCampus> findSystemCampusByCampusDefault(Boolean campusDefault, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSystemCampusByCampusDefault", startResult, maxRows, campusDefault);
		return new LinkedHashSet<SystemCampus>(query.getResultList());
	}

	/**
	 * JPQL Query - findSystemCampusByUpdatedAtAfter
	 *
	 */
	@Transactional
	public Set<SystemCampus> findSystemCampusByUpdatedAtAfter(java.util.Calendar updatedAt) throws DataAccessException {

		return findSystemCampusByUpdatedAtAfter(updatedAt, -1, -1);
	}

	/**
	 * JPQL Query - findSystemCampusByUpdatedAtAfter
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SystemCampus> findSystemCampusByUpdatedAtAfter(java.util.Calendar updatedAt, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSystemCampusByUpdatedAtAfter", startResult, maxRows, updatedAt);
		return new LinkedHashSet<SystemCampus>(query.getResultList());
	}

	/**
	 * JPQL Query - findSystemCampusByCampusName
	 *
	 */
	@Transactional
	public Set<SystemCampus> findSystemCampusByCampusName(String campusName) throws DataAccessException {

		return findSystemCampusByCampusName(campusName, -1, -1);
	}

	/**
	 * JPQL Query - findSystemCampusByCampusName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SystemCampus> findSystemCampusByCampusName(String campusName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSystemCampusByCampusName", startResult, maxRows, campusName);
		return new LinkedHashSet<SystemCampus>(query.getResultList());
	}

	/**
	 * JPQL Query - findSystemCampusByCreatedAtBefore
	 *
	 */
	@Transactional
	public Set<SystemCampus> findSystemCampusByCreatedAtBefore(java.util.Calendar createdAt) throws DataAccessException {

		return findSystemCampusByCreatedAtBefore(createdAt, -1, -1);
	}

	/**
	 * JPQL Query - findSystemCampusByCreatedAtBefore
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SystemCampus> findSystemCampusByCreatedAtBefore(java.util.Calendar createdAt, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSystemCampusByCreatedAtBefore", startResult, maxRows, createdAt);
		return new LinkedHashSet<SystemCampus>(query.getResultList());
	}

	/**
	 * JPQL Query - findSystemCampusByCreatedAtAfter
	 *
	 */
	@Transactional
	public Set<SystemCampus> findSystemCampusByCreatedAtAfter(java.util.Calendar createdAt) throws DataAccessException {

		return findSystemCampusByCreatedAtAfter(createdAt, -1, -1);
	}

	/**
	 * JPQL Query - findSystemCampusByCreatedAtAfter
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SystemCampus> findSystemCampusByCreatedAtAfter(java.util.Calendar createdAt, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSystemCampusByCreatedAtAfter", startResult, maxRows, createdAt);
		return new LinkedHashSet<SystemCampus>(query.getResultList());
	}

	/**
	 * JPQL Query - findSystemCampusByPrimaryKey
	 *
	 */
	@Transactional
	public SystemCampus findSystemCampusByPrimaryKey(String campusNumber) throws DataAccessException {

		return findSystemCampusByPrimaryKey(campusNumber, -1, -1);
	}

	/**
	 * JPQL Query - findSystemCampusByPrimaryKey
	 *
	 */

	@Transactional
	public SystemCampus findSystemCampusByPrimaryKey(String campusNumber, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findSystemCampusByPrimaryKey", startResult, maxRows, campusNumber);
			return (net.xidlims.domain.SystemCampus) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findSystemCampusByCampusNumberContaining
	 *
	 */
	@Transactional
	public Set<SystemCampus> findSystemCampusByCampusNumberContaining(String campusNumber) throws DataAccessException {

		return findSystemCampusByCampusNumberContaining(campusNumber, -1, -1);
	}

	/**
	 * JPQL Query - findSystemCampusByCampusNumberContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SystemCampus> findSystemCampusByCampusNumberContaining(String campusNumber, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSystemCampusByCampusNumberContaining", startResult, maxRows, campusNumber);
		return new LinkedHashSet<SystemCampus>(query.getResultList());
	}

	/**
	 * JPQL Query - findSystemCampusByCreatedAt
	 *
	 */
	@Transactional
	public Set<SystemCampus> findSystemCampusByCreatedAt(java.util.Calendar createdAt) throws DataAccessException {

		return findSystemCampusByCreatedAt(createdAt, -1, -1);
	}

	/**
	 * JPQL Query - findSystemCampusByCreatedAt
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SystemCampus> findSystemCampusByCreatedAt(java.util.Calendar createdAt, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSystemCampusByCreatedAt", startResult, maxRows, createdAt);
		return new LinkedHashSet<SystemCampus>(query.getResultList());
	}

	/**
	 * JPQL Query - findSystemCampusByUpdatedAt
	 *
	 */
	@Transactional
	public Set<SystemCampus> findSystemCampusByUpdatedAt(java.util.Calendar updatedAt) throws DataAccessException {

		return findSystemCampusByUpdatedAt(updatedAt, -1, -1);
	}

	/**
	 * JPQL Query - findSystemCampusByUpdatedAt
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SystemCampus> findSystemCampusByUpdatedAt(java.util.Calendar updatedAt, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSystemCampusByUpdatedAt", startResult, maxRows, updatedAt);
		return new LinkedHashSet<SystemCampus>(query.getResultList());
	}

	/**
	 * JPQL Query - findSystemCampusByUpdatedAtBefore
	 *
	 */
	@Transactional
	public Set<SystemCampus> findSystemCampusByUpdatedAtBefore(java.util.Calendar updatedAt) throws DataAccessException {

		return findSystemCampusByUpdatedAtBefore(updatedAt, -1, -1);
	}

	/**
	 * JPQL Query - findSystemCampusByUpdatedAtBefore
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SystemCampus> findSystemCampusByUpdatedAtBefore(java.util.Calendar updatedAt, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSystemCampusByUpdatedAtBefore", startResult, maxRows, updatedAt);
		return new LinkedHashSet<SystemCampus>(query.getResultList());
	}

	/**
	 * JPQL Query - findSystemCampusByCampusNameContaining
	 *
	 */
	@Transactional
	public Set<SystemCampus> findSystemCampusByCampusNameContaining(String campusName) throws DataAccessException {

		return findSystemCampusByCampusNameContaining(campusName, -1, -1);
	}

	/**
	 * JPQL Query - findSystemCampusByCampusNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SystemCampus> findSystemCampusByCampusNameContaining(String campusName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSystemCampusByCampusNameContaining", startResult, maxRows, campusName);
		return new LinkedHashSet<SystemCampus>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllSystemCampuss
	 *
	 */
	@Transactional
	public Set<SystemCampus> findAllSystemCampuss() throws DataAccessException {

		return findAllSystemCampuss(-1, -1);
	}

	/**
	 * JPQL Query - findAllSystemCampuss
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SystemCampus> findAllSystemCampuss(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllSystemCampuss", startResult, maxRows);
		return new LinkedHashSet<SystemCampus>(query.getResultList());
	}

	/**
	 * JPQL Query - findSystemCampusByCampusNumber
	 *
	 */
	@Transactional
	public SystemCampus findSystemCampusByCampusNumber(String campusNumber) throws DataAccessException {

		return findSystemCampusByCampusNumber(campusNumber, -1, -1);
	}

	/**
	 * JPQL Query - findSystemCampusByCampusNumber
	 *
	 */

	@Transactional
	public SystemCampus findSystemCampusByCampusNumber(String campusNumber, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findSystemCampusByCampusNumber", startResult, maxRows, campusNumber);
			return (net.xidlims.domain.SystemCampus) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(SystemCampus entity) {
		return true;
	}
}
