package net.xidlims.domain;

import java.io.Serializable;

import java.lang.StringBuilder;

import java.math.BigDecimal;

import java.util.Set;

import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import javax.xml.bind.annotation.*;

import javax.persistence.*;

import org.codehaus.jackson.annotate.JsonIgnore;

/**
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "findAllReportRates", query = "select myReportRate from ReportRate myReportRate"),
		@NamedQuery(name = "findReportRateByComplexItemRate", query = "select myReportRate from ReportRate myReportRate where myReportRate.complexItemRate = ?1"),
		@NamedQuery(name = "findReportRateById", query = "select myReportRate from ReportRate myReportRate where myReportRate.id = ?1"),
		@NamedQuery(name = "findReportRateByItemsRate", query = "select myReportRate from ReportRate myReportRate where myReportRate.itemsRate = ?1"),
		@NamedQuery(name = "findReportRateByLabAdminRate", query = "select myReportRate from ReportRate myReportRate where myReportRate.labAdminRate = ?1"),
		@NamedQuery(name = "findReportRateByLabRate", query = "select myReportRate from ReportRate myReportRate where myReportRate.labRate = ?1"),
		@NamedQuery(name = "findReportRateByLargeDeviceTimeRate", query = "select myReportRate from ReportRate myReportRate where myReportRate.largeDeviceTimeRate = ?1"),
		@NamedQuery(name = "findReportRateByLargeDeviceUsedRate", query = "select myReportRate from ReportRate myReportRate where myReportRate.largeDeviceUsedRate = ?1"),
		@NamedQuery(name = "findReportRateByPrimaryKey", query = "select myReportRate from ReportRate myReportRate where myReportRate.id = ?1"),
		@NamedQuery(name = "findReportRateByRank", query = "select myReportRate from ReportRate myReportRate where myReportRate.rank = ?1"),
		@NamedQuery(name = "findReportRateByScore", query = "select myReportRate from ReportRate myReportRate where myReportRate.score = ?1"),
		@NamedQuery(name = "findReportRateByStudentTrainRate", query = "select myReportRate from ReportRate myReportRate where myReportRate.studentTrainRate = ?1"),
		@NamedQuery(name = "findReportRateByTeacherItemRate", query = "select myReportRate from ReportRate myReportRate where myReportRate.teacherItemRate = ?1"),
		@NamedQuery(name = "findReportRateByTerms", query = "select myReportRate from ReportRate myReportRate where myReportRate.terms = ?1"),
		@NamedQuery(name = "findReportRateByTermsContaining", query = "select myReportRate from ReportRate myReportRate where myReportRate.terms like ?1") })
@Table(catalog = "xidlims", name = "report_rate")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "ReportRate")
public class ReportRate implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ������õ�8����Чָ�ꡢ�ۺ�ָ�������
	 * 
	 */

	@Column(name = "id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	@XmlElement
	Integer id;
	/**
	 * ʵ����������
	 * 
	 */

	@Column(name = "lab_rate", scale = 2, precision = 11)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal labRate;
	/**
	 * �����豸ƽ���ʱ������
	 * 
	 */

	@Column(name = "large_device_time_rate", scale = 2, precision = 11)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal largeDeviceTimeRate;
	/**
	 * �����豸̨��ʹ����
	 * 
	 */

	@Column(name = "large_device_used_rate", scale = 2, precision = 11)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal largeDeviceUsedRate;
	/**
	 * ʵ����Ŀ������
	 * 
	 */

	@Column(name = "items_rate", scale = 2, precision = 11)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal itemsRate;
	/**
	 * ��ʦ����ָ��ʵ�����
	 * 
	 */

	@Column(name = "teacher_item_rate", scale = 2, precision = 11)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal teacherItemRate;
	/**
	 * ʵ����רְ����Աƽ����ʱ��
	 * 
	 */

	@Column(name = "lab_admin_rate", scale = 2, precision = 11)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal labAdminRate;
	/**
	 * �����ۺ���ʵ�����
	 * 
	 */

	@Column(name = "complex_item_rate", scale = 2, precision = 11)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal complexItemRate;
	/**
	 * �˲�������
	 * 
	 */

	@Column(name = "student_train_rate", scale = 2, precision = 11)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal studentTrainRate;
	/**
	 * ѧ���ַ�(��ʽ��23,34)
	 * 
	 */

	@Column(name = "terms")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String terms;
	/**
	 * �ۺ�ָ��
	 * 
	 */

	@Column(name = "score", scale = 4, precision = 11)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal score;
	/**
	 * ��Ч����
	 * 
	 */

	@Column(name = "rank")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer rank;
	
	@Column(name = "student_time",nullable=true,columnDefinition="INT default 0")  //默认值0
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer studentTime;

	/**
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumns({ @JoinColumn(name = "academy_number", referencedColumnName = "academy_number") })
	@XmlTransient
	SchoolAcademy schoolAcademy;

	/**
	 * ������õ�8����Чָ�ꡢ�ۺ�ָ�������
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * ������õ�8����Чָ�ꡢ�ۺ�ָ�������
	 * 
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * ʵ����������
	 * 
	 */
	public void setLabRate(BigDecimal labRate) {
		this.labRate = labRate;
	}

	/**
	 * ʵ����������
	 * 
	 */
	public BigDecimal getLabRate() {
		return this.labRate;
	}

	/**
	 * �����豸ƽ���ʱ������
	 * 
	 */
	public void setLargeDeviceTimeRate(BigDecimal largeDeviceTimeRate) {
		this.largeDeviceTimeRate = largeDeviceTimeRate;
	}

	/**
	 * �����豸ƽ���ʱ������
	 * 
	 */
	public BigDecimal getLargeDeviceTimeRate() {
		return this.largeDeviceTimeRate;
	}

	/**
	 * �����豸̨��ʹ����
	 * 
	 */
	public void setLargeDeviceUsedRate(BigDecimal largeDeviceUsedRate) {
		this.largeDeviceUsedRate = largeDeviceUsedRate;
	}

	/**
	 * �����豸̨��ʹ����
	 * 
	 */
	public BigDecimal getLargeDeviceUsedRate() {
		return this.largeDeviceUsedRate;
	}

	/**
	 * ʵ����Ŀ������
	 * 
	 */
	public void setItemsRate(BigDecimal itemsRate) {
		this.itemsRate = itemsRate;
	}

	/**
	 * ʵ����Ŀ������
	 * 
	 */
	public BigDecimal getItemsRate() {
		return this.itemsRate;
	}

	/**
	 * ��ʦ����ָ��ʵ�����
	 * 
	 */
	public void setTeacherItemRate(BigDecimal teacherItemRate) {
		this.teacherItemRate = teacherItemRate;
	}

	/**
	 * ��ʦ����ָ��ʵ�����
	 * 
	 */
	public BigDecimal getTeacherItemRate() {
		return this.teacherItemRate;
	}

	/**
	 * ʵ����רְ����Աƽ����ʱ��
	 * 
	 */
	public void setLabAdminRate(BigDecimal labAdminRate) {
		this.labAdminRate = labAdminRate;
	}

	/**
	 * ʵ����רְ����Աƽ����ʱ��
	 * 
	 */
	public BigDecimal getLabAdminRate() {
		return this.labAdminRate;
	}

	/**
	 * �����ۺ���ʵ�����
	 * 
	 */
	public void setComplexItemRate(BigDecimal complexItemRate) {
		this.complexItemRate = complexItemRate;
	}

	/**
	 * �����ۺ���ʵ�����
	 * 
	 */
	public BigDecimal getComplexItemRate() {
		return this.complexItemRate;
	}

	/**
	 * �˲�������
	 * 
	 */
	public void setStudentTrainRate(BigDecimal studentTrainRate) {
		this.studentTrainRate = studentTrainRate;
	}

	/**
	 * �˲�������
	 * 
	 */
	public BigDecimal getStudentTrainRate() {
		return this.studentTrainRate;
	}

	/**
	 * ѧ���ַ�(��ʽ��23,34)
	 * 
	 */
	public void setTerms(String terms) {
		this.terms = terms;
	}

	/**
	 * ѧ���ַ�(��ʽ��23,34)
	 * 
	 */
	public String getTerms() {
		return this.terms;
	}

	/**
	 * �ۺ�ָ��
	 * 
	 */
	public void setScore(BigDecimal score) {
		this.score = score;
	}

	/**
	 * �ۺ�ָ��
	 * 
	 */
	public BigDecimal getScore() {
		return this.score;
	}

	/**
	 * ��Ч����
	 * 
	 */
	public void setRank(Integer rank) {
		this.rank = rank;
	}

	/**
	 * ��Ч����
	 * 
	 */
	public Integer getRank() {
		return this.rank;
	}
	
	public Integer getStudentTime() {
		return studentTime;
	}

	public void setStudentTime(Integer studentTime) {
		this.studentTime = studentTime;
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
	public ReportRate() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(ReportRate that) {
		setId(that.getId());
		setLabRate(that.getLabRate());
		setLargeDeviceTimeRate(that.getLargeDeviceTimeRate());
		setLargeDeviceUsedRate(that.getLargeDeviceUsedRate());
		setItemsRate(that.getItemsRate());
		setTeacherItemRate(that.getTeacherItemRate());
		setLabAdminRate(that.getLabAdminRate());
		setComplexItemRate(that.getComplexItemRate());
		setStudentTrainRate(that.getStudentTrainRate());
		setTerms(that.getTerms());
		setScore(that.getScore());
		setRank(that.getRank());
		setStudentTime(that.getStudentTime());
		setSchoolAcademy(that.getSchoolAcademy());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("id=[").append(id).append("] ");
		buffer.append("labRate=[").append(labRate).append("] ");
		buffer.append("largeDeviceTimeRate=[").append(largeDeviceTimeRate).append("] ");
		buffer.append("largeDeviceUsedRate=[").append(largeDeviceUsedRate).append("] ");
		buffer.append("itemsRate=[").append(itemsRate).append("] ");
		buffer.append("teacherItemRate=[").append(teacherItemRate).append("] ");
		buffer.append("labAdminRate=[").append(labAdminRate).append("] ");
		buffer.append("complexItemRate=[").append(complexItemRate).append("] ");
		buffer.append("studentTrainRate=[").append(studentTrainRate).append("] ");
		buffer.append("studentTime=[").append(studentTime).append("] ");
		buffer.append("terms=[").append(terms).append("] ");
		buffer.append("score=[").append(score).append("] ");
		buffer.append("rank=[").append(rank).append("] ");

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
		if (!(obj instanceof ReportRate))
			return false;
		ReportRate equalCheck = (ReportRate) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
