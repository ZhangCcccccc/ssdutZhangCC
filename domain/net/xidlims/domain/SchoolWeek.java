package net.xidlims.domain;

import java.io.Serializable;

import java.lang.StringBuilder;

import java.util.Calendar;
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
		@NamedQuery(name = "findAllSchoolWeeks", query = "select mySchoolWeek from SchoolWeek mySchoolWeek"),
		@NamedQuery(name = "findSchoolWeekByCreatedAt", query = "select mySchoolWeek from SchoolWeek mySchoolWeek where mySchoolWeek.createdAt = ?1"),
		@NamedQuery(name = "findSchoolWeekByDate", query = "select mySchoolWeek from SchoolWeek mySchoolWeek where mySchoolWeek.date = ?1"),
		@NamedQuery(name = "findSchoolWeekByDateAfter", query = "select mySchoolWeek from SchoolWeek mySchoolWeek where mySchoolWeek.date > ?1"),
		@NamedQuery(name = "findSchoolWeekByDateBefore", query = "select mySchoolWeek from SchoolWeek mySchoolWeek where mySchoolWeek.date < ?1"),
		@NamedQuery(name = "findSchoolWeekById", query = "select mySchoolWeek from SchoolWeek mySchoolWeek where mySchoolWeek.id = ?1"),
		@NamedQuery(name = "findSchoolWeekByPrimaryKey", query = "select mySchoolWeek from SchoolWeek mySchoolWeek where mySchoolWeek.id = ?1"),
		@NamedQuery(name = "findSchoolWeekByUpdatedAt", query = "select mySchoolWeek from SchoolWeek mySchoolWeek where mySchoolWeek.updatedAt = ?1"),
		@NamedQuery(name = "findSchoolWeekByWeek", query = "select mySchoolWeek from SchoolWeek mySchoolWeek where mySchoolWeek.week = ?1"),
		@NamedQuery(name = "findSchoolWeekByWeekday", query = "select mySchoolWeek from SchoolWeek mySchoolWeek where mySchoolWeek.weekday = ?1") })
@Table(catalog = "xidlims", name = "school_week")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "SchoolWeek")
public class SchoolWeek implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * �ܻ��
	 * 
	 */

	@Column(name = "id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@XmlElement
	Integer id;
	/**
	 * �ڼ���
	 * 
	 */

	@Column(name = "week")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	@GeneratedValue(strategy = GenerationType.AUTO)
	Integer week;
	/**
	 * ���ڼ�
	 * 
	 */

	@Column(name = "weekday")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer weekday;
	/**
	 * ʱ��
	 * 
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "date")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar date;
	/**
	 * ����ʱ��
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar createdAt;
	/**
	 * ����ʱ��
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar updatedAt;
	
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "term_id", referencedColumnName = "id") })
	@XmlTransient
	SchoolTerm schoolTerm;
	
	
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "is_occupied", referencedColumnName = "c_teaching_date_type") })
	@XmlTransient
	CDictionary CDictionary;


	/**
	 * �ܻ��
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * �ܻ��
	 * 
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * �ڼ���
	 * 
	 */
	public void setWeek(Integer week) {
		this.week = week;
	}

	/**
	 * �ڼ���
	 * 
	 */
	public Integer getWeek() {
		return this.week;
	}

	/**
	 * ���ڼ�
	 * 
	 */
	public void setWeekday(Integer weekday) {
		this.weekday = weekday;
	}

	/**
	 * ���ڼ�
	 * 
	 */
	public Integer getWeekday() {
		return this.weekday;
	}

	/**
	 * ʱ��
	 * 
	 */
	public void setDate(Calendar date) {
		this.date = date;
	}

	/**
	 * ʱ��
	 * 
	 */
	public Calendar getDate() {
		return this.date;
	}

	/**
	 * ����ʱ��
	 * 
	 */
	public void setCreatedAt(Calendar createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * ����ʱ��
	 * 
	 */
	public Calendar getCreatedAt() {
		return this.createdAt;
	}

	/**
	 * ����ʱ��
	 * 
	 */
	public void setUpdatedAt(Calendar updatedAt) {
		this.updatedAt = updatedAt;
	}

	/**
	 * ����ʱ��
	 * 
	 */
	public Calendar getUpdatedAt() {
		return this.updatedAt;
	}

	/**
	 */
	public void setSchoolTerm(SchoolTerm schoolTerm) {
		this.schoolTerm = schoolTerm;
	}

	/**
	 */
	@JsonIgnore
	public SchoolTerm getSchoolTerm() {
		return schoolTerm;
	}
	
	/**
	 */
	public void setCDictionary(CDictionary CDictionary) {
		this.CDictionary = CDictionary;
	}

	/**
	 */
	@JsonIgnore
	public CDictionary getCDictionary() {
		return CDictionary;
	}

	/**
	 */
	public SchoolWeek() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(SchoolWeek that) {
		setId(that.getId());
		setWeek(that.getWeek());
		setWeekday(that.getWeekday());
		setDate(that.getDate());
		setCreatedAt(that.getCreatedAt());
		setUpdatedAt(that.getUpdatedAt());
		setSchoolTerm(that.getSchoolTerm());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("id=[").append(id).append("] ");
		buffer.append("week=[").append(week).append("] ");
		buffer.append("weekday=[").append(weekday).append("] ");
		buffer.append("date=[").append(date).append("] ");
		buffer.append("createdAt=[").append(createdAt).append("] ");
		buffer.append("updatedAt=[").append(updatedAt).append("] ");

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
		if (!(obj instanceof SchoolWeek))
			return false;
		SchoolWeek equalCheck = (SchoolWeek) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
