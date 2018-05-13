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
		@NamedQuery(name = "findAllMenus", query = "select myMenu from Menu myMenu"),
		@NamedQuery(name = "findMenuById", query = "select myMenu from Menu myMenu where myMenu.id = ?1"),
		@NamedQuery(name = "findMenuByMemo", query = "select myMenu from Menu myMenu where myMenu.memo = ?1"),
		@NamedQuery(name = "findMenuByMemoContaining", query = "select myMenu from Menu myMenu where myMenu.memo like ?1"),
		@NamedQuery(name = "findMenuByName", query = "select myMenu from Menu myMenu where myMenu.name = ?1"),
		@NamedQuery(name = "findMenuByNameContaining", query = "select myMenu from Menu myMenu where myMenu.name like ?1"),
		@NamedQuery(name = "findMenuByPrimaryKey", query = "select myMenu from Menu myMenu where myMenu.id = ?1") })
@Table(catalog = "xidlims", name = "menu")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "Menu")
@XmlRootElement(namespace = "xidlims/net/xidlims/domain")
public class Menu implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ���˵���Ŀ��
	 * 
	 */

	@Column(name = "id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	Integer id;
	/**
	 * ���
	 * 
	 */

	@Column(name = "name")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String name;
	/**
	 * ����
	 * 
	 */

	@Column(name = "memo")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String memo;

	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "parent_id", referencedColumnName = "id") })
	@XmlTransient
	Menu menu;
	/**
	 */
	@OneToMany(mappedBy = "menu", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.AuthorityMenu> authorityMenus;
	/**
	 */
	@OneToMany(mappedBy = "menu", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	@OrderBy("id")//对set集合进行排序,根据id进行排序
	java.util.Set<net.xidlims.domain.Menu> menus;

	/**
	 * ���˵���Ŀ��
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * ���˵���Ŀ��
	 * 
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * ���
	 * 
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * ���
	 * 
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * ����
	 * 
	 */
	public void setMemo(String memo) {
		this.memo = memo;
	}

	/**
	 * ����
	 * 
	 */
	public String getMemo() {
		return this.memo;
	}

	/**
	 */
	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	/**
	 */
	@JsonIgnore
	public Menu getMenu() {
		return menu;
	}

	/**
	 */
	public void setAuthorityMenus(Set<AuthorityMenu> authorityMenus) {
		this.authorityMenus = authorityMenus;
	}

	/**
	 */
	@JsonIgnore
	public Set<AuthorityMenu> getAuthorityMenus() {
		if (authorityMenus == null) {
			authorityMenus = new java.util.LinkedHashSet<net.xidlims.domain.AuthorityMenu>();
		}
		return authorityMenus;
	}

	/**
	 */
	public void setMenus(Set<Menu> menus) {
		this.menus = menus;
	}

	/**
	 */
	@JsonIgnore
	public Set<Menu> getMenus() {
		if (menus == null) {
			menus = new java.util.LinkedHashSet<net.xidlims.domain.Menu>();
		}
		return menus;
	}

	/**
	 */
	public Menu() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(Menu that) {
		setId(that.getId());
		setName(that.getName());
		setMemo(that.getMemo());
		setMenu(that.getMenu());
		setAuthorityMenus(new java.util.LinkedHashSet<net.xidlims.domain.AuthorityMenu>(that.getAuthorityMenus()));
		setMenus(new java.util.LinkedHashSet<net.xidlims.domain.Menu>(that.getMenus()));
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("id=[").append(id).append("] ");
		buffer.append("name=[").append(name).append("] ");
		buffer.append("memo=[").append(memo).append("] ");

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
		if (!(obj instanceof Menu))
			return false;
		Menu equalCheck = (Menu) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
