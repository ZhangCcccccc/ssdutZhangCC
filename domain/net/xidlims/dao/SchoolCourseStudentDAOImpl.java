package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.SchoolCourseStudent;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage SchoolCourseStudent entities.
 * 
 */
@Repository("SchoolCourseStudentDAO")
@Transactional
public class SchoolCourseStudentDAOImpl extends AbstractJpaDao<SchoolCourseStudent>
		implements SchoolCourseStudentDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { SchoolCourseStudent.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new SchoolCourseStudentDAOImpl
	 *
	 */
	public SchoolCourseStudentDAOImpl() {
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
	 * JPQL Query - findSchoolCourseStudentByCreatedDate
	 *
	 */
	@Transactional
	public Set<SchoolCourseStudent> findSchoolCourseStudentByCreatedDate(java.util.Calendar createdDate) throws DataAccessException {

		return findSchoolCourseStudentByCreatedDate(createdDate, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseStudentByCreatedDate
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourseStudent> findSchoolCourseStudentByCreatedDate(java.util.Calendar createdDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolCourseStudentByCreatedDate", startResult, maxRows, createdDate);
		return new LinkedHashSet<SchoolCourseStudent>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllSchoolCourseStudents
	 *
	 */
	@Transactional
	public Set<SchoolCourseStudent> findAllSchoolCourseStudents() throws DataAccessException {

		return findAllSchoolCourseStudents(-1, -1);
	}

	/**
	 * JPQL Query - findAllSchoolCourseStudents
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourseStudent> findAllSchoolCourseStudents(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllSchoolCourseStudents", startResult, maxRows);
		return new LinkedHashSet<SchoolCourseStudent>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolCourseStudentByMemo
	 *
	 */
	@Transactional
	public Set<SchoolCourseStudent> findSchoolCourseStudentByMemo(String memo) throws DataAccessException {

		return findSchoolCourseStudentByMemo(memo, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseStudentByMemo
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourseStudent> findSchoolCourseStudentByMemo(String memo, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolCourseStudentByMemo", startResult, maxRows, memo);
		return new LinkedHashSet<SchoolCourseStudent>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolCourseStudentByMajorNameContaining
	 *
	 */
	@Transactional
	public Set<SchoolCourseStudent> findSchoolCourseStudentByMajorNameContaining(String majorName) throws DataAccessException {

		return findSchoolCourseStudentByMajorNameContaining(majorName, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseStudentByMajorNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourseStudent> findSchoolCourseStudentByMajorNameContaining(String majorName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolCourseStudentByMajorNameContaining", startResult, maxRows, majorName);
		return new LinkedHashSet<SchoolCourseStudent>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolCourseStudentById
	 *
	 */
	@Transactional
	public SchoolCourseStudent findSchoolCourseStudentById(Integer id) throws DataAccessException {

		return findSchoolCourseStudentById(id, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseStudentById
	 *
	 */

	@Transactional
	public SchoolCourseStudent findSchoolCourseStudentById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findSchoolCourseStudentById", startResult, maxRows, id);
			return (net.xidlims.domain.SchoolCourseStudent) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findSchoolCourseStudentByMajorDirectionName
	 *
	 */
	@Transactional
	public Set<SchoolCourseStudent> findSchoolCourseStudentByMajorDirectionName(String majorDirectionName) throws DataAccessException {

		return findSchoolCourseStudentByMajorDirectionName(majorDirectionName, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseStudentByMajorDirectionName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourseStudent> findSchoolCourseStudentByMajorDirectionName(String majorDirectionName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolCourseStudentByMajorDirectionName", startResult, maxRows, majorDirectionName);
		return new LinkedHashSet<SchoolCourseStudent>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolCourseStudentByState
	 *
	 */
	@Transactional
	public Set<SchoolCourseStudent> findSchoolCourseStudentByState(Integer state) throws DataAccessException {

		return findSchoolCourseStudentByState(state, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseStudentByState
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourseStudent> findSchoolCourseStudentByState(Integer state, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolCourseStudentByState", startResult, maxRows, state);
		return new LinkedHashSet<SchoolCourseStudent>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolCourseStudentByPrimaryKey
	 *
	 */
	@Transactional
	public SchoolCourseStudent findSchoolCourseStudentByPrimaryKey(Integer id) throws DataAccessException {

		return findSchoolCourseStudentByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseStudentByPrimaryKey
	 *
	 */

	@Transactional
	public SchoolCourseStudent findSchoolCourseStudentByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findSchoolCourseStudentByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.SchoolCourseStudent) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findSchoolCourseStudentByMajorDirectionNameContaining
	 *
	 */
	@Transactional
	public Set<SchoolCourseStudent> findSchoolCourseStudentByMajorDirectionNameContaining(String majorDirectionName) throws DataAccessException {

		return findSchoolCourseStudentByMajorDirectionNameContaining(majorDirectionName, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseStudentByMajorDirectionNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourseStudent> findSchoolCourseStudentByMajorDirectionNameContaining(String majorDirectionName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolCourseStudentByMajorDirectionNameContaining", startResult, maxRows, majorDirectionName);
		return new LinkedHashSet<SchoolCourseStudent>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolCourseStudentByMemoContaining
	 *
	 */
	@Transactional
	public Set<SchoolCourseStudent> findSchoolCourseStudentByMemoContaining(String memo) throws DataAccessException {

		return findSchoolCourseStudentByMemoContaining(memo, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseStudentByMemoContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourseStudent> findSchoolCourseStudentByMemoContaining(String memo, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolCourseStudentByMemoContaining", startResult, maxRows, memo);
		return new LinkedHashSet<SchoolCourseStudent>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolCourseStudentByMajorName
	 *
	 */
	@Transactional
	public Set<SchoolCourseStudent> findSchoolCourseStudentByMajorName(String majorName) throws DataAccessException {

		return findSchoolCourseStudentByMajorName(majorName, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseStudentByMajorName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourseStudent> findSchoolCourseStudentByMajorName(String majorName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolCourseStudentByMajorName", startResult, maxRows, majorName);
		return new LinkedHashSet<SchoolCourseStudent>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolCourseStudentByClassesStudents
	 *
	 */
	@Transactional
	public Set<SchoolCourseStudent> findSchoolCourseStudentByClassesStudents(String classesStudents) throws DataAccessException {

		return findSchoolCourseStudentByClassesStudents(classesStudents, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseStudentByClassesStudents
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourseStudent> findSchoolCourseStudentByClassesStudents(String classesStudents, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolCourseStudentByClassesStudents", startResult, maxRows, classesStudents);
		return new LinkedHashSet<SchoolCourseStudent>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolCourseStudentByUpdatedDate
	 *
	 */
	@Transactional
	public Set<SchoolCourseStudent> findSchoolCourseStudentByUpdatedDate(java.util.Calendar updatedDate) throws DataAccessException {

		return findSchoolCourseStudentByUpdatedDate(updatedDate, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseStudentByUpdatedDate
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourseStudent> findSchoolCourseStudentByUpdatedDate(java.util.Calendar updatedDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolCourseStudentByUpdatedDate", startResult, maxRows, updatedDate);
		return new LinkedHashSet<SchoolCourseStudent>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolCourseStudentByClassesStudentsContaining
	 *
	 */
	@Transactional
	public Set<SchoolCourseStudent> findSchoolCourseStudentByClassesStudentsContaining(String classesStudents) throws DataAccessException {

		return findSchoolCourseStudentByClassesStudentsContaining(classesStudents, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseStudentByClassesStudentsContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourseStudent> findSchoolCourseStudentByClassesStudentsContaining(String classesStudents, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolCourseStudentByClassesStudentsContaining", startResult, maxRows, classesStudents);
		return new LinkedHashSet<SchoolCourseStudent>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolCourseStudentByTestNum
	 *
	 */
	@Transactional
	public Set<SchoolCourseStudent> findSchoolCourseStudentByTestNum(Integer testNum) throws DataAccessException {

		return findSchoolCourseStudentByTestNum(testNum, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseStudentByTestNum
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourseStudent> findSchoolCourseStudentByTestNum(Integer testNum, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolCourseStudentByTestNum", startResult, maxRows, testNum);
		return new LinkedHashSet<SchoolCourseStudent>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(SchoolCourseStudent entity) {
		return true;
	}
}
