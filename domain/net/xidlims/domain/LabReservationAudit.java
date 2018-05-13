package net.xidlims.domain;

import java.io.Serializable;

import java.lang.StringBuilder;

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
		@NamedQuery(name = "findAllLabReservationAudits", query = "select myLabReservationAudit from LabReservationAudit myLabReservationAudit"),
		@NamedQuery(name = "findLabReservationAuditByComments", query = "select myLabReservationAudit from LabReservationAudit myLabReservationAudit where myLabReservationAudit.comments = ?1"),
		@NamedQuery(name = "findLabReservationAuditById", query = "select myLabReservationAudit from LabReservationAudit myLabReservationAudit where myLabReservationAudit.id = ?1"),
		@NamedQuery(name = "findLabReservationAuditByPrimaryKey", query = "select myLabReservationAudit from LabReservationAudit myLabReservationAudit where myLabReservationAudit.id = ?1") })
@Table(catalog = "xidlims", name = "lab_reservation_audit")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "LabReservationAudit")
public class LabReservationAudit implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ʵ����ԤԼ��˽���
	 * 
	 */

	@Column(name = "id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	@GeneratedValue(strategy = GenerationType.AUTO)
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
	@JoinColumns({ @JoinColumn(name = "lab_reservsation_id", referencedColumnName = "id") })
	@XmlTransient
	LabReservation labReservation;
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
	 * ʵ����ԤԼ��˽���
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * ʵ����ԤԼ��˽���
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
	public void setLabReservation(LabReservation labReservation) {
		this.labReservation = labReservation;
	}

	/**
	 */
	@JsonIgnore
	public LabReservation getLabReservation() {
		return labReservation;
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
	public LabReservationAudit() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(LabReservationAudit that) {
		setId(that.getId());
		setComments(that.getComments());
		setLabReservation(that.getLabReservation());
		setCDictionary(that.getCDictionary());
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
		if (!(obj instanceof LabReservationAudit))
			return false;
		LabReservationAudit equalCheck = (LabReservationAudit) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
