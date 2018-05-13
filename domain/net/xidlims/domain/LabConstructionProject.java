package net.xidlims.domain;

import java.io.Serializable;

import java.lang.StringBuilder;

import java.math.BigDecimal;

import java.util.Calendar;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
		@NamedQuery(name = "findAllLabConstructionProjects", query = "select myLabConstructionProject from LabConstructionProject myLabConstructionProject"),
		@NamedQuery(name = "findLabConstructionProjectByAuditResults", query = "select myLabConstructionProject from LabConstructionProject myLabConstructionProject where myLabConstructionProject.auditResults = ?1"),
		@NamedQuery(name = "findLabConstructionProjectByCreatedAt", query = "select myLabConstructionProject from LabConstructionProject myLabConstructionProject where myLabConstructionProject.createdAt = ?1"),
		@NamedQuery(name = "findLabConstructionProjectByEmail", query = "select myLabConstructionProject from LabConstructionProject myLabConstructionProject where myLabConstructionProject.email = ?1"),
		@NamedQuery(name = "findLabConstructionProjectByEmailContaining", query = "select myLabConstructionProject from LabConstructionProject myLabConstructionProject where myLabConstructionProject.email like ?1"),
		@NamedQuery(name = "findLabConstructionProjectById", query = "select myLabConstructionProject from LabConstructionProject myLabConstructionProject where myLabConstructionProject.id = ?1"),
		@NamedQuery(name = "findLabConstructionProjectByPrimaryKey", query = "select myLabConstructionProject from LabConstructionProject myLabConstructionProject where myLabConstructionProject.id = ?1"),
		@NamedQuery(name = "findLabConstructionProjectByProjectAnalysis", query = "select myLabConstructionProject from LabConstructionProject myLabConstructionProject where myLabConstructionProject.projectAnalysis = ?1"),
		@NamedQuery(name = "findLabConstructionProjectByProjectBudget", query = "select myLabConstructionProject from LabConstructionProject myLabConstructionProject where myLabConstructionProject.projectBudget = ?1"),
		@NamedQuery(name = "findLabConstructionProjectByProjectName", query = "select myLabConstructionProject from LabConstructionProject myLabConstructionProject where myLabConstructionProject.projectName = ?1"),
		@NamedQuery(name = "findLabConstructionProjectByProjectNameContaining", query = "select myLabConstructionProject from LabConstructionProject myLabConstructionProject where myLabConstructionProject.projectName like ?1"),
		@NamedQuery(name = "findLabConstructionProjectByProjectNumber", query = "select myLabConstructionProject from LabConstructionProject myLabConstructionProject where myLabConstructionProject.projectNumber = ?1"),
		@NamedQuery(name = "findLabConstructionProjectByProjectNumberContaining", query = "select myLabConstructionProject from LabConstructionProject myLabConstructionProject where myLabConstructionProject.projectNumber like ?1"),
		@NamedQuery(name = "findLabConstructionProjectByProjectScheme", query = "select myLabConstructionProject from LabConstructionProject myLabConstructionProject where myLabConstructionProject.projectScheme = ?1"),
		@NamedQuery(name = "findLabConstructionProjectByStage", query = "select myLabConstructionProject from LabConstructionProject myLabConstructionProject where myLabConstructionProject.stage = ?1"),
		@NamedQuery(name = "findLabConstructionProjectByTelephone", query = "select myLabConstructionProject from LabConstructionProject myLabConstructionProject where myLabConstructionProject.telephone = ?1"),
		@NamedQuery(name = "findLabConstructionProjectByTelephoneContaining", query = "select myLabConstructionProject from LabConstructionProject myLabConstructionProject where myLabConstructionProject.telephone like ?1") })
