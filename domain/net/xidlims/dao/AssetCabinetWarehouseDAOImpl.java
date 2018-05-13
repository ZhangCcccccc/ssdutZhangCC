package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.AssetCabinetWarehouse;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage AssetCabinetWarehouse entities.
 * 
 */
@Repository("AssetCabinetWarehouseDAO")
@Transactional
public class AssetCabinetWarehouseDAOImpl extends AbstractJpaDao<AssetCabinetWarehouse>
		implements AssetCabinetWarehouseDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { AssetCabinetWarehouse.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new AssetCabinetWarehouseDAOImpl
	 *
	 */
	public AssetCabinetWarehouseDAOImpl() {
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
	 * JPQL Query - findAssetCabinetWarehouseByWarehouseName
	 *
	 */
	@Transactional
	public Set<AssetCabinetWarehouse> findAssetCabinetWarehouseByWarehouseName(String warehouseName) throws DataAccessException {

		return findAssetCabinetWarehouseByWarehouseName(warehouseName, -1, -1);
	}

	/**
	 * JPQL Query - findAssetCabinetWarehouseByWarehouseName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AssetCabinetWarehouse> findAssetCabinetWarehouseByWarehouseName(String warehouseName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAssetCabinetWarehouseByWarehouseName", startResult, maxRows, warehouseName);
		return new LinkedHashSet<AssetCabinetWarehouse>(query.getResultList());
	}

	/**
	 * JPQL Query - findAssetCabinetWarehouseByWarehouseCode
	 *
	 */
	@Transactional
	public Set<AssetCabinetWarehouse> findAssetCabinetWarehouseByWarehouseCode(String warehouseCode) throws DataAccessException {

		return findAssetCabinetWarehouseByWarehouseCode(warehouseCode, -1, -1);
	}

	/**
	 * JPQL Query - findAssetCabinetWarehouseByWarehouseCode
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AssetCabinetWarehouse> findAssetCabinetWarehouseByWarehouseCode(String warehouseCode, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAssetCabinetWarehouseByWarehouseCode", startResult, maxRows, warehouseCode);
		return new LinkedHashSet<AssetCabinetWarehouse>(query.getResultList());
	}

	/**
	 * JPQL Query - findAssetCabinetWarehouseByWarehouseNameContaining
	 *
	 */
	@Transactional
	public Set<AssetCabinetWarehouse> findAssetCabinetWarehouseByWarehouseNameContaining(String warehouseName) throws DataAccessException {

		return findAssetCabinetWarehouseByWarehouseNameContaining(warehouseName, -1, -1);
	}

	/**
	 * JPQL Query - findAssetCabinetWarehouseByWarehouseNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AssetCabinetWarehouse> findAssetCabinetWarehouseByWarehouseNameContaining(String warehouseName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAssetCabinetWarehouseByWarehouseNameContaining", startResult, maxRows, warehouseName);
		return new LinkedHashSet<AssetCabinetWarehouse>(query.getResultList());
	}

	/**
	 * JPQL Query - findAssetCabinetWarehouseByPrimaryKey
	 *
	 */
	@Transactional
	public AssetCabinetWarehouse findAssetCabinetWarehouseByPrimaryKey(Integer id) throws DataAccessException {

		return findAssetCabinetWarehouseByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findAssetCabinetWarehouseByPrimaryKey
	 *
	 */

	@Transactional
	public AssetCabinetWarehouse findAssetCabinetWarehouseByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findAssetCabinetWarehouseByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.AssetCabinetWarehouse) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findAllAssetCabinetWarehouses
	 *
	 */
	@Transactional
	public Set<AssetCabinetWarehouse> findAllAssetCabinetWarehouses() throws DataAccessException {

		return findAllAssetCabinetWarehouses(-1, -1);
	}

	/**
	 * JPQL Query - findAllAssetCabinetWarehouses
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AssetCabinetWarehouse> findAllAssetCabinetWarehouses(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllAssetCabinetWarehouses", startResult, maxRows);
		return new LinkedHashSet<AssetCabinetWarehouse>(query.getResultList());
	}

	/**
	 * JPQL Query - findAssetCabinetWarehouseById
	 *
	 */
	@Transactional
	public AssetCabinetWarehouse findAssetCabinetWarehouseById(Integer id) throws DataAccessException {

		return findAssetCabinetWarehouseById(id, -1, -1);
	}

	/**
	 * JPQL Query - findAssetCabinetWarehouseById
	 *
	 */

	@Transactional
	public AssetCabinetWarehouse findAssetCabinetWarehouseById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findAssetCabinetWarehouseById", startResult, maxRows, id);
			return (net.xidlims.domain.AssetCabinetWarehouse) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findAssetCabinetWarehouseByWarehouseCodeContaining
	 *
	 */
	@Transactional
	public Set<AssetCabinetWarehouse> findAssetCabinetWarehouseByWarehouseCodeContaining(String warehouseCode) throws DataAccessException {

		return findAssetCabinetWarehouseByWarehouseCodeContaining(warehouseCode, -1, -1);
	}

	/**
	 * JPQL Query - findAssetCabinetWarehouseByWarehouseCodeContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AssetCabinetWarehouse> findAssetCabinetWarehouseByWarehouseCodeContaining(String warehouseCode, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAssetCabinetWarehouseByWarehouseCodeContaining", startResult, maxRows, warehouseCode);
		return new LinkedHashSet<AssetCabinetWarehouse>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(AssetCabinetWarehouse entity) {
		return true;
	}
}
