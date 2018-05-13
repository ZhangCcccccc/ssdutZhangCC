package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.LabRoomDevice;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage LabRoomDevice entities.
 * 
 */
@Repository("LabRoomDeviceDAO")
@Transactional
public class LabRoomDeviceDAOImpl extends AbstractJpaDao<LabRoomDevice>
		implements LabRoomDeviceDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { LabRoomDevice.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new LabRoomDeviceDAOImpl
	 *
	 */
	public LabRoomDeviceDAOImpl() {
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
	 * JPQL Query - findLabRoomDeviceByFunction
	 *
	 */
	@Transactional
	public Set<LabRoomDevice> findLabRoomDeviceByFunction(String function) throws DataAccessException {

		return findLabRoomDeviceByFunction(function, -1, -1);
	}

	/**
	 * JPQL Query - findLabRoomDeviceByFunction
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabRoomDevice> findLabRoomDeviceByFunction(String function, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabRoomDeviceByFunction", startResult, maxRows, function);
		return new LinkedHashSet<LabRoomDevice>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllLabRoomDevices
	 *
	 */
	@Transactional
	public Set<LabRoomDevice> findAllLabRoomDevices() throws DataAccessException {

		return findAllLabRoomDevices(-1, -1);
	}

	/**
	 * JPQL Query - findAllLabRoomDevices
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabRoomDevice> findAllLabRoomDevices(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllLabRoomDevices", startResult, maxRows);
		return new LinkedHashSet<LabRoomDevice>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabRoomDeviceByFeatures
	 *
	 */
	@Transactional
	public Set<LabRoomDevice> findLabRoomDeviceByFeatures(String features) throws DataAccessException {

		return findLabRoomDeviceByFeatures(features, -1, -1);
	}

	/**
	 * JPQL Query - findLabRoomDeviceByFeatures
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabRoomDevice> findLabRoomDeviceByFeatures(String features, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabRoomDeviceByFeatures", startResult, maxRows, features);
		return new LinkedHashSet<LabRoomDevice>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabRoomDeviceByDimensionalCodeContaining
	 *
	 */
	@Transactional
	public Set<LabRoomDevice> findLabRoomDeviceByDimensionalCodeContaining(String dimensionalCode) throws DataAccessException {

		return findLabRoomDeviceByDimensionalCodeContaining(dimensionalCode, -1, -1);
	}

	/**
	 * JPQL Query - findLabRoomDeviceByDimensionalCodeContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabRoomDevice> findLabRoomDeviceByDimensionalCodeContaining(String dimensionalCode, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabRoomDeviceByDimensionalCodeContaining", startResult, maxRows, dimensionalCode);
		return new LinkedHashSet<LabRoomDevice>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabRoomDeviceByDimensionalCode
	 *
	 */
	@Transactional
	public Set<LabRoomDevice> findLabRoomDeviceByDimensionalCode(String dimensionalCode) throws DataAccessException {

		return findLabRoomDeviceByDimensionalCode(dimensionalCode, -1, -1);
	}

	/**
	 * JPQL Query - findLabRoomDeviceByDimensionalCode
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabRoomDevice> findLabRoomDeviceByDimensionalCode(String dimensionalCode, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabRoomDeviceByDimensionalCode", startResult, maxRows, dimensionalCode);
		return new LinkedHashSet<LabRoomDevice>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabRoomDeviceById
	 *
	 */
	@Transactional
	public LabRoomDevice findLabRoomDeviceById(Integer id) throws DataAccessException {

		return findLabRoomDeviceById(id, -1, -1);
	}

	/**
	 * JPQL Query - findLabRoomDeviceById
	 *
	 */

	@Transactional
	public LabRoomDevice findLabRoomDeviceById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findLabRoomDeviceById", startResult, maxRows, id);
			return (net.xidlims.domain.LabRoomDevice) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findLabRoomDeviceByPrice
	 *
	 */
	@Transactional
	public Set<LabRoomDevice> findLabRoomDeviceByPrice(java.math.BigDecimal price) throws DataAccessException {

		return findLabRoomDeviceByPrice(price, -1, -1);
	}

	/**
	 * JPQL Query - findLabRoomDeviceByPrice
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabRoomDevice> findLabRoomDeviceByPrice(java.math.BigDecimal price, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabRoomDeviceByPrice", startResult, maxRows, price);
		return new LinkedHashSet<LabRoomDevice>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabRoomDeviceByApplications
	 *
	 */
	@Transactional
	public Set<LabRoomDevice> findLabRoomDeviceByApplications(String applications) throws DataAccessException {

		return findLabRoomDeviceByApplications(applications, -1, -1);
	}

	/**
	 * JPQL Query - findLabRoomDeviceByApplications
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabRoomDevice> findLabRoomDeviceByApplications(String applications, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabRoomDeviceByApplications", startResult, maxRows, applications);
		return new LinkedHashSet<LabRoomDevice>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabRoomDeviceByPrimaryKey
	 *
	 */
	@Transactional
	public LabRoomDevice findLabRoomDeviceByPrimaryKey(Integer id) throws DataAccessException {

		return findLabRoomDeviceByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findLabRoomDeviceByPrimaryKey
	 *
	 */

	@Transactional
	public LabRoomDevice findLabRoomDeviceByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findLabRoomDeviceByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.LabRoomDevice) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findLabRoomDeviceByIndicators
	 *
	 */
	@Transactional
	public Set<LabRoomDevice> findLabRoomDeviceByIndicators(String indicators) throws DataAccessException {

		return findLabRoomDeviceByIndicators(indicators, -1, -1);
	}

	/**
	 * JPQL Query - findLabRoomDeviceByIndicators
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabRoomDevice> findLabRoomDeviceByIndicators(String indicators, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabRoomDeviceByIndicators", startResult, maxRows, indicators);
		return new LinkedHashSet<LabRoomDevice>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(LabRoomDevice entity) {
		return true;
	}
}
