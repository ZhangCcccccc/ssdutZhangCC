package net.xidlims.dao;

import java.util.Calendar;
import java.util.Set;

import net.xidlims.domain.AssetOpenLog;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage AssetOpenLog entities.
 * 
 */
public interface AssetOpenLogDAO extends JpaDao<AssetOpenLog> {

	/**
	 * JPQL Query - findAssetOpenLogByStatus
	 *
	 */
	public Set<AssetOpenLog> findAssetOpenLogByStatus(Integer status) throws DataAccessException;

	/**
	 * JPQL Query - findAssetOpenLogByStatus
	 *
	 */
	public Set<AssetOpenLog> findAssetOpenLogByStatus(Integer status, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAssetOpenLogByMemContaining
	 *
	 */
	public Set<AssetOpenLog> findAssetOpenLogByMemContaining(String mem) throws DataAccessException;

	/**
	 * JPQL Query - findAssetOpenLogByMemContaining
	 *
	 */
	public Set<AssetOpenLog> findAssetOpenLogByMemContaining(String mem, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAssetOpenLogByMem
	 *
	 */
	public Set<AssetOpenLog> findAssetOpenLogByMem(String mem_1) throws DataAccessException;

	/**
	 * JPQL Query - findAssetOpenLogByMem
	 *
	 */
	public Set<AssetOpenLog> findAssetOpenLogByMem(String mem_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAssetOpenLogByCreateDate
	 *
	 */
	public Set<AssetOpenLog> findAssetOpenLogByCreateDate(java.util.Calendar createDate) throws DataAccessException;

	/**
	 * JPQL Query - findAssetOpenLogByCreateDate
	 *
	 */
	public Set<AssetOpenLog> findAssetOpenLogByCreateDate(Calendar createDate, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAssetOpenLogByCreateUser
	 *
	 */
	public Set<AssetOpenLog> findAssetOpenLogByCreateUser(String createUser) throws DataAccessException;

	/**
	 * JPQL Query - findAssetOpenLogByCreateUser
	 *
	 */
	public Set<AssetOpenLog> findAssetOpenLogByCreateUser(String createUser, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAssetOpenLogByOpenDate
	 *
	 */
	public Set<AssetOpenLog> findAssetOpenLogByOpenDate(java.util.Calendar openDate) throws DataAccessException;

	/**
	 * JPQL Query - findAssetOpenLogByOpenDate
	 *
	 */
	public Set<AssetOpenLog> findAssetOpenLogByOpenDate(Calendar openDate, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAssetOpenLogByPrimaryKey
	 *
	 */
	public AssetOpenLog findAssetOpenLogByPrimaryKey(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findAssetOpenLogByPrimaryKey
	 *
	 */
	public AssetOpenLog findAssetOpenLogByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAssetOpenLogById
	 *
	 */
	public AssetOpenLog findAssetOpenLogById(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findAssetOpenLogById
	 *
	 */
	public AssetOpenLog findAssetOpenLogById(Integer id_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllAssetOpenLogs
	 *
	 */
	public Set<AssetOpenLog> findAllAssetOpenLogs() throws DataAccessException;

	/**
	 * JPQL Query - findAllAssetOpenLogs
	 *
	 */
	public Set<AssetOpenLog> findAllAssetOpenLogs(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAssetOpenLogByCreateUserContaining
	 *
	 */
	public Set<AssetOpenLog> findAssetOpenLogByCreateUserContaining(String createUser_1) throws DataAccessException;

	/**
	 * JPQL Query - findAssetOpenLogByCreateUserContaining
	 *
	 */
	public Set<AssetOpenLog> findAssetOpenLogByCreateUserContaining(String createUser_1, int startResult, int maxRows) throws DataAccessException;

}