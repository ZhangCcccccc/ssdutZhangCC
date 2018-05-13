package net.xidlims.domain;

import java.io.Serializable;

import java.lang.StringBuilder;
import java.util.Set;



import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import net.xidlims.domain.TimetableLabRelatedDevice;

import org.codehaus.jackson.annotate.JsonIgnore;

import javax.xml.bind.annotation.*;

import javax.persistence.*;

/**
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "findAllTimetableLabRelateds", query = "select myTimetableLabRelated from TimetableLabRelated myTimetableLabRelated"),
		@NamedQuery(name = "findTimetableLabRelatedById", query = "select myTimetableLabRelated from TimetableLabRelated myTimetableLabRelated where myTimetableLabRelated.id = ?1"),
		@NamedQuery(name = "findTimetableLabRelatedByPrimaryKey", query = "select myTimetableLabRelated from TimetableLabRelated myTimetableLabRelated where myTimetableLabRelated.id = ?1") })
@Table(catalog = "xidlims", name = "timetable_lab_related")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "TimetableLabRelated")
public class TimetableLabRelated implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * �ſΡ�ԤԼ֮��������ı�
	 * 
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
	@OneToMany(mappedBy = "timetableLabRelated", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.TimetableLabRelatedDevice> timetableLabRelatedDevices;
	/**
	 * �ſΡ�ԤԼ֮��������ı�
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * �ſΡ�ԤԼ֮��������ı�
	 * 
	 */
	public Integer getId() {
		return this.id;
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
	@JsonIgnore
	public TimetableAppointment getTimetableAppointment() {
		return timetableAppointment;
	}

	/**
	 */
	public TimetableLabRelated() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(TimetableLabRelated that) {
		setId(that.getId());
		setLabRoom(that.getLabRoom());
		setTimetableAppointment(that.getTimetableAppointment());
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
		if (!(obj instanceof TimetableLabRelated))
			return false;
		TimetableLabRelated equalCheck = (TimetableLabRelated) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
