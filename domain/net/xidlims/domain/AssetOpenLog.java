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
		@NamedQuery(name = "findAllAssetOpenLogs", query = "select myAssetOpenLog from AssetOpenLog myAssetOpenLog"),
		@NamedQuery(name = "findAssetOpenLogByCreateDate", query = "select myAssetOpenLog from AssetOpenLog myAssetOpenLog where myAssetOpenLog.createDate = ?1"),
		@NamedQuery(name = "findAssetOpenLogByCreateUser", query = "select myAssetOpenLog from AssetOpenLog myAssetOpenLog where myAssetOpenLog.createUser = ?1"),
		@NamedQuery(name = "findAssetOpenLogByCreateUserContaining", query = "select myAssetOpenLog from AssetOpenLog myAssetOpenLog where myAssetOpenLog.createUser like ?1"),
		@NamedQuery(name = "findAssetOpenLogById", query = "select myAssetOpenLog from AssetOpenLog myAssetOpenLog where myAssetOpenLog.id = ?1"),
		@NamedQuery(name = "findAssetOpenLogByMem", query = "select myAssetOpenLog from AssetOpenLog myAssetOpenLog where myAssetOpenLog.mem = ?1"),
		@NamedQuery(name = "findAssetOpenLogByMemContaining", query = "select myAssetOpenLog from AssetOpenLog myAssetOpenLog where myAssetOpenLog.mem like ?1"),
		@NamedQuery(name = "findAssetOpenLogByOpenDate", query = "select myAssetOpenLog from AssetOpenLog myAssetOpenLog where myAssetOpenLog.openDate = ?1"),
		@NamedQuery(name = "findAssetOpenLogByPrimaryKey", query = "select myAssetOpenLog from AssetOpenLog myAssetOpenLog where myAssetOpenLog.id = ?1"),
		@NamedQuery(name = "findAssetOpenLogByStatus", query = "select myAssetOpenLog from AssetOpenLog myAssetOpenLog where myAssetOpenLog.status = ?1") })
@Table(catalog = "xidlims", name = "asset_open_log")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "AssetOpenLog")
public class AssetOpenLog implements Serializable {
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
	 * ��ʱ��
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "open_date")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar openDate;
	/**
	 * ���û�username
	 * 
	 */

	@Column(name = "create_user")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String createUser;
	/**
	 * ��������
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar createDate;
	/**
	 * ����״̬1δ�ɹ�0���ɹ�
	 * 
	 */

	@Column(name = "status")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer status;
	/**
	 * ����
	 * 
	 */

	@Column(name = "mem")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String mem;
	
	@Column(name = "card_no")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String cardNo;

	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "warehouse_id", referencedColumnName = "id") })
	@XmlTransient
	AssetCabinetWarehouse assetCabinetWarehouse;

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
	 * ��ʱ��
	 * 
	 */
	public void setOpenDate(Calendar openDate) {
		this.openDate = openDate;
	}

	/**
	 * ��ʱ��
	 * 
	 */
	public Calendar getOpenDate() {
		return this.openDate;
	}

	/**
	 * ���û�username
	 * 
	 */
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	/**
	 * ���û�username
	 * 
	 */
	public String getCreateUser() {
		return this.createUser;
	}

	/**
	 * ��������
	 * 
	 */
	public void setCreateDate(Calendar createDate) {
		this.createDate = createDate;
	}

	/**
	 * ��������
	 * 
	 */
	public Calendar getCreateDate() {
		return this.createDate;
	}

	/**
	 * ����״̬1δ�ɹ�0���ɹ�
	 * 
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * ����״̬1δ�ɹ�0���ɹ�
	 * 
	 */
	public Integer getStatus() {
		return this.status;
	}

	/**
	 * ����
	 * 
	 */
	public void setMem(String mem) {
		this.mem = mem;
	}

	/**
	 * ����
	 * 
	 */
	public String getMem() {
		return this.mem;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	/**
	 */
	public void setAssetCabinetWarehouse(AssetCabinetWarehouse assetCabinetWarehouse) {
		this.assetCabinetWarehouse = assetCabinetWarehouse;
	}

	/**
	 */
	@JsonIgnore
	public AssetCabinetWarehouse getAssetCabinetWarehouse() {
		return assetCabinetWarehouse;
	}

	/**
	 */
	public AssetOpenLog() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(AssetOpenLog that) {
		setId(that.getId());
		setOpenDate(that.getOpenDate());
		setCreateUser(that.getCreateUser());
		setCreateDate(that.getCreateDate());
		setStatus(that.getStatus());
		setMem(that.getMem());
		setAssetCabinetWarehouse(that.getAssetCabinetWarehouse());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("id=[").append(id).append("] ");
		buffer.append("openDate=[").append(openDate).append("] ");
		buffer.append("createUser=[").append(createUser).append("] ");
		buffer.append("createDate=[").append(createDate).append("] ");
		buffer.append("status=[").append(status).append("] ");
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
		if (!(obj instanceof AssetOpenLog))
			return false;
		AssetOpenLog equalCheck = (AssetOpenLog) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
