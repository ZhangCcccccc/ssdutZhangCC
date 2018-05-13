package net.xidlims.domain;

import java.io.Serializable;

import java.lang.StringBuilder;

import java.util.Set;

import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.codehaus.jackson.annotate.JsonIgnore;

import javax.xml.bind.annotation.*;

import javax.persistence.*;

/**
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "findAllTExerciseAnswerRecords", query = "select myTExerciseAnswerRecord from TExerciseAnswerRecord myTExerciseAnswerRecord"),
		@NamedQuery(name = "findTExerciseAnswerRecordByAnswerText", query = "select myTExerciseAnswerRecord from TExerciseAnswerRecord myTExerciseAnswerRecord where myTExerciseAnswerRecord.answerText = ?1"),
		@NamedQuery(name = "findTExerciseAnswerRecordByAnswerTextContaining", query = "select myTExerciseAnswerRecord from TExerciseAnswerRecord myTExerciseAnswerRecord where myTExerciseAnswerRecord.answerText like ?1"),
		@NamedQuery(name = "findTExerciseAnswerRecordById", query = "select myTExerciseAnswerRecord from TExerciseAnswerRecord myTExerciseAnswerRecord where myTExerciseAnswerRecord.id = ?1"),
		@NamedQuery(name = "findTExerciseAnswerRecordByPrimaryKey", query = "select myTExerciseAnswerRecord from TExerciseAnswerRecord myTExerciseAnswerRecord where myTExerciseAnswerRecord.id = ?1") })
@Table(catalog = "xidlims", name = "t_exercise_answer_record")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "TExerciseAnswerRecord")
public class TExerciseAnswerRecord implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ѧ����ϰ�𰸼�¼
	 * 
	 */

	@Column(name = "id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	@XmlElement
	Integer id;
	/**
	 * ������������
	 * 
	 */

	@Column(name = "answer_text")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String answerText;

	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "item_record_id", referencedColumnName = "id") })
	@XmlTransient
	TExerciseItemRecord TExerciseItemRecord;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "answer_id", referencedColumnName = "id") })
	@XmlTransient
	TAssignmentAnswer TAssignmentAnswer;

	/**
	 * ѧ����ϰ�𰸼�¼
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * ѧ����ϰ�𰸼�¼
	 * 
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * ������������
	 * 
	 */
	public void setAnswerText(String answerText) {
		this.answerText = answerText;
	}

	/**
	 * ������������
	 * 
	 */
	public String getAnswerText() {
		return this.answerText;
	}

	/**
	 */
	public void setTExerciseItemRecord(TExerciseItemRecord TExerciseItemRecord) {
		this.TExerciseItemRecord = TExerciseItemRecord;
	}

	/**
	 */
	@JsonIgnore
	public TExerciseItemRecord getTExerciseItemRecord() {
		return TExerciseItemRecord;
	}

	/**
	 */
	public void setTAssignmentAnswer(TAssignmentAnswer TAssignmentAnswer) {
		this.TAssignmentAnswer = TAssignmentAnswer;
	}

	/**
	 */
	@JsonIgnore
	public TAssignmentAnswer getTAssignmentAnswer() {
		return TAssignmentAnswer;
	}

	/**
	 */
	public TExerciseAnswerRecord() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(TExerciseAnswerRecord that) {
		setId(that.getId());
		setAnswerText(that.getAnswerText());
		setTExerciseItemRecord(that.getTExerciseItemRecord());
		setTAssignmentAnswer(that.getTAssignmentAnswer());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("id=[").append(id).append("] ");
		buffer.append("answerText=[").append(answerText).append("] ");

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
		if (!(obj instanceof TExerciseAnswerRecord))
			return false;
		TExerciseAnswerRecord equalCheck = (TExerciseAnswerRecord) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
