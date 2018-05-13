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
		@NamedQuery(name = "findAllDevicePurchaseDetailCSuppliers", query = "select myDevicePurchaseDetailCSupplier from DevicePurchaseDetailCSupplier myDevicePurchaseDetailCSupplier"),
		@NamedQuery(name = "findDevicePurchaseDetailCSupplierById", query = "select myDevicePurchaseDetailCSupplier from DevicePurchaseDetailCSupplier myDevicePurchaseDetailCSupplier where myDevicePurchaseDetailCSupplier.id = ?1"),
		@NamedQuery(name = "findDevicePurchaseDetailCSupplierByPrimaryKey", query = "select myDevicePurchaseDetailCSupplier from DevicePurchaseDetailCSupplier myDevicePurchaseDetailCSupplier where myDevicePurchaseDetailCSupplier.id = ?1") })
@Table(catalog = "xidlims", name = "device_purchase_detail_c_supplier")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "DevicePurchaseDetailCSupplier")
public class DevicePurchaseDetailCSupplier implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ��Ӧ��--�豸��
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
	@JoinColumns({ @JoinColumn(name = "device_id", referencedColumnName = "id") })
	@XmlTransient
	NDevicePurchaseDetail NDevicePurchaseDetail;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "supplier_id", referencedColumnName = "id") })
	@XmlTransient
	CDeviceSupplier CDeviceSupplier;

	/**
	 * ��Ӧ��--�豸��
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * ��Ӧ��--�豸��
	 * 
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 */
	public void setNDevicePurchaseDetail(NDevicePurchaseDetail NDevicePurchaseDetail) {
		this.NDevicePurchaseDetail = NDevicePurchaseDetail;
	}

	/**
	 */
	@JsonIgnore
	public NDevicePurchaseDetail getNDevicePurchaseDetail() {
		return NDevicePurchaseDetail;
	}

	/**
	 */
	public void setCDeviceSupplier(CDeviceSupplier CDeviceSupplier) {
		this.CDeviceSupplier = CDeviceSupplier;
	}

	/**
	 */
	@JsonIgnore
	public CDeviceSupplier getCDeviceSupplier() {
		return CDeviceSupplier;
	}

	/**
	 */
	public DevicePurchaseDetailCSupplier() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(DevicePurchaseDetailCSupplier that) {
		setId(that.getId());
		setNDevicePurchaseDetail(that.getNDevicePurchaseDetail());
		setCDeviceSupplier(that.getCDeviceSupplier());
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
		if (!(obj instanceof DevicePurchaseDetailCSupplier))
			return false;
		DevicePurchaseDetailCSupplier equalCheck = (DevicePurchaseDetailCSupplier) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
