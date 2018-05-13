package net.xidlims.domain;

import java.io.Serializable;

import java.lang.StringBuilder;

import java.util.Calendar;
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
		@NamedQuery(name = "findAllLabAnnexs", query = "select myLabAnnex from LabAnnex myLabAnnex"),
		@NamedQuery(name = "findLabAnnexByAwardInformation", query = "select myLabAnnex from LabAnnex myLabAnnex where myLabAnnex.awardInformation = ?1"),
		@NamedQuery(name = "findLabAnnexByBelongDepartment", query = "select myLabAnnex from LabAnnex myLabAnnex where myLabAnnex.belongDepartment = ?1"),
		@NamedQuery(name = "findLabAnnexByBelongDepartmentContaining", query = "select myLabAnnex from LabAnnex myLabAnnex where myLabAnnex.belongDepartment like ?1"),
		@NamedQuery(name = "findLabAnnexByContact", query = "select myLabAnnex from LabAnnex myLabAnnex where myLabAnnex.contact = ?1"),
		@NamedQuery(name = "findLabAnnexByContactContaining", query = "select myLabAnnex from LabAnnex myLabAnnex where myLabAnnex.contact like ?1"),
		@NamedQuery(name = "findLabAnnexByCreatedAt", query = "select myLabAnnex from LabAnnex myLabAnnex where myLabAnnex.createdAt = ?1"),
		@NamedQuery(name = "findLabAnnexById", query = "select myLabAnnex from LabAnnex myLabAnnex where myLabAnnex.id = ?1"),
		@NamedQuery(name = "findLabAnnexByLabActive", query = "select myLabAnnex from LabAnnex myLabAnnex where myLabAnnex.labActive = ?1"),
		@NamedQuery(name = "findLabAnnexByLabAttention", query = "select myLabAnnex from LabAnnex myLabAnnex where myLabAnnex.labAttention = ?1"),
		@NamedQuery(name = "findLabAnnexByLabCapacity", query = "select myLabAnnex from LabAnnex myLabAnnex where myLabAnnex.labCapacity = ?1"),
		@NamedQuery(name = "findLabAnnexByLabCapacityContaining", query = "select myLabAnnex from LabAnnex myLabAnnex where myLabAnnex.labCapacity like ?1"),
		@NamedQuery(name = "findLabAnnexByLabDescription", query = "select myLabAnnex from LabAnnex myLabAnnex where myLabAnnex.labDescription = ?1"),
		@NamedQuery(name = "findLabAnnexByLabEnName", query = "select myLabAnnex from LabAnnex myLabAnnex where myLabAnnex.labEnName = ?1"),
		@NamedQuery(name = "findLabAnnexByLabEnNameContaining", query = "select myLabAnnex from LabAnnex myLabAnnex where myLabAnnex.labEnName like ?1"),
		@NamedQuery(name = "findLabAnnexByLabName", query = "select myLabAnnex from LabAnnex myLabAnnex where myLabAnnex.labName = ?1"),
		@NamedQuery(name = "findLabAnnexByLabNameContaining", query = "select myLabAnnex from LabAnnex myLabAnnex where myLabAnnex.labName like ?1"),
		@NamedQuery(name = "findLabAnnexByLabNumber", query = "select myLabAnnex from LabAnnex myLabAnnex where myLabAnnex.labNumber = ?1"),
		@NamedQuery(name = "findLabAnnexByLabNumberContaining", query = "select myLabAnnex from LabAnnex myLabAnnex where myLabAnnex.labNumber like ?1"),
		@NamedQuery(name = "findLabAnnexByLabShortName", query = "select myLabAnnex from LabAnnex myLabAnnex where myLabAnnex.labShortName = ?1"),
		@NamedQuery(name = "findLabAnnexByLabShortNameContaining", query = "select myLabAnnex from LabAnnex myLabAnnex where myLabAnnex.labShortName like ?1"),
		@NamedQuery(name = "findLabAnnexByLabSubject", query = "select myLabAnnex from LabAnnex myLabAnnex where myLabAnnex.labSubject = ?1"),
		@NamedQuery(name = "findLabAnnexByLabSubjectContaining", query = "select myLabAnnex from LabAnnex myLabAnnex where myLabAnnex.labSubject like ?1"),
		@NamedQuery(name = "findLabAnnexByLabUseArea", query = "select myLabAnnex from LabAnnex myLabAnnex where myLabAnnex.labUseArea = ?1"),
		@NamedQuery(name = "findLabAnnexByLabUseAreaContaining", query = "select myLabAnnex from LabAnnex myLabAnnex where myLabAnnex.labUseArea like ?1"),
		@NamedQuery(name = "findLabAnnexByPrimaryKey", query = "select myLabAnnex from LabAnnex myLabAnnex where myLabAnnex.id = ?1"),
		@NamedQuery(name = "findLabAnnexByUpdatedAt", query = "select myLabAnnex from LabAnnex myLabAnnex where myLabAnnex.updatedAt = ?1") })
