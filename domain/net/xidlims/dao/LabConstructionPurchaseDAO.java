package net.xidlims.dao;

import java.util.Calendar;
import java.util.Set;

import net.xidlims.domain.LabConstructionPurchase;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage LabConstructionPurchase entities.
 * 
 */
public interface LabConstructionPurchaseDAO extends
		JpaDao<LabConstructionPurchase> {

	/**
	 * JPQL Query - findLabConstructionPurchaseByUseLocationContaining
	 *
	 */
	public Set<LabConstructionPurchase> findLabConstructionPurchaseByUseLocationContaining(String useLocation) throws DataAccessException;

	/**
	 * JPQL Query - findLabConstructionPurchaseByUseLocationContaining
	 *
	 */
	public Set<LabConstructionPurchase> findLabConstructionPurchaseByUseLocationContaining(String useLocation, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabConstructionPurchaseByUseLocation
	 *
	 */
	public Set<LabConstructionPurchase> findLabConstructionPurchaseByUseLocation(String useLocation_1) throws DataAccessException;

	/**
	 * JPQL Query - findLabConstructionPurchaseByUseLocation
	 *
	 */
	public Set<LabConstructionPurchase> findLabConstructionPurchaseByUseLocation(String useLocation_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabConstructionPurchaseById
	 *
	 */
	public LabConstructionPurchase findLabConstructionPurchaseById(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findLabConstructionPurchaseById
	 *
	 */
	public LabConstructionPurchase findLabConstructionPurchaseById(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllLabConstructionPurchases
	 *
	 */
	public Set<LabConstructionPurchase> findAllLabConstructionPurchases() throws DataAccessException;

	/**
	 * JPQL Query - findAllLabConstructionPurchases
	 *
	 */
	public Set<LabConstructionPurchase> findAllLabConstructionPurchases(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabConstructionPurchaseByPrimaryKey
	 *
	 */
	public LabConstructionPurchase findLabConstructionPurchaseByPrimaryKey(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findLabConstructionPurchaseByPrimaryKey
	 *
	 */
	public LabConstructionPurchase findLabConstructionPurchaseByPrimaryKey(Integer id_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabConstructionPurchaseByPurchaseReason
	 *
	 */
	public Set<LabConstructionPurchase> findLabConstructionPurchaseByPurchaseReason(String purchaseReason) throws DataAccessException;

	/**
	 * JPQL Query - findLabConstructionPurchaseByPurchaseReason
	 *
	 */
	public Set<LabConstructionPurchase> findLabConstructionPurchaseByPurchaseReason(String purchaseReason, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabConstructionPurchaseByPurchaseTime
	 *
	 */
	public Set<LabConstructionPurchase> findLabConstructionPurchaseByPurchaseTime(java.util.Calendar purchaseTime) throws DataAccessException;

	/**
	 * JPQL Query - findLabConstructionPurchaseByPurchaseTime
	 *
	 */
	public Set<LabConstructionPurchase> findLabConstructionPurchaseByPurchaseTime(Calendar purchaseTime, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabConstructionPurchaseByAuditResults
	 *
	 */
	public Set<LabConstructionPurchase> findLabConstructionPurchaseByAuditResults(Integer auditResults) throws DataAccessException;

	/**
	 * JPQL Query - findLabConstructionPurchaseByAuditResults
	 *
	 */
	public Set<LabConstructionPurchase> findLabConstructionPurchaseByAuditResults(Integer auditResults, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabConstructionPurchaseByStage
	 *
	 */
	public Set<LabConstructionPurchase> findLabConstructionPurchaseByStage(Integer stage) throws DataAccessException;

	/**
	 * JPQL Query - findLabConstructionPurchaseByStage
	 *
	 */
	public Set<LabConstructionPurchase> findLabConstructionPurchaseByStage(Integer stage, int startResult, int maxRows) throws DataAccessException;

}