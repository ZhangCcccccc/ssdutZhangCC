package net.xidlims.domain;

import java.io.Serializable;

import java.lang.StringBuilder;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import javax.xml.bind.annotation.*;

import javax.persistence.*;

import org.codehaus.jackson.annotate.JsonIgnore;

/**
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "findAllLabRoomLimitTimes", query = "select myLabRoomLimitTime from LabRoomLimitTime myLabRoomLimitTime"),
		@NamedQuery(name = "findLabRoomLimitTimeByEndclass", query = "select myLabRoomLimitTime from LabRoomLimitTime myLabRoomLimitTime where myLabRoomLimitTime.endclass = ?1"),
		@NamedQuery(name = "findLabRoomLimitTimeByEndweek", query = "select myLabRoomLimitTime from LabRoomLimitTime myLabRoomLimitTime where myLabRoomLimitTime.endweek = ?1"),
		@NamedQuery(name = "findLabRoomLimitTimeById", query = "select myLabRoomLimitTime from LabRoomLimitTime myLabRoomLimitTime where myLabRoomLimitTime.id = ?1"),
		@NamedQuery(name = "findLabRoomLimitTimeByInfo", query = "select myLabRoomLimitTime from LabRoomLimitTime myLabRoomLimitTime where myLabRoomLimitTime.info = ?1"),
		@NamedQuery(name = "findLabRoomLimitTimeByInfoContaining", query = "select myLabRoomLimitTime from LabRoomLimitTime myLabRoomLimitTime where myLabRoomLimitTime.info like ?1"),
		@NamedQuery(name = "findLabRoomLimitTimeByLabId", query = "select myLabRoomLimitTime from LabRoomLimitTime myLabRoomLimitTime where myLabRoomLimitTime.labId = ?1"),
		@NamedQuery(name = "findLabRoomLimitTimeByPrimaryKey", query = "select myLabRoomLimitTime from LabRoomLimitTime myLabRoomLimitTime where myLabRoomLimitTime.id = ?1"),
		@NamedQuery(name = "findLabRoomLimitTimeByStartclass", query = "select myLabRoomLimitTime from LabRoomLimitTime myLabRoomLimitTime where myLabRoomLimitTime.startclass = ?1"),
		@NamedQuery(name = "findLabRoomLimitTimeByStartweek", query = "select myLabRoomLimitTime from LabRoomLimitTime myLabRoomLimitTime where myLabRoomLimitTime.startweek = ?1"),
		@NamedQuery(name = "findLabRoomLimitTimeByWeekday", query = "select myLabRoomLimitTime from LabRoomLimitTime myLabRoomLimitTime where myLabRoomLimitTime.weekday = ?1") })
@Table(catalog = "xidlims", name = "lab_room_limit_time")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "test/net/xidlims/domain", name = "LabRoomLimitTime")
public class LabRoomLimitTime implements Serializable {
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
	 * ������Ӧѧ�ڵ�id
	 * 
	 */


	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "term", referencedColumnName = "id") })
	@XmlTransient
	SchoolTerm schoolTerm;
	/**
	 * ����
	 * 
	 */

	@Column(name = "weekday", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer weekday;
	/**
	 * ��ʼ����
	 * 
	 */

	@Column(name = "startweek", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer startweek;
	/**
	 * ��������
	 * 
	 */

	@Column(name = "endweek", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer endweek;
	/**
	 * ��ʼ�ڴ�
	 * 
	 */

	@Column(name = "startclass", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer startclass;
	/**
	 */

	@Column(name = "endclass", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer endclass;
	/**
	 * ������ʵ����id�����
	 * 
	 */

	@Column(name = "lab_id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer labId;
	
	@Column(name = "flag")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer flag;
	
	/**
	 */

	@Column(name = "info")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String info;

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
	 * ����
	 * 
	 */
	public void setWeekday(Integer weekday) {
		this.weekday = weekday;
	}

	/**
	 * ����
	 * 
	 */
	public Integer getWeekday() {
		return this.weekday;
	}

	/**
	 * ��ʼ����
	 * 
	 */
	public void setStartweek(Integer startweek) {
		this.startweek = startweek;
	}

	/**
	 * ��ʼ����
	 * 
	 */
	public Integer getStartweek() {
		return this.startweek;
	}

	/**
	 * ��������
	 * 
	 */
	public void setEndweek(Integer endweek) {
		this.endweek = endweek;
	}

	/**
	 * ��������
	 * 
	 */
	public Integer getEndweek() {
		return this.endweek;
	}

	/**
	 * ��ʼ�ڴ�
	 * 
	 */
	public void setStartclass(Integer startclass) {
		this.startclass = startclass;
	}

	/**
	 * ��ʼ�ڴ�
	 * 
	 */
	public Integer getStartclass() {
		return this.startclass;
	}

	/**
	 */
	public void setEndclass(Integer endclass) {
		this.endclass = endclass;
	}

	/**
	 */
	public Integer getEndclass() {
		return this.endclass;
	}

	/**
	 * ������ʵ����id�����
	 * 
	 */
	public void setLabId(Integer labId) {
		this.labId = labId;
	}

	/**
	 * ������ʵ����id�����
	 * 
	 */
	public Integer getLabId() {
		return this.labId;
	}
	
	

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	/**
	 */
	public void setInfo(String info) {
		this.info = info;
	}

	/**
	 */
	public String getInfo() {
		return this.info;
	}

	/**
	 */
	public LabRoomLimitTime() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(LabRoomLimitTime that) {
		setId(that.getId());
		setWeekday(that.getWeekday());
		setStartweek(that.getStartweek());
		setEndweek(that.getEndweek());
		setStartclass(that.getStartclass());
		setEndclass(that.getEndclass());
		setLabId(that.getLabId());
		setInfo(that.getInfo());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("id=[").append(id).append("] ");
		buffer.append("weekday=[").append(weekday).append("] ");
		buffer.append("startweek=[").append(startweek).append("] ");
		buffer.append("endweek=[").append(endweek).append("] ");
		buffer.append("startclass=[").append(startclass).append("] ");
		buffer.append("endclass=[").append(endclass).append("] ");
		buffer.append("labId=[").append(labId).append("] ");
		buffer.append("info=[").append(info).append("] ");

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
		if (!(obj instanceof LabRoomLimitTime))
			return false;
		LabRoomLimitTime equalCheck = (LabRoomLimitTime) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
