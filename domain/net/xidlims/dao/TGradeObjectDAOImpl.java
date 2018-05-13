package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.TGradeObject;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage TGradeObject entities.
 * 
 */
@Repository("TGradeObjectDAO")
@Transactional
public class TGradeObjectDAOImpl extends AbstractJpaDao<TGradeObject> implements
		TGradeObjectDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { TGradeObject.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new TGradeObjectDAOImpl
	 *
	 */
	public TGradeObjectDAOImpl() {
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
	 * JPQL Query - findTGradeObjectByTitle
	 *
	 */
	@Transactional
	public Set<TGradeObject> findTGradeObjectByTitle(String title) throws DataAccessException {

		return findTGradeObjectByTitle(title, -1, -1);
	}

	/**
	 * JPQL Query - findTGradeObjectByTitle
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TGradeObject> findTGradeObjectByTitle(String title, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTGradeObjectByTitle", startResult, maxRows, title);
		return new LinkedHashSet<TGradeObject>(query.getResultList());
	}

	/**
	 * JPQL Query - findTGradeObjectByReleased
	 *
	 */
	@Transactional
	public Set<TGradeObject> findTGradeObjectByReleased(Integer released) throws DataAccessException {

		return findTGradeObjectByReleased(released, -1, -1);
	}

	/**
	 * JPQL Query - findTGradeObjectByReleased
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TGradeObject> findTGradeObjectByReleased(Integer released, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTGradeObjectByReleased", startResult, maxRows, released);
		return new LinkedHashSet<TGradeObject>(query.getResultList());
	}

	/**
	 * JPQL Query - findTGradeObjectByType
	 *
	 */
	@Transactional
	public Set<TGradeObject> findTGradeObjectByType(String type) throws DataAccessException {

		return findTGradeObjectByType(type, -1, -1);
	}

	/**
	 * JPQL Query - findTGradeObjectByType
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TGradeObject> findTGradeObjectByType(String type, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTGradeObjectByType", startResult, maxRows, type);
		return new LinkedHashSet<TGradeObject>(query.getResultList());
	}

	/**
	 * JPQL Query - findTGradeObjectByPointsPossible
	 *
	 */
	@Transactional
	public Set<TGradeObject> findTGradeObjectByPointsPossible(java.math.BigDecimal pointsPossible) throws DataAccessException {

		return findTGradeObjectByPointsPossible(pointsPossible, -1, -1);
	}

	/**
	 * JPQL Query - findTGradeObjectByPointsPossible
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TGradeObject> findTGradeObjectByPointsPossible(java.math.BigDecimal pointsPossible, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTGradeObjectByPointsPossible", startResult, maxRows, pointsPossible);
		return new LinkedHashSet<TGradeObject>(query.getResultList());
	}

	/**
	 * JPQL Query - findTGradeObjectByDueTime
	 *
	 */
	@Transactional
	public Set<TGradeObject> findTGradeObjectByDueTime(java.util.Calendar dueTime) throws DataAccessException {

		return findTGradeObjectByDueTime(dueTime, -1, -1);
	}

	/**
	 * JPQL Query - findTGradeObjectByDueTime
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TGradeObject> findTGradeObjectByDueTime(java.util.Calendar dueTime, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTGradeObjectByDueTime", startResult, maxRows, dueTime);
		return new LinkedHashSet<TGradeObject>(query.getResultList());
	}

	/**
	 * JPQL Query - findTGradeObjectByPrimaryKey
	 *
	 */
	@Transactional
	public TGradeObject findTGradeObjectByPrimaryKey(Integer id) throws DataAccessException {

		return findTGradeObjectByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findTGradeObjectByPrimaryKey
	 *
	 */

	@Transactional
	public TGradeObject findTGradeObjectByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findTGradeObjectByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.TGradeObject) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findTGradeObjectByStartTime
	 *
	 */
	@Transactional
	public Set<TGradeObject> findTGradeObjectByStartTime(java.util.Calendar startTime) throws DataAccessException {

		return findTGradeObjectByStartTime(startTime, -1, -1);
	}

	/**
	 * JPQL Query - findTGradeObjectByStartTime
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TGradeObject> findTGradeObjectByStartTime(java.util.Calendar startTime, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTGradeObjectByStartTime", startResult, maxRows, startTime);
		return new LinkedHashSet<TGradeObject>(query.getResultList());
	}

	/**
	 * JPQL Query - findTGradeObjectById
	 *
	 */
	@Transactional
	public TGradeObject findTGradeObjectById(Integer id) throws DataAccessException {

		return findTGradeObjectById(id, -1, -1);
	}

	/**
	 * JPQL Query - findTGradeObjectById
	 *
	 */

	@Transactional
	public TGradeObject findTGradeObjectById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findTGradeObjectById", startResult, maxRows, id);
			return (net.xidlims.domain.TGradeObject) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findTGradeObjectByTitleContaining
	 *
	 */
	@Transactional
	public Set<TGradeObject> findTGradeObjectByTitleContaining(String title) throws DataAccessException {

		return findTGradeObjectByTitleContaining(title, -1, -1);
	}

	/**
	 * JPQL Query - findTGradeObjectByTitleContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TGradeObject> findTGradeObjectByTitleContaining(String title, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTGradeObjectByTitleContaining", startResult, maxRows, title);
		return new LinkedHashSet<TGradeObject>(query.getResultList());
	}

	/**
	 * JPQL Query - findTGradeObjectByTypeContaining
	 *
	 */
	@Transactional
	public Set<TGradeObject> findTGradeObjectByTypeContaining(String type) throws DataAccessException {

		return findTGradeObjectByTypeContaining(type, -1, -1);
	}

	/**
	 * JPQL Query - findTGradeObjectByTypeContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TGradeObject> findTGradeObjectByTypeContaining(String type, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTGradeObjectByTypeContaining", startResult, maxRows, type);
		return new LinkedHashSet<TGradeObject>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllTGradeObjects
	 *
	 */
	@Transactional
	public Set<TGradeObject> findAllTGradeObjects() throws DataAccessException {

		return findAllTGradeObjects(-1, -1);
	}

	/**
	 * JPQL Query - findAllTGradeObjects
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TGradeObject> findAllTGradeObjects(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllTGradeObjects", startResult, maxRows);
		return new LinkedHashSet<TGradeObject>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(TGradeObject entity) {
		return true;
	}
}
