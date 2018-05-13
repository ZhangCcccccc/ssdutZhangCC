package net.xidlims.dao;

import java.util.Calendar;
import java.util.Set;

import net.xidlims.domain.OperationItemMaterialRecord;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage OperationItemMaterialRecord entities.
 * 
 */
public interface OperationItemMaterialRecordDAO extends
		JpaDao<OperationItemMaterialRecord> {

	/**
	 * JPQL Query - findOperationItemMaterialRecordByLpmrAmountContaining
	 *
	 */
	public Set<OperationItemMaterialRecord> findOperationItemMaterialRecordByLpmrAmountContaining(String lpmrAmount) throws DataAccessException;

	/**
	 * JPQL Query - findOperationItemMaterialRecordByLpmrAmountContaining
	 *
	 */
	public Set<OperationItemMaterialRecord> findOperationItemMaterialRecordByLpmrAmountContaining(String lpmrAmount, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findOperationItemMaterialRecordByLpmrAmount
	 *
	 */
	public Set<OperationItemMaterialRecord> findOperationItemMaterialRecordByLpmrAmount(String lpmrAmount_1) throws DataAccessException;

	/**
	 * JPQL Query - findOperationItemMaterialRecordByLpmrAmount
	 *
	 */
	public Set<OperationItemMaterialRecord> findOperationItemMaterialRecordByLpmrAmount(String lpmrAmount_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findOperationItemMaterialRecordByLpmrId
	 *
	 */
	public OperationItemMaterialRecord findOperationItemMaterialRecordByLpmrId(Integer lpmrId) throws DataAccessException;

	/**
	 * JPQL Query - findOperationItemMaterialRecordByLpmrId
	 *
	 */
	public OperationItemMaterialRecord findOperationItemMaterialRecordByLpmrId(Integer lpmrId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findOperationItemMaterialRecordByLpmrModel
	 *
	 */
	public Set<OperationItemMaterialRecord> findOperationItemMaterialRecordByLpmrModel(String lpmrModel) throws DataAccessException;

	/**
	 * JPQL Query - findOperationItemMaterialRecordByLpmrModel
	 *
	 */
	public Set<OperationItemMaterialRecord> findOperationItemMaterialRecordByLpmrModel(String lpmrModel, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllOperationItemMaterialRecords
	 *
	 */
	public Set<OperationItemMaterialRecord> findAllOperationItemMaterialRecords() throws DataAccessException;

	/**
	 * JPQL Query - findAllOperationItemMaterialRecords
	 *
	 */
	public Set<OperationItemMaterialRecord> findAllOperationItemMaterialRecords(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findOperationItemMaterialRecordByLpmrTimeCreate
	 *
	 */
	public Set<OperationItemMaterialRecord> findOperationItemMaterialRecordByLpmrTimeCreate(java.util.Calendar lpmrTimeCreate) throws DataAccessException;

	/**
	 * JPQL Query - findOperationItemMaterialRecordByLpmrTimeCreate
	 *
	 */
	public Set<OperationItemMaterialRecord> findOperationItemMaterialRecordByLpmrTimeCreate(Calendar lpmrTimeCreate, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findOperationItemMaterialRecordByLpmrName
	 *
	 */
	public Set<OperationItemMaterialRecord> findOperationItemMaterialRecordByLpmrName(String lpmrName) throws DataAccessException;

	/**
	 * JPQL Query - findOperationItemMaterialRecordByLpmrName
	 *
	 */
	public Set<OperationItemMaterialRecord> findOperationItemMaterialRecordByLpmrName(String lpmrName, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findOperationItemMaterialRecordByLpmrUnit
	 *
	 */
	public Set<OperationItemMaterialRecord> findOperationItemMaterialRecordByLpmrUnit(String lpmrUnit) throws DataAccessException;

	/**
	 * JPQL Query - findOperationItemMaterialRecordByLpmrUnit
	 *
	 */
	public Set<OperationItemMaterialRecord> findOperationItemMaterialRecordByLpmrUnit(String lpmrUnit, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findOperationItemMaterialRecordByLpmrRemarkContaining
	 *
	 */
	public Set<OperationItemMaterialRecord> findOperationItemMaterialRecordByLpmrRemarkContaining(String lpmrRemark) throws DataAccessException;

	/**
	 * JPQL Query - findOperationItemMaterialRecordByLpmrRemarkContaining
	 *
	 */
	public Set<OperationItemMaterialRecord> findOperationItemMaterialRecordByLpmrRemarkContaining(String lpmrRemark, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findOperationItemMaterialRecordByLpmrRemark
	 *
	 */
	public Set<OperationItemMaterialRecord> findOperationItemMaterialRecordByLpmrRemark(String lpmrRemark_1) throws DataAccessException;

	/**
	 * JPQL Query - findOperationItemMaterialRecordByLpmrRemark
	 *
	 */
	public Set<OperationItemMaterialRecord> findOperationItemMaterialRecordByLpmrRemark(String lpmrRemark_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findOperationItemMaterialRecordByLpmrNameContaining
	 *
	 */
	public Set<OperationItemMaterialRecord> findOperationItemMaterialRecordByLpmrNameContaining(String lpmrName_1) throws DataAccessException;

	/**
	 * JPQL Query - findOperationItemMaterialRecordByLpmrNameContaining
	 *
	 */
	public Set<OperationItemMaterialRecord> findOperationItemMaterialRecordByLpmrNameContaining(String lpmrName_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findOperationItemMaterialRecordByLpmrModelContaining
	 *
	 */
	public Set<OperationItemMaterialRecord> findOperationItemMaterialRecordByLpmrModelContaining(String lpmrModel_1) throws DataAccessException;

	/**
	 * JPQL Query - findOperationItemMaterialRecordByLpmrModelContaining
	 *
	 */
	public Set<OperationItemMaterialRecord> findOperationItemMaterialRecordByLpmrModelContaining(String lpmrModel_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findOperationItemMaterialRecordByPrimaryKey
	 *
	 */
	public OperationItemMaterialRecord findOperationItemMaterialRecordByPrimaryKey(Integer lpmrId_1) throws DataAccessException;

	/**
	 * JPQL Query - findOperationItemMaterialRecordByPrimaryKey
	 *
	 */
	public OperationItemMaterialRecord findOperationItemMaterialRecordByPrimaryKey(Integer lpmrId_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findOperationItemMaterialRecordByLpmrUnitContaining
	 *
	 */
	public Set<OperationItemMaterialRecord> findOperationItemMaterialRecordByLpmrUnitContaining(String lpmrUnit_1) throws DataAccessException;

	/**
	 * JPQL Query - findOperationItemMaterialRecordByLpmrUnitContaining
	 *
	 */
	public Set<OperationItemMaterialRecord> findOperationItemMaterialRecordByLpmrUnitContaining(String lpmrUnit_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findOperationItemMaterialRecordByLpmrQuantity
	 *
	 */
	public Set<OperationItemMaterialRecord> findOperationItemMaterialRecordByLpmrQuantity(String lpmrQuantity) throws DataAccessException;

	/**
	 * JPQL Query - findOperationItemMaterialRecordByLpmrQuantity
	 *
	 */
	public Set<OperationItemMaterialRecord> findOperationItemMaterialRecordByLpmrQuantity(String lpmrQuantity, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findOperationItemMaterialRecordByLpmrQuantityContaining
	 *
	 */
	public Set<OperationItemMaterialRecord> findOperationItemMaterialRecordByLpmrQuantityContaining(String lpmrQuantity_1) throws DataAccessException;

	/**
	 * JPQL Query - findOperationItemMaterialRecordByLpmrQuantityContaining
	 *
	 */
	public Set<OperationItemMaterialRecord> findOperationItemMaterialRecordByLpmrQuantityContaining(String lpmrQuantity_1, int startResult, int maxRows) throws DataAccessException;

}