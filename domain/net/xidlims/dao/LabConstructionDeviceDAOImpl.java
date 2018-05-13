package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.LabConstructionDevice;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage LabConstructionDevice entities.
 * 
 */
@Repository("LabConstructionDeviceDAO")
@Transactional
public class LabConstructionDeviceDAOImpl extends AbstractJpaDao<LabConstructionDevice>
		implements LabConstructionDeviceDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { LabConstructionDevice.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new LabConstructionDeviceDAOImpl
	 *
	 */
	public LabConstructionDeviceDAOImpl() {
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
	 * JPQL Query - findLabConstructionDeviceByDeviceModelContaining
	 *
	 */
	@Transactional
	public Set<LabConstructionDevice> findLabConstructionDeviceByDeviceModelContaining(String deviceModel) throws DataAccessException {

		return findLabConstructionDeviceByDeviceModelContaining(deviceModel, -1, -1);
	}

	/**
	 * JPQL Query - findLabConstructionDeviceByDeviceModelContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabConstructionDevice> findLabConstructionDeviceByDeviceModelContaining(String deviceModel, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabConstructionDeviceByDeviceModelContaining", startResult, maxRows, deviceModel);
		return new LinkedHashSet<LabConstructionDevice>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllLabConstructionDevices
	 *
	 */
	@Transactional
	public Set<LabConstructionDevice> findAllLabConstructionDevices() throws DataAccessException {

		return findAllLabConstructionDevices(-1, -1);
	}

	/**
	 * JPQL Query - findAllLabConstructionDevices
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabConstructionDevice> findAllLabConstructionDevices(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllLabConstructionDevices", startResult, maxRows);
		return new LinkedHashSet<LabConstructionDevice>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabConstructionDeviceByDeviceNameContaining
	 *
	 */
	@Transactional
	public Set<LabConstructionDevice> findLabConstructionDeviceByDeviceNameContaining(String deviceName) throws DataAccessException {

		return findLabConstructionDeviceByDeviceNameContaining(deviceName, -1, -1);
	}

	/**
	 * JPQL Query - findLabConstructionDeviceByDeviceNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabConstructionDevice> findLabConstructionDeviceByDeviceNameContaining(String deviceName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabConstructionDeviceByDeviceNameContaining", startResult, maxRows, deviceName);
		return new LinkedHashSet<LabConstructionDevice>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabConstructionDeviceByPrimaryKey
	 *
	 */
	@Transactional
	public LabConstructionDevice findLabConstructionDeviceByPrimaryKey(Integer id) throws DataAccessException {

		return findLabConstructionDeviceByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findLabConstructionDeviceByPrimaryKey
	 *
	 */

	@Transactional
	public LabConstructionDevice findLabConstructionDeviceByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findLabConstructionDeviceByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.LabConstructionDevice) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findLabConstructionDeviceByDevicePriceRef
	 *
	 */
	@Transactional
	public Set<LabConstructionDevice> findLabConstructionDeviceByDevicePriceRef(java.math.BigDecimal devicePriceRef) throws DataAccessException {

		return findLabConstructionDeviceByDevicePriceRef(devicePriceRef, -1, -1);
	}

	/**
	 * JPQL Query - findLabConstructionDeviceByDevicePriceRef
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabConstructionDevice> findLabConstructionDeviceByDevicePriceRef(java.math.BigDecimal devicePriceRef, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabConstructionDeviceByDevicePriceRef", startResult, maxRows, devicePriceRef);
		return new LinkedHashSet<LabConstructionDevice>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabConstructionDeviceByDeviceQuantity
	 *
	 */
	@Transactional
	public Set<LabConstructionDevice> findLabConstructionDeviceByDeviceQuantity(Integer deviceQuantity) throws DataAccessException {

		return findLabConstructionDeviceByDeviceQuantity(deviceQuantity, -1, -1);
	}

	/**
	 * JPQL Query - findLabConstructionDeviceByDeviceQuantity
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabConstructionDevice> findLabConstructionDeviceByDeviceQuantity(Integer deviceQuantity, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabConstructionDeviceByDeviceQuantity", startResult, maxRows, deviceQuantity);
		return new LinkedHashSet<LabConstructionDevice>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabConstructionDeviceByComments
	 *
	 */
	@Transactional
	public Set<LabConstructionDevice> findLabConstructionDeviceByComments(String comments) throws DataAccessException {

		return findLabConstructionDeviceByComments(comments, -1, -1);
	}

	/**
	 * JPQL Query - findLabConstructionDeviceByComments
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabConstructionDevice> findLabConstructionDeviceByComments(String comments, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabConstructionDeviceByComments", startResult, maxRows, comments);
		return new LinkedHashSet<LabConstructionDevice>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabConstructionDeviceByTag
	 *
	 */
	@Transactional
	public Set<LabConstructionDevice> findLabConstructionDeviceByTag(Integer tag) throws DataAccessException {

		return findLabConstructionDeviceByTag(tag, -1, -1);
	}

	/**
	 * JPQL Query - findLabConstructionDeviceByTag
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabConstructionDevice> findLabConstructionDeviceByTag(Integer tag, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabConstructionDeviceByTag", startResult, maxRows, tag);
		return new LinkedHashSet<LabConstructionDevice>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabConstructionDeviceByDeviceName
	 *
	 */
	@Transactional
	public Set<LabConstructionDevice> findLabConstructionDeviceByDeviceName(String deviceName) throws DataAccessException {

		return findLabConstructionDeviceByDeviceName(deviceName, -1, -1);
	}

	/**
	 * JPQL Query - findLabConstructionDeviceByDeviceName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabConstructionDevice> findLabConstructionDeviceByDeviceName(String deviceName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabConstructionDeviceByDeviceName", startResult, maxRows, deviceName);
		return new LinkedHashSet<LabConstructionDevice>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabConstructionDeviceByDevicePriceSig
	 *
	 */
	@Transactional
	public Set<LabConstructionDevice> findLabConstructionDeviceByDevicePriceSig(java.math.BigDecimal devicePriceSig) throws DataAccessException {

		return findLabConstructionDeviceByDevicePriceSig(devicePriceSig, -1, -1);
	}

	/**
	 * JPQL Query - findLabConstructionDeviceByDevicePriceSig
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabConstructionDevice> findLabConstructionDeviceByDevicePriceSig(java.math.BigDecimal devicePriceSig, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabConstructionDeviceByDevicePriceSig", startResult, maxRows, devicePriceSig);
		return new LinkedHashSet<LabConstructionDevice>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabConstructionDeviceByDeviceModel
	 *
	 */
	@Transactional
	public Set<LabConstructionDevice> findLabConstructionDeviceByDeviceModel(String deviceModel) throws DataAccessException {

		return findLabConstructionDeviceByDeviceModel(deviceModel, -1, -1);
	}

	/**
	 * JPQL Query - findLabConstructionDeviceByDeviceModel
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabConstructionDevice> findLabConstructionDeviceByDeviceModel(String deviceModel, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabConstructionDeviceByDeviceModel", startResult, maxRows, deviceModel);
		return new LinkedHashSet<LabConstructionDevice>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabConstructionDeviceByArrivalTime
	 *
	 */
	@Transactional
	public Set<LabConstructionDevice> findLabConstructionDeviceByArrivalTime(java.util.Calendar arrivalTime) throws DataAccessException {

		return findLabConstructionDeviceByArrivalTime(arrivalTime, -1, -1);
	}

	/**
	 * JPQL Query - findLabConstructionDeviceByArrivalTime
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabConstructionDevice> findLabConstructionDeviceByArrivalTime(java.util.Calendar arrivalTime, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabConstructionDeviceByArrivalTime", startResult, maxRows, arrivalTime);
		return new LinkedHashSet<LabConstructionDevice>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabConstructionDeviceById
	 *
	 */
	@Transactional
	public LabConstructionDevice findLabConstructionDeviceById(Integer id) throws DataAccessException {

		return findLabConstructionDeviceById(id, -1, -1);
	}

	/**
	 * JPQL Query - findLabConstructionDeviceById
	 *
	 */

	@Transactional
	public LabConstructionDevice findLabConstructionDeviceById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findLabConstructionDeviceById", startResult, maxRows, id);
			return (net.xidlims.domain.LabConstructionDevice) query.getSingleResult();
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
	public boolean canBeMerged(LabConstructionDevice entity) {
		return true;
	}
}