@Table(catalog = "xidlims", name = "lab_construction_project")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "LabConstructionProject")
@XmlRootElement(namespace = "xidlims/net/xidlims/domain")
public class LabConstructionProject implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ????????-????????
	 * 
	 */

	@Column(name = "id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@XmlElement
	Integer id;
	/**
	 * ??????
	 * 
	 */

	@Column(name = "project_name", length = 40)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String projectName;
	/**
	 * ??????
	 * 
	 */

	@Column(name = "project_number", length = 40)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String projectNumber;
	/**
	 * ??????????ο???school_device???????
	 * 
	 */

	@Column(name = "project_budget", scale = 2, precision = 20)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal projectBudget;
	/**
	 * ???????
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar createdAt;
	/**
	 * ???????
	 * 
	 */

	@Column(name = "project_analysis", columnDefinition = "TEXT")
	@Basic(fetch = FetchType.EAGER)
	@Lob
	@XmlElement
	String projectAnalysis;
	/**
	 * ???跽??
	 * 
	 */

	@Column(name = "project_scheme", columnDefinition = "TEXT")
	@Basic(fetch = FetchType.EAGER)
	@Lob
	@XmlElement
	String projectScheme;
	/**
	 * ???????1 ???2????У?3δ????4 ?????
	 * 
	 */

	@Column(name = "audit_results")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer auditResults;
	/**
	 * ????Σ?0 δ???  1  ??????  2 ???????  3 ??????
	 * 
	 */

	@Column(name = "stage")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer stage;
	/**
	 * ????绰
	 * 
	 */

	@Column(name = "telephone", length = 20)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String telephone;
	/**
	 * E-Mail???
	 * 
	 */

	@Column(name = "email", length = 20)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String email;

	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "project_manager", referencedColumnName = "username") })
	@XmlTransient
	User user;
	
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "created_by", referencedColumnName = "username") })
	@XmlTransient
	User userByCreatedBy;
	
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "academy_number", referencedColumnName = "academy_number") })
	@XmlTransient
	SchoolAcademy schoolAcademy;
	/**
	 */
	@OneToMany(mappedBy = "labConstructionProject", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.LabConstructionDevice> labConstructionDevices;
	/**
	 */
	@OneToMany(mappedBy = "labConstructionProject", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.LabConstructionPurchase> labConstructionPurchases;
	/**
	 */
	@OneToMany(mappedBy = "labConstructionProject", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.LabConstructionAcceptance> labConstructionAcceptances;
	/**
	 */
	/*@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(catalog = "xidlims", name = "m_lab_construction_project_user", joinColumns = { @JoinColumn(name = "lab_construction_project_id", referencedColumnName = "id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "user_id", referencedColumnName = "username", nullable = false, updatable = false) })
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.User> users;*/
	/**
	 */
	@OneToMany(mappedBy = "labConstructionProject", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.MLabConstructionProjectUser> MLabConstructionProjectUsers;
	/**
	 */
	@OneToMany(mappedBy = "labConstructionProject", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.LabConstructionProjectAudit> labConstructionProjectAudits;
	/**
	 */
	@OneToMany(mappedBy = "labConstructionProject", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.LabConstructionFunding> labConstructionFundings;

	//2015.10.14新增
	@OneToMany(mappedBy = "labConstructionProject", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.CommonDocument> commonDocuments;
	
	/**
	 * ????????-????????
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * ????????-????????
	 * 
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * ??????
	 * 
	 */
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	/**
	 * ??????
	 * 
	 */
	public String getProjectName() {
		return this.projectName;
	}

	/**
	 * ??????
	 * 
	 */
	public void setProjectNumber(String projectNumber) {
		this.projectNumber = projectNumber;
	}

	/**
	 * ??????
	 * 
	 */
	public String getProjectNumber() {
		return this.projectNumber;
	}

	/**
	 * ??????????ο???school_device???????
	 * 
	 */
	public void setProjectBudget(BigDecimal projectBudget) {
		this.projectBudget = projectBudget;
	}

	/**
	 * ??????????ο???school_device???????
	 * 
	 */
	public BigDecimal getProjectBudget() {
		return this.projectBudget;
	}

	/**
	 * ???????
	 * 
	 */
	public void setCreatedAt(Calendar createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * ???????
	 * 
	 */
	public Calendar getCreatedAt() {
		return this.createdAt;
	}

	/**
	 * ???????
	 * 
	 */
	public void setProjectAnalysis(String projectAnalysis) {
		this.projectAnalysis = projectAnalysis;
	}

	/**
	 * ???????
	 * 
	 */
	public String getProjectAnalysis() {
		return this.projectAnalysis;
	}

	/**
	 * ???跽??
	 * 
	 */
	public void setProjectScheme(String projectScheme) {
		this.projectScheme = projectScheme;
	}

	/**
	 * ???跽??
	 * 
	 */
	public String getProjectScheme() {
		return this.projectScheme;
	}

	/**
	 * ???????1 ???2????У?3δ????4 ?????
	 * 
	 */
	public void setAuditResults(Integer auditResults) {
		this.auditResults = auditResults;
	}

	/**
	 * ???????1 ???2????У?3δ????4 ?????
	 * 
	 */
	public Integer getAuditResults() {
		return this.auditResults;
	}

	/**
	 * ????Σ?0 δ???  1  ??????  2 ???????  3 ??????
	 * 
	 */
	public void setStage(Integer stage) {
		this.stage = stage;
	}

	/**
	 * ????Σ?0 δ???  1  ??????  2 ???????  3 ??????
	 * 
	 */
	public Integer getStage() {
		return this.stage;
	}

	/**
	 * ????绰
	 * 
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	/**
	 * ????绰
	 * 
	 */
	public String getTelephone() {
		return this.telephone;
	}

	/**
	 * E-Mail???
	 * 
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * E-Mail???
	 * 
	 */
	public String getEmail() {
		return this.email;
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
	public void setLabConstructionDevices(Set<LabConstructionDevice> labConstructionDevices) {
		this.labConstructionDevices = labConstructionDevices;
	}

	/**
	 */
	@JsonIgnore
	public Set<LabConstructionDevice> getLabConstructionDevices() {
		if (labConstructionDevices == null) {
			labConstructionDevices = new java.util.LinkedHashSet<net.xidlims.domain.LabConstructionDevice>();
		}
		return labConstructionDevices;
	}

	/**
	 */
	public void setLabConstructionPurchases(Set<LabConstructionPurchase> labConstructionPurchases) {
		this.labConstructionPurchases = labConstructionPurchases;
	}

	/**
	 */
	@JsonIgnore
	public Set<LabConstructionPurchase> getLabConstructionPurchases() {
		if (labConstructionPurchases == null) {
			labConstructionPurchases = new java.util.LinkedHashSet<net.xidlims.domain.LabConstructionPurchase>();
		}
		return labConstructionPurchases;
	}

	/**
	 */
	public void setLabConstructionAcceptances(Set<LabConstructionAcceptance> labConstructionAcceptances) {
		this.labConstructionAcceptances = labConstructionAcceptances;
	}

	/**
	 */
	@JsonIgnore
	public Set<LabConstructionAcceptance> getLabConstructionAcceptances() {
		if (labConstructionAcceptances == null) {
			labConstructionAcceptances = new java.util.LinkedHashSet<net.xidlims.domain.LabConstructionAcceptance>();
		}
		return labConstructionAcceptances;
	}

	/**
	 *//*
	public void setUsers(Set<User> users) {
		this.users = users;
	}

	*//**
	 *//*
	@JsonIgnore
	public Set<User> getUsers() {
		if (users == null) {
			users = new java.util.LinkedHashSet<net.xidlims.domain.User>();
		}
		return users;
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
	public void setLabConstructionFundings(Set<LabConstructionFunding> labConstructionFundings) {
		this.labConstructionFundings = labConstructionFundings;
	}

	/**
	 */
	@JsonIgnore
	public Set<LabConstructionFunding> getLabConstructionFundings() {
		if (labConstructionFundings == null) {
			labConstructionFundings = new java.util.LinkedHashSet<net.xidlims.domain.LabConstructionFunding>();
		}
		return labConstructionFundings;
	}
	
	//2015.10.14新增
	/**
	 */
	public void setCommonDocuments(Set<CommonDocument> commonDocuments) {
		this.commonDocuments = commonDocuments;
	}

	/**
	 */
	@JsonIgnore
	public Set<CommonDocument> getCommonDocuments() {
		if (commonDocuments == null) {
			commonDocuments = new java.util.LinkedHashSet<net.xidlims.domain.CommonDocument>();
		}
		return commonDocuments;
	}

	/**
	 */
	public LabConstructionProject() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(LabConstructionProject that) {
		setId(that.getId());
		setProjectName(that.getProjectName());
		setProjectNumber(that.getProjectNumber());
		setProjectBudget(that.getProjectBudget());
		setCreatedAt(that.getCreatedAt());
		setProjectAnalysis(that.getProjectAnalysis());
		setProjectScheme(that.getProjectScheme());
		setAuditResults(that.getAuditResults());
		setStage(that.getStage());
		setTelephone(that.getTelephone());
		setEmail(that.getEmail());
		setUser(that.getUser());
		setUserByCreatedBy(that.getUserByCreatedBy());
		setSchoolAcademy(that.getSchoolAcademy());
		setLabConstructionDevices(new java.util.LinkedHashSet<net.xidlims.domain.LabConstructionDevice>(that.getLabConstructionDevices()));
		setLabConstructionPurchases(new java.util.LinkedHashSet<net.xidlims.domain.LabConstructionPurchase>(that.getLabConstructionPurchases()));
		setLabConstructionAcceptances(new java.util.LinkedHashSet<net.xidlims.domain.LabConstructionAcceptance>(that.getLabConstructionAcceptances()));
//		setUsers(new java.util.LinkedHashSet<net.xidlims.domain.User>(that.getUsers()));
		setMLabConstructionProjectUsers(new java.util.LinkedHashSet<net.xidlims.domain.MLabConstructionProjectUser>(that.getMLabConstructionProjectUsers()));
		setLabConstructionProjectAudits(new java.util.LinkedHashSet<net.xidlims.domain.LabConstructionProjectAudit>(that.getLabConstructionProjectAudits()));
		setLabConstructionFundings(new java.util.LinkedHashSet<net.xidlims.domain.LabConstructionFunding>(that.getLabConstructionFundings()));
		setCommonDocuments(that.getCommonDocuments());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("id=[").append(id).append("] ");
		buffer.append("projectName=[").append(projectName).append("] ");
		buffer.append("projectNumber=[").append(projectNumber).append("] ");
		buffer.append("projectBudget=[").append(projectBudget).append("] ");
		buffer.append("createdAt=[").append(createdAt).append("] ");
		buffer.append("projectAnalysis=[").append(projectAnalysis).append("] ");
		buffer.append("projectScheme=[").append(projectScheme).append("] ");
		buffer.append("auditResults=[").append(auditResults).append("] ");
		buffer.append("stage=[").append(stage).append("] ");
		buffer.append("telephone=[").append(telephone).append("] ");
		buffer.append("email=[").append(email).append("] ");

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
		if (!(obj instanceof LabConstructionProject))
			return false;
		LabConstructionProject equalCheck = (LabConstructionProject) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
