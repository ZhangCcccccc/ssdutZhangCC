package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.LabReservationAudit;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage LabReservationAudit entities.
 * 
 */
@Repository("LabReservationAuditDAO")
@Transactional
public class LabReservationAuditDAOImpl extends AbstractJpaDao<LabReservationAudit>
		implements LabReservationAuditDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { LabReservationAudit.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new LabReservationAuditDAOImpl
	 *
	 */
	public LabReservationAuditDAOImpl() {
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
	 * JPQL Query - findLabReservationAuditByPrimaryKey
	 *
	 */
	@Transactional
	public LabReservationAudit findLabReservationAuditByPrimaryKey(Integer id) throws DataAccessException {

		return findLabReservationAuditByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findLabReservationAuditByPrimaryKey
	 *
	 */

	@Transactional
	public LabReservationAudit findLabReservationAuditByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findLabReservationAuditByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.LabReservationAudit) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findAllLabReservationAudits
	 *
	 */
	@Transactional
	public Set<LabReservationAudit> findAllLabReservationAudits() throws DataAccessException {

		return findAllLabReservationAudits(-1, -1);
	}

	/**
	 * JPQL Query - findAllLabReservationAudits
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabReservationAudit> findAllLabReservationAudits(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllLabReservationAudits", startResult, maxRows);
		return new LinkedHashSet<LabReservationAudit>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabReservationAuditById
	 *
	 */
	@Transactional
	public LabReservationAudit findLabReservationAuditById(Integer id) throws DataAccessException {

		return findLabReservationAuditById(id, -1, -1);
	}

	/**
	 * JPQL Query - findLabReservationAuditById
	 *
	 */

	@Transactional
	public LabReservationAudit findLabReservationAuditById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findLabReservationAuditById", startResult, maxRows, id);
			return (net.xidlims.domain.LabReservationAudit) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findLabReservationAuditByComments
	 *
	 */
	@Transactional
	public Set<LabReservationAudit> findLabReservationAuditByComments(String comments) throws DataAccessException {

		return findLabReservationAuditByComments(comments, -1, -1);
	}

	/**
	 * JPQL Query - findLabReservationAuditByComments
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabReservationAudit> findLabReservationAuditByComments(String comments, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabReservationAuditByComments", startResult, maxRows, comments);
		return new LinkedHashSet<LabReservationAudit>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(LabReservationAudit entity) {
		return true;
	}
}
