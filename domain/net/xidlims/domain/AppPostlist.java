package net.xidlims.domain;

import java.io.Serializable;

import java.lang.StringBuilder;

import java.util.Calendar;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.codehaus.jackson.annotate.JsonIgnore;

import javax.xml.bind.annotation.*;

import javax.persistence.*;

/**
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "findAllAppPostlists", query = "select myAppPostlist from AppPostlist myAppPostlist"),
		@NamedQuery(name = "findAppPostlistByContent", query = "select myAppPostlist from AppPostlist myAppPostlist where myAppPostlist.content = ?1"),
		@NamedQuery(name = "findAppPostlistByContentContaining", query = "select myAppPostlist from AppPostlist myAppPostlist where myAppPostlist.content like ?1"),
		@NamedQuery(name = "findAppPostlistById", query = "select myAppPostlist from AppPostlist myAppPostlist where myAppPostlist.id = ?1"),
		@NamedQuery(name = "findAppPostlistByImagelist", query = "select myAppPostlist from AppPostlist myAppPostlist where myAppPostlist.imagelist = ?1"),
		@NamedQuery(name = "findAppPostlistByImagelistContaining", query = "select myAppPostlist from AppPostlist myAppPostlist where myAppPostlist.imagelist like ?1"),
		@NamedQuery(name = "findAppPostlistByIsstick", query = "select myAppPostlist from AppPostlist myAppPostlist where myAppPostlist.isstick = ?1"),
		@NamedQuery(name = "findAppPostlistByPrimaryKey", query = "select myAppPostlist from AppPostlist myAppPostlist where myAppPostlist.id = ?1"),
		@NamedQuery(name = "findAppPostlistByState", query = "select myAppPostlist from AppPostlist myAppPostlist where myAppPostlist.state = ?1"),
		@NamedQuery(name = "findAppPostlistByTime", query = "select myAppPostlist from AppPostlist myAppPostlist where myAppPostlist.time = ?1"),
		@NamedQuery(name = "findAppPostlistByTitle", query = "select myAppPostlist from AppPostlist myAppPostlist where myAppPostlist.title = ?1"),
		@NamedQuery(name = "findAppPostlistByTitleContaining", query = "select myAppPostlist from AppPostlist myAppPostlist where myAppPostlist.title like ?1"),
		@NamedQuery(name = "findAppPostlistByType", query = "select myAppPostlist from AppPostlist myAppPostlist where myAppPostlist.type = ?1") })
@Table(catalog = "xidlims", name = "app_postlist")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "AppPostlist")
@XmlRootElement(namespace = "xidlims/net/xidlims/domain")
public class AppPostlist implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * APP�ҵİ༶ģ������
	 * 
	 */

	@Column(name = "id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@XmlElement
	Integer id;
	/**
	 * ���ӱ���
	 * 
	 */

	@Column(name = "title")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String title;
	/**
	 * ����״̬��0��ʾδ����1��ʾ�Ѷ���2��ʾ�и��£�3��ʾ�������
	 * 
	 */

	@Column(name = "state")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer state;
	/**
	 * �Ƿ��ö�
	 * 
	 */

	@Column(name = "isstick")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer isstick;
	/**
	 * ��������
	 * 
	 */

	@Column(name = "content")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String content;
	/**
	 * ����ʱ��
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "time")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar time;
	/**
	 */

	@Column(name = "imagelist")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String imagelist;
	/**
	 * 1Ϊ���ӣ�2Ϊ���棬3Ϊ���?4Ϊ���
	 * 
	 */

	@Column(name = "type")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer type;

	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "group_id", referencedColumnName = "id") })
	@XmlTransient
	AppGroup appGroup;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "sponsor", referencedColumnName = "username") })
	@XmlTransient
	User user;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "class_id", referencedColumnName = "class_number") })
	@XmlTransient
	SchoolClasses schoolClasses;
	/**
	 */
	@OneToMany(mappedBy = "appPostlist", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.AppPostReply> appPostReplies;
	/**
	 */
	@OneToMany(mappedBy = "appPostlist", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	@OrderBy("uploadTime asc")
	java.util.Set<net.xidlims.domain.AppPostImages> appPostImageses;
	/**
	 */
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(catalog = "xidlims", name = "app_usercollectpost", joinColumns = { @JoinColumn(name = "id", referencedColumnName = "id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "username", referencedColumnName = "username", nullable = false, updatable = false) })
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.User> userOfCollection;
	/**
	 */
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(catalog = "xidlims", name = "app_userlikepost", joinColumns = { @JoinColumn(name = "id", referencedColumnName = "id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "username", referencedColumnName = "username", nullable = false, updatable = false) })
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.User> likedUser;
	/**
	 */
	@OneToMany(mappedBy = "appPostlist", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.AppPostStatus> appPostStatuses;

	/**
	 * APP�ҵİ༶ģ������
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * APP�ҵİ༶ģ������
	 * 
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * ���ӱ���
	 * 
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * ���ӱ���
	 * 
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * ����״̬��0��ʾδ����1��ʾ�Ѷ���2��ʾ�и��£�3��ʾ�������
	 * 
	 */
	public void setState(Integer state) {
		this.state = state;
	}

	/**
	 * ����״̬��0��ʾδ����1��ʾ�Ѷ���2��ʾ�и��£�3��ʾ�������
	 * 
	 */
	public Integer getState() {
		return this.state;
	}

	/**
	 * �Ƿ��ö�
	 * 
	 */
	public void setIsstick(Integer isstick) {
		this.isstick = isstick;
	}

	/**
	 * �Ƿ��ö�
	 * 
	 */
	public Integer getIsstick() {
		return this.isstick;
	}

	/**
	 * ��������
	 * 
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * ��������
	 * 
	 */
	public String getContent() {
		return this.content;
	}

	/**
	 * ����ʱ��
	 * 
	 */
	public void setTime(Calendar time) {
		this.time = time;
	}

	/**
	 * ����ʱ��
	 * 
	 */
	public Calendar getTime() {
		return this.time;
	}

	/**
	 */
	public void setImagelist(String imagelist) {
		this.imagelist = imagelist;
	}

	/**
	 */
	public String getImagelist() {
		return this.imagelist;
	}

	/**
	 * 1Ϊ���ӣ�2Ϊ���棬3Ϊ���?4Ϊ���
	 * 
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * 1Ϊ���ӣ�2Ϊ���棬3Ϊ���?4Ϊ���
	 * 
	 */
	public Integer getType() {
		return this.type;
	}

	/**
	 */
	public void setAppGroup(AppGroup appGroup) {
		this.appGroup = appGroup;
	}

	/**
	 */
	@JsonIgnore
	public AppGroup getAppGroup() {
		return appGroup;
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
	public void setSchoolClasses(SchoolClasses schoolClasses) {
		this.schoolClasses = schoolClasses;
	}

	/**
	 */
	@JsonIgnore
	public SchoolClasses getSchoolClasses() {
		return schoolClasses;
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
	public void setAppPostImageses(Set<AppPostImages> appPostImageses) {
		this.appPostImageses = appPostImageses;
	}

	/**
	 */
	@JsonIgnore
	public Set<AppPostImages> getAppPostImageses() {
		if (appPostImageses == null) {
			appPostImageses = new java.util.LinkedHashSet<net.xidlims.domain.AppPostImages>();
		}
		return appPostImageses;
	}

	public Set<User> getUserOfCollection() {
		if(userOfCollection == null){
			userOfCollection = new java.util.LinkedHashSet<net.xidlims.domain.User>();
		}
		return userOfCollection;
	}

	public void setUserOfCollection(Set<User> userOfCollection) {
		this.userOfCollection = userOfCollection;
	}

	public Set<User> getLikedUser() {
		if(likedUser == null){
			likedUser = new java.util.LinkedHashSet<net.xidlims.domain.User>();
		}
		return likedUser;
	}

	public void setLikedUser(java.util.Set<net.xidlims.domain.User> likedUser) {
		this.likedUser = likedUser;
	}
	
	/**
	 */
	public void setAppPostStatuses(Set<AppPostStatus> appPostStatuses) {
		this.appPostStatuses = appPostStatuses;
	}

	/**
	 */
	@JsonIgnore
	public Set<AppPostStatus> getAppPostStatuses() {
		if (appPostStatuses == null) {
			appPostStatuses = new java.util.LinkedHashSet<net.xidlims.domain.AppPostStatus>();
		}
		return appPostStatuses;
	}

	/**
	 */
	public AppPostlist() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(AppPostlist that) {
		setId(that.getId());
		setTitle(that.getTitle());
		setState(that.getState());
		setIsstick(that.getIsstick());
		setContent(that.getContent());
		setTime(that.getTime());
		setImagelist(that.getImagelist());
		setType(that.getType());
		setAppGroup(that.getAppGroup());
		setUser(that.getUser());
		setSchoolClasses(that.getSchoolClasses());
		setAppPostReplies(new java.util.LinkedHashSet<net.xidlims.domain.AppPostReply>(that.getAppPostReplies()));
		setAppPostImageses(new java.util.LinkedHashSet<net.xidlims.domain.AppPostImages>(that.getAppPostImageses()));
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("id=[").append(id).append("] ");
		buffer.append("title=[").append(title).append("] ");
		buffer.append("state=[").append(state).append("] ");
		buffer.append("isstick=[").append(isstick).append("] ");
		buffer.append("content=[").append(content).append("] ");
		buffer.append("time=[").append(time).append("] ");
		buffer.append("imagelist=[").append(imagelist).append("] ");
		buffer.append("type=[").append(type).append("] ");

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
		if (!(obj instanceof AppPostlist))
			return false;
		AppPostlist equalCheck = (AppPostlist) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