@Table(catalog = "xidlims", name = "lab_annex")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "LabAnnex")
@XmlRootElement(namespace = "xidlims/net/xidlims/domain")
public class LabAnnex implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ʵ���Ҹ�����Ϣ��
	 * 
	 */

	@Column(name = "id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	@XmlElement
	Integer id;
	/**
	 * ʵ���ҷ�������
	 * 
	 */

	@Column(name = "lab_number", length = 40)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String labNumber;
	/**
	 * ʵ�������
	 * 
	 */

	@Column(name = "lab_name", length = 100)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String labName;
	/**
	 * ʵ����Ӣ�����
	 * 
	 */

	@Column(name = "lab_en_name", length = 100)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String labEnName;
	/**
	 * ʵ���Ҽ��
	 * 
	 */

	@Column(name = "lab_short_name", length = 50)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String labShortName;
	/**
	 * ����ѧ��
	 * 
	 */

	@Column(name = "lab_subject")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String labSubject;
	/**
	 * �����
	 * 
	 */

	@Column(name = "belong_department", length = 200)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String belongDepartment;
	/**
	 * ��ϵ��ʽ
	 * 
	 */

	@Column(name = "contact")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String contact;
	/**
	 * ʵ���Ҽ��
	 * 
	 */

	@Column(name = "lab_description", columnDefinition = "TEXT")
	@Basic(fetch = FetchType.EAGER)
	@Lob
	@XmlElement
	String labDescription;
	/**
	 * �����ƶ�
	 * 
	 */

	@Column(name = "lab_attention", columnDefinition = "TEXT")
	@Basic(fetch = FetchType.EAGER)
	@Lob
	@XmlElement
	String labAttention;
	/**
	 * ����Ϣ
	 * 
	 */

	@Column(name = "award_information", columnDefinition = "TEXT")
	@Basic(fetch = FetchType.EAGER)
	@Lob
	@XmlElement
	String awardInformation;
	/**
	 * ʵ��������
	 * 
	 */

	@Column(name = "lab_capacity", length = 50)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String labCapacity;
	/**
	 * ʵ����ʹ�����
	 * 
	 */

	@Column(name = "lab_use_area", length = 10)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String labUseArea;
	/**
	 * �Ƿ񿪷ţ����
	 * 
	 */

	@Column(name = "lab_active")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer labActive;
	/**
	 * ����ʱ��
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar createdAt;
	/**
	 * ����ʱ��
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar updatedAt;

	/**
	 * ������
	 * 
	 */

	@Column(name = "created_by", length = 40)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String createdBy;
	/**
	 * ������
	 * 
	 */

	@Column(name = "updated_by", length = 40)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String updatedBy;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "belong_center", referencedColumnName = "id") })
	@XmlTransient
	LabCenter labCenter;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "lab_type", referencedColumnName = "id") })
	@XmlTransient
	CDictionary CDictionaryByLabAnnex;
	/**
	 */
	@OneToMany(mappedBy = "labAnnex", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.CommonVideo> commonVideos;
	/**
	 */
	@OneToMany(mappedBy = "labAnnex", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.LabRoom> labRooms;
	/**
	 */
	@OneToMany(mappedBy = "labAnnex", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.CommonDocument> commonDocuments;

	/**
	 * ʵ���Ҹ�����Ϣ��
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * ʵ���Ҹ�����Ϣ��
	 * 
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * ʵ���ҷ�������
	 * 
	 */
	public void setLabNumber(String labNumber) {
		this.labNumber = labNumber;
	}

	/**
	 * ʵ���ҷ�������
	 * 
	 */
	public String getLabNumber() {
		return this.labNumber;
	}

	/**
	 * ʵ�������
	 * 
	 */
	public void setLabName(String labName) {
		this.labName = labName;
	}

	/**
	 * ʵ�������
	 * 
	 */
	public String getLabName() {
		return this.labName;
	}

	/**
	 * ʵ����Ӣ�����
	 * 
	 */
	public void setLabEnName(String labEnName) {
		this.labEnName = labEnName;
	}

	/**
	 * ʵ����Ӣ�����
	 * 
	 */
	public String getLabEnName() {
		return this.labEnName;
	}

	/**
	 * ʵ���Ҽ��
	 * 
	 */
	public void setLabShortName(String labShortName) {
		this.labShortName = labShortName;
	}

	/**
	 * ʵ���Ҽ��
	 * 
	 */
	public String getLabShortName() {
		return this.labShortName;
	}

	/**
	 * ����ѧ��
	 * 
	 */
	public void setLabSubject(String labSubject) {
		this.labSubject = labSubject;
	}

	/**
	 * ����ѧ��
	 * 
	 */
	public String getLabSubject() {
		return this.labSubject;
	}

	/**
	 * �����
	 * 
	 */
	public void setBelongDepartment(String belongDepartment) {
		this.belongDepartment = belongDepartment;
	}

	/**
	 * �����
	 * 
	 */
	public String getBelongDepartment() {
		return this.belongDepartment;
	}

	/**
	 * ��ϵ��ʽ
	 * 
	 */
	public void setContact(String contact) {
		this.contact = contact;
	}

	/**
	 * ��ϵ��ʽ
	 * 
	 */
	public String getContact() {
		return this.contact;
	}

	/**
	 * ʵ���Ҽ��
	 * 
	 */
	public void setLabDescription(String labDescription) {
		this.labDescription = labDescription;
	}

	/**
	 * ʵ���Ҽ��
	 * 
	 */
	public String getLabDescription() {
		return this.labDescription;
	}

	/**
	 * �����ƶ�
	 * 
	 */
	public void setLabAttention(String labAttention) {
		this.labAttention = labAttention;
	}

	/**
	 * �����ƶ�
	 * 
	 */
	public String getLabAttention() {
		return this.labAttention;
	}

	/**
	 * ����Ϣ
	 * 
	 */
	public void setAwardInformation(String awardInformation) {
		this.awardInformation = awardInformation;
	}

	/**
	 * ����Ϣ
	 * 
	 */
	public String getAwardInformation() {
		return this.awardInformation;
	}

	/**
	 * ʵ��������
	 * 
	 */
	public void setLabCapacity(String labCapacity) {
		this.labCapacity = labCapacity;
	}

	/**
	 * ʵ��������
	 * 
	 */
	public String getLabCapacity() {
		return this.labCapacity;
	}

	/**
	 * ʵ����ʹ�����
	 * 
	 */
	public void setLabUseArea(String labUseArea) {
		this.labUseArea = labUseArea;
	}

	/**
	 * ʵ����ʹ�����
	 * 
	 */
	public String getLabUseArea() {
		return this.labUseArea;
	}

	/**
	 * �Ƿ񿪷ţ����
	 * 
	 */
	public void setLabActive(Integer labActive) {
		this.labActive = labActive;
	}

	/**
	 * �Ƿ񿪷ţ����
	 * 
	 */
	public Integer getLabActive() {
		return this.labActive;
	}

	/**
	 * ����ʱ��
	 * 
	 */
	public void setCreatedAt(Calendar createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * ����ʱ��
	 * 
	 */
	public Calendar getCreatedAt() {
		return this.createdAt;
	}

	/**
	 * ����ʱ��
	 * 
	 */
	public void setUpdatedAt(Calendar updatedAt) {
		this.updatedAt = updatedAt;
	}

	/**
	 * ����ʱ��
	 * 
	 */
	public Calendar getUpdatedAt() {
		return this.updatedAt;
	}

	/**
	 * ������
	 * 
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * ������
	 * 
	 */
	public String getCreatedBy() {
		return this.createdBy;
	}

	/**
	 * ������
	 * 
	 */
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	/**
	 * ������
	 * 
	 */
	public String getUpdatedBy() {
		return this.updatedBy;
	}

	/**
	 */
	public void setLabCenter(LabCenter labCenter) {
		this.labCenter = labCenter;
	}

	/**
	 */
	@JsonIgnore
	public LabCenter getLabCenter() {
		return labCenter;
	}
	
	public void setCDictionaryByLabAnnex(CDictionary CDictionaryByLabAnnex) {
		this.CDictionaryByLabAnnex = CDictionaryByLabAnnex;
	}
	
	public CDictionary getCDictionaryByLabAnnex() {
		return CDictionaryByLabAnnex;
	}

	/**
	 */
	public void setCommonVideos(Set<CommonVideo> commonVideos) {
		this.commonVideos = commonVideos;
	}

	/**
	 */
	@JsonIgnore
	public Set<CommonVideo> getCommonVideos() {
		if (commonVideos == null) {
			commonVideos = new java.util.LinkedHashSet<net.xidlims.domain.CommonVideo>();
		}
		return commonVideos;
	}

	/**
	 */
	public void setLabRooms(Set<LabRoom> labRooms) {
		this.labRooms = labRooms;
	}

	/**
	 */
	@JsonIgnore
	public Set<LabRoom> getLabRooms() {
		if (labRooms == null) {
			labRooms = new java.util.LinkedHashSet<net.xidlims.domain.LabRoom>();
		}
		return labRooms;
	}

	/**
	 */
	public void setCommonDocuments(Set<CommonDocument> commonDocuments) {
		this.commonDocuments = commonDocuments;
	}

	/**
	 */
	@JsonIgnore
	public Set<CommonDocument> getCommonDocuments() {
		if (commonDocuments == null) {
			commonDocuments = new java.util.LinkedHashSet<net.xidlims.domain.CommonDocument>();
		}
		return commonDocuments;
	}

	/**
	 */
	public LabAnnex() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(LabAnnex that) {
		setId(that.getId());
		setLabNumber(that.getLabNumber());
		setLabName(that.getLabName());
		setLabEnName(that.getLabEnName());
		setLabShortName(that.getLabShortName());
		setLabSubject(that.getLabSubject());
		setBelongDepartment(that.getBelongDepartment());
		setContact(that.getContact());
		setLabDescription(that.getLabDescription());
		setLabAttention(that.getLabAttention());
		setAwardInformation(that.getAwardInformation());
		setLabCapacity(that.getLabCapacity());
		setLabUseArea(that.getLabUseArea());
		setLabActive(that.getLabActive());
		setCreatedAt(that.getCreatedAt());
		setUpdatedAt(that.getUpdatedAt());
		setCreatedBy(that.getCreatedBy());
		setLabCenter(that.getLabCenter());
		setCDictionaryByLabAnnex(that.getCDictionaryByLabAnnex());
		setUpdatedBy(that.getUpdatedBy());
		setCommonVideos(new java.util.LinkedHashSet<net.xidlims.domain.CommonVideo>(that.getCommonVideos()));
		setLabRooms(new java.util.LinkedHashSet<net.xidlims.domain.LabRoom>(that.getLabRooms()));
		setCommonDocuments(new java.util.LinkedHashSet<net.xidlims.domain.CommonDocument>(that.getCommonDocuments()));
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("id=[").append(id).append("] ");
		buffer.append("labNumber=[").append(labNumber).append("] ");
		buffer.append("labName=[").append(labName).append("] ");
		buffer.append("labEnName=[").append(labEnName).append("] ");
		buffer.append("labShortName=[").append(labShortName).append("] ");
		buffer.append("labSubject=[").append(labSubject).append("] ");
		buffer.append("belongDepartment=[").append(belongDepartment).append("] ");
		buffer.append("contact=[").append(contact).append("] ");
		buffer.append("labDescription=[").append(labDescription).append("] ");
		buffer.append("labAttention=[").append(labAttention).append("] ");
		buffer.append("awardInformation=[").append(awardInformation).append("] ");
		buffer.append("labCapacity=[").append(labCapacity).append("] ");
		buffer.append("labUseArea=[").append(labUseArea).append("] ");
		buffer.append("labActive=[").append(labActive).append("] ");
		buffer.append("createdAt=[").append(createdAt).append("] ");
		buffer.append("updatedAt=[").append(updatedAt).append("] ");

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
		if (!(obj instanceof LabAnnex))
			return false;
		LabAnnex equalCheck = (LabAnnex) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
