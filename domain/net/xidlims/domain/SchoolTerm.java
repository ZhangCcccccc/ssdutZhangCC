package net.xidlims.domain;

import java.io.Serializable;

import java.lang.StringBuilder;

import java.util.Calendar;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
		@NamedQuery(name = "findAllSchoolTerms", query = "select mySchoolTerm from SchoolTerm mySchoolTerm"),
		@NamedQuery(name = "findSchoolTermByCreatedAt", query = "select mySchoolTerm from SchoolTerm mySchoolTerm where mySchoolTerm.createdAt = ?1"),
		@NamedQuery(name = "findSchoolTermById", query = "select mySchoolTerm from SchoolTerm mySchoolTerm where mySchoolTerm.id = ?1"),
		@NamedQuery(name = "findSchoolTermByPrimaryKey", query = "select mySchoolTerm from SchoolTerm mySchoolTerm where mySchoolTerm.id = ?1"),
		@NamedQuery(name = "findSchoolTermByTermCode", query = "select mySchoolTerm from SchoolTerm mySchoolTerm where mySchoolTerm.termCode = ?1"),
		@NamedQuery(name = "findSchoolTermByTermEnd", query = "select mySchoolTerm from SchoolTerm mySchoolTerm where mySchoolTerm.termEnd = ?1"),
		@NamedQuery(name = "findSchoolTermByTermEndAfter", query = "select mySchoolTerm from SchoolTerm mySchoolTerm where mySchoolTerm.termEnd > ?1"),
		@NamedQuery(name = "findSchoolTermByTermEndBefore", query = "select mySchoolTerm from SchoolTerm mySchoolTerm where mySchoolTerm.termEnd < ?1"),
		@NamedQuery(name = "findSchoolTermByTermName", query = "select mySchoolTerm from SchoolTerm mySchoolTerm where mySchoolTerm.termName = ?1"),
		@NamedQuery(name = "findSchoolTermByTermNameContaining", query = "select mySchoolTerm from SchoolTerm mySchoolTerm where mySchoolTerm.termName like ?1"),
		@NamedQuery(name = "findSchoolTermByTermStart", query = "select mySchoolTerm from SchoolTerm mySchoolTerm where mySchoolTerm.termStart = ?1"),
		@NamedQuery(name = "findSchoolTermByTermStartAfter", query = "select mySchoolTerm from SchoolTerm mySchoolTerm where mySchoolTerm.termStart > ?1"),
		@NamedQuery(name = "findSchoolTermByTermStartBefore", query = "select mySchoolTerm from SchoolTerm mySchoolTerm where mySchoolTerm.termStart < ?1"),
		@NamedQuery(name = "findSchoolTermByUpdatedAt", query = "select mySchoolTerm from SchoolTerm mySchoolTerm where mySchoolTerm.updatedAt = ?1"),
		@NamedQuery(name = "findSchoolTermByYearCode", query = "select mySchoolTerm from SchoolTerm mySchoolTerm where mySchoolTerm.yearCode = ?1"),
		@NamedQuery(name = "findSchoolTermByYearCodeContaining", query = "select mySchoolTerm from SchoolTerm mySchoolTerm where mySchoolTerm.yearCode like ?1") })
