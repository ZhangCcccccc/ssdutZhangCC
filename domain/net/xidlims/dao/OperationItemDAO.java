package net.xidlims.dao;

import java.math.BigDecimal;

import java.util.Set;

import net.xidlims.domain.OperationItem;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage OperationItem entities.
 * 
 */
public interface OperationItemDAO extends JpaDao<OperationItem> {

	/**
	 * JPQL Query - findOperationItemByLpStudentNumberGroupContaining
	 *
	 */
	public Set<OperationItem> findOperationItemByLpStudentNumberGroupContaining(String lpStudentNumberGroup) throws DataAccessException;

	/**
	 * JPQL Query - findOperationItemByLpStudentNumberGroupContaining
	 *
	 */
	public Set<OperationItem> findOperationItemByLpStudentNumberGroupContaining(String lpStudentNumberGroup, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findOperationItemByLpCodeCustom
	 *
	 */
	public Set<OperationItem> findOperationItemByLpCodeCustom(String lpCodeCustom) throws DataAccessException;

	/**
	 * JPQL Query - findOperationItemByLpCodeCustom
	 *
	 */
	public Set<OperationItem> findOperationItemByLpCodeCustom(String lpCodeCustom, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findOperationItemByLpDepartmentHoursTotal
	 *
	 */
	public Set<OperationItem> findOperationItemByLpDepartmentHoursTotal(Integer lpDepartmentHoursTotal) throws DataAccessException;

	/**
	 * JPQL Query - findOperationItemByLpDepartmentHoursTotal
	 *
	 */
	public Set<OperationItem> findOperationItemByLpDepartmentHoursTotal(Integer lpDepartmentHoursTotal, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findOperationItemByLpCodeCustomContaining
	 *
	 */
	public Set<OperationItem> findOperationItemByLpCodeCustomContaining(String lpCodeCustom_1) throws DataAccessException;

	/**
	 * JPQL Query - findOperationItemByLpCodeCustomContaining
	 *
	 */
	public Set<OperationItem> findOperationItemByLpCodeCustomContaining(String lpCodeCustom_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findOperationItemByLpIntroduction
	 *
	 */
	public Set<OperationItem> findOperationItemByLpIntroduction(String lpIntroduction) throws DataAccessException;

	/**
	 * JPQL Query - findOperationItemByLpIntroduction
	 *
	 */
	public Set<OperationItem> findOperationItemByLpIntroduction(String lpIntroduction, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findOperationItemByLpMajorFit
	 *
	 */
	public Set<OperationItem> findOperationItemByLpMajorFit(String lpMajorFit) throws DataAccessException;

	/**
	 * JPQL Query - findOperationItemByLpMajorFit
	 *
	 */
	public Set<OperationItem> findOperationItemByLpMajorFit(String lpMajorFit, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findOperationItemByLpConfigMaterial
	 *
	 */
	public Set<OperationItem> findOperationItemByLpConfigMaterial(String lpConfigMaterial) throws DataAccessException;

	/**
	 * JPQL Query - findOperationItemByLpConfigMaterial
	 *
	 */
	public Set<OperationItem> findOperationItemByLpConfigMaterial(String lpConfigMaterial, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllOperationItems
	 *
	 */
	public Set<OperationItem> findAllOperationItems() throws DataAccessException;

	/**
	 * JPQL Query - findAllOperationItems
	 *
	 */
	public Set<OperationItem> findAllOperationItems(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findOperationItemByLpSetNumber
	 *
	 */
	public Set<OperationItem> findOperationItemByLpSetNumber(String lpSetNumber) throws DataAccessException;

	/**
	 * JPQL Query - findOperationItemByLpSetNumber
	 *
	 */
	public Set<OperationItem> findOperationItemByLpSetNumber(String lpSetNumber, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findOperationItemByLpGuideBookTitleContaining
	 *
	 */
	public Set<OperationItem> findOperationItemByLpGuideBookTitleContaining(String lpGuideBookTitle) throws DataAccessException;

	/**
	 * JPQL Query - findOperationItemByLpGuideBookTitleContaining
	 *
	 */
	public Set<OperationItem> findOperationItemByLpGuideBookTitleContaining(String lpGuideBookTitle, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findOperationItemByLpGuideBookAuthor
	 *
	 */
	public Set<OperationItem> findOperationItemByLpGuideBookAuthor(String lpGuideBookAuthor) throws DataAccessException;

	/**
	 * JPQL Query - findOperationItemByLpGuideBookAuthor
	 *
	 */
	public Set<OperationItem> findOperationItemByLpGuideBookAuthor(String lpGuideBookAuthor, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findOperationItemByLpNameContaining
	 *
	 */
	public Set<OperationItem> findOperationItemByLpNameContaining(String lpName) throws DataAccessException;

	/**
	 * JPQL Query - findOperationItemByLpNameContaining
	 *
	 */
	public Set<OperationItem> findOperationItemByLpNameContaining(String lpName, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findOperationItemByLpGuideBookAuthorContaining
	 *
	 */
	public Set<OperationItem> findOperationItemByLpGuideBookAuthorContaining(String lpGuideBookAuthor_1) throws DataAccessException;

	/**
	 * JPQL Query - findOperationItemByLpGuideBookAuthorContaining
	 *
	 */
	public Set<OperationItem> findOperationItemByLpGuideBookAuthorContaining(String lpGuideBookAuthor_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findOperationItemByLpCourseHoursTotal
	 *
	 */
	public Set<OperationItem> findOperationItemByLpCourseHoursTotal(Integer lpCourseHoursTotal) throws DataAccessException;

	/**
	 * JPQL Query - findOperationItemByLpCourseHoursTotal
	 *
	 */
	public Set<OperationItem> findOperationItemByLpCourseHoursTotal(Integer lpCourseHoursTotal, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findOperationItemByLpGuideBookTitle
	 *
	 */
	public Set<OperationItem> findOperationItemByLpGuideBookTitle(String lpGuideBookTitle_1) throws DataAccessException;

	/**
	 * JPQL Query - findOperationItemByLpGuideBookTitle
	 *
	 */
	public Set<OperationItem> findOperationItemByLpGuideBookTitle(String lpGuideBookTitle_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findOperationItemByLpAssessmentMethods
	 *
	 */
	public Set<OperationItem> findOperationItemByLpAssessmentMethods(String lpAssessmentMethods) throws DataAccessException;

	/**
	 * JPQL Query - findOperationItemByLpAssessmentMethods
	 *
	 */
	public Set<OperationItem> findOperationItemByLpAssessmentMethods(String lpAssessmentMethods, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findOperationItemByPrimaryKey
	 *
	 */
	public OperationItem findOperationItemByPrimaryKey(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findOperationItemByPrimaryKey
	 *
	 */
	public OperationItem findOperationItemByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findOperationItemByLpYearsPeopleNumberPlan
	 *
	 */
	public Set<OperationItem> findOperationItemByLpYearsPeopleNumberPlan(Integer lpYearsPeopleNumberPlan) throws DataAccessException;

	/**
	 * JPQL Query - findOperationItemByLpYearsPeopleNumberPlan
	 *
	 */
	public Set<OperationItem> findOperationItemByLpYearsPeopleNumberPlan(Integer lpYearsPeopleNumberPlan, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findOperationItemByLpPurposes
	 *
	 */
	public Set<OperationItem> findOperationItemByLpPurposes(String lpPurposes) throws DataAccessException;

	/**
	 * JPQL Query - findOperationItemByLpPurposes
	 *
	 */
	public Set<OperationItem> findOperationItemByLpPurposes(String lpPurposes, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findOperationItemByLpStudentNumberGroup
	 *
	 */
	public Set<OperationItem> findOperationItemByLpStudentNumberGroup(String lpStudentNumberGroup_1) throws DataAccessException;

	/**
	 * JPQL Query - findOperationItemByLpStudentNumberGroup
	 *
	 */
	public Set<OperationItem> findOperationItemByLpStudentNumberGroup(String lpStudentNumberGroup_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findOperationItemByLpMajorFitContaining
	 *
	 */
	public Set<OperationItem> findOperationItemByLpMajorFitContaining(String lpMajorFit_1) throws DataAccessException;

	/**
	 * JPQL Query - findOperationItemByLpMajorFitContaining
	 *
	 */
	public Set<OperationItem> findOperationItemByLpMajorFitContaining(String lpMajorFit_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findOperationItemById
	 *
	 */
	public OperationItem findOperationItemById(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findOperationItemById
	 *
	 */
	public OperationItem findOperationItemById(Integer id_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findOperationItemByLpAssessmentMethodsContaining
	 *
	 */
	public Set<OperationItem> findOperationItemByLpAssessmentMethodsContaining(String lpAssessmentMethods_1) throws DataAccessException;

	/**
	 * JPQL Query - findOperationItemByLpAssessmentMethodsContaining
	 *
	 */
	public Set<OperationItem> findOperationItemByLpAssessmentMethodsContaining(String lpAssessmentMethods_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findOperationItemByLpCollege
	 *
	 */
	public Set<OperationItem> findOperationItemByLpCollege(String lpCollege) throws DataAccessException;

	/**
	 * JPQL Query - findOperationItemByLpCollege
	 *
	 */
	public Set<OperationItem> findOperationItemByLpCollege(String lpCollege, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findOperationItemByLpStudentNumber
	 *
	 */
	public Set<OperationItem> findOperationItemByLpStudentNumber(Integer lpStudentNumber) throws DataAccessException;

	/**
	 * JPQL Query - findOperationItemByLpStudentNumber
	 *
	 */
	public Set<OperationItem> findOperationItemByLpStudentNumber(Integer lpStudentNumber, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findOperationItemByLpSetNumberContaining
	 *
	 */
	public Set<OperationItem> findOperationItemByLpSetNumberContaining(String lpSetNumber_1) throws DataAccessException;

	/**
	 * JPQL Query - findOperationItemByLpSetNumberContaining
	 *
	 */
	public Set<OperationItem> findOperationItemByLpSetNumberContaining(String lpSetNumber_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findOperationItemByLpCollegeContaining
	 *
	 */
	public Set<OperationItem> findOperationItemByLpCollegeContaining(String lpCollege_1) throws DataAccessException;

	/**
	 * JPQL Query - findOperationItemByLpCollegeContaining
	 *
	 */
	public Set<OperationItem> findOperationItemByLpCollegeContaining(String lpCollege_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findOperationItemByLpName
	 *
	 */
	public Set<OperationItem> findOperationItemByLpName(String lpName_1) throws DataAccessException;

	/**
	 * JPQL Query - findOperationItemByLpName
	 *
	 */
	public Set<OperationItem> findOperationItemByLpName(String lpName_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findOperationItemByLpCycleNumber
	 *
	 */
	public Set<OperationItem> findOperationItemByLpCycleNumber(String lpCycleNumber) throws DataAccessException;

	/**
	 * JPQL Query - findOperationItemByLpCycleNumber
	 *
	 */
	public Set<OperationItem> findOperationItemByLpCycleNumber(String lpCycleNumber, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findOperationItemByLpDepartmentHours
	 *
	 */
	public Set<OperationItem> findOperationItemByLpDepartmentHours(Integer lpDepartmentHours) throws DataAccessException;

	/**
	 * JPQL Query - findOperationItemByLpDepartmentHours
	 *
	 */
	public Set<OperationItem> findOperationItemByLpDepartmentHours(Integer lpDepartmentHours, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findOperationItemByLpPreparation
	 *
	 */
	public Set<OperationItem> findOperationItemByLpPreparation(String lpPreparation) throws DataAccessException;

	/**
	 * JPQL Query - findOperationItemByLpPreparation
	 *
	 */
	public Set<OperationItem> findOperationItemByLpPreparation(String lpPreparation, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findOperationItemByLpScore
	 *
	 */
	public Set<OperationItem> findOperationItemByLpScore(java.math.BigDecimal lpScore) throws DataAccessException;

	/**
	 * JPQL Query - findOperationItemByLpScore
	 *
	 */
	public Set<OperationItem> findOperationItemByLpScore(BigDecimal lpScore, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findOperationItemByLpCycleNumberContaining
	 *
	 */
	public Set<OperationItem> findOperationItemByLpCycleNumberContaining(String lpCycleNumber_1) throws DataAccessException;

	/**
	 * JPQL Query - findOperationItemByLpCycleNumberContaining
	 *
	 */
	public Set<OperationItem> findOperationItemByLpCycleNumberContaining(String lpCycleNumber_1, int startResult, int maxRows) throws DataAccessException;
	/**
	 * JPQL Query - findOperationItemByLpCycleNumberContaining
	 *
	 */


}