package net.xidlims.domain;

import java.io.Serializable;

import java.lang.StringBuilder;

import java.util.Calendar;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import javax.xml.bind.annotation.*;

import javax.persistence.*;

/**
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "findAllMessages", query = "select myMessage from Message myMessage"),
		@NamedQuery(name = "findMessageByAuthId", query = "select myMessage from Message myMessage where myMessage.authId = ?1"),
		@NamedQuery(name = "findMessageByCond", query = "select myMessage from Message myMessage where myMessage.cond = ?1"),
		@NamedQuery(name = "findMessageByContent", query = "select myMessage from Message myMessage where myMessage.content = ?1"),
		@NamedQuery(name = "findMessageByContentContaining", query = "select myMessage from Message myMessage where myMessage.content like ?1"),
		@NamedQuery(name = "findMessageByCreateTime", query = "select myMessage from Message myMessage where myMessage.createTime = ?1"),
		@NamedQuery(name = "findMessageByCreateTimeAfter", query = "select myMessage from Message myMessage where myMessage.createTime > ?1"),
		@NamedQuery(name = "findMessageByCreateTimeBefore", query = "select myMessage from Message myMessage where myMessage.createTime < ?1"),
		@NamedQuery(name = "findMessageById", query = "select myMessage from Message myMessage where myMessage.id = ?1"),
		@NamedQuery(name = "findMessageByMessageState", query = "select myMessage from Message myMessage where myMessage.messageState = ?1"),
		@NamedQuery(name = "findMessageByPrimaryKey", query = "select myMessage from Message myMessage where myMessage.id = ?1"),
		@NamedQuery(name = "findMessageByReceiveCpartyid", query = "select myMessage from Message myMessage where myMessage.receiveCpartyid = ?1"),
		@NamedQuery(name = "findMessageBySendCparty", query = "select myMessage from Message myMessage where myMessage.sendCparty = ?1"),
		@NamedQuery(name = "findMessageBySendCpartyContaining", query = "select myMessage from Message myMessage where myMessage.sendCparty like ?1"),
		@NamedQuery(name = "findMessageBySendUser", query = "select myMessage from Message myMessage where myMessage.sendUser = ?1"),
		@NamedQuery(name = "findMessageBySendUserContaining", query = "select myMessage from Message myMessage where myMessage.sendUser like ?1"),
		//新增username字段
		@NamedQuery(name = "findMessageByUsername", query = "select myMessage from Message myMessage where myMessage.username = ?1"),
		@NamedQuery(name = "findMessageByTitle", query = "select myMessage from Message myMessage where myMessage.title = ?1"),
		@NamedQuery(name = "findMessageByTitleContaining", query = "select myMessage from Message myMessage where myMessage.title like ?1") })
		
@Table(catalog = "xidlims", name = "message")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "Message")
public class Message implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * �������ı�
	 * 
	 */

	@Column(name = "id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	@GeneratedValue(strategy = GenerationType.AUTO)
	Integer id;
	/**
	 * ����״�� 0Ϊ���� 1Ϊһ��
	 * 
	 */

	@Column(name = "cond")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer cond;
	/**
	 * ��Ϣ����
	 * 
	 */

	@Column(name = "title", length = 255)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String title;
	/**
	 * ����ʱ��
	 * 
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "create_time")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar createTime;
	/**
	 * ��Ϣ����
	 * 
	 */

	@Column(name = "content")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String content;
	/**
	 * ��Ϣ״̬
	 * 
	 */

	@Column(name = "message_state")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer messageState;
	/**
	 * ������Ϣ��
	 * 
	 */

	@Column(name = "send_user", length = 11)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String sendUser;
	/**
	 * ��Ϣ�˵�λ���
	 * 
	 */

	@Column(name = "send_cparty", length = 50)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String sendCparty;
	/**
	 * Ȩ��id
	 * 
	 */

	@Column(name = "auth_id")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer authId;
	/**
	 * ���յ�λ
	 * 
	 */

	@Column(name = "receive_cpartyid")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer receiveCpartyid;
	
	//新增username字段
		@Column(name = "username", length = 40)
		@Basic(fetch = FetchType.EAGER)
		@XmlElement
		String username;
		 
	/**
	 * ��Ϣ����
	 * 
	*/
	@Column(name = "type")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	/**
	 * �������ı�
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * �������ı�
	 * 
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * ����״�� 0Ϊ���� 1Ϊһ��
	 * 
	 */
	public void setCond(Integer cond) {
		this.cond = cond;
	}

	/**
	 * ����״�� 0Ϊ���� 1Ϊһ��
	 * 
	 */
	public Integer getCond() {
		return this.cond;
	}

	/**
	 * ��Ϣ����
	 * 
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * ��Ϣ����
	 * 
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * ����ʱ��
	 * 
	 */
	public void setCreateTime(Calendar createTime) {
		this.createTime = createTime;
	}

	/**
	 * ����ʱ��
	 * 
	 */
	public Calendar getCreateTime() {
		return this.createTime;
	}

	/**
	 * ��Ϣ����
	 * 
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * ��Ϣ����
	 * 
	 */
	public String getContent() {
		return this.content;
	}

	/**
	 * ��Ϣ״̬
	 * 
	 */
	public void setMessageState(Integer messageState) {
		this.messageState = messageState;
	}

	/**
	 * ��Ϣ״̬
	 * 
	 */
	public Integer getMessageState() {
		return this.messageState;
	}

	/**
	 * ������Ϣ��
	 * 
	 */
	public void setSendUser(String sendUser) {
		this.sendUser = sendUser;
	}

	/**
	 * ������Ϣ��
	 * 
	 */
	public String getSendUser() {
		return this.sendUser;
	}

	/**
	 * ��Ϣ�˵�λ���
	 * 
	 */
	public void setSendCparty(String sendCparty) {
		this.sendCparty = sendCparty;
	}

	/**
	 * ��Ϣ�˵�λ���
	 * 
	 */
	public String getSendCparty() {
		return this.sendCparty;
	}

	/**
	 * Ȩ��id
	 * 
	 */
	public void setAuthId(Integer authId) {
		this.authId = authId;
	}

	/**
	 * Ȩ��id
	 * 
	 */
	public Integer getAuthId() {
		return this.authId;
	}

	/**
	 * ���յ�λ
	 * 
	 */
	public void setReceiveCpartyid(Integer receiveCpartyid) {
		this.receiveCpartyid = receiveCpartyid;
	}

	/**
	 * ���յ�λ
	 * 
	 */
	public Integer getReceiveCpartyid() {
		return this.receiveCpartyid;
	}

	
	//新增username字段 
		 /**
		 */
		public void setUsername(String username) {
			this.username = username;
		}

		/**
		 */
		public String getUsername() {
			return this.username;
		}
	/**
	 */
	public Message() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(Message that) {
		setId(that.getId());
		setCond(that.getCond());
		setTitle(that.getTitle());
		setCreateTime(that.getCreateTime());
		setContent(that.getContent());
		setMessageState(that.getMessageState());
		setSendUser(that.getSendUser());
		setSendCparty(that.getSendCparty());
		setAuthId(that.getAuthId());
		setReceiveCpartyid(that.getReceiveCpartyid());
		//新增username字段
		setUsername(that.getUsername());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("id=[").append(id).append("] ");
		buffer.append("cond=[").append(cond).append("] ");
		buffer.append("title=[").append(title).append("] ");
		buffer.append("createTime=[").append(createTime).append("] ");
		buffer.append("content=[").append(content).append("] ");
		buffer.append("messageState=[").append(messageState).append("] ");
		buffer.append("sendUser=[").append(sendUser).append("] ");
		buffer.append("sendCparty=[").append(sendCparty).append("] ");
		buffer.append("authId=[").append(authId).append("] ");
		buffer.append("receiveCpartyid=[").append(receiveCpartyid).append("] ");
		//新增username字段
		buffer.append("username=[").append(username).append("] ");

		return buffer.toString();
	}

	/**
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((id == null) ? 0 : id.hashCode()));
		return result;
	}

	/**
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof Message))
			return false;
		Message equalCheck = (Message) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
