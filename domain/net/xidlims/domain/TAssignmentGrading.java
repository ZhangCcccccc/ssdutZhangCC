package net.xidlims.domain;

import java.io.Serializable;

import java.lang.StringBuilder;

import java.math.BigDecimal;

import java.util.Calendar;


import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import javax.xml.bind.annotation.*;

import javax.persistence.*;

/**
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "findAllTAssignmentGradings", query = "select myTAssignmentGrading from TAssignmentGrading myTAssignmentGrading"),
		@NamedQuery(name = "findTAssignmentGradingByAccessmentgradingId", query = "select myTAssignmentGrading from TAssignmentGrading myTAssignmentGrading where myTAssignmentGrading.accessmentgradingId = ?1"),
		@NamedQuery(name = "findTAssignmentGradingByComments", query = "select myTAssignmentGrading from TAssignmentGrading myTAssignmentGrading where myTAssignmentGrading.comments = ?1"),
		@NamedQuery(name = "findTAssignmentGradingByCommentsContaining", query = "select myTAssignmentGrading from TAssignmentGrading myTAssignmentGrading where myTAssignmentGrading.comments like ?1"),
		@NamedQuery(name = "findTAssignmentGradingByFinalScore", query = "select myTAssignmentGrading from TAssignmentGrading myTAssignmentGrading where myTAssignmentGrading.finalScore = ?1"),
		@NamedQuery(name = "findTAssignmentGradingByGradeTime", query = "select myTAssignmentGrading from TAssignmentGrading myTAssignmentGrading where myTAssignmentGrading.gradeTime = ?1"),
		@NamedQuery(name = "findTAssignmentGradingByIslate", query = "select myTAssignmentGrading from TAssignmentGrading myTAssignmentGrading where myTAssignmentGrading.islate = ?1"),
		@NamedQuery(name = "findTAssignmentGradingByPrimaryKey", query = "select myTAssignmentGrading from TAssignmentGrading myTAssignmentGrading where myTAssignmentGrading.accessmentgradingId = ?1"),
		@NamedQuery(name = "findTAssignmentGradingBySubmitdate", query = "select myTAssignmentGrading from TAssignmentGrading myTAssignmentGrading where myTAssignmentGrading.submitdate = ?1") })
@Table(catalog = "xidlims", name = "t_assignment_grading")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "test/net/xidlims/domain", name = "TAssignmentGrading")
public class TAssignmentGrading implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 */

	@Column(name = "accessmentgrading_id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	@XmlElement
	Integer accessmentgradingId;
	/**
	 * �ύʱ��
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "submitdate")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar submitdate;
	/**
	 * ���ʱ��
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "grade_time")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar gradeTime;
	/**
	 * ���յ÷�
	 * 
	 */

	@Column(name = "final_score", precision = 12)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal finalScore;
	/**
	 * �Ƿ�ٽ�0��1�ٽ�
	 * 
	 */

	@Column(name = "islate")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer islate;
	/**
	 * ����
	 * 
	 */

	@Column(name = "comments")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String comments;

	/**
	 * 提交的作业内容
	 */
	@Column(name = "content", columnDefinition = "TEXT")
	@Basic(fetch = FetchType.EAGER)
	@Lob
	@XmlElement
	String content;
	
	/**
	 * 作业提交次数，用于和提交次数限制作比较，0表示仅保存未提交，教师在提交作业列表中不能看到
	 */
	@Column(name = "submitTime",length = 2)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer submitTime;
	
	@Column(name = "gradeUrl")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String gradeUrl;
	
	/**
	 * 提交的作业分工
	 */
	@Column(name = "distribution", columnDefinition = "TEXT")
	@Basic(fetch = FetchType.EAGER)
	@Lob
	@XmlElement
	String distribution;
	
	/**
	 * 小组作业对应小组id
	 */
	@Column(name = "group_id")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer groupId;
	/**
	 * �Ƿ�ٽ�0��1�ٽ�
	 * 
	 */

	@Column(name = "attendence_type")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer attendenceType;

	/**
	 * ����
	 * 
	 */
	
	public void setAttendenceType(Integer attendenceType) {
		this.attendenceType = attendenceType;
	}

	/**
	 * �Ƿ�ٽ�0��1�ٽ�
	 * 
	 */
	public Integer getAttendenceType() {
		return this.attendenceType;
	}
	/**
	 * �Ƿ�ٽ�0��1�ٽ�
	 * 
	 */
	public String getGradeUrl() {
		return gradeUrl;
	}

	public void setGradeUrl(String gradeUrl) {
		this.gradeUrl = gradeUrl;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDistribution() {
		return distribution;
	}

	public void setDistribution(String distribution) {
		this.distribution = distribution;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public Integer getSubmitTime() {
		return submitTime;
	}

	public void setSubmitTime(Integer submitTime) {
		this.submitTime = submitTime;
	}

	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "student", referencedColumnName = "username") })
	@XmlTransient
	User userByStudent;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "grade_by", referencedColumnName = "username") })
	@XmlTransient
	User userByGradeBy;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "assignment_id", referencedColumnName = "id") })
	@XmlTransient
	TAssignment TAssignment;

	/**
	 */
	public void setAccessmentgradingId(Integer accessmentgradingId) {
		this.accessmentgradingId = accessmentgradingId;
	}

	/**
	 */
	public Integer getAccessmentgradingId() {
		return this.accessmentgradingId;
	}

	/**
	 * �ύʱ��
	 * 
	 */
	public void setSubmitdate(Calendar submitdate) {
		this.submitdate = submitdate;
	}

	/**
	 * �ύʱ��
	 * 
	 */
	public Calendar getSubmitdate() {
		return this.submitdate;
	}

	/**
	 * ���ʱ��
	 * 
	 */
	public void setGradeTime(Calendar gradeTime) {
		this.gradeTime = gradeTime;
	}

	/**
	 * ���ʱ��
	 * 
	 */
	public Calendar getGradeTime() {
		return this.gradeTime;
	}

	/**
	 * ���յ÷�
	 * 
	 */
	public void setFinalScore(BigDecimal finalScore) {
		this.finalScore = finalScore;
	}

	/**
	 * ���յ÷�
	 * 
	 */
	public BigDecimal getFinalScore() {
		return this.finalScore;
	}

	/**
	 * �Ƿ�ٽ�0��1�ٽ�
	 * 
	 */
	public void setIslate(Integer islate) {
		this.islate = islate;
	}

	/**
	 * �Ƿ�ٽ�0��1�ٽ�
	 * 
	 */
	public Integer getIslate() {
		return this.islate;
	}

	/**
	 * ����
	 * 
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}

	/**
	 * ����
	 * 
	 */
	public String getComments() {
		return this.comments;
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
	public void setUserByGradeBy(User userByGradeBy) {
		this.userByGradeBy = userByGradeBy;
	}

	/**
	 */
	public User getUserByGradeBy() {
		return userByGradeBy;
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
	public TAssignmentGrading() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(TAssignmentGrading that) {
		setAccessmentgradingId(that.getAccessmentgradingId());
		setSubmitdate(that.getSubmitdate());
		setGradeTime(that.getGradeTime());
		setFinalScore(that.getFinalScore());
		setIslate(that.getIslate());
		setComments(that.getComments());
		setContent(that.getContent());
		setSubmitTime(that.getSubmitTime());
		setUserByStudent(that.getUserByStudent());
		setUserByGradeBy(that.getUserByGradeBy());
		setTAssignment(that.getTAssignment());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("accessmentgradingId=[").append(accessmentgradingId).append("] ");
		buffer.append("submitdate=[").append(submitdate).append("] ");
		buffer.append("gradeTime=[").append(gradeTime).append("] ");
		buffer.append("finalScore=[").append(finalScore).append("] ");
		buffer.append("islate=[").append(islate).append("] ");
		buffer.append("comments=[").append(comments).append("] ");
		buffer.append("content=[").append(content).append("] ");
		buffer.append("submitTime=[").append(submitTime).append("] ");

		return buffer.toString();
	}

	/**
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((accessmentgradingId == null) ? 0 : accessmentgradingId.hashCode()));
		return result;
	}

	/**
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof TAssignmentGrading))
			return false;
		TAssignmentGrading equalCheck = (TAssignmentGrading) obj;
		if ((accessmentgradingId == null && equalCheck.accessmentgradingId != null) || (accessmentgradingId != null && equalCheck.accessmentgradingId == null))
			return false;
		if (accessmentgradingId != null && !accessmentgradingId.equals(equalCheck.accessmentgradingId))
			return false;
		return true;
	}
}
