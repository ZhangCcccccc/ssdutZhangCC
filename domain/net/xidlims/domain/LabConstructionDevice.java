package net.xidlims.domain;

import java.io.Serializable;

import java.lang.StringBuilder;

import java.math.BigDecimal;

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
		@NamedQuery(name = "findAllLabConstructionDevices", query = "select myLabConstructionDevice from LabConstructionDevice myLabConstructionDevice"),
		@NamedQuery(name = "findLabConstructionDeviceByArrivalTime", query = "select myLabConstructionDevice from LabConstructionDevice myLabConstructionDevice where myLabConstructionDevice.arrivalTime = ?1"),
		@NamedQuery(name = "findLabConstructionDeviceByComments", query = "select myLabConstructionDevice from LabConstructionDevice myLabConstructionDevice where myLabConstructionDevice.comments = ?1"),
		@NamedQuery(name = "findLabConstructionDeviceByDeviceModel", query = "select myLabConstructionDevice from LabConstructionDevice myLabConstructionDevice where myLabConstructionDevice.deviceModel = ?1"),
		@NamedQuery(name = "findLabConstructionDeviceByDeviceModelContaining", query = "select myLabConstructionDevice from LabConstructionDevice myLabConstructionDevice where myLabConstructionDevice.deviceModel like ?1"),
		@NamedQuery(name = "findLabConstructionDeviceByDeviceName", query = "select myLabConstructionDevice from LabConstructionDevice myLabConstructionDevice where myLabConstructionDevice.deviceName = ?1"),
		@NamedQuery(name = "findLabConstructionDeviceByDeviceNameContaining", query = "select myLabConstructionDevice from LabConstructionDevice myLabConstructionDevice where myLabConstructionDevice.deviceName like ?1"),
		@NamedQuery(name = "findLabConstructionDeviceByDevicePriceRef", query = "select myLabConstructionDevice from LabConstructionDevice myLabConstructionDevice where myLabConstructionDevice.devicePriceRef = ?1"),
		@NamedQuery(name = "findLabConstructionDeviceByDevicePriceSig", query = "select myLabConstructionDevice from LabConstructionDevice myLabConstructionDevice where myLabConstructionDevice.devicePriceSig = ?1"),
		@NamedQuery(name = "findLabConstructionDeviceByDeviceQuantity", query = "select myLabConstructionDevice from LabConstructionDevice myLabConstructionDevice where myLabConstructionDevice.deviceQuantity = ?1"),
		@NamedQuery(name = "findLabConstructionDeviceById", query = "select myLabConstructionDevice from LabConstructionDevice myLabConstructionDevice where myLabConstructionDevice.id = ?1"),
		@NamedQuery(name = "findLabConstructionDeviceByPrimaryKey", query = "select myLabConstructionDevice from LabConstructionDevice myLabConstructionDevice where myLabConstructionDevice.id = ?1"),
		@NamedQuery(name = "findLabConstructionDeviceByTag", query = "select myLabConstructionDevice from LabConstructionDevice myLabConstructionDevice where myLabConstructionDevice.tag = ?1") })
