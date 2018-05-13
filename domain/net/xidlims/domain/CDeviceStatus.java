package net.xidlims.domain;

import java.io.Serializable;

import java.lang.StringBuilder;

import java.util.Calendar;
import java.util.LinkedHashSet;
import java.util.Set;

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
		@NamedQuery(name = "findAllCDeviceStatuss", query = "select myCDeviceStatus from CDeviceStatus myCDeviceStatus"),
		@NamedQuery(name = "findCDeviceStatusById", query = "select myCDeviceStatus from CDeviceStatus myCDeviceStatus where myCDeviceStatus.id = ?1"),
		@NamedQuery(name = "findCDeviceStatusByPrimaryKey", query = "select myCDeviceStatus from CDeviceStatus myCDeviceStatus where myCDeviceStatus.id = ?1"),
		@NamedQuery(name = "findCDeviceStatusByStatusName", query = "select myCDeviceStatus from CDeviceStatus myCDeviceStatus where myCDeviceStatus.statusName = ?1"),
		@NamedQuery(name = "findCDeviceStatusByStatusNameContaining", query = "select myCDeviceStatus from CDeviceStatus myCDeviceStatus where myCDeviceStatus.statusName like ?1") })
@Table(catalog = "xidlims", name = "c_device_status")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "CDeviceStatus")
@XmlRootElement(namespace = "xidlims/net/xidlims/domain")
public class CDeviceStatus implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 */

	@Column(name = "id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@XmlElement
	Integer id;
	/**
	 * �豸״̬
	 * 
	 */

	@Column(name = "status_name")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String statusName;
	
	@Column(name = "flag")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer flag;
	
	@Column(name = "interval_date")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer intervalDate;
	
	@Column(name = "status_order")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer statusOrder;

	/**
	 */
	@OneToMany(mappedBy = "CDeviceStatus", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.NDevicePurchase> NDevicePurchases;

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
	 * �豸״̬
	 * 
	 */
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	/**
	 * �豸״̬
	 * 
	 */
	public String getStatusName() {
		return this.statusName;
	}

	/**
	 */
	public void setNDevicePurchases(Set<NDevicePurchase> NDevicePurchases) {
		this.NDevicePurchases = NDevicePurchases;
	}

	/**
	 */
	@JsonIgnore
	public Set<NDevicePurchase> getNDevicePurchases() {
		if (NDevicePurchases == null) {
			NDevicePurchases = new java.util.LinkedHashSet<net.xidlims.domain.NDevicePurchase>();
		}
		return NDevicePurchases;
	}

	/**
	 */
	public CDeviceStatus() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(CDeviceStatus that) {
		setId(that.getId());
		setStatusName(that.getStatusName());
		setNDevicePurchases(new java.util.LinkedHashSet<net.xidlims.domain.NDevicePurchase>(that.getNDevicePurchases()));
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("id=[").append(id).append("] ");
		buffer.append("statusName=[").append(statusName).append("] ");

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
		if (!(obj instanceof CDeviceStatus))
			return false;
		CDeviceStatus equalCheck = (CDeviceStatus) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
	
	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public Integer getIntervalDate() {
		return intervalDate;
	}

	public void setIntervalDate(Integer intervalDate) {
		this.intervalDate = intervalDate;
	}
	
	public Integer getStatusOrder() {
		return statusOrder;
	}

	public void setStatusOrder(Integer statusOrder) {
		this.statusOrder = statusOrder;
	}
}
