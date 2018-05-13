package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.SchoolCourseMerge;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage SchoolCourseMerge entities.
 * 
 */
@Repository("SchoolCourseMergeDAO")
@Transactional
public class SchoolCourseMergeDAOImpl extends AbstractJpaDao<SchoolCourseMerge>
		implements SchoolCourseMergeDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { SchoolCourseMerge.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new SchoolCourseMergeDAOImpl
	 *
	 */
	public SchoolCourseMergeDAOImpl() {
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
	 * JPQL Query - findSchoolCourseMergeByCourseNameContaining
	 *
	 */
	@Transactional
	public Set<SchoolCourseMerge> findSchoolCourseMergeByCourseNameContaining(String courseName) throws DataAccessException {

		return findSchoolCourseMergeByCourseNameContaining(courseName, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseMergeByCourseNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourseMerge> findSchoolCourseMergeByCourseNameContaining(String courseName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolCourseMergeByCourseNameContaining", startResult, maxRows, courseName);
		return new LinkedHashSet<SchoolCourseMerge>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolCourseMergeByCourseDetailNo
	 *
	 */
	@Transactional
	public Set<SchoolCourseMerge> findSchoolCourseMergeByCourseDetailNo(String courseDetailNo) throws DataAccessException {

		return findSchoolCourseMergeByCourseDetailNo(courseDetailNo, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseMergeByCourseDetailNo
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourseMerge> findSchoolCourseMergeByCourseDetailNo(String courseDetailNo, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolCourseMergeByCourseDetailNo", startResult, maxRows, courseDetailNo);
		return new LinkedHashSet<SchoolCourseMerge>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolCourseMergeByCourseDetailNoContaining
	 *
	 */
	@Transactional
	public Set<SchoolCourseMerge> findSchoolCourseMergeByCourseDetailNoContaining(String courseDetailNo) throws DataAccessException {

		return findSchoolCourseMergeByCourseDetailNoContaining(courseDetailNo, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseMergeByCourseDetailNoContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourseMerge> findSchoolCourseMergeByCourseDetailNoContaining(String courseDetailNo, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolCourseMergeByCourseDetailNoContaining", startResult, maxRows, courseDetailNo);
		return new LinkedHashSet<SchoolCourseMerge>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolCourseMergeByCourseNo
	 *
	 */
	@Transactional
	public Set<SchoolCourseMerge> findSchoolCourseMergeByCourseNo(String courseNo) throws DataAccessException {

		return findSchoolCourseMergeByCourseNo(courseNo, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseMergeByCourseNo
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourseMerge> findSchoolCourseMergeByCourseNo(String courseNo, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolCourseMergeByCourseNo", startResult, maxRows, courseNo);
		return new LinkedHashSet<SchoolCourseMerge>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolCourseMergeByCourseName
	 *
	 */
	@Transactional
	public Set<SchoolCourseMerge> findSchoolCourseMergeByCourseName(String courseName) throws DataAccessException {

		return findSchoolCourseMergeByCourseName(courseName, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseMergeByCourseName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourseMerge> findSchoolCourseMergeByCourseName(String courseName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolCourseMergeByCourseName", startResult, maxRows, courseName);
		return new LinkedHashSet<SchoolCourseMerge>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolCourseMergeByCourseNumber
	 *
	 */
	@Transactional
	public Set<SchoolCourseMerge> findSchoolCourseMergeByCourseNumber(String courseNumber) throws DataAccessException {

		return findSchoolCourseMergeByCourseNumber(courseNumber, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseMergeByCourseNumber
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourseMerge> findSchoolCourseMergeByCourseNumber(String courseNumber, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolCourseMergeByCourseNumber", startResult, maxRows, courseNumber);
		return new LinkedHashSet<SchoolCourseMerge>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolCourseMergeByStudentNumbersContaining
	 *
	 */
	@Transactional
	public Set<SchoolCourseMerge> findSchoolCourseMergeByStudentNumbersContaining(String studentNumbers) throws DataAccessException {

		return findSchoolCourseMergeByStudentNumbersContaining(studentNumbers, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseMergeByStudentNumbersContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourseMerge> findSchoolCourseMergeByStudentNumbersContaining(String studentNumbers, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolCourseMergeByStudentNumbersContaining", startResult, maxRows, studentNumbers);
		return new LinkedHashSet<SchoolCourseMerge>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolCourseMergeByPrimaryKey
	 *
	 */
	@Transactional
	public SchoolCourseMerge findSchoolCourseMergeByPrimaryKey(Integer id) throws DataAccessException {

		return findSchoolCourseMergeByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseMergeByPrimaryKey
	 *
	 */

	@Transactional
	public SchoolCourseMerge findSchoolCourseMergeByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findSchoolCourseMergeByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.SchoolCourseMerge) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findSchoolCourseMergeByStudentNumbers
	 *
	 */
	@Transactional
	public Set<SchoolCourseMerge> findSchoolCourseMergeByStudentNumbers(String studentNumbers) throws DataAccessException {

		return findSchoolCourseMergeByStudentNumbers(studentNumbers, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseMergeByStudentNumbers
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourseMerge> findSchoolCourseMergeByStudentNumbers(String studentNumbers, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolCourseMergeByStudentNumbers", startResult, maxRows, studentNumbers);
		return new LinkedHashSet<SchoolCourseMerge>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolCourseMergeByCourseNoContaining
	 *
	 */
	@Transactional
	public Set<SchoolCourseMerge> findSchoolCourseMergeByCourseNoContaining(String courseNo) throws DataAccessException {

		return findSchoolCourseMergeByCourseNoContaining(courseNo, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseMergeByCourseNoContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourseMerge> findSchoolCourseMergeByCourseNoContaining(String courseNo, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolCourseMergeByCourseNoContaining", startResult, maxRows, courseNo);
		return new LinkedHashSet<SchoolCourseMerge>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolCourseMergeById
	 *
	 */
	@Transactional
	public SchoolCourseMerge findSchoolCourseMergeById(Integer id) throws DataAccessException {

		return findSchoolCourseMergeById(id, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseMergeById
	 *
	 */

	@Transactional
	public SchoolCourseMerge findSchoolCourseMergeById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findSchoolCourseMergeById", startResult, maxRows, id);
			return (net.xidlims.domain.SchoolCourseMerge) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findAllSchoolCourseMerges
	 *
	 */
	@Transactional
	public Set<SchoolCourseMerge> findAllSchoolCourseMerges() throws DataAccessException {

		return findAllSchoolCourseMerges(-1, -1);
	}

	/**
	 * JPQL Query - findAllSchoolCourseMerges
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourseMerge> findAllSchoolCourseMerges(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllSchoolCourseMerges", startResult, maxRows);
		return new LinkedHashSet<SchoolCourseMerge>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolCourseMergeByCourseNumberContaining
	 *
	 */
	@Transactional
	public Set<SchoolCourseMerge> findSchoolCourseMergeByCourseNumberContaining(String courseNumber) throws DataAccessException {

		return findSchoolCourseMergeByCourseNumberContaining(courseNumber, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolCourseMergeByCourseNumberContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolCourseMerge> findSchoolCourseMergeByCourseNumberContaining(String courseNumber, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolCourseMergeByCourseNumberContaining", startResult, maxRows, courseNumber);
		return new LinkedHashSet<SchoolCourseMerge>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(SchoolCourseMerge entity) {
		return true;
	}
}
