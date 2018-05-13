package net.xidlims.domain;

import java.io.Serializable;

import java.lang.StringBuilder;

import java.math.BigDecimal;

import java.util.LinkedHashSet;
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
		@NamedQuery(name = "findAllAssetReceiveRecords", query = "select myAssetReceiveRecord from AssetReceiveRecord myAssetReceiveRecord"),
		@NamedQuery(name = "findAssetReceiveRecordById", query = "select myAssetReceiveRecord from AssetReceiveRecord myAssetReceiveRecord where myAssetReceiveRecord.id = ?1"),
		@NamedQuery(name = "findAssetReceiveRecordByPrimaryKey", query = "select myAssetReceiveRecord from AssetReceiveRecord myAssetReceiveRecord where myAssetReceiveRecord.id = ?1"),
		@NamedQuery(name = "findAssetReceiveRecordByQuantity", query = "select myAssetReceiveRecord from AssetReceiveRecord myAssetReceiveRecord where myAssetReceiveRecord.quantity = ?1"),
		@NamedQuery(name = "findAssetReceiveRecordByUnit", query = "select myAssetReceiveRecord from AssetReceiveRecord myAssetReceiveRecord where myAssetReceiveRecord.unit = ?1"),
		@NamedQuery(name = "findAssetReceiveRecordByUnitContaining", query = "select myAssetReceiveRecord from AssetReceiveRecord myAssetReceiveRecord where myAssetReceiveRecord.unit like ?1") })
@Table(catalog = "xidlims", name = "asset_receive_record")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "AssetReceiveRecord")
@XmlRootElement(namespace = "xidlims/net/xidlims/domain")
public class AssetReceiveRecord implements Serializable {
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
	 * ���쵥λ
	 * 
	 */
	@Column(name = "result")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer result;
	/**
	 * �깺��������
	 * 
	 */
	@Column(name = "allocation_status")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer allocationStatus;

	@Column(name = "unit")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String unit;
	/**
	 * ��������
	 * 
	 */

	@Column(name = "quantity", scale = 2, precision = 11)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal quantity;
	
	@Column(name = "if_public")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer ifPublic;

	
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "asset_id", referencedColumnName = "id") })
	@XmlTransient
	Asset asset;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "receive_id", referencedColumnName = "id") })
	@XmlTransient
	AssetReceive assetReceive;
	/**
	 */
	@OneToMany(mappedBy = "assetReceiveRecord", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.AssetReceiveAllocation> assetReceiveAllocations;

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
	 * ���쵥λ
	 * 
	 */
	public void setUnit(String unit) {
		this.unit = unit;
	}

	/**
	 * ���쵥λ
	 * 
	 */
	public String getUnit() {
		return this.unit;
	}

	
	public Integer getIfPublic() {
		return ifPublic;
	}

	public void setIfPublic(Integer ifPublic) {
		this.ifPublic = ifPublic;
	}

	/**
	 * ��������
	 * 
	 */
	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	/**
	 * ��������
	 * 
	 */
	public BigDecimal getQuantity() {
		return this.quantity;
	}
	
	public Integer getAllocationStatus() {
		return allocationStatus;
	}

	public void setAllocationStatus(Integer allocationStatus) {
		this.allocationStatus = allocationStatus;
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
	

	public Integer getResult() {
		return result;
	}

	public void setResult(Integer result) {
		this.result = result;
	}

	/**
	 */
	public void setAssetReceive(AssetReceive assetReceive) {
		this.assetReceive = assetReceive;
	}

	/**
	 */
	@JsonIgnore
	public AssetReceive getAssetReceive() {
		return assetReceive;
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
	public AssetReceiveRecord() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(AssetReceiveRecord that) {
		setId(that.getId());
		setUnit(that.getUnit());
		setQuantity(that.getQuantity());
		setAsset(that.getAsset());
		setAssetReceive(that.getAssetReceive());
		setAssetReceiveAllocations(new java.util.LinkedHashSet<net.xidlims.domain.AssetReceiveAllocation>(that.getAssetReceiveAllocations()));
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("id=[").append(id).append("] ");
		buffer.append("unit=[").append(unit).append("] ");
		buffer.append("quantity=[").append(quantity).append("] ");

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
		if (!(obj instanceof AssetReceiveRecord))
			return false;
		AssetReceiveRecord equalCheck = (AssetReceiveRecord) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
