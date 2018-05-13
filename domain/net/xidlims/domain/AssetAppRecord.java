package net.xidlims.domain;

import java.io.Serializable;

import java.lang.StringBuilder;

import java.math.BigDecimal;

import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
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
		@NamedQuery(name = "findAllAssetAppRecords", query = "select myAssetAppRecord from AssetAppRecord myAssetAppRecord"),
		@NamedQuery(name = "findAssetAppRecordByAppBrand", query = "select myAssetAppRecord from AssetAppRecord myAssetAppRecord where myAssetAppRecord.appBrand = ?1"),
		@NamedQuery(name = "findAssetAppRecordByAppBrandContaining", query = "select myAssetAppRecord from AssetAppRecord myAssetAppRecord where myAssetAppRecord.appBrand like ?1"),
		@NamedQuery(name = "findAssetAppRecordByAppPrice", query = "select myAssetAppRecord from AssetAppRecord myAssetAppRecord where myAssetAppRecord.appPrice = ?1"),
		@NamedQuery(name = "findAssetAppRecordByAppQuantity", query = "select myAssetAppRecord from AssetAppRecord myAssetAppRecord where myAssetAppRecord.appQuantity = ?1"),
		@NamedQuery(name = "findAssetAppRecordByAppSpec", query = "select myAssetAppRecord from AssetAppRecord myAssetAppRecord where myAssetAppRecord.appSpec = ?1"),
		@NamedQuery(name = "findAssetAppRecordByAppSpecContaining", query = "select myAssetAppRecord from AssetAppRecord myAssetAppRecord where myAssetAppRecord.appSpec like ?1"),
		@NamedQuery(name = "findAssetAppRecordByAppSupplier", query = "select myAssetAppRecord from AssetAppRecord myAssetAppRecord where myAssetAppRecord.appSupplier = ?1"),
		@NamedQuery(name = "findAssetAppRecordByAppUsage", query = "select myAssetAppRecord from AssetAppRecord myAssetAppRecord where myAssetAppRecord.appUsage = ?1"),
		@NamedQuery(name = "findAssetAppRecordByAppUsageContaining", query = "select myAssetAppRecord from AssetAppRecord myAssetAppRecord where myAssetAppRecord.appUsage like ?1"),
		@NamedQuery(name = "findAssetAppRecordByAuditStatus", query = "select myAssetAppRecord from AssetAppRecord myAssetAppRecord where myAssetAppRecord.auditStatus = ?1"),
		@NamedQuery(name = "findAssetAppRecordById", query = "select myAssetAppRecord from AssetAppRecord myAssetAppRecord where myAssetAppRecord.id = ?1"),
		@NamedQuery(name = "findAssetAppRecordByMem", query = "select myAssetAppRecord from AssetAppRecord myAssetAppRecord where myAssetAppRecord.mem = ?1"),
		@NamedQuery(name = "findAssetAppRecordByPrimaryKey", query = "select myAssetAppRecord from AssetAppRecord myAssetAppRecord where myAssetAppRecord.id = ?1") })
