package net.xidlims.domain;

import java.io.Serializable;

import java.lang.StringBuilder;

import java.util.Calendar;
import java.util.Set;


import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import org.codehaus.jackson.annotate.JsonIgnore;

import javax.xml.bind.annotation.*;

import javax.persistence.*;

/**
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "findAllWkLessons", query = "select myWkLesson from WkLesson myWkLesson"),
		@NamedQuery(name = "findWkLessonByContent", query = "select myWkLesson from WkLesson myWkLesson where myWkLesson.content = ?1"),
		@NamedQuery(name = "findWkLessonByCreateTime", query = "select myWkLesson from WkLesson myWkLesson where myWkLesson.createTime = ?1"),
		@NamedQuery(name = "findWkLessonByDeep", query = "select myWkLesson from WkLesson myWkLesson where myWkLesson.deep = ?1"),
		@NamedQuery(name = "findWkLessonByFileId", query = "select myWkLesson from WkLesson myWkLesson where myWkLesson.fileId = ?1"),
		@NamedQuery(name = "findWkLessonById", query = "select myWkLesson from WkLesson myWkLesson where myWkLesson.id = ?1"),
		@NamedQuery(name = "findWkLessonByPrimaryKey", query = "select myWkLesson from WkLesson myWkLesson where myWkLesson.id = ?1"),
		@NamedQuery(name = "findWkLessonBySeq", query = "select myWkLesson from WkLesson myWkLesson where myWkLesson.seq = ?1"),
		@NamedQuery(name = "findWkLessonBySourceList", query = "select myWkLesson from WkLesson myWkLesson where myWkLesson.sourceList = ?1"),
		@NamedQuery(name = "findWkLessonBySourceListContaining", query = "select myWkLesson from WkLesson myWkLesson where myWkLesson.sourceList like ?1"),
		@NamedQuery(name = "findWkLessonByTestId", query = "select myWkLesson from WkLesson myWkLesson where myWkLesson.testId = ?1"),
		@NamedQuery(name = "findWkLessonByTitle", query = "select myWkLesson from WkLesson myWkLesson where myWkLesson.title = ?1"),
		@NamedQuery(name = "findWkLessonByTitleContaining", query = "select myWkLesson from WkLesson myWkLesson where myWkLesson.title like ?1"),
		@NamedQuery(name = "findWkLessonByType", query = "select myWkLesson from WkLesson myWkLesson where myWkLesson.type = ?1"),
		@NamedQuery(name = "findWkLessonByTypeContaining", query = "select myWkLesson from WkLesson myWkLesson where myWkLesson.type like ?1"),
		@NamedQuery(name = "findWkLessonByVideoUrl", query = "select myWkLesson from WkLesson myWkLesson where myWkLesson.videoUrl = ?1"),
		@NamedQuery(name = "findWkLessonByVideoUrlContaining", query = "select myWkLesson from WkLesson myWkLesson where myWkLesson.videoUrl like ?1"),
		@NamedQuery(name = "findWkLessonByViewedNum", query = "select myWkLesson from WkLesson myWkLesson where myWkLesson.viewedNum = ?1") })
@Table(catalog = "xidlims", name = "wk_lesson")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "WkLesson")
public class WkLesson implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * �γ̵Ŀ�ʱ��
	 * 
	 */

	@Column(name = "id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	@XmlElement
	Integer id;
	/**
	 */

	@Column(name = "title")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String title;
	/**
	 * ��ʱ���ͣ�video,text
	 * 
	 */

	@Column(name = "type", length = 11)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String type;
	/**
	 * ͼ������
	 * 
	 */

	@Column(name = "content", columnDefinition = "TEXT")
	@Basic(fetch = FetchType.EAGER)
	@Lob
	@XmlElement
	String content;
	/**
	 * ����
	 * 
	 */

	@Column(name = "seq")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer seq;
	/**
	 * ��ص��ļ�id
	 * 
	 */

	@Column(name = "fileId")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer fileId;
	/**
	 * ������Ƶ��·��
	 * 
	 */

	@Column(name = "videoUrl")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String videoUrl;
	/**
	 * ����id
	 * 
	 */

	@Column(name = "testId")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer testId;
	/**
	 */

	@Column(name = "deep", columnDefinition = "TEXT")
	@Basic(fetch = FetchType.EAGER)
	@Lob
	@XmlElement
	String deep;
	/**
	 * ��Դid�б�,���磺1,5,21
	 * 
	 */

	@Column(name = "sourceList")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String sourceList;
	/**
	 * ���ʴ���
	 * 
	 */

	@Column(name = "viewedNum")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer viewedNum;
	/**
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createTime")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar createTime;
	
	@Column(name = "codeImgPath")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String codeImgPath;
	
	@Column(name = "learning_target")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String learningTarget;
	
	public String getLearningTarget() {
		return learningTarget;
	}

	public void setLearningTarget(String learningTarget) {
		this.learningTarget = learningTarget;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Column(name = "remarks")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String remarks;
	/**
	 * ��ʱ���ͣ�video,text
	 * 
	 */

	public String getCodeImgPath() {
		return codeImgPath;
	}

	public void setCodeImgPath(String codeImgPath) {
		this.codeImgPath = codeImgPath;
	}

	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "chapter_id", referencedColumnName = "id") })
	@XmlTransient
	WkChapter wkChapter;
	
	/**
	 */
	@OneToMany(mappedBy = "WkLesson", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	@OrderBy("id ASC")
	java.util.Set<net.xidlims.domain.WkFolder> wkFolders;
	
	/**
	 */
	public void setWkFolders(Set<WkFolder> wkFolders) {
		this.wkFolders = wkFolders;
	}

	/**
	 */
	@JsonIgnore
	public Set<WkFolder> getWkFolders() {
		if (wkFolders == null) {
			wkFolders = new java.util.LinkedHashSet<net.xidlims.domain.WkFolder>();
		}
		return wkFolders;
	}
	

	/**
	 * �γ̵Ŀ�ʱ��
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * �γ̵Ŀ�ʱ��
	 * 
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * ��ʱ���ͣ�video,text
	 * 
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * ��ʱ���ͣ�video,text
	 * 
	 */
	public String getType() {
		return this.type;
	}

	/**
	 * ͼ������
	 * 
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * ͼ������
	 * 
	 */
	public String getContent() {
		return this.content;
	}

	/**
	 * ����
	 * 
	 */
	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	/**
	 * ����
	 * 
	 */
	public Integer getSeq() {
		return this.seq;
	}

	/**
	 * ��ص��ļ�id
	 * 
	 */
	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}

	/**
	 * ��ص��ļ�id
	 * 
	 */
	public Integer getFileId() {
		return this.fileId;
	}

	/**
	 * ������Ƶ��·��
	 * 
	 */
	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}

	/**
	 * ������Ƶ��·��
	 * 
	 */
	public String getVideoUrl() {
		return this.videoUrl;
	}

	/**
	 * ����id
	 * 
	 */
	public void setTestId(Integer testId) {
		this.testId = testId;
	}

	/**
	 * ����id
	 * 
	 */
	public Integer getTestId() {
		return this.testId;
	}

	/**
	 */
	public void setDeep(String deep) {
		this.deep = deep;
	}

	/**
	 */
	public String getDeep() {
		return this.deep;
	}

	/**
	 * ��Դid�б�,���磺1,5,21
	 * 
	 */
	public void setSourceList(String sourceList) {
		this.sourceList = sourceList;
	}

	/**
	 * ��Դid�б�,���磺1,5,21
	 * 
	 */
	public String getSourceList() {
		return this.sourceList;
	}

	/**
	 * ���ʴ���
	 * 
	 */
	public void setViewedNum(Integer viewedNum) {
		this.viewedNum = viewedNum;
	}

	/**
	 * ���ʴ���
	 * 
	 */
	public Integer getViewedNum() {
		return this.viewedNum;
	}

	/**
	 */
	public void setCreateTime(Calendar createTime) {
		this.createTime = createTime;
	}

	/**
	 */
	public Calendar getCreateTime() {
		return this.createTime;
	}

	/**
	 */
	public void setWkChapter(WkChapter wkChapter) {
		this.wkChapter = wkChapter;
	}

	/**
	 */
	@JsonIgnore
	public WkChapter getWkChapter() {
		return wkChapter;
	}

	/**
	 */
	public WkLesson() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(WkLesson that) {
		setId(that.getId());
		setTitle(that.getTitle());
		setType(that.getType());
		setContent(that.getContent());
		setSeq(that.getSeq());
		setFileId(that.getFileId());
		setVideoUrl(that.getVideoUrl());
		setTestId(that.getTestId());
		setDeep(that.getDeep());
		setSourceList(that.getSourceList());
		setViewedNum(that.getViewedNum());
		setCreateTime(that.getCreateTime());
		setWkChapter(that.getWkChapter());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("id=[").append(id).append("] ");
		buffer.append("title=[").append(title).append("] ");
		buffer.append("type=[").append(type).append("] ");
		buffer.append("content=[").append(content).append("] ");
		buffer.append("seq=[").append(seq).append("] ");
		buffer.append("fileId=[").append(fileId).append("] ");
		buffer.append("videoUrl=[").append(videoUrl).append("] ");
		buffer.append("testId=[").append(testId).append("] ");
		buffer.append("deep=[").append(deep).append("] ");
		buffer.append("sourceList=[").append(sourceList).append("] ");
		buffer.append("viewedNum=[").append(viewedNum).append("] ");
		buffer.append("createTime=[").append(createTime).append("] ");

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
		if (!(obj instanceof WkLesson))
			return false;
		WkLesson equalCheck = (WkLesson) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
