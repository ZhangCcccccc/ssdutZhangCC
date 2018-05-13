package net.xidlims.domain;

import java.io.Serializable;

import java.lang.StringBuilder;

import java.util.Calendar;

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
		@NamedQuery(name = "findAllLabRoomLogs", query = "select myLabRoomLog from LabRoomLog myLabRoomLog"),
		@NamedQuery(name = "findLabRoomLogByAction", query = "select myLabRoomLog from LabRoomLog myLabRoomLog where myLabRoomLog.action = ?1"),
		@NamedQuery(name = "findLabRoomLogByActionContaining", query = "select myLabRoomLog from LabRoomLog myLabRoomLog where myLabRoomLog.action like ?1"),
		@NamedQuery(name = "findLabRoomLogByCreateTime", query = "select myLabRoomLog from LabRoomLog myLabRoomLog where myLabRoomLog.createTime = ?1"),
		@NamedQuery(name = "findLabRoomLogByDate", query = "select myLabRoomLog from LabRoomLog myLabRoomLog where myLabRoomLog.date = ?1"),
		@NamedQuery(name = "findLabRoomLogByDateAfter", query = "select myLabRoomLog from LabRoomLog myLabRoomLog where myLabRoomLog.date > ?1"),
		@NamedQuery(name = "findLabRoomLogByDateBefore", query = "select myLabRoomLog from LabRoomLog myLabRoomLog where myLabRoomLog.date < ?1"),
		@NamedQuery(name = "findLabRoomLogByDayS", query = "select myLabRoomLog from LabRoomLog myLabRoomLog where myLabRoomLog.dayS = ?1"),
		@NamedQuery(name = "findLabRoomLogById", query = "select myLabRoomLog from LabRoomLog myLabRoomLog where myLabRoomLog.id = ?1"),
		@NamedQuery(name = "findLabRoomLogByModule", query = "select myLabRoomLog from LabRoomLog myLabRoomLog where myLabRoomLog.module = ?1"),
		@NamedQuery(name = "findLabRoomLogByModuleContaining", query = "select myLabRoomLog from LabRoomLog myLabRoomLog where myLabRoomLog.module like ?1"),
		@NamedQuery(name = "findLabRoomLogByPrimaryKey", query = "select myLabRoomLog from LabRoomLog myLabRoomLog where myLabRoomLog.id = ?1"),
		@NamedQuery(name = "findLabRoomLogBySchoolCourse", query = "select myLabRoomLog from LabRoomLog myLabRoomLog where myLabRoomLog.schoolCourse = ?1"),
		@NamedQuery(name = "findLabRoomLogBySectionS", query = "select myLabRoomLog from LabRoomLog myLabRoomLog where myLabRoomLog.sectionS = ?1"),
		@NamedQuery(name = "findLabRoomLogBySectionSContaining", query = "select myLabRoomLog from LabRoomLog myLabRoomLog where myLabRoomLog.sectionS like ?1"),
		@NamedQuery(name = "findLabRoomLogByStudentNo", query = "select myLabRoomLog from LabRoomLog myLabRoomLog where myLabRoomLog.studentNo = ?1"),
		@NamedQuery(name = "findLabRoomLogByStudentNoContaining", query = "select myLabRoomLog from LabRoomLog myLabRoomLog where myLabRoomLog.studentNo like ?1"),
		@NamedQuery(name = "findLabRoomLogByWeekS", query = "select myLabRoomLog from LabRoomLog myLabRoomLog where myLabRoomLog.weekS = ?1") })
