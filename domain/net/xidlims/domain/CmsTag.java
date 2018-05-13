package net.xidlims.domain;

import java.io.Serializable;

import java.lang.StringBuilder;

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
		@NamedQuery(name = "findAllCmsTags", query = "select myCmsTag from CmsTag myCmsTag"),
		@NamedQuery(name = "findCmsTagByCategory", query = "select myCmsTag from CmsTag myCmsTag where myCmsTag.category = ?1"),
		@NamedQuery(name = "findCmsTagByCategoryContaining", query = "select myCmsTag from CmsTag myCmsTag where myCmsTag.category like ?1"),
		@NamedQuery(name = "findCmsTagByDescription", query = "select myCmsTag from CmsTag myCmsTag where myCmsTag.description = ?1"),
		@NamedQuery(name = "findCmsTagByDescriptionContaining", query = "select myCmsTag from CmsTag myCmsTag where myCmsTag.description like ?1"),
		@NamedQuery(name = "findCmsTagById", query = "select myCmsTag from CmsTag myCmsTag where myCmsTag.id = ?1"),
		@NamedQuery(name = "findCmsTagByName", query = "select myCmsTag from CmsTag myCmsTag where myCmsTag.name = ?1"),
		@NamedQuery(name = "findCmsTagByNameContaining", query = "select myCmsTag from CmsTag myCmsTag where myCmsTag.name like ?1"),
		@NamedQuery(name = "findCmsTagByPrimaryKey", query = "select myCmsTag from CmsTag myCmsTag where myCmsTag.id = ?1") })
@Table(catalog = "xidlims", name = "cms_tag")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/com/xidlims/domain", name = "CmsTag")
@XmlRootElement(namespace = "xidlims/com/xidlims/domain")
public class CmsTag implements Serializable {
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
	 * ����
	 * 
	 */

	@Column(name = "name", length = 20)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String name;
	/**
	 * ����
	 * 
	 */

	@Column(name = "description")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String description;
	/**
	 * tag����,1Ϊ��Ŀ��2Ϊ���ţ�3ΪͼƬ
	 * 
	 */

	@Column(name = "category", length = 100)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String category;

	/**
	 */
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(catalog = "xidlims", name = "cms_tag_channel", joinColumns = { @JoinColumn(name = "tag_id", referencedColumnName = "id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "channel_id", referencedColumnName = "id", nullable = false, updatable = false) })
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.CmsChannel> cmsChannels;
	/**
	 */
	@ManyToMany(mappedBy = "cmsTags", fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.CmsArticle> cmsArticles;
	/**
	 */
	@OneToMany(mappedBy = "cmsTag", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.CmsLink> cmsLinks;

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
	 * ����
	 * 
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * ����
	 * 
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * ����
	 * 
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * ����
	 * 
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * tag����,1Ϊ��Ŀ��2Ϊ���ţ�3ΪͼƬ
	 * 
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * tag����,1Ϊ��Ŀ��2Ϊ���ţ�3ΪͼƬ
	 * 
	 */
	public String getCategory() {
		return this.category;
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
	public CmsTag() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(CmsTag that) {
		setId(that.getId());
		setName(that.getName());
		setDescription(that.getDescription());
		setCategory(that.getCategory());
		setCmsChannels(new java.util.LinkedHashSet<net.xidlims.domain.CmsChannel>(that.getCmsChannels()));
		setCmsArticles(new java.util.LinkedHashSet<net.xidlims.domain.CmsArticle>(that.getCmsArticles()));
		setCmsLinks(new java.util.LinkedHashSet<net.xidlims.domain.CmsLink>(that.getCmsLinks()));
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("id=[").append(id).append("] ");
		buffer.append("name=[").append(name).append("] ");
		buffer.append("description=[").append(description).append("] ");
		buffer.append("category=[").append(category).append("] ");

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
		if (!(obj instanceof CmsTag))
			return false;
		CmsTag equalCheck = (CmsTag) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
