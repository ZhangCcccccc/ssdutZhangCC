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
		@NamedQuery(name = "findAllSystemMajor08s", query = "select mySystemMajor08 from SystemMajor08 mySystemMajor08"),
		@NamedQuery(name = "findSystemMajor08ByPrimaryKey", query = "select mySystemMajor08 from SystemMajor08 mySystemMajor08 where mySystemMajor08.SNumber = ?1"),
		@NamedQuery(name = "findSystemMajor08BySName", query = "select mySystemMajor08 from SystemMajor08 mySystemMajor08 where mySystemMajor08.SName = ?1"),
		@NamedQuery(name = "findSystemMajor08BySNameContaining", query = "select mySystemMajor08 from SystemMajor08 mySystemMajor08 where mySystemMajor08.SName like ?1"),
		@NamedQuery(name = "findSystemMajor08BySNumber", query = "select mySystemMajor08 from SystemMajor08 mySystemMajor08 where mySystemMajor08.SNumber = ?1"),
		@NamedQuery(name = "findSystemMajor08BySNumberContaining", query = "select mySystemMajor08 from SystemMajor08 mySystemMajor08 where mySystemMajor08.SNumber like ?1") })
@Table(catalog = "xidlims", name = "system_major_08")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "SystemMajor08")
@XmlRootElement(namespace = "xidlims/net/xidlims/domain")
public class SystemMajor08 implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * רҵ���
	 * 
	 */

	@Column(name = "s_number", length = 40, nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	String SNumber;
	/**
	 * רҵ���
	 * 
	 */

	@Column(name = "s_name", length = 100)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String SName;

	/**
	 */
	@OneToMany(mappedBy = "systemMajor08", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.SystemMajor12> systemMajor12s;

	/**
	 * רҵ���
	 * 
	 */
	public void setSNumber(String SNumber) {
		this.SNumber = SNumber;
	}

	/**
	 * רҵ���
	 * 
	 */
	public String getSNumber() {
		return this.SNumber;
	}

	/**
	 * רҵ���
	 * 
	 */
	public void setSName(String SName) {
		this.SName = SName;
	}

	/**
	 * רҵ���
	 * 
	 */
	public String getSName() {
		return this.SName;
	}

	/**
	 */
	public void setSystemMajor12s(Set<SystemMajor12> systemMajor12s) {
		this.systemMajor12s = systemMajor12s;
	}

	/**
	 */
	@JsonIgnore
	public Set<SystemMajor12> getSystemMajor12s() {
		if (systemMajor12s == null) {
			systemMajor12s = new java.util.LinkedHashSet<net.xidlims.domain.SystemMajor12>();
		}
		return systemMajor12s;
	}

	/**
	 */
	public SystemMajor08() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(SystemMajor08 that) {
		setSNumber(that.getSNumber());
		setSName(that.getSName());
		setSystemMajor12s(new java.util.LinkedHashSet<net.xidlims.domain.SystemMajor12>(that.getSystemMajor12s()));
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
		if (!(obj instanceof SystemMajor08))
			return false;
		SystemMajor08 equalCheck = (SystemMajor08) obj;
		if ((SNumber == null && equalCheck.SNumber != null) || (SNumber != null && equalCheck.SNumber == null))
			return false;
		if (SNumber != null && !SNumber.equals(equalCheck.SNumber))
			return false;
		return true;
	}
}
