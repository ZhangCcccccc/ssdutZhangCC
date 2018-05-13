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
		@NamedQuery(name = "findAllTimetableBatchItemss", query = "select myTimetableBatchItems from TimetableBatchItems myTimetableBatchItems"),
		@NamedQuery(name = "findTimetableBatchItemsById", query = "select myTimetableBatchItems from TimetableBatchItems myTimetableBatchItems where myTimetableBatchItems.id = ?1"),
		@NamedQuery(name = "findTimetableBatchItemsByPrimaryKey", query = "select myTimetableBatchItems from TimetableBatchItems myTimetableBatchItems where myTimetableBatchItems.id = ?1") })
@Table(catalog = "xidlims", name = "timetable_batch_items")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "TimetableBatchItems")
public class TimetableBatchItems implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ����󶨵�ʵ����Ŀ��
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
	@JoinColumns({ @JoinColumn(name = "batch_id", referencedColumnName = "id") })
	@XmlTransient
	TimetableBatch timetableBatch;

	/**
	 * ����󶨵�ʵ����Ŀ��
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * ����󶨵�ʵ����Ŀ��
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
	public TimetableBatchItems() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(TimetableBatchItems that) {
		setId(that.getId());
		setOperationItem(that.getOperationItem());
		setTimetableBatch(that.getTimetableBatch());
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
		if (!(obj instanceof TimetableBatchItems))
			return false;
		TimetableBatchItems equalCheck = (TimetableBatchItems) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