@Table(catalog = "xidlims", name = "school_term")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "SchoolTerm")
@XmlRootElement(namespace = "xidlims/net/xidlims/domain")
public class SchoolTerm implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ѧ�ڻ��
	 * 
	 */

	@Column(name = "id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@XmlElement
	Integer id;
	/**
	 * ѧ�����
	 * 
	 */

	@Column(name = "term_name", length = 50)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String termName;
	/**
	 * ��ʼʱ��
	 * 
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "term_start")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar termStart;
	/**
	 * ����ʱ��
	 * 
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "term_end")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar termEnd;
	/**
	 * ���
	 * 
	 */

	@Column(name = "year_code", length = 10)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String yearCode;
	/**
	 * ѧ�ڱ��
	 * 
	 */

	@Column(name = "term_code")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer termCode;
	

	/**
	 * ���ڼ�
	 * 
	 */

	@Column(name = "flag")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer flag;

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

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
	
	@Column(name = "mem", length = 255)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String mem;

	public String getMem() {
		return mem;
	}

	public void setMem(String mem) {
		this.mem = mem;
	}

	/**
	 */
	@OneToMany(mappedBy = "schoolTerm", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.SchoolCourseDetail> schoolCourseDetails;
	
	@OneToMany(mappedBy = "schoolTerm", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.LabRoomDeviceTraining> labRoomDeviceTrainings;

	/**
	 */
	@OneToMany(mappedBy = "schoolTerm", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.SchoolWeek> schoolWeeks;
	/**
	 */
	@OneToMany(mappedBy = "schoolTerm", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.SchoolCourseStudent> schoolCourseStudents;
	/**
	 */
	@OneToMany(mappedBy = "schoolTerm", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.SchoolCourse> schoolCourses;
	/**
	 */
	@OneToMany(mappedBy = "schoolTerm", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.TimetableSelfCourse> timetableSelfCourses;
	/**
	 */
	@OneToMany(mappedBy = "schoolTerm", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.OperationItem> operationItems;
	
	/**
	 */
	@OneToMany(mappedBy = "schoolTerm", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.OperationOutline> operationOutlines;
	
	@OneToMany(mappedBy = "schoolTerm", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.LabReservationTimeTable> labReservationTimeTables;
	
	/**
	 */
	@OneToMany(mappedBy = "schoolTerm", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.IotSharePowerOpentime> IotSharePowerOpentimes;
	
	/**
	 */
	@OneToMany(mappedBy = "schoolTerm", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.SchoolTermActive> schoolTermActives;
	@JsonIgnore
	public java.util.Set<net.xidlims.domain.LabReservationTimeTable> getLabReservationTimeTables() {
		return labReservationTimeTables;
	}

	public void setLabReservationTimeTables(
			java.util.Set<net.xidlims.domain.LabReservationTimeTable> labReservationTimeTables) {
		this.labReservationTimeTables = labReservationTimeTables;
	}

	/**
	 * ѧ�ڻ��
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * ѧ�ڻ��
	 * 
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * ѧ�����
	 * 
	 */
	public void setTermName(String termName) {
		this.termName = termName;
	}

	/**
	 * ѧ�����
	 * 
	 */
	public String getTermName() {
		return this.termName;
	}

	/**
	 * ��ʼʱ��
	 * 
	 */
	public void setTermStart(Calendar termStart) {
		this.termStart = termStart;
	}

	/**
	 * ��ʼʱ��
	 * 
	 */
	public Calendar getTermStart() {
		return this.termStart;
	}

	/**
	 * ����ʱ��
	 * 
	 */
	public void setTermEnd(Calendar termEnd) {
		this.termEnd = termEnd;
	}

	/**
	 * ����ʱ��
	 * 
	 */
	public Calendar getTermEnd() {
		return this.termEnd;
	}

	/**
	 * ���
	 * 
	 */
	public void setYearCode(String yearCode) {
		this.yearCode = yearCode;
	}

	/**
	 * ���
	 * 
	 */
	public String getYearCode() {
		return this.yearCode;
	}

	/**
	 * ѧ�ڱ��
	 * 
	 */
	public void setTermCode(Integer termCode) {
		this.termCode = termCode;
	}

	/**
	 * ѧ�ڱ��
	 * 
	 */
	public Integer getTermCode() {
		return this.termCode;
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
	public void setSchoolWeeks(Set<SchoolWeek> schoolWeeks) {
		this.schoolWeeks = schoolWeeks;
	}

	/**
	 */
	@JsonIgnore
	public Set<SchoolWeek> getSchoolWeeks() {
		if (schoolWeeks == null) {
			schoolWeeks = new java.util.LinkedHashSet<net.xidlims.domain.SchoolWeek>();
		}
		return schoolWeeks;
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
	public void setOperationOutlines(Set<OperationOutline> operationOutlines) {
		this.operationOutlines = operationOutlines;
	}

	/**
	 */
	@JsonIgnore
	public Set<OperationOutline> getOperationOutline() {
		if (operationOutlines == null) {
			operationOutlines = new java.util.LinkedHashSet<net.xidlims.domain.OperationOutline>();
		}
		return operationOutlines;
	}
	
	/**
	 */
	public void setIotSharePowerOpentimes(Set<IotSharePowerOpentime> IotSharePowerOpentimes) {
		this.IotSharePowerOpentimes = IotSharePowerOpentimes;
	}

	/**
	 */
	@JsonIgnore
	public Set<IotSharePowerOpentime> getIotSharePowerOpentimes() {
		if (IotSharePowerOpentimes == null) {
			IotSharePowerOpentimes = new java.util.LinkedHashSet<net.xidlims.domain.IotSharePowerOpentime>();
		}
		return IotSharePowerOpentimes;
	}
	/**
	 */
	public void setSchoolTermActives(Set<SchoolTermActive> schoolTermActives) {
		this.schoolTermActives = schoolTermActives;
	}

	/**
	 */
	@JsonIgnore
	public Set<SchoolTermActive> getSchoolTermActives() {
		if (schoolTermActives == null) {
			schoolTermActives = new java.util.LinkedHashSet<net.xidlims.domain.SchoolTermActive>();
		}
		return schoolTermActives;
	}
	/**
	 */
	public SchoolTerm() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(SchoolTerm that) {
		setId(that.getId());
		setTermName(that.getTermName());
		setTermStart(that.getTermStart());
		setTermEnd(that.getTermEnd());
		setYearCode(that.getYearCode());
		setTermCode(that.getTermCode());
		setCreatedAt(that.getCreatedAt());
		setUpdatedAt(that.getUpdatedAt());
		setSchoolCourseDetails(new java.util.LinkedHashSet<net.xidlims.domain.SchoolCourseDetail>(that.getSchoolCourseDetails()));
		setSchoolWeeks(new java.util.LinkedHashSet<net.xidlims.domain.SchoolWeek>(that.getSchoolWeeks()));
		setSchoolCourseStudents(new java.util.LinkedHashSet<net.xidlims.domain.SchoolCourseStudent>(that.getSchoolCourseStudents()));
		setSchoolCourses(new java.util.LinkedHashSet<net.xidlims.domain.SchoolCourse>(that.getSchoolCourses()));
		setTimetableSelfCourses(new java.util.LinkedHashSet<net.xidlims.domain.TimetableSelfCourse>(that.getTimetableSelfCourses()));
		setOperationItems(new java.util.LinkedHashSet<net.xidlims.domain.OperationItem>(that.getOperationItems()));
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("id=[").append(id).append("] ");
		buffer.append("termName=[").append(termName).append("] ");
		buffer.append("termStart=[").append(termStart).append("] ");
		buffer.append("termEnd=[").append(termEnd).append("] ");
		buffer.append("yearCode=[").append(yearCode).append("] ");
		buffer.append("termCode=[").append(termCode).append("] ");
		buffer.append("createdAt=[").append(createdAt).append("] ");
		buffer.append("updatedAt=[").append(updatedAt).append("] ");

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
		if (!(obj instanceof SchoolTerm))
			return false;
		SchoolTerm equalCheck = (SchoolTerm) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
