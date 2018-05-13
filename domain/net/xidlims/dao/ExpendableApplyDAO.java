package net.xidlims.dao;

import java.util.Calendar;
import java.util.Set;

import net.xidlims.domain.ExpendableApply;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage ExpendableApply entities.
 * 
 */
public interface ExpendableApplyDAO extends JpaDao<ExpendableApply> {

	/**
	 * JPQL Query - findExpendableApplyByFlag
	 *
	 */
	public Set<ExpendableApply> findExpendableApplyByFlag(Integer flag) throws DataAccessException;

	/**
	 * JPQL Query - findExpendableApplyByFlag
	 *
	 */
	public Set<ExpendableApply> findExpendableApplyByFlag(Integer flag, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findExpendableApplyByBorrowTime
	 *
	 */
	public Set<ExpendableApply> findExpendableApplyByBorrowTime(java.util.Calendar borrowTime) throws DataAccessException;

	/**
	 * JPQL Query - findExpendableApplyByBorrowTime
	 *
	 */
	public Set<ExpendableApply> findExpendableApplyByBorrowTime(Calendar borrowTime, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findExpendableApplyByRemarksContaining
	 *
	 */
	public Set<ExpendableApply> findExpendableApplyByRemarksContaining(String remarks) throws DataAccessException;

	/**
	 * JPQL Query - findExpendableApplyByRemarksContaining
	 *
	 */
	public Set<ExpendableApply> findExpendableApplyByRemarksContaining(String remarks, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllExpendableApplys
	 *
	 */
	public Set<ExpendableApply> findAllExpendableApplys() throws DataAccessException;

	/**
	 * JPQL Query - findAllExpendableApplys
	 *
	 */
	public Set<ExpendableApply> findAllExpendableApplys(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findExpendableApplyByRemarks
	 *
	 */
	public Set<ExpendableApply> findExpendableApplyByRemarks(String remarks_1) throws DataAccessException;

	/**
	 * JPQL Query - findExpendableApplyByRemarks
	 *
	 */
	public Set<ExpendableApply> findExpendableApplyByRemarks(String remarks_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findExpendableApplyByExpendableNumber
	 *
	 */
	public Set<ExpendableApply> findExpendableApplyByExpendableNumber(Integer expendableNumber) throws DataAccessException;

	/**
	 * JPQL Query - findExpendableApplyByExpendableNumber
	 *
	 */
	public Set<ExpendableApply> findExpendableApplyByExpendableNumber(Integer expendableNumber, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findExpendableApplyById
	 *
	 */
	public ExpendableApply findExpendableApplyById(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findExpendableApplyById
	 *
	 */
	public ExpendableApply findExpendableApplyById(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findExpendableApplyByPrimaryKey
	 *
	 */
	public ExpendableApply findExpendableApplyByPrimaryKey(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findExpendableApplyByPrimaryKey
	 *
	 */
	public ExpendableApply findExpendableApplyByPrimaryKey(Integer id_1, int startResult, int maxRows) throws DataAccessException;

}