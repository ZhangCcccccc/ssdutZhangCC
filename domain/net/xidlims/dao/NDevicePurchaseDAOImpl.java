package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.NDevicePurchase;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage NDevicePurchase entities.
 * 
 */
@Repository("NDevicePurchaseDAO")
@Transactional
public class NDevicePurchaseDAOImpl extends AbstractJpaDao<NDevicePurchase>
		implements NDevicePurchaseDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { NDevicePurchase.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new NDevicePurchaseDAOImpl
	 *
	 */
	public NDevicePurchaseDAOImpl() {
		super();
	}

	/**
	 * Get the entity manager that manages persistence unit 
	 *
	 */
	public EntityManager getEntityManager() {
		return entityManager;
	}

	/**
	 * Returns the set of entity classes managed by this DAO.
	 *
	 */
	public Set<Class<?>> getTypes() {
		return dataTypes;
	}

	/**
	 * JPQL Query - findNDevicePurchaseByAuditStatus
	 *
	 */
	@Transactional
	public Set<NDevicePurchase> findNDevicePurchaseByAuditStatus(Integer auditStatus) throws DataAccessException {

		return findNDevicePurchaseByAuditStatus(auditStatus, -1, -1);
	}

	/**
	 * JPQL Query - findNDevicePurchaseByAuditStatus
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<NDevicePurchase> findNDevicePurchaseByAuditStatus(Integer auditStatus, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findNDevicePurchaseByAuditStatus", startResult, maxRows, auditStatus);
		return new LinkedHashSet<NDevicePurchase>(query.getResultList());
	}

	/**
	 * JPQL Query - findNDevicePurchaseByDevicePurchaseReason
	 *
	 */
	@Transactional
	public Set<NDevicePurchase> findNDevicePurchaseByDevicePurchaseReason(String devicePurchaseReason) throws DataAccessException {

		return findNDevicePurchaseByDevicePurchaseReason(devicePurchaseReason, -1, -1);
	}

	/**
	 * JPQL Query - findNDevicePurchaseByDevicePurchaseReason
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<NDevicePurchase> findNDevicePurchaseByDevicePurchaseReason(String devicePurchaseReason, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findNDevicePurchaseByDevicePurchaseReason", startResult, maxRows, devicePurchaseReason);
		return new LinkedHashSet<NDevicePurchase>(query.getResultList());
	}

	/**
	 * JPQL Query - findNDevicePurchaseByContactInformationContaining
	 *
	 */
	@Transactional
	public Set<NDevicePurchase> findNDevicePurchaseByContactInformationContaining(String contactInformation) throws DataAccessException {

		return findNDevicePurchaseByContactInformationContaining(contactInformation, -1, -1);
	}

	/**
	 * JPQL Query - findNDevicePurchaseByContactInformationContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<NDevicePurchase> findNDevicePurchaseByContactInformationContaining(String contactInformation, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findNDevicePurchaseByContactInformationContaining", startResult, maxRows, contactInformation);
		return new LinkedHashSet<NDevicePurchase>(query.getResultList());
	}

	/**
	 * JPQL Query - findNDevicePurchaseByPrimaryKey
	 *
	 */
	@Transactional
	public NDevicePurchase findNDevicePurchaseByPrimaryKey(Integer id) throws DataAccessException {

		return findNDevicePurchaseByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findNDevicePurchaseByPrimaryKey
	 *
	 */

	@Transactional
	public NDevicePurchase findNDevicePurchaseByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findNDevicePurchaseByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.NDevicePurchase) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findNDevicePurchaseByCostCode
	 *
	 */
	@Transactional
	public Set<NDevicePurchase> findNDevicePurchaseByCostCode(String costCode) throws DataAccessException {

		return findNDevicePurchaseByCostCode(costCode, -1, -1);
	}

	/**
	 * JPQL Query - findNDevicePurchaseByCostCode
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<NDevicePurchase> findNDevicePurchaseByCostCode(String costCode, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findNDevicePurchaseByCostCode", startResult, maxRows, costCode);
		return new LinkedHashSet<NDevicePurchase>(query.getResultList());
	}

	/**
	 * JPQL Query - findNDevicePurchaseByUseDirectionContaining
	 *
	 */
	@Transactional
	public Set<NDevicePurchase> findNDevicePurchaseByUseDirectionContaining(String useDirection) throws DataAccessException {

		return findNDevicePurchaseByUseDirectionContaining(useDirection, -1, -1);
	}

	/**
	 * JPQL Query - findNDevicePurchaseByUseDirectionContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<NDevicePurchase> findNDevicePurchaseByUseDirectionContaining(String useDirection, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findNDevicePurchaseByUseDirectionContaining", startResult, maxRows, useDirection);
		return new LinkedHashSet<NDevicePurchase>(query.getResultList());
	}

	/**
	 * JPQL Query - findNDevicePurchaseById
	 *
	 */
	@Transactional
	public NDevicePurchase findNDevicePurchaseById(Integer id) throws DataAccessException {

		return findNDevicePurchaseById(id, -1, -1);
	}

	/**
	 * JPQL Query - findNDevicePurchaseById
	 *
	 */

	@Transactional
	public NDevicePurchase findNDevicePurchaseById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findNDevicePurchaseById", startResult, maxRows, id);
			return (net.xidlims.domain.NDevicePurchase) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findNDevicePurchaseByModifyDate
	 *
	 */
	@Transactional
	public Set<NDevicePurchase> findNDevicePurchaseByModifyDate(java.util.Calendar modifyDate) throws DataAccessException {

		return findNDevicePurchaseByModifyDate(modifyDate, -1, -1);
	}

	/**
	 * JPQL Query - findNDevicePurchaseByModifyDate
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<NDevicePurchase> findNDevicePurchaseByModifyDate(java.util.Calendar modifyDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findNDevicePurchaseByModifyDate", startResult, maxRows, modifyDate);
		return new LinkedHashSet<NDevicePurchase>(query.getResultList());
	}

	/**
	 * JPQL Query - findNDevicePurchaseByCostCodeContaining
	 *
	 */
	@Transactional
	public Set<NDevicePurchase> findNDevicePurchaseByCostCodeContaining(String costCode) throws DataAccessException {

		return findNDevicePurchaseByCostCodeContaining(costCode, -1, -1);
	}

	/**
	 * JPQL Query - findNDevicePurchaseByCostCodeContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<NDevicePurchase> findNDevicePurchaseByCostCodeContaining(String costCode, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findNDevicePurchaseByCostCodeContaining", startResult, maxRows, costCode);
		return new LinkedHashSet<NDevicePurchase>(query.getResultList());
	}

	/**
	 * JPQL Query - findNDevicePurchaseByContactInformation
	 *
	 */
	@Transactional
	public Set<NDevicePurchase> findNDevicePurchaseByContactInformation(String contactInformation) throws DataAccessException {

		return findNDevicePurchaseByContactInformation(contactInformation, -1, -1);
	}

	/**
	 * JPQL Query - findNDevicePurchaseByContactInformation
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<NDevicePurchase> findNDevicePurchaseByContactInformation(String contactInformation, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findNDevicePurchaseByContactInformation", startResult, maxRows, contactInformation);
		return new LinkedHashSet<NDevicePurchase>(query.getResultList());
	}

	/**
	 * JPQL Query - findNDevicePurchaseByUseDirection
	 *
	 */
	@Transactional
	public Set<NDevicePurchase> findNDevicePurchaseByUseDirection(String useDirection) throws DataAccessException {

		return findNDevicePurchaseByUseDirection(useDirection, -1, -1);
	}

	/**
	 * JPQL Query - findNDevicePurchaseByUseDirection
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<NDevicePurchase> findNDevicePurchaseByUseDirection(String useDirection, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findNDevicePurchaseByUseDirection", startResult, maxRows, useDirection);
		return new LinkedHashSet<NDevicePurchase>(query.getResultList());
	}

	/**
	 * JPQL Query - findNDevicePurchaseByPurchaseNumberContaining
	 *
	 */
	@Transactional
	public Set<NDevicePurchase> findNDevicePurchaseByPurchaseNumberContaining(String purchaseNumber) throws DataAccessException {

		return findNDevicePurchaseByPurchaseNumberContaining(purchaseNumber, -1, -1);
	}

	/**
	 * JPQL Query - findNDevicePurchaseByPurchaseNumberContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<NDevicePurchase> findNDevicePurchaseByPurchaseNumberContaining(String purchaseNumber, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findNDevicePurchaseByPurchaseNumberContaining", startResult, maxRows, purchaseNumber);
		return new LinkedHashSet<NDevicePurchase>(query.getResultList());
	}

	/**
	 * JPQL Query - findNDevicePurchaseByPurchaseNumber
	 *
	 */
	@Transactional
	public Set<NDevicePurchase> findNDevicePurchaseByPurchaseNumber(String purchaseNumber) throws DataAccessException {

		return findNDevicePurchaseByPurchaseNumber(purchaseNumber, -1, -1);
	}

	/**
	 * JPQL Query - findNDevicePurchaseByPurchaseNumber
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<NDevicePurchase> findNDevicePurchaseByPurchaseNumber(String purchaseNumber, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findNDevicePurchaseByPurchaseNumber", startResult, maxRows, purchaseNumber);
		return new LinkedHashSet<NDevicePurchase>(query.getResultList());
	}

	/**
	 * JPQL Query - findNDevicePurchaseByPurchaseFormatContaining
	 *
	 */
	@Transactional
	public Set<NDevicePurchase> findNDevicePurchaseByPurchaseFormatContaining(String purchaseFormat) throws DataAccessException {

		return findNDevicePurchaseByPurchaseFormatContaining(purchaseFormat, -1, -1);
	}

	/**
	 * JPQL Query - findNDevicePurchaseByPurchaseFormatContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<NDevicePurchase> findNDevicePurchaseByPurchaseFormatContaining(String purchaseFormat, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findNDevicePurchaseByPurchaseFormatContaining", startResult, maxRows, purchaseFormat);
		return new LinkedHashSet<NDevicePurchase>(query.getResultList());
	}

	/**
	 * JPQL Query - findNDevicePurchaseByCreateDate
	 *
	 */
	@Transactional
	public Set<NDevicePurchase> findNDevicePurchaseByCreateDate(java.util.Calendar createDate) throws DataAccessException {

		return findNDevicePurchaseByCreateDate(createDate, -1, -1);
	}

	/**
	 * JPQL Query - findNDevicePurchaseByCreateDate
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<NDevicePurchase> findNDevicePurchaseByCreateDate(java.util.Calendar createDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findNDevicePurchaseByCreateDate", startResult, maxRows, createDate);
		return new LinkedHashSet<NDevicePurchase>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllNDevicePurchases
	 *
	 */
	@Transactional
	public Set<NDevicePurchase> findAllNDevicePurchases() throws DataAccessException {

		return findAllNDevicePurchases(-1, -1);
	}

	/**
	 * JPQL Query - findAllNDevicePurchases
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<NDevicePurchase> findAllNDevicePurchases(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllNDevicePurchases", startResult, maxRows);
		return new LinkedHashSet<NDevicePurchase>(query.getResultList());
	}

	/**
	 * JPQL Query - findNDevicePurchaseByPurchaseCost
	 *
	 */
	@Transactional
	public Set<NDevicePurchase> findNDevicePurchaseByPurchaseCost(java.math.BigDecimal purchaseCost) throws DataAccessException {

		return findNDevicePurchaseByPurchaseCost(purchaseCost, -1, -1);
	}

	/**
	 * JPQL Query - findNDevicePurchaseByPurchaseCost
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<NDevicePurchase> findNDevicePurchaseByPurchaseCost(java.math.BigDecimal purchaseCost, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findNDevicePurchaseByPurchaseCost", startResult, maxRows, purchaseCost);
		return new LinkedHashSet<NDevicePurchase>(query.getResultList());
	}

	/**
	 * JPQL Query - findNDevicePurchaseByPurchaseFormat
	 *
	 */
	@Transactional
	public Set<NDevicePurchase> findNDevicePurchaseByPurchaseFormat(String purchaseFormat) throws DataAccessException {

		return findNDevicePurchaseByPurchaseFormat(purchaseFormat, -1, -1);
	}

	/**
	 * JPQL Query - findNDevicePurchaseByPurchaseFormat
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<NDevicePurchase> findNDevicePurchaseByPurchaseFormat(String purchaseFormat, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findNDevicePurchaseByPurchaseFormat", startResult, maxRows, purchaseFormat);
		return new LinkedHashSet<NDevicePurchase>(query.getResultList());
	}

	/**
	 * JPQL Query - findNDevicePurchaseByIfEmergency
	 *
	 */
	@Transactional
	public Set<NDevicePurchase> findNDevicePurchaseByIfEmergency(Integer ifEmergency) throws DataAccessException {

		return findNDevicePurchaseByIfEmergency(ifEmergency, -1, -1);
	}

	/**
	 * JPQL Query - findNDevicePurchaseByIfEmergency
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<NDevicePurchase> findNDevicePurchaseByIfEmergency(Integer ifEmergency, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findNDevicePurchaseByIfEmergency", startResult, maxRows, ifEmergency);
		return new LinkedHashSet<NDevicePurchase>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(NDevicePurchase entity) {
		return true;
	}
}
