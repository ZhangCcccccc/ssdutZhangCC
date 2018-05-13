package net.xidlims.dao;

import java.util.Set;

import net.xidlims.domain.TimetableSelfCourse;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage TimetableSelfCourse entities.
 * 
 */
public interface TimetableSelfCourseDAO extends JpaDao<TimetableSelfCourse> {

	/**
	 * JPQL Query - findTimetableSelfCourseById
	 *
	 */
	public TimetableSelfCourse findTimetableSelfCourseById(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableSelfCourseById
	 *
	 */
	public TimetableSelfCourse findTimetableSelfCourseById(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableSelfCourseByCourseCodeContaining
	 *
	 */
	public Set<TimetableSelfCourse> findTimetableSelfCourseByCourseCodeContaining(String courseCode) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableSelfCourseByCourseCodeContaining
	 *
	 */
	public Set<TimetableSelfCourse> findTimetableSelfCourseByCourseCodeContaining(String courseCode, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableSelfCourseByPrimaryKey
	 *
	 */
	public TimetableSelfCourse findTimetableSelfCourseByPrimaryKey(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableSelfCourseByPrimaryKey
	 *
	 */
	public TimetableSelfCourse findTimetableSelfCourseByPrimaryKey(Integer id_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllTimetableSelfCourses
	 *
	 */
	public Set<TimetableSelfCourse> findAllTimetableSelfCourses() throws DataAccessException;

	/**
	 * JPQL Query - findAllTimetableSelfCourses
	 *
	 */
	public Set<TimetableSelfCourse> findAllTimetableSelfCourses(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableSelfCourseByName
	 *
	 */
	public Set<TimetableSelfCourse> findTimetableSelfCourseByName(String name) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableSelfCourseByName
	 *
	 */
	public Set<TimetableSelfCourse> findTimetableSelfCourseByName(String name, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableSelfCourseByCourseCode
	 *
	 */
	public Set<TimetableSelfCourse> findTimetableSelfCourseByCourseCode(String courseCode_1) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableSelfCourseByCourseCode
	 *
	 */
	public Set<TimetableSelfCourse> findTimetableSelfCourseByCourseCode(String courseCode_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableSelfCourseByNameContaining
	 *
	 */
	public Set<TimetableSelfCourse> findTimetableSelfCourseByNameContaining(String name_1) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableSelfCourseByNameContaining
	 *
	 */
	public Set<TimetableSelfCourse> findTimetableSelfCourseByNameContaining(String name_1, int startResult, int maxRows) throws DataAccessException;

}