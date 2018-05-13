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
		@NamedQuery(name = "findAllLabReservationTimeTableStudents", query = "select myLabReservationTimeTableStudent from LabReservationTimeTableStudent myLabReservationTimeTableStudent"),
		@NamedQuery(name = "findLabReservationTimeTableStudentById", query = "select myLabReservationTimeTableStudent from LabReservationTimeTableStudent myLabReservationTimeTableStudent where myLabReservationTimeTableStudent.id = ?1"),
		@NamedQuery(name = "findLabReservationTimeTableStudentByPrimaryKey", query = "select myLabReservationTimeTableStudent from LabReservationTimeTableStudent myLabReservationTimeTableStudent where myLabReservationTimeTableStudent.id = ?1") })
@Table(catalog = "xidlims", name = "lab_reservation_time_table_student")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "LabReservationTimeTableStudent")
public class LabReservationTimeTableStudent implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ʵ����ԤԼѧ���
	 * 
	 */

	@Column(name = "id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	@GeneratedValue(strategy = GenerationType.AUTO)
	Integer id;

	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "student", referencedColumnName = "username") })
	@XmlTransient
	User user;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "lab_reservation_id", referencedColumnName = "id") })
	@XmlTransient
	LabReservation labReservation;

	/**
	 * ʵ����ԤԼѧ���
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * ʵ����ԤԼѧ���
	 * 
	 */
	public Integer getId() {
		return this.id;
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
	public void setLabReservation(LabReservation labReservation) {
		this.labReservation = labReservation;
	}

	/**
	 */
	@JsonIgnore
	public LabReservation getLabReservation() {
		return labReservation;
	}

	/**
	 */
	public LabReservationTimeTableStudent() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(LabReservationTimeTableStudent that) {
		setId(that.getId());
		setUser(that.getUser());
		setLabReservation(that.getLabReservation());
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
		if (!(obj instanceof LabReservationTimeTableStudent))
			return false;
		LabReservationTimeTableStudent equalCheck = (LabReservationTimeTableStudent) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
