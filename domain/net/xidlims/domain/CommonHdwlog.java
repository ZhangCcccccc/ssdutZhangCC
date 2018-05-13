package net.xidlims.domain;

import java.io.Serializable;

import java.lang.StringBuilder;

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
		@NamedQuery(name = "findAllCommonHdwlogs", query = "select myCommonHdwlog from CommonHdwlog myCommonHdwlog"),
		@NamedQuery(name = "findCommonHdwlogByCardname", query = "select myCommonHdwlog from CommonHdwlog myCommonHdwlog where myCommonHdwlog.cardname = ?1"),
		@NamedQuery(name = "findCommonHdwlogByCardnameContaining", query = "select myCommonHdwlog from CommonHdwlog myCommonHdwlog where myCommonHdwlog.cardname like ?1"),
		@NamedQuery(name = "findCommonHdwlogByCardnumber", query = "select myCommonHdwlog from CommonHdwlog myCommonHdwlog where myCommonHdwlog.cardnumber = ?1"),
		@NamedQuery(name = "findCommonHdwlogByCardnumberContaining", query = "select myCommonHdwlog from CommonHdwlog myCommonHdwlog where myCommonHdwlog.cardnumber like ?1"),
		@NamedQuery(name = "findCommonHdwlogByDatetime", query = "select myCommonHdwlog from CommonHdwlog myCommonHdwlog where myCommonHdwlog.datetime = ?1"),
		@NamedQuery(name = "findCommonHdwlogByDatetimeContaining", query = "select myCommonHdwlog from CommonHdwlog myCommonHdwlog where myCommonHdwlog.datetime like ?1"),
		@NamedQuery(name = "findCommonHdwlogByDeviceno", query = "select myCommonHdwlog from CommonHdwlog myCommonHdwlog where myCommonHdwlog.deviceno = ?1"),
		@NamedQuery(name = "findCommonHdwlogByDevicenoContaining", query = "select myCommonHdwlog from CommonHdwlog myCommonHdwlog where myCommonHdwlog.deviceno like ?1"),
		@NamedQuery(name = "findCommonHdwlogByHardwareid", query = "select myCommonHdwlog from CommonHdwlog myCommonHdwlog where myCommonHdwlog.hardwareid = ?1"),
		@NamedQuery(name = "findCommonHdwlogByHardwareidContaining", query = "select myCommonHdwlog from CommonHdwlog myCommonHdwlog where myCommonHdwlog.hardwareid like ?1"),
		@NamedQuery(name = "findCommonHdwlogByHdwCheck", query = "select myCommonHdwlog from CommonHdwlog myCommonHdwlog where myCommonHdwlog.hdwCheck = ?1"),
		@NamedQuery(name = "findCommonHdwlogByHdwCheckContaining", query = "select myCommonHdwlog from CommonHdwlog myCommonHdwlog where myCommonHdwlog.hdwCheck like ?1"),
		@NamedQuery(name = "findCommonHdwlogById", query = "select myCommonHdwlog from CommonHdwlog myCommonHdwlog where myCommonHdwlog.id = ?1"),
		@NamedQuery(name = "findCommonHdwlogByPrimaryKey", query = "select myCommonHdwlog from CommonHdwlog myCommonHdwlog where myCommonHdwlog.id = ?1"),
		@NamedQuery(name = "findCommonHdwlogByRemark", query = "select myCommonHdwlog from CommonHdwlog myCommonHdwlog where myCommonHdwlog.remark = ?1"),
		@NamedQuery(name = "findCommonHdwlogByRemarkContaining", query = "select myCommonHdwlog from CommonHdwlog myCommonHdwlog where myCommonHdwlog.remark like ?1"),
		@NamedQuery(name = "findCommonHdwlogByStatus", query = "select myCommonHdwlog from CommonHdwlog myCommonHdwlog where myCommonHdwlog.status = ?1"),
		@NamedQuery(name = "findCommonHdwlogByStatusContaining", query = "select myCommonHdwlog from CommonHdwlog myCommonHdwlog where myCommonHdwlog.status like ?1"),
		@NamedQuery(name = "findCommonHdwlogByUpdatedat", query = "select myCommonHdwlog from CommonHdwlog myCommonHdwlog where myCommonHdwlog.updatedat = ?1"),
		@NamedQuery(name = "findCommonHdwlogByUpdatedatContaining", query = "select myCommonHdwlog from CommonHdwlog myCommonHdwlog where myCommonHdwlog.updatedat like ?1") })
