package net.xidlims.dao;

import java.util.Set;

import net.xidlims.domain.TExerciseInfo;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage TExerciseInfo entities.
 * 
 */
public interface TExerciseInfoDAO extends JpaDao<TExerciseInfo> {

	/**
	 * JPQL Query - findTExerciseInfoByStochasticString
	 *
	 */
	public Set<TExerciseInfo> findTExerciseInfoByStochasticString(String stochasticString) throws DataAccessException;

	/**
	 * JPQL Query - findTExerciseInfoByStochasticString
	 *
	 */
	public Set<TExerciseInfo> findTExerciseInfoByStochasticString(String stochasticString, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTExerciseInfoByMistakeNumber
	 *
	 */
	public Set<TExerciseInfo> findTExerciseInfoByMistakeNumber(Integer mistakeNumber) throws DataAccessException;

	/**
	 * JPQL Query - findTExerciseInfoByMistakeNumber
	 *
	 */
	public Set<TExerciseInfo> findTExerciseInfoByMistakeNumber(Integer mistakeNumber, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTExerciseInfoByPrimaryKey
	 *
	 */
	public TExerciseInfo findTExerciseInfoByPrimaryKey(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findTExerciseInfoByPrimaryKey
	 *
	 */
	public TExerciseInfo findTExerciseInfoByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTExerciseInfoById
	 *
	 */
	public TExerciseInfo findTExerciseInfoById(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findTExerciseInfoById
	 *
	 */
	public TExerciseInfo findTExerciseInfoById(Integer id_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTExerciseInfoByStochasticNumber
	 *
	 */
	public Set<TExerciseInfo> findTExerciseInfoByStochasticNumber(Integer stochasticNumber) throws DataAccessException;

	/**
	 * JPQL Query - findTExerciseInfoByStochasticNumber
	 *
	 */
	public Set<TExerciseInfo> findTExerciseInfoByStochasticNumber(Integer stochasticNumber, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTExerciseInfoByOrderNumber
	 *
	 */
	public Set<TExerciseInfo> findTExerciseInfoByOrderNumber(Integer orderNumber) throws DataAccessException;

	/**
	 * JPQL Query - findTExerciseInfoByOrderNumber
	 *
	 */
	public Set<TExerciseInfo> findTExerciseInfoByOrderNumber(Integer orderNumber, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllTExerciseInfos
	 *
	 */
	public Set<TExerciseInfo> findAllTExerciseInfos() throws DataAccessException;

	/**
	 * JPQL Query - findAllTExerciseInfos
	 *
	 */
	public Set<TExerciseInfo> findAllTExerciseInfos(int startResult, int maxRows) throws DataAccessException;

}