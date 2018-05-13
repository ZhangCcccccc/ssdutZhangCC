package net.xidlims.dao;

import net.xidlims.domain.CmsDocument;

import java.util.Calendar;
import java.util.Set;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage CmsDocument entities.
 * 
 */
public interface CmsDocumentDAO extends JpaDao<CmsDocument> {

	/**
	 * JPQL Query - findCmsDocumentByName
	 *
	 */
	public Set<CmsDocument> findCmsDocumentByName(String name) throws DataAccessException;

	/**
	 * JPQL Query - findCmsDocumentByName
	 *
	 */
	public Set<CmsDocument> findCmsDocumentByName(String name, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCmsDocumentByUrlContaining
	 *
	 */
	public Set<CmsDocument> findCmsDocumentByUrlContaining(String url) throws DataAccessException;

	/**
	 * JPQL Query - findCmsDocumentByUrlContaining
	 *
	 */
	public Set<CmsDocument> findCmsDocumentByUrlContaining(String url, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCmsDocumentByUrl
	 *
	 */
	public Set<CmsDocument> findCmsDocumentByUrl(String url_1) throws DataAccessException;

	/**
	 * JPQL Query - findCmsDocumentByUrl
	 *
	 */
	public Set<CmsDocument> findCmsDocumentByUrl(String url_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCmsDocumentByProfileContaining
	 *
	 */
	public Set<CmsDocument> findCmsDocumentByProfileContaining(String profile) throws DataAccessException;

	/**
	 * JPQL Query - findCmsDocumentByProfileContaining
	 *
	 */
	public Set<CmsDocument> findCmsDocumentByProfileContaining(String profile, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllCmsDocuments
	 *
	 */
	public Set<CmsDocument> findAllCmsDocuments() throws DataAccessException;

	/**
	 * JPQL Query - findAllCmsDocuments
	 *
	 */
	public Set<CmsDocument> findAllCmsDocuments(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCmsDocumentByPrimaryKey
	 *
	 */
	public CmsDocument findCmsDocumentByPrimaryKey(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findCmsDocumentByPrimaryKey
	 *
	 */
	public CmsDocument findCmsDocumentByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCmsDocumentById
	 *
	 */
	public CmsDocument findCmsDocumentById(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findCmsDocumentById
	 *
	 */
	public CmsDocument findCmsDocumentById(Integer id_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCmsDocumentByTag
	 *
	 */
	public Set<CmsDocument> findCmsDocumentByTag(Integer tag) throws DataAccessException;

	/**
	 * JPQL Query - findCmsDocumentByTag
	 *
	 */
	public Set<CmsDocument> findCmsDocumentByTag(Integer tag, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCmsDocumentByCreateTime
	 *
	 */
	public Set<CmsDocument> findCmsDocumentByCreateTime(java.util.Calendar createTime) throws DataAccessException;

	/**
	 * JPQL Query - findCmsDocumentByCreateTime
	 *
	 */
	public Set<CmsDocument> findCmsDocumentByCreateTime(Calendar createTime, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCmsDocumentByProfile
	 *
	 */
	public Set<CmsDocument> findCmsDocumentByProfile(String profile_1) throws DataAccessException;

	/**
	 * JPQL Query - findCmsDocumentByProfile
	 *
	 */
	public Set<CmsDocument> findCmsDocumentByProfile(String profile_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCmsDocumentByNameContaining
	 *
	 */
	public Set<CmsDocument> findCmsDocumentByNameContaining(String name_1) throws DataAccessException;

	/**
	 * JPQL Query - findCmsDocumentByNameContaining
	 *
	 */
	public Set<CmsDocument> findCmsDocumentByNameContaining(String name_1, int startResult, int maxRows) throws DataAccessException;

}