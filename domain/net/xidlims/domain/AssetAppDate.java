package net.xidlims.domain;

import java.io.Serializable;

import java.lang.StringBuilder;

import java.util.Calendar;

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
		@NamedQuery(name = "findAllAssetAppDates", query = "select myAssetAppDate from AssetAppDate myAssetAppDate"),
		@NamedQuery(name = "findAssetAppDateByEndDate", query = "select myAssetAppDate from AssetAppDate myAssetAppDate where myAssetAppDate.endDate = ?1"),
		@NamedQuery(name = "findAssetAppDateById", query = "select myAssetAppDate from AssetAppDate myAssetAppDate where myAssetAppDate.id = ?1"),
		@NamedQuery(name = "findAssetAppDateByPrimaryKey", query = "select myAssetAppDate from AssetAppDate myAssetAppDate where myAssetAppDate.id = ?1"),
		@NamedQuery(name = "findAssetAppDateByStartDate", query = "select myAssetAppDate from AssetAppDate myAssetAppDate where myAssetAppDate.startDate = ?1") })
@Table(catalog = "xidlims", name = "asset_app_date")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "AssetAppDate")
public class AssetAppDate implements Serializable {
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
	 * �깺ʱ��Ŀ�ʼ��Χ
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "start_date")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar startDate;
	/**
	 * �깺ʱ��Ľ���Χ
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "end_date")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar endDate;

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
	 * �깺ʱ��Ŀ�ʼ��Χ
	 * 
	 */
	public void setStartDate(Calendar startDate) {
		this.startDate = startDate;
	}

	/**
	 * �깺ʱ��Ŀ�ʼ��Χ
	 * 
	 */
	public Calendar getStartDate() {
		return this.startDate;
	}

	/**
	 * �깺ʱ��Ľ���Χ
	 * 
	 */
	public void setEndDate(Calendar endDate) {
		this.endDate = endDate;
	}

	/**
	 * �깺ʱ��Ľ���Χ
	 * 
	 */
	public Calendar getEndDate() {
		return this.endDate;
	}

	/**
	 */
	public AssetAppDate() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(AssetAppDate that) {
		setId(that.getId());
		setStartDate(that.getStartDate());
		setEndDate(that.getEndDate());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("id=[").append(id).append("] ");
		buffer.append("startDate=[").append(startDate).append("] ");
		buffer.append("endDate=[").append(endDate).append("] ");

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
		if (!(obj instanceof AssetAppDate))
			return false;
		AssetAppDate equalCheck = (AssetAppDate) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
