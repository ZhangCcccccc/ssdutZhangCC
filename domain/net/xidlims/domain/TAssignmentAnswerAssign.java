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
		@NamedQuery(name = "findAllTAssignmentAnswerAssigns", query = "select myTAssignmentAnswerAssign from TAssignmentAnswerAssign myTAssignmentAnswerAssign"),
		@NamedQuery(name = "findTAssignmentAnswerAssignByContent", query = "select myTAssignmentAnswerAssign from TAssignmentAnswerAssign myTAssignmentAnswerAssign where myTAssignmentAnswerAssign.content = ?1"),
		@NamedQuery(name = "findTAssignmentAnswerAssignByGrade", query = "select myTAssignmentAnswerAssign from TAssignmentAnswerAssign myTAssignmentAnswerAssign where myTAssignmentAnswerAssign.grade = ?1"),
		@NamedQuery(name = "findTAssignmentAnswerAssignById", query = "select myTAssignmentAnswerAssign from TAssignmentAnswerAssign myTAssignmentAnswerAssign where myTAssignmentAnswerAssign.id = ?1"),
		@NamedQuery(name = "findTAssignmentAnswerAssignByPrimaryKey", query = "select myTAssignmentAnswerAssign from TAssignmentAnswerAssign myTAssignmentAnswerAssign where myTAssignmentAnswerAssign.id = ?1"),
		@NamedQuery(name = "findTAssignmentAnswerAssignByReference", query = "select myTAssignmentAnswerAssign from TAssignmentAnswerAssign myTAssignmentAnswerAssign where myTAssignmentAnswerAssign.reference = ?1"),
		@NamedQuery(name = "findTAssignmentAnswerAssignByScore", query = "select myTAssignmentAnswerAssign from TAssignmentAnswerAssign myTAssignmentAnswerAssign where myTAssignmentAnswerAssign.score = ?1"),
		@NamedQuery(name = "findTAssignmentAnswerAssignByScoreDate", query = "select myTAssignmentAnswerAssign from TAssignmentAnswerAssign myTAssignmentAnswerAssign where myTAssignmentAnswerAssign.scoreDate = ?1") })
@Table(catalog = "xidlims", name = "t_assignment_answer_assign")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "test/net/xidlims/domain", name = "TAssignmentAnswerAssign")
public class TAssignmentAnswerAssign implements Serializable {
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
	 * ��ֵ
	 * 
	 */

	@Column(name = "score", scale = 2, precision = 5)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal score;
	/**
	 * �÷�
	 * 
	 */

	@Column(name = "grade", scale = 2, precision = 5)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal grade;
	/**
	 * �ش�����
	 * 
	 */

	@Column(name = "content", columnDefinition = "TEXT")
	@Basic(fetch = FetchType.EAGER)
	@Lob
	@XmlElement
	String content;
	/**
	 * �ο���
	 * 
	 */

	@Column(name = "reference", columnDefinition = "TEXT")
	@Basic(fetch = FetchType.EAGER)
	@Lob
	@XmlElement
	String reference;
	/**
	 * ���ʱ��
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "score_date")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar scoreDate;

	/**
	 */
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "assignment_id", referencedColumnName = "id") })
	@XmlTransient
	TAssignment TAssignment;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "teacher", referencedColumnName = "username") })
	@XmlTransient
	User user;
	/**
	 */
	@Column(name = "late_score")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer lateScore;
	/**
	 */
	@Column(name = "leave_score")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer leaveScore;
	/**
	 */
	@Column(name = "truant_score")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer truantScore;
	/**
	 */
	@Column(name = "early_score")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer earlyScore;
	

	public Integer getEarlyScore() {
		return earlyScore;
	}

	public void setEarlyScore(Integer earlyScore) {
		this.earlyScore = earlyScore;
	}

	public Integer getTruantScore() {
		return truantScore;
	}

	public void setTruantScore(Integer truantScore) {
		this.truantScore = truantScore;
	}


	public Integer getLeaveScore() {
		return leaveScore;
	}

	public void setLeaveScore(Integer leaveScore) {
		this.leaveScore = leaveScore;
	}


	public Integer getLateScore() {
		return lateScore;
	}

	public void setLateScore(Integer lateScore) {
		this.lateScore = lateScore;
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
	 * �÷�
	 * 
	 */
	public void setGrade(BigDecimal grade) {
		this.grade = grade;
	}

	/**
	 * �÷�
	 * 
	 */
	public BigDecimal getGrade() {
		return this.grade;
	}

	/**
	 * �ش�����
	 * 
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * �ش�����
	 * 
	 */
	public String getContent() {
		return this.content;
	}

	/**
	 * �ο���
	 * 
	 */
	public void setReference(String reference) {
		this.reference = reference;
	}

	/**
	 * �ο���
	 * 
	 */
	public String getReference() {
		return this.reference;
	}

	/**
	 * ���ʱ��
	 * 
	 */
	public void setScoreDate(Calendar scoreDate) {
		this.scoreDate = scoreDate;
	}

	/**
	 * ���ʱ��
	 * 
	 */
	public Calendar getScoreDate() {
		return this.scoreDate;
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
	public TAssignmentAnswerAssign() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(TAssignmentAnswerAssign that) {
		setId(that.getId());
		setScore(that.getScore());
		setGrade(that.getGrade());
		setContent(that.getContent());
		setReference(that.getReference());
		setScoreDate(that.getScoreDate());
		setTAssignment(that.getTAssignment());
		setUser(that.getUser());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("id=[").append(id).append("] ");
		buffer.append("score=[").append(score).append("] ");
		buffer.append("grade=[").append(grade).append("] ");
		buffer.append("content=[").append(content).append("] ");
		buffer.append("reference=[").append(reference).append("] ");
		buffer.append("scoreDate=[").append(scoreDate).append("] ");

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
		if (!(obj instanceof TAssignmentAnswerAssign))
			return false;
		TAssignmentAnswerAssign equalCheck = (TAssignmentAnswerAssign) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
