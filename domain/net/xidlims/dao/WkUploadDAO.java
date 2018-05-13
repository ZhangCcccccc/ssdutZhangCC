package net.xidlims.dao;

import java.util.Calendar;
import java.util.Set;

import net.xidlims.domain.WkUpload;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage WkUpload entities.
 * 
 */
public interface WkUploadDAO extends JpaDao<WkUpload> {

	/**
	 * JPQL Query - findAllWkUploads
	 *
	 */
	public Set<WkUpload> findAllWkUploads() throws DataAccessException;

	/**
	 * JPQL Query - findAllWkUploads
	 *
	 */
	public Set<WkUpload> findAllWkUploads(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findWkUploadByUrl
	 *
	 */
	public Set<WkUpload> findWkUploadByUrl(String url) throws DataAccessException;

	/**
	 * JPQL Query - findWkUploadByUrl
	 *
	 */
	public Set<WkUpload> findWkUploadByUrl(String url, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findWkUploadByNameContaining
	 *
	 */
	public Set<WkUpload> findWkUploadByNameContaining(String name) throws DataAccessException;

	/**
	 * JPQL Query - findWkUploadByNameContaining
	 *
	 */
	public Set<WkUpload> findWkUploadByNameContaining(String name, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findWkUploadByDescription
	 *
	 */
	public Set<WkUpload> findWkUploadByDescription(String description) throws DataAccessException;

	/**
	 * JPQL Query - findWkUploadByDescription
	 *
	 */
	public Set<WkUpload> findWkUploadByDescription(String description, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findWkUploadByUpTime
	 *
	 */
	public Set<WkUpload> findWkUploadByUpTime(java.util.Calendar upTime) throws DataAccessException;

	/**
	 * JPQL Query - findWkUploadByUpTime
	 *
	 */
	public Set<WkUpload> findWkUploadByUpTime(Calendar upTime, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findWkUploadById
	 *
	 */
	public WkUpload findWkUploadById(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findWkUploadById
	 *
	 */
	public WkUpload findWkUploadById(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findWkUploadByType
	 *
	 */
	public Set<WkUpload> findWkUploadByType(Integer type) throws DataAccessException;

	/**
	 * JPQL Query - findWkUploadByType
	 *
	 */
	public Set<WkUpload> findWkUploadByType(Integer type, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findWkUploadByPrimaryKey
	 *
	 */
	public WkUpload findWkUploadByPrimaryKey(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findWkUploadByPrimaryKey
	 *
	 */
	public WkUpload findWkUploadByPrimaryKey(Integer id_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findWkUploadByDescriptionContaining
	 *
	 */
	public Set<WkUpload> findWkUploadByDescriptionContaining(String description_1) throws DataAccessException;

	/**
	 * JPQL Query - findWkUploadByDescriptionContaining
	 *
	 */
	public Set<WkUpload> findWkUploadByDescriptionContaining(String description_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findWkUploadByName
	 *
	 */
	public Set<WkUpload> findWkUploadByName(String name_1) throws DataAccessException;

	/**
	 * JPQL Query - findWkUploadByName
	 *
	 */
	public Set<WkUpload> findWkUploadByName(String name_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findWkUploadByUrlContaining
	 *
	 */
	public Set<WkUpload> findWkUploadByUrlContaining(String url_1) throws DataAccessException;

	/**
	 * JPQL Query - findWkUploadByUrlContaining
	 *
	 */
	public Set<WkUpload> findWkUploadByUrlContaining(String url_1, int startResult, int maxRows) throws DataAccessException;

}