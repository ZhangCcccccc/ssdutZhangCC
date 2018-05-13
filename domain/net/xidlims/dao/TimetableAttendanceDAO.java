package net.xidlims.dao;

import java.util.Calendar;
import java.util.Set;

import net.xidlims.domain.TimetableAttendance;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage TimetableAttendance entities.
 * 
 */
public interface TimetableAttendanceDAO extends JpaDao<TimetableAttendance> {

	/**
	 * JPQL Query - findTimetableAttendanceById
	 *
	 */
	public TimetableAttendance findTimetableAttendanceById(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAttendanceById
	 *
	 */
	public TimetableAttendance findTimetableAttendanceById(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAttendanceByAttendanceStatusContaining
	 *
	 */
	public Set<TimetableAttendance> findTimetableAttendanceByAttendanceStatusContaining(String attendanceStatus) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAttendanceByAttendanceStatusContaining
	 *
	 */
	public Set<TimetableAttendance> findTimetableAttendanceByAttendanceStatusContaining(String attendanceStatus, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAttendanceByAttendDate
	 *
	 */
	public Set<TimetableAttendance> findTimetableAttendanceByAttendDate(java.util.Calendar attendDate) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAttendanceByAttendDate
	 *
	 */
	public Set<TimetableAttendance> findTimetableAttendanceByAttendDate(Calendar attendDate, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAttendanceByActualAttendance
	 *
	 */
	public Set<TimetableAttendance> findTimetableAttendanceByActualAttendance(Integer actualAttendance) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAttendanceByActualAttendance
	 *
	 */
	public Set<TimetableAttendance> findTimetableAttendanceByActualAttendance(Integer actualAttendance, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAttendanceByMemo
	 *
	 */
	public Set<TimetableAttendance> findTimetableAttendanceByMemo(String memo) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAttendanceByMemo
	 *
	 */
	public Set<TimetableAttendance> findTimetableAttendanceByMemo(String memo, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAttendanceByCreatedDate
	 *
	 */
	public Set<TimetableAttendance> findTimetableAttendanceByCreatedDate(java.util.Calendar createdDate) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAttendanceByCreatedDate
	 *
	 */
	public Set<TimetableAttendance> findTimetableAttendanceByCreatedDate(Calendar createdDate, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllTimetableAttendances
	 *
	 */
	public Set<TimetableAttendance> findAllTimetableAttendances() throws DataAccessException;

	/**
	 * JPQL Query - findAllTimetableAttendances
	 *
	 */
	public Set<TimetableAttendance> findAllTimetableAttendances(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAttendanceByWeekday
	 *
	 */
	public Set<TimetableAttendance> findTimetableAttendanceByWeekday(Integer weekday) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAttendanceByWeekday
	 *
	 */
	public Set<TimetableAttendance> findTimetableAttendanceByWeekday(Integer weekday, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAttendanceByMemoContaining
	 *
	 */
	public Set<TimetableAttendance> findTimetableAttendanceByMemoContaining(String memo_1) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAttendanceByMemoContaining
	 *
	 */
	public Set<TimetableAttendance> findTimetableAttendanceByMemoContaining(String memo_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAttendanceByPrimaryKey
	 *
	 */
	public TimetableAttendance findTimetableAttendanceByPrimaryKey(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAttendanceByPrimaryKey
	 *
	 */
	public TimetableAttendance findTimetableAttendanceByPrimaryKey(Integer id_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAttendanceByUpdatedDate
	 *
	 */
	public Set<TimetableAttendance> findTimetableAttendanceByUpdatedDate(java.util.Calendar updatedDate) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAttendanceByUpdatedDate
	 *
	 */
	public Set<TimetableAttendance> findTimetableAttendanceByUpdatedDate(Calendar updatedDate, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAttendanceByArrangeClass
	 *
	 */
	public Set<TimetableAttendance> findTimetableAttendanceByArrangeClass(Integer arrangeClass) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAttendanceByArrangeClass
	 *
	 */
	public Set<TimetableAttendance> findTimetableAttendanceByArrangeClass(Integer arrangeClass, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAttendanceByWeek
	 *
	 */
	public Set<TimetableAttendance> findTimetableAttendanceByWeek(Integer week) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAttendanceByWeek
	 *
	 */
	public Set<TimetableAttendance> findTimetableAttendanceByWeek(Integer week, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAttendanceByAttendanceMachine
	 *
	 */
	public Set<TimetableAttendance> findTimetableAttendanceByAttendanceMachine(Integer attendanceMachine) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAttendanceByAttendanceMachine
	 *
	 */
	public Set<TimetableAttendance> findTimetableAttendanceByAttendanceMachine(Integer attendanceMachine, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAttendanceByAttendanceStatus
	 *
	 */
	public Set<TimetableAttendance> findTimetableAttendanceByAttendanceStatus(String attendanceStatus_1) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAttendanceByAttendanceStatus
	 *
	 */
	public Set<TimetableAttendance> findTimetableAttendanceByAttendanceStatus(String attendanceStatus_1, int startResult, int maxRows) throws DataAccessException;

}