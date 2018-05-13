package net.xidlims.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import org.codehaus.jackson.annotate.JsonIgnore;

/**
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "findAllSystemMajor12s", query = "select mySystemMajor12 from SystemMajor12 mySystemMajor12"),
		@NamedQuery(name = "findSystemMajor12ByMName", query = "select mySystemMajor12 from SystemMajor12 mySystemMajor12 where mySystemMajor12.MName = ?1"),
		@NamedQuery(name = "findSystemMajor12ByMNameContaining", query = "select mySystemMajor12 from SystemMajor12 mySystemMajor12 where mySystemMajor12.MName like ?1"),
		@NamedQuery(name = "findSystemMajor12ByMNumber", query = "select mySystemMajor12 from SystemMajor12 mySystemMajor12 where mySystemMajor12.MNumber = ?1"),
		@NamedQuery(name = "findSystemMajor12ByMNumberContaining", query = "select mySystemMajor12 from SystemMajor12 mySystemMajor12 where mySystemMajor12.MNumber like ?1"),
		@NamedQuery(name = "findSystemMajor12ByPrimaryKey", query = "select mySystemMajor12 from SystemMajor12 mySystemMajor12 where mySystemMajor12.MNumber = ?1") })
@Table(catalog = "xidlims", name = "system_major_12")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "SystemMajor12")
@XmlRootElement(namespace = "xidlims/net/xidlims/domain")
public class SystemMajor12 implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 */

	@Column(name = "m_number", length = 40, nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	String MNumber;
	/**
	 */

	@Column(name = "m_name", length = 100)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String MName;

	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "m_number_08", referencedColumnName = "s_number") })
	@XmlTransient
	SystemMajor08 systemMajor08;
	/**
	 */
	@OneToMany(mappedBy = "systemMajor12", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.SchoolCourse> schoolCourses;
	/**
	 */
	@OneToMany(mappedBy = "systemMajor12", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.OperationItem> operationItems;
	/**
	 */
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(catalog = "xidlims", name = "operation_outline_system_major_12", joinColumns = { @JoinColumn(name = "system_major_12", referencedColumnName = "m_number", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "operation_outline_id", referencedColumnName = "id", nullable = false, updatable = false) })
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.OperationOutline> operationOutlines;

	/**
	 */
	public void setMNumber(String MNumber) {
		this.MNumber = MNumber;
	}

	/**
	 */
	public String getMNumber() {
		return this.MNumber;
	}

	/**
	 */
	public void setMName(String MName) {
		this.MName = MName;
	}

	/**
	 */
	public String getMName() {
		return this.MName;
	}

	/**
	 */
	public void setSystemMajor08(SystemMajor08 systemMajor08) {
		this.systemMajor08 = systemMajor08;
	}

	/**
	 */
	@JsonIgnore
	public SystemMajor08 getSystemMajor08() {
		return systemMajor08;
	}

	/**
	 */
	public void setSchoolCourses(Set<SchoolCourse> schoolCourses) {
		this.schoolCourses = schoolCourses;
	}

	/**
	 */
	@JsonIgnore
	public Set<SchoolCourse> getSchoolCourses() {
		if (schoolCourses == null) {
			schoolCourses = new java.util.LinkedHashSet<net.xidlims.domain.SchoolCourse>();
		}
		return schoolCourses;
	}
	/**
	 */
	public void setOperationOutlines(Set<OperationOutline> operationOutlines) {
		this.operationOutlines = operationOutlines;
	}

	/**
	 */
	@JsonIgnore
	public Set<OperationOutline> getOperationOutlines() {
		if (operationOutlines == null) {
			operationOutlines = new java.util.LinkedHashSet<net.xidlims.domain.OperationOutline>();
		}
		return operationOutlines;
	}

	/**
	 */
	public void setOperationItems(Set<OperationItem> operationItems) {
		this.operationItems = operationItems;
	}

	/**
	 */
	@JsonIgnore
	public Set<OperationItem> getOperationItems() {
		if (operationItems == null) {
			operationItems = new java.util.LinkedHashSet<net.xidlims.domain.OperationItem>();
		}
		return operationItems;
	}

	/**
	 */
	public SystemMajor12() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(SystemMajor12 that) {
		setMNumber(that.getMNumber());
		setMName(that.getMName());
		setSystemMajor08(that.getSystemMajor08());
		setSchoolCourses(new java.util.LinkedHashSet<net.xidlims.domain.SchoolCourse>(that.getSchoolCourses()));
		setOperationItems(new java.util.LinkedHashSet<net.xidlims.domain.OperationItem>(that.getOperationItems()));
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("MNumber=[").append(MNumber).append("] ");
		buffer.append("MName=[").append(MName).append("] ");

		return buffer.toString();
	}

	/**
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((MNumber == null) ? 0 : MNumber.hashCode()));
		return result;
	}

	/**
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof SystemMajor12))
			return false;
		SystemMajor12 equalCheck = (SystemMajor12) obj;
		if ((MNumber == null && equalCheck.MNumber != null) || (MNumber != null && equalCheck.MNumber == null))
			return false;
		if (MNumber != null && !MNumber.equals(equalCheck.MNumber))
			return false;
		return true;
	}
}
