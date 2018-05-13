package net.xidlims.dao;

import java.util.Set;

import net.xidlims.domain.SchoolCourseMerge;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage SchoolCourseMerge entities.
 * 
 */
public interface SchoolCourseMergeDAO extends JpaDao<SchoolCourseMerge> {

	/**
	 * JPQL Query - findSchoolCourseMergeByCourseNameContaining
	 *
	 */
	public Set<SchoolCourseMerge> findSchoolCourseMergeByCourseNameContaining(String courseName) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseMergeByCourseNameContaining
	 *
	 */
	public Set<SchoolCourseMerge> findSchoolCourseMergeByCourseNameContaining(String courseName, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseMergeByCourseDetailNo
	 *
	 */
	public Set<SchoolCourseMerge> findSchoolCourseMergeByCourseDetailNo(String courseDetailNo) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseMergeByCourseDetailNo
	 *
	 */
	public Set<SchoolCourseMerge> findSchoolCourseMergeByCourseDetailNo(String courseDetailNo, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseMergeByCourseDetailNoContaining
	 *
	 */
	public Set<SchoolCourseMerge> findSchoolCourseMergeByCourseDetailNoContaining(String courseDetailNo_1) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseMergeByCourseDetailNoContaining
	 *
	 */
	public Set<SchoolCourseMerge> findSchoolCourseMergeByCourseDetailNoContaining(String courseDetailNo_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseMergeByCourseNo
	 *
	 */
	public Set<SchoolCourseMerge> findSchoolCourseMergeByCourseNo(String courseNo) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseMergeByCourseNo
	 *
	 */
	public Set<SchoolCourseMerge> findSchoolCourseMergeByCourseNo(String courseNo, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseMergeByCourseName
	 *
	 */
	public Set<SchoolCourseMerge> findSchoolCourseMergeByCourseName(String courseName_1) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseMergeByCourseName
	 *
	 */
	public Set<SchoolCourseMerge> findSchoolCourseMergeByCourseName(String courseName_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseMergeByCourseNumber
	 *
	 */
	public Set<SchoolCourseMerge> findSchoolCourseMergeByCourseNumber(String courseNumber) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseMergeByCourseNumber
	 *
	 */
	public Set<SchoolCourseMerge> findSchoolCourseMergeByCourseNumber(String courseNumber, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseMergeByStudentNumbersContaining
	 *
	 */
	public Set<SchoolCourseMerge> findSchoolCourseMergeByStudentNumbersContaining(String studentNumbers) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseMergeByStudentNumbersContaining
	 *
	 */
	public Set<SchoolCourseMerge> findSchoolCourseMergeByStudentNumbersContaining(String studentNumbers, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseMergeByPrimaryKey
	 *
	 */
	public SchoolCourseMerge findSchoolCourseMergeByPrimaryKey(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseMergeByPrimaryKey
	 *
	 */
	public SchoolCourseMerge findSchoolCourseMergeByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseMergeByStudentNumbers
	 *
	 */
	public Set<SchoolCourseMerge> findSchoolCourseMergeByStudentNumbers(String studentNumbers_1) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseMergeByStudentNumbers
	 *
	 */
	public Set<SchoolCourseMerge> findSchoolCourseMergeByStudentNumbers(String studentNumbers_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseMergeByCourseNoContaining
	 *
	 */
	public Set<SchoolCourseMerge> findSchoolCourseMergeByCourseNoContaining(String courseNo_1) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseMergeByCourseNoContaining
	 *
	 */
	public Set<SchoolCourseMerge> findSchoolCourseMergeByCourseNoContaining(String courseNo_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseMergeById
	 *
	 */
	public SchoolCourseMerge findSchoolCourseMergeById(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseMergeById
	 *
	 */
	public SchoolCourseMerge findSchoolCourseMergeById(Integer id_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllSchoolCourseMerges
	 *
	 */
	public Set<SchoolCourseMerge> findAllSchoolCourseMerges() throws DataAccessException;

	/**
	 * JPQL Query - findAllSchoolCourseMerges
	 *
	 */
	public Set<SchoolCourseMerge> findAllSchoolCourseMerges(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseMergeByCourseNumberContaining
	 *
	 */
	public Set<SchoolCourseMerge> findSchoolCourseMergeByCourseNumberContaining(String courseNumber_1) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseMergeByCourseNumberContaining
	 *
	 */
	public Set<SchoolCourseMerge> findSchoolCourseMergeByCourseNumberContaining(String courseNumber_1, int startResult, int maxRows) throws DataAccessException;

}