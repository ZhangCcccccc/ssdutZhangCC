package net.xidlims.domain;

import java.io.Serializable;

import java.lang.StringBuilder;

import java.util.Calendar;

import java.util.Set;

import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import javax.xml.bind.annotation.*;

import javax.persistence.*;

import org.codehaus.jackson.annotate.JsonIgnore;

/**
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "findAllTAssignmentQuestionpools", query = "select myTAssignmentQuestionpool from TAssignmentQuestionpool myTAssignmentQuestionpool"),
		@NamedQuery(name = "findTAssignmentQuestionpoolByCreatedTime", query = "select myTAssignmentQuestionpool from TAssignmentQuestionpool myTAssignmentQuestionpool where myTAssignmentQuestionpool.createdTime = ?1"),
		@NamedQuery(name = "findTAssignmentQuestionpoolByDescription", query = "select myTAssignmentQuestionpool from TAssignmentQuestionpool myTAssignmentQuestionpool where myTAssignmentQuestionpool.description = ?1"),
		@NamedQuery(name = "findTAssignmentQuestionpoolByDescriptionContaining", query = "select myTAssignmentQuestionpool from TAssignmentQuestionpool myTAssignmentQuestionpool where myTAssignmentQuestionpool.description like ?1"),
		@NamedQuery(name = "findTAssignmentQuestionpoolByPrimaryKey", query = "select myTAssignmentQuestionpool from TAssignmentQuestionpool myTAssignmentQuestionpool where myTAssignmentQuestionpool.questionpoolId = ?1"),
		@NamedQuery(name = "findTAssignmentQuestionpoolByQuestionpoolId", query = "select myTAssignmentQuestionpool from TAssignmentQuestionpool myTAssignmentQuestionpool where myTAssignmentQuestionpool.questionpoolId = ?1"),
		@NamedQuery(name = "findTAssignmentQuestionpoolByTitle", query = "select myTAssignmentQuestionpool from TAssignmentQuestionpool myTAssignmentQuestionpool where myTAssignmentQuestionpool.title = ?1"),
		@NamedQuery(name = "findTAssignmentQuestionpoolByTitleContaining", query = "select myTAssignmentQuestionpool from TAssignmentQuestionpool myTAssignmentQuestionpool where myTAssignmentQuestionpool.title like ?1") })
@Table(catalog = "xidlims", name = "t_assignment_questionpool")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "TAssignmentQuestionpool")
@XmlRootElement(namespace = "xidlims/net/xidlims/domain")
public class TAssignmentQuestionpool implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ���id
	 * 
	 */

	@Column(name = "questionpool_id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	@XmlElement
	Integer questionpoolId;
	/**
	 * ������
	 * 
	 */

	@Column(name = "title")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String title;
	/**
	 * ����ʱ��
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_time")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar createdTime;
	/**
	 * ����ʱ��
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modify_time")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar modifyTime;
	/**
	 * ���
	 * 
	 */

	@Column(name = "description")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String description;
	
	/**
	 * ������
	 * 
	 */

	@Column(name = "type")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer type;

	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "owner", referencedColumnName = "username") })
	@XmlTransient
	User user;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "parentpool_id", referencedColumnName = "questionpool_id") })
	@XmlTransient
	TAssignmentQuestionpool TAssignmentQuestionpool;
	/**
	 */
	@OneToMany(mappedBy = "TAssignmentQuestionpool", fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.TAssignmentQuestionpool> TAssignmentQuestionpools;
	/**
	 */
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(catalog = "xidlims", name = "t_assignment_questionpool_item", joinColumns = { @JoinColumn(name = "questionpool_id", referencedColumnName = "questionpool_id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "item_id", referencedColumnName = "id", nullable = false, updatable = false) })
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.TAssignmentItem> TAssignmentItems;
	/**
	 */
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(catalog = "xidlims", name = "t_assignment_questionpool_access", joinColumns = { @JoinColumn(name = "questionpool_id", referencedColumnName = "questionpool_id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "manger", referencedColumnName = "username", nullable = false, updatable = false) })
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.User> users;

	/**
	 */
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(catalog = "xidlims", name = "t_course_questionpool", joinColumns = { @JoinColumn(name = "t_questionpool_id", referencedColumnName = "questionpool_id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "t_course_site_id", referencedColumnName = "id", nullable = false, updatable = false) })
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.TCourseSite> TCourseSites;
	/**
	 */
	@ManyToMany(mappedBy = "TAssignmentQuestionpools", fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.TAssignment> TAssignments;
	/**
	 * ���id
	 * 
	 */
	public void setQuestionpoolId(Integer questionpoolId) {
		this.questionpoolId = questionpoolId;
	}

	/**
	 * ���id
	 * 
	 */
	public Integer getQuestionpoolId() {
		return this.questionpoolId;
	}

	/**
	 * ������
	 * 
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * ������
	 * 
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * ����ʱ��
	 * 
	 */
	public void setCreatedTime(Calendar createdTime) {
		this.createdTime = createdTime;
	}

	/**
	 * ����ʱ��
	 * 
	 */
	public Calendar getCreatedTime() {
		return this.createdTime;
	}

	public Calendar getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Calendar modifyTime) {
		this.modifyTime = modifyTime;
	}

	/**
	 * ���
	 * 
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * ���
	 * 
	 */
	public String getDescription() {
		return this.description;
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
	public void setTAssignmentQuestionpool(TAssignmentQuestionpool TAssignmentQuestionpool) {
		this.TAssignmentQuestionpool = TAssignmentQuestionpool;
	}

	/**
	 */
	public TAssignmentQuestionpool getTAssignmentQuestionpool() {
		return TAssignmentQuestionpool;
	}

	/**
	 */
	public void setTAssignmentQuestionpools(Set<TAssignmentQuestionpool> TAssignmentQuestionpools) {
		this.TAssignmentQuestionpools = TAssignmentQuestionpools;
	}

	/**
	 */
	public Set<TAssignmentQuestionpool> getTAssignmentQuestionpools() {
		if (TAssignmentQuestionpools == null) {
			TAssignmentQuestionpools = new java.util.LinkedHashSet<net.xidlims.domain.TAssignmentQuestionpool>();
		}
		return TAssignmentQuestionpools;
	}

	/**
	 */
	public void setTAssignmentItems(Set<TAssignmentItem> TAssignmentItems) {
		this.TAssignmentItems = TAssignmentItems;
	}

	/**
	 */
	public Set<TAssignmentItem> getTAssignmentItems() {
		if (TAssignmentItems == null) {
			TAssignmentItems = new java.util.LinkedHashSet<net.xidlims.domain.TAssignmentItem>();
		}
		return TAssignmentItems;
	}

	/**
	 */
	public void setUsers(Set<User> users) {
		this.users = users;
	}

	/**
	 */
	public Set<User> getUsers() {
		if (users == null) {
			users = new java.util.LinkedHashSet<net.xidlims.domain.User>();
		}
		return users;
	}

	/**
	 */
	public void setTCourseSites(Set<TCourseSite> TCourseSites) {
		this.TCourseSites = TCourseSites;
	}

	/**
	 */
	@JsonIgnore
	public Set<TCourseSite> getTCourseSites() {
		if (TCourseSites == null) {
			TCourseSites = new java.util.LinkedHashSet<net.xidlims.domain.TCourseSite>();
		}
		return TCourseSites;
	}
	
	/**
	 */
	public void setTAssignments(Set<TAssignment> TAssignments) {
		this.TAssignments = TAssignments;
	}

	/**
	 */
	public Set<TAssignment> getTAssignments() {
		if (TAssignments == null) {
			TAssignments = new java.util.LinkedHashSet<net.xidlims.domain.TAssignment>();
		}
		return TAssignments;
	}
	
	/**
	 */
	public TAssignmentQuestionpool() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(TAssignmentQuestionpool that) {
		setQuestionpoolId(that.getQuestionpoolId());
		setTitle(that.getTitle());
		setCreatedTime(that.getCreatedTime());
		setDescription(that.getDescription());
		setUser(that.getUser());
		setTAssignmentQuestionpool(that.getTAssignmentQuestionpool());
		setTAssignmentQuestionpools(new java.util.LinkedHashSet<net.xidlims.domain.TAssignmentQuestionpool>(that.getTAssignmentQuestionpools()));
		setTAssignmentItems(new java.util.LinkedHashSet<net.xidlims.domain.TAssignmentItem>(that.getTAssignmentItems()));
		setUsers(new java.util.LinkedHashSet<net.xidlims.domain.User>(that.getUsers()));
		setTCourseSites(new java.util.LinkedHashSet<net.xidlims.domain.TCourseSite>(that.getTCourseSites()));
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("questionpoolId=[").append(questionpoolId).append("] ");
		buffer.append("title=[").append(title).append("] ");
		buffer.append("createdTime=[").append(createdTime).append("] ");
		buffer.append("description=[").append(description).append("] ");

		return buffer.toString();
	}

	/**
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((questionpoolId == null) ? 0 : questionpoolId.hashCode()));
		return result;
	}

	/**
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof TAssignmentQuestionpool))
			return false;
		TAssignmentQuestionpool equalCheck = (TAssignmentQuestionpool) obj;
		if ((questionpoolId == null && equalCheck.questionpoolId != null) || (questionpoolId != null && equalCheck.questionpoolId == null))
			return false;
		if (questionpoolId != null && !questionpoolId.equals(equalCheck.questionpoolId))
			return false;
		return true;
	}
}
