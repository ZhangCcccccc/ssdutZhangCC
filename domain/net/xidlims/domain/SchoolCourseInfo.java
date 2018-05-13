package net.xidlims.domain;

import java.io.Serializable;

import java.lang.StringBuilder;

import java.util.Calendar;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import net.xidlims.domain.TCourseSite;

import org.codehaus.jackson.annotate.JsonIgnore;

import javax.xml.bind.annotation.*;

import javax.persistence.*;

/**
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "findAllSchoolCourseInfos", query = "select mySchoolCourseInfo from SchoolCourseInfo mySchoolCourseInfo"),
		@NamedQuery(name = "findSchoolCourseInfoByAcademyNumber", query = "select mySchoolCourseInfo from SchoolCourseInfo mySchoolCourseInfo where mySchoolCourseInfo.academyNumber = ?1"),
		@NamedQuery(name = "findSchoolCourseInfoByAcademyNumberContaining", query = "select mySchoolCourseInfo from SchoolCourseInfo mySchoolCourseInfo where mySchoolCourseInfo.academyNumber like ?1"),
		@NamedQuery(name = "findSchoolCourseInfoByCourseEnName", query = "select mySchoolCourseInfo from SchoolCourseInfo mySchoolCourseInfo where mySchoolCourseInfo.courseEnName = ?1"),
		@NamedQuery(name = "findSchoolCourseInfoByCourseEnNameContaining", query = "select mySchoolCourseInfo from SchoolCourseInfo mySchoolCourseInfo where mySchoolCourseInfo.courseEnName like ?1"),
		@NamedQuery(name = "findSchoolCourseInfoByCourseName", query = "select mySchoolCourseInfo from SchoolCourseInfo mySchoolCourseInfo where mySchoolCourseInfo.courseName = ?1"),
		@NamedQuery(name = "findSchoolCourseInfoByCourseNameContaining", query = "select mySchoolCourseInfo from SchoolCourseInfo mySchoolCourseInfo where mySchoolCourseInfo.courseName like ?1"),
		@NamedQuery(name = "findSchoolCourseInfoByCourseNumber", query = "select mySchoolCourseInfo from SchoolCourseInfo mySchoolCourseInfo where mySchoolCourseInfo.courseNumber = ?1"),
		@NamedQuery(name = "findSchoolCourseInfoByCourseNumberContaining", query = "select mySchoolCourseInfo from SchoolCourseInfo mySchoolCourseInfo where mySchoolCourseInfo.courseNumber like ?1"),
		@NamedQuery(name = "findSchoolCourseInfoByCreatedAt", query = "select mySchoolCourseInfo from SchoolCourseInfo mySchoolCourseInfo where mySchoolCourseInfo.createdAt = ?1"),
		@NamedQuery(name = "findSchoolCourseInfoByCreatedBy", query = "select mySchoolCourseInfo from SchoolCourseInfo mySchoolCourseInfo where mySchoolCourseInfo.createdBy = ?1"),
		@NamedQuery(name = "findSchoolCourseInfoByFlag", query = "select mySchoolCourseInfo from SchoolCourseInfo mySchoolCourseInfo where mySchoolCourseInfo.flag = ?1"),
		@NamedQuery(name = "findSchoolCourseInfoByPrimaryKey", query = "select mySchoolCourseInfo from SchoolCourseInfo mySchoolCourseInfo where mySchoolCourseInfo.courseNumber = ?1"),
		@NamedQuery(name = "findSchoolCourseInfoByTheoreticalHours", query = "select mySchoolCourseInfo from SchoolCourseInfo mySchoolCourseInfo where mySchoolCourseInfo.theoreticalHours = ?1"),
		@NamedQuery(name = "findSchoolCourseInfoByTotalHours", query = "select mySchoolCourseInfo from SchoolCourseInfo mySchoolCourseInfo where mySchoolCourseInfo.totalHours = ?1"),
		@NamedQuery(name = "findSchoolCourseInfoByUpdatedAt", query = "select mySchoolCourseInfo from SchoolCourseInfo mySchoolCourseInfo where mySchoolCourseInfo.updatedAt = ?1"),
		@NamedQuery(name = "findSchoolCourseInfoByUpdatedBy", query = "select mySchoolCourseInfo from SchoolCourseInfo mySchoolCourseInfo where mySchoolCourseInfo.updatedBy = ?1") })
@Table(catalog = "xidlims", name = "school_course_info")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "SchoolCourseInfo")
@XmlRootElement(namespace = "xidlims/net/xidlims/domain")
public class SchoolCourseInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * �γ���Ϣ���
	 * 
	 */

	@Column(name = "course_number", length = 40, nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	String courseNumber;
	/**
	 * �γ����
	 * 
	 */

	@Column(name = "course_name", length = 100)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String courseName;
	/**
	 * �γ�Ӣ�����
	 * 
	 */

	@Column(name = "course_en_name", length = 200)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String courseEnName;
	/**
	 * ѧԺ
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
	@Column(name = "created_at", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar createdAt;
	/**
	 * ����ʱ��
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar updatedAt;
	/**
	 * ������
	 * 
	 */

	@Column(name = "created_by")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer createdBy;
	/**
	 * ������
	 * 
	 */

	@Column(name = "updated_by")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer updatedBy;
	/**
	 * ����ѧʱ
	 * 
	 */

	@Column(name = "theoretical_hours")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer theoreticalHours;
	/**
	 * ��ѧʱ
	 * 
	 */

	@Column(name = "total_hours")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer totalHours;
	/**
	 * �γ̱��Ϊ��1Ϊ�Խ��γ̣�0Ϊ�����
	 * 
	 */

	@Column(name = "flag")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer flag;

	/**
	 */
	@OneToMany(mappedBy = "schoolCourseInfo", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.SchoolCourse> schoolCourses;
	/**
	 */
	@OneToMany(mappedBy = "schoolCourseInfo", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.TimetableAppointment> timetableAppointments;
	/**
	 */
	@OneToMany(mappedBy = "schoolCourseInfo", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.OperationItem> operationItems;
	/**
	 */
	@OneToMany(mappedBy = "schoolCourseInfo", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.TimetableSelfCourse> timetableSelfCourses;

	/**
	 */
	@OneToMany(mappedBy = "schoolCourseInfo", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	@OrderBy("id ASC")
	java.util.Set<net.xidlims.domain.TCourseSite> TCourseSites;
	/**
	 */
	@OneToMany(mappedBy = "schoolCourseInfoByClassId", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.OperationOutline> operationOutlinesForClassId;
	/**
	 */
	@OneToMany(mappedBy = "schoolCourseInfoByFollowUpCourses", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.OperationOutline> operationOutlinesForFollowUpCourses;
	/**
	 */
	@OneToMany(mappedBy = "schoolCourseInfoByFirstCourses", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.OperationOutline> operationOutlinesForFirstCourses;
	
	/**
	 */
	@ManyToMany(mappedBy = "schoolCourseInfoes", fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.OperationOutline> operationOutlinees;
	
	/**
	 * �γ���Ϣ���
	 * 
	 */
	public void setCourseNumber(String courseNumber) {
		this.courseNumber = courseNumber;
	}

	/**
	 * �γ���Ϣ���
	 * 
	 */
	public String getCourseNumber() {
		return this.courseNumber;
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
	 * �γ�Ӣ�����
	 * 
	 */
	public void setCourseEnName(String courseEnName) {
		this.courseEnName = courseEnName;
	}

	/**
	 * �γ�Ӣ�����
	 * 
	 */
	public String getCourseEnName() {
		return this.courseEnName;
	}

	/**
	 * ѧԺ
	 * 
	 */
	public void setAcademyNumber(String academyNumber) {
		this.academyNumber = academyNumber;
	}

	/**
	 * ѧԺ
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
	 * ������
	 * 
	 */
	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	/**
	 * ������
	 * 
	 */
	public Integer getUpdatedBy() {
		return this.updatedBy;
	}

	/**
	 * ����ѧʱ
	 * 
	 */
	public void setTheoreticalHours(Integer theoreticalHours) {
		this.theoreticalHours = theoreticalHours;
	}

	/**
	 * ����ѧʱ
	 * 
	 */
	public Integer getTheoreticalHours() {
		return this.theoreticalHours;
	}

	/**
	 * ��ѧʱ
	 * 
	 */
	public void setTotalHours(Integer totalHours) {
		this.totalHours = totalHours;
	}

	/**
	 * ��ѧʱ
	 * 
	 */
	public Integer getTotalHours() {
		return this.totalHours;
	}

	/**
	 * �γ̱��Ϊ��1Ϊ�Խ��γ̣�0Ϊ�����
	 * 
	 */
	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	/**
	 * �γ̱��Ϊ��1Ϊ�Խ��γ̣�0Ϊ�����
	 * 
	 */
	public Integer getFlag() {
		return this.flag;
	}

	/**
	 */
	public void setSchoolCourses(Set<SchoolCourse> schoolCourses) {
		this.schoolCourses = schoolCourses;
	}

	/**
	 */
	@JsonIgnore
	public Set<SchoolCourse> getSchoolCourses() {
		if (schoolCourses == null) {
			schoolCourses = new java.util.LinkedHashSet<net.xidlims.domain.SchoolCourse>();
		}
		return schoolCourses;
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

	/**
	 */
	public void setOperationItems(Set<OperationItem> operationItems) {
		this.operationItems = operationItems;
	}

	/**
	 */
	@JsonIgnore
	public Set<OperationItem> getOperationItems() {
		if (operationItems == null) {
			operationItems = new java.util.LinkedHashSet<net.xidlims.domain.OperationItem>();
		}
		return operationItems;
	}

	/**
	 */
	public void setTimetableSelfCourses(Set<TimetableSelfCourse> timetableSelfCourses) {
		this.timetableSelfCourses = timetableSelfCourses;
	}

	/**
	 */
	@JsonIgnore
	public Set<TimetableSelfCourse> getTimetableSelfCourses() {
		if (timetableSelfCourses == null) {
			timetableSelfCourses = new java.util.LinkedHashSet<net.xidlims.domain.TimetableSelfCourse>();
		}
		return timetableSelfCourses;
	}

	/**
	 */
	public void setTCourseSites(Set<TCourseSite> TCourseSites) {
		this.TCourseSites = TCourseSites;
	}

	/**
	 */
	public Set<TCourseSite> getTCourseSites() {
		if (TCourseSites == null) {
			TCourseSites = new java.util.LinkedHashSet<net.xidlims.domain.TCourseSite>();
		}
		return TCourseSites;
	}
	/**
	 */
	public void setOperationOutlinesForClassId(Set<OperationOutline> operationOutlinesForClassId) {
		this.operationOutlinesForClassId = operationOutlinesForClassId;
	}

	/**
	 */
	@JsonIgnore
	public Set<OperationOutline> getOperationOutlinesForClassId() {
		if (operationOutlinesForClassId == null) {
			operationOutlinesForClassId = new java.util.LinkedHashSet<net.xidlims.domain.OperationOutline>();
		}
		return operationOutlinesForClassId;
	}
	/**
	 */
	public void setOperationOutlinesForFollowUpCourses(Set<OperationOutline> operationOutlinesForFollowUpCourses) {
		this.operationOutlinesForFollowUpCourses = operationOutlinesForFollowUpCourses;
	}

	/**
	 */
	@JsonIgnore
	public Set<OperationOutline> getOperationOutlinesForFollowUpCourses() {
		if (operationOutlinesForFollowUpCourses == null) {
			operationOutlinesForFollowUpCourses = new java.util.LinkedHashSet<net.xidlims.domain.OperationOutline>();
		}
		return operationOutlinesForFollowUpCourses;
	}

	/**
	 */
	public void setOperationOutlinesForFirstCourses(Set<OperationOutline> operationOutlinesForFirstCourses) {
		this.operationOutlinesForFirstCourses = operationOutlinesForFirstCourses;
	}

	/**
	 */
	@JsonIgnore
	public Set<OperationOutline> getOperationOutlinesForFirstCourses() {
		if (operationOutlinesForFirstCourses == null) {
			operationOutlinesForFirstCourses = new java.util.LinkedHashSet<net.xidlims.domain.OperationOutline>();
		}
		return operationOutlinesForFirstCourses;
	}

	/**
	 */
	public void setOperationOutlinees(Set<OperationOutline> operationOutlinees) {
		this.operationOutlinees = operationOutlinees;
	}

	/**
	 */
	@JsonIgnore
	public Set<OperationOutline> getOperationOutlinees() {
		if (operationOutlinees == null) {
			operationOutlinees = new java.util.LinkedHashSet<net.xidlims.domain.OperationOutline>();
		}
		return operationOutlinees;
	}

	
	/**
	 */
	public SchoolCourseInfo() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(SchoolCourseInfo that) {
		setCourseNumber(that.getCourseNumber());
		setCourseName(that.getCourseName());
		setCourseEnName(that.getCourseEnName());
		setAcademyNumber(that.getAcademyNumber());
		setCreatedAt(that.getCreatedAt());
		setUpdatedAt(that.getUpdatedAt());
		setCreatedBy(that.getCreatedBy());
		setUpdatedBy(that.getUpdatedBy());
		setTheoreticalHours(that.getTheoreticalHours());
		setTotalHours(that.getTotalHours());
		setFlag(that.getFlag());
		setSchoolCourses(new java.util.LinkedHashSet<net.xidlims.domain.SchoolCourse>(that.getSchoolCourses()));
		setTimetableAppointments(new java.util.LinkedHashSet<net.xidlims.domain.TimetableAppointment>(that.getTimetableAppointments()));
		setOperationItems(new java.util.LinkedHashSet<net.xidlims.domain.OperationItem>(that.getOperationItems()));
		setTimetableSelfCourses(new java.util.LinkedHashSet<net.xidlims.domain.TimetableSelfCourse>(that.getTimetableSelfCourses()));
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("courseNumber=[").append(courseNumber).append("] ");
		buffer.append("courseName=[").append(courseName).append("] ");
		buffer.append("courseEnName=[").append(courseEnName).append("] ");
		buffer.append("academyNumber=[").append(academyNumber).append("] ");
		buffer.append("createdAt=[").append(createdAt).append("] ");
		buffer.append("updatedAt=[").append(updatedAt).append("] ");
		buffer.append("createdBy=[").append(createdBy).append("] ");
		buffer.append("updatedBy=[").append(updatedBy).append("] ");
		buffer.append("theoreticalHours=[").append(theoreticalHours).append("] ");
		buffer.append("totalHours=[").append(totalHours).append("] ");
		buffer.append("flag=[").append(flag).append("] ");

		return buffer.toString();
	}

	/**
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((courseNumber == null) ? 0 : courseNumber.hashCode()));
		return result;
	}

	/**
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof SchoolCourseInfo))
			return false;
		SchoolCourseInfo equalCheck = (SchoolCourseInfo) obj;
		if ((courseNumber == null && equalCheck.courseNumber != null) || (courseNumber != null && equalCheck.courseNumber == null))
			return false;
		if (courseNumber != null && !courseNumber.equals(equalCheck.courseNumber))
			return false;
		return true;
	}
}
