package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.TExperimentSkillUser;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage TExperimentSkillUser entities.
 * 
 */
@Repository("TExperimentSkillUserDAO")
@Transactional
public class TExperimentSkillUserDAOImpl extends AbstractJpaDao<TExperimentSkillUser>
		implements TExperimentSkillUserDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { TExperimentSkillUser.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new TExperimentSkillUserDAOImpl
	 *
	 */
	public TExperimentSkillUserDAOImpl() {
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
	 * JPQL Query - findAllTExperimentSkillUsers
	 *
	 */
	@Transactional
	public Set<TExperimentSkillUser> findAllTExperimentSkillUsers() throws DataAccessException {

		return findAllTExperimentSkillUsers(-1, -1);
	}

	/**
	 * JPQL Query - findAllTExperimentSkillUsers
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TExperimentSkillUser> findAllTExperimentSkillUsers(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllTExperimentSkillUsers", startResult, maxRows);
		return new LinkedHashSet<TExperimentSkillUser>(query.getResultList());
	}

	/**
	 * JPQL Query - findTExperimentSkillUserByUsername
	 *
	 */
	@Transactional
	public Set<TExperimentSkillUser> findTExperimentSkillUserByUsername(String username) throws DataAccessException {

		return findTExperimentSkillUserByUsername(username, -1, -1);
	}

	/**
	 * JPQL Query - findTExperimentSkillUserByUsername
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TExperimentSkillUser> findTExperimentSkillUserByUsername(String username, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTExperimentSkillUserByUsername", startResult, maxRows, username);
		return new LinkedHashSet<TExperimentSkillUser>(query.getResultList());
	}

	/**
	 * JPQL Query - findTExperimentSkillUserByCreateTime
	 *
	 */
	@Transactional
	public Set<TExperimentSkillUser> findTExperimentSkillUserByCreateTime(java.util.Calendar createTime) throws DataAccessException {

		return findTExperimentSkillUserByCreateTime(createTime, -1, -1);
	}

	/**
	 * JPQL Query - findTExperimentSkillUserByCreateTime
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TExperimentSkillUser> findTExperimentSkillUserByCreateTime(java.util.Calendar createTime, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTExperimentSkillUserByCreateTime", startResult, maxRows, createTime);
		return new LinkedHashSet<TExperimentSkillUser>(query.getResultList());
	}

	/**
	 * JPQL Query - findTExperimentSkillUserByGradeTeacher
	 *
	 */
	@Transactional
	public Set<TExperimentSkillUser> findTExperimentSkillUserByGradeTeacher(String gradeTeacher) throws DataAccessException {

		return findTExperimentSkillUserByGradeTeacher(gradeTeacher, -1, -1);
	}

	/**
	 * JPQL Query - findTExperimentSkillUserByGradeTeacher
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TExperimentSkillUser> findTExperimentSkillUserByGradeTeacher(String gradeTeacher, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTExperimentSkillUserByGradeTeacher", startResult, maxRows, gradeTeacher);
		return new LinkedHashSet<TExperimentSkillUser>(query.getResultList());
	}

	/**
	 * JPQL Query - findTExperimentSkillUserByPrimaryKey
	 *
	 */
	@Transactional
	public TExperimentSkillUser findTExperimentSkillUserByPrimaryKey(Integer id) throws DataAccessException {

		return findTExperimentSkillUserByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findTExperimentSkillUserByPrimaryKey
	 *
	 */

	@Transactional
	public TExperimentSkillUser findTExperimentSkillUserByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findTExperimentSkillUserByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.TExperimentSkillUser) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findTExperimentSkillUserByGradeTeacherContaining
	 *
	 */
	@Transactional
	public Set<TExperimentSkillUser> findTExperimentSkillUserByGradeTeacherContaining(String gradeTeacher) throws DataAccessException {

		return findTExperimentSkillUserByGradeTeacherContaining(gradeTeacher, -1, -1);
	}

	/**
	 * JPQL Query - findTExperimentSkillUserByGradeTeacherContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TExperimentSkillUser> findTExperimentSkillUserByGradeTeacherContaining(String gradeTeacher, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTExperimentSkillUserByGradeTeacherContaining", startResult, maxRows, gradeTeacher);
		return new LinkedHashSet<TExperimentSkillUser>(query.getResultList());
	}

	/**
	 * JPQL Query - findTExperimentSkillUserByUsernameContaining
	 *
	 */
	@Transactional
	public Set<TExperimentSkillUser> findTExperimentSkillUserByUsernameContaining(String username) throws DataAccessException {

		return findTExperimentSkillUserByUsernameContaining(username, -1, -1);
	}

	/**
	 * JPQL Query - findTExperimentSkillUserByUsernameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TExperimentSkillUser> findTExperimentSkillUserByUsernameContaining(String username, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTExperimentSkillUserByUsernameContaining", startResult, maxRows, username);
		return new LinkedHashSet<TExperimentSkillUser>(query.getResultList());
	}

	/**
	 * JPQL Query - findTExperimentSkillUserBySkillId
	 *
	 */
	@Transactional
	public Set<TExperimentSkillUser> findTExperimentSkillUserBySkillId(Integer skillId) throws DataAccessException {

		return findTExperimentSkillUserBySkillId(skillId, -1, -1);
	}

	/**
	 * JPQL Query - findTExperimentSkillUserBySkillId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TExperimentSkillUser> findTExperimentSkillUserBySkillId(Integer skillId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTExperimentSkillUserBySkillId", startResult, maxRows, skillId);
		return new LinkedHashSet<TExperimentSkillUser>(query.getResultList());
	}

	/**
	 * JPQL Query - findTExperimentSkillUserByRealGrade
	 *
	 */
	@Transactional
	public Set<TExperimentSkillUser> findTExperimentSkillUserByRealGrade(Integer realGrade) throws DataAccessException {

		return findTExperimentSkillUserByRealGrade(realGrade, -1, -1);
	}

	/**
	 * JPQL Query - findTExperimentSkillUserByRealGrade
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TExperimentSkillUser> findTExperimentSkillUserByRealGrade(Integer realGrade, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTExperimentSkillUserByRealGrade", startResult, maxRows, realGrade);
		return new LinkedHashSet<TExperimentSkillUser>(query.getResultList());
	}

	/**
	 * JPQL Query - findTExperimentSkillUserByGradeTime
	 *
	 */
	@Transactional
	public Set<TExperimentSkillUser> findTExperimentSkillUserByGradeTime(java.util.Calendar gradeTime) throws DataAccessException {

		return findTExperimentSkillUserByGradeTime(gradeTime, -1, -1);
	}

	/**
	 * JPQL Query - findTExperimentSkillUserByGradeTime
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TExperimentSkillUser> findTExperimentSkillUserByGradeTime(java.util.Calendar gradeTime, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTExperimentSkillUserByGradeTime", startResult, maxRows, gradeTime);
		return new LinkedHashSet<TExperimentSkillUser>(query.getResultList());
	}

	/**
	 * JPQL Query - findTExperimentSkillUserById
	 *
	 */
	@Transactional
	public TExperimentSkillUser findTExperimentSkillUserById(Integer id) throws DataAccessException {

		return findTExperimentSkillUserById(id, -1, -1);
	}

	/**
	 * JPQL Query - findTExperimentSkillUserById
	 *
	 */

	@Transactional
	public TExperimentSkillUser findTExperimentSkillUserById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findTExperimentSkillUserById", startResult, maxRows, id);
			return (net.xidlims.domain.TExperimentSkillUser) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findTExperimentSkillUserByFinalGrade
	 *
	 */
	@Transactional
	public Set<TExperimentSkillUser> findTExperimentSkillUserByFinalGrade(Integer finalGrade) throws DataAccessException {

		return findTExperimentSkillUserByFinalGrade(finalGrade, -1, -1);
	}

	/**
	 * JPQL Query - findTExperimentSkillUserByFinalGrade
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TExperimentSkillUser> findTExperimentSkillUserByFinalGrade(Integer finalGrade, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTExperimentSkillUserByFinalGrade", startResult, maxRows, finalGrade);
		return new LinkedHashSet<TExperimentSkillUser>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(TExperimentSkillUser entity) {
		return true;
	}
}
