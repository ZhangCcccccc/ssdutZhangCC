package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.TExerciseAnswerRecord;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage TExerciseAnswerRecord entities.
 * 
 */
@Repository("TExerciseAnswerRecordDAO")
@Transactional
public class TExerciseAnswerRecordDAOImpl extends AbstractJpaDao<TExerciseAnswerRecord>
		implements TExerciseAnswerRecordDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { TExerciseAnswerRecord.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new TExerciseAnswerRecordDAOImpl
	 *
	 */
	public TExerciseAnswerRecordDAOImpl() {
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
	 * JPQL Query - findAllTExerciseAnswerRecords
	 *
	 */
	@Transactional
	public Set<TExerciseAnswerRecord> findAllTExerciseAnswerRecords() throws DataAccessException {

		return findAllTExerciseAnswerRecords(-1, -1);
	}

	/**
	 * JPQL Query - findAllTExerciseAnswerRecords
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TExerciseAnswerRecord> findAllTExerciseAnswerRecords(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllTExerciseAnswerRecords", startResult, maxRows);
		return new LinkedHashSet<TExerciseAnswerRecord>(query.getResultList());
	}

	/**
	 * JPQL Query - findTExerciseAnswerRecordById
	 *
	 */
	@Transactional
	public TExerciseAnswerRecord findTExerciseAnswerRecordById(Integer id) throws DataAccessException {

		return findTExerciseAnswerRecordById(id, -1, -1);
	}

	/**
	 * JPQL Query - findTExerciseAnswerRecordById
	 *
	 */

	@Transactional
	public TExerciseAnswerRecord findTExerciseAnswerRecordById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findTExerciseAnswerRecordById", startResult, maxRows, id);
			return (net.xidlims.domain.TExerciseAnswerRecord) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findTExerciseAnswerRecordByAnswerTextContaining
	 *
	 */
	@Transactional
	public Set<TExerciseAnswerRecord> findTExerciseAnswerRecordByAnswerTextContaining(String answerText) throws DataAccessException {

		return findTExerciseAnswerRecordByAnswerTextContaining(answerText, -1, -1);
	}

	/**
	 * JPQL Query - findTExerciseAnswerRecordByAnswerTextContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TExerciseAnswerRecord> findTExerciseAnswerRecordByAnswerTextContaining(String answerText, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTExerciseAnswerRecordByAnswerTextContaining", startResult, maxRows, answerText);
		return new LinkedHashSet<TExerciseAnswerRecord>(query.getResultList());
	}

	/**
	 * JPQL Query - findTExerciseAnswerRecordByPrimaryKey
	 *
	 */
	@Transactional
	public TExerciseAnswerRecord findTExerciseAnswerRecordByPrimaryKey(Integer id) throws DataAccessException {

		return findTExerciseAnswerRecordByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findTExerciseAnswerRecordByPrimaryKey
	 *
	 */

	@Transactional
	public TExerciseAnswerRecord findTExerciseAnswerRecordByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findTExerciseAnswerRecordByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.TExerciseAnswerRecord) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findTExerciseAnswerRecordByAnswerText
	 *
	 */
	@Transactional
	public Set<TExerciseAnswerRecord> findTExerciseAnswerRecordByAnswerText(String answerText) throws DataAccessException {

		return findTExerciseAnswerRecordByAnswerText(answerText, -1, -1);
	}

	/**
	 * JPQL Query - findTExerciseAnswerRecordByAnswerText
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TExerciseAnswerRecord> findTExerciseAnswerRecordByAnswerText(String answerText, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTExerciseAnswerRecordByAnswerText", startResult, maxRows, answerText);
		return new LinkedHashSet<TExerciseAnswerRecord>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(TExerciseAnswerRecord entity) {
		return true;
	}
}
