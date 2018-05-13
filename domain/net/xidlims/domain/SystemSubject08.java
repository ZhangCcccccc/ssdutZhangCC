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
		@NamedQuery(name = "findAllSystemSubject08s", query = "select mySystemSubject08 from SystemSubject08 mySystemSubject08"),
		@NamedQuery(name = "findSystemSubject08ByPrimaryKey", query = "select mySystemSubject08 from SystemSubject08 mySystemSubject08 where mySystemSubject08.SNumber = ?1"),
		@NamedQuery(name = "findSystemSubject08BySName", query = "select mySystemSubject08 from SystemSubject08 mySystemSubject08 where mySystemSubject08.SName = ?1"),
		@NamedQuery(name = "findSystemSubject08BySNameContaining", query = "select mySystemSubject08 from SystemSubject08 mySystemSubject08 where mySystemSubject08.SName like ?1"),
		@NamedQuery(name = "findSystemSubject08BySNumber", query = "select mySystemSubject08 from SystemSubject08 mySystemSubject08 where mySystemSubject08.SNumber = ?1"),
		@NamedQuery(name = "findSystemSubject08BySNumberContaining", query = "select mySystemSubject08 from SystemSubject08 mySystemSubject08 where mySystemSubject08.SNumber like ?1") })
@Table(catalog = "xidlims", name = "system_subject_08")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "SystemSubject08")
@XmlRootElement(namespace = "xidlims/net/xidlims/domain")
public class SystemSubject08 implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 */

	@Column(name = "s_number", length = 40, nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	String SNumber;
	/**
	 */

	@Column(name = "s_name", length = 100)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String SName;

	/**
	 */
	@OneToMany(mappedBy = "systemSubject08", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.SystemSubject12> systemSubject12s;

	/**
	 */
	public void setSNumber(String SNumber) {
		this.SNumber = SNumber;
	}

	/**
	 */
	public String getSNumber() {
		return this.SNumber;
	}

	/**
	 */
	public void setSName(String SName) {
		this.SName = SName;
	}

	/**
	 */
	public String getSName() {
		return this.SName;
	}

	/**
	 */
	public void setSystemSubject12s(Set<SystemSubject12> systemSubject12s) {
		this.systemSubject12s = systemSubject12s;
	}

	/**
	 */
	@JsonIgnore
	public Set<SystemSubject12> getSystemSubject12s() {
		if (systemSubject12s == null) {
			systemSubject12s = new java.util.LinkedHashSet<net.xidlims.domain.SystemSubject12>();
		}
		return systemSubject12s;
	}

	/**
	 */
	public SystemSubject08() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(SystemSubject08 that) {
		setSNumber(that.getSNumber());
		setSName(that.getSName());
		setSystemSubject12s(new java.util.LinkedHashSet<net.xidlims.domain.SystemSubject12>(that.getSystemSubject12s()));
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
		if (!(obj instanceof SystemSubject08))
			return false;
		SystemSubject08 equalCheck = (SystemSubject08) obj;
		if ((SNumber == null && equalCheck.SNumber != null) || (SNumber != null && equalCheck.SNumber == null))
			return false;
		if (SNumber != null && !SNumber.equals(equalCheck.SNumber))
			return false;
		return true;
	}
}
