package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.TAssignmentItem;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage TAssignmentItem entities.
 * 
 */
@Repository("TAssignmentItemDAO")
@Transactional
public class TAssignmentItemDAOImpl extends AbstractJpaDao<TAssignmentItem>
		implements TAssignmentItemDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { TAssignmentItem.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new TAssignmentItemDAOImpl
	 *
	 */
	public TAssignmentItemDAOImpl() {
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
	 * JPQL Query - findTAssignmentItemById
	 *
	 */
	@Transactional
	public TAssignmentItem findTAssignmentItemById(Integer id) throws DataAccessException {

		return findTAssignmentItemById(id, -1, -1);
	}

	/**
	 * JPQL Query - findTAssignmentItemById
	 *
	 */

	@Transactional
	public TAssignmentItem findTAssignmentItemById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findTAssignmentItemById", startResult, maxRows, id);
			return (net.xidlims.domain.TAssignmentItem) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findTAssignmentItemByScore
	 *
	 */
	@Transactional
	public Set<TAssignmentItem> findTAssignmentItemByScore(java.math.BigDecimal score) throws DataAccessException {

		return findTAssignmentItemByScore(score, -1, -1);
	}

	/**
	 * JPQL Query - findTAssignmentItemByScore
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TAssignmentItem> findTAssignmentItemByScore(java.math.BigDecimal score, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTAssignmentItemByScore", startResult, maxRows, score);
		return new LinkedHashSet<TAssignmentItem>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllTAssignmentItems
	 *
	 */
	@Transactional
	public Set<TAssignmentItem> findAllTAssignmentItems() throws DataAccessException {

		return findAllTAssignmentItems(-1, -1);
	}

	/**
	 * JPQL Query - findAllTAssignmentItems
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TAssignmentItem> findAllTAssignmentItems(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllTAssignmentItems", startResult, maxRows);
		return new LinkedHashSet<TAssignmentItem>(query.getResultList());
	}

	/**
	 * JPQL Query - findTAssignmentItemByPrimaryKey
	 *
	 */
	@Transactional
	public TAssignmentItem findTAssignmentItemByPrimaryKey(Integer id) throws DataAccessException {

		return findTAssignmentItemByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findTAssignmentItemByPrimaryKey
	 *
	 */

	@Transactional
	public TAssignmentItem findTAssignmentItemByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findTAssignmentItemByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.TAssignmentItem) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findTAssignmentItemByCreatedTime
	 *
	 */
	@Transactional
	public Set<TAssignmentItem> findTAssignmentItemByCreatedTime(java.util.Calendar createdTime) throws DataAccessException {

		return findTAssignmentItemByCreatedTime(createdTime, -1, -1);
	}

	/**
	 * JPQL Query - findTAssignmentItemByCreatedTime
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TAssignmentItem> findTAssignmentItemByCreatedTime(java.util.Calendar createdTime, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTAssignmentItemByCreatedTime", startResult, maxRows, createdTime);
		return new LinkedHashSet<TAssignmentItem>(query.getResultList());
	}

	/**
	 * JPQL Query - findTAssignmentItemByDescriptionContaining
	 *
	 */
	@Transactional
	public Set<TAssignmentItem> findTAssignmentItemByDescriptionContaining(String description) throws DataAccessException {

		return findTAssignmentItemByDescriptionContaining(description, -1, -1);
	}

	/**
	 * JPQL Query - findTAssignmentItemByDescriptionContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TAssignmentItem> findTAssignmentItemByDescriptionContaining(String description, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTAssignmentItemByDescriptionContaining", startResult, maxRows, description);
		return new LinkedHashSet<TAssignmentItem>(query.getResultList());
	}

	/**
	 * JPQL Query - findTAssignmentItemByDescription
	 *
	 */
	@Transactional
	public Set<TAssignmentItem> findTAssignmentItemByDescription(String description) throws DataAccessException {

		return findTAssignmentItemByDescription(description, -1, -1);
	}

	/**
	 * JPQL Query - findTAssignmentItemByDescription
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TAssignmentItem> findTAssignmentItemByDescription(String description, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTAssignmentItemByDescription", startResult, maxRows, description);
		return new LinkedHashSet<TAssignmentItem>(query.getResultList());
	}

	/**
	 * JPQL Query - findTAssignmentItemByGradeContaining
	 *
	 */
	@Transactional
	public Set<TAssignmentItem> findTAssignmentItemByGradeContaining(String grade) throws DataAccessException {

		return findTAssignmentItemByGradeContaining(grade, -1, -1);
	}

	/**
	 * JPQL Query - findTAssignmentItemByGradeContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TAssignmentItem> findTAssignmentItemByGradeContaining(String grade, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTAssignmentItemByGradeContaining", startResult, maxRows, grade);
		return new LinkedHashSet<TAssignmentItem>(query.getResultList());
	}

	/**
	 * JPQL Query - findTAssignmentItemByGrade
	 *
	 */
	@Transactional
	public Set<TAssignmentItem> findTAssignmentItemByGrade(String grade) throws DataAccessException {

		return findTAssignmentItemByGrade(grade, -1, -1);
	}

	/**
	 * JPQL Query - findTAssignmentItemByGrade
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TAssignmentItem> findTAssignmentItemByGrade(String grade, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTAssignmentItemByGrade", startResult, maxRows, grade);
		return new LinkedHashSet<TAssignmentItem>(query.getResultList());
	}

	/**
	 * JPQL Query - findTAssignmentItemByStatus
	 *
	 */
	@Transactional
	public Set<TAssignmentItem> findTAssignmentItemByStatus(Integer status) throws DataAccessException {

		return findTAssignmentItemByStatus(status, -1, -1);
	}

	/**
	 * JPQL Query - findTAssignmentItemByStatus
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TAssignmentItem> findTAssignmentItemByStatus(Integer status, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTAssignmentItemByStatus", startResult, maxRows, status);
		return new LinkedHashSet<TAssignmentItem>(query.getResultList());
	}

	/**
	 * JPQL Query - findTAssignmentItemBySequence
	 *
	 */
	@Transactional
	public Set<TAssignmentItem> findTAssignmentItemBySequence(Integer sequence) throws DataAccessException {

		return findTAssignmentItemBySequence(sequence, -1, -1);
	}

	/**
	 * JPQL Query - findTAssignmentItemBySequence
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TAssignmentItem> findTAssignmentItemBySequence(Integer sequence, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTAssignmentItemBySequence", startResult, maxRows, sequence);
		return new LinkedHashSet<TAssignmentItem>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(TAssignmentItem entity) {
		return true;
	}
}
