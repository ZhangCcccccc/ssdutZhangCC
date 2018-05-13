package net.xidlims.dao;

import java.math.BigDecimal;

import java.util.Set;

import net.xidlims.domain.AssetCabinetWarehouseAccessRecord;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage AssetCabinetWarehouseAccessRecord entities.
 * 
 */
public interface AssetCabinetWarehouseAccessRecordDAO extends
		JpaDao<AssetCabinetWarehouseAccessRecord> {

	/**
	 * JPQL Query - findAllAssetCabinetWarehouseAccessRecords
	 *
	 */
	public Set<AssetCabinetWarehouseAccessRecord> findAllAssetCabinetWarehouseAccessRecords() throws DataAccessException;

	/**
	 * JPQL Query - findAllAssetCabinetWarehouseAccessRecords
	 *
	 */
	public Set<AssetCabinetWarehouseAccessRecord> findAllAssetCabinetWarehouseAccessRecords(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAssetCabinetWarehouseAccessRecordByCabinetQuantity
	 *
	 */
	public Set<AssetCabinetWarehouseAccessRecord> findAssetCabinetWarehouseAccessRecordByCabinetQuantity(java.math.BigDecimal cabinetQuantity) throws DataAccessException;

	/**
	 * JPQL Query - findAssetCabinetWarehouseAccessRecordByCabinetQuantity
	 *
	 */
	public Set<AssetCabinetWarehouseAccessRecord> findAssetCabinetWarehouseAccessRecordByCabinetQuantity(BigDecimal cabinetQuantity, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAssetCabinetWarehouseAccessRecordById
	 *
	 */
	public AssetCabinetWarehouseAccessRecord findAssetCabinetWarehouseAccessRecordById(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findAssetCabinetWarehouseAccessRecordById
	 *
	 */
	public AssetCabinetWarehouseAccessRecord findAssetCabinetWarehouseAccessRecordById(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAssetCabinetWarehouseAccessRecordByPrimaryKey
	 *
	 */
	public AssetCabinetWarehouseAccessRecord findAssetCabinetWarehouseAccessRecordByPrimaryKey(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findAssetCabinetWarehouseAccessRecordByPrimaryKey
	 *
	 */
	public AssetCabinetWarehouseAccessRecord findAssetCabinetWarehouseAccessRecordByPrimaryKey(Integer id_1, int startResult, int maxRows) throws DataAccessException;

}