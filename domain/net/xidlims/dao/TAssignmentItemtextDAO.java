package net.xidlims.dao;

import java.util.Set;

import net.xidlims.domain.TAssignmentItemtext;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage TAssignmentItemtext entities.
 * 
 */
public interface TAssignmentItemtextDAO extends JpaDao<TAssignmentItemtext> {

	/**
	 * JPQL Query - findTAssignmentItemtextById
	 *
	 */
	public TAssignmentItemtext findTAssignmentItemtextById(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentItemtextById
	 *
	 */
	public TAssignmentItemtext findTAssignmentItemtextById(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentItemtextByPrimaryKey
	 *
	 */
	public TAssignmentItemtext findTAssignmentItemtextByPrimaryKey(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentItemtextByPrimaryKey
	 *
	 */
	public TAssignmentItemtext findTAssignmentItemtextByPrimaryKey(Integer id_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentItemtextBySequence
	 *
	 */
	public Set<TAssignmentItemtext> findTAssignmentItemtextBySequence(Integer sequence) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentItemtextBySequence
	 *
	 */
	public Set<TAssignmentItemtext> findTAssignmentItemtextBySequence(Integer sequence, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentItemtextByTextContaining
	 *
	 */
	public Set<TAssignmentItemtext> findTAssignmentItemtextByTextContaining(String text) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentItemtextByTextContaining
	 *
	 */
	public Set<TAssignmentItemtext> findTAssignmentItemtextByTextContaining(String text, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentItemtextByText
	 *
	 */
	public Set<TAssignmentItemtext> findTAssignmentItemtextByText(String text_1) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentItemtextByText
	 *
	 */
	public Set<TAssignmentItemtext> findTAssignmentItemtextByText(String text_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllTAssignmentItemtexts
	 *
	 */
	public Set<TAssignmentItemtext> findAllTAssignmentItemtexts() throws DataAccessException;

	/**
	 * JPQL Query - findAllTAssignmentItemtexts
	 *
	 */
	public Set<TAssignmentItemtext> findAllTAssignmentItemtexts(int startResult, int maxRows) throws DataAccessException;

}