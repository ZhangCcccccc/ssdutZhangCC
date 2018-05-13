package net.xidlims.domain;

import java.io.Serializable;

import java.lang.StringBuilder;

import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import org.codehaus.jackson.annotate.JsonIgnore;

import javax.xml.bind.annotation.*;

import javax.persistence.*;

/**
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "findAllLabRoomAgents", query = "select myLabRoomAgent from LabRoomAgent myLabRoomAgent"),
		@NamedQuery(name = "findLabRoomAgentByHardwareIp", query = "select myLabRoomAgent from LabRoomAgent myLabRoomAgent where myLabRoomAgent.hardwareIp = ?1"),
		@NamedQuery(name = "findLabRoomAgentByHardwareIpContaining", query = "select myLabRoomAgent from LabRoomAgent myLabRoomAgent where myLabRoomAgent.hardwareIp like ?1"),
		@NamedQuery(name = "findLabRoomAgentByHardwarePort", query = "select myLabRoomAgent from LabRoomAgent myLabRoomAgent where myLabRoomAgent.hardwarePort = ?1"),
		@NamedQuery(name = "findLabRoomAgentByHardwarePortContaining", query = "select myLabRoomAgent from LabRoomAgent myLabRoomAgent where myLabRoomAgent.hardwarePort like ?1"),
		@NamedQuery(name = "findLabRoomAgentByHardwareRemark", query = "select myLabRoomAgent from LabRoomAgent myLabRoomAgent where myLabRoomAgent.hardwareRemark = ?1"),
		@NamedQuery(name = "findLabRoomAgentByHardwareRemarkContaining", query = "select myLabRoomAgent from LabRoomAgent myLabRoomAgent where myLabRoomAgent.hardwareRemark like ?1"),
		@NamedQuery(name = "findLabRoomAgentById", query = "select myLabRoomAgent from LabRoomAgent myLabRoomAgent where myLabRoomAgent.id = ?1"),
		@NamedQuery(name = "findLabRoomAgentByPrimaryKey", query = "select myLabRoomAgent from LabRoomAgent myLabRoomAgent where myLabRoomAgent.id = ?1") })
@Table(catalog = "xidlims", name = "lab_room_agent")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "LabRoomAgent")
public class LabRoomAgent implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ʵ���ҷ��Ҵ����˱�
	 * 
	 */

	@Column(name = "id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	@GeneratedValue(strategy = GenerationType.AUTO)
	Integer id;
	/**
	 * ����Ӳ��IP
	 * 
	 */

	@Column(name = "hardware_ip")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String hardwareIp;
	/**
	 * ����Ӳ���˿�
	 * 
	 */

	@Column(name = "hardware_port")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String hardwarePort;
	/**
	 * PageCam
	 * 
	 */

	@Column(name = "hardware_remark")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String hardwareRemark;
	
	@Column(name = "hardware_version")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String hardwareVersion;

	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "hardware_type", referencedColumnName = "id") })
	@XmlTransient
	CDictionary CDictionary;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "lab_room_id", referencedColumnName = "id") })
	@XmlTransient
	LabRoom labRoom;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "server_id", referencedColumnName = "id") })
	@XmlTransient
	CommonServer commonServer;

	/**
	 */
	@OneToMany(mappedBy = "labRoomAgent", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.LabRoomDevice> labRoomDevices;
	/**
	 * ʵ���ҷ��Ҵ����˱�
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * ʵ���ҷ��Ҵ����˱�
	 * 
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * ����Ӳ��IP
	 * 
	 */
	public void setHardwareIp(String hardwareIp) {
		this.hardwareIp = hardwareIp;
	}

	/**
	 * ����Ӳ��IP
	 * 
	 */
	public String getHardwareIp() {
		return this.hardwareIp;
	}

	/**
	 * ����Ӳ���˿�
	 * 
	 */
	public void setHardwarePort(String hardwarePort) {
		this.hardwarePort = hardwarePort;
	}

	/**
	 * ����Ӳ���˿�
	 * 
	 */
	public String getHardwarePort() {
		return this.hardwarePort;
	}

	/**
	 * PageCam
	 * 
	 */
	public void setHardwareRemark(String hardwareRemark) {
		this.hardwareRemark = hardwareRemark;
	}
	
	public String getHardwareVersion() {
		return hardwareVersion;
	}

	public void setHardwareVersion(String hardwareVersion) {
		this.hardwareVersion = hardwareVersion;
	}

	/**
	 * PageCam
	 * 
	 */
	public String getHardwareRemark() {
		return this.hardwareRemark;
	}

	/**
	 */
	public void setCDictionary(CDictionary CDictionary) {
		this.CDictionary = CDictionary;
	}

	/**
	 */
	@JsonIgnore
	public CDictionary getCDictionary() {
		return CDictionary;
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
	public void setCommonServer(CommonServer commonServer) {
		this.commonServer = commonServer;
	}

	/**
	 */
	@JsonIgnore
	public CommonServer getCommonServer() {
		return commonServer;
	}

	/**
	 */
	public void setLabRoomDevice(Set<LabRoomDevice> labRoomDevices) {
		this.labRoomDevices = labRoomDevices;
	}

	/**
	 */
	@JsonIgnore
	public Set<LabRoomDevice> getLabRoomDevices() {
		if (labRoomDevices == null) {
			labRoomDevices = new java.util.LinkedHashSet<net.xidlims.domain.LabRoomDevice>();
		}
		return labRoomDevices;
	}
	/**
	 */
	public LabRoomAgent() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(LabRoomAgent that) {
		setId(that.getId());
		setHardwareIp(that.getHardwareIp());
		setHardwarePort(that.getHardwarePort());
		setHardwareRemark(that.getHardwareRemark());
		setCDictionary(that.getCDictionary());
		setLabRoom(that.getLabRoom());
		setCommonServer(that.getCommonServer());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("id=[").append(id).append("] ");
		buffer.append("hardwareIp=[").append(hardwareIp).append("] ");
		buffer.append("hardwarePort=[").append(hardwarePort).append("] ");
		buffer.append("hardwareRemark=[").append(hardwareRemark).append("] ");

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
		if (!(obj instanceof LabRoomAgent))
			return false;
		LabRoomAgent equalCheck = (LabRoomAgent) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
