package net.xidlims.domain;

import java.io.Serializable;

import java.lang.StringBuilder;

import java.util.Calendar;


import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import javax.xml.bind.annotation.*;

import javax.persistence.*;

/**
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "findAllTimetableAppointmentSameNumbers", query = "select myTimetableAppointmentSameNumber from TimetableAppointmentSameNumber myTimetableAppointmentSameNumber order by myTimetableAppointmentSameNumber.startClass,myTimetableAppointmentSameNumber.endClass"),
		@NamedQuery(name = "findTimetableAppointmentSameNumberByCreatedBy", query = "select myTimetableAppointmentSameNumber from TimetableAppointmentSameNumber myTimetableAppointmentSameNumber where myTimetableAppointmentSameNumber.createdBy = ?1"),
		@NamedQuery(name = "findTimetableAppointmentSameNumberByCreatedDate", query = "select myTimetableAppointmentSameNumber from TimetableAppointmentSameNumber myTimetableAppointmentSameNumber where myTimetableAppointmentSameNumber.createdDate = ?1"),
		@NamedQuery(name = "findTimetableAppointmentSameNumberByEndClass", query = "select myTimetableAppointmentSameNumber from TimetableAppointmentSameNumber myTimetableAppointmentSameNumber where myTimetableAppointmentSameNumber.endClass = ?1"),
		@NamedQuery(name = "findTimetableAppointmentSameNumberByEndWeek", query = "select myTimetableAppointmentSameNumber from TimetableAppointmentSameNumber myTimetableAppointmentSameNumber where myTimetableAppointmentSameNumber.endWeek = ?1"),
		@NamedQuery(name = "findTimetableAppointmentSameNumberById", query = "select myTimetableAppointmentSameNumber from TimetableAppointmentSameNumber myTimetableAppointmentSameNumber where myTimetableAppointmentSameNumber.id = ?1"),
		@NamedQuery(name = "findTimetableAppointmentSameNumberByPrimaryKey", query = "select myTimetableAppointmentSameNumber from TimetableAppointmentSameNumber myTimetableAppointmentSameNumber where myTimetableAppointmentSameNumber.id = ?1"),
		@NamedQuery(name = "findTimetableAppointmentSameNumberByStartClass", query = "select myTimetableAppointmentSameNumber from TimetableAppointmentSameNumber myTimetableAppointmentSameNumber where myTimetableAppointmentSameNumber.startClass = ?1"),
		@NamedQuery(name = "findTimetableAppointmentSameNumberByStartWeek", query = "select myTimetableAppointmentSameNumber from TimetableAppointmentSameNumber myTimetableAppointmentSameNumber where myTimetableAppointmentSameNumber.startWeek = ?1"),
		@NamedQuery(name = "findTimetableAppointmentSameNumberByTotalClasses", query = "select myTimetableAppointmentSameNumber from TimetableAppointmentSameNumber myTimetableAppointmentSameNumber where myTimetableAppointmentSameNumber.totalClasses = ?1"),
		@NamedQuery(name = "findTimetableAppointmentSameNumberByTotalWeeks", query = "select myTimetableAppointmentSameNumber from TimetableAppointmentSameNumber myTimetableAppointmentSameNumber where myTimetableAppointmentSameNumber.totalWeeks = ?1"),
		@NamedQuery(name = "findTimetableAppointmentSameNumberByTotalWeeksContaining", query = "select myTimetableAppointmentSameNumber from TimetableAppointmentSameNumber myTimetableAppointmentSameNumber where myTimetableAppointmentSameNumber.totalWeeks like ?1"),
		@NamedQuery(name = "findTimetableAppointmentSameNumberByUpdatedBy", query = "select myTimetableAppointmentSameNumber from TimetableAppointmentSameNumber myTimetableAppointmentSameNumber where myTimetableAppointmentSameNumber.updatedBy = ?1"),
		@NamedQuery(name = "findTimetableAppointmentSameNumberByUpdatedDate", query = "select myTimetableAppointmentSameNumber from TimetableAppointmentSameNumber myTimetableAppointmentSameNumber where myTimetableAppointmentSameNumber.updatedDate = ?1"),
		@NamedQuery(name = "findTimetableAppointmentSameNumberByWeekday", query = "select myTimetableAppointmentSameNumber from TimetableAppointmentSameNumber myTimetableAppointmentSameNumber where myTimetableAppointmentSameNumber.weekday = ?1") })
@Table(catalog = "xidlims", name = "timetable_appointment_same_number")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "TimetableAppointmentSameNumber")
public class TimetableAppointmentSameNumber implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * һ���ſζ�����¼�ſα�
	 * 
	 */

	@Column(name = "id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	@XmlElement
	Integer id;
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
	 * ������
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

	@Column(name = "created_by")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String createdBy;
	/**
	 * ������
	 * 
	 */

	@Column(name = "updated_by")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String updatedBy;
	/**
	 * ����
	 * 
	 */

	@Column(name = "total_weeks", length = 10)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String totalWeeks;
	/**
	 * ���ڼ�
	 * 
	 */

	@Column(name = "weekday")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer weekday;
	/**
	 * ��ʼ��
	 * 
	 */

	@Column(name = "start_week")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer startWeek;
	/**
	 * ������
	 * 
	 */

	@Column(name = "end_week")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer endWeek;
	/**
	 * �ڴ�
	 * 
	 */

	@Column(name = "total_classes")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer totalClasses;
	/**
	 * ��ʼ�ڴ�
	 * 
	 */

	@Column(name = "start_class")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer startClass;
	/**
	 * ����ڴ�
	 * 
	 */

	@Column(name = "end_class")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer endClass;

	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "appointment_id", referencedColumnName = "id") })
	@XmlTransient
	TimetableAppointment timetableAppointment;

	/**
	 * һ���ſζ�����¼�ſα�
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * һ���ſζ�����¼�ſα�
	 * 
	 */
	public Integer getId() {
		return this.id;
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
	 * ������
	 * 
	 */
	public void setUpdatedDate(Calendar updatedDate) {
		this.updatedDate = updatedDate;
	}

	/**
	 * ������
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
	 * ����
	 * 
	 */
	public void setTotalWeeks(String totalWeeks) {
		this.totalWeeks = totalWeeks;
	}

	/**
	 * ����
	 * 
	 */
	public String getTotalWeeks() {
		return this.totalWeeks;
	}

	/**
	 * ���ڼ�
	 * 
	 */
	public void setWeekday(Integer weekday) {
		this.weekday = weekday;
	}

	/**
	 * ���ڼ�
	 * 
	 */
	public Integer getWeekday() {
		return this.weekday;
	}

	/**
	 * ��ʼ��
	 * 
	 */
	public void setStartWeek(Integer startWeek) {
		this.startWeek = startWeek;
	}

	/**
	 * ��ʼ��
	 * 
	 */
	public Integer getStartWeek() {
		return this.startWeek;
	}

	/**
	 * ������
	 * 
	 */
	public void setEndWeek(Integer endWeek) {
		this.endWeek = endWeek;
	}

	/**
	 * ������
	 * 
	 */
	public Integer getEndWeek() {
		return this.endWeek;
	}

	/**
	 * �ڴ�
	 * 
	 */
	public void setTotalClasses(Integer totalClasses) {
		this.totalClasses = totalClasses;
	}

	/**
	 * �ڴ�
	 * 
	 */
	public Integer getTotalClasses() {
		return this.totalClasses;
	}

	/**
	 * ��ʼ�ڴ�
	 * 
	 */
	public void setStartClass(Integer startClass) {
		this.startClass = startClass;
	}

	/**
	 * ��ʼ�ڴ�
	 * 
	 */
	public Integer getStartClass() {
		return this.startClass;
	}

	/**
	 * ����ڴ�
	 * 
	 */
	public void setEndClass(Integer endClass) {
		this.endClass = endClass;
	}

	/**
	 * ����ڴ�
	 * 
	 */
	public Integer getEndClass() {
		return this.endClass;
	}

	/**
	 */
	public void setTimetableAppointment(TimetableAppointment timetableAppointment) {
		this.timetableAppointment = timetableAppointment;
	}

	/**
	 */
	public TimetableAppointment getTimetableAppointment() {
		return timetableAppointment;
	}

	/**
	 */
	public TimetableAppointmentSameNumber() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(TimetableAppointmentSameNumber that) {
		setId(that.getId());
		setCreatedDate(that.getCreatedDate());
		setUpdatedDate(that.getUpdatedDate());
		setCreatedBy(that.getCreatedBy());
		setUpdatedBy(that.getUpdatedBy());
		setTotalWeeks(that.getTotalWeeks());
		setWeekday(that.getWeekday());
		setStartWeek(that.getStartWeek());
		setEndWeek(that.getEndWeek());
		setTotalClasses(that.getTotalClasses());
		setStartClass(that.getStartClass());
		setEndClass(that.getEndClass());
		setTimetableAppointment(that.getTimetableAppointment());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("id=[").append(id).append("] ");
		buffer.append("createdDate=[").append(createdDate).append("] ");
		buffer.append("updatedDate=[").append(updatedDate).append("] ");
		buffer.append("createdBy=[").append(createdBy).append("] ");
		buffer.append("updatedBy=[").append(updatedBy).append("] ");
		buffer.append("totalWeeks=[").append(totalWeeks).append("] ");
		buffer.append("weekday=[").append(weekday).append("] ");
		buffer.append("startWeek=[").append(startWeek).append("] ");
		buffer.append("endWeek=[").append(endWeek).append("] ");
		buffer.append("totalClasses=[").append(totalClasses).append("] ");
		buffer.append("startClass=[").append(startClass).append("] ");
		buffer.append("endClass=[").append(endClass).append("] ");

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
		if (!(obj instanceof TimetableAppointmentSameNumber))
			return false;
		TimetableAppointmentSameNumber equalCheck = (TimetableAppointmentSameNumber) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
