package net.xidlims.domain;

import java.io.Serializable;

import java.lang.StringBuilder;
import java.math.BigDecimal;

import java.util.Calendar;


import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.FetchType;
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
		@NamedQuery(name = "findAllTimetableAttendances", query = "select myTimetableAttendance from TimetableAttendance myTimetableAttendance"),
		@NamedQuery(name = "findTimetableAttendanceByActualAttendance", query = "select myTimetableAttendance from TimetableAttendance myTimetableAttendance where myTimetableAttendance.actualAttendance = ?1"),
		@NamedQuery(name = "findTimetableAttendanceByArrangeClass", query = "select myTimetableAttendance from TimetableAttendance myTimetableAttendance where myTimetableAttendance.arrangeClass = ?1"),
		@NamedQuery(name = "findTimetableAttendanceByAttendDate", query = "select myTimetableAttendance from TimetableAttendance myTimetableAttendance where myTimetableAttendance.attendDate = ?1"),
		@NamedQuery(name = "findTimetableAttendanceByAttendanceMachine", query = "select myTimetableAttendance from TimetableAttendance myTimetableAttendance where myTimetableAttendance.attendanceMachine = ?1"),
		@NamedQuery(name = "findTimetableAttendanceByAttendanceStatus", query = "select myTimetableAttendance from TimetableAttendance myTimetableAttendance where myTimetableAttendance.attendanceStatus = ?1"),
		@NamedQuery(name = "findTimetableAttendanceByAttendanceStatusContaining", query = "select myTimetableAttendance from TimetableAttendance myTimetableAttendance where myTimetableAttendance.attendanceStatus like ?1"),
		@NamedQuery(name = "findTimetableAttendanceByCreatedDate", query = "select myTimetableAttendance from TimetableAttendance myTimetableAttendance where myTimetableAttendance.createdDate = ?1"),
		@NamedQuery(name = "findTimetableAttendanceById", query = "select myTimetableAttendance from TimetableAttendance myTimetableAttendance where myTimetableAttendance.id = ?1"),
		@NamedQuery(name = "findTimetableAttendanceByMemo", query = "select myTimetableAttendance from TimetableAttendance myTimetableAttendance where myTimetableAttendance.memo = ?1"),
		@NamedQuery(name = "findTimetableAttendanceByMemoContaining", query = "select myTimetableAttendance from TimetableAttendance myTimetableAttendance where myTimetableAttendance.memo like ?1"),
		@NamedQuery(name = "findTimetableAttendanceByPrimaryKey", query = "select myTimetableAttendance from TimetableAttendance myTimetableAttendance where myTimetableAttendance.id = ?1"),
		@NamedQuery(name = "findTimetableAttendanceByUpdatedDate", query = "select myTimetableAttendance from TimetableAttendance myTimetableAttendance where myTimetableAttendance.updatedDate = ?1"),
		@NamedQuery(name = "findTimetableAttendanceByWeek", query = "select myTimetableAttendance from TimetableAttendance myTimetableAttendance where myTimetableAttendance.week = ?1"),
		@NamedQuery(name = "findTimetableAttendanceByWeekday", query = "select myTimetableAttendance from TimetableAttendance myTimetableAttendance where myTimetableAttendance.weekday = ?1") })
