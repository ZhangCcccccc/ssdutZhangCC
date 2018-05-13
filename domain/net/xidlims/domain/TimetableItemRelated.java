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
		@NamedQuery(name = "findAllTimetableItemRelateds", query = "select myTimetableItemRelated from TimetableItemRelated myTimetableItemRelated"),
		@NamedQuery(name = "findTimetableItemRelatedById", query = "select myTimetableItemRelated from TimetableItemRelated myTimetableItemRelated where myTimetableItemRelated.id = ?1"),
		@NamedQuery(name = "findTimetableItemRelatedByPrimaryKey", query = "select myTimetableItemRelated from TimetableItemRelated myTimetableItemRelated where myTimetableItemRelated.id = ?1") })
@Table(catalog = "xidlims", name = "timetable_item_related")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "TimetableItemRelated")
public class TimetableItemRelated implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * �ſΡ�ԤԼ֮���������ʵ����Ŀ��
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
	@JoinColumns({ @JoinColumn(name = "item_id", referencedColumnName = "id") })
	@XmlTransient
	OperationItem operationItem;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "appointment_id", referencedColumnName = "id") })
	@XmlTransient
	TimetableAppointment timetableAppointment;

	/**
	 * �ſΡ�ԤԼ֮���������ʵ����Ŀ��
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * �ſΡ�ԤԼ֮���������ʵ����Ŀ��
	 * 
	 */
	public Integer getId() {
		return this.id;
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
	public TimetableItemRelated() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(TimetableItemRelated that) {
		setId(that.getId());
		setOperationItem(that.getOperationItem());
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
		if (!(obj instanceof TimetableItemRelated))
			return false;
		TimetableItemRelated equalCheck = (TimetableItemRelated) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
