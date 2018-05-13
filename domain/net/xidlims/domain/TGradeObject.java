package net.xidlims.domain;

import java.io.Serializable;

import java.lang.StringBuilder;

import java.math.BigDecimal;

import java.util.Calendar;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import javax.xml.bind.annotation.*;

import javax.persistence.*;

/**
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "findAllTGradeObjects", query = "select myTGradeObject from TGradeObject myTGradeObject"),
		@NamedQuery(name = "findTGradeObjectByDueTime", query = "select myTGradeObject from TGradeObject myTGradeObject where myTGradeObject.dueTime = ?1"),
		@NamedQuery(name = "findTGradeObjectById", query = "select myTGradeObject from TGradeObject myTGradeObject where myTGradeObject.id = ?1"),
		@NamedQuery(name = "findTGradeObjectByPointsPossible", query = "select myTGradeObject from TGradeObject myTGradeObject where myTGradeObject.pointsPossible = ?1"),
		@NamedQuery(name = "findTGradeObjectByPrimaryKey", query = "select myTGradeObject from TGradeObject myTGradeObject where myTGradeObject.id = ?1"),
		@NamedQuery(name = "findTGradeObjectByReleased", query = "select myTGradeObject from TGradeObject myTGradeObject where myTGradeObject.released = ?1"),
		@NamedQuery(name = "findTGradeObjectByStartTime", query = "select myTGradeObject from TGradeObject myTGradeObject where myTGradeObject.startTime = ?1"),
		@NamedQuery(name = "findTGradeObjectByTitle", query = "select myTGradeObject from TGradeObject myTGradeObject where myTGradeObject.title = ?1"),
		@NamedQuery(name = "findTGradeObjectByTitleContaining", query = "select myTGradeObject from TGradeObject myTGradeObject where myTGradeObject.title like ?1"),
		@NamedQuery(name = "findTGradeObjectByType", query = "select myTGradeObject from TGradeObject myTGradeObject where myTGradeObject.type = ?1"),
		@NamedQuery(name = "findTGradeObjectByTypeContaining", query = "select myTGradeObject from TGradeObject myTGradeObject where myTGradeObject.type like ?1") })
@Table(catalog = "xidlims", name = "t_grade_object")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "TGradeObject")
public class TGradeObject implements Serializable {
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
	 * ���Ի���ҵ���
	 * 
	 */

	@Column(name = "title")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String title;
	/**
	 * ��ֳɼ�
	 * 
	 */

	@Column(name = "points_possible", scale = 1, precision = 11)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal pointsPossible;
	
	@Column(name = "weight", scale = 1, precision = 11)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal weight;
	
	@Column(name = "marked", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer marked;
	
	public BigDecimal getWeight() {
		return weight;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}

	public Integer getMarked() {
		return marked;
	}

	public void setMarked(Integer marked) {
		this.marked = marked;
	}

	/**
	 * ���test���ԣ�assignment��ҵ
	 * 
	 */

	@Column(name = "type")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String type;
	/**
	 * �Ƿ񷢲�:1������0δ����
	 * 
	 */

	@Column(name = "released", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer released;
	/**
	 * ����ʱ��
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "due_time")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar dueTime;
	/**
	 * ��ʼʱ��
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "start_time")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar startTime;

	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "assignment_id", referencedColumnName = "id") })
	@XmlTransient
	TAssignment TAssignment;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "grade_id", referencedColumnName = "id") })
	@XmlTransient
	TGradebook TGradebook;

	@OneToMany(mappedBy = "TGradeObject", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.TGradeRecord> TGradeRecords;

	/**
	 */
	public void setTGradeRecords(Set<TGradeRecord> TGradeRecords) {
		this.TGradeRecords = TGradeRecords;
	}

	/**
	 */
	public Set<TGradeRecord> getTGradeRecords() {
		if (TGradeRecords == null) {
			TGradeRecords = new java.util.LinkedHashSet<net.xidlims.domain.TGradeRecord>();
		}
		return TGradeRecords;
	}
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
	 * ���Ի���ҵ���
	 * 
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * ���Ի���ҵ���
	 * 
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * ��ֳɼ�
	 * 
	 */
	public void setPointsPossible(BigDecimal pointsPossible) {
		this.pointsPossible = pointsPossible;
	}

	/**
	 * ��ֳɼ�
	 * 
	 */
	public BigDecimal getPointsPossible() {
		return this.pointsPossible;
	}

	/**
	 * ���test���ԣ�assignment��ҵ
	 * 
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * ���test���ԣ�assignment��ҵ
	 * 
	 */
	public String getType() {
		return this.type;
	}

	/**
	 * �Ƿ񷢲�:1������0δ����
	 * 
	 */
	public void setReleased(Integer released) {
		this.released = released;
	}

	/**
	 * �Ƿ񷢲�:1������0δ����
	 * 
	 */
	public Integer getReleased() {
		return this.released;
	}

	/**
	 * ����ʱ��
	 * 
	 */
	public void setDueTime(Calendar dueTime) {
		this.dueTime = dueTime;
	}

	/**
	 * ����ʱ��
	 * 
	 */
	public Calendar getDueTime() {
		return this.dueTime;
	}

	/**
	 * ��ʼʱ��
	 * 
	 */
	public void setStartTime(Calendar startTime) {
		this.startTime = startTime;
	}

	/**
	 * ��ʼʱ��
	 * 
	 */
	public Calendar getStartTime() {
		return this.startTime;
	}

	/**
	 */
	public void setTAssignment(TAssignment TAssignment) {
		this.TAssignment = TAssignment;
	}

	/**
	 */
	public TAssignment getTAssignment() {
		return TAssignment;
	}

	/**
	 */
	public void setTGradebook(TGradebook TGradebook) {
		this.TGradebook = TGradebook;
	}

	/**
	 */
	public TGradebook getTGradebook() {
		return TGradebook;
	}

	/**
	 */
	public TGradeObject() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(TGradeObject that) {
		setId(that.getId());
		setTitle(that.getTitle());
		setPointsPossible(that.getPointsPossible());
		setType(that.getType());
		setReleased(that.getReleased());
		setDueTime(that.getDueTime());
		setStartTime(that.getStartTime());
		setTAssignment(that.getTAssignment());
		setTGradebook(that.getTGradebook());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("id=[").append(id).append("] ");
		buffer.append("title=[").append(title).append("] ");
		buffer.append("pointsPossible=[").append(pointsPossible).append("] ");
		buffer.append("type=[").append(type).append("] ");
		buffer.append("released=[").append(released).append("] ");
		buffer.append("dueTime=[").append(dueTime).append("] ");
		buffer.append("startTime=[").append(startTime).append("] ");

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
		if (!(obj instanceof TGradeObject))
			return false;
		TGradeObject equalCheck = (TGradeObject) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
