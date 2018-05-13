package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.WkCourse;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage WkCourse entities.
 * 
 */
@Repository("WkCourseDAO")
@Transactional
public class WkCourseDAOImpl extends AbstractJpaDao<WkCourse> implements
		WkCourseDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { WkCourse.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new WkCourseDAOImpl
	 *
	 */
	public WkCourseDAOImpl() {
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
	 * JPQL Query - findWkCourseByEvaluationContaining
	 *
	 */
	@Transactional
	public Set<WkCourse> findWkCourseByEvaluationContaining(String evaluation) throws DataAccessException {

		return findWkCourseByEvaluationContaining(evaluation, -1, -1);
	}

	/**
	 * JPQL Query - findWkCourseByEvaluationContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<WkCourse> findWkCourseByEvaluationContaining(String evaluation, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findWkCourseByEvaluationContaining", startResult, maxRows, evaluation);
		return new LinkedHashSet<WkCourse>(query.getResultList());
	}

	/**
	 * JPQL Query - findWkCourseByOutcomesContaining
	 *
	 */
	@Transactional
	public Set<WkCourse> findWkCourseByOutcomesContaining(String outcomes) throws DataAccessException {

		return findWkCourseByOutcomesContaining(outcomes, -1, -1);
	}

	/**
	 * JPQL Query - findWkCourseByOutcomesContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<WkCourse> findWkCourseByOutcomesContaining(String outcomes, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findWkCourseByOutcomesContaining", startResult, maxRows, outcomes);
		return new LinkedHashSet<WkCourse>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllWkCourses
	 *
	 */
	@Transactional
	public Set<WkCourse> findAllWkCourses() throws DataAccessException {

		return findAllWkCourses(-1, -1);
	}

	/**
	 * JPQL Query - findAllWkCourses
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<WkCourse> findAllWkCourses(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllWkCourses", startResult, maxRows);
		return new LinkedHashSet<WkCourse>(query.getResultList());
	}

	/**
	 * JPQL Query - findWkCourseByNameContaining
	 *
	 */
	@Transactional
	public Set<WkCourse> findWkCourseByNameContaining(String name) throws DataAccessException {

		return findWkCourseByNameContaining(name, -1, -1);
	}

	/**
	 * JPQL Query - findWkCourseByNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<WkCourse> findWkCourseByNameContaining(String name, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findWkCourseByNameContaining", startResult, maxRows, name);
		return new LinkedHashSet<WkCourse>(query.getResultList());
	}

	/**
	 * JPQL Query - findWkCourseByName
	 *
	 */
	@Transactional
	public Set<WkCourse> findWkCourseByName(String name) throws DataAccessException {

		return findWkCourseByName(name, -1, -1);
	}

	/**
	 * JPQL Query - findWkCourseByName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<WkCourse> findWkCourseByName(String name, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findWkCourseByName", startResult, maxRows, name);
		return new LinkedHashSet<WkCourse>(query.getResultList());
	}

	/**
	 * JPQL Query - findWkCourseByOutcomes
	 *
	 */
	@Transactional
	public Set<WkCourse> findWkCourseByOutcomes(String outcomes) throws DataAccessException {

		return findWkCourseByOutcomes(outcomes, -1, -1);
	}

	/**
	 * JPQL Query - findWkCourseByOutcomes
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<WkCourse> findWkCourseByOutcomes(String outcomes, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findWkCourseByOutcomes", startResult, maxRows, outcomes);
		return new LinkedHashSet<WkCourse>(query.getResultList());
	}

	/**
	 * JPQL Query - findWkCourseByLogoUrlContaining
	 *
	 */
	@Transactional
	public Set<WkCourse> findWkCourseByLogoUrlContaining(String logoUrl) throws DataAccessException {

		return findWkCourseByLogoUrlContaining(logoUrl, -1, -1);
	}

	/**
	 * JPQL Query - findWkCourseByLogoUrlContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<WkCourse> findWkCourseByLogoUrlContaining(String logoUrl, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findWkCourseByLogoUrlContaining", startResult, maxRows, logoUrl);
		return new LinkedHashSet<WkCourse>(query.getResultList());
	}

	/**
	 * JPQL Query - findWkCourseById
	 *
	 */
	@Transactional
	public WkCourse findWkCourseById(Integer id) throws DataAccessException {

		return findWkCourseById(id, -1, -1);
	}

	/**
	 * JPQL Query - findWkCourseById
	 *
	 */

	@Transactional
	public WkCourse findWkCourseById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findWkCourseById", startResult, maxRows, id);
			return (net.xidlims.domain.WkCourse) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findWkCourseBySyllabus
	 *
	 */
	@Transactional
	public Set<WkCourse> findWkCourseBySyllabus(String syllabus) throws DataAccessException {

		return findWkCourseBySyllabus(syllabus, -1, -1);
	}

	/**
	 * JPQL Query - findWkCourseBySyllabus
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<WkCourse> findWkCourseBySyllabus(String syllabus, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findWkCourseBySyllabus", startResult, maxRows, syllabus);
		return new LinkedHashSet<WkCourse>(query.getResultList());
	}

	/**
	 * JPQL Query - findWkCourseByTagList
	 *
	 */
	@Transactional
	public Set<WkCourse> findWkCourseByTagList(String tagList) throws DataAccessException {

		return findWkCourseByTagList(tagList, -1, -1);
	}

	/**
	 * JPQL Query - findWkCourseByTagList
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<WkCourse> findWkCourseByTagList(String tagList, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findWkCourseByTagList", startResult, maxRows, tagList);
		return new LinkedHashSet<WkCourse>(query.getResultList());
	}

	/**
	 * JPQL Query - findWkCourseByIsOpen
	 *
	 */
	@Transactional
	public Set<WkCourse> findWkCourseByIsOpen(Integer isOpen) throws DataAccessException {

		return findWkCourseByIsOpen(isOpen, -1, -1);
	}

	/**
	 * JPQL Query - findWkCourseByIsOpen
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<WkCourse> findWkCourseByIsOpen(Integer isOpen, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findWkCourseByIsOpen", startResult, maxRows, isOpen);
		return new LinkedHashSet<WkCourse>(query.getResultList());
	}

	/**
	 * JPQL Query - findWkCourseByCodeContaining
	 *
	 */
	@Transactional
	public Set<WkCourse> findWkCourseByCodeContaining(String code) throws DataAccessException {

		return findWkCourseByCodeContaining(code, -1, -1);
	}

	/**
	 * JPQL Query - findWkCourseByCodeContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<WkCourse> findWkCourseByCodeContaining(String code, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findWkCourseByCodeContaining", startResult, maxRows, code);
		return new LinkedHashSet<WkCourse>(query.getResultList());
	}

	/**
	 * JPQL Query - findWkCourseByManager
	 *
	 */
	@Transactional
	public Set<WkCourse> findWkCourseByManager(String manager) throws DataAccessException {

		return findWkCourseByManager(manager, -1, -1);
	}

	/**
	 * JPQL Query - findWkCourseByManager
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<WkCourse> findWkCourseByManager(String manager, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findWkCourseByManager", startResult, maxRows, manager);
		return new LinkedHashSet<WkCourse>(query.getResultList());
	}

	/**
	 * JPQL Query - findWkCourseByTagListContaining
	 *
	 */
	@Transactional
	public Set<WkCourse> findWkCourseByTagListContaining(String tagList) throws DataAccessException {

		return findWkCourseByTagListContaining(tagList, -1, -1);
	}

	/**
	 * JPQL Query - findWkCourseByTagListContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<WkCourse> findWkCourseByTagListContaining(String tagList, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findWkCourseByTagListContaining", startResult, maxRows, tagList);
		return new LinkedHashSet<WkCourse>(query.getResultList());
	}

	/**
	 * JPQL Query - findWkCourseByIntroductionContaining
	 *
	 */
	@Transactional
	public Set<WkCourse> findWkCourseByIntroductionContaining(String introduction) throws DataAccessException {

		return findWkCourseByIntroductionContaining(introduction, -1, -1);
	}

	/**
	 * JPQL Query - findWkCourseByIntroductionContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<WkCourse> findWkCourseByIntroductionContaining(String introduction, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findWkCourseByIntroductionContaining", startResult, maxRows, introduction);
		return new LinkedHashSet<WkCourse>(query.getResultList());
	}

	/**
	 * JPQL Query - findWkCourseByManagerContaining
	 *
	 */
	@Transactional
	public Set<WkCourse> findWkCourseByManagerContaining(String manager) throws DataAccessException {

		return findWkCourseByManagerContaining(manager, -1, -1);
	}

	/**
	 * JPQL Query - findWkCourseByManagerContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<WkCourse> findWkCourseByManagerContaining(String manager, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findWkCourseByManagerContaining", startResult, maxRows, manager);
		return new LinkedHashSet<WkCourse>(query.getResultList());
	}

	/**
	 * JPQL Query - findWkCourseByPrimaryKey
	 *
	 */
	@Transactional
	public WkCourse findWkCourseByPrimaryKey(Integer id) throws DataAccessException {

		return findWkCourseByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findWkCourseByPrimaryKey
	 *
	 */

	@Transactional
	public WkCourse findWkCourseByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findWkCourseByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.WkCourse) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findWkCourseByEvaluation
	 *
	 */
	@Transactional
	public Set<WkCourse> findWkCourseByEvaluation(String evaluation) throws DataAccessException {

		return findWkCourseByEvaluation(evaluation, -1, -1);
	}

	/**
	 * JPQL Query - findWkCourseByEvaluation
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<WkCourse> findWkCourseByEvaluation(String evaluation, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findWkCourseByEvaluation", startResult, maxRows, evaluation);
		return new LinkedHashSet<WkCourse>(query.getResultList());
	}

	/**
	 * JPQL Query - findWkCourseByCode
	 *
	 */
	@Transactional
	public Set<WkCourse> findWkCourseByCode(String code) throws DataAccessException {

		return findWkCourseByCode(code, -1, -1);
	}

	/**
	 * JPQL Query - findWkCourseByCode
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<WkCourse> findWkCourseByCode(String code, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findWkCourseByCode", startResult, maxRows, code);
		return new LinkedHashSet<WkCourse>(query.getResultList());
	}

	/**
	 * JPQL Query - findWkCourseByLogoUrl
	 *
	 */
	@Transactional
	public Set<WkCourse> findWkCourseByLogoUrl(String logoUrl) throws DataAccessException {

		return findWkCourseByLogoUrl(logoUrl, -1, -1);
	}

	/**
	 * JPQL Query - findWkCourseByLogoUrl
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<WkCourse> findWkCourseByLogoUrl(String logoUrl, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findWkCourseByLogoUrl", startResult, maxRows, logoUrl);
		return new LinkedHashSet<WkCourse>(query.getResultList());
	}

	/**
	 * JPQL Query - findWkCourseByVideoId
	 *
	 */
	@Transactional
	public Set<WkCourse> findWkCourseByVideoId(Integer videoId) throws DataAccessException {

		return findWkCourseByVideoId(videoId, -1, -1);
	}

	/**
	 * JPQL Query - findWkCourseByVideoId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<WkCourse> findWkCourseByVideoId(Integer videoId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findWkCourseByVideoId", startResult, maxRows, videoId);
		return new LinkedHashSet<WkCourse>(query.getResultList());
	}

	/**
	 * JPQL Query - findWkCourseByFilesList
	 *
	 */
	@Transactional
	public Set<WkCourse> findWkCourseByFilesList(String filesList) throws DataAccessException {

		return findWkCourseByFilesList(filesList, -1, -1);
	}

	/**
	 * JPQL Query - findWkCourseByFilesList
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<WkCourse> findWkCourseByFilesList(String filesList, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findWkCourseByFilesList", startResult, maxRows, filesList);
		return new LinkedHashSet<WkCourse>(query.getResultList());
	}

	/**
	 * JPQL Query - findWkCourseByIntroduction
	 *
	 */
	@Transactional
	public Set<WkCourse> findWkCourseByIntroduction(String introduction) throws DataAccessException {

		return findWkCourseByIntroduction(introduction, -1, -1);
	}

	/**
	 * JPQL Query - findWkCourseByIntroduction
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<WkCourse> findWkCourseByIntroduction(String introduction, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findWkCourseByIntroduction", startResult, maxRows, introduction);
		return new LinkedHashSet<WkCourse>(query.getResultList());
	}

	/**
	 * JPQL Query - findWkCourseByCerateTime
	 *
	 */
	@Transactional
	public Set<WkCourse> findWkCourseByCerateTime(java.util.Calendar cerateTime) throws DataAccessException {

		return findWkCourseByCerateTime(cerateTime, -1, -1);
	}

	/**
	 * JPQL Query - findWkCourseByCerateTime
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<WkCourse> findWkCourseByCerateTime(java.util.Calendar cerateTime, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findWkCourseByCerateTime", startResult, maxRows, cerateTime);
		return new LinkedHashSet<WkCourse>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(WkCourse entity) {
		return true;
	}
}
