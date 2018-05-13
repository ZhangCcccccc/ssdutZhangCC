package net.xidlims.domain;

import java.io.Serializable;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import org.codehaus.jackson.annotate.JsonIgnore;

/**
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "findAllOperationOutlines", query = "select myOperationOutline from OperationOutline myOperationOutline"),
		@NamedQuery(name = "findOperationOutlineByAssResultsPerEvaluation", query = "select myOperationOutline from OperationOutline myOperationOutline where myOperationOutline.assResultsPerEvaluation = ?1"),
		@NamedQuery(name = "findOperationOutlineByBasicContentCourse", query = "select myOperationOutline from OperationOutline myOperationOutline where myOperationOutline.basicContentCourse = ?1"),
		@NamedQuery(name = "findOperationOutlineByBasicRequirementsCourse", query = "select myOperationOutline from OperationOutline myOperationOutline where myOperationOutline.basicRequirementsCourse = ?1"),
		@NamedQuery(name = "findOperationOutlineByCourseDescription", query = "select myOperationOutline from OperationOutline myOperationOutline where myOperationOutline.courseDescription = ?1"),
		@NamedQuery(name = "findOperationOutlineByCoursesAdvice", query = "select myOperationOutline from OperationOutline myOperationOutline where myOperationOutline.coursesAdvice = ?1"),
		@NamedQuery(name = "findOperationOutlineByEnName", query = "select myOperationOutline from OperationOutline myOperationOutline where myOperationOutline.enName = ?1"),
		@NamedQuery(name = "findOperationOutlineByEnNameContaining", query = "select myOperationOutline from OperationOutline myOperationOutline where myOperationOutline.enName like ?1"),
		@NamedQuery(name = "findOperationOutlineById", query = "select myOperationOutline from OperationOutline myOperationOutline where myOperationOutline.id = ?1"),
		@NamedQuery(name = "findOperationOutlineByLabOutlineName", query = "select myOperationOutline from OperationOutline myOperationOutline where myOperationOutline.labOutlineName = ?1"),
		@NamedQuery(name = "findOperationOutlineByLabOutlineNameContaining", query = "select myOperationOutline from OperationOutline myOperationOutline where myOperationOutline.labOutlineName like ?1"),
		@NamedQuery(name = "findOperationOutlineByOutlineCourseTeachingTarget", query = "select myOperationOutline from OperationOutline myOperationOutline where myOperationOutline.outlineCourseTeachingTarget = ?1"),
		@NamedQuery(name = "findOperationOutlineByOutlineCourseTeachingTargetOver", query = "select myOperationOutline from OperationOutline myOperationOutline where myOperationOutline.outlineCourseTeachingTargetOver = ?1"),
		@NamedQuery(name = "findOperationOutlineByPrimaryKey", query = "select myOperationOutline from OperationOutline myOperationOutline where myOperationOutline.id = ?1"),
		@NamedQuery(name = "findOperationOutlineByProfessional", query = "select myOperationOutline from OperationOutline myOperationOutline where myOperationOutline.professional = ?1"),
		@NamedQuery(name = "findOperationOutlineByProfessionalContaining", query = "select myOperationOutline from OperationOutline myOperationOutline where myOperationOutline.professional like ?1"),
		@NamedQuery(name = "findOperationOutlineByUseMaterials", query = "select myOperationOutline from OperationOutline myOperationOutline where myOperationOutline.useMaterials = ?1"),
		@NamedQuery(name = "findOperationOutlineByUseMaterialsContaining", query = "select myOperationOutline from OperationOutline myOperationOutline where myOperationOutline.useMaterials like ?1") })
@Table(catalog = "xidlims", name = "operation_outline")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "OperationOutline")
public class OperationOutline implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ʵ�������д�ٱ�
	 * 
	 */

	@Column(name = "id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@XmlElement
	Integer id;
	/**
	 * ʵ���Ҵ�����
	 * 
	 */

	@Column(name = "lab_outline_name")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String labOutlineName;
	/**
	 * Ӣ�����
	 * 
	 */

	@Column(name = "en_name")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String enName;
	/**
	 * ����רҵ
	 * 
	 */

	@Column(name = "professional")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String professional;
	/**
	 * ʹ�ý̲�
	 * 
	 */

	@Column(name = "use_materials")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String useMaterials;
	/**
	 * �γ̼��
	 * 
	 */

	@Column(name = "course_description", columnDefinition = "TEXT")
	@Basic(fetch = FetchType.EAGER)
	@Lob
	@XmlElement
	String courseDescription;
	/**
	 * ѡ�ν���
	 * 
	 */

	@Column(name = "courses_advice", columnDefinition = "TEXT")
	@Basic(fetch = FetchType.EAGER)
	@Lob
	@XmlElement
	String coursesAdvice;
	/**
	 * `�γ�����ͽ�ѧĿ��
	 * 
	 */

	@Column(name = "outline_course_teaching_target", columnDefinition = "TEXT")
	@Basic(fetch = FetchType.EAGER)
	@Lob
	@XmlElement
	String outlineCourseTeachingTarget;
	/**
	 * �γ̻�����
	 * 
	 */

	@Column(name = "basic_content_course", columnDefinition = "TEXT")
	@Basic(fetch = FetchType.EAGER)
	@Lob
	@XmlElement
	String basicContentCourse;
	/**
	 * �γ̻�Ҫ��
	 * 
	 */

	@Column(name = "basic_requirements_course", columnDefinition = "TEXT")
	@Basic(fetch = FetchType.EAGER)
	@Lob
	@XmlElement
	String basicRequirementsCourse;
	/**
	 * ���˳ɼ����ɼ�����
	 * 
	 */

	@Column(name = "ass_results_per_evaluation", columnDefinition = "TEXT")
	@Basic(fetch = FetchType.EAGER)
	@Lob
	@XmlElement
	String assResultsPerEvaluation;
	/**
	 * �γ�����ͽ�ѧĿ��
	 * 
	 */

	@Column(name = "outline_course_teaching_target_over", columnDefinition = "TEXT")
	@Basic(fetch = FetchType.EAGER)
	@Lob
	@XmlElement
	String outlineCourseTeachingTargetOver;
	
	/**
	 * ʵ�������д�ٱ�
	 * 
	 */

	@Column(name = "period")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Double period;
	
	
	/**
	 * ʵ�������д�ٱ�
	 * 
	 */

	@Column(name = "experiment_period")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Double experimentPeriod;
	
	public Double getExperimentPeriod() {
		return experimentPeriod;
	}

	public void setExperimentPeriod(Double experimentPeriod) {
		this.experimentPeriod = experimentPeriod;
	}

	/**
	 * ʵ�������д�ٱ�
	 * 
	 */

	@Column(name = "status")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer status;

	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "audit_user", referencedColumnName = "username") })
	@XmlTransient
	User userByAuditUser;
	
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "permitted_teacher", referencedColumnName = "username") })
	@XmlTransient
	User userByPermittedTeacher;

	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "first_courses", referencedColumnName = "course_number") })
	@XmlTransient
	SchoolCourseInfo schoolCourseInfoByFirstCourses;
	/**
	 */
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "class_id", referencedColumnName = "course_number") })
	@XmlTransient
	SchoolCourseInfo schoolCourseInfoByClassId;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "credit", referencedColumnName = "id") })
	@XmlTransient
	COperationOutlineCredit COperationOutlineCredit;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "term_id", referencedColumnName = "id") })
	@XmlTransient
	SchoolTerm schoolTerm;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "start_school", referencedColumnName = "academy_number") })
	@XmlTransient
	SchoolAcademy schoolAcademy;
	
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "createder", referencedColumnName = "username") })
	@XmlTransient
	User user;
	/**
	 */
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "follow_up_courses", referencedColumnName = "course_number") })
	@XmlTransient
	SchoolCourseInfo schoolCourseInfoByFollowUpCourses;
	/**
	 */
	@OneToMany(mappedBy = "operationOutline", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.CommonDocument> commonDocuments;
	/**
	 */
	@OneToMany(mappedBy = "operationOutline", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.OperationItem> operationItems;
	/**
	 */
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(catalog = "xidlims", name = "operation_outline_system_major_12", joinColumns = { @JoinColumn(name = "operation_outline_id", referencedColumnName = "id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "system_major_12", referencedColumnName = "m_number", nullable = false, updatable = false) })
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.SystemMajor12> systemMajors;
	/**
	 */
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(catalog = "xidlims", name = "operation_outline_ccourse_property", joinColumns = { @JoinColumn(name = "operation_outline_id", referencedColumnName = "id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "c_outline_property_id", referencedColumnName = "id", nullable = false, updatable = false) })
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.CDictionary> CDictionarys;
	
	/**
	 */
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(catalog = "xidlims", name = "operatoutline_academy", joinColumns = { @JoinColumn(name = "outline_id", referencedColumnName = "id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "academy_id", referencedColumnName = "academy_number", nullable = false, updatable = false) })
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.SchoolAcademy> schoolAcademies;
	
	/**
	 */
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(catalog = "xidlims", name = "operation_outline_firstcourse", joinColumns = { @JoinColumn(name = "outline_id", referencedColumnName = "id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "course_id", referencedColumnName = "course_number", nullable = false, updatable = false) })
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.SchoolCourseInfo> schoolCourseInfoes;
	
	/**
	 */
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(catalog = "xidlims", name = "operation_outline_student_status", joinColumns = { @JoinColumn(name = "outline_id", referencedColumnName = "id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "status_id", referencedColumnName = "id", nullable = false, updatable = false) })
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.CDictionary> studyStages;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "lab_type", referencedColumnName = "id") })
	@XmlTransient
	LabCenter labCenter;

	/**
	 * ʵ�������д�ٱ�
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * ʵ�������д�ٱ�
	 * 
	 */
	public Integer getId() {
		return this.id;
	}
	/**
	 */
	public void setSystemMajors(Set<SystemMajor12> systemMajors) {
		this.systemMajors = systemMajors;
	}

	/**
	 */
	@JsonIgnore
	public Set<SystemMajor12> getSystemMajors() {
		if (systemMajors == null) {
			systemMajors = new java.util.LinkedHashSet<net.xidlims.domain.SystemMajor12>();
		}
		return systemMajors;
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
	public java.util.Set<net.xidlims.domain.CommonDocument> getCommonDocuments() {
		if (commonDocuments == null) {
			commonDocuments = new java.util.LinkedHashSet<net.xidlims.domain.CommonDocument>();
		}
		return commonDocuments;
	}

	public void setCommonDocuments(java.util.Set<net.xidlims.domain.CommonDocument> commonDocuments) {
		this.commonDocuments = commonDocuments;
	}
	

	/**
	 * ʵ���Ҵ�����
	 * 
	 */
	public void setLabOutlineName(String labOutlineName) {
		this.labOutlineName = labOutlineName;
	}

	/**
	 * ʵ���Ҵ�����
	 * 
	 */
	public String getLabOutlineName() {
		return this.labOutlineName;
	}

	/**
	 * Ӣ�����
	 * 
	 */
	public void setEnName(String enName) {
		this.enName = enName;
	}

	/**
	 * Ӣ�����
	 * 
	 */
	public String getEnName() {
		return this.enName;
	}

	/**
	 * ����רҵ
	 * 
	 */
	public void setProfessional(String professional) {
		this.professional = professional;
	}

	/**
	 * ����רҵ
	 * 
	 */
	public String getProfessional() {
		return this.professional;
	}

	/**
	 * ʹ�ý̲�
	 * 
	 */
	public void setUseMaterials(String useMaterials) {
		this.useMaterials = useMaterials;
	}

	/**
	 * ʹ�ý̲�
	 * 
	 */
	public String getUseMaterials() {
		return this.useMaterials;
	}

	/**
	 * �γ̼��
	 * 
	 */
	public void setCourseDescription(String courseDescription) {
		this.courseDescription = courseDescription;
	}

	/**
	 * �γ̼��
	 * 
	 */
	public String getCourseDescription() {
		return this.courseDescription;
	}

	/**
	 * ѡ�ν���
	 * 
	 */
	public void setCoursesAdvice(String coursesAdvice) {
		this.coursesAdvice = coursesAdvice;
	}

	/**
	 * ѡ�ν���
	 * 
	 */
	public String getCoursesAdvice() {
		return this.coursesAdvice;
	}

	/**
	 * `�γ�����ͽ�ѧĿ��
	 * 
	 */
	public void setOutlineCourseTeachingTarget(String outlineCourseTeachingTarget) {
		this.outlineCourseTeachingTarget = outlineCourseTeachingTarget;
	}

	/**
	 * `�γ�����ͽ�ѧĿ��
	 * 
	 */
	public String getOutlineCourseTeachingTarget() {
		return this.outlineCourseTeachingTarget;
	}

	/**
	 * �γ̻�����
	 * 
	 */
	public void setBasicContentCourse(String basicContentCourse) {
		this.basicContentCourse = basicContentCourse;
	}

	/**
	 * �γ̻�����
	 * 
	 */
	public String getBasicContentCourse() {
		return this.basicContentCourse;
	}

	/**
	 * �γ̻�Ҫ��
	 * 
	 */
	public void setBasicRequirementsCourse(String basicRequirementsCourse) {
		this.basicRequirementsCourse = basicRequirementsCourse;
	}

	/**
	 * �γ̻�Ҫ��
	 * 
	 */
	public String getBasicRequirementsCourse() {
		return this.basicRequirementsCourse;
	}

	/**
	 * ���˳ɼ����ɼ�����
	 * 
	 */
	public void setAssResultsPerEvaluation(String assResultsPerEvaluation) {
		this.assResultsPerEvaluation = assResultsPerEvaluation;
	}

	/**
	 * ���˳ɼ����ɼ�����
	 * 
	 */
	public String getAssResultsPerEvaluation() {
		return this.assResultsPerEvaluation;
	}

	/**
	 * �γ�����ͽ�ѧĿ��
	 * 
	 */
	public void setOutlineCourseTeachingTargetOver(String outlineCourseTeachingTargetOver) {
		this.outlineCourseTeachingTargetOver = outlineCourseTeachingTargetOver;
	}

	/**
	 * �γ�����ͽ�ѧĿ��
	 * 
	 */
	public String getOutlineCourseTeachingTargetOver() {
		return this.outlineCourseTeachingTargetOver;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 */
	public void schoolCourseInfoByFirstCourses(SchoolCourseInfo schoolCourseInfoByFirstCourses) {
		this.schoolCourseInfoByFirstCourses = schoolCourseInfoByFirstCourses;
	}
	public void setSchoolCourseInfoByFirstCourses(SchoolCourseInfo schoolCourseInfoByFirstCourses) {
		this.schoolCourseInfoByFirstCourses = schoolCourseInfoByFirstCourses;
	}

	/**
	 */
	@JsonIgnore
	public SchoolCourseInfo getSchoolCourseInfoByFirstCourses() {
		return schoolCourseInfoByFirstCourses;
	}

	/**
	 */
	public void setSchoolCourseInfoByClassId(SchoolCourseInfo schoolCourseInfoByClassId) {
		this.schoolCourseInfoByClassId = schoolCourseInfoByClassId;
	}
	
	/**
	 */
	@JsonIgnore
	public User getUserByAuditUser() {
		return userByAuditUser;
	}
	
	/**
	 */
	public void setUserByAuditUser(User userByAuditUser) {
		this.userByAuditUser = userByAuditUser;
	}

	
	/**
	 */
	@JsonIgnore
	public User getUserByPermittedTeacher() {
		return userByPermittedTeacher;
	}
	
	/**
	 */
	public void setUserByPermittedTeacher(User userByPermittedTeacher) {
		this.userByPermittedTeacher = userByPermittedTeacher;
	}

	/**
	 */
	@JsonIgnore
	public SchoolCourseInfo getSchoolCourseInfoByClassId() {
		return schoolCourseInfoByClassId;
	}

	/**
	 */
	public void setCOperationOutlineCredit(COperationOutlineCredit COperationOutlineCredit) {
		this.COperationOutlineCredit = COperationOutlineCredit;
	}

	/**
	 */
	@JsonIgnore
	public COperationOutlineCredit getCOperationOutlineCredit() {
		return COperationOutlineCredit;
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
	public void setSchoolAcademies(Set<SchoolAcademy> schoolAcademies) {
		this.schoolAcademies = schoolAcademies;
	}

	/**
	 */
	@JsonIgnore
	public Set<SchoolAcademy> getSchoolAcademies() {
		if (schoolAcademies == null) {
			schoolAcademies = new java.util.LinkedHashSet<net.xidlims.domain.SchoolAcademy>();
		}
		return schoolAcademies;
	}
	
	/**
	 */
	@JsonIgnore
	public Set<SchoolCourseInfo> getSchoolCourseInfoes() {
		if (schoolCourseInfoes == null) {
			schoolCourseInfoes = new java.util.LinkedHashSet<net.xidlims.domain.SchoolCourseInfo>();
		}
		return schoolCourseInfoes;
	}
	
	public void setSchoolCourseInfoes(Set<SchoolCourseInfo> schoolCourseInfoes) {
		this.schoolCourseInfoes = schoolCourseInfoes;
	}
	
	/**
	 */
	
	
	@JsonIgnore
	public Set<CDictionary> getStudyStages() {
		if(studyStages == null){
			studyStages = new java.util.LinkedHashSet<net.xidlims.domain.CDictionary>();
		}
		return studyStages;
	}
	
	public void setStudyStages(Set<CDictionary> studyStages) {
		this.studyStages = studyStages;
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
	public void setSchoolCourseInfoByFollowUpCourses(SchoolCourseInfo schoolCourseInfoByFollowUpCourses) {
		this.schoolCourseInfoByFollowUpCourses = schoolCourseInfoByFollowUpCourses;
	}

	/**
	 */
	@JsonIgnore
	public SchoolCourseInfo getSchoolCourseInfoByFollowUpCourses() {
		return schoolCourseInfoByFollowUpCourses;
	}

	/**
	 */
	/**
	 */
	public void setCDictionarys(Set<CDictionary> CDictionarys) {
		this.CDictionarys = CDictionarys;
	}

	/**
	 */
	@JsonIgnore
	public Set<CDictionary> getCDictionarys() {
		if (CDictionarys == null) {
			CDictionarys = new java.util.LinkedHashSet<net.xidlims.domain.CDictionary>();
		}
		return CDictionarys;
	}
	
	public void setLabCenter(LabCenter labCenter) {
		this.labCenter = labCenter;
	}

	/**
	 */
	@JsonIgnore
	public LabCenter getLabCenter() {
		return labCenter;
	}
	
	public Double getPeriod() {
		return period;
	}

	public void setPeriod(Double period) {
		this.period = period;
	}

	public OperationOutline() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(OperationOutline that) {
		setId(that.getId());
		setLabOutlineName(that.getLabOutlineName());
		setEnName(that.getEnName());
		setProfessional(that.getProfessional());
		setUseMaterials(that.getUseMaterials());
		setCourseDescription(that.getCourseDescription());
		setCoursesAdvice(that.getCoursesAdvice());
		setOutlineCourseTeachingTarget(that.getOutlineCourseTeachingTarget());
		setBasicContentCourse(that.getBasicContentCourse());
		setBasicRequirementsCourse(that.getBasicRequirementsCourse());
		setAssResultsPerEvaluation(that.getAssResultsPerEvaluation());
		setOutlineCourseTeachingTargetOver(that.getOutlineCourseTeachingTargetOver());
		setCOperationOutlineCredit(that.getCOperationOutlineCredit());
		//setSchoolAcademy(that.getSchoolAcademy());
		setUser(that.getUser());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("id=[").append(id).append("] ");
		buffer.append("labOutlineName=[").append(labOutlineName).append("] ");
		buffer.append("enName=[").append(enName).append("] ");
		buffer.append("professional=[").append(professional).append("] ");
		buffer.append("useMaterials=[").append(useMaterials).append("] ");
		buffer.append("courseDescription=[").append(courseDescription).append("] ");
		buffer.append("coursesAdvice=[").append(coursesAdvice).append("] ");
		buffer.append("outlineCourseTeachingTarget=[").append(outlineCourseTeachingTarget).append("] ");
		buffer.append("basicContentCourse=[").append(basicContentCourse).append("] ");
		buffer.append("basicRequirementsCourse=[").append(basicRequirementsCourse).append("] ");
		buffer.append("assResultsPerEvaluation=[").append(assResultsPerEvaluation).append("] ");
		buffer.append("outlineCourseTeachingTargetOver=[").append(outlineCourseTeachingTargetOver).append("] ");

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
		if (!(obj instanceof OperationOutline))
			return false;
		OperationOutline equalCheck = (OperationOutline) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
