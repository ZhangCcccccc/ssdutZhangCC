package net.xidlims.domain;

import java.io.Serializable;

import java.lang.StringBuilder;

import java.util.Set;

import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.codehaus.jackson.annotate.JsonIgnore;

import javax.xml.bind.annotation.*;

import javax.persistence.*;

/**
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "findAllTMistakeItems", query = "select myTMistakeItem from TMistakeItem myTMistakeItem"),
		@NamedQuery(name = "findTMistakeItemByErrorCount", query = "select myTMistakeItem from TMistakeItem myTMistakeItem where myTMistakeItem.errorCount = ?1"),
		@NamedQuery(name = "findTMistakeItemById", query = "select myTMistakeItem from TMistakeItem myTMistakeItem where myTMistakeItem.id = ?1"),
		@NamedQuery(name = "findTMistakeItemByPrimaryKey", query = "select myTMistakeItem from TMistakeItem myTMistakeItem where myTMistakeItem.id = ?1") })
@Table(catalog = "xidlims", name = "t_mistake_item")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "TMistakeItem")
public class TMistakeItem implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ���������¼
	 * 
	 */

	@Column(name = "id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	@XmlElement
	Integer id;
	/**
	 * �������
	 * 
	 */

	@Column(name = "error_count")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer errorCount;

	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "site_id", referencedColumnName = "id") })
	@XmlTransient
	TCourseSite TCourseSite;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "item_id", referencedColumnName = "id") })
	@XmlTransient
	TAssignmentItem TAssignmentItem;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "username", referencedColumnName = "username") })
	@XmlTransient
	User user;

	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "question_id", referencedColumnName = "questionpool_id") })
	@XmlTransient
	TAssignmentQuestionpool TAssignmentQuestionpool;
	
	/**
	 * ���������¼
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * ���������¼
	 * 
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * �������
	 * 
	 */
	public void setErrorCount(Integer errorCount) {
		this.errorCount = errorCount;
	}

	/**
	 * �������
	 * 
	 */
	public Integer getErrorCount() {
		return this.errorCount;
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
	public void setTAssignmentItem(TAssignmentItem TAssignmentItem) {
		this.TAssignmentItem = TAssignmentItem;
	}

	/**
	 */
	@JsonIgnore
	public TAssignmentItem getTAssignmentItem() {
		return TAssignmentItem;
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
	public void setTAssignmentQuestionpool(TAssignmentQuestionpool TAssignmentQuestionpool) {
		this.TAssignmentQuestionpool = TAssignmentQuestionpool;
	}

	/**
	 */
	@JsonIgnore
	public TAssignmentQuestionpool getTAssignmentQuestionpool() {
		return TAssignmentQuestionpool;
	}
	
	/**
	 */
	public TMistakeItem() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(TMistakeItem that) {
		setId(that.getId());
		setErrorCount(that.getErrorCount());
		setTCourseSite(that.getTCourseSite());
		setTAssignmentItem(that.getTAssignmentItem());
		setUser(that.getUser());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("id=[").append(id).append("] ");
		buffer.append("errorCount=[").append(errorCount).append("] ");

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
		if (!(obj instanceof TMistakeItem))
			return false;
		TMistakeItem equalCheck = (TMistakeItem) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
