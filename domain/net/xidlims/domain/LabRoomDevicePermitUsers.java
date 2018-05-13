package net.xidlims.domain;

import java.io.Serializable;

import java.lang.StringBuilder;

import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.FetchType;
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
		@NamedQuery(name = "findAllLabRoomDevicePermitUserss", query = "select myLabRoomDevicePermitUsers from LabRoomDevicePermitUsers myLabRoomDevicePermitUsers"),
		@NamedQuery(name = "findLabRoomDevicePermitUsersById", query = "select myLabRoomDevicePermitUsers from LabRoomDevicePermitUsers myLabRoomDevicePermitUsers where myLabRoomDevicePermitUsers.id = ?1"),
		@NamedQuery(name = "findLabRoomDevicePermitUsersByPrimaryKey", query = "select myLabRoomDevicePermitUsers from LabRoomDevicePermitUsers myLabRoomDevicePermitUsers where myLabRoomDevicePermitUsers.id = ?1") })
@Table(catalog = "xidlims", name = "lab_room_device_permit_users")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "test/net/xidlims/domain", name = "LabRoomDevicePermitUsers")
public class LabRoomDevicePermitUsers implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 */

	@Column(name = "id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@XmlElement
	Integer id;
	
	@Column(name = "flag")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer flag;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "student", referencedColumnName = "username") })
	@XmlTransient
	User user;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "lab_room_device", referencedColumnName = "id") })
	@XmlTransient
	LabRoomDevice labRoomDevice;

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
	
	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	
	/**
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 */
	public User getUser() {
		return user;
	}

	/**
	 */
	public void setLabRoomDevice(LabRoomDevice labRoomDevice) {
		this.labRoomDevice = labRoomDevice;
	}

	/**
	 */
	public LabRoomDevice getLabRoomDevice() {
		return labRoomDevice;
	}

	/**
	 */
	public LabRoomDevicePermitUsers() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(LabRoomDevicePermitUsers that) {
		setId(that.getId());
		setUser(that.getUser());
		setLabRoomDevice(that.getLabRoomDevice());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("id=[").append(id).append("] ");

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
		if (!(obj instanceof LabRoomDevicePermitUsers))
			return false;
		LabRoomDevicePermitUsers equalCheck = (LabRoomDevicePermitUsers) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
