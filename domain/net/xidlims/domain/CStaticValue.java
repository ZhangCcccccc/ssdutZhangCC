package net.xidlims.domain;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "findAllCStaticValues", query = "select myCStaticValue from CStaticValue myCStaticValue"),
		@NamedQuery(name = "findCStaticValueByCode", query = "select myCStaticValue from CStaticValue myCStaticValue where myCStaticValue.code = ?1"),
		@NamedQuery(name = "findCStaticValueByCodeContaining", query = "select myCStaticValue from CStaticValue myCStaticValue where myCStaticValue.code like ?1"),
		@NamedQuery(name = "findCStaticValueById", query = "select myCStaticValue from CStaticValue myCStaticValue where myCStaticValue.id = ?1"),
		@NamedQuery(name = "findCStaticValueByPrimaryKey", query = "select myCStaticValue from CStaticValue myCStaticValue where myCStaticValue.id = ?1"),
		@NamedQuery(name = "findCStaticValueByStaticValue", query = "select myCStaticValue from CStaticValue myCStaticValue where myCStaticValue.staticValue = ?1"),
		@NamedQuery(name = "findCStaticValueByStaticValueContaining", query = "select myCStaticValue from CStaticValue myCStaticValue where myCStaticValue.staticValue like ?1"),
		@NamedQuery(name = "findCStaticValueByValueName", query = "select myCStaticValue from CStaticValue myCStaticValue where myCStaticValue.valueName = ?1"),
		@NamedQuery(name = "findCStaticValueByValueNameContaining", query = "select myCStaticValue from CStaticValue myCStaticValue where myCStaticValue.valueName like ?1") })
@Table(catalog = "xidlims", name = "c_static_value")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "CStaticValue")
public class CStaticValue implements Serializable {
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
	 * �ֵ�����
	 * 
	 */

	@Column(name = "code")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String code;
	/**
	 * �ֵ����
	 * 
	 */

	@Column(name = "value_name")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String valueName;
	/**
	 * �ֵ������
	 * 
	 */

	@Column(name = "static_value")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String staticValue;
	
	
	/**
	 */
	@Column(name = "academy_number")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String academyNumber;
	
	public String getAcademyNumber() {
		return academyNumber;
	}

	public void setAcademyNumber(String academyNumber) {
		this.academyNumber = academyNumber;
	}

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
	 * �ֵ�����
	 * 
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * �ֵ�����
	 * 
	 */
	public String getCode() {
		return this.code;
	}

	/**
	 * �ֵ����
	 * 
	 */
	public void setValueName(String valueName) {
		this.valueName = valueName;
	}

	/**
	 * �ֵ����
	 * 
	 */
	public String getValueName() {
		return this.valueName;
	}

	/**
	 * �ֵ������
	 * 
	 */
	public void setStaticValue(String staticValue) {
		this.staticValue = staticValue;
	}

	/**
	 * �ֵ������
	 * 
	 */
	public String getStaticValue() {
		return this.staticValue;
	}

	/**
	 */
	public CStaticValue() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(CStaticValue that) {
		setId(that.getId());
		setCode(that.getCode());
		setValueName(that.getValueName());
		setStaticValue(that.getStaticValue());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("id=[").append(id).append("] ");
		buffer.append("code=[").append(code).append("] ");
		buffer.append("valueName=[").append(valueName).append("] ");
		buffer.append("staticValue=[").append(staticValue).append("] ");

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
		if (!(obj instanceof CStaticValue))
			return false;
		CStaticValue equalCheck = (CStaticValue) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
