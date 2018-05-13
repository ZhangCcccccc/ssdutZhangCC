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
		@NamedQuery(name = "findAllSchoolCourseStudents", query = "select mySchoolCourseStudent from SchoolCourseStudent mySchoolCourseStudent"),
		@NamedQuery(name = "findSchoolCourseStudentByClassesStudents", query = "select mySchoolCourseStudent from SchoolCourseStudent mySchoolCourseStudent where mySchoolCourseStudent.classesStudents = ?1"),
		@NamedQuery(name = "findSchoolCourseStudentByClassesStudentsContaining", query = "select mySchoolCourseStudent from SchoolCourseStudent mySchoolCourseStudent where mySchoolCourseStudent.classesStudents like ?1"),
		@NamedQuery(name = "findSchoolCourseStudentByCreatedDate", query = "select mySchoolCourseStudent from SchoolCourseStudent mySchoolCourseStudent where mySchoolCourseStudent.createdDate = ?1"),
		@NamedQuery(name = "findSchoolCourseStudentById", query = "select mySchoolCourseStudent from SchoolCourseStudent mySchoolCourseStudent where mySchoolCourseStudent.id = ?1"),
		@NamedQuery(name = "findSchoolCourseStudentByMajorDirectionName", query = "select mySchoolCourseStudent from SchoolCourseStudent mySchoolCourseStudent where mySchoolCourseStudent.majorDirectionName = ?1"),
		@NamedQuery(name = "findSchoolCourseStudentByMajorDirectionNameContaining", query = "select mySchoolCourseStudent from SchoolCourseStudent mySchoolCourseStudent where mySchoolCourseStudent.majorDirectionName like ?1"),
		@NamedQuery(name = "findSchoolCourseStudentByMajorName", query = "select mySchoolCourseStudent from SchoolCourseStudent mySchoolCourseStudent where mySchoolCourseStudent.majorName = ?1"),
		@NamedQuery(name = "findSchoolCourseStudentByMajorNameContaining", query = "select mySchoolCourseStudent from SchoolCourseStudent mySchoolCourseStudent where mySchoolCourseStudent.majorName like ?1"),
		@NamedQuery(name = "findSchoolCourseStudentByMemo", query = "select mySchoolCourseStudent from SchoolCourseStudent mySchoolCourseStudent where mySchoolCourseStudent.memo = ?1"),
		@NamedQuery(name = "findSchoolCourseStudentByMemoContaining", query = "select mySchoolCourseStudent from SchoolCourseStudent mySchoolCourseStudent where mySchoolCourseStudent.memo like ?1"),
		@NamedQuery(name = "findSchoolCourseStudentByPrimaryKey", query = "select mySchoolCourseStudent from SchoolCourseStudent mySchoolCourseStudent where mySchoolCourseStudent.id = ?1"),
		@NamedQuery(name = "findSchoolCourseStudentByState", query = "select mySchoolCourseStudent from SchoolCourseStudent mySchoolCourseStudent where mySchoolCourseStudent.state = ?1"),
		@NamedQuery(name = "findSchoolCourseStudentByTestNum", query = "select mySchoolCourseStudent from SchoolCourseStudent mySchoolCourseStudent where mySchoolCourseStudent.testNum = ?1"),
		@NamedQuery(name = "findSchoolCourseStudentByUpdatedDate", query = "select mySchoolCourseStudent from SchoolCourseStudent mySchoolCourseStudent where mySchoolCourseStudent.updatedDate = ?1") })
