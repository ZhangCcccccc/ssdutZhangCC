package net.xidlims.domain;

import java.io.Serializable;

import java.lang.StringBuilder;

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
		@NamedQuery(name = "findAllAppClouddickDownloadFiles", query = "select myAppClouddickDownloadFile from AppClouddickDownloadFile myAppClouddickDownloadFile"),
		@NamedQuery(name = "findAppClouddickDownloadFileByFilename", query = "select myAppClouddickDownloadFile from AppClouddickDownloadFile myAppClouddickDownloadFile where myAppClouddickDownloadFile.filename = ?1"),
		@NamedQuery(name = "findAppClouddickDownloadFileByFilenameContaining", query = "select myAppClouddickDownloadFile from AppClouddickDownloadFile myAppClouddickDownloadFile where myAppClouddickDownloadFile.filename like ?1"),
		@NamedQuery(name = "findAppClouddickDownloadFileById", query = "select myAppClouddickDownloadFile from AppClouddickDownloadFile myAppClouddickDownloadFile where myAppClouddickDownloadFile.id = ?1"),
		@NamedQuery(name = "findAppClouddickDownloadFileByLocation", query = "select myAppClouddickDownloadFile from AppClouddickDownloadFile myAppClouddickDownloadFile where myAppClouddickDownloadFile.location = ?1"),
		@NamedQuery(name = "findAppClouddickDownloadFileByLocationContaining", query = "select myAppClouddickDownloadFile from AppClouddickDownloadFile myAppClouddickDownloadFile where myAppClouddickDownloadFile.location like ?1"),
		@NamedQuery(name = "findAppClouddickDownloadFileByPrimaryKey", query = "select myAppClouddickDownloadFile from AppClouddickDownloadFile myAppClouddickDownloadFile where myAppClouddickDownloadFile.id = ?1") })
@Table(catalog = "xidlims", name = "app_clouddick_download_file")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "AppClouddickDownloadFile")
public class AppClouddickDownloadFile implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * �ļ�id
	 * 
	 */

	@Column(name = "id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@XmlElement
	Integer id;
	/**
	 * �ļ���
	 * 
	 */

	@Column(name = "filename", length = 30, nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String filename;
	/**
	 * �ļ���ַ
	 * 
	 */

	@Column(name = "location")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String location;

	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "fromfolder", referencedColumnName = "id", nullable = false) })
	@XmlTransient
	AppClouddickDownloadFolder appClouddickDownloadFolder;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "users", referencedColumnName = "username") })
	@XmlTransient
	User user;

	/**
	 * �ļ�id
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * �ļ�id
	 * 
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * �ļ���
	 * 
	 */
	public void setFilename(String filename) {
		this.filename = filename;
	}

	/**
	 * �ļ���
	 * 
	 */
	public String getFilename() {
		return this.filename;
	}

	/**
	 * �ļ���ַ
	 * 
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * �ļ���ַ
	 * 
	 */
	public String getLocation() {
		return this.location;
	}

	/**
	 */
	public void setAppClouddickDownloadFolder(AppClouddickDownloadFolder appClouddickDownloadFolder) {
		this.appClouddickDownloadFolder = appClouddickDownloadFolder;
	}

	/**
	 */
	@JsonIgnore
	public AppClouddickDownloadFolder getAppClouddickDownloadFolder() {
		return appClouddickDownloadFolder;
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
	public AppClouddickDownloadFile() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(AppClouddickDownloadFile that) {
		setId(that.getId());
		setFilename(that.getFilename());
		setLocation(that.getLocation());
		setAppClouddickDownloadFolder(that.getAppClouddickDownloadFolder());
		setUser(that.getUser());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("id=[").append(id).append("] ");
		buffer.append("filename=[").append(filename).append("] ");
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
		if (!(obj instanceof AppClouddickDownloadFile))
			return false;
		AppClouddickDownloadFile equalCheck = (AppClouddickDownloadFile) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
