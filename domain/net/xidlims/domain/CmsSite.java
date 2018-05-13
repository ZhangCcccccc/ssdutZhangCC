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
		@NamedQuery(name = "findAllCmsSites", query = "select myCmsSite from CmsSite myCmsSite"),
		@NamedQuery(name = "findCmsSiteByBottomContent", query = "select myCmsSite from CmsSite myCmsSite where myCmsSite.bottomContent = ?1"),
		@NamedQuery(name = "findCmsSiteByCreateTime", query = "select myCmsSite from CmsSite myCmsSite where myCmsSite.createTime = ?1"),
		@NamedQuery(name = "findCmsSiteByCurrent", query = "select myCmsSite from CmsSite myCmsSite where myCmsSite.current = ?1"),
		@NamedQuery(name = "findCmsSiteByImageResource", query = "select myCmsSite from CmsSite myCmsSite where myCmsSite.imageResource = ?1"),
		@NamedQuery(name = "findCmsSiteByName", query = "select myCmsSite from CmsSite myCmsSite where myCmsSite.name = ?1"),
		@NamedQuery(name = "findCmsSiteByNameContaining", query = "select myCmsSite from CmsSite myCmsSite where myCmsSite.name like ?1"),
		@NamedQuery(name = "findCmsSiteByPrimaryKey", query = "select myCmsSite from CmsSite myCmsSite where myCmsSite.siteurl = ?1"),
		@NamedQuery(name = "findCmsSiteByProfile", query = "select myCmsSite from CmsSite myCmsSite where myCmsSite.profile = ?1"),
		@NamedQuery(name = "findCmsSiteByProfileContaining", query = "select myCmsSite from CmsSite myCmsSite where myCmsSite.profile like ?1"),
		@NamedQuery(name = "findCmsSiteBySiteurl", query = "select myCmsSite from CmsSite myCmsSite where myCmsSite.siteurl = ?1"),
		@NamedQuery(name = "findCmsSiteBySiteurlContaining", query = "select myCmsSite from CmsSite myCmsSite where myCmsSite.siteurl like ?1"),
		@NamedQuery(name = "findCmsSiteByState", query = "select myCmsSite from CmsSite myCmsSite where myCmsSite.state = ?1"),
		@NamedQuery(name = "findCmsSiteByVideoResource", query = "select myCmsSite from CmsSite myCmsSite where myCmsSite.videoResource = ?1") })
