package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.TimetableAttendances;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage TimetableAttendances entities.
 * 
 */
@Repository("TimetableAttendancesDAO")
@Transactional
public class TimetableAttendancesDAOImpl extends AbstractJpaDao<TimetableAttendances>
		implements TimetableAttendancesDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { TimetableAttendances.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new TimetableAttendancesDAOImpl
	 *
	 */
	public TimetableAttendancesDAOImpl() {
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
	 * JPQL Query - findTimetableAttendancesByUserNumber
	 *
	 */
	@Transactional
	public Set<TimetableAttendances> findTimetableAttendancesByUserNumber(String userNumber) throws DataAccessException {

		return findTimetableAttendancesByUserNumber(userNumber, -1, -1);
	}

	/**
	 * JPQL Query - findTimetableAttendancesByUserNumber
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TimetableAttendances> findTimetableAttendancesByUserNumber(String userNumber, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTimetableAttendancesByUserNumber", startResult, maxRows, userNumber);
		return new LinkedHashSet<TimetableAttendances>(query.getResultList());
	}

	/**
	 * JPQL Query - findTimetableAttendancesByCreatedBy
	 *
	 */
	@Transactional
	public Set<TimetableAttendances> findTimetableAttendancesByCreatedBy(Integer createdBy) throws DataAccessException {

		return findTimetableAttendancesByCreatedBy(createdBy, -1, -1);
	}

	/**
	 * JPQL Query - findTimetableAttendancesByCreatedBy
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TimetableAttendances> findTimetableAttendancesByCreatedBy(Integer createdBy, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTimetableAttendancesByCreatedBy", startResult, maxRows, createdBy);
		return new LinkedHashSet<TimetableAttendances>(query.getResultList());
	}

	/**
	 * JPQL Query - findTimetableAttendancesByWeek
	 *
	 */
	@Transactional
	public Set<TimetableAttendances> findTimetableAttendancesByWeek(Integer week) throws DataAccessException {

		return findTimetableAttendancesByWeek(week, -1, -1);
	}

	/**
	 * JPQL Query - findTimetableAttendancesByWeek
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TimetableAttendances> findTimetableAttendancesByWeek(Integer week, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTimetableAttendancesByWeek", startResult, maxRows, week);
		return new LinkedHashSet<TimetableAttendances>(query.getResultList());
	}

	/**
	 * JPQL Query - findTimetableAttendancesByPrimaryKey
	 *
	 */
	@Transactional
	public TimetableAttendances findTimetableAttendancesByPrimaryKey(Integer id) throws DataAccessException {

		return findTimetableAttendancesByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findTimetableAttendancesByPrimaryKey
	 *
	 */

	@Transactional
	public TimetableAttendances findTimetableAttendancesByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findTimetableAttendancesByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.TimetableAttendances) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findTimetableAttendancesByEndDateTime
	 *
	 */
	@Transactional
	public Set<TimetableAttendances> findTimetableAttendancesByEndDateTime(java.util.Calendar endDateTime) throws DataAccessException {

		return findTimetableAttendancesByEndDateTime(endDateTime, -1, -1);
	}

	/**
	 * JPQL Query - findTimetableAttendancesByEndDateTime
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TimetableAttendances> findTimetableAttendancesByEndDateTime(java.util.Calendar endDateTime, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTimetableAttendancesByEndDateTime", startResult, maxRows, endDateTime);
		return new LinkedHashSet<TimetableAttendances>(query.getResultList());
	}

	/**
	 * JPQL Query - findTimetableAttendancesByActualAttendance
	 *
	 */
	@Transactional
	public Set<TimetableAttendances> findTimetableAttendancesByActualAttendance(Integer actualAttendance) throws DataAccessException {

		return findTimetableAttendancesByActualAttendance(actualAttendance, -1, -1);
	}

	/**
	 * JPQL Query - findTimetableAttendancesByActualAttendance
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TimetableAttendances> findTimetableAttendancesByActualAttendance(Integer actualAttendance, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTimetableAttendancesByActualAttendance", startResult, maxRows, actualAttendance);
		return new LinkedHashSet<TimetableAttendances>(query.getResultList());
	}

	/**
	 * JPQL Query - findTimetableAttendancesByCreatedDate
	 *
	 */
	@Transactional
	public Set<TimetableAttendances> findTimetableAttendancesByCreatedDate(java.util.Calendar createdDate) throws DataAccessException {

		return findTimetableAttendancesByCreatedDate(createdDate, -1, -1);
	}

	/**
	 * JPQL Query - findTimetableAttendancesByCreatedDate
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TimetableAttendances> findTimetableAttendancesByCreatedDate(java.util.Calendar createdDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTimetableAttendancesByCreatedDate", startResult, maxRows, createdDate);
		return new LinkedHashSet<TimetableAttendances>(query.getResultList());
	}

	/**
	 * JPQL Query - findTimetableAttendancesByMemo
	 *
	 */
	@Transactional
	public Set<TimetableAttendances> findTimetableAttendancesByMemo(String memo) throws DataAccessException {

		return findTimetableAttendancesByMemo(memo, -1, -1);
	}

	/**
	 * JPQL Query - findTimetableAttendancesByMemo
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TimetableAttendances> findTimetableAttendancesByMemo(String memo, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTimetableAttendancesByMemo", startResult, maxRows, memo);
		return new LinkedHashSet<TimetableAttendances>(query.getResultList());
	}

	/**
	 * JPQL Query - findTimetableAttendancesByStartDateTime
	 *
	 */
	@Transactional
	public Set<TimetableAttendances> findTimetableAttendancesByStartDateTime(java.util.Calendar startDateTime) throws DataAccessException {

		return findTimetableAttendancesByStartDateTime(startDateTime, -1, -1);
	}

	/**
	 * JPQL Query - findTimetableAttendancesByStartDateTime
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TimetableAttendances> findTimetableAttendancesByStartDateTime(java.util.Calendar startDateTime, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTimetableAttendancesByStartDateTime", startResult, maxRows, startDateTime);
		return new LinkedHashSet<TimetableAttendances>(query.getResultList());
	}

	/**
	 * JPQL Query - findTimetableAttendancesByAttendanceStatusContaining
	 *
	 */
	@Transactional
	public Set<TimetableAttendances> findTimetableAttendancesByAttendanceStatusContaining(String attendanceStatus) throws DataAccessException {

		return findTimetableAttendancesByAttendanceStatusContaining(attendanceStatus, -1, -1);
	}

	/**
	 * JPQL Query - findTimetableAttendancesByAttendanceStatusContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TimetableAttendances> findTimetableAttendancesByAttendanceStatusContaining(String attendanceStatus, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTimetableAttendancesByAttendanceStatusContaining", startResult, maxRows, attendanceStatus);
		return new LinkedHashSet<TimetableAttendances>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllTimetableAttendancess
	 *
	 */
	@Transactional
	public Set<TimetableAttendances> findAllTimetableAttendancess() throws DataAccessException {

		return findAllTimetableAttendancess(-1, -1);
	}

	/**
	 * JPQL Query - findAllTimetableAttendancess
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TimetableAttendances> findAllTimetableAttendancess(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllTimetableAttendancess", startResult, maxRows);
		return new LinkedHashSet<TimetableAttendances>(query.getResultList());
	}

	/**
	 * JPQL Query - findTimetableAttendancesByUserNumberContaining
	 *
	 */
	@Transactional
	public Set<TimetableAttendances> findTimetableAttendancesByUserNumberContaining(String userNumber) throws DataAccessException {

		return findTimetableAttendancesByUserNumberContaining(userNumber, -1, -1);
	}

	/**
	 * JPQL Query - findTimetableAttendancesByUserNumberContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TimetableAttendances> findTimetableAttendancesByUserNumberContaining(String userNumber, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTimetableAttendancesByUserNumberContaining", startResult, maxRows, userNumber);
		return new LinkedHashSet<TimetableAttendances>(query.getResultList());
	}

	/**
	 * JPQL Query - findTimetableAttendancesByStartClass
	 *
	 */
	@Transactional
	public Set<TimetableAttendances> findTimetableAttendancesByStartClass(Integer startClass) throws DataAccessException {

		return findTimetableAttendancesByStartClass(startClass, -1, -1);
	}

	/**
	 * JPQL Query - findTimetableAttendancesByStartClass
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TimetableAttendances> findTimetableAttendancesByStartClass(Integer startClass, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTimetableAttendancesByStartClass", startResult, maxRows, startClass);
		return new LinkedHashSet<TimetableAttendances>(query.getResultList());
	}

	/**
	 * JPQL Query - findTimetableAttendancesByAttendanceMachine
	 *
	 */
	@Transactional
	public Set<TimetableAttendances> findTimetableAttendancesByAttendanceMachine(Integer attendanceMachine) throws DataAccessException {

		return findTimetableAttendancesByAttendanceMachine(attendanceMachine, -1, -1);
	}

	/**
	 * JPQL Query - findTimetableAttendancesByAttendanceMachine
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TimetableAttendances> findTimetableAttendancesByAttendanceMachine(Integer attendanceMachine, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTimetableAttendancesByAttendanceMachine", startResult, maxRows, attendanceMachine);
		return new LinkedHashSet<TimetableAttendances>(query.getResultList());
	}

	/**
	 * JPQL Query - findTimetableAttendancesByWeekday
	 *
	 */
	@Transactional
	public Set<TimetableAttendances> findTimetableAttendancesByWeekday(Integer weekday) throws DataAccessException {

		return findTimetableAttendancesByWeekday(weekday, -1, -1);
	}

	/**
	 * JPQL Query - findTimetableAttendancesByWeekday
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TimetableAttendances> findTimetableAttendancesByWeekday(Integer weekday, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTimetableAttendancesByWeekday", startResult, maxRows, weekday);
		return new LinkedHashSet<TimetableAttendances>(query.getResultList());
	}

	/**
	 * JPQL Query - findTimetableAttendancesByAttendDate
	 *
	 */
	@Transactional
	public Set<TimetableAttendances> findTimetableAttendancesByAttendDate(java.util.Calendar attendDate) throws DataAccessException {

		return findTimetableAttendancesByAttendDate(attendDate, -1, -1);
	}

	/**
	 * JPQL Query - findTimetableAttendancesByAttendDate
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TimetableAttendances> findTimetableAttendancesByAttendDate(java.util.Calendar attendDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTimetableAttendancesByAttendDate", startResult, maxRows, attendDate);
		return new LinkedHashSet<TimetableAttendances>(query.getResultList());
	}

	/**
	 * JPQL Query - findTimetableAttendancesByEndClass
	 *
	 */
	@Transactional
	public Set<TimetableAttendances> findTimetableAttendancesByEndClass(Integer endClass) throws DataAccessException {

		return findTimetableAttendancesByEndClass(endClass, -1, -1);
	}

	/**
	 * JPQL Query - findTimetableAttendancesByEndClass
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TimetableAttendances> findTimetableAttendancesByEndClass(Integer endClass, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTimetableAttendancesByEndClass", startResult, maxRows, endClass);
		return new LinkedHashSet<TimetableAttendances>(query.getResultList());
	}

	/**
	 * JPQL Query - findTimetableAttendancesByLabNumber
	 *
	 */
	@Transactional
	public Set<TimetableAttendances> findTimetableAttendancesByLabNumber(Integer labNumber) throws DataAccessException {

		return findTimetableAttendancesByLabNumber(labNumber, -1, -1);
	}

	/**
	 * JPQL Query - findTimetableAttendancesByLabNumber
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TimetableAttendances> findTimetableAttendancesByLabNumber(Integer labNumber, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTimetableAttendancesByLabNumber", startResult, maxRows, labNumber);
		return new LinkedHashSet<TimetableAttendances>(query.getResultList());
	}

	/**
	 * JPQL Query - findTimetableAttendancesByAttendanceStatus
	 *
	 */
	@Transactional
	public Set<TimetableAttendances> findTimetableAttendancesByAttendanceStatus(String attendanceStatus) throws DataAccessException {

		return findTimetableAttendancesByAttendanceStatus(attendanceStatus, -1, -1);
	}

	/**
	 * JPQL Query - findTimetableAttendancesByAttendanceStatus
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TimetableAttendances> findTimetableAttendancesByAttendanceStatus(String attendanceStatus, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTimetableAttendancesByAttendanceStatus", startResult, maxRows, attendanceStatus);
		return new LinkedHashSet<TimetableAttendances>(query.getResultList());
	}

	/**
	 * JPQL Query - findTimetableAttendancesByMemoContaining
	 *
	 */
	@Transactional
	public Set<TimetableAttendances> findTimetableAttendancesByMemoContaining(String memo) throws DataAccessException {

		return findTimetableAttendancesByMemoContaining(memo, -1, -1);
	}

	/**
	 * JPQL Query - findTimetableAttendancesByMemoContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TimetableAttendances> findTimetableAttendancesByMemoContaining(String memo, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTimetableAttendancesByMemoContaining", startResult, maxRows, memo);
		return new LinkedHashSet<TimetableAttendances>(query.getResultList());
	}

	/**
	 * JPQL Query - findTimetableAttendancesByDetailId
	 *
	 */
	@Transactional
	public Set<TimetableAttendances> findTimetableAttendancesByDetailId(Integer detailId) throws DataAccessException {

		return findTimetableAttendancesByDetailId(detailId, -1, -1);
	}

	/**
	 * JPQL Query - findTimetableAttendancesByDetailId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TimetableAttendances> findTimetableAttendancesByDetailId(Integer detailId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTimetableAttendancesByDetailId", startResult, maxRows, detailId);
		return new LinkedHashSet<TimetableAttendances>(query.getResultList());
	}

	/**
	 * JPQL Query - findTimetableAttendancesByUpdatedDate
	 *
	 */
	@Transactional
	public Set<TimetableAttendances> findTimetableAttendancesByUpdatedDate(java.util.Calendar updatedDate) throws DataAccessException {

		return findTimetableAttendancesByUpdatedDate(updatedDate, -1, -1);
	}

	/**
	 * JPQL Query - findTimetableAttendancesByUpdatedDate
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TimetableAttendances> findTimetableAttendancesByUpdatedDate(java.util.Calendar updatedDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTimetableAttendancesByUpdatedDate", startResult, maxRows, updatedDate);
		return new LinkedHashSet<TimetableAttendances>(query.getResultList());
	}

	/**
	 * JPQL Query - findTimetableAttendancesById
	 *
	 */
	@Transactional
	public TimetableAttendances findTimetableAttendancesById(Integer id) throws DataAccessException {

		return findTimetableAttendancesById(id, -1, -1);
	}

	/**
	 * JPQL Query - findTimetableAttendancesById
	 *
	 */

	@Transactional
	public TimetableAttendances findTimetableAttendancesById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findTimetableAttendancesById", startResult, maxRows, id);
			return (net.xidlims.domain.TimetableAttendances) query.getSingleResult();
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
	public boolean canBeMerged(TimetableAttendances entity) {
		return true;
	}
}
