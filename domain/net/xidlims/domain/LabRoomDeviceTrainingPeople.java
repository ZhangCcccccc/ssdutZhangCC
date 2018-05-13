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
		@NamedQuery(name = "findAllLabRoomDeviceTrainingPeoples", query = "select myLabRoomDeviceTrainingPeople from LabRoomDeviceTrainingPeople myLabRoomDeviceTrainingPeople"),
		@NamedQuery(name = "findLabRoomDeviceTrainingPeopleById", query = "select myLabRoomDeviceTrainingPeople from LabRoomDeviceTrainingPeople myLabRoomDeviceTrainingPeople where myLabRoomDeviceTrainingPeople.id = ?1"),
		@NamedQuery(name = "findLabRoomDeviceTrainingPeopleByPrimaryKey", query = "select myLabRoomDeviceTrainingPeople from LabRoomDeviceTrainingPeople myLabRoomDeviceTrainingPeople where myLabRoomDeviceTrainingPeople.id = ?1") })
@Table(catalog = "xidlims", name = "lab_room_device_training_people")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "test/net/xidlims/domain", name = "LabRoomDeviceTrainingPeople")
public class LabRoomDeviceTrainingPeople implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * �豸ԤԼ��ѵ���
	 * 
	 */

	@Column(name = "id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@XmlElement
	Integer id;

	@Column(name = "telephone")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String telephone;
	
	@Column(name = "e_mail")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String eMail;
	
	@Column(name = "message_flag")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer messageFlag;
	
	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "student", referencedColumnName = "username") })
	@XmlTransient
	User user;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "result", referencedColumnName = "id") })
	@XmlTransient
	CDictionary CDictionary;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "train", referencedColumnName = "id") })
	@XmlTransient
	LabRoomDeviceTraining labRoomDeviceTraining;

	/**
	 * �豸ԤԼ��ѵ���
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getMessageFlag() {
		return messageFlag;
	}

	public void setMessageFlag(Integer messageFlag) {
		this.messageFlag = messageFlag;
	}
	/**
	 * �豸ԤԼ��ѵ���
	 * 
	 */
	public Integer getId() {
		return this.id;
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
	public void setCDictionary(CDictionary CDictionary) {
		this.CDictionary = CDictionary;
	}

	/**
	 */
	public CDictionary getCDictionary() {
		return CDictionary;
	}

	/**
	 */
	public void setLabRoomDeviceTraining(LabRoomDeviceTraining labRoomDeviceTraining) {
		this.labRoomDeviceTraining = labRoomDeviceTraining;
	}

	/**
	 */
	public LabRoomDeviceTraining getLabRoomDeviceTraining() {
		return labRoomDeviceTraining;
	}

	/**
	 */
	public LabRoomDeviceTrainingPeople() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(LabRoomDeviceTrainingPeople that) {
		setId(that.getId());
		setUser(that.getUser());
		setCDictionary(that.getCDictionary());
		setLabRoomDeviceTraining(that.getLabRoomDeviceTraining());
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
		if (!(obj instanceof LabRoomDeviceTrainingPeople))
			return false;
		LabRoomDeviceTrainingPeople equalCheck = (LabRoomDeviceTrainingPeople) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
