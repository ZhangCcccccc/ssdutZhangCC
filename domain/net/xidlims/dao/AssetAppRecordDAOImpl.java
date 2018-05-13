package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.AssetAppRecord;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage AssetAppRecord entities.
 * 
 */
@Repository("AssetAppRecordDAO")
@Transactional
public class AssetAppRecordDAOImpl extends AbstractJpaDao<AssetAppRecord>
		implements AssetAppRecordDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { AssetAppRecord.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new AssetAppRecordDAOImpl
	 *
	 */
	public AssetAppRecordDAOImpl() {
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
	 * JPQL Query - findAssetAppRecordByAppSpec
	 *
	 */
	@Transactional
	public Set<AssetAppRecord> findAssetAppRecordByAppSpec(String appSpec) throws DataAccessException {

		return findAssetAppRecordByAppSpec(appSpec, -1, -1);
	}

	/**
	 * JPQL Query - findAssetAppRecordByAppSpec
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AssetAppRecord> findAssetAppRecordByAppSpec(String appSpec, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAssetAppRecordByAppSpec", startResult, maxRows, appSpec);
		return new LinkedHashSet<AssetAppRecord>(query.getResultList());
	}

	/**
	 * JPQL Query - findAssetAppRecordById
	 *
	 */
	@Transactional
	public AssetAppRecord findAssetAppRecordById(Integer id) throws DataAccessException {

		return findAssetAppRecordById(id, -1, -1);
	}

	/**
	 * JPQL Query - findAssetAppRecordById
	 *
	 */

	@Transactional
	public AssetAppRecord findAssetAppRecordById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findAssetAppRecordById", startResult, maxRows, id);
			return (net.xidlims.domain.AssetAppRecord) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findAssetAppRecordByAppBrand
	 *
	 */
	@Transactional
	public Set<AssetAppRecord> findAssetAppRecordByAppBrand(String appBrand) throws DataAccessException {

		return findAssetAppRecordByAppBrand(appBrand, -1, -1);
	}

	/**
	 * JPQL Query - findAssetAppRecordByAppBrand
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AssetAppRecord> findAssetAppRecordByAppBrand(String appBrand, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAssetAppRecordByAppBrand", startResult, maxRows, appBrand);
		return new LinkedHashSet<AssetAppRecord>(query.getResultList());
	}

	/**
	 * JPQL Query - findAssetAppRecordByAppUsageContaining
	 *
	 */
	@Transactional
	public Set<AssetAppRecord> findAssetAppRecordByAppUsageContaining(String appUsage) throws DataAccessException {

		return findAssetAppRecordByAppUsageContaining(appUsage, -1, -1);
	}

	/**
	 * JPQL Query - findAssetAppRecordByAppUsageContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AssetAppRecord> findAssetAppRecordByAppUsageContaining(String appUsage, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAssetAppRecordByAppUsageContaining", startResult, maxRows, appUsage);
		return new LinkedHashSet<AssetAppRecord>(query.getResultList());
	}

	/**
	 * JPQL Query - findAssetAppRecordByAppSupplier
	 *
	 */
	@Transactional
	public Set<AssetAppRecord> findAssetAppRecordByAppSupplier(Integer appSupplier) throws DataAccessException {

		return findAssetAppRecordByAppSupplier(appSupplier, -1, -1);
	}

	/**
	 * JPQL Query - findAssetAppRecordByAppSupplier
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AssetAppRecord> findAssetAppRecordByAppSupplier(Integer appSupplier, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAssetAppRecordByAppSupplier", startResult, maxRows, appSupplier);
		return new LinkedHashSet<AssetAppRecord>(query.getResultList());
	}

	/**
	 * JPQL Query - findAssetAppRecordByAppSpecContaining
	 *
	 */
	@Transactional
	public Set<AssetAppRecord> findAssetAppRecordByAppSpecContaining(String appSpec) throws DataAccessException {

		return findAssetAppRecordByAppSpecContaining(appSpec, -1, -1);
	}

	/**
	 * JPQL Query - findAssetAppRecordByAppSpecContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AssetAppRecord> findAssetAppRecordByAppSpecContaining(String appSpec, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAssetAppRecordByAppSpecContaining", startResult, maxRows, appSpec);
		return new LinkedHashSet<AssetAppRecord>(query.getResultList());
	}

	/**
	 * JPQL Query - findAssetAppRecordByAuditStatus
	 *
	 */
	@Transactional
	public Set<AssetAppRecord> findAssetAppRecordByAuditStatus(Integer auditStatus) throws DataAccessException {

		return findAssetAppRecordByAuditStatus(auditStatus, -1, -1);
	}

	/**
	 * JPQL Query - findAssetAppRecordByAuditStatus
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AssetAppRecord> findAssetAppRecordByAuditStatus(Integer auditStatus, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAssetAppRecordByAuditStatus", startResult, maxRows, auditStatus);
		return new LinkedHashSet<AssetAppRecord>(query.getResultList());
	}

	/**
	 * JPQL Query - findAssetAppRecordByPrimaryKey
	 *
	 */
	@Transactional
	public AssetAppRecord findAssetAppRecordByPrimaryKey(Integer id) throws DataAccessException {

		return findAssetAppRecordByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findAssetAppRecordByPrimaryKey
	 *
	 */

	@Transactional
	public AssetAppRecord findAssetAppRecordByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findAssetAppRecordByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.AssetAppRecord) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findAssetAppRecordByMem
	 *
	 */
	@Transactional
	public Set<AssetAppRecord> findAssetAppRecordByMem(String mem) throws DataAccessException {

		return findAssetAppRecordByMem(mem, -1, -1);
	}

	/**
	 * JPQL Query - findAssetAppRecordByMem
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AssetAppRecord> findAssetAppRecordByMem(String mem, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAssetAppRecordByMem", startResult, maxRows, mem);
		return new LinkedHashSet<AssetAppRecord>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllAssetAppRecords
	 *
	 */
	@Transactional
	public Set<AssetAppRecord> findAllAssetAppRecords() throws DataAccessException {

		return findAllAssetAppRecords(-1, -1);
	}

	/**
	 * JPQL Query - findAllAssetAppRecords
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AssetAppRecord> findAllAssetAppRecords(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllAssetAppRecords", startResult, maxRows);
		return new LinkedHashSet<AssetAppRecord>(query.getResultList());
	}

	/**
	 * JPQL Query - findAssetAppRecordByAppQuantity
	 *
	 */
	@Transactional
	public Set<AssetAppRecord> findAssetAppRecordByAppQuantity(Integer appQuantity) throws DataAccessException {

		return findAssetAppRecordByAppQuantity(appQuantity, -1, -1);
	}

	/**
	 * JPQL Query - findAssetAppRecordByAppQuantity
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AssetAppRecord> findAssetAppRecordByAppQuantity(Integer appQuantity, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAssetAppRecordByAppQuantity", startResult, maxRows, appQuantity);
		return new LinkedHashSet<AssetAppRecord>(query.getResultList());
	}

	/**
	 * JPQL Query - findAssetAppRecordByAppPrice
	 *
	 */
	@Transactional
	public Set<AssetAppRecord> findAssetAppRecordByAppPrice(java.math.BigDecimal appPrice) throws DataAccessException {

		return findAssetAppRecordByAppPrice(appPrice, -1, -1);
	}

	/**
	 * JPQL Query - findAssetAppRecordByAppPrice
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AssetAppRecord> findAssetAppRecordByAppPrice(java.math.BigDecimal appPrice, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAssetAppRecordByAppPrice", startResult, maxRows, appPrice);
		return new LinkedHashSet<AssetAppRecord>(query.getResultList());
	}

	/**
	 * JPQL Query - findAssetAppRecordByAppBrandContaining
	 *
	 */
	@Transactional
	public Set<AssetAppRecord> findAssetAppRecordByAppBrandContaining(String appBrand) throws DataAccessException {

		return findAssetAppRecordByAppBrandContaining(appBrand, -1, -1);
	}

	/**
	 * JPQL Query - findAssetAppRecordByAppBrandContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AssetAppRecord> findAssetAppRecordByAppBrandContaining(String appBrand, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAssetAppRecordByAppBrandContaining", startResult, maxRows, appBrand);
		return new LinkedHashSet<AssetAppRecord>(query.getResultList());
	}

	/**
	 * JPQL Query - findAssetAppRecordByLabId
	 *
	 */
	@Transactional
	public Set<AssetAppRecord> findAssetAppRecordByLabId(Integer labId) throws DataAccessException {

		return findAssetAppRecordByLabId(labId, -1, -1);
	}

	/**
	 * JPQL Query - findAssetAppRecordByLabId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AssetAppRecord> findAssetAppRecordByLabId(Integer labId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAssetAppRecordByLabId", startResult, maxRows, labId);
		return new LinkedHashSet<AssetAppRecord>(query.getResultList());
	}

	/**
	 * JPQL Query - findAssetAppRecordByAppUsage
	 *
	 */
	@Transactional
	public Set<AssetAppRecord> findAssetAppRecordByAppUsage(String appUsage) throws DataAccessException {

		return findAssetAppRecordByAppUsage(appUsage, -1, -1);
	}

	/**
	 * JPQL Query - findAssetAppRecordByAppUsage
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AssetAppRecord> findAssetAppRecordByAppUsage(String appUsage, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAssetAppRecordByAppUsage", startResult, maxRows, appUsage);
		return new LinkedHashSet<AssetAppRecord>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(AssetAppRecord entity) {
		return true;
	}
}
