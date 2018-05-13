package net.xidlims.dao;

import java.util.Calendar;
import java.util.Set;

import net.xidlims.domain.SchoolClasses;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage SchoolClasses entities.
 * 
 */
public interface SchoolClassesDAO extends JpaDao<SchoolClasses> {

	/**
	 * JPQL Query - findSchoolClassesByAcademyNumberContaining
	 *
	 */
	public Set<SchoolClasses> findSchoolClassesByAcademyNumberContaining(String academyNumber) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolClassesByAcademyNumberContaining
	 *
	 */
	public Set<SchoolClasses> findSchoolClassesByAcademyNumberContaining(String academyNumber, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolClassesByPrimaryKey
	 *
	 */
	public SchoolClasses findSchoolClassesByPrimaryKey(String classNumber) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolClassesByPrimaryKey
	 *
	 */
	public SchoolClasses findSchoolClassesByPrimaryKey(String classNumber, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolClassesByClassPlanStudents
	 *
	 */
	public Set<SchoolClasses> findSchoolClassesByClassPlanStudents(String classPlanStudents) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolClassesByClassPlanStudents
	 *
	 */
	public Set<SchoolClasses> findSchoolClassesByClassPlanStudents(String classPlanStudents, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolClassesByClassGrade
	 *
	 */
	public Set<SchoolClasses> findSchoolClassesByClassGrade(String classGrade) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolClassesByClassGrade
	 *
	 */
	public Set<SchoolClasses> findSchoolClassesByClassGrade(String classGrade, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolClassesByAttendanceTimeContaining
	 *
	 */
	public Set<SchoolClasses> findSchoolClassesByAttendanceTimeContaining(String attendanceTime) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolClassesByAttendanceTimeContaining
	 *
	 */
	public Set<SchoolClasses> findSchoolClassesByAttendanceTimeContaining(String attendanceTime, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolClassesByUpdatedAt
	 *
	 */
	public Set<SchoolClasses> findSchoolClassesByUpdatedAt(java.util.Calendar updatedAt) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolClassesByUpdatedAt
	 *
	 */
	public Set<SchoolClasses> findSchoolClassesByUpdatedAt(Calendar updatedAt, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllSchoolClassess
	 *
	 */
	public Set<SchoolClasses> findAllSchoolClassess() throws DataAccessException;

	/**
	 * JPQL Query - findAllSchoolClassess
	 *
	 */
	public Set<SchoolClasses> findAllSchoolClassess(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolClassesByClassNameContaining
	 *
	 */
	public Set<SchoolClasses> findSchoolClassesByClassNameContaining(String className) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolClassesByClassNameContaining
	 *
	 */
	public Set<SchoolClasses> findSchoolClassesByClassNameContaining(String className, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolClassesByClassNumber
	 *
	 */
	public SchoolClasses findSchoolClassesByClassNumber(String classNumber_1) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolClassesByClassNumber
	 *
	 */
	public SchoolClasses findSchoolClassesByClassNumber(String classNumber_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolClassesByClassNumberContaining
	 *
	 */
	public Set<SchoolClasses> findSchoolClassesByClassNumberContaining(String classNumber_2) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolClassesByClassNumberContaining
	 *
	 */
	public Set<SchoolClasses> findSchoolClassesByClassNumberContaining(String classNumber_2, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolClassesByCreatedAt
	 *
	 */
	public Set<SchoolClasses> findSchoolClassesByCreatedAt(java.util.Calendar createdAt) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolClassesByCreatedAt
	 *
	 */
	public Set<SchoolClasses> findSchoolClassesByCreatedAt(Calendar createdAt, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolClassesByClassStudentsNumber
	 *
	 */
	public Set<SchoolClasses> findSchoolClassesByClassStudentsNumber(String classStudentsNumber) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolClassesByClassStudentsNumber
	 *
	 */
	public Set<SchoolClasses> findSchoolClassesByClassStudentsNumber(String classStudentsNumber, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolClassesByMajorNumber
	 *
	 */
	public Set<SchoolClasses> findSchoolClassesByMajorNumber(String majorNumber) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolClassesByMajorNumber
	 *
	 */
	public Set<SchoolClasses> findSchoolClassesByMajorNumber(String majorNumber, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolClassesByAttendanceTime
	 *
	 */
	public Set<SchoolClasses> findSchoolClassesByAttendanceTime(String attendanceTime_1) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolClassesByAttendanceTime
	 *
	 */
	public Set<SchoolClasses> findSchoolClassesByAttendanceTime(String attendanceTime_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolClassesByClassGradeContaining
	 *
	 */
	public Set<SchoolClasses> findSchoolClassesByClassGradeContaining(String classGrade_1) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolClassesByClassGradeContaining
	 *
	 */
	public Set<SchoolClasses> findSchoolClassesByClassGradeContaining(String classGrade_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolClassesByClassName
	 *
	 */
	public Set<SchoolClasses> findSchoolClassesByClassName(String className_1) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolClassesByClassName
	 *
	 */
	public Set<SchoolClasses> findSchoolClassesByClassName(String className_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolClassesByClassStudentsNumberContaining
	 *
	 */
	public Set<SchoolClasses> findSchoolClassesByClassStudentsNumberContaining(String classStudentsNumber_1) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolClassesByClassStudentsNumberContaining
	 *
	 */
	public Set<SchoolClasses> findSchoolClassesByClassStudentsNumberContaining(String classStudentsNumber_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolClassesById
	 *
	 */
	public Set<SchoolClasses> findSchoolClassesById(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolClassesById
	 *
	 */
	public Set<SchoolClasses> findSchoolClassesById(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolClassesByClassShortName
	 *
	 */
	public Set<SchoolClasses> findSchoolClassesByClassShortName(String classShortName) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolClassesByClassShortName
	 *
	 */
	public Set<SchoolClasses> findSchoolClassesByClassShortName(String classShortName, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolClassesByAcademyNumber
	 *
	 */
	public Set<SchoolClasses> findSchoolClassesByAcademyNumber(String academyNumber_1) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolClassesByAcademyNumber
	 *
	 */
	public Set<SchoolClasses> findSchoolClassesByAcademyNumber(String academyNumber_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolClassesByMajorNumberContaining
	 *
	 */
	public Set<SchoolClasses> findSchoolClassesByMajorNumberContaining(String majorNumber_1) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolClassesByMajorNumberContaining
	 *
	 */
	public Set<SchoolClasses> findSchoolClassesByMajorNumberContaining(String majorNumber_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolClassesByClassShortNameContaining
	 *
	 */
	public Set<SchoolClasses> findSchoolClassesByClassShortNameContaining(String classShortName_1) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolClassesByClassShortNameContaining
	 *
	 */
	public Set<SchoolClasses> findSchoolClassesByClassShortNameContaining(String classShortName_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolClassesByClassPlanStudentsContaining
	 *
	 */
	public Set<SchoolClasses> findSchoolClassesByClassPlanStudentsContaining(String classPlanStudents_1) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolClassesByClassPlanStudentsContaining
	 *
	 */
	public Set<SchoolClasses> findSchoolClassesByClassPlanStudentsContaining(String classPlanStudents_1, int startResult, int maxRows) throws DataAccessException;

}