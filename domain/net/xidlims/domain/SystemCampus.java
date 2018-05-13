package net.xidlims.domain;

import java.io.Serializable;

import java.lang.StringBuilder;

import java.util.Calendar;
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
		@NamedQuery(name = "findAllSystemCampuss", query = "select mySystemCampus from SystemCampus mySystemCampus"),
		@NamedQuery(name = "findSystemCampusByCampusDefault", query = "select mySystemCampus from SystemCampus mySystemCampus where mySystemCampus.campusDefault = ?1"),
		@NamedQuery(name = "findSystemCampusByCampusName", query = "select mySystemCampus from SystemCampus mySystemCampus where mySystemCampus.campusName = ?1"),
		@NamedQuery(name = "findSystemCampusByCampusNameContaining", query = "select mySystemCampus from SystemCampus mySystemCampus where mySystemCampus.campusName like ?1"),
		@NamedQuery(name = "findSystemCampusByCampusNumber", query = "select mySystemCampus from SystemCampus mySystemCampus where mySystemCampus.campusNumber = ?1"),
		@NamedQuery(name = "findSystemCampusByCampusNumberContaining", query = "select mySystemCampus from SystemCampus mySystemCampus where mySystemCampus.campusNumber like ?1"),
		@NamedQuery(name = "findSystemCampusByCreatedAt", query = "select mySystemCampus from SystemCampus mySystemCampus where mySystemCampus.createdAt = ?1"),
		@NamedQuery(name = "findSystemCampusByCreatedAtAfter", query = "select mySystemCampus from SystemCampus mySystemCampus where mySystemCampus.createdAt > ?1"),
		@NamedQuery(name = "findSystemCampusByCreatedAtBefore", query = "select mySystemCampus from SystemCampus mySystemCampus where mySystemCampus.createdAt < ?1"),
		@NamedQuery(name = "findSystemCampusByPrimaryKey", query = "select mySystemCampus from SystemCampus mySystemCampus where mySystemCampus.campusNumber = ?1"),
		@NamedQuery(name = "findSystemCampusByUpdatedAt", query = "select mySystemCampus from SystemCampus mySystemCampus where mySystemCampus.updatedAt = ?1"),
		@NamedQuery(name = "findSystemCampusByUpdatedAtAfter", query = "select mySystemCampus from SystemCampus mySystemCampus where mySystemCampus.updatedAt > ?1"),
		@NamedQuery(name = "findSystemCampusByUpdatedAtBefore", query = "select mySystemCampus from SystemCampus mySystemCampus where mySystemCampus.updatedAt < ?1") })
@Table(catalog = "xidlims", name = "system_campus")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "SystemCampus")
@XmlRootElement(namespace = "xidlims/net/xidlims/domain")
public class SystemCampus implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * У���
	 * 
	 */

	@Column(name = "campus_number", length = 40, nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	String campusNumber;
	/**
	 * У�����
	 * 
	 */

	@Column(name = "campus_name")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String campusName;
	/**
	 * �Ƿ��ʶΪĬ�ϣ�1ΪĬ�ϣ�0Ϊ��Ĭ��
	 * 
	 */

	@Column(name = "campus_default")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Boolean campusDefault;
	/**
	 * ����ʱ��
	 * 
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "created_at")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar createdAt;
	/**
	 * ����ʱ��
	 * 
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "updated_at")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar updatedAt;

	/**
	 */
	@OneToMany(mappedBy = "systemCampus", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.LabCenter> labCenters;
	/**
	 */
	@OneToMany(mappedBy = "systemCampus", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.SystemTime> systemTimes;

	/**
	 * У���
	 * 
	 */
	public void setCampusNumber(String campusNumber) {
		this.campusNumber = campusNumber;
	}

	/**
	 * У���
	 * 
	 */
	public String getCampusNumber() {
		return this.campusNumber;
	}

	/**
	 * У�����
	 * 
	 */
	public void setCampusName(String campusName) {
		this.campusName = campusName;
	}

	/**
	 * У�����
	 * 
	 */
	public String getCampusName() {
		return this.campusName;
	}

	/**
	 * �Ƿ��ʶΪĬ�ϣ�1ΪĬ�ϣ�0Ϊ��Ĭ��
	 * 
	 */
	public void setCampusDefault(Boolean campusDefault) {
		this.campusDefault = campusDefault;
	}

	/**
	 * �Ƿ��ʶΪĬ�ϣ�1ΪĬ�ϣ�0Ϊ��Ĭ��
	 * 
	 */
	public Boolean getCampusDefault() {
		return this.campusDefault;
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
	 */
	public void setLabCenters(Set<LabCenter> labCenters) {
		this.labCenters = labCenters;
	}

	/**
	 */
	@JsonIgnore
	public Set<LabCenter> getLabCenters() {
		if (labCenters == null) {
			labCenters = new java.util.LinkedHashSet<net.xidlims.domain.LabCenter>();
		}
		return labCenters;
	}

	/**
	 */
	public void setSystemTimes(Set<SystemTime> systemTimes) {
		this.systemTimes = systemTimes;
	}

	/**
	 */
	@JsonIgnore
	public Set<SystemTime> getSystemTimes() {
		if (systemTimes == null) {
			systemTimes = new java.util.LinkedHashSet<net.xidlims.domain.SystemTime>();
		}
		return systemTimes;
	}

	/**
	 */
	public SystemCampus() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(SystemCampus that) {
		setCampusNumber(that.getCampusNumber());
		setCampusName(that.getCampusName());
		setCampusDefault(that.getCampusDefault());
		setCreatedAt(that.getCreatedAt());
		setUpdatedAt(that.getUpdatedAt());
		setLabCenters(new java.util.LinkedHashSet<net.xidlims.domain.LabCenter>(that.getLabCenters()));
		setSystemTimes(new java.util.LinkedHashSet<net.xidlims.domain.SystemTime>(that.getSystemTimes()));
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("campusNumber=[").append(campusNumber).append("] ");
		buffer.append("campusName=[").append(campusName).append("] ");
		buffer.append("campusDefault=[").append(campusDefault).append("] ");
		buffer.append("createdAt=[").append(createdAt).append("] ");
		buffer.append("updatedAt=[").append(updatedAt).append("] ");

		return buffer.toString();
	}

	/**
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((campusNumber == null) ? 0 : campusNumber.hashCode()));
		return result;
	}

	/**
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof SystemCampus))
			return false;
		SystemCampus equalCheck = (SystemCampus) obj;
		if ((campusNumber == null && equalCheck.campusNumber != null) || (campusNumber != null && equalCheck.campusNumber == null))
			return false;
		if (campusNumber != null && !campusNumber.equals(equalCheck.campusNumber))
			return false;
		return true;
	}
}
