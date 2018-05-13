package net.xidlims.dao;

import java.util.Calendar;
import java.util.Set;

import net.xidlims.domain.NDeviceAuditRecord;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage NDeviceAuditRecord entities.
 * 
 */
public interface NDeviceAuditRecordDAO extends JpaDao<NDeviceAuditRecord> {

	/**
	 * JPQL Query - findNDeviceAuditRecordByAuditDate
	 *
	 */
	public Set<NDeviceAuditRecord> findNDeviceAuditRecordByAuditDate(java.util.Calendar auditDate) throws DataAccessException;

	/**
	 * JPQL Query - findNDeviceAuditRecordByAuditDate
	 *
	 */
	public Set<NDeviceAuditRecord> findNDeviceAuditRecordByAuditDate(Calendar auditDate, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findNDeviceAuditRecordByFlag
	 *
	 */
	public Set<NDeviceAuditRecord> findNDeviceAuditRecordByFlag(Integer flag) throws DataAccessException;

	/**
	 * JPQL Query - findNDeviceAuditRecordByFlag
	 *
	 */
	public Set<NDeviceAuditRecord> findNDeviceAuditRecordByFlag(Integer flag, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findNDeviceAuditRecordByRemarkContaining
	 *
	 */
	public Set<NDeviceAuditRecord> findNDeviceAuditRecordByRemarkContaining(String remark) throws DataAccessException;

	/**
	 * JPQL Query - findNDeviceAuditRecordByRemarkContaining
	 *
	 */
	public Set<NDeviceAuditRecord> findNDeviceAuditRecordByRemarkContaining(String remark, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findNDeviceAuditRecordByRemark
	 *
	 */
	public Set<NDeviceAuditRecord> findNDeviceAuditRecordByRemark(String remark_1) throws DataAccessException;

	/**
	 * JPQL Query - findNDeviceAuditRecordByRemark
	 *
	 */
	public Set<NDeviceAuditRecord> findNDeviceAuditRecordByRemark(String remark_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findNDeviceAuditRecordByPrimaryKey
	 *
	 */
	public NDeviceAuditRecord findNDeviceAuditRecordByPrimaryKey(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findNDeviceAuditRecordByPrimaryKey
	 *
	 */
	public NDeviceAuditRecord findNDeviceAuditRecordByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findNDeviceAuditRecordById
	 *
	 */
	public NDeviceAuditRecord findNDeviceAuditRecordById(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findNDeviceAuditRecordById
	 *
	 */
	public NDeviceAuditRecord findNDeviceAuditRecordById(Integer id_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllNDeviceAuditRecords
	 *
	 */
	public Set<NDeviceAuditRecord> findAllNDeviceAuditRecords() throws DataAccessException;

	/**
	 * JPQL Query - findAllNDeviceAuditRecords
	 *
	 */
	public Set<NDeviceAuditRecord> findAllNDeviceAuditRecords(int startResult, int maxRows) throws DataAccessException;

}