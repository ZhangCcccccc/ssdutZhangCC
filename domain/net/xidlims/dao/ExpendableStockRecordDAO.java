package net.xidlims.dao;

import java.util.Calendar;
import java.util.Set;

import net.xidlims.domain.ExpendableStockRecord;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage ExpendableStockRecord entities.
 * 
 */
public interface ExpendableStockRecordDAO extends JpaDao<ExpendableStockRecord> {

	/**
	 * JPQL Query - findExpendableStockRecordByPrimaryKey
	 *
	 */
	public ExpendableStockRecord findExpendableStockRecordByPrimaryKey(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findExpendableStockRecordByPrimaryKey
	 *
	 */
	public ExpendableStockRecord findExpendableStockRecordByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findExpendableStockRecordByUseType
	 *
	 */
	public Set<ExpendableStockRecord> findExpendableStockRecordByUseType(String useType) throws DataAccessException;

	/**
	 * JPQL Query - findExpendableStockRecordByUseType
	 *
	 */
	public Set<ExpendableStockRecord> findExpendableStockRecordByUseType(String useType, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findExpendableStockRecordByStockDate
	 *
	 */
	public Set<ExpendableStockRecord> findExpendableStockRecordByStockDate(java.util.Calendar stockDate) throws DataAccessException;

	/**
	 * JPQL Query - findExpendableStockRecordByStockDate
	 *
	 */
	public Set<ExpendableStockRecord> findExpendableStockRecordByStockDate(Calendar stockDate, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findExpendableStockRecordById
	 *
	 */
	public ExpendableStockRecord findExpendableStockRecordById(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findExpendableStockRecordById
	 *
	 */
	public ExpendableStockRecord findExpendableStockRecordById(Integer id_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findExpendableStockRecordByStockNumber
	 *
	 */
	public Set<ExpendableStockRecord> findExpendableStockRecordByStockNumber(Integer stockNumber) throws DataAccessException;

	/**
	 * JPQL Query - findExpendableStockRecordByStockNumber
	 *
	 */
	public Set<ExpendableStockRecord> findExpendableStockRecordByStockNumber(Integer stockNumber, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findExpendableStockRecordByRemark
	 *
	 */
	public Set<ExpendableStockRecord> findExpendableStockRecordByRemark(String remark) throws DataAccessException;

	/**
	 * JPQL Query - findExpendableStockRecordByRemark
	 *
	 */
	public Set<ExpendableStockRecord> findExpendableStockRecordByRemark(String remark, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllExpendableStockRecords
	 *
	 */
	public Set<ExpendableStockRecord> findAllExpendableStockRecords() throws DataAccessException;

	/**
	 * JPQL Query - findAllExpendableStockRecords
	 *
	 */
	public Set<ExpendableStockRecord> findAllExpendableStockRecords(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findExpendableStockRecordByRemarkContaining
	 *
	 */
	public Set<ExpendableStockRecord> findExpendableStockRecordByRemarkContaining(String remark_1) throws DataAccessException;

	/**
	 * JPQL Query - findExpendableStockRecordByRemarkContaining
	 *
	 */
	public Set<ExpendableStockRecord> findExpendableStockRecordByRemarkContaining(String remark_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findExpendableStockRecordByUseTypeContaining
	 *
	 */
	public Set<ExpendableStockRecord> findExpendableStockRecordByUseTypeContaining(String useType_1) throws DataAccessException;

	/**
	 * JPQL Query - findExpendableStockRecordByUseTypeContaining
	 *
	 */
	public Set<ExpendableStockRecord> findExpendableStockRecordByUseTypeContaining(String useType_1, int startResult, int maxRows) throws DataAccessException;

}