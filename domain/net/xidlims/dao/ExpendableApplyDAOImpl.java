package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.ExpendableApply;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage ExpendableApply entities.
 * 
 */
@Repository("ExpendableApplyDAO")
@Transactional
public class ExpendableApplyDAOImpl extends AbstractJpaDao<ExpendableApply>
		implements ExpendableApplyDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { ExpendableApply.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new ExpendableApplyDAOImpl
	 *
	 */
	public ExpendableApplyDAOImpl() {
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
	 * JPQL Query - findExpendableApplyByFlag
	 *
	 */
	@Transactional
	public Set<ExpendableApply> findExpendableApplyByFlag(Integer flag) throws DataAccessException {

		return findExpendableApplyByFlag(flag, -1, -1);
	}

	/**
	 * JPQL Query - findExpendableApplyByFlag
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<ExpendableApply> findExpendableApplyByFlag(Integer flag, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findExpendableApplyByFlag", startResult, maxRows, flag);
		return new LinkedHashSet<ExpendableApply>(query.getResultList());
	}

	/**
	 * JPQL Query - findExpendableApplyByBorrowTime
	 *
	 */
	@Transactional
	public Set<ExpendableApply> findExpendableApplyByBorrowTime(java.util.Calendar borrowTime) throws DataAccessException {

		return findExpendableApplyByBorrowTime(borrowTime, -1, -1);
	}

	/**
	 * JPQL Query - findExpendableApplyByBorrowTime
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<ExpendableApply> findExpendableApplyByBorrowTime(java.util.Calendar borrowTime, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findExpendableApplyByBorrowTime", startResult, maxRows, borrowTime);
		return new LinkedHashSet<ExpendableApply>(query.getResultList());
	}

	/**
	 * JPQL Query - findExpendableApplyByRemarksContaining
	 *
	 */
	@Transactional
	public Set<ExpendableApply> findExpendableApplyByRemarksContaining(String remarks) throws DataAccessException {

		return findExpendableApplyByRemarksContaining(remarks, -1, -1);
	}

	/**
	 * JPQL Query - findExpendableApplyByRemarksContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<ExpendableApply> findExpendableApplyByRemarksContaining(String remarks, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findExpendableApplyByRemarksContaining", startResult, maxRows, remarks);
		return new LinkedHashSet<ExpendableApply>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllExpendableApplys
	 *
	 */
	@Transactional
	public Set<ExpendableApply> findAllExpendableApplys() throws DataAccessException {

		return findAllExpendableApplys(-1, -1);
	}

	/**
	 * JPQL Query - findAllExpendableApplys
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<ExpendableApply> findAllExpendableApplys(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllExpendableApplys", startResult, maxRows);
		return new LinkedHashSet<ExpendableApply>(query.getResultList());
	}

	/**
	 * JPQL Query - findExpendableApplyByRemarks
	 *
	 */
	@Transactional
	public Set<ExpendableApply> findExpendableApplyByRemarks(String remarks) throws DataAccessException {

		return findExpendableApplyByRemarks(remarks, -1, -1);
	}

	/**
	 * JPQL Query - findExpendableApplyByRemarks
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<ExpendableApply> findExpendableApplyByRemarks(String remarks, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findExpendableApplyByRemarks", startResult, maxRows, remarks);
		return new LinkedHashSet<ExpendableApply>(query.getResultList());
	}

	/**
	 * JPQL Query - findExpendableApplyByExpendableNumber
	 *
	 */
	@Transactional
	public Set<ExpendableApply> findExpendableApplyByExpendableNumber(Integer expendableNumber) throws DataAccessException {

		return findExpendableApplyByExpendableNumber(expendableNumber, -1, -1);
	}

	/**
	 * JPQL Query - findExpendableApplyByExpendableNumber
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<ExpendableApply> findExpendableApplyByExpendableNumber(Integer expendableNumber, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findExpendableApplyByExpendableNumber", startResult, maxRows, expendableNumber);
		return new LinkedHashSet<ExpendableApply>(query.getResultList());
	}

	/**
	 * JPQL Query - findExpendableApplyById
	 *
	 */
	@Transactional
	public ExpendableApply findExpendableApplyById(Integer id) throws DataAccessException {

		return findExpendableApplyById(id, -1, -1);
	}

	/**
	 * JPQL Query - findExpendableApplyById
	 *
	 */

	@Transactional
	public ExpendableApply findExpendableApplyById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findExpendableApplyById", startResult, maxRows, id);
			return (net.xidlims.domain.ExpendableApply) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findExpendableApplyByPrimaryKey
	 *
	 */
	@Transactional
	public ExpendableApply findExpendableApplyByPrimaryKey(Integer id) throws DataAccessException {

		return findExpendableApplyByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findExpendableApplyByPrimaryKey
	 *
	 */

	@Transactional
	public ExpendableApply findExpendableApplyByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findExpendableApplyByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.ExpendableApply) query.getSingleResult();
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
	public boolean canBeMerged(ExpendableApply entity) {
		return true;
	}
}
