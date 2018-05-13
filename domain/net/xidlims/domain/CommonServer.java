package net.xidlims.domain;

import java.io.Serializable;

import java.lang.StringBuilder;

import java.util.LinkedHashSet;
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
		@NamedQuery(name = "findAllCommonServers", query = "select myCommonServer from CommonServer myCommonServer"),
		@NamedQuery(name = "findCommonServerByAcademyEnName", query = "select myCommonServer from CommonServer myCommonServer where myCommonServer.academyEnName = ?1"),
		@NamedQuery(name = "findCommonServerByAcademyEnNameContaining", query = "select myCommonServer from CommonServer myCommonServer where myCommonServer.academyEnName like ?1"),
		@NamedQuery(name = "findCommonServerByAcademyName", query = "select myCommonServer from CommonServer myCommonServer where myCommonServer.academyName = ?1"),
		@NamedQuery(name = "findCommonServerByAcademyNameContaining", query = "select myCommonServer from CommonServer myCommonServer where myCommonServer.academyName like ?1"),
		@NamedQuery(name = "findCommonServerByAcademyNumber", query = "select myCommonServer from CommonServer myCommonServer where myCommonServer.academyNumber = ?1"),
		@NamedQuery(name = "findCommonServerByAcademyNumberContaining", query = "select myCommonServer from CommonServer myCommonServer where myCommonServer.academyNumber like ?1"),
		@NamedQuery(name = "findCommonServerById", query = "select myCommonServer from CommonServer myCommonServer where myCommonServer.id = ?1"),
		@NamedQuery(name = "findCommonServerByPrimaryKey", query = "select myCommonServer from CommonServer myCommonServer where myCommonServer.id = ?1"),
		@NamedQuery(name = "findCommonServerByReamark", query = "select myCommonServer from CommonServer myCommonServer where myCommonServer.reamark = ?1"),
		@NamedQuery(name = "findCommonServerByReamarkContaining", query = "select myCommonServer from CommonServer myCommonServer where myCommonServer.reamark like ?1"),
		@NamedQuery(name = "findCommonServerByServerAddress", query = "select myCommonServer from CommonServer myCommonServer where myCommonServer.serverAddress = ?1"),
		@NamedQuery(name = "findCommonServerByServerAddressContaining", query = "select myCommonServer from CommonServer myCommonServer where myCommonServer.serverAddress like ?1"),
		@NamedQuery(name = "findCommonServerByServerIp", query = "select myCommonServer from CommonServer myCommonServer where myCommonServer.serverIp = ?1"),
		@NamedQuery(name = "findCommonServerByServerIpContaining", query = "select myCommonServer from CommonServer myCommonServer where myCommonServer.serverIp like ?1"),
		@NamedQuery(name = "findCommonServerByServerName", query = "select myCommonServer from CommonServer myCommonServer where myCommonServer.serverName = ?1"),
		@NamedQuery(name = "findCommonServerByServerNameContaining", query = "select myCommonServer from CommonServer myCommonServer where myCommonServer.serverName like ?1"),
		@NamedQuery(name = "findCommonServerByServerSn", query = "select myCommonServer from CommonServer myCommonServer where myCommonServer.serverSn = ?1"),
		@NamedQuery(name = "findCommonServerByServerSnContaining", query = "select myCommonServer from CommonServer myCommonServer where myCommonServer.serverSn like ?1") })