@Table(catalog = "xidlims", name = "timetable_attendance")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "TimetableAttendance")
public class TimetableAttendance implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * �ܿ���ʱ���
	 * 
	 */

	@Column(name = "id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	@XmlElement
	Integer id;
	/**
	 * ����ʱ��
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "attend_date")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar attendDate;
	/**
	 * ��
	 * 
	 */

	@Column(name = "attendance_machine")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer attendanceMachine;
	/**
	 * ʵ�ʿ���
	 * 
	 */

	@Column(name = "actual_attendance")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer actualAttendance;
	/**
	 * ��ע
	 * 
	 */

	@Column(name = "memo", length = 40)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String memo;
	/**
	 * �ܼ�
	 * 
	 */

	@Column(name = "weekday")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer weekday;
	/**
	 * ��
	 * 
	 */

	@Column(name = "week")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer week;
	/**
	 * ����
	 * 
	 */

	@Column(name = "score")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal score;
	/**
	 * ���ŵĽڴ�
	 * 
	 */

	@Column(name = "arrange_class")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer arrangeClass;
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
	 * ����״̬
	 * 
	 */

	@Column(name = "attendance_status", length = 20)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String attendanceStatus;

	/**
	 * ��ע
	 * 
	 */

	@Column(name = "course_no", length = 50)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String courseNo;
	
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "created_by", referencedColumnName = "username") })
	@XmlTransient
	User userByCreatedBy;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "user_number", referencedColumnName = "username") })
	@XmlTransient
	User userByUserNumber;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "appointment_id", referencedColumnName = "id") })
	@XmlTransient
	TimetableAppointment timetableAppointment;

	/**
	 * �ܿ���ʱ���
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * �ܿ���ʱ���
	 * 
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * ����ʱ��
	 * 
	 */
	public void setAttendDate(Calendar attendDate) {
		this.attendDate = attendDate;
	}

	/**
	 * ����ʱ��
	 * 
	 */
	public Calendar getAttendDate() {
		return this.attendDate;
	}

	/**
	 * ��
	 * 
	 */
	public void setAttendanceMachine(Integer attendanceMachine) {
		this.attendanceMachine = attendanceMachine;
	}

	/**
	 * ��
	 * 
	 */
	public Integer getAttendanceMachine() {
		return this.attendanceMachine;
	}

	/**
	 * ʵ�ʿ���
	 * 
	 */
	public void setActualAttendance(Integer actualAttendance) {
		this.actualAttendance = actualAttendance;
	}

	/**
	 * ʵ�ʿ���
	 * 
	 */
	public Integer getActualAttendance() {
		return this.actualAttendance;
	}

	
	public String getCourseNo() {
		return courseNo;
	}

	public void setCourseNo(String courseNo) {
		this.courseNo = courseNo;
	}
	/**
	 * ��ע
	 * 
	 */
	public void setMemo(String memo) {
		this.memo = memo;
	}

	/**
	 * ��ע
	 * 
	 */
	public String getMemo() {
		return this.memo;
	}

	/**
	 * �ܼ�
	 * 
	 */
	public void setWeekday(Integer weekday) {
		this.weekday = weekday;
	}

	/**
	 * �ܼ�
	 * 
	 */
	public Integer getWeekday() {
		return this.weekday;
	}

	/**
	 * ��
	 * 
	 */
	public void setWeek(Integer week) {
		this.week = week;
	}

	/**
	 * ��
	 * 
	 */
	public Integer getWeek() {
		return this.week;
	}

	/**
	 * ���ŵĽڴ�
	 * 
	 */
	public void setArrangeClass(Integer arrangeClass) {
		this.arrangeClass = arrangeClass;
	}

	/**
	 * ���ŵĽڴ�
	 * 
	 */
	public Integer getArrangeClass() {
		return this.arrangeClass;
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
	 * ����״̬
	 * 
	 */
	public void setAttendanceStatus(String attendanceStatus) {
		this.attendanceStatus = attendanceStatus;
	}

	/**
	 * ����״̬
	 * 
	 */
	public String getAttendanceStatus() {
		return this.attendanceStatus;
	}

	/**
	 */
	public void setUserByCreatedBy(User userByCreatedBy) {
		this.userByCreatedBy = userByCreatedBy;
	}

	/**
	 */
	@JsonIgnore
	public User getUserByCreatedBy() {
		return userByCreatedBy;
	}

	/**
	 */
	public void setUserByUserNumber(User userByUserNumber) {
		this.userByUserNumber = userByUserNumber;
	}

	/**
	 */
	@JsonIgnore
	public User getUserByUserNumber() {
		return userByUserNumber;
	}
	
	public BigDecimal getScore() {
		return score;
	}

	public void setScore(BigDecimal score) {
		this.score = score;
	}

	/**
	 */
	public void setTimetableAppointment(TimetableAppointment timetableAppointment) {
		this.timetableAppointment = timetableAppointment;
	}

	/**
	 */
	@JsonIgnore
	public TimetableAppointment getTimetableAppointment() {
		return timetableAppointment;
	}

	/**
	 */
	public TimetableAttendance() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(TimetableAttendance that) {
		setId(that.getId());
		setAttendDate(that.getAttendDate());
		setAttendanceMachine(that.getAttendanceMachine());
		setActualAttendance(that.getActualAttendance());
		setMemo(that.getMemo());
		setWeekday(that.getWeekday());
		setWeek(that.getWeek());
		setArrangeClass(that.getArrangeClass());
		setCreatedDate(that.getCreatedDate());
		setUpdatedDate(that.getUpdatedDate());
		setAttendanceStatus(that.getAttendanceStatus());
		setUserByCreatedBy(that.getUserByCreatedBy());
		setUserByUserNumber(that.getUserByUserNumber());
		setTimetableAppointment(that.getTimetableAppointment());
		setScore(that.getScore());
		setCourseNo(that.getCourseNo());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("id=[").append(id).append("] ");
		buffer.append("attendDate=[").append(attendDate).append("] ");
		buffer.append("attendanceMachine=[").append(attendanceMachine).append("] ");
		buffer.append("actualAttendance=[").append(actualAttendance).append("] ");
		buffer.append("memo=[").append(memo).append("] ");
		buffer.append("weekday=[").append(weekday).append("] ");
		buffer.append("week=[").append(week).append("] ");
		buffer.append("arrangeClass=[").append(arrangeClass).append("] ");
		buffer.append("createdDate=[").append(createdDate).append("] ");
		buffer.append("updatedDate=[").append(updatedDate).append("] ");
		buffer.append("attendanceStatus=[").append(attendanceStatus).append("] ");
		buffer.append("score=[").append(score).append("] ");
		buffer.append("courseNo=[").append(courseNo).append("] ");
		
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
		if (!(obj instanceof TimetableAttendance))
			return false;
		TimetableAttendance equalCheck = (TimetableAttendance) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
