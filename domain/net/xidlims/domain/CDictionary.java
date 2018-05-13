package net.xidlims.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.codehaus.jackson.annotate.JsonIgnore;


/**
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "findAllCDictionarys", query = "select myCDictionary from CDictionary myCDictionary"),
		@NamedQuery(name = "findCDictionaryByCCategory", query = "select myCDictionary from CDictionary myCDictionary where myCDictionary.CCategory = ?1"),
		@NamedQuery(name = "findCDictionaryByCCategoryContaining", query = "select myCDictionary from CDictionary myCDictionary where myCDictionary.CCategory like ?1"),
		@NamedQuery(name = "findCDictionaryByCName", query = "select myCDictionary from CDictionary myCDictionary where myCDictionary.CName = ?1"),
		@NamedQuery(name = "findCDictionaryByCNameContaining", query = "select myCDictionary from CDictionary myCDictionary where myCDictionary.CName like ?1"),
		@NamedQuery(name = "findCDictionaryByCNumber", query = "select myCDictionary from CDictionary myCDictionary where myCDictionary.CNumber = ?1"),
		@NamedQuery(name = "findCDictionaryByCNumberContaining", query = "select myCDictionary from CDictionary myCDictionary where myCDictionary.CNumber like ?1"),
		@NamedQuery(name = "findCDictionaryByEnabled", query = "select myCDictionary from CDictionary myCDictionary where myCDictionary.enabled = ?1"),
		@NamedQuery(name = "findCDictionaryById", query = "select myCDictionary from CDictionary myCDictionary where myCDictionary.id = ?1"),
		@NamedQuery(name = "findCDictionaryByPrimaryKey", query = "select myCDictionary from CDictionary myCDictionary where myCDictionary.id = ?1") })
@Table(catalog = "xidlims", name = "c_dictionary")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "CDictionary")
@XmlRootElement(namespace = "xidlims/net/xidlims/domain")
public class CDictionary implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 */

	@Column(name = "id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	Integer id;
	/**
	 * ���
	 * 
	 */

	@Column(name = "c_number", length = 40)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String CNumber;
	/**
	 * ��������
	 * 
	 */

	@Column(name = "c_category", length = 100)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String CCategory;
	/**
	 * ���
	 * 
	 */

	@Column(name = "c_name", length = 100)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String CName;
	/**
	 */

	@Column(name = "enabled")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Boolean enabled;

	/**
	 * ���ʼ���
	 * 
	 */

	@Column(name = "c_teaching_date_type")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer cTeachingDateType;
	
	/**
	 */
	@OneToMany(mappedBy = "CTrainingStatus", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.LabRoomDeviceTraining> labRoomDeviceTrainings;
	/**
	 */
	@OneToMany(mappedBy = "CDictionaryByCostReason", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.NDevicePurchase> NDevicePurchasesForCostType;
	/**
	 */
	@OneToMany(mappedBy = "CDictionaryByCostReason", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.NDevicePurchase> NDevicePurchasesForCostReason;
	

	/**
	 */
	@OneToMany(mappedBy = "CDictionary", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.LabRoomAgent> labRoomAgents;
	/**
	 */
	@OneToMany(mappedBy = "CDictionaryByTeacherAudit", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.LabRoomDevice> labRoomDevicesForManagerAudit;
	/**
	 */
	@OneToMany(mappedBy = "CDictionaryByCourseStatus", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.SchoolCourse> schoolCoursesForCourseStatus;
	
	/**
	 */
	@OneToMany(mappedBy = "CDictionaryByTeacherAudit", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.LabRoomDevice> labRoomDevicesForTrainType;

	/**
	 */
	@OneToMany(mappedBy = "CDictionary", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.LabRoomDeviceTrainingPeople> labRoomDeviceTrainingPeoples;

	/**
	 */
	@OneToMany(mappedBy = "CDictionaryByStudentType", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.SchoolCourse> schoolCoursesForStudentType;
	/**
	 */
	@OneToMany(mappedBy = "CDictionaryByTeacherAudit", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.LabRoomDevice> labRoomDevicesForAllowLending;
	/**
	 */
	@OneToMany(mappedBy = "CDictionaryByTeacherAudit", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.LabRoomDevice> labRoomDevicesForAppointmentType;
	/**
	 */
	@OneToMany(mappedBy = "CDictionaryByTeacherAudit", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.LabRoomDevice> labRoomDevicesForAllowSecurityAccess;
	/**
	 */
	@OneToMany(mappedBy = "CDictionaryByTeacherAudit", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.LabRoomDevice> labRoomDevicesForSecurityAccessType;
	/**
	 */
	@OneToMany(mappedBy = "CDictionaryByTeacherAudit", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.LabRoomDevice> labRoomDevicesForDeviceType;
	/**
	 */
	@OneToMany(mappedBy = "CDictionaryByTeacherAudit", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.LabRoomDevice> labRoomDevicesForDeviceStatus;
	/**
	 */
	@OneToMany(mappedBy = "CDictionaryByTeacherAudit", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.LabRoomDevice> labRoomDevicesForDeviceCharge;
	/**
	 */
	@OneToMany(mappedBy = "CDictionaryByTeacherAudit", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.LabRoomDevice> labRoomDevicesForTeacherAudit;
	/**
	 */
	@OneToMany(mappedBy = "CDictionaryByLpCategoryMain", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.OperationItem> operationItemsForLpStatusCheck;
	/**
	 */
	@OneToMany(mappedBy = "CDictionaryByTeacherAudit", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.LabRoomDevice> labRoomDevicesForAllowAppointment;
	/**
	 */
	@OneToMany(mappedBy = "CDictionaryByLpCategoryMain", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.OperationItem> operationItemsForLpCategoryPublic;
	/**
	 */
	@OneToMany(mappedBy = "CDictionaryByLpCategoryMain", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.OperationItem> operationItemsForLpStatusChange;
	/**
	 */
	@OneToMany(mappedBy = "CDictionaryByLwMainWork", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.LabWorker> labWorkersForLwCategoryStaff;
	/**
	 */
	@OneToMany(mappedBy = "CDictionaryByLpCategoryMain", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.OperationItem> operationItemsForLpCategoryRewardLevel;
	/**
	 */
	@OneToMany(mappedBy = "CDictionaryByLwMainWork", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.LabWorker> labWorkersForLwForeignLanguageLevel;
	/**
	 */
	@OneToMany(mappedBy = "CDictionaryByLwMainWork", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.LabWorker> labWorkersForLwDegree;
	/**
	 */
	@OneToMany(mappedBy = "CDictionaryByLpCategoryMain", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.OperationItem> operationItemsForLpCategoryApp;
	/**
	 */
	@OneToMany(mappedBy = "CDictionaryByLwMainWork", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.LabWorker> labWorkersForLwBookLevel;
	/**
	 */
	@OneToMany(mappedBy = "CDictionaryByLpCategoryMain", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.OperationItem> operationItemsForLpCategoryGuideBook;
	/**
	 */
	@OneToMany(mappedBy = "CDictionaryByLpCategoryMain", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.OperationItem> operationItemsForLpCategoryStudent;
	/**
	 */
	@OneToMany(mappedBy = "CDictionaryByLpCategoryMain", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.OperationItem> operationItemsForLpCategoryNature;
	/**
	 */
	@OneToMany(mappedBy = "CDictionaryByLwMainWork", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.LabWorker> labWorkersForLwMainWork;
	/**
	 */
	@OneToMany(mappedBy = "CDictionaryByLwMainWork", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.LabWorker> labWorkersForLwPaperLevel;
	/**
	 */
	@OneToMany(mappedBy = "CDictionaryByLwMainWork", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.LabWorker> labWorkersForLwSpecialtyDuty;
	/**
	 */
	@OneToMany(mappedBy = "CDictionaryByTeacherAudit", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.LabRoomDevice> labRoomDevicesForIsAudit;
	/**
	 */
	@OneToMany(mappedBy = "CDictionaryByLwMainWork", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.LabWorker> labWorkersForLwSubject;
	/**
	 */
	@OneToMany(mappedBy = "CDictionaryByLpCategoryMain", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.OperationItem> operationItemsForLpCategoryRequire;
	/**
	 */
	@OneToMany(mappedBy = "CDictionaryByLwMainWork", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.LabWorker> labWorkersForLwForeignLanguage;
	/**
	 */
	@OneToMany(mappedBy = "CDictionaryByLwMainWork", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.LabWorker> labWorkersForLwAcademicDegree;
	/**
	 */
	@OneToMany(mappedBy = "CDictionaryByLwMainWork", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.LabWorker> labWorkersForLwReward;
	/**
	 */
	@OneToMany(mappedBy = "CDictionaryByLwMainWork", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.LabWorker> labWorkersForLwEmployment;
	/**
	 */
	@OneToMany(mappedBy = "CDictionaryByLpCategoryMain", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.OperationItem> operationItemsForLpCategoryMain;
	
	@OneToMany(mappedBy = "CDictionary", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.OperationItemMaterialRecord> operationItemMaterialRecords;
	
	/**
	 */
	@OneToMany(mappedBy = "CDictionaryByLabRoom", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.LabRoom> labRoomsForLabRoom;
	
	
	@OneToMany(mappedBy = "CDictionary", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.LabReservationTimeTable> labReservationTimeTables;
	/**
	 */
	@OneToMany(mappedBy = "CDictionaryByActivityCategory", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.LabReservation> labReservationsForActivityCategory;
	/**
	 */
	@OneToMany(mappedBy = "CDictionary", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.LabReservationAudit> labReservationAudits;
	
	@OneToMany(mappedBy = "CDictionaryByActivityCategory", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.LabReservation> labReservationsForLabReservetYpe;
	
	
	//新增
	
	/**
	 */
	@OneToMany(mappedBy = "CDictionary", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.LabConstructionProjectAudit> labConstructionProjectAudits;
	/**
	 */
	@OneToMany(mappedBy = "CDictionary", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.LabConstructionPurchaseAudit> labConstructionPurchaseAudits;
	/**
	 */
	@OneToMany(mappedBy = "CDictionary", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.LabConstructionPurchase> labConstructionPurchases;
	/**
	 */
	@OneToMany(mappedBy = "CDictionary", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.LabConstructionFundingAudit> labConstructionFundingAudits;

	/**
	 */
	@OneToMany(mappedBy = "CDictionary", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.TCourseSiteArtical> TCourseSiteArticals;
	
	/**
	 */
	@OneToMany(mappedBy = "CDictionary", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.TCourseSiteChannel> TCourseSiteChannels;
	
	/**
	 */
	@OneToMany(mappedBy = "CDictionaryByLabAnnex", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.LabAnnex> labAnnexesForLabAnnex;
	/**
	 */
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(catalog = "xidlims", name = "operation_outline_ccourse_property", joinColumns = { @JoinColumn(name = "c_outline_property_id", referencedColumnName = "id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "operation_outline_id", referencedColumnName = "id", nullable = false, updatable = false) })
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.OperationOutline> operationOutlines;


	/**
	 */
	@OneToMany(mappedBy = "CDictionaryByStatusId", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.LabRoomDeviceRepair> labRoomDeviceRepairsForStatusId;
	
	/**
	 */
	@OneToMany(mappedBy = "CDictionaryByFailureChoice", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.LabRoomDeviceRepair> labRoomDeviceRepairsForFailureChoice;
	
	/**
	 */
	@OneToMany(mappedBy = "CDictionaryByPartitionId", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.LabRoomDeviceRepair> labRoomDeviceRepairsForPartitionId;
	
	/**
	 */
	@OneToMany(mappedBy = "CDictionary", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.LabRoomDeviceLendingResult> labRoomDeviceLendingResults;
	
	/**
	 */
	@OneToMany(mappedBy = "CDictionary", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.LabRoomDeviceLending> labRoomDeviceLendings;
	
	
	/**
	 */
	@OneToMany(mappedBy = "CDictionary", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.SchoolWeek> schoolWeeks;
	
	/**
	 */
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(catalog = "xidlims", name = "operation_outline_student_status", joinColumns = { @JoinColumn(name = "status_id", referencedColumnName = "id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "outline_id", referencedColumnName = "id", nullable = false, updatable = false) })
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.OperationOutline> operationOutlinees;
	
	/**
	 */
	public Integer getcTeachingDateType() {
		return cTeachingDateType;
	}
	/**
	 */
	@OneToMany(mappedBy = "CDictionary", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.SchoolTermActive> schoolTermActives;
	/**
	 */
	public void setcTeachingDateType(Integer cTeachingDateType) {
		this.cTeachingDateType = cTeachingDateType;
	}

	
	@JsonIgnore
	public java.util.Set<net.xidlims.domain.LabReservationTimeTable> getLabReservationTimeTables() {
		return labReservationTimeTables;
	}

	public void setLabReservationTimeTables(
			java.util.Set<net.xidlims.domain.LabReservationTimeTable> labReservationTimeTables) {
		this.labReservationTimeTables = labReservationTimeTables;
	}
	
	/**
	 */
	public void setLabRoomsForLabRoom(Set<LabRoom> labRoomsForLabRoom) {
		this.labRoomsForLabRoom = labRoomsForLabRoom;
	}

	/**
	 */
	@JsonIgnore
	public Set<LabRoom> getLabRoomsForLabRoom() {
		if (labRoomsForLabRoom == null) {
			labRoomsForLabRoom = new java.util.LinkedHashSet<net.xidlims.domain.LabRoom>();
		}
		return labRoomsForLabRoom;
	}

	@JsonIgnore
	public java.util.Set<net.xidlims.domain.LabReservation> getLabReservationsForActivityCategory() {
		return labReservationsForActivityCategory;
	}

	public void setLabReservationsForActivityCategory(
			java.util.Set<net.xidlims.domain.LabReservation> labReservationsForActivityCategory) {
		this.labReservationsForActivityCategory = labReservationsForActivityCategory;
	}

	@JsonIgnore
	public java.util.Set<net.xidlims.domain.LabReservationAudit> getLabReservationAudits() {
		return labReservationAudits;
	}

	public void setLabReservationAudits(
			java.util.Set<net.xidlims.domain.LabReservationAudit> labReservationAudits) {
		this.labReservationAudits = labReservationAudits;
	}

	@JsonIgnore
	public java.util.Set<net.xidlims.domain.LabReservation> getLabReservationsForLabReservetYpe() {
		return labReservationsForLabReservetYpe;
	}

	public void setLabReservationsForLabReservetYpe(
			java.util.Set<net.xidlims.domain.LabReservation> labReservationsForLabReservetYpe) {
		this.labReservationsForLabReservetYpe = labReservationsForLabReservetYpe;
	}
	
	
	@JsonIgnore
	public java.util.Set<net.xidlims.domain.SchoolWeek> getSchoolWeeks() {
		return schoolWeeks;
	}

	public void setSchoolWeeks(
			java.util.Set<net.xidlims.domain.SchoolWeek> schoolWeeks) {
		this.schoolWeeks = schoolWeeks;
	}

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
	 * ���
	 * 
	 */
	public void setCNumber(String CNumber) {
		this.CNumber = CNumber;
	}

	/**
	 * ���
	 * 
	 */
	public String getCNumber() {
		return this.CNumber;
	}

	/**
	 * ��������
	 * 
	 */
	public void setCCategory(String CCategory) {
		this.CCategory = CCategory;
	}

	/**
	 * ��������
	 * 
	 */
	public String getCCategory() {
		return this.CCategory;
	}

	/**
	 * ���
	 * 
	 */
	public void setCName(String CName) {
		this.CName = CName;
	}

	/**
	 * ���
	 * 
	 */
	public String getCName() {
		return this.CName;
	}

	/**
	 */
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	/**
	 */
	public Boolean getEnabled() {
		return this.enabled;
	}

	/**
	 */
	public void setLabRoomAgents(Set<LabRoomAgent> labRoomAgents) {
		this.labRoomAgents = labRoomAgents;
	}

	/**
	 */
	@JsonIgnore
	public Set<LabRoomAgent> getLabRoomAgents() {
		if (labRoomAgents == null) {
			labRoomAgents = new java.util.LinkedHashSet<net.xidlims.domain.LabRoomAgent>();
		}
		return labRoomAgents;
	}

	/**
	 */
	public void setLabRoomDevicesForManagerAudit(Set<LabRoomDevice> labRoomDevicesForManagerAudit) {
		this.labRoomDevicesForManagerAudit = labRoomDevicesForManagerAudit;
	}

	/**
	 */
	@JsonIgnore
	public Set<LabRoomDevice> getLabRoomDevicesForManagerAudit() {
		if (labRoomDevicesForManagerAudit == null) {
			labRoomDevicesForManagerAudit = new java.util.LinkedHashSet<net.xidlims.domain.LabRoomDevice>();
		}
		return labRoomDevicesForManagerAudit;
	}

	/**
	 */
	public void setSchoolCoursesForCourseStatus(Set<SchoolCourse> schoolCoursesForCourseStatus) {
		this.schoolCoursesForCourseStatus = schoolCoursesForCourseStatus;
	}

	/**
	 */
	@JsonIgnore
	public Set<SchoolCourse> getSchoolCoursesForCourseStatus() {
		if (schoolCoursesForCourseStatus == null) {
			schoolCoursesForCourseStatus = new java.util.LinkedHashSet<net.xidlims.domain.SchoolCourse>();
		}
		return schoolCoursesForCourseStatus;
	}

	/**
	 */
	public void setSchoolCoursesForStudentType(Set<SchoolCourse> schoolCoursesForStudentType) {
		this.schoolCoursesForStudentType = schoolCoursesForStudentType;
	}

	/**
	 */
	@JsonIgnore
	public Set<SchoolCourse> getSchoolCoursesForStudentType() {
		if (schoolCoursesForStudentType == null) {
			schoolCoursesForStudentType = new java.util.LinkedHashSet<net.xidlims.domain.SchoolCourse>();
		}
		return schoolCoursesForStudentType;
	}

	/**
	 */
	public void setLabRoomDevicesForAllowLending(Set<LabRoomDevice> labRoomDevicesForAllowLending) {
		this.labRoomDevicesForAllowLending = labRoomDevicesForAllowLending;
	}

	/**
	 */
	@JsonIgnore
	public Set<LabRoomDevice> getLabRoomDevicesForAllowLending() {
		if (labRoomDevicesForAllowLending == null) {
			labRoomDevicesForAllowLending = new java.util.LinkedHashSet<net.xidlims.domain.LabRoomDevice>();
		}
		return labRoomDevicesForAllowLending;
	}

	/**
	 */
	public void setLabRoomDevicesForAppointmentType(Set<LabRoomDevice> labRoomDevicesForAppointmentType) {
		this.labRoomDevicesForAppointmentType = labRoomDevicesForAppointmentType;
	}

	/**
	 */
	@JsonIgnore
	public Set<LabRoomDevice> getLabRoomDevicesForAppointmentType() {
		if (labRoomDevicesForAppointmentType == null) {
			labRoomDevicesForAppointmentType = new java.util.LinkedHashSet<net.xidlims.domain.LabRoomDevice>();
		}
		return labRoomDevicesForAppointmentType;
	}

	/**
	 */
	public void setLabRoomDevicesForAllowSecurityAccess(Set<LabRoomDevice> labRoomDevicesForAllowSecurityAccess) {
		this.labRoomDevicesForAllowSecurityAccess = labRoomDevicesForAllowSecurityAccess;
	}

	/**
	 */
	@JsonIgnore
	public Set<LabRoomDevice> getLabRoomDevicesForAllowSecurityAccess() {
		if (labRoomDevicesForAllowSecurityAccess == null) {
			labRoomDevicesForAllowSecurityAccess = new java.util.LinkedHashSet<net.xidlims.domain.LabRoomDevice>();
		}
		return labRoomDevicesForAllowSecurityAccess;
	}

	/**
	 */
	public void setLabRoomDevicesForSecurityAccessType(Set<LabRoomDevice> labRoomDevicesForSecurityAccessType) {
		this.labRoomDevicesForSecurityAccessType = labRoomDevicesForSecurityAccessType;
	}

	/**
	 */
	@JsonIgnore
	public Set<LabRoomDevice> getLabRoomDevicesForSecurityAccessType() {
		if (labRoomDevicesForSecurityAccessType == null) {
			labRoomDevicesForSecurityAccessType = new java.util.LinkedHashSet<net.xidlims.domain.LabRoomDevice>();
		}
		return labRoomDevicesForSecurityAccessType;
	}

	/**
	 */
	public void setLabRoomDevicesForDeviceType(Set<LabRoomDevice> labRoomDevicesForDeviceType) {
		this.labRoomDevicesForDeviceType = labRoomDevicesForDeviceType;
	}

	/**
	 */
	@JsonIgnore
	public Set<LabRoomDevice> getLabRoomDevicesForDeviceType() {
		if (labRoomDevicesForDeviceType == null) {
			labRoomDevicesForDeviceType = new java.util.LinkedHashSet<net.xidlims.domain.LabRoomDevice>();
		}
		return labRoomDevicesForDeviceType;
	}

	/**
	 */
	public void setLabRoomDevicesForDeviceStatus(Set<LabRoomDevice> labRoomDevicesForDeviceStatus) {
		this.labRoomDevicesForDeviceStatus = labRoomDevicesForDeviceStatus;
	}

	/**
	 */
	@JsonIgnore
	public Set<LabRoomDevice> getLabRoomDevicesForDeviceStatus() {
		if (labRoomDevicesForDeviceStatus == null) {
			labRoomDevicesForDeviceStatus = new java.util.LinkedHashSet<net.xidlims.domain.LabRoomDevice>();
		}
		return labRoomDevicesForDeviceStatus;
	}

	/**
	 */
	public void setLabRoomDevicesForDeviceCharge(Set<LabRoomDevice> labRoomDevicesForDeviceCharge) {
		this.labRoomDevicesForDeviceCharge = labRoomDevicesForDeviceCharge;
	}

	/**
	 */
	@JsonIgnore
	public Set<LabRoomDevice> getLabRoomDevicesForDeviceCharge() {
		if (labRoomDevicesForDeviceCharge == null) {
			labRoomDevicesForDeviceCharge = new java.util.LinkedHashSet<net.xidlims.domain.LabRoomDevice>();
		}
		return labRoomDevicesForDeviceCharge;
	}

	/**
	 */
	public void setLabRoomDevicesForTeacherAudit(Set<LabRoomDevice> labRoomDevicesForTeacherAudit) {
		this.labRoomDevicesForTeacherAudit = labRoomDevicesForTeacherAudit;
	}

	/**
	 */
	@JsonIgnore
	public Set<LabRoomDevice> getLabRoomDevicesForTeacherAudit() {
		if (labRoomDevicesForTeacherAudit == null) {
			labRoomDevicesForTeacherAudit = new java.util.LinkedHashSet<net.xidlims.domain.LabRoomDevice>();
		}
		return labRoomDevicesForTeacherAudit;
	}

	/**
	 */
	public void setOperationItemsForLpStatusCheck(Set<OperationItem> operationItemsForLpStatusCheck) {
		this.operationItemsForLpStatusCheck = operationItemsForLpStatusCheck;
	}

	/**
	 */
	@JsonIgnore
	public Set<OperationItem> getOperationItemsForLpStatusCheck() {
		if (operationItemsForLpStatusCheck == null) {
			operationItemsForLpStatusCheck = new java.util.LinkedHashSet<net.xidlims.domain.OperationItem>();
		}
		return operationItemsForLpStatusCheck;
	}

	/**
	 */
	public void setLabRoomDevicesForAllowAppointment(Set<LabRoomDevice> labRoomDevicesForAllowAppointment) {
		this.labRoomDevicesForAllowAppointment = labRoomDevicesForAllowAppointment;
	}

	/**
	 */
	@JsonIgnore
	public Set<LabRoomDevice> getLabRoomDevicesForAllowAppointment() {
		if (labRoomDevicesForAllowAppointment == null) {
			labRoomDevicesForAllowAppointment = new java.util.LinkedHashSet<net.xidlims.domain.LabRoomDevice>();
		}
		return labRoomDevicesForAllowAppointment;
	}

	/**
	 */
	public void setOperationItemsForLpCategoryPublic(Set<OperationItem> operationItemsForLpCategoryPublic) {
		this.operationItemsForLpCategoryPublic = operationItemsForLpCategoryPublic;
	}

	/**
	 */
	@JsonIgnore
	public Set<OperationItem> getOperationItemsForLpCategoryPublic() {
		if (operationItemsForLpCategoryPublic == null) {
			operationItemsForLpCategoryPublic = new java.util.LinkedHashSet<net.xidlims.domain.OperationItem>();
		}
		return operationItemsForLpCategoryPublic;
	}

	/**
	 */
	public void setOperationItemsForLpStatusChange(Set<OperationItem> operationItemsForLpStatusChange) {
		this.operationItemsForLpStatusChange = operationItemsForLpStatusChange;
	}

	/**
	 */
	@JsonIgnore
	public Set<OperationItem> getOperationItemsForLpStatusChange() {
		if (operationItemsForLpStatusChange == null) {
			operationItemsForLpStatusChange = new java.util.LinkedHashSet<net.xidlims.domain.OperationItem>();
		}
		return operationItemsForLpStatusChange;
	}

	/**
	 */
	public void setLabWorkersForLwCategoryStaff(Set<LabWorker> labWorkersForLwCategoryStaff) {
		this.labWorkersForLwCategoryStaff = labWorkersForLwCategoryStaff;
	}

	/**
	 */
	@JsonIgnore
	public Set<LabWorker> getLabWorkersForLwCategoryStaff() {
		if (labWorkersForLwCategoryStaff == null) {
			labWorkersForLwCategoryStaff = new java.util.LinkedHashSet<net.xidlims.domain.LabWorker>();
		}
		return labWorkersForLwCategoryStaff;
	}

	/**
	 */
	public void setOperationItemsForLpCategoryRewardLevel(Set<OperationItem> operationItemsForLpCategoryRewardLevel) {
		this.operationItemsForLpCategoryRewardLevel = operationItemsForLpCategoryRewardLevel;
	}

	/**
	 */
	@JsonIgnore
	public Set<OperationItem> getOperationItemsForLpCategoryRewardLevel() {
		if (operationItemsForLpCategoryRewardLevel == null) {
			operationItemsForLpCategoryRewardLevel = new java.util.LinkedHashSet<net.xidlims.domain.OperationItem>();
		}
		return operationItemsForLpCategoryRewardLevel;
	}

	/**
	 */
	public void setLabWorkersForLwForeignLanguageLevel(Set<LabWorker> labWorkersForLwForeignLanguageLevel) {
		this.labWorkersForLwForeignLanguageLevel = labWorkersForLwForeignLanguageLevel;
	}

	/**
	 */
	@JsonIgnore
	public Set<LabWorker> getLabWorkersForLwForeignLanguageLevel() {
		if (labWorkersForLwForeignLanguageLevel == null) {
			labWorkersForLwForeignLanguageLevel = new java.util.LinkedHashSet<net.xidlims.domain.LabWorker>();
		}
		return labWorkersForLwForeignLanguageLevel;
	}

	/**
	 */
	public void setLabWorkersForLwDegree(Set<LabWorker> labWorkersForLwDegree) {
		this.labWorkersForLwDegree = labWorkersForLwDegree;
	}

	/**
	 */
	@JsonIgnore
	public Set<LabWorker> getLabWorkersForLwDegree() {
		if (labWorkersForLwDegree == null) {
			labWorkersForLwDegree = new java.util.LinkedHashSet<net.xidlims.domain.LabWorker>();
		}
		return labWorkersForLwDegree;
	}

	/**
	 */
	public void setOperationItemsForLpCategoryApp(Set<OperationItem> operationItemsForLpCategoryApp) {
		this.operationItemsForLpCategoryApp = operationItemsForLpCategoryApp;
	}

	/**
	 */
	@JsonIgnore
	public Set<OperationItem> getOperationItemsForLpCategoryApp() {
		if (operationItemsForLpCategoryApp == null) {
			operationItemsForLpCategoryApp = new java.util.LinkedHashSet<net.xidlims.domain.OperationItem>();
		}
		return operationItemsForLpCategoryApp;
	}

	/**
	 */
	public void setLabWorkersForLwBookLevel(Set<LabWorker> labWorkersForLwBookLevel) {
		this.labWorkersForLwBookLevel = labWorkersForLwBookLevel;
	}

	/**
	 */
	@JsonIgnore
	public Set<LabWorker> getLabWorkersForLwBookLevel() {
		if (labWorkersForLwBookLevel == null) {
			labWorkersForLwBookLevel = new java.util.LinkedHashSet<net.xidlims.domain.LabWorker>();
		}
		return labWorkersForLwBookLevel;
	}

	/**
	 */
	public void setOperationItemsForLpCategoryGuideBook(Set<OperationItem> operationItemsForLpCategoryGuideBook) {
		this.operationItemsForLpCategoryGuideBook = operationItemsForLpCategoryGuideBook;
	}

	/**
	 */
	@JsonIgnore
	public Set<OperationItem> getOperationItemsForLpCategoryGuideBook() {
		if (operationItemsForLpCategoryGuideBook == null) {
			operationItemsForLpCategoryGuideBook = new java.util.LinkedHashSet<net.xidlims.domain.OperationItem>();
		}
		return operationItemsForLpCategoryGuideBook;
	}

	/**
	 */
	public void setOperationItemsForLpCategoryStudent(Set<OperationItem> operationItemsForLpCategoryStudent) {
		this.operationItemsForLpCategoryStudent = operationItemsForLpCategoryStudent;
	}

	/**
	 */
	@JsonIgnore
	public Set<OperationItem> getOperationItemsForLpCategoryStudent() {
		if (operationItemsForLpCategoryStudent == null) {
			operationItemsForLpCategoryStudent = new java.util.LinkedHashSet<net.xidlims.domain.OperationItem>();
		}
		return operationItemsForLpCategoryStudent;
	}

	/**
	 */
	public void setOperationItemsForLpCategoryNature(Set<OperationItem> operationItemsForLpCategoryNature) {
		this.operationItemsForLpCategoryNature = operationItemsForLpCategoryNature;
	}

	/**
	 */
	@JsonIgnore
	public Set<OperationItem> getOperationItemsForLpCategoryNature() {
		if (operationItemsForLpCategoryNature == null) {
			operationItemsForLpCategoryNature = new java.util.LinkedHashSet<net.xidlims.domain.OperationItem>();
		}
		return operationItemsForLpCategoryNature;
	}

	/**
	 */
	public void setLabWorkersForLwMainWork(Set<LabWorker> labWorkersForLwMainWork) {
		this.labWorkersForLwMainWork = labWorkersForLwMainWork;
	}

	/**
	 */
	@JsonIgnore
	public Set<LabWorker> getLabWorkersForLwMainWork() {
		if (labWorkersForLwMainWork == null) {
			labWorkersForLwMainWork = new java.util.LinkedHashSet<net.xidlims.domain.LabWorker>();
		}
		return labWorkersForLwMainWork;
	}

	/**
	 */
	public void setLabWorkersForLwPaperLevel(Set<LabWorker> labWorkersForLwPaperLevel) {
		this.labWorkersForLwPaperLevel = labWorkersForLwPaperLevel;
	}

	/**
	 */
	@JsonIgnore
	public Set<LabWorker> getLabWorkersForLwPaperLevel() {
		if (labWorkersForLwPaperLevel == null) {
			labWorkersForLwPaperLevel = new java.util.LinkedHashSet<net.xidlims.domain.LabWorker>();
		}
		return labWorkersForLwPaperLevel;
	}

	/**
	 */
	public void setLabWorkersForLwSpecialtyDuty(Set<LabWorker> labWorkersForLwSpecialtyDuty) {
		this.labWorkersForLwSpecialtyDuty = labWorkersForLwSpecialtyDuty;
	}

	/**
	 */
	@JsonIgnore
	public Set<LabWorker> getLabWorkersForLwSpecialtyDuty() {
		if (labWorkersForLwSpecialtyDuty == null) {
			labWorkersForLwSpecialtyDuty = new java.util.LinkedHashSet<net.xidlims.domain.LabWorker>();
		}
		return labWorkersForLwSpecialtyDuty;
	}

	/**
	 */
	public void setLabRoomDevicesForIsAudit(Set<LabRoomDevice> labRoomDevicesForIsAudit) {
		this.labRoomDevicesForIsAudit = labRoomDevicesForIsAudit;
	}

	/**
	 */
	@JsonIgnore
	public Set<LabRoomDevice> getLabRoomDevicesForIsAudit() {
		if (labRoomDevicesForIsAudit == null) {
			labRoomDevicesForIsAudit = new java.util.LinkedHashSet<net.xidlims.domain.LabRoomDevice>();
		}
		return labRoomDevicesForIsAudit;
	}

	/**
	 */
	public void setLabWorkersForLwSubject(Set<LabWorker> labWorkersForLwSubject) {
		this.labWorkersForLwSubject = labWorkersForLwSubject;
	}

	/**
	 */
	@JsonIgnore
	public Set<LabWorker> getLabWorkersForLwSubject() {
		if (labWorkersForLwSubject == null) {
			labWorkersForLwSubject = new java.util.LinkedHashSet<net.xidlims.domain.LabWorker>();
		}
		return labWorkersForLwSubject;
	}

	/**
	 */
	public void setOperationItemsForLpCategoryRequire(Set<OperationItem> operationItemsForLpCategoryRequire) {
		this.operationItemsForLpCategoryRequire = operationItemsForLpCategoryRequire;
	}

	/**
	 */
	@JsonIgnore
	public Set<OperationItem> getOperationItemsForLpCategoryRequire() {
		if (operationItemsForLpCategoryRequire == null) {
			operationItemsForLpCategoryRequire = new java.util.LinkedHashSet<net.xidlims.domain.OperationItem>();
		}
		return operationItemsForLpCategoryRequire;
	}

	/**
	 */
	public void setLabWorkersForLwForeignLanguage(Set<LabWorker> labWorkersForLwForeignLanguage) {
		this.labWorkersForLwForeignLanguage = labWorkersForLwForeignLanguage;
	}

	/**
	 */
	@JsonIgnore
	public Set<LabWorker> getLabWorkersForLwForeignLanguage() {
		if (labWorkersForLwForeignLanguage == null) {
			labWorkersForLwForeignLanguage = new java.util.LinkedHashSet<net.xidlims.domain.LabWorker>();
		}
		return labWorkersForLwForeignLanguage;
	}

	/**
	 */
	public void setLabWorkersForLwAcademicDegree(Set<LabWorker> labWorkersForLwAcademicDegree) {
		this.labWorkersForLwAcademicDegree = labWorkersForLwAcademicDegree;
	}

	/**
	 */
	@JsonIgnore
	public Set<LabWorker> getLabWorkersForLwAcademicDegree() {
		if (labWorkersForLwAcademicDegree == null) {
			labWorkersForLwAcademicDegree = new java.util.LinkedHashSet<net.xidlims.domain.LabWorker>();
		}
		return labWorkersForLwAcademicDegree;
	}
	
	@JsonIgnore
	public java.util.Set<net.xidlims.domain.OperationItemMaterialRecord> getOperationItemMaterialRecords() {
		return operationItemMaterialRecords;
	}

	public void setOperationItemMaterialRecords(
			java.util.Set<net.xidlims.domain.OperationItemMaterialRecord> operationItemMaterialRecords) {
		this.operationItemMaterialRecords = operationItemMaterialRecords;
	}

	/**
	 */
	public void setLabWorkersForLwReward(Set<LabWorker> labWorkersForLwReward) {
		this.labWorkersForLwReward = labWorkersForLwReward;
	}

	/**
	 */
	@JsonIgnore
	public Set<LabWorker> getLabWorkersForLwReward() {
		if (labWorkersForLwReward == null) {
			labWorkersForLwReward = new java.util.LinkedHashSet<net.xidlims.domain.LabWorker>();
		}
		return labWorkersForLwReward;
	}

	/**
	 */
	public void setLabWorkersForLwEmployment(Set<LabWorker> labWorkersForLwEmployment) {
		this.labWorkersForLwEmployment = labWorkersForLwEmployment;
	}

	/**
	 */
	@JsonIgnore
	public Set<LabWorker> getLabWorkersForLwEmployment() {
		if (labWorkersForLwEmployment == null) {
			labWorkersForLwEmployment = new java.util.LinkedHashSet<net.xidlims.domain.LabWorker>();
		}
		return labWorkersForLwEmployment;
	}

	/**
	 */
	public void setOperationItemsForLpCategoryMain(Set<OperationItem> operationItemsForLpCategoryMain) {
		this.operationItemsForLpCategoryMain = operationItemsForLpCategoryMain;
	}

	/**
	 */
	@JsonIgnore
	public Set<OperationItem> getOperationItemsForLpCategoryMain() {
		if (operationItemsForLpCategoryMain == null) {
			operationItemsForLpCategoryMain = new java.util.LinkedHashSet<net.xidlims.domain.OperationItem>();
		}
		return operationItemsForLpCategoryMain;
	}
	//新增
	
	/**
	 */
	public void setLabConstructionProjectAudits(Set<LabConstructionProjectAudit> labConstructionProjectAudits) {
		this.labConstructionProjectAudits = labConstructionProjectAudits;
	}

	/**
	 */
	@JsonIgnore
	public Set<LabConstructionProjectAudit> getLabConstructionProjectAudits() {
		if (labConstructionProjectAudits == null) {
			labConstructionProjectAudits = new java.util.LinkedHashSet<net.xidlims.domain.LabConstructionProjectAudit>();
		}
		return labConstructionProjectAudits;
	}

	/**
	 */
	public void setLabConstructionPurchaseAudits(Set<LabConstructionPurchaseAudit> labConstructionPurchaseAudits) {
		this.labConstructionPurchaseAudits = labConstructionPurchaseAudits;
	}

	/**
	 */
	@JsonIgnore
	public Set<LabConstructionPurchaseAudit> getLabConstructionPurchaseAudits() {
		if (labConstructionPurchaseAudits == null) {
			labConstructionPurchaseAudits = new java.util.LinkedHashSet<net.xidlims.domain.LabConstructionPurchaseAudit>();
		}
		return labConstructionPurchaseAudits;
	}

	/**
	 */
	public void setLabConstructionPurchases(Set<LabConstructionPurchase> labConstructionPurchases) {
		this.labConstructionPurchases = labConstructionPurchases;
	}

	/**
	 */
	@JsonIgnore
	public Set<LabConstructionPurchase> getLabConstructionPurchases() {
		if (labConstructionPurchases == null) {
			labConstructionPurchases = new java.util.LinkedHashSet<net.xidlims.domain.LabConstructionPurchase>();
		}
		return labConstructionPurchases;
	}

	/**
	 */
	public void setLabConstructionFundingAudits(Set<LabConstructionFundingAudit> labConstructionFundingAudits) {
		this.labConstructionFundingAudits = labConstructionFundingAudits;
	}

	/**
	 */
	@JsonIgnore
	public Set<LabConstructionFundingAudit> getLabConstructionFundingAudits() {
		if (labConstructionFundingAudits == null) {
			labConstructionFundingAudits = new java.util.LinkedHashSet<net.xidlims.domain.LabConstructionFundingAudit>();
		}
		return labConstructionFundingAudits;
	}
	/**
	 */
	public void setTCourseSiteArticals(Set<TCourseSiteArtical> TCourseSiteArticals) {
		this.TCourseSiteArticals = TCourseSiteArticals;
	}

	/**
	 */
	@JsonIgnore
	public Set<TCourseSiteArtical> getTCourseSiteArticals() {
		if (TCourseSiteArticals == null) {
			TCourseSiteArticals = new java.util.LinkedHashSet<net.xidlims.domain.TCourseSiteArtical>();
		}
		return TCourseSiteArticals;
	}

	/**
	 */
	public void setTCourseSiteChannels(Set<TCourseSiteChannel> TCourseSiteChannels) {
		this.TCourseSiteChannels = TCourseSiteChannels;
	}

	/**
	 */
	@JsonIgnore
	public Set<TCourseSiteChannel> getTCourseSiteChannels() {
		if (TCourseSiteChannels == null) {
			TCourseSiteChannels = new java.util.LinkedHashSet<net.xidlims.domain.TCourseSiteChannel>();
		}
		return TCourseSiteChannels;
	}
	
	/**
	 */
	public void setOperationOutlines(Set<OperationOutline> operationOutlines) {
		this.operationOutlines = operationOutlines;
	}

	/**
	 */
	@JsonIgnore
	public Set<OperationOutline> getOperationOutlines() {
		if (operationOutlines == null) {
			operationOutlines = new java.util.LinkedHashSet<net.xidlims.domain.OperationOutline>();
		}
		return operationOutlines;
	}


	public Set<LabRoomDeviceRepair> getLabRoomDeviceRepairsForStatusId() {
		if (labRoomDeviceRepairsForStatusId == null) {
			labRoomDeviceRepairsForStatusId = new java.util.LinkedHashSet<net.xidlims.domain.LabRoomDeviceRepair>();
		}
		return labRoomDeviceRepairsForStatusId;
	}

	public void setLabRoomDeviceRepairsForStatusId(Set<LabRoomDeviceRepair> labRoomDeviceRepairsForStatusId) {
		this.labRoomDeviceRepairsForStatusId = labRoomDeviceRepairsForStatusId;
	}

	public Set<LabRoomDeviceRepair> getLabRoomDeviceRepairsForFailureChoice() {
		if (labRoomDeviceRepairsForFailureChoice == null) {
			labRoomDeviceRepairsForFailureChoice = new java.util.LinkedHashSet<net.xidlims.domain.LabRoomDeviceRepair>();
		}
		return labRoomDeviceRepairsForFailureChoice;
	}

	public void setLabRoomDeviceRepairsForFailureChoice(Set<LabRoomDeviceRepair> labRoomDeviceRepairsForFailureChoice) {
		this.labRoomDeviceRepairsForFailureChoice = labRoomDeviceRepairsForFailureChoice;
	}

	public Set<LabRoomDeviceRepair> getLabRoomDeviceRepairsForPartitionId() {
		if (labRoomDeviceRepairsForPartitionId == null) {
			labRoomDeviceRepairsForPartitionId = new java.util.LinkedHashSet<net.xidlims.domain.LabRoomDeviceRepair>();
		}
		return labRoomDeviceRepairsForPartitionId;
	}

	public void setLabRoomDeviceRepairsForPartitionId(Set<LabRoomDeviceRepair> labRoomDeviceRepairsForPartitionId) {
		this.labRoomDeviceRepairsForPartitionId = labRoomDeviceRepairsForPartitionId;
	}

	public Set<LabAnnex> getLabAnnexesForLabAnnex() {
		if (labAnnexesForLabAnnex == null) {
			labAnnexesForLabAnnex = new java.util.LinkedHashSet<net.xidlims.domain.LabAnnex>();
		}
		return labAnnexesForLabAnnex;
	}

	public void setLabAnnexesForLabAnnex(Set<LabAnnex> labAnnexesForLabAnnex) {
		this.labAnnexesForLabAnnex = labAnnexesForLabAnnex;
	}
	/**
	 */
	public void setNDevicePurchasesForCostType(Set<NDevicePurchase> NDevicePurchasesForCostType) {
		this.NDevicePurchasesForCostType = NDevicePurchasesForCostType;
	}

	/**
	 */
	@JsonIgnore
	public Set<NDevicePurchase> getNDevicePurchasesForCostType() {
		if (NDevicePurchasesForCostType == null) {
			NDevicePurchasesForCostType = new java.util.LinkedHashSet<net.xidlims.domain.NDevicePurchase>();
		}
		return NDevicePurchasesForCostType;
	}

	/**
	 */
	public void setNDevicePurchasesForCostReason(Set<NDevicePurchase> NDevicePurchasesForCostReason) {
		this.NDevicePurchasesForCostReason = NDevicePurchasesForCostReason;
	}

	/**
	 */
	@JsonIgnore
	public Set<NDevicePurchase> getNDevicePurchasesForCostReason() {
		if (NDevicePurchasesForCostReason == null) {
			NDevicePurchasesForCostReason = new java.util.LinkedHashSet<net.xidlims.domain.NDevicePurchase>();
		}
		return NDevicePurchasesForCostReason;
	}
	
	/**
	 * 
	 */
	public Set<OperationOutline> getOperationOutlinees(){
		if(operationOutlinees == null){
			operationOutlinees = new java.util.LinkedHashSet<net.xidlims.domain.OperationOutline>();
		}
		return operationOutlinees;
	}
	
	/**
	 * 
	 */
	public void setOperationOutlinees(Set<OperationOutline> operationOutlinees){
		this.operationOutlinees = operationOutlinees;
	}

	/**
	 */
	public CDictionary() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	/**
	 */
	public void setSchoolTermActives(Set<SchoolTermActive> schoolTermActives) {
		this.schoolTermActives = schoolTermActives;
	}

	/**
	 */
	@JsonIgnore
	public Set<SchoolTermActive> getSchoolTermActives() {
		if (schoolTermActives == null) {
			schoolTermActives = new java.util.LinkedHashSet<net.xidlims.domain.SchoolTermActive>();
		}
		return schoolTermActives;
	}
	public void copy(CDictionary that) {
		setId(that.getId());
		setCNumber(that.getCNumber());
		setCCategory(that.getCCategory());
		setCName(that.getCName());
		setEnabled(that.getEnabled());
		setLabRoomAgents(new java.util.LinkedHashSet<net.xidlims.domain.LabRoomAgent>(that.getLabRoomAgents()));
		setLabRoomDevicesForManagerAudit(new java.util.LinkedHashSet<net.xidlims.domain.LabRoomDevice>(that.getLabRoomDevicesForManagerAudit()));
		setSchoolCoursesForCourseStatus(new java.util.LinkedHashSet<net.xidlims.domain.SchoolCourse>(that.getSchoolCoursesForCourseStatus()));
		setSchoolCoursesForStudentType(new java.util.LinkedHashSet<net.xidlims.domain.SchoolCourse>(that.getSchoolCoursesForStudentType()));
		setLabRoomDevicesForAllowLending(new java.util.LinkedHashSet<net.xidlims.domain.LabRoomDevice>(that.getLabRoomDevicesForAllowLending()));
		setLabRoomDevicesForAppointmentType(new java.util.LinkedHashSet<net.xidlims.domain.LabRoomDevice>(that.getLabRoomDevicesForAppointmentType()));
		setLabRoomDevicesForAllowSecurityAccess(new java.util.LinkedHashSet<net.xidlims.domain.LabRoomDevice>(that.getLabRoomDevicesForAllowSecurityAccess()));
		setLabRoomDevicesForSecurityAccessType(new java.util.LinkedHashSet<net.xidlims.domain.LabRoomDevice>(that.getLabRoomDevicesForSecurityAccessType()));
		setLabRoomDevicesForDeviceType(new java.util.LinkedHashSet<net.xidlims.domain.LabRoomDevice>(that.getLabRoomDevicesForDeviceType()));
		setLabRoomDevicesForDeviceStatus(new java.util.LinkedHashSet<net.xidlims.domain.LabRoomDevice>(that.getLabRoomDevicesForDeviceStatus()));
		setLabRoomDevicesForDeviceCharge(new java.util.LinkedHashSet<net.xidlims.domain.LabRoomDevice>(that.getLabRoomDevicesForDeviceCharge()));
		setLabRoomDevicesForTeacherAudit(new java.util.LinkedHashSet<net.xidlims.domain.LabRoomDevice>(that.getLabRoomDevicesForTeacherAudit()));
		setOperationItemsForLpStatusCheck(new java.util.LinkedHashSet<net.xidlims.domain.OperationItem>(that.getOperationItemsForLpStatusCheck()));
		setLabRoomDevicesForAllowAppointment(new java.util.LinkedHashSet<net.xidlims.domain.LabRoomDevice>(that.getLabRoomDevicesForAllowAppointment()));
		setOperationItemsForLpCategoryPublic(new java.util.LinkedHashSet<net.xidlims.domain.OperationItem>(that.getOperationItemsForLpCategoryPublic()));
		setOperationItemsForLpStatusChange(new java.util.LinkedHashSet<net.xidlims.domain.OperationItem>(that.getOperationItemsForLpStatusChange()));
		setLabWorkersForLwCategoryStaff(new java.util.LinkedHashSet<net.xidlims.domain.LabWorker>(that.getLabWorkersForLwCategoryStaff()));
		setOperationItemsForLpCategoryRewardLevel(new java.util.LinkedHashSet<net.xidlims.domain.OperationItem>(that.getOperationItemsForLpCategoryRewardLevel()));
		setLabWorkersForLwForeignLanguageLevel(new java.util.LinkedHashSet<net.xidlims.domain.LabWorker>(that.getLabWorkersForLwForeignLanguageLevel()));
		setLabWorkersForLwDegree(new java.util.LinkedHashSet<net.xidlims.domain.LabWorker>(that.getLabWorkersForLwDegree()));
		setOperationItemsForLpCategoryApp(new java.util.LinkedHashSet<net.xidlims.domain.OperationItem>(that.getOperationItemsForLpCategoryApp()));
		setLabWorkersForLwBookLevel(new java.util.LinkedHashSet<net.xidlims.domain.LabWorker>(that.getLabWorkersForLwBookLevel()));
		setOperationItemsForLpCategoryGuideBook(new java.util.LinkedHashSet<net.xidlims.domain.OperationItem>(that.getOperationItemsForLpCategoryGuideBook()));
		setOperationItemsForLpCategoryStudent(new java.util.LinkedHashSet<net.xidlims.domain.OperationItem>(that.getOperationItemsForLpCategoryStudent()));
		setOperationItemsForLpCategoryNature(new java.util.LinkedHashSet<net.xidlims.domain.OperationItem>(that.getOperationItemsForLpCategoryNature()));
		setLabWorkersForLwMainWork(new java.util.LinkedHashSet<net.xidlims.domain.LabWorker>(that.getLabWorkersForLwMainWork()));
		setLabWorkersForLwPaperLevel(new java.util.LinkedHashSet<net.xidlims.domain.LabWorker>(that.getLabWorkersForLwPaperLevel()));
		setLabWorkersForLwSpecialtyDuty(new java.util.LinkedHashSet<net.xidlims.domain.LabWorker>(that.getLabWorkersForLwSpecialtyDuty()));
		setLabRoomDevicesForIsAudit(new java.util.LinkedHashSet<net.xidlims.domain.LabRoomDevice>(that.getLabRoomDevicesForIsAudit()));
		setLabWorkersForLwSubject(new java.util.LinkedHashSet<net.xidlims.domain.LabWorker>(that.getLabWorkersForLwSubject()));
		setOperationItemsForLpCategoryRequire(new java.util.LinkedHashSet<net.xidlims.domain.OperationItem>(that.getOperationItemsForLpCategoryRequire()));
		setLabWorkersForLwForeignLanguage(new java.util.LinkedHashSet<net.xidlims.domain.LabWorker>(that.getLabWorkersForLwForeignLanguage()));
		setLabWorkersForLwAcademicDegree(new java.util.LinkedHashSet<net.xidlims.domain.LabWorker>(that.getLabWorkersForLwAcademicDegree()));
		setLabWorkersForLwReward(new java.util.LinkedHashSet<net.xidlims.domain.LabWorker>(that.getLabWorkersForLwReward()));
		setLabWorkersForLwEmployment(new java.util.LinkedHashSet<net.xidlims.domain.LabWorker>(that.getLabWorkersForLwEmployment()));
		setOperationItemsForLpCategoryMain(new java.util.LinkedHashSet<net.xidlims.domain.OperationItem>(that.getOperationItemsForLpCategoryMain()));
		//新增
		setLabConstructionProjectAudits(new java.util.LinkedHashSet<net.xidlims.domain.LabConstructionProjectAudit>(that.getLabConstructionProjectAudits()));
		setLabConstructionPurchaseAudits(new java.util.LinkedHashSet<net.xidlims.domain.LabConstructionPurchaseAudit>(that.getLabConstructionPurchaseAudits()));
		setLabConstructionPurchases(new java.util.LinkedHashSet<net.xidlims.domain.LabConstructionPurchase>(that.getLabConstructionPurchases()));
		setLabConstructionFundingAudits(new java.util.LinkedHashSet<net.xidlims.domain.LabConstructionFundingAudit>(that.getLabConstructionFundingAudits()));
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("id=[").append(id).append("] ");
		buffer.append("CNumber=[").append(CNumber).append("] ");
		buffer.append("CCategory=[").append(CCategory).append("] ");
		buffer.append("CName=[").append(CName).append("] ");
		buffer.append("enabled=[").append(enabled).append("] ");

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
		if (!(obj instanceof CDictionary))
			return false;
		CDictionary equalCheck = (CDictionary) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
