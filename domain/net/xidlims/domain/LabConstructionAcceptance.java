package net.xidlims.domain;

import java.io.Serializable;

import java.lang.StringBuilder;

import java.util.Calendar;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.codehaus.jackson.annotate.JsonIgnore;

import javax.xml.bind.annotation.*;

import javax.persistence.*;

/**
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "findAllLabConstructionAcceptances", query = "select myLabConstructionAcceptance from LabConstructionAcceptance myLabConstructionAcceptance"),
		@NamedQuery(name = "findLabConstructionAcceptanceById", query = "select myLabConstructionAcceptance from LabConstructionAcceptance myLabConstructionAcceptance where myLabConstructionAcceptance.id = ?1"),
		@NamedQuery(name = "findLabConstructionAcceptanceByPrimaryKey", query = "select myLabConstructionAcceptance from LabConstructionAcceptance myLabConstructionAcceptance where myLabConstructionAcceptance.id = ?1") })
@Table(catalog = "xidlims", name = "lab_construction_acceptance")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "LabConstructionAcceptance")
public class LabConstructionAcceptance implements Serializable {
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
	 * ��Ŀ���
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar createdAt;
	
	
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
	@JoinColumns({ @JoinColumn(name = "lab_construction_project", referencedColumnName = "id") })
	@XmlTransient
	LabConstructionProject labConstructionProject;
	//2015.10.14新增
	@OneToMany(mappedBy = "labConstructionAcceptance", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.CommonDocument> commonDocuments;

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
	
	public Integer getStage() {
		return stage;
	}

	public void setStage(Integer stage) {
		this.stage = stage;
	}

	/**
	 */
	public LabConstructionAcceptance() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(LabConstructionAcceptance that) {
		setId(that.getId());
		setCreatedAt(that.getCreatedAt());
		setLabConstructionProject(that.getLabConstructionProject());
		setCommonDocuments(that.getCommonDocuments());
		setStage(that.getStage());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("id=[").append(id).append("] ");

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
		if (!(obj instanceof LabConstructionAcceptance))
			return false;
		LabConstructionAcceptance equalCheck = (LabConstructionAcceptance) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
