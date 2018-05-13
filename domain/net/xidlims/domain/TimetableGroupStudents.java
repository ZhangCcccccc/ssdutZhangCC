package net.xidlims.domain;

import java.io.Serializable;

import java.lang.StringBuilder;



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
		@NamedQuery(name = "findAllTimetableGroupStudentss", query = "select myTimetableGroupStudents from TimetableGroupStudents myTimetableGroupStudents"),
		@NamedQuery(name = "findTimetableGroupStudentsById", query = "select myTimetableGroupStudents from TimetableGroupStudents myTimetableGroupStudents where myTimetableGroupStudents.id = ?1"),
		@NamedQuery(name = "findTimetableGroupStudentsByPrimaryKey", query = "select myTimetableGroupStudents from TimetableGroupStudents myTimetableGroupStudents where myTimetableGroupStudents.id = ?1") })
@Table(catalog = "xidlims", name = "timetable_group_students")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "TimetableGroupStudents")
public class TimetableGroupStudents implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ѧ��ѡ��
	 * 
	 */

	@Column(name = "id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@XmlElement
	Integer id;

	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "group_id", referencedColumnName = "id") })
	@XmlTransient
	TimetableGroup timetableGroup;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "username", referencedColumnName = "username") })
	@XmlTransient
	User user;

	/**
	 * ѧ��ѡ��
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * ѧ��ѡ��
	 * 
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 */
	public void setTimetableGroup(TimetableGroup timetableGroup) {
		this.timetableGroup = timetableGroup;
	}

	/**
	 */
	@JsonIgnore
	public TimetableGroup getTimetableGroup() {
		return timetableGroup;
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
	public TimetableGroupStudents() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(TimetableGroupStudents that) {
		setTimetableGroup(that.getTimetableGroup());
		setUser(that.getUser());
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
		if (!(obj instanceof TimetableGroupStudents))
			return false;
		TimetableGroupStudents equalCheck = (TimetableGroupStudents) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
