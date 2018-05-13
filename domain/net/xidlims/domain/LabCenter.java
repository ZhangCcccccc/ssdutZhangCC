package net.xidlims.domain;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import org.codehaus.jackson.annotate.JsonIgnore;

/**
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "findAllLabCenters", query = "select myLabCenter from LabCenter myLabCenter"),
		@NamedQuery(name = "findLabCenterByCenterAddress", query = "select myLabCenter from LabCenter myLabCenter where myLabCenter.centerAddress = ?1"),
		@NamedQuery(name = "findLabCenterByCenterAddressContaining", query = "select myLabCenter from LabCenter myLabCenter where myLabCenter.centerAddress like ?1"),
		@NamedQuery(name = "findLabCenterByCenterName", query = "select myLabCenter from LabCenter myLabCenter where myLabCenter.centerName = ?1"),
		@NamedQuery(name = "findLabCenterByCenterNameContaining", query = "select myLabCenter from LabCenter myLabCenter where myLabCenter.centerName like ?1"),
		@NamedQuery(name = "findLabCenterByCenterNumber", query = "select myLabCenter from LabCenter myLabCenter where myLabCenter.centerNumber = ?1"),
		@NamedQuery(name = "findLabCenterByCenterNumberContaining", query = "select myLabCenter from LabCenter myLabCenter where myLabCenter.centerNumber like ?1"),
		@NamedQuery(name = "findLabCenterByCreatedAt", query = "select myLabCenter from LabCenter myLabCenter where myLabCenter.createdAt = ?1"),
		@NamedQuery(name = "findLabCenterByEnabled", query = "select myLabCenter from LabCenter myLabCenter where myLabCenter.enabled = ?1"),
		@NamedQuery(name = "findLabCenterById", query = "select myLabCenter from LabCenter myLabCenter where myLabCenter.id = ?1"),
		@NamedQuery(name = "findLabCenterByPrimaryKey", query = "select myLabCenter from LabCenter myLabCenter where myLabCenter.id = ?1"),
		@NamedQuery(name = "findLabCenterByUpdatedAt", query = "select myLabCenter from LabCenter myLabCenter where myLabCenter.updatedAt = ?1") })
@Table(catalog = "xidlims", name = "lab_center")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "LabCenter")
@XmlRootElement(namespace = "xidlims/net/xidlims/domain")
public class LabCenter implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ʵ�������ı�
	 * 
	 */

	@Column(name = "id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@XmlElement
	Integer id;
	/**
	 * ʵ�����ı��
	 * 
	 */

	@Column(name = "center_number", length = 40)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String centerNumber;
	/**
	 * ʵ���������
	 * 
	 */

	@Column(name = "center_name", length = 100)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String centerName;
	/**
	 * ���ĵ�ַ
	 * 
	 */

	@Column(name = "center_address")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String centerAddress;
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
	 * �Ƿ����
	 * 
	 */

	@Column(name = "enabled")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer enabled;

	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "center_manager", referencedColumnName = "username") })
	@XmlTransient
	User userByCenterManager;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "created_by", referencedColumnName = "username") })
	@XmlTransient
	User userByCreatedBy;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "updated_by", referencedColumnName = "username") })
	@XmlTransient
	User userByUpdatedBy;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "campus_number", referencedColumnName = "campus_number") })
	@XmlTransient
	SystemCampus systemCampus;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "academy_number", referencedColumnName = "academy_number") })
	@XmlTransient
	SchoolAcademy schoolAcademy;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "building_number", referencedColumnName = "build_number") })
	@XmlTransient
	SystemBuild systemBuild;
	/**
	 */
	@OneToMany(mappedBy = "labCenter", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.LabWorker> labWorkers;
	/**
	 */
	@OneToMany(mappedBy = "labCenter", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.LabRoom> labRooms;
	/**
	 */
	@OneToMany(mappedBy = "labCenter", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.LabAnnex> labAnnexes;
	
	/**
	 */
	@OneToMany(mappedBy = "labCenter", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.OperationItem> operationItems;
	
	/**
	 */
	@OneToMany(mappedBy = "labCenter", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.OperationOutline> operationOutlines;
	
	/**
     */
    @OneToMany(mappedBy = "labCenter", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
    @XmlElement(name = "", namespace = "")
    java.util.Set<net.xidlims.domain.InnerAcademyCenter> innerAcademyCenters;
    
    /**
	 */
	@OneToMany(mappedBy = "labCenter", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.User> users;
	/**
	 * ʵ�������ı�
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * ʵ�������ı�
	 * 
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * ʵ�����ı��
	 * 
	 */
	public void setCenterNumber(String centerNumber) {
		this.centerNumber = centerNumber;
	}

	/**
	 * ʵ�����ı��
	 * 
	 */
	public String getCenterNumber() {
		return this.centerNumber;
	}

	/**
	 * ʵ���������
	 * 
	 */
	public void setCenterName(String centerName) {
		this.centerName = centerName;
	}

	/**
	 * ʵ���������
	 * 
	 */
	public String getCenterName() {
		return this.centerName;
	}

	/**
	 * ���ĵ�ַ
	 * 
	 */
	public void setCenterAddress(String centerAddress) {
		this.centerAddress = centerAddress;
	}

	/**
	 * ���ĵ�ַ
	 * 
	 */
	public String getCenterAddress() {
		return this.centerAddress;
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
	 * �Ƿ����
	 * 
	 */
	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	/**
	 * �Ƿ����
	 * 
	 */
	public Integer getEnabled() {
		return this.enabled;
	}
	
	/**
	 */
	public void setUserByCenterManager(User userByCenterManager) {
		this.userByCenterManager = userByCenterManager;
	}
	
	/**
	 */
	@JsonIgnore
	public User getUserByCenterManager() {
		return userByCenterManager;
	}

	/**
	 */
	public void setUserByCreatedBy(User userByCreatedBy) {
		this.userByCreatedBy = userByCreatedBy;
	}

	/**
	 */
	@JsonIgnore
	public User getUserByCreatedBy() {
		return userByCreatedBy;
	}

	/**
	 */
	public void setUserByUpdatedBy(User userByUpdatedBy) {
		this.userByUpdatedBy = userByUpdatedBy;
	}

	/**
	 */
	@JsonIgnore
	public User getUserByUpdatedBy() {
		return userByUpdatedBy;
	}

	/**
	 */
	public void setSystemCampus(SystemCampus systemCampus) {
		this.systemCampus = systemCampus;
	}

	/**
	 */
	@JsonIgnore
	public SystemCampus getSystemCampus() {
		return systemCampus;
	}

	/**
	 */
	public void setSchoolAcademy(SchoolAcademy schoolAcademy) {
		this.schoolAcademy = schoolAcademy;
	}

	/**
	 */
	@JsonIgnore
	public SchoolAcademy getSchoolAcademy() {
		return schoolAcademy;
	}

	/**
	 */
	public void setSystemBuild(SystemBuild systemBuild) {
		this.systemBuild = systemBuild;
	}

	/**
	 */
	@JsonIgnore
	public SystemBuild getSystemBuild() {
		return systemBuild;
	}

	/**
	 */
	public void setLabWorkers(Set<LabWorker> labWorkers) {
		this.labWorkers = labWorkers;
	}

	/**
	 */
	@JsonIgnore
	public Set<LabWorker> getLabWorkers() {
		if (labWorkers == null) {
			labWorkers = new java.util.LinkedHashSet<net.xidlims.domain.LabWorker>();
		}
		return labWorkers;
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
	public void setUsers(Set<User> users) {
		this.users = users;
	}

	/**
	 */
	@JsonIgnore
	public Set<User> getUsers() {
		if (users == null) {
			users = new java.util.LinkedHashSet<net.xidlims.domain.User>();
		}
		return users;
	}
	/**
	 */
	public void setLabAnnexes(Set<LabAnnex> labAnnexes) {
		this.labAnnexes = labAnnexes;
	}

	/**
	 */
	@JsonIgnore
	public Set<LabAnnex> getLabAnnexes() {
		if (labAnnexes == null) {
			labAnnexes = new java.util.LinkedHashSet<net.xidlims.domain.LabAnnex>();
		}
		return labAnnexes;
	}
	
	/**
	 */
	public void setOperationItems(Set<OperationItem> operationItems) {
		this.operationItems = operationItems;
	}

	/**
	 */
	@JsonIgnore
	public Set<OperationItem> getOperationItems() {
		if (operationItems == null) {
			operationItems = new java.util.LinkedHashSet<net.xidlims.domain.OperationItem>();
		}
		return operationItems;
	}
	
	public void setOperationOutlines(Set<OperationOutline> operationOutlines) {
		this.operationOutlines = operationOutlines;
	}

	/**
	 */
	@JsonIgnore
	public Set<OperationOutline> getOperationOutlines() {
		if (operationOutlines == null) {
			operationOutlines = new java.util.LinkedHashSet<net.xidlims.domain.OperationOutline>();
		}
		return operationOutlines;
	}
	
	/**
     */
    public void setInnerAcademyCenters(Set<InnerAcademyCenter> innerAcademyCenters) {
        this.innerAcademyCenters = innerAcademyCenters;
    }

    /**
     */
    @JsonIgnore
    public Set<InnerAcademyCenter> getInnerAcademyCenters() {
        if (innerAcademyCenters == null) {
            innerAcademyCenters = new java.util.LinkedHashSet<net.xidlims.domain.InnerAcademyCenter>();
        }
        return innerAcademyCenters;
    }
	
	/**
	 */
	public LabCenter() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(LabCenter that) {
		setId(that.getId());
		setCenterNumber(that.getCenterNumber());
		setCenterName(that.getCenterName());
		setCenterAddress(that.getCenterAddress());
		setCreatedAt(that.getCreatedAt());
		setUpdatedAt(that.getUpdatedAt());
		setEnabled(that.getEnabled());
		setSystemCampus(that.getSystemCampus());
		setSchoolAcademy(that.getSchoolAcademy());
		setSystemBuild(that.getSystemBuild());
		setLabWorkers(new java.util.LinkedHashSet<net.xidlims.domain.LabWorker>(that.getLabWorkers()));
		setLabRooms(new java.util.LinkedHashSet<net.xidlims.domain.LabRoom>(that.getLabRooms()));
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("id=[").append(id).append("] ");
		buffer.append("centerNumber=[").append(centerNumber).append("] ");
		buffer.append("centerName=[").append(centerName).append("] ");
		buffer.append("centerAddress=[").append(centerAddress).append("] ");
		buffer.append("createdAt=[").append(createdAt).append("] ");
		buffer.append("updatedAt=[").append(updatedAt).append("] ");
		buffer.append("enabled=[").append(enabled).append("] ");

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
		if (!(obj instanceof LabCenter))
			return false;
		LabCenter equalCheck = (LabCenter) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
