package net.xidlims.domain;

import java.io.Serializable;

import java.lang.StringBuilder;

import java.math.BigDecimal;

import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.FetchType;
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
		@NamedQuery(name = "findAllNDevicePurchaseDetails", query = "select myNDevicePurchaseDetail from NDevicePurchaseDetail myNDevicePurchaseDetail"),
		@NamedQuery(name = "findNDevicePurchaseDetailByDeviceEnName", query = "select myNDevicePurchaseDetail from NDevicePurchaseDetail myNDevicePurchaseDetail where myNDevicePurchaseDetail.deviceEnName = ?1"),
		@NamedQuery(name = "findNDevicePurchaseDetailByDeviceEnNameContaining", query = "select myNDevicePurchaseDetail from NDevicePurchaseDetail myNDevicePurchaseDetail where myNDevicePurchaseDetail.deviceEnName like ?1"),
		@NamedQuery(name = "findNDevicePurchaseDetailByDeviceFormat", query = "select myNDevicePurchaseDetail from NDevicePurchaseDetail myNDevicePurchaseDetail where myNDevicePurchaseDetail.deviceFormat = ?1"),
		@NamedQuery(name = "findNDevicePurchaseDetailByDeviceFormatContaining", query = "select myNDevicePurchaseDetail from NDevicePurchaseDetail myNDevicePurchaseDetail where myNDevicePurchaseDetail.deviceFormat like ?1"),
		@NamedQuery(name = "findNDevicePurchaseDetailByDeviceModel", query = "select myNDevicePurchaseDetail from NDevicePurchaseDetail myNDevicePurchaseDetail where myNDevicePurchaseDetail.deviceModel = ?1"),
		@NamedQuery(name = "findNDevicePurchaseDetailByDeviceModelContaining", query = "select myNDevicePurchaseDetail from NDevicePurchaseDetail myNDevicePurchaseDetail where myNDevicePurchaseDetail.deviceModel like ?1"),
		@NamedQuery(name = "findNDevicePurchaseDetailByDeviceName", query = "select myNDevicePurchaseDetail from NDevicePurchaseDetail myNDevicePurchaseDetail where myNDevicePurchaseDetail.deviceName = ?1"),
		@NamedQuery(name = "findNDevicePurchaseDetailByDeviceNameContaining", query = "select myNDevicePurchaseDetail from NDevicePurchaseDetail myNDevicePurchaseDetail where myNDevicePurchaseDetail.deviceName like ?1"),
		@NamedQuery(name = "findNDevicePurchaseDetailByDeviceNumber", query = "select myNDevicePurchaseDetail from NDevicePurchaseDetail myNDevicePurchaseDetail where myNDevicePurchaseDetail.deviceNumber = ?1"),
		@NamedQuery(name = "findNDevicePurchaseDetailByDeviceNumberContaining", query = "select myNDevicePurchaseDetail from NDevicePurchaseDetail myNDevicePurchaseDetail where myNDevicePurchaseDetail.deviceNumber like ?1"),
		@NamedQuery(name = "findNDevicePurchaseDetailByDevicePrice", query = "select myNDevicePurchaseDetail from NDevicePurchaseDetail myNDevicePurchaseDetail where myNDevicePurchaseDetail.devicePrice = ?1"),
		@NamedQuery(name = "findNDevicePurchaseDetailByDeviceQuantity", query = "select myNDevicePurchaseDetail from NDevicePurchaseDetail myNDevicePurchaseDetail where myNDevicePurchaseDetail.deviceQuantity = ?1"),
		@NamedQuery(name = "findNDevicePurchaseDetailByDeviceSupplier", query = "select myNDevicePurchaseDetail from NDevicePurchaseDetail myNDevicePurchaseDetail where myNDevicePurchaseDetail.deviceSupplier = ?1"),
		@NamedQuery(name = "findNDevicePurchaseDetailByDeviceSupplierContaining", query = "select myNDevicePurchaseDetail from NDevicePurchaseDetail myNDevicePurchaseDetail where myNDevicePurchaseDetail.deviceSupplier like ?1"),
		@NamedQuery(name = "findNDevicePurchaseDetailByDeviceUnit", query = "select myNDevicePurchaseDetail from NDevicePurchaseDetail myNDevicePurchaseDetail where myNDevicePurchaseDetail.deviceUnit = ?1"),
		@NamedQuery(name = "findNDevicePurchaseDetailByDeviceUnitContaining", query = "select myNDevicePurchaseDetail from NDevicePurchaseDetail myNDevicePurchaseDetail where myNDevicePurchaseDetail.deviceUnit like ?1"),
		@NamedQuery(name = "findNDevicePurchaseDetailById", query = "select myNDevicePurchaseDetail from NDevicePurchaseDetail myNDevicePurchaseDetail where myNDevicePurchaseDetail.id = ?1"),
		@NamedQuery(name = "findNDevicePurchaseDetailByPlace", query = "select myNDevicePurchaseDetail from NDevicePurchaseDetail myNDevicePurchaseDetail where myNDevicePurchaseDetail.place = ?1"),
		@NamedQuery(name = "findNDevicePurchaseDetailByPlaceContaining", query = "select myNDevicePurchaseDetail from NDevicePurchaseDetail myNDevicePurchaseDetail where myNDevicePurchaseDetail.place like ?1"),
		@NamedQuery(name = "findNDevicePurchaseDetailByPrimaryKey", query = "select myNDevicePurchaseDetail from NDevicePurchaseDetail myNDevicePurchaseDetail where myNDevicePurchaseDetail.id = ?1"),
		@NamedQuery(name = "findNDevicePurchaseDetailByRemark", query = "select myNDevicePurchaseDetail from NDevicePurchaseDetail myNDevicePurchaseDetail where myNDevicePurchaseDetail.remark = ?1"),
		@NamedQuery(name = "findNDevicePurchaseDetailByRemarkContaining", query = "select myNDevicePurchaseDetail from NDevicePurchaseDetail myNDevicePurchaseDetail where myNDevicePurchaseDetail.remark like ?1"),
		@NamedQuery(name = "findNDevicePurchaseDetailByUseDirection", query = "select myNDevicePurchaseDetail from NDevicePurchaseDetail myNDevicePurchaseDetail where myNDevicePurchaseDetail.useDirection = ?1"),
		@NamedQuery(name = "findNDevicePurchaseDetailByUseDirectionContaining", query = "select myNDevicePurchaseDetail from NDevicePurchaseDetail myNDevicePurchaseDetail where myNDevicePurchaseDetail.useDirection like ?1") })
