package net.xidlims.domain;

import java.io.Serializable;

import java.lang.StringBuilder;

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
		@NamedQuery(name = "findAllExpendableApplys", query = "select myExpendableApply from ExpendableApply myExpendableApply"),
		@NamedQuery(name = "findExpendableApplyByBorrowTime", query = "select myExpendableApply from ExpendableApply myExpendableApply where myExpendableApply.borrowTime = ?1"),
		@NamedQuery(name = "findExpendableApplyByExpendableNumber", query = "select myExpendableApply from ExpendableApply myExpendableApply where myExpendableApply.expendableNumber = ?1"),
		@NamedQuery(name = "findExpendableApplyByFlag", query = "select myExpendableApply from ExpendableApply myExpendableApply where myExpendableApply.flag = ?1"),
		@NamedQuery(name = "findExpendableApplyById", query = "select myExpendableApply from ExpendableApply myExpendableApply where myExpendableApply.id = ?1"),
		@NamedQuery(name = "findExpendableApplyByPrimaryKey", query = "select myExpendableApply from ExpendableApply myExpendableApply where myExpendableApply.id = ?1"),
		@NamedQuery(name = "findExpendableApplyByRemarks", query = "select myExpendableApply from ExpendableApply myExpendableApply where myExpendableApply.remarks = ?1"),
		@NamedQuery(name = "findExpendableApplyByRemarksContaining", query = "select myExpendableApply from ExpendableApply myExpendableApply where myExpendableApply.remarks like ?1") })
@Table(catalog = "xidlims", name = "expendable_apply")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "ExpendableApply")
@XmlRootElement(namespace = "xidlims/net/xidlims/domain")
public class ExpendableApply implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ���Ʒ���쵥
	 * 
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

	@Column(name = "expendable_number")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer expendableNumber;
	/**
	 * ��������
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "borrow_time")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar borrowTime;
	/**
	 * ����˵��
	 * 
	 */

	@Column(name = "remarks", length = 40)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String remarks;
	/**
	 * �ύ״̬��0 δ�ύ   1 ���ύ��2����У�3���ͨ��4��˾ܾ�
	 * 
	 */
	@Column(name = "apply_remark", length = 40)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String applyRemark;
	/**
	 * �ύ״̬��0 δ�ύ   1 ���ύ��2����У�3���ͨ��4��˾ܾ�
	 * 
	 */

	@Column(name = "flag")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer flag;

	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "apply_user", referencedColumnName = "username") })
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
	@OneToMany(mappedBy = "expendableApply", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.ExpendableApplyAuditRecord> expendableApplyAuditRecords;

	/**
	 * ���Ʒ���쵥
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * ���Ʒ���쵥
	 * 
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * ��������
	 * 
	 */
	
	public void setExpendableNumber(Integer expendableNumber) {
		this.expendableNumber = expendableNumber;
	}

	public String getApplyRemark() {
		return applyRemark;
	}

	public void setApplyRemark(String applyRemark) {
		this.applyRemark = applyRemark;
	}

	/**
	 * ��������
	 * 
	 */
	public Integer getExpendableNumber() {
		return this.expendableNumber;
	}

	/**
	 * ��������
	 * 
	 */
	public void setBorrowTime(Calendar borrowTime) {
		this.borrowTime = borrowTime;
	}

	/**
	 * ��������
	 * 
	 */
	public Calendar getBorrowTime() {
		return this.borrowTime;
	}

	/**
	 * ����˵��
	 * 
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	/**
	 * ����˵��
	 * 
	 */
	public String getRemarks() {
		return this.remarks;
	}

	/**
	 * �ύ״̬��0 δ�ύ   1 ���ύ��2����У�3���ͨ��4��˾ܾ�
	 * 
	 */
	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	/**
	 * �ύ״̬��0 δ�ύ   1 ���ύ��2����У�3���ͨ��4��˾ܾ�
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
	public void setExpendableApplyAuditRecords(Set<ExpendableApplyAuditRecord> expendableApplyAuditRecords) {
		this.expendableApplyAuditRecords = expendableApplyAuditRecords;
	}

	/**
	 */
	@JsonIgnore
	public Set<ExpendableApplyAuditRecord> getExpendableApplyAuditRecords() {
		if (expendableApplyAuditRecords == null) {
			expendableApplyAuditRecords = new java.util.LinkedHashSet<net.xidlims.domain.ExpendableApplyAuditRecord>();
		}
		return expendableApplyAuditRecords;
	}

	/**
	 */
	public ExpendableApply() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(ExpendableApply that) {
		setId(that.getId());
		setExpendableNumber(that.getExpendableNumber());
		setBorrowTime(that.getBorrowTime());
		setRemarks(that.getRemarks());
		setFlag(that.getFlag());
		setUser(that.getUser());
		setExpendable(that.getExpendable());
		setExpendableApplyAuditRecords(new java.util.LinkedHashSet<net.xidlims.domain.ExpendableApplyAuditRecord>(that.getExpendableApplyAuditRecords()));
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("id=[").append(id).append("] ");
		buffer.append("expendableNumber=[").append(expendableNumber).append("] ");
		buffer.append("borrowTime=[").append(borrowTime).append("] ");
		buffer.append("remarks=[").append(remarks).append("] ");
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
		if (!(obj instanceof ExpendableApply))
			return false;
		ExpendableApply equalCheck = (ExpendableApply) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
