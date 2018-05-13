package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.CDeviceStatus;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage CDeviceStatus entities.
 * 
 */
@Repository("CDeviceStatusDAO")
@Transactional
public class CDeviceStatusDAOImpl extends AbstractJpaDao<CDeviceStatus>
		implements CDeviceStatusDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { CDeviceStatus.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new CDeviceStatusDAOImpl
	 *
	 */
	public CDeviceStatusDAOImpl() {
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
	 * JPQL Query - findCDeviceStatusById
	 *
	 */
	@Transactional
	public CDeviceStatus findCDeviceStatusById(Integer id) throws DataAccessException {

		return findCDeviceStatusById(id, -1, -1);
	}

	/**
	 * JPQL Query - findCDeviceStatusById
	 *
	 */

	@Transactional
	public CDeviceStatus findCDeviceStatusById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findCDeviceStatusById", startResult, maxRows, id);
			return (net.xidlims.domain.CDeviceStatus) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findCDeviceStatusByEndDate
	 *
	 */
	@Transactional
	public Set<CDeviceStatus> findCDeviceStatusByEndDate(java.util.Calendar endDate) throws DataAccessException {

		return findCDeviceStatusByEndDate(endDate, -1, -1);
	}

	/**
	 * JPQL Query - findCDeviceStatusByEndDate
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CDeviceStatus> findCDeviceStatusByEndDate(java.util.Calendar endDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCDeviceStatusByEndDate", startResult, maxRows, endDate);
		return new LinkedHashSet<CDeviceStatus>(query.getResultList());
	}

	/**
	 * JPQL Query - findCDeviceStatusByStatusNameContaining
	 *
	 */
	@Transactional
	public Set<CDeviceStatus> findCDeviceStatusByStatusNameContaining(String statusName) throws DataAccessException {

		return findCDeviceStatusByStatusNameContaining(statusName, -1, -1);
	}

	/**
	 * JPQL Query - findCDeviceStatusByStatusNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CDeviceStatus> findCDeviceStatusByStatusNameContaining(String statusName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCDeviceStatusByStatusNameContaining", startResult, maxRows, statusName);
		return new LinkedHashSet<CDeviceStatus>(query.getResultList());
	}

	/**
	 * JPQL Query - findCDeviceStatusByPrimaryKey
	 *
	 */
	@Transactional
	public CDeviceStatus findCDeviceStatusByPrimaryKey(Integer id) throws DataAccessException {

		return findCDeviceStatusByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findCDeviceStatusByPrimaryKey
	 *
	 */

	@Transactional
	public CDeviceStatus findCDeviceStatusByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findCDeviceStatusByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.CDeviceStatus) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findAllCDeviceStatuss
	 *
	 */
	@Transactional
	public Set<CDeviceStatus> findAllCDeviceStatuss() throws DataAccessException {

		return findAllCDeviceStatuss(-1, -1);
	}

	/**
	 * JPQL Query - findAllCDeviceStatuss
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CDeviceStatus> findAllCDeviceStatuss(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllCDeviceStatuss", startResult, maxRows);
		return new LinkedHashSet<CDeviceStatus>(query.getResultList());
	}

	/**
	 * JPQL Query - findCDeviceStatusByStatusName
	 *
	 */
	@Transactional
	public Set<CDeviceStatus> findCDeviceStatusByStatusName(String statusName) throws DataAccessException {

		return findCDeviceStatusByStatusName(statusName, -1, -1);
	}

	/**
	 * JPQL Query - findCDeviceStatusByStatusName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CDeviceStatus> findCDeviceStatusByStatusName(String statusName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCDeviceStatusByStatusName", startResult, maxRows, statusName);
		return new LinkedHashSet<CDeviceStatus>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(CDeviceStatus entity) {
		return true;
	}
}
