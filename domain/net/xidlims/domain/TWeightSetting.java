package net.xidlims.domain;

import java.io.Serializable;

import java.lang.StringBuilder;

import java.math.BigDecimal;

import java.util.Calendar;


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
		@NamedQuery(name = "findAllTWeightSettings", query = "select myTWeightSetting from TWeightSetting myTWeightSetting"),
		@NamedQuery(name = "findTWeightSettingByCreateDate", query = "select myTWeightSetting from TWeightSetting myTWeightSetting where myTWeightSetting.createDate = ?1"),
		@NamedQuery(name = "findTWeightSettingById", query = "select myTWeightSetting from TWeightSetting myTWeightSetting where myTWeightSetting.id = ?1"),
		@NamedQuery(name = "findTWeightSettingByModifyDate", query = "select myTWeightSetting from TWeightSetting myTWeightSetting where myTWeightSetting.modifyDate = ?1"),
		@NamedQuery(name = "findTWeightSettingByPrimaryKey", query = "select myTWeightSetting from TWeightSetting myTWeightSetting where myTWeightSetting.id = ?1"),
		@NamedQuery(name = "findTWeightSettingByType", query = "select myTWeightSetting from TWeightSetting myTWeightSetting where myTWeightSetting.type = ?1"),
		@NamedQuery(name = "findTWeightSettingByTypeContaining", query = "select myTWeightSetting from TWeightSetting myTWeightSetting where myTWeightSetting.type like ?1"),
		@NamedQuery(name = "findTWeightSettingByWeight", query = "select myTWeightSetting from TWeightSetting myTWeightSetting where myTWeightSetting.weight = ?1") })
@Table(catalog = "xidlims", name = "t_weight_setting")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "TWeightSetting")
public class TWeightSetting implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * �γ̷���Ȩ������
	 * 
	 */

	@Column(name = "id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	@XmlElement
	Integer id;
	/**
	 * ����
	 * 
	 */

	@Column(name = "type")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String type;
	/**
	 * Ȩ��
	 * 
	 */

	@Column(name = "weight", scale = 2, precision = 10)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal weight;
	/**
	 * ��¼����ʱ��
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar createDate;
	/**
	 * ��¼�޸�ʱ��
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modify_date")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar modifyDate;

	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "site_id", referencedColumnName = "id") })
	@XmlTransient
	TCourseSite TCourseSite;

	/**
	 * �γ̷���Ȩ������
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * �γ̷���Ȩ������
	 * 
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * ����
	 * 
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * ����
	 * 
	 */
	public String getType() {
		return this.type;
	}

	/**
	 * Ȩ��
	 * 
	 */
	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}

	/**
	 * Ȩ��
	 * 
	 */
	public BigDecimal getWeight() {
		return this.weight;
	}

	/**
	 * ��¼����ʱ��
	 * 
	 */
	public void setCreateDate(Calendar createDate) {
		this.createDate = createDate;
	}

	/**
	 * ��¼����ʱ��
	 * 
	 */
	public Calendar getCreateDate() {
		return this.createDate;
	}

	/**
	 * ��¼�޸�ʱ��
	 * 
	 */
	public void setModifyDate(Calendar modifyDate) {
		this.modifyDate = modifyDate;
	}

	/**
	 * ��¼�޸�ʱ��
	 * 
	 */
	public Calendar getModifyDate() {
		return this.modifyDate;
	}

	/**
	 */
	public void setTCourseSite(TCourseSite TCourseSite) {
		this.TCourseSite = TCourseSite;
	}

	/**
	 */
	@JsonIgnore
	public TCourseSite getTCourseSite() {
		return TCourseSite;
	}

	/**
	 */
	public TWeightSetting() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(TWeightSetting that) {
		setId(that.getId());
		setType(that.getType());
		setWeight(that.getWeight());
		setCreateDate(that.getCreateDate());
		setModifyDate(that.getModifyDate());
		setTCourseSite(that.getTCourseSite());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("id=[").append(id).append("] ");
		buffer.append("type=[").append(type).append("] ");
		buffer.append("weight=[").append(weight).append("] ");
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
		if (!(obj instanceof TWeightSetting))
			return false;
		TWeightSetting equalCheck = (TWeightSetting) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
