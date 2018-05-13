package net.xidlims.domain;

import java.io.Serializable;

import java.lang.StringBuilder;

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
		@NamedQuery(name = "findAllLabRoomCourseCapacitys", query = "select myLabRoomCourseCapacity from LabRoomCourseCapacity myLabRoomCourseCapacity"),
		@NamedQuery(name = "findLabRoomCourseCapacityByCapacity", query = "select myLabRoomCourseCapacity from LabRoomCourseCapacity myLabRoomCourseCapacity where myLabRoomCourseCapacity.capacity = ?1"),
		@NamedQuery(name = "findLabRoomCourseCapacityById", query = "select myLabRoomCourseCapacity from LabRoomCourseCapacity myLabRoomCourseCapacity where myLabRoomCourseCapacity.id = ?1"),
		@NamedQuery(name = "findLabRoomCourseCapacityByPrimaryKey", query = "select myLabRoomCourseCapacity from LabRoomCourseCapacity myLabRoomCourseCapacity where myLabRoomCourseCapacity.id = ?1") })
@Table(catalog = "xidlims", name = "lab_room_course_capacity")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "LabRoomCourseCapacity")
public class LabRoomCourseCapacity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 */

	@Column(name = "id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@XmlElement
	Integer id;
	/**
	 */

	@Column(name = "capacity")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer capacity;

	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "lab_id", referencedColumnName = "id") })
	@XmlTransient
	LabRoom labRoom;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "course_detail_no", referencedColumnName = "course_detail_no") })
	@XmlTransient
	SchoolCourseDetail schoolCourseDetail;

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
	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	/**
	 */
	public Integer getCapacity() {
		return this.capacity;
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
	public void setSchoolCourseDetail(SchoolCourseDetail schoolCourseDetail) {
		this.schoolCourseDetail = schoolCourseDetail;
	}

	/**
	 */
	@JsonIgnore
	public SchoolCourseDetail getSchoolCourseDetail() {
		return schoolCourseDetail;
	}

	/**
	 */
	public LabRoomCourseCapacity() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(LabRoomCourseCapacity that) {
		setId(that.getId());
		setCapacity(that.getCapacity());
		setLabRoom(that.getLabRoom());
		setSchoolCourseDetail(that.getSchoolCourseDetail());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("id=[").append(id).append("] ");
		buffer.append("capacity=[").append(capacity).append("] ");

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
		if (!(obj instanceof LabRoomCourseCapacity))
			return false;
		LabRoomCourseCapacity equalCheck = (LabRoomCourseCapacity) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
