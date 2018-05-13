package net.xidlims.dao;

import java.util.Calendar;
import java.util.Set;

import net.xidlims.domain.AppPostReply;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage AppPostReply entities.
 * 
 */
public interface AppPostReplyDAO extends JpaDao<AppPostReply> {

	/**
	 * JPQL Query - findAppPostReplyByTime
	 *
	 */
	public Set<AppPostReply> findAppPostReplyByTime(java.util.Calendar time) throws DataAccessException;

	/**
	 * JPQL Query - findAppPostReplyByTime
	 *
	 */
	public Set<AppPostReply> findAppPostReplyByTime(Calendar time, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAppPostReplyByCommentContaining
	 *
	 */
	public Set<AppPostReply> findAppPostReplyByCommentContaining(String comment) throws DataAccessException;

	/**
	 * JPQL Query - findAppPostReplyByCommentContaining
	 *
	 */
	public Set<AppPostReply> findAppPostReplyByCommentContaining(String comment, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllAppPostReplys
	 *
	 */
	public Set<AppPostReply> findAllAppPostReplys() throws DataAccessException;

	/**
	 * JPQL Query - findAllAppPostReplys
	 *
	 */
	public Set<AppPostReply> findAllAppPostReplys(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAppPostReplyByCollection
	 *
	 */
	public Set<AppPostReply> findAppPostReplyByCollection(Integer collection) throws DataAccessException;

	/**
	 * JPQL Query - findAppPostReplyByCollection
	 *
	 */
	public Set<AppPostReply> findAppPostReplyByCollection(Integer collection, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAppPostReplyByPrimaryKey
	 *
	 */
	public AppPostReply findAppPostReplyByPrimaryKey(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findAppPostReplyByPrimaryKey
	 *
	 */
	public AppPostReply findAppPostReplyByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAppPostReplyById
	 *
	 */
	public AppPostReply findAppPostReplyById(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findAppPostReplyById
	 *
	 */
	public AppPostReply findAppPostReplyById(Integer id_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAppPostReplyByComment
	 *
	 */
	public Set<AppPostReply> findAppPostReplyByComment(String comment_1) throws DataAccessException;

	/**
	 * JPQL Query - findAppPostReplyByComment
	 *
	 */
	public Set<AppPostReply> findAppPostReplyByComment(String comment_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAppPostReplyByUpvote
	 *
	 */
	public Set<AppPostReply> findAppPostReplyByUpvote(Integer upvote) throws DataAccessException;

	/**
	 * JPQL Query - findAppPostReplyByUpvote
	 *
	 */
	public Set<AppPostReply> findAppPostReplyByUpvote(Integer upvote, int startResult, int maxRows) throws DataAccessException;

}