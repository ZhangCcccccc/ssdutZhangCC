package net.xidlims.domain;

import java.io.Serializable;

import java.lang.StringBuilder;

import java.util.Calendar;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.codehaus.jackson.annotate.JsonIgnore;

import javax.xml.bind.annotation.*;

import javax.persistence.*;

/**
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "findAllLabConstructionFundings", query = "select myLabConstructionFunding from LabConstructionFunding myLabConstructionFunding"),
		@NamedQuery(name = "findLabConstructionFundingByAuditResults", query = "select myLabConstructionFunding from LabConstructionFunding myLabConstructionFunding where myLabConstructionFunding.auditResults = ?1"),
		@NamedQuery(name = "findLabConstructionFundingByFundingNumber", query = "select myLabConstructionFunding from LabConstructionFunding myLabConstructionFunding where myLabConstructionFunding.fundingNumber = ?1"),
		@NamedQuery(name = "findLabConstructionFundingByFundingNumberContaining", query = "select myLabConstructionFunding from LabConstructionFunding myLabConstructionFunding where myLabConstructionFunding.fundingNumber like ?1"),
		@NamedQuery(name = "findLabConstructionFundingById", query = "select myLabConstructionFunding from LabConstructionFunding myLabConstructionFunding where myLabConstructionFunding.id = ?1"),
		@NamedQuery(name = "findLabConstructionFundingByPrimaryKey", query = "select myLabConstructionFunding from LabConstructionFunding myLabConstructionFunding where myLabConstructionFunding.id = ?1"),
		@NamedQuery(name = "findLabConstructionFundingByStage", query = "select myLabConstructionFunding from LabConstructionFunding myLabConstructionFunding where myLabConstructionFunding.stage = ?1") })
@Table(catalog = "xidlims", name = "lab_construction_funding")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "LabConstructionFunding")
@XmlRootElement(namespace = "xidlims/net/xidlims/domain")
public class LabConstructionFunding implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 */

	@Column(name = "id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@XmlElement
	Integer id;
	/**
	 * ���ѱ��
	 * 
	 */

	@Column(name = "funding_number", length = 40)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String fundingNumber;
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
	 * ��Ŀ���
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar createdAt;
	
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "lab_construction_project", referencedColumnName = "id", nullable = false) })
	@XmlTransient
	LabConstructionProject labConstructionProject;
	/**
	 */
	@OneToMany(mappedBy = "labConstructionFunding", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.LabConstructionFundingAudit> labConstructionFundingAudits;

	/**
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * ���ѱ��
	 * 
	 */
	public void setFundingNumber(String fundingNumber) {
		this.fundingNumber = fundingNumber;
	}

	/**
	 * ���ѱ��
	 * 
	 */
	public String getFundingNumber() {
		return this.fundingNumber;
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
	 */
	public LabConstructionFunding() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(LabConstructionFunding that) {
		setId(that.getId());
		setCreatedAt(that.getCreatedAt());
		setFundingNumber(that.getFundingNumber());
		setAuditResults(that.getAuditResults());
		setStage(that.getStage());
		setLabConstructionProject(that.getLabConstructionProject());
		setLabConstructionFundingAudits(new java.util.LinkedHashSet<net.xidlims.domain.LabConstructionFundingAudit>(that.getLabConstructionFundingAudits()));
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("id=[").append(id).append("] ");
		buffer.append("fundingNumber=[").append(fundingNumber).append("] ");
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
		if (!(obj instanceof LabConstructionFunding))
			return false;
		LabConstructionFunding equalCheck = (LabConstructionFunding) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
