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
		@NamedQuery(name = "findAllTMessageUsers", query = "select myTMessageUser from TMessageUser myTMessageUser"),
		@NamedQuery(name = "findTMessageUserByCreateBy", query = "select myTMessageUser from TMessageUser myTMessageUser where myTMessageUser.createBy = ?1"),
		@NamedQuery(name = "findTMessageUserByCreateByContaining", query = "select myTMessageUser from TMessageUser myTMessageUser where myTMessageUser.createBy like ?1"),
		@NamedQuery(name = "findTMessageUserByCreateTime", query = "select myTMessageUser from TMessageUser myTMessageUser where myTMessageUser.createTime = ?1"),
		@NamedQuery(name = "findTMessageUserById", query = "select myTMessageUser from TMessageUser myTMessageUser where myTMessageUser.id = ?1"),
		@NamedQuery(name = "findTMessageUserByMessageId", query = "select myTMessageUser from TMessageUser myTMessageUser where myTMessageUser.messageId = ?1"),
		@NamedQuery(name = "findTMessageUserByPrimaryKey", query = "select myTMessageUser from TMessageUser myTMessageUser where myTMessageUser.id = ?1"),
		@NamedQuery(name = "findTMessageUserByUsername", query = "select myTMessageUser from TMessageUser myTMessageUser where myTMessageUser.username = ?1"),
		@NamedQuery(name = "findTMessageUserByUsernameContaining", query = "select myTMessageUser from TMessageUser myTMessageUser where myTMessageUser.username like ?1") })
@Table(catalog = "xidlims", name = "t_message_user")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "TMessageUser")
public class TMessageUser implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ��Ϣ���ͱ�
	 * 
	 */

	@Column(name = "id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	@XmlElement
	Integer id;
	/**
	 * ��Ϣ���
	 * 
	 */

	@Column(name = "message_id")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer messageId;
	/**
	 * ��Ϣ������
	 * 
	 */

	@Column(name = "username")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String username;
	/**
	 * ��Ϣ������
	 * 
	 */

	@Column(name = "create_by")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String createBy;
	/**
	 * ��Ϣ����ʱ��
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar createTime;
	/**
	 * ��Ϣ���
	 * 
	 */

	@Column(name = "is_read")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer isRead;
	/**
	 * ��Ϣ���ͱ�
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * ��Ϣ���ͱ�
	 * 
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * ��Ϣ���
	 * 
	 */
	public void setMessageId(Integer messageId) {
		this.messageId = messageId;
	}

	/**
	 * ��Ϣ���
	 * 
	 */
	public Integer getMessageId() {
		return this.messageId;
	}
	/**
	 * ��Ϣ���
	 * 
	 */
	public void setIsRead(Integer isRead) {
		this.isRead = isRead;
	}

	/**
	 * ��Ϣ���
	 * 
	 */
	public Integer isRead() {
		return this.isRead;
	}
	/**
	 * ��Ϣ������
	 * 
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * ��Ϣ������
	 * 
	 */
	public String getUsername() {
		return this.username;
	}

	/**
	 * ��Ϣ������
	 * 
	 */
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	/**
	 * ��Ϣ������
	 * 
	 */
	public String getCreateBy() {
		return this.createBy;
	}

	/**
	 * ��Ϣ����ʱ��
	 * 
	 */
	public void setCreateTime(Calendar createTime) {
		this.createTime = createTime;
	}

	/**
	 * ��Ϣ����ʱ��
	 * 
	 */
	public Calendar getCreateTime() {
		return this.createTime;
	}

	/**
	 */
	public TMessageUser() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(TMessageUser that) {
		setId(that.getId());
		setMessageId(that.getMessageId());
		setUsername(that.getUsername());
		setCreateBy(that.getCreateBy());
		setCreateTime(that.getCreateTime());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("id=[").append(id).append("] ");
		buffer.append("messageId=[").append(messageId).append("] ");
		buffer.append("username=[").append(username).append("] ");
		buffer.append("createBy=[").append(createBy).append("] ");
		buffer.append("createTime=[").append(createTime).append("] ");

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
		if (!(obj instanceof TMessageUser))
			return false;
		TMessageUser equalCheck = (TMessageUser) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
