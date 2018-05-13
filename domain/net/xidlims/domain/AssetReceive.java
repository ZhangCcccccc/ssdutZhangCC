package net.xidlims.domain;

import java.io.Serializable;

import java.lang.StringBuilder;

import java.util.Calendar;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
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
		@NamedQuery(name = "findAllAssetReceives", query = "select myAssetReceive from AssetReceive myAssetReceive"),
		@NamedQuery(name = "findAssetReceiveByAssetUsage", query = "select myAssetReceive from AssetReceive myAssetReceive where myAssetReceive.assetUsage = ?1"),
		@NamedQuery(name = "findAssetReceiveByAssetUsageContaining", query = "select myAssetReceive from AssetReceive myAssetReceive where myAssetReceive.assetUsage like ?1"),
		@NamedQuery(name = "findAssetReceiveByEndDate", query = "select myAssetReceive from AssetReceive myAssetReceive where myAssetReceive.endDate = ?1"),
		@NamedQuery(name = "findAssetReceiveById", query = "select myAssetReceive from AssetReceive myAssetReceive where myAssetReceive.id = ?1"),
		@NamedQuery(name = "findAssetReceiveByMem", query = "select myAssetReceive from AssetReceive myAssetReceive where myAssetReceive.mem = ?1"),
		@NamedQuery(name = "findAssetReceiveByMemContaining", query = "select myAssetReceive from AssetReceive myAssetReceive where myAssetReceive.mem like ?1"),
		@NamedQuery(name = "findAssetReceiveByPrimaryKey", query = "select myAssetReceive from AssetReceive myAssetReceive where myAssetReceive.id = ?1"),
		@NamedQuery(name = "findAssetReceiveByProjectContent", query = "select myAssetReceive from AssetReceive myAssetReceive where myAssetReceive.projectContent = ?1"),
		@NamedQuery(name = "findAssetReceiveByProjectContentContaining", query = "select myAssetReceive from AssetReceive myAssetReceive where myAssetReceive.projectContent like ?1"),
		@NamedQuery(name = "findAssetReceiveByProjectName", query = "select myAssetReceive from AssetReceive myAssetReceive where myAssetReceive.projectName = ?1"),
		@NamedQuery(name = "findAssetReceiveByProjectNameContaining", query = "select myAssetReceive from AssetReceive myAssetReceive where myAssetReceive.projectName like ?1"),
		@NamedQuery(name = "findAssetReceiveByReceiveDate", query = "select myAssetReceive from AssetReceive myAssetReceive where myAssetReceive.receiveDate = ?1"),
		@NamedQuery(name = "findAssetReceiveByReceiveNo", query = "select myAssetReceive from AssetReceive myAssetReceive where myAssetReceive.receiveNo = ?1"),
		@NamedQuery(name = "findAssetReceiveByReceiveNoContaining", query = "select myAssetReceive from AssetReceive myAssetReceive where myAssetReceive.receiveNo like ?1"),
		@NamedQuery(name = "findAssetReceiveByResult", query = "select myAssetReceive from AssetReceive myAssetReceive where myAssetReceive.result = ?1"),
		@NamedQuery(name = "findAssetReceiveByResultContaining", query = "select myAssetReceive from AssetReceive myAssetReceive where myAssetReceive.result like ?1"),
		@NamedQuery(name = "findAssetReceiveBySaveSubmit", query = "select myAssetReceive from AssetReceive myAssetReceive where myAssetReceive.saveSubmit = ?1"),
		@NamedQuery(name = "findAssetReceiveByStartData", query = "select myAssetReceive from AssetReceive myAssetReceive where myAssetReceive.startData = ?1"),
		@NamedQuery(name = "findAssetReceiveByStatus", query = "select myAssetReceive from AssetReceive myAssetReceive where myAssetReceive.status = ?1") })
