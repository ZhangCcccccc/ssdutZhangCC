package net.xidlims.domain;

import java.io.Serializable;

import java.lang.StringBuilder;

import java.util.Calendar;
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
		@NamedQuery(name = "findAllIotSharePowerOpentimes", query = "select myIotSharePowerOpentime from IotSharePowerOpentime myIotSharePowerOpentime"),
		@NamedQuery(name = "findIotSharePowerOpentimeByEnable", query = "select myIotSharePowerOpentime from IotSharePowerOpentime myIotSharePowerOpentime where myIotSharePowerOpentime.enable = ?1"),
		@NamedQuery(name = "findIotSharePowerOpentimeByEndTime", query = "select myIotSharePowerOpentime from IotSharePowerOpentime myIotSharePowerOpentime where myIotSharePowerOpentime.endTime = ?1"),
		@NamedQuery(name = "findIotSharePowerOpentimeById", query = "select myIotSharePowerOpentime from IotSharePowerOpentime myIotSharePowerOpentime where myIotSharePowerOpentime.id = ?1"),
		@NamedQuery(name = "findIotSharePowerOpentimeByPrimaryKey", query = "select myIotSharePowerOpentime from IotSharePowerOpentime myIotSharePowerOpentime where myIotSharePowerOpentime.id = ?1"),
		@NamedQuery(name = "findIotSharePowerOpentimeByStartTime", query = "select myIotSharePowerOpentime from IotSharePowerOpentime myIotSharePowerOpentime where myIotSharePowerOpentime.startTime = ?1") })
@Table(catalog = "xidlims", name = "iot_share_power_opentime")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "IotSharePowerOpentime")
public class IotSharePowerOpentime implements Serializable {
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
	 * ��ʼʱ��
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "start_time")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar startTime;
	/**
	 * ����ʱ��
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "end_time")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar endTime;
	/**
	 */

	@Column(name = "enable")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer enable;

	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "term_id", referencedColumnName = "id") })
	@XmlTransient
	SchoolTerm schoolTerm;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "weekday_id", referencedColumnName = "id") })
	@XmlTransient
	SchoolWeekday schoolWeekday;

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
	 * ��ʼʱ��
	 * 
	 */
	public void setStartTime(Calendar startTime) {
		this.startTime = startTime;
	}

	/**
	 * ��ʼʱ��
	 * 
	 */
	public Calendar getStartTime() {
		return this.startTime;
	}

	/**
	 * ����ʱ��
	 * 
	 */
	public void setEndTime(Calendar endTime) {
		this.endTime = endTime;
	}

	/**
	 * ����ʱ��
	 * 
	 */
	public Calendar getEndTime() {
		return this.endTime;
	}

	/**
	 */
	public void setEnable(Integer enable) {
		this.enable = enable;
	}

	/**
	 */
	public Integer getEnable() {
		return this.enable;
	}

	/**
	 */
	public void setSchoolTerm(SchoolTerm schoolTerm) {
		this.schoolTerm = schoolTerm;
	}

	/**
	 */
	@JsonIgnore
	public SchoolTerm getSchoolTerm() {
		return schoolTerm;
	}

	/**
	 */
	public void setSchoolWeekday(SchoolWeekday schoolWeekday) {
		this.schoolWeekday = schoolWeekday;
	}

	/**
	 */
	@JsonIgnore
	public SchoolWeekday getSchoolWeekday() {
		return schoolWeekday;
	}

	/**
	 */
	public IotSharePowerOpentime() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(IotSharePowerOpentime that) {
		setId(that.getId());
		setStartTime(that.getStartTime());
		setEndTime(that.getEndTime());
		setEnable(that.getEnable());
		setSchoolTerm(that.getSchoolTerm());
		setSchoolWeekday(that.getSchoolWeekday());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("id=[").append(id).append("] ");
		buffer.append("startTime=[").append(startTime).append("] ");
		buffer.append("endTime=[").append(endTime).append("] ");
		buffer.append("enable=[").append(enable).append("] ");

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
		if (!(obj instanceof IotSharePowerOpentime))
			return false;
		IotSharePowerOpentime equalCheck = (IotSharePowerOpentime) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
