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
		@NamedQuery(name = "findAllSystemSubject12s", query = "select mySystemSubject12 from SystemSubject12 mySystemSubject12"),
		@NamedQuery(name = "findSystemSubject12ByPrimaryKey", query = "select mySystemSubject12 from SystemSubject12 mySystemSubject12 where mySystemSubject12.SNumber = ?1"),
		@NamedQuery(name = "findSystemSubject12BySName", query = "select mySystemSubject12 from SystemSubject12 mySystemSubject12 where mySystemSubject12.SName = ?1"),
		@NamedQuery(name = "findSystemSubject12BySNameContaining", query = "select mySystemSubject12 from SystemSubject12 mySystemSubject12 where mySystemSubject12.SName like ?1"),
		@NamedQuery(name = "findSystemSubject12BySNumber", query = "select mySystemSubject12 from SystemSubject12 mySystemSubject12 where mySystemSubject12.SNumber = ?1"),
		@NamedQuery(name = "findSystemSubject12BySNumberContaining", query = "select mySystemSubject12 from SystemSubject12 mySystemSubject12 where mySystemSubject12.SNumber like ?1") })
@Table(catalog = "xidlims", name = "system_subject_12")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "SystemSubject12")
@XmlRootElement(namespace = "xidlims/net/xidlims/domain")
public class SystemSubject12 implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ѧ�Ʊ��
	 * 
	 */

	@Column(name = "s_number", length = 40, nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	String SNumber;
	/**
	 * ѧ�����
	 * 
	 */

	@Column(name = "s_name", length = 100)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String SName;

	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "s_number_08", referencedColumnName = "s_number") })
	@XmlTransient
	SystemSubject08 systemSubject08;
	/**
	 */
	@OneToMany(mappedBy = "systemSubject12", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.OperationItem> operationItems;
	
	/*@OneToMany(mappedBy="systemSubject12", cascade = {CascadeType.REMOVE}, fetch = FetchType.LAZY)
	@XmlElement()
	java.util.Set<net.xidlims.domain.LabRoom> labRooms;*/

	/*@JsonIgnore
	public java.util.Set<net.xidlims.domain.LabRoom> getLabRooms() {
		return labRooms;
	}

	public void setLabRooms(java.util.Set<net.xidlims.domain.LabRoom> labRooms) {
		this.labRooms = labRooms;
	}
*/
	/**
	 * ѧ�Ʊ��
	 * 
	 */
	public void setSNumber(String SNumber) {
		this.SNumber = SNumber;
	}

	/**
	 * ѧ�Ʊ��
	 * 
	 */
	public String getSNumber() {
		return this.SNumber;
	}

	/**
	 * ѧ�����
	 * 
	 */
	public void setSName(String SName) {
		this.SName = SName;
	}

	/**
	 * ѧ�����
	 * 
	 */
	public String getSName() {
		return this.SName;
	}

	/**
	 */
	public void setSystemSubject08(SystemSubject08 systemSubject08) {
		this.systemSubject08 = systemSubject08;
	}

	/**
	 */
	@JsonIgnore
	public SystemSubject08 getSystemSubject08() {
		return systemSubject08;
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
	public SystemSubject12() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(SystemSubject12 that) {
		setSNumber(that.getSNumber());
		setSName(that.getSName());
		setSystemSubject08(that.getSystemSubject08());
		setOperationItems(new java.util.LinkedHashSet<net.xidlims.domain.OperationItem>(that.getOperationItems()));
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("SNumber=[").append(SNumber).append("] ");
		buffer.append("SName=[").append(SName).append("] ");

		return buffer.toString();
	}

	/**
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((SNumber == null) ? 0 : SNumber.hashCode()));
		return result;
	}

	/**
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof SystemSubject12))
			return false;
		SystemSubject12 equalCheck = (SystemSubject12) obj;
		if ((SNumber == null && equalCheck.SNumber != null) || (SNumber != null && equalCheck.SNumber == null))
			return false;
		if (SNumber != null && !SNumber.equals(equalCheck.SNumber))
			return false;
		return true;
	}
}
