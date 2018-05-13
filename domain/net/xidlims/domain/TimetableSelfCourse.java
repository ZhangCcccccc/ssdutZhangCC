package net.xidlims.domain;

import java.io.Serializable;

import java.lang.StringBuilder;


import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
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
		@NamedQuery(name = "findAllTimetableSelfCourses", query = "select myTimetableSelfCourse from TimetableSelfCourse myTimetableSelfCourse"),
		@NamedQuery(name = "findTimetableSelfCourseByCourseCode", query = "select myTimetableSelfCourse from TimetableSelfCourse myTimetableSelfCourse where myTimetableSelfCourse.courseCode = ?1"),
		@NamedQuery(name = "findTimetableSelfCourseByCourseCodeContaining", query = "select myTimetableSelfCourse from TimetableSelfCourse myTimetableSelfCourse where myTimetableSelfCourse.courseCode like ?1"),
		@NamedQuery(name = "findTimetableSelfCourseById", query = "select myTimetableSelfCourse from TimetableSelfCourse myTimetableSelfCourse where myTimetableSelfCourse.id = ?1"),
		@NamedQuery(name = "findTimetableSelfCourseByName", query = "select myTimetableSelfCourse from TimetableSelfCourse myTimetableSelfCourse where myTimetableSelfCourse.name = ?1"),
		@NamedQuery(name = "findTimetableSelfCourseByNameContaining", query = "select myTimetableSelfCourse from TimetableSelfCourse myTimetableSelfCourse where myTimetableSelfCourse.name like ?1"),
		@NamedQuery(name = "findTimetableSelfCourseByPrimaryKey", query = "select myTimetableSelfCourse from TimetableSelfCourse myTimetableSelfCourse where myTimetableSelfCourse.id = ?1") })
@Table(catalog = "xidlims", name = "timetable_self_course")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "TimetableSelfCourse")
@XmlRootElement(namespace = "xidlims/net/xidlims/domain")
public class TimetableSelfCourse implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ��������ѡ����
	 * 
	 */

	@Column(name = "id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	@XmlElement
	Integer id;
	/**
	 * ѡ������
	 * 
	 */

	@Column(name = "course_count")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer courseCount;
	
	@Column(name = "status")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer status;
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "course_code")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String courseCode;
	/**
	 * �Զ����ѡ�������
	 * 
	 */

	@Column(name = "name", length = 11)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String name;

	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "term_id", referencedColumnName = "id") })
	@XmlTransient
	SchoolTerm schoolTerm;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "teacher", referencedColumnName = "username") })
	@XmlTransient
	User user;
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
	@OneToMany(mappedBy = "timetableSelfCourse", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.TimetableCourseStudent> timetableCourseStudents;

	@OneToMany(mappedBy = "timetableSelfCourse", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.TimetableAppointment> timetableAppointments;
	
	@OneToMany(mappedBy = "timetableSelfCourse", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.TCourseSite> tCourseSites;

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
	 * ��������ѡ����
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * ��������ѡ����
	 * 
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * ÿ������
	 * 
	 */
	public void setCourseCount(Integer courseCount) {
		this.courseCount = courseCount;
	}

	/**
	 * ÿ������
	 * 
	 */
	public Integer getCourseCount() {
		return this.courseCount;
	}
	
	/**
	 * ѡ������
	 * 
	 */
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	/**
	 * ѡ������
	 * 
	 */
	public String getCourseCode() {
		return this.courseCode;
	}

	/**
	 * �Զ����ѡ�������
	 * 
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * �Զ����ѡ�������
	 * 
	 */
	public String getName() {
		return this.name;
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
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 */
	@JsonIgnore
	public User getUser() {
		return user;
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
	public void setTimetableCourseStudents(Set<TimetableCourseStudent> timetableCourseStudents) {
		this.timetableCourseStudents = timetableCourseStudents;
	}

	/**
	 */
	@JsonIgnore
	public Set<TimetableCourseStudent> getTimetableCourseStudents() {
		if (timetableCourseStudents == null) {
			timetableCourseStudents = new java.util.LinkedHashSet<net.xidlims.domain.TimetableCourseStudent>();
		}
		return timetableCourseStudents;
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
	public TimetableSelfCourse() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(TimetableSelfCourse that) {
		setId(that.getId());
		setCourseCode(that.getCourseCode());
		setName(that.getName());
		setSchoolTerm(that.getSchoolTerm());
		setUser(that.getUser());
		setSchoolCourseInfo(that.getSchoolCourseInfo());
		setSchoolAcademy(that.getSchoolAcademy());
		setTimetableCourseStudents(new java.util.LinkedHashSet<net.xidlims.domain.TimetableCourseStudent>(that.getTimetableCourseStudents()));
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("id=[").append(id).append("] ");
		buffer.append("courseCode=[").append(courseCode).append("] ");
		buffer.append("name=[").append(name).append("] ");

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
		if (!(obj instanceof TimetableSelfCourse))
			return false;
		TimetableSelfCourse equalCheck = (TimetableSelfCourse) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
