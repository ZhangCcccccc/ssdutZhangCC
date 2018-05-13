package net.xidlims.domain;

import java.io.Serializable;

import java.lang.StringBuilder;

import java.util.Calendar;
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
		@NamedQuery(name = "findAllLabRoomDeviceRepairs", query = "select myLabRoomDeviceRepair from LabRoomDeviceRepair myLabRoomDeviceRepair"),
		@NamedQuery(name = "findLabRoomDeviceRepairByCreateTime", query = "select myLabRoomDeviceRepair from LabRoomDeviceRepair myLabRoomDeviceRepair where myLabRoomDeviceRepair.createTime = ?1"),
		@NamedQuery(name = "findLabRoomDeviceRepairByDescription", query = "select myLabRoomDeviceRepair from LabRoomDeviceRepair myLabRoomDeviceRepair where myLabRoomDeviceRepair.description = ?1"),
		@NamedQuery(name = "findLabRoomDeviceRepairByHardwareFailure", query = "select myLabRoomDeviceRepair from LabRoomDeviceRepair myLabRoomDeviceRepair where myLabRoomDeviceRepair.hardwareFailure = ?1"),
		@NamedQuery(name = "findLabRoomDeviceRepairById", query = "select myLabRoomDeviceRepair from LabRoomDeviceRepair myLabRoomDeviceRepair where myLabRoomDeviceRepair.id = ?1"),
		@NamedQuery(name = "findLabRoomDeviceRepairByPrimaryKey", query = "select myLabRoomDeviceRepair from LabRoomDeviceRepair myLabRoomDeviceRepair where myLabRoomDeviceRepair.id = ?1"),
		@NamedQuery(name = "findLabRoomDeviceRepairByRepairRecords", query = "select myLabRoomDeviceRepair from LabRoomDeviceRepair myLabRoomDeviceRepair where myLabRoomDeviceRepair.repairRecords = ?1"),
		@NamedQuery(name = "findLabRoomDeviceRepairByRepairTime", query = "select myLabRoomDeviceRepair from LabRoomDeviceRepair myLabRoomDeviceRepair where myLabRoomDeviceRepair.repairTime = ?1"),
		@NamedQuery(name = "findLabRoomDeviceRepairByRestoreTime", query = "select myLabRoomDeviceRepair from LabRoomDeviceRepair myLabRoomDeviceRepair where myLabRoomDeviceRepair.restoreTime = ?1"),
		@NamedQuery(name = "findLabRoomDeviceRepairBySoftwareFailure", query = "select myLabRoomDeviceRepair from LabRoomDeviceRepair myLabRoomDeviceRepair where myLabRoomDeviceRepair.softwareFailure = ?1") })
