package net.xidlims.dao;

import java.util.Calendar;
import java.util.Set;

import net.xidlims.domain.TimetableAppointment;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage TimetableAppointment entities.
 * 
 */
public interface TimetableAppointmentDAO extends JpaDao<TimetableAppointment> {

	/**
	 * JPQL Query - findTimetableAppointmentByCreatedBy
	 *
	 */
	public Set<TimetableAppointment> findTimetableAppointmentByCreatedBy(Integer createdBy) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAppointmentByCreatedBy
	 *
	 */
	public Set<TimetableAppointment> findTimetableAppointmentByCreatedBy(Integer createdBy, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAppointmentByTimetableNumber
	 *
	 */
	public Set<TimetableAppointment> findTimetableAppointmentByTimetableNumber(Integer timetableNumber) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAppointmentByTimetableNumber
	 *
	 */
	public Set<TimetableAppointment> findTimetableAppointmentByTimetableNumber(Integer timetableNumber, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAppointmentByStartWeek
	 *
	 */
	public Set<TimetableAppointment> findTimetableAppointmentByStartWeek(Integer startWeek) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAppointmentByStartWeek
	 *
	 */
	public Set<TimetableAppointment> findTimetableAppointmentByStartWeek(Integer startWeek, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAppointmentByEndClass
	 *
	 */
	public Set<TimetableAppointment> findTimetableAppointmentByEndClass(Integer endClass) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAppointmentByEndClass
	 *
	 */
	public Set<TimetableAppointment> findTimetableAppointmentByEndClass(Integer endClass, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAppointmentByTimetableStyle
	 *
	 */
	public Set<TimetableAppointment> findTimetableAppointmentByTimetableStyle(Integer timetableStyle) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAppointmentByTimetableStyle
	 *
	 */
	public Set<TimetableAppointment> findTimetableAppointmentByTimetableStyle(Integer timetableStyle, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAppointmentByMemoContaining
	 *
	 */
	public Set<TimetableAppointment> findTimetableAppointmentByMemoContaining(String memo) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAppointmentByMemoContaining
	 *
	 */
	public Set<TimetableAppointment> findTimetableAppointmentByMemoContaining(String memo, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAppointmentByDetailContaining
	 *
	 */
	public Set<TimetableAppointment> findTimetableAppointmentByDetailContaining(String detail) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAppointmentByDetailContaining
	 *
	 */
	public Set<TimetableAppointment> findTimetableAppointmentByDetailContaining(String detail, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAppointmentByPrimaryKey
	 *
	 */
	public TimetableAppointment findTimetableAppointmentByPrimaryKey(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAppointmentByPrimaryKey
	 *
	 */
	public TimetableAppointment findTimetableAppointmentByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAppointmentById
	 *
	 */
	public TimetableAppointment findTimetableAppointmentById(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAppointmentById
	 *
	 */
	public TimetableAppointment findTimetableAppointmentById(Integer id_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAppointmentByCourseCode
	 *
	 */
	public Set<TimetableAppointment> findTimetableAppointmentByCourseCode(String courseCode) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAppointmentByCourseCode
	 *
	 */
	public Set<TimetableAppointment> findTimetableAppointmentByCourseCode(String courseCode, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAppointmentByMemo
	 *
	 */
	public Set<TimetableAppointment> findTimetableAppointmentByMemo(String memo_1) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAppointmentByMemo
	 *
	 */
	public Set<TimetableAppointment> findTimetableAppointmentByMemo(String memo_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAppointmentByCourseCodeContaining
	 *
	 */
	public Set<TimetableAppointment> findTimetableAppointmentByCourseCodeContaining(String courseCode_1) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAppointmentByCourseCodeContaining
	 *
	 */
	public Set<TimetableAppointment> findTimetableAppointmentByCourseCodeContaining(String courseCode_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAppointmentByAppointmentNo
	 *
	 */
	public Set<TimetableAppointment> findTimetableAppointmentByAppointmentNo(String appointmentNo) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAppointmentByAppointmentNo
	 *
	 */
	public Set<TimetableAppointment> findTimetableAppointmentByAppointmentNo(String appointmentNo, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAppointmentByTotalClasses
	 *
	 */
	public Set<TimetableAppointment> findTimetableAppointmentByTotalClasses(Integer totalClasses) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAppointmentByTotalClasses
	 *
	 */
	public Set<TimetableAppointment> findTimetableAppointmentByTotalClasses(Integer totalClasses, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAppointmentByTotalWeeksContaining
	 *
	 */
	public Set<TimetableAppointment> findTimetableAppointmentByTotalWeeksContaining(String totalWeeks) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAppointmentByTotalWeeksContaining
	 *
	 */
	public Set<TimetableAppointment> findTimetableAppointmentByTotalWeeksContaining(String totalWeeks, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAppointmentByUpdatedBy
	 *
	 */
	public Set<TimetableAppointment> findTimetableAppointmentByUpdatedBy(Integer updatedBy) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAppointmentByUpdatedBy
	 *
	 */
	public Set<TimetableAppointment> findTimetableAppointmentByUpdatedBy(Integer updatedBy, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllTimetableAppointments
	 *
	 */
	public Set<TimetableAppointment> findAllTimetableAppointments() throws DataAccessException;

	/**
	 * JPQL Query - findAllTimetableAppointments
	 *
	 */
	public Set<TimetableAppointment> findAllTimetableAppointments(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAppointmentByAppointmentNoContaining
	 *
	 */
	public Set<TimetableAppointment> findTimetableAppointmentByAppointmentNoContaining(String appointmentNo_1) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAppointmentByAppointmentNoContaining
	 *
	 */
	public Set<TimetableAppointment> findTimetableAppointmentByAppointmentNoContaining(String appointmentNo_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAppointmentByEnabled
	 *
	 */
	public Set<TimetableAppointment> findTimetableAppointmentByEnabled(Boolean enabled) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAppointmentByEnabled
	 *
	 */
	public Set<TimetableAppointment> findTimetableAppointmentByEnabled(Boolean enabled, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAppointmentByDetail
	 *
	 */
	public Set<TimetableAppointment> findTimetableAppointmentByDetail(String detail_1) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAppointmentByDetail
	 *
	 */
	public Set<TimetableAppointment> findTimetableAppointmentByDetail(String detail_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAppointmentByStartClass
	 *
	 */
	public Set<TimetableAppointment> findTimetableAppointmentByStartClass(Integer startClass) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAppointmentByStartClass
	 *
	 */
	public Set<TimetableAppointment> findTimetableAppointmentByStartClass(Integer startClass, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAppointmentByUpdatedDate
	 *
	 */
	public Set<TimetableAppointment> findTimetableAppointmentByUpdatedDate(java.util.Calendar updatedDate) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAppointmentByUpdatedDate
	 *
	 */
	public Set<TimetableAppointment> findTimetableAppointmentByUpdatedDate(Calendar updatedDate, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAppointmentByTotalWeeks
	 *
	 */
	public Set<TimetableAppointment> findTimetableAppointmentByTotalWeeks(String totalWeeks_1) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAppointmentByTotalWeeks
	 *
	 */
	public Set<TimetableAppointment> findTimetableAppointmentByTotalWeeks(String totalWeeks_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAppointmentByWeekday
	 *
	 */
	public Set<TimetableAppointment> findTimetableAppointmentByWeekday(Integer weekday) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAppointmentByWeekday
	 *
	 */
	public Set<TimetableAppointment> findTimetableAppointmentByWeekday(Integer weekday, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAppointmentByEndWeek
	 *
	 */
	public Set<TimetableAppointment> findTimetableAppointmentByEndWeek(Integer endWeek) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAppointmentByEndWeek
	 *
	 */
	public Set<TimetableAppointment> findTimetableAppointmentByEndWeek(Integer endWeek, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAppointmentByCreatedDate
	 *
	 */
	public Set<TimetableAppointment> findTimetableAppointmentByCreatedDate(java.util.Calendar createdDate) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAppointmentByCreatedDate
	 *
	 */
	public Set<TimetableAppointment> findTimetableAppointmentByCreatedDate(Calendar createdDate, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAppointmentByStatus
	 *
	 */
	public Set<TimetableAppointment> findTimetableAppointmentByStatus(Integer status) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableAppointmentByStatus
	 *
	 */
	public Set<TimetableAppointment> findTimetableAppointmentByStatus(Integer status, int startResult, int maxRows) throws DataAccessException;

}