package net.xidlims.dao;

import java.util.Set;

import net.xidlims.domain.LabRoomLimitTime;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage LabRoomLimitTime entities.
 * 
 */
public interface LabRoomLimitTimeDAO extends JpaDao<LabRoomLimitTime> {

	/**
	 * JPQL Query - findLabRoomLimitTimeByTerm
	 *
	 */
	public Set<LabRoomLimitTime> findLabRoomLimitTimeByTerm(Integer term) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomLimitTimeByTerm
	 *
	 */
	public Set<LabRoomLimitTime> findLabRoomLimitTimeByTerm(Integer term, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomLimitTimeByPrimaryKey
	 *
	 */
	public LabRoomLimitTime findLabRoomLimitTimeByPrimaryKey(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomLimitTimeByPrimaryKey
	 *
	 */
	public LabRoomLimitTime findLabRoomLimitTimeByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomLimitTimeByEndweek
	 *
	 */
	public Set<LabRoomLimitTime> findLabRoomLimitTimeByEndweek(Integer endweek) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomLimitTimeByEndweek
	 *
	 */
	public Set<LabRoomLimitTime> findLabRoomLimitTimeByEndweek(Integer endweek, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllLabRoomLimitTimes
	 *
	 */
	public Set<LabRoomLimitTime> findAllLabRoomLimitTimes() throws DataAccessException;

	/**
	 * JPQL Query - findAllLabRoomLimitTimes
	 *
	 */
	public Set<LabRoomLimitTime> findAllLabRoomLimitTimes(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomLimitTimeByStartweek
	 *
	 */
	public Set<LabRoomLimitTime> findLabRoomLimitTimeByStartweek(Integer startweek) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomLimitTimeByStartweek
	 *
	 */
	public Set<LabRoomLimitTime> findLabRoomLimitTimeByStartweek(Integer startweek, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomLimitTimeByLabId
	 *
	 */
	public Set<LabRoomLimitTime> findLabRoomLimitTimeByLabId(Integer labId) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomLimitTimeByLabId
	 *
	 */
	public Set<LabRoomLimitTime> findLabRoomLimitTimeByLabId(Integer labId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomLimitTimeByInfoContaining
	 *
	 */
	public Set<LabRoomLimitTime> findLabRoomLimitTimeByInfoContaining(String info) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomLimitTimeByInfoContaining
	 *
	 */
	public Set<LabRoomLimitTime> findLabRoomLimitTimeByInfoContaining(String info, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomLimitTimeByInfo
	 *
	 */
	public Set<LabRoomLimitTime> findLabRoomLimitTimeByInfo(String info_1) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomLimitTimeByInfo
	 *
	 */
	public Set<LabRoomLimitTime> findLabRoomLimitTimeByInfo(String info_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomLimitTimeByWeekday
	 *
	 */
	public Set<LabRoomLimitTime> findLabRoomLimitTimeByWeekday(Integer weekday) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomLimitTimeByWeekday
	 *
	 */
	public Set<LabRoomLimitTime> findLabRoomLimitTimeByWeekday(Integer weekday, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomLimitTimeById
	 *
	 */
	public LabRoomLimitTime findLabRoomLimitTimeById(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomLimitTimeById
	 *
	 */
	public LabRoomLimitTime findLabRoomLimitTimeById(Integer id_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomLimitTimeByStartclass
	 *
	 */
	public Set<LabRoomLimitTime> findLabRoomLimitTimeByStartclass(Integer startclass) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomLimitTimeByStartclass
	 *
	 */
	public Set<LabRoomLimitTime> findLabRoomLimitTimeByStartclass(Integer startclass, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomLimitTimeByEndclass
	 *
	 */
	public Set<LabRoomLimitTime> findLabRoomLimitTimeByEndclass(Integer endclass) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomLimitTimeByEndclass
	 *
	 */
	public Set<LabRoomLimitTime> findLabRoomLimitTimeByEndclass(Integer endclass, int startResult, int maxRows) throws DataAccessException;

}