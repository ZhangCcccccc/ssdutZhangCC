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
		@NamedQuery(name = "findAllLabConstructionPurchaseAudits", query = "select myLabConstructionPurchaseAudit from LabConstructionPurchaseAudit myLabConstructionPurchaseAudit"),
		@NamedQuery(name = "findLabConstructionPurchaseAuditByComments", query = "select myLabConstructionPurchaseAudit from LabConstructionPurchaseAudit myLabConstructionPurchaseAudit where myLabConstructionPurchaseAudit.comments = ?1"),
		@NamedQuery(name = "findLabConstructionPurchaseAuditById", query = "select myLabConstructionPurchaseAudit from LabConstructionPurchaseAudit myLabConstructionPurchaseAudit where myLabConstructionPurchaseAudit.id = ?1"),
		@NamedQuery(name = "findLabConstructionPurchaseAuditByPrimaryKey", query = "select myLabConstructionPurchaseAudit from LabConstructionPurchaseAudit myLabConstructionPurchaseAudit where myLabConstructionPurchaseAudit.id = ?1") })
@Table(catalog = "xidlims", name = "lab_construction_purchase_audit")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "LabConstructionPurchaseAudit")
public class LabConstructionPurchaseAudit implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ʵ���ҽ���-��Ŀ����?�깺��˽��?
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

	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "audit_return", referencedColumnName = "id") })
	@XmlTransient
	CDictionary CDictionary;
	
	@Column(name = "tag")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer tag;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "user", referencedColumnName = "username") })
	@XmlTransient
	User user;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "lab_construction_purchase_id", referencedColumnName = "id") })
	@XmlTransient
	LabConstructionPurchase labConstructionPurchase;

	/**
	 * ʵ���ҽ���-��Ŀ����?�깺��˽��?
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * ʵ���ҽ���-��Ŀ����?�깺��˽��?
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
	 */
	public void setLabConstructionPurchase(LabConstructionPurchase labConstructionPurchase) {
		this.labConstructionPurchase = labConstructionPurchase;
	}

	/**
	 */
	@JsonIgnore
	public LabConstructionPurchase getLabConstructionPurchase() {
		return labConstructionPurchase;
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
	public LabConstructionPurchaseAudit() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(LabConstructionPurchaseAudit that) {
		setId(that.getId());
		setComments(that.getComments());
		setCDictionary(that.getCDictionary());
		setUser(that.getUser());
		setTag(that.getTag());
		setLabConstructionPurchase(that.getLabConstructionPurchase());
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
		if (!(obj instanceof LabConstructionPurchaseAudit))
			return false;
		LabConstructionPurchaseAudit equalCheck = (LabConstructionPurchaseAudit) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
