package net.xidlims.domain;

import java.io.Serializable;

import java.lang.StringBuilder;

import java.util.Set;

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
		@NamedQuery(name = "findAllLabRoomFurnitures", query = "select myLabRoomFurniture from LabRoomFurniture myLabRoomFurniture"),
		@NamedQuery(name = "findLabRoomFurnitureByFurnitureName", query = "select myLabRoomFurniture from LabRoomFurniture myLabRoomFurniture where myLabRoomFurniture.furnitureName = ?1"),
		@NamedQuery(name = "findLabRoomFurnitureByFurnitureNameContaining", query = "select myLabRoomFurniture from LabRoomFurniture myLabRoomFurniture where myLabRoomFurniture.furnitureName like ?1"),
		@NamedQuery(name = "findLabRoomFurnitureByFurnitureNo", query = "select myLabRoomFurniture from LabRoomFurniture myLabRoomFurniture where myLabRoomFurniture.furnitureNo = ?1"),
		@NamedQuery(name = "findLabRoomFurnitureByFurnitureNoContaining", query = "select myLabRoomFurniture from LabRoomFurniture myLabRoomFurniture where myLabRoomFurniture.furnitureNo like ?1"),
		@NamedQuery(name = "findLabRoomFurnitureById", query = "select myLabRoomFurniture from LabRoomFurniture myLabRoomFurniture where myLabRoomFurniture.id = ?1"),
		@NamedQuery(name = "findLabRoomFurnitureByPrimaryKey", query = "select myLabRoomFurniture from LabRoomFurniture myLabRoomFurniture where myLabRoomFurniture.id = ?1") })
@Table(catalog = "xidlims", name = "lab_room_furniture")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "LabRoomFurniture")
public class LabRoomFurniture implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ʵ���ҷ��ҼҾ߱�
	 * 
	 */

	@Column(name = "id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@XmlElement
	Integer id;
	/**
	 * �Ҿ����
	 * 
	 */

	@Column(name = "furniture_name")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String furnitureName;
	/**
	 * �Ҿ߱��
	 * 
	 */

	@Column(name = "furniture_no")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String furnitureNo;

	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "lab_room_id", referencedColumnName = "id") })
	@XmlTransient
	LabRoom labRoom;

	/**
	 * ʵ���ҷ��ҼҾ߱�
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * ʵ���ҷ��ҼҾ߱�
	 * 
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * �Ҿ����
	 * 
	 */
	public void setFurnitureName(String furnitureName) {
		this.furnitureName = furnitureName;
	}

	/**
	 * �Ҿ����
	 * 
	 */
	public String getFurnitureName() {
		return this.furnitureName;
	}

	/**
	 * �Ҿ߱��
	 * 
	 */
	public void setFurnitureNo(String furnitureNo) {
		this.furnitureNo = furnitureNo;
	}

	/**
	 * �Ҿ߱��
	 * 
	 */
	public String getFurnitureNo() {
		return this.furnitureNo;
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

	/**
	 */
	public LabRoomFurniture() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(LabRoomFurniture that) {
		setId(that.getId());
		setFurnitureName(that.getFurnitureName());
		setFurnitureNo(that.getFurnitureNo());
		setLabRoom(that.getLabRoom());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("id=[").append(id).append("] ");
		buffer.append("furnitureName=[").append(furnitureName).append("] ");
		buffer.append("furnitureNo=[").append(furnitureNo).append("] ");

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
		if (!(obj instanceof LabRoomFurniture))
			return false;
		LabRoomFurniture equalCheck = (LabRoomFurniture) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
