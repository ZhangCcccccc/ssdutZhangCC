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

import javax.xml.bind.annotation.*;

import javax.persistence.*;

/**
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "findAllTAssignmentSections", query = "select myTAssignmentSection from TAssignmentSection myTAssignmentSection"),
		@NamedQuery(name = "findTAssignmentSectionByCreatedTime", query = "select myTAssignmentSection from TAssignmentSection myTAssignmentSection where myTAssignmentSection.createdTime = ?1"),
		@NamedQuery(name = "findTAssignmentSectionByDescription", query = "select myTAssignmentSection from TAssignmentSection myTAssignmentSection where myTAssignmentSection.description = ?1"),
		@NamedQuery(name = "findTAssignmentSectionByDescriptionContaining", query = "select myTAssignmentSection from TAssignmentSection myTAssignmentSection where myTAssignmentSection.description like ?1"),
		@NamedQuery(name = "findTAssignmentSectionById", query = "select myTAssignmentSection from TAssignmentSection myTAssignmentSection where myTAssignmentSection.id = ?1"),
		@NamedQuery(name = "findTAssignmentSectionByPrimaryKey", query = "select myTAssignmentSection from TAssignmentSection myTAssignmentSection where myTAssignmentSection.id = ?1"),
		@NamedQuery(name = "findTAssignmentSectionBySequence", query = "select myTAssignmentSection from TAssignmentSection myTAssignmentSection where myTAssignmentSection.sequence = ?1"),
		@NamedQuery(name = "findTAssignmentSectionByStatus", query = "select myTAssignmentSection from TAssignmentSection myTAssignmentSection where myTAssignmentSection.status = ?1") })
@Table(catalog = "xidlims", name = "t_assignment_section")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "TAssignmentSection")
@XmlRootElement(namespace = "xidlims/net/xidlims/domain")
public class TAssignmentSection implements Serializable {
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
	 * ����˳��
	 * 
	 */

	@Column(name = "sequence")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer sequence;
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
	 * ���
	 * 
	 */

	@Column(name = "description")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String description;
	/**
	 * ״̬ 1���ѷ�����0��δ����
	 * 
	 */

	@Column(name = "status")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer status;

	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "created_by", referencedColumnName = "username") })
	@XmlTransient
	User user;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "assignment_id", referencedColumnName = "id") })
	@XmlTransient
	TAssignment TAssignment;
	/**
	 */
	@OneToMany(mappedBy = "TAssignmentSection", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	@OrderBy(value="sequence ASC")
	java.util.Set<net.xidlims.domain.TAssignmentItem> TAssignmentItems;

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
	 * ����˳��
	 * 
	 */
	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	/**
	 * ����˳��
	 * 
	 */
	public Integer getSequence() {
		return this.sequence;
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

	/**
	 * ���
	 * 
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * ���
	 * 
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * ״̬ 1���ѷ�����0��δ����
	 * 
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * ״̬ 1���ѷ�����0��δ����
	 * 
	 */
	public Integer getStatus() {
		return this.status;
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
	public TAssignmentSection() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(TAssignmentSection that) {
		setId(that.getId());
		setSequence(that.getSequence());
		setCreatedTime(that.getCreatedTime());
		setDescription(that.getDescription());
		setStatus(that.getStatus());
		setUser(that.getUser());
		setTAssignment(that.getTAssignment());
		setTAssignmentItems(new java.util.LinkedHashSet<net.xidlims.domain.TAssignmentItem>(that.getTAssignmentItems()));
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("id=[").append(id).append("] ");
		buffer.append("sequence=[").append(sequence).append("] ");
		buffer.append("createdTime=[").append(createdTime).append("] ");
		buffer.append("description=[").append(description).append("] ");
		buffer.append("status=[").append(status).append("] ");

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
		if (!(obj instanceof TAssignmentSection))
			return false;
		TAssignmentSection equalCheck = (TAssignmentSection) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
	
	
}
