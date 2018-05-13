package net.xidlims.dao;

import java.util.Calendar;
import java.util.Set;

import net.xidlims.domain.TAssignmentSection;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage TAssignmentSection entities.
 * 
 */
public interface TAssignmentSectionDAO extends JpaDao<TAssignmentSection> {

	/**
	 * JPQL Query - findTAssignmentSectionByPrimaryKey
	 *
	 */
	public TAssignmentSection findTAssignmentSectionByPrimaryKey(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentSectionByPrimaryKey
	 *
	 */
	public TAssignmentSection findTAssignmentSectionByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentSectionByDescription
	 *
	 */
	public Set<TAssignmentSection> findTAssignmentSectionByDescription(String description) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentSectionByDescription
	 *
	 */
	public Set<TAssignmentSection> findTAssignmentSectionByDescription(String description, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentSectionByStatus
	 *
	 */
	public Set<TAssignmentSection> findTAssignmentSectionByStatus(Integer status) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentSectionByStatus
	 *
	 */
	public Set<TAssignmentSection> findTAssignmentSectionByStatus(Integer status, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentSectionById
	 *
	 */
	public TAssignmentSection findTAssignmentSectionById(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentSectionById
	 *
	 */
	public TAssignmentSection findTAssignmentSectionById(Integer id_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentSectionByDescriptionContaining
	 *
	 */
	public Set<TAssignmentSection> findTAssignmentSectionByDescriptionContaining(String description_1) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentSectionByDescriptionContaining
	 *
	 */
	public Set<TAssignmentSection> findTAssignmentSectionByDescriptionContaining(String description_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllTAssignmentSections
	 *
	 */
	public Set<TAssignmentSection> findAllTAssignmentSections() throws DataAccessException;

	/**
	 * JPQL Query - findAllTAssignmentSections
	 *
	 */
	public Set<TAssignmentSection> findAllTAssignmentSections(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentSectionBySequence
	 *
	 */
	public Set<TAssignmentSection> findTAssignmentSectionBySequence(Integer sequence) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentSectionBySequence
	 *
	 */
	public Set<TAssignmentSection> findTAssignmentSectionBySequence(Integer sequence, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentSectionByCreatedTime
	 *
	 */
	public Set<TAssignmentSection> findTAssignmentSectionByCreatedTime(java.util.Calendar createdTime) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentSectionByCreatedTime
	 *
	 */
	public Set<TAssignmentSection> findTAssignmentSectionByCreatedTime(Calendar createdTime, int startResult, int maxRows) throws DataAccessException;

}