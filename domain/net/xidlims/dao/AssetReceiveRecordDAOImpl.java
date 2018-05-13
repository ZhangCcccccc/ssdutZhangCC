package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.AssetReceiveRecord;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage AssetReceiveRecord entities.
 * 
 */
@Repository("AssetReceiveRecordDAO")
@Transactional
public class AssetReceiveRecordDAOImpl extends AbstractJpaDao<AssetReceiveRecord>
		implements AssetReceiveRecordDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { AssetReceiveRecord.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new AssetReceiveRecordDAOImpl
	 *
	 */
	public AssetReceiveRecordDAOImpl() {
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
	 * JPQL Query - findAssetReceiveRecordByPrimaryKey
	 *
	 */
	@Transactional
	public AssetReceiveRecord findAssetReceiveRecordByPrimaryKey(Integer id) throws DataAccessException {

		return findAssetReceiveRecordByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findAssetReceiveRecordByPrimaryKey
	 *
	 */

	@Transactional
	public AssetReceiveRecord findAssetReceiveRecordByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findAssetReceiveRecordByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.AssetReceiveRecord) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findAssetReceiveRecordByQuantity
	 *
	 */
	@Transactional
	public Set<AssetReceiveRecord> findAssetReceiveRecordByQuantity(java.math.BigDecimal quantity) throws DataAccessException {

		return findAssetReceiveRecordByQuantity(quantity, -1, -1);
	}

	/**
	 * JPQL Query - findAssetReceiveRecordByQuantity
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AssetReceiveRecord> findAssetReceiveRecordByQuantity(java.math.BigDecimal quantity, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAssetReceiveRecordByQuantity", startResult, maxRows, quantity);
		return new LinkedHashSet<AssetReceiveRecord>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllAssetReceiveRecords
	 *
	 */
	@Transactional
	public Set<AssetReceiveRecord> findAllAssetReceiveRecords() throws DataAccessException {

		return findAllAssetReceiveRecords(-1, -1);
	}

	/**
	 * JPQL Query - findAllAssetReceiveRecords
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AssetReceiveRecord> findAllAssetReceiveRecords(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllAssetReceiveRecords", startResult, maxRows);
		return new LinkedHashSet<AssetReceiveRecord>(query.getResultList());
	}

	/**
	 * JPQL Query - findAssetReceiveRecordById
	 *
	 */
	@Transactional
	public AssetReceiveRecord findAssetReceiveRecordById(Integer id) throws DataAccessException {

		return findAssetReceiveRecordById(id, -1, -1);
	}

	/**
	 * JPQL Query - findAssetReceiveRecordById
	 *
	 */

	@Transactional
	public AssetReceiveRecord findAssetReceiveRecordById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findAssetReceiveRecordById", startResult, maxRows, id);
			return (net.xidlims.domain.AssetReceiveRecord) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findAssetReceiveRecordByUnit
	 *
	 */
	@Transactional
	public Set<AssetReceiveRecord> findAssetReceiveRecordByUnit(String unit) throws DataAccessException {

		return findAssetReceiveRecordByUnit(unit, -1, -1);
	}

	/**
	 * JPQL Query - findAssetReceiveRecordByUnit
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AssetReceiveRecord> findAssetReceiveRecordByUnit(String unit, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAssetReceiveRecordByUnit", startResult, maxRows, unit);
		return new LinkedHashSet<AssetReceiveRecord>(query.getResultList());
	}

	/**
	 * JPQL Query - findAssetReceiveRecordByUnitContaining
	 *
	 */
	@Transactional
	public Set<AssetReceiveRecord> findAssetReceiveRecordByUnitContaining(String unit) throws DataAccessException {

		return findAssetReceiveRecordByUnitContaining(unit, -1, -1);
	}

	/**
	 * JPQL Query - findAssetReceiveRecordByUnitContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AssetReceiveRecord> findAssetReceiveRecordByUnitContaining(String unit, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAssetReceiveRecordByUnitContaining", startResult, maxRows, unit);
		return new LinkedHashSet<AssetReceiveRecord>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(AssetReceiveRecord entity) {
		return true;
	}
}
