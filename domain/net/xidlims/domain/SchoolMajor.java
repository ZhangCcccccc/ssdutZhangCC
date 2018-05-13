package net.xidlims.domain;

import java.io.Serializable;

import java.lang.StringBuilder;

import java.util.Calendar;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import org.codehaus.jackson.annotate.JsonIgnore;

import javax.xml.bind.annotation.*;

import javax.persistence.*;

/**
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "findAllSchoolMajors", query = "select mySchoolMajor from SchoolMajor mySchoolMajor"),
		@NamedQuery(name = "findSchoolMajorByCreatedAt", query = "select mySchoolMajor from SchoolMajor mySchoolMajor where mySchoolMajor.createdAt = ?1"),
		@NamedQuery(name = "findSchoolMajorByCreatedBy", query = "select mySchoolMajor from SchoolMajor mySchoolMajor where mySchoolMajor.createdBy = ?1"),
		@NamedQuery(name = "findSchoolMajorByCreatedByContaining", query = "select mySchoolMajor from SchoolMajor mySchoolMajor where mySchoolMajor.createdBy like ?1"),
		@NamedQuery(name = "findSchoolMajorById", query = "select mySchoolMajor from SchoolMajor mySchoolMajor where mySchoolMajor.id = ?1"),
		@NamedQuery(name = "findSchoolMajorByMajorName", query = "select mySchoolMajor from SchoolMajor mySchoolMajor where mySchoolMajor.majorName = ?1"),
		@NamedQuery(name = "findSchoolMajorByMajorNameContaining", query = "select mySchoolMajor from SchoolMajor mySchoolMajor where mySchoolMajor.majorName like ?1"),
		@NamedQuery(name = "findSchoolMajorByMajorNumber", query = "select mySchoolMajor from SchoolMajor mySchoolMajor where mySchoolMajor.majorNumber = ?1"),
		@NamedQuery(name = "findSchoolMajorByMajorNumberContaining", query = "select mySchoolMajor from SchoolMajor mySchoolMajor where mySchoolMajor.majorNumber like ?1"),
		@NamedQuery(name = "findSchoolMajorByPrimaryKey", query = "select mySchoolMajor from SchoolMajor mySchoolMajor where mySchoolMajor.majorNumber = ?1"),
		@NamedQuery(name = "findSchoolMajorByStudentType", query = "select mySchoolMajor from SchoolMajor mySchoolMajor where mySchoolMajor.studentType = ?1"),
		@NamedQuery(name = "findSchoolMajorByStudentTypeContaining", query = "select mySchoolMajor from SchoolMajor mySchoolMajor where mySchoolMajor.studentType like ?1"),
		@NamedQuery(name = "findSchoolMajorByUpdatedAt", query = "select mySchoolMajor from SchoolMajor mySchoolMajor where mySchoolMajor.updatedAt = ?1"),
		@NamedQuery(name = "findSchoolMajorByUpdatedBy", query = "select mySchoolMajor from SchoolMajor mySchoolMajor where mySchoolMajor.updatedBy = ?1"),
		@NamedQuery(name = "findSchoolMajorByUpdatedByContaining", query = "select mySchoolMajor from SchoolMajor mySchoolMajor where mySchoolMajor.updatedBy like ?1") })
@Table(catalog = "xidlims", name = "school_major")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "gvsun/net/gvsun/domain", name = "SchoolMajor")
@XmlRootElement(namespace = "gvsun/net/gvsun/domain")
public class SchoolMajor implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * רҵ����
	 * 
	 */

	@Column(name = "major_number", length = 40, nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	String majorNumber;
	/**
	 * רҵ��Ϣ���
	 * 
	 */

	@Column(name = "id")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer id;
	/**
	 * רҵ���
	 * 
	 */

	@Column(name = "major_name", length = 100)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String majorName;
	/**
	 * ����ʱ��
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar createdAt;
	/**
	 * ����ʱ��
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar updatedAt;
	/**
	 * ������
	 * 
	 */

	@Column(name = "created_by", length = 40)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String createdBy;
	/**
	 * ������
	 * 
	 */

	@Column(name = "updated_by", length = 40)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String updatedBy;
	/**
	 * �༶����
	 * 
	 */

	@Column(name = "student_type", length = 40)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String studentType;

	/**
	 */

	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "academy_number", referencedColumnName = "academy_number") })
	@XmlTransient
	SchoolAcademy schoolAcademy;
	
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
	 * רҵ����
	 * 
	 */
	public void setMajorNumber(String majorNumber) {
		this.majorNumber = majorNumber;
	}

	/**
	 * רҵ����
	 * 
	 */
	public String getMajorNumber() {
		return this.majorNumber;
	}

	/**
	 * רҵ��Ϣ���
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * רҵ��Ϣ���
	 * 
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * רҵ���
	 * 
	 */
	public void setMajorName(String majorName) {
		this.majorName = majorName;
	}

	/**
	 * רҵ���
	 * 
	 */
	public String getMajorName() {
		return this.majorName;
	}

	/**
	 * ����ʱ��
	 * 
	 */
	public void setCreatedAt(Calendar createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * ����ʱ��
	 * 
	 */
	public Calendar getCreatedAt() {
		return this.createdAt;
	}

	/**
	 * ����ʱ��
	 * 
	 */
	public void setUpdatedAt(Calendar updatedAt) {
		this.updatedAt = updatedAt;
	}

	/**
	 * ����ʱ��
	 * 
	 */
	public Calendar getUpdatedAt() {
		return this.updatedAt;
	}

	/**
	 * ������
	 * 
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * ������
	 * 
	 */
	public String getCreatedBy() {
		return this.createdBy;
	}

	/**
	 * ������
	 * 
	 */
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	/**
	 * ������
	 * 
	 */
	public String getUpdatedBy() {
		return this.updatedBy;
	}

	/**
	 * �༶����
	 * 
	 */
	public void setStudentType(String studentType) {
		this.studentType = studentType;
	}

	/**
	 * �༶����
	 * 
	 */
	public String getStudentType() {
		return this.studentType;
	}


	/**
	 *//*
	public void setOperationItems(Set<OperationItem> operationItems) {
		this.operationItems = operationItems;
	}

	*//**
	 *//*
	@JsonIgnore
	public Set<OperationItem> getOperationItems() {
		if (operationItems == null) {
			operationItems = new java.util.LinkedHashSet<net.gvsun.domain.OperationItem>();
		}
		return operationItems;
	}
*/




	/**
	 */
	public SchoolMajor() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(SchoolMajor that) {
		setMajorNumber(that.getMajorNumber());
		setId(that.getId());
		setMajorName(that.getMajorName());
		setCreatedAt(that.getCreatedAt());
		setUpdatedAt(that.getUpdatedAt());
		setCreatedBy(that.getCreatedBy());
		setUpdatedBy(that.getUpdatedBy());
		setStudentType(that.getStudentType());
	}

	/**
	 * Returns a textual representation of a bean.
	 *	
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("majorNumber=[").append(majorNumber).append("] ");
		buffer.append("id=[").append(id).append("] ");
		buffer.append("majorName=[").append(majorName).append("] ");
		buffer.append("createdAt=[").append(createdAt).append("] ");
		buffer.append("updatedAt=[").append(updatedAt).append("] ");
		buffer.append("createdBy=[").append(createdBy).append("] ");
		buffer.append("updatedBy=[").append(updatedBy).append("] ");
		buffer.append("studentType=[").append(studentType).append("] ");

		return buffer.toString();
	}

	/**
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((majorNumber == null) ? 0 : majorNumber.hashCode()));
		return result;
	}

	/**
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof SchoolMajor))
			return false;
		SchoolMajor equalCheck = (SchoolMajor) obj;
		if ((majorNumber == null && equalCheck.majorNumber != null) || (majorNumber != null && equalCheck.majorNumber == null))
			return false;
		if (majorNumber != null && !majorNumber.equals(equalCheck.majorNumber))
			return false;
		return true;
	}
}
