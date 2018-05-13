package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.OperationItemDevice;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage OperationItemDevice entities.
 * 
 */
@Repository("OperationItemDeviceDAO")
@Transactional
public class OperationItemDeviceDAOImpl extends AbstractJpaDao<OperationItemDevice>
		implements OperationItemDeviceDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { OperationItemDevice.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new OperationItemDeviceDAOImpl
	 *
	 */
	public OperationItemDeviceDAOImpl() {
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
	 * JPQL Query - findAllOperationItemDevices
	 *
	 */
	@Transactional
	public Set<OperationItemDevice> findAllOperationItemDevices() throws DataAccessException {

		return findAllOperationItemDevices(-1, -1);
	}

	/**
	 * JPQL Query - findAllOperationItemDevices
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<OperationItemDevice> findAllOperationItemDevices(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllOperationItemDevices", startResult, maxRows);
		return new LinkedHashSet<OperationItemDevice>(query.getResultList());
	}

	/**
	 * JPQL Query - findOperationItemDeviceByPrimaryKey
	 *
	 */
	@Transactional
	public OperationItemDevice findOperationItemDeviceByPrimaryKey(Integer id) throws DataAccessException {

		return findOperationItemDeviceByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findOperationItemDeviceByPrimaryKey
	 *
	 */

	@Transactional
	public OperationItemDevice findOperationItemDeviceByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findOperationItemDeviceByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.OperationItemDevice) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findOperationItemDeviceById
	 *
	 */
	@Transactional
	public OperationItemDevice findOperationItemDeviceById(Integer id) throws DataAccessException {

		return findOperationItemDeviceById(id, -1, -1);
	}

	/**
	 * JPQL Query - findOperationItemDeviceById
	 *
	 */

	@Transactional
	public OperationItemDevice findOperationItemDeviceById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findOperationItemDeviceById", startResult, maxRows, id);
			return (net.xidlims.domain.OperationItemDevice) query.getSingleResult();
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
	public boolean canBeMerged(OperationItemDevice entity) {
		return true;
	}
}
