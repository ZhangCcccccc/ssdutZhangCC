package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.SystemSubject12;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage SystemSubject12 entities.
 * 
 */
@Repository("SystemSubject12DAO")
@Transactional
public class SystemSubject12DAOImpl extends AbstractJpaDao<SystemSubject12>
		implements SystemSubject12DAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { SystemSubject12.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new SystemSubject12DAOImpl
	 *
	 */
	public SystemSubject12DAOImpl() {
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
	 * JPQL Query - findSystemSubject12BySNumberContaining
	 *
	 */
	@Transactional
	public Set<SystemSubject12> findSystemSubject12BySNumberContaining(String SNumber) throws DataAccessException {

		return findSystemSubject12BySNumberContaining(SNumber, -1, -1);
	}

	/**
	 * JPQL Query - findSystemSubject12BySNumberContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SystemSubject12> findSystemSubject12BySNumberContaining(String SNumber, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSystemSubject12BySNumberContaining", startResult, maxRows, SNumber);
		return new LinkedHashSet<SystemSubject12>(query.getResultList());
	}

	/**
	 * JPQL Query - findSystemSubject12BySName
	 *
	 */
	@Transactional
	public Set<SystemSubject12> findSystemSubject12BySName(String SName) throws DataAccessException {

		return findSystemSubject12BySName(SName, -1, -1);
	}

	/**
	 * JPQL Query - findSystemSubject12BySName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SystemSubject12> findSystemSubject12BySName(String SName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSystemSubject12BySName", startResult, maxRows, SName);
		return new LinkedHashSet<SystemSubject12>(query.getResultList());
	}

	/**
	 * JPQL Query - findSystemSubject12ByPrimaryKey
	 *
	 */
	@Transactional
	public SystemSubject12 findSystemSubject12ByPrimaryKey(String SNumber) throws DataAccessException {

		return findSystemSubject12ByPrimaryKey(SNumber, -1, -1);
	}

	/**
	 * JPQL Query - findSystemSubject12ByPrimaryKey
	 *
	 */

	@Transactional
	public SystemSubject12 findSystemSubject12ByPrimaryKey(String SNumber, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findSystemSubject12ByPrimaryKey", startResult, maxRows, SNumber);
			return (net.xidlims.domain.SystemSubject12) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findAllSystemSubject12s
	 *
	 */
	@Transactional
	public Set<SystemSubject12> findAllSystemSubject12s() throws DataAccessException {

		return findAllSystemSubject12s(-1, -1);
	}

	/**
	 * JPQL Query - findAllSystemSubject12s
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SystemSubject12> findAllSystemSubject12s(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllSystemSubject12s", startResult, maxRows);
		return new LinkedHashSet<SystemSubject12>(query.getResultList());
	}

	/**
	 * JPQL Query - findSystemSubject12BySNumber
	 *
	 */
	@Transactional
	public SystemSubject12 findSystemSubject12BySNumber(String SNumber) throws DataAccessException {

		return findSystemSubject12BySNumber(SNumber, -1, -1);
	}

	/**
	 * JPQL Query - findSystemSubject12BySNumber
	 *
	 */

	@Transactional
	public SystemSubject12 findSystemSubject12BySNumber(String SNumber, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findSystemSubject12BySNumber", startResult, maxRows, SNumber);
			return (net.xidlims.domain.SystemSubject12) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findSystemSubject12BySNameContaining
	 *
	 */
	@Transactional
	public Set<SystemSubject12> findSystemSubject12BySNameContaining(String SName) throws DataAccessException {

		return findSystemSubject12BySNameContaining(SName, -1, -1);
	}

	/**
	 * JPQL Query - findSystemSubject12BySNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SystemSubject12> findSystemSubject12BySNameContaining(String SName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSystemSubject12BySNameContaining", startResult, maxRows, SName);
		return new LinkedHashSet<SystemSubject12>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(SystemSubject12 entity) {
		return true;
	}
}
