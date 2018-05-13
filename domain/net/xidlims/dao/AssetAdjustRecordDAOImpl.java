package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.AssetAdjustRecord;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage AssetAdjustRecord entities.
 * 
 */
@Repository("AssetAdjustRecordDAO")
@Transactional
public class AssetAdjustRecordDAOImpl extends AbstractJpaDao<AssetAdjustRecord>
		implements AssetAdjustRecordDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { AssetAdjustRecord.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new AssetAdjustRecordDAOImpl
	 *
	 */
	public AssetAdjustRecordDAOImpl() {
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
	 * JPQL Query - findAssetAdjustRecordByType
	 *
	 */
	@Transactional
	public Set<AssetAdjustRecord> findAssetAdjustRecordByType(Integer type) throws DataAccessException {

		return findAssetAdjustRecordByType(type, -1, -1);
	}

	/**
	 * JPQL Query - findAssetAdjustRecordByType
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AssetAdjustRecord> findAssetAdjustRecordByType(Integer type, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAssetAdjustRecordByType", startResult, maxRows, type);
		return new LinkedHashSet<AssetAdjustRecord>(query.getResultList());
	}

	/**
	 * JPQL Query - findAssetAdjustRecordByUnitContaining
	 *
	 */
	@Transactional
	public Set<AssetAdjustRecord> findAssetAdjustRecordByUnitContaining(String unit) throws DataAccessException {

		return findAssetAdjustRecordByUnitContaining(unit, -1, -1);
	}

	/**
	 * JPQL Query - findAssetAdjustRecordByUnitContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AssetAdjustRecord> findAssetAdjustRecordByUnitContaining(String unit, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAssetAdjustRecordByUnitContaining", startResult, maxRows, unit);
		return new LinkedHashSet<AssetAdjustRecord>(query.getResultList());
	}

	/**
	 * JPQL Query - findAssetAdjustRecordByUnit
	 *
	 */
	@Transactional
	public Set<AssetAdjustRecord> findAssetAdjustRecordByUnit(String unit) throws DataAccessException {

		return findAssetAdjustRecordByUnit(unit, -1, -1);
	}

	/**
	 * JPQL Query - findAssetAdjustRecordByUnit
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AssetAdjustRecord> findAssetAdjustRecordByUnit(String unit, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAssetAdjustRecordByUnit", startResult, maxRows, unit);
		return new LinkedHashSet<AssetAdjustRecord>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllAssetAdjustRecords
	 *
	 */
	@Transactional
	public Set<AssetAdjustRecord> findAllAssetAdjustRecords() throws DataAccessException {

		return findAllAssetAdjustRecords(-1, -1);
	}

	/**
	 * JPQL Query - findAllAssetAdjustRecords
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AssetAdjustRecord> findAllAssetAdjustRecords(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllAssetAdjustRecords", startResult, maxRows);
		return new LinkedHashSet<AssetAdjustRecord>(query.getResultList());
	}

	/**
	 * JPQL Query - findAssetAdjustRecordByPrimaryKey
	 *
	 */
	@Transactional
	public AssetAdjustRecord findAssetAdjustRecordByPrimaryKey(Integer id) throws DataAccessException {

		return findAssetAdjustRecordByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findAssetAdjustRecordByPrimaryKey
	 *
	 */

	@Transactional
	public AssetAdjustRecord findAssetAdjustRecordByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findAssetAdjustRecordByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.AssetAdjustRecord) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findAssetAdjustRecordById
	 *
	 */
	@Transactional
	public AssetAdjustRecord findAssetAdjustRecordById(Integer id) throws DataAccessException {

		return findAssetAdjustRecordById(id, -1, -1);
	}

	/**
	 * JPQL Query - findAssetAdjustRecordById
	 *
	 */

	@Transactional
	public AssetAdjustRecord findAssetAdjustRecordById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findAssetAdjustRecordById", startResult, maxRows, id);
			return (net.xidlims.domain.AssetAdjustRecord) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findAssetAdjustRecordByQuantity
	 *
	 */
	@Transactional
	public Set<AssetAdjustRecord> findAssetAdjustRecordByQuantity(java.math.BigDecimal quantity) throws DataAccessException {

		return findAssetAdjustRecordByQuantity(quantity, -1, -1);
	}

	/**
	 * JPQL Query - findAssetAdjustRecordByQuantity
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AssetAdjustRecord> findAssetAdjustRecordByQuantity(java.math.BigDecimal quantity, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAssetAdjustRecordByQuantity", startResult, maxRows, quantity);
		return new LinkedHashSet<AssetAdjustRecord>(query.getResultList());
	}

	/**
	 * JPQL Query - findAssetAdjustRecordByDate
	 *
	 */
	@Transactional
	public Set<AssetAdjustRecord> findAssetAdjustRecordByDate(java.util.Calendar date) throws DataAccessException {

		return findAssetAdjustRecordByDate(date, -1, -1);
	}

	/**
	 * JPQL Query - findAssetAdjustRecordByDate
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AssetAdjustRecord> findAssetAdjustRecordByDate(java.util.Calendar date, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAssetAdjustRecordByDate", startResult, maxRows, date);
		return new LinkedHashSet<AssetAdjustRecord>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(AssetAdjustRecord entity) {
		return true;
	}
}
