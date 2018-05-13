package net.xidlims.domain;

import java.io.Serializable;

import java.lang.StringBuilder;


import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import org.codehaus.jackson.annotate.JsonIgnore;

import javax.xml.bind.annotation.*;

import javax.persistence.*;

/**
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "findAllTCourseSiteTags", query = "select myTCourseSiteTag from TCourseSiteTag myTCourseSiteTag"),
		@NamedQuery(name = "findTCourseSiteTagByDescription", query = "select myTCourseSiteTag from TCourseSiteTag myTCourseSiteTag where myTCourseSiteTag.description = ?1"),
		@NamedQuery(name = "findTCourseSiteTagByDescriptionContaining", query = "select myTCourseSiteTag from TCourseSiteTag myTCourseSiteTag where myTCourseSiteTag.description like ?1"),
		@NamedQuery(name = "findTCourseSiteTagById", query = "select myTCourseSiteTag from TCourseSiteTag myTCourseSiteTag where myTCourseSiteTag.id = ?1"),
		@NamedQuery(name = "findTCourseSiteTagByPrimaryKey", query = "select myTCourseSiteTag from TCourseSiteTag myTCourseSiteTag where myTCourseSiteTag.id = ?1"),
		@NamedQuery(name = "findTCourseSiteTagBySiteTag", query = "select myTCourseSiteTag from TCourseSiteTag myTCourseSiteTag where myTCourseSiteTag.siteTag = ?1"),
		@NamedQuery(name = "findTCourseSiteTagBySiteTagContaining", query = "select myTCourseSiteTag from TCourseSiteTag myTCourseSiteTag where myTCourseSiteTag.siteTag like ?1"),
		@NamedQuery(name = "findTCourseSiteTagBySiteTagText", query = "select myTCourseSiteTag from TCourseSiteTag myTCourseSiteTag where myTCourseSiteTag.siteTagText = ?1"),
		@NamedQuery(name = "findTCourseSiteTagBySiteTagTextContaining", query = "select myTCourseSiteTag from TCourseSiteTag myTCourseSiteTag where myTCourseSiteTag.siteTagText like ?1"),
		@NamedQuery(name = "findTCourseSiteTagByType", query = "select myTCourseSiteTag from TCourseSiteTag myTCourseSiteTag where myTCourseSiteTag.type = ?1") })
@Table(catalog = "xidlims", name = "t_course_site_tag")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "TCourseSiteTag")
@XmlRootElement(namespace = "xidlims/net/xidlims/domain")
public class TCourseSiteTag implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * �γ̽��ܣ�CMS����Ŀ��
	 * 
	 */

	@Column(name = "id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@XmlElement
	Integer id;
	/**
	 * ��ǩ���
	 * 
	 */

	@Column(name = "site_tag")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String siteTag;
	/**
	 * ��ǩ��Ҫ
	 * 
	 */

	@Column(name = "site_tag_text")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String siteTagText;
	/**
	 * ���
	 * 
	 */

	@Column(name = "description")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String description;
	/**
	 * ���ͣ�1��ʾ��Ŀ��ǩ2��ʾ���±�ǩ��
	 * 
	 */

	@Column(name = "type")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer type;

	/**
	 */
	@OneToMany(mappedBy = "TCourseSiteTag", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.TCourseSiteArtical> TCourseSiteArticals;
	/**
	 */
	@OneToMany(mappedBy = "TCourseSiteTag", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.TCourseSiteChannel> TCourseSiteChannels;
	

	/**
	 * �γ̽��ܣ�CMS����Ŀ��
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * �γ̽��ܣ�CMS����Ŀ��
	 * 
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * ��ǩ���
	 * 
	 */
	public void setSiteTag(String siteTag) {
		this.siteTag = siteTag;
	}

	/**
	 * ��ǩ���
	 * 
	 */
	public String getSiteTag() {
		return this.siteTag;
	}

	/**
	 * ��ǩ��Ҫ
	 * 
	 */
	public void setSiteTagText(String siteTagText) {
		this.siteTagText = siteTagText;
	}

	/**
	 * ��ǩ��Ҫ
	 * 
	 */
	public String getSiteTagText() {
		return this.siteTagText;
	}

	/**
	 * ���
	 * 
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * ���
	 * 
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * ���ͣ�1��ʾ��Ŀ��ǩ2��ʾ���±�ǩ��
	 * 
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * ���ͣ�1��ʾ��Ŀ��ǩ2��ʾ���±�ǩ��
	 * 
	 */
	public Integer getType() {
		return this.type;
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
	public void setTCourseSiteChannels(Set<TCourseSiteChannel> TCourseSiteChannels) {
		this.TCourseSiteChannels = TCourseSiteChannels;
	}

	/**
	 */
	@JsonIgnore
	public Set<TCourseSiteChannel> getTCourseSiteChannels() {
		if (TCourseSiteChannels == null) {
			TCourseSiteChannels = new java.util.LinkedHashSet<net.xidlims.domain.TCourseSiteChannel>();
		}
		return TCourseSiteChannels;
	}

	/**
	 */
	public TCourseSiteTag() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(TCourseSiteTag that) {
		setId(that.getId());
		setSiteTag(that.getSiteTag());
		setSiteTagText(that.getSiteTagText());
		setDescription(that.getDescription());
		setType(that.getType());
		setTCourseSiteArticals(new java.util.LinkedHashSet<net.xidlims.domain.TCourseSiteArtical>(that.getTCourseSiteArticals()));
		setTCourseSiteChannels(new java.util.LinkedHashSet<net.xidlims.domain.TCourseSiteChannel>(that.getTCourseSiteChannels()));
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("id=[").append(id).append("] ");
		buffer.append("siteTag=[").append(siteTag).append("] ");
		buffer.append("siteTagText=[").append(siteTagText).append("] ");
		buffer.append("description=[").append(description).append("] ");
		buffer.append("type=[").append(type).append("] ");

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
		if (!(obj instanceof TCourseSiteTag))
			return false;
		TCourseSiteTag equalCheck = (TCourseSiteTag) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
