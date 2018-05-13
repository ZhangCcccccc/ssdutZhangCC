package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.AssetCabinetWarehouseAccessRecord;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage AssetCabinetWarehouseAccessRecord entities.
 * 
 */
@Repository("AssetCabinetWarehouseAccessRecordDAO")
@Transactional
public class AssetCabinetWarehouseAccessRecordDAOImpl extends AbstractJpaDao<AssetCabinetWarehouseAccessRecord>
		implements AssetCabinetWarehouseAccessRecordDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { AssetCabinetWarehouseAccessRecord.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new AssetCabinetWarehouseAccessRecordDAOImpl
	 *
	 */
	public AssetCabinetWarehouseAccessRecordDAOImpl() {
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
	 * JPQL Query - findAllAssetCabinetWarehouseAccessRecords
	 *
	 */
	@Transactional
	public Set<AssetCabinetWarehouseAccessRecord> findAllAssetCabinetWarehouseAccessRecords() throws DataAccessException {

		return findAllAssetCabinetWarehouseAccessRecords(-1, -1);
	}

	/**
	 * JPQL Query - findAllAssetCabinetWarehouseAccessRecords
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AssetCabinetWarehouseAccessRecord> findAllAssetCabinetWarehouseAccessRecords(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllAssetCabinetWarehouseAccessRecords", startResult, maxRows);
		return new LinkedHashSet<AssetCabinetWarehouseAccessRecord>(query.getResultList());
	}

	/**
	 * JPQL Query - findAssetCabinetWarehouseAccessRecordByCabinetQuantity
	 *
	 */
	@Transactional
	public Set<AssetCabinetWarehouseAccessRecord> findAssetCabinetWarehouseAccessRecordByCabinetQuantity(java.math.BigDecimal cabinetQuantity) throws DataAccessException {

		return findAssetCabinetWarehouseAccessRecordByCabinetQuantity(cabinetQuantity, -1, -1);
	}

	/**
	 * JPQL Query - findAssetCabinetWarehouseAccessRecordByCabinetQuantity
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AssetCabinetWarehouseAccessRecord> findAssetCabinetWarehouseAccessRecordByCabinetQuantity(java.math.BigDecimal cabinetQuantity, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAssetCabinetWarehouseAccessRecordByCabinetQuantity", startResult, maxRows, cabinetQuantity);
		return new LinkedHashSet<AssetCabinetWarehouseAccessRecord>(query.getResultList());
	}

	/**
	 * JPQL Query - findAssetCabinetWarehouseAccessRecordById
	 *
	 */
	@Transactional
	public AssetCabinetWarehouseAccessRecord findAssetCabinetWarehouseAccessRecordById(Integer id) throws DataAccessException {

		return findAssetCabinetWarehouseAccessRecordById(id, -1, -1);
	}

	/**
	 * JPQL Query - findAssetCabinetWarehouseAccessRecordById
	 *
	 */

	@Transactional
	public AssetCabinetWarehouseAccessRecord findAssetCabinetWarehouseAccessRecordById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findAssetCabinetWarehouseAccessRecordById", startResult, maxRows, id);
			return (net.xidlims.domain.AssetCabinetWarehouseAccessRecord) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findAssetCabinetWarehouseAccessRecordByPrimaryKey
	 *
	 */
	@Transactional
	public AssetCabinetWarehouseAccessRecord findAssetCabinetWarehouseAccessRecordByPrimaryKey(Integer id) throws DataAccessException {

		return findAssetCabinetWarehouseAccessRecordByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findAssetCabinetWarehouseAccessRecordByPrimaryKey
	 *
	 */

	@Transactional
	public AssetCabinetWarehouseAccessRecord findAssetCabinetWarehouseAccessRecordByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findAssetCabinetWarehouseAccessRecordByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.AssetCabinetWarehouseAccessRecord) query.getSingleResult();
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
	public boolean canBeMerged(AssetCabinetWarehouseAccessRecord entity) {
		return true;
	}
}
