package net.xidlims.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import org.codehaus.jackson.annotate.JsonIgnore;
import javax.persistence.OrderBy;

/**
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "findAllTimetableAppointments", query = "select myTimetableAppointment from TimetableAppointment myTimetableAppointment"),
		@NamedQuery(name = "findTimetableAppointmentByAppointmentNo", query = "select myTimetableAppointment from TimetableAppointment myTimetableAppointment where myTimetableAppointment.appointmentNo = ?1"),
		@NamedQuery(name = "findTimetableAppointmentByAppointmentNoContaining", query = "select myTimetableAppointment from TimetableAppointment myTimetableAppointment where myTimetableAppointment.appointmentNo like ?1"),
		@NamedQuery(name = "findTimetableAppointmentByCourseCode", query = "select myTimetableAppointment from TimetableAppointment myTimetableAppointment where myTimetableAppointment.courseCode = ?1"),
		@NamedQuery(name = "findTimetableAppointmentByCourseCodeContaining", query = "select myTimetableAppointment from TimetableAppointment myTimetableAppointment where myTimetableAppointment.courseCode like ?1"),
		@NamedQuery(name = "findTimetableAppointmentByCreatedBy", query = "select myTimetableAppointment from TimetableAppointment myTimetableAppointment where myTimetableAppointment.createdBy = ?1"),
		@NamedQuery(name = "findTimetableAppointmentByCreatedDate", query = "select myTimetableAppointment from TimetableAppointment myTimetableAppointment where myTimetableAppointment.createdDate = ?1"),
		@NamedQuery(name = "findTimetableAppointmentByDetail", query = "select myTimetableAppointment from TimetableAppointment myTimetableAppointment where myTimetableAppointment.detail = ?1"),
		@NamedQuery(name = "findTimetableAppointmentByDetailContaining", query = "select myTimetableAppointment from TimetableAppointment myTimetableAppointment where myTimetableAppointment.detail like ?1"),
		@NamedQuery(name = "findTimetableAppointmentByEnabled", query = "select myTimetableAppointment from TimetableAppointment myTimetableAppointment where myTimetableAppointment.enabled = ?1"),
		@NamedQuery(name = "findTimetableAppointmentByEndClass", query = "select myTimetableAppointment from TimetableAppointment myTimetableAppointment where myTimetableAppointment.endClass = ?1"),
		@NamedQuery(name = "findTimetableAppointmentByEndWeek", query = "select myTimetableAppointment from TimetableAppointment myTimetableAppointment where myTimetableAppointment.endWeek = ?1"),
		@NamedQuery(name = "findTimetableAppointmentById", query = "select myTimetableAppointment from TimetableAppointment myTimetableAppointment where myTimetableAppointment.id = ?1"),
		@NamedQuery(name = "findTimetableAppointmentByMemo", query = "select myTimetableAppointment from TimetableAppointment myTimetableAppointment where myTimetableAppointment.memo = ?1"),
		@NamedQuery(name = "findTimetableAppointmentByMemoContaining", query = "select myTimetableAppointment from TimetableAppointment myTimetableAppointment where myTimetableAppointment.memo like ?1"),
		@NamedQuery(name = "findTimetableAppointmentByPrimaryKey", query = "select myTimetableAppointment from TimetableAppointment myTimetableAppointment where myTimetableAppointment.id = ?1"),
		@NamedQuery(name = "findTimetableAppointmentByStartClass", query = "select myTimetableAppointment from TimetableAppointment myTimetableAppointment where myTimetableAppointment.startClass = ?1"),
		@NamedQuery(name = "findTimetableAppointmentByStartWeek", query = "select myTimetableAppointment from TimetableAppointment myTimetableAppointment where myTimetableAppointment.startWeek = ?1"),
		@NamedQuery(name = "findTimetableAppointmentByStatus", query = "select myTimetableAppointment from TimetableAppointment myTimetableAppointment where myTimetableAppointment.status = ?1"),
		@NamedQuery(name = "findTimetableAppointmentByTimetableNumber", query = "select myTimetableAppointment from TimetableAppointment myTimetableAppointment where myTimetableAppointment.timetableNumber = ?1"),
		@NamedQuery(name = "findTimetableAppointmentByTimetableStyle", query = "select myTimetableAppointment from TimetableAppointment myTimetableAppointment where myTimetableAppointment.timetableStyle = ?1"),
		@NamedQuery(name = "findTimetableAppointmentByTotalClasses", query = "select myTimetableAppointment from TimetableAppointment myTimetableAppointment where myTimetableAppointment.totalClasses = ?1"),
		@NamedQuery(name = "findTimetableAppointmentByTotalWeeks", query = "select myTimetableAppointment from TimetableAppointment myTimetableAppointment where myTimetableAppointment.totalWeeks = ?1"),
		@NamedQuery(name = "findTimetableAppointmentByTotalWeeksContaining", query = "select myTimetableAppointment from TimetableAppointment myTimetableAppointment where myTimetableAppointment.totalWeeks like ?1"),
		@NamedQuery(name = "findTimetableAppointmentByUpdatedBy", query = "select myTimetableAppointment from TimetableAppointment myTimetableAppointment where myTimetableAppointment.updatedBy = ?1"),
		@NamedQuery(name = "findTimetableAppointmentByUpdatedDate", query = "select myTimetableAppointment from TimetableAppointment myTimetableAppointment where myTimetableAppointment.updatedDate = ?1"),
		@NamedQuery(name = "findTimetableAppointmentByWeekday", query = "select myTimetableAppointment from TimetableAppointment myTimetableAppointment where myTimetableAppointment.weekday = ?1") })
@Table(catalog = "xidlims", name = "timetable_appointment")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "TimetableAppointment")
@XmlRootElement(namespace = "xidlims/net/xidlims/domain")
public class TimetableAppointment implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * �γ�ԤԼʱ���
	 * 
	 */

	@Column(name = "id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	@XmlElement
	Integer id;
	/**
	 * �ſα��
	 * 
	 */

	@Column(name = "timetable_number")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer timetableNumber;
	/**
	 * ѡ������
	 * 
	 */

	@Column(name = "course_code")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String courseCode;
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
	 * �ſ�״̬ 1���ѷ��� 10δ���� 2����
	 * 
	 */

	@Column(name = "status")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer status;
	/**
	 * ��ע
	 * 
	 */

	@Column(name = "memo", length = 100)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String memo;
	/**
	 * ԤԼ���
	 * 
	 */

	@Column(name = "appointment_no")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String appointmentNo;
	/**
	 * ���ŵ���ϸ
	 * 
	 */

	@Column(name = "detail", length = 100)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String detail;
	/**
	 * �Ƿ�����
	 * 
	 */

	@Column(name = "enabled")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Boolean enabled;
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
	 * �ſη�ʽ��1ֱ���ſ�2�����ſ�3���β������ſ�4���η����ſ�5�����ſ�
	 * 
	 */

	@Column(name = "timetable_style")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer timetableStyle;
	
	@Column(name = "preparation", length = 255)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String preparation;
	
	@Column(name = "groups")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer groups;
	
	@Column(name = "group_count")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer groupCount;
	
	@Column(name = "labhours")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer labhours;
	
	@Column(name = "consumables_costs", scale = 2, precision = 11)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal consumablesCosts;
	
	/**
	 * 此次排课针对的是设备还是实验室（1 实验室  2 设备）
	 * 
	 */

	@Column(name = "device_or_lab")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer deviceOrLab;
	
	/**
	 * ѡ�ο�ʼ����
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "actual_start_date")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar actualStartDate;
	
	/**
	 * ѡ�ο�ʼ����
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "actual_end_date")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar actualEndDate;
	
	/**
	 * ��ע
	 * 
	 */

	@Column(name = "use_condition", columnDefinition = "TEXT")
	@Basic(fetch = FetchType.EAGER)
	@Lob
	@XmlElement
	String useCondition;
	
	
	public String getUseCondition() {
		return useCondition;
	}

	public void setUseCondition(String useCondition) {
		this.useCondition = useCondition;
	}

	public Calendar getActualEndDate() {
		return actualEndDate;
	}

	public void setActualEndDate(Calendar actualEndDate) {
		this.actualEndDate = actualEndDate;
	}

	public Calendar getActualStartDate() {
		return actualStartDate;
	}

	public void setActualStartDate(Calendar actualStartDate) {
		this.actualStartDate = actualStartDate;
	}
	
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "detail_id", referencedColumnName = "course_detail_no") })
	@XmlTransient
	SchoolCourseDetail schoolCourseDetail;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "course_number", referencedColumnName = "course_number") })
	@XmlTransient
	SchoolCourseInfo schoolCourseInfo;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "course_no", referencedColumnName = "course_no") })
	@XmlTransient
	SchoolCourse schoolCourse;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "self_course_code", referencedColumnName = "id") })
	@XmlTransient
	TimetableSelfCourse timetableSelfCourse;
	/**
	 */
	@OneToMany(mappedBy = "timetableAppointment", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.TimetableTutorRelated> timetableTutorRelateds;
	/**
	 */
	@OneToMany(mappedBy = "timetableAppointment", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.TimetableItemRelated> timetableItemRelateds;
	/**
	 */
	@OneToMany(mappedBy = "timetableAppointment", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.TimetableLabRelated> timetableLabRelateds;
	/**
	 */
	@OneToMany(mappedBy = "timetableAppointment", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.TimetableAttendance> timetableAttendances;

	/**
	 */
	@OneToMany(mappedBy = "timetableAppointment", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.TimetableTeacherRelated> timetableTeacherRelateds;

	/**
	 */
	@OneToMany(mappedBy = "timetableAppointment", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	@OrderBy("startClass,startWeek ASC")
	java.util.Set<net.xidlims.domain.TimetableAppointmentSameNumber> timetableAppointmentSameNumbers;

	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(catalog = "xidlims", name = "timetable_appointment_group", joinColumns = { @JoinColumn(name = "appointment_id", referencedColumnName = "id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "group_id", referencedColumnName = "id", nullable = false, updatable = false) })
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.TimetableGroup> timetableGroups;
	
	
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "merge_id", referencedColumnName = "id") })
	@XmlTransient
	SchoolCourseMerge schoolCourseMerge;
	
	/**
	 */
	public void setSchoolCourseMerge(SchoolCourseMerge schoolCourseMerge) {
		this.schoolCourseMerge = schoolCourseMerge;
	}

	/**
	 */
	@JsonIgnore
	public SchoolCourseMerge getSchoolCourseMerge() {
		return schoolCourseMerge;
	}
	
	
	public java.util.Set<net.xidlims.domain.TimetableGroup> getTimetableGroups() {
		return this.timetableGroups;
	}

	public void setTimetableGroups(
			java.util.Set<net.xidlims.domain.TimetableGroup> timetableGroups) {
		if (timetableGroups == null) {
			timetableGroups = new java.util.LinkedHashSet<net.xidlims.domain.TimetableGroup>();
		}
		this.timetableGroups = timetableGroups;
	}
	/**
	 * �γ�ԤԼʱ���
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * �γ�ԤԼʱ���
	 * 
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * �ſα��
	 * 
	 */
	public void setTimetableNumber(Integer timetableNumber) {
		this.timetableNumber = timetableNumber;
	}

	/**
	 * �ſα��
	 * 
	 */
	public Integer getTimetableNumber() {
		return this.timetableNumber;
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
	 * �ſ�״̬ 1���ѷ��� 10δ���� 2����
	 * 
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * �ſ�״̬ 1���ѷ��� 10δ���� 2����
	 * 
	 */
	public Integer getStatus() {
		return this.status;
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
	 * ԤԼ���
	 * 
	 */
	public void setAppointmentNo(String appointmentNo) {
		this.appointmentNo = appointmentNo;
	}

	/**
	 * ԤԼ���
	 * 
	 */
	public String getAppointmentNo() {
		return this.appointmentNo;
	}

	/**
	 * ���ŵ���ϸ
	 * 
	 */
	public void setDetail(String detail) {
		this.detail = detail;
	}

	/**
	 * ���ŵ���ϸ
	 * 
	 */
	public String getDetail() {
		return this.detail;
	}

	/**
	 * �Ƿ�����
	 * 
	 */
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	/**
	 * �Ƿ�����
	 * 
	 */
	public Boolean getEnabled() {
		return this.enabled;
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
	 * �ſη�ʽ��1ֱ���ſ�2�����ſ�3���β������ſ�4���η����ſ�5�����ſ�
	 * 
	 */
	public void setTimetableStyle(Integer timetableStyle) {
		this.timetableStyle = timetableStyle;
	}

	/**
	 * �ſη�ʽ��1ֱ���ſ�2�����ſ�3���β������ſ�4���η����ſ�5�����ſ�
	 * 
	 */
	public Integer getTimetableStyle() {
		return this.timetableStyle;
	}

	/**
	 */
	public void setSchoolCourseDetail(SchoolCourseDetail schoolCourseDetail) {
		this.schoolCourseDetail = schoolCourseDetail;
	}

	/**
	 */
	@JsonIgnore
	public SchoolCourseDetail getSchoolCourseDetail() {
		return schoolCourseDetail;
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
	public void setSchoolCourse(SchoolCourse schoolCourse) {
		this.schoolCourse = schoolCourse;
	}

	/**
	 */
	@JsonIgnore
	public SchoolCourse getSchoolCourse() {
		return schoolCourse;
	}
	
	/**
	 */
	public void setTimetableSelfCourse(TimetableSelfCourse timetableSelfCourse) {
		this.timetableSelfCourse = timetableSelfCourse;
	}

	/**
	 */
	@JsonIgnore
	public TimetableSelfCourse getTimetableSelfCourse() {
		return timetableSelfCourse;
	}
	
	
	/**
	 * ������
	 * 
	 */
	public void setPreparation(String preparation) {
		this.preparation = preparation;
	}

	/**
	 * ������
	 * 
	 */
	public String getPreparation() {
		return this.preparation;
	}

	
	/**
	 * ������
	 * 
	 */
	public void setGroups(Integer groups) {
		this.groups = groups;
	}

	/**
	 * ������
	 * 
	 */
	public Integer getGroups() {
		return this.groups;
	}
	
	public void setGroupCount(Integer groupCount) {
		this.groupCount = groupCount;
	}

	/**
	 * ������
	 * 
	 */
	public Integer getGroupCount() {
		return this.groupCount;
	}


	
	/**
	 * ������
	 * 
	 */
	public void setLabhours(Integer labhours) {
		this.labhours = labhours;
	}

	/**
	 * ������
	 * 
	 */
	public Integer getLabhours() {
		return this.labhours;
	}


	/**
	 */
	public void setConsumablesCosts(BigDecimal consumablesCosts) {
		this.consumablesCosts = consumablesCosts;
	}

	/**
	 */
	public BigDecimal getConsumablesCosts() {
		return this.consumablesCosts;
	}
	
	public Integer getDeviceOrLab() {
		return deviceOrLab;
	}

	public void setDeviceOrLab(Integer deviceOrLab) {
		this.deviceOrLab = deviceOrLab;
	}

	/**
	 */
	public void setTimetableTutorRelateds(Set<TimetableTutorRelated> timetableTutorRelateds) {
		this.timetableTutorRelateds = timetableTutorRelateds;
	}

	/**
	 */
	@JsonIgnore
	public Set<TimetableTutorRelated> getTimetableTutorRelateds() {
		if (timetableTutorRelateds == null) {
			timetableTutorRelateds = new java.util.LinkedHashSet<net.xidlims.domain.TimetableTutorRelated>();
		}
		return timetableTutorRelateds;
	}

	/**
	 */
	public void setTimetableItemRelateds(Set<TimetableItemRelated> timetableItemRelateds) {
		this.timetableItemRelateds = timetableItemRelateds;
	}

	/**
	 */
	@JsonIgnore
	public Set<TimetableItemRelated> getTimetableItemRelateds() {
		if (timetableItemRelateds == null) {
			timetableItemRelateds = new java.util.LinkedHashSet<net.xidlims.domain.TimetableItemRelated>();
		}
		return timetableItemRelateds;
	}
	
	/**
	 */
	public void setTimetableAppointmentSameNumbers(Set<TimetableAppointmentSameNumber> timetableAppointmentSameNumbers) {
		this.timetableAppointmentSameNumbers = timetableAppointmentSameNumbers;
	}

	/**
	 */
	@JsonIgnore
	public Set<TimetableAppointmentSameNumber> getTimetableAppointmentSameNumbers() {
		if (timetableAppointmentSameNumbers == null) {
			timetableAppointmentSameNumbers = new java.util.LinkedHashSet<net.xidlims.domain.TimetableAppointmentSameNumber>();
		}
		return timetableAppointmentSameNumbers;
	}

	/**
	 */
	public void setTimetableLabRelateds(Set<TimetableLabRelated> timetableLabRelateds) {
		this.timetableLabRelateds = timetableLabRelateds;
	}

	/**
	 */
	@JsonIgnore
	public Set<TimetableLabRelated> getTimetableLabRelateds() {
		if (timetableLabRelateds == null) {
			timetableLabRelateds = new java.util.LinkedHashSet<net.xidlims.domain.TimetableLabRelated>();
		}
		return timetableLabRelateds;
	}

	/**
	 */
	public void setTimetableAttendances(Set<TimetableAttendance> timetableAttendances) {
		this.timetableAttendances = timetableAttendances;
	}

	/**
	 */
	@JsonIgnore
	public Set<TimetableAttendance> getTimetableAttendances() {
		if (timetableAttendances == null) {
			timetableAttendances = new java.util.LinkedHashSet<net.xidlims.domain.TimetableAttendance>();
		}
		return timetableAttendances;
	}

	/**
	 */
	public void setTimetableTeacherRelateds(Set<TimetableTeacherRelated> timetableTeacherRelateds) {
		this.timetableTeacherRelateds = timetableTeacherRelateds;
	}

	/**
	 */
	@JsonIgnore
	public Set<TimetableTeacherRelated> getTimetableTeacherRelateds() {
		if (timetableTeacherRelateds == null) {
			timetableTeacherRelateds = new java.util.LinkedHashSet<net.xidlims.domain.TimetableTeacherRelated>();
		}
		return timetableTeacherRelateds;
	}

	/**
	 */
	public TimetableAppointment() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(TimetableAppointment that) {
		setId(that.getId());
		setTimetableNumber(that.getTimetableNumber());
		setCourseCode(that.getCourseCode());
		setCreatedDate(that.getCreatedDate());
		setUpdatedDate(that.getUpdatedDate());
		setCreatedBy(that.getCreatedBy());
		setUpdatedBy(that.getUpdatedBy());
		setStatus(that.getStatus());
		setMemo(that.getMemo());
		setAppointmentNo(that.getAppointmentNo());
		setDetail(that.getDetail());
		setEnabled(that.getEnabled());
		setTotalWeeks(that.getTotalWeeks());
		setWeekday(that.getWeekday());
		setStartWeek(that.getStartWeek());
		setEndWeek(that.getEndWeek());
		setTotalClasses(that.getTotalClasses());
		setStartClass(that.getStartClass());
		setEndClass(that.getEndClass());
		setTimetableStyle(that.getTimetableStyle());
		setSchoolCourseDetail(that.getSchoolCourseDetail());
		setSchoolCourse(that.getSchoolCourse());
		setTimetableTutorRelateds(new java.util.LinkedHashSet<net.xidlims.domain.TimetableTutorRelated>(that.getTimetableTutorRelateds()));
		setTimetableItemRelateds(new java.util.LinkedHashSet<net.xidlims.domain.TimetableItemRelated>(that.getTimetableItemRelateds()));
		setTimetableLabRelateds(new java.util.LinkedHashSet<net.xidlims.domain.TimetableLabRelated>(that.getTimetableLabRelateds()));
		setTimetableAttendances(new java.util.LinkedHashSet<net.xidlims.domain.TimetableAttendance>(that.getTimetableAttendances()));
		setTimetableGroups(new java.util.LinkedHashSet<net.xidlims.domain.TimetableGroup>(that.getTimetableGroups()));
		setTimetableTeacherRelateds(new java.util.LinkedHashSet<net.xidlims.domain.TimetableTeacherRelated>(that.getTimetableTeacherRelateds()));
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("id=[").append(id).append("] ");
		buffer.append("timetableNumber=[").append(timetableNumber).append("] ");
		buffer.append("courseCode=[").append(courseCode).append("] ");
		buffer.append("createdDate=[").append(createdDate).append("] ");
		buffer.append("updatedDate=[").append(updatedDate).append("] ");
		buffer.append("createdBy=[").append(createdBy).append("] ");
		buffer.append("updatedBy=[").append(updatedBy).append("] ");
		buffer.append("status=[").append(status).append("] ");
		buffer.append("memo=[").append(memo).append("] ");
		buffer.append("appointmentNo=[").append(appointmentNo).append("] ");
		buffer.append("detail=[").append(detail).append("] ");
		buffer.append("enabled=[").append(enabled).append("] ");
		buffer.append("totalWeeks=[").append(totalWeeks).append("] ");
		buffer.append("weekday=[").append(weekday).append("] ");
		buffer.append("startWeek=[").append(startWeek).append("] ");
		buffer.append("endWeek=[").append(endWeek).append("] ");
		buffer.append("totalClasses=[").append(totalClasses).append("] ");
		buffer.append("startClass=[").append(startClass).append("] ");
		buffer.append("endClass=[").append(endClass).append("] ");
		buffer.append("timetableStyle=[").append(timetableStyle).append("] ");

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
		if (!(obj instanceof TimetableAppointment))
			return false;
		TimetableAppointment equalCheck = (TimetableAppointment) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
