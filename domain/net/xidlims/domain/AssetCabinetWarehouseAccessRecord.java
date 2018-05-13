package net.xidlims.domain;

import java.io.Serializable;

import java.lang.StringBuilder;

import java.math.BigDecimal;

import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.FetchType;
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
		@NamedQuery(name = "findAllAssetCabinetWarehouseAccessRecords", query = "select myAssetCabinetWarehouseAccessRecord from AssetCabinetWarehouseAccessRecord myAssetCabinetWarehouseAccessRecord"),
		@NamedQuery(name = "findAssetCabinetWarehouseAccessRecordByCabinetQuantity", query = "select myAssetCabinetWarehouseAccessRecord from AssetCabinetWarehouseAccessRecord myAssetCabinetWarehouseAccessRecord where myAssetCabinetWarehouseAccessRecord.cabinetQuantity = ?1"),
		@NamedQuery(name = "findAssetCabinetWarehouseAccessRecordById", query = "select myAssetCabinetWarehouseAccessRecord from AssetCabinetWarehouseAccessRecord myAssetCabinetWarehouseAccessRecord where myAssetCabinetWarehouseAccessRecord.id = ?1"),
		@NamedQuery(name = "findAssetCabinetWarehouseAccessRecordByPrimaryKey", query = "select myAssetCabinetWarehouseAccessRecord from AssetCabinetWarehouseAccessRecord myAssetCabinetWarehouseAccessRecord where myAssetCabinetWarehouseAccessRecord.id = ?1") })
@Table(catalog = "xidlims", name = "asset_cabinet_warehouse_access_record")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "AssetCabinetWarehouseAccessRecord")
public class AssetCabinetWarehouseAccessRecord implements Serializable {
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
	 * ��������
	 * 
	 */

	@Column(name = "cabinet_quantity", scale = 2, precision = 11)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal cabinetQuantity;
	
	@Column(name = "position")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String position; 
	/**
	 * ��������
	 * 
	 */

	@Column(name = "status")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer status;

	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "asset_id", referencedColumnName = "id") })
	@XmlTransient
	Asset asset;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "warehouse_id", referencedColumnName = "id") })
	@XmlTransient
	AssetCabinetWarehouse assetCabinetWarehouse;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "access_id", referencedColumnName = "id") })
	@XmlTransient
	AssetCabinetWarehouseAccess assetCabinetWarehouseAccess;

	/**
	 */
	@OneToMany(mappedBy = "assetCabinetWarehouseAccessRecord", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.AssetAdjustRecord> assetAdjustRecords;
	
	/**
	 */
	@OneToMany(mappedBy = "assetCabinetWarehouseAccessRecord", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.AssetReceiveAllocation> assetReceiveAllocations;
	
	/**
	 */
	@OneToMany(mappedBy = "assetCabinetWarehouseAccessRecord", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.OperationItemMaterialRecord> operationItemMaterialRecords;
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
	 * ��������
	 * 
	 */
	public void setCabinetQuantity(BigDecimal cabinetQuantity) {
		this.cabinetQuantity = cabinetQuantity;
	}

	/**
	 * ��������
	 * 
	 */
	public BigDecimal getCabinetQuantity() {
		return this.cabinetQuantity;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 */
	public void setAsset(Asset asset) {
		this.asset = asset;
	}
	
	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	/**
	 */
	@JsonIgnore
	public Asset getAsset() {
		return asset;
	}

	/**
	 */
	public void setAssetCabinetWarehouse(AssetCabinetWarehouse assetCabinetWarehouse) {
		this.assetCabinetWarehouse = assetCabinetWarehouse;
	}

	/**
	 */
	@JsonIgnore
	public AssetCabinetWarehouse getAssetCabinetWarehouse() {
		return assetCabinetWarehouse;
	}

	/**
	 */
	public void setAssetCabinetWarehouseAccess(AssetCabinetWarehouseAccess assetCabinetWarehouseAccess) {
		this.assetCabinetWarehouseAccess = assetCabinetWarehouseAccess;
	}

	/**
	 */
	@JsonIgnore
	public AssetCabinetWarehouseAccess getAssetCabinetWarehouseAccess() {
		return assetCabinetWarehouseAccess;
	}

	/**
	 */
	public void setAssetAdjustRecords(Set<AssetAdjustRecord> assetAdjustRecords) {
		this.assetAdjustRecords = assetAdjustRecords;
	}

	/**
	 */
	@JsonIgnore
	public Set<AssetAdjustRecord> getAssetAdjustRecords() {
		if (assetAdjustRecords == null) {
			assetAdjustRecords = new java.util.LinkedHashSet<net.xidlims.domain.AssetAdjustRecord>();
		}
		return assetAdjustRecords;
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
	public void setOperationItemMaterialRecords(Set<OperationItemMaterialRecord> operationItemMaterialRecords) {
		this.operationItemMaterialRecords = operationItemMaterialRecords;
	}

	/**
	 */
	@JsonIgnore
	public Set<OperationItemMaterialRecord> getOperationItemMaterialRecords() {
		if (operationItemMaterialRecords == null) {
			operationItemMaterialRecords = new java.util.LinkedHashSet<net.xidlims.domain.OperationItemMaterialRecord>();
		}
		return operationItemMaterialRecords;
	}
	/**
	 */
	public AssetCabinetWarehouseAccessRecord() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(AssetCabinetWarehouseAccessRecord that) {
		setId(that.getId());
		setCabinetQuantity(that.getCabinetQuantity());
		setAsset(that.getAsset());
		setAssetCabinetWarehouse(that.getAssetCabinetWarehouse());
		setAssetCabinetWarehouseAccess(that.getAssetCabinetWarehouseAccess());
		setAssetAdjustRecords(new java.util.LinkedHashSet<net.xidlims.domain.AssetAdjustRecord>(that.getAssetAdjustRecords()));
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("id=[").append(id).append("] ");
		buffer.append("cabinetQuantity=[").append(cabinetQuantity).append("] ");

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
		if (!(obj instanceof AssetCabinetWarehouseAccessRecord))
			return false;
		AssetCabinetWarehouseAccessRecord equalCheck = (AssetCabinetWarehouseAccessRecord) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
