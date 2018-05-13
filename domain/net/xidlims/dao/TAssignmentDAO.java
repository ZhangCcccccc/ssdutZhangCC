package net.xidlims.dao;

import java.util.Calendar;
import java.util.Set;

import net.xidlims.domain.TAssignment;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage TAssignment entities.
 * 
 */
public interface TAssignmentDAO extends JpaDao<TAssignment> {

	/**
	 * JPQL Query - findTAssignmentByStatus
	 *
	 */
	public Set<TAssignment> findTAssignmentByStatus(Integer status) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentByStatus
	 *
	 */
	public Set<TAssignment> findTAssignmentByStatus(Integer status, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentById
	 *
	 */
	public TAssignment findTAssignmentById(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentById
	 *
	 */
	public TAssignment findTAssignmentById(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentByTitleContaining
	 *
	 */
	public Set<TAssignment> findTAssignmentByTitleContaining(String title) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentByTitleContaining
	 *
	 */
	public Set<TAssignment> findTAssignmentByTitleContaining(String title, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentByDescriptionContaining
	 *
	 */
	public Set<TAssignment> findTAssignmentByDescriptionContaining(String description) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentByDescriptionContaining
	 *
	 */
	public Set<TAssignment> findTAssignmentByDescriptionContaining(String description, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllTAssignments
	 *
	 */
	public Set<TAssignment> findAllTAssignments() throws DataAccessException;

	/**
	 * JPQL Query - findAllTAssignments
	 *
	 */
	public Set<TAssignment> findAllTAssignments(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentByTitle
	 *
	 */
	public Set<TAssignment> findTAssignmentByTitle(String title_1) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentByTitle
	 *
	 */
	public Set<TAssignment> findTAssignmentByTitle(String title_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentByPrimaryKey
	 *
	 */
	public TAssignment findTAssignmentByPrimaryKey(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentByPrimaryKey
	 *
	 */
	public TAssignment findTAssignmentByPrimaryKey(Integer id_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentByDescription
	 *
	 */
	public Set<TAssignment> findTAssignmentByDescription(String description_1) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentByDescription
	 *
	 */
	public Set<TAssignment> findTAssignmentByDescription(String description_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentByCreatedTime
	 *
	 */
	public Set<TAssignment> findTAssignmentByCreatedTime(java.util.Calendar createdTime) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentByCreatedTime
	 *
	 */
	public Set<TAssignment> findTAssignmentByCreatedTime(Calendar createdTime, int startResult, int maxRows) throws DataAccessException;

}