package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.ExpendableStockRecord;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage ExpendableStockRecord entities.
 * 
 */
@Repository("ExpendableStockRecordDAO")
@Transactional
public class ExpendableStockRecordDAOImpl extends AbstractJpaDao<ExpendableStockRecord>
		implements ExpendableStockRecordDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { ExpendableStockRecord.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new ExpendableStockRecordDAOImpl
	 *
	 */
	public ExpendableStockRecordDAOImpl() {
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
	 * JPQL Query - findExpendableStockRecordByPrimaryKey
	 *
	 */
	@Transactional
	public ExpendableStockRecord findExpendableStockRecordByPrimaryKey(Integer id) throws DataAccessException {

		return findExpendableStockRecordByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findExpendableStockRecordByPrimaryKey
	 *
	 */

	@Transactional
	public ExpendableStockRecord findExpendableStockRecordByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findExpendableStockRecordByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.ExpendableStockRecord) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findExpendableStockRecordByUseType
	 *
	 */
	@Transactional
	public Set<ExpendableStockRecord> findExpendableStockRecordByUseType(String useType) throws DataAccessException {

		return findExpendableStockRecordByUseType(useType, -1, -1);
	}

	/**
	 * JPQL Query - findExpendableStockRecordByUseType
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<ExpendableStockRecord> findExpendableStockRecordByUseType(String useType, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findExpendableStockRecordByUseType", startResult, maxRows, useType);
		return new LinkedHashSet<ExpendableStockRecord>(query.getResultList());
	}

	/**
	 * JPQL Query - findExpendableStockRecordByStockDate
	 *
	 */
	@Transactional
	public Set<ExpendableStockRecord> findExpendableStockRecordByStockDate(java.util.Calendar stockDate) throws DataAccessException {

		return findExpendableStockRecordByStockDate(stockDate, -1, -1);
	}

	/**
	 * JPQL Query - findExpendableStockRecordByStockDate
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<ExpendableStockRecord> findExpendableStockRecordByStockDate(java.util.Calendar stockDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findExpendableStockRecordByStockDate", startResult, maxRows, stockDate);
		return new LinkedHashSet<ExpendableStockRecord>(query.getResultList());
	}

	/**
	 * JPQL Query - findExpendableStockRecordById
	 *
	 */
	@Transactional
	public ExpendableStockRecord findExpendableStockRecordById(Integer id) throws DataAccessException {

		return findExpendableStockRecordById(id, -1, -1);
	}

	/**
	 * JPQL Query - findExpendableStockRecordById
	 *
	 */

	@Transactional
	public ExpendableStockRecord findExpendableStockRecordById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findExpendableStockRecordById", startResult, maxRows, id);
			return (net.xidlims.domain.ExpendableStockRecord) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findExpendableStockRecordByStockNumber
	 *
	 */
	@Transactional
	public Set<ExpendableStockRecord> findExpendableStockRecordByStockNumber(Integer stockNumber) throws DataAccessException {

		return findExpendableStockRecordByStockNumber(stockNumber, -1, -1);
	}

	/**
	 * JPQL Query - findExpendableStockRecordByStockNumber
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<ExpendableStockRecord> findExpendableStockRecordByStockNumber(Integer stockNumber, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findExpendableStockRecordByStockNumber", startResult, maxRows, stockNumber);
		return new LinkedHashSet<ExpendableStockRecord>(query.getResultList());
	}

	/**
	 * JPQL Query - findExpendableStockRecordByRemark
	 *
	 */
	@Transactional
	public Set<ExpendableStockRecord> findExpendableStockRecordByRemark(String remark) throws DataAccessException {

		return findExpendableStockRecordByRemark(remark, -1, -1);
	}

	/**
	 * JPQL Query - findExpendableStockRecordByRemark
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<ExpendableStockRecord> findExpendableStockRecordByRemark(String remark, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findExpendableStockRecordByRemark", startResult, maxRows, remark);
		return new LinkedHashSet<ExpendableStockRecord>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllExpendableStockRecords
	 *
	 */
	@Transactional
	public Set<ExpendableStockRecord> findAllExpendableStockRecords() throws DataAccessException {

		return findAllExpendableStockRecords(-1, -1);
	}

	/**
	 * JPQL Query - findAllExpendableStockRecords
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<ExpendableStockRecord> findAllExpendableStockRecords(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllExpendableStockRecords", startResult, maxRows);
		return new LinkedHashSet<ExpendableStockRecord>(query.getResultList());
	}

	/**
	 * JPQL Query - findExpendableStockRecordByRemarkContaining
	 *
	 */
	@Transactional
	public Set<ExpendableStockRecord> findExpendableStockRecordByRemarkContaining(String remark) throws DataAccessException {

		return findExpendableStockRecordByRemarkContaining(remark, -1, -1);
	}

	/**
	 * JPQL Query - findExpendableStockRecordByRemarkContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<ExpendableStockRecord> findExpendableStockRecordByRemarkContaining(String remark, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findExpendableStockRecordByRemarkContaining", startResult, maxRows, remark);
		return new LinkedHashSet<ExpendableStockRecord>(query.getResultList());
	}

	/**
	 * JPQL Query - findExpendableStockRecordByUseTypeContaining
	 *
	 */
	@Transactional
	public Set<ExpendableStockRecord> findExpendableStockRecordByUseTypeContaining(String useType) throws DataAccessException {

		return findExpendableStockRecordByUseTypeContaining(useType, -1, -1);
	}

	/**
	 * JPQL Query - findExpendableStockRecordByUseTypeContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<ExpendableStockRecord> findExpendableStockRecordByUseTypeContaining(String useType, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findExpendableStockRecordByUseTypeContaining", startResult, maxRows, useType);
		return new LinkedHashSet<ExpendableStockRecord>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(ExpendableStockRecord entity) {
		return true;
	}
}
