package net.xidlims.dao;

import java.math.BigDecimal;

import java.util.Calendar;
import java.util.Set;

import net.xidlims.domain.TAssignmentItemMapping;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage TAssignmentItemMapping entities.
 * 
 */
public interface TAssignmentItemMappingDAO extends
		JpaDao<TAssignmentItemMapping> {

	/**
	 * JPQL Query - findTAssignmentItemMappingById
	 *
	 */
	public TAssignmentItemMapping findTAssignmentItemMappingById(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentItemMappingById
	 *
	 */
	public TAssignmentItemMapping findTAssignmentItemMappingById(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentItemMappingByComments
	 *
	 */
	public Set<TAssignmentItemMapping> findTAssignmentItemMappingByComments(String comments) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentItemMappingByComments
	 *
	 */
	public Set<TAssignmentItemMapping> findTAssignmentItemMappingByComments(String comments, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentItemMappingBySubmitDate
	 *
	 */
	public Set<TAssignmentItemMapping> findTAssignmentItemMappingBySubmitDate(java.util.Calendar submitDate) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentItemMappingBySubmitDate
	 *
	 */
	public Set<TAssignmentItemMapping> findTAssignmentItemMappingBySubmitDate(Calendar submitDate, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentItemMappingByOveriderScore
	 *
	 */
	public Set<TAssignmentItemMapping> findTAssignmentItemMappingByOveriderScore(java.math.BigDecimal overiderScore) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentItemMappingByOveriderScore
	 *
	 */
	public Set<TAssignmentItemMapping> findTAssignmentItemMappingByOveriderScore(BigDecimal overiderScore, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentItemMappingByGradetime
	 *
	 */
	public Set<TAssignmentItemMapping> findTAssignmentItemMappingByGradetime(java.util.Calendar gradetime) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentItemMappingByGradetime
	 *
	 */
	public Set<TAssignmentItemMapping> findTAssignmentItemMappingByGradetime(Calendar gradetime, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllTAssignmentItemMappings
	 *
	 */
	public Set<TAssignmentItemMapping> findAllTAssignmentItemMappings() throws DataAccessException;

	/**
	 * JPQL Query - findAllTAssignmentItemMappings
	 *
	 */
	public Set<TAssignmentItemMapping> findAllTAssignmentItemMappings(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentItemMappingByPrimaryKey
	 *
	 */
	public TAssignmentItemMapping findTAssignmentItemMappingByPrimaryKey(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentItemMappingByPrimaryKey
	 *
	 */
	public TAssignmentItemMapping findTAssignmentItemMappingByPrimaryKey(Integer id_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentItemMappingByCommentsContaining
	 *
	 */
	public Set<TAssignmentItemMapping> findTAssignmentItemMappingByCommentsContaining(String comments_1) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentItemMappingByCommentsContaining
	 *
	 */
	public Set<TAssignmentItemMapping> findTAssignmentItemMappingByCommentsContaining(String comments_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentItemMappingByAutoscore
	 *
	 */
	public Set<TAssignmentItemMapping> findTAssignmentItemMappingByAutoscore(java.math.BigDecimal autoscore) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentItemMappingByAutoscore
	 *
	 */
	public Set<TAssignmentItemMapping> findTAssignmentItemMappingByAutoscore(BigDecimal autoscore, int startResult, int maxRows) throws DataAccessException;

}