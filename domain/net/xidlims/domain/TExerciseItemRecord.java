package net.xidlims.domain;

import java.io.Serializable;

import java.lang.StringBuilder;

import java.util.Calendar;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.codehaus.jackson.annotate.JsonIgnore;

import javax.xml.bind.annotation.*;

import javax.persistence.*;

/**
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "findAllTExerciseItemRecords", query = "select myTExerciseItemRecord from TExerciseItemRecord myTExerciseItemRecord"),
		@NamedQuery(name = "findTExerciseItemRecordByExerciseType", query = "select myTExerciseItemRecord from TExerciseItemRecord myTExerciseItemRecord where myTExerciseItemRecord.exerciseType = ?1"),
		@NamedQuery(name = "findTExerciseItemRecordByExerciseTypeContaining", query = "select myTExerciseItemRecord from TExerciseItemRecord myTExerciseItemRecord where myTExerciseItemRecord.exerciseType like ?1"),
		@NamedQuery(name = "findTExerciseItemRecordById", query = "select myTExerciseItemRecord from TExerciseItemRecord myTExerciseItemRecord where myTExerciseItemRecord.id = ?1"),
		@NamedQuery(name = "findTExerciseItemRecordByIscorrect", query = "select myTExerciseItemRecord from TExerciseItemRecord myTExerciseItemRecord where myTExerciseItemRecord.iscorrect = ?1"),
		@NamedQuery(name = "findTExerciseItemRecordByPrimaryKey", query = "select myTExerciseItemRecord from TExerciseItemRecord myTExerciseItemRecord where myTExerciseItemRecord.id = ?1") })
@Table(catalog = "xidlims", name = "t_exercise_item_record")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "TExerciseItemRecord")
@XmlRootElement(namespace = "xidlims/net/xidlims/domain")
public class TExerciseItemRecord implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ѧ����ϰ��¼
	 * 
	 */

	@Column(name = "id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	@XmlElement
	Integer id;
	/**
	 * ��ϰ���ͣ�orderΪ˳����ϰ��stochasticΪ�����ϰ��mistakeΪ������ϰ
	 * 
	 */

	@Column(name = "exercise_type")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String exerciseType;
	/**
	 * �����Ƿ���ȷ
	 * 
	 */

	@Column(name = "iscorrect")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer iscorrect;
	
	/**
	 * ����ʱ��
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar createDate;
	
	/**
	 * ����ʱ��
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modify_date")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar modifyDate;
	
	/**
	 * ����ʱ��
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "submit_date")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar submitDate;

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
	@OneToMany(mappedBy = "TExerciseItemRecord", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.TExerciseAnswerRecord> TExerciseAnswerRecords;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "question_id", referencedColumnName = "questionpool_id") })
	@XmlTransient
	TAssignmentQuestionpool TAssignmentQuestionpool;
	/**
	 * ѧ����ϰ��¼
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * ѧ����ϰ��¼
	 * 
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * ��ϰ���ͣ�orderΪ˳����ϰ��stochasticΪ�����ϰ��mistakeΪ������ϰ
	 * 
	 */
	public void setExerciseType(String exerciseType) {
		this.exerciseType = exerciseType;
	}

	/**
	 * ��ϰ���ͣ�orderΪ˳����ϰ��stochasticΪ�����ϰ��mistakeΪ������ϰ
	 * 
	 */
	public String getExerciseType() {
		return this.exerciseType;
	}

	/**
	 * �����Ƿ���ȷ
	 * 
	 */
	public void setIscorrect(Integer iscorrect) {
		this.iscorrect = iscorrect;
	}

	/**
	 * �����Ƿ���ȷ
	 * 
	 */
	public Integer getIscorrect() {
		return this.iscorrect;
	}

	public Calendar getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Calendar createDate) {
		this.createDate = createDate;
	}

	public Calendar getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Calendar modifyDate) {
		this.modifyDate = modifyDate;
	}

	public Calendar getSubmitDate() {
		return submitDate;
	}

	public void setSubmitDate(Calendar submitDate) {
		this.submitDate = submitDate;
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
	public void setTExerciseAnswerRecords(Set<TExerciseAnswerRecord> TExerciseAnswerRecords) {
		this.TExerciseAnswerRecords = TExerciseAnswerRecords;
	}

	/**
	 */
	@JsonIgnore
	public Set<TExerciseAnswerRecord> getTExerciseAnswerRecords() {
		if (TExerciseAnswerRecords == null) {
			TExerciseAnswerRecords = new java.util.LinkedHashSet<net.xidlims.domain.TExerciseAnswerRecord>();
		}
		return TExerciseAnswerRecords;
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
	public TExerciseItemRecord() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(TExerciseItemRecord that) {
		setId(that.getId());
		setExerciseType(that.getExerciseType());
		setIscorrect(that.getIscorrect());
		setTCourseSite(that.getTCourseSite());
		setTAssignmentItem(that.getTAssignmentItem());
		setUser(that.getUser());
		setTExerciseAnswerRecords(new java.util.LinkedHashSet<net.xidlims.domain.TExerciseAnswerRecord>(that.getTExerciseAnswerRecords()));
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("id=[").append(id).append("] ");
		buffer.append("exerciseType=[").append(exerciseType).append("] ");
		buffer.append("iscorrect=[").append(iscorrect).append("] ");

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
		if (!(obj instanceof TExerciseItemRecord))
			return false;
		TExerciseItemRecord equalCheck = (TExerciseItemRecord) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
