package net.xidlims.dao;

import java.util.Calendar;
import java.util.Set;

import net.xidlims.domain.SchoolCourseStudent;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage SchoolCourseStudent entities.
 * 
 */
public interface SchoolCourseStudentDAO extends JpaDao<SchoolCourseStudent> {

	/**
	 * JPQL Query - findSchoolCourseStudentByCreatedDate
	 *
	 */
	public Set<SchoolCourseStudent> findSchoolCourseStudentByCreatedDate(java.util.Calendar createdDate) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseStudentByCreatedDate
	 *
	 */
	public Set<SchoolCourseStudent> findSchoolCourseStudentByCreatedDate(Calendar createdDate, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllSchoolCourseStudents
	 *
	 */
	public Set<SchoolCourseStudent> findAllSchoolCourseStudents() throws DataAccessException;

	/**
	 * JPQL Query - findAllSchoolCourseStudents
	 *
	 */
	public Set<SchoolCourseStudent> findAllSchoolCourseStudents(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseStudentByMemo
	 *
	 */
	public Set<SchoolCourseStudent> findSchoolCourseStudentByMemo(String memo) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseStudentByMemo
	 *
	 */
	public Set<SchoolCourseStudent> findSchoolCourseStudentByMemo(String memo, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseStudentByMajorNameContaining
	 *
	 */
	public Set<SchoolCourseStudent> findSchoolCourseStudentByMajorNameContaining(String majorName) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseStudentByMajorNameContaining
	 *
	 */
	public Set<SchoolCourseStudent> findSchoolCourseStudentByMajorNameContaining(String majorName, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseStudentById
	 *
	 */
	public SchoolCourseStudent findSchoolCourseStudentById(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseStudentById
	 *
	 */
	public SchoolCourseStudent findSchoolCourseStudentById(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseStudentByMajorDirectionName
	 *
	 */
	public Set<SchoolCourseStudent> findSchoolCourseStudentByMajorDirectionName(String majorDirectionName) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseStudentByMajorDirectionName
	 *
	 */
	public Set<SchoolCourseStudent> findSchoolCourseStudentByMajorDirectionName(String majorDirectionName, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseStudentByState
	 *
	 */
	public Set<SchoolCourseStudent> findSchoolCourseStudentByState(Integer state) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseStudentByState
	 *
	 */
	public Set<SchoolCourseStudent> findSchoolCourseStudentByState(Integer state, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseStudentByPrimaryKey
	 *
	 */
	public SchoolCourseStudent findSchoolCourseStudentByPrimaryKey(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseStudentByPrimaryKey
	 *
	 */
	public SchoolCourseStudent findSchoolCourseStudentByPrimaryKey(Integer id_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseStudentByMajorDirectionNameContaining
	 *
	 */
	public Set<SchoolCourseStudent> findSchoolCourseStudentByMajorDirectionNameContaining(String majorDirectionName_1) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseStudentByMajorDirectionNameContaining
	 *
	 */
	public Set<SchoolCourseStudent> findSchoolCourseStudentByMajorDirectionNameContaining(String majorDirectionName_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseStudentByMemoContaining
	 *
	 */
	public Set<SchoolCourseStudent> findSchoolCourseStudentByMemoContaining(String memo_1) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseStudentByMemoContaining
	 *
	 */
	public Set<SchoolCourseStudent> findSchoolCourseStudentByMemoContaining(String memo_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseStudentByMajorName
	 *
	 */
	public Set<SchoolCourseStudent> findSchoolCourseStudentByMajorName(String majorName_1) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseStudentByMajorName
	 *
	 */
	public Set<SchoolCourseStudent> findSchoolCourseStudentByMajorName(String majorName_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseStudentByClassesStudents
	 *
	 */
	public Set<SchoolCourseStudent> findSchoolCourseStudentByClassesStudents(String classesStudents) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseStudentByClassesStudents
	 *
	 */
	public Set<SchoolCourseStudent> findSchoolCourseStudentByClassesStudents(String classesStudents, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseStudentByUpdatedDate
	 *
	 */
	public Set<SchoolCourseStudent> findSchoolCourseStudentByUpdatedDate(java.util.Calendar updatedDate) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseStudentByUpdatedDate
	 *
	 */
	public Set<SchoolCourseStudent> findSchoolCourseStudentByUpdatedDate(Calendar updatedDate, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseStudentByClassesStudentsContaining
	 *
	 */
	public Set<SchoolCourseStudent> findSchoolCourseStudentByClassesStudentsContaining(String classesStudents_1) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseStudentByClassesStudentsContaining
	 *
	 */
	public Set<SchoolCourseStudent> findSchoolCourseStudentByClassesStudentsContaining(String classesStudents_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseStudentByTestNum
	 *
	 */
	public Set<SchoolCourseStudent> findSchoolCourseStudentByTestNum(Integer testNum) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseStudentByTestNum
	 *
	 */
	public Set<SchoolCourseStudent> findSchoolCourseStudentByTestNum(Integer testNum, int startResult, int maxRows) throws DataAccessException;

}