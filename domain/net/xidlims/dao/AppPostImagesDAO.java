package net.xidlims.dao;

import java.util.Calendar;
import java.util.Set;

import net.xidlims.domain.AppPostImages;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage AppPostImages entities.
 * 
 */
public interface AppPostImagesDAO extends JpaDao<AppPostImages> {

	/**
	 * JPQL Query - findAppPostImagesByImageurlContaining
	 *
	 */
	public Set<AppPostImages> findAppPostImagesByImageurlContaining(String imageurl) throws DataAccessException;

	/**
	 * JPQL Query - findAppPostImagesByImageurlContaining
	 *
	 */
	public Set<AppPostImages> findAppPostImagesByImageurlContaining(String imageurl, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAppPostImagesById
	 *
	 */
	public AppPostImages findAppPostImagesById(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findAppPostImagesById
	 *
	 */
	public AppPostImages findAppPostImagesById(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAppPostImagesByImageurl
	 *
	 */
	public Set<AppPostImages> findAppPostImagesByImageurl(String imageurl_1) throws DataAccessException;

	/**
	 * JPQL Query - findAppPostImagesByImageurl
	 *
	 */
	public Set<AppPostImages> findAppPostImagesByImageurl(String imageurl_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAppPostImagesByUploadTime
	 *
	 */
	public Set<AppPostImages> findAppPostImagesByUploadTime(java.util.Calendar uploadTime) throws DataAccessException;

	/**
	 * JPQL Query - findAppPostImagesByUploadTime
	 *
	 */
	public Set<AppPostImages> findAppPostImagesByUploadTime(Calendar uploadTime, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllAppPostImagess
	 *
	 */
	public Set<AppPostImages> findAllAppPostImagess() throws DataAccessException;

	/**
	 * JPQL Query - findAllAppPostImagess
	 *
	 */
	public Set<AppPostImages> findAllAppPostImagess(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAppPostImagesByPrimaryKey
	 *
	 */
	public AppPostImages findAppPostImagesByPrimaryKey(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findAppPostImagesByPrimaryKey
	 *
	 */
	public AppPostImages findAppPostImagesByPrimaryKey(Integer id_1, int startResult, int maxRows) throws DataAccessException;

}