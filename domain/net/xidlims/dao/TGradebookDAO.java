package net.xidlims.dao;

import java.util.Set;

import net.xidlims.domain.TGradebook;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage TGradebook entities.
 * 
 */
public interface TGradebookDAO extends JpaDao<TGradebook> {

	/**
	 * JPQL Query - findTGradebookByTitle
	 *
	 */
	public Set<TGradebook> findTGradebookByTitle(String title) throws DataAccessException;

	/**
	 * JPQL Query - findTGradebookByTitle
	 *
	 */
	public Set<TGradebook> findTGradebookByTitle(String title, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTGradebookById
	 *
	 */
	public TGradebook findTGradebookById(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findTGradebookById
	 *
	 */
	public TGradebook findTGradebookById(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllTGradebooks
	 *
	 */
	public Set<TGradebook> findAllTGradebooks() throws DataAccessException;

	/**
	 * JPQL Query - findAllTGradebooks
	 *
	 */
	public Set<TGradebook> findAllTGradebooks(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTGradebookByTitleContaining
	 *
	 */
	public Set<TGradebook> findTGradebookByTitleContaining(String title_1) throws DataAccessException;

	/**
	 * JPQL Query - findTGradebookByTitleContaining
	 *
	 */
	public Set<TGradebook> findTGradebookByTitleContaining(String title_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTGradebookByPrimaryKey
	 *
	 */
	public TGradebook findTGradebookByPrimaryKey(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findTGradebookByPrimaryKey
	 *
	 */
	public TGradebook findTGradebookByPrimaryKey(Integer id_1, int startResult, int maxRows) throws DataAccessException;

}