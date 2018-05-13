package net.xidlims.dao;

import java.util.Calendar;
import java.util.Set;

import net.xidlims.domain.SchoolTerm;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage SchoolTerm entities.
 * 
 */
public interface SchoolTermDAO extends JpaDao<SchoolTerm> {

	/**
	 * JPQL Query - findSchoolTermByTermStartAfter
	 *
	 */
	public Set<SchoolTerm> findSchoolTermByTermStartAfter(java.util.Calendar termStart) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolTermByTermStartAfter
	 *
	 */
	public Set<SchoolTerm> findSchoolTermByTermStartAfter(Calendar termStart, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolTermByYearCodeContaining
	 *
	 */
	public Set<SchoolTerm> findSchoolTermByYearCodeContaining(String yearCode) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolTermByYearCodeContaining
	 *
	 */
	public Set<SchoolTerm> findSchoolTermByYearCodeContaining(String yearCode, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolTermByTermCode
	 *
	 */
	public Set<SchoolTerm> findSchoolTermByTermCode(Integer termCode) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolTermByTermCode
	 *
	 */
	public Set<SchoolTerm> findSchoolTermByTermCode(Integer termCode, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolTermByTermStartBefore
	 *
	 */
	public Set<SchoolTerm> findSchoolTermByTermStartBefore(java.util.Calendar termStart_1) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolTermByTermStartBefore
	 *
	 */
	public Set<SchoolTerm> findSchoolTermByTermStartBefore(Calendar termStart_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolTermByTermEndBefore
	 *
	 */
	public Set<SchoolTerm> findSchoolTermByTermEndBefore(java.util.Calendar termEnd) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolTermByTermEndBefore
	 *
	 */
	public Set<SchoolTerm> findSchoolTermByTermEndBefore(Calendar termEnd, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolTermByTermEndAfter
	 *
	 */
	public Set<SchoolTerm> findSchoolTermByTermEndAfter(java.util.Calendar termEnd_1) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolTermByTermEndAfter
	 *
	 */
	public Set<SchoolTerm> findSchoolTermByTermEndAfter(Calendar termEnd_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolTermByUpdatedAt
	 *
	 */
	public Set<SchoolTerm> findSchoolTermByUpdatedAt(java.util.Calendar updatedAt) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolTermByUpdatedAt
	 *
	 */
	public Set<SchoolTerm> findSchoolTermByUpdatedAt(Calendar updatedAt, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolTermByTermNameContaining
	 *
	 */
	public Set<SchoolTerm> findSchoolTermByTermNameContaining(String termName) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolTermByTermNameContaining
	 *
	 */
	public Set<SchoolTerm> findSchoolTermByTermNameContaining(String termName, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolTermByCreatedAt
	 *
	 */
	public Set<SchoolTerm> findSchoolTermByCreatedAt(java.util.Calendar createdAt) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolTermByCreatedAt
	 *
	 */
	public Set<SchoolTerm> findSchoolTermByCreatedAt(Calendar createdAt, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolTermByTermStart
	 *
	 */
	public Set<SchoolTerm> findSchoolTermByTermStart(java.util.Calendar termStart_2) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolTermByTermStart
	 *
	 */
	public Set<SchoolTerm> findSchoolTermByTermStart(Calendar termStart_2, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolTermById
	 *
	 */
	public SchoolTerm findSchoolTermById(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolTermById
	 *
	 */
	public SchoolTerm findSchoolTermById(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllSchoolTerms
	 *
	 */
	public Set<SchoolTerm> findAllSchoolTerms() throws DataAccessException;

	/**
	 * JPQL Query - findAllSchoolTerms
	 *
	 */
	public Set<SchoolTerm> findAllSchoolTerms(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolTermByPrimaryKey
	 *
	 */
	public SchoolTerm findSchoolTermByPrimaryKey(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolTermByPrimaryKey
	 *
	 */
	public SchoolTerm findSchoolTermByPrimaryKey(Integer id_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolTermByTermEnd
	 *
	 */
	public Set<SchoolTerm> findSchoolTermByTermEnd(java.util.Calendar termEnd_2) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolTermByTermEnd
	 *
	 */
	public Set<SchoolTerm> findSchoolTermByTermEnd(Calendar termEnd_2, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolTermByTermName
	 *
	 */
	public Set<SchoolTerm> findSchoolTermByTermName(String termName_1) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolTermByTermName
	 *
	 */
	public Set<SchoolTerm> findSchoolTermByTermName(String termName_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolTermByYearCode
	 *
	 */
	public Set<SchoolTerm> findSchoolTermByYearCode(String yearCode_1) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolTermByYearCode
	 *
	 */
	public Set<SchoolTerm> findSchoolTermByYearCode(String yearCode_1, int startResult, int maxRows) throws DataAccessException;

}