package net.xidlims.domain;

import java.io.Serializable;

import java.lang.StringBuilder;

import java.math.BigDecimal;

import java.util.Calendar;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import net.xidlims.domain.LabAnnex;
import net.xidlims.domain.User;

import org.codehaus.jackson.annotate.JsonIgnore;

import javax.xml.bind.annotation.*;

import javax.persistence.*;

/**
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "findAllLabRooms", query = "select myLabRoom from LabRoom myLabRoom"),
		@NamedQuery(name = "findLabRoomById", query = "select myLabRoom from LabRoom myLabRoom where myLabRoom.id = ?1"),
		@NamedQuery(name = "findLabRoomByIsUsed", query = "select myLabRoom from LabRoom myLabRoom where myLabRoom.isUsed = ?1"),
		@NamedQuery(name = "findLabRoomByLabRoomActive", query = "select myLabRoom from LabRoom myLabRoom where myLabRoom.labRoomActive = ?1"),
		@NamedQuery(name = "findLabRoomByLabRoomAddress", query = "select myLabRoom from LabRoom myLabRoom where myLabRoom.labRoomAddress = ?1"),
		@NamedQuery(name = "findLabRoomByLabRoomAddressContaining", query = "select myLabRoom from LabRoom myLabRoom where myLabRoom.labRoomAddress like ?1"),
		@NamedQuery(name = "findLabRoomByLabRoomArea", query = "select myLabRoom from LabRoom myLabRoom where myLabRoom.labRoomArea = ?1"),
		@NamedQuery(name = "findLabRoomByLabRoomAudit", query = "select myLabRoom from LabRoom myLabRoom where myLabRoom.labRoomAudit = ?1"),
		@NamedQuery(name = "findLabRoomByLabRoomCapacity", query = "select myLabRoom from LabRoom myLabRoom where myLabRoom.labRoomCapacity = ?1"),
		@NamedQuery(name = "findLabRoomByLabRoomEnName", query = "select myLabRoom from LabRoom myLabRoom where myLabRoom.labRoomEnName = ?1"),
		@NamedQuery(name = "findLabRoomByLabRoomEnNameContaining", query = "select myLabRoom from LabRoom myLabRoom where myLabRoom.labRoomEnName like ?1"),
		@NamedQuery(name = "findLabRoomByLabRoomIntroduction", query = "select myLabRoom from LabRoom myLabRoom where myLabRoom.labRoomIntroduction = ?1"),
		@NamedQuery(name = "findLabRoomByLabRoomManagerAgencies", query = "select myLabRoom from LabRoom myLabRoom where myLabRoom.labRoomManagerAgencies = ?1"),
		@NamedQuery(name = "findLabRoomByLabRoomManagerAgenciesContaining", query = "select myLabRoom from LabRoom myLabRoom where myLabRoom.labRoomManagerAgencies like ?1"),
		@NamedQuery(name = "findLabRoomByLabRoomName", query = "select myLabRoom from LabRoom myLabRoom where myLabRoom.labRoomName = ?1"),
		@NamedQuery(name = "findLabRoomByLabRoomNameContaining", query = "select myLabRoom from LabRoom myLabRoom where myLabRoom.labRoomName like ?1"),
		@NamedQuery(name = "findLabRoomByLabRoomNumber", query = "select myLabRoom from LabRoom myLabRoom where myLabRoom.labRoomNumber = ?1"),
		@NamedQuery(name = "findLabRoomByLabRoomNumberContaining", query = "select myLabRoom from LabRoom myLabRoom where myLabRoom.labRoomNumber like ?1"),
		@NamedQuery(name = "findLabRoomByLabRoomPrizeInformation", query = "select myLabRoom from LabRoom myLabRoom where myLabRoom.labRoomPrizeInformation = ?1"),
		@NamedQuery(name = "findLabRoomByLabRoomRegulations", query = "select myLabRoom from LabRoom myLabRoom where myLabRoom.labRoomRegulations = ?1"),
		@NamedQuery(name = "findLabRoomByLabRoomReservation", query = "select myLabRoom from LabRoom myLabRoom where myLabRoom.labRoomReservation = ?1"),
		@NamedQuery(name = "findLabRoomByLabRoomTimeCreate", query = "select myLabRoom from LabRoom myLabRoom where myLabRoom.labRoomTimeCreate = ?1"),
		@NamedQuery(name = "findLabRoomByLabRoonAbbreviation", query = "select myLabRoom from LabRoom myLabRoom where myLabRoom.labRoonAbbreviation = ?1"),
		@NamedQuery(name = "findLabRoomByLabRoonAbbreviationContaining", query = "select myLabRoom from LabRoom myLabRoom where myLabRoom.labRoonAbbreviation like ?1"),
		@NamedQuery(name = "findLabRoomByPrimaryKey", query = "select myLabRoom from LabRoom myLabRoom where myLabRoom.id = ?1") })
@Table(catalog = "xidlims", name = "lab_room")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "LabRoom")
@XmlRootElement(namespace = "xidlims/net/xidlims/domain")
public class LabRoom implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ʵ���ҷ��ұ�
	 * 
	 */

	@Column(name = "id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@XmlElement
	Integer id;
	/**
	 * ʵ���ұ��
	 * 
	 */

	@Column(name = "lab_room_number")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String labRoomNumber;
	
	/**
	 * ʵ���ұ��
	 * 
	 */

	@Column(name = "lab_room_subject")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String labRoomSubject;
	
	public String getLabRoomSubject() {
		return labRoomSubject;
	}

	public void setLabRoomSubject(String labRoomSubject) {
		this.labRoomSubject = labRoomSubject;
	}

	/**
	 * ʵ�������
	 * 
	 */

	@Column(name = "lab_room_name")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String labRoomName;
	/**
	 * ʵ����Ӣ�����
	 * 
	 */

	@Column(name = "lab_room_en_name")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String labRoomEnName;
	/**
	 * ʵ���Ҽ��
	 * 
	 */

	@Column(name = "lab_roon_abbreviation")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String labRoonAbbreviation;

	/**
	 * ʵ���ҵص�
	 * 
	 */

	@Column(name = "lab_room_address", length = 40)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String labRoomAddress;
	
	/**
	 * ʹ�����
	 * 
	 */

	@Column(name = "lab_room_area", precision = 11)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal labRoomArea;
	/**
	 * ʵ��������
	 * 
	 */

	@Column(name = "lab_room_capacity")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer labRoomCapacity;


	/**
	 * 可同时预约该实验室的并发次数（人数）
	 */
	@Column(name = "reservation_number")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer reservationNumber;
	
	@Column(name = "is_special")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer isSpecial;
	
	/**
	 * �����
	 * 
	 */

	@Column(name = "lab_room_manager_agencies")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String labRoomManagerAgencies;
	
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "system_room", referencedColumnName = "room_number") })
	@XmlTransient
	SystemRoom systemRoom;

	/**
	 * �Ƿ���ã���� )
	 * 
	 */

	@Column(name = "lab_room_active")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer labRoomActive;
	/**
	 * �Ƿ��ԤԼ�����
	 * 
	 */

	@Column(name = "lab_room_reservation")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer labRoomReservation;
	/**
	 * ԤԼ�Ƿ���ˣ���� ��
	 * 
	 */

	@Column(name = "lab_room_audit")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer labRoomAudit;
	/**
	 * ʵ���Ҽ��
	 * 
	 */

	@Column(name = "lab_room_introduction", columnDefinition = "TEXT")
	@Basic(fetch = FetchType.EAGER)
	@Lob
	@XmlElement
	String labRoomIntroduction;
	/**
	 * �����ƶ�
	 * 
	 */

	@Column(name = "lab_room_regulations", columnDefinition = "TEXT")
	@Basic(fetch = FetchType.EAGER)
	@Lob
	@XmlElement
	String labRoomRegulations;
	/**
	 * ����Ϣ
	 * 
	 */

	@Column(name = "lab_room_prize_information", columnDefinition = "TEXT")
	@Basic(fetch = FetchType.EAGER)
	@Lob
	@XmlElement
	String labRoomPrizeInformation;

	/**
	 */

	@Column(name = "is_used")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer isUsed;
	/**
	 * ʵ���Ҵ���ʱ��
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "lab_room_time_create")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar labRoomTimeCreate;
	
	/**
	 * ����ѧ��
	 * 
	 */
  
	/**
	 * ��ϵ��ʽ
	 * 
	 */

	@Column(name = "lab_room_phone")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String labRoomPhone;
	
	/**
	 */
	@OneToMany(mappedBy = "labRoom", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.LabRoomCourseCapacity> labRoomCourseCapacities;
	
	
	/**
	 */
	public void setLabRoomCourseCapacities(Set<LabRoomCourseCapacity> labRoomCourseCapacities) {
		this.labRoomCourseCapacities = labRoomCourseCapacities;
	}

	/**
	 */
	@JsonIgnore
	public Set<LabRoomCourseCapacity> getLabRoomCourseCapacities() {
		if (labRoomCourseCapacities == null) {
			labRoomCourseCapacities = new java.util.LinkedHashSet<net.xidlims.domain.LabRoomCourseCapacity>();
		}
		return labRoomCourseCapacities;
	}


	public String getLabRoomPhone() {
		return labRoomPhone;
	}

	public void setLabRoomPhone(String labRoomPhone) {
		this.labRoomPhone = labRoomPhone;
	}

	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "lab_center_id", referencedColumnName = "id") })
	@XmlTransient
	LabCenter labCenter;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "lab_annex_id", referencedColumnName = "id") })
	@XmlTransient
	LabAnnex labAnnex;
	/**
	 */
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "lab_room_type", referencedColumnName = "id") })
	@XmlTransient
	CDictionary CDictionaryByLabRoom;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "creater_user", referencedColumnName = "username") })
	@XmlTransient
	User user;
	
