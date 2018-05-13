package net.xidlims.domain;

import java.io.Serializable;

import java.lang.StringBuilder;

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
		@NamedQuery(name = "findAllOperationItemDevices", query = "select myOperationItemDevice from OperationItemDevice myOperationItemDevice"),
		@NamedQuery(name = "findOperationItemDeviceById", query = "select myOperationItemDevice from OperationItemDevice myOperationItemDevice where myOperationItemDevice.id = ?1"),
		@NamedQuery(name = "findOperationItemDeviceByPrimaryKey", query = "select myOperationItemDevice from OperationItemDevice myOperationItemDevice where myOperationItemDevice.id = ?1") })
@Table(catalog = "xidlims", name = "operation_item_device")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "OperationItemDevice")
public class OperationItemDevice implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ʵ����Ŀ�豸��
	 * 
	 */

	@Column(name = "id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@XmlElement
	Integer id;

	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "lab_room_device_id", referencedColumnName = "id") })
	@XmlTransient
	LabRoomDevice labRoomDevice;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "operation_item_id", referencedColumnName = "id") })
	@XmlTransient
	OperationItem operationItem;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "category_main", referencedColumnName = "id") })
	@XmlTransient
	CDictionary CDictionary;

	/**
	 * ʵ����Ŀ�豸��
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * ʵ����Ŀ�豸��
	 * 
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 */
	public void setOperationItem(OperationItem operationItem) {
		this.operationItem = operationItem;
	}

	/**
	 */
	@JsonIgnore
	public OperationItem getOperationItem() {
		return operationItem;
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
	public OperationItemDevice() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(OperationItemDevice that) {
		setId(that.getId());
		setOperationItem(that.getOperationItem());
		setCDictionary(that.getCDictionary());
	}

	@JsonIgnore
	public LabRoomDevice getLabRoomDevice() {
		return labRoomDevice;
	}

	public void setLabRoomDevice(LabRoomDevice labRoomDevice) {
		this.labRoomDevice = labRoomDevice;
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
		if (!(obj instanceof OperationItemDevice))
			return false;
		OperationItemDevice equalCheck = (OperationItemDevice) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
