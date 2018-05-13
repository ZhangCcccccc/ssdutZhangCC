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
		@NamedQuery(name = "findAllAppGroups", query = "select myAppGroup from AppGroup myAppGroup"),
		@NamedQuery(name = "findAppGroupById", query = "select myAppGroup from AppGroup myAppGroup where myAppGroup.id = ?1"),
		@NamedQuery(name = "findAppGroupByName", query = "select myAppGroup from AppGroup myAppGroup where myAppGroup.name = ?1"),
		@NamedQuery(name = "findAppGroupByNameContaining", query = "select myAppGroup from AppGroup myAppGroup where myAppGroup.name like ?1"),
		@NamedQuery(name = "findAppGroupByPrimaryKey", query = "select myAppGroup from AppGroup myAppGroup where myAppGroup.id = ?1") })
@Table(catalog = "xidlims", name = "app_group")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "AppGroup")
@XmlRootElement(namespace = "xidlims/net/xidlims/domain")
public class AppGroup implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * app�ҵ�С��
	 * 
	 */

	@Column(name = "id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@XmlElement
	Integer id;
	/**
	 * С�����
	 * 
	 */

	@Column(name = "name", length = 40)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String name;

	/**
	 */
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(catalog = "xidlims", name = "app_group_user", joinColumns = { @JoinColumn(name = "id", referencedColumnName = "id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "username", referencedColumnName = "username", nullable = false, updatable = false) })
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.User> users;
	/**
	 */
	@OneToMany(mappedBy = "appGroup", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.AppPostlist> appPostlists;

	/**
	 * app�ҵ�С��
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * app�ҵ�С��
	 * 
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * С�����
	 * 
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * С�����
	 * 
	 */
	public String getName() {
		return this.name;
	}

	/**
	 */
	public void setUsers(Set<User> users) {
		this.users = users;
	}

	/**
	 */
	@JsonIgnore
	public Set<User> getUsers() {
		if (users == null) {
			users = new java.util.LinkedHashSet<net.xidlims.domain.User>();
		}
		return users;
	}

	/**
	 */
	public void setAppPostlists(Set<AppPostlist> appPostlists) {
		this.appPostlists = appPostlists;
	}

	/**
	 */
	@JsonIgnore
	public Set<AppPostlist> getAppPostlists() {
		if (appPostlists == null) {
			appPostlists = new java.util.LinkedHashSet<net.xidlims.domain.AppPostlist>();
		}
		return appPostlists;
	}

	/**
	 */
	public AppGroup() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(AppGroup that) {
		setId(that.getId());
		setName(that.getName());
		setUsers(new java.util.LinkedHashSet<net.xidlims.domain.User>(that.getUsers()));
		setAppPostlists(new java.util.LinkedHashSet<net.xidlims.domain.AppPostlist>(that.getAppPostlists()));
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("id=[").append(id).append("] ");
		buffer.append("name=[").append(name).append("] ");

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
		if (!(obj instanceof AppGroup))
			return false;
		AppGroup equalCheck = (AppGroup) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
