package net.xidlims.dao;

import java.util.Calendar;
import java.util.Set;

import net.xidlims.domain.AssetReceiveAudit;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage AssetReceiveAudit entities.
 * 
 */
public interface AssetReceiveAuditDAO extends JpaDao<AssetReceiveAudit> {

	/**
	 * JPQL Query - findAssetReceiveAuditByAuditRoles
	 *
	 */
	public Set<AssetReceiveAudit> findAssetReceiveAuditByAuditRoles(Integer auditRoles) throws DataAccessException;

	/**
	 * JPQL Query - findAssetReceiveAuditByAuditRoles
	 *
	 */
	public Set<AssetReceiveAudit> findAssetReceiveAuditByAuditRoles(Integer auditRoles, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAssetReceiveAuditByMem
	 *
	 */
	public Set<AssetReceiveAudit> findAssetReceiveAuditByMem(String mem) throws DataAccessException;

	/**
	 * JPQL Query - findAssetReceiveAuditByMem
	 *
	 */
	public Set<AssetReceiveAudit> findAssetReceiveAuditByMem(String mem, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAssetReceiveAuditByResult
	 *
	 */
	public Set<AssetReceiveAudit> findAssetReceiveAuditByResult(String result) throws DataAccessException;

	/**
	 * JPQL Query - findAssetReceiveAuditByResult
	 *
	 */
	public Set<AssetReceiveAudit> findAssetReceiveAuditByResult(String result, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAssetReceiveAuditById
	 *
	 */
	public AssetReceiveAudit findAssetReceiveAuditById(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findAssetReceiveAuditById
	 *
	 */
	public AssetReceiveAudit findAssetReceiveAuditById(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllAssetReceiveAudits
	 *
	 */
	public Set<AssetReceiveAudit> findAllAssetReceiveAudits() throws DataAccessException;

	/**
	 * JPQL Query - findAllAssetReceiveAudits
	 *
	 */
	public Set<AssetReceiveAudit> findAllAssetReceiveAudits(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAssetReceiveAuditByPrimaryKey
	 *
	 */
	public AssetReceiveAudit findAssetReceiveAuditByPrimaryKey(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findAssetReceiveAuditByPrimaryKey
	 *
	 */
	public AssetReceiveAudit findAssetReceiveAuditByPrimaryKey(Integer id_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAssetReceiveAuditByStatus
	 *
	 */
	public Set<AssetReceiveAudit> findAssetReceiveAuditByStatus(Integer status) throws DataAccessException;

	/**
	 * JPQL Query - findAssetReceiveAuditByStatus
	 *
	 */
	public Set<AssetReceiveAudit> findAssetReceiveAuditByStatus(Integer status, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAssetReceiveAuditByCreateDate
	 *
	 */
	public Set<AssetReceiveAudit> findAssetReceiveAuditByCreateDate(java.util.Calendar createDate) throws DataAccessException;

	/**
	 * JPQL Query - findAssetReceiveAuditByCreateDate
	 *
	 */
	public Set<AssetReceiveAudit> findAssetReceiveAuditByCreateDate(Calendar createDate, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAssetReceiveAuditByResultContaining
	 *
	 */
	public Set<AssetReceiveAudit> findAssetReceiveAuditByResultContaining(String result_1) throws DataAccessException;

	/**
	 * JPQL Query - findAssetReceiveAuditByResultContaining
	 *
	 */
	public Set<AssetReceiveAudit> findAssetReceiveAuditByResultContaining(String result_1, int startResult, int maxRows) throws DataAccessException;

}