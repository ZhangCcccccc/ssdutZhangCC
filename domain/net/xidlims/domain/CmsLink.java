package net.xidlims.domain;

import java.io.Serializable;

import java.lang.StringBuilder;

import java.util.Calendar;
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
		@NamedQuery(name = "findAllCmsLinks", query = "select myCmsLink from CmsLink myCmsLink"),
		@NamedQuery(name = "findCmsLinkByCreateTime", query = "select myCmsLink from CmsLink myCmsLink where myCmsLink.createTime = ?1"),
		@NamedQuery(name = "findCmsLinkById", query = "select myCmsLink from CmsLink myCmsLink where myCmsLink.id = ?1"),
		@NamedQuery(name = "findCmsLinkByLinkName", query = "select myCmsLink from CmsLink myCmsLink where myCmsLink.linkName = ?1"),
		@NamedQuery(name = "findCmsLinkByLinkNameContaining", query = "select myCmsLink from CmsLink myCmsLink where myCmsLink.linkName like ?1"),
		@NamedQuery(name = "findCmsLinkByLinkUrl", query = "select myCmsLink from CmsLink myCmsLink where myCmsLink.linkUrl = ?1"),
		@NamedQuery(name = "findCmsLinkByLinkUrlContaining", query = "select myCmsLink from CmsLink myCmsLink where myCmsLink.linkUrl like ?1"),
		@NamedQuery(name = "findCmsLinkByPrimaryKey", query = "select myCmsLink from CmsLink myCmsLink where myCmsLink.id = ?1"),
		@NamedQuery(name = "findCmsLinkByProfile", query = "select myCmsLink from CmsLink myCmsLink where myCmsLink.profile = ?1"),
		@NamedQuery(name = "findCmsLinkByProfileContaining", query = "select myCmsLink from CmsLink myCmsLink where myCmsLink.profile like ?1"),
		@NamedQuery(name = "findCmsLinkBySort", query = "select myCmsLink from CmsLink myCmsLink where myCmsLink.sort = ?1"),
		@NamedQuery(name = "findCmsLinkByState", query = "select myCmsLink from CmsLink myCmsLink where myCmsLink.state = ?1") })
@Table(catalog = "xidlims", name = "cms_link")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/com/xidlims/domain", name = "CmsLink")
public class CmsLink implements Serializable {
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
	 */

	@Column(name = "linkName")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String linkName;
	/**
	 */

	@Column(name = "linkUrl")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String linkUrl;
	/**
	 */

	@Column(name = "profile")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String profile;
	/**
	 */

	@Column(name = "state")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer state;
	/**
	 */

	@Column(name = "sort")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer sort;
	/**
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar createTime;

	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "site", referencedColumnName = "siteurl") })
	@XmlTransient
	CmsSite cmsSite;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "resource_id", referencedColumnName = "id") })
	@XmlTransient
	CmsResource cmsResource;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "link_tag", referencedColumnName = "id") })
	@XmlTransient
	CmsTag cmsTag;

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
	 */
	public void setLinkName(String linkName) {
		this.linkName = linkName;
	}

	/**
	 */
	public String getLinkName() {
		return this.linkName;
	}

	/**
	 */
	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	/**
	 */
	public String getLinkUrl() {
		return this.linkUrl;
	}

	/**
	 */
	public void setProfile(String profile) {
		this.profile = profile;
	}

	/**
	 */
	public String getProfile() {
		return this.profile;
	}

	/**
	 */
	public void setState(Integer state) {
		this.state = state;
	}

	/**
	 */
	public Integer getState() {
		return this.state;
	}

	/**
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}

	/**
	 */
	public Integer getSort() {
		return this.sort;
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
	public void setCmsSite(CmsSite cmsSite) {
		this.cmsSite = cmsSite;
	}

	/**
	 */
	@JsonIgnore
	public CmsSite getCmsSite() {
		return cmsSite;
	}

	/**
	 */
	public void setCmsResource(CmsResource cmsResource) {
		this.cmsResource = cmsResource;
	}

	/**
	 */
	@JsonIgnore
	public CmsResource getCmsResource() {
		return cmsResource;
	}

	/**
	 */
	public void setCmsTag(CmsTag cmsTag) {
		this.cmsTag = cmsTag;
	}

	/**
	 */
	@JsonIgnore
	public CmsTag getCmsTag() {
		return cmsTag;
	}

	/**
	 */
	public CmsLink() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(CmsLink that) {
		setId(that.getId());
		setLinkName(that.getLinkName());
		setLinkUrl(that.getLinkUrl());
		setProfile(that.getProfile());
		setState(that.getState());
		setSort(that.getSort());
		setCreateTime(that.getCreateTime());
		setCmsSite(that.getCmsSite());
		setCmsResource(that.getCmsResource());
		setCmsTag(that.getCmsTag());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("id=[").append(id).append("] ");
		buffer.append("linkName=[").append(linkName).append("] ");
		buffer.append("linkUrl=[").append(linkUrl).append("] ");
		buffer.append("profile=[").append(profile).append("] ");
		buffer.append("state=[").append(state).append("] ");
		buffer.append("sort=[").append(sort).append("] ");
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
		if (!(obj instanceof CmsLink))
			return false;
		CmsLink equalCheck = (CmsLink) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
