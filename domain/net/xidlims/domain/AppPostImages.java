package net.xidlims.domain;

import java.io.Serializable;

import java.lang.StringBuilder;

import java.util.Calendar;
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
		@NamedQuery(name = "findAllAppPostImagess", query = "select myAppPostImages from AppPostImages myAppPostImages"),
		@NamedQuery(name = "findAppPostImagesById", query = "select myAppPostImages from AppPostImages myAppPostImages where myAppPostImages.id = ?1"),
		@NamedQuery(name = "findAppPostImagesByImageurl", query = "select myAppPostImages from AppPostImages myAppPostImages where myAppPostImages.imageurl = ?1"),
		@NamedQuery(name = "findAppPostImagesByImageurlContaining", query = "select myAppPostImages from AppPostImages myAppPostImages where myAppPostImages.imageurl like ?1"),
		@NamedQuery(name = "findAppPostImagesByPrimaryKey", query = "select myAppPostImages from AppPostImages myAppPostImages where myAppPostImages.id = ?1"),
		@NamedQuery(name = "findAppPostImagesByUploadTime", query = "select myAppPostImages from AppPostImages myAppPostImages where myAppPostImages.uploadTime = ?1") })
@Table(catalog = "xidlims", name = "app_post_images")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "AppPostImages")
public class AppPostImages implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * app����ͼƬ��ű�
	 * 
	 */

	@Column(name = "id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@XmlElement
	Integer id;
	/**
	 * ͼƬ���·��
	 * 
	 */

	@Column(name = "imageurl")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String imageurl;
	/**
	 * �ϴ�ʱ��
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "upload_time")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar uploadTime;

	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "upload_user", referencedColumnName = "username") })
	@XmlTransient
	User user;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "post_id", referencedColumnName = "id") })
	@XmlTransient
	AppPostlist appPostlist;

	/**
	 * app����ͼƬ��ű�
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * app����ͼƬ��ű�
	 * 
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * ͼƬ���·��
	 * 
	 */
	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}

	/**
	 * ͼƬ���·��
	 * 
	 */
	public String getImageurl() {
		return this.imageurl;
	}

	/**
	 * �ϴ�ʱ��
	 * 
	 */
	public void setUploadTime(Calendar uploadTime) {
		this.uploadTime = uploadTime;
	}

	/**
	 * �ϴ�ʱ��
	 * 
	 */
	public Calendar getUploadTime() {
		return this.uploadTime;
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
	public AppPostImages() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(AppPostImages that) {
		setId(that.getId());
		setImageurl(that.getImageurl());
		setUploadTime(that.getUploadTime());
		setUser(that.getUser());
		setAppPostlist(that.getAppPostlist());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("id=[").append(id).append("] ");
		buffer.append("imageurl=[").append(imageurl).append("] ");
		buffer.append("uploadTime=[").append(uploadTime).append("] ");

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
		if (!(obj instanceof AppPostImages))
			return false;
		AppPostImages equalCheck = (AppPostImages) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
