package net.xidlims.domain;

import java.io.Serializable;

import java.lang.StringBuilder;



import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import javax.xml.bind.annotation.*;

import javax.persistence.*;

/**
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "findAllTCourseSitePages", query = "select myTCourseSitePage from TCourseSitePage myTCourseSitePage"),
		@NamedQuery(name = "findTCourseSitePageById", query = "select myTCourseSitePage from TCourseSitePage myTCourseSitePage where myTCourseSitePage.id = ?1"),
		@NamedQuery(name = "findTCourseSitePageByPopup", query = "select myTCourseSitePage from TCourseSitePage myTCourseSitePage where myTCourseSitePage.popup = ?1"),
		@NamedQuery(name = "findTCourseSitePageByPrimaryKey", query = "select myTCourseSitePage from TCourseSitePage myTCourseSitePage where myTCourseSitePage.id = ?1"),
		@NamedQuery(name = "findTCourseSitePageBySiteOrder", query = "select myTCourseSitePage from TCourseSitePage myTCourseSitePage where myTCourseSitePage.siteOrder = ?1"),
		@NamedQuery(name = "findTCourseSitePageByTitle", query = "select myTCourseSitePage from TCourseSitePage myTCourseSitePage where myTCourseSitePage.title = ?1"),
		@NamedQuery(name = "findTCourseSitePageByTitleContaining", query = "select myTCourseSitePage from TCourseSitePage myTCourseSitePage where myTCourseSitePage.title like ?1") })
@Table(catalog = "xidlims", name = "t_course_site_page")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "TCourseSitePage")
public class TCourseSitePage implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 */

	@Column(name = "id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	Integer id;
	/**
	 * ��ҳ���
	 * 
	 */

	@Column(name = "title")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String title;
	/**
	 * ��ҳ����
	 * 
	 */

	@Column(name = "site_order")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer siteOrder;
	/**
	 * �Ƿ񵯳���1������0������
	 * 
	 */

	@Column(name = "popup")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer popup;

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
	 * ��ҳ���
	 * 
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * ��ҳ���
	 * 
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * ��ҳ����
	 * 
	 */
	public void setSiteOrder(Integer siteOrder) {
		this.siteOrder = siteOrder;
	}

	/**
	 * ��ҳ����
	 * 
	 */
	public Integer getSiteOrder() {
		return this.siteOrder;
	}

	/**
	 * �Ƿ񵯳���1������0������
	 * 
	 */
	public void setPopup(Integer popup) {
		this.popup = popup;
	}

	/**
	 * �Ƿ񵯳���1������0������
	 * 
	 */
	public Integer getPopup() {
		return this.popup;
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
	public TCourseSitePage() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(TCourseSitePage that) {
		setId(that.getId());
		setTitle(that.getTitle());
		setSiteOrder(that.getSiteOrder());
		setPopup(that.getPopup());
		setTCourseSite(that.getTCourseSite());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("id=[").append(id).append("] ");
		buffer.append("title=[").append(title).append("] ");
		buffer.append("siteOrder=[").append(siteOrder).append("] ");
		buffer.append("popup=[").append(popup).append("] ");

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
		if (!(obj instanceof TCourseSitePage))
			return false;
		TCourseSitePage equalCheck = (TCourseSitePage) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
