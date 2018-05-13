package net.xidlims.domain;

import java.io.Serializable;

import java.lang.StringBuilder;

import java.util.LinkedHashSet;
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
		@NamedQuery(name = "findAllLabReservations", query = "select myLabReservation from LabReservation myLabReservation"),
		@NamedQuery(name = "findLabReservationByAuditResults", query = "select myLabReservation from LabReservation myLabReservation where myLabReservation.auditResults = ?1"),
		@NamedQuery(name = "findLabReservationByElectiveGroup", query = "select myLabReservation from LabReservation myLabReservation where myLabReservation.electiveGroup = ?1"),
		@NamedQuery(name = "findLabReservationByElectiveGroupContaining", query = "select myLabReservation from LabReservation myLabReservation where myLabReservation.electiveGroup like ?1"),
		@NamedQuery(name = "findLabReservationByEnvironmentalRequirements", query = "select myLabReservation from LabReservation myLabReservation where myLabReservation.environmentalRequirements = ?1"),
		@NamedQuery(name = "findLabReservationByEventName", query = "select myLabReservation from LabReservation myLabReservation where myLabReservation.eventName = ?1"),
		@NamedQuery(name = "findLabReservationByEventNameContaining", query = "select myLabReservation from LabReservation myLabReservation where myLabReservation.eventName like ?1"),
		@NamedQuery(name = "findLabReservationById", query = "select myLabReservation from LabReservation myLabReservation where myLabReservation.id = ?1"),
		@NamedQuery(name = "findLabReservationByItemReleasese", query = "select myLabReservation from LabReservation myLabReservation where myLabReservation.itemReleasese = ?1"),
		@NamedQuery(name = "findLabReservationByNumber", query = "select myLabReservation from LabReservation myLabReservation where myLabReservation.number = ?1"),
		@NamedQuery(name = "findLabReservationByPrimaryKey", query = "select myLabReservation from LabReservation myLabReservation where myLabReservation.id = ?1"),
		@NamedQuery(name = "findLabReservationByRemarks", query = "select myLabReservation from LabReservation myLabReservation where myLabReservation.remarks = ?1"),
		@NamedQuery(name = "findLabReservationByRemarksContaining", query = "select myLabReservation from LabReservation myLabReservation where myLabReservation.remarks like ?1"),
		@NamedQuery(name = "findLabReservationByReservations", query = "select myLabReservation from LabReservation myLabReservation where myLabReservation.reservations = ?1"),
		@NamedQuery(name = "findLabReservationBySelecteNumber", query = "select myLabReservation from LabReservation myLabReservation where myLabReservation.selecteNumber = ?1") })
