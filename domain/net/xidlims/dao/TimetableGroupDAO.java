package net.xidlims.dao;

import java.util.Calendar;
import java.util.Set;

import net.xidlims.domain.TimetableGroup;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage TimetableGroup entities.
 * 
 */
public interface TimetableGroupDAO extends JpaDao<TimetableGroup> {

	/**
	 * JPQL Query - findTimetableGroupByGroupName
	 *
	 */
	public Set<TimetableGroup> findTimetableGroupByGroupName(String groupName) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableGroupByGroupName
	 *
	 */
	public Set<TimetableGroup> findTimetableGroupByGroupName(String groupName, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableGroupById
	 *
	 */
	public TimetableGroup findTimetableGroupById(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableGroupById
	 *
	 */
	public TimetableGroup findTimetableGroupById(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllTimetableGroups
	 *
	 */
	public Set<TimetableGroup> findAllTimetableGroups() throws DataAccessException;

	/**
	 * JPQL Query - findAllTimetableGroups
	 *
	 */
	public Set<TimetableGroup> findAllTimetableGroups(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableGroupByNumbers
	 *
	 */
	public Set<TimetableGroup> findTimetableGroupByNumbers(Integer numbers) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableGroupByNumbers
	 *
	 */
	public Set<TimetableGroup> findTimetableGroupByNumbers(Integer numbers, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableGroupByIfselect
	 *
	 */
	public Set<TimetableGroup> findTimetableGroupByIfselect(Integer ifselect) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableGroupByIfselect
	 *
	 */
	public Set<TimetableGroup> findTimetableGroupByIfselect(Integer ifselect, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableGroupByStartDate
	 *
	 */
	public Set<TimetableGroup> findTimetableGroupByStartDate(java.util.Calendar startDate) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableGroupByStartDate
	 *
	 */
	public Set<TimetableGroup> findTimetableGroupByStartDate(Calendar startDate, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableGroupByPrimaryKey
	 *
	 */
	public TimetableGroup findTimetableGroupByPrimaryKey(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableGroupByPrimaryKey
	 *
	 */
	public TimetableGroup findTimetableGroupByPrimaryKey(Integer id_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableGroupByGroupNameContaining
	 *
	 */
	public Set<TimetableGroup> findTimetableGroupByGroupNameContaining(String groupName_1) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableGroupByGroupNameContaining
	 *
	 */
	public Set<TimetableGroup> findTimetableGroupByGroupNameContaining(String groupName_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableGroupByEndDate
	 *
	 */
	public Set<TimetableGroup> findTimetableGroupByEndDate(java.util.Calendar endDate) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableGroupByEndDate
	 *
	 */
	public Set<TimetableGroup> findTimetableGroupByEndDate(Calendar endDate, int startResult, int maxRows) throws DataAccessException;

}