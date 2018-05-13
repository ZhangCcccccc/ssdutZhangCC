package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.SchoolMajor;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage SchoolMajor entities.
 * 
 */
@Repository("SchoolMajorDAO")
@Transactional
public class SchoolMajorDAOImpl extends AbstractJpaDao<SchoolMajor> implements
		SchoolMajorDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { SchoolMajor.class }));

	/**
	 * EntityManager injected by Spring for persistence unit gvsunConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new SchoolMajorDAOImpl
	 *
	 */
	public SchoolMajorDAOImpl() {
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
	 * JPQL Query - findSchoolMajorByUpdatedAt
	 *
	 */
	@Transactional
	public Set<SchoolMajor> findSchoolMajorByUpdatedAt(java.util.Calendar updatedAt) throws DataAccessException {

		return findSchoolMajorByUpdatedAt(updatedAt, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolMajorByUpdatedAt
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolMajor> findSchoolMajorByUpdatedAt(java.util.Calendar updatedAt, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolMajorByUpdatedAt", startResult, maxRows, updatedAt);
		return new LinkedHashSet<SchoolMajor>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolMajorById
	 *
	 */
	@Transactional
	public Set<SchoolMajor> findSchoolMajorById(Integer id) throws DataAccessException {

		return findSchoolMajorById(id, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolMajorById
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolMajor> findSchoolMajorById(Integer id, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolMajorById", startResult, maxRows, id);
		return new LinkedHashSet<SchoolMajor>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolMajorByMajorName
	 *
	 */
	@Transactional
	public Set<SchoolMajor> findSchoolMajorByMajorName(String majorName) throws DataAccessException {

		return findSchoolMajorByMajorName(majorName, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolMajorByMajorName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolMajor> findSchoolMajorByMajorName(String majorName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolMajorByMajorName", startResult, maxRows, majorName);
		return new LinkedHashSet<SchoolMajor>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolMajorByPrimaryKey
	 *
	 */
	@Transactional
	public SchoolMajor findSchoolMajorByPrimaryKey(String majorNumber) throws DataAccessException {

		return findSchoolMajorByPrimaryKey(majorNumber, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolMajorByPrimaryKey
	 *
	 */

	@Transactional
	public SchoolMajor findSchoolMajorByPrimaryKey(String majorNumber, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findSchoolMajorByPrimaryKey", startResult, maxRows, majorNumber);
			return (net.xidlims.domain.SchoolMajor) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findSchoolMajorByStudentTypeContaining
	 *
	 */
	@Transactional
	public Set<SchoolMajor> findSchoolMajorByStudentTypeContaining(String studentType) throws DataAccessException {

		return findSchoolMajorByStudentTypeContaining(studentType, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolMajorByStudentTypeContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolMajor> findSchoolMajorByStudentTypeContaining(String studentType, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolMajorByStudentTypeContaining", startResult, maxRows, studentType);
		return new LinkedHashSet<SchoolMajor>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolMajorByCreatedBy
	 *
	 */
	@Transactional
	public Set<SchoolMajor> findSchoolMajorByCreatedBy(String createdBy) throws DataAccessException {

		return findSchoolMajorByCreatedBy(createdBy, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolMajorByCreatedBy
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolMajor> findSchoolMajorByCreatedBy(String createdBy, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolMajorByCreatedBy", startResult, maxRows, createdBy);
		return new LinkedHashSet<SchoolMajor>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolMajorByCreatedByContaining
	 *
	 */
	@Transactional
	public Set<SchoolMajor> findSchoolMajorByCreatedByContaining(String createdBy) throws DataAccessException {

		return findSchoolMajorByCreatedByContaining(createdBy, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolMajorByCreatedByContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolMajor> findSchoolMajorByCreatedByContaining(String createdBy, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolMajorByCreatedByContaining", startResult, maxRows, createdBy);
		return new LinkedHashSet<SchoolMajor>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolMajorByCreatedAt
	 *
	 */
	@Transactional
	public Set<SchoolMajor> findSchoolMajorByCreatedAt(java.util.Calendar createdAt) throws DataAccessException {

		return findSchoolMajorByCreatedAt(createdAt, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolMajorByCreatedAt
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolMajor> findSchoolMajorByCreatedAt(java.util.Calendar createdAt, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolMajorByCreatedAt", startResult, maxRows, createdAt);
		return new LinkedHashSet<SchoolMajor>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolMajorByMajorNumberContaining
	 *
	 */
	@Transactional
	public Set<SchoolMajor> findSchoolMajorByMajorNumberContaining(String majorNumber) throws DataAccessException {

		return findSchoolMajorByMajorNumberContaining(majorNumber, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolMajorByMajorNumberContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolMajor> findSchoolMajorByMajorNumberContaining(String majorNumber, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolMajorByMajorNumberContaining", startResult, maxRows, majorNumber);
		return new LinkedHashSet<SchoolMajor>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolMajorByUpdatedBy
	 *
	 */
	@Transactional
	public Set<SchoolMajor> findSchoolMajorByUpdatedBy(String updatedBy) throws DataAccessException {

		return findSchoolMajorByUpdatedBy(updatedBy, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolMajorByUpdatedBy
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolMajor> findSchoolMajorByUpdatedBy(String updatedBy, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolMajorByUpdatedBy", startResult, maxRows, updatedBy);
		return new LinkedHashSet<SchoolMajor>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolMajorByStudentType
	 *
	 */
	@Transactional
	public Set<SchoolMajor> findSchoolMajorByStudentType(String studentType) throws DataAccessException {

		return findSchoolMajorByStudentType(studentType, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolMajorByStudentType
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolMajor> findSchoolMajorByStudentType(String studentType, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolMajorByStudentType", startResult, maxRows, studentType);
		return new LinkedHashSet<SchoolMajor>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllSchoolMajors
	 *
	 */
	@Transactional
	public Set<SchoolMajor> findAllSchoolMajors() throws DataAccessException {

		return findAllSchoolMajors(-1, -1);
	}

	/**
	 * JPQL Query - findAllSchoolMajors
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolMajor> findAllSchoolMajors(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllSchoolMajors", startResult, maxRows);
		return new LinkedHashSet<SchoolMajor>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolMajorByMajorNumber
	 *
	 */
	@Transactional
	public SchoolMajor findSchoolMajorByMajorNumber(String majorNumber) throws DataAccessException {

		return findSchoolMajorByMajorNumber(majorNumber, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolMajorByMajorNumber
	 *
	 */

	@Transactional
	public SchoolMajor findSchoolMajorByMajorNumber(String majorNumber, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findSchoolMajorByMajorNumber", startResult, maxRows, majorNumber);
			return (net.xidlims.domain.SchoolMajor) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findSchoolMajorByMajorNameContaining
	 *
	 */
	@Transactional
	public Set<SchoolMajor> findSchoolMajorByMajorNameContaining(String majorName) throws DataAccessException {

		return findSchoolMajorByMajorNameContaining(majorName, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolMajorByMajorNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolMajor> findSchoolMajorByMajorNameContaining(String majorName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolMajorByMajorNameContaining", startResult, maxRows, majorName);
		return new LinkedHashSet<SchoolMajor>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolMajorByUpdatedByContaining
	 *
	 */
	@Transactional
	public Set<SchoolMajor> findSchoolMajorByUpdatedByContaining(String updatedBy) throws DataAccessException {

		return findSchoolMajorByUpdatedByContaining(updatedBy, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolMajorByUpdatedByContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolMajor> findSchoolMajorByUpdatedByContaining(String updatedBy, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolMajorByUpdatedByContaining", startResult, maxRows, updatedBy);
		return new LinkedHashSet<SchoolMajor>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(SchoolMajor entity) {
		return true;
	}
}
