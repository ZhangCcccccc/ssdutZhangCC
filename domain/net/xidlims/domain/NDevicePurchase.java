package net.xidlims.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import org.codehaus.jackson.annotate.JsonIgnore;

/**
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "findAllNDevicePurchases", query = "select myNDevicePurchase from NDevicePurchase myNDevicePurchase"),
		@NamedQuery(name = "findNDevicePurchaseByAuditStatus", query = "select myNDevicePurchase from NDevicePurchase myNDevicePurchase where myNDevicePurchase.auditStatus = ?1"),
		@NamedQuery(name = "findNDevicePurchaseByContactInformation", query = "select myNDevicePurchase from NDevicePurchase myNDevicePurchase where myNDevicePurchase.contactInformation = ?1"),
		@NamedQuery(name = "findNDevicePurchaseByContactInformationContaining", query = "select myNDevicePurchase from NDevicePurchase myNDevicePurchase where myNDevicePurchase.contactInformation like ?1"),
		@NamedQuery(name = "findNDevicePurchaseByCostCode", query = "select myNDevicePurchase from NDevicePurchase myNDevicePurchase where myNDevicePurchase.costCode = ?1"),
		@NamedQuery(name = "findNDevicePurchaseByCostCodeContaining", query = "select myNDevicePurchase from NDevicePurchase myNDevicePurchase where myNDevicePurchase.costCode like ?1"),
		@NamedQuery(name = "findNDevicePurchaseByCreateDate", query = "select myNDevicePurchase from NDevicePurchase myNDevicePurchase where myNDevicePurchase.createDate = ?1"),
		@NamedQuery(name = "findNDevicePurchaseByDevicePurchaseReason", query = "select myNDevicePurchase from NDevicePurchase myNDevicePurchase where myNDevicePurchase.devicePurchaseReason = ?1"),
		@NamedQuery(name = "findNDevicePurchaseById", query = "select myNDevicePurchase from NDevicePurchase myNDevicePurchase where myNDevicePurchase.id = ?1"),
		@NamedQuery(name = "findNDevicePurchaseByIfEmergency", query = "select myNDevicePurchase from NDevicePurchase myNDevicePurchase where myNDevicePurchase.ifEmergency = ?1"),
		@NamedQuery(name = "findNDevicePurchaseByModifyDate", query = "select myNDevicePurchase from NDevicePurchase myNDevicePurchase where myNDevicePurchase.modifyDate = ?1"),
		@NamedQuery(name = "findNDevicePurchaseByPrimaryKey", query = "select myNDevicePurchase from NDevicePurchase myNDevicePurchase where myNDevicePurchase.id = ?1"),
		@NamedQuery(name = "findNDevicePurchaseByPurchaseCost", query = "select myNDevicePurchase from NDevicePurchase myNDevicePurchase where myNDevicePurchase.purchaseCost = ?1"),
		@NamedQuery(name = "findNDevicePurchaseByPurchaseFormat", query = "select myNDevicePurchase from NDevicePurchase myNDevicePurchase where myNDevicePurchase.purchaseFormat = ?1"),
		@NamedQuery(name = "findNDevicePurchaseByPurchaseFormatContaining", query = "select myNDevicePurchase from NDevicePurchase myNDevicePurchase where myNDevicePurchase.purchaseFormat like ?1"),
		@NamedQuery(name = "findNDevicePurchaseByPurchaseNumber", query = "select myNDevicePurchase from NDevicePurchase myNDevicePurchase where myNDevicePurchase.purchaseNumber = ?1"),
		@NamedQuery(name = "findNDevicePurchaseByPurchaseNumberContaining", query = "select myNDevicePurchase from NDevicePurchase myNDevicePurchase where myNDevicePurchase.purchaseNumber like ?1"),
		@NamedQuery(name = "findNDevicePurchaseByUseDirection", query = "select myNDevicePurchase from NDevicePurchase myNDevicePurchase where myNDevicePurchase.useDirection = ?1"),
		@NamedQuery(name = "findNDevicePurchaseByUseDirectionContaining", query = "select myNDevicePurchase from NDevicePurchase myNDevicePurchase where myNDevicePurchase.useDirection like ?1") })
@Table(catalog = "xidlims", name = "n_device_purchase")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "NDevicePurchase")
@XmlRootElement(namespace = "xidlims/net/xidlims/domain")
public class NDevicePurchase implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * �豸�깺����������
	 * 
	 */

	@Column(name = "id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@XmlElement
	Integer id;
	/**
	 * ʹ�÷���
	 * 
	 */

	@Column(name = "use_direction")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String useDirection;
	/**
     * ����ʱ��
     * 
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "end_date")
    @Basic(fetch = FetchType.EAGER)
    @XmlElement
    Calendar endDate;
	

	/**
	 * �豸�깺����
	 * 
	 */
	

	@Column(name = "device_purchase_reason", columnDefinition = "TEXT")
	@Basic(fetch = FetchType.EAGER)
	@Lob
	@XmlElement
	String devicePurchaseReason;
	/**
	 * ���״̬��0��ʾδ��ˣ�1��ʾ��˾ܾ�2��ʾ���ͨ��
	 * 
	 */
	@Column(name = "cost_name", columnDefinition = "TEXT")
	@Basic(fetch = FetchType.EAGER)
	@Lob
	@XmlElement
	String costName;

	/**
	 * ���״̬��0��ʾδ��ˣ�1��ʾ��˾ܾ�2��ʾ���ͨ��
	 * 
	 */

	@Column(name = "audit_status")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer auditStatus;
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
	 * �޸�����
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modify_date")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar modifyDate;
	/**
	 * �깺���
	 * 
	 */

	@Column(name = "purchase_number", length = 225)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String purchaseNumber;
	/**
	 * �깺���
	 * 
	 */
	@Column(name = "audit_advice", length = 225)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String auditAdvice;
	/**
	 * �깺���
	 * 
	 */
	@Column(name = "audit_advice_center", length = 225)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String auditAdviceCenter;
	

	/**
	 * �깺���
	 * 
	 */

	@Column(name = "purchase_cost", scale = 2, precision = 10)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal purchaseCost;
	/**
	 * ��ϵ��ʽ
	 * 
	 */

	@Column(name = "contact_information")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String contactInformation;
	/**
	 * ���˵��
	 * 
	 */

	@Column(name = "purchase_format")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String purchaseFormat;
	/**
	 * �Ƿ������0�Ƿ�1Ϊ�ǣ�
	 * 
	 */

	@Column(name = "if_emergency")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer ifEmergency;
	
	@Column(name = "delay_date")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer delayDate;
	

	/**
	 * ���ѱ��
	 * 
	 */

	@Column(name = "cost_code")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String costCode;

	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "device_status", referencedColumnName = "id") })
	@XmlTransient
	CDeviceStatus CDeviceStatus;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "cost_reason", referencedColumnName = "id") })
	@XmlTransient
	CDictionary CDictionaryByCostReason;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "cost_type", referencedColumnName = "id") })
	@XmlTransient
	CDictionary CDictionaryByCostType;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "creator", referencedColumnName = "username") })
	@XmlTransient
	User user;
	/**
	 */
	@OneToMany(mappedBy = "NDevicePurchase", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.NDeviceAuditRecord> NDeviceAuditRecords;
	/**
	 */
	@OneToMany(mappedBy = "NDevicePurchase", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.NDevicePurchaseDetail> NDevicePurchaseDetails;

	/**
	 */
	@OneToMany(mappedBy = "NDevicePurchase", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.DeviceStatusRecord> deviceStatusRecords;
	/**
	 * �豸�깺����������
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * �豸�깺����������
	 * 
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * ʹ�÷���
	 * 
	 */
	public String getAuditAdvice() {
		return auditAdvice;
	}

	public void setAuditAdvice(String auditAdvice) {
		this.auditAdvice = auditAdvice;
	}
	/**
	 * ʹ�÷���
	 * 
	 */

	public String getAuditAdviceCenter() {
		return auditAdviceCenter;
	}

	public void setAuditAdviceCenter(String auditAdviceCenter) {
		this.auditAdviceCenter = auditAdviceCenter;
	}
	/**
	 * ʹ�÷���
	 * 
	 */
	public void setUseDirection(String useDirection) {
		this.useDirection = useDirection;
	}

	/**
	 * ʹ�÷���
	 * 
	 */
	public String getUseDirection() {
		return this.useDirection;
	}

	/**
	 * �豸�깺����
	 * 
	 */
	public void setDevicePurchaseReason(String devicePurchaseReason) {
		this.devicePurchaseReason = devicePurchaseReason;
	}

	/**
	 * �豸�깺����
	 * 
	 */
	public String getDevicePurchaseReason() {
		return this.devicePurchaseReason;
	}

	/**
	 * ���״̬��0��ʾδ��ˣ�1��ʾ��˾ܾ�2��ʾ���ͨ��
	 * 
	 */
	public Calendar getEndDate() {
		return endDate;
	}

	public void setEndDate(Calendar endDate) {
		this.endDate = endDate;
	}
	public void setAuditStatus(Integer auditStatus) {
		this.auditStatus = auditStatus;
	}

	/**
	 * ���״̬��0��ʾδ��ˣ�1��ʾ��˾ܾ�2��ʾ���ͨ��
	 * 
	 */
	public Integer getAuditStatus() {
		return this.auditStatus;
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
	public String getCostName() {
		return costName;
	}

	public void setCostName(String costName) {
		this.costName = costName;
	}

	/**
	 * ���״̬��0��ʾδ��ˣ�1��ʾ��˾ܾ�2��ʾ���ͨ��
	 * 
	 */
	public Calendar getCreateDate() {
		return this.createDate;
	}

	/**
	 * �޸�����
	 * 
	 */
	public void setModifyDate(Calendar modifyDate) {
		this.modifyDate = modifyDate;
	}

	/**
	 * �޸�����
	 * 
	 */
	public Calendar getModifyDate() {
		return this.modifyDate;
	}

	/**
	 * �깺���
	 * 
	 */
	public void setPurchaseNumber(String purchaseNumber) {
		this.purchaseNumber = purchaseNumber;
	}

	/**
	 * �깺���
	 * 
	 */
	public String getPurchaseNumber() {
		return this.purchaseNumber;
	}

	/**
	 * �깺���
	 * 
	 */
	public void setPurchaseCost(BigDecimal purchaseCost) {
		this.purchaseCost = purchaseCost;
	}

	/**
	 * �깺���
	 * 
	 */
	public BigDecimal getPurchaseCost() {
		return this.purchaseCost;
	}

	/**
	 * ��ϵ��ʽ
	 * 
	 */
	public void setContactInformation(String contactInformation) {
		this.contactInformation = contactInformation;
	}

	/**
	 * ��ϵ��ʽ
	 * 
	 */
	public String getContactInformation() {
		return this.contactInformation;
	}

	/**
	 * ���˵��
	 * 
	 */
	public void setPurchaseFormat(String purchaseFormat) {
		this.purchaseFormat = purchaseFormat;
	}

	/**
	 * ���˵��
	 * 
	 */
	public String getPurchaseFormat() {
		return this.purchaseFormat;
	}

	/**
	 * �Ƿ������0�Ƿ�1Ϊ�ǣ�
	 * 
	 */
	public void setIfEmergency(Integer ifEmergency) {
		this.ifEmergency = ifEmergency;
	}

	/**
	 * �Ƿ������0�Ƿ�1Ϊ�ǣ�
	 * 
	 */
	public Integer getIfEmergency() {
		return this.ifEmergency;
	}

	/**
	 * ���ѱ��
	 * 
	 */
	public void setCostCode(String costCode) {
		this.costCode = costCode;
	}

	/**
	 * ���ѱ��
	 * 
	 */
	public String getCostCode() {
		return this.costCode;
	}

	/**
	 */
	public void setCDeviceStatus(CDeviceStatus CDeviceStatus) {
		this.CDeviceStatus = CDeviceStatus;
	}

	/**
	 */
	@JsonIgnore
	public CDeviceStatus getCDeviceStatus() {
		return CDeviceStatus;
	}

	/**
	 */
	public void setCDictionaryByCostReason(CDictionary CDictionaryByCostReason) {
		this.CDictionaryByCostReason = CDictionaryByCostReason;
	}

	/**
	 */
	@JsonIgnore
	public CDictionary getCDictionaryByCostReason() {
		return CDictionaryByCostReason;
	}

	/**
	 */
	public void setCDictionaryByCostType(CDictionary CDictionaryByCostType) {
		this.CDictionaryByCostType = CDictionaryByCostType;
	}

	
	public Integer getDelayDate() {
		return delayDate;
	}

	public void setDelayDate(Integer delayDate) {
		this.delayDate = delayDate;
	}
	/**
	 */
	@JsonIgnore
	public CDictionary getCDictionaryByCostType() {
		return CDictionaryByCostType;
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
	public void setNDeviceAuditRecords(Set<NDeviceAuditRecord> NDeviceAuditRecords) {
		this.NDeviceAuditRecords = NDeviceAuditRecords;
	}

	/**
	 */
	@JsonIgnore
	public Set<NDeviceAuditRecord> getNDeviceAuditRecords() {
		if (NDeviceAuditRecords == null) {
			NDeviceAuditRecords = new java.util.LinkedHashSet<net.xidlims.domain.NDeviceAuditRecord>();
		}
		return NDeviceAuditRecords;
	}

	/**
	 */
	public void setNDevicePurchaseDetails(Set<NDevicePurchaseDetail> NDevicePurchaseDetails) {
		this.NDevicePurchaseDetails = NDevicePurchaseDetails;
	}

	/**
	 */
	@JsonIgnore
	public Set<NDevicePurchaseDetail> getNDevicePurchaseDetails() {
		if (NDevicePurchaseDetails == null) {
			NDevicePurchaseDetails = new java.util.LinkedHashSet<net.xidlims.domain.NDevicePurchaseDetail>();
		}
		return NDevicePurchaseDetails;
	}
	
	/**
	 */
	public void setDeviceStatusRecords(Set<DeviceStatusRecord> deviceStatusRecords) {
		this.deviceStatusRecords = deviceStatusRecords;
	}

	/**
	 */
	@JsonIgnore
	public Set<DeviceStatusRecord> getDeviceStatusRecords() {
		if (deviceStatusRecords == null) {
			deviceStatusRecords = new java.util.LinkedHashSet<net.xidlims.domain.DeviceStatusRecord>();
		}
		return deviceStatusRecords;
	}

	/**
	 */
	public NDevicePurchase() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(NDevicePurchase that) {
		setId(that.getId());
		setUseDirection(that.getUseDirection());
		setDevicePurchaseReason(that.getDevicePurchaseReason());
		setAuditStatus(that.getAuditStatus());
		setCreateDate(that.getCreateDate());
		setModifyDate(that.getModifyDate());
		setPurchaseNumber(that.getPurchaseNumber());
		setPurchaseCost(that.getPurchaseCost());
		setContactInformation(that.getContactInformation());
		setPurchaseFormat(that.getPurchaseFormat());
		setIfEmergency(that.getIfEmergency());
		setCostCode(that.getCostCode());
		setCDeviceStatus(that.getCDeviceStatus());
		setCDictionaryByCostReason(that.getCDictionaryByCostReason());
		setCDictionaryByCostType(that.getCDictionaryByCostType());
		setUser(that.getUser());
		setNDeviceAuditRecords(new java.util.LinkedHashSet<net.xidlims.domain.NDeviceAuditRecord>(that.getNDeviceAuditRecords()));
		setNDevicePurchaseDetails(new java.util.LinkedHashSet<net.xidlims.domain.NDevicePurchaseDetail>(that.getNDevicePurchaseDetails()));
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("id=[").append(id).append("] ");
		buffer.append("useDirection=[").append(useDirection).append("] ");
		buffer.append("devicePurchaseReason=[").append(devicePurchaseReason).append("] ");
		buffer.append("auditStatus=[").append(auditStatus).append("] ");
		buffer.append("createDate=[").append(createDate).append("] ");
		buffer.append("modifyDate=[").append(modifyDate).append("] ");
		buffer.append("purchaseNumber=[").append(purchaseNumber).append("] ");
		buffer.append("purchaseCost=[").append(purchaseCost).append("] ");
		buffer.append("contactInformation=[").append(contactInformation).append("] ");
		buffer.append("purchaseFormat=[").append(purchaseFormat).append("] ");
		buffer.append("ifEmergency=[").append(ifEmergency).append("] ");
		buffer.append("costCode=[").append(costCode).append("] ");

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
		if (!(obj instanceof NDevicePurchase))
			return false;
		NDevicePurchase equalCheck = (NDevicePurchase) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
