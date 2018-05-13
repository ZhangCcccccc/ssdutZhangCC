package net.xidlims.domain;

import java.io.Serializable;

import java.lang.StringBuilder;

import java.util.Calendar;

import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import javax.xml.bind.annotation.*;

import javax.persistence.*;

/**
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "findAllWkUploads", query = "select myWkUpload from WkUpload myWkUpload"),
		@NamedQuery(name = "findWkUploadByDescription", query = "select myWkUpload from WkUpload myWkUpload where myWkUpload.description = ?1"),
		@NamedQuery(name = "findWkUploadByDescriptionContaining", query = "select myWkUpload from WkUpload myWkUpload where myWkUpload.description like ?1"),
		@NamedQuery(name = "findWkUploadById", query = "select myWkUpload from WkUpload myWkUpload where myWkUpload.id = ?1"),
		@NamedQuery(name = "findWkUploadByName", query = "select myWkUpload from WkUpload myWkUpload where myWkUpload.name = ?1"),
		@NamedQuery(name = "findWkUploadByNameContaining", query = "select myWkUpload from WkUpload myWkUpload where myWkUpload.name like ?1"),
		@NamedQuery(name = "findWkUploadByPrimaryKey", query = "select myWkUpload from WkUpload myWkUpload where myWkUpload.id = ?1"),
		@NamedQuery(name = "findWkUploadByType", query = "select myWkUpload from WkUpload myWkUpload where myWkUpload.type = ?1"),
		@NamedQuery(name = "findWkUploadByUpTime", query = "select myWkUpload from WkUpload myWkUpload where myWkUpload.upTime = ?1"),
		@NamedQuery(name = "findWkUploadByUrl", query = "select myWkUpload from WkUpload myWkUpload where myWkUpload.url = ?1"),
		@NamedQuery(name = "findWkUploadByUrlContaining", query = "select myWkUpload from WkUpload myWkUpload where myWkUpload.url like ?1") })
@Table(catalog = "xidlims", name = "wk_upload")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "WkUpload")
public class WkUpload implements Serializable {
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

	@Column(name = "name", length = 50)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String name;
	/**
	 * ·��
	 * 
	 */

	@Column(name = "url")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String url;
	/**
	 * ·��
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

	@Column(name = "description", length = 50)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String description;
	
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "folder_id", referencedColumnName = "id") })
	@XmlTransient
	WkFolder wkFolder;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "create_user", referencedColumnName = "username") })
	@XmlTransient
	User user;

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
	 * ·��
	 * 
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * ·��
	 * 
	 */
	public String getUrl() {
		return this.url;
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

	/**
	 */
	public Integer getType() {
		return this.type;
	}

	/**
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 */
	public String getDescription() {
		return this.description;
	}

	
	
	
	public WkFolder getWkFolder() {
		return wkFolder;
	}

	public void setWkFolder(WkFolder wkFolder) {
		this.wkFolder = wkFolder;
	}
	
	
	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	/**
	 */
	public WkUpload() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(WkUpload that) {
		setId(that.getId());
		setName(that.getName());
		setUrl(that.getUrl());
		setUpTime(that.getUpTime());
		setType(that.getType());
		setDescription(that.getDescription());
		setWkFolder(that.getWkFolder());
		setSize(that.getSize());
		setUser(that.getUser());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("id=[").append(id).append("] ");
		buffer.append("name=[").append(name).append("] ");
		buffer.append("url=[").append(url).append("] ");
		buffer.append("upTime=[").append(upTime).append("] ");
		buffer.append("type=[").append(type).append("] ");
		buffer.append("description=[").append(description).append("] ");

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
		if (!(obj instanceof WkUpload))
			return false;
		WkUpload equalCheck = (WkUpload) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
