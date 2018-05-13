package net.xidlims.dao;

import java.util.Set;

import net.xidlims.domain.AppClouddickDownloadFile;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage AppClouddickDownloadFile entities.
 * 
 */
public interface AppClouddickDownloadFileDAO extends
		JpaDao<AppClouddickDownloadFile> {

	/**
	 * JPQL Query - findAppClouddickDownloadFileByLocation
	 *
	 */
	public Set<AppClouddickDownloadFile> findAppClouddickDownloadFileByLocation(String location) throws DataAccessException;

	/**
	 * JPQL Query - findAppClouddickDownloadFileByLocation
	 *
	 */
	public Set<AppClouddickDownloadFile> findAppClouddickDownloadFileByLocation(String location, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAppClouddickDownloadFileByLocationContaining
	 *
	 */
	public Set<AppClouddickDownloadFile> findAppClouddickDownloadFileByLocationContaining(String location_1) throws DataAccessException;

	/**
	 * JPQL Query - findAppClouddickDownloadFileByLocationContaining
	 *
	 */
	public Set<AppClouddickDownloadFile> findAppClouddickDownloadFileByLocationContaining(String location_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAppClouddickDownloadFileByPrimaryKey
	 *
	 */
	public AppClouddickDownloadFile findAppClouddickDownloadFileByPrimaryKey(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findAppClouddickDownloadFileByPrimaryKey
	 *
	 */
	public AppClouddickDownloadFile findAppClouddickDownloadFileByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAppClouddickDownloadFileById
	 *
	 */
	public AppClouddickDownloadFile findAppClouddickDownloadFileById(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findAppClouddickDownloadFileById
	 *
	 */
	public AppClouddickDownloadFile findAppClouddickDownloadFileById(Integer id_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllAppClouddickDownloadFiles
	 *
	 */
	public Set<AppClouddickDownloadFile> findAllAppClouddickDownloadFiles() throws DataAccessException;

	/**
	 * JPQL Query - findAllAppClouddickDownloadFiles
	 *
	 */
	public Set<AppClouddickDownloadFile> findAllAppClouddickDownloadFiles(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAppClouddickDownloadFileByFilenameContaining
	 *
	 */
	public Set<AppClouddickDownloadFile> findAppClouddickDownloadFileByFilenameContaining(String filename) throws DataAccessException;

	/**
	 * JPQL Query - findAppClouddickDownloadFileByFilenameContaining
	 *
	 */
	public Set<AppClouddickDownloadFile> findAppClouddickDownloadFileByFilenameContaining(String filename, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAppClouddickDownloadFileByFilename
	 *
	 */
	public Set<AppClouddickDownloadFile> findAppClouddickDownloadFileByFilename(String filename_1) throws DataAccessException;

	/**
	 * JPQL Query - findAppClouddickDownloadFileByFilename
	 *
	 */
	public Set<AppClouddickDownloadFile> findAppClouddickDownloadFileByFilename(String filename_1, int startResult, int maxRows) throws DataAccessException;

}