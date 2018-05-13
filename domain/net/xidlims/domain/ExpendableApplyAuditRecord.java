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
		@NamedQuery(name = "findAllExpendableApplyAuditRecords", query = "select myExpendableApplyAuditRecord from ExpendableApplyAuditRecord myExpendableApplyAuditRecord"),
		@NamedQuery(name = "findExpendableApplyAuditRecordByAuditDate", query = "select myExpendableApplyAuditRecord from ExpendableApplyAuditRecord myExpendableApplyAuditRecord where myExpendableApplyAuditRecord.auditDate = ?1"),
		@NamedQuery(name = "findExpendableApplyAuditRecordByAuditResult", query = "select myExpendableApplyAuditRecord from ExpendableApplyAuditRecord myExpendableApplyAuditRecord where myExpendableApplyAuditRecord.auditResult = ?1"),
		@NamedQuery(name = "findExpendableApplyAuditRecordById", query = "select myExpendableApplyAuditRecord from ExpendableApplyAuditRecord myExpendableApplyAuditRecord where myExpendableApplyAuditRecord.id = ?1"),
		@NamedQuery(name = "findExpendableApplyAuditRecordByPrimaryKey", query = "select myExpendableApplyAuditRecord from ExpendableApplyAuditRecord myExpendableApplyAuditRecord where myExpendableApplyAuditRecord.id = ?1"),
		@NamedQuery(name = "findExpendableApplyAuditRecordByRemark", query = "select myExpendableApplyAuditRecord from ExpendableApplyAuditRecord myExpendableApplyAuditRecord where myExpendableApplyAuditRecord.remark = ?1"),
		@NamedQuery(name = "findExpendableApplyAuditRecordByRemarkContaining", query = "select myExpendableApplyAuditRecord from ExpendableApplyAuditRecord myExpendableApplyAuditRecord where myExpendableApplyAuditRecord.remark like ?1") })
@Table(catalog = "xidlims", name = "expendable_apply_audit_record")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "ExpendableApplyAuditRecord")
public class ExpendableApplyAuditRecord implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ���Ʒ������˼�¼
	 * 
	 */

	@Column(name = "id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@XmlElement
	Integer id;
	/**
	 * ��˽��1��ͨ��0�Ǿܾ�
	 * 
	 */

	@Column(name = "audit_result")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer auditResult;
	/**
	 * �����������
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "audit_date")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar auditDate;
	/**
	 * ���˵��
	 * 
	 */

	@Column(name = "remark")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String remark;

	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "audit_user", referencedColumnName = "username") })
	@XmlTransient
	User user;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "apply_id", referencedColumnName = "id") })
	@XmlTransient
	ExpendableApply expendableApply;

	/**
	 * ���Ʒ������˼�¼
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * ���Ʒ������˼�¼
	 * 
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * ��˽��1��ͨ��0�Ǿܾ�
	 * 
	 */
	public void setAuditResult(Integer auditResult) {
		this.auditResult = auditResult;
	}

	/**
	 * ��˽��1��ͨ��0�Ǿܾ�
	 * 
	 */
	public Integer getAuditResult() {
		return this.auditResult;
	}

	/**
	 * �����������
	 * 
	 */
	public void setAuditDate(Calendar auditDate) {
		this.auditDate = auditDate;
	}

	/**
	 * �����������
	 * 
	 */
	public Calendar getAuditDate() {
		return this.auditDate;
	}

	/**
	 * ���˵��
	 * 
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * ���˵��
	 * 
	 */
	public String getRemark() {
		return this.remark;
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
	public void setExpendableApply(ExpendableApply expendableApply) {
		this.expendableApply = expendableApply;
	}

	/**
	 */
	@JsonIgnore
	public ExpendableApply getExpendableApply() {
		return expendableApply;
	}

	/**
	 */
	public ExpendableApplyAuditRecord() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(ExpendableApplyAuditRecord that) {
		setId(that.getId());
		setAuditResult(that.getAuditResult());
		setAuditDate(that.getAuditDate());
		setRemark(that.getRemark());
		setUser(that.getUser());
		setExpendableApply(that.getExpendableApply());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("id=[").append(id).append("] ");
		buffer.append("auditResult=[").append(auditResult).append("] ");
		buffer.append("auditDate=[").append(auditDate).append("] ");
		buffer.append("remark=[").append(remark).append("] ");

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
		if (!(obj instanceof ExpendableApplyAuditRecord))
			return false;
		ExpendableApplyAuditRecord equalCheck = (ExpendableApplyAuditRecord) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
