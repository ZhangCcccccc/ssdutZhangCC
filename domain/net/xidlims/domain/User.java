package net.xidlims.domain;

import java.io.Serializable;
import java.util.Calendar;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
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


/**
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "findAllUsers", query = "select myUser from User myUser"),
		@NamedQuery(name = "findUserByAttendanceTime", query = "select myUser from User myUser where myUser.attendanceTime = ?1"),
		@NamedQuery(name = "findUserByAttendanceTimeContaining", query = "select myUser from User myUser where myUser.attendanceTime like ?1"),
		@NamedQuery(name = "findUserByCardno", query = "select myUser from User myUser where myUser.cardno = ?1"),
		@NamedQuery(name = "findUserByCardnoContaining", query = "select myUser from User myUser where myUser.cardno like ?1"),
		@NamedQuery(name = "findUserByCname", query = "select myUser from User myUser where myUser.cname = ?1"),
		@NamedQuery(name = "findUserByCnameContaining", query = "select myUser from User myUser where myUser.cname like ?1"),
		@NamedQuery(name = "findUserByCreatedAt", query = "select myUser from User myUser where myUser.createdAt = ?1"),
		@NamedQuery(name = "findUserByCreatedAtAfter", query = "select myUser from User myUser where myUser.createdAt > ?1"),
		@NamedQuery(name = "findUserByCreatedAtBefore", query = "select myUser from User myUser where myUser.createdAt < ?1"),
		@NamedQuery(name = "findUserByEmail", query = "select myUser from User myUser where myUser.email = ?1"),
		@NamedQuery(name = "findUserByEmailContaining", query = "select myUser from User myUser where myUser.email like ?1"),
		@NamedQuery(name = "findUserByEnabled", query = "select myUser from User myUser where myUser.enabled = ?1"),
		@NamedQuery(name = "findUserByEnrollmentStatus", query = "select myUser from User myUser where myUser.enrollmentStatus = ?1"),
		@NamedQuery(name = "findUserByGrade", query = "select myUser from User myUser where myUser.grade = ?1"),
		@NamedQuery(name = "findUserByGradeContaining", query = "select myUser from User myUser where myUser.grade like ?1"),
		@NamedQuery(name = "findUserByIfEnrollment", query = "select myUser from User myUser where myUser.ifEnrollment = ?1"),
		@NamedQuery(name = "findUserByIfEnrollmentContaining", query = "select myUser from User myUser where myUser.ifEnrollment like ?1"),
		@NamedQuery(name = "findUserByLastLogin", query = "select myUser from User myUser where myUser.lastLogin = ?1"),
		@NamedQuery(name = "findUserByMajorDirection", query = "select myUser from User myUser where myUser.majorDirection = ?1"),
		@NamedQuery(name = "findUserByMajorDirectionContaining", query = "select myUser from User myUser where myUser.majorDirection like ?1"),
		@NamedQuery(name = "findUserByMajorNumber", query = "select myUser from User myUser where myUser.majorNumber = ?1"),
		@NamedQuery(name = "findUserByMajorNumberContaining", query = "select myUser from User myUser where myUser.majorNumber like ?1"),
		@NamedQuery(name = "findUserByPassword", query = "select myUser from User myUser where myUser.password = ?1"),
		@NamedQuery(name = "findUserByPasswordContaining", query = "select myUser from User myUser where myUser.password like ?1"),
		@NamedQuery(name = "findUserByPrimaryKey", query = "select myUser from User myUser where myUser.username = ?1"),
		@NamedQuery(name = "findUserByTeacherNumber", query = "select myUser from User myUser where myUser.teacherNumber = ?1"),
		@NamedQuery(name = "findUserByTelephone", query = "select myUser from User myUser where myUser.telephone = ?1"),
		@NamedQuery(name = "findUserByTelephoneContaining", query = "select myUser from User myUser where myUser.telephone like ?1"),
		@NamedQuery(name = "findUserByUpdatedAt", query = "select myUser from User myUser where myUser.updatedAt = ?1"),
		@NamedQuery(name = "findUserByUpdatedAtAfter", query = "select myUser from User myUser where myUser.updatedAt > ?1"),
		@NamedQuery(name = "findUserByUpdatedAtBefore", query = "select myUser from User myUser where myUser.updatedAt < ?1"),
		@NamedQuery(name = "findUserByUserRole", query = "select myUser from User myUser where myUser.userRole = ?1"),
		@NamedQuery(name = "findUserByUserRoleContaining", query = "select myUser from User myUser where myUser.userRole like ?1"),
		@NamedQuery(name = "findUserByUserSexy", query = "select myUser from User myUser where myUser.userSexy = ?1"),
		@NamedQuery(name = "findUserByUserSexyContaining", query = "select myUser from User myUser where myUser.userSexy like ?1"),
		@NamedQuery(name = "findUserByUserStatus", query = "select myUser from User myUser where myUser.userStatus = ?1"),
		@NamedQuery(name = "findUserByUserStatusContaining", query = "select myUser from User myUser where myUser.userStatus like ?1"),
		@NamedQuery(name = "findUserByUserType", query = "select myUser from User myUser where myUser.userType = ?1"),
		@NamedQuery(name = "findUserByUsername", query = "select myUser from User myUser where myUser.username = ?1"),
		@NamedQuery(name = "findUserByUsernameContaining", query = "select myUser from User myUser where myUser.username like ?1") })
@Table(catalog = "xidlims", name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "User")
@XmlRootElement(namespace = "xidlims/net/xidlims/domain")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * ѧ��/����
	 * 
	 */

	@Column(name = "username", length = 40, nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	String username;
	/**
	 * ����
	 * 
	 */

	@Column(name = "cardno")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String cardno;
	/**
	 * ����
	 * 
	 */

	@Column(name = "cname", length = 100, nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String cname;
	/**
	 * ����
	 * 
	 */

	@Column(name = "password", length = 120, nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String password;
	/**
	 * �Ա�
	 * 
	 */

	@Column(name = "user_sexy", length = 10)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String userSexy;
	/**
	 * �Ƿ���У
	 * 
	 */

	@Column(name = "user_status", length = 10)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String userStatus;
	/**
	 * ����Ա����
	 * 
	 */

	@Column(name = "teacher_number")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer teacherNumber;
	/**
	 * רҵ����
	 * 
	 */

	@Column(name = "major_number", length = 40)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String majorNumber;
	/**
	 * �û���ɫ��0��ѧ��1�ǽ�ʦ��
	 * 
	 */

	@Column(name = "user_role", length = 40)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String userRole;
	
	/**
	 * ����¼ʱ��
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_login")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar lastLogin;
	/**
	 * ����ʱ��
	 * 
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "created_at")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar createdAt;
	/**
	 * ����ʱ��
	 * 
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "updated_at")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar updatedAt;
	/**
	 * ��ϵ�绰
	 * 
	 */

	@Column(name = "telephone", length = 20)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String telephone;
	/**
	 * �ʼ�
	 * 
	 */

	@Column(name = "email", length = 20)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String email;
	/**
	 * �Ƿ����
	 * 
	 */

	@Column(name = "enabled")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Boolean enabled;
	/**
	 * ְ��
	 * 
	 */

	@Column(name = "major_direction", length = 100)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String majorDirection;
	/**
	 * ѧ��״̬
	 * 
	 */

	@Column(name = "enrollment_status")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer enrollmentStatus;
	/**
	 * �Ƿ��ڼ�
	 * 
	 */

	@Column(name = "if_enrollment", length = 40)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String ifEnrollment;
	/**
	 * ѧ�����
	 * 
	 */

	@Column(name = "user_type")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer userType;
	/**
	 * ��ѧ���
	 * 
	 */

	@Column(name = "attendance_time", length = 20)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String attendanceTime;
	/**
	 * �����꼶
	 * 
	 */

	@Column(name = "grade", length = 20)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String grade;
	
		
	//QQ
	@Column(name = "qq", length = 20)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String qq;
	
	/**
	 * רҵ����
	 * 
	 */
	@Column(name = "online")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer online;
	
	/**
	 * רҵ����
	 * 
	 */
	@Column(name = "phone_ip")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String phoneIP;
	
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "center_id", referencedColumnName = "id") })
	@XmlTransient
	LabCenter labCenter;
	
	/**
	 * רҵ����
	 * 
	 */
	@Column(name = "public_withdrawl_times")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer publicWithdrawlTimes;
	
	public Integer getPublicWithdrawlTimes() {
		return publicWithdrawlTimes;
	}

	public void setPublicWithdrawlTimes(Integer publicWithdrawlTimes) {
		this.publicWithdrawlTimes = publicWithdrawlTimes;
	}

	/**
	 */
	public void setLabCenter(LabCenter labCenter) {
		this.labCenter = labCenter;
	}

	/**
	 */
	@JsonIgnore
	public LabCenter getLabCenter() {
		return labCenter;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}
	/**
	 * רҵ����
	 * 
	 */
	@Column(name = "photo")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String photo;
	
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "academy_number", referencedColumnName = "academy_number") })
	@XmlTransient
	SchoolAcademy schoolAcademy;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "classes_number", referencedColumnName = "class_number") })
	@XmlTransient
	SchoolClasses schoolClasses;
	
	/**
	 */
	@OneToMany(mappedBy = "userByCreatedBy", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.TimetableAttendance> timetableAttendancesForUserNumber;
	/**
	 */
	@OneToMany(mappedBy = "userByCenterManager", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.LabCenter> labCentersForCenterManager;
	/**
	 */
	@OneToMany(mappedBy = "userByCreatedBy", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.LabCenter> labCentersForCreatedBy;
	/**
	 */
	@OneToMany(mappedBy = "userByUpdatedBy", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.LabCenter> labCentersForUpdatedBy;
	/**
	 */
	@OneToMany(mappedBy = "userByKeepUser", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.SchoolDevice> schoolDevicesForUserNumber;
	/**
	 */
	@OneToMany(mappedBy = "user", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.TimetableTutorRelated> timetableTutorRelateds;
	/**
	 */
	@OneToMany(mappedBy = "user", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.TimetableGroupStudents> timetableGroupStudentses;
	/**
	 */
	@OneToMany(mappedBy = "userByKeepUser", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.SchoolDevice> schoolDevicesForKeepUser;
	/**
	 */
	@OneToMany(mappedBy = "userByCreatedBy", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.TimetableAttendance> timetableAttendancesForCreatedBy;
	/**
	 */
	@OneToMany(mappedBy = "user", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.LabRoomAdmin> labRoomAdmins;
	/**
	 */
	@OneToMany(mappedBy = "userByTeacherNumber", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.SchoolCourseStudent> schoolCourseStudentsForTeacherNumber;
	/**
	 */
	@OneToMany(mappedBy = "user", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.LabRoomDevice> labRoomDevices;
	/**
	 */
	@OneToMany(mappedBy = "userByCreatedBy", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.SchoolCourse> schoolCoursesForCreatedBy;
	/**
	 */
	@OneToMany(mappedBy = "userByCreatedBy", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.SchoolCourse> schoolCoursesForUpdatedBy;
	/**
	 */
	@OneToMany(mappedBy = "userByLpTeacherAssistantId", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.OperationItem> operationItemsForLpTeacherAssistantId;
	/**
	 */
	@OneToMany(mappedBy = "userByLpTeacherSpeakerId", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.OperationItem> operationItemsForLpTeacherSpeakerId;
	
	@OneToMany(mappedBy = "userByLpCheckUser", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.OperationItem> operationItemsForLpCheckUser;
	
	@OneToMany(mappedBy = "userByLpCreateUser", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.OperationItem> operationItemsForLpCreateUser;

	/**
	 */
	@OneToMany(mappedBy = "userByCreatedBy", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.SchoolCourse> schoolCoursesForTeacher;
	/**
	 */
	@OneToMany(mappedBy = "user", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.UserCard> userCards;
	
	/**
	 */
	@OneToMany(mappedBy = "user", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.LabRoomDeviceTraining> labRoomDeviceTrainings;
	/**
	 */
	@OneToMany(mappedBy = "user", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.LabRoomDevicePermitUsers> labRoomDevicePermitUserses;
	/**
	 */
	@OneToMany(mappedBy = "user", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.LabRoomDeviceTrainingPeople> labRoomDeviceTrainingPeoples;

	/**
	 */
	@OneToMany(mappedBy = "userByTeacherNumber", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.SchoolCourseStudent> schoolCourseStudentsForStudentNumber;
	/**
	 */
	@OneToMany(mappedBy = "user", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.TimetableTeacherRelated> timetableTeacherRelateds;
	/**
	 */
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(catalog = "xidlims", name = "user_authority", joinColumns = { @JoinColumn(name = "user_id", referencedColumnName = "username", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "authority_id", referencedColumnName = "id", nullable = false, updatable = false) })
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.Authority> authorities;
	/**
	 */
	@OneToMany(mappedBy = "user", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.TimetableSelfCourse> timetableSelfCourses;
	
	@OneToMany(mappedBy = "user", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.LabRoom> labRooms;
	
	/**
	 */
	@OneToMany(mappedBy = "user", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.TimetableCourseStudent> timetableCourseStudents;
	/**
	 */
	@OneToMany(mappedBy = "user", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.SchoolCourseDetail> schoolCourseDetails;
	
	@OneToMany(mappedBy = "user", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.LabReservation> labReservations;
	
	@OneToMany(mappedBy = "user", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.LabReservationAudit> labReservationAudits;
	
	@OneToMany(mappedBy = "user", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.LabReservationTimeTableStudent> labReservationTimeTableStudents;
	//新增
	/**
	 */
	@OneToMany(mappedBy = "user", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.LabConstructionProjectAudit> labConstructionProjectAudits;
	/**
	 */
	@OneToMany(mappedBy = "user", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.LabConstructionPurchaseAudit> labConstructionPurchaseAudits;
	/**
	 *//*
	@ManyToMany(fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.LabConstructionProject> labConstructionProjects;*/
	/**
	 */
	@ManyToMany(mappedBy = "user", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)//2015-10-09 更改  原来是 @OneToMany
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.MLabConstructionProjectUser> MLabConstructionProjectUsers;
	
	/**
	 */
	@OneToMany(mappedBy = "userByKeeper", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)//2015-10-09 更改  原来是 userByApplicant
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.LabConstructionPurchase> labConstructionPurchasesForKeeper;
	/**
	 */
	@OneToMany(mappedBy = "user", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.LabConstructionProject> labConstructionProjects_1;
	/**
	 */
	@OneToMany(mappedBy = "userByCreatedBy", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.LabConstructionProject> labConstructionProjects;
	
	
	/**
	 */
	@OneToMany(mappedBy = "user", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.LabConstructionFundingAudit> labConstructionFundingAudits;
	/**
	 */
	@OneToMany(mappedBy = "userByApplicant", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.LabConstructionPurchase> labConstructionPurchasesForApplicant;
	
	
	/**
	 */
	@OneToMany(mappedBy = "user", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.CommonDocument> commonDocument;
	
	
	
	/**
	 */
	@OneToMany(mappedBy = "userByReserveUser", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.LabRoomDeviceReservation> labRoomDeviceReservationsForReserveUser;
	/**
	 */
	@OneToMany(mappedBy = "userByReserveUser", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.LabRoomDeviceReservation> labRoomDeviceReservationsForTeacher;
	
	/**
	 */
	@OneToMany(mappedBy = "userByCreatedBy", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.SystemBuild> systemBuildsForCreatedBy;
	/**
	 */
	@OneToMany(mappedBy = "userByCreatedBy", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.SystemBuild> systemBuildsForUpdatedBy;
	/**
	 */
	@OneToMany(mappedBy = "user", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.LabRoomDeviceReservationResult> labRoomDeviceReservationResults;
	/**
	 */
	@ManyToMany(mappedBy = "user", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.LabReservation> labReservations_1;
	/**
	 */
	@OneToMany(mappedBy = "user", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.WkFolder> folders;

	/**
	 */
	@OneToMany(mappedBy = "user", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.TCourseSiteUser> TCourseSiteUsers;

	/**
	 */
	@OneToMany(mappedBy = "userByGradeBy", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.TAssignmentGrading> TAssignmentGradingsForGradeBy;
	/**
	 */
	@OneToMany(mappedBy = "user", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.TAssignmentItem> TAssignmentItems;
	/**
	 */
	@OneToMany(mappedBy = "userByCreatedBy", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.TCourseSite> TCourseSitesForCreatedBy;
	/**
	 */
	@OneToMany(mappedBy = "userByStudent", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.TAssignmentGrading> TAssignmentGradingsForStudent;
	/**
	 */
	@OneToMany(mappedBy = "userByModifiedBy", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.TCourseSite> TCourseSitesForModifiedBy;
	/**
	 */
	@OneToMany(mappedBy = "user", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.TAssignmentQuestionpool> TAssignmentQuestionpools;
	/**
	 */
	@OneToMany(mappedBy = "user", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.TAssignment> TAssignments;
	/**
	 */
	@ManyToMany(mappedBy = "user", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.TAssignmentQuestionpool> TAssignmentQuestionpools_1;
	/**
	 */
	@OneToMany(mappedBy = "user", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.TAssignmentSection> TAssignmentSections;

	/**
	 */
	@OneToMany(mappedBy = "user", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.TGradeRecord> TGradeRecords;
	
	@OneToMany(mappedBy = "createUser", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.TCourseSiteChannel> TCourseSiteChannels;
	
	/**
	 */
	@OneToMany(mappedBy = "createUser", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.TCourseSiteArtical> TCourseSiteArticals;
	
	
	/**
	 */
	@OneToMany(mappedBy = "user", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.TAssignmentAnswerAssign> TAssignmentAnswerAssigns;

	/**
	 */
	@OneToMany(mappedBy = "userByStudent", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.TAssignmentItemMapping> TAssignmentItemMappingsForStudent;

	/**
	 */
	@OneToMany(mappedBy = "userByGradeby", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.TAssignmentItemMapping> TAssignmentItemMappingsForGradeby;

	/**
	 * 一对多（该用户发布的通知公告）
	 */
	@OneToMany(mappedBy = "user", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.TMessage> sendMessages;
	
	
	/**
	 */
	@OneToMany(mappedBy = "user", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.TExerciseItemRecord> TExerciseItemRecords;
	/**
	 */
	@OneToMany(mappedBy = "user", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.TExerciseInfo> TExerciseInfos;
	/**
	 */
	@OneToMany(mappedBy = "user", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.TMistakeItem> TMistakeItems;
	/**
	 */
	@OneToMany(mappedBy = "user", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.OperationOutline> operationOutlines;
	/**
	 */
	@OneToMany(mappedBy = "user", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.NDeviceAuditRecord> NDeviceAuditRecords;
	/**
	 */
	@OneToMany(mappedBy = "user", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.NDevicePurchaseDetail> NDevicePurchaseDetails;
	/**
	 */
	@OneToMany(mappedBy = "user", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.NDevicePurchase> NDevicePurchases;

	/**
	 */
	@OneToMany(mappedBy = "user", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.AssetApp> assetApps;
	/**
	 */
	@OneToMany(mappedBy = "user", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.AssetReceive> assetReceives;
	/**
	 */
	@OneToMany(mappedBy = "user", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.AssetCabinetWarehouseAccess> assetCabinetWarehouseAccesses;
	
	/**
	 */
	@OneToMany(mappedBy = "user", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.AssetAdjustRecord> assetAdjustRecords;
	/**
	 */
	@OneToMany(mappedBy = "user", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.AssetAppAudit> assetAppAudits;
	/**
	 */
	@OneToMany(mappedBy = "user", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.AssetReceiveAudit> assetReceiveAudits;
	/**
	 */
	@OneToMany(mappedBy = "user", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.CmsChannel> cmsChannels;
	/**
	 */
	@OneToMany(mappedBy = "user", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.CmsArticle> cmsArticles;
	/**
	 */
	@OneToMany(mappedBy = "user", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.CmsSite> cmsSites;
	
	/**
	 */
	@OneToMany(mappedBy = "user", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.LabRoomDeviceRepair> labRoomDeviceRepairs;
	
	@OneToMany(mappedBy = "user", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.LabWorker> labWorkers;
	
	/**
	 */
	@OneToMany(mappedBy = "user", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.TDiscuss> tDiscusses;
	
	/**
	 */
	/**
	 */
	@OneToMany(mappedBy = "user", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.ExpendableStockRecord> expendableStockRecords;
	/**
	 */
	@OneToMany(mappedBy = "user", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.ExpendableApply> expendableApplies;
	/**
	 */
	@OneToMany(mappedBy = "user", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.Expendable> expendables;
	/**
	 */
	@OneToMany(mappedBy = "user", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.ExpendableApplyAuditRecord> expendableApplyAuditRecords;
	
	
	/**
	 */
	@OneToMany(mappedBy = "user", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.TimetableBatchStudent> timetableBatchStudents;
	/**
	 */
	@OneToMany(mappedBy = "user", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.TCourseSiteSchedule> TCourseSiteSchedules;
	/**
	 */
	@ManyToMany(mappedBy = "users", fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.SchoolCourseDetail> schoolCourseDetails2;
	
	/**
	 */
	@ManyToMany(mappedBy = "userByScheduleTeachers", fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.SchoolCourseDetail> schoolCourseDetails3;
	
	/**
	 */
	@ManyToMany(mappedBy = "users", fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.AppGroup> appGroups;
	/**
	 */
	@OneToMany(mappedBy = "user", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.AppPostImages> appPostImageses;
	/**
	 */
	@OneToMany(mappedBy = "user", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.AppPostReply> appPostReplies;
	/**
	 */
	@OneToMany(mappedBy = "user", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.AppPostlist> appPostlists;
	/**
	 */
	@OneToMany(mappedBy = "user", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.AppPostStatus> appPostStatuses;
	/**
	 */
	@ManyToMany(mappedBy = "userOfCollection", fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.AppPostlist> collectPosts;
	/**
	 */
	@ManyToMany(mappedBy = "likedUser", fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.AppPostlist> likedPosts;
	/**
	 */
	@OneToMany(mappedBy = "user", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.AppClouddickDownloadFolder> appClouddickDownloadFolders;
	/**
	 */
	@OneToMany(mappedBy = "user", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.AppClouddickDownloadFile> appClouddickDownloadFiles;
	
	/**
	 */
	public void setAppClouddickDownloadFiles(Set<AppClouddickDownloadFile> appClouddickDownloadFiles) {
		this.appClouddickDownloadFiles = appClouddickDownloadFiles;
	}

	/**
	 */
	@JsonIgnore
	public Set<AppClouddickDownloadFile> getAppClouddickDownloadFiles() {
		if (appClouddickDownloadFiles == null) {
			appClouddickDownloadFiles = new java.util.LinkedHashSet<net.xidlims.domain.AppClouddickDownloadFile>();
		}
		return appClouddickDownloadFiles;
	}
	/**
	 */
	public void setAppClouddickDownloadFolders(Set<AppClouddickDownloadFolder> appClouddickDownloadFolders) {
		this.appClouddickDownloadFolders = appClouddickDownloadFolders;
	}

	/**
	 */
	@JsonIgnore
	public Set<AppClouddickDownloadFolder> getAppClouddickDownloadFolders() {
		if (appClouddickDownloadFolders == null) {
			appClouddickDownloadFolders = new java.util.LinkedHashSet<net.xidlims.domain.AppClouddickDownloadFolder>();
		}
		return appClouddickDownloadFolders;
	}

	/**
	 */
	public void setTimetableBatchStudents(Set<TimetableBatchStudent> timetableBatchStudents) {
		this.timetableBatchStudents = timetableBatchStudents;
	}

	/**
	 */
	@JsonIgnore
	public Set<TimetableBatchStudent> getTimetableBatchStudents() {
		if (timetableBatchStudents == null) {
			timetableBatchStudents = new java.util.LinkedHashSet<net.xidlims.domain.TimetableBatchStudent>();
		}
		return timetableBatchStudents;
	}
	
	public java.util.Set<net.xidlims.domain.TCourseSiteSchedule> getTCourseSiteSchedules() {
		return TCourseSiteSchedules;
	}

	public void setTCourseSiteSchedules(
			java.util.Set<net.xidlims.domain.TCourseSiteSchedule> tCourseSiteSchedules) {
		TCourseSiteSchedules = tCourseSiteSchedules;
	}

	/**
	 */
	public void setOperationOutlines(Set<OperationOutline> operationOutlines) {
		this.operationOutlines = operationOutlines;
	}

	public java.util.Set<net.xidlims.domain.TDiscuss> gettDiscusses() {
		return tDiscusses;
	}

	public void settDiscusses(java.util.Set<net.xidlims.domain.TDiscuss> tDiscusses) {
		this.tDiscusses = tDiscusses;
	}
	
	/**
	 */
	public void setExpendableStockRecords(Set<ExpendableStockRecord> expendableStockRecords) {
		this.expendableStockRecords = expendableStockRecords;
	}

	/**
	 */
	@JsonIgnore
	public Set<ExpendableStockRecord> getExpendableStockRecords() {
		if (expendableStockRecords == null) {
			expendableStockRecords = new java.util.LinkedHashSet<net.xidlims.domain.ExpendableStockRecord>();
		}
		return expendableStockRecords;
	}

	/**
	 */
	public void setExpendableApplies(Set<ExpendableApply> expendableApplies) {
		this.expendableApplies = expendableApplies;
	}

	/**
	 */
	@JsonIgnore
	public Set<ExpendableApply> getExpendableApplies() {
		if (expendableApplies == null) {
			expendableApplies = new java.util.LinkedHashSet<net.xidlims.domain.ExpendableApply>();
		}
		return expendableApplies;
	}

	/**
	 */
	public void setExpendables(Set<Expendable> expendables) {
		this.expendables = expendables;
	}

	/**
	 */
	@JsonIgnore
	public Set<Expendable> getExpendables() {
		if (expendables == null) {
			expendables = new java.util.LinkedHashSet<net.xidlims.domain.Expendable>();
		}
		return expendables;
	}

	/**
	 */
	public void setExpendableApplyAuditRecords(Set<ExpendableApplyAuditRecord> expendableApplyAuditRecords) {
		this.expendableApplyAuditRecords = expendableApplyAuditRecords;
	}

	/**
	 */
	@JsonIgnore
	public Set<ExpendableApplyAuditRecord> getExpendableApplyAuditRecords() {
		if (expendableApplyAuditRecords == null) {
			expendableApplyAuditRecords = new java.util.LinkedHashSet<net.xidlims.domain.ExpendableApplyAuditRecord>();
		}
		return expendableApplyAuditRecords;
	}

	/**
	 */
	@JsonIgnore
	public Set<OperationOutline> getOperationOutlines() {
		if (operationOutlines == null) {
			operationOutlines = new java.util.LinkedHashSet<net.xidlims.domain.OperationOutline>();
		}
		return operationOutlines;
	}
	
	/**
	 */
	public void setAssetApps(Set<AssetApp> assetApps) {
		this.assetApps = assetApps;
	}

	/**
	 */
	@JsonIgnore
	public Set<AssetApp> getAssetApps() {
		if (assetApps == null) {
			assetApps = new java.util.LinkedHashSet<net.xidlims.domain.AssetApp>();
		}
		return assetApps;
	}

	/**
	 */
	public void setAssetReceives(Set<AssetReceive> assetReceives) {
		this.assetReceives = assetReceives;
	}

	/**
	 */
	@JsonIgnore
	public Set<AssetReceive> getAssetReceives() {
		if (assetReceives == null) {
			assetReceives = new java.util.LinkedHashSet<net.xidlims.domain.AssetReceive>();
		}
		return assetReceives;
	}

	/**
	 */
	public void setAssetCabinetWarehouseAccesses(Set<AssetCabinetWarehouseAccess> assetCabinetWarehouseAccesses) {
		this.assetCabinetWarehouseAccesses = assetCabinetWarehouseAccesses;
	}

	/**
	 */
	@JsonIgnore
	public Set<AssetCabinetWarehouseAccess> getAssetCabinetWarehouseAccesses() {
		if (assetCabinetWarehouseAccesses == null) {
			assetCabinetWarehouseAccesses = new java.util.LinkedHashSet<net.xidlims.domain.AssetCabinetWarehouseAccess>();
		}
		return assetCabinetWarehouseAccesses;
	}
	
	/**
	 */
	public void setAssetAdjustRecords(Set<AssetAdjustRecord> assetAdjustRecords) {
		this.assetAdjustRecords = assetAdjustRecords;
	}

	/**
	 */
	@JsonIgnore
	public Set<AssetAdjustRecord> getAssetAdjustRecords() {
		if (assetAdjustRecords == null) {
			assetAdjustRecords = new java.util.LinkedHashSet<net.xidlims.domain.AssetAdjustRecord>();
		}
		return assetAdjustRecords;
	}

	/**
	 */
	public void setAssetAppAudits(Set<AssetAppAudit> assetAppAudits) {
		this.assetAppAudits = assetAppAudits;
	}

	/**
	 */
	@JsonIgnore
	public Set<AssetAppAudit> getAssetAppAudits() {
		if (assetAppAudits == null) {
			assetAppAudits = new java.util.LinkedHashSet<net.xidlims.domain.AssetAppAudit>();
		}
		return assetAppAudits;
	}

	/**
	 */
	public void setAssetReceiveAudits(Set<AssetReceiveAudit> assetReceiveAudits) {
		this.assetReceiveAudits = assetReceiveAudits;
	}

	/**
	 */
	@JsonIgnore
	public Set<AssetReceiveAudit> getAssetReceiveAudits() {
		if (assetReceiveAudits == null) {
			assetReceiveAudits = new java.util.LinkedHashSet<net.xidlims.domain.AssetReceiveAudit>();
		}
		return assetReceiveAudits;
	}

	/**
	 */
	
	
	@JsonIgnore
	public java.util.Set<net.xidlims.domain.LabReservation> getLabReservations() {
		return labReservations;
	}

	public void setLabReservations(
			java.util.Set<net.xidlims.domain.LabReservation> labReservations) {
		this.labReservations = labReservations;
	}
	
	@JsonIgnore
	public java.util.Set<net.xidlims.domain.LabRoom> getLabRooms() {
		return labRooms;
	}

	public void setLabRooms(java.util.Set<net.xidlims.domain.LabRoom> labRooms) {
		this.labRooms = labRooms;
	}

	@JsonIgnore
	public java.util.Set<net.xidlims.domain.LabReservationAudit> getLabReservationAudits() {
		return labReservationAudits;
	}

	public void setLabReservationAudits(
			java.util.Set<net.xidlims.domain.LabReservationAudit> labReservationAudits) {
		this.labReservationAudits = labReservationAudits;
	}

	@JsonIgnore
	public java.util.Set<net.xidlims.domain.LabReservationTimeTableStudent> getLabReservationTimeTableStudents() {
		return labReservationTimeTableStudents;
	}

	public void setLabReservationTimeTableStudents(
			java.util.Set<net.xidlims.domain.LabReservationTimeTableStudent> labReservationTimeTableStudents) {
		this.labReservationTimeTableStudents = labReservationTimeTableStudents;
	}

	/**
	 * ѧ��/����
	 * 
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * ѧ��/����
	 * 
	 */
	public String getUsername() {
		return this.username;
	}

	/**
	 * ����
	 * 
	 */
	public void setCardno(String cardno) {
		this.cardno = cardno;
	}

	/**
	 * ����
	 * 
	 */
	public String getCardno() {
		return this.cardno;
	}

	/**
	 * ����
	 * 
	 */
	public void setCname(String cname) {
		this.cname = cname;
	}

	/**
	 * ����
	 * 
	 */
	public String getCname() {
		return this.cname;
	}

	/**
	 * ����
	 * 
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * ����
	 * 
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 * �Ա�
	 * 
	 */
	public void setUserSexy(String userSexy) {
		this.userSexy = userSexy;
	}

	/**
	 * �Ա�
	 * 
	 */
	public String getUserSexy() {
		return this.userSexy;
	}

	/**
	 * �Ƿ���У
	 * 
	 */
	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	/**
	 * �Ƿ���У
	 * 
	 */
	public String getUserStatus() {
		return this.userStatus;
	}

	/**
	 * ����Ա����
	 * 
	 */
	public void setTeacherNumber(Integer teacherNumber) {
		this.teacherNumber = teacherNumber;
	}

	/**
	 * ����Ա����
	 * 
	 */
	public Integer getTeacherNumber() {
		return this.teacherNumber;
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
	 * �û���ɫ��0��ѧ��1�ǽ�ʦ��
	 * 
	 */
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	/**
	 * �û���ɫ��0��ѧ��1�ǽ�ʦ��
	 * 
	 */
	public String getUserRole() {
		return this.userRole;
	}

	/**
	 * ����¼ʱ��
	 * 
	 */
	public void setLastLogin(Calendar lastLogin) {
		this.lastLogin = lastLogin;
	}

	/**
	 * ����¼ʱ��
	 * 
	 */
	public Calendar getLastLogin() {
		return this.lastLogin;
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
	 * ��ϵ�绰
	 * 
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	/**
	 * ��ϵ�绰
	 * 
	 */
	public String getTelephone() {
		return this.telephone;
	}

	/**
	 * �ʼ�
	 * 
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * �ʼ�
	 * 
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * �Ƿ����
	 * 
	 */
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	/**
	 * �Ƿ����
	 * 
	 */
	public Boolean getEnabled() {
		return this.enabled;
	}

	/**
	 * ְ��
	 * 
	 */
	public void setMajorDirection(String majorDirection) {
		this.majorDirection = majorDirection;
	}

	/**
	 * ְ��
	 * 
	 */
	public String getMajorDirection() {
		return this.majorDirection;
	}

	/**
	 * ѧ��״̬
	 * 
	 */
	public void setEnrollmentStatus(Integer enrollmentStatus) {
		this.enrollmentStatus = enrollmentStatus;
	}

	/**
	 * ѧ��״̬
	 * 
	 */
	public Integer getEnrollmentStatus() {
		return this.enrollmentStatus;
	}

	/**
	 * �Ƿ��ڼ�
	 * 
	 */
	public void setIfEnrollment(String ifEnrollment) {
		this.ifEnrollment = ifEnrollment;
	}

	/**
	 * �Ƿ��ڼ�
	 * 
	 */
	public String getIfEnrollment() {
		return this.ifEnrollment;
	}

	/**
	 * ѧ�����
	 * 
	 */
	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	/**
	 * ѧ�����
	 * 
	 */
	public Integer getUserType() {
		return this.userType;
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
	 * �����꼶
	 * 
	 */
	public void setGrade(String grade) {
		this.grade = grade;
	}

	/**
	 * �����꼶
	 * 
	 */
	public String getGrade() {
		return this.grade;
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
	public void setTimetableAttendancesForUserNumber(Set<TimetableAttendance> timetableAttendancesForUserNumber) {
		this.timetableAttendancesForUserNumber = timetableAttendancesForUserNumber;
	}

	/**
	 */
	@JsonIgnore
	public Set<TimetableAttendance> getTimetableAttendancesForUserNumber() {
		if (timetableAttendancesForUserNumber == null) {
			timetableAttendancesForUserNumber = new java.util.LinkedHashSet<net.xidlims.domain.TimetableAttendance>();
		}
		return timetableAttendancesForUserNumber;
	}

	/**
	 */
	public void setLabCentersForCenterManager(Set<LabCenter> labCentersForCenterManager) {
		this.labCentersForCenterManager = labCentersForCenterManager;
	}

	/**
	 */
	@JsonIgnore
	public Set<LabCenter> getLabCentersForCenterManager() {
		if (labCentersForCenterManager == null) {
			labCentersForCenterManager = new java.util.LinkedHashSet<net.xidlims.domain.LabCenter>();
		}
		return labCentersForCenterManager;
	}
	
	/**
	 */
	public void setLabCentersForCreatedBy(Set<LabCenter> labCentersForCreatedBy) {
		this.labCentersForCreatedBy = labCentersForCreatedBy;
	}
	
	/**
	 */
	@JsonIgnore
	public Set<LabCenter> getLabCentersForCreatedBy() {
		if (labCentersForCreatedBy == null) {
			labCentersForCreatedBy = new java.util.LinkedHashSet<net.xidlims.domain.LabCenter>();
		}
		return labCentersForCreatedBy;
	}
	
	/**
	 */
	public void setLabCentersForUpdatedBy(Set<LabCenter> labCentersForUpdatedBy) {
		this.labCentersForUpdatedBy = labCentersForUpdatedBy;
	}

	/**
	 */
	@JsonIgnore
	public Set<LabCenter> getLabCentersForUpdatedBy() {
		if (labCentersForUpdatedBy == null) {
			labCentersForUpdatedBy = new java.util.LinkedHashSet<net.xidlims.domain.LabCenter>();
		}
		return labCentersForUpdatedBy;
	}

	/**
	 */
	public void setSchoolDevicesForUserNumber(Set<SchoolDevice> schoolDevicesForUserNumber) {
		this.schoolDevicesForUserNumber = schoolDevicesForUserNumber;
	}

	/**
	 */
	@JsonIgnore
	public Set<SchoolDevice> getSchoolDevicesForUserNumber() {
		if (schoolDevicesForUserNumber == null) {
			schoolDevicesForUserNumber = new java.util.LinkedHashSet<net.xidlims.domain.SchoolDevice>();
		}
		return schoolDevicesForUserNumber;
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
	public void setTimetableGroupStudentses(Set<TimetableGroupStudents> timetableGroupStudentses) {
		this.timetableGroupStudentses = timetableGroupStudentses;
	}

	/**
	 */
	@JsonIgnore
	public Set<TimetableGroupStudents> getTimetableGroupStudentses() {
		if (timetableGroupStudentses == null) {
			timetableGroupStudentses = new java.util.LinkedHashSet<net.xidlims.domain.TimetableGroupStudents>();
		}
		return timetableGroupStudentses;
	}

	/**
	 */
	public void setSchoolDevicesForKeepUser(Set<SchoolDevice> schoolDevicesForKeepUser) {
		this.schoolDevicesForKeepUser = schoolDevicesForKeepUser;
	}

	/**
	 */
	@JsonIgnore
	public Set<SchoolDevice> getSchoolDevicesForKeepUser() {
		if (schoolDevicesForKeepUser == null) {
			schoolDevicesForKeepUser = new java.util.LinkedHashSet<net.xidlims.domain.SchoolDevice>();
		}
		return schoolDevicesForKeepUser;
	}
	
	public java.util.Set<net.xidlims.domain.OperationItem> getOperationItemsForLpCreateUser() {
		return operationItemsForLpCreateUser;
	}

	public void setOperationItemsForLpCreateUser(
			java.util.Set<net.xidlims.domain.OperationItem> operationItemsForLpCreateUser) {
		this.operationItemsForLpCreateUser = operationItemsForLpCreateUser;
	}

	/**
	 */
	public void setTimetableAttendancesForCreatedBy(Set<TimetableAttendance> timetableAttendancesForCreatedBy) {
		this.timetableAttendancesForCreatedBy = timetableAttendancesForCreatedBy;
	}

	/**
	 */
	@JsonIgnore
	public Set<TimetableAttendance> getTimetableAttendancesForCreatedBy() {
		if (timetableAttendancesForCreatedBy == null) {
			timetableAttendancesForCreatedBy = new java.util.LinkedHashSet<net.xidlims.domain.TimetableAttendance>();
		}
		return timetableAttendancesForCreatedBy;
	}

	/**
	 */
	public void setLabRoomAdmins(Set<LabRoomAdmin> labRoomAdmins) {
		this.labRoomAdmins = labRoomAdmins;
	}

	/**
	 */
	@JsonIgnore
	public Set<LabRoomAdmin> getLabRoomAdmins() {
		if (labRoomAdmins == null) {
			labRoomAdmins = new java.util.LinkedHashSet<net.xidlims.domain.LabRoomAdmin>();
		}
		return labRoomAdmins;
	}

	/**
	 */
	public void setSchoolCourseStudentsForTeacherNumber(Set<SchoolCourseStudent> schoolCourseStudentsForTeacherNumber) {
		this.schoolCourseStudentsForTeacherNumber = schoolCourseStudentsForTeacherNumber;
	}

	/**
	 */
	@JsonIgnore
	public Set<SchoolCourseStudent> getSchoolCourseStudentsForTeacherNumber() {
		if (schoolCourseStudentsForTeacherNumber == null) {
			schoolCourseStudentsForTeacherNumber = new java.util.LinkedHashSet<net.xidlims.domain.SchoolCourseStudent>();
		}
		return schoolCourseStudentsForTeacherNumber;
	}

	/**
	 */
	public void setLabRoomDevices(Set<LabRoomDevice> labRoomDevices) {
		this.labRoomDevices = labRoomDevices;
	}

	/**
	 */
	@JsonIgnore
	public Set<LabRoomDevice> getLabRoomDevices() {
		if (labRoomDevices == null) {
			labRoomDevices = new java.util.LinkedHashSet<net.xidlims.domain.LabRoomDevice>();
		}
		return labRoomDevices;
	}

	/**
	 */
	public void setSchoolCoursesForCreatedBy(Set<SchoolCourse> schoolCoursesForCreatedBy) {
		this.schoolCoursesForCreatedBy = schoolCoursesForCreatedBy;
	}

	/**
	 */
	@JsonIgnore
	public Set<SchoolCourse> getSchoolCoursesForCreatedBy() {
		if (schoolCoursesForCreatedBy == null) {
			schoolCoursesForCreatedBy = new java.util.LinkedHashSet<net.xidlims.domain.SchoolCourse>();
		}
		return schoolCoursesForCreatedBy;
	}

	/**
	 */
	public void setSchoolCoursesForUpdatedBy(Set<SchoolCourse> schoolCoursesForUpdatedBy) {
		this.schoolCoursesForUpdatedBy = schoolCoursesForUpdatedBy;
	}

	/**
	 */
	@JsonIgnore
	public Set<SchoolCourse> getSchoolCoursesForUpdatedBy() {
		if (schoolCoursesForUpdatedBy == null) {
			schoolCoursesForUpdatedBy = new java.util.LinkedHashSet<net.xidlims.domain.SchoolCourse>();
		}
		return schoolCoursesForUpdatedBy;
	}

	/**
	 */
	public void setOperationItemsForLpTeacherAssistantId(Set<OperationItem> operationItemsForLpTeacherAssistantId) {
		this.operationItemsForLpTeacherAssistantId = operationItemsForLpTeacherAssistantId;
	}
	
	public java.util.Set<net.xidlims.domain.OperationItem> getOperationItemsForLpCheckUser() {
		return operationItemsForLpCheckUser;
	}

	public void setOperationItemsForLpCheckUser(
			java.util.Set<net.xidlims.domain.OperationItem> operationItemsForLpCheckUser) {
		this.operationItemsForLpCheckUser = operationItemsForLpCheckUser;
	}

	/**
	 */
	@JsonIgnore
	public Set<OperationItem> getOperationItemsForLpTeacherAssistantId() {
		if (operationItemsForLpTeacherAssistantId == null) {
			operationItemsForLpTeacherAssistantId = new java.util.LinkedHashSet<net.xidlims.domain.OperationItem>();
		}
		return operationItemsForLpTeacherAssistantId;
	}

	/**
	 */
	public void setOperationItemsForLpTeacherSpeakerId(Set<OperationItem> operationItemsForLpTeacherSpeakerId) {
		this.operationItemsForLpTeacherSpeakerId = operationItemsForLpTeacherSpeakerId;
	}

	/**
	 */
	@JsonIgnore
	public Set<OperationItem> getOperationItemsForLpTeacherSpeakerId() {
		if (operationItemsForLpTeacherSpeakerId == null) {
			operationItemsForLpTeacherSpeakerId = new java.util.LinkedHashSet<net.xidlims.domain.OperationItem>();
		}
		return operationItemsForLpTeacherSpeakerId;
	}

	/**
	 */
	public void setSchoolCoursesForTeacher(Set<SchoolCourse> schoolCoursesForTeacher) {
		this.schoolCoursesForTeacher = schoolCoursesForTeacher;
	}

	/**
	 */
	@JsonIgnore
	public Set<SchoolCourse> getSchoolCoursesForTeacher() {
		if (schoolCoursesForTeacher == null) {
			schoolCoursesForTeacher = new java.util.LinkedHashSet<net.xidlims.domain.SchoolCourse>();
		}
		return schoolCoursesForTeacher;
	}

	/**
	 */
	public void setUserCards(Set<UserCard> userCards) {
		this.userCards = userCards;
	}

	/**
	 */
	@JsonIgnore
	public Set<UserCard> getUserCards() {
		if (userCards == null) {
			userCards = new java.util.LinkedHashSet<net.xidlims.domain.UserCard>();
		}
		return userCards;
	}

	/**
	 */
	public void setSchoolCourseStudentsForStudentNumber(Set<SchoolCourseStudent> schoolCourseStudentsForStudentNumber) {
		this.schoolCourseStudentsForStudentNumber = schoolCourseStudentsForStudentNumber;
	}

	/**
	 */
	@JsonIgnore
	public Set<SchoolCourseStudent> getSchoolCourseStudentsForStudentNumber() {
		if (schoolCourseStudentsForStudentNumber == null) {
			schoolCourseStudentsForStudentNumber = new java.util.LinkedHashSet<net.xidlims.domain.SchoolCourseStudent>();
		}
		return schoolCourseStudentsForStudentNumber;
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
	public void setAuthorities(Set<Authority> authorities) {
		this.authorities = authorities;
	}

	/**
	 */
	@JsonIgnore
	public Set<Authority> getAuthorities() {
		if (authorities == null) {
			authorities = new java.util.LinkedHashSet<net.xidlims.domain.Authority>();
		}
		return authorities;
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
	
	public SchoolClasses getSchoolClasses() {
		return schoolClasses;
	}

	public void setSchoolClasses(SchoolClasses schoolClasses) {
		this.schoolClasses = schoolClasses;
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
	
	//新增
	/**
	 */
	public void setLabConstructionProjectAudits(Set<LabConstructionProjectAudit> labConstructionProjectAudits) {
		this.labConstructionProjectAudits = labConstructionProjectAudits;
	}

	/**
	 */
	@JsonIgnore
	public Set<LabConstructionProjectAudit> getLabConstructionProjectAudits() {
		if (labConstructionProjectAudits == null) {
			labConstructionProjectAudits = new java.util.LinkedHashSet<net.xidlims.domain.LabConstructionProjectAudit>();
		}
		return labConstructionProjectAudits;
	}

	/**
	 */
	public void setLabConstructionPurchaseAudits(Set<LabConstructionPurchaseAudit> labConstructionPurchaseAudits) {
		this.labConstructionPurchaseAudits = labConstructionPurchaseAudits;
	}

	/**
	 */
	@JsonIgnore
	public Set<LabConstructionPurchaseAudit> getLabConstructionPurchaseAudits() {
		if (labConstructionPurchaseAudits == null) {
			labConstructionPurchaseAudits = new java.util.LinkedHashSet<net.xidlims.domain.LabConstructionPurchaseAudit>();
		}
		return labConstructionPurchaseAudits;
	}

	/**
	 *//*
	public void setLabConstructionProjects(Set<LabConstructionProject> labConstructionProjects) {
		this.labConstructionProjects = labConstructionProjects;
	}

	*//**
	 *//*
	@JsonIgnore
	public Set<LabConstructionProject> getLabConstructionProjects() {
		if (labConstructionProjects == null) {
			labConstructionProjects = new java.util.LinkedHashSet<net.xidlims.domain.LabConstructionProject>();
		}
		return labConstructionProjects;
	}*/

	/**
	 */
	public void setMLabConstructionProjectUsers(Set<MLabConstructionProjectUser> MLabConstructionProjectUsers) {
		this.MLabConstructionProjectUsers = MLabConstructionProjectUsers;
	}

	/**
	 */
	@JsonIgnore
	public Set<MLabConstructionProjectUser> getMLabConstructionProjectUsers() {
		if (MLabConstructionProjectUsers == null) {
			MLabConstructionProjectUsers = new java.util.LinkedHashSet<net.xidlims.domain.MLabConstructionProjectUser>();
		}
		return MLabConstructionProjectUsers;
	}
	/**
	 */
	public void setLabConstructionPurchasesForKeeper(Set<LabConstructionPurchase> labConstructionPurchasesForKeeper) {
		this.labConstructionPurchasesForKeeper = labConstructionPurchasesForKeeper;
	}

	/**
	 */
	@JsonIgnore
	public Set<LabConstructionPurchase> getLabConstructionPurchasesForKeeper() {
		if (labConstructionPurchasesForKeeper == null) {
			labConstructionPurchasesForKeeper = new java.util.LinkedHashSet<net.xidlims.domain.LabConstructionPurchase>();
		}
		return labConstructionPurchasesForKeeper;
	}

	/**
	 */
	public void setLabConstructionProjects_1(Set<LabConstructionProject> labConstructionProjects_1) {
		this.labConstructionProjects_1 = labConstructionProjects_1;
	}

	/**
	 */
	@JsonIgnore
	public Set<LabConstructionProject> getLabConstructionProjects_1() {
		if (labConstructionProjects_1 == null) {
			labConstructionProjects_1 = new java.util.LinkedHashSet<net.xidlims.domain.LabConstructionProject>();
		}
		return labConstructionProjects_1;
	}

	/**
	 */
	public void setLabConstructionFundingAudits(Set<LabConstructionFundingAudit> labConstructionFundingAudits) {
		this.labConstructionFundingAudits = labConstructionFundingAudits;
	}

	/**
	 */
	@JsonIgnore
	public Set<LabConstructionFundingAudit> getLabConstructionFundingAudits() {
		if (labConstructionFundingAudits == null) {
			labConstructionFundingAudits = new java.util.LinkedHashSet<net.xidlims.domain.LabConstructionFundingAudit>();
		}
		return labConstructionFundingAudits;
	}

	/**
	 */
	public void setLabConstructionPurchasesForApplicant(Set<LabConstructionPurchase> labConstructionPurchasesForApplicant) {
		this.labConstructionPurchasesForApplicant = labConstructionPurchasesForApplicant;
	}

	/**
	 */
	@JsonIgnore
	public Set<LabConstructionPurchase> getLabConstructionPurchasesForApplicant() {
		if (labConstructionPurchasesForApplicant == null) {
			labConstructionPurchasesForApplicant = new java.util.LinkedHashSet<net.xidlims.domain.LabConstructionPurchase>();
		}
		return labConstructionPurchasesForApplicant;
	}

	
	
	@JsonIgnore
	public java.util.Set<net.xidlims.domain.CommonDocument> getCommonDocument() {
		return commonDocument;
	}

	public void setCommonDocument(
			java.util.Set<net.xidlims.domain.CommonDocument> commonDocument) {
		this.commonDocument = commonDocument;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public java.util.Set<net.xidlims.domain.LabRoomDeviceTraining> getLabRoomDeviceTrainings() {
		return labRoomDeviceTrainings;
	}

	public void setLabRoomDeviceTrainings(
			java.util.Set<net.xidlims.domain.LabRoomDeviceTraining> labRoomDeviceTrainings) {
		this.labRoomDeviceTrainings = labRoomDeviceTrainings;
	}

	public java.util.Set<net.xidlims.domain.LabRoomDevicePermitUsers> getLabRoomDevicePermitUserses() {
		return labRoomDevicePermitUserses;
	}

	public void setLabRoomDevicePermitUserses(
			java.util.Set<net.xidlims.domain.LabRoomDevicePermitUsers> labRoomDevicePermitUserses) {
		this.labRoomDevicePermitUserses = labRoomDevicePermitUserses;
	}

	public java.util.Set<net.xidlims.domain.LabRoomDeviceTrainingPeople> getLabRoomDeviceTrainingPeoples() {
		return labRoomDeviceTrainingPeoples;
	}

	public void setLabRoomDeviceTrainingPeoples(
			java.util.Set<net.xidlims.domain.LabRoomDeviceTrainingPeople> labRoomDeviceTrainingPeoples) {
		this.labRoomDeviceTrainingPeoples = labRoomDeviceTrainingPeoples;
	}

	public java.util.Set<net.xidlims.domain.LabConstructionProject> getLabConstructionProjects() {
		return labConstructionProjects;
	}

	public void setLabConstructionProjects(
			java.util.Set<net.xidlims.domain.LabConstructionProject> labConstructionProjects) {
		this.labConstructionProjects = labConstructionProjects;
	}

	public java.util.Set<net.xidlims.domain.LabRoomDeviceReservation> getLabRoomDeviceReservationsForReserveUser() {
		return labRoomDeviceReservationsForReserveUser;
	}

	public void setLabRoomDeviceReservationsForReserveUser(
			java.util.Set<net.xidlims.domain.LabRoomDeviceReservation> labRoomDeviceReservationsForReserveUser) {
		this.labRoomDeviceReservationsForReserveUser = labRoomDeviceReservationsForReserveUser;
	}

	public java.util.Set<net.xidlims.domain.LabRoomDeviceReservation> getLabRoomDeviceReservationsForTeacher() {
		return labRoomDeviceReservationsForTeacher;
	}

	public void setLabRoomDeviceReservationsForTeacher(
			java.util.Set<net.xidlims.domain.LabRoomDeviceReservation> labRoomDeviceReservationsForTeacher) {
		this.labRoomDeviceReservationsForTeacher = labRoomDeviceReservationsForTeacher;
	}


	public java.util.Set<net.xidlims.domain.SystemBuild> getSystemBuildsForCreatedBy() {
		return systemBuildsForCreatedBy;
	}

	public void setSystemBuildsForCreatedBy(
			java.util.Set<net.xidlims.domain.SystemBuild> systemBuildsForCreatedBy) {
		this.systemBuildsForCreatedBy = systemBuildsForCreatedBy;
	}

	public java.util.Set<net.xidlims.domain.SystemBuild> getSystemBuildsForUpdatedBy() {
		return systemBuildsForUpdatedBy;
	}

	public void setSystemBuildsForUpdatedBy(
			java.util.Set<net.xidlims.domain.SystemBuild> systemBuildsForUpdatedBy) {
		this.systemBuildsForUpdatedBy = systemBuildsForUpdatedBy;
	}

	public java.util.Set<net.xidlims.domain.LabRoomDeviceReservationResult> getLabRoomDeviceReservationResults() {
		return labRoomDeviceReservationResults;
	}

	public void setLabRoomDeviceReservationResults(
			java.util.Set<net.xidlims.domain.LabRoomDeviceReservationResult> labRoomDeviceReservationResults) {
		this.labRoomDeviceReservationResults = labRoomDeviceReservationResults;
	}

	public java.util.Set<net.xidlims.domain.LabReservation> getLabReservations_1() {
		return labReservations_1;
	}

	public void setLabReservations_1(
			java.util.Set<net.xidlims.domain.LabReservation> labReservations_1) {
		this.labReservations_1 = labReservations_1;
	}

	public java.util.Set<net.xidlims.domain.WkFolder> getFolders() {
		return folders;
	}

	public void setFolders(java.util.Set<net.xidlims.domain.WkFolder> folders) {
		this.folders = folders;
	}

	public java.util.Set<net.xidlims.domain.TCourseSiteUser> getTCourseSiteUsers() {
		return TCourseSiteUsers;
	}

	public void setTCourseSiteUsers(
			java.util.Set<net.xidlims.domain.TCourseSiteUser> tCourseSiteUsers) {
		TCourseSiteUsers = tCourseSiteUsers;
	}

	public java.util.Set<net.xidlims.domain.TAssignmentGrading> getTAssignmentGradingsForGradeBy() {
		return TAssignmentGradingsForGradeBy;
	}

	public void setTAssignmentGradingsForGradeBy(
			java.util.Set<net.xidlims.domain.TAssignmentGrading> tAssignmentGradingsForGradeBy) {
		TAssignmentGradingsForGradeBy = tAssignmentGradingsForGradeBy;
	}

	public java.util.Set<net.xidlims.domain.TAssignmentItem> getTAssignmentItems() {
		return TAssignmentItems;
	}

	public void setTAssignmentItems(
			java.util.Set<net.xidlims.domain.TAssignmentItem> tAssignmentItems) {
		TAssignmentItems = tAssignmentItems;
	}

	public java.util.Set<net.xidlims.domain.TCourseSite> getTCourseSitesForCreatedBy() {
		return TCourseSitesForCreatedBy;
	}

	public void setTCourseSitesForCreatedBy(
			java.util.Set<net.xidlims.domain.TCourseSite> tCourseSitesForCreatedBy) {
		TCourseSitesForCreatedBy = tCourseSitesForCreatedBy;
	}

	public java.util.Set<net.xidlims.domain.TAssignmentGrading> getTAssignmentGradingsForStudent() {
		return TAssignmentGradingsForStudent;
	}

	public void setTAssignmentGradingsForStudent(
			java.util.Set<net.xidlims.domain.TAssignmentGrading> tAssignmentGradingsForStudent) {
		TAssignmentGradingsForStudent = tAssignmentGradingsForStudent;
	}

	public java.util.Set<net.xidlims.domain.TCourseSite> getTCourseSitesForModifiedBy() {
		return TCourseSitesForModifiedBy;
	}

	public void setTCourseSitesForModifiedBy(
			java.util.Set<net.xidlims.domain.TCourseSite> tCourseSitesForModifiedBy) {
		TCourseSitesForModifiedBy = tCourseSitesForModifiedBy;
	}

	public java.util.Set<net.xidlims.domain.TAssignmentQuestionpool> getTAssignmentQuestionpools() {
		return TAssignmentQuestionpools;
	}

	public void setTAssignmentQuestionpools(
			java.util.Set<net.xidlims.domain.TAssignmentQuestionpool> tAssignmentQuestionpools) {
		TAssignmentQuestionpools = tAssignmentQuestionpools;
	}

	public java.util.Set<net.xidlims.domain.TAssignment> getTAssignments() {
		return TAssignments;
	}

	public void setTAssignments(
			java.util.Set<net.xidlims.domain.TAssignment> tAssignments) {
		TAssignments = tAssignments;
	}

	public java.util.Set<net.xidlims.domain.TAssignmentQuestionpool> getTAssignmentQuestionpools_1() {
		return TAssignmentQuestionpools_1;
	}

	public void setTAssignmentQuestionpools_1(
			java.util.Set<net.xidlims.domain.TAssignmentQuestionpool> tAssignmentQuestionpools_1) {
		TAssignmentQuestionpools_1 = tAssignmentQuestionpools_1;
	}

	public java.util.Set<net.xidlims.domain.TAssignmentSection> getTAssignmentSections() {
		return TAssignmentSections;
	}

	public void setTAssignmentSections(
			java.util.Set<net.xidlims.domain.TAssignmentSection> tAssignmentSections) {
		TAssignmentSections = tAssignmentSections;
	}

	public java.util.Set<net.xidlims.domain.TGradeRecord> getTGradeRecords() {
		return TGradeRecords;
	}

	public void setTGradeRecords(
			java.util.Set<net.xidlims.domain.TGradeRecord> tGradeRecords) {
		TGradeRecords = tGradeRecords;
	}

	public java.util.Set<net.xidlims.domain.TCourseSiteChannel> getTCourseSiteChannels() {
		return TCourseSiteChannels;
	}

	public void setTCourseSiteChannels(
			java.util.Set<net.xidlims.domain.TCourseSiteChannel> tCourseSiteChannels) {
		TCourseSiteChannels = tCourseSiteChannels;
	}

	public java.util.Set<net.xidlims.domain.TCourseSiteArtical> getTCourseSiteArticals() {
		return TCourseSiteArticals;
	}

	public void setTCourseSiteArticals(
			java.util.Set<net.xidlims.domain.TCourseSiteArtical> tCourseSiteArticals) {
		TCourseSiteArticals = tCourseSiteArticals;
	}

	public java.util.Set<net.xidlims.domain.TAssignmentAnswerAssign> getTAssignmentAnswerAssigns() {
		return TAssignmentAnswerAssigns;
	}

	public void setTAssignmentAnswerAssigns(
			java.util.Set<net.xidlims.domain.TAssignmentAnswerAssign> tAssignmentAnswerAssigns) {
		TAssignmentAnswerAssigns = tAssignmentAnswerAssigns;
	}

	public java.util.Set<net.xidlims.domain.TAssignmentItemMapping> getTAssignmentItemMappingsForStudent() {
		return TAssignmentItemMappingsForStudent;
	}

	public void setTAssignmentItemMappingsForStudent(
			java.util.Set<net.xidlims.domain.TAssignmentItemMapping> tAssignmentItemMappingsForStudent) {
		TAssignmentItemMappingsForStudent = tAssignmentItemMappingsForStudent;
	}

	public java.util.Set<net.xidlims.domain.TAssignmentItemMapping> getTAssignmentItemMappingsForGradeby() {
		return TAssignmentItemMappingsForGradeby;
	}

	public void setTAssignmentItemMappingsForGradeby(
			java.util.Set<net.xidlims.domain.TAssignmentItemMapping> tAssignmentItemMappingsForGradeby) {
		TAssignmentItemMappingsForGradeby = tAssignmentItemMappingsForGradeby;
	}

	public java.util.Set<net.xidlims.domain.TMessage> getSendMessages() {
		return sendMessages;
	}

	public void setSendMessages(
			java.util.Set<net.xidlims.domain.TMessage> sendMessages) {
		this.sendMessages = sendMessages;
	}

	public java.util.Set<net.xidlims.domain.TExerciseItemRecord> getTExerciseItemRecords() {
		return TExerciseItemRecords;
	}

	public void setTExerciseItemRecords(
			java.util.Set<net.xidlims.domain.TExerciseItemRecord> tExerciseItemRecords) {
		TExerciseItemRecords = tExerciseItemRecords;
	}

	public java.util.Set<net.xidlims.domain.TExerciseInfo> getTExerciseInfos() {
		return TExerciseInfos;
	}

	public void setTExerciseInfos(
			java.util.Set<net.xidlims.domain.TExerciseInfo> tExerciseInfos) {
		TExerciseInfos = tExerciseInfos;
	}

	public java.util.Set<net.xidlims.domain.TMistakeItem> getTMistakeItems() {
		return TMistakeItems;
	}

	public void setTMistakeItems(
			java.util.Set<net.xidlims.domain.TMistakeItem> tMistakeItems) {
		TMistakeItems = tMistakeItems;
	}

	/**
	 */
	public void setCmsChannels(Set<CmsChannel> cmsChannels) {
		this.cmsChannels = cmsChannels;
	}

	/**
	 */
	@JsonIgnore
	public Set<CmsChannel> getCmsChannels() {
		if (cmsChannels == null) {
			cmsChannels = new java.util.LinkedHashSet<net.xidlims.domain.CmsChannel>();
		}
		return cmsChannels;
	}

	/**
	 */
	public void setCmsArticles(Set<CmsArticle> cmsArticles) {
		this.cmsArticles = cmsArticles;
	}

	/**
	 */
	@JsonIgnore
	public Set<CmsArticle> getCmsArticles() {
		if (cmsArticles == null) {
			cmsArticles = new java.util.LinkedHashSet<net.xidlims.domain.CmsArticle>();
		}
		return cmsArticles;
	}

	/**
	 */
	public void setCmsSites(Set<CmsSite> cmsSites) {
		this.cmsSites = cmsSites;
	}

	/**
	 */
	@JsonIgnore
	public Set<CmsSite> getCmsSites() {
		if (cmsSites == null) {
			cmsSites = new java.util.LinkedHashSet<net.xidlims.domain.CmsSite>();
		}
		return cmsSites;
	}
	/**
	 */
	public void setLabRoomDeviceRepairs(Set<LabRoomDeviceRepair> labRoomDeviceRepairs) {
		this.labRoomDeviceRepairs = labRoomDeviceRepairs;
	}

	/**
	 */
	@JsonIgnore
	public Set<LabRoomDeviceRepair> getLabRoomDeviceRepairs() {
		if (labRoomDeviceRepairs == null) {
			labRoomDeviceRepairs = new java.util.LinkedHashSet<net.xidlims.domain.LabRoomDeviceRepair>();
		}
		return labRoomDeviceRepairs;
	}
	/**
	 */
	public void setLabWorkers(Set<LabWorker> labWorkers) {
		this.labWorkers = labWorkers;
	}

	/**
	 */
	@JsonIgnore
	public Set<LabWorker> getLabWorkers() {
		if (labWorkers == null) {
			labWorkers = new java.util.LinkedHashSet<net.xidlims.domain.LabWorker>();
		}
		return labWorkers;
	}
	/**
	 */
	public void setNDeviceAuditRecords(Set<NDeviceAuditRecord> NDeviceAuditRecords) {
		this.NDeviceAuditRecords = NDeviceAuditRecords;
	}

	/**
	 */
	@JsonIgnore
	public Set<NDeviceAuditRecord> getNDeviceAuditRecords() {
		if (NDeviceAuditRecords == null) {
			NDeviceAuditRecords = new java.util.LinkedHashSet<net.xidlims.domain.NDeviceAuditRecord>();
		}
		return NDeviceAuditRecords;
	}

	/**
	 */
	public void setNDevicePurchaseDetails(Set<NDevicePurchaseDetail> NDevicePurchaseDetails) {
		this.NDevicePurchaseDetails = NDevicePurchaseDetails;
	}

	/**
	 */
	@JsonIgnore
	public Set<NDevicePurchaseDetail> getNDevicePurchaseDetails() {
		if (NDevicePurchaseDetails == null) {
			NDevicePurchaseDetails = new java.util.LinkedHashSet<net.xidlims.domain.NDevicePurchaseDetail>();
		}
		return NDevicePurchaseDetails;
	}

	/**
	 */
	public void setNDevicePurchases(Set<NDevicePurchase> NDevicePurchases) {
		this.NDevicePurchases = NDevicePurchases;
	}

	/**
	 */
	@JsonIgnore
	public Set<NDevicePurchase> getNDevicePurchases() {
		if (NDevicePurchases == null) {
			NDevicePurchases = new java.util.LinkedHashSet<net.xidlims.domain.NDevicePurchase>();
		}
		return NDevicePurchases;
	}
	/**
	 */
	public void setSchoolCourseDetails2(Set<SchoolCourseDetail> schoolCourseDetails2) {
		this.schoolCourseDetails2 = schoolCourseDetails2;
	}
 	
	/**
	 */
	@JsonIgnore
	public Set<SchoolCourseDetail> getSchoolCourseDetails2() {
		if(schoolCourseDetails2 == null){
			schoolCourseDetails2 = new java.util.LinkedHashSet<net.xidlims.domain.SchoolCourseDetail>();
		}
		return schoolCourseDetails2;
	}
	
	/**
	 */
	public void setAppGroups(Set<AppGroup> appGroups) {
		this.appGroups = appGroups;
	}

	/**
	 */
	@JsonIgnore
	public Set<AppGroup> getAppGroups() {
		if (appGroups == null) {
			appGroups = new java.util.LinkedHashSet<net.xidlims.domain.AppGroup>();
		}
		return appGroups;
	}

	public Set<AppPostlist> getCollectPosts() {
		if(collectPosts == null){
			collectPosts = new java.util.LinkedHashSet<AppPostlist>();
		}
		return collectPosts;
	}

	public void setCollectPosts(Set<AppPostlist> collectPosts) {
		this.collectPosts = collectPosts;
	}

	public Set<AppPostlist> getLikedPosts() {
		if(likedPosts == null){
			likedPosts = new java.util.LinkedHashSet<AppPostlist>();
		}
		return likedPosts;
	}

	public void setLikedPosts(Set<AppPostlist> likedPosts) {
		this.likedPosts = likedPosts;
	}

	/**
	 */
	public void setAppPostImageses(Set<AppPostImages> appPostImageses) {
		this.appPostImageses = appPostImageses;
	}

	/**
	 */
	@JsonIgnore
	public Set<AppPostImages> getAppPostImageses() {
		if (appPostImageses == null) {
			appPostImageses = new java.util.LinkedHashSet<net.xidlims.domain.AppPostImages>();
		}
		return appPostImageses;
	}

	/**
	 */
	public void setAppPostReplies(Set<AppPostReply> appPostReplies) {
		this.appPostReplies = appPostReplies;
	}

	/**
	 */
	@JsonIgnore
	public Set<AppPostReply> getAppPostReplies() {
		if (appPostReplies == null) {
			appPostReplies = new java.util.LinkedHashSet<net.xidlims.domain.AppPostReply>();
		}
		return appPostReplies;
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
	public void setAppPostStatuses(Set<AppPostStatus> appPostStatuses) {
		this.appPostStatuses = appPostStatuses;
	}

	/**
	 */
	@JsonIgnore
	public Set<AppPostStatus> getAppPostStatuses() {
		if (appPostStatuses == null) {
			appPostStatuses = new java.util.LinkedHashSet<net.xidlims.domain.AppPostStatus>();
		}
		return appPostStatuses;
	}

	public Integer getOnline() {
		return online;
	}

	public void setOnline(Integer online) {
		this.online = online;
	}

	public String getPhoneIP() {
		return phoneIP;
	}

	

	public void setPhoneIP(String phoneIP) {
		this.phoneIP = phoneIP;
	}
	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}
	/**
	 */
	public User() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(User that) {
		setUsername(that.getUsername());
		setCardno(that.getCardno());
		setCname(that.getCname());
		setPassword(that.getPassword());
		setUserSexy(that.getUserSexy());
		setUserStatus(that.getUserStatus());
		setTeacherNumber(that.getTeacherNumber());
		setMajorNumber(that.getMajorNumber());
		setUserRole(that.getUserRole());
		setLastLogin(that.getLastLogin());
		setCreatedAt(that.getCreatedAt());
		setUpdatedAt(that.getUpdatedAt());
		setTelephone(that.getTelephone());
		setEmail(that.getEmail());
		setEnabled(that.getEnabled());
		setMajorDirection(that.getMajorDirection());
		setEnrollmentStatus(that.getEnrollmentStatus());
		setIfEnrollment(that.getIfEnrollment());
		setUserType(that.getUserType());
		setAttendanceTime(that.getAttendanceTime());
		setGrade(that.getGrade());
		setSchoolAcademy(that.getSchoolAcademy());
		setTimetableAttendancesForUserNumber(new java.util.LinkedHashSet<net.xidlims.domain.TimetableAttendance>(that.getTimetableAttendancesForUserNumber()));
		setLabCentersForCenterManager(new java.util.LinkedHashSet<net.xidlims.domain.LabCenter>(that.getLabCentersForCenterManager()));
		setLabCentersForCreatedBy(new java.util.LinkedHashSet<net.xidlims.domain.LabCenter>(that.getLabCentersForCreatedBy()));
		setLabCentersForUpdatedBy(new java.util.LinkedHashSet<net.xidlims.domain.LabCenter>(that.getLabCentersForUpdatedBy()));
		setSchoolDevicesForUserNumber(new java.util.LinkedHashSet<net.xidlims.domain.SchoolDevice>(that.getSchoolDevicesForUserNumber()));
		setTimetableTutorRelateds(new java.util.LinkedHashSet<net.xidlims.domain.TimetableTutorRelated>(that.getTimetableTutorRelateds()));
		setTimetableGroupStudentses(new java.util.LinkedHashSet<net.xidlims.domain.TimetableGroupStudents>(that.getTimetableGroupStudentses()));
		setSchoolDevicesForKeepUser(new java.util.LinkedHashSet<net.xidlims.domain.SchoolDevice>(that.getSchoolDevicesForKeepUser()));
		setTimetableAttendancesForCreatedBy(new java.util.LinkedHashSet<net.xidlims.domain.TimetableAttendance>(that.getTimetableAttendancesForCreatedBy()));
		setLabRoomAdmins(new java.util.LinkedHashSet<net.xidlims.domain.LabRoomAdmin>(that.getLabRoomAdmins()));
		setSchoolCourseStudentsForTeacherNumber(new java.util.LinkedHashSet<net.xidlims.domain.SchoolCourseStudent>(that.getSchoolCourseStudentsForTeacherNumber()));
		setLabRoomDevices(new java.util.LinkedHashSet<net.xidlims.domain.LabRoomDevice>(that.getLabRoomDevices()));
		setSchoolCoursesForCreatedBy(new java.util.LinkedHashSet<net.xidlims.domain.SchoolCourse>(that.getSchoolCoursesForCreatedBy()));
		setSchoolCoursesForUpdatedBy(new java.util.LinkedHashSet<net.xidlims.domain.SchoolCourse>(that.getSchoolCoursesForUpdatedBy()));
		setOperationItemsForLpTeacherAssistantId(new java.util.LinkedHashSet<net.xidlims.domain.OperationItem>(that.getOperationItemsForLpTeacherAssistantId()));
		setOperationItemsForLpTeacherSpeakerId(new java.util.LinkedHashSet<net.xidlims.domain.OperationItem>(that.getOperationItemsForLpTeacherSpeakerId()));
		setSchoolCoursesForTeacher(new java.util.LinkedHashSet<net.xidlims.domain.SchoolCourse>(that.getSchoolCoursesForTeacher()));
		setUserCards(new java.util.LinkedHashSet<net.xidlims.domain.UserCard>(that.getUserCards()));
		setSchoolCourseStudentsForStudentNumber(new java.util.LinkedHashSet<net.xidlims.domain.SchoolCourseStudent>(that.getSchoolCourseStudentsForStudentNumber()));
		setTimetableTeacherRelateds(new java.util.LinkedHashSet<net.xidlims.domain.TimetableTeacherRelated>(that.getTimetableTeacherRelateds()));
		setAuthorities(new java.util.LinkedHashSet<net.xidlims.domain.Authority>(that.getAuthorities()));
		setTimetableSelfCourses(new java.util.LinkedHashSet<net.xidlims.domain.TimetableSelfCourse>(that.getTimetableSelfCourses()));
		setTimetableCourseStudents(new java.util.LinkedHashSet<net.xidlims.domain.TimetableCourseStudent>(that.getTimetableCourseStudents()));
		setSchoolCourseDetails(new java.util.LinkedHashSet<net.xidlims.domain.SchoolCourseDetail>(that.getSchoolCourseDetails()));
		//新增
		setLabConstructionProjectAudits(new java.util.LinkedHashSet<net.xidlims.domain.LabConstructionProjectAudit>(that.getLabConstructionProjectAudits()));
		setLabConstructionPurchaseAudits(new java.util.LinkedHashSet<net.xidlims.domain.LabConstructionPurchaseAudit>(that.getLabConstructionPurchaseAudits()));
//		setLabConstructionProjects(new java.util.LinkedHashSet<net.xidlims.domain.LabConstructionProject>(that.getLabConstructionProjects()));
		setMLabConstructionProjectUsers(new java.util.LinkedHashSet<net.xidlims.domain.MLabConstructionProjectUser>(that.getMLabConstructionProjectUsers()));
		setLabConstructionPurchasesForKeeper(new java.util.LinkedHashSet<net.xidlims.domain.LabConstructionPurchase>(that.getLabConstructionPurchasesForKeeper()));
		setLabConstructionProjects_1(new java.util.LinkedHashSet<net.xidlims.domain.LabConstructionProject>(that.getLabConstructionProjects_1()));
		setLabConstructionFundingAudits(new java.util.LinkedHashSet<net.xidlims.domain.LabConstructionFundingAudit>(that.getLabConstructionFundingAudits()));
		setLabConstructionPurchasesForApplicant(new java.util.LinkedHashSet<net.xidlims.domain.LabConstructionPurchase>(that.getLabConstructionPurchasesForApplicant()));
		setCommonDocument(that.getCommonDocument());
		setOperationOutlines(new java.util.LinkedHashSet<net.xidlims.domain.OperationOutline>(that.getOperationOutlines()));
		
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("username=[").append(username).append("] ");
		buffer.append("cardno=[").append(cardno).append("] ");
		buffer.append("cname=[").append(cname).append("] ");
		buffer.append("password=[").append(password).append("] ");
		buffer.append("userSexy=[").append(userSexy).append("] ");
		buffer.append("userStatus=[").append(userStatus).append("] ");
		buffer.append("teacherNumber=[").append(teacherNumber).append("] ");
		buffer.append("majorNumber=[").append(majorNumber).append("] ");
		buffer.append("userRole=[").append(userRole).append("] ");
		buffer.append("lastLogin=[").append(lastLogin).append("] ");
		buffer.append("createdAt=[").append(createdAt).append("] ");
		buffer.append("updatedAt=[").append(updatedAt).append("] ");
		buffer.append("telephone=[").append(telephone).append("] ");
		buffer.append("email=[").append(email).append("] ");
		buffer.append("enabled=[").append(enabled).append("] ");
		buffer.append("majorDirection=[").append(majorDirection).append("] ");
		buffer.append("enrollmentStatus=[").append(enrollmentStatus).append("] ");
		buffer.append("ifEnrollment=[").append(ifEnrollment).append("] ");
		buffer.append("userType=[").append(userType).append("] ");
		buffer.append("attendanceTime=[").append(attendanceTime).append("] ");
		buffer.append("grade=[").append(grade).append("] ");

		return buffer.toString();
	}

	/**
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((username == null) ? 0 : username.hashCode()));
		return result;
	}

	/**
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof User))
			return false;
		User equalCheck = (User) obj;
		if ((username == null && equalCheck.username != null) || (username != null && equalCheck.username == null))
			return false;
		if (username != null && !username.equals(equalCheck.username))
			return false;
		return true;
	}
}
