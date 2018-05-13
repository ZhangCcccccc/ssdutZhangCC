package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.IotSharePowerOpentime;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage IotSharePowerOpentime entities.
 * 
 */
@Repository("IotSharePowerOpentimeDAO")
@Transactional
public class IotSharePowerOpentimeDAOImpl extends AbstractJpaDao<IotSharePowerOpentime>
		implements IotSharePowerOpentimeDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { IotSharePowerOpentime.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new IotSharePowerOpentimeDAOImpl
	 *
	 */
	public IotSharePowerOpentimeDAOImpl() {
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
	 * JPQL Query - findAllIotSharePowerOpentimes
	 *
	 */
	@Transactional
	public Set<IotSharePowerOpentime> findAllIotSharePowerOpentimes() throws DataAccessException {

		return findAllIotSharePowerOpentimes(-1, -1);
	}

	/**
	 * JPQL Query - findAllIotSharePowerOpentimes
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<IotSharePowerOpentime> findAllIotSharePowerOpentimes(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllIotSharePowerOpentimes", startResult, maxRows);
		return new LinkedHashSet<IotSharePowerOpentime>(query.getResultList());
	}

	/**
	 * JPQL Query - findIotSharePowerOpentimeByStartTime
	 *
	 */
	@Transactional
	public Set<IotSharePowerOpentime> findIotSharePowerOpentimeByStartTime(java.util.Calendar startTime) throws DataAccessException {

		return findIotSharePowerOpentimeByStartTime(startTime, -1, -1);
	}

	/**
	 * JPQL Query - findIotSharePowerOpentimeByStartTime
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<IotSharePowerOpentime> findIotSharePowerOpentimeByStartTime(java.util.Calendar startTime, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findIotSharePowerOpentimeByStartTime", startResult, maxRows, startTime);
		return new LinkedHashSet<IotSharePowerOpentime>(query.getResultList());
	}

	/**
	 * JPQL Query - findIotSharePowerOpentimeByEndTime
	 *
	 */
	@Transactional
	public Set<IotSharePowerOpentime> findIotSharePowerOpentimeByEndTime(java.util.Calendar endTime) throws DataAccessException {

		return findIotSharePowerOpentimeByEndTime(endTime, -1, -1);
	}

	/**
	 * JPQL Query - findIotSharePowerOpentimeByEndTime
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<IotSharePowerOpentime> findIotSharePowerOpentimeByEndTime(java.util.Calendar endTime, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findIotSharePowerOpentimeByEndTime", startResult, maxRows, endTime);
		return new LinkedHashSet<IotSharePowerOpentime>(query.getResultList());
	}

	/**
	 * JPQL Query - findIotSharePowerOpentimeByPrimaryKey
	 *
	 */
	@Transactional
	public IotSharePowerOpentime findIotSharePowerOpentimeByPrimaryKey(Integer id) throws DataAccessException {

		return findIotSharePowerOpentimeByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findIotSharePowerOpentimeByPrimaryKey
	 *
	 */

	@Transactional
	public IotSharePowerOpentime findIotSharePowerOpentimeByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findIotSharePowerOpentimeByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.IotSharePowerOpentime) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findIotSharePowerOpentimeByEnable
	 *
	 */
	@Transactional
	public Set<IotSharePowerOpentime> findIotSharePowerOpentimeByEnable(Integer enable) throws DataAccessException {

		return findIotSharePowerOpentimeByEnable(enable, -1, -1);
	}

	/**
	 * JPQL Query - findIotSharePowerOpentimeByEnable
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<IotSharePowerOpentime> findIotSharePowerOpentimeByEnable(Integer enable, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findIotSharePowerOpentimeByEnable", startResult, maxRows, enable);
		return new LinkedHashSet<IotSharePowerOpentime>(query.getResultList());
	}

	/**
	 * JPQL Query - findIotSharePowerOpentimeById
	 *
	 */
	@Transactional
	public IotSharePowerOpentime findIotSharePowerOpentimeById(Integer id) throws DataAccessException {

		return findIotSharePowerOpentimeById(id, -1, -1);
	}

	/**
	 * JPQL Query - findIotSharePowerOpentimeById
	 *
	 */

	@Transactional
	public IotSharePowerOpentime findIotSharePowerOpentimeById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findIotSharePowerOpentimeById", startResult, maxRows, id);
			return (net.xidlims.domain.IotSharePowerOpentime) query.getSingleResult();
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
	public boolean canBeMerged(IotSharePowerOpentime entity) {
		return true;
	}
}
