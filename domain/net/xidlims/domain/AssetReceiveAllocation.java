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
		@NamedQuery(name = "findAllAssetReceiveAllocations", query = "select myAssetReceiveAllocation from AssetReceiveAllocation myAssetReceiveAllocation"),
		@NamedQuery(name = "findAssetReceiveAllocationById", query = "select myAssetReceiveAllocation from AssetReceiveAllocation myAssetReceiveAllocation where myAssetReceiveAllocation.id = ?1"),
		@NamedQuery(name = "findAssetReceiveAllocationByMem", query = "select myAssetReceiveAllocation from AssetReceiveAllocation myAssetReceiveAllocation where myAssetReceiveAllocation.mem = ?1"),
		@NamedQuery(name = "findAssetReceiveAllocationByMemContaining", query = "select myAssetReceiveAllocation from AssetReceiveAllocation myAssetReceiveAllocation where myAssetReceiveAllocation.mem like ?1"),
		@NamedQuery(name = "findAssetReceiveAllocationByPrimaryKey", query = "select myAssetReceiveAllocation from AssetReceiveAllocation myAssetReceiveAllocation where myAssetReceiveAllocation.id = ?1") })
@Table(catalog = "xidlims", name = "asset_receive_allocation")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "AssetReceiveAllocation")
public class AssetReceiveAllocation implements Serializable {
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
	 * ��ע
	 * 
	 */
	@Column(name = "position")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String position; 
	
	@Column(name = "mem")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String mem;
	
	@Column(name = "quantity", scale = 2, precision = 11)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal Quantity;
	
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "warehouse_id", referencedColumnName = "id") })
	@XmlTransient
	AssetCabinetWarehouse assetCabinetWarehouse;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "asset_id", referencedColumnName = "id") })
	@XmlTransient
	Asset asset;
	

	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "receive_record_id", referencedColumnName = "id") })
	@XmlTransient
	AssetReceiveRecord assetReceiveRecord;
	
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "access_record_id", referencedColumnName = "id") })
	@XmlTransient
	AssetCabinetWarehouseAccessRecord assetCabinetWarehouseAccessRecord;
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
	public void setMem(String mem) {
		this.mem = mem;
	}

	/**
	 * ��ע
	 * 
	 */
	public String getMem() {
		return this.mem;
	}
	

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}


	/**
	 */
	public void setAssetCabinetWarehouse(AssetCabinetWarehouse assetCabinetWarehouse) {
		this.assetCabinetWarehouse = assetCabinetWarehouse;
	}
	
	public BigDecimal getQuantity() {
		return Quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		Quantity = quantity;
	}


	/**
	 */
	@JsonIgnore
	public AssetCabinetWarehouse getAssetCabinetWarehouse() {
		return assetCabinetWarehouse;
	}

	/**
	 */
	public void setAsset(Asset asset) {
		this.asset = asset;
	}

	/**
	 */
	@JsonIgnore
	public Asset getAsset() {
		return asset;
	}

	/**
	 */
	public void setAssetReceiveRecord(AssetReceiveRecord assetReceiveRecord) {
		this.assetReceiveRecord = assetReceiveRecord;
	}

	/**
	 */
	@JsonIgnore
	public AssetReceiveRecord getAssetReceiveRecord() {
		return assetReceiveRecord;
	}
	
	/**
	 */
	public void setAssetCabinetWarehouseAccessRecord(AssetCabinetWarehouseAccessRecord assetCabinetWarehouseAccessRecord) {
		this.assetCabinetWarehouseAccessRecord = assetCabinetWarehouseAccessRecord;
	}

	/**
	 */
	@JsonIgnore
	public AssetCabinetWarehouseAccessRecord getAssetCabinetWarehouseAccessRecord() {
		return assetCabinetWarehouseAccessRecord;
	}
	/**
	 */
	public AssetReceiveAllocation() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(AssetReceiveAllocation that) {
		setId(that.getId());
		setMem(that.getMem());
		setAssetCabinetWarehouse(that.getAssetCabinetWarehouse());
		setAsset(that.getAsset());
		setAssetReceiveRecord(that.getAssetReceiveRecord());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("id=[").append(id).append("] ");
		buffer.append("mem=[").append(mem).append("] ");

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
		if (!(obj instanceof AssetReceiveAllocation))
			return false;
		AssetReceiveAllocation equalCheck = (AssetReceiveAllocation) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
