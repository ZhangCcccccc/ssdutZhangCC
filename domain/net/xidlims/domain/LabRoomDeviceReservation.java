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
		@NamedQuery(name = "findAllLabRoomDeviceReservations", query = "select myLabRoomDeviceReservation from LabRoomDeviceReservation myLabRoomDeviceReservation"),
		@NamedQuery(name = "findLabRoomDeviceReservationByBegintime", query = "select myLabRoomDeviceReservation from LabRoomDeviceReservation myLabRoomDeviceReservation where myLabRoomDeviceReservation.begintime = ?1"),
		@NamedQuery(name = "findLabRoomDeviceReservationByContent", query = "select myLabRoomDeviceReservation from LabRoomDeviceReservation myLabRoomDeviceReservation where myLabRoomDeviceReservation.content = ?1"),
		@NamedQuery(name = "findLabRoomDeviceReservationByEndtime", query = "select myLabRoomDeviceReservation from LabRoomDeviceReservation myLabRoomDeviceReservation where myLabRoomDeviceReservation.endtime = ?1"),
		@NamedQuery(name = "findLabRoomDeviceReservationById", query = "select myLabRoomDeviceReservation from LabRoomDeviceReservation myLabRoomDeviceReservation where myLabRoomDeviceReservation.id = ?1"),
		@NamedQuery(name = "findLabRoomDeviceReservationByPhone", query = "select myLabRoomDeviceReservation from LabRoomDeviceReservation myLabRoomDeviceReservation where myLabRoomDeviceReservation.phone = ?1"),
		@NamedQuery(name = "findLabRoomDeviceReservationByPhoneContaining", query = "select myLabRoomDeviceReservation from LabRoomDeviceReservation myLabRoomDeviceReservation where myLabRoomDeviceReservation.phone like ?1"),
		@NamedQuery(name = "findLabRoomDeviceReservationByPrimaryKey", query = "select myLabRoomDeviceReservation from LabRoomDeviceReservation myLabRoomDeviceReservation where myLabRoomDeviceReservation.id = ?1"),
		@NamedQuery(name = "findLabRoomDeviceReservationByStage", query = "select myLabRoomDeviceReservation from LabRoomDeviceReservation myLabRoomDeviceReservation where myLabRoomDeviceReservation.stage = ?1"),
		@NamedQuery(name = "findLabRoomDeviceReservationByStageContaining", query = "select myLabRoomDeviceReservation from LabRoomDeviceReservation myLabRoomDeviceReservation where myLabRoomDeviceReservation.stage like ?1"),
		@NamedQuery(name = "findLabRoomDeviceReservationByTime", query = "select myLabRoomDeviceReservation from LabRoomDeviceReservation myLabRoomDeviceReservation where myLabRoomDeviceReservation.time = ?1") })
