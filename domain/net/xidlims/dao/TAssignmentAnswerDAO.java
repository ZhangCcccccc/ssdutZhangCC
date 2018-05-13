package net.xidlims.dao;

import java.math.BigDecimal;

import java.util.Set;

import net.xidlims.domain.TAssignmentAnswer;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage TAssignmentAnswer entities.
 * 
 */
public interface TAssignmentAnswerDAO extends JpaDao<TAssignmentAnswer> {

	/**
	 * JPQL Query - findAllTAssignmentAnswers
	 *
	 */
	public Set<TAssignmentAnswer> findAllTAssignmentAnswers() throws DataAccessException;

	/**
	 * JPQL Query - findAllTAssignmentAnswers
	 *
	 */
	public Set<TAssignmentAnswer> findAllTAssignmentAnswers(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentAnswerByText
	 *
	 */
	public Set<TAssignmentAnswer> findTAssignmentAnswerByText(String text) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentAnswerByText
	 *
	 */
	public Set<TAssignmentAnswer> findTAssignmentAnswerByText(String text, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentAnswerByScore
	 *
	 */
	public Set<TAssignmentAnswer> findTAssignmentAnswerByScore(java.math.BigDecimal score) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentAnswerByScore
	 *
	 */
	public Set<TAssignmentAnswer> findTAssignmentAnswerByScore(BigDecimal score, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentAnswerByPrimaryKey
	 *
	 */
	public TAssignmentAnswer findTAssignmentAnswerByPrimaryKey(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentAnswerByPrimaryKey
	 *
	 */
	public TAssignmentAnswer findTAssignmentAnswerByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentAnswerByGrade
	 *
	 */
	public Set<TAssignmentAnswer> findTAssignmentAnswerByGrade(String grade) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentAnswerByGrade
	 *
	 */
	public Set<TAssignmentAnswer> findTAssignmentAnswerByGrade(String grade, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentAnswerByLabel
	 *
	 */
	public Set<TAssignmentAnswer> findTAssignmentAnswerByLabel(String label) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentAnswerByLabel
	 *
	 */
	public Set<TAssignmentAnswer> findTAssignmentAnswerByLabel(String label, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentAnswerById
	 *
	 */
	public TAssignmentAnswer findTAssignmentAnswerById(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentAnswerById
	 *
	 */
	public TAssignmentAnswer findTAssignmentAnswerById(Integer id_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentAnswerByLabelContaining
	 *
	 */
	public Set<TAssignmentAnswer> findTAssignmentAnswerByLabelContaining(String label_1) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentAnswerByLabelContaining
	 *
	 */
	public Set<TAssignmentAnswer> findTAssignmentAnswerByLabelContaining(String label_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentAnswerByIscorrect
	 *
	 */
	public Set<TAssignmentAnswer> findTAssignmentAnswerByIscorrect(Integer iscorrect) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentAnswerByIscorrect
	 *
	 */
	public Set<TAssignmentAnswer> findTAssignmentAnswerByIscorrect(Integer iscorrect, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentAnswerByGradeContaining
	 *
	 */
	public Set<TAssignmentAnswer> findTAssignmentAnswerByGradeContaining(String grade_1) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentAnswerByGradeContaining
	 *
	 */
	public Set<TAssignmentAnswer> findTAssignmentAnswerByGradeContaining(String grade_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentAnswerByTextContaining
	 *
	 */
	public Set<TAssignmentAnswer> findTAssignmentAnswerByTextContaining(String text_1) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentAnswerByTextContaining
	 *
	 */
	public Set<TAssignmentAnswer> findTAssignmentAnswerByTextContaining(String text_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentAnswerBySequence
	 *
	 */
	public Set<TAssignmentAnswer> findTAssignmentAnswerBySequence(Integer sequence) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentAnswerBySequence
	 *
	 */
	public Set<TAssignmentAnswer> findTAssignmentAnswerBySequence(Integer sequence, int startResult, int maxRows) throws DataAccessException;

}