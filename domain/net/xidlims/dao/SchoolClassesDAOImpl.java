package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.SchoolClasses;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage SchoolClasses entities.
 * 
 */
@Repository("SchoolClassesDAO")
@Transactional
public class SchoolClassesDAOImpl extends AbstractJpaDao<SchoolClasses>
		implements SchoolClassesDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { SchoolClasses.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new SchoolClassesDAOImpl
	 *
	 */
	public SchoolClassesDAOImpl() {
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
	 * JPQL Query - findSchoolClassesByAcademyNumberContaining
	 *
	 */
	@Transactional
	public Set<SchoolClasses> findSchoolClassesByAcademyNumberContaining(String academyNumber) throws DataAccessException {

		return findSchoolClassesByAcademyNumberContaining(academyNumber, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolClassesByAcademyNumberContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolClasses> findSchoolClassesByAcademyNumberContaining(String academyNumber, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolClassesByAcademyNumberContaining", startResult, maxRows, academyNumber);
		return new LinkedHashSet<SchoolClasses>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolClassesByPrimaryKey
	 *
	 */
	@Transactional
	public SchoolClasses findSchoolClassesByPrimaryKey(String classNumber) throws DataAccessException {

		return findSchoolClassesByPrimaryKey(classNumber, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolClassesByPrimaryKey
	 *
	 */

	@Transactional
	public SchoolClasses findSchoolClassesByPrimaryKey(String classNumber, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findSchoolClassesByPrimaryKey", startResult, maxRows, classNumber);
			return (net.xidlims.domain.SchoolClasses) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findSchoolClassesByClassPlanStudents
	 *
	 */
	@Transactional
	public Set<SchoolClasses> findSchoolClassesByClassPlanStudents(String classPlanStudents) throws DataAccessException {

		return findSchoolClassesByClassPlanStudents(classPlanStudents, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolClassesByClassPlanStudents
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolClasses> findSchoolClassesByClassPlanStudents(String classPlanStudents, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolClassesByClassPlanStudents", startResult, maxRows, classPlanStudents);
		return new LinkedHashSet<SchoolClasses>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolClassesByClassGrade
	 *
	 */
	@Transactional
	public Set<SchoolClasses> findSchoolClassesByClassGrade(String classGrade) throws DataAccessException {

		return findSchoolClassesByClassGrade(classGrade, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolClassesByClassGrade
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolClasses> findSchoolClassesByClassGrade(String classGrade, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolClassesByClassGrade", startResult, maxRows, classGrade);
		return new LinkedHashSet<SchoolClasses>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolClassesByAttendanceTimeContaining
	 *
	 */
	@Transactional
	public Set<SchoolClasses> findSchoolClassesByAttendanceTimeContaining(String attendanceTime) throws DataAccessException {

		return findSchoolClassesByAttendanceTimeContaining(attendanceTime, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolClassesByAttendanceTimeContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolClasses> findSchoolClassesByAttendanceTimeContaining(String attendanceTime, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolClassesByAttendanceTimeContaining", startResult, maxRows, attendanceTime);
		return new LinkedHashSet<SchoolClasses>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolClassesByUpdatedAt
	 *
	 */
	@Transactional
	public Set<SchoolClasses> findSchoolClassesByUpdatedAt(java.util.Calendar updatedAt) throws DataAccessException {

		return findSchoolClassesByUpdatedAt(updatedAt, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolClassesByUpdatedAt
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolClasses> findSchoolClassesByUpdatedAt(java.util.Calendar updatedAt, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolClassesByUpdatedAt", startResult, maxRows, updatedAt);
		return new LinkedHashSet<SchoolClasses>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllSchoolClassess
	 *
	 */
	@Transactional
	public Set<SchoolClasses> findAllSchoolClassess() throws DataAccessException {

		return findAllSchoolClassess(-1, -1);
	}

	/**
	 * JPQL Query - findAllSchoolClassess
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolClasses> findAllSchoolClassess(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllSchoolClassess", startResult, maxRows);
		return new LinkedHashSet<SchoolClasses>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolClassesByClassNameContaining
	 *
	 */
	@Transactional
	public Set<SchoolClasses> findSchoolClassesByClassNameContaining(String className) throws DataAccessException {

		return findSchoolClassesByClassNameContaining(className, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolClassesByClassNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolClasses> findSchoolClassesByClassNameContaining(String className, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolClassesByClassNameContaining", startResult, maxRows, className);
		return new LinkedHashSet<SchoolClasses>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolClassesByClassNumber
	 *
	 */
	@Transactional
	public SchoolClasses findSchoolClassesByClassNumber(String classNumber) throws DataAccessException {

		return findSchoolClassesByClassNumber(classNumber, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolClassesByClassNumber
	 *
	 */

	@Transactional
	public SchoolClasses findSchoolClassesByClassNumber(String classNumber, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findSchoolClassesByClassNumber", startResult, maxRows, classNumber);
			return (net.xidlims.domain.SchoolClasses) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findSchoolClassesByClassNumberContaining
	 *
	 */
	@Transactional
	public Set<SchoolClasses> findSchoolClassesByClassNumberContaining(String classNumber) throws DataAccessException {

		return findSchoolClassesByClassNumberContaining(classNumber, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolClassesByClassNumberContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolClasses> findSchoolClassesByClassNumberContaining(String classNumber, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolClassesByClassNumberContaining", startResult, maxRows, classNumber);
		return new LinkedHashSet<SchoolClasses>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolClassesByCreatedAt
	 *
	 */
	@Transactional
	public Set<SchoolClasses> findSchoolClassesByCreatedAt(java.util.Calendar createdAt) throws DataAccessException {

		return findSchoolClassesByCreatedAt(createdAt, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolClassesByCreatedAt
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolClasses> findSchoolClassesByCreatedAt(java.util.Calendar createdAt, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolClassesByCreatedAt", startResult, maxRows, createdAt);
		return new LinkedHashSet<SchoolClasses>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolClassesByClassStudentsNumber
	 *
	 */
	@Transactional
	public Set<SchoolClasses> findSchoolClassesByClassStudentsNumber(String classStudentsNumber) throws DataAccessException {

		return findSchoolClassesByClassStudentsNumber(classStudentsNumber, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolClassesByClassStudentsNumber
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolClasses> findSchoolClassesByClassStudentsNumber(String classStudentsNumber, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolClassesByClassStudentsNumber", startResult, maxRows, classStudentsNumber);
		return new LinkedHashSet<SchoolClasses>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolClassesByMajorNumber
	 *
	 */
	@Transactional
	public Set<SchoolClasses> findSchoolClassesByMajorNumber(String majorNumber) throws DataAccessException {

		return findSchoolClassesByMajorNumber(majorNumber, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolClassesByMajorNumber
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolClasses> findSchoolClassesByMajorNumber(String majorNumber, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolClassesByMajorNumber", startResult, maxRows, majorNumber);
		return new LinkedHashSet<SchoolClasses>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolClassesByAttendanceTime
	 *
	 */
	@Transactional
	public Set<SchoolClasses> findSchoolClassesByAttendanceTime(String attendanceTime) throws DataAccessException {

		return findSchoolClassesByAttendanceTime(attendanceTime, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolClassesByAttendanceTime
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolClasses> findSchoolClassesByAttendanceTime(String attendanceTime, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolClassesByAttendanceTime", startResult, maxRows, attendanceTime);
		return new LinkedHashSet<SchoolClasses>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolClassesByClassGradeContaining
	 *
	 */
	@Transactional
	public Set<SchoolClasses> findSchoolClassesByClassGradeContaining(String classGrade) throws DataAccessException {

		return findSchoolClassesByClassGradeContaining(classGrade, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolClassesByClassGradeContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolClasses> findSchoolClassesByClassGradeContaining(String classGrade, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolClassesByClassGradeContaining", startResult, maxRows, classGrade);
		return new LinkedHashSet<SchoolClasses>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolClassesByClassName
	 *
	 */
	@Transactional
	public Set<SchoolClasses> findSchoolClassesByClassName(String className) throws DataAccessException {

		return findSchoolClassesByClassName(className, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolClassesByClassName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolClasses> findSchoolClassesByClassName(String className, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolClassesByClassName", startResult, maxRows, className);
		return new LinkedHashSet<SchoolClasses>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolClassesByClassStudentsNumberContaining
	 *
	 */
	@Transactional
	public Set<SchoolClasses> findSchoolClassesByClassStudentsNumberContaining(String classStudentsNumber) throws DataAccessException {

		return findSchoolClassesByClassStudentsNumberContaining(classStudentsNumber, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolClassesByClassStudentsNumberContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolClasses> findSchoolClassesByClassStudentsNumberContaining(String classStudentsNumber, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolClassesByClassStudentsNumberContaining", startResult, maxRows, classStudentsNumber);
		return new LinkedHashSet<SchoolClasses>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolClassesById
	 *
	 */
	@Transactional
	public Set<SchoolClasses> findSchoolClassesById(Integer id) throws DataAccessException {

		return findSchoolClassesById(id, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolClassesById
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolClasses> findSchoolClassesById(Integer id, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolClassesById", startResult, maxRows, id);
		return new LinkedHashSet<SchoolClasses>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolClassesByClassShortName
	 *
	 */
	@Transactional
	public Set<SchoolClasses> findSchoolClassesByClassShortName(String classShortName) throws DataAccessException {

		return findSchoolClassesByClassShortName(classShortName, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolClassesByClassShortName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolClasses> findSchoolClassesByClassShortName(String classShortName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolClassesByClassShortName", startResult, maxRows, classShortName);
		return new LinkedHashSet<SchoolClasses>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolClassesByAcademyNumber
	 *
	 */
	@Transactional
	public Set<SchoolClasses> findSchoolClassesByAcademyNumber(String academyNumber) throws DataAccessException {

		return findSchoolClassesByAcademyNumber(academyNumber, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolClassesByAcademyNumber
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolClasses> findSchoolClassesByAcademyNumber(String academyNumber, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolClassesByAcademyNumber", startResult, maxRows, academyNumber);
		return new LinkedHashSet<SchoolClasses>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolClassesByMajorNumberContaining
	 *
	 */
	@Transactional
	public Set<SchoolClasses> findSchoolClassesByMajorNumberContaining(String majorNumber) throws DataAccessException {

		return findSchoolClassesByMajorNumberContaining(majorNumber, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolClassesByMajorNumberContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolClasses> findSchoolClassesByMajorNumberContaining(String majorNumber, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolClassesByMajorNumberContaining", startResult, maxRows, majorNumber);
		return new LinkedHashSet<SchoolClasses>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolClassesByClassShortNameContaining
	 *
	 */
	@Transactional
	public Set<SchoolClasses> findSchoolClassesByClassShortNameContaining(String classShortName) throws DataAccessException {

		return findSchoolClassesByClassShortNameContaining(classShortName, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolClassesByClassShortNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolClasses> findSchoolClassesByClassShortNameContaining(String classShortName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolClassesByClassShortNameContaining", startResult, maxRows, classShortName);
		return new LinkedHashSet<SchoolClasses>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolClassesByClassPlanStudentsContaining
	 *
	 */
	@Transactional
	public Set<SchoolClasses> findSchoolClassesByClassPlanStudentsContaining(String classPlanStudents) throws DataAccessException {

		return findSchoolClassesByClassPlanStudentsContaining(classPlanStudents, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolClassesByClassPlanStudentsContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolClasses> findSchoolClassesByClassPlanStudentsContaining(String classPlanStudents, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolClassesByClassPlanStudentsContaining", startResult, maxRows, classPlanStudents);
		return new LinkedHashSet<SchoolClasses>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(SchoolClasses entity) {
		return true;
	}
}
