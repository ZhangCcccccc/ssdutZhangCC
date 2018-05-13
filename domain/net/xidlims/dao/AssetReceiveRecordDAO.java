package net.xidlims.dao;

import java.math.BigDecimal;

import java.util.Set;

import net.xidlims.domain.AssetReceiveRecord;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage AssetReceiveRecord entities.
 * 
 */
public interface AssetReceiveRecordDAO extends JpaDao<AssetReceiveRecord> {

	/**
	 * JPQL Query - findAssetReceiveRecordByPrimaryKey
	 *
	 */
	public AssetReceiveRecord findAssetReceiveRecordByPrimaryKey(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findAssetReceiveRecordByPrimaryKey
	 *
	 */
	public AssetReceiveRecord findAssetReceiveRecordByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAssetReceiveRecordByQuantity
	 *
	 */
	public Set<AssetReceiveRecord> findAssetReceiveRecordByQuantity(java.math.BigDecimal quantity) throws DataAccessException;

	/**
	 * JPQL Query - findAssetReceiveRecordByQuantity
	 *
	 */
	public Set<AssetReceiveRecord> findAssetReceiveRecordByQuantity(BigDecimal quantity, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllAssetReceiveRecords
	 *
	 */
	public Set<AssetReceiveRecord> findAllAssetReceiveRecords() throws DataAccessException;

	/**
	 * JPQL Query - findAllAssetReceiveRecords
	 *
	 */
	public Set<AssetReceiveRecord> findAllAssetReceiveRecords(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAssetReceiveRecordById
	 *
	 */
	public AssetReceiveRecord findAssetReceiveRecordById(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findAssetReceiveRecordById
	 *
	 */
	public AssetReceiveRecord findAssetReceiveRecordById(Integer id_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAssetReceiveRecordByUnit
	 *
	 */
	public Set<AssetReceiveRecord> findAssetReceiveRecordByUnit(String unit) throws DataAccessException;

	/**
	 * JPQL Query - findAssetReceiveRecordByUnit
	 *
	 */
	public Set<AssetReceiveRecord> findAssetReceiveRecordByUnit(String unit, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAssetReceiveRecordByUnitContaining
	 *
	 */
	public Set<AssetReceiveRecord> findAssetReceiveRecordByUnitContaining(String unit_1) throws DataAccessException;

	/**
	 * JPQL Query - findAssetReceiveRecordByUnitContaining
	 *
	 */
	public Set<AssetReceiveRecord> findAssetReceiveRecordByUnitContaining(String unit_1, int startResult, int maxRows) throws DataAccessException;

}