package net.xidlims.domain;

import java.io.Serializable;

import java.lang.StringBuilder;


import java.util.Set;

import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import javax.xml.bind.annotation.*;

import javax.persistence.*;

/**
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "findAllTAssignmentItemtexts", query = "select myTAssignmentItemtext from TAssignmentItemtext myTAssignmentItemtext"),
		@NamedQuery(name = "findTAssignmentItemtextById", query = "select myTAssignmentItemtext from TAssignmentItemtext myTAssignmentItemtext where myTAssignmentItemtext.id = ?1"),
		@NamedQuery(name = "findTAssignmentItemtextByPrimaryKey", query = "select myTAssignmentItemtext from TAssignmentItemtext myTAssignmentItemtext where myTAssignmentItemtext.id = ?1"),
		@NamedQuery(name = "findTAssignmentItemtextBySequence", query = "select myTAssignmentItemtext from TAssignmentItemtext myTAssignmentItemtext where myTAssignmentItemtext.sequence = ?1"),
		@NamedQuery(name = "findTAssignmentItemtextByText", query = "select myTAssignmentItemtext from TAssignmentItemtext myTAssignmentItemtext where myTAssignmentItemtext.text = ?1"),
		@NamedQuery(name = "findTAssignmentItemtextByTextContaining", query = "select myTAssignmentItemtext from TAssignmentItemtext myTAssignmentItemtext where myTAssignmentItemtext.text like ?1") })
@Table(catalog = "xidlims", name = "t_assignment_itemtext")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "TAssignmentItemtext")
@XmlRootElement(namespace = "xidlims/net/xidlims/domain")
public class TAssignmentItemtext implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 */

	@Column(name = "id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	Integer id;
	/**
	 * �������
	 * 
	 */

	@Column(name = "sequence")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer sequence;
	/**
	 * ���
	 * 
	 */

	@Column(name = "text", length = 20)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String text;

	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "item_id", referencedColumnName = "id") })
	@XmlTransient
	TAssignmentItem TAssignmentItem;
	/**
	 */
	@OneToMany(mappedBy = "TAssignmentItemtext", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.TAssignmentAnswer> TAssignmentAnswers;

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
	 * �������
	 * 
	 */
	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	/**
	 * �������
	 * 
	 */
	public Integer getSequence() {
		return this.sequence;
	}

	/**
	 * ���
	 * 
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * ���
	 * 
	 */
	public String getText() {
		return this.text;
	}

	/**
	 */
	public void setTAssignmentItem(TAssignmentItem TAssignmentItem) {
		this.TAssignmentItem = TAssignmentItem;
	}

	/**
	 */
	public TAssignmentItem getTAssignmentItem() {
		return TAssignmentItem;
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
	public TAssignmentItemtext() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(TAssignmentItemtext that) {
		setId(that.getId());
		setSequence(that.getSequence());
		setText(that.getText());
		setTAssignmentItem(that.getTAssignmentItem());
		setTAssignmentAnswers(new java.util.LinkedHashSet<net.xidlims.domain.TAssignmentAnswer>(that.getTAssignmentAnswers()));
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("id=[").append(id).append("] ");
		buffer.append("sequence=[").append(sequence).append("] ");
		buffer.append("text=[").append(text).append("] ");

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
		if (!(obj instanceof TAssignmentItemtext))
			return false;
		TAssignmentItemtext equalCheck = (TAssignmentItemtext) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
