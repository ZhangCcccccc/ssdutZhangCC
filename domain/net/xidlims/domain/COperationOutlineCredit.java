package net.xidlims.domain;

import java.io.Serializable;

import java.lang.StringBuilder;

import java.util.LinkedHashSet;
import java.util.Set;

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
		@NamedQuery(name = "findAllCOperationOutlineCredits", query = "select myCOperationOutlineCredit from COperationOutlineCredit myCOperationOutlineCredit"),
		@NamedQuery(name = "findCOperationOutlineCreditByCredit", query = "select myCOperationOutlineCredit from COperationOutlineCredit myCOperationOutlineCredit where myCOperationOutlineCredit.credit = ?1"),
		@NamedQuery(name = "findCOperationOutlineCreditById", query = "select myCOperationOutlineCredit from COperationOutlineCredit myCOperationOutlineCredit where myCOperationOutlineCredit.id = ?1"),
		@NamedQuery(name = "findCOperationOutlineCreditByPrimaryKey", query = "select myCOperationOutlineCredit from COperationOutlineCredit myCOperationOutlineCredit where myCOperationOutlineCredit.id = ?1") })
@Table(catalog = "xidlims", name = "c_operation_outline_credit")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "COperationOutlineCredit")
@XmlRootElement(namespace = "xidlims/net/xidlims/domain")
public class COperationOutlineCredit implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ʵ����ѧ���ֵ��
	 * 
	 */

	@Column(name = "id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@XmlElement
	Integer id;
	/**
	 * ѧ��
	 * 
	 */

	@Column(name = "credit")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String credit;

	/**
	 */
	@OneToMany(mappedBy = "COperationOutlineCredit", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.OperationOutline> operationOutlines;

	/**
	 * ʵ����ѧ���ֵ��
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * ʵ����ѧ���ֵ��
	 * 
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * ѧ��
	 * 
	 */
	public void setCredit(String credit) {
		this.credit = credit;
	}

	/**
	 * ѧ��
	 * 
	 */
	public String getCredit() {
		return this.credit;
	}

	/**
	 */
	public void setOperationOutlines(Set<OperationOutline> operationOutlines) {
		this.operationOutlines = operationOutlines;
	}

	/**
	 */
	@JsonIgnore
	public Set<OperationOutline> getOperationOutlines() {
		if (operationOutlines == null) {
			operationOutlines = new java.util.LinkedHashSet<net.xidlims.domain.OperationOutline>();
		}
		return operationOutlines;
	}

	/**
	 */
	public COperationOutlineCredit() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(COperationOutlineCredit that) {
		setId(that.getId());
		setCredit(that.getCredit());
		setOperationOutlines(new java.util.LinkedHashSet<net.xidlims.domain.OperationOutline>(that.getOperationOutlines()));
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("id=[").append(id).append("] ");
		buffer.append("credit=[").append(credit).append("] ");

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
		if (!(obj instanceof COperationOutlineCredit))
			return false;
		COperationOutlineCredit equalCheck = (COperationOutlineCredit) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
