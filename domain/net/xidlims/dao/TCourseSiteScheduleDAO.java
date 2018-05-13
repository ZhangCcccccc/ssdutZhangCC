package net.xidlims.dao;

import net.xidlims.domain.TCourseSiteSchedule;

import java.util.Calendar;
import java.util.Set;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage TCourseSiteSchedule entities.
 * 
 */
public interface TCourseSiteScheduleDAO extends JpaDao<TCourseSiteSchedule> {

	/**
	 * JPQL Query - findTCourseSiteScheduleByContentContaining
	 *
	 */
	public Set<TCourseSiteSchedule> findTCourseSiteScheduleByContentContaining(String content) throws DataAccessException;

	/**
	 * JPQL Query - findTCourseSiteScheduleByContentContaining
	 *
	 */
	public Set<TCourseSiteSchedule> findTCourseSiteScheduleByContentContaining(String content, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllTCourseSiteSchedules
	 *
	 */
	public Set<TCourseSiteSchedule> findAllTCourseSiteSchedules() throws DataAccessException;

	/**
	 * JPQL Query - findAllTCourseSiteSchedules
	 *
	 */
	public Set<TCourseSiteSchedule> findAllTCourseSiteSchedules(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTCourseSiteScheduleByPlace
	 *
	 */
	public Set<TCourseSiteSchedule> findTCourseSiteScheduleByPlace(String place) throws DataAccessException;

	/**
	 * JPQL Query - findTCourseSiteScheduleByPlace
	 *
	 */
	public Set<TCourseSiteSchedule> findTCourseSiteScheduleByPlace(String place, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTCourseSiteScheduleById
	 *
	 */
	public TCourseSiteSchedule findTCourseSiteScheduleById(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findTCourseSiteScheduleById
	 *
	 */
	public TCourseSiteSchedule findTCourseSiteScheduleById(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTCourseSiteScheduleByPrimaryKey
	 *
	 */
	public TCourseSiteSchedule findTCourseSiteScheduleByPrimaryKey(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findTCourseSiteScheduleByPrimaryKey
	 *
	 */
	public TCourseSiteSchedule findTCourseSiteScheduleByPrimaryKey(Integer id_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTCourseSiteScheduleByPlaceContaining
	 *
	 */
	public Set<TCourseSiteSchedule> findTCourseSiteScheduleByPlaceContaining(String place_1) throws DataAccessException;

	/**
	 * JPQL Query - findTCourseSiteScheduleByPlaceContaining
	 *
	 */
	public Set<TCourseSiteSchedule> findTCourseSiteScheduleByPlaceContaining(String place_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTCourseSiteScheduleByCreateTime
	 *
	 */
	public Set<TCourseSiteSchedule> findTCourseSiteScheduleByCreateTime(java.util.Calendar createTime) throws DataAccessException;

	/**
	 * JPQL Query - findTCourseSiteScheduleByCreateTime
	 *
	 */
	public Set<TCourseSiteSchedule> findTCourseSiteScheduleByCreateTime(Calendar createTime, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTCourseSiteScheduleByContent
	 *
	 */
	public Set<TCourseSiteSchedule> findTCourseSiteScheduleByContent(String content_1) throws DataAccessException;

	/**
	 * JPQL Query - findTCourseSiteScheduleByContent
	 *
	 */
	public Set<TCourseSiteSchedule> findTCourseSiteScheduleByContent(String content_1, int startResult, int maxRows) throws DataAccessException;

}