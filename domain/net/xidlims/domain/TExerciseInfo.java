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
		@NamedQuery(name = "findAllTExerciseInfos", query = "select myTExerciseInfo from TExerciseInfo myTExerciseInfo"),
		@NamedQuery(name = "findTExerciseInfoById", query = "select myTExerciseInfo from TExerciseInfo myTExerciseInfo where myTExerciseInfo.id = ?1"),
		@NamedQuery(name = "findTExerciseInfoByMistakeNumber", query = "select myTExerciseInfo from TExerciseInfo myTExerciseInfo where myTExerciseInfo.mistakeNumber = ?1"),
		@NamedQuery(name = "findTExerciseInfoByOrderNumber", query = "select myTExerciseInfo from TExerciseInfo myTExerciseInfo where myTExerciseInfo.orderNumber = ?1"),
		@NamedQuery(name = "findTExerciseInfoByPrimaryKey", query = "select myTExerciseInfo from TExerciseInfo myTExerciseInfo where myTExerciseInfo.id = ?1"),
		@NamedQuery(name = "findTExerciseInfoByStochasticNumber", query = "select myTExerciseInfo from TExerciseInfo myTExerciseInfo where myTExerciseInfo.stochasticNumber = ?1"),
		@NamedQuery(name = "findTExerciseInfoByStochasticString", query = "select myTExerciseInfo from TExerciseInfo myTExerciseInfo where myTExerciseInfo.stochasticString = ?1") })
@Table(catalog = "xidlims", name = "t_exercise_info")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "TExerciseInfo")
public class TExerciseInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ѧ����ϰ����Ϣ
	 * 
	 */

	@Column(name = "id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	@XmlElement
	Integer id;
	/**
	 * �����ϰ�������ţ�ֻ���һ��
	 * 
	 */

	@Column(name = "stochasticString", columnDefinition = "TEXT")
	@Basic(fetch = FetchType.EAGER)
	@Lob
	@XmlElement
	String stochasticString;
	
	@Column(name = "singleStochasticString", columnDefinition = "TEXT")
	@Basic(fetch = FetchType.EAGER)
	@Lob
	@XmlElement
	String singleStochasticString;
	
	@Column(name = "multipleStochasticString", columnDefinition = "TEXT")
	@Basic(fetch = FetchType.EAGER)
	@Lob
	@XmlElement
	String  multipleStochasticString;
	
	@Column(name = "blankStochasticString", columnDefinition = "TEXT")
	@Basic(fetch = FetchType.EAGER)
	@Lob
	@XmlElement
	String blankStochasticString;

	/**
	 * ˳����������λ��
	 * 
	 */

	@Column(name = "order_number")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer orderNumber;
	/**
	 * �������λ�ã�����id
	 * 
	 */

	@Column(name = "stochastic_number")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer stochasticNumber;
	/**
	 * ����������
	 * 
	 */

	@Column(name = "mistake_number")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer mistakeNumber;

	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "site_id", referencedColumnName = "id") })
	@XmlTransient
	TCourseSite TCourseSite;
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
	 * ѧ����ϰ����Ϣ
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * ѧ����ϰ����Ϣ
	 * 
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * �����ϰ�������ţ�ֻ���һ��
	 * 
	 */
	public void setStochasticString(String stochasticString) {
		this.stochasticString = stochasticString;
	}

	/**
	 * �����ϰ�������ţ�ֻ���һ��
	 * 
	 */
	public String getStochasticString() {
		return this.stochasticString;
	}

	public String getSingleStochasticString() {
		return singleStochasticString;
	}

	public void setSingleStochasticString(String singleStochasticString) {
		this.singleStochasticString = singleStochasticString;
	}

	public String getMultipleStochasticString() {
		return multipleStochasticString;
	}

	public void setMultipleStochasticString(String multipleStochasticString) {
		this.multipleStochasticString = multipleStochasticString;
	}

	public String getBlankStochasticString() {
		return blankStochasticString;
	}

	public void setBlankStochasticString(String blankStochasticString) {
		this.blankStochasticString = blankStochasticString;
	}

	/**
	 * ˳����������λ��
	 * 
	 */
	public void setOrderNumber(Integer orderNumber) {
		this.orderNumber = orderNumber;
	}

	/**
	 * ˳����������λ��
	 * 
	 */
	public Integer getOrderNumber() {
		return this.orderNumber;
	}

	/**
	 * �������λ�ã�����id
	 * 
	 */
	public void setStochasticNumber(Integer stochasticNumber) {
		this.stochasticNumber = stochasticNumber;
	}

	/**
	 * �������λ�ã�����id
	 * 
	 */
	public Integer getStochasticNumber() {
		return this.stochasticNumber;
	}

	/**
	 * ����������
	 * 
	 */
	public void setMistakeNumber(Integer mistakeNumber) {
		this.mistakeNumber = mistakeNumber;
	}

	/**
	 * ����������
	 * 
	 */
	public Integer getMistakeNumber() {
		return this.mistakeNumber;
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
	public TExerciseInfo() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(TExerciseInfo that) {
		setId(that.getId());
		setStochasticString(that.getStochasticString());
		setOrderNumber(that.getOrderNumber());
		setStochasticNumber(that.getStochasticNumber());
		setMistakeNumber(that.getMistakeNumber());
		setTCourseSite(that.getTCourseSite());
		setUser(that.getUser());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("id=[").append(id).append("] ");
		buffer.append("stochasticString=[").append(stochasticString).append("] ");
		buffer.append("orderNumber=[").append(orderNumber).append("] ");
		buffer.append("stochasticNumber=[").append(stochasticNumber).append("] ");
		buffer.append("mistakeNumber=[").append(mistakeNumber).append("] ");

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
		if (!(obj instanceof TExerciseInfo))
			return false;
		TExerciseInfo equalCheck = (TExerciseInfo) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
