package net.xidlims.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "findAllTExperimentSkills", query = "select myTExperimentSkill from TExperimentSkill myTExperimentSkill"),
		@NamedQuery(name = "findTExperimentSkillByChapterId", query = "select myTExperimentSkill from TExperimentSkill myTExperimentSkill where myTExperimentSkill.chapterId = ?1"),
		@NamedQuery(name = "findTExperimentSkillByCreatedBy", query = "select myTExperimentSkill from TExperimentSkill myTExperimentSkill where myTExperimentSkill.createdBy = ?1"),
		@NamedQuery(name = "findTExperimentSkillByCreatedByContaining", query = "select myTExperimentSkill from TExperimentSkill myTExperimentSkill where myTExperimentSkill.createdBy like ?1"),
		@NamedQuery(name = "findTExperimentSkillByCreatedTime", query = "select myTExperimentSkill from TExperimentSkill myTExperimentSkill where myTExperimentSkill.createdTime = ?1"),
		@NamedQuery(name = "findTExperimentSkillByDuedate", query = "select myTExperimentSkill from TExperimentSkill myTExperimentSkill where myTExperimentSkill.duedate = ?1"),
		@NamedQuery(name = "findTExperimentSkillByExperimentDescribe", query = "select myTExperimentSkill from TExperimentSkill myTExperimentSkill where myTExperimentSkill.experimentDescribe = ?1"),
		@NamedQuery(name = "findTExperimentSkillByExperimentGoal", query = "select myTExperimentSkill from TExperimentSkill myTExperimentSkill where myTExperimentSkill.experimentGoal = ?1"),
		@NamedQuery(name = "findTExperimentSkillByExperimentGoalContaining", query = "select myTExperimentSkill from TExperimentSkill myTExperimentSkill where myTExperimentSkill.experimentGoal like ?1"),
		@NamedQuery(name = "findTExperimentSkillByExperimentName", query = "select myTExperimentSkill from TExperimentSkill myTExperimentSkill where myTExperimentSkill.experimentName = ?1"),
		@NamedQuery(name = "findTExperimentSkillByExperimentNameContaining", query = "select myTExperimentSkill from TExperimentSkill myTExperimentSkill where myTExperimentSkill.experimentName like ?1"),
		@NamedQuery(name = "findTExperimentSkillByExperimentNo", query = "select myTExperimentSkill from TExperimentSkill myTExperimentSkill where myTExperimentSkill.experimentNo = ?1"),
		@NamedQuery(name = "findTExperimentSkillByExperimentNoContaining", query = "select myTExperimentSkill from TExperimentSkill myTExperimentSkill where myTExperimentSkill.experimentNo like ?1"),
		@NamedQuery(name = "findTExperimentSkillByExperimentStatus", query = "select myTExperimentSkill from TExperimentSkill myTExperimentSkill where myTExperimentSkill.experimentStatus = ?1"),
		@NamedQuery(name = "findTExperimentSkillByExperimentVersion", query = "select myTExperimentSkill from TExperimentSkill myTExperimentSkill where myTExperimentSkill.experimentVersion = ?1"),
		@NamedQuery(name = "findTExperimentSkillByExperimentVersionContaining", query = "select myTExperimentSkill from TExperimentSkill myTExperimentSkill where myTExperimentSkill.experimentVersion like ?1"),
		@NamedQuery(name = "findTExperimentSkillById", query = "select myTExperimentSkill from TExperimentSkill myTExperimentSkill where myTExperimentSkill.id = ?1"),
		@NamedQuery(name = "findTExperimentSkillByPrimaryKey", query = "select myTExperimentSkill from TExperimentSkill myTExperimentSkill where myTExperimentSkill.id = ?1"),
		@NamedQuery(name = "findTExperimentSkillBySiteId", query = "select myTExperimentSkill from TExperimentSkill myTExperimentSkill where myTExperimentSkill.siteId = ?1"),
		@NamedQuery(name = "findTExperimentSkillBySort", query = "select myTExperimentSkill from TExperimentSkill myTExperimentSkill where myTExperimentSkill.sort = ?1"),
		@NamedQuery(name = "findTExperimentSkillByStartdate", query = "select myTExperimentSkill from TExperimentSkill myTExperimentSkill where myTExperimentSkill.startdate = ?1") })
@Table(catalog = "xidlims", name = "t_experiment_skill")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "TExperimentSkill")
public class TExperimentSkill implements Serializable {
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
	 * �����γ�վ��id
	 * 
	 */

	@Column(name = "site_id")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer siteId;
	/**
	 * �����½�id
	 * 
	 */

	@Column(name = "chapter_id")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer chapterId;
	/**
	 * ʵ�����
	 * 
	 */

	@Column(name = "experiment_name")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String experimentName;
	/**
	 * ʵ����
	 * 
	 */

	@Column(name = "experiment_no")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String experimentNo;
	/**
	 * �Ƿ�����
	 * 
	 */

	@Column(name = "experiment_status")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer experimentStatus;
	
	/**
	 * ʵ������
	 * 
	 */

	@Column(name = "experiment_type")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer experimentType;
	
	/**
	 * ʵ������
	 * 
	 */

	@Column(name = "experiment_isopen")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer experimentIsopen;
	
	public Integer getExperimentType() {
		return experimentType;
	}

	public void setExperimentType(Integer experimentType) {
		this.experimentType = experimentType;
	}

	public Integer getExperimentIsopen() {
		return experimentIsopen;
	}

	public void setExperimentIsopen(Integer experimentIsopen) {
		this.experimentIsopen = experimentIsopen;
	}

	/**
	 * ʵ��汾
	 * 
	 */

	@Column(name = "experiment_version")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String experimentVersion;
	/**
	 * ʵ��Ŀ��
	 * 
	 */

