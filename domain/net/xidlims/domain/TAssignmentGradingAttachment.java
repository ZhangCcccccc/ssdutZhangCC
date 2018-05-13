package net.xidlims.domain;

import java.io.Serializable;

import java.lang.StringBuilder;

import java.util.Calendar;

import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import javax.xml.bind.annotation.*;

import javax.persistence.*;

/**
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "findAllTAssignmentGradingAttachments", query = "select myTAssignmentGradingAttachment from TAssignmentGradingAttachment myTAssignmentGradingAttachment"),
		@NamedQuery(name = "findTAssignmentGradingAttachmentByCreatedBy", query = "select myTAssignmentGradingAttachment from TAssignmentGradingAttachment myTAssignmentGradingAttachment where myTAssignmentGradingAttachment.createdBy = ?1"),
		@NamedQuery(name = "findTAssignmentGradingAttachmentByCreatedByContaining", query = "select myTAssignmentGradingAttachment from TAssignmentGradingAttachment myTAssignmentGradingAttachment where myTAssignmentGradingAttachment.createdBy like ?1"),
		@NamedQuery(name = "findTAssignmentGradingAttachmentByCreatedTime", query = "select myTAssignmentGradingAttachment from TAssignmentGradingAttachment myTAssignmentGradingAttachment where myTAssignmentGradingAttachment.createdTime = ?1"),
		@NamedQuery(name = "findTAssignmentGradingAttachmentByGradeUrl", query = "select myTAssignmentGradingAttachment from TAssignmentGradingAttachment myTAssignmentGradingAttachment where myTAssignmentGradingAttachment.gradeUrl = ?1"),
		@NamedQuery(name = "findTAssignmentGradingAttachmentByGradeUrlContaining", query = "select myTAssignmentGradingAttachment from TAssignmentGradingAttachment myTAssignmentGradingAttachment where myTAssignmentGradingAttachment.gradeUrl like ?1"),
		@NamedQuery(name = "findTAssignmentGradingAttachmentByGroupId", query = "select myTAssignmentGradingAttachment from TAssignmentGradingAttachment myTAssignmentGradingAttachment where myTAssignmentGradingAttachment.groupId = ?1"),
		@NamedQuery(name = "findTAssignmentGradingAttachmentByPrimaryKey", query = "select myTAssignmentGradingAttachment from TAssignmentGradingAttachment myTAssignmentGradingAttachment where myTAssignmentGradingAttachment.TAssignmentGradingAttachmentId = ?1"),
		@NamedQuery(name = "findTAssignmentGradingAttachmentByTAssignmentGradingAttachmentId", query = "select myTAssignmentGradingAttachment from TAssignmentGradingAttachment myTAssignmentGradingAttachment where myTAssignmentGradingAttachment.TAssignmentGradingAttachmentId = ?1"),
		@NamedQuery(name = "findTAssignmentGradingAttachmentByTAssignmentGradingId", query = "select myTAssignmentGradingAttachment from TAssignmentGradingAttachment myTAssignmentGradingAttachment where myTAssignmentGradingAttachment.TAssignmentGradingId = ?1"),
		@NamedQuery(name = "findTAssignmentGradingAttachmentByType", query = "select myTAssignmentGradingAttachment from TAssignmentGradingAttachment myTAssignmentGradingAttachment where myTAssignmentGradingAttachment.type = ?1") })
@Table(catalog = "xidlims", name = "t_assignment_grading_attachment")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "TAssignmentGradingAttachment")
public class TAssignmentGradingAttachment implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 */

	@Column(name = "t_assignment_grading_attachment_id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@XmlElement
	Integer TAssignmentGradingAttachmentId;
	/**
	 * t_assignment_grading��id
	 * 
	 */

	@Column(name = "t_assignment_grading_id")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer TAssignmentGradingId;
	/**
	 * ����·��
	 * 
	 */

	@Column(name = "gradeUrl")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String gradeUrl;
	/**
	 * ������
	 * 
	 */

	@Column(name = "created_by", length = 50)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String createdBy;
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
	 * typeĬ��Ϊ1��С����ҵ����
	 * 
	 */

	@Column(name = "type")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer type;
	/**
	 * С��id
	 * 
	 */

	@Column(name = "group_id")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer groupId;

	/**
	 */
	public void setTAssignmentGradingAttachmentId(Integer TAssignmentGradingAttachmentId) {
		this.TAssignmentGradingAttachmentId = TAssignmentGradingAttachmentId;
	}

	/**
	 */
	public Integer getTAssignmentGradingAttachmentId() {
		return this.TAssignmentGradingAttachmentId;
	}

	/**
	 * t_assignment_grading��id
	 * 
	 */
	public void setTAssignmentGradingId(Integer TAssignmentGradingId) {
		this.TAssignmentGradingId = TAssignmentGradingId;
	}

	/**
	 * t_assignment_grading��id
	 * 
	 */
	public Integer getTAssignmentGradingId() {
		return this.TAssignmentGradingId;
	}

	/**
	 * ����·��
	 * 
	 */
	public void setGradeUrl(String gradeUrl) {
		this.gradeUrl = gradeUrl;
	}

	/**
	 * ����·��
	 * 
	 */
	public String getGradeUrl() {
		return this.gradeUrl;
	}

	/**
	 * ������
	 * 
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * ������
	 * 
	 */
	public String getCreatedBy() {
		return this.createdBy;
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
	 * typeĬ��Ϊ1��С����ҵ����
	 * 
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * typeĬ��Ϊ1��С����ҵ����
	 * 
	 */
	public Integer getType() {
		return this.type;
	}

	/**
	 * С��id
	 * 
	 */
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	/**
	 * С��id
	 * 
	 */
	public Integer getGroupId() {
		return this.groupId;
	}

	/**
	 */
	public TAssignmentGradingAttachment() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(TAssignmentGradingAttachment that) {
		setTAssignmentGradingAttachmentId(that.getTAssignmentGradingAttachmentId());
		setTAssignmentGradingId(that.getTAssignmentGradingId());
		setGradeUrl(that.getGradeUrl());
		setCreatedBy(that.getCreatedBy());
		setCreatedTime(that.getCreatedTime());
		setType(that.getType());
		setGroupId(that.getGroupId());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("TAssignmentGradingAttachmentId=[").append(TAssignmentGradingAttachmentId).append("] ");
		buffer.append("TAssignmentGradingId=[").append(TAssignmentGradingId).append("] ");
		buffer.append("gradeUrl=[").append(gradeUrl).append("] ");
		buffer.append("createdBy=[").append(createdBy).append("] ");
		buffer.append("createdTime=[").append(createdTime).append("] ");
		buffer.append("type=[").append(type).append("] ");
		buffer.append("groupId=[").append(groupId).append("] ");

		return buffer.toString();
	}

	/**
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((TAssignmentGradingAttachmentId == null) ? 0 : TAssignmentGradingAttachmentId.hashCode()));
		return result;
	}

	/**
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof TAssignmentGradingAttachment))
			return false;
		TAssignmentGradingAttachment equalCheck = (TAssignmentGradingAttachment) obj;
		if ((TAssignmentGradingAttachmentId == null && equalCheck.TAssignmentGradingAttachmentId != null) || (TAssignmentGradingAttachmentId != null && equalCheck.TAssignmentGradingAttachmentId == null))
			return false;
		if (TAssignmentGradingAttachmentId != null && !TAssignmentGradingAttachmentId.equals(equalCheck.TAssignmentGradingAttachmentId))
			return false;
		return true;
	}
}
