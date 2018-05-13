package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.NDeviceAuditRecord;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage NDeviceAuditRecord entities.
 * 
 */
@Repository("NDeviceAuditRecordDAO")
@Transactional
public class NDeviceAuditRecordDAOImpl extends AbstractJpaDao<NDeviceAuditRecord>
		implements NDeviceAuditRecordDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { NDeviceAuditRecord.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new NDeviceAuditRecordDAOImpl
	 *
	 */
	public NDeviceAuditRecordDAOImpl() {
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
	 * JPQL Query - findNDeviceAuditRecordByAuditDate
	 *
	 */
	@Transactional
	public Set<NDeviceAuditRecord> findNDeviceAuditRecordByAuditDate(java.util.Calendar auditDate) throws DataAccessException {

		return findNDeviceAuditRecordByAuditDate(auditDate, -1, -1);
	}

	/**
	 * JPQL Query - findNDeviceAuditRecordByAuditDate
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<NDeviceAuditRecord> findNDeviceAuditRecordByAuditDate(java.util.Calendar auditDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findNDeviceAuditRecordByAuditDate", startResult, maxRows, auditDate);
		return new LinkedHashSet<NDeviceAuditRecord>(query.getResultList());
	}

	/**
	 * JPQL Query - findNDeviceAuditRecordByFlag
	 *
	 */
	@Transactional
	public Set<NDeviceAuditRecord> findNDeviceAuditRecordByFlag(Integer flag) throws DataAccessException {

		return findNDeviceAuditRecordByFlag(flag, -1, -1);
	}

	/**
	 * JPQL Query - findNDeviceAuditRecordByFlag
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<NDeviceAuditRecord> findNDeviceAuditRecordByFlag(Integer flag, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findNDeviceAuditRecordByFlag", startResult, maxRows, flag);
		return new LinkedHashSet<NDeviceAuditRecord>(query.getResultList());
	}

	/**
	 * JPQL Query - findNDeviceAuditRecordByRemarkContaining
	 *
	 */
	@Transactional
	public Set<NDeviceAuditRecord> findNDeviceAuditRecordByRemarkContaining(String remark) throws DataAccessException {

		return findNDeviceAuditRecordByRemarkContaining(remark, -1, -1);
	}

	/**
	 * JPQL Query - findNDeviceAuditRecordByRemarkContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<NDeviceAuditRecord> findNDeviceAuditRecordByRemarkContaining(String remark, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findNDeviceAuditRecordByRemarkContaining", startResult, maxRows, remark);
		return new LinkedHashSet<NDeviceAuditRecord>(query.getResultList());
	}

	/**
	 * JPQL Query - findNDeviceAuditRecordByRemark
	 *
	 */
	@Transactional
	public Set<NDeviceAuditRecord> findNDeviceAuditRecordByRemark(String remark) throws DataAccessException {

		return findNDeviceAuditRecordByRemark(remark, -1, -1);
	}

	/**
	 * JPQL Query - findNDeviceAuditRecordByRemark
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<NDeviceAuditRecord> findNDeviceAuditRecordByRemark(String remark, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findNDeviceAuditRecordByRemark", startResult, maxRows, remark);
		return new LinkedHashSet<NDeviceAuditRecord>(query.getResultList());
	}

	/**
	 * JPQL Query - findNDeviceAuditRecordByPrimaryKey
	 *
	 */
	@Transactional
	public NDeviceAuditRecord findNDeviceAuditRecordByPrimaryKey(Integer id) throws DataAccessException {

		return findNDeviceAuditRecordByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findNDeviceAuditRecordByPrimaryKey
	 *
	 */

	@Transactional
	public NDeviceAuditRecord findNDeviceAuditRecordByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findNDeviceAuditRecordByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.NDeviceAuditRecord) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findNDeviceAuditRecordById
	 *
	 */
	@Transactional
	public NDeviceAuditRecord findNDeviceAuditRecordById(Integer id) throws DataAccessException {

		return findNDeviceAuditRecordById(id, -1, -1);
	}

	/**
	 * JPQL Query - findNDeviceAuditRecordById
	 *
	 */

	@Transactional
	public NDeviceAuditRecord findNDeviceAuditRecordById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findNDeviceAuditRecordById", startResult, maxRows, id);
			return (net.xidlims.domain.NDeviceAuditRecord) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findAllNDeviceAuditRecords
	 *
	 */
	@Transactional
	public Set<NDeviceAuditRecord> findAllNDeviceAuditRecords() throws DataAccessException {

		return findAllNDeviceAuditRecords(-1, -1);
	}

	/**
	 * JPQL Query - findAllNDeviceAuditRecords
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<NDeviceAuditRecord> findAllNDeviceAuditRecords(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllNDeviceAuditRecords", startResult, maxRows);
		return new LinkedHashSet<NDeviceAuditRecord>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(NDeviceAuditRecord entity) {
		return true;
	}
}
