package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.LabConstructionFundingAudit;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage LabConstructionFundingAudit entities.
 * 
 */
@Repository("LabConstructionFundingAuditDAO")
@Transactional
public class LabConstructionFundingAuditDAOImpl extends AbstractJpaDao<LabConstructionFundingAudit>
		implements LabConstructionFundingAuditDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { LabConstructionFundingAudit.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new LabConstructionFundingAuditDAOImpl
	 *
	 */
	public LabConstructionFundingAuditDAOImpl() {
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
	 * JPQL Query - findLabConstructionFundingAuditByPrimaryKey
	 *
	 */
	@Transactional
	public LabConstructionFundingAudit findLabConstructionFundingAuditByPrimaryKey(Integer id) throws DataAccessException {

		return findLabConstructionFundingAuditByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findLabConstructionFundingAuditByPrimaryKey
	 *
	 */

	@Transactional
	public LabConstructionFundingAudit findLabConstructionFundingAuditByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findLabConstructionFundingAuditByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.LabConstructionFundingAudit) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findAllLabConstructionFundingAudits
	 *
	 */
	@Transactional
	public Set<LabConstructionFundingAudit> findAllLabConstructionFundingAudits() throws DataAccessException {

		return findAllLabConstructionFundingAudits(-1, -1);
	}

	/**
	 * JPQL Query - findAllLabConstructionFundingAudits
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabConstructionFundingAudit> findAllLabConstructionFundingAudits(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllLabConstructionFundingAudits", startResult, maxRows);
		return new LinkedHashSet<LabConstructionFundingAudit>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabConstructionFundingAuditById
	 *
	 */
	@Transactional
	public LabConstructionFundingAudit findLabConstructionFundingAuditById(Integer id) throws DataAccessException {

		return findLabConstructionFundingAuditById(id, -1, -1);
	}

	/**
	 * JPQL Query - findLabConstructionFundingAuditById
	 *
	 */

	@Transactional
	public LabConstructionFundingAudit findLabConstructionFundingAuditById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findLabConstructionFundingAuditById", startResult, maxRows, id);
			return (net.xidlims.domain.LabConstructionFundingAudit) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findLabConstructionFundingAuditByComments
	 *
	 */
	@Transactional
	public Set<LabConstructionFundingAudit> findLabConstructionFundingAuditByComments(String comments) throws DataAccessException {

		return findLabConstructionFundingAuditByComments(comments, -1, -1);
	}

	/**
	 * JPQL Query - findLabConstructionFundingAuditByComments
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabConstructionFundingAudit> findLabConstructionFundingAuditByComments(String comments, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabConstructionFundingAuditByComments", startResult, maxRows, comments);
		return new LinkedHashSet<LabConstructionFundingAudit>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(LabConstructionFundingAudit entity) {
		return true;
	}
}
