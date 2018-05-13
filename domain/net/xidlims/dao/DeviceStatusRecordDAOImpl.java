package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.DeviceStatusRecord;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage DeviceStatusRecord entities.
 * 
 */
@Repository("DeviceStatusRecordDAO")
@Transactional
public class DeviceStatusRecordDAOImpl extends AbstractJpaDao<DeviceStatusRecord>
		implements DeviceStatusRecordDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { DeviceStatusRecord.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new DeviceStatusRecordDAOImpl
	 *
	 */
	public DeviceStatusRecordDAOImpl() {
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
	 * JPQL Query - findDeviceStatusRecordById
	 *
	 */
	@Transactional
	public DeviceStatusRecord findDeviceStatusRecordById(Integer id) throws DataAccessException {

		return findDeviceStatusRecordById(id, -1, -1);
	}

	/**
	 * JPQL Query - findDeviceStatusRecordById
	 *
	 */

	@Transactional
	public DeviceStatusRecord findDeviceStatusRecordById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findDeviceStatusRecordById", startResult, maxRows, id);
			return (net.xidlims.domain.DeviceStatusRecord) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findDeviceStatusRecordByEndDate
	 *
	 */
	@Transactional
	public Set<DeviceStatusRecord> findDeviceStatusRecordByEndDate(java.util.Calendar endDate) throws DataAccessException {

		return findDeviceStatusRecordByEndDate(endDate, -1, -1);
	}

	/**
	 * JPQL Query - findDeviceStatusRecordByEndDate
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<DeviceStatusRecord> findDeviceStatusRecordByEndDate(java.util.Calendar endDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findDeviceStatusRecordByEndDate", startResult, maxRows, endDate);
		return new LinkedHashSet<DeviceStatusRecord>(query.getResultList());
	}

	/**
	 * JPQL Query - findDeviceStatusRecordByEndDateAfter
	 *
	 */
	@Transactional
	public Set<DeviceStatusRecord> findDeviceStatusRecordByEndDateAfter(java.util.Calendar endDate) throws DataAccessException {

		return findDeviceStatusRecordByEndDateAfter(endDate, -1, -1);
	}

	/**
	 * JPQL Query - findDeviceStatusRecordByEndDateAfter
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<DeviceStatusRecord> findDeviceStatusRecordByEndDateAfter(java.util.Calendar endDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findDeviceStatusRecordByEndDateAfter", startResult, maxRows, endDate);
		return new LinkedHashSet<DeviceStatusRecord>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllDeviceStatusRecords
	 *
	 */
	@Transactional
	public Set<DeviceStatusRecord> findAllDeviceStatusRecords() throws DataAccessException {

		return findAllDeviceStatusRecords(-1, -1);
	}

	/**
	 * JPQL Query - findAllDeviceStatusRecords
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<DeviceStatusRecord> findAllDeviceStatusRecords(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllDeviceStatusRecords", startResult, maxRows);
		return new LinkedHashSet<DeviceStatusRecord>(query.getResultList());
	}

	/**
	 * JPQL Query - findDeviceStatusRecordByStatusName
	 *
	 */
	@Transactional
	public Set<DeviceStatusRecord> findDeviceStatusRecordByStatusName(String statusName) throws DataAccessException {

		return findDeviceStatusRecordByStatusName(statusName, -1, -1);
	}

	/**
	 * JPQL Query - findDeviceStatusRecordByStatusName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<DeviceStatusRecord> findDeviceStatusRecordByStatusName(String statusName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findDeviceStatusRecordByStatusName", startResult, maxRows, statusName);
		return new LinkedHashSet<DeviceStatusRecord>(query.getResultList());
	}

	/**
	 * JPQL Query - findDeviceStatusRecordByStatusNameContaining
	 *
	 */
	@Transactional
	public Set<DeviceStatusRecord> findDeviceStatusRecordByStatusNameContaining(String statusName) throws DataAccessException {

		return findDeviceStatusRecordByStatusNameContaining(statusName, -1, -1);
	}

	/**
	 * JPQL Query - findDeviceStatusRecordByStatusNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<DeviceStatusRecord> findDeviceStatusRecordByStatusNameContaining(String statusName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findDeviceStatusRecordByStatusNameContaining", startResult, maxRows, statusName);
		return new LinkedHashSet<DeviceStatusRecord>(query.getResultList());
	}

	/**
	 * JPQL Query - findDeviceStatusRecordByEndDateBefore
	 *
	 */
	@Transactional
	public Set<DeviceStatusRecord> findDeviceStatusRecordByEndDateBefore(java.util.Calendar endDate) throws DataAccessException {

		return findDeviceStatusRecordByEndDateBefore(endDate, -1, -1);
	}

	/**
	 * JPQL Query - findDeviceStatusRecordByEndDateBefore
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<DeviceStatusRecord> findDeviceStatusRecordByEndDateBefore(java.util.Calendar endDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findDeviceStatusRecordByEndDateBefore", startResult, maxRows, endDate);
		return new LinkedHashSet<DeviceStatusRecord>(query.getResultList());
	}

	/**
	 * JPQL Query - findDeviceStatusRecordByPrimaryKey
	 *
	 */
	@Transactional
	public DeviceStatusRecord findDeviceStatusRecordByPrimaryKey(Integer id) throws DataAccessException {

		return findDeviceStatusRecordByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findDeviceStatusRecordByPrimaryKey
	 *
	 */

	@Transactional
	public DeviceStatusRecord findDeviceStatusRecordByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findDeviceStatusRecordByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.DeviceStatusRecord) query.getSingleResult();
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
	public boolean canBeMerged(DeviceStatusRecord entity) {
		return true;
	}
}
