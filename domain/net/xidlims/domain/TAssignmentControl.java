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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "findAllTAssignmentControls", query = "select myTAssignmentControl from TAssignmentControl myTAssignmentControl"),
		@NamedQuery(name = "findTAssignmentControlById", query = "select myTAssignmentControl from TAssignmentControl myTAssignmentControl where myTAssignmentControl.id = ?1"),
		@NamedQuery(name = "findTAssignmentControlByDuedate", query = "select myTAssignmentControl from TAssignmentControl myTAssignmentControl where myTAssignmentControl.duedate = ?1"),
		@NamedQuery(name = "findTAssignmentControlByPrimaryKey", query = "select myTAssignmentControl from TAssignmentControl myTAssignmentControl where myTAssignmentControl.id = ?1"),
		@NamedQuery(name = "findTAssignmentControlByStartdate", query = "select myTAssignmentControl from TAssignmentControl myTAssignmentControl where myTAssignmentControl.startdate = ?1"),
		@NamedQuery(name = "findTAssignmentControlBySubmessage", query = "select myTAssignmentControl from TAssignmentControl myTAssignmentControl where myTAssignmentControl.submessage = ?1"),
		@NamedQuery(name = "findTAssignmentControlBySubmessageContaining", query = "select myTAssignmentControl from TAssignmentControl myTAssignmentControl where myTAssignmentControl.submessage like ?1"),
		@NamedQuery(name = "findTAssignmentControlByTimelimit", query = "select myTAssignmentControl from TAssignmentControl myTAssignmentControl where myTAssignmentControl.timelimit = ?1") })
@Table(catalog = "xidlims", name = "t_assignment_control")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "TAssignmentControl")
public class TAssignmentControl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	@XmlElement
	Integer id;
	

		
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * ����ʱ�����ƣ�0������
	 * 
	 */

	@Column(name = "timelimit")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer timelimit;
	/**
	 * ���Կ�ʼʱ��
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "startdate")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar startdate;
	/**
	 * ����ʱ��
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "duedate")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar duedate;
	/**
	 * �ύ��Ϣ
	 * 
	 */

	@Column(name = "submessage")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String submessage;
	

	@Column(name = "to_gradebook")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String toGradebook;

	@Column(name = "submit_type")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer submitType;
	
	@Column(name = "grade_to_student")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String gradeToStudent;
	
	@Column(name = "grade_to_totalGrade")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String gradeToTotalGrade;

	public Integer getSubmitType() {
		return submitType;
	}

	public void setSubmitType(Integer submitType) {
		this.submitType = submitType;
	}

	public String getGradeToStudent() {
		return gradeToStudent;
	}

	public void setGradeToStudent(String gradeToStudent) {
		this.gradeToStudent = gradeToStudent;
	}

	public String getGradeToTotalGrade() {
		return gradeToTotalGrade;
	}

	public void setGradeToTotalGrade(String gradeToTotalGrade) {
		this.gradeToTotalGrade = gradeToTotalGrade;
	}

	public String getToGradebook() {
		return toGradebook;
	}

	public void setToGradebook(String toGradebook) {
		this.toGradebook = toGradebook;
	}

	@OneToOne(fetch = FetchType.EAGER)
    @JoinColumns({ @JoinColumn(name = "assignment_id", referencedColumnName = "id") })
	@XmlElement(name = "", namespace = "")
	TAssignment TAssignment;
	/**
	 * ����ʱ�����ƣ�0������
	 * 
	 */
	public void setTimelimit(Integer timelimit) {
		this.timelimit = timelimit;
	}

	/**
	 * ����ʱ�����ƣ�0������
	 * 
	 */
	public Integer getTimelimit() {
		return this.timelimit;
	}

	/**
	 * ���Կ�ʼʱ��
	 * 
	 */
	public void setStartdate(Calendar startdate) {
		this.startdate = startdate;
	}

	/**
	 * ���Կ�ʼʱ��
	 * 
	 */
	public Calendar getStartdate() {
		return this.startdate;
	}

	/**
	 * ����ʱ��
	 * 
	 */
	public void setDuedate(Calendar duedate) {
		this.duedate = duedate;
	}

	/**
	 * ����ʱ��
	 * 
	 */
	public Calendar getDuedate() {
		return this.duedate;
	}

	/**
	 * �ύ��Ϣ
	 * 
	 */
	public void setSubmessage(String submessage) {
		this.submessage = submessage;
	}

	/**
	 * �ύ��Ϣ
	 * 
	 */
	public String getSubmessage() {
		return this.submessage;
	}

	/**
	 */
	public void setTAssignment(TAssignment TAssignment) {
		this.TAssignment = TAssignment;
	}

	/**
	 */
	public TAssignment getTAssignment() {
		return TAssignment;
	}

	/**
	 */
	public TAssignmentControl() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(TAssignmentControl that) {
		//setAssignmentId(that.getAssignmentId());
		setTimelimit(that.getTimelimit());
		setStartdate(that.getStartdate());
		setDuedate(that.getDuedate());
		setSubmessage(that.getSubmessage());
		setGradeToStudent(that.getGradeToStudent());
		setGradeToTotalGrade(that.getGradeToTotalGrade());
		setSubmitType(that.getSubmitType());
		setToGradebook(that.getToGradebook());
		setTAssignment(that.getTAssignment());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		//buffer.append("assignmentId=[").append(assignmentId).append("] ");
		buffer.append("timelimit=[").append(timelimit).append("] ");
		buffer.append("startdate=[").append(startdate).append("] ");
		buffer.append("duedate=[").append(duedate).append("] ");
		buffer.append("submessage=[").append(submessage).append("] ");

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
		if (!(obj instanceof TAssignmentControl))
			return false;
		TAssignmentControl equalCheck = (TAssignmentControl) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
