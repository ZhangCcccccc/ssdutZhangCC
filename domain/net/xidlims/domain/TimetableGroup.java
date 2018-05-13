package net.xidlims.domain;

import java.io.Serializable;
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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
		@NamedQuery(name = "findAllTimetableGroups", query = "select myTimetableGroup from TimetableGroup myTimetableGroup"),
		@NamedQuery(name = "findTimetableGroupByEndDate", query = "select myTimetableGroup from TimetableGroup myTimetableGroup where myTimetableGroup.endDate = ?1"),
		@NamedQuery(name = "findTimetableGroupByGroupName", query = "select myTimetableGroup from TimetableGroup myTimetableGroup where myTimetableGroup.groupName = ?1"),
		@NamedQuery(name = "findTimetableGroupByGroupNameContaining", query = "select myTimetableGroup from TimetableGroup myTimetableGroup where myTimetableGroup.groupName like ?1"),
		@NamedQuery(name = "findTimetableGroupById", query = "select myTimetableGroup from TimetableGroup myTimetableGroup where myTimetableGroup.id = ?1"),
		@NamedQuery(name = "findTimetableGroupByIfselect", query = "select myTimetableGroup from TimetableGroup myTimetableGroup where myTimetableGroup.ifselect = ?1"),
		@NamedQuery(name = "findTimetableGroupByNumbers", query = "select myTimetableGroup from TimetableGroup myTimetableGroup where myTimetableGroup.numbers = ?1"),
		@NamedQuery(name = "findTimetableGroupByPrimaryKey", query = "select myTimetableGroup from TimetableGroup myTimetableGroup where myTimetableGroup.id = ?1"),
		@NamedQuery(name = "findTimetableGroupByStartDate", query = "select myTimetableGroup from TimetableGroup myTimetableGroup where myTimetableGroup.startDate = ?1") })