@Table(catalog = "xidlims", name = "lab_room_log")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "LabRoomLog")
public class LabRoomLog implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 */

	@Column(name = "id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	@XmlElement
	Integer id;
	/**
	 */

	@Column(name = "action")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String action;
	/**
	 */

	@Column(name = "module")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String module;
	/**
	 * ����
	 * 
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "date")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar date;
	/**
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar createTime;
	/**
	 * �γ����
	 * 
	 */

	@Column(name = "school_course")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer schoolCourse;
	/**
	 * �ܴ�
	 * 
	 */

	@Column(name = "week_s")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer weekS;
	/**
	 * ����
	 * 
	 */

	@Column(name = "day_s")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer dayS;
	/**
	 * �ڴ�
	 * 
	 */

	@Column(name = "section_s")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String sectionS;
	/**
	 * ѧ����
	 * 
	 */

	@Column(name = "student_no")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String studentNo;

	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "lab_room", referencedColumnName = "id") })
	@XmlTransient
	LabRoom labRoom;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "operation_item", referencedColumnName = "id") })
	@XmlTransient
	OperationItem operationItem;

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
	 */
	public void setAction(String action) {
		this.action = action;
	}

	/**
	 */
	public String getAction() {
		return this.action;
	}

	/**
	 */
	public void setModule(String module) {
		this.module = module;
	}

	/**
	 */
	public String getModule() {
		return this.module;
	}

	/**
	 * ����
	 * 
	 */
	public void setDate(Calendar date) {
		this.date = date;
	}

	/**
	 * ����
	 * 
	 */
	public Calendar getDate() {
		return this.date;
	}

	/**
	 */
	public void setCreateTime(Calendar createTime) {
		this.createTime = createTime;
	}

	/**
	 */
	public Calendar getCreateTime() {
		return this.createTime;
	}

	/**
	 * �γ����
	 * 
	 */
	public void setSchoolCourse(Integer schoolCourse) {
		this.schoolCourse = schoolCourse;
	}

	/**
	 * �γ����
	 * 
	 */
	public Integer getSchoolCourse() {
		return this.schoolCourse;
	}

	/**
	 * �ܴ�
	 * 
	 */
	public void setWeekS(Integer weekS) {
		this.weekS = weekS;
	}

	/**
	 * �ܴ�
	 * 
	 */
	public Integer getWeekS() {
		return this.weekS;
	}

	/**
	 * ����
	 * 
	 */
	public void setDayS(Integer dayS) {
		this.dayS = dayS;
	}

	/**
	 * ����
	 * 
	 */
	public Integer getDayS() {
		return this.dayS;
	}

	/**
	 * �ڴ�
	 * 
	 */
	public void setSectionS(String sectionS) {
		this.sectionS = sectionS;
	}

	/**
	 * �ڴ�
	 * 
	 */
	public String getSectionS() {
		return this.sectionS;
	}

	/**
	 * ѧ����
	 * 
	 */
	public void setStudentNo(String studentNo) {
		this.studentNo = studentNo;
	}

	/**
	 * ѧ����
	 * 
	 */
	public String getStudentNo() {
		return this.studentNo;
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
	 */
	public LabRoomLog() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(LabRoomLog that) {
		setId(that.getId());
		setAction(that.getAction());
		setModule(that.getModule());
		setDate(that.getDate());
		setCreateTime(that.getCreateTime());
		setSchoolCourse(that.getSchoolCourse());
		setWeekS(that.getWeekS());
		setDayS(that.getDayS());
		setSectionS(that.getSectionS());
		setStudentNo(that.getStudentNo());
		setLabRoom(that.getLabRoom());
		setOperationItem(that.getOperationItem());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("id=[").append(id).append("] ");
		buffer.append("action=[").append(action).append("] ");
		buffer.append("module=[").append(module).append("] ");
		buffer.append("date=[").append(date).append("] ");
		buffer.append("createTime=[").append(createTime).append("] ");
		buffer.append("schoolCourse=[").append(schoolCourse).append("] ");
		buffer.append("weekS=[").append(weekS).append("] ");
		buffer.append("dayS=[").append(dayS).append("] ");
		buffer.append("sectionS=[").append(sectionS).append("] ");
		buffer.append("studentNo=[").append(studentNo).append("] ");

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
		if (!(obj instanceof LabRoomLog))
			return false;
		LabRoomLog equalCheck = (LabRoomLog) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
