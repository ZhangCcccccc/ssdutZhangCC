package net.xidlims.domain;

import java.io.Serializable;

import java.lang.StringBuilder;

import java.util.Calendar;
import java.util.Set;

import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.codehaus.jackson.annotate.JsonIgnore;

import javax.xml.bind.annotation.*;

import javax.persistence.*;

/**
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "findAllTCourseSiteArticals", query = "select myTCourseSiteArtical from TCourseSiteArtical myTCourseSiteArtical"),
		@NamedQuery(name = "findTCourseSiteArticalByContent", query = "select myTCourseSiteArtical from TCourseSiteArtical myTCourseSiteArtical where myTCourseSiteArtical.content = ?1"),
		@NamedQuery(name = "findTCourseSiteArticalByCreateDate", query = "select myTCourseSiteArtical from TCourseSiteArtical myTCourseSiteArtical where myTCourseSiteArtical.createDate = ?1"),
		@NamedQuery(name = "findTCourseSiteArticalById", query = "select myTCourseSiteArtical from TCourseSiteArtical myTCourseSiteArtical where myTCourseSiteArtical.id = ?1"),
		@NamedQuery(name = "findTCourseSiteArticalByImageUrl", query = "select myTCourseSiteArtical from TCourseSiteArtical myTCourseSiteArtical where myTCourseSiteArtical.imageUrl = ?1"),
		@NamedQuery(name = "findTCourseSiteArticalByImageUrlContaining", query = "select myTCourseSiteArtical from TCourseSiteArtical myTCourseSiteArtical where myTCourseSiteArtical.imageUrl like ?1"),
		@NamedQuery(name = "findTCourseSiteArticalByName", query = "select myTCourseSiteArtical from TCourseSiteArtical myTCourseSiteArtical where myTCourseSiteArtical.name = ?1"),
		@NamedQuery(name = "findTCourseSiteArticalByNameContaining", query = "select myTCourseSiteArtical from TCourseSiteArtical myTCourseSiteArtical where myTCourseSiteArtical.name like ?1"),
		@NamedQuery(name = "findTCourseSiteArticalByPrimaryKey", query = "select myTCourseSiteArtical from TCourseSiteArtical myTCourseSiteArtical where myTCourseSiteArtical.id = ?1"),
		@NamedQuery(name = "findTCourseSiteArticalBySort", query = "select myTCourseSiteArtical from TCourseSiteArtical myTCourseSiteArtical where myTCourseSiteArtical.sort = ?1"),
		@NamedQuery(name = "findTCourseSiteArticalByText", query = "select myTCourseSiteArtical from TCourseSiteArtical myTCourseSiteArtical where myTCourseSiteArtical.text = ?1"),
		@NamedQuery(name = "findTCourseSiteArticalByTextContaining", query = "select myTCourseSiteArtical from TCourseSiteArtical myTCourseSiteArtical where myTCourseSiteArtical.text like ?1") })
@Table(catalog = "xidlims", name = "t_course_site_artical")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "TCourseSiteArtical")
public class TCourseSiteArtical implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * �γ�վ�����±�
	 * 
	 */

	@Column(name = "id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@XmlElement
	Integer id;
	/**
	 * �������
	 * 
	 */

	@Column(name = "name")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String name;
	/**
	 * ���ݸ�Ҫ
	 * 
	 */

	@Column(name = "text")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String text;
	/**
	 * ��������ԽСԽ��ǰ��
	 * 
	 */

	@Column(name = "sort")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer sort;
	/**
	 * ͼƬ
	 * 
	 */

	@Column(name = "imageUrl")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String imageUrl;
	/**
	 * ����ʱ��
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar createDate;
	/**
	 * ����ʱ��
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modify_date")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar modifyDate;
	/**
	 * ��������
	 * 
	 */

	@Column(name = "content", columnDefinition = "TEXT")
	@Basic(fetch = FetchType.EAGER)
	@Lob
	@XmlElement
	String content;
	
	/**
	 * ��������
	 * 
	 */
	
	/*@Column(name = "coursewarePath", columnDefinition = "TEXT")
	@Basic(fetch = FetchType.EAGER)
	@Lob
	@XmlElement
	String coursewarePath;*/

	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "channel_id", referencedColumnName = "id") })
	@XmlTransient
	TCourseSiteChannel TCourseSiteChannel;
	
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "create_user", referencedColumnName = "username") })
	@XmlTransient
	User createUser;

	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "tag_id", referencedColumnName = "id") })
	@XmlTransient
	TCourseSiteTag TCourseSiteTag;
	public User getCreateUser() {
		return createUser;
	}

	public void setCreateUser(User createUser) {
		this.createUser = createUser;
	}

	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "state", referencedColumnName = "id") })
	@XmlTransient
	CDictionary CDictionary;

	/**
	 * �γ�վ�����±�
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * �γ�վ�����±�
	 * 
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * �������
	 * 
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * �������
	 * 
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * ���ݸ�Ҫ
	 * 
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * ���ݸ�Ҫ
	 * 
	 */
	public String getText() {
		return this.text;
	}

	/**
	 * ��������ԽСԽ��ǰ��
	 * 
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}

	/**
	 * ��������ԽСԽ��ǰ��
	 * 
	 */
	public Integer getSort() {
		return this.sort;
	}

	/**
	 * ͼƬ
	 * 
	 */
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	/**
	 * ͼƬ
	 * 
	 */
	public String getImageUrl() {
		return this.imageUrl;
	}

	/**
	 * ����ʱ��
	 * 
	 */
	public void setCreateDate(Calendar createDate) {
		this.createDate = createDate;
	}

	/**
	 * ����ʱ��
	 * 
	 */
	public Calendar getCreateDate() {
		return this.createDate;
	}

	public Calendar getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Calendar modifyDate) {
		this.modifyDate = modifyDate;
	}

	/**
	 * ��������
	 * 
	 */
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

	/*public String getCoursewarePath() {
		return coursewarePath;
	}

	public void setCoursewarePath(String coursewarePath) {
		this.coursewarePath = coursewarePath;
	}*/

	/**
	 */
	public void setTCourseSiteChannel(TCourseSiteChannel TCourseSiteChannel) {
		this.TCourseSiteChannel = TCourseSiteChannel;
	}

	/**
	 */
	@JsonIgnore
	public TCourseSiteChannel getTCourseSiteChannel() {
		return TCourseSiteChannel;
	}

	/**
	 */
	public void setTCourseSiteTag(TCourseSiteTag TCourseSiteTag) {
		this.TCourseSiteTag = TCourseSiteTag;
	}

	/**
	 */
	@JsonIgnore
	public TCourseSiteTag getTCourseSiteTag() {
		return TCourseSiteTag;
	}

	@JsonIgnore
	public CDictionary getCDictionary() {
		return CDictionary;
	}

	public void setCDictionary(CDictionary cDictionary) {
		CDictionary = cDictionary;
	}

	/**
	 */
	public TCourseSiteArtical() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(TCourseSiteArtical that) {
		setId(that.getId());
		setName(that.getName());
		setText(that.getText());
		setSort(that.getSort());
		setImageUrl(that.getImageUrl());
		setCreateDate(that.getCreateDate());
		setModifyDate(that.getModifyDate());
		setContent(that.getContent());
		setTCourseSiteChannel(that.getTCourseSiteChannel());
		setCreateUser(that.getCreateUser());
		setTCourseSiteTag(that.getTCourseSiteTag());
		setCDictionary(that.getCDictionary());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("id=[").append(id).append("] ");
		buffer.append("name=[").append(name).append("] ");
		buffer.append("text=[").append(text).append("] ");
		buffer.append("sort=[").append(sort).append("] ");
		buffer.append("imageUrl=[").append(imageUrl).append("] ");
		buffer.append("createDate=[").append(createDate).append("] ");
		buffer.append("modifyDate=[").append(modifyDate).append("] ");
		buffer.append("content=[").append(content).append("] ");

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
		if (!(obj instanceof TCourseSiteArtical))
			return false;
		TCourseSiteArtical equalCheck = (TCourseSiteArtical) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
