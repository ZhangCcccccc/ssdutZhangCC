package net.xidlims.domain;

import java.io.Serializable;

import java.lang.StringBuilder;

import java.math.BigDecimal;

import java.util.Calendar;
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
		@NamedQuery(name = "findAllExpendables", query = "select myExpendable from Expendable myExpendable"),
		@NamedQuery(name = "findExpendableByArriveQuantity", query = "select myExpendable from Expendable myExpendable where myExpendable.arriveQuantity = ?1"),
		@NamedQuery(name = "findExpendableByArriveQuantityContaining", query = "select myExpendable from Expendable myExpendable where myExpendable.arriveQuantity like ?1"),
		@NamedQuery(name = "findExpendableByArriveTotalPrice", query = "select myExpendable from Expendable myExpendable where myExpendable.arriveTotalPrice = ?1"),
		@NamedQuery(name = "findExpendableByBrand", query = "select myExpendable from Expendable myExpendable where myExpendable.brand = ?1"),
		@NamedQuery(name = "findExpendableByBrandContaining", query = "select myExpendable from Expendable myExpendable where myExpendable.brand like ?1"),
		@NamedQuery(name = "findExpendableByDangerousType", query = "select myExpendable from Expendable myExpendable where myExpendable.dangerousType = ?1"),
		@NamedQuery(name = "findExpendableByDangerousTypeContaining", query = "select myExpendable from Expendable myExpendable where myExpendable.dangerousType like ?1"),
		@NamedQuery(name = "findExpendableByExpendableName", query = "select myExpendable from Expendable myExpendable where myExpendable.expendableName = ?1"),
		@NamedQuery(name = "findExpendableByExpendableNameContaining", query = "select myExpendable from Expendable myExpendable where myExpendable.expendableName like ?1"),
		@NamedQuery(name = "findExpendableByExpendableSource", query = "select myExpendable from Expendable myExpendable where myExpendable.expendableSource = ?1"),
		@NamedQuery(name = "findExpendableByExpendableSourceContaining", query = "select myExpendable from Expendable myExpendable where myExpendable.expendableSource like ?1"),
		@NamedQuery(name = "findExpendableByExpendableSpecification", query = "select myExpendable from Expendable myExpendable where myExpendable.expendableSpecification = ?1"),
		@NamedQuery(name = "findExpendableByExpendableSpecificationContaining", query = "select myExpendable from Expendable myExpendable where myExpendable.expendableSpecification like ?1"),
		@NamedQuery(name = "findExpendableByExpendableStatus", query = "select myExpendable from Expendable myExpendable where myExpendable.expendableStatus = ?1"),
		@NamedQuery(name = "findExpendableByExpendableStatusContaining", query = "select myExpendable from Expendable myExpendable where myExpendable.expendableStatus like ?1"),
		@NamedQuery(name = "findExpendableByExpendableType", query = "select myExpendable from Expendable myExpendable where myExpendable.expendableType = ?1"),
		@NamedQuery(name = "findExpendableByExpendableTypeContaining", query = "select myExpendable from Expendable myExpendable where myExpendable.expendableType like ?1"),
		@NamedQuery(name = "findExpendableByExpendableUnit", query = "select myExpendable from Expendable myExpendable where myExpendable.expendableUnit = ?1"),
		@NamedQuery(name = "findExpendableByExpendableUnitContaining", query = "select myExpendable from Expendable myExpendable where myExpendable.expendableUnit like ?1"),
		@NamedQuery(name = "findExpendableByFlag", query = "select myExpendable from Expendable myExpendable where myExpendable.flag = ?1"),
		@NamedQuery(name = "findExpendableByFundAccount", query = "select myExpendable from Expendable myExpendable where myExpendable.fundAccount = ?1"),
		@NamedQuery(name = "findExpendableByFundAccountContaining", query = "select myExpendable from Expendable myExpendable where myExpendable.fundAccount like ?1"),
		@NamedQuery(name = "findExpendableById", query = "select myExpendable from Expendable myExpendable where myExpendable.id = ?1"),
		@NamedQuery(name = "findExpendableByIfDangerous", query = "select myExpendable from Expendable myExpendable where myExpendable.ifDangerous = ?1"),
		@NamedQuery(name = "findExpendableByOrderNumber", query = "select myExpendable from Expendable myExpendable where myExpendable.orderNumber = ?1"),
		@NamedQuery(name = "findExpendableByOrderNumberContaining", query = "select myExpendable from Expendable myExpendable where myExpendable.orderNumber like ?1"),
		@NamedQuery(name = "findExpendableByPlace", query = "select myExpendable from Expendable myExpendable where myExpendable.place = ?1"),
		@NamedQuery(name = "findExpendableByPlaceContaining", query = "select myExpendable from Expendable myExpendable where myExpendable.place like ?1"),
		@NamedQuery(name = "findExpendableByPrimaryKey", query = "select myExpendable from Expendable myExpendable where myExpendable.id = ?1"),
		@NamedQuery(name = "findExpendableByPurchaseDate", query = "select myExpendable from Expendable myExpendable where myExpendable.purchaseDate = ?1"),
		@NamedQuery(name = "findExpendableByQuantity", query = "select myExpendable from Expendable myExpendable where myExpendable.quantity = ?1"),
		@NamedQuery(name = "findExpendableByQuantityContaining", query = "select myExpendable from Expendable myExpendable where myExpendable.quantity like ?1"),
		@NamedQuery(name = "findExpendableBySupplier", query = "select myExpendable from Expendable myExpendable where myExpendable.supplier = ?1"),
		@NamedQuery(name = "findExpendableBySupplierContaining", query = "select myExpendable from Expendable myExpendable where myExpendable.supplier like ?1"),
		@NamedQuery(name = "findExpendableByUnitPrice", query = "select myExpendable from Expendable myExpendable where myExpendable.unitPrice = ?1") })
