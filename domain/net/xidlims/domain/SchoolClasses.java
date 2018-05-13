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
		@NamedQuery(name = "findAllSchoolClassess", query = "select mySchoolClasses from SchoolClasses mySchoolClasses"),
		@NamedQuery(name = "findSchoolClassesByAcademyNumber", query = "select mySchoolClasses from SchoolClasses mySchoolClasses where mySchoolClasses.academyNumber = ?1"),
		@NamedQuery(name = "findSchoolClassesByAcademyNumberContaining", query = "select mySchoolClasses from SchoolClasses mySchoolClasses where mySchoolClasses.academyNumber like ?1"),
		@NamedQuery(name = "findSchoolClassesByAttendanceTime", query = "select mySchoolClasses from SchoolClasses mySchoolClasses where mySchoolClasses.attendanceTime = ?1"),
		@NamedQuery(name = "findSchoolClassesByAttendanceTimeContaining", query = "select mySchoolClasses from SchoolClasses mySchoolClasses where mySchoolClasses.attendanceTime like ?1"),
		@NamedQuery(name = "findSchoolClassesByClassGrade", query = "select mySchoolClasses from SchoolClasses mySchoolClasses where mySchoolClasses.classGrade = ?1"),
		@NamedQuery(name = "findSchoolClassesByClassGradeContaining", query = "select mySchoolClasses from SchoolClasses mySchoolClasses where mySchoolClasses.classGrade like ?1"),
		@NamedQuery(name = "findSchoolClassesByClassName", query = "select mySchoolClasses from SchoolClasses mySchoolClasses where mySchoolClasses.className = ?1"),
		@NamedQuery(name = "findSchoolClassesByClassNameContaining", query = "select mySchoolClasses from SchoolClasses mySchoolClasses where mySchoolClasses.className like ?1"),
		@NamedQuery(name = "findSchoolClassesByClassNumber", query = "select mySchoolClasses from SchoolClasses mySchoolClasses where mySchoolClasses.classNumber = ?1"),
		@NamedQuery(name = "findSchoolClassesByClassNumberContaining", query = "select mySchoolClasses from SchoolClasses mySchoolClasses where mySchoolClasses.classNumber like ?1"),
		@NamedQuery(name = "findSchoolClassesByClassPlanStudents", query = "select mySchoolClasses from SchoolClasses mySchoolClasses where mySchoolClasses.classPlanStudents = ?1"),
		@NamedQuery(name = "findSchoolClassesByClassPlanStudentsContaining", query = "select mySchoolClasses from SchoolClasses mySchoolClasses where mySchoolClasses.classPlanStudents like ?1"),
		@NamedQuery(name = "findSchoolClassesByClassShortName", query = "select mySchoolClasses from SchoolClasses mySchoolClasses where mySchoolClasses.classShortName = ?1"),
		@NamedQuery(name = "findSchoolClassesByClassShortNameContaining", query = "select mySchoolClasses from SchoolClasses mySchoolClasses where mySchoolClasses.classShortName like ?1"),
		@NamedQuery(name = "findSchoolClassesByClassStudentsNumber", query = "select mySchoolClasses from SchoolClasses mySchoolClasses where mySchoolClasses.classStudentsNumber = ?1"),
		@NamedQuery(name = "findSchoolClassesByClassStudentsNumberContaining", query = "select mySchoolClasses from SchoolClasses mySchoolClasses where mySchoolClasses.classStudentsNumber like ?1"),
		@NamedQuery(name = "findSchoolClassesByCreatedAt", query = "select mySchoolClasses from SchoolClasses mySchoolClasses where mySchoolClasses.createdAt = ?1"),
		@NamedQuery(name = "findSchoolClassesById", query = "select mySchoolClasses from SchoolClasses mySchoolClasses where mySchoolClasses.id = ?1"),
		@NamedQuery(name = "findSchoolClassesByMajorNumber", query = "select mySchoolClasses from SchoolClasses mySchoolClasses where mySchoolClasses.majorNumber = ?1"),
		@NamedQuery(name = "findSchoolClassesByMajorNumberContaining", query = "select mySchoolClasses from SchoolClasses mySchoolClasses where mySchoolClasses.majorNumber like ?1"),
		@NamedQuery(name = "findSchoolClassesByPrimaryKey", query = "select mySchoolClasses from SchoolClasses mySchoolClasses where mySchoolClasses.classNumber = ?1"),
		@NamedQuery(name = "findSchoolClassesByUpdatedAt", query = "select mySchoolClasses from SchoolClasses mySchoolClasses where mySchoolClasses.updatedAt = ?1") })
