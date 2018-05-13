package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.TAssignmentAnswer;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage TAssignmentAnswer entities.
 * 
 */
@Repository("TAssignmentAnswerDAO")
@Transactional
public class TAssignmentAnswerDAOImpl extends AbstractJpaDao<TAssignmentAnswer>
		implements TAssignmentAnswerDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { TAssignmentAnswer.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new TAssignmentAnswerDAOImpl
	 *
	 */
	public TAssignmentAnswerDAOImpl() {
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
	 * JPQL Query - findAllTAssignmentAnswers
	 *
	 */
	@Transactional
	public Set<TAssignmentAnswer> findAllTAssignmentAnswers() throws DataAccessException {

		return findAllTAssignmentAnswers(-1, -1);
	}

	/**
	 * JPQL Query - findAllTAssignmentAnswers
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TAssignmentAnswer> findAllTAssignmentAnswers(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllTAssignmentAnswers", startResult, maxRows);
		return new LinkedHashSet<TAssignmentAnswer>(query.getResultList());
	}

	/**
	 * JPQL Query - findTAssignmentAnswerByText
	 *
	 */
	@Transactional
	public Set<TAssignmentAnswer> findTAssignmentAnswerByText(String text) throws DataAccessException {

		return findTAssignmentAnswerByText(text, -1, -1);
	}

	/**
	 * JPQL Query - findTAssignmentAnswerByText
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TAssignmentAnswer> findTAssignmentAnswerByText(String text, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTAssignmentAnswerByText", startResult, maxRows, text);
		return new LinkedHashSet<TAssignmentAnswer>(query.getResultList());
	}

	/**
	 * JPQL Query - findTAssignmentAnswerByScore
	 *
	 */
	@Transactional
	public Set<TAssignmentAnswer> findTAssignmentAnswerByScore(java.math.BigDecimal score) throws DataAccessException {

		return findTAssignmentAnswerByScore(score, -1, -1);
	}

	/**
	 * JPQL Query - findTAssignmentAnswerByScore
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TAssignmentAnswer> findTAssignmentAnswerByScore(java.math.BigDecimal score, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTAssignmentAnswerByScore", startResult, maxRows, score);
		return new LinkedHashSet<TAssignmentAnswer>(query.getResultList());
	}

	/**
	 * JPQL Query - findTAssignmentAnswerByPrimaryKey
	 *
	 */
	@Transactional
	public TAssignmentAnswer findTAssignmentAnswerByPrimaryKey(Integer id) throws DataAccessException {

		return findTAssignmentAnswerByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findTAssignmentAnswerByPrimaryKey
	 *
	 */

	@Transactional
	public TAssignmentAnswer findTAssignmentAnswerByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findTAssignmentAnswerByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.TAssignmentAnswer) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findTAssignmentAnswerByGrade
	 *
	 */
	@Transactional
	public Set<TAssignmentAnswer> findTAssignmentAnswerByGrade(String grade) throws DataAccessException {

		return findTAssignmentAnswerByGrade(grade, -1, -1);
	}

	/**
	 * JPQL Query - findTAssignmentAnswerByGrade
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TAssignmentAnswer> findTAssignmentAnswerByGrade(String grade, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTAssignmentAnswerByGrade", startResult, maxRows, grade);
		return new LinkedHashSet<TAssignmentAnswer>(query.getResultList());
	}

	/**
	 * JPQL Query - findTAssignmentAnswerByLabel
	 *
	 */
	@Transactional
	public Set<TAssignmentAnswer> findTAssignmentAnswerByLabel(String label) throws DataAccessException {

		return findTAssignmentAnswerByLabel(label, -1, -1);
	}

	/**
	 * JPQL Query - findTAssignmentAnswerByLabel
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TAssignmentAnswer> findTAssignmentAnswerByLabel(String label, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTAssignmentAnswerByLabel", startResult, maxRows, label);
		return new LinkedHashSet<TAssignmentAnswer>(query.getResultList());
	}

	/**
	 * JPQL Query - findTAssignmentAnswerById
	 *
	 */
	@Transactional
	public TAssignmentAnswer findTAssignmentAnswerById(Integer id) throws DataAccessException {

		return findTAssignmentAnswerById(id, -1, -1);
	}

	/**
	 * JPQL Query - findTAssignmentAnswerById
	 *
	 */

	@Transactional
	public TAssignmentAnswer findTAssignmentAnswerById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findTAssignmentAnswerById", startResult, maxRows, id);
			return (net.xidlims.domain.TAssignmentAnswer) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findTAssignmentAnswerByLabelContaining
	 *
	 */
	@Transactional
	public Set<TAssignmentAnswer> findTAssignmentAnswerByLabelContaining(String label) throws DataAccessException {

		return findTAssignmentAnswerByLabelContaining(label, -1, -1);
	}

	/**
	 * JPQL Query - findTAssignmentAnswerByLabelContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TAssignmentAnswer> findTAssignmentAnswerByLabelContaining(String label, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTAssignmentAnswerByLabelContaining", startResult, maxRows, label);
		return new LinkedHashSet<TAssignmentAnswer>(query.getResultList());
	}

	/**
	 * JPQL Query - findTAssignmentAnswerByIscorrect
	 *
	 */
	@Transactional
	public Set<TAssignmentAnswer> findTAssignmentAnswerByIscorrect(Integer iscorrect) throws DataAccessException {

		return findTAssignmentAnswerByIscorrect(iscorrect, -1, -1);
	}

	/**
	 * JPQL Query - findTAssignmentAnswerByIscorrect
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TAssignmentAnswer> findTAssignmentAnswerByIscorrect(Integer iscorrect, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTAssignmentAnswerByIscorrect", startResult, maxRows, iscorrect);
		return new LinkedHashSet<TAssignmentAnswer>(query.getResultList());
	}

	/**
	 * JPQL Query - findTAssignmentAnswerByGradeContaining
	 *
	 */
	@Transactional
	public Set<TAssignmentAnswer> findTAssignmentAnswerByGradeContaining(String grade) throws DataAccessException {

		return findTAssignmentAnswerByGradeContaining(grade, -1, -1);
	}

	/**
	 * JPQL Query - findTAssignmentAnswerByGradeContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TAssignmentAnswer> findTAssignmentAnswerByGradeContaining(String grade, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTAssignmentAnswerByGradeContaining", startResult, maxRows, grade);
		return new LinkedHashSet<TAssignmentAnswer>(query.getResultList());
	}

	/**
	 * JPQL Query - findTAssignmentAnswerByTextContaining
	 *
	 */
	@Transactional
	public Set<TAssignmentAnswer> findTAssignmentAnswerByTextContaining(String text) throws DataAccessException {

		return findTAssignmentAnswerByTextContaining(text, -1, -1);
	}

	/**
	 * JPQL Query - findTAssignmentAnswerByTextContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TAssignmentAnswer> findTAssignmentAnswerByTextContaining(String text, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTAssignmentAnswerByTextContaining", startResult, maxRows, text);
		return new LinkedHashSet<TAssignmentAnswer>(query.getResultList());
	}

	/**
	 * JPQL Query - findTAssignmentAnswerBySequence
	 *
	 */
	@Transactional
	public Set<TAssignmentAnswer> findTAssignmentAnswerBySequence(Integer sequence) throws DataAccessException {

		return findTAssignmentAnswerBySequence(sequence, -1, -1);
	}

	/**
	 * JPQL Query - findTAssignmentAnswerBySequence
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TAssignmentAnswer> findTAssignmentAnswerBySequence(Integer sequence, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTAssignmentAnswerBySequence", startResult, maxRows, sequence);
		return new LinkedHashSet<TAssignmentAnswer>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(TAssignmentAnswer entity) {
		return true;
	}
}