/*	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({@JoinColumn(name="lab_room_subject", referencedColumnName="s_number")})
	@XmlTransient
	SystemSubject12 systemSubject12;*/
	
	/**
	 */
	@OneToMany(mappedBy = "labRoom", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.TimetableLabRelated> timetableLabRelateds;
	/**
	 */
	@OneToMany(mappedBy = "labRoom", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.LabRoomDevice> labRoomDevices;
	/**
	 */
	@OneToMany(mappedBy = "labRoom", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.LabRoomAdmin> labRoomAdmins;
	/**
	 */
	@OneToMany(mappedBy = "labRoom", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.LabRoomAgent> labRoomAgents;
	/**
	 */
	@OneToMany(mappedBy = "labRoom", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.OperationItem> operationItem;///原来的一对多
	//新增多对多
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(catalog = "xidlims", name = "m_operation_item_lab_room", joinColumns = { @JoinColumn(name = "lab_room", referencedColumnName = "id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "operation_item", referencedColumnName = "id", nullable = false, updatable = false) })
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.OperationItem> operationItems;
	
	@OneToMany(mappedBy = "labRoom", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.LabReservation> labReservations;
	
	//2015.10.13新增
	
	@OneToMany(mappedBy = "labRoom", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.CommonDocument> commonDocuments;
	/**
	 */
	@OneToMany(mappedBy = "labRoom", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.CommonVideo> commonVideos;
	
	/**
	 */
	@OneToMany(mappedBy = "labRoom", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.AssetCabinet> assetCabinets;
	/**
	 */
	@OneToMany(mappedBy = "labRoom", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.AssetReceiveAudit> assetReceiveAudits;
	/**
	 */
	@OneToMany(mappedBy = "labRoom", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.AssetAppRecord> assetAppRecords;
	/**
	 */
	@OneToMany(mappedBy = "labRoom", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.AssetCabinetWarehouseAccess> assetCabinetWarehouseAccesses;

	
	
	@Column(name = "lab_room_attentions", columnDefinition = "TEXT")
	@Basic(fetch = FetchType.EAGER)
	@Lob
	@XmlElement
	String labRoomAttentions;

	@JsonIgnore
	public java.util.Set<net.xidlims.domain.LabReservation> getLabReservations() {
		return labReservations;
	}

	public void setLabReservations(
			java.util.Set<net.xidlims.domain.LabReservation> labReservations) {
		this.labReservations = labReservations;
	}
	
	/*@JsonIgnore
	public SystemSubject12 getSystemSubject12() {
		return systemSubject12;
	}

	public void setSystemSubject12(SystemSubject12 systemSubject12) {
		this.systemSubject12 = systemSubject12;
	}
*/
	/**
	 */
	public void setLabAnnex(LabAnnex labAnnex) {
		this.labAnnex = labAnnex;
	}

	/**
	 */
	@JsonIgnore
	public LabAnnex getLabAnnex() {
		return labAnnex;
	}
	
	/**
	 */
	public void setCDictionaryByLabRoomType(CDictionary CDictionaryByLabRoom) {
		this.CDictionaryByLabRoom = CDictionaryByLabRoom;
	}
	public Integer getIsSpecial() {
		return isSpecial;
	}

	public void setIsSpecial(Integer isSpecial) {
		this.isSpecial = isSpecial;
	}
	/**
	 */
	@JsonIgnore
	public CDictionary getCDictionaryByLabRoom() {
		return CDictionaryByLabRoom;
	}
	
	public SystemRoom getSystemRoom() {
		return systemRoom;
	}

	public void setSystemRoom(SystemRoom systemRoom) {
		this.systemRoom = systemRoom;
	}
	
	/**
	 * ʵ���ҷ��ұ�
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * ʵ���ҷ��ұ�
	 * 
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * ʵ���ұ��
	 * 
	 */
	public void setLabRoomNumber(String labRoomNumber) {
		this.labRoomNumber = labRoomNumber;
	}

	/**
	 * ʵ���ұ��
	 * 
	 */
	public String getLabRoomNumber() {
		return this.labRoomNumber;
	}

	/**
	 * ʵ�������
	 * 
	 */
	public void setLabRoomName(String labRoomName) {
		this.labRoomName = labRoomName;
	}

	/**
	 * ʵ�������
	 * 
	 */
	public String getLabRoomName() {
		return this.labRoomName;
	}

	/**
	 * ʵ����Ӣ�����
	 * 
	 */
	public void setLabRoomEnName(String labRoomEnName) {
		this.labRoomEnName = labRoomEnName;
	}

	/**
	 * ʵ����Ӣ�����
	 * 
	 */
	public String getLabRoomEnName() {
		return this.labRoomEnName;
	}

	/**
	 * ʵ���Ҽ��
	 * 
	 */
	public void setLabRoonAbbreviation(String labRoonAbbreviation) {
		this.labRoonAbbreviation = labRoonAbbreviation;
	}

	/**
	 * ʵ���Ҽ��
	 * 
	 */
	public String getLabRoonAbbreviation() {
		return this.labRoonAbbreviation;
	}

	/**
	 * ʵ���ҵص�
	 * 
	 */
	public void setLabRoomAddress(String labRoomAddress) {
		this.labRoomAddress = labRoomAddress;
	}

	/**
	 * ʵ���ҵص�
	 * 
	 */
	public String getLabRoomAddress() {
		return this.labRoomAddress;
	}

	/**
	 * ʹ�����
	 * 
	 */
	public void setLabRoomArea(BigDecimal labRoomArea) {
		this.labRoomArea = labRoomArea;
	}

	/**
	 * ʹ�����
	 * 
	 */
	public BigDecimal getLabRoomArea() {
		return this.labRoomArea;
	}

	/**
	 * ʵ��������
	 * 
	 */
	public void setLabRoomCapacity(Integer labRoomCapacity) {
		this.labRoomCapacity = labRoomCapacity;
	}

	/**
	 * ʵ��������
	 * 
	 */
	public Integer getLabRoomCapacity() {
		return this.labRoomCapacity;
	}
	
	public Integer getReservationNumber() {
		return reservationNumber;
	}

	public void setReservationNumber(Integer reservationNumber) {
		this.reservationNumber = reservationNumber;
	}

	/**
	 * �����
	 * 
	 */
	public void setLabRoomManagerAgencies(String labRoomManagerAgencies) {
		this.labRoomManagerAgencies = labRoomManagerAgencies;
	}

	/**
	 * �����
	 * 
	 */
	public String getLabRoomManagerAgencies() {
		return this.labRoomManagerAgencies;
	}

	/**
	 * �Ƿ���ã���� )
	 * 
	 */
	public void setLabRoomActive(Integer labRoomActive) {
		this.labRoomActive = labRoomActive;
	}

	/**
	 * �Ƿ���ã���� )
	 * 
	 */
	public Integer getLabRoomActive() {
		return this.labRoomActive;
	}

	/**
	 * �Ƿ��ԤԼ�����
	 * 
	 */
	public void setLabRoomReservation(Integer labRoomReservation) {
		this.labRoomReservation = labRoomReservation;
	}

	/**
	 * �Ƿ��ԤԼ�����
	 * 
	 */
	public Integer getLabRoomReservation() {
		return this.labRoomReservation;
	}

	/**
	 * ԤԼ�Ƿ���ˣ���� ��
	 * 
	 */
	public void setLabRoomAudit(Integer labRoomAudit) {
		this.labRoomAudit = labRoomAudit;
	}

	/**
	 * ԤԼ�Ƿ���ˣ���� ��
	 * 
	 */
	public Integer getLabRoomAudit() {
		return this.labRoomAudit;
	}

	/**
	 * ʵ���Ҽ��
	 * 
	 */
	public void setLabRoomIntroduction(String labRoomIntroduction) {
		this.labRoomIntroduction = labRoomIntroduction;
	}

	/**
	 * ʵ���Ҽ��
	 * 
	 */
	public String getLabRoomIntroduction() {
		return this.labRoomIntroduction;
	}

	/**
	 * �����ƶ�
	 * 
	 */
	public void setLabRoomRegulations(String labRoomRegulations) {
		this.labRoomRegulations = labRoomRegulations;
	}

	/**
	 * �����ƶ�
	 * 
	 */
	public String getLabRoomRegulations() {
		return this.labRoomRegulations;
	}

	/**
	 * ����Ϣ
	 * 
	 */
	public void setLabRoomPrizeInformation(String labRoomPrizeInformation) {
		this.labRoomPrizeInformation = labRoomPrizeInformation;
	}

	/**
	 * ����Ϣ
	 * 
	 */
	public String getLabRoomPrizeInformation() {
		return this.labRoomPrizeInformation;
	}

	/**
	 */
	public void setIsUsed(Integer isUsed) {
		this.isUsed = isUsed;
	}

	/**
	 */
	public Integer getIsUsed() {
		return this.isUsed;
	}

	/**
	 * ʵ���Ҵ���ʱ��
	 * 
	 */
	public void setLabRoomTimeCreate(Calendar labRoomTimeCreate) {
		this.labRoomTimeCreate = labRoomTimeCreate;
	}

	/**
	 * ʵ���Ҵ���ʱ��
	 * 
	 */
	public Calendar getLabRoomTimeCreate() {
		return this.labRoomTimeCreate;
	}

	/**
	 */
	public void setLabCenter(LabCenter labCenter) {
		this.labCenter = labCenter;
	}

	/**
	 */
	@JsonIgnore
	public LabCenter getLabCenter() {
		return labCenter;
	}

	/**
	 */
	public void setTimetableLabRelateds(Set<TimetableLabRelated> timetableLabRelateds) {
		this.timetableLabRelateds = timetableLabRelateds;
	}

	/**
	 */
	@JsonIgnore
	public Set<TimetableLabRelated> getTimetableLabRelateds() {
		if (timetableLabRelateds == null) {
			timetableLabRelateds = new java.util.LinkedHashSet<net.xidlims.domain.TimetableLabRelated>();
		}
		return timetableLabRelateds;
	}

	/**
	 */
	public void setLabRoomDevices(Set<LabRoomDevice> labRoomDevices) {
		this.labRoomDevices = labRoomDevices;
	}

	/**
	 */
	@JsonIgnore
	public Set<LabRoomDevice> getLabRoomDevices() {
		if (labRoomDevices == null) {
			labRoomDevices = new java.util.LinkedHashSet<net.xidlims.domain.LabRoomDevice>();
		}
		return labRoomDevices;
	}

	/**
	 */
	public void setLabRoomAdmins(Set<LabRoomAdmin> labRoomAdmins) {
		this.labRoomAdmins = labRoomAdmins;
	}

	/**
	 */
	@JsonIgnore
	public Set<LabRoomAdmin> getLabRoomAdmins() {
		if (labRoomAdmins == null) {
			labRoomAdmins = new java.util.LinkedHashSet<net.xidlims.domain.LabRoomAdmin>();
		}
		return labRoomAdmins;
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
	public void setOperationItem(Set<OperationItem> operationItem) {
		this.operationItem = operationItem;
	}

	/**
	 
	*/@JsonIgnore
	public Set<OperationItem> getOperationItem() {
		if (operationItem == null) {
			operationItem = new java.util.LinkedHashSet<net.xidlims.domain.OperationItem>();
		}
		return operationItem;
	}//原来的一对多
	
	//新增多对多
	public void setOperationItems(Set<OperationItem> operationItems) {
		this.operationItems = operationItems;
	}

	/**
	 */
	@JsonIgnore
	public Set<OperationItem> getOperationItems() {
		if (operationItems == null) {
			operationItems = new java.util.LinkedHashSet<net.xidlims.domain.OperationItem>();
		}
		return operationItems;
	}
	
	
	//2015.10.13新增
	

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
	
	public String getLabRoomAttentions() {
		return labRoomAttentions;
	}

	public void setLabRoomAttentions(String labRoomAttentions) {
		this.labRoomAttentions = labRoomAttentions;
	}
	
	/**
	 */
	public void setAssetCabinets(Set<AssetCabinet> assetCabinets) {
		this.assetCabinets = assetCabinets;
	}

	/**
	 */
	@JsonIgnore
	public Set<AssetCabinet> getAssetCabinets() {
		if (assetCabinets == null) {
			assetCabinets = new java.util.LinkedHashSet<net.xidlims.domain.AssetCabinet>();
		}
		return assetCabinets;
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
	public void setAssetAppRecords(Set<AssetAppRecord> assetAppRecords) {
		this.assetAppRecords = assetAppRecords;
	}

	/**
	 */
	@JsonIgnore
	public Set<AssetAppRecord> getAssetAppRecords() {
		if (assetAppRecords == null) {
			assetAppRecords = new java.util.LinkedHashSet<net.xidlims.domain.AssetAppRecord>();
		}
		return assetAppRecords;
	}

	/**
	 */
	public void setAssetCabinetWarehouseAccesses(Set<AssetCabinetWarehouseAccess> assetCabinetWarehouseAccesses) {
		this.assetCabinetWarehouseAccesses = assetCabinetWarehouseAccesses;
	}

	/**
	 */
	@JsonIgnore
	public Set<AssetCabinetWarehouseAccess> getAssetCabinetWarehouseAccesses() {
		if (assetCabinetWarehouseAccesses == null) {
			assetCabinetWarehouseAccesses = new java.util.LinkedHashSet<net.xidlims.domain.AssetCabinetWarehouseAccess>();
		}
		return assetCabinetWarehouseAccesses;
	}

	
	/**
	 */
	public LabRoom() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(LabRoom that) {
		setId(that.getId());
		setLabRoomNumber(that.getLabRoomNumber());
		setLabRoomName(that.getLabRoomName());
		setLabRoomEnName(that.getLabRoomEnName());
		setLabRoonAbbreviation(that.getLabRoonAbbreviation());
		setLabRoomAddress(that.getLabRoomAddress());
		setLabRoomArea(that.getLabRoomArea());
		setLabRoomCapacity(that.getLabRoomCapacity());
		setReservationNumber(that.getReservationNumber());
		setLabRoomManagerAgencies(that.getLabRoomManagerAgencies());
		setSystemRoom(that.getSystemRoom());
		setLabRoomActive(that.getLabRoomActive());
		setLabRoomReservation(that.getLabRoomReservation());
		setLabRoomAudit(that.getLabRoomAudit());
		setLabRoomIntroduction(that.getLabRoomIntroduction());
		setLabRoomRegulations(that.getLabRoomRegulations());
		setLabRoomPrizeInformation(that.getLabRoomPrizeInformation());
		setIsUsed(that.getIsUsed());
		setLabRoomTimeCreate(that.getLabRoomTimeCreate());
		setLabCenter(that.getLabCenter());
		setTimetableLabRelateds(new java.util.LinkedHashSet<net.xidlims.domain.TimetableLabRelated>(that.getTimetableLabRelateds()));
		setLabRoomDevices(new java.util.LinkedHashSet<net.xidlims.domain.LabRoomDevice>(that.getLabRoomDevices()));
		setLabRoomAdmins(new java.util.LinkedHashSet<net.xidlims.domain.LabRoomAdmin>(that.getLabRoomAdmins()));
		setLabRoomAgents(new java.util.LinkedHashSet<net.xidlims.domain.LabRoomAgent>(that.getLabRoomAgents()));
//		setOperationItems(new java.util.LinkedHashSet<net.xidlims.domain.OperationItem>(that.getOperationItems()));
		//新增多对多，与原来的一对多一样的 
		setOperationItems(new java.util.LinkedHashSet<net.xidlims.domain.OperationItem>(that.getOperationItems()));
//		setCDictionaryByLabRoomActive(that.getCDictionaryByLabRoomActive());
//		setCDictionaryByLabRoomType(that.getCDictionaryByLabRoomType());
		//2015.10.13新增
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
		buffer.append("labRoomNumber=[").append(labRoomNumber).append("] ");
		buffer.append("labRoomName=[").append(labRoomName).append("] ");
		buffer.append("labRoomEnName=[").append(labRoomEnName).append("] ");
		buffer.append("systemRoom=[").append(systemRoom).append("] ");
		buffer.append("labRoonAbbreviation=[").append(labRoonAbbreviation).append("] ");
		buffer.append("labRoomAddress=[").append(labRoomAddress).append("] ");
		buffer.append("labRoomArea=[").append(labRoomArea).append("] ");
		buffer.append("labRoomCapacity=[").append(labRoomCapacity).append("] ");
		buffer.append("reservationNumber=[").append(reservationNumber).append("] ");
		buffer.append("labRoomManagerAgencies=[").append(labRoomManagerAgencies).append("] ");
		buffer.append("labRoomActive=[").append(labRoomActive).append("] ");
		buffer.append("labRoomReservation=[").append(labRoomReservation).append("] ");
		buffer.append("labRoomAudit=[").append(labRoomAudit).append("] ");
		buffer.append("labRoomIntroduction=[").append(labRoomIntroduction).append("] ");
		buffer.append("labRoomRegulations=[").append(labRoomRegulations).append("] ");
		buffer.append("labRoomPrizeInformation=[").append(labRoomPrizeInformation).append("] ");
		buffer.append("isUsed=[").append(isUsed).append("] ");
		buffer.append("labRoomTimeCreate=[").append(labRoomTimeCreate).append("] ");

		return buffer.toString();
	}

	@JsonIgnore
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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
		if (!(obj instanceof LabRoom))
			return false;
		LabRoom equalCheck = (LabRoom) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
