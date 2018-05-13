package net.xidlims.dao;

import java.util.Calendar;
import java.util.Set;

import net.xidlims.domain.Message;
import net.xidlims.domain.TCourseSiteUser;
import net.xidlims.domain.TMessage;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage Message entities.
 * 
 */
public interface MessageDAO extends JpaDao<Message> {

	/**
	 * JPQL Query - findMessageByCreateTime
	 *
	 */
	public Set<Message> findMessageByCreateTime(java.util.Calendar createTime) throws DataAccessException;

	/**
	 * JPQL Query - findMessageByCreateTime
	 *
	 */
	public Set<Message> findMessageByCreateTime(Calendar createTime, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findMessageByReceiveCpartyid
	 *
	 */
	public Set<Message> findMessageByReceiveCpartyid(Integer receiveCpartyid) throws DataAccessException;

	/**
	 * JPQL Query - findMessageByReceiveCpartyid
	 *
	 */
	public Set<Message> findMessageByReceiveCpartyid(Integer receiveCpartyid, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findMessageBySendCparty
	 *
	 */
	public Set<Message> findMessageBySendCparty(String sendCparty) throws DataAccessException;

	/**
	 * JPQL Query - findMessageBySendCparty
	 *
	 */
	public Set<Message> findMessageBySendCparty(String sendCparty, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findMessageBySendUserContaining
	 *
	 */
	public Set<Message> findMessageBySendUserContaining(String sendUser) throws DataAccessException;

	/**
	 * JPQL Query - findMessageBySendUserContaining
	 *
	 */
	public Set<Message> findMessageBySendUserContaining(String sendUser, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findMessageByCond
	 *
	 */
	public Set<Message> findMessageByCond(Integer cond) throws DataAccessException;

	/**
	 * JPQL Query - findMessageByCond
	 *
	 */
	public Set<Message> findMessageByCond(Integer cond, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findMessageByAuthId
	 *
	 */
	public Set<Message> findMessageByAuthId(Integer authId) throws DataAccessException;

	/**
	 * JPQL Query - findMessageByAuthId
	 *
	 */
	public Set<Message> findMessageByAuthId(Integer authId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findMessageByContent
	 *
	 */
	public Set<Message> findMessageByContent(String content) throws DataAccessException;

	/**
	 * JPQL Query - findMessageByContent
	 *
	 */
	public Set<Message> findMessageByContent(String content, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findMessageByTitleContaining
	 *
	 */
	public Set<Message> findMessageByTitleContaining(String title) throws DataAccessException;

	/**
	 * JPQL Query - findMessageByTitleContaining
	 *
	 */
	public Set<Message> findMessageByTitleContaining(String title, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findMessageByMessageState
	 *
	 */
	public Set<Message> findMessageByMessageState(Integer messageState) throws DataAccessException;

	/**
	 * JPQL Query - findMessageByMessageState
	 *
	 */
	public Set<Message> findMessageByMessageState(Integer messageState, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findMessageByContentContaining
	 *
	 */
	public Set<Message> findMessageByContentContaining(String content_1) throws DataAccessException;

	/**
	 * JPQL Query - findMessageByContentContaining
	 *
	 */
	public Set<Message> findMessageByContentContaining(String content_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findMessageByTitle
	 *
	 */
	public Set<Message> findMessageByTitle(String title_1) throws DataAccessException;

	/**
	 * JPQL Query - findMessageByTitle
	 *
	 */
	public Set<Message> findMessageByTitle(String title_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllMessages
	 *
	 */
	public Set<Message> findAllMessages() throws DataAccessException;

	/**
	 * JPQL Query - findAllMessages
	 *
	 */
	public Set<Message> findAllMessages(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findMessageByCreateTimeAfter
	 *
	 */
	public Set<Message> findMessageByCreateTimeAfter(java.util.Calendar createTime_1) throws DataAccessException;

	/**
	 * JPQL Query - findMessageByCreateTimeAfter
	 *
	 */
	public Set<Message> findMessageByCreateTimeAfter(Calendar createTime_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findMessageByCreateTimeBefore
	 *
	 */
	public Set<Message> findMessageByCreateTimeBefore(java.util.Calendar createTime_2) throws DataAccessException;

	/**
	 * JPQL Query - findMessageByCreateTimeBefore
	 *
	 */
	public Set<Message> findMessageByCreateTimeBefore(Calendar createTime_2, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findMessageBySendCpartyContaining
	 *
	 */
	public Set<Message> findMessageBySendCpartyContaining(String sendCparty_1) throws DataAccessException;

	/**
	 * JPQL Query - findMessageBySendCpartyContaining
	 *
	 */
	public Set<Message> findMessageBySendCpartyContaining(String sendCparty_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findMessageBySendUser
	 *
	 */
	public Set<Message> findMessageBySendUser(String sendUser_1) throws DataAccessException;

	/**
	 * JPQL Query - findMessageBySendUser
	 *
	 */
	public Set<Message> findMessageBySendUser(String sendUser_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findMessageById
	 *
	 */
	public Message findMessageById(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findMessageById
	 *
	 */
	public Message findMessageById(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findMessageByPrimaryKey
	 *
	 */
	public Message findMessageByPrimaryKey(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findMessageByPrimaryKey
	 *
	 */
	public Message findMessageByPrimaryKey(Integer id_1, int startResult, int maxRows) throws DataAccessException;
	
	//新增username字段
	
	/**
	 * JPQL Query - findMessageByUsername
	 *
	 */
	public Set<Message> findMessageByUsername(String username_1) throws DataAccessException;

	/**
	 * JPQL Query - findMessageByUsername
	 *
	 */
	public Set<Message> findMessageByUsername(String username_1, int startResult, int maxRows) throws DataAccessException;
	
	/*************************
	 * 功能：根据tMessage和tCourseSiteUser查找相对应的Message
	 * 作者：张佳鸣
	 * 时间：2017-10-16
	 *************************/ 
	public Message findMessageByTMessageAndTCourseSiteUser(TMessage tMessage,TCourseSiteUser tCourseSiteUser);

}