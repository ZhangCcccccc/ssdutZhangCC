package net.xidlims.dao;

import java.math.BigDecimal;

import java.util.Calendar;
import java.util.Set;

import net.xidlims.domain.TAssignmentAnswerAssign;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage TAssignmentAnswerAssign entities.
 * 
 */
public interface TAssignmentAnswerAssignDAO extends
		JpaDao<TAssignmentAnswerAssign> {

	/**
	 * JPQL Query - findTAssignmentAnswerAssignByGrade
	 *
	 */
	public Set<TAssignmentAnswerAssign> findTAssignmentAnswerAssignByGrade(java.math.BigDecimal grade) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentAnswerAssignByGrade
	 *
	 */
	public Set<TAssignmentAnswerAssign> findTAssignmentAnswerAssignByGrade(BigDecimal grade, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentAnswerAssignById
	 *
	 */
	public TAssignmentAnswerAssign findTAssignmentAnswerAssignById(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentAnswerAssignById
	 *
	 */
	public TAssignmentAnswerAssign findTAssignmentAnswerAssignById(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentAnswerAssignByContent
	 *
	 */
	public Set<TAssignmentAnswerAssign> findTAssignmentAnswerAssignByContent(String content) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentAnswerAssignByContent
	 *
	 */
	public Set<TAssignmentAnswerAssign> findTAssignmentAnswerAssignByContent(String content, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentAnswerAssignByScore
	 *
	 */
	public Set<TAssignmentAnswerAssign> findTAssignmentAnswerAssignByScore(java.math.BigDecimal score) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentAnswerAssignByScore
	 *
	 */
	public Set<TAssignmentAnswerAssign> findTAssignmentAnswerAssignByScore(BigDecimal score, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentAnswerAssignByReference
	 *
	 */
	public Set<TAssignmentAnswerAssign> findTAssignmentAnswerAssignByReference(String reference) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentAnswerAssignByReference
	 *
	 */
	public Set<TAssignmentAnswerAssign> findTAssignmentAnswerAssignByReference(String reference, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllTAssignmentAnswerAssigns
	 *
	 */
	public Set<TAssignmentAnswerAssign> findAllTAssignmentAnswerAssigns() throws DataAccessException;

	/**
	 * JPQL Query - findAllTAssignmentAnswerAssigns
	 *
	 */
	public Set<TAssignmentAnswerAssign> findAllTAssignmentAnswerAssigns(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentAnswerAssignByPrimaryKey
	 *
	 */
	public TAssignmentAnswerAssign findTAssignmentAnswerAssignByPrimaryKey(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentAnswerAssignByPrimaryKey
	 *
	 */
	public TAssignmentAnswerAssign findTAssignmentAnswerAssignByPrimaryKey(Integer id_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentAnswerAssignByScoreDate
	 *
	 */
	public Set<TAssignmentAnswerAssign> findTAssignmentAnswerAssignByScoreDate(java.util.Calendar scoreDate) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentAnswerAssignByScoreDate
	 *
	 */
	public Set<TAssignmentAnswerAssign> findTAssignmentAnswerAssignByScoreDate(Calendar scoreDate, int startResult, int maxRows) throws DataAccessException;

}