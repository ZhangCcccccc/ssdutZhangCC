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
		@NamedQuery(name = "findAllLabRoomDeviceLendingResults", query = "select myLabRoomDeviceLendingResult from LabRoomDeviceLendingResult myLabRoomDeviceLendingResult"),
		@NamedQuery(name = "findLabRoomDeviceLendingResultById", query = "select myLabRoomDeviceLendingResult from LabRoomDeviceLendingResult myLabRoomDeviceLendingResult where myLabRoomDeviceLendingResult.id = ?1"),
		@NamedQuery(name = "findLabRoomDeviceLendingResultByPrimaryKey", query = "select myLabRoomDeviceLendingResult from LabRoomDeviceLendingResult myLabRoomDeviceLendingResult where myLabRoomDeviceLendingResult.id = ?1"),
		@NamedQuery(name = "findLabRoomDeviceLendingResultByRemark", query = "select myLabRoomDeviceLendingResult from LabRoomDeviceLendingResult myLabRoomDeviceLendingResult where myLabRoomDeviceLendingResult.remark = ?1") })
@Table(catalog = "xidlims", name = "lab_room_device_lending_result")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "LabRoomDeviceLendingResult")
public class LabRoomDeviceLendingResult implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
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

	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "lending_id", referencedColumnName = "id") })
	@XmlTransient
	LabRoomDeviceLending labRoomDeviceLending;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "audit_result", referencedColumnName = "id") })
	@XmlTransient
	CDictionary CDictionary;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "audit_user", referencedColumnName = "username") })
	@XmlTransient
	User user;

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
	public void setLabRoomDeviceLending(LabRoomDeviceLending labRoomDeviceLending) {
		this.labRoomDeviceLending = labRoomDeviceLending;
	}

	/**
	 */
	@JsonIgnore
	public LabRoomDeviceLending getLabRoomDeviceLending() {
		return labRoomDeviceLending;
	}


	public CDictionary getCDictionary() {
		return CDictionary;
	}

	public void setCDictionary(CDictionary cDictionary) {
		CDictionary = cDictionary;
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
	public LabRoomDeviceLendingResult() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(LabRoomDeviceLendingResult that) {
		setId(that.getId());
		setRemark(that.getRemark());
		setLabRoomDeviceLending(that.getLabRoomDeviceLending());
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
		if (!(obj instanceof LabRoomDeviceLendingResult))
			return false;
		LabRoomDeviceLendingResult equalCheck = (LabRoomDeviceLendingResult) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
