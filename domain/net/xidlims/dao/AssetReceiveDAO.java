package net.xidlims.dao;

import java.util.Calendar;
import java.util.Set;

import net.xidlims.domain.AssetReceive;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage AssetReceive entities.
 * 
 */
public interface AssetReceiveDAO extends JpaDao<AssetReceive> {

	/**
	 * JPQL Query - findAllAssetReceives
	 *
	 */
	public Set<AssetReceive> findAllAssetReceives() throws DataAccessException;

	/**
	 * JPQL Query - findAllAssetReceives
	 *
	 */
	public Set<AssetReceive> findAllAssetReceives(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAssetReceiveByProjectContentContaining
	 *
	 */
	public Set<AssetReceive> findAssetReceiveByProjectContentContaining(String projectContent) throws DataAccessException;

	/**
	 * JPQL Query - findAssetReceiveByProjectContentContaining
	 *
	 */
	public Set<AssetReceive> findAssetReceiveByProjectContentContaining(String projectContent, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAssetReceiveById
	 *
	 */
	public AssetReceive findAssetReceiveById(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findAssetReceiveById
	 *
	 */
	public AssetReceive findAssetReceiveById(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAssetReceiveByReceiveNo
	 *
	 */
	public Set<AssetReceive> findAssetReceiveByReceiveNo(String receiveNo) throws DataAccessException;

	/**
	 * JPQL Query - findAssetReceiveByReceiveNo
	 *
	 */
	public Set<AssetReceive> findAssetReceiveByReceiveNo(String receiveNo, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAssetReceiveByReceiveNoContaining
	 *
	 */
	public Set<AssetReceive> findAssetReceiveByReceiveNoContaining(String receiveNo_1) throws DataAccessException;

	/**
	 * JPQL Query - findAssetReceiveByReceiveNoContaining
	 *
	 */
	public Set<AssetReceive> findAssetReceiveByReceiveNoContaining(String receiveNo_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAssetReceiveByProjectName
	 *
	 */
	public Set<AssetReceive> findAssetReceiveByProjectName(String projectName) throws DataAccessException;

	/**
	 * JPQL Query - findAssetReceiveByProjectName
	 *
	 */
	public Set<AssetReceive> findAssetReceiveByProjectName(String projectName, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAssetReceiveByAssetUsage
	 *
	 */
	public Set<AssetReceive> findAssetReceiveByAssetUsage(String assetUsage) throws DataAccessException;

	/**
	 * JPQL Query - findAssetReceiveByAssetUsage
	 *
	 */
	public Set<AssetReceive> findAssetReceiveByAssetUsage(String assetUsage, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAssetReceiveByProjectNameContaining
	 *
	 */
	public Set<AssetReceive> findAssetReceiveByProjectNameContaining(String projectName_1) throws DataAccessException;

	/**
	 * JPQL Query - findAssetReceiveByProjectNameContaining
	 *
	 */
	public Set<AssetReceive> findAssetReceiveByProjectNameContaining(String projectName_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAssetReceiveByProjectContent
	 *
	 */
	public Set<AssetReceive> findAssetReceiveByProjectContent(String projectContent_1) throws DataAccessException;

	/**
	 * JPQL Query - findAssetReceiveByProjectContent
	 *
	 */
	public Set<AssetReceive> findAssetReceiveByProjectContent(String projectContent_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAssetReceiveByStatus
	 *
	 */
	public Set<AssetReceive> findAssetReceiveByStatus(Integer status) throws DataAccessException;

	/**
	 * JPQL Query - findAssetReceiveByStatus
	 *
	 */
	public Set<AssetReceive> findAssetReceiveByStatus(Integer status, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAssetReceiveByReceiveQuantity
	 *
	 */
	public Set<AssetReceive> findAssetReceiveByReceiveQuantity(Integer receiveQuantity) throws DataAccessException;

	/**
	 * JPQL Query - findAssetReceiveByReceiveQuantity
	 *
	 */
	public Set<AssetReceive> findAssetReceiveByReceiveQuantity(Integer receiveQuantity, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAssetReceiveByResult
	 *
	 */
	public Set<AssetReceive> findAssetReceiveByResult(String result) throws DataAccessException;

	/**
	 * JPQL Query - findAssetReceiveByResult
	 *
	 */
	public Set<AssetReceive> findAssetReceiveByResult(String result, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAssetReceiveByPrimaryKey
	 *
	 */
	public AssetReceive findAssetReceiveByPrimaryKey(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findAssetReceiveByPrimaryKey
	 *
	 */
	public AssetReceive findAssetReceiveByPrimaryKey(Integer id_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAssetReceiveByAssetUsageContaining
	 *
	 */
	public Set<AssetReceive> findAssetReceiveByAssetUsageContaining(String assetUsage_1) throws DataAccessException;

	/**
	 * JPQL Query - findAssetReceiveByAssetUsageContaining
	 *
	 */
	public Set<AssetReceive> findAssetReceiveByAssetUsageContaining(String assetUsage_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAssetReceiveBySaveSubmit
	 *
	 */
	public Set<AssetReceive> findAssetReceiveBySaveSubmit(Integer saveSubmit) throws DataAccessException;

	/**
	 * JPQL Query - findAssetReceiveBySaveSubmit
	 *
	 */
	public Set<AssetReceive> findAssetReceiveBySaveSubmit(Integer saveSubmit, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAssetReceiveByResultContaining
	 *
	 */
	public Set<AssetReceive> findAssetReceiveByResultContaining(String result_1) throws DataAccessException;

	/**
	 * JPQL Query - findAssetReceiveByResultContaining
	 *
	 */
	public Set<AssetReceive> findAssetReceiveByResultContaining(String result_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAssetReceiveByMemContaining
	 *
	 */
	public Set<AssetReceive> findAssetReceiveByMemContaining(String mem) throws DataAccessException;

	/**
	 * JPQL Query - findAssetReceiveByMemContaining
	 *
	 */
	public Set<AssetReceive> findAssetReceiveByMemContaining(String mem, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAssetReceiveByMem
	 *
	 */
	public Set<AssetReceive> findAssetReceiveByMem(String mem_1) throws DataAccessException;

	/**
	 * JPQL Query - findAssetReceiveByMem
	 *
	 */
	public Set<AssetReceive> findAssetReceiveByMem(String mem_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAssetReceiveByEndDate
	 *
	 */
	public Set<AssetReceive> findAssetReceiveByEndDate(java.util.Calendar endDate) throws DataAccessException;

	/**
	 * JPQL Query - findAssetReceiveByEndDate
	 *
	 */
	public Set<AssetReceive> findAssetReceiveByEndDate(Calendar endDate, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAssetReceiveByReceiveDate
	 *
	 */
	public Set<AssetReceive> findAssetReceiveByReceiveDate(java.util.Calendar receiveDate) throws DataAccessException;

	/**
	 * JPQL Query - findAssetReceiveByReceiveDate
	 *
	 */
	public Set<AssetReceive> findAssetReceiveByReceiveDate(Calendar receiveDate, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAssetReceiveByStartData
	 *
	 */
	public Set<AssetReceive> findAssetReceiveByStartData(java.util.Calendar startData) throws DataAccessException;

	/**
	 * JPQL Query - findAssetReceiveByStartData
	 *
	 */
	public Set<AssetReceive> findAssetReceiveByStartData(Calendar startData, int startResult, int maxRows) throws DataAccessException;

}