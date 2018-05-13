package net.xidlims.domain;

import java.io.Serializable;

import java.lang.StringBuilder;



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
		@NamedQuery(name = "findAllTimetableCourseStudents", query = "select myTimetableCourseStudent from TimetableCourseStudent myTimetableCourseStudent"),
		@NamedQuery(name = "findTimetableCourseStudentById", query = "select myTimetableCourseStudent from TimetableCourseStudent myTimetableCourseStudent where myTimetableCourseStudent.id = ?1"),
		@NamedQuery(name = "findTimetableCourseStudentByPrimaryKey", query = "select myTimetableCourseStudent from TimetableCourseStudent myTimetableCourseStudent where myTimetableCourseStudent.id = ?1") })
@Table(catalog = "xidlims", name = "timetable_course_student")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "TimetableCourseStudent")
public class TimetableCourseStudent implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * �����ſε�ѧ��ѡ�ΰ���
	 * 
	 */

	@Column(name = "id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	@XmlElement
	Integer id;

	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "course_code_id", referencedColumnName = "id") })
	@XmlTransient
	TimetableSelfCourse timetableSelfCourse;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "student_number", referencedColumnName = "username") })
	@XmlTransient
	User user;

	/**
	 * �����ſε�ѧ��ѡ�ΰ���
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * �����ſε�ѧ��ѡ�ΰ���
	 * 
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 */
	public void setTimetableSelfCourse(TimetableSelfCourse timetableSelfCourse) {
		this.timetableSelfCourse = timetableSelfCourse;
	}

	/**
	 */
	@JsonIgnore
	public TimetableSelfCourse getTimetableSelfCourse() {
		return timetableSelfCourse;
	}

	/**
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 */
	@JsonIgnore
	public User getUser() {
		return user;
	}

	/**
	 */
	public TimetableCourseStudent() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(TimetableCourseStudent that) {
		setId(that.getId());
		setTimetableSelfCourse(that.getTimetableSelfCourse());
		setUser(that.getUser());
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
		if (!(obj instanceof TimetableCourseStudent))
			return false;
		TimetableCourseStudent equalCheck = (TimetableCourseStudent) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
