package net.xidlims.dao;

import java.util.Set;

import net.xidlims.domain.AppClouddickDownloadFolder;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage AppClouddickDownloadFolder entities.
 * 
 */
public interface AppClouddickDownloadFolderDAO extends
		JpaDao<AppClouddickDownloadFolder> {

	/**
	 * JPQL Query - findAppClouddickDownloadFolderByPrimaryKey
	 *
	 */
	public AppClouddickDownloadFolder findAppClouddickDownloadFolderByPrimaryKey(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findAppClouddickDownloadFolderByPrimaryKey
	 *
	 */
	public AppClouddickDownloadFolder findAppClouddickDownloadFolderByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAppClouddickDownloadFolderByLocation
	 *
	 */
	public Set<AppClouddickDownloadFolder> findAppClouddickDownloadFolderByLocation(String location) throws DataAccessException;

	/**
	 * JPQL Query - findAppClouddickDownloadFolderByLocation
	 *
	 */
	public Set<AppClouddickDownloadFolder> findAppClouddickDownloadFolderByLocation(String location, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAppClouddickDownloadFolderByLocationContaining
	 *
	 */
	public Set<AppClouddickDownloadFolder> findAppClouddickDownloadFolderByLocationContaining(String location_1) throws DataAccessException;

	/**
	 * JPQL Query - findAppClouddickDownloadFolderByLocationContaining
	 *
	 */
	public Set<AppClouddickDownloadFolder> findAppClouddickDownloadFolderByLocationContaining(String location_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllAppClouddickDownloadFolders
	 *
	 */
	public Set<AppClouddickDownloadFolder> findAllAppClouddickDownloadFolders() throws DataAccessException;

	/**
	 * JPQL Query - findAllAppClouddickDownloadFolders
	 *
	 */
	public Set<AppClouddickDownloadFolder> findAllAppClouddickDownloadFolders(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAppClouddickDownloadFolderById
	 *
	 */
	public AppClouddickDownloadFolder findAppClouddickDownloadFolderById(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findAppClouddickDownloadFolderById
	 *
	 */
	public AppClouddickDownloadFolder findAppClouddickDownloadFolderById(Integer id_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAppClouddickDownloadFolderByFoldername
	 *
	 */
	public Set<AppClouddickDownloadFolder> findAppClouddickDownloadFolderByFoldername(String foldername) throws DataAccessException;

	/**
	 * JPQL Query - findAppClouddickDownloadFolderByFoldername
	 *
	 */
	public Set<AppClouddickDownloadFolder> findAppClouddickDownloadFolderByFoldername(String foldername, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAppClouddickDownloadFolderByFoldernameContaining
	 *
	 */
	public Set<AppClouddickDownloadFolder> findAppClouddickDownloadFolderByFoldernameContaining(String foldername_1) throws DataAccessException;

	/**
	 * JPQL Query - findAppClouddickDownloadFolderByFoldernameContaining
	 *
	 */
	public Set<AppClouddickDownloadFolder> findAppClouddickDownloadFolderByFoldernameContaining(String foldername_1, int startResult, int maxRows) throws DataAccessException;

}