package net.xidlims.dao;

import java.util.Set;

import net.xidlims.domain.TExerciseAnswerRecord;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage TExerciseAnswerRecord entities.
 * 
 */
public interface TExerciseAnswerRecordDAO extends JpaDao<TExerciseAnswerRecord> {

	/**
	 * JPQL Query - findAllTExerciseAnswerRecords
	 *
	 */
	public Set<TExerciseAnswerRecord> findAllTExerciseAnswerRecords() throws DataAccessException;

	/**
	 * JPQL Query - findAllTExerciseAnswerRecords
	 *
	 */
	public Set<TExerciseAnswerRecord> findAllTExerciseAnswerRecords(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTExerciseAnswerRecordById
	 *
	 */
	public TExerciseAnswerRecord findTExerciseAnswerRecordById(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findTExerciseAnswerRecordById
	 *
	 */
	public TExerciseAnswerRecord findTExerciseAnswerRecordById(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTExerciseAnswerRecordByAnswerTextContaining
	 *
	 */
	public Set<TExerciseAnswerRecord> findTExerciseAnswerRecordByAnswerTextContaining(String answerText) throws DataAccessException;

	/**
	 * JPQL Query - findTExerciseAnswerRecordByAnswerTextContaining
	 *
	 */
	public Set<TExerciseAnswerRecord> findTExerciseAnswerRecordByAnswerTextContaining(String answerText, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTExerciseAnswerRecordByPrimaryKey
	 *
	 */
	public TExerciseAnswerRecord findTExerciseAnswerRecordByPrimaryKey(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findTExerciseAnswerRecordByPrimaryKey
	 *
	 */
	public TExerciseAnswerRecord findTExerciseAnswerRecordByPrimaryKey(Integer id_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTExerciseAnswerRecordByAnswerText
	 *
	 */
	public Set<TExerciseAnswerRecord> findTExerciseAnswerRecordByAnswerText(String answerText_1) throws DataAccessException;

	/**
	 * JPQL Query - findTExerciseAnswerRecordByAnswerText
	 *
	 */
	public Set<TExerciseAnswerRecord> findTExerciseAnswerRecordByAnswerText(String answerText_1, int startResult, int maxRows) throws DataAccessException;

}