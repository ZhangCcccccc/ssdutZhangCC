package net.xidlims.domain;

import java.io.Serializable;


import java.lang.StringBuilder;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import java.util.Set;
import javax.xml.bind.annotation.*;

import javax.persistence.*;

import org.codehaus.jackson.annotate.JsonIgnore;

/**
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "findAllSchoolWeekdays", query = "select mySchoolWeekday from SchoolWeekday mySchoolWeekday"),
		@NamedQuery(name = "findSchoolWeekdayById", query = "select mySchoolWeekday from SchoolWeekday mySchoolWeekday where mySchoolWeekday.id = ?1"),
		@NamedQuery(name = "findSchoolWeekdayByPrimaryKey", query = "select mySchoolWeekday from SchoolWeekday mySchoolWeekday where mySchoolWeekday.id = ?1"),
		@NamedQuery(name = "findSchoolWeekdayByWeekdayName", query = "select mySchoolWeekday from SchoolWeekday mySchoolWeekday where mySchoolWeekday.weekdayName = ?1"),
		@NamedQuery(name = "findSchoolWeekdayByWeekdayNameContaining", query = "select mySchoolWeekday from SchoolWeekday mySchoolWeekday where mySchoolWeekday.weekdayName like ?1") })
@Table(catalog = "xidlims", name = "school_weekday")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "SchoolWeekday")
public class SchoolWeekday implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * �����ջ��
	 * 
	 */

	@Column(name = "id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	Integer id;
	/**
	 * �������
	 * 
	 */

	@Column(name = "weekday_name", length = 40)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String weekdayName;
	
	@OneToMany(mappedBy = "schoolWeekday", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.LabReservationTimeTable> labReservationTimeTables;

	@JsonIgnore
	public java.util.Set<net.xidlims.domain.LabReservationTimeTable> getLabReservationTimeTables() {
		return labReservationTimeTables;
	}

	public void setLabReservationTimeTables(
			java.util.Set<net.xidlims.domain.LabReservationTimeTable> labReservationTimeTables) {
		this.labReservationTimeTables = labReservationTimeTables;
	}

	
	/**
	 */
	@OneToMany(mappedBy = "schoolWeekday", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.IotSharePowerOpentime> IotSharePowerOpentimes;
	/**
	 * �����ջ��
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * �����ջ��
	 * 
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * �������
	 * 
	 */
	public void setWeekdayName(String weekdayName) {
		this.weekdayName = weekdayName;
	}

	/**
	 * �������
	 * 
	 */
	public String getWeekdayName() {
		return this.weekdayName;
	}
	
	/**
	 */
	public void setIotSharePowerOpentimes(Set<IotSharePowerOpentime> IotSharePowerOpentimes) {
		this.IotSharePowerOpentimes = IotSharePowerOpentimes;
	}

	/**
	 */
	@JsonIgnore
	public Set<IotSharePowerOpentime> getIotSharePowerOpentimes() {
		if (IotSharePowerOpentimes == null) {
			IotSharePowerOpentimes = new java.util.LinkedHashSet<net.xidlims.domain.IotSharePowerOpentime>();
		}
		return IotSharePowerOpentimes;
	}

	/**
	 */
	public SchoolWeekday() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(SchoolWeekday that) {
		setId(that.getId());
		setWeekdayName(that.getWeekdayName());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("id=[").append(id).append("] ");
		buffer.append("weekdayName=[").append(weekdayName).append("] ");

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
		if (!(obj instanceof SchoolWeekday))
			return false;
		SchoolWeekday equalCheck = (SchoolWeekday) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
