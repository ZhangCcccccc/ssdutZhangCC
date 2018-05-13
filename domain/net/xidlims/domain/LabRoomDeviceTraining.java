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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
		@NamedQuery(name = "findAllLabRoomDeviceTrainings", query = "select myLabRoomDeviceTraining from LabRoomDeviceTraining myLabRoomDeviceTraining"),
		@NamedQuery(name = "findLabRoomDeviceTrainingByAccessJoin", query = "select myLabRoomDeviceTraining from LabRoomDeviceTraining myLabRoomDeviceTraining where myLabRoomDeviceTraining.accessJoin = ?1"),
		@NamedQuery(name = "findLabRoomDeviceTrainingByAccessJoinContaining", query = "select myLabRoomDeviceTraining from LabRoomDeviceTraining myLabRoomDeviceTraining where myLabRoomDeviceTraining.accessJoin like ?1"),
		@NamedQuery(name = "findLabRoomDeviceTrainingByAddress", query = "select myLabRoomDeviceTraining from LabRoomDeviceTraining myLabRoomDeviceTraining where myLabRoomDeviceTraining.address = ?1"),
		@NamedQuery(name = "findLabRoomDeviceTrainingByAddressContaining", query = "select myLabRoomDeviceTraining from LabRoomDeviceTraining myLabRoomDeviceTraining where myLabRoomDeviceTraining.address like ?1"),
		@NamedQuery(name = "findLabRoomDeviceTrainingByContent", query = "select myLabRoomDeviceTraining from LabRoomDeviceTraining myLabRoomDeviceTraining where myLabRoomDeviceTraining.content = ?1"),
		@NamedQuery(name = "findLabRoomDeviceTrainingById", query = "select myLabRoomDeviceTraining from LabRoomDeviceTraining myLabRoomDeviceTraining where myLabRoomDeviceTraining.id = ?1"),
		@NamedQuery(name = "findLabRoomDeviceTrainingByJoinNumber", query = "select myLabRoomDeviceTraining from LabRoomDeviceTraining myLabRoomDeviceTraining where myLabRoomDeviceTraining.joinNumber = ?1"),
		@NamedQuery(name = "findLabRoomDeviceTrainingByNumber", query = "select myLabRoomDeviceTraining from LabRoomDeviceTraining myLabRoomDeviceTraining where myLabRoomDeviceTraining.number = ?1"),
		@NamedQuery(name = "findLabRoomDeviceTrainingByPassRate", query = "select myLabRoomDeviceTraining from LabRoomDeviceTraining myLabRoomDeviceTraining where myLabRoomDeviceTraining.passRate = ?1"),
		@NamedQuery(name = "findLabRoomDeviceTrainingByPassRateContaining", query = "select myLabRoomDeviceTraining from LabRoomDeviceTraining myLabRoomDeviceTraining where myLabRoomDeviceTraining.passRate like ?1"),
		@NamedQuery(name = "findLabRoomDeviceTrainingByPrimaryKey", query = "select myLabRoomDeviceTraining from LabRoomDeviceTraining myLabRoomDeviceTraining where myLabRoomDeviceTraining.id = ?1"),
		//@NamedQuery(name = "findLabRoomDeviceTrainingByStatus", query = "select myLabRoomDeviceTraining from LabRoomDeviceTraining myLabRoomDeviceTraining where myLabRoomDeviceTraining.status = ?1"),
		@NamedQuery(name = "findLabRoomDeviceTrainingByTime", query = "select myLabRoomDeviceTraining from LabRoomDeviceTraining myLabRoomDeviceTraining where myLabRoomDeviceTraining.time = ?1") })
