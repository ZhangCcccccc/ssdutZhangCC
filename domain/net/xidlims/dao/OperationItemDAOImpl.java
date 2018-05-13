package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.OperationItem;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage OperationItem entities.
 * 
 */
@Repository("OperationItemDAO")
@Transactional
public class OperationItemDAOImpl extends AbstractJpaDao<OperationItem>
		implements OperationItemDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { OperationItem.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new OperationItemDAOImpl
	 *
	 */
	public OperationItemDAOImpl() {
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
	 * JPQL Query - findOperationItemByLpStudentNumberGroupContaining
	 *
	 */
	@Transactional
	public Set<OperationItem> findOperationItemByLpStudentNumberGroupContaining(String lpStudentNumberGroup) throws DataAccessException {

		return findOperationItemByLpStudentNumberGroupContaining(lpStudentNumberGroup, -1, -1);
	}

	/**
	 * JPQL Query - findOperationItemByLpStudentNumberGroupContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<OperationItem> findOperationItemByLpStudentNumberGroupContaining(String lpStudentNumberGroup, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findOperationItemByLpStudentNumberGroupContaining", startResult, maxRows, lpStudentNumberGroup);
		return new LinkedHashSet<OperationItem>(query.getResultList());
	}

	/**
	 * JPQL Query - findOperationItemByLpCodeCustom
	 *
	 */
	@Transactional
	public Set<OperationItem> findOperationItemByLpCodeCustom(String lpCodeCustom) throws DataAccessException {

		return findOperationItemByLpCodeCustom(lpCodeCustom, -1, -1);
	}

	/**
	 * JPQL Query - findOperationItemByLpCodeCustom
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<OperationItem> findOperationItemByLpCodeCustom(String lpCodeCustom, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findOperationItemByLpCodeCustom", startResult, maxRows, lpCodeCustom);
		return new LinkedHashSet<OperationItem>(query.getResultList());
	}

	/**
	 * JPQL Query - findOperationItemByLpDepartmentHoursTotal
	 *
	 */
	@Transactional
	public Set<OperationItem> findOperationItemByLpDepartmentHoursTotal(Integer lpDepartmentHoursTotal) throws DataAccessException {

		return findOperationItemByLpDepartmentHoursTotal(lpDepartmentHoursTotal, -1, -1);
	}

	/**
	 * JPQL Query - findOperationItemByLpDepartmentHoursTotal
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<OperationItem> findOperationItemByLpDepartmentHoursTotal(Integer lpDepartmentHoursTotal, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findOperationItemByLpDepartmentHoursTotal", startResult, maxRows, lpDepartmentHoursTotal);
		return new LinkedHashSet<OperationItem>(query.getResultList());
	}

	/**
	 * JPQL Query - findOperationItemByLpCodeCustomContaining
	 *
	 */
	@Transactional
	public Set<OperationItem> findOperationItemByLpCodeCustomContaining(String lpCodeCustom) throws DataAccessException {

		return findOperationItemByLpCodeCustomContaining(lpCodeCustom, -1, -1);
	}

	/**
	 * JPQL Query - findOperationItemByLpCodeCustomContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<OperationItem> findOperationItemByLpCodeCustomContaining(String lpCodeCustom, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findOperationItemByLpCodeCustomContaining", startResult, maxRows, lpCodeCustom);
		return new LinkedHashSet<OperationItem>(query.getResultList());
	}

	/**
	 * JPQL Query - findOperationItemByLpIntroduction
	 *
	 */
	@Transactional
	public Set<OperationItem> findOperationItemByLpIntroduction(String lpIntroduction) throws DataAccessException {

		return findOperationItemByLpIntroduction(lpIntroduction, -1, -1);
	}

	/**
	 * JPQL Query - findOperationItemByLpIntroduction
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<OperationItem> findOperationItemByLpIntroduction(String lpIntroduction, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findOperationItemByLpIntroduction", startResult, maxRows, lpIntroduction);
		return new LinkedHashSet<OperationItem>(query.getResultList());
	}

	/**
	 * JPQL Query - findOperationItemByLpMajorFit
	 *
	 */
	@Transactional
	public Set<OperationItem> findOperationItemByLpMajorFit(String lpMajorFit) throws DataAccessException {

		return findOperationItemByLpMajorFit(lpMajorFit, -1, -1);
	}

	/**
	 * JPQL Query - findOperationItemByLpMajorFit
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<OperationItem> findOperationItemByLpMajorFit(String lpMajorFit, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findOperationItemByLpMajorFit", startResult, maxRows, lpMajorFit);
		return new LinkedHashSet<OperationItem>(query.getResultList());
	}

	/**
	 * JPQL Query - findOperationItemByLpConfigMaterial
	 *
	 */
	@Transactional
	public Set<OperationItem> findOperationItemByLpConfigMaterial(String lpConfigMaterial) throws DataAccessException {

		return findOperationItemByLpConfigMaterial(lpConfigMaterial, -1, -1);
	}

	/**
	 * JPQL Query - findOperationItemByLpConfigMaterial
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<OperationItem> findOperationItemByLpConfigMaterial(String lpConfigMaterial, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findOperationItemByLpConfigMaterial", startResult, maxRows, lpConfigMaterial);
		return new LinkedHashSet<OperationItem>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllOperationItems
	 *
	 */
	@Transactional
	public Set<OperationItem> findAllOperationItems() throws DataAccessException {

		return findAllOperationItems(-1, -1);
	}

	/**
	 * JPQL Query - findAllOperationItems
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<OperationItem> findAllOperationItems(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllOperationItems", startResult, maxRows);
		return new LinkedHashSet<OperationItem>(query.getResultList());
	}

	/**
	 * JPQL Query - findOperationItemByLpSetNumber
	 *
	 */
	@Transactional
	public Set<OperationItem> findOperationItemByLpSetNumber(String lpSetNumber) throws DataAccessException {

		return findOperationItemByLpSetNumber(lpSetNumber, -1, -1);
	}

	/**
	 * JPQL Query - findOperationItemByLpSetNumber
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<OperationItem> findOperationItemByLpSetNumber(String lpSetNumber, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findOperationItemByLpSetNumber", startResult, maxRows, lpSetNumber);
		return new LinkedHashSet<OperationItem>(query.getResultList());
	}

	/**
	 * JPQL Query - findOperationItemByLpGuideBookTitleContaining
	 *
	 */
	@Transactional
	public Set<OperationItem> findOperationItemByLpGuideBookTitleContaining(String lpGuideBookTitle) throws DataAccessException {

		return findOperationItemByLpGuideBookTitleContaining(lpGuideBookTitle, -1, -1);
	}

	/**
	 * JPQL Query - findOperationItemByLpGuideBookTitleContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<OperationItem> findOperationItemByLpGuideBookTitleContaining(String lpGuideBookTitle, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findOperationItemByLpGuideBookTitleContaining", startResult, maxRows, lpGuideBookTitle);
		return new LinkedHashSet<OperationItem>(query.getResultList());
	}

	/**
	 * JPQL Query - findOperationItemByLpGuideBookAuthor
	 *
	 */
	@Transactional
	public Set<OperationItem> findOperationItemByLpGuideBookAuthor(String lpGuideBookAuthor) throws DataAccessException {

		return findOperationItemByLpGuideBookAuthor(lpGuideBookAuthor, -1, -1);
	}

	/**
	 * JPQL Query - findOperationItemByLpGuideBookAuthor
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<OperationItem> findOperationItemByLpGuideBookAuthor(String lpGuideBookAuthor, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findOperationItemByLpGuideBookAuthor", startResult, maxRows, lpGuideBookAuthor);
		return new LinkedHashSet<OperationItem>(query.getResultList());
	}

	/**
	 * JPQL Query - findOperationItemByLpNameContaining
	 *
	 */
	@Transactional
	public Set<OperationItem> findOperationItemByLpNameContaining(String lpName) throws DataAccessException {

		return findOperationItemByLpNameContaining(lpName, -1, -1);
	}

	/**
	 * JPQL Query - findOperationItemByLpNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<OperationItem> findOperationItemByLpNameContaining(String lpName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findOperationItemByLpNameContaining", startResult, maxRows, lpName);
		return new LinkedHashSet<OperationItem>(query.getResultList());
	}

	/**
	 * JPQL Query - findOperationItemByLpGuideBookAuthorContaining
	 *
	 */
	@Transactional
	public Set<OperationItem> findOperationItemByLpGuideBookAuthorContaining(String lpGuideBookAuthor) throws DataAccessException {

		return findOperationItemByLpGuideBookAuthorContaining(lpGuideBookAuthor, -1, -1);
	}

	/**
	 * JPQL Query - findOperationItemByLpGuideBookAuthorContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<OperationItem> findOperationItemByLpGuideBookAuthorContaining(String lpGuideBookAuthor, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findOperationItemByLpGuideBookAuthorContaining", startResult, maxRows, lpGuideBookAuthor);
		return new LinkedHashSet<OperationItem>(query.getResultList());
	}

	/**
	 * JPQL Query - findOperationItemByLpCourseHoursTotal
	 *
	 */
	@Transactional
	public Set<OperationItem> findOperationItemByLpCourseHoursTotal(Integer lpCourseHoursTotal) throws DataAccessException {

		return findOperationItemByLpCourseHoursTotal(lpCourseHoursTotal, -1, -1);
	}

	/**
	 * JPQL Query - findOperationItemByLpCourseHoursTotal
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<OperationItem> findOperationItemByLpCourseHoursTotal(Integer lpCourseHoursTotal, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findOperationItemByLpCourseHoursTotal", startResult, maxRows, lpCourseHoursTotal);
		return new LinkedHashSet<OperationItem>(query.getResultList());
	}

	/**
	 * JPQL Query - findOperationItemByLpGuideBookTitle
	 *
	 */
	@Transactional
	public Set<OperationItem> findOperationItemByLpGuideBookTitle(String lpGuideBookTitle) throws DataAccessException {

		return findOperationItemByLpGuideBookTitle(lpGuideBookTitle, -1, -1);
	}

	/**
	 * JPQL Query - findOperationItemByLpGuideBookTitle
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<OperationItem> findOperationItemByLpGuideBookTitle(String lpGuideBookTitle, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findOperationItemByLpGuideBookTitle", startResult, maxRows, lpGuideBookTitle);
		return new LinkedHashSet<OperationItem>(query.getResultList());
	}

	/**
	 * JPQL Query - findOperationItemByLpAssessmentMethods
	 *
	 */
	@Transactional
	public Set<OperationItem> findOperationItemByLpAssessmentMethods(String lpAssessmentMethods) throws DataAccessException {

		return findOperationItemByLpAssessmentMethods(lpAssessmentMethods, -1, -1);
	}

	/**
	 * JPQL Query - findOperationItemByLpAssessmentMethods
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<OperationItem> findOperationItemByLpAssessmentMethods(String lpAssessmentMethods, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findOperationItemByLpAssessmentMethods", startResult, maxRows, lpAssessmentMethods);
		return new LinkedHashSet<OperationItem>(query.getResultList());
	}

	/**
	 * JPQL Query - findOperationItemByPrimaryKey
	 *
	 */
	@Transactional
	public OperationItem findOperationItemByPrimaryKey(Integer id) throws DataAccessException {

		return findOperationItemByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findOperationItemByPrimaryKey
	 *
	 */

	@Transactional
	public OperationItem findOperationItemByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findOperationItemByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.OperationItem) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findOperationItemByLpYearsPeopleNumberPlan
	 *
	 */
	@Transactional
	public Set<OperationItem> findOperationItemByLpYearsPeopleNumberPlan(Integer lpYearsPeopleNumberPlan) throws DataAccessException {

		return findOperationItemByLpYearsPeopleNumberPlan(lpYearsPeopleNumberPlan, -1, -1);
	}

	/**
	 * JPQL Query - findOperationItemByLpYearsPeopleNumberPlan
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<OperationItem> findOperationItemByLpYearsPeopleNumberPlan(Integer lpYearsPeopleNumberPlan, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findOperationItemByLpYearsPeopleNumberPlan", startResult, maxRows, lpYearsPeopleNumberPlan);
		return new LinkedHashSet<OperationItem>(query.getResultList());
	}

	/**
	 * JPQL Query - findOperationItemByLpPurposes
	 *
	 */
	@Transactional
	public Set<OperationItem> findOperationItemByLpPurposes(String lpPurposes) throws DataAccessException {

		return findOperationItemByLpPurposes(lpPurposes, -1, -1);
	}

	/**
	 * JPQL Query - findOperationItemByLpPurposes
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<OperationItem> findOperationItemByLpPurposes(String lpPurposes, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findOperationItemByLpPurposes", startResult, maxRows, lpPurposes);
		return new LinkedHashSet<OperationItem>(query.getResultList());
	}

	/**
	 * JPQL Query - findOperationItemByLpStudentNumberGroup
	 *
	 */
	@Transactional
	public Set<OperationItem> findOperationItemByLpStudentNumberGroup(String lpStudentNumberGroup) throws DataAccessException {

		return findOperationItemByLpStudentNumberGroup(lpStudentNumberGroup, -1, -1);
	}

	/**
	 * JPQL Query - findOperationItemByLpStudentNumberGroup
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<OperationItem> findOperationItemByLpStudentNumberGroup(String lpStudentNumberGroup, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findOperationItemByLpStudentNumberGroup", startResult, maxRows, lpStudentNumberGroup);
		return new LinkedHashSet<OperationItem>(query.getResultList());
	}

	/**
	 * JPQL Query - findOperationItemByLpMajorFitContaining
	 *
	 */
	@Transactional
	public Set<OperationItem> findOperationItemByLpMajorFitContaining(String lpMajorFit) throws DataAccessException {

		return findOperationItemByLpMajorFitContaining(lpMajorFit, -1, -1);
	}

	/**
	 * JPQL Query - findOperationItemByLpMajorFitContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<OperationItem> findOperationItemByLpMajorFitContaining(String lpMajorFit, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findOperationItemByLpMajorFitContaining", startResult, maxRows, lpMajorFit);
		return new LinkedHashSet<OperationItem>(query.getResultList());
	}

	/**
	 * JPQL Query - findOperationItemById
	 *
	 */
	@Transactional
	public OperationItem findOperationItemById(Integer id) throws DataAccessException {

		return findOperationItemById(id, -1, -1);
	}

	/**
	 * JPQL Query - findOperationItemById
	 *
	 */

	@Transactional
	public OperationItem findOperationItemById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findOperationItemById", startResult, maxRows, id);
			return (net.xidlims.domain.OperationItem) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findOperationItemByLpAssessmentMethodsContaining
	 *
	 */
	@Transactional
	public Set<OperationItem> findOperationItemByLpAssessmentMethodsContaining(String lpAssessmentMethods) throws DataAccessException {

		return findOperationItemByLpAssessmentMethodsContaining(lpAssessmentMethods, -1, -1);
	}

	/**
	 * JPQL Query - findOperationItemByLpAssessmentMethodsContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<OperationItem> findOperationItemByLpAssessmentMethodsContaining(String lpAssessmentMethods, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findOperationItemByLpAssessmentMethodsContaining", startResult, maxRows, lpAssessmentMethods);
		return new LinkedHashSet<OperationItem>(query.getResultList());
	}

	/**
	 * JPQL Query - findOperationItemByLpCollege
	 *
	 */
	@Transactional
	public Set<OperationItem> findOperationItemByLpCollege(String lpCollege) throws DataAccessException {

		return findOperationItemByLpCollege(lpCollege, -1, -1);
	}

	/**
	 * JPQL Query - findOperationItemByLpCollege
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<OperationItem> findOperationItemByLpCollege(String lpCollege, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findOperationItemByLpCollege", startResult, maxRows, lpCollege);
		return new LinkedHashSet<OperationItem>(query.getResultList());
	}

	/**
	 * JPQL Query - findOperationItemByLpStudentNumber
	 *
	 */
	@Transactional
	public Set<OperationItem> findOperationItemByLpStudentNumber(Integer lpStudentNumber) throws DataAccessException {

		return findOperationItemByLpStudentNumber(lpStudentNumber, -1, -1);
	}

	/**
	 * JPQL Query - findOperationItemByLpStudentNumber
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<OperationItem> findOperationItemByLpStudentNumber(Integer lpStudentNumber, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findOperationItemByLpStudentNumber", startResult, maxRows, lpStudentNumber);
		return new LinkedHashSet<OperationItem>(query.getResultList());
	}

	/**
	 * JPQL Query - findOperationItemByLpSetNumberContaining
	 *
	 */
	@Transactional
	public Set<OperationItem> findOperationItemByLpSetNumberContaining(String lpSetNumber) throws DataAccessException {

		return findOperationItemByLpSetNumberContaining(lpSetNumber, -1, -1);
	}

	/**
	 * JPQL Query - findOperationItemByLpSetNumberContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<OperationItem> findOperationItemByLpSetNumberContaining(String lpSetNumber, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findOperationItemByLpSetNumberContaining", startResult, maxRows, lpSetNumber);
		return new LinkedHashSet<OperationItem>(query.getResultList());
	}

	/**
	 * JPQL Query - findOperationItemByLpCollegeContaining
	 *
	 */
	@Transactional
	public Set<OperationItem> findOperationItemByLpCollegeContaining(String lpCollege) throws DataAccessException {

		return findOperationItemByLpCollegeContaining(lpCollege, -1, -1);
	}

	/**
	 * JPQL Query - findOperationItemByLpCollegeContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<OperationItem> findOperationItemByLpCollegeContaining(String lpCollege, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findOperationItemByLpCollegeContaining", startResult, maxRows, lpCollege);
		return new LinkedHashSet<OperationItem>(query.getResultList());
	}

	/**
	 * JPQL Query - findOperationItemByLpName
	 *
	 */
	@Transactional
	public Set<OperationItem> findOperationItemByLpName(String lpName) throws DataAccessException {

		return findOperationItemByLpName(lpName, -1, -1);
	}

	/**
	 * JPQL Query - findOperationItemByLpName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<OperationItem> findOperationItemByLpName(String lpName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findOperationItemByLpName", startResult, maxRows, lpName);
		return new LinkedHashSet<OperationItem>(query.getResultList());
	}

	/**
	 * JPQL Query - findOperationItemByLpCycleNumber
	 *
	 */
	@Transactional
	public Set<OperationItem> findOperationItemByLpCycleNumber(String lpCycleNumber) throws DataAccessException {

		return findOperationItemByLpCycleNumber(lpCycleNumber, -1, -1);
	}

	/**
	 * JPQL Query - findOperationItemByLpCycleNumber
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<OperationItem> findOperationItemByLpCycleNumber(String lpCycleNumber, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findOperationItemByLpCycleNumber", startResult, maxRows, lpCycleNumber);
		return new LinkedHashSet<OperationItem>(query.getResultList());
	}

	/**
	 * JPQL Query - findOperationItemByLpDepartmentHours
	 *
	 */
	@Transactional
	public Set<OperationItem> findOperationItemByLpDepartmentHours(Integer lpDepartmentHours) throws DataAccessException {

		return findOperationItemByLpDepartmentHours(lpDepartmentHours, -1, -1);
	}

	/**
	 * JPQL Query - findOperationItemByLpDepartmentHours
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<OperationItem> findOperationItemByLpDepartmentHours(Integer lpDepartmentHours, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findOperationItemByLpDepartmentHours", startResult, maxRows, lpDepartmentHours);
		return new LinkedHashSet<OperationItem>(query.getResultList());
	}

	/**
	 * JPQL Query - findOperationItemByLpPreparation
	 *
	 */
	@Transactional
	public Set<OperationItem> findOperationItemByLpPreparation(String lpPreparation) throws DataAccessException {

		return findOperationItemByLpPreparation(lpPreparation, -1, -1);
	}

	/**
	 * JPQL Query - findOperationItemByLpPreparation
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<OperationItem> findOperationItemByLpPreparation(String lpPreparation, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findOperationItemByLpPreparation", startResult, maxRows, lpPreparation);
		return new LinkedHashSet<OperationItem>(query.getResultList());
	}

	/**
	 * JPQL Query - findOperationItemByLpScore
	 *
	 */
	@Transactional
	public Set<OperationItem> findOperationItemByLpScore(java.math.BigDecimal lpScore) throws DataAccessException {

		return findOperationItemByLpScore(lpScore, -1, -1);
	}

	/**
	 * JPQL Query - findOperationItemByLpScore
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<OperationItem> findOperationItemByLpScore(java.math.BigDecimal lpScore, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findOperationItemByLpScore", startResult, maxRows, lpScore);
		return new LinkedHashSet<OperationItem>(query.getResultList());
	}

	/**
	 * JPQL Query - findOperationItemByLpCycleNumberContaining
	 *
	 */
	@Transactional
	public Set<OperationItem> findOperationItemByLpCycleNumberContaining(String lpCycleNumber) throws DataAccessException {

		return findOperationItemByLpCycleNumberContaining(lpCycleNumber, -1, -1);
	}

	/**
	 * JPQL Query - findOperationItemByLpCycleNumberContaining
	 *
	 */
	

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<OperationItem> findOperationItemByLpCycleNumberContaining(String lpCycleNumber, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findOperationItemByLpCycleNumberContaining", startResult, maxRows, lpCycleNumber);
		return new LinkedHashSet<OperationItem>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(OperationItem entity) {
		return true;
	}
}
