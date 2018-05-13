package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.SchoolCourseInfo;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage SchoolCourseInfo entities.
 * 
 */
@Repository("SchoolCourseInfoDAO")
@Transactional
public class SchoolCourseInfoDAOImpl extends AbstractJpaDao<SchoolCourseInfo>
		implements SchoolCourseInfoDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { SchoolCourseInfo.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new SchoolCourseInfoDAOImpl
	 *
	 */
	public SchoolCourseInfoDAOImpl() {
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
	 * JPQL Query - findSchoolCourseInfoByUpdatedAt
	 *
	 */
	@Transactional
	public Set<SchoolCourseInfo> findSchoolCourseInfoByUpdatedAt(java.util.Calendar updatedAt) throws DataAccessException {

		return findSchoolCourseInfoByUpdatedAt(updatedAt, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseInfoByUpdatedAt
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourseInfo> findSchoolCourseInfoByUpdatedAt(java.util.Calendar updatedAt, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolCourseInfoByUpdatedAt", startResult, maxRows, updatedAt);
		return new LinkedHashSet<SchoolCourseInfo>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolCourseInfoByTotalHours
	 *
	 */
	@Transactional
	public Set<SchoolCourseInfo> findSchoolCourseInfoByTotalHours(Integer totalHours) throws DataAccessException {

		return findSchoolCourseInfoByTotalHours(totalHours, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseInfoByTotalHours
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourseInfo> findSchoolCourseInfoByTotalHours(Integer totalHours, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolCourseInfoByTotalHours", startResult, maxRows, totalHours);
		return new LinkedHashSet<SchoolCourseInfo>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolCourseInfoByPrimaryKey
	 *
	 */
	@Transactional
	public SchoolCourseInfo findSchoolCourseInfoByPrimaryKey(String courseNumber) throws DataAccessException {

		return findSchoolCourseInfoByPrimaryKey(courseNumber, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseInfoByPrimaryKey
	 *
	 */

	@Transactional
	public SchoolCourseInfo findSchoolCourseInfoByPrimaryKey(String courseNumber, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findSchoolCourseInfoByPrimaryKey", startResult, maxRows, courseNumber);
			return (net.xidlims.domain.SchoolCourseInfo) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findSchoolCourseInfoByCourseNameContaining
	 *
	 */
	@Transactional
	public Set<SchoolCourseInfo> findSchoolCourseInfoByCourseNameContaining(String courseName) throws DataAccessException {

		return findSchoolCourseInfoByCourseNameContaining(courseName, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseInfoByCourseNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourseInfo> findSchoolCourseInfoByCourseNameContaining(String courseName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolCourseInfoByCourseNameContaining", startResult, maxRows, courseName);
		return new LinkedHashSet<SchoolCourseInfo>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolCourseInfoByCourseNumber
	 *
	 */
	@Transactional
	public SchoolCourseInfo findSchoolCourseInfoByCourseNumber(String courseNumber) throws DataAccessException {

		return findSchoolCourseInfoByCourseNumber(courseNumber, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseInfoByCourseNumber
	 *
	 */

	@Transactional
	public SchoolCourseInfo findSchoolCourseInfoByCourseNumber(String courseNumber, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findSchoolCourseInfoByCourseNumber", startResult, maxRows, courseNumber);
			return (net.xidlims.domain.SchoolCourseInfo) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findSchoolCourseInfoByUpdatedBy
	 *
	 */
	@Transactional
	public Set<SchoolCourseInfo> findSchoolCourseInfoByUpdatedBy(Integer updatedBy) throws DataAccessException {

		return findSchoolCourseInfoByUpdatedBy(updatedBy, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseInfoByUpdatedBy
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourseInfo> findSchoolCourseInfoByUpdatedBy(Integer updatedBy, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolCourseInfoByUpdatedBy", startResult, maxRows, updatedBy);
		return new LinkedHashSet<SchoolCourseInfo>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolCourseInfoByAcademyNumber
	 *
	 */
	@Transactional
	public Set<SchoolCourseInfo> findSchoolCourseInfoByAcademyNumber(String academyNumber) throws DataAccessException {

		return findSchoolCourseInfoByAcademyNumber(academyNumber, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseInfoByAcademyNumber
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourseInfo> findSchoolCourseInfoByAcademyNumber(String academyNumber, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolCourseInfoByAcademyNumber", startResult, maxRows, academyNumber);
		return new LinkedHashSet<SchoolCourseInfo>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolCourseInfoByCreatedBy
	 *
	 */
	@Transactional
	public Set<SchoolCourseInfo> findSchoolCourseInfoByCreatedBy(Integer createdBy) throws DataAccessException {

		return findSchoolCourseInfoByCreatedBy(createdBy, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseInfoByCreatedBy
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourseInfo> findSchoolCourseInfoByCreatedBy(Integer createdBy, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolCourseInfoByCreatedBy", startResult, maxRows, createdBy);
		return new LinkedHashSet<SchoolCourseInfo>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolCourseInfoByTheoreticalHours
	 *
	 */
	@Transactional
	public Set<SchoolCourseInfo> findSchoolCourseInfoByTheoreticalHours(Integer theoreticalHours) throws DataAccessException {

		return findSchoolCourseInfoByTheoreticalHours(theoreticalHours, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseInfoByTheoreticalHours
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourseInfo> findSchoolCourseInfoByTheoreticalHours(Integer theoreticalHours, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolCourseInfoByTheoreticalHours", startResult, maxRows, theoreticalHours);
		return new LinkedHashSet<SchoolCourseInfo>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolCourseInfoByCreatedAt
	 *
	 */
	@Transactional
	public Set<SchoolCourseInfo> findSchoolCourseInfoByCreatedAt(java.util.Calendar createdAt) throws DataAccessException {

		return findSchoolCourseInfoByCreatedAt(createdAt, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseInfoByCreatedAt
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourseInfo> findSchoolCourseInfoByCreatedAt(java.util.Calendar createdAt, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolCourseInfoByCreatedAt", startResult, maxRows, createdAt);
		return new LinkedHashSet<SchoolCourseInfo>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolCourseInfoByFlag
	 *
	 */
	@Transactional
	public Set<SchoolCourseInfo> findSchoolCourseInfoByFlag(Integer flag) throws DataAccessException {

		return findSchoolCourseInfoByFlag(flag, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseInfoByFlag
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourseInfo> findSchoolCourseInfoByFlag(Integer flag, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolCourseInfoByFlag", startResult, maxRows, flag);
		return new LinkedHashSet<SchoolCourseInfo>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolCourseInfoByCourseName
	 *
	 */
	@Transactional
	public Set<SchoolCourseInfo> findSchoolCourseInfoByCourseName(String courseName) throws DataAccessException {

		return findSchoolCourseInfoByCourseName(courseName, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseInfoByCourseName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourseInfo> findSchoolCourseInfoByCourseName(String courseName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolCourseInfoByCourseName", startResult, maxRows, courseName);
		return new LinkedHashSet<SchoolCourseInfo>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolCourseInfoByCourseEnName
	 *
	 */
	@Transactional
	public Set<SchoolCourseInfo> findSchoolCourseInfoByCourseEnName(String courseEnName) throws DataAccessException {

		return findSchoolCourseInfoByCourseEnName(courseEnName, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseInfoByCourseEnName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourseInfo> findSchoolCourseInfoByCourseEnName(String courseEnName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolCourseInfoByCourseEnName", startResult, maxRows, courseEnName);
		return new LinkedHashSet<SchoolCourseInfo>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllSchoolCourseInfos
	 *
	 */
	@Transactional
	public Set<SchoolCourseInfo> findAllSchoolCourseInfos() throws DataAccessException {

		return findAllSchoolCourseInfos(-1, -1);
	}

	/**
	 * JPQL Query - findAllSchoolCourseInfos
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourseInfo> findAllSchoolCourseInfos(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllSchoolCourseInfos", startResult, maxRows);
		return new LinkedHashSet<SchoolCourseInfo>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolCourseInfoByCourseEnNameContaining
	 *
	 */
	@Transactional
	public Set<SchoolCourseInfo> findSchoolCourseInfoByCourseEnNameContaining(String courseEnName) throws DataAccessException {

		return findSchoolCourseInfoByCourseEnNameContaining(courseEnName, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseInfoByCourseEnNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourseInfo> findSchoolCourseInfoByCourseEnNameContaining(String courseEnName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolCourseInfoByCourseEnNameContaining", startResult, maxRows, courseEnName);
		return new LinkedHashSet<SchoolCourseInfo>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolCourseInfoByCourseNumberContaining
	 *
	 */
	@Transactional
	public Set<SchoolCourseInfo> findSchoolCourseInfoByCourseNumberContaining(String courseNumber) throws DataAccessException {

		return findSchoolCourseInfoByCourseNumberContaining(courseNumber, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseInfoByCourseNumberContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourseInfo> findSchoolCourseInfoByCourseNumberContaining(String courseNumber, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolCourseInfoByCourseNumberContaining", startResult, maxRows, courseNumber);
		return new LinkedHashSet<SchoolCourseInfo>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolCourseInfoByAcademyNumberContaining
	 *
	 */
	@Transactional
	public Set<SchoolCourseInfo> findSchoolCourseInfoByAcademyNumberContaining(String academyNumber) throws DataAccessException {

		return findSchoolCourseInfoByAcademyNumberContaining(academyNumber, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseInfoByAcademyNumberContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourseInfo> findSchoolCourseInfoByAcademyNumberContaining(String academyNumber, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolCourseInfoByAcademyNumberContaining", startResult, maxRows, academyNumber);
		return new LinkedHashSet<SchoolCourseInfo>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(SchoolCourseInfo entity) {
		return true;
	}
}
