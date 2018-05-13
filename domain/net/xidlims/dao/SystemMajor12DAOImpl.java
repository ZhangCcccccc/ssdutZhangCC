package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.SystemMajor12;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage SystemMajor12 entities.
 * 
 */
@Repository("SystemMajor12DAO")
@Transactional
public class SystemMajor12DAOImpl extends AbstractJpaDao<SystemMajor12>
		implements SystemMajor12DAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { SystemMajor12.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new SystemMajor12DAOImpl
	 *
	 */
	public SystemMajor12DAOImpl() {
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
	 * JPQL Query - findAllSystemMajor12s
	 *
	 */
	@Transactional
	public Set<SystemMajor12> findAllSystemMajor12s() throws DataAccessException {

		return findAllSystemMajor12s(-1, -1);
	}

	/**
	 * JPQL Query - findAllSystemMajor12s
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SystemMajor12> findAllSystemMajor12s(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllSystemMajor12s", startResult, maxRows);
		return new LinkedHashSet<SystemMajor12>(query.getResultList());
	}

	/**
	 * JPQL Query - findSystemMajor12ByMNumberContaining
	 *
	 */
	@Transactional
	public Set<SystemMajor12> findSystemMajor12ByMNumberContaining(String MNumber) throws DataAccessException {

		return findSystemMajor12ByMNumberContaining(MNumber, -1, -1);
	}

	/**
	 * JPQL Query - findSystemMajor12ByMNumberContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SystemMajor12> findSystemMajor12ByMNumberContaining(String MNumber, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSystemMajor12ByMNumberContaining", startResult, maxRows, MNumber);
		return new LinkedHashSet<SystemMajor12>(query.getResultList());
	}

	/**
	 * JPQL Query - findSystemMajor12ByPrimaryKey
	 *
	 */
	@Transactional
	public SystemMajor12 findSystemMajor12ByPrimaryKey(String MNumber) throws DataAccessException {

		return findSystemMajor12ByPrimaryKey(MNumber, -1, -1);
	}

	/**
	 * JPQL Query - findSystemMajor12ByPrimaryKey
	 *
	 */

	@Transactional
	public SystemMajor12 findSystemMajor12ByPrimaryKey(String MNumber, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findSystemMajor12ByPrimaryKey", startResult, maxRows, MNumber);
			return (net.xidlims.domain.SystemMajor12) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findSystemMajor12ByMNameContaining
	 *
	 */
	@Transactional
	public Set<SystemMajor12> findSystemMajor12ByMNameContaining(String MName) throws DataAccessException {

		return findSystemMajor12ByMNameContaining(MName, -1, -1);
	}

	/**
	 * JPQL Query - findSystemMajor12ByMNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SystemMajor12> findSystemMajor12ByMNameContaining(String MName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSystemMajor12ByMNameContaining", startResult, maxRows, MName);
		return new LinkedHashSet<SystemMajor12>(query.getResultList());
	}

	/**
	 * JPQL Query - findSystemMajor12ByMNumber
	 *
	 */
	@Transactional
	public SystemMajor12 findSystemMajor12ByMNumber(String MNumber) throws DataAccessException {

		return findSystemMajor12ByMNumber(MNumber, -1, -1);
	}

	/**
	 * JPQL Query - findSystemMajor12ByMNumber
	 *
	 */

	@Transactional
	public SystemMajor12 findSystemMajor12ByMNumber(String MNumber, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findSystemMajor12ByMNumber", startResult, maxRows, MNumber);
			return (net.xidlims.domain.SystemMajor12) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findSystemMajor12ByMName
	 *
	 */
	@Transactional
	public Set<SystemMajor12> findSystemMajor12ByMName(String MName) throws DataAccessException {

		return findSystemMajor12ByMName(MName, -1, -1);
	}

	/**
	 * JPQL Query - findSystemMajor12ByMName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SystemMajor12> findSystemMajor12ByMName(String MName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSystemMajor12ByMName", startResult, maxRows, MName);
		return new LinkedHashSet<SystemMajor12>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(SystemMajor12 entity) {
		return true;
	}
}
