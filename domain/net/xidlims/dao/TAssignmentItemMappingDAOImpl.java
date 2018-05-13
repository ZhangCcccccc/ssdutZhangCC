package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.TAssignmentItemMapping;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage TAssignmentItemMapping entities.
 * 
 */
@Repository("TAssignmentItemMappingDAO")
@Transactional
public class TAssignmentItemMappingDAOImpl extends AbstractJpaDao<TAssignmentItemMapping>
		implements TAssignmentItemMappingDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { TAssignmentItemMapping.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new TAssignmentItemMappingDAOImpl
	 *
	 */
	public TAssignmentItemMappingDAOImpl() {
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
	 * JPQL Query - findTAssignmentItemMappingById
	 *
	 */
	@Transactional
	public TAssignmentItemMapping findTAssignmentItemMappingById(Integer id) throws DataAccessException {

		return findTAssignmentItemMappingById(id, -1, -1);
	}

	/**
	 * JPQL Query - findTAssignmentItemMappingById
	 *
	 */

	@Transactional
	public TAssignmentItemMapping findTAssignmentItemMappingById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findTAssignmentItemMappingById", startResult, maxRows, id);
			return (net.xidlims.domain.TAssignmentItemMapping) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findTAssignmentItemMappingByComments
	 *
	 */
	@Transactional
	public Set<TAssignmentItemMapping> findTAssignmentItemMappingByComments(String comments) throws DataAccessException {

		return findTAssignmentItemMappingByComments(comments, -1, -1);
	}

	/**
	 * JPQL Query - findTAssignmentItemMappingByComments
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TAssignmentItemMapping> findTAssignmentItemMappingByComments(String comments, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTAssignmentItemMappingByComments", startResult, maxRows, comments);
		return new LinkedHashSet<TAssignmentItemMapping>(query.getResultList());
	}

	/**
	 * JPQL Query - findTAssignmentItemMappingBySubmitDate
	 *
	 */
	@Transactional
	public Set<TAssignmentItemMapping> findTAssignmentItemMappingBySubmitDate(java.util.Calendar submitDate) throws DataAccessException {

		return findTAssignmentItemMappingBySubmitDate(submitDate, -1, -1);
	}

	/**
	 * JPQL Query - findTAssignmentItemMappingBySubmitDate
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TAssignmentItemMapping> findTAssignmentItemMappingBySubmitDate(java.util.Calendar submitDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTAssignmentItemMappingBySubmitDate", startResult, maxRows, submitDate);
		return new LinkedHashSet<TAssignmentItemMapping>(query.getResultList());
	}

	/**
	 * JPQL Query - findTAssignmentItemMappingByOveriderScore
	 *
	 */
	@Transactional
	public Set<TAssignmentItemMapping> findTAssignmentItemMappingByOveriderScore(java.math.BigDecimal overiderScore) throws DataAccessException {

		return findTAssignmentItemMappingByOveriderScore(overiderScore, -1, -1);
	}

	/**
	 * JPQL Query - findTAssignmentItemMappingByOveriderScore
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TAssignmentItemMapping> findTAssignmentItemMappingByOveriderScore(java.math.BigDecimal overiderScore, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTAssignmentItemMappingByOveriderScore", startResult, maxRows, overiderScore);
		return new LinkedHashSet<TAssignmentItemMapping>(query.getResultList());
	}

	/**
	 * JPQL Query - findTAssignmentItemMappingByGradetime
	 *
	 */
	@Transactional
	public Set<TAssignmentItemMapping> findTAssignmentItemMappingByGradetime(java.util.Calendar gradetime) throws DataAccessException {

		return findTAssignmentItemMappingByGradetime(gradetime, -1, -1);
	}

	/**
	 * JPQL Query - findTAssignmentItemMappingByGradetime
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TAssignmentItemMapping> findTAssignmentItemMappingByGradetime(java.util.Calendar gradetime, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTAssignmentItemMappingByGradetime", startResult, maxRows, gradetime);
		return new LinkedHashSet<TAssignmentItemMapping>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllTAssignmentItemMappings
	 *
	 */
	@Transactional
	public Set<TAssignmentItemMapping> findAllTAssignmentItemMappings() throws DataAccessException {

		return findAllTAssignmentItemMappings(-1, -1);
	}

	/**
	 * JPQL Query - findAllTAssignmentItemMappings
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TAssignmentItemMapping> findAllTAssignmentItemMappings(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllTAssignmentItemMappings", startResult, maxRows);
		return new LinkedHashSet<TAssignmentItemMapping>(query.getResultList());
	}

	/**
	 * JPQL Query - findTAssignmentItemMappingByPrimaryKey
	 *
	 */
	@Transactional
	public TAssignmentItemMapping findTAssignmentItemMappingByPrimaryKey(Integer id) throws DataAccessException {

		return findTAssignmentItemMappingByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findTAssignmentItemMappingByPrimaryKey
	 *
	 */

	@Transactional
	public TAssignmentItemMapping findTAssignmentItemMappingByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findTAssignmentItemMappingByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.TAssignmentItemMapping) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findTAssignmentItemMappingByCommentsContaining
	 *
	 */
	@Transactional
	public Set<TAssignmentItemMapping> findTAssignmentItemMappingByCommentsContaining(String comments) throws DataAccessException {

		return findTAssignmentItemMappingByCommentsContaining(comments, -1, -1);
	}

	/**
	 * JPQL Query - findTAssignmentItemMappingByCommentsContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TAssignmentItemMapping> findTAssignmentItemMappingByCommentsContaining(String comments, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTAssignmentItemMappingByCommentsContaining", startResult, maxRows, comments);
		return new LinkedHashSet<TAssignmentItemMapping>(query.getResultList());
	}

	/**
	 * JPQL Query - findTAssignmentItemMappingByAutoscore
	 *
	 */
	@Transactional
	public Set<TAssignmentItemMapping> findTAssignmentItemMappingByAutoscore(java.math.BigDecimal autoscore) throws DataAccessException {

		return findTAssignmentItemMappingByAutoscore(autoscore, -1, -1);
	}

	/**
	 * JPQL Query - findTAssignmentItemMappingByAutoscore
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TAssignmentItemMapping> findTAssignmentItemMappingByAutoscore(java.math.BigDecimal autoscore, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTAssignmentItemMappingByAutoscore", startResult, maxRows, autoscore);
		return new LinkedHashSet<TAssignmentItemMapping>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(TAssignmentItemMapping entity) {
		return true;
	}
}
