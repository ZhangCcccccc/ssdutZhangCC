package net.xidlims.domain;

import java.io.Serializable;

import java.lang.StringBuilder;

import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.FetchType;
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
		@NamedQuery(name = "findAllLabConstructionProjectAudits", query = "select myLabConstructionProjectAudit from LabConstructionProjectAudit myLabConstructionProjectAudit"),
		@NamedQuery(name = "findLabConstructionProjectAuditByComments", query = "select myLabConstructionProjectAudit from LabConstructionProjectAudit myLabConstructionProjectAudit where myLabConstructionProjectAudit.comments = ?1"),
		@NamedQuery(name = "findLabConstructionProjectAuditById", query = "select myLabConstructionProjectAudit from LabConstructionProjectAudit myLabConstructionProjectAudit where myLabConstructionProjectAudit.id = ?1"),
		@NamedQuery(name = "findLabConstructionProjectAuditByPrimaryKey", query = "select myLabConstructionProjectAudit from LabConstructionProjectAudit myLabConstructionProjectAudit where myLabConstructionProjectAudit.id = ?1") })
@Table(catalog = "xidlims", name = "lab_construction_project_audit")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "LabConstructionProjectAudit")
public class LabConstructionProjectAudit implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ʵ���ҽ���-��Ŀ�����˽��?
	 * 
	 */

	@Column(name = "id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@XmlElement
	Integer id;
	/**
	 * ������
	 * 
	 */

	@Column(name = "comments", columnDefinition = "TEXT")
	@Basic(fetch = FetchType.EAGER)
	@Lob
	@XmlElement
	String comments;
	
	
	@Column(name = "tag")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer tag;

	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "lab_construction_project_id", referencedColumnName = "id") })
	@XmlTransient
	LabConstructionProject labConstructionProject;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "audit_return", referencedColumnName = "id") })
	@XmlTransient
	CDictionary CDictionary;
	
	
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "user", referencedColumnName = "username") })
	@XmlTransient
	User user;

	/**
	 * ʵ���ҽ���-��Ŀ�����˽��?
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * ʵ���ҽ���-��Ŀ�����˽��?
	 * 
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * ������
	 * 
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}

	/**
	 * ������
	 * 
	 */
	public String getComments() {
		return this.comments;
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
	 * ��־λ��1 purchase  2 funding��
	 * 
	 */
	public void setTag(Integer tag) {
		this.tag = tag;
	}

	/**
	 * ��־λ��1 purchase  2 funding��
	 * 
	 */
	public Integer getTag() {
		return this.tag;
	}
	
	
	/**
	 */
	public LabConstructionProjectAudit() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(LabConstructionProjectAudit that) {
		setId(that.getId());
		setComments(that.getComments());
		setLabConstructionProject(that.getLabConstructionProject());
		setCDictionary(that.getCDictionary());
		setTag(that.getTag());
		setUser(that.getUser());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("id=[").append(id).append("] ");
		buffer.append("comments=[").append(comments).append("] ");

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
		if (!(obj instanceof LabConstructionProjectAudit))
			return false;
		LabConstructionProjectAudit equalCheck = (LabConstructionProjectAudit) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
