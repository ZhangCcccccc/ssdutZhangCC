package net.xidlims.domain;

import java.io.Serializable;

import java.lang.StringBuilder;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import org.codehaus.jackson.annotate.JsonIgnore;

import javax.xml.bind.annotation.*;

import javax.persistence.*;

/**
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "findAllAssetCabinetWarehouses", query = "select myAssetCabinetWarehouse from AssetCabinetWarehouse myAssetCabinetWarehouse"),
		@NamedQuery(name = "findAssetCabinetWarehouseById", query = "select myAssetCabinetWarehouse from AssetCabinetWarehouse myAssetCabinetWarehouse where myAssetCabinetWarehouse.id = ?1"),
		@NamedQuery(name = "findAssetCabinetWarehouseByPrimaryKey", query = "select myAssetCabinetWarehouse from AssetCabinetWarehouse myAssetCabinetWarehouse where myAssetCabinetWarehouse.id = ?1"),
		@NamedQuery(name = "findAssetCabinetWarehouseByWarehouseCode", query = "select myAssetCabinetWarehouse from AssetCabinetWarehouse myAssetCabinetWarehouse where myAssetCabinetWarehouse.warehouseCode = ?1"),
		@NamedQuery(name = "findAssetCabinetWarehouseByWarehouseCodeContaining", query = "select myAssetCabinetWarehouse from AssetCabinetWarehouse myAssetCabinetWarehouse where myAssetCabinetWarehouse.warehouseCode like ?1"),
		@NamedQuery(name = "findAssetCabinetWarehouseByWarehouseName", query = "select myAssetCabinetWarehouse from AssetCabinetWarehouse myAssetCabinetWarehouse where myAssetCabinetWarehouse.warehouseName = ?1"),
		@NamedQuery(name = "findAssetCabinetWarehouseByWarehouseNameContaining", query = "select myAssetCabinetWarehouse from AssetCabinetWarehouse myAssetCabinetWarehouse where myAssetCabinetWarehouse.warehouseName like ?1") })
@Table(catalog = "xidlims", name = "asset_cabinet_warehouse")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "AssetCabinetWarehouse")
@XmlRootElement(namespace = "xidlims/net/xidlims/domain")
public class AssetCabinetWarehouse implements Serializable {
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
	 * �ֿ���
	 * 
	 */

	@Column(name = "warehouse_code")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String warehouseCode;
	/**
	 * �ֿ����
	 * 
	 */

	@Column(name = "warehouse_name")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String warehouseName;

	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "asset_cabinet_id", referencedColumnName = "id") })
	@XmlTransient
	AssetCabinet assetCabinet;
	
	/**
	 */
	@OneToMany(mappedBy = "assetCabinetWarehouse", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.AssetOpenLog> assetOpenLogs;
	
	/**
	 */
	@OneToMany(mappedBy = "assetCabinetWarehouse", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.AssetReceiveAllocation> assetReceiveAllocations;
	
	/**
	 */
	@OneToMany(mappedBy = "assetCabinetWarehouse", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.AssetCabinetWarehouseAccessRecord> assetCabinetWarehouseAccessRecords;

	/**
	 */

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
	 * �ֿ���
	 * 
	 */
	public void setWarehouseCode(String warehouseCode) {
		this.warehouseCode = warehouseCode;
	}

	/**
	 * �ֿ���
	 * 
	 */
	public String getWarehouseCode() {
		return this.warehouseCode;
	}

	/**
	 * �ֿ����
	 * 
	 */
	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}

	/**
	 * �ֿ����
	 * 
	 */
	public String getWarehouseName() {
		return this.warehouseName;
	}

	/**
	 */
	public void setAssetCabinet(AssetCabinet assetCabinet) {
		this.assetCabinet = assetCabinet;
	}

	/**
	 */
	@JsonIgnore
	public AssetCabinet getAssetCabinet() {
		return assetCabinet;
	}
	/**
	 */
	public void setAssetReceiveAllocations(Set<AssetReceiveAllocation> assetReceiveAllocations) {
		this.assetReceiveAllocations = assetReceiveAllocations;
	}

	/**
	 */
	@JsonIgnore
	public Set<AssetReceiveAllocation> getAssetReceiveAllocations() {
		if (assetReceiveAllocations == null) {
			assetReceiveAllocations = new java.util.LinkedHashSet<net.xidlims.domain.AssetReceiveAllocation>();
		}
		return assetReceiveAllocations;
	}

	/**
	 */
	public void setAssetOpenLogs(Set<AssetOpenLog> assetOpenLogs) {
		this.assetOpenLogs = assetOpenLogs;
	}

	/**
	 */
	@JsonIgnore
	public Set<AssetOpenLog> getAssetOpenLogs() {
		if (assetOpenLogs == null) {
			assetOpenLogs = new java.util.LinkedHashSet<net.xidlims.domain.AssetOpenLog>();
		}
		return assetOpenLogs;
	}
	/**
	 */
	public void setAssetCabinetWarehouseAccessRecords(Set<AssetCabinetWarehouseAccessRecord> assetCabinetWarehouseAccessRecords) {
		this.assetCabinetWarehouseAccessRecords = assetCabinetWarehouseAccessRecords;
	}

	/**
	 */
	@JsonIgnore
	public Set<AssetCabinetWarehouseAccessRecord> getAssetCabinetWarehouseAccessRecords() {
		if (assetCabinetWarehouseAccessRecords == null) {
			assetCabinetWarehouseAccessRecords = new java.util.LinkedHashSet<net.xidlims.domain.AssetCabinetWarehouseAccessRecord>();
		}
		return assetCabinetWarehouseAccessRecords;
	}
	/**
	 */
	public AssetCabinetWarehouse() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(AssetCabinetWarehouse that) {
		setId(that.getId());
		setWarehouseCode(that.getWarehouseCode());
		setWarehouseName(that.getWarehouseName());
		setAssetCabinet(that.getAssetCabinet());
		setAssetReceiveAllocations(new java.util.LinkedHashSet<net.xidlims.domain.AssetReceiveAllocation>(that.getAssetReceiveAllocations()));
		setAssetOpenLogs(new java.util.LinkedHashSet<net.xidlims.domain.AssetOpenLog>(that.getAssetOpenLogs()));
		setAssetCabinetWarehouseAccessRecords(new java.util.LinkedHashSet<net.xidlims.domain.AssetCabinetWarehouseAccessRecord>(that.getAssetCabinetWarehouseAccessRecords()));
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("id=[").append(id).append("] ");
		buffer.append("warehouseCode=[").append(warehouseCode).append("] ");
		buffer.append("warehouseName=[").append(warehouseName).append("] ");

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
		if (!(obj instanceof AssetCabinetWarehouse))
			return false;
		AssetCabinetWarehouse equalCheck = (AssetCabinetWarehouse) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
