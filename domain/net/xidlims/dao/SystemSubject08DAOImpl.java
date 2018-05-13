package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.SystemSubject08;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage SystemSubject08 entities.
 * 
 */
@Repository("SystemSubject08DAO")
@Transactional
public class SystemSubject08DAOImpl extends AbstractJpaDao<SystemSubject08>
		implements SystemSubject08DAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { SystemSubject08.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new SystemSubject08DAOImpl
	 *
	 */
	public SystemSubject08DAOImpl() {
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
	 * JPQL Query - findAllSystemSubject08s
	 *
	 */
	@Transactional
	public Set<SystemSubject08> findAllSystemSubject08s() throws DataAccessException {

		return findAllSystemSubject08s(-1, -1);
	}

	/**
	 * JPQL Query - findAllSystemSubject08s
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SystemSubject08> findAllSystemSubject08s(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllSystemSubject08s", startResult, maxRows);
		return new LinkedHashSet<SystemSubject08>(query.getResultList());
	}

	/**
	 * JPQL Query - findSystemSubject08BySName
	 *
	 */
	@Transactional
	public Set<SystemSubject08> findSystemSubject08BySName(String SName) throws DataAccessException {

		return findSystemSubject08BySName(SName, -1, -1);
	}

	/**
	 * JPQL Query - findSystemSubject08BySName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SystemSubject08> findSystemSubject08BySName(String SName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSystemSubject08BySName", startResult, maxRows, SName);
		return new LinkedHashSet<SystemSubject08>(query.getResultList());
	}

	/**
	 * JPQL Query - findSystemSubject08BySNumberContaining
	 *
	 */
	@Transactional
	public Set<SystemSubject08> findSystemSubject08BySNumberContaining(String SNumber) throws DataAccessException {

		return findSystemSubject08BySNumberContaining(SNumber, -1, -1);
	}

	/**
	 * JPQL Query - findSystemSubject08BySNumberContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SystemSubject08> findSystemSubject08BySNumberContaining(String SNumber, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSystemSubject08BySNumberContaining", startResult, maxRows, SNumber);
		return new LinkedHashSet<SystemSubject08>(query.getResultList());
	}

	/**
	 * JPQL Query - findSystemSubject08BySNameContaining
	 *
	 */
	@Transactional
	public Set<SystemSubject08> findSystemSubject08BySNameContaining(String SName) throws DataAccessException {

		return findSystemSubject08BySNameContaining(SName, -1, -1);
	}

	/**
	 * JPQL Query - findSystemSubject08BySNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SystemSubject08> findSystemSubject08BySNameContaining(String SName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSystemSubject08BySNameContaining", startResult, maxRows, SName);
		return new LinkedHashSet<SystemSubject08>(query.getResultList());
	}

	/**
	 * JPQL Query - findSystemSubject08BySNumber
	 *
	 */
	@Transactional
	public SystemSubject08 findSystemSubject08BySNumber(String SNumber) throws DataAccessException {

		return findSystemSubject08BySNumber(SNumber, -1, -1);
	}

	/**
	 * JPQL Query - findSystemSubject08BySNumber
	 *
	 */

	@Transactional
	public SystemSubject08 findSystemSubject08BySNumber(String SNumber, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findSystemSubject08BySNumber", startResult, maxRows, SNumber);
			return (net.xidlims.domain.SystemSubject08) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findSystemSubject08ByPrimaryKey
	 *
	 */
	@Transactional
	public SystemSubject08 findSystemSubject08ByPrimaryKey(String SNumber) throws DataAccessException {

		return findSystemSubject08ByPrimaryKey(SNumber, -1, -1);
	}

	/**
	 * JPQL Query - findSystemSubject08ByPrimaryKey
	 *
	 */

	@Transactional
	public SystemSubject08 findSystemSubject08ByPrimaryKey(String SNumber, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findSystemSubject08ByPrimaryKey", startResult, maxRows, SNumber);
			return (net.xidlims.domain.SystemSubject08) query.getSingleResult();
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
	public boolean canBeMerged(SystemSubject08 entity) {
		return true;
	}
}
