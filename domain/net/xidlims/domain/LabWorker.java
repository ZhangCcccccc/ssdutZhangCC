package net.xidlims.domain;

import java.io.Serializable;

import java.lang.StringBuilder;

import java.util.Calendar;
import java.util.Set;

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
		@NamedQuery(name = "findAllLabWorkers", query = "select myLabWorker from LabWorker myLabWorker"),
		@NamedQuery(name = "findLabWorkerById", query = "select myLabWorker from LabWorker myLabWorker where myLabWorker.id = ?1"),
		@NamedQuery(name = "findLabWorkerByLwBirthday", query = "select myLabWorker from LabWorker myLabWorker where myLabWorker.lwBirthday = ?1"),
		@NamedQuery(name = "findLabWorkerByLwBirthdayAfter", query = "select myLabWorker from LabWorker myLabWorker where myLabWorker.lwBirthday > ?1"),
		@NamedQuery(name = "findLabWorkerByLwBirthdayBefore", query = "select myLabWorker from LabWorker myLabWorker where myLabWorker.lwBirthday < ?1"),
		@NamedQuery(name = "findLabWorkerByLwBookQuantity", query = "select myLabWorker from LabWorker myLabWorker where myLabWorker.lwBookQuantity = ?1"),
		@NamedQuery(name = "findLabWorkerByLwCategoryExpert", query = "select myLabWorker from LabWorker myLabWorker where myLabWorker.lwCategoryExpert = ?1"),
		@NamedQuery(name = "findLabWorkerByLwCategoryExpertContaining", query = "select myLabWorker from LabWorker myLabWorker where myLabWorker.lwCategoryExpert like ?1"),
		@NamedQuery(name = "findLabWorkerByLwCodeCustom", query = "select myLabWorker from LabWorker myLabWorker where myLabWorker.lwCodeCustom = ?1"),
		@NamedQuery(name = "findLabWorkerByLwCodeCustomContaining", query = "select myLabWorker from LabWorker myLabWorker where myLabWorker.lwCodeCustom like ?1"),
		@NamedQuery(name = "findLabWorkerByLwDuty", query = "select myLabWorker from LabWorker myLabWorker where myLabWorker.lwDuty = ?1"),
		@NamedQuery(name = "findLabWorkerByLwDutyContaining", query = "select myLabWorker from LabWorker myLabWorker where myLabWorker.lwDuty like ?1"),
		@NamedQuery(name = "findLabWorkerByLwGraduationMajor", query = "select myLabWorker from LabWorker myLabWorker where myLabWorker.lwGraduationMajor = ?1"),
		@NamedQuery(name = "findLabWorkerByLwGraduationMajorContaining", query = "select myLabWorker from LabWorker myLabWorker where myLabWorker.lwGraduationMajor like ?1"),
		@NamedQuery(name = "findLabWorkerByLwGraduationSchool", query = "select myLabWorker from LabWorker myLabWorker where myLabWorker.lwGraduationSchool = ?1"),
		@NamedQuery(name = "findLabWorkerByLwGraduationSchoolContaining", query = "select myLabWorker from LabWorker myLabWorker where myLabWorker.lwGraduationSchool like ?1"),
		@NamedQuery(name = "findLabWorkerByLwGraduationTime", query = "select myLabWorker from LabWorker myLabWorker where myLabWorker.lwGraduationTime = ?1"),
		@NamedQuery(name = "findLabWorkerByLwLabProject", query = "select myLabWorker from LabWorker myLabWorker where myLabWorker.lwLabProject = ?1"),
		@NamedQuery(name = "findLabWorkerByLwLabProjectContaining", query = "select myLabWorker from LabWorker myLabWorker where myLabWorker.lwLabProject like ?1"),
		@NamedQuery(name = "findLabWorkerByLwMajor", query = "select myLabWorker from LabWorker myLabWorker where myLabWorker.lwMajor = ?1"),
		@NamedQuery(name = "findLabWorkerByLwMajorContaining", query = "select myLabWorker from LabWorker myLabWorker where myLabWorker.lwMajor like ?1"),
		@NamedQuery(name = "findLabWorkerByLwName", query = "select myLabWorker from LabWorker myLabWorker where myLabWorker.lwName = ?1"),
		@NamedQuery(name = "findLabWorkerByLwNameContaining", query = "select myLabWorker from LabWorker myLabWorker where myLabWorker.lwName like ?1"),
		@NamedQuery(name = "findLabWorkerByLwPaperQuantity", query = "select myLabWorker from LabWorker myLabWorker where myLabWorker.lwPaperQuantity = ?1"),
		@NamedQuery(name = "findLabWorkerByLwProfessionSpecialty", query = "select myLabWorker from LabWorker myLabWorker where myLabWorker.lwProfessionSpecialty = ?1"),
		@NamedQuery(name = "findLabWorkerByLwProfessionSpecialtyContaining", query = "select myLabWorker from LabWorker myLabWorker where myLabWorker.lwProfessionSpecialty like ?1"),
		@NamedQuery(name = "findLabWorkerByLwProfessionTime", query = "select myLabWorker from LabWorker myLabWorker where myLabWorker.lwProfessionTime = ?1"),
		@NamedQuery(name = "findLabWorkerByLwRemarkNumOne", query = "select myLabWorker from LabWorker myLabWorker where myLabWorker.lwRemarkNumOne = ?1"),
		@NamedQuery(name = "findLabWorkerByLwRemarkNumOneContaining", query = "select myLabWorker from LabWorker myLabWorker where myLabWorker.lwRemarkNumOne like ?1"),
		@NamedQuery(name = "findLabWorkerByLwRemarkNumTwo", query = "select myLabWorker from LabWorker myLabWorker where myLabWorker.lwRemarkNumTwo = ?1"),
		@NamedQuery(name = "findLabWorkerByLwRemarkNumTwoContaining", query = "select myLabWorker from LabWorker myLabWorker where myLabWorker.lwRemarkNumTwo like ?1"),
		@NamedQuery(name = "findLabWorkerByLwRemarkOne", query = "select myLabWorker from LabWorker myLabWorker where myLabWorker.lwRemarkOne = ?1"),
		@NamedQuery(name = "findLabWorkerByLwRemarkTwo", query = "select myLabWorker from LabWorker myLabWorker where myLabWorker.lwRemarkTwo = ?1"),
		@NamedQuery(name = "findLabWorkerByLwRewardTime", query = "select myLabWorker from LabWorker myLabWorker where myLabWorker.lwRewardTime = ?1"),
		@NamedQuery(name = "findLabWorkerByLwSex", query = "select myLabWorker from LabWorker myLabWorker where myLabWorker.lwSex = ?1"),
		@NamedQuery(name = "findLabWorkerByLwSexContaining", query = "select myLabWorker from LabWorker myLabWorker where myLabWorker.lwSex like ?1"),
		@NamedQuery(name = "findLabWorkerByLwSkillLevel", query = "select myLabWorker from LabWorker myLabWorker where myLabWorker.lwSkillLevel = ?1"),
		@NamedQuery(name = "findLabWorkerByLwSkillLevelContaining", query = "select myLabWorker from LabWorker myLabWorker where myLabWorker.lwSkillLevel like ?1"),
		@NamedQuery(name = "findLabWorkerByLwTrainFormalAbroadTime", query = "select myLabWorker from LabWorker myLabWorker where myLabWorker.lwTrainFormalAbroadTime = ?1"),
		@NamedQuery(name = "findLabWorkerByLwTrainFormalAbroadTimeContaining", query = "select myLabWorker from LabWorker myLabWorker where myLabWorker.lwTrainFormalAbroadTime like ?1"),
		@NamedQuery(name = "findLabWorkerByLwTrainFormalInlandTime", query = "select myLabWorker from LabWorker myLabWorker where myLabWorker.lwTrainFormalInlandTime = ?1"),
		@NamedQuery(name = "findLabWorkerByLwTrainFormalInlandTimeContaining", query = "select myLabWorker from LabWorker myLabWorker where myLabWorker.lwTrainFormalInlandTime like ?1"),
		@NamedQuery(name = "findLabWorkerByLwTrainInformalAbroadContent", query = "select myLabWorker from LabWorker myLabWorker where myLabWorker.lwTrainInformalAbroadContent = ?1"),
		@NamedQuery(name = "findLabWorkerByLwTrainInformalAbroadTime", query = "select myLabWorker from LabWorker myLabWorker where myLabWorker.lwTrainInformalAbroadTime = ?1"),
		@NamedQuery(name = "findLabWorkerByLwTrainInformalAbroadTimeContaining", query = "select myLabWorker from LabWorker myLabWorker where myLabWorker.lwTrainInformalAbroadTime like ?1"),
		@NamedQuery(name = "findLabWorkerByLwTrainInformalInlandContent", query = "select myLabWorker from LabWorker myLabWorker where myLabWorker.lwTrainInformalInlandContent = ?1"),
		@NamedQuery(name = "findLabWorkerByLwTrainInformalInlandTime", query = "select myLabWorker from LabWorker myLabWorker where myLabWorker.lwTrainInformalInlandTime = ?1"),
		@NamedQuery(name = "findLabWorkerByLwTrainInformalInlandTimeContaining", query = "select myLabWorker from LabWorker myLabWorker where myLabWorker.lwTrainInformalInlandTime like ?1"),
		@NamedQuery(name = "findLabWorkerByLwWorkAge", query = "select myLabWorker from LabWorker myLabWorker where myLabWorker.lwWorkAge = ?1"),
		@NamedQuery(name = "findLabWorkerByLwWorkTime", query = "select myLabWorker from LabWorker myLabWorker where myLabWorker.lwWorkTime = ?1"),
		@NamedQuery(name = "findLabWorkerByPrimaryKey", query = "select myLabWorker from LabWorker myLabWorker where myLabWorker.id = ?1") })
