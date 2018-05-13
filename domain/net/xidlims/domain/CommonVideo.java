package net.xidlims.domain;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import net.xidlims.domain.LabAnnex;

import org.codehaus.jackson.annotate.JsonIgnore;

/**
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "findAllCommonVideos", query = "select myCommonVideo from CommonVideo myCommonVideo"),
		@NamedQuery(name = "findCommonVideoById", query = "select myCommonVideo from CommonVideo myCommonVideo where myCommonVideo.id = ?1"),
		@NamedQuery(name = "findCommonVideoByLabRoom", query = "select myCommonVideo from CommonVideo myCommonVideo where myCommonVideo.labRoom = ?1"),
		@NamedQuery(name = "findCommonVideoByLabRoomDevice", query = "select myCommonVideo from CommonVideo myCommonVideo where myCommonVideo.labRoomDevice = ?1"),
		@NamedQuery(name = "findCommonVideoByPrimaryKey", query = "select myCommonVideo from CommonVideo myCommonVideo where myCommonVideo.id = ?1"),
		@NamedQuery(name = "findCommonVideoByVideoName", query = "select myCommonVideo from CommonVideo myCommonVideo where myCommonVideo.videoName = ?1"),
		@NamedQuery(name = "findCommonVideoByVideoNameContaining", query = "select myCommonVideo from CommonVideo myCommonVideo where myCommonVideo.videoName like ?1"),
		@NamedQuery(name = "findCommonVideoByVideoUrl", query = "select myCommonVideo from CommonVideo myCommonVideo where myCommonVideo.videoUrl = ?1"),
		@NamedQuery(name = "findCommonVideoByVideoUrlContaining", query = "select myCommonVideo from CommonVideo myCommonVideo where myCommonVideo.videoUrl like ?1") })
@Table(catalog = "xidlims", name = "common_video")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "CommonVideo")
public class CommonVideo implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ��Ƶ��
	 * 
	 */

	@Column(name = "id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@XmlElement
	Integer id;
	/**
	 * ��Ƶ���
	 * 
	 */

	@Column(name = "videoName")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String videoName;
	/**
	 * ��Ƶ·��
	 * 
	 */

	@Column(name = "videoUrl")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String videoUrl;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "lab_room", referencedColumnName = "id") })
	@XmlTransient
	LabRoom labRoom;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "lab_room_device", referencedColumnName = "id") })
	@XmlTransient
	LabRoomDevice labRoomDevice;

	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "lab_annex", referencedColumnName = "id") })
	@XmlTransient
	LabAnnex labAnnex;
	
	/**
	 * ��Ƶ��
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * ��Ƶ��
	 * 
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * ��Ƶ���
	 * 
	 */
	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}

	/**
	 * ��Ƶ���
	 * 
	 */
	public String getVideoName() {
		return this.videoName;
	}

	/**
	 * ��Ƶ·��
	 * 
	 */
	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}

	/**
	 * ��Ƶ·��
	 * 
	 */
	public String getVideoUrl() {
		return this.videoUrl;
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
	public void setLabRoomDevice(LabRoomDevice labRoomDevice) {
		this.labRoomDevice = labRoomDevice;
	}

	/**
	 */
	@JsonIgnore
	public LabRoomDevice getLabRoomDevice() {
		return labRoomDevice;
	}
	/**
	 */
	public void setLabAnnex(LabAnnex labAnnex) {
		this.labAnnex = labAnnex;
	}

	/**
	 */
	@JsonIgnore
	public LabAnnex getLabAnnex() {
		return labAnnex;
	}
	/**
	 */
	public CommonVideo() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(CommonVideo that) {
		setId(that.getId());
		setVideoName(that.getVideoName());
		setVideoUrl(that.getVideoUrl());
		setLabRoom(that.getLabRoom());
		setLabRoomDevice(that.getLabRoomDevice());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("id=[").append(id).append("] ");
		buffer.append("videoName=[").append(videoName).append("] ");
		buffer.append("videoUrl=[").append(videoUrl).append("] ");
		buffer.append("labRoom=[").append(labRoom).append("] ");
		buffer.append("labRoomDevice=[").append(labRoomDevice).append("] ");

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
		if (!(obj instanceof CommonVideo))
			return false;
		CommonVideo equalCheck = (CommonVideo) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
