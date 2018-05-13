package net.xidlims.dao;

import java.util.Calendar;
import java.util.Set;

import net.xidlims.domain.WkFolder;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage WkFolder entities.
 * 
 */
public interface WkFolderDAO extends JpaDao<WkFolder> {

	/**
	 * JPQL Query - findWkFolderByUpdateTime
	 *
	 */
	public Set<WkFolder> findWkFolderByUpdateTime(java.util.Calendar updateTime) throws DataAccessException;

	/**
	 * JPQL Query - findWkFolderByUpdateTime
	 *
	 */
	public Set<WkFolder> findWkFolderByUpdateTime(Calendar updateTime, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findWkFolderById
	 *
	 */
	public WkFolder findWkFolderById(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findWkFolderById
	 *
	 */
	public WkFolder findWkFolderById(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findWkFolderByName
	 *
	 */
	public Set<WkFolder> findWkFolderByName(String name) throws DataAccessException;

	/**
	 * JPQL Query - findWkFolderByName
	 *
	 */
	public Set<WkFolder> findWkFolderByName(String name, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findWkFolderByCreateTime
	 *
	 */
	public Set<WkFolder> findWkFolderByCreateTime(java.util.Calendar createTime) throws DataAccessException;

	/**
	 * JPQL Query - findWkFolderByCreateTime
	 *
	 */
	public Set<WkFolder> findWkFolderByCreateTime(Calendar createTime, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllWkFolders
	 *
	 */
	public Set<WkFolder> findAllWkFolders() throws DataAccessException;

	/**
	 * JPQL Query - findAllWkFolders
	 *
	 */
	public Set<WkFolder> findAllWkFolders(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findWkFolderByNameContaining
	 *
	 */
	public Set<WkFolder> findWkFolderByNameContaining(String name_1) throws DataAccessException;

	/**
	 * JPQL Query - findWkFolderByNameContaining
	 *
	 */
	public Set<WkFolder> findWkFolderByNameContaining(String name_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findWkFolderByPrimaryKey
	 *
	 */
	public WkFolder findWkFolderByPrimaryKey(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findWkFolderByPrimaryKey
	 *
	 */
	public WkFolder findWkFolderByPrimaryKey(Integer id_1, int startResult, int maxRows) throws DataAccessException;

}