@Table(catalog = "xidlims", name = "timetable_group")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "TimetableGroup")
@XmlRootElement(namespace = "xidlims/net/xidlims/domain")
public class TimetableGroup implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * �ſη����
	 * 
	 */

	@Column(name = "id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	@XmlElement
	Integer id;
	/**
	 * �������ƣ�Ĭ��Ϊ��1���...
	 * 
	 */

	@Column(name = "group_name")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String groupName;
	/**
	 * �Ƿ�ѧ��ѡ��0�Զ�ѡ��1ѧ��ѡ��
	 * 
	 */

	@Column(name = "ifselect")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer ifselect;
	/**
	 * ѡ�ο�ʼ����
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "start_date")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar startDate;
	/**
	 * ѡ�ο�ʼ����
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "end_date")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar endDate;
	

	
	/**
	 * �Ƿ�ѧ��ѡ��0�Զ�ѡ��1ѧ��ѡ��
	 * 
	 */

	@Column(name = "timetable_style")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer timetableStyle;
	

	/**
	 * ѡ�ο�ʼ����
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "actual_start_date")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar actualStartDate;
	
	/**
	 * ѡ�ο�ʼ����
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "actual_end_date")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar actualEndDate;
	

	public Calendar getActualStartDate() {
		return actualStartDate;
	}

	public void setActualStartDate(Calendar actualStartDate) {
		this.actualStartDate = actualStartDate;
	}
	
	/**
	 * @return the actualEndDate
	 */
	public Calendar getActualEndDate() {
		return actualEndDate;
	}

	/**
	 * @param actualEndDate the actualEndDate to set
	 */
	public void setActualEndDate(Calendar actualEndDate) {
		this.actualEndDate = actualEndDate;
	}

	public Integer getTimetableStyle() {
		return timetableStyle;
	}

	public void setTimetableStyle(Integer timetableStyle) {
		this.timetableStyle = timetableStyle;
	}

	/**
	 * ÿ������
	 * 
	 */

	@Column(name = "numbers")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer numbers;

	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "batch_id", referencedColumnName = "id") })
	@XmlTransient
	TimetableBatch timetableBatch;
	
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "lab_id", referencedColumnName = "id") })
	@XmlTransient
	LabRoom labRoom;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "appointment_id", referencedColumnName = "id") })
	@XmlTransient
	TimetableAppointment timetableAppointment;
	/**
	 */
	@OneToMany(mappedBy = "timetableGroup", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.TimetableGroupStudents> timetableGroupStudentses;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(catalog = "xidlims", name = "timetable_appointment_group", joinColumns = { @JoinColumn(name = "group_id", referencedColumnName = "id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "appointment_id", referencedColumnName = "id", nullable = false, updatable = false) })
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.TimetableAppointment> timetableAppointments;
	
	/**
	 */
	@OneToMany(mappedBy = "timetableGroup", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.TimetableBatchStudent> timetableBatchStudents;

	
	/**
	 */
	public void setTimetableBatchStudents(Set<TimetableBatchStudent> timetableBatchStudents) {
		this.timetableBatchStudents = timetableBatchStudents;
	}

	/**
	 */
	@JsonIgnore
	public Set<TimetableBatchStudent> getTimetableBatchStudents() {
		if (timetableBatchStudents == null) {
			timetableBatchStudents = new java.util.LinkedHashSet<net.xidlims.domain.TimetableBatchStudent>();
		}
		return timetableBatchStudents;
	}
	
	/**
	 * �ſη����
	 * 
	 */
	public java.util.Set<net.xidlims.domain.TimetableAppointment> getTimetableAppointments() {
		return this.timetableAppointments;
	}

	public void setTimetableAppointments(
			java.util.Set<net.xidlims.domain.TimetableAppointment> timetableAppointments) {
		if (timetableAppointments == null) {
			timetableAppointments = new java.util.LinkedHashSet<net.xidlims.domain.TimetableAppointment>();
		}
		this.timetableAppointments = timetableAppointments;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * �ſη����
	 * 
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * ������ƣ�Ĭ��Ϊ��1���...
	 * 
	 */
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	/**
	 * ������ƣ�Ĭ��Ϊ��1���...
	 * 
	 */
	public String getGroupName() {
		return this.groupName;
	}

	/**
	 * �Ƿ�ѧ��ѡ��0�Զ�ѡ��1ѧ��ѡ��
	 * 
	 */
	public void setIfselect(Integer ifselect) {
		this.ifselect = ifselect;
	}

	/**
	 * �Ƿ�ѧ��ѡ��0�Զ�ѡ��1ѧ��ѡ��
	 * 
	 */
	public Integer getIfselect() {
		return this.ifselect;
	}

	/**
	 * ѡ�ο�ʼ����
	 * 
	 */
	public void setStartDate(Calendar startDate) {
		this.startDate = startDate;
	}

	/**
	 * ѡ�ο�ʼ����
	 * 
	 */
	public Calendar getStartDate() {
		return this.startDate;
	}

	/**
	 * ѡ�ο�ʼ����
	 * 
	 */
	public void setEndDate(Calendar endDate) {
		this.endDate = endDate;
	}

	/**
	 * ѡ�ο�ʼ����
	 * 
	 */
	public Calendar getEndDate() {
		return this.endDate;
	}

	/**
	 * ÿ������
	 * 
	 */
	public void setNumbers(Integer numbers) {
		this.numbers = numbers;
	}

	/**
	 * ÿ������
	 * 
	 */
	public Integer getNumbers() {
		return this.numbers;
	}

	/**
	 */
	public void setTimetableBatch(TimetableBatch timetableBatch) {
		this.timetableBatch = timetableBatch;
	}

	/**
	 */
	@JsonIgnore
	public TimetableBatch getTimetableBatch() {
		return timetableBatch;
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
	public void setTimetableGroupStudentses(Set<TimetableGroupStudents> timetableGroupStudentses) {
		this.timetableGroupStudentses = timetableGroupStudentses;
	}

	/**
	 */
	@JsonIgnore
	public Set<TimetableGroupStudents> getTimetableGroupStudentses() {
		if (timetableGroupStudentses == null) {
			timetableGroupStudentses = new java.util.LinkedHashSet<net.xidlims.domain.TimetableGroupStudents>();
		}
		return timetableGroupStudentses;
	}

	/**
	 */
	public TimetableGroup() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(TimetableGroup that) {
		// setId(that.getId());
		setGroupName(that.getGroupName());
		// setIfselect(that.getIfselect());
		setStartDate(that.getStartDate());
		setEndDate(that.getEndDate());
		setActualStartDate(that.getActualStartDate());
		setActualEndDate(that.getActualEndDate());
		setNumbers(that.getNumbers());
		setTimetableStyle(that.getTimetableStyle());
		setLabRoom(that.getLabRoom());
		// setTimetableBatch(that.getTimetableBatch());
		// setTimetableAppointment(that.getTimetableAppointment());
		// setTimetableGroupStudentses(new java.util.LinkedHashSet<net.xidlims.domain.TimetableGroupStudents>(that.getTimetableGroupStudentses()));
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("id=[").append(id).append("] ");
		buffer.append("groupName=[").append(groupName).append("] ");
		buffer.append("ifselect=[").append(ifselect).append("] ");
		buffer.append("startDate=[").append(startDate).append("] ");
		buffer.append("endDate=[").append(endDate).append("] ");
		buffer.append("numbers=[").append(numbers).append("] ");

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
		if (!(obj instanceof TimetableGroup))
			return false;
		TimetableGroup equalCheck = (TimetableGroup) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