@Table(catalog = "xidlims", name = "lab_construction_device")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "LabConstructionDevice")
public class LabConstructionDevice implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ʵ���ҽ���-��Ŀ����?�깺�豸��
	 * 
	 */

	@Column(name = "id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@XmlElement
	Integer id;
	/**
	 * ��Ʒ���
	 * 
	 */

	@Column(name = "device_name")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String deviceName;
	/**
	 * ����ͺ�
	 * 
	 */

	@Column(name = "device_model")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String deviceModel;
	/**
	 * ����
	 * 
	 */

	@Column(name = "device_quantity")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer deviceQuantity;
	/**
	 * �豸����
	 * 
	 */

	@Column(name = "device_price_sig", scale = 2, precision = 20)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal devicePriceSig;
	/**
	 * �ο��۸�
	 * 
	 */

	@Column(name = "device_price_ref", scale = 2, precision = 20)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal devicePriceRef;
	/**
	 * Ҫ�󵽻�ʱ��
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "arrival_time")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar arrivalTime;
	/**
	 * ��ע���Ƽ�����Դ�ȣ�
	 * 
	 */

	@Column(name = "comments", columnDefinition = "TEXT")
	@Basic(fetch = FetchType.EAGER)
	@Lob
	@XmlElement
	String comments;
	/**
	 * ��־λ��1 purchase  2 funding��
	 * 
	 */

	@Column(name = "tag")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer tag;

	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "lab_construction_project", referencedColumnName = "id", nullable = false) })
	@XmlTransient
	LabConstructionProject labConstructionProject;

	/**
	 * ʵ���ҽ���-��Ŀ����?�깺�豸��
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * ʵ���ҽ���-��Ŀ����?�깺�豸��
	 * 
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * ��Ʒ���
	 * 
	 */
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	/**
	 * ��Ʒ���
	 * 
	 */
	public String getDeviceName() {
		return this.deviceName;
	}

	/**
	 * ����ͺ�
	 * 
	 */
	public void setDeviceModel(String deviceModel) {
		this.deviceModel = deviceModel;
	}

	/**
	 * ����ͺ�
	 * 
	 */
	public String getDeviceModel() {
		return this.deviceModel;
	}

	/**
	 * ����
	 * 
	 */
	public void setDeviceQuantity(Integer deviceQuantity) {
		this.deviceQuantity = deviceQuantity;
	}

	/**
	 * ����
	 * 
	 */
	public Integer getDeviceQuantity() {
		return this.deviceQuantity;
	}

	/**
	 * �豸����
	 * 
	 */
	public void setDevicePriceSig(BigDecimal devicePriceSig) {
		this.devicePriceSig = devicePriceSig;
	}

	/**
	 * �豸����
	 * 
	 */
	public BigDecimal getDevicePriceSig() {
		return this.devicePriceSig;
	}

	/**
	 * �ο��۸�
	 * 
	 */
	public void setDevicePriceRef(BigDecimal devicePriceRef) {
		this.devicePriceRef = devicePriceRef;
	}

	/**
	 * �ο��۸�
	 * 
	 */
	public BigDecimal getDevicePriceRef() {
		return this.devicePriceRef;
	}

	/**
	 * Ҫ�󵽻�ʱ��
	 * 
	 */
	public void setArrivalTime(Calendar arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	/**
	 * Ҫ�󵽻�ʱ��
	 * 
	 */
	public Calendar getArrivalTime() {
		return this.arrivalTime;
	}

	/**
	 * ��ע���Ƽ�����Դ�ȣ�
	 * 
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}

	/**
	 * ��ע���Ƽ�����Դ�ȣ�
	 * 
	 */
	public String getComments() {
		return this.comments;
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
	public LabConstructionDevice() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(LabConstructionDevice that) {
		setId(that.getId());
		setDeviceName(that.getDeviceName());
		setDeviceModel(that.getDeviceModel());
		setDeviceQuantity(that.getDeviceQuantity());
		setDevicePriceSig(that.getDevicePriceSig());
		setDevicePriceRef(that.getDevicePriceRef());
		setArrivalTime(that.getArrivalTime());
		setComments(that.getComments());
		setTag(that.getTag());
		setLabConstructionProject(that.getLabConstructionProject());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("id=[").append(id).append("] ");
		buffer.append("deviceName=[").append(deviceName).append("] ");
		buffer.append("deviceModel=[").append(deviceModel).append("] ");
		buffer.append("deviceQuantity=[").append(deviceQuantity).append("] ");
		buffer.append("devicePriceSig=[").append(devicePriceSig).append("] ");
		buffer.append("devicePriceRef=[").append(devicePriceRef).append("] ");
		buffer.append("arrivalTime=[").append(arrivalTime).append("] ");
		buffer.append("comments=[").append(comments).append("] ");
		buffer.append("tag=[").append(tag).append("] ");

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
		if (!(obj instanceof LabConstructionDevice))
			return false;
		LabConstructionDevice equalCheck = (LabConstructionDevice) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
