package net.xidlims.domain;

import java.io.Serializable;

import java.lang.StringBuilder;

import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import org.codehaus.jackson.annotate.JsonIgnore;

import org.skyway.spring.util.databinding.TypeConversionUtils;

import javax.xml.bind.annotation.*;

import javax.persistence.*;

/**
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "findAllSystemTimes", query = "select mySystemTime from SystemTime mySystemTime"),
		@NamedQuery(name = "findSystemTimeByCombine", query = "select mySystemTime from SystemTime mySystemTime where mySystemTime.combine = ?1"),
		@NamedQuery(name = "findSystemTimeByCombineContaining", query = "select mySystemTime from SystemTime mySystemTime where mySystemTime.combine like ?1"),
		@NamedQuery(name = "findSystemTimeByCreatedBy", query = "select mySystemTime from SystemTime mySystemTime where mySystemTime.createdBy = ?1"),
		@NamedQuery(name = "findSystemTimeByCreatedByContaining", query = "select mySystemTime from SystemTime mySystemTime where mySystemTime.createdBy like ?1"),
		@NamedQuery(name = "findSystemTimeByCreatedDate", query = "select mySystemTime from SystemTime mySystemTime where mySystemTime.createdDate = ?1"),
		@NamedQuery(name = "findSystemTimeByEndDate", query = "select mySystemTime from SystemTime mySystemTime where mySystemTime.endDate = ?1"),
		@NamedQuery(name = "findSystemTimeById", query = "select mySystemTime from SystemTime mySystemTime where mySystemTime.id = ?1"),
		@NamedQuery(name = "findSystemTimeByPrimaryKey", query = "select mySystemTime from SystemTime mySystemTime where mySystemTime.id = ?1"),
		@NamedQuery(name = "findSystemTimeBySequence", query = "select mySystemTime from SystemTime mySystemTime where mySystemTime.sequence = ?1"),
		@NamedQuery(name = "findSystemTimeBySequenceContaining", query = "select mySystemTime from SystemTime mySystemTime where mySystemTime.sequence like ?1"),
		@NamedQuery(name = "findSystemTimeByStartDate", query = "select mySystemTime from SystemTime mySystemTime where mySystemTime.startDate = ?1"),
		@NamedQuery(name = "findSystemTimeByTimeName", query = "select mySystemTime from SystemTime mySystemTime where mySystemTime.sectionName = ?1"),
		@NamedQuery(name = "findSystemTimeByTimeNameContaining", query = "select mySystemTime from SystemTime mySystemTime where mySystemTime.sectionName like ?1"),
		@NamedQuery(name = "findSystemTimeByUpdatedBy", query = "select mySystemTime from SystemTime mySystemTime where mySystemTime.updatedBy = ?1"),
		@NamedQuery(name = "findSystemTimeByUpdatedByContaining", query = "select mySystemTime from SystemTime mySystemTime where mySystemTime.updatedBy like ?1"),
		@NamedQuery(name = "findSystemTimeByUpdatedDate", query = "select mySystemTime from SystemTime mySystemTime where mySystemTime.updatedDate = ?1") })
@Table(catalog = "xidlims", name = "system_time")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "SystemTime")
public class SystemTime implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * �ڴ�ʱ��ϵͳ��
	 * 
	 */

	@Column(name = "id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	@GeneratedValue(strategy = GenerationType.AUTO)
	Integer id;
	/**
	 * ÿ�ڿ�ʼʱ��
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "start_date")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar startDate;
	/**
	 * ÿ�ڽ���ʱ��
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "end_date")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar endDate;
	/**
	 * �ڴ����
	 * 
	 */

	@Column(name = "section_name", length = 20)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String sectionName;
	/**
	 * ����
	 * 
	 */

	@Column(name = "sequence", length = 20)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String sequence;
	/**
	 * ���
	 * 
	 */

	@Column(name = "combine", length = 10)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String combine;
	/**
	 * ��������
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar createdDate;
	/**
	 * ��������
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_date")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar updatedDate;
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

	@Column(name = "section")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer section;

	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "campus_number", referencedColumnName = "campus_number") })
	@XmlTransient
	SystemCampus systemCampus;
	
	@OneToMany(mappedBy = "systemTime", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
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
	 * �ڴ�ʱ��ϵͳ��
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * �ڴ�ʱ��ϵͳ��
	 * 
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * ÿ�ڿ�ʼʱ��
	 * 
	 */
	public void setStartDate(Calendar startDate) {
		TypeConversionUtils.clearDateFields(startDate);
		this.startDate = startDate;
	}

	/**
	 * ÿ�ڿ�ʼʱ��
	 * 
	 */
	public Calendar getStartDate() {
		return this.startDate;
	}

	/**
	 * ÿ�ڽ���ʱ��
	 * 
	 */
	public void setEndDate(Calendar endDate) {
		TypeConversionUtils.clearDateFields(endDate);
		this.endDate = endDate;
	}

	/**
	 * ÿ�ڽ���ʱ��
	 * 
	 */
	public Calendar getEndDate() {
		return this.endDate;
	}

	/**
	 * �ڴ����
	 * 
	 */
	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}


	/**
	 * �ڴ����
	 * 
	 */
	public String getSectionName() {
		return this.sectionName;
	}


	/**
	 * �ڴ����
	 * 
	 */
	public Integer getSection() {
		return this.section;
	}

	/**
	 * �ڴ����
	 * 
	 */
	public void setSection(Integer section) {
		this.section = section;
	}

	/**
	 * ����
	 * 
	 */
	public void setSequence(String sequence) {
		this.sequence = sequence;
	}

	/**
	 * ����
	 * 
	 */
	public String getSequence() {
		return this.sequence;
	}

	/**
	 * ���
	 * 
	 */
	public void setCombine(String combine) {
		this.combine = combine;
	}

	/**
	 * ���
	 * 
	 */
	public String getCombine() {
		return this.combine;
	}

	/**
	 * ��������
	 * 
	 */
	public void setCreatedDate(Calendar createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * ��������
	 * 
	 */
	public Calendar getCreatedDate() {
		return this.createdDate;
	}

	/**
	 * ��������
	 * 
	 */
	public void setUpdatedDate(Calendar updatedDate) {
		this.updatedDate = updatedDate;
	}

	/**
	 * ��������
	 * 
	 */
	public Calendar getUpdatedDate() {
		return this.updatedDate;
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
	 */
	public void setSystemCampus(SystemCampus systemCampus) {
		this.systemCampus = systemCampus;
	}

	/**
	 */
	@JsonIgnore
	public SystemCampus getSystemCampus() {
		return systemCampus;
	}

	/**
	 */
	public SystemTime() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(SystemTime that) {
		setId(that.getId());
		setStartDate(that.getStartDate());
		setEndDate(that.getEndDate());
		setSectionName(that.getSectionName());
		setSequence(that.getSequence());
		setCombine(that.getCombine());
		setCreatedDate(that.getCreatedDate());
		setUpdatedDate(that.getUpdatedDate());
		setCreatedBy(that.getCreatedBy());
		setUpdatedBy(that.getUpdatedBy());
		setSystemCampus(that.getSystemCampus());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("id=[").append(id).append("] ");
		buffer.append("startDate=[").append(startDate).append("] ");
		buffer.append("endDate=[").append(endDate).append("] ");
		buffer.append("sectionName=[").append(sectionName).append("] ");
		buffer.append("sequence=[").append(sequence).append("] ");
		buffer.append("combine=[").append(combine).append("] ");
		buffer.append("createdDate=[").append(createdDate).append("] ");
		buffer.append("updatedDate=[").append(updatedDate).append("] ");
		buffer.append("createdBy=[").append(createdBy).append("] ");
		buffer.append("updatedBy=[").append(updatedBy).append("] ");

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
		if (!(obj instanceof SystemTime))
			return false;
		SystemTime equalCheck = (SystemTime) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