@Table(catalog = "xidlims", name = "cms_site")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/com/xidlims/domain", name = "CmsSite")
@XmlRootElement(namespace = "xidlims/com/xidlims/domain")
public class CmsSite implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ��վ����
	 * 
	 */

	@Column(name = "siteurl", length = 20, nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	@XmlElement
	String siteurl;
	/**
	 * ��վ���
	 * 
	 */

	@Column(name = "name")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String name;
	/**
	 * logoͼƬ����
	 * 
	 */

	@Column(name = "image_resource")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer imageResource;
	/**
	 * ��Ƶ
	 * 
	 */

	@Column(name = "video_resource")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer videoResource;
	/**
	 * �ײ�����
	 * 
	 */

	@Column(name = "bottom_content", columnDefinition = "TEXT")
	@Basic(fetch = FetchType.EAGER)
	@Lob
	@XmlElement
	String bottomContent;
	/**
	 * ״̬�����ã��ر�
	 * 
	 */

	@Column(name = "state")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer state;
	/**
	 * ����ʱ��
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar createTime;
	/**
	 */

	@Column(name = "profile")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String profile;
	/**
	 */

	@Column(name = "current")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer current;

	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "template_id", referencedColumnName = "id", nullable = false) })
	@XmlTransient
	CmsTemplate cmsTemplate;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "create_user", referencedColumnName = "username") })
	@XmlTransient
	User user;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "image_attachment", referencedColumnName = "id") })
	@XmlTransient
	CmsResource cmsResource;
	/**
	 */
	@OneToMany(mappedBy = "cmsSite", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.CmsChannel> cmsChannels;
	/**
	 */
	@OneToMany(mappedBy = "cmsSite", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.CmsLink> cmsLinks;

	/**
	 * ��վ����
	 * 
	 */
	public void setSiteurl(String siteurl) {
		this.siteurl = siteurl;
	}

	/**
	 * ��վ����
	 * 
	 */
	public String getSiteurl() {
		return this.siteurl;
	}

	/**
	 * ��վ���
	 * 
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * ��վ���
	 * 
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * logoͼƬ����
	 * 
	 */
	public void setImageResource(Integer imageResource) {
		this.imageResource = imageResource;
	}

	/**
	 * logoͼƬ����
	 * 
	 */
	public Integer getImageResource() {
		return this.imageResource;
	}

	/**
	 * ��Ƶ
	 * 
	 */
	public void setVideoResource(Integer videoResource) {
		this.videoResource = videoResource;
	}

	/**
	 * ��Ƶ
	 * 
	 */
	public Integer getVideoResource() {
		return this.videoResource;
	}

	/**
	 * �ײ�����
	 * 
	 */
	public void setBottomContent(String bottomContent) {
		this.bottomContent = bottomContent;
	}

	/**
	 * �ײ�����
	 * 
	 */
	public String getBottomContent() {
		return this.bottomContent;
	}

	/**
	 * ״̬�����ã��ر�
	 * 
	 */
	public void setState(Integer state) {
		this.state = state;
	}

	/**
	 * ״̬�����ã��ر�
	 * 
	 */
	public Integer getState() {
		return this.state;
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
	public void setCurrent(Integer current) {
		this.current = current;
	}

	/**
	 */
	public Integer getCurrent() {
		return this.current;
	}

	/**
	 */
	public void setCmsTemplate(CmsTemplate cmsTemplate) {
		this.cmsTemplate = cmsTemplate;
	}

	/**
	 */
	@JsonIgnore
	public CmsTemplate getCmsTemplate() {
		return cmsTemplate;
	}

	/**
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 */
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
	public CmsResource getCmsResource() {
		return cmsResource;
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
	public CmsSite() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(CmsSite that) {
		setSiteurl(that.getSiteurl());
		setName(that.getName());
		setImageResource(that.getImageResource());
		setVideoResource(that.getVideoResource());
		setBottomContent(that.getBottomContent());
		setState(that.getState());
		setCreateTime(that.getCreateTime());
		setProfile(that.getProfile());
		setCurrent(that.getCurrent());
		setCmsTemplate(that.getCmsTemplate());
		setUser(that.getUser());
		setCmsResource(that.getCmsResource());
		setCmsChannels(new java.util.LinkedHashSet<net.xidlims.domain.CmsChannel>(that.getCmsChannels()));
		setCmsLinks(new java.util.LinkedHashSet<net.xidlims.domain.CmsLink>(that.getCmsLinks()));
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("siteurl=[").append(siteurl).append("] ");
		buffer.append("name=[").append(name).append("] ");
		buffer.append("imageResource=[").append(imageResource).append("] ");
		buffer.append("videoResource=[").append(videoResource).append("] ");
		buffer.append("bottomContent=[").append(bottomContent).append("] ");
		buffer.append("state=[").append(state).append("] ");
		buffer.append("createTime=[").append(createTime).append("] ");
		buffer.append("profile=[").append(profile).append("] ");
		buffer.append("current=[").append(current).append("] ");

		return buffer.toString();
	}

	/**
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((siteurl == null) ? 0 : siteurl.hashCode()));
		return result;
	}

	/**
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof CmsSite))
			return false;
		CmsSite equalCheck = (CmsSite) obj;
		if ((siteurl == null && equalCheck.siteurl != null) || (siteurl != null && equalCheck.siteurl == null))
			return false;
		if (siteurl != null && !siteurl.equals(equalCheck.siteurl))
			return false;
		return true;
	}
}
