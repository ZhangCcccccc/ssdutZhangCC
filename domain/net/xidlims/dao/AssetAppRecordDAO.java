package net.xidlims.dao;

import java.math.BigDecimal;

import java.util.Set;

import net.xidlims.domain.AssetAppRecord;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage AssetAppRecord entities.
 * 
 */
public interface AssetAppRecordDAO extends JpaDao<AssetAppRecord> {

	/**
	 * JPQL Query - findAssetAppRecordByAppSpec
	 *
	 */
	public Set<AssetAppRecord> findAssetAppRecordByAppSpec(String appSpec) throws DataAccessException;

	/**
	 * JPQL Query - findAssetAppRecordByAppSpec
	 *
	 */
	public Set<AssetAppRecord> findAssetAppRecordByAppSpec(String appSpec, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAssetAppRecordById
	 *
	 */
	public AssetAppRecord findAssetAppRecordById(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findAssetAppRecordById
	 *
	 */
	public AssetAppRecord findAssetAppRecordById(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAssetAppRecordByAppBrand
	 *
	 */
	public Set<AssetAppRecord> findAssetAppRecordByAppBrand(String appBrand) throws DataAccessException;

	/**
	 * JPQL Query - findAssetAppRecordByAppBrand
	 *
	 */
	public Set<AssetAppRecord> findAssetAppRecordByAppBrand(String appBrand, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAssetAppRecordByAppUsageContaining
	 *
	 */
	public Set<AssetAppRecord> findAssetAppRecordByAppUsageContaining(String appUsage) throws DataAccessException;

	/**
	 * JPQL Query - findAssetAppRecordByAppUsageContaining
	 *
	 */
	public Set<AssetAppRecord> findAssetAppRecordByAppUsageContaining(String appUsage, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAssetAppRecordByAppSupplier
	 *
	 */
	public Set<AssetAppRecord> findAssetAppRecordByAppSupplier(Integer appSupplier) throws DataAccessException;

	/**
	 * JPQL Query - findAssetAppRecordByAppSupplier
	 *
	 */
	public Set<AssetAppRecord> findAssetAppRecordByAppSupplier(Integer appSupplier, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAssetAppRecordByAppSpecContaining
	 *
	 */
	public Set<AssetAppRecord> findAssetAppRecordByAppSpecContaining(String appSpec_1) throws DataAccessException;

	/**
	 * JPQL Query - findAssetAppRecordByAppSpecContaining
	 *
	 */
	public Set<AssetAppRecord> findAssetAppRecordByAppSpecContaining(String appSpec_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAssetAppRecordByAuditStatus
	 *
	 */
	public Set<AssetAppRecord> findAssetAppRecordByAuditStatus(Integer auditStatus) throws DataAccessException;

	/**
	 * JPQL Query - findAssetAppRecordByAuditStatus
	 *
	 */
	public Set<AssetAppRecord> findAssetAppRecordByAuditStatus(Integer auditStatus, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAssetAppRecordByPrimaryKey
	 *
	 */
	public AssetAppRecord findAssetAppRecordByPrimaryKey(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findAssetAppRecordByPrimaryKey
	 *
	 */
	public AssetAppRecord findAssetAppRecordByPrimaryKey(Integer id_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAssetAppRecordByMem
	 *
	 */
	public Set<AssetAppRecord> findAssetAppRecordByMem(String mem) throws DataAccessException;

	/**
	 * JPQL Query - findAssetAppRecordByMem
	 *
	 */
	public Set<AssetAppRecord> findAssetAppRecordByMem(String mem, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllAssetAppRecords
	 *
	 */
	public Set<AssetAppRecord> findAllAssetAppRecords() throws DataAccessException;

	/**
	 * JPQL Query - findAllAssetAppRecords
	 *
	 */
	public Set<AssetAppRecord> findAllAssetAppRecords(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAssetAppRecordByAppQuantity
	 *
	 */
	public Set<AssetAppRecord> findAssetAppRecordByAppQuantity(Integer appQuantity) throws DataAccessException;

	/**
	 * JPQL Query - findAssetAppRecordByAppQuantity
	 *
	 */
	public Set<AssetAppRecord> findAssetAppRecordByAppQuantity(Integer appQuantity, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAssetAppRecordByAppPrice
	 *
	 */
	public Set<AssetAppRecord> findAssetAppRecordByAppPrice(java.math.BigDecimal appPrice) throws DataAccessException;

	/**
	 * JPQL Query - findAssetAppRecordByAppPrice
	 *
	 */
	public Set<AssetAppRecord> findAssetAppRecordByAppPrice(BigDecimal appPrice, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAssetAppRecordByAppBrandContaining
	 *
	 */
	public Set<AssetAppRecord> findAssetAppRecordByAppBrandContaining(String appBrand_1) throws DataAccessException;

	/**
	 * JPQL Query - findAssetAppRecordByAppBrandContaining
	 *
	 */
	public Set<AssetAppRecord> findAssetAppRecordByAppBrandContaining(String appBrand_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAssetAppRecordByLabId
	 *
	 */
	public Set<AssetAppRecord> findAssetAppRecordByLabId(Integer labId) throws DataAccessException;

	/**
	 * JPQL Query - findAssetAppRecordByLabId
	 *
	 */
	public Set<AssetAppRecord> findAssetAppRecordByLabId(Integer labId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAssetAppRecordByAppUsage
	 *
	 */
	public Set<AssetAppRecord> findAssetAppRecordByAppUsage(String appUsage_1) throws DataAccessException;

	/**
	 * JPQL Query - findAssetAppRecordByAppUsage
	 *
	 */
	public Set<AssetAppRecord> findAssetAppRecordByAppUsage(String appUsage_1, int startResult, int maxRows) throws DataAccessException;

}