	@Column(name = "experiment_goal")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String experimentGoal;
	/**
	 * ʵ������
	 * 
	 */

	@Column(name = "experiment_describe", columnDefinition = "TEXT")
	@Basic(fetch = FetchType.EAGER)
	@Lob
	@XmlElement
	String experimentDescribe;
	/**
	 * ����������user
	 * 
	 */

	@Column(name = "created_by")
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
	 * ��ʼʱ��
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "startdate")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar startdate;
	/**
	 * ����ʱ��
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "duedate")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar duedate;
	/**
	 * ����
	 * 
	 */

	@Column(name = "sort")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer sort;
	
	@Column(name = "weight", scale = 2, precision = 11)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal weight;
	

	public BigDecimal getWeight() {
		return weight;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
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
	 * �����γ�վ��id
	 * 
	 */
	public void setSiteId(Integer siteId) {
		this.siteId = siteId;
	}

	/**
	 * �����γ�վ��id
	 * 
	 */
	public Integer getSiteId() {
		return this.siteId;
	}

	/**
	 * �����½�id
	 * 
	 */
	public void setChapterId(Integer chapterId) {
		this.chapterId = chapterId;
	}

	/**
	 * �����½�id
	 * 
	 */
	public Integer getChapterId() {
		return this.chapterId;
	}

	/**
	 * ʵ�����
	 * 
	 */
	public void setExperimentName(String experimentName) {
		this.experimentName = experimentName;
	}

	/**
	 * ʵ�����
	 * 
	 */
	public String getExperimentName() {
		return this.experimentName;
	}

	/**
	 * ʵ����
	 * 
	 */
	public void setExperimentNo(String experimentNo) {
		this.experimentNo = experimentNo;
	}

	/**
	 * ʵ����
	 * 
	 */
	public String getExperimentNo() {
		return this.experimentNo;
	}

	/**
	 * �Ƿ�����
	 * 
	 */
	public void setExperimentStatus(Integer experimentStatus) {
		this.experimentStatus = experimentStatus;
	}

	/**
	 * �Ƿ�����
	 * 
	 */
	public Integer getExperimentStatus() {
		return this.experimentStatus;
	}

	/**
	 * ʵ��汾
	 * 
	 */
	public void setExperimentVersion(String experimentVersion) {
		this.experimentVersion = experimentVersion;
	}

	/**
	 * ʵ��汾
	 * 
	 */
	public String getExperimentVersion() {
		return this.experimentVersion;
	}

	/**
	 * ʵ��Ŀ��
	 * 
	 */
	public void setExperimentGoal(String experimentGoal) {
		this.experimentGoal = experimentGoal;
	}

	/**
	 * ʵ��Ŀ��
	 * 
	 */
	public String getExperimentGoal() {
		return this.experimentGoal;
	}

	/**
	 * ʵ������
	 * 
	 */
	public void setExperimentDescribe(String experimentDescribe) {
		this.experimentDescribe = experimentDescribe;
	}

	/**
	 * ʵ������
	 * 
	 */
	public String getExperimentDescribe() {
		return this.experimentDescribe;
	}

	/**
	 * ����������user
	 * 
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * ����������user
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
	 * ��ʼʱ��
	 * 
	 */
	public void setStartdate(Calendar startdate) {
		this.startdate = startdate;
	}

	/**
	 * ��ʼʱ��
	 * 
	 */
	public Calendar getStartdate() {
		return this.startdate;
	}

	/**
	 * ����ʱ��
	 * 
	 */
	public void setDuedate(Calendar duedate) {
		this.duedate = duedate;
	}

	/**
	 * ����ʱ��
	 * 
	 */
	public Calendar getDuedate() {
		return this.duedate;
	}

	/**
	 * ����
	 * 
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}

	/**
	 * ����
	 * 
	 */
	public Integer getSort() {
		return this.sort;
	}
			
	/**
	 */
	public TExperimentSkill() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(TExperimentSkill that) {
		setId(that.getId());
		setSiteId(that.getSiteId());
		setChapterId(that.getChapterId());
		setExperimentName(that.getExperimentName());
		setExperimentNo(that.getExperimentNo());
		setExperimentStatus(that.getExperimentStatus());
		setExperimentVersion(that.getExperimentVersion());
		setExperimentGoal(that.getExperimentGoal());
		setExperimentDescribe(that.getExperimentDescribe());
		setCreatedBy(that.getCreatedBy());
		setCreatedTime(that.getCreatedTime());
		setStartdate(that.getStartdate());
		setDuedate(that.getDuedate());
		setSort(that.getSort());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("id=[").append(id).append("] ");
		buffer.append("siteId=[").append(siteId).append("] ");
		buffer.append("chapterId=[").append(chapterId).append("] ");
		buffer.append("experimentName=[").append(experimentName).append("] ");
		buffer.append("experimentNo=[").append(experimentNo).append("] ");
		buffer.append("experimentStatus=[").append(experimentStatus).append("] ");
		buffer.append("experimentVersion=[").append(experimentVersion).append("] ");
		buffer.append("experimentGoal=[").append(experimentGoal).append("] ");
		buffer.append("experimentDescribe=[").append(experimentDescribe).append("] ");
		buffer.append("createdBy=[").append(createdBy).append("] ");
		buffer.append("createdTime=[").append(createdTime).append("] ");
		buffer.append("startdate=[").append(startdate).append("] ");
		buffer.append("duedate=[").append(duedate).append("] ");
		buffer.append("sort=[").append(sort).append("] ");

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
		if (!(obj instanceof TExperimentSkill))
			return false;
		TExperimentSkill equalCheck = (TExperimentSkill) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
