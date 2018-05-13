package net.xidlims.domain;

import java.io.Serializable;

import java.lang.StringBuilder;

import java.util.Calendar;
import java.util.Set;

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
		@NamedQuery(name = "findAllNDeviceAuditRecords", query = "select myNDeviceAuditRecord from NDeviceAuditRecord myNDeviceAuditRecord"),
		@NamedQuery(name = "findNDeviceAuditRecordByAuditDate", query = "select myNDeviceAuditRecord from NDeviceAuditRecord myNDeviceAuditRecord where myNDeviceAuditRecord.auditDate = ?1"),
		@NamedQuery(name = "findNDeviceAuditRecordByFlag", query = "select myNDeviceAuditRecord from NDeviceAuditRecord myNDeviceAuditRecord where myNDeviceAuditRecord.flag = ?1"),
		@NamedQuery(name = "findNDeviceAuditRecordById", query = "select myNDeviceAuditRecord from NDeviceAuditRecord myNDeviceAuditRecord where myNDeviceAuditRecord.id = ?1"),
		@NamedQuery(name = "findNDeviceAuditRecordByPrimaryKey", query = "select myNDeviceAuditRecord from NDeviceAuditRecord myNDeviceAuditRecord where myNDeviceAuditRecord.id = ?1"),
		@NamedQuery(name = "findNDeviceAuditRecordByRemark", query = "select myNDeviceAuditRecord from NDeviceAuditRecord myNDeviceAuditRecord where myNDeviceAuditRecord.remark = ?1"),
		@NamedQuery(name = "findNDeviceAuditRecordByRemarkContaining", query = "select myNDeviceAuditRecord from NDeviceAuditRecord myNDeviceAuditRecord where myNDeviceAuditRecord.remark like ?1") })
@Table(catalog = "xidlims", name = "n_device_audit_record")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "NDeviceAuditRecord")
public class NDeviceAuditRecord implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * �豸��˼�¼����������
	 * 
	 */

	@Column(name = "id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@XmlElement
	Integer id;
	/**
	 * �������
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "audit_date")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar auditDate;
	/**
	 * ��ע
	 * 
	 */

	@Column(name = "remark")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String remark;
	/**
	 * ����������
	 * 
	 */

	@Column(name = "flag")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer flag;
	

	/**
	 */
	@Column(name = "audit_result")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer auditResult;

	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "device_id", referencedColumnName = "id") })
	@XmlTransient
	NDevicePurchase NDevicePurchase;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "audit_user", referencedColumnName = "username") })
	@XmlTransient
	User user;

	/**
	 * �豸��˼�¼����������
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * �豸��˼�¼����������
	 * 
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * �������
	 * 
	 */
	public void setAuditDate(Calendar auditDate) {
		this.auditDate = auditDate;
	}

	/**
	 * �������
	 * 
	 */
	public Calendar getAuditDate() {
		return this.auditDate;
	}

	/**
	 * ��ע
	 * 
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * ��ע
	 * 
	 */
	public String getRemark() {
		return this.remark;
	}

	/**
	 * ����������
	 * 
	 */
	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	/**
	 * ����������
	 * 
	 */
	public Integer getFlag() {
		return this.flag;
	}

	/**
	 */
	public void setNDevicePurchase(NDevicePurchase NDevicePurchase) {
		this.NDevicePurchase = NDevicePurchase;
	}

	/**
	 */
	@JsonIgnore
	public NDevicePurchase getNDevicePurchase() {
		return NDevicePurchase;
	}


	public Integer getAuditResult() {
		return auditResult;
	}

	public void setAuditResult(Integer auditResult) {
		this.auditResult = auditResult;
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
	public NDeviceAuditRecord() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(NDeviceAuditRecord that) {
		setId(that.getId());
		setAuditDate(that.getAuditDate());
		setRemark(that.getRemark());
		setFlag(that.getFlag());
		setNDevicePurchase(that.getNDevicePurchase());
		setUser(that.getUser());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("id=[").append(id).append("] ");
		buffer.append("auditDate=[").append(auditDate).append("] ");
		buffer.append("remark=[").append(remark).append("] ");
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
		if (!(obj instanceof NDeviceAuditRecord))
			return false;
		NDeviceAuditRecord equalCheck = (NDeviceAuditRecord) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
