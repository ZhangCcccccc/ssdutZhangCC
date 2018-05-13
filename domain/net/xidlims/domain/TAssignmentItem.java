package net.xidlims.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

/**
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "findAllTAssignmentItems", query = "select myTAssignmentItem from TAssignmentItem myTAssignmentItem"),
		@NamedQuery(name = "findTAssignmentItemByCreatedTime", query = "select myTAssignmentItem from TAssignmentItem myTAssignmentItem where myTAssignmentItem.createdTime = ?1"),
		@NamedQuery(name = "findTAssignmentItemByDescription", query = "select myTAssignmentItem from TAssignmentItem myTAssignmentItem where myTAssignmentItem.description = ?1"),
		@NamedQuery(name = "findTAssignmentItemByDescriptionContaining", query = "select myTAssignmentItem from TAssignmentItem myTAssignmentItem where myTAssignmentItem.description like ?1"),
		@NamedQuery(name = "findTAssignmentItemByGrade", query = "select myTAssignmentItem from TAssignmentItem myTAssignmentItem where myTAssignmentItem.grade = ?1"),
		@NamedQuery(name = "findTAssignmentItemByGradeContaining", query = "select myTAssignmentItem from TAssignmentItem myTAssignmentItem where myTAssignmentItem.grade like ?1"),
		@NamedQuery(name = "findTAssignmentItemById", query = "select myTAssignmentItem from TAssignmentItem myTAssignmentItem where myTAssignmentItem.id = ?1"),
		@NamedQuery(name = "findTAssignmentItemByPrimaryKey", query = "select myTAssignmentItem from TAssignmentItem myTAssignmentItem where myTAssignmentItem.id = ?1"),
		@NamedQuery(name = "findTAssignmentItemByScore", query = "select myTAssignmentItem from TAssignmentItem myTAssignmentItem where myTAssignmentItem.score = ?1"),
		@NamedQuery(name = "findTAssignmentItemBySequence", query = "select myTAssignmentItem from TAssignmentItem myTAssignmentItem where myTAssignmentItem.sequence = ?1"),
		@NamedQuery(name = "findTAssignmentItemByStatus", query = "select myTAssignmentItem from TAssignmentItem myTAssignmentItem where myTAssignmentItem.status = ?1") })
@Table(catalog = "xidlims", name = "t_assignment_item")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "TAssignmentItem")
@XmlRootElement(namespace = "xidlims/net/xidlims/domain")
public class TAssignmentItem implements Serializable {
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
	 * ��Ŀ˳��
	 * 
	 */

	@Column(name = "sequence")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer sequence;
	
	@Column(name = "type")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer type;
	
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

	@Column(name = "description")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String description;
	/**
	 * ���
	 * 
	 */
	
	@Column(name = "descriptionTemp")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String descriptionTemp;
	/**
	 * �÷�
	 * 
	 */

	@Column(name = "grade")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String grade;
	/**
	 * ��ֵ
	 * 
	 */

	@Column(name = "score", scale = 1, precision = 10)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal score;
	/**
	 * ״̬ 1���ѷ�����0��δ����
	 * 
	 */

	@Column(name = "status")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer status;
	/**
	 * ����ʱ��
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_time")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar createdTime;

	
	@Column(name = "item_parent")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer itemParent;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "created_by", referencedColumnName = "username") })
	@XmlTransient
	User user;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "section_id", referencedColumnName = "id") })
	@XmlTransient
	TAssignmentSection TAssignmentSection;
	/**
	 */
	@ManyToMany(mappedBy = "TAssignmentItems", fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.TAssignmentQuestionpool> TAssignmentQuestionpools;
	/**
	 */
	@OneToMany(mappedBy = "TAssignmentItem", cascade = { CascadeType.REMOVE,CascadeType.MERGE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	@OrderBy(value="label ASC")
	java.util.Set<net.xidlims.domain.TAssignmentAnswer> TAssignmentAnswers;
	/**
	 */
	@OneToMany(mappedBy = "TAssignmentItem", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.TAssignmentItemtext> TAssignmentItemtexts;
	
	/**
	 */
	@OneToMany(mappedBy = "TAssignmentItem", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.TAssignmentItemMapping> TAssignmentItemMappings;
	
	/**
	 */
	@OneToMany(mappedBy = "TAssignmentItem", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.TMistakeItem> TMistakeItems;

	public java.util.Set<net.xidlims.domain.TMistakeItem> getTMistakeItems() {
		return TMistakeItems;
	}

	public void setTMistakeItems(
			java.util.Set<net.xidlims.domain.TMistakeItem> tMistakeItems) {
		TMistakeItems = tMistakeItems;
	}

	/**
	 */
	public void setTAssignmentItemMappings(Set<TAssignmentItemMapping> TAssignmentItemMappings) {
		this.TAssignmentItemMappings = TAssignmentItemMappings;
	}

	/**
	 */
	public Set<TAssignmentItemMapping> getTAssignmentItemMappings() {
		if (TAssignmentItemMappings == null) {
			TAssignmentItemMappings = new java.util.LinkedHashSet<net.xidlims.domain.TAssignmentItemMapping>();
		}
		return TAssignmentItemMappings;
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
	 * ��Ŀ˳��
	 * 
	 */
	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	/**
	 * ��Ŀ˳��
	 * 
	 */
	public Integer getSequence() {
		return this.sequence;
	}

	/**
	 * ���
	 * 
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getDescriptionTemp() {
		return descriptionTemp;
	}

	public void setDescriptionTemp(String descriptionTemp) {
		this.descriptionTemp = descriptionTemp;
	}

	/**
	 * ���
	 * 
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * �÷�
	 * 
	 */
	public void setGrade(String grade) {
		this.grade = grade;
	}

	/**
	 * �÷�
	 * 
	 */
	public String getGrade() {
		return this.grade;
	}

	/**
	 * ��ֵ
	 * 
	 */
	public void setScore(BigDecimal score) {
		this.score = score;
	}

	/**
	 * ��ֵ
	 * 
	 */
	public BigDecimal getScore() {
		return this.score;
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

	public Integer getItemParent() {
		return itemParent;
	}

	public void setItemParent(Integer itemParent) {
		this.itemParent = itemParent;
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
	public void setTAssignmentSection(TAssignmentSection TAssignmentSection) {
		this.TAssignmentSection = TAssignmentSection;
	}

	/**
	 */
	public TAssignmentSection getTAssignmentSection() {
		return TAssignmentSection;
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
	public void setTAssignmentAnswers(Set<TAssignmentAnswer> TAssignmentAnswers) {
		this.TAssignmentAnswers = TAssignmentAnswers;
	}

	/**
	 */
	public Set<TAssignmentAnswer> getTAssignmentAnswers() {
		if (TAssignmentAnswers == null) {
			TAssignmentAnswers = new java.util.LinkedHashSet<net.xidlims.domain.TAssignmentAnswer>();
		}
		return TAssignmentAnswers;
	}

	/**
	 */
	public void setTAssignmentItemtexts(Set<TAssignmentItemtext> TAssignmentItemtexts) {
		this.TAssignmentItemtexts = TAssignmentItemtexts;
	}

	/**
	 */
	public Set<TAssignmentItemtext> getTAssignmentItemtexts() {
		if (TAssignmentItemtexts == null) {
			TAssignmentItemtexts = new java.util.LinkedHashSet<net.xidlims.domain.TAssignmentItemtext>();
		}
		return TAssignmentItemtexts;
	}

	/**
	 */
	public TAssignmentItem() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(TAssignmentItem that) {
		setId(that.getId());
		setSequence(that.getSequence());
		setDescription(that.getDescription());
		setGrade(that.getGrade());
		setScore(that.getScore());
		setStatus(that.getStatus());
		setCreatedTime(that.getCreatedTime());
		setDescriptionTemp(that.getDescriptionTemp());
		setType(that.getType());
		setUser(that.getUser());
		setTAssignmentSection(that.getTAssignmentSection());
		setTAssignmentQuestionpools(new java.util.LinkedHashSet<net.xidlims.domain.TAssignmentQuestionpool>(that.getTAssignmentQuestionpools()));
		setTAssignmentAnswers(new java.util.LinkedHashSet<net.xidlims.domain.TAssignmentAnswer>(that.getTAssignmentAnswers()));
		setTAssignmentItemtexts(new java.util.LinkedHashSet<net.xidlims.domain.TAssignmentItemtext>(that.getTAssignmentItemtexts()));
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("id=[").append(id).append("] ");
		buffer.append("sequence=[").append(sequence).append("] ");
		buffer.append("description=[").append(description).append("] ");
		buffer.append("grade=[").append(grade).append("] ");
		buffer.append("score=[").append(score).append("] ");
		buffer.append("status=[").append(status).append("] ");
		buffer.append("createdTime=[").append(createdTime).append("] ");

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
		if (!(obj instanceof TAssignmentItem))
			return false;
		TAssignmentItem equalCheck = (TAssignmentItem) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}

}
