package net.xidlims.domain;

import java.io.Serializable;

import java.lang.StringBuilder;



import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import javax.xml.bind.annotation.*;

import javax.persistence.*;

/**
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "findAllTCourseSiteGroups", query = "select myTCourseSiteGroup from TCourseSiteGroup myTCourseSiteGroup"),
		@NamedQuery(name = "findTCourseSiteGroupByDescription", query = "select myTCourseSiteGroup from TCourseSiteGroup myTCourseSiteGroup where myTCourseSiteGroup.description = ?1"),
		@NamedQuery(name = "findTCourseSiteGroupByDescriptionContaining", query = "select myTCourseSiteGroup from TCourseSiteGroup myTCourseSiteGroup where myTCourseSiteGroup.description like ?1"),
		@NamedQuery(name = "findTCourseSiteGroupByGroupTitle", query = "select myTCourseSiteGroup from TCourseSiteGroup myTCourseSiteGroup where myTCourseSiteGroup.groupTitle = ?1"),
		@NamedQuery(name = "findTCourseSiteGroupByGroupTitleContaining", query = "select myTCourseSiteGroup from TCourseSiteGroup myTCourseSiteGroup where myTCourseSiteGroup.groupTitle like ?1"),
		@NamedQuery(name = "findTCourseSiteGroupById", query = "select myTCourseSiteGroup from TCourseSiteGroup myTCourseSiteGroup where myTCourseSiteGroup.id = ?1"),
		@NamedQuery(name = "findTCourseSiteGroupByPrimaryKey", query = "select myTCourseSiteGroup from TCourseSiteGroup myTCourseSiteGroup where myTCourseSiteGroup.id = ?1") })
@Table(catalog = "xidlims", name = "t_course_site_group")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "TCourseSiteGroup")
public class TCourseSiteGroup implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 */

	@Column(name = "id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@XmlElement
	Integer id;
	/**
	 * �γ̷�����ƣ�ƽ�а���ƣ�
	 * 
	 */

	@Column(name = "group_title")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String groupTitle;
	/**
	 * �γ̷��飨ƽ�аࣩ����
	 * 
	 */

	@Column(name = "description")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String description;

	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "site_id", referencedColumnName = "id", nullable = false) })
	@XmlTransient
	TCourseSite TCourseSite;

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
	 * �γ̷�����ƣ�ƽ�а���ƣ�
	 * 
	 */
	public void setGroupTitle(String groupTitle) {
		this.groupTitle = groupTitle;
	}

	/**
	 * �γ̷�����ƣ�ƽ�а���ƣ�
	 * 
	 */
	public String getGroupTitle() {
		return this.groupTitle;
	}

	/**
	 * �γ̷��飨ƽ�аࣩ����
	 * 
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * �γ̷��飨ƽ�аࣩ����
	 * 
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 */
	public void setTCourseSite(TCourseSite TCourseSite) {
		this.TCourseSite = TCourseSite;
	}

	/**
	 */
	public TCourseSite getTCourseSite() {
		return TCourseSite;
	}

	/**
	 */
	public TCourseSiteGroup() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(TCourseSiteGroup that) {
		setId(that.getId());
		setGroupTitle(that.getGroupTitle());
		setDescription(that.getDescription());
		setTCourseSite(that.getTCourseSite());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("id=[").append(id).append("] ");
		buffer.append("groupTitle=[").append(groupTitle).append("] ");
		buffer.append("description=[").append(description).append("] ");

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
		if (!(obj instanceof TCourseSiteGroup))
			return false;
		TCourseSiteGroup equalCheck = (TCourseSiteGroup) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
