package net.xidlims.domain;

import java.io.Serializable;

import java.lang.StringBuilder;

import java.util.Calendar;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.codehaus.jackson.annotate.JsonIgnore;

import javax.xml.bind.annotation.*;

import javax.persistence.*;

/**
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "findAllLabConstructionPurchases", query = "select myLabConstructionPurchase from LabConstructionPurchase myLabConstructionPurchase"),
		@NamedQuery(name = "findLabConstructionPurchaseByAuditResults", query = "select myLabConstructionPurchase from LabConstructionPurchase myLabConstructionPurchase where myLabConstructionPurchase.auditResults = ?1"),
		@NamedQuery(name = "findLabConstructionPurchaseById", query = "select myLabConstructionPurchase from LabConstructionPurchase myLabConstructionPurchase where myLabConstructionPurchase.id = ?1"),
		@NamedQuery(name = "findLabConstructionPurchaseByPrimaryKey", query = "select myLabConstructionPurchase from LabConstructionPurchase myLabConstructionPurchase where myLabConstructionPurchase.id = ?1"),
		@NamedQuery(name = "findLabConstructionPurchaseByPurchaseReason", query = "select myLabConstructionPurchase from LabConstructionPurchase myLabConstructionPurchase where myLabConstructionPurchase.purchaseReason = ?1"),
		@NamedQuery(name = "findLabConstructionPurchaseByPurchaseTime", query = "select myLabConstructionPurchase from LabConstructionPurchase myLabConstructionPurchase where myLabConstructionPurchase.purchaseTime = ?1"),
		@NamedQuery(name = "findLabConstructionPurchaseByStage", query = "select myLabConstructionPurchase from LabConstructionPurchase myLabConstructionPurchase where myLabConstructionPurchase.stage = ?1"),
		@NamedQuery(name = "findLabConstructionPurchaseByUseLocation", query = "select myLabConstructionPurchase from LabConstructionPurchase myLabConstructionPurchase where myLabConstructionPurchase.useLocation = ?1"),
		@NamedQuery(name = "findLabConstructionPurchaseByUseLocationContaining", query = "select myLabConstructionPurchase from LabConstructionPurchase myLabConstructionPurchase where myLabConstructionPurchase.useLocation like ?1") })
@Table(catalog = "xidlims", name = "lab_construction_purchase")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "LabConstructionPurchase")
@XmlRootElement(namespace = "xidlims/net/xidlims/domain")
public class LabConstructionPurchase implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ʵ���ҽ���-��Ŀ����?�깺��
	 * 
	 */

	@Column(name = "id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@XmlElement
	Integer id;
	/**
	 * ����״�����깺���ɼ�Ҫ��
	 * 
	 */

	@Column(name = "purchase_reason", columnDefinition = "TEXT")
	@Basic(fetch = FetchType.EAGER)
	@Lob
	@XmlElement
	String purchaseReason;
	/**
	 * ʹ�õص�
	 * 
	 */

	@Column(name = "use_location", length = 40)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String useLocation;
	/**
	 * �깺ʱ��
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "purchase_time")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar purchaseTime;
	/**
	 * ���״̬��1 ͨ��2����У�3δ��ˣ�4 ��˾ܾ�
	 * 
	 */

	@Column(name = "audit_results")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer auditResults;
	/**
	 * ��˽׶Σ�0 δ���  1  һ�����  2 �������  3 �����  4 �ļ���ˣ�
	 * 
	 */

	@Column(name = "stage")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer stage;

	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "department_number", referencedColumnName = "academy_number") })
	@XmlTransient
	SchoolAcademy schoolAcademy;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "lab_construction_project", referencedColumnName = "id", nullable = false) })
	@XmlTransient
	LabConstructionProject labConstructionProject;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "purchase_type", referencedColumnName = "id") })
	@XmlTransient
	CDictionary CDictionary;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "applicant", referencedColumnName = "username") })
	@XmlTransient
	User userByApplicant;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "keeper", referencedColumnName = "username") })
	@XmlTransient
	User userByKeeper;
	/**
	 */
	@OneToMany(mappedBy = "labConstructionPurchase", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.LabConstructionPurchaseAudit> labConstructionPurchaseAudits;

	/**
	 * ʵ���ҽ���-��Ŀ����?�깺��
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * ʵ���ҽ���-��Ŀ����?�깺��
	 * 
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * ����״�����깺���ɼ�Ҫ��
	 * 
	 */
	public void setPurchaseReason(String purchaseReason) {
		this.purchaseReason = purchaseReason;
	}

	/**
	 * ����״�����깺���ɼ�Ҫ��
	 * 
	 */
	public String getPurchaseReason() {
		return this.purchaseReason;
	}

	/**
	 * ʹ�õص�
	 * 
	 */
	public void setUseLocation(String useLocation) {
		this.useLocation = useLocation;
	}

	/**
	 * ʹ�õص�
	 * 
	 */
	public String getUseLocation() {
		return this.useLocation;
	}

	/**
	 * �깺ʱ��
	 * 
	 */
	public void setPurchaseTime(Calendar purchaseTime) {
		this.purchaseTime = purchaseTime;
	}

	/**
	 * �깺ʱ��
	 * 
	 */
	public Calendar getPurchaseTime() {
		return this.purchaseTime;
	}

	/**
	 * ���״̬��1 ͨ��2����У�3δ��ˣ�4 ��˾ܾ�
	 * 
	 */
	public void setAuditResults(Integer auditResults) {
		this.auditResults = auditResults;
	}

	/**
	 * ���״̬��1 ͨ��2����У�3δ��ˣ�4 ��˾ܾ�
	 * 
	 */
	public Integer getAuditResults() {
		return this.auditResults;
	}

	/**
	 * ��˽׶Σ�0 δ���  1  һ�����  2 �������  3 �����  4 �ļ���ˣ�
	 * 
	 */
	public void setStage(Integer stage) {
		this.stage = stage;
	}

	/**
	 * ��˽׶Σ�0 δ���  1  һ�����  2 �������  3 �����  4 �ļ���ˣ�
	 * 
	 */
	public Integer getStage() {
		return this.stage;
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
	public void setLabConstructionProject(LabConstructionProject labConstructionProject) {
		this.labConstructionProject = labConstructionProject;
	}

	/**
	 */
	@JsonIgnore
	public LabConstructionProject getLabConstructionProject() {
		return labConstructionProject;
	}

	/**
	 */
	public void setCDictionary(CDictionary CDictionary) {
		this.CDictionary = CDictionary;
	}

	/**
	 */
	@JsonIgnore
	public CDictionary getCDictionary() {
		return CDictionary;
	}

	/**
	 */
	public void setUserByApplicant(User userByApplicant) {
		this.userByApplicant = userByApplicant;
	}

	/**
	 */
	@JsonIgnore
	public User getUserByApplicant() {
		return userByApplicant;
	}

	/**
	 */
	public void setUserByKeeper(User userByKeeper) {
		this.userByKeeper = userByKeeper;
	}

	/**
	 */
	@JsonIgnore
	public User getUserByKeeper() {
		return userByKeeper;
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
	 */
	public LabConstructionPurchase() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(LabConstructionPurchase that) {
		setId(that.getId());
		setPurchaseReason(that.getPurchaseReason());
		setUseLocation(that.getUseLocation());
		setPurchaseTime(that.getPurchaseTime());
		setAuditResults(that.getAuditResults());
		setStage(that.getStage());
		setSchoolAcademy(that.getSchoolAcademy());
		setLabConstructionProject(that.getLabConstructionProject());
		setCDictionary(that.getCDictionary());
		setUserByApplicant(that.getUserByApplicant());
		setUserByKeeper(that.getUserByKeeper());
		setLabConstructionPurchaseAudits(new java.util.LinkedHashSet<net.xidlims.domain.LabConstructionPurchaseAudit>(that.getLabConstructionPurchaseAudits()));
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("id=[").append(id).append("] ");
		buffer.append("purchaseReason=[").append(purchaseReason).append("] ");
		buffer.append("useLocation=[").append(useLocation).append("] ");
		buffer.append("purchaseTime=[").append(purchaseTime).append("] ");
		buffer.append("auditResults=[").append(auditResults).append("] ");
		buffer.append("stage=[").append(stage).append("] ");

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
		if (!(obj instanceof LabConstructionPurchase))
			return false;
		LabConstructionPurchase equalCheck = (LabConstructionPurchase) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
