package net.xidlims.domain;

import java.io.Serializable;

import java.lang.StringBuilder;

import java.math.BigDecimal;

import java.util.Calendar;
import java.util.Set;

import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.codehaus.jackson.annotate.JsonIgnore;

import javax.xml.bind.annotation.*;

import javax.persistence.*;

/**
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "findAllAssetAdjustRecords", query = "select myAssetAdjustRecord from AssetAdjustRecord myAssetAdjustRecord"),
		@NamedQuery(name = "findAssetAdjustRecordByDate", query = "select myAssetAdjustRecord from AssetAdjustRecord myAssetAdjustRecord where myAssetAdjustRecord.date = ?1"),
		@NamedQuery(name = "findAssetAdjustRecordById", query = "select myAssetAdjustRecord from AssetAdjustRecord myAssetAdjustRecord where myAssetAdjustRecord.id = ?1"),
		@NamedQuery(name = "findAssetAdjustRecordByPrimaryKey", query = "select myAssetAdjustRecord from AssetAdjustRecord myAssetAdjustRecord where myAssetAdjustRecord.id = ?1"),
		@NamedQuery(name = "findAssetAdjustRecordByQuantity", query = "select myAssetAdjustRecord from AssetAdjustRecord myAssetAdjustRecord where myAssetAdjustRecord.quantity = ?1"),
		@NamedQuery(name = "findAssetAdjustRecordByType", query = "select myAssetAdjustRecord from AssetAdjustRecord myAssetAdjustRecord where myAssetAdjustRecord.type = ?1"),
		@NamedQuery(name = "findAssetAdjustRecordByUnit", query = "select myAssetAdjustRecord from AssetAdjustRecord myAssetAdjustRecord where myAssetAdjustRecord.unit = ?1"),
		@NamedQuery(name = "findAssetAdjustRecordByUnitContaining", query = "select myAssetAdjustRecord from AssetAdjustRecord myAssetAdjustRecord where myAssetAdjustRecord.unit like ?1") })
@Table(catalog = "xidlims", name = "asset_adjust_record")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "AssetAdjustRecord")
public class AssetAdjustRecord implements Serializable {
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

	@Column(name = "quantity", scale = 2, precision = 11)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal quantity;
	/**
	 * ��������,1���ӣ�0����
	 * 
	 */

	@Column(name = "type")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer type;
	/**
	 * ����λ
	 * 
	 */

	@Column(name = "unit")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String unit;
	/**
	 * ����ʱ��
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar date;

	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "access_record_id", referencedColumnName = "id") })
	@XmlTransient
	AssetCabinetWarehouseAccessRecord assetCabinetWarehouseAccessRecord;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "asset_id", referencedColumnName = "id") })
	@XmlTransient
	Asset asset;

	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "manager", referencedColumnName = "username") })
	@XmlTransient
	User user;
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

	/**
	 * ��������,1���ӣ�0����
	 * 
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * ��������,1���ӣ�0����
	 * 
	 */
	public Integer getType() {
		return this.type;
	}

	/**
	 * ����λ
	 * 
	 */
	public void setUnit(String unit) {
		this.unit = unit;
	}

	/**
	 * ����λ
	 * 
	 */
	public String getUnit() {
		return this.unit;
	}

	/**
	 * ����ʱ��
	 * 
	 */
	public void setDate(Calendar date) {
		this.date = date;
	}

	/**
	 * ����ʱ��
	 * 
	 */
	public Calendar getDate() {
		return this.date;
	}
	/**
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 */
	@JsonIgnore
	public User getUser() {
		return user;
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
	public AssetAdjustRecord() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(AssetAdjustRecord that) {
		setId(that.getId());
		setQuantity(that.getQuantity());
		setType(that.getType());
		setUnit(that.getUnit());
		setDate(that.getDate());
		setAssetCabinetWarehouseAccessRecord(that.getAssetCabinetWarehouseAccessRecord());
		setAsset(that.getAsset());
		setUser(that.getUser());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("id=[").append(id).append("] ");
		buffer.append("quantity=[").append(quantity).append("] ");
		buffer.append("type=[").append(type).append("] ");
		buffer.append("unit=[").append(unit).append("] ");
		buffer.append("date=[").append(date).append("] ");

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
		if (!(obj instanceof AssetAdjustRecord))
			return false;
		AssetAdjustRecord equalCheck = (AssetAdjustRecord) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
