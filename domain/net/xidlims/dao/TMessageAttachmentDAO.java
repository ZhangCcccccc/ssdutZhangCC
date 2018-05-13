package net.xidlims.dao;

import java.util.Set;

import net.xidlims.domain.TMessageAttachment;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage TMessageAttachment entities.
 * 
 */
public interface TMessageAttachmentDAO extends JpaDao<TMessageAttachment> {

	/**
	 * JPQL Query - findAllTMessageAttachments
	 *
	 */
	public Set<TMessageAttachment> findAllTMessageAttachments() throws DataAccessException;

	/**
	 * JPQL Query - findAllTMessageAttachments
	 *
	 */
	public Set<TMessageAttachment> findAllTMessageAttachments(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTMessageAttachmentByPrimaryKey
	 *
	 */
	public TMessageAttachment findTMessageAttachmentByPrimaryKey(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findTMessageAttachmentByPrimaryKey
	 *
	 */
	public TMessageAttachment findTMessageAttachmentByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTMessageAttachmentByPath
	 *
	 */
	public Set<TMessageAttachment> findTMessageAttachmentByPath(String path) throws DataAccessException;

	/**
	 * JPQL Query - findTMessageAttachmentByPath
	 *
	 */
	public Set<TMessageAttachment> findTMessageAttachmentByPath(String path, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTMessageAttachmentByPathContaining
	 *
	 */
	public Set<TMessageAttachment> findTMessageAttachmentByPathContaining(String path_1) throws DataAccessException;

	/**
	 * JPQL Query - findTMessageAttachmentByPathContaining
	 *
	 */
	public Set<TMessageAttachment> findTMessageAttachmentByPathContaining(String path_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTMessageAttachmentById
	 *
	 */
	public TMessageAttachment findTMessageAttachmentById(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findTMessageAttachmentById
	 *
	 */
	public TMessageAttachment findTMessageAttachmentById(Integer id_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTMessageAttachmentByMessageId
	 *
	 */
	public Set<TMessageAttachment> findTMessageAttachmentByMessageId(Integer messageId) throws DataAccessException;

	/**
	 * JPQL Query - findTMessageAttachmentByMessageId
	 *
	 */
	public Set<TMessageAttachment> findTMessageAttachmentByMessageId(Integer messageId, int startResult, int maxRows) throws DataAccessException;

}