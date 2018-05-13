package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.SchoolCourse;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage SchoolCourse entities.
 * 
 */
@Repository("SchoolCourseDAO")
@Transactional
public class SchoolCourseDAOImpl extends AbstractJpaDao<SchoolCourse> implements
		SchoolCourseDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { SchoolCourse.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new SchoolCourseDAOImpl
	 *
	 */
	public SchoolCourseDAOImpl() {
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
	 * JPQL Query - findSchoolCourseByCourseAddressTypeContaining
	 *
	 */
	@Transactional
	public Set<SchoolCourse> findSchoolCourseByCourseAddressTypeContaining(String courseAddressType) throws DataAccessException {

		return findSchoolCourseByCourseAddressTypeContaining(courseAddressType, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseByCourseAddressTypeContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourse> findSchoolCourseByCourseAddressTypeContaining(String courseAddressType, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolCourseByCourseAddressTypeContaining", startResult, maxRows, courseAddressType);
		return new LinkedHashSet<SchoolCourse>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolCourseByPlanLabTime
	 *
	 */
	@Transactional
	public Set<SchoolCourse> findSchoolCourseByPlanLabTime(String planLabTime) throws DataAccessException {

		return findSchoolCourseByPlanLabTime(planLabTime, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseByPlanLabTime
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourse> findSchoolCourseByPlanLabTime(String planLabTime, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolCourseByPlanLabTime", startResult, maxRows, planLabTime);
		return new LinkedHashSet<SchoolCourse>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolCourseByCourseCodeContaining
	 *
	 */
	@Transactional
	public Set<SchoolCourse> findSchoolCourseByCourseCodeContaining(String courseCode) throws DataAccessException {

		return findSchoolCourseByCourseCodeContaining(courseCode, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseByCourseCodeContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourse> findSchoolCourseByCourseCodeContaining(String courseCode, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolCourseByCourseCodeContaining", startResult, maxRows, courseCode);
		return new LinkedHashSet<SchoolCourse>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolCourseByCourseNameContaining
	 *
	 */
	@Transactional
	public Set<SchoolCourse> findSchoolCourseByCourseNameContaining(String courseName) throws DataAccessException {

		return findSchoolCourseByCourseNameContaining(courseName, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseByCourseNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourse> findSchoolCourseByCourseNameContaining(String courseName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolCourseByCourseNameContaining", startResult, maxRows, courseName);
		return new LinkedHashSet<SchoolCourse>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolCourseByState
	 *
	 */
	@Transactional
	public Set<SchoolCourse> findSchoolCourseByState(Integer state) throws DataAccessException {

		return findSchoolCourseByState(state, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseByState
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourse> findSchoolCourseByState(Integer state, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolCourseByState", startResult, maxRows, state);
		return new LinkedHashSet<SchoolCourse>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolCourseByClassesNameContaining
	 *
	 */
	@Transactional
	public Set<SchoolCourse> findSchoolCourseByClassesNameContaining(String classesName) throws DataAccessException {

		return findSchoolCourseByClassesNameContaining(classesName, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseByClassesNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourse> findSchoolCourseByClassesNameContaining(String classesName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolCourseByClassesNameContaining", startResult, maxRows, classesName);
		return new LinkedHashSet<SchoolCourse>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolCourseByCreditsContaining
	 *
	 */
	@Transactional
	public Set<SchoolCourse> findSchoolCourseByCreditsContaining(String credits) throws DataAccessException {

		return findSchoolCourseByCreditsContaining(credits, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseByCreditsContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourse> findSchoolCourseByCreditsContaining(String credits, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolCourseByCreditsContaining", startResult, maxRows, credits);
		return new LinkedHashSet<SchoolCourse>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolCourseByPrimaryKey
	 *
	 */
	@Transactional
	public SchoolCourse findSchoolCourseByPrimaryKey(String courseNo) throws DataAccessException {

		return findSchoolCourseByPrimaryKey(courseNo, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseByPrimaryKey
	 *
	 */

	@Transactional
	public SchoolCourse findSchoolCourseByPrimaryKey(String courseNo, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findSchoolCourseByPrimaryKey", startResult, maxRows, courseNo);
			return (net.xidlims.domain.SchoolCourse) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findSchoolCourseByCourseNo
	 *
	 */
	@Transactional
	public SchoolCourse findSchoolCourseByCourseNo(String courseNo) throws DataAccessException {

		return findSchoolCourseByCourseNo(courseNo, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseByCourseNo
	 *
	 */

	@Transactional
	public SchoolCourse findSchoolCourseByCourseNo(String courseNo, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findSchoolCourseByCourseNo", startResult, maxRows, courseNo);
			return (net.xidlims.domain.SchoolCourse) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findSchoolCourseByCredits
	 *
	 */
	@Transactional
	public Set<SchoolCourse> findSchoolCourseByCredits(String credits) throws DataAccessException {

		return findSchoolCourseByCredits(credits, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseByCredits
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourse> findSchoolCourseByCredits(String credits, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolCourseByCredits", startResult, maxRows, credits);
		return new LinkedHashSet<SchoolCourse>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolCourseByCourseNoContaining
	 *
	 */
	@Transactional
	public Set<SchoolCourse> findSchoolCourseByCourseNoContaining(String courseNo) throws DataAccessException {

		return findSchoolCourseByCourseNoContaining(courseNo, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseByCourseNoContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourse> findSchoolCourseByCourseNoContaining(String courseNo, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolCourseByCourseNoContaining", startResult, maxRows, courseNo);
		return new LinkedHashSet<SchoolCourse>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolCourseByCourseName
	 *
	 */
	@Transactional
	public Set<SchoolCourse> findSchoolCourseByCourseName(String courseName) throws DataAccessException {

		return findSchoolCourseByCourseName(courseName, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseByCourseName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourse> findSchoolCourseByCourseName(String courseName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolCourseByCourseName", startResult, maxRows, courseName);
		return new LinkedHashSet<SchoolCourse>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolCourseByCreatedDate
	 *
	 */
	@Transactional
	public Set<SchoolCourse> findSchoolCourseByCreatedDate(java.util.Calendar createdDate) throws DataAccessException {

		return findSchoolCourseByCreatedDate(createdDate, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseByCreatedDate
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourse> findSchoolCourseByCreatedDate(java.util.Calendar createdDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolCourseByCreatedDate", startResult, maxRows, createdDate);
		return new LinkedHashSet<SchoolCourse>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolCourseByMemoContaining
	 *
	 */
	@Transactional
	public Set<SchoolCourse> findSchoolCourseByMemoContaining(String memo) throws DataAccessException {

		return findSchoolCourseByMemoContaining(memo, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseByMemoContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourse> findSchoolCourseByMemoContaining(String memo, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolCourseByMemoContaining", startResult, maxRows, memo);
		return new LinkedHashSet<SchoolCourse>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolCourseByMemo
	 *
	 */
	@Transactional
	public Set<SchoolCourse> findSchoolCourseByMemo(String memo) throws DataAccessException {

		return findSchoolCourseByMemo(memo, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseByMemo
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourse> findSchoolCourseByMemo(String memo, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolCourseByMemo", startResult, maxRows, memo);
		return new LinkedHashSet<SchoolCourse>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolCourseByCourseTypeContaining
	 *
	 */
	@Transactional
	public Set<SchoolCourse> findSchoolCourseByCourseTypeContaining(String courseType) throws DataAccessException {

		return findSchoolCourseByCourseTypeContaining(courseType, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseByCourseTypeContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourse> findSchoolCourseByCourseTypeContaining(String courseType, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolCourseByCourseTypeContaining", startResult, maxRows, courseType);
		return new LinkedHashSet<SchoolCourse>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolCourseByPlanStudentNumber
	 *
	 */
	@Transactional
	public Set<SchoolCourse> findSchoolCourseByPlanStudentNumber(String planStudentNumber) throws DataAccessException {

		return findSchoolCourseByPlanStudentNumber(planStudentNumber, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseByPlanStudentNumber
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourse> findSchoolCourseByPlanStudentNumber(String planStudentNumber, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolCourseByPlanStudentNumber", startResult, maxRows, planStudentNumber);
		return new LinkedHashSet<SchoolCourse>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolCourseByPlanLabTimeContaining
	 *
	 */
	@Transactional
	public Set<SchoolCourse> findSchoolCourseByPlanLabTimeContaining(String planLabTime) throws DataAccessException {

		return findSchoolCourseByPlanLabTimeContaining(planLabTime, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseByPlanLabTimeContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourse> findSchoolCourseByPlanLabTimeContaining(String planLabTime, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolCourseByPlanLabTimeContaining", startResult, maxRows, planLabTime);
		return new LinkedHashSet<SchoolCourse>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolCourseByCourseType
	 *
	 */
	@Transactional
	public Set<SchoolCourse> findSchoolCourseByCourseType(String courseType) throws DataAccessException {

		return findSchoolCourseByCourseType(courseType, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseByCourseType
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourse> findSchoolCourseByCourseType(String courseType, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolCourseByCourseType", startResult, maxRows, courseType);
		return new LinkedHashSet<SchoolCourse>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolCourseByClassesName
	 *
	 */
	@Transactional
	public Set<SchoolCourse> findSchoolCourseByClassesName(String classesName) throws DataAccessException {

		return findSchoolCourseByClassesName(classesName, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseByClassesName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourse> findSchoolCourseByClassesName(String classesName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolCourseByClassesName", startResult, maxRows, classesName);
		return new LinkedHashSet<SchoolCourse>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolCourseByCourseAddressType
	 *
	 */
	@Transactional
	public Set<SchoolCourse> findSchoolCourseByCourseAddressType(String courseAddressType) throws DataAccessException {

		return findSchoolCourseByCourseAddressType(courseAddressType, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseByCourseAddressType
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourse> findSchoolCourseByCourseAddressType(String courseAddressType, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolCourseByCourseAddressType", startResult, maxRows, courseAddressType);
		return new LinkedHashSet<SchoolCourse>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolCourseByCourseCode
	 *
	 */
	@Transactional
	public Set<SchoolCourse> findSchoolCourseByCourseCode(String courseCode) throws DataAccessException {

		return findSchoolCourseByCourseCode(courseCode, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseByCourseCode
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourse> findSchoolCourseByCourseCode(String courseCode, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolCourseByCourseCode", startResult, maxRows, courseCode);
		return new LinkedHashSet<SchoolCourse>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllSchoolCourses
	 *
	 */
	@Transactional
	public Set<SchoolCourse> findAllSchoolCourses() throws DataAccessException {

		return findAllSchoolCourses(-1, -1);
	}

	/**
	 * JPQL Query - findAllSchoolCourses
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourse> findAllSchoolCourses(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllSchoolCourses", startResult, maxRows);
		return new LinkedHashSet<SchoolCourse>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolCourseByPlanStudentNumberContaining
	 *
	 */
	@Transactional
	public Set<SchoolCourse> findSchoolCourseByPlanStudentNumberContaining(String planStudentNumber) throws DataAccessException {

		return findSchoolCourseByPlanStudentNumberContaining(planStudentNumber, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseByPlanStudentNumberContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourse> findSchoolCourseByPlanStudentNumberContaining(String planStudentNumber, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolCourseByPlanStudentNumberContaining", startResult, maxRows, planStudentNumber);
		return new LinkedHashSet<SchoolCourse>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolCourseByUpdatedDate
	 *
	 */
	@Transactional
	public Set<SchoolCourse> findSchoolCourseByUpdatedDate(java.util.Calendar updatedDate) throws DataAccessException {

		return findSchoolCourseByUpdatedDate(updatedDate, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseByUpdatedDate
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourse> findSchoolCourseByUpdatedDate(java.util.Calendar updatedDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolCourseByUpdatedDate", startResult, maxRows, updatedDate);
		return new LinkedHashSet<SchoolCourse>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(SchoolCourse entity) {
		return true;
	}
}
