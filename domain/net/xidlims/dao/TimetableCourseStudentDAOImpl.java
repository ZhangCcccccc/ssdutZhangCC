package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.TimetableCourseStudent;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage TimetableCourseStudent entities.
 * 
 */
@Repository("TimetableCourseStudentDAO")
@Transactional
public class TimetableCourseStudentDAOImpl extends AbstractJpaDao<TimetableCourseStudent>
		implements TimetableCourseStudentDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { TimetableCourseStudent.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new TimetableCourseStudentDAOImpl
	 *
	 */
	public TimetableCourseStudentDAOImpl() {
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
	 * JPQL Query - findTimetableCourseStudentByPrimaryKey
	 *
	 */
	@Transactional
	public TimetableCourseStudent findTimetableCourseStudentByPrimaryKey(Integer id) throws DataAccessException {

		return findTimetableCourseStudentByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findTimetableCourseStudentByPrimaryKey
	 *
	 */

	@Transactional
	public TimetableCourseStudent findTimetableCourseStudentByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findTimetableCourseStudentByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.TimetableCourseStudent) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findAllTimetableCourseStudents
	 *
	 */
	@Transactional
	public Set<TimetableCourseStudent> findAllTimetableCourseStudents() throws DataAccessException {

		return findAllTimetableCourseStudents(-1, -1);
	}

	/**
	 * JPQL Query - findAllTimetableCourseStudents
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TimetableCourseStudent> findAllTimetableCourseStudents(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllTimetableCourseStudents", startResult, maxRows);
		return new LinkedHashSet<TimetableCourseStudent>(query.getResultList());
	}

	/**
	 * JPQL Query - findTimetableCourseStudentById
	 *
	 */
	@Transactional
	public TimetableCourseStudent findTimetableCourseStudentById(Integer id) throws DataAccessException {

		return findTimetableCourseStudentById(id, -1, -1);
	}

	/**
	 * JPQL Query - findTimetableCourseStudentById
	 *
	 */

	@Transactional
	public TimetableCourseStudent findTimetableCourseStudentById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findTimetableCourseStudentById", startResult, maxRows, id);
			return (net.xidlims.domain.TimetableCourseStudent) query.getSingleResult();
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
	public boolean canBeMerged(TimetableCourseStudent entity) {
		return true;
	}
}