@Table(catalog = "xidlims", name = "n_device_purchase_detail")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "NDevicePurchaseDetail")
public class NDevicePurchaseDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * �豸��ϸ��
	 * 
	 */

	@Column(name = "id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@XmlElement
	Integer id;
	/**
	 * �豸���
	 * 
	 */
	@Column(name = "if_passed")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer ifPassed;
	
	/**
	 * ���ѱ��
	 * 
	 */
	@Column(name = "if_passed_center")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer ifPassedCenter;
	
	

	/**
	 * ���ѱ��
	 * 
	 */

	@Column(name = "device_name")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String deviceName;
	/**
	 * �豸Ӣ�����
	 * 
	 */

	@Column(name = "device_en_name")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String deviceEnName;
	/**
	 * �豸���
	 * 
	 */

	@Column(name = "device_number", length = 225)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String deviceNumber;
	/**
	 * ��������
	 * 
	 */

	@Column(name = "device_quantity")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer deviceQuantity;
	/**
	 * �豸�۸�
	 * 
	 */

	@Column(name = "device_price", scale = 2, precision = 10)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal devicePrice;
	/**
	 * �豸���
	 * 
	 */

	@Column(name = "device_format")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String deviceFormat;
	/**
	 * �豸��Ӧ�̣�Ʒ�ƣ�
	 * 
	 */
	@Column(name = "audit_advice")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String auditAdvice;
	
	/**
	 * �豸��Ӧ�̣�Ʒ�ƣ�
	 * 
	 */
	@Column(name = "audit_advice_center")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String auditAdviceCenter;

	

	/**
	 * �豸��Ӧ�̣�Ʒ�ƣ�
	 * 
	 */

	@Column(name = "device_supplier")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String deviceSupplier;
	/**
	 * ʹ�÷���
	 * 
	 */

	@Column(name = "use_direction")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String useDirection;
	/**
	 * ��ע
	 * 
	 */

	@Column(name = "remark")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String remark;
	/**
	 * �豸�ͺ�
	 * 
	 */

	@Column(name = "device_model")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String deviceModel;
	/**
	 * �豸��λ
	 * 
	 */

	@Column(name = "device_unit")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String deviceUnit;
	/**
	 * �豸��ŵص�
	 * 
	 */

	@Column(name = "place")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String place;

	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "device_purchase_id", referencedColumnName = "id") })
	@XmlTransient
	NDevicePurchase NDevicePurchase;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "user", referencedColumnName = "username") })
	@XmlTransient
	User user;
	
	/**
	 */
	@OneToMany(mappedBy = "NDevicePurchaseDetail", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.DevicePurchaseDetailCSupplier> devicePurchaseDetailCSuppliers;
	
	

	/**
	 * �豸��ϸ��
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * �豸��ϸ��
	 * 
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * �豸���
	 * 
	 */
	public String getAuditAdvice() {
		return auditAdvice;
	}

	public void setAuditAdvice(String auditAdvice) {
		this.auditAdvice = auditAdvice;
	}
	/**
	 * �豸���
	 * 
	 */
	public Integer getIfPassed() {
		return ifPassed;
	}

	public void setIfPassed(Integer ifPassed) {
		this.ifPassed = ifPassed;
	}
	/**
	 * �豸���
	 * 
	 */
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	/**
	 * �豸���
	 * 
	 */
	public String getDeviceName() {
		return this.deviceName;
	}

	/**
	 * �豸Ӣ�����
	 * 
	 */
	public void setDeviceEnName(String deviceEnName) {
		this.deviceEnName = deviceEnName;
	}
	public String getAuditAdviceCenter() {
		return auditAdviceCenter;
	}

	public void setAuditAdviceCenter(String auditAdviceCenter) {
		this.auditAdviceCenter = auditAdviceCenter;
	}

	/**
	 * �豸Ӣ�����
	 * 
	 */
	public String getDeviceEnName() {
		return this.deviceEnName;
	}

	/**
	 * �豸���
	 * 
	 */
	public void setDeviceNumber(String deviceNumber) {
		this.deviceNumber = deviceNumber;
	}

	/**
	 * �豸���
	 * 
	 */
	public String getDeviceNumber() {
		return this.deviceNumber;
	}

	/**
	 * ��������
	 * 
	 */
	public void setDeviceQuantity(Integer deviceQuantity) {
		this.deviceQuantity = deviceQuantity;
	}

	/**
	 * ��������
	 * 
	 */
	public Integer getDeviceQuantity() {
		return this.deviceQuantity;
	}

	/**
	 * �豸�۸�
	 * 
	 */
	public void setDevicePrice(BigDecimal devicePrice) {
		this.devicePrice = devicePrice;
	}

	/**
	 * �豸�۸�
	 * 
	 */
	public BigDecimal getDevicePrice() {
		return this.devicePrice;
	}

	/**
	 * �豸���
	 * 
	 */
	public void setDeviceFormat(String deviceFormat) {
		this.deviceFormat = deviceFormat;
	}

	/**
	 * �豸���
	 * 
	 */
	public String getDeviceFormat() {
		return this.deviceFormat;
	}

	/**
	 * �豸��Ӧ�̣�Ʒ�ƣ�
	 * 
	 */
	public void setDeviceSupplier(String deviceSupplier) {
		this.deviceSupplier = deviceSupplier;
	}

	/**
	 * �豸��Ӧ�̣�Ʒ�ƣ�
	 * 
	 */
	public String getDeviceSupplier() {
		return this.deviceSupplier;
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
	 * �豸�ͺ�
	 * 
	 */
	public void setDeviceModel(String deviceModel) {
		this.deviceModel = deviceModel;
	}

	/**
	 * �豸�ͺ�
	 * 
	 */
	public String getDeviceModel() {
		return this.deviceModel;
	}

	/**
	 * �豸��λ
	 * 
	 */
	
	public void setDeviceUnit(String deviceUnit) {
		this.deviceUnit = deviceUnit;
	}

	/**
	 * �豸��λ
	 * 
	 */
	public Integer getIfPassedCenter() {
		return ifPassedCenter;
	}

	public void setIfPassedCenter(Integer ifPassedCenter) {
		this.ifPassedCenter = ifPassedCenter;
	}
	
	public String getDeviceUnit() {
		return this.deviceUnit;
	}

	/**
	 * �豸��ŵص�
	 * 
	 */
	public void setPlace(String place) {
		this.place = place;
	}

	/**
	 * �豸��ŵص�
	 * 
	 */
	public String getPlace() {
		return this.place;
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
	public void setDevicePurchaseDetailCSuppliers(Set<DevicePurchaseDetailCSupplier> devicePurchaseDetailCSuppliers) {
		this.devicePurchaseDetailCSuppliers = devicePurchaseDetailCSuppliers;
	}

	/**
	 */
	@JsonIgnore
	public Set<DevicePurchaseDetailCSupplier> getDevicePurchaseDetailCSuppliers() {
		if (devicePurchaseDetailCSuppliers == null) {
			devicePurchaseDetailCSuppliers = new java.util.LinkedHashSet<net.xidlims.domain.DevicePurchaseDetailCSupplier>();
		}
		return devicePurchaseDetailCSuppliers;
	}

	/**
	 */
	public NDevicePurchaseDetail() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(NDevicePurchaseDetail that) {
		setId(that.getId());
		setDeviceName(that.getDeviceName());
		setDeviceEnName(that.getDeviceEnName());
		setDeviceNumber(that.getDeviceNumber());
		setDeviceQuantity(that.getDeviceQuantity());
		setDevicePrice(that.getDevicePrice());
		setDeviceFormat(that.getDeviceFormat());
		setDeviceSupplier(that.getDeviceSupplier());
		setUseDirection(that.getUseDirection());
		setRemark(that.getRemark());
		setDeviceModel(that.getDeviceModel());
		setDeviceUnit(that.getDeviceUnit());
		setPlace(that.getPlace());
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
		buffer.append("deviceName=[").append(deviceName).append("] ");
		buffer.append("deviceEnName=[").append(deviceEnName).append("] ");
		buffer.append("deviceNumber=[").append(deviceNumber).append("] ");
		buffer.append("deviceQuantity=[").append(deviceQuantity).append("] ");
		buffer.append("devicePrice=[").append(devicePrice).append("] ");
		buffer.append("deviceFormat=[").append(deviceFormat).append("] ");
		buffer.append("deviceSupplier=[").append(deviceSupplier).append("] ");
		buffer.append("useDirection=[").append(useDirection).append("] ");
		buffer.append("remark=[").append(remark).append("] ");
		buffer.append("deviceModel=[").append(deviceModel).append("] ");
		buffer.append("deviceUnit=[").append(deviceUnit).append("] ");
		buffer.append("place=[").append(place).append("] ");

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
		if (!(obj instanceof NDevicePurchaseDetail))
			return false;
		NDevicePurchaseDetail equalCheck = (NDevicePurchaseDetail) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
