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
		@NamedQuery(name = "findAllCmsTemplates", query = "select myCmsTemplate from CmsTemplate myCmsTemplate"),
		@NamedQuery(name = "findCmsTemplateById", query = "select myCmsTemplate from CmsTemplate myCmsTemplate where myCmsTemplate.id = ?1"),
		@NamedQuery(name = "findCmsTemplateByName", query = "select myCmsTemplate from CmsTemplate myCmsTemplate where myCmsTemplate.name = ?1"),
		@NamedQuery(name = "findCmsTemplateByNameContaining", query = "select myCmsTemplate from CmsTemplate myCmsTemplate where myCmsTemplate.name like ?1"),
		@NamedQuery(name = "findCmsTemplateByPrimaryKey", query = "select myCmsTemplate from CmsTemplate myCmsTemplate where myCmsTemplate.id = ?1"),
		@NamedQuery(name = "findCmsTemplateByProfile", query = "select myCmsTemplate from CmsTemplate myCmsTemplate where myCmsTemplate.profile = ?1"),
		@NamedQuery(name = "findCmsTemplateByProfileContaining", query = "select myCmsTemplate from CmsTemplate myCmsTemplate where myCmsTemplate.profile like ?1"),
		@NamedQuery(name = "findCmsTemplateByState", query = "select myCmsTemplate from CmsTemplate myCmsTemplate where myCmsTemplate.state = ?1") })
@Table(catalog = "xidlims", name = "cms_template")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/com/xidlims/domain", name = "CmsTemplate")
@XmlRootElement(namespace = "xidlims/com/xidlims/domain")
public class CmsTemplate implements Serializable {
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
	 * ģ�����
	 * 
	 */

	@Column(name = "name")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String name;
	/**
	 * ��Ҫ
	 * 
	 */

	@Column(name = "profile")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String profile;
	/**
	 * �Ƿ�ʹ��
	 * 
	 */

	@Column(name = "state")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer state;

	/**
	 */
	@OneToMany(mappedBy = "cmsTemplate", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
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
	 * ģ�����
	 * 
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * ģ�����
	 * 
	 */
	public String getName() {
		return this.name;
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
	 * �Ƿ�ʹ��
	 * 
	 */
	public void setState(Integer state) {
		this.state = state;
	}

	/**
	 * �Ƿ�ʹ��
	 * 
	 */
	public Integer getState() {
		return this.state;
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
	public CmsTemplate() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(CmsTemplate that) {
		setId(that.getId());
		setName(that.getName());
		setProfile(that.getProfile());
		setState(that.getState());
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
		buffer.append("profile=[").append(profile).append("] ");
		buffer.append("state=[").append(state).append("] ");

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
		if (!(obj instanceof CmsTemplate))
			return false;
		CmsTemplate equalCheck = (CmsTemplate) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
