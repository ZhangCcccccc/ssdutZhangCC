package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.TimetableGroupStudents;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage TimetableGroupStudents entities.
 * 
 */
@Repository("TimetableGroupStudentsDAO")
@Transactional
public class TimetableGroupStudentsDAOImpl extends AbstractJpaDao<TimetableGroupStudents>
		implements TimetableGroupStudentsDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { TimetableGroupStudents.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new TimetableGroupStudentsDAOImpl
	 *
	 */
	public TimetableGroupStudentsDAOImpl() {
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
	 * JPQL Query - findAllTimetableGroupStudentss
	 *
	 */
	@Transactional
	public Set<TimetableGroupStudents> findAllTimetableGroupStudentss() throws DataAccessException {

		return findAllTimetableGroupStudentss(-1, -1);
	}

	/**
	 * JPQL Query - findAllTimetableGroupStudentss
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TimetableGroupStudents> findAllTimetableGroupStudentss(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllTimetableGroupStudentss", startResult, maxRows);
		return new LinkedHashSet<TimetableGroupStudents>(query.getResultList());
	}

	/**
	 * JPQL Query - findTimetableGroupStudentsByPrimaryKey
	 *
	 */
	@Transactional
	public TimetableGroupStudents findTimetableGroupStudentsByPrimaryKey(Integer id) throws DataAccessException {

		return findTimetableGroupStudentsByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findTimetableGroupStudentsByPrimaryKey
	 *
	 */

	@Transactional
	public TimetableGroupStudents findTimetableGroupStudentsByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findTimetableGroupStudentsByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.TimetableGroupStudents) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findTimetableGroupStudentsById
	 *
	 */
	@Transactional
	public TimetableGroupStudents findTimetableGroupStudentsById(Integer id) throws DataAccessException {

		return findTimetableGroupStudentsById(id, -1, -1);
	}

	/**
	 * JPQL Query - findTimetableGroupStudentsById
	 *
	 */

	@Transactional
	public TimetableGroupStudents findTimetableGroupStudentsById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findTimetableGroupStudentsById", startResult, maxRows, id);
			return (net.xidlims.domain.TimetableGroupStudents) query.getSingleResult();
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
	public boolean canBeMerged(TimetableGroupStudents entity) {
		return true;
	}
}
