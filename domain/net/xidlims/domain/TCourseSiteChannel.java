package net.xidlims.domain;

import java.io.Serializable;

import java.lang.StringBuilder;

import java.util.Calendar;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.codehaus.jackson.annotate.JsonIgnore;

import javax.xml.bind.annotation.*;

import javax.persistence.*;

/**
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "findAllTCourseSiteChannels", query = "select myTCourseSiteChannel from TCourseSiteChannel myTCourseSiteChannel"),
		@NamedQuery(name = "findTCourseSiteChannelByChannelName", query = "select myTCourseSiteChannel from TCourseSiteChannel myTCourseSiteChannel where myTCourseSiteChannel.channelName = ?1"),
		@NamedQuery(name = "findTCourseSiteChannelByChannelNameContaining", query = "select myTCourseSiteChannel from TCourseSiteChannel myTCourseSiteChannel where myTCourseSiteChannel.channelName like ?1"),
		@NamedQuery(name = "findTCourseSiteChannelByChannelText", query = "select myTCourseSiteChannel from TCourseSiteChannel myTCourseSiteChannel where myTCourseSiteChannel.channelText = ?1"),
		@NamedQuery(name = "findTCourseSiteChannelByChannelTextContaining", query = "select myTCourseSiteChannel from TCourseSiteChannel myTCourseSiteChannel where myTCourseSiteChannel.channelText like ?1"),
		@NamedQuery(name = "findTCourseSiteChannelByCreateUser", query = "select myTCourseSiteChannel from TCourseSiteChannel myTCourseSiteChannel where myTCourseSiteChannel.createUser = ?1"),
		@NamedQuery(name = "findTCourseSiteChannelByCreateUserContaining", query = "select myTCourseSiteChannel from TCourseSiteChannel myTCourseSiteChannel where myTCourseSiteChannel.createUser like ?1"),
		@NamedQuery(name = "findTCourseSiteChannelById", query = "select myTCourseSiteChannel from TCourseSiteChannel myTCourseSiteChannel where myTCourseSiteChannel.id = ?1"),
		@NamedQuery(name = "findTCourseSiteChannelByImageUrl", query = "select myTCourseSiteChannel from TCourseSiteChannel myTCourseSiteChannel where myTCourseSiteChannel.imageUrl = ?1"),
		@NamedQuery(name = "findTCourseSiteChannelByImageUrlContaining", query = "select myTCourseSiteChannel from TCourseSiteChannel myTCourseSiteChannel where myTCourseSiteChannel.imageUrl like ?1"),
		@NamedQuery(name = "findTCourseSiteChannelByLink", query = "select myTCourseSiteChannel from TCourseSiteChannel myTCourseSiteChannel where myTCourseSiteChannel.link = ?1"),
		@NamedQuery(name = "findTCourseSiteChannelByLinkContaining", query = "select myTCourseSiteChannel from TCourseSiteChannel myTCourseSiteChannel where myTCourseSiteChannel.link like ?1"),
		@NamedQuery(name = "findTCourseSiteChannelByPrimaryKey", query = "select myTCourseSiteChannel from TCourseSiteChannel myTCourseSiteChannel where myTCourseSiteChannel.id = ?1") })
@Table(catalog = "xidlims", name = "t_course_site_channel")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "TCourseSiteChannel")
@XmlRootElement(namespace = "xidlims/net/xidlims/domain")
public class TCourseSiteChannel implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * �γ�վ����Ŀ��
	 * 
	 */

	@Column(name = "id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@XmlElement
	Integer id;
	/**
	 * ��Ŀ���
	 * 
	 */

	@Column(name = "channelName")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String channelName;
	/**
	 * ��Ŀ����
	 * 
	 */

	@Column(name = "link")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String link;
	/**
	 * ��Ŀ��Ҫ
	 * 
	 */

	@Column(name = "channelText")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String channelText;
	/**
	 * ͼƬ
	 * 
	 */

	@Column(name = "imageURL")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String imageUrl;
	
	/**
	 * ����ʱ��
	 * 
	 */
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar createDate;
	
	public Calendar getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Calendar createDate) {
		this.createDate = createDate;
	}

	public Calendar getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Calendar modifyDate) {
		this.modifyDate = modifyDate;
	}

	/**
	 * ����ʱ��
	 * 
	 */
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modify_date")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar modifyDate;
	
	/**
	 * �����ˣ����
	 * 
	 */
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "create_user", referencedColumnName = "username") })
	@XmlTransient
	User createUser;

	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "tag_id", referencedColumnName = "id") })
	@XmlTransient
	TCourseSiteTag TCourseSiteTag;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "site_id", referencedColumnName = "id") })
	@XmlTransient
	TCourseSite TCourseSite;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "state", referencedColumnName = "id") })
	@XmlTransient
	CDictionary CDictionary;
	/**
	 */
	@ManyToMany(cascade =  {CascadeType.REMOVE}, fetch = FetchType.LAZY)
	@JoinTable(catalog = "xidlims", name = "t_channel_parentchannel", joinColumns = { @JoinColumn(name = "parentChannel_id", referencedColumnName = "id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "channel_id", referencedColumnName = "id", nullable = false, updatable = false) })
	@XmlElement(name = "", namespace = "")
	@OrderBy(value="id ASC")
	java.util.Set<net.xidlims.domain.TCourseSiteChannel> TCourseSiteChannelsForChannelId;
	/**
	 */
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(catalog = "xidlims", name = "t_channel_parentchannel", joinColumns = { @JoinColumn(name = "channel_id", referencedColumnName = "id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "parentChannel_id", referencedColumnName = "id", nullable = false, updatable = false) })
	@XmlElement(name = "", namespace = "")
	@OrderBy(value="id ASC")
	java.util.Set<net.xidlims.domain.TCourseSiteChannel> TCourseSiteChannelsForParentChannelId;
	/**
	 */
	@OneToMany(mappedBy = "TCourseSiteChannel", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	@OrderBy("id ASC")
	java.util.Set<net.xidlims.domain.TCourseSiteArtical> TCourseSiteArticals;

	/**
	 * �γ�վ����Ŀ��
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * �γ�վ����Ŀ��
	 * 
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * ��Ŀ���
	 * 
	 */
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	/**
	 * ��Ŀ���
	 * 
	 */
	public String getChannelName() {
		return this.channelName;
	}

	/**
	 * ��Ŀ����
	 * 
	 */
	public void setLink(String link) {
		this.link = link;
	}

	/**
	 * ��Ŀ����
	 * 
	 */
	public String getLink() {
		return this.link;
	}

	/**
	 * ��Ŀ��Ҫ
	 * 
	 */
	public void setChannelText(String channelText) {
		this.channelText = channelText;
	}

	/**
	 * ��Ŀ��Ҫ
	 * 
	 */
	public String getChannelText() {
		return this.channelText;
	}

	/**
	 * ͼƬ
	 * 
	 */
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	/**
	 * ͼƬ
	 * 
	 */
	public String getImageUrl() {
		return this.imageUrl;
	}

	/**
	 * �����ˣ����
	 * 
	 */
	public void setCreateUser(User createUser) {
		this.createUser = createUser;
	}

	/**
	 * �����ˣ����
	 * 
	 */
	public User getCreateUser() {
		return this.createUser;
	}

	/**
	 */
	public void setTCourseSiteTag(TCourseSiteTag TCourseSiteTag) {
		this.TCourseSiteTag = TCourseSiteTag;
	}

	/**
	 */
	@JsonIgnore
	public TCourseSiteTag getTCourseSiteTag() {
		return TCourseSiteTag;
	}

	
	public TCourseSite getTCourseSite() {
		return TCourseSite;
	}

	public void setTCourseSite(TCourseSite tCourseSite) {
		TCourseSite = tCourseSite;
	}

	@JsonIgnore
	public CDictionary getCDictionary() {
		return CDictionary;
	}

	public void setCDictionary(CDictionary cDictionary) {
		CDictionary = cDictionary;
	}

	/**
	 */
	public void setTCourseSiteChannelsForChannelId(Set<TCourseSiteChannel> TCourseSiteChannelsForChannelId) {
		this.TCourseSiteChannelsForChannelId = TCourseSiteChannelsForChannelId;
	}

	/**
	 */
	@JsonIgnore
	public Set<TCourseSiteChannel> getTCourseSiteChannelsForChannelId() {
		if (TCourseSiteChannelsForChannelId == null) {
			TCourseSiteChannelsForChannelId = new java.util.LinkedHashSet<net.xidlims.domain.TCourseSiteChannel>();
		}
		return TCourseSiteChannelsForChannelId;
	}

	/**
	 */
	public void setTCourseSiteChannelsForParentChannelId(Set<TCourseSiteChannel> TCourseSiteChannelsForParentChannelId) {
		this.TCourseSiteChannelsForParentChannelId = TCourseSiteChannelsForParentChannelId;
	}

	/**
	 */
	@JsonIgnore
	public Set<TCourseSiteChannel> getTCourseSiteChannelsForParentChannelId() {
		if (TCourseSiteChannelsForParentChannelId == null) {
			TCourseSiteChannelsForParentChannelId = new java.util.LinkedHashSet<net.xidlims.domain.TCourseSiteChannel>();
		}
		return TCourseSiteChannelsForParentChannelId;
	}

	/**
	 */
	public void setTCourseSiteArticals(Set<TCourseSiteArtical> TCourseSiteArticals) {
		this.TCourseSiteArticals = TCourseSiteArticals;
	}

	/**
	 */
	@JsonIgnore
	public Set<TCourseSiteArtical> getTCourseSiteArticals() {
		if (TCourseSiteArticals == null) {
			TCourseSiteArticals = new java.util.LinkedHashSet<net.xidlims.domain.TCourseSiteArtical>();
		}
		return TCourseSiteArticals;
	}

	/**
	 */
	public TCourseSiteChannel() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(TCourseSiteChannel that) {
		setId(that.getId());
		setChannelName(that.getChannelName());
		setLink(that.getLink());
		setChannelText(that.getChannelText());
		setImageUrl(that.getImageUrl());
		setCreateUser(that.getCreateUser());
		setCreateDate(that.getCreateDate());
		setModifyDate(that.getModifyDate());
		setTCourseSiteTag(that.getTCourseSiteTag());
		setCDictionary(that.getCDictionary());
		setTCourseSite(that.getTCourseSite());
		setTCourseSiteChannelsForChannelId(new java.util.LinkedHashSet<net.xidlims.domain.TCourseSiteChannel>(that.getTCourseSiteChannelsForChannelId()));
		setTCourseSiteChannelsForParentChannelId(new java.util.LinkedHashSet<net.xidlims.domain.TCourseSiteChannel>(that.getTCourseSiteChannelsForParentChannelId()));
		setTCourseSiteArticals(new java.util.LinkedHashSet<net.xidlims.domain.TCourseSiteArtical>(that.getTCourseSiteArticals()));
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("id=[").append(id).append("] ");
		buffer.append("channelName=[").append(channelName).append("] ");
		buffer.append("link=[").append(link).append("] ");
		buffer.append("channelText=[").append(channelText).append("] ");
		buffer.append("imageUrl=[").append(imageUrl).append("] ");
		buffer.append("createUser=[").append(createUser).append("] ");
		buffer.append("createDate=[").append(createDate).append("] ");
		buffer.append("modifyDate=[").append(modifyDate).append("] ");

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
		if (!(obj instanceof TCourseSiteChannel))
			return false;
		TCourseSiteChannel equalCheck = (TCourseSiteChannel) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
