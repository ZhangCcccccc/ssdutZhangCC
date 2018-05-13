package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.TimetableSelfCourse;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage TimetableSelfCourse entities.
 * 
 */
@Repository("TimetableSelfCourseDAO")
@Transactional
public class TimetableSelfCourseDAOImpl extends AbstractJpaDao<TimetableSelfCourse>
		implements TimetableSelfCourseDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { TimetableSelfCourse.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new TimetableSelfCourseDAOImpl
	 *
	 */
	public TimetableSelfCourseDAOImpl() {
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
	 * JPQL Query - findTimetableSelfCourseById
	 *
	 */
	@Transactional
	public TimetableSelfCourse findTimetableSelfCourseById(Integer id) throws DataAccessException {

		return findTimetableSelfCourseById(id, -1, -1);
	}

	/**
	 * JPQL Query - findTimetableSelfCourseById
	 *
	 */

	@Transactional
	public TimetableSelfCourse findTimetableSelfCourseById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findTimetableSelfCourseById", startResult, maxRows, id);
			return (net.xidlims.domain.TimetableSelfCourse) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findTimetableSelfCourseByCourseCodeContaining
	 *
	 */
	@Transactional
	public Set<TimetableSelfCourse> findTimetableSelfCourseByCourseCodeContaining(String courseCode) throws DataAccessException {

		return findTimetableSelfCourseByCourseCodeContaining(courseCode, -1, -1);
	}

	/**
	 * JPQL Query - findTimetableSelfCourseByCourseCodeContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TimetableSelfCourse> findTimetableSelfCourseByCourseCodeContaining(String courseCode, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTimetableSelfCourseByCourseCodeContaining", startResult, maxRows, courseCode);
		return new LinkedHashSet<TimetableSelfCourse>(query.getResultList());
	}

	/**
	 * JPQL Query - findTimetableSelfCourseByPrimaryKey
	 *
	 */
	@Transactional
	public TimetableSelfCourse findTimetableSelfCourseByPrimaryKey(Integer id) throws DataAccessException {

		return findTimetableSelfCourseByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findTimetableSelfCourseByPrimaryKey
	 *
	 */

	@Transactional
	public TimetableSelfCourse findTimetableSelfCourseByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findTimetableSelfCourseByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.TimetableSelfCourse) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findAllTimetableSelfCourses
	 *
	 */
	@Transactional
	public Set<TimetableSelfCourse> findAllTimetableSelfCourses() throws DataAccessException {

		return findAllTimetableSelfCourses(-1, -1);
	}

	/**
	 * JPQL Query - findAllTimetableSelfCourses
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TimetableSelfCourse> findAllTimetableSelfCourses(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllTimetableSelfCourses", startResult, maxRows);
		return new LinkedHashSet<TimetableSelfCourse>(query.getResultList());
	}

	/**
	 * JPQL Query - findTimetableSelfCourseByName
	 *
	 */
	@Transactional
	public Set<TimetableSelfCourse> findTimetableSelfCourseByName(String name) throws DataAccessException {

		return findTimetableSelfCourseByName(name, -1, -1);
	}

	/**
	 * JPQL Query - findTimetableSelfCourseByName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TimetableSelfCourse> findTimetableSelfCourseByName(String name, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTimetableSelfCourseByName", startResult, maxRows, name);
		return new LinkedHashSet<TimetableSelfCourse>(query.getResultList());
	}

	/**
	 * JPQL Query - findTimetableSelfCourseByCourseCode
	 *
	 */
	@Transactional
	public Set<TimetableSelfCourse> findTimetableSelfCourseByCourseCode(String courseCode) throws DataAccessException {

		return findTimetableSelfCourseByCourseCode(courseCode, -1, -1);
	}

	/**
	 * JPQL Query - findTimetableSelfCourseByCourseCode
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TimetableSelfCourse> findTimetableSelfCourseByCourseCode(String courseCode, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTimetableSelfCourseByCourseCode", startResult, maxRows, courseCode);
		return new LinkedHashSet<TimetableSelfCourse>(query.getResultList());
	}

	/**
	 * JPQL Query - findTimetableSelfCourseByNameContaining
	 *
	 */
	@Transactional
	public Set<TimetableSelfCourse> findTimetableSelfCourseByNameContaining(String name) throws DataAccessException {

		return findTimetableSelfCourseByNameContaining(name, -1, -1);
	}

	/**
	 * JPQL Query - findTimetableSelfCourseByNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TimetableSelfCourse> findTimetableSelfCourseByNameContaining(String name, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTimetableSelfCourseByNameContaining", startResult, maxRows, name);
		return new LinkedHashSet<TimetableSelfCourse>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(TimetableSelfCourse entity) {
		return true;
	}
}
