package net.xidlims.domain;

import java.io.Serializable;

import java.lang.StringBuilder;
import java.util.Calendar;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import javax.xml.bind.annotation.*;

import javax.persistence.*;

/**
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "findAllLogs", query = "select myLog from Log myLog"),
		@NamedQuery(name = "findLogByAction", query = "select myLog from Log myLog where myLog.action = ?1"),
		@NamedQuery(name = "findLogByActionContaining", query = "select myLog from Log myLog where myLog.action like ?1"),
		@NamedQuery(name = "findLogByCreateTime", query = "select myLog from Log myLog where myLog.createTime = ?1"),
		@NamedQuery(name = "findLogByCreateTimeContaining", query = "select myLog from Log myLog where myLog.createTime like ?1"),
		@NamedQuery(name = "findLogByData", query = "select myLog from Log myLog where myLog.data = ?1"),
		@NamedQuery(name = "findLogByDataContaining", query = "select myLog from Log myLog where myLog.data like ?1"),
		@NamedQuery(name = "findLogById", query = "select myLog from Log myLog where myLog.id = ?1"),
		@NamedQuery(name = "findLogByIp", query = "select myLog from Log myLog where myLog.ip = ?1"),
		@NamedQuery(name = "findLogByIpContaining", query = "select myLog from Log myLog where myLog.ip like ?1"),
		@NamedQuery(name = "findLogByModule", query = "select myLog from Log myLog where myLog.module = ?1"),
		@NamedQuery(name = "findLogByModuleContaining", query = "select myLog from Log myLog where myLog.module like ?1"),
		@NamedQuery(name = "findLogByPrimaryKey", query = "select myLog from Log myLog where myLog.id = ?1"),
		@NamedQuery(name = "findLogByUserid", query = "select myLog from Log myLog where myLog.userid = ?1"),
		@NamedQuery(name = "findLogByUseridContaining", query = "select myLog from Log myLog where myLog.userid like ?1") })
@Table(catalog = "xidlims", name = "log")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "Log")
public class Log implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ��־��
	 * 
	 */

	@Column(name = "id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	@XmlElement
	Integer id;
	/**
	 * �û�
	 * 
	 */

	@Column(name = "userid", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String userid;
	/**
	 * ģ��
	 * 
	 */

	@Column(name = "module", length = 32, nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String module;
	/**
	 * ����
	 * 
	 */

	@Column(name = "action", length = 32, nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String action;
	/**
	 * ���
	 * 
	 */

	@Column(name = "data", length = 32)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String data;
	/**
	 */

	@Column(name = "ip", length = 100, nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String ip;
	/**
	 * ����ʱ��
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar createTime;

	/**
	 * ��־��
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * ��־��
	 * 
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * �û�
	 * 
	 */
	public void setUserid(String userid) {
		this.userid = userid;
	}

	/**
	 * �û�
	 * 
	 */
	public String getUserid() {
		return this.userid;
	}

	/**
	 * ģ��
	 * 
	 */
	public void setModule(String module) {
		this.module = module;
	}

	/**
	 * ģ��
	 * 
	 */
	public String getModule() {
		return this.module;
	}

	/**
	 * ����
	 * 
	 */
	public void setAction(String action) {
		this.action = action;
	}

	/**
	 * ����
	 * 
	 */
	public String getAction() {
		return this.action;
	}

	/**
	 * ���
	 * 
	 */
	public void setData(String data) {
		this.data = data;
	}

	/**
	 * ���
	 * 
	 */
	public String getData() {
		return this.data;
	}

	/**
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}

	/**
	 */
	public String getIp() {
		return this.ip;
	}

	/**
	 * ����ʱ��
	 * 
	 */
	public void setCreateTime(Calendar calendar) {
		this.createTime = calendar;
	}

	/**
	 * ����ʱ��
	 * 
	 */
	public Calendar getCreateTime() {
		return this.createTime;
	}

	/**
	 */
	public Log() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(Log that) {
		setId(that.getId());
		setUserid(that.getUserid());
		setModule(that.getModule());
		setAction(that.getAction());
		setData(that.getData());
		setIp(that.getIp());
		setCreateTime(that.getCreateTime());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("id=[").append(id).append("] ");
		buffer.append("userid=[").append(userid).append("] ");
		buffer.append("module=[").append(module).append("] ");
		buffer.append("action=[").append(action).append("] ");
		buffer.append("data=[").append(data).append("] ");
		buffer.append("ip=[").append(ip).append("] ");
		buffer.append("createTime=[").append(createTime).append("] ");

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
		if (!(obj instanceof Log))
			return false;
		Log equalCheck = (Log) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
