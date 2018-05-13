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
		@NamedQuery(name = "findAllCmsResources", query = "select myCmsResource from CmsResource myCmsResource"),
		@NamedQuery(name = "findCmsResourceByCreateTime", query = "select myCmsResource from CmsResource myCmsResource where myCmsResource.createTime = ?1"),
		@NamedQuery(name = "findCmsResourceById", query = "select myCmsResource from CmsResource myCmsResource where myCmsResource.id = ?1"),
		@NamedQuery(name = "findCmsResourceByImageVideo", query = "select myCmsResource from CmsResource myCmsResource where myCmsResource.imageVideo = ?1"),
		@NamedQuery(name = "findCmsResourceByName", query = "select myCmsResource from CmsResource myCmsResource where myCmsResource.name = ?1"),
		@NamedQuery(name = "findCmsResourceByNameContaining", query = "select myCmsResource from CmsResource myCmsResource where myCmsResource.name like ?1"),
		@NamedQuery(name = "findCmsResourceByPrimaryKey", query = "select myCmsResource from CmsResource myCmsResource where myCmsResource.id = ?1"),
		@NamedQuery(name = "findCmsResourceByProfile", query = "select myCmsResource from CmsResource myCmsResource where myCmsResource.profile = ?1"),
		@NamedQuery(name = "findCmsResourceByProfileContaining", query = "select myCmsResource from CmsResource myCmsResource where myCmsResource.profile like ?1"),
		@NamedQuery(name = "findCmsResourceByUrl", query = "select myCmsResource from CmsResource myCmsResource where myCmsResource.url = ?1"),
		@NamedQuery(name = "findCmsResourceByUrlContaining", query = "select myCmsResource from CmsResource myCmsResource where myCmsResource.url like ?1") })
@Table(catalog = "xidlims", name = "cms_resource")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/com/xidlims/domain", name = "CmsResource")
@XmlRootElement(namespace = "xidlims/com/xidlims/domain")
public class CmsResource implements Serializable {
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

	@Column(name = "name", length = 100, nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String name;
	/**
	 * ·��
	 * 
	 */

	@Column(name = "url", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String url;
	/**
	 * ��Ҫ
	 * 
	 */

	@Column(name = "profile")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String profile;
	/**
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time", nullable = true)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar createTime;
	/**
	 */

	@Column(name = "image_video")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer imageVideo;

	/**
	 */
	@OneToMany(mappedBy = "cmsResource", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.CmsChannel> cmsChannels;
	/**
	 */
	@OneToMany(mappedBy = "cmsResource", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.CmsArticle> cmsArticles;
	/**
	 */
	@OneToMany(mappedBy = "cmsResource", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.CmsLink> cmsLinks;
	/**
	 */
	@OneToMany(mappedBy = "cmsResource", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.CmsSite> cmsSites;

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
	 * ·��
	 * 
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * ·��
	 * 
	 */
	public String getUrl() {
		return this.url;
	}

	/**
	 * ��Ҫ
	 * 
	 */
	public void setProfile(String profile) {
		this.profile = profile;
	}

	/**
	 * ��Ҫ
	 * 
	 */
	public String getProfile() {
		return this.profile;
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
	public void setImageVideo(Integer imageVideo) {
		this.imageVideo = imageVideo;
	}

	/**
	 */
	public Integer getImageVideo() {
		return this.imageVideo;
	}

	/**
	 */
	public void setCmsChannels(Set<CmsChannel> cmsChannels) {
		this.cmsChannels = cmsChannels;
	}

	/**
	 */
	@JsonIgnore
	public Set<CmsChannel> getCmsChannels() {
		if (cmsChannels == null) {
			cmsChannels = new java.util.LinkedHashSet<net.xidlims.domain.CmsChannel>();
		}
		return cmsChannels;
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
	public void setCmsLinks(Set<CmsLink> cmsLinks) {
		this.cmsLinks = cmsLinks;
	}

	/**
	 */
	@JsonIgnore
	public Set<CmsLink> getCmsLinks() {
		if (cmsLinks == null) {
			cmsLinks = new java.util.LinkedHashSet<net.xidlims.domain.CmsLink>();
		}
		return cmsLinks;
	}

	/**
	 */
	public void setCmsSites(Set<CmsSite> cmsSites) {
		this.cmsSites = cmsSites;
	}

	/**
	 */
	@JsonIgnore
	public Set<CmsSite> getCmsSites() {
		if (cmsSites == null) {
			cmsSites = new java.util.LinkedHashSet<net.xidlims.domain.CmsSite>();
		}
		return cmsSites;
	}

	/**
	 */
	public CmsResource() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(CmsResource that) {
		setId(that.getId());
		setName(that.getName());
		setUrl(that.getUrl());
		setProfile(that.getProfile());
		setCreateTime(that.getCreateTime());
		setImageVideo(that.getImageVideo());
		setCmsChannels(new java.util.LinkedHashSet<net.xidlims.domain.CmsChannel>(that.getCmsChannels()));
		setCmsArticles(new java.util.LinkedHashSet<net.xidlims.domain.CmsArticle>(that.getCmsArticles()));
		setCmsLinks(new java.util.LinkedHashSet<net.xidlims.domain.CmsLink>(that.getCmsLinks()));
		setCmsSites(new java.util.LinkedHashSet<net.xidlims.domain.CmsSite>(that.getCmsSites()));
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
		buffer.append("imageVideo=[").append(imageVideo).append("] ");

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
		if (!(obj instanceof CmsResource))
			return false;
		CmsResource equalCheck = (CmsResource) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
