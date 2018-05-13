package net.xidlims.dao;

import java.util.Calendar;
import java.util.Set;

import net.xidlims.domain.SchoolAcademy;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage SchoolAcademy entities.
 * 
 */
public interface SchoolAcademyDAO extends JpaDao<SchoolAcademy> {

	/**
	 * JPQL Query - findAllSchoolAcademys
	 *
	 */
	public Set<SchoolAcademy> findAllSchoolAcademys() throws DataAccessException;

	/**
	 * JPQL Query - findAllSchoolAcademys
	 *
	 */
	public Set<SchoolAcademy> findAllSchoolAcademys(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolAcademyByAcademyTypeContaining
	 *
	 */
	public Set<SchoolAcademy> findSchoolAcademyByAcademyTypeContaining(String academyType) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolAcademyByAcademyTypeContaining
	 *
	 */
	public Set<SchoolAcademy> findSchoolAcademyByAcademyTypeContaining(String academyType, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolAcademyByPrimaryKey
	 *
	 */
	public SchoolAcademy findSchoolAcademyByPrimaryKey(String academyNumber) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolAcademyByPrimaryKey
	 *
	 */
	public SchoolAcademy findSchoolAcademyByPrimaryKey(String academyNumber, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolAcademyByAcademyNameContaining
	 *
	 */
	public Set<SchoolAcademy> findSchoolAcademyByAcademyNameContaining(String academyName) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolAcademyByAcademyNameContaining
	 *
	 */
	public Set<SchoolAcademy> findSchoolAcademyByAcademyNameContaining(String academyName, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolAcademyByIsVaild
	 *
	 */
	public Set<SchoolAcademy> findSchoolAcademyByIsVaild(Boolean isVaild) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolAcademyByIsVaild
	 *
	 */
	public Set<SchoolAcademy> findSchoolAcademyByIsVaild(Boolean isVaild, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolAcademyByAcademyType
	 *
	 */
	public Set<SchoolAcademy> findSchoolAcademyByAcademyType(String academyType_1) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolAcademyByAcademyType
	 *
	 */
	public Set<SchoolAcademy> findSchoolAcademyByAcademyType(String academyType_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolAcademyByCreatedAt
	 *
	 */
	public Set<SchoolAcademy> findSchoolAcademyByCreatedAt(java.util.Calendar createdAt) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolAcademyByCreatedAt
	 *
	 */
	public Set<SchoolAcademy> findSchoolAcademyByCreatedAt(Calendar createdAt, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolAcademyByAcademyNumber
	 *
	 */
	public SchoolAcademy findSchoolAcademyByAcademyNumber(String academyNumber_1) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolAcademyByAcademyNumber
	 *
	 */
	public SchoolAcademy findSchoolAcademyByAcademyNumber(String academyNumber_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolAcademyByAcademyName
	 *
	 */
	public Set<SchoolAcademy> findSchoolAcademyByAcademyName(String academyName_1) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolAcademyByAcademyName
	 *
	 */
	public Set<SchoolAcademy> findSchoolAcademyByAcademyName(String academyName_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolAcademyByUpdatedAt
	 *
	 */
	public Set<SchoolAcademy> findSchoolAcademyByUpdatedAt(java.util.Calendar updatedAt) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolAcademyByUpdatedAt
	 *
	 */
	public Set<SchoolAcademy> findSchoolAcademyByUpdatedAt(Calendar updatedAt, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolAcademyByAcademyNumberContaining
	 *
	 */
	public Set<SchoolAcademy> findSchoolAcademyByAcademyNumberContaining(String academyNumber_2) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolAcademyByAcademyNumberContaining
	 *
	 */
	public Set<SchoolAcademy> findSchoolAcademyByAcademyNumberContaining(String academyNumber_2, int startResult, int maxRows) throws DataAccessException;

}