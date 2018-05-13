package net.xidlims.domain;

import java.io.Serializable;

import java.lang.StringBuilder;


import java.util.Set;

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
		@NamedQuery(name = "findAllTGradebooks", query = "select myTGradebook from TGradebook myTGradebook"),
		@NamedQuery(name = "findTGradebookById", query = "select myTGradebook from TGradebook myTGradebook where myTGradebook.id = ?1"),
		@NamedQuery(name = "findTGradebookByPrimaryKey", query = "select myTGradebook from TGradebook myTGradebook where myTGradebook.id = ?1"),
		@NamedQuery(name = "findTGradebookByTitle", query = "select myTGradebook from TGradebook myTGradebook where myTGradebook.title = ?1"),
		@NamedQuery(name = "findTGradebookByTitleContaining", query = "select myTGradebook from TGradebook myTGradebook where myTGradebook.title like ?1") })
@Table(catalog = "xidlims", name = "t_gradebook")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "TGradebook")
@XmlRootElement(namespace = "xidlims/net/xidlims/domain")
public class TGradebook implements Serializable {
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
	 * �ɼ������
	 * 
	 */

	@Column(name = "title")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String title;

	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "site_id", referencedColumnName = "id", nullable = false) })
	@XmlTransient
	TCourseSite TCourseSite;
	/**
	 */
	@OneToMany(mappedBy = "TGradebook", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.TGradeObject> TGradeObjects;

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
	 * �ɼ������
	 * 
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * �ɼ������
	 * 
	 */
	public String getTitle() {
		return this.title;
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
	public void setTGradeObjects(Set<TGradeObject> TGradeObjects) {
		this.TGradeObjects = TGradeObjects;
	}

	/**
	 */
	public Set<TGradeObject> getTGradeObjects() {
		if (TGradeObjects == null) {
			TGradeObjects = new java.util.LinkedHashSet<net.xidlims.domain.TGradeObject>();
		}
		return TGradeObjects;
	}

	/**
	 */
	public TGradebook() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(TGradebook that) {
		setId(that.getId());
		setTitle(that.getTitle());
		setTCourseSite(that.getTCourseSite());
		setTGradeObjects(new java.util.LinkedHashSet<net.xidlims.domain.TGradeObject>(that.getTGradeObjects()));
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("id=[").append(id).append("] ");
		buffer.append("title=[").append(title).append("] ");

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
		if (!(obj instanceof TGradebook))
			return false;
		TGradebook equalCheck = (TGradebook) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
