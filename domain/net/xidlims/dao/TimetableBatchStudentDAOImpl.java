package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.TimetableBatchStudent;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage TimetableBatchStudent entities.
 * 
 */
@Repository("TimetableBatchStudentDAO")
@Transactional
public class TimetableBatchStudentDAOImpl extends AbstractJpaDao<TimetableBatchStudent>
		implements TimetableBatchStudentDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { TimetableBatchStudent.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new TimetableBatchStudentDAOImpl
	 *
	 */
	public TimetableBatchStudentDAOImpl() {
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
	 * JPQL Query - findTimetableBatchStudentByWithdrawTimes
	 *
	 */
	@Transactional
	public Set<TimetableBatchStudent> findTimetableBatchStudentByWithdrawTimes(Integer withdrawTimes) throws DataAccessException {

		return findTimetableBatchStudentByWithdrawTimes(withdrawTimes, -1, -1);
	}

	/**
	 * JPQL Query - findTimetableBatchStudentByWithdrawTimes
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TimetableBatchStudent> findTimetableBatchStudentByWithdrawTimes(Integer withdrawTimes, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTimetableBatchStudentByWithdrawTimes", startResult, maxRows, withdrawTimes);
		return new LinkedHashSet<TimetableBatchStudent>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllTimetableBatchStudents
	 *
	 */
	@Transactional
	public Set<TimetableBatchStudent> findAllTimetableBatchStudents() throws DataAccessException {

		return findAllTimetableBatchStudents(-1, -1);
	}

	/**
	 * JPQL Query - findAllTimetableBatchStudents
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TimetableBatchStudent> findAllTimetableBatchStudents(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllTimetableBatchStudents", startResult, maxRows);
		return new LinkedHashSet<TimetableBatchStudent>(query.getResultList());
	}

	/**
	 * JPQL Query - findTimetableBatchStudentByPrimaryKey
	 *
	 */
	@Transactional
	public TimetableBatchStudent findTimetableBatchStudentByPrimaryKey(Integer id) throws DataAccessException {

		return findTimetableBatchStudentByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findTimetableBatchStudentByPrimaryKey
	 *
	 */

	@Transactional
	public TimetableBatchStudent findTimetableBatchStudentByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findTimetableBatchStudentByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.TimetableBatchStudent) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findTimetableBatchStudentById
	 *
	 */
	@Transactional
	public TimetableBatchStudent findTimetableBatchStudentById(Integer id) throws DataAccessException {

		return findTimetableBatchStudentById(id, -1, -1);
	}

	/**
	 * JPQL Query - findTimetableBatchStudentById
	 *
	 */

	@Transactional
	public TimetableBatchStudent findTimetableBatchStudentById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findTimetableBatchStudentById", startResult, maxRows, id);
			return (net.xidlims.domain.TimetableBatchStudent) query.getSingleResult();
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
	public boolean canBeMerged(TimetableBatchStudent entity) {
		return true;
	}
}
