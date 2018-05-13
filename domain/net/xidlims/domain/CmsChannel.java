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
		@NamedQuery(name = "findAllCmsChannels", query = "select myCmsChannel from CmsChannel myCmsChannel"),
		@NamedQuery(name = "findCmsChannelByCreateTime", query = "select myCmsChannel from CmsChannel myCmsChannel where myCmsChannel.createTime = ?1"),
		@NamedQuery(name = "findCmsChannelByHyperlink", query = "select myCmsChannel from CmsChannel myCmsChannel where myCmsChannel.hyperlink = ?1"),
		@NamedQuery(name = "findCmsChannelByHyperlinkContaining", query = "select myCmsChannel from CmsChannel myCmsChannel where myCmsChannel.hyperlink like ?1"),
		@NamedQuery(name = "findCmsChannelById", query = "select myCmsChannel from CmsChannel myCmsChannel where myCmsChannel.id = ?1"),
		@NamedQuery(name = "findCmsChannelByPrimaryKey", query = "select myCmsChannel from CmsChannel myCmsChannel where myCmsChannel.id = ?1"),
		@NamedQuery(name = "findCmsChannelByProfile", query = "select myCmsChannel from CmsChannel myCmsChannel where myCmsChannel.profile = ?1"),
		@NamedQuery(name = "findCmsChannelByProfileContaining", query = "select myCmsChannel from CmsChannel myCmsChannel where myCmsChannel.profile like ?1"),
		@NamedQuery(name = "findCmsChannelByReadNum", query = "select myCmsChannel from CmsChannel myCmsChannel where myCmsChannel.readNum = ?1"),
		@NamedQuery(name = "findCmsChannelBySort", query = "select myCmsChannel from CmsChannel myCmsChannel where myCmsChannel.sort = ?1"),
		@NamedQuery(name = "findCmsChannelByState", query = "select myCmsChannel from CmsChannel myCmsChannel where myCmsChannel.state = ?1"),
		@NamedQuery(name = "findCmsChannelByTitle", query = "select myCmsChannel from CmsChannel myCmsChannel where myCmsChannel.title = ?1"),
		@NamedQuery(name = "findCmsChannelByTitleContaining", query = "select myCmsChannel from CmsChannel myCmsChannel where myCmsChannel.title like ?1") })
@Table(catalog = "xidlims", name = "cms_channel")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/com/xidlims/domain", name = "CmsChannel")
@XmlRootElement(namespace = "xidlims/com/xidlims/domain")
public class CmsChannel implements Serializable {
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
	 * ��Ŀ���
	 * 
	 */

	@Column(name = "title", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String title;
	/**
	 */

	@Column(name = "sort")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer sort;
	/**
	 * ������
	 * 
	 */

	@Column(name = "hyperlink")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String hyperlink;
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
	@Column(name = "create_time", nullable = true)//表中为不能为空
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar createTime;

	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "top_id", referencedColumnName = "id") })
	@XmlTransient
	CmsChannel cmsChannel;
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
	@JoinColumns({ @JoinColumn(name = "site_id", referencedColumnName = "siteurl", nullable = false) })
	@XmlTransient
	CmsSite cmsSite;
	/**
	 */
	@ManyToMany(mappedBy = "cmsChannels", fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.CmsTag> cmsTags;
	/**
	 */
	@OneToMany(mappedBy = "cmsChannel", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.CmsChannel> cmsChannels;
	/**
	 */
	@OneToMany(mappedBy = "cmsChannel", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.CmsArticle> cmsArticles;

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
	 * ��Ŀ���
	 * 
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * ��Ŀ���
	 * 
	 */
	public String getTitle() {
		return this.title;
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
	 * ������
	 * 
	 */
	public void setHyperlink(String hyperlink) {
		this.hyperlink = hyperlink;
	}

	/**
	 * ������
	 * 
	 */
	public String getHyperlink() {
		return this.hyperlink;
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
	public CmsChannel() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(CmsChannel that) {
		setId(that.getId());
		setTitle(that.getTitle());
		setSort(that.getSort());
		setHyperlink(that.getHyperlink());
		setProfile(that.getProfile());
		setState(that.getState());
		setReadNum(that.getReadNum());
		setCreateTime(that.getCreateTime());
		setCmsChannel(that.getCmsChannel());
		setUser(that.getUser());
		setCmsResource(that.getCmsResource());
		setCmsSite(that.getCmsSite());
		setCmsTags(new java.util.LinkedHashSet<net.xidlims.domain.CmsTag>(that.getCmsTags()));
		setCmsChannels(new java.util.LinkedHashSet<net.xidlims.domain.CmsChannel>(that.getCmsChannels()));
		setCmsArticles(new java.util.LinkedHashSet<net.xidlims.domain.CmsArticle>(that.getCmsArticles()));
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("id=[").append(id).append("] ");
		buffer.append("title=[").append(title).append("] ");
		buffer.append("sort=[").append(sort).append("] ");
		buffer.append("hyperlink=[").append(hyperlink).append("] ");
		buffer.append("profile=[").append(profile).append("] ");
		buffer.append("state=[").append(state).append("] ");
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
		if (!(obj instanceof CmsChannel))
			return false;
		CmsChannel equalCheck = (CmsChannel) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