@Table(catalog = "xidlims", name = "school_classes")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "SchoolClasses")
@XmlRootElement(namespace = "xidlims/net/xidlims/domain")
public class SchoolClasses implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * �༶���
	 * 
	 */

	@Column(name = "class_number", length = 40, nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	String classNumber;
	/**
	 * �༶��Ϣ���
	 * 
	 */

	@Column(name = "id")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer id;
	/**
	 * �༶���
	 * 
	 */

	@Column(name = "class_name", length = 100)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String className;
	/**
	 * �����꼶
	 * 
	 */

	@Column(name = "class_grade", length = 40)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String classGrade;
	/**
	 * �༶����
	 * 
	 */

	@Column(name = "class_students_number", length = 40)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String classStudentsNumber;
	/**
	 * �༶���
	 * 
	 */

	@Column(name = "class_short_name", length = 100)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String classShortName;
	/**
	 * רҵ����
	 * 
	 */

	@Column(name = "major_number", length = 40)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String majorNumber;
	/**
	 * ѧԺ����
	 * 
	 */

	@Column(name = "academy_number", length = 40)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String academyNumber;
	/**
	 * ����ʱ��
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar createdAt;
	/**
	 * ����ʱ��
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar updatedAt;
	/**
	 * �༶�ƻ�����
	 * 
	 */

	@Column(name = "class_plan_students", length = 40)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String classPlanStudents;
	/**
	 * ��ѧ���
	 * 
	 */

	@Column(name = "attendance_time", length = 20)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String attendanceTime;

	/**
	 */
	@OneToMany(mappedBy = "schoolClasses", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.SchoolCourseStudent> schoolCourseStudents;
	
	@OneToMany(mappedBy = "schoolClasses", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.User> users;
	
	/**
	 */
	@OneToMany(mappedBy = "schoolClasses", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.AppPostlist> appPostlists;

	/**
	 * �༶���
	 * 
	 */
	public void setClassNumber(String classNumber) {
		this.classNumber = classNumber;
	}

	/**
	 * �༶���
	 * 
	 */
	public String getClassNumber() {
		return this.classNumber;
	}

	/**
	 * �༶��Ϣ���
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * �༶��Ϣ���
	 * 
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * �༶���
	 * 
	 */
	public void setClassName(String className) {
		this.className = className;
	}

	/**
	 * �༶���
	 * 
	 */
	public String getClassName() {
		return this.className;
	}

	/**
	 * �����꼶
	 * 
	 */
	public void setClassGrade(String classGrade) {
		this.classGrade = classGrade;
	}

	/**
	 * �����꼶
	 * 
	 */
	public String getClassGrade() {
		return this.classGrade;
	}

	/**
	 * �༶����
	 * 
	 */
	public void setClassStudentsNumber(String classStudentsNumber) {
		this.classStudentsNumber = classStudentsNumber;
	}

	/**
	 * �༶����
	 * 
	 */
	public String getClassStudentsNumber() {
		return this.classStudentsNumber;
	}

	/**
	 * �༶���
	 * 
	 */
	public void setClassShortName(String classShortName) {
		this.classShortName = classShortName;
	}

	/**
	 * �༶���
	 * 
	 */
	public String getClassShortName() {
		return this.classShortName;
	}

	/**
	 * רҵ����
	 * 
	 */
	public void setMajorNumber(String majorNumber) {
		this.majorNumber = majorNumber;
	}

	/**
	 * רҵ����
	 * 
	 */
	public String getMajorNumber() {
		return this.majorNumber;
	}

	/**
	 * ѧԺ����
	 * 
	 */
	public void setAcademyNumber(String academyNumber) {
		this.academyNumber = academyNumber;
	}

	/**
	 * ѧԺ����
	 * 
	 */
	public String getAcademyNumber() {
		return this.academyNumber;
	}

	/**
	 * ����ʱ��
	 * 
	 */
	public void setCreatedAt(Calendar createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * ����ʱ��
	 * 
	 */
	public Calendar getCreatedAt() {
		return this.createdAt;
	}

	/**
	 * ����ʱ��
	 * 
	 */
	public void setUpdatedAt(Calendar updatedAt) {
		this.updatedAt = updatedAt;
	}

	/**
	 * ����ʱ��
	 * 
	 */
	public Calendar getUpdatedAt() {
		return this.updatedAt;
	}

	/**
	 * �༶�ƻ�����
	 * 
	 */
	public void setClassPlanStudents(String classPlanStudents) {
		this.classPlanStudents = classPlanStudents;
	}

	/**
	 * �༶�ƻ�����
	 * 
	 */
	public String getClassPlanStudents() {
		return this.classPlanStudents;
	}

	/**
	 * ��ѧ���
	 * 
	 */
	public void setAttendanceTime(String attendanceTime) {
		this.attendanceTime = attendanceTime;
	}

	/**
	 * ��ѧ���
	 * 
	 */
	public String getAttendanceTime() {
		return this.attendanceTime;
	}

	/**
	 */
	public void setSchoolCourseStudents(Set<SchoolCourseStudent> schoolCourseStudents) {
		this.schoolCourseStudents = schoolCourseStudents;
	}

	/**
	 */
	@JsonIgnore
	public Set<SchoolCourseStudent> getSchoolCourseStudents() {
		if (schoolCourseStudents == null) {
			schoolCourseStudents = new java.util.LinkedHashSet<net.xidlims.domain.SchoolCourseStudent>();
		}
		return schoolCourseStudents;
	}
	
	public java.util.Set<net.xidlims.domain.User> getUsers() {
		return users;
	}

	public void setUsers(java.util.Set<net.xidlims.domain.User> users) {
		this.users = users;
	}
	
	/**
	 */
	public void setAppPostlists(Set<AppPostlist> appPostlists) {
		this.appPostlists = appPostlists;
	}

	/**
	 */
	@JsonIgnore
	public Set<AppPostlist> getAppPostlists() {
		if (appPostlists == null) {
			appPostlists = new java.util.LinkedHashSet<net.xidlims.domain.AppPostlist>();
		}
		return appPostlists;
	}

	/**
	 */
	public SchoolClasses() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(SchoolClasses that) {
		setClassNumber(that.getClassNumber());
		setId(that.getId());
		setClassName(that.getClassName());
		setClassGrade(that.getClassGrade());
		setClassStudentsNumber(that.getClassStudentsNumber());
		setClassShortName(that.getClassShortName());
		setMajorNumber(that.getMajorNumber());
		setAcademyNumber(that.getAcademyNumber());
		setCreatedAt(that.getCreatedAt());
		setUpdatedAt(that.getUpdatedAt());
		setClassPlanStudents(that.getClassPlanStudents());
		setAttendanceTime(that.getAttendanceTime());
		setSchoolCourseStudents(new java.util.LinkedHashSet<net.xidlims.domain.SchoolCourseStudent>(that.getSchoolCourseStudents()));
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("classNumber=[").append(classNumber).append("] ");
		buffer.append("id=[").append(id).append("] ");
		buffer.append("className=[").append(className).append("] ");
		buffer.append("classGrade=[").append(classGrade).append("] ");
		buffer.append("classStudentsNumber=[").append(classStudentsNumber).append("] ");
		buffer.append("classShortName=[").append(classShortName).append("] ");
		buffer.append("majorNumber=[").append(majorNumber).append("] ");
		buffer.append("academyNumber=[").append(academyNumber).append("] ");
		buffer.append("createdAt=[").append(createdAt).append("] ");
		buffer.append("updatedAt=[").append(updatedAt).append("] ");
		buffer.append("classPlanStudents=[").append(classPlanStudents).append("] ");
		buffer.append("attendanceTime=[").append(attendanceTime).append("] ");

		return buffer.toString();
	}

	/**
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((classNumber == null) ? 0 : classNumber.hashCode()));
		return result;
	}

	/**
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof SchoolClasses))
			return false;
		SchoolClasses equalCheck = (SchoolClasses) obj;
		if ((classNumber == null && equalCheck.classNumber != null) || (classNumber != null && equalCheck.classNumber == null))
			return false;
		if (classNumber != null && !classNumber.equals(equalCheck.classNumber))
			return false;
		return true;
	}
}