@Table(catalog = "xidlims", name = "school_course_student")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "SchoolCourseStudent")
public class SchoolCourseStudent implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * �γ���Ϣ������
	 * 
	 */

	@Column(name = "id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@XmlElement
	Integer id;
	/**
	 * ��ע
	 * 
	 */

	@Column(name = "memo", length = 40)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String memo;
	/**
	 * ��������
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar createdDate;
	/**
	 * ��������
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_date")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar updatedDate;
	/**
	 * �༶����
	 * 
	 */

	@Column(name = "classes_students", length = 40)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String classesStudents;
	/**
	 * רҵ���
	 * 
	 */

	@Column(name = "major_name", length = 100)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String majorName;
	/**
	 * רҵ�������
	 * 
	 */

	@Column(name = "major_direction_name", length = 100)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String majorDirectionName;
	/**
	 * ��Ʒ�ò����õ��ֶ�
	 * 
	 */

	@Column(name = "test_num")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer testNum;
	/**
	 * ״̬
	 * 
	 */

	@Column(name = "state")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer state;
	
	@Column(name = "is_select")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer isSelect;

	public Integer getIsSelect() {
		return isSelect;
	}

	public void setIsSelect(Integer isSelect) {
		this.isSelect = isSelect;
	}

	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "teacher_number", referencedColumnName = "username") })
	@XmlTransient
	User userByTeacherNumber;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "term_id", referencedColumnName = "id") })
	@XmlTransient
	SchoolTerm schoolTerm;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "course_detail_no", referencedColumnName = "course_detail_no", nullable = false) })
	@XmlTransient
	SchoolCourseDetail schoolCourseDetail;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "classes_number", referencedColumnName = "class_number") })
	@XmlTransient
	SchoolClasses schoolClasses;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "student_number", referencedColumnName = "username", nullable = false) })
	@XmlTransient
	User userByStudentNumber;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "academy_number", referencedColumnName = "academy_number") })
	@XmlTransient
	SchoolAcademy schoolAcademy;

	/**
	 * �γ���Ϣ������
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * �γ���Ϣ������
	 * 
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * ��ע
	 * 
	 */
	public void setMemo(String memo) {
		this.memo = memo;
	}

	/**
	 * ��ע
	 * 
	 */
	public String getMemo() {
		return this.memo;
	}

	/**
	 * ��������
	 * 
	 */
	public void setCreatedDate(Calendar createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * ��������
	 * 
	 */
	public Calendar getCreatedDate() {
		return this.createdDate;
	}

	/**
	 * ��������
	 * 
	 */
	public void setUpdatedDate(Calendar updatedDate) {
		this.updatedDate = updatedDate;
	}

	/**
	 * ��������
	 * 
	 */
	public Calendar getUpdatedDate() {
		return this.updatedDate;
	}

	/**
	 * �༶����
	 * 
	 */
	public void setClassesStudents(String classesStudents) {
		this.classesStudents = classesStudents;
	}

	/**
	 * �༶����
	 * 
	 */
	public String getClassesStudents() {
		return this.classesStudents;
	}

	/**
	 * רҵ���
	 * 
	 */
	public void setMajorName(String majorName) {
		this.majorName = majorName;
	}

	/**
	 * רҵ���
	 * 
	 */
	public String getMajorName() {
		return this.majorName;
	}

	/**
	 * רҵ�������
	 * 
	 */
	public void setMajorDirectionName(String majorDirectionName) {
		this.majorDirectionName = majorDirectionName;
	}

	/**
	 * רҵ�������
	 * 
	 */
	public String getMajorDirectionName() {
		return this.majorDirectionName;
	}

	/**
	 * ��Ʒ�ò����õ��ֶ�
	 * 
	 */
	public void setTestNum(Integer testNum) {
		this.testNum = testNum;
	}

	/**
	 * ��Ʒ�ò����õ��ֶ�
	 * 
	 */
	public Integer getTestNum() {
		return this.testNum;
	}

	/**
	 * ״̬
	 * 
	 */
	public void setState(Integer state) {
		this.state = state;
	}

	/**
	 * ״̬
	 * 
	 */
	public Integer getState() {
		return this.state;
	}

	/**
	 */
	public void setUserByTeacherNumber(User userByTeacherNumber) {
		this.userByTeacherNumber = userByTeacherNumber;
	}

	/**
	 */
	@JsonIgnore
	public User getUserByTeacherNumber() {
		return userByTeacherNumber;
	}

	/**
	 */
	public void setSchoolTerm(SchoolTerm schoolTerm) {
		this.schoolTerm = schoolTerm;
	}

	/**
	 */
	@JsonIgnore
	public SchoolTerm getSchoolTerm() {
		return schoolTerm;
	}

	/**
	 */
	public void setSchoolCourseDetail(SchoolCourseDetail schoolCourseDetail) {
		this.schoolCourseDetail = schoolCourseDetail;
	}

	/**
	 */
	@JsonIgnore
	public SchoolCourseDetail getSchoolCourseDetail() {
		return schoolCourseDetail;
	}

	/**
	 */
	public void setSchoolClasses(SchoolClasses schoolClasses) {
		this.schoolClasses = schoolClasses;
	}

	/**
	 */
	@JsonIgnore
	public SchoolClasses getSchoolClasses() {
		return schoolClasses;
	}

	/**
	 */
	public void setUserByStudentNumber(User userByStudentNumber) {
		this.userByStudentNumber = userByStudentNumber;
	}

	/**
	 */
	@JsonIgnore
	public User getUserByStudentNumber() {
		return userByStudentNumber;
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
	public SchoolCourseStudent() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(SchoolCourseStudent that) {
		setId(that.getId());
		setMemo(that.getMemo());
		setCreatedDate(that.getCreatedDate());
		setUpdatedDate(that.getUpdatedDate());
		setClassesStudents(that.getClassesStudents());
		setMajorName(that.getMajorName());
		setMajorDirectionName(that.getMajorDirectionName());
		setTestNum(that.getTestNum());
		setState(that.getState());
		setUserByTeacherNumber(that.getUserByTeacherNumber());
		setSchoolTerm(that.getSchoolTerm());
		setSchoolCourseDetail(that.getSchoolCourseDetail());
		setSchoolClasses(that.getSchoolClasses());
		setUserByStudentNumber(that.getUserByStudentNumber());
		setSchoolAcademy(that.getSchoolAcademy());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("id=[").append(id).append("] ");
		buffer.append("memo=[").append(memo).append("] ");
		buffer.append("createdDate=[").append(createdDate).append("] ");
		buffer.append("updatedDate=[").append(updatedDate).append("] ");
		buffer.append("classesStudents=[").append(classesStudents).append("] ");
		buffer.append("majorName=[").append(majorName).append("] ");
		buffer.append("majorDirectionName=[").append(majorDirectionName).append("] ");
		buffer.append("testNum=[").append(testNum).append("] ");
		buffer.append("state=[").append(state).append("] ");

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
		if (!(obj instanceof SchoolCourseStudent))
			return false;
		SchoolCourseStudent equalCheck = (SchoolCourseStudent) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
