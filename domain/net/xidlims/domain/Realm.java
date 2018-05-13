package net.xidlims.domain;

import java.io.Serializable;

import java.lang.StringBuilder;

import java.util.LinkedHashSet;
import java.util.Set;

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
		@NamedQuery(name = "findAllRealms", query = "select myRealm from Realm myRealm"),
		@NamedQuery(name = "findRealmById", query = "select myRealm from Realm myRealm where myRealm.id = ?1"),
		@NamedQuery(name = "findRealmByName", query = "select myRealm from Realm myRealm where myRealm.name = ?1"),
		@NamedQuery(name = "findRealmByNameContaining", query = "select myRealm from Realm myRealm where myRealm.name like ?1"),
		@NamedQuery(name = "findRealmByPrimaryKey", query = "select myRealm from Realm myRealm where myRealm.id = ?1"),
		@NamedQuery(name = "findRealmByRealmType", query = "select myRealm from Realm myRealm where myRealm.realmType = ?1"),
		@NamedQuery(name = "findRealmByRealmTypeContaining", query = "select myRealm from Realm myRealm where myRealm.realmType like ?1") })
@Table(catalog = "xidlims", name = "realm")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "Realm")
@XmlRootElement(namespace = "xidlims/net/xidlims/domain")
public class Realm implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 */

	@Column(name = "id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	Integer id;
	/**
	 * ����Ȩ�ޣ�add���ӣ�deleteɾ��update�ģ�query��
	 * 
	 */

	@Column(name = "realm_type")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String realmType;
	/**
	 * ���
	 * 
	 */

	@Column(name = "name")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String name;

	/**
	 */
	@OneToMany(mappedBy = "realm", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.AuthorityMenu> authorityMenus;

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
	 * ����Ȩ�ޣ�add���ӣ�deleteɾ��update�ģ�query��
	 * 
	 */
	public void setRealmType(String realmType) {
		this.realmType = realmType;
	}

	/**
	 * ����Ȩ�ޣ�add���ӣ�deleteɾ��update�ģ�query��
	 * 
	 */
	public String getRealmType() {
		return this.realmType;
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
	 */
	public void setAuthorityMenus(Set<AuthorityMenu> authorityMenus) {
		this.authorityMenus = authorityMenus;
	}

	/**
	 */
	@JsonIgnore
	public Set<AuthorityMenu> getAuthorityMenus() {
		if (authorityMenus == null) {
			authorityMenus = new java.util.LinkedHashSet<net.xidlims.domain.AuthorityMenu>();
		}
		return authorityMenus;
	}

	/**
	 */
	public Realm() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(Realm that) {
		setId(that.getId());
		setRealmType(that.getRealmType());
		setName(that.getName());
		setAuthorityMenus(new java.util.LinkedHashSet<net.xidlims.domain.AuthorityMenu>(that.getAuthorityMenus()));
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("id=[").append(id).append("] ");
		buffer.append("realmType=[").append(realmType).append("] ");
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
		if (!(obj instanceof Realm))
			return false;
		Realm equalCheck = (Realm) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
