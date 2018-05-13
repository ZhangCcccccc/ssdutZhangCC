package net.xidlims.dao;

import java.util.Set;

import net.xidlims.domain.TExerciseItemRecord;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage TExerciseItemRecord entities.
 * 
 */
public interface TExerciseItemRecordDAO extends JpaDao<TExerciseItemRecord> {

	/**
	 * JPQL Query - findTExerciseItemRecordByPrimaryKey
	 *
	 */
	public TExerciseItemRecord findTExerciseItemRecordByPrimaryKey(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findTExerciseItemRecordByPrimaryKey
	 *
	 */
	public TExerciseItemRecord findTExerciseItemRecordByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTExerciseItemRecordByExerciseTypeContaining
	 *
	 */
	public Set<TExerciseItemRecord> findTExerciseItemRecordByExerciseTypeContaining(String exerciseType) throws DataAccessException;

	/**
	 * JPQL Query - findTExerciseItemRecordByExerciseTypeContaining
	 *
	 */
	public Set<TExerciseItemRecord> findTExerciseItemRecordByExerciseTypeContaining(String exerciseType, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTExerciseItemRecordByExerciseType
	 *
	 */
	public Set<TExerciseItemRecord> findTExerciseItemRecordByExerciseType(String exerciseType_1) throws DataAccessException;

	/**
	 * JPQL Query - findTExerciseItemRecordByExerciseType
	 *
	 */
	public Set<TExerciseItemRecord> findTExerciseItemRecordByExerciseType(String exerciseType_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllTExerciseItemRecords
	 *
	 */
	public Set<TExerciseItemRecord> findAllTExerciseItemRecords() throws DataAccessException;

	/**
	 * JPQL Query - findAllTExerciseItemRecords
	 *
	 */
	public Set<TExerciseItemRecord> findAllTExerciseItemRecords(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTExerciseItemRecordByIscorrect
	 *
	 */
	public Set<TExerciseItemRecord> findTExerciseItemRecordByIscorrect(Integer iscorrect) throws DataAccessException;

	/**
	 * JPQL Query - findTExerciseItemRecordByIscorrect
	 *
	 */
	public Set<TExerciseItemRecord> findTExerciseItemRecordByIscorrect(Integer iscorrect, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTExerciseItemRecordById
	 *
	 */
	public TExerciseItemRecord findTExerciseItemRecordById(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findTExerciseItemRecordById
	 *
	 */
	public TExerciseItemRecord findTExerciseItemRecordById(Integer id_1, int startResult, int maxRows) throws DataAccessException;

}