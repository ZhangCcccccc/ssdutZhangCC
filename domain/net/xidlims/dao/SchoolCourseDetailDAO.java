package net.xidlims.dao;

import java.util.Set;

import net.xidlims.domain.SchoolCourseDetail;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage SchoolCourseDetail entities.
 * 
 */
public interface SchoolCourseDetailDAO extends JpaDao<SchoolCourseDetail> {

	/**
	 * JPQL Query - findSchoolCourseDetailByRemainingArrange
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByRemainingArrange(String remainingArrange) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByRemainingArrange
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByRemainingArrange(String remainingArrange, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByRemainingArrangeContaining
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByRemainingArrangeContaining(String remainingArrange_1) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByRemainingArrangeContaining
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByRemainingArrangeContaining(String remainingArrange_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByWeekClassHour
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByWeekClassHour(String weekClassHour) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByWeekClassHour
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByWeekClassHour(String weekClassHour, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByState
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByState(Integer state) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByState
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByState(Integer state, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByCourseType
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByCourseType(String courseType) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByCourseType
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByCourseType(String courseType, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByCourseTypeContaining
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByCourseTypeContaining(String courseType_1) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByCourseTypeContaining
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByCourseTypeContaining(String courseType_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByClassroomType
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByClassroomType(String classroomType) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByClassroomType
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByClassroomType(String classroomType, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByMinStudentsContaining
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByMinStudentsContaining(String minStudents) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByMinStudentsContaining
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByMinStudentsContaining(String minStudents, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByCourseNameContaining
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByCourseNameContaining(String courseName) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByCourseNameContaining
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByCourseNameContaining(String courseName, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByIfAppointment
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByIfAppointment(Boolean ifAppointment) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByIfAppointment
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByIfAppointment(Boolean ifAppointment, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByMajorDirectionName
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByMajorDirectionName(String majorDirectionName) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByMajorDirectionName
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByMajorDirectionName(String majorDirectionName, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByMinStudents
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByMinStudents(String minStudents_1) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByMinStudents
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByMinStudents(String minStudents_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByIfFlop
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByIfFlop(Boolean ifFlop) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByIfFlop
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByIfFlop(Boolean ifFlop, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByExperimentalProportion
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByExperimentalProportion(String experimentalProportion) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByExperimentalProportion
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByExperimentalProportion(String experimentalProportion, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByRequiredSoftware
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByRequiredSoftware(String requiredSoftware) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByRequiredSoftware
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByRequiredSoftware(String requiredSoftware, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByClasstime
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByClasstime(String classtime) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByClasstime
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByClasstime(String classtime, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByEvaluationMode
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByEvaluationMode(String evaluationMode) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByEvaluationMode
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByEvaluationMode(String evaluationMode, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByInstructionTypeContaining
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByInstructionTypeContaining(String instructionType) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByInstructionTypeContaining
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByInstructionTypeContaining(String instructionType, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByInstructionType
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByInstructionType(String instructionType_1) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByInstructionType
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByInstructionType(String instructionType_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByWeekType
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByWeekType(String weekType) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByWeekType
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByWeekType(String weekType, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByEndClass
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByEndClass(Integer endClass) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByEndClass
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByEndClass(Integer endClass, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByEnvironmentalRequirements
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByEnvironmentalRequirements(String environmentalRequirements) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByEnvironmentalRequirements
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByEnvironmentalRequirements(String environmentalRequirements, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByPlanStudentNumber
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByPlanStudentNumber(String planStudentNumber) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByPlanStudentNumber
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByPlanStudentNumber(String planStudentNumber, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByIfTemporary
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByIfTemporary(Boolean ifTemporary) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByIfTemporary
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByIfTemporary(Boolean ifTemporary, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByCourseNumberContaining
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByCourseNumberContaining(String courseNumber) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByCourseNumberContaining
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByCourseNumberContaining(String courseNumber, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByTotalClassHourContaining
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByTotalClassHourContaining(String totalClassHour) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByTotalClassHourContaining
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByTotalClassHourContaining(String totalClassHour, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByTotalClassHour
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByTotalClassHour(String totalClassHour_1) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByTotalClassHour
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByTotalClassHour(String totalClassHour_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByDetailId
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByDetailId(Integer detailId) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByDetailId
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByDetailId(Integer detailId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByRequiredSoftwareContaining
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByRequiredSoftwareContaining(String requiredSoftware_1) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByRequiredSoftwareContaining
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByRequiredSoftwareContaining(String requiredSoftware_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByCourseDetailNoContaining
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByCourseDetailNoContaining(String courseDetailNo) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByCourseDetailNoContaining
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByCourseDetailNoContaining(String courseDetailNo, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByCourseTypeName
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByCourseTypeName(String courseTypeName) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByCourseTypeName
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByCourseTypeName(String courseTypeName, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByComputeTimeContaining
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByComputeTimeContaining(String computeTime) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByComputeTimeContaining
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByComputeTimeContaining(String computeTime, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByMajorName
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByMajorName(String majorName) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByMajorName
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByMajorName(String majorName, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByTotalWeeksContaining
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByTotalWeeksContaining(String totalWeeks) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByTotalWeeksContaining
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByTotalWeeksContaining(String totalWeeks, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByStartClass
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByStartClass(Integer startClass) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByStartClass
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByStartClass(Integer startClass, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByPlanStudentNumberContaining
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByPlanStudentNumberContaining(String planStudentNumber_1) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByPlanStudentNumberContaining
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByPlanStudentNumberContaining(String planStudentNumber_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByEquipmentConfiguration
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByEquipmentConfiguration(String equipmentConfiguration) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByEquipmentConfiguration
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByEquipmentConfiguration(String equipmentConfiguration, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByCourseNumber
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByCourseNumber(String courseNumber_1) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByCourseNumber
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByCourseNumber(String courseNumber_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByTutorContaining
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByTutorContaining(String tutor) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByTutorContaining
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByTutorContaining(String tutor, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByInstructionTypeNameContaining
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByInstructionTypeNameContaining(String instructionTypeName) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByInstructionTypeNameContaining
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByInstructionTypeNameContaining(String instructionTypeName, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByWeekday
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByWeekday(Integer weekday) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByWeekday
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByWeekday(Integer weekday, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByEnvironmentalRequirementsContaining
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByEnvironmentalRequirementsContaining(String environmentalRequirements_1) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByEnvironmentalRequirementsContaining
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByEnvironmentalRequirementsContaining(String environmentalRequirements_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByEvaluationModeName
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByEvaluationModeName(String evaluationModeName) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByEvaluationModeName
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByEvaluationModeName(String evaluationModeName, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByCourseDetailNo
	 *
	 */
	public SchoolCourseDetail findSchoolCourseDetailByCourseDetailNo(String courseDetailNo_1) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByCourseDetailNo
	 *
	 */
	public SchoolCourseDetail findSchoolCourseDetailByCourseDetailNo(String courseDetailNo_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByUseweek
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByUseweek(String useweek) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByUseweek
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByUseweek(String useweek, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByStartWeek
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByStartWeek(Integer startWeek) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByStartWeek
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByStartWeek(Integer startWeek, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByUseweekContaining
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByUseweekContaining(String useweek_1) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByUseweekContaining
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByUseweekContaining(String useweek_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByMajorDirectionNameContaining
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByMajorDirectionNameContaining(String majorDirectionName_1) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByMajorDirectionNameContaining
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByMajorDirectionNameContaining(String majorDirectionName_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByClassesExperiment
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByClassesExperiment(String classesExperiment) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByClassesExperiment
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByClassesExperiment(String classesExperiment, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByComputeTime
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByComputeTime(String computeTime_1) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByComputeTime
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByComputeTime(String computeTime_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByExperimentalClassHour
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByExperimentalClassHour(String experimentalClassHour) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByExperimentalClassHour
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByExperimentalClassHour(String experimentalClassHour, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByTutor
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByTutor(String tutor_1) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByTutor
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByTutor(String tutor_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByIfArrange
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByIfArrange(Boolean ifArrange) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByIfArrange
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByIfArrange(Boolean ifArrange, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByCourseTypeNameContaining
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByCourseTypeNameContaining(String courseTypeName_1) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByCourseTypeNameContaining
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByCourseTypeNameContaining(String courseTypeName_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByMaxStudents
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByMaxStudents(String maxStudents) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByMaxStudents
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByMaxStudents(String maxStudents, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByEndWeek
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByEndWeek(Integer endWeek) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByEndWeek
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByEndWeek(Integer endWeek, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailById
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailById(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailById
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailById(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByCourseName
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByCourseName(String courseName_1) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByCourseName
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByCourseName(String courseName_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByInstructionTypeName
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByInstructionTypeName(String instructionTypeName_1) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByInstructionTypeName
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByInstructionTypeName(String instructionTypeName_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByClasstimeContaining
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByClasstimeContaining(String classtime_1) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByClasstimeContaining
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByClasstimeContaining(String classtime_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByExperimentalProportionContaining
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByExperimentalProportionContaining(String experimentalProportion_1) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByExperimentalProportionContaining
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByExperimentalProportionContaining(String experimentalProportion_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByTotalWeeks
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByTotalWeeks(String totalWeeks_1) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByTotalWeeks
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByTotalWeeks(String totalWeeks_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByNeedId
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByNeedId(Integer needId) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByNeedId
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByNeedId(Integer needId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByIfAllowCourse
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByIfAllowCourse(Boolean ifAllowCourse) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByIfAllowCourse
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByIfAllowCourse(Boolean ifAllowCourse, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByWeeksExperiment
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByWeeksExperiment(String weeksExperiment) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByWeeksExperiment
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByWeeksExperiment(String weeksExperiment, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByWeeksExperimentContaining
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByWeeksExperimentContaining(String weeksExperiment_1) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByWeeksExperimentContaining
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByWeeksExperimentContaining(String weeksExperiment_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByPrimaryKey
	 *
	 */
	public SchoolCourseDetail findSchoolCourseDetailByPrimaryKey(String courseDetailNo_2) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByPrimaryKey
	 *
	 */
	public SchoolCourseDetail findSchoolCourseDetailByPrimaryKey(String courseDetailNo_2, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByMaxStudentsContaining
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByMaxStudentsContaining(String maxStudents_1) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByMaxStudentsContaining
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByMaxStudentsContaining(String maxStudents_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByClassesExperimentContaining
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByClassesExperimentContaining(String classesExperiment_1) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByClassesExperimentContaining
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByClassesExperimentContaining(String classesExperiment_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByClassroomTypeContaining
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByClassroomTypeContaining(String classroomType_1) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByClassroomTypeContaining
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByClassroomTypeContaining(String classroomType_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByIfSelect
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByIfSelect(Boolean ifSelect) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByIfSelect
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByIfSelect(Boolean ifSelect, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByWeekTypeContaining
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByWeekTypeContaining(String weekType_1) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByWeekTypeContaining
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByWeekTypeContaining(String weekType_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByMajorNameContaining
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByMajorNameContaining(String majorName_1) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByMajorNameContaining
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByMajorNameContaining(String majorName_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByEquipmentConfigurationContaining
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByEquipmentConfigurationContaining(String equipmentConfiguration_1) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByEquipmentConfigurationContaining
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByEquipmentConfigurationContaining(String equipmentConfiguration_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByExperimentalClassHourContaining
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByExperimentalClassHourContaining(String experimentalClassHour_1) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByExperimentalClassHourContaining
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByExperimentalClassHourContaining(String experimentalClassHour_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllSchoolCourseDetails
	 *
	 */
	public Set<SchoolCourseDetail> findAllSchoolCourseDetails() throws DataAccessException;

	/**
	 * JPQL Query - findAllSchoolCourseDetails
	 *
	 */
	public Set<SchoolCourseDetail> findAllSchoolCourseDetails(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByEvaluationModeContaining
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByEvaluationModeContaining(String evaluationMode_1) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByEvaluationModeContaining
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByEvaluationModeContaining(String evaluationMode_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByTotalClasses
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByTotalClasses(Integer totalClasses) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByTotalClasses
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByTotalClasses(Integer totalClasses, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByEvaluationModeNameContaining
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByEvaluationModeNameContaining(String evaluationModeName_1) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByEvaluationModeNameContaining
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByEvaluationModeNameContaining(String evaluationModeName_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByWeekClassHourContaining
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByWeekClassHourContaining(String weekClassHour_1) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolCourseDetailByWeekClassHourContaining
	 *
	 */
	public Set<SchoolCourseDetail> findSchoolCourseDetailByWeekClassHourContaining(String weekClassHour_1, int startResult, int maxRows) throws DataAccessException;

}