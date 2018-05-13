package net.xidlims.dao;

import java.util.Set;

import net.xidlims.domain.AssetCabinet;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage AssetCabinet entities.
 * 
 */
public interface AssetCabinetDAO extends JpaDao<AssetCabinet> {

	/**
	 * JPQL Query - findAllAssetCabinets
	 *
	 */
	public Set<AssetCabinet> findAllAssetCabinets() throws DataAccessException;

	/**
	 * JPQL Query - findAllAssetCabinets
	 *
	 */
	public Set<AssetCabinet> findAllAssetCabinets(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAssetCabinetByCabinetNameContaining
	 *
	 */
	public Set<AssetCabinet> findAssetCabinetByCabinetNameContaining(String cabinetName) throws DataAccessException;

	/**
	 * JPQL Query - findAssetCabinetByCabinetNameContaining
	 *
	 */
	public Set<AssetCabinet> findAssetCabinetByCabinetNameContaining(String cabinetName, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAssetCabinetByPrimaryKey
	 *
	 */
	public AssetCabinet findAssetCabinetByPrimaryKey(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findAssetCabinetByPrimaryKey
	 *
	 */
	public AssetCabinet findAssetCabinetByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAssetCabinetById
	 *
	 */
	public AssetCabinet findAssetCabinetById(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findAssetCabinetById
	 *
	 */
	public AssetCabinet findAssetCabinetById(Integer id_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAssetCabinetByCabinetCode
	 *
	 */
	public Set<AssetCabinet> findAssetCabinetByCabinetCode(String cabinetCode) throws DataAccessException;

	/**
	 * JPQL Query - findAssetCabinetByCabinetCode
	 *
	 */
	public Set<AssetCabinet> findAssetCabinetByCabinetCode(String cabinetCode, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAssetCabinetByCabinetName
	 *
	 */
	public Set<AssetCabinet> findAssetCabinetByCabinetName(String cabinetName_1) throws DataAccessException;

	/**
	 * JPQL Query - findAssetCabinetByCabinetName
	 *
	 */
	public Set<AssetCabinet> findAssetCabinetByCabinetName(String cabinetName_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAssetCabinetByCabinetCodeContaining
	 *
	 */
	public Set<AssetCabinet> findAssetCabinetByCabinetCodeContaining(String cabinetCode_1) throws DataAccessException;

	/**
	 * JPQL Query - findAssetCabinetByCabinetCodeContaining
	 *
	 */
	public Set<AssetCabinet> findAssetCabinetByCabinetCodeContaining(String cabinetCode_1, int startResult, int maxRows) throws DataAccessException;

}