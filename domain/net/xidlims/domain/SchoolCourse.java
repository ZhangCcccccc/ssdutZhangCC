package net.xidlims.domain;

import java.io.Serializable;

import java.lang.StringBuilder;

import java.util.Calendar;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import org.codehaus.jackson.annotate.JsonIgnore;

import javax.xml.bind.annotation.*;

import javax.persistence.*;

/**
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "findAllSchoolCourses", query = "select mySchoolCourse from SchoolCourse mySchoolCourse"),
		@NamedQuery(name = "findSchoolCourseByClassesName", query = "select mySchoolCourse from SchoolCourse mySchoolCourse where mySchoolCourse.classesName = ?1"),
		@NamedQuery(name = "findSchoolCourseByClassesNameContaining", query = "select mySchoolCourse from SchoolCourse mySchoolCourse where mySchoolCourse.classesName like ?1"),
		@NamedQuery(name = "findSchoolCourseByCourseAddressType", query = "select mySchoolCourse from SchoolCourse mySchoolCourse where mySchoolCourse.courseAddressType = ?1"),
		@NamedQuery(name = "findSchoolCourseByCourseAddressTypeContaining", query = "select mySchoolCourse from SchoolCourse mySchoolCourse where mySchoolCourse.courseAddressType like ?1"),
		@NamedQuery(name = "findSchoolCourseByCourseCode", query = "select mySchoolCourse from SchoolCourse mySchoolCourse where mySchoolCourse.courseCode = ?1"),
		@NamedQuery(name = "findSchoolCourseByCourseCodeContaining", query = "select mySchoolCourse from SchoolCourse mySchoolCourse where mySchoolCourse.courseCode like ?1"),
		@NamedQuery(name = "findSchoolCourseByCourseName", query = "select mySchoolCourse from SchoolCourse mySchoolCourse where mySchoolCourse.courseName = ?1"),
		@NamedQuery(name = "findSchoolCourseByCourseNameContaining", query = "select mySchoolCourse from SchoolCourse mySchoolCourse where mySchoolCourse.courseName like ?1"),
		@NamedQuery(name = "findSchoolCourseByCourseNo", query = "select mySchoolCourse from SchoolCourse mySchoolCourse where mySchoolCourse.courseNo = ?1"),
		@NamedQuery(name = "findSchoolCourseByCourseNoContaining", query = "select mySchoolCourse from SchoolCourse mySchoolCourse where mySchoolCourse.courseNo like ?1"),
		@NamedQuery(name = "findSchoolCourseByCourseType", query = "select mySchoolCourse from SchoolCourse mySchoolCourse where mySchoolCourse.courseType = ?1"),
		@NamedQuery(name = "findSchoolCourseByCourseTypeContaining", query = "select mySchoolCourse from SchoolCourse mySchoolCourse where mySchoolCourse.courseType like ?1"),
		@NamedQuery(name = "findSchoolCourseByCreatedDate", query = "select mySchoolCourse from SchoolCourse mySchoolCourse where mySchoolCourse.createdDate = ?1"),
		@NamedQuery(name = "findSchoolCourseByCredits", query = "select mySchoolCourse from SchoolCourse mySchoolCourse where mySchoolCourse.credits = ?1"),
		@NamedQuery(name = "findSchoolCourseByCreditsContaining", query = "select mySchoolCourse from SchoolCourse mySchoolCourse where mySchoolCourse.credits like ?1"),
		@NamedQuery(name = "findSchoolCourseByMemo", query = "select mySchoolCourse from SchoolCourse mySchoolCourse where mySchoolCourse.memo = ?1"),
		@NamedQuery(name = "findSchoolCourseByMemoContaining", query = "select mySchoolCourse from SchoolCourse mySchoolCourse where mySchoolCourse.memo like ?1"),
		@NamedQuery(name = "findSchoolCourseByPlanLabTime", query = "select mySchoolCourse from SchoolCourse mySchoolCourse where mySchoolCourse.planLabTime = ?1"),
		@NamedQuery(name = "findSchoolCourseByPlanLabTimeContaining", query = "select mySchoolCourse from SchoolCourse mySchoolCourse where mySchoolCourse.planLabTime like ?1"),
		@NamedQuery(name = "findSchoolCourseByPlanStudentNumber", query = "select mySchoolCourse from SchoolCourse mySchoolCourse where mySchoolCourse.planStudentNumber = ?1"),
		@NamedQuery(name = "findSchoolCourseByPlanStudentNumberContaining", query = "select mySchoolCourse from SchoolCourse mySchoolCourse where mySchoolCourse.planStudentNumber like ?1"),
		@NamedQuery(name = "findSchoolCourseByPrimaryKey", query = "select mySchoolCourse from SchoolCourse mySchoolCourse where mySchoolCourse.courseNo = ?1"),
		@NamedQuery(name = "findSchoolCourseByState", query = "select mySchoolCourse from SchoolCourse mySchoolCourse where mySchoolCourse.state = ?1"),
		@NamedQuery(name = "findSchoolCourseByUpdatedDate", query = "select mySchoolCourse from SchoolCourse mySchoolCourse where mySchoolCourse.updatedDate = ?1") })
@Table(catalog = "xidlims", name = "school_course")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "SchoolCourse")
@XmlRootElement(namespace = "xidlims/net/xidlims/domain")
public class SchoolCourse implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ��ɺ�Ŀγ̱�ţ�Ψһ�ԣ�
	 * 
	 */

	@Column(name = "course_no", length = 100, nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	String courseNo;
	/**
	 * �γ̴���
	 * 
	 */

	@Column(name = "course_code", length = 40)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String courseCode;
	/**
	 * �γ����
	 * 
	 */

	@Column(name = "course_name", length = 200)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String courseName;
	/**
	 * �γ�����
	 * 
	 */

	@Column(name = "course_type", length = 100)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String courseType;
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
	 * ��ע
	 * 
	 */

	@Column(name = "memo", length = 100)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String memo;
	/**
	 * ��ѧ�����
	 * 
	 */

	@Column(name = "classes_name", length = 200)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String classesName;
	
	/**
	 * ��ѧ�����
	 * 
	 */

	@Column(name = "class_hour", length = 200)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String classHour;
	/**
	 * ѧ��
	 * 
	 */

	@Column(name = "credits", length = 10)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String credits;
	/**
	 * �ƻ�����
	 * 
	 */

	@Column(name = "plan_student_number", length = 100)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String planStudentNumber;
	/**
	 * �ƻ�ʵ����ѧʱ
	 * 
	 */

	@Column(name = "plan_lab_time", length = 20)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String planLabTime;
	/**
	 * �Ͽεص�
	 * 
	 */

	@Column(name = "course_address_type", length = 100)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String courseAddressType;
	/**
	 * ״̬
	 * 
	 */

	@Column(name = "state")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer state;

	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "major_number", referencedColumnName = "m_number") })
	@XmlTransient
	SystemMajor12 systemMajor12;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "term_id", referencedColumnName = "id") })
	@XmlTransient
	SchoolTerm schoolTerm;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "course_status", referencedColumnName = "id") })
	@XmlTransient
	CDictionary CDictionaryByCourseStatus;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "created_by", referencedColumnName = "username") })
	@XmlTransient
	User userByCreatedBy;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "student_type", referencedColumnName = "id") })
	@XmlTransient
	CDictionary CDictionaryByStudentType;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "teacher", referencedColumnName = "username") })
	@XmlTransient
	User userByTeacher;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "course_number", referencedColumnName = "course_number") })
	@XmlTransient
	SchoolCourseInfo schoolCourseInfo;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "academy_number", referencedColumnName = "academy_number") })
	@XmlTransient
	SchoolAcademy schoolAcademy;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "updated_by", referencedColumnName = "username") })
	@XmlTransient
	User userByUpdatedBy;
	/**
	 */
	@OneToMany(mappedBy = "schoolCourse", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.SchoolCourseDetail> schoolCourseDetails;
	/**
	 */
	@OneToMany(mappedBy = "schoolCourse", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.TimetableAppointment> timetableAppointments;
	
	@OneToMany(mappedBy = "schoolCourse", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.LabReservation> labReservations;
	
	@OneToMany(mappedBy = "schoolCourse", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.TCourseSite> tCourseSites;
	
	
	@JsonIgnore
	public java.util.Set<net.xidlims.domain.LabReservation> getLabReservations() {
		return labReservations;
	}

	public void setLabReservations(java.util.Set<net.xidlims.domain.LabReservation> labReservations) {
		this.labReservations = labReservations;
	}

	/**
	 * ��ɺ�Ŀγ̱�ţ�Ψһ�ԣ�
	 * 
	 */
	public void setCourseNo(String courseNo) {
		this.courseNo = courseNo;
	}

	/**
	 * ��ɺ�Ŀγ̱�ţ�Ψһ�ԣ�
	 * 
	 */
	public String getCourseNo() {
		return this.courseNo;
	}

	/**
	 * �γ̴���
	 * 
	 */
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	/**
	 * �γ̴���
	 * 
	 */
	public String getCourseCode() {
		return this.courseCode;
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
	 * �γ�����
	 * 
	 */
	public void setCourseType(String courseType) {
		this.courseType = courseType;
	}

	/**
	 * �γ�����
	 * 
	 */
	public String getCourseType() {
		return this.courseType;
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
	 * ��ѧ�����
	 * 
	 */
	public void setClassesName(String classesName) {
		this.classesName = classesName;
	}

	/**
	 * ��ѧ�����
	 * 
	 */
	public String getClassesName() {
		return this.classesName;
	}

	
	public String getClassHour() {
		return classHour;
	}

	public void setClassHour(String classHour) {
		this.classHour = classHour;
	}

	/**
	 * ѧ��
	 * 
	 */
	public void setCredits(String credits) {
		this.credits = credits;
	}

	/**
	 * ѧ��
	 * 
	 */
	public String getCredits() {
		return this.credits;
	}

	/**
	 * �ƻ�����
	 * 
	 */
	public void setPlanStudentNumber(String planStudentNumber) {
		this.planStudentNumber = planStudentNumber;
	}

	/**
	 * �ƻ�����
	 * 
	 */
	public String getPlanStudentNumber() {
		return this.planStudentNumber;
	}

	/**
	 * �ƻ�ʵ����ѧʱ
	 * 
	 */
	public void setPlanLabTime(String planLabTime) {
		this.planLabTime = planLabTime;
	}

	/**
	 * �ƻ�ʵ����ѧʱ
	 * 
	 */
	public String getPlanLabTime() {
		return this.planLabTime;
	}

	/**
	 * �Ͽεص�
	 * 
	 */
	public void setCourseAddressType(String courseAddressType) {
		this.courseAddressType = courseAddressType;
	}

	/**
	 * �Ͽεص�
	 * 
	 */
	public String getCourseAddressType() {
		return this.courseAddressType;
	}

	/**
	 * ״̬
	 * 
	 */
	public void setState(Integer state) {
		this.state = state;
	}

	/**
	 * ״̬
	 * 
	 */
	public Integer getState() {
		return this.state;
	}

	/**
	 */
	public void setSystemMajor12(SystemMajor12 systemMajor12) {
		this.systemMajor12 = systemMajor12;
	}

	/**
	 */
	@JsonIgnore
	public SystemMajor12 getSystemMajor12() {
		return systemMajor12;
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
	public void setCDictionaryByCourseStatus(CDictionary CDictionaryByCourseStatus) {
		this.CDictionaryByCourseStatus = CDictionaryByCourseStatus;
	}

	/**
	 */
	@JsonIgnore
	public CDictionary getCDictionaryByCourseStatus() {
		return CDictionaryByCourseStatus;
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
	public void setCDictionaryByStudentType(CDictionary CDictionaryByStudentType) {
		this.CDictionaryByStudentType = CDictionaryByStudentType;
	}

	/**
	 */
	@JsonIgnore
	public CDictionary getCDictionaryByStudentType() {
		return CDictionaryByStudentType;
	}

	/**
	 */
	public void setUserByTeacher(User userByTeacher) {
		this.userByTeacher = userByTeacher;
	}

	/**
	 */
	@JsonIgnore
	public User getUserByTeacher() {
		return userByTeacher;
	}

	/**
	 */
	public void setSchoolCourseInfo(SchoolCourseInfo schoolCourseInfo) {
		this.schoolCourseInfo = schoolCourseInfo;
	}

	/**
	 */
	@JsonIgnore
	public SchoolCourseInfo getSchoolCourseInfo() {
		return schoolCourseInfo;
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
	public void setUserByUpdatedBy(User userByUpdatedBy) {
		this.userByUpdatedBy = userByUpdatedBy;
	}

	/**
	 */
	@JsonIgnore
	public User getUserByUpdatedBy() {
		return userByUpdatedBy;
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


	public java.util.Set<net.xidlims.domain.TCourseSite> gettCourseSites() {
		return tCourseSites;
	}

	public void settCourseSites(
			java.util.Set<net.xidlims.domain.TCourseSite> tCourseSites) {
		this.tCourseSites = tCourseSites;
	}

	/**
	 */
	public SchoolCourse() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(SchoolCourse that) {
		setCourseNo(that.getCourseNo());
		setCourseCode(that.getCourseCode());
		setCourseName(that.getCourseName());
		setCourseType(that.getCourseType());
		setCreatedDate(that.getCreatedDate());
		setUpdatedDate(that.getUpdatedDate());
		setMemo(that.getMemo());
		setClassesName(that.getClassesName());
		setClassHour(that.getClassHour());
		setCredits(that.getCredits());
		setPlanStudentNumber(that.getPlanStudentNumber());
		setPlanLabTime(that.getPlanLabTime());
		setCourseAddressType(that.getCourseAddressType());
		setState(that.getState());
		setSystemMajor12(that.getSystemMajor12());
		setSchoolTerm(that.getSchoolTerm());
		setCDictionaryByCourseStatus(that.getCDictionaryByCourseStatus());
		setUserByCreatedBy(that.getUserByCreatedBy());
		setCDictionaryByStudentType(that.getCDictionaryByStudentType());
		setUserByTeacher(that.getUserByTeacher());
		setSchoolCourseInfo(that.getSchoolCourseInfo());
		setSchoolAcademy(that.getSchoolAcademy());
		setUserByUpdatedBy(that.getUserByUpdatedBy());
		setSchoolCourseDetails(new java.util.LinkedHashSet<net.xidlims.domain.SchoolCourseDetail>(that.getSchoolCourseDetails()));
		setTimetableAppointments(new java.util.LinkedHashSet<net.xidlims.domain.TimetableAppointment>(that.getTimetableAppointments()));
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("courseNo=[").append(courseNo).append("] ");
		buffer.append("courseCode=[").append(courseCode).append("] ");
		buffer.append("courseName=[").append(courseName).append("] ");
		buffer.append("classHour=[").append(classHour).append("] ");
		buffer.append("courseType=[").append(courseType).append("] ");
		buffer.append("createdDate=[").append(createdDate).append("] ");
		buffer.append("updatedDate=[").append(updatedDate).append("] ");
		buffer.append("memo=[").append(memo).append("] ");
		buffer.append("classesName=[").append(classesName).append("] ");
		buffer.append("credits=[").append(credits).append("] ");
		buffer.append("planStudentNumber=[").append(planStudentNumber).append("] ");
		buffer.append("planLabTime=[").append(planLabTime).append("] ");
		buffer.append("courseAddressType=[").append(courseAddressType).append("] ");
		buffer.append("state=[").append(state).append("] ");

		return buffer.toString();
	}

	/**
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((courseNo == null) ? 0 : courseNo.hashCode()));
		return result;
	}

	/**
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof SchoolCourse))
			return false;
		SchoolCourse equalCheck = (SchoolCourse) obj;
		if ((courseNo == null && equalCheck.courseNo != null) || (courseNo != null && equalCheck.courseNo == null))
			return false;
		if (courseNo != null && !courseNo.equals(equalCheck.courseNo))
			return false;
		return true;
	}
}
