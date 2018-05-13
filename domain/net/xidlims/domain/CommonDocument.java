package net.xidlims.domain;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import net.xidlims.domain.OperationOutline;
import net.xidlims.domain.LabAnnex;

import org.codehaus.jackson.annotate.JsonIgnore;

/**
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "findAllCommonDocuments", query = "select myCommonDocument from CommonDocument myCommonDocument"),
		@NamedQuery(name = "findCommonDocumentByDocumentName", query = "select myCommonDocument from CommonDocument myCommonDocument where myCommonDocument.documentName = ?1"),
		@NamedQuery(name = "findCommonDocumentByDocumentNameContaining", query = "select myCommonDocument from CommonDocument myCommonDocument where myCommonDocument.documentName like ?1"),
		@NamedQuery(name = "findCommonDocumentByDocumentUrl", query = "select myCommonDocument from CommonDocument myCommonDocument where myCommonDocument.documentUrl = ?1"),
		@NamedQuery(name = "findCommonDocumentByDocumentUrlContaining", query = "select myCommonDocument from CommonDocument myCommonDocument where myCommonDocument.documentUrl like ?1"),
		@NamedQuery(name = "findCommonDocumentById", query = "select myCommonDocument from CommonDocument myCommonDocument where myCommonDocument.id = ?1"),
		@NamedQuery(name = "findCommonDocumentByLabRoom", query = "select myCommonDocument from CommonDocument myCommonDocument where myCommonDocument.labRoom = ?1"),
		@NamedQuery(name = "findCommonDocumentByLabRoomDevice", query = "select myCommonDocument from CommonDocument myCommonDocument where myCommonDocument.labRoomDevice = ?1"),
		@NamedQuery(name = "findCommonDocumentByPrimaryKey", query = "select myCommonDocument from CommonDocument myCommonDocument where myCommonDocument.id = ?1"),
		@NamedQuery(name = "findCommonDocumentByType", query = "select myCommonDocument from CommonDocument myCommonDocument where myCommonDocument.type = ?1") })
@Table(catalog = "xidlims", name = "common_document")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "CommonDocument")
public class CommonDocument implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * �ĵ���
	 * 
	 */

	@Column(name = "id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@XmlElement
	Integer id;
	/**
	 * ���ͣ�1��ʾͼƬ2��ʾ�ĵ���
	 * 
	 */

	@Column(name = "type")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer type;
	/**
	 * �ĵ����
	 * 
	 */

	@Column(name = "documentName")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String documentName;
	/**
	 * �ĵ�����·��
	 * 
	 */

	@Column(name = "documentUrl")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String documentUrl;
	
	
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
	//2015.10.14新增
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "lab_construction_project", referencedColumnName = "id") })
	@XmlTransient
	LabConstructionProject labConstructionProject;
	
	
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "lab_construction_acceptance", referencedColumnName = "id") })
	@XmlTransient
	LabConstructionAcceptance labConstructionAcceptance;
	
	
	/**
	 * ���ͣ�1��ʾͼƬ2��ʾ�ĵ���
	 * 
	 */

	@Column(name = "flag")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer flag;
	
	
	@Column(name = "comments", columnDefinition = "TEXT")
	@Basic(fetch = FetchType.EAGER)
	@Lob
	@XmlElement
	String comments;
	
	
	/**
	 * ��Ŀ���
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar createdAt;
	
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "created_by", referencedColumnName = "username") })
	@XmlTransient
	User user;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "operation_online", referencedColumnName = "id") })
	@XmlTransient
	OperationOutline operationOutline;
	
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "item_id", referencedColumnName = "id") })
	@XmlTransient
	OperationItem operationItem;


	/**
	 * �ĵ���
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * �ĵ���
	 * 
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * ���ͣ�1��ʾͼƬ2��ʾ�ĵ���
	 * 
	 */
	public OperationOutline getOperationOutline() {
		return operationOutline;
	}

	public void setOperationOutline(OperationOutline operationOutline) {
		this.operationOutline = operationOutline;
	}
	
	/**
	 * ���ͣ�1��ʾͼƬ2��ʾ�ĵ���
	 * 
	 */
	public OperationItem getOperationItem() {
		return operationItem;
	}

	public void setOperationItem(OperationItem operationItem) {
		this.operationItem = operationItem;
	}

	/**
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * ���ͣ�1��ʾͼƬ2��ʾ�ĵ���
	 * 
	 */
	public Integer getType() {
		return this.type;
	}

	/**
	 * �ĵ����
	 * 
	 */
	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}

	/**
	 * �ĵ����
	 * 
	 */
	public String getDocumentName() {
		return this.documentName;
	}

	/**
	 * �ĵ�����·��
	 * 
	 */
	public void setDocumentUrl(String documentUrl) {
		this.documentUrl = documentUrl;
	}

	/**
	 * �ĵ�����·��
	 * 
	 */
	public String getDocumentUrl() {
		return this.documentUrl;
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
	@JsonIgnore
	public LabConstructionProject getLabConstructionProject() {
		return labConstructionProject;
	}

	public void setLabConstructionProject(LabConstructionProject labConstructionProject) {
		this.labConstructionProject = labConstructionProject;
	}

	/**
	 */
	@JsonIgnore
	public LabConstructionAcceptance getLabConstructionAcceptance() {
		return labConstructionAcceptance;
	}

	public void setLabConstructionAcceptance(LabConstructionAcceptance labConstructionAcceptance) {
		this.labConstructionAcceptance = labConstructionAcceptance;
	}

	
	
	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Calendar getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Calendar createdAt) {
		this.createdAt = createdAt;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	/**
	 */
	public CommonDocument() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(CommonDocument that) {
		setId(that.getId());
		setType(that.getType());
		setDocumentName(that.getDocumentName());
		setDocumentUrl(that.getDocumentUrl());
		setLabRoom(that.getLabRoom());
		setLabRoomDevice(that.getLabRoomDevice());
		setLabConstructionProject(that.getLabConstructionProject());
		setLabConstructionAcceptance(that.getLabConstructionAcceptance());
		setComments(that.getComments());
		setCreatedAt(that.getCreatedAt());
		setUser(that.getUser());
		setFlag(that.getFlag());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("id=[").append(id).append("] ");
		buffer.append("type=[").append(type).append("] ");
		buffer.append("documentName=[").append(documentName).append("] ");
		buffer.append("documentUrl=[").append(documentUrl).append("] ");
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
		if (!(obj instanceof CommonDocument))
			return false;
		CommonDocument equalCheck = (CommonDocument) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
