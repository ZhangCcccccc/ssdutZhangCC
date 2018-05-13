package net.xidlims.dao;

import java.util.Set;

import net.xidlims.domain.AssetCabinetWarehouse;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage AssetCabinetWarehouse entities.
 * 
 */
public interface AssetCabinetWarehouseDAO extends JpaDao<AssetCabinetWarehouse> {

	/**
	 * JPQL Query - findAssetCabinetWarehouseByWarehouseName
	 *
	 */
	public Set<AssetCabinetWarehouse> findAssetCabinetWarehouseByWarehouseName(String warehouseName) throws DataAccessException;

	/**
	 * JPQL Query - findAssetCabinetWarehouseByWarehouseName
	 *
	 */
	public Set<AssetCabinetWarehouse> findAssetCabinetWarehouseByWarehouseName(String warehouseName, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAssetCabinetWarehouseByWarehouseCode
	 *
	 */
	public Set<AssetCabinetWarehouse> findAssetCabinetWarehouseByWarehouseCode(String warehouseCode) throws DataAccessException;

	/**
	 * JPQL Query - findAssetCabinetWarehouseByWarehouseCode
	 *
	 */
	public Set<AssetCabinetWarehouse> findAssetCabinetWarehouseByWarehouseCode(String warehouseCode, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAssetCabinetWarehouseByWarehouseNameContaining
	 *
	 */
	public Set<AssetCabinetWarehouse> findAssetCabinetWarehouseByWarehouseNameContaining(String warehouseName_1) throws DataAccessException;

	/**
	 * JPQL Query - findAssetCabinetWarehouseByWarehouseNameContaining
	 *
	 */
	public Set<AssetCabinetWarehouse> findAssetCabinetWarehouseByWarehouseNameContaining(String warehouseName_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAssetCabinetWarehouseByPrimaryKey
	 *
	 */
	public AssetCabinetWarehouse findAssetCabinetWarehouseByPrimaryKey(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findAssetCabinetWarehouseByPrimaryKey
	 *
	 */
	public AssetCabinetWarehouse findAssetCabinetWarehouseByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllAssetCabinetWarehouses
	 *
	 */
	public Set<AssetCabinetWarehouse> findAllAssetCabinetWarehouses() throws DataAccessException;

	/**
	 * JPQL Query - findAllAssetCabinetWarehouses
	 *
	 */
	public Set<AssetCabinetWarehouse> findAllAssetCabinetWarehouses(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAssetCabinetWarehouseById
	 *
	 */
	public AssetCabinetWarehouse findAssetCabinetWarehouseById(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findAssetCabinetWarehouseById
	 *
	 */
	public AssetCabinetWarehouse findAssetCabinetWarehouseById(Integer id_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAssetCabinetWarehouseByWarehouseCodeContaining
	 *
	 */
	public Set<AssetCabinetWarehouse> findAssetCabinetWarehouseByWarehouseCodeContaining(String warehouseCode_1) throws DataAccessException;

	/**
	 * JPQL Query - findAssetCabinetWarehouseByWarehouseCodeContaining
	 *
	 */
	public Set<AssetCabinetWarehouse> findAssetCabinetWarehouseByWarehouseCodeContaining(String warehouseCode_1, int startResult, int maxRows) throws DataAccessException;

}