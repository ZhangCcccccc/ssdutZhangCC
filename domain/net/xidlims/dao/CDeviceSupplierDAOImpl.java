package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.CDeviceSupplier;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage CDeviceSupplier entities.
 * 
 */
@Repository("CDeviceSupplierDAO")
@Transactional
public class CDeviceSupplierDAOImpl extends AbstractJpaDao<CDeviceSupplier>
		implements CDeviceSupplierDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { CDeviceSupplier.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new CDeviceSupplierDAOImpl
	 *
	 */
	public CDeviceSupplierDAOImpl() {
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
	 * JPQL Query - findCDeviceSupplierById
	 *
	 */
	@Transactional
	public CDeviceSupplier findCDeviceSupplierById(Integer id) throws DataAccessException {

		return findCDeviceSupplierById(id, -1, -1);
	}

	/**
	 * JPQL Query - findCDeviceSupplierById
	 *
	 */

	@Transactional
	public CDeviceSupplier findCDeviceSupplierById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findCDeviceSupplierById", startResult, maxRows, id);
			return (net.xidlims.domain.CDeviceSupplier) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findCDeviceSupplierByDevicName
	 *
	 */
	@Transactional
	public Set<CDeviceSupplier> findCDeviceSupplierByDevicName(String devicName) throws DataAccessException {

		return findCDeviceSupplierByDevicName(devicName, -1, -1);
	}

	/**
	 * JPQL Query - findCDeviceSupplierByDevicName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CDeviceSupplier> findCDeviceSupplierByDevicName(String devicName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCDeviceSupplierByDevicName", startResult, maxRows, devicName);
		return new LinkedHashSet<CDeviceSupplier>(query.getResultList());
	}

	/**
	 * JPQL Query - findCDeviceSupplierByDevicNameContaining
	 *
	 */
	@Transactional
	public Set<CDeviceSupplier> findCDeviceSupplierByDevicNameContaining(String devicName) throws DataAccessException {

		return findCDeviceSupplierByDevicNameContaining(devicName, -1, -1);
	}

	/**
	 * JPQL Query - findCDeviceSupplierByDevicNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CDeviceSupplier> findCDeviceSupplierByDevicNameContaining(String devicName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCDeviceSupplierByDevicNameContaining", startResult, maxRows, devicName);
		return new LinkedHashSet<CDeviceSupplier>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllCDeviceSuppliers
	 *
	 */
	@Transactional
	public Set<CDeviceSupplier> findAllCDeviceSuppliers() throws DataAccessException {

		return findAllCDeviceSuppliers(-1, -1);
	}

	/**
	 * JPQL Query - findAllCDeviceSuppliers
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CDeviceSupplier> findAllCDeviceSuppliers(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllCDeviceSuppliers", startResult, maxRows);
		return new LinkedHashSet<CDeviceSupplier>(query.getResultList());
	}

	/**
	 * JPQL Query - findCDeviceSupplierByPrimaryKey
	 *
	 */
	@Transactional
	public CDeviceSupplier findCDeviceSupplierByPrimaryKey(Integer id) throws DataAccessException {

		return findCDeviceSupplierByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findCDeviceSupplierByPrimaryKey
	 *
	 */

	@Transactional
	public CDeviceSupplier findCDeviceSupplierByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findCDeviceSupplierByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.CDeviceSupplier) query.getSingleResult();
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
	public boolean canBeMerged(CDeviceSupplier entity) {
		return true;
	}
}
