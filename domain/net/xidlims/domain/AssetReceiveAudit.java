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
		@NamedQuery(name = "findAllAssetReceiveAudits", query = "select myAssetReceiveAudit from AssetReceiveAudit myAssetReceiveAudit"),
		@NamedQuery(name = "findAssetReceiveAuditByAuditRoles", query = "select myAssetReceiveAudit from AssetReceiveAudit myAssetReceiveAudit where myAssetReceiveAudit.auditRoles = ?1"),
		@NamedQuery(name = "findAssetReceiveAuditByCreateDate", query = "select myAssetReceiveAudit from AssetReceiveAudit myAssetReceiveAudit where myAssetReceiveAudit.createDate = ?1"),
		@NamedQuery(name = "findAssetReceiveAuditById", query = "select myAssetReceiveAudit from AssetReceiveAudit myAssetReceiveAudit where myAssetReceiveAudit.id = ?1"),
		@NamedQuery(name = "findAssetReceiveAuditByMem", query = "select myAssetReceiveAudit from AssetReceiveAudit myAssetReceiveAudit where myAssetReceiveAudit.mem = ?1"),
		@NamedQuery(name = "findAssetReceiveAuditByPrimaryKey", query = "select myAssetReceiveAudit from AssetReceiveAudit myAssetReceiveAudit where myAssetReceiveAudit.id = ?1"),
		@NamedQuery(name = "findAssetReceiveAuditByResult", query = "select myAssetReceiveAudit from AssetReceiveAudit myAssetReceiveAudit where myAssetReceiveAudit.result = ?1"),
		@NamedQuery(name = "findAssetReceiveAuditByResultContaining", query = "select myAssetReceiveAudit from AssetReceiveAudit myAssetReceiveAudit where myAssetReceiveAudit.result like ?1"),
		@NamedQuery(name = "findAssetReceiveAuditByStatus", query = "select myAssetReceiveAudit from AssetReceiveAudit myAssetReceiveAudit where myAssetReceiveAudit.status = ?1") })
@Table(catalog = "xidlims", name = "asset_receive_audit")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "AssetReceiveAudit")
public class AssetReceiveAudit implements Serializable {
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
	 * �������״̬ ��1���ͨ��2��˾ܾ�
	 * 
	 */

	@Column(name = "status")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer status;
	/**
	 * ������
	 * 
	 */

	@Column(name = "result")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String result;
	/**
	 * ��˽�ɫ
	 * 
	 */

	@Column(name = "audit_roles")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer auditRoles;
	/**
	 * ���ʱ��
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar createDate;
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
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "manager", referencedColumnName = "username") })
	@XmlTransient
	User user;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "lab_id", referencedColumnName = "id") })
	@XmlTransient
	LabRoom labRoom;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "receive_id", referencedColumnName = "id") })
	@XmlTransient
	AssetReceive assetReceive;

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
	 * �������״̬ ��1���ͨ��2��˾ܾ�
	 * 
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * �������״̬ ��1���ͨ��2��˾ܾ�
	 * 
	 */
	public Integer getStatus() {
		return this.status;
	}

	/**
	 * ������
	 * 
	 */
	public void setResult(String result) {
		this.result = result;
	}

	/**
	 * ������
	 * 
	 */
	public String getResult() {
		return this.result;
	}

	/**
	 * ��˽�ɫ
	 * 
	 */
	public void setAuditRoles(Integer auditRoles) {
		this.auditRoles = auditRoles;
	}

	/**
	 * ��˽�ɫ
	 * 
	 */
	public Integer getAuditRoles() {
		return this.auditRoles;
	}

	/**
	 * ���ʱ��
	 * 
	 */
	public void setCreateDate(Calendar createDate) {
		this.createDate = createDate;
	}

	/**
	 * ���ʱ��
	 * 
	 */
	public Calendar getCreateDate() {
		return this.createDate;
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
	public AssetReceiveAudit() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(AssetReceiveAudit that) {
		setId(that.getId());
		setStatus(that.getStatus());
		setResult(that.getResult());
		setAuditRoles(that.getAuditRoles());
		setCreateDate(that.getCreateDate());
		setMem(that.getMem());
		setUser(that.getUser());
		setLabRoom(that.getLabRoom());
		setAssetReceive(that.getAssetReceive());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("id=[").append(id).append("] ");
		buffer.append("status=[").append(status).append("] ");
		buffer.append("result=[").append(result).append("] ");
		buffer.append("auditRoles=[").append(auditRoles).append("] ");
		buffer.append("createDate=[").append(createDate).append("] ");
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
		if (!(obj instanceof AssetReceiveAudit))
			return false;
		AssetReceiveAudit equalCheck = (AssetReceiveAudit) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
