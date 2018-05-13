package net.xidlims.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.Message;
import net.xidlims.domain.TCourseSiteUser;
import net.xidlims.domain.TMessage;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage Message entities.
 * 
 */
@Repository("MessageDAO")
@Transactional
public class MessageDAOImpl extends AbstractJpaDao<Message> implements
		MessageDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { Message.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new MessageDAOImpl
	 *
	 */
	public MessageDAOImpl() {
		super();
	}

	/**
	 * Get the entity manager that manages persistence unit 
	 *
	 */
	public EntityManager getEntityManager() {
		return entityManager;
	}

	/**
	 * Returns the set of entity classes managed by this DAO.
	 *
	 */
	public Set<Class<?>> getTypes() {
		return dataTypes;
	}

	/**
	 * JPQL Query - findMessageByCreateTime
	 *
	 */
	@Transactional
	public Set<Message> findMessageByCreateTime(java.util.Calendar createTime) throws DataAccessException {

		return findMessageByCreateTime(createTime, -1, -1);
	}

	/**
	 * JPQL Query - findMessageByCreateTime
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Message> findMessageByCreateTime(java.util.Calendar createTime, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findMessageByCreateTime", startResult, maxRows, createTime);
		return new LinkedHashSet<Message>(query.getResultList());
	}

	/**
	 * JPQL Query - findMessageByReceiveCpartyid
	 *
	 */
	@Transactional
	public Set<Message> findMessageByReceiveCpartyid(Integer receiveCpartyid) throws DataAccessException {

		return findMessageByReceiveCpartyid(receiveCpartyid, -1, -1);
	}

	/**
	 * JPQL Query - findMessageByReceiveCpartyid
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Message> findMessageByReceiveCpartyid(Integer receiveCpartyid, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findMessageByReceiveCpartyid", startResult, maxRows, receiveCpartyid);
		return new LinkedHashSet<Message>(query.getResultList());
	}

	/**
	 * JPQL Query - findMessageBySendCparty
	 *
	 */
	@Transactional
	public Set<Message> findMessageBySendCparty(String sendCparty) throws DataAccessException {

		return findMessageBySendCparty(sendCparty, -1, -1);
	}

	/**
	 * JPQL Query - findMessageBySendCparty
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Message> findMessageBySendCparty(String sendCparty, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findMessageBySendCparty", startResult, maxRows, sendCparty);
		return new LinkedHashSet<Message>(query.getResultList());
	}

	/**
	 * JPQL Query - findMessageBySendUserContaining
	 *
	 */
	@Transactional
	public Set<Message> findMessageBySendUserContaining(String sendUser) throws DataAccessException {

		return findMessageBySendUserContaining(sendUser, -1, -1);
	}

	/**
	 * JPQL Query - findMessageBySendUserContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Message> findMessageBySendUserContaining(String sendUser, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findMessageBySendUserContaining", startResult, maxRows, sendUser);
		return new LinkedHashSet<Message>(query.getResultList());
	}

	/**
	 * JPQL Query - findMessageByCond
	 *
	 */
	@Transactional
	public Set<Message> findMessageByCond(Integer cond) throws DataAccessException {

		return findMessageByCond(cond, -1, -1);
	}

	/**
	 * JPQL Query - findMessageByCond
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Message> findMessageByCond(Integer cond, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findMessageByCond", startResult, maxRows, cond);
		return new LinkedHashSet<Message>(query.getResultList());
	}

	/**
	 * JPQL Query - findMessageByAuthId
	 *
	 */
	@Transactional
	public Set<Message> findMessageByAuthId(Integer authId) throws DataAccessException {

		return findMessageByAuthId(authId, -1, -1);
	}

	/**
	 * JPQL Query - findMessageByAuthId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Message> findMessageByAuthId(Integer authId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findMessageByAuthId", startResult, maxRows, authId);
		return new LinkedHashSet<Message>(query.getResultList());
	}

	/**
	 * JPQL Query - findMessageByContent
	 *
	 */
	@Transactional
	public Set<Message> findMessageByContent(String content) throws DataAccessException {

		return findMessageByContent(content, -1, -1);
	}

	/**
	 * JPQL Query - findMessageByContent
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Message> findMessageByContent(String content, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findMessageByContent", startResult, maxRows, content);
		return new LinkedHashSet<Message>(query.getResultList());
	}

	/**
	 * JPQL Query - findMessageByTitleContaining
	 *
	 */
	@Transactional
	public Set<Message> findMessageByTitleContaining(String title) throws DataAccessException {

		return findMessageByTitleContaining(title, -1, -1);
	}

	/**
	 * JPQL Query - findMessageByTitleContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Message> findMessageByTitleContaining(String title, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findMessageByTitleContaining", startResult, maxRows, title);
		return new LinkedHashSet<Message>(query.getResultList());
	}

	/**
	 * JPQL Query - findMessageByMessageState
	 *
	 */
	@Transactional
	public Set<Message> findMessageByMessageState(Integer messageState) throws DataAccessException {

		return findMessageByMessageState(messageState, -1, -1);
	}

	/**
	 * JPQL Query - findMessageByMessageState
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Message> findMessageByMessageState(Integer messageState, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findMessageByMessageState", startResult, maxRows, messageState);
		return new LinkedHashSet<Message>(query.getResultList());
	}

	/**
	 * JPQL Query - findMessageByContentContaining
	 *
	 */
	@Transactional
	public Set<Message> findMessageByContentContaining(String content) throws DataAccessException {

		return findMessageByContentContaining(content, -1, -1);
	}

	/**
	 * JPQL Query - findMessageByContentContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Message> findMessageByContentContaining(String content, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findMessageByContentContaining", startResult, maxRows, content);
		return new LinkedHashSet<Message>(query.getResultList());
	}

	/**
	 * JPQL Query - findMessageByTitle
	 *
	 */
	@Transactional
	public Set<Message> findMessageByTitle(String title) throws DataAccessException {

		return findMessageByTitle(title, -1, -1);
	}

	/**
	 * JPQL Query - findMessageByTitle
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Message> findMessageByTitle(String title, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findMessageByTitle", startResult, maxRows, title);
		return new LinkedHashSet<Message>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllMessages
	 *
	 */
	@Transactional
	public Set<Message> findAllMessages() throws DataAccessException {

		return findAllMessages(-1, -1);
	}

	/**
	 * JPQL Query - findAllMessages
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Message> findAllMessages(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllMessages", startResult, maxRows);
		return new LinkedHashSet<Message>(query.getResultList());
	}

	/**
	 * JPQL Query - findMessageByCreateTimeAfter
	 *
	 */
	@Transactional
	public Set<Message> findMessageByCreateTimeAfter(java.util.Calendar createTime) throws DataAccessException {

		return findMessageByCreateTimeAfter(createTime, -1, -1);
	}

	/**
	 * JPQL Query - findMessageByCreateTimeAfter
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Message> findMessageByCreateTimeAfter(java.util.Calendar createTime, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findMessageByCreateTimeAfter", startResult, maxRows, createTime);
		return new LinkedHashSet<Message>(query.getResultList());
	}

	/**
	 * JPQL Query - findMessageByCreateTimeBefore
	 *
	 */
	@Transactional
	public Set<Message> findMessageByCreateTimeBefore(java.util.Calendar createTime) throws DataAccessException {

		return findMessageByCreateTimeBefore(createTime, -1, -1);
	}

	/**
	 * JPQL Query - findMessageByCreateTimeBefore
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Message> findMessageByCreateTimeBefore(java.util.Calendar createTime, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findMessageByCreateTimeBefore", startResult, maxRows, createTime);
		return new LinkedHashSet<Message>(query.getResultList());
	}

	/**
	 * JPQL Query - findMessageBySendCpartyContaining
	 *
	 */
	@Transactional
	public Set<Message> findMessageBySendCpartyContaining(String sendCparty) throws DataAccessException {

		return findMessageBySendCpartyContaining(sendCparty, -1, -1);
	}

	/**
	 * JPQL Query - findMessageBySendCpartyContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Message> findMessageBySendCpartyContaining(String sendCparty, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findMessageBySendCpartyContaining", startResult, maxRows, sendCparty);
		return new LinkedHashSet<Message>(query.getResultList());
	}

	/**
	 * JPQL Query - findMessageBySendUser
	 *
	 */
	@Transactional
	public Set<Message> findMessageBySendUser(String sendUser) throws DataAccessException {

		return findMessageBySendUser(sendUser, -1, -1);
	}

	/**
	 * JPQL Query - findMessageBySendUser
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Message> findMessageBySendUser(String sendUser, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findMessageBySendUser", startResult, maxRows, sendUser);
		return new LinkedHashSet<Message>(query.getResultList());
	}

	/**
	 * JPQL Query - findMessageById
	 *
	 */
	@Transactional
	public Message findMessageById(Integer id) throws DataAccessException {

		return findMessageById(id, -1, -1);
	}

	/**
	 * JPQL Query - findMessageById
	 *
	 */

	@Transactional
	public Message findMessageById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findMessageById", startResult, maxRows, id);
			return (net.xidlims.domain.Message) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findMessageByPrimaryKey
	 *
	 */
	@Transactional
	public Message findMessageByPrimaryKey(Integer id) throws DataAccessException {

		return findMessageByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findMessageByPrimaryKey
	 *
	 */

	@Transactional
	public Message findMessageByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findMessageByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.Message) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(Message entity) {
		return true;
	}
	
	//新增username字段
	/**
	 * JPQL Query - findMessageByUsername
	 *
	 */
	@Transactional
	public Set<Message> findMessageByUsername(String username) throws DataAccessException {

		return findMessageByUsername(username, -1, -1);
	}

	/**
	 * JPQL Query - findMessageByUsername
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Message> findMessageByUsername(String username, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findMessageByUsername", startResult, maxRows, username);
		return new LinkedHashSet<Message>(query.getResultList());
	}
	
	/*************************
	 * 功能：根据tMessage和tCourseSiteUser查找相对应的Message
	 * 作者：张佳鸣
	 * 时间：2017-10-16
	 *************************/ 
	public Message findMessageByTMessageAndTCourseSiteUser(TMessage tMessage,TCourseSiteUser tCourseSiteUser){
		
		//建立查询
		String sql="select m from Message m where 1=1";
		//查询条件
		//发布者
		sql +=" and sendUser ='"+tMessage.getUser().getCname()+"'";
		//接收者
		sql +=" and username ='"+tCourseSiteUser.getUser().getUsername()+"'";
		//消息类型（通知、信息发送等选取tMessage主键id作为消息类型）
		sql +=" and type ='"+tMessage.getId()+"'";
		//执行查询并返回
		List<Message> list = this.executeQuery(sql, 0, -1);
		if(list!=null && !list.isEmpty()){
			return list.get(0);
		}else{
			return null;
		}
	}
}
