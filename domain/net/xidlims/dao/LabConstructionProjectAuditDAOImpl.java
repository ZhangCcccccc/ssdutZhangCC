package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.LabConstructionProjectAudit;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage LabConstructionProjectAudit entities.
 * 
 */
@Repository("LabConstructionProjectAuditDAO")
@Transactional
public class LabConstructionProjectAuditDAOImpl extends AbstractJpaDao<LabConstructionProjectAudit>
		implements LabConstructionProjectAuditDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { LabConstructionProjectAudit.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new LabConstructionProjectAuditDAOImpl
	 *
	 */
	public LabConstructionProjectAuditDAOImpl() {
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
	 * JPQL Query - findLabConstructionProjectAuditByComments
	 *
	 */
	@Transactional
	public Set<LabConstructionProjectAudit> findLabConstructionProjectAuditByComments(String comments) throws DataAccessException {

		return findLabConstructionProjectAuditByComments(comments, -1, -1);
	}

	/**
	 * JPQL Query - findLabConstructionProjectAuditByComments
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabConstructionProjectAudit> findLabConstructionProjectAuditByComments(String comments, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabConstructionProjectAuditByComments", startResult, maxRows, comments);
		return new LinkedHashSet<LabConstructionProjectAudit>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllLabConstructionProjectAudits
	 *
	 */
	@Transactional
	public Set<LabConstructionProjectAudit> findAllLabConstructionProjectAudits() throws DataAccessException {

		return findAllLabConstructionProjectAudits(-1, -1);
	}

	/**
	 * JPQL Query - findAllLabConstructionProjectAudits
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabConstructionProjectAudit> findAllLabConstructionProjectAudits(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllLabConstructionProjectAudits", startResult, maxRows);
		return new LinkedHashSet<LabConstructionProjectAudit>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabConstructionProjectAuditByPrimaryKey
	 *
	 */
	@Transactional
	public LabConstructionProjectAudit findLabConstructionProjectAuditByPrimaryKey(Integer id) throws DataAccessException {

		return findLabConstructionProjectAuditByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findLabConstructionProjectAuditByPrimaryKey
	 *
	 */

	@Transactional
	public LabConstructionProjectAudit findLabConstructionProjectAuditByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findLabConstructionProjectAuditByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.LabConstructionProjectAudit) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findLabConstructionProjectAuditById
	 *
	 */
	@Transactional
	public LabConstructionProjectAudit findLabConstructionProjectAuditById(Integer id) throws DataAccessException {

		return findLabConstructionProjectAuditById(id, -1, -1);
	}

	/**
	 * JPQL Query - findLabConstructionProjectAuditById
	 *
	 */

	@Transactional
	public LabConstructionProjectAudit findLabConstructionProjectAuditById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findLabConstructionProjectAuditById", startResult, maxRows, id);
			return (net.xidlims.domain.LabConstructionProjectAudit) query.getSingleResult();
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
	public boolean canBeMerged(LabConstructionProjectAudit entity) {
		return true;
	}
}
