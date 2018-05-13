package net.xidlims.dao;

import java.util.Calendar;
import java.util.Set;

import net.xidlims.domain.SchoolCourse;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage SchoolCourse entities.
 * 
 */
public interface SchoolCourseDAO extends JpaDao<SchoolCourse> {

	/**
	 * JPQL Query - findSchoolCourseByCourseAddressTypeContaining
	 *
	 */
	public Set<SchoolCourse> findSchoolCourseByCourseAddressTypeContaining(String courseAddressType) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseByCourseAddressTypeContaining
	 *
	 */
	public Set<SchoolCourse> findSchoolCourseByCourseAddressTypeContaining(String courseAddressType, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseByPlanLabTime
	 *
	 */
	public Set<SchoolCourse> findSchoolCourseByPlanLabTime(String planLabTime) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseByPlanLabTime
	 *
	 */
	public Set<SchoolCourse> findSchoolCourseByPlanLabTime(String planLabTime, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseByCourseCodeContaining
	 *
	 */
	public Set<SchoolCourse> findSchoolCourseByCourseCodeContaining(String courseCode) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseByCourseCodeContaining
	 *
	 */
	public Set<SchoolCourse> findSchoolCourseByCourseCodeContaining(String courseCode, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseByCourseNameContaining
	 *
	 */
	public Set<SchoolCourse> findSchoolCourseByCourseNameContaining(String courseName) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseByCourseNameContaining
	 *
	 */
	public Set<SchoolCourse> findSchoolCourseByCourseNameContaining(String courseName, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseByState
	 *
	 */
	public Set<SchoolCourse> findSchoolCourseByState(Integer state) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseByState
	 *
	 */
	public Set<SchoolCourse> findSchoolCourseByState(Integer state, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseByClassesNameContaining
	 *
	 */
	public Set<SchoolCourse> findSchoolCourseByClassesNameContaining(String classesName) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseByClassesNameContaining
	 *
	 */
	public Set<SchoolCourse> findSchoolCourseByClassesNameContaining(String classesName, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseByCreditsContaining
	 *
	 */
	public Set<SchoolCourse> findSchoolCourseByCreditsContaining(String credits) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseByCreditsContaining
	 *
	 */
	public Set<SchoolCourse> findSchoolCourseByCreditsContaining(String credits, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseByPrimaryKey
	 *
	 */
	public SchoolCourse findSchoolCourseByPrimaryKey(String courseNo) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseByPrimaryKey
	 *
	 */
	public SchoolCourse findSchoolCourseByPrimaryKey(String courseNo, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseByCourseNo
	 *
	 */
	public SchoolCourse findSchoolCourseByCourseNo(String courseNo_1) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseByCourseNo
	 *
	 */
	public SchoolCourse findSchoolCourseByCourseNo(String courseNo_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseByCredits
	 *
	 */
	public Set<SchoolCourse> findSchoolCourseByCredits(String credits_1) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseByCredits
	 *
	 */
	public Set<SchoolCourse> findSchoolCourseByCredits(String credits_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseByCourseNoContaining
	 *
	 */
	public Set<SchoolCourse> findSchoolCourseByCourseNoContaining(String courseNo_2) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseByCourseNoContaining
	 *
	 */
	public Set<SchoolCourse> findSchoolCourseByCourseNoContaining(String courseNo_2, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseByCourseName
	 *
	 */
	public Set<SchoolCourse> findSchoolCourseByCourseName(String courseName_1) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseByCourseName
	 *
	 */
	public Set<SchoolCourse> findSchoolCourseByCourseName(String courseName_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseByCreatedDate
	 *
	 */
	public Set<SchoolCourse> findSchoolCourseByCreatedDate(java.util.Calendar createdDate) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseByCreatedDate
	 *
	 */
	public Set<SchoolCourse> findSchoolCourseByCreatedDate(Calendar createdDate, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseByMemoContaining
	 *
	 */
	public Set<SchoolCourse> findSchoolCourseByMemoContaining(String memo) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseByMemoContaining
	 *
	 */
	public Set<SchoolCourse> findSchoolCourseByMemoContaining(String memo, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseByMemo
	 *
	 */
	public Set<SchoolCourse> findSchoolCourseByMemo(String memo_1) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseByMemo
	 *
	 */
	public Set<SchoolCourse> findSchoolCourseByMemo(String memo_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseByCourseTypeContaining
	 *
	 */
	public Set<SchoolCourse> findSchoolCourseByCourseTypeContaining(String courseType) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseByCourseTypeContaining
	 *
	 */
	public Set<SchoolCourse> findSchoolCourseByCourseTypeContaining(String courseType, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseByPlanStudentNumber
	 *
	 */
	public Set<SchoolCourse> findSchoolCourseByPlanStudentNumber(String planStudentNumber) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseByPlanStudentNumber
	 *
	 */
	public Set<SchoolCourse> findSchoolCourseByPlanStudentNumber(String planStudentNumber, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseByPlanLabTimeContaining
	 *
	 */
	public Set<SchoolCourse> findSchoolCourseByPlanLabTimeContaining(String planLabTime_1) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseByPlanLabTimeContaining
	 *
	 */
	public Set<SchoolCourse> findSchoolCourseByPlanLabTimeContaining(String planLabTime_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseByCourseType
	 *
	 */
	public Set<SchoolCourse> findSchoolCourseByCourseType(String courseType_1) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseByCourseType
	 *
	 */
	public Set<SchoolCourse> findSchoolCourseByCourseType(String courseType_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseByClassesName
	 *
	 */
	public Set<SchoolCourse> findSchoolCourseByClassesName(String classesName_1) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseByClassesName
	 *
	 */
	public Set<SchoolCourse> findSchoolCourseByClassesName(String classesName_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseByCourseAddressType
	 *
	 */
	public Set<SchoolCourse> findSchoolCourseByCourseAddressType(String courseAddressType_1) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseByCourseAddressType
	 *
	 */
	public Set<SchoolCourse> findSchoolCourseByCourseAddressType(String courseAddressType_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseByCourseCode
	 *
	 */
	public Set<SchoolCourse> findSchoolCourseByCourseCode(String courseCode_1) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseByCourseCode
	 *
	 */
	public Set<SchoolCourse> findSchoolCourseByCourseCode(String courseCode_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllSchoolCourses
	 *
	 */
	public Set<SchoolCourse> findAllSchoolCourses() throws DataAccessException;

	/**
	 * JPQL Query - findAllSchoolCourses
	 *
	 */
	public Set<SchoolCourse> findAllSchoolCourses(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseByPlanStudentNumberContaining
	 *
	 */
	public Set<SchoolCourse> findSchoolCourseByPlanStudentNumberContaining(String planStudentNumber_1) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseByPlanStudentNumberContaining
	 *
	 */
	public Set<SchoolCourse> findSchoolCourseByPlanStudentNumberContaining(String planStudentNumber_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseByUpdatedDate
	 *
	 */
	public Set<SchoolCourse> findSchoolCourseByUpdatedDate(java.util.Calendar updatedDate) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseByUpdatedDate
	 *
	 */
	public Set<SchoolCourse> findSchoolCourseByUpdatedDate(Calendar updatedDate, int startResult, int maxRows) throws DataAccessException;

}