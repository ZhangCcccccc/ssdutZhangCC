package net.xidlims.domain;

import java.io.Serializable;

import java.lang.StringBuilder;

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
		@NamedQuery(name = "findAllAppClouddickDownloadFolders", query = "select myAppClouddickDownloadFolder from AppClouddickDownloadFolder myAppClouddickDownloadFolder"),
		@NamedQuery(name = "findAppClouddickDownloadFolderByFoldername", query = "select myAppClouddickDownloadFolder from AppClouddickDownloadFolder myAppClouddickDownloadFolder where myAppClouddickDownloadFolder.foldername = ?1"),
		@NamedQuery(name = "findAppClouddickDownloadFolderByFoldernameContaining", query = "select myAppClouddickDownloadFolder from AppClouddickDownloadFolder myAppClouddickDownloadFolder where myAppClouddickDownloadFolder.foldername like ?1"),
		@NamedQuery(name = "findAppClouddickDownloadFolderById", query = "select myAppClouddickDownloadFolder from AppClouddickDownloadFolder myAppClouddickDownloadFolder where myAppClouddickDownloadFolder.id = ?1"),
		@NamedQuery(name = "findAppClouddickDownloadFolderByLocation", query = "select myAppClouddickDownloadFolder from AppClouddickDownloadFolder myAppClouddickDownloadFolder where myAppClouddickDownloadFolder.location = ?1"),
		@NamedQuery(name = "findAppClouddickDownloadFolderByLocationContaining", query = "select myAppClouddickDownloadFolder from AppClouddickDownloadFolder myAppClouddickDownloadFolder where myAppClouddickDownloadFolder.location like ?1"),
		@NamedQuery(name = "findAppClouddickDownloadFolderByPrimaryKey", query = "select myAppClouddickDownloadFolder from AppClouddickDownloadFolder myAppClouddickDownloadFolder where myAppClouddickDownloadFolder.id = ?1") })
@Table(catalog = "xidlims", name = "app_clouddick_download_folder")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "AppClouddickDownloadFolder")
@XmlRootElement(namespace = "xidlims/net/xidlims/domain")
public class AppClouddickDownloadFolder implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * �ļ���ID
	 * 
	 */

	@Column(name = "id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@XmlElement
	Integer id;
	/**
	 * �ļ������
	 * 
	 */

	@Column(name = "foldername", length = 20, nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String foldername;
	/**
	 * ��ַ
	 * 
	 */

	@Column(name = "location")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String location;

	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "owner", referencedColumnName = "username") })
	@XmlTransient
	User user;
	/**
	 */
	@OneToMany(mappedBy = "appClouddickDownloadFolder", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.AppClouddickDownloadFile> appClouddickDownloadFiles;

	/**
	 * �ļ���ID
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * �ļ���ID
	 * 
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * �ļ������
	 * 
	 */
	public void setFoldername(String foldername) {
		this.foldername = foldername;
	}

	/**
	 * �ļ������
	 * 
	 */
	public String getFoldername() {
		return this.foldername;
	}

	/**
	 * ��ַ
	 * 
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * ��ַ
	 * 
	 */
	public String getLocation() {
		return this.location;
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
	public void setAppClouddickDownloadFiles(Set<AppClouddickDownloadFile> appClouddickDownloadFiles) {
		this.appClouddickDownloadFiles = appClouddickDownloadFiles;
	}

	/**
	 */
	@JsonIgnore
	public Set<AppClouddickDownloadFile> getAppClouddickDownloadFiles() {
		if (appClouddickDownloadFiles == null) {
			appClouddickDownloadFiles = new java.util.LinkedHashSet<net.xidlims.domain.AppClouddickDownloadFile>();
		}
		return appClouddickDownloadFiles;
	}

	/**
	 */
	public AppClouddickDownloadFolder() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(AppClouddickDownloadFolder that) {
		setId(that.getId());
		setFoldername(that.getFoldername());
		setLocation(that.getLocation());
		setUser(that.getUser());
		setAppClouddickDownloadFiles(new java.util.LinkedHashSet<net.xidlims.domain.AppClouddickDownloadFile>(that.getAppClouddickDownloadFiles()));
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("id=[").append(id).append("] ");
		buffer.append("foldername=[").append(foldername).append("] ");
		buffer.append("location=[").append(location).append("] ");

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
		if (!(obj instanceof AppClouddickDownloadFolder))
			return false;
		AppClouddickDownloadFolder equalCheck = (AppClouddickDownloadFolder) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
