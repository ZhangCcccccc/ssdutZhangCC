package net.xidlims.domain;

import java.io.Serializable;

import java.lang.StringBuilder;

import java.util.Calendar;

import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.codehaus.jackson.annotate.JsonIgnore;

import javax.xml.bind.annotation.*;

import javax.persistence.*;

/**
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "findAllTMessages", query = "select myTMessage from TMessage myTMessage"),
		@NamedQuery(name = "findTMessageByContent", query = "select myTMessage from TMessage myTMessage where myTMessage.content = ?1"),
		@NamedQuery(name = "findTMessageById", query = "select myTMessage from TMessage myTMessage where myTMessage.id = ?1"),
		@NamedQuery(name = "findTMessageByPrimaryKey", query = "select myTMessage from TMessage myTMessage where myTMessage.id = ?1"),
		@NamedQuery(name = "findTMessageByPublish", query = "select myTMessage from TMessage myTMessage where myTMessage.publish = ?1"),
		@NamedQuery(name = "findTMessageByReleaseTime", query = "select myTMessage from TMessage myTMessage where myTMessage.releaseTime = ?1"),
		@NamedQuery(name = "findTMessageBySummary", query = "select myTMessage from TMessage myTMessage where myTMessage.summary = ?1"),
		@NamedQuery(name = "findTMessageBySummaryContaining", query = "select myTMessage from TMessage myTMessage where myTMessage.summary like ?1"),
		@NamedQuery(name = "findTMessageByTitle", query = "select myTMessage from TMessage myTMessage where myTMessage.title = ?1"),
		@NamedQuery(name = "findTMessageByTitleContaining", query = "select myTMessage from TMessage myTMessage where myTMessage.title like ?1") })
@Table(catalog = "xidlims", name = "t_message")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "TMessage")
public class TMessage implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ֪ͨ�����
	 * 
	 */

	@Column(name = "id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	@XmlElement
	Integer id;
	/**
	 * ����
	 * 
	 */

	@Column(name = "title")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String title;
	/**
	 * ��Ҫ
	 * 
	 */

	@Column(name = "summary")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String summary;
	/**
	 * �Ƿ񷢲���0δ������1�ѷ�����
	 * 
	 */

	@Column(name = "publish")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer publish;
	/**
	 * ����ʱ��
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "release_time")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar releaseTime;
	/**
	 * ����
	 * 
	 */

	@Column(name = "content", columnDefinition = "TEXT")
	@Basic(fetch = FetchType.EAGER)
	@Lob
	@XmlElement
	String content;
	/**
	 * ����
	 * 
	 */
	@Column(name = "type")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer type;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "site_id", referencedColumnName = "id") })
	@XmlTransient
	TCourseSite TCourseSite;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "publisher", referencedColumnName = "username") })
	@XmlTransient
	User user;
	

	/**
	 * ֪ͨ�����
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * ֪ͨ�����
	 * 
	 */
	public Integer getId() {
		return this.id;
	}
	/**
	 * ֪ͨ�����
	 * 
	 */
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * ����
	 * 
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * ����
	 * 
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * ��Ҫ
	 * 
	 */
	public void setSummary(String summary) {
		this.summary = summary;
	}

	/**
	 * ��Ҫ
	 * 
	 */
	public String getSummary() {
		return this.summary;
	}

	/**
	 * �Ƿ񷢲���0δ������1�ѷ�����
	 * 
	 */
	public void setPublish(Integer publish) {
		this.publish = publish;
	}

	/**
	 * �Ƿ񷢲���0δ������1�ѷ�����
	 * 
	 */
	public Integer getPublish() {
		return this.publish;
	}

	/**
	 * ����ʱ��
	 * 
	 */
	public void setReleaseTime(Calendar releaseTime) {
		this.releaseTime = releaseTime;
	}

	/**
	 * ����ʱ��
	 * 
	 */
	public Calendar getReleaseTime() {
		return this.releaseTime;
	}

	/**
	 * ����
	 * 
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * ����
	 * 
	 */
	public String getContent() {
		return this.content;
	}

	/**
	 */
	public void setTCourseSite(TCourseSite TCourseSite) {
		this.TCourseSite = TCourseSite;
	}

	/**
	 */
	@JsonIgnore
	public TCourseSite getTCourseSite() {
		return TCourseSite;
	}

	/**
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 */
	@JsonIgnore
	public User getUser() {
		return user;
	}




	/**
	 */
	public TMessage() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(TMessage that) {
		setId(that.getId());
		setTitle(that.getTitle());
		setSummary(that.getSummary());
		setPublish(that.getPublish());
		setReleaseTime(that.getReleaseTime());
		setContent(that.getContent());
		setTCourseSite(that.getTCourseSite());
		setUser(that.getUser());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("id=[").append(id).append("] ");
		buffer.append("title=[").append(title).append("] ");
		buffer.append("summary=[").append(summary).append("] ");
		buffer.append("publish=[").append(publish).append("] ");
		buffer.append("releaseTime=[").append(releaseTime).append("] ");
		buffer.append("content=[").append(content).append("] ");

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
		if (!(obj instanceof TMessage))
			return false;
		TMessage equalCheck = (TMessage) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
