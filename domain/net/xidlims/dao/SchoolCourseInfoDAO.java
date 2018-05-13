package net.xidlims.dao;

import java.util.Calendar;
import java.util.Set;

import net.xidlims.domain.SchoolCourseInfo;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage SchoolCourseInfo entities.
 * 
 */
public interface SchoolCourseInfoDAO extends JpaDao<SchoolCourseInfo> {

	/**
	 * JPQL Query - findSchoolCourseInfoByUpdatedAt
	 *
	 */
	public Set<SchoolCourseInfo> findSchoolCourseInfoByUpdatedAt(java.util.Calendar updatedAt) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseInfoByUpdatedAt
	 *
	 */
	public Set<SchoolCourseInfo> findSchoolCourseInfoByUpdatedAt(Calendar updatedAt, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseInfoByTotalHours
	 *
	 */
	public Set<SchoolCourseInfo> findSchoolCourseInfoByTotalHours(Integer totalHours) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseInfoByTotalHours
	 *
	 */
	public Set<SchoolCourseInfo> findSchoolCourseInfoByTotalHours(Integer totalHours, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseInfoByPrimaryKey
	 *
	 */
	public SchoolCourseInfo findSchoolCourseInfoByPrimaryKey(String courseNumber) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseInfoByPrimaryKey
	 *
	 */
	public SchoolCourseInfo findSchoolCourseInfoByPrimaryKey(String courseNumber, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseInfoByCourseNameContaining
	 *
	 */
	public Set<SchoolCourseInfo> findSchoolCourseInfoByCourseNameContaining(String courseName) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseInfoByCourseNameContaining
	 *
	 */
	public Set<SchoolCourseInfo> findSchoolCourseInfoByCourseNameContaining(String courseName, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseInfoByCourseNumber
	 *
	 */
	public SchoolCourseInfo findSchoolCourseInfoByCourseNumber(String courseNumber_1) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseInfoByCourseNumber
	 *
	 */
	public SchoolCourseInfo findSchoolCourseInfoByCourseNumber(String courseNumber_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseInfoByUpdatedBy
	 *
	 */
	public Set<SchoolCourseInfo> findSchoolCourseInfoByUpdatedBy(Integer updatedBy) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseInfoByUpdatedBy
	 *
	 */
	public Set<SchoolCourseInfo> findSchoolCourseInfoByUpdatedBy(Integer updatedBy, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseInfoByAcademyNumber
	 *
	 */
	public Set<SchoolCourseInfo> findSchoolCourseInfoByAcademyNumber(String academyNumber) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseInfoByAcademyNumber
	 *
	 */
	public Set<SchoolCourseInfo> findSchoolCourseInfoByAcademyNumber(String academyNumber, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseInfoByCreatedBy
	 *
	 */
	public Set<SchoolCourseInfo> findSchoolCourseInfoByCreatedBy(Integer createdBy) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseInfoByCreatedBy
	 *
	 */
	public Set<SchoolCourseInfo> findSchoolCourseInfoByCreatedBy(Integer createdBy, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseInfoByTheoreticalHours
	 *
	 */
	public Set<SchoolCourseInfo> findSchoolCourseInfoByTheoreticalHours(Integer theoreticalHours) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseInfoByTheoreticalHours
	 *
	 */
	public Set<SchoolCourseInfo> findSchoolCourseInfoByTheoreticalHours(Integer theoreticalHours, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseInfoByCreatedAt
	 *
	 */
	public Set<SchoolCourseInfo> findSchoolCourseInfoByCreatedAt(java.util.Calendar createdAt) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseInfoByCreatedAt
	 *
	 */
	public Set<SchoolCourseInfo> findSchoolCourseInfoByCreatedAt(Calendar createdAt, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseInfoByFlag
	 *
	 */
	public Set<SchoolCourseInfo> findSchoolCourseInfoByFlag(Integer flag) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseInfoByFlag
	 *
	 */
	public Set<SchoolCourseInfo> findSchoolCourseInfoByFlag(Integer flag, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseInfoByCourseName
	 *
	 */
	public Set<SchoolCourseInfo> findSchoolCourseInfoByCourseName(String courseName_1) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseInfoByCourseName
	 *
	 */
	public Set<SchoolCourseInfo> findSchoolCourseInfoByCourseName(String courseName_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseInfoByCourseEnName
	 *
	 */
	public Set<SchoolCourseInfo> findSchoolCourseInfoByCourseEnName(String courseEnName) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseInfoByCourseEnName
	 *
	 */
	public Set<SchoolCourseInfo> findSchoolCourseInfoByCourseEnName(String courseEnName, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllSchoolCourseInfos
	 *
	 */
	public Set<SchoolCourseInfo> findAllSchoolCourseInfos() throws DataAccessException;

	/**
	 * JPQL Query - findAllSchoolCourseInfos
	 *
	 */
	public Set<SchoolCourseInfo> findAllSchoolCourseInfos(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseInfoByCourseEnNameContaining
	 *
	 */
	public Set<SchoolCourseInfo> findSchoolCourseInfoByCourseEnNameContaining(String courseEnName_1) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseInfoByCourseEnNameContaining
	 *
	 */
	public Set<SchoolCourseInfo> findSchoolCourseInfoByCourseEnNameContaining(String courseEnName_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseInfoByCourseNumberContaining
	 *
	 */
	public Set<SchoolCourseInfo> findSchoolCourseInfoByCourseNumberContaining(String courseNumber_2) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseInfoByCourseNumberContaining
	 *
	 */
	public Set<SchoolCourseInfo> findSchoolCourseInfoByCourseNumberContaining(String courseNumber_2, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseInfoByAcademyNumberContaining
	 *
	 */
	public Set<SchoolCourseInfo> findSchoolCourseInfoByAcademyNumberContaining(String academyNumber_1) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseInfoByAcademyNumberContaining
	 *
	 */
	public Set<SchoolCourseInfo> findSchoolCourseInfoByAcademyNumberContaining(String academyNumber_1, int startResult, int maxRows) throws DataAccessException;

}