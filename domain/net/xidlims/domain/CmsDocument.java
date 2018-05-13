package net.xidlims.domain;

import java.io.Serializable;

import java.lang.StringBuilder;

import java.util.Calendar;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
		@NamedQuery(name = "findAllCmsDocuments", query = "select myCmsDocument from CmsDocument myCmsDocument"),
		@NamedQuery(name = "findCmsDocumentByCreateTime", query = "select myCmsDocument from CmsDocument myCmsDocument where myCmsDocument.createTime = ?1"),
		@NamedQuery(name = "findCmsDocumentById", query = "select myCmsDocument from CmsDocument myCmsDocument where myCmsDocument.id = ?1"),
		@NamedQuery(name = "findCmsDocumentByName", query = "select myCmsDocument from CmsDocument myCmsDocument where myCmsDocument.name = ?1"),
		@NamedQuery(name = "findCmsDocumentByNameContaining", query = "select myCmsDocument from CmsDocument myCmsDocument where myCmsDocument.name like ?1"),
		@NamedQuery(name = "findCmsDocumentByPrimaryKey", query = "select myCmsDocument from CmsDocument myCmsDocument where myCmsDocument.id = ?1"),
		@NamedQuery(name = "findCmsDocumentByProfile", query = "select myCmsDocument from CmsDocument myCmsDocument where myCmsDocument.profile = ?1"),
		@NamedQuery(name = "findCmsDocumentByProfileContaining", query = "select myCmsDocument from CmsDocument myCmsDocument where myCmsDocument.profile like ?1"),
		@NamedQuery(name = "findCmsDocumentByTag", query = "select myCmsDocument from CmsDocument myCmsDocument where myCmsDocument.tag = ?1"),
		@NamedQuery(name = "findCmsDocumentByUrl", query = "select myCmsDocument from CmsDocument myCmsDocument where myCmsDocument.url = ?1"),
		@NamedQuery(name = "findCmsDocumentByUrlContaining", query = "select myCmsDocument from CmsDocument myCmsDocument where myCmsDocument.url like ?1") })
@Table(catalog = "xidlims", name = "cms_document")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/com/xidlims/domain", name = "CmsDocument")
@XmlRootElement(namespace = "xidlims/com/xidlims/domain")
public class CmsDocument implements Serializable {
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
	 * ���
	 * 
	 */

	@Column(name = "name", length = 100)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String name;
	/**
	 * ����·��
	 * 
	 */

	@Column(name = "url")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String url;
	/**
	 * ԭ�ļ���
	 * 
	 */

	@Column(name = "profile")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String profile;
	/**
	 * ����ʱ��
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar createTime;
	/**
	 * ������Ŀ0Ϊ������Ϣ��1Ϊ�����
	 * 
	 */

	@Column(name = "tag")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer tag;

	/**
	 */
	@OneToMany(mappedBy = "cmsDocument", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.CmsArticle> cmsArticles;

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
	 * ���
	 * 
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * ���
	 * 
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * ����·��
	 * 
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * ����·��
	 * 
	 */
	public String getUrl() {
		return this.url;
	}

	/**
	 * ԭ�ļ���
	 * 
	 */
	public void setProfile(String profile) {
		this.profile = profile;
	}

	/**
	 * ԭ�ļ���
	 * 
	 */
	public String getProfile() {
		return this.profile;
	}

	/**
	 * ����ʱ��
	 * 
	 */
	public void setCreateTime(Calendar createTime) {
		this.createTime = createTime;
	}

	/**
	 * ����ʱ��
	 * 
	 */
	public Calendar getCreateTime() {
		return this.createTime;
	}

	/**
	 * ������Ŀ0Ϊ������Ϣ��1Ϊ�����
	 * 
	 */
	public void setTag(Integer tag) {
		this.tag = tag;
	}

	/**
	 * ������Ŀ0Ϊ������Ϣ��1Ϊ�����
	 * 
	 */
	public Integer getTag() {
		return this.tag;
	}

	/**
	 */
	public void setCmsArticles(Set<CmsArticle> cmsArticles) {
		this.cmsArticles = cmsArticles;
	}

	/**
	 */
	@JsonIgnore
	public Set<CmsArticle> getCmsArticles() {
		if (cmsArticles == null) {
			cmsArticles = new java.util.LinkedHashSet<net.xidlims.domain.CmsArticle>();
		}
		return cmsArticles;
	}

	/**
	 */
	public CmsDocument() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(CmsDocument that) {
		setId(that.getId());
		setName(that.getName());
		setUrl(that.getUrl());
		setProfile(that.getProfile());
		setCreateTime(that.getCreateTime());
		setTag(that.getTag());
		setCmsArticles(new java.util.LinkedHashSet<net.xidlims.domain.CmsArticle>(that.getCmsArticles()));
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("id=[").append(id).append("] ");
		buffer.append("name=[").append(name).append("] ");
		buffer.append("url=[").append(url).append("] ");
		buffer.append("profile=[").append(profile).append("] ");
		buffer.append("createTime=[").append(createTime).append("] ");
		buffer.append("tag=[").append(tag).append("] ");

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
		if (!(obj instanceof CmsDocument))
			return false;
		CmsDocument equalCheck = (CmsDocument) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
