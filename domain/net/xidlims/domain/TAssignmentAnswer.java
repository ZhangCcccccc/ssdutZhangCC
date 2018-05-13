package net.xidlims.domain;

import java.io.Serializable;

import java.lang.StringBuilder;

import java.math.BigDecimal;

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
		@NamedQuery(name = "findAllTAssignmentAnswers", query = "select myTAssignmentAnswer from TAssignmentAnswer myTAssignmentAnswer"),
		@NamedQuery(name = "findTAssignmentAnswerByGrade", query = "select myTAssignmentAnswer from TAssignmentAnswer myTAssignmentAnswer where myTAssignmentAnswer.grade = ?1"),
		@NamedQuery(name = "findTAssignmentAnswerByGradeContaining", query = "select myTAssignmentAnswer from TAssignmentAnswer myTAssignmentAnswer where myTAssignmentAnswer.grade like ?1"),
		@NamedQuery(name = "findTAssignmentAnswerById", query = "select myTAssignmentAnswer from TAssignmentAnswer myTAssignmentAnswer where myTAssignmentAnswer.id = ?1"),
		@NamedQuery(name = "findTAssignmentAnswerByIscorrect", query = "select myTAssignmentAnswer from TAssignmentAnswer myTAssignmentAnswer where myTAssignmentAnswer.iscorrect = ?1"),
		@NamedQuery(name = "findTAssignmentAnswerByLabel", query = "select myTAssignmentAnswer from TAssignmentAnswer myTAssignmentAnswer where myTAssignmentAnswer.label = ?1"),
		@NamedQuery(name = "findTAssignmentAnswerByLabelContaining", query = "select myTAssignmentAnswer from TAssignmentAnswer myTAssignmentAnswer where myTAssignmentAnswer.label like ?1"),
		@NamedQuery(name = "findTAssignmentAnswerByPrimaryKey", query = "select myTAssignmentAnswer from TAssignmentAnswer myTAssignmentAnswer where myTAssignmentAnswer.id = ?1"),
		@NamedQuery(name = "findTAssignmentAnswerByScore", query = "select myTAssignmentAnswer from TAssignmentAnswer myTAssignmentAnswer where myTAssignmentAnswer.score = ?1"),
		@NamedQuery(name = "findTAssignmentAnswerBySequence", query = "select myTAssignmentAnswer from TAssignmentAnswer myTAssignmentAnswer where myTAssignmentAnswer.sequence = ?1"),
		@NamedQuery(name = "findTAssignmentAnswerByText", query = "select myTAssignmentAnswer from TAssignmentAnswer myTAssignmentAnswer where myTAssignmentAnswer.text = ?1"),
		@NamedQuery(name = "findTAssignmentAnswerByTextContaining", query = "select myTAssignmentAnswer from TAssignmentAnswer myTAssignmentAnswer where myTAssignmentAnswer.text like ?1") })
@Table(catalog = "xidlims", name = "t_assignment_answer")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "TAssignmentAnswer")
public class TAssignmentAnswer implements Serializable {
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
	 * ����
	 * 
	 */

	@Column(name = "text")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String text;
	/**
	 * ������
	 * 
	 */

	@Column(name = "sequence")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer sequence;
	/**
	 * �𰸱������ѡ��ģ�A��B��C��D...
	 * 
	 */

	@Column(name = "label", length = 11)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String label;
	/**
	 * ״̬ 1����ȷ��0������
	 * 
	 */

	@Column(name = "iscorrect")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer iscorrect;
	/**
	 * �÷�
	 * 
	 */

	@Column(name = "grade", length = 50)
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
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "itemtext_id", referencedColumnName = "id") })
	@XmlTransient
	TAssignmentItemtext TAssignmentItemtext;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "item_id", referencedColumnName = "id") })
	@XmlTransient
	TAssignmentItem TAssignmentItem;

	/**
	 */
	@OneToMany(mappedBy = "TAssignmentAnswer", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.TAssignmentItemMapping> TAssignmentItemMappings;

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
	 * ����
	 * 
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * ����
	 * 
	 */
	public String getText() {
		return this.text;
	}

	/**
	 * ������
	 * 
	 */
	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	/**
	 * ������
	 * 
	 */
	public Integer getSequence() {
		return this.sequence;
	}

	/**
	 * �𰸱������ѡ��ģ�A��B��C��D...
	 * 
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * �𰸱������ѡ��ģ�A��B��C��D...
	 * 
	 */
	public String getLabel() {
		return this.label;
	}

	/**
	 * ״̬ 1����ȷ��0������
	 * 
	 */
	public void setIscorrect(Integer iscorrect) {
		this.iscorrect = iscorrect;
	}

	/**
	 * ״̬ 1����ȷ��0������
	 * 
	 */
	public Integer getIscorrect() {
		return this.iscorrect;
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
	 */
	public void setTAssignmentItemtext(TAssignmentItemtext TAssignmentItemtext) {
		this.TAssignmentItemtext = TAssignmentItemtext;
	}

	/**
	 */
	public TAssignmentItemtext getTAssignmentItemtext() {
		return TAssignmentItemtext;
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
	public TAssignmentAnswer() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(TAssignmentAnswer that) {
		setId(that.getId());
		setText(that.getText());
		setSequence(that.getSequence());
		setLabel(that.getLabel());
		setIscorrect(that.getIscorrect());
		setGrade(that.getGrade());
		setScore(that.getScore());
		setTAssignmentItemtext(that.getTAssignmentItemtext());
		setTAssignmentItem(that.getTAssignmentItem());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("id=[").append(id).append("] ");
		buffer.append("text=[").append(text).append("] ");
		buffer.append("sequence=[").append(sequence).append("] ");
		buffer.append("label=[").append(label).append("] ");
		buffer.append("iscorrect=[").append(iscorrect).append("] ");
		buffer.append("grade=[").append(grade).append("] ");
		buffer.append("score=[").append(score).append("] ");

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
		if (!(obj instanceof TAssignmentAnswer))
			return false;
		TAssignmentAnswer equalCheck = (TAssignmentAnswer) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
