package net.xidlims.dao;

import java.util.Calendar;
import java.util.Set;

import net.xidlims.domain.TimetableAppointmentSameNumber;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage TimetableAppointmentSameNumber entities.
 * 
 */
public interface TimetableAppointmentSameNumberDAO extends
		JpaDao<TimetableAppointmentSameNumber> {

	/**
	 * JPQL Query - findTimetableAppointmentSameNumberByUpdatedBy
	 *
	 */
	public Set<TimetableAppointmentSameNumber> findTimetableAppointmentSameNumberByUpdatedBy(Integer updatedBy) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAppointmentSameNumberByUpdatedBy
	 *
	 */
	public Set<TimetableAppointmentSameNumber> findTimetableAppointmentSameNumberByUpdatedBy(Integer updatedBy, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAppointmentSameNumberByTotalWeeksContaining
	 *
	 */
	public Set<TimetableAppointmentSameNumber> findTimetableAppointmentSameNumberByTotalWeeksContaining(String totalWeeks) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAppointmentSameNumberByTotalWeeksContaining
	 *
	 */
	public Set<TimetableAppointmentSameNumber> findTimetableAppointmentSameNumberByTotalWeeksContaining(String totalWeeks, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllTimetableAppointmentSameNumbers
	 *
	 */
	public Set<TimetableAppointmentSameNumber> findAllTimetableAppointmentSameNumbers() throws DataAccessException;

	/**
	 * JPQL Query - findAllTimetableAppointmentSameNumbers
	 *
	 */
	public Set<TimetableAppointmentSameNumber> findAllTimetableAppointmentSameNumbers(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAppointmentSameNumberByEndClass
	 *
	 */
	public Set<TimetableAppointmentSameNumber> findTimetableAppointmentSameNumberByEndClass(Integer endClass) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAppointmentSameNumberByEndClass
	 *
	 */
	public Set<TimetableAppointmentSameNumber> findTimetableAppointmentSameNumberByEndClass(Integer endClass, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAppointmentSameNumberByEndWeek
	 *
	 */
	public Set<TimetableAppointmentSameNumber> findTimetableAppointmentSameNumberByEndWeek(Integer endWeek) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAppointmentSameNumberByEndWeek
	 *
	 */
	public Set<TimetableAppointmentSameNumber> findTimetableAppointmentSameNumberByEndWeek(Integer endWeek, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAppointmentSameNumberByStartClass
	 *
	 */
	public Set<TimetableAppointmentSameNumber> findTimetableAppointmentSameNumberByStartClass(Integer startClass) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAppointmentSameNumberByStartClass
	 *
	 */
	public Set<TimetableAppointmentSameNumber> findTimetableAppointmentSameNumberByStartClass(Integer startClass, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAppointmentSameNumberByUpdatedDate
	 *
	 */
	public Set<TimetableAppointmentSameNumber> findTimetableAppointmentSameNumberByUpdatedDate(java.util.Calendar updatedDate) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAppointmentSameNumberByUpdatedDate
	 *
	 */
	public Set<TimetableAppointmentSameNumber> findTimetableAppointmentSameNumberByUpdatedDate(Calendar updatedDate, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAppointmentSameNumberByTotalWeeks
	 *
	 */
	public Set<TimetableAppointmentSameNumber> findTimetableAppointmentSameNumberByTotalWeeks(String totalWeeks_1) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAppointmentSameNumberByTotalWeeks
	 *
	 */
	public Set<TimetableAppointmentSameNumber> findTimetableAppointmentSameNumberByTotalWeeks(String totalWeeks_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAppointmentSameNumberById
	 *
	 */
	public TimetableAppointmentSameNumber findTimetableAppointmentSameNumberById(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAppointmentSameNumberById
	 *
	 */
	public TimetableAppointmentSameNumber findTimetableAppointmentSameNumberById(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAppointmentSameNumberByTotalClasses
	 *
	 */
	public Set<TimetableAppointmentSameNumber> findTimetableAppointmentSameNumberByTotalClasses(Integer totalClasses) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAppointmentSameNumberByTotalClasses
	 *
	 */
	public Set<TimetableAppointmentSameNumber> findTimetableAppointmentSameNumberByTotalClasses(Integer totalClasses, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAppointmentSameNumberByCreatedBy
	 *
	 */
	public Set<TimetableAppointmentSameNumber> findTimetableAppointmentSameNumberByCreatedBy(Integer createdBy) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAppointmentSameNumberByCreatedBy
	 *
	 */
	public Set<TimetableAppointmentSameNumber> findTimetableAppointmentSameNumberByCreatedBy(Integer createdBy, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAppointmentSameNumberByPrimaryKey
	 *
	 */
	public TimetableAppointmentSameNumber findTimetableAppointmentSameNumberByPrimaryKey(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAppointmentSameNumberByPrimaryKey
	 *
	 */
	public TimetableAppointmentSameNumber findTimetableAppointmentSameNumberByPrimaryKey(Integer id_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAppointmentSameNumberByWeekday
	 *
	 */
	public Set<TimetableAppointmentSameNumber> findTimetableAppointmentSameNumberByWeekday(Integer weekday) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAppointmentSameNumberByWeekday
	 *
	 */
	public Set<TimetableAppointmentSameNumber> findTimetableAppointmentSameNumberByWeekday(Integer weekday, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAppointmentSameNumberByCreatedDate
	 *
	 */
	public Set<TimetableAppointmentSameNumber> findTimetableAppointmentSameNumberByCreatedDate(java.util.Calendar createdDate) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAppointmentSameNumberByCreatedDate
	 *
	 */
	public Set<TimetableAppointmentSameNumber> findTimetableAppointmentSameNumberByCreatedDate(Calendar createdDate, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAppointmentSameNumberByStartWeek
	 *
	 */
	public Set<TimetableAppointmentSameNumber> findTimetableAppointmentSameNumberByStartWeek(Integer startWeek) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAppointmentSameNumberByStartWeek
	 *
	 */
	public Set<TimetableAppointmentSameNumber> findTimetableAppointmentSameNumberByStartWeek(Integer startWeek, int startResult, int maxRows) throws DataAccessException;

}