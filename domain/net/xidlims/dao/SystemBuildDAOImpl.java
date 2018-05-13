package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.SystemBuild;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage SystemBuild entities.
 * 
 */
@Repository("SystemBuildDAO")
@Transactional
public class SystemBuildDAOImpl extends AbstractJpaDao<SystemBuild> implements
		SystemBuildDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { SystemBuild.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new SystemBuildDAOImpl
	 *
	 */
	public SystemBuildDAOImpl() {
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
	 * JPQL Query - findSystemBuildByBuildNameContaining
	 *
	 */
	@Transactional
	public Set<SystemBuild> findSystemBuildByBuildNameContaining(String buildName) throws DataAccessException {

		return findSystemBuildByBuildNameContaining(buildName, -1, -1);
	}

	/**
	 * JPQL Query - findSystemBuildByBuildNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SystemBuild> findSystemBuildByBuildNameContaining(String buildName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSystemBuildByBuildNameContaining", startResult, maxRows, buildName);
		return new LinkedHashSet<SystemBuild>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllSystemBuilds
	 *
	 */
	@Transactional
	public Set<SystemBuild> findAllSystemBuilds() throws DataAccessException {

		return findAllSystemBuilds(-1, -1);
	}

	/**
	 * JPQL Query - findAllSystemBuilds
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SystemBuild> findAllSystemBuilds(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllSystemBuilds", startResult, maxRows);
		return new LinkedHashSet<SystemBuild>(query.getResultList());
	}

	/**
	 * JPQL Query - findSystemBuildByBuildNumberContaining
	 *
	 */
	@Transactional
	public Set<SystemBuild> findSystemBuildByBuildNumberContaining(String buildNumber) throws DataAccessException {

		return findSystemBuildByBuildNumberContaining(buildNumber, -1, -1);
	}

	/**
	 * JPQL Query - findSystemBuildByBuildNumberContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SystemBuild> findSystemBuildByBuildNumberContaining(String buildNumber, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSystemBuildByBuildNumberContaining", startResult, maxRows, buildNumber);
		return new LinkedHashSet<SystemBuild>(query.getResultList());
	}

	/**
	 * JPQL Query - findSystemBuildByBuildNumber
	 *
	 */
	@Transactional
	public SystemBuild findSystemBuildByBuildNumber(String buildNumber) throws DataAccessException {

		return findSystemBuildByBuildNumber(buildNumber, -1, -1);
	}

	/**
	 * JPQL Query - findSystemBuildByBuildNumber
	 *
	 */

	@Transactional
	public SystemBuild findSystemBuildByBuildNumber(String buildNumber, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findSystemBuildByBuildNumber", startResult, maxRows, buildNumber);
			return (net.xidlims.domain.SystemBuild) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findSystemBuildByPrimaryKey
	 *
	 */
	@Transactional
	public SystemBuild findSystemBuildByPrimaryKey(String buildNumber) throws DataAccessException {

		return findSystemBuildByPrimaryKey(buildNumber, -1, -1);
	}

	/**
	 * JPQL Query - findSystemBuildByPrimaryKey
	 *
	 */

	@Transactional
	public SystemBuild findSystemBuildByPrimaryKey(String buildNumber, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findSystemBuildByPrimaryKey", startResult, maxRows, buildNumber);
			return (net.xidlims.domain.SystemBuild) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findSystemBuildByBuildName
	 *
	 */
	@Transactional
	public Set<SystemBuild> findSystemBuildByBuildName(String buildName) throws DataAccessException {

		return findSystemBuildByBuildName(buildName, -1, -1);
	}

	/**
	 * JPQL Query - findSystemBuildByBuildName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SystemBuild> findSystemBuildByBuildName(String buildName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSystemBuildByBuildName", startResult, maxRows, buildName);
		return new LinkedHashSet<SystemBuild>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(SystemBuild entity) {
		return true;
	}
}
