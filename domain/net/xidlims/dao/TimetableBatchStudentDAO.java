package net.xidlims.dao;

import java.util.Set;

import net.xidlims.domain.TimetableBatchStudent;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage TimetableBatchStudent entities.
 * 
 */
public interface TimetableBatchStudentDAO extends JpaDao<TimetableBatchStudent> {

	/**
	 * JPQL Query - findTimetableBatchStudentByWithdrawTimes
	 *
	 */
	public Set<TimetableBatchStudent> findTimetableBatchStudentByWithdrawTimes(Integer withdrawTimes) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableBatchStudentByWithdrawTimes
	 *
	 */
	public Set<TimetableBatchStudent> findTimetableBatchStudentByWithdrawTimes(Integer withdrawTimes, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllTimetableBatchStudents
	 *
	 */
	public Set<TimetableBatchStudent> findAllTimetableBatchStudents() throws DataAccessException;

	/**
	 * JPQL Query - findAllTimetableBatchStudents
	 *
	 */
	public Set<TimetableBatchStudent> findAllTimetableBatchStudents(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableBatchStudentByPrimaryKey
	 *
	 */
	public TimetableBatchStudent findTimetableBatchStudentByPrimaryKey(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableBatchStudentByPrimaryKey
	 *
	 */
	public TimetableBatchStudent findTimetableBatchStudentByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableBatchStudentById
	 *
	 */
	public TimetableBatchStudent findTimetableBatchStudentById(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableBatchStudentById
	 *
	 */
	public TimetableBatchStudent findTimetableBatchStudentById(Integer id_1, int startResult, int maxRows) throws DataAccessException;

}