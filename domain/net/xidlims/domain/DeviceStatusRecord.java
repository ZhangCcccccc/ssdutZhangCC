package net.xidlims.domain;

import java.io.Serializable;

import java.lang.StringBuilder;

import java.util.Calendar;
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
		@NamedQuery(name = "findAllDeviceStatusRecords", query = "select myDeviceStatusRecord from DeviceStatusRecord myDeviceStatusRecord"),
		@NamedQuery(name = "findDeviceStatusRecordByEndDate", query = "select myDeviceStatusRecord from DeviceStatusRecord myDeviceStatusRecord where myDeviceStatusRecord.endDate = ?1"),
		@NamedQuery(name = "findDeviceStatusRecordByEndDateAfter", query = "select myDeviceStatusRecord from DeviceStatusRecord myDeviceStatusRecord where myDeviceStatusRecord.endDate > ?1"),
		@NamedQuery(name = "findDeviceStatusRecordByEndDateBefore", query = "select myDeviceStatusRecord from DeviceStatusRecord myDeviceStatusRecord where myDeviceStatusRecord.endDate < ?1"),
		@NamedQuery(name = "findDeviceStatusRecordById", query = "select myDeviceStatusRecord from DeviceStatusRecord myDeviceStatusRecord where myDeviceStatusRecord.id = ?1"),
		@NamedQuery(name = "findDeviceStatusRecordByPrimaryKey", query = "select myDeviceStatusRecord from DeviceStatusRecord myDeviceStatusRecord where myDeviceStatusRecord.id = ?1"),
		@NamedQuery(name = "findDeviceStatusRecordByStatusName", query = "select myDeviceStatusRecord from DeviceStatusRecord myDeviceStatusRecord where myDeviceStatusRecord.statusName = ?1"),
		@NamedQuery(name = "findDeviceStatusRecordByStatusNameContaining", query = "select myDeviceStatusRecord from DeviceStatusRecord myDeviceStatusRecord where myDeviceStatusRecord.statusName like ?1") })
@Table(catalog = "xidlims", name = "device_status_record")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "DeviceStatusRecord")
public class DeviceStatusRecord implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * �豸״̬����������
	 * 
	 */

	@Column(name = "id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@XmlElement
	Integer id;
	/**
	 * �豸״̬��
	 * 
	 */

	@Column(name = "status_name")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String statusName;
	/**
	 * ����״̬ת��ʱ��
	 * 
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "end_date")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar endDate;

	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "device_id", referencedColumnName = "id") })
	@XmlTransient
	NDevicePurchase NDevicePurchase;

	/**
	 * �豸״̬����������
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * �豸״̬����������
	 * 
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * �豸״̬��
	 * 
	 */
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	/**
	 * �豸״̬��
	 * 
	 */
	public String getStatusName() {
		return this.statusName;
	}

	/**
	 * ����״̬ת��ʱ��
	 * 
	 */
	public void setEndDate(Calendar endDate) {
		this.endDate = endDate;
	}

	/**
	 * ����״̬ת��ʱ��
	 * 
	 */
	public Calendar getEndDate() {
		return this.endDate;
	}

	/**
	 */
	public void setNDevicePurchase(NDevicePurchase NDevicePurchase) {
		this.NDevicePurchase = NDevicePurchase;
	}

	/**
	 */
	@JsonIgnore
	public NDevicePurchase getNDevicePurchase() {
		return NDevicePurchase;
	}

	/**
	 */
	public DeviceStatusRecord() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(DeviceStatusRecord that) {
		setId(that.getId());
		setStatusName(that.getStatusName());
		setEndDate(that.getEndDate());
		setNDevicePurchase(that.getNDevicePurchase());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("id=[").append(id).append("] ");
		buffer.append("statusName=[").append(statusName).append("] ");
		buffer.append("endDate=[").append(endDate).append("] ");

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
		if (!(obj instanceof DeviceStatusRecord))
			return false;
		DeviceStatusRecord equalCheck = (DeviceStatusRecord) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
