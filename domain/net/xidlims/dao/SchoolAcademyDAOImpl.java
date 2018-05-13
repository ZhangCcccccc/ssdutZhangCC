package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.SchoolAcademy;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage SchoolAcademy entities.
 * 
 */
@Repository("SchoolAcademyDAO")
@Transactional
public class SchoolAcademyDAOImpl extends AbstractJpaDao<SchoolAcademy>
		implements SchoolAcademyDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { SchoolAcademy.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new SchoolAcademyDAOImpl
	 *
	 */
	public SchoolAcademyDAOImpl() {
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
	 * JPQL Query - findAllSchoolAcademys
	 *
	 */
	@Transactional
	public Set<SchoolAcademy> findAllSchoolAcademys() throws DataAccessException {

		return findAllSchoolAcademys(-1, -1);
	}

	/**
	 * JPQL Query - findAllSchoolAcademys
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolAcademy> findAllSchoolAcademys(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllSchoolAcademys", startResult, maxRows);
		return new LinkedHashSet<SchoolAcademy>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolAcademyByAcademyTypeContaining
	 *
	 */
	@Transactional
	public Set<SchoolAcademy> findSchoolAcademyByAcademyTypeContaining(String academyType) throws DataAccessException {

		return findSchoolAcademyByAcademyTypeContaining(academyType, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolAcademyByAcademyTypeContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolAcademy> findSchoolAcademyByAcademyTypeContaining(String academyType, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolAcademyByAcademyTypeContaining", startResult, maxRows, academyType);
		return new LinkedHashSet<SchoolAcademy>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolAcademyByPrimaryKey
	 *
	 */
	@Transactional
	public SchoolAcademy findSchoolAcademyByPrimaryKey(String academyNumber) throws DataAccessException {

		return findSchoolAcademyByPrimaryKey(academyNumber, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolAcademyByPrimaryKey
	 *
	 */

	@Transactional
	public SchoolAcademy findSchoolAcademyByPrimaryKey(String academyNumber, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findSchoolAcademyByPrimaryKey", startResult, maxRows, academyNumber);
			return (net.xidlims.domain.SchoolAcademy) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findSchoolAcademyByAcademyNameContaining
	 *
	 */
	@Transactional
	public Set<SchoolAcademy> findSchoolAcademyByAcademyNameContaining(String academyName) throws DataAccessException {

		return findSchoolAcademyByAcademyNameContaining(academyName, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolAcademyByAcademyNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolAcademy> findSchoolAcademyByAcademyNameContaining(String academyName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolAcademyByAcademyNameContaining", startResult, maxRows, academyName);
		return new LinkedHashSet<SchoolAcademy>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolAcademyByIsVaild
	 *
	 */
	@Transactional
	public Set<SchoolAcademy> findSchoolAcademyByIsVaild(Boolean isVaild) throws DataAccessException {

		return findSchoolAcademyByIsVaild(isVaild, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolAcademyByIsVaild
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolAcademy> findSchoolAcademyByIsVaild(Boolean isVaild, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolAcademyByIsVaild", startResult, maxRows, isVaild);
		return new LinkedHashSet<SchoolAcademy>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolAcademyByAcademyType
	 *
	 */
	@Transactional
	public Set<SchoolAcademy> findSchoolAcademyByAcademyType(String academyType) throws DataAccessException {

		return findSchoolAcademyByAcademyType(academyType, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolAcademyByAcademyType
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolAcademy> findSchoolAcademyByAcademyType(String academyType, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolAcademyByAcademyType", startResult, maxRows, academyType);
		return new LinkedHashSet<SchoolAcademy>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolAcademyByCreatedAt
	 *
	 */
	@Transactional
	public Set<SchoolAcademy> findSchoolAcademyByCreatedAt(java.util.Calendar createdAt) throws DataAccessException {

		return findSchoolAcademyByCreatedAt(createdAt, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolAcademyByCreatedAt
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolAcademy> findSchoolAcademyByCreatedAt(java.util.Calendar createdAt, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolAcademyByCreatedAt", startResult, maxRows, createdAt);
		return new LinkedHashSet<SchoolAcademy>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolAcademyByAcademyNumber
	 *
	 */
	@Transactional
	public SchoolAcademy findSchoolAcademyByAcademyNumber(String academyNumber) throws DataAccessException {

		return findSchoolAcademyByAcademyNumber(academyNumber, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolAcademyByAcademyNumber
	 *
	 */

	@Transactional
	public SchoolAcademy findSchoolAcademyByAcademyNumber(String academyNumber, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findSchoolAcademyByAcademyNumber", startResult, maxRows, academyNumber);
			return (net.xidlims.domain.SchoolAcademy) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findSchoolAcademyByAcademyName
	 *
	 */
	@Transactional
	public Set<SchoolAcademy> findSchoolAcademyByAcademyName(String academyName) throws DataAccessException {

		return findSchoolAcademyByAcademyName(academyName, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolAcademyByAcademyName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolAcademy> findSchoolAcademyByAcademyName(String academyName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolAcademyByAcademyName", startResult, maxRows, academyName);
		return new LinkedHashSet<SchoolAcademy>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolAcademyByUpdatedAt
	 *
	 */
	@Transactional
	public Set<SchoolAcademy> findSchoolAcademyByUpdatedAt(java.util.Calendar updatedAt) throws DataAccessException {

		return findSchoolAcademyByUpdatedAt(updatedAt, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolAcademyByUpdatedAt
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolAcademy> findSchoolAcademyByUpdatedAt(java.util.Calendar updatedAt, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolAcademyByUpdatedAt", startResult, maxRows, updatedAt);
		return new LinkedHashSet<SchoolAcademy>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolAcademyByAcademyNumberContaining
	 *
	 */
	@Transactional
	public Set<SchoolAcademy> findSchoolAcademyByAcademyNumberContaining(String academyNumber) throws DataAccessException {

		return findSchoolAcademyByAcademyNumberContaining(academyNumber, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolAcademyByAcademyNumberContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolAcademy> findSchoolAcademyByAcademyNumberContaining(String academyNumber, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolAcademyByAcademyNumberContaining", startResult, maxRows, academyNumber);
		return new LinkedHashSet<SchoolAcademy>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(SchoolAcademy entity) {
		return true;
	}
}