@Table(catalog = "xidlims", name = "lab_worker")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "LabWorker")
public class LabWorker implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 */

	@Column(name = "id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	@XmlElement
	Integer id;
	/**
	 * ��Ա���
	 * 
	 */

	@Column(name = "lw_code_custom", length = 40)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String lwCodeCustom;
	/**
	 * ����
	 * 
	 */

	@Column(name = "lw_name")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String lwName;
	/**
	 * �Ա�1�У�0Ů��
	 * 
	 */

	@Column(name = "lw_sex", length = 10)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String lwSex;
	/**
	 * רҵ
	 * 
	 */

	@Column(name = "lw_major")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String lwMajor;
	/**
	 * ��������
	 * 
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "lw_birthday")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar lwBirthday;
	/**
	 * ��ҵʱ��
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "lw_graduation_time")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar lwGraduationTime;
	/**
	 * ��ҵѧУ
	 * 
	 */

	@Column(name = "lw_graduation_school")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String lwGraduationSchool;
	/**
	 * ��ѧרҵ
	 * 
	 */

	@Column(name = "lw_graduation_major")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String lwGraduationMajor;
	/**
	 * ����ʱ��
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "lw_work_time")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar lwWorkTime;
	/**
	 * ʵ���ҹ���
	 * 
	 */

	@Column(name = "lw_work_age")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer lwWorkAge;
	/**
	 * ְ��
	 * 
	 */

	@Column(name = "lw_duty")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String lwDuty;
	/**
	 * �����ȼ�
	 * 
	 */

	@Column(name = "lw_skill_level")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String lwSkillLevel;
	/**
	 * ��ְʱ��
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "lw_profession_time")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar lwProfessionTime;
	/**
	 * ҵ��ר��
	 * 
	 */

	@Column(name = "lw_profession_specialty")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String lwProfessionSpecialty;
	/**
	 * ��������
	 * 
	 */

	@Column(name = "lw_paper_quantity")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer lwPaperQuantity;
	/**
	 * ר�����
	 * 
	 */

	@Column(name = "lw_category_expert")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String lwCategoryExpert;
	/**
	 * ������ѵʱ��--ѧ�����
	 * 
	 */

	@Column(name = "lw_train_formal_inland_time")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String lwTrainFormalInlandTime;
	/**
	 * ������ѵʱ��--��ѧ�����
	 * 
	 */

	@Column(name = "lw_train_informal_inland_time")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String lwTrainInformalInlandTime;
	/**
	 * ������ѵ����
	 * 
	 */

	@Column(name = "lw_train_informal_inland_content", columnDefinition = "TEXT")
	@Basic(fetch = FetchType.EAGER)
	@Lob
	@XmlElement
	String lwTrainInformalInlandContent;
	/**
	 * ������ѵʱ��--ѧ�����
	 * 
	 */

	@Column(name = "lw_train_formal_abroad_time")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String lwTrainFormalAbroadTime;
	/**
	 * ������ѵʱ��--��ѧ�����
	 * 
	 */

	@Column(name = "lw_train_informal_abroad_time")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String lwTrainInformalAbroadTime;
	/**
	 * ������ѵ����
	 * 
	 */

	@Column(name = "lw_train_informal_abroad_content", columnDefinition = "TEXT")
	@Basic(fetch = FetchType.EAGER)
	@Lob
	@XmlElement
	String lwTrainInformalAbroadContent;
	/**
	 * ��������
	 * 
	 */

	@Column(name = "lw_book_quantity")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer lwBookQuantity;
	/**
	 * ��ʱ��
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "lw_reward_time")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar lwRewardTime;
	/**
	 * ��Ŀ���
	 * 
	 */

	@Column(name = "lw_lab_project")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String lwLabProject;
	/**
	 * ��������1
	 * 
	 */

	@Column(name = "lw_remark_num_one")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String lwRemarkNumOne;
	/**
	 * ��������2
	 * 
	 */

	@Column(name = "lw_remark_num_two")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String lwRemarkNumTwo;
	/**
	 * �����ַ�1
	 * 
	 */

	@Column(name = "lw_remark_one", columnDefinition = "TEXT")
	@Basic(fetch = FetchType.EAGER)
	@Lob
	@XmlElement
	String lwRemarkOne;
	/**
	 * �����ַ�2
	 * 
	 */

	@Column(name = "lw_remark_two", columnDefinition = "TEXT")
	@Basic(fetch = FetchType.EAGER)
	@Lob
	@XmlElement
	String lwRemarkTwo;

	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "lw_main_work", referencedColumnName = "id") })
	@XmlTransient
	CDictionary CDictionaryByLwMainWork;
	/**
	 */
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "lw_foreign_language_level", referencedColumnName = "id") })
	@XmlTransient
	CDictionary CDictionaryByLwForeignLanguageLevel;
	/**
	 */
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "lw_foreign_language", referencedColumnName = "id") })
	@XmlTransient
	CDictionary CDictionaryByLwForeignLanguage;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "lw_lab_center_id", referencedColumnName = "id") })
	@XmlTransient
	LabCenter labCenter;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "lw_username", referencedColumnName = "username") })
	@XmlTransient
	User user;
	/**
	 */
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "lw_specialty_duty", referencedColumnName = "id") })
	@XmlTransient
	CDictionary CDictionaryByLwSpecialtyDuty;
	/**
	 */
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "lw_book_level", referencedColumnName = "id") })
	@XmlTransient
	CDictionary CDictionaryByLwBookLevel;
	/**
	 */
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "lw_employment", referencedColumnName = "id") })
	@XmlTransient
	CDictionary CDictionaryByLwEmployment;
	/**
	 */
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "lw_category_staff", referencedColumnName = "id") })
	@XmlTransient
	CDictionary CDictionaryByLwCategoryStaff;
	/**
	 */
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "lw_degree", referencedColumnName = "id") })
	@XmlTransient
	CDictionary CDictionaryByLwDegree;
	/**
	 */
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "lw_subject", referencedColumnName = "id") })
	@XmlTransient
	CDictionary CDictionaryByLwSubject;
	/**
	 */
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "lw_academic_degree", referencedColumnName = "id") })
	@XmlTransient
	CDictionary CDictionaryByLwAcademicDegree;
	/**
	 */
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "lw_paper_level", referencedColumnName = "id") })
	@XmlTransient
	CDictionary CDictionaryByLwPaperLevel;
	/**
	 */
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "lw_reward", referencedColumnName = "id") })
	@XmlTransient
	CDictionary CDictionaryByLwReward;

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
	 * ��Ա���
	 * 
	 */
	public void setLwCodeCustom(String lwCodeCustom) {
		this.lwCodeCustom = lwCodeCustom;
	}

	/**
	 * ��Ա���
	 * 
	 */
	public String getLwCodeCustom() {
		return this.lwCodeCustom;
	}

	/**
	 * ����
	 * 
	 */
	public void setLwName(String lwName) {
		this.lwName = lwName;
	}

	/**
	 * ����
	 * 
	 */
	public String getLwName() {
		return this.lwName;
	}

	/**
	 * �Ա�1�У�0Ů��
	 * 
	 */
	public void setLwSex(String lwSex) {
		this.lwSex = lwSex;
	}

	/**
	 * �Ա�1�У�0Ů��
	 * 
	 */
	public String getLwSex() {
		return this.lwSex;
	}

	/**
	 * רҵ
	 * 
	 */
	public void setLwMajor(String lwMajor) {
		this.lwMajor = lwMajor;
	}

	/**
	 * רҵ
	 * 
	 */
	public String getLwMajor() {
		return this.lwMajor;
	}

	/**
	 * ��������
	 * 
	 */
	public void setLwBirthday(Calendar lwBirthday) {
		this.lwBirthday = lwBirthday;
	}

	/**
	 * ��������
	 * 
	 */
	public Calendar getLwBirthday() {
		return this.lwBirthday;
	}

	/**
	 * ��ҵʱ��
	 * 
	 */
	public void setLwGraduationTime(Calendar lwGraduationTime) {
		this.lwGraduationTime = lwGraduationTime;
	}

	/**
	 * ��ҵʱ��
	 * 
	 */
	public Calendar getLwGraduationTime() {
		return this.lwGraduationTime;
	}

	/**
	 * ��ҵѧУ
	 * 
	 */
	public void setLwGraduationSchool(String lwGraduationSchool) {
		this.lwGraduationSchool = lwGraduationSchool;
	}

	/**
	 * ��ҵѧУ
	 * 
	 */
	public String getLwGraduationSchool() {
		return this.lwGraduationSchool;
	}

	/**
	 * ��ѧרҵ
	 * 
	 */
	public void setLwGraduationMajor(String lwGraduationMajor) {
		this.lwGraduationMajor = lwGraduationMajor;
	}

	/**
	 * ��ѧרҵ
	 * 
	 */
	public String getLwGraduationMajor() {
		return this.lwGraduationMajor;
	}

	/**
	 * ����ʱ��
	 * 
	 */
	public void setLwWorkTime(Calendar lwWorkTime) {
		this.lwWorkTime = lwWorkTime;
	}

	/**
	 * ����ʱ��
	 * 
	 */
	public Calendar getLwWorkTime() {
		return this.lwWorkTime;
	}

	/**
	 * ʵ���ҹ���
	 * 
	 */
	public void setLwWorkAge(Integer lwWorkAge) {
		this.lwWorkAge = lwWorkAge;
	}

	/**
	 * ʵ���ҹ���
	 * 
	 */
	public Integer getLwWorkAge() {
		return this.lwWorkAge;
	}

	/**
	 * ְ��
	 * 
	 */
	public void setLwDuty(String lwDuty) {
		this.lwDuty = lwDuty;
	}

	/**
	 * ְ��
	 * 
	 */
	public String getLwDuty() {
		return this.lwDuty;
	}

	/**
	 * �����ȼ�
	 * 
	 */
	public void setLwSkillLevel(String lwSkillLevel) {
		this.lwSkillLevel = lwSkillLevel;
	}

	/**
	 * �����ȼ�
	 * 
	 */
	public String getLwSkillLevel() {
		return this.lwSkillLevel;
	}

	/**
	 * ��ְʱ��
	 * 
	 */
	public void setLwProfessionTime(Calendar lwProfessionTime) {
		this.lwProfessionTime = lwProfessionTime;
	}

	/**
	 * ��ְʱ��
	 * 
	 */
	public Calendar getLwProfessionTime() {
		return this.lwProfessionTime;
	}

	/**
	 * ҵ��ר��
	 * 
	 */
	public void setLwProfessionSpecialty(String lwProfessionSpecialty) {
		this.lwProfessionSpecialty = lwProfessionSpecialty;
	}

	/**
	 * ҵ��ר��
	 * 
	 */
	public String getLwProfessionSpecialty() {
		return this.lwProfessionSpecialty;
	}

	/**
	 * ��������
	 * 
	 */
	public void setLwPaperQuantity(Integer lwPaperQuantity) {
		this.lwPaperQuantity = lwPaperQuantity;
	}

	/**
	 * ��������
	 * 
	 */
	public Integer getLwPaperQuantity() {
		return this.lwPaperQuantity;
	}

	/**
	 * ר�����
	 * 
	 */
	public void setLwCategoryExpert(String lwCategoryExpert) {
		this.lwCategoryExpert = lwCategoryExpert;
	}

	/**
	 * ר�����
	 * 
	 */
	public String getLwCategoryExpert() {
		return this.lwCategoryExpert;
	}

	/**
	 * ������ѵʱ��--ѧ�����
	 * 
	 */
	public void setLwTrainFormalInlandTime(String lwTrainFormalInlandTime) {
		this.lwTrainFormalInlandTime = lwTrainFormalInlandTime;
	}

	/**
	 * ������ѵʱ��--ѧ�����
	 * 
	 */
	public String getLwTrainFormalInlandTime() {
		return this.lwTrainFormalInlandTime;
	}

	/**
	 * ������ѵʱ��--��ѧ�����
	 * 
	 */
	public void setLwTrainInformalInlandTime(String lwTrainInformalInlandTime) {
		this.lwTrainInformalInlandTime = lwTrainInformalInlandTime;
	}

	/**
	 * ������ѵʱ��--��ѧ�����
	 * 
	 */
	public String getLwTrainInformalInlandTime() {
		return this.lwTrainInformalInlandTime;
	}

	/**
	 * ������ѵ����
	 * 
	 */
	public void setLwTrainInformalInlandContent(String lwTrainInformalInlandContent) {
		this.lwTrainInformalInlandContent = lwTrainInformalInlandContent;
	}

	/**
	 * ������ѵ����
	 * 
	 */
	public String getLwTrainInformalInlandContent() {
		return this.lwTrainInformalInlandContent;
	}

	/**
	 * ������ѵʱ��--ѧ�����
	 * 
	 */
	public void setLwTrainFormalAbroadTime(String lwTrainFormalAbroadTime) {
		this.lwTrainFormalAbroadTime = lwTrainFormalAbroadTime;
	}

	/**
	 * ������ѵʱ��--ѧ�����
	 * 
	 */
	public String getLwTrainFormalAbroadTime() {
		return this.lwTrainFormalAbroadTime;
	}

	/**
	 * ������ѵʱ��--��ѧ�����
	 * 
	 */
	public void setLwTrainInformalAbroadTime(String lwTrainInformalAbroadTime) {
		this.lwTrainInformalAbroadTime = lwTrainInformalAbroadTime;
	}

	/**
	 * ������ѵʱ��--��ѧ�����
	 * 
	 */
	public String getLwTrainInformalAbroadTime() {
		return this.lwTrainInformalAbroadTime;
	}

	/**
	 * ������ѵ����
	 * 
	 */
	public void setLwTrainInformalAbroadContent(String lwTrainInformalAbroadContent) {
		this.lwTrainInformalAbroadContent = lwTrainInformalAbroadContent;
	}

	/**
	 * ������ѵ����
	 * 
	 */
	public String getLwTrainInformalAbroadContent() {
		return this.lwTrainInformalAbroadContent;
	}

	/**
	 * ��������
	 * 
	 */
	public void setLwBookQuantity(Integer lwBookQuantity) {
		this.lwBookQuantity = lwBookQuantity;
	}

	/**
	 * ��������
	 * 
	 */
	public Integer getLwBookQuantity() {
		return this.lwBookQuantity;
	}

	/**
	 * ��ʱ��
	 * 
	 */
	public void setLwRewardTime(Calendar lwRewardTime) {
		this.lwRewardTime = lwRewardTime;
	}

	/**
	 * ��ʱ��
	 * 
	 */
	public Calendar getLwRewardTime() {
		return this.lwRewardTime;
	}

	/**
	 * ��Ŀ���
	 * 
	 */
	public void setLwLabProject(String lwLabProject) {
		this.lwLabProject = lwLabProject;
	}

	/**
	 * ��Ŀ���
	 * 
	 */
	public String getLwLabProject() {
		return this.lwLabProject;
	}

	/**
	 * ��������1
	 * 
	 */
	public void setLwRemarkNumOne(String lwRemarkNumOne) {
		this.lwRemarkNumOne = lwRemarkNumOne;
	}

	/**
	 * ��������1
	 * 
	 */
	public String getLwRemarkNumOne() {
		return this.lwRemarkNumOne;
	}

	/**
	 * ��������2
	 * 
	 */
	public void setLwRemarkNumTwo(String lwRemarkNumTwo) {
		this.lwRemarkNumTwo = lwRemarkNumTwo;
	}

	/**
	 * ��������2
	 * 
	 */
	public String getLwRemarkNumTwo() {
		return this.lwRemarkNumTwo;
	}

	/**
	 * �����ַ�1
	 * 
	 */
	public void setLwRemarkOne(String lwRemarkOne) {
		this.lwRemarkOne = lwRemarkOne;
	}

	/**
	 * �����ַ�1
	 * 
	 */
	public String getLwRemarkOne() {
		return this.lwRemarkOne;
	}

	/**
	 * �����ַ�2
	 * 
	 */
	public void setLwRemarkTwo(String lwRemarkTwo) {
		this.lwRemarkTwo = lwRemarkTwo;
	}

	/**
	 * �����ַ�2
	 * 
	 */
	public String getLwRemarkTwo() {
		return this.lwRemarkTwo;
	}

	/**
	 */
	public void setCDictionaryByLwMainWork(CDictionary CDictionaryByLwMainWork) {
		this.CDictionaryByLwMainWork = CDictionaryByLwMainWork;
	}

	/**
	 */
	@JsonIgnore
	public CDictionary getCDictionaryByLwMainWork() {
		return CDictionaryByLwMainWork;
	}

	/**
	 */
	public void setCDictionaryByLwForeignLanguageLevel(CDictionary CDictionaryByLwForeignLanguageLevel) {
		this.CDictionaryByLwForeignLanguageLevel = CDictionaryByLwForeignLanguageLevel;
	}

	/**
	 */
	@JsonIgnore
	public CDictionary getCDictionaryByLwForeignLanguageLevel() {
		return CDictionaryByLwForeignLanguageLevel;
	}

	/**
	 */
	public void setCDictionaryByLwForeignLanguage(CDictionary CDictionaryByLwForeignLanguage) {
		this.CDictionaryByLwForeignLanguage = CDictionaryByLwForeignLanguage;
	}

	/**
	 */
	@JsonIgnore
	public CDictionary getCDictionaryByLwForeignLanguage() {
		return CDictionaryByLwForeignLanguage;
	}

	/**
	 */
	public void setLabCenter(LabCenter labCenter) {
		this.labCenter = labCenter;
	}

	/**
	 */
	@JsonIgnore
	public LabCenter getLabCenter() {
		return labCenter;
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
	public void setCDictionaryByLwSpecialtyDuty(CDictionary CDictionaryByLwSpecialtyDuty) {
		this.CDictionaryByLwSpecialtyDuty = CDictionaryByLwSpecialtyDuty;
	}

	/**
	 */
	@JsonIgnore
	public CDictionary getCDictionaryByLwSpecialtyDuty() {
		return CDictionaryByLwSpecialtyDuty;
	}

	/**
	 */
	public void setCDictionaryByLwBookLevel(CDictionary CDictionaryByLwBookLevel) {
		this.CDictionaryByLwBookLevel = CDictionaryByLwBookLevel;
	}

	/**
	 */
	@JsonIgnore
	public CDictionary getCDictionaryByLwBookLevel() {
		return CDictionaryByLwBookLevel;
	}

	/**
	 */
	public void setCDictionaryByLwEmployment(CDictionary CDictionaryByLwEmployment) {
		this.CDictionaryByLwEmployment = CDictionaryByLwEmployment;
	}

	/**
	 */
	@JsonIgnore
	public CDictionary getCDictionaryByLwEmployment() {
		return CDictionaryByLwEmployment;
	}

	/**
	 */
	public void setCDictionaryByLwCategoryStaff(CDictionary CDictionaryByLwCategoryStaff) {
		this.CDictionaryByLwCategoryStaff = CDictionaryByLwCategoryStaff;
	}

	/**
	 */
	@JsonIgnore
	public CDictionary getCDictionaryByLwCategoryStaff() {
		return CDictionaryByLwCategoryStaff;
	}

	/**
	 */
	public void setCDictionaryByLwDegree(CDictionary CDictionaryByLwDegree) {
		this.CDictionaryByLwDegree = CDictionaryByLwDegree;
	}

	/**
	 */
	@JsonIgnore
	public CDictionary getCDictionaryByLwDegree() {
		return CDictionaryByLwDegree;
	}

	/**
	 */
	public void setCDictionaryByLwSubject(CDictionary CDictionaryByLwSubject) {
		this.CDictionaryByLwSubject = CDictionaryByLwSubject;
	}

	/**
	 */
	@JsonIgnore
	public CDictionary getCDictionaryByLwSubject() {
		return CDictionaryByLwSubject;
	}

	/**
	 */
	public void setCDictionaryByLwAcademicDegree(CDictionary CDictionaryByLwAcademicDegree) {
		this.CDictionaryByLwAcademicDegree = CDictionaryByLwAcademicDegree;
	}

	/**
	 */
	@JsonIgnore
	public CDictionary getCDictionaryByLwAcademicDegree() {
		return CDictionaryByLwAcademicDegree;
	}

	/**
	 */
	public void setCDictionaryByLwPaperLevel(CDictionary CDictionaryByLwPaperLevel) {
		this.CDictionaryByLwPaperLevel = CDictionaryByLwPaperLevel;
	}

	/**
	 */
	@JsonIgnore
	public CDictionary getCDictionaryByLwPaperLevel() {
		return CDictionaryByLwPaperLevel;
	}

	/**
	 */
	public void setCDictionaryByLwReward(CDictionary CDictionaryByLwReward) {
		this.CDictionaryByLwReward = CDictionaryByLwReward;
	}

	/**
	 */
	@JsonIgnore
	public CDictionary getCDictionaryByLwReward() {
		return CDictionaryByLwReward;
	}

	/**
	 */
	public LabWorker() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(LabWorker that) {
		setId(that.getId());
		setLwCodeCustom(that.getLwCodeCustom());
		setLwName(that.getLwName());
		setLwSex(that.getLwSex());
		setLwMajor(that.getLwMajor());
		setLwBirthday(that.getLwBirthday());
		setLwGraduationTime(that.getLwGraduationTime());
		setLwGraduationSchool(that.getLwGraduationSchool());
		setLwGraduationMajor(that.getLwGraduationMajor());
		setLwWorkTime(that.getLwWorkTime());
		setLwWorkAge(that.getLwWorkAge());
		setLwDuty(that.getLwDuty());
		setLwSkillLevel(that.getLwSkillLevel());
		setLwProfessionTime(that.getLwProfessionTime());
		setLwProfessionSpecialty(that.getLwProfessionSpecialty());
		setLwPaperQuantity(that.getLwPaperQuantity());
		setLwCategoryExpert(that.getLwCategoryExpert());
		setLwTrainFormalInlandTime(that.getLwTrainFormalInlandTime());
		setLwTrainInformalInlandTime(that.getLwTrainInformalInlandTime());
		setLwTrainInformalInlandContent(that.getLwTrainInformalInlandContent());
		setLwTrainFormalAbroadTime(that.getLwTrainFormalAbroadTime());
		setLwTrainInformalAbroadTime(that.getLwTrainInformalAbroadTime());
		setLwTrainInformalAbroadContent(that.getLwTrainInformalAbroadContent());
		setLwBookQuantity(that.getLwBookQuantity());
		setLwRewardTime(that.getLwRewardTime());
		setLwLabProject(that.getLwLabProject());
		setLwRemarkNumOne(that.getLwRemarkNumOne());
		setLwRemarkNumTwo(that.getLwRemarkNumTwo());
		setLwRemarkOne(that.getLwRemarkOne());
		setLwRemarkTwo(that.getLwRemarkTwo());
		setCDictionaryByLwMainWork(that.getCDictionaryByLwMainWork());
		setCDictionaryByLwForeignLanguageLevel(that.getCDictionaryByLwForeignLanguageLevel());
		setCDictionaryByLwForeignLanguage(that.getCDictionaryByLwForeignLanguage());
		setLabCenter(that.getLabCenter());
		setCDictionaryByLwSpecialtyDuty(that.getCDictionaryByLwSpecialtyDuty());
		setCDictionaryByLwBookLevel(that.getCDictionaryByLwBookLevel());
		setCDictionaryByLwEmployment(that.getCDictionaryByLwEmployment());
		setCDictionaryByLwCategoryStaff(that.getCDictionaryByLwCategoryStaff());
		setCDictionaryByLwDegree(that.getCDictionaryByLwDegree());
		setCDictionaryByLwSubject(that.getCDictionaryByLwSubject());
		setCDictionaryByLwAcademicDegree(that.getCDictionaryByLwAcademicDegree());
		setCDictionaryByLwPaperLevel(that.getCDictionaryByLwPaperLevel());
		setCDictionaryByLwReward(that.getCDictionaryByLwReward());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("id=[").append(id).append("] ");
		buffer.append("lwCodeCustom=[").append(lwCodeCustom).append("] ");
		buffer.append("lwName=[").append(lwName).append("] ");
		buffer.append("lwSex=[").append(lwSex).append("] ");
		buffer.append("lwMajor=[").append(lwMajor).append("] ");
		buffer.append("lwBirthday=[").append(lwBirthday).append("] ");
		buffer.append("lwGraduationTime=[").append(lwGraduationTime).append("] ");
		buffer.append("lwGraduationSchool=[").append(lwGraduationSchool).append("] ");
		buffer.append("lwGraduationMajor=[").append(lwGraduationMajor).append("] ");
		buffer.append("lwWorkTime=[").append(lwWorkTime).append("] ");
		buffer.append("lwWorkAge=[").append(lwWorkAge).append("] ");
		buffer.append("lwDuty=[").append(lwDuty).append("] ");
		buffer.append("lwSkillLevel=[").append(lwSkillLevel).append("] ");
		buffer.append("lwProfessionTime=[").append(lwProfessionTime).append("] ");
		buffer.append("lwProfessionSpecialty=[").append(lwProfessionSpecialty).append("] ");
		buffer.append("lwPaperQuantity=[").append(lwPaperQuantity).append("] ");
		buffer.append("lwCategoryExpert=[").append(lwCategoryExpert).append("] ");
		buffer.append("lwTrainFormalInlandTime=[").append(lwTrainFormalInlandTime).append("] ");
		buffer.append("lwTrainInformalInlandTime=[").append(lwTrainInformalInlandTime).append("] ");
		buffer.append("lwTrainInformalInlandContent=[").append(lwTrainInformalInlandContent).append("] ");
		buffer.append("lwTrainFormalAbroadTime=[").append(lwTrainFormalAbroadTime).append("] ");
		buffer.append("lwTrainInformalAbroadTime=[").append(lwTrainInformalAbroadTime).append("] ");
		buffer.append("lwTrainInformalAbroadContent=[").append(lwTrainInformalAbroadContent).append("] ");
		buffer.append("lwBookQuantity=[").append(lwBookQuantity).append("] ");
		buffer.append("lwRewardTime=[").append(lwRewardTime).append("] ");
		buffer.append("lwLabProject=[").append(lwLabProject).append("] ");
		buffer.append("lwRemarkNumOne=[").append(lwRemarkNumOne).append("] ");
		buffer.append("lwRemarkNumTwo=[").append(lwRemarkNumTwo).append("] ");
		buffer.append("lwRemarkOne=[").append(lwRemarkOne).append("] ");
		buffer.append("lwRemarkTwo=[").append(lwRemarkTwo).append("] ");

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
		if (!(obj instanceof LabWorker))
			return false;
		LabWorker equalCheck = (LabWorker) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
