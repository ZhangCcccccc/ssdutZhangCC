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
		@NamedQuery(name = "findAllResearchProjects", query = "select myResearchProject from ResearchProject myResearchProject"),
		@NamedQuery(name = "findResearchProjectByCode", query = "select myResearchProject from ResearchProject myResearchProject where myResearchProject.code = ?1"),
		@NamedQuery(name = "findResearchProjectByCodeContaining", query = "select myResearchProject from ResearchProject myResearchProject where myResearchProject.code like ?1"),
		@NamedQuery(name = "findResearchProjectById", query = "select myResearchProject from ResearchProject myResearchProject where myResearchProject.id = ?1"),
		@NamedQuery(name = "findResearchProjectByName", query = "select myResearchProject from ResearchProject myResearchProject where myResearchProject.name = ?1"),
		@NamedQuery(name = "findResearchProjectByNameContaining", query = "select myResearchProject from ResearchProject myResearchProject where myResearchProject.name like ?1"),
		@NamedQuery(name = "findResearchProjectByPrimaryKey", query = "select myResearchProject from ResearchProject myResearchProject where myResearchProject.id = ?1") })
@Table(catalog = "xidlims", name = "research_project")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "ResearchProject")
@XmlRootElement(namespace = "xidlims/net/xidlims/domain")
public class ResearchProject implements Serializable {
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
	 * ���������
	 * 
	 */

	@Column(name = "name")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String name;
	/**
	 * ����
	 * 
	 */

	@Column(name = "code")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String code;

	/**
	 */
	@OneToMany(mappedBy = "researchProject", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.LabRoomDeviceReservation> labRoomDeviceReservations;

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
	 * ���������
	 * 
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * ���������
	 * 
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * ����
	 * 
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * ����
	 * 
	 */
	public String getCode() {
		return this.code;
	}

	/**
	 */
	public void setLabRoomDeviceReservations(Set<LabRoomDeviceReservation> labRoomDeviceReservations) {
		this.labRoomDeviceReservations = labRoomDeviceReservations;
	}

	/**
	 */
	@JsonIgnore
	public Set<LabRoomDeviceReservation> getLabRoomDeviceReservations() {
		if (labRoomDeviceReservations == null) {
			labRoomDeviceReservations = new java.util.LinkedHashSet<net.xidlims.domain.LabRoomDeviceReservation>();
		}
		return labRoomDeviceReservations;
	}

	/**
	 */
	public ResearchProject() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(ResearchProject that) {
		setId(that.getId());
		setName(that.getName());
		setCode(that.getCode());
		setLabRoomDeviceReservations(new java.util.LinkedHashSet<net.xidlims.domain.LabRoomDeviceReservation>(that.getLabRoomDeviceReservations()));
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("id=[").append(id).append("] ");
		buffer.append("name=[").append(name).append("] ");
		buffer.append("code=[").append(code).append("] ");

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
		if (!(obj instanceof ResearchProject))
			return false;
		ResearchProject equalCheck = (ResearchProject) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
