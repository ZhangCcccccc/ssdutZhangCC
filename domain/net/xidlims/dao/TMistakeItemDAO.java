package net.xidlims.dao;

import java.util.Set;

import net.xidlims.domain.TMistakeItem;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage TMistakeItem entities.
 * 
 */
public interface TMistakeItemDAO extends JpaDao<TMistakeItem> {

	/**
	 * JPQL Query - findAllTMistakeItems
	 *
	 */
	public Set<TMistakeItem> findAllTMistakeItems() throws DataAccessException;

	/**
	 * JPQL Query - findAllTMistakeItems
	 *
	 */
	public Set<TMistakeItem> findAllTMistakeItems(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTMistakeItemById
	 *
	 */
	public TMistakeItem findTMistakeItemById(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findTMistakeItemById
	 *
	 */
	public TMistakeItem findTMistakeItemById(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTMistakeItemByPrimaryKey
	 *
	 */
	public TMistakeItem findTMistakeItemByPrimaryKey(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findTMistakeItemByPrimaryKey
	 *
	 */
	public TMistakeItem findTMistakeItemByPrimaryKey(Integer id_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTMistakeItemByErrorCount
	 *
	 */
	public Set<TMistakeItem> findTMistakeItemByErrorCount(Integer errorCount) throws DataAccessException;

	/**
	 * JPQL Query - findTMistakeItemByErrorCount
	 *
	 */
	public Set<TMistakeItem> findTMistakeItemByErrorCount(Integer errorCount, int startResult, int maxRows) throws DataAccessException;

}