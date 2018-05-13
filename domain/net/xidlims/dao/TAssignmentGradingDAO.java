package net.xidlims.dao;

import java.math.BigDecimal;

import java.util.Calendar;
import java.util.Set;

import net.xidlims.domain.TAssignmentGrading;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage TAssignmentGrading entities.
 * 
 */
public interface TAssignmentGradingDAO extends JpaDao<TAssignmentGrading> {

	/**
	 * JPQL Query - findTAssignmentGradingByAccessmentgradingId
	 *
	 */
	public TAssignmentGrading findTAssignmentGradingByAccessmentgradingId(Integer accessmentgradingId) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentGradingByAccessmentgradingId
	 *
	 */
	public TAssignmentGrading findTAssignmentGradingByAccessmentgradingId(Integer accessmentgradingId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllTAssignmentGradings
	 *
	 */
	public Set<TAssignmentGrading> findAllTAssignmentGradings() throws DataAccessException;

	/**
	 * JPQL Query - findAllTAssignmentGradings
	 *
	 */
	public Set<TAssignmentGrading> findAllTAssignmentGradings(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentGradingByPrimaryKey
	 *
	 */
	public TAssignmentGrading findTAssignmentGradingByPrimaryKey(Integer accessmentgradingId_1) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentGradingByPrimaryKey
	 *
	 */
	public TAssignmentGrading findTAssignmentGradingByPrimaryKey(Integer accessmentgradingId_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentGradingByIslate
	 *
	 */
	public Set<TAssignmentGrading> findTAssignmentGradingByIslate(Integer islate) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentGradingByIslate
	 *
	 */
	public Set<TAssignmentGrading> findTAssignmentGradingByIslate(Integer islate, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentGradingBySubmitdate
	 *
	 */
	public Set<TAssignmentGrading> findTAssignmentGradingBySubmitdate(java.util.Calendar submitdate) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentGradingBySubmitdate
	 *
	 */
	public Set<TAssignmentGrading> findTAssignmentGradingBySubmitdate(Calendar submitdate, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentGradingByComments
	 *
	 */
	public Set<TAssignmentGrading> findTAssignmentGradingByComments(String comments) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentGradingByComments
	 *
	 */
	public Set<TAssignmentGrading> findTAssignmentGradingByComments(String comments, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentGradingByCommentsContaining
	 *
	 */
	public Set<TAssignmentGrading> findTAssignmentGradingByCommentsContaining(String comments_1) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentGradingByCommentsContaining
	 *
	 */
	public Set<TAssignmentGrading> findTAssignmentGradingByCommentsContaining(String comments_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentGradingByFinalScore
	 *
	 */
	public Set<TAssignmentGrading> findTAssignmentGradingByFinalScore(java.math.BigDecimal finalScore) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentGradingByFinalScore
	 *
	 */
	public Set<TAssignmentGrading> findTAssignmentGradingByFinalScore(BigDecimal finalScore, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentGradingByGradeTime
	 *
	 */
	public Set<TAssignmentGrading> findTAssignmentGradingByGradeTime(java.util.Calendar gradeTime) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentGradingByGradeTime
	 *
	 */
	public Set<TAssignmentGrading> findTAssignmentGradingByGradeTime(Calendar gradeTime, int startResult, int maxRows) throws DataAccessException;

}