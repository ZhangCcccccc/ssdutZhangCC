package net.xidlims.dao;

import java.math.BigDecimal;

import java.util.Calendar;
import java.util.Set;

import net.xidlims.domain.AssetAdjustRecord;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage AssetAdjustRecord entities.
 * 
 */
public interface AssetAdjustRecordDAO extends JpaDao<AssetAdjustRecord> {

	/**
	 * JPQL Query - findAssetAdjustRecordByType
	 *
	 */
	public Set<AssetAdjustRecord> findAssetAdjustRecordByType(Integer type) throws DataAccessException;

	/**
	 * JPQL Query - findAssetAdjustRecordByType
	 *
	 */
	public Set<AssetAdjustRecord> findAssetAdjustRecordByType(Integer type, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAssetAdjustRecordByUnitContaining
	 *
	 */
	public Set<AssetAdjustRecord> findAssetAdjustRecordByUnitContaining(String unit) throws DataAccessException;

	/**
	 * JPQL Query - findAssetAdjustRecordByUnitContaining
	 *
	 */
	public Set<AssetAdjustRecord> findAssetAdjustRecordByUnitContaining(String unit, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAssetAdjustRecordByUnit
	 *
	 */
	public Set<AssetAdjustRecord> findAssetAdjustRecordByUnit(String unit_1) throws DataAccessException;

	/**
	 * JPQL Query - findAssetAdjustRecordByUnit
	 *
	 */
	public Set<AssetAdjustRecord> findAssetAdjustRecordByUnit(String unit_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllAssetAdjustRecords
	 *
	 */
	public Set<AssetAdjustRecord> findAllAssetAdjustRecords() throws DataAccessException;

	/**
	 * JPQL Query - findAllAssetAdjustRecords
	 *
	 */
	public Set<AssetAdjustRecord> findAllAssetAdjustRecords(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAssetAdjustRecordByPrimaryKey
	 *
	 */
	public AssetAdjustRecord findAssetAdjustRecordByPrimaryKey(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findAssetAdjustRecordByPrimaryKey
	 *
	 */
	public AssetAdjustRecord findAssetAdjustRecordByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAssetAdjustRecordById
	 *
	 */
	public AssetAdjustRecord findAssetAdjustRecordById(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findAssetAdjustRecordById
	 *
	 */
	public AssetAdjustRecord findAssetAdjustRecordById(Integer id_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAssetAdjustRecordByQuantity
	 *
	 */
	public Set<AssetAdjustRecord> findAssetAdjustRecordByQuantity(java.math.BigDecimal quantity) throws DataAccessException;

	/**
	 * JPQL Query - findAssetAdjustRecordByQuantity
	 *
	 */
	public Set<AssetAdjustRecord> findAssetAdjustRecordByQuantity(BigDecimal quantity, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAssetAdjustRecordByDate
	 *
	 */
	public Set<AssetAdjustRecord> findAssetAdjustRecordByDate(java.util.Calendar date) throws DataAccessException;

	/**
	 * JPQL Query - findAssetAdjustRecordByDate
	 *
	 */
	public Set<AssetAdjustRecord> findAssetAdjustRecordByDate(Calendar date, int startResult, int maxRows) throws DataAccessException;

}