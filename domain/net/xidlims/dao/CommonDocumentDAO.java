package net.xidlims.dao;

import java.util.Set;

import net.xidlims.domain.CommonDocument;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage CommonDocument entities.
 * 
 */
public interface CommonDocumentDAO extends JpaDao<CommonDocument> {

	/**
	 * JPQL Query - findCommonDocumentByDocumentUrl
	 *
	 */
	public Set<CommonDocument> findCommonDocumentByDocumentUrl(String documentUrl) throws DataAccessException;

	/**
	 * JPQL Query - findCommonDocumentByDocumentUrl
	 *
	 */
	public Set<CommonDocument> findCommonDocumentByDocumentUrl(String documentUrl, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCommonDocumentByType
	 *
	 */
	public Set<CommonDocument> findCommonDocumentByType(Integer type) throws DataAccessException;

	/**
	 * JPQL Query - findCommonDocumentByType
	 *
	 */
	public Set<CommonDocument> findCommonDocumentByType(Integer type, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCommonDocumentByDocumentUrlContaining
	 *
	 */
	public Set<CommonDocument> findCommonDocumentByDocumentUrlContaining(String documentUrl_1) throws DataAccessException;

	/**
	 * JPQL Query - findCommonDocumentByDocumentUrlContaining
	 *
	 */
	public Set<CommonDocument> findCommonDocumentByDocumentUrlContaining(String documentUrl_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCommonDocumentById
	 *
	 */
	public CommonDocument findCommonDocumentById(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findCommonDocumentById
	 *
	 */
	public CommonDocument findCommonDocumentById(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCommonDocumentByDocumentNameContaining
	 *
	 */
	public Set<CommonDocument> findCommonDocumentByDocumentNameContaining(String documentName) throws DataAccessException;

	/**
	 * JPQL Query - findCommonDocumentByDocumentNameContaining
	 *
	 */
	public Set<CommonDocument> findCommonDocumentByDocumentNameContaining(String documentName, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCommonDocumentByPrimaryKey
	 *
	 */
	public CommonDocument findCommonDocumentByPrimaryKey(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findCommonDocumentByPrimaryKey
	 *
	 */
	public CommonDocument findCommonDocumentByPrimaryKey(Integer id_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllCommonDocuments
	 *
	 */
	public Set<CommonDocument> findAllCommonDocuments() throws DataAccessException;

	/**
	 * JPQL Query - findAllCommonDocuments
	 *
	 */
	public Set<CommonDocument> findAllCommonDocuments(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCommonDocumentByDocumentName
	 *
	 */
	public Set<CommonDocument> findCommonDocumentByDocumentName(String documentName_1) throws DataAccessException;

	/**
	 * JPQL Query - findCommonDocumentByDocumentName
	 *
	 */
	public Set<CommonDocument> findCommonDocumentByDocumentName(String documentName_1, int startResult, int maxRows) throws DataAccessException;

}