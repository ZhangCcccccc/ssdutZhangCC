package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.SchoolWeek;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage SchoolWeek entities.
 * 
 */
@Repository("SchoolWeekDAO")
@Transactional
public class SchoolWeekDAOImpl extends AbstractJpaDao<SchoolWeek> implements
		SchoolWeekDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { SchoolWeek.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new SchoolWeekDAOImpl
	 *
	 */
	public SchoolWeekDAOImpl() {
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
	 * JPQL Query - findSchoolWeekByDate
	 *
	 */
	@Transactional
	public Set<SchoolWeek> findSchoolWeekByDate(java.util.Calendar date) throws DataAccessException {

		return findSchoolWeekByDate(date, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolWeekByDate
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolWeek> findSchoolWeekByDate(java.util.Calendar date, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolWeekByDate", startResult, maxRows, date);
		return new LinkedHashSet<SchoolWeek>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolWeekByWeekday
	 *
	 */
	@Transactional
	public Set<SchoolWeek> findSchoolWeekByWeekday(Integer weekday) throws DataAccessException {

		return findSchoolWeekByWeekday(weekday, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolWeekByWeekday
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolWeek> findSchoolWeekByWeekday(Integer weekday, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolWeekByWeekday", startResult, maxRows, weekday);
		return new LinkedHashSet<SchoolWeek>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllSchoolWeeks
	 *
	 */
	@Transactional
	public Set<SchoolWeek> findAllSchoolWeeks() throws DataAccessException {

		return findAllSchoolWeeks(-1, -1);
	}

	/**
	 * JPQL Query - findAllSchoolWeeks
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolWeek> findAllSchoolWeeks(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllSchoolWeeks", startResult, maxRows);
		return new LinkedHashSet<SchoolWeek>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolWeekByUpdatedAt
	 *
	 */
	@Transactional
	public Set<SchoolWeek> findSchoolWeekByUpdatedAt(java.util.Calendar updatedAt) throws DataAccessException {

		return findSchoolWeekByUpdatedAt(updatedAt, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolWeekByUpdatedAt
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolWeek> findSchoolWeekByUpdatedAt(java.util.Calendar updatedAt, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolWeekByUpdatedAt", startResult, maxRows, updatedAt);
		return new LinkedHashSet<SchoolWeek>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolWeekByPrimaryKey
	 *
	 */
	@Transactional
	public SchoolWeek findSchoolWeekByPrimaryKey(Integer id) throws DataAccessException {

		return findSchoolWeekByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolWeekByPrimaryKey
	 *
	 */

	@Transactional
	public SchoolWeek findSchoolWeekByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findSchoolWeekByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.SchoolWeek) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findSchoolWeekById
	 *
	 */
	@Transactional
	public SchoolWeek findSchoolWeekById(Integer id) throws DataAccessException {

		return findSchoolWeekById(id, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolWeekById
	 *
	 */

	@Transactional
	public SchoolWeek findSchoolWeekById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findSchoolWeekById", startResult, maxRows, id);
			return (net.xidlims.domain.SchoolWeek) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findSchoolWeekByDateAfter
	 *
	 */
	@Transactional
	public Set<SchoolWeek> findSchoolWeekByDateAfter(java.util.Calendar date) throws DataAccessException {

		return findSchoolWeekByDateAfter(date, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolWeekByDateAfter
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolWeek> findSchoolWeekByDateAfter(java.util.Calendar date, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolWeekByDateAfter", startResult, maxRows, date);
		return new LinkedHashSet<SchoolWeek>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolWeekByDateBefore
	 *
	 */
	@Transactional
	public Set<SchoolWeek> findSchoolWeekByDateBefore(java.util.Calendar date) throws DataAccessException {

		return findSchoolWeekByDateBefore(date, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolWeekByDateBefore
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolWeek> findSchoolWeekByDateBefore(java.util.Calendar date, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolWeekByDateBefore", startResult, maxRows, date);
		return new LinkedHashSet<SchoolWeek>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolWeekByWeek
	 *
	 */
	@Transactional
	public Set<SchoolWeek> findSchoolWeekByWeek(Integer week) throws DataAccessException {

		return findSchoolWeekByWeek(week, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolWeekByWeek
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolWeek> findSchoolWeekByWeek(Integer week, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolWeekByWeek", startResult, maxRows, week);
		return new LinkedHashSet<SchoolWeek>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolWeekByCreatedAt
	 *
	 */
	@Transactional
	public Set<SchoolWeek> findSchoolWeekByCreatedAt(java.util.Calendar createdAt) throws DataAccessException {

		return findSchoolWeekByCreatedAt(createdAt, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolWeekByCreatedAt
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolWeek> findSchoolWeekByCreatedAt(java.util.Calendar createdAt, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolWeekByCreatedAt", startResult, maxRows, createdAt);
		return new LinkedHashSet<SchoolWeek>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(SchoolWeek entity) {
		return true;
	}
}
