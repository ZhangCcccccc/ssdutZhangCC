package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.LabConstructionFunding;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage LabConstructionFunding entities.
 * 
 */
@Repository("LabConstructionFundingDAO")
@Transactional
public class LabConstructionFundingDAOImpl extends AbstractJpaDao<LabConstructionFunding>
		implements LabConstructionFundingDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { LabConstructionFunding.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new LabConstructionFundingDAOImpl
	 *
	 */
	public LabConstructionFundingDAOImpl() {
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
	 * JPQL Query - findLabConstructionFundingByStage
	 *
	 */
	@Transactional
	public Set<LabConstructionFunding> findLabConstructionFundingByStage(Integer stage) throws DataAccessException {

		return findLabConstructionFundingByStage(stage, -1, -1);
	}

	/**
	 * JPQL Query - findLabConstructionFundingByStage
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabConstructionFunding> findLabConstructionFundingByStage(Integer stage, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabConstructionFundingByStage", startResult, maxRows, stage);
		return new LinkedHashSet<LabConstructionFunding>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabConstructionFundingByPrimaryKey
	 *
	 */
	@Transactional
	public LabConstructionFunding findLabConstructionFundingByPrimaryKey(Integer id) throws DataAccessException {

		return findLabConstructionFundingByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findLabConstructionFundingByPrimaryKey
	 *
	 */

	@Transactional
	public LabConstructionFunding findLabConstructionFundingByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findLabConstructionFundingByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.LabConstructionFunding) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findLabConstructionFundingByFundingNumber
	 *
	 */
	@Transactional
	public Set<LabConstructionFunding> findLabConstructionFundingByFundingNumber(String fundingNumber) throws DataAccessException {

		return findLabConstructionFundingByFundingNumber(fundingNumber, -1, -1);
	}

	/**
	 * JPQL Query - findLabConstructionFundingByFundingNumber
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabConstructionFunding> findLabConstructionFundingByFundingNumber(String fundingNumber, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabConstructionFundingByFundingNumber", startResult, maxRows, fundingNumber);
		return new LinkedHashSet<LabConstructionFunding>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabConstructionFundingById
	 *
	 */
	@Transactional
	public LabConstructionFunding findLabConstructionFundingById(Integer id) throws DataAccessException {

		return findLabConstructionFundingById(id, -1, -1);
	}

	/**
	 * JPQL Query - findLabConstructionFundingById
	 *
	 */

	@Transactional
	public LabConstructionFunding findLabConstructionFundingById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findLabConstructionFundingById", startResult, maxRows, id);
			return (net.xidlims.domain.LabConstructionFunding) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findLabConstructionFundingByAuditResults
	 *
	 */
	@Transactional
	public Set<LabConstructionFunding> findLabConstructionFundingByAuditResults(Integer auditResults) throws DataAccessException {

		return findLabConstructionFundingByAuditResults(auditResults, -1, -1);
	}

	/**
	 * JPQL Query - findLabConstructionFundingByAuditResults
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabConstructionFunding> findLabConstructionFundingByAuditResults(Integer auditResults, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabConstructionFundingByAuditResults", startResult, maxRows, auditResults);
		return new LinkedHashSet<LabConstructionFunding>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllLabConstructionFundings
	 *
	 */
	@Transactional
	public Set<LabConstructionFunding> findAllLabConstructionFundings() throws DataAccessException {

		return findAllLabConstructionFundings(-1, -1);
	}

	/**
	 * JPQL Query - findAllLabConstructionFundings
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabConstructionFunding> findAllLabConstructionFundings(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllLabConstructionFundings", startResult, maxRows);
		return new LinkedHashSet<LabConstructionFunding>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabConstructionFundingByFundingNumberContaining
	 *
	 */
	@Transactional
	public Set<LabConstructionFunding> findLabConstructionFundingByFundingNumberContaining(String fundingNumber) throws DataAccessException {

		return findLabConstructionFundingByFundingNumberContaining(fundingNumber, -1, -1);
	}

	/**
	 * JPQL Query - findLabConstructionFundingByFundingNumberContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabConstructionFunding> findLabConstructionFundingByFundingNumberContaining(String fundingNumber, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabConstructionFundingByFundingNumberContaining", startResult, maxRows, fundingNumber);
		return new LinkedHashSet<LabConstructionFunding>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(LabConstructionFunding entity) {
		return true;
	}
}
