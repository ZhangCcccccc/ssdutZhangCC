package net.xidlims.domain;

import java.io.Serializable;

import java.lang.StringBuilder;

import java.util.Calendar;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.codehaus.jackson.annotate.JsonIgnore;

import javax.xml.bind.annotation.*;

import javax.persistence.*;

/**
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "findAllSchoolCourseDetails", query = "select mySchoolCourseDetail from SchoolCourseDetail mySchoolCourseDetail"),
		@NamedQuery(name = "findSchoolCourseDetailByClassesExperiment", query = "select mySchoolCourseDetail from SchoolCourseDetail mySchoolCourseDetail where mySchoolCourseDetail.classesExperiment = ?1"),
		@NamedQuery(name = "findSchoolCourseDetailByClassesExperimentContaining", query = "select mySchoolCourseDetail from SchoolCourseDetail mySchoolCourseDetail where mySchoolCourseDetail.classesExperiment like ?1"),
		@NamedQuery(name = "findSchoolCourseDetailByClassroomType", query = "select mySchoolCourseDetail from SchoolCourseDetail mySchoolCourseDetail where mySchoolCourseDetail.classroomType = ?1"),
		@NamedQuery(name = "findSchoolCourseDetailByClassroomTypeContaining", query = "select mySchoolCourseDetail from SchoolCourseDetail mySchoolCourseDetail where mySchoolCourseDetail.classroomType like ?1"),
		@NamedQuery(name = "findSchoolCourseDetailByClasstime", query = "select mySchoolCourseDetail from SchoolCourseDetail mySchoolCourseDetail where mySchoolCourseDetail.classtime = ?1"),
		@NamedQuery(name = "findSchoolCourseDetailByClasstimeContaining", query = "select mySchoolCourseDetail from SchoolCourseDetail mySchoolCourseDetail where mySchoolCourseDetail.classtime like ?1"),
		@NamedQuery(name = "findSchoolCourseDetailByComputeTime", query = "select mySchoolCourseDetail from SchoolCourseDetail mySchoolCourseDetail where mySchoolCourseDetail.computeTime = ?1"),
		@NamedQuery(name = "findSchoolCourseDetailByComputeTimeContaining", query = "select mySchoolCourseDetail from SchoolCourseDetail mySchoolCourseDetail where mySchoolCourseDetail.computeTime like ?1"),
		@NamedQuery(name = "findSchoolCourseDetailByCourseDetailNo", query = "select mySchoolCourseDetail from SchoolCourseDetail mySchoolCourseDetail where mySchoolCourseDetail.courseDetailNo = ?1"),
		@NamedQuery(name = "findSchoolCourseDetailByCourseDetailNoContaining", query = "select mySchoolCourseDetail from SchoolCourseDetail mySchoolCourseDetail where mySchoolCourseDetail.courseDetailNo like ?1"),
		@NamedQuery(name = "findSchoolCourseDetailByCourseName", query = "select mySchoolCourseDetail from SchoolCourseDetail mySchoolCourseDetail where mySchoolCourseDetail.courseName = ?1"),
		@NamedQuery(name = "findSchoolCourseDetailByCourseNameContaining", query = "select mySchoolCourseDetail from SchoolCourseDetail mySchoolCourseDetail where mySchoolCourseDetail.courseName like ?1"),
		@NamedQuery(name = "findSchoolCourseDetailByCourseNumber", query = "select mySchoolCourseDetail from SchoolCourseDetail mySchoolCourseDetail where mySchoolCourseDetail.courseNumber = ?1"),
		@NamedQuery(name = "findSchoolCourseDetailByCourseNumberContaining", query = "select mySchoolCourseDetail from SchoolCourseDetail mySchoolCourseDetail where mySchoolCourseDetail.courseNumber like ?1"),
		@NamedQuery(name = "findSchoolCourseDetailByCourseType", query = "select mySchoolCourseDetail from SchoolCourseDetail mySchoolCourseDetail where mySchoolCourseDetail.courseType = ?1"),
		@NamedQuery(name = "findSchoolCourseDetailByCourseTypeContaining", query = "select mySchoolCourseDetail from SchoolCourseDetail mySchoolCourseDetail where mySchoolCourseDetail.courseType like ?1"),
		@NamedQuery(name = "findSchoolCourseDetailByCourseTypeName", query = "select mySchoolCourseDetail from SchoolCourseDetail mySchoolCourseDetail where mySchoolCourseDetail.courseTypeName = ?1"),
		@NamedQuery(name = "findSchoolCourseDetailByCourseTypeNameContaining", query = "select mySchoolCourseDetail from SchoolCourseDetail mySchoolCourseDetail where mySchoolCourseDetail.courseTypeName like ?1"),
		@NamedQuery(name = "findSchoolCourseDetailByDetailId", query = "select mySchoolCourseDetail from SchoolCourseDetail mySchoolCourseDetail where mySchoolCourseDetail.detailId = ?1"),
		@NamedQuery(name = "findSchoolCourseDetailByEndClass", query = "select mySchoolCourseDetail from SchoolCourseDetail mySchoolCourseDetail where mySchoolCourseDetail.endClass = ?1"),
		@NamedQuery(name = "findSchoolCourseDetailByEndWeek", query = "select mySchoolCourseDetail from SchoolCourseDetail mySchoolCourseDetail where mySchoolCourseDetail.endWeek = ?1"),
		@NamedQuery(name = "findSchoolCourseDetailByEnvironmentalRequirements", query = "select mySchoolCourseDetail from SchoolCourseDetail mySchoolCourseDetail where mySchoolCourseDetail.environmentalRequirements = ?1"),
		@NamedQuery(name = "findSchoolCourseDetailByEnvironmentalRequirementsContaining", query = "select mySchoolCourseDetail from SchoolCourseDetail mySchoolCourseDetail where mySchoolCourseDetail.environmentalRequirements like ?1"),
		@NamedQuery(name = "findSchoolCourseDetailByEquipmentConfiguration", query = "select mySchoolCourseDetail from SchoolCourseDetail mySchoolCourseDetail where mySchoolCourseDetail.equipmentConfiguration = ?1"),
		@NamedQuery(name = "findSchoolCourseDetailByEquipmentConfigurationContaining", query = "select mySchoolCourseDetail from SchoolCourseDetail mySchoolCourseDetail where mySchoolCourseDetail.equipmentConfiguration like ?1"),
		@NamedQuery(name = "findSchoolCourseDetailByEvaluationMode", query = "select mySchoolCourseDetail from SchoolCourseDetail mySchoolCourseDetail where mySchoolCourseDetail.evaluationMode = ?1"),
		@NamedQuery(name = "findSchoolCourseDetailByEvaluationModeContaining", query = "select mySchoolCourseDetail from SchoolCourseDetail mySchoolCourseDetail where mySchoolCourseDetail.evaluationMode like ?1"),
		@NamedQuery(name = "findSchoolCourseDetailByEvaluationModeName", query = "select mySchoolCourseDetail from SchoolCourseDetail mySchoolCourseDetail where mySchoolCourseDetail.evaluationModeName = ?1"),
		@NamedQuery(name = "findSchoolCourseDetailByEvaluationModeNameContaining", query = "select mySchoolCourseDetail from SchoolCourseDetail mySchoolCourseDetail where mySchoolCourseDetail.evaluationModeName like ?1"),
		@NamedQuery(name = "findSchoolCourseDetailByExperimentalClassHour", query = "select mySchoolCourseDetail from SchoolCourseDetail mySchoolCourseDetail where mySchoolCourseDetail.experimentalClassHour = ?1"),
		@NamedQuery(name = "findSchoolCourseDetailByExperimentalClassHourContaining", query = "select mySchoolCourseDetail from SchoolCourseDetail mySchoolCourseDetail where mySchoolCourseDetail.experimentalClassHour like ?1"),
		@NamedQuery(name = "findSchoolCourseDetailByExperimentalProportion", query = "select mySchoolCourseDetail from SchoolCourseDetail mySchoolCourseDetail where mySchoolCourseDetail.experimentalProportion = ?1"),
		@NamedQuery(name = "findSchoolCourseDetailByExperimentalProportionContaining", query = "select mySchoolCourseDetail from SchoolCourseDetail mySchoolCourseDetail where mySchoolCourseDetail.experimentalProportion like ?1"),
		@NamedQuery(name = "findSchoolCourseDetailById", query = "select mySchoolCourseDetail from SchoolCourseDetail mySchoolCourseDetail where mySchoolCourseDetail.id = ?1"),
		@NamedQuery(name = "findSchoolCourseDetailByIfAllowCourse", query = "select mySchoolCourseDetail from SchoolCourseDetail mySchoolCourseDetail where mySchoolCourseDetail.ifAllowCourse = ?1"),
		@NamedQuery(name = "findSchoolCourseDetailByIfAppointment", query = "select mySchoolCourseDetail from SchoolCourseDetail mySchoolCourseDetail where mySchoolCourseDetail.ifAppointment = ?1"),
		@NamedQuery(name = "findSchoolCourseDetailByIfArrange", query = "select mySchoolCourseDetail from SchoolCourseDetail mySchoolCourseDetail where mySchoolCourseDetail.ifArrange = ?1"),
		@NamedQuery(name = "findSchoolCourseDetailByIfFlop", query = "select mySchoolCourseDetail from SchoolCourseDetail mySchoolCourseDetail where mySchoolCourseDetail.ifFlop = ?1"),
		@NamedQuery(name = "findSchoolCourseDetailByIfSelect", query = "select mySchoolCourseDetail from SchoolCourseDetail mySchoolCourseDetail where mySchoolCourseDetail.ifSelect = ?1"),
		@NamedQuery(name = "findSchoolCourseDetailByIfTemporary", query = "select mySchoolCourseDetail from SchoolCourseDetail mySchoolCourseDetail where mySchoolCourseDetail.ifTemporary = ?1"),
		@NamedQuery(name = "findSchoolCourseDetailByInstructionType", query = "select mySchoolCourseDetail from SchoolCourseDetail mySchoolCourseDetail where mySchoolCourseDetail.instructionType = ?1"),
		@NamedQuery(name = "findSchoolCourseDetailByInstructionTypeContaining", query = "select mySchoolCourseDetail from SchoolCourseDetail mySchoolCourseDetail where mySchoolCourseDetail.instructionType like ?1"),
		@NamedQuery(name = "findSchoolCourseDetailByInstructionTypeName", query = "select mySchoolCourseDetail from SchoolCourseDetail mySchoolCourseDetail where mySchoolCourseDetail.instructionTypeName = ?1"),
		@NamedQuery(name = "findSchoolCourseDetailByInstructionTypeNameContaining", query = "select mySchoolCourseDetail from SchoolCourseDetail mySchoolCourseDetail where mySchoolCourseDetail.instructionTypeName like ?1"),
		@NamedQuery(name = "findSchoolCourseDetailByMajorDirectionName", query = "select mySchoolCourseDetail from SchoolCourseDetail mySchoolCourseDetail where mySchoolCourseDetail.majorDirectionName = ?1"),
		@NamedQuery(name = "findSchoolCourseDetailByMajorDirectionNameContaining", query = "select mySchoolCourseDetail from SchoolCourseDetail mySchoolCourseDetail where mySchoolCourseDetail.majorDirectionName like ?1"),
		@NamedQuery(name = "findSchoolCourseDetailByMajorName", query = "select mySchoolCourseDetail from SchoolCourseDetail mySchoolCourseDetail where mySchoolCourseDetail.majorName = ?1"),
		@NamedQuery(name = "findSchoolCourseDetailByMajorNameContaining", query = "select mySchoolCourseDetail from SchoolCourseDetail mySchoolCourseDetail where mySchoolCourseDetail.majorName like ?1"),
		@NamedQuery(name = "findSchoolCourseDetailByMaxStudents", query = "select mySchoolCourseDetail from SchoolCourseDetail mySchoolCourseDetail where mySchoolCourseDetail.maxStudents = ?1"),
		@NamedQuery(name = "findSchoolCourseDetailByMaxStudentsContaining", query = "select mySchoolCourseDetail from SchoolCourseDetail mySchoolCourseDetail where mySchoolCourseDetail.maxStudents like ?1"),
		@NamedQuery(name = "findSchoolCourseDetailByMinStudents", query = "select mySchoolCourseDetail from SchoolCourseDetail mySchoolCourseDetail where mySchoolCourseDetail.minStudents = ?1"),
		@NamedQuery(name = "findSchoolCourseDetailByMinStudentsContaining", query = "select mySchoolCourseDetail from SchoolCourseDetail mySchoolCourseDetail where mySchoolCourseDetail.minStudents like ?1"),
		@NamedQuery(name = "findSchoolCourseDetailByNeedId", query = "select mySchoolCourseDetail from SchoolCourseDetail mySchoolCourseDetail where mySchoolCourseDetail.needId = ?1"),
		@NamedQuery(name = "findSchoolCourseDetailByPlanStudentNumber", query = "select mySchoolCourseDetail from SchoolCourseDetail mySchoolCourseDetail where mySchoolCourseDetail.planStudentNumber = ?1"),
		@NamedQuery(name = "findSchoolCourseDetailByPlanStudentNumberContaining", query = "select mySchoolCourseDetail from SchoolCourseDetail mySchoolCourseDetail where mySchoolCourseDetail.planStudentNumber like ?1"),
		@NamedQuery(name = "findSchoolCourseDetailByPrimaryKey", query = "select mySchoolCourseDetail from SchoolCourseDetail mySchoolCourseDetail where mySchoolCourseDetail.courseDetailNo = ?1"),
		@NamedQuery(name = "findSchoolCourseDetailByRemainingArrange", query = "select mySchoolCourseDetail from SchoolCourseDetail mySchoolCourseDetail where mySchoolCourseDetail.remainingArrange = ?1"),
		@NamedQuery(name = "findSchoolCourseDetailByRemainingArrangeContaining", query = "select mySchoolCourseDetail from SchoolCourseDetail mySchoolCourseDetail where mySchoolCourseDetail.remainingArrange like ?1"),
		@NamedQuery(name = "findSchoolCourseDetailByRequiredSoftware", query = "select mySchoolCourseDetail from SchoolCourseDetail mySchoolCourseDetail where mySchoolCourseDetail.requiredSoftware = ?1"),
		@NamedQuery(name = "findSchoolCourseDetailByRequiredSoftwareContaining", query = "select mySchoolCourseDetail from SchoolCourseDetail mySchoolCourseDetail where mySchoolCourseDetail.requiredSoftware like ?1"),
		@NamedQuery(name = "findSchoolCourseDetailByStartClass", query = "select mySchoolCourseDetail from SchoolCourseDetail mySchoolCourseDetail where mySchoolCourseDetail.startClass = ?1"),
		@NamedQuery(name = "findSchoolCourseDetailByStartWeek", query = "select mySchoolCourseDetail from SchoolCourseDetail mySchoolCourseDetail where mySchoolCourseDetail.startWeek = ?1"),
		@NamedQuery(name = "findSchoolCourseDetailByState", query = "select mySchoolCourseDetail from SchoolCourseDetail mySchoolCourseDetail where mySchoolCourseDetail.state = ?1"),
		@NamedQuery(name = "findSchoolCourseDetailByTotalClassHour", query = "select mySchoolCourseDetail from SchoolCourseDetail mySchoolCourseDetail where mySchoolCourseDetail.totalClassHour = ?1"),
		@NamedQuery(name = "findSchoolCourseDetailByTotalClassHourContaining", query = "select mySchoolCourseDetail from SchoolCourseDetail mySchoolCourseDetail where mySchoolCourseDetail.totalClassHour like ?1"),
		@NamedQuery(name = "findSchoolCourseDetailByTotalClasses", query = "select mySchoolCourseDetail from SchoolCourseDetail mySchoolCourseDetail where mySchoolCourseDetail.totalClasses = ?1"),
		@NamedQuery(name = "findSchoolCourseDetailByTotalWeeks", query = "select mySchoolCourseDetail from SchoolCourseDetail mySchoolCourseDetail where mySchoolCourseDetail.totalWeeks = ?1"),
		@NamedQuery(name = "findSchoolCourseDetailByTotalWeeksContaining", query = "select mySchoolCourseDetail from SchoolCourseDetail mySchoolCourseDetail where mySchoolCourseDetail.totalWeeks like ?1"),
		@NamedQuery(name = "findSchoolCourseDetailByTutor", query = "select mySchoolCourseDetail from SchoolCourseDetail mySchoolCourseDetail where mySchoolCourseDetail.tutor = ?1"),
		@NamedQuery(name = "findSchoolCourseDetailByTutorContaining", query = "select mySchoolCourseDetail from SchoolCourseDetail mySchoolCourseDetail where mySchoolCourseDetail.tutor like ?1"),
		@NamedQuery(name = "findSchoolCourseDetailByUseweek", query = "select mySchoolCourseDetail from SchoolCourseDetail mySchoolCourseDetail where mySchoolCourseDetail.useweek = ?1"),
		@NamedQuery(name = "findSchoolCourseDetailByUseweekContaining", query = "select mySchoolCourseDetail from SchoolCourseDetail mySchoolCourseDetail where mySchoolCourseDetail.useweek like ?1"),
		@NamedQuery(name = "findSchoolCourseDetailByWeekClassHour", query = "select mySchoolCourseDetail from SchoolCourseDetail mySchoolCourseDetail where mySchoolCourseDetail.weekClassHour = ?1"),
		@NamedQuery(name = "findSchoolCourseDetailByWeekClassHourContaining", query = "select mySchoolCourseDetail from SchoolCourseDetail mySchoolCourseDetail where mySchoolCourseDetail.weekClassHour like ?1"),
		@NamedQuery(name = "findSchoolCourseDetailByWeekType", query = "select mySchoolCourseDetail from SchoolCourseDetail mySchoolCourseDetail where mySchoolCourseDetail.weekType = ?1"),
		@NamedQuery(name = "findSchoolCourseDetailByWeekTypeContaining", query = "select mySchoolCourseDetail from SchoolCourseDetail mySchoolCourseDetail where mySchoolCourseDetail.weekType like ?1"),
		@NamedQuery(name = "findSchoolCourseDetailByWeekday", query = "select mySchoolCourseDetail from SchoolCourseDetail mySchoolCourseDetail where mySchoolCourseDetail.weekday = ?1"),
		@NamedQuery(name = "findSchoolCourseDetailByWeeksExperiment", query = "select mySchoolCourseDetail from SchoolCourseDetail mySchoolCourseDetail where mySchoolCourseDetail.weeksExperiment = ?1"),
		@NamedQuery(name = "findSchoolCourseDetailByWeeksExperimentContaining", query = "select mySchoolCourseDetail from SchoolCourseDetail mySchoolCourseDetail where mySchoolCourseDetail.weeksExperiment like ?1") })
@Table(catalog = "xidlims", name = "school_course_detail")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "xidlims/net/xidlims/domain", name = "SchoolCourseDetail")
@XmlRootElement(namespace = "xidlims/net/xidlims/domain")
public class SchoolCourseDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ��ɺ�Ŀγ������ţ�Ψһ�ԣ�
	 * 
	 */

	@Column(name = "course_detail_no", length = 100, nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	String courseDetailNo;
	/**
	 */

	@Column(name = "id")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer id;
	/**
	 * �γ����
	 * 
	 */

	@Column(name = "course_number", length = 40)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String courseNumber;
	/**
	 * רҵ�������
	 * 
	 */

	@Column(name = "major_direction_name", length = 100)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String majorDirectionName;
	/**
	 * רҵ���
	 * 
	 */

	@Column(name = "major_name", length = 100)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String majorName;
	/**
	 * �ƻ�����
	 * 
	 */

	@Column(name = "plan_student_number", length = 10)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String planStudentNumber;
	/**
	 * ����
	 * 
	 */

	@Column(name = "total_weeks", length = 10)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String totalWeeks;
	/**
	 * ���ڼ�
	 * 
	 */

	@Column(name = "weekday")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer weekday;
	/**
	 * ��ʼ��
	 * 
	 */

	@Column(name = "start_week")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer startWeek;
	/**
	 * ������
	 * 
	 */

	@Column(name = "end_week")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer endWeek;
	/**
	 * ����
	 * 
	 */

	@Column(name = "total_classes")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer totalClasses;
	/**
	 * ��ʼ�ڴ�
	 * 
	 */

	@Column(name = "start_class")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer startClass;
	/**
	 * �����
	 * 
	 */

	@Column(name = "end_class")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer endClass;
	/**
	 * ָ����ʦ��Ӧ���û�
	 * 
	 */

	@Column(name = "tutor", length = 40)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String tutor;
	/**
	 * �ܿ�ʱ
	 * 
	 */

	@Column(name = "total_class_hour", length = 10)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String totalClassHour;
	/**
	 * �ܿ�ʱ
	 * 
	 */

	@Column(name = "week_class_hour", length = 10)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String weekClassHour;
	/**
	 * ʵ����ѧʱ
	 * 
	 */

	@Column(name = "experimental_class_hour", length = 40)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String experimentalClassHour;
	/**
	 * ʵ�����
	 * 
	 */

	@Column(name = "experimental_proportion", length = 10)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String experimentalProportion;
	/**
	 * �Ƿ�����
	 * 
	 */

	@Column(name = "if_arrange")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Boolean ifArrange;
	/**
	 * ��������
	 * 
	 */

	@Column(name = "max_students", length = 40)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String maxStudents;
	/**
	 * ��������
	 * 
	 */

	@Column(name = "min_students", length = 40)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String minStudents;
	/**
	 * �����豸����
	 * 
	 */

	@Column(name = "equipment_configuration", length = 20)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String equipmentConfiguration;
	/**
	 * ����������
	 * 
	 */

	@Column(name = "classroom_type", length = 100)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String classroomType;
	/**
	 * �Ƿ��ѡ
	 * 
	 */

	@Column(name = "if_select")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Boolean ifSelect;
	/**
	 * �Ƿ����
	 * 
	 */

	@Column(name = "if_flop")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Boolean ifFlop;
	/**
	 * �γ�����
	 * 
	 */

	@Column(name = "course_type", length = 20)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String courseType;
	/**
	 * �γ��������
	 * 
	 */

	@Column(name = "course_type_name", length = 100)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String courseTypeName;
	/**
	 * ���˷�ʽ
	 * 
	 */

	@Column(name = "evaluation_mode", length = 40)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String evaluationMode;
	/**
	 * ���˷�ʽ���
	 * 
	 */

	@Column(name = "evaluation_mode_name", length = 100)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String evaluationModeName;
	/**
	 * �ڿ���������
	 * 
	 */

	@Column(name = "instruction_type", length = 10)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String instructionType;
	/**
	 * �ڿ������������
	 * 
	 */

	@Column(name = "instruction_type_name", length = 40)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String instructionTypeName;
	/**
	 * �Ƿ������˿�
	 * 
	 */

	@Column(name = "if_allow_course")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Boolean ifAllowCourse;
	/**
	 * ����Ҫ��
	 * 
	 */

	@Column(name = "environmental_requirements", length = 200)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String environmentalRequirements;
	/**
	 * �ϻ�ʱ��
	 * 
	 */

	@Column(name = "compute_time", length = 200)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String computeTime;
	/**
	 * �ϻ��������
	 * 
	 */

	@Column(name = "required_software", length = 200)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String requiredSoftware;
	/**
	 * ������
	 * 
	 */

	@Column(name = "week_type", length = 40)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String weekType;
	/**
	 * �Ƿ�Ϊ��ʱ�γ�
	 * 
	 */

	@Column(name = "if_temporary")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Boolean ifTemporary;
	/**
	 * ��صĽ�ʦ����
	 * 
	 */

	@Column(name = "need_id")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer needId;
	/**
	 * �ογ��Ƿ��Ѿ�ԤԼ��
	 * 
	 */

	@Column(name = "if_appointment")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Boolean ifAppointment;
	/**
	 * ʣ��Ŀγ̰���
	 * 
	 */

	@Column(name = "remaining_arrange")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String remainingArrange;
	/**
	 * �γ����
	 * 
	 */

	@Column(name = "course_name", length = 200)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String courseName;
	/**
	 * ʵ���ϻ���ܴ�
	 * 
	 */

	@Column(name = "weeks_experiment")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String weeksExperiment;
	/**
	 * ʵ���ϻ�Ľڴ�
	 * 
	 */

	@Column(name = "classes_experiment")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String classesExperiment;
	/**
	 * �����γ�
	 * 
	 */

	@Column(name = "detail_id")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer detailId;
	/**
	 */

	@Column(name = "useweek", length = 30)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String useweek;
	/**
	 */

	@Column(name = "classtime", length = 10)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String classtime;
	/**
	 * ״̬
	 * 
	 */

	@Column(name = "state")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer state;

	/**
	 * ����ʱ��
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "course_start_time")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar courseStartTime;

	/**
	 * ״̬
	 * 
	 */

	@Column(name = "course_end_minute")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer courseEndMinute;

	/**
	 * ״̬
	 * 
	 */

	@Column(name = "withdrawal_minute")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer withdrawalMinute;

	/**
	 * ״̬
	 * 
	 */

	@Column(name = "withdrawal_times")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer withdrawalTimes;

	/**
	 * ��ע
	 * 
	 */

	@Column(name = "course_requirement", columnDefinition = "TEXT")
	@Basic(fetch = FetchType.EAGER)
	@Lob
	@XmlElement
	String courseRequirement;

	/**
	 * ����ʱ��
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "course_start")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar courseStart;

	/**
	 * ����ʱ��
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "course_end")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar courseEnd;

	@Column(name = "is_complete")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer isComplete;

	/**
	 * ״̬
	 * 
	 */

	@Column(name = "is_schedule")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer isSchedule;
	/**
	 * ״̬
	 * 
	 */
	@Column(name = "experiment_period")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer experimentPeriod;
	
	
	@Column(name = "theory_hour")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer theoryHour;

	@Column(name = "course_category", length = 40)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String courseCategory;

	/**
	 * ״̬
	 * 
	 */
	public Integer getExperimentPeriod() {
		return experimentPeriod;
	}

	public void setExperimentPeriod(Integer experimentPeriod) {
		this.experimentPeriod = experimentPeriod;
	}

	/**
	 * @return the theoryHour
	 */
	public Integer getTheoryHour() {
		return theoryHour;
	}

	/**
	 * @param theoryHour the theoryHour to set
	 */
	public void setTheoryHour(Integer theoryHour) {
		this.theoryHour = theoryHour;
	}

	/**
	 * @return the courseCategory
	 */
	public String getCourseCategory() {
		return courseCategory;
	}

	/**
	 * @param courseCategory
	 *            the courseCategory to set
	 */
	public void setCourseCategory(String courseCategory) {
		this.courseCategory = courseCategory;
	}

	/**
	 * ״̬
	 * 
	 */
	public Integer getIsSchedule() {
		return isSchedule;
	}

	public void setIsSchedule(Integer isSchedule) {
		this.isSchedule = isSchedule;
	}

	public Integer getIsComplete() {
		return isComplete;
	}

	public void setIsComplete(Integer isComplete) {
		this.isComplete = isComplete;
	}

	/**
	 */
	@OneToMany(mappedBy = "schoolCourseDetail", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.LabRoomCourseCapacity> labRoomCourseCapacities;

	/**
	 */
	public void setLabRoomCourseCapacities(
			Set<LabRoomCourseCapacity> labRoomCourseCapacities) {
		this.labRoomCourseCapacities = labRoomCourseCapacities;
	}

	/**
	 */
	@JsonIgnore
	public Set<LabRoomCourseCapacity> getLabRoomCourseCapacities() {
		if (labRoomCourseCapacities == null) {
			labRoomCourseCapacities = new java.util.LinkedHashSet<net.xidlims.domain.LabRoomCourseCapacity>();
		}
		return labRoomCourseCapacities;
	}

	public Calendar getCourseStart() {
		return courseStart;
	}

	public void setCourseStart(Calendar courseStart) {
		this.courseStart = courseStart;
	}

	public Calendar getCourseEnd() {
		return courseEnd;
	}

	public void setCourseEnd(Calendar courseEnd) {
		this.courseEnd = courseEnd;
	}

	public Calendar getCourseStartTime() {
		return courseStartTime;
	}

	public void setCourseStartTime(Calendar courseStartTime) {
		this.courseStartTime = courseStartTime;
	}

	public Integer getCourseEndMinute() {
		return courseEndMinute;
	}

	public void setCourseEndMinute(Integer courseEndMinute) {
		this.courseEndMinute = courseEndMinute;
	}

	public Integer getWithdrawalMinute() {
		return withdrawalMinute;
	}

	public void setWithdrawalMinute(Integer withdrawalMinute) {
		this.withdrawalMinute = withdrawalMinute;
	}

	public Integer getWithdrawalTimes() {
		return withdrawalTimes;
	}

	public void setWithdrawalTimes(Integer withdrawalTimes) {
		this.withdrawalTimes = withdrawalTimes;
	}

	public String getCourseRequirement() {
		return courseRequirement;
	}

	public void setCourseRequirement(String courseRequirement) {
		this.courseRequirement = courseRequirement;
	}

	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "merge_id", referencedColumnName = "id") })
	@XmlTransient
	SchoolCourseMerge schoolCourseMerge;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "teacher_number", referencedColumnName = "username") })
	@XmlTransient
	User user;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "term_id", referencedColumnName = "id") })
	@XmlTransient
	SchoolTerm schoolTerm;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "course_no", referencedColumnName = "course_no") })
	@XmlTransient
	SchoolCourse schoolCourse;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "academy_number", referencedColumnName = "academy_number") })
	@XmlTransient
	SchoolAcademy schoolAcademy;

	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "schedule_teacher", referencedColumnName = "username") })
	@XmlTransient
	User userByScheduleTeacher;
	/**
	 */
	@OneToMany(mappedBy = "schoolCourseDetail", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.TimetableAppointment> timetableAppointments;
	/**
	 */
	@OneToMany(mappedBy = "schoolCourseDetail", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.SchoolCourseStudent> schoolCourseStudents;
	/**
	 */
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(catalog = "xidlims", name = "school_course_detail_teacher", joinColumns = { @JoinColumn(name = "course_detail_no", referencedColumnName = "course_detail_no", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "teacher_number", referencedColumnName = "username", nullable = false, updatable = false) })
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.User> users;

	/**
	 */
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(catalog = "xidlims", name = "school_course_detail_schedule_teacher", joinColumns = { @JoinColumn(name = "course_detail_no", referencedColumnName = "course_detail_no", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "teacher_number", referencedColumnName = "username", nullable = false, updatable = false) })
	@XmlElement(name = "", namespace = "")
	java.util.Set<net.xidlims.domain.User> userByScheduleTeachers;

	/**
	 * ��ɺ�Ŀγ������ţ�Ψһ�ԣ�
	 * 
	 */
	public void setCourseDetailNo(String courseDetailNo) {
		this.courseDetailNo = courseDetailNo;
	}

	/**
	 * ��ɺ�Ŀγ������ţ�Ψһ�ԣ�
	 * 
	 */
	public String getCourseDetailNo() {
		return this.courseDetailNo;
	}

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
	 * �γ����
	 * 
	 */
	public void setCourseNumber(String courseNumber) {
		this.courseNumber = courseNumber;
	}

	/**
	 * �γ����
	 * 
	 */
	public String getCourseNumber() {
		return this.courseNumber;
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
	 * �ƻ�����
	 * 
	 */
	public void setPlanStudentNumber(String planStudentNumber) {
		this.planStudentNumber = planStudentNumber;
	}

	/**
	 * �ƻ�����
	 * 
	 */
	public String getPlanStudentNumber() {
		return this.planStudentNumber;
	}

	/**
	 * ����
	 * 
	 */
	public void setTotalWeeks(String totalWeeks) {
		this.totalWeeks = totalWeeks;
	}

	/**
	 * ����
	 * 
	 */
	public String getTotalWeeks() {
		return this.totalWeeks;
	}

	/**
	 * ���ڼ�
	 * 
	 */
	public void setWeekday(Integer weekday) {
		this.weekday = weekday;
	}

	/**
	 * ���ڼ�
	 * 
	 */
	public Integer getWeekday() {
		return this.weekday;
	}

	/**
	 * ��ʼ��
	 * 
	 */
	public void setStartWeek(Integer startWeek) {
		this.startWeek = startWeek;
	}

	/**
	 * ��ʼ��
	 * 
	 */
	public Integer getStartWeek() {
		return this.startWeek;
	}

	/**
	 * ������
	 * 
	 */
	public void setEndWeek(Integer endWeek) {
		this.endWeek = endWeek;
	}

	/**
	 * ������
	 * 
	 */
	public Integer getEndWeek() {
		return this.endWeek;
	}

	/**
	 * ����
	 * 
	 */
	public void setTotalClasses(Integer totalClasses) {
		this.totalClasses = totalClasses;
	}

	/**
	 * ����
	 * 
	 */
	public Integer getTotalClasses() {
		return this.totalClasses;
	}

	/**
	 * ��ʼ�ڴ�
	 * 
	 */
	public void setStartClass(Integer startClass) {
		this.startClass = startClass;
	}

	/**
	 * ��ʼ�ڴ�
	 * 
	 */
	public Integer getStartClass() {
		return this.startClass;
	}

	/**
	 * �����
	 * 
	 */
	public void setEndClass(Integer endClass) {
		this.endClass = endClass;
	}

	/**
	 * �����
	 * 
	 */
	public Integer getEndClass() {
		return this.endClass;
	}

	/**
	 * ָ����ʦ��Ӧ���û�
	 * 
	 */
	public void setTutor(String tutor) {
		this.tutor = tutor;
	}

	/**
	 * ָ����ʦ��Ӧ���û�
	 * 
	 */
	public String getTutor() {
		return this.tutor;
	}

	/**
	 * �ܿ�ʱ
	 * 
	 */
	public void setTotalClassHour(String totalClassHour) {
		this.totalClassHour = totalClassHour;
	}

	/**
	 * �ܿ�ʱ
	 * 
	 */
	public String getTotalClassHour() {
		return this.totalClassHour;
	}

	/**
	 * �ܿ�ʱ
	 * 
	 */
	public void setWeekClassHour(String weekClassHour) {
		this.weekClassHour = weekClassHour;
	}

	/**
	 * �ܿ�ʱ
	 * 
	 */
	public String getWeekClassHour() {
		return this.weekClassHour;
	}

	/**
	 * ʵ����ѧʱ
	 * 
	 */
	public void setExperimentalClassHour(String experimentalClassHour) {
		this.experimentalClassHour = experimentalClassHour;
	}

	/**
	 * ʵ����ѧʱ
	 * 
	 */
	public String getExperimentalClassHour() {
		return this.experimentalClassHour;
	}

	/**
	 * ʵ�����
	 * 
	 */
	public void setExperimentalProportion(String experimentalProportion) {
		this.experimentalProportion = experimentalProportion;
	}

	/**
	 * ʵ�����
	 * 
	 */
	public String getExperimentalProportion() {
		return this.experimentalProportion;
	}

	/**
	 * �Ƿ�����
	 * 
	 */
	public void setIfArrange(Boolean ifArrange) {
		this.ifArrange = ifArrange;
	}

	/**
	 * �Ƿ�����
	 * 
	 */
	public Boolean getIfArrange() {
		return this.ifArrange;
	}

	/**
	 * ��������
	 * 
	 */
	public void setMaxStudents(String maxStudents) {
		this.maxStudents = maxStudents;
	}

	/**
	 * ��������
	 * 
	 */
	public String getMaxStudents() {
		return this.maxStudents;
	}

	/**
	 * ��������
	 * 
	 */
	public void setMinStudents(String minStudents) {
		this.minStudents = minStudents;
	}

	/**
	 * ��������
	 * 
	 */
	public String getMinStudents() {
		return this.minStudents;
	}

	/**
	 * �����豸����
	 * 
	 */
	public void setEquipmentConfiguration(String equipmentConfiguration) {
		this.equipmentConfiguration = equipmentConfiguration;
	}

	/**
	 * �����豸����
	 * 
	 */
	public String getEquipmentConfiguration() {
		return this.equipmentConfiguration;
	}

	/**
	 * ����������
	 * 
	 */
	public void setClassroomType(String classroomType) {
		this.classroomType = classroomType;
	}

	/**
	 * ����������
	 * 
	 */
	public String getClassroomType() {
		return this.classroomType;
	}

	/**
	 * �Ƿ��ѡ
	 * 
	 */
	public void setIfSelect(Boolean ifSelect) {
		this.ifSelect = ifSelect;
	}

	/**
	 * �Ƿ��ѡ
	 * 
	 */
	public Boolean getIfSelect() {
		return this.ifSelect;
	}

	/**
	 * �Ƿ����
	 * 
	 */
	public void setIfFlop(Boolean ifFlop) {
		this.ifFlop = ifFlop;
	}

	/**
	 * �Ƿ����
	 * 
	 */
	public Boolean getIfFlop() {
		return this.ifFlop;
	}

	/**
	 * �γ�����
	 * 
	 */
	public void setCourseType(String courseType) {
		this.courseType = courseType;
	}

	/**
	 * �γ�����
	 * 
	 */
	public String getCourseType() {
		return this.courseType;
	}

	/**
	 * �γ��������
	 * 
	 */
	public void setCourseTypeName(String courseTypeName) {
		this.courseTypeName = courseTypeName;
	}

	/**
	 * �γ��������
	 * 
	 */
	public String getCourseTypeName() {
		return this.courseTypeName;
	}

	/**
	 * ���˷�ʽ
	 * 
	 */
	public void setEvaluationMode(String evaluationMode) {
		this.evaluationMode = evaluationMode;
	}

	/**
	 * ���˷�ʽ
	 * 
	 */
	public String getEvaluationMode() {
		return this.evaluationMode;
	}

	/**
	 * ���˷�ʽ���
	 * 
	 */
	public void setEvaluationModeName(String evaluationModeName) {
		this.evaluationModeName = evaluationModeName;
	}

	/**
	 * ���˷�ʽ���
	 * 
	 */
	public String getEvaluationModeName() {
		return this.evaluationModeName;
	}

	/**
	 * �ڿ���������
	 * 
	 */
	public void setInstructionType(String instructionType) {
		this.instructionType = instructionType;
	}

	/**
	 * �ڿ���������
	 * 
	 */
	public String getInstructionType() {
		return this.instructionType;
	}

	/**
	 * �ڿ������������
	 * 
	 */
	public void setInstructionTypeName(String instructionTypeName) {
		this.instructionTypeName = instructionTypeName;
	}

	/**
	 * �ڿ������������
	 * 
	 */
	public String getInstructionTypeName() {
		return this.instructionTypeName;
	}

	/**
	 * �Ƿ������˿�
	 * 
	 */
	public void setIfAllowCourse(Boolean ifAllowCourse) {
		this.ifAllowCourse = ifAllowCourse;
	}

	/**
	 * �Ƿ������˿�
	 * 
	 */
	public Boolean getIfAllowCourse() {
		return this.ifAllowCourse;
	}

	/**
	 * ����Ҫ��
	 * 
	 */
	public void setEnvironmentalRequirements(String environmentalRequirements) {
		this.environmentalRequirements = environmentalRequirements;
	}

	/**
	 * ����Ҫ��
	 * 
	 */
	public String getEnvironmentalRequirements() {
		return this.environmentalRequirements;
	}

	/**
	 * �ϻ�ʱ��
	 * 
	 */
	public void setComputeTime(String computeTime) {
		this.computeTime = computeTime;
	}

	/**
	 * �ϻ�ʱ��
	 * 
	 */
	public String getComputeTime() {
		return this.computeTime;
	}

	/**
	 * �ϻ��������
	 * 
	 */
	public void setRequiredSoftware(String requiredSoftware) {
		this.requiredSoftware = requiredSoftware;
	}

	/**
	 * �ϻ��������
	 * 
	 */
	public String getRequiredSoftware() {
		return this.requiredSoftware;
	}

	/**
	 * ������
	 * 
	 */
	public void setWeekType(String weekType) {
		this.weekType = weekType;
	}

	/**
	 * ������
	 * 
	 */
	public String getWeekType() {
		return this.weekType;
	}

	/**
	 * �Ƿ�Ϊ��ʱ�γ�
	 * 
	 */
	public void setIfTemporary(Boolean ifTemporary) {
		this.ifTemporary = ifTemporary;
	}

	/**
	 * �Ƿ�Ϊ��ʱ�γ�
	 * 
	 */
	public Boolean getIfTemporary() {
		return this.ifTemporary;
	}

	/**
	 * ��صĽ�ʦ����
	 * 
	 */
	public void setNeedId(Integer needId) {
		this.needId = needId;
	}

	/**
	 * ��صĽ�ʦ����
	 * 
	 */
	public Integer getNeedId() {
		return this.needId;
	}

	/**
	 * �ογ��Ƿ��Ѿ�ԤԼ��
	 * 
	 */
	public void setIfAppointment(Boolean ifAppointment) {
		this.ifAppointment = ifAppointment;
	}

	/**
	 * �ογ��Ƿ��Ѿ�ԤԼ��
	 * 
	 */
	public Boolean getIfAppointment() {
		return this.ifAppointment;
	}

	/**
	 * ʣ��Ŀγ̰���
	 * 
	 */
	public void setRemainingArrange(String remainingArrange) {
		this.remainingArrange = remainingArrange;
	}

	/**
	 * ʣ��Ŀγ̰���
	 * 
	 */
	public String getRemainingArrange() {
		return this.remainingArrange;
	}

	/**
	 * �γ����
	 * 
	 */
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	/**
	 * �γ����
	 * 
	 */
	public String getCourseName() {
		return this.courseName;
	}

	/**
	 * ʵ���ϻ���ܴ�
	 * 
	 */
	public void setWeeksExperiment(String weeksExperiment) {
		this.weeksExperiment = weeksExperiment;
	}

	/**
	 * ʵ���ϻ���ܴ�
	 * 
	 */
	public String getWeeksExperiment() {
		return this.weeksExperiment;
	}

	/**
	 * ʵ���ϻ�Ľڴ�
	 * 
	 */
	public void setClassesExperiment(String classesExperiment) {
		this.classesExperiment = classesExperiment;
	}

	/**
	 * ʵ���ϻ�Ľڴ�
	 * 
	 */
	public String getClassesExperiment() {
		return this.classesExperiment;
	}

	/**
	 * �����γ�
	 * 
	 */
	public void setDetailId(Integer detailId) {
		this.detailId = detailId;
	}

	/**
	 * �����γ�
	 * 
	 */
	public Integer getDetailId() {
		return this.detailId;
	}

	/**
	 */
	public void setUseweek(String useweek) {
		this.useweek = useweek;
	}

	/**
	 */
	public String getUseweek() {
		return this.useweek;
	}

	/**
	 */
	public void setClasstime(String classtime) {
		this.classtime = classtime;
	}

	/**
	 */
	public String getClasstime() {
		return this.classtime;
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
	public void setSchoolCourseMerge(SchoolCourseMerge schoolCourseMerge) {
		this.schoolCourseMerge = schoolCourseMerge;
	}

	/**
	 */
	@JsonIgnore
	public SchoolCourseMerge getSchoolCourseMerge() {
		return schoolCourseMerge;
	}

	/**
	 */
	public void setUserByScheduleTeacher(User userByScheduleTeacher) {
		this.userByScheduleTeacher = userByScheduleTeacher;
	}

	/**
	 */
	@JsonIgnore
	public User getUserByScheduleTeacher() {
		return userByScheduleTeacher;
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
	public void setSchoolCourse(SchoolCourse schoolCourse) {
		this.schoolCourse = schoolCourse;
	}

	/**
	 */
	@JsonIgnore
	public SchoolCourse getSchoolCourse() {
		return schoolCourse;
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
	public void setTimetableAppointments(
			Set<TimetableAppointment> timetableAppointments) {
		this.timetableAppointments = timetableAppointments;
	}

	/**
	 */
	@JsonIgnore
	public Set<TimetableAppointment> getTimetableAppointments() {
		if (timetableAppointments == null) {
			timetableAppointments = new java.util.LinkedHashSet<net.xidlims.domain.TimetableAppointment>();
		}
		return timetableAppointments;
	}

	/**
	 */
	public void setSchoolCourseStudents(
			Set<SchoolCourseStudent> schoolCourseStudents) {
		this.schoolCourseStudents = schoolCourseStudents;
	}

	/**
	 */
	@JsonIgnore
	public Set<SchoolCourseStudent> getSchoolCourseStudents() {
		if (schoolCourseStudents == null) {
			schoolCourseStudents = new java.util.LinkedHashSet<net.xidlims.domain.SchoolCourseStudent>();
		}
		return schoolCourseStudents;
	}

	/**
	 */
	public void setUsers(Set<User> users) {
		this.users = users;
	}

	/**
	 */
	@JsonIgnore
	public Set<User> getUsers() {
		if (users == null) {
			users = new java.util.LinkedHashSet<net.xidlims.domain.User>();
		}
		return users;
	}

	/**
	 */
	@JsonIgnore
	public Set<User> getUserByScheduleTeachers() {
		if (userByScheduleTeachers == null) {
			userByScheduleTeachers = new java.util.LinkedHashSet<net.xidlims.domain.User>();
		}
		return userByScheduleTeachers;
	}

	/**
	 */
	public void setUserByScheduleTeachers(Set<User> userByScheduleTeachers) {
		this.userByScheduleTeachers = userByScheduleTeachers;
	}

	/**
	 */
	public SchoolCourseDetail() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 */
	public void copy(SchoolCourseDetail that) {
		setCourseDetailNo(that.getCourseDetailNo());
		setId(that.getId());
		setCourseNumber(that.getCourseNumber());
		setMajorDirectionName(that.getMajorDirectionName());
		setMajorName(that.getMajorName());
		setPlanStudentNumber(that.getPlanStudentNumber());
		setTotalWeeks(that.getTotalWeeks());
		setWeekday(that.getWeekday());
		setStartWeek(that.getStartWeek());
		setEndWeek(that.getEndWeek());
		setTotalClasses(that.getTotalClasses());
		setStartClass(that.getStartClass());
		setEndClass(that.getEndClass());
		setTutor(that.getTutor());
		setTotalClassHour(that.getTotalClassHour());
		setWeekClassHour(that.getWeekClassHour());
		setExperimentalClassHour(that.getExperimentalClassHour());
		setExperimentalProportion(that.getExperimentalProportion());
		setIfArrange(that.getIfArrange());
		setMaxStudents(that.getMaxStudents());
		setMinStudents(that.getMinStudents());
		setEquipmentConfiguration(that.getEquipmentConfiguration());
		setClassroomType(that.getClassroomType());
		setIfSelect(that.getIfSelect());
		setIfFlop(that.getIfFlop());
		setCourseType(that.getCourseType());
		setCourseTypeName(that.getCourseTypeName());
		setEvaluationMode(that.getEvaluationMode());
		setEvaluationModeName(that.getEvaluationModeName());
		setInstructionType(that.getInstructionType());
		setInstructionTypeName(that.getInstructionTypeName());
		setIfAllowCourse(that.getIfAllowCourse());
		setEnvironmentalRequirements(that.getEnvironmentalRequirements());
		setComputeTime(that.getComputeTime());
		setRequiredSoftware(that.getRequiredSoftware());
		setWeekType(that.getWeekType());
		setIfTemporary(that.getIfTemporary());
		setNeedId(that.getNeedId());
		setIfAppointment(that.getIfAppointment());
		setRemainingArrange(that.getRemainingArrange());
		setCourseName(that.getCourseName());
		setWeeksExperiment(that.getWeeksExperiment());
		setClassesExperiment(that.getClassesExperiment());
		setDetailId(that.getDetailId());
		setUseweek(that.getUseweek());
		setClasstime(that.getClasstime());
		setState(that.getState());
		setUser(that.getUser());
		setSchoolTerm(that.getSchoolTerm());
		setSchoolCourse(that.getSchoolCourse());
		setSchoolAcademy(that.getSchoolAcademy());
		setTimetableAppointments(new java.util.LinkedHashSet<net.xidlims.domain.TimetableAppointment>(
				that.getTimetableAppointments()));
		setSchoolCourseStudents(new java.util.LinkedHashSet<net.xidlims.domain.SchoolCourseStudent>(
				that.getSchoolCourseStudents()));
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("courseDetailNo=[").append(courseDetailNo).append("] ");
		buffer.append("id=[").append(id).append("] ");
		buffer.append("courseNumber=[").append(courseNumber).append("] ");
		buffer.append("majorDirectionName=[").append(majorDirectionName)
				.append("] ");
		buffer.append("majorName=[").append(majorName).append("] ");
		buffer.append("planStudentNumber=[").append(planStudentNumber)
				.append("] ");
		buffer.append("totalWeeks=[").append(totalWeeks).append("] ");
		buffer.append("weekday=[").append(weekday).append("] ");
		buffer.append("startWeek=[").append(startWeek).append("] ");
		buffer.append("endWeek=[").append(endWeek).append("] ");
		buffer.append("totalClasses=[").append(totalClasses).append("] ");
		buffer.append("startClass=[").append(startClass).append("] ");
		buffer.append("endClass=[").append(endClass).append("] ");
		buffer.append("tutor=[").append(tutor).append("] ");
		buffer.append("totalClassHour=[").append(totalClassHour).append("] ");
		buffer.append("weekClassHour=[").append(weekClassHour).append("] ");
		buffer.append("experimentalClassHour=[").append(experimentalClassHour)
				.append("] ");
		buffer.append("experimentalProportion=[")
				.append(experimentalProportion).append("] ");
		buffer.append("ifArrange=[").append(ifArrange).append("] ");
		buffer.append("maxStudents=[").append(maxStudents).append("] ");
		buffer.append("minStudents=[").append(minStudents).append("] ");
		buffer.append("equipmentConfiguration=[")
				.append(equipmentConfiguration).append("] ");
		buffer.append("classroomType=[").append(classroomType).append("] ");
		buffer.append("ifSelect=[").append(ifSelect).append("] ");
		buffer.append("ifFlop=[").append(ifFlop).append("] ");
		buffer.append("courseType=[").append(courseType).append("] ");
		buffer.append("courseTypeName=[").append(courseTypeName).append("] ");
		buffer.append("evaluationMode=[").append(evaluationMode).append("] ");
		buffer.append("evaluationModeName=[").append(evaluationModeName)
				.append("] ");
		buffer.append("instructionType=[").append(instructionType).append("] ");
		buffer.append("instructionTypeName=[").append(instructionTypeName)
				.append("] ");
		buffer.append("ifAllowCourse=[").append(ifAllowCourse).append("] ");
		buffer.append("environmentalRequirements=[")
				.append(environmentalRequirements).append("] ");
		buffer.append("computeTime=[").append(computeTime).append("] ");
		buffer.append("requiredSoftware=[").append(requiredSoftware)
				.append("] ");
		buffer.append("weekType=[").append(weekType).append("] ");
		buffer.append("ifTemporary=[").append(ifTemporary).append("] ");
		buffer.append("needId=[").append(needId).append("] ");
		buffer.append("ifAppointment=[").append(ifAppointment).append("] ");
		buffer.append("remainingArrange=[").append(remainingArrange)
				.append("] ");
		buffer.append("courseName=[").append(courseName).append("] ");
		buffer.append("weeksExperiment=[").append(weeksExperiment).append("] ");
		buffer.append("classesExperiment=[").append(classesExperiment)
				.append("] ");
		buffer.append("detailId=[").append(detailId).append("] ");
		buffer.append("useweek=[").append(useweek).append("] ");
		buffer.append("classtime=[").append(classtime).append("] ");
		buffer.append("state=[").append(state).append("] ");

		return buffer.toString();
	}

	/**
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((courseDetailNo == null) ? 0
				: courseDetailNo.hashCode()));
		return result;
	}

	/**
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof SchoolCourseDetail))
			return false;
		SchoolCourseDetail equalCheck = (SchoolCourseDetail) obj;
		if ((courseDetailNo == null && equalCheck.courseDetailNo != null)
				|| (courseDetailNo != null && equalCheck.courseDetailNo == null))
			return false;
		if (courseDetailNo != null
				&& !courseDetailNo.equals(equalCheck.courseDetailNo))
			return false;
		return true;
	}
}
