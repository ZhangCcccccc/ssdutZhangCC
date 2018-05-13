package net.xidlims.domain;

import java.io.Serializable;

import java.lang.StringBuilder;

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
		@NamedQuery(name = "findAllOperationItemMaterialRecords", query = "select myOperationItemMaterialRecord from OperationItemMaterialRecord myOperationItemMaterialRecord"),
		@NamedQuery(name = "findOperationItemMaterialRecordByLpmrAmount", query = "select myOperationItemMaterialRecord from OperationItemMaterialRecord myOperationItemMaterialRecord where myOperationItemMaterialRecord.lpmrAmount = ?1"),
		@NamedQuery(name = "findOperationItemMaterialRecordByLpmrAmountContaining", query = "select myOperationItemMaterialRecord from OperationItemMaterialRecord myOperationItemMaterialRecord where myOperationItemMaterialRecord.lpmrAmount like ?1"),
		@NamedQuery(name = "findOperationItemMaterialRecordByLpmrId", query = "select myOperationItemMaterialRecord from OperationItemMaterialRecord myOperationItemMaterialRecord where myOperationItemMaterialRecord.lpmrId = ?1"),
		@NamedQuery(name = "findOperationItemMaterialRecordByLpmrModel", query = "select myOperationItemMaterialRecord from OperationItemMaterialRecord myOperationItemMaterialRecord where myOperationItemMaterialRecord.lpmrModel = ?1"),
		@NamedQuery(name = "findOperationItemMaterialRecordByLpmrModelContaining", query = "select myOperationItemMaterialRecord from OperationItemMaterialRecord myOperationItemMaterialRecord where myOperationItemMaterialRecord.lpmrModel like ?1"),
		@NamedQuery(name = "findOperationItemMaterialRecordByLpmrName", query = "select myOperationItemMaterialRecord from OperationItemMaterialRecord myOperationItemMaterialRecord where myOperationItemMaterialRecord.lpmrName = ?1"),
		@NamedQuery(name = "findOperationItemMaterialRecordByLpmrNameContaining", query = "select myOperationItemMaterialRecord from OperationItemMaterialRecord myOperationItemMaterialRecord where myOperationItemMaterialRecord.lpmrName like ?1"),
		@NamedQuery(name = "findOperationItemMaterialRecordByLpmrQuantity", query = "select myOperationItemMaterialRecord from OperationItemMaterialRecord myOperationItemMaterialRecord where myOperationItemMaterialRecord.lpmrQuantity = ?1"),
		@NamedQuery(name = "findOperationItemMaterialRecordByLpmrQuantityContaining", query = "select myOperationItemMaterialRecord from OperationItemMaterialRecord myOperationItemMaterialRecord where myOperationItemMaterialRecord.lpmrQuantity like ?1"),
		@NamedQuery(name = "findOperationItemMaterialRecordByLpmrRemark", query = "select myOperationItemMaterialRecord from OperationItemMaterialRecord myOperationItemMaterialRecord where myOperationItemMaterialRecord.lpmrRemark = ?1"),
		@NamedQuery(name = "findOperationItemMaterialRecordByLpmrRemarkContaining", query = "select myOperationItemMaterialRecord from OperationItemMaterialRecord myOperationItemMaterialRecord where myOperationItemMaterialRecord.lpmrRemark like ?1"),
		@NamedQuery(name = "findOperationItemMaterialRecordByLpmrTimeCreate", query = "select myOperationItemMaterialRecord from OperationItemMaterialRecord myOperationItemMaterialRecord where myOperationItemMaterialRecord.lpmrTimeCreate = ?1"),
		@NamedQuery(name = "findOperationItemMaterialRecordByLpmrUnit", query = "select myOperationItemMaterialRecord from OperationItemMaterialRecord myOperationItemMaterialRecord where myOperationItemMaterialRecord.lpmrUnit = ?1"),
		@NamedQuery(name = "findOperationItemMaterialRecordByLpmrUnitContaining", query = "select myOperationItemMaterialRecord from OperationItemMaterialRecord myOperationItemMaterialRecord where myOperationItemMaterialRecord.lpmrUnit like ?1"),
		@NamedQuery(name = "findOperationItemMaterialRecordByPrimaryKey", query = "select myOperationItemMaterialRecord from OperationItemMaterialRecord myOperationItemMaterialRecord where myOperationItemMaterialRecord.lpmrId = ?1") })
