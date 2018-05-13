package net.xidlims.dao;

import java.util.Calendar;
import java.util.Set;

import net.xidlims.domain.SystemTime;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage SystemTime entities.
 * 
 */
public interface SystemTimeDAO extends JpaDao<SystemTime> {

	/**
	 * JPQL Query - findSystemTimeBySequenceContaining
	 *
	 */
	public Set<SystemTime> findSystemTimeBySequenceContaining(String sequence) throws DataAccessException;

	/**
	 * JPQL Query - findSystemTimeBySequenceContaining
	 *
	 */
	public Set<SystemTime> findSystemTimeBySequenceContaining(String sequence, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSystemTimeByTimeNameContaining
	 *
	 */
	public Set<SystemTime> findSystemTimeByTimeNameContaining(String timeName) throws DataAccessException;

	/**
	 * JPQL Query - findSystemTimeByTimeNameContaining
	 *
	 */
	public Set<SystemTime> findSystemTimeByTimeNameContaining(String timeName, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSystemTimeByCreatedByContaining
	 *
	 */
	public Set<SystemTime> findSystemTimeByCreatedByContaining(String createdBy) throws DataAccessException;

	/**
	 * JPQL Query - findSystemTimeByCreatedByContaining
	 *
	 */
	public Set<SystemTime> findSystemTimeByCreatedByContaining(String createdBy, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSystemTimeByCombineContaining
	 *
	 */
	public Set<SystemTime> findSystemTimeByCombineContaining(String combine) throws DataAccessException;

	/**
	 * JPQL Query - findSystemTimeByCombineContaining
	 *
	 */
	public Set<SystemTime> findSystemTimeByCombineContaining(String combine, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSystemTimeByUpdatedByContaining
	 *
	 */
	public Set<SystemTime> findSystemTimeByUpdatedByContaining(String updatedBy) throws DataAccessException;

	/**
	 * JPQL Query - findSystemTimeByUpdatedByContaining
	 *
	 */
	public Set<SystemTime> findSystemTimeByUpdatedByContaining(String updatedBy, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSystemTimeByStartDate
	 *
	 */
	public Set<SystemTime> findSystemTimeByStartDate(java.util.Calendar startDate) throws DataAccessException;

	/**
	 * JPQL Query - findSystemTimeByStartDate
	 *
	 */
	public Set<SystemTime> findSystemTimeByStartDate(Calendar startDate, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSystemTimeBySequence
	 *
	 */
	public Set<SystemTime> findSystemTimeBySequence(String sequence_1) throws DataAccessException;

	/**
	 * JPQL Query - findSystemTimeBySequence
	 *
	 */
	public Set<SystemTime> findSystemTimeBySequence(String sequence_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSystemTimeByUpdatedBy
	 *
	 */
	public Set<SystemTime> findSystemTimeByUpdatedBy(String updatedBy_1) throws DataAccessException;

	/**
	 * JPQL Query - findSystemTimeByUpdatedBy
	 *
	 */
	public Set<SystemTime> findSystemTimeByUpdatedBy(String updatedBy_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSystemTimeByPrimaryKey
	 *
	 */
	public SystemTime findSystemTimeByPrimaryKey(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findSystemTimeByPrimaryKey
	 *
	 */
	public SystemTime findSystemTimeByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSystemTimeById
	 *
	 */
	public SystemTime findSystemTimeById(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findSystemTimeById
	 *
	 */
	public SystemTime findSystemTimeById(Integer id_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSystemTimeByCombine
	 *
	 */
	public Set<SystemTime> findSystemTimeByCombine(String combine_1) throws DataAccessException;

	/**
	 * JPQL Query - findSystemTimeByCombine
	 *
	 */
	public Set<SystemTime> findSystemTimeByCombine(String combine_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSystemTimeByCreatedBy
	 *
	 */
	public Set<SystemTime> findSystemTimeByCreatedBy(String createdBy_1) throws DataAccessException;

	/**
	 * JPQL Query - findSystemTimeByCreatedBy
	 *
	 */
	public Set<SystemTime> findSystemTimeByCreatedBy(String createdBy_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSystemTimeByCreatedDate
	 *
	 */
	public Set<SystemTime> findSystemTimeByCreatedDate(java.util.Calendar createdDate) throws DataAccessException;

	/**
	 * JPQL Query - findSystemTimeByCreatedDate
	 *
	 */
	public Set<SystemTime> findSystemTimeByCreatedDate(Calendar createdDate, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllSystemTimes
	 *
	 */
	public Set<SystemTime> findAllSystemTimes() throws DataAccessException;

	/**
	 * JPQL Query - findAllSystemTimes
	 *
	 */
	public Set<SystemTime> findAllSystemTimes(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSystemTimeByUpdatedDate
	 *
	 */
	public Set<SystemTime> findSystemTimeByUpdatedDate(java.util.Calendar updatedDate) throws DataAccessException;

	/**
	 * JPQL Query - findSystemTimeByUpdatedDate
	 *
	 */
	public Set<SystemTime> findSystemTimeByUpdatedDate(Calendar updatedDate, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSystemTimeByEndDate
	 *
	 */
	public Set<SystemTime> findSystemTimeByEndDate(java.util.Calendar endDate) throws DataAccessException;

	/**
	 * JPQL Query - findSystemTimeByEndDate
	 *
	 */
	public Set<SystemTime> findSystemTimeByEndDate(Calendar endDate, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSystemTimeByTimeName
	 *
	 */
	public Set<SystemTime> findSystemTimeByTimeName(String timeName_1) throws DataAccessException;

	/**
	 * JPQL Query - findSystemTimeByTimeName
	 *
	 */
	public Set<SystemTime> findSystemTimeByTimeName(String timeName_1, int startResult, int maxRows) throws DataAccessException;

}