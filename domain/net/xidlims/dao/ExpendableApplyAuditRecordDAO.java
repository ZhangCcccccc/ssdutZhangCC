package net.xidlims.dao;

import java.util.Calendar;
import java.util.Set;

import net.xidlims.domain.ExpendableApplyAuditRecord;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage ExpendableApplyAuditRecord entities.
 * 
 */
public interface ExpendableApplyAuditRecordDAO extends
		JpaDao<ExpendableApplyAuditRecord> {

	/**
	 * JPQL Query - findAllExpendableApplyAuditRecords
	 *
	 */
	public Set<ExpendableApplyAuditRecord> findAllExpendableApplyAuditRecords() throws DataAccessException;

	/**
	 * JPQL Query - findAllExpendableApplyAuditRecords
	 *
	 */
	public Set<ExpendableApplyAuditRecord> findAllExpendableApplyAuditRecords(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findExpendableApplyAuditRecordByRemarkContaining
	 *
	 */
	public Set<ExpendableApplyAuditRecord> findExpendableApplyAuditRecordByRemarkContaining(String remark) throws DataAccessException;

	/**
	 * JPQL Query - findExpendableApplyAuditRecordByRemarkContaining
	 *
	 */
	public Set<ExpendableApplyAuditRecord> findExpendableApplyAuditRecordByRemarkContaining(String remark, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findExpendableApplyAuditRecordByRemark
	 *
	 */
	public Set<ExpendableApplyAuditRecord> findExpendableApplyAuditRecordByRemark(String remark_1) throws DataAccessException;

	/**
	 * JPQL Query - findExpendableApplyAuditRecordByRemark
	 *
	 */
	public Set<ExpendableApplyAuditRecord> findExpendableApplyAuditRecordByRemark(String remark_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findExpendableApplyAuditRecordById
	 *
	 */
	public ExpendableApplyAuditRecord findExpendableApplyAuditRecordById(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findExpendableApplyAuditRecordById
	 *
	 */
	public ExpendableApplyAuditRecord findExpendableApplyAuditRecordById(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findExpendableApplyAuditRecordByAuditResult
	 *
	 */
	public Set<ExpendableApplyAuditRecord> findExpendableApplyAuditRecordByAuditResult(Integer auditResult) throws DataAccessException;

	/**
	 * JPQL Query - findExpendableApplyAuditRecordByAuditResult
	 *
	 */
	public Set<ExpendableApplyAuditRecord> findExpendableApplyAuditRecordByAuditResult(Integer auditResult, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findExpendableApplyAuditRecordByAuditDate
	 *
	 */
	public Set<ExpendableApplyAuditRecord> findExpendableApplyAuditRecordByAuditDate(java.util.Calendar auditDate) throws DataAccessException;

	/**
	 * JPQL Query - findExpendableApplyAuditRecordByAuditDate
	 *
	 */
	public Set<ExpendableApplyAuditRecord> findExpendableApplyAuditRecordByAuditDate(Calendar auditDate, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findExpendableApplyAuditRecordByPrimaryKey
	 *
	 */
	public ExpendableApplyAuditRecord findExpendableApplyAuditRecordByPrimaryKey(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findExpendableApplyAuditRecordByPrimaryKey
	 *
	 */
	public ExpendableApplyAuditRecord findExpendableApplyAuditRecordByPrimaryKey(Integer id_1, int startResult, int maxRows) throws DataAccessException;

}