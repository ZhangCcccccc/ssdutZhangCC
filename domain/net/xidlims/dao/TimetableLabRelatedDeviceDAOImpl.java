package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.TimetableLabRelatedDevice;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage TimetableLabRelatedDevice entities.
 * 
 */
@Repository("TimetableLabRelatedDeviceDAO")
@Transactional
public class TimetableLabRelatedDeviceDAOImpl extends AbstractJpaDao<TimetableLabRelatedDevice>
		implements TimetableLabRelatedDeviceDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { TimetableLabRelatedDevice.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new TimetableLabRelatedDeviceDAOImpl
	 *
	 */
	public TimetableLabRelatedDeviceDAOImpl() {
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
	 * JPQL Query - findTimetableLabRelatedDeviceByPrimaryKey
	 *
	 */
	@Transactional
	public TimetableLabRelatedDevice findTimetableLabRelatedDeviceByPrimaryKey(Integer id) throws DataAccessException {

		return findTimetableLabRelatedDeviceByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findTimetableLabRelatedDeviceByPrimaryKey
	 *
	 */

	@Transactional
	public TimetableLabRelatedDevice findTimetableLabRelatedDeviceByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findTimetableLabRelatedDeviceByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.TimetableLabRelatedDevice) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findAllTimetableLabRelatedDevices
	 *
	 */
	@Transactional
	public Set<TimetableLabRelatedDevice> findAllTimetableLabRelatedDevices() throws DataAccessException {

		return findAllTimetableLabRelatedDevices(-1, -1);
	}

	/**
	 * JPQL Query - findAllTimetableLabRelatedDevices
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TimetableLabRelatedDevice> findAllTimetableLabRelatedDevices(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllTimetableLabRelatedDevices", startResult, maxRows);
		return new LinkedHashSet<TimetableLabRelatedDevice>(query.getResultList());
	}

	/**
	 * JPQL Query - findTimetableLabRelatedDeviceByLabRoomDevice
	 *
	 */
	@Transactional
	public Set<TimetableLabRelatedDevice> findTimetableLabRelatedDeviceByLabRoomDevice(Integer labRoomDevice) throws DataAccessException {

		return findTimetableLabRelatedDeviceByLabRoomDevice(labRoomDevice, -1, -1);
	}

	/**
	 * JPQL Query - findTimetableLabRelatedDeviceByLabRoomDevice
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TimetableLabRelatedDevice> findTimetableLabRelatedDeviceByLabRoomDevice(Integer labRoomDevice, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTimetableLabRelatedDeviceByLabRoomDevice", startResult, maxRows, labRoomDevice);
		return new LinkedHashSet<TimetableLabRelatedDevice>(query.getResultList());
	}

	/**
	 * JPQL Query - findTimetableLabRelatedDeviceById
	 *
	 */
	@Transactional
	public TimetableLabRelatedDevice findTimetableLabRelatedDeviceById(Integer id) throws DataAccessException {

		return findTimetableLabRelatedDeviceById(id, -1, -1);
	}

	/**
	 * JPQL Query - findTimetableLabRelatedDeviceById
	 *
	 */

	@Transactional
	public TimetableLabRelatedDevice findTimetableLabRelatedDeviceById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findTimetableLabRelatedDeviceById", startResult, maxRows, id);
			return (net.xidlims.domain.TimetableLabRelatedDevice) query.getSingleResult();
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
	public boolean canBeMerged(TimetableLabRelatedDevice entity) {
		return true;
	}
}
