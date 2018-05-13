package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.SchoolCourseDetail;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage SchoolCourseDetail entities.
 * 
 */
@Repository("SchoolCourseDetailDAO")
@Transactional
public class SchoolCourseDetailDAOImpl extends AbstractJpaDao<SchoolCourseDetail>
		implements SchoolCourseDetailDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { SchoolCourseDetail.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new SchoolCourseDetailDAOImpl
	 *
	 */
	public SchoolCourseDetailDAOImpl() {
		super();
	}

	/**
	 * Get the entity manager that manages persistence unit 
	 *
	 */
	public EntityManager getEntityManager() {
		return entityManager;
	}

	/**
	 * Returns the set of entity classes managed by this DAO.
	 *
	 */
	public Set<Class<?>> getTypes() {
		return dataTypes;
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByRemainingArrange
	 *
	 */
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByRemainingArrange(String remainingArrange) throws DataAccessException {

		return findSchoolCourseDetailByRemainingArrange(remainingArrange, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByRemainingArrange
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByRemainingArrange(String remainingArrange, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolCourseDetailByRemainingArrange", startResult, maxRows, remainingArrange);
		return new LinkedHashSet<SchoolCourseDetail>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByRemainingArrangeContaining
	 *
	 */
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByRemainingArrangeContaining(String remainingArrange) throws DataAccessException {

		return findSchoolCourseDetailByRemainingArrangeContaining(remainingArrange, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByRemainingArrangeContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByRemainingArrangeContaining(String remainingArrange, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolCourseDetailByRemainingArrangeContaining", startResult, maxRows, remainingArrange);
		return new LinkedHashSet<SchoolCourseDetail>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByWeekClassHour
	 *
	 */
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByWeekClassHour(String weekClassHour) throws DataAccessException {

		return findSchoolCourseDetailByWeekClassHour(weekClassHour, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByWeekClassHour
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByWeekClassHour(String weekClassHour, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolCourseDetailByWeekClassHour", startResult, maxRows, weekClassHour);
		return new LinkedHashSet<SchoolCourseDetail>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByState
	 *
	 */
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByState(Integer state) throws DataAccessException {

		return findSchoolCourseDetailByState(state, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByState
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByState(Integer state, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolCourseDetailByState", startResult, maxRows, state);
		return new LinkedHashSet<SchoolCourseDetail>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByCourseType
	 *
	 */
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByCourseType(String courseType) throws DataAccessException {

		return findSchoolCourseDetailByCourseType(courseType, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByCourseType
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByCourseType(String courseType, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolCourseDetailByCourseType", startResult, maxRows, courseType);
		return new LinkedHashSet<SchoolCourseDetail>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByCourseTypeContaining
	 *
	 */
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByCourseTypeContaining(String courseType) throws DataAccessException {

		return findSchoolCourseDetailByCourseTypeContaining(courseType, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByCourseTypeContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByCourseTypeContaining(String courseType, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolCourseDetailByCourseTypeContaining", startResult, maxRows, courseType);
		return new LinkedHashSet<SchoolCourseDetail>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByClassroomType
	 *
	 */
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByClassroomType(String classroomType) throws DataAccessException {

		return findSchoolCourseDetailByClassroomType(classroomType, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByClassroomType
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByClassroomType(String classroomType, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolCourseDetailByClassroomType", startResult, maxRows, classroomType);
		return new LinkedHashSet<SchoolCourseDetail>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByMinStudentsContaining
	 *
	 */
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByMinStudentsContaining(String minStudents) throws DataAccessException {

		return findSchoolCourseDetailByMinStudentsContaining(minStudents, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByMinStudentsContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByMinStudentsContaining(String minStudents, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolCourseDetailByMinStudentsContaining", startResult, maxRows, minStudents);
		return new LinkedHashSet<SchoolCourseDetail>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByCourseNameContaining
	 *
	 */
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByCourseNameContaining(String courseName) throws DataAccessException {

		return findSchoolCourseDetailByCourseNameContaining(courseName, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByCourseNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByCourseNameContaining(String courseName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolCourseDetailByCourseNameContaining", startResult, maxRows, courseName);
		return new LinkedHashSet<SchoolCourseDetail>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByIfAppointment
	 *
	 */
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByIfAppointment(Boolean ifAppointment) throws DataAccessException {

		return findSchoolCourseDetailByIfAppointment(ifAppointment, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByIfAppointment
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByIfAppointment(Boolean ifAppointment, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolCourseDetailByIfAppointment", startResult, maxRows, ifAppointment);
		return new LinkedHashSet<SchoolCourseDetail>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByMajorDirectionName
	 *
	 */
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByMajorDirectionName(String majorDirectionName) throws DataAccessException {

		return findSchoolCourseDetailByMajorDirectionName(majorDirectionName, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByMajorDirectionName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByMajorDirectionName(String majorDirectionName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolCourseDetailByMajorDirectionName", startResult, maxRows, majorDirectionName);
		return new LinkedHashSet<SchoolCourseDetail>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByMinStudents
	 *
	 */
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByMinStudents(String minStudents) throws DataAccessException {

		return findSchoolCourseDetailByMinStudents(minStudents, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByMinStudents
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByMinStudents(String minStudents, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolCourseDetailByMinStudents", startResult, maxRows, minStudents);
		return new LinkedHashSet<SchoolCourseDetail>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByIfFlop
	 *
	 */
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByIfFlop(Boolean ifFlop) throws DataAccessException {

		return findSchoolCourseDetailByIfFlop(ifFlop, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByIfFlop
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByIfFlop(Boolean ifFlop, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolCourseDetailByIfFlop", startResult, maxRows, ifFlop);
		return new LinkedHashSet<SchoolCourseDetail>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByExperimentalProportion
	 *
	 */
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByExperimentalProportion(String experimentalProportion) throws DataAccessException {

		return findSchoolCourseDetailByExperimentalProportion(experimentalProportion, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByExperimentalProportion
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByExperimentalProportion(String experimentalProportion, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolCourseDetailByExperimentalProportion", startResult, maxRows, experimentalProportion);
		return new LinkedHashSet<SchoolCourseDetail>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByRequiredSoftware
	 *
	 */
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByRequiredSoftware(String requiredSoftware) throws DataAccessException {

		return findSchoolCourseDetailByRequiredSoftware(requiredSoftware, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByRequiredSoftware
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByRequiredSoftware(String requiredSoftware, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolCourseDetailByRequiredSoftware", startResult, maxRows, requiredSoftware);
		return new LinkedHashSet<SchoolCourseDetail>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByClasstime
	 *
	 */
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByClasstime(String classtime) throws DataAccessException {

		return findSchoolCourseDetailByClasstime(classtime, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByClasstime
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByClasstime(String classtime, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolCourseDetailByClasstime", startResult, maxRows, classtime);
		return new LinkedHashSet<SchoolCourseDetail>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByEvaluationMode
	 *
	 */
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByEvaluationMode(String evaluationMode) throws DataAccessException {

		return findSchoolCourseDetailByEvaluationMode(evaluationMode, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByEvaluationMode
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByEvaluationMode(String evaluationMode, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolCourseDetailByEvaluationMode", startResult, maxRows, evaluationMode);
		return new LinkedHashSet<SchoolCourseDetail>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByInstructionTypeContaining
	 *
	 */
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByInstructionTypeContaining(String instructionType) throws DataAccessException {

		return findSchoolCourseDetailByInstructionTypeContaining(instructionType, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByInstructionTypeContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByInstructionTypeContaining(String instructionType, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolCourseDetailByInstructionTypeContaining", startResult, maxRows, instructionType);
		return new LinkedHashSet<SchoolCourseDetail>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByInstructionType
	 *
	 */
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByInstructionType(String instructionType) throws DataAccessException {

		return findSchoolCourseDetailByInstructionType(instructionType, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByInstructionType
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByInstructionType(String instructionType, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolCourseDetailByInstructionType", startResult, maxRows, instructionType);
		return new LinkedHashSet<SchoolCourseDetail>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByWeekType
	 *
	 */
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByWeekType(String weekType) throws DataAccessException {

		return findSchoolCourseDetailByWeekType(weekType, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByWeekType
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByWeekType(String weekType, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolCourseDetailByWeekType", startResult, maxRows, weekType);
		return new LinkedHashSet<SchoolCourseDetail>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByEndClass
	 *
	 */
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByEndClass(Integer endClass) throws DataAccessException {

		return findSchoolCourseDetailByEndClass(endClass, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByEndClass
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByEndClass(Integer endClass, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolCourseDetailByEndClass", startResult, maxRows, endClass);
		return new LinkedHashSet<SchoolCourseDetail>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByEnvironmentalRequirements
	 *
	 */
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByEnvironmentalRequirements(String environmentalRequirements) throws DataAccessException {

		return findSchoolCourseDetailByEnvironmentalRequirements(environmentalRequirements, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByEnvironmentalRequirements
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByEnvironmentalRequirements(String environmentalRequirements, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolCourseDetailByEnvironmentalRequirements", startResult, maxRows, environmentalRequirements);
		return new LinkedHashSet<SchoolCourseDetail>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByPlanStudentNumber
	 *
	 */
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByPlanStudentNumber(String planStudentNumber) throws DataAccessException {

		return findSchoolCourseDetailByPlanStudentNumber(planStudentNumber, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByPlanStudentNumber
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByPlanStudentNumber(String planStudentNumber, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolCourseDetailByPlanStudentNumber", startResult, maxRows, planStudentNumber);
		return new LinkedHashSet<SchoolCourseDetail>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByIfTemporary
	 *
	 */
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByIfTemporary(Boolean ifTemporary) throws DataAccessException {

		return findSchoolCourseDetailByIfTemporary(ifTemporary, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByIfTemporary
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByIfTemporary(Boolean ifTemporary, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolCourseDetailByIfTemporary", startResult, maxRows, ifTemporary);
		return new LinkedHashSet<SchoolCourseDetail>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByCourseNumberContaining
	 *
	 */
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByCourseNumberContaining(String courseNumber) throws DataAccessException {

		return findSchoolCourseDetailByCourseNumberContaining(courseNumber, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByCourseNumberContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByCourseNumberContaining(String courseNumber, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolCourseDetailByCourseNumberContaining", startResult, maxRows, courseNumber);
		return new LinkedHashSet<SchoolCourseDetail>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByTotalClassHourContaining
	 *
	 */
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByTotalClassHourContaining(String totalClassHour) throws DataAccessException {

		return findSchoolCourseDetailByTotalClassHourContaining(totalClassHour, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByTotalClassHourContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByTotalClassHourContaining(String totalClassHour, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolCourseDetailByTotalClassHourContaining", startResult, maxRows, totalClassHour);
		return new LinkedHashSet<SchoolCourseDetail>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByTotalClassHour
	 *
	 */
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByTotalClassHour(String totalClassHour) throws DataAccessException {

		return findSchoolCourseDetailByTotalClassHour(totalClassHour, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByTotalClassHour
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByTotalClassHour(String totalClassHour, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolCourseDetailByTotalClassHour", startResult, maxRows, totalClassHour);
		return new LinkedHashSet<SchoolCourseDetail>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByDetailId
	 *
	 */
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByDetailId(Integer detailId) throws DataAccessException {

		return findSchoolCourseDetailByDetailId(detailId, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByDetailId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByDetailId(Integer detailId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolCourseDetailByDetailId", startResult, maxRows, detailId);
		return new LinkedHashSet<SchoolCourseDetail>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByRequiredSoftwareContaining
	 *
	 */
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByRequiredSoftwareContaining(String requiredSoftware) throws DataAccessException {

		return findSchoolCourseDetailByRequiredSoftwareContaining(requiredSoftware, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByRequiredSoftwareContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByRequiredSoftwareContaining(String requiredSoftware, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolCourseDetailByRequiredSoftwareContaining", startResult, maxRows, requiredSoftware);
		return new LinkedHashSet<SchoolCourseDetail>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByCourseDetailNoContaining
	 *
	 */
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByCourseDetailNoContaining(String courseDetailNo) throws DataAccessException {

		return findSchoolCourseDetailByCourseDetailNoContaining(courseDetailNo, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByCourseDetailNoContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByCourseDetailNoContaining(String courseDetailNo, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolCourseDetailByCourseDetailNoContaining", startResult, maxRows, courseDetailNo);
		return new LinkedHashSet<SchoolCourseDetail>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByCourseTypeName
	 *
	 */
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByCourseTypeName(String courseTypeName) throws DataAccessException {

		return findSchoolCourseDetailByCourseTypeName(courseTypeName, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByCourseTypeName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByCourseTypeName(String courseTypeName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolCourseDetailByCourseTypeName", startResult, maxRows, courseTypeName);
		return new LinkedHashSet<SchoolCourseDetail>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByComputeTimeContaining
	 *
	 */
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByComputeTimeContaining(String computeTime) throws DataAccessException {

		return findSchoolCourseDetailByComputeTimeContaining(computeTime, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByComputeTimeContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByComputeTimeContaining(String computeTime, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolCourseDetailByComputeTimeContaining", startResult, maxRows, computeTime);
		return new LinkedHashSet<SchoolCourseDetail>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByMajorName
	 *
	 */
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByMajorName(String majorName) throws DataAccessException {

		return findSchoolCourseDetailByMajorName(majorName, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByMajorName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByMajorName(String majorName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolCourseDetailByMajorName", startResult, maxRows, majorName);
		return new LinkedHashSet<SchoolCourseDetail>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByTotalWeeksContaining
	 *
	 */
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByTotalWeeksContaining(String totalWeeks) throws DataAccessException {

		return findSchoolCourseDetailByTotalWeeksContaining(totalWeeks, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByTotalWeeksContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByTotalWeeksContaining(String totalWeeks, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolCourseDetailByTotalWeeksContaining", startResult, maxRows, totalWeeks);
		return new LinkedHashSet<SchoolCourseDetail>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByStartClass
	 *
	 */
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByStartClass(Integer startClass) throws DataAccessException {

		return findSchoolCourseDetailByStartClass(startClass, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByStartClass
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByStartClass(Integer startClass, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolCourseDetailByStartClass", startResult, maxRows, startClass);
		return new LinkedHashSet<SchoolCourseDetail>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByPlanStudentNumberContaining
	 *
	 */
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByPlanStudentNumberContaining(String planStudentNumber) throws DataAccessException {

		return findSchoolCourseDetailByPlanStudentNumberContaining(planStudentNumber, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByPlanStudentNumberContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByPlanStudentNumberContaining(String planStudentNumber, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolCourseDetailByPlanStudentNumberContaining", startResult, maxRows, planStudentNumber);
		return new LinkedHashSet<SchoolCourseDetail>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByEquipmentConfiguration
	 *
	 */
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByEquipmentConfiguration(String equipmentConfiguration) throws DataAccessException {

		return findSchoolCourseDetailByEquipmentConfiguration(equipmentConfiguration, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByEquipmentConfiguration
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByEquipmentConfiguration(String equipmentConfiguration, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolCourseDetailByEquipmentConfiguration", startResult, maxRows, equipmentConfiguration);
		return new LinkedHashSet<SchoolCourseDetail>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByCourseNumber
	 *
	 */
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByCourseNumber(String courseNumber) throws DataAccessException {

		return findSchoolCourseDetailByCourseNumber(courseNumber, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByCourseNumber
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByCourseNumber(String courseNumber, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolCourseDetailByCourseNumber", startResult, maxRows, courseNumber);
		return new LinkedHashSet<SchoolCourseDetail>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByTutorContaining
	 *
	 */
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByTutorContaining(String tutor) throws DataAccessException {

		return findSchoolCourseDetailByTutorContaining(tutor, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByTutorContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByTutorContaining(String tutor, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolCourseDetailByTutorContaining", startResult, maxRows, tutor);
		return new LinkedHashSet<SchoolCourseDetail>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByInstructionTypeNameContaining
	 *
	 */
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByInstructionTypeNameContaining(String instructionTypeName) throws DataAccessException {

		return findSchoolCourseDetailByInstructionTypeNameContaining(instructionTypeName, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByInstructionTypeNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByInstructionTypeNameContaining(String instructionTypeName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolCourseDetailByInstructionTypeNameContaining", startResult, maxRows, instructionTypeName);
		return new LinkedHashSet<SchoolCourseDetail>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByWeekday
	 *
	 */
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByWeekday(Integer weekday) throws DataAccessException {

		return findSchoolCourseDetailByWeekday(weekday, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByWeekday
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByWeekday(Integer weekday, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolCourseDetailByWeekday", startResult, maxRows, weekday);
		return new LinkedHashSet<SchoolCourseDetail>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByEnvironmentalRequirementsContaining
	 *
	 */
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByEnvironmentalRequirementsContaining(String environmentalRequirements) throws DataAccessException {

		return findSchoolCourseDetailByEnvironmentalRequirementsContaining(environmentalRequirements, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByEnvironmentalRequirementsContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByEnvironmentalRequirementsContaining(String environmentalRequirements, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolCourseDetailByEnvironmentalRequirementsContaining", startResult, maxRows, environmentalRequirements);
		return new LinkedHashSet<SchoolCourseDetail>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByEvaluationModeName
	 *
	 */
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByEvaluationModeName(String evaluationModeName) throws DataAccessException {

		return findSchoolCourseDetailByEvaluationModeName(evaluationModeName, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByEvaluationModeName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByEvaluationModeName(String evaluationModeName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolCourseDetailByEvaluationModeName", startResult, maxRows, evaluationModeName);
		return new LinkedHashSet<SchoolCourseDetail>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByCourseDetailNo
	 *
	 */
	@Transactional
	public SchoolCourseDetail findSchoolCourseDetailByCourseDetailNo(String courseDetailNo) throws DataAccessException {

		return findSchoolCourseDetailByCourseDetailNo(courseDetailNo, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByCourseDetailNo
	 *
	 */

	@Transactional
	public SchoolCourseDetail findSchoolCourseDetailByCourseDetailNo(String courseDetailNo, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findSchoolCourseDetailByCourseDetailNo", startResult, maxRows, courseDetailNo);
			return (net.xidlims.domain.SchoolCourseDetail) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByUseweek
	 *
	 */
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByUseweek(String useweek) throws DataAccessException {

		return findSchoolCourseDetailByUseweek(useweek, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByUseweek
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByUseweek(String useweek, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolCourseDetailByUseweek", startResult, maxRows, useweek);
		return new LinkedHashSet<SchoolCourseDetail>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByStartWeek
	 *
	 */
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByStartWeek(Integer startWeek) throws DataAccessException {

		return findSchoolCourseDetailByStartWeek(startWeek, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByStartWeek
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByStartWeek(Integer startWeek, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolCourseDetailByStartWeek", startResult, maxRows, startWeek);
		return new LinkedHashSet<SchoolCourseDetail>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByUseweekContaining
	 *
	 */
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByUseweekContaining(String useweek) throws DataAccessException {

		return findSchoolCourseDetailByUseweekContaining(useweek, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByUseweekContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByUseweekContaining(String useweek, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolCourseDetailByUseweekContaining", startResult, maxRows, useweek);
		return new LinkedHashSet<SchoolCourseDetail>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByMajorDirectionNameContaining
	 *
	 */
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByMajorDirectionNameContaining(String majorDirectionName) throws DataAccessException {

		return findSchoolCourseDetailByMajorDirectionNameContaining(majorDirectionName, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByMajorDirectionNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByMajorDirectionNameContaining(String majorDirectionName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolCourseDetailByMajorDirectionNameContaining", startResult, maxRows, majorDirectionName);
		return new LinkedHashSet<SchoolCourseDetail>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByClassesExperiment
	 *
	 */
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByClassesExperiment(String classesExperiment) throws DataAccessException {

		return findSchoolCourseDetailByClassesExperiment(classesExperiment, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByClassesExperiment
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByClassesExperiment(String classesExperiment, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolCourseDetailByClassesExperiment", startResult, maxRows, classesExperiment);
		return new LinkedHashSet<SchoolCourseDetail>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByComputeTime
	 *
	 */
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByComputeTime(String computeTime) throws DataAccessException {

		return findSchoolCourseDetailByComputeTime(computeTime, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByComputeTime
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByComputeTime(String computeTime, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolCourseDetailByComputeTime", startResult, maxRows, computeTime);
		return new LinkedHashSet<SchoolCourseDetail>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByExperimentalClassHour
	 *
	 */
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByExperimentalClassHour(String experimentalClassHour) throws DataAccessException {

		return findSchoolCourseDetailByExperimentalClassHour(experimentalClassHour, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByExperimentalClassHour
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByExperimentalClassHour(String experimentalClassHour, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolCourseDetailByExperimentalClassHour", startResult, maxRows, experimentalClassHour);
		return new LinkedHashSet<SchoolCourseDetail>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByTutor
	 *
	 */
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByTutor(String tutor) throws DataAccessException {

		return findSchoolCourseDetailByTutor(tutor, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByTutor
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByTutor(String tutor, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolCourseDetailByTutor", startResult, maxRows, tutor);
		return new LinkedHashSet<SchoolCourseDetail>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByIfArrange
	 *
	 */
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByIfArrange(Boolean ifArrange) throws DataAccessException {

		return findSchoolCourseDetailByIfArrange(ifArrange, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByIfArrange
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByIfArrange(Boolean ifArrange, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolCourseDetailByIfArrange", startResult, maxRows, ifArrange);
		return new LinkedHashSet<SchoolCourseDetail>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByCourseTypeNameContaining
	 *
	 */
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByCourseTypeNameContaining(String courseTypeName) throws DataAccessException {

		return findSchoolCourseDetailByCourseTypeNameContaining(courseTypeName, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByCourseTypeNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByCourseTypeNameContaining(String courseTypeName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolCourseDetailByCourseTypeNameContaining", startResult, maxRows, courseTypeName);
		return new LinkedHashSet<SchoolCourseDetail>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByMaxStudents
	 *
	 */
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByMaxStudents(String maxStudents) throws DataAccessException {

		return findSchoolCourseDetailByMaxStudents(maxStudents, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByMaxStudents
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByMaxStudents(String maxStudents, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolCourseDetailByMaxStudents", startResult, maxRows, maxStudents);
		return new LinkedHashSet<SchoolCourseDetail>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByEndWeek
	 *
	 */
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByEndWeek(Integer endWeek) throws DataAccessException {

		return findSchoolCourseDetailByEndWeek(endWeek, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByEndWeek
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByEndWeek(Integer endWeek, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolCourseDetailByEndWeek", startResult, maxRows, endWeek);
		return new LinkedHashSet<SchoolCourseDetail>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolCourseDetailById
	 *
	 */
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailById(Integer id) throws DataAccessException {

		return findSchoolCourseDetailById(id, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseDetailById
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailById(Integer id, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolCourseDetailById", startResult, maxRows, id);
		return new LinkedHashSet<SchoolCourseDetail>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByCourseName
	 *
	 */
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByCourseName(String courseName) throws DataAccessException {

		return findSchoolCourseDetailByCourseName(courseName, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByCourseName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByCourseName(String courseName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolCourseDetailByCourseName", startResult, maxRows, courseName);
		return new LinkedHashSet<SchoolCourseDetail>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByInstructionTypeName
	 *
	 */
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByInstructionTypeName(String instructionTypeName) throws DataAccessException {

		return findSchoolCourseDetailByInstructionTypeName(instructionTypeName, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByInstructionTypeName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByInstructionTypeName(String instructionTypeName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolCourseDetailByInstructionTypeName", startResult, maxRows, instructionTypeName);
		return new LinkedHashSet<SchoolCourseDetail>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByClasstimeContaining
	 *
	 */
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByClasstimeContaining(String classtime) throws DataAccessException {

		return findSchoolCourseDetailByClasstimeContaining(classtime, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByClasstimeContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByClasstimeContaining(String classtime, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolCourseDetailByClasstimeContaining", startResult, maxRows, classtime);
		return new LinkedHashSet<SchoolCourseDetail>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByExperimentalProportionContaining
	 *
	 */
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByExperimentalProportionContaining(String experimentalProportion) throws DataAccessException {

		return findSchoolCourseDetailByExperimentalProportionContaining(experimentalProportion, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByExperimentalProportionContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByExperimentalProportionContaining(String experimentalProportion, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolCourseDetailByExperimentalProportionContaining", startResult, maxRows, experimentalProportion);
		return new LinkedHashSet<SchoolCourseDetail>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByTotalWeeks
	 *
	 */
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByTotalWeeks(String totalWeeks) throws DataAccessException {

		return findSchoolCourseDetailByTotalWeeks(totalWeeks, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByTotalWeeks
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByTotalWeeks(String totalWeeks, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolCourseDetailByTotalWeeks", startResult, maxRows, totalWeeks);
		return new LinkedHashSet<SchoolCourseDetail>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByNeedId
	 *
	 */
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByNeedId(Integer needId) throws DataAccessException {

		return findSchoolCourseDetailByNeedId(needId, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByNeedId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByNeedId(Integer needId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolCourseDetailByNeedId", startResult, maxRows, needId);
		return new LinkedHashSet<SchoolCourseDetail>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByIfAllowCourse
	 *
	 */
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByIfAllowCourse(Boolean ifAllowCourse) throws DataAccessException {

		return findSchoolCourseDetailByIfAllowCourse(ifAllowCourse, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByIfAllowCourse
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByIfAllowCourse(Boolean ifAllowCourse, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolCourseDetailByIfAllowCourse", startResult, maxRows, ifAllowCourse);
		return new LinkedHashSet<SchoolCourseDetail>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByWeeksExperiment
	 *
	 */
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByWeeksExperiment(String weeksExperiment) throws DataAccessException {

		return findSchoolCourseDetailByWeeksExperiment(weeksExperiment, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByWeeksExperiment
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByWeeksExperiment(String weeksExperiment, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolCourseDetailByWeeksExperiment", startResult, maxRows, weeksExperiment);
		return new LinkedHashSet<SchoolCourseDetail>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByWeeksExperimentContaining
	 *
	 */
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByWeeksExperimentContaining(String weeksExperiment) throws DataAccessException {

		return findSchoolCourseDetailByWeeksExperimentContaining(weeksExperiment, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByWeeksExperimentContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByWeeksExperimentContaining(String weeksExperiment, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolCourseDetailByWeeksExperimentContaining", startResult, maxRows, weeksExperiment);
		return new LinkedHashSet<SchoolCourseDetail>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByPrimaryKey
	 *
	 */
	@Transactional
	public SchoolCourseDetail findSchoolCourseDetailByPrimaryKey(String courseDetailNo) throws DataAccessException {

		return findSchoolCourseDetailByPrimaryKey(courseDetailNo, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByPrimaryKey
	 *
	 */

	@Transactional
	public SchoolCourseDetail findSchoolCourseDetailByPrimaryKey(String courseDetailNo, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findSchoolCourseDetailByPrimaryKey", startResult, maxRows, courseDetailNo);
			return (net.xidlims.domain.SchoolCourseDetail) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByMaxStudentsContaining
	 *
	 */
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByMaxStudentsContaining(String maxStudents) throws DataAccessException {

		return findSchoolCourseDetailByMaxStudentsContaining(maxStudents, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByMaxStudentsContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByMaxStudentsContaining(String maxStudents, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolCourseDetailByMaxStudentsContaining", startResult, maxRows, maxStudents);
		return new LinkedHashSet<SchoolCourseDetail>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByClassesExperimentContaining
	 *
	 */
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByClassesExperimentContaining(String classesExperiment) throws DataAccessException {

		return findSchoolCourseDetailByClassesExperimentContaining(classesExperiment, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByClassesExperimentContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByClassesExperimentContaining(String classesExperiment, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolCourseDetailByClassesExperimentContaining", startResult, maxRows, classesExperiment);
		return new LinkedHashSet<SchoolCourseDetail>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByClassroomTypeContaining
	 *
	 */
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByClassroomTypeContaining(String classroomType) throws DataAccessException {

		return findSchoolCourseDetailByClassroomTypeContaining(classroomType, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByClassroomTypeContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByClassroomTypeContaining(String classroomType, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolCourseDetailByClassroomTypeContaining", startResult, maxRows, classroomType);
		return new LinkedHashSet<SchoolCourseDetail>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByIfSelect
	 *
	 */
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByIfSelect(Boolean ifSelect) throws DataAccessException {

		return findSchoolCourseDetailByIfSelect(ifSelect, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByIfSelect
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByIfSelect(Boolean ifSelect, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolCourseDetailByIfSelect", startResult, maxRows, ifSelect);
		return new LinkedHashSet<SchoolCourseDetail>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByWeekTypeContaining
	 *
	 */
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByWeekTypeContaining(String weekType) throws DataAccessException {

		return findSchoolCourseDetailByWeekTypeContaining(weekType, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByWeekTypeContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByWeekTypeContaining(String weekType, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolCourseDetailByWeekTypeContaining", startResult, maxRows, weekType);
		return new LinkedHashSet<SchoolCourseDetail>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByMajorNameContaining
	 *
	 */
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByMajorNameContaining(String majorName) throws DataAccessException {

		return findSchoolCourseDetailByMajorNameContaining(majorName, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByMajorNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByMajorNameContaining(String majorName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolCourseDetailByMajorNameContaining", startResult, maxRows, majorName);
		return new LinkedHashSet<SchoolCourseDetail>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByEquipmentConfigurationContaining
	 *
	 */
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByEquipmentConfigurationContaining(String equipmentConfiguration) throws DataAccessException {

		return findSchoolCourseDetailByEquipmentConfigurationContaining(equipmentConfiguration, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByEquipmentConfigurationContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByEquipmentConfigurationContaining(String equipmentConfiguration, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolCourseDetailByEquipmentConfigurationContaining", startResult, maxRows, equipmentConfiguration);
		return new LinkedHashSet<SchoolCourseDetail>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByExperimentalClassHourContaining
	 *
	 */
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByExperimentalClassHourContaining(String experimentalClassHour) throws DataAccessException {

		return findSchoolCourseDetailByExperimentalClassHourContaining(experimentalClassHour, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByExperimentalClassHourContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByExperimentalClassHourContaining(String experimentalClassHour, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolCourseDetailByExperimentalClassHourContaining", startResult, maxRows, experimentalClassHour);
		return new LinkedHashSet<SchoolCourseDetail>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllSchoolCourseDetails
	 *
	 */
	@Transactional
	public Set<SchoolCourseDetail> findAllSchoolCourseDetails() throws DataAccessException {

		return findAllSchoolCourseDetails(-1, -1);
	}

	/**
	 * JPQL Query - findAllSchoolCourseDetails
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourseDetail> findAllSchoolCourseDetails(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllSchoolCourseDetails", startResult, maxRows);
		return new LinkedHashSet<SchoolCourseDetail>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByEvaluationModeContaining
	 *
	 */
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByEvaluationModeContaining(String evaluationMode) throws DataAccessException {

		return findSchoolCourseDetailByEvaluationModeContaining(evaluationMode, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByEvaluationModeContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByEvaluationModeContaining(String evaluationMode, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolCourseDetailByEvaluationModeContaining", startResult, maxRows, evaluationMode);
		return new LinkedHashSet<SchoolCourseDetail>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByTotalClasses
	 *
	 */
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByTotalClasses(Integer totalClasses) throws DataAccessException {

		return findSchoolCourseDetailByTotalClasses(totalClasses, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByTotalClasses
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByTotalClasses(Integer totalClasses, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolCourseDetailByTotalClasses", startResult, maxRows, totalClasses);
		return new LinkedHashSet<SchoolCourseDetail>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByEvaluationModeNameContaining
	 *
	 */
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByEvaluationModeNameContaining(String evaluationModeName) throws DataAccessException {

		return findSchoolCourseDetailByEvaluationModeNameContaining(evaluationModeName, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByEvaluationModeNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByEvaluationModeNameContaining(String evaluationModeName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolCourseDetailByEvaluationModeNameContaining", startResult, maxRows, evaluationModeName);
		return new LinkedHashSet<SchoolCourseDetail>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByWeekClassHourContaining
	 *
	 */
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByWeekClassHourContaining(String weekClassHour) throws DataAccessException {

		return findSchoolCourseDetailByWeekClassHourContaining(weekClassHour, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseDetailByWeekClassHourContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourseDetail> findSchoolCourseDetailByWeekClassHourContaining(String weekClassHour, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolCourseDetailByWeekClassHourContaining", startResult, maxRows, weekClassHour);
		return new LinkedHashSet<SchoolCourseDetail>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(SchoolCourseDetail entity) {
		return true;
	}
}
