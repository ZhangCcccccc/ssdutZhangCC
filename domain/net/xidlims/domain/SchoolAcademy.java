package net.xidlims.domain;

import java.io.Serializable;

import java.lang.StringBuilder;

import java.util.Calendar;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.codehaus.jackson.annotate.JsonIgnore;

import javax.xml.bind.annotation.*;

import javax.persistence.*;

/**
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "findAllSchoolAcademys", query = "select mySchoolAcademy from SchoolAcademy mySchoolAcademy"),
		@NamedQuery(name = "findSchoolAcademyByAcademyName", query = "select mySchoolAcademy from SchoolAcademy mySchoolAcademy where mySchoolAcademy.academyName = ?1"),
		@NamedQuery(name = "findSchoolAcademyByAcademyNameContaining", query = "select mySchoolAcademy from SchoolAcademy mySchoolAcademy where mySchoolAcademy.academyName like ?1"),
		@NamedQuery(name = "findSchoolAcademyByAcademyNumber", query = "select mySchoolAcademy from SchoolAcademy mySchoolAcademy where mySchoolAcademy.academyNumber = ?1"),
		@NamedQuery(name = "findSchoolAcademyByAcademyNumberContaining", query = "select mySchoolAcademy from SchoolAcademy mySchoolAcademy where mySchoolAcademy.academyNumber like ?1"),
		@NamedQuery(name = "findSchoolAcademyByAcademyType", query = "select mySchoolAcademy from SchoolAcademy mySchoolAcademy where mySchoolAcademy.academyType = ?1"),
		@NamedQuery(name = "findSchoolAcademyByAcademyTypeContaining", query = "select mySchoolAcademy from SchoolAcademy mySchoolAcademy where mySchoolAcademy.academyType like ?1"),
		@NamedQuery(name = "findSchoolAcademyByCreatedAt", query = "select mySchoolAcademy from SchoolAcademy mySchoolAcademy where mySchoolAcademy.createdAt = ?1"),
		@NamedQuery(name = "findSchoolAcademyByIsVaild", query = "select mySchoolAcademy from SchoolAcademy mySchoolAcademy where mySchoolAcademy.isVaild = ?1"),
		@NamedQuery(name = "findSchoolAcademyByPrimaryKey", query = "select mySchoolAcademy from SchoolAcademy mySchoolAcademy where mySchoolAcademy.academyNumber = ?1"),
		@NamedQuery(name = "findSchoolAcademyByUpdatedAt", query = "select mySchoolAcademy from SchoolAcademy mySchoolAcademy where mySchoolAcademy.updatedAt = ?1") })
@Table(catalog = "xidlims", name = "school_academy")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "SchoolAcademy")
@XmlRootElement(namespace = "xidlims/net/xidlims/domain")
public class SchoolAcademy implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ѧԺ����
	 * 
	 */

	@Column(name = "academy_number", length = 40, nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	String academyNumber;
	/**
	 * ѧԺ���
	 * 
	 */

	@Column(name = "academy_name", length = 150)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String academyName;
	/**
	 */

	@Column(name = "is_vaild")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Boolean isVaild;
	/**
	 * ѧԺ����
	 * 
	 */

	@Column(name = "academy_type", length = 40)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String academyType;
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
	 */
	@OneToMany(mappedBy = "schoolAcademy", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.SchoolCourseDetail> schoolCourseDetails;
	/**
	 */
	@OneToMany(mappedBy = "schoolAcademy", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.SchoolCourseStudent> schoolCourseStudents;
	/**
	 */
	@OneToMany(mappedBy = "schoolAcademy", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.LabCenter> labCenters;
	/**
	 */
	@OneToMany(mappedBy = "schoolAcademy", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.SchoolDevice> schoolDevices;
	/**
	 */
	@OneToMany(mappedBy = "schoolAcademy", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.User> users;
	/**
	 */
	
	/**
	 */
	@OneToMany(mappedBy = "schoolAcademy", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.AssetAppRecord> assetAppRecords;
	
	@OneToMany(mappedBy = "schoolAcademy", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.TimetableSelfCourse> timetableSelfCourses;
	/**
	 */
	@OneToMany(mappedBy = "schoolAcademy", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.SchoolCourse> schoolCourses;

	//新增
	/**
	 */
	@OneToMany(mappedBy = "schoolAcademy", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.LabConstructionProject> labConstructionProjects;
	/**
	 */
	@OneToMany(mappedBy = "schoolAcademy", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.LabConstructionPurchase> labConstructionPurchases;

	/**
	 */
	@OneToMany(mappedBy = "schoolAcademy", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.OperationOutline> operationOutlines;
	
	/**
	 */
	@OneToMany(mappedBy = "schoolAcademy", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.SchoolMajor> schoolMajors;
	
	/**
	 */
	@ManyToMany(mappedBy = "schoolAcademies", fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.OperationOutline> operationOutlinees;
	
	/**
	 */
	public void setSchoolMajors(Set<SchoolMajor> schoolMajors) {
		this.schoolMajors = schoolMajors;
	}

	/**
	 */
	@JsonIgnore
	public Set<SchoolMajor> getSchoolMajors() {
		if (schoolMajors == null) {
			schoolMajors = new java.util.LinkedHashSet<net.xidlims.domain.SchoolMajor>();
		}
		return schoolMajors;
	}
	
	/**
	 */
	public void setOperationOutlinees(Set<OperationOutline> operationOutlinees) {
		this.operationOutlinees = operationOutlinees;
	}

	/**
	 */
	@JsonIgnore
	public Set<OperationOutline> getOperationOutlinees() {
		if (operationOutlinees == null) {
			operationOutlinees = new java.util.LinkedHashSet<net.xidlims.domain.OperationOutline>();
		}
		return operationOutlinees;
	}
	

	/**
	 */
	@OneToMany(mappedBy = "schoolAcademyByCollege", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.OperationItem> operationItemsForCollege;
	
	/**
     */
    @OneToMany(mappedBy = "schoolAcademy", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
    @XmlElement(name = "", namespace = "")
    java.util.Set<net.xidlims.domain.InnerAcademyCenter> innerAcademyCenters;
	
	/**
	 */
	public void setOperationItemsForCollege(Set<OperationItem> operationItemsForCollege) {
		this.operationItemsForCollege = operationItemsForCollege;
	}

	/**
	 */
	@JsonIgnore
	public Set<OperationItem> getOperationItemsForCollege() {
		if (operationItemsForCollege == null) {
			operationItemsForCollege = new java.util.LinkedHashSet<net.xidlims.domain.OperationItem>();
		}
		return operationItemsForCollege;
	}
	/**
	 * ѧԺ����
	 * 
	 */
	public void setAcademyNumber(String academyNumber) {
		this.academyNumber = academyNumber;
	}

	/**
	 * ѧԺ����
	 * 
	 */
	public String getAcademyNumber() {
		return this.academyNumber;
	}

	/**
	 * ѧԺ���
	 * 
	 */
	public void setAcademyName(String academyName) {
		this.academyName = academyName;
	}

	/**
	 * ѧԺ���
	 * 
	 */
	public String getAcademyName() {
		return this.academyName;
	}

	/**
	 */
	public void setIsVaild(Boolean isVaild) {
		this.isVaild = isVaild;
	}

	/**
	 */
	public Boolean getIsVaild() {
		return this.isVaild;
	}

	/**
	 * ѧԺ����
	 * 
	 */
	public void setAcademyType(String academyType) {
		this.academyType = academyType;
	}

	/**
	 * ѧԺ����
	 * 
	 */
	public String getAcademyType() {
		return this.academyType;
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
	 */
	public void setSchoolCourseDetails(Set<SchoolCourseDetail> schoolCourseDetails) {
		this.schoolCourseDetails = schoolCourseDetails;
	}

	/**
	 */
	@JsonIgnore
	public Set<SchoolCourseDetail> getSchoolCourseDetails() {
		if (schoolCourseDetails == null) {
			schoolCourseDetails = new java.util.LinkedHashSet<net.xidlims.domain.SchoolCourseDetail>();
		}
		return schoolCourseDetails;
	}

	/**
	 */
	public void setSchoolCourseStudents(Set<SchoolCourseStudent> schoolCourseStudents) {
		this.schoolCourseStudents = schoolCourseStudents;
	}

	/**
	 */
	@JsonIgnore
	public Set<SchoolCourseStudent> getSchoolCourseStudents() {
		if (schoolCourseStudents == null) {
			schoolCourseStudents = new java.util.LinkedHashSet<net.xidlims.domain.SchoolCourseStudent>();
		}
		return schoolCourseStudents;
	}

	/**
	 */
	public void setLabCenters(Set<LabCenter> labCenters) {
		this.labCenters = labCenters;
	}

	/**
	 */
	@JsonIgnore
	public Set<LabCenter> getLabCenters() {
		if (labCenters == null) {
			labCenters = new java.util.LinkedHashSet<net.xidlims.domain.LabCenter>();
		}
		return labCenters;
	}

	/**
	 */
	public void setSchoolDevices(Set<SchoolDevice> schoolDevices) {
		this.schoolDevices = schoolDevices;
	}

	/**
	 */
	@JsonIgnore
	public Set<SchoolDevice> getSchoolDevices() {
		if (schoolDevices == null) {
			schoolDevices = new java.util.LinkedHashSet<net.xidlims.domain.SchoolDevice>();
		}
		return schoolDevices;
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
	public void setTimetableSelfCourses(Set<TimetableSelfCourse> timetableSelfCourses) {
		this.timetableSelfCourses = timetableSelfCourses;
	}

	/**
	 */
	@JsonIgnore
	public Set<TimetableSelfCourse> getTimetableSelfCourses() {
		if (timetableSelfCourses == null) {
			timetableSelfCourses = new java.util.LinkedHashSet<net.xidlims.domain.TimetableSelfCourse>();
		}
		return timetableSelfCourses;
	}

	/**
	 */
	public void setSchoolCourses(Set<SchoolCourse> schoolCourses) {
		this.schoolCourses = schoolCourses;
	}

	/**
	 */
	@JsonIgnore
	public Set<SchoolCourse> getSchoolCourses() {
		if (schoolCourses == null) {
			schoolCourses = new java.util.LinkedHashSet<net.xidlims.domain.SchoolCourse>();
		}
		return schoolCourses;
	}
	
	//新增
	/**
	 */
	public void setLabConstructionProjects(Set<LabConstructionProject> labConstructionProjects) {
		this.labConstructionProjects = labConstructionProjects;
	}

	/**
	 */
	@JsonIgnore
	public Set<LabConstructionProject> getLabConstructionProjects() {
		if (labConstructionProjects == null) {
			labConstructionProjects = new java.util.LinkedHashSet<net.xidlims.domain.LabConstructionProject>();
		}
		return labConstructionProjects;
	}

	/**
	 */
	public void setLabConstructionPurchases(Set<LabConstructionPurchase> labConstructionPurchases) {
		this.labConstructionPurchases = labConstructionPurchases;
	}

	/**
	 */
	@JsonIgnore
	public Set<LabConstructionPurchase> getLabConstructionPurchases() {
		if (labConstructionPurchases == null) {
			labConstructionPurchases = new java.util.LinkedHashSet<net.xidlims.domain.LabConstructionPurchase>();
		}
		return labConstructionPurchases;
	}
	/**
	 */
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
	public void setAssetAppRecords(Set<AssetAppRecord> assetAppRecords) {
		this.assetAppRecords = assetAppRecords;
	}

	/**
	 */
	@JsonIgnore
	public Set<AssetAppRecord> getAssetAppRecords() {
		if (assetAppRecords == null) {
			assetAppRecords = new java.util.LinkedHashSet<net.xidlims.domain.AssetAppRecord>();
		}
		return assetAppRecords;
	}

	/**
	 */

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
	public SchoolAcademy() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(SchoolAcademy that) {
		setAcademyNumber(that.getAcademyNumber());
		setAcademyName(that.getAcademyName());
		setIsVaild(that.getIsVaild());
		setAcademyType(that.getAcademyType());
		setCreatedAt(that.getCreatedAt());
		setUpdatedAt(that.getUpdatedAt());
		setSchoolCourseDetails(new java.util.LinkedHashSet<net.xidlims.domain.SchoolCourseDetail>(that.getSchoolCourseDetails()));
		setSchoolCourseStudents(new java.util.LinkedHashSet<net.xidlims.domain.SchoolCourseStudent>(that.getSchoolCourseStudents()));
		setLabCenters(new java.util.LinkedHashSet<net.xidlims.domain.LabCenter>(that.getLabCenters()));
		setSchoolDevices(new java.util.LinkedHashSet<net.xidlims.domain.SchoolDevice>(that.getSchoolDevices()));
		setUsers(new java.util.LinkedHashSet<net.xidlims.domain.User>(that.getUsers()));
		setTimetableSelfCourses(new java.util.LinkedHashSet<net.xidlims.domain.TimetableSelfCourse>(that.getTimetableSelfCourses()));
		setSchoolCourses(new java.util.LinkedHashSet<net.xidlims.domain.SchoolCourse>(that.getSchoolCourses()));
		//新增
		setOperationOutlines(new java.util.LinkedHashSet<net.xidlims.domain.OperationOutline>(that.getOperationOutlines()));
		setLabConstructionProjects(new java.util.LinkedHashSet<net.xidlims.domain.LabConstructionProject>(that.getLabConstructionProjects()));
		setLabConstructionPurchases(new java.util.LinkedHashSet<net.xidlims.domain.LabConstructionPurchase>(that.getLabConstructionPurchases()));
		
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("academyNumber=[").append(academyNumber).append("] ");
		buffer.append("academyName=[").append(academyName).append("] ");
		buffer.append("isVaild=[").append(isVaild).append("] ");
		buffer.append("academyType=[").append(academyType).append("] ");
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
		result = (int) (prime * result + ((academyNumber == null) ? 0 : academyNumber.hashCode()));
		return result;
	}

	/**
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof SchoolAcademy))
			return false;
		SchoolAcademy equalCheck = (SchoolAcademy) obj;
		if ((academyNumber == null && equalCheck.academyNumber != null) || (academyNumber != null && equalCheck.academyNumber == null))
			return false;
		if (academyNumber != null && !academyNumber.equals(equalCheck.academyNumber))
			return false;
		return true;
	}
}