@Table(catalog = "xidlims", name = "expendable")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "Expendable")
@XmlRootElement(namespace = "xidlims/net/xidlims/domain")
public class Expendable implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ѧУ���Ʒ�ǼǱ�
	 * 
	 */

	@Column(name = "id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@XmlElement
	Integer id;
	/**
	 * ������
	 * 
	 */

	@Column(name = "order_number", length = 225)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String orderNumber;
	/**
	 * ����
	 * 
	 */

	@Column(name = "expendable_type")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String expendableType;
	/**
	 * ���������
	 * 
	 */

	@Column(name = "supplier")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String supplier;
	/**
	 * �깺ʱ��
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "purchase_date")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar purchaseDate;
	/**
	 * �����ʺ�
	 * 
	 */

	@Column(name = "fund_account")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String fundAccount;
	/**
	 * ��Ʒ���
	 * 
	 */

	@Column(name = "expendable_name")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String expendableName;
	/**
	 * ��Ʒ��Դ���
	 * 
	 */

	@Column(name = "expendable_source")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String expendableSource;
	/**
	 * ��Ʒ���
	 * 
	 */

	@Column(name = "expendable_specification")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String expendableSpecification;
	/**
	 * Ʒ��
	 * 
	 */

	@Column(name = "brand")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String brand;
	/**
	 * ��װ��λ
	 * 
	 */

	@Column(name = "expendable_unit")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String expendableUnit;
	/**
	 * ����
	 * 
	 */

	@Column(name = "unit_price", scale = 2, precision = 10)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal unitPrice;
	/**
	 * ����
	 * 
	 */

	@Column(name = "quantity")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer quantity;
	/**
	 * ��������
	 * 
	 */

	@Column(name = "arrive_quantity")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer arriveQuantity;
	/**
	 * �����ܼ�Ԫ
	 * 
	 */

	@Column(name = "arrive_total_price", scale = 2, precision = 10)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal arriveTotalPrice;
	/**
	 * ״̬
	 * 
	 */

	@Column(name = "expendable_status")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String expendableStatus;
	/**
	 * Σ��Ʒ��ǣ�1����ǣ�0����
	 * 
	 */

	@Column(name = "if_dangerous")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer ifDangerous;
	/**
	 * Σ��Ʒ����
	 * 
	 */

	@Column(name = "dangerous_type")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String dangerousType;
	/**
	 * ���λ��
	 * 
	 */

	@Column(name = "place")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String place;
	/**
	 * ���λ��1Ϊ�½��ģ�0����ģ�
	 * 
	 */

	@Column(name = "flag")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer flag;

	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "purchase_user", referencedColumnName = "username") })
	@XmlTransient
	User user;
	/**
	 */
	@OneToMany(mappedBy = "expendable", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.ExpendableStockRecord> expendableStockRecords;
	/**
	 */
	@OneToMany(mappedBy = "expendable", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.ExpendableApply> expendableApplies;

	/**
	 * ѧУ���Ʒ�ǼǱ�
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * ѧУ���Ʒ�ǼǱ�
	 * 
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * ������
	 * 
	 */
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	/**
	 * ������
	 * 
	 */
	public String getOrderNumber() {
		return this.orderNumber;
	}

	/**
	 * ����
	 * 
	 */
	public void setExpendableType(String expendableType) {
		this.expendableType = expendableType;
	}

	/**
	 * ����
	 * 
	 */
	public String getExpendableType() {
		return this.expendableType;
	}

	/**
	 * ���������
	 * 
	 */
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	/**
	 * ���������
	 * 
	 */
	public String getSupplier() {
		return this.supplier;
	}

	/**
	 * �깺ʱ��
	 * 
	 */
	public void setPurchaseDate(Calendar purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	/**
	 * �깺ʱ��
	 * 
	 */
	public Calendar getPurchaseDate() {
		return this.purchaseDate;
	}

	/**
	 * �����ʺ�
	 * 
	 */
	public void setFundAccount(String fundAccount) {
		this.fundAccount = fundAccount;
	}

	/**
	 * �����ʺ�
	 * 
	 */
	public String getFundAccount() {
		return this.fundAccount;
	}

	/**
	 * ��Ʒ���
	 * 
	 */
	public void setExpendableName(String expendableName) {
		this.expendableName = expendableName;
	}

	/**
	 * ��Ʒ���
	 * 
	 */
	public String getExpendableName() {
		return this.expendableName;
	}

	/**
	 * ��Ʒ��Դ���
	 * 
	 */
	public void setExpendableSource(String expendableSource) {
		this.expendableSource = expendableSource;
	}

	/**
	 * ��Ʒ��Դ���
	 * 
	 */
	public String getExpendableSource() {
		return this.expendableSource;
	}

	/**
	 * ��Ʒ���
	 * 
	 */
	public void setExpendableSpecification(String expendableSpecification) {
		this.expendableSpecification = expendableSpecification;
	}

	/**
	 * ��Ʒ���
	 * 
	 */
	public String getExpendableSpecification() {
		return this.expendableSpecification;
	}

	/**
	 * Ʒ��
	 * 
	 */
	public void setBrand(String brand) {
		this.brand = brand;
	}

	/**
	 * Ʒ��
	 * 
	 */
	public String getBrand() {
		return this.brand;
	}

	/**
	 * ��װ��λ
	 * 
	 */
	public void setExpendableUnit(String expendableUnit) {
		this.expendableUnit = expendableUnit;
	}

	/**
	 * ��װ��λ
	 * 
	 */
	public String getExpendableUnit() {
		return this.expendableUnit;
	}

	/**
	 * ����
	 * 
	 */
	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	/**
	 * ����
	 * 
	 */
	public BigDecimal getUnitPrice() {
		return this.unitPrice;
	}

	/**
	 * ����
	 * 
	 */
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	/**
	 * ����
	 * 
	 */
	public Integer getQuantity() {
		return this.quantity;
	}

	/**
	 * ��������
	 * 
	 */
	public void setArriveQuantity(Integer arriveQuantity) {
		this.arriveQuantity = arriveQuantity;
	}

	/**
	 * ��������
	 * 
	 */
	public Integer getArriveQuantity() {
		return this.arriveQuantity;
	}

	/**
	 * �����ܼ�Ԫ
	 * 
	 */
	public void setArriveTotalPrice(BigDecimal arriveTotalPrice) {
		this.arriveTotalPrice = arriveTotalPrice;
	}

	/**
	 * �����ܼ�Ԫ
	 * 
	 */
	public BigDecimal getArriveTotalPrice() {
		return this.arriveTotalPrice;
	}

	/**
	 * ״̬
	 * 
	 */
	public void setExpendableStatus(String expendableStatus) {
		this.expendableStatus = expendableStatus;
	}

	/**
	 * ״̬
	 * 
	 */
	public String getExpendableStatus() {
		return this.expendableStatus;
	}

	/**
	 * Σ��Ʒ��ǣ�1����ǣ�0����
	 * 
	 */
	public void setIfDangerous(Integer ifDangerous) {
		this.ifDangerous = ifDangerous;
	}

	/**
	 * Σ��Ʒ��ǣ�1����ǣ�0����
	 * 
	 */
	public Integer getIfDangerous() {
		return this.ifDangerous;
	}

	/**
	 * Σ��Ʒ����
	 * 
	 */
	public void setDangerousType(String dangerousType) {
		this.dangerousType = dangerousType;
	}

	/**
	 * Σ��Ʒ����
	 * 
	 */
	public String getDangerousType() {
		return this.dangerousType;
	}

	/**
	 * ���λ��
	 * 
	 */
	public void setPlace(String place) {
		this.place = place;
	}

	/**
	 * ���λ��
	 * 
	 */
	public String getPlace() {
		return this.place;
	}

	/**
	 * ���λ��1Ϊ�½��ģ�0����ģ�
	 * 
	 */
	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	/**
	 * ���λ��1Ϊ�½��ģ�0����ģ�
	 * 
	 */
	public Integer getFlag() {
		return this.flag;
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
	public void setExpendableStockRecords(Set<ExpendableStockRecord> expendableStockRecords) {
		this.expendableStockRecords = expendableStockRecords;
	}

	/**
	 */
	@JsonIgnore
	public Set<ExpendableStockRecord> getExpendableStockRecords() {
		if (expendableStockRecords == null) {
			expendableStockRecords = new java.util.LinkedHashSet<net.xidlims.domain.ExpendableStockRecord>();
		}
		return expendableStockRecords;
	}

	/**
	 */
	public void setExpendableApplies(Set<ExpendableApply> expendableApplies) {
		this.expendableApplies = expendableApplies;
	}

	/**
	 */
	@JsonIgnore
	public Set<ExpendableApply> getExpendableApplies() {
		if (expendableApplies == null) {
			expendableApplies = new java.util.LinkedHashSet<net.xidlims.domain.ExpendableApply>();
		}
		return expendableApplies;
	}

	/**
	 */
	public Expendable() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(Expendable that) {
		setOrderNumber(that.getOrderNumber());
		setExpendableType(that.getExpendableType());
		setSupplier(that.getSupplier());
		setPurchaseDate(that.getPurchaseDate());
		setFundAccount(that.getFundAccount());
		setExpendableName(that.getExpendableName());
		setExpendableSource(that.getExpendableSource());
		setExpendableSpecification(that.getExpendableSpecification());
		setBrand(that.getBrand());
		setExpendableUnit(that.getExpendableUnit());
		setUnitPrice(that.getUnitPrice());
		setQuantity(that.getQuantity());
		setArriveQuantity(that.getArriveQuantity());
		setArriveTotalPrice(that.getArriveTotalPrice());
		setExpendableStatus(that.getExpendableStatus());
		setIfDangerous(that.getIfDangerous());
		setDangerousType(that.getDangerousType());
		setPlace(that.getPlace());
		setFlag(that.getFlag());
		setUser(that.getUser());
		setExpendableStockRecords(new java.util.LinkedHashSet<net.xidlims.domain.ExpendableStockRecord>(that.getExpendableStockRecords()));
		setExpendableApplies(new java.util.LinkedHashSet<net.xidlims.domain.ExpendableApply>(that.getExpendableApplies()));
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("id=[").append(id).append("] ");
		buffer.append("orderNumber=[").append(orderNumber).append("] ");
		buffer.append("expendableType=[").append(expendableType).append("] ");
		buffer.append("supplier=[").append(supplier).append("] ");
		buffer.append("purchaseDate=[").append(purchaseDate).append("] ");
		buffer.append("fundAccount=[").append(fundAccount).append("] ");
		buffer.append("expendableName=[").append(expendableName).append("] ");
		buffer.append("expendableSource=[").append(expendableSource).append("] ");
		buffer.append("expendableSpecification=[").append(expendableSpecification).append("] ");
		buffer.append("brand=[").append(brand).append("] ");
		buffer.append("expendableUnit=[").append(expendableUnit).append("] ");
		buffer.append("unitPrice=[").append(unitPrice).append("] ");
		buffer.append("quantity=[").append(quantity).append("] ");
		buffer.append("arriveQuantity=[").append(arriveQuantity).append("] ");
		buffer.append("arriveTotalPrice=[").append(arriveTotalPrice).append("] ");
		buffer.append("expendableStatus=[").append(expendableStatus).append("] ");
		buffer.append("ifDangerous=[").append(ifDangerous).append("] ");
		buffer.append("dangerousType=[").append(dangerousType).append("] ");
		buffer.append("place=[").append(place).append("] ");
		buffer.append("flag=[").append(flag).append("] ");

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
		if (!(obj instanceof Expendable))
			return false;
		Expendable equalCheck = (Expendable) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
