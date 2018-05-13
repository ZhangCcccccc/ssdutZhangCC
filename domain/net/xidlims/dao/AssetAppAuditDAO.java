package net.xidlims.dao;

import java.util.Calendar;
import java.util.Set;

import net.xidlims.domain.AssetAppAudit;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage AssetAppAudit entities.
 * 
 */
public interface AssetAppAuditDAO extends JpaDao<AssetAppAudit> {

	/**
	 * JPQL Query - findAllAssetAppAudits
	 *
	 */
	public Set<AssetAppAudit> findAllAssetAppAudits() throws DataAccessException;

	/**
	 * JPQL Query - findAllAssetAppAudits
	 *
	 */
	public Set<AssetAppAudit> findAllAssetAppAudits(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAssetAppAuditByMem
	 *
	 */
	public Set<AssetAppAudit> findAssetAppAuditByMem(String mem) throws DataAccessException;

	/**
	 * JPQL Query - findAssetAppAuditByMem
	 *
	 */
	public Set<AssetAppAudit> findAssetAppAuditByMem(String mem, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAssetAppAuditByStatus
	 *
	 */
	public Set<AssetAppAudit> findAssetAppAuditByStatus(Integer status) throws DataAccessException;

	/**
	 * JPQL Query - findAssetAppAuditByStatus
	 *
	 */
	public Set<AssetAppAudit> findAssetAppAuditByStatus(Integer status, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAssetAppAuditByPrimaryKey
	 *
	 */
	public AssetAppAudit findAssetAppAuditByPrimaryKey(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findAssetAppAuditByPrimaryKey
	 *
	 */
	public AssetAppAudit findAssetAppAuditByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAssetAppAuditByResult
	 *
	 */
	public Set<AssetAppAudit> findAssetAppAuditByResult(String result) throws DataAccessException;

	/**
	 * JPQL Query - findAssetAppAuditByResult
	 *
	 */
	public Set<AssetAppAudit> findAssetAppAuditByResult(String result, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAssetAppAuditByCreateDate
	 *
	 */
	public Set<AssetAppAudit> findAssetAppAuditByCreateDate(java.util.Calendar createDate) throws DataAccessException;

	/**
	 * JPQL Query - findAssetAppAuditByCreateDate
	 *
	 */
	public Set<AssetAppAudit> findAssetAppAuditByCreateDate(Calendar createDate, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAssetAppAuditByResultContaining
	 *
	 */
	public Set<AssetAppAudit> findAssetAppAuditByResultContaining(String result_1) throws DataAccessException;

	/**
	 * JPQL Query - findAssetAppAuditByResultContaining
	 *
	 */
	public Set<AssetAppAudit> findAssetAppAuditByResultContaining(String result_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAssetAppAuditByAuditRoles
	 *
	 */
	public Set<AssetAppAudit> findAssetAppAuditByAuditRoles(Integer auditRoles) throws DataAccessException;

	/**
	 * JPQL Query - findAssetAppAuditByAuditRoles
	 *
	 */
	public Set<AssetAppAudit> findAssetAppAuditByAuditRoles(Integer auditRoles, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAssetAppAuditById
	 *
	 */
	public AssetAppAudit findAssetAppAuditById(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findAssetAppAuditById
	 *
	 */
	public AssetAppAudit findAssetAppAuditById(Integer id_1, int startResult, int maxRows) throws DataAccessException;

}