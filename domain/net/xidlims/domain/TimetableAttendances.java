package net.xidlims.domain;

import java.io.Serializable;

import java.lang.StringBuilder;

import java.util.Calendar;

import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import javax.xml.bind.annotation.*;

import javax.persistence.*;

/**
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "findAllTimetableAttendancess", query = "select myTimetableAttendances from TimetableAttendances myTimetableAttendances"),
		@NamedQuery(name = "findTimetableAttendancesByActualAttendance", query = "select myTimetableAttendances from TimetableAttendances myTimetableAttendances where myTimetableAttendances.actualAttendance = ?1"),
		@NamedQuery(name = "findTimetableAttendancesByAttendDate", query = "select myTimetableAttendances from TimetableAttendances myTimetableAttendances where myTimetableAttendances.attendDate = ?1"),
		@NamedQuery(name = "findTimetableAttendancesByAttendanceMachine", query = "select myTimetableAttendances from TimetableAttendances myTimetableAttendances where myTimetableAttendances.attendanceMachine = ?1"),
		@NamedQuery(name = "findTimetableAttendancesByAttendanceStatus", query = "select myTimetableAttendances from TimetableAttendances myTimetableAttendances where myTimetableAttendances.attendanceStatus = ?1"),
		@NamedQuery(name = "findTimetableAttendancesByAttendanceStatusContaining", query = "select myTimetableAttendances from TimetableAttendances myTimetableAttendances where myTimetableAttendances.attendanceStatus like ?1"),
		@NamedQuery(name = "findTimetableAttendancesByCreatedBy", query = "select myTimetableAttendances from TimetableAttendances myTimetableAttendances where myTimetableAttendances.createdBy = ?1"),
		@NamedQuery(name = "findTimetableAttendancesByCreatedDate", query = "select myTimetableAttendances from TimetableAttendances myTimetableAttendances where myTimetableAttendances.createdDate = ?1"),
		@NamedQuery(name = "findTimetableAttendancesByDetailId", query = "select myTimetableAttendances from TimetableAttendances myTimetableAttendances where myTimetableAttendances.detailId = ?1"),
		@NamedQuery(name = "findTimetableAttendancesByEndClass", query = "select myTimetableAttendances from TimetableAttendances myTimetableAttendances where myTimetableAttendances.endClass = ?1"),
		@NamedQuery(name = "findTimetableAttendancesByEndDateTime", query = "select myTimetableAttendances from TimetableAttendances myTimetableAttendances where myTimetableAttendances.endDateTime = ?1"),
		@NamedQuery(name = "findTimetableAttendancesById", query = "select myTimetableAttendances from TimetableAttendances myTimetableAttendances where myTimetableAttendances.id = ?1"),
		@NamedQuery(name = "findTimetableAttendancesByLabNumber", query = "select myTimetableAttendances from TimetableAttendances myTimetableAttendances where myTimetableAttendances.labNumber = ?1"),
		@NamedQuery(name = "findTimetableAttendancesByMemo", query = "select myTimetableAttendances from TimetableAttendances myTimetableAttendances where myTimetableAttendances.memo = ?1"),
		@NamedQuery(name = "findTimetableAttendancesByMemoContaining", query = "select myTimetableAttendances from TimetableAttendances myTimetableAttendances where myTimetableAttendances.memo like ?1"),
		@NamedQuery(name = "findTimetableAttendancesByPrimaryKey", query = "select myTimetableAttendances from TimetableAttendances myTimetableAttendances where myTimetableAttendances.id = ?1"),
		@NamedQuery(name = "findTimetableAttendancesByStartClass", query = "select myTimetableAttendances from TimetableAttendances myTimetableAttendances where myTimetableAttendances.startClass = ?1"),
		@NamedQuery(name = "findTimetableAttendancesByStartDateTime", query = "select myTimetableAttendances from TimetableAttendances myTimetableAttendances where myTimetableAttendances.startDateTime = ?1"),
		@NamedQuery(name = "findTimetableAttendancesByUpdatedDate", query = "select myTimetableAttendances from TimetableAttendances myTimetableAttendances where myTimetableAttendances.updatedDate = ?1"),
		@NamedQuery(name = "findTimetableAttendancesByUserNumber", query = "select myTimetableAttendances from TimetableAttendances myTimetableAttendances where myTimetableAttendances.userNumber = ?1"),
		@NamedQuery(name = "findTimetableAttendancesByUserNumberContaining", query = "select myTimetableAttendances from TimetableAttendances myTimetableAttendances where myTimetableAttendances.userNumber like ?1"),
		@NamedQuery(name = "findTimetableAttendancesByWeek", query = "select myTimetableAttendances from TimetableAttendances myTimetableAttendances where myTimetableAttendances.week = ?1"),
		@NamedQuery(name = "findTimetableAttendancesByWeekday", query = "select myTimetableAttendances from TimetableAttendances myTimetableAttendances where myTimetableAttendances.weekday = ?1") })
@Table(catalog = "xidlims", name = "timetable_attendances")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "TimetableAttendances")
public class TimetableAttendances implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * �ڴο���ʱ���
	 * 
	 */

	@Column(name = "id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
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
	 * ѧ������
	 * 
	 */

	@Column(name = "user_number", length = 40)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String userNumber;
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
	 * �γ̰���
	 * 
	 */

	@Column(name = "detail_id")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer detailId;
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
	 * ���ŵĽڴ�
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
	 * ��ʼʱ��
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "start_date_time")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar startDateTime;
	/**
	 * ����ʱ��
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "end_date_time")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar endDateTime;
	/**
	 * ʵ���ұ��
	 * 
	 */

	@Column(name = "lab_number")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer labNumber;
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

	@Column(name = "created_by")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer createdBy;
	/**
	 * ����״̬
	 * 
	 */

	@Column(name = "attendance_status", length = 20)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String attendanceStatus;

	/**
	 * �ڴο���ʱ���
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * �ڴο���ʱ���
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
	 * ѧ������
	 * 
	 */
	public void setUserNumber(String userNumber) {
		this.userNumber = userNumber;
	}

	/**
	 * ѧ������
	 * 
	 */
	public String getUserNumber() {
		return this.userNumber;
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
	 * �γ̰���
	 * 
	 */
	public void setDetailId(Integer detailId) {
		this.detailId = detailId;
	}

	/**
	 * �γ̰���
	 * 
	 */
	public Integer getDetailId() {
		return this.detailId;
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
	public void setStartClass(Integer startClass) {
		this.startClass = startClass;
	}

	/**
	 * ���ŵĽڴ�
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
	 * ��ʼʱ��
	 * 
	 */
	public void setStartDateTime(Calendar startDateTime) {
		this.startDateTime = startDateTime;
	}

	/**
	 * ��ʼʱ��
	 * 
	 */
	public Calendar getStartDateTime() {
		return this.startDateTime;
	}

	/**
	 * ����ʱ��
	 * 
	 */
	public void setEndDateTime(Calendar endDateTime) {
		this.endDateTime = endDateTime;
	}

	/**
	 * ����ʱ��
	 * 
	 */
	public Calendar getEndDateTime() {
		return this.endDateTime;
	}

	/**
	 * ʵ���ұ��
	 * 
	 */
	public void setLabNumber(Integer labNumber) {
		this.labNumber = labNumber;
	}

	/**
	 * ʵ���ұ��
	 * 
	 */
	public Integer getLabNumber() {
		return this.labNumber;
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
	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * ������
	 * 
	 */
	public Integer getCreatedBy() {
		return this.createdBy;
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
	public TimetableAttendances() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(TimetableAttendances that) {
		setId(that.getId());
		setAttendDate(that.getAttendDate());
		setUserNumber(that.getUserNumber());
		setAttendanceMachine(that.getAttendanceMachine());
		setActualAttendance(that.getActualAttendance());
		setMemo(that.getMemo());
		setDetailId(that.getDetailId());
		setWeekday(that.getWeekday());
		setWeek(that.getWeek());
		setStartClass(that.getStartClass());
		setEndClass(that.getEndClass());
		setStartDateTime(that.getStartDateTime());
		setEndDateTime(that.getEndDateTime());
		setLabNumber(that.getLabNumber());
		setCreatedDate(that.getCreatedDate());
		setUpdatedDate(that.getUpdatedDate());
		setCreatedBy(that.getCreatedBy());
		setAttendanceStatus(that.getAttendanceStatus());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("id=[").append(id).append("] ");
		buffer.append("attendDate=[").append(attendDate).append("] ");
		buffer.append("userNumber=[").append(userNumber).append("] ");
		buffer.append("attendanceMachine=[").append(attendanceMachine).append("] ");
		buffer.append("actualAttendance=[").append(actualAttendance).append("] ");
		buffer.append("memo=[").append(memo).append("] ");
		buffer.append("detailId=[").append(detailId).append("] ");
		buffer.append("weekday=[").append(weekday).append("] ");
		buffer.append("week=[").append(week).append("] ");
		buffer.append("startClass=[").append(startClass).append("] ");
		buffer.append("endClass=[").append(endClass).append("] ");
		buffer.append("startDateTime=[").append(startDateTime).append("] ");
		buffer.append("endDateTime=[").append(endDateTime).append("] ");
		buffer.append("labNumber=[").append(labNumber).append("] ");
		buffer.append("createdDate=[").append(createdDate).append("] ");
		buffer.append("updatedDate=[").append(updatedDate).append("] ");
		buffer.append("createdBy=[").append(createdBy).append("] ");
		buffer.append("attendanceStatus=[").append(attendanceStatus).append("] ");

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
		if (!(obj instanceof TimetableAttendances))
			return false;
		TimetableAttendances equalCheck = (TimetableAttendances) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
