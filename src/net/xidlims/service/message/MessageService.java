package net.xidlims.service.message;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import net.xidlims.domain.Message;
import net.xidlims.domain.TCourseSiteUser;
import net.xidlims.domain.TMessage;

/**
 * Spring service that handles CRUD requests for Message entities
 * 
 */
public interface MessageService {

	/**
	 */
	public Message findMessageByPrimaryKey(Integer id);

	/**
	 * Delete an existing Message entity
	 * 
	 */
	public void deleteMessage(Message message);

	/**
	 * Return a count of all Message entity
	 * 
	 */
	public Integer countMessages();

	/**
	 * Return all Message entity
	 * 
	 */
	public List<Message> findAllMessages(Integer startResult, Integer maxRows);

	/**
	 * Load an existing Message entity
	 * 
	 */
	public Set<Message> loadMessages();

	/**
	 * Save an existing Message entity
	 * 
	 */
	public void saveMessage(Message message_1);
	
	
	/*************************
	 * 功能：我的消息列表
	 * 作者：贺子龙
	 * 时间：2015-09-15 20:51:04
	 *************************/
	public List<Message> findMessageBySome(Message message,int currpage, int pageSize,HttpServletRequest request);
	/*************************
	 * 功能：我的消息个数
	 * 作者：贺子龙
	 * 时间：2015-09-16 10:31:58
	 *************************/
	public Integer countmessage(Message message,HttpServletRequest request);
	
	/*************************
	 * 功能：我的消息个数
	 * 作者：贺子龙
	 * 时间：2015-09-16 10:31:58
	 *************************/ 
	public Integer countmessage( );
	
	/*************************
	 * 功能：根据tMessage保存相对应的Message
	 * 作者：张佳鸣
	 * 时间：2017-10-13
	 *************************/ 
	public void saveMessageByTMessage(TMessage tMessage,int tCourseSiteId);
	
	/*************************
	 * 功能：根据tMessage和tCourseSiteUser查找相对应的Message
	 * 作者：张佳鸣
	 * 时间：2017-10-16
	 *************************/ 
	public Message findMessageByTMessageAndTCourseSiteUser(TMessage tMessage,TCourseSiteUser tCourseSiteUser);
	
}