package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.LabConstructionPurchaseAudit;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage LabConstructionPurchaseAudit entities.
 * 
 */
@Repository("LabConstructionPurchaseAuditDAO")
@Transactional
public class LabConstructionPurchaseAuditDAOImpl extends AbstractJpaDao<LabConstructionPurchaseAudit>
		implements LabConstructionPurchaseAuditDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { LabConstructionPurchaseAudit.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new LabConstructionPurchaseAuditDAOImpl
	 *
	 */
	public LabConstructionPurchaseAuditDAOImpl() {
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
	 * JPQL Query - findLabConstructionPurchaseAuditByComments
	 *
	 */
	@Transactional
	public Set<LabConstructionPurchaseAudit> findLabConstructionPurchaseAuditByComments(String comments) throws DataAccessException {

		return findLabConstructionPurchaseAuditByComments(comments, -1, -1);
	}

	/**
	 * JPQL Query - findLabConstructionPurchaseAuditByComments
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabConstructionPurchaseAudit> findLabConstructionPurchaseAuditByComments(String comments, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabConstructionPurchaseAuditByComments", startResult, maxRows, comments);
		return new LinkedHashSet<LabConstructionPurchaseAudit>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabConstructionPurchaseAuditByPrimaryKey
	 *
	 */
	@Transactional
	public LabConstructionPurchaseAudit findLabConstructionPurchaseAuditByPrimaryKey(Integer id) throws DataAccessException {

		return findLabConstructionPurchaseAuditByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findLabConstructionPurchaseAuditByPrimaryKey
	 *
	 */

	@Transactional
	public LabConstructionPurchaseAudit findLabConstructionPurchaseAuditByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findLabConstructionPurchaseAuditByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.LabConstructionPurchaseAudit) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findAllLabConstructionPurchaseAudits
	 *
	 */
	@Transactional
	public Set<LabConstructionPurchaseAudit> findAllLabConstructionPurchaseAudits() throws DataAccessException {

		return findAllLabConstructionPurchaseAudits(-1, -1);
	}

	/**
	 * JPQL Query - findAllLabConstructionPurchaseAudits
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabConstructionPurchaseAudit> findAllLabConstructionPurchaseAudits(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllLabConstructionPurchaseAudits", startResult, maxRows);
		return new LinkedHashSet<LabConstructionPurchaseAudit>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabConstructionPurchaseAuditById
	 *
	 */
	@Transactional
	public LabConstructionPurchaseAudit findLabConstructionPurchaseAuditById(Integer id) throws DataAccessException {

		return findLabConstructionPurchaseAuditById(id, -1, -1);
	}

	/**
	 * JPQL Query - findLabConstructionPurchaseAuditById
	 *
	 */

	@Transactional
	public LabConstructionPurchaseAudit findLabConstructionPurchaseAuditById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findLabConstructionPurchaseAuditById", startResult, maxRows, id);
			return (net.xidlims.domain.LabConstructionPurchaseAudit) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(LabConstructionPurchaseAudit entity) {
		return true;
	}
}
