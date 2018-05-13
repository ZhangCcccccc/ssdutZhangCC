package net.xidlims.domain;

import java.io.Serializable;

import java.lang.StringBuilder;

import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.codehaus.jackson.annotate.JsonIgnore;

import javax.xml.bind.annotation.*;

import javax.persistence.*;

/**
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "findAllLabReservationTimeTables", query = "select myLabReservationTimeTable from LabReservationTimeTable myLabReservationTimeTable"),
		@NamedQuery(name = "findLabReservationTimeTableById", query = "select myLabReservationTimeTable from LabReservationTimeTable myLabReservationTimeTable where myLabReservationTimeTable.id = ?1"),
		@NamedQuery(name = "findLabReservationTimeTableByPrimaryKey", query = "select myLabReservationTimeTable from LabReservationTimeTable myLabReservationTimeTable where myLabReservationTimeTable.id = ?1") })
@Table(catalog = "xidlims", name = "lab_reservation_time_table")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "LabReservationTimeTable")
public class LabReservationTimeTable implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ʵ����ԤԼʱ���
	 * 
	 */

	@Column(name = "id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	@GeneratedValue(strategy = GenerationType.AUTO)
	Integer id;

	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "lab_day", referencedColumnName = "id") })
	@XmlTransient
	SchoolWeekday schoolWeekday;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "lab_time", referencedColumnName = "id") })
	@XmlTransient
	SystemTime systemTime;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "lab_week", referencedColumnName = "id") })
	@XmlTransient
	CDictionary CDictionary;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "lab_reservation_id", referencedColumnName = "id") })
	@XmlTransient
	LabReservation labReservation;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "school_term", referencedColumnName = "id") })
	@XmlTransient
	SchoolTerm schoolTerm;

	/**
	 * ��ע
	 * 
	 */

	@Column(name = "use_condition", columnDefinition = "TEXT")
	@Basic(fetch = FetchType.EAGER)
	@Lob
	@XmlElement
	String useCondition;
	
	
	public String getUseCondition() {
		return useCondition;
	}

	public void setUseCondition(String useCondition) {
		this.useCondition = useCondition;
	}
	/**
	 * ʵ����ԤԼʱ���
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * ʵ����ԤԼʱ���
	 * 
	 */
	public Integer getId() {
		return this.id;
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
	public void setSystemTime(SystemTime systemTime) {
		this.systemTime = systemTime;
	}

	/**
	 */
	@JsonIgnore
	public SystemTime getSystemTime() {
		return systemTime;
	}

	/**
	 */
	public void setCDictionary(CDictionary CDictionary) {
		this.CDictionary = CDictionary;
	}

	/**
	 */
	@JsonIgnore
	public CDictionary getCDictionary() {
		return CDictionary;
	}

	/**
	 */
	public void setLabReservation(LabReservation labReservation) {
		this.labReservation = labReservation;
	}

	/**
	 */
	@JsonIgnore
	public LabReservation getLabReservation() {
		return labReservation;
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
	public LabReservationTimeTable() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(LabReservationTimeTable that) {
		setId(that.getId());
		setSchoolWeekday(that.getSchoolWeekday());
		setSystemTime(that.getSystemTime());
		setCDictionary(that.getCDictionary());
		setLabReservation(that.getLabReservation());
		setSchoolTerm(that.getSchoolTerm());
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
		if (!(obj instanceof LabReservationTimeTable))
			return false;
		LabReservationTimeTable equalCheck = (LabReservationTimeTable) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
