package net.xidlims.domain;

import java.io.Serializable;

import java.lang.StringBuilder;

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
		@NamedQuery(name = "findAllUserCards", query = "select myUserCard from UserCard myUserCard"),
		@NamedQuery(name = "findUserCardByCardNo", query = "select myUserCard from UserCard myUserCard where myUserCard.cardNo = ?1"),
		@NamedQuery(name = "findUserCardByCardNoContaining", query = "select myUserCard from UserCard myUserCard where myUserCard.cardNo like ?1"),
		@NamedQuery(name = "findUserCardByEnabled", query = "select myUserCard from UserCard myUserCard where myUserCard.enabled = ?1"),
		@NamedQuery(name = "findUserCardByEnabledContaining", query = "select myUserCard from UserCard myUserCard where myUserCard.enabled like ?1"),
		@NamedQuery(name = "findUserCardById", query = "select myUserCard from UserCard myUserCard where myUserCard.id = ?1"),
		@NamedQuery(name = "findUserCardByPrimaryKey", query = "select myUserCard from UserCard myUserCard where myUserCard.id = ?1") })
@Table(catalog = "xidlims", name = "user_card")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "UserCard")
public class UserCard implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 */

	@Column(name = "id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	Integer id;
	/**
	 * ����
	 * 
	 */

	@Column(name = "card_no")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String cardNo;
	/**
	 * �Ƿ���ã�1���ã�0�����ã�
	 * 
	 */

	@Column(name = "enabled", length = 50)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String enabled;

	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "username", referencedColumnName = "username") })
	@XmlTransient
	User user;

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
	 * ����
	 * 
	 */
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	/**
	 * ����
	 * 
	 */
	public String getCardNo() {
		return this.cardNo;
	}

	/**
	 * �Ƿ���ã�1���ã�0�����ã�
	 * 
	 */
	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	/**
	 * �Ƿ���ã�1���ã�0�����ã�
	 * 
	 */
	public String getEnabled() {
		return this.enabled;
	}

	/**
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 */
	@JsonIgnore
	public User getUser() {
		return user;
	}

	/**
	 */
	public UserCard() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(UserCard that) {
		setId(that.getId());
		setCardNo(that.getCardNo());
		setEnabled(that.getEnabled());
		setUser(that.getUser());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("id=[").append(id).append("] ");
		buffer.append("cardNo=[").append(cardNo).append("] ");
		buffer.append("enabled=[").append(enabled).append("] ");

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
		if (!(obj instanceof UserCard))
			return false;
		UserCard equalCheck = (UserCard) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
