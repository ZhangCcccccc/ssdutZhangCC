package net.xidlims.domain;

import java.io.Serializable;

import java.lang.StringBuilder;

import java.math.BigDecimal;

import java.util.Calendar;


import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.FetchType;
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
		@NamedQuery(name = "findAllTAssignmentItemMappings", query = "select myTAssignmentItemMapping from TAssignmentItemMapping myTAssignmentItemMapping"),
		@NamedQuery(name = "findTAssignmentItemMappingByAutoscore", query = "select myTAssignmentItemMapping from TAssignmentItemMapping myTAssignmentItemMapping where myTAssignmentItemMapping.autoscore = ?1"),
		@NamedQuery(name = "findTAssignmentItemMappingByComments", query = "select myTAssignmentItemMapping from TAssignmentItemMapping myTAssignmentItemMapping where myTAssignmentItemMapping.comments = ?1"),
		@NamedQuery(name = "findTAssignmentItemMappingByCommentsContaining", query = "select myTAssignmentItemMapping from TAssignmentItemMapping myTAssignmentItemMapping where myTAssignmentItemMapping.comments like ?1"),
		@NamedQuery(name = "findTAssignmentItemMappingByGradetime", query = "select myTAssignmentItemMapping from TAssignmentItemMapping myTAssignmentItemMapping where myTAssignmentItemMapping.gradetime = ?1"),
		@NamedQuery(name = "findTAssignmentItemMappingById", query = "select myTAssignmentItemMapping from TAssignmentItemMapping myTAssignmentItemMapping where myTAssignmentItemMapping.id = ?1"),
		@NamedQuery(name = "findTAssignmentItemMappingByOveriderScore", query = "select myTAssignmentItemMapping from TAssignmentItemMapping myTAssignmentItemMapping where myTAssignmentItemMapping.overiderScore = ?1"),
		@NamedQuery(name = "findTAssignmentItemMappingByPrimaryKey", query = "select myTAssignmentItemMapping from TAssignmentItemMapping myTAssignmentItemMapping where myTAssignmentItemMapping.id = ?1"),
		@NamedQuery(name = "findTAssignmentItemMappingBySubmitDate", query = "select myTAssignmentItemMapping from TAssignmentItemMapping myTAssignmentItemMapping where myTAssignmentItemMapping.submitDate = ?1") })
@Table(catalog = "xidlims", name = "t_assignment_item_mapping")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "TAssignmentItemMapping")
public class TAssignmentItemMapping implements Serializable {
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
	 * 记录填空题答题记录
	 * 
	 */
	@Column(name = "answer_text")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String answerText;
	/**
	 * �ύ����
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "submit_date")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar submitDate;
	/**
	 * �Զ�����ķ���
	 * 
	 */

	@Column(name = "autoscore", precision = 12)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal autoscore;
	/**
	 * �޶��ķ���
	 * 
	 */

	@Column(name = "overider_score", precision = 12)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal overiderScore;
	/**
	 * �������
	 * 
	 */

	/**
	 * 作业提交次数，用于和提交次数限制作比较，0表示仅保存未提交，教师在提交作业列表中不能看到
	 */
	@Column(name = "submitTime",length = 2)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer submitTime;
	
	@Column(name = "comments")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String comments;
	/**
	 * ���ʱ��
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "gradetime")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar gradetime;

	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "student", referencedColumnName = "username") })
	@XmlTransient
	User userByStudent;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "item_id", referencedColumnName = "id") })
	@XmlTransient
	TAssignmentItem TAssignmentItem;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "assignment_id", referencedColumnName = "id") })
	@XmlTransient
	TAssignment TAssignment;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "gradeby", referencedColumnName = "username") })
	@XmlTransient
	User userByGradeby;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "answer_id", referencedColumnName = "id") })
	@XmlTransient
	TAssignmentAnswer TAssignmentAnswer;

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

	public String getAnswerText() {
		return answerText;
	}

	public void setAnswerText(String answerText) {
		this.answerText = answerText;
	}

	/**
	 * �ύ����
	 * 
	 */
	public void setSubmitDate(Calendar submitDate) {
		this.submitDate = submitDate;
	}

	/**
	 * �ύ����
	 * 
	 */
	public Calendar getSubmitDate() {
		return this.submitDate;
	}

	/**
	 * �Զ�����ķ���
	 * 
	 */
	public void setAutoscore(BigDecimal autoscore) {
		this.autoscore = autoscore;
	}

	/**
	 * �Զ�����ķ���
	 * 
	 */
	public BigDecimal getAutoscore() {
		return this.autoscore;
	}

	/**
	 * �޶��ķ���
	 * 
	 */
	public void setOveriderScore(BigDecimal overiderScore) {
		this.overiderScore = overiderScore;
	}

	/**
	 * �޶��ķ���
	 * 
	 */
	public BigDecimal getOveriderScore() {
		return this.overiderScore;
	}

	/**
	 * �������
	 * 
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}

	/**
	 * �������
	 * 
	 */
	public String getComments() {
		return this.comments;
	}

	/**
	 * ���ʱ��
	 * 
	 */
	public void setGradetime(Calendar gradetime) {
		this.gradetime = gradetime;
	}

	/**
	 * ���ʱ��
	 * 
	 */
	public Calendar getGradetime() {
		return this.gradetime;
	}

	/**
	 */
	public void setUserByStudent(User userByStudent) {
		this.userByStudent = userByStudent;
	}

	/**
	 */
	public User getUserByStudent() {
		return userByStudent;
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
	public void setUserByGradeby(User userByGradeby) {
		this.userByGradeby = userByGradeby;
	}

	/**
	 */
	public User getUserByGradeby() {
		return userByGradeby;
	}

	/**
	 */
	public void setTAssignmentAnswer(TAssignmentAnswer TAssignmentAnswer) {
		this.TAssignmentAnswer = TAssignmentAnswer;
	}

	/**
	 */
	public TAssignmentAnswer getTAssignmentAnswer() {
		return TAssignmentAnswer;
	}

	/**
	 */
	public TAssignmentItemMapping() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(TAssignmentItemMapping that) {
		setId(that.getId());
		setSubmitDate(that.getSubmitDate());
		setAutoscore(that.getAutoscore());
		setOveriderScore(that.getOveriderScore());
		setComments(that.getComments());
		setGradetime(that.getGradetime());
		setUserByStudent(that.getUserByStudent());
		setTAssignmentItem(that.getTAssignmentItem());
		setTAssignment(that.getTAssignment());
		setUserByGradeby(that.getUserByGradeby());
		setTAssignmentAnswer(that.getTAssignmentAnswer());
	}

	public Integer getSubmitTime() {
		return submitTime;
	}

	public void setSubmitTime(Integer submitTime) {
		this.submitTime = submitTime;
	}
	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("id=[").append(id).append("] ");
		buffer.append("submitDate=[").append(submitDate).append("] ");
		buffer.append("autoscore=[").append(autoscore).append("] ");
		buffer.append("overiderScore=[").append(overiderScore).append("] ");
		buffer.append("comments=[").append(comments).append("] ");
		buffer.append("gradetime=[").append(gradetime).append("] ");

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
		if (!(obj instanceof TAssignmentItemMapping))
			return false;
		TAssignmentItemMapping equalCheck = (TAssignmentItemMapping) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
