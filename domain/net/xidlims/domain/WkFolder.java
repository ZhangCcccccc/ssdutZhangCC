package net.xidlims.domain;

import java.io.Serializable;

import java.lang.StringBuilder;

import java.util.Calendar;

import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import org.codehaus.jackson.annotate.JsonIgnore;

import javax.xml.bind.annotation.*;

import javax.persistence.*;

/**
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "findAllWkFolders", query = "select myWkFolder from WkFolder myWkFolder"),
		@NamedQuery(name = "findWkFolderByCreateTime", query = "select myWkFolder from WkFolder myWkFolder where myWkFolder.createTime = ?1"),
		@NamedQuery(name = "findWkFolderById", query = "select myWkFolder from WkFolder myWkFolder where myWkFolder.id = ?1"),
		@NamedQuery(name = "findWkFolderByName", query = "select myWkFolder from WkFolder myWkFolder where myWkFolder.name = ?1"),
		@NamedQuery(name = "findWkFolderByNameContaining", query = "select myWkFolder from WkFolder myWkFolder where myWkFolder.name like ?1"),
		@NamedQuery(name = "findWkFolderByPrimaryKey", query = "select myWkFolder from WkFolder myWkFolder where myWkFolder.id = ?1"),
		@NamedQuery(name = "findWkFolderByUpdateTime", query = "select myWkFolder from WkFolder myWkFolder where myWkFolder.updateTime = ?1") })
@Table(catalog = "xidlims", name = "wk_folder")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "WkFolder")
@XmlRootElement(namespace = "xidlims/net/xidlims/domain")
public class WkFolder implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 */

	@Column(name = "id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	@XmlElement
	Integer id;
	/**
	 * ���
	 * 
	 */

	@Column(name = "name")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String name;
	/**
	 * ����ʱ��
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar createTime;
	/**
	 * ����ʱ��
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_time")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar updateTime;
	
	/**
	 */

	@Column(name = "type")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer type;
	
	/**
	 * ������Ƶ��·��
	 * 
	 */

	@Column(name = "videoUrl")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String videoUrl;
	
	/**
	 * ���
	 * 
	 */

	@Column(name = "qRCode_url")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String qRCodeUrl;

	public String getqRCodeUrl() {
		return qRCodeUrl;
	}

	public void setqRCodeUrl(String qRCodeUrl) {
		this.qRCodeUrl = qRCodeUrl;
	}

	public String getVideoUrl() {
		return videoUrl;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "create_user", referencedColumnName = "username") })
	@XmlTransient
	User user;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "parent", referencedColumnName = "id") })
	@XmlTransient
	WkFolder wkFolder;
	/**
	 */
	@OneToMany(mappedBy = "wkFolder", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	@OrderBy("id ASC")
	java.util.Set<net.xidlims.domain.WkFolder> wkFolders;
	/**
	 */
	@OneToMany(mappedBy = "wkFolder", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	@OrderBy("id ASC")
	java.util.Set<net.xidlims.domain.WkUpload> uploads;
	
	@OneToMany(mappedBy = "wkFolder", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.TAssignment> TAssignments;
	
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "chapter_id", referencedColumnName = "id") })
	@XmlTransient
	WkChapter WkChapter;
	
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "lesson_id", referencedColumnName = "id") })
	@XmlTransient
	WkLesson WkLesson;
	
	/**
	 */
	public void setTAssignments(Set<TAssignment> TAssignments) {
		this.TAssignments = TAssignments;
	}

	/**
	 */
	public Set<TAssignment> getTAssignments() {
		if (TAssignments == null) {
			TAssignments = new java.util.LinkedHashSet<net.xidlims.domain.TAssignment>();
		}
		return TAssignments;
	}

	/**
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 */
	public Integer getId() {
		return this.id;
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
	 * ����ʱ��
	 * 
	 */
	public void setUpdateTime(Calendar updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * ����ʱ��
	 * 
	 */
	public Calendar getUpdateTime() {
		return this.updateTime;
	}

	

	public WkChapter getWkChapter() {
		return WkChapter;
	}

	public void setWkChapter(WkChapter wkChapter) {
		WkChapter = wkChapter;
	}

	public WkLesson getWkLesson() {
		return WkLesson;
	}

	public void setWkLesson(WkLesson wkLesson) {
		WkLesson = wkLesson;
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
	public void setWkFolder(WkFolder wkFolder) {
		this.wkFolder = wkFolder;
	}

	/**
	 */
	@JsonIgnore
	public WkFolder getWkFolder() {
		return wkFolder;
	}

	/**
	 */
	public void setWkFolders(Set<WkFolder> wkFolders) {
		this.wkFolders = wkFolders;
	}

	/**
	 */
	@JsonIgnore
	public Set<WkFolder> getWkFolders() {
		if (wkFolders == null) {
			wkFolders = new java.util.LinkedHashSet<net.xidlims.domain.WkFolder>();
		}
		return wkFolders;
	}

	
	
	public java.util.Set<net.xidlims.domain.WkUpload> getUploads() {
		return uploads;
	}

	public void setUploads(java.util.Set<net.xidlims.domain.WkUpload> uploads) {
		this.uploads = uploads;
	}

	/**
	 */
	public WkFolder() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(WkFolder that) {
		setId(that.getId());
		setName(that.getName());
		setCreateTime(that.getCreateTime());
		setUpdateTime(that.getUpdateTime());
		setUser(that.getUser());
		setWkFolder(that.getWkFolder());
		setWkFolders(new java.util.LinkedHashSet<net.xidlims.domain.WkFolder>(that.getWkFolders()));
		setUploads(new java.util.LinkedHashSet<net.xidlims.domain.WkUpload>(that.getUploads()));
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("id=[").append(id).append("] ");
		buffer.append("name=[").append(name).append("] ");
		buffer.append("createTime=[").append(createTime).append("] ");
		buffer.append("updateTime=[").append(updateTime).append("] ");
		buffer.append("type=[").append(type).append("] ");
		buffer.append("videoUrl=[").append(videoUrl).append("] ");

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
		if (!(obj instanceof WkFolder))
			return false;
		WkFolder equalCheck = (WkFolder) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
