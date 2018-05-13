package net.xidlims.domain;

import java.io.Serializable;

import java.lang.StringBuilder;

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
		@NamedQuery(name = "findAllTExperimentLabDevices", query = "select myTExperimentLabDevice from TExperimentLabDevice myTExperimentLabDevice"),
		@NamedQuery(name = "findTExperimentLabDeviceByExperimentSkillId", query = "select myTExperimentLabDevice from TExperimentLabDevice myTExperimentLabDevice where myTExperimentLabDevice.experimentSkillId = ?1"),
		@NamedQuery(name = "findTExperimentLabDeviceById", query = "select myTExperimentLabDevice from TExperimentLabDevice myTExperimentLabDevice where myTExperimentLabDevice.id = ?1"),
		@NamedQuery(name = "findTExperimentLabDeviceByLabDeviceId", query = "select myTExperimentLabDevice from TExperimentLabDevice myTExperimentLabDevice where myTExperimentLabDevice.labDeviceId = ?1"),
		@NamedQuery(name = "findTExperimentLabDeviceByPrimaryKey", query = "select myTExperimentLabDevice from TExperimentLabDevice myTExperimentLabDevice where myTExperimentLabDevice.id = ?1") })
@Table(catalog = "xidlims", name = "t_experiment_lab_device")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "TExperimentLabDevice")
public class TExperimentLabDevice implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 */

	@Column(name = "id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	@XmlElement
	Integer id;
	/**
	 * ����ʵ�鼼��id
	 * 
	 */

	@Column(name = "experiment_skill_id")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer experimentSkillId;
	/**
	 * �����豸id
	 * 
	 */

	@Column(name = "lab_device_id")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer labDeviceId;

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
	 * ����ʵ�鼼��id
	 * 
	 */
	public void setExperimentSkillId(Integer experimentSkillId) {
		this.experimentSkillId = experimentSkillId;
	}

	/**
	 * ����ʵ�鼼��id
	 * 
	 */
	public Integer getExperimentSkillId() {
		return this.experimentSkillId;
	}

	/**
	 * �����豸id
	 * 
	 */
	public void setLabDeviceId(Integer labDeviceId) {
		this.labDeviceId = labDeviceId;
	}

	/**
	 * �����豸id
	 * 
	 */
	public Integer getLabDeviceId() {
		return this.labDeviceId;
	}

	/**
	 */
	public TExperimentLabDevice() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(TExperimentLabDevice that) {
		setId(that.getId());
		setExperimentSkillId(that.getExperimentSkillId());
		setLabDeviceId(that.getLabDeviceId());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("id=[").append(id).append("] ");
		buffer.append("experimentSkillId=[").append(experimentSkillId).append("] ");
		buffer.append("labDeviceId=[").append(labDeviceId).append("] ");

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
		if (!(obj instanceof TExperimentLabDevice))
			return false;
		TExperimentLabDevice equalCheck = (TExperimentLabDevice) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
