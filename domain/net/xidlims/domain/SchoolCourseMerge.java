package net.xidlims.domain;

import java.io.Serializable;

import java.lang.StringBuilder;
import java.util.Calendar;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import javax.xml.bind.annotation.*;

import javax.persistence.*;

import org.codehaus.jackson.annotate.JsonIgnore;

/**
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "findAllSchoolCourseMerges", query = "select mySchoolCourseMerge from SchoolCourseMerge mySchoolCourseMerge"),
		@NamedQuery(name = "findSchoolCourseMergeByCourseDetailNo", query = "select mySchoolCourseMerge from SchoolCourseMerge mySchoolCourseMerge where mySchoolCourseMerge.courseDetailNo = ?1"),
		@NamedQuery(name = "findSchoolCourseMergeByCourseDetailNoContaining", query = "select mySchoolCourseMerge from SchoolCourseMerge mySchoolCourseMerge where mySchoolCourseMerge.courseDetailNo like ?1"),
		@NamedQuery(name = "findSchoolCourseMergeByCourseName", query = "select mySchoolCourseMerge from SchoolCourseMerge mySchoolCourseMerge where mySchoolCourseMerge.courseName = ?1"),
		@NamedQuery(name = "findSchoolCourseMergeByCourseNameContaining", query = "select mySchoolCourseMerge from SchoolCourseMerge mySchoolCourseMerge where mySchoolCourseMerge.courseName like ?1"),
		@NamedQuery(name = "findSchoolCourseMergeByCourseNo", query = "select mySchoolCourseMerge from SchoolCourseMerge mySchoolCourseMerge where mySchoolCourseMerge.courseNo = ?1"),
		@NamedQuery(name = "findSchoolCourseMergeByCourseNoContaining", query = "select mySchoolCourseMerge from SchoolCourseMerge mySchoolCourseMerge where mySchoolCourseMerge.courseNo like ?1"),
		@NamedQuery(name = "findSchoolCourseMergeByCourseNumber", query = "select mySchoolCourseMerge from SchoolCourseMerge mySchoolCourseMerge where mySchoolCourseMerge.courseNumber = ?1"),
		@NamedQuery(name = "findSchoolCourseMergeByCourseNumberContaining", query = "select mySchoolCourseMerge from SchoolCourseMerge mySchoolCourseMerge where mySchoolCourseMerge.courseNumber like ?1"),
		@NamedQuery(name = "findSchoolCourseMergeById", query = "select mySchoolCourseMerge from SchoolCourseMerge mySchoolCourseMerge where mySchoolCourseMerge.id = ?1"),
		@NamedQuery(name = "findSchoolCourseMergeByPrimaryKey", query = "select mySchoolCourseMerge from SchoolCourseMerge mySchoolCourseMerge where mySchoolCourseMerge.id = ?1"),
		@NamedQuery(name = "findSchoolCourseMergeByStudentNumbers", query = "select mySchoolCourseMerge from SchoolCourseMerge mySchoolCourseMerge where mySchoolCourseMerge.studentNumbers = ?1"),
		@NamedQuery(name = "findSchoolCourseMergeByStudentNumbersContaining", query = "select mySchoolCourseMerge from SchoolCourseMerge mySchoolCourseMerge where mySchoolCourseMerge.studentNumbers like ?1") })
@Table(catalog = "xidlims", name = "school_course_merge")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "SchoolCourseMerge")
public class SchoolCourseMerge implements Serializable {
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
	 * �γ̱��
	 * 
	 */

	@Column(name = "course_no")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String courseNo;
	/**
	 * �����
	 * 
	 */

	@Column(name = "course_number")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String courseNumber;
	/**
	 * ѡ������
	 * 
	 */

	@Column(name = "course_detail_no")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String courseDetailNo;
	/**
	 * ѧ������
	 * 
	 */

	@Column(name = "student_numbers")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer studentNumbers;
	/**
	 * �γ����
	 * 
	 */

	@Column(name = "course_name")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String courseName;

	
	/**
	 * ѧ������
	 * 
	 */

	@Column(name = "term_id")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer termId;
	
	/**
	 * ����ʱ��
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "course_start_time")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar courseStartTime;
	
	
	/**
	 * ״̬
	 * 
	 */

	@Column(name = "course_end_minute")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer courseEndMinute;
	
	/**
	 * ״̬
	 * 
	 */

	@Column(name = "withdrawal_minute")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer withdrawalMinute;
	
	/**
	 * ״̬
	 * 
	 */

	@Column(name = "withdrawal_times")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer withdrawalTimes;
	
	/**
	 * ��ע
	 * 
	 */

	@Column(name = "course_requirement", columnDefinition = "TEXT")
	@Basic(fetch = FetchType.EAGER)
	@Lob
	@XmlElement
	String courseRequirement;
	
	/**
	 * �γ̴���
	 * 
	 */

	@Column(name = "course_code", length = 255)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String courseCode;
	
	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	public String getCourseRequirement() {
		return courseRequirement;
	}

	public void setCourseRequirement(String courseRequirement) {
		this.courseRequirement = courseRequirement;
	}

	public Calendar getCourseStartTime() {
		return courseStartTime;
	}

	public void setCourseStartTime(Calendar courseStartTime) {
		this.courseStartTime = courseStartTime;
	}

	public Integer getCourseEndMinute() {
		return courseEndMinute;
	}

	public void setCourseEndMinute(Integer courseEndMinute) {
		this.courseEndMinute = courseEndMinute;
	}

	public Integer getWithdrawalMinute() {
		return withdrawalMinute;
	}

	public void setWithdrawalMinute(Integer withdrawalMinute) {
		this.withdrawalMinute = withdrawalMinute;
	}

	public Integer getWithdrawalTimes() {
		return withdrawalTimes;
	}

	public void setWithdrawalTimes(Integer withdrawalTimes) {
		this.withdrawalTimes = withdrawalTimes;
	}

	public Integer getTermId() {
		return termId;
	}

	public void setTermId(Integer termId) {
		this.termId = termId;
	}

	
	/**
	 * ����ʱ��
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "course_start")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar courseStart;
	
	/**
	 * ����ʱ��
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "course_end")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar courseEnd;
	
	

	@Column(name = "is_complete")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer isComplete;
	
	public Integer getIsComplete() {
		return isComplete;
	}

	public void setIsComplete(Integer isComplete) {
		this.isComplete = isComplete;
	}
	
	public Calendar getCourseStart() {
		return courseStart;
	}

	public void setCourseStart(Calendar courseStart) {
		this.courseStart = courseStart;
	}

	public Calendar getCourseEnd() {
		return courseEnd;
	}

	public void setCourseEnd(Calendar courseEnd) {
		this.courseEnd = courseEnd;
	}
	/**
	 */
	@OneToMany(mappedBy = "schoolCourseMerge", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.SchoolCourseDetail> schoolCourseDetails;
	
	/**
	 */
	@OneToMany(mappedBy = "schoolCourseMerge", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.TimetableAppointment> timetableAppointments;
	
	/**
	 */
	public void setTimetableAppointments(Set<TimetableAppointment> timetableAppointments) {
		this.timetableAppointments = timetableAppointments;
	}

	/**
	 */
	@JsonIgnore
	public Set<TimetableAppointment> getTimetableAppointments() {
		if (timetableAppointments == null) {
			timetableAppointments = new java.util.LinkedHashSet<net.xidlims.domain.TimetableAppointment>();
		}
		return timetableAppointments;
	}

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
	 * �γ̱��
	 * 
	 */
	public void setCourseNo(String courseNo) {
		this.courseNo = courseNo;
	}

	/**
	 * �γ̱��
	 * 
	 */
	public String getCourseNo() {
		return this.courseNo;
	}

	/**
	 * �����
	 * 
	 */
	public void setCourseNumber(String courseNumber) {
		this.courseNumber = courseNumber;
	}

	/**
	 * �����
	 * 
	 */
	public String getCourseNumber() {
		return this.courseNumber;
	}

	/**
	 * ѡ������
	 * 
	 */
	public void setCourseDetailNo(String courseDetailNo) {
		this.courseDetailNo = courseDetailNo;
	}

	/**
	 * ѡ������
	 * 
	 */
	public String getCourseDetailNo() {
		return this.courseDetailNo;
	}

	/**
	 * ѧ������
	 * 
	 */
	public void setStudentNumbers(Integer studentNumbers) {
		this.studentNumbers = studentNumbers;
	}

	/**
	 * ѧ������
	 * 
	 */
	public Integer getStudentNumbers() {
		return this.studentNumbers;
	}

	/**
	 * �γ����
	 * 
	 */
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	/**
	 * �γ����
	 * 
	 */
	public String getCourseName() {
		return this.courseName;
	}

	
	/**
	 */
	public void setSchoolCourseDetails(Set<SchoolCourseDetail> schoolCourseDetails) {
		this.schoolCourseDetails = schoolCourseDetails;
	}

	/**
	 */
	@JsonIgnore
	public Set<SchoolCourseDetail> getSchoolCourseDetails() {
		if (schoolCourseDetails == null) {
			schoolCourseDetails = new java.util.LinkedHashSet<net.xidlims.domain.SchoolCourseDetail>();
		}
		return schoolCourseDetails;
	}
	/**
	 */
	public SchoolCourseMerge() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(SchoolCourseMerge that) {
		setId(that.getId());
		setCourseNo(that.getCourseNo());
		setCourseNumber(that.getCourseNumber());
		setCourseDetailNo(that.getCourseDetailNo());
		setStudentNumbers(that.getStudentNumbers());
		setCourseName(that.getCourseName());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("id=[").append(id).append("] ");
		buffer.append("courseNo=[").append(courseNo).append("] ");
		buffer.append("courseNumber=[").append(courseNumber).append("] ");
		buffer.append("courseDetailNo=[").append(courseDetailNo).append("] ");
		buffer.append("studentNumbers=[").append(studentNumbers).append("] ");
		buffer.append("courseName=[").append(courseName).append("] ");

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
		if (!(obj instanceof SchoolCourseMerge))
			return false;
		SchoolCourseMerge equalCheck = (SchoolCourseMerge) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
