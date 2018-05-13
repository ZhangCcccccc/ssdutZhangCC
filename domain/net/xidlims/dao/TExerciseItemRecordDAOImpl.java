package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.TExerciseItemRecord;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage TExerciseItemRecord entities.
 * 
 */
@Repository("TExerciseItemRecordDAO")
@Transactional
public class TExerciseItemRecordDAOImpl extends AbstractJpaDao<TExerciseItemRecord>
		implements TExerciseItemRecordDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { TExerciseItemRecord.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new TExerciseItemRecordDAOImpl
	 *
	 */
	public TExerciseItemRecordDAOImpl() {
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
	 * JPQL Query - findTExerciseItemRecordByPrimaryKey
	 *
	 */
	@Transactional
	public TExerciseItemRecord findTExerciseItemRecordByPrimaryKey(Integer id) throws DataAccessException {

		return findTExerciseItemRecordByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findTExerciseItemRecordByPrimaryKey
	 *
	 */

	@Transactional
	public TExerciseItemRecord findTExerciseItemRecordByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findTExerciseItemRecordByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.TExerciseItemRecord) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findTExerciseItemRecordByExerciseTypeContaining
	 *
	 */
	@Transactional
	public Set<TExerciseItemRecord> findTExerciseItemRecordByExerciseTypeContaining(String exerciseType) throws DataAccessException {

		return findTExerciseItemRecordByExerciseTypeContaining(exerciseType, -1, -1);
	}

	/**
	 * JPQL Query - findTExerciseItemRecordByExerciseTypeContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TExerciseItemRecord> findTExerciseItemRecordByExerciseTypeContaining(String exerciseType, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTExerciseItemRecordByExerciseTypeContaining", startResult, maxRows, exerciseType);
		return new LinkedHashSet<TExerciseItemRecord>(query.getResultList());
	}

	/**
	 * JPQL Query - findTExerciseItemRecordByExerciseType
	 *
	 */
	@Transactional
	public Set<TExerciseItemRecord> findTExerciseItemRecordByExerciseType(String exerciseType) throws DataAccessException {

		return findTExerciseItemRecordByExerciseType(exerciseType, -1, -1);
	}

	/**
	 * JPQL Query - findTExerciseItemRecordByExerciseType
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TExerciseItemRecord> findTExerciseItemRecordByExerciseType(String exerciseType, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTExerciseItemRecordByExerciseType", startResult, maxRows, exerciseType);
		return new LinkedHashSet<TExerciseItemRecord>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllTExerciseItemRecords
	 *
	 */
	@Transactional
	public Set<TExerciseItemRecord> findAllTExerciseItemRecords() throws DataAccessException {

		return findAllTExerciseItemRecords(-1, -1);
	}

	/**
	 * JPQL Query - findAllTExerciseItemRecords
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TExerciseItemRecord> findAllTExerciseItemRecords(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllTExerciseItemRecords", startResult, maxRows);
		return new LinkedHashSet<TExerciseItemRecord>(query.getResultList());
	}

	/**
	 * JPQL Query - findTExerciseItemRecordByIscorrect
	 *
	 */
	@Transactional
	public Set<TExerciseItemRecord> findTExerciseItemRecordByIscorrect(Integer iscorrect) throws DataAccessException {

		return findTExerciseItemRecordByIscorrect(iscorrect, -1, -1);
	}

	/**
	 * JPQL Query - findTExerciseItemRecordByIscorrect
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TExerciseItemRecord> findTExerciseItemRecordByIscorrect(Integer iscorrect, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTExerciseItemRecordByIscorrect", startResult, maxRows, iscorrect);
		return new LinkedHashSet<TExerciseItemRecord>(query.getResultList());
	}

	/**
	 * JPQL Query - findTExerciseItemRecordById
	 *
	 */
	@Transactional
	public TExerciseItemRecord findTExerciseItemRecordById(Integer id) throws DataAccessException {

		return findTExerciseItemRecordById(id, -1, -1);
	}

	/**
	 * JPQL Query - findTExerciseItemRecordById
	 *
	 */

	@Transactional
	public TExerciseItemRecord findTExerciseItemRecordById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findTExerciseItemRecordById", startResult, maxRows, id);
			return (net.xidlims.domain.TExerciseItemRecord) query.getSingleResult();
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
	public boolean canBeMerged(TExerciseItemRecord entity) {
		return true;
	}
}