@Table(catalog = "xidlims", name = "asset_app_record")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "AssetAppRecord")
public class AssetAppRecord implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * �������ʼ�¼��
	 * 
	 */

	@Column(name = "id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@XmlElement
	Integer id;
	/**
	 * ʵ�������  ��� lab_room
	 * 
	 */


	@Column(name = "app_usage")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String appUsage;
	/**
	 * �깺��������
	 * 
	 */
	

	@Column(name = "app_quantity")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer appQuantity;
	/**
	 * ���ʵ���
	 * 
	 */

	@Column(name = "app_price", scale = 10, precision = 16)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal appPrice;
	/**
	 * ��Ӧ��
	 * 
	 */

	@Column(name = "app_supplier")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String appSupplier;
	/**
	 * ����Ʒ��
	 * 
	 */

	@Column(name = "app_brand")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String appBrand;
	
	/**
	 * ���ʻ���
	 * 
	 */

	@Column(name = "style_number")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String styleNumber;
	/**
	 * �������
	 * 
	 */

	@Column(name = "app_spec")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String appSpec;
	/**
	 * ���״̬ 1���ͨ�� 2���� 3����� 4δ���
	 * 
	 */

	@Column(name = "audit_status")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer auditStatus;
	
	@Column(name = "result")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer result;
	
	/**
	 * ����״̬��1����or2δ����
	 * 
	 */

	@Column(name = "stock_status")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer stockStatus; 
	
	/**
	 * ��ע
	 * 
	 */

	@Column(name = "mem", columnDefinition = "TEXT")
	@Basic(fetch = FetchType.EAGER)
	@Lob
	@XmlElement
	String mem;
	
	/**
	 * ����״̬��1����or2δ����
	 * 
	 */

	@Column(name = "select_number")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String selectNumber;
	
	@Column(name = "app_finalSpec")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String appfinalSpec;

	

	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "lab_id", referencedColumnName = "id") })
	@XmlTransient
	LabRoom labRoom;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "app_id", referencedColumnName = "id") })
	@XmlTransient
	AssetApp assetApp;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "asset_id", referencedColumnName = "id") })
	@XmlTransient
	Asset asset;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "academy_number", referencedColumnName = "academy_number") })
	@XmlTransient
	SchoolAcademy schoolAcademy;
	/**
	 */
	 
	@OneToMany(mappedBy = "assetAppRecord", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.AssetCabinetWarehouseAccess> assetCabinetWarehouseAccesses;
	
	public String getAppfinalSpec() {
		return appfinalSpec;
	}

	public void setAppfinalSpec(String appfinalSpec) {
		this.appfinalSpec = appfinalSpec;
	}
	/**
	 * �������ʼ�¼��
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * �������ʼ�¼��
	 * 
	 */
	public Integer getId() {
		return this.id;
	}

	
	public String getSelectNumber() {
		return selectNumber;
	}

	public void setSelectNumber(String selectNumber) {
		this.selectNumber = selectNumber;
	}
	/**
	 * ʵ�������  ��� lab_room
	 * 
	 */

	/**
	 * ������;
	 * 
	 */
	public void setAppUsage(String appUsage) {
		this.appUsage = appUsage;
	}

	/**
	 * ������;
	 * 
	 */
	public String getAppUsage() {
		return this.appUsage;
	}

	/**
	 * �깺��������
	 * 
	 */
	public void setAppQuantity(Integer appQuantity) {
		this.appQuantity = appQuantity;
	}

	/**
	 * �깺��������
	 * 
	 */
	public Integer getAppQuantity() {
		return this.appQuantity;
	}

	/**
	 * ���ʵ���
	 * 
	 */
	public void setAppPrice(BigDecimal appPrice) {
		this.appPrice = appPrice;
	}

	/**
	 * ���ʵ���
	 * 
	 */
	public BigDecimal getAppPrice() {
		return this.appPrice;
	}
	/**
	 * ���ʻ���
	 * 
	 */
	public void setStyleNumber(String styleNumber) {
		this.styleNumber = styleNumber;
	}

	/**
	 * ���ʻ���
	 * 
	 */
	public String getStyleNumber() {
		return this.styleNumber;
	}

	/**
	 * ��Ӧ��
	 * 
	 */
	public void setAppSupplier(String appSupplier) {
		this.appSupplier = appSupplier;
	}

	/**
	 * ��Ӧ��
	 * 
	 */
	public String getAppSupplier() {
		return this.appSupplier;
	}

	/**
	 * ����Ʒ��
	 * 
	 */
	public void setAppBrand(String appBrand) {
		this.appBrand = appBrand;
	}

	/**
	 * ����Ʒ��
	 * 
	 */
	public String getAppBrand() {
		return this.appBrand;
	}
	public Integer getStockStatus() {
		return stockStatus;
	}

	public void setStockStatus(Integer stockStatus) {
		this.stockStatus = stockStatus;
	}

	/**
	 * �������
	 * 
	 */
	public void setAppSpec(String appSpec) {
		this.appSpec = appSpec;
	}

	/**
	 * �������
	 * 
	 */
	public String getAppSpec() {
		return this.appSpec;
	}

	/**
	 * ���״̬ 1���ͨ�� 2���� 3����� 4δ���
	 * 
	 */
	public void setAuditStatus(Integer auditStatus) {
		this.auditStatus = auditStatus;
	}

	/**
	 * ���״̬ 1���ͨ�� 2���� 3����� 4δ���
	 * 
	 */
	public Integer getAuditStatus() {
		return this.auditStatus;
	}

	/**
	 * ��ע
	 * 
	 */
	public void setMem(String mem) {
		this.mem = mem;
	}
	public Integer getResult() {
		return result;
	}

	public void setResult(Integer result) {
		this.result = result;
	}
	/**
	 * ��ע
	 * 
	 */
	public String getMem() {
		return this.mem;
	}

	/**
	 */
	public void setLabRoom(LabRoom labRoom) {
		this.labRoom = labRoom;
	}

	/**
	 */
	@JsonIgnore
	public LabRoom getLabRoom() {
		return labRoom;
	}

	/**
	 */
	public void setAssetApp(AssetApp assetApp) {
		this.assetApp = assetApp;
	}

	/**
	 */
	@JsonIgnore
	public AssetApp getAssetApp() {
		return assetApp;
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
	public void setSchoolAcademy(SchoolAcademy schoolAcademy) {
		this.schoolAcademy = schoolAcademy;
	}
	/**
	 */
	public void setAssetCabinetWarehouseAccesses(Set<AssetCabinetWarehouseAccess> assetCabinetWarehouseAccesses) {
		this.assetCabinetWarehouseAccesses = assetCabinetWarehouseAccesses;
	}

	/**
	 */
	@JsonIgnore
	public Set<AssetCabinetWarehouseAccess> getAssetCabinetWarehouseAccesses() {
		if (assetCabinetWarehouseAccesses == null) {
			assetCabinetWarehouseAccesses = new java.util.LinkedHashSet<net.xidlims.domain.AssetCabinetWarehouseAccess>();
		}
		return assetCabinetWarehouseAccesses;
	}


	/**
	 */
	@JsonIgnore
	public SchoolAcademy getSchoolAcademy() {
		return schoolAcademy;
	}

	/**
	 */
	public AssetAppRecord() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(AssetAppRecord that) {
		setId(that.getId());
		setAppUsage(that.getAppUsage());
		setAppQuantity(that.getAppQuantity());
		setAppPrice(that.getAppPrice());
		setAppSupplier(that.getAppSupplier());
		setAppBrand(that.getAppBrand());
		setAppSpec(that.getAppSpec());
		setAuditStatus(that.getAuditStatus());
		setMem(that.getMem());
		setLabRoom(that.getLabRoom());
		setStyleNumber(that.getStyleNumber());
		setAssetApp(that.getAssetApp());
		setAsset(that.getAsset());
		setSchoolAcademy(that.getSchoolAcademy());
		setAssetCabinetWarehouseAccesses(new java.util.LinkedHashSet<net.xidlims.domain.AssetCabinetWarehouseAccess>(that.getAssetCabinetWarehouseAccesses()));
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("id=[").append(id).append("] ");
		buffer.append("styleNumber=[").append(styleNumber).append("] ");
		buffer.append("appUsage=[").append(appUsage).append("] ");
		buffer.append("appQuantity=[").append(appQuantity).append("] ");
		buffer.append("appPrice=[").append(appPrice).append("] ");
		buffer.append("appSupplier=[").append(appSupplier).append("] ");
		buffer.append("appBrand=[").append(appBrand).append("] ");
		buffer.append("appSpec=[").append(appSpec).append("] ");
		buffer.append("auditStatus=[").append(auditStatus).append("] ");
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
		if (!(obj instanceof AssetAppRecord))
			return false;
		AssetAppRecord equalCheck = (AssetAppRecord) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
