package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.TGradeRecord;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage TGradeRecord entities.
 * 
 */
@Repository("TGradeRecordDAO")
@Transactional
public class TGradeRecordDAOImpl extends AbstractJpaDao<TGradeRecord> implements
		TGradeRecordDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { TGradeRecord.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new TGradeRecordDAOImpl
	 *
	 */
	public TGradeRecordDAOImpl() {
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
	 * JPQL Query - findTGradeRecordById
	 *
	 */
	@Transactional
	public TGradeRecord findTGradeRecordById(Integer id) throws DataAccessException {

		return findTGradeRecordById(id, -1, -1);
	}

	/**
	 * JPQL Query - findTGradeRecordById
	 *
	 */

	@Transactional
	public TGradeRecord findTGradeRecordById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findTGradeRecordById", startResult, maxRows, id);
			return (net.xidlims.domain.TGradeRecord) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findTGradeRecordByPoints
	 *
	 */
	@Transactional
	public Set<TGradeRecord> findTGradeRecordByPoints(java.math.BigDecimal points) throws DataAccessException {

		return findTGradeRecordByPoints(points, -1, -1);
	}

	/**
	 * JPQL Query - findTGradeRecordByPoints
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TGradeRecord> findTGradeRecordByPoints(java.math.BigDecimal points, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTGradeRecordByPoints", startResult, maxRows, points);
		return new LinkedHashSet<TGradeRecord>(query.getResultList());
	}

	/**
	 * JPQL Query - findTGradeRecordByPrimaryKey
	 *
	 */
	@Transactional
	public TGradeRecord findTGradeRecordByPrimaryKey(Integer id) throws DataAccessException {

		return findTGradeRecordByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findTGradeRecordByPrimaryKey
	 *
	 */

	@Transactional
	public TGradeRecord findTGradeRecordByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findTGradeRecordByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.TGradeRecord) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findAllTGradeRecords
	 *
	 */
	@Transactional
	public Set<TGradeRecord> findAllTGradeRecords() throws DataAccessException {

		return findAllTGradeRecords(-1, -1);
	}

	/**
	 * JPQL Query - findAllTGradeRecords
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TGradeRecord> findAllTGradeRecords(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllTGradeRecords", startResult, maxRows);
		return new LinkedHashSet<TGradeRecord>(query.getResultList());
	}

	/**
	 * JPQL Query - findTGradeRecordByRecordTime
	 *
	 */
	@Transactional
	public Set<TGradeRecord> findTGradeRecordByRecordTime(java.util.Calendar recordTime) throws DataAccessException {

		return findTGradeRecordByRecordTime(recordTime, -1, -1);
	}

	/**
	 * JPQL Query - findTGradeRecordByRecordTime
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TGradeRecord> findTGradeRecordByRecordTime(java.util.Calendar recordTime, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTGradeRecordByRecordTime", startResult, maxRows, recordTime);
		return new LinkedHashSet<TGradeRecord>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(TGradeRecord entity) {
		return true;
	}
}
