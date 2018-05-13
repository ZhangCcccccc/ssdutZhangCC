package net.xidlims.dao;

import java.util.Calendar;
import java.util.Set;

import net.xidlims.domain.TMessage;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage TMessage entities.
 * 
 */
public interface TMessageDAO extends JpaDao<TMessage> {

	/**
	 * JPQL Query - findTMessageBySummary
	 *
	 */
	public Set<TMessage> findTMessageBySummary(String summary) throws DataAccessException;

	/**
	 * JPQL Query - findTMessageBySummary
	 *
	 */
	public Set<TMessage> findTMessageBySummary(String summary, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllTMessages
	 *
	 */
	public Set<TMessage> findAllTMessages() throws DataAccessException;

	/**
	 * JPQL Query - findAllTMessages
	 *
	 */
	public Set<TMessage> findAllTMessages(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTMessageBySummaryContaining
	 *
	 */
	public Set<TMessage> findTMessageBySummaryContaining(String summary_1) throws DataAccessException;

	/**
	 * JPQL Query - findTMessageBySummaryContaining
	 *
	 */
	public Set<TMessage> findTMessageBySummaryContaining(String summary_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTMessageByContent
	 *
	 */
	public Set<TMessage> findTMessageByContent(String content) throws DataAccessException;

	/**
	 * JPQL Query - findTMessageByContent
	 *
	 */
	public Set<TMessage> findTMessageByContent(String content, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTMessageByTitleContaining
	 *
	 */
	public Set<TMessage> findTMessageByTitleContaining(String title) throws DataAccessException;

	/**
	 * JPQL Query - findTMessageByTitleContaining
	 *
	 */
	public Set<TMessage> findTMessageByTitleContaining(String title, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTMessageByReleaseTime
	 *
	 */
	public Set<TMessage> findTMessageByReleaseTime(java.util.Calendar releaseTime) throws DataAccessException;

	/**
	 * JPQL Query - findTMessageByReleaseTime
	 *
	 */
	public Set<TMessage> findTMessageByReleaseTime(Calendar releaseTime, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTMessageByPrimaryKey
	 *
	 */
	public TMessage findTMessageByPrimaryKey(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findTMessageByPrimaryKey
	 *
	 */
	public TMessage findTMessageByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTMessageById
	 *
	 */
	public TMessage findTMessageById(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findTMessageById
	 *
	 */
	public TMessage findTMessageById(Integer id_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTMessageByPublish
	 *
	 */
	public Set<TMessage> findTMessageByPublish(Integer publish) throws DataAccessException;

	/**
	 * JPQL Query - findTMessageByPublish
	 *
	 */
	public Set<TMessage> findTMessageByPublish(Integer publish, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTMessageByTitle
	 *
	 */
	public Set<TMessage> findTMessageByTitle(String title_1) throws DataAccessException;

	/**
	 * JPQL Query - findTMessageByTitle
	 *
	 */
	public Set<TMessage> findTMessageByTitle(String title_1, int startResult, int maxRows) throws DataAccessException;

}