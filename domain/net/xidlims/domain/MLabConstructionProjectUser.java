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
		@NamedQuery(name = "findAllMLabConstructionProjectUsers", query = "select myMLabConstructionProjectUser from MLabConstructionProjectUser myMLabConstructionProjectUser"),
		@NamedQuery(name = "findMLabConstructionProjectUserById", query = "select myMLabConstructionProjectUser from MLabConstructionProjectUser myMLabConstructionProjectUser where myMLabConstructionProjectUser.id = ?1"),
		@NamedQuery(name = "findMLabConstructionProjectUserByPrimaryKey", query = "select myMLabConstructionProjectUser from MLabConstructionProjectUser myMLabConstructionProjectUser where myMLabConstructionProjectUser.id = ?1") })
@Table(catalog = "xidlims", name = "m_lab_construction_project_user")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "MLabConstructionProjectUser")
public class MLabConstructionProjectUser implements Serializable {
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
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "lab_construction_project_id", referencedColumnName = "id", nullable = false) })
	@XmlTransient
	LabConstructionProject labConstructionProject;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "user_id", referencedColumnName = "username", nullable = false) })
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
	 */
	public void setLabConstructionProject(LabConstructionProject labConstructionProject) {
		this.labConstructionProject = labConstructionProject;
	}

	/**
	 */
	@JsonIgnore
	public LabConstructionProject getLabConstructionProject() {
		return labConstructionProject;
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
	public MLabConstructionProjectUser() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(MLabConstructionProjectUser that) {
		setId(that.getId());
		setLabConstructionProject(that.getLabConstructionProject());
		setUser(that.getUser());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("id=[").append(id).append("] ");
		buffer.append("labConstructionProject=[").append(labConstructionProject).append("] ");
		buffer.append("user=[").append(user).append("] ");

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
		if (!(obj instanceof MLabConstructionProjectUser))
			return false;
		MLabConstructionProjectUser equalCheck = (MLabConstructionProjectUser) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
