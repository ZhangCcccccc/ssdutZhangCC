package net.xidlims.dao;

import java.math.BigDecimal;

import java.util.Calendar;
import java.util.Set;

import net.xidlims.domain.TAssignmentItem;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage TAssignmentItem entities.
 * 
 */
public interface TAssignmentItemDAO extends JpaDao<TAssignmentItem> {

	/**
	 * JPQL Query - findTAssignmentItemById
	 *
	 */
	public TAssignmentItem findTAssignmentItemById(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentItemById
	 *
	 */
	public TAssignmentItem findTAssignmentItemById(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentItemByScore
	 *
	 */
	public Set<TAssignmentItem> findTAssignmentItemByScore(java.math.BigDecimal score) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentItemByScore
	 *
	 */
	public Set<TAssignmentItem> findTAssignmentItemByScore(BigDecimal score, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllTAssignmentItems
	 *
	 */
	public Set<TAssignmentItem> findAllTAssignmentItems() throws DataAccessException;

	/**
	 * JPQL Query - findAllTAssignmentItems
	 *
	 */
	public Set<TAssignmentItem> findAllTAssignmentItems(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentItemByPrimaryKey
	 *
	 */
	public TAssignmentItem findTAssignmentItemByPrimaryKey(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentItemByPrimaryKey
	 *
	 */
	public TAssignmentItem findTAssignmentItemByPrimaryKey(Integer id_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentItemByCreatedTime
	 *
	 */
	public Set<TAssignmentItem> findTAssignmentItemByCreatedTime(java.util.Calendar createdTime) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentItemByCreatedTime
	 *
	 */
	public Set<TAssignmentItem> findTAssignmentItemByCreatedTime(Calendar createdTime, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentItemByDescriptionContaining
	 *
	 */
	public Set<TAssignmentItem> findTAssignmentItemByDescriptionContaining(String description) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentItemByDescriptionContaining
	 *
	 */
	public Set<TAssignmentItem> findTAssignmentItemByDescriptionContaining(String description, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentItemByDescription
	 *
	 */
	public Set<TAssignmentItem> findTAssignmentItemByDescription(String description_1) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentItemByDescription
	 *
	 */
	public Set<TAssignmentItem> findTAssignmentItemByDescription(String description_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentItemByGradeContaining
	 *
	 */
	public Set<TAssignmentItem> findTAssignmentItemByGradeContaining(String grade) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentItemByGradeContaining
	 *
	 */
	public Set<TAssignmentItem> findTAssignmentItemByGradeContaining(String grade, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentItemByGrade
	 *
	 */
	public Set<TAssignmentItem> findTAssignmentItemByGrade(String grade_1) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentItemByGrade
	 *
	 */
	public Set<TAssignmentItem> findTAssignmentItemByGrade(String grade_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentItemByStatus
	 *
	 */
	public Set<TAssignmentItem> findTAssignmentItemByStatus(Integer status) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentItemByStatus
	 *
	 */
	public Set<TAssignmentItem> findTAssignmentItemByStatus(Integer status, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentItemBySequence
	 *
	 */
	public Set<TAssignmentItem> findTAssignmentItemBySequence(Integer sequence) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentItemBySequence
	 *
	 */
	public Set<TAssignmentItem> findTAssignmentItemBySequence(Integer sequence, int startResult, int maxRows) throws DataAccessException;

}