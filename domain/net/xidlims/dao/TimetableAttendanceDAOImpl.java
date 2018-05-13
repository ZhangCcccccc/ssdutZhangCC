package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.TimetableAttendance;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage TimetableAttendance entities.
 * 
 */
@Repository("TimetableAttendanceDAO")
@Transactional
public class TimetableAttendanceDAOImpl extends AbstractJpaDao<TimetableAttendance>
		implements TimetableAttendanceDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { TimetableAttendance.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new TimetableAttendanceDAOImpl
	 *
	 */
	public TimetableAttendanceDAOImpl() {
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
	 * JPQL Query - findTimetableAttendanceById
	 *
	 */
	@Transactional
	public TimetableAttendance findTimetableAttendanceById(Integer id) throws DataAccessException {

		return findTimetableAttendanceById(id, -1, -1);
	}

	/**
	 * JPQL Query - findTimetableAttendanceById
	 *
	 */

	@Transactional
	public TimetableAttendance findTimetableAttendanceById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findTimetableAttendanceById", startResult, maxRows, id);
			return (net.xidlims.domain.TimetableAttendance) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findTimetableAttendanceByAttendanceStatusContaining
	 *
	 */
	@Transactional
	public Set<TimetableAttendance> findTimetableAttendanceByAttendanceStatusContaining(String attendanceStatus) throws DataAccessException {

		return findTimetableAttendanceByAttendanceStatusContaining(attendanceStatus, -1, -1);
	}

	/**
	 * JPQL Query - findTimetableAttendanceByAttendanceStatusContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TimetableAttendance> findTimetableAttendanceByAttendanceStatusContaining(String attendanceStatus, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTimetableAttendanceByAttendanceStatusContaining", startResult, maxRows, attendanceStatus);
		return new LinkedHashSet<TimetableAttendance>(query.getResultList());
	}

	/**
	 * JPQL Query - findTimetableAttendanceByAttendDate
	 *
	 */
	@Transactional
	public Set<TimetableAttendance> findTimetableAttendanceByAttendDate(java.util.Calendar attendDate) throws DataAccessException {

		return findTimetableAttendanceByAttendDate(attendDate, -1, -1);
	}

	/**
	 * JPQL Query - findTimetableAttendanceByAttendDate
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TimetableAttendance> findTimetableAttendanceByAttendDate(java.util.Calendar attendDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTimetableAttendanceByAttendDate", startResult, maxRows, attendDate);
		return new LinkedHashSet<TimetableAttendance>(query.getResultList());
	}

	/**
	 * JPQL Query - findTimetableAttendanceByActualAttendance
	 *
	 */
	@Transactional
	public Set<TimetableAttendance> findTimetableAttendanceByActualAttendance(Integer actualAttendance) throws DataAccessException {

		return findTimetableAttendanceByActualAttendance(actualAttendance, -1, -1);
	}

	/**
	 * JPQL Query - findTimetableAttendanceByActualAttendance
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TimetableAttendance> findTimetableAttendanceByActualAttendance(Integer actualAttendance, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTimetableAttendanceByActualAttendance", startResult, maxRows, actualAttendance);
		return new LinkedHashSet<TimetableAttendance>(query.getResultList());
	}

	/**
	 * JPQL Query - findTimetableAttendanceByMemo
	 *
	 */
	@Transactional
	public Set<TimetableAttendance> findTimetableAttendanceByMemo(String memo) throws DataAccessException {

		return findTimetableAttendanceByMemo(memo, -1, -1);
	}

	/**
	 * JPQL Query - findTimetableAttendanceByMemo
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TimetableAttendance> findTimetableAttendanceByMemo(String memo, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTimetableAttendanceByMemo", startResult, maxRows, memo);
		return new LinkedHashSet<TimetableAttendance>(query.getResultList());
	}

	/**
	 * JPQL Query - findTimetableAttendanceByCreatedDate
	 *
	 */
	@Transactional
	public Set<TimetableAttendance> findTimetableAttendanceByCreatedDate(java.util.Calendar createdDate) throws DataAccessException {

		return findTimetableAttendanceByCreatedDate(createdDate, -1, -1);
	}

	/**
	 * JPQL Query - findTimetableAttendanceByCreatedDate
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TimetableAttendance> findTimetableAttendanceByCreatedDate(java.util.Calendar createdDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTimetableAttendanceByCreatedDate", startResult, maxRows, createdDate);
		return new LinkedHashSet<TimetableAttendance>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllTimetableAttendances
	 *
	 */
	@Transactional
	public Set<TimetableAttendance> findAllTimetableAttendances() throws DataAccessException {

		return findAllTimetableAttendances(-1, -1);
	}

	/**
	 * JPQL Query - findAllTimetableAttendances
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TimetableAttendance> findAllTimetableAttendances(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllTimetableAttendances", startResult, maxRows);
		return new LinkedHashSet<TimetableAttendance>(query.getResultList());
	}

	/**
	 * JPQL Query - findTimetableAttendanceByWeekday
	 *
	 */
	@Transactional
	public Set<TimetableAttendance> findTimetableAttendanceByWeekday(Integer weekday) throws DataAccessException {

		return findTimetableAttendanceByWeekday(weekday, -1, -1);
	}

	/**
	 * JPQL Query - findTimetableAttendanceByWeekday
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TimetableAttendance> findTimetableAttendanceByWeekday(Integer weekday, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTimetableAttendanceByWeekday", startResult, maxRows, weekday);
		return new LinkedHashSet<TimetableAttendance>(query.getResultList());
	}

	/**
	 * JPQL Query - findTimetableAttendanceByMemoContaining
	 *
	 */
	@Transactional
	public Set<TimetableAttendance> findTimetableAttendanceByMemoContaining(String memo) throws DataAccessException {

		return findTimetableAttendanceByMemoContaining(memo, -1, -1);
	}

	/**
	 * JPQL Query - findTimetableAttendanceByMemoContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TimetableAttendance> findTimetableAttendanceByMemoContaining(String memo, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTimetableAttendanceByMemoContaining", startResult, maxRows, memo);
		return new LinkedHashSet<TimetableAttendance>(query.getResultList());
	}

	/**
	 * JPQL Query - findTimetableAttendanceByPrimaryKey
	 *
	 */
	@Transactional
	public TimetableAttendance findTimetableAttendanceByPrimaryKey(Integer id) throws DataAccessException {

		return findTimetableAttendanceByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findTimetableAttendanceByPrimaryKey
	 *
	 */

	@Transactional
	public TimetableAttendance findTimetableAttendanceByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findTimetableAttendanceByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.TimetableAttendance) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findTimetableAttendanceByUpdatedDate
	 *
	 */
	@Transactional
	public Set<TimetableAttendance> findTimetableAttendanceByUpdatedDate(java.util.Calendar updatedDate) throws DataAccessException {

		return findTimetableAttendanceByUpdatedDate(updatedDate, -1, -1);
	}

	/**
	 * JPQL Query - findTimetableAttendanceByUpdatedDate
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TimetableAttendance> findTimetableAttendanceByUpdatedDate(java.util.Calendar updatedDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTimetableAttendanceByUpdatedDate", startResult, maxRows, updatedDate);
		return new LinkedHashSet<TimetableAttendance>(query.getResultList());
	}

	/**
	 * JPQL Query - findTimetableAttendanceByArrangeClass
	 *
	 */
	@Transactional
	public Set<TimetableAttendance> findTimetableAttendanceByArrangeClass(Integer arrangeClass) throws DataAccessException {

		return findTimetableAttendanceByArrangeClass(arrangeClass, -1, -1);
	}

	/**
	 * JPQL Query - findTimetableAttendanceByArrangeClass
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TimetableAttendance> findTimetableAttendanceByArrangeClass(Integer arrangeClass, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTimetableAttendanceByArrangeClass", startResult, maxRows, arrangeClass);
		return new LinkedHashSet<TimetableAttendance>(query.getResultList());
	}

	/**
	 * JPQL Query - findTimetableAttendanceByWeek
	 *
	 */
	@Transactional
	public Set<TimetableAttendance> findTimetableAttendanceByWeek(Integer week) throws DataAccessException {

		return findTimetableAttendanceByWeek(week, -1, -1);
	}

	/**
	 * JPQL Query - findTimetableAttendanceByWeek
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TimetableAttendance> findTimetableAttendanceByWeek(Integer week, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTimetableAttendanceByWeek", startResult, maxRows, week);
		return new LinkedHashSet<TimetableAttendance>(query.getResultList());
	}

	/**
	 * JPQL Query - findTimetableAttendanceByAttendanceMachine
	 *
	 */
	@Transactional
	public Set<TimetableAttendance> findTimetableAttendanceByAttendanceMachine(Integer attendanceMachine) throws DataAccessException {

		return findTimetableAttendanceByAttendanceMachine(attendanceMachine, -1, -1);
	}

	/**
	 * JPQL Query - findTimetableAttendanceByAttendanceMachine
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TimetableAttendance> findTimetableAttendanceByAttendanceMachine(Integer attendanceMachine, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTimetableAttendanceByAttendanceMachine", startResult, maxRows, attendanceMachine);
		return new LinkedHashSet<TimetableAttendance>(query.getResultList());
	}

	/**
	 * JPQL Query - findTimetableAttendanceByAttendanceStatus
	 *
	 */
	@Transactional
	public Set<TimetableAttendance> findTimetableAttendanceByAttendanceStatus(String attendanceStatus) throws DataAccessException {

		return findTimetableAttendanceByAttendanceStatus(attendanceStatus, -1, -1);
	}

	/**
	 * JPQL Query - findTimetableAttendanceByAttendanceStatus
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TimetableAttendance> findTimetableAttendanceByAttendanceStatus(String attendanceStatus, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTimetableAttendanceByAttendanceStatus", startResult, maxRows, attendanceStatus);
		return new LinkedHashSet<TimetableAttendance>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(TimetableAttendance entity) {
		return true;
	}
}
