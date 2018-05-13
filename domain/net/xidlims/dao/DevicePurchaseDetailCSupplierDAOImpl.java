package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.DevicePurchaseDetailCSupplier;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage DevicePurchaseDetailCSupplier entities.
 * 
 */
@Repository("DevicePurchaseDetailCSupplierDAO")
@Transactional
public class DevicePurchaseDetailCSupplierDAOImpl extends AbstractJpaDao<DevicePurchaseDetailCSupplier>
		implements DevicePurchaseDetailCSupplierDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { DevicePurchaseDetailCSupplier.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new DevicePurchaseDetailCSupplierDAOImpl
	 *
	 */
	public DevicePurchaseDetailCSupplierDAOImpl() {
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
	 * JPQL Query - findDevicePurchaseDetailCSupplierByPrimaryKey
	 *
	 */
	@Transactional
	public DevicePurchaseDetailCSupplier findDevicePurchaseDetailCSupplierByPrimaryKey(Integer id) throws DataAccessException {

		return findDevicePurchaseDetailCSupplierByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findDevicePurchaseDetailCSupplierByPrimaryKey
	 *
	 */

	@Transactional
	public DevicePurchaseDetailCSupplier findDevicePurchaseDetailCSupplierByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findDevicePurchaseDetailCSupplierByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.DevicePurchaseDetailCSupplier) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findAllDevicePurchaseDetailCSuppliers
	 *
	 */
	@Transactional
	public Set<DevicePurchaseDetailCSupplier> findAllDevicePurchaseDetailCSuppliers() throws DataAccessException {

		return findAllDevicePurchaseDetailCSuppliers(-1, -1);
	}

	/**
	 * JPQL Query - findAllDevicePurchaseDetailCSuppliers
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<DevicePurchaseDetailCSupplier> findAllDevicePurchaseDetailCSuppliers(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllDevicePurchaseDetailCSuppliers", startResult, maxRows);
		return new LinkedHashSet<DevicePurchaseDetailCSupplier>(query.getResultList());
	}

	/**
	 * JPQL Query - findDevicePurchaseDetailCSupplierById
	 *
	 */
	@Transactional
	public DevicePurchaseDetailCSupplier findDevicePurchaseDetailCSupplierById(Integer id) throws DataAccessException {

		return findDevicePurchaseDetailCSupplierById(id, -1, -1);
	}

	/**
	 * JPQL Query - findDevicePurchaseDetailCSupplierById
	 *
	 */

	@Transactional
	public DevicePurchaseDetailCSupplier findDevicePurchaseDetailCSupplierById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findDevicePurchaseDetailCSupplierById", startResult, maxRows, id);
			return (net.xidlims.domain.DevicePurchaseDetailCSupplier) query.getSingleResult();
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
	public boolean canBeMerged(DevicePurchaseDetailCSupplier entity) {
		return true;
	}
}
