package net.xidlims.domain;

import java.io.Serializable;

import java.lang.StringBuilder;


import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
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
		@NamedQuery(name = "findAllWkChapters", query = "select myWkChapter from WkChapter myWkChapter"),
		@NamedQuery(name = "findWkChapterByFileList", query = "select myWkChapter from WkChapter myWkChapter where myWkChapter.fileList = ?1"),
		@NamedQuery(name = "findWkChapterByFileListContaining", query = "select myWkChapter from WkChapter myWkChapter where myWkChapter.fileList like ?1"),
		@NamedQuery(name = "findWkChapterById", query = "select myWkChapter from WkChapter myWkChapter where myWkChapter.id = ?1"),
		@NamedQuery(name = "findWkChapterByName", query = "select myWkChapter from WkChapter myWkChapter where myWkChapter.name = ?1"),
		@NamedQuery(name = "findWkChapterByNameContaining", query = "select myWkChapter from WkChapter myWkChapter where myWkChapter.name like ?1"),
		@NamedQuery(name = "findWkChapterByPrimaryKey", query = "select myWkChapter from WkChapter myWkChapter where myWkChapter.id = ?1"),
		@NamedQuery(name = "findWkChapterBySeq", query = "select myWkChapter from WkChapter myWkChapter where myWkChapter.seq = ?1") })
@Table(catalog = "xidlims", name = "wk_chapter")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "WkChapter")
@XmlRootElement(namespace = "xidlims/net/xidlims/domain")
public class WkChapter implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * �½ڱ�
	 * 
	 */

	@Column(name = "id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	@XmlElement
	Integer id;
	/**
	 * �½����
	 * 
	 */

	@Column(name = "name", length = 50)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String name;
	/**
	 * ����
	 * 
	 */

	@Column(name = "seq")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer seq;
	/**
	 * �ļ��б�
	 * 
	 */

	@Column(name = "fileList")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String fileList;
	
	/**
	 * ����
	 * 
	 */

	@Column(name = "type")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer type;
	
	/**
	 * ͼ������
	 * 
	 */

	@Column(name = "content", columnDefinition = "TEXT")
	@Basic(fetch = FetchType.EAGER)
	@Lob
	@XmlElement
	String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "site_id", referencedColumnName = "id") })
	@XmlTransient
	TCourseSite TCourseSite;
	/**
	 */
	@OneToMany(mappedBy = "wkChapter", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	@OrderBy("seq ASC")
	java.util.Set<net.xidlims.domain.WkLesson> wkLessons;
	

	
	/**
	 */
	@OneToMany(mappedBy = "WkChapter", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
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
	 * �½ڱ�
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * �½ڱ�
	 * 
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * �½����
	 * 
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * �½����
	 * 
	 */
	public String getName() {
		return this.name;
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
	 * �ļ��б�
	 * 
	 */
	public void setFileList(String fileList) {
		this.fileList = fileList;
	}

	/**
	 * �ļ��б�
	 * 
	 */
	public String getFileList() {
		return this.fileList;
	}

	/**
	 */
	public void setTCourseSite(TCourseSite tCourseSite) {
		this.TCourseSite = tCourseSite;
	}

	/**
	 */
	@JsonIgnore
	public TCourseSite getTCourseSite() {
		return TCourseSite;
	}

	/**
	 */
	public void setWkLessons(Set<WkLesson> wkLessons) {
		this.wkLessons = wkLessons;
	}

	/**
	 */
	@JsonIgnore
	public Set<WkLesson> getWkLessons() {
		if (wkLessons == null) {
			wkLessons = new java.util.LinkedHashSet<net.xidlims.domain.WkLesson>();
		}
		return wkLessons;
	}

	/**
	 */
	public WkChapter() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(WkChapter that) {
		setId(that.getId());
		setName(that.getName());
		setSeq(that.getSeq());
		setFileList(that.getFileList());
		setTCourseSite(that.getTCourseSite());
		setWkLessons(new java.util.LinkedHashSet<net.xidlims.domain.WkLesson>(that.getWkLessons()));
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("id=[").append(id).append("] ");
		buffer.append("name=[").append(name).append("] ");
		buffer.append("seq=[").append(seq).append("] ");
		buffer.append("fileList=[").append(fileList).append("] ");

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
		if (!(obj instanceof WkChapter))
			return false;
		WkChapter equalCheck = (WkChapter) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
