package net.xidlims.dao;

import java.util.Calendar;
import java.util.Set;

import net.xidlims.domain.AssetApp;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage AssetApp entities.
 * 
 */
public interface AssetAppDAO extends JpaDao<AssetApp> {

	/**
	 * JPQL Query - findAssetAppBySaveSubmit
	 *
	 */
	public Set<AssetApp> findAssetAppBySaveSubmit(Integer saveSubmit) throws DataAccessException;

	/**
	 * JPQL Query - findAssetAppBySaveSubmit
	 *
	 */
	public Set<AssetApp> findAssetAppBySaveSubmit(Integer saveSubmit, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAssetAppByMem
	 *
	 */
	public Set<AssetApp> findAssetAppByMem(String mem) throws DataAccessException;

	/**
	 * JPQL Query - findAssetAppByMem
	 *
	 */
	public Set<AssetApp> findAssetAppByMem(String mem, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAssetAppByAppDateBefore
	 *
	 */
	public Set<AssetApp> findAssetAppByAppDateBefore(java.util.Calendar appDate) throws DataAccessException;

	/**
	 * JPQL Query - findAssetAppByAppDateBefore
	 *
	 */
	public Set<AssetApp> findAssetAppByAppDateBefore(Calendar appDate, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAssetAppByAppNo
	 *
	 */
	public Set<AssetApp> findAssetAppByAppNo(String appNo) throws DataAccessException;

	/**
	 * JPQL Query - findAssetAppByAppNo
	 *
	 */
	public Set<AssetApp> findAssetAppByAppNo(String appNo, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAssetAppByAppDateAfter
	 *
	 */
	public Set<AssetApp> findAssetAppByAppDateAfter(java.util.Calendar appDate_1) throws DataAccessException;

	/**
	 * JPQL Query - findAssetAppByAppDateAfter
	 *
	 */
	public Set<AssetApp> findAssetAppByAppDateAfter(Calendar appDate_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAssetAppByProjectNameContaining
	 *
	 */
	public Set<AssetApp> findAssetAppByProjectNameContaining(String projectName) throws DataAccessException;

	/**
	 * JPQL Query - findAssetAppByProjectNameContaining
	 *
	 */
	public Set<AssetApp> findAssetAppByProjectNameContaining(String projectName, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAssetAppById
	 *
	 */
	public AssetApp findAssetAppById(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findAssetAppById
	 *
	 */
	public AssetApp findAssetAppById(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAssetAppByAppDate
	 *
	 */
	public Set<AssetApp> findAssetAppByAppDate(java.util.Calendar appDate_2) throws DataAccessException;

	/**
	 * JPQL Query - findAssetAppByAppDate
	 *
	 */
	public Set<AssetApp> findAssetAppByAppDate(Calendar appDate_2, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAssetAppByProjectName
	 *
	 */
	public Set<AssetApp> findAssetAppByProjectName(String projectName_1) throws DataAccessException;

	/**
	 * JPQL Query - findAssetAppByProjectName
	 *
	 */
	public Set<AssetApp> findAssetAppByProjectName(String projectName_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAssetAppByPrimaryKey
	 *
	 */
	public AssetApp findAssetAppByPrimaryKey(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findAssetAppByPrimaryKey
	 *
	 */
	public AssetApp findAssetAppByPrimaryKey(Integer id_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllAssetApps
	 *
	 */
	public Set<AssetApp> findAllAssetApps() throws DataAccessException;

	/**
	 * JPQL Query - findAllAssetApps
	 *
	 */
	public Set<AssetApp> findAllAssetApps(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAssetAppByAppNoContaining
	 *
	 */
	public Set<AssetApp> findAssetAppByAppNoContaining(String appNo_1) throws DataAccessException;

	/**
	 * JPQL Query - findAssetAppByAppNoContaining
	 *
	 */
	public Set<AssetApp> findAssetAppByAppNoContaining(String appNo_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAssetAppByAssetStatu
	 *
	 */
	public Set<AssetApp> findAssetAppByAssetStatu(Integer assetStatu) throws DataAccessException;

	/**
	 * JPQL Query - findAssetAppByAssetStatu
	 *
	 */
	public Set<AssetApp> findAssetAppByAssetStatu(Integer assetStatu, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAssetAppByAssetAuditStatus
	 *
	 */
	public Set<AssetApp> findAssetAppByAssetAuditStatus(Integer assetAuditStatus) throws DataAccessException;

	/**
	 * JPQL Query - findAssetAppByAssetAuditStatus
	 *
	 */
	public Set<AssetApp> findAssetAppByAssetAuditStatus(Integer assetAuditStatus, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAssetAppByAppType
	 *
	 */
	public Set<AssetApp> findAssetAppByAppType(Integer appType) throws DataAccessException;

	/**
	 * JPQL Query - findAssetAppByAppType
	 *
	 */
	public Set<AssetApp> findAssetAppByAppType(Integer appType, int startResult, int maxRows) throws DataAccessException;

}