@Table(catalog = "xidlims", name = "common_hdwlog")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "CommonHdwlog")
public class CommonHdwlog implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * �����豸��־��ű�
	 * 
	 */

	@Column(name = "id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	@XmlElement
	Integer id;
	/**
	 */

	@Column(name = "cardnumber", length = 40)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String cardnumber;
	/**
	 */

	@Column(name = "deviceno", length = 10)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String deviceno;
	/**
	 */

	@Column(name = "datetime", length = 50)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String datetime;
	/**
	 */

	@Column(name = "status", length = 50)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String status;
	/**
	 */

	@Column(name = "hardwareid", length = 50)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String hardwareid;
	/**
	 */

	@Column(name = "cardname", length = 20)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String cardname;
	/**
	 */

	@Column(name = "remark", length = 100)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String remark;
	/**
	 */

	@Column(name = "hdw_check", length = 10)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String hdwCheck;
	/**
	 */

	@Column(name = "updatedat", length = 20)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String updatedat;

	@Column(name = "username", length = 20)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String username;
	
	@Column(name = "academy_number", length = 20)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String academyNumber;
	
	@Column(name = "doorindex", length = 20)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer doorindex;
	/**
	 * �����豸��־��ű�
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * �����豸��־��ű�
	 * 
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 */
	public void setCardnumber(String cardnumber) {
		this.cardnumber = cardnumber;
	}

	/**
	 */
	public String getCardnumber() {
		return this.cardnumber;
	}

	/**
	 */
	public void setDeviceno(String deviceno) {
		this.deviceno = deviceno;
	}

	/**
	 */
	public String getDeviceno() {
		return this.deviceno;
	}

	/**
	 */
	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	/**
	 */
	public String getDatetime() {
		return this.datetime;
	}

	/**
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 */
	public String getStatus() {
		return this.status;
	}

	/**
	 */
	public void setHardwareid(String hardwareid) {
		this.hardwareid = hardwareid;
	}

	/**
	 */
	public String getHardwareid() {
		return this.hardwareid;
	}

	/**
	 */
	public void setCardname(String cardname) {
		this.cardname = cardname;
	}

	/**
	 */
	public String getCardname() {
		return this.cardname;
	}

	/**
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 */
	public String getRemark() {
		return this.remark;
	}

	/**
	 */
	public void setHdwCheck(String hdwCheck) {
		this.hdwCheck = hdwCheck;
	}

	/**
	 */
	public String getHdwCheck() {
		return this.hdwCheck;
	}

	/**
	 */
	public void setUpdatedat(String updatedat) {
		this.updatedat = updatedat;
	}

	/**
	 */
	public String getUpdatedat() {
		return this.updatedat;
	}
	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	
	public String getAcademyNumber() {
		return academyNumber;
	}

	public void setAcademyNumber(String academyNumber) {
		this.academyNumber = academyNumber;
	}

	
	public Integer getDoorindex() {
		return doorindex;
	}

	public void setDoorindex(Integer doorindex) {
		this.doorindex = doorindex;
	}

	/**
	 */
	public CommonHdwlog() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(CommonHdwlog that) {
		setId(that.getId());
		setCardnumber(that.getCardnumber());
		setDeviceno(that.getDeviceno());
		setDatetime(that.getDatetime());
		setStatus(that.getStatus());
		setHardwareid(that.getHardwareid());
		setCardname(that.getCardname());
		setRemark(that.getRemark());
		setHdwCheck(that.getHdwCheck());
		setUpdatedat(that.getUpdatedat());
		setUsername(that.getUsername());
		setAcademyNumber(that.getAcademyNumber());
		setDoorindex(that.getDoorindex());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("id=[").append(id).append("] ");
		buffer.append("cardnumber=[").append(cardnumber).append("] ");
		buffer.append("deviceno=[").append(deviceno).append("] ");
		buffer.append("datetime=[").append(datetime).append("] ");
		buffer.append("status=[").append(status).append("] ");
		buffer.append("hardwareid=[").append(hardwareid).append("] ");
		buffer.append("cardname=[").append(cardname).append("] ");
		buffer.append("remark=[").append(remark).append("] ");
		buffer.append("hdwCheck=[").append(hdwCheck).append("] ");
		buffer.append("updatedat=[").append(updatedat).append("] ");
		buffer.append("username=[").append(username).append("] ");
		buffer.append("academyNumber=[").append(academyNumber).append("] ");
		buffer.append("doorindex=[").append(doorindex).append("] ");

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
		if (!(obj instanceof CommonHdwlog))
			return false;
		CommonHdwlog equalCheck = (CommonHdwlog) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