@Table(catalog = "xidlims", name = "operation_item_material_record")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "OperationItemMaterialRecord")
public class OperationItemMaterialRecord implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ʵ����Ŀ����ʹ�ü�¼��
	 * 
	 */

	@Column(name = "lpmr_id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@XmlElement
	Integer lpmrId;
	/**
	 * ���
	 * 
	 */

	@Column(name = "lpmr_name")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String lpmrName;
	
	/**
	 * ����ʱ��
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "lpmr_time_create")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar lpmrTimeCreate;
	/**
	 * ����ͺ�
	 * 
	 */

	@Column(name = "lpmr_model")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String lpmrModel;
	/**
	 * ��λ
	 * 
	 */

	@Column(name = "lpmr_unit")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String lpmrUnit;
	/**
	 * ����
	 * 
	 */

	@Column(name = "lpmr_quantity")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String lpmrQuantity;
	/**
	 * ���
	 * 
	 */

	@Column(name = "lpmr_amount")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String lpmrAmount;
	/**
	 * ��ע
	 * 
	 */

	@Column(name = "lpmr_remark")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String lpmrRemark;

	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "lpmr_operation_item_id", referencedColumnName = "id") })
	@XmlTransient
	OperationItem operationItem;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "lpmr_category_main", referencedColumnName = "id") })
	@XmlTransient
	CDictionary CDictionary;

	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "access_record_id", referencedColumnName = "id") })
	@XmlTransient
	AssetCabinetWarehouseAccessRecord assetCabinetWarehouseAccessRecord;
	/**
	 * ʵ����Ŀ����ʹ�ü�¼��
	 * 
	 */
	public void setLpmrId(Integer lpmrId) {
		this.lpmrId = lpmrId;
	}

	/**
	 * ʵ����Ŀ����ʹ�ü�¼��
	 * 
	 */
	public Integer getLpmrId() {
		return this.lpmrId;
	}

	/**
	 * ���
	 * 
	 */
	public void setLpmrName(String lpmrName) {
		this.lpmrName = lpmrName;
	}

	/**
	 * ���
	 * 
	 */
	public String getLpmrName() {
		return this.lpmrName;
	}

	/**
	 * ����ʱ��
	 * 
	 */
	public void setLpmrTimeCreate(Calendar lpmrTimeCreate) {
		this.lpmrTimeCreate = lpmrTimeCreate;
	}

	/**
	 * ����ʱ��
	 * 
	 */
	public Calendar getLpmrTimeCreate() {
		return this.lpmrTimeCreate;
	}

	/**
	 * ����ͺ�
	 * 
	 */
	public void setLpmrModel(String lpmrModel) {
		this.lpmrModel = lpmrModel;
	}

	/**
	 * ����ͺ�
	 * 
	 */
	public String getLpmrModel() {
		return this.lpmrModel;
	}

	/**
	 * ��λ
	 * 
	 */
	public void setLpmrUnit(String lpmrUnit) {
		this.lpmrUnit = lpmrUnit;
	}

	/**
	 * ��λ
	 * 
	 */
	public String getLpmrUnit() {
		return this.lpmrUnit;
	}

	/**
	 * ����
	 * 
	 */
	public void setLpmrQuantity(String lpmrQuantity) {
		this.lpmrQuantity = lpmrQuantity;
	}

	/**
	 * ����
	 * 
	 */
	public String getLpmrQuantity() {
		return this.lpmrQuantity;
	}

	/**
	 * ���
	 * 
	 */
	public void setLpmrAmount(String lpmrAmount) {
		this.lpmrAmount = lpmrAmount;
	}

	/**
	 * ���
	 * 
	 */
	public String getLpmrAmount() {
		return this.lpmrAmount;
	}

	/**
	 * ��ע
	 * 
	 */
	public void setLpmrRemark(String lpmrRemark) {
		this.lpmrRemark = lpmrRemark;
	}

	/**
	 * ��ע
	 * 
	 */
	public String getLpmrRemark() {
		return this.lpmrRemark;
	}

	/**
	 */
	public void setOperationItem(OperationItem operationItem) {
		this.operationItem = operationItem;
	}

	/**
	 */
	@JsonIgnore
	public OperationItem getOperationItem() {
		return operationItem;
	}
	
	@JsonIgnore
	public CDictionary getCDictionary() {
		return CDictionary;
	}

	public void setCDictionary(CDictionary cDictionary) {
		CDictionary = cDictionary;
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
	public OperationItemMaterialRecord() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(OperationItemMaterialRecord that) {
		setLpmrId(that.getLpmrId());
		setLpmrName(that.getLpmrName());
		setLpmrTimeCreate(that.getLpmrTimeCreate());
		setLpmrModel(that.getLpmrModel());
		setLpmrUnit(that.getLpmrUnit());
		setLpmrQuantity(that.getLpmrQuantity());
		setLpmrAmount(that.getLpmrAmount());
		setLpmrRemark(that.getLpmrRemark());
		setOperationItem(that.getOperationItem());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("lpmrId=[").append(lpmrId).append("] ");
		buffer.append("lpmrName=[").append(lpmrName).append("] ");
		buffer.append("lpmrTimeCreate=[").append(lpmrTimeCreate).append("] ");
		buffer.append("lpmrModel=[").append(lpmrModel).append("] ");
		buffer.append("lpmrUnit=[").append(lpmrUnit).append("] ");
		buffer.append("lpmrQuantity=[").append(lpmrQuantity).append("] ");
		buffer.append("lpmrAmount=[").append(lpmrAmount).append("] ");
		buffer.append("lpmrRemark=[").append(lpmrRemark).append("] ");

		return buffer.toString();
	}

	/**
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((lpmrId == null) ? 0 : lpmrId.hashCode()));
		return result;
	}

	/**
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof OperationItemMaterialRecord))
			return false;
		OperationItemMaterialRecord equalCheck = (OperationItemMaterialRecord) obj;
		if ((lpmrId == null && equalCheck.lpmrId != null) || (lpmrId != null && equalCheck.lpmrId == null))
			return false;
		if (lpmrId != null && !lpmrId.equals(equalCheck.lpmrId))
			return false;
		return true;
	}
}
