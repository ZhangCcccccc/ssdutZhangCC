package net.xidlims.domain;

import java.io.Serializable;

import java.lang.StringBuilder;

import java.util.Calendar;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import javax.xml.bind.annotation.*;

import javax.persistence.*;

import net.xidlims.domain.WkChapter;

import org.codehaus.jackson.annotate.JsonIgnore;

/**
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "findAllTCourseSites", query = "select myTCourseSite from TCourseSite myTCourseSite"),
		@NamedQuery(name = "findTCourseSiteByCreatedTime", query = "select myTCourseSite from TCourseSite myTCourseSite where myTCourseSite.createdTime = ?1"),
		@NamedQuery(name = "findTCourseSiteByDescription", query = "select myTCourseSite from TCourseSite myTCourseSite where myTCourseSite.description = ?1"),
		@NamedQuery(name = "findTCourseSiteById", query = "select myTCourseSite from TCourseSite myTCourseSite where myTCourseSite.id = ?1"),
		@NamedQuery(name = "findTCourseSiteByModifiedTime", query = "select myTCourseSite from TCourseSite myTCourseSite where myTCourseSite.modifiedTime = ?1"),
		@NamedQuery(name = "findTCourseSiteByPrimaryKey", query = "select myTCourseSite from TCourseSite myTCourseSite where myTCourseSite.id = ?1"),
		@NamedQuery(name = "findTCourseSiteBySiteCode", query = "select myTCourseSite from TCourseSite myTCourseSite where myTCourseSite.siteCode = ?1"),
		@NamedQuery(name = "findTCourseSiteBySiteCodeContaining", query = "select myTCourseSite from TCourseSite myTCourseSite where myTCourseSite.siteCode like ?1"),
		@NamedQuery(name = "findTCourseSiteByTitle", query = "select myTCourseSite from TCourseSite myTCourseSite where myTCourseSite.title = ?1"),
		@NamedQuery(name = "findTCourseSiteByTitleContaining", query = "select myTCourseSite from TCourseSite myTCourseSite where myTCourseSite.title like ?1"),
		@NamedQuery(name = "findTCourseSiteByType", query = "select myTCourseSite from TCourseSite myTCourseSite where myTCourseSite.type = ?1"),
		@NamedQuery(name = "findTCourseSiteByTypeContaining", query = "select myTCourseSite from TCourseSite myTCourseSite where myTCourseSite.type like ?1") })
@Table(catalog = "xidlims", name = "t_course_site")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "TCourseSite")
@XmlRootElement(namespace = "xidlims/net/xidlims/domain")
public class TCourseSite implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * �γ�վ������
	 * 
	 */

	@Column(name = "id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	@XmlElement
	Integer id;
	/**
	 * �γ�վ�����
	 * 
	 */

	@Column(name = "title", length = 99)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String title;
	/**
	 * 是否开放
	 * 
	 */

	@Column(name = "isOpen")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer isOpen;
	/**
	 * 站点图片
	 * 
	 */

	@Column(name = "siteImage")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String siteImage;
	/**
	 * 教师图片
	 * 
	 */

	@Column(name = "teacherImage")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String teacherImage;
	/**
	 * �γ����ͣ�course���γ�
	 * 
	 */

	@Column(name = "type", length = 99)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String type;
	/**
	 * �γ�վ������
	 * 
	 */

	@Column(name = "description", columnDefinition = "LONGTEXT")
	@Basic(fetch = FetchType.EAGER)
	@Lob
	@XmlElement
	String description;
	/**
	 * �γ�վ����
	 * 
	 */

	@Column(name = "site_code")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String siteCode;
	
	/**
	 * 是否被删除，0表示被删除，1正常使用
	 * 
	 */

	@Column(name = "status")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer status;
	/**
	 * ������schoolacademy��academy_number
	 * 
	 */

	
	/**
	 * ����ʱ��
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_time")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar createdTime;
	/**
	 * �޸�ʱ��
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modified_time")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar modifiedTime;

	/**
	 */
	@Column(name = "major_number")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String majorNumber;
	
	                                                                                                                                                   
	/**
	 */
	@Column(name = "experiment_skill_profile")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String experimentSkillProfile;
	
	public String getExperimentSkillProfile() {
		return experimentSkillProfile;
	}

	public void setExperimentSkillProfile(String experimentSkillProfile) {
		this.experimentSkillProfile = experimentSkillProfile;
	}

	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "term_id", referencedColumnName = "id") })
	@XmlTransient
	SchoolTerm schoolTerm;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "academy_number", referencedColumnName = "academy_number") })
	@XmlTransient
	SchoolAcademy schoolAcademy;

	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "created_by", referencedColumnName = "username") })
	@XmlTransient
	User userByCreatedBy;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "course_number", referencedColumnName = "course_number") })
	@XmlTransient
	SchoolCourseInfo schoolCourseInfo;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "modified_by", referencedColumnName = "username") })
	@XmlTransient
	User userByModifiedBy;
	/**
	 */
	@OneToMany(mappedBy = "TCourseSite", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.TCourseSitePage> TCourseSitePages;
	/**
	 */
	@OneToMany(mappedBy = "TCourseSite", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.TCourseSiteGroup> TCourseSiteGroups;
	/**
	 */
	@OneToMany(mappedBy = "TCourseSite", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.TCourseSiteUser> TCourseSiteUsers;
	/**
	 */
	@OneToMany(mappedBy = "TCourseSite", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.TCourseSiteChannel> TCourseSiteChannels;
	
	
	
	/**
	 */
	@OneToMany(mappedBy = "TCourseSite", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.TMessage> TMessages;

	/**
	 */
	@ManyToMany(mappedBy = "TCourseSites", fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.TAssignmentQuestionpool> TAssignmentQuestionpools;
	
	/**
	 */
	@OneToMany(mappedBy = "TCourseSite", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.TExerciseItemRecord> TExerciseItemRecords;
	/**
	 */
	@OneToMany(mappedBy = "TCourseSite", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.TExerciseInfo> TExerciseInfos;
	
	/**
	 */
	@OneToMany(mappedBy = "TCourseSite", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.TMistakeItem> TMistakeItems;
	
	/**
	 */
	@OneToMany(mappedBy = "TCourseSite", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	@OrderBy("seq ASC")
	java.util.Set<net.xidlims.domain.WkChapter> wkChapters;
	
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "course_no", referencedColumnName = "course_no") })
	@XmlTransient
	SchoolCourse schoolCourse;
	
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "timetable_self_course_id", referencedColumnName = "id") })
	@XmlTransient
	TimetableSelfCourse timetableSelfCourse;
	
	/**
	 */
	@OneToMany(mappedBy = "TCourseSite", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.TDiscuss> tDiscusses;
	
	/**
	 */
	@OneToMany(mappedBy = "TCourseSite", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.TCourseSiteSchedule> TCourseSiteSchedules;
	
	public java.util.Set<net.xidlims.domain.TCourseSiteSchedule> getTCourseSiteSchedules() {
		return TCourseSiteSchedules;
	}

	public void setTCourseSiteSchedules(
			java.util.Set<net.xidlims.domain.TCourseSiteSchedule> tCourseSiteSchedules) {
		TCourseSiteSchedules = tCourseSiteSchedules;
	}

	public java.util.Set<net.xidlims.domain.TDiscuss> getTDiscusses() {
		return tDiscusses;
	}

	public void setTDiscusses(
			java.util.Set<net.xidlims.domain.TDiscuss> tDiscusses) {
		this.tDiscusses = tDiscusses;
	}

	/**
	 * �γ�վ������
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * �γ�վ������
	 * 
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * �γ�վ�����
	 * 
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * �γ�վ�����
	 * 
	 */
	public String getTitle() {
		return this.title;
	}

	
	
	public Integer getIsOpen() {
		return isOpen;
	}

	public void setIsOpen(Integer isOpen) {
		this.isOpen = isOpen;
	}

	public String getSiteImage() {
		return siteImage;
	}

	public void setSiteImage(String siteImage) {
		this.siteImage = siteImage;
	}

	public String getTeacherImage() {
		return teacherImage;
	}

	public void setTeacherImage(String teacherImage) {
		this.teacherImage = teacherImage;
	}

	/**
	 * �γ����ͣ�course���γ�
	 * 
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * �γ����ͣ�course���γ�
	 * 
	 */
	public String getType() {
		return this.type;
	}

	/**
	 * �γ�վ������
	 * 
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * �γ�վ������
	 * 
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * �γ�վ����
	 * 
	 */
	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}

	/**
	 * �γ�վ����
	 * 
	 */
	public String getSiteCode() {
		return this.siteCode;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * ����ʱ��
	 * 
	 */
	public void setCreatedTime(Calendar createdTime) {
		this.createdTime = createdTime;
	}

	/**
	 * ����ʱ��
	 * 
	 */
	public Calendar getCreatedTime() {
		return this.createdTime;
	}

	/**
	 * �޸�ʱ��
	 * 
	 */
	public void setModifiedTime(Calendar modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	/**
	 * �޸�ʱ��
	 * 
	 */
	public Calendar getModifiedTime() {
		return this.modifiedTime;
	}

	public String getMajorNumber() {
		return majorNumber;
	}

	public void setMajorNumber(String majorNumber) {
		this.majorNumber = majorNumber;
	}

	/**
	 */
	public void setSchoolTerm(SchoolTerm schoolTerm) {
		this.schoolTerm = schoolTerm;
	}

	/**
	 */
	public SchoolAcademy getSchoolAcademy() {
		return schoolAcademy;
	}

	public void setSchoolAcademy(SchoolAcademy schoolAcademy) {
		this.schoolAcademy = schoolAcademy;
	}

	/**
	 */
	public SchoolTerm getSchoolTerm() {
		return schoolTerm;
	}

	/**
	 */
	public void setUserByCreatedBy(User userByCreatedBy) {
		this.userByCreatedBy = userByCreatedBy;
	}

	/**
	 */
	public User getUserByCreatedBy() {
		return userByCreatedBy;
	}

	/**
	 */
	public void setSchoolCourseInfo(SchoolCourseInfo schoolCourseInfo) {
		this.schoolCourseInfo = schoolCourseInfo;
	}

	/**
	 */
	public SchoolCourseInfo getSchoolCourseInfo() {
		return schoolCourseInfo;
	}

	/**
	 */
	public void setUserByModifiedBy(User userByModifiedBy) {
		this.userByModifiedBy = userByModifiedBy;
	}

	/**
	 */
	public User getUserByModifiedBy() {
		return userByModifiedBy;
	}

	/**
	 */
	public void setTCourseSitePages(Set<TCourseSitePage> TCourseSitePages) {
		this.TCourseSitePages = TCourseSitePages;
	}

	/**
	 */
	public Set<TCourseSitePage> getTCourseSitePages() {
		if (TCourseSitePages == null) {
			TCourseSitePages = new java.util.LinkedHashSet<net.xidlims.domain.TCourseSitePage>();
		}
		return TCourseSitePages;
	}

	/**
	 */
	public void setTCourseSiteGroups(Set<TCourseSiteGroup> TCourseSiteGroups) {
		this.TCourseSiteGroups = TCourseSiteGroups;
	}

	/**
	 */
	public Set<TCourseSiteGroup> getTCourseSiteGroups() {
		if (TCourseSiteGroups == null) {
			TCourseSiteGroups = new java.util.LinkedHashSet<net.xidlims.domain.TCourseSiteGroup>();
		}
		return TCourseSiteGroups;
	}

	/**
	 */
	public void setTCourseSiteUsers(Set<TCourseSiteUser> TCourseSiteUsers) {
		this.TCourseSiteUsers = TCourseSiteUsers;
	}

	/**
	 */
	public Set<TCourseSiteUser> getTCourseSiteUsers() {
		if (TCourseSiteUsers == null) {
			TCourseSiteUsers = new java.util.LinkedHashSet<net.xidlims.domain.TCourseSiteUser>();
		}
		return TCourseSiteUsers;
	}
	
	

	public java.util.Set<net.xidlims.domain.TCourseSiteChannel> getTCourseSiteChannels() {
		return TCourseSiteChannels;
	}

	public void setTCourseSiteChannels(
			java.util.Set<net.xidlims.domain.TCourseSiteChannel> tCourseSiteChannels) {
		TCourseSiteChannels = tCourseSiteChannels;
	}

	
	
	public java.util.Set<net.xidlims.domain.TMessage> getTMessages() {
		return TMessages;
	}

	public void setTMessages(java.util.Set<net.xidlims.domain.TMessage> tMessages) {
		TMessages = tMessages;
	}
	
	/**
	 */
	public void setTAssignmentQuestionpools(Set<TAssignmentQuestionpool> TAssignmentQuestionpools) {
		this.TAssignmentQuestionpools = TAssignmentQuestionpools;
	}

	/**
	 */
	@JsonIgnore
	public Set<TAssignmentQuestionpool> getTAssignmentQuestionpools() {
		if (TAssignmentQuestionpools == null) {
			TAssignmentQuestionpools = new java.util.LinkedHashSet<net.xidlims.domain.TAssignmentQuestionpool>();
		}
		return TAssignmentQuestionpools;
	}
	
	/**
	 */
	public void setTExerciseItemRecords(Set<TExerciseItemRecord> TExerciseItemRecords) {
		this.TExerciseItemRecords = TExerciseItemRecords;
	}

	/**
	 */
	@JsonIgnore
	public Set<TExerciseItemRecord> getTExerciseItemRecords() {
		if (TExerciseItemRecords == null) {
			TExerciseItemRecords = new java.util.LinkedHashSet<net.xidlims.domain.TExerciseItemRecord>();
		}
		return TExerciseItemRecords;
	}

	/**
	 */
	public void setTExerciseInfos(Set<TExerciseInfo> TExerciseInfos) {
		this.TExerciseInfos = TExerciseInfos;
	}

	/**
	 */
	@JsonIgnore
	public Set<TExerciseInfo> getTExerciseInfos() {
		if (TExerciseInfos == null) {
			TExerciseInfos = new java.util.LinkedHashSet<net.xidlims.domain.TExerciseInfo>();
		}
		return TExerciseInfos;
	}

	/**
	 */
	public void setTMistakeItems(Set<TMistakeItem> TMistakeItems) {
		this.TMistakeItems = TMistakeItems;
	}

	/**
	 */
	@JsonIgnore
	public Set<TMistakeItem> getTMistakeItems() {
		if (TMistakeItems == null) {
			TMistakeItems = new java.util.LinkedHashSet<net.xidlims.domain.TMistakeItem>();
		}
		return TMistakeItems;
	}

	/**
	 */
	public void setWkChapters(Set<WkChapter> wkChapters) {
		this.wkChapters = wkChapters;
	}

	/**
	 */
	public Set<WkChapter> getWkChapters() {
		if (wkChapters == null) {
			wkChapters = new java.util.LinkedHashSet<net.xidlims.domain.WkChapter>();
		}
		return wkChapters;
	}
	
	public SchoolCourse getSchoolCourse() {
		return schoolCourse;
	}

	public void setSchoolCourse(SchoolCourse schoolCourse) {
		this.schoolCourse = schoolCourse;
	}

	public TimetableSelfCourse getTimetableSelfCourse() {
		return timetableSelfCourse;
	}

	public void setTimetableSelfCourse(TimetableSelfCourse timetableSelfCourse) {
		this.timetableSelfCourse = timetableSelfCourse;
	}

	/**
	 */
	public TCourseSite() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(TCourseSite that) {
		setId(that.getId());
		setTitle(that.getTitle());
		setType(that.getType());
		setDescription(that.getDescription());
		setSiteCode(that.getSiteCode());

		setCreatedTime(that.getCreatedTime());
		setModifiedTime(that.getModifiedTime());
		setMajorNumber(that.getMajorNumber());
		setSchoolTerm(that.getSchoolTerm());
		setUserByCreatedBy(that.getUserByCreatedBy());
		setSchoolCourseInfo(that.getSchoolCourseInfo());
		setUserByModifiedBy(that.getUserByModifiedBy());
		setTCourseSitePages(new java.util.LinkedHashSet<net.xidlims.domain.TCourseSitePage>(that.getTCourseSitePages()));
		setTCourseSiteGroups(new java.util.LinkedHashSet<net.xidlims.domain.TCourseSiteGroup>(that.getTCourseSiteGroups()));
		setTCourseSiteUsers(new java.util.LinkedHashSet<net.xidlims.domain.TCourseSiteUser>(that.getTCourseSiteUsers()));
		setTCourseSiteChannels(new java.util.LinkedHashSet<net.xidlims.domain.TCourseSiteChannel>(that.getTCourseSiteChannels()));
		setTMessages(new java.util.LinkedHashSet<net.xidlims.domain.TMessage>(that.getTMessages()));
		setIsOpen(that.getIsOpen());
		setSiteImage(that.getSiteImage());
		setTeacherImage(that.getTeacherImage());
		
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("id=[").append(id).append("] ");
		buffer.append("title=[").append(title).append("] ");
		buffer.append("type=[").append(type).append("] ");
		buffer.append("description=[").append(description).append("] ");
		buffer.append("siteCode=[").append(siteCode).append("] ");
		buffer.append("createdTime=[").append(createdTime).append("] ");
		buffer.append("modifiedTime=[").append(modifiedTime).append("] ");
		buffer.append("isOpen=[").append(isOpen).append("] ");
		buffer.append("siteImage=[").append(siteImage).append("] ");
		buffer.append("teacherImage=[").append(teacherImage).append("] ");
		
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
		if (!(obj instanceof TCourseSite))
			return false;
		TCourseSite equalCheck = (TCourseSite) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
