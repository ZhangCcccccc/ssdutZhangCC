package net.xidlims.domain;

import java.io.Serializable;

import java.lang.StringBuilder;

import java.util.Calendar;

import javax.xml.bind.annotation.*;

import javax.persistence.*;

/**
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "findAllTExperimentSkillUsers", query = "select myTExperimentSkillUser from TExperimentSkillUser myTExperimentSkillUser"),
		@NamedQuery(name = "findTExperimentSkillUserByCreateTime", query = "select myTExperimentSkillUser from TExperimentSkillUser myTExperimentSkillUser where myTExperimentSkillUser.createTime = ?1"),
		@NamedQuery(name = "findTExperimentSkillUserByFinalGrade", query = "select myTExperimentSkillUser from TExperimentSkillUser myTExperimentSkillUser where myTExperimentSkillUser.finalGrade = ?1"),
		@NamedQuery(name = "findTExperimentSkillUserByGradeTeacher", query = "select myTExperimentSkillUser from TExperimentSkillUser myTExperimentSkillUser where myTExperimentSkillUser.gradeTeacher = ?1"),
		@NamedQuery(name = "findTExperimentSkillUserByGradeTeacherContaining", query = "select myTExperimentSkillUser from TExperimentSkillUser myTExperimentSkillUser where myTExperimentSkillUser.gradeTeacher like ?1"),
		@NamedQuery(name = "findTExperimentSkillUserByGradeTime", query = "select myTExperimentSkillUser from TExperimentSkillUser myTExperimentSkillUser where myTExperimentSkillUser.gradeTime = ?1"),
		@NamedQuery(name = "findTExperimentSkillUserById", query = "select myTExperimentSkillUser from TExperimentSkillUser myTExperimentSkillUser where myTExperimentSkillUser.id = ?1"),
		@NamedQuery(name = "findTExperimentSkillUserByPrimaryKey", query = "select myTExperimentSkillUser from TExperimentSkillUser myTExperimentSkillUser where myTExperimentSkillUser.id = ?1"),
		@NamedQuery(name = "findTExperimentSkillUserByRealGrade", query = "select myTExperimentSkillUser from TExperimentSkillUser myTExperimentSkillUser where myTExperimentSkillUser.realGrade = ?1"),
		@NamedQuery(name = "findTExperimentSkillUserBySkillId", query = "select myTExperimentSkillUser from TExperimentSkillUser myTExperimentSkillUser where myTExperimentSkillUser.skillId = ?1"),
		@NamedQuery(name = "findTExperimentSkillUserByUsername", query = "select myTExperimentSkillUser from TExperimentSkillUser myTExperimentSkillUser where myTExperimentSkillUser.username = ?1"),
		@NamedQuery(name = "findTExperimentSkillUserByUsernameContaining", query = "select myTExperimentSkillUser from TExperimentSkillUser myTExperimentSkillUser where myTExperimentSkillUser.username like ?1") })
@Table(catalog = "xidlims", name = "t_experiment_skill_user")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "TExperimentSkillUser")
public class TExperimentSkillUser implements Serializable {
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
	 */

	@Column(name = "skill_id")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer skillId;
	/**
	 */

	@Column(name = "username")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String username;
	/**
	 */

	@Column(name = "real_grade")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer realGrade;
	/**
	 */

	@Column(name = "final_grade")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer finalGrade;
	/**
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar createTime;
	/**
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "grade_time")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar gradeTime;
	/**
	 */

	@Column(name = "grade_teacher")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String gradeTeacher;

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
	 */
	public void setSkillId(Integer skillId) {
		this.skillId = skillId;
	}

	/**
	 */
	public Integer getSkillId() {
		return this.skillId;
	}

	/**
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 */
	public String getUsername() {
		return this.username;
	}

	/**
	 */
	public void setRealGrade(Integer realGrade) {
		this.realGrade = realGrade;
	}

	/**
	 */
	public Integer getRealGrade() {
		return this.realGrade;
	}

	/**
	 */
	public void setFinalGrade(Integer finalGrade) {
		this.finalGrade = finalGrade;
	}

	/**
	 */
	public Integer getFinalGrade() {
		return this.finalGrade;
	}

	/**
	 */
	public void setCreateTime(Calendar createTime) {
		this.createTime = createTime;
	}

	/**
	 */
	public Calendar getCreateTime() {
		return this.createTime;
	}

	/**
	 */
	public void setGradeTime(Calendar gradeTime) {
		this.gradeTime = gradeTime;
	}

	/**
	 */
	public Calendar getGradeTime() {
		return this.gradeTime;
	}

	/**
	 */
	public void setGradeTeacher(String gradeTeacher) {
		this.gradeTeacher = gradeTeacher;
	}

	/**
	 */
	public String getGradeTeacher() {
		return this.gradeTeacher;
	}

	/**
	 */
	public TExperimentSkillUser() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(TExperimentSkillUser that) {
		setId(that.getId());
		setSkillId(that.getSkillId());
		setUsername(that.getUsername());
		setRealGrade(that.getRealGrade());
		setFinalGrade(that.getFinalGrade());
		setCreateTime(that.getCreateTime());
		setGradeTime(that.getGradeTime());
		setGradeTeacher(that.getGradeTeacher());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("id=[").append(id).append("] ");
		buffer.append("skillId=[").append(skillId).append("] ");
		buffer.append("username=[").append(username).append("] ");
		buffer.append("realGrade=[").append(realGrade).append("] ");
		buffer.append("finalGrade=[").append(finalGrade).append("] ");
		buffer.append("createTime=[").append(createTime).append("] ");
		buffer.append("gradeTime=[").append(gradeTime).append("] ");
		buffer.append("gradeTeacher=[").append(gradeTeacher).append("] ");

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
		if (!(obj instanceof TExperimentSkillUser))
			return false;
		TExperimentSkillUser equalCheck = (TExperimentSkillUser) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
