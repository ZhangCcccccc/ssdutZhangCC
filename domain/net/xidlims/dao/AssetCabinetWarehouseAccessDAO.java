package net.xidlims.dao;

import java.math.BigDecimal;

import java.util.Calendar;
import java.util.Set;

import net.xidlims.domain.AssetCabinetWarehouseAccess;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage AssetCabinetWarehouseAccess entities.
 * 
 */
public interface AssetCabinetWarehouseAccessDAO extends
		JpaDao<AssetCabinetWarehouseAccess> {

	/**
	 * JPQL Query - findAssetCabinetWarehouseAccessById
	 *
	 */
	public AssetCabinetWarehouseAccess findAssetCabinetWarehouseAccessById(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findAssetCabinetWarehouseAccessById
	 *
	 */
	public AssetCabinetWarehouseAccess findAssetCabinetWarehouseAccessById(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAssetCabinetWarehouseAccessByMemContaining
	 *
	 */
	public Set<AssetCabinetWarehouseAccess> findAssetCabinetWarehouseAccessByMemContaining(String mem) throws DataAccessException;

	/**
	 * JPQL Query - findAssetCabinetWarehouseAccessByMemContaining
	 *
	 */
	public Set<AssetCabinetWarehouseAccess> findAssetCabinetWarehouseAccessByMemContaining(String mem, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAssetCabinetWarehouseAccessByPrimaryKey
	 *
	 */
	public AssetCabinetWarehouseAccess findAssetCabinetWarehouseAccessByPrimaryKey(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findAssetCabinetWarehouseAccessByPrimaryKey
	 *
	 */
	public AssetCabinetWarehouseAccess findAssetCabinetWarehouseAccessByPrimaryKey(Integer id_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAssetCabinetWarehouseAccessByCreateDate
	 *
	 */
	public Set<AssetCabinetWarehouseAccess> findAssetCabinetWarehouseAccessByCreateDate(java.util.Calendar createDate) throws DataAccessException;

	/**
	 * JPQL Query - findAssetCabinetWarehouseAccessByCreateDate
	 *
	 */
	public Set<AssetCabinetWarehouseAccess> findAssetCabinetWarehouseAccessByCreateDate(Calendar createDate, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllAssetCabinetWarehouseAccesss
	 *
	 */
	public Set<AssetCabinetWarehouseAccess> findAllAssetCabinetWarehouseAccesss() throws DataAccessException;

	/**
	 * JPQL Query - findAllAssetCabinetWarehouseAccesss
	 *
	 */
	public Set<AssetCabinetWarehouseAccess> findAllAssetCabinetWarehouseAccesss(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAssetCabinetWarehouseAccessByMem
	 *
	 */
	public Set<AssetCabinetWarehouseAccess> findAssetCabinetWarehouseAccessByMem(String mem_1) throws DataAccessException;

	/**
	 * JPQL Query - findAssetCabinetWarehouseAccessByMem
	 *
	 */
	public Set<AssetCabinetWarehouseAccess> findAssetCabinetWarehouseAccessByMem(String mem_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAssetCabinetWarehouseAccessByStatus
	 *
	 */
	public Set<AssetCabinetWarehouseAccess> findAssetCabinetWarehouseAccessByStatus(Integer status) throws DataAccessException;

	/**
	 * JPQL Query - findAssetCabinetWarehouseAccessByStatus
	 *
	 */
	public Set<AssetCabinetWarehouseAccess> findAssetCabinetWarehouseAccessByStatus(Integer status, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAssetCabinetWarehouseAccessByCabinetQuantity
	 *
	 */
	public Set<AssetCabinetWarehouseAccess> findAssetCabinetWarehouseAccessByCabinetQuantity(java.math.BigDecimal cabinetQuantity) throws DataAccessException;

	/**
	 * JPQL Query - findAssetCabinetWarehouseAccessByCabinetQuantity
	 *
	 */
	public Set<AssetCabinetWarehouseAccess> findAssetCabinetWarehouseAccessByCabinetQuantity(BigDecimal cabinetQuantity, int startResult, int maxRows) throws DataAccessException;

}