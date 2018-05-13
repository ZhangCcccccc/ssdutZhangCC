package net.xidlims.dao;

import java.util.Calendar;
import java.util.Set;

import net.xidlims.domain.TimetableAttendances;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage TimetableAttendances entities.
 * 
 */
public interface TimetableAttendancesDAO extends JpaDao<TimetableAttendances> {

	/**
	 * JPQL Query - findTimetableAttendancesByUserNumber
	 *
	 */
	public Set<TimetableAttendances> findTimetableAttendancesByUserNumber(String userNumber) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAttendancesByUserNumber
	 *
	 */
	public Set<TimetableAttendances> findTimetableAttendancesByUserNumber(String userNumber, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAttendancesByCreatedBy
	 *
	 */
	public Set<TimetableAttendances> findTimetableAttendancesByCreatedBy(Integer createdBy) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAttendancesByCreatedBy
	 *
	 */
	public Set<TimetableAttendances> findTimetableAttendancesByCreatedBy(Integer createdBy, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAttendancesByWeek
	 *
	 */
	public Set<TimetableAttendances> findTimetableAttendancesByWeek(Integer week) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAttendancesByWeek
	 *
	 */
	public Set<TimetableAttendances> findTimetableAttendancesByWeek(Integer week, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAttendancesByPrimaryKey
	 *
	 */
	public TimetableAttendances findTimetableAttendancesByPrimaryKey(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAttendancesByPrimaryKey
	 *
	 */
	public TimetableAttendances findTimetableAttendancesByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAttendancesByEndDateTime
	 *
	 */
	public Set<TimetableAttendances> findTimetableAttendancesByEndDateTime(java.util.Calendar endDateTime) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAttendancesByEndDateTime
	 *
	 */
	public Set<TimetableAttendances> findTimetableAttendancesByEndDateTime(Calendar endDateTime, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAttendancesByActualAttendance
	 *
	 */
	public Set<TimetableAttendances> findTimetableAttendancesByActualAttendance(Integer actualAttendance) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAttendancesByActualAttendance
	 *
	 */
	public Set<TimetableAttendances> findTimetableAttendancesByActualAttendance(Integer actualAttendance, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAttendancesByCreatedDate
	 *
	 */
	public Set<TimetableAttendances> findTimetableAttendancesByCreatedDate(java.util.Calendar createdDate) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAttendancesByCreatedDate
	 *
	 */
	public Set<TimetableAttendances> findTimetableAttendancesByCreatedDate(Calendar createdDate, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAttendancesByMemo
	 *
	 */
	public Set<TimetableAttendances> findTimetableAttendancesByMemo(String memo) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAttendancesByMemo
	 *
	 */
	public Set<TimetableAttendances> findTimetableAttendancesByMemo(String memo, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAttendancesByStartDateTime
	 *
	 */
	public Set<TimetableAttendances> findTimetableAttendancesByStartDateTime(java.util.Calendar startDateTime) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAttendancesByStartDateTime
	 *
	 */
	public Set<TimetableAttendances> findTimetableAttendancesByStartDateTime(Calendar startDateTime, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAttendancesByAttendanceStatusContaining
	 *
	 */
	public Set<TimetableAttendances> findTimetableAttendancesByAttendanceStatusContaining(String attendanceStatus) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAttendancesByAttendanceStatusContaining
	 *
	 */
	public Set<TimetableAttendances> findTimetableAttendancesByAttendanceStatusContaining(String attendanceStatus, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllTimetableAttendancess
	 *
	 */
	public Set<TimetableAttendances> findAllTimetableAttendancess() throws DataAccessException;

	/**
	 * JPQL Query - findAllTimetableAttendancess
	 *
	 */
	public Set<TimetableAttendances> findAllTimetableAttendancess(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAttendancesByUserNumberContaining
	 *
	 */
	public Set<TimetableAttendances> findTimetableAttendancesByUserNumberContaining(String userNumber_1) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAttendancesByUserNumberContaining
	 *
	 */
	public Set<TimetableAttendances> findTimetableAttendancesByUserNumberContaining(String userNumber_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAttendancesByStartClass
	 *
	 */
	public Set<TimetableAttendances> findTimetableAttendancesByStartClass(Integer startClass) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAttendancesByStartClass
	 *
	 */
	public Set<TimetableAttendances> findTimetableAttendancesByStartClass(Integer startClass, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAttendancesByAttendanceMachine
	 *
	 */
	public Set<TimetableAttendances> findTimetableAttendancesByAttendanceMachine(Integer attendanceMachine) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAttendancesByAttendanceMachine
	 *
	 */
	public Set<TimetableAttendances> findTimetableAttendancesByAttendanceMachine(Integer attendanceMachine, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAttendancesByWeekday
	 *
	 */
	public Set<TimetableAttendances> findTimetableAttendancesByWeekday(Integer weekday) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAttendancesByWeekday
	 *
	 */
	public Set<TimetableAttendances> findTimetableAttendancesByWeekday(Integer weekday, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAttendancesByAttendDate
	 *
	 */
	public Set<TimetableAttendances> findTimetableAttendancesByAttendDate(java.util.Calendar attendDate) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAttendancesByAttendDate
	 *
	 */
	public Set<TimetableAttendances> findTimetableAttendancesByAttendDate(Calendar attendDate, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAttendancesByEndClass
	 *
	 */
	public Set<TimetableAttendances> findTimetableAttendancesByEndClass(Integer endClass) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAttendancesByEndClass
	 *
	 */
	public Set<TimetableAttendances> findTimetableAttendancesByEndClass(Integer endClass, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAttendancesByLabNumber
	 *
	 */
	public Set<TimetableAttendances> findTimetableAttendancesByLabNumber(Integer labNumber) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAttendancesByLabNumber
	 *
	 */
	public Set<TimetableAttendances> findTimetableAttendancesByLabNumber(Integer labNumber, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAttendancesByAttendanceStatus
	 *
	 */
	public Set<TimetableAttendances> findTimetableAttendancesByAttendanceStatus(String attendanceStatus_1) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAttendancesByAttendanceStatus
	 *
	 */
	public Set<TimetableAttendances> findTimetableAttendancesByAttendanceStatus(String attendanceStatus_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAttendancesByMemoContaining
	 *
	 */
	public Set<TimetableAttendances> findTimetableAttendancesByMemoContaining(String memo_1) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAttendancesByMemoContaining
	 *
	 */
	public Set<TimetableAttendances> findTimetableAttendancesByMemoContaining(String memo_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAttendancesByDetailId
	 *
	 */
	public Set<TimetableAttendances> findTimetableAttendancesByDetailId(Integer detailId) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAttendancesByDetailId
	 *
	 */
	public Set<TimetableAttendances> findTimetableAttendancesByDetailId(Integer detailId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAttendancesByUpdatedDate
	 *
	 */
	public Set<TimetableAttendances> findTimetableAttendancesByUpdatedDate(java.util.Calendar updatedDate) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAttendancesByUpdatedDate
	 *
	 */
	public Set<TimetableAttendances> findTimetableAttendancesByUpdatedDate(Calendar updatedDate, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAttendancesById
	 *
	 */
	public TimetableAttendances findTimetableAttendancesById(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAttendancesById
	 *
	 */
	public TimetableAttendances findTimetableAttendancesById(Integer id_1, int startResult, int maxRows) throws DataAccessException;

}