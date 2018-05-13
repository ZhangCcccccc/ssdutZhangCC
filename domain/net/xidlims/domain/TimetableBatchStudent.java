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
		@NamedQuery(name = "findAllTimetableBatchStudents", query = "select myTimetableBatchStudent from TimetableBatchStudent myTimetableBatchStudent"),
		@NamedQuery(name = "findTimetableBatchStudentById", query = "select myTimetableBatchStudent from TimetableBatchStudent myTimetableBatchStudent where myTimetableBatchStudent.id = ?1"),
		@NamedQuery(name = "findTimetableBatchStudentByPrimaryKey", query = "select myTimetableBatchStudent from TimetableBatchStudent myTimetableBatchStudent where myTimetableBatchStudent.id = ?1"),
		@NamedQuery(name = "findTimetableBatchStudentByWithdrawTimes", query = "select myTimetableBatchStudent from TimetableBatchStudent myTimetableBatchStudent where myTimetableBatchStudent.withdrawTimes = ?1") })
@Table(catalog = "xidlims", name = "timetable_batch_student")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "TimetableBatchStudent")
public class TimetableBatchStudent implements Serializable {
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
	 * ��ѡ����
	 * 
	 */

	@Column(name = "withdraw_times")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer withdrawTimes;

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
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "batch_id", referencedColumnName = "id") })
	@XmlTransient
	TimetableBatch timetableBatch;

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
	 * ��ѡ����
	 * 
	 */
	public void setWithdrawTimes(Integer withdrawTimes) {
		this.withdrawTimes = withdrawTimes;
	}

	/**
	 * ��ѡ����
	 * 
	 */
	public Integer getWithdrawTimes() {
		return this.withdrawTimes;
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
	public TimetableBatchStudent() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(TimetableBatchStudent that) {
		setId(that.getId());
		setWithdrawTimes(that.getWithdrawTimes());
		setTimetableGroup(that.getTimetableGroup());
		setUser(that.getUser());
		setTimetableBatch(that.getTimetableBatch());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("id=[").append(id).append("] ");
		buffer.append("withdrawTimes=[").append(withdrawTimes).append("] ");

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
		if (!(obj instanceof TimetableBatchStudent))
			return false;
		TimetableBatchStudent equalCheck = (TimetableBatchStudent) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
