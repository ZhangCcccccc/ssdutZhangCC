package net.xidlims.domain;

import java.io.Serializable;

import java.lang.StringBuilder;
import java.util.Calendar;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import javax.xml.bind.annotation.*;

import javax.persistence.*;

/**
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "findAllTMessageAttachments", query = "select myTMessageAttachment from TMessageAttachment myTMessageAttachment"),
		@NamedQuery(name = "findTMessageAttachmentById", query = "select myTMessageAttachment from TMessageAttachment myTMessageAttachment where myTMessageAttachment.id = ?1"),
		@NamedQuery(name = "findTMessageAttachmentByMessageId", query = "select myTMessageAttachment from TMessageAttachment myTMessageAttachment where myTMessageAttachment.messageId = ?1"),
		@NamedQuery(name = "findTMessageAttachmentByPath", query = "select myTMessageAttachment from TMessageAttachment myTMessageAttachment where myTMessageAttachment.path = ?1"),
		@NamedQuery(name = "findTMessageAttachmentByPathContaining", query = "select myTMessageAttachment from TMessageAttachment myTMessageAttachment where myTMessageAttachment.path like ?1"),
		@NamedQuery(name = "findTMessageAttachmentByPrimaryKey", query = "select myTMessageAttachment from TMessageAttachment myTMessageAttachment where myTMessageAttachment.id = ?1") })
@Table(catalog = "xidlims", name = "t_message_attachment")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "TMessageAttachment")
public class TMessageAttachment implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ��Ϣ����
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
	@Column(name = "name", length = 50)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String name;
	/**
	 * ������ַ
	 * 
	 */
	@Column(name = "size")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String size;
	/**
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "upTime")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar upTime;
	/**
	 */

	@Column(name = "type")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer type;
	/**
	 */
	@Column(name = "message_id")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer messageId;
	/**
	 * ������ַ
	 * 
	 */

	@Column(name = "path")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String path;

	/**
	 * ��Ϣ����
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * ��Ϣ����
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
	 * ���
	 * 
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * ���
	 * 
	 */
	public String getName() {
		return this.name;
	}
	/**
	 */
	public void setUpTime(Calendar upTime) {
		this.upTime = upTime;
	}

	/**
	 */
	public Calendar getUpTime() {
		return this.upTime;
	}

	/**
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}
	/**
	 */
	public Integer getType() {
		return this.type;
	}

	/**
	 * ������ַ
	 * 
	 */
	
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * ������ַ
	 * 
	 */
	public String getPath() {
		return this.path;
	}

	/**
	 */
	public TMessageAttachment() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(TMessageAttachment that) {
		setId(that.getId());
		setMessageId(that.getMessageId());
		setPath(that.getPath());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("id=[").append(id).append("] ");
		buffer.append("messageId=[").append(messageId).append("] ");
		buffer.append("path=[").append(path).append("] ");

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
		if (!(obj instanceof TMessageAttachment))
			return false;
		TMessageAttachment equalCheck = (TMessageAttachment) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
