package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.TExperimentSkill;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage TExperimentSkill entities.
 * 
 */
@Repository("TExperimentSkillDAO")
@Transactional
public class TExperimentSkillDAOImpl extends AbstractJpaDao<TExperimentSkill>
		implements TExperimentSkillDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { TExperimentSkill.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlims
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new TExperimentSkillDAOImpl
	 *
	 */
	public TExperimentSkillDAOImpl() {
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
	 * JPQL Query - findTExperimentSkillByCreatedTime
	 *
	 */
	@Transactional
	public Set<TExperimentSkill> findTExperimentSkillByCreatedTime(java.util.Calendar createdTime) throws DataAccessException {

		return findTExperimentSkillByCreatedTime(createdTime, -1, -1);
	}

	/**
	 * JPQL Query - findTExperimentSkillByCreatedTime
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TExperimentSkill> findTExperimentSkillByCreatedTime(java.util.Calendar createdTime, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTExperimentSkillByCreatedTime", startResult, maxRows, createdTime);
		return new LinkedHashSet<TExperimentSkill>(query.getResultList());
	}

	/**
	 * JPQL Query - findTExperimentSkillById
	 *
	 */
	@Transactional
	public TExperimentSkill findTExperimentSkillById(Integer id) throws DataAccessException {

		return findTExperimentSkillById(id, -1, -1);
	}

	/**
	 * JPQL Query - findTExperimentSkillById
	 *
	 */

	@Transactional
	public TExperimentSkill findTExperimentSkillById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findTExperimentSkillById", startResult, maxRows, id);
			return (net.xidlims.domain.TExperimentSkill) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findTExperimentSkillByExperimentVersion
	 *
	 */
	@Transactional
	public Set<TExperimentSkill> findTExperimentSkillByExperimentVersion(String experimentVersion) throws DataAccessException {

		return findTExperimentSkillByExperimentVersion(experimentVersion, -1, -1);
	}

	/**
	 * JPQL Query - findTExperimentSkillByExperimentVersion
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TExperimentSkill> findTExperimentSkillByExperimentVersion(String experimentVersion, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTExperimentSkillByExperimentVersion", startResult, maxRows, experimentVersion);
		return new LinkedHashSet<TExperimentSkill>(query.getResultList());
	}

	/**
	 * JPQL Query - findTExperimentSkillByExperimentGoalContaining
	 *
	 */
	@Transactional
	public Set<TExperimentSkill> findTExperimentSkillByExperimentGoalContaining(String experimentGoal) throws DataAccessException {

		return findTExperimentSkillByExperimentGoalContaining(experimentGoal, -1, -1);
	}

	/**
	 * JPQL Query - findTExperimentSkillByExperimentGoalContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TExperimentSkill> findTExperimentSkillByExperimentGoalContaining(String experimentGoal, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTExperimentSkillByExperimentGoalContaining", startResult, maxRows, experimentGoal);
		return new LinkedHashSet<TExperimentSkill>(query.getResultList());
	}

	/**
	 * JPQL Query - findTExperimentSkillByCreatedByContaining
	 *
	 */
	@Transactional
	public Set<TExperimentSkill> findTExperimentSkillByCreatedByContaining(String createdBy) throws DataAccessException {

		return findTExperimentSkillByCreatedByContaining(createdBy, -1, -1);
	}

	/**
	 * JPQL Query - findTExperimentSkillByCreatedByContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TExperimentSkill> findTExperimentSkillByCreatedByContaining(String createdBy, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTExperimentSkillByCreatedByContaining", startResult, maxRows, createdBy);
		return new LinkedHashSet<TExperimentSkill>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllTExperimentSkills
	 *
	 */
	@Transactional
	public Set<TExperimentSkill> findAllTExperimentSkills() throws DataAccessException {

		return findAllTExperimentSkills(-1, -1);
	}

	/**
	 * JPQL Query - findAllTExperimentSkills
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TExperimentSkill> findAllTExperimentSkills(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllTExperimentSkills", startResult, maxRows);
		return new LinkedHashSet<TExperimentSkill>(query.getResultList());
	}

	/**
	 * JPQL Query - findTExperimentSkillByDuedate
	 *
	 */
	@Transactional
	public Set<TExperimentSkill> findTExperimentSkillByDuedate(java.util.Calendar duedate) throws DataAccessException {

		return findTExperimentSkillByDuedate(duedate, -1, -1);
	}

	/**
	 * JPQL Query - findTExperimentSkillByDuedate
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TExperimentSkill> findTExperimentSkillByDuedate(java.util.Calendar duedate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTExperimentSkillByDuedate", startResult, maxRows, duedate);
		return new LinkedHashSet<TExperimentSkill>(query.getResultList());
	}

	/**
	 * JPQL Query - findTExperimentSkillBySiteId
	 *
	 */
	@Transactional
	public Set<TExperimentSkill> findTExperimentSkillBySiteId(Integer siteId) throws DataAccessException {

		return findTExperimentSkillBySiteId(siteId, -1, -1);
	}

	/**
	 * JPQL Query - findTExperimentSkillBySiteId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TExperimentSkill> findTExperimentSkillBySiteId(Integer siteId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTExperimentSkillBySiteId", startResult, maxRows, siteId);
		return new LinkedHashSet<TExperimentSkill>(query.getResultList());
	}

	/**
	 * JPQL Query - findTExperimentSkillByExperimentNoContaining
	 *
	 */
	@Transactional
	public Set<TExperimentSkill> findTExperimentSkillByExperimentNoContaining(String experimentNo) throws DataAccessException {

		return findTExperimentSkillByExperimentNoContaining(experimentNo, -1, -1);
	}

	/**
	 * JPQL Query - findTExperimentSkillByExperimentNoContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TExperimentSkill> findTExperimentSkillByExperimentNoContaining(String experimentNo, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTExperimentSkillByExperimentNoContaining", startResult, maxRows, experimentNo);
		return new LinkedHashSet<TExperimentSkill>(query.getResultList());
	}

	/**
	 * JPQL Query - findTExperimentSkillByExperimentName
	 *
	 */
	@Transactional
	public Set<TExperimentSkill> findTExperimentSkillByExperimentName(String experimentName) throws DataAccessException {

		return findTExperimentSkillByExperimentName(experimentName, -1, -1);
	}

	/**
	 * JPQL Query - findTExperimentSkillByExperimentName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TExperimentSkill> findTExperimentSkillByExperimentName(String experimentName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTExperimentSkillByExperimentName", startResult, maxRows, experimentName);
		return new LinkedHashSet<TExperimentSkill>(query.getResultList());
	}

	/**
	 * JPQL Query - findTExperimentSkillByExperimentNo
	 *
	 */
	@Transactional
	public Set<TExperimentSkill> findTExperimentSkillByExperimentNo(String experimentNo) throws DataAccessException {

		return findTExperimentSkillByExperimentNo(experimentNo, -1, -1);
	}

	/**
	 * JPQL Query - findTExperimentSkillByExperimentNo
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TExperimentSkill> findTExperimentSkillByExperimentNo(String experimentNo, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTExperimentSkillByExperimentNo", startResult, maxRows, experimentNo);
		return new LinkedHashSet<TExperimentSkill>(query.getResultList());
	}

	/**
	 * JPQL Query - findTExperimentSkillByExperimentGoal
	 *
	 */
	@Transactional
	public Set<TExperimentSkill> findTExperimentSkillByExperimentGoal(String experimentGoal) throws DataAccessException {

		return findTExperimentSkillByExperimentGoal(experimentGoal, -1, -1);
	}

	/**
	 * JPQL Query - findTExperimentSkillByExperimentGoal
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TExperimentSkill> findTExperimentSkillByExperimentGoal(String experimentGoal, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTExperimentSkillByExperimentGoal", startResult, maxRows, experimentGoal);
		return new LinkedHashSet<TExperimentSkill>(query.getResultList());
	}

	/**
	 * JPQL Query - findTExperimentSkillByPrimaryKey
	 *
	 */
	@Transactional
	public TExperimentSkill findTExperimentSkillByPrimaryKey(Integer id) throws DataAccessException {

		return findTExperimentSkillByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findTExperimentSkillByPrimaryKey
	 *
	 */

	@Transactional
	public TExperimentSkill findTExperimentSkillByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findTExperimentSkillByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.TExperimentSkill) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findTExperimentSkillByExperimentNameContaining
	 *
	 */
	@Transactional
	public Set<TExperimentSkill> findTExperimentSkillByExperimentNameContaining(String experimentName) throws DataAccessException {

		return findTExperimentSkillByExperimentNameContaining(experimentName, -1, -1);
	}

	/**
	 * JPQL Query - findTExperimentSkillByExperimentNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TExperimentSkill> findTExperimentSkillByExperimentNameContaining(String experimentName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTExperimentSkillByExperimentNameContaining", startResult, maxRows, experimentName);
		return new LinkedHashSet<TExperimentSkill>(query.getResultList());
	}

	/**
	 * JPQL Query - findTExperimentSkillByCreatedBy
	 *
	 */
	@Transactional
	public Set<TExperimentSkill> findTExperimentSkillByCreatedBy(String createdBy) throws DataAccessException {

		return findTExperimentSkillByCreatedBy(createdBy, -1, -1);
	}

	/**
	 * JPQL Query - findTExperimentSkillByCreatedBy
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TExperimentSkill> findTExperimentSkillByCreatedBy(String createdBy, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTExperimentSkillByCreatedBy", startResult, maxRows, createdBy);
		return new LinkedHashSet<TExperimentSkill>(query.getResultList());
	}

	/**
	 * JPQL Query - findTExperimentSkillByChapterId
	 *
	 */
	@Transactional
	public Set<TExperimentSkill> findTExperimentSkillByChapterId(Integer chapterId) throws DataAccessException {

		return findTExperimentSkillByChapterId(chapterId, -1, -1);
	}

	/**
	 * JPQL Query - findTExperimentSkillByChapterId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TExperimentSkill> findTExperimentSkillByChapterId(Integer chapterId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTExperimentSkillByChapterId", startResult, maxRows, chapterId);
		return new LinkedHashSet<TExperimentSkill>(query.getResultList());
	}

	/**
	 * JPQL Query - findTExperimentSkillByExperimentVersionContaining
	 *
	 */
	@Transactional
	public Set<TExperimentSkill> findTExperimentSkillByExperimentVersionContaining(String experimentVersion) throws DataAccessException {

		return findTExperimentSkillByExperimentVersionContaining(experimentVersion, -1, -1);
	}

	/**
	 * JPQL Query - findTExperimentSkillByExperimentVersionContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TExperimentSkill> findTExperimentSkillByExperimentVersionContaining(String experimentVersion, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTExperimentSkillByExperimentVersionContaining", startResult, maxRows, experimentVersion);
		return new LinkedHashSet<TExperimentSkill>(query.getResultList());
	}

	/**
	 * JPQL Query - findTExperimentSkillByExperimentStatus
	 *
	 */
	@Transactional
	public Set<TExperimentSkill> findTExperimentSkillByExperimentStatus(Integer experimentStatus) throws DataAccessException {

		return findTExperimentSkillByExperimentStatus(experimentStatus, -1, -1);
	}

	/**
	 * JPQL Query - findTExperimentSkillByExperimentStatus
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TExperimentSkill> findTExperimentSkillByExperimentStatus(Integer experimentStatus, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTExperimentSkillByExperimentStatus", startResult, maxRows, experimentStatus);
		return new LinkedHashSet<TExperimentSkill>(query.getResultList());
	}

	/**
	 * JPQL Query - findTExperimentSkillByStartdate
	 *
	 */
	@Transactional
	public Set<TExperimentSkill> findTExperimentSkillByStartdate(java.util.Calendar startdate) throws DataAccessException {

		return findTExperimentSkillByStartdate(startdate, -1, -1);
	}

	/**
	 * JPQL Query - findTExperimentSkillByStartdate
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TExperimentSkill> findTExperimentSkillByStartdate(java.util.Calendar startdate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTExperimentSkillByStartdate", startResult, maxRows, startdate);
		return new LinkedHashSet<TExperimentSkill>(query.getResultList());
	}

	/**
	 * JPQL Query - findTExperimentSkillBySort
	 *
	 */
	@Transactional
	public Set<TExperimentSkill> findTExperimentSkillBySort(Integer sort) throws DataAccessException {

		return findTExperimentSkillBySort(sort, -1, -1);
	}

	/**
	 * JPQL Query - findTExperimentSkillBySort
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TExperimentSkill> findTExperimentSkillBySort(Integer sort, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTExperimentSkillBySort", startResult, maxRows, sort);
		return new LinkedHashSet<TExperimentSkill>(query.getResultList());
	}

	/**
	 * JPQL Query - findTExperimentSkillByExperimentDescribe
	 *
	 */
	@Transactional
	public Set<TExperimentSkill> findTExperimentSkillByExperimentDescribe(String experimentDescribe) throws DataAccessException {

		return findTExperimentSkillByExperimentDescribe(experimentDescribe, -1, -1);
	}

	/**
	 * JPQL Query - findTExperimentSkillByExperimentDescribe
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TExperimentSkill> findTExperimentSkillByExperimentDescribe(String experimentDescribe, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTExperimentSkillByExperimentDescribe", startResult, maxRows, experimentDescribe);
		return new LinkedHashSet<TExperimentSkill>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(TExperimentSkill entity) {
		return true;
	}
}
