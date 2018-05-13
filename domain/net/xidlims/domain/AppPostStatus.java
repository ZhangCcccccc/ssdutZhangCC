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
		@NamedQuery(name = "findAllAppPostStatuss", query = "select myAppPostStatus from AppPostStatus myAppPostStatus"),
		@NamedQuery(name = "findAppPostStatusById", query = "select myAppPostStatus from AppPostStatus myAppPostStatus where myAppPostStatus.id = ?1"),
		@NamedQuery(name = "findAppPostStatusByPrimaryKey", query = "select myAppPostStatus from AppPostStatus myAppPostStatus where myAppPostStatus.id = ?1"),
		@NamedQuery(name = "findAppPostStatusByType", query = "select myAppPostStatus from AppPostStatus myAppPostStatus where myAppPostStatus.type = ?1") })
@Table(catalog = "xidlims", name = "app_post_status")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "AppPostStatus")
public class AppPostStatus implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 */

	@Column(name = "id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@XmlElement
	Integer id;
	/**
	 * 2��ʾ�Ѷ���3��ʾ�и���
	 * 
	 */

	@Column(name = "type")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer type;

	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "username", referencedColumnName = "username") })
	@XmlTransient
	User user;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "post_id", referencedColumnName = "id") })
	@XmlTransient
	AppPostlist appPostlist;

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
	 * 2��ʾ�Ѷ���3��ʾ�и���
	 * 
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * 2��ʾ�Ѷ���3��ʾ�и���
	 * 
	 */
	public Integer getType() {
		return this.type;
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
	public AppPostStatus() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(AppPostStatus that) {
		setId(that.getId());
		setType(that.getType());
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
		if (!(obj instanceof AppPostStatus))
			return false;
		AppPostStatus equalCheck = (AppPostStatus) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
