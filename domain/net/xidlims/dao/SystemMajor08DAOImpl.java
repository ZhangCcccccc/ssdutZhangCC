package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.SystemMajor08;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage SystemMajor08 entities.
 * 
 */
@Repository("SystemMajor08DAO")
@Transactional
public class SystemMajor08DAOImpl extends AbstractJpaDao<SystemMajor08>
		implements SystemMajor08DAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { SystemMajor08.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new SystemMajor08DAOImpl
	 *
	 */
	public SystemMajor08DAOImpl() {
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
	 * JPQL Query - findSystemMajor08ByPrimaryKey
	 *
	 */
	@Transactional
	public SystemMajor08 findSystemMajor08ByPrimaryKey(String SNumber) throws DataAccessException {

		return findSystemMajor08ByPrimaryKey(SNumber, -1, -1);
	}

	/**
	 * JPQL Query - findSystemMajor08ByPrimaryKey
	 *
	 */

	@Transactional
	public SystemMajor08 findSystemMajor08ByPrimaryKey(String SNumber, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findSystemMajor08ByPrimaryKey", startResult, maxRows, SNumber);
			return (net.xidlims.domain.SystemMajor08) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findSystemMajor08BySNumberContaining
	 *
	 */
	@Transactional
	public Set<SystemMajor08> findSystemMajor08BySNumberContaining(String SNumber) throws DataAccessException {

		return findSystemMajor08BySNumberContaining(SNumber, -1, -1);
	}

	/**
	 * JPQL Query - findSystemMajor08BySNumberContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SystemMajor08> findSystemMajor08BySNumberContaining(String SNumber, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSystemMajor08BySNumberContaining", startResult, maxRows, SNumber);
		return new LinkedHashSet<SystemMajor08>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllSystemMajor08s
	 *
	 */
	@Transactional
	public Set<SystemMajor08> findAllSystemMajor08s() throws DataAccessException {

		return findAllSystemMajor08s(-1, -1);
	}

	/**
	 * JPQL Query - findAllSystemMajor08s
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SystemMajor08> findAllSystemMajor08s(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllSystemMajor08s", startResult, maxRows);
		return new LinkedHashSet<SystemMajor08>(query.getResultList());
	}

	/**
	 * JPQL Query - findSystemMajor08BySName
	 *
	 */
	@Transactional
	public Set<SystemMajor08> findSystemMajor08BySName(String SName) throws DataAccessException {

		return findSystemMajor08BySName(SName, -1, -1);
	}

	/**
	 * JPQL Query - findSystemMajor08BySName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SystemMajor08> findSystemMajor08BySName(String SName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSystemMajor08BySName", startResult, maxRows, SName);
		return new LinkedHashSet<SystemMajor08>(query.getResultList());
	}

	/**
	 * JPQL Query - findSystemMajor08BySNumber
	 *
	 */
	@Transactional
	public SystemMajor08 findSystemMajor08BySNumber(String SNumber) throws DataAccessException {

		return findSystemMajor08BySNumber(SNumber, -1, -1);
	}

	/**
	 * JPQL Query - findSystemMajor08BySNumber
	 *
	 */

	@Transactional
	public SystemMajor08 findSystemMajor08BySNumber(String SNumber, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findSystemMajor08BySNumber", startResult, maxRows, SNumber);
			return (net.xidlims.domain.SystemMajor08) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findSystemMajor08BySNameContaining
	 *
	 */
	@Transactional
	public Set<SystemMajor08> findSystemMajor08BySNameContaining(String SName) throws DataAccessException {

		return findSystemMajor08BySNameContaining(SName, -1, -1);
	}

	/**
	 * JPQL Query - findSystemMajor08BySNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SystemMajor08> findSystemMajor08BySNameContaining(String SName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSystemMajor08BySNameContaining", startResult, maxRows, SName);
		return new LinkedHashSet<SystemMajor08>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(SystemMajor08 entity) {
		return true;
	}
}
