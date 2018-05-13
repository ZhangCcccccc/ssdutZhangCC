package net.xidlims.domain;

import java.io.Serializable;

import java.lang.StringBuilder;

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
		@NamedQuery(name = "findAllAppQuestionnaires", query = "select myAppQuestionnaire from AppQuestionnaire myAppQuestionnaire"),
		@NamedQuery(name = "findAppQuestionnaireByDescription", query = "select myAppQuestionnaire from AppQuestionnaire myAppQuestionnaire where myAppQuestionnaire.description = ?1"),
		@NamedQuery(name = "findAppQuestionnaireByDescriptionContaining", query = "select myAppQuestionnaire from AppQuestionnaire myAppQuestionnaire where myAppQuestionnaire.description like ?1"),
		@NamedQuery(name = "findAppQuestionnaireById", query = "select myAppQuestionnaire from AppQuestionnaire myAppQuestionnaire where myAppQuestionnaire.id = ?1"),
		@NamedQuery(name = "findAppQuestionnaireByPrimaryKey", query = "select myAppQuestionnaire from AppQuestionnaire myAppQuestionnaire where myAppQuestionnaire.id = ?1"),
		@NamedQuery(name = "findAppQuestionnaireByStat", query = "select myAppQuestionnaire from AppQuestionnaire myAppQuestionnaire where myAppQuestionnaire.stat = ?1"),
		@NamedQuery(name = "findAppQuestionnaireByTitle", query = "select myAppQuestionnaire from AppQuestionnaire myAppQuestionnaire where myAppQuestionnaire.title = ?1"),
		@NamedQuery(name = "findAppQuestionnaireByTitleContaining", query = "select myAppQuestionnaire from AppQuestionnaire myAppQuestionnaire where myAppQuestionnaire.title like ?1"),
		@NamedQuery(name = "findAppQuestionnaireByType", query = "select myAppQuestionnaire from AppQuestionnaire myAppQuestionnaire where myAppQuestionnaire.type = ?1") })
@Table(catalog = "xidlims", name = "app_questionnaire")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "AppQuestionnaire")
@XmlRootElement(namespace = "xidlims/net/xidlims/domain")
public class AppQuestionnaire implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 问卷调查
	 * 
	 */

	@Column(name = "id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@XmlElement
	Integer id;
	/**
	 * 问卷
	 * 
	 */

	@Column(name = "title", length = 40)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String title;
	/**
	 * 题干
	 * 
	 */

	@Column(name = "description")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String description;
	/**
	 * 题目类型，1为多选，4为单选
	 * 
	 */

	@Column(name = "type")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer type;
	/**
	 * 统计参与人数
	 * 
	 */

	@Column(name = "stat")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer stat;

	/**
	 */
	@OneToMany(mappedBy = "appQuestionnaire", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.AppQuestionchoose> appQuestionchooses;

	/**
	 * 问卷调查
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 问卷调查
	 * 
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * 问卷
	 * 
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 问卷
	 * 
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * 题干
	 * 
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 题干
	 * 
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * 题目类型，1为多选，4为单选
	 * 
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * 题目类型，1为多选，4为单选
	 * 
	 */
	public Integer getType() {
		return this.type;
	}

	/**
	 * 统计参与人数
	 * 
	 */
	public void setStat(Integer stat) {
		this.stat = stat;
	}

	/**
	 * 统计参与人数
	 * 
	 */
	public Integer getStat() {
		return this.stat;
	}

	/**
	 */
	public void setAppQuestionchooses(Set<AppQuestionchoose> appQuestionchooses) {
		this.appQuestionchooses = appQuestionchooses;
	}

	/**
	 */
	@JsonIgnore
	public Set<AppQuestionchoose> getAppQuestionchooses() {
		if (appQuestionchooses == null) {
			appQuestionchooses = new java.util.LinkedHashSet<net.xidlims.domain.AppQuestionchoose>();
		}
		return appQuestionchooses;
	}

	/**
	 */
	public AppQuestionnaire() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(AppQuestionnaire that) {
		setId(that.getId());
		setTitle(that.getTitle());
		setDescription(that.getDescription());
		setType(that.getType());
		setStat(that.getStat());
		setAppQuestionchooses(new java.util.LinkedHashSet<net.xidlims.domain.AppQuestionchoose>(that.getAppQuestionchooses()));
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
		buffer.append("type=[").append(type).append("] ");
		buffer.append("stat=[").append(stat).append("] ");

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
		if (!(obj instanceof AppQuestionnaire))
			return false;
		AppQuestionnaire equalCheck = (AppQuestionnaire) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