@Table(catalog = "xidlims", name = "lab_reservation")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "LabReservation")
@XmlRootElement(namespace = "xidlims/net/xidlims/domain")
public class LabReservation implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ʵ����ԤԼ��
	 * 
	 */

	@Column(name = "id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	@GeneratedValue(strategy = GenerationType.AUTO)
	Integer id;
	/**
	 * ����
	 * 
	 */

	@Column(name = "event_name")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String eventName;
	/**
	 * ԤԼ����/ʵ������
	 * 
	 */

	@Column(name = "reservations", columnDefinition = "TEXT")
	@Basic(fetch = FetchType.EAGER)
	@Lob
	@XmlElement
	String reservations;
	/**
	 * ��ע
	 * 
	 */

	@Column(name = "remarks")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String remarks;
	/**
	 * ѡ����
	 * 
	 */

	@Column(name = "elective_group")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String electiveGroup;
	/**
	 * ����Ҫ��
	 * 
	 */

	@Column(name = "environmental_requirements", columnDefinition = "TEXT")
	@Basic(fetch = FetchType.EAGER)
	@Lob
	@XmlElement
	String environmentalRequirements;
	/**
	 * ����
	 * 
	 */

	@Column(name = "number")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer number;
	/**
	 * 1 ͨ��2����У�3δ��ˣ�4 ��˾ܾ�
	 * 
	 */

	@Column(name = "audit_results")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer auditResults;
	/**
	 * ���� 1���Է���
	 * 
	 */

	@Column(name = "item_releasese")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer itemReleasese;
	/**
	 * ��ѡ����
	 * 
	 */

	@Column(name = "selecte_number")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer selecteNumber;

	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "activity_category", referencedColumnName = "id") })
	@XmlTransient
	CDictionary CDictionaryByActivityCategory;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "lab_room", referencedColumnName = "id") })
	@XmlTransient
	LabRoom labRoom;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "contacts", referencedColumnName = "username") })
	@XmlTransient
	User user;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "lab_reservet_ype", referencedColumnName = "id") })
	@XmlTransient
	CDictionary CDictionaryByLabReservetYpe;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "self_course_code", referencedColumnName = "id") })
	@XmlTransient
	TimetableSelfCourse timetableSelfCourse;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "class_no", referencedColumnName = "course_no") })
	@XmlTransient
	SchoolCourse schoolCourse;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "timetable_id", referencedColumnName = "id") })
	@XmlTransient
	TimetableAppointment timetableAppointment;
	/**
	 */
	@OneToMany(mappedBy = "labReservation", cascade = { CascadeType.REMOVE }, fetch = FetchType.EAGER)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.LabReservationTimeTable> labReservationTimeTables;
	/**
	 */
	@OneToMany(mappedBy = "labReservation", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.LabReservationAudit> labReservationAudits;
	/**
	 */
	@OneToMany(mappedBy = "labReservation", cascade = { CascadeType.REMOVE }, fetch = FetchType.EAGER)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.LabReservationTimeTableStudent> labReservationTimeTableStudents;

	/**
	 * ʵ����ԤԼ��
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * ʵ����ԤԼ��
	 * 
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * ����
	 * 
	 */
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	/**
	 * ����
	 * 
	 */
	public String getEventName() {
		return this.eventName;
	}

	/**
	 * ԤԼ����/ʵ������
	 * 
	 */
	public void setReservations(String reservations) {
		this.reservations = reservations;
	}

	/**
	 * ԤԼ����/ʵ������
	 * 
	 */
	public String getReservations() {
		return this.reservations;
	}

	/**
	 * ��ע
	 * 
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	/**
	 * ��ע
	 * 
	 */
	public String getRemarks() {
		return this.remarks;
	}

	/**
	 * ѡ����
	 * 
	 */
	public void setElectiveGroup(String electiveGroup) {
		this.electiveGroup = electiveGroup;
	}

	/**
	 * ѡ����
	 * 
	 */
	public String getElectiveGroup() {
		return this.electiveGroup;
	}

	/**
	 * ����Ҫ��
	 * 
	 */
	public void setEnvironmentalRequirements(String environmentalRequirements) {
		this.environmentalRequirements = environmentalRequirements;
	}

	/**
	 * ����Ҫ��
	 * 
	 */
	public String getEnvironmentalRequirements() {
		return this.environmentalRequirements;
	}

	/**
	 * ����
	 * 
	 */
	public void setNumber(Integer number) {
		this.number = number;
	}

	/**
	 * ����
	 * 
	 */
	public Integer getNumber() {
		return this.number;
	}

	/**
	 * 1 ͨ��2����У�3δ��ˣ�4 ��˾ܾ�
	 * 
	 */
	public void setAuditResults(Integer auditResults) {
		this.auditResults = auditResults;
	}

	/**
	 * 1 ͨ��2����У�3δ��ˣ�4 ��˾ܾ�
	 * 
	 */
	public Integer getAuditResults() {
		return this.auditResults;
	}

	/**
	 * ���� 1���Է���
	 * 
	 */
	public void setItemReleasese(Integer itemReleasese) {
		this.itemReleasese = itemReleasese;
	}

	/**
	 * ���� 1���Է���
	 * 
	 */
	public Integer getItemReleasese() {
		return this.itemReleasese;
	}

	/**
	 * ��ѡ����
	 * 
	 */
	public void setSelecteNumber(Integer selecteNumber) {
		this.selecteNumber = selecteNumber;
	}

	/**
	 * ��ѡ����
	 * 
	 */
	public Integer getSelecteNumber() {
		return this.selecteNumber;
	}

	/**
	 */
	public void setCDictionaryByActivityCategory(CDictionary CDictionaryByActivityCategory) {
		this.CDictionaryByActivityCategory = CDictionaryByActivityCategory;
	}

	/**
	 */
	@JsonIgnore
	public CDictionary getCDictionaryByActivityCategory() {
		return CDictionaryByActivityCategory;
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
	public void setCDictionaryByLabReservetYpe(CDictionary CDictionaryByLabReservetYpe) {
		this.CDictionaryByLabReservetYpe = CDictionaryByLabReservetYpe;
	}

	/**
	 */
	@JsonIgnore
	public CDictionary getCDictionaryByLabReservetYpe() {
		return CDictionaryByLabReservetYpe;
	}

	/**
	 */
	public void setTimetableSelfCourse(TimetableSelfCourse timetableSelfCourse) {
		this.timetableSelfCourse = timetableSelfCourse;
	}

	/**
	 */
	@JsonIgnore
	public TimetableSelfCourse getTimetableSelfCourse() {
		return timetableSelfCourse;
	}

	/**
	 */
	public void setSchoolCourse(SchoolCourse schoolCourse) {
		this.schoolCourse = schoolCourse;
	}

	/**
	 */
	@JsonIgnore
	public SchoolCourse getSchoolCourse() {
		return schoolCourse;
	}

	/**
	 */
	public void setTimetableAppointment(TimetableAppointment timetableAppointment) {
		this.timetableAppointment = timetableAppointment;
	}

	/**
	 */
	@JsonIgnore
	public TimetableAppointment getTimetableAppointment() {
		return timetableAppointment;
	}

	/**
	 */
	public void setLabReservationTimeTables(Set<LabReservationTimeTable> labReservationTimeTables) {
		this.labReservationTimeTables = labReservationTimeTables;
	}

	/**
	 */
	@JsonIgnore
	public Set<LabReservationTimeTable> getLabReservationTimeTables() {
		if (labReservationTimeTables == null) {
			labReservationTimeTables = new java.util.LinkedHashSet<net.xidlims.domain.LabReservationTimeTable>();
		}
		return labReservationTimeTables;
	}

	/**
	 */
	public void setLabReservationAudits(Set<LabReservationAudit> labReservationAudits) {
		this.labReservationAudits = labReservationAudits;
	}

	/**
	 */
	@JsonIgnore
	public Set<LabReservationAudit> getLabReservationAudits() {
		if (labReservationAudits == null) {
			labReservationAudits = new java.util.LinkedHashSet<net.xidlims.domain.LabReservationAudit>();
		}
		return labReservationAudits;
	}

	/**
	 */
	public void setLabReservationTimeTableStudents(Set<LabReservationTimeTableStudent> labReservationTimeTableStudents) {
		this.labReservationTimeTableStudents = labReservationTimeTableStudents;
	}

	/**
	 */
	@JsonIgnore
	public Set<LabReservationTimeTableStudent> getLabReservationTimeTableStudents() {
		if (labReservationTimeTableStudents == null) {
			labReservationTimeTableStudents = new java.util.LinkedHashSet<net.xidlims.domain.LabReservationTimeTableStudent>();
		}
		return labReservationTimeTableStudents;
	}

	/**
	 */
	public LabReservation() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(LabReservation that) {
		setId(that.getId());
		setEventName(that.getEventName());
		setReservations(that.getReservations());
		setRemarks(that.getRemarks());
		setElectiveGroup(that.getElectiveGroup());
		setEnvironmentalRequirements(that.getEnvironmentalRequirements());
		setNumber(that.getNumber());
		setAuditResults(that.getAuditResults());
		setItemReleasese(that.getItemReleasese());
		setSelecteNumber(that.getSelecteNumber());
		setCDictionaryByActivityCategory(that.getCDictionaryByActivityCategory());
		setLabRoom(that.getLabRoom());
		setUser(that.getUser());
		setCDictionaryByLabReservetYpe(that.getCDictionaryByLabReservetYpe());
		setTimetableSelfCourse(that.getTimetableSelfCourse());
		setSchoolCourse(that.getSchoolCourse());
		setTimetableAppointment(that.getTimetableAppointment());
		setLabReservationTimeTables(new java.util.LinkedHashSet<net.xidlims.domain.LabReservationTimeTable>(that.getLabReservationTimeTables()));
		setLabReservationAudits(new java.util.LinkedHashSet<net.xidlims.domain.LabReservationAudit>(that.getLabReservationAudits()));
		setLabReservationTimeTableStudents(new java.util.LinkedHashSet<net.xidlims.domain.LabReservationTimeTableStudent>(that.getLabReservationTimeTableStudents()));
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("id=[").append(id).append("] ");
		buffer.append("eventName=[").append(eventName).append("] ");
		buffer.append("reservations=[").append(reservations).append("] ");
		buffer.append("remarks=[").append(remarks).append("] ");
		buffer.append("electiveGroup=[").append(electiveGroup).append("] ");
		buffer.append("environmentalRequirements=[").append(environmentalRequirements).append("] ");
		buffer.append("number=[").append(number).append("] ");
		buffer.append("auditResults=[").append(auditResults).append("] ");
		buffer.append("itemReleasese=[").append(itemReleasese).append("] ");
		buffer.append("selecteNumber=[").append(selecteNumber).append("] ");

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
		if (!(obj instanceof LabReservation))
			return false;
		LabReservation equalCheck = (LabReservation) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
