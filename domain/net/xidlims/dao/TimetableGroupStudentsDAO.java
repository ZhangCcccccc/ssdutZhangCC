package net.xidlims.dao;

import java.util.Set;

import net.xidlims.domain.TimetableGroupStudents;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage TimetableGroupStudents entities.
 * 
 */
public interface TimetableGroupStudentsDAO extends
		JpaDao<TimetableGroupStudents> {

	/**
	 * JPQL Query - findAllTimetableGroupStudentss
	 *
	 */
	public Set<TimetableGroupStudents> findAllTimetableGroupStudentss() throws DataAccessException;

	/**
	 * JPQL Query - findAllTimetableGroupStudentss
	 *
	 */
	public Set<TimetableGroupStudents> findAllTimetableGroupStudentss(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableGroupStudentsByPrimaryKey
	 *
	 */
	public TimetableGroupStudents findTimetableGroupStudentsByPrimaryKey(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableGroupStudentsByPrimaryKey
	 *
	 */
	public TimetableGroupStudents findTimetableGroupStudentsByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableGroupStudentsById
	 *
	 */
	public TimetableGroupStudents findTimetableGroupStudentsById(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableGroupStudentsById
	 *
	 */
	public TimetableGroupStudents findTimetableGroupStudentsById(Integer id_1, int startResult, int maxRows) throws DataAccessException;

}