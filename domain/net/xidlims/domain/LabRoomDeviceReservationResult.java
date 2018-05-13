package net.xidlims.domain;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
		@NamedQuery(name = "findAllLabRoomDeviceReservationResults", query = "select myLabRoomDeviceReservationResult from LabRoomDeviceReservationResult myLabRoomDeviceReservationResult"),
		@NamedQuery(name = "findLabRoomDeviceReservationResultById", query = "select myLabRoomDeviceReservationResult from LabRoomDeviceReservationResult myLabRoomDeviceReservationResult where myLabRoomDeviceReservationResult.id = ?1"),
		@NamedQuery(name = "findLabRoomDeviceReservationResultByPrimaryKey", query = "select myLabRoomDeviceReservationResult from LabRoomDeviceReservationResult myLabRoomDeviceReservationResult where myLabRoomDeviceReservationResult.id = ?1"),
		@NamedQuery(name = "findLabRoomDeviceReservationResultByRemark", query = "select myLabRoomDeviceReservationResult from LabRoomDeviceReservationResult myLabRoomDeviceReservationResult where myLabRoomDeviceReservationResult.remark = ?1") })
@Table(catalog = "xidlims", name = "lab_room_device_reservation_result")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "LabRoomDeviceReservationResult")
public class LabRoomDeviceReservationResult implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ʵ���ҷ����豸ԤԼ����
	 * 
	 */

	@Column(name = "id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	@XmlElement
	Integer id;
	/**
	 * ��ע
	 * 
	 */

	@Column(name = "remark", columnDefinition = "TEXT")
	@Basic(fetch = FetchType.EAGER)
	@Lob
	@XmlElement
	String remark;

	@Column(name = "tag")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer tag;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "audit_time")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar auditTime;
	
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "audit_result", referencedColumnName = "id") })
	@XmlTransient
	CDictionary CDictionaryByCTrainingResult;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "audit_user", referencedColumnName = "username") })
	@XmlTransient
	User user;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "reservation_id", referencedColumnName = "id") })
	@XmlTransient
	LabRoomDeviceReservation labRoomDeviceReservation;
	

	/**
	 * ʵ���ҷ����豸ԤԼ����
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * ʵ���ҷ����豸ԤԼ����
	 * 
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * ��ע
	 * 
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * ��ע
	 * 
	 */
	public String getRemark() {
		return this.remark;
	}

	/**
	 */
	public void setCTrainingResult(CDictionary CDictionaryByCTrainingResult) {
		this.CDictionaryByCTrainingResult = CDictionaryByCTrainingResult;
	}

	/**
	 */
	@JsonIgnore
	public CDictionary getCTrainingResult() {
		return CDictionaryByCTrainingResult;
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
	public void setLabRoomDeviceReservation(LabRoomDeviceReservation labRoomDeviceReservation) {
		this.labRoomDeviceReservation = labRoomDeviceReservation;
	}

	/**
	 */
	@JsonIgnore
	public LabRoomDeviceReservation getLabRoomDeviceReservation() {
		return labRoomDeviceReservation;
	}

	/**
	 * ��־λ��1 ��
	 * 
	 */
	public void setTag(Integer tag) {
		this.tag = tag;
	}

	/**
	 * ��־λ��1 ��
	 * 
	 */
	public Integer getTag() {
		return this.tag;
	}
	
	public Calendar getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(Calendar auditTime) {
		this.auditTime = auditTime;
	}
	
	/**
	 */
	public LabRoomDeviceReservationResult() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(LabRoomDeviceReservationResult that) {
		//setId(that.getId());
		setRemark(that.getRemark());
		setCTrainingResult(that.getCTrainingResult());
		setUser(that.getUser());
		setTag(that.getTag());
		//setLabRoomDeviceReservation(that.getLabRoomDeviceReservation());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("id=[").append(id).append("] ");
		buffer.append("remark=[").append(remark).append("] ");

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
		if (!(obj instanceof LabRoomDeviceReservationResult))
			return false;
		LabRoomDeviceReservationResult equalCheck = (LabRoomDeviceReservationResult) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
