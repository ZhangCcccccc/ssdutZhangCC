package net.xidlims.dao;

import java.math.BigDecimal;

import java.util.Calendar;
import java.util.Set;

import net.xidlims.domain.NDevicePurchase;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage NDevicePurchase entities.
 * 
 */
public interface NDevicePurchaseDAO extends JpaDao<NDevicePurchase> {

	/**
	 * JPQL Query - findNDevicePurchaseByAuditStatus
	 *
	 */
	public Set<NDevicePurchase> findNDevicePurchaseByAuditStatus(Integer auditStatus) throws DataAccessException;

	/**
	 * JPQL Query - findNDevicePurchaseByAuditStatus
	 *
	 */
	public Set<NDevicePurchase> findNDevicePurchaseByAuditStatus(Integer auditStatus, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findNDevicePurchaseByDevicePurchaseReason
	 *
	 */
	public Set<NDevicePurchase> findNDevicePurchaseByDevicePurchaseReason(String devicePurchaseReason) throws DataAccessException;

	/**
	 * JPQL Query - findNDevicePurchaseByDevicePurchaseReason
	 *
	 */
	public Set<NDevicePurchase> findNDevicePurchaseByDevicePurchaseReason(String devicePurchaseReason, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findNDevicePurchaseByContactInformationContaining
	 *
	 */
	public Set<NDevicePurchase> findNDevicePurchaseByContactInformationContaining(String contactInformation) throws DataAccessException;

	/**
	 * JPQL Query - findNDevicePurchaseByContactInformationContaining
	 *
	 */
	public Set<NDevicePurchase> findNDevicePurchaseByContactInformationContaining(String contactInformation, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findNDevicePurchaseByPrimaryKey
	 *
	 */
	public NDevicePurchase findNDevicePurchaseByPrimaryKey(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findNDevicePurchaseByPrimaryKey
	 *
	 */
	public NDevicePurchase findNDevicePurchaseByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findNDevicePurchaseByCostCode
	 *
	 */
	public Set<NDevicePurchase> findNDevicePurchaseByCostCode(String costCode) throws DataAccessException;

	/**
	 * JPQL Query - findNDevicePurchaseByCostCode
	 *
	 */
	public Set<NDevicePurchase> findNDevicePurchaseByCostCode(String costCode, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findNDevicePurchaseByUseDirectionContaining
	 *
	 */
	public Set<NDevicePurchase> findNDevicePurchaseByUseDirectionContaining(String useDirection) throws DataAccessException;

	/**
	 * JPQL Query - findNDevicePurchaseByUseDirectionContaining
	 *
	 */
	public Set<NDevicePurchase> findNDevicePurchaseByUseDirectionContaining(String useDirection, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findNDevicePurchaseById
	 *
	 */
	public NDevicePurchase findNDevicePurchaseById(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findNDevicePurchaseById
	 *
	 */
	public NDevicePurchase findNDevicePurchaseById(Integer id_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findNDevicePurchaseByModifyDate
	 *
	 */
	public Set<NDevicePurchase> findNDevicePurchaseByModifyDate(java.util.Calendar modifyDate) throws DataAccessException;

	/**
	 * JPQL Query - findNDevicePurchaseByModifyDate
	 *
	 */
	public Set<NDevicePurchase> findNDevicePurchaseByModifyDate(Calendar modifyDate, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findNDevicePurchaseByCostCodeContaining
	 *
	 */
	public Set<NDevicePurchase> findNDevicePurchaseByCostCodeContaining(String costCode_1) throws DataAccessException;

	/**
	 * JPQL Query - findNDevicePurchaseByCostCodeContaining
	 *
	 */
	public Set<NDevicePurchase> findNDevicePurchaseByCostCodeContaining(String costCode_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findNDevicePurchaseByContactInformation
	 *
	 */
	public Set<NDevicePurchase> findNDevicePurchaseByContactInformation(String contactInformation_1) throws DataAccessException;

	/**
	 * JPQL Query - findNDevicePurchaseByContactInformation
	 *
	 */
	public Set<NDevicePurchase> findNDevicePurchaseByContactInformation(String contactInformation_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findNDevicePurchaseByUseDirection
	 *
	 */
	public Set<NDevicePurchase> findNDevicePurchaseByUseDirection(String useDirection_1) throws DataAccessException;

	/**
	 * JPQL Query - findNDevicePurchaseByUseDirection
	 *
	 */
	public Set<NDevicePurchase> findNDevicePurchaseByUseDirection(String useDirection_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findNDevicePurchaseByPurchaseNumberContaining
	 *
	 */
	public Set<NDevicePurchase> findNDevicePurchaseByPurchaseNumberContaining(String purchaseNumber) throws DataAccessException;

	/**
	 * JPQL Query - findNDevicePurchaseByPurchaseNumberContaining
	 *
	 */
	public Set<NDevicePurchase> findNDevicePurchaseByPurchaseNumberContaining(String purchaseNumber, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findNDevicePurchaseByPurchaseNumber
	 *
	 */
	public Set<NDevicePurchase> findNDevicePurchaseByPurchaseNumber(String purchaseNumber_1) throws DataAccessException;

	/**
	 * JPQL Query - findNDevicePurchaseByPurchaseNumber
	 *
	 */
	public Set<NDevicePurchase> findNDevicePurchaseByPurchaseNumber(String purchaseNumber_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findNDevicePurchaseByPurchaseFormatContaining
	 *
	 */
	public Set<NDevicePurchase> findNDevicePurchaseByPurchaseFormatContaining(String purchaseFormat) throws DataAccessException;

	/**
	 * JPQL Query - findNDevicePurchaseByPurchaseFormatContaining
	 *
	 */
	public Set<NDevicePurchase> findNDevicePurchaseByPurchaseFormatContaining(String purchaseFormat, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findNDevicePurchaseByCreateDate
	 *
	 */
	public Set<NDevicePurchase> findNDevicePurchaseByCreateDate(java.util.Calendar createDate) throws DataAccessException;

	/**
	 * JPQL Query - findNDevicePurchaseByCreateDate
	 *
	 */
	public Set<NDevicePurchase> findNDevicePurchaseByCreateDate(Calendar createDate, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllNDevicePurchases
	 *
	 */
	public Set<NDevicePurchase> findAllNDevicePurchases() throws DataAccessException;

	/**
	 * JPQL Query - findAllNDevicePurchases
	 *
	 */
	public Set<NDevicePurchase> findAllNDevicePurchases(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findNDevicePurchaseByPurchaseCost
	 *
	 */
	public Set<NDevicePurchase> findNDevicePurchaseByPurchaseCost(java.math.BigDecimal purchaseCost) throws DataAccessException;

	/**
	 * JPQL Query - findNDevicePurchaseByPurchaseCost
	 *
	 */
	public Set<NDevicePurchase> findNDevicePurchaseByPurchaseCost(BigDecimal purchaseCost, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findNDevicePurchaseByPurchaseFormat
	 *
	 */
	public Set<NDevicePurchase> findNDevicePurchaseByPurchaseFormat(String purchaseFormat_1) throws DataAccessException;

	/**
	 * JPQL Query - findNDevicePurchaseByPurchaseFormat
	 *
	 */
	public Set<NDevicePurchase> findNDevicePurchaseByPurchaseFormat(String purchaseFormat_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findNDevicePurchaseByIfEmergency
	 *
	 */
	public Set<NDevicePurchase> findNDevicePurchaseByIfEmergency(Integer ifEmergency) throws DataAccessException;

	/**
	 * JPQL Query - findNDevicePurchaseByIfEmergency
	 *
	 */
	public Set<NDevicePurchase> findNDevicePurchaseByIfEmergency(Integer ifEmergency, int startResult, int maxRows) throws DataAccessException;

}