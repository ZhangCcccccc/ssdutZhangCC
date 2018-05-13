package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.TimetableAppointment;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage TimetableAppointment entities.
 * 
 */
@Repository("TimetableAppointmentDAO")
@Transactional
public class TimetableAppointmentDAOImpl extends AbstractJpaDao<TimetableAppointment>
		implements TimetableAppointmentDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { TimetableAppointment.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new TimetableAppointmentDAOImpl
	 *
	 */
	public TimetableAppointmentDAOImpl() {
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
	 * JPQL Query - findTimetableAppointmentByCreatedBy
	 *
	 */
	@Transactional
	public Set<TimetableAppointment> findTimetableAppointmentByCreatedBy(Integer createdBy) throws DataAccessException {

		return findTimetableAppointmentByCreatedBy(createdBy, -1, -1);
	}

	/**
	 * JPQL Query - findTimetableAppointmentByCreatedBy
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TimetableAppointment> findTimetableAppointmentByCreatedBy(Integer createdBy, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTimetableAppointmentByCreatedBy", startResult, maxRows, createdBy);
		return new LinkedHashSet<TimetableAppointment>(query.getResultList());
	}

	/**
	 * JPQL Query - findTimetableAppointmentByTimetableNumber
	 *
	 */
	@Transactional
	public Set<TimetableAppointment> findTimetableAppointmentByTimetableNumber(Integer timetableNumber) throws DataAccessException {
		return findTimetableAppointmentByTimetableNumber(timetableNumber, -1, -1);
	}

	/**
	 * JPQL Query - findTimetableAppointmentByTimetableNumber
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TimetableAppointment> findTimetableAppointmentByTimetableNumber(Integer timetableNumber, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTimetableAppointmentByTimetableNumber", startResult, maxRows, timetableNumber);
		return new LinkedHashSet<TimetableAppointment>(query.getResultList());
	}

	/**
	 * JPQL Query - findTimetableAppointmentByStartWeek
	 *
	 */
	@Transactional
	public Set<TimetableAppointment> findTimetableAppointmentByStartWeek(Integer startWeek) throws DataAccessException {

		return findTimetableAppointmentByStartWeek(startWeek, -1, -1);
	}
	
	/**
	 * JPQL Query - findTimetableAppointmentByStartWeek
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TimetableAppointment> findTimetableAppointmentByStartWeek(Integer startWeek, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTimetableAppointmentByStartWeek", startResult, maxRows, startWeek);
		return new LinkedHashSet<TimetableAppointment>(query.getResultList());
	}

	/**
	 * JPQL Query - findTimetableAppointmentByEndClass
	 *
	 */
	@Transactional
	public Set<TimetableAppointment> findTimetableAppointmentByEndClass(Integer endClass) throws DataAccessException {

		return findTimetableAppointmentByEndClass(endClass, -1, -1);
	}

	/**
	 * JPQL Query - findTimetableAppointmentByEndClass
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TimetableAppointment> findTimetableAppointmentByEndClass(Integer endClass, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTimetableAppointmentByEndClass", startResult, maxRows, endClass);
		return new LinkedHashSet<TimetableAppointment>(query.getResultList());
	}

	/**
	 * JPQL Query - findTimetableAppointmentByTimetableStyle
	 *
	 */
	@Transactional
	public Set<TimetableAppointment> findTimetableAppointmentByTimetableStyle(Integer timetableStyle) throws DataAccessException {

		return findTimetableAppointmentByTimetableStyle(timetableStyle, -1, -1);
	}

	/**
	 * JPQL Query - findTimetableAppointmentByTimetableStyle
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TimetableAppointment> findTimetableAppointmentByTimetableStyle(Integer timetableStyle, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTimetableAppointmentByTimetableStyle", startResult, maxRows, timetableStyle);
		return new LinkedHashSet<TimetableAppointment>(query.getResultList());
	}

	/**
	 * JPQL Query - findTimetableAppointmentByMemoContaining
	 *
	 */
	@Transactional
	public Set<TimetableAppointment> findTimetableAppointmentByMemoContaining(String memo) throws DataAccessException {

		return findTimetableAppointmentByMemoContaining(memo, -1, -1);
	}

	/**
	 * JPQL Query - findTimetableAppointmentByMemoContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TimetableAppointment> findTimetableAppointmentByMemoContaining(String memo, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTimetableAppointmentByMemoContaining", startResult, maxRows, memo);
		return new LinkedHashSet<TimetableAppointment>(query.getResultList());
	}

	/**
	 * JPQL Query - findTimetableAppointmentByDetailContaining
	 *
	 */
	@Transactional
	public Set<TimetableAppointment> findTimetableAppointmentByDetailContaining(String detail) throws DataAccessException {

		return findTimetableAppointmentByDetailContaining(detail, -1, -1);
	}

	/**
	 * JPQL Query - findTimetableAppointmentByDetailContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TimetableAppointment> findTimetableAppointmentByDetailContaining(String detail, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTimetableAppointmentByDetailContaining", startResult, maxRows, detail);
		return new LinkedHashSet<TimetableAppointment>(query.getResultList());
	}

	/**
	 * JPQL Query - findTimetableAppointmentByPrimaryKey
	 *
	 */
	@Transactional
	public TimetableAppointment findTimetableAppointmentByPrimaryKey(Integer id) throws DataAccessException {

		return findTimetableAppointmentByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findTimetableAppointmentByPrimaryKey
	 *
	 */

	@Transactional
	public TimetableAppointment findTimetableAppointmentByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findTimetableAppointmentByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.TimetableAppointment) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findTimetableAppointmentById
	 *
	 */
	@Transactional
	public TimetableAppointment findTimetableAppointmentById(Integer id) throws DataAccessException {

		return findTimetableAppointmentById(id, -1, -1);
	}

	/**
	 * JPQL Query - findTimetableAppointmentById
	 *
	 */

	@Transactional
	public TimetableAppointment findTimetableAppointmentById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findTimetableAppointmentById", startResult, maxRows, id);
			return (net.xidlims.domain.TimetableAppointment) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findTimetableAppointmentByCourseCode
	 *
	 */
	@Transactional
	public Set<TimetableAppointment> findTimetableAppointmentByCourseCode(String courseCode) throws DataAccessException {

		return findTimetableAppointmentByCourseCode(courseCode, -1, -1);
	}

	/**
	 * JPQL Query - findTimetableAppointmentByCourseCode
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TimetableAppointment> findTimetableAppointmentByCourseCode(String courseCode, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTimetableAppointmentByCourseCode", startResult, maxRows, courseCode);
		return new LinkedHashSet<TimetableAppointment>(query.getResultList());
	}

	/**
	 * JPQL Query - findTimetableAppointmentByMemo
	 *
	 */
	@Transactional
	public Set<TimetableAppointment> findTimetableAppointmentByMemo(String memo) throws DataAccessException {

		return findTimetableAppointmentByMemo(memo, -1, -1);
	}

	/**
	 * JPQL Query - findTimetableAppointmentByMemo
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TimetableAppointment> findTimetableAppointmentByMemo(String memo, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTimetableAppointmentByMemo", startResult, maxRows, memo);
		return new LinkedHashSet<TimetableAppointment>(query.getResultList());
	}

	/**
	 * JPQL Query - findTimetableAppointmentByCourseCodeContaining
	 *
	 */
	@Transactional
	public Set<TimetableAppointment> findTimetableAppointmentByCourseCodeContaining(String courseCode) throws DataAccessException {

		return findTimetableAppointmentByCourseCodeContaining(courseCode, -1, -1);
	}

	/**
	 * JPQL Query - findTimetableAppointmentByCourseCodeContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TimetableAppointment> findTimetableAppointmentByCourseCodeContaining(String courseCode, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTimetableAppointmentByCourseCodeContaining", startResult, maxRows, courseCode);
		return new LinkedHashSet<TimetableAppointment>(query.getResultList());
	}

	/**
	 * JPQL Query - findTimetableAppointmentByAppointmentNo
	 *
	 */
	@Transactional
	public Set<TimetableAppointment> findTimetableAppointmentByAppointmentNo(String appointmentNo) throws DataAccessException {

		return findTimetableAppointmentByAppointmentNo(appointmentNo, -1, -1);
	}

	/**
	 * JPQL Query - findTimetableAppointmentByAppointmentNo
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TimetableAppointment> findTimetableAppointmentByAppointmentNo(String appointmentNo, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTimetableAppointmentByAppointmentNo", startResult, maxRows, appointmentNo);
		return new LinkedHashSet<TimetableAppointment>(query.getResultList());
	}

	/**
	 * JPQL Query - findTimetableAppointmentByTotalClasses
	 *
	 */
	@Transactional
	public Set<TimetableAppointment> findTimetableAppointmentByTotalClasses(Integer totalClasses) throws DataAccessException {

		return findTimetableAppointmentByTotalClasses(totalClasses, -1, -1);
	}

	/**
	 * JPQL Query - findTimetableAppointmentByTotalClasses
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TimetableAppointment> findTimetableAppointmentByTotalClasses(Integer totalClasses, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTimetableAppointmentByTotalClasses", startResult, maxRows, totalClasses);
		return new LinkedHashSet<TimetableAppointment>(query.getResultList());
	}

	/**
	 * JPQL Query - findTimetableAppointmentByTotalWeeksContaining
	 *
	 */
	@Transactional
	public Set<TimetableAppointment> findTimetableAppointmentByTotalWeeksContaining(String totalWeeks) throws DataAccessException {

		return findTimetableAppointmentByTotalWeeksContaining(totalWeeks, -1, -1);
	}

	/**
	 * JPQL Query - findTimetableAppointmentByTotalWeeksContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TimetableAppointment> findTimetableAppointmentByTotalWeeksContaining(String totalWeeks, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTimetableAppointmentByTotalWeeksContaining", startResult, maxRows, totalWeeks);
		return new LinkedHashSet<TimetableAppointment>(query.getResultList());
	}

	/**
	 * JPQL Query - findTimetableAppointmentByUpdatedBy
	 *
	 */
	@Transactional
	public Set<TimetableAppointment> findTimetableAppointmentByUpdatedBy(Integer updatedBy) throws DataAccessException {

		return findTimetableAppointmentByUpdatedBy(updatedBy, -1, -1);
	}

	/**
	 * JPQL Query - findTimetableAppointmentByUpdatedBy
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TimetableAppointment> findTimetableAppointmentByUpdatedBy(Integer updatedBy, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTimetableAppointmentByUpdatedBy", startResult, maxRows, updatedBy);
		return new LinkedHashSet<TimetableAppointment>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllTimetableAppointments
	 *
	 */
	@Transactional
	public Set<TimetableAppointment> findAllTimetableAppointments() throws DataAccessException {

		return findAllTimetableAppointments(-1, -1);
	}

	/**
	 * JPQL Query - findAllTimetableAppointments
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TimetableAppointment> findAllTimetableAppointments(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllTimetableAppointments", startResult, maxRows);
		return new LinkedHashSet<TimetableAppointment>(query.getResultList());
	}

	/**
	 * JPQL Query - findTimetableAppointmentByAppointmentNoContaining
	 *
	 */
	@Transactional
	public Set<TimetableAppointment> findTimetableAppointmentByAppointmentNoContaining(String appointmentNo) throws DataAccessException {

		return findTimetableAppointmentByAppointmentNoContaining(appointmentNo, -1, -1);
	}

	/**
	 * JPQL Query - findTimetableAppointmentByAppointmentNoContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TimetableAppointment> findTimetableAppointmentByAppointmentNoContaining(String appointmentNo, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTimetableAppointmentByAppointmentNoContaining", startResult, maxRows, appointmentNo);
		return new LinkedHashSet<TimetableAppointment>(query.getResultList());
	}

	/**
	 * JPQL Query - findTimetableAppointmentByEnabled
	 *
	 */
	@Transactional
	public Set<TimetableAppointment> findTimetableAppointmentByEnabled(Boolean enabled) throws DataAccessException {

		return findTimetableAppointmentByEnabled(enabled, -1, -1);
	}

	/**
	 * JPQL Query - findTimetableAppointmentByEnabled
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TimetableAppointment> findTimetableAppointmentByEnabled(Boolean enabled, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTimetableAppointmentByEnabled", startResult, maxRows, enabled);
		return new LinkedHashSet<TimetableAppointment>(query.getResultList());
	}

	/**
	 * JPQL Query - findTimetableAppointmentByDetail
	 *
	 */
	@Transactional
	public Set<TimetableAppointment> findTimetableAppointmentByDetail(String detail) throws DataAccessException {

		return findTimetableAppointmentByDetail(detail, -1, -1);
	}

	/**
	 * JPQL Query - findTimetableAppointmentByDetail
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TimetableAppointment> findTimetableAppointmentByDetail(String detail, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTimetableAppointmentByDetail", startResult, maxRows, detail);
		return new LinkedHashSet<TimetableAppointment>(query.getResultList());
	}

	/**
	 * JPQL Query - findTimetableAppointmentByStartClass
	 *
	 */
	@Transactional
	public Set<TimetableAppointment> findTimetableAppointmentByStartClass(Integer startClass) throws DataAccessException {

		return findTimetableAppointmentByStartClass(startClass, -1, -1);
	}

	/**
	 * JPQL Query - findTimetableAppointmentByStartClass
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TimetableAppointment> findTimetableAppointmentByStartClass(Integer startClass, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTimetableAppointmentByStartClass", startResult, maxRows, startClass);
		return new LinkedHashSet<TimetableAppointment>(query.getResultList());
	}

	/**
	 * JPQL Query - findTimetableAppointmentByUpdatedDate
	 *
	 */
	@Transactional
	public Set<TimetableAppointment> findTimetableAppointmentByUpdatedDate(java.util.Calendar updatedDate) throws DataAccessException {

		return findTimetableAppointmentByUpdatedDate(updatedDate, -1, -1);
	}

	/**
	 * JPQL Query - findTimetableAppointmentByUpdatedDate
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TimetableAppointment> findTimetableAppointmentByUpdatedDate(java.util.Calendar updatedDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTimetableAppointmentByUpdatedDate", startResult, maxRows, updatedDate);
		return new LinkedHashSet<TimetableAppointment>(query.getResultList());
	}

	/**
	 * JPQL Query - findTimetableAppointmentByTotalWeeks
	 *
	 */
	@Transactional
	public Set<TimetableAppointment> findTimetableAppointmentByTotalWeeks(String totalWeeks) throws DataAccessException {

		return findTimetableAppointmentByTotalWeeks(totalWeeks, -1, -1);
	}

	/**
	 * JPQL Query - findTimetableAppointmentByTotalWeeks
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TimetableAppointment> findTimetableAppointmentByTotalWeeks(String totalWeeks, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTimetableAppointmentByTotalWeeks", startResult, maxRows, totalWeeks);
		return new LinkedHashSet<TimetableAppointment>(query.getResultList());
	}

	/**
	 * JPQL Query - findTimetableAppointmentByWeekday
	 *
	 */
	@Transactional
	public Set<TimetableAppointment> findTimetableAppointmentByWeekday(Integer weekday) throws DataAccessException {

		return findTimetableAppointmentByWeekday(weekday, -1, -1);
	}

	/**
	 * JPQL Query - findTimetableAppointmentByWeekday
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TimetableAppointment> findTimetableAppointmentByWeekday(Integer weekday, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTimetableAppointmentByWeekday", startResult, maxRows, weekday);
		return new LinkedHashSet<TimetableAppointment>(query.getResultList());
	}

	/**
	 * JPQL Query - findTimetableAppointmentByEndWeek
	 *
	 */
	@Transactional
	public Set<TimetableAppointment> findTimetableAppointmentByEndWeek(Integer endWeek) throws DataAccessException {

		return findTimetableAppointmentByEndWeek(endWeek, -1, -1);
	}

	/**
	 * JPQL Query - findTimetableAppointmentByEndWeek
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TimetableAppointment> findTimetableAppointmentByEndWeek(Integer endWeek, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTimetableAppointmentByEndWeek", startResult, maxRows, endWeek);
		return new LinkedHashSet<TimetableAppointment>(query.getResultList());
	}

	/**
	 * JPQL Query - findTimetableAppointmentByCreatedDate
	 *
	 */
	@Transactional
	public Set<TimetableAppointment> findTimetableAppointmentByCreatedDate(java.util.Calendar createdDate) throws DataAccessException {

		return findTimetableAppointmentByCreatedDate(createdDate, -1, -1);
	}

	/**
	 * JPQL Query - findTimetableAppointmentByCreatedDate
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TimetableAppointment> findTimetableAppointmentByCreatedDate(java.util.Calendar createdDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTimetableAppointmentByCreatedDate", startResult, maxRows, createdDate);
		return new LinkedHashSet<TimetableAppointment>(query.getResultList());
	}

	/**
	 * JPQL Query - findTimetableAppointmentByStatus
	 *
	 */
	@Transactional
	public Set<TimetableAppointment> findTimetableAppointmentByStatus(Integer status) throws DataAccessException {

		return findTimetableAppointmentByStatus(status, -1, -1);
	}

	/**
	 * JPQL Query - findTimetableAppointmentByStatus
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TimetableAppointment> findTimetableAppointmentByStatus(Integer status, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTimetableAppointmentByStatus", startResult, maxRows, status);
		return new LinkedHashSet<TimetableAppointment>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(TimetableAppointment entity) {
		return true;
	}
}
