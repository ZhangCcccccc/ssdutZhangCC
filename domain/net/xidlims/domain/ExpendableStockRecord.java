package net.xidlims.domain;

import java.io.Serializable;

import java.lang.StringBuilder;

import java.util.Calendar;
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
		@NamedQuery(name = "findAllExpendableStockRecords", query = "select myExpendableStockRecord from ExpendableStockRecord myExpendableStockRecord"),
		@NamedQuery(name = "findExpendableStockRecordById", query = "select myExpendableStockRecord from ExpendableStockRecord myExpendableStockRecord where myExpendableStockRecord.id = ?1"),
		@NamedQuery(name = "findExpendableStockRecordByPrimaryKey", query = "select myExpendableStockRecord from ExpendableStockRecord myExpendableStockRecord where myExpendableStockRecord.id = ?1"),
		@NamedQuery(name = "findExpendableStockRecordByRemark", query = "select myExpendableStockRecord from ExpendableStockRecord myExpendableStockRecord where myExpendableStockRecord.remark = ?1"),
		@NamedQuery(name = "findExpendableStockRecordByRemarkContaining", query = "select myExpendableStockRecord from ExpendableStockRecord myExpendableStockRecord where myExpendableStockRecord.remark like ?1"),
		@NamedQuery(name = "findExpendableStockRecordByStockDate", query = "select myExpendableStockRecord from ExpendableStockRecord myExpendableStockRecord where myExpendableStockRecord.stockDate = ?1"),
		@NamedQuery(name = "findExpendableStockRecordByStockNumber", query = "select myExpendableStockRecord from ExpendableStockRecord myExpendableStockRecord where myExpendableStockRecord.stockNumber = ?1"),
		@NamedQuery(name = "findExpendableStockRecordByUseType", query = "select myExpendableStockRecord from ExpendableStockRecord myExpendableStockRecord where myExpendableStockRecord.useType = ?1"),
		@NamedQuery(name = "findExpendableStockRecordByUseTypeContaining", query = "select myExpendableStockRecord from ExpendableStockRecord myExpendableStockRecord where myExpendableStockRecord.useType like ?1") })
@Table(catalog = "xidlims", name = "expendable_stock_record")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "ExpendableStockRecord")
public class ExpendableStockRecord implements Serializable {
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
	 * �̿�����
	 * 
	 */

	@Column(name = "stock_number")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer stockNumber;
	/**
	 * �̿�����
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "stock_date")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar stockDate;
	/**
	 * ����˵��
	 * 
	 */

	@Column(name = "remark")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String remark;
	/**
	 * ʹ������
	 * 
	 */

	@Column(name = "use_type")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String useType;

	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "user", referencedColumnName = "username") })
	@XmlTransient
	User user;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "expendable_id", referencedColumnName = "id") })
	@XmlTransient
	Expendable expendable;

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
	 * �̿�����
	 * 
	 */
	public void setStockNumber(Integer stockNumber) {
		this.stockNumber = stockNumber;
	}

	/**
	 * �̿�����
	 * 
	 */
	public Integer getStockNumber() {
		return this.stockNumber;
	}

	/**
	 * �̿�����
	 * 
	 */
	public void setStockDate(Calendar stockDate) {
		this.stockDate = stockDate;
	}

	/**
	 * �̿�����
	 * 
	 */
	public Calendar getStockDate() {
		return this.stockDate;
	}

	/**
	 * ����˵��
	 * 
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * ����˵��
	 * 
	 */
	public String getRemark() {
		return this.remark;
	}

	/**
	 * ʹ������
	 * 
	 */
	public void setUseType(String useType) {
		this.useType = useType;
	}

	/**
	 * ʹ������
	 * 
	 */
	public String getUseType() {
		return this.useType;
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
	public void setExpendable(Expendable expendable) {
		this.expendable = expendable;
	}

	/**
	 */
	@JsonIgnore
	public Expendable getExpendable() {
		return expendable;
	}

	/**
	 */
	public ExpendableStockRecord() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(ExpendableStockRecord that) {
		setId(that.getId());
		setStockNumber(that.getStockNumber());
		setStockDate(that.getStockDate());
		setRemark(that.getRemark());
		setUseType(that.getUseType());
		setUser(that.getUser());
		setExpendable(that.getExpendable());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("id=[").append(id).append("] ");
		buffer.append("stockNumber=[").append(stockNumber).append("] ");
		buffer.append("stockDate=[").append(stockDate).append("] ");
		buffer.append("remark=[").append(remark).append("] ");
		buffer.append("useType=[").append(useType).append("] ");

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
		if (!(obj instanceof ExpendableStockRecord))
			return false;
		ExpendableStockRecord equalCheck = (ExpendableStockRecord) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
