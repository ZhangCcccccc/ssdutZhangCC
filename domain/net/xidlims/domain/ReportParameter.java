package net.xidlims.domain;

import java.io.Serializable;

import java.lang.StringBuilder;

import java.math.BigDecimal;

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
		@NamedQuery(name = "findAllReportParameters", query = "select myReportParameter from ReportParameter myReportParameter"),
		@NamedQuery(name = "findReportParameterByDeviceAvgTime", query = "select myReportParameter from ReportParameter myReportParameter where myReportParameter.deviceAvgTime = ?1"),
		@NamedQuery(name = "findReportParameterById", query = "select myReportParameter from ReportParameter myReportParameter where myReportParameter.id = ?1"),
		@NamedQuery(name = "findReportParameterByLabAvgArea", query = "select myReportParameter from ReportParameter myReportParameter where myReportParameter.labAvgArea = ?1"),
		@NamedQuery(name = "findReportParameterByPrimaryKey", query = "select myReportParameter from ReportParameter myReportParameter where myReportParameter.id = ?1"),
		@NamedQuery(name = "findReportParameterByRatedCourseTime", query = "select myReportParameter from ReportParameter myReportParameter where myReportParameter.ratedCourseTime = ?1"),
		@NamedQuery(name = "findReportParameterBySubjectFactor", query = "select myReportParameter from ReportParameter myReportParameter where myReportParameter.subjectFactor = ?1") })
@Table(catalog = "xidlims", name = "report_parameter")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "ReportParameter")
public class ReportParameter implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ��������
	 * 
	 */

	@Column(name = "id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	@XmlElement
	Integer id;
	/**
	 * ���ʱ��
	 * 
	 */

	@Column(name = "rated_course_time")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer ratedCourseTime;
	/**
	 * �豸ƽ���ʱ
	 * 
	 */

	@Column(name = "device_avg_time")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer deviceAvgTime;

	/**
	 * ʵ����������
	 * 
	 */

	@Column(name = "lab_avg_area")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer labAvgArea;
	/**
	 * ѧԺѧ��ϵ��
	 * 
	 */

	@Column(name = "subject_factor", scale = 2, precision = 11)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal subjectFactor;

	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "academy_number", referencedColumnName = "academy_number") })
	@XmlTransient
	SchoolAcademy schoolAcademy;
	
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "term_id", referencedColumnName = "id") })
	@XmlTransient
	SchoolTerm schoolTerm;

	/**
	 * ��������
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * ��������
	 * 
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * ���ʱ��
	 * 
	 */
	public void setRatedCourseTime(Integer ratedCourseTime) {
		this.ratedCourseTime = ratedCourseTime;
	}

	/**
	 * ���ʱ��
	 * 
	 */
	public Integer getRatedCourseTime() {
		return this.ratedCourseTime;
	}

	/**
	 * �豸ƽ���ʱ
	 * 
	 */
	public void setDeviceAvgTime(Integer deviceAvgTime) {
		this.deviceAvgTime = deviceAvgTime;
	}

	/**
	 * �豸ƽ���ʱ
	 * 
	 */
	public Integer getDeviceAvgTime() {
		return this.deviceAvgTime;
	}

	public SchoolTerm getSchoolTerm() {
		return schoolTerm;
	}

	public void setSchoolTerm(SchoolTerm schoolTerm) {
		this.schoolTerm = schoolTerm;
	}

	/**
	 * ʵ����������
	 * 
	 */
	public void setLabAvgArea(Integer labAvgArea) {
		this.labAvgArea = labAvgArea;
	}

	/**
	 * ʵ����������
	 * 
	 */
	public Integer getLabAvgArea() {
		return this.labAvgArea;
	}

	/**
	 * ѧԺѧ��ϵ��
	 * 
	 */
	public void setSubjectFactor(BigDecimal subjectFactor) {
		this.subjectFactor = subjectFactor;
	}

	/**
	 * ѧԺѧ��ϵ��
	 * 
	 */
	public BigDecimal getSubjectFactor() {
		return this.subjectFactor;
	}

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
	 */
	public ReportParameter() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(ReportParameter that) {
		setId(that.getId());
		setRatedCourseTime(that.getRatedCourseTime());
		setDeviceAvgTime(that.getDeviceAvgTime());
		setLabAvgArea(that.getLabAvgArea());
		setSubjectFactor(that.getSubjectFactor());
		setSchoolAcademy(that.getSchoolAcademy());
		setSchoolTerm(that.getSchoolTerm());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("id=[").append(id).append("] ");
		buffer.append("ratedCourseTime=[").append(ratedCourseTime).append("] ");
		buffer.append("deviceAvgTime=[").append(deviceAvgTime).append("] ");
		buffer.append("labAvgArea=[").append(labAvgArea).append("] ");
		buffer.append("subjectFactor=[").append(subjectFactor).append("] ");

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
		if (!(obj instanceof ReportParameter))
			return false;
		ReportParameter equalCheck = (ReportParameter) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
