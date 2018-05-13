package net.xidlims.domain;

import java.io.Serializable;

import java.lang.StringBuilder;

import java.util.Set;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import javax.xml.bind.annotation.*;

import javax.persistence.*;

/**
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "findAllTimetableLabRelatedDevices", query = "select myTimetableLabRelatedDevice from TimetableLabRelatedDevice myTimetableLabRelatedDevice"),
		@NamedQuery(name = "findTimetableLabRelatedDeviceById", query = "select myTimetableLabRelatedDevice from TimetableLabRelatedDevice myTimetableLabRelatedDevice where myTimetableLabRelatedDevice.id = ?1"),
		@NamedQuery(name = "findTimetableLabRelatedDeviceByPrimaryKey", query = "select myTimetableLabRelatedDevice from TimetableLabRelatedDevice myTimetableLabRelatedDevice where myTimetableLabRelatedDevice.id = ?1") })
@Table(catalog = "xidlims", name = "timetable_lab_related_device")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "TimetableLabRelatedDevice")
public class TimetableLabRelatedDevice implements Serializable {
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
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "lab_room_device", referencedColumnName = "id") })
	@XmlTransient
	LabRoomDevice labRoomDevice;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "lab_related_id", referencedColumnName = "id", nullable = false) })
	@XmlTransient
	TimetableLabRelated timetableLabRelated;

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
	public void setLabRoomDevice(LabRoomDevice labRoomDevice) {
		this.labRoomDevice = labRoomDevice;
	}

	/**
	 */
	public LabRoomDevice getLabRoomDevice() {
		return labRoomDevice;
	}

	/**
	 */
	public void setTimetableLabRelated(TimetableLabRelated timetableLabRelated) {
		this.timetableLabRelated = timetableLabRelated;
	}

	/**
	 */
	public TimetableLabRelated getTimetableLabRelated() {
		return timetableLabRelated;
	}

	/**
	 */
	public TimetableLabRelatedDevice() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(TimetableLabRelatedDevice that) {
		setId(that.getId());
		setLabRoomDevice(that.getLabRoomDevice());
		setTimetableLabRelated(that.getTimetableLabRelated());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("id=[").append(id).append("] ");

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
		if (!(obj instanceof TimetableLabRelatedDevice))
			return false;
		TimetableLabRelatedDevice equalCheck = (TimetableLabRelatedDevice) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
