package net.xidlims.dao;

import java.util.Calendar;
import java.util.Set;

import net.xidlims.domain.TAssignmentQuestionpool;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage TAssignmentQuestionpool entities.
 * 
 */
public interface TAssignmentQuestionpoolDAO extends
		JpaDao<TAssignmentQuestionpool> {

	/**
	 * JPQL Query - findTAssignmentQuestionpoolByQuestionpoolId
	 *
	 */
	public TAssignmentQuestionpool findTAssignmentQuestionpoolByQuestionpoolId(Integer questionpoolId) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentQuestionpoolByQuestionpoolId
	 *
	 */
	public TAssignmentQuestionpool findTAssignmentQuestionpoolByQuestionpoolId(Integer questionpoolId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentQuestionpoolByCreatedTime
	 *
	 */
	public Set<TAssignmentQuestionpool> findTAssignmentQuestionpoolByCreatedTime(java.util.Calendar createdTime) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentQuestionpoolByCreatedTime
	 *
	 */
	public Set<TAssignmentQuestionpool> findTAssignmentQuestionpoolByCreatedTime(Calendar createdTime, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentQuestionpoolByPrimaryKey
	 *
	 */
	public TAssignmentQuestionpool findTAssignmentQuestionpoolByPrimaryKey(Integer questionpoolId_1) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentQuestionpoolByPrimaryKey
	 *
	 */
	public TAssignmentQuestionpool findTAssignmentQuestionpoolByPrimaryKey(Integer questionpoolId_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentQuestionpoolByDescriptionContaining
	 *
	 */
	public Set<TAssignmentQuestionpool> findTAssignmentQuestionpoolByDescriptionContaining(String description) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentQuestionpoolByDescriptionContaining
	 *
	 */
	public Set<TAssignmentQuestionpool> findTAssignmentQuestionpoolByDescriptionContaining(String description, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllTAssignmentQuestionpools
	 *
	 */
	public Set<TAssignmentQuestionpool> findAllTAssignmentQuestionpools() throws DataAccessException;

	/**
	 * JPQL Query - findAllTAssignmentQuestionpools
	 *
	 */
	public Set<TAssignmentQuestionpool> findAllTAssignmentQuestionpools(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentQuestionpoolByTitle
	 *
	 */
	public Set<TAssignmentQuestionpool> findTAssignmentQuestionpoolByTitle(String title) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentQuestionpoolByTitle
	 *
	 */
	public Set<TAssignmentQuestionpool> findTAssignmentQuestionpoolByTitle(String title, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentQuestionpoolByDescription
	 *
	 */
	public Set<TAssignmentQuestionpool> findTAssignmentQuestionpoolByDescription(String description_1) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentQuestionpoolByDescription
	 *
	 */
	public Set<TAssignmentQuestionpool> findTAssignmentQuestionpoolByDescription(String description_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentQuestionpoolByTitleContaining
	 *
	 */
	public Set<TAssignmentQuestionpool> findTAssignmentQuestionpoolByTitleContaining(String title_1) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentQuestionpoolByTitleContaining
	 *
	 */
	public Set<TAssignmentQuestionpool> findTAssignmentQuestionpoolByTitleContaining(String title_1, int startResult, int maxRows) throws DataAccessException;

}