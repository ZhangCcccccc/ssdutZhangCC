package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.TAssignmentAnswerAssign;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage TAssignmentAnswerAssign entities.
 * 
 */
@Repository("TAssignmentAnswerAssignDAO")
@Transactional
public class TAssignmentAnswerAssignDAOImpl extends AbstractJpaDao<TAssignmentAnswerAssign>
		implements TAssignmentAnswerAssignDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { TAssignmentAnswerAssign.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new TAssignmentAnswerAssignDAOImpl
	 *
	 */
	public TAssignmentAnswerAssignDAOImpl() {
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
	 * JPQL Query - findTAssignmentAnswerAssignByGrade
	 *
	 */
	@Transactional
	public Set<TAssignmentAnswerAssign> findTAssignmentAnswerAssignByGrade(java.math.BigDecimal grade) throws DataAccessException {

		return findTAssignmentAnswerAssignByGrade(grade, -1, -1);
	}

	/**
	 * JPQL Query - findTAssignmentAnswerAssignByGrade
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TAssignmentAnswerAssign> findTAssignmentAnswerAssignByGrade(java.math.BigDecimal grade, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTAssignmentAnswerAssignByGrade", startResult, maxRows, grade);
		return new LinkedHashSet<TAssignmentAnswerAssign>(query.getResultList());
	}

	/**
	 * JPQL Query - findTAssignmentAnswerAssignById
	 *
	 */
	@Transactional
	public TAssignmentAnswerAssign findTAssignmentAnswerAssignById(Integer id) throws DataAccessException {

		return findTAssignmentAnswerAssignById(id, -1, -1);
	}

	/**
	 * JPQL Query - findTAssignmentAnswerAssignById
	 *
	 */

	@Transactional
	public TAssignmentAnswerAssign findTAssignmentAnswerAssignById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findTAssignmentAnswerAssignById", startResult, maxRows, id);
			return (net.xidlims.domain.TAssignmentAnswerAssign) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findTAssignmentAnswerAssignByContent
	 *
	 */
	@Transactional
	public Set<TAssignmentAnswerAssign> findTAssignmentAnswerAssignByContent(String content) throws DataAccessException {

		return findTAssignmentAnswerAssignByContent(content, -1, -1);
	}

	/**
	 * JPQL Query - findTAssignmentAnswerAssignByContent
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TAssignmentAnswerAssign> findTAssignmentAnswerAssignByContent(String content, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTAssignmentAnswerAssignByContent", startResult, maxRows, content);
		return new LinkedHashSet<TAssignmentAnswerAssign>(query.getResultList());
	}

	/**
	 * JPQL Query - findTAssignmentAnswerAssignByScore
	 *
	 */
	@Transactional
	public Set<TAssignmentAnswerAssign> findTAssignmentAnswerAssignByScore(java.math.BigDecimal score) throws DataAccessException {

		return findTAssignmentAnswerAssignByScore(score, -1, -1);
	}

	/**
	 * JPQL Query - findTAssignmentAnswerAssignByScore
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TAssignmentAnswerAssign> findTAssignmentAnswerAssignByScore(java.math.BigDecimal score, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTAssignmentAnswerAssignByScore", startResult, maxRows, score);
		return new LinkedHashSet<TAssignmentAnswerAssign>(query.getResultList());
	}

	/**
	 * JPQL Query - findTAssignmentAnswerAssignByReference
	 *
	 */
	@Transactional
	public Set<TAssignmentAnswerAssign> findTAssignmentAnswerAssignByReference(String reference) throws DataAccessException {

		return findTAssignmentAnswerAssignByReference(reference, -1, -1);
	}

	/**
	 * JPQL Query - findTAssignmentAnswerAssignByReference
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TAssignmentAnswerAssign> findTAssignmentAnswerAssignByReference(String reference, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTAssignmentAnswerAssignByReference", startResult, maxRows, reference);
		return new LinkedHashSet<TAssignmentAnswerAssign>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllTAssignmentAnswerAssigns
	 *
	 */
	@Transactional
	public Set<TAssignmentAnswerAssign> findAllTAssignmentAnswerAssigns() throws DataAccessException {

		return findAllTAssignmentAnswerAssigns(-1, -1);
	}

	/**
	 * JPQL Query - findAllTAssignmentAnswerAssigns
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TAssignmentAnswerAssign> findAllTAssignmentAnswerAssigns(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllTAssignmentAnswerAssigns", startResult, maxRows);
		return new LinkedHashSet<TAssignmentAnswerAssign>(query.getResultList());
	}

	/**
	 * JPQL Query - findTAssignmentAnswerAssignByPrimaryKey
	 *
	 */
	@Transactional
	public TAssignmentAnswerAssign findTAssignmentAnswerAssignByPrimaryKey(Integer id) throws DataAccessException {

		return findTAssignmentAnswerAssignByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findTAssignmentAnswerAssignByPrimaryKey
	 *
	 */

	@Transactional
	public TAssignmentAnswerAssign findTAssignmentAnswerAssignByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findTAssignmentAnswerAssignByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.TAssignmentAnswerAssign) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findTAssignmentAnswerAssignByScoreDate
	 *
	 */
	@Transactional
	public Set<TAssignmentAnswerAssign> findTAssignmentAnswerAssignByScoreDate(java.util.Calendar scoreDate) throws DataAccessException {

		return findTAssignmentAnswerAssignByScoreDate(scoreDate, -1, -1);
	}

	/**
	 * JPQL Query - findTAssignmentAnswerAssignByScoreDate
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TAssignmentAnswerAssign> findTAssignmentAnswerAssignByScoreDate(java.util.Calendar scoreDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTAssignmentAnswerAssignByScoreDate", startResult, maxRows, scoreDate);
		return new LinkedHashSet<TAssignmentAnswerAssign>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(TAssignmentAnswerAssign entity) {
		return true;
	}
}
