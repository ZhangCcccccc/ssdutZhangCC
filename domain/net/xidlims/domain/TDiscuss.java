package net.xidlims.domain;

import java.io.Serializable;

import java.lang.StringBuilder;

import java.util.Calendar;
import java.util.LinkedHashSet;
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
		@NamedQuery(name = "findAllTDiscusss", query = "select myTDiscuss from TDiscuss myTDiscuss"),
		@NamedQuery(name = "findTDiscussByContent", query = "select myTDiscuss from TDiscuss myTDiscuss where myTDiscuss.content = ?1"),
		@NamedQuery(name = "findTDiscussByContentContaining", query = "select myTDiscuss from TDiscuss myTDiscuss where myTDiscuss.content like ?1"),
		@NamedQuery(name = "findTDiscussByDiscussTime", query = "select myTDiscuss from TDiscuss myTDiscuss where myTDiscuss.discussTime = ?1"),
		@NamedQuery(name = "findTDiscussById", query = "select myTDiscuss from TDiscuss myTDiscuss where myTDiscuss.id = ?1"),
		@NamedQuery(name = "findTDiscussByIp", query = "select myTDiscuss from TDiscuss myTDiscuss where myTDiscuss.ip = ?1"),
		@NamedQuery(name = "findTDiscussByIpContaining", query = "select myTDiscuss from TDiscuss myTDiscuss where myTDiscuss.ip like ?1"),
		@NamedQuery(name = "findTDiscussByPrimaryKey", query = "select myTDiscuss from TDiscuss myTDiscuss where myTDiscuss.id = ?1"),
		@NamedQuery(name = "findTDiscussByType", query = "select myTDiscuss from TDiscuss myTDiscuss where myTDiscuss.type = ?1") })
@Table(catalog = "xidlims", name = "t_discuss")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "TDiscuss")
@XmlRootElement(namespace = "xidlims/net/xidlims/domain")
public class TDiscuss implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ����ID
	 * 
	 */

	@Column(name = "id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	@XmlElement
	Integer id;
	
	/**
	 * ��������
	 * 
	 */

	@Column(name = "title")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String title;
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * ��������
	 * 
	 */

	@Column(name = "content")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String content;
	/**
	 * ����ʱ��
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "discussTime", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar discussTime;
	/**
	 * ip��ַ
	 * 
	 */

	@Column(name = "ip", length = 50)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String ip;
	/**
	 */

	@Column(name = "type")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer type;
	
	/**
	 */

	@Column(name = "skill_id")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer skillId;

	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "topDiscuss", referencedColumnName = "id") })
	@XmlTransient
	TDiscuss tDiscuss;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "userName", referencedColumnName = "username") })
	@XmlTransient
	User user;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "siteId", referencedColumnName = "id") })
	@XmlTransient
	TCourseSite TCourseSite;
	/**
	 */
	@OneToMany(mappedBy = "tDiscuss", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.TDiscuss> tDiscusses;

	/**
	 * ����ID
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * ����ID
	 * 
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * ��������
	 * 
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * ��������
	 * 
	 */
	public String getContent() {
		return this.content;
	}

	/**
	 * ����ʱ��
	 * 
	 */
	public void setDiscussTime(Calendar discussTime) {
		this.discussTime = discussTime;
	}

	/**
	 * ����ʱ��
	 * 
	 */
	public Calendar getDiscussTime() {
		return this.discussTime;
	}

	/**
	 * ip��ַ
	 * 
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}

	/**
	 * ip��ַ
	 * 
	 */
	public String getIp() {
		return this.ip;
	}

	/**
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 */
	public Integer getType() {
		return this.type;
	}

	/**
	 */
	public void setTDiscuss(TDiscuss tDiscuss) {
		this.tDiscuss = tDiscuss;
	}

	/**
	 */
	@JsonIgnore
	public TDiscuss getTDiscuss() {
		return tDiscuss;
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
	public void setTDiscusses(Set<TDiscuss> tDiscusses) {
		this.tDiscusses = tDiscusses;
	}

	public Integer getSkillId() {
		return skillId;
	}

	public void setSkillId(Integer skillId) {
		this.skillId = skillId;
	}

	/**
	 */
	@JsonIgnore
	public Set<TDiscuss> getTDiscusses() {
		if (tDiscusses == null) {
			tDiscusses = new java.util.LinkedHashSet<net.xidlims.domain.TDiscuss>();
		}
		return tDiscusses;
	}

	/**
	 */
	public TDiscuss() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(TDiscuss that) {
		setId(that.getId());
		setContent(that.getContent());
		setDiscussTime(that.getDiscussTime());
		setIp(that.getIp());
		setType(that.getType());
		setTDiscuss(that.getTDiscuss());
		setUser(that.getUser());
		setTCourseSite(that.getTCourseSite());
		setTDiscusses(new java.util.LinkedHashSet<net.xidlims.domain.TDiscuss>(that.getTDiscusses()));
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("id=[").append(id).append("] ");
		buffer.append("content=[").append(content).append("] ");
		buffer.append("discussTime=[").append(discussTime).append("] ");
		buffer.append("ip=[").append(ip).append("] ");
		buffer.append("type=[").append(type).append("] ");

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
		if (!(obj instanceof TDiscuss))
			return false;
		TDiscuss equalCheck = (TDiscuss) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
