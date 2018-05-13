package net.xidlims.domain;

import java.io.Serializable;

import java.lang.StringBuilder;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.FetchType;
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
		@NamedQuery(name = "findAllAssetCabinets", query = "select myAssetCabinet from AssetCabinet myAssetCabinet"),
		@NamedQuery(name = "findAssetCabinetByCabinetCode", query = "select myAssetCabinet from AssetCabinet myAssetCabinet where myAssetCabinet.cabinetCode = ?1"),
		@NamedQuery(name = "findAssetCabinetByCabinetCodeContaining", query = "select myAssetCabinet from AssetCabinet myAssetCabinet where myAssetCabinet.cabinetCode like ?1"),
		@NamedQuery(name = "findAssetCabinetByCabinetName", query = "select myAssetCabinet from AssetCabinet myAssetCabinet where myAssetCabinet.cabinetName = ?1"),
		@NamedQuery(name = "findAssetCabinetByCabinetNameContaining", query = "select myAssetCabinet from AssetCabinet myAssetCabinet where myAssetCabinet.cabinetName like ?1"),
		@NamedQuery(name = "findAssetCabinetById", query = "select myAssetCabinet from AssetCabinet myAssetCabinet where myAssetCabinet.id = ?1"),
		@NamedQuery(name = "findAssetCabinetByPrimaryKey", query = "select myAssetCabinet from AssetCabinet myAssetCabinet where myAssetCabinet.id = ?1") })
@Table(catalog = "xidlims", name = "asset_cabinet")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "AssetCabinet")
@XmlRootElement(namespace = "xidlims/net/xidlims/domain")
public class AssetCabinet implements Serializable {
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
	 * ��Ʒ����
	 * 
	 */

	@Column(name = "cabinet_code")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String cabinetCode;
	/**
	 */

	@Column(name = "cabinet_name")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String cabinetName;
	
	@Column(name = "flag")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer flag;

	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "lab_id", referencedColumnName = "id") })
	@XmlTransient
	LabRoom labRoom;

	@OneToMany(mappedBy = "assetCabinet", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.AssetCabinetWarehouse> assetCabinetWarehouses;

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
	 * ��Ʒ����
	 * 
	 */
	public void setCabinetCode(String cabinetCode) {
		this.cabinetCode = cabinetCode;
	}

	/**
	 * ��Ʒ����
	 * 
	 */
	public String getCabinetCode() {
		return this.cabinetCode;
	}

	/**
	 */
	public void setCabinetName(String cabinetName) {
		this.cabinetName = cabinetName;
	}

	/**
	 */
	public String getCabinetName() {
		return this.cabinetName;
	}

	/**
	 */
	public void setLabRoom(LabRoom labRoom) {
		this.labRoom = labRoom;
	}

	/**
	 */
	@JsonIgnore
	public LabRoom getLabRoom() {
		return labRoom;
	}

	
	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	/**
	 */
	public void setAssetCabinetWarehouses(Set<AssetCabinetWarehouse> assetCabinetWarehouses) {
		this.assetCabinetWarehouses = assetCabinetWarehouses;
	}

	/**
	 */
	@JsonIgnore
	public Set<AssetCabinetWarehouse> getAssetCabinetWarehouses() {
		if (assetCabinetWarehouses == null) {
			assetCabinetWarehouses = new java.util.LinkedHashSet<net.xidlims.domain.AssetCabinetWarehouse>();
		}
		return assetCabinetWarehouses;
	}

	/**
	 */
	public AssetCabinet() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(AssetCabinet that) {
		setId(that.getId());
		setCabinetCode(that.getCabinetCode());
		setCabinetName(that.getCabinetName());
		setLabRoom(that.getLabRoom());
		setAssetCabinetWarehouses(new java.util.LinkedHashSet<net.xidlims.domain.AssetCabinetWarehouse>(that.getAssetCabinetWarehouses()));
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("id=[").append(id).append("] ");
		buffer.append("cabinetCode=[").append(cabinetCode).append("] ");
		buffer.append("cabinetName=[").append(cabinetName).append("] ");

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
		if (!(obj instanceof AssetCabinet))
			return false;
		AssetCabinet equalCheck = (AssetCabinet) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
