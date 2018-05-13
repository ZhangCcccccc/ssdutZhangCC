package net.xidlims.dao;

import java.util.Set;

import net.xidlims.domain.SchoolWeekday;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage SchoolWeekday entities.
 * 
 */
public interface SchoolWeekdayDAO extends JpaDao<SchoolWeekday> {

	/**
	 * JPQL Query - findSchoolWeekdayByWeekdayName
	 *
	 */
	public Set<SchoolWeekday> findSchoolWeekdayByWeekdayName(String weekdayName) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolWeekdayByWeekdayName
	 *
	 */
	public Set<SchoolWeekday> findSchoolWeekdayByWeekdayName(String weekdayName, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolWeekdayById
	 *
	 */
	public SchoolWeekday findSchoolWeekdayById(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolWeekdayById
	 *
	 */
	public SchoolWeekday findSchoolWeekdayById(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllSchoolWeekdays
	 *
	 */
	public Set<SchoolWeekday> findAllSchoolWeekdays() throws DataAccessException;

	/**
	 * JPQL Query - findAllSchoolWeekdays
	 *
	 */
	public Set<SchoolWeekday> findAllSchoolWeekdays(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolWeekdayByPrimaryKey
	 *
	 */
	public SchoolWeekday findSchoolWeekdayByPrimaryKey(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolWeekdayByPrimaryKey
	 *
	 */
	public SchoolWeekday findSchoolWeekdayByPrimaryKey(Integer id_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolWeekdayByWeekdayNameContaining
	 *
	 */
	public Set<SchoolWeekday> findSchoolWeekdayByWeekdayNameContaining(String weekdayName_1) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolWeekdayByWeekdayNameContaining
	 *
	 */
	public Set<SchoolWeekday> findSchoolWeekdayByWeekdayNameContaining(String weekdayName_1, int startResult, int maxRows) throws DataAccessException;

}