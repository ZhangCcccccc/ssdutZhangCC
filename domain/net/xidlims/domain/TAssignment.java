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
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
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
		@NamedQuery(name = "findAllTAssignments", query = "select myTAssignment from TAssignment myTAssignment"),
		@NamedQuery(name = "findTAssignmentByCreatedTime", query = "select myTAssignment from TAssignment myTAssignment where myTAssignment.createdTime = ?1"),
		@NamedQuery(name = "findTAssignmentByDescription", query = "select myTAssignment from TAssignment myTAssignment where myTAssignment.description = ?1"),
		@NamedQuery(name = "findTAssignmentByDescriptionContaining", query = "select myTAssignment from TAssignment myTAssignment where myTAssignment.description like ?1"),
		@NamedQuery(name = "findTAssignmentById", query = "select myTAssignment from TAssignment myTAssignment where myTAssignment.id = ?1"),
		@NamedQuery(name = "findTAssignmentByPrimaryKey", query = "select myTAssignment from TAssignment myTAssignment where myTAssignment.id = ?1"),
		@NamedQuery(name = "findTAssignmentByStatus", query = "select myTAssignment from TAssignment myTAssignment where myTAssignment.status = ?1"),
		@NamedQuery(name = "findTAssignmentByTitle", query = "select myTAssignment from TAssignment myTAssignment where myTAssignment.title = ?1"),
		@NamedQuery(name = "findTAssignmentByTitleContaining", query = "select myTAssignment from TAssignment myTAssignment where myTAssignment.title like ?1") })
@Table(catalog = "xidlims", name = "t_assignment")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "TAssignment")
@XmlRootElement(namespace = "xidlims/net/xidlims/domain")
public class TAssignment implements Serializable {
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
	 * ���Ի���ҵ���
	 * 
	 */

	@Column(name = "title", length = 50)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String title;
	/**
	 * ���
	 * 
	 */

	@Column(name = "description")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String description;
	/**
	 * ����ʱ��
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_time")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar createdTime;
	/**
	 * ״̬ 1���ѷ�����0��δ����
	 * 
	 */

	@Column(name = "status")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer status;
	
	@Column(name = "site_id")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer siteId;
	

	public Integer getSiteId() {
		return siteId;
	}

	public void setSiteId(Integer siteId) {
		this.siteId = siteId;
	}

	@Column(name = "type")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String type;
	
	@Column(name = "content", columnDefinition = "TEXT")
	@Basic(fetch = FetchType.EAGER)
	@Lob
	@XmlElement
	String content;

	@Column(name = "sequence")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer sequence;
	
	@Column(name = "testParentId")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer testParentId;

	@Column(name = "teacherFilePath")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String teacherFilePath;
	
	@Column(name = "isSubmit")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer isSubmit;
	
	@Column(name = "is_group")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer isGroup;
	
	public Integer getIsGroup() {
		return isGroup;
	}

	public void setIsGroup(Integer isGroup) {
		this.isGroup = isGroup;
	}

