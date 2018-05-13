package net.xidlims.dao;

import java.util.Calendar;
import java.util.Set;

import net.xidlims.domain.SchoolMajor;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage SchoolMajor entities.
 * 
 */
public interface SchoolMajorDAO extends JpaDao<SchoolMajor> {

	/**
	 * JPQL Query - findSchoolMajorByUpdatedAt
	 *
	 */
	public Set<SchoolMajor> findSchoolMajorByUpdatedAt(java.util.Calendar updatedAt) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolMajorByUpdatedAt
	 *
	 */
	public Set<SchoolMajor> findSchoolMajorByUpdatedAt(Calendar updatedAt, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolMajorById
	 *
	 */
	public Set<SchoolMajor> findSchoolMajorById(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolMajorById
	 *
	 */
	public Set<SchoolMajor> findSchoolMajorById(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolMajorByMajorName
	 *
	 */
	public Set<SchoolMajor> findSchoolMajorByMajorName(String majorName) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolMajorByMajorName
	 *
	 */
	public Set<SchoolMajor> findSchoolMajorByMajorName(String majorName, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolMajorByPrimaryKey
	 *
	 */
	public SchoolMajor findSchoolMajorByPrimaryKey(String majorNumber) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolMajorByPrimaryKey
	 *
	 */
	public SchoolMajor findSchoolMajorByPrimaryKey(String majorNumber, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolMajorByStudentTypeContaining
	 *
	 */
	public Set<SchoolMajor> findSchoolMajorByStudentTypeContaining(String studentType) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolMajorByStudentTypeContaining
	 *
	 */
	public Set<SchoolMajor> findSchoolMajorByStudentTypeContaining(String studentType, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolMajorByCreatedBy
	 *
	 */
	public Set<SchoolMajor> findSchoolMajorByCreatedBy(String createdBy) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolMajorByCreatedBy
	 *
	 */
	public Set<SchoolMajor> findSchoolMajorByCreatedBy(String createdBy, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolMajorByCreatedByContaining
	 *
	 */
	public Set<SchoolMajor> findSchoolMajorByCreatedByContaining(String createdBy_1) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolMajorByCreatedByContaining
	 *
	 */
	public Set<SchoolMajor> findSchoolMajorByCreatedByContaining(String createdBy_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolMajorByCreatedAt
	 *
	 */
	public Set<SchoolMajor> findSchoolMajorByCreatedAt(java.util.Calendar createdAt) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolMajorByCreatedAt
	 *
	 */
	public Set<SchoolMajor> findSchoolMajorByCreatedAt(Calendar createdAt, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolMajorByMajorNumberContaining
	 *
	 */
	public Set<SchoolMajor> findSchoolMajorByMajorNumberContaining(String majorNumber_1) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolMajorByMajorNumberContaining
	 *
	 */
	public Set<SchoolMajor> findSchoolMajorByMajorNumberContaining(String majorNumber_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolMajorByUpdatedBy
	 *
	 */
	public Set<SchoolMajor> findSchoolMajorByUpdatedBy(String updatedBy) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolMajorByUpdatedBy
	 *
	 */
	public Set<SchoolMajor> findSchoolMajorByUpdatedBy(String updatedBy, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolMajorByStudentType
	 *
	 */
	public Set<SchoolMajor> findSchoolMajorByStudentType(String studentType_1) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolMajorByStudentType
	 *
	 */
	public Set<SchoolMajor> findSchoolMajorByStudentType(String studentType_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllSchoolMajors
	 *
	 */
	public Set<SchoolMajor> findAllSchoolMajors() throws DataAccessException;

	/**
	 * JPQL Query - findAllSchoolMajors
	 *
	 */
	public Set<SchoolMajor> findAllSchoolMajors(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolMajorByMajorNumber
	 *
	 */
	public SchoolMajor findSchoolMajorByMajorNumber(String majorNumber_2) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolMajorByMajorNumber
	 *
	 */
	public SchoolMajor findSchoolMajorByMajorNumber(String majorNumber_2, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolMajorByMajorNameContaining
	 *
	 */
	public Set<SchoolMajor> findSchoolMajorByMajorNameContaining(String majorName_1) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolMajorByMajorNameContaining
	 *
	 */
	public Set<SchoolMajor> findSchoolMajorByMajorNameContaining(String majorName_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolMajorByUpdatedByContaining
	 *
	 */
	public Set<SchoolMajor> findSchoolMajorByUpdatedByContaining(String updatedBy_1) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolMajorByUpdatedByContaining
	 *
	 */
	public Set<SchoolMajor> findSchoolMajorByUpdatedByContaining(String updatedBy_1, int startResult, int maxRows) throws DataAccessException;

}