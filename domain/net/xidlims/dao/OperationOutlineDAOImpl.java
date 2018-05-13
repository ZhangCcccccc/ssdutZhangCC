package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.OperationOutline;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage OperationOutline entities.
 * 
 */
@Repository("OperationOutlineDAO")
@Transactional
public class OperationOutlineDAOImpl extends AbstractJpaDao<OperationOutline>
		implements OperationOutlineDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { OperationOutline.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new OperationOutlineDAOImpl
	 *
	 */
	public OperationOutlineDAOImpl() {
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
	 * JPQL Query - findOperationOutlineByPrimaryKey
	 *
	 */
	@Transactional
	public OperationOutline findOperationOutlineByPrimaryKey(Integer id) throws DataAccessException {

		return findOperationOutlineByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findOperationOutlineByPrimaryKey
	 *
	 */

	@Transactional
	public OperationOutline findOperationOutlineByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findOperationOutlineByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.OperationOutline) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findOperationOutlineByUseMaterialsContaining
	 *
	 */
	@Transactional
	public Set<OperationOutline> findOperationOutlineByUseMaterialsContaining(String useMaterials) throws DataAccessException {

		return findOperationOutlineByUseMaterialsContaining(useMaterials, -1, -1);
	}

	/**
	 * JPQL Query - findOperationOutlineByUseMaterialsContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<OperationOutline> findOperationOutlineByUseMaterialsContaining(String useMaterials, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findOperationOutlineByUseMaterialsContaining", startResult, maxRows, useMaterials);
		return new LinkedHashSet<OperationOutline>(query.getResultList());
	}

	/**
	 * JPQL Query - findOperationOutlineByLabOutlineName
	 *
	 */
	@Transactional
	public Set<OperationOutline> findOperationOutlineByLabOutlineName(String labOutlineName) throws DataAccessException {

		return findOperationOutlineByLabOutlineName(labOutlineName, -1, -1);
	}

	/**
	 * JPQL Query - findOperationOutlineByLabOutlineName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<OperationOutline> findOperationOutlineByLabOutlineName(String labOutlineName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findOperationOutlineByLabOutlineName", startResult, maxRows, labOutlineName);
		return new LinkedHashSet<OperationOutline>(query.getResultList());
	}

	/**
	 * JPQL Query - findOperationOutlineByAssResultsPerEvaluation
	 *
	 */
	@Transactional
	public Set<OperationOutline> findOperationOutlineByAssResultsPerEvaluation(String assResultsPerEvaluation) throws DataAccessException {

		return findOperationOutlineByAssResultsPerEvaluation(assResultsPerEvaluation, -1, -1);
	}

	/**
	 * JPQL Query - findOperationOutlineByAssResultsPerEvaluation
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<OperationOutline> findOperationOutlineByAssResultsPerEvaluation(String assResultsPerEvaluation, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findOperationOutlineByAssResultsPerEvaluation", startResult, maxRows, assResultsPerEvaluation);
		return new LinkedHashSet<OperationOutline>(query.getResultList());
	}

	/**
	 * JPQL Query - findOperationOutlineByOutlineCourseTeachingTarget
	 *
	 */
	@Transactional
	public Set<OperationOutline> findOperationOutlineByOutlineCourseTeachingTarget(String outlineCourseTeachingTarget) throws DataAccessException {

		return findOperationOutlineByOutlineCourseTeachingTarget(outlineCourseTeachingTarget, -1, -1);
	}

	/**
	 * JPQL Query - findOperationOutlineByOutlineCourseTeachingTarget
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<OperationOutline> findOperationOutlineByOutlineCourseTeachingTarget(String outlineCourseTeachingTarget, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findOperationOutlineByOutlineCourseTeachingTarget", startResult, maxRows, outlineCourseTeachingTarget);
		return new LinkedHashSet<OperationOutline>(query.getResultList());
	}

	/**
	 * JPQL Query - findOperationOutlineByCoursesAdvice
	 *
	 */
	@Transactional
	public Set<OperationOutline> findOperationOutlineByCoursesAdvice(String coursesAdvice) throws DataAccessException {

		return findOperationOutlineByCoursesAdvice(coursesAdvice, -1, -1);
	}

	/**
	 * JPQL Query - findOperationOutlineByCoursesAdvice
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<OperationOutline> findOperationOutlineByCoursesAdvice(String coursesAdvice, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findOperationOutlineByCoursesAdvice", startResult, maxRows, coursesAdvice);
		return new LinkedHashSet<OperationOutline>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllOperationOutlines
	 *
	 */
	@Transactional
	public Set<OperationOutline> findAllOperationOutlines() throws DataAccessException {

		return findAllOperationOutlines(-1, -1);
	}

	/**
	 * JPQL Query - findAllOperationOutlines
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<OperationOutline> findAllOperationOutlines(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllOperationOutlines", startResult, maxRows);
		return new LinkedHashSet<OperationOutline>(query.getResultList());
	}

	/**
	 * JPQL Query - findOperationOutlineByEnNameContaining
	 *
	 */
	@Transactional
	public Set<OperationOutline> findOperationOutlineByEnNameContaining(String enName) throws DataAccessException {

		return findOperationOutlineByEnNameContaining(enName, -1, -1);
	}

	/**
	 * JPQL Query - findOperationOutlineByEnNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<OperationOutline> findOperationOutlineByEnNameContaining(String enName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findOperationOutlineByEnNameContaining", startResult, maxRows, enName);
		return new LinkedHashSet<OperationOutline>(query.getResultList());
	}

	/**
	 * JPQL Query - findOperationOutlineByLabOutlineNameContaining
	 *
	 */
	@Transactional
	public Set<OperationOutline> findOperationOutlineByLabOutlineNameContaining(String labOutlineName) throws DataAccessException {

		return findOperationOutlineByLabOutlineNameContaining(labOutlineName, -1, -1);
	}

	/**
	 * JPQL Query - findOperationOutlineByLabOutlineNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<OperationOutline> findOperationOutlineByLabOutlineNameContaining(String labOutlineName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findOperationOutlineByLabOutlineNameContaining", startResult, maxRows, labOutlineName);
		return new LinkedHashSet<OperationOutline>(query.getResultList());
	}

	/**
	 * JPQL Query - findOperationOutlineByProfessional
	 *
	 */
	@Transactional
	public Set<OperationOutline> findOperationOutlineByProfessional(String professional) throws DataAccessException {

		return findOperationOutlineByProfessional(professional, -1, -1);
	}

	/**
	 * JPQL Query - findOperationOutlineByProfessional
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<OperationOutline> findOperationOutlineByProfessional(String professional, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findOperationOutlineByProfessional", startResult, maxRows, professional);
		return new LinkedHashSet<OperationOutline>(query.getResultList());
	}

	/**
	 * JPQL Query - findOperationOutlineByOutlineCourseTeachingTargetOver
	 *
	 */
	@Transactional
	public Set<OperationOutline> findOperationOutlineByOutlineCourseTeachingTargetOver(String outlineCourseTeachingTargetOver) throws DataAccessException {

		return findOperationOutlineByOutlineCourseTeachingTargetOver(outlineCourseTeachingTargetOver, -1, -1);
	}

	/**
	 * JPQL Query - findOperationOutlineByOutlineCourseTeachingTargetOver
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<OperationOutline> findOperationOutlineByOutlineCourseTeachingTargetOver(String outlineCourseTeachingTargetOver, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findOperationOutlineByOutlineCourseTeachingTargetOver", startResult, maxRows, outlineCourseTeachingTargetOver);
		return new LinkedHashSet<OperationOutline>(query.getResultList());
	}

	/**
	 * JPQL Query - findOperationOutlineByEnName
	 *
	 */
	@Transactional
	public Set<OperationOutline> findOperationOutlineByEnName(String enName) throws DataAccessException {

		return findOperationOutlineByEnName(enName, -1, -1);
	}

	/**
	 * JPQL Query - findOperationOutlineByEnName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<OperationOutline> findOperationOutlineByEnName(String enName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findOperationOutlineByEnName", startResult, maxRows, enName);
		return new LinkedHashSet<OperationOutline>(query.getResultList());
	}

	/**
	 * JPQL Query - findOperationOutlineByCourseDescription
	 *
	 */
	@Transactional
	public Set<OperationOutline> findOperationOutlineByCourseDescription(String courseDescription) throws DataAccessException {

		return findOperationOutlineByCourseDescription(courseDescription, -1, -1);
	}

	/**
	 * JPQL Query - findOperationOutlineByCourseDescription
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<OperationOutline> findOperationOutlineByCourseDescription(String courseDescription, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findOperationOutlineByCourseDescription", startResult, maxRows, courseDescription);
		return new LinkedHashSet<OperationOutline>(query.getResultList());
	}

	/**
	 * JPQL Query - findOperationOutlineById
	 *
	 */
	@Transactional
	public OperationOutline findOperationOutlineById(Integer id) throws DataAccessException {

		return findOperationOutlineById(id, -1, -1);
	}

	/**
	 * JPQL Query - findOperationOutlineById
	 *
	 */

	@Transactional
	public OperationOutline findOperationOutlineById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findOperationOutlineById", startResult, maxRows, id);
			return (net.xidlims.domain.OperationOutline) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findOperationOutlineByProfessionalContaining
	 *
	 */
	@Transactional
	public Set<OperationOutline> findOperationOutlineByProfessionalContaining(String professional) throws DataAccessException {

		return findOperationOutlineByProfessionalContaining(professional, -1, -1);
	}

	/**
	 * JPQL Query - findOperationOutlineByProfessionalContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<OperationOutline> findOperationOutlineByProfessionalContaining(String professional, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findOperationOutlineByProfessionalContaining", startResult, maxRows, professional);
		return new LinkedHashSet<OperationOutline>(query.getResultList());
	}

	/**
	 * JPQL Query - findOperationOutlineByUseMaterials
	 *
	 */
	@Transactional
	public Set<OperationOutline> findOperationOutlineByUseMaterials(String useMaterials) throws DataAccessException {

		return findOperationOutlineByUseMaterials(useMaterials, -1, -1);
	}

	/**
	 * JPQL Query - findOperationOutlineByUseMaterials
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<OperationOutline> findOperationOutlineByUseMaterials(String useMaterials, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findOperationOutlineByUseMaterials", startResult, maxRows, useMaterials);
		return new LinkedHashSet<OperationOutline>(query.getResultList());
	}

	/**
	 * JPQL Query - findOperationOutlineByBasicContentCourse
	 *
	 */
	@Transactional
	public Set<OperationOutline> findOperationOutlineByBasicContentCourse(String basicContentCourse) throws DataAccessException {

		return findOperationOutlineByBasicContentCourse(basicContentCourse, -1, -1);
	}

	/**
	 * JPQL Query - findOperationOutlineByBasicContentCourse
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<OperationOutline> findOperationOutlineByBasicContentCourse(String basicContentCourse, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findOperationOutlineByBasicContentCourse", startResult, maxRows, basicContentCourse);
		return new LinkedHashSet<OperationOutline>(query.getResultList());
	}

	/**
	 * JPQL Query - findOperationOutlineByBasicRequirementsCourse
	 *
	 */
	@Transactional
	public Set<OperationOutline> findOperationOutlineByBasicRequirementsCourse(String basicRequirementsCourse) throws DataAccessException {

		return findOperationOutlineByBasicRequirementsCourse(basicRequirementsCourse, -1, -1);
	}

	/**
	 * JPQL Query - findOperationOutlineByBasicRequirementsCourse
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<OperationOutline> findOperationOutlineByBasicRequirementsCourse(String basicRequirementsCourse, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findOperationOutlineByBasicRequirementsCourse", startResult, maxRows, basicRequirementsCourse);
		return new LinkedHashSet<OperationOutline>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(OperationOutline entity) {
		return true;
	}
}
