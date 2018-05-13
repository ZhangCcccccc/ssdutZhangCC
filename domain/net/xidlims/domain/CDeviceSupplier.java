package net.xidlims.domain;

import java.io.Serializable;

import java.lang.StringBuilder;

import java.util.LinkedHashSet;
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
		@NamedQuery(name = "findAllCDeviceSuppliers", query = "select myCDeviceSupplier from CDeviceSupplier myCDeviceSupplier"),
		@NamedQuery(name = "findCDeviceSupplierByDevicName", query = "select myCDeviceSupplier from CDeviceSupplier myCDeviceSupplier where myCDeviceSupplier.devicName = ?1"),
		@NamedQuery(name = "findCDeviceSupplierByDevicNameContaining", query = "select myCDeviceSupplier from CDeviceSupplier myCDeviceSupplier where myCDeviceSupplier.devicName like ?1"),
		@NamedQuery(name = "findCDeviceSupplierById", query = "select myCDeviceSupplier from CDeviceSupplier myCDeviceSupplier where myCDeviceSupplier.id = ?1"),
		@NamedQuery(name = "findCDeviceSupplierByPrimaryKey", query = "select myCDeviceSupplier from CDeviceSupplier myCDeviceSupplier where myCDeviceSupplier.id = ?1") })
@Table(catalog = "xidlims", name = "c_device_supplier")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "CDeviceSupplier")
@XmlRootElement(namespace = "xidlims/net/xidlims/domain")
public class CDeviceSupplier implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * �豸��Ӧ��
	 * 
	 */

	@Column(name = "id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@XmlElement
	Integer id;
	/**
	 * ��Ӧ������
	 * 
	 */

	@Column(name = "devic_name")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String devicName;

	/**
	 */
	@OneToMany(mappedBy = "CDeviceSupplier", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.DevicePurchaseDetailCSupplier> devicePurchaseDetailCSuppliers;

	/**
	 * �豸��Ӧ��
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * �豸��Ӧ��
	 * 
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * ��Ӧ������
	 * 
	 */
	public void setDevicName(String devicName) {
		this.devicName = devicName;
	}

	/**
	 * ��Ӧ������
	 * 
	 */
	public String getDevicName() {
		return this.devicName;
	}

	/**
	 */
	public void setDevicePurchaseDetailCSuppliers(Set<DevicePurchaseDetailCSupplier> devicePurchaseDetailCSuppliers) {
		this.devicePurchaseDetailCSuppliers = devicePurchaseDetailCSuppliers;
	}

	/**
	 */
	@JsonIgnore
	public Set<DevicePurchaseDetailCSupplier> getDevicePurchaseDetailCSuppliers() {
		if (devicePurchaseDetailCSuppliers == null) {
			devicePurchaseDetailCSuppliers = new java.util.LinkedHashSet<net.xidlims.domain.DevicePurchaseDetailCSupplier>();
		}
		return devicePurchaseDetailCSuppliers;
	}

	/**
	 */
	public CDeviceSupplier() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(CDeviceSupplier that) {
		setId(that.getId());
		setDevicName(that.getDevicName());
		setDevicePurchaseDetailCSuppliers(new java.util.LinkedHashSet<net.xidlims.domain.DevicePurchaseDetailCSupplier>(that.getDevicePurchaseDetailCSuppliers()));
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("id=[").append(id).append("] ");
		buffer.append("devicName=[").append(devicName).append("] ");

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
		if (!(obj instanceof CDeviceSupplier))
			return false;
		CDeviceSupplier equalCheck = (CDeviceSupplier) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
