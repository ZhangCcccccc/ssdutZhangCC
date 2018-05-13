package net.xidlims.dao;

import java.util.Set;

import net.xidlims.domain.TimetableBatch;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage TimetableBatch entities.
 * 
 */
public interface TimetableBatchDAO extends JpaDao<TimetableBatch> {

	/**
	 * JPQL Query - findAllTimetableBatchs
	 *
	 */
	public Set<TimetableBatch> findAllTimetableBatchs() throws DataAccessException;

	/**
	 * JPQL Query - findAllTimetableBatchs
	 *
	 */
	public Set<TimetableBatch> findAllTimetableBatchs(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableBatchByPrimaryKey
	 *
	 */
	public TimetableBatch findTimetableBatchByPrimaryKey(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableBatchByPrimaryKey
	 *
	 */
	public TimetableBatch findTimetableBatchByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableBatchByBatchNameContaining
	 *
	 */
	public Set<TimetableBatch> findTimetableBatchByBatchNameContaining(String batchName) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableBatchByBatchNameContaining
	 *
	 */
	public Set<TimetableBatch> findTimetableBatchByBatchNameContaining(String batchName, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableBatchById
	 *
	 */
	public TimetableBatch findTimetableBatchById(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableBatchById
	 *
	 */
	public TimetableBatch findTimetableBatchById(Integer id_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableBatchByCourseCodeContaining
	 *
	 */
	public Set<TimetableBatch> findTimetableBatchByCourseCodeContaining(String courseCode) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableBatchByCourseCodeContaining
	 *
	 */
	public Set<TimetableBatch> findTimetableBatchByCourseCodeContaining(String courseCode, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableBatchByIfselect
	 *
	 */
	public Set<TimetableBatch> findTimetableBatchByIfselect(Integer ifselect) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableBatchByIfselect
	 *
	 */
	public Set<TimetableBatch> findTimetableBatchByIfselect(Integer ifselect, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableBatchByBatchName
	 *
	 */
	public Set<TimetableBatch> findTimetableBatchByBatchName(String batchName_1) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableBatchByBatchName
	 *
	 */
	public Set<TimetableBatch> findTimetableBatchByBatchName(String batchName_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableBatchByCourseCode
	 *
	 */
	public Set<TimetableBatch> findTimetableBatchByCourseCode(String courseCode_1) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableBatchByCourseCode
	 *
	 */
	public Set<TimetableBatch> findTimetableBatchByCourseCode(String courseCode_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableBatchByCountGroup
	 *
	 */
	public Set<TimetableBatch> findTimetableBatchByCountGroup(Integer countGroup) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableBatchByCountGroup
	 *
	 */
	public Set<TimetableBatch> findTimetableBatchByCountGroup(Integer countGroup, int startResult, int maxRows) throws DataAccessException;

}