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
		@NamedQuery(name = "findAllTCourseSiteSchedules", query = "select myTCourseSiteSchedule from TCourseSiteSchedule myTCourseSiteSchedule"),
		@NamedQuery(name = "findTCourseSiteScheduleByContent", query = "select myTCourseSiteSchedule from TCourseSiteSchedule myTCourseSiteSchedule where myTCourseSiteSchedule.content = ?1"),
		@NamedQuery(name = "findTCourseSiteScheduleByContentContaining", query = "select myTCourseSiteSchedule from TCourseSiteSchedule myTCourseSiteSchedule where myTCourseSiteSchedule.content like ?1"),
		@NamedQuery(name = "findTCourseSiteScheduleByCreateTime", query = "select myTCourseSiteSchedule from TCourseSiteSchedule myTCourseSiteSchedule where myTCourseSiteSchedule.createTime = ?1"),
		@NamedQuery(name = "findTCourseSiteScheduleById", query = "select myTCourseSiteSchedule from TCourseSiteSchedule myTCourseSiteSchedule where myTCourseSiteSchedule.id = ?1"),
		@NamedQuery(name = "findTCourseSiteScheduleByPlace", query = "select myTCourseSiteSchedule from TCourseSiteSchedule myTCourseSiteSchedule where myTCourseSiteSchedule.place = ?1"),
		@NamedQuery(name = "findTCourseSiteScheduleByPlaceContaining", query = "select myTCourseSiteSchedule from TCourseSiteSchedule myTCourseSiteSchedule where myTCourseSiteSchedule.place like ?1"),
		@NamedQuery(name = "findTCourseSiteScheduleByPrimaryKey", query = "select myTCourseSiteSchedule from TCourseSiteSchedule myTCourseSiteSchedule where myTCourseSiteSchedule.id = ?1") })
@Table(catalog = "xidlims", name = "t_course_site_schedule")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/xidlims/net/domain", name = "TCourseSiteSchedule")
public class TCourseSiteSchedule implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ����
	 * 
	 */

	@Column(name = "id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	@XmlElement
	Integer id;
	/**
	 * �Ͽ�ʱ��
	 * 
	 */

	@Column(name = "content")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String content;
	/**
	 * �Ͽ�ʱ��
	 * 
	 */

	@Column(name = "week")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer week;
	/**
	 * �Ͽ�ʱ��
	 * 
	 */

	@Column(name = "session")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer session;
	/**
	 * �Ͽ�ʱ��
	 * 
	 */

	@Column(name = "day")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer day;
	/**
	 * �Ͽεص�
	 * 
	 */

	@Column(name = "place")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String place;
	/**
	 * ����ʱ��
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar createTime;

	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "site_id", referencedColumnName = "id") })
	@XmlTransient
	TCourseSite TCourseSite;
	
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "create_by", referencedColumnName = "username") })
	@XmlTransient
	User user;

	/**
	 * ����
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * ����
	 * 
	 */
	public Integer getId() {
		return this.id;
	}
	/**
	 * ����
	 * 
	 */
	public void setWeek(Integer week) {
		this.week = week;
	}

	/**
	 * ����
	 * 
	 */
	public Integer getweek() {
		return this.week;
	}
	/**
	 * ����
	 * 
	 */
	public void setDay(Integer day) {
		this.day = day;
	}

	/**
	 * ����
	 * 
	 */
	public Integer getDay() {
		return this.day;
	}
	
	/**
	 * ����
	 * 
	 */
	public void setSession(Integer session) {
		this.session = session;
	}

	/**
	 * ����
	 * 
	 */
	public Integer getSession() {
		return this.session;
	}
	/**
	 * ����
	 * 
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * �Ͽ�ʱ��
	 * 
	 */
	public String getContent() {
		return this.content;
	}

	/**
	 * �Ͽεص�
	 * 
	 */
	public void setPlace(String place) {
		this.place = place;
	}

	/**
	 * �Ͽεص�
	 * 
	 */
	public String getPlace() {
		return this.place;
	}

	/**
	 * ����ʱ��
	 * 
	 */
	public void setCreateTime(Calendar createTime) {
		this.createTime = createTime;
	}

	/**
	 * ����ʱ��
	 * 
	 */
	public Calendar getCreateTime() {
		return this.createTime;
	}

	/**
	 */
	public void setTCourseSite(TCourseSite TCourseSite) {
		this.TCourseSite = TCourseSite;
	}

	/**
	 */
	@JsonIgnore
	public TCourseSite getTCourseSite() {
		return TCourseSite;
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
	public TCourseSiteSchedule() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(TCourseSiteSchedule that) {
		setId(that.getId());
		setContent(that.getContent());
		setPlace(that.getPlace());
		setCreateTime(that.getCreateTime());
		setTCourseSite(that.getTCourseSite());	
		setUser(that.getUser());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("id=[").append(id).append("] ");
		buffer.append("content=[").append(content).append("] ");
		buffer.append("place=[").append(place).append("] ");
		buffer.append("createTime=[").append(createTime).append("] ");

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
		if (!(obj instanceof TCourseSiteSchedule))
			return false;
		TCourseSiteSchedule equalCheck = (TCourseSiteSchedule) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
