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
		@NamedQuery(name = "findAllTExperimentLabRooms", query = "select myTExperimentLabRoom from TExperimentLabRoom myTExperimentLabRoom"),
		@NamedQuery(name = "findTExperimentLabRoomByExperimentSkillId", query = "select myTExperimentLabRoom from TExperimentLabRoom myTExperimentLabRoom where myTExperimentLabRoom.experimentSkillId = ?1"),
		@NamedQuery(name = "findTExperimentLabRoomById", query = "select myTExperimentLabRoom from TExperimentLabRoom myTExperimentLabRoom where myTExperimentLabRoom.id = ?1"),
		@NamedQuery(name = "findTExperimentLabRoomByLabRoomId", query = "select myTExperimentLabRoom from TExperimentLabRoom myTExperimentLabRoom where myTExperimentLabRoom.labRoomId = ?1"),
		@NamedQuery(name = "findTExperimentLabRoomByPrimaryKey", query = "select myTExperimentLabRoom from TExperimentLabRoom myTExperimentLabRoom where myTExperimentLabRoom.id = ?1") })
@Table(catalog = "xidlims", name = "t_experiment_lab_room")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "TExperimentLabRoom")
public class TExperimentLabRoom implements Serializable {
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
	 * ����ʵ����id
	 * 
	 */

	@Column(name = "lab_room_id")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer labRoomId;

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
	 * ����ʵ����id
	 * 
	 */
	public void setLabRoomId(Integer labRoomId) {
		this.labRoomId = labRoomId;
	}

	/**
	 * ����ʵ����id
	 * 
	 */
	public Integer getLabRoomId() {
		return this.labRoomId;
	}

	/**
	 */
	public TExperimentLabRoom() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(TExperimentLabRoom that) {
		setId(that.getId());
		setExperimentSkillId(that.getExperimentSkillId());
		setLabRoomId(that.getLabRoomId());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("id=[").append(id).append("] ");
		buffer.append("experimentSkillId=[").append(experimentSkillId).append("] ");
		buffer.append("labRoomId=[").append(labRoomId).append("] ");

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
		if (!(obj instanceof TExperimentLabRoom))
			return false;
		TExperimentLabRoom equalCheck = (TExperimentLabRoom) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
