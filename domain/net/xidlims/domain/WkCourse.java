package net.xidlims.domain;

import java.io.Serializable;

import java.lang.StringBuilder;

import java.util.Calendar;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import org.codehaus.jackson.annotate.JsonIgnore;

import javax.xml.bind.annotation.*;

import javax.persistence.*;

/**
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "findAllWkCourses", query = "select myWkCourse from WkCourse myWkCourse"),
		@NamedQuery(name = "findWkCourseByCerateTime", query = "select myWkCourse from WkCourse myWkCourse where myWkCourse.cerateTime = ?1"),
		@NamedQuery(name = "findWkCourseByCode", query = "select myWkCourse from WkCourse myWkCourse where myWkCourse.code = ?1"),
		@NamedQuery(name = "findWkCourseByCodeContaining", query = "select myWkCourse from WkCourse myWkCourse where myWkCourse.code like ?1"),
		@NamedQuery(name = "findWkCourseByEvaluation", query = "select myWkCourse from WkCourse myWkCourse where myWkCourse.evaluation = ?1"),
		@NamedQuery(name = "findWkCourseByEvaluationContaining", query = "select myWkCourse from WkCourse myWkCourse where myWkCourse.evaluation like ?1"),
		@NamedQuery(name = "findWkCourseByFilesList", query = "select myWkCourse from WkCourse myWkCourse where myWkCourse.filesList = ?1"),
		@NamedQuery(name = "findWkCourseById", query = "select myWkCourse from WkCourse myWkCourse where myWkCourse.id = ?1"),
		@NamedQuery(name = "findWkCourseByIntroduction", query = "select myWkCourse from WkCourse myWkCourse where myWkCourse.introduction = ?1"),
		@NamedQuery(name = "findWkCourseByIntroductionContaining", query = "select myWkCourse from WkCourse myWkCourse where myWkCourse.introduction like ?1"),
		@NamedQuery(name = "findWkCourseByIsOpen", query = "select myWkCourse from WkCourse myWkCourse where myWkCourse.isOpen = ?1"),
		@NamedQuery(name = "findWkCourseByLogoUrl", query = "select myWkCourse from WkCourse myWkCourse where myWkCourse.logoUrl = ?1"),
		@NamedQuery(name = "findWkCourseByLogoUrlContaining", query = "select myWkCourse from WkCourse myWkCourse where myWkCourse.logoUrl like ?1"),
		@NamedQuery(name = "findWkCourseByManager", query = "select myWkCourse from WkCourse myWkCourse where myWkCourse.manager = ?1"),
		@NamedQuery(name = "findWkCourseByManagerContaining", query = "select myWkCourse from WkCourse myWkCourse where myWkCourse.manager like ?1"),
		@NamedQuery(name = "findWkCourseByName", query = "select myWkCourse from WkCourse myWkCourse where myWkCourse.name = ?1"),
		@NamedQuery(name = "findWkCourseByNameContaining", query = "select myWkCourse from WkCourse myWkCourse where myWkCourse.name like ?1"),
		@NamedQuery(name = "findWkCourseByOutcomes", query = "select myWkCourse from WkCourse myWkCourse where myWkCourse.outcomes = ?1"),
		@NamedQuery(name = "findWkCourseByOutcomesContaining", query = "select myWkCourse from WkCourse myWkCourse where myWkCourse.outcomes like ?1"),
		@NamedQuery(name = "findWkCourseByPrimaryKey", query = "select myWkCourse from WkCourse myWkCourse where myWkCourse.id = ?1"),
		@NamedQuery(name = "findWkCourseBySyllabus", query = "select myWkCourse from WkCourse myWkCourse where myWkCourse.syllabus = ?1"),
		@NamedQuery(name = "findWkCourseByTagList", query = "select myWkCourse from WkCourse myWkCourse where myWkCourse.tagList = ?1"),
		@NamedQuery(name = "findWkCourseByTagListContaining", query = "select myWkCourse from WkCourse myWkCourse where myWkCourse.tagList like ?1"),
		@NamedQuery(name = "findWkCourseByVideoId", query = "select myWkCourse from WkCourse myWkCourse where myWkCourse.videoId = ?1") })
@Table(catalog = "xidlims", name = "wk_course")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "WkCourse")
@XmlRootElement(namespace = "xidlims/net/xidlims/domain")
public class WkCourse implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * �γ̱�
	 * 
	 */

	@Column(name = "id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	Integer id;
	/**
	 * �γ̱��
	 * 
	 */

	@Column(name = "code", length = 40)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String code;
	/**
	 * �γ����
	 * 
	 */

	@Column(name = "name")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String name;
	/**
	 * ��ǩ�б�,���磺1,5,21...
	 * 
	 */

	@Column(name = "tagList")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String tagList;
	/**
	 * �γ̴�����
	 * 
	 */

	@Column(name = "manager")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String manager;
	/**
	 * ��Ҫ
	 * 
	 */

	@Column(name = "introduction", length = 1024)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String introduction;
	/**
	 * ѧϰĿ��
	 * 
	 */

	@Column(name = "outcomes")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String outcomes;
	/**
	 * ��ѧ���
	 * 
	 */

	@Column(name = "syllabus", columnDefinition = "TEXT")
	@Basic(fetch = FetchType.EAGER)
	@Lob
	@XmlElement
	String syllabus;
	/**
	 * ��������
	 * 
	 */

	@Column(name = "evaluation")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String evaluation;
	/**
	 * �γ�logo·��
	 * 
	 */

	@Column(name = "logoUrl")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String logoUrl;
	/**
	 */

	@Column(name = "videoId")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer videoId;
	/**
	 */

	@Column(name = "filesList", columnDefinition = "TEXT")
	@Basic(fetch = FetchType.EAGER)
	@Lob
	@XmlElement
	String filesList;
	/**
	 * ����ʱ��
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "cerateTime", nullable = true)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar cerateTime;
	/**
	 * �γ��Ƿ���⿪�� 1���ţ�0������
	 * 
	 */

	@Column(name = "isOpen")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer isOpen;


	/**
	 * �γ̱�
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * �γ̱�
	 * 
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * �γ̱��
	 * 
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * �γ̱��
	 * 
	 */
	public String getCode() {
		return this.code;
	}

	/**
	 * �γ����
	 * 
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * �γ����
	 * 
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * ��ǩ�б�,���磺1,5,21...
	 * 
	 */
	public void setTagList(String tagList) {
		this.tagList = tagList;
	}

	/**
	 * ��ǩ�б�,���磺1,5,21...
	 * 
	 */
	public String getTagList() {
		return this.tagList;
	}

	/**
	 * �γ̴�����
	 * 
	 */
	public void setManager(String manager) {
		this.manager = manager;
	}

	/**
	 * �γ̴�����
	 * 
	 */
	public String getManager() {
		return this.manager;
	}

	/**
	 * ��Ҫ
	 * 
	 */
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	/**
	 * ��Ҫ
	 * 
	 */
	public String getIntroduction() {
		return this.introduction;
	}

	/**
	 * ѧϰĿ��
	 * 
	 */
	public void setOutcomes(String outcomes) {
		this.outcomes = outcomes;
	}

	/**
	 * ѧϰĿ��
	 * 
	 */
	public String getOutcomes() {
		return this.outcomes;
	}

	/**
	 * ��ѧ���
	 * 
	 */
	public void setSyllabus(String syllabus) {
		this.syllabus = syllabus;
	}

	/**
	 * ��ѧ���
	 * 
	 */
	public String getSyllabus() {
		return this.syllabus;
	}

	/**
	 * ��������
	 * 
	 */
	public void setEvaluation(String evaluation) {
		this.evaluation = evaluation;
	}

	/**
	 * ��������
	 * 
	 */
	public String getEvaluation() {
		return this.evaluation;
	}

	/**
	 * �γ�logo·��
	 * 
	 */
	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	/**
	 * �γ�logo·��
	 * 
	 */
	public String getLogoUrl() {
		return this.logoUrl;
	}

	/**
	 */
	public void setVideoId(Integer videoId) {
		this.videoId = videoId;
	}

	/**
	 */
	public Integer getVideoId() {
		return this.videoId;
	}

	/**
	 */
	public void setFilesList(String filesList) {
		this.filesList = filesList;
	}

	/**
	 */
	public String getFilesList() {
		return this.filesList;
	}

	/**
	 * ����ʱ��
	 * 
	 */
	public void setCerateTime(Calendar cerateTime) {
		this.cerateTime = cerateTime;
	}

	/**
	 * ����ʱ��
	 * 
	 */
	public Calendar getCerateTime() {
		return this.cerateTime;
	}

	/**
	 * �γ��Ƿ���⿪�� 1���ţ�0������
	 * 
	 */
	public void setIsOpen(Integer isOpen) {
		this.isOpen = isOpen;
	}

	/**
	 * �γ��Ƿ���⿪�� 1���ţ�0������
	 * 
	 */
	public Integer getIsOpen() {
		return this.isOpen;
	}

	
	/**
	 */
	public WkCourse() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(WkCourse that) {
		setId(that.getId());
		setCode(that.getCode());
		setName(that.getName());
		setTagList(that.getTagList());
		setManager(that.getManager());
		setIntroduction(that.getIntroduction());
		setOutcomes(that.getOutcomes());
		setSyllabus(that.getSyllabus());
		setEvaluation(that.getEvaluation());
		setLogoUrl(that.getLogoUrl());
		setVideoId(that.getVideoId());
		setFilesList(that.getFilesList());
		setCerateTime(that.getCerateTime());
		setIsOpen(that.getIsOpen());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("id=[").append(id).append("] ");
		buffer.append("code=[").append(code).append("] ");
		buffer.append("name=[").append(name).append("] ");
		buffer.append("tagList=[").append(tagList).append("] ");
		buffer.append("manager=[").append(manager).append("] ");
		buffer.append("introduction=[").append(introduction).append("] ");
		buffer.append("outcomes=[").append(outcomes).append("] ");
		buffer.append("syllabus=[").append(syllabus).append("] ");
		buffer.append("evaluation=[").append(evaluation).append("] ");
		buffer.append("logoUrl=[").append(logoUrl).append("] ");
		buffer.append("videoId=[").append(videoId).append("] ");
		buffer.append("filesList=[").append(filesList).append("] ");
		buffer.append("cerateTime=[").append(cerateTime).append("] ");
		buffer.append("isOpen=[").append(isOpen).append("] ");

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
		if (!(obj instanceof WkCourse))
			return false;
		WkCourse equalCheck = (WkCourse) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