@Table(catalog = "xidlims", name = "asset_receive")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "AssetReceive")
@XmlRootElement(namespace = "xidlims/net/xidlims/domain")
public class AssetReceive implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ���������
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

	@Column(name = "receive_no")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String receiveNo;
	
	/**
	 * �깺��������
	 * 
	 */
	@Column(name = "allocation_status")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer allocationStatus;
	

	/**
	 * ����ʱ��
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "receive_date")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar receiveDate;
	/**
	 * ���쿪ʼʱ��
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "start_data")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar startData;
	/**
	 * �������ʱ��
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "end_date")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar endDate;
	/**
	 * ���������������
	 * 
	 */

	@Column(name = "result")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String result;
	/**
	 * ������;
	 * 
	 */

	@Column(name = "asset_usage")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String assetUsage;
	/**
	 * ����״̬1����ͬ��0��ͬ��
	 * 
	 */

	@Column(name = "status")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer status;

	/**
	 * ʵ���������
	 * 
	 */

	@Column(name = "`project_ name`")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String projectName;
	/**
	 */

	@Column(name = "project_content")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String projectContent;
	/**
	 * �����ύ
	 * 
	 */

	@Column(name = "save_submit")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer saveSubmit;
	/**
	 * ��ע
	 * 
	 */

	@Column(name = "mem")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String mem;

	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "app_user", referencedColumnName = "username") })
	@XmlTransient
	User user;
	
	@Column(name = "type")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer type;


	/**
	 */
	@OneToMany(mappedBy = "assetReceive", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.AssetReceiveAudit> assetReceiveAudits;
	/**
	 */
	@OneToMany(mappedBy = "assetReceive", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.AssetReceiveRecord> assetReceiveRecords;
	
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "operation_item_id", referencedColumnName = "id") })
	@XmlTransient
	OperationItem operationItem;
	
	

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
	/**
	 * ���������
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * ���������
	 * 
	 */
	public Integer getId() {
		return this.id;
	}
	public Integer getAllocationStatus() {
		return allocationStatus;
	}

	public void setAllocationStatus(Integer allocationStatus) {
		this.allocationStatus = allocationStatus;
	}
	/**
	 * ������
	 * 
	 */
	public void setReceiveNo(String receiveNo) {
		this.receiveNo = receiveNo;
	}

	/**
	 * ������
	 * 
	 */
	public String getReceiveNo() {
		return this.receiveNo;
	}
	
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * ����ʱ��
	 * 
	 */
	public void setReceiveDate(Calendar receiveDate) {
		this.receiveDate = receiveDate;
	}

	/**
	 * ����ʱ��
	 * 
	 */
	public Calendar getReceiveDate() {
		return this.receiveDate;
	}

	/**
	 * ���쿪ʼʱ��
	 * 
	 */
	public void setStartData(Calendar startData) {
		this.startData = startData;
	}

	/**
	 * ���쿪ʼʱ��
	 * 
	 */
	public Calendar getStartData() {
		return this.startData;
	}

	/**
	 * �������ʱ��
	 * 
	 */
	public void setEndDate(Calendar endDate) {
		this.endDate = endDate;
	}

	/**
	 * �������ʱ��
	 * 
	 */
	public Calendar getEndDate() {
		return this.endDate;
	}

	/**
	 * ���������������
	 * 
	 */
	public void setResult(String result) {
		this.result = result;
	}

	/**
	 * ���������������
	 * 
	 */
	public String getResult() {
		return this.result;
	}

	/**
	 * ������;
	 * 
	 */
	public void setAssetUsage(String assetUsage) {
		this.assetUsage = assetUsage;
	}

	/**
	 * ������;
	 * 
	 */
	public String getAssetUsage() {
		return this.assetUsage;
	}

	/**
	 * ����״̬1����ͬ��0��ͬ��
	 * 
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * ����״̬1����ͬ��0��ͬ��
	 * 
	 */
	public Integer getStatus() {
		return this.status;
	}


	/**
	 * ʵ���������
	 * 
	 */
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	/**
	 * ʵ���������
	 * 
	 */
	public String getProjectName() {
		return this.projectName;
	}

	/**
	 */
	public void setProjectContent(String projectContent) {
		this.projectContent = projectContent;
	}

	/**
	 */
	public String getProjectContent() {
		return this.projectContent;
	}

	/**
	 * �����ύ
	 * 
	 */
	public void setSaveSubmit(Integer saveSubmit) {
		this.saveSubmit = saveSubmit;
	}

	/**
	 * �����ύ
	 * 
	 */
	public Integer getSaveSubmit() {
		return this.saveSubmit;
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
	public void setAssetReceiveAudits(Set<AssetReceiveAudit> assetReceiveAudits) {
		this.assetReceiveAudits = assetReceiveAudits;
	}

	/**
	 */
	@JsonIgnore
	public Set<AssetReceiveAudit> getAssetReceiveAudits() {
		if (assetReceiveAudits == null) {
			assetReceiveAudits = new java.util.LinkedHashSet<net.xidlims.domain.AssetReceiveAudit>();
		}
		return assetReceiveAudits;
	}
	/**
	 */
	public void setAssetReceiveRecords(Set<AssetReceiveRecord> assetReceiveRecords) {
		this.assetReceiveRecords = assetReceiveRecords;
	}

	/**
	 */
	@JsonIgnore
	public Set<AssetReceiveRecord> getAssetReceiveRecords() {
		if (assetReceiveRecords == null) {
			assetReceiveRecords = new java.util.LinkedHashSet<net.xidlims.domain.AssetReceiveRecord>();
		}
		return assetReceiveRecords;
	}

	/**
	 */
	public AssetReceive() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(AssetReceive that) {
		setId(that.getId());
		setReceiveNo(that.getReceiveNo());
		setReceiveDate(that.getReceiveDate());
		setStartData(that.getStartData());
		setEndDate(that.getEndDate());
		setResult(that.getResult());
		setAssetUsage(that.getAssetUsage());
		setStatus(that.getStatus());
		setProjectName(that.getProjectName());
		setProjectContent(that.getProjectContent());
		setSaveSubmit(that.getSaveSubmit());
		setMem(that.getMem());
		setUser(that.getUser());
		setOperationItem(that.getOperationItem());
		setAssetReceiveAudits(new java.util.LinkedHashSet<net.xidlims.domain.AssetReceiveAudit>(that.getAssetReceiveAudits()));
		setAssetReceiveRecords(new java.util.LinkedHashSet<net.xidlims.domain.AssetReceiveRecord>(that.getAssetReceiveRecords()));
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("id=[").append(id).append("] ");
		buffer.append("receiveNo=[").append(receiveNo).append("] ");
		buffer.append("receiveDate=[").append(receiveDate).append("] ");
		buffer.append("startData=[").append(startData).append("] ");
		buffer.append("endDate=[").append(endDate).append("] ");
		buffer.append("result=[").append(result).append("] ");
		buffer.append("assetUsage=[").append(assetUsage).append("] ");
		buffer.append("status=[").append(status).append("] ");
		buffer.append("projectName=[").append(projectName).append("] ");
		buffer.append("projectContent=[").append(projectContent).append("] ");
		buffer.append("saveSubmit=[").append(saveSubmit).append("] ");
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
		if (!(obj instanceof AssetReceive))
			return false;
		AssetReceive equalCheck = (AssetReceive) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
