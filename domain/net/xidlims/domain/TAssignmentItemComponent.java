package net.xidlims.domain;

import java.io.Serializable;

import java.lang.StringBuilder;

import java.math.BigDecimal;



import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import net.xidlims.domain.TAssignmentQuestionpool;

import org.codehaus.jackson.annotate.JsonIgnore;

import javax.xml.bind.annotation.*;

import javax.persistence.*;

/**
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "findAllTAssignmentItemComponents", query = "select myTAssignmentItemComponent from TAssignmentItemComponent myTAssignmentItemComponent"),
		@NamedQuery(name = "findTAssignmentItemComponentById", query = "select myTAssignmentItemComponent from TAssignmentItemComponent myTAssignmentItemComponent where myTAssignmentItemComponent.id = ?1"),
		@NamedQuery(name = "findTAssignmentItemComponentByItemQuantity", query = "select myTAssignmentItemComponent from TAssignmentItemComponent myTAssignmentItemComponent where myTAssignmentItemComponent.itemQuantity = ?1"),
		@NamedQuery(name = "findTAssignmentItemComponentByItemScore", query = "select myTAssignmentItemComponent from TAssignmentItemComponent myTAssignmentItemComponent where myTAssignmentItemComponent.itemScore = ?1"),
		@NamedQuery(name = "findTAssignmentItemComponentByItemType", query = "select myTAssignmentItemComponent from TAssignmentItemComponent myTAssignmentItemComponent where myTAssignmentItemComponent.itemType = ?1"),
		@NamedQuery(name = "findTAssignmentItemComponentByPrimaryKey", query = "select myTAssignmentItemComponent from TAssignmentItemComponent myTAssignmentItemComponent where myTAssignmentItemComponent.id = ?1") })
@Table(catalog = "xidlims", name = "t_assignment_item_component")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "TAssignmentItemComponent")
public class TAssignmentItemComponent implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ���Ե����⹹��q���
	 * 
	 */

	@Column(name = "id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	@XmlElement
	Integer id;
	/**
	 * �������ͣ�1��ѡ��2�Դ?4��ѡ��5����⣬8��գ�9ƥ��
	 * 
	 */

	@Column(name = "item_type")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer itemType;
	/**
	 * ĳ�����͵���������
	 * 
	 */

	@Column(name = "item_quantity")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer itemQuantity;
	/**
	 * ĳ�����͵������ڿ����Ծ��еķ�ֵ
	 * 
	 */

	@Column(name = "item_score", precision = 255)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal itemScore;

	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "t_assignment_id", referencedColumnName = "id") })
	@XmlTransient
	TAssignment TAssignment;
	
	@Column(name = "section_name")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String sectionName;
	
	public String getSectionName() {
		return sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}

	public TAssignmentQuestionpool getTAssignmentQuestionpool() {
		return TAssignmentQuestionpool;
	}

	public void setTAssignmentQuestionpool(
			TAssignmentQuestionpool tAssignmentQuestionpool) {
		TAssignmentQuestionpool = tAssignmentQuestionpool;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "t_questionpool_id", referencedColumnName = "questionpool_id") })
	@XmlTransient
	TAssignmentQuestionpool TAssignmentQuestionpool;

	/**
	 * ���Ե����⹹��q���
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * ���Ե����⹹��q���
	 * 
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * �������ͣ�1��ѡ��2�Դ?4��ѡ��5����⣬8��գ�9ƥ��
	 * 
	 */
	public void setItemType(Integer itemType) {
		this.itemType = itemType;
	}

	/**
	 * �������ͣ�1��ѡ��2�Դ?4��ѡ��5����⣬8��գ�9ƥ��
	 * 
	 */
	public Integer getItemType() {
		return this.itemType;
	}

	/**
	 * ĳ�����͵���������
	 * 
	 */
	public void setItemQuantity(Integer itemQuantity) {
		this.itemQuantity = itemQuantity;
	}

	/**
	 * ĳ�����͵���������
	 * 
	 */
	public Integer getItemQuantity() {
		return this.itemQuantity;
	}

	/**
	 * ĳ�����͵������ڿ����Ծ��еķ�ֵ
	 * 
	 */
	public void setItemScore(BigDecimal itemScore) {
		this.itemScore = itemScore;
	}

	/**
	 * ĳ�����͵������ڿ����Ծ��еķ�ֵ
	 * 
	 */
	public BigDecimal getItemScore() {
		return this.itemScore;
	}

	/**
	 */
	public void setTAssignment(TAssignment TAssignment) {
		this.TAssignment = TAssignment;
	}

	/**
	 */
	@JsonIgnore
	public TAssignment getTAssignment() {
		return TAssignment;
	}

	/**
	 */
	public TAssignmentItemComponent() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(TAssignmentItemComponent that) {
		setId(that.getId());
		setItemType(that.getItemType());
		setItemQuantity(that.getItemQuantity());
		setItemScore(that.getItemScore());
		setTAssignment(that.getTAssignment());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("id=[").append(id).append("] ");
		buffer.append("itemType=[").append(itemType).append("] ");
		buffer.append("itemQuantity=[").append(itemQuantity).append("] ");
		buffer.append("itemScore=[").append(itemScore).append("] ");

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
		if (!(obj instanceof TAssignmentItemComponent))
			return false;
		TAssignmentItemComponent equalCheck = (TAssignmentItemComponent) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
