package net.xidlims.domain;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import net.xidlims.domain.LabRoomDeviceReservation;
import net.xidlims.domain.LabRoomDeviceTraining;
import net.xidlims.domain.TimetableLabRelatedDevice;

import org.codehaus.jackson.annotate.JsonIgnore;

/**
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "findAllLabRoomDevices", query = "select myLabRoomDevice from LabRoomDevice myLabRoomDevice"),
		@NamedQuery(name = "findLabRoomDeviceByApplications", query = "select myLabRoomDevice from LabRoomDevice myLabRoomDevice where myLabRoomDevice.applications = ?1"),
		@NamedQuery(name = "findLabRoomDeviceByDimensionalCode", query = "select myLabRoomDevice from LabRoomDevice myLabRoomDevice where myLabRoomDevice.dimensionalCode = ?1"),
		@NamedQuery(name = "findLabRoomDeviceByDimensionalCodeContaining", query = "select myLabRoomDevice from LabRoomDevice myLabRoomDevice where myLabRoomDevice.dimensionalCode like ?1"),
		@NamedQuery(name = "findLabRoomDeviceByFeatures", query = "select myLabRoomDevice from LabRoomDevice myLabRoomDevice where myLabRoomDevice.features = ?1"),
		@NamedQuery(name = "findLabRoomDeviceByFunction", query = "select myLabRoomDevice from LabRoomDevice myLabRoomDevice where myLabRoomDevice.function = ?1"),
		@NamedQuery(name = "findLabRoomDeviceById", query = "select myLabRoomDevice from LabRoomDevice myLabRoomDevice where myLabRoomDevice.id = ?1"),
		@NamedQuery(name = "findLabRoomDeviceByIndicators", query = "select myLabRoomDevice from LabRoomDevice myLabRoomDevice where myLabRoomDevice.indicators = ?1"),
		@NamedQuery(name = "findLabRoomDeviceByPrice", query = "select myLabRoomDevice from LabRoomDevice myLabRoomDevice where myLabRoomDevice.price = ?1"),
		@NamedQuery(name = "findLabRoomDeviceByPrimaryKey", query = "select myLabRoomDevice from LabRoomDevice myLabRoomDevice where myLabRoomDevice.id = ?1") })
@Table(catalog = "xidlims", name = "lab_room_device")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "LabRoomDevice")
public class LabRoomDevice implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ʵ���ҷ������ñ�
	 * 
	 */

	@Column(name = "id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	@GeneratedValue(strategy = GenerationType.AUTO)
	Integer id;
	/**
	 * ��Ҫ����ָ��
	 * 
	 */

	@Column(name = "indicators", columnDefinition = "TEXT")
	@Basic(fetch = FetchType.EAGER)
	@Lob
	@XmlElement
	String indicators;
	/**
	 * ����
	 * 
	 */

	@Column(name = "price", scale = 2, precision = 11)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal price;
	/**
	 * ����/Ӧ�÷�Χ
	 * 
	 */

	@Column(name = "function", columnDefinition = "TEXT")
	@Basic(fetch = FetchType.EAGER)
	@Lob
	@XmlElement
	String function;
	/**
	 * ������ɫ
	 * 
	 */

	@Column(name = "features", columnDefinition = "TEXT")
	@Basic(fetch = FetchType.EAGER)
	@Lob
	@XmlElement
	String features;
	/**
	 * ��Ҫ���Ժ�Ӧ������
	 * 
	 */

	@Column(name = "applications", columnDefinition = "TEXT")
	@Basic(fetch = FetchType.EAGER)
	@Lob
	@XmlElement
	String applications;
	/**
	 * ��ά��url
	 * 
	 */

	@Column(name = "dimensionalCode")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String dimensionalCode;
	
	@Column(name = "manager_telephone", length = 40)
	@Basic(fetch = FetchType.EAGER)
	@Lob
	@XmlElement
	String managerTelephone;
	
	@Column(name = "manager_mail", length = 40)
	@Basic(fetch = FetchType.EAGER)
	@Lob
	@XmlElement
	String managerMail;
	
	@Column(name = "manager_office", length = 40)
	@Basic(fetch = FetchType.EAGER)
	@Lob
	@XmlElement
	String managerOffice;

	/**
	 *实验室图片中的定位：x坐标
	 * 
	 */

	@Column(name = "x_coordinate", scale = 2, precision = 11)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal xCoordinate;
	
	/**
	 * 实验室图片中的定位：y坐标
	 * 
	 */

	@Column(name = "y_coordinate", scale = 2, precision = 11)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal yCoordinate;
	
	@Column(name = "is_special")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer isSpecial; 
	
	@Column(name = "qRCode_url", length = 255)
	@Basic(fetch = FetchType.EAGER)
	@Lob
	@XmlElement
	String qRCodeUrl;

	public String getqRCodeUrl() {
		return qRCodeUrl;
	}

	public void setqRCodeUrl(String qRCodeUrl) {
		this.qRCodeUrl = qRCodeUrl;
	}

	public BigDecimal getxCoordinate() {
		return xCoordinate;
	}

	public void setxCoordinate(BigDecimal xCoordinate) {
		this.xCoordinate = xCoordinate;
	}

	public BigDecimal getyCoordinate() {
		return yCoordinate;
	}

	public void setyCoordinate(BigDecimal yCoordinate) {
		this.yCoordinate = yCoordinate;
	}

	@Column(name = "is_audit_time_limit")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer isAuditTimeLimit;
	
	@Column(name = "audit_time_limit")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer auditTimeLimit;	
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "teacherAudit", referencedColumnName = "id") })
	@XmlTransient
	CDictionary CDictionaryByTeacherAudit;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "allow_lending", referencedColumnName = "id") })
	@XmlTransient
	CDictionary CDictionaryByAllowLending;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "device_type", referencedColumnName = "id") })
	@XmlTransient
	CDictionary CDictionaryByDeviceType;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "allow_appointment", referencedColumnName = "id") })
	@XmlTransient
	CDictionary CDictionaryByAllowAppointment;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "school_device_id", referencedColumnName = "device_number") })
	@XmlTransient
	SchoolDevice schoolDevice;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "manager_user", referencedColumnName = "username") })
	@XmlTransient
	User user;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "managerAudit", referencedColumnName = "id") })
	@XmlTransient
	CDictionary CDictionaryByManagerAudit;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "security_access_type", referencedColumnName = "id") })
	@XmlTransient
	CDictionary CDictionaryBySecurityAccessType;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "lab_room_id", referencedColumnName = "id") })
	@XmlTransient
	LabRoom labRoom;
	
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "labManagerAudit", referencedColumnName = "id") })
	@XmlTransient
	CDictionary CDictionaryByLabManagerAudit;
	
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "appointment_type", referencedColumnName = "id") })
	@XmlTransient
	CDictionary CDictionaryByAppointmentType;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "device_charge", referencedColumnName = "id") })
	@XmlTransient
	CDictionary CDictionaryByDeviceCharge;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "device_status", referencedColumnName = "id") })
	@XmlTransient
	CDictionary CDictionaryByDeviceStatus;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "allow_security_access", referencedColumnName = "id") })
	@XmlTransient
	CDictionary CDictionaryByAllowSecurityAccess;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "isAudit", referencedColumnName = "id") })
	@XmlTransient
	CDictionary CDictionaryByIsAudit;
	
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "train_type", referencedColumnName = "id") })
	@XmlTransient
	CDictionary CDictionaryByTrainType;
	
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "agent_id", referencedColumnName = "id") })
	@XmlTransient
	LabRoomAgent labRoomAgent;
	/**
	 */
	@OneToMany(mappedBy = "labRoomDevice", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.CommonDocument> commonDocuments;
	
	/**
	 */
	@OneToMany(mappedBy = "labRoomDevice", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.CommonVideo> commonVideos;

	/**
	 */
	@OneToMany(mappedBy = "labRoomDevice", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.LabRoomDeviceTraining> labRoomDeviceTrainings;
	/**
	 */
	@OneToMany(mappedBy = "labRoomDevice", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.LabRoomDevicePermitUsers> labRoomDevicePermitUserses;
	/**
	 */
	@OneToMany(mappedBy = "labRoomDevice", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.TimetableLabRelatedDevice> timetableLabRelatedDevices;
	//2015.10.13新增
	/**
	 */
	@OneToMany(mappedBy = "labRoomDevice", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.LabRoomDeviceReservation> labRoomDeviceReservations;
	
	
	public Integer getIsSpecial() {
		return isSpecial;
	}

	public void setIsSpecial(Integer isSpecial) {
		this.isSpecial = isSpecial;
	}

	/**
	 * ʵ���ҷ������ñ�
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * ʵ���ҷ������ñ�
	 * 
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * ��Ҫ����ָ��
	 * 
	 */
	public void setIndicators(String indicators) {
		this.indicators = indicators;
	}

	/**
	 * ��Ҫ����ָ��
	 * 
	 */
	public String getIndicators() {
		return this.indicators;
	}

	/**
	 * ����
	 * 
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	/**
	 * ����
	 * 
	 */
	public BigDecimal getPrice() {
		return this.price;
	}

	/**
	 * ����/Ӧ�÷�Χ
	 * 
	 */
	public void setFunction(String function) {
		this.function = function;
	}

	/**
	 * ����/Ӧ�÷�Χ
	 * 
	 */
	public String getFunction() {
		return this.function;
	}

	/**
	 * ������ɫ
	 * 
	 */
	public void setFeatures(String features) {
		this.features = features;
	}

	/**
	 * ������ɫ
	 * 
	 */
	public String getFeatures() {
		return this.features;
	}

	/**
	 * ��Ҫ���Ժ�Ӧ������
	 * 
	 */
	public void setApplications(String applications) {
		this.applications = applications;
	}

	/**
	 * ��Ҫ���Ժ�Ӧ������
	 * 
	 */
	public String getApplications() {
		return this.applications;
	}

	/**
	 * ��ά��url
	 * 
	 */
	public void setDimensionalCode(String dimensionalCode) {
		this.dimensionalCode = dimensionalCode;
	}

	/**
	 * ��ά��url
	 * 
	 */
	public String getDimensionalCode() {
		return this.dimensionalCode;
	}
	
	public String getManagerTelephone() {
		return managerTelephone;
	}

	public void setManagerTelephone(String managerTelephone) {
		this.managerTelephone = managerTelephone;
	}

	public String getManagerMail() {
		return managerMail;
	}

	public void setManagerMail(String managerMail) {
		this.managerMail = managerMail;
	}

	public String getManagerOffice() {
		return managerOffice;
	}

	public void setManagerOffice(String managerOffice) {
		this.managerOffice = managerOffice;
	}
	
	public Integer getIsAuditTimeLimit() {
		return isAuditTimeLimit;
	}

	public void setIsAuditTimeLimit(Integer isAuditTimeLimit) {
		this.isAuditTimeLimit = isAuditTimeLimit;
	}

	public Integer getAuditTimeLimit() {
		return auditTimeLimit;
	}

	public void setAuditTimeLimit(Integer auditTimeLimit) {
		this.auditTimeLimit = auditTimeLimit;
	}
	/**
	 */
	public void setCDictionaryByTeacherAudit(CDictionary CDictionaryByTeacherAudit) {
		this.CDictionaryByTeacherAudit = CDictionaryByTeacherAudit;
	}

	/**
	 */
	@JsonIgnore
	public CDictionary getCDictionaryByTeacherAudit() {
		return CDictionaryByTeacherAudit;
	}

	/**
	 */
	public void setCDictionaryByAllowLending(CDictionary CDictionaryByAllowLending) {
		this.CDictionaryByAllowLending = CDictionaryByAllowLending;
	}

	/**
	 */
	@JsonIgnore
	public CDictionary getCDictionaryByAllowLending() {
		return CDictionaryByAllowLending;
	}

	/**
	 */
	public void setCDictionaryByDeviceType(CDictionary CDictionaryByDeviceType) {
		this.CDictionaryByDeviceType = CDictionaryByDeviceType;
	}

	/**
	 */
	@JsonIgnore
	public CDictionary getCDictionaryByDeviceType() {
		return CDictionaryByDeviceType;
	}

	/**
	 */
	public void setCDictionaryByAllowAppointment(CDictionary CDictionaryByAllowAppointment) {
		this.CDictionaryByAllowAppointment = CDictionaryByAllowAppointment;
	}

	/**
	 */
	@JsonIgnore
	public CDictionary getCDictionaryByAllowAppointment() {
		return CDictionaryByAllowAppointment;
	}

	/**
	 */
	public void setCActiveByLabManagerAudit(CDictionary CDictionaryByLabManagerAudit) {
		this.CDictionaryByLabManagerAudit = CDictionaryByLabManagerAudit;
	}

	/**
	 */
	@JsonIgnore
	public CDictionary getCActiveByLabManagerAudit() {
		return CDictionaryByLabManagerAudit;
	}
	
	/**
	 */
	public void setSchoolDevice(SchoolDevice schoolDevice) {
		this.schoolDevice = schoolDevice;
	}

	/**
	 */
	@JsonIgnore
	public SchoolDevice getSchoolDevice() {
		return schoolDevice;
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
	public void setCDictionaryByTrainType(CDictionary CDictionaryByTrainType) {
		this.CDictionaryByTrainType = CDictionaryByTrainType;
	}

	/**
	 */
	public CDictionary getCDictionaryByTrainType() {
		return CDictionaryByTrainType;
	}
	
	/**
	 */
	public void setCDictionaryByManagerAudit(CDictionary CDictionaryByManagerAudit) {
		this.CDictionaryByManagerAudit = CDictionaryByManagerAudit;
	}

	/**
	 */
	@JsonIgnore
	public CDictionary getCDictionaryByManagerAudit() {
		return CDictionaryByManagerAudit;
	}

	/**
	 */
	public void setCDictionaryBySecurityAccessType(CDictionary CDictionaryBySecurityAccessType) {
		this.CDictionaryBySecurityAccessType = CDictionaryBySecurityAccessType;
	}
	/**
	 */
	public void setLabRoomDevicePermitUserses(Set<LabRoomDevicePermitUsers> labRoomDevicePermitUserses) {
		this.labRoomDevicePermitUserses = labRoomDevicePermitUserses;
	}

	/**
	 */
	public Set<LabRoomDevicePermitUsers> getLabRoomDevicePermitUserses() {
		if (labRoomDevicePermitUserses == null) {
			labRoomDevicePermitUserses = new java.util.LinkedHashSet<net.xidlims.domain.LabRoomDevicePermitUsers>();
		}
		return labRoomDevicePermitUserses;
	}
	/**
	 */
	@JsonIgnore
	public CDictionary getCDictionaryBySecurityAccessType() {
		return CDictionaryBySecurityAccessType;
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
	public void setLabRoomAgent(LabRoomAgent labRoomAgent) {
		this.labRoomAgent = labRoomAgent;
	}

	/**
	 */
	@JsonIgnore
	public LabRoomAgent getLabRoomAgent() {
		return labRoomAgent;
	}

	/**
	 */
	public void setCDictionaryByAppointmentType(CDictionary CDictionaryByAppointmentType) {
		this.CDictionaryByAppointmentType = CDictionaryByAppointmentType;
	}

	/**
	 */
	@JsonIgnore
	public CDictionary getCDictionaryByAppointmentType() {
		return CDictionaryByAppointmentType;
	}

	/**
	 */
	public void setCDictionaryByDeviceCharge(CDictionary CDictionaryByDeviceCharge) {
		this.CDictionaryByDeviceCharge = CDictionaryByDeviceCharge;
	}

	/**
	 */
	@JsonIgnore
	public CDictionary getCDictionaryByDeviceCharge() {
		return CDictionaryByDeviceCharge;
	}

	/**
	 */
	public void setCDictionaryByDeviceStatus(CDictionary CDictionaryByDeviceStatus) {
		this.CDictionaryByDeviceStatus = CDictionaryByDeviceStatus;
	}

	/**
	 */
	@JsonIgnore
	public CDictionary getCDictionaryByDeviceStatus() {
		return CDictionaryByDeviceStatus;
	}

	/**
	 */
	public void setCDictionaryByAllowSecurityAccess(CDictionary CDictionaryByAllowSecurityAccess) {
		this.CDictionaryByAllowSecurityAccess = CDictionaryByAllowSecurityAccess;
	}

	/**
	 */
	@JsonIgnore
	public CDictionary getCDictionaryByAllowSecurityAccess() {
		return CDictionaryByAllowSecurityAccess;
	}

	/**
	 */
	public void setCDictionaryByIsAudit(CDictionary CDictionaryByIsAudit) {
		this.CDictionaryByIsAudit = CDictionaryByIsAudit;
	}

	/**
	 */
	@JsonIgnore
	public CDictionary getCDictionaryByIsAudit() {
		return CDictionaryByIsAudit;
	}
	
	
	/**
	 */
	public void setCommonVideos(Set<CommonVideo> commonVideos) {
		this.commonVideos = commonVideos;
	}

	/**
	 */
	@JsonIgnore
	public Set<CommonVideo> getCommonVideos() {
		if (commonVideos == null) {
			commonVideos = new java.util.LinkedHashSet<net.xidlims.domain.CommonVideo>();
		}
		return commonVideos;
	}
	
	/**
	 */
	public void setLabRoomDeviceTrainings(Set<LabRoomDeviceTraining> labRoomDeviceTrainings) {
		this.labRoomDeviceTrainings = labRoomDeviceTrainings;
	}

	/**
	 */
	@JsonIgnore
	public Set<LabRoomDeviceTraining> getLabRoomDeviceTrainings() {
		if (labRoomDeviceTrainings == null) {
			labRoomDeviceTrainings = new java.util.LinkedHashSet<net.xidlims.domain.LabRoomDeviceTraining>();
		}
		return labRoomDeviceTrainings;
	}
	
	
	/**
	 */
	public void setCommonDocuments(Set<CommonDocument> commonDocuments) {
		this.commonDocuments = commonDocuments;
	}

	/**
	 */
	@JsonIgnore
	public Set<CommonDocument> getCommonDocuments() {
		if (commonDocuments == null) {
			commonDocuments = new java.util.LinkedHashSet<net.xidlims.domain.CommonDocument>();
		}
		return commonDocuments;
	}
	/**
	 */
	public void setTimetableLabRelatedDevices(Set<TimetableLabRelatedDevice> timetableLabRelatedDevices) {
		this.timetableLabRelatedDevices = timetableLabRelatedDevices;
	}

	/**
	 */
	public Set<TimetableLabRelatedDevice> getTimetableLabRelatedDevices() {
		if (timetableLabRelatedDevices == null) {
			timetableLabRelatedDevices = new java.util.LinkedHashSet<net.xidlims.domain.TimetableLabRelatedDevice>();
		}
		return timetableLabRelatedDevices;
	}
	
	/**
	 */
	public void setLabRoomDeviceReservations(Set<LabRoomDeviceReservation> labRoomDeviceReservations) {
		this.labRoomDeviceReservations = labRoomDeviceReservations;
	}

	/**
	 */
	@JsonIgnore
	public Set<LabRoomDeviceReservation> getLabRoomDeviceReservations() {
		if (labRoomDeviceReservations == null) {
			labRoomDeviceReservations = new java.util.LinkedHashSet<net.xidlims.domain.LabRoomDeviceReservation>();
		}
		return labRoomDeviceReservations;
	}
	
	public CDictionary getCDictionaryByLabManagerAudit() {
		return CDictionaryByLabManagerAudit;
	}

	public void setCDictionaryByLabManagerAudit(
			CDictionary cDictionaryByLabManagerAudit) {
		CDictionaryByLabManagerAudit = cDictionaryByLabManagerAudit;
	}
	/**
	 */
	public LabRoomDevice() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(LabRoomDevice that) {
		setId(that.getId());
		setIndicators(that.getIndicators());
		setPrice(that.getPrice());
		setFunction(that.getFunction());
		setFeatures(that.getFeatures());
		setApplications(that.getApplications());
		setDimensionalCode(that.getDimensionalCode());
		setCDictionaryByTeacherAudit(that.getCDictionaryByTeacherAudit());
		setCDictionaryByAllowLending(that.getCDictionaryByAllowLending());
		setCDictionaryByDeviceType(that.getCDictionaryByDeviceType());
		setCDictionaryByAllowAppointment(that.getCDictionaryByAllowAppointment());
		setSchoolDevice(that.getSchoolDevice());
		setUser(that.getUser());
		setCDictionaryByManagerAudit(that.getCDictionaryByManagerAudit());
		setCDictionaryBySecurityAccessType(that.getCDictionaryBySecurityAccessType());
		setLabRoom(that.getLabRoom());
		setCDictionaryByAppointmentType(that.getCDictionaryByAppointmentType());
		setCDictionaryByDeviceCharge(that.getCDictionaryByDeviceCharge());
		setCDictionaryByDeviceStatus(that.getCDictionaryByDeviceStatus());
		setCDictionaryByAllowSecurityAccess(that.getCDictionaryByAllowSecurityAccess());
		setCDictionaryByIsAudit(that.getCDictionaryByIsAudit());
		

		setCommonDocuments(new java.util.LinkedHashSet<net.xidlims.domain.CommonDocument>(that.getCommonDocuments()));
		setCommonVideos(new java.util.LinkedHashSet<net.xidlims.domain.CommonVideo>(that.getCommonVideos()));
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("id=[").append(id).append("] ");
		buffer.append("indicators=[").append(indicators).append("] ");
		buffer.append("price=[").append(price).append("] ");
		buffer.append("function=[").append(function).append("] ");
		buffer.append("features=[").append(features).append("] ");
		buffer.append("applications=[").append(applications).append("] ");
		buffer.append("dimensionalCode=[").append(dimensionalCode).append("] ");

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
		if (!(obj instanceof LabRoomDevice))
			return false;
		LabRoomDevice equalCheck = (LabRoomDevice) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
