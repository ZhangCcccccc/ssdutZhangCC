package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.ExpendableApplyAuditRecord;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage ExpendableApplyAuditRecord entities.
 * 
 */
@Repository("ExpendableApplyAuditRecordDAO")
@Transactional
public class ExpendableApplyAuditRecordDAOImpl extends AbstractJpaDao<ExpendableApplyAuditRecord>
		implements ExpendableApplyAuditRecordDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { ExpendableApplyAuditRecord.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new ExpendableApplyAuditRecordDAOImpl
	 *
	 */
	public ExpendableApplyAuditRecordDAOImpl() {
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
	 * JPQL Query - findAllExpendableApplyAuditRecords
	 *
	 */
	@Transactional
	public Set<ExpendableApplyAuditRecord> findAllExpendableApplyAuditRecords() throws DataAccessException {

		return findAllExpendableApplyAuditRecords(-1, -1);
	}

	/**
	 * JPQL Query - findAllExpendableApplyAuditRecords
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<ExpendableApplyAuditRecord> findAllExpendableApplyAuditRecords(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllExpendableApplyAuditRecords", startResult, maxRows);
		return new LinkedHashSet<ExpendableApplyAuditRecord>(query.getResultList());
	}

	/**
	 * JPQL Query - findExpendableApplyAuditRecordByRemarkContaining
	 *
	 */
	@Transactional
	public Set<ExpendableApplyAuditRecord> findExpendableApplyAuditRecordByRemarkContaining(String remark) throws DataAccessException {

		return findExpendableApplyAuditRecordByRemarkContaining(remark, -1, -1);
	}

	/**
	 * JPQL Query - findExpendableApplyAuditRecordByRemarkContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<ExpendableApplyAuditRecord> findExpendableApplyAuditRecordByRemarkContaining(String remark, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findExpendableApplyAuditRecordByRemarkContaining", startResult, maxRows, remark);
		return new LinkedHashSet<ExpendableApplyAuditRecord>(query.getResultList());
	}

	/**
	 * JPQL Query - findExpendableApplyAuditRecordByRemark
	 *
	 */
	@Transactional
	public Set<ExpendableApplyAuditRecord> findExpendableApplyAuditRecordByRemark(String remark) throws DataAccessException {

		return findExpendableApplyAuditRecordByRemark(remark, -1, -1);
	}

	/**
	 * JPQL Query - findExpendableApplyAuditRecordByRemark
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<ExpendableApplyAuditRecord> findExpendableApplyAuditRecordByRemark(String remark, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findExpendableApplyAuditRecordByRemark", startResult, maxRows, remark);
		return new LinkedHashSet<ExpendableApplyAuditRecord>(query.getResultList());
	}

	/**
	 * JPQL Query - findExpendableApplyAuditRecordById
	 *
	 */
	@Transactional
	public ExpendableApplyAuditRecord findExpendableApplyAuditRecordById(Integer id) throws DataAccessException {

		return findExpendableApplyAuditRecordById(id, -1, -1);
	}

	/**
	 * JPQL Query - findExpendableApplyAuditRecordById
	 *
	 */

	@Transactional
	public ExpendableApplyAuditRecord findExpendableApplyAuditRecordById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findExpendableApplyAuditRecordById", startResult, maxRows, id);
			return (net.xidlims.domain.ExpendableApplyAuditRecord) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findExpendableApplyAuditRecordByAuditResult
	 *
	 */
	@Transactional
	public Set<ExpendableApplyAuditRecord> findExpendableApplyAuditRecordByAuditResult(Integer auditResult) throws DataAccessException {

		return findExpendableApplyAuditRecordByAuditResult(auditResult, -1, -1);
	}

	/**
	 * JPQL Query - findExpendableApplyAuditRecordByAuditResult
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<ExpendableApplyAuditRecord> findExpendableApplyAuditRecordByAuditResult(Integer auditResult, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findExpendableApplyAuditRecordByAuditResult", startResult, maxRows, auditResult);
		return new LinkedHashSet<ExpendableApplyAuditRecord>(query.getResultList());
	}

	/**
	 * JPQL Query - findExpendableApplyAuditRecordByAuditDate
	 *
	 */
	@Transactional
	public Set<ExpendableApplyAuditRecord> findExpendableApplyAuditRecordByAuditDate(java.util.Calendar auditDate) throws DataAccessException {

		return findExpendableApplyAuditRecordByAuditDate(auditDate, -1, -1);
	}

	/**
	 * JPQL Query - findExpendableApplyAuditRecordByAuditDate
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<ExpendableApplyAuditRecord> findExpendableApplyAuditRecordByAuditDate(java.util.Calendar auditDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findExpendableApplyAuditRecordByAuditDate", startResult, maxRows, auditDate);
		return new LinkedHashSet<ExpendableApplyAuditRecord>(query.getResultList());
	}

	/**
	 * JPQL Query - findExpendableApplyAuditRecordByPrimaryKey
	 *
	 */
	@Transactional
	public ExpendableApplyAuditRecord findExpendableApplyAuditRecordByPrimaryKey(Integer id) throws DataAccessException {

		return findExpendableApplyAuditRecordByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findExpendableApplyAuditRecordByPrimaryKey
	 *
	 */

	@Transactional
	public ExpendableApplyAuditRecord findExpendableApplyAuditRecordByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findExpendableApplyAuditRecordByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.ExpendableApplyAuditRecord) query.getSingleResult();
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
	public boolean canBeMerged(ExpendableApplyAuditRecord entity) {
		return true;
	}
}
