package net.xidlims.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import org.codehaus.jackson.annotate.JsonIgnore;

/**
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "findAllSchoolDeviceUses", query = "select mySchoolDeviceUse from SchoolDeviceUse mySchoolDeviceUse"),
		@NamedQuery(name = "findSchoolDeviceUseById", query = "select mySchoolDeviceUse from SchoolDeviceUse mySchoolDeviceUse where mySchoolDeviceUse.id = ?1"),
		@NamedQuery(name = "findSchoolDeviceUseByPrice", query = "select mySchoolDeviceUse from SchoolDeviceUse mySchoolDeviceUse where mySchoolDeviceUse.price = ?1"),
		@NamedQuery(name = "findSchoolDeviceUseByPrimaryKey", query = "select mySchoolDeviceUse from SchoolDeviceUse mySchoolDeviceUse where mySchoolDeviceUse.id = ?1"),
		@NamedQuery(name = "findSchoolDeviceUseByTerm", query = "select mySchoolDeviceUse from SchoolDeviceUse mySchoolDeviceUse where mySchoolDeviceUse.term = ?1"),
		@NamedQuery(name = "findSchoolDeviceUseByUseCount", query = "select mySchoolDeviceUse from SchoolDeviceUse mySchoolDeviceUse where mySchoolDeviceUse.useCount = ?1"),
		@NamedQuery(name = "findSchoolDeviceUseByUseHours", query = "select mySchoolDeviceUse from SchoolDeviceUse mySchoolDeviceUse where mySchoolDeviceUse.useHours = ?1") })
@Table(catalog = "xidlims", name = "school_device_use")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "SchoolDeviceUse")
public class SchoolDeviceUse implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键自增
	 * 
	 */

	@Column(name = "id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	@XmlElement
	Integer id;
	/**
	 * 所属学期（非外键）
	 * 
	 */

	@Column(name = "term")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer term;
	/**
	 * 使用时长（按学期）
	 * 
	 */

	@Column(name = "use_hours", scale = 2, precision = 11)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal useHours;
	/**
	 * 使用次数（按学期）
	 * 
	 */

	@Column(name = "use_count", scale = 2, precision = 11)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer useCount;
	/**
	 * 收取的费用（按学期）
	 * 
	 */

	@Column(name = "price", scale = 2, precision = 11)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal price;

	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "device_number", referencedColumnName = "device_number", nullable = false) })
	@XmlTransient
	SchoolDevice schoolDevice;

	/**
	 * 主键自增
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 主键自增
	 * 
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * 所属学期（非外键）
	 * 
	 */
	public void setTerm(Integer term) {
		this.term = term;
	}

	/**
	 * 所属学期（非外键）
	 * 
	 */
	public Integer getTerm() {
		return this.term;
	}

	/**
	 * 使用时长（按学期）
	 * 
	 */
	public void setUseHours(BigDecimal useHours) {
		this.useHours = useHours;
	}

	/**
	 * 使用时长（按学期）
	 * 
	 */
	public BigDecimal getUseHours() {
		return this.useHours;
	}

	/**
	 * 使用次数（按学期）
	 * 
	 */
	public void setUseCount(Integer useCount) {
		this.useCount = useCount;
	}

	/**
	 * 使用次数（按学期）
	 * 
	 */
	public Integer getUseCount() {
		return this.useCount;
	}

	/**
	 * 收取的费用（按学期）
	 * 
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	/**
	 * 收取的费用（按学期）
	 * 
	 */
	public BigDecimal getPrice() {
		return this.price;
	}

	/**
	 */
	public void setSchoolDevice(SchoolDevice schoolDevice) {
		this.schoolDevice = schoolDevice;
	}

	/**
	 */
	@JsonIgnore
	public SchoolDevice getSchoolDevice() {
		return schoolDevice;
	}

	/**
	 */
	public SchoolDeviceUse() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(SchoolDeviceUse that) {
		setId(that.getId());
		setTerm(that.getTerm());
		setUseHours(that.getUseHours());
		setUseCount(that.getUseCount());
		setPrice(that.getPrice());
		setSchoolDevice(that.getSchoolDevice());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("id=[").append(id).append("] ");
		buffer.append("term=[").append(term).append("] ");
		buffer.append("useHours=[").append(useHours).append("] ");
		buffer.append("useCount=[").append(useCount).append("] ");
		buffer.append("price=[").append(price).append("] ");

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
		if (!(obj instanceof SchoolDeviceUse))
			return false;
		SchoolDeviceUse equalCheck = (SchoolDeviceUse) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
