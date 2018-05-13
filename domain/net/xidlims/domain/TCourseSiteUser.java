package net.xidlims.domain;

import java.io.Serializable;

import java.lang.StringBuilder;



import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import javax.xml.bind.annotation.*;

import javax.persistence.*;

/**
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "findAllTCourseSiteUsers", query = "select myTCourseSiteUser from TCourseSiteUser myTCourseSiteUser"),
		@NamedQuery(name = "findTCourseSiteUserById", query = "select myTCourseSiteUser from TCourseSiteUser myTCourseSiteUser where myTCourseSiteUser.id = ?1"),
		//@NamedQuery(name = "findTCourseSiteUserByPermission", query = "select myTCourseSiteUser from TCourseSiteUser myTCourseSiteUser where myTCourseSiteUser.permission = ?1"),
		@NamedQuery(name = "findTCourseSiteUserByPrimaryKey", query = "select myTCourseSiteUser from TCourseSiteUser myTCourseSiteUser where myTCourseSiteUser.id = ?1") })
@Table(catalog = "xidlims", name = "t_course_site_user")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "TCourseSiteUser")
public class TCourseSiteUser implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 */

	@Column(name = "id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	@XmlElement
	Integer id;
	
	@Column(name = "role")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer role;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "site_id", referencedColumnName = "id", nullable = false) })
	@XmlTransient
	TCourseSite TCourseSite;
	
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "permission", referencedColumnName = "id", nullable = false) })
	@XmlTransient
	Authority authority;
	
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "username", referencedColumnName = "username", nullable = false) })
	@XmlTransient
	User user;
	
	@Column(name = "group_id")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer groupId;
	
	/**
	 */
	@Column(name = "increment")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer increment;
	
	/**
	 */
	public void setIncrement(Integer increment) {
		this.increment = increment;
	}

	/**
	 */
	public Integer getIncrement() {
		return this.increment;
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

	public void setRole(Integer role) {
		this.role = role;
	}

	public Integer getRole() {
		return this.role;
	}
	
	public void setgroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public Integer getgroupId() {
		return this.groupId;
	}
	/**
	 */
	public void setTCourseSite(TCourseSite TCourseSite) {
		this.TCourseSite = TCourseSite;
	}

	/**
	 */
	public TCourseSite getTCourseSite() {
		return TCourseSite;
	}

	/**
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 */
	public User getUser() {
		return user;
	}
	
	/**
	 */
	public void setAuthority(Authority authority) {
		this.authority = authority;
	}

	/**
	 */
	public Authority getAuthority() {
		return authority;
	}

	/**
	 */
	public TCourseSiteUser() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(TCourseSiteUser that) {
		setId(that.getId());
		//setPermission(that.getPermission());
		setTCourseSite(that.getTCourseSite());
		setUser(that.getUser());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("id=[").append(id).append("] ");
		//buffer.append("permission=[").append(permission).append("] ");
		buffer.append("user=[").append(user.getUsername()).append("]");
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
		if (!(obj instanceof TCourseSiteUser))
			return false;
		TCourseSiteUser equalCheck = (TCourseSiteUser) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
