package net.xidlims.domain;

import java.io.Serializable;

import java.lang.StringBuilder;

import java.util.Calendar;

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
		@NamedQuery(name = "findAllSystemLogs", query = "select mySystemLog from SystemLog mySystemLog"),
		@NamedQuery(name = "findSystemLogByCalendarTime", query = "select mySystemLog from SystemLog mySystemLog where mySystemLog.calendarTime = ?1"),
		@NamedQuery(name = "findSystemLogByChildModule", query = "select mySystemLog from SystemLog mySystemLog where mySystemLog.childModule = ?1"),
		@NamedQuery(name = "findSystemLogByChildModuleContaining", query = "select mySystemLog from SystemLog mySystemLog where mySystemLog.childModule like ?1"),
		@NamedQuery(name = "findSystemLogById", query = "select mySystemLog from SystemLog mySystemLog where mySystemLog.id = ?1"),
		@NamedQuery(name = "findSystemLogByObjectiveDetail", query = "select mySystemLog from SystemLog mySystemLog where mySystemLog.objectiveDetail = ?1"),
		@NamedQuery(name = "findSystemLogByObjectiveDetailContaining", query = "select mySystemLog from SystemLog mySystemLog where mySystemLog.objectiveDetail like ?1"),
		@NamedQuery(name = "findSystemLogByOperationAction", query = "select mySystemLog from SystemLog mySystemLog where mySystemLog.operationAction = ?1"),
		@NamedQuery(name = "findSystemLogByPrimaryKey", query = "select mySystemLog from SystemLog mySystemLog where mySystemLog.id = ?1"),
		@NamedQuery(name = "findSystemLogBySuperModule", query = "select mySystemLog from SystemLog mySystemLog where mySystemLog.superModule = ?1"),
		@NamedQuery(name = "findSystemLogBySuperModuleContaining", query = "select mySystemLog from SystemLog mySystemLog where mySystemLog.superModule like ?1"),
		@NamedQuery(name = "findSystemLogByUserDetail", query = "select mySystemLog from SystemLog mySystemLog where mySystemLog.userDetail = ?1"),
		@NamedQuery(name = "findSystemLogByUserDetailContaining", query = "select mySystemLog from SystemLog mySystemLog where mySystemLog.userDetail like ?1"),
		@NamedQuery(name = "findSystemLogByUserIp", query = "select mySystemLog from SystemLog mySystemLog where mySystemLog.userIp = ?1"),
		@NamedQuery(name = "findSystemLogByUserIpContaining", query = "select mySystemLog from SystemLog mySystemLog where mySystemLog.userIp like ?1") })
@Table(catalog = "xidlims", name = "system_log")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "SystemLog")
public class SystemLog implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ϵͳ��־��
	 * 
	 */

	@Column(name = "id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@XmlElement
	Integer id;
	/**
	 * ������ģ��
	 * 
	 */

	@Column(name = "super_module", length = 40)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String superModule;
	/**
	 * ������ģ��
	 * 
	 */

	@Column(name = "child_module", length = 40)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String childModule;
	/**
	 * ��������
	 * 
	 */

	@Column(name = "objective_detail", length = 255)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String objectiveDetail;
	/**
	 * �û�����+����
	 * 
	 */

	@Column(name = "user_detail", length = 40)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String userDetail;
	
	/**
	 * �û�����+����
	 * 
	 */

	@Column(name = "user_academy", length = 40)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String userAcademy;
	
	/**
	 * �û�ip��ַ
	 * 
	 */

	@Column(name = "user_ip", length = 40)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String userIp;
	/**
	 * ����ʱ��
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "calendar_time")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar calendarTime;
	/**
	 * ����: 0 �½� 1 �༭ 2 �鿴 3 ɾ�� 4 �ύ 5 ���
	 * 
	 */

	@Column(name = "operation_action")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer operationAction;

	/**
	 * ϵͳ��־��
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * ϵͳ��־��
	 * 
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * ������ģ��
	 * 
	 */
	public void setSuperModule(String superModule) {
		this.superModule = superModule;
	}

	/**
	 * ������ģ��
	 * 
	 */
	public String getSuperModule() {
		return this.superModule;
	}

	/**
	 * ������ģ��
	 * 
	 */
	public void setChildModule(String childModule) {
		this.childModule = childModule;
	}

	/**
	 * ������ģ��
	 * 
	 */
	public String getChildModule() {
		return this.childModule;
	}

	/**
	 * ��������
	 * 
	 */
	public void setObjectiveDetail(String objectiveDetail) {
		this.objectiveDetail = objectiveDetail;
	}

	/**
	 * ��������
	 * 
	 */
	public String getObjectiveDetail() {
		return this.objectiveDetail;
	}

	/**
	 * �û�����+����
	 * 
	 */
	public void setUserDetail(String userDetail) {
		this.userDetail = userDetail;
	}

	/**
	 * �û�����+����
	 * 
	 */
	public String getUserDetail() {
		return this.userDetail;
	}

	
	
	public String getUserAcademy() {
		return this.userAcademy;
	}

	public void setUserAcademy(String userAcademy) {
		this.userAcademy = userAcademy;
	}

	/**
	 * �û�ip��ַ
	 * 
	 */
	public void setUserIp(String userIp) {
		this.userIp = userIp;
	}

	/**
	 * �û�ip��ַ
	 * 
	 */
	public String getUserIp() {
		return this.userIp;
	}

	/**
	 * ����ʱ��
	 * 
	 */
	public void setCalendarTime(Calendar calendarTime) {
		this.calendarTime = calendarTime;
	}

	/**
	 * ����ʱ��
	 * 
	 */
	public Calendar getCalendarTime() {
		return this.calendarTime;
	}

	/**
	 * ����: 0 �½� 1 �༭ 2 �鿴 3 ɾ�� 4 �ύ 5 ���
	 * 
	 */
	public void setOperationAction(Integer operationAction) {
		this.operationAction = operationAction;
	}

	/**
	 * ����: 0 �½� 1 �༭ 2 �鿴 3 ɾ�� 4 �ύ 5 ���
	 * 
	 */
	public Integer getOperationAction() {
		return this.operationAction;
	}

	/**
	 */
	public SystemLog() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(SystemLog that) {
		setId(that.getId());
		setSuperModule(that.getSuperModule());
		setChildModule(that.getChildModule());
		setObjectiveDetail(that.getObjectiveDetail());
		setUserDetail(that.getUserDetail());
		setUserAcademy(that.getUserAcademy());
		setUserIp(that.getUserIp());
		setCalendarTime(that.getCalendarTime());
		setOperationAction(that.getOperationAction());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("id=[").append(id).append("] ");
		buffer.append("superModule=[").append(superModule).append("] ");
		buffer.append("childModule=[").append(childModule).append("] ");
		buffer.append("objectiveDetail=[").append(objectiveDetail).append("] ");
		buffer.append("userDetail=[").append(userDetail).append("] ");
		buffer.append("userAcademy=[").append(userAcademy).append("] ");
		buffer.append("userIp=[").append(userIp).append("] ");
		buffer.append("calendarTime=[").append(calendarTime).append("] ");
		buffer.append("operationAction=[").append(operationAction).append("] ");

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
		if (!(obj instanceof SystemLog))
			return false;
		SystemLog equalCheck = (SystemLog) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
