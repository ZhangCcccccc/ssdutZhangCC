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
		@NamedQuery(name = "findAllCmsArticles", query = "select myCmsArticle from CmsArticle myCmsArticle"),
		@NamedQuery(name = "findCmsArticleByCreateTime", query = "select myCmsArticle from CmsArticle myCmsArticle where myCmsArticle.createTime = ?1"),
		@NamedQuery(name = "findCmsArticleById", query = "select myCmsArticle from CmsArticle myCmsArticle where myCmsArticle.id = ?1"),
		@NamedQuery(name = "findCmsArticleByNews", query = "select myCmsArticle from CmsArticle myCmsArticle where myCmsArticle.news = ?1"),
		@NamedQuery(name = "findCmsArticleByPrimaryKey", query = "select myCmsArticle from CmsArticle myCmsArticle where myCmsArticle.id = ?1"),
		@NamedQuery(name = "findCmsArticleByProfile", query = "select myCmsArticle from CmsArticle myCmsArticle where myCmsArticle.profile = ?1"),
		@NamedQuery(name = "findCmsArticleByProfileContaining", query = "select myCmsArticle from CmsArticle myCmsArticle where myCmsArticle.profile like ?1"),
		@NamedQuery(name = "findCmsArticleByReadNum", query = "select myCmsArticle from CmsArticle myCmsArticle where myCmsArticle.readNum = ?1"),
		@NamedQuery(name = "findCmsArticleBySort", query = "select myCmsArticle from CmsArticle myCmsArticle where myCmsArticle.sort = ?1"),
		@NamedQuery(name = "findCmsArticleByState", query = "select myCmsArticle from CmsArticle myCmsArticle where myCmsArticle.state = ?1"),
		@NamedQuery(name = "findCmsArticleByTitle", query = "select myCmsArticle from CmsArticle myCmsArticle where myCmsArticle.title = ?1"),
		@NamedQuery(name = "findCmsArticleByTitleContaining", query = "select myCmsArticle from CmsArticle myCmsArticle where myCmsArticle.title like ?1") })
@Table(catalog = "xidlims", name = "cms_article")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/com/xidlims/domain", name = "CmsArticle")
public class CmsArticle implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 * 
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

	@Column(name = "title", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String title;
	/**
	 * ����
	 * 
	 */

	@Column(name = "news", columnDefinition = "LONGTEXT")
	@Basic(fetch = FetchType.EAGER)
	@Lob
	@XmlElement
	String news;
	/**
	 * ��Ҫ
	 * 
	 */

	@Column(name = "profile")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String profile;
	/**
	 * ״̬����ʾ������
	 * 
	 */

	@Column(name = "state", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer state;
	/**
	 * ����
	 * 
	 */

	@Column(name = "sort")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer sort;
	/**
	 * �Ķ�����
	 * 
	 */

	@Column(name = "read_num")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer readNum;
	/**
	 * ����ʱ��
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time", nullable = true)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar createTime;

	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "create_user", referencedColumnName = "username") })
	@XmlTransient
	User user;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "resource_id", referencedColumnName = "id") })
	@XmlTransient
	CmsResource cmsResource;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "channel_id", referencedColumnName = "id", nullable = false) })
	@XmlTransient
	CmsChannel cmsChannel;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "document_id", referencedColumnName = "id") })
	@XmlTransient
	CmsDocument cmsDocument;
	/**
	 */
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(catalog = "xidlims", name = "cms_tag_article", joinColumns = { @JoinColumn(name = "article_id", referencedColumnName = "id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "tag_id", referencedColumnName = "id", nullable = false, updatable = false) })
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.CmsTag> cmsTags;

	/**
	 * id
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * id
	 * 
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * ����
	 * 
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * ����
	 * 
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * ����
	 * 
	 */
	public void setNews(String news) {
		this.news = news;
	}

	/**
	 * ����
	 * 
	 */
	public String getNews() {
		return this.news;
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
	 * ״̬����ʾ������
	 * 
	 */
	public void setState(Integer state) {
		this.state = state;
	}

	/**
	 * ״̬����ʾ������
	 * 
	 */
	public Integer getState() {
		return this.state;
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
	 * �Ķ�����
	 * 
	 */
	public void setReadNum(Integer readNum) {
		this.readNum = readNum;
	}

	/**
	 * �Ķ�����
	 * 
	 */
	public Integer getReadNum() {
		return this.readNum;
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
	public void setCmsChannel(CmsChannel cmsChannel) {
		this.cmsChannel = cmsChannel;
	}

	/**
	 */
	@JsonIgnore
	public CmsChannel getCmsChannel() {
		return cmsChannel;
	}

	/**
	 */
	public void setCmsDocument(CmsDocument cmsDocument) {
		this.cmsDocument = cmsDocument;
	}

	/**
	 */
	@JsonIgnore
	public CmsDocument getCmsDocument() {
		return cmsDocument;
	}

	/**
	 */
	public void setCmsTags(Set<CmsTag> cmsTags) {
		this.cmsTags = cmsTags;
	}

	/**
	 */
	@JsonIgnore
	public Set<CmsTag> getCmsTags() {
		if (cmsTags == null) {
			cmsTags = new java.util.LinkedHashSet<net.xidlims.domain.CmsTag>();
		}
		return cmsTags;
	}

	/**
	 */
	public CmsArticle() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(CmsArticle that) {
		setId(that.getId());
		setTitle(that.getTitle());
		setNews(that.getNews());
		setProfile(that.getProfile());
		setState(that.getState());
		setSort(that.getSort());
		setReadNum(that.getReadNum());
		setCreateTime(that.getCreateTime());
		setUser(that.getUser());
		setCmsResource(that.getCmsResource());
		setCmsChannel(that.getCmsChannel());
		setCmsDocument(that.getCmsDocument());
		setCmsTags(new java.util.LinkedHashSet<net.xidlims.domain.CmsTag>(that.getCmsTags()));
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("id=[").append(id).append("] ");
		buffer.append("title=[").append(title).append("] ");
		buffer.append("news=[").append(news).append("] ");
		buffer.append("profile=[").append(profile).append("] ");
		buffer.append("state=[").append(state).append("] ");
		buffer.append("sort=[").append(sort).append("] ");
		buffer.append("readNum=[").append(readNum).append("] ");
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
		if (!(obj instanceof CmsArticle))
			return false;
		CmsArticle equalCheck = (CmsArticle) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
