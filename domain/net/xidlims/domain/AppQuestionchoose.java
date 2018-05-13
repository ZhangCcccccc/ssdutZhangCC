package net.xidlims.domain;

import java.io.Serializable;

import java.lang.StringBuilder;

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
		@NamedQuery(name = "findAllAppQuestionchooses", query = "select myAppQuestionchoose from AppQuestionchoose myAppQuestionchoose"),
		@NamedQuery(name = "findAppQuestionchooseByChoose", query = "select myAppQuestionchoose from AppQuestionchoose myAppQuestionchoose where myAppQuestionchoose.choose = ?1"),
		@NamedQuery(name = "findAppQuestionchooseByChooseContaining", query = "select myAppQuestionchoose from AppQuestionchoose myAppQuestionchoose where myAppQuestionchoose.choose like ?1"),
		@NamedQuery(name = "findAppQuestionchooseById", query = "select myAppQuestionchoose from AppQuestionchoose myAppQuestionchoose where myAppQuestionchoose.id = ?1"),
		@NamedQuery(name = "findAppQuestionchooseByNum", query = "select myAppQuestionchoose from AppQuestionchoose myAppQuestionchoose where myAppQuestionchoose.num = ?1"),
		@NamedQuery(name = "findAppQuestionchooseByPrimaryKey", query = "select myAppQuestionchoose from AppQuestionchoose myAppQuestionchoose where myAppQuestionchoose.id = ?1"),
		@NamedQuery(name = "findAppQuestionchooseByText", query = "select myAppQuestionchoose from AppQuestionchoose myAppQuestionchoose where myAppQuestionchoose.text = ?1"),
		@NamedQuery(name = "findAppQuestionchooseByTextContaining", query = "select myAppQuestionchoose from AppQuestionchoose myAppQuestionchoose where myAppQuestionchoose.text like ?1") })
@Table(catalog = "xidlims", name = "app_questionchoose")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "AppQuestionchoose")
public class AppQuestionchoose implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * �ʾ����ѡ���
	 * 
	 */

	@Column(name = "id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@XmlElement
	Integer id;
	/**
	 * �ʾ����ѡ��
	 * 
	 */

	@Column(name = "text")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String text;
	/**
	 * �ʾ��ѡ��
	 * 
	 */

	@Column(name = "choose")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String choose;
	/**
	 * ѡ������
	 * 
	 */

	@Column(name = "num")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer num;

	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "question_id", referencedColumnName = "id") })
	@XmlTransient
	AppQuestionnaire appQuestionnaire;

	/**
	 * �ʾ����ѡ���
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * �ʾ����ѡ���
	 * 
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * �ʾ����ѡ��
	 * 
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * �ʾ����ѡ��
	 * 
	 */
	public String getText() {
		return this.text;
	}

	/**
	 * �ʾ��ѡ��
	 * 
	 */
	public void setChoose(String choose) {
		this.choose = choose;
	}

	/**
	 * �ʾ��ѡ��
	 * 
	 */
	public String getChoose() {
		return this.choose;
	}

	/**
	 * ѡ������
	 * 
	 */
	public void setNum(Integer num) {
		this.num = num;
	}

	/**
	 * ѡ������
	 * 
	 */
	public Integer getNum() {
		return this.num;
	}

	/**
	 */
	public void setAppQuestionnaire(AppQuestionnaire appQuestionnaire) {
		this.appQuestionnaire = appQuestionnaire;
	}

	/**
	 */
	@JsonIgnore
	public AppQuestionnaire getAppQuestionnaire() {
		return appQuestionnaire;
	}

	/**
	 */
	public AppQuestionchoose() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(AppQuestionchoose that) {
		setId(that.getId());
		setText(that.getText());
		setChoose(that.getChoose());
		setNum(that.getNum());
		setAppQuestionnaire(that.getAppQuestionnaire());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("id=[").append(id).append("] ");
		buffer.append("text=[").append(text).append("] ");
		buffer.append("choose=[").append(choose).append("] ");
		buffer.append("num=[").append(num).append("] ");

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
		if (!(obj instanceof AppQuestionchoose))
			return false;
		AppQuestionchoose equalCheck = (AppQuestionchoose) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
