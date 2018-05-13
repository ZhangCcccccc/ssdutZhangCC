package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.TimetableBatch;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage TimetableBatch entities.
 * 
 */
@Repository("TimetableBatchDAO")
@Transactional
public class TimetableBatchDAOImpl extends AbstractJpaDao<TimetableBatch>
		implements TimetableBatchDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { TimetableBatch.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new TimetableBatchDAOImpl
	 *
	 */
	public TimetableBatchDAOImpl() {
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
	 * JPQL Query - findAllTimetableBatchs
	 *
	 */
	@Transactional
	public Set<TimetableBatch> findAllTimetableBatchs() throws DataAccessException {

		return findAllTimetableBatchs(-1, -1);
	}

	/**
	 * JPQL Query - findAllTimetableBatchs
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TimetableBatch> findAllTimetableBatchs(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllTimetableBatchs", startResult, maxRows);
		return new LinkedHashSet<TimetableBatch>(query.getResultList());
	}

	/**
	 * JPQL Query - findTimetableBatchByPrimaryKey
	 *
	 */
	@Transactional
	public TimetableBatch findTimetableBatchByPrimaryKey(Integer id) throws DataAccessException {

		return findTimetableBatchByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findTimetableBatchByPrimaryKey
	 *
	 */

	@Transactional
	public TimetableBatch findTimetableBatchByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findTimetableBatchByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.TimetableBatch) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findTimetableBatchByBatchNameContaining
	 *
	 */
	@Transactional
	public Set<TimetableBatch> findTimetableBatchByBatchNameContaining(String batchName) throws DataAccessException {

		return findTimetableBatchByBatchNameContaining(batchName, -1, -1);
	}

	/**
	 * JPQL Query - findTimetableBatchByBatchNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TimetableBatch> findTimetableBatchByBatchNameContaining(String batchName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTimetableBatchByBatchNameContaining", startResult, maxRows, batchName);
		return new LinkedHashSet<TimetableBatch>(query.getResultList());
	}

	/**
	 * JPQL Query - findTimetableBatchById
	 *
	 */
	@Transactional
	public TimetableBatch findTimetableBatchById(Integer id) throws DataAccessException {

		return findTimetableBatchById(id, -1, -1);
	}

	/**
	 * JPQL Query - findTimetableBatchById
	 *
	 */

	@Transactional
	public TimetableBatch findTimetableBatchById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findTimetableBatchById", startResult, maxRows, id);
			return (net.xidlims.domain.TimetableBatch) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findTimetableBatchByCourseCodeContaining
	 *
	 */
	@Transactional
	public Set<TimetableBatch> findTimetableBatchByCourseCodeContaining(String courseCode) throws DataAccessException {

		return findTimetableBatchByCourseCodeContaining(courseCode, -1, -1);
	}

	/**
	 * JPQL Query - findTimetableBatchByCourseCodeContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TimetableBatch> findTimetableBatchByCourseCodeContaining(String courseCode, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTimetableBatchByCourseCodeContaining", startResult, maxRows, courseCode);
		return new LinkedHashSet<TimetableBatch>(query.getResultList());
	}

	/**
	 * JPQL Query - findTimetableBatchByIfselect
	 *
	 */
	@Transactional
	public Set<TimetableBatch> findTimetableBatchByIfselect(Integer ifselect) throws DataAccessException {

		return findTimetableBatchByIfselect(ifselect, -1, -1);
	}

	/**
	 * JPQL Query - findTimetableBatchByIfselect
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TimetableBatch> findTimetableBatchByIfselect(Integer ifselect, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTimetableBatchByIfselect", startResult, maxRows, ifselect);
		return new LinkedHashSet<TimetableBatch>(query.getResultList());
	}

	/**
	 * JPQL Query - findTimetableBatchByBatchName
	 *
	 */
	@Transactional
	public Set<TimetableBatch> findTimetableBatchByBatchName(String batchName) throws DataAccessException {

		return findTimetableBatchByBatchName(batchName, -1, -1);
	}

	/**
	 * JPQL Query - findTimetableBatchByBatchName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TimetableBatch> findTimetableBatchByBatchName(String batchName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTimetableBatchByBatchName", startResult, maxRows, batchName);
		return new LinkedHashSet<TimetableBatch>(query.getResultList());
	}

	/**
	 * JPQL Query - findTimetableBatchByCourseCode
	 *
	 */
	@Transactional
	public Set<TimetableBatch> findTimetableBatchByCourseCode(String courseCode) throws DataAccessException {

		return findTimetableBatchByCourseCode(courseCode, -1, -1);
	}

	/**
	 * JPQL Query - findTimetableBatchByCourseCode
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TimetableBatch> findTimetableBatchByCourseCode(String courseCode, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTimetableBatchByCourseCode", startResult, maxRows, courseCode);
		return new LinkedHashSet<TimetableBatch>(query.getResultList());
	}

	/**
	 * JPQL Query - findTimetableBatchByCountGroup
	 *
	 */
	@Transactional
	public Set<TimetableBatch> findTimetableBatchByCountGroup(Integer countGroup) throws DataAccessException {

		return findTimetableBatchByCountGroup(countGroup, -1, -1);
	}

	/**
	 * JPQL Query - findTimetableBatchByCountGroup
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TimetableBatch> findTimetableBatchByCountGroup(Integer countGroup, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTimetableBatchByCountGroup", startResult, maxRows, countGroup);
		return new LinkedHashSet<TimetableBatch>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(TimetableBatch entity) {
		return true;
	}
}
