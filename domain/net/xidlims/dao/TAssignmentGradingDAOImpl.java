package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.TAssignmentGrading;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage TAssignmentGrading entities.
 * 
 */
@Repository("TAssignmentGradingDAO")
@Transactional
public class TAssignmentGradingDAOImpl extends AbstractJpaDao<TAssignmentGrading>
		implements TAssignmentGradingDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { TAssignmentGrading.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new TAssignmentGradingDAOImpl
	 *
	 */
	public TAssignmentGradingDAOImpl() {
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
	 * JPQL Query - findTAssignmentGradingByAccessmentgradingId
	 *
	 */
	@Transactional
	public TAssignmentGrading findTAssignmentGradingByAccessmentgradingId(Integer accessmentgradingId) throws DataAccessException {

		return findTAssignmentGradingByAccessmentgradingId(accessmentgradingId, -1, -1);
	}

	/**
	 * JPQL Query - findTAssignmentGradingByAccessmentgradingId
	 *
	 */

	@Transactional
	public TAssignmentGrading findTAssignmentGradingByAccessmentgradingId(Integer accessmentgradingId, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findTAssignmentGradingByAccessmentgradingId", startResult, maxRows, accessmentgradingId);
			return (net.xidlims.domain.TAssignmentGrading) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findAllTAssignmentGradings
	 *
	 */
	@Transactional
	public Set<TAssignmentGrading> findAllTAssignmentGradings() throws DataAccessException {

		return findAllTAssignmentGradings(-1, -1);
	}

	/**
	 * JPQL Query - findAllTAssignmentGradings
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TAssignmentGrading> findAllTAssignmentGradings(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllTAssignmentGradings", startResult, maxRows);
		return new LinkedHashSet<TAssignmentGrading>(query.getResultList());
	}

	/**
	 * JPQL Query - findTAssignmentGradingByPrimaryKey
	 *
	 */
	@Transactional
	public TAssignmentGrading findTAssignmentGradingByPrimaryKey(Integer accessmentgradingId) throws DataAccessException {

		return findTAssignmentGradingByPrimaryKey(accessmentgradingId, -1, -1);
	}

	/**
	 * JPQL Query - findTAssignmentGradingByPrimaryKey
	 *
	 */

	@Transactional
	public TAssignmentGrading findTAssignmentGradingByPrimaryKey(Integer accessmentgradingId, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findTAssignmentGradingByPrimaryKey", startResult, maxRows, accessmentgradingId);
			return (net.xidlims.domain.TAssignmentGrading) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findTAssignmentGradingByIslate
	 *
	 */
	@Transactional
	public Set<TAssignmentGrading> findTAssignmentGradingByIslate(Integer islate) throws DataAccessException {

		return findTAssignmentGradingByIslate(islate, -1, -1);
	}

	/**
	 * JPQL Query - findTAssignmentGradingByIslate
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TAssignmentGrading> findTAssignmentGradingByIslate(Integer islate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTAssignmentGradingByIslate", startResult, maxRows, islate);
		return new LinkedHashSet<TAssignmentGrading>(query.getResultList());
	}

	/**
	 * JPQL Query - findTAssignmentGradingBySubmitdate
	 *
	 */
	@Transactional
	public Set<TAssignmentGrading> findTAssignmentGradingBySubmitdate(java.util.Calendar submitdate) throws DataAccessException {

		return findTAssignmentGradingBySubmitdate(submitdate, -1, -1);
	}

	/**
	 * JPQL Query - findTAssignmentGradingBySubmitdate
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TAssignmentGrading> findTAssignmentGradingBySubmitdate(java.util.Calendar submitdate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTAssignmentGradingBySubmitdate", startResult, maxRows, submitdate);
		return new LinkedHashSet<TAssignmentGrading>(query.getResultList());
	}

	/**
	 * JPQL Query - findTAssignmentGradingByComments
	 *
	 */
	@Transactional
	public Set<TAssignmentGrading> findTAssignmentGradingByComments(String comments) throws DataAccessException {

		return findTAssignmentGradingByComments(comments, -1, -1);
	}

	/**
	 * JPQL Query - findTAssignmentGradingByComments
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TAssignmentGrading> findTAssignmentGradingByComments(String comments, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTAssignmentGradingByComments", startResult, maxRows, comments);
		return new LinkedHashSet<TAssignmentGrading>(query.getResultList());
	}

	/**
	 * JPQL Query - findTAssignmentGradingByCommentsContaining
	 *
	 */
	@Transactional
	public Set<TAssignmentGrading> findTAssignmentGradingByCommentsContaining(String comments) throws DataAccessException {

		return findTAssignmentGradingByCommentsContaining(comments, -1, -1);
	}

	/**
	 * JPQL Query - findTAssignmentGradingByCommentsContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TAssignmentGrading> findTAssignmentGradingByCommentsContaining(String comments, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTAssignmentGradingByCommentsContaining", startResult, maxRows, comments);
		return new LinkedHashSet<TAssignmentGrading>(query.getResultList());
	}

	/**
	 * JPQL Query - findTAssignmentGradingByFinalScore
	 *
	 */
	@Transactional
	public Set<TAssignmentGrading> findTAssignmentGradingByFinalScore(java.math.BigDecimal finalScore) throws DataAccessException {

		return findTAssignmentGradingByFinalScore(finalScore, -1, -1);
	}

	/**
	 * JPQL Query - findTAssignmentGradingByFinalScore
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TAssignmentGrading> findTAssignmentGradingByFinalScore(java.math.BigDecimal finalScore, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTAssignmentGradingByFinalScore", startResult, maxRows, finalScore);
		return new LinkedHashSet<TAssignmentGrading>(query.getResultList());
	}

	/**
	 * JPQL Query - findTAssignmentGradingByGradeTime
	 *
	 */
	@Transactional
	public Set<TAssignmentGrading> findTAssignmentGradingByGradeTime(java.util.Calendar gradeTime) throws DataAccessException {

		return findTAssignmentGradingByGradeTime(gradeTime, -1, -1);
	}

	/**
	 * JPQL Query - findTAssignmentGradingByGradeTime
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TAssignmentGrading> findTAssignmentGradingByGradeTime(java.util.Calendar gradeTime, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTAssignmentGradingByGradeTime", startResult, maxRows, gradeTime);
		return new LinkedHashSet<TAssignmentGrading>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(TAssignmentGrading entity) {
		return true;
	}
}