@Table(catalog = "xidlims", name = "common_server")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "CommonServer")
@XmlRootElement(namespace = "xidlims/net/xidlims/domain")
public class CommonServer implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ʵ����������������
	 * 
	 */

	@Column(name = "id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@XmlElement
	Integer id;
	/**
	 * ���������
	 * 
	 */

	@Column(name = "server_name")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String serverName;
	/**
	 * ��������IP
	 * 
	 */

	@Column(name = "server_ip")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String serverIp;
	/**
	 * �������Ķ˿�
	 * 
	 */

	@Column(name = "server_sn")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String serverSn;
	/**
	 * ��������ַ
	 * 
	 */

	@Column(name = "server_address")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String serverAddress;
	/**
	 * ѧԺ
	 * 
	 */

	@Column(name = "academy_name")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String academyName;
	/**
	 * ѧԺ���
	 * 
	 */

	@Column(name = "academy_number", length = 40)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String academyNumber;
	/**
	 * ѧԺ��ƣ�Ӣ����ģ�
	 * 
	 */

	@Column(name = "academy_en_name")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String academyEnName;
	/**
	 * �����ֶ�
	 * 
	 */

	@Column(name = "reamark")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String reamark;

	/**
	 */
	@OneToMany(mappedBy = "commonServer", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.LabRoomAgent> labRoomAgents;

	/**
	 * ʵ����������������
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * ʵ����������������
	 * 
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * ���������
	 * 
	 */
	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	/**
	 * ���������
	 * 
	 */
	public String getServerName() {
		return this.serverName;
	}

	/**
	 * ��������IP
	 * 
	 */
	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
	}

	/**
	 * ��������IP
	 * 
	 */
	public String getServerIp() {
		return this.serverIp;
	}

	/**
	 * �������Ķ˿�
	 * 
	 */
	public void setServerSn(String serverSn) {
		this.serverSn = serverSn;
	}

	/**
	 * �������Ķ˿�
	 * 
	 */
	public String getServerSn() {
		return this.serverSn;
	}

	/**
	 * ��������ַ
	 * 
	 */
	public void setServerAddress(String serverAddress) {
		this.serverAddress = serverAddress;
	}

	/**
	 * ��������ַ
	 * 
	 */
	public String getServerAddress() {
		return this.serverAddress;
	}

	/**
	 * ѧԺ
	 * 
	 */
	public void setAcademyName(String academyName) {
		this.academyName = academyName;
	}

	/**
	 * ѧԺ
	 * 
	 */
	public String getAcademyName() {
		return this.academyName;
	}

	/**
	 * ѧԺ���
	 * 
	 */
	public void setAcademyNumber(String academyNumber) {
		this.academyNumber = academyNumber;
	}

	/**
	 * ѧԺ���
	 * 
	 */
	public String getAcademyNumber() {
		return this.academyNumber;
	}

	/**
	 * ѧԺ��ƣ�Ӣ����ģ�
	 * 
	 */
	public void setAcademyEnName(String academyEnName) {
		this.academyEnName = academyEnName;
	}

	/**
	 * ѧԺ��ƣ�Ӣ����ģ�
	 * 
	 */
	public String getAcademyEnName() {
		return this.academyEnName;
	}

	/**
	 * �����ֶ�
	 * 
	 */
	public void setReamark(String reamark) {
		this.reamark = reamark;
	}

	/**
	 * �����ֶ�
	 * 
	 */
	public String getReamark() {
		return this.reamark;
	}

	/**
	 */
	public void setLabRoomAgents(Set<LabRoomAgent> labRoomAgents) {
		this.labRoomAgents = labRoomAgents;
	}

	/**
	 */
	@JsonIgnore
	public Set<LabRoomAgent> getLabRoomAgents() {
		if (labRoomAgents == null) {
			labRoomAgents = new java.util.LinkedHashSet<net.xidlims.domain.LabRoomAgent>();
		}
		return labRoomAgents;
	}

	/**
	 */
	public CommonServer() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(CommonServer that) {
		setId(that.getId());
		setServerName(that.getServerName());
		setServerIp(that.getServerIp());
		setServerSn(that.getServerSn());
		setServerAddress(that.getServerAddress());
		setAcademyName(that.getAcademyName());
		setAcademyNumber(that.getAcademyNumber());
		setAcademyEnName(that.getAcademyEnName());
		setReamark(that.getReamark());
		setLabRoomAgents(new java.util.LinkedHashSet<net.xidlims.domain.LabRoomAgent>(that.getLabRoomAgents()));
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("id=[").append(id).append("] ");
		buffer.append("serverName=[").append(serverName).append("] ");
		buffer.append("serverIp=[").append(serverIp).append("] ");
		buffer.append("serverSn=[").append(serverSn).append("] ");
		buffer.append("serverAddress=[").append(serverAddress).append("] ");
		buffer.append("academyName=[").append(academyName).append("] ");
		buffer.append("academyNumber=[").append(academyNumber).append("] ");
		buffer.append("academyEnName=[").append(academyEnName).append("] ");
		buffer.append("reamark=[").append(reamark).append("] ");

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
		if (!(obj instanceof CommonServer))
			return false;
		CommonServer equalCheck = (CommonServer) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