@Table(catalog = "xidlims", name = "lab_room_device_reservation")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "LabRoomDeviceReservation")
@XmlRootElement(namespace = "xidlims/net/xidlims/domain")
public class LabRoomDeviceReservation implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ʵ���ҷ����豸ԤԼ��
	 * 
	 */

	@Column(name = "id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	@XmlElement
	Integer id;
	/**
	 * ��������
	 * 
	 */

	@Column(name = "content", columnDefinition = "TEXT")
	@Basic(fetch = FetchType.EAGER)
	@Lob
	@XmlElement
	String content;
	/**
	 * ��������
	 * 
	 */

	@Column(name = "use_time")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String useTime;
	/**
	 * ��ϵ�绰
	 * 
	 */

	@Column(name = "phone")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String phone;
	/**
	 * ��ʼʱ��
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "begintime")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar begintime;
	/**
	 * ����ʱ��
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "endtime")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar endtime;
	
	/**
	 * ��ʼʱ��
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "originalBegin")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar originalBegin;
	/**
	 * ����ʱ��
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "originalEnd")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar originalEnd;
	
	/**
	 * ����ʱ��
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "time")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar time;
	/**
	 * �׶�
	 * 
	 */

	@Column(name = "stage")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer stage;
	
	/**
	 * �׶�
	 * 
	 */

	@Column(name = "tag")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer tag;
	
	@Column(name = "appointment_id")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer appointmentId;
	
	@Column(name = "inner_same")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String innerSame;
	
	@Column(name = "inner_device_name")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String innerDeviceName;
	
	@Column(name = "timetable_lab_device")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer timetableLabDevice;
	
	@Column(name = "reserve_hours", scale = 2, precision = 11)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal reserveHours;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "property", referencedColumnName = "id") })
	@XmlTransient
	CDictionary CDictionaryByCReservationProperty;
	
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "lab_room_device", referencedColumnName = "id") })
	@XmlTransient
	LabRoomDevice labRoomDevice;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "school_term", referencedColumnName = "id") })
	@XmlTransient
	SchoolTerm schoolTerm;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "status", referencedColumnName = "id") })
	@XmlTransient
	CDictionary CDictionaryByCAuditResult;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "reserve_user", referencedColumnName = "username") })
	@XmlTransient
	User userByReserveUser;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "teacher", referencedColumnName = "username") })
	@XmlTransient
	User userByTeacher;
	/**
	 */
	@OneToMany(mappedBy = "labRoomDeviceReservation", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.LabRoomDeviceReservationResult> labRoomDeviceReservationResults;

	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "research_project_id", referencedColumnName = "id") })
	@XmlTransient
	ResearchProject researchProject;
	/**
	 * ʵ���ҷ����豸ԤԼ��
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * ʵ���ҷ����豸ԤԼ��
	 * 
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * ��������
	 * 
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * ��������
	 * 
	 */
	public String getContent() {
		return this.content;
	}

	
	public String getUseTime() {
		return useTime;
	}

	public void setUseTime(String useTime) {
		this.useTime = useTime;
	}

	/**
	 * ��ϵ�绰
	 * 
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * ��ϵ�绰
	 * 
	 */
	public String getPhone() {
		return this.phone;
	}

	/**
	 * ��ʼʱ��
	 * 
	 */
	public void setBegintime(Calendar begintime) {
		this.begintime = begintime;
	}

	/**
	 * ��ʼʱ��
	 * 
	 */
	public Calendar getBegintime() {
		return this.begintime;
	}

	/**
	 * ����ʱ��
	 * 
	 */
	public void setEndtime(Calendar endtime) {
		this.endtime = endtime;
	}

	/**
	 * ����ʱ��
	 * 
	 */
	public Calendar getEndtime() {
		return this.endtime;
	}

	public Calendar getOriginalBegin() {
		return originalBegin;
	}

	public void setOriginalBegin(Calendar originalBegin) {
		this.originalBegin = originalBegin;
	}

	public Calendar getOriginalEnd() {
		return originalEnd;
	}

	public void setOriginalEnd(Calendar originalEnd) {
		this.originalEnd = originalEnd;
	}

	/**
	 * ����ʱ��
	 * 
	 */
	public void setTime(Calendar time) {
		this.time = time;
	}

	/**
	 * ����ʱ��
	 * 
	 */
	public Calendar getTime() {
		return this.time;
	}

	/**
	 * �׶�
	 * 
	 */
	public void setStage(Integer stage) {
		this.stage = stage;
	}

	/**
	 * �׶�
	 * 
	 */
	public Integer getStage() {
		return this.stage;
	}

	
	
	public Integer getTag() {
		return tag;
	}

	public void setTag(Integer tag) {
		this.tag = tag;
	}
	
	public Integer getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(Integer appointmentId) {
		this.appointmentId = appointmentId;
	}
	
	public String getInnerSame() {
		return innerSame;
	}

	public void setInnerSame(String innerSame) {
		this.innerSame = innerSame;
	}

	public String getInnerDeviceName() {
		return innerDeviceName;
	}

	public void setInnerDeviceName(String innerDeviceName) {
		this.innerDeviceName = innerDeviceName;
	}
	
	public Integer getTimetableLabDevice() {
		return timetableLabDevice;
	}

	public void setTimetableLabDevice(Integer timetableLabDevice) {
		this.timetableLabDevice = timetableLabDevice;
	}

	public BigDecimal getReserveHours() {
		return reserveHours;
	}

	public void setReserveHours(BigDecimal reserveHours) {
		this.reserveHours = reserveHours;
	}
	
	/**
	 */
	public void setCReservationProperty(CDictionary CDictionaryByCReservationProperty) {
		this.CDictionaryByCReservationProperty = CDictionaryByCReservationProperty;
	}

	/**
	 */
	@JsonIgnore
	public CDictionary getCReservationProperty() {
		return CDictionaryByCReservationProperty;
	}

	/**
	 */
	public void setLabRoomDevice(LabRoomDevice labRoomDevice) {
		this.labRoomDevice = labRoomDevice;
	}

	/**
	 */
	@JsonIgnore
	public LabRoomDevice getLabRoomDevice() {
		return labRoomDevice;
	}
	
	
	public SchoolTerm getSchoolTerm() {
		return this.schoolTerm;
	}

	public void setSchoolTerm(SchoolTerm schoolTerm) {
		this.schoolTerm = schoolTerm;
	}

	/**
	 */
	public void setResearchProject(ResearchProject researchProject) {
		this.researchProject = researchProject;
	}

	/**
	 */
	@JsonIgnore
	public ResearchProject getResearchProject() {
		return researchProject;
	}
	/**
	 */
	public void setCAuditResult(CDictionary CDictionaryByCAuditResult) {
		this.CDictionaryByCAuditResult = CDictionaryByCAuditResult;
	}

	/**
	 */
	@JsonIgnore
	public CDictionary getCAuditResult() {
		return CDictionaryByCAuditResult;
	}

	/**
	 */
	public void setUserByReserveUser(User userByReserveUser) {
		this.userByReserveUser = userByReserveUser;
	}

	/**
	 */
	@JsonIgnore
	public User getUserByReserveUser() {
		return userByReserveUser;
	}

	/**
	 */
	public void setUserByTeacher(User userByTeacher) {
		this.userByTeacher = userByTeacher;
	}

	/**
	 */
	@JsonIgnore
	public User getUserByTeacher() {
		return userByTeacher;
	}

	/**
	 */
	public void setLabRoomDeviceReservationResults(Set<LabRoomDeviceReservationResult> labRoomDeviceReservationResults) {
		this.labRoomDeviceReservationResults = labRoomDeviceReservationResults;
	}

	/**
	 */
	@JsonIgnore
	public Set<LabRoomDeviceReservationResult> getLabRoomDeviceReservationResults() {
		if (labRoomDeviceReservationResults == null) {
			labRoomDeviceReservationResults = new java.util.LinkedHashSet<net.xidlims.domain.LabRoomDeviceReservationResult>();
		}
		return labRoomDeviceReservationResults;
	}

	/**
	 */
	public LabRoomDeviceReservation() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(LabRoomDeviceReservation that) {
		//setId(that.getId());
		setContent(that.getContent());
		setUseTime(that.getUseTime());
		setPhone(that.getPhone());
		setBegintime(that.getBegintime());
		setEndtime(that.getEndtime());
		setTime(that.getTime());
		setStage(that.getStage());
		setCReservationProperty(that.getCReservationProperty());
		//setLabRoomDevice(that.getLabRoomDevice());
		setSchoolTerm(that.getSchoolTerm());
		setInnerSame(that.getInnerSame());
		setCAuditResult(that.getCAuditResult());
		setUserByReserveUser(that.getUserByReserveUser());
		setUserByTeacher(that.getUserByTeacher());
		//setLabRoomDeviceReservationResults(new java.util.LinkedHashSet<net.xidlims.domain.LabRoomDeviceReservationResult>(that.getLabRoomDeviceReservationResults()));
		setTag(that.getTag());
		setOriginalBegin(that.getOriginalBegin());
		setOriginalEnd(that.getOriginalEnd());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("id=[").append(id).append("] ");
		buffer.append("content=[").append(content).append("] ");
		buffer.append("useTime=[").append(useTime).append("] ");
		buffer.append("phone=[").append(phone).append("] ");
		buffer.append("begintime=[").append(begintime).append("] ");
		buffer.append("endtime=[").append(endtime).append("] ");
		buffer.append("time=[").append(time).append("] ");
		buffer.append("stage=[").append(stage).append("] ");
		buffer.append("tag=[").append(tag).append("] ");
		buffer.append("originalBegin=[").append(originalBegin).append("] ");
		buffer.append("originalEnd=[").append(originalEnd).append("] ");

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
		if (!(obj instanceof LabRoomDeviceReservation))
			return false;
		LabRoomDeviceReservation equalCheck = (LabRoomDeviceReservation) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
