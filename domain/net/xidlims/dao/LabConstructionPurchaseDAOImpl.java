package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.LabConstructionPurchase;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage LabConstructionPurchase entities.
 * 
 */
@Repository("LabConstructionPurchaseDAO")
@Transactional
public class LabConstructionPurchaseDAOImpl extends AbstractJpaDao<LabConstructionPurchase>
		implements LabConstructionPurchaseDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { LabConstructionPurchase.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new LabConstructionPurchaseDAOImpl
	 *
	 */
	public LabConstructionPurchaseDAOImpl() {
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
	 * JPQL Query - findLabConstructionPurchaseByUseLocationContaining
	 *
	 */
	@Transactional
	public Set<LabConstructionPurchase> findLabConstructionPurchaseByUseLocationContaining(String useLocation) throws DataAccessException {

		return findLabConstructionPurchaseByUseLocationContaining(useLocation, -1, -1);
	}

	/**
	 * JPQL Query - findLabConstructionPurchaseByUseLocationContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabConstructionPurchase> findLabConstructionPurchaseByUseLocationContaining(String useLocation, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabConstructionPurchaseByUseLocationContaining", startResult, maxRows, useLocation);
		return new LinkedHashSet<LabConstructionPurchase>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabConstructionPurchaseByUseLocation
	 *
	 */
	@Transactional
	public Set<LabConstructionPurchase> findLabConstructionPurchaseByUseLocation(String useLocation) throws DataAccessException {

		return findLabConstructionPurchaseByUseLocation(useLocation, -1, -1);
	}

	/**
	 * JPQL Query - findLabConstructionPurchaseByUseLocation
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabConstructionPurchase> findLabConstructionPurchaseByUseLocation(String useLocation, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabConstructionPurchaseByUseLocation", startResult, maxRows, useLocation);
		return new LinkedHashSet<LabConstructionPurchase>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabConstructionPurchaseById
	 *
	 */
	@Transactional
	public LabConstructionPurchase findLabConstructionPurchaseById(Integer id) throws DataAccessException {

		return findLabConstructionPurchaseById(id, -1, -1);
	}

	/**
	 * JPQL Query - findLabConstructionPurchaseById
	 *
	 */

	@Transactional
	public LabConstructionPurchase findLabConstructionPurchaseById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findLabConstructionPurchaseById", startResult, maxRows, id);
			return (net.xidlims.domain.LabConstructionPurchase) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findAllLabConstructionPurchases
	 *
	 */
	@Transactional
	public Set<LabConstructionPurchase> findAllLabConstructionPurchases() throws DataAccessException {

		return findAllLabConstructionPurchases(-1, -1);
	}

	/**
	 * JPQL Query - findAllLabConstructionPurchases
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabConstructionPurchase> findAllLabConstructionPurchases(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllLabConstructionPurchases", startResult, maxRows);
		return new LinkedHashSet<LabConstructionPurchase>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabConstructionPurchaseByPrimaryKey
	 *
	 */
	@Transactional
	public LabConstructionPurchase findLabConstructionPurchaseByPrimaryKey(Integer id) throws DataAccessException {

		return findLabConstructionPurchaseByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findLabConstructionPurchaseByPrimaryKey
	 *
	 */

	@Transactional
	public LabConstructionPurchase findLabConstructionPurchaseByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findLabConstructionPurchaseByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.LabConstructionPurchase) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findLabConstructionPurchaseByPurchaseReason
	 *
	 */
	@Transactional
	public Set<LabConstructionPurchase> findLabConstructionPurchaseByPurchaseReason(String purchaseReason) throws DataAccessException {

		return findLabConstructionPurchaseByPurchaseReason(purchaseReason, -1, -1);
	}

	/**
	 * JPQL Query - findLabConstructionPurchaseByPurchaseReason
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabConstructionPurchase> findLabConstructionPurchaseByPurchaseReason(String purchaseReason, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabConstructionPurchaseByPurchaseReason", startResult, maxRows, purchaseReason);
		return new LinkedHashSet<LabConstructionPurchase>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabConstructionPurchaseByPurchaseTime
	 *
	 */
	@Transactional
	public Set<LabConstructionPurchase> findLabConstructionPurchaseByPurchaseTime(java.util.Calendar purchaseTime) throws DataAccessException {

		return findLabConstructionPurchaseByPurchaseTime(purchaseTime, -1, -1);
	}

	/**
	 * JPQL Query - findLabConstructionPurchaseByPurchaseTime
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabConstructionPurchase> findLabConstructionPurchaseByPurchaseTime(java.util.Calendar purchaseTime, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabConstructionPurchaseByPurchaseTime", startResult, maxRows, purchaseTime);
		return new LinkedHashSet<LabConstructionPurchase>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabConstructionPurchaseByAuditResults
	 *
	 */
	@Transactional
	public Set<LabConstructionPurchase> findLabConstructionPurchaseByAuditResults(Integer auditResults) throws DataAccessException {

		return findLabConstructionPurchaseByAuditResults(auditResults, -1, -1);
	}

	/**
	 * JPQL Query - findLabConstructionPurchaseByAuditResults
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabConstructionPurchase> findLabConstructionPurchaseByAuditResults(Integer auditResults, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabConstructionPurchaseByAuditResults", startResult, maxRows, auditResults);
		return new LinkedHashSet<LabConstructionPurchase>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabConstructionPurchaseByStage
	 *
	 */
	@Transactional
	public Set<LabConstructionPurchase> findLabConstructionPurchaseByStage(Integer stage) throws DataAccessException {

		return findLabConstructionPurchaseByStage(stage, -1, -1);
	}

	/**
	 * JPQL Query - findLabConstructionPurchaseByStage
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabConstructionPurchase> findLabConstructionPurchaseByStage(Integer stage, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabConstructionPurchaseByStage", startResult, maxRows, stage);
		return new LinkedHashSet<LabConstructionPurchase>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(LabConstructionPurchase entity) {
		return true;
	}
}