	/**
	 */
	@OneToMany(mappedBy = "TAssignment", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.TGradeObject> TGradeObjects;

	/**
	 */
	@OneToMany(mappedBy = "TAssignment", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.TAssignmentItemMapping> TAssignmentItemMappings;

	/**
	 */
	public void setTAssignmentItemMappings(Set<TAssignmentItemMapping> TAssignmentItemMappings) {
		this.TAssignmentItemMappings = TAssignmentItemMappings;
	}

	/**
	 */
	public Set<TAssignmentItemMapping> getTAssignmentItemMappings() {
		if (TAssignmentItemMappings == null) {
			TAssignmentItemMappings = new java.util.LinkedHashSet<net.xidlims.domain.TAssignmentItemMapping>();
		}
		return TAssignmentItemMappings;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "folder_id", referencedColumnName = "id") })
	@XmlTransient
	WkFolder wkFolder;
	
	
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "created_by", referencedColumnName = "username") })
	@XmlTransient
	User user;
	/**
	 */
	@OneToMany(mappedBy = "TAssignment", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.TAssignmentGrading> TAssignmentGradings;
	/**
	 */
	@OneToOne(mappedBy = "TAssignment", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	TAssignmentControl  TAssignmentControl;

	
	
	/**
	 */
	@OneToMany(mappedBy = "TAssignment", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	@OrderBy(value="sequence ASC")
	java.util.Set<net.xidlims.domain.TAssignmentSection> TAssignmentSections;

	@OneToOne(mappedBy = "TAssignment", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	TAssignmentAnswerAssign TAssignmentAnswerAssign;
	
	/**
	 */
	@OneToMany(mappedBy = "TAssignment", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.TAssignmentItemComponent> TAssignmentItemComponents;
	
	/**
	 */
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(catalog = "xidlims", name = "t_assignment_question", joinColumns = { @JoinColumn(name = "t_assignment_id", referencedColumnName = "id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "t_question_id", referencedColumnName = "questionpool_id", nullable = false, updatable = false) })
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.TAssignmentQuestionpool> TAssignmentQuestionpools;

	public TAssignmentAnswerAssign getTAssignmentAnswerAssign() {
		return TAssignmentAnswerAssign;
	}

	public void setTAssignmentAnswerAssign(TAssignmentAnswerAssign tAssignmentAnswerAssign) {
		TAssignmentAnswerAssign = tAssignmentAnswerAssign;
	}

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
	 * ���Ի���ҵ���
	 * 
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * ���Ի���ҵ���
	 * 
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * ���
	 * 
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * ���
	 * 
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * ����ʱ��
	 * 
	 */
	public void setCreatedTime(Calendar createdTime) {
		this.createdTime = createdTime;
	}

	/**
	 * ����ʱ��
	 * 
	 */
	public Calendar getCreatedTime() {
		return this.createdTime;
	}

	/**
	 * ״̬ 1���ѷ�����0��δ����
	 * 
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * ״̬ 1���ѷ�����0��δ����
	 * 
	 */
	public Integer getStatus() {
		return this.status;
	}


	public WkFolder getWkFolder() {
		return wkFolder;
	}

	public void setWkFolder(WkFolder wkFolder) {
		this.wkFolder = wkFolder;
	}

	/**
	 */
	public void setUser(User user) {
		this.user = user;
	}

	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * ��������
	 * 
	 */
	public String getContent() {
		return this.content;
	}
	
	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}
 
	public Integer getTestParentId() {
		return testParentId;
	}

	public void setTestParentId(Integer testParentId) {
		this.testParentId = testParentId;
	}

	public String getTeacherFilePath() {
		return teacherFilePath;
	}

	public void setTeacherFilePath(String teacherFilePath) {
		this.teacherFilePath = teacherFilePath;
	}

	public Integer getIsSubmit() {
		return isSubmit;
	}

	public void setIsSubmit(Integer isSubmit) {
		this.isSubmit = isSubmit;
	}

	/**
	 */
	public User getUser() {
		return user;
	}

	/**
	 */
	public void setTAssignmentGradings(Set<TAssignmentGrading> TAssignmentGradings) {
		this.TAssignmentGradings = TAssignmentGradings;
	}

	/**
	 */
	public Set<TAssignmentGrading> getTAssignmentGradings() {
		if (TAssignmentGradings == null) {
			TAssignmentGradings = new java.util.LinkedHashSet<net.xidlims.domain.TAssignmentGrading>();
		}
		return TAssignmentGradings;
	}

	
	public TAssignmentControl getTAssignmentControl() {
		return TAssignmentControl;
	}

	public void setTAssignmentControl(TAssignmentControl tAssignmentControl) {
		TAssignmentControl = tAssignmentControl;
	}

	/**
	 */
	public void setTAssignmentSections(Set<TAssignmentSection> TAssignmentSections) {
		this.TAssignmentSections = TAssignmentSections;
	}

	/**
	 */
	public Set<TAssignmentSection> getTAssignmentSections() {
		if (TAssignmentSections == null) {
			TAssignmentSections = new java.util.LinkedHashSet<net.xidlims.domain.TAssignmentSection>();
		}
		return TAssignmentSections;
	}

	/**
	 */
	public void setTGradeObjects(Set<TGradeObject> TGradeObjects) {
		this.TGradeObjects = TGradeObjects;
	}

	/**
	 */
	public Set<TGradeObject> getTGradeObjects() {
		if (TGradeObjects == null) {
			TGradeObjects = new java.util.LinkedHashSet<net.xidlims.domain.TGradeObject>();
		}
		return TGradeObjects;
	}
	/**
	 */
	public void setTAssignmentItemComponents(Set<TAssignmentItemComponent> TAssignmentItemComponents) {
		this.TAssignmentItemComponents = TAssignmentItemComponents;
	}

	/**
	 */
	@JsonIgnore
	public Set<TAssignmentItemComponent> getTAssignmentItemComponents() {
		if (TAssignmentItemComponents == null) {
			TAssignmentItemComponents = new java.util.LinkedHashSet<net.xidlims.domain.TAssignmentItemComponent>();
		}
		return TAssignmentItemComponents;
	}
	
	/**
	 */
	public void setTAssignmentQuestionpools(Set<TAssignmentQuestionpool> TAssignmentQuestionpools) {
		this.TAssignmentQuestionpools = TAssignmentQuestionpools;
	}

	/**
	 */
	public Set<TAssignmentQuestionpool> getTAssignmentQuestionpools() {
		if (TAssignmentQuestionpools == null) {
			TAssignmentQuestionpools = new java.util.LinkedHashSet<net.xidlims.domain.TAssignmentQuestionpool>();
		}
		return TAssignmentQuestionpools;
	}

	/**
	 */
	public TAssignment() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(TAssignment that) {
		setId(that.getId());
		setTitle(that.getTitle());
		setDescription(that.getDescription());
		setCreatedTime(that.getCreatedTime());
		setStatus(that.getStatus());
		setWkFolder(that.getWkFolder());
		setUser(that.getUser());
		setTAssignmentGradings(new java.util.LinkedHashSet<net.xidlims.domain.TAssignmentGrading>(that.getTAssignmentGradings()));
		//setTAssignmentControl(that.getTAssignmentControl());
		setTAssignmentSections(new java.util.LinkedHashSet<net.xidlims.domain.TAssignmentSection>(that.getTAssignmentSections()));
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("id=[").append(id).append("] ");
		buffer.append("title=[").append(title).append("] ");
		buffer.append("description=[").append(description).append("] ");
		buffer.append("createdTime=[").append(createdTime).append("] ");
		buffer.append("status=[").append(status).append("] ");

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
		if (!(obj instanceof TAssignment))
			return false;
		TAssignment equalCheck = (TAssignment) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
