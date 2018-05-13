package net.xidlims.dao;

import java.util.Set;

import net.xidlims.domain.TimetableCourseStudent;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage TimetableCourseStudent entities.
 * 
 */
public interface TimetableCourseStudentDAO extends
		JpaDao<TimetableCourseStudent> {

	/**
	 * JPQL Query - findTimetableCourseStudentByPrimaryKey
	 *
	 */
	public TimetableCourseStudent findTimetableCourseStudentByPrimaryKey(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableCourseStudentByPrimaryKey
	 *
	 */
	public TimetableCourseStudent findTimetableCourseStudentByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllTimetableCourseStudents
	 *
	 */
	public Set<TimetableCourseStudent> findAllTimetableCourseStudents() throws DataAccessException;

	/**
	 * JPQL Query - findAllTimetableCourseStudents
	 *
	 */
	public Set<TimetableCourseStudent> findAllTimetableCourseStudents(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableCourseStudentById
	 *
	 */
	public TimetableCourseStudent findTimetableCourseStudentById(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableCourseStudentById
	 *
	 */
	public TimetableCourseStudent findTimetableCourseStudentById(Integer id_1, int startResult, int maxRows) throws DataAccessException;

}