@Table(catalog = "xidlims", name = "lab_room_device_training")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "test/net/xidlims/domain", name = "LabRoomDeviceTraining")
@XmlRootElement(namespace = "test/net/xidlims/domain")
public class LabRoomDeviceTraining implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * �豸��ѵ��
	 * 
	 */

	@Column(name = "id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@XmlElement
	Integer id;
	/**
	 * ��ѵʱ��
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "time")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar time;
	/**
	 * ��ѵ�ص�
	 * 
	 */

	@Column(name = "address")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String address;
	/**
	 * ��ѵ����
	 * 
	 */

	@Column(name = "content", columnDefinition = "TEXT")
	@Basic(fetch = FetchType.EAGER)
	@Lob
	@XmlElement
	String content;
	/**
	 * ��ѵ����
	 * 
	 */

	@Column(name = "number")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer number;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "status", referencedColumnName = "id") })
	@XmlTransient
	CDictionary CTrainingStatus;
	
	/**
	 * ��ѵͨ����
	 * 
	 */

	@Column(name = "pass_rate")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String passRate;
	/**
	 * �μ���ѵ������
	 * 
	 */

	@Column(name = "join_number")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer joinNumber;
	/**
	 * �Ƿ�����ԤԼ����Ե�ǰ��¼�ˣ�
	 * 
	 */

	@Column(name = "access_join")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String accessJoin;
	
	@Column(name = "flag")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer flag;
	
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "lab_room_device", referencedColumnName = "id") })
	@XmlTransient
	LabRoomDevice labRoomDevice;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "teacher", referencedColumnName = "username") })
	@XmlTransient
	User user;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "term", referencedColumnName = "id") })
	@XmlTransient
	SchoolTerm schoolTerm;
	/**
	 */
	@OneToMany(mappedBy = "labRoomDeviceTraining", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.LabRoomDeviceTrainingPeople> labRoomDeviceTrainingPeoples;

	/**
	 * �豸��ѵ��
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * �豸��ѵ��
	 * 
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * ��ѵʱ��
	 * 
	 */
	public void setTime(Calendar time) {
		this.time = time;
	}

	/**
	 * ��ѵʱ��
	 * 
	 */
	public Calendar getTime() {
		return this.time;
	}

	/**
	 * ��ѵ�ص�
	 * 
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * ��ѵ�ص�
	 * 
	 */
	public String getAddress() {
		return this.address;
	}

	/**
	 * ��ѵ����
	 * 
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * ��ѵ����
	 * 
	 */
	public String getContent() {
		return this.content;
	}

	/**
	 * ��ѵ����
	 * 
	 */
	public void setNumber(Integer number) {
		this.number = number;
	}

	/**
	 * ��ѵ����
	 * 
	 */
	public Integer getNumber() {
		return this.number;
	}

	/**
	 */
	public void setCTrainingStatus(CDictionary CTrainingStatus) {
		this.CTrainingStatus = CTrainingStatus;
	}

	/**
	 */
	@JsonIgnore
	public CDictionary getCTrainingStatus() {
		return CTrainingStatus;
	}

	/**
	 * ��ѵͨ����
	 * 
	 */
	public void setPassRate(String passRate) {
		this.passRate = passRate;
	}

	/**
	 * ��ѵͨ����
	 * 
	 */
	public String getPassRate() {
		return this.passRate;
	}

	/**
	 * �μ���ѵ������
	 * 
	 */
	public void setJoinNumber(Integer joinNumber) {
		this.joinNumber = joinNumber;
	}

	/**
	 * �μ���ѵ������
	 * 
	 */
	public Integer getJoinNumber() {
		return this.joinNumber;
	}

	/**
	 * �Ƿ�����ԤԼ����Ե�ǰ��¼�ˣ�
	 * 
	 */
	public void setAccessJoin(String accessJoin) {
		this.accessJoin = accessJoin;
	}

	/**
	 * �Ƿ�����ԤԼ����Ե�ǰ��¼�ˣ�
	 * 
	 */
	public String getAccessJoin() {
		return this.accessJoin;
	}
	
	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	/**
	 */
	public void setLabRoomDevice(LabRoomDevice labRoomDevice) {
		this.labRoomDevice = labRoomDevice;
	}

	/**
	 */
	public LabRoomDevice getLabRoomDevice() {
		return labRoomDevice;
	}

	/**
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 */
	public User getUser() {
		return user;
	}

	/**
	 */
	public void setSchoolTerm(SchoolTerm schoolTerm) {
		this.schoolTerm = schoolTerm;
	}

	/**
	 */
	public SchoolTerm getSchoolTerm() {
		return schoolTerm;
	}

	/**
	 */
	public void setLabRoomDeviceTrainingPeoples(Set<LabRoomDeviceTrainingPeople> labRoomDeviceTrainingPeoples) {
		this.labRoomDeviceTrainingPeoples = labRoomDeviceTrainingPeoples;
	}

	/**
	 */
	public Set<LabRoomDeviceTrainingPeople> getLabRoomDeviceTrainingPeoples() {
		if (labRoomDeviceTrainingPeoples == null) {
			labRoomDeviceTrainingPeoples = new java.util.LinkedHashSet<net.xidlims.domain.LabRoomDeviceTrainingPeople>();
		}
		return labRoomDeviceTrainingPeoples;
	}

	/**
	 */
	public LabRoomDeviceTraining() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(LabRoomDeviceTraining that) {
		setId(that.getId());
		setTime(that.getTime());
		setAddress(that.getAddress());
		setContent(that.getContent());
		setNumber(that.getNumber());
		//setStatus(that.getStatus());
		setPassRate(that.getPassRate());
		setJoinNumber(that.getJoinNumber());
		setAccessJoin(that.getAccessJoin());
		setLabRoomDevice(that.getLabRoomDevice());
		setUser(that.getUser());
		setSchoolTerm(that.getSchoolTerm());
		setLabRoomDeviceTrainingPeoples(new java.util.LinkedHashSet<net.xidlims.domain.LabRoomDeviceTrainingPeople>(that.getLabRoomDeviceTrainingPeoples()));
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("id=[").append(id).append("] ");
		buffer.append("time=[").append(time).append("] ");
		buffer.append("address=[").append(address).append("] ");
		buffer.append("content=[").append(content).append("] ");
		buffer.append("number=[").append(number).append("] ");
		//buffer.append("status=[").append(status).append("] ");
		buffer.append("passRate=[").append(passRate).append("] ");
		buffer.append("joinNumber=[").append(joinNumber).append("] ");
		buffer.append("accessJoin=[").append(accessJoin).append("] ");

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
		if (!(obj instanceof LabRoomDeviceTraining))
			return false;
		LabRoomDeviceTraining equalCheck = (LabRoomDeviceTraining) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
