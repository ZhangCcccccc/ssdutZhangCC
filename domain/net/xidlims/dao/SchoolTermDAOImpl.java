package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.SchoolTerm;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage SchoolTerm entities.
 * 
 */
@Repository("SchoolTermDAO")
@Transactional
public class SchoolTermDAOImpl extends AbstractJpaDao<SchoolTerm> implements
		SchoolTermDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { SchoolTerm.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new SchoolTermDAOImpl
	 *
	 */
	public SchoolTermDAOImpl() {
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
	 * JPQL Query - findSchoolTermByTermStartAfter
	 *
	 */
	@Transactional
	public Set<SchoolTerm> findSchoolTermByTermStartAfter(java.util.Calendar termStart) throws DataAccessException {

		return findSchoolTermByTermStartAfter(termStart, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolTermByTermStartAfter
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolTerm> findSchoolTermByTermStartAfter(java.util.Calendar termStart, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolTermByTermStartAfter", startResult, maxRows, termStart);
		return new LinkedHashSet<SchoolTerm>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolTermByYearCodeContaining
	 *
	 */
	@Transactional
	public Set<SchoolTerm> findSchoolTermByYearCodeContaining(String yearCode) throws DataAccessException {

		return findSchoolTermByYearCodeContaining(yearCode, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolTermByYearCodeContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolTerm> findSchoolTermByYearCodeContaining(String yearCode, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolTermByYearCodeContaining", startResult, maxRows, yearCode);
		return new LinkedHashSet<SchoolTerm>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolTermByTermCode
	 *
	 */
	@Transactional
	public Set<SchoolTerm> findSchoolTermByTermCode(Integer termCode) throws DataAccessException {

		return findSchoolTermByTermCode(termCode, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolTermByTermCode
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolTerm> findSchoolTermByTermCode(Integer termCode, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolTermByTermCode", startResult, maxRows, termCode);
		return new LinkedHashSet<SchoolTerm>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolTermByTermStartBefore
	 *
	 */
	@Transactional
	public Set<SchoolTerm> findSchoolTermByTermStartBefore(java.util.Calendar termStart) throws DataAccessException {

		return findSchoolTermByTermStartBefore(termStart, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolTermByTermStartBefore
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolTerm> findSchoolTermByTermStartBefore(java.util.Calendar termStart, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolTermByTermStartBefore", startResult, maxRows, termStart);
		return new LinkedHashSet<SchoolTerm>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolTermByTermEndBefore
	 *
	 */
	@Transactional
	public Set<SchoolTerm> findSchoolTermByTermEndBefore(java.util.Calendar termEnd) throws DataAccessException {

		return findSchoolTermByTermEndBefore(termEnd, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolTermByTermEndBefore
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolTerm> findSchoolTermByTermEndBefore(java.util.Calendar termEnd, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolTermByTermEndBefore", startResult, maxRows, termEnd);
		return new LinkedHashSet<SchoolTerm>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolTermByTermEndAfter
	 *
	 */
	@Transactional
	public Set<SchoolTerm> findSchoolTermByTermEndAfter(java.util.Calendar termEnd) throws DataAccessException {

		return findSchoolTermByTermEndAfter(termEnd, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolTermByTermEndAfter
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolTerm> findSchoolTermByTermEndAfter(java.util.Calendar termEnd, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolTermByTermEndAfter", startResult, maxRows, termEnd);
		return new LinkedHashSet<SchoolTerm>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolTermByUpdatedAt
	 *
	 */
	@Transactional
	public Set<SchoolTerm> findSchoolTermByUpdatedAt(java.util.Calendar updatedAt) throws DataAccessException {

		return findSchoolTermByUpdatedAt(updatedAt, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolTermByUpdatedAt
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolTerm> findSchoolTermByUpdatedAt(java.util.Calendar updatedAt, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolTermByUpdatedAt", startResult, maxRows, updatedAt);
		return new LinkedHashSet<SchoolTerm>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolTermByTermNameContaining
	 *
	 */
	@Transactional
	public Set<SchoolTerm> findSchoolTermByTermNameContaining(String termName) throws DataAccessException {

		return findSchoolTermByTermNameContaining(termName, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolTermByTermNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolTerm> findSchoolTermByTermNameContaining(String termName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolTermByTermNameContaining", startResult, maxRows, termName);
		return new LinkedHashSet<SchoolTerm>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolTermByCreatedAt
	 *
	 */
	@Transactional
	public Set<SchoolTerm> findSchoolTermByCreatedAt(java.util.Calendar createdAt) throws DataAccessException {

		return findSchoolTermByCreatedAt(createdAt, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolTermByCreatedAt
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolTerm> findSchoolTermByCreatedAt(java.util.Calendar createdAt, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolTermByCreatedAt", startResult, maxRows, createdAt);
		return new LinkedHashSet<SchoolTerm>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolTermByTermStart
	 *
	 */
	@Transactional
	public Set<SchoolTerm> findSchoolTermByTermStart(java.util.Calendar termStart) throws DataAccessException {

		return findSchoolTermByTermStart(termStart, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolTermByTermStart
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolTerm> findSchoolTermByTermStart(java.util.Calendar termStart, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolTermByTermStart", startResult, maxRows, termStart);
		return new LinkedHashSet<SchoolTerm>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolTermById
	 *
	 */
	@Transactional
	public SchoolTerm findSchoolTermById(Integer id) throws DataAccessException {

		return findSchoolTermById(id, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolTermById
	 *
	 */

	@Transactional
	public SchoolTerm findSchoolTermById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findSchoolTermById", startResult, maxRows, id);
			return (net.xidlims.domain.SchoolTerm) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findAllSchoolTerms
	 *
	 */
	@Transactional
	public Set<SchoolTerm> findAllSchoolTerms() throws DataAccessException {

		return findAllSchoolTerms(-1, -1);
	}

	/**
	 * JPQL Query - findAllSchoolTerms
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolTerm> findAllSchoolTerms(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllSchoolTerms", startResult, maxRows);
		return new LinkedHashSet<SchoolTerm>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolTermByPrimaryKey
	 *
	 */
	@Transactional
	public SchoolTerm findSchoolTermByPrimaryKey(Integer id) throws DataAccessException {

		return findSchoolTermByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolTermByPrimaryKey
	 *
	 */

	@Transactional
	public SchoolTerm findSchoolTermByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findSchoolTermByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.SchoolTerm) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findSchoolTermByTermEnd
	 *
	 */
	@Transactional
	public Set<SchoolTerm> findSchoolTermByTermEnd(java.util.Calendar termEnd) throws DataAccessException {

		return findSchoolTermByTermEnd(termEnd, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolTermByTermEnd
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolTerm> findSchoolTermByTermEnd(java.util.Calendar termEnd, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolTermByTermEnd", startResult, maxRows, termEnd);
		return new LinkedHashSet<SchoolTerm>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolTermByTermName
	 *
	 */
	@Transactional
	public Set<SchoolTerm> findSchoolTermByTermName(String termName) throws DataAccessException {

		return findSchoolTermByTermName(termName, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolTermByTermName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolTerm> findSchoolTermByTermName(String termName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolTermByTermName", startResult, maxRows, termName);
		return new LinkedHashSet<SchoolTerm>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolTermByYearCode
	 *
	 */
	@Transactional
	public Set<SchoolTerm> findSchoolTermByYearCode(String yearCode) throws DataAccessException {

		return findSchoolTermByYearCode(yearCode, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolTermByYearCode
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolTerm> findSchoolTermByYearCode(String yearCode, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolTermByYearCode", startResult, maxRows, yearCode);
		return new LinkedHashSet<SchoolTerm>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(SchoolTerm entity) {
		return true;
	}
}
