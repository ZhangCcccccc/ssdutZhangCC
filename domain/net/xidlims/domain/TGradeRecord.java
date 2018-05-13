package net.xidlims.domain;

import java.io.Serializable;

import java.lang.StringBuilder;

import java.math.BigDecimal;

import java.util.Calendar;


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
		@NamedQuery(name = "findAllTGradeRecords", query = "select myTGradeRecord from TGradeRecord myTGradeRecord"),
		@NamedQuery(name = "findTGradeRecordById", query = "select myTGradeRecord from TGradeRecord myTGradeRecord where myTGradeRecord.id = ?1"),
		@NamedQuery(name = "findTGradeRecordByPoints", query = "select myTGradeRecord from TGradeRecord myTGradeRecord where myTGradeRecord.points = ?1"),
		@NamedQuery(name = "findTGradeRecordByPrimaryKey", query = "select myTGradeRecord from TGradeRecord myTGradeRecord where myTGradeRecord.id = ?1"),
		@NamedQuery(name = "findTGradeRecordByRecordTime", query = "select myTGradeRecord from TGradeRecord myTGradeRecord where myTGradeRecord.recordTime = ?1") })
@Table(catalog = "xidlims", name = "t_grade_record")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "TGradeRecord")
public class TGradeRecord implements Serializable {
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
	 * ѧ��ɼ�
	 * 
	 */

	@Column(name = "points", scale = 1, precision = 11, nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal points;
	/**
	 * ��¼ʱ��
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "record_time", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar recordTime;

	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "object_id", referencedColumnName = "id", nullable = false) })
	@XmlTransient
	TGradeObject TGradeObject;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "student_number", referencedColumnName = "username", nullable = false) })
	@XmlTransient
	User user;

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
	 * ѧ��ɼ�
	 * 
	 */
	public void setPoints(BigDecimal points) {
		this.points = points;
	}

	/**
	 * ѧ��ɼ�
	 * 
	 */
	public BigDecimal getPoints() {
		return this.points;
	}

	/**
	 * ��¼ʱ��
	 * 
	 */
	public void setRecordTime(Calendar recordTime) {
		this.recordTime = recordTime;
	}

	/**
	 * ��¼ʱ��
	 * 
	 */
	public Calendar getRecordTime() {
		return this.recordTime;
	}

	/**
	 */
	public void setTGradeObject(TGradeObject TGradeObject) {
		this.TGradeObject = TGradeObject;
	}

	/**
	 */
	public TGradeObject getTGradeObject() {
		return TGradeObject;
	}

	/**
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 */
	public User getUser() {
		return user;
	}

	/**
	 */
	public TGradeRecord() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(TGradeRecord that) {
		setId(that.getId());
		setPoints(that.getPoints());
		setRecordTime(that.getRecordTime());
		setTGradeObject(that.getTGradeObject());
		setUser(that.getUser());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("id=[").append(id).append("] ");
		buffer.append("points=[").append(points).append("] ");
		buffer.append("recordTime=[").append(recordTime).append("] ");

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
		if (!(obj instanceof TGradeRecord))
			return false;
		TGradeRecord equalCheck = (TGradeRecord) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