@Table(catalog = "xidlims", name = "lab_room_device_repair")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "LabRoomDeviceRepair")
public class LabRoomDeviceRepair implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * �豸ά�ޱ�
	 * 
	 */

	@Column(name = "id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	@XmlElement
	Integer id;
	/**
	 * ����ʱ��
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "repair_time")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar repairTime;
	/**
	 * Ӳ������
	 * 
	 */

	@Column(name = "hardware_failure", columnDefinition = "TEXT")
	@Basic(fetch = FetchType.EAGER)
	@Lob
	@XmlElement
	String hardwareFailure;
	/**
	 * �������
	 * 
	 */

	@Column(name = "software_failure", columnDefinition = "TEXT")
	@Basic(fetch = FetchType.EAGER)
	@Lob
	@XmlElement
	String softwareFailure;
	/**
	 * ��������
	 * 
	 */

	@Column(name = "description", columnDefinition = "TEXT")
	@Basic(fetch = FetchType.EAGER)
	@Lob
	@XmlElement
	String description;
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
	 * �޸�ʱ��
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "restore_time")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar restoreTime;
	/**
	 * �����¼
	 * 
	 */

	@Column(name = "repair_records", columnDefinition = "TEXT")
	@Basic(fetch = FetchType.EAGER)
	@Lob
	@XmlElement
	String repairRecords;

	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "repair_user", referencedColumnName = "username") })
	@XmlTransient
	User user;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "status_id", referencedColumnName = "id") })
	@XmlTransient
	CDictionary CDictionaryByStatusId;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "failure_choice", referencedColumnName = "id") })
	@XmlTransient
	CDictionary CDictionaryByFailureChoice;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "partition_id", referencedColumnName = "id") })
	@XmlTransient
	CDictionary CDictionaryByPartitionId;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "school_device_id", referencedColumnName = "id") })
	@XmlTransient
	LabRoomDevice labRoomDevice;

	/**
	 * �豸ά�ޱ�
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * �豸ά�ޱ�
	 * 
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * ����ʱ��
	 * 
	 */
	public void setRepairTime(Calendar repairTime) {
		this.repairTime = repairTime;
	}

	/**
	 * ����ʱ��
	 * 
	 */
	public Calendar getRepairTime() {
		return this.repairTime;
	}

	/**
	 * Ӳ������
	 * 
	 */
	public void setHardwareFailure(String hardwareFailure) {
		this.hardwareFailure = hardwareFailure;
	}

	/**
	 * Ӳ������
	 * 
	 */
	public String getHardwareFailure() {
		return this.hardwareFailure;
	}

	/**
	 * �������
	 * 
	 */
	public void setSoftwareFailure(String softwareFailure) {
		this.softwareFailure = softwareFailure;
	}

	/**
	 * �������
	 * 
	 */
	public String getSoftwareFailure() {
		return this.softwareFailure;
	}

	/**
	 * ��������
	 * 
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * ��������
	 * 
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * ����ʱ��
	 * 
	 */
	public void setCreateTime(Calendar createTime) {
		this.createTime = createTime;
	}

	/**
	 * ����ʱ��
	 * 
	 */
	public Calendar getCreateTime() {
		return this.createTime;
	}

	/**
	 * �޸�ʱ��
	 * 
	 */
	public void setRestoreTime(Calendar restoreTime) {
		this.restoreTime = restoreTime;
	}

	/**
	 * �޸�ʱ��
	 * 
	 */
	public Calendar getRestoreTime() {
		return this.restoreTime;
	}

	/**
	 * �����¼
	 * 
	 */
	public void setRepairRecords(String repairRecords) {
		this.repairRecords = repairRecords;
	}

	/**
	 * �����¼
	 * 
	 */
	public String getRepairRecords() {
		return this.repairRecords;
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

	public CDictionary getCDictionaryByStatusId() {
		return CDictionaryByStatusId;
	}

	public void setCDictionaryByStatusId(CDictionary cDictionaryByStatusId) {
		CDictionaryByStatusId = cDictionaryByStatusId;
	}

	public CDictionary getCDictionaryByFailureChoice() {
		return CDictionaryByFailureChoice;
	}

	public void setCDictionaryByFailureChoice(CDictionary cDictionaryByFailureChoice) {
		CDictionaryByFailureChoice = cDictionaryByFailureChoice;
	}

	public CDictionary getCDictionaryByPartitionId() {
		return CDictionaryByPartitionId;
	}

	public void setCDictionaryByPartitionId(CDictionary cDictionaryByPartitionId) {
		CDictionaryByPartitionId = cDictionaryByPartitionId;
	}

	public LabRoomDevice getLabRoomDevice() {
		return labRoomDevice;
	}

	public void setLabRoomDevice(LabRoomDevice labRoomDevice) {
		this.labRoomDevice = labRoomDevice;
	}

	/**
	 */
	public LabRoomDeviceRepair() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(LabRoomDeviceRepair that) {
		setId(that.getId());
		setRepairTime(that.getRepairTime());
		setHardwareFailure(that.getHardwareFailure());
		setSoftwareFailure(that.getSoftwareFailure());
		setDescription(that.getDescription());
		setCreateTime(that.getCreateTime());
		setRestoreTime(that.getRestoreTime());
		setRepairRecords(that.getRepairRecords());
		setUser(that.getUser());
		setCDictionaryByStatusId(that.getCDictionaryByStatusId());
		setCDictionaryByFailureChoice(that.getCDictionaryByFailureChoice());
		setCDictionaryByPartitionId(that.getCDictionaryByPartitionId());
		setLabRoomDevice(that.getLabRoomDevice());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("id=[").append(id).append("] ");
		buffer.append("repairTime=[").append(repairTime).append("] ");
		buffer.append("hardwareFailure=[").append(hardwareFailure).append("] ");
		buffer.append("softwareFailure=[").append(softwareFailure).append("] ");
		buffer.append("description=[").append(description).append("] ");
		buffer.append("createTime=[").append(createTime).append("] ");
		buffer.append("restoreTime=[").append(restoreTime).append("] ");
		buffer.append("repairRecords=[").append(repairRecords).append("] ");

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
		if (!(obj instanceof LabRoomDeviceRepair))
			return false;
		LabRoomDeviceRepair equalCheck = (LabRoomDeviceRepair) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
