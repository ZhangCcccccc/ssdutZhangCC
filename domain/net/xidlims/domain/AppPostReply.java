package net.xidlims.domain;

import java.io.Serializable;

import java.lang.StringBuilder;

import java.util.Calendar;
import java.util.LinkedHashSet;
import java.util.Set;

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
		@NamedQuery(name = "findAllAppPostReplys", query = "select myAppPostReply from AppPostReply myAppPostReply"),
		@NamedQuery(name = "findAppPostReplyByCollection", query = "select myAppPostReply from AppPostReply myAppPostReply where myAppPostReply.collection = ?1"),
		@NamedQuery(name = "findAppPostReplyByComment", query = "select myAppPostReply from AppPostReply myAppPostReply where myAppPostReply.comment = ?1"),
		@NamedQuery(name = "findAppPostReplyByCommentContaining", query = "select myAppPostReply from AppPostReply myAppPostReply where myAppPostReply.comment like ?1"),
		@NamedQuery(name = "findAppPostReplyById", query = "select myAppPostReply from AppPostReply myAppPostReply where myAppPostReply.id = ?1"),
		@NamedQuery(name = "findAppPostReplyByPrimaryKey", query = "select myAppPostReply from AppPostReply myAppPostReply where myAppPostReply.id = ?1"),
		@NamedQuery(name = "findAppPostReplyByTime", query = "select myAppPostReply from AppPostReply myAppPostReply where myAppPostReply.time = ?1"),
		@NamedQuery(name = "findAppPostReplyByUpvote", query = "select myAppPostReply from AppPostReply myAppPostReply where myAppPostReply.upvote = ?1") })
@Table(catalog = "xidlims", name = "app_post_reply")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "AppPostReply")
@XmlRootElement(namespace = "xidlims/net/xidlims/domain")
public class AppPostReply implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * app���ӻظ��ղر�
	 * 
	 */

	@Column(name = "id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@XmlElement
	Integer id;
	/**
	 * �ظ�ʱ��
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "time")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar time;
	/**
	 * �ظ�������
	 * 
	 */

	@Column(name = "comment")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String comment;
	/**
	 * �ղ�
	 * 
	 */

	@Column(name = "collection")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer collection;
	/**
	 * ����
	 * 
	 */

	@Column(name = "upvote")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer upvote;

	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "to_discussion_in_id", referencedColumnName = "id") })
	@XmlTransient
	AppPostlist appPostlist;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "to_response_id", referencedColumnName = "id") })
	@XmlTransient
	AppPostReply appPostReply;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "sponsor", referencedColumnName = "username") })
	@XmlTransient
	User user;
	/**
	 */
	@OneToMany(mappedBy = "appPostReply", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.AppPostReply> appPostReplies;

	/**
	 * app���ӻظ��ղر�
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * app���ӻظ��ղر�
	 * 
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * �ظ�ʱ��
	 * 
	 */
	public void setTime(Calendar time) {
		this.time = time;
	}

	/**
	 * �ظ�ʱ��
	 * 
	 */
	public Calendar getTime() {
		return this.time;
	}

	/**
	 * �ظ�������
	 * 
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * �ظ�������
	 * 
	 */
	public String getComment() {
		return this.comment;
	}

	/**
	 * �ղ�
	 * 
	 */
	public void setCollection(Integer collection) {
		this.collection = collection;
	}

	/**
	 * �ղ�
	 * 
	 */
	public Integer getCollection() {
		return this.collection;
	}

	/**
	 * ����
	 * 
	 */
	public void setUpvote(Integer upvote) {
		this.upvote = upvote;
	}

	/**
	 * ����
	 * 
	 */
	public Integer getUpvote() {
		return this.upvote;
	}

	/**
	 */
	public void setAppPostlist(AppPostlist appPostlist) {
		this.appPostlist = appPostlist;
	}

	/**
	 */
	@JsonIgnore
	public AppPostlist getAppPostlist() {
		return appPostlist;
	}

	/**
	 */
	public void setAppPostReply(AppPostReply appPostReply) {
		this.appPostReply = appPostReply;
	}

	/**
	 */
	@JsonIgnore
	public AppPostReply getAppPostReply() {
		return appPostReply;
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
	public void setAppPostReplies(Set<AppPostReply> appPostReplies) {
		this.appPostReplies = appPostReplies;
	}

	/**
	 */
	@JsonIgnore
	public Set<AppPostReply> getAppPostReplies() {
		if (appPostReplies == null) {
			appPostReplies = new java.util.LinkedHashSet<net.xidlims.domain.AppPostReply>();
		}
		return appPostReplies;
	}

	/**
	 */
	public AppPostReply() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(AppPostReply that) {
		setId(that.getId());
		setTime(that.getTime());
		setComment(that.getComment());
		setCollection(that.getCollection());
		setUpvote(that.getUpvote());
		setAppPostlist(that.getAppPostlist());
		setAppPostReply(that.getAppPostReply());
		setUser(that.getUser());
		setAppPostReplies(new java.util.LinkedHashSet<net.xidlims.domain.AppPostReply>(that.getAppPostReplies()));
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("id=[").append(id).append("] ");
		buffer.append("time=[").append(time).append("] ");
		buffer.append("comment=[").append(comment).append("] ");
		buffer.append("collection=[").append(collection).append("] ");
		buffer.append("upvote=[").append(upvote).append("] ");

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
		if (!(obj instanceof AppPostReply))
			return false;
		AppPostReply equalCheck = (AppPostReply) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
