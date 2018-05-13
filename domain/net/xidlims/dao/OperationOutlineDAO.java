package net.xidlims.dao;

import java.util.Set;

import net.xidlims.domain.OperationOutline;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage OperationOutline entities.
 * 
 */
public interface OperationOutlineDAO extends JpaDao<OperationOutline> {

	/**
	 * JPQL Query - findOperationOutlineByPrimaryKey
	 *
	 */
	public OperationOutline findOperationOutlineByPrimaryKey(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findOperationOutlineByPrimaryKey
	 *
	 */
	public OperationOutline findOperationOutlineByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findOperationOutlineByUseMaterialsContaining
	 *
	 */
	public Set<OperationOutline> findOperationOutlineByUseMaterialsContaining(String useMaterials) throws DataAccessException;

	/**
	 * JPQL Query - findOperationOutlineByUseMaterialsContaining
	 *
	 */
	public Set<OperationOutline> findOperationOutlineByUseMaterialsContaining(String useMaterials, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findOperationOutlineByLabOutlineName
	 *
	 */
	public Set<OperationOutline> findOperationOutlineByLabOutlineName(String labOutlineName) throws DataAccessException;

	/**
	 * JPQL Query - findOperationOutlineByLabOutlineName
	 *
	 */
	public Set<OperationOutline> findOperationOutlineByLabOutlineName(String labOutlineName, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findOperationOutlineByAssResultsPerEvaluation
	 *
	 */
	public Set<OperationOutline> findOperationOutlineByAssResultsPerEvaluation(String assResultsPerEvaluation) throws DataAccessException;

	/**
	 * JPQL Query - findOperationOutlineByAssResultsPerEvaluation
	 *
	 */
	public Set<OperationOutline> findOperationOutlineByAssResultsPerEvaluation(String assResultsPerEvaluation, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findOperationOutlineByOutlineCourseTeachingTarget
	 *
	 */
	public Set<OperationOutline> findOperationOutlineByOutlineCourseTeachingTarget(String outlineCourseTeachingTarget) throws DataAccessException;

	/**
	 * JPQL Query - findOperationOutlineByOutlineCourseTeachingTarget
	 *
	 */
	public Set<OperationOutline> findOperationOutlineByOutlineCourseTeachingTarget(String outlineCourseTeachingTarget, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findOperationOutlineByCoursesAdvice
	 *
	 */
	public Set<OperationOutline> findOperationOutlineByCoursesAdvice(String coursesAdvice) throws DataAccessException;

	/**
	 * JPQL Query - findOperationOutlineByCoursesAdvice
	 *
	 */
	public Set<OperationOutline> findOperationOutlineByCoursesAdvice(String coursesAdvice, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllOperationOutlines
	 *
	 */
	public Set<OperationOutline> findAllOperationOutlines() throws DataAccessException;

	/**
	 * JPQL Query - findAllOperationOutlines
	 *
	 */
	public Set<OperationOutline> findAllOperationOutlines(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findOperationOutlineByEnNameContaining
	 *
	 */
	public Set<OperationOutline> findOperationOutlineByEnNameContaining(String enName) throws DataAccessException;

	/**
	 * JPQL Query - findOperationOutlineByEnNameContaining
	 *
	 */
	public Set<OperationOutline> findOperationOutlineByEnNameContaining(String enName, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findOperationOutlineByLabOutlineNameContaining
	 *
	 */
	public Set<OperationOutline> findOperationOutlineByLabOutlineNameContaining(String labOutlineName_1) throws DataAccessException;

	/**
	 * JPQL Query - findOperationOutlineByLabOutlineNameContaining
	 *
	 */
	public Set<OperationOutline> findOperationOutlineByLabOutlineNameContaining(String labOutlineName_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findOperationOutlineByProfessional
	 *
	 */
	public Set<OperationOutline> findOperationOutlineByProfessional(String professional) throws DataAccessException;

	/**
	 * JPQL Query - findOperationOutlineByProfessional
	 *
	 */
	public Set<OperationOutline> findOperationOutlineByProfessional(String professional, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findOperationOutlineByOutlineCourseTeachingTargetOver
	 *
	 */
	public Set<OperationOutline> findOperationOutlineByOutlineCourseTeachingTargetOver(String outlineCourseTeachingTargetOver) throws DataAccessException;

	/**
	 * JPQL Query - findOperationOutlineByOutlineCourseTeachingTargetOver
	 *
	 */
	public Set<OperationOutline> findOperationOutlineByOutlineCourseTeachingTargetOver(String outlineCourseTeachingTargetOver, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findOperationOutlineByEnName
	 *
	 */
	public Set<OperationOutline> findOperationOutlineByEnName(String enName_1) throws DataAccessException;

	/**
	 * JPQL Query - findOperationOutlineByEnName
	 *
	 */
	public Set<OperationOutline> findOperationOutlineByEnName(String enName_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findOperationOutlineByCourseDescription
	 *
	 */
	public Set<OperationOutline> findOperationOutlineByCourseDescription(String courseDescription) throws DataAccessException;

	/**
	 * JPQL Query - findOperationOutlineByCourseDescription
	 *
	 */
	public Set<OperationOutline> findOperationOutlineByCourseDescription(String courseDescription, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findOperationOutlineById
	 *
	 */
	public OperationOutline findOperationOutlineById(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findOperationOutlineById
	 *
	 */
	public OperationOutline findOperationOutlineById(Integer id_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findOperationOutlineByProfessionalContaining
	 *
	 */
	public Set<OperationOutline> findOperationOutlineByProfessionalContaining(String professional_1) throws DataAccessException;

	/**
	 * JPQL Query - findOperationOutlineByProfessionalContaining
	 *
	 */
	public Set<OperationOutline> findOperationOutlineByProfessionalContaining(String professional_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findOperationOutlineByUseMaterials
	 *
	 */
	public Set<OperationOutline> findOperationOutlineByUseMaterials(String useMaterials_1) throws DataAccessException;

	/**
	 * JPQL Query - findOperationOutlineByUseMaterials
	 *
	 */
	public Set<OperationOutline> findOperationOutlineByUseMaterials(String useMaterials_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findOperationOutlineByBasicContentCourse
	 *
	 */
	public Set<OperationOutline> findOperationOutlineByBasicContentCourse(String basicContentCourse) throws DataAccessException;

	/**
	 * JPQL Query - findOperationOutlineByBasicContentCourse
	 *
	 */
	public Set<OperationOutline> findOperationOutlineByBasicContentCourse(String basicContentCourse, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findOperationOutlineByBasicRequirementsCourse
	 *
	 */
	public Set<OperationOutline> findOperationOutlineByBasicRequirementsCourse(String basicRequirementsCourse) throws DataAccessException;

	/**
	 * JPQL Query - findOperationOutlineByBasicRequirementsCourse
	 *
	 */
	public Set<OperationOutline> findOperationOutlineByBasicRequirementsCourse(String basicRequirementsCourse, int startResult, int maxRows) throws DataAccessException;

}