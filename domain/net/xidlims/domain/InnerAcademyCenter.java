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
		@NamedQuery(name = "findAllInnerAcademyCenters", query = "select myInnerAcademyCenter from InnerAcademyCenter myInnerAcademyCenter"),
		@NamedQuery(name = "findInnerAcademyCenterById", query = "select myInnerAcademyCenter from InnerAcademyCenter myInnerAcademyCenter where myInnerAcademyCenter.id = ?1"),
		@NamedQuery(name = "findInnerAcademyCenterByPrimaryKey", query = "select myInnerAcademyCenter from InnerAcademyCenter myInnerAcademyCenter where myInnerAcademyCenter.id = ?1") })
@Table(catalog = "xidlims", name = "inner_academy_center")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "InnerAcademyCenter")
public class InnerAcademyCenter implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ��������
	 * 
	 */

	@Column(name = "id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	@XmlElement
	Integer id;

	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "center_id", referencedColumnName = "id") })
	@XmlTransient
	LabCenter labCenter;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "academy_number", referencedColumnName = "academy_number") })
	@XmlTransient
	SchoolAcademy schoolAcademy;

	/**
	 * ��������
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * ��������
	 * 
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 */
	public void setLabCenter(LabCenter labCenter) {
		this.labCenter = labCenter;
	}

	/**
	 */
	@JsonIgnore
	public LabCenter getLabCenter() {
		return labCenter;
	}

	/**
	 */
	public void setSchoolAcademy(SchoolAcademy schoolAcademy) {
		this.schoolAcademy = schoolAcademy;
	}

	/**
	 */
	@JsonIgnore
	public SchoolAcademy getSchoolAcademy() {
		return schoolAcademy;
	}

	/**
	 */
	public InnerAcademyCenter() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(InnerAcademyCenter that) {
		setId(that.getId());
		setLabCenter(that.getLabCenter());
		setSchoolAcademy(that.getSchoolAcademy());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("id=[").append(id).append("] ");

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
		if (!(obj instanceof InnerAcademyCenter))
			return false;
		InnerAcademyCenter equalCheck = (InnerAcademyCenter) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
