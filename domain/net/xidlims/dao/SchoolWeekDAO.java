package net.xidlims.dao;

import java.util.Calendar;
import java.util.Set;

import net.xidlims.domain.SchoolWeek;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage SchoolWeek entities.
 * 
 */
public interface SchoolWeekDAO extends JpaDao<SchoolWeek> {

	/**
	 * JPQL Query - findSchoolWeekByDate
	 *
	 */
	public Set<SchoolWeek> findSchoolWeekByDate(java.util.Calendar date) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolWeekByDate
	 *
	 */
	public Set<SchoolWeek> findSchoolWeekByDate(Calendar date, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolWeekByWeekday
	 *
	 */
	public Set<SchoolWeek> findSchoolWeekByWeekday(Integer weekday) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolWeekByWeekday
	 *
	 */
	public Set<SchoolWeek> findSchoolWeekByWeekday(Integer weekday, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllSchoolWeeks
	 *
	 */
	public Set<SchoolWeek> findAllSchoolWeeks() throws DataAccessException;

	/**
	 * JPQL Query - findAllSchoolWeeks
	 *
	 */
	public Set<SchoolWeek> findAllSchoolWeeks(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolWeekByUpdatedAt
	 *
	 */
	public Set<SchoolWeek> findSchoolWeekByUpdatedAt(java.util.Calendar updatedAt) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolWeekByUpdatedAt
	 *
	 */
	public Set<SchoolWeek> findSchoolWeekByUpdatedAt(Calendar updatedAt, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolWeekByPrimaryKey
	 *
	 */
	public SchoolWeek findSchoolWeekByPrimaryKey(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolWeekByPrimaryKey
	 *
	 */
	public SchoolWeek findSchoolWeekByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolWeekById
	 *
	 */
	public SchoolWeek findSchoolWeekById(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolWeekById
	 *
	 */
	public SchoolWeek findSchoolWeekById(Integer id_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolWeekByDateAfter
	 *
	 */
	public Set<SchoolWeek> findSchoolWeekByDateAfter(java.util.Calendar date_1) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolWeekByDateAfter
	 *
	 */
	public Set<SchoolWeek> findSchoolWeekByDateAfter(Calendar date_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolWeekByDateBefore
	 *
	 */
	public Set<SchoolWeek> findSchoolWeekByDateBefore(java.util.Calendar date_2) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolWeekByDateBefore
	 *
	 */
	public Set<SchoolWeek> findSchoolWeekByDateBefore(Calendar date_2, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolWeekByWeek
	 *
	 */
	public Set<SchoolWeek> findSchoolWeekByWeek(Integer week) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolWeekByWeek
	 *
	 */
	public Set<SchoolWeek> findSchoolWeekByWeek(Integer week, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolWeekByCreatedAt
	 *
	 */
	public Set<SchoolWeek> findSchoolWeekByCreatedAt(java.util.Calendar createdAt) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolWeekByCreatedAt
	 *
	 */
	public Set<SchoolWeek> findSchoolWeekByCreatedAt(Calendar createdAt, int startResult, int maxRows) throws DataAccessException;

}